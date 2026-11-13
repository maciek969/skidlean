/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.GameMode;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.AttackBlockEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.BlockStateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.player.SpeedMineMode;
import su.catlean.module.world.Nuker$1;
import su.catlean.module.world.Nuker$EntriesMappings;
import su.catlean.module.world.Nuker$WhenMappings;
import su.catlean.module.world.Nuker$onPlayerUpdate$1;
import su.catlean.module.world.NukerFilterMode;
import su.catlean.module.world.NukerSortMode;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceInfo;
import su.catlean.util.PlaceManager;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Nuker
extends Module {
    @NotNull
    public static final Nuker INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting filter$delegate;
    @NotNull
    private static final ModeSetting sort$delegate;
    @NotNull
    private static final BlockListSetting selectedBlocks$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final FloatSetting range$delegate;
    @NotNull
    private static final IntSetting yawAngle$delegate;
    @NotNull
    private static final BooleanSetting ignoreWalls$delegate;
    @NotNull
    private static final BooleanSetting flatten;
    @NotNull
    private static final BooleanSetting creative$delegate;
    @NotNull
    private static final BooleanSetting avoidLava$delegate;
    @Nullable
    private static Block targetBlock;
    @Nullable
    private static PlaceInfo currentPlace;
    @NotNull
    private static final Timer breakTimer;
    private static boolean y;
    private static String A;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private Nuker() {
        super("nuker", Categories.getWorld(), null, null);
    }

    private final NukerFilterMode getFilter() {
        return (NukerFilterMode)((Object)filter$delegate.getValue(this, delegatedProperties[0]));
    }

    private final NukerSortMode getSort() {
        return (NukerSortMode)((Object)sort$delegate.getValue(this, delegatedProperties[1]));
    }

    private final BlockListValue getSelectedBlocks() {
        return (BlockListValue)selectedBlocks$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setSelectedBlocks(BlockListValue blockListValue) {
        selectedBlocks$delegate.setValue(this, delegatedProperties[2], blockListValue);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final float getRange() {
        return ((Number)range$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    private final int getYawAngle() {
        return ((Number)yawAngle$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final boolean getIgnoreWalls() {
        return (Boolean)ignoreWalls$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean e() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        return (Boolean)flatten.getValue(this, delegatedProperties[Nuker.zkmBootstrap2("x", (int)30074, (long)(0xC2C03543B56F3BBL ^ l))]);
    }

    private final boolean getCreative() {
        return (Boolean)creative$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getAvoidLava() {
        return (Boolean)avoidLava$delegate.getValue(this, delegatedProperties[9]);
    }

    @Flow
    public final void onAttackBlock(@NotNull AttackBlockEvent attackBlockEvent) {
        Intrinsics.checkNotNullParameter(attackBlockEvent, "e");
        Object var8_2 = null;
        if (Globals.getWorld().isAir(attackBlockEvent.getPos())) {
            return;
        }
        Block block = Globals.getWorld().getBlockState(attackBlockEvent.getPos()).getBlock();
        Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
        Block block2 = block;
        if (this.getFilter() == NukerFilterMode.Select && !Intrinsics.areEqual(targetBlock, block2)) {
            targetBlock = block2;
        }
    }

    @Flow
    public final void onBlockState(@NotNull BlockStateEvent blockStateEvent) {
        long l = a ^ 0x5777130EC238L;
        long l2 = l ^ 0x64399D667581L;
        Intrinsics.checkNotNullParameter(blockStateEvent, "e");
        Object var8_4 = null;
        PlaceInfo placeInfo = currentPlace;
        if (Intrinsics.areEqual(placeInfo != null ? placeInfo.getBp() : null, blockStateEvent.getPos()) && blockStateEvent.getState() != null) {
            BlockState blockState = blockStateEvent.getState();
            Intrinsics.checkNotNull(blockState);
            if (blockState.isAir() && !this.getCreative()) {
                currentPlace = null;
                this.updateTarget(l2);
            }
        }
    }

    @Flow
    public final void onPlayerUpdate(@Nullable PlayerUpdateEvent playerUpdateEvent) {
        block15: {
            Object object;
            int n;
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7;
            block13: {
                long l;
                block14: {
                    long l2;
                    long l3 = l2 = a ^ 0x3F880A42240EL;
                    n7 = (int)((l3 ^ 0x4F3B3DBC5C2FL) << 32 >>> 32);
                    l = l3 ^ 0xCC6842A93B7L;
                    long l4 = l3 ^ 0x53BD3CEC8A55L;
                    n6 = (int)(l4 >>> 48);
                    n5 = (int)(l4 << 16 >>> 48);
                    n4 = (int)(l4 << 32 >>> 32);
                    long l5 = l3 ^ 0x2A3846A92510L;
                    n3 = (int)(l5 >>> 48);
                    n2 = (int)(l5 << 16 >>> 48);
                    n = (int)(l5 << 32 >>> 32);
                    this.updateTarget(l);
                    Object var56_11 = null;
                    if (currentPlace == null) break block13;
                    ClientWorld clientWorld = Globals.getWorld();
                    PlaceInfo placeInfo = currentPlace;
                    Intrinsics.checkNotNull(placeInfo);
                    Block block = clientWorld.getBlockState(placeInfo.getBp()).getBlock();
                    Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
                    object = block;
                    if (!Intrinsics.areEqual(object, targetBlock) && this.getFilter() == NukerFilterMode.Select) break block14;
                    PlaceInfo placeInfo2 = currentPlace;
                    Intrinsics.checkNotNull(placeInfo2);
                    Vec3d vec3d = placeInfo2.getBp().toCenterPos();
                    Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
                    if (!(MovementUtil.INSTANCE.squaredDistanceTo(vec3d) > this.getRange() * this.getRange()) && !Intrinsics.areEqual(object, Blocks.AIR)) break block13;
                }
                currentPlace = null;
                this.updateTarget(l);
            }
            if (Globals.getMc().options.attackKey.isPressed() || currentPlace == null) {
                return;
            }
            PlaceInfo placeInfo = currentPlace;
            Intrinsics.checkNotNull(placeInfo);
            object = placeInfo.getVec3d();
            if (!this.getCreative()) {
                double d = MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(((Vec3d)object).z - Globals.getPlayer().getZ(), ((Vec3d)object).x - Globals.getPlayer().getX())) - 90.0 - (double)MovementUtil.INSTANCE.getLastYaw());
                float f = (float)(-Math.toDegrees(Math.atan2(((Vec3d)object).y - Globals.getPlayer().getEyePos().y, Math.sqrt(Math.pow(((Vec3d)object).x - Globals.getPlayer().getX(), 2.0) + Math.pow(((Vec3d)object).z - Globals.getPlayer().getZ(), 2.0))))) - MovementUtil.INSTANCE.getLastPitch();
                float f2 = MathHelper.clamp(MathHelper.abs((float)d), -((float)this.getYawAngle()), (float)this.getYawAngle());
                float f3 = MathHelper.clamp(f, -((float)this.getYawAngle()), (float)this.getYawAngle());
                y = MathHelper.abs((float)d) <= (float)this.getYawAngle();
                float f4 = MovementUtil.INSTANCE.getLastYaw() + (d > 0.0 ? f2 : -f2);
                float f5 = MathHelper.clamp(MovementUtil.INSTANCE.getLastPitch() + f3, -90.0f, 90.0f);
                RotationManager.INSTANCE.submit(new RotationRequest(new Rotation(f4, f5, false, null, 12, null), 0, Nuker::onPlayerUpdate$lambda$0));
            }
            if (this.getCreative() && breakTimer.hasPassed2(this.getDelay())) {
                int n8 = (int)((float)Math.floor(this.getRange()) + 1.0f);
                List list = new ArrayList();
                Entity entity = Globals.getPlayer();
                BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
                Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
                BlockPos blockPos2 = blockPos;
                int n9 = -n8;
                if (n9 <= n8) {
                    while (true) {
                        int n10;
                        if ((n10 = -n8) <= n8) {
                            while (true) {
                                int n11;
                                if ((n11 = -n8) <= n8) {
                                    while (true) {
                                        BlockPos blockPos3 = blockPos2.add(n9, n10, n11);
                                        Intrinsics.checkNotNullExpressionValue(blockPos3, "offset(...)");
                                        list.add(blockPos3);
                                        if (n11 == n8) break;
                                        ++n11;
                                    }
                                }
                                if (n10 == n8) break;
                                ++n10;
                            }
                        }
                        if (n9 == n8) break;
                        ++n9;
                    }
                }
                Object object222 = list;
                for (Object object222 : CollectionsKt.sortedWith(object222, new Nuker$onPlayerUpdate$1())) {
                    Block block;
                    BlockState blockState;
                    if (this.e() && (double)((Vec3i)object222).getY() < Globals.getPlayer().getY() || this.getAvoidLava() && this.isExposed(n7, (BlockPos)object222)) continue;
                    Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState((BlockPos)object222), "getBlockState(...)");
                    Vec3d vec3d = ((BlockPos)object222).toCenterPos();
                    Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
                    if (!(MovementUtil.INSTANCE.squaredDistanceTo(vec3d) <= this.getRange() * this.getRange())) continue;
                    Intrinsics.checkNotNullExpressionValue(blockState.getBlock(), "getBlock(...)");
                    if (!this.isValidBlock((short)n6, (char)n5, block, n4)) continue;
                    PacketUtil.sendSequencedPacket(arg_0 -> Nuker.onPlayerUpdate$lambda$1((BlockPos)object222, arg_0));
                    PacketUtil.sendSequencedPacket(arg_0 -> Nuker.onPlayerUpdate$lambda$2((BlockPos)object222, arg_0));
                    Globals.getWorld().setBlockState((BlockPos)object222, Blocks.AIR.getDefaultState());
                    Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                }
            }
            if (this.getCreative() || !y || !breakTimer.hasPassed2(this.getDelay())) break block15;
            this.breakCurrent((char)n3, (short)n2, n);
        }
    }

    public final synchronized void breakCurrent(char c, short s, int n) {
        long l = ((long)c << 48 | (long)s << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        long l2 = l ^ 0x61FE9E99C7ADL;
        if (currentPlace == null || Globals.getMc().options.attackKey.isPressed()) {
            return;
        }
        if (this.getEnabled() && SpeedMine.INSTANCE.getMode() != SpeedMineMode.DAMAGE && !SpeedMine.INSTANCE.canMineMore()) {
            PlaceInfo placeInfo = currentPlace;
            Intrinsics.checkNotNull(placeInfo);
            BlockPos blockPos = placeInfo.getBp();
            PlaceInfo placeInfo2 = currentPlace;
            Intrinsics.checkNotNull(placeInfo2);
            SpeedMine.INSTANCE.startMining(blockPos, placeInfo2.getDir(), l2);
            return;
        }
        if (Globals.getInteractionManager().getCurrentGameMode() == GameMode.CREATIVE) {
            ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
            PlaceInfo placeInfo = currentPlace;
            Intrinsics.checkNotNull(placeInfo);
            BlockPos blockPos = placeInfo.getBp();
            PlaceInfo placeInfo3 = currentPlace;
            Intrinsics.checkNotNull(placeInfo3);
            v7 = clientPlayerInteractionManager.attackBlock(blockPos, placeInfo3.getDir());
        } else {
            ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
            PlaceInfo placeInfo = currentPlace;
            Intrinsics.checkNotNull(placeInfo);
            BlockPos blockPos = placeInfo.getBp();
            PlaceInfo placeInfo4 = currentPlace;
            Intrinsics.checkNotNull(placeInfo4);
            v7 = clientPlayerInteractionManager.updateBlockBreakingProgress(blockPos, placeInfo4.getDir());
        }
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        Intrinsics.checkNotNullParameter(render3DEvent, "event");
        BlockPos blockPos = null;
        Object var18_3 = null;
        if (!this.getCreative() && currentPlace != null) {
            PlaceInfo placeInfo = currentPlace;
            Intrinsics.checkNotNull(placeInfo);
            blockPos = placeInfo.getBp();
        }
        if (blockPos != null) {
            Object var13_4 = null;
            Color color = null;
            Color color2 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 1.0f);
            Box box = new Box(blockPos);
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color2, color, var13_4);
            Object var20_8 = null;
            Color color3 = null;
            Color color4 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 0.5f);
            Box box2 = new Box(blockPos);
            RenderState renderState = RenderState.INSTANCE;
            RenderState.addFilledBox$default(renderState, box2, color4, color3, var20_8);
        }
    }

    private final PlaceInfo findNextBlock(short s, short s2, int n) {
        long l;
        long l2 = l = ((long)s << 48 | (long)s2 << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        int n2 = (int)((l2 ^ 0x49C5CEEE2470L) << 32 >>> 32);
        long l3 = l2 ^ 0x5543CFBEF20AL;
        int n3 = (int)(l3 >>> 48);
        int n4 = (int)(l3 << 16 >>> 48);
        int n5 = (int)(l3 << 32 >>> 32);
        int n6 = (int)((l2 ^ 0x6B8833354394L) << 48 >>> 48);
        int n7 = (int)((float)Math.floor(this.getRange()) + 1.0f);
        List list = new ArrayList();
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        Object var39_14 = null;
        int n8 = -n7;
        if (n8 <= n7) {
            while (true) {
                int n9;
                if ((n9 = -n7) <= n7) {
                    while (true) {
                        int n10;
                        if ((n10 = -n7) <= n7) {
                            while (true) {
                                BlockPos blockPos3 = blockPos2.add(n8, n9, n10);
                                Intrinsics.checkNotNullExpressionValue(blockPos3, "offset(...)");
                                list.add(blockPos3);
                                if (n10 == n7) break;
                                ++n10;
                            }
                        }
                        if (n9 == n7) break;
                        ++n9;
                    }
                }
                if (n8 == n7) break;
                ++n8;
            }
        }
        Object object22 = list;
        for (Object object22 : CollectionsKt.sortedWith(object22, new Nuker$1())) {
            Block block;
            BlockState blockState;
            if (SpeedMine.INSTANCE.getEnabled() && SpeedMine.INSTANCE.isQueued((BlockPos)object22, (short)n6) || this.e() && (double)((Vec3i)object22).getY() < Globals.getPlayer().getY()) continue;
            Vec3d vec3d = ((BlockPos)object22).toCenterPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            if (MovementUtil.INSTANCE.squaredDistanceTo(vec3d) > this.getRange() * this.getRange() || this.getAvoidLava() && this.isExposed(n2, (BlockPos)object22)) continue;
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState((BlockPos)object22), "getBlockState(...)");
            Intrinsics.checkNotNullExpressionValue(blockState.getBlock(), "getBlock(...)");
            if (!this.isValidBlock((short)n3, (char)n4, block, n5)) continue;
            if (this.getIgnoreWalls()) {
                BlockHitResult blockHitResult = PlaceManager.raycastAgainst$default(PlaceManager.INSTANCE, new RaycastContext(Globals.getPlayer().getEyePos(), ((BlockPos)object22).toCenterPos(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer()), (BlockPos)object22, null, null);
                Vec3d vec3d2 = blockHitResult.getPos();
                Intrinsics.checkNotNullExpressionValue(vec3d2, "getLocation(...)");
                Direction direction = blockHitResult.getSide();
                Intrinsics.checkNotNullExpressionValue(direction, "getDirection(...)");
                return new PlaceInfo((BlockPos)object22, vec3d2, direction);
            }
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    for (int k = 0; k < 5; ++k) {
                        BlockHitResult blockHitResult;
                        float f = 0.25f;
                        Vec3d vec3d3 = new Vec3d((float)((Vec3i)object22).getX() + (float)i * f, (float)((Vec3i)object22).getY() + (float)j * f, (float)((Vec3i)object22).getZ() + (float)k * f);
                        Intrinsics.checkNotNullExpressionValue(Globals.getWorld().raycast(new RaycastContext(Globals.getPlayer().getEyePos(), vec3d3, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, Globals.getPlayer())), "clip(...)");
                        if (blockHitResult.getType() != HitResult.Type.BLOCK || !Intrinsics.areEqual(blockHitResult.getBlockPos(), object22)) continue;
                        Direction direction = blockHitResult.getSide();
                        Intrinsics.checkNotNullExpressionValue(direction, "getDirection(...)");
                        return new PlaceInfo((BlockPos)object22, vec3d3, direction);
                    }
                }
            }
        }
        return null;
    }

    private final boolean isExposed(int n, BlockPos blockPos) {
        int n2 = 0;
        long l = ((long)n2 << 32 | (long)n << 32 >>> 32) ^ a;
        Iterator iterator2 = Nuker$EntriesMappings.enumEntries.iterator();
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8718970837953990489L, (long)l);
        while (iterator2.hasNext()) {
            Direction direction = (Direction)iterator2.next();
            boolean bl = Intrinsics.areEqual(Globals.getWorld().getBlockState(blockPos.offset(direction)).getBlock(), Blocks.LAVA);
            while (bl) {
                bl = true;
                if (n <= 0 || callSite != null) continue;
                return bl;
            }
        }
        return false;
    }

    private final void updateTarget(long l) {
        long l2 = (l = a ^ l) ^ 0xA387778EBE8L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 48);
        int n3 = (int)(l2 << 32 >>> 32);
        if (!(targetBlock == null && this.getFilter() == NukerFilterMode.Select || Globals.getMc().options.attackKey.isPressed() || currentPlace != null)) {
            currentPlace = this.findNextBlock((short)n, (short)n2, n3);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isValidBlock(short s, char c, Block block, int n) {
        long l = ((long)s << 48 | (long)c << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        boolean bl = this.getSelectedBlocks().getBlocks().contains(block);
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5802292948222863069L, (long)l);
        boolean bl2 = Nuker$WhenMappings.P[this.getFilter().ordinal()];
        if (callSite == null) {
            switch (bl2) {
                case 1: {
                    bl2 = Intrinsics.areEqual(block, Blocks.BEDROCK);
                    if (callSite != null) return bl2;
                    if (bl2) return false;
                    bl2 = Intrinsics.areEqual(block, Blocks.AIR);
                    if (callSite != null) return bl2;
                    if (bl2) return false;
                    bl2 = Intrinsics.areEqual(block, Blocks.CAVE_AIR);
                    if (callSite != null) return bl2;
                    if (bl2) return false;
                    bl2 = block instanceof FluidBlock;
                    if (callSite != null) return bl2;
                    if (bl2) return false;
                    return true;
                }
                case 2: {
                    bl2 = Intrinsics.areEqual(block, targetBlock);
                    return bl2;
                }
                case 3: {
                    bl2 = bl;
                    return bl2;
                }
                default: {
                    bl2 = bl;
                }
            }
        }
        CallSite callSite2 = callSite;
        if (s >= 0) {
            if (callSite2 == null) {
                if (bl2) return false;
                bl2 = Intrinsics.areEqual(block, Blocks.BEDROCK);
            }
            callSite2 = callSite;
        }
        if (c > '\u0000') {
            if (callSite2 == null) {
                if (bl2) return false;
                bl2 = Intrinsics.areEqual(block, Blocks.AIR);
            }
            callSite2 = callSite;
        }
        if (n > 0) {
            if (callSite2 == null) {
                if (bl2) return false;
                bl2 = Intrinsics.areEqual(block, Blocks.CAVE_AIR);
            }
            callSite2 = callSite;
        }
        if (n >= 0) {
            if (callSite2 == null) {
                if (bl2) return false;
                bl2 = block instanceof FluidBlock;
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) return bl2;
        if (bl2) return false;
        return true;
    }

    private static final Unit onPlayerUpdate$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final Packet onPlayerUpdate$lambda$1(BlockPos blockPos, int n) {
        return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, blockPos, Direction.UP, n);
    }

    private static final Packet onPlayerUpdate$lambda$2(BlockPos blockPos, int n) {
        return new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, blockPos, Direction.UP, n);
    }

    public static final NukerSortMode access$getSort(Nuker nuker) {
        return nuker.getSort();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Nuker.a = ZkmKeys.a(-2066120293456293034L, 8718585026777844002L, MethodHandles.lookup().lookupClass()).a(273418987614513L);
                        var20 = Nuker.a ^ 98241256938656L;
                        Nuker.e = new HashMap<K, V>(13);
                        Nuker.zkmSetFlag(null);
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
                        var18_3 = new String[46];
                        var16_4 = 0;
                        var15_5 = "\u0090:\f\f<(\u00906]<\\>\u001d.\tW(\u00de\u00dc\u00be'\u009e#3\u0001Z\n\u00bef\u0004\u00ac\u0098\u0000p\u0016b\u000f\u00c2\u00aecY]\u0098}j\u009f\u00cc*d\u00b4\bN\u00e2*\u00b9^\u0018 \u0092\u00a3d\u0091\\\u007f\u00f1\u00123)W\u00cd\u00b7EfK&\u00f3|\u00f4\u0085\n\u0081\u0016!\u0086\u00cb\u001b\u00ba\u0087g\u0004\u0018U\u00eb\u00f0\u00ff\rG\u008b\u00f3A)\u00aa]W\u008c}\u0014\u00e0j\n&\u0004\u00ee\u00d2!\u0018\u00f3\\V\u00c5\u0082E3\u008fs\u00f3)\r\u00a8\u00b8\u0093\u0088uo\u001b\u00a3\u00fe!q\u00be(n[\u00d8\u0084\u00c3Q]\u008c\u00c4\u00a1\u009bA\u00fe<\u00f7\u00e3>\u00a9\u0000\u0086\u009e+\u0082&\u00c3O\u0010A\u00aeV\u00c7\u00ec\u00f03Bog\u00eb\u00e9c\u0010\u0004J\u008d4O\u00b5@2\u0094\u00c7\u0015\u00c9\"\u00abq\u0019\u0010\u00fa\u00e3\u00f1\u00b3\u00bec\u00f3\u0016\u00aca\u0084\u0013\u0011\u0085rW  \u00de\u0088H^\u0013\u00abr\u00d0\u00c9L\u0087W\u00d6\u00a77\u00c8\u00e2\u007f\u0015\u00ac&\u00eaw\u00ff\u00e5p\u0092]\u00a0x70\u008c\u001d2\u0007\u0092^\u00d4\u00bcU\u00b2k\u0097\u0003\u00e9\"\f\u00c7=\u001a\u0010\u00a5\u00e3*\u00a5L\u00f3!T\u0092w\u00c8\u0080v\u0095y\u00dd\u0098\u0090\u00b3\u00a8[bl2+r \u00b4 F\u00d0\u00ea\u0086\u00e5\u007f\u0010k\u00f4\u00c1Q\u008fN\u00fa\u00f8$\u0080\u009c\u00d7\u008e\u0010\u00c3\u00e0a\f\u00ed\u00a6\u00f8\u00ec\\D\u00f2\u0010\u00ba\u00d2\u009e]\u0089\u0019\u0012kA\"\u0088!2\u00a4E\u00e5 B#,s1*\u00a0\u00a2\u0007\u00e0SB\u00a3\u00fd.1\u00f2\u009d\u00ce\u0010{]\u00b9z\u009c$\u00a2\u00be\u0097bv\u001a \n\u00bc\u00e5*\u00e4\u009c]\u00cc#\u00dc\u00e1\u0090\u00c1\u00f9\u00048\u00feB\u00978\u0085F\u00e99\u0095/\u00d3\u0015\u008d\u0019\u00c5\u00b9\u0018\u00f8V\u00be@'\u00ec\u00c8z\u00d7O\u00dd<\u00e9\u008c}[\u00e3/\u00a8@>r~=\u0018\u00c2\u0093\u00d5=\u0088G\r\u00c1\u00bd\u000f\u00d9z?\u00dfo\u00c4\u00bcN\u00af\u00d3I\u00ecn\u00b6@\u0098\u00bf\u00d2\u00c4Fj\u00d8\u00c0\u00f7,\u00e0q\u00df\u00d6\u00e0\u00d8\u00d3\u00e2S\u00ae\u00ce\u0093T_?:\u001e\u001f[\u000e\u001e\u00b3>C\u0014\u009a\u001b\u00f4L\u00dbn=\u00e9\u009a\u00c5\u00ba\u00ad;]\u00fb\u00b4\u00f8\u001dB\u00d6\u0080j\u0084\u00c8T\u00dc?H\u001e .&\u00f0;-\u00ac\u00d9\u0088=\u0004N,zd4\u0096\u00a0i\u0010$8\u00a1;\u0095ti\u00a8\u00b7,\u0099U\u00d1 y\u0084\u00b3\u00afZ\u00ddIm\u0094\u0089\u00e7\u00f4\u00d5N\u00d7Yn\u00ee\u00c9\u00d1\u00f1Z\u00b9\u00fd\b\u0096\u00b4\u00df)\u0011\u00e6\u00fc\u0010}o\u008eUbA\u0080\u00cd\u001b6\n\u00eb\u00b2\u00e0\u00c0\u0095 \u00e7\u00c6\u00f2_\u00b0, \u00cb~\u00c92\u0092/xt?\u00b5z\u008c\u00ac\u009b\u0014t\u0001\u00d8M\u00db\u0005K\u00e2\u00ec\u00bb\u0018\u00d4@]\u000eB\u00d57k\u00aa'\u0013v\u00c7M\u0098\"\u0090\u00b6\u0082M\u0016\u0082\u0013k(l$\n\u000b\u001ez\u00be\u00ffl|\u00c5;\u0017\u00e9\u0011\u00e3\f\u00b5\u00f0}\u00b3\u00dc\u00b4\u0004\u00e9h\u0018\u00e0\u00c5\u00f2\u00fd\u00dc\u0014\\\u00d6\u00ea\u00e2$\u00ac\u00c0\u0018\u00e0uR\"\u0012M\u00a5\u00b4z\u0081h%7v\u0082d\u00e3U^\u00fd\u00fd\u0088\u00d5\u0085\u0018\u00fe\u0099\u00db\u00ed\u0097u\u00ef\u00b7\u00aakPmF&\u00dd1\u008a\u00e9*\u00d5ZO\u00c5&(\u00a2G\u00df\u00ac\u001er\u008au\u00f7\u00c9M\u00de\u00f5\u000e\u00ba\u008dF\u00a5\u00d5\u00e0\u0000\u00ba\u001a[pZ \u001f;\u00acQ\u00d8P\u008a\u008df\u00aa~\u00eaI\u0010G\u00f7\u000e\u0007\u0093\u00b6_\u00b3a\u0096\u00dc\u00b8\u00b7\u008a\u00e0\u008c\u0018\u00e7\u00ef\u00cb\u00f0\u00d5\u00dcC_s\u0087U\u00b3\u001aWN\u0006\u00b2\u00fe\u00e4y\u009d\u00d7\u008e\u0081 \u00f7)\\g\u00bdDZ>\u0087\"Y\u00cdM\u00f1JZ%Fw\u0097z\u0080\f\u008c\u00cfI7\u0099$pl\u00e6\u0010u\u00f9w\u0093\u0093>\u0013\fa\u0083\n4\u00f6y\u0005\u0085 \u00a3\u0004\u001c\u00c1q\u00a9\u0010Za\u008a\u00be\u00ddu\u00e3\u00d1\u0006\u009d\u000e\u001cH\u00ff\u00ce\u00ba\u00a2\u00df\u001c\u00ea\u0092\u00b97\u00d6r\u0018\u00cc\u00d8\u00a1\u00cc\u00e5~;\u00f3 IR\u00f6M\u00dcC\u0092\u00ceE\u008dg\u00b8\u00f0\u00e5\u00cb\u0010,\u00ef\u0087\u00b3A2\u000f7\u00c1\u00aa\u00bd\u0016\u00eb(\u0091j k\u0003Z\u00cbK\u00d8\u0002M\u0091\u00efh\u009a#\u009eMk\u00b1\u00f7A=\u0011\u00e9@\u00b6\u00c2NP\u0086\u0089\u00f2vV\u0010\u008b)\u00da\u007f\u00a7\u0081f\u000bN\u00df\u00a4\u00a9\u0096\u009d(\u00cc \u00ba\u0089\u00feC8=\u00ceX\u00eaC\u00a6&\u0088\u00ce\u0095\u00e4E\u00ee\u00ab5\u00baf<\u0082Z\u008f\u001e\u00a0(`9\u00e5(\u00bf\u00a8\u00d7<\u0013\n\u00f9\u00bb\u00c4\u00bb\u00d6\u0017\u0086]\u00c1\u0084\u00b6p\u00bb,\u0011\u00b7)~u\u0095\u00f0\u00af~W\u00b0\u0013\u00f0\u0013#\u00a2B\u0099v%\u0018J8\u00de-z!N\n\u00e3'\u00f3y\u00d3\u00c4\u00ea\u00c4\u00b4\u00a7\u00c7\u00e3\u00e4\u0005\u00f61\u0018\u00969\u00ceE\u00c4\u00b9\u008b\u000bwV\u00c2\u00ec\u00e2\u00f9\u00b9\u00fbQ\u0006xc\u001e\u00d7\u00cf\u00e70\u00e7\u0013\u00d9\u00f5q[\u00baI!\u00cd&\u00dc\u0089\u00fa\u009b\u00e2\u001a\u000e)d\u00ff\u0089\u00de~Ve\u0097\u00b0 \u00d7'\u00c7\u000b\u0019\u00bf\u00ec?Ue\u00f5\u00b7\u00dc\u00a46\u000e\u00cb;\u0084 \u00ba<(S\u0011MG\u00a55+\u0005\u00c4\u00fb*\u00f8ht\u00cdiV\u008f\u00bf\u00b1\u0007\u00b5K\u009e\u00a3$\u0007#\u00aa\u0010x\u00f4\u00a1/4f\u00f0\u0089\u00a7\u00da\u00c1\u00e9\u00d2\u00ce\u0000\u00e3\u0010\u00f0\u00be\u001c\u009f\u00fd\u00db_\u00b6W\u00b7\u00f4\u0017\"\u00d7\u00e9] \u0080l\u0091?^[\u0003\u008c!\u00e9\u0091\r\u0006Y\u00c4GV\u00c9d\u00b0\u00c334\u0094\u00ba\f7C\u00c5T/\u00ea";
                        var17_6 = 1291;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = Nuker.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ef\u00a1OH\u00b0:4m<\u00a2\u009e\u0017\u009f\u00b3\u00c44 \u0082J\u00bd\u0095\u00ea\u00d9h\u00ea\u00f4A\u000b\u0092\u008a\u008f\n\u0084\u00ec8F\u008et\u0087\u00a8\u0098\u00ef\u0019\u00bc\u0096a\u001d'\u00fe";
                            var17_6 = 49;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = Nuker.zkmDecodeUtf8(var19_9).intern();
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
                Nuker.b = var18_3;
                Nuker.c = new String[46];
                Nuker.j = new HashMap<K, V>(13);
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
                var6_12 = new long[17];
                var3_13 = 0;
                var4_14 = "\u00b1}\u009e\u0014\u0002\u0001\u00b6\u00a5M\u0014\u00ed\\p\u00e66\u0011\u00dav\u00a0k]\u0011\u00a6\u00fa\r\u00d8$R\u00e8\u000b\u0016\u00e68\u0085DW&\u0016Y\u00d4\u00dfI\u00a7\u001e\u00c3R\u00cc\u0004b\u0092\u00f4\u00baS\u007f(\u00bb_U\u0096*\u0014\u00df\u000f~\u000e\u000e\u00d3\u0099\u000eH\u000e\u0081\u00fc\u00ee(Lu(\u0003\u00d9?\u00fc\u0015\u0080\u00e1,,\u00def\u0012\u00ffD\u008dfWYD?\\\u001a\u0087K\f\u0003\u00dc\u00fcv\u00a2l<4\u009d\u00b7\u00db\u0086\u00e7\u00a6b\u00adb";
                var5_15 = 120;
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
                    var4_14 = "\u001a\u00f2\u00ff\u0097M\u0001xk\u0093\u0095\u00fe\u000b=\u007f\u00bf\u0004";
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
        Nuker.h = var6_12;
        Nuker.i = new Integer[17];
        var42_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Nuker.class, "filter", "getFilter()Lsu/catlean/xb;", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "sort", "getSort()Lsu/catlean/bm;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Nuker.class, "selectedBlocks", "getSelectedBlocks()Lsu/catlean/dg;", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "range", "getRange()F", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "yawAngle", "getYawAngle()I", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "ignoreWalls", "getIgnoreWalls()Z", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "flatten", "getFlatten()Z", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "creative", "getCreative()Z", 0)), Reflection.property1(new PropertyReference1Impl(Nuker.class, "avoidLava", "getAvoidLava()Z", 0))};
        Nuker.delegatedProperties = var42_20;
        Nuker.INSTANCE = new Nuker();
        Nuker.filter$delegate = SettingFactory.modeSetting$default((SettingContainer)Nuker.INSTANCE, "filter", (Enum)NukerFilterMode.Select, null, null, 12, null);
        Nuker.sort$delegate = SettingFactory.modeSetting$default((SettingContainer)Nuker.INSTANCE, "sort", (Enum)NukerSortMode.FOV, null, null, 12, null);
        var37_21 = null;
        var39_22 = null;
        var40_23 = null;
        var41_24 = new BlockListValue(null, null);
        Nuker.selectedBlocks$delegate = SettingFactory.blockListSetting(Nuker.INSTANCE, "blocks", var41_24, var40_23, var39_22, var37_21);
        Nuker.delay$delegate = SettingFactory.intSetting$default(Nuker.INSTANCE, "delay", 25, new IntRange(0, 1000), null, null, 24, null);
        Nuker.range$delegate = SettingFactory.floatSetting$default(Nuker.INSTANCE, "range", 4.2f, RangesKt.rangeTo(3.0f, 7.0f), null, null, 24, null);
        Nuker.yawAngle$delegate = SettingFactory.intSetting$default(Nuker.INSTANCE, "degrees-per-tick", 35, new IntRange(5, 180), null, null, 24, null);
        Nuker.ignoreWalls$delegate = SettingFactory.booleanSetting$default((SettingContainer)Nuker.INSTANCE, "ignore-walls", false, null, null, 12, null);
        Nuker.flatten = SettingFactory.booleanSetting$default((SettingContainer)Nuker.INSTANCE, "flatten", false, null, null, 12, null);
        Nuker.creative$delegate = SettingFactory.booleanSetting$default((SettingContainer)Nuker.INSTANCE, "creative", false, null, null, 12, null);
        Nuker.avoidLava$delegate = SettingFactory.booleanSetting$default((SettingContainer)Nuker.INSTANCE, "avoid-lava", false, null, null, 12, null);
        Nuker.breakTimer = new Timer();
    }

    public static void zkmSetFlag(String string) {
        A = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return A;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5DC8;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/ev", exception);
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
            Nuker.c[n2] = Nuker.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Nuker.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ev" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4BDA;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
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
                throw new RuntimeException("su/catlean/ev", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Nuker.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Nuker.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ev" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Nuker.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Nuker.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

