package com.myapps.randomdailyquirk.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.resources.Identifier;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;

public class MiniMeMobEffect extends InstantenousMobEffect {
	public MiniMeMobEffect() {
		super(MobEffectCategory.NEUTRAL, -1);
		this.addAttributeModifier(Attributes.SCALE, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, "effect.mini_me_0"), -0.5, AttributeModifier.Operation.ADD_VALUE);
	}
}