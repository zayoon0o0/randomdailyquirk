/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.myapps.randomdailyquirk.init;

import com.myapps.randomdailyquirk.potion.MiniMeMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder;

import java.util.function.Supplier;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;

public class RandomdailyquirkModMobEffects {
	public static Holder<MobEffect> MINI_ME;

	public static void load() {
		MINI_ME = register("mini_me", MiniMeMobEffect::new);
	}

	private static Holder<MobEffect> register(String registryname, Supplier<MobEffect> element) {
		return Holder.direct(Registry.register(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, registryname), element.get()));
	}
}