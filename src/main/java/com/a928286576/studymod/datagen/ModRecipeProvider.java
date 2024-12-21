package com.a928286576.studymod.datagen;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.block.ModBlocks;
import com.a928286576.studymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        List<ItemLike> ONE_SMELTABLES = List.of(ModBlocks.ONE_ORE);

        //有序合成
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ONE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ONE.get())
                .unlockedBy("has_one", has(ModItems.ONE)).save(recipeOutput);

        //无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ONE.get(), 9)
                .requires(ModBlocks.ONE_BLOCK.get())
                .unlockedBy("has_one_block", has(ModBlocks.ONE_BLOCK)).save(recipeOutput);

        //重命名输出的json文件
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ONE.get(), 18)
                .requires(ModBlocks.ONE_BLOCK.get())
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "studymod:one_from_magic_block");

        oreSmelting(recipeOutput, ONE_SMELTABLES, RecipeCategory.MISC, ModItems.ONE.get(),
                0.25f, 200, "bismuth");
        oreBlasting(recipeOutput, ONE_SMELTABLES, RecipeCategory.MISC, ModItems.ONE.get(),
                0.25f, 100, "bismuth");

    }

    // 定义一个静态方法 oreSmelting，用于创建熔炼配方
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                      ItemLike pResult, float pXP, int pCookTime, String pGroup){
        // 调用 oreCooking 方法来生成熔炼配方
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pXP, pCookTime, pGroup,"_from_smelting");
    }
    //同上,只是更快的冶炼炉
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                      ItemLike pResult, float pXP, int pCookTime, String pGroup){
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pXP, pCookTime, pGroup,"_from_blasting");
    }

    //// 定义一个泛型静态方法 oreCooking，用于创建熔炼配方
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput,
                                                                       RecipeSerializer<T> pCookingSerializer,
                                                                       AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory,
                                                                       ItemLike pResult, float pXP, int pCookTime, String pGroup,
                                                                       String pRecipeName){
        // 遍历输入材料列表 pIngredients
        for (ItemLike itemLike : pIngredients){
            //// 使用 SimpleCookingRecipeBuilder 创建通用的熔炉类配方
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemLike), pCategory, pResult, pXP, pCookTime, pCookingSerializer, factory)
                    // 设置配方的分组、解锁条件并保存配方
                    .group(pGroup).unlockedBy(getHasName(itemLike), has(itemLike))
                    // 保存配方到 recipeOutput，使用组合的字符串作为配方的名称
                    .save(recipeOutput, StudyMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemLike));
        }
    }
}
