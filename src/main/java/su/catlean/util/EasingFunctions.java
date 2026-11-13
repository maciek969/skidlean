/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class EasingFunctions {
    @NotNull
    public static final EasingFunctions INSTANCE;
    private static final long a;

    private EasingFunctions() {
    }

    public final float ease(float f, int n, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        block7: {
            block8: {
                float f7;
                block5: {
                    CallSite callSite;
                    block6: {
                        int n2 = 0;
                        int n3 = 0;
                        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
                        f6 = f3;
                        callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-475426801540986413L, (long)l);
                        float f8 = f == f2 ? 0 : (f > f2 ? 1 : -1);
                        if (callSite != false) {
                            f8 = f8 > 0 ? 1.0f : 0.0f;
                        }
                        f5 = f8;
                        float f9 = f6 - 0.0f;
                        f7 = f9 == 0.0f ? 0 : (f9 < 0.0f ? -1 : 1);
                        if (callSite == false) break block5;
                        if (f7 >= 0) break block6;
                        f4 = 0.0f;
                        if (n < 0) break block7;
                        f6 = f4;
                        if (callSite != false) break block8;
                    }
                    f4 = f6;
                    if (callSite == false) break block7;
                    float f10 = f4 - 1.0f;
                    f7 = f10 == 0.0f ? 0 : (f10 > 0.0f ? 1 : -1);
                }
                if (f7 > 0) {
                    f6 = 1.0f;
                }
            }
            f4 = (float)(Math.max((double)f, (double)f2) - Math.min((double)f, (double)f2));
        }
        float f11 = f4;
        float f12 = f11 * f6;
        return f2 + (f5 != false ? f12 : -f12);
    }

    public final double easeOutCirc(@NotNull Number number) {
        Intrinsics.checkNotNullParameter(number, "a");
        return Math.sqrt(1.0 - (number.doubleValue() - 1.0) * (number.doubleValue() - 1.0));
    }

    public final double easeInOutSine(@NotNull Number number) {
        Intrinsics.checkNotNullParameter(number, "x");
        return 1.0 - (0.5 * Math.sin(Math.PI * number.doubleValue() + 1.5707963267948966) + 0.5);
    }

    static {
        a = ZkmKeys.a(5311751763558104881L, 2191921631743439405L, MethodHandles.lookup().lookupClass()).a(22637797381130L);
        INSTANCE = new EasingFunctions();
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

