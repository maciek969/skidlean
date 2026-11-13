/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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

final class AutoArmorElytraPriorityMode
extends Enum {
    public static final AutoArmorElytraPriorityMode NONE;
    public static final AutoArmorElytraPriorityMode ALWAYS;
    public static final AutoArmorElytraPriorityMode ELYTRA_PLUS;
    public static final AutoArmorElytraPriorityMode IGNORE;
    private static final AutoArmorElytraPriorityMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoArmorElytraPriorityMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoArmorElytraPriorityMode[] values() {
        return (AutoArmorElytraPriorityMode[])$VALUES.clone();
    }

    public static AutoArmorElytraPriorityMode valueOf(String string) {
        return Enum.valueOf(AutoArmorElytraPriorityMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoArmorElytraPriorityMode[] $values() {
        AutoArmorElytraPriorityMode[] autoArmorElytraPriorityModeArray = new AutoArmorElytraPriorityMode[]{NONE, ALWAYS, ELYTRA_PLUS, IGNORE};
        return autoArmorElytraPriorityModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(8026915209759146663L, -724339515260618886L, MethodHandles.lookup().lookupClass()).a(85239508610129L) ^ 2211822911499L;
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
                var5_5 = "3V\u00c1\u00b3\u0006I\u00aa\u00f1\b.\u00b2\u00eb\u00baQ\u00a8\u00a2\u00fd";
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
                    var0_3[var6_4++] = AutoArmorElytraPriorityMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "K\u00bd\u00a2\u00d8\u0011g\u001b\u001c{\u0093\u0014\u0010xoML\b\u00c2\u00b5\u0000\u00fd-YM ";
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
                    var0_3[var6_4++] = AutoArmorElytraPriorityMode.zkmDecodeUtf8(var8_9).intern();
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
        AutoArmorElytraPriorityMode.NONE = new AutoArmorElytraPriorityMode(var0_3[0], 0);
        AutoArmorElytraPriorityMode.ALWAYS = new AutoArmorElytraPriorityMode(var0_3[3], 1);
        AutoArmorElytraPriorityMode.ELYTRA_PLUS = new AutoArmorElytraPriorityMode(var0_3[2], 2);
        AutoArmorElytraPriorityMode.IGNORE = new AutoArmorElytraPriorityMode(var0_3[1], 3);
        AutoArmorElytraPriorityMode.$VALUES = AutoArmorElytraPriorityMode.$values();
        AutoArmorElytraPriorityMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AutoArmorElytraPriorityMode.$VALUES);
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

