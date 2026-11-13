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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.MovementType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.TridentUseEvent;
import su.catlean.api.event.events.player.TridentWeatherEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.TridentBoostMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class TridentBoost
extends Module {
    @NotNull
    public static final TridentBoost INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting factor$delegate;
    @NotNull
    private static final IntSetting cooldown$delegate;
    @NotNull
    private static final BooleanSetting anyWeather$delegate;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private TridentBoost() {
        super("trident-boost", Categories.getRage(), null, null);
    }

    private final TridentBoostMode getMode() {
        return (TridentBoostMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final float getFactor() {
        return ((Number)factor$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    public final int getCooldown$catlean() {
        return ((Number)cooldown$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final boolean getAnyWeather() {
        return (Boolean)anyWeather$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    public final void onTridentUse(@NotNull TridentUseEvent tridentUseEvent) {
        Intrinsics.checkNotNullParameter(tridentUseEvent, "e");
        if (Globals.getPlayer().getItemUseTime() >= this.getCooldown$catlean()) {
            float f = EnchantmentHelper.getTridentSpinAttackStrength(Globals.getPlayer().getActiveItem(), Globals.getPlayer());
            if ((this.getAnyWeather() || Globals.getPlayer().isTouchingWaterOrRain()) && f > 0.0f) {
                float f2 = Globals.getPlayer().getYaw();
                float f3 = Globals.getPlayer().getPitch();
                float f4 = -((float)Math.sin(f2 * ((float)Math.PI / 180))) * (float)Math.cos(f3 * ((float)Math.PI / 180));
                float f5 = -((float)Math.sin(f3 * ((float)Math.PI / 180)));
                float f6 = (float)Math.cos(f2 * ((float)Math.PI / 180)) * (float)Math.cos(f3 * ((float)Math.PI / 180));
                float f7 = (float)Math.sqrt(f4 * f4 + f5 * f5 + f6 * f6);
                float f8 = this.getMode() == TridentBoostMode.Factor ? this.getFactor() * 3.0f * ((1.0f + f) / 4.0f) : this.getFactor();
                Globals.getPlayer().addVelocity(f4 *= f8 / f7, f5 *= f8 / f7, f6 *= f8 / f7);
                Globals.getPlayer().useRiptide(20, 8.0f, Globals.getPlayer().getActiveItem());
                if (Globals.getPlayer().isOnGround()) {
                    Globals.getPlayer().move(MovementType.SELF, new Vec3d(0.0, 1.1999999284744263, 0.0));
                }
                RegistryEntry<SoundEvent> registryEntry = EnchantmentHelper.getEffect(Globals.getPlayer().getActiveItem(), EnchantmentEffectComponentTypes.TRIDENT_SOUND).orElse(SoundEvents.ITEM_TRIDENT_THROW);
                Globals.getWorld().playSoundFromEntity(null, Globals.getPlayer(), registryEntry.comp_349(), SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
        }
        tridentUseEvent.cancel();
    }

    @Flow
    public final void onTridentWeather(@NotNull TridentWeatherEvent tridentWeatherEvent) {
        Intrinsics.checkNotNullParameter(tridentWeatherEvent, "e");
        if (this.getAnyWeather()) {
            tridentWeatherEvent.cancel();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        TridentBoost.b = ZkmKeys.a(3093449907022646038L, -9088610704999788548L, MethodHandles.lookup().lookupClass()).a(113229408984033L);
                        var20 = TridentBoost.b ^ 40565088808450L;
                        TridentBoost.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[13];
                        var16_4 = 0;
                        var15_5 = "\\\u00af\u00e7(\u00df\u0093\u000b\u001b\u007f\u0015\u008f\u00f3\u00b7\u00bb.I \u0083\u00e0\u00e4I\u0098\u00b1h\u00a3\u00a8\u00ad\u00ecHi\u00df\u00cb\u0086\u00b3\u00d5\u0085\u00b5^\u007f2\u00eb\u00a5\u00ae\u000e\u00d6\u00c7Pn\u0088(\u00b0\u0090\u00b9\u0089\u009c\u00b0\u00c0\u00e2\u001f\u0014\u00bf\u0097\u00f9Si J\u000f\u00ce\u001e\u00c6\u00eeA\u008c\u00c5*bH\u009f\u00a9QA\u009ei87o=^?\u0010\u0082FQ\u00cbv\u00c12\u00d2\u00b1\u00e0Z\u00c1-%\u00de\u0018 \u00b3b\\\u00f2\u0085\u00c9{\u0089\u0017\u00a7\u00ad3\u00ae\u00cd\u00ed\u00c6\u00cd\u009f\u00f8\u00d5\u00f3<\u00a9\u00f8(\u009b(\u00b8\u008e\u00ae\u00d3.\u0010\u0093Z\u009d\u009bw\u00e7\u00e7\u00a8\u00c9\u00cc\u00d7\u00e0%@\u0006D(\u00a4\u00ce~BOj\u008fQ\u00d5\u00d9EW\u00ae\b\u00a4u\u00e0\n\u00a88\u00c2\u008f:\u00b53\u00b7\u0019\u00a5\u0093\u0013RI(\u00da.\u00ea\u00ca\u00f9\u008b\u0093 \u00c6-v\u00dbl\\>\u00824\u00dd\u00cc\u0003|(s\u00ff\u00d7\u00f1\u0004\u00ee\u009b_\u008f\u0080\u009fB\u00e7/\u00ea2#^\u0018\u00ff\u00e2h\u00e5\u00a1\u0000\u0097\u007f\u0086v\"[\u00e8Su\u008f\u001f\u0092e\u0083K\t\u00ee%\u0010\u00db\u0016\u00cf\u00a2\u008b\u00c1d\u001e_\u008b'j\u00c3\u00a4\u00d0j \u00f9<bs#\u00e9n\u00ba\u00898\u00a8@\u0095\u00cb\u00da\u00c7X\u0099\u00d0\u00da\t\u00fa\u00ef[\u00185D\u0088\u00d1\u00ae\u00de\u0007";
                        var17_6 = 306;
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
                            var18_3[var16_4++] = TridentBoost.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "i\u009e\u00f5\u00db\nT\u00f6\u007f\u00117\u00e9T\u00d9\u00a5\u00b6\u00950\r\u001d\u00cfS4\u00c6\u0091\u00f1NL\u00d5\u0016\u00dc\u00bb\u00e8:\u001e\u00dc\u00c6R\u00cf\u0088\u0096\u00fc\u00c6\u008aG\u0001\u0084n\u00b3\u00fe'\u00e3\u00b8/\u0097h\u0005z\u009d\u000e\u00ea\u00b3}\u0016\u00b3V";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = TridentBoost.zkmDecodeUtf8(var19_9).intern();
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
                TridentBoost.c = var18_3;
                TridentBoost.d = new String[13];
                TridentBoost.i = new HashMap<K, V>(13);
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
                var4_14 = "\u0015\n\u00b4'\u00f7\u00f8AD\u00c4\u00b9\u00f8\b\u00f7\u00e1I\u00ee\u00fd\u00c0}\u0090\u00b8\u00f6\u009a\t\u00c3\u0081\u00c8\u0014J\u00d8\u0090J\u00c6\u00f8\u00f6l\u0004\u00a9\u00f8\u0085";
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
                    var4_14 = "\u00a7\u008c\u00b7'\u00c0\u00e04w\u00ccp\u0019\u0084F=#\u009b";
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
        TridentBoost.g = var6_12;
        TridentBoost.h = new Integer[7];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(TridentBoost.class, "mode", "getMode()Lsu/catlean/rz;", 0)), Reflection.property1(new PropertyReference1Impl(TridentBoost.class, "factor", "getFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(TridentBoost.class, "cooldown", "getCooldown$catlean()I", 0)), Reflection.property1(new PropertyReference1Impl(TridentBoost.class, "anyWeather", "getAnyWeather()Z", 0))};
        TridentBoost.delegatedProperties = var32_20;
        TridentBoost.INSTANCE = new TridentBoost();
        TridentBoost.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)TridentBoost.INSTANCE, "mode", (Enum)TridentBoostMode.Motion, null, null, 12, null);
        TridentBoost.factor$delegate = SettingFactory.floatSetting$default(TridentBoost.INSTANCE, "factor", 1.0f, RangesKt.rangeTo(0.1f, 20.0f), null, null, 24, null);
        TridentBoost.cooldown$delegate = SettingFactory.intSetting$default(TridentBoost.INSTANCE, "delay", 10, new IntRange(0, 20), null, null, 24, null);
        TridentBoost.anyWeather$delegate = SettingFactory.booleanSetting$default((SettingContainer)TridentBoost.INSTANCE, "rain-spoof", true, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1AC6;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/ui", exception);
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
            TridentBoost.d[n2] = TridentBoost.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TridentBoost.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ui" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5494;
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
                throw new RuntimeException("su/catlean/ui", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TridentBoost.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TridentBoost.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ui" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TridentBoost.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TridentBoost.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

