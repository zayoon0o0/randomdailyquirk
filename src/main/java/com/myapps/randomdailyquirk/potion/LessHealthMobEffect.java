package com.myapps.randomdailyquirk.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;
import com.myapps.randomdailyquirk.RandomdailyquirkMod;

public class LessHealthMobEffect extends MobEffect {
    public LessHealthMobEffect() {
        super(MobEffectCategory.NEUTRAL, -1);
        this.addAttributeModifier(Attributes.MAX_HEALTH, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, "effect.lesshealth_0"), -2, AttributeModifier.Operation.ADD_VALUE);
    }
}