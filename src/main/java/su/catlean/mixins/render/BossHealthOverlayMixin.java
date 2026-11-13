/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.render;

import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.render.BossBarRenderEvent;

@Mixin(value={BossBarHud.class})
public class BossHealthOverlayMixin {
    @Final
    @Shadow
    Map<UUID, ClientBossBar> bossBars;

    @Inject(method={"method_1796"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderHook(DrawContext context, CallbackInfo ci) {
        if (!this.bossBars.isEmpty() && BossBarRenderEvent.INSTANCE.call()) {
            ci.cancel();
        }
    }
}

