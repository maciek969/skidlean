/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class PanelType
extends Enum {
    public static final PanelType Config;
    public static final PanelType Window;
    private static final /* synthetic */ PanelType[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static Module[] P;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private PanelType() {
        void var2_-1;
        void var1_-1;
    }

    public static PanelType[] values() {
        return (PanelType[])$VALUES.clone();
    }

    public static PanelType valueOf(String string) {
        return Enum.valueOf(PanelType.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(4400548242696601110L, 4905836105992513256L, MethodHandles.lookup().lookupClass()).a(9513679863243L) ^ 0x63DEB61CEA13L;
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
        String string = "Zdt)%\u00f2\u00e25\b\u00f9\u0096\u00ff\u0004D\u0092\u00ee\u008c";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = PanelType.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Config = new PanelType(stringArray[0], 0);
                Window = new PanelType(stringArray[1], 1);
                $VALUES = panelTypeArray = new PanelType[]{PanelType.Config, PanelType.Window};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        P = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return P;
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

