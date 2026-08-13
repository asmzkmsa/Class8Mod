package xyz.class8.mod.item;

import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/** 与鳝的合照：放在副手时由服务器端逻辑提供无限时长的生命恢复 II。 */
public class ShanPhotoItem extends Item {
    public ShanPhotoItem(Properties properties) {
        super(properties);
    }

    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        tooltip.add(Component.translatable(
                "item.class8mod.shan_photo.tooltip"
        ));
    }

}
