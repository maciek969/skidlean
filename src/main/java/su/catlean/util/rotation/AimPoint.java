/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

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

public final class AimPoint
extends Enum {
    public static final AimPoint CENTER;
    public static final AimPoint EYES;
    public static final AimPoint CLOSEST;
    public static final AimPoint ROAMING;
    public static final AimPoint RUST;
    private static final AimPoint[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AimPoint() {
        void var2_-1;
        void var1_-1;
    }

    public static AimPoint[] values() {
        return (AimPoint[])$VALUES.clone();
    }

    public static AimPoint valueOf(String string) {
        return Enum.valueOf(AimPoint.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AimPoint[] $values() {
        AimPoint[] aimPointArray = new AimPoint[]{CENTER, EYES, CLOSEST, ROAMING, RUST};
        return aimPointArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(5824445265659733564L, -7339248401476368947L, MethodHandles.lookup().lookupClass()).a(111427794106751L) ^ 63740139840928L;
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
                var5_5 = "\u00b7\u00f8\u001d\u00edZ\u00efak\b\u008c\u00f5\u00f0l\u00e4\u00de\u00e4\u0010\b\u00e2\u00da\u00e2a\u00d3\u00fc\u00ad\u00be";
                var7_6 = 26;
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
                    var0_3[var6_4++] = AimPoint.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00c5\u0083j\u008eG\u00d7\u00b3.\b\b\u00f3\u00ea\u00ce5q4\u00d0";
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
                    var0_3[var6_4++] = AimPoint.zkmDecodeUtf8(var8_9).intern();
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
        AimPoint.CENTER = new AimPoint(var0_3[3], 0);
        AimPoint.EYES = new AimPoint(var0_3[0], 1);
        AimPoint.CLOSEST = new AimPoint(var0_3[4], 2);
        AimPoint.ROAMING = new AimPoint(var0_3[2], 3);
        AimPoint.RUST = new AimPoint(var0_3[1], 4);
        AimPoint.$VALUES = AimPoint.$values();
        AimPoint.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AimPoint.$VALUES);
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

