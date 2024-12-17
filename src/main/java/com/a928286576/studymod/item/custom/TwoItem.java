package com.a928286576.studymod.item.custom;

import com.a928286576.studymod.block.ModBlocks;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class TwoItem extends Item {
    //这里定义了一个Map，用于存储原木转为方块的对应关系
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
                    Blocks.IRON_ORE, Blocks.STONE,
                    Blocks.NETHERRACK, ModBlocks.ONE_BLOCK.get()
            );
    //这里定义了一个构造方法，用于初始化Item的属性
    public TwoItem(Properties properties){
        super(properties);
    }

    @Override
    //这里重写了Item的useOn方法，用于实现方块转为方块的功能
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        //如果点击的方块在CHISEL_MAP中，则将其转为对应的方块
        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                //Only on server side
                //这里调用了Level的setBlockAndUpdate方法
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
                //这里调用了Item的hurtAndBreak方法，用于损耗物品耐久
                context.getItemInHand().hurtAndBreak(1,((ServerLevel)level),context.getPlayer(),
                    //这里调用了Player的onEquippedItemBroken方法，用于在物品损坏时正常触发
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                //触发事件后,播放了一个音效
                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }

    //这里重写了Item的appendHoverText方法，用于添加物品的提示信息
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.studymod.two.shift_tooltip1"));
            tooltipComponents.add(Component.translatable("tooltip.studymod.two.shift_tooltip2"));
            tooltipComponents.add(Component.translatable("tooltip.studymod.two.shift_tooltip3"));
        }else{
            tooltipComponents.add(Component.translatable("tooltip.studymod.two.tooltip1"));
            tooltipComponents.add(Component.translatable("tooltip.studymod.two.tooltip2"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    /*暂时不用
    @Override
    //这里重写了Item的getUseAnimation方法，用于设置物品的动画效果
    public UseAnim getUseAnimation(ItemStack stack) {
       //这里返回了DRINK，表示物品的动画效果为饮用
        return UseAnim.DRINK;
    }
    */
}
