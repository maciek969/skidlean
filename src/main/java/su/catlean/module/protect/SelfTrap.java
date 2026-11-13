/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.TrapModule;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class SelfTrap
extends TrapModule {
    @NotNull
    public static final SelfTrap INSTANCE;
    private static final long i;
    private static final String o;

    private SelfTrap() {
        super(o, Categories.getProtect());
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = i ^ 0x795491DB52FL;
        long l2 = l ^ 0x47AB9101954CL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 32);
        this.trapPlayer(n, Globals.getPlayer(), n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        i = ZkmKeys.a(8483872058818725846L, -6435918021674741058L, MethodHandles.lookup().lookupClass()).a(49829903838760L);
        long l = i ^ 0x665475094D24L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u00ce7\u00ac\u0092\u00dd\b\u00a9\u00d4\u0014C\u008a\u00b5=\u00f73\u00c8".getBytes("ISO-8859-1"));
                o = SelfTrap.zkmDecodeUtf8(byArray3).intern();
                INSTANCE = new SelfTrap();
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

