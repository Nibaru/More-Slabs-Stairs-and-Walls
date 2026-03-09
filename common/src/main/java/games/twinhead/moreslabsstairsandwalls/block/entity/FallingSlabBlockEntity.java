package games.twinhead.moreslabsstairsandwalls.block.entity;

import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FallingSlabBlockEntity extends FallingBlockEntity {

    private BlockState block;
    public int time;
    public boolean dropItem = true;
    private boolean destroyedOnLanding;
    private boolean hurtEntities;
    private int fallHurtMax = 40;
    private float fallHurtAmount;
    @Nullable
    public CompoundTag blockData;
    protected static final EntityDataAccessor<BlockPos> DATA_START_POS = SynchedEntityData.defineId(FallingSlabBlockEntity.class, EntityDataSerializers.BLOCK_POS);

    public FallingSlabBlockEntity(EntityType<? extends FallingSlabBlockEntity> entityType, Level world) {
        super(entityType, world);
    }

    private FallingSlabBlockEntity(Level world, double x, double y, double z, BlockState block) {
        super(ModRegistry.getFallingSlabEntityType(), world);
        this.block = block;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setStartPos(this.blockPosition());
    }

    public static FallingSlabBlockEntity fall(Level world, BlockPos pos, BlockState state) {
        FallingSlabBlockEntity fallingBlockEntity = new FallingSlabBlockEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, state.hasProperty(BlockStateProperties.WATERLOGGED) ? (BlockState)state.setValue(BlockStateProperties.WATERLOGGED, false) : state);
        world.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        world.addFreshEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    public void tick() {
        if (this.block.isAir()) {
            this.discard();
        } else {
            Block block = this.block.getBlock();
            ++this.time;
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            if (!this.level().isClientSide) {
                BlockPos blockPos = this.blockPosition();
                boolean bl = this.block.getBlock() instanceof ConcretePowderBlock;
                boolean bl2 = bl && this.level().getFluidState(blockPos).is(FluidTags.WATER);
                double d = this.getDeltaMovement().lengthSqr();
                if (bl && d > 1.0) {
                    BlockHitResult blockHitResult = this.level().clip(new ClipContext(new Vec3(this.xo, this.yo, this.zo), this.position(), ClipContext.Block.COLLIDER, ClipContext.Fluid.SOURCE_ONLY, this));
                    if (blockHitResult.getType() != HitResult.Type.MISS && this.level().getFluidState(blockHitResult.getBlockPos()).is(FluidTags.WATER)) {
                        blockPos = blockHitResult.getBlockPos();
                        bl2 = true;
                    }
                }

                if (!this.onGround() && !bl2) {
                    if (!this.level().isClientSide && (this.time > 100 && (blockPos.getY() <= this.level().getMinBuildHeight() || blockPos.getY() > this.level().getMaxBuildHeight()) || this.time > 600)) {
                        if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            this.spawnAtLocation(block);
                        }

                        this.discard();
                    }
                } else {
                    BlockState blockState = this.level().getBlockState(blockPos);
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
                    if (!blockState.is(Blocks.MOVING_PISTON)) {
                        boolean bl3 = blockState.canBeReplaced(new DirectionalPlaceContext(this.level(), blockPos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                        boolean bl4 = FallingBlock.isFree(this.level().getBlockState(blockPos.below())) && (!bl || !bl2);
                        boolean bl5 = this.block.canSurvive(this.level(), blockPos) && !bl4;
                        if (bl3 && bl5) {
                            if (this.block.hasProperty(BlockStateProperties.WATERLOGGED) && this.level().getFluidState(blockPos).getType() == Fluids.WATER) {
                                this.block = this.block.setValue(BlockStateProperties.WATERLOGGED, true);
                            }

                            if (this.level().setBlock(blockPos, this.block, 3)) {
                                ((ServerLevel)this.level()).getChunkSource().chunkMap.broadcast(this, new ClientboundBlockUpdatePacket(blockPos, this.level().getBlockState(blockPos)));
                                this.discard();

                                if (block instanceof LandingSlabBlock) {
                                    ((LandingSlabBlock)block).onLanding(this.level(), blockPos, this.block, blockState, this);
                                }

                                if (this.blockData != null && this.block.hasBlockEntity()) {
                                    BlockEntity blockEntity = this.level().getBlockEntity(blockPos);
                                    if (blockEntity != null) {
                                        CompoundTag nbtCompound = blockEntity.saveWithoutMetadata();

                                        for (String string : this.blockData.getAllKeys()) {
                                            nbtCompound.put(string, this.blockData.get(string).copy());
                                        }

                                        try {
                                            blockEntity.load(nbtCompound);
                                        } catch (Exception var15) {
                                            //field_36333.error("Failed to load block entity from falling block", var15);
                                        }

                                        blockEntity.setChanged();
                                    }
                                }
                            } else if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                this.discard();
                                this.callOnBrokenAfterFall(block, blockPos);
                            }
                        } else {
                            this.discard();
                            if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                this.callOnBrokenAfterFall(block, blockPos);
                            }
                        }
                    }
                }
            }

            this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        }
    }
    public void callOnBrokenAfterFall(Block block, BlockPos pos) {
        if (block instanceof LandingSlabBlock) {
            ((LandingSlabBlock) block).onDestroyedOnLanding(this.level(), pos, this);
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.put("BlockState", NbtUtils.writeBlockState(this.block));
        nbt.putInt("Time", this.time);
        nbt.putBoolean("DropItem", this.dropItem);
        nbt.putBoolean("HurtEntities", this.hurtEntities);
        nbt.putFloat("FallHurtAmount", this.fallHurtAmount);
        nbt.putInt("FallHurtMax", this.fallHurtMax);
        if (this.blockData != null) {
            nbt.put("TileEntityData", this.blockData);
        }
        nbt.putBoolean("CancelDrop", this.destroyedOnLanding);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.block = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), nbt.getCompound("BlockState"));
        this.time = nbt.getInt("Time");
        if (nbt.contains("HurtEntities", Tag.TAG_ANY_NUMERIC)) {
            this.hurtEntities = nbt.getBoolean("HurtEntities");
            this.fallHurtAmount = nbt.getFloat("FallHurtAmount");
            this.fallHurtMax = nbt.getInt("FallHurtMax");
        } else if (this.block.is(BlockTags.ANVIL)) {
            this.hurtEntities = true;
        }
        if (nbt.contains("DropItem", Tag.TAG_ANY_NUMERIC)) {
            this.dropItem = nbt.getBoolean("DropItem");
        }
        if (nbt.contains("TileEntityData", Tag.TAG_COMPOUND)) {
            this.blockData = nbt.getCompound("TileEntityData").copy();
        }
        this.destroyedOnLanding = nbt.getBoolean("CancelDrop");
        if (this.block.isAir()) {
            this.block = Blocks.SAND.defaultBlockState();
        }
    }

    public BlockState getBlockState() {
        return this.block;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, Block.getId(this.getBlockState()));
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        this.block = Block.stateById(packet.getData());
        this.blocksBuilding = true;
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        this.setPos(d, e, f);
        this.setStartPos(this.blockPosition());
    }


    public void setStartPos(BlockPos pos) {
        this.entityData.set(DATA_START_POS, pos);
    }

    public BlockPos getStartPos() {
        return this.entityData.get(DATA_START_POS);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_START_POS, BlockPos.ZERO);
    }



}
