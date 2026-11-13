/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.MethodHandles;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AutoArmor$WhenMappings {
    public static final int[] C;
    public static final int[] l;
    private static int[] t;

    static {
        long l = ZkmKeys.a(-5274999213636242509L, -1873037450010107066L, MethodHandles.lookup().lookupClass()).a(205484714076169L) ^ 0x14B6DED5F60AL;
        int[] nArray = new int[EquipmentSlot.values().length];
        ZkmReferences.zkmBootstrap("\u00c5", null, (long)5620884583187413771L, (long)l);
        try {
            nArray[EquipmentSlot.HEAD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[EquipmentSlot.BODY.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[EquipmentSlot.LEGS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[EquipmentSlot.FEET.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        C = nArray;
        nArray = new int[EntityAttributeModifier.Operation.values().length];
        try {
            nArray[EntityAttributeModifier.Operation.ADD_VALUE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        AutoArmor$WhenMappings.l = nArray;
    }

    public static void zkmSetFlag(int[] nArray) {
        t = nArray;
    }

    public static int[] zkmGetFlag() {
        return t;
    }
}

