package xyz.class8.mod.item;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import net.minecraft.world.level.Level;
import xyz.class8.mod.Class8Mod;

/**
 * 鳝的拍立得：需要背包中有鳝的馈赠，每次成功使用消耗一份馈赠和一点耐久，
 * 并获得一张“与鳝的合照”。
 */
public class ShanCameraItem extends Item {
    public ShanCameraItem(Properties properties) {
        super(properties.durability(42));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack camera = player.getItemInHand(hand);

        // 仅在服务端执行实际物品变化，避免客户端与服务端重复扣除。
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (!consumeShansGift(player)) {
            return InteractionResult.FAIL;
        }

        ItemStack photo = new ItemStack(ModItems.SHAN_PHOTO);
        if (!player.addItem(photo)) {
            player.drop(photo, false);
        }
        camera.hurtAndBreak(1, player, hand);
        MinecraftServer server = level.getServer();
        if (server != null) {
            awardAdvancement(server, (ServerPlayer) player, "smile");
        }

        return InteractionResult.SUCCESS;
    }

    private static boolean consumeShansGift(Player player) {
        // 玩家背包（主物品栏、盔甲栏、副手）中的任意一份鳝的馈赠都可以用于拍照。
        for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (stack.getItem() == ModItems.SHANS_GIFT) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                return true;
            }
        }

        if (player.getOffhandItem().getItem() == ModItems.SHANS_GIFT) {
            if (!player.getAbilities().instabuild) {
                player.getOffhandItem().shrink(1);
            }
            return true;
        }

        return false;
    }

    private static void awardAdvancement(MinecraftServer server, ServerPlayer player, String advancementPath) {
        var advancement = server.getAdvancements().get(Class8Mod.id(advancementPath));
        if (advancement != null) {
            player.getAdvancements().award(advancement, "trigger");
        }
    }


    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        tooltip.add(Component.translatable(
                "item.class8mod.shan_camera.tooltip"
        ));
    }

}
