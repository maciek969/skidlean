/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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

public final class AutoCrystalRenderMode
extends Enum {
    public static final AutoCrystalRenderMode SHADER;
    public static final AutoCrystalRenderMode DEAFULT;
    public static final AutoCrystalRenderMode TECHNO;
    public static final AutoCrystalRenderMode OFF;
    private static final AutoCrystalRenderMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalRenderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalRenderMode[] values() {
        return (AutoCrystalRenderMode[])$VALUES.clone();
    }

    public static AutoCrystalRenderMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalRenderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalRenderMode[] $values() {
        AutoCrystalRenderMode[] autoCrystalRenderModeArray = new AutoCrystalRenderMode[]{SHADER, DEAFULT, TECHNO, OFF};
        return autoCrystalRenderModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-2730300540144778939L, -6423729631646826840L, MethodHandles.lookup().lookupClass()).a(2892533757754L) ^ 102329342373537L;
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
                var5_5 = "\u009e\u00ccu\u00e9uMn\u00ba\b\u00b7\u00d9\u00a46\u0014\u00be\u0086\u00de";
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
                    var0_3[var6_4++] = AutoCrystalRenderMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\r(\u00e3\u009a\u00a9\u008bR\u00ec\b\\\u0098%\u00b7\u00df$\u00f3\u00d2";
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
                    var0_3[var6_4++] = AutoCrystalRenderMode.zkmDecodeUtf8(var8_9).intern();
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
        AutoCrystalRenderMode.SHADER = new AutoCrystalRenderMode(var0_3[3], 0);
        AutoCrystalRenderMode.DEAFULT = new AutoCrystalRenderMode(var0_3[2], 1);
        AutoCrystalRenderMode.TECHNO = new AutoCrystalRenderMode(var0_3[0], 2);
        AutoCrystalRenderMode.OFF = new AutoCrystalRenderMode(var0_3[1], 3);
        AutoCrystalRenderMode.$VALUES = AutoCrystalRenderMode.$values();
        AutoCrystalRenderMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AutoCrystalRenderMode.$VALUES);
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

