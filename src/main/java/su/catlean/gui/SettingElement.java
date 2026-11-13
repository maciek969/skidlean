/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.Setting;
import su.catlean.zkm.ZkmKeys;

public abstract class SettingElement {
    @NotNull
    private final Setting setting;
    private float x;
    private float y;
    private float width;
    private float height;
    private static boolean c;
    private static final long a;
    private static final String j;

    public SettingElement(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, j);
        this.setting = setting2;
        this.width = 116.0f;
        this.height = 13.0f;
    }

    @NotNull
    public Setting getSetting() {
        return this.setting;
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public abstract void render(@NotNull DrawContext var1, int var2, int var3, long var4, float var6);

    public abstract void mouseClicked(long var1, double var3, double var5, int var7);

    public abstract void mouseReleased(double var1, double var3);

    public abstract void keyPressed(int var1, short var2, int var3, char var4);

    public abstract void charTyped(char var1);

    public abstract boolean mouseScrolled(double var1, double var3, double var5);

    public abstract void close();

    public static void zkmSetFlag(boolean bl) {
        c = bl;
    }

    public static boolean boolean_zkmGetFlag() {
        return c;
    }

    public static boolean e() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-700852211368038802L, -3026040840279007935L, MethodHandles.lookup().lookupClass()).a(269491494342262L);
        long l = a ^ 0x745E89393750L;
        if (SettingElement.e()) {
            SettingElement.zkmSetFlag(true);
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(" \u00f6Q\u0001\u001b\u001f\u00d2C".getBytes("ISO-8859-1"));
                j = SettingElement.zkmDecodeUtf8(byArray3).intern();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
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

