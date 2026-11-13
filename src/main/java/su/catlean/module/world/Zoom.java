/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

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
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.BasicProjectionMatrixEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;

public final class Zoom
extends Module {
    @NotNull
    public static final Zoom INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BindSetting teleBind$delegate;
    @NotNull
    private static final FloatSetting teleScale$delegate;
    @NotNull
    private static final BindSetting wideBind$delegate;
    @NotNull
    private static final FloatSetting wideScale$delegate;
    @NotNull
    private static final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private Zoom() {
        super("zoom", Categories.getWorld(), null, null);
    }

    private final Bind getTeleBind() {
        return (Bind)teleBind$delegate.getValue(this, delegatedProperties[0]);
    }

    private final float getTeleScale() {
        return ((Number)teleScale$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final Bind getWideBind() {
        return (Bind)wideBind$delegate.getValue(this, delegatedProperties[2]);
    }

    private final float getWideScale() {
        return ((Number)wideScale$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    @Flow
    private final void onBasicProjectionMatrix(BasicProjectionMatrixEvent basicProjectionMatrixEvent) {
        int n;
        char c;
        int n2;
        char c2;
        long l = a ^ 0x64F01B5232D8L;
        int n3 = (int)((l ^ 0x6B1957594DCBL) << 48 >>> 48);
        char c3 = (char)n3;
        int n4 = this.getTeleBind().getKey();
        animation.animate(KeyboardUtil.isKeyPressed(c3, n4) ? this.getTeleScale() : (KeyboardUtil.isKeyPressed(c2 = (char)n3, n2 = this.getWideBind().getKey()) ? this.getWideScale() : 1.0f));
        int n5 = this.getWideBind().getKey();
        char c4 = (char)n3;
        if (KeyboardUtil.isKeyPressed(c4, n5) || KeyboardUtil.isKeyPressed(c = (char)n3, n = this.getTeleBind().getKey()) || !(animation.getValue() == 1.0f)) {
            basicProjectionMatrixEvent.setFov(basicProjectionMatrixEvent.getFov() / animation.getValue());
            basicProjectionMatrixEvent.setRatio((float)Globals.getMc().getWindow().getFramebufferWidth() / (float)Globals.getMc().getWindow().getFramebufferHeight());
            basicProjectionMatrixEvent.cancel();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            Zoom.a = ZkmKeys.a(8312389092053637099L, 3566925015409492064L, MethodHandles.lookup().lookupClass()).a(124271329227642L);
                            var27 = Zoom.a ^ 75701708780262L;
                            Zoom.d = new HashMap<K, V>(13);
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var27 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var27 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[13];
                            var23_4 = 0;
                            var22_5 = "\u00b5n\u00d3\u00a2(\u00f2i\u00f5\u00ba\u00ed\u00ec\u00ba*\u00ea\u00bf\u00e9i{a\u007f\u00d4\u0002S\u00b9\u00b9[W\u0094\u00be\u00bd-\\\u0010,\u00bd\u00cd\u00b3\u0004\u00cf\u008fh#\u00d3\u009b\u008d\u00c2}\u00db\u00df\u0018\u00bf\u00b1\b\u00906'6\u00fe_xS\u00d0\u00f4&Zu\u00c7\u00b0\u0088\u00ba\u00c3G\rZ \u00b6\u00d9\u000f\u00d4\u001f\u00b1\u00ce\u00e2qH\u00bb\u00cc=6\u00eb\f>`\u000b4\u008a\u00d7\u00ba\u00b7\u00aa6\u00b8!Mb\u00e2\u0087\u0010\u00f5\u0014\u00b0\u00d8\u00f2\u00ba\u00b8e/3:\u00ba\u0092\u0080\u00d8\u00c8 {\u00f0\u008b\u00afz\u0098\u001f\u008f\u009b;\u00d7\u00b6\u00f8j\u00e7\u00d9\u00c0\\\u0090eW\u0088\u0087\u00b8\u00aaT\u00cd\u00a9(\u00af5U Y\"\u00f4\u00b7{\u00bf\u00f8-l\u0081\u00199\u001d\u00db\u00c56\u00c4\u00b4\u00fb\u00cd\u00e3j&\u00af>\u00cd\u00db\u0018\u008f\u00e5<k\u0018\u00b4\u00bb\u001eU\u0013\u00cf\u00db\u0007\u00bd)\u0000\u00e1S#\r\u009d~\u0089@\u00ef\u000e\u00b9\u00b5\u00e2\u0018U\u00f1\u009c\u00cavR\\\u007fo3&\u00b8\u0087\u00b2\u009a\u0017\u00d5\u0083\u00cbY\u001eO\u00b8)8\u00f6\u00d2\u00c0\u000b\u0083\u00f2-7\u0018\u009b\u00e3~\u008e\u00c3^\u00b5\u001c5)\u00d2\u0090&$\u00bfk\u00d5\u00c9\u00ba\u0085xp\u00a2\u00b3\u000fB0Dg\u008aA\u0087>c'\u001f\u00c2N\u009b\u00ed\\\u001a\u00da\u00d32<\u0007 \u0085\u00fd\u00ed\u00e5\u00cf!\u00c0\t\u0080\u0093\u00d17\\\u00ff\u00d4\u00f7]\u0006*S\u0096\u0089\u00e0?\u0094\u0085\u0007\u001a\u00c11-9";
                            var24_6 = 330;
                            var21_7 = 32;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = Zoom.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "{&Qu\u00a4\u009d\u00ce\u0002\u00c9\u00e1AD\u00eeA\u0016t\\\u00b6\u00ba\u00f5C|\u00b3|\u00ca\u0092\u00ceM^\u0012hi\u00a7\u00ce\u00b2\u00a3 \u00e3<\u00cf\u0003Mis~\u0018)U\u00f6F1\u0083[q\u00ee\u001b \u00b9\u00a7\u00ea\u00ea-\u00f7&e\u00e8\u00deYy\u00cc\u00af\u00b7y\\\u00a14F\u00965\u00b9@\u001f\u0085_e:\u00a5\u00e2\u00a9";
                                var24_6 = 89;
                                var21_7 = 56;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = Zoom.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    Zoom.b = var25_3;
                    Zoom.c = new String[13];
                    var8_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var27 >>> 56);
                    for (var9_11 = 1; var9_11 < 8; ++var9_11) {
                        v9 = v9;
                        v9[var9_11] = (byte)(var27 << var9_11 * 8 >>> 56);
                    }
                    var8_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var7_12 = new long[6];
                    var11_13 = 0;
                    var12_14 = "^V\u0087\u00c8\u00f85\u0017\u009b\u00b5\u00fa.!T\u00ff#J\u007f\u00b1A\u009bM\u00f5\u00c8S\u0089\u00cb\u0005V\b;f\u0016";
                    var13_15 = 32;
                    var10_16 = 0;
                    while (true) {
                        var14_17 = var12_14.substring(var10_16, var10_16 += 8).getBytes("ISO-8859-1");
                        v10 = var7_12;
                        v11 = var11_13++;
                        v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl77:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var10_16 < var13_15) ** continue;
                        var12_14 = "\u0014\"\u00a03@p\t'~\u00e1\u001c\u0094\u000e>\u00b0\u009b";
                        var13_15 = 16;
                        var10_16 = 0;
                        while (true) {
                            var14_17 = var12_14.substring(var10_16, var10_16 += 8).getBytes("ISO-8859-1");
                            v10 = var7_12;
                            v11 = var11_13++;
                            v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl90:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var10_16 < var13_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_18 = v12;
                var17_19 = var8_10.doFinal(new byte[]{(byte)(var15_18 >>> 56), (byte)(var15_18 >>> 48), (byte)(var15_18 >>> 40), (byte)(var15_18 >>> 32), (byte)(var15_18 >>> 24), (byte)(var15_18 >>> 16), (byte)(var15_18 >>> 8), (byte)var15_18});
                v14 = ((long)var17_19[0] & 255L) << 56 | ((long)var17_19[1] & 255L) << 48 | ((long)var17_19[2] & 255L) << 40 | ((long)var17_19[3] & 255L) << 32 | ((long)var17_19[4] & 255L) << 24 | ((long)var17_19[5] & 255L) << 16 | ((long)var17_19[6] & 255L) << 8 | (long)var17_19[7] & 255L;
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
            var2_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_21 = 1; var3_21 < 8; ++var3_21) {
                v17 = v17;
                v17[var3_21] = (byte)(var27 << var3_21 * 8 >>> 56);
            }
            break block24;
lbl115:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{-117, -50, -8, 45, 33, -2, -1, 34});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        var40_24 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Zoom.class, "teleBind", "getTeleBind()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(Zoom.class, "teleScale", "getTeleScale()F", 0)), Reflection.property1(new PropertyReference1Impl(Zoom.class, "wideBind", "getWideBind()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(Zoom.class, "wideScale", "getWideScale()F", 0))};
        Zoom.delegatedProperties = var40_24;
        Zoom.INSTANCE = new Zoom();
        Zoom.teleBind$delegate = SettingFactory.bindSetting$default(Zoom.INSTANCE, "telephoto", new Bind(0, false, false, (int)var7_12[3], null), null, null, (int)var7_12[0], null);
        Zoom.teleScale$delegate = SettingFactory.floatSetting$default(Zoom.INSTANCE, "crop", 8.0f, RangesKt.rangeTo(1.0f, 25.0f), null, null, (int)var7_12[1], null);
        Zoom.wideBind$delegate = SettingFactory.bindSetting$default(Zoom.INSTANCE, "ultra-wide", new Bind(0, false, false, (int)var7_12[2], null), null, null, (int)var7_12[4], null);
        Zoom.wideScale$delegate = SettingFactory.floatSetting$default(Zoom.INSTANCE, "reversed-crop", 0.75f, RangesKt.rangeTo(0.1f, 1.0f), null, null, (int)var7_12[5], null);
        Zoom.animation = new Animation(Easing.OUT_QUINT, var0_22);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2276;
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
                throw new RuntimeException("su/catlean/kj", exception);
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
            Zoom.c[n2] = Zoom.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Zoom.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Zoom.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

