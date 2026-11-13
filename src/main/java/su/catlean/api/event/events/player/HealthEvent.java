/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.player;

import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class HealthEvent
extends Event {
    @NotNull
    public static final HealthEvent INSTANCE;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private static final ReadWriteProperty health$delegate;

    private HealthEvent() {
    }

    public final float getHealth() {
        return ((Number)health$delegate.getValue(this, $$delegatedProperties[0])).floatValue();
    }

    public final void setHealth(float f) {
        health$delegate.setValue(this, $$delegatedProperties[0], Float.valueOf(f));
    }

    public final boolean call(float health) {
        this.setHealth(health);
        this.setCancelled(false);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        KProperty[] kPropertyArray = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(HealthEvent.class, "health", "getHealth()F", 0))};
        $$delegatedProperties = kPropertyArray;
        INSTANCE = new HealthEvent();
        health$delegate = Delegates.INSTANCE.notNull();
    }
}

