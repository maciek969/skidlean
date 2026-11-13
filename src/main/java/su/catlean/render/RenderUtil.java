/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector2f
 *  org.joml.Vector3f
 *  org.joml.Vector4d
 *  org.joml.Vector4f
 *  org.lwjgl.system.MemoryUtil
 */
package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.IntBuffer;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.render.FlipFrameEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.RenderScreenEvent;
import su.catlean.gofra.Flow;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.mixins.accessors.NativeImageAccessor;
import su.catlean.module.Module;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BlurPass;
import su.catlean.render.Pipelines;
import su.catlean.render.Rect;
import su.catlean.render.RenderState;
import su.catlean.render.ScissorStack;
import su.catlean.render.UniformStorage;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.render.uniform.BlurUniformStorage;
import su.catlean.render.uniform.BufferUniformStorage;
import su.catlean.render.uniform.ChamsUniformStorageA;
import su.catlean.render.uniform.ChamsUniformStorageC;
import su.catlean.render.uniform.ChamsUniformStorageD;
import su.catlean.render.uniform.ChamsUniformStorageE;
import su.catlean.render.uniform.GradientUniformStorage;
import su.catlean.render.uniform.ShapeUniformStorage;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class RenderUtil {
    @NotNull
    public static final RenderUtil INSTANCE;
    @NotNull
    private static final CopyOnWriteArrayList renderTasks;
    private static boolean d;
    @NotNull
    private static final Matrix4f r;
    @NotNull
    private static final Matrix4f V;
    @NotNull
    private static int[] M;
    private static int w;
    private static float X;
    private static Module[] v;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private RenderUtil() {
    }

    public final boolean V() {
        return d;
    }

    public final void h(boolean bl) {
        d = bl;
    }

    @NotNull
    public final Matrix4f b() {
        return r;
    }

    @NotNull
    public final Matrix4f m() {
        return V;
    }

    @NotNull
    public final int[] u() {
        return M;
    }

    public final void p(@NotNull int[] nArray) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(nArray, (String)((Object)RenderUtil.zkmBootstrap("u", (int)31528, (long)(0x6C2C3E17819394FAL ^ l))));
        M = nArray;
    }

    public final float t() {
        return X;
    }

    public final void m(float f) {
        X = f;
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        int n = Globals.getMc().getWindow().getScaleFactor();
        Object var8_3 = null;
        if (n != w) {
            Iterable iterable = Fonts.b();
            for (Object t : iterable) {
                FontRenderer fontRenderer = (FontRenderer)t;
                fontRenderer.close();
                fontRenderer.updateScale();
            }
            w = n;
        }
    }

    @Flow(priority=20)
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l = a ^ 0x412F90712659L;
        long l2 = l ^ 0x5DE98D8577EAL;
        BlurPass.INSTANCE.shouldUpdate(l2);
        Iterable iterable = renderTasks;
        Iterator iterator2 = iterable.iterator();
        Object var6_6 = null;
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            Function0 function0 = (Function0)t;
            function0.invoke();
        }
        renderTasks.clear();
    }

    @Flow(priority=-20)
    private final void onRenderScreen(RenderScreenEvent renderScreenEvent) {
        if (d) {
            GlStateManager._glBindFramebuffer(36160, 0);
            d = false;
        }
    }

    @Flow
    private final void onRenderScreen2(RenderScreenEvent renderScreenEvent) {
        long l = a ^ 0x1BDA2321D552L;
        long l2 = l ^ 0xCB6A32AC3A4L;
        if (Globals.getMc().currentScreen instanceof ClickGuiScreen && ClickGuiScreen.INSTANCE.getComponent() == null) {
            ClickGuiScreen.INSTANCE.renderBackground(renderScreenEvent.getContext(), l2);
        }
    }

    @Flow
    private final void onFlipFrame(FlipFrameEvent flipFrameEvent) {
        ShapeUniformStorage.INSTANCE.getDynamicUniformStorage().clear();
        BlurUniformStorage.INSTANCE.getDynamicUniformStorage().clear();
        GradientUniformStorage.INSTANCE.getDynamicUniformStorage().clear();
        BufferUniformStorage.INSTANCE.getDynamicUniformStorage().clear();
        ChamsUniformStorageD.INSTANCE.getDynamicUniformStorage().clear();
        ChamsUniformStorageE.INSTANCE.getDynamicUniformStorage().clear();
        ChamsUniformStorageC.INSTANCE.getDynamicUniformStorage().clear();
        ChamsUniformStorageA.INSTANCE.getDynamicUniformStorage().clear();
        UniformStorage.INSTANCE.getDynamicUniformStorage().clear();
    }

    public final void gradientQuad(@NotNull BatchedBuffer batchedBuffer, float f, float f2, float f3, float f4, @NotNull Color[] colorArray) {
        Intrinsics.checkNotNullParameter(batchedBuffer, "polygon");
        Object var11_7 = null;
        Intrinsics.checkNotNullParameter(colorArray, "colors");
        BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(batchedBuffer, f, f2 + f4, 0.0f, null).color(colorArray[0]), f + f3, f2 + f4, 0.0f, null).color(colorArray.length > 1 ? colorArray[1] : colorArray[0]), f + f3, f2, 0.0f, null).color(colorArray.length > 1 ? colorArray[2] : colorArray[0]), f, f2, 0.0f, null).color(colorArray.length > 1 ? colorArray[3] : colorArray[0]);
    }

    public final void drawGradient(@NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, @NotNull Color[] colorArray, long l) {
        long l2 = (l = a ^ l) ^ 0x402C2A2777C6L;
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "matrices");
        Intrinsics.checkNotNullParameter(colorArray, "colors");
        Object var22_9 = null;
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 6;
        boolean bl = false;
        int n2 = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer.draw$default(l2, BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker), f, f2 + f4, 0.0f, null).color(colorArray[0]), f + f3, f2 + f4, 0.0f, null).color(colorArray.length > 1 ? colorArray[1] : colorArray[0]), f + f3, f2, 0.0f, null).color(colorArray.length > 1 ? colorArray[2] : colorArray[0]), f, f2, 0.0f, null).color(colorArray.length > 1 ? colorArray[3] : colorArray[0]), Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, null, new Matrix3x2f((Matrix3x2fc)matrix3x2fStack), null, 46, null);
    }

    public final void drawRect(@Nullable Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, long l, @NotNull Color color) {
        long l2 = (l = a ^ l) ^ 0x2C5D18E84EB2L;
        Intrinsics.checkNotNullParameter(color, "c");
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer.draw$default(l2, BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(new BatchedBuffer(vertexFormat2, 4, true), f, f2, 0.0f, null).color(color), f, f2 + f4, 0.0f, null).color(color), f, f2 + f4, 0.0f, null).color(color), f + f3, f2 + f4, 0.0f, null).color(color), f + f3, f2 + f4, 0.0f, null).color(color), f + f3, f2, 0.0f, null).color(color), f + f3, f2, 0.0f, null).color(color), f, f2, 0.0f, null).color(color), Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, null, new Matrix3x2f((Matrix3x2fc)matrix3x2fStack), null, 46, null);
    }

    public final void beginScissor(@NotNull DrawContext drawContext, @NotNull Rect rect) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(rect, "r1");
        Vector2f vector2f = new Vector2f();
        Vector2f vector2f2 = new Vector2f();
        drawContext.getMatrices().transformPosition(rect.getX(), rect.getY(), vector2f);
        drawContext.getMatrices().transformPosition(rect.getX1(), rect.getY1(), vector2f2);
        drawContext.enableScissor((int)vector2f.x, (int)vector2f.y, (int)vector2f2.x, (int)vector2f2.y);
        ScissorStack.INSTANCE.push(new Rect(vector2f.x, vector2f.y, vector2f2.x, vector2f2.y));
    }

    public final void endScissor(@NotNull DrawContext drawContext) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
        drawContext.disableScissor();
        ScissorStack.INSTANCE.pop();
    }

    private final void quad(BatchedBuffer batchedBuffer, float f, float f2, float f3, float f4, Color color) {
        batchedBuffer.vertex(f, f2, 0.0f).color(color);
        batchedBuffer.vertex(f, f4, 0.0f).color(color);
        batchedBuffer.vertex(f3, f4, 0.0f).color(color);
        batchedBuffer.vertex(f3, f2, 0.0f).color(color);
    }

    public final void drawTexture(@NotNull Matrix3x2f matrix3x2f, @NotNull Identifier identifier, float f, float f2, float f3, float f4, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, float f5, float f6, float f7, float f8, boolean bl, long l) {
        long l2 = (l = a ^ l) ^ 0x3597E37DC0B8L;
        Intrinsics.checkNotNullParameter(matrix3x2f, "matrices");
        Intrinsics.checkNotNullParameter(identifier, "id");
        Intrinsics.checkNotNullParameter(color, "color1");
        Intrinsics.checkNotNullParameter(color2, "color2");
        Intrinsics.checkNotNullParameter(color3, "color3");
        Intrinsics.checkNotNullParameter(color4, "color4");
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(identifier).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        Object var30_19 = null;
        CallSite callSite = RenderUtil.zkmBootstrap2("y", (int)26960, (long)(0x181EF97B8393B023L ^ l));
        boolean bl2 = false;
        int n = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n, bl2, (int)callSite, var30_19);
        BatchedBuffer.vertex$default(batchedBuffer, f + 0.0f, f2 + f4, 0.0f, null).texture(f5, f8).color(color);
        BatchedBuffer.vertex$default(batchedBuffer, f + f3, f2 + f4, 0.0f, null).texture(f7, f8).color(color2);
        BatchedBuffer.vertex$default(batchedBuffer, f + f3, f2 + 0.0f, 0.0f, null).texture(f7, f6).color(color3);
        BatchedBuffer.vertex$default(batchedBuffer, f + 0.0f, f2 + 0.0f, 0.0f, null).texture(f5, f6).color(color4);
        RenderPipeline renderPipeline = bl ? Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend() : Pipelines.INSTANCE.getProgramDefaultColoredTexture();
        Framebuffer framebuffer = Globals.getMc().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
        var30_19 = null;
        callSite = RenderUtil.zkmBootstrap2("y", (int)13540, (long)(0x3DEBD8FC14556D82L ^ l));
        Map<String, GpuTextureView> map = MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2));
        Matrix3x2f matrix3x2f2 = matrix3x2f;
        vertexFormat2 = null;
        GpuBufferSlice gpuBufferSlice = null;
        Framebuffer framebuffer2 = framebuffer;
        RenderPipeline renderPipeline2 = renderPipeline;
        BatchedBuffer batchedBuffer2 = batchedBuffer;
        BatchedBuffer.draw$default(l2, batchedBuffer2, renderPipeline2, framebuffer2, gpuBufferSlice, (Matrix4f)vertexFormat2, matrix3x2f2, map, (int)callSite, var30_19);
    }

    public static void drawTexture$default(RenderUtil renderUtil, Matrix3x2f matrix3x2f, Identifier identifier, float f, float f2, float f3, float f4, Color color, Color color2, int n, Color color3, Color color4, float f5, char c, float f6, float f7, short s, float f8, boolean bl, int n2, Object object) {
        long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)s << 48 >>> 48) ^ a;
        long l2 = l ^ 0x5758E34785B7L;
        Module[] moduleArray = RenderUtil.zkmGetFlag();
        if ((n2 & RenderUtil.zkmBootstrap2("y", (int)23733, (long)(0x46289BBF897BC16L ^ l))) != 0) {
            Color color5 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color5, (String)((Object)RenderUtil.zkmBootstrap("u", (int)8403, (long)(0x168648696F50EE52L ^ l))));
            color = color5;
        }
        int n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)2597, (long)(0x1B7754302A0E6A87L ^ l));
        Module[] moduleArray2 = moduleArray;
        if (c >= '\u0000') {
            if (n3 != 0) {
                color2 = color;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)4408, (long)(0x5EDF1FA66826F186L ^ l));
            moduleArray2 = moduleArray;
        }
        if (n >= 0) {
            if (n3 != 0) {
                color3 = color2;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)30957, (long)(0x1744D7C47740985BL ^ l));
            moduleArray2 = moduleArray;
        }
        if (c >= '\u0000') {
            if (n3 != 0) {
                color4 = color3;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)3554, (long)(0x3A0867BF282D6D48L ^ l));
            moduleArray2 = moduleArray;
        }
        if (c > '\u0000') {
            if (n3 != 0) {
                f5 = 0.0f;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)28013, (long)(0x7CA7D96E74D20DCAL ^ l));
            moduleArray2 = moduleArray;
        }
        if (s > 0) {
            if (n3 != 0) {
                f6 = 0.0f;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)23570, (long)(0x3E3BDEE1EF053CAAL ^ l));
            moduleArray2 = moduleArray;
        }
        if (c >= '\u0000') {
            if (n3 != 0) {
                f7 = 1.0f;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)17975, (long)(0x1EEB1FB8ABCDA685L ^ l));
            moduleArray2 = moduleArray;
        }
        if (n > 0) {
            if (n3 != 0) {
                f8 = 1.0f;
            }
            n3 = n2 & RenderUtil.zkmBootstrap2("y", (int)24363, (long)(0x1743096453143F86L ^ l));
            moduleArray2 = moduleArray;
        }
        if (n3 != 0) {
            bl = false;
        }
        renderUtil.drawTexture(matrix3x2f, identifier, f, f2, f3, f4, color, color2, color3, color4, f5, f6, f7, f8, bl, l2);
    }

    public final void texturedQuad(@NotNull BatchedBuffer batchedBuffer, float f, float f2, float f3, float f4, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, float f5, float f6, float f7, float f8) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(batchedBuffer, (String)((Object)RenderUtil.zkmBootstrap("u", (int)31150, (long)(0x4E1D0D827398BACAL ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)RenderUtil.zkmBootstrap("u", (int)305, (long)(0x7FA1AB8E93F0C26BL ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RenderUtil.zkmBootstrap("u", (int)27319, (long)(0x4B1DED5BACF329D8L ^ l))));
        Intrinsics.checkNotNullParameter(color3, (String)((Object)RenderUtil.zkmBootstrap("u", (int)26767, (long)(0x564F1FD4EF6C2BE5L ^ l))));
        Intrinsics.checkNotNullParameter(color4, (String)((Object)RenderUtil.zkmBootstrap("u", (int)27387, (long)(0x5A0A9653B5F0298DL ^ l))));
        BatchedBuffer.vertex$default(batchedBuffer, f + 0.0f, f2 + f4, 0.0f, null).texture(f5, f8).color(color);
        BatchedBuffer.vertex$default(batchedBuffer, f + f3, f2 + f4, 0.0f, null).texture(f7, f8).color(color2);
        BatchedBuffer.vertex$default(batchedBuffer, f + f3, f2 + 0.0f, 0.0f, null).texture(f7, f6).color(color3);
        BatchedBuffer.vertex$default(batchedBuffer, f + 0.0f, f2 + 0.0f, 0.0f, null).texture(f5, f6).color(color4);
    }

    public static void texturedQuad$default(RenderUtil renderUtil, BatchedBuffer batchedBuffer, float f, float f2, float f3, float f4, Color color, Color color2, Color color3, Color color4, float f5, float f6, float f7, long l, float f8, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-1102968877069820240L, (long)l);
        int n2 = n & RenderUtil.zkmBootstrap2("y", (int)2441, (long)(0x1F0DC988F15537F3L ^ l));
        if (callSite == null) {
            if (n2 != 0) {
                Color color5 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(color5, (String)((Object)RenderUtil.zkmBootstrap("u", (int)7819, (long)(0x4AB1E5334F3A8ECCL ^ l))));
                color = color5;
            }
            n2 = n & RenderUtil.zkmBootstrap2("y", (int)2475, (long)(0x6C919E5A56A137D9L ^ l));
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color2 = color;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)1453, (long)(0x499CA0EB97753BCEL ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color3 = color2;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)16873, (long)(0x52B3099E5E22FF86L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color4 = color3;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)13952, (long)(0x6041541803CF88E9L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f5 = 0.0f;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)30700, (long)(0x50C214D8E0C74991L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f6 = 0.0f;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)11242, (long)(0xB0D3C28BCD51596L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                f7 = 1.0f;
            }
            n2 = n & RenderUtil.zkmBootstrap2("y", (int)7881, (long)(0x7EBC382E875A0B1L ^ l));
        }
        if (n2 != 0) {
            f8 = 1.0f;
        }
        renderUtil.texturedQuad(batchedBuffer, f, f2, f3, f4, color, color2, color3, color4, f5, f6, f7, f8);
    }

    public final void drawTexture3D(@NotNull Matrix4f matrix4f, @NotNull Identifier identifier, float f, float f2, float f3, float f4, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, boolean bl, float f5, float f6, float f7, long l, float f8) {
        long l2 = (l = a ^ l) ^ 0x403AD93B8A62L;
        Intrinsics.checkNotNullParameter(matrix4f, "matrices");
        Intrinsics.checkNotNullParameter(identifier, "id");
        Intrinsics.checkNotNullParameter(color, "color1");
        Intrinsics.checkNotNullParameter(color2, "color2");
        Intrinsics.checkNotNullParameter(color3, "color3");
        Intrinsics.checkNotNullParameter(color4, "color4");
        float f9 = f4 / 2.0f;
        float f10 = f3 / 2.0f;
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(identifier).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        Object var30_21 = null;
        int n = 6;
        boolean bl2 = false;
        int n2 = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl2, n, var30_21);
        BatchedBuffer.vertex$default(batchedBuffer, f - f9, f2 + f9, 0.0f, null).texture(f5, f8).color(color);
        BatchedBuffer.vertex$default(batchedBuffer, f + f10, f2 + f9, 0.0f, null).texture(f7, f8).color(color2);
        BatchedBuffer.vertex$default(batchedBuffer, f + f10, f2 - f9, 0.0f, null).texture(f7, f6).color(color3);
        BatchedBuffer.vertex$default(batchedBuffer, f - f10, f2 - f9, 0.0f, null).texture(f5, f6).color(color4);
        RenderPipeline renderPipeline = bl ? Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend() : Pipelines.INSTANCE.getProgramDefaultColoredTexture();
        Framebuffer framebuffer = Globals.getMc().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
        var30_21 = null;
        n = 4;
        Map<String, GpuTextureView> map = MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2));
        Matrix3x2f matrix3x2f = (Matrix3x2f)new Matrix3x2fStack();
        vertexFormat2 = matrix4f;
        GpuBufferSlice gpuBufferSlice = null;
        Framebuffer framebuffer2 = framebuffer;
        RenderPipeline renderPipeline2 = renderPipeline;
        BatchedBuffer batchedBuffer2 = batchedBuffer;
        BatchedBuffer.draw$default(l2, batchedBuffer2, renderPipeline2, framebuffer2, gpuBufferSlice, (Matrix4f)vertexFormat2, matrix3x2f, map, n, var30_21);
    }

    public static void drawTexture3D$default(RenderUtil renderUtil, Matrix4f matrix4f, Identifier identifier, float f, float f2, float f3, long l, float f4, Color color, Color color2, Color color3, Color color4, boolean bl, float f5, float f6, float f7, float f8, int n, Object object) {
        long l2 = (l = a ^ l) ^ 0x52C11118DA44L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4278169351745951070L, (long)l);
        int n2 = n & RenderUtil.zkmBootstrap2("y", (int)23733, (long)(0x462F98F308EA93FL ^ l));
        if (callSite == null) {
            if (n2 != 0) {
                Color color5 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(color5, (String)((Object)RenderUtil.zkmBootstrap("u", (int)8403, (long)(0x1686385DA749FB7BL ^ l))));
                color = color5;
            }
            n2 = n & RenderUtil.zkmBootstrap2("y", (int)2597, (long)(0x1B772404E2177FAEL ^ l));
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color2 = color;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)4408, (long)(0x5EDF6F92A03FE4AFL ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color3 = color2;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)30957, (long)(0x1744A7F0BF598D72L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color4 = color3;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)3554, (long)(0x3A08178BE0347861L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    bl = false;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)28013, (long)(0x7CA7A95ABCCB18E3L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f5 = 0.0f;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)23570, (long)(0x3E3BAED5271C2983L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f6 = 0.0f;
                }
                n2 = n & RenderUtil.zkmBootstrap2("y", (int)29626, (long)(0x2C42294401020606L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                f7 = 1.0f;
            }
            n2 = n & RenderUtil.zkmBootstrap2("y", (int)10627, (long)(0xC364B35FC0DDC03L ^ l));
        }
        if (n2 != 0) {
            f8 = 1.0f;
        }
        renderUtil.drawTexture3D(matrix4f, identifier, f, f2, f3, f4, color, color2, color3, color4, bl, f5, f6, f7, l2, f8);
    }

    public final void drawGradientRect(@NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, long l, float f4, @NotNull Color color, int n, @NotNull Color color2) {
        long l2 = (l << 32 | (long)n << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x59DAB96CD7D0L;
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "matrices");
        Intrinsics.checkNotNullParameter(color, (String)((Object)RenderUtil.zkmBootstrap("u", (int)20066, (long)(0x5B6B8DCF1BCC2E49L ^ l2))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RenderUtil.zkmBootstrap("u", (int)27319, (long)(0x4B1D82A00EE68A9EL ^ l2))));
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n2 = 2;
        boolean bl = true;
        int n3 = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer.draw$default(l3, BatchedBuffer.vertex$default(BatchedBuffer.vertex$default(new BatchedBuffer(vertexFormat2, n3, bl, n2, defaultConstructorMarker), f, f2, 0.0f, null).color(color), f3, f4, 0.0f, null).color(color2), Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, null, new Matrix3x2f((Matrix3x2fc)matrix3x2fStack), null, (int)RenderUtil.zkmBootstrap2("y", (int)22086, (long)(0x379BCE26F482984FL ^ l2)), null);
    }

    public static void drawGradientRect$default(long l, RenderUtil renderUtil, Matrix3x2fStack matrix3x2fStack, float f, float f2, int n, float f3, float f4, Color color, Color color2, int n2, Object object) {
        long l2 = (l << 32 | (long)n << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x6DFF984F3B66L;
        long l4 = l3 >>> 32;
        int n3 = (int)(l3 << 32 >>> 32);
        if ((n2 & RenderUtil.zkmBootstrap2("y", (int)23733, (long)(0x462DF51D98E15AFL ^ l2))) != 0) {
            color2 = color;
        }
        renderUtil.drawGradientRect(matrix3x2fStack, f, f2, f3, l4, f4, color, n3, color2);
    }

    private final int lerp(int n, int n2, float f) {
        return MathKt.roundToInt((float)n + (float)(n2 - n) * f);
    }

    public final double lerp(double d, double d2, float f) {
        return d + (d2 - d) * (double)f;
    }

    public final double lerp(double d, double d2, double d3) {
        return d + (d2 - d) * d3;
    }

    public final float lerp(float f, float f2, float f3) {
        return f + (f2 - f) * f3;
    }

    @NotNull
    public final Vec3d toCameraRelative(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Object var6_2 = null;
        Object object = Globals.getMc().getEntityRenderDispatcher().camera;
        if (object == null || (object = ((Camera)object).getCameraPos()) == null) {
            Vec3d vec3d2 = Vec3d.ZERO;
            object = vec3d2;
            Intrinsics.checkNotNullExpressionValue(vec3d2, "ZERO");
        }
        Object object2 = object;
        int n = Globals.getMc().getWindow().getHeight() * (this.getGpuVendor() ? 2 : 1);
        Vector3f vector3f = new Vector3f();
        double d = vec3d.x - ((Vec3d)object2).x;
        double d2 = vec3d.y - ((Vec3d)object2).y;
        double d3 = vec3d.z - ((Vec3d)object2).z;
        Vector4f vector4f = new Vector4f((float)d, (float)d2, (float)d3, 1.0f).mul((Matrix4fc)V);
        Matrix4f matrix4f = new Matrix4f((Matrix4fc)RenderState.INSTANCE.getProjection());
        Matrix4f matrix4f2 = new Matrix4f((Matrix4fc)r);
        matrix4f.mul((Matrix4fc)matrix4f2).project(vector4f.x(), vector4f.y(), vector4f.z(), M, vector3f);
        return new Vec3d((double)vector3f.x / (double)Globals.getMc().getWindow().getScaleFactor(), (double)((float)n - vector3f.y) / (double)Globals.getMc().getWindow().getScaleFactor(), vector3f.z);
    }

    public final void renderItem(@NotNull MatrixStack matrixStack, @NotNull ItemStack itemStack) {
        block4: {
            Intrinsics.checkNotNullParameter(matrixStack, "matrices");
            Object var7_3 = null;
            Intrinsics.checkNotNullParameter(itemStack, "stack");
            if (itemStack.isEmpty()) break block4;
            matrixStack.push();
            matrixStack.translate(8.0f, 8.0f, 150.0f);
            try {
                matrixStack.scale(16.0f, -16.0f, 16.0f);
                ItemRenderState itemRenderState = new ItemRenderState();
                Globals.getMc().getItemModelManager().clearAndUpdate(itemRenderState, itemStack, ItemDisplayContext.GUI, Globals.getWorld(), null, 0);
                itemRenderState.render(matrixStack, Globals.getMc().gameRenderer.getEntityRenderCommandQueue(), 0xF000F0, OverlayTexture.DEFAULT_UV, 0);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            matrixStack.pop();
        }
    }

    public final void registerTexture(@NotNull Identifier identifier, @NotNull BufferedImage bufferedImage) {
        Intrinsics.checkNotNullParameter(identifier, "i");
        Intrinsics.checkNotNullParameter(bufferedImage, "bi");
        if (RenderSystem.isOnRenderThread()) {
            Globals.getMc().getTextureManager().registerTexture(identifier, this.toNativeImage(bufferedImage));
        } else {
            Function0<Unit> function0 = () -> RenderUtil.registerTexture$lambda$0(identifier, bufferedImage);
            this.queueRenderTask(function0);
        }
    }

    public final boolean queueRenderTask(@NotNull Function0 function0) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)RenderUtil.zkmBootstrap("u", (int)28391, (long)(0x667F63CDA787631FL ^ l))));
        return renderTasks.add(function0);
    }

    @NotNull
    public final NativeImageBackedTexture toNativeImage(@NotNull BufferedImage bufferedImage) {
        Intrinsics.checkNotNullParameter(bufferedImage, "bi");
        int n = bufferedImage.getWidth();
        int n2 = bufferedImage.getHeight();
        NativeImage nativeImage = new NativeImage(NativeImage.Format.RGBA, n, n2, false);
        long l = ((NativeImageAccessor)((Object)nativeImage)).getPixels();
        IntBuffer intBuffer = MemoryUtil.memIntBuffer((long)l, (int)(nativeImage.getWidth() * nativeImage.getHeight()));
        WritableRaster writableRaster = bufferedImage.getRaster();
        ColorModel colorModel = bufferedImage.getColorModel();
        int n3 = writableRaster.getNumBands();
        int n4 = writableRaster.getDataBuffer().getDataType();
        Object[] objectArray = switch (n4) {
            case 0 -> new byte[n3];
            case 1 -> (Object[])new short[n3];
            case 3 -> (Object[])new int[n3];
            case 4 -> (Object[])new float[n3];
            case 5 -> (Object[])new double[n3];
            default -> throw new IllegalArgumentException("Unknown data buffer type: " + n4);
        };
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                writableRaster.getDataElements(j, i, objectArray);
                int n5 = colorModel.getAlpha(objectArray);
                int n6 = colorModel.getRed(objectArray);
                int n7 = colorModel.getGreen(objectArray);
                int n8 = colorModel.getBlue(objectArray);
                int n9 = n5 << 24 | n8 << 16 | n7 << 8 | n6;
                intBuffer.put(n9);
            }
        }
        Supplier<String> supplier = () -> RenderUtil.getTextureId(bufferedImage);
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(supplier, nativeImage);
        nativeImageBackedTexture.upload();
        return nativeImageBackedTexture;
    }

    @Nullable
    public final Vector4d worldToScreen(@NotNull Entity entity, float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(entity, (String)((Object)RenderUtil.zkmBootstrap("u", (int)17536, (long)(0x5A94B6A922FF19E7L ^ l))));
        return this.worldToScreen(this.lerp(entity.lastX, entity.getX(), this.getTickDelta()), this.lerp(entity.lastY, entity.getY(), this.getTickDelta()) + (double)entity.getEyeHeight(entity.getPose()) + (double)f, this.lerp(entity.lastZ, entity.getZ(), this.getTickDelta()));
    }

    public static Vector4d worldToScreen$default(RenderUtil renderUtil, Entity entity, float f, Object object) {
        f = 0.4f;
        return renderUtil.worldToScreen(entity, f);
    }

    @Nullable
    public final Vector4d worldToScreen(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        return this.worldToScreen(vec3d.x, vec3d.y, vec3d.z);
    }

    @Nullable
    public final Vector4d worldToScreen(double d, double d2, double d3) {
        Vector4d vector4d;
        block5: {
            double d4;
            Vec3d vec3d;
            block4: {
                long l = 0L;
                int n = 0;
                long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
                vector4d = null;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-585948958672872993L, (long)l2);
                vec3d = this.toCameraRelative(new Vec3d(d, d2, d3));
                double d5 = vec3d.z - 0.0;
                d4 = d5 == 0.0 ? 0 : (d5 > 0.0 ? 1 : -1);
                if (callSite != null) break block4;
                if (d4 <= 0) break block5;
                double d6 = vec3d.z - 1.0;
                d4 = d6 == 0.0 ? 0 : (d6 < 0.0 ? -1 : 1);
            }
            if (d4 < 0) {
                vector4d = new Vector4d(vec3d.x, vec3d.y, Math.max(vec3d.x, vec3d.z), 0.0);
            }
        }
        return vector4d;
    }

    @NotNull
    public final Color withAlpha(@NotNull Color color, float f) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(255.0f * Math.clamp(f, 0.0f, 1.0f)));
    }

    @NotNull
    public final Color blend(@NotNull Color color, @NotNull Color color2, float f) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(color2, "end");
        float f2 = Math.clamp(f, 0.0f, 1.0f);
        return new Color(this.lerp(color.getRed(), color2.getRed(), f2), this.lerp(color.getGreen(), color2.getGreen(), f2), this.lerp(color.getBlue(), color2.getBlue(), f2), this.lerp(color.getAlpha(), color2.getAlpha(), f2));
    }

    public final float getRed(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return (float)color.getRed() / 255.0f;
    }

    public final float getGreen(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return (float)color.getGreen() / 255.0f;
    }

    public final float getBlue(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return (float)color.getBlue() / 255.0f;
    }

    public final float getAlpha(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return (float)color.getAlpha() / 255.0f;
    }

    public final float getTickDelta() {
        return Globals.getMc().getRenderTickCounter().getTickProgress(true);
    }

    public final float interpolate(float f, float f2) {
        return this.lerp(f, f2, this.getTickDelta());
    }

    public final double interpolate(double d, double d2) {
        return this.lerp(d, d2, this.getTickDelta());
    }

    @NotNull
    public final Vec3d[] getBoxCorners(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "ent");
        Box box = RenderState.INSTANCE.getRenderBox(entity);
        Vec3d[] vec3dArray = new Vec3d[]{new Vec3d(box.minX, box.minY, box.minZ), new Vec3d(box.minX, box.maxY, box.minZ), new Vec3d(box.maxX, box.minY, box.minZ), new Vec3d(box.maxX, box.maxY, box.minZ), new Vec3d(box.minX, box.minY, box.maxZ), new Vec3d(box.minX, box.maxY, box.maxZ), new Vec3d(box.maxX, box.minY, box.maxZ), new Vec3d(box.maxX, box.maxY, box.maxZ)};
        return vec3dArray;
    }

    public final void outlineQuad(@NotNull BatchedBuffer batchedBuffer, float f, float f2, float f3, float f4, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(batchedBuffer, "polygon");
        Intrinsics.checkNotNullParameter(color, "color");
        Color color2 = Color.BLACK;
        Intrinsics.checkNotNull(color2);
        this.quad(batchedBuffer, f - 1.0f, f2, f + 0.5f, f4 + 0.5f, color2);
        this.quad(batchedBuffer, f - 1.0f, f2 - 0.5f, f3 + 0.5f, f2 + 1.0f, color2);
        this.quad(batchedBuffer, f3 - 1.0f, f2, f3 + 0.5f, f4 + 0.5f, color2);
        this.quad(batchedBuffer, f - 1.0f, f4 - 1.0f, f3 + 0.5f, f4 + 0.5f, color2);
        this.quad(batchedBuffer, f - 0.5f, f2, f, f4, color);
        this.quad(batchedBuffer, f, f4 - 0.5f, f3, f4, color);
        this.quad(batchedBuffer, f - 0.5f, f2, f3, f2 + 0.5f, color);
        this.quad(batchedBuffer, f3 - 0.5f, f2, f3, f4, color);
    }

    @NotNull
    public final Color getRainbow(int n, int n2, float f, float f2) {
        int n3 = (int)((Globals.now() / (long)n + (long)n2) % (long)360);
        float f3 = (float)n3 / 360.0f;
        Color color = new Color(Color.HSBtoRGB(f3, f, f2));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
    }

    @NotNull
    public final Color rainbow(int n, int n2) {
        int n3 = (int)((Globals.now() / (long)n + (long)n2) % (long)360);
        int n4 = 360;
        Object var3_5 = null;
        int n5 = n4;
        Color color = Color.getHSBColor((double)((float)((double)n4 / 360.0)) < 0.5 ? -((float)((double)n3 / 360.0)) : (float)((double)(n3 %= n5) / 360.0), 0.5f, 1.0f);
        Intrinsics.checkNotNullExpressionValue(color, "getHSBColor(...)");
        return color;
    }

    private final boolean getGpuVendor() {
        String string = RenderSystem.getDevice().getVendor();
        Intrinsics.checkNotNullExpressionValue(string, "getVendor(...)");
        return StringsKt.contains$default((CharSequence)string, "Apple", false, 2, null);
    }

    private static final Unit registerTexture$lambda$0(Identifier identifier, BufferedImage bufferedImage) {
        Globals.getMc().getTextureManager().registerTexture(identifier, INSTANCE.toNativeImage(bufferedImage));
        return Unit.INSTANCE;
    }

    private static final String getTextureId(BufferedImage bufferedImage) {
        return "ctl_nibt_" + bufferedImage.hashCode();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        RenderUtil.a = ZkmKeys.a(-9195116701774200179L, -3824621880647670146L, MethodHandles.lookup().lookupClass()).a(20721331819440L);
                        var20 = RenderUtil.a ^ 70019816432172L;
                        RenderUtil.e = new HashMap<K, V>(13);
                        RenderUtil.zkmSetFlag(null);
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
                        var18_3 = new String[51];
                        var16_4 = 0;
                        var15_5 = "\u00b8\u00e7\u00a3\u0096\u001civ\u00115\u00be\u00be+d\n;i\u0010\u00837eo\u0005\u00ff5p\u009e\u00e6C\u00f4j\u0081\u00ac\u0018\u0010\u00c4W\u00a9\u00e1\u00c1V\u0087\u0095\u007fR+\u0086\u00d0\u0088\u000f\u00e9 \u0097\u00cc\u008e\u00b6\u001e\u00f6\u00dfn\u00a6\u00e3\u0011\u0018w\u00c4\u00f0\u00ecMcf\u00f5\u00ef+\u0088L`\u008c\u00fb\u00c9JsMr\u0018\u00bd\u00a7hq\u00b6\u0001I\u00d5\u0097\u00ec\u0097\u000e\u00f3:\u00a2\u009e\u00ec\u00adG\u00d2\u0089\u0017PV\u0010\u00ee\u001aO\u0011\u00cb\u00fa\u00b0B\u00a4\u00f6\u008b\b\u00eb\u008bq\u0083\u0010\u00cb\u009d\u0017\u00f9ZHI-}\u00f7\u008cz\u00df\u00a1b\u00ce\u0010\u00e5\u00c4\u009e\u00d8qZV\u00c3\u000b\u009b\u00ab\u0083\u00ef\u00fc\u00f2\u00c8\u0010\u00b0t/\u00feJ~\u00ca-\u0098PBG%\u0089X\u00de\u0010\u00ff\u009e{\"\u0092,\u008d\u0093O\u00ef\u00ee\u0003\u00e0}\r@\u0010T\u0005\u00a1\u00d3\u0003\u00dba\u00ba\u0003\u00c3g\u00d7j\u00c7\u00d5\u00a6\u0010\u008b\u0090B\u00b0\u00eb\u00bc*B`\u00f7ly\u00d5\u00c0\u00f5\u00b7\u0010\u00ac\u0088k02\u00e08;\u00c86\u00d7\u00b6V\u009c\u00e3\u00db\u0010\u00cd\b\u007f\u0087\u00a6>\u00dd2\u0084%D\u00e4\u00faD\\\u001a D\u00d6\u0011\u00e1\u0019\u00ad\u00e0y\u00ac\u001e\u00bf\u00d4\u00faWA\u00eb\u0017\u00af\u00d3\"g\u00c6m\u00b6hh\u00bb\u00b3\u00a8\u0012\u00eb30\u00ff\u00c1\u0010X\u00d0\u00d5@@ \u0097\b?\u00fb[\u00e8\u0094\u001f\u00cb\u00fa\u0081\u00c8\u00b5f\u0088P\u00be\u00fc_\u00e8,6\u0093\u00b7\u00e4\u00ff\u00c3r\u00b6\u0019N\u00d6h\u00a0\u0011\u00a3p3\u00b2 \u0011\u008c=\u009b\u00a0\u009a00V\u00edkt\u00d8\u00c6T\u0099r\u00cf\u008d\u009b\u00b9\u00e8\u00e7\u00c1\u00b6\u00cdO`\u0092Q\u00fa\u009a\u0010\u00a7\u00c4\u00c0W\u009e\u00bbY\u0083.\u0003Z\u0081\u00b0\u00c9M6(\u0005\u00da\u00a8]\u00c4\u00a0Zg\u00c1$\u00f8\u00f9\u0098\u0081\u00ac\u00eb\u001a\u00107\u00df\u0001]pl\u00a07\u0003\u00c5\u00b6\u008e\u0002\u00d9A9\u00ddj\u0002c\u00ab\u001b\u0010\u00b3\u001b\u00a0\u00a3\u00e1\u00a1\u00db\u00ca==\u00fa\u0010\u00e7\u00de\u0085:\u0010\u00cf\u0004\u00dc\u00ed\u00bb\u0091\u00f7\u00c7\u00b7%\u0019\u00eb\u0095V\u0090?\u0010)i\u00b3s\u00c6\n\u00e2\u000e\u0094:\u000e\u0089c\u00a9\u00fc\u000f\u0018Z\u00f94\u00cb\u00a5\u001as~|0\u00e6K\u00ad;\nx,S\u00e4\u00f7\u00dc\u00cc\u001cu\u0010\u00b4\u0083\u00f4n)\u008d:\u00f4\u0098k\u000b\u00b2xD\u0007\u001a\u00109O\u000b\u00f2\u000e\u00b7nF\u0095 h\u00beAxa\u0006 \u00b1\u001c?p\u00a2\u0013\u0004\t\u0018\u00f8\u00bf\u00cd\u0016#\u00a51u\u0015MHQ\u00b7\u00ab\u00e3q\u00e2W@+w\u00e6N\u0010\u0097\u00a3\u00c9\u00c4\u00f4dY\u00bd\u00c8l\u00e1,T\u00b5\u0002\u00d7\u0018\u00abp\u00bd\u008dus(#ZS\u00ed\u0097\u000e\u00b5\u0096\u00a6\u0006\u0088u\u009d]V}8\u0010\u00d9p\u00b1I<\u00d3\u00f8j\u00cbG\u00ac\u00bd\u00caC\u00ed\u001c\u0010\u0094\u00af'\\z2j\u009a\u00e2\u0017\u00fc\u00d8\u0091\u00f3XP(mI\u0080v\u00da\u0010B*\u00b9Z\u00e3\u00b1\u008e\u00ab\u00caJ\u00e8a\u00c0\u001b\u00eaNA\u00adU\u00a0\u001b\u00c4Y\u0013\u00e1w\u00e8\u00af\u00f1\u00d8\u00f0\f\f\u00c5\u0010\u001f{\u0005u\u00d2P1#i\u001f\u00cb\u0018UX\u0007\u00f2\u0010X4\u00004W\u009e\u0094\u009eg\u00b0I\u0091>\u009a0\u00b7\u0010h:W\u00d0\u00d5\u00a4\u00f6\u00cfp\u00d7}]\u008b\u00e1\u00f0\u00d90Zig\\>A\u00e7K\u0091\u0091\u009c\u0014Ic^\n\u00df\u00b0\u00c9Bh\u00e5\u0087:\u00eap\u00d2\u00ab\u00fd\u00cf3z\u00dc*\u0095\u00dd\u00f1c,{\u0013#\u00da\u00ab*\u00ec\u0001\u00bf\u0010\u0082\u00d7\be\u00b3\u0088\u00ecf\u00c9\u00ecj\u0081\u0095\u00e4\u001c\u0002\u0010qA\u0090\u00a8\u0002\u0007\u0099{\u0010\u00d6Z\u00d2oad\u00a18`;\u0092u\u001f\u00af\u00998\u001bR\b\u00fa!7WLv\u00e8\\$\u00f9T\u00f2\u00155\u00df7E\u008e\u007fg1\u00a7.\u0088P\u00c1\f\u00c7\u00e05|\u00ce\u00a0\u00c0\u0095\u008b\u00ed\u00f2\u00b3\u00aa\u00fc%\u00ae(\u00c8\u0010\u00d5\u0019\u00cf~\u00e4\u00bc\tZ\u00f1\u00da+&\u00f0\u00dc)\u00dd\u0010RR!K\u00fb\u008fs\u00b3J\u00b6fW\u00d2\u00daO\u00d1\u0010\u0097 \u009c\u001a\u00b1H2%\u0012\u00fc\u0094\u00a3\u008fgD\u00f80X\u001f\u00b8-pG\u00a4\u00d2y\u009fuK\u0011\u00bf\u00d14\u0093Z\u00d3\u0005\u0095p\u00dbi\u00fc\u00fe\u001d\u00f5\u00a008\u00d0~\u0080\u0000N\u001b\u009d|\u00bc\u00af\u00c7g\u001dt(\u00c9\u00cf\u0010uD\u00e8\u00d2\u0010\u0085\u00e9\u00bf\u0007-\u00a3\u00a6\u00ae\u00fa\u00bb\u00e2\u0010cS\u00e1(\u00b7\u00e2\u00f2\u0080\u00fc4^\u00b6\u00baf\r\u00f8\u0018j$U\u00b8[\u001bDx6\"\u009c\u0096\u00b9\u009b\u0085\u00c9\u009d}\u00bb\u00a6\u001eW[A(x\u00d8S\u00c2\u00f6 \u00a4GK\u001e\u0004{\u000e\u001f\u001e\u0080\u00f9\u0092#\u0081&\u00ffnG\u0095D#\u009e}\u00c7\u0015r\u00d7\u000b\u00f3\u00d4\u0092E\u00e3\u00dc\u0010r\u00a2\u00c8\u00ecu\u00d2\u007fi&\u00e2ZW\u00d2\u00dd\u0015\u0010\u0010<\u00ea5\u00a8n\u001f\u00f9\u00b0\u00d0\u0098\u00ef\u001f\u00d5\u00f1\u00cb\u008c(-h\u001bo\u00d6\u00c7\u00a3\u0084/,7\u00a1\u00e8+\u00c7\u008a\u00c0\u00ea+\u00ed\u008d\u00db\u00b9T,\u00c1\u00f8\u00c8\u00fem[\u00ec\u00bc\u00f8Tk\u00c0\u0019\u00f3\u0001";
                        var17_6 = "\u00b8\u00e7\u00a3\u0096\u001civ\u00115\u00be\u00be+d\n;i\u0010\u00837eo\u0005\u00ff5p\u009e\u00e6C\u00f4j\u0081\u00ac\u0018\u0010\u00c4W\u00a9\u00e1\u00c1V\u0087\u0095\u007fR+\u0086\u00d0\u0088\u000f\u00e9 \u0097\u00cc\u008e\u00b6\u001e\u00f6\u00dfn\u00a6\u00e3\u0011\u0018w\u00c4\u00f0\u00ecMcf\u00f5\u00ef+\u0088L`\u008c\u00fb\u00c9JsMr\u0018\u00bd\u00a7hq\u00b6\u0001I\u00d5\u0097\u00ec\u0097\u000e\u00f3:\u00a2\u009e\u00ec\u00adG\u00d2\u0089\u0017PV\u0010\u00ee\u001aO\u0011\u00cb\u00fa\u00b0B\u00a4\u00f6\u008b\b\u00eb\u008bq\u0083\u0010\u00cb\u009d\u0017\u00f9ZHI-}\u00f7\u008cz\u00df\u00a1b\u00ce\u0010\u00e5\u00c4\u009e\u00d8qZV\u00c3\u000b\u009b\u00ab\u0083\u00ef\u00fc\u00f2\u00c8\u0010\u00b0t/\u00feJ~\u00ca-\u0098PBG%\u0089X\u00de\u0010\u00ff\u009e{\"\u0092,\u008d\u0093O\u00ef\u00ee\u0003\u00e0}\r@\u0010T\u0005\u00a1\u00d3\u0003\u00dba\u00ba\u0003\u00c3g\u00d7j\u00c7\u00d5\u00a6\u0010\u008b\u0090B\u00b0\u00eb\u00bc*B`\u00f7ly\u00d5\u00c0\u00f5\u00b7\u0010\u00ac\u0088k02\u00e08;\u00c86\u00d7\u00b6V\u009c\u00e3\u00db\u0010\u00cd\b\u007f\u0087\u00a6>\u00dd2\u0084%D\u00e4\u00faD\\\u001a D\u00d6\u0011\u00e1\u0019\u00ad\u00e0y\u00ac\u001e\u00bf\u00d4\u00faWA\u00eb\u0017\u00af\u00d3\"g\u00c6m\u00b6hh\u00bb\u00b3\u00a8\u0012\u00eb30\u00ff\u00c1\u0010X\u00d0\u00d5@@ \u0097\b?\u00fb[\u00e8\u0094\u001f\u00cb\u00fa\u0081\u00c8\u00b5f\u0088P\u00be\u00fc_\u00e8,6\u0093\u00b7\u00e4\u00ff\u00c3r\u00b6\u0019N\u00d6h\u00a0\u0011\u00a3p3\u00b2 \u0011\u008c=\u009b\u00a0\u009a00V\u00edkt\u00d8\u00c6T\u0099r\u00cf\u008d\u009b\u00b9\u00e8\u00e7\u00c1\u00b6\u00cdO`\u0092Q\u00fa\u009a\u0010\u00a7\u00c4\u00c0W\u009e\u00bbY\u0083.\u0003Z\u0081\u00b0\u00c9M6(\u0005\u00da\u00a8]\u00c4\u00a0Zg\u00c1$\u00f8\u00f9\u0098\u0081\u00ac\u00eb\u001a\u00107\u00df\u0001]pl\u00a07\u0003\u00c5\u00b6\u008e\u0002\u00d9A9\u00ddj\u0002c\u00ab\u001b\u0010\u00b3\u001b\u00a0\u00a3\u00e1\u00a1\u00db\u00ca==\u00fa\u0010\u00e7\u00de\u0085:\u0010\u00cf\u0004\u00dc\u00ed\u00bb\u0091\u00f7\u00c7\u00b7%\u0019\u00eb\u0095V\u0090?\u0010)i\u00b3s\u00c6\n\u00e2\u000e\u0094:\u000e\u0089c\u00a9\u00fc\u000f\u0018Z\u00f94\u00cb\u00a5\u001as~|0\u00e6K\u00ad;\nx,S\u00e4\u00f7\u00dc\u00cc\u001cu\u0010\u00b4\u0083\u00f4n)\u008d:\u00f4\u0098k\u000b\u00b2xD\u0007\u001a\u00109O\u000b\u00f2\u000e\u00b7nF\u0095 h\u00beAxa\u0006 \u00b1\u001c?p\u00a2\u0013\u0004\t\u0018\u00f8\u00bf\u00cd\u0016#\u00a51u\u0015MHQ\u00b7\u00ab\u00e3q\u00e2W@+w\u00e6N\u0010\u0097\u00a3\u00c9\u00c4\u00f4dY\u00bd\u00c8l\u00e1,T\u00b5\u0002\u00d7\u0018\u00abp\u00bd\u008dus(#ZS\u00ed\u0097\u000e\u00b5\u0096\u00a6\u0006\u0088u\u009d]V}8\u0010\u00d9p\u00b1I<\u00d3\u00f8j\u00cbG\u00ac\u00bd\u00caC\u00ed\u001c\u0010\u0094\u00af'\\z2j\u009a\u00e2\u0017\u00fc\u00d8\u0091\u00f3XP(mI\u0080v\u00da\u0010B*\u00b9Z\u00e3\u00b1\u008e\u00ab\u00caJ\u00e8a\u00c0\u001b\u00eaNA\u00adU\u00a0\u001b\u00c4Y\u0013\u00e1w\u00e8\u00af\u00f1\u00d8\u00f0\f\f\u00c5\u0010\u001f{\u0005u\u00d2P1#i\u001f\u00cb\u0018UX\u0007\u00f2\u0010X4\u00004W\u009e\u0094\u009eg\u00b0I\u0091>\u009a0\u00b7\u0010h:W\u00d0\u00d5\u00a4\u00f6\u00cfp\u00d7}]\u008b\u00e1\u00f0\u00d90Zig\\>A\u00e7K\u0091\u0091\u009c\u0014Ic^\n\u00df\u00b0\u00c9Bh\u00e5\u0087:\u00eap\u00d2\u00ab\u00fd\u00cf3z\u00dc*\u0095\u00dd\u00f1c,{\u0013#\u00da\u00ab*\u00ec\u0001\u00bf\u0010\u0082\u00d7\be\u00b3\u0088\u00ecf\u00c9\u00ecj\u0081\u0095\u00e4\u001c\u0002\u0010qA\u0090\u00a8\u0002\u0007\u0099{\u0010\u00d6Z\u00d2oad\u00a18`;\u0092u\u001f\u00af\u00998\u001bR\b\u00fa!7WLv\u00e8\\$\u00f9T\u00f2\u00155\u00df7E\u008e\u007fg1\u00a7.\u0088P\u00c1\f\u00c7\u00e05|\u00ce\u00a0\u00c0\u0095\u008b\u00ed\u00f2\u00b3\u00aa\u00fc%\u00ae(\u00c8\u0010\u00d5\u0019\u00cf~\u00e4\u00bc\tZ\u00f1\u00da+&\u00f0\u00dc)\u00dd\u0010RR!K\u00fb\u008fs\u00b3J\u00b6fW\u00d2\u00daO\u00d1\u0010\u0097 \u009c\u001a\u00b1H2%\u0012\u00fc\u0094\u00a3\u008fgD\u00f80X\u001f\u00b8-pG\u00a4\u00d2y\u009fuK\u0011\u00bf\u00d14\u0093Z\u00d3\u0005\u0095p\u00dbi\u00fc\u00fe\u001d\u00f5\u00a008\u00d0~\u0080\u0000N\u001b\u009d|\u00bc\u00af\u00c7g\u001dt(\u00c9\u00cf\u0010uD\u00e8\u00d2\u0010\u0085\u00e9\u00bf\u0007-\u00a3\u00a6\u00ae\u00fa\u00bb\u00e2\u0010cS\u00e1(\u00b7\u00e2\u00f2\u0080\u00fc4^\u00b6\u00baf\r\u00f8\u0018j$U\u00b8[\u001bDx6\"\u009c\u0096\u00b9\u009b\u0085\u00c9\u009d}\u00bb\u00a6\u001eW[A(x\u00d8S\u00c2\u00f6 \u00a4GK\u001e\u0004{\u000e\u001f\u001e\u0080\u00f9\u0092#\u0081&\u00ffnG\u0095D#\u009e}\u00c7\u0015r\u00d7\u000b\u00f3\u00d4\u0092E\u00e3\u00dc\u0010r\u00a2\u00c8\u00ecu\u00d2\u007fi&\u00e2ZW\u00d2\u00dd\u0015\u0010\u0010<\u00ea5\u00a8n\u001f\u00f9\u00b0\u00d0\u0098\u00ef\u001f\u00d5\u00f1\u00cb\u008c(-h\u001bo\u00d6\u00c7\u00a3\u0084/,7\u00a1\u00e8+\u00c7\u008a\u00c0\u00ea+\u00ed\u008d\u00db\u00b9T,\u00c1\u00f8\u00c8\u00fem[\u00ec\u00bc\u00f8Tk\u00c0\u0019\u00f3\u0001".length();
                        var14_7 = 16;
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
                            var18_3[var16_4++] = RenderUtil.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b0\n:\u00c85,\f\u00a9\u00c3\u0001}*\u00d9\u00ef\u00e22h+\u00d4T\u00d9^\u00c5\u0091\u000fvuc\u009d\u00f3\b\u0086\u0010\u00c0-\u00ec\u00b6\u00d7S^\u00d0\u00a8H]\u00c7+V\u00d2l";
                            var17_6 = "\u00b0\n:\u00c85,\f\u00a9\u00c3\u0001}*\u00d9\u00ef\u00e22h+\u00d4T\u00d9^\u00c5\u0091\u000fvuc\u009d\u00f3\b\u0086\u0010\u00c0-\u00ec\u00b6\u00d7S^\u00d0\u00a8H]\u00c7+V\u00d2l".length();
                            var14_7 = 32;
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
                            var18_3[var16_4++] = RenderUtil.zkmDecodeUtf8(var19_9).intern();
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
                RenderUtil.b = var18_3;
                RenderUtil.c = new String[51];
                RenderUtil.h = new HashMap<K, V>(13);
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
                var6_12 = new long[34];
                var3_13 = 0;
                var4_14 = "\u00a9v1\u001fr[;\u0093\u00b6\u00c5(\u0088O\u0091\u00de\u00bc\u0099\u00f5i+\u00d6\u000eT`\u001b>G,\u00ff\u00fc\u008f\u000eW\n3\u0090X\u00d2\u00ea\u0014\u00cc\u0003_\u0017\u00a0\u0087\u00c8,\u008f\u00e9]\\\u0097s\u009a\u008e)\u00ca\u00b2\u0097\u00a8\u00baVM\u000f\u009e\u008b\u00c1x\u007f\u00feL\u00e0;a\u00b2j\u0091\b>\u00ca\u0093\u00c0Ou\u00db(\u00f45\u0013\u00df1\f!&lp\u0093\u00b3L\u00c5\u00ce\u00f10\u00c4t_\u0002\u00d9o\u00a94^}\u009e\u00bay!\u008c\u001fg\r\u00de\u00f1\u00d4X\u00f0\u00f8wNfVu\u00a0\u00d8BP\u00a0Jg\u00cb%6\u0014v\u001b\u0012\u00ae\u00feV\f\u00aa\u00a2\u000fT\u00b6\u00de\u0098\u0005Ow_\u0006Jcn\u00a3\u00bd\u0000\u0095\u00d13\u00fb\u00ef9\u00b0\u00c2\u00a7\u00e95\u00d8\u00e9-\u009e2\u008d\u00b5\u00dd\u0080\u00d3\u00d0}7\u00b6m\u000f\u001d\u00c4\u00bc\b\u008e\u0019\u0097f\u00b3g\u001c\u008e\u00f1\u00e6\u000f\u00d0\u0091\u0086?\u0086-\u00e8\u0006<w\u00c1iJ^\u00c5\u00d9\u0012\u0084\u00d5X\u00d6\u0003G\u0005\u00cb\u0082\u0003\u0082\u00fe\u001eD\u00b0\u0095\u0004\u009a\u0004\u00d3\u00bd\u001f\u0096\u00ef\u00cb\u00de\u00a2\u000f";
                var5_15 = "\u00a9v1\u001fr[;\u0093\u00b6\u00c5(\u0088O\u0091\u00de\u00bc\u0099\u00f5i+\u00d6\u000eT`\u001b>G,\u00ff\u00fc\u008f\u000eW\n3\u0090X\u00d2\u00ea\u0014\u00cc\u0003_\u0017\u00a0\u0087\u00c8,\u008f\u00e9]\\\u0097s\u009a\u008e)\u00ca\u00b2\u0097\u00a8\u00baVM\u000f\u009e\u008b\u00c1x\u007f\u00feL\u00e0;a\u00b2j\u0091\b>\u00ca\u0093\u00c0Ou\u00db(\u00f45\u0013\u00df1\f!&lp\u0093\u00b3L\u00c5\u00ce\u00f10\u00c4t_\u0002\u00d9o\u00a94^}\u009e\u00bay!\u008c\u001fg\r\u00de\u00f1\u00d4X\u00f0\u00f8wNfVu\u00a0\u00d8BP\u00a0Jg\u00cb%6\u0014v\u001b\u0012\u00ae\u00feV\f\u00aa\u00a2\u000fT\u00b6\u00de\u0098\u0005Ow_\u0006Jcn\u00a3\u00bd\u0000\u0095\u00d13\u00fb\u00ef9\u00b0\u00c2\u00a7\u00e95\u00d8\u00e9-\u009e2\u008d\u00b5\u00dd\u0080\u00d3\u00d0}7\u00b6m\u000f\u001d\u00c4\u00bc\b\u008e\u0019\u0097f\u00b3g\u001c\u008e\u00f1\u00e6\u000f\u00d0\u0091\u0086?\u0086-\u00e8\u0006<w\u00c1iJ^\u00c5\u00d9\u0012\u0084\u00d5X\u00d6\u0003G\u0005\u00cb\u0082\u0003\u0082\u00fe\u001eD\u00b0\u0095\u0004\u009a\u0004\u00d3\u00bd\u001f\u0096\u00ef\u00cb\u00de\u00a2\u000f".length();
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
                    var4_14 = "z\u00a5\u00c4\u00d7\u00a3u2\u00fa\u00b1\u00d2\u00b4\u00d11h\u00f6.";
                    var5_15 = "z\u00a5\u00c4\u00d7\u00a3u2\u00fa\u00b1\u00d2\u00b4\u00d11h\u00f6.".length();
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
        RenderUtil.f = var6_12;
        RenderUtil.g = new Integer[34];
        RenderUtil.INSTANCE = new RenderUtil();
        RenderUtil.renderTasks = new CopyOnWriteArrayList<E>();
        RenderUtil.r = new Matrix4f();
        RenderUtil.V = new Matrix4f();
        RenderUtil.M = new int[4];
        RenderUtil.w = -1;
        RenderUtil.X = 1.0f;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        v = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return v;
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x61B1;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/jl", exception);
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
            RenderUtil.c[n2] = RenderUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RenderUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F88;
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
                throw new RuntimeException("su/catlean/jl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RenderUtil.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RenderUtil.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/jl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RenderUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RenderUtil.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

