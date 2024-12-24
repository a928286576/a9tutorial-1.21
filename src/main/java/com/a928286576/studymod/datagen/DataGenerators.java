package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//@EventBusSubscriber(...)：这是一个注解，用于注册该类为事件总线的订阅者。
// modid 指定模块的 ID，bus 指定事件总线类型。在这里，它表示该类将会监听与模块相关的事件。
@EventBusSubscriber(modid = StudyMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators{
    //表明下面的方法 gatherData 是一个事件处理方法，会在特定事件发生时被调用。通常这是用来注册一个事件监听器
    @SubscribeEvent
    //定义了一个静态方法 gatherData，它接受一个参数 event，类型为 GatherDataEvent。这个方法的目的在于处理数据收集事件，通常用于游戏或应用的数据生成。
    public static void gatherData(GatherDataEvent event){
        //获取数据生成器
        DataGenerator generator = event.getGenerator();
        //获取数据包的输出目录
        PackOutput packOutput = generator.getPackOutput();
        //该实例通常用于帮助检查和处理现有文件，以避免重复生成
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        //获取一个 CompletableFuture，它将在数据收集完成时完成。用于查找资源位置
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //添加一个 LootTableProvider，它将生成一个包含所有块的 loot_table.json 文件。
        //event.includeServer() 方法用于检查是否包含服务器数据
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                //整个目的是配置一个物品掉落表的生成逻辑
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        //ModRecipeProvider 生成配方的json文件
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        //BlockTagsProvider，生成block的tag有关json文件
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        //ModItemTagProvider 用于生成item的tag有关json文件
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        //ModDataMapProviders 用于生成datamap的json文件
        generator.addProvider(event.includeServer(), new ModDataMapProvider(packOutput, lookupProvider));

        //ModItemModelProvider 用于生成物品模型json文件
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        //ModBlockStateProvider 用于生成方块状态json文件
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
    }

}
