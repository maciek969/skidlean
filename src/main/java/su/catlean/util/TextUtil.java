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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.LegacyTextConverter;
import su.catlean.zkm.ZkmKeys;

public final class TextUtil {
    private static int[] b;
    private static final long a;
    private static final String c;

    @NotNull
    public static final String getToLegacy(@NotNull Text text) {
        Intrinsics.checkNotNullParameter(text, c);
        return LegacyTextConverter.INSTANCE.toLegacy(text);
    }

    public static void zkmSetFlag(int[] nArray) {
        b = nArray;
    }

    public static int[] zkmGetFlag() {
        return b;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-8951303050468135118L, 8625399071338096972L, MethodHandles.lookup().lookupClass()).a(133516138501493L);
        long l = a ^ 0x514B62ED4D1EL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("^\u001b\u00c0d\u00fbDW\u0097".getBytes("ISO-8859-1"));
                c = TextUtil.zkmDecodeUtf8(byArray3).intern();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
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

