/*
 * Decompiled with CFR 0.152.
 */
package su.catlean;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.SplashOverlayEvent;
import su.catlean.gofra.Flow;
import su.catlean.render.RenderTarget;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;

public final class y0 {
    @NotNull
    public static final y0 INSTANCE;
    @NotNull
    private static final RenderTarget renderTarget;
    @NotNull
    private static final Animation animation;

    private y0() {
    }

    @Flow
    private final void onSplashOverlay(SplashOverlayEvent splashOverlayEvent) {
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-6988260737325891364L, -2436869225648496431L, MethodHandles.lookup().lookupClass()).a(278832802358832L) ^ 0x356E3D8D80D2L;
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
        byte[] byArray3 = cipher.doFinal("W\u00c0\u0018j\u00fd6\u00a08".getBytes("ISO-8859-1"));
        String string = y0.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        long[] lArray = new long[2];
        int n = 0;
        String string2 = "\u0010\u00a3\n)\u00e9\u00d8\u00ab~\nE-\u00bc\u0017\n\f\u001e";
        int n2 = 16;
        int n3 = 0;
        do {
            byte[] byArray6 = string2.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n4] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n3 < n2);
        Cipher cipher3 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory3 = SecretKeyFactory.getInstance("DES");
        byte[] byArray8 = new byte[8];
        byte[] byArray9 = byArray8;
        byArray8[0] = (byte)(l >>> 56);
        int n5 = 1;
        while (true) {
            if (n5 >= 8) {
                cipher3.init(2, (Key)secretKeyFactory3.generateSecret(new DESKeySpec(byArray9)), new IvParameterSpec(new byte[8]));
                byte[] byArray10 = cipher3.doFinal(new byte[]{-1, 56, 91, 86, -112, 98, 44, -45});
                long l3 = ((long)byArray10[0] & 0xFFL) << 56 | ((long)byArray10[1] & 0xFFL) << 48 | ((long)byArray10[2] & 0xFFL) << 40 | ((long)byArray10[3] & 0xFFL) << 32 | ((long)byArray10[4] & 0xFFL) << 24 | ((long)byArray10[5] & 0xFFL) << 16 | ((long)byArray10[6] & 0xFFL) << 8 | (long)byArray10[7] & 0xFFL;
                INSTANCE = new y0();
                renderTarget = new RenderTarget(string, (int)lArray[0], (int)lArray[1]);
                animation = new Animation(Easing.OUT_QUINT, l3);
                return;
            }
            byArray9 = byArray9;
            byArray9[n5] = (byte)(l << n5 * 8 >>> 56);
            ++n5;
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

