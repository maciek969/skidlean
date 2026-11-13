/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={MinecraftClient.class})
public interface MinecraftAccessor {
    @Accessor(value="field_1752")
    public int getUseCooldown();

    @Accessor(value="field_1752")
    public void setUseCooldown(int var1);

    @Invoker(value="method_1583")
    public void idoItemUse();

    @Invoker(value="method_1536")
    public boolean idoAttack();
}

