/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ModeOption {
    private final float x;
    private final float y;
    private final float w;
    @NotNull
    private final Enum value;
    @NotNull
    private final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public ModeOption(float f, float f2, float f3, @NotNull Enum enum_, @NotNull Animation animation) {
        Intrinsics.checkNotNullParameter(enum_, "value");
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.x = f;
        this.y = f2;
        this.w = f3;
        this.value = enum_;
        this.animation = animation;
    }

    public /* synthetic */ ModeOption(float f, float f2, float f3, Enum enum_, Animation animation, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_EXPO, h);
        this(f, f2, f3, enum_, animation);
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

    @NotNull
    public final Enum getValue() {
        return this.value;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
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

    @NotNull
    public final Enum getValue2() {
        return this.value;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    @NotNull
    public final ModeOption copy(float f, float f2, float f3, @NotNull Enum enum_, @NotNull Animation animation) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(enum_, (String)((Object)ModeOption.zkmBootstrap("a", (int)5994, (long)(0x1F87BA6E6C9853D9L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)ModeOption.zkmBootstrap("a", (int)25944, (long)(0x52D012E717EF21EAL ^ l))));
        return new ModeOption(f, f2, f3, enum_, animation);
    }

    public static /* synthetic */ ModeOption copy$default(ModeOption modeOption, float f, float f2, float f3, Enum enum_, Animation animation, int n, Object object, long l) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-1438599769114072172L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                f = modeOption.x;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    f2 = modeOption.y;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    f3 = modeOption.w;
                }
                n2 = n & ModeOption.zkmBootstrap2("s", (int)3030, (long)(0x78D38A3E521FD504L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) {
            if (n2 != 0) {
                enum_ = modeOption.value;
            }
            n2 = n & ModeOption.zkmBootstrap2("s", (int)13581, (long)(0x7E13A9158A27EBD9L ^ l));
        }
        if (n2 != 0) {
            animation = modeOption.animation;
        }
        return modeOption.copy(f, f2, f3, enum_, animation);
    }

    @NotNull
    public String toString() {
        Animation animation = this.animation;
        Enum enum_ = this.value;
        float f = this.w;
        float f2 = this.y;
        float f3 = this.x;
        return "Selector(x=" + f3 + ", y=" + f2 + ", w=" + f + ", value=" + enum_ + ", animation=" + animation + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.x);
        n = n * 31 + Float.hashCode(this.y);
        n = n * 31 + Float.hashCode(this.w);
        n = n * 31 + this.value.hashCode();
        n = n * 31 + this.animation.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ModeOption)) {
            return false;
        }
        ModeOption modeOption = (ModeOption)object;
        if (Float.compare(this.x, modeOption.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, modeOption.y) != 0) {
            return false;
        }
        if (Float.compare(this.w, modeOption.w) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual(this.value, modeOption.value)) {
            return false;
        }
        return Intrinsics.areEqual(this.animation, modeOption.animation);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            ModeOption.a = ZkmKeys.a(7097905613796251025L, 2601798964985699917L, MethodHandles.lookup().lookupClass()).a(247327208534087L);
                            ModeOption.d = new HashMap<K, V>(13);
                            var16 = ModeOption.a ^ 46544965120404L;
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var16 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var16 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[9];
                            var23_4 = 0;
                            var22_5 = "^\u0087\u00b7\u00f8\u00b1\u00b3!\u00cc\u0004\u00b9\u00c6\u00d4\u00c2\u0014\u0095\u001f\u0018\u00b0j\u00f9-\u00aeQ\u0097\u00029D`\u0086\u00e5\u00fa\u00d2\u00fe\u009a\u00d66\u00ecO\u0084'k \u00f5\u0091\\\u00d2\u009fi\u00f2*\u00da;%\u00cc\u00d5\u00ffX\u0099n\u00ba\u00dcR3\\$\u00c9|\u00e5\u00a3\u00bf\u00feuO\u00d3\u0010a\u0006q\u00f3\u00a4\u0095\u00b2\u00f2\u00c8\u00f8\u001d\u00d8\u000e\u000ejV\u0010\u0080\u00eaV\u00e6\u008b\u008f..\u00ac\u00a0]\u00b3#\u00a9\u00f3K \u0004\u00c0\u00a5i\u00ee^|\u00e3\u0092}\u00b9\u0007D\u000e\u0090\u00ef6\u00f3\u00e4\u0006\u0090e\u00d5\u00da\f\u00f4\u009cE\u001e\u00b1\u00e1\u00a1 Y\u00b3\u008f\u00b4!'\u00a6\u00e4@\u00ac[u\u00b8 \u00db\u00fb\u00ad\u00ee\u00ef\u00a0O\u0007_\u00f7),\u00cf\u00b7(\u0094\u00f0\u00ce";
                            var24_6 = 174;
                            var21_7 = 16;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = ModeOption.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "g\u00d3kuJ\u00bf\u00a4r\u00b3\u00e3\u00a5\u00982\u00ba\n\u00c0R\u00fb\u00e4\u0089\u00db\u00db;\u00afZ\u00e9\u00ba\u00b8\u00f0\u0082\u00f1\u00e9 c\u008dn\u00f3\u0082hc/wA\u00ecg\u009ck,\u00f5\tz\u00ac\u00eb!%C\u0090\u00bd\u00878\u00000\u001c[\u0098";
                                var24_6 = 65;
                                var21_7 = 32;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = ModeOption.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    ModeOption.b = var25_3;
                    ModeOption.c = new String[9];
                    ModeOption.g = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var16 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var16 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[5];
                    var8_13 = 0;
                    var9_14 = "\u00ee\u00a00\u0083\u0007\u00bc\u0012\u0015\u00f9\u00d2q\u0092e\u0012\u00f0\u0091\u00bd\u00a4\u00b3\u00c7\u0013\u0019\u0082\u0003";
                    var10_15 = 24;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\n\u00aa\u0090\u0004\u00c8\u00f1@\u0083\u0011!\u00f2\u0082gh\u00b6\u00e6";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
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
            ModeOption.e = var11_12;
            ModeOption.f = new Integer[5];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var16 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var16 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-111, 105, -69, -111, -1, 22, 124, 104});
        ** while (true)
        ModeOption.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7BEF;
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
                throw new RuntimeException("su/catlean/yh", exception);
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
            ModeOption.c[n2] = ModeOption.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ModeOption.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/yh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x33C7;
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
                throw new RuntimeException("su/catlean/yh", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ModeOption.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ModeOption.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/yh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ModeOption.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ModeOption.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

