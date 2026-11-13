/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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

public final class AutoCrystalPredictMode
extends Enum {
    public static final AutoCrystalPredictMode AUTO;
    public static final AutoCrystalPredictMode CUSTOM;
    public static final AutoCrystalPredictMode OFF;
    private static final AutoCrystalPredictMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalPredictMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalPredictMode[] values() {
        return (AutoCrystalPredictMode[])$VALUES.clone();
    }

    public static AutoCrystalPredictMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalPredictMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalPredictMode[] $values() {
        AutoCrystalPredictMode[] autoCrystalPredictModeArray = new AutoCrystalPredictMode[]{AUTO, CUSTOM, OFF};
        return autoCrystalPredictModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(54591576832972997L, -312313463657965551L, MethodHandles.lookup().lookupClass()).a(57005856315367L) ^ 0x33E55844F3CDL;
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
        String string = "Y\u0089\u00cbX\u0007\u00edz\u00c8\b\u00e4$\u0095\u00d8i\u00d8\u00ff\u00fa\b\u00ad\u0010P7\u00cc\u00d6]\u00b6";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoCrystalPredictMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                AUTO = new AutoCrystalPredictMode(stringArray[0], 0);
                CUSTOM = new AutoCrystalPredictMode(stringArray[1], 1);
                OFF = new AutoCrystalPredictMode(stringArray[2], 2);
                $VALUES = AutoCrystalPredictMode.$values();
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

