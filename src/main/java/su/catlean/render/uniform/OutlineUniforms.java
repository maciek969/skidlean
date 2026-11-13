/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render.uniform;

import com.mojang.blaze3d.buffers.Std140Builder;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class OutlineUniforms
implements DynamicUniformStorage.Uploadable {
    @NotNull
    private final Vec2f uViewOffset;
    @NotNull
    private final Vec2f uHalfTexelSize;
    @NotNull
    private final Color uColor;
    private final float uBorder;
    private final float alphaX;
    private final float alphaY;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    public OutlineUniforms(@NotNull Vec2f vec2f, @NotNull Vec2f vec2f2, @NotNull Color color, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(vec2f, "uViewOffset");
        Intrinsics.checkNotNullParameter(vec2f2, "uHalfTexelSize");
        Intrinsics.checkNotNullParameter(color, "uColor");
        this.uViewOffset = vec2f;
        this.uHalfTexelSize = vec2f2;
        this.uColor = color;
        this.uBorder = f;
        this.alphaX = f2;
        this.alphaY = f3;
    }

    @NotNull
    public final Vec2f getUViewOffset() {
        return this.uViewOffset;
    }

    @NotNull
    public final Vec2f getUHalfTexelSize() {
        return this.uHalfTexelSize;
    }

    @NotNull
    public final Color getUColor() {
        return this.uColor;
    }

    public final float getUBorder() {
        return this.uBorder;
    }

    public final float getAlphaX() {
        return this.alphaX;
    }

    public final float getAlphaY() {
        return this.alphaY;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putVec2(this.uViewOffset.x, this.uViewOffset.y).putVec4(RenderUtil.INSTANCE.getRed(this.uColor), RenderUtil.INSTANCE.getGreen(this.uColor), RenderUtil.INSTANCE.getBlue(this.uColor), RenderUtil.INSTANCE.getAlpha(this.uColor)).putFloat(this.uBorder).putVec2(this.uHalfTexelSize.x, this.uHalfTexelSize.y).putFloat(this.alphaX).putFloat(this.alphaY);
    }

    @NotNull
    public final Vec2f getUViewOffset2() {
        return this.uViewOffset;
    }

    @NotNull
    public final Vec2f getUHalfTexelSize2() {
        return this.uHalfTexelSize;
    }

    @NotNull
    public final Color getUColor2() {
        return this.uColor;
    }

    public final float getUBorder2() {
        return this.uBorder;
    }

    public final float getAlphaX2() {
        return this.alphaX;
    }

    public final float getAlphaY2() {
        return this.alphaY;
    }

    @NotNull
    public final OutlineUniforms copy(@NotNull Vec2f vec2f, @NotNull Vec2f vec2f2, @NotNull Color color, float f, float f2, float f3) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(vec2f, (String)((Object)OutlineUniforms.zkmBootstrap("u", (int)3102, (long)(0x23DC32982220B5D0L ^ l))));
        Intrinsics.checkNotNullParameter(vec2f2, (String)((Object)OutlineUniforms.zkmBootstrap("u", (int)4931, (long)(0x38DE7883330C2A8CL ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)OutlineUniforms.zkmBootstrap("u", (int)20627, (long)(0x23833B553C366953L ^ l))));
        return new OutlineUniforms(vec2f, vec2f2, color, f, f2, f3);
    }

    public static OutlineUniforms copy$default(OutlineUniforms outlineUniforms, Vec2f vec2f, Vec2f vec2f2, Color color, float f, float f2, short s, int n, float f3, int n2, Object object) {
        int n3 = 0;
        long l = ((long)s << 48 | (long)n << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4627491993512619014L, (long)l);
        int n4 = n2 & 1;
        if (callSite == null) {
            if (n4 != 0) {
                vec2f = outlineUniforms.uViewOffset;
            }
            n4 = n2 & 2;
        }
        CallSite callSite2 = callSite;
        if (s >= 0) {
            if (callSite2 == null) {
                if (n4 != 0) {
                    vec2f2 = outlineUniforms.uHalfTexelSize;
                }
                n4 = n2 & 4;
            }
            callSite2 = callSite;
        }
        if (n > 0) {
            if (callSite2 == null) {
                if (n4 != 0) {
                    color = outlineUniforms.uColor;
                }
                n4 = n2 & OutlineUniforms.zkmBootstrap2("m", (int)15469, (long)(0x180D81E03F23D865L ^ l));
            }
            callSite2 = callSite;
        }
        if (s >= 0) {
            if (callSite2 == null) {
                if (n4 != 0) {
                    f = outlineUniforms.uBorder;
                }
                n4 = n2 & OutlineUniforms.zkmBootstrap2("m", (int)11442, (long)(0x4D71855DC9C848B9L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n4 != 0) {
                f2 = outlineUniforms.alphaX;
            }
            n4 = n2 & OutlineUniforms.zkmBootstrap2("m", (int)18820, (long)(0x3C5D8E58BD382D8EL ^ l));
        }
        if (n4 != 0) {
            f3 = outlineUniforms.alphaY;
        }
        return outlineUniforms.copy(vec2f, vec2f2, color, f, f2, f3);
    }

    @NotNull
    public String toString() {
        float f = this.alphaY;
        float f2 = this.alphaX;
        float f3 = this.uBorder;
        Color color = this.uColor;
        Vec2f vec2f = this.uHalfTexelSize;
        Vec2f vec2f2 = this.uViewOffset;
        return "UniformData(uViewOffset=" + vec2f2 + ", uHalfTexelSize=" + vec2f + ", uColor=" + color + ", uBorder=" + f3 + ", alphaX=" + f2 + ", alphaY=" + f + ")";
    }

    public int hashCode() {
        int n = this.uViewOffset.hashCode();
        n = n * 31 + this.uHalfTexelSize.hashCode();
        n = n * 31 + this.uColor.hashCode();
        n = n * 31 + Float.hashCode(this.uBorder);
        n = n * 31 + Float.hashCode(this.alphaX);
        n = n * 31 + Float.hashCode(this.alphaY);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x13BB153456A6L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2611507893210957828L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof OutlineUniforms)) {
                return false;
            }
            object2 = object;
        }
        OutlineUniforms outlineUniforms = (OutlineUniforms)object2;
        int n = Intrinsics.areEqual(this.uViewOffset, outlineUniforms.uViewOffset);
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uHalfTexelSize, outlineUniforms.uHalfTexelSize);
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uColor, outlineUniforms.uColor);
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.uBorder, outlineUniforms.uBorder);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.alphaX, outlineUniforms.alphaX);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.alphaY, outlineUniforms.alphaY);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = 1;
        }
        return n != 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        OutlineUniforms.a = ZkmKeys.a(-6895901878429067327L, -4870533705573218913L, MethodHandles.lookup().lookupClass()).a(112284425128268L);
                        OutlineUniforms.e = new HashMap<K, V>(13);
                        var11 = OutlineUniforms.a ^ 125776754517350L;
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
                        var17_5 = "\u0089\u00a2\u0015\rp\u0019\u00e8\u0016K\u0094\u00bc\u00e7\u00b37\rV\u0010\u0089A\u00c1\u00ca\u00e8=~\u0018q\u00e5\u0001\u009a\u00b5\u009a\u0010\u0013\u00e4\u0017\u00e2g\u00d6}\u0012\u00a8MI.\r\u00b4}S\u0086\u0018\u0017\u0005\u00d34Y\u0015!F#\u00cf\u00a4\u00d2\u0088k\u00d6o\u00c4\u00c4\u00af\u00a5h{\t\u0092 \u00ee\u00e1]\u001db\u008e\u0086\u00e1\u0094A\u00be\u00a9\u009a\u00ef\u00e4x\u00acQ\u00e6\u00e2\u0090\u00b6L\u0010\u009bj<\u0002\u0080\u00b6\u000b\u00d9\u0018@\u008f\u00bf\u0090\u009e\u00fbr\u0082p~\u00b9\u00f4\u0002\u008cE\u007f\u008c{\u008aW\u008b\u0096\u000e\u00e48\u00da\u0019\u00a8\u007f\u0099(\u00d24\u009e\u00876\u00f3\u00ed\u0081H=M\u0001\\7\u00ef\u00ea\u00af\u0087\u0097\u0085\u00ff^\u0000Lf!Y\b\u00884\u00ae\u001c\u00d6\u0000\u00bb(\u00f7\u0004\u00a3\u00a1\u00b6\u00bf\u0090)\u001e9\u00c9_\b \u0018\u00ad{v\u0094\u00f7\u00b0\u0006\u00cb\u00aeM=\u00a9\u0014\u009f\u00e9\u00cc\u00121qsH\u0011%\u00cf\u0018\u0011\u00c3\u00d2\u0002\u00feKal\u00e4\u00bc\u008e\u00b7\u00f4\u0080SiwBw\u008b\u008a\u00f8\u0097\u007f\u0010[O\u00e2\u00c0,\u00faB\u00e43\u0095T\u008d\u00f3\u00b7\u009bo(\u00b9\u00a8\u00c08\u00c0\u009a\u00deo\n\u000f\u00a4H]\u00f0\u00a5\u001d}\u00d21\u00d6\u00c5^\u0080\u00a7\u00d5q\u00e6\u00c5\u00d7oW\u00d9%[\u0002\u00c4\u001c}7\u00e8\u0018\u00b1\u0089\u0018,5\u00d2\u0013#\u008a\u00f8r^\u00c5\u00b5\u0095\u0096\u00fa\u00d6\u008e-\u00cb\u00a6x ";
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
                            var20_3[var18_4++] = OutlineUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00c2\u0012\u009d\u00e4I\u00ff\u00cd-\u001c\u008e\u00f4<\u0006$'\u009c\u0010\u00ef-/\u00a8\u0016RgZ\u00d9\u00e1\u00f5\u00c1\u00e1\u001d\u00e6\t";
                            var19_6 = 33;
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
                            var20_3[var18_4++] = OutlineUniforms.zkmDecodeUtf8(var21_9).intern();
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
                OutlineUniforms.c = var20_3;
                OutlineUniforms.d = new String[13];
                OutlineUniforms.i = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "e\u0094\u00f5sm\u00f5\u00b2#\"\u00ac0V\u001f\u00af\u00b3\u00ea\u00ee\u00f0\u00e0\u00f01\u0000\r\u0010";
                var5_15 = 24;
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
                    var4_14 = "\u00ea\t\u00e7}\u0003t)\u00a5\u008d1Z\u00ae\u00f7\u001a\u0095\u00c9";
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
        OutlineUniforms.f = var6_12;
        OutlineUniforms.h = new Integer[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4ED;
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
                throw new RuntimeException("su/catlean/c1", exception);
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
            OutlineUniforms.d[n2] = OutlineUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = OutlineUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/c1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2503;
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
                throw new RuntimeException("su/catlean/c1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            OutlineUniforms.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = OutlineUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/c1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(OutlineUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(OutlineUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

