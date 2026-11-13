/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.core;

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
import su.catlean.api.IPasteable;
import su.catlean.command.CommandManager;
import su.catlean.config.ConfigStore;
import su.catlean.discord.PresenceTextProvider;
import su.catlean.gofra.Gofra;
import su.catlean.macro.MacroManager;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.render.MainFramebuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.particle.ParticleManager;
import su.catlean.sound.Sounds;
import su.catlean.ui.Notifications;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.Async;
import su.catlean.util.CatleanUserTracker;
import su.catlean.util.FpsCounter;
import su.catlean.util.HoleManager;
import su.catlean.util.I18n;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PendingBlocks;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlayerListUtil;
import su.catlean.util.ProxyManager;
import su.catlean.util.Scheduler;
import su.catlean.util.ServerInfo;
import su.catlean.util.TimerManager;
import su.catlean.util.WaypointManager;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.combat.TargetUtil;
import su.catlean.util.rotation.RotationManager;
import su.catlean.y0;
import su.catlean.zkm.ZkmKeys;

public final class EventBusSetup {
    private static final long a;
    private static final String b;
    private static final long[] c;
    private static final Integer[] d;
    private static final Map e;

    public static final void bus(@NotNull IPasteable iPasteable) {
        Intrinsics.checkNotNullParameter(iPasteable, b);
        Object[] objectArray = new Object[]{iPasteable, ConfigStore.INSTANCE, ModuleManager.INSTANCE, y0.INSTANCE, DamageUtil.INSTANCE, HudManager.INSTANCE, PlayerListUtil.INSTANCE, ServerInfo.INSTANCE, RotationManager.INSTANCE, InventoryUtil.INSTANCE, CommandManager.INSTANCE, TimerManager.INSTANCE, Notifications.INSTANCE, MovementUtil.INSTANCE, TargetUtil.INSTANCE, ParticleManager.INSTANCE, MacroManager.INSTANCE, WaypointManager.INSTANCE, ProxyManager.INSTANCE, Async.INSTANCE, PresenceTextProvider.INSTANCE, PendingBlocks.INSTANCE, PlaceManager.INSTANCE, FpsCounter.INSTANCE, MainFramebuffer.INSTANCE, Sounds.INSTANCE, CatleanUserTracker.INSTANCE, InterpolationTracker.INSTANCE, Scheduler.INSTANCE, HoleManager.INSTANCE, RenderUtil.INSTANCE, RenderState.INSTANCE, Pipelines.INSTANCE, I18n.INSTANCE};
        Gofra.INSTANCE.plugAll(objectArray);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                block12: {
                    EventBusSetup.a = ZkmKeys.a(-8602483457174832485L, -907383826857794503L, MethodHandles.lookup().lookupClass()).a(4216711779853L);
                    var11 = EventBusSetup.a ^ 19059147357213L;
                    var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var11 >>> 56);
                    for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                        v2 = v2;
                        v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                    }
                    break block12;
lbl13:
                    // 1 sources

