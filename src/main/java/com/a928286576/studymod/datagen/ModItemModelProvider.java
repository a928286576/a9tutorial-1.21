package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.block.ModBlocks;
import com.a928286576.studymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StudyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ONE.get());

        basicItem(ModItems.TWO.get());
        basicItem(ModItems.THREE.get());
        basicItem(ModItems.FOUR.get());
        basicItem(ModItems.FIVE.get());

        buttonItem(ModBlocks.ONE_BUTTON, ModBlocks.ONE_BLOCK);
        fenceItem(ModBlocks.ONE_FENCE, ModBlocks.ONE_BLOCK);
        wallItem(ModBlocks.ONE_WALL, ModBlocks.ONE_BLOCK);

        basicItem(ModBlocks.ONE_DOOR.asItem());


    }

    //按钮类型的物品模型
    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    //围栏类型的物品模型
    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    //墙壁类型的物品模型
    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                // 这里的key值与其它模型不同，需要注意
                .texture("wall", ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
