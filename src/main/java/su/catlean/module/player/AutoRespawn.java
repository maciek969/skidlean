/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.AutoRespawn$WhenMappings;
import su.catlean.module.player.AutoRespawn$onPlayerUpdate$1;
import su.catlean.module.player.AutoRespawnCommandTimingMode;
import su.catlean.module.player.AutoRespawnDeathCommandMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoRespawn
extends Module {
    @NotNull
    public static final AutoRespawn INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting deathCoords$delegate;
    @NotNull
    private static final ModeSetting deathCommand$delegate;
    @NotNull
    private static final TextSetting kit$delegate;
    @NotNull
    private static final TextSetting command$delegate;
    @NotNull
    private static final ModeSetting commandTiming$delegate;
    private static boolean m;
    private static int S;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private AutoRespawn() {
        super("auto-respawn", Categories.getPlayer(), null, null);
    }

    private final boolean getDeathCoords() {
        return (Boolean)deathCoords$delegate.getValue(this, delegatedProperties[0]);
    }

    private final AutoRespawnDeathCommandMode getDeathCommand() {
        return (AutoRespawnDeathCommandMode)((Object)deathCommand$delegate.getValue(this, delegatedProperties[1]));
    }

    private final String getKit() {
        return (String)kit$delegate.getValue(this, delegatedProperties[2]);
    }

    private final String getCommand() {
        return (String)command$delegate.getValue(this, delegatedProperties[3]);
    }

    private final AutoRespawnCommandTimingMode getCommandTiming() {
        return (AutoRespawnCommandTimingMode)((Object)commandTiming$delegate.getValue(this, delegatedProperties[4]));
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (Globals.getMc().currentScreen instanceof DeathScreen) {
            if (m) {
                ++S;
                if (this.getDeathCoords()) {
                    ModuleMessages.S(this, ModuleMessages.i18n$default(this, "respawn-message", new Object[0], false, 4, null) + " X: " + (int)Globals.getPlayer().getX() + " Z: " + (int)Globals.getPlayer().getZ(), false, null);
                }
                Globals.getPlayer().requestRespawn();
                Globals.getMc().setScreen(null);
                if (this.getCommandTiming() == AutoRespawnCommandTimingMode.DELAYED) {
                    Async async = Async.INSTANCE;
                    int n = 1000;
                    async.getExecutor().execute(new AutoRespawn$onPlayerUpdate$1(n));
                }
                m = false;
            }
        } else {
            m = true;
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (receivePacket.getPacket() instanceof EntityStatusS2CPacket && Globals.getMc().player != null && Globals.getMc().world != null) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundEntityEventPacket");
            EntityStatusS2CPacket entityStatusS2CPacket = (EntityStatusS2CPacket)packet;
            if ((entityStatusS2CPacket.getStatus() == 3 || entityStatusS2CPacket.getStatus() == 60) && Intrinsics.areEqual(entityStatusS2CPacket.getEntity(Globals.getWorld()), Globals.getPlayer()) && this.getCommandTiming() == AutoRespawnCommandTimingMode.INSTANT) {
                this.respawn();
            }
        }
    }

    public final void respawn() {
        if (Globals.getMc().player != null) {
            switch (AutoRespawn$WhenMappings.A[this.getDeathCommand().ordinal()]) {
                case 1: {
                    PacketUtil.sendCommand("kit " + this.getKit());
                    break;
                }
                case 2: {
                    PacketUtil.sendCommand("back");
                    break;
                }
                case 3: {
                    PacketUtil.sendCommand("home");
                    break;
                }
                case 4: {
                    PacketUtil.sendCommand(this.getCommand());
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getDeathCommand() == AutoRespawnDeathCommandMode.KIT;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getDeathCommand() == AutoRespawnDeathCommandMode.CUSTOM;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoRespawn.a = ZkmKeys.a(7811351097077925840L, -1408723325581327534L, MethodHandles.lookup().lookupClass()).a(231759894766863L);
                        var20 = AutoRespawn.a ^ 116532479366824L;
                        AutoRespawn.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[25];
                        var16_4 = 0;
                        var15_5 = "\u00e1~\u00df,S\u0014\u00bdz\u00af\u0090\u008dG\u00b5\u0089\u00bf\u009e\u00f8\u00ec7*O.=(\u008e\"~\"Z\u00b1\u0002r\u00e4W\u00fd\u00ff>nk\f\u009a\u00f8\u00b8@0N5sE\u00cc\u0080T\u00d972\u00be\u00c8\u00db\u00ff\u00b1{|]\u0087\u0010\u0092E\u00a2\u00ab\u00a4P\u001a\u009a\u00a9\u0007\u00d90\u00e2\u00d4\u0089\u0088\u0010\u00b2\u00e2\u00b1*a\u0091\u00aca\u00cfg\u00d5V qf\u00d5\u0010\u0012^\u00ef\u00a7\u00ccS\u00fa1A\u00dbG%}4?\u00df\u0010kG\u0017\u00dbhz\u00ed\u00f3\u00b7\u00e6\u00a5\u0002\u001b\u0095|\u00e1@\u00cf/8\u00b1j\u00d9\u00d18\u00b4\u00f2R\u0010\u00d1<;\u0080\u00d1\u00e7hn\u00d1V\u00e6\u000e\u00ad<\u00fd\u00bc\u00c6W\tK\u0017\u00e7\u001f\u00a9]\u00f6_\t\u00b6_\u0095Am\u00dfpr\u00f9\u00e0j\u00f3\u00ee\u00c4\u0010R\u00b1\u008avV\u00b8\u00fc\u00da\u00b7 \u00d4\u0087n\u00e7E\u001d\u00d8\u00f4&\u00af\u00abS\u00a8\u007f\u0002\u00f0\u0092J\u0018\u00a99n[\u00bc/\u00c9VI\u008a\u0088\u00a5\u00ed\u0010\u00ecDp\u00cb\n\u0004\u00a4\u00f8\u00ca1\u008a\u00a5\u00c0\u00d6\u0089\u001e 9s\u0013\u008f\u00d2\u00fd\u0090\u00a0R\u00a3\u00b1\u00e7\u001bL\u00caq\u00a7\u00ddX*!W\u00d7g\u0016\u00eb\u00c4\u00e5\u001f\u00ce\u0001*\u0010%\u00f6\u00bf[\u00f5\u00f3\u0006\u0080\u0096\b\u0085\u000e\u008c\u000f.\u00c9 \u000eo \u00b9`\u009dM}|\u008bL(\u0081%\u00afe\u00a1\u0015\u00f6T\u000b\u00d8\u00c3<v\u00a2$:n~\u00ae4 .\u0099<#\u00d1Z\u00b7w\u0001\u00f1i64\u00db\u0004A\u00ce~\u00c7lB<\u0003\u00e1\u0013N\u008b2\u0089\\\u00f8\u0091\u0018P\u0092\u00db\u008cE1\u00c1\u00cd\u0019H\u008b\u000fAv\u00fe1yE\u00d4\u00a7\u0092\u008f3X\u0010#\u0095\u00f4\u00cd\u00dby\u00dd\u00caunZ\u00c8\u00f5\u00b7\fj\u00a0\u0002\u0084<~\u00c9\u0001xA \u00b3\u00ea\u00ddi\u00fb\u00b6A\u001c\u00e7\u00b7X\u00c7\f\u009c\u00a9\u00c4\u00bba\u0084\u00ef\u00f9#5\u0083\u0006\u00de\u00a3\u000e\u00fc\u008b\u00f8\u0004>~wf\u00919\u00b8\u0006N\u0018<Z\u00ae<>'\u008694}\u00b6 QR\u00e8\u00e0\u00f0\u00d8\u00ec\u0004]Y\u00d7\u00e5\u00b1E:\u00d4\u00c7\u0012\u00dc\u00c2\\8%l\u00ae\u00fd1\u00a2\u00de\u00cb\u001b\u0015Cz\u00f3\u001d6\u0004\u00c0\u00f6\u00da\u00f2\u0003)\u00e4\u0089\\\u00f7\u00e0Tr_\u00fa\u00a4\u009f@\u00e6t\u00b0N\u0007\u00beE\u0006\u00a8\u0019!W=\u0007\u00b2`\u0083@\u00d8\u00cfj \u00cb\u001a1\u00e5\u00c5%\u00b5\u00d1z\u00e9\u0017R\u00af\f>\u009b\u00c2\u00e7\u00a30\u00de\u00d8\u0099\u00cd\u0097\u00a2\u00db\u00e6C\u00f7\u00bb\u00bb\u00d8y%Q`b]P\u00e3H\u00b2A\u00db@\u00a6\u00a0\u0002\u00be\u00e0\u00fc\u00d9\u0083~\u00f6\u0002:\u0019\u00cb\u0086\u0096E\u00b4\u00cd+\u00c1\u00ff\u0018\u00ad\u0017v\u00f9s\u0011\u00ca\u00cc\u0017M.\u00e8\u0083\u001f\u009b\u0002\u0017udp\u00cau\u00ae\u00c8 \u0010\u00ee}\u00a9Sq%\u00e2\u00f5R\u001b'\u009e\u008a\u00b5\u00d7x\u009fm\u0090\u008e\u00c4<<\u009d\u00c4M\u00f3\u0007\u00b3A\u00058p\u00c1\u00d8\u0003J\u00daj\u008c\u00de\u009d\u00a8\u00c6\u0089\u00c1V\u0088\u00d1L\u0088\u00e4\u008b\u00c7\u00bb\u00a5~\u00ba\u00eaj\u00a5\u0004\u0089\u0096\u0082X\u009f`g /I\u0003\u00d7\u00cbm\u009f\u00ebr\u00ec\u0089\u00de\u00ae\u00b3\u0091\u000f\u00b7P \u0081\u00d3\u007f\u00df\u00e2\u0082bpmU\u00f4\u00bb\u0082 \u00e9dxX!\u00cb\u00f8YT45\u0084F\u00bd\u00abx\u0010\u007f\u0018\u00b3\u00a1G\u0095\u00a0\u00e1#\f\u00c22\u0099|0\u0097\u001f\u00d7r\u00c0'\u00d7\u00c1\u00bd\u00f7\u00d3\u0010\u00ff1]L\u0080\u00fb\u00b89\\\u00e2P\u00e6\u00df9\u00bbx(\u00a7[@K\u00bea\u001c\u00ea\u0097{d\u00b7\u00c2x\u00d5\u00e5c\u008a\u0003b\u00ab2q\b>\td\u00bd\u0000\u0097\u00b9\u00bb[\u0006\r\u009d\u001d\u001e\u0018\u0015";
                        var17_6 = 846;
                        var14_7 = 64;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoRespawn.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "V\u001dZ\u0010\u0007\u0096|K>fR\u009e{;\u00dc\u008a\u0010KGc\u00d6uH@\u00e5\u0017\u0084R\u0007\u000b\u007f\u00fc\u00f5";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoRespawn.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                AutoRespawn.b = var18_3;
                AutoRespawn.c = new String[25];
                AutoRespawn.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u009f\u00fa\f\u0000\u00f6\u00e6\u00cf\u00ea\n\u00afl\u00b1\u0090g\u00ae2";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "u5\u00ce\u001dj\u00c1R\u00d6\u00b2\u00aam\u00f31\u00f8\u00e6\r";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
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
        AutoRespawn.e = var6_12;
        AutoRespawn.g = new Integer[4];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoRespawn.class, "deathCoords", "getDeathCoords()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoRespawn.class, "deathCommand", "getDeathCommand()Lsu/catlean/b3;", 0)), Reflection.property1(new PropertyReference1Impl(AutoRespawn.class, "kit", "getKit()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(AutoRespawn.class, "command", "getCommand()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(AutoRespawn.class, "commandTiming", "getCommandTiming()Lsu/catlean/xo;", 0))};
        AutoRespawn.delegatedProperties = var30_20;
        AutoRespawn.INSTANCE = new AutoRespawn();
        AutoRespawn.deathCoords$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoRespawn.INSTANCE, "death-coords", true, null, null, 12, null);
        AutoRespawn.deathCommand$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoRespawn.INSTANCE, "death-command", (Enum)AutoRespawnDeathCommandMode.KIT, null, null, 12, null);
        AutoRespawn.kit$delegate = SettingFactory.textSetting$default(AutoRespawn.INSTANCE, "kit-name", "kitname", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        AutoRespawn.command$delegate = SettingFactory.textSetting$default(AutoRespawn.INSTANCE, "command", "/ebal", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        AutoRespawn.commandTiming$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoRespawn.INSTANCE, "command-timing", (Enum)AutoRespawnCommandTimingMode.DELAYED, null, null, 12, null);
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2CF9;
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
                throw new RuntimeException("su/catlean/e3", exception);
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
            AutoRespawn.c[n2] = AutoRespawn.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoRespawn.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x365E;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoRespawn.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoRespawn.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoRespawn.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoRespawn.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

