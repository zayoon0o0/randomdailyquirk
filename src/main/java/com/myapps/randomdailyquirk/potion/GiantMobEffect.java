package com.myapps.randomdailyquirk.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;

public class GiantMobEffect extends MobEffect {
    public GiantMobEffect() {
        super(MobEffectCategory.NEUTRAL, -1);
        this.addAttributeModifier(Attributes.SCALE, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, "effect.huge_0"), 0.43, AttributeModifier.Operation.ADD_VALUE);
    }
}