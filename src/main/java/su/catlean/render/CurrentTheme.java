/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.Color;
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
import org.jetbrains.annotations.NotNull;
import su.catlean.render.ThemePreset;
import su.catlean.zkm.ZkmKeys;

public final class CurrentTheme {
    @NotNull
    private static ThemePreset themePreset;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @NotNull
    public static final ThemePreset getThemePreset() {
        return themePreset;
    }

    public static final void setThemePreset(@NotNull ThemePreset themePreset) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(themePreset, (String)((Object)CurrentTheme.zkmBootstrap("d", (int)22613, (long)(0x307F8B050E9E77F3L ^ l))));
        CurrentTheme.themePreset = themePreset;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    CurrentTheme.a = ZkmKeys.a(-7178842063185856478L, -2932489648419997534L, MethodHandles.lookup().lookupClass()).a(92510374512673L);
                    var20 = CurrentTheme.a ^ 74948333939076L;
                    CurrentTheme.d = new HashMap<K, V>(13);
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
                    var18_3 = new String[2];
                    var16_4 = 0;
                    var15_5 = "\u008e\u00f9F\u009b\u00d3\u00f1\u0013_\u00a1\u001c\u00a3\u00b6\u00ef\u00eb\u00a8\u00b9\u0010\u00ef\u001a\r\u0010\u008c\u008a\u00b4Y4\u00e2\u00ac4\u00b4 \u00dd\u0011";
                    var17_6 = 33;
                    var14_7 = 16;
                    var13_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        continue;
                        break;
                    }
lbl22:
                    // 1 sources

                    while (true) {
                        var18_3[var16_4++] = CurrentTheme.zkmDecodeUtf8(var19_9).intern();
                        if ((var13_8 += var14_7) < var17_6) {
                            var14_7 = var15_5.charAt(var13_8);
                            ** continue;
                        }
                        break block12;
                        break;
                    }
                    v3 = ++var13_8;
                    var19_9 = var11_1.doFinal(var15_5.substring(v3, v3 + var14_7).getBytes("ISO-8859-1"));
                    ** while (true)
                }
                CurrentTheme.b = var18_3;
                CurrentTheme.c = new String[2];
                var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v4 = SecretKeyFactory.getInstance("DES");
                v5 = new byte[8];
                v6 = v5;
                v5[0] = (byte)(var20 >>> 56);
                for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                    v6 = v6;
                    v6[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                }
                var1_10.init(2, (Key)v4.generateSecret(new DESKeySpec(v6)), new IvParameterSpec(new byte[8]));
                var0_12 = new long[29];
                var4_13 = 0;
                var5_14 = "q\u0013\u00c3\u00c0\u008e\u00f6Ua\u0095\u00bd\u00c1\u0006]\u00c9\u0004\u00c2^\u001e\u00e8\u00e5\u00ce8H\u00f0\u00da\u0087\u00b6`\u00ee\u00ee\u009ce\u00fbL?H\u00ac\u00e0\u0007\u0012\u008d%d\u009e\f\u000e\u00b07\u00d6\u00fe \u00c3p\u00f71LH\n\u0094\u00bc\u00968\u0014\u008cH\u00fefw\u00ac\u0001\u00a9\u0096\u00b41~\u00dfM\u00c0\u00a7@\r\u00f4\u00b2E,$\u00c7}\u00df\u00ba\u0004?[\u00a3)\u001d\u008c}\u00ad\u009d\fe\u00f4\u00c8\u0001\u008fX\u00e6\u00af~\u0082\u0094=]0h\u0094\u0092\u00ef\u00ecH\u00f4\u00a8#A\u000b>\u00d3H,\u0097>\u00b5O9\u0010L*\u00d6\u00ca\u0080\u000e\u00e1\u009a\u009c\u001a\u00008;\u0086\u00f3\u00eb\u00cde\u009eF\u00eb\u00b9\u00d7kwSw\u00abe1'r\u00acJy\u00ef\u00fc\u00fb\u00c9\u00e8\u00ec\u0080\u00a7\u00b1\u009b\u00b04\u00f8\u0017\u008eRJ/9Y[E\u00eeb[\u00968\u0018L\u00d2lj\u00ccu\u001f\u00e7\u00ce\u00ea\u00ad-gv[\u00bfB";
                var6_15 = 216;
                var3_16 = 0;
                while (true) {
                    var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                    v7 = var0_12;
                    v8 = var4_13++;
                    v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v10 = -1;
                    break block13;
                    break;
                }
lbl56:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var3_16 < var6_15) ** continue;
                    var5_14 = "\u00adJ\u00ca&\u0001\u00860\u00bf{H\u00f7\u00b4\u0019%&$";
                    var6_15 = 16;
                    var3_16 = 0;
                    while (true) {
                        var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                        v7 = var0_12;
                        v8 = var4_13++;
                        v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v10 = 0;
                        break block13;
                        break;
                    }
                    break;
                }
lbl69:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var3_16 < var6_15) ** continue;
                    break block14;
                    break;
                }
            }
            var8_18 = v9;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v11 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v10) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl82:
                // 1 sources

                ** continue;
            }
        }
        var25_20 = new Color((int)var0_12[7], (int)var0_12[7], (int)var0_12[1], (int)var0_12[13]);
        var26_21 = new Color((int)var0_12[27], (int)var0_12[27], (int)var0_12[8], (int)var0_12[25]);
        var27_22 = new Color((int)var0_12[22], (int)var0_12[27], (int)var0_12[12], (int)var0_12[11]);
        var28_23 = new Color((int)var0_12[17], (int)var0_12[4], (int)var0_12[26], (int)var0_12[14]);
        var29_24 = new Color((int)var0_12[21], (int)var0_12[18], (int)var0_12[2]);
        var30_25 = new Color((int)var0_12[19], (int)var0_12[9], (int)var0_12[9]);
        var31_26 = new Color((int)var0_12[28], (int)var0_12[13], (int)var0_12[13]);
        var32_27 = new Color((int)var0_12[20], (int)var0_12[10], (int)var0_12[24]);
        CurrentTheme.themePreset = new ThemePreset("CatLean", new Color((int)var0_12[23]), new Color((int)var0_12[16]), new Color((int)var0_12[15], (int)var0_12[6], (int)var0_12[6]), new Color((int)var0_12[3], (int)var0_12[5], (int)var0_12[0]), var32_27, var31_26, var30_25, var29_24, var28_23, var27_22, var26_21, var25_20);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4B63;
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
                throw new RuntimeException("su/catlean/cd", exception);
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
            CurrentTheme.c[n2] = CurrentTheme.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CurrentTheme.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/cd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CurrentTheme.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

