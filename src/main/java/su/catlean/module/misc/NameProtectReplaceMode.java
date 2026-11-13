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
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class NameProtectReplaceMode
extends Enum {
    public static final NameProtectReplaceMode ONLY_SELF;
    public static final NameProtectReplaceMode FRIENDS;
    public static final NameProtectReplaceMode ALL;
    private static final NameProtectReplaceMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static Module[] N;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NameProtectReplaceMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NameProtectReplaceMode[] values() {
        return (NameProtectReplaceMode[])$VALUES.clone();
    }

    public static NameProtectReplaceMode valueOf(String string) {
        return Enum.valueOf(NameProtectReplaceMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NameProtectReplaceMode[] $values() {
        NameProtectReplaceMode[] nameProtectReplaceModeArray = new NameProtectReplaceMode[]{ONLY_SELF, FRIENDS, ALL};
        return nameProtectReplaceModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-8157752009596557228L, -3941876658401929917L, MethodHandles.lookup().lookupClass()).a(16001232321969L) ^ 0x1A1070996A1EL;
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
        String string = "9\u00f5\u00bc\u00cf\u00bb\u00f8\u00fa\u00d1\u0010aIR\u00f9\u0098\u00aeX\u00b92\u00b9x)\u00e7\u00ed\u00e9\u00a5\bCT\u009f\u00f8\u00f9I\u00a1\u00a3";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = NameProtectReplaceMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                ONLY_SELF = new NameProtectReplaceMode(stringArray[1], 0);
                FRIENDS = new NameProtectReplaceMode(stringArray[0], 1);
                ALL = new NameProtectReplaceMode(stringArray[2], 2);
                $VALUES = NameProtectReplaceMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        N = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return N;
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

