/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.render;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.particle.Particle;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class RenderParticleEvent
extends Event {
    @NotNull
    public static final RenderParticleEvent INSTANCE = new RenderParticleEvent();
    private static Particle particle;

    private RenderParticleEvent() {
    }

    @NotNull
    public final Particle getParticle() {
        Particle particle = RenderParticleEvent.particle;
        if (particle != null) {
            return particle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("particle");
        return null;
    }

    public final boolean call(@NotNull Particle particle) {
        Intrinsics.checkNotNullParameter(particle, "particle");
        this.setCancelled(false);
        RenderParticleEvent.particle = particle;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }
}

