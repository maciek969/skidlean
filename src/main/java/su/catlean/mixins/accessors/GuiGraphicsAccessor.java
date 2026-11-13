/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={DrawContext.class})
public interface GuiGraphicsAccessor {
    @Accessor(value="field_59826")
    public GuiRenderState getGuiRenderState();

    @Accessor(value="field_44659")
    public DrawContext.ScissorStack getScissorStack();
}

