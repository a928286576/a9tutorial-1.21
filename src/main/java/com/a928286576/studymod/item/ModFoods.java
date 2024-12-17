package com.a928286576.studymod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //这是一份食物的配置文件，里面包含了一些食物的属性，例如营养成分、饮用时间、效果等。
    //3饱食度,0.25饱和度,效果是提升生命值，持续时间400红石刻，35%几率触发。
    public static final FoodProperties THREE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f)
            .build();
}
