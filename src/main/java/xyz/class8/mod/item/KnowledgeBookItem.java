package xyz.class8.mod.item;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class KnowledgeBookItem extends Item {


    public KnowledgeBookItem(Properties properties) {
        super(properties);
    }



    @Override
    public InteractionResult use(
            Level level,
            Player player,
            InteractionHand hand
    ) {


        ItemStack stack = player.getItemInHand(hand);


        if (!level.isClientSide()) {


            // 给玩家一本鳝的馈赠
            player.addItem(
                    new ItemStack(
                            ModItems.SHANS_GIFT
                    )
            );


            // 非创造模式消耗
            if (!player.getAbilities().instabuild) {

                stack.shrink(1);

            }

        }


        return InteractionResult.SUCCESS;

    }

}