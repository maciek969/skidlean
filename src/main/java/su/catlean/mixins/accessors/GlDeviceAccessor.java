/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package su.catlean.mixins.accessors;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gl.CompiledShaderPipeline;
import net.minecraft.client.gl.GlBackend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={GlBackend.class})
public interface GlDeviceAccessor {
    @Invoker(value="method_68381")
    public CompiledShaderPipeline compilePipelineCachedCatlean(RenderPipeline var1);
}

