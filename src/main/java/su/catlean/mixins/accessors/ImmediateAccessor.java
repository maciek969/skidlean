/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package su.catlean.mixins.accessors;

import java.util.Map;
import java.util.SequencedMap;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={VertexConsumerProvider.Immediate.class})
public interface ImmediateAccessor {
    @Accessor(value="field_20953")
    public SequencedMap<RenderLayer, BufferAllocator> getLayers();

    @Accessor(value="field_52158")
    public RenderLayer getLastSharedType();

    @Accessor(value="field_52158")
    public void setLastSharedType(RenderLayer var1);

    @Accessor(value="field_52156")
    public BufferAllocator getSharedBuffer();

    @Accessor(value="field_52157")
    public Map<RenderLayer, BufferBuilder> getStartedBuilders();
}

