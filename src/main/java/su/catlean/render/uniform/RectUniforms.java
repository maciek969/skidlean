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

public final class RectUniforms
implements DynamicUniformStorage.Uploadable {
    private final float uSizeX;
    private final float uSizeY;
    private final float uRadius;
    private final float uAlpha;
    private final float uBorderWidth;
    @NotNull
    private final Color uColor;
    @NotNull
    private final Color uBorderColor;
    private final float uMix;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public RectUniforms(float f, float f2, float f3, float f4, float f5, @NotNull Color color, @NotNull Color color2, float f6) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)RectUniforms.zkmBootstrap("k", (int)4959, (long)(0x5FD95E642AA37FA6L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RectUniforms.zkmBootstrap("k", (int)30150, (long)(0x6752FF708CD5993CL ^ l))));
        this.uSizeX = f;
        this.uSizeY = f2;
        this.uRadius = f3;
        this.uAlpha = f4;
        this.uBorderWidth = f5;
        this.uColor = color;
        this.uBorderColor = color2;
        this.uMix = f6;
    }

    public final float getUSizeX() {
        return this.uSizeX;
    }

    public final float getUSizeY() {
        return this.uSizeY;
    }

    public final float getURadius() {
        return this.uRadius;
    }

    public final float getUAlpha() {
        return this.uAlpha;
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
        Std140Builder.intoBuffer(byteBuffer).putVec2(this.uSizeX, this.uSizeY).putFloat(this.uRadius).putFloat(this.uAlpha).putVec4(RenderUtil.INSTANCE.getRed(this.uColor), RenderUtil.INSTANCE.getGreen(this.uColor), RenderUtil.INSTANCE.getBlue(this.uColor), RenderUtil.INSTANCE.getAlpha(this.uColor)).putFloat(this.uBorderWidth).putVec4(RenderUtil.INSTANCE.getRed(this.uBorderColor), RenderUtil.INSTANCE.getGreen(this.uBorderColor), RenderUtil.INSTANCE.getBlue(this.uBorderColor), RenderUtil.INSTANCE.getAlpha(this.uBorderColor)).putFloat(this.uMix);
    }

    public final float getUSizeX2() {
        return this.uSizeX;
    }

    public final float getUSizeY2() {
        return this.uSizeY;
    }

    public final float getURadius2() {
        return this.uRadius;
    }

    public final float getUAlpha2() {
        return this.uAlpha;
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
    public final RectUniforms copy(float f, float f2, float f3, float f4, float f5, @NotNull Color color, @NotNull Color color2, float f6) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)RectUniforms.zkmBootstrap("k", (int)20815, (long)(0x76BCA75151BD8EDEL ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)RectUniforms.zkmBootstrap("k", (int)26796, (long)(0x3369F8A4308FB736L ^ l))));
        return new RectUniforms(f, f2, f3, f4, f5, color, color2, f6);
    }

    public static RectUniforms copy$default(RectUniforms rectUniforms, long l, float f, float f2, float f3, float f4, float f5, Color color, Color color2, float f6, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-8504367084600138300L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = rectUniforms.uSizeX;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = rectUniforms.uSizeY;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f3 = rectUniforms.uRadius;
                }
                n2 = n & RectUniforms.zkmBootstrap2("t", (int)10205, (long)(0x1487E7799AA538EFL ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f4 = rectUniforms.uAlpha;
                }
                n2 = n & RectUniforms.zkmBootstrap2("t", (int)27972, (long)(0x6F7E2F17F803F271L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f5 = rectUniforms.uBorderWidth;
                }
                n2 = n & RectUniforms.zkmBootstrap2("t", (int)6488, (long)(0x427510F72BB8066FL ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color = rectUniforms.uColor;
                }
                n2 = n & RectUniforms.zkmBootstrap2("t", (int)21965, (long)(0x5D3F0F25D2D34AFEL ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                color2 = rectUniforms.uBorderColor;
            }
            n2 = n & RectUniforms.zkmBootstrap2("t", (int)23446, (long)(0x1B3033678F16C4A6L ^ l));
        }
        if (n2 != 0) {
            f6 = rectUniforms.uMix;
        }
        return rectUniforms.copy(f, f2, f3, f4, f5, color, color2, f6);
    }

    @NotNull
    public String toString() {
        float f = this.uMix;
        Color color = this.uBorderColor;
        Color color2 = this.uColor;
        float f2 = this.uBorderWidth;
        float f3 = this.uAlpha;
        float f4 = this.uRadius;
        float f5 = this.uSizeY;
        float f6 = this.uSizeX;
        return "UniformData(uSizeX=" + f6 + ", uSizeY=" + f5 + ", uRadius=" + f4 + ", uAlpha=" + f3 + ", uBorderWidth=" + f2 + ", uColor=" + color2 + ", uBorderColor=" + color + ", uMix=" + f + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.uSizeX);
        n = n * 31 + Float.hashCode(this.uSizeY);
        n = n * 31 + Float.hashCode(this.uRadius);
        n = n * 31 + Float.hashCode(this.uAlpha);
        n = n * 31 + Float.hashCode(this.uBorderWidth);
        n = n * 31 + this.uColor.hashCode();
        n = n * 31 + this.uBorderColor.hashCode();
        n = n * 31 + Float.hashCode(this.uMix);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x78AFC6E8409DL;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8150137364388814117L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof RectUniforms)) {
                return false;
            }
            object2 = object;
        }
        RectUniforms rectUniforms = (RectUniforms)object2;
        int n = Float.compare(this.uSizeX, rectUniforms.uSizeX);
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uSizeY, rectUniforms.uSizeY);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uRadius, rectUniforms.uRadius);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uAlpha, rectUniforms.uAlpha);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uBorderWidth, rectUniforms.uBorderWidth);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uColor, rectUniforms.uColor) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uBorderColor, rectUniforms.uBorderColor);
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.uMix, rectUniforms.uMix);
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
                        RectUniforms.a = ZkmKeys.a(-6363162574684976107L, 5767215486636573369L, MethodHandles.lookup().lookupClass()).a(257746452321548L);
                        RectUniforms.d = new HashMap<K, V>(13);
                        var11 = RectUniforms.a ^ 118224647653834L;
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
                        var17_5 = "Zy\u00dbR\\I\u00ebg\u00d6\u0017,\u0093\u0018\u008a\u00bd\u00e4\u00dd)\u00a7c,GWfl\u00af\u00e5|\u00b4\u00b4\u0004*\u0010]\u00a8q\u00ae\u00fa\u00ae\u0081\u0082\u00f6\u00ad\u00c3\u000fa\u0098&\u00f9\u0018\u0013\u007fi\u0098!\u0005uA\u00ed\u00cc/\u00fav\u0003\u00d29\u000f\u001a+o\u00ba\u0017\u0015\u0083\u0018\u00aelD#\u00da\u0089U \\\u009a\u00d0\u001ao<{\u00fd\u000b\u0007`\u0001>\u00f0g} \u00ac\u00b8\u0091\u00e3\u0014\u008e\u00bf@FC\u00c3IK>\u0088\u00f1\u0099:/\u00ab\u0085\u00e3\u00ac6BE\u001a*\u00cb\u00a8#\u00c7 \u00b4\u00d9\u0001*5@Ls\u00e3.c\u00ac>\u00d6%\r\u00bdB[\u00bcz\u00b4\u00cb\u00c7\u0095%\u00fbu\u008b\u009em0\u0018;\u00b9\u00eb=sEp>\u009f\u00a6PD\u00a4\u00cf\u00e4Qy\u00b8\u0097O\u009a\u00b6$\u0092 \u00ae\u00ac\u00b0\u00ccw\u00a6B\u00aax\u00e2\u0092\u00f3\u00f2k}\u00d2\u00e4\u0095V\u0090\u00945\u00bb\u0087\u00bb\u0083\u00a2\u00dc\u00df\"@\u0099 \u00c2lX!\u0014\u00b9B0)\t\u00b0\u00ff\u0003wc\u0086^:\u007f\u0000\u00da\u00f4~\u000b\u00b9\u0099\u00cc\tOt\u00f3\u008b\u0010f\u00e0\u00aa\u00b0{\u00a5\n\u00e7\"A\u00cf\u0097i\u009dU\u00ff\u0010\u0010\t\u0015\u00fb\u00ce\u00b2>#\b\u00a3\u00a0\u00ed\u00ac\u0083k.";
                        var19_6 = 290;
                        var16_7 = 32;
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
                            var20_3[var18_4++] = RectUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00efE\r\u0083\u00d1\nGRD'\u0018\u00abe\u00a36\u00db;\u009bM\u00ddo2\u0012!\u00b4*F\u0081\n\u0099\u0012\u00e6x\u00bf\u0084\u00dc\u00cb\u00bc\u001b\u00eb\u00102\u00d97\u0095\u00dc\u0018/\b\t\u0016\u00b9QO\u00ea\u00dc\u00a4";
                            var19_6 = 57;
                            var16_7 = 40;
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
                            var20_3[var18_4++] = RectUniforms.zkmDecodeUtf8(var21_9).intern();
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
                RectUniforms.b = var20_3;
                RectUniforms.c = new String[13];
                RectUniforms.g = new HashMap<K, V>(13);
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
                var4_14 = "\u00a9X[\u00eav\u008a3\u0080F\u0096\u00d0\u0092\u00cd\u0010\u00ed\u00ecd?\u0091b\u00c6\u00ffM\u0094\u000b'\u00c0!\u00f2]\u00c2h\u00d32\u00ad\u00f4?[\u00dc\u00b5";
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
                    var4_14 = "\u00fe\u00a7\u0013ho\u007f\u00a5\u00d0\u0086X\u00de\u00f7\u001f\u0098\u00ad\u0001";
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
        RectUniforms.e = var6_12;
        RectUniforms.f = new Integer[7];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7896;
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
                throw new RuntimeException("su/catlean/a_", exception);
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
            RectUniforms.c[n2] = RectUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RectUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/a_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x17FB;
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
                throw new RuntimeException("su/catlean/a_", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RectUniforms.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RectUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/a_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RectUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RectUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

