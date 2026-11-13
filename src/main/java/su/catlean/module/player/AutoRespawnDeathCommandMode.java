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

final class AutoRespawnDeathCommandMode
extends Enum {
    public static final AutoRespawnDeathCommandMode KIT;
    public static final AutoRespawnDeathCommandMode HOME;
    public static final AutoRespawnDeathCommandMode BACK;
    public static final AutoRespawnDeathCommandMode CUSTOM;
    private static final AutoRespawnDeathCommandMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoRespawnDeathCommandMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoRespawnDeathCommandMode[] values() {
        return (AutoRespawnDeathCommandMode[])$VALUES.clone();
    }

    public static AutoRespawnDeathCommandMode valueOf(String string) {
        return Enum.valueOf(AutoRespawnDeathCommandMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoRespawnDeathCommandMode[] $values() {
        AutoRespawnDeathCommandMode[] autoRespawnDeathCommandModeArray = new AutoRespawnDeathCommandMode[]{KIT, HOME, BACK, CUSTOM};
        return autoRespawnDeathCommandModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-8863713311114809187L, 5003379275313291898L, MethodHandles.lookup().lookupClass()).a(163519913788066L) ^ 48168640236505L;
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
                var5_5 = "\\\u00ac\u00c7\u00c4\u00da\u00f4\u00e6?\b\u00a4\fe+5\u00d2\u0083G";
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
                    var0_3[var6_4++] = AutoRespawnDeathCommandMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "c0\u00b2\u001b\u0014\u00f8l\u00de\bhh\u00d8K\u00af\r\u0087\u00d6";
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
                    var0_3[var6_4++] = AutoRespawnDeathCommandMode.zkmDecodeUtf8(var8_9).intern();
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
        AutoRespawnDeathCommandMode.KIT = new AutoRespawnDeathCommandMode(var0_3[2], 0);
        AutoRespawnDeathCommandMode.HOME = new AutoRespawnDeathCommandMode(var0_3[1], 1);
        AutoRespawnDeathCommandMode.BACK = new AutoRespawnDeathCommandMode(var0_3[3], 2);
        AutoRespawnDeathCommandMode.CUSTOM = new AutoRespawnDeathCommandMode(var0_3[0], 3);
        AutoRespawnDeathCommandMode.$VALUES = AutoRespawnDeathCommandMode.$values();
        AutoRespawnDeathCommandMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AutoRespawnDeathCommandMode.$VALUES);
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

