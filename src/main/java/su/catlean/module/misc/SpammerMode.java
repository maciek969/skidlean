/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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

final class SpammerMode
extends Enum {
    public static final SpammerMode GLOBAL;
    public static final SpammerMode LOCAL;
    public static final SpammerMode WHISPERS;
    private static final SpammerMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpammerMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpammerMode[] values() {
        return (SpammerMode[])$VALUES.clone();
    }

    public static SpammerMode valueOf(String string) {
        return Enum.valueOf(SpammerMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpammerMode[] $values() {
        SpammerMode[] spammerModeArray = new SpammerMode[]{GLOBAL, LOCAL, WHISPERS};
        return spammerModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(4897489042939343776L, 2941431525160700064L, MethodHandles.lookup().lookupClass()).a(97907818341269L) ^ 0x73669BC4F87L;
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
        String string = "\u009dJ8\u0085\u00a8ob7\b\u001bb\u00b3I\u0004\u0005\u0088\u00f4\u0010\u001f(\u00f0\u00e0\u0003m\u0010\u001d\u009c\u001dg\u00aa\u00b3\n\u008c?";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SpammerMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                GLOBAL = new SpammerMode(stringArray[1], 0);
                LOCAL = new SpammerMode(stringArray[0], 1);
                WHISPERS = new SpammerMode(stringArray[2], 2);
                $VALUES = SpammerMode.$values();
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

