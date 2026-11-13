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

public final class StorageEspShaderMode
extends Enum {
    public static final StorageEspShaderMode DEFAULT;
    public static final StorageEspShaderMode LINES;
    public static final StorageEspShaderMode DOTS;
    private static final StorageEspShaderMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int[] q;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private StorageEspShaderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static StorageEspShaderMode[] values() {
        return (StorageEspShaderMode[])$VALUES.clone();
    }

    public static StorageEspShaderMode valueOf(String string) {
        return Enum.valueOf(StorageEspShaderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final StorageEspShaderMode[] $values() {
        StorageEspShaderMode[] storageEspShaderModeArray = new StorageEspShaderMode[]{DEFAULT, LINES, DOTS};
        return storageEspShaderModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8815998564268878848L, 6904128356196001765L, MethodHandles.lookup().lookupClass()).a(8432005734363L) ^ 0x52CD676F4BFCL;
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
        String string = "\u00d9=k;\u0094C\u0012\u0001\b\t\u008d\u00e2\u0089_\u00a1\u000f#\b\u00f1\u00e7\u00c7/-~!\u00ec";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = StorageEspShaderMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                DEFAULT = new StorageEspShaderMode(stringArray[2], 0);
                LINES = new StorageEspShaderMode(stringArray[1], 1);
                DOTS = new StorageEspShaderMode(stringArray[0], 2);
                $VALUES = StorageEspShaderMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int[] nArray) {
        q = nArray;
    }

    public static int[] zkmGetFlag() {
        return q;
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

