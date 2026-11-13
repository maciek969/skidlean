/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

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
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.PostTitleScreenInitEvent;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.config.ConfigStore;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.gofra.Flow;
import su.catlean.gui.DiscordConfirmScreen;
import su.catlean.module.Module;
import su.catlean.module.basic.MoveFix;
import su.catlean.module.misc.Assistant;
import su.catlean.module.misc.RPC;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class PresenceTextProvider
implements Manager {
    @NotNull
    public static final PresenceTextProvider INSTANCE;
    private static final long Y;
    @NotNull
    private static final Timer timer;
    private static boolean A;
    private static Module[] O;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;
    private static final long[] f;
    private static final Long[] g;
    private static final Map i;

    private PresenceTextProvider() {
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        if (RPC.INSTANCE.getEnabled()) {
            if (timer.hasPassed2((int)e)) {
                DiscordRpcSettings discordRpcSettings;
                DiscordRpcSettings discordRpcSettings2 = discordRpcSettings = DiscordRpcSettings.INSTANCE;
                discordRpcSettings2.setAppId(1324838037661945876L);
                if (((CharSequence)INSTANCE.getStatusText()).length() > 0) {
                    discordRpcSettings2.setDetails(INSTANCE.getStatusText());
                }
                discordRpcSettings2.setLargeImage(TuplesKt.to("main", "By Pan4ur & 06ED"));
                discordRpcSettings2.setButton1(TuplesKt.to("Get", "https://catlean.su/"));
                discordRpcSettings2.setState(INSTANCE.getBuildType());
            }
        } else if (DiscordRpcSettings.INSTANCE.isConnected()) {
            DiscordRpcSettings.INSTANCE.sendPresence();
        }
    }

    private final String getStatusText() {
        Object object;
        if (Globals.getMc().getNetworkHandler() != null && Globals.getNetworkHandler().getServerInfo() != null) {
            ServerInfo serverInfo = Globals.getNetworkHandler().getServerInfo();
            Intrinsics.checkNotNull(serverInfo);
            object = "Playing on " + serverInfo.address;
        } else {
            object = Globals.getMc().currentScreen instanceof MultiplayerScreen ? "Selecting server..." : "";
        }
        return object;
    }

    private final String getBuildType() {
        return "public beta test";
    }

    @Flow
    private final void onPostTitleScreenInit(PostTitleScreenInitEvent postTitleScreenInitEvent) {
        if (!A && !ConfigStore.INSTANCE.getConfigManager().getData().isRpcAsked()) {
            Assistant.INSTANCE.setEnabled(true);
            RPC.INSTANCE.setEnabled(true);
            MoveFix.INSTANCE.setEnabled(true);
            Globals.getMc().setScreen(new DiscordConfirmScreen());
            ConfigStore.INSTANCE.getConfigManager().getData().setRpcAsked(true);
        }
        A = true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block18: {
            block17: {
                block19: {
                    block16: {
                        block15: {
                            PresenceTextProvider.a = ZkmKeys.a(-481000771276900038L, -4647109238056793790L, MethodHandles.lookup().lookupClass()).a(126704563012650L);
                            var25 = PresenceTextProvider.a ^ 87790731231810L;
                            PresenceTextProvider.d = new HashMap<K, V>(13);
                            PresenceTextProvider.zkmSetFlag(null);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[7];
                            var21_4 = 0;
                            var20_5 = ":\u00da`0K\u00e06.!=\r\u00a1\u0014%\u00bf}\u00f9X\u0092s%I\u00ddV\u0092\u00fc\u00b8\u00f5\u0099F+\u0012\u001e\u00c9\u00d9\u00af\u0081 \u00f4\u00f8\u0010\u00ff$b\u0085\u00efR\u00b3\u00b4?M\u0005i\u00ccSl\u00a208\u001f\u00e8\u00b6\u00a9v\u00b4I\u0095U\u00ae\u00bb\u00bb*\u00f5&\u00eb\u00ee\u00a9\u00d3\u00fa\u00feh\u00e9\u0011q*\u0095\u0080\u00abD\u00d2\rSmZ\u00d2C\u009bC\u00e2`=m\u00bf\u00ad.\u001f(\u00d3\u00da\u0087\u00d2\u00f4\u0013.\u0004U\u0082h_\u00eczW\n\u00a4\u00a1o^\u0015\u00d1\u00ec\u00eb\u00ab\u0092\u00e3\u00fa\u00f1\u00a6\u001f\u00fby\u00ae\u00df\u00fe\u00c3ceB(\u0006:\u0016\u0006\u00e0\u00fe\u00d2\u0083\u00ecy\u0091\u0007\u00f3\u00f1\u00d9\u00d8\u00ba\u00afc\u0002\u00d74i\r\u00fcf\u00beJ\u0093\u00aa\u0096\u00cf\u00ae\rtu\u0099\u00c3\u00e2\u0098";
                            var22_6 = 188;
                            var19_7 = 40;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block15;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = PresenceTextProvider.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u0001<R\u0005\u00c0v\u00bb\u00f0\u0098\u00ba\u0099B\u00c79\u00b3\u00bd \u00eb\u001eZM\"\u0089`U\u00a8\u00c3\u00e6\u00bf\u008f\u0016?f\u008e4\u008f\u0003\u00cb\u0090\u009e\u0084\u00bf\u00ff\u0087\u001d?\u0081\u00ba\u0007";
                                var22_6 = 49;
                                var19_7 = 16;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block15;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = PresenceTextProvider.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block16;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
                        switch (v5) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl52:
                            // 1 sources

                            ** continue;
                        }
                    }
                    PresenceTextProvider.b = var23_3;
                    PresenceTextProvider.c = new String[7];
                    var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                        v9 = v9;
                        v9[var12_11] = (byte)(var25 << var12_11 * 8 >>> 56);
                    }
                    break block19;
lbl66:
                    // 1 sources

                    while (true) {
                        continue;
                        break;
                    }
                }
                var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var15_12 = var11_10.doFinal(new byte[]{120, 55, 125, 12, 120, 117, 105, -30});
                ** while (true)
                PresenceTextProvider.e = ((long)var15_12[0] & 255L) << 56 | ((long)var15_12[1] & 255L) << 48 | ((long)var15_12[2] & 255L) << 40 | ((long)var15_12[3] & 255L) << 32 | ((long)var15_12[4] & 255L) << 24 | ((long)var15_12[5] & 255L) << 16 | ((long)var15_12[6] & 255L) << 8 | (long)var15_12[7] & 255L;
                PresenceTextProvider.i = new HashMap<K, V>(13);
                var0_13 = Cipher.getInstance("DES/CBC/NoPadding");
                v10 = SecretKeyFactory.getInstance("DES");
                v11 = new byte[8];
                v12 = v11;
                v11[0] = (byte)(var25 >>> 56);
                for (var1_14 = 1; var1_14 < 8; ++var1_14) {
                    v12 = v12;
                    v12[var1_14] = (byte)(var25 << var1_14 * 8 >>> 56);
                }
                var0_13.init(2, (Key)v10.generateSecret(new DESKeySpec(v12)), new IvParameterSpec(new byte[8]));
                var6_15 = new long[2];
                var3_16 = 0;
                var4_17 = "G\u00de\u00d3\u0099y\u00d9'D\u0017_\u0007\u00ec\u00f4S\u00a5y";
                var5_18 = 16;
                var2_19 = 0;
                while (true) {
                    break block17;
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    var6_15[v13] = ((long)var10_22[0] & 255L) << 56 | ((long)var10_22[1] & 255L) << 48 | ((long)var10_22[2] & 255L) << 40 | ((long)var10_22[3] & 255L) << 32 | ((long)var10_22[4] & 255L) << 24 | ((long)var10_22[5] & 255L) << 16 | ((long)var10_22[6] & 255L) << 8 | (long)var10_22[7] & 255L;
                    if (var2_19 < var5_18) ** continue;
                    break block18;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v13 = var3_16++;
            var8_21 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
            var10_22 = var0_13.doFinal(new byte[]{(byte)(var8_21 >>> 56), (byte)(var8_21 >>> 48), (byte)(var8_21 >>> 40), (byte)(var8_21 >>> 32), (byte)(var8_21 >>> 24), (byte)(var8_21 >>> 16), (byte)(var8_21 >>> 8), (byte)var8_21});
            ** while (true)
        }
        PresenceTextProvider.f = var6_15;
        PresenceTextProvider.g = new Long[2];
        PresenceTextProvider.Y = 1324838037661945876L;
        PresenceTextProvider.INSTANCE = new PresenceTextProvider();
        PresenceTextProvider.timer = new Timer();
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        O = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return O;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xEAF;
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
                throw new RuntimeException("su/catlean/wd", exception);
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
            PresenceTextProvider.c[n2] = PresenceTextProvider.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PresenceTextProvider.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x721;
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
                throw new RuntimeException("su/catlean/wd", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            PresenceTextProvider.g[n2] = l4;
        }
        return g[n2];
    }

    private static long b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = PresenceTextProvider.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/wd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PresenceTextProvider.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PresenceTextProvider.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

