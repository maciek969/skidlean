/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix4f
 *  org.lwjgl.opengl.GL32C
 */
package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL32C;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.misc.UI;
import su.catlean.module.misc.UILinesMode;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BlockHighlightRenderer;
import su.catlean.render.BoxFace;
import su.catlean.render.Pipelines;
import su.catlean.render.QueuedBox;
import su.catlean.render.QueuedLine;
import su.catlean.render.Render2D;
import su.catlean.render.RenderQueue$WhenMappings;
import su.catlean.render.RenderState;
import su.catlean.render.UniformStorage;
import su.catlean.render.uniform.BlurUniforms;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class RenderQueue {
    @NotNull
    public static final RenderQueue INSTANCE;
    @NotNull
    private static List filledBoxes;
    @NotNull
    private static List boxes;
    @NotNull
    private static List F;
    @NotNull
    private static List faces;
    @NotNull
    private static List faceOutlines;
    @NotNull
    private static List lines;
    @NotNull
    private static List K;
    @Nullable
    private static WindowFramebuffer glowFramebuffer;
    @Nullable
    private static WindowFramebuffer framebuffer;
    private static boolean g;
    private static boolean E;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    private RenderQueue() {
    }

    @NotNull
    public final List getFilledBoxes() {
        return filledBoxes;
    }

    public final void setFilledBoxes(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)19741, (long)(0x140F3BEFAF64EF69L ^ l))));
        filledBoxes = list;
    }

    @NotNull
    public final List getBoxes() {
        return boxes;
    }

    public final void setBoxes(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)19741, (long)(0x140F40DD1EBC5AF1L ^ l))));
        boxes = list;
    }

    @NotNull
    public final List x() {
        return F;
    }

    public final void w(@NotNull List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)6222, (long)(0x3BBB64DE8B6B634AL ^ l))));
        F = list;
    }

    @NotNull
    public final List getFaces() {
        return faces;
    }

    public final void setFaces(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)19741, (long)(0x140F5530B7AF1666L ^ l))));
        faces = list;
    }

    @NotNull
    public final List getFaceOutlines() {
        return faceOutlines;
    }

    public final void setFaceOutlines(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)19741, (long)(0x140F1C0F10BA5825L ^ l))));
        faceOutlines = list;
    }

    @NotNull
    public final List getLines() {
        return lines;
    }

    public final void setLines(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)19741, (long)(0x140F49C3AA6D14E3L ^ l))));
        lines = list;
    }

    @NotNull
    public final List N() {
        return K;
    }

    public final void N(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)RenderQueue.zkmBootstrap("n", (int)19741, (long)(0x140F7260563E0257L ^ l))));
        K = list;
    }

    @Flow(priority=-10)
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l;
        long l2 = l = a ^ 0x2DC6D46810B1L;
        long l3 = l2 ^ 0x36E56A7A8774L;
        long l4 = l2 ^ 0x3302E68161BDL;
        long l5 = l2 ^ 0x6F0166810C16L;
        long l6 = l2 ^ 0xC685F14B416L;
        int n = (int)(l6 >>> 32);
        int n2 = (int)(l6 << 32 >>> 48);
        int n3 = (int)(l6 << 48 >>> 48);
        BlockHighlightRenderer.INSTANCE.renderAll(render3DEvent.getStack());
        this.renderBoxes(l5, render3DEvent.getStack());
        this.renderOutlines(render3DEvent.getStack(), l3);
        this.renderFilled(n, render3DEvent.getStack(), (short)n2, (char)n3);
        this.renderLines(l4, render3DEvent.getStack());
        filledBoxes.clear();
        boxes.clear();
        F.clear();
        faces.clear();
        faceOutlines.clear();
        lines.clear();
        K.clear();
    }

    private final void renderLines(long l, MatrixStack matrixStack) {
        long l2 = (l = a ^ l) ^ 0x95A7200275BL;
        if (!((Collection)boxes).isEmpty()) {
            this.ensureFramebuffer();
            VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n = 4;
            boolean bl = false;
            int n2 = boxes.size() * 6;
            VertexFormat vertexFormat2 = vertexFormat;
            BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
            Iterable iterable = boxes;
            for (Object t : iterable) {
                QueuedBox queuedBox = (QueuedBox)t;
                INSTANCE.drawFilledBox(batchedBuffer, queuedBox.getBox(), queuedBox.getColor(), queuedBox.getColor());
            }
            RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultPosColorNoBlend();
            WindowFramebuffer windowFramebuffer = glowFramebuffer;
            Intrinsics.checkNotNull(windowFramebuffer);
            BatchedBuffer.draw$default(l2, batchedBuffer, renderPipeline, windowFramebuffer, null, matrixStack.peek().getPositionMatrix(), null, null, 52, null);
            g = true;
        }
    }

    private final void renderBoxes(long l, MatrixStack matrixStack) {
        long l2 = (l = a ^ l) ^ 0x5559F2004AF0L;
        if (!((Collection)filledBoxes).isEmpty() || !((Collection)faces).isEmpty()) {
            Object object;
            VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n = 4;
            boolean bl = false;
            int n2 = filledBoxes.size() * 6 + faces.size();
            VertexFormat vertexFormat2 = vertexFormat;
            BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
            Iterable iterable = filledBoxes;
            for (Object t : iterable) {
                object = (QueuedBox)t;
                INSTANCE.drawFilledBox(batchedBuffer, ((QueuedBox)object).getBox(), ((QueuedBox)object).getColor(), ((QueuedBox)object).getColor2());
            }
            iterable = faces;
            for (Object t : iterable) {
                object = (BoxFace)t;
                INSTANCE.drawFaceOutline(((BoxFace)object).getBox(), batchedBuffer, ((BoxFace)object).getColor(), ((BoxFace)object).getSide());
            }
            BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, matrixStack.peek().getPositionMatrix(), null, null, 54, null);
        }
    }

    private final void renderFilled(int n, MatrixStack matrixStack, short s, char c) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)c << 48 >>> 48) ^ a;
        long l2 = l ^ 0x3630CB95F2F0L;
        boolean bl = Render2D.U();
        boolean bl2 = ((Collection)K).isEmpty();
        if (!bl) {
            bl2 = !bl2;
        }
        if (bl2) {
            block4: {
                GL32C.glEnable((int)2848);
                GL32C.glHint((int)3154, (int)4354);
                VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
                Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
                boolean bl3 = true;
                int n2 = K.size();
                VertexFormat vertexFormat2 = vertexFormat;
                BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl3);
                Iterable iterable = K;
                for (Object t : iterable) {
                    QueuedLine queuedLine = (QueuedLine)t;
                    RenderQueue.drawLine$default(INSTANCE, batchedBuffer, queuedLine.getStart(), queuedLine.getEnd(), queuedLine.getStartColor(), queuedLine.getEndColor(), false, 32, null);
                    if (!bl) {
                        if (!bl) continue;
                    }
                    break block4;
                }
                RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultPosColorLinesAlt();
                Framebuffer framebuffer = Globals.getMc().getFramebuffer();
                Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
                BatchedBuffer.draw$default(l2, batchedBuffer, renderPipeline, framebuffer, null, matrixStack.peek().getPositionMatrix(), null, null, 52, null);
            }
            GL32C.glDisable((int)2848);
        }
    }

    private final void renderOutlines(MatrixStack matrixStack, long l) {
        block16: {
            Framebuffer framebuffer;
            Object object;
            Object t;
            Iterator iterator2;
            Object object2;
            BatchedBuffer batchedBuffer;
            VertexFormat vertexFormat;
            int n;
            boolean bl;
            long l2;
            block14: {
                block13: {
                    block15: {
                        l2 = (l = a ^ l) ^ 0xCBDFEFBC192L;
                        bl = Render2D.U();
                        boolean bl2 = ((Collection)F).isEmpty();
                        if (!bl) {
                            bl2 = !bl2;
                        }
                        if (bl2) break block15;
                        boolean bl3 = ((Collection)faceOutlines).isEmpty();
                        if (!bl) {
                            bl3 = !bl3;
                        }
                        if (bl3) break block15;
                        boolean bl4 = ((Collection)lines).isEmpty();
                        if (!bl) {
                            bl4 = !bl4;
                        }
                        if (!bl4) break block16;
                    }
                    if (UI.INSTANCE.getLinesMode() == UILinesMode.NEW) {
                        this.ensureGlowFramebuffer();
                    }
                    GL32C.glEnable((int)2848);
                    GL32C.glHint((int)3154, (int)4354);
                    VertexFormat vertexFormat2 = VertexFormats.POSITION_COLOR;
                    Intrinsics.checkNotNullExpressionValue(vertexFormat2, "POSITION_COLOR");
                    boolean bl5 = true;
                    n = lines.size() + faceOutlines.size() * 4 + F.size() * 12;
                    vertexFormat = vertexFormat2;
                    batchedBuffer = new BatchedBuffer(vertexFormat, n, bl5);
                    object2 = F;
                    iterator2 = object2.iterator();
                    while (iterator2.hasNext()) {
                        t = iterator2.next();
                        object = (QueuedBox)t;
                        INSTANCE.drawOutlineBox(((QueuedBox)object).getBox(), batchedBuffer, ((QueuedBox)object).getColor(), ((QueuedBox)object).getColor2());
                        if (!bl) {
                            if (!bl) continue;
                        }
                        break block13;
                    }
                    object2 = faceOutlines;
                }
                iterator2 = object2.iterator();
                while (iterator2.hasNext()) {
                    t = iterator2.next();
                    object = (BoxFace)t;
                    INSTANCE.drawFace(((BoxFace)object).getBox(), batchedBuffer, ((BoxFace)object).getColor(), ((BoxFace)object).getSide());
                    if (!bl) {
                        if (!bl) continue;
                    }
                    break block14;
                }
                object2 = lines;
            }
            iterator2 = object2.iterator();
            while (iterator2.hasNext()) {
                t = iterator2.next();
                object = (QueuedLine)t;
                INSTANCE.drawLine(batchedBuffer, ((QueuedLine)object).getStart(), ((QueuedLine)object).getEnd(), ((QueuedLine)object).getStartColor(), ((QueuedLine)object).getEndColor(), ((QueuedLine)object).isIgnoreFrustrum());
                if (!bl && !bl) continue;
            }
            RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultPosColorLines();
            if (UI.INSTANCE.getLinesMode() == UILinesMode.OLD) {
                framebuffer = Globals.getMc().getFramebuffer();
            } else {
                WindowFramebuffer windowFramebuffer = RenderQueue.framebuffer;
                Intrinsics.checkNotNull(windowFramebuffer);
                framebuffer = windowFramebuffer;
            }
            object2 = framebuffer;
            Intrinsics.checkNotNull(object2);
            Object var22_6 = null;
            n = 52;
            vertexFormat = null;
            Matrix3x2f matrix3x2f = null;
            Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
            GpuBufferSlice gpuBufferSlice = null;
            Object object3 = object2;
            RenderPipeline renderPipeline2 = renderPipeline;
            BatchedBuffer batchedBuffer2 = batchedBuffer;
            BatchedBuffer.draw$default(l2, batchedBuffer2, renderPipeline2, (Framebuffer)object3, gpuBufferSlice, matrix4f, matrix3x2f, (Map)((Object)vertexFormat), n, var22_6);
            int n2 = 2848;
            if (!bl) {
                GL32C.glDisable((int)n2);
                n2 = UI.INSTANCE.getLinesMode() == UILinesMode.NEW ? 1 : 0;
            }
            E = n2;
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        block7: {
            boolean bl;
            block8: {
                WindowFramebuffer windowFramebuffer;
                boolean bl2;
                long l;
                block4: {
                    block5: {
                        boolean bl3;
                        block6: {
                            long l2 = a ^ 0x6B7288D78F91L;
                            l = l2 ^ 0x595EA0893DC9L;
                            bl2 = Render2D.U();
                            windowFramebuffer = glowFramebuffer;
                            if (bl2) break block4;
                            if (windowFramebuffer == null) break block5;
                            bl3 = g;
                            if (bl2) break block6;
                            if (!bl3) break block5;
                            BatchedBuffer batchedBuffer = new BatchedBuffer(null, 0, false, 7, null);
                            RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramPostMultidefault();
                            Framebuffer framebuffer = Globals.getMc().getFramebuffer();
                            Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
                            DynamicUniformStorage dynamicUniformStorage = UniformStorage.INSTANCE.getDynamicUniformStorage();
                            WindowFramebuffer windowFramebuffer2 = glowFramebuffer;
                            Intrinsics.checkNotNull(windowFramebuffer2);
                            float f = 1.0f / (float)windowFramebuffer2.textureWidth;
                            WindowFramebuffer windowFramebuffer3 = glowFramebuffer;
                            Intrinsics.checkNotNull(windowFramebuffer3);
                            GpuBufferSlice gpuBufferSlice = dynamicUniformStorage.write((DynamicUniformStorage.Uploadable)new BlurUniforms(new Vec2f(f, 1.0f / (float)windowFramebuffer3.textureHeight)));
                            WindowFramebuffer windowFramebuffer4 = glowFramebuffer;
                            Intrinsics.checkNotNull(windowFramebuffer4);
                            GpuTextureView gpuTextureView = windowFramebuffer4.getColorAttachmentView();
                            Intrinsics.checkNotNull(gpuTextureView);
                            BatchedBuffer.draw$default(l, batchedBuffer.fullscreenQuad(), renderPipeline, framebuffer, gpuBufferSlice, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView)), 24, null);
                            bl3 = false;
                        }
                        g = bl3;
                    }
                    windowFramebuffer = framebuffer;
                }
                if (windowFramebuffer == null) break block7;
                bl = E;
                if (bl2) break block8;
                if (!bl) break block7;
                BatchedBuffer batchedBuffer = new BatchedBuffer(null, 0, false, 7, null);
                RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramPostLines();
                Framebuffer framebuffer = Globals.getMc().getFramebuffer();
                Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
                DynamicUniformStorage dynamicUniformStorage = UniformStorage.INSTANCE.getDynamicUniformStorage();
                WindowFramebuffer windowFramebuffer5 = RenderQueue.framebuffer;
                Intrinsics.checkNotNull(windowFramebuffer5);
                float f = 1.0f / (float)windowFramebuffer5.textureWidth;
                WindowFramebuffer windowFramebuffer6 = RenderQueue.framebuffer;
                Intrinsics.checkNotNull(windowFramebuffer6);
                GpuBufferSlice gpuBufferSlice = dynamicUniformStorage.write((DynamicUniformStorage.Uploadable)new BlurUniforms(new Vec2f(f, 1.0f / (float)windowFramebuffer6.textureHeight)));
                WindowFramebuffer windowFramebuffer7 = RenderQueue.framebuffer;
                Intrinsics.checkNotNull(windowFramebuffer7);
                GpuTextureView gpuTextureView = windowFramebuffer7.getColorAttachmentView();
                Intrinsics.checkNotNull(gpuTextureView);
                BatchedBuffer.draw$default(l, batchedBuffer.fullscreenQuad(), renderPipeline, framebuffer, gpuBufferSlice, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView)), 24, null);
                bl = false;
            }
            E = bl;
        }
    }

    private final void drawLine(BatchedBuffer batchedBuffer, Vec3d vec3d, Vec3d vec3d2, Color color, Color color2, boolean bl) {
        block5: {
            boolean bl2;
            block4: {
                boolean bl3 = Render2D.U();
                bl2 = bl;
                if (bl3) break block4;
                if (bl2) break block5;
                bl2 = RenderState.INSTANCE.getFrustum().intersectPoint(vec3d2.x, vec3d2.y, vec3d2.z);
            }
            if (!bl2) {
                return;
            }
        }
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d3 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d3, "position(...)");
        Vec3d vec3d4 = vec3d3;
        float f = (float)(vec3d.x - vec3d4.getX());
        float f2 = (float)(vec3d.y - vec3d4.getY());
        float f3 = (float)(vec3d.z - vec3d4.getZ());
        float f4 = (float)(vec3d2.x - vec3d4.getX());
        float f5 = (float)(vec3d2.y - vec3d4.getY());
        float f6 = (float)(vec3d2.z - vec3d4.getZ());
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
    }

    /*
     * WARNING - void declaration
     */
    static void drawLine$default(RenderQueue renderQueue, BatchedBuffer batchedBuffer, Vec3d vec3d, Vec3d vec3d2, Color color, Color color2, boolean bl, int n, Object object) {
        boolean bl2;
        block5: {
            boolean bl3;
            block4: {
                void var7_8;
                boolean bl4 = Render2D.U();
                bl3 = var7_8 & 0x10;
                if (!bl4) {
                    bl3 = var7_8 & 0x20;
                }
                if (bl4) break block4;
                if (!bl3) break block5;
                bl3 = false;
            }
            bl2 = bl3;
        }
        renderQueue.drawLine(batchedBuffer, vec3d, vec3d2, color, color2, bl2);
    }

    private final void drawOutlineBox(Box box, BatchedBuffer batchedBuffer, Color color, Color color2) {
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "position(...)");
        Vec3d vec3d2 = vec3d;
        float f = (float)(box.minX - vec3d2.getX());
        float f2 = (float)(box.minY - vec3d2.getY());
        float f3 = (float)(box.minZ - vec3d2.getZ());
        float f4 = (float)(box.maxX - vec3d2.getX());
        float f5 = (float)(box.maxY - vec3d2.getY());
        float f6 = (float)(box.maxZ - vec3d2.getZ());
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f, f2, f6).color(color);
        batchedBuffer.vertex(f, f2, f6).color(color);
        batchedBuffer.vertex(f4, f2, f6).color(color);
        batchedBuffer.vertex(f4, f2, f6).color(color);
        batchedBuffer.vertex(f4, f2, f3).color(color);
        batchedBuffer.vertex(f4, f2, f3).color(color);
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f, f5, f3).color(color2);
        batchedBuffer.vertex(f, f5, f6).color(color2);
        batchedBuffer.vertex(f, f5, f6).color(color2);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
        batchedBuffer.vertex(f4, f5, f3).color(color2);
        batchedBuffer.vertex(f4, f5, f3).color(color2);
        batchedBuffer.vertex(f, f5, f3).color(color2);
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f, f5, f3).color(color2);
        batchedBuffer.vertex(f4, f2, f3).color(color);
        batchedBuffer.vertex(f4, f5, f3).color(color2);
        batchedBuffer.vertex(f4, f2, f6).color(color);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
        batchedBuffer.vertex(f, f2, f6).color(color);
        batchedBuffer.vertex(f, f5, f6).color(color2);
    }

    /*
     * Unable to fully structure code
     */
    private final void drawFace(Box var1_1, BatchedBuffer var2_2, Color var3_3, Direction var4_4) {
        v0 = Render2D.U();
        v1 = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(v1);
        v2 = v1.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(v2, "position(...)");
        var12_5 = v2;
        var13_6 = (float)(var1_1.minX - var12_5.getX());
        var11_7 = v0;
        var14_8 = (float)(var1_1.minY - var12_5.getY());
        var15_9 = (float)(var1_1.minZ - var12_5.getZ());
        var16_10 = (float)(var1_1.maxX - var12_5.getX());
        var17_11 = (float)(var1_1.maxY - var12_5.getY());
        var18_12 = (float)(var1_1.maxZ - var12_5.getZ());
        if (var11_7) ** GOTO lbl31
        switch (RenderQueue$WhenMappings.N[var4_4.ordinal()]) {
            case 1: {
                var2_2.vertex(var13_6, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var18_12).color(var3_3);
lbl31:
                // 2 sources

                v3 = var2_2.vertex(var13_6, var17_11, var15_9).color(var3_3);
                break;
            }
            case 2: {
                var2_2.vertex(var13_6, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var14_8, var18_12).color(var3_3);
                v3 = var2_2.vertex(var13_6, var14_8, var15_9).color(var3_3);
                break;
            }
            case 3: {
                var2_2.vertex(var13_6, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var15_9).color(var3_3);
                v3 = var2_2.vertex(var13_6, var14_8, var15_9).color(var3_3);
                break;
            }
            case 4: {
                var2_2.vertex(var13_6, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var18_12).color(var3_3);
                v3 = var2_2.vertex(var13_6, var14_8, var18_12).color(var3_3);
                break;
            }
            case 5: {
                var2_2.vertex(var13_6, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var13_6, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var13_6, var17_11, var15_9).color(var3_3);
                v3 = var2_2.vertex(var13_6, var14_8, var15_9).color(var3_3);
                break;
            }
            case 6: {
                var2_2.vertex(var16_10, var14_8, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var14_8, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var18_12).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var15_9).color(var3_3);
                var2_2.vertex(var16_10, var17_11, var15_9).color(var3_3);
                v3 = var2_2.vertex(var16_10, var14_8, var15_9).color(var3_3);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final void drawFaceOutline(Box box, BatchedBuffer batchedBuffer, Color color, Direction direction) {
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "position(...)");
        Vec3d vec3d2 = vec3d;
        float f = (float)(box.minX - vec3d2.getX());
        float f2 = (float)(box.minY - vec3d2.getY());
        float f3 = (float)(box.minZ - vec3d2.getZ());
        float f4 = (float)(box.maxX - vec3d2.getX());
        float f5 = (float)(box.maxY - vec3d2.getY());
        float f6 = (float)(box.maxZ - vec3d2.getZ());
        switch (RenderQueue$WhenMappings.N[direction.ordinal()]) {
            case 1: {
                batchedBuffer.vertex(f, f5, f3).color(color);
                batchedBuffer.vertex(f4, f5, f3).color(color);
                batchedBuffer.vertex(f4, f5, f6).color(color);
                BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(f, f5, f6).color(color);
                break;
            }
            case 2: {
                batchedBuffer.vertex(f, f2, f3).color(color);
                batchedBuffer.vertex(f4, f2, f3).color(color);
                batchedBuffer.vertex(f4, f2, f6).color(color);
                BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(f, f2, f6).color(color);
                break;
            }
            case 3: {
                batchedBuffer.vertex(f, f2, f3).color(color);
                batchedBuffer.vertex(f4, f2, f3).color(color);
                batchedBuffer.vertex(f4, f5, f3).color(color);
                BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(f, f5, f3).color(color);
                break;
            }
            case 4: {
                batchedBuffer.vertex(f, f2, f6).color(color);
                batchedBuffer.vertex(f4, f2, f6).color(color);
                batchedBuffer.vertex(f4, f5, f6).color(color);
                BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(f, f5, f6).color(color);
                break;
            }
            case 5: {
                batchedBuffer.vertex(f, f2, f3).color(color);
                batchedBuffer.vertex(f, f2, f6).color(color);
                batchedBuffer.vertex(f, f5, f6).color(color);
                BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(f, f5, f3).color(color);
                break;
            }
            case 6: {
                batchedBuffer.vertex(f4, f2, f3).color(color);
                batchedBuffer.vertex(f4, f2, f6).color(color);
                batchedBuffer.vertex(f4, f5, f6).color(color);
                BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(f4, f5, f3).color(color);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    public final void drawFilledBox(@NotNull BatchedBuffer batchedBuffer, @NotNull Box box, @NotNull Color color, @NotNull Color color2) {
        Intrinsics.checkNotNullParameter(batchedBuffer, "polygon");
        Intrinsics.checkNotNullParameter(box, "box");
        Intrinsics.checkNotNullParameter(color, "c");
        Intrinsics.checkNotNullParameter(color2, "c1");
        if (!RenderState.INSTANCE.getFrustum().isVisible(box)) {
            return;
        }
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "position(...)");
        Vec3d vec3d2 = vec3d;
        float f = (float)(box.minX - vec3d2.getX());
        float f2 = (float)(box.minY - vec3d2.getY());
        float f3 = (float)(box.minZ - vec3d2.getZ());
        float f4 = (float)(box.maxX - vec3d2.getX());
        float f5 = (float)(box.maxY - vec3d2.getY());
        float f6 = (float)(box.maxZ - vec3d2.getZ());
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f, f5, f3).color(color2);
        batchedBuffer.vertex(f4, f5, f3).color(color2);
        batchedBuffer.vertex(f4, f2, f3).color(color);
        batchedBuffer.vertex(f4, f2, f3).color(color);
        batchedBuffer.vertex(f4, f5, f3).color(color2);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
        batchedBuffer.vertex(f4, f2, f6).color(color);
        batchedBuffer.vertex(f, f2, f6).color(color);
        batchedBuffer.vertex(f4, f2, f6).color(color);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
        batchedBuffer.vertex(f, f5, f6).color(color2);
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f, f2, f6).color(color);
        batchedBuffer.vertex(f, f5, f6).color(color2);
        batchedBuffer.vertex(f, f5, f3).color(color2);
        batchedBuffer.vertex(f, f5, f3).color(color2);
        batchedBuffer.vertex(f, f5, f6).color(color2);
        batchedBuffer.vertex(f4, f5, f6).color(color2);
        batchedBuffer.vertex(f4, f5, f3).color(color2);
        batchedBuffer.vertex(f, f2, f3).color(color);
        batchedBuffer.vertex(f, f2, f6).color(color);
        batchedBuffer.vertex(f4, f2, f6).color(color);
        batchedBuffer.vertex(f4, f2, f3).color(color);
    }

    private final void ensureFramebuffer() {
        block5: {
            block4: {
                if (glowFramebuffer == null) {
                    glowFramebuffer = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                }
                int n = Globals.getMc().getFramebuffer().textureWidth;
                WindowFramebuffer windowFramebuffer = glowFramebuffer;
                Intrinsics.checkNotNull(windowFramebuffer);
                if (n != windowFramebuffer.textureWidth) break block4;
                int n2 = Globals.getMc().getFramebuffer().textureHeight;
                WindowFramebuffer windowFramebuffer2 = glowFramebuffer;
                Intrinsics.checkNotNull(windowFramebuffer2);
                if (n2 == windowFramebuffer2.textureHeight) break block5;
            }
            WindowFramebuffer windowFramebuffer = glowFramebuffer;
            Intrinsics.checkNotNull(windowFramebuffer);
            windowFramebuffer.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        WindowFramebuffer windowFramebuffer = glowFramebuffer;
        Intrinsics.checkNotNull(windowFramebuffer);
        GpuTexture gpuTexture = windowFramebuffer.getColorAttachment();
        Intrinsics.checkNotNull(gpuTexture);
        WindowFramebuffer windowFramebuffer3 = glowFramebuffer;
        Intrinsics.checkNotNull(windowFramebuffer3);
        GpuTexture gpuTexture2 = windowFramebuffer3.getDepthAttachment();
        Intrinsics.checkNotNull(gpuTexture2);
        commandEncoder.clearColorAndDepthTextures(gpuTexture, 0, gpuTexture2, 1.0);
    }

    private final void ensureGlowFramebuffer() {
        block5: {
            block4: {
                if (framebuffer == null) {
                    framebuffer = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                }
                int n = Globals.getMc().getFramebuffer().textureWidth;
                WindowFramebuffer windowFramebuffer = framebuffer;
                Intrinsics.checkNotNull(windowFramebuffer);
                if (n != windowFramebuffer.textureWidth) break block4;
                int n2 = Globals.getMc().getFramebuffer().textureHeight;
                WindowFramebuffer windowFramebuffer2 = framebuffer;
                Intrinsics.checkNotNull(windowFramebuffer2);
                if (n2 == windowFramebuffer2.textureHeight) break block5;
            }
            WindowFramebuffer windowFramebuffer = framebuffer;
            Intrinsics.checkNotNull(windowFramebuffer);
            windowFramebuffer.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        WindowFramebuffer windowFramebuffer = framebuffer;
        Intrinsics.checkNotNull(windowFramebuffer);
        GpuTexture gpuTexture = windowFramebuffer.getColorAttachment();
        Intrinsics.checkNotNull(gpuTexture);
        WindowFramebuffer windowFramebuffer3 = framebuffer;
        Intrinsics.checkNotNull(windowFramebuffer3);
        GpuTexture gpuTexture2 = windowFramebuffer3.getDepthAttachment();
        Intrinsics.checkNotNull(gpuTexture2);
        commandEncoder.clearColorAndDepthTextures(gpuTexture, 0, gpuTexture2, 1.0);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        RenderQueue.a = ZkmKeys.a(-6327522248767712995L, 7685745101805475913L, MethodHandles.lookup().lookupClass()).a(132900495710087L);
                        RenderQueue.d = new HashMap<K, V>(13);
                        var11 = RenderQueue.a ^ 114148618066593L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[13];
                        var18_4 = 0;
                        var17_5 = "\u001a\u00a4\u0013\u00a7\u00a6\u00a9\u009er&'\u009a\u00e2 \u00bcB\u00a7\u00ca\u0004\u00ad\u00c99B\u00af48U\u008eB\u00d9J\u0089~\u0098\u00a7>iqI'D\u0016\u0084>^\u001f`K\u00d8\u00b1+\u0000)>'\u00f8O\u00de\u00c2\u00bb\u00fe\u0082\u00e9O,\u00e9n<*\u00e4\u00c0m9\u0010\u009f\u0090\u0016\u009d\u00afyH\u0090\u0010\u00f8\u00de\u00c7`:\u00fbs\u009c\u00895xu\u00be\u00dc\u00dcS \u00cd\u001e\u007f\u0090\u0089\u0093]\u0087n\u0093\u00ce\u00b5\r\u00c0\u00c7\u0091W\u0092g-\u0098\u0089G\u00de\u0000\u00c1\u00b3u\u00fd\u00afK\u0018\u0018^R\u001e3\u00e7\u0096\u00b3\u00e3\u00e4\u007f\u00e4\u00b1\b!\r\u0083\u00cd\u008f\u0084\u0012\u00f7\u00c05\u00a4\u0010\u00be\"\u00ebN\u0015KN\u0016\u00b8\u0011\u0083\u00d8\u001c9\u00aa\u001a\u0010\u0093\u008d\u00e6\u00f9\u000f\u009fO\u00c9\u009d\u0003\u00a7\u00cdx\u00bd\u0001\u000e\u0018\u00fb\u00ec\u0082\u0090\u00f7\u0014\u00a1\u008a\u00baI!>\u0091WkX\u0013\u00ef?\u0094\u00bb\u0015\u0004r8|)\u00b1r\u00d1\u00bd}\u00b8\u0099\u001f\u00b2\u00a2\u00b3T?\u00f6Kp\u00df)\u00f2=\\\u00a6\u00e1843\u0098}\u0002\u00c8\u009b\u009cf\u00caCh\u0081#\u00d0\u00c9N\u00f3(\u0090$\u00e0{\u001e\u0098\u00a4\u00d7\u00e2\u00d4K \u00b4\u00b1\u00fd\u0089\u00a65\u00c8\u00d8\u00f5\u00a2\u0086\u00af'\u009d\u00e0\u00c6\u00c1\u00ed\u00a0\u00fc+\u001c\u00cb\u00a1\u00a6\u0016s\u001c\u00c0\u0092k\u001e\u0010\u00f9\u0011\u00ef\u00e9S\u00d1K\u00e3\u0019\u00acuA\u00f4\u00d3\u00ec\u001f";
                        var19_6 = 322;
                        var16_7 = 24;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = RenderQueue.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0080\u00f5\u00b5\u0090T\u00ee\u008a\u0010\u00c7\u0098C\u00ea[{0:\u0018=\u00c92\u00cbY\u0091w\u00f5\u00d0\u00a6\u0093\u00b6n4\u00e4\u00b8\u0081\u0015\u00b5\u00ae\u00c7\u00c6\u00dc\u0011";
                            var19_6 = 41;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = RenderQueue.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                RenderQueue.b = var20_3;
                RenderQueue.c = new String[13];
                RenderQueue.i = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[19];
                var3_13 = 0;
                var4_14 = "\u0011\u008b!O_\u00f3*e\u00ce\u0098\u0011Y%B@\u001a\u00c9Y\u0005%\u00a7\u00fcZP\u00cf\u00ba\"d\u0090\u00ee\u00b26\f\u0088\u00c8i\u00c7Ut\u0095\u0007\u00a36MD\u00ec\u009e\u0099z>bi\u00d8\u001eT\u0098\u00ac\u00df\u00fepz\u00f4F\u0095r\u008b\u0092D{\u00b7ZB\u00d5\u00dd\"\u009d\u00af\u00b7a\u00c8\u008e\u00f0)\u007f\u00cb\u008e\u0011u\u00f0\u00b5r\u00f7WC\u00a94\u00d2e\u00bc`\u008e1\u00af\u00a9\u0014KsJ(\u00a2\u009fr~\u00e6\u00f2\u0081!O\u009b\u00a8u\u00f1Y\u0088\u0017\u00fa\u0093^X\u00e64\u001f\u00b4w\u00e7\u00e4";
                var5_15 = 136;
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "S\u00fflI\u00d6*\u008b\u00ceA\u0003,\u001d\u00de>\u00aar";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        RenderQueue.f = var6_12;
        RenderQueue.h = new Integer[19];
        RenderQueue.INSTANCE = new RenderQueue();
        RenderQueue.filledBoxes = new ArrayList<E>();
        RenderQueue.boxes = new ArrayList<E>();
        RenderQueue.F = new ArrayList<E>();
        RenderQueue.faces = new ArrayList<E>();
        RenderQueue.faceOutlines = new ArrayList<E>();
        RenderQueue.lines = new ArrayList<E>();
        RenderQueue.K = new ArrayList<E>();
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6EA0;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/cz", exception);
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
            RenderQueue.c[n2] = RenderQueue.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RenderQueue.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/cz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x27BA;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/cz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RenderQueue.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RenderQueue.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/cz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RenderQueue.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RenderQueue.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

