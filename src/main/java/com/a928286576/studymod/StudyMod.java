package com.a928286576.studymod;

import com.a928286576.studymod.block.ModBlocks;
import com.a928286576.studymod.item.ModCreativeTabs;
import com.a928286576.studymod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// 这里的值应该与META-INF/neoforge.mods.toml文件中的一个条目匹配
@Mod(StudyMod.MOD_ID)
public class StudyMod
{
    //(第零节 如何开始)模组名
    public static final String MOD_ID = "studymod";
    // 直接引用一个slf4j日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    // 模组类的构造函数是在你的模组加载时运行的第一段代码。
    // FML会识别一些参数类型，如IEventBus或ModContainer，并自动传入这些参数。
    public StudyMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // 注册commonSetup方法以进行模组加载设置
        modEventBus.addListener(this::commonSetup);

        // 注册我们以处理服务器和其他感兴趣的游戏中事件。
        // 请注意，只有当我们希望这个类（ExampleMod）直接响应事件时，这才是必要的。
        // 如果这个类中没有使用@SubscribeEvent注解的方法（如下面的onServerStarting()方法），则不要添加这一行。
        NeoForge.EVENT_BUS.register(this);
        //(第三节创造物品标签)
        ModCreativeTabs.register(modEventBus);
        //(第一节创建物品)这是呼应ModItems的内容
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        // 将物品注册到创造标签页
        modEventBus.addListener(this::addCreative);

        // 注册我们模组的ModConfigSpec，以便FML可以为我们创建和加载配置文件。
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // 一些通用的设置代码
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // 将示例方块物品添加到建筑方块标签页
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //(第一节创建物品)这里将物品one加入到了创造标签页中的原材料(ingredients)分类里
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ONE);
        }
        //(第二节创建方块)将one block加入到了"建筑方块"标签内
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.ONE_BLOCK);
        }
        //(第二节创建方块)将one ore加入到了"自然方块"标签内
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.ONE_ORE);
        }
    }

    // 你可以使用SubscribeEvent并让事件总线发现需要调用的方法。
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // 你可以使用EventBusSubscriber自动注册类中所有带有@SubscribeEvent注解的静态方法。
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
