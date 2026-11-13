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
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class GradientUniforms
implements DynamicUniformStorage.Uploadable {
    private final float halfTexelSizeX;
    private final float halfTexelSizeY;
    @NotNull
    private final Color color1;
    @NotNull
    private final Color color2;
    @NotNull
    private final Color color3;
    @NotNull
    private final Color color4;
    private final float lineWidth;
    private final float time;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    public GradientUniforms(float f, float f2, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, float f3, float f4) {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 56 | l << 8 >>> 8) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)11400, (long)(0x77C4117EAFBEB7E9L ^ l2))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)4784, (long)(0x14EEDCAAE3689DBL ^ l2))));
        Intrinsics.checkNotNullParameter(color3, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)23935, (long)(0x12201530CE8BC61DL ^ l2))));
        Intrinsics.checkNotNullParameter(color4, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)31226, (long)(0x109A57E62CE76292L ^ l2))));
        this.halfTexelSizeX = f;
        this.halfTexelSizeY = f2;
        this.color1 = color;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        this.lineWidth = f3;
        this.time = f4;
    }

    public final float getHalfTexelSizeX() {
        return this.halfTexelSizeX;
    }

    public final float getHalfTexelSizeY() {
        return this.halfTexelSizeY;
    }

    @NotNull
    public final Color getColor1() {
        return this.color1;
    }

    @NotNull
    public final Color getColor2() {
        return this.color2;
    }

    @NotNull
    public final Color getColor3() {
        return this.color3;
    }

    @NotNull
    public final Color getColor4() {
        return this.color4;
    }

    public final float getLineWidth() {
        return this.lineWidth;
    }

    public final float getTime() {
        return this.time;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        block0: {
            long l = a ^ 0x1F22BC2BA8F8L;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-6415068228192810297L, (long)l);
            Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
            Std140Builder.intoBuffer(byteBuffer).putVec2(this.halfTexelSizeX, this.halfTexelSizeY).putVec4(RenderUtil.INSTANCE.getRed(this.color1), RenderUtil.INSTANCE.getGreen(this.color1), RenderUtil.INSTANCE.getBlue(this.color1), RenderUtil.INSTANCE.getAlpha(this.color1)).putVec4(RenderUtil.INSTANCE.getRed(this.color2), RenderUtil.INSTANCE.getGreen(this.color2), RenderUtil.INSTANCE.getBlue(this.color2), RenderUtil.INSTANCE.getAlpha(this.color2)).putVec4(RenderUtil.INSTANCE.getRed(this.color3), RenderUtil.INSTANCE.getGreen(this.color3), RenderUtil.INSTANCE.getBlue(this.color3), RenderUtil.INSTANCE.getAlpha(this.color3)).putVec4(RenderUtil.INSTANCE.getRed(this.color4), RenderUtil.INSTANCE.getGreen(this.color4), RenderUtil.INSTANCE.getBlue(this.color4), RenderUtil.INSTANCE.getAlpha(this.color4)).putFloat(this.lineWidth).putFloat(this.time);
            CallSite callSite2 = callSite;
            if (callSite2 == null) break block0;
            Module.zkmSetFlag(new Module[2]);
        }
    }

    public final float getHalfTexelSizeX2() {
        return this.halfTexelSizeX;
    }

    public final float getHalfTexelSizeY2() {
        return this.halfTexelSizeY;
    }

    @NotNull
    public final Color getColor12() {
        return this.color1;
    }

    @NotNull
    public final Color getColor22() {
        return this.color2;
    }

    @NotNull
    public final Color getColor32() {
        return this.color3;
    }

    @NotNull
    public final Color getColor42() {
        return this.color4;
    }

    public final float getLineWidth2() {
        return this.lineWidth;
    }

    public final float getTime2() {
        return this.time;
    }

    @NotNull
    public final GradientUniforms copy(float f, float f2, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, float f3, float f4) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)6216, (long)(0x1F3465C28FBB7973L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)3420, (long)(0x7FAA0122A07CEC61L ^ l))));
        Intrinsics.checkNotNullParameter(color3, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)1423, (long)(0x182322866EC7E4BEL ^ l))));
        Intrinsics.checkNotNullParameter(color4, (String)((Object)GradientUniforms.zkmBootstrap("b", (int)11705, (long)(0x763D909AF99A4C8AL ^ l))));
        return new GradientUniforms(f, f2, color, color2, color3, color4, f3, f4);
    }

    public static GradientUniforms copy$default(GradientUniforms gradientUniforms, float f, float f2, Color color, char c, Color color2, Color color3, Color color4, float f3, long l, float f4, int n, Object object) {
        long l2 = ((long)c << 48 | l << 16 >>> 16) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2936949807934850300L, (long)l2);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = gradientUniforms.halfTexelSizeX;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (c >= '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = gradientUniforms.halfTexelSizeY;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (c >= '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color = gradientUniforms.color1;
                }
                n2 = n & GradientUniforms.zkmBootstrap2("m", (int)13562, (long)(0x2AC701AD5A399497L ^ l2));
            }
            callSite2 = callSite;
        }
        if (c >= '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color2 = gradientUniforms.color2;
                }
                n2 = n & GradientUniforms.zkmBootstrap2("m", (int)20929, (long)(0x203B47CB158871ADL ^ l2));
            }
            callSite2 = callSite;
        }
        if (c >= '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color3 = gradientUniforms.color3;
                }
                n2 = n & GradientUniforms.zkmBootstrap2("m", (int)6416, (long)(0x62014F67B8143978L ^ l2));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    color4 = gradientUniforms.color4;
                }
                n2 = n & GradientUniforms.zkmBootstrap2("m", (int)31633, (long)(0x6FACD0AA8F26DBF8L ^ l2));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                f3 = gradientUniforms.lineWidth;
            }
            n2 = n & GradientUniforms.zkmBootstrap2("m", (int)26632, (long)(0x2504E8CE838C4862L ^ l2));
        }
        if (n2 != 0) {
            f4 = gradientUniforms.time;
        }
        return gradientUniforms.copy(f, f2, color, color2, color3, color4, f3, f4);
    }

    @NotNull
    public String toString() {
        float f = this.time;
        float f2 = this.lineWidth;
        Color color = this.color4;
        Color color2 = this.color3;
        Color color3 = this.color2;
        Color color4 = this.color1;
        float f3 = this.halfTexelSizeY;
        float f4 = this.halfTexelSizeX;
        return "UniformData(halfTexelSizeX=" + f4 + ", halfTexelSizeY=" + f3 + ", color1=" + color4 + ", color2=" + color3 + ", color3=" + color2 + ", color4=" + color + ", lineWidth=" + f2 + ", time=" + f + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.halfTexelSizeX);
        n = n * 31 + Float.hashCode(this.halfTexelSizeY);
        n = n * 31 + this.color1.hashCode();
        n = n * 31 + this.color2.hashCode();
        n = n * 31 + this.color3.hashCode();
        n = n * 31 + this.color4.hashCode();
        n = n * 31 + Float.hashCode(this.lineWidth);
        n = n * 31 + Float.hashCode(this.time);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x524F757231D1L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4598267589560751086L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof GradientUniforms)) {
                return false;
            }
            object2 = object;
        }
        GradientUniforms gradientUniforms = (GradientUniforms)object2;
        int n = Float.compare(this.halfTexelSizeX, gradientUniforms.halfTexelSizeX);
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.halfTexelSizeY, gradientUniforms.halfTexelSizeY);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.color1, gradientUniforms.color1) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.color2, gradientUniforms.color2) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.color3, gradientUniforms.color3) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.color4, gradientUniforms.color4) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.lineWidth, gradientUniforms.lineWidth);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.time, gradientUniforms.time);
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
                        GradientUniforms.a = ZkmKeys.a(-3199097495515647968L, -6012671410044015894L, MethodHandles.lookup().lookupClass()).a(24272069776065L);
                        GradientUniforms.d = new HashMap<K, V>(13);
                        var11 = GradientUniforms.a ^ 128876738321384L;
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
                        var20_3 = new String[17];
                        var18_4 = 0;
                        var17_5 = "VT\u0084-\u00bdA\u00b1\u00fc\u008eqW\u0012\u0082_\u00ed\u00f8 T\u0010\u00ed\u00e9\u008e\u00ca\u00b1x>\u00f7ob\u00b9%\u00bf\u00c2+M\u00bc\u008cy\u00d1\u00ce\u0014\u0085\u00e7\u00b5\u00f5M\u0010\u0018\u00e1\u0018<\u00c7\u0006\u00eaq(\u00d4R\u00cf\u00bf\u00dfh*\u00ce\\\u00e0\u00c9\u0007\"\u00df\u00cfj\u00f2(\u0010\u00c0u\u0090\u0012GL\u00d1\u00b0}\u00b3>\u00d2\u00b3\u00fa\u0000\u0097\u0018t\u00c3S\u00d3\u0089\u00e2j\u0082\u0086\u001eF&\u00b9G#\u0080\u00cer\u0090\u00d4L\u0012\u0014\u0018\u0010\u0007W\u00cc\u0083\u00ce\u00f3\u00b6\bY\u008e[\u00a9<D\u00d7\u008f\u0010\u001e\u00c310\u00a03\u00f4\u00e9\u00c6T<\u00ec\u00bb\u00afq\u00d0\u0010\u0085\u00fbO\u00bc=\u009b>(\u0016\u00d3Bm\u00cbk@.(6\u00bb\u0007\u00b94b\u00c8\u00e3\u00af\u00c4\u00ab\u00e9\u00f4\u00eeN\u001e\u00f4&\u0088\u009d\u0015\u00a1\u00c5\u00df;\u0090h\u00f2`\u00b7\u0092\u00c3\u00d0\u0097\u001d\u009f\u00c0I\u0018\u00c9\u00101\u0004\f\u00db]\u00bc.\u001d\u00b8\"-\u00d9~(\u00c4\u008b\u0010\u00f4\u00bb\u00c1\u00ff\u00d8N{[H\u0086\u00dd\u00ec\u00d0\u001dE+\u0010I\u0099\u00aa\u00b1\u0092\u00e3\u009bM\u00fe\u0007\u008dn\u00c0\u00a1,7 ;\u00fd2\u00d4\u00bbd\u009a\u0082d\u000e\u00d6\u0084\u00f4\u00f72\u00a2\u00d3\u00e1\u009e\u00ce\u00a1\u00c0\u0086ke.\u0014\u0094\u0019\u00e9\u0016&\u0010/\u0088%\u0001\u0082\u00975\u0018_[\u0001\u00ac\u0002\u00a5\u00f6M\u0010\u00fc\u00bbu<$\u00f6\u001c\u00f4\b\u00e9j\u00d4\u00a9\u0014\u0095\u00a3";
                        var19_6 = 326;
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
                            var20_3[var18_4++] = GradientUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0085t\u00ef\u00b6\u0002\u008b\u00f8c(\u00c6B\u00bf.\u00d56\u009b\r\u0011\u00a7O\u00e4\u00fc\u00ea\u009a \u00d8K\u00b5\u00d9\u0098\u00bc\u00b4\bE\u00fd\u0083\u00b3\u00e8\u0086m\u00f8\u001d\u00b1\u0003(n\r\u0014\u0018\u00c1\f\u00bda\u00b6E\u001e\u0011\u008f\u001c\u00f1M\u00c8\u00af\u00a2y\u00c8Z\u00d4\u0080!l\u00f0n";
                            var19_6 = 73;
                            var16_7 = 48;
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
                            var20_3[var18_4++] = GradientUniforms.zkmDecodeUtf8(var21_9).intern();
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
                GradientUniforms.b = var20_3;
                GradientUniforms.c = new String[17];
                GradientUniforms.i = new HashMap<K, V>(13);
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
                var4_14 = "S\u008b\u00c9\u001d\"\u00cbez\u00dcL\u00f92\u00806\u00b0\u007f\u00c7\b\u0097M\u00c5\u008b\u00ea7\u00d9kO\u00fcy3\u00bd1\u00a5\u00fe2\u00e7\u000br\u00b9\u008f";
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
                    var4_14 = "E\u0098J\u00b1\u0088P\rZz\u0098|Im\u00e3b\u00b1";
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
        GradientUniforms.f = var6_12;
        GradientUniforms.h = new Integer[7];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2002;
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
                throw new RuntimeException("su/catlean/lc", exception);
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
            GradientUniforms.c[n2] = GradientUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GradientUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/lc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x999;
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
                throw new RuntimeException("su/catlean/lc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            GradientUniforms.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = GradientUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/lc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GradientUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(GradientUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

