/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
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
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class GlowUniforms
implements DynamicUniformStorage.Uploadable {
    @NotNull
    private final Vec2f texelSize;
    @NotNull
    private final Vec2f direction;
    private final float radius;
    private final float glowAlpha;
    @NotNull
    private final Matrix4f matrix;
    @NotNull
    private final Color color;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;

    public GlowUniforms(@NotNull Vec2f vec2f, @NotNull Vec2f vec2f2, float f, float f2, @NotNull Matrix4f matrix4f, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(vec2f, "texelSize");
        Intrinsics.checkNotNullParameter(vec2f2, "direction");
        Intrinsics.checkNotNullParameter(matrix4f, "matrix");
        Intrinsics.checkNotNullParameter(color, "color");
        this.texelSize = vec2f;
        this.direction = vec2f2;
        this.radius = f;
        this.glowAlpha = f2;
        this.matrix = matrix4f;
        this.color = color;
    }

    @NotNull
    public final Vec2f getTexelSize() {
        return this.texelSize;
    }

    @NotNull
    public final Vec2f getDirection() {
        return this.direction;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getGlowAlpha() {
        return this.glowAlpha;
    }

    @NotNull
    public final Matrix4f getMatrix() {
        return this.matrix;
    }

    @NotNull
    public final Color getColor() {
        return this.color;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putVec2(this.texelSize.x, this.texelSize.y).putVec2(this.direction.x, this.direction.y).putFloat(this.radius).putFloat(this.glowAlpha).putMat4f((Matrix4fc)this.matrix).putVec4(RenderUtil.INSTANCE.getRed(this.color), RenderUtil.INSTANCE.getGreen(this.color), RenderUtil.INSTANCE.getBlue(this.color), RenderUtil.INSTANCE.getAlpha(this.color));
    }

    @NotNull
    public final Vec2f getTexelSize2() {
        return this.texelSize;
    }

    @NotNull
    public final Vec2f getDirection2() {
        return this.direction;
    }

    public final float getRadius2() {
        return this.radius;
    }

    public final float getGlowAlpha2() {
        return this.glowAlpha;
    }

    @NotNull
    public final Matrix4f getMatrix2() {
        return this.matrix;
    }

    @NotNull
    public final Color getColor2() {
        return this.color;
    }

    @NotNull
    public final GlowUniforms copy(@NotNull Vec2f vec2f, @NotNull Vec2f vec2f2, float f, float f2, @NotNull Matrix4f matrix4f, @NotNull Color color) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(vec2f, (String)((Object)GlowUniforms.zkmBootstrap("n", (int)16926, (long)(0x4B558B51827E215DL ^ l))));
        Intrinsics.checkNotNullParameter(vec2f2, (String)((Object)GlowUniforms.zkmBootstrap("n", (int)28495, (long)(0x4E6A029C91C60C0AL ^ l))));
        Intrinsics.checkNotNullParameter(matrix4f, (String)((Object)GlowUniforms.zkmBootstrap("n", (int)26861, (long)(0x32AEF3A63FE58BAFL ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)GlowUniforms.zkmBootstrap("n", (int)27703, (long)(0x2CE2E6A22EAB8F71L ^ l))));
        return new GlowUniforms(vec2f, vec2f2, f, f2, matrix4f, color);
    }

    public static GlowUniforms copy$default(GlowUniforms glowUniforms, Vec2f vec2f, Vec2f vec2f2, float f, float f2, Matrix4f matrix4f, Color color, int n, Object object, char c) {
        long l = 0L;
        long l2 = (l << 16 | (long)c << 48 >>> 48) ^ b;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2078918180545753320L, (long)l2);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                vec2f = glowUniforms.texelSize;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (c >= '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    vec2f2 = glowUniforms.direction;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (c > '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f = glowUniforms.radius;
                }
                n2 = n & GlowUniforms.zkmBootstrap2("o", (int)19113, (long)(0x44B65DBF8DEBA371L ^ l2));
            }
            callSite2 = callSite;
        }
        if (c >= '\u0000') {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = glowUniforms.glowAlpha;
                }
                n2 = n & GlowUniforms.zkmBootstrap2("o", (int)23995, (long)(0x7084710E7A4FB460L ^ l2));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                matrix4f = glowUniforms.matrix;
            }
            n2 = n & GlowUniforms.zkmBootstrap2("o", (int)25784, (long)(0x719C18196A820D61L ^ l2));
        }
        if (n2 != 0) {
            color = glowUniforms.color;
        }
        return glowUniforms.copy(vec2f, vec2f2, f, f2, matrix4f, color);
    }

    @NotNull
    public String toString() {
        Color color = this.color;
        Matrix4f matrix4f = this.matrix;
        float f = this.glowAlpha;
        float f2 = this.radius;
        Vec2f vec2f = this.direction;
        Vec2f vec2f2 = this.texelSize;
        return "UniformData(texelSize=" + vec2f2 + ", direction=" + vec2f + ", radius=" + f2 + ", glowAlpha=" + f + ", matrix=" + matrix4f + ", color=" + color + ")";
    }

    public int hashCode() {
        int n = this.texelSize.hashCode();
        n = n * 31 + this.direction.hashCode();
        n = n * 31 + Float.hashCode(this.radius);
        n = n * 31 + Float.hashCode(this.glowAlpha);
        n = n * 31 + this.matrix.hashCode();
        n = n * 31 + this.color.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        long l = b ^ 0x5C9556A84FF8L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)584909999535547424L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof GlowUniforms)) {
                return false;
            }
            object2 = object;
        }
        GlowUniforms glowUniforms = (GlowUniforms)object2;
        int n = Intrinsics.areEqual(this.texelSize, glowUniforms.texelSize);
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.direction, glowUniforms.direction);
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.radius, glowUniforms.radius);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.glowAlpha, glowUniforms.glowAlpha);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.matrix, glowUniforms.matrix) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.color, glowUniforms.color) ? 1 : 0;
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
                        GlowUniforms.b = ZkmKeys.a(-3437108233957883407L, -4942130755215261277L, MethodHandles.lookup().lookupClass()).a(97121812749859L);
                        GlowUniforms.e = new HashMap<K, V>(13);
                        var11 = GlowUniforms.b ^ 47323855634897L;
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
                        var20_3 = new String[15];
                        var18_4 = 0;
                        var17_5 = "h\u00f0\u008a\u0086et-\u00d1\u00a5\u00ec\u0098\u0094\u00c79h\u0083\u00ae\u00eea\u00c9\u00b44\u0000^wm\u00fa\u00bdcf\u00bfb\u0018)\u0001\u0098\u00dd\r7\u00aa\u0087\u001c\u00e1[=\u00b9\u00cf\u00ad\u00f6\u009cd\u00ae\u00d0\u00a7xY\u00e0\u0010\u000f1\u00cd\u0089\u00e0\u00f5\u00b0\u00deH0\u00f9\u00e3\u00faM\u007fw\u0018\"A`\u007f\u00f1g\f)@Mm\u008b\u00d2Q\u0083\u0017/~U\u0010S\u007f\u00b3\u001f\u0018V\u00ea\u0088\u00fd\u00d2\u00ba\u00b7\u00c5\n\u00e7[\u008bU\u00aar\u0090u\u00aa\u00c5\u00f2\u0095\u0096!\u0003\u0018\u0011\u00b4\u0014\u00fa|\u0091\u00d6\u00d0\u00f7\u00ef24\f\u0094&\u0084u\u00ddPfi\u00f4\u00ae\u00e0\u0010\u0001hQ\u00b1o\u00f8\u008e\u0088N\u00cc\u00d9\u00f3\u0012(\u009f\u0081 \u00ec\u00a3}M\u009e'\u00ff\\T\u00f1\u00d4\u0093\u00fb\"\u00da\u00e4%\u00eb\u0011l\u00a4\r\u00bfo\rW\u00f8\u00e5T\u0095\u0010\u000b w\u000b\u00a1b\u009a\u00f9\u00ef/y\u000fF=\u00e6<E^\u009f\u00benb\u00d6\u00b4\u00be\u00f6\u00f1\u00a3\u00c9]~ku\u0012\u0010\u0094\u0018{\u00b7\u00dcp\u00d8\u0098\u001f\u0094B\u0095\u00d5\u00d3\u001dF \u0087\u0018I\u0011\u001aCD\u00b8f^\u00ab\"\u00ea \u00cc\u00a4\u00c8O\u00d6#\u00f3Mf\u00e0\u0084\u0083m\u00d0\u009a\u00d8\u009e\u0016\u0018F?\u00d6\u00b9\u0000m\u0015Wpc\u00a3\b}&\u0016\u00d3\u00ea\u00f0W,fB\u007f\u00eb\u0010\u00df\u00c4\u00b8\u00d97w\u008e\u0097\u0018\r\u00d4\fH\u00d3\u001c\\";
                        var19_6 = 324;
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
                            var20_3[var18_4++] = GlowUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0084\u00d3\u00d3\u001e\u0016I\u0002\u00da\u0087\u0005?g.\u00a4\u00cf\u009f\u00cb\u00e2e\u0000\u00a7\"\u00c3RU}`\u0004x\u00cf\u00a0\u00b2,\u00fe/\u00ba\u0081\u0081~W\u0010\u00e2Ii\u00e4\u0094\u00dd\u00dbO\f\u00a2\u001f$0\u00c1\u00f0\u008f";
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
                            var20_3[var18_4++] = GlowUniforms.zkmDecodeUtf8(var21_9).intern();
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
                GlowUniforms.c = var20_3;
                GlowUniforms.d = new String[15];
                GlowUniforms.i = new HashMap<K, V>(13);
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
                var4_14 = "<\u000fF\u0087\u0013$\u001e\u00fdk\u0013#\u00a0\u0002\u00edp\u0092e\u0088=\u0093\u0010\u00a0\u00dd\u00da";
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
                    var4_14 = "\u00a7Y%C\u00d1dp\u00874\u0092\u008d\u0086\u00dd\u00fe\u0013\u00ae";
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
        GlowUniforms.f = var6_12;
        GlowUniforms.g = new Integer[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x321F;
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
                throw new RuntimeException("su/catlean/zj", exception);
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
            GlowUniforms.d[n2] = GlowUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GlowUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xBCC;
        if (g[n2] == null) {
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
                throw new RuntimeException("su/catlean/zj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            GlowUniforms.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = GlowUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/zj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GlowUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(GlowUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

