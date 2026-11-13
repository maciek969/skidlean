/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.sound;

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
import kotlin.random.Random;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundSystem;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.CloseSoundManagerEvent;
import su.catlean.api.event.events.client.PostTickEvent;
import su.catlean.config.ConfigStore;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.SoundEngineAccessor;
import su.catlean.mixins.accessors.SoundManagerAccessor;
import su.catlean.module.misc.Assistant;
import su.catlean.sound.Sound;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class Sounds
implements Manager {
    @NotNull
    public static final Sounds INSTANCE;
    private static boolean y;
    @NotNull
    private static final Sound startup;
    @NotNull
    private static final Sound shutdown;
    @NotNull
    private static final Sound firstLaunch;
    @NotNull
    private static final Sound settingsOpen;
    @NotNull
    private static final Sound settingsClose;
    @NotNull
    private static final Sound noSettings;
    @NotNull
    private static final Sound switchRight;
    @NotNull
    private static final Sound switchLeft;
    @NotNull
    private static final Sound enable;
    @NotNull
    private static final Sound disable;
    @NotNull
    private static final Sound lagBack;
    @NotNull
    private static final Sound lag;
    @NotNull
    private static final Sound armorAlert;
    @NotNull
    private static final Sound chatMention;
    @NotNull
    private static final Sound totemPop;
    @NotNull
    private static final Sound rangeIn;
    @NotNull
    private static final Sound rangeOut;
    @NotNull
    private static final Sound configLoad;
    @NotNull
    private static final Sound configSave;
    @NotNull
    private static final Sound kill;
    @NotNull
    private static final Sound crystalKill;
    @NotNull
    private static final Sound foundStash;
    @NotNull
    private static final Sound legoBreaking;
    @NotNull
    private static final Timer timer;
    private static String B;
    private static final long b;
    private static final String[] d;
    private static final String[] f;
    private static final Map g;
    private static final long i;
    private static final long j;

    private Sounds() {
    }

    @NotNull
    public final Sound getSettingsOpen() {
        return settingsOpen;
    }

    @NotNull
    public final Sound getSettingsClose() {
        return settingsClose;
    }

    @NotNull
    public final Sound getNoSettings() {
        return noSettings;
    }

    @NotNull
    public final Sound getSwitchRight() {
        return switchRight;
    }

    @NotNull
    public final Sound getSwitchLeft() {
        return switchLeft;
    }

    @NotNull
    public final Sound getEnable() {
        return enable;
    }

    @NotNull
    public final Sound getDisable() {
        return disable;
    }

    @NotNull
    public final Sound getLagBack() {
        return lagBack;
    }

    @NotNull
    public final Sound getLag() {
        return lag;
    }

    @NotNull
    public final Sound getArmorAlert() {
        return armorAlert;
    }

    @NotNull
    public final Sound getChatMention() {
        return chatMention;
    }

    @NotNull
    public final Sound getTotemPop() {
        return totemPop;
    }

    @NotNull
    public final Sound getRangeIn() {
        return rangeIn;
    }

    @NotNull
    public final Sound getRangeOut() {
        return rangeOut;
    }

    @NotNull
    public final Sound getConfigLoad() {
        return configLoad;
    }

    @NotNull
    public final Sound getConfigSave() {
        return configSave;
    }

    @NotNull
    public final Sound getFoundStash() {
        return foundStash;
    }

    @NotNull
    public final Sound getLegoBreaking() {
        return legoBreaking;
    }

    public final void playToggle(boolean bl) {
        if (!Assistant.INSTANCE.getEnabled() || !timer.hasPassed((int)i)) {
            return;
        }
        if (bl && Random.Default.nextBoolean()) {
            crystalKill.play();
        } else {
            kill.play();
        }
        timer.reset();
    }

    @Flow
    private final void onPostTick(PostTickEvent postTickEvent) {
        block2: {
            String string = Sounds.zkmGetFlag();
            SoundManager soundManager = Globals.getMc().getSoundManager();
            Intrinsics.checkNotNull(soundManager, "null cannot be cast to non-null type su.catlean.mixins.accessors.SoundManagerAccessor");
            SoundSystem soundSystem = ((SoundManagerAccessor)((Object)soundManager)).getSoundEngine();
            Intrinsics.checkNotNull(soundSystem, "null cannot be cast to non-null type su.catlean.mixins.accessors.SoundEngineAccessor");
            SoundEngineAccessor soundEngineAccessor = (SoundEngineAccessor)((Object)soundSystem);
            String string2 = string;
            if (y || !soundEngineAccessor.isLoaded()) break block2;
            y = true;
            if (!ConfigStore.INSTANCE.getConfigManager().getData().isRpcAsked()) {
                firstLaunch.play();
            } else {
                startup.play();
            }
        }
    }

    @Flow
    private final void onCloseSoundManager(CloseSoundManagerEvent closeSoundManagerEvent) {
        shutdown.play();
        Thread.sleep(j);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        Sounds.b = ZkmKeys.a(5188290402429086961L, -8996866272900460662L, MethodHandles.lookup().lookupClass()).a(100031045391592L);
                        var19 = Sounds.b ^ 43768963848668L;
                        Sounds.g = new HashMap<K, V>(13);
                        Sounds.zkmSetFlag(null);
                        var10_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var19 >>> 56);
                        for (var11_2 = 1; var11_2 < 8; ++var11_2) {
                            v2 = v2;
                            v2[var11_2] = (byte)(var19 << var11_2 * 8 >>> 56);
                        }
                        var10_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var17_3 = new String[25];
                        var15_4 = 0;
                        var14_5 = "\u009e\u00c4\u00c0LB\u001e\u00a8x\n\u00bd=\u008b\u00b0\u008aS\u00cf\u00ed\u00d1\u00d4\u00d5\u00e5\u0083e> \u00e5\u009bJ\u009c\u0013%\u00adF\u0088'\\\u00ad;\u00c4\u00a0\u0088!\u0083\u00f7O\u00c5\u0094\r;\u0003\b\u00b1\u009b\u0015Q5\u0014\u0010?\u00df\u0006\u00c6/\u009e\u00a3\u0014!,J\u00da\u0006Fg5\u0018\u00f7m\u00f6r\u0007\u0019\u0003\u0007\u00e4\u00b4\u00a81d\u00191\u00f6b\u00fc\u00d0\u00e0\u00d3\u0089\u0012\u0001 =\u00aa\u00f4\u00aa\u00ff\u007fL#\u00de\u0094\u0015\u00b5#\u008c5P\u0007\u0086\u00ecP\u0097XD\u0085\"\u00b5\u0016\u00b4xnM0\u0018o\u00fa\u00a9#\u00d3m@\u00eaP\u00eax2|\u00cb\u00a8k\u00f6\u0091l$q\u0097\u0011\f \u00a1\u00ea\u00b1\u009aJK\u00d6\u00f4<\u00a8\u0088\u000f'k4O\u00e22\u00f3\u00a2C\u00ff\u00a5\u00d0i]G\u00c0\nYyy\u0010I\u00d5\u00e3\u00e5\u0014\u0006\u00e2\u0093\u001f5U&Rd\u00c4B\u0010\u00a8\u0092<f\u0003\re\u00f4d\u00ea\u00a0\u00e2\u0088\u00b6\u00e6\u00d2\u0010\u0084\u00b3{\u00fc\u001cTO\u008e\u00ea\u00bdy\u00c6\u0017\u00bc\u00d9n \u00f1]\u001b\u00a3\u00d5\u00b8{\u00ac\u00aa\u00dd\u00e5\u00b6c\u0084ow\u0087\u00c8\u00d9S\u00ed\u00e3\u00f0}v\u00d1m\u00d6\u0097\u00fdv\u001e\u0018\u00fcXt\u00f3\u00fc\u001b\u00110\u008adJ\u00f0DB7U\u0084\u00af\u00ec[\u00f1X\u00a0q \u0019\u001c\u0080\u00a4\u00f7&\u00a6\u00a2\u0003\u008bO\u00e2\u00c6zG\u00c9-\u000b\u00ee\u009c\u0084\u00f5\u00a3\u0016\u0016)L|\u00c1e\u00f4\u00e6 \u00e0\u0010C\u00d0\u009e\u00eb\u00c2\u00ac\u009c\u0003\u00f4\u00fd\u0087z\u00840iz\u0018\u001d\u0092\u00f3\u00d6\u00d8\u00d07+\u00e3\u001e\u0087O\f \rx\u00a3\u0099\"\u0099\u00b9FK\u00ef\u00b7\u0088\u00e0\u00bf\"!\u0094\u00ab*\u0084\u001b\u00d0\u001d\u00bb\u009dn\u00f2\u00a0y\u00b77:\u0018\u0012\u00e9\u00e3R\u008b\u00ca*D\u0015A}\u00c3\u0097s\u00a0_\u00a3\u00e4\u0011\u00e4H\u00c3\u00e5n )\u00d0q\u00e37\u00c5\u00cf\\a\u00de\u0016\u00c0\u00c1-\u0017\u00fbA-\u00d3%\u00cf\u00c4-\u0081\u00b9\u00a1y\u007f\u00b8\u00e7\u00bf\u008f A\u00bfX\u001a?j\u00e9\u00bd\u00ee\u00eb\u00efAa\u00d8\u009d4\u00c0\u00f7\u00d9\u00f8-\u00a1\u00bb\u009d\u00f4\u008b\u00f6\u00a0\u0088\u00a0\u00c8X\u0018\u001d\u008bc\u00f3\u00cb\u00f6\u00e2\u00c0\u00f2&\u00das\u00b2\u00d7.\u001d\u0003\u0087\u00d1|\u00fb\u0083\u00b7\u00cd\u0018\u009e\r68\u00db\u00edc\u0092i\u00c1\u00d4B\u0002z\u00e6J=\u00ae1\u0011\u00c2`\u00b6\u008f\u0088\u009e\u00fam\u00df\u0014\u00be\u00bb\u0007\u00b0\u00d6\f.+\u00ea\u00c3%\u008f\u008b\u00b0\u00c9\u00b2O\u00b7\u00e3\u0001Z\u00e6\u00ef\u00d2\u0003\u007fsq\u0095\u00be=\u0083\tnI\u00dbN\u00f8\u00f1\u008b\u00120f\u000be\u00ab\u008b\u009b`d=\u0085\u0096\u00b4\u0085\u0086\u0082\u00f0\u00a5\u00a2T\u00ae\u001dr%6\u0010\u0003\u0005_\u00e0W\u0013\u00f3\u00b0f\u00bd\u00c2\u00c1\u00997\u00b0\\\u0011\u0089B\u00d7F\t\u00c0\u00c3\u00af\u009aC\u00ddp\u000b\u00daT\u00abu\u0018\u0099f\u009b\u00fa\u0087\u001b\u0096\u00b8\u00a0\u00b3\u0099\u00cd#\u00eczY\u00fe\u00e3QZz\u00fd\u00d8\u00b7)\u0019\u00c3\u00d2!\u0088j\"\u00b5b\u00bd:>\u0002W\u0080\u0011>\u00a0\u00cc\u0091\b\u00b39\u00adF\u00faB\u00d4\u0017\u00ee\u0091\u00df\u00fd^\u00cf\u0004\u00a6I$6\u00be`\u00a8\u00c1\u0086}.\u001a/\u00f9|j\u000bM{\u00bdF\u00c1\u00b9KEj\u0001\u00a5\u00e2\u0019\u00f0I1\u00b39w\u001b\u00b7\u00ddj\u00a8\u00aa\u008dH:\u00f6\u0097\u00d2g\u0014\u00fc\u00b43:\u00b4i)\u00d5I\u001aV-\u00f7\u0081\u0087\u00bfI&\u00b6\u001f1\u00c3t,Q\u00b8j\u009b\u00bf\u00f7v\u009cO\"\u00e1\u0018\u00d8\u00ecq\u009b\u00b0:fe\u00fb?\u00b1S\u00bbJ\u0015\u00cdlk\u00fc ;Y\u00d1m3kI\u0083#\u00d4Vr\u00f5\u00b4\u009b\u00a0\u0083F\u00f78\u008e\u00d9\u008e.?\u008dd\u00d1\u00dc24\u0086";
                        var16_6 = 846;
                        var13_7 = 24;
                        var12_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var12_8;
                            v4 = var14_5.substring(v3, v3 + var13_7);
                            v5 = -1;
                            break block14;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var17_3[var15_4++] = Sounds.zkmDecodeUtf8(var18_9).intern();
                            if ((var12_8 += var13_7) < var16_6) {
                                var13_7 = var14_5.charAt(var12_8);
                                ** continue;
                            }
                            var14_5 = "\u0085\u00b6\u00c2\u00feIph\u0011m\u00eaFYr\u00ae\u008b\u008d\u0018Nqh\u0087h\u00c1\u00f6\u00b8\u008b\u00e7\u00c2\u00b0\u008b\u00dd27\u0018\u0007\u00c7\tU\u00b4\u001a\u0097";
                            var16_6 = 41;
                            var13_7 = 16;
                            var12_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var12_8;
                                v4 = var14_5.substring(v6, v6 + var13_7);
                                v5 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var17_3[var15_4++] = Sounds.zkmDecodeUtf8(var18_9).intern();
                            if ((var12_8 += var13_7) < var16_6) {
                                var13_7 = var14_5.charAt(var12_8);
                                ** continue;
                            }
                            break block15;
                            break;
                        }
                    }
                    var18_9 = var10_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                Sounds.d = var17_3;
                Sounds.f = new String[25];
                var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var19 >>> 56);
                for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                    v9 = v9;
                    v9[var6_11] = (byte)(var19 << var6_11 * 8 >>> 56);
                }
                break block16;
