/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.Grouping;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import su.catlean.util.TextUtil;
import su.catlean.zkm.ZkmKeys;

public final class ItemGroup$grouping$1
implements Grouping {
    final Iterable b;
    private static final long a = ZkmKeys.a(2033904172459930695L, -1541671367760256798L, MethodHandles.lookup().lookupClass()).a(149816205595117L);
    private static final String c;

    public ItemGroup$grouping$1(Iterable iterable) {
        this.b = iterable;
    }

    public Iterator sourceIterator() {
        return this.b.iterator();
    }

    public Object keyOf(Object object) {
        ItemEntity itemEntity = (ItemEntity)object;
        Text text = itemEntity.getStack().getName();
        Intrinsics.checkNotNullExpressionValue(text, c);
        return TextUtil.getToLegacy(text);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0xA9087665ED8L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u0012\u00c0.\u00d1\u00ff\u00a3\u0089\r\u00a8u\u001c\u00ef\u0016q\u001a\u00ceO\u001d\u00f3\u00a9A]\u00f0*".getBytes("ISO-8859-1"));
                c = ItemGroup$grouping$1.zkmDecodeUtf8(byArray3).intern();
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

