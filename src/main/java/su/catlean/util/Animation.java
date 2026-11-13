/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Animation {
    @NotNull
    private final Easing easing;
    private final long ms;
    private float I;
    private long startTime;
    private float startValue;
    private float targetValue;
    private boolean b;
    private static int J;
    private static final long a;
    private static final String c;

    public Animation(@NotNull Easing easing, long l) {
        Intrinsics.checkNotNullParameter((Object)easing, c);
        this.easing = easing;
        this.ms = l;
        this.startTime = Globals.now();
    }

    public final float getValue() {
        return this.I;
    }

    public final void setValue(float f) {
        this.I = f;
    }

    public final boolean x() {
        return this.b;
    }

    public final void N(boolean bl) {
        this.b = bl;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void animate(float var1_1) {
        var2_2 = Easing.O();
        cfr_temp_0 = this.targetValue - var1_1;
        v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
        if (var2_2 == 0) {
            v0 = v0 == false ? (float)true : (float)false;
        }
        if (v0 != false) ** GOTO lbl-1000
        this.targetValue = var1_1;
        this.reset();
        if (var2_2 != 0) lbl-1000:
        // 2 sources

        {
            v1 = (cfr_temp_1 = Globals.now() - this.ms - this.startTime) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1);
            if (var2_2 == 0) {
                v1 = v1 > 0 ? (long)1 : (long)0;
            }
            this.b = v1;
            v2 /* !! */  = this;
            if (var2_2 == 0) {
                if (v2 /* !! */ .b) {
                    this.I = var1_1;
                    return;
                } else {
                    ** GOTO lbl-1000
                }
            }
        } else lbl-1000:
        // 3 sources

        {
            v2 /* !! */  = this.easing.a().invoke((double)(Globals.now() - this.startTime) / (double)this.ms);
        }
        var3_3 = ((Number)v2 /* !! */ ).doubleValue();
        v3 = this.I;
        if (var2_2 != 0) ** GOTO lbl28
        if (v3 > this.targetValue) {
            v4 = (float)((double)this.startValue - (double)(this.startValue - this.targetValue) * var3_3);
        } else {
            v3 = this.startValue;
lbl28:
            // 2 sources

            v4 = (float)((double)v3 + (double)(this.targetValue - this.startValue) * var3_3);
        }
        this.I = v4;
    }

    public final void animateTo(float f) {
        this.startTime = Globals.now();
        this.startValue = f;
        this.b = false;
    }

    public final void reset() {
        this.animateTo(this.I);
    }

    public static void zkmSetFlag(int n) {
        J = n;
    }

    public static int zkmGetFlag() {
        return J;
    }

    public static int zkmIntConstant() {
        return 67;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(4273794009832625751L, 2453618424043736318L, MethodHandles.lookup().lookupClass()).a(151979130651768L);
        long l = a ^ 0x443B53350A1CL;
        if (Animation.zkmIntConstant() == 0) {
            Animation.zkmSetFlag(27);
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
                byte[] byArray3 = cipher.doFinal("b\u00ces\u0090c8\u00b9M".getBytes("ISO-8859-1"));
                c = Animation.zkmDecodeUtf8(byArray3).intern();
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

