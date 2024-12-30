package com.a928286576.studymod.util;

import com.a928286576.studymod.StudyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

//tag的用法按个人理解,是创建一个物品的集合,以便于批量的处理标签所对应的物品
public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ONE_TOOL = createTag("needs_one_tool");
        public static final TagKey<Block> INCORRECT_FOR_ONE_TOOL = createTag("incorrect_for_one_tool");
        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID, name));
        }
    }

    public static class Items {
        //该标签的具体内容存储在data/studymod/tags/items/transformable_items.json文件中
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID, name));
        }
    }
}
