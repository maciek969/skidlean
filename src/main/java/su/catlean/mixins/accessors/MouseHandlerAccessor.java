/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package su.catlean.mixins.accessors;

import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={Mouse.class})
public interface MouseHandlerAccessor {
    @Invoker(value="method_1601")
    public void iMouseClick(long var1, MouseInput var3, int var4);
}

