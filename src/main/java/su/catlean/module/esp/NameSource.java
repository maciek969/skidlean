/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

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

public final class NameSource
extends Enum {
    public static final NameSource Entity;
    public static final NameSource ScoreBoard;
    public static final NameSource PlayerList;
    private static final NameSource[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NameSource() {
        void var2_-1;
        void var1_-1;
    }

    public static NameSource[] values() {
        return (NameSource[])$VALUES.clone();
    }

    public static NameSource valueOf(String string) {
        return Enum.valueOf(NameSource.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NameSource[] $values() {
        NameSource[] nameSourceArray = new NameSource[]{Entity, ScoreBoard, PlayerList};
        return nameSourceArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(4366845909566078131L, -3433268952717824730L, MethodHandles.lookup().lookupClass()).a(167485264598026L) ^ 0x392912A41EE8L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[3];
        int n = 0;
        String string = "\u00d8\u0084\u0088\u00fc\u001d.X<\u00fa\u0091\u0093j\u00f7\u0092^\u0012\u0010\u00dd$\u00ea\u0099\u00ee;\t\u00b1P\u00e0\u00a2\u00d9\u001c\u00a3\u00f2R\b\" -\u00e3\u00c5\u008b\u00cd,";
        int n2 = 42;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = NameSource.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Entity = new NameSource(stringArray[2], 0);
                ScoreBoard = new NameSource(stringArray[0], 1);
                PlayerList = new NameSource(stringArray[1], 2);
                $VALUES = NameSource.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
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

