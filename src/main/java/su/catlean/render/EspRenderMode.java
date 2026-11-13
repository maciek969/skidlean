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

public final class EspRenderMode
extends Enum {
    public static final EspRenderMode BOX;
    public static final EspRenderMode OUTLINE;
    public static final EspRenderMode FRAME;
    public static final EspRenderMode SHADER;
    public static final EspRenderMode OFF;
    private static final /* synthetic */ EspRenderMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private EspRenderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static EspRenderMode[] values() {
        return (EspRenderMode[])$VALUES.clone();
    }

    public static EspRenderMode valueOf(String string) {
        return Enum.valueOf(EspRenderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ EspRenderMode[] $values() {
        EspRenderMode[] espRenderModeArray = new EspRenderMode[]{BOX, OUTLINE, FRAME, SHADER, OFF};
        return espRenderModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(4243346128314371937L, -2825454912494151937L, MethodHandles.lookup().lookupClass()).a(223405524443575L) ^ 20778147855817L;
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
                var5_5 = "\u0004Q\u0011\u00dfN\u00bc\u0080^\b7\u0016\u00bc\u0089U`Fa\b\u0000V\u00d3\u009c\u00b9\u00c2\f\u00fa";
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
                    var0_3[var6_4++] = EspRenderMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00b7i\u00b1\u00f3\u00fc\u00b8\u0016\u00f9\b\u0000\u00e4\u00fc\u00d2'@\u0000\u00fc";
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
                    var0_3[var6_4++] = EspRenderMode.zkmDecodeUtf8(var8_9).intern();
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
        EspRenderMode.BOX = new EspRenderMode(var0_3[4], 0);
        EspRenderMode.OUTLINE = new EspRenderMode(var0_3[3], 1);
        EspRenderMode.FRAME = new EspRenderMode(var0_3[0], 2);
        EspRenderMode.SHADER = new EspRenderMode(var0_3[2], 3);
        EspRenderMode.OFF = new EspRenderMode(var0_3[1], 4);
        EspRenderMode.$VALUES = EspRenderMode.$values();
        EspRenderMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])EspRenderMode.$VALUES);
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

