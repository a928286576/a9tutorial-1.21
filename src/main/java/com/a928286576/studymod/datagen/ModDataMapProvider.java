package com.a928286576.studymod.datagen;

import com.a928286576.studymod.item.ModItems;
import net.minecraft.advancements.critereon.BrewedPotionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    // 开始构建 NeoForge 的datamap，为一些物品赋予热值
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.FOUR.getId(), new FurnaceFuel(1200), false)
                .add(ModItems.FIVE.getId(), new FurnaceFuel(2400), false);
    }
}
