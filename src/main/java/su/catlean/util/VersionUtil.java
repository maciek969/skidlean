/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class VersionUtil {
    private static final long a = ZkmKeys.a(-8555004561154222389L, -452644591607570317L, MethodHandles.lookup().lookupClass()).a(272087165307025L);
    private static final String b;
    private static final long c;

    public static final int compareVersion(@NotNull String string, long l) {
        int n;
        block7: {
            l = a ^ l;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6735395338405520796L, (long)l);
            Intrinsics.checkNotNullParameter(string, b);
            CallSite callSite2 = callSite;
            String[] stringArray = new String[]{"+", "."};
            List list = StringsKt.split$default((CharSequence)string, stringArray, false, 0, (int)c, null);
            double d = 0.0;
            Iterable iterable = CollectionsKt.reversed(list);
            int n2 = 0;
            block0: for (Object object : iterable) {
                do {
                    int n3;
                    int n4;
                    Object t = object;
                    n = n4 = n2++;
                    Object object2 = callSite2;
                    if (l > 0L) {
                        if (object2 != null) break block7;
                        object2 = callSite2;
                    }
                    if (l > 0L) {
                        if (object2 == null) {
                            if (n < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            n3 = n4;
                        }
                        object2 = (String)t;
                    }
                    CallSite callSite3 = object2;
                    void var13_12 = n3;
                    d += Math.pow(10.0, (double)var13_12) * (double)Integer.parseInt((String)((Object)callSite3));
                    if (callSite2 == null) continue block0;
                    object = new Module[3];
                } while (l < 0L);
                ZkmReferences.zkmBootstrap("\u00c5", object, (long)6679925266948118158L, (long)l);
                break;
            }
            n = (int)d;
        }
        return n;
    }

    public static final boolean V() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x31EDED7A563BL;
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
        byte[] byArray3 = cipher.doFinal("\u00c3\u000fE\u00ab\u0097\nI\u009c".getBytes("ISO-8859-1"));
        b = VersionUtil.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{68, -67, 126, -92, 71, 46, 110, 75});
                c = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                return;
            }
            byArray5 = byArray5;
            byArray5[n] = (byte)(l << n * 8 >>> 56);
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

