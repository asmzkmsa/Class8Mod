package xyz.class8.mod.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementTab.class)
public abstract class AdvancementTabMixin {
    private static final Identifier CLASS8_BACKGROUND = Identifier.fromNamespaceAndPath(
            "class8mod", "textures/gui/advancements/backgrounds/class8.png"
    );

    private static final int BACKGROUND_WIDTH = 1024;
    private static final int BACKGROUND_HEIGHT = 576;

    @Shadow private AdvancementNode rootNode;
    @Shadow private double scrollX;
    @Shadow private double scrollY;

    /**
     * The contents pose has already been translated and clipped at this point.
     * Draw the image in advancement-canvas coordinates, so it moves together
     * with the nodes when the player drags the tab.
     */
    @Inject(
            method = "extractContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/advancements/DisplayInfo;getBackground()Ljava/util/Optional;"
            )
    )
    private void class8$drawLargeBackground(
            GuiGraphicsExtractor graphics, int x, int y, CallbackInfo ci
    ) {
        if (!rootNode.holder().id().getNamespace().equals("class8mod")) {
            return;
        }

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                CLASS8_BACKGROUND,
                -(int) Math.floor(scrollX),
                -(int) Math.floor(scrollY),
                0.0F,
                0.0F,
                BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT,
                BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT
        );
    }

    /**
     * Vanilla calls this blit only for its 16x16 tiled advancement background.
     * Skipping that call retains the rest of extractContents: links, nodes,
     * hover state, clipping, and scrolling.
     */
    @Redirect(
            method = "extractContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"
            )
    )
    private void class8$skipVanillaTiledBackground(
            GuiGraphicsExtractor graphics,
            RenderPipeline pipeline,
            Identifier texture,
            int x,
            int y,
            float u,
            float v,
            int width,
            int height,
            int textureWidth,
            int textureHeight
    ) {
        if (!rootNode.holder().id().getNamespace().equals("class8mod")) {
            graphics.blit(pipeline, texture, x, y, u, v, width, height, textureWidth, textureHeight);
        }
    }
}
