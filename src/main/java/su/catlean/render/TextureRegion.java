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
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.font.GlyphPage;
import su.catlean.zkm.ZkmKeys;

public final class TextureRegion {
    private final int u;
    private final int v;
    private final int width;
    private final int height;
    private final char value;
    @NotNull
    private final GlyphPage owner;
    private static final long a = ZkmKeys.a(-810995229064868910L, 6853255934013737892L, MethodHandles.lookup().lookupClass()).a(279284565300984L);
    private static final String b;

    public TextureRegion(int n, int n2, int n3, int n4, char c, @NotNull GlyphPage glyphPage) {
        Intrinsics.checkNotNullParameter(glyphPage, b);
        this.u = n;
        this.v = n2;
        this.width = n3;
        this.height = n4;
        this.value = c;
        this.owner = glyphPage;
    }

    public final int getU() {
        return this.u;
    }

    public final int getV() {
        return this.v;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final char getValue() {
        return this.value;
    }

    @NotNull
    public final GlyphPage getOwner() {
        return this.owner;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x4E4B2CFA3F44L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("1\u00e7\u00dd\u00db\u0091\u00be\u00b5\u00ab".getBytes("ISO-8859-1"));
                b = TextureRegion.zkmDecodeUtf8(byArray3).intern();
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

