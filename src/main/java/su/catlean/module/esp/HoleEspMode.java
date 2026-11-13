/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

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

final class HoleEspMode
extends Enum {
    public static final HoleEspMode Fade;
    public static final HoleEspMode CubeOutline;
    public static final HoleEspMode CubeFill;
    public static final HoleEspMode CubeBoth;
    public static final HoleEspMode Shader;
    public static final HoleEspMode BLOOM;
    private static final HoleEspMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static final long a;
    private static final long b;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private HoleEspMode() {
        void var2_-1;
        void var1_-1;
    }

    public static HoleEspMode[] values() {
        return (HoleEspMode[])$VALUES.clone();
    }

    public static HoleEspMode valueOf(String string) {
        return Enum.valueOf(HoleEspMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final HoleEspMode[] $values() {
        HoleEspMode[] holeEspModeArray = new HoleEspMode[(int)b];
        holeEspModeArray[0] = Fade;
        holeEspModeArray[1] = CubeOutline;
        holeEspModeArray[2] = CubeFill;
        holeEspModeArray[3] = CubeBoth;
        holeEspModeArray[4] = Shader;
        holeEspModeArray[5] = BLOOM;
        return holeEspModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    HoleEspMode.a = ZkmKeys.a(8467547636889101675L, -2590303826075700853L, MethodHandles.lookup().lookupClass()).a(130001848970119L);
                    var14 = HoleEspMode.a ^ 93968509391626L;
                    var6_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var7_2 = 1; var7_2 < 8; ++var7_2) {
                        v2 = v2;
                        v2[var7_2] = (byte)(var14 << var7_2 * 8 >>> 56);
                    }
                    var6_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var5_3 = new String[6];
                    var11_4 = 0;
                    var10_5 = "\u00e2\u00a2 I+<\u0087&\b\\#\u00e0\u008b\u00f45\u0095p\u0010\u000fk\u0083R\u00193\u009dE^0\u001b\u00e5S_\u00c0E\u0010Y\u00e4~\u00c3\u0091\u00a9\u00bd\u00d3\u0081\u00b0\u00ad\u001by\u008c3\u008b";
                    var12_6 = 51;
                    var9_7 = 8;
                    var8_8 = -1;
lbl19:
                    // 2 sources

                    while (true) {
                        v3 = ++var8_8;
                        v4 = var10_5.substring(v3, v3 + var9_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl24:
                    // 1 sources

                    while (true) {
                        var5_3[var11_4++] = HoleEspMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        var10_5 = "\u00b0\u0013,\u00f0\u00969\u009e\u00e1\u0010\u0097'\u00e3\u008a\u0011t`\u0016\u00a6\u0080\u00a0\u00f87r|\u00f8";
                        var12_6 = 25;
                        var9_7 = 8;
                        var8_8 = -1;
lbl33:
                        // 2 sources

                        while (true) {
                            v6 = ++var8_8;
                            v4 = var10_5.substring(v6, v6 + var9_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl38:
                    // 1 sources

                    while (true) {
                        var5_3[var11_4++] = HoleEspMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var6_1.doFinal(v4.getBytes("ISO-8859-1"));
                switch (v5) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl50:
                    // 1 sources

                    ** continue;
                }
            }
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl62:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{113, -27, 70, -127, 78, 99, -26, 57});
        ** while (true)
        HoleEspMode.b = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        HoleEspMode.Fade = new HoleEspMode(var5_3[4], 0);
        HoleEspMode.CubeOutline = new HoleEspMode(var5_3[3], 1);
        HoleEspMode.CubeFill = new HoleEspMode(var5_3[5], 2);
        HoleEspMode.CubeBoth = new HoleEspMode(var5_3[2], 3);
        HoleEspMode.Shader = new HoleEspMode(var5_3[0], 4);
        HoleEspMode.BLOOM = new HoleEspMode(var5_3[1], 5);
        HoleEspMode.$VALUES = HoleEspMode.$values();
        HoleEspMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])HoleEspMode.$VALUES);
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

