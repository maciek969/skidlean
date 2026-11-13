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

final class ScaffoldAutoSwitchMode
extends Enum {
    public static final ScaffoldAutoSwitchMode Normal;
    public static final ScaffoldAutoSwitchMode Silent;
    public static final ScaffoldAutoSwitchMode Inventory;
    public static final ScaffoldAutoSwitchMode None;
    private static final ScaffoldAutoSwitchMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ScaffoldAutoSwitchMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ScaffoldAutoSwitchMode[] values() {
        return (ScaffoldAutoSwitchMode[])$VALUES.clone();
    }

    public static ScaffoldAutoSwitchMode valueOf(String string) {
        return Enum.valueOf(ScaffoldAutoSwitchMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ScaffoldAutoSwitchMode[] $values() {
        ScaffoldAutoSwitchMode[] scaffoldAutoSwitchModeArray = new ScaffoldAutoSwitchMode[]{Normal, Silent, Inventory, None};
        return scaffoldAutoSwitchModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(6734540187936468201L, 3576685651114872021L, MethodHandles.lookup().lookupClass()).a(258553997824674L) ^ 134554896763683L;
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
                var5_5 = "\u00dbN\u0097\u00d6\u00f1\u00f1u\u001d\u0098\u00e8\u00ff:\u008d\u00ae\u00a4\u000f\b\u009f\u008f\u00d05\u00b1W\u00dfx";
                var7_6 = 25;
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
                    var0_3[var6_4++] = ScaffoldAutoSwitchMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "a\u0082SNP\u00a1\u00f3i\b\u00e13j]\u00d5\u00ca\u001aN";
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
                    var0_3[var6_4++] = ScaffoldAutoSwitchMode.zkmDecodeUtf8(var8_9).intern();
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
        ScaffoldAutoSwitchMode.Normal = new ScaffoldAutoSwitchMode(var0_3[2], 0);
        ScaffoldAutoSwitchMode.Silent = new ScaffoldAutoSwitchMode(var0_3[3], 1);
        ScaffoldAutoSwitchMode.Inventory = new ScaffoldAutoSwitchMode(var0_3[0], 2);
        ScaffoldAutoSwitchMode.None = new ScaffoldAutoSwitchMode(var0_3[1], 3);
        ScaffoldAutoSwitchMode.$VALUES = ScaffoldAutoSwitchMode.$values();
        ScaffoldAutoSwitchMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ScaffoldAutoSwitchMode.$VALUES);
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

