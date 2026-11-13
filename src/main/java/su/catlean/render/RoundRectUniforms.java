/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class RoundRectUniforms
implements DynamicUniformStorage.Uploadable {
    private final float uRadius1;
    private final float uRadius2;
    private final float uRadius3;
    private final float uRadius4;
    private final float uBorderWidth;
    @NotNull
    private final Color uColor;
    @NotNull
    private final Color uBorderColor;
    private final float uMix;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public RoundRectUniforms(float f, float f2, float f3, float f4, float f5, @NotNull Color color, @NotNull Color color2, float f6) {
        Intrinsics.checkNotNullParameter(color, "uColor");
        Intrinsics.checkNotNullParameter(color2, "uBorderColor");
        this.uRadius1 = f;
        this.uRadius2 = f2;
        this.uRadius3 = f3;
        this.uRadius4 = f4;
        this.uBorderWidth = f5;
        this.uColor = color;
        this.uBorderColor = color2;
        this.uMix = f6;
    }

    public final float getURadius1() {
        return this.uRadius1;
    }

    public final float getURadius2() {
        return this.uRadius2;
    }

    public final float getURadius3() {
        return this.uRadius3;
    }

    public final float getURadius4() {
        return this.uRadius4;
    }

    public final float getUBorderWidth() {
        return this.uBorderWidth;
    }

    @NotNull
    public final Color getUColor() {
        return this.uColor;
    }

    @NotNull
    public final Color getUBorderColor() {
        return this.uBorderColor;
    }

    public final float getUMix() {
        return this.uMix;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putVec4(this.uRadius2, this.uRadius1, this.uRadius4, this.uRadius3).putFloat(this.uBorderWidth).putVec4(RenderUtil.INSTANCE.getRed(this.uColor), RenderUtil.INSTANCE.getGreen(this.uColor), RenderUtil.INSTANCE.getBlue(this.uColor), RenderUtil.INSTANCE.getAlpha(this.uColor)).putVec4(RenderUtil.INSTANCE.getRed(this.uBorderColor), RenderUtil.INSTANCE.getGreen(this.uBorderColor), RenderUtil.INSTANCE.getBlue(this.uBorderColor), RenderUtil.INSTANCE.getAlpha(this.uBorderColor)).putFloat(this.uMix);
    }

    public final float getURadius12() {
        return this.uRadius1;
    }

    public final float getURadius22() {
        return this.uRadius2;
    }

    public final float getURadius32() {
        return this.uRadius3;
    }

    public final float getURadius42() {
        return this.uRadius4;
    }

    public final float getUBorderWidth2() {
        return this.uBorderWidth;
    }

    @NotNull
    public final Color getUColor2() {
        return this.uColor;
    }

    @NotNull
    public final Color getUBorderColor2() {
        return this.uBorderColor;
    }

    public final float getUMix2() {
        return this.uMix;
    }

    @NotNull
    public final RoundRectUniforms copy(float f, float f2, float f3, float f4, float f5, @NotNull Color color, @NotNull Color color2, float f6) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)RoundRectUniforms.zkmBootstrap("p", (int)10363, (long)(0x10821309A8B70172L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RoundRectUniforms.zkmBootstrap("p", (int)21720, (long)(0x142644E60B6A7DD6L ^ l))));
        return new RoundRectUniforms(f, f2, f3, f4, f5, color, color2, f6);
    }

    public static RoundRectUniforms copy$default(RoundRectUniforms roundRectUniforms, long l, float f, float f2, float f3, float f4, float f5, Color color, Color color2, float f6, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2971346969046402306L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = roundRectUniforms.uRadius1;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = roundRectUniforms.uRadius2;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f3 = roundRectUniforms.uRadius3;
                }
                n2 = n & RoundRectUniforms.zkmBootstrap2("h", (int)18027, (long)(0x2AE1D745BE7D5D1L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f4 = roundRectUniforms.uRadius4;
                }
                n2 = n & RoundRectUniforms.zkmBootstrap2("h", (int)8930, (long)(0x712141A610B5315EL ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f5 = roundRectUniforms.uBorderWidth;
                }
                n2 = n & RoundRectUniforms.zkmBootstrap2("h", (int)16559, (long)(0x485DE7394F885317L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color = roundRectUniforms.uColor;
                }
                n2 = n & RoundRectUniforms.zkmBootstrap2("h", (int)930, (long)(0x3037C21BDFC79019L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                color2 = roundRectUniforms.uBorderColor;
            }
            n2 = n & RoundRectUniforms.zkmBootstrap2("h", (int)11788, (long)(0xCC31A068B2B3DB2L ^ l));
        }
        if (n2 != 0) {
            f6 = roundRectUniforms.uMix;
        }
        return roundRectUniforms.copy(f, f2, f3, f4, f5, color, color2, f6);
    }

    @NotNull
    public String toString() {
        float f = this.uMix;
        Color color = this.uBorderColor;
        Color color2 = this.uColor;
        float f2 = this.uBorderWidth;
        float f3 = this.uRadius4;
        float f4 = this.uRadius3;
        float f5 = this.uRadius2;
        float f6 = this.uRadius1;
        return "UniformData(uRadius1=" + f6 + ", uRadius2=" + f5 + ", uRadius3=" + f4 + ", uRadius4=" + f3 + ", uBorderWidth=" + f2 + ", uColor=" + color2 + ", uBorderColor=" + color + ", uMix=" + f + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.uRadius1);
        n = n * 31 + Float.hashCode(this.uRadius2);
        n = n * 31 + Float.hashCode(this.uRadius3);
        n = n * 31 + Float.hashCode(this.uRadius4);
        n = n * 31 + Float.hashCode(this.uBorderWidth);
        n = n * 31 + this.uColor.hashCode();
        n = n * 31 + this.uBorderColor.hashCode();
        n = n * 31 + Float.hashCode(this.uMix);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x5785054BA938L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)710451877044807138L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof RoundRectUniforms)) {
                return false;
            }
            object2 = object;
        }
        RoundRectUniforms roundRectUniforms = (RoundRectUniforms)object2;
        int n = Float.compare(this.uRadius1, roundRectUniforms.uRadius1);
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uRadius2, roundRectUniforms.uRadius2);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uRadius3, roundRectUniforms.uRadius3);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uRadius4, roundRectUniforms.uRadius4);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uBorderWidth, roundRectUniforms.uBorderWidth);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uColor, roundRectUniforms.uColor) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uBorderColor, roundRectUniforms.uBorderColor);
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.uMix, roundRectUniforms.uMix);
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
                        RoundRectUniforms.a = ZkmKeys.a(8702468549638356877L, 7637948291742174976L, MethodHandles.lookup().lookupClass()).a(100417182798641L);
                        RoundRectUniforms.e = new HashMap<K, V>(13);
                        var11 = RoundRectUniforms.a ^ 64076957199204L;
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
                        var17_5 = "P\u00a1yRP\u00cci\u00a6\u00e4?D\u00cfm\b\u0096\u008fE\u00ae^\u00c7\u00a6-\u009a\u00fd\u0018\u008e\u00bc\u00a1\u00c9\u001a3\u00aaN\u00f9\u00da\u0003\u0014]\u0015\u0005\bpS*\u0092D\u00c4\u0019\u00db\u0018#o\u009c\u00fd\u00a9\u0010 \u00a0\u00ed\u00c9\u00fcc\u009f\u00ce\u00a5\u00f4\u0087\u009bg&Q@\u009c\u00ad(y8\u0019\u009dB\u00c4vbu\u00f7\u0098\u0007\u00cf\u00e89\u00d2\u0096R\u00b0rr\u001cv2\u00b8\u00cf+\u00f2d\u00c9\u00eb\u0097\u00cf1\u0011\n\u00bf[\u00act\u0018\u0016G\u009c\u0005L\u0091\u00aeI\u00df\u0090\t&\u00ea\u00b0\u009e\u00de\u00ec\u0001[\u0090\u00bd\u00a2QX\u0010\u007fu\u00d5\u00ea\u0019\u0012\u00179\u00de\u0001\u00ec\u0017\u0094\u00a8f\u0096 *0?\u00ff\u00da\u00b7\u00f9\u00c7\u00d0,\u00cd?\u0019\u009f\u0097\u00a7b\u00f5\u0006\u008c\u0085+(\u0087H\u00c3\u00bdM\u00dc\u0018\u0012\u00f0\u0018\u00f7\u0006Roa\u00ee\u00a1\u00cb\u00da;v\u00a5y\u00b7\f,X\u00d7\"\r\u008e\u0015\u00ceo\u0018\u00dc\u0096\u00e5%\u00bc\u00deB\u00f4\u0012lD,\u007f\u00e0`'\u0086W\u00a9R)\u00e7\u00808\u0010^4K\u000e\u0014\u00e60(\u0001X\u00e6z\u00181|\b \u00fb}\u0093\u00ael\u00c8\u00cc\u00b7waP\r+\u00acO\u00d2g\u00a03\u0006\u0017c\u00bcd\u00ba\u0081\u000e\u00adc\u0080\u00b0\u0003";
                        var19_6 = 290;
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
                            var20_3[var18_4++] = RoundRectUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00c3\u009b\"\u0093;0}*W\u00a6aw\u00b8\u0082\u00d9\u0098\u0018\u00c6dU\u000b\u0099Dj\r\u00de\u001aS\u00dd\u001e\u00aak\u00e6\u001a\u0093\u0007\u009ebv:\u00b9";
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
                            var20_3[var18_4++] = RoundRectUniforms.zkmDecodeUtf8(var21_9).intern();
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
                RoundRectUniforms.c = var20_3;
                RoundRectUniforms.d = new String[13];
                RoundRectUniforms.h = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "\u0015\u00e0\t\u00bb(\u00f7\u00d53@\u00a0P\u00ca\u0082\u0092\u0005\u000e\u00a7R\u0084\u00a0>\u0006,2\u0084\u00b9\u00a8\u00fd\u00c4\u00e8\u00cd>!1\u00a9b^\u00d1\u00d0e";
                var5_15 = 40;
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
                    var4_14 = "\u00c0\u00b6.N\u00ba\u00b4\u0089\u00d4\u009ff{F\u00cd\u00f2$\u0094";
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
        RoundRectUniforms.f = var6_12;
        RoundRectUniforms.g = new Integer[7];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7B03;
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
                throw new RuntimeException("su/catlean/du", exception);
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
            RoundRectUniforms.d[n2] = RoundRectUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RoundRectUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/du" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3BB0;
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
                throw new RuntimeException("su/catlean/du", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RoundRectUniforms.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RoundRectUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/du" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RoundRectUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RoundRectUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

