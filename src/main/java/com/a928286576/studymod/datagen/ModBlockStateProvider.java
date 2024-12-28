package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.block.ModBlocks;
import com.a928286576.studymod.block.custom.LampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, StudyMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MAGIC_BLOCK);

        blockWithItem(ModBlocks.ONE_BLOCK);

        blockWithItem(ModBlocks.ONE_ORE);

        stairsBlock(ModBlocks.ONE_STAIRS.get(), blockTexture(ModBlocks.ONE_BLOCK.get()));
        slabBlock(ModBlocks.ONE_SLAB.get(), blockTexture(ModBlocks.ONE_BLOCK.get()), blockTexture(ModBlocks.ONE_BLOCK.get()));

        buttonBlock(ModBlocks.ONE_BUTTON.get(), blockTexture(ModBlocks.ONE_BLOCK.get()));
        pressurePlateBlock(ModBlocks.ONE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ONE_BLOCK.get()));

        fenceBlock(ModBlocks.ONE_FENCE.get(), blockTexture(ModBlocks.ONE_BLOCK.get()));
        fenceGateBlock(ModBlocks.ONE_FENCE_GATE.get(), blockTexture(ModBlocks.ONE_BLOCK.get()));
        wallBlock(ModBlocks.ONE_WALL.get(), blockTexture(ModBlocks.ONE_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.ONE_DOOR.get(), modLoc("block/one_door_bottom"), modLoc("block/one_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ONE_TRAPDOOR.get(), modLoc("block/one_trapdoor"), true, "cutout");

        blockItem(ModBlocks.ONE_STAIRS);
        blockItem(ModBlocks.ONE_SLAB);
        blockItem(ModBlocks.ONE_PRESSURE_PLATE);
        blockItem(ModBlocks.ONE_FENCE_GATE);
        blockItem(ModBlocks.ONE_TRAPDOOR, "_bottom");

        customLamp();
    }

    private void customLamp(){
        getVariantBuilder(ModBlocks.LAMP_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(LampBlock.CLICKED)){
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("lamp_on",
                        ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID, "block/" + "lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("lamp_off",
                        ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID, "block/" + "lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.LAMP_BLOCK.get(), models().cubeAll("lamp_on",
                ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID, "block/" + "lamp_on")));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("studymod:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("studymod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
