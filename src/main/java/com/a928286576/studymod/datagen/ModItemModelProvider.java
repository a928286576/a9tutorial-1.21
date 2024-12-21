package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
    }
}
