/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.command;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.gui.Element;
import su.catlean.zkm.ZkmKeys;

public final class Suggestions {
    @NotNull
    private final List suggestions;
    private static final long a = ZkmKeys.a(5029942605386981333L, -4741181842543453637L, MethodHandles.lookup().lookupClass()).a(90150293233075L);
    private static final String b;

    public Suggestions(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, b);
        this.suggestions = list;
    }

    @Nullable
    public final String suggest(@NotNull String string) {
        Object v1;
        block1: {
            String string2 = Element.java_lang_String_zkmGetFlag();
            Intrinsics.checkNotNullParameter(string, "s");
            Iterable iterable = this.suggestions;
            String string3 = string2;
            for (Object t : iterable) {
                String string4 = (String)t;
                if (!StringsKt.startsWith$default(string4, string, false, 2, null)) continue;
                v1 = t;
                break block1;
            }
            v1 = null;
        }
        return v1;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x195EA0842CF8L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(")M\u0017\u0099\u00de:\u0011\u0005\u00ea>\u00e2\u00a5\u00d8\u00f6/\u00db".getBytes("ISO-8859-1"));
                b = Suggestions.zkmDecodeUtf8(byArray3).intern();
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

