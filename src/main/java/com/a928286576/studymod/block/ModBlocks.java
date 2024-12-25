package com.a928286576.studymod.block;

import com.a928286576.studymod.StudyMod;
import com.a928286576.studymod.block.custom.MagicBlock;
import com.a928286576.studymod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//(第二节 添加方块)
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(StudyMod.MOD_ID);

    //第二节 在此定义了一个名为one_block的方块,使其硬度为4,需要工具采集,紫水晶音效
    public static final DeferredBlock<Block> ONE_BLOCK = registerBlock("one_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<StairBlock> ONE_STAIRS = registerBlock("one_stairs",
            () -> new StairBlock(ModBlocks.ONE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<SlabBlock> ONE_SLAB = registerBlock("one_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<PressurePlateBlock> ONE_PRESSURE_PLATE = registerBlock("one_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<ButtonBlock> ONE_BUTTON = registerBlock("one_button",
            () -> new ButtonBlock(BlockSetType.IRON,20, BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST).noCollission()));
    public static final DeferredBlock<FenceBlock> ONE_FENCE = registerBlock("one_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<FenceGateBlock> ONE_FENCE_GATE = registerBlock("one_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<WallBlock> ONE_WALL = registerBlock("one_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<DoorBlock> ONE_DOOR = registerBlock("one_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> ONE_TRAPDOOR = registerBlock("one_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST).noOcclusion()));

    public static final DeferredBlock<Block> ONE_ORE = registerBlock("one_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4),
                    BlockBehaviour.Properties.of()
                            .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //(创建功能方块)注册一个魔法方块,硬度为2,需要工具采集
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops()));

    //第二节 这个用法应该是将name和对应的方块定义收集,同时传入道 方块物品 和方块的注册中
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //第二节 这个用法有关 方块物品 的注册
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    //(第二节 添加方块 和item一样的接入总线,应该同样在主文件有对应)
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
