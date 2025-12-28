package com.myapps.randomdailyquirk.procedures;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class MiniMeEffectExpiresProcedure {
	public static boolean eventResult = true;

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(Attributes.SCALE)) {
			_livingEntity0.getAttribute(Attributes.SCALE).setBaseValue(1);
		}
	}
}