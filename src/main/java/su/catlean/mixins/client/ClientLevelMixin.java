/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.world.AfterEntityRemove;
import su.catlean.api.event.events.world.AfterEntitySpawn;
import su.catlean.api.event.events.world.EntityRemove;
import su.catlean.api.event.events.world.EntitySpawn;
import su.catlean.mixins.accessors.ClientLevelAccessor;

@Mixin(value={ClientWorld.class})
public class ClientLevelMixin {
    @Unique
    private final MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(method={"method_53875"}, at={@At(value="HEAD")}, cancellable=true)
    public void addEntityHook(Entity entity, CallbackInfo ci) {
        if (EntitySpawn.INSTANCE.call(entity)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_53875"}, at={@At(value="RETURN")}, cancellable=true)
    public void addEntityHookPost(Entity entity, CallbackInfo ci) {
        if (AfterEntitySpawn.INSTANCE.call(entity)) {
            ci.cancel();
        }
    }

    @Inject(method={"method_2945"}, at={@At(value="HEAD")}, cancellable=true)
    public void removeEntityHook(int entityId, Entity.RemovalReason removalReason, CallbackInfo ci) {
        if (this.mc.world != null && this.mc.player != null && EntityRemove.INSTANCE.call(((ClientLevelAccessor)((Object)this.mc.world)).iGetEntityLookup().getLookup().get(entityId))) {
            ci.cancel();
        }
    }

    @Inject(method={"method_2945"}, at={@At(value="RETURN")}, cancellable=true)
    public void removeEntityHookPost(int entityId, Entity.RemovalReason removalReason, CallbackInfo ci) {
        if (this.mc.world != null && this.mc.player != null && AfterEntityRemove.INSTANCE.call(((ClientLevelAccessor)((Object)this.mc.world)).iGetEntityLookup().getLookup().get(entityId))) {
            ci.cancel();
        }
    }
}

