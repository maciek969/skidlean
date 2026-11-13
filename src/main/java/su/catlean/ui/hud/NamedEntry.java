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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.ui.ArrayListEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class NamedEntry {
    @NotNull
    private final String name;
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

    public NamedEntry(@NotNull String string, @NotNull Animation animation, boolean bl) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)NamedEntry.zkmBootstrap("y", (int)5885, (long)(0x2AFAB6D63C271BB3L ^ l2))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)NamedEntry.zkmBootstrap("y", (int)22358, (long)(0x149936C9DDFF5A1EL ^ l2))));
        this.name = string;
        this.animation = animation;
        this.removing = bl;
    }

    public /* synthetic */ NamedEntry(String string, Animation animation, boolean bl, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_QUINT, h);
        bl = false;
        this(string, animation, bl);
    }

    @NotNull
    public final String getName() {
        return this.name;
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
    public final String getName2() {
        return this.name;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    public final boolean isRemoving2() {
        return this.removing;
    }

    @NotNull
    public final NamedEntry copy(@NotNull String string, @NotNull Animation animation, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)NamedEntry.zkmBootstrap("y", (int)11253, (long)(0x27B5503033095C9FL ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)NamedEntry.zkmBootstrap("y", (int)22756, (long)(0x3FF0E100BC822F88L ^ l))));
        return new NamedEntry(string, animation, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ NamedEntry copy$default(long l, NamedEntry namedEntry, String string, Animation animation, boolean bl, int n, Object object) {
        boolean bl2;
        block9: {
            boolean bl3;
            block8: {
                void var6_6;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-3470309760604006809L, (long)l);
                bl3 = var6_6 & 1;
                if (callSite == false) {
                    if (bl3) {
                        string = namedEntry.name;
                    }
                    bl3 = var6_6 & 2;
                }
                CallSite callSite2 = callSite;
                if (l >= 0L) {
                    if (callSite2 == false) {
                        if (bl3) {
                            animation = namedEntry.animation;
                        }
                        bl3 = var6_6 & 4;
                    }
                    callSite2 = callSite;
                }
                if (callSite2 != false) break block8;
                if (!bl3) break block9;
                bl3 = namedEntry.removing;
            }
            bl2 = bl3;
        }
        return namedEntry.copy(string, animation, bl2);
    }

    @NotNull
    public String toString() {
        boolean bl = this.removing;
        Animation animation = this.animation;
        String string = this.name;
        return "Staff(name=" + string + ", animation=" + animation + ", removing=" + bl + ")";
    }

    public int hashCode() {
        int n = this.name.hashCode();
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
            if (!(object2 instanceof NamedEntry)) {
                return false;
            }
            object2 = object;
        }
        NamedEntry namedEntry = (NamedEntry)object2;
        boolean bl2 = Intrinsics.areEqual(this.name, namedEntry.name);
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.animation, namedEntry.animation);
        }
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.removing;
        }
        if (!bl) {
            if (bl2 != namedEntry.removing) {
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
                            NamedEntry.a = ZkmKeys.a(5174657365208650441L, 983708807868453106L, MethodHandles.lookup().lookupClass()).a(155201265158289L);
                            NamedEntry.d = new HashMap<K, V>(13);
                            var16 = NamedEntry.a ^ 18056410013011L;
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
                            var22_5 = "7\u00a3\b\u00b0\u00bc$\u00e6\u000f\u00e1Y\u0010\u00d2\u008f\u009a\u0002\u0093\u0018[\u00c1\u00e0\u00cb\u00b64\u0098\u00be\u00fa\u0012\u001c8@\u0007\u00cb7\u00fc\u00a0}\u00d8\u001c\u0096\u001c;\u0018 W\u0019\u00de\u00e9\u00ba\u00ff\b\u00f7-;\u009a\u0090\u0086\u00ca\u00a5\u00d5\u0004\u00f5\u00c4\u00faTV  MFG/G$y\u00d4\u0084\u0082%\u00dd\u00e6\bnN\u00e8/\u00d2\u0091;\u00ce\u00a1w\u0082\u0017\u0080\u00e6*\u00d9S\u009d\u0010\u001d\u00173%\u008f\u00d5\u009d\u009b2\u00b8\u00b1\u00a6&#\u00d1\u00db";
                            var24_6 = 116;
                            var21_7 = 16;
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
                                var25_3[var23_4++] = NamedEntry.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "!\u00f67\u0001IC\u00df\u00dd\u0017\u00fb\u0089\u00e5\u00de\u00d8\t\u0085\u0012{\u00e5\u0087U\u008e\u0015\u00ca\u001c_\u00c5K\u00ef8\u00acD\u0018\u00b4-\u00c1\u008a\u00db\u00cd9\u001c\u00e8@\u0081\u00e6a\u0005\u009em\u00ba\u00f1\u00e1n\u00ea\u00c5\u0094,";
                                var24_6 = 57;
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
                                var25_3[var23_4++] = NamedEntry.zkmDecodeUtf8(var26_9).intern();
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
                    NamedEntry.b = var25_3;
                    NamedEntry.c = new String[7];
                    NamedEntry.g = new HashMap<K, V>(13);
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
                    var9_14 = "\u00db\u008a\u0094\u00fb\u00cdB\u00af\u0004\u0089\u00b3lTU+\u00e7L";
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
            NamedEntry.e = var11_12;
            NamedEntry.f = new Integer[2];
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
        var4_22 = var0_20.doFinal(new byte[]{101, 119, -49, -2, 54, 23, -89, 29});
        ** while (true)
        NamedEntry.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5016;
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
                throw new RuntimeException("su/catlean/pz", exception);
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
            NamedEntry.c[n2] = NamedEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NamedEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6E0F;
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
                throw new RuntimeException("su/catlean/pz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NamedEntry.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NamedEntry.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NamedEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NamedEntry.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

