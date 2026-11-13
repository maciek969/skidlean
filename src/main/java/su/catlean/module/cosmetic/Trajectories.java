/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Triple;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.EggItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.player.FreecamStateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.Trajectories$WhenMappings;
import su.catlean.module.legit.BowMinecart;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;

public final class Trajectories
extends Module {
    @NotNull
    public static final Trajectories INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BindSetting previewBind$delegate;
    @NotNull
    private static final BooleanSetting handOffset$delegate;
    @Nullable
    private static Vec3d hitPos;
    @NotNull
    private static final Animation animation;
    @NotNull
    private static Vec3d landingPos;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map j;

    private Trajectories() {
        super("trajectories", Categories.getCosmetic(), null, null);
    }

    private final Bind getPreviewBind() {
        return (Bind)previewBind$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setPreviewBind(Bind bind) {
        previewBind$delegate.setValue(this, delegatedProperties[0], bind);
    }

    private final boolean getHandOffset() {
        return (Boolean)handOffset$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setHandOffset(boolean bl) {
        handOffset$delegate.setValue(this, delegatedProperties[1], bl);
    }

    private final boolean isThrowable(Item item) {
        return item instanceof EnderPearlItem || item instanceof TridentItem || item instanceof ExperienceBottleItem || item instanceof SnowballItem || item instanceof EggItem || item instanceof SplashPotionItem || item instanceof LingeringPotionItem;
    }

    private final float getThrowPower(Item item) {
        return item instanceof BowItem ? 1.0f : 0.4f;
    }

    private final float getGravity(Item item) {
        if (item instanceof SplashPotionItem || item instanceof LingeringPotionItem) {
            return 0.39f;
        }
        if (item instanceof ExperienceBottleItem) {
            return 0.59f;
        }
        if (item instanceof TridentItem) {
            return 2.0f;
        }
        return 1.5f;
    }

    private final int isPotionLike(Item item) {
        return item instanceof SplashPotionItem || item instanceof LingeringPotionItem || item instanceof ExperienceBottleItem ? 20 : 0;
    }

    private final float getMaxPredictTicks(Item item) {
        return item instanceof SplashPotionItem || item instanceof LingeringPotionItem || item instanceof ExperienceBottleItem ? 4.0f : 0.5f;
    }

    @Flow
    private final void onFreecamState(FreecamStateEvent freecamStateEvent) {
        int n;
        char c;
        long l = b ^ 0x37AF7117724EL;
        int n2 = (int)((l ^ 0x1C13951F08FAL) << 48 >>> 48);
        if (hitPos == null || this.getPreviewBind().getKey() == -1 || !KeyboardUtil.isKeyPressed(c = (char)n2, n = this.getPreviewBind().getKey()) || Globals.getMc().getCameraEntity() == null) {
            return;
        }
        freecamStateEvent.cancel();
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        Vec3d vec3d2 = hitPos;
        Intrinsics.checkNotNull(vec3d2);
        Vec3d vec3d3 = vec3d2.add(0.0, Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d3, "add(...)");
        float f = animation.getValue();
        Vec3d vec3d4 = vec3d3;
        Vec3d vec3d5 = vec3d;
        Vec3d vec3d6 = RenderState.INSTANCE.lerpVec(vec3d5, vec3d4, f);
        freecamStateEvent.setX(vec3d6.x);
        freecamStateEvent.setY(vec3d6.y);
        freecamStateEvent.setZ(vec3d6.z);
        Entity entity = Globals.getMc().getCameraEntity();
        Intrinsics.checkNotNull(entity);
        freecamStateEvent.setYaw(entity.getYaw());
        freecamStateEvent.setPitch(0.0f);
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        block0: {
            ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
            if (clientPlayerEntity == null) break block0;
            ClientPlayerEntity clientPlayerEntity2 = clientPlayerEntity;
            Vec3d vec3d = clientPlayerEntity2.getVelocity();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getDeltaMovement(...)");
            landingPos = vec3d;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onRender3D(Render3DEvent var1_1) {
        var2_2 = Trajectories.b ^ 11287706877132L;
        var20_3 = (int)((var2_2 ^ 37353259943544L) << 48 >>> 48);
        new String[0];
        var34_4 = this.getPreviewBind().getKey();
        var35_5 = (char)var20_3;
        Trajectories.animation.animate(KeyboardUtil.isKeyPressed(var35_5, var34_4) != false ? 1.0f : 0.0f);
        Trajectories.hitPos = null;
        v0 = Globals.getMc().options.getBobView().getValue();
        Intrinsics.checkNotNullExpressionValue(v0, "get(...)");
        var24_6 = v0;
        Globals.getMc().options.getBobView().setValue(false);
        for (AbstractClientPlayerEntity var26_10 : Globals.getWorld().getPlayers()) {
            Intrinsics.checkNotNullExpressionValue(var26_10.getMainHandStack(), "getMainHandItem(...)");
            Intrinsics.checkNotNullExpressionValue(var26_10.getOffHandStack(), "getOffhandItem(...)");
            if (var27_11.getItem() instanceof BowItem || var27_11.getItem() instanceof CrossbowItem) ** GOTO lbl-1000
            v1 = var27_11.getItem();
            Intrinsics.checkNotNullExpressionValue(v1, "getItem(...)");
            if (this.isThrowable(v1)) lbl-1000:
            // 2 sources

            {
                v2 = Hand.MAIN_HAND;
            } else {
                if (!(var28_12.getItem() instanceof BowItem) && !(var28_12.getItem() instanceof CrossbowItem)) {
                    v3 = var28_12.getItem();
                    Intrinsics.checkNotNullExpressionValue(v3, "getItem(...)");
                    if (!this.isThrowable(v3)) continue;
                }
                v2 = Hand.OFF_HAND;
            }
            var29_13 = v2;
            var31_16 = var29_13 == Hand.OFF_HAND ? var28_12.getItem() : var27_11.getItem();
            Intrinsics.checkNotNull(var31_16);
            var30_14 = var31_16;
            v4 = Enchantments.MULTISHOT;
            Intrinsics.checkNotNullExpressionValue(v4, "MULTISHOT");
            var31_15 = EnchantUtil.hasEnchantment(var28_12, v4);
            v5 = Enchantments.MULTISHOT;
            Intrinsics.checkNotNullExpressionValue(v5, "MULTISHOT");
            var32_8 = EnchantUtil.hasEnchantment(var27_11, v5);
            var33_9 = RenderUtil.INSTANCE.interpolate(var26_10.lastYaw, var26_10.getYaw());
            if (var28_12.getItem() instanceof CrossbowItem && var31_15 || var27_11.getItem() instanceof CrossbowItem && var32_8) {
                Intrinsics.checkNotNull(var26_10);
                this.simulateTrajectory(var26_10, var30_14, var33_9 - 10.0f);
                this.simulateTrajectory(var26_10, var30_14, var33_9);
                this.simulateTrajectory(var26_10, var30_14, var33_9 + 10.0f);
                continue;
            }
            Intrinsics.checkNotNull(var26_10);
            this.simulateTrajectory(var26_10, var30_14, var33_9);
        }
        Globals.getMc().options.getBobView().setValue(var24_6);
    }

    public final void simulateTrajectory(@NotNull PlayerEntity playerEntity, @NotNull Item item, float f) {
        Intrinsics.checkNotNullParameter(playerEntity, "player");
        Intrinsics.checkNotNullParameter(item, "item");
        hitPos = null;
        double d = RenderUtil.INSTANCE.interpolate(playerEntity.lastX, playerEntity.getX());
        double d2 = RenderUtil.INSTANCE.interpolate(playerEntity.lastY, playerEntity.getY());
        double d3 = RenderUtil.INSTANCE.interpolate(playerEntity.lastZ, playerEntity.getZ());
        d2 += (double)playerEntity.getEyeHeight(playerEntity.getPose());
        if (this.getHandOffset()) {
            d2 -= 0.1;
            if (Intrinsics.areEqual(playerEntity, Globals.getMc().player)) {
                if (Intrinsics.areEqual(item, playerEntity.getOffHandStack().getItem())) {
                    d += (double)((float)Math.cos(f / 180.0f * (float)Math.PI) * 0.16f);
                    d3 += (double)((float)Math.sin(f / 180.0f * (float)Math.PI) * 0.16f);
                } else {
                    d -= (double)((float)Math.cos(f / 180.0f * (float)Math.PI) * 0.16f);
                    d3 -= (double)((float)Math.sin(f / 180.0f * (float)Math.PI) * 0.16f);
                }
            }
        }
        float f2 = RenderUtil.INSTANCE.interpolate(playerEntity.lastPitch, playerEntity.getPitch());
        float f3 = this.getThrowPower(item);
        double d4 = -((float)Math.sin(f / 180.0f * (float)Math.PI)) * (float)Math.cos(f2 / 180.0f * (float)Math.PI) * f3;
        double d5 = -((float)Math.sin((f2 - (float)this.isPotionLike(item)) / 180.0f * 3.141593f)) * f3;
        double d6 = (float)Math.cos(f / 180.0f * (float)Math.PI) * (float)Math.cos(f2 / 180.0f * (float)Math.PI) * f3;
        float f4 = RenderUtil.INSTANCE.interpolate(Math.max(0.0f, (float)playerEntity.getItemUseTime() - 1.0f), playerEntity.getItemUseTime()) / 20.0f;
        if ((f4 = (f4 * f4 + f4 * 2.0f) / 3.0f) > 1.0f || f4 == 0.0f) {
            f4 = 1.0f;
        }
        float f5 = MathHelper.sqrt((float)(d4 * d4 + d5 * d5 + d6 * d6));
        d4 /= (double)f5;
        d5 /= (double)f5;
        d6 /= (double)f5;
        float f6 = (item instanceof BowItem ? f4 * 2.0f : (item instanceof CrossbowItem ? 2.2f : 1.0f)) * this.getGravity(item);
        d4 *= (double)f6;
        d5 *= (double)f6;
        d6 *= (double)f6;
        if (Intrinsics.areEqual(playerEntity, Globals.getMc().player)) {
            d4 += RenderUtil.INSTANCE.interpolate(Trajectories.landingPos.x, playerEntity.getVelocity().x);
            d5 += RenderUtil.INSTANCE.interpolate(Trajectories.landingPos.y, playerEntity.getVelocity().y);
            d6 += RenderUtil.INSTANCE.interpolate(Trajectories.landingPos.z, playerEntity.getVelocity().z);
        }
        for (int i = 0; i < 300; ++i) {
            Entity entity;
            Iterable<Entity> iterable;
            Object object;
            BlockHitResult blockHitResult;
            Vec3d vec3d = new Vec3d(d, d2, d3);
            if (Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(d += d4, d2 += d5, d3 += d6)).getBlock(), Blocks.WATER)) {
                d4 *= 0.8;
                d5 *= 0.8;
                d6 *= 0.8;
            } else {
                d4 *= 0.99;
                d5 *= 0.99;
                d6 *= 0.99;
            }
            d5 -= item instanceof BowItem ? (double)0.05f : (playerEntity.getMainHandStack().getItem() instanceof CrossbowItem ? (double)0.05f : 0.03);
            Vec3d vec3d2 = new Vec3d(d, d2, d3);
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().raycast(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, playerEntity)), "clip(...)");
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                if (BowMinecart.INSTANCE.canUseFlameBow()) {
                    Box box = new Box(blockHitResult.getBlockPos().up()).withMaxY((double)blockHitResult.getBlockPos().up().getY() + 0.125);
                    Intrinsics.checkNotNullExpressionValue(box, "setMaxY(...)");
                    object = box;
                    ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                    Intrinsics.checkNotNull(clientPlayerEntity);
                    float f7 = (float)clientPlayerEntity.getBlockInteractionRange();
                    double d7 = Globals.getPlayer().getEyePos().distanceTo(blockHitResult.getBlockPos().toCenterPos());
                    Color color = ((double)2.3f <= d7 ? d7 <= (double)f7 : false) ? Theme.INSTANCE.getGradientColor(0) : Color.RED;
                    Intrinsics.checkNotNull(color);
                    RenderState.addOutlineBox$default(RenderState.INSTANCE, (Box)object, color, null, null);
                    float f8 = RenderUtil.INSTANCE.getAlpha(color) / 4.0f;
                    Object var84_48 = null;
                    Color color2 = null;
                    Color color3 = RenderUtil.INSTANCE.withAlpha(color, f8);
                    Object object2 = object;
                    RenderState renderState = RenderState.INSTANCE;
                    RenderState.addFilledBox$default(renderState, (Box)object2, color3, color2, var84_48);
                } else {
                    Vec3d vec3d3 = blockHitResult.getPos();
                    Intrinsics.checkNotNullExpressionValue(vec3d3, "getLocation(...)");
                    float f9 = this.getMaxPredictTicks(item);
                    Direction direction = blockHitResult.getSide();
                    Intrinsics.checkNotNullExpressionValue(direction, "getDirection(...)");
                    Color color = Theme.INSTANCE.getGradientColor(i * 10);
                    Direction direction2 = direction;
                    float f10 = f9;
                    Vec3d vec3d4 = vec3d3;
                    this.drawHitMarker(vec3d4, f10, direction2, color);
                }
                Vec3d vec3d5 = blockHitResult.getPos();
                Intrinsics.checkNotNullExpressionValue(vec3d5, "getLocation(...)");
                Object var92_57 = null;
                int n = 24;
                boolean bl = false;
                Color color = null;
                Color color4 = Theme.INSTANCE.getGradientColor(i);
                Vec3d vec3d6 = vec3d5;
                Vec3d vec3d7 = vec3d;
                RenderState.addLine$default(RenderState.INSTANCE, vec3d7, vec3d6, color4, color, bl, n, var92_57);
                if (!(item instanceof EnderPearlItem) || !Intrinsics.areEqual(playerEntity, Globals.getMc().player)) break;
                hitPos = blockHitResult.getPos();
                break;
            }
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getEntities(), "entitiesForRendering(...)");
            Iterable<Entity> iterable2 = iterable;
            Collection collection = new ArrayList();
            for (Entity entity2 : iterable2) {
                entity = entity2;
                if (!(!(entity instanceof ProjectileEntity) && !Intrinsics.areEqual(entity, Globals.getMc().player) && (entity.squaredDistanceTo(vec3d2) < 25.0 || entity.squaredDistanceTo(vec3d) < 25.0))) continue;
                collection.add(entity2);
            }
            object = (List)collection;
            boolean bl = false;
            Iterable iterable3 = (Iterable)object;
            for (Object e : iterable3) {
                Vec3d vec3d8;
                Entity entity3 = (Entity)e;
                Intrinsics.checkNotNullExpressionValue(entity3.getBoundingBox(), "getBoundingBox(...)");
                Optional<Vec3d> optional = ((Box)((Object)entity)).raycast(vec3d, vec3d2);
                if (!optional.isPresent() || bl) continue;
                Intrinsics.checkNotNullExpressionValue(optional.get(), "get(...)");
                Object var102_33 = null;
                int n = 24;
                boolean bl2 = false;
                Color color = null;
                Color color5 = Theme.INSTANCE.getGradientColor(i);
                Vec3d vec3d9 = vec3d;
                RenderState.addLine$default(RenderState.INSTANCE, vec3d9, vec3d8, color5, color, bl2, n, var102_33);
                bl = true;
                if (!(item instanceof EnderPearlItem) || !Intrinsics.areEqual(playerEntity, Globals.getMc().player)) continue;
                hitPos = entity3.getBoundingBox().getCenter();
            }
            if (bl || d2 <= -65.0) break;
            if (d4 == 0.0 && d5 == 0.0 && d6 == 0.0) continue;
            Object var112_40 = null;
            int n = 24;
            boolean bl3 = false;
            Color color = null;
            Color color6 = Theme.INSTANCE.getGradientColor(i);
            Vec3d vec3d10 = vec3d2;
            Vec3d vec3d11 = vec3d;
            RenderState.addLine$default(RenderState.INSTANCE, vec3d11, vec3d10, color6, color, bl3, n, var112_40);
        }
    }

    private final void drawHitMarker(Vec3d vec3d, float f, Direction direction, Color color) {
        List list = new ArrayList();
        double d = vec3d.x;
        double d2 = vec3d.y;
        double d3 = vec3d.z;
        int n = 10;
        int n2 = 0;
        int n3 = ProgressionUtilKt.getProgressionLastElement(0, 360, n);
        if (n2 <= n3) {
            while (true) {
                double d4 = (double)n2 * Math.PI / 180.0;
                Triple<Double, Double, Double> triple = switch (Trajectories$WhenMappings.C[direction.ordinal()]) {
                    case 1 -> new Triple<Double, Double, Double>(Math.sin(d4) * (double)f, Math.cos(d4) * (double)f, 0.0);
                    case 2 -> new Triple<Double, Double, Double>(Math.sin(d4) * (double)f, Math.cos(d4) * (double)f, 0.0);
                    case 3 -> new Triple<Double, Double, Double>(0.0, Math.sin(d4) * (double)f, -Math.cos(d4) * (double)f);
                    case 4 -> new Triple<Double, Double, Double>(0.0, Math.sin(d4) * (double)f, -Math.cos(d4) * (double)f);
                    case 5 -> new Triple<Double, Double, Double>(Math.cos(d4) * (double)f, 0.0, Math.sin(d4) * (double)f);
                    case 6 -> new Triple<Double, Double, Double>(Math.cos(d4) * (double)f, 0.0, Math.sin(d4) * (double)f);
                    default -> throw new NoWhenBranchMatchedException();
                };
                double d5 = ((Number)triple.component1()).doubleValue();
                double d6 = ((Number)triple.component2()).doubleValue();
                double d7 = ((Number)triple.component3()).doubleValue();
                list.add(new Vec3d(d + d5, d2 + d6, d3 + d7));
                if (n2 == n3) break;
                n2 += n;
            }
        }
        n3 = list.size() - 1;
        for (n2 = 0; n2 < n3; ++n2) {
            Object var30_22 = null;
            int n4 = 24;
            boolean bl = false;
            Color color2 = null;
            Color color3 = color;
            Vec3d vec3d2 = (Vec3d)list.get(n2 + 1);
            Vec3d vec3d3 = (Vec3d)list.get(n2);
            RenderState.addLine$default(RenderState.INSTANCE, vec3d3, vec3d2, color3, color2, bl, n4, var30_22);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            Trajectories.b = ZkmKeys.a(8621345471678034559L, 7541880272653035240L, MethodHandles.lookup().lookupClass()).a(34667908258972L);
                            var27 = Trajectories.b ^ 13881990078781L;
                            Trajectories.f = new HashMap<K, V>(13);
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var27 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var27 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[28];
                            var23_4 = 0;
                            var22_5 = "\u00e6WX\u0001M8W\u00a2\u00dc\u0082>\u0097`F\u00ae1\u001d\u00f38\u0085\u00aas\u008f)\u00a3\u00a2|\u0004\u00a8\u00c3\u00ee\u00ea\u00b8a\u00aaV\u00db\u0090\u00a6\u00a4\u0010p\u00ca\u00f5>C\u00c5\u00fa\u00d9\u00e2\r\u00ad\u00f8\u00d5\u00cc\u00f3\u00ce(\u00e8\u008c\u00df\u0080,5j\u008e\t\u00f4\u00f6\u0000\u0096\u0003\u00b9\u0015\u0097\u00cf\u007f\u0087bX\u00e5\u008e\u00e0\u00ff~$\u0006\u00d1[\u0081j>/\u007f\u0015H\u00a6\u00d3 \u00aa\u00cf\u00a2v?\u00bd\u00d1\u0018\u00af\u0012\r\u0019\u00d8\u00a7\n5\u0007\u0019\u00ef\u0007O\u0014\u00bf~6*\u0004\u00cd\u009cl\u001e\u00f6 \u00df\u007f\u000e\u00f2\u00d7q:`G\u00de\u001e:\u000e//\u00c8`f\u00cc_?\u00e9\u00da)x\u00d9\u00eb\u00b4\u0089\u00e5\u00bc<(\u001a\u00d0\u00a9\u00c61\u00f0f\u00d2\u00d6\u00b6\u00cb\"\u009f\u0019\u00ad\u00067\u0082\u00b8p*\u00f0\u0015\u00ac[\u008d\u00ef\u0093\u00b6!@\u00c9\u00a0\u00cb\u00cfr\u00f2U\u00a3\u00fa \u00b8\u00dc\u0096\u001bN\u008f\u00d5\u00ca\u0007Q\u00ec\u00c1j]yE\u00c9\u00e0\u0001Cwa\u0098w\u00b7\u00af:\u00f7{\u00bf)e %\u0004-\u00a5A?\u00d0\u00cf\u00ecU\u00c8\u00bf\u0013\u0081}\u00fd\u00faJ\u00cda\u00d1\u001aX\u00f1\u00b4\u0083TD\u00f6A\u00807(\rQ\u0084b\u008b\u008e\u00fe)&\u00c7!\u00ee\u00ae]\u00ca\u00c5\u0095\u0080G\u00b8\u00cb\u00b1\u00ed\u00d2\u00fbTy\u00e8r\u00e1\u000f\u00be\u00f3\u001f\u00f2\u00d1_\u0098\u0016&8\u000b~\u00c3\u00fd\u0089\u00f9\u0085Y$r5\u00af\u0003\u00ee6\u00cc\u00car0&\u00bf\u0087~\u00e12AYMa;\u00ecp\u0098.#\u00c0c\u009eX\u00be:\u00ea \u0003\u00d3V\u00b9\u00e0\u00bc\u0003.v:\u00c9\u00cb{(g\u000bA\u00ea\u00de\u00d7:BF\u0005L\u0083\u00fa\u00e0\u00f5\u001631\u0096\u00dbA\u009ce8\u0002\u00db\u0095\u00f4\u00f5~G\u0082\u0015\u00bat~\u000fG\u00ed%\u0018\u00eb\u0010{P;1\u00e6\u00ef\u00edMb\u00d1&4\u00be\u0006\u00a3\u0012U\u0097\u00dd\u00e2\u00e3\u00f6\u0018\u0014\u0097\u0014\u008aw9\u00d2\u00c3g\u00efV\u00e85M\u00f7\u00be{I\u0092\u0019\u00cd$\u0012\u001d(\u00cf\u00f9\u00cb\u00a0,\u00b5\u00fb\u00b7\u0018\u0004\t\u00c0@N>b\u0003!.\u008a\u0080\u001a\u00e0\u0015\u009e\u00d5\u0083<\u0097V\u00dd8\u00dd\u00de2\u0000]}\r\u00c2(\u00a5\u00daQ+\u00d7\u00f2\u00ab\u0088\bg?\u00f5\u008bo\u00dc\u00ebh\u001c.\u009b\u00f5C\u00cb\n.\u009c\u0093Sa\u00ee\"\u0004G`\u009bc\\\u0002\u009f\u0002 '\u009a\u00ac\u001f\u00cb\u008aGqnw\u00e1\u0006\u001a5\u00c4/G\u00e0\u0015:\u00a4\u00ee' \u00fa\u00a5S\u00a3c\u00fb\u00c9\u00d6\u0018\u00a4\u00b9a7b\u00f5q\u001c\u00ad\u00ea\u00d3\u00c6\u00d3\u001c\u00b0\u008b\u00bc\u00f3\u0086\u00aa\u00e5l\u00e0\u00db B\u0013\u00a7JA\u00d3\u00162\u00af$_\u00d3i \u00f0x\u00b3\u00c0\u00a07]\u00a1/xQq\u0017 \u0092e\u00fb\u00f6(\u00e2\u0015\u00e3\u001cSl\u00f7\u000e\f>\u00f0\u00bb\u00cfc\u00f6\u0003\u00ea\u00e48\u0013\u00dcV>\u00fa\u00882\u001dI\tvC\u00b6+2W\u009c\u0013\u00a0\u0094\u008f\u0010TF\u008f\u00dfO\u00fc@\u00b4\u00fe\u00a2\u00cb\u009a\u0097\u00b2\u00acH\u0018\u0004R\u00f8\u00a8\u00fd(\u00e9\u00d1G\u0019\u0080\u00fclC.\u008c\u00c0O\u00e3\t\u000eT8-0\u00cc\u00fc\u00af\u0007\u00b94\u00a8\u00cd\u00a09\u0092\u0093\u00ff\u00a9\u00c8/\u00a9\u00aa\u0091\u00ae\u0095S\u00f3\u00b2\u00e89A\u0095\u008f)f\u000e6&\u0001\u00caG\u0005\u00abt\u00e0\u0002\u0012\u008b\u0002\u00e9\u00cf\u00c1\u0010\rN\u00b1\f\u00cd\rU\u009a\u00f9%\u00e2\u00155\u00cd>* \u0083\u0007E\u0089\u00e0\u00e6\u00aa\u00dbZ\u009e\u008czo\u00e8|z\u0085\u00a7\u009a0\u00c9\u00b9U\u00b7\u00e0\u00dbU\u00f0\r\u0006&\u0005\u0018\rs'\u00e0B\u00d6 \u00b3T\u00d2\u0088i\u0094\u00ad\u00ae\u00fe\u001f\u00f6P\u00c3\u00ef\u00baL\u00d18\u00eby3O\u00c7\u00c6\u0005\u0091\u008a\u001ffX\u00c9\u00f1\u00bb\u00af8k7\u00bd)C\u00c54\u00bb\u00b8\u00d1.2\u00d5\u008a\u008dW#\u00cf'\u00f8{\u00e1\u00bd\u00b4\u001b\u0080.\u001bb\u001a}\f\u0013\u00ed*/r\u00fe\u0013";
                            var24_6 = 897;
                            var21_7 = 40;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = Trajectories.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u0018,|Nh\tRV\u00d4\u0081\u00ab\u0019\u009d\u0001\u00f0k\u0018\r-\u00bf\u00f1w\u0092sy*\u00cd\u00cc|\u0087\u00c2\u00dd\u00ae\u0014\u00cf\u00d8\u00c3\u00b3iq\u0088";
                                var24_6 = 41;
                                var21_7 = 16;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = Trajectories.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    Trajectories.c = var25_3;
                    Trajectories.d = new String[28];
                    Trajectories.j = new HashMap<K, V>(13);
                    var7_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var27 >>> 56);
                    for (var8_11 = 1; var8_11 < 8; ++var8_11) {
                        v9 = v9;
                        v9[var8_11] = (byte)(var27 << var8_11 * 8 >>> 56);
                    }
                    var7_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var13_12 = new long[10];
                    var10_13 = 0;
                    var11_14 = "\u00da~O\u0097\u00b1\u0095\u0011uR\\\u0081\u0094\u00f1\u0098\u00a0a\u00bad]H\u00bf\u00e6-4~\u00c0q\u001d\u00f8\u00d7\u00ec\u00bd/\f\u00d4\u00dc\u00db=\u00b7\u001d\u00de\u0001;\u0086\u00a6=\u0010.\u00c5h\n\u0085\u000bU\u00e7-\u00cc\u0015W\u00acQ[{8";
                    var12_15 = 64;
                    var9_16 = 0;
                    while (true) {
                        var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                        v10 = var13_12;
                        v11 = var10_13++;
                        v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        var11_14 = "\u00f3\u00dc\u0096\u0099\u00d5\u0080\u0080\u0004\u00e8\u00e7\u00ba|\u00de\u0082\u00ac\u00f3";
                        var12_15 = 16;
                        var9_16 = 0;
                        while (true) {
                            var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                            v10 = var13_12;
                            v11 = var10_13++;
                            v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_18 = v12;
                var17_19 = var7_10.doFinal(new byte[]{(byte)(var15_18 >>> 56), (byte)(var15_18 >>> 48), (byte)(var15_18 >>> 40), (byte)(var15_18 >>> 32), (byte)(var15_18 >>> 24), (byte)(var15_18 >>> 16), (byte)(var15_18 >>> 8), (byte)var15_18});
                v14 = ((long)var17_19[0] & 255L) << 56 | ((long)var17_19[1] & 255L) << 48 | ((long)var17_19[2] & 255L) << 40 | ((long)var17_19[3] & 255L) << 32 | ((long)var17_19[4] & 255L) << 24 | ((long)var17_19[5] & 255L) << 16 | ((long)var17_19[6] & 255L) << 8 | (long)var17_19[7] & 255L;
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
            Trajectories.g = var13_12;
            Trajectories.h = new Integer[10];
            var2_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_21 = 1; var3_21 < 8; ++var3_21) {
                v17 = v17;
                v17[var3_21] = (byte)(var27 << var3_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{54, -106, -76, -9, 43, 79, -99, -74});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        var40_24 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Trajectories.class, "previewBind", "getPreviewBind()Lsu/catlean/lj;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Trajectories.class, "handOffset", "getHandOffset()Z", 0))};
        Trajectories.delegatedProperties = var40_24;
        Trajectories.INSTANCE = new Trajectories();
        Trajectories.previewBind$delegate = SettingFactory.bindSetting$default(Trajectories.INSTANCE, "preview", new Bind(0, false, false, 7, null), null, null, 12, null);
        Trajectories.handOffset$delegate = SettingFactory.booleanSetting$default((SettingContainer)Trajectories.INSTANCE, "hand-offset", false, null, null, 12, null);
        Trajectories.animation = new Animation(Easing.OUT_QUINT, var0_22);
        v18 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v18, "ZERO");
        Trajectories.landingPos = v18;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1A40;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/kk", exception);
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
            Trajectories.d[n2] = Trajectories.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Trajectories.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xA67;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kk", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Trajectories.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Trajectories.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Trajectories.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Trajectories.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

