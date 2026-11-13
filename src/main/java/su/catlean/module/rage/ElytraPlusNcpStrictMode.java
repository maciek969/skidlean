/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class ElytraPlusNcpStrictMode
extends Enum {
    public static final ElytraPlusNcpStrictMode Off;
    public static final ElytraPlusNcpStrictMode Old;
    public static final ElytraPlusNcpStrictMode New;
    public static final ElytraPlusNcpStrictMode Motion;
    private static final ElytraPlusNcpStrictMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String[] F;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ElytraPlusNcpStrictMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ElytraPlusNcpStrictMode[] values() {
        return (ElytraPlusNcpStrictMode[])$VALUES.clone();
    }

    public static ElytraPlusNcpStrictMode valueOf(String string) {
        return Enum.valueOf(ElytraPlusNcpStrictMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ElytraPlusNcpStrictMode[] $values() {
        ElytraPlusNcpStrictMode[] elytraPlusNcpStrictModeArray = new ElytraPlusNcpStrictMode[]{Off, Old, New, Motion};
        return elytraPlusNcpStrictModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-8721691644991822009L, 5506501606517897408L, MethodHandles.lookup().lookupClass()).a(51764202505447L) ^ 139408803635658L;
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
                var5_5 = "\u008f\u00e8\u00ca\u0093\u00a1\u009bi\u00b5\b\u00e9\u00df;d/\u00f1p\u00c8";
                var7_6 = 17;
                var4_7 = 8;
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
                    var0_3[var6_4++] = ElytraPlusNcpStrictMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "e\u0083:\u00c9\u00e5\u008e\u000f\u001d\b\u00b3]X%\u00f9\u0012\u00ee\u00d5";
                    var7_6 = 17;
                    var4_7 = 8;
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
                    var0_3[var6_4++] = ElytraPlusNcpStrictMode.zkmDecodeUtf8(var8_9).intern();
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
        ElytraPlusNcpStrictMode.Off = new ElytraPlusNcpStrictMode(var0_3[0], 0);
        ElytraPlusNcpStrictMode.Old = new ElytraPlusNcpStrictMode(var0_3[2], 1);
        ElytraPlusNcpStrictMode.New = new ElytraPlusNcpStrictMode(var0_3[1], 2);
        ElytraPlusNcpStrictMode.Motion = new ElytraPlusNcpStrictMode(var0_3[3], 3);
        ElytraPlusNcpStrictMode.$VALUES = ElytraPlusNcpStrictMode.$values();
        ElytraPlusNcpStrictMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ElytraPlusNcpStrictMode.$VALUES);
    }

    public static void zkmSetFlag(String[] stringArray) {
        F = stringArray;
    }

    public static String[] zkmGetFlag() {
        return F;
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

