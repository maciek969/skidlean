/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.gl.CompiledShaderPipeline;
import net.minecraft.client.gl.GlCommandEncoder;
import net.minecraft.client.gl.RenderPassImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={RenderPassImpl.class})
public interface RenderPassAccessor {
    @Accessor(value="field_57868")
    public void setPipeline(CompiledShaderPipeline var1);

    @Accessor(value="field_57877")
    public GlCommandEncoder getEncoder();
}

