package com.myapps.randomdailyquirk.potion;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.resources.Identifier;


public class SlowSpeedMobEffect extends InstantenousMobEffect {
  public SlowSpeedMobEffect() {
      super(MobEffectCategory.BENEFICIAL, 0x98D982);
      this.addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, "effect.slow_speed_0"), 0.1f, AttributeModifier.Operation.ADD_VALUE);

  }
}
