/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoAuth
extends Module {
    @NotNull
    public static final AutoAuth INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final TextSetting pass$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private AutoAuth() {
        super("auto-auth", Categories.getPlayer(), null, null);
    }

    private final String getPass() {
        return (String)pass$delegate.getValue(this, delegatedProperties[0]);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (receivePacket.getPacket() instanceof GameMessageS2CPacket) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundSystemChatPacket");
            GameMessageS2CPacket gameMessageS2CPacket = (GameMessageS2CPacket)packet;
            String string = gameMessageS2CPacket.comp_763().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string2, "toLowerCase(...)");
            String string3 = string2;
            if (((CharSequence)this.getPass()).length() == 0) {
                ModuleMessages.S(this, Formatting.RED + ModuleMessages.i18n$default(this, "pass-is-empty", new Object[0], false, 4, null), false, null);
                Object var19_4 = null;
                int n = 16;
                ItemStack itemStack = null;
                NotificationType notificationType = NotificationType.ERROR;
                int n2 = 4;
                String string4 = ModuleMessages.i18n$default(this, "pass-is-empty", new Object[0], false, 4, null);
                String string5 = "Auto Auth";
                Notifications notifications = Notifications.INSTANCE;
                Notifications.push$default(notifications, string5, string4, n2, notificationType, itemStack, n, var19_4);
                return;
            }
            if (StringsKt.contains$default((CharSequence)string3, "/reg", false, 2, null) || StringsKt.contains$default((CharSequence)string3, "/register", false, 2, null) || StringsKt.contains$default((CharSequence)string3, "\u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u0443\u0439\u0442\u0435\u0441\u044c", false, 2, null)) {
                PacketUtil.sendCommand("reg " + this.getPass() + " " + this.getPass());
                Object var30_12 = null;
                int n = 16;
                ItemStack itemStack = null;
                NotificationType notificationType = NotificationType.SUCCESS;
                int n3 = 4;
                String string6 = ModuleMessages.i18n$default(this, "reg-success", new Object[0], false, 4, null);
                String string7 = "Auto Auth";
                Notifications notifications = Notifications.INSTANCE;
                Notifications.push$default(notifications, string7, string6, n3, notificationType, itemStack, n, var30_12);
            } else if (StringsKt.contains$default((CharSequence)string3, "\u0430\u0432\u0442\u043e\u0440\u0438\u0437\u0443\u0439\u0442\u0435\u0441\u044c", false, 2, null) || StringsKt.contains$default((CharSequence)string3, "/l ", false, 2, null) || StringsKt.contains$default((CharSequence)string3, "/login", false, 2, null)) {
                PacketUtil.sendCommand("login " + this.getPass());
                Object var41_20 = null;
                int n = 16;
                ItemStack itemStack = null;
                NotificationType notificationType = NotificationType.SUCCESS;
                int n4 = 4;
                String string8 = ModuleMessages.i18n$default(this, "log-success", new Object[0], false, 4, null);
                String string9 = "Auto Auth";
                Notifications notifications = Notifications.INSTANCE;
                Notifications.push$default(notifications, string9, string8, n4, notificationType, itemStack, n, var41_20);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        AutoAuth.a = ZkmKeys.a(-120582353425786494L, -3124757141016495558L, MethodHandles.lookup().lookupClass()).a(210180638246449L);
                        var20 = AutoAuth.a ^ 16172648321292L;
                        AutoAuth.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[22];
                        var16_4 = 0;
                        var15_5 = "\u00f6e-^\u00f1\u00f4\u00c39\u00df\u00fe\u00db\u00bc\u0010W~f \u0096\u00f4\u00dcdS\u00d1\u001e\u0010\u0097\u000fv\u00b6\u00b6L?\u0001\u0099\u00d4\u00ce\u00ecB\u00d6q\u00b88J'\u0012\u001b%\u00eb\u008d\u0018\u00ff\u001aG#S*\u00c7z\u00c4\u00cc\u00d69\u0001\u0010\u0016\u00e2N\u008f\u00f8b\u00f8io| &\u00fd\u00e73*\u00e2\u00f6\u00e5\u00d8\u00a5q\u00eb\u00eb\b\u00d5\u009ea\u00a5\n\u00c3\u0096\u0081\u00a2T\u00eaC\u001d%\u00ac\u0084Q\u00a0\u0010O\u00ed\u009bm\u00dekZ\u0089\u00a0S\u00d86\u00ed*u\u00ea\u0018O\u00bf(\u00ea\u0019e\u0083\u008d\u0087\u0003\u00c8\u00d5m'2A\u00174a;?\u00f4|\u0006\u0010\u00f5B\u00dck\u000eP\u009f\u00aa\u00ad\u00d0\u00fa\u0081 M\u00a6\u00b2\u0018\u00ad\u00b3\u00e1bo\u00d4P\u00ef\u00ce\u00eb\u0092\u00f7\u00c5\u00ba\u000b\u0006?\u0099\u0005?<\u00bc\u0013\u00c1 \u00f3\u0010\u00b2R\u0010\u00be}\u00df\n\u00f4\"28;.\u00a2\u0011\u00d5\u0006=\u00d8X\u0006\u00c5E\u009a\u008di\u00d4N6b@\u0014\u008b\u00cb\u00e0\u0093\u00a9/\u00f5]\u00baWR\u00feE\u00ec\u0011\u00c0\u001c\u00f7\u00d1AR\u00d7/\f\u00bf\u00cc\u001a\u00d1\u00d1?\u009a_U\u00efU\u00f2u\u0087u\\\u00c7~\u00e3\u00c88DR\u001f\u0093\u0004\u00b9\u0098{\u00fd\u00c6\u00a9\u008f\u00d7B\u00c1\u00bdq\u00f3 \u00eb\fH\u00d1'\u00e5\u00b6\u00fe\u00a2\u00eb\u00d0\u00b8\u000b7&\u00eb\u001d\u0080t~\u00bf\u00ca\u0089\u00e4a\u00d8\u00f1[bo\u00f6I\u0018+:\u00d9U\u00c6\u00f6\\\u00f1\u00bc\u0080\u0003\u00a8\u00b2\u00bf\u009eg)\u00b7\u00b1Sh2u\u0084\u00a0 \u00ff\u00ecW\u0004\u00e8\u00dd^\u00893\u00d6\u00de\u0098\u00f1w?\u00ee<\u008e\u0087>\u00f7\u0007\u00d7\u001ebbDE}\u00b8\u00deN\u0093P0\u00b9\u00a3\u00d9\u0082E~!\u00f1\fc\u00980\u00842e\u0085\u008b3\u00db\u00df^\u008d\u00ee\u000b\u00ae1.\\2 _gu\u00b4\u0087\u0011\u00b6[\u00cb\u00d8|OIQ\u00eb\u00c2\u00ca\u00e4F\u00c6c\u00ad\u00c0\u0082!;\u001d\u00f7\u007fB_\u00d8fGh\u00bb;Z\u00ec\u00c9\u00cd\u00b2b\u0004F\u00a3D\u00b1o\u00bf\u009aI9\u00ec[gy`_M\u00e1.\u00ffE(8'\u00df<\u0091\u00f2\u00aa\u008b\u00e9\u001cD\u00d4\u00ca\u008dY\u0011G\u000b\u001a5\u00127o\u00f2\u0011\u009a\"\u000be\u0010\u00c6\u00efd\u00e9\u00a7\r\u009e\u0091\u00f7\u00f8$\u00fd.\u00d0\u00a3) \u00fe\u0002\u00b0i\u00a8\u00a1/\u00b7\u0084\u00c7&\u0013:T\u0014\u001cN\u00f0\u00a2\u001b\u00ec\u00c0\u00e3\u0099\u00cf\u00b5T\u0014\u009a\u00c0\u007f\u00e70R\u00b11\u009d\u00e2dP\u00ea\u0002V\u00b5\u0091\u008e\u00e3Q\u00cd.\u00ff\u00f4\u00fa\u00dc\u0095\u00c4\u0099\u0005\u00d0\u0091~F/\u00eb\u00d8\u00b0\u008a\u0088\u00c8\u00a0A\u00f2__,\u0090\u00a3I-\u00d9\u00fd\u0018\u0004\u00b5x\u00f7\u00f8\u009e4L\u000e\u00e3`\u00c8]j\u001a\u00f4\u00f0\u00f7\u0091\u008a/\u009d\u0006\u00c5 \u00f2\u0019\u00a8\u008bb\u00c5\u0007@\u00ba!\u0095'\u00cehE\u00ba\u000b\u00f6\u00cf\u001e\u0086\u0093-\u00b9\u0097\u009e\u00c7\u00fdg.\u00a1\u00ff\u0010\u00e3\u00be5\u0089\u00bf\u0084\u00fc\u009b+\u00f7+\u001a\t\u00d39\u00d6(\u0091y\u0099\u00d1\u00b7\u00d0\u00fc\u0096j\r\u00e4\u007f\u0080\u00faU\u0097\u00bf\u00a5\u0082\u00c1\u000b\u00f0T\u00a2\u0005\u0099\u00ee9\u00b1T\u008dt\u00bb\u0094uM\u001b\u0019N\f";
                        var17_6 = 723;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoAuth.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\f\u00a2Rk\u000eA\u00a4v\u00b4\u00a4T6H\u0085\u00b9\u00f40\u00b0\u00a1/\u00ca\u00b5hf\u00d4kT^\u00da^\u00b3\u00da\u0014\u009f-\bB~\u0006\u00ff\u00f2ND\u001c\u00e4\u00dd\u00b4P\u00a4b\u0097\u00ca\u0083<\u00ff\u0083\n\u00a1\u00a4\u007f\u0085\u00f83\u00cdY";
                            var17_6 = 65;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoAuth.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                AutoAuth.b = var18_3;
                AutoAuth.e = new String[22];
                AutoAuth.i = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "ca\u00ee\u001d\u00a2\u00111\u0004\u0097cM\u001fc\u00f6DQ\u00dfz\u009c~\u00bb\u008c\u00bb2";
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
        AutoAuth.g = var6_12;
        AutoAuth.h = new Integer[3];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoAuth.class, "pass", "getPass()Ljava/lang/String;", 0))};
        AutoAuth.delegatedProperties = var26_20;
        AutoAuth.INSTANCE = new AutoAuth();
        AutoAuth.pass$delegate = SettingFactory.textSetting$default(AutoAuth.INSTANCE, "password", "c1a2t3l4e5a6n", null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7A75;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/eh", exception);
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
            AutoAuth.e[n2] = AutoAuth.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoAuth.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/eh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x55BC;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/eh", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoAuth.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoAuth.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/eh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoAuth.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoAuth.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

