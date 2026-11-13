/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ClientPlayerInteractionManager.class})
public interface InteractionManagerAccessor {
    @Invoker(value="method_2911")
    public void syncSlot();

    @Accessor(value="field_3715")
    public float getCurBlockDamageMP();

    @Accessor(value="field_3715")
    public void setCurBlockDamageMP(float var1);

    @Accessor(value="field_3721")
    public void setCarriedIndex(int var1);
}

