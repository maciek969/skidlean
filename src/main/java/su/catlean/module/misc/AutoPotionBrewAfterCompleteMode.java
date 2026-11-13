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

public final class AutoPotionBrewAfterCompleteMode
extends Enum {
    public static final AutoPotionBrewAfterCompleteMode THROW;
    public static final AutoPotionBrewAfterCompleteMode TAKE;
    public static final AutoPotionBrewAfterCompleteMode NONE;
    private static final /* synthetic */ AutoPotionBrewAfterCompleteMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoPotionBrewAfterCompleteMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoPotionBrewAfterCompleteMode[] values() {
        return (AutoPotionBrewAfterCompleteMode[])$VALUES.clone();
    }

    public static AutoPotionBrewAfterCompleteMode valueOf(String string) {
        return Enum.valueOf(AutoPotionBrewAfterCompleteMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(7014956411523928880L, 8160000632042841166L, MethodHandles.lookup().lookupClass()).a(69171128252082L) ^ 0x38AAB02226E2L;
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
        String string = "\u00e0\u00ec\u009a\u009c\f\u00f3\u00df\u00b3\bg]2\u001b\u00c43\u0094g\b\u0019|\u00b0\u00a41Fx\u0001";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoPotionBrewAfterCompleteMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                THROW = new AutoPotionBrewAfterCompleteMode(stringArray[1], 0);
                TAKE = new AutoPotionBrewAfterCompleteMode(stringArray[2], 1);
                NONE = new AutoPotionBrewAfterCompleteMode(stringArray[0], 2);
                $VALUES = autoPotionBrewAfterCompleteModeArray = new AutoPotionBrewAfterCompleteMode[]{AutoPotionBrewAfterCompleteMode.THROW, AutoPotionBrewAfterCompleteMode.TAKE, AutoPotionBrewAfterCompleteMode.NONE};
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

