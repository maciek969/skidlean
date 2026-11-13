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

public final class ArmorDisplayMode
extends Enum {
    public static final ArmorDisplayMode None;
    public static final ArmorDisplayMode Full;
    public static final ArmorDisplayMode Durability;
    private static final ArmorDisplayMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ArmorDisplayMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ArmorDisplayMode[] values() {
        return (ArmorDisplayMode[])$VALUES.clone();
    }

    public static ArmorDisplayMode valueOf(String string) {
        return Enum.valueOf(ArmorDisplayMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ArmorDisplayMode[] $values() {
        ArmorDisplayMode[] armorDisplayModeArray = new ArmorDisplayMode[]{None, Full, Durability};
        return armorDisplayModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(1024607904779056836L, -2216895185901628600L, MethodHandles.lookup().lookupClass()).a(124431479226509L) ^ 0x2460A74BADFFL;
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
        String string = "M\u00a3W\u00db\u0082\u00f6b\u008e\b\u00adq\u00ca\u00f0\u00fe\u00a8\u001c\u00b3\u0010\u0007\u0010\u0016\u00bb\u00b0KEx\u0083V\u00a3\u0005\r\u00ba\u0085\u00f6";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ArmorDisplayMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                None = new ArmorDisplayMode(stringArray[0], 0);
                Full = new ArmorDisplayMode(stringArray[1], 1);
                Durability = new ArmorDisplayMode(stringArray[2], 2);
                $VALUES = ArmorDisplayMode.$values();
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

