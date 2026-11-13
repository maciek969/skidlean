/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.ElytraMode;
import su.catlean.module.rage.ElytraMode$WhenMappings;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class Flight
extends Module {
    @NotNull
    public static final Flight INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting hSpeed$delegate;
    @NotNull
    private static final FloatSetting vSpeed$delegate;
    @NotNull
    private static final BooleanSetting antiKick$delegate;
    @Nullable
    private static Vec3d vec3d;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    private Flight() {
        super("flight", Categories.getRage(), null, null);
    }

    private final ElytraMode getMode() {
        return (ElytraMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final float getHSpeed() {
        return ((Number)hSpeed$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final float getVSpeed() {
        return ((Number)vSpeed$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final boolean getAntiKick() {
        return (Boolean)antiKick$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    private final void onPreSync(PreSyncEvent preSyncEvent) {
        switch (ElytraMode$WhenMappings.p[this.getMode().ordinal()]) {
            case 1: {
                if (MovementUtil.INSTANCE.isMoving()) {
                    double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, this.getHSpeed(), false, 0.0f, 0.0f, 0.0f, 30, null);
                    Globals.getPlayer().setVelocity(dArray[0], 0.0, dArray[1]);
                } else {
                    Globals.getPlayer().setVelocity(0.0, 0.0, 0.0);
                }
                if (Globals.getMc().options.jumpKey.isPressed()) {
                    Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().add(0.0, this.getVSpeed(), 0.0));
                }
                if (!Globals.getMc().options.sneakKey.isPressed()) break;
                Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().add(0.0, -((double)this.getVSpeed()), 0.0));
                break;
            }
            case 2: {
                Globals.getPlayer().setSprinting(true);
                if (Globals.getPlayer().age % 2 != 0) break;
                preSyncEvent.cancel();
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true, false));
                break;
            }
        }
        if (this.getAntiKick() && Globals.getPlayer().age % 15 == 0) {
            Globals.getPlayer().setVelocity(new Vec3d(Globals.getPlayer().getVelocity().x, Globals.getPlayer().age % 30 == 0 ? -0.08 : 0.08, Globals.getPlayer().getVelocity().z));
        }
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent var1_1) {
        block6: {
            block4: {
                block5: {
                    v0 = ElytraMode$WhenMappings.t();
                    Intrinsics.checkNotNullParameter(var1_1, "event");
                    var16_2 = v0;
                    v1 = this.getMode();
                    v2 = ElytraMode.Creative;
                    if (!var16_2) {
                        if (v1 == v2) {
                            Globals.getPlayer().getAbilities().flying = true;
                            Globals.getPlayer().getAbilities().setFlySpeed(this.getHSpeed() / 10.0f);
                        }
                        v1 = this.getMode();
                        v2 = ElytraMode.MATRIX_JUMP;
                    }
                    if (v1 != v2) break block4;
                    v3 = MovementUtil.INSTANCE;
                    if (var16_2) break block5;
                    if (!(v3.getFallDistance() > 0.0)) break block4;
                    v3 = MovementUtil.INSTANCE;
                }
                var17_3 = MovementUtil.getMovement$default(v3, this.getHSpeed(), false, 0.0f, 0.0f, 0.0f, 30, null);
                v4 = Globals.getPlayer();
                v5 = var17_3[0];
                v6 = Globals.getMc().options.jumpKey.isPressed();
                if (var16_2) ** GOTO lbl27
                if (v6) {
                    v7 = Flight.INSTANCE.getVSpeed();
                } else {
                    v6 = Globals.getMc().options.sneakKey.isPressed();
lbl27:
                    // 2 sources

                    v7 = v6 != false ? -((double)Flight.INSTANCE.getVSpeed()) : 0.0;
                }
                v4.setVelocity(v5, v7, var17_3[1]);
            }
            if (!var16_2) break block6;
            Module.zkmSetFlag(new Module[1]);
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block0: {
            if (this.getMode() != ElytraMode.MATRIX_JUMP || !(receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket)) break block0;
            vec3d = Globals.getPlayer().getVelocity();
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        block0: {
            long l = a ^ 0x6DA9E31A7A90L;
            long l2 = l ^ 0x2650E1C91477L;
            if (this.getMode() != ElytraMode.MATRIX_JUMP || !(sendPacket.getPacket() instanceof PlayerMoveC2SPacket.Full) || vec3d == null) break block0;
            ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
            Vec3d vec3d = Flight.vec3d;
            Intrinsics.checkNotNull(vec3d);
            double d = vec3d.x;
            Vec3d vec3d2 = Flight.vec3d;
            Intrinsics.checkNotNull(vec3d2);
            double d2 = vec3d2.y;
            Vec3d vec3d3 = Flight.vec3d;
            Intrinsics.checkNotNull(vec3d3);
            clientPlayerEntity.setVelocity(d, d2, vec3d3.z);
            this.disable(l2);
            Flight.vec3d = null;
        }
    }

    @Flow
    public final void onMove(@NotNull MoveEvent moveEvent) {
        Intrinsics.checkNotNullParameter(moveEvent, "event");
        if (this.getMode() == ElytraMode.GRIM_GLIDE) {
            double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, 0.3f, false, 1.0f, 0.0f, 0.0f, 16, null);
            moveEvent.cancel();
            moveEvent.setY(-0.03);
            moveEvent.setX(dArray[0]);
            moveEvent.setZ(dArray[1]);
            Globals.getPlayer().setVelocity(moveEvent.getX(), -0.03, moveEvent.getZ());
        }
    }

    @Override
    public void onDisable(long l) {
        Globals.getPlayer().getAbilities().flying = false;
        ElytraMode$WhenMappings.t();
        Globals.getPlayer().getAbilities().setFlySpeed(0.05f);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == ElytraMode.Creative || INSTANCE.getMode() == ElytraMode.Vanilla;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Flight.a = ZkmKeys.a(-7922677117316658856L, 5254213832544096717L, MethodHandles.lookup().lookupClass()).a(118308594946723L);
                        var20 = Flight.a ^ 112611400945011L;
                        Flight.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[15];
                        var16_4 = 0;
                        var15_5 = "|\u0099&\u00a0\u00e0\u00d2n\u00d8\u0013\u00ab\u00b1\u0017\u00d2b\u00a8\u001c\u0010\u00f4\u0081_\u000f\u00ca\u00e8E)\u0018\u0004\u00bb\u00d2R{\u001b\u001b <E\u00bc\u00db\u008bP\u00e9()\u00ad\u00cb\u00c5O7\u00e2\u00cf.\u00a2\u008b\u0081\u00ec\u00d84\u00e4\u001cI/!@\t\u00a6\u008b \u0019\u00b6\u0088>\u00c8\u00ecp\u001b\u009aB\u00c7\u00fd\b\u0087\u00d7\u00ce\u00ad\u00cc\u0085\u00d0\u0006\u0094\u0000[}\u00ef\u00f5\u00b1\u00cb\u0085\u0017\u0014 \u0002m\u00c7\u00a9doE\u0097zo\u008b$\u00b0\u0087b\u00e2e\u00a6\u00f7S\u001d=5\u001b\u00f9\u00a0\u0092\u00e1}\u00abX<\u0010\u00fd^\u0086U\u00ab`\u00f9$\u0017\u00c5]mE\u00e7\u00b0\u0007 H\u00ac\u001eQ\u00ac\u0005\u00d7\u00b3\u0085\u00dc\u00e1\u00daV8yy!gT\u00a5\u00c5i\u00cc\u00f9\u000b\u00fel\n\u0090\u0010b|\u0010`DF\u00ee\u00a6\u008a\u009c\u00cc\u00df\u00bf\u0087\u0007L\u00db\u00be]\u0010\u00a1v%\u00e6\u009d:\u00ff\u00d8\u0093R]\u009f+\u009f\u00de,\u0018` \u00bc\u000e\u00dbdG\u0092\u00066\u00b7\u00f2\u00bd=b_p\u00b0\u000f\u00ee\u00d6\u00f2>\u0013\u0010\u008fu\u00de\u00b8\u000b\u000e\u0015e=\u00caR\u0099j\u0083Q\u00eb0\u001b\u009b[\u007fm\u009el'\u00c5'\u0089<\u00ab\u0081`\u00a6\u00c9Pv\u00c7\u00c6\u009a\"\u00b7lf\u00d6\u00f7\u00c6\u001d\u0006\u0089\u00b0H&\u00e0\u0094\u0094R\u00d4\u00d2\u0080n&z\u009c\u008ag \u009bb\u00f61\u0011f\u0013U\u00e3<\t\u00fa\u00a7d\u00dbg\u00cf\u0085@\u00f3\u00a2\u00e8'\u00de\u00b5X3Ea\u00be9\u00e4";
                        var17_6 = 340;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = Flight.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c0\u0099P\u007f\u0085\u0089/\u00a9\u0094\rj/\u00fb6\u008bn\u0018)dJ2N\u0005\u0001;\u0080\u0091Y\u00bc\u00ede\u009d.%\u0085\t\u0001\u00ac\u0002\u0007\u009d";
                            var17_6 = 41;
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
                            var18_3[var16_4++] = Flight.zkmDecodeUtf8(var19_9).intern();
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
                Flight.b = var18_3;
                Flight.c = new String[15];
                Flight.i = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "g\u00f2\bc\u0087r\u00be+/\u0082\u007f\u00e1D\u00d9\u00aa\u0010A\u001c\u00b2wD`\u0097\u00b7}\u00e8\u0086\u0006\u00db\u00cc\u009a(\u00aeTAf\u0086\u00f0\u00e8T";
                var5_15 = 40;
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
                    var4_14 = "}R: d\u0017%\u0003i;qOp}\u0089Z";
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
        Flight.f = var6_12;
        Flight.h = new Integer[7];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Flight.class, "mode", "getMode()Lsu/catlean/be;", 0)), Reflection.property1(new PropertyReference1Impl(Flight.class, "hSpeed", "getHSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(Flight.class, "vSpeed", "getVSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(Flight.class, "antiKick", "getAntiKick()Z", 0))};
        Flight.delegatedProperties = var30_20;
        Flight.INSTANCE = new Flight();
        Flight.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Flight.INSTANCE, "mode", (Enum)ElytraMode.Vanilla, null, null, 12, null);
        Flight.hSpeed$delegate = SettingFactory.floatSetting$default(Flight.INSTANCE, "horizontal", 1.0f, RangesKt.rangeTo(0.0f, 10.0f), null, null, 24, null);
        Flight.vSpeed$delegate = SettingFactory.floatSetting$default(Flight.INSTANCE, "vertical", 0.78f, RangesKt.rangeTo(0.0f, 5.0f), null, null, 24, null);
        Flight.antiKick$delegate = SettingFactory.booleanSetting$default((SettingContainer)Flight.INSTANCE, "anti-kick", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Flight.vec3d = Vec3d.ZERO;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x62D0;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/u8", exception);
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
            Flight.c[n2] = Flight.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Flight.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5543;
        if (h[n2] == null) {
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
                throw new RuntimeException("su/catlean/u8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Flight.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Flight.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Flight.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Flight.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

