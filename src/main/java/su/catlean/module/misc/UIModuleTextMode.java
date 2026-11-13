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

public final class UIModuleTextMode
extends Enum {
    public static final UIModuleTextMode CENTER;
    public static final UIModuleTextMode LEFT;
    private static final UIModuleTextMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int q;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private UIModuleTextMode() {
        void var2_-1;
        void var1_-1;
    }

    public static UIModuleTextMode[] values() {
        return (UIModuleTextMode[])$VALUES.clone();
    }

    public static UIModuleTextMode valueOf(String string) {
        return Enum.valueOf(UIModuleTextMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final UIModuleTextMode[] $values() {
        UIModuleTextMode[] uIModuleTextModeArray = new UIModuleTextMode[]{CENTER, LEFT};
        return uIModuleTextModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(4032788424236711666L, -3719495614645525276L, MethodHandles.lookup().lookupClass()).a(173556589701170L) ^ 0x3AA6D05077C4L;
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
        String string = "\u0013\u00bf\u000b&\u00a6L\u008c\u001a\b\u00a7L9?\u00a3\u00a4\u00a4\u0083";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = UIModuleTextMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                CENTER = new UIModuleTextMode(stringArray[1], 0);
                LEFT = new UIModuleTextMode(stringArray[0], 1);
                $VALUES = UIModuleTextMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int n) {
        q = n;
    }

    public static int zkmGetFlag() {
        return q;
    }

    public static int zkmIntConstant() {
        return 60;
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

