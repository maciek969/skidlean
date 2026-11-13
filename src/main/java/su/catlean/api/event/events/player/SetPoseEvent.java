/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.EntityPose;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;

public final class SetPoseEvent
extends Event {
    @NotNull
    public static final SetPoseEvent INSTANCE = new SetPoseEvent();
    @NotNull
    private static EntityPose pose = EntityPose.STANDING;

    private SetPoseEvent() {
    }

    @NotNull
    public final EntityPose getPose() {
        return pose;
    }

    public final void setPose(@NotNull EntityPose entityPose) {
        Intrinsics.checkNotNullParameter(entityPose, "<set-?>");
        pose = entityPose;
    }
}

