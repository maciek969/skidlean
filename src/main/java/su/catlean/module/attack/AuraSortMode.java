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

public final class AuraSortMode
extends Enum {
    public static final AuraSortMode Distance;
    public static final AuraSortMode Health;
    public static final AuraSortMode FOV;
    private static final /* synthetic */ AuraSortMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AuraSortMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AuraSortMode[] values() {
        return (AuraSortMode[])$VALUES.clone();
    }

    public static AuraSortMode valueOf(String string) {
        return Enum.valueOf(AuraSortMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8721996444822398110L, 4843903816121879210L, MethodHandles.lookup().lookupClass()).a(60272656428260L) ^ 0x4601B47A2C49L;
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
        String string = "]\u0013\u00b9\u00e7j\u00c9\u00b2\u00e8MP\u00be:]lpJ\b\u00a3\u009e\u0089\u00e4<6\u001e?\b\u00e7D0\u00a2\u0016\u009bu\u00d7";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AuraSortMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Distance = new AuraSortMode(stringArray[0], 0);
                Health = new AuraSortMode(stringArray[2], 1);
                FOV = new AuraSortMode(stringArray[1], 2);
                $VALUES = auraSortModeArray = new AuraSortMode[]{AuraSortMode.Distance, AuraSortMode.Health, AuraSortMode.FOV};
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

