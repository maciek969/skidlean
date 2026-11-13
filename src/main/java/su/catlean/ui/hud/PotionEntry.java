/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

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
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.ui.ArrayListEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PotionEntry {
    @NotNull
    private final StatusEffectInstance instance;
    @NotNull
    private final Animation animation;
    private boolean removing;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public PotionEntry(@NotNull StatusEffectInstance statusEffectInstance, @NotNull Animation animation, boolean bl) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(statusEffectInstance, (String)((Object)PotionEntry.zkmBootstrap("m", (int)18984, (long)(0x340233B235E19097L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)PotionEntry.zkmBootstrap("m", (int)16549, (long)(0x59CA7FEB06241A18L ^ l))));
        this.instance = statusEffectInstance;
        this.animation = animation;
        this.removing = bl;
    }

    public /* synthetic */ PotionEntry(StatusEffectInstance statusEffectInstance, Animation animation, boolean bl, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_QUINT, h);
        bl = false;
        this(statusEffectInstance, animation, bl);
    }

    @NotNull
    public final StatusEffectInstance getInstance() {
        return this.instance;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    public final boolean isRemoving() {
        return this.removing;
    }

    public final void setRemoving(boolean bl) {
        this.removing = bl;
    }

    @NotNull
    public final StatusEffectInstance getInstance2() {
        return this.instance;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    public final boolean isRemoving2() {
        return this.removing;
    }

    @NotNull
    public final PotionEntry copy(@NotNull StatusEffectInstance statusEffectInstance, @NotNull Animation animation, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(statusEffectInstance, (String)((Object)PotionEntry.zkmBootstrap("m", (int)118, (long)(0x3AB8300A04C512A7L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)PotionEntry.zkmBootstrap("m", (int)12623, (long)(0x6FD84A1E8BF02399L ^ l))));
        return new PotionEntry(statusEffectInstance, animation, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ PotionEntry copy$default(long l, PotionEntry potionEntry, StatusEffectInstance statusEffectInstance, Animation animation, boolean bl, int n, Object object) {
        boolean bl2;
        block9: {
            boolean bl3;
            block8: {
                void var6_6;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2788275870207159746L, (long)l);
                bl3 = var6_6 & 1;
                if (callSite != false) {
                    if (bl3) {
                        statusEffectInstance = potionEntry.instance;
                    }
                    bl3 = var6_6 & 2;
                }
                CallSite callSite2 = callSite;
                if (l >= 0L) {
                    if (callSite2 != false) {
                        if (bl3) {
                            animation = potionEntry.animation;
                        }
                        bl3 = var6_6 & 4;
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == false) break block8;
                if (!bl3) break block9;
                bl3 = potionEntry.removing;
            }
            bl2 = bl3;
        }
        return potionEntry.copy(statusEffectInstance, animation, bl2);
    }

    @NotNull
    public String toString() {
        boolean bl = this.removing;
        Animation animation = this.animation;
        StatusEffectInstance statusEffectInstance = this.instance;
        return "PotionHolder(instance=" + statusEffectInstance + ", animation=" + animation + ", removing=" + bl + ")";
    }

    public int hashCode() {
        int n = this.instance.hashCode();
        n = n * 31 + this.animation.hashCode();
        n = n * 31 + Boolean.hashCode(this.removing);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = ArrayListEntry.u();
        Object object2 = this;
        if (!bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (!bl) {
            if (!(object2 instanceof PotionEntry)) {
                return false;
            }
            object2 = object;
        }
        PotionEntry potionEntry = (PotionEntry)object2;
        boolean bl2 = Intrinsics.areEqual(this.instance, potionEntry.instance);
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.animation, potionEntry.animation);
        }
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.removing;
        }
        if (!bl) {
            if (bl2 != potionEntry.removing) {
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
        block19: {
            block18: {
                block17: {
                    block16: {
                        block15: {
                            PotionEntry.a = ZkmKeys.a(-8470295709590641938L, 5119415053560401807L, MethodHandles.lookup().lookupClass()).a(44125292741753L);
                            PotionEntry.d = new HashMap<K, V>(13);
                            var16 = PotionEntry.a ^ 26038580669572L;
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
                            var25_3 = new String[7];
                            var23_4 = 0;
                            var22_5 = "j\u0096\u0083>\u0015\u00d6\u00064F\u0087k>\u00c2\u0015\u00b7\u0011\u00ean\u0093\u00c3\u0007\u0095\u0007\u00f2\u00b2\u00e5O\u00e0\u00dc\nA\u0007 \u0094o\u00f1\u0098#\u00ff~4\u00d4\u00cd\u00f4\u00d3\u009b)\u00fe\u000bt\u00ee\u00f0A\u00f8?\u00c0\u0086\u00cf\u00d4\u00dd)\u00bb\u00b2\u001a\u0093\u0018@X\u00a1%\u00e5\u00b8\u00ab\u00d3<\b\u00e7\u000b\u00e8'\u009b\u00e7CH'\u00ddf\u00ec\u00b5v(\u0098\u00c9\u008f\u00cc\u0093y\u0081W\u00fd\u000e\u00bf9zz\u00a4\u009ek\u00f5\t\u0012\u00abS%8\u00912\u00f3\u00d7\u0082d\u00bd\u0005\b\u00fdi\u008e \u0005o\u00d7\u0018\n\u008d&)k\u008e\u0001\u00d4\u0006\u00ba\u0013\u00f1\u00a5\u0084\u00d0\u00c7.\u00f2\u00cf\u00ef\u0085\u0004\u00bb\u00c7";
                            var24_6 = 156;
                            var21_7 = 32;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block15;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = PotionEntry.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u000f\u009f\u00fbN\u00b5\u00c8\u00bf\u009dD\u00fa\u00b0Q\u00f8\u00e1z\u000e\u00e5x\u0092\u008b\u00c2;\u00ef\u00bcoy\u0010\u0003\u00f6^\u009c\u0098 `\\S\u00b8T\u00a8\u00dd`] q\u0082A+\u00f1yU#\u00b4D\u00b1e>\b6\u00d9\u00c6M\u00caG\u0017s";
                                var24_6 = 65;
                                var21_7 = 32;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block15;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = PotionEntry.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block16;
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
                    PotionEntry.b = var25_3;
                    PotionEntry.c = new String[7];
                    PotionEntry.g = new HashMap<K, V>(13);
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
                    var11_12 = new long[2];
                    var8_13 = 0;
                    var9_14 = "\"\r\u00f4O<\u00b1\u00b8\u000b,0\u00e5\u001eL\u008a\u00d3p";
                    var10_15 = 16;
                    var7_16 = 0;
                    while (true) {
                        break block17;
                        break;
                    }
lbl73:
                    // 1 sources

                    while (true) {
                        var11_12[v10] = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                        if (var7_16 < var10_15) ** continue;
                        break block18;
                        break;
                    }
                }
                var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                v10 = var8_13++;
                var13_18 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                ** while (true)
            }
            PotionEntry.e = var11_12;
            PotionEntry.f = new Integer[2];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v11 = SecretKeyFactory.getInstance("DES");
            v12 = new byte[8];
            v13 = v12;
            v12[0] = (byte)(var16 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v13 = v13;
                v13[var1_21] = (byte)(var16 << var1_21 * 8 >>> 56);
            }
            break block19;
lbl96:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v11.generateSecret(new DESKeySpec(v13)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{7, -80, 100, -34, -61, -74, -5, -74});
        ** while (true)
        PotionEntry.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A28;
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
                throw new RuntimeException("su/catlean/ms", exception);
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
            PotionEntry.c[n2] = PotionEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PotionEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ms" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x225E;
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
                throw new RuntimeException("su/catlean/ms", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PotionEntry.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PotionEntry.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ms" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PotionEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PotionEntry.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

