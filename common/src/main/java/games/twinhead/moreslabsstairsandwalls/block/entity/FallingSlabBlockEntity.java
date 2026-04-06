package games.twinhead.moreslabsstairsandwalls.block.entity;

import games.twinhead.moreslabsstairsandwalls.registry.ModRegistry;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
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

    public int time;
    public boolean dropItem = true;
    private boolean destroyedOnLanding;
    private boolean hurtEntities;
    private int fallHurtMax = 40;
    private float fallHurtAmount;
    @Nullable
    public CompoundTag blockData;

    public FallingSlabBlockEntity(EntityType<? extends FallingSlabBlockEntity> entityType, Level world) {
        super(entityType, world);
    }

    private FallingSlabBlockEntity(Level world, double x, double y, double z, BlockState state) {
        super(ModRegistry.getFallingSlabEntityType(), world);
        this.blockState = state;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.entityData.set(FallingBlockEntity.DATA_START_POS, this.blockPosition());
    }

    public static FallingSlabBlockEntity fall(Level world, BlockPos pos, BlockState state) {
        FallingSlabBlockEntity fallingBlockEntity = new FallingSlabBlockEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, state.hasProperty(BlockStateProperties.WATERLOGGED) ? (BlockState)state.setValue(BlockStateProperties.WATERLOGGED, false) : state);
        world.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        world.addFreshEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    public void tick() {
        if (this.blockState.isAir()) {
            this.discard();
        } else {
            Block slabBlock = this.blockState.getBlock();
            ++this.time;
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            if (!this.level().isClientSide) {
                BlockPos blockPos = this.blockPosition();
                boolean bl = this.blockState.getBlock() instanceof ConcretePowderBlock;
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
                            this.spawnAtLocation(slabBlock);
                        }

                        this.discard();
                    }
                } else {
                    BlockState blockState = this.level().getBlockState(blockPos);
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
                    if (!blockState.is(Blocks.MOVING_PISTON)) {
                        boolean bl3 = blockState.canBeReplaced(new DirectionalPlaceContext(this.level(), blockPos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                        boolean bl4 = FallingBlock.isFree(this.level().getBlockState(blockPos.below())) && (!bl || !bl2);
                        boolean bl5 = this.blockState.canSurvive(this.level(), blockPos) && !bl4;
                        if (bl3 && bl5) {
                            if (this.blockState.hasProperty(BlockStateProperties.WATERLOGGED) && this.level().getFluidState(blockPos).getType() == Fluids.WATER) {
                                this.blockState = this.blockState.setValue(BlockStateProperties.WATERLOGGED, true);
                            }

                            if (this.level().setBlock(blockPos, this.blockState, 3)) {
                                ((ServerLevel)this.level()).getChunkSource().broadcast(this, new ClientboundBlockUpdatePacket(blockPos, this.level().getBlockState(blockPos)));
                                this.discard();

                                if (slabBlock instanceof LandingSlabBlock) {
                                    ((LandingSlabBlock)slabBlock).onLanding(this.level(), blockPos, this.blockState, blockState, this);
                                }

                                if (this.blockData != null && this.blockState.hasBlockEntity()) {
                                    BlockEntity blockEntity = this.level().getBlockEntity(blockPos);
                                    if (blockEntity != null) {
                                        CompoundTag nbtCompound = blockEntity.saveWithoutMetadata(this.level().registryAccess());
                                        for (String string : this.blockData.getAllKeys()) {
                                            nbtCompound.put(string, this.blockData.get(string).copy());
                                        }
                                        blockEntity.loadCustomOnly(nbtCompound, this.level().registryAccess());
                                        blockEntity.setChanged();
                                    }
                                }
                            } else if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                this.discard();
                                this.callOnBrokenAfterFall(slabBlock, blockPos);
                            }
                        } else {
                            this.discard();
                            if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                this.callOnBrokenAfterFall(slabBlock, blockPos);
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
        nbt.put("BlockState", NbtUtils.writeBlockState(this.blockState));
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
        this.blockState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), nbt.getCompound("BlockState"));
        this.time = nbt.getInt("Time");
        if (nbt.contains("HurtEntities", Tag.TAG_ANY_NUMERIC)) {
            this.hurtEntities = nbt.getBoolean("HurtEntities");
            this.fallHurtAmount = nbt.getFloat("FallHurtAmount");
            this.fallHurtMax = nbt.getInt("FallHurtMax");
        } else if (this.blockState.is(BlockTags.ANVIL)) {
            this.hurtEntities = true;
        }
        if (nbt.contains("DropItem", Tag.TAG_ANY_NUMERIC)) {
            this.dropItem = nbt.getBoolean("DropItem");
        }
        if (nbt.contains("TileEntityData", Tag.TAG_COMPOUND)) {
            this.blockData = nbt.getCompound("TileEntityData").copy();
        }
        this.destroyedOnLanding = nbt.getBoolean("CancelDrop");
        if (this.blockState.isAir()) {
            this.blockState = Blocks.SAND.defaultBlockState();
        }
    }

}
