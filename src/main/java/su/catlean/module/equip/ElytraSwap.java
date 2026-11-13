/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.Nameable;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.ElytraRecast;
import su.catlean.module.equip.ElytraSwapFireWorkMode;
import su.catlean.module.equip.ElytraSwapHotBarSwapMode;
import su.catlean.module.equip.ElytraSwapMode;
import su.catlean.module.equip.ElytraSwapSwapMode;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;

public final class ElytraSwap
extends Module {
    @NotNull
    public static final ElytraSwap INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BindSetting switchButton$delegate;
    @NotNull
    private static final BindSetting fireWorkButton$delegate;
    @NotNull
    private static final BooleanSetting startFireWork$delegate;
    @NotNull
    private static final ModeSetting fireWorkMode$delegate;
    @NotNull
    private static final ModeSetting swapMode$delegate;
    @NotNull
    private static final ModeSetting hotBarSwapMode$delegate;
    @NotNull
    private static final BooleanSetting swapOnLand$delegate;
    private static boolean m;
    private static boolean y;
    private static int W;
    @NotNull
    private static final Item[] swapItems;
    private static boolean e;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map h;
    private static final long[] i;
    private static final Integer[] n;
    private static final Map o;

    private ElytraSwap() {
        super("elytra-swap", Categories.getEquip(), null, null);
    }

    private final ElytraSwapMode getMode() {
        return (ElytraSwapMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final Bind getSwitchButton() {
        return (Bind)switchButton$delegate.getValue(this, delegatedProperties[1]);
    }

    private final Bind getFireWorkButton() {
        return (Bind)fireWorkButton$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getStartFireWork() {
        return (Boolean)startFireWork$delegate.getValue(this, delegatedProperties[3]);
    }

    private final ElytraSwapFireWorkMode getFireWorkMode() {
        return (ElytraSwapFireWorkMode)((Object)fireWorkMode$delegate.getValue(this, delegatedProperties[4]));
    }

    private final ElytraSwapSwapMode getSwapMode() {
        return (ElytraSwapSwapMode)((Object)swapMode$delegate.getValue(this, delegatedProperties[5]));
    }

    private final ElytraSwapHotBarSwapMode getHotBarSwapMode() {
        return (ElytraSwapHotBarSwapMode)((Object)hotBarSwapMode$delegate.getValue(this, delegatedProperties[6]));
    }

    private final boolean getSwapOnLand() {
        return (Boolean)swapOnLand$delegate.getValue(this, delegatedProperties[7]);
    }

    public final boolean r() {
        return m;
    }

    public final void K(boolean bl) {
        m = bl;
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().player != null && this.getMode() == ElytraSwapMode.ENABLE) {
            ElytraSwap.swap$default(this, true, false, null);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (this.getSwapOnLand()) {
            if (MovementUtil.INSTANCE.Z() > 15) {
                y = true;
            }
            if (!Globals.getPlayer().isGliding() && y) {
                if (Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA)) {
                    this.swap(false, true);
                }
                y = false;
            }
        }
    }

    @Flow
    private final void onAfterSendPacket(AfterSendPacket afterSendPacket) {
        if (afterSendPacket.getPacket() instanceof ClientCommandC2SPacket && ((ClientCommandC2SPacket)afterSendPacket.getPacket()).getMode() == ClientCommandC2SPacket.Mode.START_FALL_FLYING) {
            if (this.getMode() == ElytraSwapMode.BIND && this.getStartFireWork() && !m) {
                this.scheduleSwap();
            }
            m = false;
        }
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        block2: {
            if (this.getMode() != ElytraSwapMode.BIND || Globals.getMc().currentScreen != null || inputEvent.getAction() != InputEvent.Action.Press) break block2;
            if (inputEvent.getKey() == this.getSwitchButton().getKey()) {
                ElytraSwap.swap$default(this, false, false, null);
            }
            if (this.getFireWorkButton().getKey() != -1 && inputEvent.getKey() == this.getFireWorkButton().getKey() && Globals.getPlayer().isGliding()) {
                this.scheduleSwap();
            }
        }
    }

    private final void scheduleSwap() {
        Scheduler.schedule$default(Scheduler.INSTANCE, 0, (byte)-10, false, ElytraSwap::findFirework, 5, null);
    }

    public final int findElytraSlot(short s, int n, short s2) {
        long l = ((long)s << 48 | (long)n << 32 >>> 16 | (long)s2 << 48 >>> 48) ^ b;
        long l2 = l ^ 0x8AB4BB07233L;
        int n2 = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        Item[] itemArray = swapItems;
        for (Item item : itemArray) {
            Item[] itemArray2 = new Item[]{item};
            SlotResult slotResult = InventoryUtil.INSTANCE.findInInventory(itemArray2, (char)n2, l3);
            if (!slotResult.isFound()) continue;
            return slotResult.getSlot();
        }
        return -1;
    }

    private final int findChestplateSlot() {
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < 37; ++i) {
            ItemEnchantmentsComponent itemEnchantmentsComponent;
            ItemStack itemStack;
            Nameable nameable = Globals.getMc().player;
            ItemStack itemStack2 = nameable != null && (nameable = ((PlayerEntity)nameable).getInventory()) != null ? ((PlayerInventory)nameable).getStack(i) : (itemStack = null);
            if (itemStack == null || !Intrinsics.areEqual(itemStack.getItem(), Items.ELYTRA) || !ElytraRecast.INSTANCE.hasDurability(itemStack)) continue;
            int n3 = 0;
            Intrinsics.checkNotNullExpressionValue(EnchantmentHelper.getEnchantments(itemStack), "getEnchantmentsForCrafting(...)");
            Set<RegistryEntry<Enchantment>> set = itemEnchantmentsComponent.getEnchantments();
            Intrinsics.checkNotNullExpressionValue(set, "keySet(...)");
            Iterable iterable = set;
            for (Object t : iterable) {
                RegistryEntry registryEntry;
                RegistryEntry registryEntry2 = (RegistryEntry)t;
                if (registryEntry2 == null) continue;
                n3 += itemEnchantmentsComponent.getLevel(registryEntry);
            }
            if (n3 <= n2) continue;
            n2 = n3;
            n = i < 9 ? i + 36 : i;
        }
        return n;
    }

    private final void swap(boolean bl, boolean bl2) {
        Scheduler.schedule$default(Scheduler.INSTANCE, 0, (byte)-10, false, () -> ElytraSwap.swap$lambda$0(bl, bl2), 5, null);
    }

    static void swap$default(ElytraSwap elytraSwap, boolean bl, boolean bl2, Object object) {
        bl2 = false;
        elytraSwap.swap(bl, bl2);
    }

    private final void swapIntoChest(int n, boolean bl) {
        boolean bl2 = 36 <= n ? n < 45 : false;
        if (bl2) {
            if (this.getHotBarSwapMode() == ElytraSwapHotBarSwapMode.DEFAULT) {
                InteractionUtil.clickSlot(6, n - 36, SlotActionType.SWAP, true);
            } else {
                InventoryUtil.swapSlots$default(InventoryUtil.INSTANCE, n - 36, null);
            }
        } else if (this.getSwapMode() == ElytraSwapSwapMode.DEFAULT) {
            InteractionUtil.clickSlot$default(n, 0, null, false, 14, null);
            InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
            InteractionUtil.clickSlot$default(n, 0, null, true, 6, null);
        } else {
            InteractionUtil.clickSlot$default(n, 7, SlotActionType.SWAP, false, 8, null);
            InteractionUtil.clickSlot$default(6, 7, SlotActionType.SWAP, false, 8, null);
            InteractionUtil.clickSlot(n, 7, SlotActionType.SWAP, true);
        }
        if (bl && this.getStartFireWork()) {
            W = Globals.getPlayer().age;
            this.scheduleSwapBack();
        }
    }

    private final void scheduleSwapBack() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ b;
        Scheduler.schedule$default(Scheduler.INSTANCE, -1, (byte)ElytraSwap.zkmBootstrap2("v", (int)18111, (long)(0x6F513C991C6C03C1L ^ l)), false, ElytraSwap::isWearingElytra, 4, null);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == ElytraSwapMode.BIND;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == ElytraSwapMode.BIND;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == ElytraSwapMode.BIND;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == ElytraSwapMode.BIND;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() == ElytraSwapMode.BIND;
    }

    private static final void findFirework() {
        long l = b ^ 0x2A57B0225897L;
        long l2 = l ^ 0x1C4D62D03EA9L;
        int n = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        Object object = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
        SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar((Item[])object);
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
        object = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n, l3);
        InventoryUtil.INSTANCE.saveSelectedSlot();
        if (slotResult.isFound()) {
            slotResult.select();
        } else if (((SlotResult)object).isFound()) {
            InteractionUtil.clickSlot(((SlotResult)object).getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
        } else {
            ModuleMessages.S(INSTANCE, ModuleMessages.i18n(INSTANCE, "no-fireworks", new Object[0], true), false, null);
            return;
        }
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        if (INSTANCE.getFireWorkMode() == ElytraSwapFireWorkMode.SILENT) {
            InventoryUtil.INSTANCE.restoreSelectedSlot();
            if (!slotResult.isFound()) {
                InteractionUtil.clickSlot(((SlotResult)object).getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
            }
        }
    }

    private static final void swap$lambda$0(boolean bl, boolean bl2) {
        block2: {
            long l;
            long l2 = l = b ^ 0x69FC2113B76L;
            long l3 = l2 ^ 0x6A7F1B877040L;
            int n = (int)(l3 >>> 48);
            int n2 = (int)(l3 << 16 >>> 32);
            int n3 = (int)(l3 << 48 >>> 48);
            int n4 = (int)((l2 ^ 0x2DD58DF9431BL) >>> 32);
            if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA)) break block2;
            int n5 = INSTANCE.findElytraSlot((short)n, n2, (short)n3);
            if (n5 != -1) {
                Object var28_8 = null;
                int n6 = 4;
                ItemStack itemStack = Globals.getPlayer().getInventory().getStack(n5 >= 36 ? n5 - 36 : n5).copy();
                int n7 = 0;
                NotificationType notificationType = NotificationType.SUCCESS;
                ModuleMessages.notify$default(INSTANCE, ModuleMessages.i18n$default(INSTANCE, "done-chestplate", new Object[0], false, 4, null), n4, notificationType, n7, itemStack, n6, var28_8);
                INSTANCE.swapIntoChest(n5, false);
                Notifications.INSTANCE.t(true);
                Notifications.INSTANCE.t(false);
            } else {
                ModuleMessages.S(INSTANCE, ModuleMessages.i18n$default(INSTANCE, "no-chestplate", new Object[0], false, 4, null), false, null);
            }
        }
    }

    private static final void isWearingElytra() {
        block3: {
            block2: {
                if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA)) break block2;
                if (Globals.getPlayer().isGliding()) {
                    Globals.getMc().options.jumpKey.setPressed(false);
                } else {
                    Globals.getMc().options.jumpKey.setPressed((Globals.getPlayer().age - W) % 2 == 0);
                    INSTANCE.scheduleSwapBack();
                }
                break block3;
            }
            if (!Globals.getPlayer().isOnGround()) break block3;
            Globals.getMc().options.jumpKey.setPressed(true);
            INSTANCE.scheduleSwapBack();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ElytraSwap.b = ZkmKeys.a(-2684698560115584595L, 2535245307344694768L, MethodHandles.lookup().lookupClass()).a(63775117434846L);
                        var20 = ElytraSwap.b ^ 18554803705775L;
                        ElytraSwap.h = new HashMap<K, V>(13);
                        ElytraSwap.zkmSetFlag(false);
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
                        var18_3 = new String[42];
                        var16_4 = 0;
                        var15_5 = "/Sf\u001d\u0092\u00ea7\u00c7'\b\b\u00bb6\u00a3\u00f4+Z\r\u00d7\u00fb\nU\u0015pJ\u0088\u007f\u00eetK\u00fb\f J\u00dfT\u00a9b\u00d2\u0003\u00d0\u0086\"~\u00f5F\u00d3\u00ab;-a\u001ef\u00e1\u00a2D\u00ea \u00de\u0083D\u0016\u0090}* \u00cc\u00bc=&b\u008a\u0093\u00e9\u00b02\tA=\u00d8\u0080\t\u00e3\u00b4X=/\u000e|\u00c9n\u00b2\u00f7X@\u00ed\u00b0\u0091\u0018\u0084\u009d\u00d3CvK\u0095\u00d8\u00df/II\u00f0\\\u00d5\u00cfC}\u00cdK4\u009d\u00d4\u001e8\u00b8\u00be\u00b5\u001c\u00e8\u00a2\u00d7\u00d6\u000e\u00c8\u00c7\u00a7\u00d8Y\u0096F\u00ac\u00ed\u00f4\u00f1l\"x\u007f\u008b\u00bbj\u00f1ZO\u001ec_\u00e2\u00f9U\u00915A}\u00ddhU\u00b2w\u00e5R\u00bd\u0013\u00a0\u0000\u008f\u0091\u00f3\u00bb\u00dd \u0004\u0082\u00f7\u00e0\u00c01\"G;\u0017W\u00eegS\u00d6B\u00c2\u00f3\u00c7E\u00b8fL\u00c1}\u00a2\u0010+gX\u00d1\u00ac z\u0094x\u00ff\u00daY\u0006\u0019q\rk\u0010\u0017\u00a6\u00ed\u00fd\u0091\u00d4\u009e\u00c0\n\u00c9\u008c\u00f7\u00ca\u00fc\u009a\u00aaC\u00b9n\u00e5\u0018\u0013\u00c4In\u00b0\u00d5\t]\u00da\u009a\u00b5\b\u00e6I\u00e5\u00a8^\u00ecJj\u00b8\u0005F-\u0018\u00e4\u00f2|zT\u0096V\u007f\u00d6\u00f9\u0089\u00e7\u009a+.\u00df\u00b5\u00e9\u00d1\u009a\u00f1\u008e\u00c6R(\u00fe\u00e5\u00c0\u00e8\u001f\u00da\u0088\u00b0iJ+52\u00ff\u00f0\u00ceX\u00ac\u009b\u0004Yn8\u000f\u000b\\\u00b8Q\u00c4\u00f7<\u009d8\u00fed\u00a2\u001fA\u00c7\b l\u00d7,A\u009c\u00e2\u00f2\u00db\u00a5\u0085\u00f62\u0093\u00e9S5\u00c2\u00cc\u00fd\u00f1\u0092<H\u00d5\u00b6v\u0017s\u00c2\u00d6gp\u0018\u00e0\u00e3x\u009dc\u00a2\u00ab2\u00ad\u000f]\u0092\u00e6\u00dfF\fz{+\u0014\u00cd\u001c\u009d\u00e8(J\u00c1\u00b7\u0081Vcs8\u00fb_p\bJ\u00efg\u0015Ur)T\u00a3\u00d1\u00a1t\u00a7}\u0084\u00c8\u001c\u00f4\u00f0p\u00e4\u0006\u0087U\u00c6\u00a6\u00bb\u0083 \u00a6b\u00ec[e\u0088=M)?c~m)*\u00c0de\u0003\u00d9\u00a4\u00f2lb \u00b3\u0091\u00be\u00e2\u00b8bQ(\u0016t1\u00bc4\u00de\u00e9\u008d\u00ae\u0099\u00ee\u00c1&\u00a9\u00b5s\u008b\u00d6\u00deq\u008c\u00ae\u0001\u0017\u0002\u000e[\u00d7\u0001\u000b\u00c0\u000b\u00c4\u00a0\u00e5]0`\u00f8T\u0018l\u0001\u00b2\u0090\u00e6\u009f\u00a8]\u000bNg\u00aaq\u0080\u00e8Z\u0000]=y(w\u0082] \u008d,\u00cbuj\u0002\u00eb\u0089\u00ef\u0007\u00a4\u00e8\u00e5\u00d9\u00ca\u00a6\u009aH\u00f4\u00b5\u001a\u0014@\u0096[\u00c0E\u00b4El\u0018\u00f7 \u00d2\u0083\u00bb\u00a5}\u001f)\u00ed\t\u00db\r\u00fd\u009e\u0086*_E)\fZ>\u0015\u00e2\u0091b\u00d4\u0086\u00f2\u00ef\u00f1\u001cD0\u00b6\u00c0$\u00d5\u00e1\u00ed\u008dI&\u0094sY^*\u0083\u0007\u0098Z\u00dd\u00ec\u008f\u00a6xz\u00b1\u00f0cjA\u00fb\u00f8U\u00f6!\u000b\u00a7\\\u008fC\u0006\u00a1eI\u00ac\u00e1'\u00d5\u00e4\u0010avP\u0010\u0085\u00f7\u0006\u00a7\u00af\u001b\u0096\u008e$\u008c\u00b6\u009c\u00100\u009e\fuZ\u00f3\u0089\u0014S\u0093\u0094\u001e%\u001e\u00db\u0016\u00184\u009d\u00df\u0096\u00f2>\u00b7\u0081\u00da\u008d\u008c\u00a5B\\\u00cfz\u0085\u00f4a\u001be\u00c3[\rH\u00b4U\u007f\u00af\u00a6\u00a2\u00efA\u00a01\u00b5\u00ed\u00f1|\u008e\u0005\u001e\u00ca\u00d6ral\u00e8\u0004\u0002Z\f!h\u00b8o&?\u0017-\u00e6$\u001c\u00b7\u00e6\u00bc:m1\u00f9E\u00a9R\u0096b\u001f/#\u001d'.-J\u00d2@x\u00ba\u0090%.\u0084\u00f1\u00f7\u00c7i\u00c7\u0084(`\u0019\u00baA\u00d8\u0090g^\u00c4!J+\b\u00ecQe{\u0018\u0086.\u00f79\u0096B\u00d0\u00ec\u001f?\u00b1\u00ed\u00bd\u00e6\u00fd\u00f7 \u00115\u00a4%d8%z\u00f1\u00a4\u0083\u00e7@\u00a0\u00e7\u00a1\u00f6\u00eb\u009cy\u00df\u009b\u0002.*\u009c\u00f7\u00d8\u00fbF}\u00bc\u00dd\u00ba\u00b3Q\u00c9c\u008c\u00faY\u00e0FZ\u00ca[C\u00c4sW\u00c8\u0098[&\u00dcJ\u00f0e\u000f\u00e9\u00bf?\u0018\u000f\u00d7T:\u00edfT\u00d3\u0093\u00ae\u000e\u00b2Z\u00f5\u0083\u00c1I\u00d60k\u00f8\u00c8\u0085\u00c4(\u0092\u0097\u0015-\u00d2\u0097\u00d0K\u0014\u00ec8\u00e4\u000b\u00ff\u001e\u00be\u00d7\u00f5\u00c4\u00bd\u00b8A@\u0010\u009c\u00dc\u001a\u00d5V\u00dc\u0081q),\u00bd\u00af\u0094?\u00f7\u008e \u00d3F\u00f4\u00fc\u008dYh\u00f8\u0097>\u0005\n\u00f3\u00d7wN\u0082P\u00fc\u0019^g\u00e9k\u0018\u0004\u008fd\u0014Jv\u009c@\u001cn\u00a0\u00df\u0086\u0083N\u00b1\u00f7\u00dcWo\u001d\u00f0HH\u00e0~<>\b\u00c0\u00ad\u0098\u00b2\b\u001b\u008a\u0007}\t\u00d1\u0086\tX\u00e2[\u00fd\u0010\u0086\u00bd\u00f8\u00bf\u008cr\u00e6\u00c5\u0003\u009d\u009cDd;\u00e5\u00eb\u0013\u00c7\u00d3\u00c1\r1\u00b4\u00ea\u00f80\u00bf\u000fxf\u00ed\u00b4J\u00b9v\u00f6\u00fd\u00b1\u0085\u00b6\u00d8\u0012\u0017\u0099\u0014\u00ddFr\u0007\u00f7\u0012M\u0011\"vZ\u00eb\t{\u00b9\u00f9c$\u00b7\u00dbc\n\u00f2\u00f83\u000f[\u00ca\u0010\u0018\u001e\u00ad\u00b8\u00d4\u00de\u00b0\u0085\u0016\u00e2\u008f\u00a2\u00d6u\u00c4\u00ea&6\u008bb\u00b3\u009f\u00b7\u0004\u00e2(Q\u00fd\u00e7\u00a1\u0082AS\u0003X'\u00ea\u00a6\u00ad\u00031\u0080\u00a7\u0094O\u00fa\u00fb\u009e\u00a4n|>*\u00db\u0005?\u00c8\u00b7\u0083c\u00cb\u0016\u00d9\u0012\r\u008e U\u00af\u00ca\u00f4m\u00e2\u001b\u00f2L\u00e0 \u00f7K*,R\u00d2\u001e#^u)x(P\u00ef7\u0080\u00a3\u001d\u00d9\u0094\u0018\u0018J\u001b\u00b4[\u00c4\u00a8Z\u00d5\u00da\u0093\u001d-6\u00cf\u0010nG \u00ee>\u00e3\u00ad\u00bb\u0018Y\u00d8\u00a7:\u0080\u00e1E\u00de\u00ae~v\u00fd|3\u00a5\u00ed\u009dU\u00f5\u0017aj=,\u0018|\u00ae)\u0019\u00cb\u00ba\u00ff2Ji\u00ff\u00914u\u00d8Z\u00f0y]\u0004\u008c\u0002\u0091\u0090(\u00efaJ\u00ec\u00e4\u00a3\u0003\u00f6\u00b6\u00caP\u00a3jh\n\u00e0\u0094y`\u0084\u008c\u0010E\u001e\u00a5U\u00a3\u0019 \u00cd\u00a3\u00c9\n\u00b3=\u00961\u0099\u00fdn z\u00a9?\u00ef0\u00ff\u0099^\u00a8H\u0014\u00d4\u00ca\u00ec\u00fen\u00e4\u00f3\u00fe\u009f\u00aa\u00cb\u0015\u00b4\u00b4qv\u00ef\u001c\u00a6\u00cf\u00d9(\n\u0015\f\u00ed\t\u00ad\u0082/k\u0095\u00f0\u0089\u008c\u0099\u00b6\u00e6\u00edX\u00b9\u007f:\u0086S\u00ca\u00ac-\u008c\f]iZ\u00fc\u00b6\u0004 \u0019\u00fb(\u00ef\u0082\u0018#\u0018\u00c0\u000f\u00ff\u00d2\u00cc\u00f9!\u00c1\u008a\u00e2\u00f6\u0086[W\f\u00ba\u0012\u00ae\u00cd\u00e6\u00b2\u00f8";
                        var17_6 = 1407;
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ElytraSwap.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u001aO4--\u001dP\u00a0\u00f5\b\u00d8\u00c9vM+\u008b[D\u00fa\u00bf[\u0086rv\u00cc\u009d\u009a\u00d0\u0091`\u00ad\u001b\u0087\u00e6\u00f3\u00cb\u00fb\u00ef\u00d9\u00f5i{{y\u00c0\u00ddJ\u008cB\u00c18\u00cc\u009dWMb\u00ea\u001f\u00dd\u00ab=\u00e4\u00ae\u009b(I\u0017\u00a1\u00b6\u00e6\u001e0>\u00ea\u00c8t\u009a4\u0090=\u00ba\u00d6\u009a1H\u00fdT\u00d9\u0083\u009a^\b\u00a6\u00f6\u00d01\u00e2\u00e4\u00e4\u00cct\u00f9\u00c5E\u00f7";
                            var17_6 = 105;
                            var14_7 = 64;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ElytraSwap.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                ElytraSwap.c = var18_3;
                ElytraSwap.d = new String[42];
                ElytraSwap.o = new HashMap<K, V>(13);
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
                var6_12 = new long[20];
                var3_13 = 0;
                var4_14 = "\u0000o\u0002\u00c2\u0011\u00ecm\u00a3\u00dd\u0084\b\u00e4RM/B\u00a9s\u00cad.x\u00f0\u0080\u00d1g\u009d\u0090I\u001d\u0011\u00f4'ZO\u00ddv\u00b3\u00b5\u00a6\u00fa\u00f9\r\u0000\u00e0\u00bd'M/nU|}\u00af\u00faP\u00e8\u00dd\u0010PA\u00c5\u00e3u\f\u0019\u008f\u0007y[\u00f1\u00f5\u00c9\u0088\u00dc\u00bf5-\u008f-_\u00afui\u00e5{\u00d786\u00e1\u0089Nr\u00f56\u00b3\u007f\u0091\u00a0\u00ea\u00f2\u000ea\\\u00ed\u00e55\u00c8\u00c1\u000b\u00f3\u0015\u00d6y\u0099\u00ed\u00fd\u00a2\u00e2\u00cab\u009a}\u00c5\u00cd\u00ef\u00f75\u00f9\u00b1\"\u00a33\u00e99\u00fe\u00fa\u001dg\u00c9\u00fb9\u00c2\u0001";
                var5_15 = 144;
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "ylWE\u001b\u009fi\u0095@_\u0089\u008d\u00c6\u00fbE\u00a1";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        ElytraSwap.i = var6_12;
        ElytraSwap.n = new Integer[20];
        var33_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "mode", "getMode()Lsu/catlean/zp;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "switchButton", "getSwitchButton()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "fireWorkButton", "getFireWorkButton()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "startFireWork", "getStartFireWork()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "fireWorkMode", "getFireWorkMode()Lsu/catlean/yt;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "swapMode", "getSwapMode()Lsu/catlean/wj;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "hotBarSwapMode", "getHotBarSwapMode()Lsu/catlean/b_;", 0)), Reflection.property1(new PropertyReference1Impl(ElytraSwap.class, "swapOnLand", "getSwapOnLand()Z", 0))};
        ElytraSwap.delegatedProperties = var33_20;
        ElytraSwap.INSTANCE = new ElytraSwap();
        ElytraSwap.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraSwap.INSTANCE, "mode", (Enum)ElytraSwapMode.ENABLE, null, null, 12, null);
        var34_21 = null;
        var35_22 = 4;
        var38_23 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)();
        ElytraSwap.switchButton$delegate = SettingFactory.bindSetting$default(ElytraSwap.INSTANCE, "switch-button", new Bind(0, false, false, 7, null), null, var38_23, var35_22, var34_21);
        var40_24 = null;
        var41_25 = 4;
        var44_26 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)();
        ElytraSwap.fireWorkButton$delegate = SettingFactory.bindSetting$default(ElytraSwap.INSTANCE, "fire-work-button", new Bind(0, false, false, 7, null), null, var44_26, var41_25, var40_24);
        ElytraSwap.startFireWork$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraSwap.INSTANCE, "start-fire-work", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraSwap.fireWorkMode$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraSwap.INSTANCE, "fire-work-mode", (Enum)ElytraSwapFireWorkMode.SILENT, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraSwap.swapMode$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraSwap.INSTANCE, "swap-mode", (Enum)ElytraSwapSwapMode.ALT, null, null, 12, null);
        ElytraSwap.hotBarSwapMode$delegate = SettingFactory.modeSetting$default((SettingContainer)ElytraSwap.INSTANCE, "hotbar-swap-mode", (Enum)ElytraSwapHotBarSwapMode.DEFAULT, null, null, 12, null);
        ElytraSwap.swapOnLand$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraSwap.INSTANCE, "swap-on-land", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        var33_20 = new Item[6];
        Intrinsics.checkNotNullExpressionValue(Items.NETHERITE_CHESTPLATE, "NETHERITE_CHESTPLATE");
        Intrinsics.checkNotNullExpressionValue(Items.DIAMOND_CHESTPLATE, "DIAMOND_CHESTPLATE");
        Intrinsics.checkNotNullExpressionValue(Items.CHAINMAIL_CHESTPLATE, "CHAINMAIL_CHESTPLATE");
        Intrinsics.checkNotNullExpressionValue(Items.IRON_CHESTPLATE, "IRON_CHESTPLATE");
        Intrinsics.checkNotNullExpressionValue(Items.GOLDEN_CHESTPLATE, "GOLDEN_CHESTPLATE");
        Intrinsics.checkNotNullExpressionValue(Items.LEATHER_CHESTPLATE, "LEATHER_CHESTPLATE");
        ElytraSwap.swapItems = var33_20;
    }

    public static void zkmSetFlag(boolean bl) {
        e = bl;
    }

    public static boolean boolean_zkmGetFlag() {
        return e;
    }

    public static boolean s() {
        return true;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7ED1;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qc", exception);
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
            ElytraSwap.d[n2] = ElytraSwap.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ElytraSwap.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x528;
        if (ElytraSwap.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])o.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ElytraSwap.n[n2] = n3;
        }
        return ElytraSwap.n[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ElytraSwap.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ElytraSwap.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ElytraSwap.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

