/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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
import net.minecraft.network.packet.c2s.common.ResourcePackStatusC2SPacket;
import net.minecraft.network.packet.s2c.common.ResourcePackSendS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.util.Globals;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class AntiResourcePack
extends Module {
    @NotNull
    public static final AntiResourcePack INSTANCE;
    private static boolean C;
    private static boolean Y;
    private static int K;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AntiResourcePack() {
        super("anti-resource-pack", Categories.getMisc(), null, null);
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        block3: {
            int n;
            block2: {
                int n2 = ModuleManager.zkmIntConstant();
                Intrinsics.checkNotNullParameter(receivePacket, "e");
                int n3 = n2;
                n = receivePacket.getPacket() instanceof ResourcePackSendS2CPacket;
                if (n3 == 0) break block2;
                if (n == 0) break block3;
                C = true;
                Y = false;
                n = 0;
            }
            K = n;
            receivePacket.cancel();
        }
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent var1_1) {
        block19: {
            block15: {
                block18: {
                    block16: {
                        block17: {
                            block14: {
                                var2_2 = AntiResourcePack.a ^ 120002776911400L;
                                var8_3 = var2_2 ^ 19131035333132L;
                                v0 = ModuleManager.zkmIntConstant();
                                Intrinsics.checkNotNullParameter(var1_1, "e");
                                var12_4 = v0;
                                if (var12_4 == 0 || (v1 = Globals.getNetworkHandler().getServerInfo()) == null) ** GOTO lbl-1000
                                v2 = v1.address;
                                v3 /* !! */  = v2;
                                v1 = v2;
                                if (var12_4 == 0) ** GOTO lbl15
                                if (v3 /* !! */  != null) {
                                    v1 = (CharSequence)v1;
                                    v3 /* !! */  = "crystalpvp.cc";
lbl15:
                                    // 2 sources

                                    v4 = StringsKt.contains$default((CharSequence)v1, v3 /* !! */ , false, 2, null);
                                    if (var12_4 != 0) {
                                        v4 = v4;
                                    }
                                } else lbl-1000:
                                // 2 sources

                                {
                                    v4 = false;
                                }
                                if (v4) {
                                    this.disable(var8_3);
                                }
                                v5 = AntiResourcePack.C;
                                if (var12_4 == 0) break block14;
                                if (v5 == 0) break block15;
                                v5 = AntiResourcePack.K;
                            }
                            var13_5 = v5;
                            AntiResourcePack.K = var13_5 + 1;
                            cfr_temp_0 = (double)AntiResourcePack.K - (15.0 + Math.random() * 15.0);
                            v6 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                            if (var12_4 == 0) break block16;
                            if (v6 <= 0) break block17;
                            v6 = (double)AntiResourcePack.Y;
                            if (var12_4 == 0) break block16;
                            if (v6 == false) {
                                PacketUtil.sendPacket(new ResourcePackStatusC2SPacket(Globals.getPlayer().getUuid(), ResourcePackStatusC2SPacket.Status.ACCEPTED));
                                AntiResourcePack.Y = true;
                            }
                        }
                        v6 = (cfr_temp_1 = (double)AntiResourcePack.K - (40.0 + Math.random() * 20.0)) == 0.0 ? 0 : (cfr_temp_1 > 0.0 ? 1 : -1);
                    }
                    if (var12_4 == 0) break block18;
                    if (v6 <= 0) break block15;
                    v6 = (double)AntiResourcePack.Y;
                }
                if (var12_4 != 0 && v6 != false) {
                    PacketUtil.sendPacket(new ResourcePackStatusC2SPacket(Globals.getPlayer().getUuid(), ResourcePackStatusC2SPacket.Status.SUCCESSFULLY_LOADED));
                    AntiResourcePack.C = (double)false;
                    v6 = (double)AntiResourcePack.C;
                }
            }
            if (var12_4 != 0) break block19;
            Module.zkmSetFlag(new Module[3]);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    AntiResourcePack.a = ZkmKeys.a(-1412228687822135172L, -5815952478061240875L, MethodHandles.lookup().lookupClass()).a(253099714829001L);
                    var20 = AntiResourcePack.a ^ 2726936875091L;
                    AntiResourcePack.d = new HashMap<K, V>(13);
                    var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var20 >>> 56);
                    for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                        v2 = v2;
                        v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                    }
                    var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var18_3 = new String[2];
                    var16_4 = 0;
                    var15_5 = "D\u0096H\u00d4\"&\u00fe\u00db\u00b3Y\u0082\u0087fe*\u00fd{\u00d0\u00bb\u000b\u000e\u00d1\f^\u00e8\u0091\u00d6k=\u00f6\u00e7\u0018(\u00a1\u0086T\u00f9\u00d8s\u00bd\u00bf\u00a1?\u00a7\u00f9!=\u00d3\u00b9n\u0093n\u0093\u0010\u00cc\u00db!\u0012\u00f3\u0090\u00a7\u00e5\u0082\u00eb4\u00b8\u00faI\u00d7l\u00c0Z\u001b";
                    var17_6 = 73;
                    var14_7 = 32;
                    var13_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        continue;
                        break;
                    }
lbl22:
                    // 1 sources

                    while (true) {
                        var18_3[var16_4++] = AntiResourcePack.zkmDecodeUtf8(var19_9).intern();
                        if ((var13_8 += var14_7) < var17_6) {
                            var14_7 = var15_5.charAt(var13_8);
                            ** continue;
                        }
                        break block12;
                        break;
                    }
                    v3 = ++var13_8;
                    var19_9 = var11_1.doFinal(var15_5.substring(v3, v3 + var14_7).getBytes("ISO-8859-1"));
                    ** while (true)
                }
                AntiResourcePack.b = var18_3;
                AntiResourcePack.c = new String[2];
                AntiResourcePack.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v4 = SecretKeyFactory.getInstance("DES");
                v5 = new byte[8];
                v6 = v5;
                v5[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v6 = v6;
                    v6[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v4.generateSecret(new DESKeySpec(v6)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u00e4\u00bd\tMt\u00fa\n\u00d8\u00e6\u00ca\u000b:.\u00c3\u00be\u00eb";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v7 = var6_12;
                    v8 = var3_13++;
                    v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v10 = -1;
                    break block13;
                    break;
                }
lbl57:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00106\u0019G\u00ec\u0004RQs\u00a8j\u00ff\u00ee\u00fel\u0013";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v7 = var6_12;
                        v8 = var3_13++;
                        v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v10 = 0;
                        break block13;
                        break;
                    }
                    break;
                }
lbl70:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    break block14;
                    break;
                }
            }
            var8_18 = v9;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v11 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v10) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl83:
                // 1 sources

                ** continue;
            }
        }
        AntiResourcePack.e = var6_12;
        AntiResourcePack.f = new Integer[4];
        AntiResourcePack.INSTANCE = new AntiResourcePack();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x45EF;
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
                throw new RuntimeException("su/catlean/q9", exception);
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
            AntiResourcePack.c[n2] = AntiResourcePack.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AntiResourcePack.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2FF4;
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
                throw new RuntimeException("su/catlean/q9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AntiResourcePack.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AntiResourcePack.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AntiResourcePack.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AntiResourcePack.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

