/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class ConfigFiles {
    @NotNull
    private static final File ctlDir;
    @NotNull
    private static final File inventoriesDir;
    @NotNull
    private static final File stashesDir;
    @NotNull
    private static final File[] Y;
    private static String[] q;

    @NotNull
    public static final File getCtlDir() {
        return ctlDir;
    }

    @NotNull
    public static final File getInventoriesDir() {
        return inventoriesDir;
    }

    @NotNull
    public static final File getStashesDir() {
        return stashesDir;
    }

    @NotNull
    public static final File[] l() {
        return Y;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(6698001044120371411L, 2540612746589965740L, MethodHandles.lookup().lookupClass()).a(8539572600840L) ^ 112080634316937L;
                var1_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var2_2 = 1; var2_2 < 8; ++var2_2) {
                    v2 = v2;
                    v2[var2_2] = (byte)(var9 << var2_2 * 8 >>> 56);
                }
                var1_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var0_3 = new String[4];
                var6_4 = 0;
                var5_5 = "*\u00c9E \u00b7\u00cf\u00ec|)\u00a3\u009d\u00d1ef\u008e\u009d\u0010g\u00a2\n\u00ecO2\u00aa\u00e9o\u00cf\u00e9pm\u0015\u0099\u0081";
                var7_6 = 33;
                var4_7 = 16;
                var3_8 = -1;
lbl18:
                // 2 sources

                while (true) {
                    v3 = ++var3_8;
                    v4 = var5_5.substring(v3, v3 + var4_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl23:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = ConfigFiles.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "]\u0018\u001a\u00b3@\u0000\u0090\t\u00a1\u00f4\u00e6E\u00d1\u001a\u0010\u00c8\b\u0013\u008bY|\u008a\u0099\u00d3\u0090";
                    var7_6 = 25;
                    var4_7 = 16;
                    var3_8 = -1;
lbl32:
                    // 2 sources

                    while (true) {
                        v6 = ++var3_8;
                        v4 = var5_5.substring(v6, v6 + var4_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl37:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = ConfigFiles.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var1_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl49:
                // 1 sources

                ** continue;
            }
        }
        ConfigFiles.ctlDir = new File(System.getProperty(var0_3[2]) + var0_3[3]);
        ConfigFiles.inventoriesDir = new File(ConfigFiles.ctlDir + var0_3[1]);
        ConfigFiles.stashesDir = new File(ConfigFiles.ctlDir + var0_3[0]);
        var11_10 = new File[]{ConfigFiles.ctlDir, ConfigFiles.inventoriesDir, ConfigFiles.stashesDir};
        ConfigFiles.Y = var11_10;
    }

    public static void zkmSetFlag(String[] stringArray) {
        q = stringArray;
    }

    public static String[] zkmGetFlag() {
        return q;
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
}

