package com.a928286576.studymod.item;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.item.custom.FuelItem;
import com.a928286576.studymod.item.custom.HammerItem;
import com.a928286576.studymod.item.custom.TwoItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

//(第一节创建物品)创建物品的前置内容
public class ModItems {
    //(第一节创建物品)在原版的创造物品处注册ID
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StudyMod.MOD_ID);
    //(第一节创建物品)下面是创建了一个id为one的物品
    public static final DeferredItem<Item> ONE = ITEMS.register("one",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TWO = ITEMS.register("two",
            () -> new TwoItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> THREE = ITEMS.register("three",
            () -> new Item(new Item.Properties().food(ModFoods.THREE)){
                //这也是一种添加物品信息的方法,适用于常规的物品,我想后面我可以改写使其更加简便
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.studymod.three.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> FOUR = ITEMS.register("four",
            () -> new FuelItem(new Item.Properties(),800));

    public static final DeferredItem<Item> FIVE = ITEMS.register("five",
            () -> new Item(new Item.Properties()));

    //一把剑
    public static final DeferredItem<SwordItem> ONE_SWORD = ITEMS.register("one_sword",
            () -> new SwordItem(ModToolTiers.ONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ONE, 2, -2f))));
    //一把稿
    public static final DeferredItem<PickaxeItem> ONE_PICKAXE = ITEMS.register("one_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ONE, 1.0f, -2f))));
    //一把铲
    public static final DeferredItem<ShovelItem> ONE_SHOVEL = ITEMS.register("one_shovel",
            () -> new ShovelItem(ModToolTiers.ONE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.ONE, 1.0f, -1.5f))));
    //一把斧
    public static final DeferredItem<AxeItem> ONE_AXE = ITEMS.register("one_axe",
            () -> new AxeItem(ModToolTiers.ONE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.ONE, 4.0f, -2f))));
    //一把锄
    public static final DeferredItem<HoeItem> ONE_HOE = ITEMS.register("one_hoe",
            () -> new HoeItem(ModToolTiers.ONE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.ONE, 0f, -2.5f))));
    //一把锤
    public static final DeferredItem<HammerItem> ONE_HAMMER = ITEMS.register("one_hammer",
            () -> new HammerItem(ModToolTiers.ONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ONE, 5f, -2.5f))));

    //(第一节创建物品)以下这三行与StudyMod中接入总线相呼应
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
