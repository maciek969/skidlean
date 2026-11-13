/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

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

public final class ShaderMode
extends Enum {
    public static final ShaderMode MIRROR;
    public static final ShaderMode DEFAULT;
    public static final ShaderMode BLOOM;
    public static final ShaderMode DOUBLE;
    public static final ShaderMode CAMOUFLAGE;
    private static final ShaderMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int v;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ShaderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ShaderMode[] values() {
        return (ShaderMode[])$VALUES.clone();
    }

    public static ShaderMode valueOf(String string) {
        return Enum.valueOf(ShaderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ShaderMode[] $values() {
        ShaderMode[] shaderModeArray = new ShaderMode[]{MIRROR, DEFAULT, BLOOM, DOUBLE, CAMOUFLAGE};
        return shaderModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(7319074314789041939L, -1086925738408626528L, MethodHandles.lookup().lookupClass()).a(228645840305507L) ^ 69548038974762L;
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
                var5_5 = "\u00eb\u000bJ\u009d1\t\u0089\u00ae\bm\u0005bW#?}\u00ce\bT\u009e\u000b\u00ef}\u00be\"\u00ac";
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
                    var0_3[var6_4++] = ShaderMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00cc8\u00f5\u00bf\u001f\u00fb?e\u0010\u00f3dNi\u00f0\u00d0\u009cqdop\u00fd\u00b5}i\u0003";
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
                    var0_3[var6_4++] = ShaderMode.zkmDecodeUtf8(var8_9).intern();
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
        ShaderMode.MIRROR = new ShaderMode(var0_3[3], 0);
        ShaderMode.DEFAULT = new ShaderMode(var0_3[2], 1);
        ShaderMode.BLOOM = new ShaderMode(var0_3[1], 2);
        ShaderMode.DOUBLE = new ShaderMode(var0_3[0], 3);
        ShaderMode.CAMOUFLAGE = new ShaderMode(var0_3[4], 4);
        ShaderMode.$VALUES = ShaderMode.$values();
        ShaderMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ShaderMode.$VALUES);
    }

    public static void zkmSetFlag(int n) {
        v = n;
    }

    public static int zkmGetFlag() {
        return v;
    }

    public static int zkmIntConstant() {
        return 0;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
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

