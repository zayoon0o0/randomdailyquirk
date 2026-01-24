/*
 *	Add Effects here
 */
package com.myapps.randomdailyquirk.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder;
import com.myapps.randomdailyquirk.potion.*;

import java.util.function.Supplier;

import com.myapps.randomdailyquirk.RandomdailyquirkMod;

public class RandomdailyquirkModMobEffects {
	// public static Holder<MobEffect> EFFECT_NAME
	public static Holder<MobEffect> FATUGE;
	public static Holder<MobEffect> MINI_ME;
	public static Holder<MobEffect> SPEEDTINY;
	public static Holder<MobEffect> SMALLJUMP;
    public static Holder<MobEffect> SLOWER;
	public static Holder<MobEffect> GIANT;
	public static Holder<MobEffect> LESSHEATH;

	public static void load() {
		GIANT = register("huge", GiantMobEffect::new);
		// EFFECT_NAME = register("effect_name", EffectNameMobEffect::new)
        SLOWER = register("slower", SlowerMobEffect::new);
		MINI_ME = register("mini_me", MiniMeMobEffect::new);
		SPEEDTINY = register("speed_tiny", SlowSpeedMobEffect::new);
		SMALLJUMP = register("small_jump_boost", SmallJumpBoostMobEffect::new);
		FATUGE = register("fatuge_effect", FatugeMobEffect::new);
		LESSHEATH = register("lesshealth", LessHealthMobEffect::new);

	}
	private static Holder<MobEffect> register(String registryname, Supplier<MobEffect> element) {
		return Holder.direct(Registry.register(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(RandomdailyquirkMod.MODID, registryname), element.get()));
	}
}