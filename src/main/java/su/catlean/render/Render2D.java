/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 */
package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.client.render.VertexFormats;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BlurPass;
import su.catlean.render.Pipelines;
import su.catlean.render.RoundRectUniforms;
import su.catlean.render.draw.ShapeCommand;
import su.catlean.render.uniform.ShapeUniformStorage;
import su.catlean.zkm.ZkmKeys;

public final class Render2D {
    @NotNull
    private List A = new ArrayList();
    private static boolean o;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public final void drawGradientBackground(@NotNull Matrix3x2fStack matrix3x2fStack, @NotNull Color color, int n, @NotNull Color color2, int n2, boolean bl) {
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
        long l2 = l ^ 0x14A06F0205D2L;
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "stack");
        Intrinsics.checkNotNullParameter(color, "c1");
        Intrinsics.checkNotNullParameter(color2, "c2");
        float f = 0.0f;
        boolean bl2 = Render2D.U();
        boolean bl3 = ((Collection)this.A).isEmpty();
        if (!bl2) {
            bl3 = !bl3;
        }
        if (bl3) {
            Object object;
            VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n3 = 4;
            boolean bl4 = false;
            int n4 = this.A.size();
            VertexFormat vertexFormat2 = vertexFormat;
            BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n4, bl4, n3, defaultConstructorMarker);
            Object object2 = this.A;
            Object object3 = object2.iterator();
            while (object3.hasNext()) {
                object = object3.next();
                ShapeCommand shapeCommand = (ShapeCommand)object;
                float f2 = RangesKt.coerceIn(shapeCommand.getW() / 10000.0f, 0.0f, 1.0f);
                float f3 = RangesKt.coerceIn(shapeCommand.getH() / 10000.0f, 0.0f, 1.0f);
                float f4 = RangesKt.coerceIn(shapeCommand.F(), 0.0f, 1.0f);
                batchedBuffer.vertex(shapeCommand.getM(), shapeCommand.getX() - 1.0f, shapeCommand.getY() + shapeCommand.getH() + 2.0f, f).texture(f2, f3).color(new Color(0.0f, 0.0f, f4, f4)).vertex(shapeCommand.getM(), shapeCommand.getX() - 1.0f, shapeCommand.getY() - 1.0f, f).texture(f2, f3).color(new Color(0.0f, 0.0f, f4, f4)).vertex(shapeCommand.getM(), shapeCommand.getX() + shapeCommand.getW() + 2.0f, shapeCommand.getY() - 1.0f, f).texture(f2, f3).color(new Color(0.0f, 0.0f, f4, f4)).vertex(shapeCommand.getM(), shapeCommand.getX() + shapeCommand.getW() + 2.0f, shapeCommand.getY() + shapeCommand.getH() + 2.0f, f).texture(f2, f3).color(new Color(0.0f, 0.0f, f4, f4));
                f += 0.1f;
                if (!bl2 && !bl2) continue;
            }
            object2 = bl ? Pipelines.INSTANCE.getProgramRectBlurred2() : Pipelines.INSTANCE.getProgramRectBlurred();
            Matrix3x2f matrix3x2f = new Matrix3x2f((Matrix3x2fc)matrix3x2fStack);
            object3 = ShapeUniformStorage.INSTANCE.getDynamicUniformStorage().write((DynamicUniformStorage.Uploadable)new RoundRectUniforms(3.0f, 3.0f, 3.0f, 3.0f, 0.75f, color, color2, 0.0f));
            object = MapsKt.mapOf(TuplesKt.to("Sampler0", BlurPass.INSTANCE.getOutput()));
            BatchedBuffer.draw$default(l2, batchedBuffer, (RenderPipeline)object2, null, (GpuBufferSlice)object3, null, matrix3x2f, object, 10, null);
            this.A.clear();
        }
    }

    public final void drawRoundedRect(@NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, float f5) {
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        Matrix3x2f matrix3x2f = new Matrix3x2f((Matrix3x2fc)matrix3x2fStack);
        this.A.add(new ShapeCommand(matrix3x2f, f, f2, f3, f4, f5));
    }

    public static void drawRoundedRect$default(Render2D render2D, Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, float f5, Object object) {
        f5 = 1.0f;
        render2D.drawRoundedRect(matrix3x2fStack, f, f2, f3, f4, f5);
    }

    public static void zkmSetFlag(boolean bl) {
        o = bl;
    }

    public static boolean zkmGetFlag() {
        return o;
    }

    public static boolean U() {
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        Render2D.a = ZkmKeys.a(4392267268387207078L, 125771913496169175L, MethodHandles.lookup().lookupClass()).a(242678106587575L);
                        var20 = Render2D.a ^ 78760021526594L;
                        Render2D.d = new HashMap<K, V>(13);
                        Render2D.zkmSetFlag(true);
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
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "u\u00c8\u000f\t\u009d\u00b16]@![\u00e7b|\u008b\u00f6(\u00f0>\u009d\u001d\u001e\u00e8-\"7dyo]\u001c9\u00a6\u0015\u00f77e\u00dc\u0001`\u0016\u00dd\u0085&\u00e5\u00ba\\`\u00b6P\u0085q\u00d8\u00c9\u00b0\u00af\u0013\u00108\u0095\u00e9r.X\u0091\u00efsN\u00ce)\u00cd\u00bd\u00bc\u0090";
                        var17_6 = 74;
                        var14_7 = 16;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Render2D.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e2\u0006\u00a6q&\u00e4\u000fP\u00deP\u0091\u00e5\u00e5\u00a7^\u001a^\u00af\u00f6\u00e1p\u00c89)\u0010\u000f5w_kp\u0003\u00cd\u0087\u001e\u001f\u0099\u00fd\u00d5\u0004|";
                            var17_6 = 41;
                            var14_7 = 24;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Render2D.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                Render2D.b = var18_3;
                Render2D.c = new String[5];
                Render2D.g = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "\u00ceK`B\u00df A\u00fa\f\u0000\u009b\u00b8\u00ee\u009e\u001a\u00d3";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl74:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        Render2D.e = var6_12;
        Render2D.f = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x13C9;
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
                throw new RuntimeException("su/catlean/ab", exception);
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
            Render2D.c[n2] = Render2D.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Render2D.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ab" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 9;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ab", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Render2D.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Render2D.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ab" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Render2D.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Render2D.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

