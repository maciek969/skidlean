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
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class SwingArmEvent
extends Event {
    @NotNull
    public static final SwingArmEvent INSTANCE;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private static final ReadWriteProperty swingProgress$delegate;
    public static MatrixStack matrices;
    @NotNull
    private static final ReadWriteProperty armX$delegate;
    public static Arm arm;

    private SwingArmEvent() {
    }

    public final float getSwingProgress() {
        return ((Number)swingProgress$delegate.getValue(this, $$delegatedProperties[0])).floatValue();
    }

    public final void setSwingProgress(float f) {
        swingProgress$delegate.setValue(this, $$delegatedProperties[0], Float.valueOf(f));
    }

    @NotNull
    public final MatrixStack getMatrices() {
        MatrixStack matrixStack = matrices;
        if (matrixStack != null) {
            return matrixStack;
        }
        Intrinsics.throwUninitializedPropertyAccessException("matrices");
        return null;
    }

    public final void setMatrices(@NotNull MatrixStack matrixStack) {
        Intrinsics.checkNotNullParameter(matrixStack, "<set-?>");
        matrices = matrixStack;
    }

    private final int getArmX() {
        return ((Number)armX$delegate.getValue(this, $$delegatedProperties[1])).intValue();
    }

    private final void setArmX(int n) {
        armX$delegate.setValue(this, $$delegatedProperties[1], n);
    }

    @NotNull
    public final Arm getArm() {
        Arm arm = SwingArmEvent.arm;
        if (arm != null) {
            return arm;
        }
        Intrinsics.throwUninitializedPropertyAccessException("arm");
        return null;
    }

    public final void setArm(@NotNull Arm arm) {
        Intrinsics.checkNotNullParameter(arm, "<set-?>");
        SwingArmEvent.arm = arm;
    }

    public final boolean call(float swingProgress, @NotNull MatrixStack matrices, int armX, @NotNull Arm arm) {
        Intrinsics.checkNotNullParameter(matrices, "matrices");
        Intrinsics.checkNotNullParameter(arm, "arm");
        this.setCancelled(false);
        this.setSwingProgress(swingProgress);
        this.setMatrices(matrices);
        this.setArm(arm);
        this.setArmX(armX);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        KProperty[] kPropertyArray = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(SwingArmEvent.class, "swingProgress", "getSwingProgress()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SwingArmEvent.class, "armX", "getArmX()I", 0))};
        $$delegatedProperties = kPropertyArray;
        INSTANCE = new SwingArmEvent();
        swingProgress$delegate = Delegates.INSTANCE.notNull();
        armX$delegate = Delegates.INSTANCE.notNull();
    }
}

