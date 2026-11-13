/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.ElytraRecast;
import su.catlean.module.equip.AutoArmor$WhenMappings;
import su.catlean.module.equip.AutoArmorElytraPriorityMode;
import su.catlean.module.equip.AutoArmorHeadMode;
import su.catlean.module.rage.ElytraPlus;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.ArmorSlotInfo;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ItemUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.TickTimer;
import su.catlean.zkm.ZkmKeys;

public final class AutoArmor
extends Module {
    @NotNull
    public static final AutoArmor INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting head$delegate;
    @NotNull
    private static final ModeSetting body$delegate;
    @NotNull
    private static final ModeSetting tights$delegate;
    @NotNull
    private static final ModeSetting feet$delegate;
    @NotNull
    private static final ModeSetting elytraPriority$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final BooleanSetting oldVersion$delegate;
    @NotNull
    private static final BooleanSetting pauseInventory$delegate;
    @NotNull
    private static final BooleanSetting noMove$delegate;
    @NotNull
    private static final BooleanSetting ignoreCurse$delegate;
    @NotNull
    private static final BooleanSetting facePlaceBait$delegate;
    @NotNull
    private static TickTimer equipTimer;
    @NotNull
    private static TickTimer baitTimer;
    @NotNull
    private static final List armorSlots;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private AutoArmor() {
        super("auto-armor", Categories.getEquip(), null, null);
    }

    private final AutoArmorHeadMode getHead() {
        return (AutoArmorHeadMode)((Object)head$delegate.getValue(this, delegatedProperties[0]));
    }

    private final AutoArmorHeadMode getBody() {
        return (AutoArmorHeadMode)((Object)body$delegate.getValue(this, delegatedProperties[1]));
    }

    private final AutoArmorHeadMode getTights() {
        return (AutoArmorHeadMode)((Object)tights$delegate.getValue(this, delegatedProperties[2]));
    }

    private final AutoArmorHeadMode getFeet() {
        return (AutoArmorHeadMode)((Object)feet$delegate.getValue(this, delegatedProperties[3]));
    }

    private final AutoArmorElytraPriorityMode getElytraPriority() {
        return (AutoArmorElytraPriorityMode)((Object)elytraPriority$delegate.getValue(this, delegatedProperties[4]));
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final boolean getOldVersion() {
        return (Boolean)oldVersion$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getPauseInventory() {
        return (Boolean)pauseInventory$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getNoMove() {
        return (Boolean)noMove$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getIgnoreCurse() {
        return (Boolean)ignoreCurse$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getFacePlaceBait() {
        return (Boolean)facePlaceBait$delegate.getValue(this, delegatedProperties[10]);
    }

    @NotNull
    public final TickTimer getEquipTimer() {
        return equipTimer;
    }

    public final void setEquipTimer(@NotNull TickTimer tickTimer) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(tickTimer, (String)((Object)AutoArmor.zkmBootstrap("t", (int)32233, (long)(0x6E407CC421D6483EL ^ l))));
        equipTimer = tickTimer;
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = b ^ 0x7EDB5A1C7B2BL;
        int n = (int)((l ^ 0x6CCF1AAAB6EAL) >>> 48);
        if (Globals.getMc().currentScreen != null && this.getPauseInventory() && !(Globals.getMc().currentScreen instanceof ChatScreen)) {
            return;
        }
        int n2 = this.getDelay();
        if (!equipTimer.hasPassed((short)n, n2)) {
            return;
        }
        Iterable iterable = armorSlots;
        for (Object object : iterable) {
            ArmorSlotInfo armorSlotInfo = (ArmorSlotInfo)object;
            armorSlotInfo.getCurrentStack();
        }
        if (Globals.getPlayer().age % 10 == 0 && this.getFacePlaceBait()) {
            InteractionUtil.clickSlot(5, 0, SlotActionType.QUICK_MOVE, true);
            baitTimer.reset();
        }
        for (int i = 0; i < 36; ++i) {
            Object object;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(i), "getItem(...)");
            int n3 = this.getArmorScore((ItemStack)object);
            if (n3 <= 0) continue;
            for (ArmorSlotInfo armorSlotInfo : armorSlots) {
                if (this.getFacePlaceBait() && !baitTimer.hasPassed((short)n, 1) && armorSlotInfo.getEquipmentSlot() == EquipmentSlot.HEAD || armorSlotInfo.getEquipmentSlot() != (ItemUtil.isBoots((ItemStack)object) ? this.getEquipmentSlot((ItemStack)object) : EquipmentSlot.CHEST) || n3 <= armorSlotInfo.getPrevProt() || n3 <= armorSlotInfo.getNewProtection()) continue;
                armorSlotInfo.setNewSlot(i);
                armorSlotInfo.setNewProtection(n3);
            }
        }
        for (Object object : armorSlots) {
            int n4 = ((ArmorSlotInfo)object).getNewSlot();
            if (n4 == -1) continue;
            if (!(((ArmorSlotInfo)object).getPrevProt() != -1 && this.getOldVersion() || n4 >= 9)) {
                InventoryUtil.INSTANCE.switchToSlot(n4);
                InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
                PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
                InventoryUtil.INSTANCE.restoreSelectedSlot();
            } else {
                if (MovementUtil.INSTANCE.isMoving() && this.getNoMove()) {
                    return;
                }
                int n5 = n4 < 9 ? 36 + n4 : n4;
                InteractionUtil.clickSlot$default(n5, 0, null, false, 14, null);
                Object var46_15 = null;
                int n6 = 6;
                boolean bl = ((ArmorSlotInfo)object).getPrevProt() == -1;
                SlotActionType slotActionType = null;
                int n7 = 0;
                InteractionUtil.clickSlot$default(((ArmorSlotInfo)object).getArmorSlot() - 34 + (39 - ((ArmorSlotInfo)object).getArmorSlot()) * 2, n7, slotActionType, bl, n6, var46_15);
                if (((ArmorSlotInfo)object).getPrevProt() != -1) {
                    InteractionUtil.clickSlot$default(n5, 0, null, true, 6, null);
                }
            }
            equipTimer.reset();
            return;
        }
    }

    private final int getArmorScore(ItemStack itemStack) {
        if (ItemUtil.isBoots(itemStack) || Intrinsics.areEqual(itemStack.getItem(), Items.ELYTRA)) {
            RegistryEntry.Reference<Enchantment> reference;
            Object object;
            Object object2;
            int n;
            int n2;
            int n3 = 0;
            EquipmentSlot equipmentSlot = this.getEquipmentSlot(itemStack);
            if (Intrinsics.areEqual(itemStack.getItem(), Items.ELYTRA)) {
                if (!ElytraPlus.INSTANCE.isUsableElytra(itemStack)) {
                    return 0;
                }
                n2 = this.getElytraPriority() == AutoArmorElytraPriorityMode.ELYTRA_PLUS && (ElytraRecast.INSTANCE.getEnabled() || ElytraPlus.INSTANCE.getEnabled()) ? 1 : 0;
                int n4 = n = this.getElytraPriority() == AutoArmorElytraPriorityMode.IGNORE && Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA) ? 1 : 0;
                if (n2 != 0 || n != 0 || this.getElytraPriority() == AutoArmorElytraPriorityMode.ALWAYS) {
                    n3 = 999;
                }
            }
            n2 = 1;
            n = 1;
            switch (AutoArmor$WhenMappings.C[equipmentSlot.ordinal()]) {
                case 1: {
                    if (this.getHead() == AutoArmorHeadMode.PROTECTION) {
                        n = 2;
                        break;
                    }
                    n2 = 2;
                    break;
                }
                case 2: {
                    if (this.getBody() == AutoArmorHeadMode.PROTECTION) {
                        n = 2;
                        break;
                    }
                    n2 = 2;
                    break;
                }
                case 3: {
                    if (this.getTights() == AutoArmorHeadMode.PROTECTION) {
                        n = 2;
                        break;
                    }
                    n2 = 2;
                    break;
                }
                case 4: {
                    if (this.getFeet() == AutoArmorHeadMode.PROTECTION) {
                        n = 2;
                        break;
                    }
                    n2 = 2;
                }
            }
            if (itemStack.hasEnchantments()) {
                ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(itemStack);
                Intrinsics.checkNotNullExpressionValue(itemEnchantmentsComponent, "getEnchantmentsForCrafting(...)");
                object2 = itemEnchantmentsComponent;
                RegistryEntry.Reference<Enchantment> reference2 = Globals.getWorld().getRegistryManager().getOptional(Enchantments.PROTECTION.getRegistryRef()).get().getEntry(Enchantments.PROTECTION.getValue()).get();
                Intrinsics.checkNotNullExpressionValue(reference2, "get(...)");
                object = reference2;
                RegistryEntry.Reference<Enchantment> reference3 = Globals.getWorld().getRegistryManager().getOptional(Enchantments.BLAST_PROTECTION.getRegistryRef()).get().getEntry(Enchantments.BLAST_PROTECTION.getValue()).get();
                Intrinsics.checkNotNullExpressionValue(reference3, "get(...)");
                reference = reference3;
                RegistryEntry.Reference<Enchantment> reference4 = Globals.getWorld().getRegistryManager().getOptional(Enchantments.BINDING_CURSE.getRegistryRef()).get().getEntry(Enchantments.BINDING_CURSE.getValue()).get();
                Intrinsics.checkNotNullExpressionValue(reference4, "get(...)");
                RegistryEntry.Reference<Enchantment> reference5 = reference4;
                if (((ItemEnchantmentsComponent)object2).getEnchantments().contains(object)) {
                    n3 += ((ItemEnchantmentsComponent)object2).getLevel((RegistryEntry)object) * n;
                }
                if (((ItemEnchantmentsComponent)object2).getEnchantments().contains(reference)) {
                    n3 += ((ItemEnchantmentsComponent)object2).getLevel((RegistryEntry<Enchantment>)reference) * n2;
                }
                if (((ItemEnchantmentsComponent)object2).getEnchantments().contains(reference5) && this.getIgnoreCurse()) {
                    n3 = -999;
                }
            }
            if (itemStack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) {
                object2 = itemStack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
                List<AttributeModifiersComponent.Entry> list = object2;
                if (list == null || (list = ((AttributeModifiersComponent)((Object)list)).comp_2393()) == null) {
                    list = CollectionsKt.emptyList();
                }
                object = list.iterator();
                while (object.hasNext()) {
                    Intrinsics.checkNotNullExpressionValue(object.next(), "next(...)");
                    if (!Intrinsics.areEqual(((AttributeModifiersComponent.Entry)((Object)reference)).comp_2395(), EntityAttributes.ARMOR) && (!Intrinsics.areEqual(((AttributeModifiersComponent.Entry)((Object)reference)).comp_2395(), EntityAttributes.ARMOR_TOUGHNESS) || ((AttributeModifiersComponent.Entry)((Object)reference)).comp_2396().comp_2450() == null)) continue;
                    double d = ((AttributeModifiersComponent.Entry)((Object)reference)).comp_2396().comp_2449();
                    EntityAttributeModifier.Operation operation = ((AttributeModifiersComponent.Entry)((Object)reference)).comp_2396().comp_2450();
                    Intrinsics.checkNotNull(operation);
                    double d2 = switch (AutoArmor$WhenMappings.l[operation.ordinal()]) {
                        case 1 -> d;
                        case 2 -> d * Globals.getPlayer().getAttributeBaseValue(((AttributeModifiersComponent.Entry)((Object)reference)).comp_2395());
                        case 3 -> d * (double)n3;
                        default -> throw new NoWhenBranchMatchedException();
                    };
                    n3 += (int)d2;
                }
            }
            return n3;
        }
        if (!itemStack.isEmpty()) {
            return 0;
        }
        return -1;
    }

    private final EquipmentSlot getEquipmentSlot(ItemStack itemStack) {
        if (itemStack.contains(DataComponentTypes.GLIDER)) {
            return EquipmentSlot.CHEST;
        }
        EquippableComponent equippableComponent = itemStack.get(DataComponentTypes.EQUIPPABLE);
        Intrinsics.checkNotNull(equippableComponent);
        return switch (equippableComponent.comp_3174().getEntitySlotId()) {
            case 0 -> EquipmentSlot.FEET;
            case 1 -> EquipmentSlot.LEGS;
            case 2 -> EquipmentSlot.CHEST;
            default -> EquipmentSlot.HEAD;
        };
    }

    public static final int access$getArmorScore(AutoArmor autoArmor, ItemStack itemStack) {
        return autoArmor.getArmorScore(itemStack);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoArmor.b = ZkmKeys.a(-5570765986815099006L, 8395397587163049665L, MethodHandles.lookup().lookupClass()).a(29513188429153L);
                        var20 = AutoArmor.b ^ 68529692513286L;
                        AutoArmor.e = new HashMap<K, V>(13);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[40];
                        var16_4 = 0;
                        var15_5 = "J\u00b1M\u00ff.\u00bc\u008c?@\u00a0`F\u00cd{\u001e\u00b1\u0010\u00d2\u0003\u00ce7u^\u0001\u0085\u0003\u0002\"\u0082G\u0096\u00ce\u00e884\u00b0\u00d9\u00bb\u00b8\u00c7s\u00bfI\u00eaG!\u00e5\u00bd}\u009d\u0080\u0082\u00c4M5\u007fv\u001d\u0001!\u00ac\u00c6\u009fI\\sM$\u00d9\u000fA\u009f\u00fcr\u0099\u00db\u0086\u009es\u008c\u0012\u00dd\u009bX\u00be\u00f7\u0094]\u00fa\u00c603W\u00fb\u0094\u00c5\u00f8\u0006\u0001!x\u00b2\u00ae-\u00c7\u00d9\u00f3\u0018\u0080f/!\u0088b\u00cf+\u00e9\u009e+)\u009eN\u00b8\u00aa\u00ec\u001cM\u00d9,\u00c7\u000e\u00dd\u00fc\u009f\fU\u0006\u001f&\u0010\u001b\u00fc\u00deN\u00ae\u001a;\u00aa\u0007pP\u000f?\u00a6k*\u0018\u0088\u00ee\u00a0\u00d5`T\u0096ON62\\=\u0011(\u009f4\u00a2z$\u00b1h\u00ef\u00c08r\u00f0\u00c7\u0014\u00a1\u00f4\u0014\u00ee<\u00beB\u0082\u0089\u0013\u00c2t\r3A\u00fd#A\u00d5\u0005f\u00d6\u00a4[\u00cc\u00d9\u009aR\fv\u00b0\u0094\u00e8\u0007\u00e3\u0002~^\t?vK\u0095\u00bfal/\u0082\u0002p\u009b\u00f1\u0010\u00de\u008c\u00c1\u00ac\u00a7\u0010<\u0089\u00ab\u00f8\u00b5a\n\u008c\u00c9\u00d5\u0010\u00a8,\u0088S8\u00f6\u00d2\u001e\u00e8\te\"-\u009a\u00d2.(\u0099\n8\u00894B\u00cfC\u00d9p\u00d5\u00e0\u001a\u0098\u00c2r\u00e1\u00ce\u00ea+]\u000e\u00f2N\u00c2\u00db\u00fbx\u00beAm.\u00d35\u00da ZHF\u00a10\u00a4q2Eo>\u00d4\u00173\u00a7\u00f2\u008a\u00db\u0096\u00b8!\u00e7\u00e7&\\\u00fd\u001e~\u0093\u00eaS\u001c\u00c6\r\u009f@a\u00dc\u00d4#\u000f\u00b3f\u0091\u0007Pm\u0087o\u00f5\u008f\u00bc\u0086\u0018\u00f4{\u00ea\u00ef~a\u00b5P\u00d9\u0011\u00f0V|[\n\u00fe\u00e0:AY\u00a3\u0016\u00d0\u0001\u0010\u00cbDn\u00abiE\u00afL\u008d1\u009a\u00d1rk;\u00a5\u00182'B\u0006\u0012\u008b\u00cc\u009a\u0014=(\u00ca| \u0085\u009b\u00ad\u009e\u0086g\u0007N\u00c4g \u00c8\u00db\"R\u0001L!&3\u0010:\u00d8\u0012\u00ea\u00d9\u00c1\u00e5~\u00cap\u00ae\u00e9\u0081\u0095\u00c7CY\u009a\u00a0@\u001b8\u0010\u0082\u00f7\u0001\u00d8\u001d\u00f9TI\u00cc\\\u0092\u008f\u00aa\u0087\u00fb(\u0010\u00c0\u00ef\u001d\u00ea\u009d|\u001c\u0007\u0092\u00ac\u00cdcx\n\u00fc} s~i\u0007\\:D\u00d9\r\u00cb\u00a2a7\u00caN\u0081\u0094\u00ef\u007fK\u0011\"\u0086\u00a2\t}\u0012\u0004N\u0089\u0084\u00d00G.\u00fc\u0089\u008a\u00e93\u00e1I\u00169\u00f7\u008e\u0081\u008d\u0095!\u008eO\u009f&^\u00d7\u00ef\fn'\u00f8\u00ac\u001c\u00c2\u00c0m\u00f1\u00aa\u0093\u00f1\u00abR\u00fc%\u00ed\u00b5/\u00ff_\u0096\u0018 \u00a0\u00a4\u00dd\u0011\u00c4\u001a\u00ef\u00e3\u00a8MA\u00da\u0005\u00c6~Y\u00d4CZ\u0099\u00b0\u00c5\u00d5\u0000\u00ea%u\u00c7\u009ff\u00c7\u0089\u0010O\u00a9\u0003\bf\u001e\u00b5\u008f\u000b\f\u00bd\u00b4\u0016r\u00a5&(\u0016\u00c0N\u0006\u00a7\u00e70uN~\u00e0\u0006\u0099!+q\u00eeyA3\u0094\u0004b\u000bBZ\u0086\u0090\u00ef\u00a4\u00f8)tA\u001f\u008f\u0091&\u00b42 E\u001d\u0005\u00ecM\u001c\u0003\u00aaE\u00d9\u00d5\u00fa7N;\u00d8d\u000fs;\u0096\u0094\u00cd\u0095\u00e9Jv`>\u00d3\u00ff\" \u00a5\u0003\u00edT\t\u00817u\u00e8\u0003\u00cf\u00d7\u00efPT\u00ca\u00b6>y\u009c\u0012\u00a1\u00f8\u00fb\u00f6\u00f3\u0099O\u00ef\u0090\n|(\u00df\u008d\"s\u0091\u0096480\u00e6,\u00a5aN\u00af\u00df\u00cbU\u00ee\u0087Y\u0099\u00c5=\u00bcG6\u00bc\u009f{\u00a1\u00a3\u0093Jn\u0007\u00fd\u00a9\u008db\u0018\u0014h>r\u0096\u00ce\u001c\u001b\u00bc\u0006\u0005<\u00e0G7!\u009a,If+\u00ec{\u00bc\u0018R\u00bb\u0089.[\u001e_\u00ab\u00a6p\u000fmp\u00f3\u00f95\n'\u00e4\u0080\u0019\u0090\u00ca\u00a1\u0010\u0000\u008e\u009a\u00b4\u00bc\u00afgx\t,N_\u0081]\u0005\u00b1 \u00ab\u009e5h\u008e\u0006B\u00a6[v\u000e\u0088\u009a\u00f5](Dy\u00fdH\u0091\u00d5[\u00f89y\u0084r\u009d[\u00ae\u00a5\u0010\u008cD'\u001cH-Cm\u00e1\u001c9\u00b20'<{ +L\u00c0\u00bb\u0011\u0018K9\u00b9\u00dcl B\u00ae>\u00b4\u00a6\u00f8\u00b1\u00b1vJ\u000b\u00d2\u007f\u00f2\u00ddqx\u0084\u00cc8\u0018L#\u00b2+\u00ce\u0016\u00e2\u00e4\u00e8\u00d9\u0090y\u009a\u0000\u000b\u0094!\u00c02\u00fc\u0002\u00121\u00d8\u0010\u0010R\u00f0\u00c1,N\u00d8)\u00e3\u00ef\u00f5\u00beQ}e\u00ba@\u000b\u0084,Z\u00e4Y\u00bb\u00dd=\u0085vK\u00e6\u0015\u0091='\u009d\u00d9\u00d9\u0099\u00c7\u0004i\u00e5\u008b\u0082\b\\\u00e0\u0085\u00eb\u00e8f\u00c3V\u00a0g\u00b1\u00bbc\u0004\u00ec\u00df\u0097TQ\u0003n\u00c9\u00a8>\u00d1\u0086\u0092Q\u00f2q\u00f4\u00d6\u008a\u00ee\u00ccw \u0084\u00b1\u00d3^s\u00a9=\u00a1\u000b\u00bcRH\u00e4[\u0018\u009bS;8\u00b2\u00de\u0002Z\u00c9\u009eg\u00b9^*G\u00a4\u00968\u0093A\u001eh\u00b60\u00d0\u001c?7N\u00d0\u0098\u00d5z\u000e\u00c8\u000bd\u0090\u00ccJ\u008cd\u0010\u00eeF\u00ca\u000e]Im\u008c&sT\u0082\u00e6\u001a\u00bc1\u008c\u00b7d\u0004\u00a1\u00ceho\u0002\"Gv+\u00fe\u008d\u0018\u00b8\u00b9\u0080U\u00d0\u007f\u0089\u00f2'(\u001e\u00a0\u0000\u0014\u00b5\u00fb\u00af\u0004\u00dcL\u00f8\u00ff\u0082\u00a2\u0010\u00d6\u001eQ\u00dbM\u00cdhA\u00b1\u009a\u00c9\u001a\u009c\u00ecX\u0099";
                        var17_6 = 1165;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoArmor.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = ",\u00eb\u0096\u00bfl.\u0086\u00d1\u001c\u0091h\u0012M\u00fa\u0080\u00ae\u0002\u009f;H=V\u00b36\u0006\u00b5\u00b9\u00b27{\u0090\u00cf\u0018\u00c3\u00b6\u001d!\u00b5w6\u00ee\u00d4F\u00c5\u0099@\u0081\u0003\u00fb\u0016\u00eb\u00d7\u00c9\u00f5\u00af\u0006\u00b7";
                            var17_6 = 57;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoArmor.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                AutoArmor.c = var18_3;
                AutoArmor.d = new String[40];
                AutoArmor.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[25];
                var3_13 = 0;
                var4_14 = "[\u00fd9\u00054\u00f7\u0017\u00d4\u0094\f\u00e3\u00e9,\u0002U\u00e9\u00dc\u0000\\\u0092\u0004\u009f[\u00c9\u00bf\u00b0&\u009c\u00f7\u00b3\u00ed\r\u001cG7\u0097\u0018\u00be>\u00a5\u001d\u0015\u00d1\u001a\u00d94\u0093\u00e5\u0082\u0005 \u00b5\u00e2\u00d6\u00f4>\u00cf\u00cb\u0010\u00f9\u00dfQ\u0011xf\u008d\u00efw\u00d1\u00b7\u00b3w\u00f8^\u00aeo&jp\u00d10\u00d0\u0001Z\u0082\u00e8\u00e6\u0082\u00e9|\u00b7\u00101\u00f0\u001d\u0010\u001f!\u00c5|\u009b,Y\u00edQ[\u00ad\u00cc^\r\u0094\u00b8\u00a4\u007fgx\u0015\u00f6#\u009e\u00a9\u00e6\u00b7\u00f5\u0085/\u00a7\u0090<$*S\u000e\u00a4\u00c3\u00fe\u00c2\u0006\u001by\u0002_,2\u000e\u0098L\u00b8p<\u00e8\u00b0\u00cf\u0084\r]W\u00ec~-\u007fk-\u008b\u00ed\u00dc\u009e\u00a0\u00f3f\u00be\u00d6G e\u00d3\u0018\b]\u00cd~>m|";
                var5_15 = 184;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "l\u0096\tyziW\u0000\u009c\u008f\u00b8\u0019x\u00f6\u00e5\u0087";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        AutoArmor.f = var6_12;
        AutoArmor.g = new Integer[25];
        var35_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "head", "getHead()Lsu/catlean/d9;", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "body", "getBody()Lsu/catlean/d9;", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "tights", "getTights()Lsu/catlean/d9;", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "feet", "getFeet()Lsu/catlean/d9;", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "elytraPriority", "getElytraPriority()Lsu/catlean/mb;", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "oldVersion", "getOldVersion()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "pauseInventory", "getPauseInventory()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "noMove", "getNoMove()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "ignoreCurse", "getIgnoreCurse()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoArmor.class, "facePlaceBait", "getFacePlaceBait()Z", 0))};
        AutoArmor.delegatedProperties = var35_20;
        AutoArmor.INSTANCE = new AutoArmor();
        AutoArmor.head$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoArmor.INSTANCE, "head", (Enum)AutoArmorHeadMode.PROTECTION, null, null, 12, null);
        AutoArmor.body$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoArmor.INSTANCE, "body", (Enum)AutoArmorHeadMode.PROTECTION, null, null, 12, null);
        AutoArmor.tights$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoArmor.INSTANCE, "tights", (Enum)AutoArmorHeadMode.PROTECTION, null, null, 12, null);
        AutoArmor.feet$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoArmor.INSTANCE, "feet", (Enum)AutoArmorHeadMode.PROTECTION, null, null, 12, null);
        AutoArmor.elytraPriority$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoArmor.INSTANCE, "elytra-priority", (Enum)AutoArmorElytraPriorityMode.IGNORE, null, null, 12, null);
        AutoArmor.delay$delegate = SettingFactory.intSetting$default(AutoArmor.INSTANCE, "delay", 1, new IntRange(0, 10), null, null, 24, null);
        AutoArmor.oldVersion$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoArmor.INSTANCE, "old-version", false, null, null, 12, null);
        AutoArmor.pauseInventory$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoArmor.INSTANCE, "pause-inventory", false, null, null, 12, null);
        AutoArmor.noMove$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoArmor.INSTANCE, "no-move", false, null, null, 12, null);
        AutoArmor.ignoreCurse$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoArmor.INSTANCE, "ignore-curse", true, null, null, 12, null);
        AutoArmor.facePlaceBait$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoArmor.INSTANCE, "face-place-bait", false, null, null, 12, null);
        AutoArmor.equipTimer = new TickTimer();
        AutoArmor.baitTimer = new TickTimer();
        var35_20 = new ArmorSlotInfo[]{new ArmorSlotInfo(EquipmentSlot.FEET, 36, -1, -1, -1), new ArmorSlotInfo(EquipmentSlot.LEGS, 37, -1, -1, -1), new ArmorSlotInfo(EquipmentSlot.CHEST, 38, -1, -1, -1), new ArmorSlotInfo(EquipmentSlot.HEAD, 39, -1, -1, -1)};
        AutoArmor.armorSlots = CollectionsKt.listOf(var35_20);
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
    }

    private static String zkmDecodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x36DB;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ql", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            AutoArmor.d[n2] = AutoArmor.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoArmor.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/ql" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xE91;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ql", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoArmor.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoArmor.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/ql" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoArmor.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(AutoArmor.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

