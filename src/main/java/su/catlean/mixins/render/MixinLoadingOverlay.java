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

import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.resource.ResourceReload;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SplashOverlay.class})
public abstract class MixinLoadingOverlay {
    @Final
    @Shadow
    private boolean reloading;
    @Shadow
    private float progress;
    @Shadow
    private long reloadCompleteTime = -1L;
    @Shadow
    private long reloadStartTime = -1L;
    @Final
    @Shadow
    private ResourceReload reload;
    @Final
    @Shadow
    private Consumer<Optional<Throwable>> exceptionHandler;

    @Inject(method={"method_25394"}, at={@At(value="HEAD")}, cancellable=true)
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
    }
}

