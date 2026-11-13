/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Math
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 *  org.joml.Vector4d
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.opengl.GL32C
 */
package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.joml.Vector4d;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL32C;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.FrustrumEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.module.esp.LogoutSpots;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BoxFace;
import su.catlean.render.Pipelines;
import su.catlean.render.QueuedBox;
import su.catlean.render.QueuedLine;
import su.catlean.render.RenderQueue;
import su.catlean.render.RenderUtil;
import su.catlean.render.TexturedPolygonConsumer;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class RenderState {
    @NotNull
    public static final RenderState INSTANCE;
    @NotNull
    private static Matrix4f projection;
    @NotNull
    private static Frustum frustum;
    private static float cameraYaw;
    private static float cameraYawBack;
    private static float cameraPitch;
    private static int N;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private RenderState() {
    }

    @NotNull
    public final Matrix4f getProjection() {
        return projection;
    }

    public final void setProjection(@NotNull Matrix4f matrix4f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(matrix4f, (String)((Object)RenderState.zkmBootstrap("i", (int)30126, (long)(0xA5E92803B47D127L ^ l))));
        projection = matrix4f;
    }

    @NotNull
    public final Frustum getFrustum() {
        return frustum;
    }

    public final void setFrustum(@NotNull Frustum frustum) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(frustum, (String)((Object)RenderState.zkmBootstrap("i", (int)18458, (long)(0x55AA6AF9FC842813L ^ l))));
        RenderState.frustum = frustum;
    }

    public final float getCameraYaw2() {
        return cameraYaw;
    }

    public final void setCameraYaw2(float f) {
        cameraYaw = f;
    }

    public final float getCameraYaw() {
        return cameraYawBack;
    }

    public final void setCameraYaw(float f) {
        cameraYawBack = f;
    }

    public final float getCameraPitch() {
        return cameraPitch;
    }

    public final void setCameraPitch(float f) {
        cameraPitch = f;
    }

    @Flow
    private final void onFrustrum(FrustrumEvent frustrumEvent) {
        frustum = frustrumEvent.getFrustrum();
    }

    @Flow(priority=20)
    private final void onRender3D(Render3DEvent render3DEvent) {
        block0: {
            Camera camera = Globals.getMc().gameRenderer.getCamera();
            Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
            Camera camera2 = camera;
            cameraYaw = Math.toRadians((float)camera2.getYaw());
            cameraYawBack = Math.toRadians((float)(camera2.getYaw() + 180.0f));
            cameraPitch = Math.toRadians((float)camera2.getPitch());
            GofraState.INSTANCE.setModifyBuffer(false);
            RenderUtil.INSTANCE.b().set((Matrix4fc)RenderSystem.getModelViewMatrix());
            RenderUtil.INSTANCE.m().set((Matrix4fc)render3DEvent.getStack().peek().getPositionMatrix());
            GL32C.glGetIntegerv((int)2978, (int[])RenderUtil.INSTANCE.u());
            projection = render3DEvent.getProjection();
            Theme.INSTANCE.update();
            if (HudManager.INSTANCE.getSelectedElement() != null || !ClickGuiScreen.INSTANCE.V() || GLFW.glfwGetPlatform() == 393219) break block0;
            GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)221185));
        }
    }

    public final void drawWorldText(int n, byte by, @NotNull String string, @NotNull Vec3d vec3d, @NotNull Color color, float f, int n2, float f2, boolean bl, @NotNull FontRenderer fontRenderer) {
        long l = ((long)n << 32 | (long)by << 56 >>> 32 | (long)n2 << 40 >>> 40) ^ a;
        long l2 = l ^ 0x7CF835A10AFFL;
        Intrinsics.checkNotNullParameter(string, "text");
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(fontRenderer, "font");
        Vector4d vector4d = RenderUtil.INSTANCE.worldToScreen(vec3d);
        if (vector4d != null) {
            Matrix3x2fStack matrix3x2fStack = new Matrix3x2fStack(8);
            matrix3x2fStack.translate((float)vector4d.x, (float)vector4d.y);
            Entity entity = Globals.getMc().getCameraEntity();
            Intrinsics.checkNotNull(entity);
            Entity entity2 = entity;
            matrix3x2fStack.scale(1.0f - ((Number)RangesKt.coerceIn((Comparable)Float.valueOf((float)LogoutSpots.INSTANCE.getPos(entity2).distanceTo(vec3d)), RangesKt.rangeTo(1.0f, 10.0f))).floatValue() / 10.0f);
            if (bl) {
                Color color2 = Color.BLACK;
                Intrinsics.checkNotNullExpressionValue(color2, "BLACK");
                float f3 = RenderUtil.INSTANCE.getAlpha(color);
                fontRenderer.drawStringCentered(matrix3x2fStack, string, f + 1.0f, f2 + 1.0f, l2, RenderUtil.INSTANCE.withAlpha(color2, f3));
            }
            fontRenderer.drawStringCentered(matrix3x2fStack, string, f, f2, l2, color);
        }
    }

    public static void drawWorldText$default(RenderState renderState, long l, String string, Vec3d vec3d, Color color, float f, float f2, boolean bl, FontRenderer fontRenderer, int n, Object object) {
        long l2 = (l = a ^ l) ^ 0x24C0995270F8L;
        int n2 = (int)(l2 >>> 32);
        int n3 = (int)(l2 << 32 >>> 56);
        int n4 = (int)(l2 << 40 >>> 40);
        if ((n & 8) != 0) {
            f = 0.0f;
        }
        if ((n & 0x10) != 0) {
            f2 = 0.0f;
        }
        if ((n & 0x20) != 0) {
            bl = false;
        }
        if ((n & 0x40) != 0) {
            fontRenderer = Fonts.getSF_B3();
        }
        renderState.drawWorldText(n2, (byte)n3, string, vec3d, color, f, n4, f2, bl, fontRenderer);
    }

    public final float getTickDelta() {
        return Globals.getMc().getRenderTickCounter().getTickProgress(true);
    }

    public final boolean addBox(@NotNull Box box, @NotNull Color color, @NotNull Color color2) {
        Intrinsics.checkNotNullParameter(box, "box");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(color2, "color2");
        return RenderQueue.INSTANCE.getBoxes().add(new QueuedBox(box, color, color2));
    }

    public static boolean addBox$default(RenderState renderState, Box box, Color color, Color color2, Object object) {
        color2 = color;
        return renderState.addBox(box, color, color2);
    }

    public final boolean addFilledBox(@NotNull Box box, @NotNull Color color, @NotNull Color color2) {
        Intrinsics.checkNotNullParameter(box, "box");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(color2, "color2");
        return RenderQueue.INSTANCE.getFilledBoxes().add(new QueuedBox(box, color, color2));
    }

    public static boolean addFilledBox$default(RenderState renderState, Box box, Color color, Color color2, Object object) {
        color2 = color;
        return renderState.addFilledBox(box, color, color2);
    }

    public final boolean addOutlineBox(@NotNull Box box, @NotNull Color color, @NotNull Color color2) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(box, (String)((Object)RenderState.zkmBootstrap("i", (int)25235, (long)(0x34475A57B0C1CE21L ^ l2))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)RenderState.zkmBootstrap("i", (int)27188, (long)(0x30CA048F5E55C6A3L ^ l2))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RenderState.zkmBootstrap("i", (int)5475, (long)(0x16DEAEC5C19DB9DDL ^ l2))));
        return RenderQueue.INSTANCE.x().add(new QueuedBox(box, color, color2));
    }

    public static boolean addOutlineBox$default(RenderState renderState, Box box, Color color, Color color2, Object object) {
        color2 = color;
        return renderState.addOutlineBox(box, color, color2);
    }

    public final boolean addFace(@NotNull Box box, @NotNull Direction direction, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(box, "box");
        Intrinsics.checkNotNullParameter(direction, "side");
        Intrinsics.checkNotNullParameter(color, "color");
        return RenderQueue.INSTANCE.getFaces().add(new BoxFace(box, color, direction));
    }

    public final boolean addFace2(@NotNull Box box, @NotNull Direction direction, @NotNull Color color) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(box, (String)((Object)RenderState.zkmBootstrap("i", (int)25235, (long)(0x34477F581873AC5AL ^ l))));
        Intrinsics.checkNotNullParameter(direction, (String)((Object)RenderState.zkmBootstrap("i", (int)28582, (long)(0x548C988349A1A16EL ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)RenderState.zkmBootstrap("i", (int)27188, (long)(0x30CA2180F6E7A4D8L ^ l))));
        return RenderQueue.INSTANCE.getFaceOutlines().add(new BoxFace(box, color, direction));
    }

    public final boolean addLine(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2, @NotNull Color color, @NotNull Color color2, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)RenderState.zkmBootstrap("i", (int)8353, (long)(0x4C5F2958B2927094L ^ l))));
        Intrinsics.checkNotNullParameter(vec3d2, (String)((Object)RenderState.zkmBootstrap("i", (int)7716, (long)(0x28890CD8E31A4E3DL ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)RenderState.zkmBootstrap("i", (int)19250, (long)(0x2BE0EEE3ABA91B02L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RenderState.zkmBootstrap("i", (int)10593, (long)(0x3065CB658347F940L ^ l))));
        return RenderQueue.INSTANCE.getLines().add(new QueuedLine(vec3d, vec3d2, color, color2, bl));
    }

    public static boolean addLine$default(RenderState renderState, Vec3d vec3d, Vec3d vec3d2, Color color, Color color2, boolean bl, int n, Object object) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 48 | (long)n3 << 32 >>> 16 | (long)n4 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5200378155913553450L, (long)l);
        int n5 = n & RenderState.zkmBootstrap2("t", (int)8302, (long)(0x6880EE4ECA76158AL ^ l));
        if (callSite == null) {
            if (n5 != 0) {
                color2 = color;
            }
            n5 = n & RenderState.zkmBootstrap2("t", (int)2189, (long)(0x5A751F53DC9BBD60L ^ l));
        }
        if (callSite == null) {
            if (n5 != 0) {
                bl = false;
            }
            n5 = renderState.addLine(vec3d, vec3d2, color, color2, bl) ? 1 : 0;
        }
        return n5 != 0;
    }

    public final boolean addLine(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2, @NotNull Color color, @NotNull Color color2) {
        Intrinsics.checkNotNullParameter(vec3d, "start");
        Intrinsics.checkNotNullParameter(vec3d2, "end");
        Intrinsics.checkNotNullParameter(color, "startColor");
        Intrinsics.checkNotNullParameter(color2, "endColor");
        return RenderQueue.INSTANCE.N().add(new QueuedLine(vec3d, vec3d2, color, color2, false, null));
    }

    public static boolean addLine$default(RenderState renderState, Vec3d vec3d, Vec3d vec3d2, Color color, Color color2, Object object) {
        color2 = color;
        return renderState.addLine(vec3d, vec3d2, color, color2);
    }

    @NotNull
    public final Box lerpBox(@NotNull Box box, @NotNull Box box2, float f) {
        Intrinsics.checkNotNullParameter(box, "from");
        Intrinsics.checkNotNullParameter(box2, "to");
        return new Box(RenderUtil.INSTANCE.lerp(box.minX, box2.minX, f), RenderUtil.INSTANCE.lerp(box.minY, box2.minY, f), RenderUtil.INSTANCE.lerp(box.minZ, box2.minZ, f), RenderUtil.INSTANCE.lerp(box.maxX, box2.maxX, f), RenderUtil.INSTANCE.lerp(box.maxY, box2.maxY, f), RenderUtil.INSTANCE.lerp(box.maxZ, box2.maxZ, f));
    }

    public static Box lerpBox$default(RenderState renderState, Box box, Box box2, float f, Object object) {
        f = renderState.getTickDelta();
        return renderState.lerpBox(box, box2, f);
    }

    @NotNull
    public final Vec3d lerpVec(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2, float f) {
        Intrinsics.checkNotNullParameter(vec3d, "from");
        Intrinsics.checkNotNullParameter(vec3d2, "to");
        return new Vec3d(RenderUtil.INSTANCE.lerp(vec3d.x, vec3d2.x, (double)f), RenderUtil.INSTANCE.lerp(vec3d.y, vec3d2.y, (double)f), RenderUtil.INSTANCE.lerp(vec3d.z, vec3d2.z, (double)f));
    }

    public static Vec3d lerpVec$default(RenderState renderState, Vec3d vec3d, Vec3d vec3d2, float f, Object object) {
        f = renderState.getTickDelta();
        return renderState.lerpVec(vec3d, vec3d2, f);
    }

    private final Box getInterpolatedBox(Entity entity) {
        double d = RenderUtil.INSTANCE.interpolate(entity.lastX, entity.getX());
        double d2 = RenderUtil.INSTANCE.interpolate(entity.lastY, entity.getY());
        double d3 = RenderUtil.INSTANCE.interpolate(entity.lastZ, entity.getZ());
        Box box = entity.getBoundingBox();
        return new Box(box.minX - entity.getX() + d - 0.05, box.minY - entity.getY() + d2, box.minZ - entity.getZ() + d3 - 0.05, box.maxX - entity.getX() + d + 0.05, box.maxY - entity.getY() + d2 + 0.15, box.maxZ - entity.getZ() + d3 + 0.05);
    }

    public final void drawBillboard(@NotNull Identifier identifier, @NotNull Vec3d vec3d, float f, float f2, long l, float f3, float f4, @NotNull Color color, boolean bl, @Nullable BatchedBuffer batchedBuffer, float f5, float f6, float f7, float f8, boolean bl2) {
        VertexFormat vertexFormat;
        int n;
        Object var31_20;
        long l2 = (l = a ^ l) ^ 0x22BA74AEABC9L;
        Intrinsics.checkNotNullParameter(identifier, "texture");
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Intrinsics.checkNotNullParameter(color, "color");
        BatchedBuffer batchedBuffer2 = batchedBuffer;
        boolean bl3 = batchedBuffer2 != null;
        MatrixStack matrixStack = new MatrixStack();
        if (!bl3) {
            VertexFormat vertexFormat2 = VertexFormats.POSITION_TEXTURE_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat2, "POSITION_TEX_COLOR");
            var31_20 = null;
            n = 6;
            boolean bl4 = false;
            int n2 = 0;
            vertexFormat = vertexFormat2;
            batchedBuffer2 = new BatchedBuffer(vertexFormat, n2, bl4, n, var31_20);
        }
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        Vec3d vec3d2 = vec3d.subtract(camera2.getCameraPos());
        Intrinsics.checkNotNullExpressionValue(vec3d2, "subtract(...)");
        Vec3d vec3d3 = vec3d2;
        if (bl2) {
            matrixStack.translate(vec3d3.x, vec3d3.y, vec3d3.z);
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(f));
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f2));
        } else {
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(camera2.getPitch()));
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(camera2.getYaw() + f + 180.0f));
            matrixStack.translate(vec3d3.x, vec3d3.y, vec3d3.z);
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-(camera2.getYaw() + f)));
            if (f2 == 0.0f) {
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(camera2.getPitch()));
            } else {
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f2));
            }
        }
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
        Matrix4f matrix4f2 = matrix4f;
        matrixStack.translate((double)(-f4) / 2.0, (double)(-f4) / 2.0, -0.01);
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(identifier).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        if (bl3) {
            batchedBuffer2.vertex(matrix4f2, 0.0f, f4, 0.0f).texture(f5, f8).color(color);
            batchedBuffer2.vertex(matrix4f2, f4, f4, 0.0f).texture(f7, f8).color(color);
            batchedBuffer2.vertex(matrix4f2, f4, 0.0f, 0.0f).texture(f7, f6).color(color);
            batchedBuffer2.vertex(matrix4f2, 0.0f, 0.0f, 0.0f).texture(f5, f6).color(color);
        } else {
            BatchedBuffer.vertex$default(batchedBuffer2, 0.0f, f4, 0.0f, null).texture(f5, f8).color(color);
            BatchedBuffer.vertex$default(batchedBuffer2, f4, f4, 0.0f, null).texture(f7, f8).color(color);
            BatchedBuffer.vertex$default(batchedBuffer2, f4, 0.0f, 0.0f, null).texture(f7, f6).color(color);
            BatchedBuffer.vertex$default(batchedBuffer2, 0.0f, 0.0f, 0.0f, null).texture(f5, f6).color(color);
            RenderPipeline renderPipeline = bl ? Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend() : Pipelines.INSTANCE.getProgramDefaultColoredTexture();
            Framebuffer framebuffer = Globals.getMc().getFramebuffer();
            Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
            var31_20 = null;
            n = 20;
            Map<String, GpuTextureView> map = MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2));
            Matrix3x2f matrix3x2f = null;
            vertexFormat = matrix4f2;
            GpuBufferSlice gpuBufferSlice = null;
            Framebuffer framebuffer2 = framebuffer;
            RenderPipeline renderPipeline2 = renderPipeline;
            BatchedBuffer batchedBuffer3 = batchedBuffer2;
            BatchedBuffer.draw$default(l2, batchedBuffer3, renderPipeline2, framebuffer2, gpuBufferSlice, (Matrix4f)vertexFormat, matrix3x2f, map, n, var31_20);
        }
    }

    public static void drawBillboard$default(RenderState renderState, Identifier identifier, Vec3d vec3d, float f, float f2, float f3, float f4, long l, Color color, boolean bl, BatchedBuffer batchedBuffer, float f5, float f6, float f7, float f8, boolean bl2, int n, Object object) {
        long l2 = (l = a ^ l) ^ 0x6F633E40BD2FL;
        if ((n & 0x80) != 0) {
            bl = true;
        }
        if ((n & 0x100) != 0) {
            batchedBuffer = null;
        }
        if ((n & 0x200) != 0) {
            f5 = 0.0f;
        }
        if ((n & 0x400) != 0) {
            f6 = 0.0f;
        }
        if ((n & 0x800) != 0) {
            f7 = 1.0f;
        }
        if ((n & 0x1000) != 0) {
            f8 = 1.0f;
        }
        if ((n & 0x2000) != 0) {
            bl2 = false;
        }
        renderState.drawBillboard(identifier, vec3d, f, f2, l2, f3, f4, color, bl, batchedBuffer, f5, f6, f7, f8, bl2);
    }

    public final void drawPlayerHead(@NotNull MatrixStack matrixStack, @NotNull Vec3d vec3d, @NotNull PlayerEntity playerEntity, long l, float f, boolean bl) {
        long l2 = (l = a ^ l) ^ 0x3E2A0149E851L;
        Intrinsics.checkNotNullParameter(matrixStack, "stack");
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(((AbstractClientPlayerEntity)playerEntity).getSkin().comp_1626().comp_3627()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        Color color = f == 1.0f ? new Color(0.8f, 0.8f, 0.8f, f) : RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f);
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        Object var25_10 = null;
        int n = 4;
        boolean bl2 = false;
        int n2 = 256;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl2, n, var25_10);
        TexturedPolygonConsumer texturedPolygonConsumer = new TexturedPolygonConsumer(batchedBuffer, color);
        EntityRenderer<?, Entity> entityRenderer = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity);
        Intrinsics.checkNotNull(entityRenderer, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.player.AvatarRenderer<in @[EnhancedNullability] net.minecraft.client.player.AbstractClientPlayer>");
        Object m = ((PlayerEntityRenderer)entityRenderer).getModel();
        Intrinsics.checkNotNullExpressionValue(m, "getModel(...)");
        PlayerEntityModel playerEntityModel = (PlayerEntityModel)m;
        Entity entity = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity).getAndUpdateRenderState((Entity)playerEntity, this.getTickDelta());
        Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.state.AvatarRenderState");
        PlayerEntityRenderState playerEntityRenderState = (PlayerEntityRenderState)((Object)entity);
        matrixStack.push();
        double d = vec3d.x;
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        double d2 = d - camera.getCameraPos().getX();
        double d3 = vec3d.y;
        Camera camera2 = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera2);
        double d4 = d3 - camera2.getCameraPos().getY();
        double d5 = vec3d.z;
        Camera camera3 = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera3);
        double d6 = d5 - camera3.getCameraPos().getZ();
        matrixStack.translate((float)d2, (float)d4, (float)d6);
        matrixStack.scale(playerEntityRenderState.baseScale, playerEntityRenderState.baseScale, playerEntityRenderState.baseScale);
        LogoutSpots.INSTANCE.applyModelRotation(playerEntityRenderState, matrixStack, playerEntityRenderState.bodyYaw);
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
        matrixStack.translate(0.0f, -1.501f, 0.0f);
        playerEntityModel.setAngles(playerEntityRenderState);
        playerEntityModel.render(matrixStack, texturedPolygonConsumer, 0xF000F0, 0, color.getRGB());
        var25_10 = null;
        n = 22;
        Map<String, GpuTextureView> map = MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2));
        Matrix3x2f matrix3x2f = null;
        vertexFormat2 = new MatrixStack().peek().getPositionMatrix();
        GpuBufferSlice gpuBufferSlice = null;
        Framebuffer framebuffer = null;
        RenderPipeline renderPipeline = bl ? Pipelines.INSTANCE.getProgramDefaultColoredTextureCullDepth() : Pipelines.INSTANCE.getProgramDefaultColoredTextureCull();
        BatchedBuffer batchedBuffer2 = batchedBuffer;
        BatchedBuffer.draw$default(l2, batchedBuffer2, renderPipeline, framebuffer, gpuBufferSlice, (Matrix4f)vertexFormat2, matrix3x2f, map, n, var25_10);
        matrixStack.pop();
    }

    public static void Q(RenderState renderState, MatrixStack matrixStack, Vec3d vec3d, PlayerEntity playerEntity, float f, boolean bl, Object object, long l) {
        long l2 = (l = a ^ l) ^ 0x14F15C267D8FL;
        bl = false;
        renderState.drawPlayerHead(matrixStack, vec3d, playerEntity, l2, f, bl);
    }

    @NotNull
    public final Box getRenderBox(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "<this>");
        return this.getInterpolatedBox(entity);
    }

    @NotNull
    public final Vec3d getInterpolatedPos(@NotNull PlayerEntity playerEntity, float f) {
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "position(...)");
        Vec3d vec3d2 = vec3d;
        double d = playerEntity.lastX + (playerEntity.getX() - playerEntity.lastX) * (double)f - vec3d2.x;
        double d2 = playerEntity.lastY + (playerEntity.getY() - playerEntity.lastY) * (double)f - vec3d2.y;
        double d3 = playerEntity.lastZ + (playerEntity.getZ() - playerEntity.lastZ) * (double)f - vec3d2.z;
        return new Vec3d(d, d2, d3);
    }

    public static Vec3d getInterpolatedPos$default(RenderState renderState, PlayerEntity playerEntity, float f, Object object) {
        f = renderState.getTickDelta();
        return renderState.getInterpolatedPos(playerEntity, f);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        RenderState.a = ZkmKeys.a(-4174707533709301951L, 9017164347033285973L, MethodHandles.lookup().lookupClass()).a(19905865458321L);
                        var20 = RenderState.a ^ 6478026844419L;
                        RenderState.e = new HashMap<K, V>(13);
                        RenderState.zkmSetFlag(0);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[47];
                        var16_4 = 0;
                        var15_5 = "\u0093\u0014\u00ce\u00d7_\u00a9k\u00bc\u00c4m~\u00ddP\u00c1\u009d{r\u008a\u0011Q]\u0088\u008c\u00ad\u00af\u00e6-\u0081D\u00ab\u00d3(\u00dcG\u00b9\u0097?\u00e2\u0013\u00aad\u00a0\u00a1[X\u00d1\u0014\u009c\u009d\u00ae\u00af\u00a7\u00fax\u0011\u007f )\u00d8\u00f0\u00c6\u0015\u00a4G\u00a6c\u001e\u0013\u00c4\u00f7\u00e7\u008c\u0098\u008c\u00e6\u00ecU4g\u00c7\u00c3\u00a3\u00e3\u008e\u00cf\u0082\u00dbOy\u0010W\u0003\u009d\u00b2\rq|)\u00e9g=\u0089\u00a06y\u00f6\u0010%?\u0096N\u0084\u00e3\u00d6\u0086\u0093}\u009a\u0087E \u000f\u0081\u0010\u0015*\u00b3\"\u00c8\u00f8q\u00a1z\u00ba\u00a4D\u00972\u0018n\u00a0\u00d7y\r\u00b1\u00b5;\u0093\u00f7<@\u00d1\u00f7\u0014\u0099\u0091\u0082\u009c\u00c9\u00cc\u00ed\u00abnb\u00f4j\u00b9\u00a9)\u00dd/\u00eb=\u0089wK\u00c657,\u00c0N\u00b5\u0002\u009b\u00e0r\u00a9aW\u00dbgy\u00d5\u00f4l\u00fch,NU\u00ad\u00f4'\u0085\u008f\u00a7\u00e0\u00e3\u00e9G\u00ad\u001a\u00fb\u00bd\u00ee}\u00fc\u00c7\u00cb\u00e7L\u00de\u0081\u0092\u00b5\u00e2A\u00c5\u00c7X\u00d1\u00fc\u009d\t\u00ce\u00f6\u00a9]=\u00a8\u00be\u00b0\u00c5\u0090>nm\u00dd(\tP\u0005\u001b\u00cd\u00bc\u0085\u00a0#\u00bf\u00b9\u008b\u00c5\u0013\u0004r\u00b04K\u0098$\u00be\u00fbY\u009e\u00a6F\u0097\u00ce\u0094\u0089\u008c[\b\u009a\u00bd\u0091\u00a4\u00e0\u00bb\u0088F\u00b8\u00c7'`o(\u0094\u00bd\u00d4\u0010(@\u00b4g\u00e9p\u00a8\u008c\u00dd\u00e7L\u00b7\u0002M\u00b3\u00f3\u0010\u00c8\u0080|\u00d1\u00c5*IU\u00aa\u00033\u00cf\u00a3\u00c5x\u00cd\u0010G\u00ec\u00d5\u0006g7\u001a-7\u0007\u0001\u00baq\u0014\u0016^\u0010G\u00a9\u008a\u00dc $.\u0096N\u00ee\u00a5F\u0014k\u00eb\u0017\u0010\u00d7\u0006\u00a12\u00d9\u0081\u00f4\u0099\u00d9\u00d6I`\u00a1\u00d1\u0081\"\u0010\u00c9\u00cb\u008b\u0096\u008e\u00c5\u009a\u00c2\u001d\u00c7/\u00faq\u0004U\u00b3 \u00bf\u00e8\u00de\u00dat$}\u008e\u00e1f\u0099\u00cd\u00e8\u0010A\u0099t\u0085\u00fd\u0098\u00fe\u0093c\u00d4X\u00fa\u00c6\u00a3\u0092'\u00a0\u00e2(\u008e\u00ac8\u0083}Q\u009b45I\u00c8\u00d8Ad[\u00e4`\u008dB\u0098\u0098eh]\u00bf\u00b5\u00db{\u0087\u00f1\u00ffP\u00c3F\u00f56 \u00da\u00fa\u00fc(v|\u00c2\u00e45\u0082~F\u0003\u0007\u009c\u00e8\u00eb\u009a\u001f\u0082\u00ee\u00c7\u00cft\u00df\u0081J\u00eal\u0090*~N%3`\u00a4\u00d6\u00db\u0006\u00027j\u00c8\u0010/l\r\u00be3Za\u00d7\u00e9\u001f\u00ab\u00d4\u00f1\u00c0P\u00cd\u0010Fs\u00b3&=\u0019:\u00fe\u000f\u0010\u0085\u00c0\b\u00c3|>\u0010e\u0012gk\u00e7\u00b3\u0091\u0088\u00a0\u0011>yn\u001c\u00ccp\u0010\u00b7q\u00fe\u009d\u0091\u00d2B\r\u0006\u00e9\u00c0\u00b7\u00b1\u00f3\u0084\u001b E\u00017c\u0095\u00fc\u00e0\u00dbZ\u00d0\u00e9\u0081f\u008b\u00b8F\u00cc\u000e{y\u00d9\u0081o\u0004\t\u00b1z\u0084CP\u001d\u00e9(\u001bjK\u001ch\u0094\u00bcuH\u00ef\u0087\u00b5\u00d0u\u00c0=\u0011z\u0096\u00cab\"?8\u0016E\u00d4\u0014\u009d\u00cb\u0091\u00e7\u00ba\u00f7\u009a)&\u00f1\u00adw\u0018(\u001f\u00d7o\u001d\u00ed\u0080\u0000{\u00ff\u00f5\u00cc\u00c8 W\u00f1K\u00e7]\f\u00b2;\u00f0A\u0010\u00c2,\u00c5\u00f0\u0016\u0080v\f\u00f2/WhxF05(\u00e4\u00a6z\u00a8mf\u00b4\u00c8\u00e8\u00f0\u00dc\u00f5\\\u00b6\u001a:\u00e2\u00b3\u00b446$m\u008eT?\u00b74w\u00c5z\u008d*\u0084\u009a\u00ec\u0085\u00e9\u0014\u00a5\u0010\u00ce*\u00b3\u00d2\u00d5\u009c\u00e12F4m\u00f0bJyg\u0010A,\u00cd\u000e\u00d9\u00f0DY\u00d4\u0013S\u0011A\u00f7\u008a\u00f7 S\u008a\u00a6f~\u0080\u00ecA\u00ae}\u00a7\t\u008d\u008a\u0098\u00a7\u00ad-\u00d9X{\u000e/Q\u008b\u000b\u0082mM\n\u00e2c \u00c5-\u00cf\u00a40\u00f1\u00f8d\u000biOH\u00d38\u00c0\u00b7\u00a8\u0083\u008e\u0081p*l,\u00cc\u00b0=\u0005 \u00af\u0080\u001f\u0010\t<\u0090d\u0012U\u00afP}\u00a7j\u00d2T\u00af\u00cd\u0003 \u00b2a\u00ca1wLn\u00ffz\u009a\u000f\u0091xY#\u00b5\u00e7\u00b2\u00f9\u00c7\u00dc>\u00f1t3b\f\u0013\"UA\u00fc\u0010\u00c0\u00cb\u00bfra\u008c\u00f8\u009cr\u009f\u00f7B\u0013\b7@\u0018 \u009f\u00d2K\u00ff\u00e4y\u00cee\u0015dMt\u008b\u0094C'/>SiP\u0086\u001a \u0002Of\u008d\u009f\u00d3\u00d6po\u00ef\u00b3\u00a8\bxIn\u0097u\u009d/u\u0001\u00f8N\u001fj\u009fau\u00e6\u00d3^\u0010.\u00be8'Dr\u00ec\u0097\u00ab\u0011$\u00961\u00f0h\u001c\u0010\u00b9~}\u00df\u008d\u0097\u0082`!\u00ab\u0088\u00ccl\r\u00b4\u0082\u0010_K\u001c7\u00dd\u0011Q\u001bo\u009d@N\u00ff\u00ae\n\u0001(\u00b6\u00a1\u0006\u00f5p$\u00e2\u00f8\u00eb\u0094\u001d\u00e0>\u00f6\u00f7H\u0001n\u00ae\u00d2\u00a8\fqF\u00c4\u00b3\u000f\u008d\u0094\u00d7Y\u0001\u00dea\u00ec7QGM\u00db\u0010w\u00aaB\u0090\u00d4\u0006\u00a3\u00b9O\u0015G\u009b\tn\u0088\u00e3\u0010\u00db!\u00dc\u00afo\u007fb\u00b0\u00d4\u00df*\u008e(\u00efm\u009f(\u00c0u,\u0089y\u0011/\u00af\u0093\u00ee\u00ff\u00c5\r\n\u0016\u0019r>\u008b\u008a\u00d5I\u00e9\u0014\u00e0\u00ac\u0099\u00ab\u00aer\u00863\u000e\u00a3\u00d0T\u00ccG\u00b1\u001c\u00f8\u000b,\u00ad(\u00cd6\u00b4\u00c52m!\u00a9\u0093\"\u00bbs\u00aa\u00c3\u00dfX\u00fb\u00ca\u009exL\u0010\u00f3\u00c4\u00d9\u001b\u0019\u00f9aZH\u00fegi\u0087\u0086eLq\u00bb\u00bd\u00f5\u0091kX\u00e7\u00d1p!\u001a\f\u009e\u00c7+\u0091\b\u00df\u0001\u0086R\u0019\u00f6\u0003\u00ebf2\u0092h\ff\u00b3\u00b9\u00a2\u0089\u00ad\u00eeO\u008b\u009ce\u00f7\u0006\u008cv%\u00df\r\u00da\u00cc\u0017\u00ff\u00e3\u00b4E7\u0010s ^~\u008a\u0081\u00d2\u00b9\u00880]P\u00bdW\u0087\u00a9!;\u00df\u00e1\u00b2\u00af\u00a2w>\u00d6A\u00b3,d\u007f\u008d\u00e71\u00b4\u00ca\u0083\u009d\u00ee\u00fb\u00a4\u00c4[\u00f7Z\n\u00d1\u00c0\u00159#E\u00b1U\u00a6\u0088U\u0015\u00bb;\u0089&z\u00e6\u00d3\u009fB\u00d6{\u00fe.M\u00c9\u00ba\u0088\u001d\u009f\u00f1(\u00c4\u00ba\u00a4\u00b2\u00a2\u00da\nQ\t7\u00bd\u0088\u00e4\u0015\u00a1\u00ac\u00b0\u0016\u00be\u00c0\u00c8\u00d0lS,Z\u00a9\u00b7\u00ed\u00df\u00a0\u0016P\u00a7Q\u00f5b\u008ar\u001bx\u00eb\u009a\u0012V\u00a7\u009d\u00f1R\b\u00ed\u008fh\u00d8\u00b1]\u00dabD\u00b4P\u00b7F\u0092\u0094\u00a7\u00ee`L\u0010mH\u00e2\u00d3\u009bl}\u00efC\u009f\u00e78w\u00e7\u0012j\u0010}\u0093\u0001\u00aez^\u00a3\u00a3\u00e0\u00b1#\u000bO\u00bb)'\u0010\u00ba\u0093\u001b\u00eaF\u0005\u00bc>\u00e8\u00aa\u00da\u00ce/=\u00e0\u00c0 \u00d9\u00f2f\u00ceN\u00c1M\u008c\u0000\u0018\u00b8D\u00e3U\u0093^\u0015Ti[Z\u00d9\u00b8\u00bb\u00d86z\u00b3@\tZ\u0013";
                        var17_6 = 1468;
                        var14_7 = 56;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = RenderState.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "W\u0092\u00c4\u00df\u00fc\u0018\u00a3\u00d4\u0091,*k\u00b8\u0081!\u008f\u0010\u00ba\u0082C\u00a9R\u00ab|Y\u00ed'\u000fV\u00b2h\u00f0\u00a2";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = RenderState.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                RenderState.b = var18_3;
                RenderState.d = new String[47];
                RenderState.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[21];
                var3_13 = 0;
                var4_14 = "`.KK\u0086A\u00f9\u00a9\u00da\u0094f\u00ad\u008d\u0011E\\2p\u008d$\u00f7]\b\u00c1\u00f7\u008d\u009e\u00e4\u00efLE\u00a3\u00c0\u00dec\u0085\u0007\u0099-\u00c1OWH\u009f\u008cu\u00a7\u0081\u00c7\u00f5\u00e9\u00b2\u00c3\u0088\u0099\u00c8\u00ab\u00bdY\u00c2\u00f7\u00e7\u00f19\u00865\"{\u00bbW\n\u009d\u00ac\u0014@\u00ccIa\u0011V-\u00e4\u00cf\u001eu\u00f5\u00b4\u00d9k\u00e58\u0016\u00ce\b\u008ct\u00f4Q}5=-\u00ea\u00a6\u008c\u0018\u0081C\u00bc\u00fd\u00d7A\u00ee.\"p\u000f1\u00df{`\u00ed\u00f8-\u0001\fY\u00fd\u0017\u00bdQ\u0094\u00b9[\u00fd>g\u00a9=\u00e1\u00e2\u00ee\u00e4\r\u00bb\u0011f\u00c9\u00b36\u0099\u00f9";
                var5_15 = 152;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "K\u00e0]\u00899\u0094\u0086\u00ebp4\u00e0\u009e\u00ff\u00bb\u00e0\u00d6";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl92:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl105:
                // 1 sources

                ** continue;
            }
        }
        RenderState.f = var6_12;
        RenderState.g = new Integer[21];
        RenderState.INSTANCE = new RenderState();
        RenderState.projection = new Matrix4f();
        RenderState.frustum = new Frustum(new Matrix4f(), new Matrix4f());
    }

    public static void zkmSetFlag(int n) {
        N = n;
    }

    public static int zkmGetFlag() {
        return N;
    }

    public static int zkmIntConstant() {
        return 28;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static String zkmDecodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x38E2;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/zi", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            RenderState.d[n2] = RenderState.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RenderState.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/zi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7C82;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/zi", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RenderState.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RenderState.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/zi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RenderState.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(RenderState.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

