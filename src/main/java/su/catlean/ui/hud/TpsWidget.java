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
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;

public final class TpsWidget
extends HudElement {
    @NotNull
    public static final TpsWidget f;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map g;

    private TpsWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x766051D8A96FL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "tps-widget", (char)n2);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2) {
        var6_3 = var1_1 ^ 116853176505585L;
        v0 = ArrayListEntry.u();
        Intrinsics.checkNotNullParameter(var3_2, "context");
        var11_4 = v0;
        v1 = ServerInfo.INSTANCE.getTpsRounded();
        v2 = ServerInfo.INSTANCE;
        if (var11_4) ** GOTO lbl13
        if (v2.isTpsSampling()) {
            v3 = "--.--";
        } else {
            v2 = ServerInfo.INSTANCE;
lbl13:
            // 2 sources

            v3 = Float.valueOf(v2.getTps());
        }
        this.drawText(var3_2, var6_3, v1 + " (" + v3 + ") t/s");
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                TpsWidget.a = ZkmKeys.a(3763233803705961437L, -4135775130737356960L, MethodHandles.lookup().lookupClass()).a(160838531008089L);
                var9 = TpsWidget.a ^ 72457318923244L;
                var11_1 = var9 ^ 120025089873928L;
                TpsWidget.g = new HashMap<K, V>(13);
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
                var7_4 = new String[5];
                var5_5 = 0;
                var4_6 = "\u009eCY\fF\u00b4\u0090\u00c1dAp\u008cT\u0080Sa\u0010\u00a8\u0081~.\u00e1\u00df\u00ce\u0092\u00a4\u00a3\u00aea):\u00fc\u00c0\u0010e\u00a2u\u0001\u0017\u00d8\u00cf|<\u0085\u00f0\u00dc\u00c8 FC";
                var6_7 = 50;
                var3_8 = 16;
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
                    var7_4[var5_5++] = TpsWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    var4_6 = "T\u008b&i@\u00e6\u0015\u00a2I\u00e3\u0018\u0096\u00c2/\u000e\u00fe\u0018\u00bcr8hV\u0083X\u00b8=\u00e4Bw\u0087%A\u00f4\u00f6q\u00a02\u00c1\u0006\u00abL";
                    var6_7 = 41;
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
                    var7_4[var5_5++] = TpsWidget.zkmDecodeUtf8(var8_10).intern();
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
        TpsWidget.b = var7_4;
        TpsWidget.c = new String[5];
        TpsWidget.f = new TpsWidget(var11_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2396;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/pd", exception);
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
            TpsWidget.c[n2] = TpsWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TpsWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TpsWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

