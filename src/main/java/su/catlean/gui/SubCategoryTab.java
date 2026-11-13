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
import su.catlean.gui.SettingElement;
import su.catlean.module.Category;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SubCategoryTab {
    private final float x;
    private final float w;
    @NotNull
    private final Category subCategory;
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

    public SubCategoryTab(float f, float f2, @NotNull Category category, @NotNull Animation animation) {
        Intrinsics.checkNotNullParameter(category, "subCategory");
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.x = f;
        this.w = f2;
        this.subCategory = category;
        this.animation = animation;
    }

    public /* synthetic */ SubCategoryTab(float f, float f2, Category category, Animation animation, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_EXPO, h);
        this(f, f2, category, animation);
    }

    public final float getX() {
        return this.x;
    }

    public final float getW() {
        return this.w;
    }

    @NotNull
    public final Category getSubCategory() {
        return this.subCategory;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    public final float getX2() {
        return this.x;
    }

    public final float getW2() {
        return this.w;
    }

    @NotNull
    public final Category getSubCategory2() {
        return this.subCategory;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    @NotNull
    public final SubCategoryTab copy(float f, float f2, @NotNull Category category, @NotNull Animation animation) {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(category, (String)((Object)SubCategoryTab.zkmBootstrap("w", (int)12389, (long)(0x77B58E8BF0DE19FDL ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)SubCategoryTab.zkmBootstrap("w", (int)31982, (long)(0x43FFB4A4BDD3D572L ^ l))));
        return new SubCategoryTab(f, f2, category, animation);
    }

    public static /* synthetic */ SubCategoryTab copy$default(SubCategoryTab subCategoryTab, long l, float f, float f2, Category category, Animation animation, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-6059128375620411777L, (long)l);
        int n2 = n & 1;
        if (callSite != false) {
            if (n2 != 0) {
                f = subCategoryTab.x;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 != false) {
                if (n2 != 0) {
                    f2 = subCategoryTab.w;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (callSite2 != false) {
            if (n2 != 0) {
                category = subCategoryTab.subCategory;
            }
            n2 = n & SubCategoryTab.zkmBootstrap2("d", (int)4665, (long)(0x27B7E968F3762946L ^ l));
        }
        if (n2 != 0) {
            animation = subCategoryTab.animation;
        }
        return subCategoryTab.copy(f, f2, category, animation);
    }

    @NotNull
    public String toString() {
        Animation animation = this.animation;
        Category category = this.subCategory;
        float f = this.w;
        float f2 = this.x;
        return "Selector(x=" + f2 + ", w=" + f + ", subCategory=" + category + ", animation=" + animation + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.x);
        n = n * 31 + Float.hashCode(this.w);
        n = n * 31 + this.subCategory.hashCode();
        n = n * 31 + this.animation.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = SettingElement.e();
        Object object2 = this;
        if (!bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (!bl) {
            if (!(object2 instanceof SubCategoryTab)) {
                return false;
            }
            object2 = object;
        }
        SubCategoryTab subCategoryTab = (SubCategoryTab)object2;
        boolean bl2 = Float.compare(this.x, subCategoryTab.x);
        if (!bl) {
            if (bl2) {
                return false;
            }
            bl2 = Float.compare(this.w, subCategoryTab.w);
        }
        if (!bl) {
            if (bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.subCategory, subCategoryTab.subCategory);
        }
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.animation, subCategoryTab.animation);
        }
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
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
                            SubCategoryTab.a = ZkmKeys.a(4139074659615699165L, 3953372415777465330L, MethodHandles.lookup().lookupClass()).a(72237254554582L);
                            SubCategoryTab.d = new HashMap<K, V>(13);
                            var16 = SubCategoryTab.a ^ 14750061926327L;
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
                            var25_3 = new String[8];
                            var23_4 = 0;
                            var22_5 = "\u00cb\u00f6##\u00a8X\u0082\u00fa\u00be\u009b\u001f3\u0080\u00f8\u00d2\u0082 X]xM\u00bf$\u00d7pJ}\u00f6n@@\u00c1+\u00e7\u00fb\u00b3\u008b1\u0084\u0087\u00c2y\u00d99$<\u001a;\u0084\u0018\u009c5z\u001au\u0001\u00968%\u000f\u00e2g\u00f8\u0080\u0094S\u00f3\t\u0016\u00df\u001c\u0011\u00f4\u0005\u00187q1\u00ed\u0099\u0003\u00e3\u0002\u00a6\u00d7\u0005\u00a7\n\u00c9P\u00a0GK,#<J\u001e\u0010 b.L\u008b\u008c\tC\u00ca\u00da\u00da\u0013X\\\"M\u00bdY\u00e5\u0099\t\u007f\u0012h\u00f2\u00fe\u00a7\u0098\u00da\u00dd\u00fe\u00aaQ jO\u0001W\u0006$p\u00c3m\u00b6\u008a\u00eb\u00de\u0080\u00b1\u00ca\u000b\u00a2\u00db\u00db\u00c2\u00f2\u0005\u0002\u00a3\u00c6\u00edXF\u0019\u0083,";
                            var24_6 = 165;
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
                                var25_3[var23_4++] = SubCategoryTab.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00dd\u0011\u001a[\u0096E\u00d1\u0018A\u00b7\u00c1\u00bbK\u0001=\u00a8\n\u00ddF\u00ce\u0006-/\u00e5 \u0098=lg\u0017\u00ccs\u00a1>\u00f3\u00ebW\u0011\u0019\u0099\u008b\u00cbb\u00be$iO\u00e4\u0006\u008c\u00f2~\u00ed\u00af\u00d5-\u00e3";
                                var24_6 = 57;
                                var21_7 = 24;
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
                                var25_3[var23_4++] = SubCategoryTab.zkmDecodeUtf8(var26_9).intern();
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
                    SubCategoryTab.b = var25_3;
                    SubCategoryTab.c = new String[8];
                    SubCategoryTab.g = new HashMap<K, V>(13);
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
                    var11_12 = new long[4];
                    var8_13 = 0;
                    var9_14 = "zW\u008d_\u001f\u001a\u0098]\u0012\u00a21\u00ac\u000b@JM";
                    var10_15 = 16;
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
                        var9_14 = "B_\u00d9\u00e0S\u001d\"\u00dd\u0081z\u00b0p\u0082h\u00eeR";
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
            SubCategoryTab.e = var11_12;
            SubCategoryTab.f = new Integer[4];
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
        var4_22 = var0_20.doFinal(new byte[]{116, -43, -9, -3, -53, -55, 90, -4});
        ** while (true)
        SubCategoryTab.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x646E;
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
                throw new RuntimeException("su/catlean/fl", exception);
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
            SubCategoryTab.c[n2] = SubCategoryTab.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SubCategoryTab.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x11A6;
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
                throw new RuntimeException("su/catlean/fl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SubCategoryTab.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SubCategoryTab.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SubCategoryTab.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SubCategoryTab.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

