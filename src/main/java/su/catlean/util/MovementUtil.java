/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.StopUsingItemEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.KeyMappingAccessor;
import su.catlean.mixins.accessors.LocalPlayerAccessor;
import su.catlean.module.Module;
import su.catlean.module.basic.Avoid;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.rage.AntiWeb;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.Phase;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.PlaceManager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class MovementUtil {
    @NotNull
    public static final MovementUtil INSTANCE;
    private static int H;
    private static float horizontalSpeed;
    private static float speed;
    private static boolean Q;
    private static boolean w;
    private static long i;
    private static long z;
    private static long u;
    private static double fallDistance;
    private static boolean M;
    private static Module[] n;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long[] j;
    private static final Long[] k;
    private static final Map l;

    private MovementUtil() {
    }

    public final int Z() {
        return H;
    }

    public final void I(int n) {
        H = n;
    }

    public final float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public final void setHorizontalSpeed(float f) {
        horizontalSpeed = f;
    }

    public final float getSpeed() {
        return speed;
    }

    public final void setSpeed(float f) {
        speed = f;
    }

    public final boolean J() {
        return Q;
    }

    public final void E(boolean bl) {
        Q = bl;
    }

    public final boolean I() {
        return w;
    }

    public final void V(boolean bl) {
        w = bl;
    }

    public final long L() {
        return i;
    }

    public final void c(long l) {
        i = l;
    }

    public final long A() {
        return z;
    }

    public final void y(long l) {
        z = l;
    }

    public final long q() {
        return u;
    }

    public final void R(long l) {
        u = l;
    }

    public final double getFallDistance() {
        return fallDistance;
    }

    public final void setFallDistance(double d) {
        fallDistance = d;
    }

    public final boolean W() {
        return M;
    }

    public final void r(boolean bl) {
        M = bl;
    }

    public final float getLastYaw() {
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
        return ((LocalPlayerAccessor)((Object)clientPlayerEntity)).getLastYaw();
    }

    public final float getLastPitch() {
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
        return ((LocalPlayerAccessor)((Object)clientPlayerEntity)).getLastPitch();
    }

    @NotNull
    public final Vec3d getServerPos() {
        Vec3d vec3d;
        if (Blink.INSTANCE.getEnabled() && Blink.INSTANCE.getStartPos() != null) {
            Vec3d vec3d2 = Blink.INSTANCE.getStartPos();
            vec3d = vec3d2;
            Intrinsics.checkNotNull(vec3d2);
        } else {
            vec3d = EntityUtil.getPos(Globals.getPlayer());
        }
        return vec3d;
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        block5: {
            if (sendPacket.getPacket() instanceof ClientCommandC2SPacket) {
                if (((ClientCommandC2SPacket)sendPacket.getPacket()).getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING) {
                    Q = true;
                }
                if (((ClientCommandC2SPacket)sendPacket.getPacket()).getMode() == ClientCommandC2SPacket.Mode.STOP_SPRINTING) {
                    Q = false;
                }
            }
            if (sendPacket.getPacket() instanceof ClickSlotC2SPacket) {
                w = true;
            }
            if (sendPacket.getPacket() instanceof CloseHandledScreenC2SPacket) {
                w = false;
            }
            if (!(sendPacket.getPacket() instanceof PlayerInteractItemC2SPacket) || !Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.CHORUS_FRUIT)) break block5;
            u = Globals.now();
        }
    }

    @Flow
    private final void onStopUsingItem(StopUsingItemEvent stopUsingItemEvent) {
        block0: {
            if (!Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.CHORUS_FRUIT)) break block0;
            u = Globals.now();
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block2: {
            if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket && ((PlayerPositionLookS2CPacket)receivePacket.getPacket()).comp_3228().comp_3148().distanceTo(EntityUtil.getPos(Globals.getPlayer())) > 3.0) {
                z = Globals.now();
            }
            if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket && Globals.now() - u > 10000L && Globals.getPlayer().age > 20) {
                i = Globals.now();
            }
            if (!(receivePacket.getPacket() instanceof PlaySoundS2CPacket) || !Intrinsics.areEqual(((PlaySoundS2CPacket)receivePacket.getPacket()).getSound().comp_349(), SoundEvents.ENTITY_ENDER_PEARL_THROW)) break block2;
            u = Globals.now();
        }
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        block3: {
            if (Globals.getMc().player == null) {
                return;
            }
            GofraState.INSTANCE.setModifyCollisions(Avoid.INSTANCE.getEnabled() || AntiWeb.INSTANCE.getEnabled() || Phase.INSTANCE.getEnabled() || SpeedMine.INSTANCE.e());
            double d = Globals.getPlayer().getX() - Globals.getPlayer().lastX;
            double d2 = Globals.getPlayer().getY() - Globals.getPlayer().lastY;
            double d3 = Globals.getPlayer().getZ() - Globals.getPlayer().lastZ;
            horizontalSpeed = (float)Math.hypot(d, d3);
            speed = (float)Math.sqrt(d * d + d2 * d2 + d3 * d3);
            if (Globals.getPlayer().isGliding() && Intrinsics.areEqual(Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST).getItem(), Items.ELYTRA)) {
                int n = H;
                H = n + 1;
            } else {
                H = 0;
            }
            if (Globals.getMc().currentScreen != null) break block3;
            w = false;
        }
    }

    @Flow
    private final void onMove(MoveEvent moveEvent) {
        if (!Globals.getPlayer().isOnGround() && !Globals.getPlayer().isInFluid()) {
            if (moveEvent.getY() < 0.0) {
                fallDistance -= moveEvent.getY();
            }
        } else {
            fallDistance = 0.0;
        }
        M = fallDistance > 0.0;
    }

    public final long getTimeSinceSetback() {
        return System.currentTimeMillis() - i;
    }

    public final boolean isMoving() {
        return Globals.getMc().player != null && Globals.getMc().world != null && (!(Globals.getPlayer().input.getMovementInput().y == 0.0f) || !(Globals.getPlayer().input.getMovementInput().x == 0.0f));
    }

    @NotNull
    public final double[] getMovement(float f, boolean bl, float f2, float f3, float f4) {
        float f5 = f2;
        float f6 = f3;
        if (bl) {
            float f7 = Globals.getMc().options.forwardKey.isPressed() ? 1.0f : (f5 = Globals.getMc().options.backKey.isPressed() ? -1.0f : 0.0f);
            f6 = Globals.getMc().options.leftKey.isPressed() ? 1.0f : (Globals.getMc().options.rightKey.isPressed() ? -1.0f : 0.0f);
        }
        float f8 = f4;
        if (!(f5 == 0.0f)) {
            if (!(f6 == 0.0f)) {
                f8 += f6 > 0.0f ? (f5 > 0.0f ? -45.0f : 45.0f) : (f5 > 0.0f ? 45.0f : -45.0f);
                f6 = 0.0f;
            }
            f5 = f5 > 0.0f ? 1.0f : -1.0f;
        }
        double d = Math.sin(Math.toRadians(f8 + 90.0f));
        double d2 = Math.cos(Math.toRadians(f8 + 90.0f));
        double d3 = (double)(f5 * f) * d2 + (double)(f6 * f) * d;
        double d4 = (double)(f5 * f) * d - (double)(f6 * f) * d2;
        double[] dArray = new double[]{d3, d4};
        return dArray;
    }

    public static double[] getMovement$default(MovementUtil movementUtil, float f, boolean bl, float f2, float f3, float f4, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        if ((n & 4) != 0) {
            f2 = Globals.getPlayer().input.getMovementInput().y;
        }
        if ((n & 8) != 0) {
            f3 = Globals.getPlayer().input.getMovementInput().x;
        }
        if ((n & 0x10) != 0) {
            f4 = Globals.getPlayer().getYaw();
        }
        return movementUtil.getMovement(f, bl, f2, f3, f4);
    }

    public final void setSpeed(int n, short s, @NotNull MoveEvent moveEvent, double d) {
        block15: {
            double d2;
            float f;
            double d3;
            double d4;
            block27: {
                block17: {
                    double d5;
                    block25: {
                        CallSite callSite;
                        block26: {
                            CallSite callSite2;
                            block23: {
                                block24: {
                                    double d6;
                                    block21: {
                                        block22: {
                                            long l;
                                            block18: {
                                                block19: {
                                                    double d7;
                                                    block20: {
                                                        block16: {
                                                            block13: {
                                                                double d8;
                                                                block14: {
                                                                    int n2 = 0;
                                                                    l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ a;
                                                                    CallSite callSite3 = ZkmReferences.zkmBootstrap("\u00c5", (long)-6327645972659807785L, (long)l);
                                                                    Intrinsics.checkNotNullParameter(moveEvent, (String)((Object)MovementUtil.zkmBootstrap("x", (int)23520, (long)(0x142522FACE963D80L ^ l))));
                                                                    callSite = callSite3;
                                                                    ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                                                                    Intrinsics.checkNotNull(clientPlayerEntity);
                                                                    d4 = clientPlayerEntity.input.getMovementInput().y;
                                                                    ClientPlayerEntity clientPlayerEntity2 = Globals.getMc().player;
                                                                    Intrinsics.checkNotNull(clientPlayerEntity2);
                                                                    d3 = clientPlayerEntity2.input.getMovementInput().x;
                                                                    ClientPlayerEntity clientPlayerEntity3 = Globals.getMc().player;
                                                                    Intrinsics.checkNotNull(clientPlayerEntity3);
                                                                    f = clientPlayerEntity3.getYaw();
                                                                    double d9 = d4 - 0.0;
                                                                    d5 = d9 == 0.0 ? 0 : (d9 < 0.0 ? -1 : 1);
                                                                    if (callSite == null) {
                                                                        d5 = d5 == false ? 1.0 : 0.0;
                                                                    }
                                                                    if (s > 0) break block13;
                                                                    if (d5 == false) break block14;
                                                                    double d10 = d3 - 0.0;
                                                                    d5 = d10 == 0.0 ? 0 : (d10 < 0.0 ? -1 : 1);
                                                                    if (callSite == null) {
                                                                        d5 = d5 == false ? 1.0 : 0.0;
                                                                    }
                                                                    if (s >= 0) break block13;
                                                                    if (d5 == false) break block14;
                                                                    moveEvent.setX(0.0);
                                                                    moveEvent.setZ(0.0);
                                                                    if (callSite == null) break block15;
                                                                }
                                                                d5 = (d8 = d4 - 0.0) == 0.0 ? 0 : (d8 < 0.0 ? -1 : 1);
                                                            }
                                                            if (callSite == null) {
                                                                d5 = d5 == false ? 1.0 : 0.0;
                                                            }
                                                            if (n < 0) break block16;
                                                            if (d5 != false) break block17;
                                                            double d11 = d3 - 0.0;
                                                            d5 = d11 == 0.0 ? 0 : (d11 > 0.0 ? 1 : -1);
                                                        }
                                                        callSite2 = callSite;
                                                        if (n <= 0) break block18;
                                                        if (callSite2 != null || n <= 0) break block19;
                                                        if (d5 <= 0) break block20;
                                                        double d12 = d4 - 0.0;
                                                        Object object = d12 == 0.0 ? 0 : (d12 > 0.0 ? 1 : -1);
                                                        if (callSite == null) {
                                                            object = object > 0 ? (Object)MovementUtil.zkmBootstrap2("p", (int)3914, (long)(0x6AACF5B7F7F4DB11L ^ l)) : (Object)MovementUtil.zkmBootstrap2("p", (int)7093, (long)(0x3F754185FA0CFE8L ^ l));
                                                        }
                                                        f += (float)object;
                                                        if (n <= 0) break block21;
                                                        if (callSite == null) break block22;
                                                    }
                                                    d5 = (d7 = d3 - 0.0) == 0.0 ? 0 : (d7 < 0.0 ? -1 : 1);
                                                }
                                                callSite2 = callSite;
                                            }
                                            if (n <= 0) break block23;
                                            if (callSite2 != null) break block24;
                                            if (d5 < 0) {
                                                double d13 = d4 - 0.0;
                                                Object object = d13 == 0.0 ? 0 : (d13 > 0.0 ? 1 : -1);
                                                if (callSite == null) {
                                                    object = object > 0 ? (Object)MovementUtil.zkmBootstrap2("p", (int)16593, (long)(0x4C56B2749B709489L ^ l)) : (Object)MovementUtil.zkmBootstrap2("p", (int)15995, (long)(0x393A8CFF3F3F6A27L ^ l));
                                                }
                                                f += (float)object;
                                            }
                                        }
                                        d3 = 0.0;
                                    }
                                    d5 = (d6 = d4 - 0.0) == 0.0 ? 0 : (d6 > 0.0 ? 1 : -1);
                                }
                                callSite2 = callSite;
                            }
                            if (callSite2 != null) break block25;
                            if (d5 <= 0) break block26;
                            d2 = 1.0;
                            if (s >= 0) break block27;
                            d4 = d2;
                            if (callSite == null) break block17;
                        }
                        d2 = d4;
                        if (callSite != null) break block27;
                        double d14 = d2 - 0.0;
                        d5 = d14 == 0.0 ? 0 : (d14 < 0.0 ? -1 : 1);
                    }
                    if (d5 < 0) {
                        d4 = -1.0;
                    }
                }
                d2 = Math.sin(Math.toRadians(f + 90.0f));
            }
            double d15 = d2;
            double d16 = Math.cos(Math.toRadians(f + 90.0f));
            moveEvent.setX(d4 * d * d16 + d3 * d * d15);
            moveEvent.setZ(d4 * d * d15 - d3 * d * d16);
        }
    }

    /*
     * Unable to fully structure code
     */
    public final float b(int var1_1) {
        block32: {
            block33: {
                block31: {
                    block25: {
                        block28: {
                            block29: {
                                block30: {
                                    block26: {
                                        block27: {
                                            block24: {
                                                block22: {
                                                    block23: {
                                                        block20: {
                                                            block21: {
                                                                var12_2 = 0L;
                                                                var2_3 = ((long)var1_1 << 32 | var12_2 << 32 >>> 32) ^ MovementUtil.a;
                                                                var6_4 = ZkmReferences.zkmBootstrap("\u00c5", (long)-7512965496910289340L, (long)var2_3);
                                                                v0 = Globals.getMc().options.forwardKey.isPressed();
                                                                if (var6_4 != null) ** GOTO lbl10
                                                                if (v0) {
                                                                    v1 = 1.0;
                                                                } else {
                                                                    v0 = Globals.getMc().options.backKey.isPressed();
lbl10:
                                                                    // 2 sources

                                                                    v1 = v0 != false ? -1.0 : 0.0;
                                                                }
                                                                var7_5 = v1;
                                                                v2 = Globals.getMc().options.leftKey.isPressed();
                                                                if (var6_4 != null) ** GOTO lbl18
                                                                if (v2) {
                                                                    v3 = 1.0;
                                                                } else {
                                                                    v2 = Globals.getMc().options.rightKey.isPressed();
lbl18:
                                                                    // 2 sources

                                                                    v3 = v2 != false ? -1.0 : 0.0;
                                                                }
                                                                var9_6 = v3;
                                                                cfr_temp_0 = var9_6 - 0.0;
                                                                v4 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                                                                if (var6_4 != null) break block20;
                                                                if (v4 <= 0) break block21;
                                                                var9_6 = 1.0;
                                                                if (var1_1 >= 0 && var6_4 == null) break block22;
                                                            }
                                                            v5 = var9_6;
                                                            if (var6_4 != null) break block23;
                                                            cfr_temp_1 = v5 - 0.0;
                                                            v4 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 < 0.0 ? -1 : 1);
                                                        }
                                                        if (v4 >= 0) break block22;
                                                        v5 = -1.0;
                                                    }
                                                    var9_6 = v5;
                                                }
                                                v6 = Globals.getMc().player;
                                                Intrinsics.checkNotNull(v6);
                                                var11_7 = v6.getYaw();
                                                cfr_temp_2 = var7_5 - 0.0;
                                                v7 = cfr_temp_2 == 0.0 ? 0 : (cfr_temp_2 < 0.0 ? -1 : 1);
                                                if (var6_4 == null) {
                                                    v7 = v7 == false ? (double)true : (double)false;
                                                }
                                                if (v7 != false) {
                                                    cfr_temp_3 = var9_6 - 0.0;
                                                    v8 = cfr_temp_3 == 0.0 ? 0 : (cfr_temp_3 < 0.0 ? -1 : 1);
                                                    if (var6_4 == null) {
                                                        v8 = v8 == false ? (double)true : (double)false;
                                                    }
                                                    if (v8 != false) {
                                                        return var11_7;
                                                    }
                                                }
                                                v9 = (cfr_temp_4 = var7_5 - 0.0) == 0.0 ? 0 : (cfr_temp_4 < 0.0 ? -1 : 1);
                                                if (var6_4 == null) {
                                                    v9 = v9 == false ? (double)true : (double)false;
                                                }
                                                if (var1_1 < 0) break block24;
                                                if (v9 != false) break block25;
                                                cfr_temp_5 = var9_6 - 0.0;
                                                v9 = cfr_temp_5 == 0.0 ? 0 : (cfr_temp_5 > 0.0 ? 1 : -1);
                                            }
                                            if (var6_4 != null) break block26;
                                            if (v9 <= 0) break block27;
                                            var11_7 += var7_5 > 0.0 ? -45.0f : -135.0f;
                                            if (var6_4 == null) break block25;
                                        }
                                        v9 = (cfr_temp_6 = var9_6 - 0.0) == 0.0 ? 0 : (cfr_temp_6 < 0.0 ? -1 : 1);
                                    }
                                    v10 = var6_4;
                                    if (var1_1 <= 0) break block28;
                                    if (v10 != null) break block29;
                                    if (v9 >= 0) break block30;
                                    var11_7 += var7_5 > 0.0 ? 45.0f : 135.0f;
                                    if (var6_4 == null) break block25;
                                }
                                v9 = (cfr_temp_7 = var7_5 - 0.0) == 0.0 ? 0 : (cfr_temp_7 < 0.0 ? -1 : 1);
                            }
                            v10 = var6_4;
                        }
                        if (v10 != null) break block31;
                        if (v9 < 0) {
                            var11_7 += 180.0f;
                        }
                    }
                    v9 = (cfr_temp_8 = var7_5 - 0.0) == 0.0 ? 0 : (cfr_temp_8 < 0.0 ? -1 : 1);
                }
                if (var6_4 == null) {
                    v9 = v9 == false ? (double)true : (double)false;
                }
                if (v9 == false) break block32;
                if (!(var9_6 > 0.0)) break block33;
                var11_7 -= 90.0f;
                if (var6_4 == null) break block32;
            }
            var11_7 += 90.0f;
        }
        return var11_7;
    }

    @NotNull
    public final double[] getMotionForSpeed(double d) {
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        Intrinsics.checkNotNull(clientPlayerEntity);
        float f = clientPlayerEntity.getYaw();
        double d2 = d * Math.cos(Math.toRadians(f + 90.0f));
        double d3 = d * Math.sin(Math.toRadians(f + 90.0f));
        double[] dArray = new double[]{d2, d3};
        return dArray;
    }

    @Nullable
    public final Entity raycastEntity(float f, float f2, float f3, boolean bl, boolean bl2) {
        HitResult hitResult = bl ? null : this.raycastFromEyes(f3, f, f2);
        Vec3d vec3d = EntityUtil.getPos(Globals.getPlayer()).add(0.0, Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        double d = (float)Math.pow(f3, 2.0);
        if (hitResult != null) {
            d = hitResult.getPos().squaredDistanceTo(vec3d2);
        }
        Vec3d vec3d3 = this.getLookVector(f2, f);
        Vec3d vec3d4 = vec3d2.add(vec3d3.x * (double)f3, vec3d3.y * (double)f3, vec3d3.z * (double)f3);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        Box box = Globals.getPlayer().getBoundingBox().stretch(vec3d3.multiply(f3)).expand(1.0, 1.0, 1.0);
        Intrinsics.checkNotNullExpressionValue(box, "inflate(...)");
        Box box2 = box;
        EntityHitResult entityHitResult = ProjectileUtil.raycast(Globals.getPlayer(), vec3d2, vec3d5, box2, arg_0 -> MovementUtil.isValidTarget(bl2, arg_0), d);
        if (entityHitResult != null && (vec3d2.squaredDistanceTo(entityHitResult.getPos()) < d || hitResult == null)) {
            return entityHitResult.getEntity();
        }
        return null;
    }

    public static Entity raycastEntity$default(MovementUtil movementUtil, float f, float f2, float f3, boolean bl, boolean bl2, Object object) {
        bl2 = true;
        return movementUtil.raycastEntity(f, f2, f3, bl, bl2);
    }

    @NotNull
    public final HitResult raycastFrom(float f, float f2, double d, double d2, double d3) {
        float f3 = (float)d3;
        float f4 = (float)d2;
        HitResult hitResult = this.raycastDirection(5.0f, f, f2, (float)d, f4, f3);
        Vec3d vec3d = new Vec3d(d, d2, d3).add(0.0, Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        double d4 = 25.0;
        if (hitResult.getType() != HitResult.Type.MISS) {
            d4 = hitResult.getPos().squaredDistanceTo(vec3d2);
        }
        Vec3d vec3d3 = this.getLookVector(f2, f);
        Vec3d vec3d4 = vec3d2.add(vec3d3.x * 5.0, vec3d3.y * 5.0, vec3d3.z * 5.0);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        Box box = new Box(d - 0.3, d2, d3 - 0.3, d + 0.3, d2 + 1.8, d3 + 0.3).stretch(vec3d3.multiply(5.0)).expand(1.0, 1.0, 1.0);
        Intrinsics.checkNotNullExpressionValue(box, "inflate(...)");
        Box box2 = box;
        EntityHitResult entityHitResult = ProjectileUtil.raycast(Globals.getPlayer(), vec3d2, vec3d5, box2, MovementUtil::isAttackable, d4);
        if (entityHitResult != null) {
            Entity entity = entityHitResult.getEntity();
            Intrinsics.checkNotNullExpressionValue(entity, "getEntity(...)");
            Entity entity2 = entity;
            Vec3d vec3d6 = entityHitResult.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d6, "getLocation(...)");
            Vec3d vec3d7 = vec3d6;
            double d5 = vec3d2.squaredDistanceTo(vec3d7);
            if ((d5 < d4 || hitResult.getType() == HitResult.Type.MISS) && entity2 instanceof LivingEntity) {
                return entityHitResult;
            }
        }
        return hitResult;
    }

    @NotNull
    public final Vec3d getLookVector(float f, float f2) {
        return new Vec3d((float)Math.sin(-f2 * ((float)Math.PI / 180)) * (float)Math.cos(f * ((float)Math.PI / 180)), -((double)((float)Math.sin(f * ((float)Math.PI / 180)))), (float)Math.cos(-f2 * ((float)Math.PI / 180)) * (float)Math.cos(f * ((float)Math.PI / 180)));
    }

    @NotNull
    public final HitResult raycastFromEyes(float f, float f2, float f3) {
        Vec3d vec3d = Globals.getPlayer().getCameraPosVec(1.0f);
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        Vec3d vec3d2 = vec3d;
        Vec3d vec3d3 = this.getLookVector(f3, f2);
        Vec3d vec3d4 = vec3d2.add(vec3d3.x * (double)f, vec3d3.y * (double)f, vec3d3.z * (double)f);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        BlockHitResult blockHitResult = Globals.getWorld().raycast(new RaycastContext(vec3d2, vec3d5, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, Globals.getPlayer()));
        Intrinsics.checkNotNullExpressionValue(blockHitResult, "clip(...)");
        return blockHitResult;
    }

    private final HitResult raycastDirection(float f, float f2, float f3, float f4, float f5, float f6) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 56 | (long)n2 << 32 >>> 8 | (long)n3 << 40 >>> 40) ^ a;
        Vec3d vec3d = new Vec3d(f4, f5, f6);
        Vec3d vec3d2 = this.getLookVector(f3, f2);
        Vec3d vec3d3 = vec3d.add(vec3d2.x * (double)f, vec3d2.y * (double)f, vec3d2.z * (double)f);
        Intrinsics.checkNotNullExpressionValue(vec3d3, (String)((Object)MovementUtil.zkmBootstrap("x", (int)9058, (long)(0x17F635646C48347BL ^ l))));
        Vec3d vec3d4 = vec3d3;
        BlockHitResult blockHitResult = Globals.getWorld().raycast(new RaycastContext(vec3d, vec3d4, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, Globals.getPlayer()));
        Intrinsics.checkNotNullExpressionValue(blockHitResult, (String)((Object)MovementUtil.zkmBootstrap("x", (int)4200, (long)(0x4255C73751E18762L ^ l))));
        return blockHitResult;
    }

    public final double a() {
        int n;
        double d = 0.2873;
        if (Globals.getMc().player == null) {
            return 0.0;
        }
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.SPEED)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.SPEED);
            Intrinsics.checkNotNull(statusEffectInstance);
            n = statusEffectInstance.getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.JUMP_BOOST)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.JUMP_BOOST);
            Intrinsics.checkNotNull(statusEffectInstance);
            n = statusEffectInstance.getAmplifier();
            d /= 1.0 + 0.2 * (double)(n + 1);
        }
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.SLOWNESS)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.SLOWNESS);
            Intrinsics.checkNotNull(statusEffectInstance);
            n = statusEffectInstance.getAmplifier();
            d /= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public final double getJumpVelocity() {
        double d = 0.3999999463558197;
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.JUMP_BOOST)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.JUMP_BOOST);
            Intrinsics.checkNotNull(statusEffectInstance);
            double d2 = statusEffectInstance.getAmplifier();
            d += (d2 + 1.0) * 0.1;
        }
        return d;
    }

    public final float getDurabilityFraction(@NotNull ItemStack itemStack) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(itemStack, (String)((Object)MovementUtil.zkmBootstrap("x", (int)25189, (long)(0x5B6DC1A926F2D51DL ^ l2))));
        return (float)(itemStack.getMaxDamage() - itemStack.getDamage()) / (float)itemStack.getMaxDamage() * 100.0f;
    }

    public final float distanceTo(@NotNull Vec3d vec3d) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)MovementUtil.zkmBootstrap("x", (int)25641, (long)(0x303D15D0552601D6L ^ l))));
        return (float)Math.sqrt(this.squaredDistanceTo(vec3d));
    }

    public final float squaredDistanceTo(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "targetPos");
        if (Globals.getMc().player == null) {
            return 0.0f;
        }
        Entity entity = Globals.getPlayer();
        Vec3d vec3d2 = PlaceManager.INSTANCE.getEyePos(entity);
        double d = vec3d.x - vec3d2.x;
        double d2 = vec3d.y - vec3d2.y;
        double d3 = vec3d.z - vec3d2.z;
        return (float)(d * d + d2 * d2 + d3 * d3);
    }

    public final boolean getYawTo(@NotNull Vec3d vec3d, int n) {
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        double d = vec3d.getX() - Globals.getMc().gameRenderer.getCamera().getCameraPos().x;
        double d2 = vec3d.getZ() - Globals.getMc().gameRenderer.getCamera().getCameraPos().z;
        float f = MathHelper.wrapDegrees((float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(d2, d)) - 90.0) - MathHelper.wrapDegrees(Globals.getMc().gameRenderer.getCamera().getYaw()));
        return Math.abs((double)f) <= (double)n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean k(long l) {
        CallSite callSite;
        boolean bl;
        block7: {
            CallSite callSite2;
            block5: {
                block6: {
                    l = a ^ l;
                    callSite2 = ZkmReferences.zkmBootstrap("\u00c5", (long)787743539272678166L, (long)l);
                    ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                    if (callSite2 == null) {
                        if (clientPlayerEntity == null) {
                            return false;
                        }
                        clientPlayerEntity = Globals.getPlayer();
                    }
                    bl = clientPlayerEntity.getMainHandStack().getComponents().contains(DataComponentTypes.FOOD);
                    if (callSite2 != null) break block5;
                    if (bl) break block6;
                    bl = Globals.getPlayer().getOffHandStack().getComponents().contains(DataComponentTypes.FOOD);
                    callSite = callSite2;
                    if (l <= 0L) break block7;
                    if (callSite != null) break block5;
                    if (!bl) return false;
                }
                bl = Globals.getPlayer().isUsingItem();
            }
            callSite = callSite2;
        }
        if (callSite != null) return bl;
        if (!bl) return false;
        return true;
    }

    @Nullable
    public final Vec3d getEyeVector(float f, float f2, float f3) {
        EntityHitResult entityHitResult;
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        Intrinsics.checkNotNull(clientPlayerEntity);
        Vec3d vec3d = EntityUtil.getPos(clientPlayerEntity).add(0.0, Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        float f4 = (float)Math.pow(f3, 2.0);
        Vec3d vec3d3 = this.getLookVector(f2, f);
        Vec3d vec3d4 = vec3d2.add(vec3d3.x * (double)f3, vec3d3.y * (double)f3, vec3d3.z * (double)f3);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        Box box = Globals.getPlayer().getBoundingBox().stretch(vec3d3.multiply(f3)).expand(1.0, 1.0, 1.0);
        Intrinsics.checkNotNullExpressionValue(box, "inflate(...)");
        Box box2 = box;
        EntityHitResult entityHitResult2 = entityHitResult = ProjectileUtil.raycast(Globals.getPlayer(), vec3d2, vec3d5, box2, MovementUtil::isNotSpectator, f4);
        return entityHitResult2 != null ? entityHitResult2.getPos() : null;
    }

    public final float squaredHorizontalDistance(double d, double d2) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-287141068230357509L, (long)l);
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        if (callSite == null) {
            if (clientPlayerEntity == null) {
                return 0.0f;
            }
            clientPlayerEntity = Globals.getPlayer();
        }
        double d3 = clientPlayerEntity.getX() - d;
        double d4 = Globals.getPlayer().getZ() - d2;
        return (float)(d3 * d3 + d4 * d4);
    }

    public final float horizontalDistance(double d, double d2) {
        return (float)Math.sqrt(this.squaredHorizontalDistance(d, d2));
    }

    public final void resetSprint() {
        block3: {
            block2: {
                long l = 0L;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8468034401485358204L, (long)(l = a ^ l));
                if (callSite != null) break block2;
                if (!Q) break block3;
                Globals.getNetworkHandler().sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                Globals.getPlayer().setSprinting(false);
            }
            Globals.getPlayer().lastSprinting = false;
        }
    }

    @NotNull
    public final HitResult canReach(double d, double d2, float f, float f2) {
        double d3 = Math.max(d, d2);
        double d4 = MathHelper.square(d3);
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        Vec3d vec3d2 = vec3d;
        HitResult hitResult = this.getLookOffset(d, f, f2);
        double d5 = hitResult.getPos().squaredDistanceTo(vec3d2);
        if (hitResult.getType() != HitResult.Type.MISS) {
            d4 = d5;
            d3 = Math.sqrt(d5);
        }
        Vec3d vec3d3 = this.getLookVector(f2, f);
        Vec3d vec3d4 = vec3d2.add(vec3d3.x * d3, vec3d3.y * d3, vec3d3.z * d3);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        Box box = Globals.getPlayer().getBoundingBox().stretch(vec3d3.multiply(d3)).expand(1.0, 1.0, 1.0);
        Intrinsics.checkNotNullExpressionValue(box, "inflate(...)");
        Box box2 = box;
        EntityHitResult entityHitResult = ProjectileUtil.raycast(Globals.getPlayer(), vec3d2, vec3d5, box2, EntityPredicates.CAN_HIT, d4);
        return entityHitResult != null && entityHitResult.getPos().squaredDistanceTo(vec3d2) < d5 ? this.isWithinRange(entityHitResult, vec3d2, d2) : this.isWithinRange(hitResult, vec3d2, d);
    }

    private final HitResult isWithinRange(HitResult hitResult, Vec3d vec3d, double d) {
        block3: {
            Vec3d vec3d2;
            Vec3d vec3d3;
            long l;
            block2: {
                long l2 = 0L;
                int n = 0;
                l = (l2 << 16 | (long)n << 48 >>> 48) ^ a;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7043508201507295303L, (long)l);
                Vec3d vec3d4 = hitResult.getPos();
                Intrinsics.checkNotNullExpressionValue(vec3d4, (String)((Object)MovementUtil.zkmBootstrap("x", (int)8452, (long)(0x753A39C6635A8EE4L ^ l))));
                Vec3d vec3d5 = vec3d4;
                CallSite callSite2 = callSite;
                vec3d3 = vec3d5;
                vec3d2 = vec3d;
                if (callSite2 != null) break block2;
                if (vec3d3.isInRange(vec3d2, d)) break block3;
                Vec3d vec3d6 = hitResult.getPos();
                vec3d2 = vec3d6;
                vec3d3 = vec3d6;
            }
            Intrinsics.checkNotNullExpressionValue(vec3d2, (String)((Object)MovementUtil.zkmBootstrap("x", (int)8452, (long)(0x753A39C6635A8EE4L ^ l))));
            Vec3d vec3d7 = vec3d3;
            Direction direction = Direction.getFacing(vec3d7.x - vec3d.x, vec3d7.y - vec3d.y, vec3d7.z - vec3d.z);
            Intrinsics.checkNotNullExpressionValue(direction, (String)((Object)MovementUtil.zkmBootstrap("x", (int)8540, (long)(0x4310B992A7EC0EB5L ^ l))));
            Direction direction2 = direction;
            BlockHitResult blockHitResult = BlockHitResult.createMissed(vec3d7, direction2, BlockPos.ofFloored(vec3d7));
            Intrinsics.checkNotNullExpressionValue(blockHitResult, (String)((Object)MovementUtil.zkmBootstrap("x", (int)31042, (long)(0x4377BE9182DD56B5L ^ l))));
            return blockHitResult;
        }
        return hitResult;
    }

    @NotNull
    public final HitResult getLookOffset(double d, float f, float f2) {
        Vec3d vec3d = this.getLookVector(f2, f).multiply(d);
        Intrinsics.checkNotNullExpressionValue(vec3d, "scale(...)");
        Vec3d vec3d2 = vec3d;
        BlockHitResult blockHitResult = Globals.getWorld().raycast(new RaycastContext(Globals.getPlayer().getEyePos(), Globals.getPlayer().getEyePos().add(vec3d2), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, Globals.getPlayer()));
        Intrinsics.checkNotNullExpressionValue(blockHitResult, "clip(...)");
        return blockHitResult;
    }

    public final void releaseMovementKeys() {
        Globals.getMc().options.forwardKey.setPressed(false);
        Globals.getMc().options.backKey.setPressed(false);
        Globals.getMc().options.leftKey.setPressed(false);
        Globals.getMc().options.rightKey.setPressed(false);
        Globals.getMc().options.jumpKey.setPressed(false);
        Globals.getMc().options.sneakKey.setPressed(false);
    }

    public final void releaseMovementKeys(long l) {
        int n = (int)(((l = a ^ l) ^ 0x437573CEEDE1L) << 48 >>> 48);
        KeyBinding keyBinding = Globals.getMc().options.forwardKey;
        KeyBinding keyBinding2 = Globals.getMc().options.forwardKey;
        Intrinsics.checkNotNull(keyBinding2, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
        int n2 = ((KeyMappingAccessor)((Object)keyBinding2)).getKey().getCode();
        char c = (char)n;
        keyBinding.setPressed(KeyboardUtil.isKeyPressed(c, n2));
        KeyBinding keyBinding3 = Globals.getMc().options.backKey;
        KeyBinding keyBinding4 = Globals.getMc().options.backKey;
        Intrinsics.checkNotNull(keyBinding4, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
        int n3 = ((KeyMappingAccessor)((Object)keyBinding4)).getKey().getCode();
        char c2 = (char)n;
        keyBinding3.setPressed(KeyboardUtil.isKeyPressed(c2, n3));
        KeyBinding keyBinding5 = Globals.getMc().options.leftKey;
        KeyBinding keyBinding6 = Globals.getMc().options.leftKey;
        Intrinsics.checkNotNull(keyBinding6, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
        int n4 = ((KeyMappingAccessor)((Object)keyBinding6)).getKey().getCode();
        char c3 = (char)n;
        keyBinding5.setPressed(KeyboardUtil.isKeyPressed(c3, n4));
        KeyBinding keyBinding7 = Globals.getMc().options.rightKey;
        KeyBinding keyBinding8 = Globals.getMc().options.rightKey;
        Intrinsics.checkNotNull(keyBinding8, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
        int n5 = ((KeyMappingAccessor)((Object)keyBinding8)).getKey().getCode();
        char c4 = (char)n;
        keyBinding7.setPressed(KeyboardUtil.isKeyPressed(c4, n5));
        KeyBinding keyBinding9 = Globals.getMc().options.jumpKey;
        KeyBinding keyBinding10 = Globals.getMc().options.jumpKey;
        Intrinsics.checkNotNull(keyBinding10, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
        int n6 = ((KeyMappingAccessor)((Object)keyBinding10)).getKey().getCode();
        char c5 = (char)n;
        keyBinding9.setPressed(KeyboardUtil.isKeyPressed(c5, n6));
        KeyBinding keyBinding11 = Globals.getMc().options.sneakKey;
        KeyBinding keyBinding12 = Globals.getMc().options.sneakKey;
        Intrinsics.checkNotNull(keyBinding12, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
        int n7 = ((KeyMappingAccessor)((Object)keyBinding12)).getKey().getCode();
        char c6 = (char)n;
        keyBinding11.setPressed(KeyboardUtil.isKeyPressed(c6, n7));
    }

    private static final boolean isValidTarget(boolean bl, Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "it");
        return !entity.isSpectator() && entity.canHit() && (entity instanceof LivingEntity || !bl);
    }

    private static final boolean isAttackable(Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "it");
        return !entity.isSpectator() && entity.canHit();
    }

    private static final boolean isNotSpectator(Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "it");
        return !entity.isSpectator();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                MovementUtil.a = ZkmKeys.a(-265864172261133626L, 338618076458484166L, MethodHandles.lookup().lookupClass()).a(220459146735406L);
                                var31 = MovementUtil.a ^ 26301898346218L;
                                MovementUtil.d = new HashMap<K, V>(13);
                                MovementUtil.zkmSetFlag(null);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[25];
                                var27_4 = 0;
                                var26_5 = "\u00c2\u00e5\u00d8<\u0004\u00bc\u0092\u00cd\u009c\u000eK\u00d1\u00c3\u0094\u0018\u00dd \u0087\u0017nLS\u0084a\"\u00a4\u008depBZ\u00940\u00fd\u00f1\u00c9\u00e2\u00bc\u00c5\u001a\u001dKc\u0092\u0017,Y\u00f6e \u000f\u0017\u00fe\u00fcO\u0001\u000e\u00fe]\u009d\u00bc\u0001\u00ed\u00d1\u00dfxC\u00c2\u00e1\u00e1\u00b9c\u00daI\u00d0\u00d6\u0016\u001a7l?\u00a9\u0090\u00a8\u008f\u00bdDs\u001e3\u00a2\u00da#\u00c8\u00a5t\rq\u0081\u0081\\\u009b^\u00b1\u0003\u008f3\u00b2u4\u00ec\u00d1\u00fe\r\u0082\u00fe(\u00c7Ps%\u00a4k\u0002\u00e3\u00b4;\u0011\u0095\u009eZ\u00e6\u00e1:\u00e78\u00c8,\u00c7\u0090c\u00a7\f/\u001c\u00c8\u009dP\n!i\u00b5\u0095\t\u009c\u009b\u009f\u0013{\u00eej=x\u001dQ\u00f8\u00eb\u00d6\u00bbW`5z\u00db\u00058\u00fe\u00a0c\u00bc\u0015\u000f\u00e8Q\u0093\u00be\u0002\u00ee\t\u00d2\u00db\u00e1\u00a7\u0099j\u00dc\u00be0\u0084\u0004\u0088\rC)cY\u0097~\u00d6\u008da\u00cd\u00ac6\rbvs\u00d7\u008fA\u00a1Bp\u00bc\u0099\u00ac \u0090\u0002\u00e3\u00a4\u00c7\u00f7\u00eb\u0000\u0003\u00cf;\u001f\u00f0\u00ae\u00c1\u00ee\u00b2\u009a\u00fb\u00e8\u00ff\u008b\u00fa>\u00d1\u00f5Y\u0090\u00fa\u0099\u00ce\u00c1(G\u0018\u00ac_\u00a7\u009d\u0019\u009c\u00c2\u00a8\u00ed,\u00a3O[2\u0095\u00e1\u00e2\u008c\u00dd8^\u0000o\u00b6\u00d8\u00df\u001f\u00a1\u00dc\u00ba\u00d6\u00f0\u0085*\u00b4)\u00a0\u00d3\u0088\u0010\u001d\u0091\u00fb\u00057\u000ep\u00dcU\u008d\u00c8\u0016\u009fZ\u009a\u00db\u0018i\u00cbX\u0016\u007f\f\u0015\u00bdX=>\u00b99\\ \u00de\u0015\u00f3\u00ed\u00dd\u0016\u00ab\u00e8\u00b4N9\\\u00de\u00c1R\u0094_#@\u00f8\u0002z\u000be\u001e\u00ae\u00b8\u00d59\u00c1D\u00b8\u00f7ar\u0001\u0088\u000e\u0010\r\u00f8\u00be\u00a5\u0083\u00d7s[}\u000e\u001a\u00b79\u00fd\u0080\u00ba\u00cd!\u0004\u00e3(\u00db+R\u00151!\u008a\u0090\u00d5\u00bc\u0082`E\u0011\u00e3\u00f7I\u00cb\t\u00be-\u0019y\u008a\u00cb\u0086\u0091;\u009eUP\u00f1,\u00b4\u00b5;(\u0094\u00de\u00f3\u00d2]\u00f7(g5\u00f1\u00dfL%#\u0090$\u00eb\u0010\u0090\u00e7E\u00b2\u00b5m\u00a2\u00da\u0006r\u00e4\u0090b\u00f4\u00d9A\u00ef\u00dc(\u001e\u00b2\u00cf\u00c10f\\[\u0002`\u0088\u00d0\u00b3v\u00cc\u0014\u0003&#}I\u0086\"\u00bf\u000f\u0087\u00f2n\u0083\u009e\u00b0jB\u008a\r\u0090\u0080\u00d6\u00d60!+@\u00d6F#\u00fb\u00b2_r\b\u0007\u00d2\u0001x\u0098,\u00cf\u00d4X\u00e9u=B\u00c9\t\u00bf\u009b\u00b1v7\u00a8\u00e9\u009f^\u0013\u00ce\u00a3L\u00c7\u008d\u0019\u0015\u00c4I-%\u0006\u00a1\u00e5\u00bf?\u0081^\u001e\u00dd\u001a\u00d7\u00c5\u0094\u00a3N\u00dbe\u00b31#Y\u00a5\u00ed\u00d0\t\u00b6\u00a0\u008c\u0093\u008a\u00cd\u009b\u008d\u00e7*\u0096\u0084\u0087\u00a4\u009d*\u00d4\u00e6!C\u0082\u00e5\u00cf\u00ff\u00cdP\u0090g\u00bb\u00d2\u0005WVQ\u0091 \u00f3%\u00b4\u00f6\u00c8$1\u0000d\u00ad\u00fb\u00d7\u0001M9\u00cb\u001b\u00f3\u00a2)\u0003O\u0096\u0016\u00b3\u00ba\u00bf\u0099\u00a3\u00ecM\u0004\u0010\u00cf\u00b1\u00b8\u00a7+\u0016S\u00de25%\u00e0\f5.\u00ce I(\u00a1k\u00c2\u00df\u00b2\u0087\u00a2\u00e6\u00ebe\u0091[g\u009cH\u00e5H\u0089k}\u009a\u00045\u001b\u00812\u00d4O\u00eb\u00cc8\u00f0i\u00cd\u00b1\u001f\u00ed\u00cb\u001f\n\u00a2Z\u0013?Q\u00a2+\u00b8\u0085\u00a5\u00fa\u00b6/\u0092W&\u00b9W\u00e8\u00bc\u0099\u00ac>>\u0014\u001bxA\u00ca\u00a7|W}to\u00b4\u009fp \u00e5\u00e1IY\u00a6\u0093E<(9X\u0083\u00a7h\u0006\u00c5\u00bf\u00df\u00c2n*\u0094/\u00f6\u0000\u009d\u00decH\u0007\u0006\u00ad\u0012\u00a5\f\u0013\u00b4\u00ec\r\u00ca\u0018\u00a6%\u00ac|\u00d8+\u00f9\u00cd\u0010,\u0090\u00e7\u00e4L\u00be\u001a\\\u00b60\u00b4\u00dc\u00deY\u00c8\u0081\u0010\u00ccb\u00c5\u0091\u000ed8'\u00a5pwb\u00e5\"\u00e1\u00f6\u0018\u0019\u00ed\u0017\u00d1\u00a2`\r{\u0086\u00c5\u0017\u0097Fe\u0018\u00dd\u00d8\u00fd\u00fa\u00a5g\u00fb2\u00b5 \u00adWSW\u0097_\u00dc\u00eaO\u0015GE\u00e4)'\u00eb\u009b\u00be\u00ec\u00b1X\u0089\u00c6\u00a7\u00d9\u00a9Z\u0004\u0095\u00ac\u00af_ \u00b9$\u0018{\u00fd\u008b\u0000\u00b8\u0012%f-U\u0015\u00e4\u00ca\u0017\u00c7\u0000 QPb\r\u00b2\u00e77\u0096\u0012X\u00c8\u00fc\u0088\u00f3\u00dc1t\u00ce\u00a9\u00d5B\u00f4\u00ea\u0096)\u009b\u0014\u00f6\u0082\u00d5\u001f\u00a5D\u008a\u0089K(\u00b8`c\u001e\u00f3\u00d9\u0004\u007f\u00fa?Q\u00a6<J\u001e\u000e\u00b8\u00b7\u0082C\u00104\u00c5U\u0088\u0012f;\u0093\u00ff\u00d4\u007f\u00a4}\u00d9#\u00bc\u00dc9C\u00db\u0006\u00e1\u000f\nA\u0019\u00ce\u0004)\u0088\u00e9\u0085\\m\u00b1\u00be\u001d\u00c9f\u0092:\u0010\u00a3\u00c7\u00da\u0080l}\u00fa\u00a3\u00fd \u00cdq-9\u00e4\u009c\u007fA\u00da\u00fb\u009e\u00f6l\u00e2\u00ae\u00a9}\u00b0\u00f0t\u001b\u00a3\u0004\u00b4\u0005Q\u00d2)\u0090\u00a2:o$\u00ba\u001a`v\u000bB(\u009f\u00a7\u0015\u0095\u00cfNVq\u0099u\u0012\u001d\u000bpHg\u008adVw\u00d1\u001c\u00c8\u0084\u00ff_\u0088\u00f2\u00d3vwOV\u008c\u00855\u0003\u001e\u00d5J\u0010\u0087\u00e5\u00d3:N\u0015\u00e7\u001e\u00a7\u000b\u00d13\u00d0\u00e5%J ?!5\u00b4A\u00d6;!g\u00deI\r\u00f9\u000f\u00ee\u00be\u0007\u00e3\u00ca\u0017B\u0089KH\u0090\u00a4\u00cb\u00cd:\u00aaI\u0086";
                                var28_6 = 1150;
                                var25_7 = 16;
                                var24_8 = -1;
lbl21:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl26:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = MovementUtil.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "c\u0081\u008a\u00171\u00d9Y\u00d6\u009b\u000b\u00c20\u00cf\u001c@D\u0098\u0010\u00eb\u00ec/\u00cd\u00b5\u0086cf\u00c3\u0092\u008f\u00c36\u008a \u00127\u00cbU\u0013\u00a0\u00f76kHV\u00fb\u0015\u00ed\u00f0\u00d5Y\u0002\u00bf&e\u00b4\u00f4\u0092m\u0089\u0086_\u0000\u009eK?";
                                    var28_6 = 65;
                                    var25_7 = 32;
                                    var24_8 = -1;
lbl35:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl40:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = MovementUtil.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        MovementUtil.b = var29_3;
                        MovementUtil.c = new String[25];
                        MovementUtil.g = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[8];
                        var14_13 = 0;
                        var15_14 = "\u009c\u008bM/\u00f9R\u00acF\u00ff\u0091\u0015Vd.\u0017u\u00cd\u00e3(\u0093\u00bf\u0081\u0089\u00f1\u00ff\u00b6U\u00d2\u0099Y#\u0082\u0013hU^\u00c7\u00c8n\u00be\u009b\u00b4NMru\u0017\u00a1";
                        var16_15 = 48;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl79:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = ">\u00b949Dn\u0082\u00f9\u00116\u00d3\u00f1\u00cf\u0085/\u00c1";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl92:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                MovementUtil.e = var17_12;
                MovementUtil.f = new Integer[8];
                MovementUtil.l = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var31 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[3];
                var3_23 = 0;
                var4_24 = "\u00cd\u0001\u00b7c\u00c0\u00b4\u00ee\u0007\u00a3[a\u00eb\u001bG\u009c\u008c\u00e9\u0019q\u00ad\u00dd\u00b3\u0013\u0094";
                var5_25 = 24;
                var2_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl127:
                // 1 sources

                while (true) {
                    var6_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var2_26 < var5_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
            v18 = var3_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        MovementUtil.j = var6_22;
        MovementUtil.k = new Long[3];
        MovementUtil.INSTANCE = new MovementUtil();
        MovementUtil.i = -1L;
        MovementUtil.z = -1L;
        MovementUtil.u = -1L;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        n = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return n;
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

    private static String getBaseSpeed(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F13;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dm", exception);
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
            MovementUtil.c[n2] = MovementUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MovementUtil.getBaseSpeed(n, l);
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
            throw new RuntimeException("su/catlean/dm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7D3C;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dm", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MovementUtil.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MovementUtil.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/dm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x42C1;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])MovementUtil.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    MovementUtil.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dm", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            MovementUtil.k[n2] = l4;
        }
        return k[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = MovementUtil.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap3(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_2().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/dm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MovementUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MovementUtil.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findStatic(MovementUtil.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

