/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.render;

import java.util.Collection;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.CrosshairRenderEvent;
import su.catlean.api.event.events.render.HotBarRenderEventPost;
import su.catlean.api.event.events.render.HotBarRenderEventPre;
import su.catlean.api.event.events.render.ItemNameRenderEvent;
import su.catlean.api.event.events.render.NauseaRenderEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.RenderPortalOverlayEvent;
import su.catlean.api.event.events.render.RenderVignetteOverlayEvent;
import su.catlean.api.event.events.render.ScoreBoardRenderEvent;
import su.catlean.api.event.events.render.StatusEffectsRenderEvent;

@Mixin(value={InGameHud.class})
public abstract class GuiMixin {
    @Inject(at={@At(value="HEAD")}, method={"method_1753"})
    public void renderHook(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Render2DEvent.INSTANCE.call(context);
    }

    @Inject(at={@At(value="HEAD")}, method={"method_1749"}, cancellable=true)
    public void renderHeldItemTooltipHook(DrawContext context, CallbackInfo ci) {
        if (ItemNameRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderScoreboardSidebarHook(DrawContext context, ScoreboardObjective objective, CallbackInfo ci) {
        if (ScoreBoardRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1735"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderVignetteOverlayHook(DrawContext context, Entity entity, CallbackInfo ci) {
        if (RenderVignetteOverlayEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1746"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderPortalOverlayHook(DrawContext context, float nauseaStrength, CallbackInfo ci) {
        if (RenderPortalOverlayEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1765"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderStatusEffectOverlayHook(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Collection<StatusEffectInstance> collection = MinecraftClient.getInstance().player.getStatusEffects();
        if (!collection.isEmpty() && MinecraftClient.getInstance().currentScreen == null && StatusEffectsRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_61980"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderNauseaOverlayHook(DrawContext context, float nauseaStrength, CallbackInfo ci) {
        if (NauseaRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1736"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (CrosshairRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_1759"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderHotbarHookPre(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        HotBarRenderEventPre.INSTANCE.call();
    }

    @Inject(method={"method_1759"}, at={@At(value="RETURN")}, cancellable=true)
    public void renderHotbarHookPost(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        HotBarRenderEventPost.INSTANCE.call();
    }
}

