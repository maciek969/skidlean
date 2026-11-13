/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render.uniform;

import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gl.DynamicUniformStorage;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class BlurUniformStorage {
    @NotNull
    public static final BlurUniformStorage INSTANCE;
    private static final int P;
    @NotNull
    private static final DynamicUniformStorage dynamicUniformStorage;

    private BlurUniformStorage() {
    }

    public final int F() {
        return P;
    }

    @NotNull
    public final DynamicUniformStorage getDynamicUniformStorage() {
        return dynamicUniformStorage;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2196713395613718499L, -6436498917340909596L, MethodHandles.lookup().lookupClass()).a(260308353236829L) ^ 0x5EFC35D4E0B9L;
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
        byte[] byArray3 = cipher.doFinal("Z\u009a\u000e\u0018r\u00c1\u0090\u00db,\u00e2\u00e4\u00e4c|\u00a7\u00f1C^1B\\\u00a3\u00f5\u0089".getBytes("ISO-8859-1"));
        String string = BlurUniformStorage.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{-22, 78, 50, 120, 17, -93, -112, -48});
                long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                INSTANCE = new BlurUniformStorage();
                P = new Std140SizeCalculator().putVec2().putFloat().get();
                dynamicUniformStorage = new DynamicUniformStorage(string, P, (int)l2);
                return;
            }
            byArray5 = byArray5;
            byArray5[n] = (byte)(l << n * 8 >>> 56);
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

