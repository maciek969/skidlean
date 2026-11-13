/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package su.catlean.mixins.render;

import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import su.catlean.api.event.events.network.PlayerListSizeEvent;

@Mixin(value={PlayerListHud.class})
public class PlayerTabOverlayMixin {
    @ModifyArgs(method={"method_48213"}, at=@At(value="INVOKE", target="Ljava/util/stream/Stream;limit(J)Ljava/util/stream/Stream;"))
    private void collectPlayerEntriesHook(Args args) {
        if (PlayerListSizeEvent.INSTANCE.call()) {
            args.set(0, (Object)1000L);
        }
    }
}

