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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CobwebBlock;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.CobWebEvent;
import su.catlean.api.event.events.player.CollisionEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.AntiWebMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PingPredictor;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class AntiWeb
extends Module {
    @NotNull
    public static final AntiWeb INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting timer$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final BooleanSetting grim$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map i;

    private AntiWeb() {
        super("anti-web", Categories.getRage(), null, null);
    }

    private final AntiWebMode getMode() {
        return (AntiWebMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setMode(AntiWebMode antiWebMode) {
        mode$delegate.setValue(this, delegatedProperties[0], (Object)antiWebMode);
    }

    private final float getTimer() {
        return ((Number)timer$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final void setTimer(float f) {
        timer$delegate.setValue(this, delegatedProperties[1], Float.valueOf(f));
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final void setSpeed(float f) {
        speed$delegate.setValue(this, delegatedProperties[2], Float.valueOf(f));
    }

    private final boolean getGrim() {
        return (Boolean)grim$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setGrim(boolean bl) {
        grim$delegate.setValue(this, delegatedProperties[3], bl);
    }

    @Flow
    public final void onPlayerUpdate(@Nullable PlayerUpdateEvent playerUpdateEvent) {
        block5: {
            long l = a ^ 0x5CCCF220C239L;
            long l2 = l ^ 0x4E4C0D33FFD8L;
            int n = (int)(l2 >>> 32);
            int n2 = (int)(l2 << 32 >>> 56);
            int n3 = (int)(l2 << 40 >>> 40);
            if (!PingPredictor.INSTANCE.isInHole(Globals.getPlayer(), n, (byte)n2, n3)) break block5;
            if (this.getMode() == AntiWebMode.TIMER) {
                ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                Intrinsics.checkNotNull(clientPlayerEntity);
                if (!clientPlayerEntity.isOnGround()) {
                    TimerManager.INSTANCE.x(this.getTimer());
                }
            }
            if (this.getMode() == AntiWebMode.FLY) {
                double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, this.getSpeed(), false, 0.0f, 0.0f, 0.0f, 30, null);
                Globals.getPlayer().setVelocity(dArray[0], 0.0, dArray[1]);
                if (Globals.getMc().options.jumpKey.isPressed()) {
                    Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().add(0.0, this.getSpeed(), 0.0));
                }
                if (Globals.getMc().options.sneakKey.isPressed()) {
                    Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().add(0.0, -((double)this.getSpeed()), 0.0));
                }
            }
        }
    }

    @Flow
    public final void onCollision(@NotNull CollisionEvent collisionEvent) {
        block0: {
            Intrinsics.checkNotNullParameter(collisionEvent, "e");
            if (!(collisionEvent.getState().getBlock() instanceof CobwebBlock) || this.getMode() != AntiWebMode.SOLID) break block0;
            BlockState blockState = Blocks.DIRT.getDefaultState();
            Intrinsics.checkNotNullExpressionValue(blockState, "defaultBlockState(...)");
            collisionEvent.setState(blockState);
        }
    }

    @Flow
    public final void onCobWeb(@NotNull CobWebEvent cobWebEvent) {
        block1: {
            Intrinsics.checkNotNullParameter(cobWebEvent, "e");
            if (this.getMode() != AntiWebMode.IGNORE) break block1;
            cobWebEvent.setCancelled(true);
            if (this.getGrim()) {
                PacketUtil.sendSequencedPacket(arg_0 -> AntiWeb.onCobWeb$lambda$0(cobWebEvent, arg_0));
            }
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == AntiWebMode.TIMER;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == AntiWebMode.FLY;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == AntiWebMode.IGNORE;
    }

    private static final Packet onCobWeb$lambda$0(CobWebEvent cobWebEvent, int n) {
        return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, cobWebEvent.getPos(), Direction.UP, n);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AntiWeb.a = ZkmKeys.a(-9006128379553538578L, 1633475887594468085L, MethodHandles.lookup().lookupClass()).a(11812280350722L);
                        var20 = AntiWeb.a ^ 26087595751378L;
                        AntiWeb.d = new HashMap<K, V>(13);
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
                        var15_5 = "Q\u00ec\u001f&\u00b7\u00ac\u0095\u00c5\u0001\u00b0\u009e\u00ae\u00b72\u001b\u001f,v\u0016\u00a6pb\u00eb\u0080u:&\u00d8\u0098\t\u00b3\u0097\u0010\u001eI\u00f7me\u000e\u0098i\u00bc()]\u0084\u00e1\u00d6h\u0018;\u00c9\u00dd\u00ecL\u000b\fK\u00aan\"\u0093\u00da\u00e3_#\u00b61\u0084\u00ac\u00b2v\u00a9\u00af\u0010\u001a\u00c3u\u0088\u00a2\u00ddR\u00c7\u009f\u0003\u00f3C\u0086)\u009c\u008e\u0010\u00a2Q\u00a7u\u00b2j\u00dc\u0097\u0007&q\u00bc\u0099\u00b1\u00a0\u00e8\u0010,\u0089\u00fa__m\u00fe\u00a3\u008a\u008c\n\u00f7%7W\u00f7\u0010J\u00a4\u00c1\u00bf#\u0016\t\u00a1\u00a7Zp\u00a0\u00d77'\u00e8\u0010\tv\u0012\u00e6\u00ff\u0088j^A\u00ff\u00cd\u00d4\u00f6G\u00a9\u00b8\u0010!\u00a5]\u00d0\u00b7c\u0082q2\u00db\u0005\r6\b\u0005T\u0010\u00a0\u00c3\u00e2\u00db\u0097\u00f24\u0094#\u00bdG\u008b\u0010\u0098\u0087\u00f30\"4\u008d\u0013\u00fb\u00e6\u00e9M\u00a0\u00fe\u00ce\u0097\u0090\b\u0086\u00daF\u00d4D\u00dd\u00ad\u00ce\u00a9E\u009b\u0014\u00b0Hq\u001f\u00cdZ\u00e3\u009e\u0015\u00a5\u00ad\u00e2\u0081\u00e0\u0003f\u001a\u0082!6M/\u00186\u00ec\u00bd~\u00b2\u00d0\u0087\u00ed\\H\u00c9\r\u00fe7\u00d3\u00e0\u00b7\u00ac\u00cd\u00dc\u00e3i\u001cx";
                        var17_6 = 267;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = AntiWeb.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "Y\u00bd\u00f5\u00e9C\u00ad>\u0089\u00d9\u00b1\r&\u0082\u00eb49\u00b6@\u00c1\u00ec<\u0086]7\u0093\u00e4\u0087\u00f7\u0012\u00c0\u00c1/0,o\u00e2\u00c7\u00a5\u0094{\u00e9\u00b0\u0081\u008d \u00a0\u00f1\by\u00eag\u000bf\u00dc\u0005:Q\u00d6\u00a3\u00df\u00f8\u009a!\u0002\u0012\u008d\u00b0\u0084\u00c9m\u0019\u00b7s\u00ab!h$\u001a\u0096\u00ed2";
                            var17_6 = 81;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = AntiWeb.zkmDecodeUtf8(var19_9).intern();
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
                AntiWeb.b = var18_3;
                AntiWeb.c = new String[14];
                AntiWeb.i = new HashMap<K, V>(13);
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
                var4_14 = "b\n\u001d\u00c2\u0099\u00ed}F\u00d9\u00cc\u00e0<p\u00a6\u0096\u00f5";
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
                    var4_14 = "<\u00ec\u0084\u0003\u00e2\u00bf\b;,\u00d0\u007fr\u0095~\u00d5\u001f";
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
        AntiWeb.e = var6_12;
        AntiWeb.f = new Integer[4];
        var30_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiWeb.class, "mode", "getMode()Lsu/catlean/xm;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiWeb.class, "timer", "getTimer()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiWeb.class, "speed", "getSpeed()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiWeb.class, "grim", "getGrim()Z", 0))};
        AntiWeb.delegatedProperties = var30_20;
        AntiWeb.INSTANCE = new AntiWeb();
        AntiWeb.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)AntiWeb.INSTANCE, "mode", (Enum)AntiWebMode.SOLID, null, null, 12, null);
        AntiWeb.timer$delegate = SettingFactory.floatSetting$default(AntiWeb.INSTANCE, "timer", 20.0f, RangesKt.rangeTo(1.0f, 50.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        AntiWeb.speed$delegate = SettingFactory.floatSetting$default(AntiWeb.INSTANCE, "speed", 0.3f, RangesKt.rangeTo(0.0f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        AntiWeb.grim$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiWeb.INSTANCE, "grim", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1230;
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
                throw new RuntimeException("su/catlean/uq", exception);
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
            AntiWeb.c[n2] = AntiWeb.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AntiWeb.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x55B7;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/uq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AntiWeb.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AntiWeb.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/uq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AntiWeb.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AntiWeb.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