                    while (true) {
                        continue;
                        break;
                    }
                }
                var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var15_3 = var13_1.doFinal("k\u00e0$\u00be\u00e8\u00a9%\u00d2".getBytes("ISO-8859-1"));
                ** while (true)
                EventBusSetup.b = EventBusSetup.zkmDecodeUtf8(var15_3).intern();
                EventBusSetup.e = new HashMap<K, V>(13);
                var0_4 = Cipher.getInstance("DES/CBC/NoPadding");
                v3 = SecretKeyFactory.getInstance("DES");
                v4 = new byte[8];
                v5 = v4;
                v4[0] = (byte)(var11 >>> 56);
                for (var1_5 = 1; var1_5 < 8; ++var1_5) {
                    v5 = v5;
                    v5[var1_5] = (byte)(var11 << var1_5 * 8 >>> 56);
                }
                var0_4.init(2, (Key)v3.generateSecret(new DESKeySpec(v5)), new IvParameterSpec(new byte[8]));
                var6_6 = new long[29];
                var3_7 = 0;
                var4_8 = "$\u00c4\u00fd\u00b1\u00cf8\u00c2n\u00b8\u00a3^\u00d4\u00f4\u00c0=k\u00fc\u00b0x\u00fbMi\u00c9/\u0097Z~\u0010L\b\u00ba\u0087\u0007*#>\u00a8Us\u001e\u009aO\u00c8$C\u00ad[%\u00cd9\u009d\u001a\u0084\u00f4\u00f6j\u00b9\u00dcs\u00b6\u00c6]\u00d3\u00c0Y\u0081\u00b3f\u00edZ\u008d\u009a7|\u00a2\u00d3\u00d3~s?\u009e\u00e9Y\u00b6Vy\u001c\u0018\u00d4\u00f9\u00cf\u0093\u00f0\"{\u0093\u00be\u0002\u00d5&\u00e1\u00be\u00f4\u0018\u001c7=F\u00c3@b$\u00fe\u00c5\u0083\u0003\u00b6\u00e7\u0012\u00f2J\u001c\u00b0\u008a>t\u0006w\u0014\u0090\u001a\u00f9\u00c39\u00fe\u0091c@\t\u00cf;,#2V\u00d96bu;\u00df\u0085\u0087\u0019\\8_~(\u00e7(\u00b2[$\u000eRz\u0089$\u0094>\u00b1\u00d5\u00e4\u0086'\u00a3\u00c9\u0083<\u0012\u0099\u00b6\u0084\u00e3\u00c8\u00ebeH<\u00e1\u00f0&z\u0080\u00be C^\u0007;a\u00ec\u0099(\u00ca\u00bc~$\u0080p\u00d9\u009f\u00df\u0015w";
                var5_9 = 216;
                var2_10 = 0;
                while (true) {
                    var7_11 = var4_8.substring(var2_10, var2_10 += 8).getBytes("ISO-8859-1");
                    v6 = var6_6;
                    v7 = var3_7++;
                    v8 = ((long)var7_11[0] & 255L) << 56 | ((long)var7_11[1] & 255L) << 48 | ((long)var7_11[2] & 255L) << 40 | ((long)var7_11[3] & 255L) << 32 | ((long)var7_11[4] & 255L) << 24 | ((long)var7_11[5] & 255L) << 16 | ((long)var7_11[6] & 255L) << 8 | (long)var7_11[7] & 255L;
                    v9 = -1;
                    break block10;
                    break;
                }
lbl43:
                // 1 sources

                while (true) {
                    v6[v7] = v10;
                    if (var2_10 < var5_9) ** continue;
                    var4_8 = "\u0002\u00f3Ja]\u009c;\u00e4E\u00cd\u00a1,\u00aa\u001f\u0087{";
                    var5_9 = 16;
                    var2_10 = 0;
                    while (true) {
                        var7_11 = var4_8.substring(var2_10, var2_10 += 8).getBytes("ISO-8859-1");
                        v6 = var6_6;
                        v7 = var3_7++;
                        v8 = ((long)var7_11[0] & 255L) << 56 | ((long)var7_11[1] & 255L) << 48 | ((long)var7_11[2] & 255L) << 40 | ((long)var7_11[3] & 255L) << 32 | ((long)var7_11[4] & 255L) << 24 | ((long)var7_11[5] & 255L) << 16 | ((long)var7_11[6] & 255L) << 8 | (long)var7_11[7] & 255L;
                        v9 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl56:
                // 1 sources

                while (true) {
                    v6[v7] = v10;
                    if (var2_10 < var5_9) ** continue;
                    break block11;
                    break;
                }
            }
            var8_12 = v8;
            var10_13 = var0_4.doFinal(new byte[]{(byte)(var8_12 >>> 56), (byte)(var8_12 >>> 48), (byte)(var8_12 >>> 40), (byte)(var8_12 >>> 32), (byte)(var8_12 >>> 24), (byte)(var8_12 >>> 16), (byte)(var8_12 >>> 8), (byte)var8_12});
            v10 = ((long)var10_13[0] & 255L) << 56 | ((long)var10_13[1] & 255L) << 48 | ((long)var10_13[2] & 255L) << 40 | ((long)var10_13[3] & 255L) << 32 | ((long)var10_13[4] & 255L) << 24 | ((long)var10_13[5] & 255L) << 16 | ((long)var10_13[6] & 255L) << 8 | (long)var10_13[7] & 255L;
            switch (v9) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl69:
                // 1 sources

                ** continue;
            }
        }
        EventBusSetup.c = var6_6;
        EventBusSetup.d = new Integer[29];
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

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x54C7;
        if (d[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = c[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])e.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/mr", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            EventBusSetup.d[n2] = n3;
        }
        return d[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = EventBusSetup.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/mr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(EventBusSetup.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