lbl66:
                // 1 sources

                while (true) {
                    continue;
                    break;
                }
            }
            var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
            var9_12 = var5_10.doFinal(new byte[]{95, -95, -34, 80, -75, -39, -60, 2});
            ** while (true)
            Sounds.i = ((long)var9_12[0] & 255L) << 56 | ((long)var9_12[1] & 255L) << 48 | ((long)var9_12[2] & 255L) << 40 | ((long)var9_12[3] & 255L) << 32 | ((long)var9_12[4] & 255L) << 24 | ((long)var9_12[5] & 255L) << 16 | ((long)var9_12[6] & 255L) << 8 | (long)var9_12[7] & 255L;
            var0_13 = Cipher.getInstance("DES/CBC/NoPadding");
            v10 = SecretKeyFactory.getInstance("DES");
            v11 = new byte[8];
            v12 = v11;
            v11[0] = (byte)(var19 >>> 56);
            for (var1_14 = 1; var1_14 < 8; ++var1_14) {
                v12 = v12;
                v12[var1_14] = (byte)(var19 << var1_14 * 8 >>> 56);
            }
            break block17;
lbl83:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_13.init(2, (Key)v10.generateSecret(new DESKeySpec(v12)), new IvParameterSpec(new byte[8]));
        var4_15 = var0_13.doFinal(new byte[]{-8, 57, -17, 19, -75, 61, 34, -118});
        ** while (true)
        Sounds.j = ((long)var4_15[0] & 255L) << 56 | ((long)var4_15[1] & 255L) << 48 | ((long)var4_15[2] & 255L) << 40 | ((long)var4_15[3] & 255L) << 32 | ((long)var4_15[4] & 255L) << 24 | ((long)var4_15[5] & 255L) << 16 | ((long)var4_15[6] & 255L) << 8 | (long)var4_15[7] & 255L;
        Sounds.INSTANCE = new Sounds();
        Sounds.startup = new Sound("startup", 1.0f);
        Sounds.shutdown = new Sound("shutdown", 1.0f);
        Sounds.firstLaunch = new Sound("first-launch", 1.0f);
        Sounds.settingsOpen = new Sound("settings-open", 1.0f);
        Sounds.settingsClose = new Sound("settings-close", 1.0f);
        Sounds.noSettings = new Sound("no-settings", 1.0f);
        Sounds.switchRight = new Sound("switch-right", 0.7f);
        Sounds.switchLeft = new Sound("switch-left", 0.7f);
        Sounds.enable = new Sound("enable", 0.7f);
        Sounds.disable = new Sound("disable", 0.7f);
        Sounds.lagBack = new Sound("lag-back", 1.0f);
        Sounds.lag = new Sound("lag", 1.0f);
        Sounds.armorAlert = new Sound("armor-alert", 1.0f);
        Sounds.chatMention = new Sound("chat-mention", 1.0f);
        Sounds.totemPop = new Sound("totem-pop", 1.0f);
        Sounds.rangeIn = new Sound("range-in", 1.0f);
        Sounds.rangeOut = new Sound("range-out", 1.0f);
        Sounds.configLoad = new Sound("config-load", 1.0f);
        Sounds.configSave = new Sound("config-save", 1.0f);
        Sounds.kill = new Sound("kill", 1.0f);
        Sounds.crystalKill = new Sound("crystal-kill", 1.0f);
        Sounds.foundStash = new Sound("found-stash", 1.0f);
        Sounds.legoBreaking = new Sound("lego-breaking", 1.0f);
        Sounds.timer = new Timer();
    }

    public static void zkmSetFlag(String string) {
        B = string;
    }

    public static String zkmGetFlag() {
        return B;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2167;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/d2", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            Sounds.f[n2] = Sounds.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Sounds.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/d2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Sounds.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

