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
import net.minecraft.entity.EntityPosition;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.FlagEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientboundPlayerPositionPacketAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class NoServerRotate
extends Module {
    @NotNull
    public static final NoServerRotate INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting legit$delegate;
    private static float savedYaw;
    private static float savedPitch;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private NoServerRotate() {
        super("no-server-rotate", Categories.getPlayer(), null, null);
    }

    public final boolean getLegit() {
        return (Boolean)legit$delegate.getValue(this, delegatedProperties[0]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        savedYaw = Globals.getPlayer().getYaw();
        savedPitch = Globals.getPlayer().getPitch();
    }

    @Flow
    private final void onFlag(FlagEvent flagEvent) {
        if (this.getLegit()) {
            Globals.getPlayer().setYaw(savedYaw + 1.0E-6f);
            Globals.getPlayer().setPitch(savedPitch + 1.0E-6f);
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket && !this.getLegit()) {
            Packet<?> packet = receivePacket.getPacket();
            EntityPosition entityPosition = ((PlayerPositionLookS2CPacket)packet).comp_3228();
            Intrinsics.checkNotNullExpressionValue(entityPosition, "change(...)");
            EntityPosition entityPosition2 = entityPosition;
            Packet<?> packet2 = packet;
            Intrinsics.checkNotNull(packet2, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientboundPlayerPositionPacketAccessor");
            ((ClientboundPlayerPositionPacketAccessor)((Object)packet2)).setChange(new EntityPosition(entityPosition2.comp_3148(), entityPosition2.comp_3149(), Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch()));
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    NoServerRotate.a = ZkmKeys.a(-7560937557423633448L, -512671032944002783L, MethodHandles.lookup().lookupClass()).a(6613293068898L);
                    var16 = NoServerRotate.a ^ 93975860220069L;
                    NoServerRotate.d = new HashMap<K, V>(13);
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var16 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var16 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[6];
                    var12_4 = 0;
                    var11_5 = "\u0015#@\u009f\u00f4f\u000b\u0083\u00d3\u00b5\u00934\u00f7\u00e5\u0084\u00e3\u009d\u0001\u00f9\u0099,\u0086\u0010\u00ca#\u00bc\u00df\u00af\u00f8\u00dfM\u00c2\u0005\u0010p\u000e(\u00b4x@\u0018\u00dad\u00fd`\u008b\u0002\u00c6\u00fd0n\u00e4\u008a\u00a0<I(\u00a7\u00b9\u0094\u00ed\u00e9\u00d6\u00caE\u00a8D\u0006\u0083\u00dd\u0001\u009a\u00a5\u00902\u00f0\u00e8\u00b8q\u00b1\u0098\u00a6\u0016\u00a7\u00f0\u00f0\u00c4\u00e2\u0003\u00f6\u0097h\u00a1\u0080W\u00e8\u00e69\u00bf?:P\u0011\nZ_a\u0013l\u0097\u00aa\u00e0\u0096Z\u0094\u00a0\u00b5\u00e6)\u00c7\u0018+k\u0097\u00e5\u0099ru\u00fa\u001c\u00f0l&%tx\u00d0\u0088\u0088Yv\u00ee*\t\u00e1\u00ad\u00f2:\u008b\b\u00cf\u00c5\u001a7\u001cR29z:A\u00b3j\u00d7}\b\u00e2\u00d1\u0007\u00ae\u00ee\u00b6\u00bf\u00b6\u00a7\u00af\u00be4\u007fk\u00d3\u0002\u0007V\u00fe=\u00aa\u00bc\u00a6\u00d7\u00ach\u00d2\u00d0\u00ec\u008c\u00c8\u00bfJ\u00ce\u00e8\u00cdV\u0013\u0013\u0085\u00f4o\u0014\u0014\u00e1\u00c7 \u0082b\u009c\u007f9\u00b5_1\u009a\u00f2s5\u00b7\u00f5\u00fad\u00f1\u00c7\u008e\u0099\u009b\u0010\u00801r\u0014\u00d3\u00c6\u00d9U6p\u0097#G\u0007\u00fb\u0005";
                    var13_6 = 251;
                    var10_7 = 40;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = NoServerRotate.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u008a\u0002\u0007k_\u0092n\"n\u0087u:&y\u00bf\u00cb\u0018\u00b0v\u00dau7^-s\u00a3\u0082>e\n|\u00f8B3\u00f5*\u00a9\u009b\u00e5\u00a0\u00da";
                        var13_6 = 41;
                        var10_7 = 16;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = NoServerRotate.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            NoServerRotate.b = var14_3;
            NoServerRotate.c = new String[6];
            var2_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var16 >>> 56);
            for (var3_11 = 1; var3_11 < 8; ++var3_11) {
                v9 = v9;
                v9[var3_11] = (byte)(var16 << var3_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var6_13 = var2_10.doFinal(new byte[]{-5, -126, -38, 15, -70, -48, -27, 67});
        ** while (true)
        var0_12 = ((long)var6_13[0] & 255L) << 56 | ((long)var6_13[1] & 255L) << 48 | ((long)var6_13[2] & 255L) << 40 | ((long)var6_13[3] & 255L) << 32 | ((long)var6_13[4] & 255L) << 24 | ((long)var6_13[5] & 255L) << 16 | ((long)var6_13[6] & 255L) << 8 | (long)var6_13[7] & 255L;
        var22_14 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NoServerRotate.class, "legit", "getLegit()Z", 0))};
        NoServerRotate.delegatedProperties = var22_14;
        NoServerRotate.INSTANCE = new NoServerRotate();
        NoServerRotate.legit$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoServerRotate.INSTANCE, "legit", true, null, null, (int)var0_12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x539E;
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
                throw new RuntimeException("su/catlean/ek", exception);
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
            NoServerRotate.c[n2] = NoServerRotate.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoServerRotate.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ek" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoServerRotate.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

