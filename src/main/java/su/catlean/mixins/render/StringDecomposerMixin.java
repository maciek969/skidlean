/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 */
package su.catlean.mixins.render;

import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import su.catlean.api.event.events.render.StringDecomposerEvent;

@Mixin(value={TextVisitFactory.class})
public abstract class StringDecomposerMixin {
    @ModifyVariable(method={"method_27473(Ljava/lang/String;ILnet/minecraft/class_2583;Lnet/minecraft/class_2583;Lnet/minecraft/class_5224;)Z"}, at=@At(value="HEAD"), ordinal=0, argsOnly=true)
    private static String iterateFormattedHook(String original) {
        return StringDecomposerEvent.INSTANCE.call(original);
    }
}

