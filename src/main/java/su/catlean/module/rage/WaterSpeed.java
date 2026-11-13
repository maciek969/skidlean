/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.SprintEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.WaterSpeedMode;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class WaterSpeed
extends Module {
    @NotNull
    public static final WaterSpeed INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    private static final long a;
    private static final String[] c;
    private static final String[] e;
    private static final Map f;

    private WaterSpeed() {
        super("water-speed", Categories.getRage(), null, null);
    }

    private final WaterSpeedMode getMode() {
        return (WaterSpeedMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    @Override
    public void onDisable(long l) {
        block0: {
            if (this.getMode() != WaterSpeedMode.DolphinGrace) break block0;
            Globals.getPlayer().removeStatusEffect(StatusEffects.DOLPHINS_GRACE);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (this.getMode() == WaterSpeedMode.DolphinGrace) {
            boolean bl = Globals.getPlayer().isSwimming() ? Globals.getPlayer().addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 2, 2)) : Globals.getPlayer().removeStatusEffect(StatusEffects.DOLPHINS_GRACE);
        }
    }

    @Flow
    private final void onMove(MoveEvent moveEvent) {
        block0: {
            long l = a ^ 0x70604A3CC80CL;
            long l2 = l ^ 0x2F52CBAF6351L;
            int n = (int)(l2 >>> 32);
            int n2 = (int)(l2 << 32 >>> 48);
            if (this.getMode() != WaterSpeedMode.Vanilla || !Globals.getPlayer().isSwimming()) break block0;
            double d = this.getSpeed();
            MovementUtil.INSTANCE.setSpeed(n, (short)n2, moveEvent, d);
            moveEvent.cancel();
        }
    }

    @Flow
    private final void onSprint(SprintEvent sprintEvent) {
        block0: {
            block1: {
                if (Globals.getMc().player == null || Globals.getMc().world == null || !this.getEnabled() || this.getMode() != WaterSpeedMode.Legit) break block0;
                if (Globals.getPlayer().isTouchingWater()) break block1;
                Entity entity = Globals.getPlayer();
                if (!(Globals.getWorld().getBlockState(BlockPos.ofFloored(this.getPos(entity).add(0.0, -0.5, 0.0))).getBlock() instanceof FluidBlock)) break block0;
            }
            sprintEvent.setSprinting(true);
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
                        WaterSpeed.a = ZkmKeys.a(-163482282287818356L, 3632263032565008146L, MethodHandles.lookup().lookupClass()).a(96955647302379L);
                        var20 = WaterSpeed.a ^ 118322934818787L;
                        WaterSpeed.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[7];
                        var16_4 = 0;
                        var15_5 = "\u001c'R!\t4|\u00ca\u00c2\u00a5M\u00c1\u00c0\u00cf\u00ce\u00fc\u0018\u0012\u009c\u00ee\u00b8\u00b5[V\u0086\u00b3\u00e5\u00b6\u00d9|\u00a2\u000f7/\u009b\u0019+\u00f9X\u00ae\u00b3\u0010\u0098K$a=@ R}\u00a6m=\u00f8\u00e7\u009b\u0002\u0010k\u00ae\u000e|}\u00aa\u000b\u00bfO\u000b\u00c4@\u0089\u00b8~\u00a5\u0018\u00d0Lpc)n`\u0093\u00f0\u00a7\u00b0\u0092\n#\u00f8*(\u00e8\nB\u00bd\u00fe\u00bf\u0095";
                        var17_6 = 100;
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
                            var18_3[var16_4++] = WaterSpeed.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\"\u00a2z\u00c0\u00a6I\u00e7\u00cbf\u00db\u000f3\r\u00db\nS8s\u0013C\u00a8A\u00980\u0011\u00ff:s\u00c3\u00f5|\u00fb\u007f\u00b7\u00ea\u0011\u00d3\u0092)R\u00d8\u00dd\u0091\u0001\u00c4e\u00de\\r\u0002K\u00ebV|\u00c7\f\u000b\u00aa/\u001e\u00055\u00f5\u001f\u00952\u00eb\u00c1#\rl\u009e\u00fe";
                            var17_6 = 73;
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
                            var18_3[var16_4++] = WaterSpeed.zkmDecodeUtf8(var19_9).intern();
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
                WaterSpeed.c = var18_3;
                WaterSpeed.e = new String[7];
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
                var5_14 = "\\\u00be\u00bc\u00ad\u007fe\u00e3~\u0016\u00fc\f\u00ce\u00aa\u0094\u000e\u00b5";
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
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(WaterSpeed.class, "mode", "getMode()Lsu/catlean/i5;", 0)), Reflection.property1(new PropertyReference1Impl(WaterSpeed.class, "speed", "getSpeed()F", 0))};
        WaterSpeed.delegatedProperties = var29_20;
        WaterSpeed.INSTANCE = new WaterSpeed();
        WaterSpeed.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)WaterSpeed.INSTANCE, "mode", (Enum)WaterSpeedMode.DolphinGrace, null, null, (int)var0_12[1], null);
        WaterSpeed.speed$delegate = SettingFactory.floatSetting$default(WaterSpeed.INSTANCE, "speed", 2.0f, RangesKt.rangeTo(1.0f, 10.0f), null, null, (int)var0_12[0], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x239E;
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
                throw new RuntimeException("su/catlean/uk", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            WaterSpeed.e[n2] = WaterSpeed.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WaterSpeed.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WaterSpeed.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

