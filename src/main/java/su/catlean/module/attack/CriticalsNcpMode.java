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

public final class CriticalsNcpMode
extends Enum {
    public static final CriticalsNcpMode NORMAL;
    public static final CriticalsNcpMode STRICT;
    public static final CriticalsNcpMode OLD;
    public static final CriticalsNcpMode NEW;
    private static final CriticalsNcpMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int[] m;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private CriticalsNcpMode() {
        void var2_-1;
        void var1_-1;
    }

    public static CriticalsNcpMode[] values() {
        return (CriticalsNcpMode[])$VALUES.clone();
    }

    public static CriticalsNcpMode valueOf(String string) {
        return Enum.valueOf(CriticalsNcpMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final CriticalsNcpMode[] $values() {
        CriticalsNcpMode[] criticalsNcpModeArray = new CriticalsNcpMode[]{NORMAL, STRICT, OLD, NEW};
        return criticalsNcpModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(3373477859934996033L, -3650849159881896032L, MethodHandles.lookup().lookupClass()).a(238607397265992L) ^ 102308242131373L;
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
                var5_5 = "\u00d1\u00b7\r\u0096\u00e2\u0080\u00cf\u00d0\b\u0099\r\u001a\u00af\u0019\u00902\u00a7";
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
                    var0_3[var6_4++] = CriticalsNcpMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00cd\u0080Qi\u00d1\u00d3\u00acG\bt\u008e\u00ec\u0098\u00f4~Hc";
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
                    var0_3[var6_4++] = CriticalsNcpMode.zkmDecodeUtf8(var8_9).intern();
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
        CriticalsNcpMode.NORMAL = new CriticalsNcpMode(var0_3[2], 0);
        CriticalsNcpMode.STRICT = new CriticalsNcpMode(var0_3[3], 1);
        CriticalsNcpMode.OLD = new CriticalsNcpMode(var0_3[1], 2);
        CriticalsNcpMode.NEW = new CriticalsNcpMode(var0_3[0], 3);
        CriticalsNcpMode.$VALUES = CriticalsNcpMode.$values();
        CriticalsNcpMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])CriticalsNcpMode.$VALUES);
    }

    public static void zkmSetFlag(int[] nArray) {
        m = nArray;
    }

    public static int[] zkmGetFlag() {
        return m;
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

