package xyz.class8.mod;

import net.fabricmc.api.ModInitializer;
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
        LOGGER.info("Class8Mod 初始化完成！");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}