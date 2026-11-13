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

public final class AuraTargetsMode
extends Enum {
    public static final AuraTargetsMode DEFAULT;
    public static final AuraTargetsMode ALWAYS;
    public static final AuraTargetsMode BOOLS;
    private static final /* synthetic */ AuraTargetsMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static String[] c;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AuraTargetsMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AuraTargetsMode[] values() {
        return (AuraTargetsMode[])$VALUES.clone();
    }

    public static AuraTargetsMode valueOf(String string) {
        return Enum.valueOf(AuraTargetsMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(2314452742185648538L, -1981299622445369348L, MethodHandles.lookup().lookupClass()).a(157974217895209L) ^ 0x6955F7A06996L;
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
        String string = "\u00bb\u00d8\u001d\u00d7\u00f4\u00a1\u00dd\u00e1\b\u009e{\u0095\u00af7\u00d0\u00ebT\bN\u00e6j\u00f8N\u001e\u00aa\\";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AuraTargetsMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                DEFAULT = new AuraTargetsMode(stringArray[0], 0);
                ALWAYS = new AuraTargetsMode(stringArray[1], 1);
                BOOLS = new AuraTargetsMode(stringArray[2], 2);
                $VALUES = auraTargetsModeArray = new AuraTargetsMode[]{AuraTargetsMode.DEFAULT, AuraTargetsMode.ALWAYS, AuraTargetsMode.BOOLS};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String[] stringArray) {
        c = stringArray;
    }

    public static String[] zkmGetFlag() {
        return c;
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

