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

public final class SpiderMode
extends Enum {
    public static final SpiderMode DEFAULT;
    public static final SpiderMode MATRIX;
    public static final SpiderMode MATRIX_2;
    public static final SpiderMode WATER_BUCKET;
    public static final SpiderMode FT_FENCE;
    private static final SpiderMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String[] E;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpiderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpiderMode[] values() {
        return (SpiderMode[])$VALUES.clone();
    }

    public static SpiderMode valueOf(String string) {
        return Enum.valueOf(SpiderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpiderMode[] $values() {
        SpiderMode[] spiderModeArray = new SpiderMode[]{DEFAULT, MATRIX, MATRIX_2, WATER_BUCKET, FT_FENCE};
        return spiderModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-5939860606891966860L, 91943142864566276L, MethodHandles.lookup().lookupClass()).a(32225959345879L) ^ 85434103527433L;
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
                var0_3 = new String[5];
                var6_4 = 0;
                var5_5 = "\u0097\n1\u00ae\u00c8\u00df\u009fi\u00ff\u00d3\u00b9W\u00fa\u0002\u00f9W\u0010\u00cb'\u00bc\u0082\u00eb\u00e6\u0091\u00f9T\u008e\u001c\u00a3\u0005\u00b0\u00c1c\b\u0094t\u00fe\u00b91\u00cf\u0001,";
                var7_6 = 42;
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
                    var0_3[var6_4++] = SpiderMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\bZ\u0017e?(G\u00cd\u0010\u001c\u00d2\u00e6\u00aeU;\u00aeM\u0083`I\u00f0\u00a7\u0099\u00c7\u00e7";
                    var7_6 = 25;
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
                    var0_3[var6_4++] = SpiderMode.zkmDecodeUtf8(var8_9).intern();
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
        SpiderMode.DEFAULT = new SpiderMode(var0_3[3], 0);
        SpiderMode.MATRIX = new SpiderMode(var0_3[2], 1);
        SpiderMode.MATRIX_2 = new SpiderMode(var0_3[0], 2);
        SpiderMode.WATER_BUCKET = new SpiderMode(var0_3[4], 3);
        SpiderMode.FT_FENCE = new SpiderMode(var0_3[1], 4);
        SpiderMode.$VALUES = SpiderMode.$values();
        SpiderMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])SpiderMode.$VALUES);
    }

    public static void zkmSetFlag(String[] stringArray) {
        E = stringArray;
    }

    public static String[] zkmGetFlag() {
        return E;
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

