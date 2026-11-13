/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 */
package su.catlean.render.draw;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import su.catlean.render.RenderTarget;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ImageCommand {
    @NotNull
    private final RenderTarget image;
    @NotNull
    private final Matrix3x2f m;
    private final float x;
    private final float y;
    private final float w;
    private final float h;
    private final float z;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h_obf;

    public ImageCommand(@NotNull RenderTarget renderTarget, @NotNull Matrix3x2f matrix3x2f, float f, float f2, float f3, float f4, float f5) {
        Intrinsics.checkNotNullParameter(renderTarget, "image");
        Intrinsics.checkNotNullParameter(matrix3x2f, "m");
        this.image = renderTarget;
        this.m = matrix3x2f;
        this.x = f;
        this.y = f2;
        this.w = f3;
        this.h = f4;
        this.z = f5;
    }

    @NotNull
    public final RenderTarget getImage() {
        return this.image;
    }

    @NotNull
    public final Matrix3x2f getM() {
        return this.m;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getW() {
        return this.w;
    }

    public final float getH() {
        return this.h;
    }

    public final float m() {
        return this.z;
    }

    @NotNull
    public final RenderTarget getImage2() {
        return this.image;
    }

    @NotNull
    public final Matrix3x2f getM2() {
        return this.m;
    }

    public final float getX2() {
        return this.x;
    }

    public final float getY2() {
        return this.y;
    }

    public final float getW2() {
        return this.w;
    }

    public final float getH2() {
        return this.h;
    }

    public final float H() {
        return this.z;
    }

    @NotNull
    public final ImageCommand copy(@NotNull RenderTarget renderTarget, @NotNull Matrix3x2f matrix3x2f, float f, float f2, float f3, float f4, float f5) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(renderTarget, (String)((Object)ImageCommand.zkmBootstrap("b", (int)29109, (long)(0x417B4414E3197CD0L ^ l))));
        Intrinsics.checkNotNullParameter(matrix3x2f, "m");
        return new ImageCommand(renderTarget, matrix3x2f, f, f2, f3, f4, f5);
    }

    public static ImageCommand copy$default(ImageCommand imageCommand, long l, RenderTarget renderTarget, Matrix3x2f matrix3x2f, float f, float f2, float f3, float f4, float f5, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2606420686872562961L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                renderTarget = imageCommand.image;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    matrix3x2f = imageCommand.m;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    f = imageCommand.x;
                }
                n2 = n & ImageCommand.zkmBootstrap2("s", (int)1277, (long)(0x2D7AB88FEFE7E158L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    f2 = imageCommand.y;
                }
                n2 = n & ImageCommand.zkmBootstrap2("s", (int)2027, (long)(0x27772B1FB53E24AL ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    f3 = imageCommand.w;
                }
                n2 = n & ImageCommand.zkmBootstrap2("s", (int)17002, (long)(0x68CAB276BB16A7CEL ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) {
            if (n2 != 0) {
                f4 = imageCommand.h;
            }
            n2 = n & ImageCommand.zkmBootstrap2("s", (int)22065, (long)(0x2513E5BCFE423397L ^ l));
        }
        if (n2 != 0) {
            f5 = imageCommand.z;
        }
        return imageCommand.copy(renderTarget, matrix3x2f, f, f2, f3, f4, f5);
    }

    @NotNull
    public String toString() {
        float f = this.z;
        float f2 = this.h;
        float f3 = this.w;
        float f4 = this.y;
        float f5 = this.x;
        Matrix3x2f matrix3x2f = this.m;
        RenderTarget renderTarget = this.image;
        return "IconRenderState(image=" + renderTarget + ", m=" + matrix3x2f + ", x=" + f5 + ", y=" + f4 + ", w=" + f3 + ", h=" + f2 + ", a=" + f + ")";
    }

    public int hashCode() {
        int n = this.image.hashCode();
        n = n * 31 + this.m.hashCode();
        n = n * 31 + Float.hashCode(this.x);
        n = n * 31 + Float.hashCode(this.y);
        n = n * 31 + Float.hashCode(this.w);
        n = n * 31 + Float.hashCode(this.h);
        n = n * 31 + Float.hashCode(this.z);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ImageCommand)) {
            return false;
        }
        ImageCommand imageCommand = (ImageCommand)object;
        if (!Intrinsics.areEqual(this.image, imageCommand.image)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.m, imageCommand.m)) {
            return false;
        }
        if (Float.compare(this.x, imageCommand.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, imageCommand.y) != 0) {
            return false;
        }
        if (Float.compare(this.w, imageCommand.w) != 0) {
            return false;
        }
        if (Float.compare(this.h, imageCommand.h) != 0) {
            return false;
        }
        return Float.compare(this.z, imageCommand.z) == 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ImageCommand.a = ZkmKeys.a(6203625375047892917L, -197309145450888293L, MethodHandles.lookup().lookupClass()).a(67242680917235L);
                        ImageCommand.e = new HashMap<K, V>(13);
                        var11 = ImageCommand.a ^ 133373332127278L;
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
                        var20_3 = new String[9];
                        var18_4 = 0;
                        var17_5 = "\u00f0\u00e3\u0005K\u00a4\u00fa.r\u00a1\u008b\u00b1\u0011!6\u00d9|\u0010U\u00e6\u00a2\u00ffQ\u00ba\u0094h,\u00bfa\u00cf\u009f\u0094\u0088\u00e7\u0010\u00b7L4&\u0007MK\u00cd\u0012\u008e\u0015\u00e7..Gp\u0010\u0002\u00c5^\u0095)0f\u00e5\u00c8\u001b\u0090.\u00e1\u001d*w(;\u00b4\u00c52K\u00a0\u0086\u0093\u00b9\u00c6`\u0017\u0097\u0084g\u00ebT\u0096\u00b2\u00ccB\u00f3\u00ee\u0006P\u009a\u0081\u008c:\u00e9\fCl\u00fe]\u0088\u00a7\u00cdCH\u0010t*5\u0087\u00dePU\u009b\u00a3\u00f7wO\u00964E\u0089\u0010\u00c3o\u0098\u00eb\u00be\u00d8|ms\u00d4\u00c4\u00b8\u008f\u00b51\u0088";
                        var19_6 = 142;
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
                            var20_3[var18_4++] = ImageCommand.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00bfg\u00a8\u0082a\u00a3rB\u0090\u00de\u00e7\u00a2\u00bb\u00c7\u00f5\u00db\u0010;F_\u00caYE\u00c4\u0099\u0096,\u00c0\u00e7\u0010\u00d2\u00e8\u0017";
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
                            var20_3[var18_4++] = ImageCommand.zkmDecodeUtf8(var21_9).intern();
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
                ImageCommand.b = var20_3;
                ImageCommand.d = new String[9];
                ImageCommand.h_obf = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u00d7P\u00a3\u0086\u00d8\u00bd\u000e\u00c0P\u00a1\u0002H\u00d9N\u009d\u00af\u008b\u001fB\u00fed\u001aGE%\u0095}\u00e4\u0010\u0003\u00c5\u00ac";
                var5_15 = 32;
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
                    var4_14 = "\u0088}\u00bd\u00e0%\u00a5\u00abl\u00eb\u00b1\u00ea\u00b2\u001a\u0099\u009f\u00c6";
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
        ImageCommand.f = var6_12;
        ImageCommand.g = new Integer[6];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3402;
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
                throw new RuntimeException("su/catlean/ff", exception);
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
            ImageCommand.d[n2] = ImageCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ImageCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ff" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3FAB;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h_obf.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h_obf.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ff", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ImageCommand.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ImageCommand.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ff" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ImageCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ImageCommand.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

