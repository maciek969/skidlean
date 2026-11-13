/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

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

public final class UniformStorage {
    @NotNull
    public static final UniformStorage INSTANCE;
    private static final int R;
    @NotNull
    private static final DynamicUniformStorage dynamicUniformStorage;

    private UniformStorage() {
    }

    public final int S() {
        return R;
    }

    @NotNull
    public final DynamicUniformStorage getDynamicUniformStorage() {
        return dynamicUniformStorage;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(743660622512850167L, 715390600819868569L, MethodHandles.lookup().lookupClass()).a(41252281676413L) ^ 0x5E433DD0DAF4L;
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
        byte[] byArray3 = cipher.doFinal("\u00a6\u00803\u00dd\u0084\u00fff\u00e9\u0083\u00fd\u0005D\u0095\t>eYE\u00e7\u0093\u00c7\u008d1q\u00e2E;\u008d\u00dcC\u00c4\u00c6".getBytes("ISO-8859-1"));
        String string = UniformStorage.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{59, 44, -112, 28, -41, -36, -42, 0});
                long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                INSTANCE = new UniformStorage();
                R = new Std140SizeCalculator().putVec2().putFloat().putInt().get();
                dynamicUniformStorage = new DynamicUniformStorage(string, R, (int)l2);
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

