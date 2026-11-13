/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.rage.Speed$WhenMappings;
import su.catlean.module.rage.SpeedBoostAngleMode;
import su.catlean.module.rage.SpeedMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.SpeedCalculator;
import su.catlean.util.Timer;
import su.catlean.util.TimerManager;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class Speed
extends Module {
    @NotNull
    public static final Speed INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BooleanSetting useTimer$delegate;
    @NotNull
    private static final BooleanSetting pauseInLiquids$delegate;
    @NotNull
    private static final BooleanSetting pauseWhileSneaking$delegate;
    @NotNull
    private static final IntSetting fireWorkSlot$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final BooleanSetting armorStands$delegate;
    @NotNull
    private static final FloatSetting distance$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final FloatSetting groundSpeed$delegate;
    @NotNull
    private static final ModeSetting boostAngle$delegate;
    @NotNull
    private static final FloatSetting predictTicks$delegate;
    @NotNull
    private static final IntSetting rollBackTicks$delegate;
    private static double baseSpeed;
    private static int i;
    private static int t;
    private static float l;
    @NotNull
    private static final Timer speedTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] k;
    private static final Map m;

    private Speed() {
        super("speed", Categories.getRage(), null, null);
    }

    private final SpeedMode getMode() {
        return (SpeedMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getUseTimer() {
        return (Boolean)useTimer$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setUseTimer(boolean bl) {
        useTimer$delegate.setValue(this, delegatedProperties[1], bl);
    }

    private final boolean getPauseInLiquids() {
        return (Boolean)pauseInLiquids$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setPauseInLiquids(boolean bl) {
        pauseInLiquids$delegate.setValue(this, delegatedProperties[2], bl);
    }

    private final boolean getPauseWhileSneaking() {
        return (Boolean)pauseWhileSneaking$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setPauseWhileSneaking(boolean bl) {
        pauseWhileSneaking$delegate.setValue(this, delegatedProperties[3], bl);
    }

    private final int getFireWorkSlot() {
        return ((Number)fireWorkSlot$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final boolean getArmorStands() {
        return (Boolean)armorStands$delegate.getValue(this, delegatedProperties[6]);
    }

    private final float getDistance() {
        return ((Number)distance$delegate.getValue(this, delegatedProperties[7])).floatValue();
    }

    private final void setDistance(float f) {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
        distance$delegate.setValue(this, delegatedProperties[Speed.zkmBootstrap2("q", (int)27688, (long)(0x1E353E9A5C29D268L ^ l))], Float.valueOf(f));
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final void setSpeed(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        speed$delegate.setValue(this, delegatedProperties[Speed.zkmBootstrap2("q", (int)5333, (long)(0x7CCEF6B2ADBB4F8AL ^ l))], Float.valueOf(f));
    }

    private final float getGroundSpeed() {
        return ((Number)groundSpeed$delegate.getValue(this, delegatedProperties[9])).floatValue();
    }

    private final void setGroundSpeed(float f) {
        long l = 0L;
        l = a ^ l;
        groundSpeed$delegate.setValue(this, delegatedProperties[Speed.zkmBootstrap2("q", (int)12275, (long)(0x572652B9895596F4L ^ l))], Float.valueOf(f));
    }

    private final SpeedBoostAngleMode getBoostAngle() {
        return (SpeedBoostAngleMode)((Object)boostAngle$delegate.getValue(this, delegatedProperties[10]));
    }

    private final void setBoostAngle(SpeedBoostAngleMode speedBoostAngleMode) {
        long l = 0L;
        l = a ^ l;
        boostAngle$delegate.setValue(this, delegatedProperties[Speed.zkmBootstrap2("q", (int)14312, (long)(0x1286F2F112E7802CL ^ l))], (Object)speedBoostAngleMode);
    }

    private final float getPredictTicks() {
        return ((Number)predictTicks$delegate.getValue(this, delegatedProperties[11])).floatValue();
    }

    private final void setPredictTicks(float f) {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 56 | l << 8 >>> 8) ^ a;
        predictTicks$delegate.setValue(this, delegatedProperties[Speed.zkmBootstrap2("q", (int)8545, (long)(0x4FBD3EF215A64D82L ^ l2))], Float.valueOf(f));
    }

    private final int getRollBackTicks() {
        return ((Number)rollBackTicks$delegate.getValue(this, delegatedProperties[12])).intValue();
    }

    private final void setRollBackTicks(int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 40 >>> 32 | (long)n4 << 56 >>> 56) ^ a;
        rollBackTicks$delegate.setValue(this, delegatedProperties[Speed.zkmBootstrap2("q", (int)30124, (long)(0x626481201945E521L ^ l))], n);
    }

    @Override
    public void onEnable(long l) {
        i = 1;
        t = 0;
        baseSpeed = 0.2873;
        speedTimer.reset();
    }

    @Flow
    private final void onPreSync(PreSyncEvent preSyncEvent) {
        block2: {
            block3: {
                if (Globals.getPlayer().isInFluid() && this.getPauseInLiquids() || Globals.getPlayer().isSneaking() && this.getPauseWhileSneaking()) {
                    return;
                }
                if (this.getMode() != SpeedMode.MATRIX) break block2;
                boolean bl = false;
                Iterator<VoxelShape> iterator2 = Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().expand(0.5, 0.0, 0.5).offset(0.0, -1.0, 0.0)).iterator();
                while (iterator2.hasNext()) {
                    VoxelShape voxelShape;
                    Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
                    if (Intrinsics.areEqual(voxelShape, VoxelShapes.empty())) continue;
                    bl = true;
                    break;
                }
                if (!MovementUtil.INSTANCE.isMoving() || !bl || !(MovementUtil.INSTANCE.getFallDistance() <= 0.0)) break block3;
                Globals.getPlayer().setOnGround(true);
                Globals.getPlayer().jump();
                break block2;
            }
            if (!(MovementUtil.INSTANCE.getFallDistance() > 0.0) || !this.getUseTimer()) break block2;
            TimerManager.INSTANCE.x(1.088f);
            Globals.getPlayer().addVelocity(0.0, -0.003, 0.0);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     */
    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block80: {
            block94: {
                block91: {
                    block93: {
                        block92: {
                            block89: {
                                block90: {
                                    block88: {
                                        block87: {
                                            block86: {
                                                block84: {
                                                    block85: {
                                                        block83: {
                                                            block81: {
                                                                block82: {
                                                                    block59: {
                                                                        block68: {
                                                                            block69: {
                                                                                block60: {
                                                                                    block70: {
                                                                                        block67: {
                                                                                            block66: {
                                                                                                block64: {
                                                                                                    block65: {
                                                                                                        block62: {
                                                                                                            block63: {
                                                                                                                var2_2 = Speed.a ^ 53945430889767L;
                                                                                                                v0 = var2_2 ^ 11335993749629L;
                                                                                                                var33_3 = (int)(v0 >>> 48);
                                                                                                                var34_4 = v0 << 16 >>> 16;
                                                                                                                var69_5 = SpeedBoostAngleMode.zkmIntConstant();
                                                                                                                v1 = Globals.getPlayer().isInFluid();
                                                                                                                if (var69_5 != 0) break block62;
                                                                                                                if (!v1) break block63;
                                                                                                                v1 = this.getPauseInLiquids();
                                                                                                                if (var69_5 != 0) break block62;
                                                                                                                if (v1) break block64;
                                                                                                            }
                                                                                                            v1 = Globals.getPlayer().isSneaking();
                                                                                                        }
                                                                                                        if (var69_5 != 0) break block65;
                                                                                                        if (!v1) break block66;
                                                                                                        v2 = this;
                                                                                                        if (var69_5 != 0) break block67;
                                                                                                        v1 = v2.getPauseWhileSneaking();
                                                                                                    }
                                                                                                    if (!v1) break block66;
                                                                                                }
                                                                                                return;
                                                                                            }
                                                                                            v2 = this;
                                                                                        }
                                                                                        v3 = v2.getMode();
                                                                                        v4 = SpeedMode.GRIM_ENTITY;
                                                                                        if (var69_5 != 0) break block68;
                                                                                        if (v3 != v4 && v3 != SpeedMode.GrimBypass) break block69;
                                                                                        var87_6 = this.getRollBackTicks();
                                                                                        SpeedCalculator.INSTANCE.updateAll(var87_6);
                                                                                        v5 = MovementUtil.INSTANCE.isMoving();
                                                                                        if (var69_5 != 0) break block70;
                                                                                        if (!v5) break block69;
                                                                                        v5 = false;
                                                                                    }
                                                                                    var70_7 = (int)v5;
                                                                                    v6 = Globals.getWorld().getEntities();
                                                                                    Intrinsics.checkNotNullExpressionValue(v6, "entitiesForRendering(...)");
                                                                                    var72_8 = v6;
                                                                                    var74_11 /* !! */  = var72_8;
                                                                                    var75_13 = new ArrayList<E>();
                                                                                    for (T var78_15 : var74_11 /* !! */ ) {
                                                                                        block74: {
                                                                                            block72: {
                                                                                                block73: {
                                                                                                    block71: {
                                                                                                        var79_16 = (Entity)var78_15 /* !! */ ;
                                                                                                        v7 = (int)Intrinsics.areEqual(var79_16, Globals.getPlayer());
                                                                                                        if (var69_5 != 0) break block59;
                                                                                                        if (var69_5 != 0) break block71;
                                                                                                        if (v7 != 0) ** GOTO lbl-1000
                                                                                                        v8 = var79_16 instanceof ArmorStandEntity;
                                                                                                    }
                                                                                                    if (var69_5 != 0) break block72;
                                                                                                    if (!v8) break block73;
                                                                                                    v8 = Speed.INSTANCE.getArmorStands();
                                                                                                    if (var69_5 != 0) break block72;
                                                                                                    if (!v8) ** GOTO lbl-1000
                                                                                                }
                                                                                                v8 = var79_16 instanceof LivingEntity;
                                                                                            }
                                                                                            if (var69_5 != 0) break block74;
                                                                                            if (v8) ** GOTO lbl-1000
                                                                                            v8 = var79_16 instanceof BoatEntity;
                                                                                            if (var69_5 == 0) {
                                                                                                ** if (!v8) goto lbl-1000
                                                                                            }
                                                                                            break block74;
lbl-1000:
                                                                                            // 2 sources

                                                                                            {
                                                                                                v8 = true;
                                                                                                ** GOTO lbl71
                                                                                            }
lbl-1000:
                                                                                            // 3 sources

                                                                                            {
                                                                                                v8 = false;
                                                                                            }
                                                                                        }
                                                                                        if (!v8) continue;
                                                                                        var75_13.add(var78_15 /* !! */ );
                                                                                        if (var69_5 == 0) continue;
                                                                                    }
                                                                                    var71_17 = ((List)var75_13).iterator();
                                                                                    block6: while (true) {
                                                                                        v9 = var71_17.hasNext();
                                                                                        while (v9 != 0) {
                                                                                            block77: {
                                                                                                block75: {
                                                                                                    block76: {
                                                                                                        v10 = var71_17.next();
                                                                                                        Intrinsics.checkNotNullExpressionValue(v10, "next(...)");
                                                                                                        var72_8 = (Entity)v10;
                                                                                                        var73_20 = (int)Globals.getPlayer().getBoundingBox().expand(this.getDistance()).intersects(var72_8.getBoundingBox());
                                                                                                        v11 = var75_13 = SpeedCalculator.INSTANCE.W();
                                                                                                        if (var69_5 != 0) break block75;
                                                                                                        v12 = v11.isEmpty();
                                                                                                        if (var69_5 != 0) break block60;
                                                                                                        if (!v12) break block76;
                                                                                                        v13 = false;
                                                                                                        break block77;
                                                                                                    }
                                                                                                    v11 = var75_13;
                                                                                                }
                                                                                                var77_14 = v11.entrySet().iterator();
                                                                                                block8: while (true) {
                                                                                                    block79: {
                                                                                                        block78: {
                                                                                                            if (!var77_14.hasNext()) break block78;
                                                                                                            var78_15 /* !! */  = (Map.Entry)var77_14.next();
                                                                                                            var79_16 = var78_15 /* !! */ ;
                                                                                                            v14 /* !! */  = (Number)var79_16.getKey();
                                                                                                            if (var69_5 != 0) ** GOTO lbl110
                                                                                                            break block79;
                                                                                                        }
                                                                                                        v13 = false;
                                                                                                        break;
                                                                                                    }
                                                                                                    v15 = v14 /* !! */ .intValue();
                                                                                                    do {
                                                                                                        block61: {
                                                                                                            if (v15 != var72_8.getId()) ** GOTO lbl-1000
                                                                                                            v14 /* !! */  = var79_16.getValue();
lbl110:
                                                                                                            // 2 sources

                                                                                                            v16 = var81_22 = (Iterable)v14 /* !! */ ;
                                                                                                            if (var69_5 != 0) ** GOTO lbl120
                                                                                                            if (!(v16 instanceof Collection)) ** GOTO lbl-1000
                                                                                                            v16 = (Collection)var81_22;
                                                                                                            if (var69_5 == 0) {
                                                                                                                ** if (!v16.isEmpty()) goto lbl-1000
lbl-1000:
                                                                                                                // 1 sources

                                                                                                                {
                                                                                                                    v17 = false;
                                                                                                                    ** GOTO lbl129
                                                                                                                }
                                                                                                            }
                                                                                                            ** GOTO lbl120
lbl-1000:
                                                                                                            // 2 sources

                                                                                                            {
                                                                                                                v16 = var81_22;
lbl120:
                                                                                                                // 4 sources

                                                                                                                for (T var84_24 : v16) {
                                                                                                                    var85_25 = (Box)var84_24;
                                                                                                                    v18 = Globals.getPlayer().getBoundingBox().expand(Speed.INSTANCE.getDistance()).intersects(var85_25);
                                                                                                                    while (v18) {
                                                                                                                        v18 = true;
                                                                                                                        if (var69_5 != 0) continue;
                                                                                                                        break block61;
                                                                                                                    }
                                                                                                                }
                                                                                                                v17 = false;
                                                                                                            }
                                                                                                        }
                                                                                                        if (v17) {
                                                                                                            v19 = true;
                                                                                                        } else lbl-1000:
                                                                                                        // 2 sources

                                                                                                        {
                                                                                                            v19 = false;
                                                                                                        }
                                                                                                        if (!v19) continue block8;
                                                                                                        v15 = 1;
                                                                                                    } while (var69_5 != 0);
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            var74_12 = v13;
                                                                                            if (var69_5 == 0) {
                                                                                                if (!var74_12) {
                                                                                                    v9 = var73_20;
                                                                                                    if (var69_5 != 0) continue;
                                                                                                    if (v9 == 0) continue block6;
                                                                                                }
                                                                                                ++var70_7;
                                                                                            }
                                                                                            if (var69_5 != 0) break block6;
                                                                                            continue block6;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                    v12 = Globals.getPlayer().isOnGround();
                                                                                }
                                                                                var71_18 = v12 != false ? this.getGroundSpeed() : this.getSpeed();
                                                                                switch (Speed$WhenMappings.k[this.getBoostAngle().ordinal()]) {
                                                                                    case 1: {
                                                                                        v20 = RotationManager.INSTANCE.hasActiveRequest(true).getYaw();
                                                                                        break;
                                                                                    }
                                                                                    case 2: {
                                                                                        v20 = Globals.getPlayer().getYaw();
                                                                                        break;
                                                                                    }
                                                                                    case 3: {
                                                                                        var75_13 = Aura.INSTANCE.getTarget();
                                                                                        if (Aura.INSTANCE.getEnabled() && var75_13 != null) {
                                                                                            v21 = this.getPos((Entity)var75_13).add(new Vec3d(var75_13.getX() - var75_13.lastX, var75_13.getY() - var75_13.lastY, var75_13.getZ() - var75_13.lastZ).multiply(this.getPredictTicks(), this.getPredictTicks(), this.getPredictTicks()));
                                                                                            Intrinsics.checkNotNullExpressionValue(v21, "add(...)");
                                                                                            v20 = Rotation.Companion.to(v21).getYaw();
                                                                                            break;
                                                                                        }
                                                                                        v20 = RotationManager.INSTANCE.hasActiveRequest(true).getYaw();
                                                                                        break;
                                                                                    }
                                                                                    default: {
                                                                                        throw new NoWhenBranchMatchedException();
                                                                                    }
                                                                                }
                                                                                var72_9 = v20;
                                                                                var74_11 /* !! */  = (Item[])MovementUtil.getMovement$default(MovementUtil.INSTANCE, var71_18 / 100.0f * (float)var70_7, false, 0.0f, 0.0f, var72_9, 14, null);
                                                                                Globals.getPlayer().addVelocity((double)var74_11 /* !! */ [0], 0.0, (double)var74_11 /* !! */ [1]);
                                                                            }
                                                                            v3 = this.getMode();
                                                                            v4 = SpeedMode.FIREWORK;
                                                                        }
                                                                        if (v3 != v4) break block80;
                                                                        var70_7 = Speed.t;
                                                                        Speed.t = var70_7 + -1;
                                                                        v7 = InventoryUtil.INSTANCE.findElytra();
                                                                    }
                                                                    var70_7 = v7;
                                                                    var72_8 = new Item[1];
                                                                    Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
                                                                    var71_19 = InventoryUtil.INSTANCE.findInHotbar((Item[])var72_8);
                                                                    var72_10 = Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET);
                                                                    v22 = var71_19.isFound();
                                                                    if (var69_5 != 0) break block81;
                                                                    if (v22 != 0) break block82;
                                                                    var74_11 /* !! */  = new Item[1];
                                                                    Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
                                                                    var73_21 = InventoryUtil.INSTANCE.findInInventory(var74_11 /* !! */ , (char)var33_3, var34_4);
                                                                    v22 = (int)var73_21.isFound();
                                                                    if (var69_5 != 0) break block81;
                                                                    if (v22 != 0) {
                                                                        InteractionUtil.clickSlot$default(var73_21.getSlot(), this.getFireWorkSlot() - 1, SlotActionType.SWAP, false, 8, null);
                                                                    }
                                                                }
                                                                v22 = var70_7;
                                                            }
                                                            if (var69_5 != 0) break block83;
                                                            if (v22 == -1) break block80;
                                                            v22 = (int)var71_19.isFound();
                                                        }
                                                        if (var69_5 != 0) break block84;
                                                        if (v22 != 0) break block85;
                                                        v22 = (int)var72_10;
                                                        if (var69_5 != 0) break block84;
                                                        if (v22 == 0) break block80;
                                                    }
                                                    v22 = (int)Globals.getPlayer().isOnGround();
                                                }
                                                if (var69_5 != 0) break block86;
                                                if (v22 != 0) break block80;
                                                cfr_temp_0 = MovementUtil.INSTANCE.getFallDistance() - 0.0;
                                                v22 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                                            }
                                            if (var69_5 != 0) break block87;
                                            if (v22 <= 0) break block80;
                                            v22 = Speed.t;
                                        }
                                        if (var69_5 != 0) break block88;
                                        if (v22 > 0) break block80;
                                        v22 = var70_7;
                                    }
                                    if (var69_5 == 0) {
                                        if (v22 != -2) {
                                            InteractionUtil.clickSlot$default(var70_7, 0, null, false, 14, null);
                                            InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                                            InteractionUtil.clickSlot$default(var70_7, 0, null, true, 6, null);
                                        }
                                        ElytraSwap.INSTANCE.K(true);
                                        PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                                        Globals.getPlayer().startGliding();
                                        v22 = Globals.getPlayer().getInventory().getSelectedSlot();
                                    }
                                    v23 = var73_20 = v22;
                                    if (var69_5 != 0) break block89;
                                    if (v23 == var71_19.getSlot()) break block90;
                                    v23 = (int)var72_10;
                                    if (var69_5 != 0) break block89;
                                    if (v23 == 0) {
                                        InventoryUtil.INSTANCE.selectSlot(var71_19.getSlot());
                                    }
                                }
                                v23 = (int)var72_10;
                            }
                            InteractionUtil.useItem$default(v23 != 0 ? Hand.OFF_HAND : Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
                            v24 = var73_20;
                            v25 = var71_19.getSlot();
                            if (var69_5 != 0) break block91;
                            if (v24 == v25) break block92;
                            v24 = (int)var72_10;
                            if (var69_5 != 0) break block93;
                            if (v24 == 0) {
                                InventoryUtil.INSTANCE.selectSlot(var73_20);
                            }
                        }
                        v24 = var70_7;
                    }
                    if (var69_5 != 0) break block94;
                    v25 = -2;
                }
                if (v24 != v25) {
                    InteractionUtil.clickSlot$default(var70_7, 0, null, false, 14, null);
                    InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                    InteractionUtil.clickSlot$default(var70_7, 0, null, true, 6, null);
                }
                v24 = this.getDelay();
            }
            Speed.t = v24;
        }
    }

    /*
     * Exception decompiling
     */
    @Flow
    private final void onMove(MoveEvent var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:461)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:251)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:673)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:56)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:722)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == SpeedMode.NCP || INSTANCE.getMode() == SpeedMode.STRICT_STRAFE || INSTANCE.getMode() == SpeedMode.MATRIX || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == SpeedMode.FIREWORK;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == SpeedMode.FIREWORK;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == SpeedMode.GRIM_ENTITY || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() == SpeedMode.GRIM_ENTITY || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getMode() == SpeedMode.GRIM_ENTITY || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getMode() == SpeedMode.GRIM_ENTITY || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getMode() == SpeedMode.GRIM_ENTITY || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible9() {
        int n = SpeedBoostAngleMode.zkmIntConstant();
        Speed speed = INSTANCE;
        if (n == 0) {
            if (speed.getMode() != SpeedMode.GRIM_ENTITY) return false;
            speed = INSTANCE;
        }
        if (speed.getBoostAngle() != SpeedBoostAngleMode.PREDICT) return false;
        return true;
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getMode() == SpeedMode.GRIM_ENTITY || INSTANCE.getMode() == SpeedMode.GrimBypass;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Speed.a = ZkmKeys.a(3763489360545130017L, 5495694745628708890L, MethodHandles.lookup().lookupClass()).a(168808784020748L);
                        var20 = Speed.a ^ 1284142124620L;
                        Speed.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[45];
                        var16_4 = 0;
                        var15_5 = "I\u00b0b\rfO\u00bb*\u007fLKH}2\u00a4\u00f5\u00fb@\u009c\u00cfO\u00ab\u00fe\u009e8{\u00f2<\u0090<\u00cf\u00f8\u0080\u00ce\u00e0\u00c8\u00e4\n\u00f7\u00d4\u001c0\u00e2$Cp\u0090\u001f\u00adS:\u00ba\u0082C\u0094\u00fe(\u00b9\u00d5%9\u0099\u00d4\u00ee\u001cy\u001ah\u00bd\u00fc4\u00d2\u00fa\u00f5#aV1V\u00e1x\u00dc~0\u00fd~\u00af\u001aQ\u00eaF\u00aej9\u00b6\u00b0\u00df \u001e\\\u00f1)J\u008d\u000e\u00e3n\u00b5\u00e5\u00ccn\u00c7\u00fa^\u00bd=\u008bQ:\u00a5\u00fd\u0011\u00d7\u001eD$\"R\u00c3\u00b0 >z\u00c3c\u001f\u00d2\u0092U\u00ac3n\u00be\u0005\u00d8\u00ba\u00c3\u0089\u00e3#V\u00f2\u00b8;S\u00dd \u00b4Iu \u00da\u00fd\u0010\u00a2c\u00e9\u00bb\u008a\u0001\u009f\u00e5\u0001\u00fd\u00d55\u00ed-\u0083Z \u00dd\"\u000f@\u0096C{F\u008d\u00d9\u00c1\u00e1\u00ad\u00d3\t\u008e\u0010\u001e\u0001\u0086\u00fdz#\u007f\u00e4\u00b6v>\u0096\u009a\u00b7\u0091\u0018\u00b3\u00c5M\tR{\u008ed\u009e={\u000eH\u00e7D\u00d0\u00d3\u0081\u0006h/\u0088h\u00bd \u00d6x+U\u00ad\u00ee\u00d0\u0091t\u00d5\u0082\u00b5c\u0083\u0007C\u00f9\u00cc'\u0013\u00d6n\u00a4\u0081;\u0003!\u000e\u00a3a\u00a0\u00b1 \u00b4G\u00c4\u0098$u?\u00d8l\u00d0\u0087\u00f1\u00f0\u00b8\u001f\u00c4w/\u00f8\u0089\u00ef\u00e6\u00e3g\u00d7\u0084:X\u000f\u009e\u00d8\u00b08*K\u0087\u008a\u00f4ru\u00a8\u00be\u0083\u0005h`\u0010\u00c1\u00a4\u00d5\u00fe\n\u008e[q\u0096\u00f6\u00d2\u008c\u00b7\u00f7\u00ca\u00cd\u00c0*\u009c\u0016h\u0085\u00f3X\u0003\u0000\u008cw\u00eeo\u00b7j\u00f0\u0003\u0089\\f*\u0094\u00b5\u00e8D(^,\u00f8\u009b\u0098\u00ad\u00d5\u001e$y]\u0083\u00f4\u0013x,U\u0086p\u00aa\u00c9\u00ec\u00a9\u0099\u0019\"\u00ca$\u00d6)\u00ab\u00f2\u00c9\u00b5\u00cfB\u008d\u00b1h\u00a50\u00e3\u00e2\u00fa\u00ec\u00a7\b\u00bf\"&^\u00a8K\u00c7y\u008b==Q\u00d8\u00f6\u0002\u00a5\u00ab\u00d5\u00dc\u00f9f\u0011\u00ab\u0001\u00a2:\u00ff\u00c1\u00ae\u00b8#\u00b9\u00bdh6\u00a5.4\u00fd\u008a\u0097\u00cb\u0010\u00c2\u00fb\u000eC<@\u00d0\u008a\u00b7\u0081\u00ec\u00e8\u00c4\u00ca\u00ca\u00ce -\u009eU\u0013?\u00f1M#\u00ef{O\u0085P%e(WVk_'\u00f1+\u0082\u00d8\u00cc(\u00b2\u0093.\u00ee\u00af\u0010\u0000\u00bea\u00cbTeP\u0011\u00dcl\u000b\u00f0\u00b1\u009c\u009e\u00dc8\u0095\u00f2\u00be\u0007P\u008f\u00c2\u0086\u0005\u008d@\b5L\u008f\u00a4\u00a6_\u00e8\u00eeK\u00b9\b6Y\u00abz\rq\u0086Ma\u00cc\u00d2\u00d5K\u00b1\u00eagw#\u00e0H\u0005,e^r)<\u0088\u00fb((O\u00db \u00ee\u0081\u00c06\u0087\u007f!\u00ac\u00f9W\u00c2\u00c0\u0007\u00dc\u00ea\u00c5\u0081N\u0014\u00e0\u00b1h\u00ca?\t\u001f\u0081\u00e2p_!\u009a\u0018T\u00d5\u00a1W\u0013\u00b2*\u00a5\u00d1\u008f/}\u008e\u0096\u00c2\u00ef\u00b3\u00bc\u00ee\u00db\u00f3\u00bf\u0095\u00f5(\u0095Kg<\u00c1\u0006k\u0085~\u00d1\u009dd^J\u00fd\u0097@\u00ac\u00c8\u008a\u0087e:\u001e\u0014\u00bc\u00d3\u0001kV\u00d0\t\u0095\u001fe\u00ba\u000e\u0011q=\u0018;\u00b2\u00ad\u008bG\u00c9\u00bf\u00c9\u00a1t\u00fa{\u00c2\u00af\u00cb\u00c7\u00f2\u00f9\u00a9K\u00ce\u00c8h\u00ff(.\u00b2\u0018\u00a1\u0094w\u0089\u00b2a\u000b91b\u00d3V\u00f2H\u0015}\u0007&\u00d4\u00c5@u\u009dA8avW-\u00f9Q*\u00a8\u00b3Q\u001f\u001f\u0018\u00b0\u0003\f\u00c4\u0000\u00e9\u00bf_\u009f\u00b2W\u00ef\u008d\u0094\u009d`0\u00e9\u00bbI+\u00a8\u00dc\u008a \u00fd+\u0081t\u00edo\u00c5\u00f8\u00fd\u0095\u00f2\u00f5\u0098\u000e\u00c0\u00bc\u0017\u00a4\u00a2\u0000q\u00c8\u00fc\u00ae\u00a1r\u00f5\u0096\u00f1\u0007\u00af\u00f0(\u0080v+\u00b9\u00a4\u00dd\u00f3:\u00dc\u00a1 \u00bf\u00a1\u0085T\u00d5\u0006.D\u009cdih\u00b0\t\u00e8\u00cd\u00d5\u008b]5\u00beM\u00c5\u00bd\u0080\u0002\u00fdM\u00b0 \u00d8^ae\u00a7=>!\u008a\"\u001bj\u0095\u0080\u00c5\u00ac\u00a0\u0016J\t%\u00f7\u00bdj0\u00a0\u00da\u00dc\u0000V\u00b1\u0017 \t{\u0089\u00b0o=|\u00866\u009b6RcG\u009f\u00b8Sg\u00ba\u0015\u00c6\u001dzb\u0092\u00faS\u008e\u000b\u00c9\u001b>\u0018\u0010\u0015\u00b5T\u00e7\u00064dl\u00f6\u008a\u00ff\u00c3\u00f7\u0098\u0005Y\u00e4\u008f\u00c0\u0086Vz\u00ea Z\u0083yU\u0011\u00fc\u00c0\u00cc\u0018^M\u0013\r\u00ab\u00fb\u00dd\u00fb\u00bdY\u00b4`\u00d3\u00ec.\u00fa?\u0016l\u0007\u00be\u0091\u0080(5\u000b\b.4\u00d3\u00c8\u0080\u00c2\u00ed}\u00be\u00fa\u009aXz\u0091\u00d7\u0098\u00ab\u00e4c\u0095U\u001c\u00b5;5\u0019\u00ac\u00bbD\u00f3rI:\u00bc\u00b1\u00d3\u001f\u0018\u00f7\u00c0\u00c2\u00be\u0017\u0014G\u0096s\u00d69j\u008c-\u00ce\u00bc\u0095\u00f0k!\u0002@\u00e5y\u0010)\u00d3x\u0003m\u008f\u001c\u00ff\u00e9\u00af\u0091`\u00cd\u000f\u009fQ .8\u00b6\u00d8\u00b3\u0086\u00e6;1\u00b5.\u0016\u00d8\u009c$0\u00fcf/\u00dc\u008a\u00bcU\u00ccV\u00c0\u009e\u00df\u0012\u00d6\u0011\u00e9 x\u00d7\rg\u00df\u0087]\u00ae\u00d566\u00b71\u00d5l\u00b0\u00db\u00d4\u0097B\u00cd\u00b8\u000b\u00d4\u000f\u009d\u0082>mm~\u001e(\u00a3\u00ab\u00be\u00cb\u00a4\u00d5\u00c81\u00a3\u00e4\u0098\u00cc\u00a4\u00cf?\u00f9\u0089\u00f4v1\u00a0\u00a7\u00f4oA3az%cY9_:\u00d3m\u009c\u009eJ\u00b7\u0018E\u009c\u00d6[\u001b\u00a1}{\u0019r\u00f6\u008b\u00f6\u00d7R\u0084j\u00f9~w=\u008a\u00e0\u0005 !\u00e2\u00df\u001d\u008d\u00dcR;\u0098\u00f86b\u00f7@\u00cd\u00a0\u0081\u0086!'1U\u00d8\u00c5\u00d5\u001f\u00ed\u00af8\u00fe\u009aG(\u00f2\u00a3\u00eb\u00ec\u0005\u00d4R\u008a\u00dc\u0001n\u00be4\u001e\u00f4-u6)\u0018G\u0098]\u00e2gK\u0080pp}\u00fc\u00a8\b\u00bd\u0013&\u000bD\u00c7\u00f3 \u008dJ[\u00fb\u00a7\u00f4\u00e3P\\\u009ap_y:\u00ab\u00e3\u00bdk5\u0084'\u0005\u0085\u00ff8\u00e3\u00be?\u00b6\u0018o\u00cc\u0010\u009f\u001e\u00aeo[\u00c4\u00fc\u00b2\u00e1\u0084\u00f8\u0011\u0096p\u007fY\u0010-\u00efN-\u00ec\u0080\u00ce\u00ac\u00a0\u0000m\u00b8\u00f9\u00eb\u00d9p\u0018\u00be\u00de[\u00f7j\u00bcB\u0014\u008cu\u009bvo:\u00a4\u00aa\u00b6\u00e1\u00dcF+$.\u00dd ;\u001bI[\u00b2\u00ae\u00fb\u00b8n\u00b9O6\u0006\u0010\u00f9\u008e`\u0012\r\u00e8:Z3\u00174\u00ec\u0098\u00e3\u00c5\u00df\u0010/(d\u001b>\u0099<\u001e\u00ab\u000782\u00b4\u00f8\u0097\u001e2\u00a6\u000f\u0006\u00f1\u00c7\u0003\u0017\u0080\u00e5\u008e\u008bC2\u00d3\u00a3\u00b4\u001cY\u009e\u0097aW\u00c5\u00a0Z";
                        var17_6 = 1418;
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
                            var18_3[var16_4++] = Speed.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "ZT\u001c\u00ebP\u0004\u001f3\u0014\u00b8\u00c6\u00d23Q+\u0006n\u0015\u001eo\u00f0\u00ee\u00f5\u00ec\u00fe\u00bc(\u007f\u00cf\u0014\u0017\u00ee\u0018\u00d7\u00c1F\u00daA\u00c30M\u00d8|\u00d9\u001e\u0083o \u00b1\u00b3W\u0099y\u00ca\u00f9\u00df\u00c7";
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
                            var18_3[var16_4++] = Speed.zkmDecodeUtf8(var19_9).intern();
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
                Speed.b = var18_3;
                Speed.e = new String[45];
                Speed.m = new HashMap<K, V>(13);
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
                var4_14 = "\u00a9\u00ff\u00c5L\u0097\u00bb\t\u008dpn\u0011\u00f7\u00fa/\u00b4\u0017k\u00efr\u0095%i\u001d?(\u00bd\u00f2\u00b0R\u0080\u00c5\u00e5\u001etAt\u00e2r\u008d\u00d8\u000b\u0002\u00f5\u00d7+1\u00bcU\u0015{\u00ed<{,\u000e\u00df\u00927\u00bf\u00fc(\u008d\u00ff\u00dd_\u00fd>\u00d3VB8\u00f9\u00ac\u0089{`g\u00b7tk\u0000gi\tBZ\u008a\u00a9\u0013\u00c1(\u00b3\u00ffNy\u00cb/\u0004\u00b6\u00e5\u00af\u0017\u0005f\u00eft\u00d0\"\u001a\u00b0\u00f1\u00fa\u00d1H\u001f\u00940m\u009e\u00f9\u00e68\u00f9\u00ee\u00a4\u00c2\u00ab\u001b\u00e7`\u00d5\u00d6\u00900m\u0005c\u0094\u00b6Q\u00da\u00dd\u001a\u00c1\u00fb\u001d\u00b5\u00d5\u009dg\u00cbJ\u0099-B!.}\u00c7\u00cdR\u001f\u00e1f\u008d\u009f\u00dd\u00ff.\u0004\u00d0{\u00d1l1W\u00a9\u008c\u00ea\u0006q77\u0012";
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
                    var4_14 = "\u00cdY \u00149\u00a9\u0087)F\u00c5\u00a1\u0092*/\u009dv";
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
        Speed.g = var6_12;
        Speed.k = new Integer[25];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Speed.class, "mode", "getMode()Lsu/catlean/gq;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "useTimer", "getUseTimer()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "pauseInLiquids", "getPauseInLiquids()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "pauseWhileSneaking", "getPauseWhileSneaking()Z", 0)), Reflection.property1(new PropertyReference1Impl(Speed.class, "fireWorkSlot", "getFireWorkSlot()I", 0)), Reflection.property1(new PropertyReference1Impl(Speed.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(Speed.class, "armorStands", "getArmorStands()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "distance", "getDistance()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "speed", "getSpeed()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "groundSpeed", "getGroundSpeed()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "boostAngle", "getBoostAngle()Lsu/catlean/px;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "predictTicks", "getPredictTicks()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Speed.class, "rollBackTicks", "getRollBackTicks()I", 0))};
        Speed.delegatedProperties = var32_20;
        Speed.INSTANCE = new Speed();
        Speed.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Speed.INSTANCE, "mode", (Enum)SpeedMode.NCP, null, null, 12, null);
        Speed.useTimer$delegate = SettingFactory.booleanSetting$default((SettingContainer)Speed.INSTANCE, "use-timer", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Speed.pauseInLiquids$delegate = SettingFactory.booleanSetting$default((SettingContainer)Speed.INSTANCE, "pause-in-liquids", false, null, null, 12, null);
        Speed.pauseWhileSneaking$delegate = SettingFactory.booleanSetting$default((SettingContainer)Speed.INSTANCE, "pause-while-sneaking", false, null, null, 12, null);
        Speed.fireWorkSlot$delegate = SettingFactory.intSetting$default(Speed.INSTANCE, "fire-slot", 1, new IntRange(1, 9), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.delay$delegate = SettingFactory.intSetting$default(Speed.INSTANCE, "delay", 8, new IntRange(1, 20), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.armorStands$delegate = SettingFactory.booleanSetting$default((SettingContainer)Speed.INSTANCE, "armor-stands", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        Speed.distance$delegate = SettingFactory.floatSetting$default(Speed.INSTANCE, "distance", 1.0f, RangesKt.rangeTo(0.1f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.speed$delegate = SettingFactory.floatSetting$default(Speed.INSTANCE, "speed", 8.0f, RangesKt.rangeTo(0.1f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.groundSpeed$delegate = SettingFactory.floatSetting$default(Speed.INSTANCE, "ground-speed", 8.0f, RangesKt.rangeTo(0.1f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.boostAngle$delegate = SettingFactory.modeSetting$default((SettingContainer)Speed.INSTANCE, "boost-angle", (Enum)SpeedBoostAngleMode.SERVER, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 4, null);
        Speed.predictTicks$delegate = SettingFactory.floatSetting$default(Speed.INSTANCE, "predict-ticks", 3.0f, RangesKt.rangeTo(0.0f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.rollBackTicks$delegate = SettingFactory.intSetting$default(Speed.INSTANCE, "rollback-ticks", 5, new IntRange(0, 30), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 8, null);
        Speed.speedTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4152;
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
                throw new RuntimeException("su/catlean/u4", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            Speed.e[n2] = Speed.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Speed.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xC92;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])m.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    m.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u4", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Speed.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Speed.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Speed.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Speed.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

