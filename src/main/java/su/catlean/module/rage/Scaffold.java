/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  org.joml.Vector3fc
 */
package su.catlean.module.rage;

import com.google.common.collect.Queues;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.AirPlace;
import su.catlean.module.rage.Scaffold$WhenMappings;
import su.catlean.module.rage.ScaffoldAutoSwitchMode;
import su.catlean.module.rage.ScaffoldTimingMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ItemPredicate;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceTarget;
import su.catlean.util.PlacementHelper;
import su.catlean.util.RandomUtil;
import su.catlean.util.TimedPacket;
import su.catlean.util.Timer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.LambdaRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Scaffold
extends Module {
    @NotNull
    public static final Scaffold INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting timing$delegate;
    @NotNull
    private static final ModeSetting autoSwitch$delegate;
    @NotNull
    private static final BooleanSetting tower$delegate;
    @NotNull
    private static final BooleanSetting safeWalk$delegate;
    @NotNull
    private static final BooleanSetting allowShift$delegate;
    @NotNull
    private static final BooleanSetting legit$delegate;
    @NotNull
    private static final BooleanSetting saveAngles$delegate;
    @NotNull
    private static final BooleanSetting telly$delegate;
    @NotNull
    private static final BooleanSetting clamp$delegate;
    @NotNull
    private static final BooleanSetting airPlace$delegate;
    @NotNull
    private static final BooleanSetting grimBlockFly;
    @NotNull
    private static final BooleanSetting keepY$delegate;
    @NotNull
    private static final BooleanSetting rotate$delegate;
    @NotNull
    private static final BooleanSetting grimSilent$delegate;
    @NotNull
    private static final Timer V;
    @Nullable
    private static PlaceTarget placeTarget;
    private static int x;
    @Nullable
    private static Rotation placeRotation;
    private static boolean h;
    @NotNull
    private static final ConcurrentLinkedQueue receivedPackets;
    @NotNull
    private static final ConcurrentLinkedQueue delayedPackets;
    @NotNull
    private static final Timer l;
    @NotNull
    private static final BlockPos[] placeOffsets;
    private static final long b;
    private static final String[] c;
    private static final String[] e;
    private static final Map g;
    private static final long[] k;
    private static final Integer[] m;
    private static final Map n;

    private Scaffold() {
        super("scaffold", Categories.getRage(), null, null);
    }

    private final ScaffoldTimingMode getTiming() {
        return (ScaffoldTimingMode)((Object)timing$delegate.getValue(this, delegatedProperties[0]));
    }

    private final ScaffoldAutoSwitchMode getAutoSwitch() {
        return (ScaffoldAutoSwitchMode)((Object)autoSwitch$delegate.getValue(this, delegatedProperties[1]));
    }

    private final boolean getTower() {
        return (Boolean)tower$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getSafeWalk() {
        return (Boolean)safeWalk$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getAllowShift() {
        return (Boolean)allowShift$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getLegit() {
        return (Boolean)legit$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getSaveAngles() {
        return (Boolean)saveAngles$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getTelly() {
        return (Boolean)telly$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getClamp() {
        return (Boolean)clamp$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getAirPlace() {
        return (Boolean)airPlace$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean n() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ b;
        return (Boolean)grimBlockFly.getValue(this, delegatedProperties[Scaffold.zkmBootstrap2("t", (int)3335, (long)(0x69420488B59B5C01L ^ l))]);
    }

    private final boolean getKeepY() {
        return (Boolean)keepY$delegate.getValue(this, delegatedProperties[11]);
    }

    private final boolean getRotate() {
        return (Boolean)rotate$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getGrimSilent() {
        return (Boolean)grimSilent$delegate.getValue(this, delegatedProperties[13]);
    }

    @Override
    public void onEnable(long l) {
        x = -999;
    }

    @Override
    public void onDisable(long l) {
        int n = (int)((l ^ 0x280BA6AF1127L) << 48 >>> 48);
        if (h && !KeyboardUtil.isKeyPressed((char)n, 340)) {
            Globals.getMc().options.sneakKey.setPressed(false);
        }
        this.flushDelayed();
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = b ^ 0x1B592227545AL;
        long l2 = l ^ 0x2FF21495395CL;
        this.update(l2);
    }

    private final void update(long l) {
        boolean bl;
        long l2 = (l = b ^ l) ^ 0x42177EBB4E3DL;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        if (Globals.getMc().options.jumpKey.isPressed() && MovementUtil.INSTANCE.getFallDistance() <= 0.0 && this.getTelly()) {
            return;
        }
        placeTarget = null;
        int n2 = this.findBlockSlot(l3, false, (char)n);
        if (!this.getSafeWalk() && Globals.getPlayer().isSneaking() && !this.getAllowShift() || n2 == -1) {
            return;
        }
        if (Globals.getMc().options.jumpKey.isPressed() && !MovementUtil.INSTANCE.isMoving()) {
            x = (int)Math.floor(Globals.getPlayer().getY() - 1.0);
        }
        Entity entity = Globals.getPlayer();
        if (this.getPos((Entity)entity).y < (double)x) {
            x = -999;
        }
        double d = (bl = this.isFullBlockSlot(n2)) ? Globals.getPlayer().getY() : Math.ceil(Globals.getPlayer().getY());
        BlockPos blockPos = (this.getKeepY() || this.getTelly()) && x != -999 ? BlockPos.ofFloored(Globals.getPlayer().getX(), x, Globals.getPlayer().getZ()) : BlockPos.ofFloored(Globals.getPlayer().getX(), d, Globals.getPlayer().getZ()).down();
        Intrinsics.checkNotNull(blockPos);
        BlockPos blockPos2 = blockPos;
        PlaceTarget placeTarget = Scaffold.placeTarget = this.getAirPlace() ? new PlaceTarget(blockPos2, Direction.DOWN) : this.canPlaceAt(blockPos2);
        if (this.getSafeWalk() && MovementUtil.INSTANCE.isMoving()) {
            if (!Globals.getPlayer().isSneaking() && Globals.getWorld().getBlockState(blockPos2).isReplaceable()) {
                Globals.getMc().options.sneakKey.setPressed(true);
                h = true;
            }
            if (Globals.getPlayer().isSneaking() && !Globals.getWorld().getBlockState(blockPos2).isReplaceable()) {
                Globals.getMc().options.sneakKey.setPressed(false);
                h = false;
            }
        }
        double d2 = this.getTiming() == ScaffoldTimingMode.VANILLA ? -0.1 : -0.25;
        PlaceTarget placeTarget2 = Scaffold.placeTarget;
        if (!(placeTarget2 == null || this.getLegit() && Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().expand(d2, 0.0, d2).offset(0.0, -0.1, 0.0)).iterator().hasNext())) {
            BlockPos blockPos3 = placeTarget2.getPosition();
            Direction direction = placeTarget2.getFacing();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new BlockHitResult(blockPos3.toCenterPos().add(new Vec3d((Vector3fc)direction.getUnitVector()).multiply(0.5)), direction, blockPos3, false);
            double d3 = Globals.getPlayer().getY() - Math.floor(Globals.getPlayer().getY()) == 0.5 ? 0.39 : 0.82 + RandomUtil.k(RangesKt.rangeTo(0.0, 0.16), false, null);
            double d4 = this.getClamp() ? RangesKt.coerceIn(Globals.getPlayer().getX() + RandomUtil.k(RangesKt.rangeTo(-0.01, 0.01), false, null), (double)((BlockHitResult)objectRef.element).getBlockPos().getX(), (double)((BlockHitResult)objectRef.element).getBlockPos().getX() + 1.0) : ((BlockHitResult)objectRef.element).getPos().x;
            double d5 = this.getClamp() ? RangesKt.coerceIn(Globals.getPlayer().getZ() + RandomUtil.k(RangesKt.rangeTo(-0.01, 0.01), false, null), (double)((BlockHitResult)objectRef.element).getBlockPos().getZ(), (double)((BlockHitResult)objectRef.element).getBlockPos().getZ() + 1.0) : ((BlockHitResult)objectRef.element).getPos().z;
            objectRef.element = new BlockHitResult(new Vec3d(d4, (double)blockPos3.getY() + d3, d5), direction, blockPos3, false);
            Vec3d vec3d = ((BlockHitResult)objectRef.element).getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getLocation(...)");
            Rotation rotation = Rotation.Companion.to(vec3d);
            if (this.getRotate() && this.getGrimSilent()) {
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.Full(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), rotation.getYaw(), rotation.getPitch(), Globals.getPlayer().isOnGround(), Globals.getPlayer().horizontalCollision));
            }
            Rotation rotation2 = !this.getRotate() || this.getGrimSilent() ? new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null) : rotation;
            RotationRequest rotationRequest = switch (Scaffold$WhenMappings.N[this.getTiming().ordinal()]) {
                case 1 -> new RotationRequest(rotation2, () -> Scaffold.update$lambda$0(objectRef), null);
                case 2 -> new ActionRotationRequest(rotation2, () -> Scaffold.update$lambda$1(objectRef), null);
                case 3 -> new LambdaRotationRequest(rotation2, () -> Scaffold.update$lambda$2(objectRef), null);
                case 4 -> new LambdaRotationRequest(rotation2, () -> Scaffold.update$lambda$2(objectRef), null);
                default -> throw new NoWhenBranchMatchedException();
            };
            RotationManager.INSTANCE.submit(rotationRequest);
            placeRotation = rotation;
        } else if (placeRotation != null && this.getSaveAngles()) {
            Rotation rotation;
            if (this.getRotate() && !this.getGrimSilent()) {
                Rotation rotation3 = placeRotation;
                rotation = rotation3;
                Intrinsics.checkNotNull(rotation3);
            } else {
                rotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
            }
            Object var66_21 = null;
            Rotation rotation4 = rotation;
            RotationManager rotationManager = RotationManager.INSTANCE;
            RotationManager.rotate$default(rotationManager, rotation4, var66_21);
        }
    }

    private final boolean isFullBlockSlot(int n) {
        if (n == -2) {
            if (Globals.getPlayer().getOffHandStack().getItem() instanceof BlockItem) {
                Item item = Globals.getPlayer().getOffHandStack().getItem();
                Intrinsics.checkNotNull(item, "null cannot be cast to non-null type net.minecraft.world.item.BlockItem");
                return Block.isShapeFullCube(((BlockItem)item).getBlock().getDefaultState().getCollisionShape(Globals.getWorld(), BlockPos.ORIGIN));
            }
        } else if (n != -1 && Globals.getPlayer().getInventory().getStack(n).getItem() instanceof BlockItem) {
            Item item = Globals.getPlayer().getInventory().getStack(n).getItem();
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type net.minecraft.world.item.BlockItem");
            return Block.isShapeFullCube(((BlockItem)item).getBlock().getDefaultState().getCollisionShape(Globals.getWorld(), BlockPos.ORIGIN));
        }
        return true;
    }

    private final void placeBlock(long l, BlockHitResult blockHitResult) {
        block10: {
            boolean bl;
            boolean bl2;
            long l2 = (l = b ^ l) ^ 0x53D2CCF58B73L;
            long l3 = l2 >>> 16;
            int n = (int)(l2 << 48 >>> 48);
            if (Globals.getMc().options.jumpKey.isPressed() && MovementUtil.INSTANCE.getFallDistance() <= 0.0 && this.getTelly()) {
                return;
            }
            int n2 = this.findBlockSlot(l3, true, (char)n);
            if (n2 == -1) break block10;
            if (Globals.getMc().options.jumpKey.isPressed() && !MovementUtil.INSTANCE.isMoving() && this.getTower()) {
                Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().x, V.hasPassed2(1500) ? -0.28 : 0.42, Globals.getPlayer().getVelocity().z);
            } else {
                V.reset();
            }
            Block block = Globals.getWorld().getBlockState(blockHitResult.getBlockPos()).getBlock();
            Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
            boolean bl3 = bl2 = PlaceManager.INSTANCE.isInteractable(block) && !Globals.getPlayer().isSneaking();
            if (bl2) {
                PlacementHelper.INSTANCE.setViaVersionFix(true);
            }
            boolean bl4 = bl = n2 != -2;
            if (this.getAirPlace() && AirPlace.INSTANCE.getEnabled() && AirPlace.INSTANCE.getBypass()) {
                InteractionUtil.useItem$default(bl ? Hand.MAIN_HAND : Hand.OFF_HAND, 0.0f, 0.0f, 6, null);
                PacketUtil.sendSequencedPacket(arg_0 -> Scaffold.createUsePacket(bl, arg_0));
                PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                PacketUtil.sendSequencedPacket(arg_0 -> Scaffold.createPlacePacket(bl, blockHitResult, arg_0));
                Globals.getPlayer().swingHand(bl ? Hand.MAIN_HAND : Hand.OFF_HAND);
                PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                Globals.getPlayer().swingHand(bl ? Hand.MAIN_HAND : Hand.OFF_HAND);
            } else {
                int n3 = Globals.getPlayer().getMainHandStack().getCount();
                if (this.n()) {
                    PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                }
                Globals.getInteractionManager().interactBlock(Globals.getPlayer(), bl ? Hand.MAIN_HAND : Hand.OFF_HAND, blockHitResult);
                PacketUtil.sendPacket(new HandSwingC2SPacket(bl ? Hand.MAIN_HAND : Hand.OFF_HAND));
                if (this.n()) {
                    PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                    Globals.getPlayer().getMainHandStack().setCount(n3);
                }
            }
            Scaffold.l.reset();
            x = blockHitResult.getBlockPos().getY();
            if (bl2) {
                PlacementHelper.INSTANCE.setViaVersionFix(false);
            }
            this.switchToBlock(n2);
            if (this.getRotate() && this.getGrimSilent()) {
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.Full(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), Globals.getPlayer().isOnGround(), Globals.getPlayer().horizontalCollision));
            }
        }
    }

    private final PlaceTarget canPlaceAt(BlockPos blockPos) {
        if (!Globals.getWorld().getBlockState(blockPos).isReplaceable()) {
            return null;
        }
        for (BlockPos blockPos2 : placeOffsets) {
            PlaceTarget placeTarget;
            BlockPos blockPos3 = blockPos.add(blockPos2);
            Intrinsics.checkNotNullExpressionValue(blockPos3, "offset(...)");
            PlaceTarget placeTarget2 = placeTarget = PlaceManager.INSTANCE.clearPending(blockPos3);
            if (placeTarget2 == null) continue;
            return placeTarget2;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isValidBlock(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof BlockItem)) return false;
        Item item = itemStack.getItem();
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type net.minecraft.world.item.BlockItem");
        if (((BlockItem)item).getBlock().getDefaultState().isReplaceable()) return false;
        Item item2 = itemStack.getItem();
        Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type net.minecraft.world.item.BlockItem");
        if (((BlockItem)item2).getBlock() instanceof FallingBlock) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private final int findBlockSlot(long var1_1, boolean var3_2, char var4_3) {
        block19: {
            block16: {
                block17: {
                    block18: {
                        block15: {
                            block14: {
                                var5_4 = (var1_1 << 16 | (long)var4_3 << 48 >>> 48) ^ Scaffold.b;
                                v0 = var5_4 ^ 111030089366172L;
                                var9_5 = (int)(v0 >>> 48);
                                var10_6 = (int)(v0 << 16 >>> 48);
                                var11_7 = (int)(v0 << 32 >>> 32);
                                var24_8 = -1;
                                var23_9 = ZkmReferences.zkmBootstrap("\u00c5", (long)4468041256736378372L, (long)var5_4);
                                v1 = Globals.getPlayer().getOffHandStack();
                                Intrinsics.checkNotNullExpressionValue(v1, (String)Scaffold.zkmBootstrap("q", (int)22710, (long)(171336333059803102L ^ var5_4)));
                                v2 = this.isValidBlock(v1);
                                if (var23_9 != null) {
                                    if (v2 != 0) {
                                        return (int)Scaffold.zkmBootstrap2("t", (int)2836, (long)(5178640256620033040L ^ var5_4));
                                    }
                                    v3 = Globals.getPlayer().getMainHandStack();
                                    Intrinsics.checkNotNullExpressionValue(v3, (String)Scaffold.zkmBootstrap("q", (int)28334, (long)(5796452387911201229L ^ var5_4)));
                                    v2 = (int)this.isValidBlock(v3);
                                }
                                if (var23_9 == null) break block14;
                                if (v2 == 0) break block15;
                                v2 = Globals.getPlayer().getInventory().getSelectedSlot();
                            }
                            return v2;
                        }
                        var25_10 = InventoryUtil.INSTANCE.findInHotbar((ItemPredicate)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/item/ItemStack;)Z, findBlockSlot$lambda$0(net.minecraft.item.ItemStack ), (Lnet/minecraft/item/ItemStack;)Z)());
                        var26_11 = InventoryUtil.INSTANCE.findInInventory((short)var9_5, (ItemPredicate)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/item/ItemStack;)Z, findBlockSlot$lambda$1(net.minecraft.item.ItemStack ), (Lnet/minecraft/item/ItemStack;)Z)(), (short)var10_6, var11_7);
                        v4 = var3_2;
                        v5 = var23_9;
                        if (var4_3 < '\u0000') break block16;
                        if (v5 == null) break block17;
                        if (v4 == 0) break block18;
                        v4 = Scaffold$WhenMappings.V[this.getAutoSwitch().ordinal()];
                        if (var23_9 == null || var4_3 <= '\u0000') ** GOTO lbl37
                        switch (v4) {
                            case 1: {
                                v4 = (int)var26_11.isFound();
lbl37:
                                // 2 sources

                                if (var23_9 == null) break block19;
                                if (v4 == 0) ** GOTO lbl-1000
                                InteractionUtil.clickSlot(var26_11.getSlot(), var24_8, SlotActionType.SWAP, true);
                                var24_8 = Globals.getPlayer().getInventory().getSelectedSlot();
                                if (var4_3 > '\u0000' && var23_9 != null) ** GOTO lbl-1000
                            }
                            case 2: 
                            case 3: {
                                v4 = (int)var25_10.isFound();
                                if (var23_9 == null) break block19;
                                if (v4 == 0) ** GOTO lbl-1000
                                var24_8 = Globals.getPlayer().getInventory().getSelectedSlot();
                                var25_10.select();
                            }
                            default: {
                                if (var23_9 != null) ** GOTO lbl-1000
                            }
                        }
                    }
                    v4 = Scaffold$WhenMappings.V[this.getAutoSwitch().ordinal()];
                }
                v5 = var23_9;
            }
            if (v5 == null) break block19;
            switch (v4 ? 1 : 0) {
                case 1: {
                    v4 = (int)var26_11.isFound();
                    if (var23_9 == null) break;
                    if (v4 == 0) ** GOTO lbl68
                    var24_8 = Globals.getPlayer().getInventory().getSelectedSlot();
                    if (var23_9 != null) ** GOTO lbl68
                }
                case 2: 
                case 3: {
                    v4 = (int)var25_10.isFound();
                    if (var23_9 == null) break;
                    if (v4 != 0) {
                        var24_8 = Globals.getPlayer().getInventory().getSelectedSlot();
                    }
                }
lbl68:
                // 6 sources

                default: lbl-1000:
                // 5 sources

                {
                    v4 = var24_8;
                }
            }
        }
        return v4;
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (!this.n()) {
            return;
        }
        Packet<?> packet = receivePacket.getPacket();
        if (packet instanceof ChatMessageC2SPacket || packet instanceof GameMessageS2CPacket || packet instanceof WorldTimeUpdateS2CPacket || packet instanceof CommandExecutionC2SPacket || packet instanceof PlaySoundS2CPacket || packet instanceof StopSoundS2CPacket || packet instanceof OverlayMessageS2CPacket || packet instanceof HealthUpdateS2CPacket || packet instanceof BossBarS2CPacket) {
            return;
        }
        if (packet instanceof PlayerPositionLookS2CPacket || packet instanceof DisconnectS2CPacket) {
            this.flushDelayed();
            return;
        }
        receivedPackets.add(new TimedPacket(receivePacket.getPacket(), Globals.now()));
        receivePacket.cancel();
    }

    @Flow
    private final void onPostTasksProcess(PostTasksProcessEvent postTasksProcessEvent) {
        if (Globals.getMc().world == null || Globals.getMc().player == null) {
            receivedPackets.clear();
            return;
        }
        Scaffold.flushPackets$default(this, false, null);
        delayedPackets.removeIf(arg_0 -> Scaffold.invokePredicate(Scaffold::onPostTasksProcess$lambda$0, arg_0));
    }

    private final void flushPackets(boolean bl) {
        receivedPackets.removeIf(arg_0 -> Scaffold.invokePredicate2(arg_0 -> Scaffold.shouldSendPacket(bl, arg_0), arg_0));
    }

    static void flushPackets$default(Scaffold scaffold, boolean bl, Object object) {
        bl = false;
        scaffold.flushPackets(bl);
    }

    private final void flushDelayed() {
        receivedPackets.removeIf(arg_0 -> Scaffold.invokePredicate3(Scaffold::sendDelayedPacket, arg_0));
    }

    private final void switchToBlock(int n) {
        switch (n) {
            case -2: 
            case -1: {
                return;
            }
        }
        switch (Scaffold$WhenMappings.V[this.getAutoSwitch().ordinal()]) {
            case 1: {
                InteractionUtil.clickSlot(n, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
                break;
            }
            case 3: {
                InventoryUtil.INSTANCE.selectSlot(n);
                break;
            }
        }
    }

    private static final boolean settingVisible() {
        return !INSTANCE.getSafeWalk();
    }

    private static final Unit update$lambda$0(Ref.ObjectRef objectRef) {
        long l = b ^ 0x5FC07FFEB10DL;
        long l2 = l ^ 0x7AAEFB021945L;
        INSTANCE.placeBlock(l2, (BlockHitResult)objectRef.element);
        return Unit.INSTANCE;
    }

    private static final Unit update$lambda$1(Ref.ObjectRef objectRef) {
        long l = b ^ 0x45E3BC777986L;
        long l2 = l ^ 0x608D388BD1CEL;
        INSTANCE.placeBlock(l2, (BlockHitResult)objectRef.element);
        return Unit.INSTANCE;
    }

    private static final Unit update$lambda$2(Ref.ObjectRef objectRef) {
        long l = b ^ 0x446ACD6B56FDL;
        long l2 = l ^ 0x61044997FEB5L;
        INSTANCE.placeBlock(l2, (BlockHitResult)objectRef.element);
        return Unit.INSTANCE;
    }

    private static final Packet createUsePacket(boolean bl, int n) {
        return new PlayerInteractItemC2SPacket(Hand.OFF_HAND, n, MovementUtil.INSTANCE.getLastYaw(), MovementUtil.INSTANCE.getLastPitch());
    }

    private static final Packet createPlacePacket(boolean bl, BlockHitResult blockHitResult, int n) {
        return new PlayerInteractBlockC2SPacket(Hand.OFF_HAND, blockHitResult, n);
    }

    private static final boolean findBlockSlot$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return INSTANCE.isValidBlock(itemStack);
    }

    private static final boolean findBlockSlot$lambda$1(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return INSTANCE.isValidBlock(itemStack);
    }

    private static final boolean onPostTasksProcess$lambda$0(Packet packet) {
        Scaffold scaffold = INSTANCE;
        try {
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener>");
            packet.apply((PacketListener)Globals.getNetworkHandler());
            Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
        return true;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean shouldSendPacket(boolean bl, TimedPacket timedPacket) {
        boolean bl2;
        block0: {
            boolean bl3 = bl2 = timedPacket.getTimestamp() <= Globals.now() - 500L;
            if (!bl3) break block0;
            delayedPackets.add(timedPacket.getPacket());
        }
        return bl2;
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean sendDelayedPacket(TimedPacket timedPacket) {
        Scaffold scaffold = INSTANCE;
        try {
            Packet packet = timedPacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener>");
            packet.apply((PacketListener)Globals.getNetworkHandler());
            Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
        return true;
    }

    private static final boolean invokePredicate3(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Scaffold.b = ZkmKeys.a(3772004756923701576L, -5245564808337987449L, MethodHandles.lookup().lookupClass()).a(229853068187810L);
                        var20 = Scaffold.b ^ 96020141158510L;
                        Scaffold.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[56];
                        var16_4 = 0;
                        var15_5 = "\u0006L\u00f5\u00d9\u0087\u000b\u00d5e2\u00c3*\u00a27\u0087\u0004\u0002\u0010\u00f1\u00e2\u0014\u0080\u00b9`\u00ed>\u00df\t(\u00ba\u00c3(c\u00dc(H\u00c4\u0088\u00d1$\u001e]u\u00adm\u00c4IB\u00e01\u00ecT\u00beC\u00a0\u0086\u00b4\u00dc\u00c1P\u00000\u00dc)\u00de\u00bc\u000f\u0000\u009c\u0006\u009c\u00ff5\u00a8\u00f2\u00d8\u00c8\u00c9r\u00a7\u001a\u00e7EY\u00a1Y\u001a\u00dc\u0082\n\u0091\u00b2\u00ef\u0018\u00eaC\u00b2\u0094\u0083\u0085p\u00f38\u00b2j*\u00a9p\u00bd\u00af\u00d4 =\u00a8\u0097\u0012\u00f0H3\u00c0b6Q\u008c\u00b1\u00f4\u00f7\u00a2\u00ee\u00f4[\u00dd\u0007\u00cc\u00a4\u008e\u00b5\u00f6\u0087l%g\u00cald?\u00c8yCt\u00a5\u009b\u0085\u00a7\u00bb\u00cd\u008d\u001c\u008d\u00ccS\u0085-cPl\u000f\u00b3\u0085\u00ee\u0013\u0001\u00c23\u00c0[\u0092*+\u00f5rh3\u009a\u00e1;\u000e@\u00fe\\\u00cbi&\u00b6\u0018\u001aJFX[\u009b\u00ea\u0019>\u00c4\u00b0^\u0018\u0011\u00cc\u008d\u00b2;\u0099\u00b9e\u00f8[\u00d9\u0002_\u00eb\u00e0m\u00d1\f3\u009e\u008e\ts\u00eco\u00d7\u0097[[Z\u001f\u00bd\u0090\u00ff\u0007\u0017\u0017\u0018Z\u00f3l\u00fb\u00abc\u0098\u00016:\u00a1\u00e4\u0084\u00aaq\u0019TuJ\u00c0\u00faE\u0087X\u0097xl|\u00acbH8\u00e6&\u00ca@\u0087\u00b3m\u001c\u008f\u00b2\u009f\u0003\u00a4\u00e0\u0010\u00bd>\u00d1\u0087\u00d0\u00e8\u00e4\u008b\u00e0\u00b2\u00e4A\u00b8\u00fa\u0092`\u00e0\u0015\u00ca\u0086!\u0005\u00c3KC8\u00c0\u00e1\u00cf\u00c7N\u00de\\.h,\u00ca\u0080\u007f\u00b9= \u008e\u00e75\u0019\f\u0019\u009d\u00e4\u00f4\u001e\u00f2T\u00b4\u00f8]\u00e4\u00b5\u000f\u007fu&\u00d4\u001c\u00bdiw\u00dd\u0096\u00ed\u00d4\u00f3\u00ec\u009e=\u00bbKd\u00b2\u00d7\u00d2i\u00fe\u00ebz\u00f4\u00ab%v\u00bf\u0014k\t\u00a7j|%\u009c}\u008f\u0014\u00c1T\u008d\u0087O\u009b{\t_\u00bb)\u00adD\u001d\u00ca\u00c6@i\u00d9\u0087{S\u00e0\u00c6p\u00ad-).\u00d5(\u00dd\u0019\u00d4\u008a\u00b5I;j\u0093\u009c\u00c8k\u0099\u0002.\u00e3l\u00c1lt\u00a4\u001b\u00d3\u00eb]\u00cb#\u0096R\u00d1\u00aeL\u00bf4\u00ac\u009e\u00ef\u00c2\u00ba\u00f8[(\u001aa\u0084\u001a\n\u00da\u00b4'\u00e7{\u00b1\n\u0097\u00f2Ur\u00c9\u00a3V\u0090\u00dc\u008bT\u0017>\u0087\u00d7\u00c5-ssm=\u00d1b\u00ae\u00d8q\u00bd\u0080!\u00e2\u00caM\u0000\u00aa\u00b0s\u00954\u0011E\u008e\u00b2\u00f0\u0017\u00bf\u00fc\u0007X\b\u00ea\u00f8\u009aET\u0010\u00b5\u00d9\u0019X=JA\u00a9\u009ei\u00cd\u0096k-\u00ec\u0098\u0010l\u0081\u00ae\u00bd?\u0096\u00e3\u00e7\u00f1-\u00bf\u00a0B\u00d2`C \u00db\u00ff\u00be\f\u00c8\u00b2\u00c6\\\u00a1\u00f2\u00d2x\u00e2~\nh\u00b2\u00e0\u0094\u0082\u00dec\u00b7\t\u00acw\u000fd_\u0089\u00d54(\u00e4\u0096\u00c7\u00ad\u001c\u0011\u00abU\u00b4\u00fa\u0015~c\u00ee\u00834\u0096\u00d0\u00d6S6\u0002\u00bae`\u00bc8\r\u0084\u00a9W\u00e3z0l`\u0006~m]@S^f%\u00fe\u00dbf\u00ea\u00d9\u001dwN\u0098\u00baI\u00e9\u00fa\u00d8\u00a8\u00b6\u00f2/\u00da\u009a\u00fd\u0011\u00ab\u00d9d\u009c<\u0003$_\u00fa\u00a1\u001db\u00f0p`\u00c2N\u00ba=\u001c\u00c6\u00a7\u0088\u00dag'v$Y\u00aa\u00dc\u0007P\u00f4\u00eei\u0082\u00d8 \u001c\u0092*\u00d8\nN\u00cf\u00f4h\u00aa\u000f\"ZK$\u0095\u00c5\u00a8x\u00e8\u00e6\u0099e@\u00b6\u00d2\u0087\u008e\u00fa\u00c1g\u00a8\u0010\u00b4\u00edo/\u0085\u00a4\u00a5\u00b8\u00f0\t\u0095\u000f\u00db\u00fe\fG \u00f3\u0010\u00fb@\u0083\u00deM\u0085C[\u00faG`\u00d8[\u00ae\u009a\u007f\u0002HP\u00d5=c'\u00a8\u00a7\u0018\u00c1\u00c7\u008b\u009b W\u00f6&L\u00d5\u00e4\u00a9Y08<\u00a3Ec}\u00d5\u008c\u008d\u00df\u0004j\u009c\u00f5\u0013 N\u00ec(\u00e2\u00e5y. t\u00af\u00d7\u00e6,\u00d9\u00d1\fL\u009c\u00d4\u00a6\u0006%\u008f\u00e6,\u0091\u00d1\u0084\u00efugP\u00db\u00e0\u00b6\u00e0\u0005\u00e8\u00d3\f \u009f\u00aaJ\u008a|\u00b3\u00b7X\u00f2\u00d4\u00a3\u008f#\u0088\u00d9\u00ae\u0003-\u0007H\u000e\u00f4\u0005\u00fe=v\u009b\u000e\rX\n,\u0010UB@8\u00ae\u000e\u00ec\u00b9}\u0086\u00cf\u00faeG\u00f7\u00e8\u0010+\u00f0\u001di\u009cx\u0002\u00de\u00b7\u00de\u0006\u00a8\u0012`4\u00f9\u0018s\u0016\u00e1\\1\u00a1\u001f5/Q5k\u00da7\u0000\u0098\u00be\u00de\u00e3H\u00f8\u00b5\u00c7\u009d\u0010\u0094\u001bc\u00b1g3\u00f4\u00a3\u009cO\u0091\u00ac\u0089\u008a$\u00c40\u00c8\u0088nt\u008d\u008bIG\u008dD?*Q\u0081\u0080\u00b0\u0018O\u0003M\u00acg\u00da\u00b0\u009b\u00ef3N4\u00d8\u0099\u009f\u00cda\u000f\u00ce\u00dbbm==\u0097\u00fa\u0001\u0086`\u00df\u00db \u000b;fW.\u008fE\u00f1'\r\u00c6\u00e2\u00f0\u00a2\u0015/\u00e3\u00e5\u00cb\u00d0\u0095\u00cc1#*\u0098+S\u008b3x\u009d8L_\u00fb\u0019 \u00f3\u00e2p9\u00d9\u00d5\u001f\u00cc%2\u00de\u00d3wH\u0099\u00d1\t\u0003!9.D\u0099\u00f7r\u00ac\u0093'S\u00d5Mx\u007f\u00f9\u00ec\u00abF\u00aaE\u00fb`|i\u0095/\u0006@\u008e\u00ce\u00e1\u00020\u0081\u00dc\u00bcP=4\u00df\u00ba\u00a3;\u0085\u00e1\u0019)o\u00e64o\u001d\u00cf\u00fd\u0014\u00ca)\u008fv\u00cf\u00f4o\u0089y\u001b\nzL\u0007\"\u00e2\u00ad\u00bc\u0019\u001cQ\u00cd\u00aa\u00f6\u00a3\b\u00185\u0082\u009b\f\u00d0\u00e7}Ii\u00f2\u00bc\u00ab\u0005\u00ed\u00a6#\u00e2\u00aeA\u00ac\"O\u00c5]\u0010\u0097\u00c6.\u009b\u00b3V7e\u009f&\u00d0V\u0012,D\u0097\u0018\u000f_\u0015<mh\u009af<\u0094f\u0000\u00b2\u008bz\u00e6{-\u00d9\t\u00d3r\u00b3\u00d5\u0018\u0006\u00be\u0080i}\u00b1}\u00e4\u00efZ\u00e8\u00e1\u00fa;\u0089}\u00b8q\u00ad\u00b4c:u\u00c2 s\u0007\u0083\u0096\u0094\u0019\u00c0g\u00aa\u001f\u00c4\u008aj\u00bc\u00bf\u007f\u00be#\u00d6\u0098\u00eb_\n\u0010\u00fb\u00d9\u0089[[x)\u00bb\u0010\u0086&\u00daD\u00db\u008b\u00a1\u00ff\u00db\u00e5N(k\u000b\u0003\u0099\u0018\u00d8\u00ef:\u0017\u001ekY=\u0002}\u008a\u00a3\u001a\u00b9\u00fd\u00fdQ\u008ea\u00f0\u007f\u0004Bwp\u00b8\u0014'j\u0017\u00c8\u00e3\u0011\u00feX\u000e9\u00f4U\u00b9k\u00a8]#\u0016\u00f14\u000e=*DI\u0099\u0086\u0090J0(\u00ab\u00ce\u00b1g\u0098\u0018|3\u0080\u00c3\u00aa\u00e3y\u00c4\u00fbo\u00fa\u0005x\u00f7\u0085\u00af\u0003\u00db\u0089\u0086\u0081\u009e\u0003\u00c1z\u00e7\u00f3A.\u0089\u00bb~\u009a\u00fa0\br\u0094\u00851\u00ef\u0083\u00bc\u00c3\u001e*\u00ae\u00c2&\u001eW\u00fft.N?\u0091G\u00b7N\u00bde,\u00ae\u00f8\u0098\u00ee\t\u009ae\u0006\u00f2|\u0018\u0006\u0087\u009fAZ\u00fdQ\u0005,;\u00d0\u00ae\u0011\u009e\u00faO\u00c9\u00aa\u00e9\u00e4A5\u001c& \u00f53\u00f9\u00e4\u00ec\u0001\u0096\u00a3w{\u000b\u00ba\u00a8\u00d5\n\u00c44\u0080\u00a4\u00a9{*\u00acjAWg\u0093\u00f6\u00c0\u00e14p0o\u00d2\u00fa\u0092\u008e\u00cd\f\u0096\u00ff\u00c2\u0006\u00bb\u00ca-\u0084\u00ae\u00db\u00ac\u00c1\u001e\u0090\u00fev\u00a1\u0015\u0089\u0089\u00ed\u00f9\u00d5\u0087\u00c0\u001f%#\u00ff\u0098\u0007q\u00bb\u00ae\u00aa\u009f\u00e4\u0015\u00a0\u008a_\u0014i\u00ae\\\u00fc\u00f1\u00ff\u00a21\u00ea\u00f7B&\u00f6\u00d6\f\u00e2\u00b4\u00c7,\u000e \u00c5\u0087\u00fd\u00b4\u00abI] \u00b3\u00a8\u001e\u0082J\u00c0\u0016\u00b1&%ju\u0010\u00c2\u0081z8\u00b3\u001cr\u00d5V\u0089D\u00b7\u0014@Ag\u0089\u001b\u001f\u0081\u0018%\u00ech\nM\u00ed\u009dC{\\\u0097\\i\u00f8\u00a9{a\u00d0oU\u007f\u00f39\u00b8 3\u00a0\u009a\r\u00f1\u00f2\u00c4.\u00e2/\u0094\u00bc\u00ec\u00b8\u0003\u00f6\u001b\u000bL\u00c4C/\u00c1\u007f:\u00de/-P\u00ad\u00c1\u0013 \u0018\u00af\u0096_\u00b7\u00b8)\u00cc\u00d2b\u00e0w]W\u0090\u001c\u00e3\u00ba=\u00aee\u009eQ\u008d R\u0085\u009f\u008b\u00ff{T\u0010U9!\u008aN\u00b1h;\u00a8\u00b7\u00d0y\u00b7Q\u00f0\u0080 n\u00f7\u00a5>\b \u00ac\u00e6\\\u0097\u00a0\u00c8q|U\u00d5\u0003\u0084\u000bF#\u00f9\u00a7\u00c0\u00b8\u00ca\u0084\u00a7L\u0098K\u00f7\u0018\u009f\u00adD`.Y\u0010\u00d3\u001d\u00ba\u008a,\u00ed\u00df\u0086\u00de\u0019l\u0004\u0016\u00da]p\u00c6\u0010\u0004\u00f1\u00ea\u00c7f\u0084\r\u00a9|?\u009a\u000e:n\u00a5\u007f \u00a9\u00a6\u0085\u00d9u\u0017\u0015\u00d4\u00dd\u0089\u0095 \u00ef\u00bd\u00c2\u0013\u008e\u0017\u0007\u0012\u00f4\u0000\u008b\u008e\u00bd\u00c9\u001c\u00ab\u00a1\u00d1%`\u0018\u001fF\u0098'B\t\u00ab\n\u00de\u001f\u00b3+Y;\u001fd\u00c6\u0094F\u00e3\u0084\u00cf\u00a3\u009808\u00c6\u0003'\u00e3\u00f6\u0088T\u0010>\u00e8s\u00e9\u000f\u008b4N\u0086\u0019\u00ae*\u00f7G<\u00f0\u0003\u00b0\u00a6\u00b1`\u0011\u0016\u0000\u00d1\u00d5\u0000\u00c3*\u0083\u00f3^\u0086F\u00bd{\u00e6\u0018\u00f10H/\u00a6\u00f3\u0005\u0006\u00c4\u00fb\u000e\u00d5\u00c9\u00e7\u00e30v\u00a3\u00f6j:\u00c3xq[H\u0010\u00e8\u00f6\u00fe@\u00b7\u00a8\u00cc\u008d8\u00b5F=\u00c7\u00a85\u0010\u00c42\u00bd5?=H\u0010\u00dc\u0087\u00e3\u00c8\n\u009b\u00a5_\u00daF\u000b\u0086\u0088s\u008b\u00fd\u0018H\u00cfI\u0095\u00d6D\u00e4\u000f\u00f0\u00eb\u009c{W\u0087#\u00d7`\u00acL\u00ad\u008f0\u0013\u00a9(,\u00b8\u00af\u00da\u00f1\u0085\u00cfw\u00eb#D\u008cdf\u0015\u00b8Sq\u00cd\u0003\u009e\u00b6|c\u00f4\u00bb_\u0019\u00f3\u0011\u00a2\u00deg\u00a8\u00d4\u00961\u0083\u0015# C\u00e1\u0014\u0080<8d\u00d8j-\u00fa7*`\u009c6\u00c3I\u00a3k\u00adi\u00a6\u00c6\u0089\u00d4\u00e1\u00c3h\u00be\u00b08 \u009eg{t\u00e9\u008e\u00d2\u0080\nI5\u00f3\u009dU\u0005P\u00fae\u0087\u00e1\u00c7\u00ca\u00e2\u0088g\u00c5\u000b\u00c2\u00df\u00b9\u00dc\u00f3\u0018\u0084h0\u00aen\u00fap2\u0098#\u00a9+\u00dbWMG\u00a9N\u00f4\u00c6!G\u00c9\u00c8\u0010\u001a\u00ae\u00ccd:b\u0017\u00c5\u00d6\u00c3\u00f6\u00df\u0098\u0080.\u00f0";
                        var17_6 = 2133;
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
                            var18_3[var16_4++] = Scaffold.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0002:V\u00ab\u00c1\u009e<\u00a6q\u001b\u0087\u00dd\u00f14n\u0013\n\u008cV@\u001a\u00d4f\u00d6\u0013\u00b7}\u00ba\"\u00d8\u0001R\u0087\u0003\u0012i\u001e\u00a2\u001eR\u0010\u00f4\u0016-\u00de\u00c4\u00f8\u00f0]0`\u00a0\u00d9\u009c\"\u00fe\u00c9";
                            var17_6 = 57;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = Scaffold.zkmDecodeUtf8(var19_9).intern();
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
                Scaffold.c = var18_3;
                Scaffold.e = new String[56];
                Scaffold.n = new HashMap<K, V>(13);
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
                var4_14 = "\u00e5\u009c\u00adMG\u007fit\u0095\u000f\u00ad\u00dd\u00da\u0095\u0014\u0086\u00ecCw`\u0096~G\u0085\u00e5\u00aa\u0090)\u00a6\u00a2PF\u001dr\u00c7y\u00c6\u00ceB\u00f7=\u00ce3INv(#M\u00a7-\u00dd\u00d1\u00a3\u00ffw|R@\u00c5\u00b9\u00feE\u009e\u00ae\u00c2\u0018w\u008a\u008a\u00a8xyh\u0015\u001a\u008e\u001c\u000f\u0000{._\u00c9<9\u00e0\u00c6{PF\tgl\u0095x\u00f9\u0010|fL.;\u00a65\n\u0005\u009bBj?\u0088\u00f4\u001bw\u00daX\u0017\u00b1m\u00858\u00a3\u00e5\u0092)\u008cl\u00d7Lt\u00005<\u00b8\u00e6%\u00bc\f$\u008f\u00ba\u00b6!\u00e2\u00d5\u0084\u009c\u0099a\u00d6\u00d8\u00c4\u0098\u00c1\u00c8\u0088\u0098\u00f0d\u0019\"\u00e8\u00b1\u00b15\u008c\u00d8\u00c8f\u008e\u00d6\u00fa\u009au\u0096\u00bc\u00c6E\u00b3\u00bb\u00b8/e";
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
                    var4_14 = "\u00a9l\u00b1\u00f0\n\u00ae?\u001d_\u00c5\u001bQy\u0098\u00ac\u00f6";
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
        Scaffold.k = var6_12;
        Scaffold.m = new Integer[25];
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Scaffold.class, "timing", "getTiming()Lsu/catlean/fb;", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "autoSwitch", "getAutoSwitch()Lsu/catlean/mq;", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "tower", "getTower()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "safeWalk", "getSafeWalk()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "allowShift", "getAllowShift()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "legit", "getLegit()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "saveAngles", "getSaveAngles()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "telly", "getTelly()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "clamp", "getClamp()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "airPlace", "getAirPlace()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "grimDelay", "getGrimDelay()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "keepY", "getKeepY()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "rotate", "getRotate()Z", 0)), Reflection.property1(new PropertyReference1Impl(Scaffold.class, "grimSilent", "getGrimSilent()Z", 0))};
        Scaffold.delegatedProperties = var29_20;
        Scaffold.INSTANCE = new Scaffold();
        Scaffold.timing$delegate = SettingFactory.modeSetting$default((SettingContainer)Scaffold.INSTANCE, "timing", (Enum)ScaffoldTimingMode.GRIM, null, null, 12, null);
        Scaffold.autoSwitch$delegate = SettingFactory.modeSetting$default((SettingContainer)Scaffold.INSTANCE, "switch", (Enum)ScaffoldAutoSwitchMode.Silent, null, null, 12, null);
        Scaffold.tower$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "tower", true, null, null, 12, null);
        Scaffold.safeWalk$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "safe-walk", false, null, null, 12, null);
        Scaffold.allowShift$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "allow-shift", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Scaffold.legit$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "legit", true, null, null, 12, null);
        Scaffold.saveAngles$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "save-angles", true, null, null, 12, null);
        Scaffold.telly$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "telly", false, null, null, 12, null);
        Scaffold.clamp$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "clamp", true, null, null, 12, null);
        Scaffold.airPlace$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "air-place", false, null, null, 12, null);
        Scaffold.grimBlockFly = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "grim-block-fly", false, null, null, 12, null);
        Scaffold.keepY$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "keep-height", false, null, null, 12, null);
        Scaffold.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "rotate", true, null, null, 12, null);
        Scaffold.grimSilent$delegate = SettingFactory.booleanSetting$default((SettingContainer)Scaffold.INSTANCE, "grim-silent", false, null, null, 12, null);
        Scaffold.V = new Timer();
        2187973559658555724L ^ var20;
        v15 = Queues.newConcurrentLinkedQueue();
        Intrinsics.checkNotNullExpressionValue(v15, "newConcurrentLinkedQueue(...)");
        Scaffold.receivedPackets = v15;
        v16 = Queues.newConcurrentLinkedQueue();
        Intrinsics.checkNotNullExpressionValue(v16, "newConcurrentLinkedQueue(...)");
        Scaffold.delayedPackets = v16;
        Scaffold.l = new Timer();
        var29_20 = new BlockPos[]{new BlockPos(0, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(-2, 0, 0), new BlockPos(2, 0, 0), new BlockPos(0, 0, 2), new BlockPos(0, 0, -2), new BlockPos(0, -1, 0), new BlockPos(1, -1, 0), new BlockPos(-1, -1, 0), new BlockPos(0, -1, 1), new BlockPos(0, -1, -1)};
        Scaffold.placeOffsets = var29_20;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x209C;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/uc", exception);
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
            Scaffold.e[n2] = Scaffold.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Scaffold.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x40FC;
        if (m[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = k[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])Scaffold.n.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    Scaffold.n.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/uc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Scaffold.m[n2] = n3;
        }
        return m[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Scaffold.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/uc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Scaffold.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Scaffold.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

