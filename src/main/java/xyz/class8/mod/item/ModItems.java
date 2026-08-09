package xyz.class8.mod.item;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import xyz.class8.mod.Class8Mod;


public class ModItems {


    public static final Item SHANS_GIFT =
            registerItem(
                    "shans_gift",
                    new ShanGiftItem(
                            new Item.Properties()
                                    .setId(
                                            itemKey("shans_gift")
                                    )
                    )
            );

    public static final Item KNOWLEDGE_BOOK =
            registerItem(
                    "knowledge_book",
                    new KnowledgeBookItem(
                            new Item.Properties()
                                    .setId(itemKey("knowledge_book"))
                    )
            );



    private static ResourceKey<Item> itemKey(String name) {

        return ResourceKey.create(
                BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(
                        Class8Mod.MOD_ID,
                        name
                )
        );

    }



    private static Item registerItem(
            String name,
            Item item
    ){

        return Registry.register(
                BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(
                        Class8Mod.MOD_ID,
                        name
                ),
                item
        );

    }



    public static void registerItems(){

        Class8Mod.LOGGER.info(
                "正在注册物品..."
        );

    }

}