/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector3f
 */
package su.catlean.module.rage;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PostSyncEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.FireWorkVectorEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.ElytraFreeze;
import su.catlean.module.rage.ElytraTarget$EntriesMappings;
import su.catlean.module.rage.ElytraTarget$onPostSync$1;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.Notifications;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.SlotResult;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.DefaultRotationHandler;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class ElytraTarget
extends Module {
    @NotNull
    public static final ElytraTarget INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting predict$delegate;
    @NotNull
    private static final BooleanSetting alwaysPredict$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final BooleanSetting offHandFirework$delegate;
    @NotNull
    private static final BooleanSetting bravoHvhSwap$delegate;
    @NotNull
    private static final IntSetting minSpeed$delegate;
    @NotNull
    private static final BooleanSetting pauseWhileEating$delegate;
    @NotNull
    private static final BooleanSetting peekAssist$delegate;
    @NotNull
    private static final BooleanSetting fakeLag$delegate;
    @NotNull
    private static final BooleanSetting onlyGroundPeek$delegate;
    @NotNull
    private static final BooleanSetting boost$delegate;
    @NotNull
    private static final BooleanSetting customSpeed$delegate;
    @NotNull
    private static final FloatSetting degrees0$delegate;
    @NotNull
    private static final FloatSetting degrees5$delegate;
    @NotNull
    private static final FloatSetting degrees10$delegate;
    @NotNull
    private static final FloatSetting degrees15$delegate;
    @NotNull
    private static final FloatSetting degrees20$delegate;
    @NotNull
    private static final FloatSetting degrees25$delegate;
    @NotNull
    private static final FloatSetting degrees30$delegate;
    @NotNull
    private static final FloatSetting degrees35$delegate;
    @NotNull
    private static final FloatSetting degrees40$delegate;
    @NotNull
    private static final FloatSetting degrees45$delegate;
    @NotNull
    private static final FloatSetting degrees50$delegate;
    @NotNull
    private static final FloatSetting degrees55$delegate;
    @NotNull
    private static final FloatSetting degrees60$delegate;
    @NotNull
    private static final FloatSetting degrees65$delegate;
    @NotNull
    private static final FloatSetting degrees70$delegate;
    @NotNull
    private static final FloatSetting degrees75$delegate;
    @NotNull
    private static final FloatSetting degrees80$delegate;
    @NotNull
    private static final FloatSetting degrees85$delegate;
    @NotNull
    private static final FloatSetting degrees90$delegate;
    @NotNull
    private static final TickTimer glideTimer;
    @NotNull
    private static final TickTimer V;
    @NotNull
    private static final TickTimer F;
    @NotNull
    private static final TickTimer predictTimer;
    private static boolean D;
    @Nullable
    private static Rotation targetRotation;
    @Nullable
    private static Direction lastDirection;
    private static boolean y;
    @Nullable
    private static Box currentBox;
    @Nullable
    private static Box prevBox;
    @Nullable
    private static Vec3d predictedPos;
    @NotNull
    private static final HashMap h;
    @NotNull
    private static HashMap speedMap;
    private static int[] z;
    private static final long ab;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] lb;
    private static final Integer[] mb;
    private static final Map nb;

    private ElytraTarget() {
        super("elytra-target", Categories.getRage(), null, null);
    }

    private final float getPredict() {
        return ((Number)predict$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final boolean getAlwaysPredict() {
        return (Boolean)alwaysPredict$delegate.getValue(this, delegatedProperties[1]);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final boolean getOffHandFirework() {
        return (Boolean)offHandFirework$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getBravoHvhSwap() {
        return (Boolean)bravoHvhSwap$delegate.getValue(this, delegatedProperties[4]);
    }

    private final int getMinSpeed() {
        return ((Number)minSpeed$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final boolean getPauseWhileEating() {
        return (Boolean)pauseWhileEating$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getPeekAssist() {
        return (Boolean)peekAssist$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getFakeLag() {
        return (Boolean)fakeLag$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getOnlyGroundPeek() {
        return (Boolean)onlyGroundPeek$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getBoost() {
        return (Boolean)boost$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getCustomSpeed() {
        return (Boolean)customSpeed$delegate.getValue(this, delegatedProperties[11]);
    }

    private final float getDegrees0() {
        return ((Number)degrees0$delegate.getValue(this, delegatedProperties[12])).floatValue();
    }

    private final float getDegrees5() {
        return ((Number)degrees5$delegate.getValue(this, delegatedProperties[13])).floatValue();
    }

    private final float getDegrees10() {
        return ((Number)degrees10$delegate.getValue(this, delegatedProperties[14])).floatValue();
    }

    private final float getDegrees15() {
        return ((Number)degrees15$delegate.getValue(this, delegatedProperties[15])).floatValue();
    }

    private final float getDegrees20() {
        return ((Number)degrees20$delegate.getValue(this, delegatedProperties[16])).floatValue();
    }

    private final float getDegrees25() {
        return ((Number)degrees25$delegate.getValue(this, delegatedProperties[17])).floatValue();
    }

    private final float getDegrees30() {
        return ((Number)degrees30$delegate.getValue(this, delegatedProperties[18])).floatValue();
    }

    private final float getDegrees35() {
        return ((Number)degrees35$delegate.getValue(this, delegatedProperties[19])).floatValue();
    }

    private final float getDegrees40() {
        return ((Number)degrees40$delegate.getValue(this, delegatedProperties[20])).floatValue();
    }

    private final float getDegrees45() {
        return ((Number)degrees45$delegate.getValue(this, delegatedProperties[21])).floatValue();
    }

    private final float getDegrees50() {
        return ((Number)degrees50$delegate.getValue(this, delegatedProperties[22])).floatValue();
    }

    private final float getDegrees55() {
        return ((Number)degrees55$delegate.getValue(this, delegatedProperties[23])).floatValue();
    }

    private final float getDegrees60() {
        return ((Number)degrees60$delegate.getValue(this, delegatedProperties[24])).floatValue();
    }

    private final float getDegrees65() {
        return ((Number)degrees65$delegate.getValue(this, delegatedProperties[25])).floatValue();
    }

    private final float getDegrees70() {
        return ((Number)degrees70$delegate.getValue(this, delegatedProperties[26])).floatValue();
    }

    private final float getDegrees75() {
        return ((Number)degrees75$delegate.getValue(this, delegatedProperties[27])).floatValue();
    }

    private final float getDegrees80() {
        return ((Number)degrees80$delegate.getValue(this, delegatedProperties[28])).floatValue();
    }

    private final float getDegrees85() {
        return ((Number)degrees85$delegate.getValue(this, delegatedProperties[29])).floatValue();
    }

    private final float getDegrees90() {
        return ((Number)degrees90$delegate.getValue(this, delegatedProperties[30])).floatValue();
    }

    @NotNull
    public final HashMap Pd() {
        return h;
    }

    @Override
    public void onDisable(long l) {
        long l2 = l ^ 0x34BEE50EF2DAL;
        if (y && Blink.INSTANCE.getEnabled()) {
            Notifications.INSTANCE.t(true);
            Blink.INSTANCE.disable(l2);
            Notifications.INSTANCE.t(false);
        }
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onPostSync(PostSyncEvent var1_1) {
        block6: {
            v0 = var2_2 = ElytraTarget.ab ^ 37793692909192L;
            v1 = v0 ^ 49286642860613L;
            var10_3 = (int)(v1 >>> 48);
            var11_4 = v1 << 16 >>> 16;
            var13_5 = (int)((v0 ^ 58633491097084L) >>> 48);
            var22_6 = v0 ^ 100763335646174L;
            if (this.getFakeLag()) {
                Blink.INSTANCE.applyDefaults();
            }
            if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA) || !Globals.getPlayer().isGliding() || Aura.INSTANCE.getTarget() == null || !(Aura.INSTANCE.getTarget() instanceof PlayerEntity)) ** GOTO lbl-1000
            if (!this.getOnlyGroundPeek()) ** GOTO lbl-1000
            v2 = Aura.INSTANCE.getTarget();
            Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
            if (!((PlayerEntity)v2).isGliding()) lbl-1000:
            // 2 sources

            {
                v3 = true;
            } else lbl-1000:
            // 2 sources

            {
                v3 = var31_7 = false;
            }
            if (!var31_7 && Blink.INSTANCE.getEnabled()) {
                Notifications.INSTANCE.t(true);
                Blink.INSTANCE.disable(var22_6);
                ElytraTarget.y = false;
                Notifications.INSTANCE.t(false);
            }
            if (DefaultRotationHandler.INSTANCE.y() == DefaultRotationHandler.INSTANCE.j() && this.getPeekAssist() && !ElytraFreeze.INSTANCE.getEnabled() && var31_7) {
                ElytraTarget.D = true;
                ElytraTarget.targetRotation = this.findOpenDirection();
                8688029662732654281L ^ var2_2;
                ElytraTarget.V.reset();
                var32_8 = Async.INSTANCE;
                var33_9 = 325;
                var32_8.getExecutor().execute(new ElytraTarget$onPostSync$1(var33_9));
            }
            if (!ElytraTarget.F.hasPassed((short)var13_5, 5) || Globals.getPlayer().isOnGround()) break block6;
            var32_8 = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
            if (InventoryUtil.INSTANCE.findInInventory((Item[])var32_8, (char)var10_3, var11_4).isFound() && Globals.getPlayer().checkGliding()) {
                ElytraSwap.INSTANCE.K(true);
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                ElytraTarget.F.reset();
            }
        }
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block2: {
            int n;
            long l;
            long l2 = l = ab ^ 0x288921C0A8CAL;
            int n2 = (int)((l2 ^ 0x3F850A0BF3BEL) >>> 48);
            long l3 = l2 ^ 0x2B0470713E59L;
            if (D && targetRotation != null) {
                Rotation rotation = targetRotation;
                Intrinsics.checkNotNull(rotation);
                RotationManager.INSTANCE.rotate(rotation, 200);
            }
            if (this.getPauseWhileEating() && Globals.getPlayer().isUsingItem() && (Globals.getPlayer().getMainHandStack().getComponents().contains(DataComponentTypes.FOOD) || Globals.getPlayer().getMainHandStack().getComponents().contains(DataComponentTypes.POTION_CONTENTS))) {
                return;
            }
            if (!Globals.getPlayer().isGliding() || !glideTimer.hasPassed((short)n2, n = this.getDelay()) || !(MovementUtil.INSTANCE.getSpeed() * 72.0f < (float)this.getMinSpeed())) break block2;
            this.findFirework(this.getOffHandFirework(), l3, this.getBravoHvhSwap());
            glideTimer.reset();
        }
    }

    @Flow
    private final void onFireWorkVelocity(FireWorkVelocityEvent fireWorkVelocityEvent) {
        float f;
        Vec3d vec3d;
        String string = ElytraTarget$onPostSync$1.zkmGetFlag();
        fireWorkVelocityEvent.cancel();
        String string2 = string;
        Vec3d vec3d2 = Globals.getPlayer().getRotationVector();
        Intrinsics.checkNotNullExpressionValue(vec3d2, "getLookAngle(...)");
        if (FireWorkVectorEvent.INSTANCE.call(vec3d2)) {
            vec3d = FireWorkVectorEvent.INSTANCE.getVector();
        } else {
            Vec3d vec3d3 = Globals.getPlayer().getRotationVector();
            vec3d = vec3d3;
            Intrinsics.checkNotNullExpressionValue(vec3d3, "getLookAngle(...)");
        }
        Vec3d vec3d4 = vec3d;
        if (this.getCustomSpeed()) {
            this.buildSpeedMap();
        }
        float f2 = Math.abs((float)Math.toDegrees(-Math.atan2(vec3d4.y, Math.hypot(vec3d4.x, vec3d4.z))));
        if (Blink.INSTANCE.getEnabled() || !this.getBoost()) {
            f = 0.1f;
        } else {
            Object v5;
            Iterable iterable = ((Map)(this.getCustomSpeed() ? speedMap : h)).entrySet();
            Iterator iterator2 = iterable.iterator();
            if (!iterator2.hasNext()) {
                v5 = null;
            } else {
                Object t = iterator2.next();
                if (!iterator2.hasNext()) {
                    v5 = t;
                } else {
                    Map.Entry entry = (Map.Entry)t;
                    float f3 = Math.abs(f2 - ((Number)entry.getKey()).floatValue());
                    do {
                        Object t2;
                        Map.Entry entry2;
                        float f4;
                        if (Float.compare(f3, f4 = Math.abs(f2 - ((Number)(entry2 = (Map.Entry)(t2 = iterator2.next())).getKey()).floatValue())) <= 0) continue;
                        t = t2;
                        f3 = f4;
                    } while (iterator2.hasNext());
                    v5 = t;
                }
            }
            Map.Entry entry = v5;
            f = entry != null ? ((Number)entry.getValue()).floatValue() : 0.1f;
        }
        float f5 = f;
        Vec3d vec3d5 = Globals.getPlayer().getVelocity();
        Intrinsics.checkNotNullExpressionValue(vec3d5, "getDeltaMovement(...)");
        Vec3d vec3d6 = vec3d5;
        fireWorkVelocityEvent.setVelocity(vec3d6.add(vec3d4.x * (double)f5 + (vec3d4.x * 1.5 - vec3d6.x) * 0.5, vec3d4.y * (double)f5 + (vec3d4.y * 1.5 - vec3d6.y) * 0.5, vec3d4.z * (double)f5 + (vec3d4.z * 1.5 - vec3d6.z) * 0.5));
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        if (prevBox != null && currentBox != null && Aura.INSTANCE.getTarget() != null && Aura.INSTANCE.getTarget() instanceof PlayerEntity) {
            Entity entity = Aura.INSTANCE.getTarget();
            Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
            if (((PlayerEntity)entity).isGliding() && !(this.getPredict() == 0.0f)) {
                Box box = prevBox;
                Intrinsics.checkNotNull(box);
                Box box2 = currentBox;
                Intrinsics.checkNotNull(box2);
                Box box3 = RenderState.lerpBox$default(RenderState.INSTANCE, box, box2, 0.0f, null);
                Object var20_3 = null;
                Color color = null;
                Color color2 = Theme.INSTANCE.getGradientColor(0);
                Box box4 = box3;
                RenderState renderState = RenderState.INSTANCE;
                RenderState.addFilledBox$default(renderState, box4, color2, color, var20_3);
                Object var28_8 = null;
                Color color3 = null;
                Color color4 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(0), 1.0f);
                Box box5 = box3;
                RenderState.addOutlineBox$default(RenderState.INSTANCE, box5, color4, color3, var28_8);
            }
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block1: {
            if (!(receivePacket.getPacket() instanceof EntityAnimationS2CPacket) || Aura.INSTANCE.getTarget() == null) break block1;
            Packet<?> packet = receivePacket.getPacket();
            int n = ((EntityAnimationS2CPacket)packet).getEntityId();
            Entity entity = Aura.INSTANCE.getTarget();
            Intrinsics.checkNotNull(entity);
            if (n == entity.getId() && (((EntityAnimationS2CPacket)packet).getAnimationId() == 0 || ((EntityAnimationS2CPacket)packet).getAnimationId() == 3)) {
                predictTimer.reset();
            }
        }
    }

    public final void findFirework(boolean bl, long l, boolean bl2) {
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x67C6C397AF96L;
        int n = (int)(l3 >>> 48);
        long l4 = l3 << 16 >>> 16;
        long l5 = l2 ^ 0x7A8B615547F9L;
        Object object = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
        SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar((Item[])object);
        String string = ElytraTarget$onPostSync$1.zkmGetFlag();
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
        object = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n, l4);
        String string2 = string;
        if (!bl || Blink.INSTANCE.getEnabled()) {
            InventoryUtil.INSTANCE.saveSelectedSlot();
            if (slotResult.isFound()) {
                slotResult.select();
            } else if (((SlotResult)object).isFound()) {
                InteractionUtil.clickSlot(((SlotResult)object).getSlot(), Globals.getPlayer().getInventory().getSelectedSlot() % 8 + 1, SlotActionType.SWAP, true);
                InventoryUtil.INSTANCE.selectSlot(Globals.getPlayer().getInventory().getSelectedSlot() % 8 + 1);
            } else if (!Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET)) {
                this.disableWithMessage(ModuleMessages.i18n(this, "no-fireworks", new Object[0], true), l5);
                return;
            }
            InteractionUtil.useItem$default(Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET) ? Hand.OFF_HAND : Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            InventoryUtil.INSTANCE.restoreSelectedSlot();
            if (!slotResult.isFound() && ((SlotResult)object).isFound()) {
                InteractionUtil.clickSlot(((SlotResult)object).getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
            }
        } else if (!Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET)) {
            if (((SlotResult)object).isFound()) {
                GuiMove.INSTANCE.setMacroActive(true);
                if (bl2) {
                    InteractionUtil.clickSlot$default(((SlotResult)object).getSlot(), 0, null, false, 14, null);
                    InteractionUtil.clickSlot$default(45, 0, null, false, 14, null);
                    InteractionUtil.clickSlot$default(((SlotResult)object).getSlot(), 0, null, false, 14, null);
                } else {
                    InteractionUtil.clickSlot$default(((SlotResult)object).getSlot(), 40, SlotActionType.SWAP, false, 8, null);
                }
                InteractionUtil.useItem$default(Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET) ? Hand.OFF_HAND : Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
                InteractionUtil.clickSlot(((SlotResult)object).getSlot(), 40, SlotActionType.SWAP, true);
                GuiMove.INSTANCE.setMacroActive(false);
            } else {
                this.disableWithMessage(ModuleMessages.i18n(this, "no-fireworks", new Object[0], true), l5);
            }
        } else {
            InteractionUtil.useItem$default(Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FIREWORK_ROCKET) ? Hand.OFF_HAND : Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        }
    }

    public final void setTargetBox(@NotNull Box box) {
        Intrinsics.checkNotNullParameter(box, "box");
        prevBox = currentBox;
        currentBox = box;
    }

    private final Rotation findOpenDirection() {
        Iterator iterator2 = ElytraTarget$EntriesMappings.enumEntries.iterator();
        String string = ElytraTarget$onPostSync$1.zkmGetFlag();
        while (iterator2.hasNext()) {
            BlockHitResult blockHitResult;
            BlockHitResult blockHitResult2;
            Direction direction = (Direction)iterator2.next();
            Vector3f vector3f = direction.getUnitVector().mul(14.0f);
            Rotation rotation = PlaceManager.INSTANCE.getYawForDirection(direction);
            if (direction == lastDirection) continue;
            Entity entity = Globals.getPlayer();
            Entity entity2 = Globals.getPlayer();
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().raycast(new RaycastContext(this.getPos(entity), this.getPos(entity2).add(vector3f.x, vector3f.y, vector3f.z), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer())), "clip(...)");
            Entity entity3 = Globals.getPlayer();
            Entity entity4 = Globals.getPlayer();
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().raycast(new RaycastContext(this.getPos(entity3).add(0.0, Globals.getPlayer().getBoundingBox().getLengthY(), 0.0), this.getPos(entity4).add(vector3f.x, (double)vector3f.y + Globals.getPlayer().getBoundingBox().getLengthY(), vector3f.z), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer())), "clip(...)");
            if (blockHitResult2.getType() != HitResult.Type.MISS || blockHitResult.getType() != HitResult.Type.MISS) continue;
            lastDirection = direction;
            targetRotation = rotation;
            return rotation;
        }
        return new Rotation(MovementUtil.INSTANCE.getLastYaw(), -88.0f, false, null, 12, null);
    }

    private final void buildSpeedMap() {
        Pair[] pairArray = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(this.getDegrees0())), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(this.getDegrees5())), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(this.getDegrees10())), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(this.getDegrees15())), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(this.getDegrees20())), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(this.getDegrees25())), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(this.getDegrees30())), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(this.getDegrees35())), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(this.getDegrees40())), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(this.getDegrees45())), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(this.getDegrees50())), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(this.getDegrees55())), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(this.getDegrees60())), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(this.getDegrees65())), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(this.getDegrees70())), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(this.getDegrees75())), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(this.getDegrees80())), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(this.getDegrees85())), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(this.getDegrees90()))};
        speedMap = MapsKt.hashMapOf(pairArray);
    }

    public final float getTargetSpeed(long l) {
        int n = (int)(((l = ab ^ l) ^ 0x4639A28F56A2L) >>> 48);
        Entity entity = Aura.INSTANCE.getTarget();
        if (entity == null) {
            return 0.0f;
        }
        Entity entity2 = entity;
        if (predictedPos == null) {
            predictedPos = this.getPos(entity2);
        } else {
            Vec3d vec3d = predictedPos;
            Intrinsics.checkNotNull(vec3d);
            if (vec3d.distanceTo(this.getPos(entity2)) < (double)0.1f) {
                predictedPos = this.getPos(entity2);
                return 0.0f;
            }
        }
        return predictTimer.hasPassed((short)n, 15) || this.getAlwaysPredict() ? this.getPredict() : 0.0f;
    }

    @NotNull
    public final Vec3d getMotion(@NotNull Entity entity) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter(entity, (String)((Object)ElytraTarget.zkmBootstrap("o", (int)21430, (long)(0x65A285B6DCA2F46EL ^ l))));
        return new Vec3d(entity.getX() - entity.lastX, entity.getY() - entity.lastY, entity.getZ() - entity.lastZ);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getOffHandFirework();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getPeekAssist();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getPeekAssist();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getBoost();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible19() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible20() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible21() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible22() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    private static final boolean settingVisible23() {
        return INSTANCE.getBoost() && INSTANCE.getCustomSpeed();
    }

    public static final void l(boolean bl) {
        D = bl;
    }

    public static final void m(boolean bl) {
        y = bl;
    }

    public static final boolean access$getFakeLag(ElytraTarget elytraTarget) {
        return elytraTarget.getFakeLag();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ElytraTarget.ab = ZkmKeys.a(273337662489177000L, 6123423811914970578L, MethodHandles.lookup().lookupClass()).a(11284962973997L);
                        var20 = ElytraTarget.ab ^ 72764509717223L;
                        ElytraTarget.hb = new HashMap<K, V>(13);
                        ElytraTarget.zkmSetFlag(new int[3]);
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
                        var18_3 = new String[107];
                        var16_4 = 0;
                        var15_5 = "r\u008c\u0093\u00a6\u00a0\u00fe\u00edG]\u00f4\u00f6\u00eb+\u0006t\u0086YT\u00dc\u00bdY\u001c',\u00df{,\u00b5}/C\u00e7 \u00f8!R\u00d8\u00e7\u00a4\u00b5~\u00ef\u0000\u000e4y_\u00a4A\u0011\u00c0\u00dd\u000b2\u0015\u00b5o\u00da\f\u00d1\u00bf\u00c8\u00d6\u0004\u00d9 \u00d8\u0097\u000f\u0012*j\u00bb\u00be\u000b\u0083w\u00036v3\u0083o\u0082V\u00ac\u0001\u00e6C\u00a3&\u00e8\u00ff\u001dE\u00bc\u00a6\u00f3 A\u00b133-\u00f1D\u001aGrh\u00c0\u008e\u008e\u001b\u0015\u00119\u00f1\u00c7\u00bf2I\u00c3\u00da\u0084^)\u0088\u00ee\u00e7A ST\u00d7u\u0099\u00cb\u00e7\u00ed^\u00fdKhZR\"E\u00ed\u00ac\u008a$1\u00e8;\u00f4\t\u00f4\u00d4m\u00ac!\u009c\u00c1 \u00a3V\u0092\u00b1}\u00de\u00a0\u00fd\u00b1\u00d8\u008a\u00d4A\u0081\u00e4\u00a9\u001e\tBD\u0019C\u008d\u00bbA\u00803\u00ff1\u00e9\u0017\u0016\u0018\u00a0\u00ae>8#\u00e2\u00e7\u00fe\u00f8\u0081JU\u0096gO\u0095\u009d\u0004\u009b\u0004\u009b\u00e6\u00dd\u0006\u00185\u008d\u0011K\u00e0\b\u00e6\u00a2\u008f\u00e4HcwG\u0004\u00f8\u00819\u009f\u0097\u00ed\u0096\u00eb\u0003 \u00c9#\u00e8\u00a1\u00ad\u00b6XH\u0081\u00c9\u009e\u00ec\u009a?_\u00a1\u00a1\u00eaw \u00f4\u009d\u00a5S\u00aaka\u008f~\u00a4\u001e\u0019 \u0017p(\u00der\u0004A\u0006\u00f0\u00d7$9F\u0007\u00ec\u00e8~U\u00f2G\u00c2\u00bd\u00cb\u008f\u00c6l\u008c)\u0011\u0006\u00c6H \u0088\u00fe\u009bpKB\u0003\u00cf\u0019\u0082\u00c6\u009b\u0090\u00969\u0012\u00a1srI\u00a2\u0015\u00ea-h\u00bf\u009d\u0010\u00f1\u00f9v\u0003 \u00f0%\u0010\u0099\u00a2o\u00d8\u00f7>\u00c4>8wQ{\u009c-\u009c\u00b8\u00c7\u00a0\u0089\u00f8\u000e\u0083\u00b2\u00f0\u0089\u00cf\u00dd\r\u00b1 cy\u0012\u00a2\u0006U\u00c3\u0096 j\u000eu\u00c8mAUh\u00e4_\u00a2\u0090\u007f\u008b\u001eq\u00b5\u0097\u0016\u0013\u0099\u00e4\u0094(M\u00c2~\u00cc\u00bd\u00d79\u008b\u00eb\u00d1\u00f7\u0085\u0086\u00cb6(X\u00b1\u00c3K+>\u0019zvw\u009d\u0007\u00bc\u00c4\u00a6X\u00d8\u00c7\u0006B]\"\u00b9V\u0018\u00ee\u0007\u00d3x<\u00e0\bT\u00e1d\u0090\u00be\u001a@\u00d0\u0019\u00feAhs`\u00bb\u00dc\u00d0(\u0088\u0099\u0097\u001d\u00a1\u0005\u0080\u00c9\u00f6\u0002\u00b1*\u001e\u0018V\u00d7\"x\u009e\u00ed\u00f2\u00fcx=be\u00a7U\u0093w\u001es\u00b1jM\u00e7\u00e8]\u00b6n(\u00bd\u0017.\u00a5\u00f4\u00ba\u0019\u00e1\u0094\u00ab\u00a2\u0081E\u00a9\r%x\u0097\u00a8\u00e2\u000b\u00e5\u00ec\u0005\u00bd^\u0014\u00cb\u00b1\u00f9(\u00cb\u00ae\u00d0\u008c\u00956\u00b2c\u00af \u00d0\u00ae.\u0015&\u00f1\u0097\u00b7\u0086\u00a1u\u00c3z\u00c4\u00de\u00cb\u007f\u0005M\u0016e*\u0080l\u0087G\u00f6\u0012\u00a6\u000e\u009c\u00fe \u00fc*\u009a^\u00a4\u0086\u00ed\u00e9\u00d9b\"\u0014\u0007L\u0014\u00e6n\u00ba\u00bb'\u00e0}\u00c2\u001b\u0017\u001e6J\u0093bH/\u0018\u00d1kc\u00b0\u00c7\u00ac\u00ec\u0011\u00ea9\u0000\u0000 \u0093A\u000e/\u009b\u0017\u001b\u00a8\u00b8X\u00d5 \u00a7\u009cR\u008ff\u00c6\u0006\u007f\u0002\u00b4@\u009f\u008bE\u00963\u00d8\u00eb\u0088C\u00b0T\u00a7\u00cf\u00c1\u00ca8\rcq)\u00e5\u0018\u00fa\u009f\u00a5*)\u0095\u00b5b\u00e7a\u00dc\u00da=\u00c1\u0091z\u00ce\u0099\u00a3\u00f2\u0087\u0080?\u00b4\u0010\u009a!\u00dc\u00f0\t\u00bd\u0084\u00f5\nP8\u0004\u009eA\u00d4\u00b5 \u009d6\u00b0\u00a6a\u00e7\u0007\u008b\u0088\u00b2\u0093yaUG\u001b\n\u001f\u00be\u00a6\u00fcw\u001f\u00e2\u0083H]D\u00e6\u009df\u0082\u0010\u00b8+\u00e7\u00e4\u00a6\u008ewA\u00f9\u001f\u001d\u00ed\u000b\u00b2\u0004\u009a %\u000b\u00c6[o\u008a\u0003Y=\u0011\u00e1\u00bc\u0089\u00b8*\u0019\u00cb\u0083z|\u0013\t\u00ba\u00de\u00de\u00a0\u00f3h\u00d1\u00c7\u00dcA\u0018\u001e\u00be\u00e7\u00f3r\u009a\u009b?l\u00beNn.\u00ae\u0083\u00f79\u00ab\u00c4\u00f1\u00eex\u0099\u0013\u0010%\u00e3\u0098\u00e0\u000e\u00ff|\u0086\u00de\u00cf \u001c\u0000\u00a7\u00fd\u001a\u0018\u00aa`\u0004\u0093h\"\u0014\u009a\u000e\u0098\u009a\u00d8Z\u00c1\u0094\f\u00e3\u00b3\u0092I(\u00eb\u00c9\u0013\u0018\u009e3m\u00ed\u0003\u008f\u000fr\u0007\u001aI\u00d0{\u0015\u00d1\u008a\u00d2/\u00ad6\u009f\u0014\u00e6\u0004\u0018\u00a9\u00eb\u00ffzj'<\u008d\u008b\u00a6\u0001\u0004\u00b3\u00f6\u00a8T\u0096\u00f17\u00f3\u008d\u0083P](\u00e2e\u00af9\u00bf\u008a\u00c9L\u00e4\u0095\f`\u00cd\u00daV\u00f0~\u009aA\u00c61\u00c0\u00f4\u00dd`\u0082\u0006\u0006_hE\u00a3o\u00e3$\u00b9V\u00baYC\u0018k\u00e2d\u00f0\u00f9\u00ecK\u00a3\u000b\u00af\u00d9\u00a5T,\u000f\u00b8\u0086\u00df\u00b2\u0011\u00d5m\u00c4> \u00f6X\u00e6\u0014\u000f\u00a03\u00fe\u00f8\u00f5\u00076\b\u00b3\u00b4\u00f8\u0098\u00ec\u008cM(\u0005\u00a7P\u00ac\u00cfW\u0086\u00b8\n\u008eP \u0005k2\u00fa\u0098\u00ee\u0019\u00a0\u00d3\u00fd\u0099B* O\u00b8\u00ce\u00ff\u00d6\u001d\u00d4\u0096\u0088\u00c4;Y\u00d4\u0011R\u008d!'\u0018\u00fa]\u0092~\u00b5\u00be\u00a5\u0087\u00e4\u00b3\u000b\"jL\u00f4\u0098\u00fa\u008b\u00e6\u00b9vl\u00ba\u00fb }L2\u0085\u00a2\u0093\u00e3,AQ\u00f5\u009b]N*A\u00cf\u008a\u0013\u0004\u007f\u00b7\u00c6\u00a5\u00e7u\u00f0\u00b1\u0012\u00cd\t\u00aa \u00d7\u00ac\u00c5I\u00d3\u00de\u00ae\u00ed\u001e\u00e4\u0092\u00f0\u001b\u00a5\u00c3zA7)6`\u00daB\u00f3\u0004\u0097\u00b9\u007f4t\u00e4\u001b {\u00bf\u00e7`n\u0085\u0084\u0013\u0082\u000e\u00f1\u00dd\u00a1z\u008b\u00e2/t\u0096\u0084S\u00f6\u00d7\u00c7s\u0018\u000f/\u00b7Z\u007f6\u0018/\u001f?\u000b!\u00af\u00da\u00e4\u00bd\u00cb\u00aa\u00e5\u00ad\u00cf\u00f8\u00dfBT>U\u00ba\u00c8NU \u009d\u00c9A\u00a8\u001f\u00e9#\u00b8\u00a3\u00cd\u00cbh\u00a7F\u00a3\u00f9\u009c\u0004\u00df2\u00b6\u0093a\u00d3R\u0080=,\u008c[\u00c5J\u0010\n\u00b5\u00ba?\u00dd\u00c1\u00a7\u0016\u00cf\u0084\u001a\u0085\u00f7(\u00f5\u00ea\u0018X\u00b5\u0085\u00a4G\u00a19\u008dL\u00f4\u00c6\u00fa\u00feCA\u00111e\u00922'\u00e7_\u00b7 Q\u00cb\u0098\u007fi\u009dR\u0010\u00cc[\bBb\u00d0!\u00e6\u00f0\u0099\u00c8\u001f6\u00b7\u00bbT\u00bc\u009d\u009f\u00fa\u00a2\u00a9\u00e18\u0018pI\u00c8HU<\u00acA\u001d|\u0095\u00e1@\u0001\u008f\u00ae\u00ae\u00f6\u001e<\u00c2\u00f0\u008e\u00f0(b\u00b4\u00d7\u00cd+\u00df;zWx\u00cb\u0097$\u00ea\u00b2\u00c6}\u0093\u00f7\u00ad-\u00d8/k\u001b\u00bf\u00e3\u00b2\u00b1B\u009f1U\u00d1\u0083h\u00a4\f\u00a2% j/\u00a6\u00ef\u0096Awk\u00caF\u001b\u008d\u00a6,\u00e4\u00a7\u00b5\u00ea\u0090\t\u00c1\u00c8\u00c9\u0005W\u00a7^\u00faA\u00cd\u00a6\u00b8(\u00ea\u009a\u0085n\u00ea2\u00986\u0007\u0094\u00b6\u00d0\u00cd\n\u00d7\u0005\u00a5\u0099~.\u0004\u00e1\u000e\u008a,\u0018>\u00eb0BF~\u0084\u00ed0\u00d1\u00b4\u00b9\u0001q\u0018\u0099y\u00cc\u00ea\u00ad\u0096,z\u00db\b\u000biG\u00f3o\u0001\u00c8\u0080j\u00b6\u00eb|\u00b9\u00e3 \u00a1fZ\u0094\u00ab\u0082Z\u00db\u00b7\u009b?\u009c\u001b9\u00a9n\u0086\u00ec|\u0087\u00c1\u0010IO\u0011)\u00895q\u00ae\u00b1\u00e5\u0018\u0080\u001b\u00ca\u00f0\u00bd\u00edN3\u000f\u00bc\u00b2~F\u0097\u00e5\u00d0\u00eb\u0094N\u00a0\u00fb\u001e\u00c1p\u0018'\u009e\u00f9$a;6/}\u00dcPzQq\u00a9\u00c3\u00849\u007fJ\u0004\u0017\u00d27 I~\u0007\u0083|yuAB\u00a2)E\u00ca\u0092\u00be\u001b\u00f2\u00ec%\u0096\u00b3o\u00ea\u00fb\u00dc\u0000~\u0087\u0093\u00de\u009aU \u0090!\u00aa\u00af\u007f\u00aa\"\u00a5U\u00bc\u000e\u008da\u00aa\u0001\u0091\u0082\u00c5\u00b3\u00fb\t!\u0090\u00dd\bR\u0015\u000f\u00053{\u00dc\u0010Q-\u00ab\u00c2\u009cq{\u0017V\u00c5e\u0086\u00c7\u00e8,\u0007  \u00e1g\u00f6\u00d6W\u0083\u0083\u00b8\u00eeo1\u00c1\u0086lB\u00ddij\u0018\u00ed#\u00a2N8\u0007\u0097\u00caN\u00b8\u00f1^\u0018\u001f\u009e\u00c0H)\u0096i'\u00a4\u008c\u0091\"\u00ec\u0097\\E\u00b1\u0002e\u0086)\u00fa\u00fb\u00db\u0010\u00f6\u00bf\t#\u00f3\u0000q\u00bc\u00d5\u00f3J\u0098\u001aP\u00b9\u0099 7\u00f3\u00aa\u0017\u00bap8\u00c0\u009e\ton]2Vs\u00b6t\u00c43\u00d3L\u00a4!o\u00fcN\u0003Pb\u0099\u000e \u00dco\u00eb\u00de<\u007f\u0084\u008e\u00029\u001d\u0085\u00c1\u00d8P\u00f2:\u0096\u00c8:\u00f4waR\u00dbp\u00cd\u00d2\u00f5\u00de\r\u00b2\u0010\u001a,\u00c1\u00a7/\u00d1`\u00c8\u00ad\t\u0016\u00c4\u00d9~\u00b3\u007f \u00df&B\u00a8\u00b6Zo\u00e8\u0014#\u00dcD\u001f\u0005\u00ea\u008fF\u0006\u00b3}c1\u00f4\u0000\u00952\u00fc\u0015\u00bfG\u00bdw(\r\u00de\u0012\u00d4\u0018\u00bb,\u0011\u00f1\u00af6f\u008aH\u0083\u00ba \u0082\u00bd\u00e0\u00dc\u00d2\u0000A\u00b3\u000b\u00da\u00cao\u00894\u001ex.\u00de\u00d8d\u001eE\u0019\u0018\u00b6\u00ffB\u0006e\u00f88\u00920\u00af1\u00c3\t\u00e5\u00b4^Q\u0010\u00d8,\u00ab\u00b5R\u00ba \u00fbo\b&?\u00a9c\u00bb\u0091/\u0081\u00ed/\u008cq\u00b9\u00dcJ\u00e6\u0090:\u0000]\u0006\u0093\u00d8\u00fd\u00a9\u00fc2\u0012\u00c3 Y6g[)\f\u0014\u00cbT\u001b\t\u001b\u00d5\u00ce\u0019\u0096\u008a\u009b\u00b7C\u0091m\u00a9\u00a3R\u00f2E\u00fe\u00f3n\u008c\u0096\u0018J\u00db@z\u008f0|<\u0001\u00df\u008d\u000f\u0098\u00e0\u00e27\u0089\u0096C\u00c8\u0082\u00a9\u00aa\u0017(\u00ab\u00ba\u00f4\u00a7Z\u00e6\u00c3\u00cf\u00ed\u00ba\u009f\u0000\u00d8\u0000\u00b1\u009e)\u00ccQ\u0011\u0092\u00dd@\u00eeA\u00c0\u0001\u00c0O\u00bc\u0085\u00e7\u0004Q\u0013\u00ceI\u00ea\u0000\u00f9\u0018\u00da\u0007\u00af\u00c2\u008b!\u0085\u00b621\u00cb\u00ebS\u0097`\u00a0)\u00e8\u000b\u008fi\u00fc\u00c7\u00f6 GD\u00ea]\u0088\u00d2\"GA*\u008a\u009c\u008e\u00cf\u0083\u0095\u00da3P\u00b79\u0010\u00f0QGp?m\u0082\u00c8Ez \u00d5\u00df\u00fd\u0001!\u00e4*0\u00bb<R\u00c0\u001c\u00f5\u00d7\u0096\u00c3\"\u00d6\u00d4\u00f1\u0085ruv\u0002o\u00dd5\u009e\u0098j\u0018\rP\u00e3^\u00eb \u009e?\u00fcl\u0006\u00bb\u00b7\u0006\u00a3\u00bb\u0014\u00bf\u0088a`\u00db\u00acz\u0018\u008e\u00a6\u00af\u00d7\u009c\u0099\u00a9\u00c8QU\u00eb\u00910\u00a5\u0083\u00d5\u00d7\u00f9y\u0080uO\u0007\u001a(w\u00bd!\u0097\u0096\u00bd/\u00d4cs\u00d3\u00fa\u00db\u001b\u0006\u00cd\u00ab\u0019\u0099#\u00ec\u0013\u0000e\u00bf\u0012\u00ea\u00a6R\u00a2p\u00df\u0019\u001e\u00c1\u00e2\u009e\u00ba\u00fas\u0018\n\u0094\u0088/tw\u0089\u0000\r\u0094\u009c\u00dc\u00d3D\u007fX\u00f4) _v\u0080\u00ef\u00b2(d\u00f5|\u00f3o7\u00f1X\u001e;\u00d7\u00a8@EM\u00942Y\u00804=I\u00d7\u00fe=\u00e9\u0018\u0091\u0001\u00f5,f.\u000e\u0015/\u00aa\u0089\u0092Qx\u008e\u0006\u0092x\u00b5\u00b5cl;\u00d5\u00cd\u00e6N\u00b7\u00ef\u001a3\u00f0\u0095\t!\u008a:\u00e5\u009a:\u0007\u00bf\u00e4\u0084\u00d6\r\u00df\u0013\u0094\u00bb8\u00b8\u00be\u00a4=\u00b2\u00f6\u0082UT\u0010\u00c2P\u0003W\u00a1\u00e7\u0003;&\u00bb\u00c1\u00aa\u00e9\u00efk\u00a2O\u00f8q\u0091\u0010\u00f6\u00a3c=\u0017\u0092\u00bc\u00f5F\u00e8\u00fe\u0089\u00b3q\u00a2+\u00eaD\n)\u00f9\u0090\u00bb\u0013 2\u00d0\u00b6\u00ce\u00b2[<\u00d3\u00cb9\u00cbXu6\u000ed\u00f4\u00fb\u00e6\f.\u00d7\u00c9\u00d0\u0081\u0094\u00e2\u0018\u0083.\u0016k\u00ab\u00ae\u00ec\u00d1\u00f2\u00960\u00a0\u00d2Z\u0090\\K\u0013\u00c7\u001be(LV \u00f7G\u0080\u00da\u0096r\u00b2\u00b7\u0084\u0094)\u0092H\u0081\u0010\u0098\u0084\u001d-\u00d80\u00c6\u00ef\u00a3\u00cdF\u0002\u001d\u0085\u0089\u001c\u00dc(\u00c6\u009d\u0088\u0097\u00e1&\u00f8fS:\u00e0}\u00f9\u00bf\u00fe\u009f\u0013\u00af^\u0082k\u00b6\u008b\u00fa\u00f6-H\u0017.C\u00d5\u00a5U\u0001\u00ec\u00b1\u00cc\u00a3C\t W\u00a0\u0099!\u001fS`\u008b6f\u00da\u0083|\u008d\u00eb\u00d4\u0017R\u0006s\u00ae\u00bc\u00c8\u00e5SqB\u0002\u00fc\u00ed,\u0095(\u0018\u00a27\u00ddj\u00df\u001bT0]\u00ef\u00fa^J\u00fb\u0091}6\u00f7}\u009a\u00c8\u00ddp\u0090\u00ae\u000e\u00b5\u0089\u00d7\u00b3\u00e9\u00c7\u007f(\u00f2\u0003\u009f\u0098\u0081\u0018\u00cb\u00ee\u009c4\u00f2\u00d9\u008e\u00c9\u00f6\u00a9\u00a5\u00bf\u008e\u00e3\u00e4\u0019\u00adj\u00cb\u00bbJf\u0015[ \u00b5I\u00ac\u00b3\u00be\u00a9\u0087\u00b1\u00e9L\u00bd\u0093Lx\u00c0\u00f5\u00c1pWH. /\u000f\u008d\u0099\u00f1\u00d7\u0083B\u00bee\u0018\u00fd\u00bb8N\u00ed\u0094$X\u00f3\t\u001d\u00bb\u00a8k1\u00f1Vm\u008ak\u00ba]\u001e] \u00f9\u001fvMh81a!\u00f1\u00c8\u00b3a\u0099\u00cb\b\u0085WB\u00f9\u00fc}i\u00f0\u00ac\u0005\u00b1f\u0097Yc\u008b xq\\\u00ceF\u00bc\\\u00fe\u000ek\u001b\u00f0\u00be:y\u0018a%.9@\u00f0\u0092O}\u009cR\u0001,j\u009a\u00c90\u0082\u001a\u00a9\u00e7R\u00b5\u00c0X\u00e8\u00b5CLs\u0016\u00d9l\u00d5\u0084\u0010\u001dkW\u0092c$\u00afCC%C\u008cA\u00da\u00d0fY&t\u00d5QU,\u0098C\u00daq\u00ad4 8\u00d5\u00ada\u0010@]\u00d4\u0018\u00c9\u00ebK\u00ddK\u00f9\u00d9\u00ed\u0005-\u0091).\u00f1a6\u00fdP\u00b2\u00bd\u0087\u00db\u00e3\u0018%\u00c5\u00a2\u00c1\u00ad\u0004\u00a1\u000e\u0097\u00e0t\u0004\u00ce\u00f5\u00ed\u0012\u00f6\u00e1\u000fC\u00c9\u0019\u00f6\u00cc \u0000?\u0089%\u001c:7\f\u00b7\u00cc\u00af\u00f6L\u00fc\u00de\u001d2$\u00fd\u00d2\u0092g\u0084\u0005\u00d2\u00c2\u0091\u00e8\u00bc\u0089\nr\u0018\u00b2k\r\u0082\u00f3\u008b\f\u00ad/\u00da\u00b2`\u009a\u0000J`\u0088\u00e0A\u00c6$\u00b1\u00f6.\u0010+h}>7O8B\u00bf\u00c5\u0016\u00dd\u00fc\u00c2J$ '\u0083\u0018\u00e9\u00c9X\u00c9\u00025m\u00cd\u00cf O\u0005\u0081\u00d1$X\u00ff\u00e3C]\u0098\u00846\u00f7lC\u00e1\u00a9V\u0018\u008e\b\u008f\u00df\u0095\u00bdI\u00c4\u00a6\u0010\u00a2\u00116\u0081/\u00e7\u0096\u00edmE\u00f6\u00a3\u00f8\u00b3 Q\u00e6h\u0015\u00b0\f\u00fb\u00c1\u00ba\u00c2\u00b4\t\u001f4\u00df\u0081K\u00d2\u000fh\u0092y\u0082\u000ea\u0005\u00f8\u00e1\u000eB7|\u0080C(\u0092\u0005\u00b2\u00f5\u00e6\u00fa\u00c0\u00bb\u00aai\u00c3\u0091\u00fc'\u0095\u00ec]6\u00fb5OVt\u00ee1\u0095\u0099{\u00dd\u0006S\u00dd\u00dd\u001c\u00c6\u00c7\u00c7\u00e5!\\\u001f\u0010;i'\u001a\u0005\u00b6\u00ae[i\u0092\u0083g<S\u00e1_\u00ab\u0017+I1\u00cb\u00dd\u00f5\u00f9\u00b8?\u00f2V\u00a4k\u0019m?\u0089w,L\u0084\u00d9<\u00ec\u009c\u00bb\u00abkp\u00ea\u00bcIj\u00bd\u00d7WR<\u00d9\u00e8\u00c0\u009d\u009e_d\u00f2\u00d8\u00f7\u00b2\u00fc\u0098\u00e5\u00ba\u00fb\u00dfH5\u00fcx`|\u008a\u00e4]\u00c9K \u00d4\u009d\u00c7\u00e6\u00a5sSI\u0090!\u00c15Q\u009c\u008c\u00c3:$\u00be<k'\u00b5l\u0094\u00a6 \u0094\u00af\n\u00f9\u00e5\u00186C%\u00bcli=U?\u00bf\u00d8v8%\u0016#\u00c2\u00810\f\u0016q\u00f5\u00a2\u0018CD\u0003\u001a|O\u000b\u00bb\u00c2\u00c3\u00dd<ep\u00a1\u00deF\u00e6\u001d:\u00e2\u00f8\u00b3\u00ac \u00cc\u00c0\u0005dU\u00b2\u00e4=\u00b0+\r0\u0016\u0086\u0087\u00e3%I\u00a1S\u001aH\u00ecj\u00d0&^M\u007f\u00a9q\u00d2 c'y\u00e4@\u00d8%k\t\u00cb/\u00c6\u00c2\u008f\u0083\u000f\u0084[9\u0098\u0094E\u00d3'\u00ab\u000f\u00f7\u00ac\u00dc\u00a4\u00af\n(U\u00db\u0080\u00b0\u00a1\u00c8Q\u00ab\u00d6lO\u00bf\u0007\u0018X\u00ce\u00a7\u00af\u0098a\u0083\u0093\u00e0^\u00b6w\u00a5\u00f1y\tw\u00e7?\u00b0\u00eb\"iz\u00fcs\u00188\u00a2tn\u00a8d6\u00c5/\u0086\ta\u00ad\u00be\u0006\u00e3\u0085\u00e23C\n\u00b3v4 \u00fd\u00bc\u0006-\u009d\u00df\u0019\u00bc\u00b3c\u00e2hW\u007f\u001d\u0001O\u0087\u009d\u00e1|\u00cb~\u00ca\u0084\u0013\u00af\u00e4\u0001\u00f9\u00af\u00ec";
                        var17_6 = 3376;
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
                            var18_3[var16_4++] = ElytraTarget.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0005\u00ce\u00bd\u00db\u00c8\u00e4\u00b0r[l\u00ee\u00e0H\u0094\u0010&ux\u00b7S.D\u00b5| U~\u00e81\u0003\u0091\u008fF\u00c6\u00f0\u00b8\u001f\u0002A\u00a7\u00e1\u00140\u00a4;\u00a1\u0014\u000b\u00b6#CA\u00fd\u00fe\u0099\u00c9\u0016";
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
                            var18_3[var16_4++] = ElytraTarget.zkmDecodeUtf8(var19_9).intern();
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
                ElytraTarget.fb = var18_3;
                ElytraTarget.gb = new String[107];
                ElytraTarget.nb = new HashMap<K, V>(13);
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
                var6_12 = new long[59];
                var3_13 = 0;
                var4_14 = "Vlt\u0013\u00a6b\u00e3\u0018m(\u009f\u00de\u00b9\u00bd.\u0098B\u00a2\u00c1\u00d5\u00c6?\f\u0096\u00d1X\u00d0d!;,'\u00f8\u00f9\u008f\u00f5\u00e0e\u00eb^\u00e3\u000e\u00de\u001e\u00cb\u00af\u00aeP\u0012\u0099\u00cb\u0007\u0006\u00a5\u00e2\u0084\u0099\u00ab\u00a8\u00a8\u00d9e\u009b\u008d9H\u00a4\u0087\u00c2X\u00b7\u0001\u00bd\u00fb\u0089\u00f9=\u00ec\u009bK\fo|\u0080\u00c9\u00e0\u008e\u00b4\u00e9\u007f\u00cf\u00f2\u00aa\u000b>\u001f\u00ebP\\*x|\u0093\u00ee1\u0013\t\u00feZj\r{\u00ab\u008a\u00bezC\tVYKIU\u00d4`\u00b5}\u008e*y_\u00ee.\u00d9\u00dd{\u00cd!v\"\u00d5\u00e7e'\u00b0\u00e9\u0098\u00ec\u00b5\u0015\u00fbe\u009c\u00f4\u00f2WmA\u00f6Sy\u00c9\u0087U\u000e\u00c8\t\u0000oq\u00a9'(E\u00e2\u009fq\u000bg\u0003nv\u00f7K\u00b1\u00f6\u00eco\u008a\u00e5|\u009e:\u00e3(y\u00b5}6\u0003bE\u00cf\u0002Yy\u00a5X@D\u00c9\u008fc\u0092\n\u00e9U\u00d4\u0093\";\u00fe\u007fN7Q\u009ex\u00d9\u00c9\u0091\u0095v%\u0082Y\\\u00a6\u0011F\u00b3\u009d(#\u00f0\u00e7\b\u0085\u0093\u00b9\u008b\u00b0\u00b0\nt\u00e2\u00ebi\u0003\u0093\u00fc\"p\u00c7\u00c6\u001ei<F\u00dfe\u0099\u008d\u00c0\u0086\f\"H\u009bGz\u00ab\u00f6\u0012\u0088V\u00a1D\u001c\u009c\u0095\u00d9\u00e4\u00f55p\u0093\u0082\u00c9\u00b3\u0016\u009e0\u0000\u00fah\u00e5\u00d7\u00c8\u000b\u00a5\u0096\u00b1\u00d0\u008bQM\u00e7\u00fa\n1D\u00b2$L\"\\N\u008cBY\u00b7\u00ef8M\u00c5(+\u00d3\u00d4\u00ffjV\u008c\u00f4\u00d0\u008an9P\u00d8\u0097/:\u00ef\u00deU\f\u00fc\u00f3tW:\u00f3}Sz\u0081\u008f\u00e0\u0011Uc\u00a2n\u00c8,\u00ec\u00b5\u00b2\u001c\u00cd5\u00dbvLX\u0019\u00d4&\u0089?}r\u00f9\u00eby\u00a8\u00be)\u00f0<\u001f\u0096\u008e\u00feD\u00a1}\u008fLW\u00da\u00b1\u0003l\u00a1\u0090\u00ecJ\u00cb\u00cd\u00e8\u000e\u0088\u00c2T;W\u00ff\u0007\u00c8]\u00ea\u00dd\u00fd\u00be\u0007\u00bd@u)\u00c8\u00d8Z\u00d7\u00e1\u00c4<N\u00be\u00da8J\u00d8\u001a";
                var5_15 = 456;
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
                    var4_14 = " \u001a\u00a9\u007fFdV\u00a4]\u00ec\u00da\u0093\\\u00e9a[";
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
        ElytraTarget.lb = var6_12;
        ElytraTarget.mb = new Integer[59];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "predict", "getPredict()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "alwaysPredict", "getAlwaysPredict()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "offHandFirework", "getOffHandFirework()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "bravoHvhSwap", "getBravoHvhSwap()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "minSpeed", "getMinSpeed()I", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "pauseWhileEating", "getPauseWhileEating()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "peekAssist", "getPeekAssist()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "fakeLag", "getFakeLag()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "onlyGroundPeek", "getOnlyGroundPeek()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "boost", "getBoost()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "customSpeed", "getCustomSpeed()Z", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees0", "getDegrees0()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees5", "getDegrees5()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees10", "getDegrees10()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees15", "getDegrees15()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees20", "getDegrees20()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees25", "getDegrees25()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees30", "getDegrees30()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees35", "getDegrees35()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees40", "getDegrees40()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees45", "getDegrees45()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees50", "getDegrees50()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees55", "getDegrees55()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees60", "getDegrees60()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees65", "getDegrees65()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees70", "getDegrees70()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees75", "getDegrees75()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees80", "getDegrees80()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees85", "getDegrees85()F", 0)), Reflection.property1(new PropertyReference1Impl(ElytraTarget.class, "degrees90", "getDegrees90()F", 0))};
        ElytraTarget.delegatedProperties = var32_20;
        ElytraTarget.INSTANCE = new ElytraTarget();
        ElytraTarget.predict$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "predict-ticks", 0.0f, RangesKt.rangeTo(0.0f, 5.0f), null, null, 24, null);
        ElytraTarget.alwaysPredict$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "always-predict", false, null, null, 12, null);
        ElytraTarget.delay$delegate = SettingFactory.intSetting$default(ElytraTarget.INSTANCE, "delay", 10, new IntRange(0, 30), null, null, 24, null);
        ElytraTarget.offHandFirework$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "offhand-firework", true, null, null, 12, null);
        ElytraTarget.bravoHvhSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "bravo-hvh-swap", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraTarget.minSpeed$delegate = SettingFactory.intSetting$default(ElytraTarget.INSTANCE, "min-speed", 120, new IntRange(0, 200), null, null, 24, null);
        ElytraTarget.pauseWhileEating$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "pause-while-eating", true, null, null, 12, null);
        ElytraTarget.peekAssist$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "peek-assist", true, null, null, 12, null);
        ElytraTarget.fakeLag$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "fake-lag", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraTarget.onlyGroundPeek$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "only-ground-peek", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraTarget.boost$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "boost", true, null, null, 12, null);
        ElytraTarget.customSpeed$delegate = SettingFactory.booleanSetting$default((SettingContainer)ElytraTarget.INSTANCE, "custom-speed", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        ElytraTarget.degrees0$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "0-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees5$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "5-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees10$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "10-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees15$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "15-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees20$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "20-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees25$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "25-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees30$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "30-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees35$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "35-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees40$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "40-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees45$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "45-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees50$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "50-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees55$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "55-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees60$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "60-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees65$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "65-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees70$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "70-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees75$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "75-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible20(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees80$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "80-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible21(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees85$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "85-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible22(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.degrees90$delegate = SettingFactory.floatSetting$default(ElytraTarget.INSTANCE, "90-degrees", 0.15f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible23(), ()Ljava/lang/Boolean;)(), 8, null);
        ElytraTarget.glideTimer = new TickTimer();
        7834730552665000351L ^ var20;
        ElytraTarget.V = new TickTimer();
        ElytraTarget.F = new TickTimer();
        ElytraTarget.predictTimer = new TickTimer();
        var32_20 = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(0.155f)), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(0.19f)), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(0.27f)), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(0.28f)), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(0.2f)), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(0.18f)), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(0.13f))};
        ElytraTarget.h = MapsKt.hashMapOf(var32_20);
        var32_20 = new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Float.valueOf(0.155f)), TuplesKt.to(Float.valueOf(5.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(10.0f), Float.valueOf(0.19f)), TuplesKt.to(Float.valueOf(15.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(0.22f)), TuplesKt.to(Float.valueOf(25.0f), Float.valueOf(0.27f)), TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(0.28f)), TuplesKt.to(Float.valueOf(35.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(40.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(45.0f), Float.valueOf(0.37f)), TuplesKt.to(Float.valueOf(50.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(55.0f), Float.valueOf(0.34f)), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(0.2f)), TuplesKt.to(Float.valueOf(65.0f), Float.valueOf(0.18f)), TuplesKt.to(Float.valueOf(70.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(75.0f), Float.valueOf(0.16f)), TuplesKt.to(Float.valueOf(80.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(85.0f), Float.valueOf(0.13f)), TuplesKt.to(Float.valueOf(90.0f), Float.valueOf(0.13f))};
        ElytraTarget.speedMap = MapsKt.hashMapOf(var32_20);
    }

    public static void zkmSetFlag(int[] nArray) {
        z = nArray;
    }

    public static int[] int_arr_zkmGetFlag() {
        return z;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1204;
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
                throw new RuntimeException("su/catlean/uy", exception);
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
            ElytraTarget.gb[n2] = ElytraTarget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ElytraTarget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uy" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5FC0;
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
                throw new RuntimeException("su/catlean/uy", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ElytraTarget.mb[n2] = n3;
        }
        return mb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ElytraTarget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/uy" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ElytraTarget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ElytraTarget.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

