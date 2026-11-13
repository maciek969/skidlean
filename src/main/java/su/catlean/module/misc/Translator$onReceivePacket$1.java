/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import su.catlean.module.Module;
import su.catlean.module.misc.Translator;
import su.catlean.util.ModuleMessages;
import su.catlean.zkm.ZkmKeys;

public final class Translator$onReceivePacket$1
implements Runnable {
    final int U;
    final String a;
    private static final long b = ZkmKeys.a(-8392838322034085229L, 5239270906462121891L, MethodHandles.lookup().lookupClass()).a(33777481023029L);
    private static final String c;

    public Translator$onReceivePacket$1(int n, String string) {
        this.U = n;
        this.a = string;
    }

    @Override
    public final void run() {
        Thread.sleep(this.U);
        try {
            Module module = Translator.INSTANCE;
            String string = Translator.access$getUrLang(Translator.INSTANCE).name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string, c);
            ModuleMessages.S(module, Formatting.WHITE + Translator.access$translate(Translator.INSTANCE, this.a, string), false, null);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = b ^ 0x5F21C509981DL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u000b\u00bb\u00c1=kTf5{S\u007f\u00e1\u00c4\u00f4\u00b2\u00adxr\u0096\u0017\u00c4\u00f4\u008b\u00dd".getBytes("ISO-8859-1"));
                c = Translator$onReceivePacket$1.zkmDecodeUtf8(byArray3).intern();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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

