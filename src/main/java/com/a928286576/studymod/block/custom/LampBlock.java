package com.a928286576.studymod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class LampBlock extends Block {
    public static final MapCodec<LampBlock> CODEC = simpleCodec(LampBlock::new);
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    @Override
    public MapCodec<LampBlock> codec() {
        return CODEC;
    }
    public LampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(CLICKED, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
    }

    @Override
    // 这个改写让玩家右键能够改变方块状态
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()){
            boolean currentState = state.getValue(CLICKED);
            level.setBlockAndUpdate(pos, state.setValue(CLICKED, !currentState));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    // 这个改写让方块接收到红石信号时候改变一次状态,就如同右键一样
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean redPower = level.hasNeighborSignal(pos);
            if(redPower){
                level.setBlock(pos, state.cycle(CLICKED), 2);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKED);
    }
}
