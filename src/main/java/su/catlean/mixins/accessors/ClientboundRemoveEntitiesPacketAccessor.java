/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntList
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package su.catlean.mixins.accessors;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={EntitiesDestroyS2CPacket.class})
public interface ClientboundRemoveEntitiesPacketAccessor {
    @Accessor(value="field_33690")
    public IntList getIds();

    @Mutable
    @Accessor(value="field_33690")
    public void setIds(IntList var1);
}

