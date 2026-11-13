/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelPipeline
 */
package su.catlean.api.event.events.network;

import io.netty.channel.ChannelPipeline;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import net.minecraft.network.NetworkSide;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class AddHandlersEvent
extends Event {
    @NotNull
    public static final AddHandlersEvent INSTANCE;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static AtomicReference<ChannelPipeline> pipeline;
    private static NetworkSide side;
    @NotNull
    private static final ReadWriteProperty local$delegate;

    private AddHandlersEvent() {
    }

    @NotNull
    public final AtomicReference<ChannelPipeline> getPipeline() {
        AtomicReference<ChannelPipeline> atomicReference = pipeline;
        if (atomicReference != null) {
            return atomicReference;
        }
        Intrinsics.throwUninitializedPropertyAccessException("pipeline");
        return null;
    }

    @NotNull
    public final NetworkSide getSide() {
        NetworkSide networkSide = side;
        if (networkSide != null) {
            return networkSide;
        }
        Intrinsics.throwUninitializedPropertyAccessException("side");
        return null;
    }

    public final boolean getLocal() {
        return (Boolean)local$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final void setLocal(boolean bl) {
        local$delegate.setValue(this, $$delegatedProperties[0], bl);
    }

    public final boolean call(@NotNull AtomicReference<ChannelPipeline> pipeline, @NotNull NetworkSide side, boolean local) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        Intrinsics.checkNotNullParameter((Object)side, "side");
        this.setCancelled(false);
        AddHandlersEvent.pipeline = pipeline;
        AddHandlersEvent.side = side;
        this.setLocal(local);
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        KProperty[] kPropertyArray = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddHandlersEvent.class, "local", "getLocal()Z", 0))};
        $$delegatedProperties = kPropertyArray;
        INSTANCE = new AddHandlersEvent();
        local$delegate = Delegates.INSTANCE.notNull();
    }
}

