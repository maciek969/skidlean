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

public final class LineUniforms
implements DynamicUniformStorage.Uploadable {
    private final float halfTexelSizeX;
    private final float halfTexelSizeY;
    @NotNull
    private final Color color;
    private final float lineWidth;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public LineUniforms(float f, float f2, @NotNull Color color, float f3) {
        Intrinsics.checkNotNullParameter(color, "color");
        this.halfTexelSizeX = f;
        this.halfTexelSizeY = f2;
        this.color = color;
        this.lineWidth = f3;
    }

    public final float getHalfTexelSizeX() {
        return this.halfTexelSizeX;
    }

    public final float getHalfTexelSizeY() {
        return this.halfTexelSizeY;
    }

    @NotNull
    public final Color getColor() {
        return this.color;
    }

    public final float getLineWidth() {
        return this.lineWidth;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putVec2(this.halfTexelSizeX, this.halfTexelSizeY).putVec4(RenderUtil.INSTANCE.getRed(this.color), RenderUtil.INSTANCE.getGreen(this.color), RenderUtil.INSTANCE.getBlue(this.color), RenderUtil.INSTANCE.getAlpha(this.color)).putFloat(this.lineWidth);
    }

    public final float getHalfTexelSizeX2() {
        return this.halfTexelSizeX;
    }

    public final float getHalfTexelSizeY2() {
        return this.halfTexelSizeY;
    }

    @NotNull
    public final Color getColor2() {
        return this.color;
    }

    public final float getLineWidth2() {
        return this.lineWidth;
    }

    @NotNull
    public final LineUniforms copy(float f, float f2, @NotNull Color color, float f3) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)LineUniforms.zkmBootstrap("a", (int)8410, (long)(0x12C1BF44F91460B6L ^ l))));
        return new LineUniforms(f, f2, color, f3);
    }

    public static LineUniforms copy$default(LineUniforms lineUniforms, long l, float f, float f2, Color color, float f3, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4204502894525732455L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = lineUniforms.halfTexelSizeX;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = lineUniforms.halfTexelSizeY;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                color = lineUniforms.color;
            }
            n2 = n & LineUniforms.zkmBootstrap2("l", (int)10450, (long)(0x27FCC629CDC21E5AL ^ l));
        }
        if (n2 != 0) {
            f3 = lineUniforms.lineWidth;
        }
        return lineUniforms.copy(f, f2, color, f3);
    }

    @NotNull
    public String toString() {
        float f = this.lineWidth;
        Color color = this.color;
        float f2 = this.halfTexelSizeY;
        float f3 = this.halfTexelSizeX;
        return "UniformData(halfTexelSizeX=" + f3 + ", halfTexelSizeY=" + f2 + ", color=" + color + ", lineWidth=" + f + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.halfTexelSizeX);
        n = n * 31 + Float.hashCode(this.halfTexelSizeY);
        n = n * 31 + this.color.hashCode();
        n = n * 31 + Float.hashCode(this.lineWidth);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x7359E9632C68L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8328853279217393576L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof LineUniforms)) {
                return false;
            }
            object2 = object;
        }
        LineUniforms lineUniforms = (LineUniforms)object2;
        int n = Float.compare(this.halfTexelSizeX, lineUniforms.halfTexelSizeX);
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.halfTexelSizeY, lineUniforms.halfTexelSizeY);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.color, lineUniforms.color);
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.lineWidth, lineUniforms.lineWidth);
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
        block16: {
            block15: {
                block14: {
                    block13: {
                        LineUniforms.a = ZkmKeys.a(-6525553850541739973L, -2200340985123166761L, MethodHandles.lookup().lookupClass()).a(63127432282481L);
                        LineUniforms.d = new HashMap<K, V>(13);
                        var11 = LineUniforms.a ^ 7705093482594L;
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
                        var20_3 = new String[7];
                        var18_4 = 0;
                        var17_5 = "\u00a5\u0086\u00e7\u00eej\n&0\u0002S\u00d4\u00bc\u00ad]\u0004v\u0012\u008em\u0081L'\u00d6G'\u0092\u00a2\u0084\u00bb\u0090\u00a0\u0003\u0095\u00a2\u009b\u00fa\u00c3,Iy \u000f]\u00far%\u0018b\u0001x\u0090S\u00ae$\u0081\u00d3\\\u00c8\u0093\u00854\u00a6\u00b03\u00a2\u00e5\u00bc\u00fb\u00c94xe\u00c7\u0010G\u00ac,\u00a4\u0087\u00f5\\\u00a8m\u00bc\u00c7T\u001e\u00e5_'8.\u00dd\u001f\u00f6\u00bax,l\u0080c\u0092\u00b38\u00e6E\u00c9\u00e5\u0001\u0098\u00fb\u00ef'\u00be\u0095?qS{\u00ac\u00e5\u00f7m\u00ec\u00bb>?1\u00ef\u00ec\u00a37N+\u001b\u00b2?\u00a07Z\u00a5\u0012:\f\u00b9k\u00e3\u0010\u0086*E\u00e0\u00ac\u001b\u0014\u00c6\u0082%\u00ce\u00f8\u00eb\u00ac\u00e0#";
                        var19_6 = 164;
                        var16_7 = 40;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = LineUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "P\u0005\u0014\u00f7\u00ca\u00ae\n\u009d\u00db\u001c-\u0088\u009f\u00a7j\u00e8j\u0083\u00a3\u00aa\b\u00ee\u0007\u00ea\u00bf\u00ab\u00da\n\u00a1\u00e5\u00e1+\u0010S\u00b4Wc\u00f1\u0017\u00d1\u00a0\u00e5\u00ff\u0084\u00d1\u00c1\u00e9\u00c57";
                            var19_6 = 49;
                            var16_7 = 32;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = LineUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
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
                LineUniforms.b = var20_3;
                LineUniforms.c = new String[7];
                LineUniforms.g = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "B{\u00eb^}/\u001b\u00c2E\u00f2\u000b1b\r\u0015\u0096\u0091+:\u00bc\u00e1&R\u00c3";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
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
        LineUniforms.e = var6_12;
        LineUniforms.f = new Integer[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x350;
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
                throw new RuntimeException("su/catlean/z0", exception);
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
            LineUniforms.c[n2] = LineUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LineUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/z0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xDE3;
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
                throw new RuntimeException("su/catlean/z0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            LineUniforms.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = LineUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/z0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LineUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(LineUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

