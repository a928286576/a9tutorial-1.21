package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.item.ModItems;
import com.a928286576.studymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, StudyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ONE.get())
                .add(Items.STICK);

        tag(ItemTags.SWORDS)
                .add(ModItems.ONE_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.ONE_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.ONE_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(ModItems.ONE_AXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.ONE_HOE.get());

    }
}
