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
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class GlowTexture {
    @NotNull
    private static final Identifier identifier;
    private static String b;

    @NotNull
    public static final Identifier getIdentifier() {
        return identifier;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(2256221254648424753L, 4550385900885864279L, MethodHandles.lookup().lookupClass()).a(147232830067080L) ^ 0x267AA82D009L;
        if (ZkmReferences.zkmBootstrap("\u00c5", (long)-9184302892509960686L, (long)l) == null) {
            ZkmReferences.zkmBootstrap("\u00c5", "NdmhB", (long)-9103745371701381048L, (long)l);
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\u00d9\u0088\u00c3\u0011@F\u00f3U\u0087uJ\u00e2\r<\b=\u00e4&\u00e3S&\u001f\n\u00fb\bG\u00d4\u009au\u008c\u00a5\"\u00e4";
        int n2 = 33;
        int n3 = 24;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = GlowTexture.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                identifier = Ids.id(stringArray[1], stringArray[0]);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String string) {
        b = string;
    }

    public static String zkmGetFlag() {
        return b;
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

