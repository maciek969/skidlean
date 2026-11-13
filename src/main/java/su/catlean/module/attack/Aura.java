/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PiercingWeaponComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profilers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.client.PostTickEvent;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.SprintEvent;
import su.catlean.api.event.events.player.UpdateCrosshairTarget;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura$WhenMappings;
import su.catlean.module.attack.AuraEspMode;
import su.catlean.module.attack.AuraRandomizeDelayMode;
import su.catlean.module.attack.AuraRotationMode;
import su.catlean.module.attack.AuraSortMode;
import su.catlean.module.attack.AuraSprintMode;
import su.catlean.module.attack.AuraSwitchHandler;
import su.catlean.module.attack.AuraSwitchMode;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.attack.AuraWhileShieldingMode;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.basic.Sprint;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.BlinkAttackActionMode;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.module.rage.MaceElytraTarget;
import su.catlean.render.MainFramebuffer;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingOverrides;
import su.catlean.setting.SettingParent;
import su.catlean.sound.Sounds;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.SlotResult;
import su.catlean.util.combat.Backtrack;
import su.catlean.util.combat.TargetManager;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.AdvancedRotationHandler;
import su.catlean.util.rotation.AimMode;
import su.catlean.util.rotation.AimSettings;
import su.catlean.util.rotation.AuraRotationHandler;
import su.catlean.util.rotation.DefaultRotationHandler;
import su.catlean.util.rotation.ManagedRotationHandler;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationHandler;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Aura
extends Module {
    @NotNull
    public static final Aura INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting attackRange$delegate;
    @NotNull
    private static final BooleanSetting ignoreWalls$delegate;
    @NotNull
    private static final IntSetting fov$delegate;
    @NotNull
    private static final ModeSetting rotationMode$delegate;
    @NotNull
    private static final BooleanSetting priorityManual$delegate;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final BooleanSetting onlyWeapon$delegate;
    @NotNull
    private static final BooleanSetting oldDelay$delegate;
    @NotNull
    private static final IntSetting minCps;
    @NotNull
    private static final IntSetting maxCps;
    @NotNull
    private static final BooleanSetting shieldBreaker;
    @NotNull
    private static final BooleanSetting splashWhileOnGround;
    @NotNull
    private static final BooleanSetting backTrack$delegate;
    @NotNull
    private static final IntSetting moveDelay$delegate;
    @NotNull
    private static final IntSetting attackChance$delegate;
    @NotNull
    private static final BooleanSetting pauseWhileEating$delegate;
    @NotNull
    private static final BooleanSetting pauseInInventory$delegate;
    @NotNull
    private static final ModeSetting whileShielding;
    @NotNull
    private static final BooleanSetting deathDisable$delegate;
    @NotNull
    private static final BooleanSetting overrideCrosshair$delegate;
    @NotNull
    private static final BooleanSetting rotateOnDisable$delegate;
    @NotNull
    private static final BooleanSetting onHitSwap$delegate;
    @NotNull
    private static final ModeSetting randomizeDelay;
    @NotNull
    private static final ModeSetting sprintMode$delegate;
    @NotNull
    private static final ModeSetting esp$delegate;
    @NotNull
    private static final ModeSetting sort$delegate;
    @NotNull
    private static final SettingGroup targets$delegate;
    @NotNull
    private static final BooleanSetting antibot;
    @NotNull
    private static final BooleanSetting players;
    @NotNull
    private static final BooleanSetting mobs;
    @NotNull
    private static final BooleanSetting animals;
    @NotNull
    private static final BooleanSetting villagers;
    @NotNull
    private static final BooleanSetting hostiles;
    @NotNull
    private static final BooleanSetting onlyAngryHostiles;
    @NotNull
    private static final BooleanSetting projectiles$delegate;
    @NotNull
    private static final BooleanSetting ignoreInvisibleEntities;
    @NotNull
    private static final BooleanSetting ignoreNamed;
    @NotNull
    private static final BooleanSetting ignoreTeam;
    @NotNull
    private static final BooleanSetting ignoreCreative;
    @NotNull
    private static final BooleanSetting ignoreNaked;
    @Nullable
    private static Entity target;
    @NotNull
    private static List targets;
    @NotNull
    private static Rotation savedRotation;
    private static boolean b;
    private static String J;
    private static final long ab;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] lb;
    private static final Integer[] mb;
    private static final Map nb;

    private Aura() {
        super("aura", Categories.getAttack(), null, null);
    }

    public final float getAttackRange() {
        return ((Number)attackRange$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    public final boolean getIgnoreWalls() {
        return (Boolean)ignoreWalls$delegate.getValue(this, delegatedProperties[1]);
    }

    public final int getFov() {
        return ((Number)fov$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    @NotNull
    public final AuraRotationMode getRotationMode() {
        return (AuraRotationMode)((Object)rotationMode$delegate.getValue(this, delegatedProperties[3]));
    }

    public final boolean getPriorityManual() {
        return (Boolean)priorityManual$delegate.getValue(this, delegatedProperties[4]);
    }

    @NotNull
    public final AuraSwitchMode getSwitchMode() {
        return (AuraSwitchMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[5]));
    }

    public final boolean getOnlyWeapon() {
        return (Boolean)onlyWeapon$delegate.getValue(this, delegatedProperties[6]);
    }

    public final boolean getOldDelay() {
        return (Boolean)oldDelay$delegate.getValue(this, delegatedProperties[7]);
    }

    public final int Bj() {
        long l = 0L;
        l = ab ^ l;
        return ((Number)minCps.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)23911, (long)(0xAF1FF8033A35FA7L ^ l))])).intValue();
    }

    public final int L() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return ((Number)maxCps.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)15690, (long)(0x11048C3FFC5F8BE3L ^ l))])).intValue();
    }

    public final boolean i() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)shieldBreaker.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)17154, (long)(0x518AEA2B7ACEAA07L ^ l))]);
    }

    public final boolean Z() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)splashWhileOnGround.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)31073, (long)(0x48C4433791C98963L ^ l))]);
    }

    public final boolean getBackTrack() {
        return (Boolean)backTrack$delegate.getValue(this, delegatedProperties[12]);
    }

    public final int getMoveDelay() {
        return ((Number)moveDelay$delegate.getValue(this, delegatedProperties[13])).intValue();
    }

    public final int getAttackChance() {
        return ((Number)attackChance$delegate.getValue(this, delegatedProperties[14])).intValue();
    }

    public final boolean getPauseWhileEating() {
        return (Boolean)pauseWhileEating$delegate.getValue(this, delegatedProperties[15]);
    }

    public final boolean getPauseInInventory() {
        return (Boolean)pauseInInventory$delegate.getValue(this, delegatedProperties[16]);
    }

    @NotNull
    public final AuraWhileShieldingMode B9() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ ab;
        return (AuraWhileShieldingMode)((Object)whileShielding.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)19344, (long)(0x4FED02587BBE50E5L ^ l))]));
    }

    public final boolean getDeathDisable() {
        return (Boolean)deathDisable$delegate.getValue(this, delegatedProperties[18]);
    }

    public final boolean getOverrideCrosshair() {
        return (Boolean)overrideCrosshair$delegate.getValue(this, delegatedProperties[19]);
    }

    public final boolean getRotateOnDisable() {
        return (Boolean)rotateOnDisable$delegate.getValue(this, delegatedProperties[20]);
    }

    public final boolean getOnHitSwap() {
        return (Boolean)onHitSwap$delegate.getValue(this, delegatedProperties[21]);
    }

    @NotNull
    public final AuraRandomizeDelayMode BH() {
        long l = 0L;
        l = ab ^ l;
        return (AuraRandomizeDelayMode)((Object)randomizeDelay.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)25900, (long)(0x2AF0C1E3DC869D76L ^ l))]));
    }

    @NotNull
    public final AuraSprintMode getSprintMode() {
        return (AuraSprintMode)((Object)sprintMode$delegate.getValue(this, delegatedProperties[23]));
    }

    @NotNull
    public final AuraEspMode getEsp() {
        return (AuraEspMode)((Object)esp$delegate.getValue(this, delegatedProperties[24]));
    }

    @NotNull
    public final AuraSortMode getSort() {
        return (AuraSortMode)((Object)sort$delegate.getValue(this, delegatedProperties[25]));
    }

    @NotNull
    public final SettingParent getTargets() {
        return (SettingParent)targets$delegate.getValue(this, delegatedProperties[26]);
    }

    public final boolean s() {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 56 | l << 8 >>> 8) ^ ab;
        return (Boolean)antibot.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)25511, (long)(0x729EB0DB359E0877L ^ l2))]);
    }

    public final boolean F() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)players.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)14767, (long)(0x75DD75D813837081L ^ l))]);
    }

    public final boolean B3() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)mobs.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)18986, (long)(0x639DE7BB4BE0B5E9L ^ l))]);
    }

    public final boolean x() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)animals.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)931, (long)(0xFD7F3A68D80C83DL ^ l))]);
    }

    public final boolean j() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)villagers.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)15399, (long)(0x10F71217D4C78CEL ^ l))]);
    }

    public final boolean t() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)hostiles.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)22823, (long)(0xE6980F1EBE092C3L ^ l))]);
    }

    public final boolean Y() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)onlyAngryHostiles.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)10407, (long)(0x1EBD0308024A650FL ^ l))]);
    }

    public final boolean getProjectiles() {
        return (Boolean)projectiles$delegate.getValue(this, delegatedProperties[34]);
    }

    public final boolean B0() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)ignoreInvisibleEntities.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)19413, (long)(0x2B2611A8F0F2AE23L ^ l))]);
    }

    public final boolean Bn() {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ ab;
        return (Boolean)ignoreNamed.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)2628, (long)(0x5F6EFAD9A094C2L ^ l2))]);
    }

    public final boolean K() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)ignoreTeam.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)12627, (long)(0x2340C3F1A6D28CDAL ^ l))]);
    }

    public final boolean Bo() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)ignoreCreative.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)24031, (long)(0x19C53324BD378706L ^ l))]);
    }

    public final boolean r() {
        long l = 0L;
        l = ab ^ l;
        return (Boolean)ignoreNaked.getValue(this, delegatedProperties[Aura.zkmBootstrap2("h", (int)1498, (long)(0xDFDC296EE5004F3L ^ l))]);
    }

    @Nullable
    public final Entity getTarget() {
        return target;
    }

    public final void setTarget(@Nullable Entity entity) {
        target = entity;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = ab ^ 0x524F522FB892L;
        long l2 = l ^ 0x2FB707016E85L;
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            target = null;
            return;
        }
        if (this.shouldPause()) {
            target = null;
            if (this.getRotateOnDisable() && b) {
                Globals.getPlayer().rotate(savedRotation.getYaw(), false, savedRotation.getPitch(), false);
                b = false;
            }
            savedRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
            Iterable iterable = targets;
            for (Object t : iterable) {
                RotationHandler rotationHandler = (RotationHandler)t;
                rotationHandler.reset();
            }
            return;
        }
        if (this.getBackTrack()) {
            Backtrack.INSTANCE.update();
        }
        if (target != null && target instanceof LivingEntity) {
            Entity entity = target;
            Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.LivingEntity");
            if (((LivingEntity)entity).isDead()) {
                Sounds.INSTANCE.playToggle(false);
            }
        }
        TargetManager.INSTANCE.L(Aura::onPlayerUpdate$lambda$0, l2);
    }

    @Flow
    private final void onSprint(SprintEvent sprintEvent) {
        block0: {
            if (!ManagedRotationHandler.INSTANCE.w() && !Sprint.INSTANCE.V()) break block0;
            sprintEvent.setSprinting(false);
        }
    }

    @Flow
    private final void onPostTick(PostTickEvent postTickEvent) {
        AuraRotationHandler.INSTANCE.runPending();
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block3: {
            long l = ab ^ 0x498698A37231L;
            long l2 = l ^ 0xA1C58A2E5A6L;
            if (Globals.getMc().player == null || Globals.getMc().world == null) {
                if (!this.getBackTrack()) break block3;
                Backtrack.INSTANCE.onReceivePacket(receivePacket);
                break block3;
            }
            if (receivePacket.getPacket() instanceof EntityStatusS2CPacket) {
                if (((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() == 48 && target != null && Intrinsics.areEqual(((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld()), target)) {
                    Entity entity = target;
                    Intrinsics.checkNotNull(entity);
                    Notifications.push$default(Notifications.INSTANCE, "Aura", "\u0423\u0441\u043f\u0435\u0448\u043d\u043e \u0441\u043b\u043e\u043c\u0430\u043b\u0438 \u0449\u0438\u0442 \u0438\u0433\u0440\u043e\u043a\u0443 " + entity.getName().getString(), 2, NotificationType.SUCCESS, null, 16, null);
                }
                if (((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() == 3 && Intrinsics.areEqual(((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld()), Globals.getPlayer()) && this.getDeathDisable()) {
                    this.disableWithMessage("Disabling due to death!", l2);
                }
            }
            if (!this.getBackTrack()) break block3;
            Backtrack.INSTANCE.onReceivePacket(receivePacket);
        }
    }

    @Flow
    private final void onPostTasksProcess(PostTasksProcessEvent postTasksProcessEvent) {
        block0: {
            if (!this.getBackTrack()) break block0;
            Backtrack.INSTANCE.shouldDelay();
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block6: {
            long l;
            long l2 = l = ab ^ 0x6766256A4EC5L;
            long l3 = l2 ^ 0x5EF440E0E98AL;
            int n = (int)(l3 >>> 32);
            int n2 = (int)(l3 << 32 >>> 48);
            int n3 = (int)(l3 << 48 >>> 48);
            long l4 = l2 ^ 0x26D572CA7FBBL;
            long l5 = l4 >>> 16;
            int n4 = (int)(l4 << 48 >>> 48);
            long l6 = l2 ^ 0x66BE7BD5826L;
            if (Globals.getMc().player == null || Globals.getMc().world == null || !AuraSwitchHandler.INSTANCE.canAttackWithHeldItem(this.getOnlyWeapon(), this.getSwitchMode()) || target == null) {
                return;
            }
            switch (Aura$WhenMappings.i[this.getEsp().ordinal()]) {
                case 1: {
                    MatrixStack matrixStack = render3DEvent.getStack();
                    Entity entity = target;
                    Intrinsics.checkNotNull(entity);
                    ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                    Entity entity2 = target;
                    Intrinsics.checkNotNull(entity2);
                    float f = 1.0f - RangesKt.coerceIn(clientPlayerEntity.distanceTo(entity2) / this.getAttackRange(), 0.0f, 1.0f);
                    Entity entity3 = entity;
                    MainFramebuffer.INSTANCE.renderEntityGlow(matrixStack, l5, entity3, (char)n4, f);
                    break;
                }
                case 2: {
                    MatrixStack matrixStack = render3DEvent.getStack();
                    Entity entity = target;
                    Intrinsics.checkNotNull(entity);
                    ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                    Entity entity4 = target;
                    Intrinsics.checkNotNull(entity4);
                    float f = 1.0f - RangesKt.coerceIn(clientPlayerEntity.distanceTo(entity4) / this.getAttackRange(), 0.0f, 1.0f);
                    Entity entity5 = entity;
                    MainFramebuffer.INSTANCE.renderEntityChams(matrixStack, n, (char)n2, entity5, (char)n3, f);
                    break;
                }
                case 3: {
                    MatrixStack matrixStack = render3DEvent.getStack();
                    Entity entity = target;
                    Intrinsics.checkNotNull(entity);
                    MainFramebuffer.INSTANCE.renderEntityOutline(matrixStack, entity);
                    break;
                }
            }
            if (!this.getBackTrack()) break block6;
            Backtrack.INSTANCE.X(l6, render3DEvent);
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        block1: {
            long l = ab ^ 0x7CE7A9B65EA3L;
            long l2 = l ^ 0x4ABA18D93A40L;
            long l3 = l2 >>> 32;
            int n = (int)(l2 << 32 >>> 32);
            if (!AuraSwitchHandler.INSTANCE.canAttackWithHeldItem(this.getOnlyWeapon(), this.getSwitchMode()) || target == null) {
                return;
            }
            if (Aura$WhenMappings.i[this.getEsp().ordinal()] != 3) break block1;
            MainFramebuffer.INSTANCE.beginCapture(l3, n);
        }
    }

    @Flow
    private final void onUpdateCrosshairTarget(UpdateCrosshairTarget updateCrosshairTarget) {
        if (target == null || !this.getOverrideCrosshair()) {
            return;
        }
        Profilers.get().pop();
        updateCrosshairTarget.cancel();
        if (Globals.getMc().getCameraEntity() != null && Globals.getMc().world != null && Globals.getMc().player != null) {
            HitResult hitResult;
            Globals.getMc().crosshairTarget = hitResult = MovementUtil.INSTANCE.canReach(Globals.getPlayer().getBlockInteractionRange(), this.getAttackRange(), savedRotation.getYaw(), savedRotation.getPitch());
            Globals.getMc().targetedEntity = hitResult instanceof EntityHitResult ? ((EntityHitResult)hitResult).getEntity() : null;
        }
    }

    @Override
    public void onEnable(long l) {
        this.clearTarget();
    }

    @Override
    public void onDisable(long l) {
        if (this.getRotateOnDisable() && Globals.getMc().player != null) {
            Globals.getPlayer().rotate(savedRotation.getYaw(), false, savedRotation.getPitch(), false);
        }
        this.clearTarget();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isAttacking() {
        long l = 0L;
        l = ab ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-8411593773195908510L, (long)l);
        if (!this.getEnabled()) return false;
        if (target == null) return false;
        boolean bl = AuraSwitchHandler.INSTANCE.canAttackWithHeldItem(this.getOnlyWeapon(), this.getSwitchMode());
        if (callSite != null) return bl;
        if (!bl) return false;
        return true;
    }

    public final void clearTarget() {
        target = null;
        Backtrack.INSTANCE.reset();
        if (Globals.getMc().player == null) {
            return;
        }
        savedRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
        ManagedRotationHandler.INSTANCE.f(false);
    }

    private final boolean handleShieldBreaker(int n, char c, short s) {
        SlotResult slotResult;
        int n2;
        int n3;
        int n4;
        block9: {
            block8: {
                long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)s << 48 >>> 48) ^ ab;
                long l2 = l ^ 0x325AC3FDC06FL;
                n4 = (int)(l2 >>> 48);
                n3 = (int)(l2 << 16 >>> 48);
                n2 = (int)(l2 << 32 >>> 32);
                if (!this.i() || !(target instanceof PlayerEntity)) break block8;
                Entity entity = target;
                Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                if (((PlayerEntity)entity).isUsingItem()) break block9;
            }
            return false;
        }
        Entity entity = target;
        Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
        if (!Intrinsics.areEqual(((PlayerEntity)entity).getOffHandStack().getItem(), Items.SHIELD)) {
            Entity entity2 = target;
            Intrinsics.checkNotNull(entity2, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
            if (!Intrinsics.areEqual(((PlayerEntity)entity2).getMainHandStack().getItem(), Items.SHIELD)) {
                return false;
            }
        }
        if (!(slotResult = InventoryUtil.INSTANCE.findInInventory((short)n4, Aura::isAxe, (short)n3, n2)).isFound()) {
            return false;
        }
        if (slotResult.getSlot() > 8) {
            GuiMove.INSTANCE.setMacroActive(true);
            InteractionUtil.clickSlot(slotResult.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
            ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
            PlayerEntity playerEntity = Globals.getPlayer();
            Entity entity3 = target;
            Intrinsics.checkNotNull(entity3);
            clientPlayerInteractionManager.attackEntity(playerEntity, entity3);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            InteractionUtil.clickSlot(slotResult.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
            GuiMove.INSTANCE.setMacroActive(false);
        } else {
            InventoryUtil.INSTANCE.switchToSlot(slotResult.getSlot());
            ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
            PlayerEntity playerEntity = Globals.getPlayer();
            Entity entity4 = target;
            Intrinsics.checkNotNull(entity4);
            clientPlayerInteractionManager.attackEntity(playerEntity, entity4);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            InventoryUtil.INSTANCE.restoreSelectedSlot();
        }
        DefaultRotationHandler.INSTANCE.A(10);
        return true;
    }

    private final void attack(Entity entity, long l, Rotation rotation) {
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x3392EAE37441L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x750C15B7DE0FL;
        int n4 = (int)(l4 >>> 32);
        long l5 = l4 << 32 >>> 32;
        if (this.getAttackChance() == 100 || Random.Default.nextFloat() * 100.0f < (float)this.getAttackChance()) {
            PiercingWeaponComponent piercingWeaponComponent;
            Object object;
            Object object2;
            Object object3 = targets;
            Object object4 = object3.iterator();
            while (object4.hasNext()) {
                object2 = object4.next();
                object = (RotationHandler)object2;
                object.preAttack(n4, l5);
            }
            Vec3d vec3d = entity.getBoundingBox().getCenter();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            object3 = Rotation.Companion.to(vec3d);
            if (this.getRotationMode() == AuraRotationMode.GRIM_SILENT) {
                object4 = Globals.getPlayer();
                ((ClientPlayerEntity)object4).networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(((Entity)object4).getX(), ((Entity)object4).getY(), ((Entity)object4).getZ(), ((Rotation)object3).getYaw(), ((Rotation)object3).getPitch(), ((Entity)object4).isOnGround(), ((ClientPlayerEntity)object4).horizontalCollision));
            }
            if ((piercingWeaponComponent = Globals.getPlayer().getStackInHand(Hand.MAIN_HAND).get(DataComponentTypes.PIERCING_WEAPON)) != null && !Globals.getInteractionManager().isFlyingLocked()) {
                Globals.getInteractionManager().attackWithPiercingWeapon(piercingWeaponComponent);
            } else {
                Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entity);
            }
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            if (this.getRotationMode() == AuraRotationMode.GRIM_SILENT) {
                object2 = Globals.getPlayer();
                ((ClientPlayerEntity)object2).networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(((Entity)object2).getX(), ((Entity)object2).getY(), ((Entity)object2).getZ(), ((Entity)object2).getYaw(), ((Entity)object2).getPitch(), ((Entity)object2).isOnGround(), ((ClientPlayerEntity)object2).horizontalCollision));
            }
            object4 = targets;
            object = object4.iterator();
            while (object.hasNext()) {
                Object e = object.next();
                RotationHandler rotationHandler = (RotationHandler)e;
                rotationHandler.postRotate(rotation, (short)n, n2, n3);
            }
        } else {
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        }
        Globals.getPlayer().resetTicksSinceLastAttack();
        AimSettings.INSTANCE.randomizeYaw();
    }

    public final boolean getBlockCollisions() {
        return Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().expand(-0.25, 1.0, -0.25)).iterator().hasNext();
    }

    private final boolean shouldPause() {
        return MovementUtil.INSTANCE.I() && this.getPauseInInventory() || Globals.getPlayer().isUsingItem() && this.getPauseWhileEating() && Globals.getPlayer().getActiveItem().get(DataComponentTypes.BLOCKS_ATTACKS) == null || Globals.getPlayer().isUsingItem() && this.B9() == AuraWhileShieldingMode.PAUSE && Globals.getPlayer().getActiveItem().get(DataComponentTypes.BLOCKS_ATTACKS) != null || !AuraSwitchHandler.INSTANCE.canAttackWithHeldItem(this.getOnlyWeapon(), this.getSwitchMode()) || Blink.INSTANCE.getEnabled() && Blink.INSTANCE.getAttackAction() == BlinkAttackActionMode.NONE && !ElytraTarget.INSTANCE.getEnabled();
    }

    @NotNull
    public final AuraRotationMode isElytraTargeting() {
        return ElytraTarget.INSTANCE.getEnabled() && Globals.getPlayer().isGliding() || MaceElytraTarget.INSTANCE.getEnabled() ? AuraRotationMode.DEFAULT : this.getRotationMode();
    }

    private static final boolean settingVisible() {
        return INSTANCE.getRotationMode() == AuraRotationMode.SNAP;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getOldDelay();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getOldDelay();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getBackTrack();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.t();
    }

    private static final Unit onPlayerUpdate$lambda$0$lambda$0(boolean bl, boolean bl2) {
        long l;
        long l2 = l = ab ^ 0x3BCE9D2C830EL;
        long l3 = l2 ^ 0x7CF5E0532ED8L;
        long l4 = l2 ^ 0x520DFDF6460BL;
        int n = (int)(l4 >>> 32);
        int n2 = (int)(l4 << 32 >>> 48);
        int n3 = (int)(l4 << 48 >>> 48);
        b = true;
        if (bl && !INSTANCE.handleShieldBreaker(n, (char)n2, (short)n3)) {
            Entity entity = target;
            Intrinsics.checkNotNull(entity);
            INSTANCE.attack(entity, l3, savedRotation);
        }
        return Unit.INSTANCE;
    }

    private static final Unit onPlayerUpdate$lambda$0() {
        boolean bl;
        long l;
        long l2 = l = ab ^ 0x63FF7B95D759L;
        long l3 = l2 ^ 0x58B0BCE9C395L;
        long l4 = l2 ^ 0x2BCA17874FDCL;
        int n = (int)(l4 >>> 32);
        int n2 = (int)(l4 << 32 >>> 48);
        int n3 = (int)(l4 << 48 >>> 48);
        long l5 = l2 ^ 0x4374A11D5AE4L;
        long l6 = l2 ^ 0x32483761C0F1L;
        int n4 = (int)(l6 >>> 32);
        int n5 = (int)(l6 << 32 >>> 48);
        int n6 = (int)(l6 << 48 >>> 48);
        long l7 = l2 ^ 0x5D5158FD67BFL;
        target = TargetManager.INSTANCE.y(n4, (short)n5, (short)n6, target);
        Iterable iterable = targets;
        for (Object t : iterable) {
            RotationHandler rotationHandler = (RotationHandler)t;
            rotationHandler.reset();
        }
        if (target == null) {
            if (INSTANCE.getRotateOnDisable() && b) {
                Globals.getPlayer().rotate(savedRotation.getYaw(), false, savedRotation.getPitch(), false);
                b = false;
            }
            savedRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
            return Unit.INSTANCE;
        }
        if (INSTANCE.getSwitchMode() == AuraSwitchMode.NORMAL) {
            AuraSwitchHandler.switchToWeapon$default(AuraSwitchHandler.INSTANCE, l7, false, 1, null);
        }
        boolean bl2 = ManagedRotationHandler.INSTANCE.J(DefaultRotationHandler.k(DefaultRotationHandler.INSTANCE, 1, l3, null), n, (short)n2, n3);
        if (INSTANCE.getFov() == 1) {
            v3 = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
        } else {
            AimMode aimMode = INSTANCE.isElytraTargeting().getAimMode();
            Entity entity = target;
            Intrinsics.checkNotNull(entity);
            v3 = savedRotation = aimMode.calcRotation(entity, l5, savedRotation, bl2);
        }
        if (INSTANCE.isElytraTargeting().getAimMode().p()) {
            Entity entity = target;
            Intrinsics.checkNotNull(entity);
            bl = PlaceManager.INSTANCE.canHitEntity(entity, savedRotation, INSTANCE.getAttackRange(), !INSTANCE.getIgnoreWalls());
        } else {
            ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
            Entity entity = target;
            Intrinsics.checkNotNull(entity);
            bl = clientPlayerEntity.canSee(entity) || INSTANCE.getIgnoreWalls();
        }
        boolean bl3 = bl;
        RotationManager.INSTANCE.submit(new ActionRotationRequest(savedRotation, 130, () -> Aura.onPlayerUpdate$lambda$0$lambda$0(bl2, bl3)));
        return Unit.INSTANCE;
    }

    private static final boolean isAxe(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return itemStack.getItem() instanceof AxeItem;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Aura.ab = ZkmKeys.a(423833835999483435L, -4703635964514932656L, MethodHandles.lookup().lookupClass()).a(66547675109402L);
                        var20 = Aura.ab ^ 137403740968709L;
                        Aura.hb = new HashMap<K, V>(13);
                        Aura.zkmSetFlag("pTYN2b");
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
                        var18_3 = new String[130];
                        var16_4 = 0;
                        var15_5 = "\u0016\u00c1\u0011\u00a1\u008a\u0098Q~\u000f:\u000ee\u0016u\u00a8\u00ce\u0099n\u00a4\u00dfAJ2\u0089(\u0016\u00e64KTR5[/n\u00d4AI\u0005\u0086\u0012n.\u00e7\f\u00b1\u00a7\u00e2\u001d\u00ef\u000b\u00e6t\u00c6&\u00b4\u00c65\u00d8D\u00f3%\u00d1\u0095u\u0010\u00afZV\u00b7z\u001e\u0095\u00ea\u0018\u00c2\u00ef8\u001d\u0018V\u0099(@\u0099\u0086\u00f0\u00cf\u00b9\u00ef\u00c1\u008ei\u00d1D\u00a3\u0003X9o\u00f9~V\u00a9_\u0081\u0011\u001e\t\u009er`\u008a\\\u001e\u00c1&\u001f-\u00f7_Dl\u0018\u00ddL\u00af\u00e4\u0094LD\u00da\u00b3J\u00a7\u00c0742h\u00c4OGWmB`\u00ce(J\u00b1\u0087\u0017\u00c0\u000ec\u00c5\u00c0_\u00f1\u00f6\u00f8H\u00ce\u00e8\u008a*\u00eb\u00e0\u001e\u00a1\u00ce\u00ee\u00cd5{\u00e0T\u00f0\u00b8\u00d1\u00e4\u0011\u00d3\u00de\u001fm\u00d2P\u0010\u00b3\u00bf'\u00cc\u000f9x\u00bc\u00d5!/\u00aa\u009e\u00a7\u008eX\u0018\u00c3\f\u001d\u00bd\u0001B\u00c8H\u009d\u0082\n\u00db\u00f6%q\u00d8\\\u00ce~9\u00fd\u009bj\u00a0(\u0081v\u00d5d\u00dd\u00f7\u0085||\u00a5w\u00f0\u00046U\u001f\b\u00c2\u0007\u00cc\u00a7\u0096\u00abm\u0082\u00e8\u00fb\u00b1n6\u0014Y\u00ca\u00beW\u00e6]\u00d4S; \u0002\u00e5we\u00df\u009d\n\u00cb\u00a5\u00d7-?\u00ab7\u00adW\u00e8D\u00f95\"\u00ac\u0005\u001a\u00d5\u00f6\u009d\u00d1\u000b\u00f5\u00f9r\u0010l\u0091\u0092o\u00c9\u00d8e\u0017m\u0018\u00c5\u009dN\u00ee\u00d1\u009b(t\u00f7\u0084\u00a3g\u00dd\u00b8\u00e5|\u00a7\u00c7\u008a\u0013D\u00a0 \u00ab\u0011\u00931\u009dfE\u00f6\u00d7\u00f9\u0005L!5\u00b4f\u0016 u\u00e4x\u00ba+d(\u00cb\u00ab\u0003_\u0017\u00907\u0010\u00b5C\u009d\u00ca\u00f9:B_l\u0004\u009dE\u0007\u009d\u0095\u0006\u00ac!\u0016\u0092\u00ad\u00b7\u00cak\u00f8\u00d1\u00a2\b\u00e8\u0016[\u00e70\u00e2\u00f4\u00ab\u00e8\u00e3\u00b0\u001bjBf\u000fb\u00ad\u00e1_<T\u00ed\u00a0f\u0089\u00925\u00a3@g3{_\u00d5\u00b8\f\u0091\u0081\u00ee\u0010\u0082\u00ead6\u0003f\u00f1\u00a2\u00e9@\u00a7\u00d0 \u00ce\u0082\u0012\u008av\u0084/&}o'\u00e2\u00cf\u00e8\u0082\u00f50!\u00b5\u00e9~\u00ed\u00d2\u0004{|\u0085\u00bdt\u008d\u00ee\u008c\u0018:\u0005\u0096\u00d0\u00a64f\u00a3\u00b5\u00da\u0094\u00b3\u00c8=\u00e9SY\u001a+\u00ef\u00bf\u0086\u0081\u00c4 p\u0004r\u001a\u00f2\u00a0\u00b1\u00f1\fH=\b\u00af\u0019\u00e8\u000f\b\u00b3\u00ce4\u008d\u00bc>\u0002\u00f3\u00bc\u00d5\u00fbR\u0014u\u00e6\u0010\u00cb\u0090\u0099E\u00fe\u00f8\"Z\u00f6\u00d6\u00c3w6\u00c7\u00bb& \u00d5;\u00cbZ\u0098\u00d2>\u0004\u00b1A\u00c0bt\u00aa\u00b7\u007f[Z\u001f\u0080\u00ad\u000bx\u0005\u009c\u00a3\u00a5\u0013!,\u00bd\u00b0 5\u001b\u00eep\u007f+\u00aeM\u008f\u00a2\f,\u00ef:\u00d5\u00d3c\u0018\u00dd\u000e\u00b4cz\u008e\u0084w\u009a\u000b\u0082\u008c\u0016D\u0010\u008b\u00eb\u0011U1I\u00d8\u00dd\u00c3g\u00b1oCw\u00dc\u00eb o\u00f8_JY4\u00a1\u008a\u008eg;\u00de\u00bb\u00d2\u00fd\u0085Awl\u00b8\u00a9\u00e2\u008bFg\u000f\u0081\u0092J\u0094\u00d3\f N#Y#\u00dc;\u009e\u00a0ch\u0098\u00e8R\u00c3j\u00db\u0000]\u0085|NJ\u00fa\u00d9x?\u0091Vse\u00c4\u00de0\u00bf\u00e2\u00dd\u00eba\u00e9\u00aa\u008e\u0018\u0015\u00b9K\u00f9\u0090P\u008a\u0015\u00b1 \u009b\u00df\u00bd\u0004\u00b9Q\u00c8 \u008bJ\u00beg\u00e1\u00c0\u0092qQ\u00f7\u00d2\u0015\u0094f\u0007,Mw\u00dc]\u001d(j-\u001c\u0081\u00c5\u008f\u00df6\u00b9\u009b\u0010\u00e2\u0088\u00f7+\u00e0\u00fe\u00cd\u0016\u00f0NK\u00f3 \u0014\u001csI\u0012)`\u00f6\u00fa:\u009aq t\u008eB\u0010\n\u00ee\u0082\u00b2rp\u00e7\u00c8\u001c\u0015\u009eS\u00ec\u00a7\t\u00e4\u0010^\u00a2\u00a4\u0013\u008e\u0010\u0006tYj4\u0096/\u0014\u00dbR(\u00ff\u0099Q\u0093k`\u00d7\u0010\u00cbx5sp\u00daY\u0099~\u00a6\u00df\u0089m,\u00c0\u00f2\u00e2\u0010@\u00b8\u00ce\u00f0-\u00b4\u00e6\u007f\u00dd\u0091M'\u00eao\u0010B\u00c8U\u00dd&\u0006\u00ee\u00de\u0003\u0085N\u00fd\u00ca\u000b\\J \u0096\u0090\u00f4\u00e73\u00b9\u0000\t\b\u00c1\u00a3h%\u00e3\r\u00c0\u00fc\u00ec\u00b0`\u0092\u009e\u00afZA\u001c\u00a8~\ni\u0018@(\u008e\u00f3\u00cd\u00b4(\u008a/F\u0097\u00b7K\u00ec\u0091/\u00bd\u009e\u00cd1\u0007\u0081Ie\u009a\u0098 =\u00d8n\u00d5\u00e9%\u00d3\u0093|IC\u009d\u00b7\u00bc@\u0018 @\u00ad\u00c3\u00f6j\u00bf\u0094\u0018\u001e!\u0003\u00ef\u00ed@\u00b0\u00c7\u00a5\u0080@\u00fd_\\v \u00ea2\u00c6\u0082\u0086H\u0000w\u00b6w\u00f2JX\u00ea\u0003\u00a5\b\u00d2w%/H\u00d8\u0002\u001d\u0018V\u0004\u00edT\u00e74\u0018\u00e2X\u00a5\u00de)m>t\u008bP5N\u00a0\u00bf1\u0086\u00f5\u00a5\u009d\\\u0094\u00f3\u00e9\u00a9\u0010\u009a\u00c9\u0002\u0012e\u008d\u0003\u0094v\u00861\u00ec\u00e6\u00197\u0005@\u0093\u00d6KT\u00d8rs\u00ec1U\u00d8\u00e6A>q\u001a<L?\u00c3j\u00c6\u00af\u00ed\u00d7\u00c3\u0090Tk\u0098r\u001a\u00a9\u00b5v\u00170\u00c0[I \u009b\u00b3\u00fd\u00df\u00aa*\u0005w\u0097\u009f\u00b9\u009fY\u00e8-\u00c3F\u00fbSL\u00ab\"\u00fa(\u0097.:%\u00act\u0080:,'\u0083\u00d5*\u00d9\u00bd:(\u0084N\u00cd\u00cd\u00ba\u0098BB\u00b7\u00ec\u00f0\u00b7\u0006D\u00be\u00b2-\u008d\u00bb=3P\u009c(\u009b\u0095w\u00a3\u0011;\u00e2\u00a2\u00dc*\\\u00bcMW*k\u008a\u00e8>\u00ffQ\u00e7\u0082}\u009e\u009fe\u0080P\u00f2\u00b7\u00ca\u00d4\u00b2N\u00e1DV\u0083b(\u00bfSbL\u00b2\u00ad~5C\u00dda$xlU\u00c9<9\r\t\u0099=\u00b8\u0011\u0016\u001e\u00cc\u0084hV /d\u0016\u009c\u00ed\u00d9+\u0088\u00c8(\"\u000fE\u008eBD\u00cb\u0080F&o\bM\u0007\u00f1\u00fdBs\u00a1\u00cb\u007fl\u00f5\u0085}~\u00fa\u00c6\u00f7t{\u0099\u00f6\u0003\u00e8n*m\u0093\u00f9\u0018\u000e&e\u00a9\u00c79\u00e3F\u00fd\u0090\u00a8\u00f0\u00b1\u0081\u008b\u00f7U?\u0016\u00b7\u00b8\u009f\u00e9~ 8!\u008b\u00e4\u0098\u0080\u0092\u0097\u0084! -\u000fb\u0003\u00cf\u00e7\u00cb:\u00a2\u00a0\u0017Uk\u00f2\u007f\u00a0\u00d2rG\u0013\u00ae ?\u0090A+\"\u0011M\u000e&\u00a9\u00e2\u00ac`^\u00d4\u00f2\u0083S\b\u00f9\u008f\\\u0097\u000b\b\u00c9\u00d2\u00b7\u00a1s?\u00a1(\u00bfz\u00e3\"\u0007\u00d7\u00de\u00e1!\u0014\u0094?3(\u0095\u0017\u00fb\u00c5oX\u0005\u0000\u00f6\u008a\u008a\u00c9y\u00e0\u00bd?\u000b%`l\"\u009bB\u00b2\u00b6P \u00d6\u00bf\u0086\u0019\u00d0hW6\u00d2,\u00c40\u00c4\u00c7\u008f\u0002\t\u00fd\u0085yL\u0019C\u0013\u009f}\u00e9\u00b2(\u0018d\u00048hU\u00a4%\u0093I\nP\u0003\u00beR\u001cn\u00ba\u0081\u0085\u00d9\u00f7\u0019\u00d9_\u0082\u007f\u001a\u00c4\u00982]\u00d5\u0096w\u00b0\u0013\u008f]\u00a3\u00f0\u00f1\u00a0YI\u0012\u00e3j$Z\u0000N\u00a9E\u00c0L\u00a2\u00eePB(\u00c6<\u00e7W\u00d9\u0014Hi;\u00f8\u00ea\u00f4\u0085\u00ef\u00d0q\u00b8\u0004e\u001c5\u008a\u00ca\u00a8=\u00d7\u00b6.L\u0081\u00ba\u0097\u00c3Ag\u0081\u0013H\u00f5\u0003\u0018v\u00bc\u00d1\u0006Pe f].0\u00c0`\u000fGK=\u0005\u00be\u00f3\u0000\u0081\u0002N8\u00b8\u00de\u00e1\u00fd\u00ae\u00f8^\u00e1\u00ff\u00fb\u00c9\u00e7\u00ea\u00ff\u008enN.\u00bf-\u0004\u00f7\u00dd\u009e\u00cfw\u0083\u00a1\u008c=\u00edNq\u00d7\u0005@\u00cb\u0014@\u00bef\u00aa\u00f9\u000f\u000f1\u00f7\u009c\\r\u00d3\u00bf\u009271\u008f \u00ae\u00a1d.7\u008f\u009b\u00e7\u007f\u00ed\u00e3[\u00de\u00fbKp\u00c4J2\u00fa\"-\u00a9aq\u00f9\u00d4i\u00df\u00e7\u00eff(\u00af\u0010\u00d7\u00ed&\u00ca\u009c\u00f5\u00d9Z\u00ec\u00c5\u00e28\"\u0084\u00f9\u00cex\u00e5\u009f\u001a\u00c1\"Z\u00a5`]\u001b\t\u0091\u00b0\u00c0\u00eez\u00de\u00ef\u00b36\u00da \u00df,\u0093\u00a7\u0085+\u00aa\u0013kL>^\u00c0\u00bc\u0097\u0089`\u0095g\u00bb\u00bd\u0002;\u00da\u00b0\u00ccg\u00f1\u008f\u0016\u00aeMx+Nl\u00ff-\u00f6\u00f8|\u009d\b\u00e3\u009e\u0082,\\\u000eO\u00ad\rgK&\u00ef\u00cfz\u001a\u00e58a\u00d9\u0099\u001c\u00f4n\u00c4(\u00aa\u00e6\u001fSZ\u0091\u00b5\u00f31\u00cd^\u00d9\u00b8\u00e0\u00f4\u00be\u000elB\u009d\u00b7y\u00b1\u00c0 \u0097\u00ac-\u0017\"\u00e2\u00c8\u00064glZ\u00c8\u0094\u0091\u00b4d\u0097\u00ef\u009bl\u00b6a\r\u0015H\u00ff\u0090c\u009c\u00b4\u00b94<\u001d)\u0090\u00ad\u001a:k\u00ce\u0080\u00bf\u00d4I\u00d8;\u0084\u0089q+-`-\u00cb\u00d7y\u00d4 \u008fa\u00d1\u00a5E\u0085E\u00f8\u00b0\u0005\u00d9\u00ea7\u00f2\u00beU\u00f8\u00cdJC(\f9\u00f0\u00f22\u00e1\u00bb\u000e\u00a0\u00f2\u009c\u0010dn\u007fEX8z\u00079\u00bb\u00a19\u00a4\u00cd\u00e9\u00b0\u0018\u00d9\"\u00ea\u00e2\u00c2\u0096\u00af\u0085o\u00ec\u001e\u00b5\u00cd\u00a6\u0010\u0094\u009bUtj\u00e5\f3R\u0010\u00ea\u0087\u008e\u00bc=%\u00abdF\u00c4\u00cc\u00ac\u008dv\u001cv(^\u00f9*\u00f5\u00b4\u00aer\u00ab\u0007G\u00af\f\u00ea\u00c6\u00a6\u00ef\u00a1\u009b*'C\u0081\u0097\u00c3\u001fp\u00d6yvu\u00c23HX\u008f\u00f8\u00fes\u00ec\u00f4\u0018U\u0018\u0016\u00fa\u0002\u00ce\u00b9N\u00d8\u0002\u0002\u0089\u0010l3s\u00b8\u007fL\u00aeje/3\u0010\u00c7\u00ae\t~\u00e4\u00e4k\u0015\u00cf2\u00b7\u00df}\u00b9\u00f4\u0086@(\u00e1\u00f7\u0084i\u009fhrp\u001e\u00cd\u00d7\u00de\u00d7\u0005)C\u00d5b;\u0096\\\u00e8L\"\u008cM\u00be\u00b7\u00db\u0093\u00c0N\u0004\u0092\u0010p\u00fd5I\u000b\u000e\u0084\u0006\u0080\u00d8\u00f1\u009f\u00ade\u00d8\u008e\u00e9{\u00fa\u00a4o\u008c\u00bc9\u009a\u00dbe8\u0018\u00f3\u0097+&7\u00d7\u0007\u0081K\u0092\u00d8\u0007]\u00f7*\u009f\u008f\u00c10~\u0015\u00d1\u0093T \u00cc)\u0018\u00a28\u0099\u00e8\u00f2>\u00ce0\u00e064\u0084\u0090AX>L7\u00a2\u00deV\u00d1dK\u00fc-t\u009an0\u0001#5\u00d3\u00a3\u0013w~\u00a6@\u00f9\u00f2\u001f\u00f2\u00cb\u00c7\u00af\u00b9\u00d0\u0099\u0090\u00f5\u00e4!\u0019!e\u00e7wDK\u00acQi6cNHq\u00d0\u00c6n\u00b6V>\u0096\u00c6\u00f7\u0018q\u00dc\u0015w\u00ea\u00d8(8\u00de3\u00c2\u0087X-\u001f\u00bc\u009b\n\u00c4s^\u00c7\u000f#`\u00f6\u0085S:\u0018\u00de?*\u009d\tp#M:G\u0017ES0\u0086\u00e0\u0087\u00a0\u00f0\u00b9/\u001a\u00ee\u00e5\u00f3k$\u001c\u00d2\u00e5\u00ae\u00b7\u0082\u00b6+\u001f\u00b5\u00b1/Y\u00b9q\u0091DLv\u0002\u009dI\u00ee\u00fb\u001e\u009c\u00c1. \u0092\u001a\u0002q\u00a6 \u008b\u0016\fM\u00afgL\u0019\u0084n\u00a2\u00e1u\n\u00c0\u00be\u00ed\u00bc\u0080\u0086\u00c8I\u00fd\f\u0097\u00f8,\u00bd\u00e5 \u00f0FQ\u00b3\u00dd\u00a6\u0000\u00b4\u00161\u00d5,\u0015~T7W\u00bbO\u007f\u009fm\u00c5h\u00a8\u00b5\u00c9?\u00cf|9* H5\u00c2\u00edA\u00d5A\u0080D\u0001\u0010\u007f\u00e9B\u0096\u00d8 QPI\u00ba'\u00af\u00acz)<Yy6\u00fa\f\u0018;]\u001b\u0080\u008b6\u00d3%4N\u009c;\u00c0\u009d\u00c0\u00ab\u00b9\u00cb\u00e0\u000f\u0082\u0084pT \u00b4\u00ab\u00f0\u00f7\u00c8\u00f5;\u0013\u0081VGo\u00c0.\u00b9\u0085\u00df\u00d9\u00b8`\u00dd\u00ea\u009a\u00a3/\u0004\u009d\u0099\u00f3\u00be\u0083\u00b2(Z\u00f5\u00e1\u0003\u00ca7\u008e5n\u00b8\u0014\u00a9\u00ed\u009a\u00c2Kc0`\u00cd\u00c0\u009b\u00de,\u0007\u00ed\u00ad?\u00bf\u0088\u0080\u00ef\u001fmc\u00bf\u00fadpj(`\u00aeS<M.\u00be\u00a6\u0081\u00c1\u00da\u00bc\u00f3\u001fk\u00a5\u0007\u0089\u00c0\u000bz\u00cel.\u009c\u0097\u0090e\u0095\u00b4<\u0000\u00b8r\u0005uV\u00b6\u008b8(3\u00bb\u00f2\u00b0c\u00b4\u0005\u00e8(\u00eb<\u001b/}`B\u00de\u008a\u00af\u00f7\u00ec~[\r\u00b0\u00dc\u00fd\u00b4\u00b8l4\u008e\u00f4\u00c7\u00dcgSg\u0017\u00a0 \u00e4\"\u00f7z\u009bV\u0000\u0085GR\u00e5N8\u0018v\u0005m\u00f7\u0005`\u00e1+\u0088\u001c\u00a7gnE\u008b\u0011B\u00820\u00b3\u00f8u\u009fNO\u00180\u0018\u00e6\u00e9\"3\u00a9A\u008e\u00bc\u00d2\t\u008a\u00ab\u00cb\u009e\u00c9b\u0000\u0088W\u00cdG\u0016\u00b4w\u00cc\u0090\u001a\u00acv\u00d9\u0098\u009a\u00c1\bn\u00a77uc(\u0007\u0014\u00cd\u00cc\u00e9#c\u00c4\u00cc\u00a10\u008f\u00f2\u00beL.}\u00e6X\u00cf$\u00d7\u0083\u00a9\u00ddw\u0092-\u00a0=\r\u00a6o\u0006\u00be|k\u00f3\u009a\u008b\u0010\u0012\u00b3\u0080/\r\u00b9\u0094\u00c4B\u008f\u0099Lwk\u0084\r\u0010\u001e\b\u00dbt\u00af\u00e3\u00a0ld[ )\u0013y\u0017\u00070c0\u00e6\u00f44\u00c4\t\u00f2\u00f6\u00a6j\u0018\u00c3\u009f\u00c9-\u00fdu>\u000bW\u00ac7\u00ddH\u00f1T\u0007\u00f4<\u0082\u00d3\u00c8\u001d\u008en\u0097\u00bd\u00b37-\u00c7\u00a1\u009d\u0080\f\u00dbJ l\tb\u00dfv\t\u0098i\u0094L\u00a8O\u008fy\u00e0s\u00d80T\u00f1\u0084t\u0080\u0010\u00e5\u001d\f\u00fcZ\u00f6\u00b0\u0096 6\u00da,\u00ecG\u00c7\u00a7\u001e+\u00c7\u00b6\u00a3\u001fM\u008a\u0003\u0086\"\u00b9\u00fe\u0098\u00ea\u0082`u\u009a\u00e3|\u00ff\u00da\u000b\u00b2x\u0011\u00bc\u00d6\u008b\u00a2{\u00f1\u00c1\u00bc\u00d1;hX0\u00e0\b.j.\u008b\u00f4\u0082]\u00ab\u00bf\u00a9\u0096\u00f9~w7\u00f00\u001e\u001da\u00e0Z\u0099w\u00ce[QZ\u00ea\u00ea\u0085\u009a\u00ba/\u00b5E\u00865O2\u0013uw\u00ac\u00d2<\u00d6\u00ea\u000e\u00a6\u009d6z\u0019\u00ded\u00ae\u0005\u00aaF\u00de\u00f6\u00d7\u00983\u0082\u0005\u00ee\u0014\u00f4\u009f\u00b2\u00ee\u00ab\u0092\u0013}\u0094q\u0016\u00d3o\u00d2\u0085}V5\u001aZ\u00fd\u00bf@\u00eem\u00fe%X\u00dc;\u0018\u0003\r\u00bdd \u00fc\u0093m\u0004\u00bd\u00b4\u00b6;\u0096f\u00ec\u0014\u0087\u00ef\u00c4\t\u00b1i\u00c5\u00dc\u0095\u009a\u00acr\u00d1W\u00ee\u0003\u00c8\u00f02: (\u009e\u0086c\u00cb\u00c1\u00a0\u00faB(\f\u00c7\u00921\u0015k\u00ef\u0084\u00f1'm4\u00de\u0010O#R+\u008b\u0085\u00a6\u0086(\u0085\nK\u00d2\u00e3\u008eN\u00d3\u0098\u0090\u00b6o\u00de4\u00a4.\u00e7\"v\u00f4\u0013[\u00d3\u00a0l,\u00b2f\u009bhz\u0005Gp\u0004\u0081y\u001e\u00a5\u00aa\u0010\u001f@`\u00f6\u00ff~*l\u0017\u001f\u00c6e\u0012\u0016\u00fb~ \u00c2\u0015\u00cb\u0015\u00f7Ea=\u008e\u00b1C\u00d7\u009e,\u00e4\u0099\u00a5\u00ec\u00fd]\u0001\u0010\u0088\u00a1\u001d\u00f3\u0097\u00b1\u00d4a[|(p\u00ab_\u0000\u00e2\u00c5\u00efS\u00d8\u0090\u00a7\n\u00bf!S\u001d\u00ea\u00cb\u00d6\u00a5\u00e7\u00c3\u00b7\u0017%\u0098\u008dhT$+Y7t|d\u0006\u00d4\u00d1\u0083\u0018H\u00be\u00c6K2\u00d8(\u0098|\u001f\u00d4C\u0001i\u00dc\u0098\u0089\u00c0\u00a0W~\u00fb\u0004e \u00e0J\u000e\u00d8\u00c4\u00b8@\u008f\u00a7\u00f0\u0081x\u00b5\u001e\u00c1\u00ba; \u00adh\u00ff\u00c7\u00d1\u00ee\u000719tH-&\u0096\u0018\u00ec\u00b5\u00bb\u00dd!\u00b4\u00de\u00869\u008c\u00fb\u0010\u00ba2\u00f8\u00ef\u0094\u00a3\u0011JN\u001b\u001fm(j\u00e4\u00a3|\u009b\u0080\u00e19\u00fd\u00e5\u001fj\u009d\u00c0\u0003\n\u00eb\u00d5\u00a5<>\u001a%@\u00ac?(\u001ck\u00b1\u0095\u00ee\u00ba\u00a1\u00f5\fsk0c\u0010\u00d5\u0094\u00d9\u00f6\u00e8\u0018\u001b\u00e8\u0000\u00f8m@\n\u00e6\u00c3\u0094\u0018\u00ed\u00a5\u008b\u008c\u00cf\u00c5\u00d9\u00f8\u00f1\u0017/\u00a1V\u00f4\u0095\u0095\u00c5\u0088\u00b5\u0086<\u00de\u00fc\u00bb\u0018\u00ad\u00b01M\u00cd\u00b0w\u001a0\u00f1v\b)\u00d5\u00a3\u00f5\u009b\u008c\u00c1\u00ac(\u0080!\u0015\u0018`lf+c29\u0096\u00ff\u00ff\u007f\u0010A\u00fd\u0011\u0013?l\u00f6\u00a5\u009d\u0015\u00c0\u00e1\u0010Y\u00ae\u00170\u009d\u00ec\u00ee\u00c4J\u00cc\u00c3\u0004\u00c6s\u00d6\u0091\u0010\u00c1\u00a47\u0005\u00d7\u00d6?\u0089\u00d4a1\u00e2\u00ffH\u0018\u00a8 \u00b0\u0000\u00ed\u0007(i\u00bc\u008a\u00de\u0019J\u00f4\u0081\f\u00a2\u00a4\u0085P\u00b4%\u00aePE[\u009d\u00bdv.b\u00a3\u0090\u0090\u0010\u00fa\u007f\u00c8\u00d7G\u00a8\u00e7\u00d9P\u00b6\u00a2\u00df\u00e0h\u00cd\u00e0\u0010(\u00d3\u001aCIx\u00cf\u000b\u0086\u00d3~e_\u00bb\u00d5\u00cf\u0018\u0086&\u0011\u00c0\u00c5\u00df\u0086r\u00da\u0098\u00b5\u00b0\u0007\u00eb\u00d2\u000b{\u00d8\u0089y{\u00e5\u00af\u0013(\u008e\u001a~\u00b9\u009f\u0017Q)\u00e76s\u00ce\u008e\u00c4\u00cc7\u00b5*\u0080\u000b\u00ff)\u00e4P\u009b7\u00c2\u00c9\u0016\u00a1K\u00edk\u000ekhH\u001f\u0099\u00b3 \u0097\u00d0\u0084j\u00b6Nf\u00b7 \u0001;\u0099i\u0092\u001b\u00dd\u0088\u00f25\u0088\u008e4\u001e\u00baB\u00b3\u0019\u00a6zC.\u0099 \u009b\u00d0\"@\u0089z\u0093(\u00ff\u00f5\u001f\u00f5h\u00f2\u00a4\u00e5\u00fcc\u00ca$\u0083\u00d6\u00ef\u00ac\u0081\u00d0.H\r\u00f6_\u00fcx\u00b6\u0092*}\u0088\u00dd\u008b:\u00f3|\u001d\u00e1\u00e7\u00cb]JM\u0080\u0099\u00f0h;\u00c9v\u00a8\u0096N \u00a7\u00d0\u00e7\u009az\u00df\u00ba:\u009ap\u00a9T\u00c1\u00f4\u001a\u0087G2qYR\u00f2\u008a\u0083\u0098d^\u00c7g\u00a9]\u00af\u0017!\u00c6\u009d\u008f/\u0097\u00f5\u00fb\u001c$\u00fe<\u00b6\u00db\u00a4!\u00bdb\u00e3\u0005\u00e8\u00ec\u0018@\u00ba\u00da'&\u009f\u00f5\u0086\u001bp\u0099\u008e6`\u001c:\u00af\u00c4\u0007\u00e8\u00c4\u00b3\u0092\b\u0087\u00db\u00ccT\u00ef\u0092\u00bb\u0012\u00b2E\u001d\u00008\u00f5m\u00a8\u0017\u0017\u00c10\u0094K(\u00d5K\u00dfl\u00aa-\r\u00c0;m\u00af\u008a1\u00dc\u00f7f\u00be\u00fa\u00e6\u00ad}\u0012\u0096{\u00edun\u00a4\u00ce\u0006B]\u00dc\r\u00ffG\u008a\u0007\u00d1\u00fc\u0080Zd\u00db\u008e$0\u0083\u0013\u000f\u00fc\u000f\u00b1\u00d1\u00a0\u00b3\u0099\u00a0j\u0081\u00cb\u00b8R\\\u001bf\u00a6\u00bf\u009f*\u00e8\u00bd\u00803\u00bb1\u00cf\u00a3o9_\u00c7#\u0015\u00e5\u00d9Gl)+\u00bd1H\u001f\u00be\u0010\u00d9\u00bab\u00faH\u00ebl\u00ecz\u00acA\u00cf\u0085\u0011X\u00a2 \u0017\u00e2\u0085\np9\u00cb,\u001b[\u00d5\u00f0\u00ad\u00ed40\u00d4\u00df6\u00a6\u0094\u000f\u0012\u00a45\u000b\u00d9\u0091\u00f4\u008f\u0096m\u0010\u007f\u00f2\u00fa\u008axG#h\u00d6\u00cby\u00a0n\u0001\u00c3G Py\u0014\u00ca\u009eo\u00ac\u00ce\u00c8\u000f1\u00c9\r\u0085\u00cb\u00fcfB\f\\D\u00b4\u0082\u00caW\u000e\u00dc\u008d\u0004\u0002\u00fa\u00c00\u00b7p7\u00ae\u0094\u00cf[hr&A\u0080\u0015\u0006\u00c2\u00df\u0085\u008a\u00c0#[~Eq\u00885\u00143\u00bc~\u008a\u0097r\u0089\u0093.\u00db\u00c8\u00bf}Q\u00bf\u00cf\u00ac\u00b7:\u00ba\u00bb\u0010sU5Xg\u00d4\u00e0\u0019E]\u00ef\u00caU\u0080\u0016\u00c5(E\u00c9q#\u00ef\u00d2\u00f03\u00c1\u00a4\u0018\u0090*`\u00c4!.\u008cu\u00ef)Wf\u0095\u001d\u00ff.\u00e3\u00ab\u009b\u00c17\u0018\u0000\u00e0\u00be\u0097'i\u0099\u0010>#*$\u00bd\u00d7\u0007V\u00d7(\u00ad\u0092\u00d5\u00c8\u00af3\u0010\u00d5a\u00b6\u0004:}\u00f20_\u00dd/\u00d5\u00c4\u00b8g\u00bb\u0018\u00a0\\B\u00e6\u001b\u0082\u0015\u00f1}\u000e\u0004\u007fyA\u00d6d-\u0090\u00eb\u00c0\u001f\u00fd\u0016L\u0018\u00cch\u00ba\u00e0\u00b0&\u0003E\u0082\u001e\u00d1\u00e1R*FY(w\u00d9V$~\u00a2- \u00b1r]i/\u008b-DAl\t>\u00ee\u00c4)i\te38\u009e\u00d8\fhpP1\u00c9\u00a2\u000eJ\u00e7 8\u00e2B\u00f8\u0000\u00c0^\u0016\u00e3qO\"\u00a8\u00ff\u00d8\u00f0\u008f?\u0097-\u00dc\u00f0\u00f0f\u009e\u00c1\u00a2\u00cd&\u0097d\r\u0010Q\u001d\u0000'\u00ac\f\u00d9\u001b6\u009e{\u00d0\u00f9{dw ?\u00d2T7\u009ff\u00b7\u00a7\u00cb/\u00e9\u00f3v\u0088\u00e2\u00eb\u001c\u001aS0\u00a8\u0007\u00d4\u00f6\u00a01Q\u0018\u0001\u00fa\t\u0092 \u00d8Y&\u0098\u00cb}.\u00a0\u00c5\u00d1\u00da\u00d5p3\u0000C\u001c8{t\u00c3\u000ee\u00a8\u00ce|VSx}\u00bbH\u0018,-\u00ef\u00a1Q\u0083rB\u007fkC\u0098\u00fc\u00b6\u00d9J\u00c2\u00a5[\u00bc#cb\u00cc(]\u00ac\u00a8!\u000fMm\u00fc5&@\u00e8\u00bd\u00b1A9\u00efU\u00db\u0082\u00db\trv4W\u00f3\u0007\u0011\u00af\u00d2\u0010\u00e1j$=\u00bb\u00be\u0005\u001f(v\u0099\u009a\u009d\u009e<1X\n\u0010\u00ca\u00af\"7\u0004\u00b0f\u0091<\u00de\u00a2\u00ca\u00a5\u00ef\u00e1\u00e4\n\u00a7\u00e6\u0005l\u00e5.\u00c5)}\u001c\u00f6\u00d1C \u0004^X\u00a3X\u00da\u00ec-\u0004\u00a1i\u00b6\u00f3W*Y\u001e\u009c*\u008a\u00e1\u00a7,\u00a1\u00ce\u00b7\u0084.P\u0097\u00d8X";
                        var17_6 = 4431;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = Aura.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0016:IZ\u0080\u00b7\u00e4RZ5\u00f3\u0097\u00c5Q\t\u00ddB\faW\u00bc\u00c2p\u0083 \u00fe\u0094\u00a1jk\u00c5\u0015\u0096\u00cc\u00e0\u0082#\u0086\u0095\u00e6e\u009f*\u00d1\u00a1Z\u00d5{\u00ecn\u001f\u0002\u00f3LA\u008d\u00f8";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = Aura.zkmDecodeUtf8(var19_9).intern();
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
                Aura.fb = var18_3;
                Aura.gb = new String[130];
                Aura.nb = new HashMap<K, V>(13);
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
                var6_12 = new long[78];
                var3_13 = 0;
                var4_14 = "\u00aaJu\u00a4\u00f7\u00df\u00d3\u0087\u0017\u00d7m\u00ea1#\u00f1\u00cfd\u00a9\u00d22U?&he\u00c1\u00c6a\u00fbY\u0081}c\u008a~^\u0085\u00e3\u00a6\u000b@\u00d8\u001b(\u00be=\u008d\u00ae\u009b\u00b0\u008bS\"hj\u0003\u009cQ\u00c9Z\u0083\u008a\u00b5\u0014\u0096Bp\u00fd\\\u00c4\u0015X\u0002\u0081'k\u00eac\u00de\u00f2\u00a5\u00f0\u00d9\u00f0E\u00b0ra\u009ai\u00ee\u00ce\u009fEI1L6\u00c4GlcN%\u00c410\u00fa\u009d\u0090erOU\u00ab_7\u00eb\u008b\u00c5\u0089\u00a1P\u00e5\u00edotrk\u0007\u00d1\u00fd\u00ce\u00cb\u00f3\u0013\u00c4D\u00b7\u00d2v\u00a9\u0006Qd\u009d}\u009c\u0012\u0094\u00d8?\u00f3/JS\u00bd\u00af\u008b\u00ce\\\u00bf\u0091Uf~\u00151\u0005\u0004\u00df\u00b7\u00f2\u00ba\u0096K\u008c\u00b5\rvd\u00ca\u00c3*\u00da^\u0083Z\u00cd\u0083\u009a\u00b2\u008d\u00f8\u00cb\u00d0G\u0012\u00b50\"\u001eJ/e\u00b1\u00daPzAR% {FW~K\u00e4\u008e\u00e3\u0088\u0097\fl\u00bd\u00f6E5\u00be\u00fcc`\u00c5\u00fe \u00cb!W|y-\u0012p\u0081\u0007\u0014\u00a30\u00e4QNM\u00a6i\u00ddx\u00a8b\u00f5\u00f6\u00ed3E\u00b9\n\u0082\u00cf\u00cco\u001eq{\u009e]\u000b\u009a\f84\u00bbJKT\u00f0\u0096\u009fN\b\u00f23-jA\u00a1H\u009b\u00f6\u0000\u00ff\u00c1\u00c9\u00fc#%\u00f6\u00a1\u009d\u00dd&\u008e\u00fe\u00e6\u00cb\u001d\u00b9%\r]\u0096]af\u00f5KSO\u00db\u008d\u008a\u00ba\u00e0S\u0012K\u00ca\u00fe\u008b9T\u00fec\u00f5\u0091\u00d2\u008e\u000bX\t3M\u00a8F\u00cce\u00c9\u000e\u00fd\u0010?\u0090\u00da\u000fT\u008e7\u00a9P\u00c5\u00c3\u00ac\u00f2~$kS\u001eA\u00f6m\u0085\u00c7z\u00a1S\u00e4\u0080Y\u00cf\u0087R\u00e0\u0099,E|\u0012\u00af\u0015\u00a2\u00a7\u008a\u008a\u0085\u00b7\u0003\u0001\u00ff\u001b\u00bc9\u00d0[!\u00b8\u00e5\u000e\u0016\u008d\u00af0H\u0090C\u00ca\u00f1\u00c2\u00c91\u00be\u00a8\u00e5w\u0011q\u00ed|\u00d6E\u00e9\u00c3\u00c7\u0090@\u009c\u00cdnx\u00c0\u0002\u00fe\u00cf(\u0084(3\u008a(%\u00f9f\u00eaa\u00c2\u00a4\u009d\u001c1\u00f0\u00cd\u00d2n\u009c\u00b6\u00ac8\u00fb=r\u00a9\u00f3~8\u00d4`^\u00be\u00e8huRd\u0098\\\u008e\u0010\u00fa5r\u009b\u00b8\u00dc\u0084\u0091YB\u00dfDD\u00ad\u00af#5\u009a\"\u00c7/%K\u0010\u00a7\u00ba\u00a5z2\u0011L\u00ea\"]\u00f85t_\u00b9\u00f3\u0099\u00cf\u0007W\u00feh\u00c5\u00f5\u00b0\u00a8`0\u00dcj\u00b5,{\u00ff\u0004\u00bc\t8\u00fe\u00cf\u009c\u000b\r(T\u00e1\u009e\u00e0\t\u00c0\u0097\u009az<|\u001c\u00e3\u00f5Q\u0083\u00e3V\u0010\u00a5\u00d9\u00cf\u00f1\u00a5\u00d0<\"\u00f3\u0011\u0003\u0096\u00aa!\u00b4\u008ep\u00b2R\u0091\u00a9\u00a4N\u0002h";
                var5_15 = 608;
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
                    var4_14 = "\u00ee'\u001b.f]5\u001d}p\u009f\n\u00b8\u00f8\u00e4\u00b1";
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
        Aura.lb = var6_12;
        Aura.mb = new Integer[78];
        var44_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Aura.class, "attackRange", "getAttackRange()F", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "ignoreWalls", "getIgnoreWalls()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "fov", "getFov()I", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "rotationMode", "getRotationMode()Lsu/catlean/g9;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "priorityManual", "getPriorityManual()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "switchMode", "getSwitchMode()Lsu/catlean/a0;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "onlyWeapon", "getOnlyWeapon()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "oldDelay", "getOldDelay()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "minCps", "getMinCps()I", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "maxCps", "getMaxCps()I", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "shieldBreaker", "getShieldBreaker()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "splashWhileOnGround", "getSplashWhileOnGround()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "backTrack", "getBackTrack()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "moveDelay", "getMoveDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "attackChance", "getAttackChance()I", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "pauseWhileEating", "getPauseWhileEating()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "pauseInInventory", "getPauseInInventory()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "whileShielding", "getWhileShielding()Lsu/catlean/mt;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "deathDisable", "getDeathDisable()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "overrideCrosshair", "getOverrideCrosshair()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "rotateOnDisable", "getRotateOnDisable()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "onHitSwap", "getOnHitSwap()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "randomizeDelay", "getRandomizeDelay()Lsu/catlean/nl;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "sprintMode", "getSprintMode()Lsu/catlean/lb;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "esp", "getEsp()Lsu/catlean/s9;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "sort", "getSort()Lsu/catlean/l8;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "targets", "getTargets()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "antibot", "getAntibot()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "players", "getPlayers()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "mobs", "getMobs()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "animals", "getAnimals()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "villagers", "getVillagers()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "hostiles", "getHostiles()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "onlyAngry", "getOnlyAngry()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "projectiles", "getProjectiles()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "ignoreInvisible", "getIgnoreInvisible()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "ignoreNamed", "getIgnoreNamed()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "ignoreTeam", "getIgnoreTeam()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "ignoreCreative", "getIgnoreCreative()Z", 0)), Reflection.property1(new PropertyReference1Impl(Aura.class, "ignoreNaked", "getIgnoreNaked()Z", 0))};
        Aura.delegatedProperties = var44_20;
        Aura.INSTANCE = new Aura();
        Aura.attackRange$delegate = SettingFactory.floatSetting$default(Aura.INSTANCE, "range", 3.0f, RangesKt.rangeTo(3.0f, 6.0f), null, null, 24, null);
        Aura.ignoreWalls$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "ignore-walls", true, null, null, 12, null);
        var45_21 = "trigger";
        var46_22 = 1;
        var47_23 = SettingFactory.intSetting$default(Aura.INSTANCE, "fov", 180, new IntRange(1, 180), null, null, 24, null);
        Aura.fov$delegate = SettingOverrides.withNameValOverride(var47_23, var46_22, var45_21);
        Aura.rotationMode$delegate = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "rotation-mode", (Enum)AuraRotationMode.DEFAULT, null, null, 12, null);
        Aura.priorityManual$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "priority-manual", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Aura.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "auto-weapon", (Enum)AuraSwitchMode.NONE, null, null, 12, null);
        Aura.onlyWeapon$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "only-weapon", false, null, null, 12, null);
        Aura.oldDelay$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "old-delay", false, null, null, 12, null);
        Aura.minCps = SettingFactory.intSetting$default(Aura.INSTANCE, "min-cps", 9, new IntRange(1, 20), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        Aura.maxCps = SettingFactory.intSetting$default(Aura.INSTANCE, "max-cps", 11, new IntRange(1, 20), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        Aura.shieldBreaker = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "shield-breaker", true, null, null, 12, null);
        Aura.splashWhileOnGround = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "splash-while-on-ground", false, null, null, 12, null);
        Aura.backTrack$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "back-track", false, null, null, 12, null);
        Aura.moveDelay$delegate = SettingFactory.intSetting$default(Aura.INSTANCE, "move-delay", 250, new IntRange(0, 1000), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        Aura.attackChance$delegate = SettingFactory.intSetting$default(Aura.INSTANCE, "attack-chance", 100, new IntRange(50, 100), null, null, 24, null);
        Aura.pauseWhileEating$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "pause-while-eating", false, null, null, 12, null);
        Aura.pauseInInventory$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "pause-in-inventory", true, null, null, 12, null);
        Aura.whileShielding = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "while-shielding", (Enum)AuraWhileShieldingMode.UNPRESS, null, null, 12, null);
        Aura.deathDisable$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "disable-on-death", true, null, null, 12, null);
        Aura.overrideCrosshair$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "override-crosshair", true, null, null, 12, null);
        Aura.rotateOnDisable$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "rotate-on-disable", false, null, null, 12, null);
        Aura.onHitSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "offhand-hit-swap", false, null, null, 12, null);
        Aura.randomizeDelay = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "randomize-delay", (Enum)AuraRandomizeDelayMode.OFF, null, null, 12, null);
        Aura.sprintMode$delegate = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "sprint", (Enum)AuraSprintMode.NORMAL, null, null, 12, null);
        Aura.esp$delegate = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "esp", (Enum)AuraEspMode.COMETS, null, null, 12, null);
        Aura.sort$delegate = SettingFactory.modeSetting$default((SettingContainer)Aura.INSTANCE, "sort", (Enum)AuraSortMode.Distance, null, null, 12, null);
        Aura.targets$delegate = SettingFactory.settingGroup$default(Aura.INSTANCE, "targets", AuraTargetsMode.BOOLS, null, 4, null);
        Aura.antibot = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "antibot", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.players = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "players", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.mobs = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "mobs", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.animals = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "animals", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.villagers = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "villagers", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.hostiles = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "hostiles", true, Aura.INSTANCE.getTargets(), null, 8, null);
        var51_24 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)();
        var52_25 = Aura.INSTANCE.getTargets();
        var53_26 = true;
        var54_27 = "only-angry-hostiles";
        var55_28 = Aura.INSTANCE;
        Aura.onlyAngryHostiles = SettingFactory.booleanSetting(var55_28, var54_27, var53_26, var52_25, var51_24);
        Aura.projectiles$delegate = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "projectiles", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.ignoreInvisibleEntities = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "ignore-invisible-entities", false, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.ignoreNamed = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "ignore-named", false, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.ignoreTeam = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "ignore-team", false, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.ignoreCreative = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "ignore-creative", true, Aura.INSTANCE.getTargets(), null, 8, null);
        Aura.ignoreNaked = SettingFactory.booleanSetting$default((SettingContainer)Aura.INSTANCE, "ignore-naked", false, Aura.INSTANCE.getTargets(), null, 8, null);
        var44_20 = new RotationHandler[]{DefaultRotationHandler.INSTANCE, AuraRotationHandler.INSTANCE, ManagedRotationHandler.INSTANCE, AdvancedRotationHandler.INSTANCE, AuraSwitchHandler.INSTANCE};
        Aura.targets = CollectionsKt.listOf(var44_20);
        Aura.savedRotation = new Rotation(0.0f, 0.0f, false, null, 12, null);
    }

    public static void zkmSetFlag(String string) {
        J = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return J;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6893;
        if (gb[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])hb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    hb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/um", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = fb[n2].getBytes("ISO-8859-1");
            Aura.gb[n2] = Aura.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Aura.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/um" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x8F7;
        if (mb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = lb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])nb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    nb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/um", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Aura.mb[n2] = n3;
        }
        return mb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Aura.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/um" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Aura.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Aura.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

