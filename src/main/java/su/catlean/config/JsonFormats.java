/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JsonNamingStrategy;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class JsonFormats {
    @NotNull
    private static final Json json;
    private static int[] E;
    private static final long a;
    private static final String b;

    @NotNull
    public static final Json getJson() {
        return json;
    }

    public static void H() {
    }

    private static final Unit _clinit_$lambda$0(JsonBuilder jsonBuilder) {
        Intrinsics.checkNotNullParameter(jsonBuilder, b);
        jsonBuilder.setIgnoreUnknownKeys(true);
        jsonBuilder.setExplicitNulls(false);
        jsonBuilder.setNamingStrategy(JsonNamingStrategy.Builtins.getSnakeCase());
        return Unit.INSTANCE;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-4736635959034706142L, -5031677942863051632L, MethodHandles.lookup().lookupClass()).a(200207185930461L);
        long l = a ^ 0x78F3998FCEC7L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u00c0\u00ee\u00e0 \u0090\u0088Rq\u00ac\u00bc\u00c5\u000f\u00d8\b\u00b3\u00b8".getBytes("ISO-8859-1"));
                b = JsonFormats.zkmDecodeUtf8(byArray3).intern();
                json = JsonKt.Json$default(null, JsonFormats::_clinit_$lambda$0, 1, null);
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }

    public static void zkmSetFlag(int[] nArray) {
        E = nArray;
    }

    public static int[] zkmGetFlag() {
        return E;
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

