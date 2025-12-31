package com.myapps.randomdailyquirk;

import com.myapps.randomdailyquirk.init.RandomdailyquirkModMobEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RandomdailyquirkMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger(RandomdailyquirkMod.class);
	public static final String MODID = "randomdailyquirk";
	private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();
	private static Object minecraft;
	private static MethodHandle playerHandle;
	private long lastDay = -1;
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing RandomdailyquirkMod");
		LOGGER.info("Hope you get a good quirk today!");
		RandomdailyquirkModMobEffects.load();
		tick();

		EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> {
			if (entity instanceof ServerPlayer player) {
				player.removeAllEffects();
				List<Holder<MobEffect>> effects = Arrays.asList(
						RandomdailyquirkModMobEffects.MINI_ME,
						RandomdailyquirkModMobEffects.SPEEDTINY,
						RandomdailyquirkModMobEffects.SMALLJUMP
				);
				Holder<MobEffect> randomEffect = effects.get(new Random().nextInt(effects.size()));
				player.addEffect(new MobEffectInstance(randomEffect, 2400, 0, false, false, false));
			}
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerLevel level : server.getAllLevels()) {
				long currentDay = level.getDayTime() / 24000;
				if (currentDay != lastDay) {
					lastDay = currentDay;
					for (ServerPlayer player : level.players()) {
						List<Holder<MobEffect>> effects = Arrays.asList(
								RandomdailyquirkModMobEffects.MINI_ME
						);
						Holder<MobEffect> randomEffect = effects.get(new Random().nextInt(effects.size()));
						player.addEffect(new MobEffectInstance(randomEffect, 2400, 0, false, false, false));
					}
				}
			}
		});
	}

	public static void queueServerWork(int tick, Runnable action) {
		workQueue.add(new Tuple<>(action, tick));
	}

	private void tick() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setB(work.getB() - 1);
				if (work.getB() == 0) {
					actions.add(work);
				}
			});
			actions.forEach(e -> e.getA().run());
			workQueue.removeAll(actions);
		});
	}

	@Nullable
	public static Player clientPlayer() {
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			try {
				if (minecraft == null || playerHandle == null) {
					Class<?> minecraftClass = Class.forName("net.minecraft.client.Minecraft");
					minecraft = MethodHandles.publicLookup()
							.findStatic(minecraftClass, "getInstance", MethodType.methodType(minecraftClass))
							.invoke();
					playerHandle = MethodHandles.publicLookup()
							.findGetter(minecraftClass, "player",
									Class.forName("net.minecraft.client.player.LocalPlayer"));
				}
				return (Player) playerHandle.invoke(minecraft);
			} catch (Throwable e) {
				LOGGER.error("Failed to get client player", e);
				return null;
			}
		}
		return null;
	}
}
