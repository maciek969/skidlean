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

public final class BoxRenderMode
extends Enum {
    public static final BoxRenderMode FILL;
    public static final BoxRenderMode OUTLINE;
    public static final BoxRenderMode BOTH;
    private static final /* synthetic */ BoxRenderMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static boolean E;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private BoxRenderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static BoxRenderMode[] values() {
        return (BoxRenderMode[])$VALUES.clone();
    }

    public static BoxRenderMode valueOf(String string) {
        return Enum.valueOf(BoxRenderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(2650816473532229466L, -1143701505941476031L, MethodHandles.lookup().lookupClass()).a(76676192592645L) ^ 0x437E80D47E61L;
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
        String string = "\u0089\u00186\u0011aw\u00d6\u00c0\b$\u009bR\u00e6\u00ce\u00f8n\u0088\bY\u00d4P#r\u009c\u009eB";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = BoxRenderMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                FILL = new BoxRenderMode(stringArray[1], 0);
                OUTLINE = new BoxRenderMode(stringArray[2], 1);
                BOTH = new BoxRenderMode(stringArray[0], 2);
                $VALUES = boxRenderModeArray = new BoxRenderMode[]{BoxRenderMode.FILL, BoxRenderMode.OUTLINE, BoxRenderMode.BOTH};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        E = bl;
    }

    public static boolean zkmGetFlag() {
        return E;
    }

    public static boolean C() {
        return true;
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

