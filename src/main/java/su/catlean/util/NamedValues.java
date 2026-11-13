/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.util.NamedValue;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class NamedValues {
    private static final long a = ZkmKeys.a(-8166355026633123887L, 5454668408976978420L, MethodHandles.lookup().lookupClass()).a(272395139683306L);
    private static final String b;

    @NotNull
    public static final NamedValue parse(@NotNull String string) {
        NamedValue namedValue;
        block0: {
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4107115841960559333L, (long)l);
            Intrinsics.checkNotNullParameter(string, b);
            CallSite callSite2 = callSite;
            namedValue = new NamedValue(string);
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)4087292782418339369L, (long)l) == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[1], (long)4112410330190497004L, (long)l);
        }
        return namedValue;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x178F73170B17L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(",\u00973\u00db\u00e8@\u001e\u00d0".getBytes("ISO-8859-1"));
                b = NamedValues.zkmDecodeUtf8(byArray3).intern();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
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

