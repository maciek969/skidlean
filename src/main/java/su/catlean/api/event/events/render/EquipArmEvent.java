/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import net.minecraft.util.Arm;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class EquipArmEvent
extends Event {
    @NotNull
    public static final EquipArmEvent INSTANCE;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private static final ReadWriteProperty progress$delegate;
    public static Arm arm;

    private EquipArmEvent() {
    }

    public final float getProgress() {
        return ((Number)progress$delegate.getValue(this, $$delegatedProperties[0])).floatValue();
    }

    public final void setProgress(float f) {
        progress$delegate.setValue(this, $$delegatedProperties[0], Float.valueOf(f));
    }

    @NotNull
    public final Arm getArm() {
        Arm arm = EquipArmEvent.arm;
        if (arm != null) {
            return arm;
        }
        Intrinsics.throwUninitializedPropertyAccessException("arm");
        return null;
    }

    public final void setArm(@NotNull Arm arm) {
        Intrinsics.checkNotNullParameter(arm, "<set-?>");
        EquipArmEvent.arm = arm;
    }

    public final boolean call(float swingProgress, @NotNull Arm arm) {
        Intrinsics.checkNotNullParameter(arm, "arm");
        this.setCancelled(false);
        this.setProgress(swingProgress);
        this.setArm(arm);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        KProperty[] kPropertyArray = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(EquipArmEvent.class, "progress", "getProgress()F", 0))};
        $$delegatedProperties = kPropertyArray;
        INSTANCE = new EquipArmEvent();
        progress$delegate = Delegates.INSTANCE.notNull();
    }
}

