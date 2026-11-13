/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.legit;

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

final class AutoBuffTriggerOnMode
extends Enum {
    public static final AutoBuffTriggerOnMode LackOfRegen;
    public static final AutoBuffTriggerOnMode Health;
    private static final AutoBuffTriggerOnMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoBuffTriggerOnMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoBuffTriggerOnMode[] values() {
        return (AutoBuffTriggerOnMode[])$VALUES.clone();
    }

    public static AutoBuffTriggerOnMode valueOf(String string) {
        return Enum.valueOf(AutoBuffTriggerOnMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoBuffTriggerOnMode[] $values() {
        AutoBuffTriggerOnMode[] autoBuffTriggerOnModeArray = new AutoBuffTriggerOnMode[]{LackOfRegen, Health};
        return autoBuffTriggerOnModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(1295002422901335120L, 6111724733008814030L, MethodHandles.lookup().lookupClass()).a(175234990209590L) ^ 0x130A265EFE90L;
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\u00a47eb\u00e8\u0098\u00036\u0010\u00f6`\u00ac\u00fc5\u0089\u0018\u00a8`\u00aa1\u009dW<\u00b1\u0002";
        int n2 = 25;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoBuffTriggerOnMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                LackOfRegen = new AutoBuffTriggerOnMode(stringArray[1], 0);
                Health = new AutoBuffTriggerOnMode(stringArray[0], 1);
                $VALUES = AutoBuffTriggerOnMode.$values();
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

