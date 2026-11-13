/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

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
import kotlin.text.StringsKt;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystalDamageSyncMode;
import su.catlean.module.player.SpeedMine;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.Timer;
import su.catlean.util.combat.DamageData;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CrystalTimers {
    @NotNull
    public static final CrystalTimers INSTANCE;
    private static float placeDelay;
    @NotNull
    private static final Timer F;
    @NotNull
    private static final Timer O;
    private static boolean V;
    private static float r;
    private static long v;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private CrystalTimers() {
    }

    public final float getPlaceDelay() {
        return placeDelay;
    }

    public final void setPlaceDelay(float f) {
        placeDelay = f;
    }

    @NotNull
    public final Timer J() {
        return F;
    }

    @NotNull
    public final Timer n() {
        return O;
    }

    public final boolean e() {
        return V;
    }

    public final void Y(boolean bl) {
        V = bl;
    }

    public final float d() {
        return r;
    }

    public final void X(float f) {
        r = f;
    }

    public final long V() {
        return v;
    }

    public final void Q(long l) {
        v = l;
    }

    public final void setDelay(float f) {
        placeDelay = f;
        F.reset();
        V = !V;
    }

    public final void resetPlace() {
        O.reset();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean shouldSync(@Nullable DamageData damageData, int n, boolean bl, int n2, @NotNull AutoCrystalDamageSyncMode autoCrystalDamageSyncMode, boolean bl2, short s, boolean bl3, float f, float f2) {
        long l;
        block10: {
            block12: {
                block11: {
                    long l2 = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)s << 48 >>> 48) ^ a;
                    l = l2 ^ 0x7DF568F37E1DL;
                    Intrinsics.checkNotNullParameter((Object)autoCrystalDamageSyncMode, "damageSync");
                    if (damageData == null) {
                        return true;
                    }
                    if (autoCrystalDamageSyncMode == AutoCrystalDamageSyncMode.OFF) {
                        return true;
                    }
                    if (autoCrystalDamageSyncMode != AutoCrystalDamageSyncMode.CONDITIONS) break block10;
                    if (!bl3) break block11;
                    Item item = Items.END_CRYSTAL;
                    Intrinsics.checkNotNullExpressionValue(item, "END_CRYSTAL");
                    if ((float)InventoryUtil.INSTANCE.findItemSlot(item, true) <= 64.0f * f) break block12;
                }
                if (!bl2) return true;
                if (damageData.isLethal()) return true;
                if (!(damageData.getDamage() < f2)) return true;
            }
            boolean bl4 = true;
            boolean bl5 = bl4;
            if (!bl5) {
                return true;
            }
        }
        if (damageData.getDamage() - placeDelay > 2.0f) {
            return true;
        }
        if (F.hasPassed((int)((510.0f + (float)(bl ? 60 : 0)) / ServerInfo.INSTANCE.getServerAddress()))) {
            return true;
        }
        if (!SpeedMine.getMinePositions$default(SpeedMine.INSTANCE, false, 1, null, l).isEmpty()) {
            return true;
        }
        if (Globals.getNetworkHandler().getServerInfo() == null) return false;
        net.minecraft.client.network.ServerInfo serverInfo = Globals.getNetworkHandler().getServerInfo();
        Intrinsics.checkNotNull(serverInfo);
        String string = serverInfo.address;
        Intrinsics.checkNotNullExpressionValue(string, "ip");
        if (!StringsKt.contains$default((CharSequence)string, "tntland", false, 2, null)) return false;
        if (V) {
            return true;
        }
        if (O.hasPassed(15500)) return false;
        return true;
    }

    public final void clampDelay(float f) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)9218629032220341229L, (long)l);
        float f2 = f;
        if (callSite != null) {
            if (f2 < 0.0f) {
                return;
            }
            f2 = f;
        }
        r = f2;
        v = Globals.now();
    }

    public final float K(float f) {
        return f;
    }

    public final void reset() {
        placeDelay = 0.0f;
        r = 0.0f;
        v = 0L;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        CrystalTimers.a = ZkmKeys.a(-8594906701976024921L, 3442588459598677257L, MethodHandles.lookup().lookupClass()).a(111904850753440L);
                        CrystalTimers.d = new HashMap<K, V>(13);
                        var11 = CrystalTimers.a ^ 119406026992802L;
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
                        var20_3 = new String[4];
                        var18_4 = 0;
                        var17_5 = "\u00ach\u00df\u0007\u0090\u00e6%_\u009b\nh\u00e6q\u008bx\u0088\u0010\u0003\u00c8m\u00c0{\u00e4\u00b0\u008e-)T\u0092,9\u0018\u00d4";
                        var19_6 = 33;
                        var16_7 = 16;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = CrystalTimers.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "l\u00ad\u00aa\u00ee\u001a\u0083\u00eb\u008ae\u00ae\u00f0_\u008c\u001a\u00f8`\u00f8\"6\u00ee\u00e0#\u00a4\u00c1@\u001c\u0006\u0098\u00d3D\u009d9\u0018\u009b\u00e8\u000f*\u00d9\u00f2\u00b7\u00f2\u00d0\u0096\u0086yJ.X\u00c3)'dy\u0098\u00ec\u00ec\u00a1";
                            var19_6 = 57;
                            var16_7 = 32;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = CrystalTimers.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
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
                CrystalTimers.b = var20_3;
                CrystalTimers.c = new String[4];
                CrystalTimers.g = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "B\u0094C\u00b1\u001b\u0010\u0000\u0007\u00e3\u00d8\u00dd\u00b5\u00d8\u00d6\u00ceA\u00a5\u00d9\u00aa\u009a#\u0092\"\u00b8";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        CrystalTimers.e = var6_12;
        CrystalTimers.f = new Integer[3];
        CrystalTimers.INSTANCE = new CrystalTimers();
        CrystalTimers.F = new Timer();
        CrystalTimers.O = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x20AF;
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
                throw new RuntimeException("su/catlean/n8", exception);
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
            CrystalTimers.c[n2] = CrystalTimers.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalTimers.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/n8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6723;
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
                throw new RuntimeException("su/catlean/n8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CrystalTimers.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CrystalTimers.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/n8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalTimers.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CrystalTimers.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

