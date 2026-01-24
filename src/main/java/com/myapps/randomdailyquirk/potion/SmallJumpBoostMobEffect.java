package com.myapps.randomdailyquirk.potion;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;


public class SmallJumpBoostMobEffect extends MobEffect {
    public SmallJumpBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x98D982);
        this.addAttributeModifier(Attributes.JUMP_STRENGTH, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, "effect.small_jump_boost-_0"), 0.1f, AttributeModifier.Operation.ADD_VALUE);

    }
}
