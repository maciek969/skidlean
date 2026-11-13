/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RawProjectionMatrix;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={GameRenderer.class})
public interface GameRendererAccessor {
    @Invoker(value="method_3172")
    public void iRenderHand(float var1, boolean var2, Matrix4f var3);

    @Invoker(value="method_3196")
    public float iGetFov(Camera var1, float var2, boolean var3);

    @Invoker(value="method_3198")
    public void itiltViewWhenHurt(MatrixStack var1, float var2);

    @Invoker(value="method_3186")
    public void ibobView(MatrixStack var1, float var2);

    @Accessor(value="field_60105")
    public RawProjectionMatrix getLevelProjectionMatrixBuffer();

    @Accessor(value="field_61732")
    public WorldRenderState getLevelRenderState();
}

