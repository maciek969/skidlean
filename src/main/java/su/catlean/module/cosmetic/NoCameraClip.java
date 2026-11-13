/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.option.Perspective;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.CameraOffsetEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class NoCameraClip
extends Module {
    @NotNull
    public static final NoCameraClip INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting distance$delegate;
    @NotNull
    private static Animation distanceAnimation;
    @NotNull
    private static Perspective lastPerspective;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private NoCameraClip() {
        super("no-camera-clip", Categories.getCosmetic(), null, null);
    }

    private final float getDistance() {
        return ((Number)distance$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final void setDistance(float f) {
        distance$delegate.setValue(this, delegatedProperties[0], Float.valueOf(f));
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        if (lastPerspective != Globals.getMc().options.getPerspective()) {
            distanceAnimation.animateTo(0.0f);
            Perspective perspective = Globals.getMc().options.getPerspective();
            Intrinsics.checkNotNullExpressionValue((Object)perspective, "getCameraType(...)");
            lastPerspective = perspective;
        }
        distanceAnimation.animate(1.0f);
    }

    @Flow
    private final void onCameraOffset(CameraOffsetEvent cameraOffsetEvent) {
        cameraOffsetEvent.setOffset(1.0f + (this.getDistance() - 1.0f) * distanceAnimation.getValue());
        cameraOffsetEvent.cancel();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        NoCameraClip.a = ZkmKeys.a(7605663450691189193L, 8624925237643576773L, MethodHandles.lookup().lookupClass()).a(157427514851799L);
                        var23 = NoCameraClip.a ^ 112367314305513L;
                        NoCameraClip.d = new HashMap<K, V>(13);
                        var14_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var23 >>> 56);
                        for (var15_2 = 1; var15_2 < 8; ++var15_2) {
                            v2 = v2;
                            v2[var15_2] = (byte)(var23 << var15_2 * 8 >>> 56);
                        }
                        var14_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var21_3 = new String[5];
                        var19_4 = 0;
                        var18_5 = "\u001a\u00de\u00dc\u00fb\u0006p\u00f7\u00ad\u00f9\u00aeZ\u00c9<\u0006$\u00af*M\u009b\u008d7\u00bd~\u00ce:Hnh5`\u00f9\u008ee\u00a4\u0096\u00e4\u00f4\u00b1\u000f\u00bd \u00de\u00f5\b\u00a9&R\u00d5*\u00a1\u00b3(\u001e\u001c\u0082\u00df\u00ed\u00c1\u000b=\u00e4P4l0Nbo\u00155\u00acE\u00df L\u00d5\u00c8v\u008c\u00fe\u0081\u00de\u00c6\u008b;\f\u00e9\u0080\u00d3z\u0093\u00df\u0016\u00c5\u00fb\u00b0\u0004\u00a0\u0006O+^T\u009fN\u0095";
                        var20_6 = 106;
                        var17_7 = 40;
                        var16_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var16_8;
                            v4 = var18_5.substring(v3, v3 + var17_7);
                            v5 = -1;
                            break block14;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var21_3[var19_4++] = NoCameraClip.zkmDecodeUtf8(var22_9).intern();
                            if ((var16_8 += var17_7) < var20_6) {
                                var17_7 = var18_5.charAt(var16_8);
                                ** continue;
                            }
                            var18_5 = "\u0095\u00d9\u00cc+CK\u001f\u00ee\u00e1\u00cc#|\u0015\u00de%M\u00b0\u00a5v\u0088\u0007\u00f6S{\u0018\u00dc7\u00a5\u00d9\u00e6,\u009b\u00a1\u00de\u008e\u008e\u000bR{\u00f98Wp\u00f1\u0099\u00d3\u0002\u00c0@";
                            var20_6 = 49;
                            var17_7 = 24;
                            var16_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var16_8;
                                v4 = var18_5.substring(v6, v6 + var17_7);
                                v5 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var21_3[var19_4++] = NoCameraClip.zkmDecodeUtf8(var22_9).intern();
                            if ((var16_8 += var17_7) < var20_6) {
                                var17_7 = var18_5.charAt(var16_8);
                                ** continue;
                            }
                            break block15;
                            break;
                        }
                    }
                    var22_9 = var14_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                NoCameraClip.b = var21_3;
                NoCameraClip.c = new String[5];
                var9_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var23 >>> 56);
                for (var10_11 = 1; var10_11 < 8; ++var10_11) {
                    v9 = v9;
                    v9[var10_11] = (byte)(var23 << var10_11 * 8 >>> 56);
                }
                break block16;
lbl65:
                // 1 sources

                while (true) {
                    continue;
                    break;
                }
            }
            var9_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
            var13_13 = var9_10.doFinal(new byte[]{93, 50, 102, -39, 115, 59, 124, -76});
            ** while (true)
            var7_12 = ((long)var13_13[0] & 255L) << 56 | ((long)var13_13[1] & 255L) << 48 | ((long)var13_13[2] & 255L) << 40 | ((long)var13_13[3] & 255L) << 32 | ((long)var13_13[4] & 255L) << 24 | ((long)var13_13[5] & 255L) << 16 | ((long)var13_13[6] & 255L) << 8 | (long)var13_13[7] & 255L;
            var2_14 = Cipher.getInstance("DES/CBC/NoPadding");
            v10 = SecretKeyFactory.getInstance("DES");
            v11 = new byte[8];
            v12 = v11;
            v11[0] = (byte)(var23 >>> 56);
            for (var3_15 = 1; var3_15 < 8; ++var3_15) {
                v12 = v12;
                v12[var3_15] = (byte)(var23 << var3_15 * 8 >>> 56);
            }
            break block17;
lbl82:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_14.init(2, (Key)v10.generateSecret(new DESKeySpec(v12)), new IvParameterSpec(new byte[8]));
        var6_17 = var2_14.doFinal(new byte[]{-41, 0, 106, 86, -70, 28, 57, 109});
        ** while (true)
        var0_16 = ((long)var6_17[0] & 255L) << 56 | ((long)var6_17[1] & 255L) << 48 | ((long)var6_17[2] & 255L) << 40 | ((long)var6_17[3] & 255L) << 32 | ((long)var6_17[4] & 255L) << 24 | ((long)var6_17[5] & 255L) << 16 | ((long)var6_17[6] & 255L) << 8 | (long)var6_17[7] & 255L;
        var32_18 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoCameraClip.class, "distance", "getDistance()F", 0))};
        NoCameraClip.delegatedProperties = var32_18;
        NoCameraClip.INSTANCE = new NoCameraClip();
        NoCameraClip.distance$delegate = SettingFactory.floatSetting$default(NoCameraClip.INSTANCE, "distance", 3.0f, RangesKt.rangeTo(1.0f, 20.0f), null, null, (int)var7_12, null);
        NoCameraClip.distanceAnimation = new Animation(Easing.OUT_QUINT, var0_16);
        NoCameraClip.lastPerspective = Perspective.FIRST_PERSON;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x30A5;
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
                throw new RuntimeException("su/catlean/kd", exception);
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
            NoCameraClip.c[n2] = NoCameraClip.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoCameraClip.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoCameraClip.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

