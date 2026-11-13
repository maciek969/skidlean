/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.util;

import com.google.common.collect.Lists;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.client.network.PendingUpdateManager;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.player.BoxShrinker;
import su.catlean.util.DirectionVector;
import su.catlean.util.EntityCheck;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.InteractMode;
import su.catlean.util.InventoryUtil;
import su.catlean.util.Manager;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PendingBlocks;
import su.catlean.util.PlaceManager$EntriesMappings;
import su.catlean.util.PlaceManager$WhenMappings;
import su.catlean.util.PlaceMode;
import su.catlean.util.PlaceTarget;
import su.catlean.util.PlacementHelper;
import su.catlean.util.RandomUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.SideDistances;
import su.catlean.util.SwitchMode;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.rotation.LambdaRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PlaceManager
implements Manager {
    @NotNull
    public static final PlaceManager INSTANCE;
    @NotNull
    private static final Map s;
    @NotNull
    private static final Map y;
    @NotNull
    private static Vec3d vec3d;
    private static int[] W;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private PlaceManager() {
    }

    @NotNull
    public final Map r() {
        return y;
    }

    @Flow(priority=10)
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x60E74A94D8F8L;
        long l2 = l ^ 0x363947E5519L;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        Map map = new HashMap(y);
        Iterator iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry;
            Map.Entry entry2 = entry = iterator2.next();
            BlockPos blockPos = (BlockPos)entry2.getKey();
            long l4 = ((Number)entry2.getValue()).longValue();
            if (!((float)(System.currentTimeMillis() - l4) > (float)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null) * 2.0f)) continue;
            y.remove(blockPos);
        }
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        PlaceManager.vec3d = vec3d;
    }

    public final boolean canSee(@NotNull Vec3d vec3d) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)PlaceManager.zkmBootstrap("e", (int)28439, (long)(0x3C4D0ADD7DCF5346L ^ l))));
        return DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, PlaceManager.vec3d, vec3d, false, null, false, (int)PlaceManager.zkmBootstrap2("o", (int)14419, (long)(0x7D7426FCCBC5237L ^ l)), null) == null;
    }

    @NotNull
    public final Vec3d getEyePos(@NotNull Entity entity) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (Intrinsics.areEqual(entity, Globals.getPlayer())) {
            return vec3d;
        }
        Vec3d vec3d = entity.getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, (String)((Object)PlaceManager.zkmBootstrap("e", (int)24052, (long)(0x3F32782DD823CCBBL ^ l))));
        return vec3d;
    }

    @Nullable
    public final RotationRequest place(@NotNull BlockPos blockPos, int n, @NotNull PlaceMode placeMode, @NotNull InteractMode interactMode, int n2, char c, @NotNull EntityCheck entityCheck, float f, float f2, int n3, @NotNull SwitchMode switchMode, @NotNull List list, boolean bl, char c2) {
        RotationRequest rotationRequest;
        long l = ((long)n2 << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x58C94C47D05FL;
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Intrinsics.checkNotNullParameter((Object)placeMode, "interact");
        Intrinsics.checkNotNullParameter((Object)interactMode, "mode");
        Intrinsics.checkNotNullParameter((Object)entityCheck, "ignore");
        Intrinsics.checkNotNullParameter((Object)switchMode, "switch");
        Intrinsics.checkNotNullParameter(list, "calcGhost");
        BlockHitResult blockHitResult = this.calcPlaceData(blockPos, l2, n, placeMode, entityCheck, f, f2, list);
        if (blockHitResult == null) {
            return null;
        }
        BlockHitResult blockHitResult2 = blockHitResult;
        Function0<Unit> function0 = () -> PlaceManager.place$lambda$0(n, switchMode, blockHitResult2, interactMode, blockPos);
        if (placeMode == PlaceMode.GRIM) {
            Vec3d vec3d = blockHitResult2.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getLocation(...)");
            Function0<Unit> function02 = function0;
            int n4 = n3;
            Rotation rotation = Rotation.Companion.to(vec3d).withGrim(bl);
            rotationRequest = new LambdaRotationRequest(rotation, n4, function02);
        } else {
            Vec3d vec3d = blockHitResult2.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getLocation(...)");
            rotationRequest = new RotationRequest(Rotation.Companion.to(vec3d).withGrim(bl), n3, function0);
        }
        return rotationRequest;
    }

    public static RotationRequest place$default(PlaceManager placeManager, BlockPos blockPos, int n, PlaceMode placeMode, InteractMode interactMode, EntityCheck entityCheck, float f, float f2, int n2, long l, SwitchMode switchMode, List list, boolean bl, int n3, Object object) {
        long l2 = (l = a ^ l) ^ 0x642D0DF44AEAL;
        int n4 = (int)(l2 >>> 32);
        int n5 = (int)(l2 << 32 >>> 48);
        int n6 = (int)(l2 << 48 >>> 48);
        if ((n3 & 0x200) != 0) {
            list = new ArrayList();
        }
        if ((n3 & 0x400) != 0) {
            bl = false;
        }
        return placeManager.place(blockPos, n, placeMode, interactMode, n4, (char)n5, entityCheck, f, f2, n2, switchMode, list, bl, (char)n6);
    }

    public final boolean findPlaceData(@NotNull BlockPos blockPos, @NotNull PlaceMode placeMode, long l, @NotNull EntityCheck entityCheck, float f, float f2) {
        long l2 = (l = a ^ l) ^ 0x3D830B7DDCB9L;
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Intrinsics.checkNotNullParameter((Object)placeMode, "interact");
        Intrinsics.checkNotNullParameter((Object)entityCheck, "ignore");
        if (y.containsKey(blockPos)) {
            return false;
        }
        return PlaceManager.calcPlaceData$default(this, blockPos, -1, l2, placeMode, entityCheck, f, f2, null, null) != null;
    }

    public final void sendSequenced(@NotNull SequencedPacketCreator sequencedPacketCreator) {
        Intrinsics.checkNotNullParameter(sequencedPacketCreator, "packetCreator");
        AutoCloseable autoCloseable = Globals.getWorld().getPendingUpdateManager().incrementSequence();
        Throwable throwable = null;
        try {
            PendingUpdateManager pendingUpdateManager = (PendingUpdateManager)autoCloseable;
            Globals.getPlayer().networkHandler.sendPacket(sequencedPacketCreator.predict(pendingUpdateManager.getSequence()));
        }
        catch (Throwable throwable2) {
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally(autoCloseable, throwable);
        }
    }

    @Nullable
    public final BlockHitResult calcPlaceData(@NotNull BlockPos blockPos, long l, int n, @NotNull PlaceMode placeMode, @NotNull EntityCheck entityCheck, float f, float f2, @NotNull List list) {
        Iterator iterator2;
        Entity entity;
        Object t;
        Iterator iterator3;
        Object object2;
        Iterator iterator4;
        Iterable iterable;
        long l2 = (l = a ^ l) ^ 0x6F0BC583516FL;
        int n2 = (int)(l2 >>> 32);
        int n3 = (int)(l2 << 32 >>> 32);
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Intrinsics.checkNotNullParameter((Object)placeMode, "interact");
        Intrinsics.checkNotNullParameter((Object)entityCheck, "ignore");
        Intrinsics.checkNotNullParameter(list, "calcGhost");
        if (PendingBlocks.INSTANCE.isPending(blockPos)) {
            return null;
        }
        ArrayList arrayList = Lists.newArrayList((Iterable)Globals.getWorld().getNonSpectatingEntities(Entity.class, BoxShrinker.INSTANCE.getEnabled() ? new Box(blockPos).expand(-1.0E-12, 0.0, -1.0E-12) : new Box(blockPos)));
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        ArrayList arrayList2 = arrayList;
        if (entityCheck == EntityCheck.ALL) {
            iterable = arrayList2;
            iterator4 = iterable;
            object2 = new ArrayList();
            iterator3 = iterator4.iterator();
            while (iterator3.hasNext()) {
                t = iterator3.next();
                entity = (Entity)t;
                if (!(!(entity instanceof ItemEntity) && !(entity instanceof ExperienceOrbEntity))) continue;
                object2.add(t);
            }
            iterator2 = ((List)object2).iterator();
            if (iterator2.hasNext()) {
                Object e = iterator2.next();
                Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                return null;
            }
        }
        if (entityCheck == EntityCheck.ONLY_CRYSTALS) {
            iterable = arrayList2;
            iterator4 = iterable;
            object2 = new ArrayList();
            iterator3 = iterator4.iterator();
            while (iterator3.hasNext()) {
                t = iterator3.next();
                entity = (Entity)t;
                if (!(!(entity instanceof EndCrystalEntity) && !(entity instanceof ItemEntity) && !(entity instanceof ExperienceOrbEntity))) continue;
                object2.add(t);
            }
            iterator2 = ((List)object2).iterator();
            if (iterator2.hasNext()) {
                Object e = iterator2.next();
                Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                return null;
            }
        }
        BlockState blockState = Globals.getWorld().getBlockState(blockPos);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        iterator2 = blockState;
        if (!((AbstractBlock.AbstractBlockState)((Object)iterator2)).isReplaceable()) {
            return null;
        }
        if (placeMode == PlaceMode.AirPlace) {
            Entity entity2 = Globals.getPlayer();
            return PlaceManager.raycastAgainst$default(this, new RaycastContext(this.getEyePos(entity2), blockPos.toCenterPos(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer()), blockPos, null, null);
        }
        iterable = this.getSupportBlocks(blockPos, list);
        iterator4 = (0 <= n ? n < 9 : false) ? Globals.getPlayer().getInventory().getStack(n).getItem() : Items.OBSIDIAN;
        Intrinsics.checkNotNull(iterator4);
        Iterator iterator5 = iterator4;
        if (Intrinsics.areEqual(iterator5, Items.ITEM_FRAME)) {
            iterable.removeIf(arg_0 -> PlaceManager.invokePredicate(PlaceManager::calcPlaceData$lambda$0, arg_0));
        }
        if (Intrinsics.areEqual(iterator5, Items.VINE) && Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)((Object)iterator2)).getBlock(), Blocks.VINE)) {
            return null;
        }
        if (Intrinsics.areEqual(iterator5, Items.LADDER)) {
            iterable.removeIf(arg_0 -> PlaceManager.invokePredicate2(arg_0 -> PlaceManager.calcPlaceData$lambda$2(arrayList2, arg_0), arg_0));
        }
        for (Object object2 : iterable) {
            boolean bl;
            float f3;
            Object object3;
            if (placeMode == PlaceMode.Strict || placeMode == PlaceMode.GRIM) {
                object3 = this.getPlaceSides(n2, ((PlaceTarget)object2).getPosition(), n3);
                if (object3.isEmpty()) {
                    return null;
                }
                if (!object3.contains(((PlaceTarget)object2).getFacing())) continue;
            }
            if ((object3 = this.getFacePoints(((PlaceTarget)object2).getFacing(), ((PlaceTarget)object2).getPosition(), f2, f3 = f, bl = placeMode == PlaceMode.GRIM)) == null) continue;
            return new BlockHitResult((Vec3d)object3, ((PlaceTarget)object2).getFacing(), ((PlaceTarget)object2).getPosition(), false);
        }
        return null;
    }

    public static BlockHitResult calcPlaceData$default(PlaceManager placeManager, BlockPos blockPos, int n, long l, PlaceMode placeMode, EntityCheck entityCheck, float f, float f2, List list, Object object) {
        long l2 = (l = a ^ l) ^ 0x72557D85D54DL;
        list = new ArrayList();
        return placeManager.calcPlaceData(blockPos, l2, n, placeMode, entityCheck, f, f2, list);
    }

    /*
     * Unable to fully structure code
     */
    @NotNull
    public final List getSupportBlocks(@NotNull BlockPos var1_1, @NotNull List var2_2) {
        Intrinsics.checkNotNullParameter(var1_1, "bp");
        Intrinsics.checkNotNullParameter(var2_2, "calcGhost");
        var10_4 = var8_3 = PlaceManager.s;
        var11_5 = new ArrayList<E>();
        var13_6 = var10_4;
        var15_7 = var13_6.entrySet().iterator();
        while (var15_7.hasNext()) {
            var19_10 = var17_9 = (var16_8 = var15_7.next());
            var21_11 = (Direction)var19_10.getKey();
            var22_12 = (BlockPos)var19_10.getValue();
            Intrinsics.checkNotNullExpressionValue(var1_1.add(var22_12), "offset(...)");
            if (!Globals.getWorld().getBlockState(var23_13).isReplaceable()) ** GOTO lbl-1000
            if (PlaceManager.y.containsKey(var23_13) || var2_2.contains(var23_13)) lbl-1000:
            // 2 sources

            {
                v0 = new PlaceTarget(var23_13, var21_11);
            } else {
                v0 = null;
            }
            if (v0 == null) continue;
            var24_14 = v0;
            var11_5.add(var24_14);
        }
        return CollectionsKt.toMutableList((List)var11_5);
    }

    @Nullable
    public final PlaceTarget clearPending(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "blockPos");
        Map map = s;
        Iterator iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            BlockPos blockPos2;
            Map.Entry entry;
            Map.Entry entry2 = entry = iterator2.next();
            Direction direction = (Direction)entry2.getKey();
            BlockPos blockPos3 = (BlockPos)entry2.getValue();
            Intrinsics.checkNotNullExpressionValue(blockPos.add(blockPos3), "offset(...)");
            if (Globals.getWorld().getBlockState(blockPos2).isReplaceable()) continue;
            return new PlaceTarget(blockPos2, direction);
        }
        return null;
    }

    public final float squaredDistanceToEyes(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "vec");
        return (float)vec3d.squaredDistanceTo(Globals.getPlayer().getEyePos());
    }

    public final float horizontalDistanceSq(@NotNull Vec3d vec3d) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)PlaceManager.zkmBootstrap("e", (int)28439, (long)(0x3C4D55962E3356F4L ^ l))));
        double d = vec3d.x - Globals.getPlayer().getX();
        double d2 = vec3d.z - Globals.getPlayer().getZ();
        return (float)(d * d + d2 * d2);
    }

    @NotNull
    public final SideDistances getSideDistances(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Vec3d vec3d = blockPos.toCenterPos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
        Vec3d vec3d2 = vec3d;
        Entity entity = Globals.getPlayer();
        Entity entity2 = Globals.getPlayer();
        Entity entity3 = Globals.getPlayer();
        Entity entity4 = Globals.getPlayer();
        Entity entity5 = Globals.getPlayer();
        Entity entity6 = Globals.getPlayer();
        return new SideDistances(this.getEyePos((Entity)entity).x - vec3d2.add((double)0.5, (double)0.0, (double)0.0).x, this.getEyePos((Entity)entity2).x - vec3d2.add((double)-0.5, (double)0.0, (double)0.0).x, this.getEyePos((Entity)entity3).z - vec3d2.add((double)0.0, (double)0.0, (double)0.5).z, this.getEyePos((Entity)entity4).z - vec3d2.add((double)0.0, (double)0.0, (double)-0.5).z, this.getEyePos((Entity)entity5).y - vec3d2.add((double)0.0, (double)0.5, (double)0.0).y, this.getEyePos((Entity)entity6).y - vec3d2.add((double)0.0, (double)-0.5, (double)0.0).y);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean canPlaceAt(@NotNull BlockPos blockPos) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)368720009466344677L, (long)l);
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)PlaceManager.zkmBootstrap("e", (int)10130, (long)(0x28515E52E26DC3A0L ^ l))));
        CallSite callSite2 = callSite;
        boolean bl = Globals.getWorld().getBlockState(blockPos).isReplaceable();
        if (callSite2 != null) return bl;
        if (!bl) return true;
        bl = y.containsKey(blockPos);
        if (callSite2 != null) return bl;
        if (!bl) return false;
        return true;
    }

    @NotNull
    public final List getPlaceSides(int n, @NotNull BlockPos blockPos, int n2) {
        List list;
        block41: {
            CallSite callSite;
            double d;
            block39: {
                CallSite callSite2;
                block40: {
                    block38: {
                        block36: {
                            double d2;
                            SideDistances sideDistances;
                            block37: {
                                CallSite callSite3;
                                block35: {
                                    block33: {
                                        double d3;
                                        block34: {
                                            CallSite callSite4;
                                            block32: {
                                                block30: {
                                                    double d4;
                                                    block31: {
                                                        CallSite callSite5;
                                                        block29: {
                                                            block27: {
                                                                double d5;
                                                                block28: {
                                                                    CallSite callSite6;
                                                                    block26: {
                                                                        block24: {
                                                                            double d6;
                                                                            block25: {
                                                                                long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
                                                                                CallSite callSite7 = ZkmReferences.zkmBootstrap("\u00c5", (long)-8851913438822435617L, (long)l);
                                                                                Intrinsics.checkNotNullParameter(blockPos, (String)((Object)PlaceManager.zkmBootstrap("e", (int)10130, (long)(0x28510517BBC7439AL ^ l))));
                                                                                callSite2 = callSite7;
                                                                                list = new ArrayList();
                                                                                sideDistances = this.getSideDistances(blockPos);
                                                                                double d7 = sideDistances.getWestDelta() - 0.0;
                                                                                d = d7 == 0.0 ? 0 : (d7 > 0.0 ? 1 : -1);
                                                                                if (callSite2 != null) break block24;
                                                                                if (d <= 0) break block25;
                                                                                d = (double)Globals.getWorld().getBlockState(blockPos.east()).isReplaceable();
                                                                                callSite6 = callSite2;
                                                                                if (n <= 0) break block26;
                                                                                if (callSite6 != null) break block24;
                                                                                if (d != false) {
                                                                                    list.add(Direction.EAST);
                                                                                }
                                                                            }
                                                                            d = (d6 = sideDistances.getEastDelta() - 0.0) == 0.0 ? 0 : (d6 < 0.0 ? -1 : 1);
                                                                        }
                                                                        callSite6 = callSite2;
                                                                    }
                                                                    if (callSite6 != null) break block27;
                                                                    if (d >= 0) break block28;
                                                                    d = (double)Globals.getWorld().getBlockState(blockPos.west()).isReplaceable();
                                                                    callSite5 = callSite2;
                                                                    if (n2 >= 0) break block29;
                                                                    if (callSite5 != null) break block27;
                                                                    if (d != false) {
                                                                        list.add(Direction.WEST);
                                                                    }
                                                                }
                                                                d = (d5 = sideDistances.getNorthDelta() - 0.0) == 0.0 ? 0 : (d5 > 0.0 ? 1 : -1);
                                                            }
                                                            callSite5 = callSite2;
                                                        }
                                                        if (callSite5 != null) break block30;
                                                        if (d <= 0) break block31;
                                                        d = (double)Globals.getWorld().getBlockState(blockPos.south()).isReplaceable();
                                                        callSite4 = callSite2;
                                                        if (n2 > 0) break block32;
                                                        if (callSite4 != null) break block30;
                                                        if (d != false) {
                                                            list.add(Direction.SOUTH);
                                                        }
                                                    }
                                                    d = (d4 = sideDistances.getSouthDelta() - 0.0) == 0.0 ? 0 : (d4 < 0.0 ? -1 : 1);
                                                }
                                                callSite4 = callSite2;
                                            }
                                            if (callSite4 != null) break block33;
                                            if (d >= 0) break block34;
                                            d = (double)Globals.getWorld().getBlockState(blockPos.north()).isReplaceable();
                                            callSite3 = callSite2;
                                            if (n <= 0) break block35;
                                            if (callSite3 != null) break block33;
                                            if (d != false) {
                                                list.add(Direction.NORTH);
                                            }
                                        }
                                        d = (d3 = sideDistances.getUpDelta() - 0.0) == 0.0 ? 0 : (d3 > 0.0 ? 1 : -1);
                                    }
                                    callSite3 = callSite2;
                                }
                                if (callSite3 != null) break block36;
                                if (d <= 0) break block37;
                                d = (double)Globals.getWorld().getBlockState(blockPos.up()).isReplaceable();
                                callSite = callSite2;
                                if (n2 > 0) break block38;
                                if (callSite != null) break block36;
                                if (d != false) {
                                    list.add(Direction.UP);
                                }
                            }
                            d = (d2 = sideDistances.getDownDelta() - 0.0) == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
                        }
                        callSite = callSite2;
                    }
                    if (n < 0) break block39;
                    if (callSite != null) break block40;
                    if (d >= 0) break block41;
                    d = (double)Globals.getWorld().getBlockState(blockPos.down()).isReplaceable();
                }
                callSite = callSite2;
            }
            if (callSite == null && d != false) {
                d = (double)list.add(Direction.DOWN);
            }
        }
        return list;
    }

    @Nullable
    public final DirectionVector getVanillaSide(@NotNull BlockPos blockPos, @NotNull PlaceMode placeMode) {
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Intrinsics.checkNotNullParameter((Object)placeMode, "interact");
        if (placeMode == PlaceMode.Vanilla) {
            Vec3d vec3d = blockPos.toCenterPos().add(0.0, 0.5, 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            return new DirectionVector(Direction.UP, vec3d);
        }
        if (placeMode == PlaceMode.Strict || placeMode == PlaceMode.GRIM) {
            float f = 999.0f;
            Direction direction = Direction.UP;
            Vec3d vec3d = null;
            for (Direction direction2 : PlaceManager$EntriesMappings.enumEntries) {
                Vec3d vec3d2;
                double d = (double)direction2.getVector().getX() * 0.5;
                double d2 = (double)direction2.getVector().getY() * 0.5;
                double d3 = (double)direction2.getVector().getZ() * 0.5;
                Intrinsics.checkNotNullExpressionValue(blockPos.toCenterPos().add(Math.abs(d) == 0.5 ? d : RandomUtil.k(RangesKt.rangeTo(-0.1, 0.1), false, null), Math.abs(d2) == 0.5 ? d2 : RandomUtil.k(RangesKt.rangeTo(-0.1, 0.1), false, null), Math.abs(d3) == 0.5 ? d3 : RandomUtil.k(RangesKt.rangeTo(-0.1, 0.1), false, null)), "add(...)");
                float f2 = this.squaredDistanceToEyes(vec3d2);
                if (!(f > f2)) continue;
                direction = direction2;
                vec3d = vec3d2;
                f = f2;
            }
            if (vec3d == null) {
                return null;
            }
            return new DirectionVector(direction, vec3d);
        }
        return null;
    }

    @Nullable
    public final Vec3d getFacePoints(@NotNull Direction direction, @NotNull BlockPos blockPos, float f, float f2, boolean bl) {
        Vec3d vec3d;
        Vec3d vec3d2;
        double d;
        double d2;
        Intrinsics.checkNotNullParameter(direction, "dir");
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Box box = this.getFaceBox(direction);
        List list = new ArrayList();
        if (box.maxX - box.minX == 0.0) {
            for (d2 = box.minY; d2 < box.maxY; d2 += 0.1) {
                for (d = box.minZ; d < box.maxZ; d += 0.1) {
                    vec3d2 = new Vec3d((double)blockPos.getX() + box.minX, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d);
                    if (this.raycast(vec3d2, blockPos, f, f2)) continue;
                    ((Collection)list).add(vec3d2);
                }
            }
        }
        if (box.maxY - box.minY == 0.0) {
            for (d2 = box.minX; d2 < box.maxX; d2 += 0.1) {
                for (d = box.minZ; d < box.maxZ; d += 0.1) {
                    vec3d2 = new Vec3d((double)blockPos.getX() + d2, (double)blockPos.getY() + box.minY, (double)blockPos.getZ() + d);
                    if (this.raycast(vec3d2, blockPos, f, f2)) continue;
                    ((Collection)list).add(vec3d2);
                }
            }
        }
        if (box.maxZ - box.minZ == 0.0) {
            for (d2 = box.minX; d2 < box.maxX; d2 += 0.1) {
                for (d = box.minY; d < box.maxY; d += 0.1) {
                    vec3d2 = new Vec3d((double)blockPos.getX() + d2, (double)blockPos.getY() + d, (double)blockPos.getZ() + box.minZ);
                    if (this.raycast(vec3d2, blockPos, f, f2)) continue;
                    ((Collection)list).add(vec3d2);
                }
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        if (bl) {
            vec3d = (Vec3d)CollectionsKt.random(list, Random.Default);
        } else {
            Object t;
            Iterable iterable = list;
            Iterator iterator2 = iterable.iterator();
            if (!iterator2.hasNext()) {
                throw new NoSuchElementException();
            }
            Object t2 = iterator2.next();
            if (!iterator2.hasNext()) {
                t = t2;
            } else {
                vec3d2 = (Vec3d)t2;
                double d3 = Globals.getPlayer().getEyePos().squaredDistanceTo(vec3d2);
                do {
                    Object t4 = iterator2.next();
                    Vec3d vec3d3 = (Vec3d)t4;
                    double d4 = Globals.getPlayer().getEyePos().squaredDistanceTo(vec3d3);
                    if (Double.compare(d3, d4) <= 0) continue;
                    t2 = t4;
                    d3 = d4;
                } while (iterator2.hasNext());
                t = t2;
            }
            vec3d = (Vec3d)t;
        }
        return vec3d;
    }

    @NotNull
    public final Box getFaceBox(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter(direction, "dir");
        return switch (PlaceManager$WhenMappings.c[direction.ordinal()]) {
            case 1 -> new Box(0.15, 0.0, 0.15, 0.85, 0.0, 0.85);
            case 2 -> new Box(0.15, 1.0, 0.15, 0.85, 1.0, 0.85);
            case 3 -> new Box(0.15, 0.15, 0.0, 0.85, 0.85, 0.0);
            case 4 -> new Box(0.15, 0.15, 1.0, 0.85, 0.85, 1.0);
            case 5 -> new Box(0.0, 0.15, 0.15, 0.0, 0.85, 0.85);
            case 6 -> new Box(1.0, 0.15, 0.15, 1.0, 0.85, 0.85);
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    public final boolean raycast(@NotNull Vec3d vec3d, @NotNull BlockPos blockPos, float f, float f2) {
        Intrinsics.checkNotNullParameter(vec3d, "point");
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        Entity entity = Globals.getPlayer();
        RaycastContext raycastContext = new RaycastContext(this.getEyePos(entity), vec3d, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer());
        BlockHitResult blockHitResult = Globals.getWorld().raycast(raycastContext);
        Intrinsics.checkNotNullExpressionValue(blockHitResult, "clip(...)");
        BlockHitResult blockHitResult2 = blockHitResult;
        float f3 = this.squaredDistanceToEyes(vec3d);
        if (blockHitResult2.getType() == HitResult.Type.BLOCK && !Intrinsics.areEqual(blockHitResult2.getBlockPos(), blockPos) && f3 > f * f) {
            return true;
        }
        return f3 > f2 * f2;
    }

    public final boolean isInteractable(@NotNull Block block) {
        Intrinsics.checkNotNullParameter(block, "b");
        return block instanceof BlockWithEntity || Intrinsics.areEqual(block, Blocks.CRAFTING_TABLE) || block instanceof TrapdoorBlock || block instanceof LeverBlock || block instanceof ButtonBlock || block instanceof DoorBlock;
    }

    public final boolean getYawTo(@NotNull Vec3d vec3d, int n) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)PlaceManager.zkmBootstrap("e", (int)16388, (long)(0x536034BD8B9560AL ^ l))));
        double d = vec3d.getX() - Globals.getPlayer().getX();
        double d2 = vec3d.getZ() - Globals.getPlayer().getZ();
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8590215962161612494L, (long)l);
        float f = MathHelper.wrapDegrees((float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(d2, d)) - 90.0) - MathHelper.wrapDegrees(Globals.getPlayer().getYaw()));
        double d3 = Math.abs((double)f) - (double)n;
        double d4 = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
        if (callSite == null) {
            d4 = d4 <= 0 ? 1.0 : 0.0;
        }
        return (boolean)d4;
    }

    @NotNull
    public final BlockHitResult raycastAgainst(@NotNull RaycastContext raycastContext, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        Intrinsics.checkNotNullParameter(raycastContext, "context");
        Intrinsics.checkNotNullParameter(blockPos, "block");
        Intrinsics.checkNotNullParameter(blockState, "state");
        BlockHitResult blockHitResult = BlockView.raycast(raycastContext.getStart(), raycastContext.getEnd(), raycastContext, (arg_0, arg_1) -> PlaceManager.raycastWithOverride(blockPos, blockState, arg_0, arg_1), PlaceManager::createMissed);
        Intrinsics.checkNotNullExpressionValue(blockHitResult, "traverseBlocks(...)");
        return blockHitResult;
    }

    public static BlockHitResult raycastAgainst$default(PlaceManager placeManager, RaycastContext raycastContext, BlockPos blockPos, BlockState blockState, Object object) {
        BlockState blockState2 = Blocks.OBSIDIAN.getDefaultState();
        Intrinsics.checkNotNullExpressionValue(blockState2, "defaultBlockState(...)");
        blockState = blockState2;
        return placeManager.raycastAgainst(raycastContext, blockPos, blockState);
    }

    public final boolean canHitEntity(@NotNull Entity entity, @NotNull Rotation rotation, float f, boolean bl) {
        HitResult hitResult;
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(rotation, "rotation");
        double d = f;
        double d2 = MathHelper.square(d);
        Vec3d vec3d = EntityUtil.getPos(Globals.getPlayer()).add(0.0, Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        double d3 = 0.0;
        if (bl) {
            hitResult = MovementUtil.INSTANCE.raycastFromEyes(f, rotation.getYaw(), rotation.getPitch());
            d3 = hitResult.getPos().squaredDistanceTo(vec3d2);
            if (hitResult.getType() != HitResult.Type.MISS) {
                d2 = d3;
                d = Math.sqrt(d3);
            }
        } else {
            hitResult = null;
        }
        Vec3d vec3d3 = MovementUtil.INSTANCE.getLookVector(rotation.getPitch(), rotation.getYaw());
        Vec3d vec3d4 = vec3d2.add(vec3d3.x * d, vec3d3.y * d, vec3d3.z * d);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        Box box = Globals.getPlayer().getBoundingBox().stretch(vec3d3.multiply(d)).expand(1.0, 1.0, 1.0);
        Intrinsics.checkNotNullExpressionValue(box, "inflate(...)");
        Box box2 = box;
        EntityHitResult entityHitResult = ProjectileUtil.raycast(Globals.getPlayer(), vec3d2, vec3d5, box2, arg_0 -> PlaceManager.isSameTarget(entity, arg_0), d2);
        if (entityHitResult == null) {
            return false;
        }
        HitResult hitResult2 = this.isWithinRange(hitResult == null || entityHitResult.getPos().squaredDistanceTo(vec3d2) < d3 ? (HitResult)entityHitResult : hitResult, vec3d2, f);
        return hitResult2 instanceof EntityHitResult && Intrinsics.areEqual(((EntityHitResult)hitResult2).getEntity(), entity);
    }

    private final HitResult isWithinRange(HitResult hitResult, Vec3d vec3d, float f) {
        if (!hitResult.getPos().isInRange(vec3d, f)) {
            Vec3d vec3d2 = hitResult.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d2, "getLocation(...)");
            Vec3d vec3d3 = vec3d2;
            BlockHitResult blockHitResult = BlockHitResult.createMissed(vec3d3, Direction.getFacing(vec3d3.x - vec3d.x, vec3d3.y - vec3d.y, vec3d3.z - vec3d.z), BlockPos.ofFloored(vec3d3));
            Intrinsics.checkNotNullExpressionValue(blockHitResult, "miss(...)");
            return blockHitResult;
        }
        return hitResult;
    }

    @NotNull
    public final List findReachableBlocks() {
        Iterable iterable;
        BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getEyePos());
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n = (int)Math.ceil(Globals.getPlayer().getBlockInteractionRange());
        Iterable iterable2 = iterable = (Iterable)new IntRange(blockPos2.getX() - n, blockPos2.getX() + n);
        Collection collection = new ArrayList();
        Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            Iterable iterable3;
            int n2;
            int n3 = n2 = ((IntIterator)iterator2).nextInt();
            Iterable iterable4 = iterable3 = (Iterable)new IntRange(blockPos2.getY() - n, blockPos2.getY() + n);
            Collection collection2 = new ArrayList();
            Iterator iterator3 = iterable4.iterator();
            while (iterator3.hasNext()) {
                Iterable iterable5;
                int n4;
                int n5 = n4 = ((IntIterator)iterator3).nextInt();
                Iterable iterable6 = iterable5 = (Iterable)new IntRange(blockPos2.getZ() - n, blockPos2.getZ() + n);
                Collection collection3 = new ArrayList();
                Iterable iterable7 = iterable6;
                Iterator iterator4 = iterable7.iterator();
                while (iterator4.hasNext()) {
                    int n6;
                    int n7;
                    int n8 = n7 = (n6 = ((IntIterator)iterator4).nextInt());
                    BlockPos blockPos3 = new BlockPos(n3, n5, n8);
                    collection3.add(blockPos3);
                }
                Iterable iterable8 = (List)collection3;
                CollectionsKt.addAll(collection2, iterable8);
            }
            Iterable iterable9 = (List)collection2;
            CollectionsKt.addAll(collection, iterable9);
        }
        return (List)collection;
    }

    @NotNull
    public final Rotation getYawForDirection(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        float f = switch (PlaceManager$WhenMappings.c[direction.ordinal()]) {
            case 3 -> 180.0f;
            case 4 -> 0.0f;
            case 5 -> 90.0f;
            case 6 -> -90.0f;
            default -> 0.0f;
        };
        float f2 = switch (PlaceManager$WhenMappings.c[direction.ordinal()]) {
            case 2 -> -88.0f;
            case 1 -> 88.0f;
            default -> -10.0f;
        };
        int n = (int)(MovementUtil.INSTANCE.getLastYaw() / 360.0f);
        return new Rotation(f + (float)(n * 360), f2, false, null, 12, null);
    }

    private static final Packet createPlacePacket(BlockHitResult blockHitResult, int n) {
        return new PlayerInteractBlockC2SPacket(Hand.MAIN_HAND, blockHitResult, n);
    }

    private static final Unit place$lambda$0(int n, SwitchMode switchMode, BlockHitResult blockHitResult, InteractMode interactMode, BlockPos blockPos) {
        boolean bl;
        int n2 = Globals.getPlayer().getInventory().getSelectedSlot();
        if (n != -1) {
            switch (PlaceManager$WhenMappings.L[switchMode.ordinal()]) {
                case 1: 
                case 2: {
                    InventoryUtil.INSTANCE.selectSlot(n);
                    break;
                }
                case 3: {
                    InventoryUtil.INSTANCE.clickSlot(n);
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
        Block block = Globals.getWorld().getBlockState(blockHitResult.getBlockPos()).getBlock();
        Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
        boolean bl2 = bl = INSTANCE.isInteractable(block) && !Globals.getPlayer().isSneaking();
        if (bl) {
            PlacementHelper.INSTANCE.setViaVersionFix(true);
        }
        if (interactMode == InteractMode.Normal) {
            ActionResult actionResult = Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
            v3 = actionResult;
            Intrinsics.checkNotNullExpressionValue(actionResult, "useItemOn(...)");
        } else {
            INSTANCE.sendSequenced(arg_0 -> PlaceManager.createPlacePacket(blockHitResult, arg_0));
            v3 = Unit.INSTANCE;
        }
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        y.put(blockPos, Globals.now());
        if (bl) {
            PlacementHelper.INSTANCE.setViaVersionFix(false);
        }
        if (n2 != -1) {
            switch (PlaceManager$WhenMappings.L[switchMode.ordinal()]) {
                case 2: {
                    InventoryUtil.INSTANCE.selectSlot(n2);
                    break;
                }
                case 3: {
                    InventoryUtil.INSTANCE.clickSlot(n);
                    break;
                }
            }
        }
        return Unit.INSTANCE;
    }

    private static final boolean calcPlaceData$lambda$0(PlaceTarget placeTarget) {
        Intrinsics.checkNotNullParameter(placeTarget, "it");
        return placeTarget.getFacing() == Direction.UP || placeTarget.getFacing() == Direction.DOWN;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean calcPlaceData$lambda$2(ArrayList arrayList, PlaceTarget placeTarget) {
        boolean bl;
        block3: {
            Intrinsics.checkNotNullParameter(placeTarget, "s");
            Iterable iterable = arrayList;
            if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                bl = false;
            } else {
                for (Object t : iterable) {
                    Entity entity = (Entity)t;
                    if (!(entity.squaredDistanceTo(placeTarget.getPosition().toCenterPos().offset(placeTarget.getFacing(), 0.5)) < 0.5)) continue;
                    bl = true;
                    break block3;
                }
                bl = false;
            }
        }
        return bl;
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final BlockHitResult raycastWithOverride(BlockPos blockPos, BlockState blockState, RaycastContext raycastContext, BlockPos blockPos2) {
        Intrinsics.checkNotNullParameter(raycastContext, "rayCastContext");
        Intrinsics.checkNotNullParameter(blockPos2, "blockPos");
        Object object = !blockPos2.equals(blockPos) ? Blocks.AIR.getDefaultState() : blockState;
        Intrinsics.checkNotNull(object);
        BlockState blockState2 = object;
        Vec3d vec3d = raycastContext.getStart();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getFrom(...)");
        object = vec3d;
        Vec3d vec3d2 = raycastContext.getEnd();
        Intrinsics.checkNotNullExpressionValue(vec3d2, "getTo(...)");
        Vec3d vec3d3 = vec3d2;
        VoxelShape voxelShape = raycastContext.getBlockShape(blockState2, Globals.getWorld(), blockPos2);
        Intrinsics.checkNotNullExpressionValue(voxelShape, "getBlockShape(...)");
        VoxelShape voxelShape2 = voxelShape;
        BlockHitResult blockHitResult = Globals.getWorld().raycastBlock((Vec3d)object, vec3d3, blockPos2, voxelShape2, blockState2);
        VoxelShape voxelShape3 = VoxelShapes.empty();
        Intrinsics.checkNotNullExpressionValue(voxelShape3, "empty(...)");
        VoxelShape voxelShape4 = voxelShape3;
        BlockHitResult blockHitResult2 = voxelShape4.raycast((Vec3d)object, vec3d3, blockPos2);
        double d = blockHitResult == null ? Double.MAX_VALUE : raycastContext.getStart().squaredDistanceTo(blockHitResult.getPos());
        double d2 = blockHitResult2 == null ? Double.MAX_VALUE : raycastContext.getStart().squaredDistanceTo(blockHitResult2.getPos());
        return d <= d2 ? blockHitResult : blockHitResult2;
    }

    private static final BlockHitResult createMissed(RaycastContext raycastContext) {
        Intrinsics.checkNotNullParameter(raycastContext, "it");
        Vec3d vec3d = raycastContext.getStart().subtract(raycastContext.getEnd());
        Intrinsics.checkNotNullExpressionValue(vec3d, "subtract(...)");
        Vec3d vec3d2 = vec3d;
        return BlockHitResult.createMissed(raycastContext.getEnd(), Direction.getFacing(vec3d2.x, vec3d2.y, vec3d2.z), BlockPos.ofFloored(raycastContext.getEnd()));
    }

    private static final boolean isSameTarget(Entity entity, Entity entity2) {
        Intrinsics.checkNotNullParameter(entity2, "it");
        return entity2.canHit() && Intrinsics.areEqual(entity2, entity);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PlaceManager.a = ZkmKeys.a(-7212214856345696819L, 378907001664476378L, MethodHandles.lookup().lookupClass()).a(242930208664616L);
                        var20 = PlaceManager.a ^ 58458194141647L;
                        PlaceManager.d = new HashMap<K, V>(13);
                        PlaceManager.zkmSetFlag(new int[2]);
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
                        var15_5 = "\u00fbg|\u0019 \u00d6\u00dc'\\\u00de\u0011\u0096A0\u00b5!\u00d1\u00af\u0013\u00a7\u00ccRJ9\u0088!]Vv\u00f7w\u0093\u0010\u000f\u00cc0\u0091\u00eb\u0083\u00df\u00c5\u00a0\u00fdC\u00db;\u00c4\u00db\u00d1\u0010<\u00ed\u009b\u00e7\fjD\u00fa\u00b4\u00b5\u0010h\u0096u\u00e64 \u009dS?4\f\u009a\u0099x\u00ecmE\u0017%\u00a9\u00bb\u00e9\u00b3\u001bIm\u00baHl\u00e2Z\u00f4\u00bb\u00e0\u00cd\u00b4\u00e1\u00ce(\"\u00d6\bg\u00aa\u00b94\u0015\u0018CbN;!Ql\u00d1\u0082\u00f9u_cl\u00b8\u00f1\u00b1Y\u00db\u00aaFN\u0019\u0005\u00f2\u00bb\u0000H\"H-\u0010\f\u00b0\u00ac\u00eeM\u0093\u00bd\u009b\b\u0004)\u00fc\u00e2\u001c6\u00f2\u0018\u00ce\u009d\u000f\u00e8\u00af\u00ba]LD\u009d\u007f\u00a2\u007f\u00b4\u00d3[m\u00da5\n\u0085+$\u009a \u009e\u008e\u00f2\\\u000e\u00e1\u00c6\u00d0\u00fdG\u00ae\u000e}&?\u009e\u00a1\u00d0\u001f\u00ca\u0001$ \u008e\u00d4\u0080\u00bbq\u00d8\u00cdS\u00f5\u0018o7\u0015I\u008ex\u00a1\u0006q\u008e\u00bd6\u00ce\u00f4\u00fe\u0019\u009c2\u00a1\u0010&AL\u00aa(\u00e4\u00da\u00a6 \u00f4\u0012a,\u00aa\u00ee\u00b6\u0084\u00f6\u00b5\rA\n\u00d2\u00ff\u0016\u00e6\u008b\u00dd\u00148\u009dE\u00e0\u00e8$\u00bd\u00a9\u0092\u00d6D\u008d\u0000\u0082\u00a7\n\u0010\u00a4 E)\u0001;\u0084i7\u00bf\u008c\u00d4\u00bb79\u00a6\u0010\u008f\u00e6&\u0006,\u00c6s\u00ce9\u00b2\u00dd_\"\u00ca\u0083\u00d1 \u00a6\rF\u00fe\u0090l\\#Y{\u0018\u0094\u00a64H\u0091\u00b1\u009f7]\u00fb\u00b4\u00d3)\u00f3]\u0013\u0010\u00c7\u00b2-\u00b7\u0018\u0081\u008an)\u0019\u00dc\u00c8\u008c\nh\u00b6%,6\u00caq\u00004}\u009b/\u00c9\u00c5\u00bc \u0093\u00f6\u00aa\u00abS\"\u0099x\u0017\u007f4\u0088\u001a \u0015p\u00fa\n{\u001e\u00ad2\u00be\u00d0I\u00d0:\u00ddU\u008f\u00a9\u00f8 \u00f9s\u00cf~\u008cL\u00aft\u008b\u00bb\u00fd{)\u00a8x\u0014\\\u00d0N\u0005{z!\u00d3f}(\u00ad\u0010\u0014Y\u0098\u0010\u0091\\ZE\u00e7\u00fd]j\u00f3\u00dcf\u000b\u00b7(\u00f8x +^b\u00d6\u00ac\u0088\u00c2\u009ds\u00c0\u00edU\u0016\u00ef!\u0083,=\u0016\u00aa\u00f3\u00c72\n\u0097K\u0084\u00f7/\u00a3\u00f7\f(\u00df\u00d3C\u00e6\u0083q\u0010\u00e8\u00f8\u0083\u008d\u001dD\u00d0*\u00bb\u0004\u0083Zr\u00e6\u000bM\\D\u00b2Zz\u0085f!\u00c6\u00e6hf?o\u00eaE[ \u009c\u00a0yL\u001f\u00c4i\u0086~\u00c6V\u00a3\u00fe\u00be\u0001\u0000\u00e1o\u00e4\u00a02\u00cc\u00ab)\u0081m\u0003\u0005\u0011yI\u00a2\u0010A\u00e3\u007f\u00ac:\u0002\u00af}\u00b0\u0019\u00a5c\u0013\u0002\u0006x(\u00cf\u00bf\u00b0}\u00dd\u001aR\u00b9s\u00b6\u00a3\u00aae\u00c8JWk\u00d8\u008b\u00c6i\u00ce\u0016z\u000b\u0002\u001b\u00e5F\u00a0\u0019*\u00cb\u00c2/1\f\u00f22\u0017\u0010\u00eaE\u00b4\u008dx..`.X{\u0018D\u0017\u00e5\u0004\u0010\u0000\u00148\u00ee\u00f2\u00d2!]\u00da\u000f\u00e8\u00bc\u00c8\u00e7fS\u0010E\u00d2\u0017\u00e2\u00a5jv+/\u008aa\u00d6\u0085S\u00cc% \u00c5\u0094\u00dd*\u00c0{\u00c1\u00a8\u00cc\r\u00e7\u00d5R\u0091\u0086\u00a7\u00ba\u00d2\u00e2pa\u00e6*\u00f8\u00b8\u00aa\u00f1\u00a6\u00aaV\u00d7\u00eb\u0010\u0002\u00f4\u00afM\u00ee\u00b8\n\u0014 y\u008f\u008a\u00b5\u00935\u009e(\u00d3\u0019\u0006\u0019%0\u00d6\u0013\u00c3\u00b3\u00e9\u00eb\u00b3w=.\u0096u@\u0000\u00b82\u0087\u0093\u00c3\u009e\u00f9\u00e6r,\u00f2\u00dbL\u00953HN]\u001a\u00ed\u0018,\u00fc\u008c\u00aa\u00b4\u009d\u00e5E\u0088\u00e1\u009d\u00ba\u009b\u0001^\t\u00cai\u00b6)\u00f1\u009d\u0087q\u0010\u00ef\u00d9\u0002\u00df\u00032X\u00a4P\u0093\u00fe\u0001\u009ee\n\u0087(\u00b4^rP\u00de\u00b3#/A\u0005\u00ca\u009e\u0001\u00aa\u00e2yD\u00fa\u00a9\u0006'Q18R'\u00e8\f\u00f8o\u00f4\u00b9\u0017\u00e1\tx\u0094\u00d0\u00b0: \u00a4\tL\u00d6@\u0088\u00e7\u00b0\u0015Y)\u00a7\u0092\u001d\u0092\u00a1>JC\u00a1/\u0016\u00e6\u00af\t\u00c9\u00cd\u00a8\u0095y\u009d\u0086\u0018\u00b8Wg\u0014\u00f1\u001e\u00f2\u00a8\u001cu|\u00b6\u0095wK\u001ak\u0097\u0089\rg\u00b9>9\u0010\u00f7_A\u00e4upz\f\u0003\u00d4\u00bc\u009e2vj)\u0010\u00d21_(\u00aa\u00e7|\u00a4!i\u00acy=k\u0007\u001e\u0010N\u00fd\u00a4\r\u00aee\u00f0GX+a\u00c8%\u00ba\u00f4\u00a3 '\u001b\u00a5\u00fb\u0091\u0086&\u0013b\u0018\u00dd\u00a8gK\f\u00a1\u00c83\u0016\b\u00e8\u00c3\u00b6\u00959\u0094.\u001au\u00c6\u00fcF \u00d7o\u008a\u0001\u00cf\u00d0\u008a\u00aa\u00ac\u001fa\u00b0nbp?\u00cf\u00deT\u00bb]\u00c2\u008e\u00aa\u000f\b\u00cd_]C\u00ea\u00b8\u0010\u0018E\u00dcJ\b\u00de\u00cap\u00f4@u\u00a1\u00f2\u0082\u00af\u00f6(\\\u0091Y\u00ac\u00a1\u008b\u0082dl\u00a3\u00b5\u00be=F\u00ad!Q\u000esW\r\u0012\u009cJ\u00e4\u00d4\u008e\u0014\u00b9\u00fa$f\u00f8\u00b0v\u00f3\u000b\u00c0|\u0006\u0010\u00ba\u0017\u00a5\u00d3<P\u00b2\u00df\u0000\u0097l\u00b3\u00b9\u0000\u0083\\(O\u00b17\u00bb\u0081\u00d6\u0092\u00f6\u0014\u000f/\u00a7\u00dab>\u00af!\u0089\u00da,\u0099\u00a3Ab\u0085:u\u0010v\u00f5\u00e9\u00cf\u0002\u00f9\u00da:\u0001\u0000k\u00b7 \u00cc\u00c6\u009f\u00f3\u00ae\n\u0010C\r1]\u00cb\u000f=\u00b9.\u0016H\u00be\u0094~?U\u00f3\u00c2\u001b\u000ez\u00a0\u00f1\u0016&(B4\u00ec\u00d2A7P$Tx\u0016g3\u00fb?\u0002\u009aG\u00b1b\u00ed\u009a\u00d3\u00e0^\u00da6}p4\u00e7\u00d3O[\u00f5\u00be\u00eaRj\n _\u0001\u001c\u0090!\u0090\b5q`\u00f9\u00e6\u00e6\u0098\u0004\u00ba|?\u00c2\u00e4yUKc\u0091\u009a\u00b8\r\u0080tQ\u00d5\u0018%\u00a4\u00b8\u00b7\u0004\u0004\u00d8\u0085q\u00f3\u00ac\u00b4\u009d\u0004\u00ffl\u00b3\u00ed\u00c0!\u00c3O.\u00d5 \u00f6\u00c7\u00f9\r\u0087G\u00c07\u00f1H\u0019\u00ca/\u00a4\u0093\u0080}\u00fbJ\u009f\u00a1q\u00a06\u0098,\n\u00daI\u00ac\u00b2\u0094 \u001c\u00c8\u00b3\u001c\u0019I\u00efnK\u00cfH\u00fd\u0092,\"lF\u001c8\u00da93l\u00e4nMy\nM_\u008c\u00f6 \u00927\u0083\u00ac\u00a6\u0016V$\u00a4\u00b8\u00e6\u0089\u00a8\u009dt\u0010\u00f3\u00da\u0094\u00c7/\u00a4\u00f6\u000e\u00be\u0083\u00ad\u00d2l\u0090\\L\u0010\u0096x\u00a9B\u000b\u00f3\u00be\u00fdB\u00cbU\n\\\r@\u0002(\u0092d\u00f3oi\u0092Q\u00a2i\u00d1\u00f1\u0018\u009e\u009f\u0096>U\"8wPw\u0095\u000f\u00fc\u0004Z\u001a\u0003\u00da\u00dd'\n\u00d5\u0005\u0083W|.\u00f3\u0018'\u00ceC\u0002\u0013Z]f\u0015\u00c2\u00cff\u00e3\u00aa\u00e5\u00b8\u0089\u00840\u000e\u00c7\u00faez ?#\u000b\u0016d\u00f6\u009c\u0082\u00afHB\u0001G\u0083\\ 8V\u0082\u00c8/\u00a7t\u0082\u00b1\u00f66\u00823=j\u00f0\u0010o\u00d3X\u00e8\f\u00f5\u00d1\u0004XpL?\u00cf\u00b3\u00a4\u00f2";
                        var17_6 = 1501;
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
                            var18_3[var16_4++] = PlaceManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b9\u00cd\u00ea\\O\u00ca\u0092\u00d0\u00e8\u001ez%~>\u00ddu\u0010\u00e7\u00c1-\u0099\u00b3\u0092\u0080\u0010]\u0005\t\u00a4\u00f3\u0011\u00ec\u009b\u00cf\u0086\u00e8.\u001f\u0001\u009b\u00a6";
                            var17_6 = 41;
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
                            var18_3[var16_4++] = PlaceManager.zkmDecodeUtf8(var19_9).intern();
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
                PlaceManager.b = var18_3;
                PlaceManager.c = new String[56];
                PlaceManager.g = new HashMap<K, V>(13);
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
                var6_12 = new long[9];
                var3_13 = 0;
                var4_14 = "?\u00c6\u00c7I\n\u0011\u00e7 \u0092\u00a6\u00e2\u00db\u00eaL\"\u00a8\u008d\u0017)4\u00b2]\u00bd\u00bd\u00016\r>\u0017\tQ\u00b1c \u00f4\u00d2\u00bf\u001a\u00b8}\u00b9\u00df\u0002+\u0080\u00de\u00fc\b.u\u00a3\n5\u00c6\u0010\u00d7";
                var5_15 = 56;
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
                    var4_14 = "Qn#>\u00c8A\u00d8g\\\u00d6_>Hd{\u00c0";
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
        PlaceManager.e = var6_12;
        PlaceManager.f = new Integer[9];
        PlaceManager.INSTANCE = new PlaceManager();
        var22_20 = new Pair[]{TuplesKt.to(Direction.UP, new BlockPos(0, -1, 0)), TuplesKt.to(Direction.DOWN, new BlockPos(0, 1, 0)), TuplesKt.to(Direction.EAST, new BlockPos(-1, 0, 0)), TuplesKt.to(Direction.WEST, new BlockPos(1, 0, 0)), TuplesKt.to(Direction.NORTH, new BlockPos(0, 0, 1)), TuplesKt.to(Direction.SOUTH, new BlockPos(0, 0, -1))};
        PlaceManager.s = MapsKt.mapOf(var22_20);
        PlaceManager.y = new LinkedHashMap<K, V>();
        v15 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v15, "ZERO");
        PlaceManager.vec3d = v15;
    }

    public static void zkmSetFlag(int[] nArray) {
        W = nArray;
    }

    public static int[] zkmGetFlag() {
        return W;
    }

    private static NoSuchElementException zkmRethrow(NoSuchElementException noSuchElementException) {
        return noSuchElementException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6053;
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
                throw new RuntimeException("su/catlean/gw", exception);
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
            PlaceManager.c[n2] = PlaceManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlaceManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x366F;
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
                throw new RuntimeException("su/catlean/gw", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PlaceManager.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PlaceManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/gw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlaceManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PlaceManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

