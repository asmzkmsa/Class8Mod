package xyz.class8.mod.item;


import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.item.TooltipContext;
import net.minecraft.world.level.Level;

import java.util.List;


public class ShanGiftItem extends Item {


    public ShanGiftItem(Properties properties) {

        super(properties.food(
                new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationModifier(0f)
                        .alwaysEdible()
                        .build()
        ));

    }


    @Override
    public ItemStack finishUsingItem(
            ItemStack stack,
            Level level,
            LivingEntity user
    ) {


        if (!level.isClientSide()) {

            addAllPositiveEffects(user);

        }


        return super.finishUsingItem(stack, level, user);

    }



    private void addAllPositiveEffects(LivingEntity entity) {


        int duration = 1200;


        entity.addEffect(new MobEffectInstance(
                MobEffects.SPEED,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.HASTE,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.STRENGTH,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.JUMP_BOOST,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.RESISTANCE,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.FIRE_RESISTANCE,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.WATER_BREATHING,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.NIGHT_VISION,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.REGENERATION,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.HEALTH_BOOST,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.ABSORPTION,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.SATURATION,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.GLOWING,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.CONDUIT_POWER,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.DOLPHINS_GRACE,
                duration,
                1,
                false,
                false,
                true
        ));


        entity.addEffect(new MobEffectInstance(
                MobEffects.LUCK,
                duration,
                1,
                false,
                false,
                true
        ));

    }



//    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltip,
            TooltipFlag flag
    ) {


        tooltip.add(
                Component.translatable(
                        "item.class8mod.shans_gift_tooltip"
                )
        );


    }


}