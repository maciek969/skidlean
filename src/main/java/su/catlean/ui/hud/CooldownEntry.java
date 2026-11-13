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
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.ui.ArrayListEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CooldownEntry {
    @NotNull
    private final Item instance;
    private final int endTick;
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
    private static final long i;

    public CooldownEntry(@NotNull Item item, int n, @NotNull Animation animation, boolean bl) {
        Intrinsics.checkNotNullParameter(item, "instance");
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.instance = item;
        this.endTick = n;
        this.animation = animation;
        this.removing = bl;
    }

    public /* synthetic */ CooldownEntry(Item item, int n, Animation animation, boolean bl, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_QUINT, i);
        bl = false;
        this(item, n, animation, bl);
    }

    @NotNull
    public final Item getInstance() {
        return this.instance;
    }

    public final int getEndTick() {
        return this.endTick;
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
    public final Item getInstance2() {
        return this.instance;
    }

    public final int getEndTick2() {
        return this.endTick;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    public final boolean isRemoving2() {
        return this.removing;
    }

    @NotNull
    public final CooldownEntry copy(@NotNull Item item, int n, @NotNull Animation animation, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)CooldownEntry.zkmBootstrap("h", (int)30222, (long)(0x130EB5E35501980L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)CooldownEntry.zkmBootstrap("h", (int)5295, (long)(0x7B5BCB70CC3F7B23L ^ l))));
        return new CooldownEntry(item, n, animation, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ CooldownEntry Z(CooldownEntry cooldownEntry, Item item, int n, Animation animation, boolean bl, int n2, long l, Object object) {
        int n3;
        block12: {
            int n4;
            block11: {
                void var5_6;
                long l2;
                l2 = a ^ l2;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-4297313081762296017L, (long)l2);
                n4 = var5_6 & 1;
                if (callSite != false) {
                    if (n4 != 0) {
                        item = cooldownEntry.instance;
                    }
                    n4 = var5_6 & 2;
                }
                CallSite callSite2 = callSite;
                if (l2 > 0L) {
                    if (callSite2 != false) {
                        if (n4 != 0) {
                            n = cooldownEntry.endTick;
                        }
                        n4 = var5_6 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l2 >= 0L) {
                    if (callSite2 != false) {
                        if (n4 != 0) {
                            animation = cooldownEntry.animation;
                        }
                        n4 = var5_6 & CooldownEntry.zkmBootstrap2("v", (int)27776, (long)(0x571A30CDB8C65046L ^ l2));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == false) break block11;
                if (n4 == 0) break block12;
                n4 = cooldownEntry.removing ? 1 : 0;
            }
            n3 = n4;
        }
        return cooldownEntry.copy(item, n, animation, n3 != 0);
    }

    @NotNull
    public String toString() {
        boolean bl = this.removing;
        Animation animation = this.animation;
        int n = this.endTick;
        Item item = this.instance;
        return "CooldownHolder(instance=" + item + ", endTick=" + n + ", animation=" + animation + ", removing=" + bl + ")";
    }

    public int hashCode() {
        int n = this.instance.hashCode();
        n = n * 31 + Integer.hashCode(this.endTick);
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
            if (!(object2 instanceof CooldownEntry)) {
                return false;
            }
            object2 = object;
        }
        CooldownEntry cooldownEntry = (CooldownEntry)object2;
        int n = Intrinsics.areEqual(this.instance, cooldownEntry.instance);
        if (!bl) {
            if (n == 0) {
                return false;
            }
            n = this.endTick;
        }
        if (!bl) {
            if (n != cooldownEntry.endTick) {
                return false;
            }
            n = Intrinsics.areEqual(this.animation, cooldownEntry.animation) ? 1 : 0;
        }
        if (!bl) {
            if (n == 0) {
                return false;
            }
            n = this.removing ? 1 : 0;
        }
        if (!bl) {
            if (n != cooldownEntry.removing) {
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
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            CooldownEntry.a = ZkmKeys.a(-2730729274920635671L, 7139244234669377216L, MethodHandles.lookup().lookupClass()).a(265283085774707L);
                            CooldownEntry.d = new HashMap<K, V>(13);
                            var16 = CooldownEntry.a ^ 104925830174350L;
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
                            var22_5 = "\u00e6\u0013\u008f\u00e6u\\,\u00b8G\t\u001a\u000b\u00baH\u00e3/n\u00b6Zz\u000b\u00c8\u0084\u00c3\u000e\f\u000f\u00cf\u00a3\u00ef\u00bf\u00fe\u0018u\u00ab\u0015c\u00a1j\u009bO\u00b7\u00be\u00ca\u00d3Z!\u0099\b,\u00964\u00dc\u00e6\u00f2\b\u00c2 \u00a2YHw\u0085\u0095\u00f1S\u00dcla8\u0018\u0091o=\u00e2D\u0019\u00c7#\u00b54\u00ef\u0084\u00e2\u0093\u0096\u00e2\u00ca\u0083\u00a8 ~u\u00a7\u00b7\u00035\u00b1Lx\u00d3\u0003*\u0092\\I*9>\u000f\u0083\u00ac\u00e8h6i\u0083\u00b97\u00c1\u000e\u0017a \u00044\u00e1\n\u00dc\u00eb\u00ca\u0006\u0091\u0019\u0012+g\"OLN#\u000fy\tah\u001e\u00f9\u0081G\u00f4k^\u001a\u0097 \u00c8_\u00db\u000f\u00d2#\u00cf\u0000\u00c1\u00cd\b\u0001\u000e\u007f\u00efQ\"\u0003\u00a4\u0083\u00d1\u00dc\u00fc\u0000DS}\u00ac\u00876\u00d8\u00fe";
                            var24_6 = 189;
                            var21_7 = 32;
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
                                var25_3[var23_4++] = CooldownEntry.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u000b\u00fc\u0086J\u0012\u00a1\u0006\f\u00d1\u0089\u00c3\u00a9\u00cc\u00ea\u00c8\u0012\u008cb+h\u00f1K\u00ae\u00e55\u00ea!\u00e3\"\u00b5A\u00d5l\u00c8\u00f6\u00d2\u00a1\u00b5\u001f\f\u008f\u00cb_\u0015\u00a7X\u0002\u0000 \u00c6W/s\u00c8\u00cc\u00f4\u00fd\u0098\u00b5\fY\u00fc\u00e7U\u008d\u00e9G)\u00c9\u0017\u00ce#$\u00d4\u0006\u00faW\u001ep8\u009a";
                                var24_6 = 81;
                                var21_7 = 48;
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
                                var25_3[var23_4++] = CooldownEntry.zkmDecodeUtf8(var26_9).intern();
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
                    CooldownEntry.b = var25_3;
                    CooldownEntry.c = new String[8];
                    CooldownEntry.g = new HashMap<K, V>(13);
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
                    var9_14 = "\u008a)li\u00991\u00a7T\u00cb\u008aX0\u0099\u00e3\u00e15";
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
                        var9_14 = "\u00a1\u00d098\u00f0\u0098\"\u00aba\u00ecj\u00f1\u00a8\u00f5\u00a9\u00de";
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
            CooldownEntry.e = var11_12;
            CooldownEntry.f = new Integer[4];
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
        var4_22 = var0_20.doFinal(new byte[]{4, -88, -52, -80, -110, 39, 113, -7});
        ** while (true)
        CooldownEntry.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5392;
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
                throw new RuntimeException("su/catlean/gb", exception);
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
            CooldownEntry.c[n2] = CooldownEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CooldownEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x792F;
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
                throw new RuntimeException("su/catlean/gb", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CooldownEntry.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CooldownEntry.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/gb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CooldownEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CooldownEntry.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

