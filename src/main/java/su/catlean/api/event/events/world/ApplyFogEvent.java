/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector4f
 */
package su.catlean.api.event.events.world;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4f;
import su.catlean.api.event.Event;

public final class ApplyFogEvent
extends Event {
    @NotNull
    public static final ApplyFogEvent INSTANCE = new ApplyFogEvent();
    @NotNull
    private static Vector4f colorVec = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    private static float eStart;
    private static float eEnd;
    private static float rStart;
    private static float rEnd;

    private ApplyFogEvent() {
    }

    @NotNull
    public final Vector4f getColorVec() {
        return colorVec;
    }

    public final void setColorVec(@NotNull Vector4f vector4f) {
        Intrinsics.checkNotNullParameter(vector4f, "<set-?>");
        colorVec = vector4f;
    }

    public final float getEStart() {
        return eStart;
    }

    public final void setEStart(float f) {
        eStart = f;
    }

    public final float getEEnd() {
        return eEnd;
    }

    public final void setEEnd(float f) {
        eEnd = f;
    }

    public final float getRStart() {
        return rStart;
    }

    public final void setRStart(float f) {
        rStart = f;
    }

    public final float getREnd() {
        return rEnd;
    }

    public final void setREnd(float f) {
        rEnd = f;
    }
}

