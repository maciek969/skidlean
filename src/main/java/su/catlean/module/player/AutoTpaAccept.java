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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.FriendManager;
import su.catlean.util.Globals;
import su.catlean.util.PacketUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class AutoTpaAccept
extends Module {
    @NotNull
    public static final AutoTpaAccept INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting grief$delegate;
    @NotNull
    private static final BooleanSetting onlyFriends$delegate;
    @NotNull
    private static final Timer timer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AutoTpaAccept() {
        super("auto-tpa-accept", Categories.getPlayer(), null, null);
    }

    private final boolean getGrief() {
        return (Boolean)grief$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setGrief(boolean bl) {
        grief$delegate.setValue(this, delegatedProperties[0], bl);
    }

    private final boolean getOnlyFriends() {
        return (Boolean)onlyFriends$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setOnlyFriends(boolean bl) {
        onlyFriends$delegate.setValue(this, delegatedProperties[1], bl);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket var1_1) {
        block9: {
            block3: {
                block6: {
                    block7: {
                        block8: {
                            block4: {
                                block5: {
                                    block2: {
                                        v0 = FriendManager.Z();
                                        Intrinsics.checkNotNullParameter(var1_1, "event");
                                        var17_2 = v0;
                                        v1 = var1_1.getPacket() instanceof GameMessageS2CPacket;
                                        if (var17_2) break block2;
                                        if (!v1) break block3;
                                        v1 = AutoTpaAccept.timer.hasPassed(250);
                                    }
                                    if (!v1) break block3;
                                    v2 = ((GameMessageS2CPacket)var1_1.getPacket()).comp_763().getString();
                                    Intrinsics.checkNotNullExpressionValue(v2, "getString(...)");
                                    var18_3 = v2;
                                    v3 = StringsKt.contains$default((CharSequence)var18_3, "\u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", false, 2, null);
                                    if (var17_2) break block4;
                                    if (v3) break block5;
                                    v3 = StringsKt.contains$default((CharSequence)var18_3, "tpaccept", false, 2, null);
                                    if (var17_2) break block4;
                                    if (!v3) break block6;
                                }
                                v3 = this.getOnlyFriends();
                            }
                            if (var17_2) break block7;
                            if (!v3) break block8;
                            v3 = FriendManager.INSTANCE.isFriend(this.isFromKnownPlayer(var18_3));
                            if (var17_2) break block7;
                            if (!v3) break block6;
                        }
                        v4 = this;
                        if (var17_2) ** GOTO lbl34
                        v3 = v4.getGrief();
                    }
                    if (v3) {
                        v4 = this;
lbl34:
                        // 2 sources

                        v5 = v4.isFromKnownPlayer(var18_3);
                    } else {
                        v5 = "";
                    }
                    PacketUtil.sendCommand("tpaccept " + v5);
                }
                AutoTpaAccept.timer.reset();
            }
            if (!var17_2) break block9;
            Module.zkmSetFlag(new Module[1]);
        }
    }

    private final String isFromKnownPlayer(String string) {
        Object object;
        block2: {
            Object v3;
            block1: {
                Collection<PlayerListEntry> collection = Globals.getNetworkHandler().getListedPlayerListEntries();
                Intrinsics.checkNotNullExpressionValue(collection, "getListedOnlinePlayers(...)");
                Iterable iterable = collection;
                for (Object t : iterable) {
                    PlayerListEntry playerListEntry = (PlayerListEntry)t;
                    CharSequence charSequence = string;
                    String string2 = playerListEntry.getProfile().name();
                    Intrinsics.checkNotNullExpressionValue(string2, "name(...)");
                    if (!StringsKt.contains$default(charSequence, string2, false, 2, null)) continue;
                    v3 = t;
                    break block1;
                }
                v3 = null;
            }
            if ((object = (PlayerListEntry)v3) != null && (object = ((PlayerListEntry)object).getProfile()) != null && (object = object.name()) != null) break block2;
            object = "-";
        }
        return object;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        AutoTpaAccept.a = ZkmKeys.a(-7438319786699045345L, 6538543648046080450L, MethodHandles.lookup().lookupClass()).a(52321821731271L);
                        var20 = AutoTpaAccept.a ^ 98761874256384L;
                        AutoTpaAccept.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[14];
                        var16_4 = 0;
                        var15_5 = "\u0094j\u0097\u0019\u0018\u0018\u009d\u007f\u00a473\u00c0;b\u00ac\u008e\u00106\u00eb$\u00cf\u00c6W\u0080\u00062;\u00e9\u00a0W\u0014\u0086(\u0010\u00b2\u00b90\u0080A\u00bbu\u0007\u00d5\u00f6\\\u0005\u00a33\u00cc_(\u0004\u0087-\u00b8:F9\u0097\u00a6P\u000fk#\u00ca\u00fd\u0098$\u00c5\u0012b\u00ce\u00e2\u00bb\u00f2\u00b3\u0005\u00e1A\u001a\u00de\u009d\u00aeE\u0005*\u00f7\u0089\u00e7f\u00c1 #e\u0099\u00b5'\u0099\u0091A\u009d\u00f5\u00d3$\u00b5:lj\u00e9a\u008f\u00ba\u00ae^\u00f5\u00df\r\u0013<\u0083\u00ad\b\u009cu \u00dd.n\u00d9\u009a\u0014\u00f4\nX\u001b\u0004LZ?B9\u00e6_\u00d1\u0002\u008f\u00cf\u00b6\u00dd\u009f\u00f5+&\u00d8\u00a1\u0018\u00dc\u0018\u0086\u00f9w\u00a9t\u0019\u00bd1\t\u00e16cl\u00e6\u00d6a~\u00eb\u000e&:\u00d3d\u001b@V\u00da9\u0016\u00a1\u00f5\t\u00de\u0085[\u00ady\u001b\u00ad\u00dda\u001f\u0003\u0001\u001b\u00a3 \u00d9X\u0007\u00e1Xz85\u008e\u00f10/N?\u0001&\u00fc[\u00f2w\u0081\u00ea\u008bb\u00e1`\u0090}\u0007b\n\u00a3\u001c\u00cfi\u0080\u00f5\u00e1u\u00a9\u0087\u0085\u0018\u00beA\u007f\u00b4U=\u0081K\u00a5\u00a9\u0081h\u0016j\u0096\u0099\u00d6\u0081\u0096\u00ae\u00c0\u00fd'\u00e7\u0018\u0000\u00b4\u00b2\u00a1\u00ca\u0085\u0082D\u0007\u00f9\u0099\u0083\u00a8\u0002\u00f6\u00a0S\u0005K\u00b2\u00ec\u00a88d 2\u0081\u0088\u009e,\u0098\u001cv\u00cb\u008e\u00be`\u00f6p\u00f3\u00a77\u0018{\u0085\u0004\u00fa\u00b7\u0006\u00d2\u0012\u00b3\u0001T\u00ec\u0093\u00a0 #kv\u00c2o(x\u0087\u00ce\u009e\u00ed\u0099\u00ba\u0007\u00e8\u00c0t\u00a3\u00a9i\u00f8\u00d1L\u00a6\u0082\u0085\u00d8\u00db\u00cao\f\u00d6";
                        var17_6 = 363;
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
                            var18_3[var16_4++] = AutoTpaAccept.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u008e\u0005\u00a83\u00b7\u0016xk\u00e0\u0086@;>\u00dajp\u000e\u0004\u00c7\u001aR\u0081)\u00d6+\u00c4\u00e85\u0093m\u008b\u00060\u001e\u0018\u008d\u00f8\u00f3G\u00e7\u0082Zo\u00b2 \u0097\u00eb\u0087\u0084\u00ce\u008f\u00e3\u0085\u00fb\u0005\u00e0\u00ce@\u00cf\u00d9\u0015\u00ec\u0001\u00fag\u0006\u0015\u00fb\u001e\u0003\u0004\u0090\u00ea\u00d27\u00edH\u00cc`o\u0086";
                            var17_6 = 81;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = AutoTpaAccept.zkmDecodeUtf8(var19_9).intern();
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
                AutoTpaAccept.b = var18_3;
                AutoTpaAccept.c = new String[14];
                AutoTpaAccept.g = new HashMap<K, V>(13);
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
                var4_14 = "\u00c6\u0012bT'\u00cen!\u00a6\u0082\u00db\u00d3\u00bbKbZ1\u00b1r\u00a8\u00efo\u009a\u00b6";
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
        AutoTpaAccept.e = var6_12;
        AutoTpaAccept.f = new Integer[3];
        var26_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoTpaAccept.class, "grief", "getGrief()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoTpaAccept.class, "onlyFriends", "getOnlyFriends()Z", 0))};
        AutoTpaAccept.delegatedProperties = var26_20;
        AutoTpaAccept.INSTANCE = new AutoTpaAccept();
        AutoTpaAccept.grief$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoTpaAccept.INSTANCE, "grief", false, null, null, 12, null);
        AutoTpaAccept.onlyFriends$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoTpaAccept.INSTANCE, "only-friends", true, null, null, 12, null);
        AutoTpaAccept.timer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5432;
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
                throw new RuntimeException("su/catlean/ec", exception);
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
            AutoTpaAccept.c[n2] = AutoTpaAccept.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoTpaAccept.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ec" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2345;
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
                throw new RuntimeException("su/catlean/ec", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoTpaAccept.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoTpaAccept.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ec" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoTpaAccept.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoTpaAccept.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

