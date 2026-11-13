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
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.LevitationControl$WhenMappings;
import su.catlean.module.rage.LevitationControlMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class LevitationControl
extends Module {
    @NotNull
    public static final LevitationControl INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private LevitationControl() {
        super("levitation-control", Categories.getRage(), null, null);
    }

    private final LevitationControlMode getMode() {
        return (LevitationControlMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    @Flow
    public final void onMove(@NotNull MoveEvent moveEvent) {
        Intrinsics.checkNotNullParameter(moveEvent, "e");
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.LEVITATION)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.LEVITATION);
            Intrinsics.checkNotNull(statusEffectInstance);
            int n = statusEffectInstance.getAmplifier();
            switch (LevitationControl$WhenMappings.x[this.getMode().ordinal()]) {
                case 1: {
                    if (Globals.getMc().options.jumpKey.isPressed()) {
                        moveEvent.setY(moveEvent.getY() + (0.05 * (double)(n + 1) - moveEvent.getY()) * 0.2);
                    } else if (Globals.getMc().options.sneakKey.isPressed()) {
                        moveEvent.setY(-0.08);
                        moveEvent.setX(0.0);
                        moveEvent.setZ(0.0);
                    } else if (MovementUtil.INSTANCE.isMoving() || MovementUtil.INSTANCE.getHorizontalSpeed() > 0.0f) {
                        moveEvent.setY(0.004);
                    } else {
                        moveEvent.setY(0.0);
                    }
                    moveEvent.cancel();
                    break;
                }
                case 2: {
                    if (Globals.getMc().options.jumpKey.isPressed()) {
                        moveEvent.setY(0.1);
                    } else if (Globals.getMc().options.sneakKey.isPressed()) {
                        moveEvent.setY(-0.1);
                    } else {
                        moveEvent.setY(0.0);
                    }
                    moveEvent.cancel();
                    break;
                }
                case 3: {
                    Globals.getPlayer().removeStatusEffect(StatusEffects.LEVITATION);
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    LevitationControl.a = ZkmKeys.a(-1713983999391940216L, -9117021530851835299L, MethodHandles.lookup().lookupClass()).a(253182023789073L);
                    var16 = LevitationControl.a ^ 49249352230711L;
                    LevitationControl.d = new HashMap<K, V>(13);
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
                    var14_3 = new String[4];
                    var12_4 = 0;
                    var11_5 = "u\u00bbZ\u00c2)y\r\b\u00d9\u0019\u001ff\u00d3M\u00a0^8\u008d\u00aa\u00c0\u00d0\u00cf\u0099\u00987BT\u00dc\u00ed\u00ac\b?3t\"\u00da\u00ba\u00b2\u0088\u00ce\u00ddy\u00b7\u00e6Nt\u0087\u0083k}f`\u0084\u00a7\u008d=J?Q\u000e\u00aa\u00a3\u00f7\u00a4\u0097\u008b\u0006%~\u000f\u00c8I\u0083";
                    var13_6 = 73;
                    var10_7 = 16;
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
                        var14_3[var12_4++] = LevitationControl.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\n\u00adMC\u009e\u008a\u00ebP\u00c4j\u0086\u00cd\u0086\u00be\u00c8b(\u00c5\u00bb`V>\u0011A5\u009e}'R\u008b\u00e9\u0085\u0004\u0091F7\u00f7\u0090~#*\u0085\u0013\u0017\u00b1\u00ba\u00bdm\u0003\u0097\u008em=\u00e2\u00f5TF";
                        var13_6 = 57;
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
                        var14_3[var12_4++] = LevitationControl.zkmDecodeUtf8(var15_9).intern();
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
            LevitationControl.b = var14_3;
            LevitationControl.c = new String[4];
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
        var6_13 = var2_10.doFinal(new byte[]{77, -105, -100, -46, -19, -3, -105, 126});
        ** while (true)
        var0_12 = ((long)var6_13[0] & 255L) << 56 | ((long)var6_13[1] & 255L) << 48 | ((long)var6_13[2] & 255L) << 40 | ((long)var6_13[3] & 255L) << 32 | ((long)var6_13[4] & 255L) << 24 | ((long)var6_13[5] & 255L) << 16 | ((long)var6_13[6] & 255L) << 8 | (long)var6_13[7] & 255L;
        var22_14 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(LevitationControl.class, "mode", "getMode()Lsu/catlean/ni;", 0))};
        LevitationControl.delegatedProperties = var22_14;
        LevitationControl.INSTANCE = new LevitationControl();
        LevitationControl.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)LevitationControl.INSTANCE, "mode", (Enum)LevitationControlMode.NCP, null, null, (int)var0_12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4AAE;
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
                throw new RuntimeException("su/catlean/ub", exception);
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
            LevitationControl.c[n2] = LevitationControl.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LevitationControl.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ub" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LevitationControl.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

