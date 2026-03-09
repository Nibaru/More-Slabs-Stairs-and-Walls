package games.twinhead.moreslabsstairsandwalls.block.oxidizable;

import games.twinhead.moreslabsstairsandwalls.block.ModBlocks;
import games.twinhead.moreslabsstairsandwalls.block.base.BaseSlab;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("deprecation")
public class OxidizableSlab extends BaseSlab implements CustomOxidizable {

    private final WeatheringCopper.WeatherState oxidationLevel;
    private final ModBlocks nextBlock;

    public OxidizableSlab(ModBlocks block, WeatheringCopper.WeatherState oxidationLevel, ModBlocks nextBlock, Properties arg) {
        super(block,arg);
        this.oxidationLevel = oxidationLevel;
        this.nextBlock = nextBlock;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return useItem(itemStack, state, world, pos, player, hand);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        changeOverTime(state, world, pos, random);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return this.oxidationLevel != WeatheringCopper.WeatherState.OXIDIZED;
    }

    public WeatheringCopper.WeatherState getAge() {
        return this.oxidationLevel;
    }

    public Optional<BlockState> getNext(BlockState state) {
        return Optional.ofNullable((this.nextBlock == null ? state : nextBlock.getBlock(getBlockType()).withPropertiesOf(state)));
    }


}
