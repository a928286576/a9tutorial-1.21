package com.a928286576.studymod.component;

import com.a928286576.studymod.StudyMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

// 定义了一个名为 ModDataComponents 的类，用于注册和管理 Minecraft 中的数据组件类型
public class ModDataComponents {
    // DeferredRegister：这是 NeoForge 提供的一个工具类，用于延迟注册对象（如数据组件类型）
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            // Registries.DATA_COMPONENT_TYPE：这是 Minecraft 中数据组件类型的注册表键（ResourceKey），用于标识数据组件类型的注册表
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE,StudyMod.MOD_ID);

    // DeferredHolder：这是 DeferredRegister 返回的一个持有者对象，用于持有注册的数据组件类型
    // register 方法：调用自定义的 register 方法，注册一个名为 "coordinates" 的数据组件类型
    // 注册一个名为 "coordinates" 的数据组件类型，该组件类型用于存储 BlockPos 数据
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
            builder -> builder.persistent(BlockPos.CODEC));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator){
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus){
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
