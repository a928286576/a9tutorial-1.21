package com.a928286576.studymod.block.custom;

import com.a928286576.studymod.item.ModItems;
import com.a928286576.studymod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class MagicBlock extends Block {
    //构造函数
    public MagicBlock(Properties properties) {
        //调用父类构造函数
        super(properties);
    }

    //此处为魔法方块的右键点击事件, 空手右键点击触发
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.SUCCESS;
    }

    //此处为魔法方块的物品交互事件, 当物品以掉落物形式落在魔法方块上时触发
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        //判断实体是否为ItemEntity
        if (entity instanceof ItemEntity itemEntity){
            //判断物品是否为对应物品
            /*(此处改写为使用tag)  'if (itemEntity.getItem().getItem() == ModItems.ONE.get()){'  */
            if (isValidItem(itemEntity.getItem())){
                //将物品替换为对应物品
                itemEntity.setItem(new ItemStack(ModItems.TWO.get(), itemEntity.getItem().getCount()));
            }

            if (itemEntity.getItem().getItem() == Items.DANDELION){
                itemEntity.setItem(new ItemStack(Items.WITHER_ROSE, itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    //此处为魔法方块的工具提示信息
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.studymod.magic_block.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
