/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package su.catlean.mixins.accessors;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={LivingEntity.class})
public interface LivingEntityAccessor {
    @Accessor(value="field_6273")
    public int getLastAttackedTicks();

    @Accessor(value="field_6228")
    public int getLastJumpCooldown();

    @Accessor(value="field_6228")
    public void setLastJumpCooldown(int var1);
}

