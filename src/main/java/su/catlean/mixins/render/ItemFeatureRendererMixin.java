/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package su.catlean.mixins.render;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.ItemCommandRenderer;
import net.minecraft.client.render.item.ItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import su.catlean.api.event.events.render.ItemCommandEvent;

@Mixin(value={ItemCommandRenderer.class})
public abstract class ItemFeatureRendererMixin {
    @ModifyArg(method={"method_73010"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_918;method_62476(Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;II[ILjava/util/List;Lnet/minecraft/class_1921;Lnet/minecraft/class_10444$class_10445;)V"), index=2)
    private VertexConsumerProvider renderHook(VertexConsumerProvider original) {
        if (ItemCommandEvent.INSTANCE.call()) {
            return ItemCommandEvent.INSTANCE.getProvider();
        }
        return original;
    }

    @ModifyArg(method={"method_73010"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_918;method_62476(Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;II[ILjava/util/List;Lnet/minecraft/class_1921;Lnet/minecraft/class_10444$class_10445;)V"), index=8)
    private ItemRenderState.Glint renderHook(ItemRenderState.Glint original) {
        if (ItemCommandEvent.INSTANCE.call()) {
            return ItemRenderState.Glint.NONE;
        }
        return original;
    }
}

