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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BorderedRectUniforms
implements DynamicUniformStorage.Uploadable {
    private final float uSizeX;
    private final float uSizeY;
    private final float radius;
    private final float uBorderWidth;
    @NotNull
    private final Color uBorderColor;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map j;

    public BorderedRectUniforms(float f, float f2, float f3, float f4, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "uBorderColor");
        this.uSizeX = f;
        this.uSizeY = f2;
        this.radius = f3;
        this.uBorderWidth = f4;
        this.uBorderColor = color;
    }

    public final float getUSizeX() {
        return this.uSizeX;
    }

    public final float getUSizeY() {
        return this.uSizeY;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getUBorderWidth() {
        return this.uBorderWidth;
    }

    @NotNull
    public final Color getUBorderColor() {
        return this.uBorderColor;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putVec2(this.uSizeX, this.uSizeY).putFloat(this.radius).putFloat(this.uBorderWidth).putVec4(RenderUtil.INSTANCE.getRed(this.uBorderColor), RenderUtil.INSTANCE.getGreen(this.uBorderColor), RenderUtil.INSTANCE.getBlue(this.uBorderColor), RenderUtil.INSTANCE.getAlpha(this.uBorderColor));
    }

    public final float getUSizeX2() {
        return this.uSizeX;
    }

    public final float getUSizeY2() {
        return this.uSizeY;
    }

    public final float getRadius2() {
        return this.radius;
    }

    public final float getUBorderWidth2() {
        return this.uBorderWidth;
    }

    @NotNull
    public final Color getUBorderColor2() {
        return this.uBorderColor;
    }

    @NotNull
    public final BorderedRectUniforms copy(float f, float f2, float f3, float f4, @NotNull Color color) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)BorderedRectUniforms.zkmBootstrap("q", (int)19393, (long)(0x2AB46EAE6DD12A3AL ^ l))));
        return new BorderedRectUniforms(f, f2, f3, f4, color);
    }

    public static BorderedRectUniforms copy$default(BorderedRectUniforms borderedRectUniforms, float f, float f2, float f3, float f4, long l, Color color, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7259859138372536574L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = borderedRectUniforms.uSizeX;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = borderedRectUniforms.uSizeY;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f3 = borderedRectUniforms.radius;
                }
                n2 = n & BorderedRectUniforms.zkmBootstrap2("f", (int)18500, (long)(0x138D4CB4AAE7086DL ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                f4 = borderedRectUniforms.uBorderWidth;
            }
            n2 = n & BorderedRectUniforms.zkmBootstrap2("f", (int)30456, (long)(0x7B8E66663127B6D3L ^ l));
        }
        if (n2 != 0) {
            color = borderedRectUniforms.uBorderColor;
        }
        return borderedRectUniforms.copy(f, f2, f3, f4, color);
    }

    @NotNull
    public String toString() {
        Color color = this.uBorderColor;
        float f = this.uBorderWidth;
        float f2 = this.radius;
        float f3 = this.uSizeY;
        float f4 = this.uSizeX;
        return "UniformData(uSizeX=" + f4 + ", uSizeY=" + f3 + ", radius=" + f2 + ", uBorderWidth=" + f + ", uBorderColor=" + color + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.uSizeX);
        n = n * 31 + Float.hashCode(this.uSizeY);
        n = n * 31 + Float.hashCode(this.radius);
        n = n * 31 + Float.hashCode(this.uBorderWidth);
        n = n * 31 + this.uBorderColor.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x1D720F58F4A8L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5431687561100896095L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof BorderedRectUniforms)) {
                return false;
            }
            object2 = object;
        }
        BorderedRectUniforms borderedRectUniforms = (BorderedRectUniforms)object2;
        int n = Float.compare(this.uSizeX, borderedRectUniforms.uSizeX);
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uSizeY, borderedRectUniforms.uSizeY);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.radius, borderedRectUniforms.radius);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uBorderWidth, borderedRectUniforms.uBorderWidth);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uBorderColor, borderedRectUniforms.uBorderColor) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
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
                        BorderedRectUniforms.a = ZkmKeys.a(-5614685526224578887L, -1720000299022954843L, MethodHandles.lookup().lookupClass()).a(166248311084952L);
                        BorderedRectUniforms.e = new HashMap<K, V>(13);
                        var11 = BorderedRectUniforms.a ^ 8468846406543L;
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
                        var20_3 = new String[8];
                        var18_4 = 0;
                        var17_5 = "\u009a\u00a2\u009cX\u001b\u00b5\u00a1\u00fc\u0019\u00e2\u00bc\u008b-\u00e1\u00df\u0010 \u00a5.\u00dc\u00b6\u0088\u00e1\u009eY\u00c6c\u00a6\u00abvU\u000f#s\u0005\u00d7p\u009c\r\u009daG^\u00ca\u001c\u0011\u00f7\u0010Y \u009a\u0016\u0088YP\u00fa\u009dB,\u00bd\u00b3+\u00c1\u00cc\u0004\u0001\rFK\u00ebmG\u00df\u0002~\u00b0\u00c3ht\u000elM \u0087A\u00b9\u000f\u0095s\u0013\u00b7\u00f2\u00cd\u0086i\u00f4\u00ba\u00bd\u0097\u00ad\u00c9\u0095\u00e0\u0087\u008f\u00d3o\u00a8\t\u008d\u0086\u00b3\u00ed\u00dc\u00a2 \u0001+\u00a1\u00c6\u00ec\u00d7-\u00bah\u00ee)\u00c2\u00dd\u00b2\u00bb\u00b2\u00e07Cc\u00f1h\u008c\u00b0\u00a0\u00a6\u00e7E3\u00ba}\u00c8 \u00bc\u009d\u00b9\u008b\u00fd\u0093H\u00a0T\u000fy\u00b8\u00c3i\u00cb\u00e1\u000e\u00bf'\u008fAF\u00ac\u008cBa\u001a\u0018\u00c6dB6";
                        var19_6 = 181;
                        var16_7 = 16;
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
                            var20_3[var18_4++] = BorderedRectUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00c8\u00dc_\u0016\u00e6\t\u00d3j\u0007\u0082q\u00cbl#\u00acM\n\u0083\u00f5|u\u009a\u00aa\u00dba\u00d5i\"\u00de\u0098[0(T\u00b5\u00bei\u00c4\u007f\u00af5NP^\u0092,\u000e\u00e7\u00ad\u00d9\u00fc\u0000\\:\u00e4W>\u00e4\u00bd\u00e3f\u0091\u00ba\u0014l\u00eb$\u00f70\t\u00ed\u00ba\u0098";
                            var19_6 = 73;
                            var16_7 = 32;
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
                            var20_3[var18_4++] = BorderedRectUniforms.zkmDecodeUtf8(var21_9).intern();
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
                BorderedRectUniforms.c = var20_3;
                BorderedRectUniforms.d = new String[8];
                BorderedRectUniforms.j = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u00df\u00e0\u00ads\u00ce\u00b5\u0003\u00a8\u00fb.K4\u0098W\u00fa\u00cc";
                var5_15 = 16;
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
                    var4_14 = "\u00e7E\u00d0\u00a9\u0088>\u00ecc\u00d9\u00ea`d?\u00d5\u00b9\u00dc";
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
        BorderedRectUniforms.f = var6_12;
        BorderedRectUniforms.g = new Integer[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7049;
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
                throw new RuntimeException("su/catlean/jd", exception);
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
            BorderedRectUniforms.d[n2] = BorderedRectUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BorderedRectUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x25DB;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/jd", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BorderedRectUniforms.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BorderedRectUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/jd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BorderedRectUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BorderedRectUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

