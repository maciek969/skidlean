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
import su.catlean.render.Render2D;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ShapeCommand {
    @NotNull
    private final Matrix3x2f m;
    private final float x;
    private final float y;
    private final float w;
    private final float h;
    private final float F;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] g;
    private static final Integer[] h_obf;
    private static final Map i;

    public ShapeCommand(@NotNull Matrix3x2f matrix3x2f, float f, float f2, float f3, float f4, float f5) {
        Intrinsics.checkNotNullParameter(matrix3x2f, "m");
        this.m = matrix3x2f;
        this.x = f;
        this.y = f2;
        this.w = f3;
        this.h = f4;
        this.F = f5;
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

    public final float F() {
        return this.F;
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
        return this.F;
    }

    @NotNull
    public final ShapeCommand copy(@NotNull Matrix3x2f matrix3x2f, float f, float f2, float f3, float f4, float f5) {
        Intrinsics.checkNotNullParameter(matrix3x2f, "m");
        return new ShapeCommand(matrix3x2f, f, f2, f3, f4, f5);
    }

    public static /* synthetic */ ShapeCommand copy$default(ShapeCommand shapeCommand, Matrix3x2f matrix3x2f, float f, float f2, float f3, long l, float f4, float f5, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6682397351624293569L, (long)l);
        int n2 = n & 1;
        if (callSite != false) {
            if (n2 != 0) {
                matrix3x2f = shapeCommand.m;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 != false) {
                if (n2 != 0) {
                    f = shapeCommand.x;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != false) {
                if (n2 != 0) {
                    f2 = shapeCommand.y;
                }
                n2 = n & ShapeCommand.zkmBootstrap2("l", (int)13945, (long)(0x74B73C807ACC44D1L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != false) {
                if (n2 != 0) {
                    f3 = shapeCommand.w;
                }
                n2 = n & ShapeCommand.zkmBootstrap2("l", (int)858, (long)(0x5F0A387C38CE71F5L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != false) {
            if (n2 != 0) {
                f4 = shapeCommand.h;
            }
            n2 = n & ShapeCommand.zkmBootstrap2("l", (int)9162, (long)(0x21308BFBDFC8D167L ^ l));
        }
        if (n2 != 0) {
            f5 = shapeCommand.F;
        }
        ShapeCommand shapeCommand2 = shapeCommand.copy(matrix3x2f, f, f2, f3, f4, f5);
        if (ZkmReferences.zkmBootstrap("\u00c5", (long)6700941781478883951L, (long)l) != null) {
            ZkmReferences.zkmBootstrap("\u00c5", (callSite == false ? 1 : 0) != 0, (long)6679501902177088095L, (long)l);
        }
        return shapeCommand2;
    }

    @NotNull
    public String toString() {
        float f = this.F;
        float f2 = this.h;
        float f3 = this.w;
        float f4 = this.y;
        float f5 = this.x;
        Matrix3x2f matrix3x2f = this.m;
        return "PlateRenderState(m=" + matrix3x2f + ", x=" + f5 + ", y=" + f4 + ", w=" + f3 + ", h=" + f2 + ", a=" + f + ")";
    }

    public int hashCode() {
        int n = this.m.hashCode();
        n = n * 31 + Float.hashCode(this.x);
        n = n * 31 + Float.hashCode(this.y);
        n = n * 31 + Float.hashCode(this.w);
        n = n * 31 + Float.hashCode(this.h);
        n = n * 31 + Float.hashCode(this.F);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = Render2D.U();
        Object object2 = this;
        if (!bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (!bl) {
            if (!(object2 instanceof ShapeCommand)) {
                return false;
            }
            object2 = object;
        }
        ShapeCommand shapeCommand = (ShapeCommand)object2;
        int n = Intrinsics.areEqual(this.m, shapeCommand.m);
        if (!bl) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.x, shapeCommand.x);
        }
        if (!bl) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.y, shapeCommand.y);
        }
        if (!bl) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.w, shapeCommand.w);
        }
        if (!bl) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.h, shapeCommand.h);
        }
        if (!bl) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.F, shapeCommand.F);
        }
        if (!bl) {
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
                        ShapeCommand.a = ZkmKeys.a(-1587782638527424369L, -3883593976393980563L, MethodHandles.lookup().lookupClass()).a(33587367007275L);
                        ShapeCommand.e = new HashMap<K, V>(13);
                        var11 = ShapeCommand.a ^ 110120092036730L;
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
                        var20_3 = new String[6];
                        var18_4 = 0;
                        var17_5 = "\u008a\b\u0098\u0083\u00a9\u00ba\u0003\u00fa\u00fa\u00d4,\u00e7B\u00c3v)\u0010\u00f4\u00f8{\u00c6\u0018\u00e7\u000e\"|!\u0016\u0001F\u00da\u00ecY\u0010\u00cd\u009b_\u00ca\u00ce?\u00e2\u00a1&\u00e5zL-\u00a4{\u009d\u0010\n\u00baH\u00b8BO\u00e1\u0096\u00a4\u00f55\u00f42w6\u00dd";
                        var19_6 = 67;
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
                            var20_3[var18_4++] = ShapeCommand.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00c3H\u00c7\u00a8\u001c\u00e9\u00da\u0013\u009dSMa\u00d3f\u0005L\u00f9\u00a6\u0091\u00d9\u001eU\u008d\u00cf\u00c2~`\u00d8\u00d2\u00d3v\u00c2'\u00e4\u009e\u00f0\u00b1\u0010\u00c2\u00cb\u0010\u0096dJX\u0019g\u00a2/\u00fbg\u00c9\u00bd\u00e6\u00f7|\u0019";
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
                            var20_3[var18_4++] = ShapeCommand.zkmDecodeUtf8(var21_9).intern();
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
                ShapeCommand.c = var20_3;
                ShapeCommand.d = new String[6];
                ShapeCommand.i = new HashMap<K, V>(13);
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
                var4_14 = "\u0004)@ZYA*O\u00d3\u00b9\u0002\u00d7\u0001\u0089'\u00fe\u009d\u00fa\u0016#\u0007\u00c4x\u008f";
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
                    var4_14 = "\u008d\u00ab\u00be\u00c1u\u00f7\u00e6_\u009a\u00ed\u001b;\u00f6(\u0080\u00b3";
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
        ShapeCommand.g = var6_12;
        ShapeCommand.h_obf = new Integer[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4FB9;
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
                throw new RuntimeException("su/catlean/ah", exception);
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
            ShapeCommand.d[n2] = ShapeCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ShapeCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ah" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2F03;
        if (h_obf[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/ah", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ShapeCommand.h_obf[n2] = n3;
        }
        return h_obf[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ShapeCommand.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ah" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ShapeCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ShapeCommand.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

