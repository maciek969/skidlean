/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

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

public final class SpeedMineSelectionMode
extends Enum {
    public static final SpeedMineSelectionMode ALWAYS;
    public static final SpeedMineSelectionMode AIR_REBREAK;
    public static final SpeedMineSelectionMode REBREAK;
    public static final SpeedMineSelectionMode OFF;
    private static final SpeedMineSelectionMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpeedMineSelectionMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpeedMineSelectionMode[] values() {
        return (SpeedMineSelectionMode[])$VALUES.clone();
    }

    public static SpeedMineSelectionMode valueOf(String string) {
        return Enum.valueOf(SpeedMineSelectionMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpeedMineSelectionMode[] $values() {
        SpeedMineSelectionMode[] speedMineSelectionModeArray = new SpeedMineSelectionMode[]{ALWAYS, AIR_REBREAK, REBREAK, OFF};
        return speedMineSelectionModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(1948764539025470230L, -7942601080177276727L, MethodHandles.lookup().lookupClass()).a(112864324952125L) ^ 19480043615521L;
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
                var5_5 = "\u0090u\u009f\u00c9(\u00a1\u0088\u008a\u0010\u0086\u00d0 \u00d9\u0091,^\u000e\u008e\u00d0\u00a4\u001e\u00c7>H\u00b5";
                var7_6 = 25;
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
                    var0_3[var6_4++] = SpeedMineSelectionMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00f8\u00b3\u00a5\u00d5\u00dc\u00b2\u00e7\u008a\b6\u0096\u00bd\u0098`{\u00aa\u00ff";
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
                    var0_3[var6_4++] = SpeedMineSelectionMode.zkmDecodeUtf8(var8_9).intern();
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
        SpeedMineSelectionMode.ALWAYS = new SpeedMineSelectionMode(var0_3[0], 0);
        SpeedMineSelectionMode.AIR_REBREAK = new SpeedMineSelectionMode(var0_3[1], 1);
        SpeedMineSelectionMode.REBREAK = new SpeedMineSelectionMode(var0_3[3], 2);
        SpeedMineSelectionMode.OFF = new SpeedMineSelectionMode(var0_3[2], 3);
        SpeedMineSelectionMode.$VALUES = SpeedMineSelectionMode.$values();
        SpeedMineSelectionMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])SpeedMineSelectionMode.$VALUES);
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

