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
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.ReverseStepMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.PingPredictor;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class ReverseStep
extends Module {
    @NotNull
    public static final ReverseStep INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting timer$delegate;
    @NotNull
    private static final FloatSetting motion$delegate;
    @NotNull
    private static final BooleanSetting pauseIfShift$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private ReverseStep() {
        super("reverse-step", Categories.getRage(), null, null);
    }

    private final ReverseStepMode getMode() {
        return (ReverseStepMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final float getTimer() {
        return ((Number)timer$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final float getMotion() {
        return ((Number)motion$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final boolean getPauseIfShift() {
        return (Boolean)pauseIfShift$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    public final void onPreSync(@NotNull PreSyncEvent preSyncEvent) {
        block2: {
            long l = a ^ 0x539A4180E64DL;
            long l2 = l ^ 0xB5E718730DEL;
            int n = (int)(l2 >>> 32);
            int n2 = (int)(l2 << 32 >>> 56);
            int n3 = (int)(l2 << 40 >>> 40);
            Intrinsics.checkNotNullParameter(preSyncEvent, "e");
            if (this.getPauseIfShift() && Globals.getMc().options.sneakKey.isPressed() || Globals.getPlayer().isTouchingWater() || Globals.getPlayer().isSubmergedInWater() || Globals.getPlayer().isInLava() || Globals.getPlayer().isGliding() || Globals.getPlayer().getAbilities().flying || PingPredictor.INSTANCE.isInHole(Globals.getPlayer(), n, (byte)n2, n3)) {
                return;
            }
            if (!(this.getMode() != ReverseStepMode.Timer || Globals.getMc().options.jumpKey.isPressed() || Globals.getPlayer().isOnGround() || Globals.getPlayer().getVelocity().getY() >= -0.1)) {
                TimerManager.INSTANCE.x(this.getTimer());
            }
            if (!Globals.getPlayer().isOnGround() || this.getMode() != ReverseStepMode.Motion) break block2;
            Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().add(0.0, -((double)this.getMotion()), 0.0));
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == ReverseStepMode.Timer;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == ReverseStepMode.Motion;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ReverseStep.a = ZkmKeys.a(4845321712088432099L, -2322460896685708301L, MethodHandles.lookup().lookupClass()).a(171049801113901L);
                        var20 = ReverseStep.a ^ 13310122249838L;
                        ReverseStep.d = new HashMap<K, V>(13);
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
                        var15_5 = "\u00f7\u000bs\u009axb\u009a\u0093\u00f6B\t\u00b2\u00be\u0081h\u0098\u00dbuk\u00bahD\u0081\u00a3\u0018vQ:\u00d3\u00f8\u008c\u00bb\u00ceo\u00f8O\u0092\u00d4&\u0086\u00db\u00ae;\u00f1\\@\u00dc\u00e1i\u0018\u00f0\u00efA\u00cby\u0015\"\u00d2\u00c7\u00cd\u0011\u0096D>\u0090\u00cbY\u0096 \u001f\u0089\u00e7vL \u00d9\u009f\u0084\u0007\u0096mE\u00a7\u00e8\u00c4\u0092n\u00c13\u00cf\u0006\u009e\u00ba\u00e6\u008e\u0000~.\u009b\u00cb5\u00ae\u00b2JHQn\u0010;\u00e4\"\u00beI|\nDi\u00c7\u00b4\u009b\u009d\u0015\u00e0\u00a5\u001090`\u0000\u0080\u0090e\u00a8+k\u00ac\u00cf\u0002\u008f\r\u00fd\u0010\u00d8\u00d0\u008eq\u00a3\u00c72UK(\u00ef*1\u008b\u00e3\u001f\u0010V\u00e3\u00846^P\u00de\u00ce\u00d2\u00acX!\u00b0\u00b6\u0006\u00ee0\u00e3\u00f8W(\u00f8\u00d42\u00c2\u0089\u00af\u00013\u0019\u00a2U\u0089\b\u00cc\u00c6\u00854`\u00f4\u0016\u00cd\u0010\u00ffC\u00b1\u00a9\u00fa\u00df_H\u00f3H\u009a\u00c0\u001d\u0081j\u0095\u009f\u00bd\u00a4\u0091\u008a\u00f7 F\u00b2[\n\u0083\u00a6\u00cfa\u009b\u00a7\u00c8\u00d3\u00fa\u00d0\u00be\u00a3\u0005\u0002P\u00ae\u00c1\u00df\u00b88`H\nb\u00be\u00c4:\u00f3\u0010z)\\g7\u0081\u00fc\u0007\u0096gj\u00e9G;\u00c3\u00fb";
                        var17_6 = 274;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = ReverseStep.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c1\u00ad\u00fd\u00da\u00eeHj\u00ca!\u0013]\u00da\n\u0018\u00c8\u00ec{\u0091\u000e\u009b\u001c\u0087z\u00cfJ\u00b2\u00f8\u00f2~H\u0097\u00bfI\u00d9\u00eeO\u00dcCFL\u0018\u0019\u00ac\\\u00c9\u008a\u0093\u00d5p\u00f4v#\u00af\u00bb\u0004\u00fe\u00f0\u00b9\u00de\u00af\\\u00a0\u00f6\u0012\u008e";
                            var17_6 = 65;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = ReverseStep.zkmDecodeUtf8(var19_9).intern();
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
                ReverseStep.b = var18_3;
                ReverseStep.c = new String[13];
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
                var0_12 = new long[4];
                var4_13 = 0;
                var5_14 = "*\u0016\u0095L\u0091LK\u00f5\u000b\u00f8\u0012\u001b\u00f0o\u00c7_";
                var6_15 = 16;
                var3_16 = 0;
                while (true) {
                    var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                    v10 = var0_12;
                    v11 = var4_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl77:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    var5_14 = "t\u00d6X\u0004Vc\u00da~\u00d3\u00e0\u0004\u0013\u0080\u00f7\u00bb\u00b6";
                    var6_15 = 16;
                    var3_16 = 0;
                    while (true) {
                        var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                        v10 = var0_12;
                        v11 = var4_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl90:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl103:
                // 1 sources

                ** continue;
            }
        }
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ReverseStep.class, "mode", "getMode()Lsu/catlean/ax;", 0)), Reflection.property1(new PropertyReference1Impl(ReverseStep.class, "timer", "getTimer()F", 0)), Reflection.property1(new PropertyReference1Impl(ReverseStep.class, "motion", "getMotion()F", 0)), Reflection.property1(new PropertyReference1Impl(ReverseStep.class, "pauseIfShift", "getPauseIfShift()Z", 0))};
        ReverseStep.delegatedProperties = var30_20;
        ReverseStep.INSTANCE = new ReverseStep();
        ReverseStep.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)ReverseStep.INSTANCE, "mode", (Enum)ReverseStepMode.Motion, null, null, (int)var0_12[3], null);
        ReverseStep.timer$delegate = SettingFactory.floatSetting$default(ReverseStep.INSTANCE, "timer", 3.0f, RangesKt.rangeTo(1.0f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), (int)var0_12[0], null);
        ReverseStep.motion$delegate = SettingFactory.floatSetting$default(ReverseStep.INSTANCE, "motion", 1.0f, RangesKt.rangeTo(0.1f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), (int)var0_12[1], null);
        ReverseStep.pauseIfShift$delegate = SettingFactory.booleanSetting$default((SettingContainer)ReverseStep.INSTANCE, "pause-if-shift", false, null, null, (int)var0_12[2], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5FF5;
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
                throw new RuntimeException("su/catlean/ud", exception);
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
            ReverseStep.c[n2] = ReverseStep.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ReverseStep.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ud" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ReverseStep.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

