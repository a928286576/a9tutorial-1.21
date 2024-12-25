package com.a928286576.studymod.item;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//(第三节创造物品标签)
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StudyMod.MOD_ID);

    //(第三节创造物品标签)创建了id为first_items_tab的创造标签页,指明了图标的地址,为标题创建了地址,将前几节加入的物品放入其中
    public static final Supplier<CreativeModeTab> FIRST_ITEMS_TAB = CREATIVE_MODE_TAB.register("first_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ONE.get()))
                    .title(Component.translatable("creativetab.studymod.first_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ONE);
                        output.accept(ModItems.TWO);
                        output.accept(ModItems.THREE);
                        output.accept(ModItems.FOUR);
                        output.accept(ModItems.FIVE);

                    }).build());
    //(第三节创造物品标签)和上面这个相同,区别是另外定义了跟在哪个标签后
    public static final Supplier<CreativeModeTab> FIRST_BLOCKS_TAB = CREATIVE_MODE_TAB.register("first_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ONE_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(StudyMod.MOD_ID, "first_items_tab"))
                    .title(Component.translatable("creativetab.studymod.first_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ONE_BLOCK);
                        output.accept(ModBlocks.ONE_ORE);

                        //(特殊方块)纳入创造标签页
                        output.accept(ModBlocks.MAGIC_BLOCK);

                        output.accept(ModBlocks.ONE_STAIRS);
                        output.accept(ModBlocks.ONE_SLAB);

                        output.accept(ModBlocks.ONE_FENCE);
                        output.accept(ModBlocks.ONE_FENCE_GATE);
                        output.accept(ModBlocks.ONE_WALL);

                        output.accept(ModBlocks.ONE_PRESSURE_PLATE);
                        output.accept(ModBlocks.ONE_BUTTON);

                        output.accept(ModBlocks.ONE_DOOR);
                        output.accept(ModBlocks.ONE_TRAPDOOR);

                    }).build());

    //(第三节创造物品标签)接入总线
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
