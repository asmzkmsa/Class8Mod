package xyz.class8.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.resources.Identifier;
import xyz.class8.mod.item.ModItems;
import xyz.class8.mod.registry.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Class8Mod implements ModInitializer {
    public static final String MOD_ID = "class8mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("正在初始化 Class8Mod...");
        ModItems.registerItems();
        ModRegistries.registerAll();
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (var player : server.getPlayerList().getPlayers()) {
                boolean photoInOffhand = player.getOffhandItem().getItem() == ModItems.SHAN_PHOTO;
                if (photoInOffhand) {
                    // 与鳝的合照放在副手：无限时长生命恢复 II + 幸运 I
                    player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.REGENERATION,
                            net.minecraft.world.effect.MobEffectInstance.INFINITE_DURATION,
                            1,
                            false,
                            true,
                            true
                    ));
                    player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.LUCK,
                            net.minecraft.world.effect.MobEffectInstance.INFINITE_DURATION,
                            0,
                            false,
                            true,
                            true
                    ));
                } else {
                    var regeneration = player.getEffect(net.minecraft.world.effect.MobEffects.REGENERATION);
                    if (regeneration != null
                            && regeneration.isInfiniteDuration()
                            && regeneration.getAmplifier() == 1) {
                        player.removeEffect(net.minecraft.world.effect.MobEffects.REGENERATION);
                    }

                    var luck = player.getEffect(net.minecraft.world.effect.MobEffects.LUCK);
                    if (luck != null
                            && luck.isInfiniteDuration()
                            && luck.getAmplifier() == 0) {
                        player.removeEffect(net.minecraft.world.effect.MobEffects.LUCK);
                    }
                }
            }
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var root = server.getAdvancements().get(id("root"));
            if (root != null) {
                handler.player.getAdvancements().award(root, "start");
            }
        });
        LOGGER.info("Class8Mod 初始化完成！");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
