/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

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

public final class IpcOpcode
extends Enum {
    public static final IpcOpcode HANDSHAKE;
    public static final IpcOpcode FRAME;
    public static final IpcOpcode CLOSE;
    public static final IpcOpcode PING;
    public static final IpcOpcode PONG;
    private static final /* synthetic */ IpcOpcode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private IpcOpcode() {
        void var2_-1;
        void var1_-1;
    }

    public static IpcOpcode[] values() {
        return (IpcOpcode[])$VALUES.clone();
    }

    public static IpcOpcode valueOf(String string) {
        return Enum.valueOf(IpcOpcode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ IpcOpcode[] $values() {
        IpcOpcode[] ipcOpcodeArray = new IpcOpcode[]{HANDSHAKE, FRAME, CLOSE, PING, PONG};
        return ipcOpcodeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-6139529863262926312L, 6885743977374080854L, MethodHandles.lookup().lookupClass()).a(122662632911943L) ^ 121650478318488L;
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
                var5_5 = "\u00eeV\f]\u00e7\u009e|uQ41\u001dl\u009cuH\b\u00c6\u0012`\u001a\u00b6\u009c\u00a7-\bN.\u00f2X\u0096HB\u00b3";
                var7_6 = 34;
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
                    var0_3[var6_4++] = IpcOpcode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "Z\u0086\u008eo\u00dc\u00af\u00e1\u0099\b\u00bdD\u00a5\u00f69\u00fd\u00ff\u00f2";
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
                    var0_3[var6_4++] = IpcOpcode.zkmDecodeUtf8(var8_9).intern();
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
        IpcOpcode.HANDSHAKE = new IpcOpcode(var0_3[0], 0);
        IpcOpcode.FRAME = new IpcOpcode(var0_3[1], 1);
        IpcOpcode.CLOSE = new IpcOpcode(var0_3[4], 2);
        IpcOpcode.PING = new IpcOpcode(var0_3[2], 3);
        IpcOpcode.PONG = new IpcOpcode(var0_3[3], 4);
        IpcOpcode.$VALUES = IpcOpcode.$values();
        IpcOpcode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])IpcOpcode.$VALUES);
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

