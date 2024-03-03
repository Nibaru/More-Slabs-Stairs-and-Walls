package games.twinhead.moreslabsstairsandwalls.block.entity;

import com.mojang.logging.LogUtils;
import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Iterator;

public class FallingSlabBlockEntity extends FallingBlockEntity {

    private BlockState block;
    private static final Logger LOGGER = LogUtils.getLogger();
    public int timeFalling;
    public boolean dropItem = true;
    private boolean destroyedOnLanding;
    private boolean hurtEntities;
    private int fallHurtMax = 40;
    private float fallHurtAmount;
    @Nullable
    public NbtCompound blockEntityData;
    protected static final TrackedData<BlockPos> BLOCK_POS = DataTracker.registerData(FallingSlabBlockEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);

    public FallingSlabBlockEntity(EntityType<? extends FallingSlabBlockEntity> entityType, World world) {
        super(entityType, world);
    }

    private FallingSlabBlockEntity(World world, double x, double y, double z, BlockState block) {
        super(ModRegistry.getFallingSlabEntityType(), world);
        this.block = block;
        this.intersectionChecked = true;
        this.setPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.setFallingBlockPos(this.getBlockPos());
    }

    public static FallingSlabBlockEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
        FallingSlabBlockEntity fallingBlockEntity = new FallingSlabBlockEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? (BlockState)state.with(Properties.WATERLOGGED, false) : state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), 3);
        world.spawnEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    public void tick() {
        if (this.block.isAir()) {
            this.discard();
        } else {
            Block block = this.block.getBlock();
            ++this.timeFalling;
            if (!this.hasNoGravity()) {
                this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
            }

            this.move(MovementType.SELF, this.getVelocity());
            if (!this.getWorld().isClient) {
                BlockPos blockPos = this.getBlockPos();
                boolean bl = this.block.getBlock() instanceof ConcretePowderBlock;
                boolean bl2 = bl && this.getWorld().getFluidState(blockPos).isIn(FluidTags.WATER);
                double d = this.getVelocity().lengthSquared();
                if (bl && d > 1.0) {
                    BlockHitResult blockHitResult = this.getWorld().raycast(new RaycastContext(new Vec3d(this.prevX, this.prevY, this.prevZ), this.getPos(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.SOURCE_ONLY, this));
                    if (blockHitResult.getType() != HitResult.Type.MISS && this.getWorld().getFluidState(blockHitResult.getBlockPos()).isIn(FluidTags.WATER)) {
                        blockPos = blockHitResult.getBlockPos();
                        bl2 = true;
                    }
                }

                if (!this.isOnGround() && !bl2) {
                    if (!this.getWorld().isClient && (this.timeFalling > 100 && (blockPos.getY() <= this.getWorld().getBottomY() || blockPos.getY() > this.getWorld().getTopY()) || this.timeFalling > 600)) {
                        if (this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                            this.dropItem(block);
                        }

                        this.discard();
                    }
                } else {
                    BlockState blockState = this.getWorld().getBlockState(blockPos);
                    this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
                    if (!blockState.isOf(Blocks.MOVING_PISTON)) {
                        boolean bl3 = blockState.canReplace(new AutomaticItemPlacementContext(this.getWorld(), blockPos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                        boolean bl4 = FallingBlock.canFallThrough(this.getWorld().getBlockState(blockPos.down())) && (!bl || !bl2);
                        boolean bl5 = this.block.canPlaceAt(this.getWorld(), blockPos) && !bl4;
                        if (bl3 && bl5) {
                            if (this.block.contains(Properties.WATERLOGGED) && this.getWorld().getFluidState(blockPos).getFluid() == Fluids.WATER) {
                                this.block = (BlockState)this.block.with(Properties.WATERLOGGED, true);
                            }

                            if (this.getWorld().setBlockState(blockPos, this.block, 3)) {
                                ((ServerWorld)this.getWorld()).getChunkManager().threadedAnvilChunkStorage.sendToOtherNearbyPlayers(this, new BlockUpdateS2CPacket(blockPos, this.getWorld().getBlockState(blockPos)));
                                this.discard();

                                if (block instanceof LandingSlabBlock) {
                                    ((LandingSlabBlock)block).onLanding(this.getWorld(), blockPos, this.block, blockState, this);
                                }

                                if (this.blockEntityData != null && this.block.hasBlockEntity()) {
                                    BlockEntity blockEntity = this.getWorld().getBlockEntity(blockPos);
                                    if (blockEntity != null) {
                                        NbtCompound nbtCompound = blockEntity.createNbt();
                                        Iterator var13 = this.blockEntityData.getKeys().iterator();

                                        while(var13.hasNext()) {
                                            String string = (String)var13.next();
                                            nbtCompound.put(string, this.blockEntityData.get(string).copy());
                                        }

                                        try {
                                            blockEntity.readNbt(nbtCompound);
                                        } catch (Exception var15) {
                                            //field_36333.error("Failed to load block entity from falling block", var15);
                                        }

                                        blockEntity.markDirty();
                                    }
                                }
                            } else if (this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                                this.discard();
                                this.onDestroyedOnLanding(block, blockPos);
                            }
                        } else {
                            this.discard();
                            if (this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                                this.onDestroyedOnLanding(block, blockPos);
                            }
                        }
                    }
                }
            }

            this.setVelocity(this.getVelocity().multiply(0.98));
        }
    }
    public void onDestroyedOnLanding(Block block, BlockPos pos) {
        if (block instanceof LandingSlabBlock) {
            ((LandingSlabBlock)((Object)block)).onDestroyedOnLanding(this.getWorld(), pos, this);
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.put("BlockState", NbtHelper.fromBlockState(this.block));
        nbt.putInt("Time", this.timeFalling);
        nbt.putBoolean("DropItem", this.dropItem);
        nbt.putBoolean("HurtEntities", this.hurtEntities);
        nbt.putFloat("FallHurtAmount", this.fallHurtAmount);
        nbt.putInt("FallHurtMax", this.fallHurtMax);
        if (this.blockEntityData != null) {
            nbt.put("TileEntityData", this.blockEntityData);
        }
        nbt.putBoolean("CancelDrop", this.destroyedOnLanding);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.block = NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound("BlockState"));
        this.timeFalling = nbt.getInt("Time");
        if (nbt.contains("HurtEntities", NbtElement.NUMBER_TYPE)) {
            this.hurtEntities = nbt.getBoolean("HurtEntities");
            this.fallHurtAmount = nbt.getFloat("FallHurtAmount");
            this.fallHurtMax = nbt.getInt("FallHurtMax");
        } else if (this.block.isIn(BlockTags.ANVIL)) {
            this.hurtEntities = true;
        }
        if (nbt.contains("DropItem", NbtElement.NUMBER_TYPE)) {
            this.dropItem = nbt.getBoolean("DropItem");
        }
        if (nbt.contains("TileEntityData", NbtElement.COMPOUND_TYPE)) {
            this.blockEntityData = nbt.getCompound("TileEntityData").copy();
        }
        this.destroyedOnLanding = nbt.getBoolean("CancelDrop");
        if (this.block.isAir()) {
            this.block = Blocks.SAND.getDefaultState();
        }
    }

    public BlockState getBlockState() {
        return this.block;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(this.getBlockState()));
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        this.block = Block.getStateFromRawId(packet.getEntityData());
        this.intersectionChecked = true;
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        this.setPosition(d, e, f);
        this.setFallingBlockPos(this.getBlockPos());
    }


    public void setFallingBlockPos(BlockPos pos) {
        this.dataTracker.set(BLOCK_POS, pos);
    }

    public BlockPos getFallingBlockPos() {
        return this.dataTracker.get(BLOCK_POS);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(BLOCK_POS, BlockPos.ORIGIN);
    }



}
