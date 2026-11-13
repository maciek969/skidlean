/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import su.catlean.util.RandomUtil;
import su.catlean.util.rotation.AimSettings;
import su.catlean.zkm.ZkmKeys;

final class Spring {
    private float value;
    private float velocity;
    private static final long a = ZkmKeys.a(2360773594629889511L, 2992802427112181265L, MethodHandles.lookup().lookupClass()).a(86650162089207L);

    public Spring(float f, float f2) {
        this.value = f;
        this.velocity = f2;
    }

    public Spring(float f, float f2, DefaultConstructorMarker defaultConstructorMarker) {
        f = 0.0f;
        f2 = 0.0f;
        this(f, f2);
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float f) {
        this.value = f;
    }

    public final float getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(float f) {
        this.velocity = f;
    }

    public final float update(long l) {
        int n = (int)(((l = a ^ l) ^ 0x5F3850E204CEL) >>> 32);
        this.velocity = RandomUtil.D(-0.1f, 0.1f, false, null);
        this.value += this.velocity;
        this.value = AimSettings.INSTANCE.clamp(this.value, RangesKt.rangeTo(-1.0f, 1.0f), n);
        return this.value;
    }

    public Spring() {
        this(0.0f, 0.0f, null);
    }
}

