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

public final class AntiWebMode
extends Enum {
    public static final AntiWebMode TIMER;
    public static final AntiWebMode SOLID;
    public static final AntiWebMode IGNORE;
    public static final AntiWebMode FLY;
    private static final AntiWebMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int[] R;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AntiWebMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AntiWebMode[] values() {
        return (AntiWebMode[])$VALUES.clone();
    }

    public static AntiWebMode valueOf(String string) {
        return Enum.valueOf(AntiWebMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AntiWebMode[] $values() {
        AntiWebMode[] antiWebModeArray = new AntiWebMode[]{TIMER, SOLID, IGNORE, FLY};
        return antiWebModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(8836961967161559101L, 2397659085046842625L, MethodHandles.lookup().lookupClass()).a(142277872029241L) ^ 121541229384206L;
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
                var5_5 = "\u00bb\u00c7\u008e\u00fe\u00de\u0088\u00d3\u00d6\b[\u00c8\u00dfE\u0082\f\u0007V";
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
                    var0_3[var6_4++] = AntiWebMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u0083 \u009e\u00c3\u009az\u00a8\u00b0\b\u00e0\u00a2\u00f8\u00b8\u00a9\u0083#F";
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
                    var0_3[var6_4++] = AntiWebMode.zkmDecodeUtf8(var8_9).intern();
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
        AntiWebMode.TIMER = new AntiWebMode(var0_3[3], 0);
        AntiWebMode.SOLID = new AntiWebMode(var0_3[1], 1);
        AntiWebMode.IGNORE = new AntiWebMode(var0_3[0], 2);
        AntiWebMode.FLY = new AntiWebMode(var0_3[2], 3);
        AntiWebMode.$VALUES = AntiWebMode.$values();
        AntiWebMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AntiWebMode.$VALUES);
    }

    public static void zkmSetFlag(int[] nArray) {
        R = nArray;
    }

    public static int[] zkmGetFlag() {
        return R;
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

