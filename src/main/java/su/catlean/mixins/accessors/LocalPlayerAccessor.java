/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ClientPlayerEntity.class})
public interface LocalPlayerAccessor {
    @Invoker(value="method_3136")
    public void iSendMovementPackets();

    @Accessor(value="field_3941")
    public float getLastYaw();

    @Accessor(value="field_3925")
    public float getLastPitch();

    @Accessor(value="field_3941")
    public void setLastYaw(float var1);

    @Accessor(value="field_3925")
    public void setLastPitch(float var1);

    @Accessor(value="field_3926")
    public double getLastX();

    @Accessor(value="field_3940")
    public double getLastBaseY();

    @Accessor(value="field_3924")
    public double getLastZ();

    @Accessor(value="field_3922")
    public void setJumpRidingScale(float var1);
}

