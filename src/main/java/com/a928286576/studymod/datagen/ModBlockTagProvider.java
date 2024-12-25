package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StudyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ONE_BLOCK.get())
                .add(ModBlocks.ONE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get())
                .add(ModBlocks.ONE_BUTTON.get())
                .add(ModBlocks.ONE_DOOR.get())
                .add(ModBlocks.ONE_TRAPDOOR.get())
                .add(ModBlocks.ONE_FENCE_GATE.get())
                .add(ModBlocks.ONE_FENCE.get())
                .add(ModBlocks.ONE_WALL.get())
                .add(ModBlocks.ONE_STAIRS.get())
                .add(ModBlocks.ONE_SLAB.get())
                .add(ModBlocks.ONE_PRESSURE_PLATE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ONE_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ONE_ORE.get())
                .add(ModBlocks.ONE_BUTTON.get())
                .add(ModBlocks.ONE_DOOR.get())
                .add(ModBlocks.ONE_TRAPDOOR.get())
                .add(ModBlocks.ONE_FENCE_GATE.get())
                .add(ModBlocks.ONE_FENCE.get())
                .add(ModBlocks.ONE_WALL.get())
                .add(ModBlocks.ONE_STAIRS.get())
                .add(ModBlocks.ONE_SLAB.get())
                .add(ModBlocks.ONE_PRESSURE_PLATE.get());;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.ONE_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.ONE_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.ONE_WALL.get());
    }
}
