package com.myapps.randomdailyquirk.potion;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.resources.Identifier;


public class FatugeMobEffect extends MobEffect {
    public FatugeMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x98D982);
        this.addAttributeModifier(Attributes.MINING_EFFICIENCY, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, "effect.fatuge_effect_0"), -0.1f, AttributeModifier.Operation.ADD_VALUE);

    }
}