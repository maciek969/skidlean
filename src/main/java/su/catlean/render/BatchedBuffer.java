/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.system.MemoryUtil
 */
package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Supplier;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.render.VertexFormats;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;
import su.catlean.api.event.GofraState;
import su.catlean.mixins.accessors.GlCommandEncoderAccessor;
import su.catlean.mixins.accessors.GlDeviceAccessor;
import su.catlean.mixins.accessors.RenderPassAccessor;
import su.catlean.module.Module;
import su.catlean.render.Rect;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.ScissorStack;
import su.catlean.render.uniform.BufferUniformStorage;
import su.catlean.render.uniform.ProjectionUniforms;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class BatchedBuffer {
    @NotNull
    private final VertexFormat format;
    private final boolean lines;
    private ByteBuffer vertexBuffer;
    private final int indicesPerPrimitive;
    private ByteBuffer indexBuffer;
    private long vertexAddress;
    private long indexAddress;
    private final ByteBuffer tempBuffer;
    private long writeAddress;
    @NotNull
    private final int[] L;
    private int v;
    private int vertexCount;
    private int indexCount;
    private final boolean B;
    private final boolean h;
    @NotNull
    private final Vector3f tempVector;
    private static String[] t;
    private static final long a;
    private static final String[] c;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] i;
    private static final Map l;
    private static final long[] n;
    private static final Long[] o;
    private static final Map p;

    public BatchedBuffer(@NotNull VertexFormat vertexFormat, int n, boolean bl) {
        int n2 = 0;
        long l = 0L;
        long l2 = ((long)n2 << 32 | l << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(vertexFormat, "format");
        Module[] moduleArray = RenderUtil.zkmGetFlag();
        this.format = vertexFormat;
        this.lines = bl;
        Module[] moduleArray2 = moduleArray;
        this.vertexBuffer = BufferUtils.createByteBuffer((int)(this.format.getVertexSize() * n * 4));
        this.indicesPerPrimitive = this.lines ? 2 : (int)BatchedBuffer.zkmBootstrap2("r", (int)15395, (long)(0x56A07E691142AE72L ^ l2));
        this.indexBuffer = BufferUtils.createByteBuffer((int)(n * this.indicesPerPrimitive * 4));
        this.vertexAddress = MemoryUtil.memAddress0((Buffer)this.vertexBuffer);
        this.indexAddress = MemoryUtil.memAddress0((Buffer)this.indexBuffer);
        this.tempBuffer = MemoryUtil.memAlloc((int)BatchedBuffer.zkmBootstrap2("r", (int)11104, (long)(0x60F11B8E688CB930L ^ l2)));
        this.writeAddress = this.vertexAddress;
        this.L = new int[4];
        this.B = Intrinsics.areEqual(this.format, VertexFormats.POSITION);
        this.h = Intrinsics.areEqual(this.format, VertexFormats.POSITION_TEXTURE);
        this.tempVector = new Vector3f();
    }

    public BatchedBuffer(VertexFormat vertexFormat, int n, boolean bl, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 1) != 0) {
            VertexFormat vertexFormat2 = VertexFormats.POSITION;
            Intrinsics.checkNotNullExpressionValue(vertexFormat2, "POSITION");
            vertexFormat = vertexFormat2;
        }
        if ((n2 & 2) != 0) {
            n = 1;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        this(vertexFormat, n, bl);
    }

    public final boolean isLines() {
        return this.lines;
    }

    @NotNull
    public final BatchedBuffer fullscreenQuad() {
        return this.vertex(-1.0f, -1.0f, 0.0f).vertex(-1.0f, 1.0f, 0.0f).vertex(1.0f, 1.0f, 0.0f).vertex(1.0f, -1.0f, 0.0f);
    }

    @NotNull
    public final BatchedBuffer screenQuad() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        float f = Globals.getMc().getWindow().getScaledWidth();
        float f2 = Globals.getMc().getWindow().getScaledHeight();
        BatchedBuffer batchedBuffer = this.vertex(0.0f, f2, 0.0f).texture(0.0f, 0.0f);
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)10574, (long)(0x2292EA0F0136B683L ^ l))));
        BatchedBuffer batchedBuffer2 = batchedBuffer.color(color).vertex(0.0f, 0.0f, 0.0f).texture(0.0f, 1.0f);
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)22233, (long)(0x3826D8CF626AC905L ^ l))));
        BatchedBuffer batchedBuffer3 = batchedBuffer2.color(color2).vertex(f, 0.0f, 0.0f).texture(1.0f, 1.0f);
        Color color3 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color3, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)22233, (long)(0x3826D8CF626AC905L ^ l))));
        BatchedBuffer batchedBuffer4 = batchedBuffer3.color(color3).vertex(f, f2, 0.0f).texture(1.0f, 0.0f);
        Color color4 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color4, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)22233, (long)(0x3826D8CF626AC905L ^ l))));
        return batchedBuffer4.color(color4);
    }

    @NotNull
    public final BatchedBuffer vertexUv(@NotNull Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, @NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(matrix4f, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)17486, (long)(0x593923A99C9851A7L ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)30711, (long)(0x2F51C153BC6E201L ^ l))));
        matrix4f.transformPosition(f, f2, f3, this.tempVector);
        this.putVertex(this.tempVector.x, this.tempVector.y, this.tempVector.z, f4, f5, color);
        return this;
    }

    @NotNull
    public final BatchedBuffer putVertex(float f, float f2, float f3, float f4, float f5, @NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)BatchedBuffer.zkmBootstrap("s", (int)31777, (long)(0x35DD5629453E4775L ^ l))));
        this.ensureVertexCapacity((int)BatchedBuffer.zkmBootstrap2("r", (int)24619, (long)(0x90C639DF06094BL ^ l)));
        this.tempBuffer.putFloat(0, f);
        this.tempBuffer.putFloat(4, f2);
        this.tempBuffer.putFloat((int)BatchedBuffer.zkmBootstrap2("r", (int)18967, (long)(0x2C860E99500E2360L ^ l)), f3);
        this.tempBuffer.putFloat((int)BatchedBuffer.zkmBootstrap2("r", (int)18579, (long)(0x5B75D97B2AFF21F2L ^ l)), f4);
        this.tempBuffer.putFloat((int)BatchedBuffer.zkmBootstrap2("r", (int)27672, (long)(0x697795D9B897056AL ^ l)), f5);
        this.tempBuffer.put((int)BatchedBuffer.zkmBootstrap2("r", (int)27524, (long)(0x6EA1DB6B44BF02F0L ^ l)), (byte)color.getRed());
        this.tempBuffer.put((int)BatchedBuffer.zkmBootstrap2("r", (int)23340, (long)(0x5FE6132A30E3B252L ^ l)), (byte)color.getGreen());
        this.tempBuffer.put((int)BatchedBuffer.zkmBootstrap2("r", (int)6555, (long)(0x283A2F0AA0D5F0EBL ^ l)), (byte)color.getBlue());
        this.tempBuffer.put((int)BatchedBuffer.zkmBootstrap2("r", (int)5694, (long)(0x467FB25F94797F5DL ^ l)), (byte)RangesKt.coerceIn((int)((float)color.getAlpha() * RenderUtil.INSTANCE.t()), new IntRange(0, (int)BatchedBuffer.zkmBootstrap2("r", (int)2595, (long)(0x5C1ACF20F7046341L ^ l)))));
        MemoryUtil.memCopy((long)MemoryUtil.memAddress((ByteBuffer)this.tempBuffer), (long)this.writeAddress, (long)BatchedBuffer.zkmBootstrap3("u", (int)29469, (long)(0xC654315E1C46FA4L ^ l)));
        this.writeAddress += (long)BatchedBuffer.zkmBootstrap2("r", (int)11104, (long)(0x60F15210CEAEC21BL ^ l));
        this.nextIndex();
        return this;
    }

    @NotNull
    public final BatchedBuffer vertex(float f, float f2, float f3) {
        block0: {
            this.ensureVertexCapacity(12);
            MemoryUtil.memPutFloat((long)this.writeAddress, (float)f);
            MemoryUtil.memPutFloat((long)(this.writeAddress + (long)4), (float)f2);
            Object var8_4 = null;
            MemoryUtil.memPutFloat((long)(this.writeAddress + (long)8), (float)f3);
            this.writeAddress += (long)12;
            if (!this.B) break block0;
            this.nextIndex();
        }
        return this;
    }

    public static BatchedBuffer vertex$default(BatchedBuffer batchedBuffer, float f, float f2, float f3, Object object) {
        f3 = 0.0f;
        return batchedBuffer.vertex(f, f2, f3);
    }

    @NotNull
    public final BatchedBuffer vertex(@NotNull Matrix4f matrix4f, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(matrix4f, "matrix");
        matrix4f.transformPosition(f, f2, f3, this.tempVector);
        return this.vertex(this.tempVector.x, this.tempVector.y, this.tempVector.z);
    }

    @NotNull
    public final BatchedBuffer vertex(@NotNull Matrix3x2f matrix3x2f, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(matrix3x2f, "matrix");
        this.toMatrix4f(matrix3x2f).transformPosition(f, f2, f3, this.tempVector);
        return this.vertex(this.tempVector.x, this.tempVector.y, this.tempVector.z);
    }

    @NotNull
    public final BatchedBuffer texture(float f, float f2) {
        block0: {
            this.ensureVertexCapacity(8);
            MemoryUtil.memPutFloat((long)this.writeAddress, (float)f);
            MemoryUtil.memPutFloat((long)(this.writeAddress + (long)4), (float)f2);
            this.writeAddress += (long)8;
            Object var7_3 = null;
            if (!this.h) break block0;
            this.nextIndex();
        }
        return this;
    }

    @NotNull
    public final BatchedBuffer color(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "c");
        int n = color.getAlpha();
        return this.color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    @NotNull
    public final BatchedBuffer color(float f, float f2, float f3, float f4) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        int n4 = (int)(f4 * (float)BatchedBuffer.zkmBootstrap2("r", (int)6439, (long)(0x7F057D6C1492EDACL ^ l)));
        return this.color((int)(f * (float)BatchedBuffer.zkmBootstrap2("r", (int)6439, (long)(0x7F057D6C1492EDACL ^ l))), (int)(f2 * (float)BatchedBuffer.zkmBootstrap2("r", (int)6439, (long)(0x7F057D6C1492EDACL ^ l))), (int)(f3 * (float)BatchedBuffer.zkmBootstrap2("r", (int)6439, (long)(0x7F057D6C1492EDACL ^ l))), n4);
    }

    @NotNull
    public final BatchedBuffer color(int n, int n2, int n3, int n4) {
        block0: {
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            long l = ((long)n5 << 32 | (long)n6 << 48 >>> 32 | (long)n7 << 48 >>> 48) ^ a;
            this.ensureVertexCapacity(4);
            MemoryUtil.memPutByte((long)this.writeAddress, (byte)((byte)n));
            Module[] moduleArray = RenderUtil.zkmGetFlag();
            MemoryUtil.memPutByte((long)(this.writeAddress + 1L), (byte)((byte)n2));
            MemoryUtil.memPutByte((long)(this.writeAddress + (long)2), (byte)((byte)n3));
            Module[] moduleArray2 = moduleArray;
            MemoryUtil.memPutByte((long)(this.writeAddress + (long)3), (byte)((byte)RangesKt.coerceAtMost((int)((float)n4 * RenderUtil.INSTANCE.t()), (int)BatchedBuffer.zkmBootstrap2("r", (int)6439, (long)(0x7F051544DAF8F726L ^ l)))));
            this.writeAddress += (long)4;
            if (this.B) break block0;
            this.nextIndex();
        }
        return this;
    }

    private final void ensureVertexCapacity(int n) {
        int n2 = (int)(this.writeAddress - this.vertexAddress);
        if (n2 + n > this.vertexBuffer.capacity()) {
            ByteBuffer byteBuffer = this.vertexBuffer;
            int n3 = Math.max(byteBuffer.capacity() * 2, n2 + n);
            ByteBuffer byteBuffer2 = BufferUtils.createByteBuffer((int)n3);
            MemoryUtil.memCopy((long)MemoryUtil.memAddress0((Buffer)byteBuffer), (long)MemoryUtil.memAddress0((Buffer)byteBuffer2), (long)n2);
            this.vertexBuffer = byteBuffer2;
            this.vertexAddress = MemoryUtil.memAddress0((Buffer)this.vertexBuffer);
            this.writeAddress = this.vertexAddress + (long)n2;
        }
    }

    private final void ensureIndexCapacity(int n) {
        int n2 = this.indexCount * 4;
        if (n2 + n > this.indexBuffer.capacity()) {
            ByteBuffer byteBuffer = this.indexBuffer;
            int n3 = Math.max(byteBuffer.capacity() * 2, n2 + n);
            ByteBuffer byteBuffer2 = BufferUtils.createByteBuffer((int)n3);
            MemoryUtil.memCopy((long)MemoryUtil.memAddress0((Buffer)byteBuffer), (long)MemoryUtil.memAddress0((Buffer)byteBuffer2), (long)n2);
            this.indexBuffer = byteBuffer2;
            this.indexAddress = MemoryUtil.memAddress0((Buffer)this.indexBuffer);
        }
    }

    private final BatchedBuffer nextIndex() {
        int n;
        int n2 = this.vertexCount;
        Object var3_2 = null;
        this.vertexCount = n2 + 1;
        this.L[this.v] = n = n2;
        n2 = this.v;
        this.v = n2 + 1;
        if (this.v == (this.lines ? 2 : 4)) {
            n2 = this.lines ? 2 : 6;
            this.ensureIndexCapacity(n2 * 4);
            long l = this.indexAddress + (long)this.indexCount * (long)4;
            MemoryUtil.memPutInt((long)l, (int)this.L[0]);
            MemoryUtil.memPutInt((long)(l + (long)4), (int)this.L[1]);
            if (!this.lines) {
                MemoryUtil.memPutInt((long)(l + 8L), (int)this.L[2]);
                MemoryUtil.memPutInt((long)(l + 12L), (int)this.L[2]);
                MemoryUtil.memPutInt((long)(l + 16L), (int)this.L[3]);
                MemoryUtil.memPutInt((long)(l + 20L), (int)this.L[0]);
            }
            this.indexCount += n2;
            this.v = 0;
        }
        return this;
    }

    @NotNull
    public final Matrix4f toMatrix4f(@NotNull Matrix3x2f matrix3x2f) {
        Matrix4f matrix4f;
        Intrinsics.checkNotNullParameter(matrix3x2f, "m");
        Matrix4f matrix4f2 = matrix4f = new Matrix4f();
        matrix4f2.m00(matrix3x2f.m00());
        matrix4f2.m01(matrix3x2f.m01());
        matrix4f2.m10(matrix3x2f.m10());
        matrix4f2.m11(matrix3x2f.m11());
        matrix4f2.m30(matrix3x2f.m20());
        matrix4f2.m31(matrix3x2f.m21());
        return matrix4f;
    }

    public final void draw(long l, @NotNull RenderPipeline renderPipeline, @NotNull Framebuffer framebuffer, @Nullable GpuBufferSlice gpuBufferSlice, @Nullable Matrix4f matrix4f, @Nullable Matrix3x2f matrix3x2f, @NotNull Map map) {
        int n = (int)(((l = a ^ l) ^ 0x5CF9C09DB5D1L) << 48 >>> 48);
        Intrinsics.checkNotNullParameter(renderPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(framebuffer, "fb");
        Intrinsics.checkNotNullParameter(map, "samplers");
        this.draw(renderPipeline, framebuffer.getColorAttachmentView(), framebuffer.getDepthAttachmentView(), gpuBufferSlice, matrix4f, matrix3x2f, (short)n, map);
    }

    public static void draw$default(long l, BatchedBuffer batchedBuffer, RenderPipeline renderPipeline, Framebuffer framebuffer, GpuBufferSlice gpuBufferSlice, Matrix4f matrix4f, Matrix3x2f matrix3x2f, Map map, int n, Object object) {
        long l2 = (l = a ^ l) ^ 0x288BE670FE36L;
        if ((n & 2) != 0) {
            Framebuffer framebuffer2 = Globals.getMc().getFramebuffer();
            Intrinsics.checkNotNullExpressionValue(framebuffer2, "getMainRenderTarget(...)");
            framebuffer = framebuffer2;
        }
        if ((n & 4) != 0) {
            gpuBufferSlice = null;
        }
        if ((n & 8) != 0) {
            matrix4f = null;
        }
        if ((n & 0x10) != 0) {
            matrix3x2f = null;
        }
        if ((n & 0x20) != 0) {
            map = MapsKt.emptyMap();
        }
        batchedBuffer.draw(l2, renderPipeline, framebuffer, gpuBufferSlice, matrix4f, matrix3x2f, map);
    }

    public final void draw(@NotNull RenderPipeline renderPipeline, @Nullable GpuTextureView gpuTextureView, @Nullable GpuTextureView gpuTextureView2, @Nullable GpuBufferSlice gpuBufferSlice, @Nullable Matrix4f matrix4f, @Nullable Matrix3x2f matrix3x2f, short s, @NotNull Map map) {
        Rect rect;
        Object object;
        block13: {
            Module[] moduleArray;
            Object object2;
            Object object3;
            Matrix4f matrix4f2;
            Matrix4f matrix4f3;
            DynamicUniformStorage dynamicUniformStorage;
            GpuBuffer gpuBuffer;
            GpuBuffer gpuBuffer2;
            Module[] moduleArray2;
            block15: {
                block14: {
                    Module[] moduleArray3 = RenderUtil.zkmGetFlag();
                    Intrinsics.checkNotNullParameter(renderPipeline, "pipeline");
                    Intrinsics.checkNotNullParameter(map, "samplers");
                    moduleArray2 = moduleArray3;
                    if (this.vertexCount == 0) {
                        return;
                    }
                    GpuBuffer gpuBuffer3 = this.format.uploadImmediateIndexBuffer(this.indexBuffer);
                    Intrinsics.checkNotNullExpressionValue(gpuBuffer3, "uploadImmediateIndexBuffer(...)");
                    gpuBuffer2 = gpuBuffer3;
                    GpuBuffer gpuBuffer4 = this.format.uploadImmediateVertexBuffer(this.vertexBuffer);
                    Intrinsics.checkNotNullExpressionValue(gpuBuffer4, "uploadImmediateVertexBuffer(...)");
                    gpuBuffer = gpuBuffer4;
                    Matrix4f matrix4f4 = matrix4f != null && matrix3x2f == null ? RenderState.INSTANCE.getProjection() : new Matrix4f().identity().setOrtho(0.0f, (float)Globals.getMc().getWindow().getScaledWidth(), (float)Globals.getMc().getWindow().getScaledHeight(), 0.0f, -100.0f, 100.0f);
                    dynamicUniformStorage = BufferUniformStorage.INSTANCE.getDynamicUniformStorage();
                    Intrinsics.checkNotNull(matrix4f4);
                    matrix4f3 = matrix4f4;
                    Module[] moduleArray4 = moduleArray2;
                    if (s <= 0) break block14;
                    matrix4f2 = matrix4f;
                    if (matrix4f2 != null) break block15;
                    moduleArray4 = moduleArray2;
                }
                Matrix3x2f matrix3x2f2 = matrix3x2f;
                if (matrix3x2f2 != null) {
                    object3 = matrix3x2f2;
                    Matrix4f matrix4f5 = matrix4f3;
                    DynamicUniformStorage dynamicUniformStorage2 = dynamicUniformStorage;
                    Matrix4f matrix4f6 = this.toMatrix4f((Matrix3x2f)object3);
                    dynamicUniformStorage = dynamicUniformStorage2;
                    matrix4f3 = matrix4f5;
                    matrix4f2 = matrix4f6;
                } else {
                    matrix4f2 = null;
                }
            }
            Matrix4f matrix4f7 = matrix4f2;
            Matrix4f matrix4f8 = matrix4f3;
            object = dynamicUniformStorage.write((DynamicUniformStorage.Uploadable)new ProjectionUniforms(matrix4f8, matrix4f7));
            Intrinsics.checkNotNullExpressionValue(object, "writeUniform(...)");
            GpuBufferSlice gpuBufferSlice2 = object;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Supplier<String> supplier = BatchedBuffer::getRenderPassName;
            GpuTextureView gpuTextureView3 = gpuTextureView;
            Intrinsics.checkNotNull(gpuTextureView3);
            RenderPass renderPass = commandEncoder.createRenderPass(supplier, gpuTextureView3, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty());
            Intrinsics.checkNotNullExpressionValue(renderPass, "createRenderPass(...)");
            object = renderPass;
            Rect rect2 = rect = ScissorStack.INSTANCE.peek();
            if (rect2 != null) {
                object2 = rect2;
                object.enableScissor((int)((Rect)object2).getX(), (int)((Rect)object2).getY(), (int)((Rect)object2).getX1(), (int)((Rect)object2).getY1());
            }
            RenderPassAccessor renderPassAccessor = (RenderPassAccessor)object;
            object2 = ((RenderPassAccessor)object).getEncoder();
            Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type su.catlean.mixins.accessors.GlCommandEncoderAccessor");
            object3 = ((GlCommandEncoderAccessor)object2).getBackendCatlean();
            Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type su.catlean.mixins.accessors.GlDeviceAccessor");
            renderPassAccessor.setPipeline(((GlDeviceAccessor)object3).compilePipelineCachedCatlean(renderPipeline));
            object.setUniform("VertexUBO", gpuBufferSlice2);
            GpuBufferSlice gpuBufferSlice3 = gpuBufferSlice;
            if (gpuBufferSlice3 != null) {
                object2 = gpuBufferSlice3;
                object.setUniform("UBO", (GpuBufferSlice)object2);
            }
            GpuSampler gpuSampler = RenderSystem.getSamplerCache().get(FilterMode.NEAREST);
            Intrinsics.checkNotNullExpressionValue(gpuSampler, "getClampToEdge(...)");
            object3 = gpuSampler;
            object2 = map;
            Iterator iterator2 = object2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry;
                Map.Entry entry2 = entry = iterator2.next();
                String string = (String)entry2.getKey();
                GpuTextureView gpuTextureView4 = (GpuTextureView)entry2.getValue();
                object.bindTexture(string, gpuTextureView4, (GpuSampler)object3);
                moduleArray = moduleArray2;
                if (s > 0) {
                    continue;
                }
                break block13;
            }
            object.setVertexBuffer(0, gpuBuffer);
            object.setIndexBuffer(gpuBuffer2, VertexFormat.IndexType.INT);
            object.drawIndexed(0, 0, this.indexCount, 1);
            moduleArray = moduleArray2;
        }
        Rect rect3 = rect;
        if (rect3 != null) {
            Rect rect4 = rect3;
            object.disableScissor();
        }
        GofraState.INSTANCE.setStopSwapBuffers(true);
        object.close();
        GofraState.INSTANCE.setStopSwapBuffers(false);
        RenderUtil.INSTANCE.h(true);
    }

    public static void draw$default(BatchedBuffer batchedBuffer, long l, RenderPipeline renderPipeline, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2, GpuBufferSlice gpuBufferSlice, Matrix4f matrix4f, Matrix3x2f matrix3x2f, Map map, int n, Object object) {
        int n2 = (int)(((l = a ^ l) ^ 0xBC0301ED2C7L) << 48 >>> 48);
        if ((n & 0x10) != 0) {
            matrix4f = null;
        }
        if ((n & 0x20) != 0) {
            matrix3x2f = null;
        }
        if ((n & 0x40) != 0) {
            map = MapsKt.emptyMap();
        }
        batchedBuffer.draw(renderPipeline, gpuTextureView, gpuTextureView2, gpuBufferSlice, matrix4f, matrix3x2f, (short)n2, map);
    }

    private static final String getRenderPassName() {
        return "ctl-polygon-renderpass";
    }

    public BatchedBuffer() {
        long l = 0L;
        l = a ^ l;
        this(null, 0, false, (int)BatchedBuffer.zkmBootstrap2("r", (int)2544, (long)(0x10BEB690D9BD1954L ^ l)), null);
    }

    public static void zkmSetFlag(String[] stringArray) {
        t = stringArray;
    }

    public static String[] zkmGetFlag() {
        return t;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block31: {
            block30: {
                block29: {
                    block28: {
                        block27: {
                            block26: {
                                BatchedBuffer.a = ZkmKeys.a(4593026781476974556L, 1292558664199173954L, MethodHandles.lookup().lookupClass()).a(178441875227259L);
                                var31 = BatchedBuffer.a ^ 113226160942963L;
                                BatchedBuffer.f = new HashMap<K, V>(13);
                                BatchedBuffer.zkmSetFlag(new String[5]);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[24];
                                var27_4 = 0;
                                var26_5 = "\u00b2T\u00f5\u00b3\u008a\u00961\u00ed\u00fb\u00d5'\u00f2w\u0092\u00c4N\u00f0H\u001b\u00d7\u00c1\u0017&Y\u00faj\b\u0014\u0012\u0093\u0091\u001f\u001e\u008b7g\u00fb~\u001e\u001aO2\u0081\u001dn\u00d3\u0091|\u0082 \u00a8L\u00ad\u0094S\u00a9\u00166\u00d5?\u009fCkiE\u00e1\u00a7\u00c0\u00d9\u00afk\u0096\u00e2\u00eb\r\u009f\u001d\u00a7a\u0085\u0098\u00e5\u0088\u00ff\u00bfr\u0007\u007f\u00fc\u008d\u0091k+Y\u00ebg\u000f\u0080F_\u00e1(\u00fbf\u0080>\u00b0\u008e7\u0096\u00e8i\u0014?\u0013\\\u0012\u00a8\u00ec\u00e6t~\u00ad\u00f4\u00fb\u00f9!R'\u00cb\u00ab.6\u0000\u0093\u0083\u0014\u00e8\u0019m\u00da4\u00f1\u00bd\u00a0\u00bc\u00ce$\tL\u00fa\u0012\u00f5\u008d\u0015\u00c1q'sZU\u00e3\u00b2\u00d5\"\u00db#\u00eb\u000e\u00e8\u00d8:\u000e\\\u00f9\u0005\u000b?\u00a9}\u0017A\u00bb\u00c3\u0097\u00ba \u00ec\u00dac\u008b\u008f\u0085\u00ae4\u00149\u00a7@\u009a\u00af\u008b\u00ff0\u00fe\u00dd\u00bd\u008a\u00a1Ma\u0015\u0001\u00f4\u00adx\b\u00f8\u008c&\u00cen@\u009ay\u00da\u001a\u0007\u00b19\u008b<\u0088\u00f1C\u0014{\u009aDd(\u00f1\u001c\u00a3\u009bV\u00fb\u00b5\u00ac\u0083\u00b0y\u0014\u00f3R\u0004u\u00ba\u007f8\u0010\u0089\u00f6*\u00e6\u0081\f\u00ff\u00d67\u00e4\u00bejv\u00f0A\u00cd\u00d7\f\u00fb\u00f9\u00ca\u009d:\u008bP\u00ec\u00f0D\u009c\u0001`u\u0011\u0019\u00ffj\u00f1n\u00b8\u00b9\u00a1\u00a82\u00a3(\u00d3I\u00a3\u00bc}\u0097KL\u00f5\u008c\u00b6\u0018\n\u008d\u00c0\u00f6'\u00f1\u001c9Sf\u00fcV0#\u00dbD\u00da\rA\u0012T\u00c4\u0086\u00da\u00bb\u00ca\u00bb\u00cd\u0010\u00bf\u0001\u00a7\u00bb\u0093.\u00f3f\u0083\u00bb\u00e1oG\u00aac\u009d\u0010\u00ea'\u00a1\u0090G\u009e\f\u001e\u00b6^\u000b\u0090\u00b9\u00a1\u0017\"\u0018r\u00e2\u0092\u0080\u00ed\u00e5\u00a8\u00fd\u00f3\u00c33\u00041\u009eC\u000fE\u00cd\u0086\u00ab\u00cc\u0006\u0098\u00ac \u00ea\u00f4\u00afH\u00c3D\u00b2\u00bfG\u00a5g\u00bb%4Y\u0085\u00b2\u0015\u00c6P\u00d4\u00fe\u00df\u00b6\u00e92\u00a6B\u00f3\u00d3\u0007\u00a0(\u00b7\u0001\u001e<\u00c1r\u0000\")/\u0000G\u00bd\u00fb\t#\u0096.\u00da\u00b3\r\u00ab\u008a\u00b1K\u008c\u00aak\u00b7\u00a6\u00c1y\u00a6\u00e58\u00b1,\u00c8\u0083\u0000\u0018\u00f8U\u001fN,l\u00eb\u00d7\u000fo\u00f8\u00d2\u00b3\u009d!X\u00c4\u001b\u009a\u009d\u008d\u001f\u00c2\u0080\u0018\u00bd\u00ae\u0098\u00de\nx\u000f>\u00c5y{\u0080\u00f8\u00e3\u00ff\u0018d\u009d1\u001a\u0011\u000f^F\u0010\u00d0\u00f1\u001dWo\u00b4\u00e8\u00cd11\u00ca\u00b6.\u00b3p\u00dd \u000fv-\u0093w\u008aG\u00e8\u00bf\u00f7\u001a\u009ac\u00cc\u0010\u0097\u00e3\u0085o\u0092\u00c9s q\u009aK\u008f\u00ee\u00a2^;\u00a1\u0010\u00c2\u00df\u0083z\u00b9\u009e/ ,\u00a6v\u00eb\u00a4\\H\u0090HY\u0005\u008b)Lx\u0096M&S\u00bf\u00f4K\u0013\u00d6\u00afa\u00bb\u00d2\u0099<w\u008ey\u0086\u00af8\u00d0\u00c9|\"\u00degh\u0088\u0094\u00cb\"{nx*^\u00fb\u00ab-kgn\u00e1\u0084D\u00af\u00f3\u00c6\u000e\ff\u001cu\"\u00aeV`\u0081\u00fa\u0099\u001d,\u0098LJ0\u0004;^\u00df\u00ae]\u0013E\u00b3\u00e3i\b\u00aag\u00f3 \u00d5\u00fa\u008cQ\u00afZw\u00bc\u00a3\u00e5\u00af\u001e\u001e(\u0005\u00bcX\u00bakhS\u007f\r\u0081P7Z\u00a9T\u0006\u0095\u00dd\u0010\u00e0a\u00a1\u0013\u00a7Y}\u00ba\u00a58z\u00d5\u0095\u001e\u00ebI\u0010\u008d$\u00e1?M(\u00f2\u00ccsU<\u00dc\u0019\u0089\u00d4\u00158\u0084X\u00f8\u00eeS\"\u008e\u00cf\u00b0\u00a8>|Q\u0018\u00f3\u00eaP\u00e5\u0014\u00ab\u0099:\u001d\u00cd)\u0003b\u0080\u00b1.[N\u00ec\u00bd-N\u00a3\u00dc\u00b8\u009f\u00d9\u00eb~!\u00a8\u007f=\u008d\u0004y8\u008f;\u001c\u008dI\u001095\u00e7\u00c0\u00d4@\u00af\u00f0a\u0015\u00e19Y\u00a3J\u00f8\u0010\u00bef\u00a2\u0002Q\u00cd\u00bf\u008b\u00a5a\r\u000b+\u00bf]\u00ee(\u00da\u00f9y\u00c5\u00b4ZO(I[\u0096R\u0087\u00ae|\u0090\u0089:\u0095\u00b1Mr\u00196\u001eb6&M\u00f8\u00cc\u008c\u001f@\bV\u001d\u00a2\u00a4|($~J\u00fc\u0092\u001d\u0003\u00ce7\u00c6df\u00fcf\u007f\u00b9\u00c5\u00dd{\u0086\u00ecv\u0083\u00f1\u00fc\u00a9;\u0080C\u00c8\u00bb;u\u00a2\u00f6x\u00c6\u00ac\u00f7\u00d8";
                                var28_6 = "\u00b2T\u00f5\u00b3\u008a\u00961\u00ed\u00fb\u00d5'\u00f2w\u0092\u00c4N\u00f0H\u001b\u00d7\u00c1\u0017&Y\u00faj\b\u0014\u0012\u0093\u0091\u001f\u001e\u008b7g\u00fb~\u001e\u001aO2\u0081\u001dn\u00d3\u0091|\u0082 \u00a8L\u00ad\u0094S\u00a9\u00166\u00d5?\u009fCkiE\u00e1\u00a7\u00c0\u00d9\u00afk\u0096\u00e2\u00eb\r\u009f\u001d\u00a7a\u0085\u0098\u00e5\u0088\u00ff\u00bfr\u0007\u007f\u00fc\u008d\u0091k+Y\u00ebg\u000f\u0080F_\u00e1(\u00fbf\u0080>\u00b0\u008e7\u0096\u00e8i\u0014?\u0013\\\u0012\u00a8\u00ec\u00e6t~\u00ad\u00f4\u00fb\u00f9!R'\u00cb\u00ab.6\u0000\u0093\u0083\u0014\u00e8\u0019m\u00da4\u00f1\u00bd\u00a0\u00bc\u00ce$\tL\u00fa\u0012\u00f5\u008d\u0015\u00c1q'sZU\u00e3\u00b2\u00d5\"\u00db#\u00eb\u000e\u00e8\u00d8:\u000e\\\u00f9\u0005\u000b?\u00a9}\u0017A\u00bb\u00c3\u0097\u00ba \u00ec\u00dac\u008b\u008f\u0085\u00ae4\u00149\u00a7@\u009a\u00af\u008b\u00ff0\u00fe\u00dd\u00bd\u008a\u00a1Ma\u0015\u0001\u00f4\u00adx\b\u00f8\u008c&\u00cen@\u009ay\u00da\u001a\u0007\u00b19\u008b<\u0088\u00f1C\u0014{\u009aDd(\u00f1\u001c\u00a3\u009bV\u00fb\u00b5\u00ac\u0083\u00b0y\u0014\u00f3R\u0004u\u00ba\u007f8\u0010\u0089\u00f6*\u00e6\u0081\f\u00ff\u00d67\u00e4\u00bejv\u00f0A\u00cd\u00d7\f\u00fb\u00f9\u00ca\u009d:\u008bP\u00ec\u00f0D\u009c\u0001`u\u0011\u0019\u00ffj\u00f1n\u00b8\u00b9\u00a1\u00a82\u00a3(\u00d3I\u00a3\u00bc}\u0097KL\u00f5\u008c\u00b6\u0018\n\u008d\u00c0\u00f6'\u00f1\u001c9Sf\u00fcV0#\u00dbD\u00da\rA\u0012T\u00c4\u0086\u00da\u00bb\u00ca\u00bb\u00cd\u0010\u00bf\u0001\u00a7\u00bb\u0093.\u00f3f\u0083\u00bb\u00e1oG\u00aac\u009d\u0010\u00ea'\u00a1\u0090G\u009e\f\u001e\u00b6^\u000b\u0090\u00b9\u00a1\u0017\"\u0018r\u00e2\u0092\u0080\u00ed\u00e5\u00a8\u00fd\u00f3\u00c33\u00041\u009eC\u000fE\u00cd\u0086\u00ab\u00cc\u0006\u0098\u00ac \u00ea\u00f4\u00afH\u00c3D\u00b2\u00bfG\u00a5g\u00bb%4Y\u0085\u00b2\u0015\u00c6P\u00d4\u00fe\u00df\u00b6\u00e92\u00a6B\u00f3\u00d3\u0007\u00a0(\u00b7\u0001\u001e<\u00c1r\u0000\")/\u0000G\u00bd\u00fb\t#\u0096.\u00da\u00b3\r\u00ab\u008a\u00b1K\u008c\u00aak\u00b7\u00a6\u00c1y\u00a6\u00e58\u00b1,\u00c8\u0083\u0000\u0018\u00f8U\u001fN,l\u00eb\u00d7\u000fo\u00f8\u00d2\u00b3\u009d!X\u00c4\u001b\u009a\u009d\u008d\u001f\u00c2\u0080\u0018\u00bd\u00ae\u0098\u00de\nx\u000f>\u00c5y{\u0080\u00f8\u00e3\u00ff\u0018d\u009d1\u001a\u0011\u000f^F\u0010\u00d0\u00f1\u001dWo\u00b4\u00e8\u00cd11\u00ca\u00b6.\u00b3p\u00dd \u000fv-\u0093w\u008aG\u00e8\u00bf\u00f7\u001a\u009ac\u00cc\u0010\u0097\u00e3\u0085o\u0092\u00c9s q\u009aK\u008f\u00ee\u00a2^;\u00a1\u0010\u00c2\u00df\u0083z\u00b9\u009e/ ,\u00a6v\u00eb\u00a4\\H\u0090HY\u0005\u008b)Lx\u0096M&S\u00bf\u00f4K\u0013\u00d6\u00afa\u00bb\u00d2\u0099<w\u008ey\u0086\u00af8\u00d0\u00c9|\"\u00degh\u0088\u0094\u00cb\"{nx*^\u00fb\u00ab-kgn\u00e1\u0084D\u00af\u00f3\u00c6\u000e\ff\u001cu\"\u00aeV`\u0081\u00fa\u0099\u001d,\u0098LJ0\u0004;^\u00df\u00ae]\u0013E\u00b3\u00e3i\b\u00aag\u00f3 \u00d5\u00fa\u008cQ\u00afZw\u00bc\u00a3\u00e5\u00af\u001e\u001e(\u0005\u00bcX\u00bakhS\u007f\r\u0081P7Z\u00a9T\u0006\u0095\u00dd\u0010\u00e0a\u00a1\u0013\u00a7Y}\u00ba\u00a58z\u00d5\u0095\u001e\u00ebI\u0010\u008d$\u00e1?M(\u00f2\u00ccsU<\u00dc\u0019\u0089\u00d4\u00158\u0084X\u00f8\u00eeS\"\u008e\u00cf\u00b0\u00a8>|Q\u0018\u00f3\u00eaP\u00e5\u0014\u00ab\u0099:\u001d\u00cd)\u0003b\u0080\u00b1.[N\u00ec\u00bd-N\u00a3\u00dc\u00b8\u009f\u00d9\u00eb~!\u00a8\u007f=\u008d\u0004y8\u008f;\u001c\u008dI\u001095\u00e7\u00c0\u00d4@\u00af\u00f0a\u0015\u00e19Y\u00a3J\u00f8\u0010\u00bef\u00a2\u0002Q\u00cd\u00bf\u008b\u00a5a\r\u000b+\u00bf]\u00ee(\u00da\u00f9y\u00c5\u00b4ZO(I[\u0096R\u0087\u00ae|\u0090\u0089:\u0095\u00b1Mr\u00196\u001eb6&M\u00f8\u00cc\u008c\u001f@\bV\u001d\u00a2\u00a4|($~J\u00fc\u0092\u001d\u0003\u00ce7\u00c6df\u00fcf\u007f\u00b9\u00c5\u00dd{\u0086\u00ecv\u0083\u00f1\u00fc\u00a9;\u0080C\u00c8\u00bb;u\u00a2\u00f6x\u00c6\u00ac\u00f7\u00d8".length();
                                var25_7 = 144;
                                var24_8 = -1;
lbl21:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block26;
                                    break;
                                }
lbl26:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = BatchedBuffer.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00b5M~b\u008d\u008cm(%\u0093\u00d7\u00b6]\u00f7N\u00d8 4\u0080*\u00e8\u00ba\u00e2\\\u0011f\u0019\u00ff\u001d\u00d3\u001e\u00c3U\u00bf\u00aeT\u0093\u00d6\u00f9\u00f4\u00dd\u00af\u0086J\u001f\t\u009f\u0093F";
                                    var28_6 = "\u00b5M~b\u008d\u008cm(%\u0093\u00d7\u00b6]\u00f7N\u00d8 4\u0080*\u00e8\u00ba\u00e2\\\u0011f\u0019\u00ff\u001d\u00d3\u001e\u00c3U\u00bf\u00aeT\u0093\u00d6\u00f9\u00f4\u00dd\u00af\u0086J\u001f\t\u009f\u0093F".length();
                                    var25_7 = 16;
                                    var24_8 = -1;
lbl35:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block26;
                                        break;
                                    }
                                    break;
                                }
lbl40:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = BatchedBuffer.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block27;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        BatchedBuffer.c = var29_3;
                        BatchedBuffer.e = new String[24];
                        BatchedBuffer.l = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[20];
                        var14_13 = 0;
                        var15_14 = "\u0010\u00ac9\u00f6\u0002\u00ab\u00deE\u00a2\u009f\u00d7\u00f7\u00a9/\u00f7\b}\u00a5\u00baa\u00b0(\u0089\u00f8\u00e8|\u00d8\u0095\u00af\u00b0\u008f\u000bl\u00f60\u00eb\u00b1\u00a7YSVu]\u0092\u009ef\u00daM\u00ac\u0081\"\u00ed\u00ce\u0017\u00c7*\u0090\u00c6\u00a0\u009cJ\u0082E*\u008aOA\u001c\u00a3\u0002Py\u00e9\u0089mp\u00d2\u00ad\u00d1\u00a3\u00ff\b\u0017H\u00fb\u00d8\u0084\u00e4\u00f8\u0007\u0085\u00eda\r\u00ea\u00f9\u00ba\u00b9\u00ebT\u00fe\u00f5\r:\u00e3@\u008f\u00ffO:\u0090\u0014g\u0081\u00a7\u00e8\u0092!\u00af\u0087\u0001\u008a\u00d8\u00b4@\u00d2\u00fcF\u0080K\u00b6@\u00cb{r\u00f4\u00cf\u00af\u009d\u00a3\n\u007f\u00aa,";
                        var16_15 = "\u0010\u00ac9\u00f6\u0002\u00ab\u00deE\u00a2\u009f\u00d7\u00f7\u00a9/\u00f7\b}\u00a5\u00baa\u00b0(\u0089\u00f8\u00e8|\u00d8\u0095\u00af\u00b0\u008f\u000bl\u00f60\u00eb\u00b1\u00a7YSVu]\u0092\u009ef\u00daM\u00ac\u0081\"\u00ed\u00ce\u0017\u00c7*\u0090\u00c6\u00a0\u009cJ\u0082E*\u008aOA\u001c\u00a3\u0002Py\u00e9\u0089mp\u00d2\u00ad\u00d1\u00a3\u00ff\b\u0017H\u00fb\u00d8\u0084\u00e4\u00f8\u0007\u0085\u00eda\r\u00ea\u00f9\u00ba\u00b9\u00ebT\u00fe\u00f5\r:\u00e3@\u008f\u00ffO:\u0090\u0014g\u0081\u00a7\u00e8\u0092!\u00af\u0087\u0001\u008a\u00d8\u00b4@\u00d2\u00fcF\u0080K\u00b6@\u00cb{r\u00f4\u00cf\u00af\u009d\u00a3\n\u007f\u00aa,".length();
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block28;
                            break;
                        }
lbl79:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "Q5.\u009bF\u009ch#\u00d4\u0099S:\u0007\u00c3D\u0015";
                            var16_15 = "Q5.\u009bF\u009ch#\u00d4\u0099S:\u0007\u00c3D\u0015".length();
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block28;
                                break;
                            }
                            break;
                        }
lbl92:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block29;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                BatchedBuffer.g = var17_12;
                BatchedBuffer.i = new Integer[20];
                BatchedBuffer.p = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var31 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[5];
                var3_23 = 0;
                var4_24 = "\u00b20e5\u0018\u00e2\u00ed\u00a8\u00a3\u00fd\u0081Y\u008f\u00eb\f/W\u0016\u00bf\u00c7_\u00dadc";
                var5_25 = "\u00b20e5\u0018\u00e2\u00ed\u00a8\u00a3\u00fd\u0081Y\u008f\u00eb\f/W\u0016\u00bf\u00c7_\u00dadc".length();
                var2_26 = 0;
                while (true) {
                    var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
                    v18 = var6_22;
                    v19 = var3_23++;
                    v20 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
                    v21 = -1;
                    break block30;
                    break;
                }
lbl132:
                // 1 sources

                while (true) {
                    v18[v19] = v22;
                    if (var2_26 < var5_25) ** continue;
                    var4_24 = "4\u00fe\u00d5;\u009bC/\u00de\u0016\u0016\u00d4\t\u00e5\u00f5\u0018\u00d3";
                    var5_25 = "4\u00fe\u00d5;\u009bC/\u00de\u0016\u0016\u00d4\t\u00e5\u00f5\u0018\u00d3".length();
                    var2_26 = 0;
                    while (true) {
                        var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
                        v18 = var6_22;
                        v19 = var3_23++;
                        v20 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
                        v21 = 0;
                        break block30;
                        break;
                    }
                    break;
                }
lbl145:
                // 1 sources

                while (true) {
                    v18[v19] = v22;
                    if (var2_26 < var5_25) ** continue;
                    break block31;
                    break;
                }
            }
            var8_28 = v20;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            v22 = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
            switch (v21) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl158:
                // 1 sources

                ** continue;
            }
        }
        BatchedBuffer.n = var6_22;
        BatchedBuffer.o = new Long[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4E16;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/g7", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            BatchedBuffer.e[n2] = BatchedBuffer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BatchedBuffer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/g7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1C35;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])BatchedBuffer.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    BatchedBuffer.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/g7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BatchedBuffer.i[n2] = n3;
        }
        return i[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BatchedBuffer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/g7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x69FB;
        if (o[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = BatchedBuffer.n[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])p.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    p.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/g7", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            BatchedBuffer.o[n2] = l4;
        }
        return o[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = BatchedBuffer.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap3(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_2().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/g7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BatchedBuffer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BatchedBuffer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findStatic(BatchedBuffer.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

