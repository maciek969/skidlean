/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage.elytra;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.input.Input;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.mixins.accessors.ClientInputAccessor;
import su.catlean.module.rage.ElytraPlus;
import su.catlean.module.rage.ElytraPlusMode;
import su.catlean.module.rage.elytra.FireWorkElytra$WhenMappings;
import su.catlean.util.ElytraListener;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class FireWorkElytra
implements ElytraListener {
    @NotNull
    public static final FireWorkElytra INSTANCE;
    private static boolean o;
    private static boolean E;
    private static float currentBoost;
    @Nullable
    private static ItemStack chestStack;
    @Nullable
    private static ItemStack fireworkStack;
    @NotNull
    private static Item chestItem;
    @NotNull
    private static Item fireworkItem;
    private static int chestSlot;
    private static int fireworkSlot;
    private static long b;
    private static final long c;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private FireWorkElytra() {
    }

    public final boolean v() {
        return o;
    }

    public final void H(boolean bl) {
        o = bl;
    }

    public final boolean B() {
        return E;
    }

    public final void Z(boolean bl) {
        E = bl;
    }

    public final float getCurrentBoost() {
        return currentBoost;
    }

    public final void setCurrentBoost(float f) {
        currentBoost = f;
    }

    @Nullable
    public final ItemStack getChestStack() {
        return chestStack;
    }

    public final void setChestStack(@Nullable ItemStack itemStack) {
        chestStack = itemStack;
    }

    @Nullable
    public final ItemStack getFireworkStack() {
        return fireworkStack;
    }

    public final void setFireworkStack(@Nullable ItemStack itemStack) {
        fireworkStack = itemStack;
    }

    @NotNull
    public final Item getChestItem() {
        return chestItem;
    }

    public final void setChestItem(@NotNull Item item) {
        long l = 0L;
        l = c ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)FireWorkElytra.zkmBootstrap("n", (int)27448, (long)(0x7D150061AD190DFL ^ l))));
        chestItem = item;
    }

    @NotNull
    public final Item getFireworkItem() {
        return fireworkItem;
    }

    public final void setFireworkItem(@NotNull Item item) {
        long l = 0L;
        l = c ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)FireWorkElytra.zkmBootstrap("n", (int)11305, (long)(0x3F61D14B391EF222L ^ l))));
        fireworkItem = item;
    }

    public final int getChestSlot() {
        return chestSlot;
    }

    public final void setChestSlot(int n) {
        chestSlot = n;
    }

    public final int getFireworkSlot() {
        return fireworkSlot;
    }

    public final void setFireworkSlot(int n) {
        fireworkSlot = n;
    }

    public final long Z() {
        return b;
    }

    public final void f(long l) {
        b = l;
    }

    @Override
    public void onPreSync(long l, @NotNull PreSyncEvent preSyncEvent) {
        int n = (int)((l ^ 0x242D9D6B0AC4L) >>> 32);
        Intrinsics.checkNotNullParameter(preSyncEvent, "e");
        if (ElytraPlus.INSTANCE.getRotate()) {
            if (Globals.getMc().options.jumpKey.isPressed() && Globals.getPlayer().isGliding() && o) {
                Globals.getPlayer().setPitch(-45.0f);
            }
            if (Globals.getMc().options.sneakKey.isPressed() && Globals.getPlayer().isGliding() && o) {
                Globals.getPlayer().setPitch(45.0f);
            }
            Globals.getPlayer().setYaw(MovementUtil.INSTANCE.b(n));
        }
        if (!MovementUtil.INSTANCE.isMoving() && Globals.getMc().options.jumpKey.isPressed() && Globals.getPlayer().isGliding() && o) {
            Globals.getPlayer().setPitch(-90.0f);
        }
        if (MovementUtil.INSTANCE.Z() < 5 && !Globals.getPlayer().isOnGround()) {
            Globals.getPlayer().setPitch(-45.0f);
        }
    }

    @Override
    public void onPreElytra(@NotNull PreElytraEvent preElytraEvent, long l) {
        Intrinsics.checkNotNullParameter(preElytraEvent, "e");
        if (MovementUtil.INSTANCE.Z() < 4) {
            ElytraPlus.INSTANCE.setSavedPitch(Globals.getPlayer().getPitch());
            Globals.getPlayer().setPitch(-45.0f);
        }
    }

    @Override
    public void onAfterElytra(@NotNull AfterElytraEvent afterElytraEvent) {
        block0: {
            Intrinsics.checkNotNullParameter(afterElytraEvent, "e");
            if (MovementUtil.INSTANCE.Z() >= 4) break block0;
            Globals.getPlayer().setPitch(ElytraPlus.INSTANCE.getSavedPitch());
        }
    }

    @Override
    public void onMove(@NotNull MoveEvent moveEvent, long l) {
        long l2 = l ^ 0x47DC97068DFBL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        Intrinsics.checkNotNullParameter(moveEvent, "e");
        if (Globals.getPlayer().isGliding() && o) {
            double d;
            double d2;
            Object object;
            if (Globals.getPlayer().horizontalCollision || Globals.getPlayer().verticalCollision) {
                ElytraPlus.INSTANCE.setCurrentSpeed(0.0f);
                currentBoost = 0.0f;
            }
            if (MovementUtil.INSTANCE.Z() < 4) {
                moveEvent.setY(0.2);
                moveEvent.cancel();
                return;
            }
            if (Globals.getMc().options.jumpKey.isPressed()) {
                var29_5 = ElytraPlus.INSTANCE.getYSpeed();
                object = moveEvent;
                float f = currentBoost += 9.0f;
                d2 = f / 100.0f;
                d = 1.0;
                ((MoveEvent)object).setY(var29_5 * Math.min(d2, d));
            } else if (Globals.getMc().options.sneakKey.isPressed()) {
                var29_5 = -ElytraPlus.INSTANCE.getYSpeed();
                object = moveEvent;
                float f = currentBoost += 9.0f;
                d2 = f / 100.0f;
                d = 1.0;
                ((MoveEvent)object).setY(var29_5 * Math.min(d2, d));
            } else {
                switch (FireWorkElytra$WhenMappings.D[ElytraPlus.INSTANCE.getAntiKick().ordinal()]) {
                    case 1: {
                        moveEvent.setY(Globals.getPlayer().age % 2 == 0 ? 0.08 : -0.08);
                        break;
                    }
                    case 2: {
                        moveEvent.setY(-0.08);
                        break;
                    }
                    case 3: {
                        moveEvent.setY(0.0);
                        break;
                    }
                    default: {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
            if (!MovementUtil.INSTANCE.isMoving()) {
                ElytraPlus.INSTANCE.setCurrentSpeed(0.0f);
            }
            if (Globals.getPlayer().input.getMovementInput().x > 0.0f) {
                Input input = Globals.getPlayer().input;
                Intrinsics.checkNotNull(input, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
                ((ClientInputAccessor)((Object)input)).setMoveVector(new Vec2f(1.0f, Globals.getPlayer().input.getMovementInput().y));
            } else if (Globals.getPlayer().input.getMovementInput().x < 0.0f) {
                Input input = Globals.getPlayer().input;
                Intrinsics.checkNotNull(input, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
                ((ClientInputAccessor)((Object)input)).setMoveVector(new Vec2f(-1.0f, Globals.getPlayer().input.getMovementInput().y));
            }
            double d3 = ElytraPlus.INSTANCE.getXzSpeed();
            MoveEvent moveEvent2 = moveEvent;
            object = MovementUtil.INSTANCE;
            ElytraPlus.INSTANCE.setCurrentSpeed(ElytraPlus.INSTANCE.getCurrentSpeed() + 9.0f);
            float f = ElytraPlus.INSTANCE.getCurrentSpeed();
            d2 = f / 100.0f;
            d = 1.0;
            double d4 = d3 * Math.min(d2, d);
            ((MovementUtil)object).setSpeed(n, (short)n2, moveEvent2, d4);
            if (ElytraPlus.INSTANCE.getStayMad() && !this.isAboveVoid() && MovementUtil.INSTANCE.Z() > 10) {
                moveEvent.setY(0.42);
            }
            moveEvent.cancel();
        }
    }

    @Override
    public void onAfterSendPacket(long l, @NotNull AfterSendPacket afterSendPacket) {
        block0: {
            long l2 = l ^ 0x5B0D4F8D2EACL;
            Intrinsics.checkNotNullParameter(afterSendPacket, "event");
            if (!(afterSendPacket.getPacket() instanceof ClientCommandC2SPacket) || ElytraPlus.INSTANCE.getMode() != ElytraPlusMode.FIRE_WORK || ((ClientCommandC2SPacket)afterSendPacket.getPacket()).getMode() != ClientCommandC2SPacket.Mode.START_FALL_FLYING) break block0;
            this.useFirework(l2, false);
        }
    }

    private final void useFirework(long l, boolean bl) {
        long l2 = (l = c ^ l) ^ 0x2AF3F121F217L;
        int n = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        if (bl && (float)(System.currentTimeMillis() - b) < ElytraPlus.INSTANCE.getFireDelay() * 1000.0f) {
            return;
        }
        if (bl && !Globals.getPlayer().isGliding()) {
            return;
        }
        if (!bl && MovementUtil.INSTANCE.Z() > 1) {
            return;
        }
        int n2 = this.getFireworkSlot((short)n, l3);
        if (n2 == -1) {
            fireworkSlot = -1;
            return;
        }
        fireworkSlot = n2;
        boolean bl2 = Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET);
        int n3 = Globals.getPlayer().getInventory().getSelectedSlot();
        if (!bl2 && n3 != n2) {
            InventoryUtil.INSTANCE.selectSlot(n2);
        }
        InteractionUtil.useItem$default(bl2 ? Hand.OFF_HAND : Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        if (!bl2 && n3 != n2) {
            InventoryUtil.INSTANCE.selectSlot(n3);
        }
        o = true;
        b = System.currentTimeMillis();
    }

    @Override
    public void onReceivePacket(@NotNull ReceivePacket receivePacket, long l, char c) {
        long l2 = l << 16 | (long)c << 48 >>> 48;
        long l3 = l2 ^ 0x67739B8CEC64L;
        Intrinsics.checkNotNullParameter(receivePacket, "e");
        if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket) {
            ElytraPlus.INSTANCE.setCurrentSpeed(0.0f);
            currentBoost = 0.0f;
            if (ElytraPlus.INSTANCE.getDisableOnFlag() && ElytraPlus.INSTANCE.getMode() == ElytraPlusMode.FIRE_WORK) {
                Object[] objectArray = new Object[]{};
                String string = "modules.common.flag-disable";
                ElytraPlus.INSTANCE.disableWithReason(l3, I18n.INSTANCE.translate(string, objectArray));
            }
        }
    }

    @Override
    public void onPlayerUpdate(int n, @NotNull PlayerUpdateEvent playerUpdateEvent, char c, char c2) {
        boolean bl;
        long l;
        long l2 = l = (long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48;
        long l3 = l2 ^ 0x357F209F46C5L;
        long l4 = l2 ^ 0x2A4E51442EBDL;
        long l5 = l2 ^ 0x2A10C0EE651BL;
        int n2 = (int)(l5 >>> 32);
        int n3 = (int)(l5 << 32 >>> 48);
        int n4 = (int)(l5 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        boolean bl2 = Globals.getWorld().isAir(BlockPos.ofFloored(EntityUtil.getPos(Globals.getPlayer())));
        boolean bl3 = bl = this.isAboveAir() && bl2 && Globals.getPlayer().getVelocity().getY() < 0.0;
        if (MovementUtil.INSTANCE.getFallDistance() > 0.0 && bl2 || bl) {
            this.equipElytra(l4);
        } else if (Globals.getPlayer().isOnGround()) {
            E = false;
            return;
        }
        if (!MovementUtil.INSTANCE.isMoving()) {
            ElytraPlus.INSTANCE.setCurrentSpeed(0.0f);
        }
        if (!this.hasFirework(n2, (short)n3, (char)n4)) {
            return;
        }
        if (!Globals.getPlayer().isGliding() && !E && Globals.getPlayer().getVelocity().getY() < 0.0) {
            PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            E = true;
        }
        if (MovementUtil.INSTANCE.Z() < 4) {
            Globals.getMc().options.jumpKey.setPressed(false);
        }
        this.useFirework(l3, true);
    }

    @Override
    public void onEnable(char c, int n, short s) {
        long l;
        long l2 = l = (long)c << 48 | (long)n << 32 >>> 16 | (long)s << 48 >>> 48;
        long l3 = l2 ^ 0x52E51EB66F5FL;
        long l4 = l2 ^ 0x747300E37E61L;
        long l5 = l2 ^ 0x77DD63D15BBL;
        int n2 = (int)(l5 >>> 48);
        int n3 = (int)(l5 << 16 >>> 48);
        int n4 = (int)(l5 << 32 >>> 32);
        currentBoost = 0.0f;
        fireworkSlot = -1;
        Item item = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(item, "AIR");
        fireworkItem = item;
        fireworkStack = null;
        o = false;
        if (!Intrinsics.areEqual(Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST).getItem(), Items.ELYTRA) && !Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.getCursorStack().getItem(), Items.ELYTRA) && InventoryUtil.INSTANCE.findElytra() == -1) {
            this.disableNoElytra(l4);
            return;
        }
        if (this.findFireworkSlot(l3, false) == -1) {
            this.disableNoFireworks((short)n2, (char)n3, n4);
            return;
        }
        if (this.findFireworkSlot(l3, true) != -1) {
            return;
        }
        fireworkStack = Globals.getPlayer().getInventory().getStack(ElytraPlus.INSTANCE.kQ() - 1).copy();
        Item item2 = Globals.getPlayer().getInventory().getStack(ElytraPlus.INSTANCE.kQ() - 1).getItem();
        Intrinsics.checkNotNullExpressionValue(item2, "getItem(...)");
        fireworkItem = item2;
    }

    @Override
    public void onDisable() {
        E = false;
        if (ElytraPlus.INSTANCE.getKeepFlying()) {
            return;
        }
        Globals.getPlayer().setVelocity(0.0, Globals.getPlayer().getVelocity().getY(), 0.0);
        new Thread(FireWorkElytra::stopGliding).start();
    }

    @Override
    public void onFireworkVelocity(@NotNull FireWorkVelocityEvent fireWorkVelocityEvent) {
        Intrinsics.checkNotNullParameter(fireWorkVelocityEvent, "e");
    }

    @Override
    public void onSetPose(@NotNull SetPoseEvent setPoseEvent) {
        Intrinsics.checkNotNullParameter(setPoseEvent, "e");
    }

    @Override
    public void handlePacket(long l, @NotNull ReceivePacket receivePacket) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
    }

    private final int findFireworkSlot(long l, boolean bl) {
        int n;
        long l2 = (l = c ^ l) ^ 0x6DE7B4F53F5L;
        int n2 = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        if (bl) {
            Item[] itemArray = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
            n = InventoryUtil.INSTANCE.findInHotbar(itemArray).getSlot();
        } else {
            Item[] itemArray = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
            n = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n2, l3).getSlot();
        }
        return n;
    }

    private final void disableNoFireworks(short s, char c, int n) {
        long l = ((long)s << 48 | (long)c << 48 >>> 16 | (long)n << 32 >>> 32) ^ FireWorkElytra.c;
        long l2 = l ^ 0x7A19F8982329L;
        Object[] objectArray = new Object[]{};
        CallSite callSite = FireWorkElytra.zkmBootstrap("n", (int)6955, (long)(0x3EE6B3662AA84E36L ^ l));
        ElytraPlus.INSTANCE.disableWithReason(l2, I18n.INSTANCE.translate((String)((Object)callSite), objectArray));
        o = false;
    }

    private final void disableNoElytra(long l) {
        long l2 = (l = c ^ l) ^ 0x9172E4648F3L;
        Object[] objectArray = new Object[]{};
        String string = "modules.common.no-elytra";
        ElytraPlus.INSTANCE.disableWithReason(l2, I18n.INSTANCE.translate(string, objectArray));
        o = false;
    }

    private final void swapFireworkIn(int n) {
        long l = 0L;
        l = c ^ l;
        InteractionUtil.clickSlot$default(n, 0, null, false, (int)FireWorkElytra.zkmBootstrap2("w", (int)23879, (long)(0x2C2BE4F1F011EDDDL ^ l)), null);
        InteractionUtil.clickSlot$default(ElytraPlus.INSTANCE.kQ() - 1 + FireWorkElytra.zkmBootstrap2("w", (int)16259, (long)(0x5657B02EE1D20F12L ^ l)), 0, null, false, (int)FireWorkElytra.zkmBootstrap2("w", (int)7133, (long)(0x2F24FB79A8B42B4DL ^ l)), null);
        InteractionUtil.clickSlot$default(n, 0, null, true, (int)FireWorkElytra.zkmBootstrap2("w", (int)10299, (long)(0x5EB9DB14814718ADL ^ l)), null);
    }

    private final void swapBack() {
        if (fireworkSlot == -1 || fireworkStack == null || Intrinsics.areEqual(fireworkItem, Items.FIREWORK_ROCKET) || Intrinsics.areEqual(fireworkItem, Items.AIR)) {
            return;
        }
        int n = this.findSlotOf(fireworkStack, fireworkItem);
        n = n < 9 && n != -1 ? n + 36 : n;
        InteractionUtil.clickSlot$default(n, 0, null, false, 14, null);
        InteractionUtil.clickSlot$default(ElytraPlus.INSTANCE.kQ() - 1 + 36, 0, null, false, 14, null);
        InteractionUtil.clickSlot$default(n, 0, null, false, 14, null);
    }

    private final void resetSwap() {
        chestSlot = -1;
        Item item = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(item, "AIR");
        chestItem = item;
        chestStack = null;
    }

    private final int findSlotOf(ItemStack itemStack, Item item) {
        if (itemStack == null) {
            return -1;
        }
        for (int i = 0; i < 45; ++i) {
            ItemStack itemStack2;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(i), "getItem(...)");
            if (!ItemStack.areItemsEqual(itemStack2, itemStack) || !Intrinsics.areEqual(itemStack2.getItem(), item)) continue;
            return i;
        }
        return -1;
    }

    private final int getFireworkSlot(short s, long l) {
        int n;
        block11: {
            int n2;
            int n3;
            int n4;
            CallSite callSite;
            long l2;
            long l3;
            block10: {
                long l4 = l3 = ((long)s << 48 | l << 16 >>> 16) ^ c;
                l2 = l4 ^ 0x2B3B151D19AAL;
                long l5 = l4 ^ 0x5EDAD3F5FC22L;
                long l6 = l4 ^ 0xB421B7E86C6L;
                int n5 = (int)(l6 >>> 48);
                int n6 = (int)(l6 << 16 >>> 48);
                int n7 = (int)(l6 << 32 >>> 32);
                callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3165110918147879731L, (long)l3);
                int n8 = Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET);
                if (callSite == null) {
                    if (n8 != 0) {
                        return (int)FireWorkElytra.zkmBootstrap2("w", (int)17579, (long)(0x6955A9D014AB9B20L ^ l3));
                    }
                    n8 = this.findFireworkSlot(l5, true);
                }
                n4 = n8;
                int n2 = n3 = this.findFireworkSlot(l5, false);
                n2 = -1;
                if (callSite != null) break block10;
                if (n == n2) {
                    this.disableNoFireworks((short)n5, (char)n6, n7);
                    return -1;
                }
                n = n4;
                if (callSite != null) break block11;
                n2 = -1;
            }
            if (n == n2) {
                int n9 = ElytraPlus.INSTANCE.kA();
                if (callSite == null) {
                    int n10;
                    if (s >= 0) {
                        if (n9 == 0) {
                            Object[] objectArray = new Object[]{};
                            CallSite callSite2 = FireWorkElytra.zkmBootstrap("n", (int)32191, (long)(0x59E01A58ED32922BL ^ l3));
                            ElytraPlus.INSTANCE.disableWithReason(l2, I18n.INSTANCE.translate((String)((Object)callSite2), objectArray));
                            return ElytraPlus.INSTANCE.kQ() - 1;
                        }
                        this.swapFireworkIn(n3);
                        n10 = ElytraPlus.INSTANCE.kQ();
                    }
                    n9 = n10 - true;
                }
                return n9;
            }
            n = n4;
        }
        return n;
    }

    private final boolean hasFirework(int n, short s, char c) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)c << 48 >>> 48) ^ FireWorkElytra.c;
        long l2 = l ^ 0x359C1150D1C9L;
        int n2 = (int)(l2 >>> 48);
        long l3 = l2 << 16 >>> 16;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5071286692040761023L, (long)l);
        int n3 = this.needsElytraSwap();
        if (callSite == null) {
            if (n3 != 0) {
                return false;
            }
            n3 = this.getFireworkSlot((short)n2, l3);
        }
        if (callSite == null) {
            n3 = n3 != -1 ? 1 : 0;
        }
        return n3 != 0;
    }

    private final boolean needsElytraSwap() {
        ItemStack itemStack = Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST);
        Intrinsics.checkNotNullExpressionValue(itemStack, "getItemBySlot(...)");
        ItemStack itemStack2 = itemStack;
        return !Intrinsics.areEqual(itemStack2.getItem(), Items.ELYTRA) || !ElytraPlus.INSTANCE.isUsableElytra(itemStack2);
    }

    private final void equipElytra(long l) {
        long l2 = (l = c ^ l) ^ 0x17EEBBA1CB36L;
        int n = InventoryUtil.INSTANCE.findElytra();
        if (n == -1 && !Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.getCursorStack().getItem(), Items.ELYTRA)) {
            this.disableNoElytra(l2);
            return;
        }
        if (!this.needsElytraSwap()) {
            return;
        }
        if (chestSlot == -1) {
            ItemStack itemStack = Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST);
            Intrinsics.checkNotNullExpressionValue(itemStack, "getItemBySlot(...)");
            ItemStack itemStack2 = itemStack;
            chestSlot = n;
            Item item = itemStack2.getItem();
            Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
            chestItem = item;
            chestStack = itemStack2.copy();
        }
        InteractionUtil.clickSlot$default(n, 0, null, false, 14, null);
        InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
        if (chestSlot != -1) {
            InteractionUtil.clickSlot$default(chestSlot, 0, null, false, 14, null);
        }
        ElytraPlus.INSTANCE.e(true);
    }

    /*
     * Unable to fully structure code
     */
    private final void restoreSwap() {
        block9: {
            if (FireWorkElytra.chestSlot == -1 || Intrinsics.areEqual(FireWorkElytra.chestItem, Items.AIR)) break block9;
            if (!ElytraPlus.INSTANCE.F1()) {
                return;
            }
            v0 = Globals.getPlayer().getInventory().getStack(FireWorkElytra.chestSlot);
            Intrinsics.checkNotNullExpressionValue(v0, "getItem(...)");
            var17_1 = v0;
            if (Intrinsics.areEqual(var17_1, ItemStack.EMPTY)) ** GOTO lbl-1000
            v1 = FireWorkElytra.chestStack;
            Intrinsics.checkNotNull(v1);
            if (!ItemStack.areItemsEqual(var17_1, v1)) {
                v2 = true;
            } else lbl-1000:
            // 2 sources

            {
                v2 = false;
            }
            var18_2 = v2;
            var19_3 = this.findSlotOf(FireWorkElytra.chestStack, FireWorkElytra.chestItem);
            v3 = var19_3 = var19_3 < 9 && var19_3 != -1 ? var19_3 + 36 : var19_3;
            if (!Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.getCursorStack().getItem(), Items.AIR)) {
                InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                if (FireWorkElytra.chestSlot != -1) {
                    var11_4 = null;
                    var12_6 = 14;
                    var13_8 = false;
                    var14_10 = null;
                    var15_12 = 0;
                    InteractionUtil.clickSlot$default(FireWorkElytra.chestSlot, var15_12, var14_10, var13_8, var12_6, var11_4);
                }
                return;
            }
            if (var19_3 == -1) {
                return;
            }
            InteractionUtil.clickSlot$default(var19_3, 0, null, false, 14, null);
            var11_5 = null;
            var12_7 = 14;
            var13_9 = false;
            var14_11 = null;
            var15_13 = 0;
            InteractionUtil.clickSlot$default(6, var15_13, var14_11, var13_9, var12_7, var11_5);
            if (!var18_2) {
                InteractionUtil.clickSlot$default(var19_3, 0, null, false, 14, null);
            } else {
                var20_14 = this.findEmptySlot();
                if (var20_14 != -1) {
                    InteractionUtil.clickSlot$default(var20_14, 0, null, false, 14, null);
                }
            }
        }
        this.resetSwap();
    }

    private final int findEmptySlot() {
        for (int i = 9; i < 45; ++i) {
            if (!Globals.getPlayer().getInventory().getStack(i).isEmpty()) continue;
            return i;
        }
        return -1;
    }

    public final boolean isAboveVoid() {
        if (Globals.getPlayer().getY() < 0.0) {
            return false;
        }
        return !Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().offset(0.0, -3.0, 0.0)).iterator().hasNext();
    }

    public final boolean isAboveAir() {
        return Globals.getWorld().getBlockState(BlockPos.ofFloored(Globals.getPlayer().getX(), Globals.getPlayer().getY() - 0.1, Globals.getPlayer().getZ())).getBlock() instanceof FluidBlock;
    }

    private static final void stopGliding() {
        PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        INSTANCE.swapBack();
        fireworkSlot = -1;
        Item item = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(item, "AIR");
        fireworkItem = item;
        fireworkStack = null;
        INSTANCE.restoreSwap();
        INSTANCE.resetSwap();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        FireWorkElytra.c = ZkmKeys.a(-7776498086378487126L, 8067530436925902127L, MethodHandles.lookup().lookupClass()).a(239646615889226L);
                        var20 = FireWorkElytra.c ^ 137547252340825L;
                        FireWorkElytra.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[17];
                        var16_4 = 0;
                        var15_5 = "\u00b4\u00b8!x\u00cd\u001d\u009a\u00a57\u00deY\u0095p\"\u00ef\bx\u0085\u00c8\u00ef\f\u00ac\u00d7\u00f5\u00b3\u0019\u00c6]\u00e2\u00f3\u00ab\u009d\u0091\u000f\u008d\u00b5XC\u001ey\u00cfr\u00b73w\u00cc;\u00a2\u00fc\u00fa\u00e0l\u000eK\u0081\u00fd\u0010\u00d4\u00c0#\u008f\u00f3f$\u001b-\u00e1a\u0015\u0017\u00a6\u009e\u00f70\u008c\u001eo\u0088V\u0088;&\u0011%\u0091\u00bb2\u00a5\u0082\u00ba7\u0005rg\u00af'\u0083\u00ce7X\u0014\u00ef\u00b0]\u008c\u001e\u0088\u0015f\u00fd\u00a7\u00ef\u008c\u0017Ev)\u0089\u00a1\u001dM\u00af \u0095\u00e7\u00a1\b7(</\u00efC\u0083\u0090\u00f8\n\u00026\u0087\u00ee\u009c\u00b9\u0082PU\u00deMB\u00f1\\\u0013w0\r \u0089\txc\u00b6\u00e1|N.\u0095l<4\u00f7\u00e7J\u00dd\u00e7[\u0013\u009b\u00d5\u00c8\u0003_\u0085\u00cfu\u0019\u00d3\u00c7\u0011\u0088\u0082\u00d89\u0002\u0015\u00e5\u00f4\u0093\u00e8t\u00aaX\u00c8\u00a5\u00fa \u001b\u001e\u0086\u00e3\bn\n(\u00f9Vf<y\u00f4\"v\u00fa1sN\u00d5\u00ee\u00d2 B\u00de\u001f\u0096<\u0084\u00a0h\u009cA\u000f\u0082\u00e2\u0018\u00b3\u00c9\u00f7\u00fc\u00e0\u0096{\u00f8\u00ef\u00bb\u008d^E\u00e3)[OLb\u0005\u0092\u00bf\u009fL\u00a7\u0019f `WT2\f\u00ba5\u00ad\u0085\b\u00b9\u00de\u00a7\u00fc.+zE\u009b\u00b1\u0098\u00cc\b\u00c3\u001f\u00a70\u001d\u00ae1\u00b3F\u00913\u00f1\u00ae\u00cajy\u00c1\\\u00980\u0003_r\u008f\u00d5\u008eM<I\u0004\u001f\u0010\u00a6\u00bc\"\u00f8\u0097bO\u00e7KkQ\u00bc^\u00dc\u00f3=(\u000e\u00b5\u00a7L\u000b\u00a6&\u00f0\u00e5\u0081X\u001b\u008d>\u00ab\u0005\u0083jI&\u0004zlo\u009c3\u0004\u00be~\u00d1\u00fc\u00fb8\u00fe\u00edZYsx-8Z>\u00b3\u00001\u00ae\u00db\u00b8\u00b0\u00fc\f\u0096\u00c2\u00ee\u0005\u00e6\b\u0016\u001c\u00c8RF\u00e3\u00cc\u008a\u00e1\u00deC\u0017+U\u00b7]\u0094!1\u00f6\u00c8B\u008b\u00f2}\u00a3\u00cb\u00f8\u00c4\u00fe\u001c\u00d4kV\u007f\u00b0\u00d5\u0081\u00ca\u0088\u00e4)\u00e8&2,\u00da\u00f8\u00d3\u00b7\u00f7\u0086P\u00a8TR}F{ \u0081\u009d5~\u00da\u00b9\f[\u008e\u00ab;x+\u0084\u00a9X3;r\u00f6\\L\u0097\u009d\u00b2wa\u00f7\u00a18\u00f8\u00ca\u0018\u00d6s\u0089\u00bf\u00cb\u00bdV\u0081>Q\u00e2\u00c3G\u0006\u00e2\u0002\u00ae\u009a\u0011O\u00f1%\u00b899\u00be\u0090:\u00e7\r\u001d\u009cxP\u00c4P\u001ek\u0084H:J}s\u00e5\u00b4#\u00972\u001bz:\u00d4\u00a9\u00cf\u0005\u0082\u0097\u00a5\u0089\bE#\u00bb\u00b7\u00a0\u0093Y\u00c4\u00d55C\u00ad\u00fa\u00df\u001e\u00bb\u00c1\u009b\u00e44\u00a8\b \u001dE.\u00d78\u0084\u0086C\u00b6\u008a\u00c5\u008bP.\u00b8o8\u009d\u0014\u00eb$>\u00c8\u00d7\u0003\u0098\u00b7\u00a0\u00d3`\u00bb\u00b5(\u009fk\u00db\u000e*\u0097\u009aZ\u00f36\u00e2\u00f6d\u00dcmW\u00fa\u00d9+9\u0097\u00bc\\\u00aa&b\u0018\u00c6\t\u0096UU\u00f7]\u0083\u00a9R\u00d7.\u00ef\u0010n/\u0088\u00f9\u00da\u0002\u00f0\u00a7\u00ef\u008d\u0018_pn\u00177 4d\u00d1\u00b6N7\u0089\u00f2\u00ea\u00a6\u00ecW\u00ce|pg\u00c9\u008cs\u00ee;\u00c0v\u00b9QD_\u00e1\u00c9\u00a21m\u0010\u00f8T\u009d\u00b3\u00b1\u00a5\u00a4\u00f6\u0081#\u008c\u0003{Y\u00f8\u00a2";
                        var17_6 = 718;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = FireWorkElytra.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c6\u009b\u008f\u00cf\u00e7W\u00f0.r\u00b4f\u00c0gRi6\u00f6\u00c5\u00c5<\u00c3$J\u00130\u0093\u00fc6\u0098D\u0004\u009cMPW\u00d6\u0007\u00c32;W\u00a8\u00dbp>\u00e8\u00ff_\u0010\u00c1m\u00a9\u00b6Su\u0006\u00f4\u00d8\u00ef\u001c7\u0007\u00deK\u0017";
                            var17_6 = 65;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = FireWorkElytra.zkmDecodeUtf8(var19_9).intern();
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
                FireWorkElytra.d = var18_3;
                FireWorkElytra.e = new String[17];
                FireWorkElytra.i = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "|:f;Q\u00b1(\u00b3\u00b9\u00951\n\u00fb\u00ce\u00ceP\u00eb\u00e9\u00e1\u00128Z\u00ff\t\u00ae\u00f43\u00d2\u0099]\u00db|q\n\u00a7i\u001e\u0095\u0018:\u001a!-Q\u00be/\u00e6v\u00c4p\u001a&\u00ceh\u00b3=\u0013\u008f\u00d3\u0012\u0085\u00a1w#\u0088Zs\u00f0\u00efb\u0015\u0016\u00ef\u00a8\u00c4\u0016\u00f9\u00a5v\u00dc";
                var5_15 = 80;
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
                    var4_14 = "\u00a4\u00a7\u00e2Z\u0086Z>\u00af\u009a7g\u00baI\u00e9\u00ee\u00d9";
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
        FireWorkElytra.g = var6_12;
        FireWorkElytra.h = new Integer[12];
        FireWorkElytra.INSTANCE = new FireWorkElytra();
        v15 = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(v15, "AIR");
        FireWorkElytra.chestItem = v15;
        v16 = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(v16, "AIR");
        FireWorkElytra.fireworkItem = v16;
        FireWorkElytra.chestSlot = -1;
        FireWorkElytra.fireworkSlot = -1;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x450B;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/m1", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            FireWorkElytra.e[n2] = FireWorkElytra.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FireWorkElytra.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/m1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7518;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/m1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FireWorkElytra.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FireWorkElytra.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/m1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FireWorkElytra.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FireWorkElytra.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

