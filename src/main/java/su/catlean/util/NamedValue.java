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
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class NamedValue {
    @NotNull
    private final String name;
    @Nullable
    private Object Z;
    private static Module[] w;
    private static final long a;
    private static final String c;

    public NamedValue(@NotNull String string) {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)658099033822425912L, (long)l2);
        Intrinsics.checkNotNullParameter(string, c);
        CallSite callSite2 = callSite;
        this.name = string;
        if (callSite2 != null) {
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[5], (long)654073173961933614L, (long)l2);
        }
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Object f() {
        return this.Z;
    }

    public final void c(@Nullable Object object) {
        this.Z = object;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        w = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return w;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(472514213862730391L, 6475336801966969797L, MethodHandles.lookup().lookupClass()).a(273495747606358L);
        long l = a ^ 0x5B40B9CDFB07L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("M&S\u0095s\u00cb\u009ba".getBytes("ISO-8859-1"));
                c = NamedValue.zkmDecodeUtf8(byArray3).intern();
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

