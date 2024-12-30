package com.a928286576.studymod.item;

import com.a928286576.studymod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier ONE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ONE_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(ModItems.ONE));
}
