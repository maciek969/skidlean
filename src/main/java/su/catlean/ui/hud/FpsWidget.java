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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.FpsCounter;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class FpsWidget
extends HudElement {
    @NotNull
    public static final FpsWidget i;
    private static int K;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;

    private FpsWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x4152B23F890CL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "fps-widget", (char)n2);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        long l2 = l ^ 0x6A47001394F1L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        this.drawText(drawContext, l2, K + " " + (Globals.getMc().options.getEnableVsync().getValue() != false ? "v-sync" : "fps"));
    }

    @Override
    public void tick() {
        if (Globals.getPlayer().age % 4 == 0) {
            K = FpsCounter.INSTANCE.D();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                FpsWidget.a = ZkmKeys.a(-8051536857964912462L, 5044691229711541945L, MethodHandles.lookup().lookupClass()).a(242925070611648L);
                var9 = FpsWidget.a ^ 103282710814173L;
                var11_1 = var9 ^ 77044443831898L;
                FpsWidget.f = new HashMap<K, V>(13);
                var0_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_3 = 1; var1_3 < 8; ++var1_3) {
                    v2 = v2;
                    v2[var1_3] = (byte)(var9 << var1_3 * 8 >>> 56);
                }
                var0_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_4 = new String[4];
                var5_5 = 0;
                var4_6 = "3\u001ef{\u00ca\u008c\u00ac\u007f\u00dc\u0088Z\u009dw\u00d7t\u00d6\u0092L\b\u00e2\u001b\u00fa\u00b1\u001c\u00f9\u008b\u0082e{\u00d4F\u008e\u0010 P\u009f\u00bf\u0081\u008a\u0001a^\u00a7\b\u00c0Wh,\u00b1";
                var6_7 = 49;
                var3_8 = 32;
                var2_9 = -1;
lbl22:
                // 2 sources

                while (true) {
                    v3 = ++var2_9;
                    v4 = var4_6.substring(v3, v3 + var3_8);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl27:
                // 1 sources

                while (true) {
                    var7_4[var5_5++] = FpsWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    var4_6 = "$\u00c2\u00c1\u00cef\u0099\u00ced\u00e79\u00adb\u00c2a\u00b0\u00d6\u0010|\u009fu\u008f\f\u00e7Y\u000f4\u00df\u00f9=\u00b6\u009ckV";
                    var6_7 = 33;
                    var3_8 = 16;
                    var2_9 = -1;
lbl36:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_9;
                        v4 = var4_6.substring(v6, v6 + var3_8);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl41:
                // 1 sources

                while (true) {
                    var7_4[var5_5++] = FpsWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_10 = var0_2.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl53:
                // 1 sources

                ** continue;
            }
        }
        FpsWidget.b = var7_4;
        FpsWidget.c = new String[4];
        FpsWidget.i = new FpsWidget(var11_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F94;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/pq", exception);
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
            FpsWidget.c[n2] = FpsWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FpsWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FpsWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

