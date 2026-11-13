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
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ServerboundMovePlayerPacketAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.zkm.ZkmKeys;

public final class AntiHunger
extends Module {
    @NotNull
    public static final AntiHunger INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting ground$delegate;
    @NotNull
    private static final BooleanSetting sprint$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;

    private AntiHunger() {
        super("anti-hunger", Categories.getPlayer(), null, null);
    }

    private final boolean getGround() {
        return (Boolean)ground$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getSprint() {
        return (Boolean)sprint$delegate.getValue(this, delegatedProperties[1]);
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (sendPacket.getPacket() instanceof PlayerMoveC2SPacket && this.getGround()) {
            Packet<?> packet = sendPacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type su.catlean.mixins.accessors.ServerboundMovePlayerPacketAccessor");
            ((ServerboundMovePlayerPacketAccessor)((Object)packet)).setGround(false);
        }
        if (sendPacket.getPacket() instanceof ClientCommandC2SPacket && this.getSprint() && ((ClientCommandC2SPacket)sendPacket.getPacket()).getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING) {
            sendPacket.cancel();
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
                        AntiHunger.a = ZkmKeys.a(-3001214006639270293L, -870175250357964846L, MethodHandles.lookup().lookupClass()).a(101105687219904L);
                        var20 = AntiHunger.a ^ 65596006072990L;
                        AntiHunger.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[8];
                        var16_4 = 0;
                        var15_5 = "\u0005&\u008f\u00eaqU\u00e7\u00de\u00aa\u00aa\u00f7\u00d9H\u00db\u00a9O\u00c3\u0089\u00dc\u00e3\r.D@ \u00a0&\u00b8O3Nu\u00c8\u0091q\u00e6 tv\u0010J\u00f3\u00ad\u00a9m\u00ca&\u0081\u00a3\u0018\u0016s[\u0085\u00e6\u0002\u00be\u0010\u001e\u0016\u00ce\u00a8ge\u00cb\u00af\u009f\u009b\u00fe\u0010a\\\u00bd\u00b7\u00a0\u00a4\u008e\u00db\f\u00ee\u00a1\u00be\u00ca\u00ba\u00a9\u0007\u00e5\u00a1,`\u00a1Z\u0012\u008f\u00cd\u0098\u00e1\u008d\u0085+\u00bb\u00b1e4\u007fK\u008aT~y\u00d5\u0006\u0099&n\u00ca\u00a9\u0090\u00f8n\tr\u0015\u0019!\u00e1\u00ab\u008e\u00bb\u0012\u0001\u00e3\u00eb\u00cdy\u00ef\u0007\u00a2: \u00f8\u00c5U\u00d9\u00f6\u00e6\u00fd2E\u00f1oJ)\u00ed\u00a3\b\u0080EJ\u0096C\u00c4\u00fa2\nBr\u00fe\u0090\u00c8\u00fa\u00f3\u001aMdz^\u0016Z\u0013II\u0089\u00d5qHU_\u0002\u008cPr7^\u008f\u0086\u00daS\u0013Yj\u0014\u00ablp\u00cd\u00b9\u00fd\u00d0B\u0016>Y\u009bm\u00d1\u00f8\u00d0L\u00dd\u0005/$Q\u0019\u00de\u000fS\u00e1\u00b9\u00f7\u0081\u00f0\u00bb\u008c\u0018\u00fa\u0086\u0082\u00d5\"\u00b4h\u00be\u00fa4|\u00a4\u00f1\u0090\u009aX|\u0084\u00b4\u00f8\u00a2-\u009a_ \u008f4i\u009fG\u00deo\u00bc\u0014L\u0084\u009dY\u00f6o7B\u0006\u00d2\u00b7\u0007\u00e1\u009f51o\u008a!%\u00df\u0002\u00d9";
                        var17_6 = 293;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = AntiHunger.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0087\u00ba\u0015E\u00b0^\u00f6Gs\u009dh\u008e\u009f/\u00f3\u0091\u00cfh\u00e7\u00actf\u0001a\u0006u\u00f2}\u00d8\u00d6Q\u00d0\u0010\u00ba\u00d1\u00bb\u008cv\u0099\u0004\u00e4\u00ed\u00c2\u00f55V\u00b8\u00c0\u0098";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = AntiHunger.zkmDecodeUtf8(var19_9).intern();
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
                AntiHunger.b = var18_3;
                AntiHunger.d = new String[8];
                var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                    v9 = v9;
                    v9[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                }
                var1_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var0_12 = new long[2];
                var4_13 = 0;
                var5_14 = ">\u00d5\u00fe\u009c\u0087\u00aay\u00c3\u0087Q\u000ei\u00e9\u00ba\u0096\u001c";
                var6_15 = 16;
                var3_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl72:
                // 1 sources

                while (true) {
                    var0_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var3_16 < var6_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
            v10 = var4_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AntiHunger.class, "ground", "getGround()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiHunger.class, "sprint", "getSprint()Z", 0))};
        AntiHunger.delegatedProperties = var26_20;
        AntiHunger.INSTANCE = new AntiHunger();
        AntiHunger.ground$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiHunger.INSTANCE, "cancel-ground", true, null, null, (int)var0_12[0], null);
        AntiHunger.sprint$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiHunger.INSTANCE, "cancel-sprint", true, null, null, (int)var0_12[1], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x15FF;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/eb", exception);
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
            AntiHunger.d[n2] = AntiHunger.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AntiHunger.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/eb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AntiHunger.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

