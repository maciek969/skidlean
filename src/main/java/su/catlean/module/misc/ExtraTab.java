/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.PlayerListSizeEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class ExtraTab
extends Module {
    @NotNull
    public static final ExtraTab INSTANCE;
    private static final long a;
    private static final String b;

    private ExtraTab() {
        super(b, Categories.getMisc(), null, null);
    }

    @Flow
    private final void onPlayerListSize(PlayerListSizeEvent playerListSizeEvent) {
        playerListSizeEvent.cancel();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(6612110598266433187L, -5962283146217009369L, MethodHandles.lookup().lookupClass()).a(266627686077058L);
        long l = a ^ 0x2DF067904824L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u0012\u0089\u0091A\u00fd\u00cc\u008c\u00ce\u009b(6\u00e1*>a=".getBytes("ISO-8859-1"));
                b = ExtraTab.zkmDecodeUtf8(byArray3).intern();
                INSTANCE = new ExtraTab();
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

