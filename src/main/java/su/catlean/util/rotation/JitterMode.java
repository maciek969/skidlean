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

public final class JitterMode
extends Enum {
    public static final JitterMode NONE;
    public static final JitterMode RANDOM;
    public static final JitterMode ROAMING;
    public static final JitterMode SINE;
    public static final JitterMode DRUNK_SINE;
    private static final JitterMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String V;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private JitterMode() {
        void var2_-1;
        void var1_-1;
    }

    public static JitterMode[] values() {
        return (JitterMode[])$VALUES.clone();
    }

    public static JitterMode valueOf(String string) {
        return Enum.valueOf(JitterMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final JitterMode[] $values() {
        JitterMode[] jitterModeArray = new JitterMode[]{NONE, RANDOM, ROAMING, SINE, DRUNK_SINE};
        return jitterModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-4781377104962693916L, 6836573162517082784L, MethodHandles.lookup().lookupClass()).a(62645829198134L) ^ 129393143799090L;
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
                var5_5 = "NO+\u00adb\u00ea\u00c6\u00df\b=m\u00f2\u00be\u0014SV\u0004\u0010\u008c\u00b8Y\u00fckv\u00e1\u00e9\u0010a\u0016\u00b2\u00bdb\u00f2\u0088";
                var7_6 = 34;
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
                    var0_3[var6_4++] = JitterMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00e4\u00a1\u0090;\u0088\u0089\u001d\u00bc\b)\u00e6GkB\u00fc\u00e7\u008b";
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
                    var0_3[var6_4++] = JitterMode.zkmDecodeUtf8(var8_9).intern();
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
        JitterMode.NONE = new JitterMode(var0_3[3], 0);
        JitterMode.RANDOM = new JitterMode(var0_3[0], 1);
        JitterMode.ROAMING = new JitterMode(var0_3[4], 2);
        JitterMode.SINE = new JitterMode(var0_3[1], 3);
        JitterMode.DRUNK_SINE = new JitterMode(var0_3[2], 4);
        JitterMode.$VALUES = JitterMode.$values();
        JitterMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])JitterMode.$VALUES);
    }

    public static void zkmSetFlag(String string) {
        V = string;
    }

    public static String zkmGetFlag() {
        return V;
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

