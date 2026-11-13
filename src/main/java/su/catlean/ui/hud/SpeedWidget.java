/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Arrays;
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
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class SpeedWidget
extends HudElement {
    @NotNull
    public static final SpeedWidget b;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting bps$delegate;
    private static final long a;
    private static final String[] c;
    private static final String[] f;
    private static final Map g;

    private SpeedWidget(int n, short s, int n2) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x414FC8E7FCBDL;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 48 >>> 48);
        super((char)n3, "speed-widget", (char)n4);
    }

    private final boolean getBps() {
        return (Boolean)bps$delegate.getValue(this, delegatedProperties[0]);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        String string;
        long l2 = l ^ 0x6A47001394F1L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        if (!this.getBps()) {
            var16_4 = "%.1f";
            var17_5 = new Object[]{(double)((float)Math.rint(this.getSpeedKmh() * TimerManager.INSTANCE.C())) * 0.1};
            String string2 = String.format(var16_4, Arrays.copyOf(var17_5, var17_5.length));
            Intrinsics.checkNotNullExpressionValue(string2, "format(...)");
            string = string2 + " km/h";
        } else {
            var16_4 = "%.1f";
            var17_5 = new Object[]{(double)((float)Math.rint(this.getSpeedBps() * TimerManager.INSTANCE.C())) * 0.1};
            String string3 = String.format(var16_4, Arrays.copyOf(var17_5, var17_5.length));
            Intrinsics.checkNotNullExpressionValue(string3, "format(...)");
            string = string3 + " b/s";
        }
        String string4 = string;
        this.drawText(drawContext, l2, string4);
    }

    private final float getSpeedKmh() {
        return Globals.getPlayer().isGliding() ? MovementUtil.INSTANCE.getSpeed() * 720.0f : MovementUtil.INSTANCE.getHorizontalSpeed() * 720.0f;
    }

    private final float getSpeedBps() {
        return Globals.getPlayer().isGliding() ? MovementUtil.INSTANCE.getSpeed() * 200.0f : MovementUtil.INSTANCE.getHorizontalSpeed() * 200.0f;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    SpeedWidget.a = ZkmKeys.a(747417204174583001L, -3591388567823944706L, MethodHandles.lookup().lookupClass()).a(233256666860436L);
                    var16 = SpeedWidget.a ^ 134988706649829L;
                    v0 = var16 ^ 106815111962835L;
                    var20_1 = (int)(v0 >>> 32);
                    var21_2 = (int)(v0 << 32 >>> 48);
                    var22_3 = (int)(v0 << 48 >>> 48);
                    SpeedWidget.g = new HashMap<K, V>(13);
                    var7_4 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v1 = SecretKeyFactory.getInstance("DES");
                    v2 = new byte[8];
                    v3 = v2;
                    v2[0] = (byte)(var16 >>> 56);
                    for (var8_5 = 1; var8_5 < 8; ++var8_5) {
                        v3 = v3;
                        v3[var8_5] = (byte)(var16 << var8_5 * 8 >>> 56);
                    }
                    var7_4.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                    var14_6 = new String[11];
                    var12_7 = 0;
                    var11_8 = "\u00db\u00c3E1\u0092\u00d0\u00a3\\\u0004\u009d\u00afeG\u0015\u00fe\u00a0\u0010\"\u0088\u0013\u00c4%\u0081\u00c6\u00f0\u00a6c\u00a2\u00125\u00d5B\u00e3 \u00ad\u00b6\u00eeW\u00f5\u009c1\u00ban\u0085\u009e\u0004\u00e8\u00bb\u00a6\u00b6\u0007W\u00b0\u00e0\u00e3Y\u000b\u00ae\u00c59*\u008f\u00e4\u00b5\u0089< \u00d6\u00cf\u00dc\u00fd\u0011\u0014\u00d3\u00ee\u0005\u00e6\u0012\u00a2\u00fa\u0096\u009f\u00f7\u00bb_\u00c9\u0001\u009b\u00eauKH\u0016\u00fb\u00e0\u00c3\bn\u0099\u0010T\u00c3\u00b2)X\u00f6\u00f1\u00eb\u00f1\u00f8vd7\u00f7\u009f\u00cd\u0010\u00f7\u00d5vw/\u00d1wv\u00ff\u00cb\u00c0\u001c:IC\"\u0010\u001a\u00fa\u00e6\u00f27A\u00f6\u00e0\u00b2%\u00caV\u0091\u00b1\u00c9\u00aa\u00103\u00a6\u00f9z\u00ffi\u00a1\u00bb\u0089\u00fb\u001fE\u00da|]H\u0010|\tP\u001d\u00e7\u00bf\u009f\u00d4\u00f7\u001b\u00eb\u00e7\u0018\u00e3\u00e7\u0000";
                    var13_9 = 184;
                    var10_10 = 16;
                    var9_11 = -1;
lbl26:
                    // 2 sources

                    while (true) {
                        v4 = ++var9_11;
                        v5 = var11_8.substring(v4, v4 + var10_10);
                        v6 = -1;
                        break block12;
                        break;
                    }
lbl31:
                    // 1 sources

                    while (true) {
                        var14_6[var12_7++] = SpeedWidget.zkmDecodeUtf8(var15_12).intern();
                        if ((var9_11 += var10_10) < var13_9) {
                            var10_10 = var11_8.charAt(var9_11);
                            ** continue;
                        }
                        var11_8 = "\u00b1\u00d5\u00f6\u00f7c\tM\u0091X\u008aA_\u000e\u0088\u00cd\u00c6\u00e7\u00ba y\u009d*\u00d7\u00f7\u00a0\u001e\u0006\u00c8\u0095\u00d0U- \u00b0\u00bcq!\u008e\u00ae\u0003\u00f9\u00f6\u00a3Zx\u00ee\u00aft\u0007\u00dc\u0089\r\u00fc\u00f2\u0096\u001e{\u0081\u00fa\u00985\u00e6)L\u00f4";
                        var13_9 = 65;
                        var10_10 = 32;
                        var9_11 = -1;
lbl40:
                        // 2 sources

                        while (true) {
                            v7 = ++var9_11;
                            v5 = var11_8.substring(v7, v7 + var10_10);
                            v6 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl45:
                    // 1 sources

                    while (true) {
                        var14_6[var12_7++] = SpeedWidget.zkmDecodeUtf8(var15_12).intern();
                        if ((var9_11 += var10_10) < var13_9) {
                            var10_10 = var11_8.charAt(var9_11);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_12 = var7_4.doFinal(v5.getBytes("ISO-8859-1"));
                switch (v6) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl57:
                    // 1 sources

                    ** continue;
                }
            }
            SpeedWidget.c = var14_6;
            SpeedWidget.f = new String[11];
            var2_13 = Cipher.getInstance("DES/CBC/NoPadding");
            v8 = SecretKeyFactory.getInstance("DES");
            v9 = new byte[8];
            v10 = v9;
            v9[0] = (byte)(var16 >>> 56);
            for (var3_14 = 1; var3_14 < 8; ++var3_14) {
                v10 = v10;
                v10[var3_14] = (byte)(var16 << var3_14 * 8 >>> 56);
            }
            break block14;
lbl71:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_13.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
        var6_16 = var2_13.doFinal(new byte[]{-37, -112, -126, 55, 117, 77, 64, -61});
        ** while (true)
        var0_15 = ((long)var6_16[0] & 255L) << 56 | ((long)var6_16[1] & 255L) << 48 | ((long)var6_16[2] & 255L) << 40 | ((long)var6_16[3] & 255L) << 32 | ((long)var6_16[4] & 255L) << 24 | ((long)var6_16[5] & 255L) << 16 | ((long)var6_16[6] & 255L) << 8 | (long)var6_16[7] & 255L;
        var23_17 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(SpeedWidget.class, "bps", "getBps()Z", 0))};
        SpeedWidget.delegatedProperties = var23_17;
        SpeedWidget.b = new SpeedWidget(var20_1, (short)var21_2, var22_3);
        SpeedWidget.bps$delegate = SettingFactory.booleanSetting$default(SpeedWidget.b, "bps", false, null, null, (int)var0_15, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7034;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/p7", exception);
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
            SpeedWidget.f[n2] = SpeedWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SpeedWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/p7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SpeedWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

