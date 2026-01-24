package com.myapps.randomdailyquirk;

import com.myapps.randomdailyquirk.init.RandomdailyquirkModMobEffects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RandomdailyquirkMod implements ModInitializer {
	public static final String MODID = "randomdailyquirk";
	private static final Map<UUID, Holder<MobEffect>> playerQuirks = new ConcurrentHashMap<>();
	private long lastDay = -1;

	@Override
	public void onInitialize() {
		RandomdailyquirkModMobEffects.load();
		EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> {
			if (entity instanceof ServerPlayer player) {
				applyNewQuirk(player);
			}
		});
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerLevel level : server.getAllLevels()) {
				long currentDay = level.getDayTime() / 24000;
				if (currentDay != lastDay) {
					lastDay = currentDay;
					for (ServerPlayer player : level.players()) {
						applyNewQuirk(player);
					}
				}
			}
		});
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			Holder<MobEffect> quirk = playerQuirks.get(newPlayer.getUUID());
			if (quirk != null) {
				applyQuirkToPlayer(newPlayer, quirk);
			}
		});
	}
	private void applyNewQuirk(ServerPlayer player) {
		List<Holder<MobEffect>> effects = Arrays.asList(
				RandomdailyquirkModMobEffects.MINI_ME,
				RandomdailyquirkModMobEffects.SPEEDTINY,
				RandomdailyquirkModMobEffects.SMALLJUMP,
				RandomdailyquirkModMobEffects.SLOWER,
				MobEffects.NAUSEA,
				RandomdailyquirkModMobEffects.GIANT,
				MobEffects.HEALTH_BOOST,
				RandomdailyquirkModMobEffects.LESSHEATH
		);
		Holder<MobEffect> randomEffect = effects.get(new Random().nextInt(effects.size()));
		playerQuirks.put(player.getUUID(), randomEffect);
		applyQuirkToPlayer(player, randomEffect);
	}

	private void applyQuirkToPlayer(ServerPlayer player, Holder<MobEffect> effect) {
		player.removeAllEffects();
		player.addEffect(new MobEffectInstance(effect, 24000, 0, false, false, false));
		if (effect.equals(MobEffects.HEALTH_BOOST)) {
			player.setHealth(player.getMaxHealth());
		}
		if (effect.equals(RandomdailyquirkModMobEffects.LESSHEATH)) {
			player.setHealth(player.getMaxHealth());
		}
	}
}
