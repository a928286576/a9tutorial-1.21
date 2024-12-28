package com.a928286576.studymod.datagen;

import com.a928286576.studymod.block.ModBlocks;
import com.a928286576.studymod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // 掉落自身的方块
        dropSelf(ModBlocks.ONE_BLOCK.get());




        dropSelf(ModBlocks.ONE_STAIRS.get());
        add(ModBlocks.ONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ONE_SLAB.get()));
        dropSelf(ModBlocks.ONE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ONE_BUTTON.get());

        dropSelf(ModBlocks.ONE_FENCE.get());
        dropSelf(ModBlocks.ONE_FENCE_GATE.get());
        dropSelf(ModBlocks.ONE_TRAPDOOR.get());
        dropSelf(ModBlocks.ONE_WALL.get());

        add(ModBlocks.ONE_DOOR.get(),
                block -> createDoorTable(ModBlocks.ONE_DOOR.get()));

        dropSelf(ModBlocks.LAMP_BLOCK.get());


        add(ModBlocks.MAGIC_BLOCK.get(),
                // 原版方法createOreDrop,可以配置掉落对应物品
                block -> createOreDrop(ModBlocks.MAGIC_BLOCK.get(), Items.NETHER_STAR));
        add(ModBlocks.ONE_ORE.get(),
                // 自定义方法createMultipOreDrops,可以配置掉落对应物品和数量
                block -> createMultipOreDrops(ModBlocks.ONE_ORE.get(), ModItems.ONE.get(), 2.0f, 5.0f));
    }

    protected LootTable.Builder createMultipOreDrops(Block pBlock, Item item, float mindrops, float maxdrops){
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(mindrops, maxdrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // 获取ModBlocks.BLOCKS中的所有Block并返回一个可迭代的 Block 对象集合
        // .stream()：将这些条目转换为一个流
        // .map(Holder::value)：将流中的Holder对象转换为实际的Block对象,是一种简化写法
        // ::iterator：在执行完映射操作后，使用双冒号运算符将流转换为一个迭代器,这使得该方法返回的类型为 Iterable<Block>，可以被其他代码轻松迭代
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
