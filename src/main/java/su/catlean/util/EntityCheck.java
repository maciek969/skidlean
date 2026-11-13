/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import su.catlean.zkm.ZkmReferences;

public final class EntityCheck
extends Enum {
    public static final EntityCheck NONE;
    public static final EntityCheck ALL;
    public static final EntityCheck ONLY_CRYSTALS;
    private static final /* synthetic */ EntityCheck[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static int[] X;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private EntityCheck() {
        void var2_-1;
        void var1_-1;
    }

    public static EntityCheck[] values() {
        return (EntityCheck[])$VALUES.clone();
    }

    public static EntityCheck valueOf(String string) {
        return Enum.valueOf(EntityCheck.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-6667116833853643612L, -204609502276127754L, MethodHandles.lookup().lookupClass()).a(103971763783115L) ^ 0x5B5CA0AF2C0EL;
        if (ZkmReferences.zkmBootstrap("\u00c5", (long)-1263930476504196349L, (long)l) == null) {
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[1], (long)-1166022503943370959L, (long)l);
        }
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
        String string = "\u009c\u008f\u0002\u009dr\u00e4\"\u00f3\bE\u0019\u0093\u00a1&\u0012W\u00ef\u0010\u007f\u00b0s\u00fa\"]\u0015\u00a4\u00c9\u0015\u00fbLDVp\u0003";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = EntityCheck.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NONE = new EntityCheck(stringArray[1], 0);
                ALL = new EntityCheck(stringArray[0], 1);
                ONLY_CRYSTALS = new EntityCheck(stringArray[2], 2);
                $VALUES = entityCheckArray = new EntityCheck[]{EntityCheck.NONE, EntityCheck.ALL, EntityCheck.ONLY_CRYSTALS};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int[] nArray) {
        X = nArray;
    }

    public static int[] zkmGetFlag() {
        return X;
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

