/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package su.catlean.mixins.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import su.catlean.interfaces.Resolvable;

@Mixin(value={OtherClientPlayerEntity.class})
public class RemotePlayerMixin
extends AbstractClientPlayerEntity
implements Resolvable {
    @Unique
    private Vec3d cachePosition = Vec3d.ZERO;

    public RemotePlayerMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Override
    public void savePosition(Vec3d position) {
        this.cachePosition = position;
    }

    @Override
    public Vec3d getSavedPosition() {
        return this.cachePosition;
    }
}

