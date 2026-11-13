/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.awt.Color;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.attack.AutoMine$1;
import su.catlean.module.attack.AutoMine$EntriesMappings;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.player.SpeedMineRebreakMode;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MiningData;
import su.catlean.util.ServerInfo;
import su.catlean.util.TickTimer;
import su.catlean.util.combat.PlacePosition;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoMine
extends Module {
    @NotNull
    public static final AutoMine INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting cevPriority$delegate;
    @NotNull
    private static final BooleanSetting antiShulker$delegate;
    @NotNull
    private static final BooleanSetting eChestMine$delegate;
    @NotNull
    private static final BooleanSetting debug$delegate;
    @NotNull
    private static final BooleanSetting antiCrawl$delegate;
    @NotNull
    private static final BindSetting levelDown$delegate;
    @NotNull
    private static final BooleanSetting cevCrawlers$delegate;
    @NotNull
    private static final BooleanSetting holdPosition$delegate;
    @NotNull
    private static final SettingGroup holdGroup$delegate;
    @NotNull
    private static final BooleanSetting holdCiv$delegate;
    @NotNull
    private static final BooleanSetting holdCev$delegate;
    @NotNull
    private static final BooleanSetting holdSurr$delegate;
    @NotNull
    private static final TickTimer cooldownTimer;
    @NotNull
    private static List targets;
    private static final long c;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private AutoMine() {
        super("auto-mine", Categories.getAttack(), null, null);
    }

    private final boolean getCevPriority() {
        return (Boolean)cevPriority$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getAntiShulker() {
        return (Boolean)antiShulker$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getEChestMine() {
        return (Boolean)eChestMine$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getDebug() {
        return (Boolean)debug$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getAntiCrawl() {
        return (Boolean)antiCrawl$delegate.getValue(this, delegatedProperties[4]);
    }

    private final Bind getLevelDown() {
        return (Bind)levelDown$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getCevCrawlers() {
        return (Boolean)cevCrawlers$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getHoldPosition() {
        return (Boolean)holdPosition$delegate.getValue(this, delegatedProperties[7]);
    }

    private final SettingParent getHoldGroup() {
        return (SettingParent)holdGroup$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getHoldCiv() {
        return (Boolean)holdCiv$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getHoldCev() {
        return (Boolean)holdCev$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getHoldSurr() {
        return (Boolean)holdSurr$delegate.getValue(this, delegatedProperties[11]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Object object;
        Object object2;
        Iterator iterator2;
        Object object3;
        Object object42;
        Object object5;
        Object object6;
        Iterator iterator3;
        Object object72;
        Object object82;
        PlayerEntity playerEntity;
        long l;
        long l2 = l = c ^ 0x22A3DF53117L;
        long l3 = l2 ^ 0x72CD5D526468L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        int n4 = (int)((l2 ^ 0x6E52138AD363L) << 48 >>> 48);
        long l4 = l2 ^ 0x7D7C764C43CDL;
        long l5 = l2 ^ 0x9DD9A4A32FBL;
        long l6 = l2 ^ 0x2C4867E15B5DL;
        int n5 = (int)(l6 >>> 48);
        int n6 = (int)(l6 << 16 >>> 32);
        int n7 = (int)(l6 << 48 >>> 48);
        long l7 = l2 ^ 0x43085E989E60L;
        int n8 = (int)(l7 >>> 32);
        int n9 = (int)(l7 << 32 >>> 48);
        int n10 = (int)(l7 << 48 >>> 48);
        int n11 = (int)((l2 ^ 0x1C1036904EBCL) >>> 48);
        int n12 = (int)((l2 ^ 0x3BFE87B340ECL) << 48 >>> 48);
        targets.clear();
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        Object var61_19 = null;
        if (this.getAntiCrawl() && this.mineSelfTrap((char)n, (short)n2, n3)) {
            return;
        }
        if (this.getEChestMine() && this.mineNearbyEnderChest(n8, n9, (short)n10)) {
            return;
        }
        char c = (char)n4;
        int n13 = this.getLevelDown().getKey();
        if (KeyboardUtil.isKeyPressed(c, n13)) {
            this.mineBlock(l4, blockPos2.down(), true);
            return;
        }
        PlayerEntity playerEntity2 = playerEntity = AutoCrystal.INSTANCE.getTarget() == null ? TargetUtil.Y((char)n5, TargetUtil.INSTANCE, (float)Globals.getPlayer().getBlockInteractionRange(), AutoCrystalTargetSortMode.FOV, false, false, 12, n6, (char)n7, null) : AutoCrystal.INSTANCE.getTarget();
        if (playerEntity == null || !cooldownTimer.hasPassed((short)n11, 60)) {
            return;
        }
        if (this.getAntiShulker() && this.mineNearbyShulker(playerEntity, l5)) {
            return;
        }
        Object object9 = playerEntity;
        BlockPos blockPos3 = BlockPos.ofFloored(this.getPos((Entity)object9));
        Intrinsics.checkNotNullExpressionValue(blockPos3, "containing(...)");
        BlockPos blockPos4 = blockPos3;
        List list = new ArrayList();
        List list2 = new ArrayList();
        Box box = playerEntity.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue(box, "getBoundingBox(...)");
        Box box2 = box;
        BlockPos blockPos5 = BlockPos.ofFloored(box2.minX + 0.001, box2.minY, box2.minZ + 0.001);
        Intrinsics.checkNotNullExpressionValue(blockPos5, "containing(...)");
        list2.add(blockPos5);
        BlockPos blockPos6 = BlockPos.ofFloored(box2.minX + 0.001, box2.minY, box2.maxZ - 0.001);
        Intrinsics.checkNotNullExpressionValue(blockPos6, "containing(...)");
        list2.add(blockPos6);
        BlockPos blockPos7 = BlockPos.ofFloored(box2.maxX - 0.001, box2.minY, box2.maxZ - 0.001);
        Intrinsics.checkNotNullExpressionValue(blockPos7, "containing(...)");
        list2.add(blockPos7);
        BlockPos blockPos8 = BlockPos.ofFloored(box2.maxX - 0.001, box2.minY, box2.minZ + 0.001);
        Intrinsics.checkNotNullExpressionValue(blockPos8, "containing(...)");
        list2.add(blockPos8);
        for (Object object82 : list2) {
            BlockState blockState;
            if (Globals.getPlayer().getEyePos().distanceTo(((BlockPos)object82).toCenterPos()) > Globals.getPlayer().getBlockInteractionRange() || Intrinsics.areEqual(object82, blockPos2) || !Intrinsics.areEqual((blockState = this.isMineable((BlockPos)object82)).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(blockState.getBlock(), Blocks.ENDER_CHEST)) continue;
            list.add(object82);
        }
        BlockState blockState = this.isMineable(blockPos4);
        object82 = new ArrayList();
        int n14 = 0;
        if (!Intrinsics.areEqual(blockState.getBlock(), Blocks.BEDROCK)) {
            object9 = list2;
            if (this.addRebreakPositions(playerEntity, (List)object9)) {
                return;
            }
            object9 = null;
            boolean bl = false;
            List list3 = (List)object82;
            List list4 = list2;
            PlayerEntity playerEntity3 = playerEntity;
            AutoMine autoMine = this;
            AutoMine.findMinePositions$default(autoMine, playerEntity3, list4, list3, bl, object9);
            iterator3 = object72 = (Iterable)AutoMine$EntriesMappings.enumEntries;
            object6 = new ArrayList();
            object5 = iterator3.iterator();
            while (object5.hasNext()) {
                object42 = object5.next();
                object3 = (Direction)object42;
                if (!(object3 != Direction.UP && object3 != Direction.DOWN)) continue;
                object6.add(object42);
            }
            for (Object object72 : (List)object6) {
                Object object10;
                Object object11;
                Object object12;
                float f = 0.0f;
                Intrinsics.checkNotNullExpressionValue(blockPos4.offset((Direction)object72), "relative(...)");
                object6 = this.isMineable((BlockPos)((Object)iterator3));
                if (Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object6).getBlock(), Blocks.AIR)) {
                    ++n14;
                }
                if (!Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object6).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object6).getBlock(), Blocks.ENDER_CHEST) || ((BlockPos)((Object)iterator3)).toCenterPos().distanceTo(Globals.getPlayer().getEyePos()) > Globals.getPlayer().getBlockInteractionRange() || Intrinsics.areEqual(iterator3, blockPos2)) continue;
                BlockPos blockPos9 = ((BlockPos)((Object)iterator3)).down();
                Intrinsics.checkNotNullExpressionValue(blockPos9, "below(...)");
                iterator2 = this.isMineable(blockPos9);
                if (Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)((Object)iterator2)).getBlock(), Blocks.OBSIDIAN) || Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)((Object)iterator2)).getBlock(), Blocks.BEDROCK)) {
                    f = 6.0f;
                }
                for (int i = 1; i < 3; ++i) {
                    BlockPos blockPos10 = ((BlockPos)((Object)iterator3)).down().offset((Direction)object72, i);
                    Intrinsics.checkNotNullExpressionValue(blockPos10, "relative(...)");
                    object42 = this.isMineable(blockPos10);
                    BlockPos blockPos11 = ((BlockPos)((Object)iterator3)).offset((Direction)object72, i);
                    Intrinsics.checkNotNullExpressionValue(blockPos11, "relative(...)");
                    object3 = this.isMineable(blockPos11);
                    if (!Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object42).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object42).getBlock(), Blocks.BEDROCK) || !((AbstractBlock.AbstractBlockState)object3).isAir()) continue;
                    f += 7.5f;
                }
                object2 = object42 = (Iterable)AutoMine$EntriesMappings.enumEntries;
                object = new ArrayList();
                Object object132 = object2.iterator();
                while (object132.hasNext()) {
                    object12 = object132.next();
                    object11 = (Direction)object12;
                    if (!(object11 != Direction.DOWN && object11 != Direction.UP && object11 != object72)) continue;
                    object.add(object12);
                }
                for (Object object42 : (List)object) {
                    Intrinsics.checkNotNullExpressionValue(((BlockPos)((Object)iterator3)).offset((Direction)object72).offset((Direction)object42), "relative(...)");
                    object2 = this.isMineable((BlockPos)object3);
                    BlockPos blockPos12 = ((BlockPos)object3).down();
                    Intrinsics.checkNotNullExpressionValue(blockPos12, "below(...)");
                    object = this.isMineable(blockPos12);
                    BlockPos blockPos13 = ((BlockPos)((Object)iterator3)).offset((Direction)object42);
                    Intrinsics.checkNotNullExpressionValue(blockPos13, "relative(...)");
                    object10 = this.isMineable(blockPos13);
                    if (Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object10).getBlock(), Blocks.OBSIDIAN) || Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object10).getBlock(), Blocks.BEDROCK) || !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object).getBlock(), Blocks.BEDROCK) || !((AbstractBlock.AbstractBlockState)object2).isAir()) continue;
                    f += 9.0f;
                }
                object2 = object42 = (Iterable)AutoMine$EntriesMappings.enumEntries;
                object = new ArrayList();
                object132 = object2.iterator();
                while (object132.hasNext()) {
                    object12 = object132.next();
                    object11 = (Direction)object12;
                    if (!(object11 != Direction.DOWN)) continue;
                    object.add(object12);
                }
                object5 = ((List)object).iterator();
                while (object5.hasNext()) {
                    Direction direction;
                    Object object14;
                    object42 = (Direction)object5.next();
                    Intrinsics.checkNotNullExpressionValue(((BlockPos)((Object)iterator3)).offset((Direction)object42), "relative(...)");
                    if (Intrinsics.areEqual(object3, blockPos4) || Intrinsics.areEqual(object3, blockPos2) || ((BlockPos)object3).toCenterPos().distanceTo(Globals.getPlayer().getEyePos()) > Globals.getPlayer().getBlockInteractionRange()) continue;
                    object2 = this.isMineable((BlockPos)object3);
                    if (((AbstractBlock.AbstractBlockState)object2).isAir()) {
                        BlockPos blockPos14 = ((BlockPos)object3).down();
                        Intrinsics.checkNotNullExpressionValue(blockPos14, "below(...)");
                        object = this.isMineable(blockPos14);
                        BlockPos blockPos15 = ((BlockPos)object3).down(2);
                        Intrinsics.checkNotNullExpressionValue(blockPos15, "below(...)");
                        object10 = this.isMineable(blockPos15);
                        if (!Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object).getBlock(), Blocks.BEDROCK)) continue;
                        f += Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object10).getBlock(), Blocks.OBSIDIAN) || Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object10).getBlock(), Blocks.BEDROCK) ? 10.0f : 9.0f;
                        continue;
                    }
                    if (!Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object2).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object2).getBlock(), Blocks.ENDER_CHEST)) continue;
                    boolean bl2 = true;
                    object11 = object132 = (Iterable)AutoMine$EntriesMappings.enumEntries;
                    List<Direction> list5 = new ArrayList();
                    Iterator iterator4 = object11.iterator();
                    while (iterator4.hasNext()) {
                        object14 = iterator4.next();
                        direction = (Direction)object14;
                        if (!(direction != Direction.DOWN)) continue;
                        list5.add((Direction)object14);
                    }
                    for (Object object132 : (List)list5) {
                        float f2;
                        Intrinsics.checkNotNullExpressionValue(((BlockPos)object3).offset((Direction)object132), "relative(...)");
                        if (Intrinsics.areEqual(object12, blockPos4) || Intrinsics.areEqual(object12, object3) || Intrinsics.areEqual(object12, iterator3) || Intrinsics.areEqual(object12, blockPos2) || ((BlockPos)object12).toCenterPos().distanceTo(Globals.getPlayer().getEyePos()) > Globals.getPlayer().getBlockInteractionRange() || !this.isMineable((BlockPos)object12).isAir()) continue;
                        BlockPos blockPos16 = ((BlockPos)object12).down();
                        Intrinsics.checkNotNullExpressionValue(blockPos16, "below(...)");
                        object11 = this.isMineable(blockPos16);
                        if (!Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object11).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object11).getBlock(), Blocks.BEDROCK)) continue;
                        f += 1.0f;
                        if (!bl2) continue;
                        Object object15 = new Direction[]{Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};
                        list5 = CollectionsKt.mutableListOf(object15);
                        list5.remove(((Direction)object42).getOpposite());
                        Object object16 = object82;
                        Object object17 = object3;
                        if (object42 == Direction.UP) {
                            f2 = 11.0f;
                        } else {
                            Direction direction2;
                            Object object18;
                            Object object19;
                            block58: {
                                object15 = list5;
                                object19 = object17;
                                object18 = object16;
                                object14 = object15.iterator();
                                while (object14.hasNext()) {
                                    BlockPos blockPos17;
                                    BlockPos blockPos18;
                                    Direction direction3 = direction = object14.next();
                                    Intrinsics.checkNotNullExpressionValue(((BlockPos)object3).down().offset(direction3), "relative(...)");
                                    BlockState blockState2 = INSTANCE.isMineable(blockPos18);
                                    Intrinsics.checkNotNullExpressionValue(((BlockPos)object3).offset(direction3), "relative(...)");
                                    BlockState blockState3 = INSTANCE.isMineable(blockPos17);
                                    if (!((Intrinsics.areEqual(blockState2.getBlock(), Blocks.OBSIDIAN) || Intrinsics.areEqual(blockState2.getBlock(), Blocks.BEDROCK)) && blockState3.isAir())) continue;
                                    direction2 = direction;
                                    break block58;
                                }
                                direction2 = null;
                            }
                            Direction direction4 = direction2;
                            object16 = object18;
                            object17 = object19;
                            f2 = direction4 != null ? 16.0f : 11.0f;
                        }
                        float f3 = f2;
                        Object object20 = object17;
                        ((ArrayList)object16).add(new PlacePosition((BlockPos)object20, f3));
                        bl2 = false;
                    }
                }
                ((ArrayList)object82).add(new PlacePosition((BlockPos)((Object)iterator3), f));
            }
        }
        if (n14 > 1) {
            ((ArrayList)object82).clear();
        }
        for (Object object72 : list) {
            ((ArrayList)object82).add(new PlacePosition((BlockPos)object72, 300.0f));
        }
        targets = (List)object82;
        if (SpeedMine.INSTANCE.getEnabled() && SpeedMine.INSTANCE.getDoubleMine()) {
            Object object21;
            Object v28;
            PlacePosition placePosition;
            Object object22;
            boolean bl;
            block59: {
                object72 = (Iterable)object82;
                if (object72 instanceof Collection && ((Collection)object72).isEmpty()) {
                    v24 = false;
                } else {
                    iterator3 = object72.iterator();
                    while (iterator3.hasNext()) {
                        object6 = iterator3.next();
                        iterator2 = (PlacePosition)object6;
                        if (!(((PlacePosition)((Object)iterator2)).getDamage() == 100.0f)) continue;
                        v24 = true;
                        break block59;
                    }
                    v24 = bl = false;
                }
            }
            if (bl) {
                Object object23;
                object6 = object22 = (Iterable)object82;
                iterator2 = new ArrayList();
                object42 = object6.iterator();
                while (object42.hasNext()) {
                    object3 = object42.next();
                    object2 = (PlacePosition)object3;
                    if (!(!(((PlacePosition)object2).getDamage() == 100.0f))) continue;
                    iterator2.add(object3);
                }
                object22 = (List)((Object)iterator2);
                object6 = object22.iterator();
                if (!object6.hasNext()) {
                    object23 = null;
                } else {
                    iterator2 = object6.next();
                    if (!object6.hasNext()) {
                        object23 = iterator2;
                    } else {
                        object5 = (PlacePosition)((Object)iterator2);
                        float f = ((PlacePosition)object5).getDamage();
                        do {
                            PlacePosition placePosition2;
                            float f4;
                            if (Float.compare(f, f4 = (placePosition2 = (PlacePosition)(object42 = object6.next())).getDamage()) >= 0) continue;
                            iterator2 = object42;
                            f = f4;
                        } while (object6.hasNext());
                        object23 = iterator2;
                    }
                }
                placePosition = (PlacePosition)object23;
            } else {
                Iterator iterator5;
                object22 = (Iterable)object82;
                object6 = object22.iterator();
                if (!object6.hasNext()) {
                    iterator5 = null;
                } else {
                    iterator2 = object6.next();
                    if (!object6.hasNext()) {
                        iterator5 = iterator2;
                    } else {
                        object5 = (PlacePosition)((Object)iterator2);
                        float f = ((PlacePosition)object5).getDamage();
                        do {
                            PlacePosition placePosition3;
                            float f5;
                            if (Float.compare(f, f5 = (placePosition3 = (PlacePosition)(object42 = object6.next())).getDamage()) >= 0) continue;
                            iterator2 = object42;
                            f = f5;
                        } while (object6.hasNext());
                        iterator5 = iterator2;
                    }
                }
                placePosition = (PlacePosition)((Object)iterator5);
            }
            object72 = placePosition;
            iterator2 = iterator3 = (Iterable)object82;
            object5 = new ArrayList();
            Iterator<Object> iterator6 = iterator2.iterator();
            while (iterator6.hasNext()) {
                object = object2 = iterator6.next();
                if (!(object72 != null && !Intrinsics.areEqual(object, object72))) continue;
                object5.add(object2);
            }
            iterator3 = (List)object5;
            iterator2 = iterator3.iterator();
            if (!iterator2.hasNext()) {
                v28 = null;
            } else {
                object5 = iterator2.next();
                if (!iterator2.hasNext()) {
                    v28 = object5;
                } else {
                    object42 = (PlacePosition)object5;
                    float f = ((PlacePosition)object42).getDamage();
                    do {
                        PlacePosition placePosition4;
                        float f6;
                        if (Float.compare(f, f6 = (placePosition4 = (PlacePosition)((Object)(iterator6 = iterator2.next()))).getDamage()) >= 0) continue;
                        object5 = iterator6;
                        f = f6;
                    } while (iterator2.hasNext());
                    v28 = object5;
                }
            }
            object22 = v28;
            if (object72 == null) {
                return;
            }
            short s = (short)n12;
            BlockPos blockPos19 = ((PlacePosition)object72).getBlockPos();
            if (!SpeedMine.INSTANCE.isQueued(blockPos19, s) || (object21 = object22) == null) {
                object21 = object72;
            }
            iterator3 = object21;
            this.mineBlock(l4, ((PlacePosition)((Object)iterator3)).getBlockPos(), false);
            v30 = Unit.INSTANCE;
        } else {
            Object object24;
            object72 = (Iterable)object82;
            iterator3 = object72.iterator();
            if (!iterator3.hasNext()) {
                object24 = null;
            } else {
                object6 = iterator3.next();
                if (!iterator3.hasNext()) {
                    object24 = object6;
                } else {
                    iterator2 = (PlacePosition)object6;
                    float f = ((PlacePosition)((Object)iterator2)).getDamage();
                    do {
                        PlacePosition placePosition;
                        float f7;
                        if (Float.compare(f, f7 = (placePosition = (PlacePosition)(object5 = iterator3.next())).getDamage()) >= 0) continue;
                        object6 = object5;
                        f = f7;
                    } while (iterator3.hasNext());
                    object24 = object6;
                }
            }
            PlacePosition placePosition = (PlacePosition)object24;
            if (placePosition != null) {
                PlacePosition placePosition5 = placePosition;
                INSTANCE.mineBlock(l4, placePosition5.getBlockPos(), false);
                v30 = Unit.INSTANCE;
            } else {
                v30 = null;
            }
        }
    }

    private final void findMinePositions(PlayerEntity playerEntity, List list, List list2, boolean bl) {
        if (!playerEntity.isCrawling() || this.getCevCrawlers()) {
            Object object22 = list;
            block0: for (Object object22 : CollectionsKt.sortedWith(object22, new AutoMine$1(playerEntity))) {
                Intrinsics.checkNotNullExpressionValue(((BlockPos)object22).up(2), "above(...)");
                for (int i = 0; i < 4; ++i) {
                    float f;
                    BlockPos blockPos;
                    BlockPos blockPos2;
                    Intrinsics.checkNotNullExpressionValue(blockPos2.up(i), "above(...)");
                    if (!(Globals.getPlayer().getEyePos().distanceTo(blockPos.toCenterPos()) <= Globals.getPlayer().getBlockInteractionRange()) || !bl && !Intrinsics.areEqual(this.isMineable(blockPos).getBlock(), Blocks.OBSIDIAN)) continue;
                    BlockPos blockPos3 = blockPos.up();
                    Intrinsics.checkNotNullExpressionValue(blockPos3, "above(...)");
                    boolean bl2 = this.isMineable(blockPos3).isAir();
                    BlockPos blockPos4 = blockPos.down();
                    Intrinsics.checkNotNullExpressionValue(blockPos4, "below(...)");
                    boolean bl3 = this.isMineable(blockPos4).isAir();
                    if (!bl2 && i == 3) continue block0;
                    boolean bl4 = SpeedMine.INSTANCE.getEnabled() && SpeedMine.INSTANCE.getDoubleMine();
                    float f2 = bl4 ? 100.0f : 99.0f;
                    float f3 = f = bl4 ? 99.0f : 100.0f;
                    list2.add(new PlacePosition(blockPos, this.getCevPriority() ? (bl3 ? f2 : f) : 0.5f));
                }
            }
        }
    }

    static void findMinePositions$default(AutoMine autoMine, PlayerEntity playerEntity, List list, List list2, boolean bl, Object object) {
        bl = false;
        autoMine.findMinePositions(playerEntity, list, list2, bl);
    }

    private final BlockState isMineable(BlockPos blockPos) {
        BlockState blockState = Globals.getWorld().getBlockState(blockPos);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        return blockState;
    }

    private final boolean addRebreakPositions(PlayerEntity playerEntity, List list) {
        Object object;
        Object v0;
        Object object2;
        Object object3;
        Object object42;
        Object object52;
        block11: {
            if (!this.getHoldPosition() || !SpeedMine.INSTANCE.getEnabled() || SpeedMine.INSTANCE.getRebreak() == SpeedMineRebreakMode.OFF) {
                return false;
            }
            object52 = SpeedMine.INSTANCE.getMiningQueue();
            object42 = object52.iterator();
            while (object42.hasNext()) {
                object3 = object42.next();
                object2 = (MiningData)object3;
                if (!(Globals.getWorld().isAir(((MiningData)object2).getPos()) || ((MiningData)object2).u() > 0)) continue;
                v0 = object3;
                break block11;
            }
            v0 = null;
        }
        if ((object = (MiningData)v0) == null || (object = ((MiningData)object).getPos()) == null) {
            return false;
        }
        Object object6 = object;
        if (this.getHoldCev()) {
            boolean bl;
            block12: {
                List list2 = new ArrayList();
                this.findMinePositions(playerEntity, list, list2, true);
                object52 = list2;
                if (object52 instanceof Collection && ((Collection)object52).isEmpty()) {
                    bl = false;
                } else {
                    object42 = object52.iterator();
                    while (object42.hasNext()) {
                        object3 = object42.next();
                        object2 = (PlacePosition)object3;
                        BlockPos blockPos = ((PlacePosition)object2).getBlockPos().down();
                        Intrinsics.checkNotNullExpressionValue(blockPos, "below(...)");
                        if (!(INSTANCE.isMineable(blockPos).isAir() && Intrinsics.areEqual(((PlacePosition)object2).getBlockPos(), object6))) continue;
                        bl = true;
                        break block12;
                    }
                    bl = false;
                }
            }
            if (bl) {
                return true;
            }
        }
        for (Object object52 : list) {
            object2 = object42 = (Iterable)AutoMine$EntriesMappings.enumEntries;
            Collection collection = new ArrayList();
            Iterator iterator2 = object2.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                Direction direction = (Direction)t;
                if (!(direction != Direction.UP && direction != Direction.DOWN)) continue;
                collection.add(t);
            }
            for (Object object42 : (List)collection) {
                Intrinsics.checkNotNullExpressionValue(((BlockPos)object52).offset((Direction)object42), "relative(...)");
                if (list.contains(object3) || (!this.getHoldSurr() || !Intrinsics.areEqual(object6, object3)) && (!this.getHoldCiv() || !Intrinsics.areEqual(object6, ((BlockPos)object3).up()))) continue;
                return true;
            }
        }
        return false;
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = c ^ 0x392C0F2BA39L;
        long l2 = l ^ 0x3ACF5F73E577L;
        if (this.getDebug()) {
            for (PlacePosition placePosition : targets) {
                int n = (int)((double)Globals.now() / 2.0 % 360.0);
                n = n >= 180 ? 360 - n : n;
                Color color = RenderUtil.INSTANCE.blend(RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 0.5f), RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 0.0f), (float)n / 360.0f);
                Object var15_11 = null;
                Color color2 = null;
                Color color3 = color;
                Box box = new Box(placePosition.getBlockPos());
                RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color3, color2, var15_11);
                Vec3d vec3d = placePosition.getBlockPos().toCenterPos();
                Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
                RenderState.drawWorldText$default(RenderState.INSTANCE, l2, String.valueOf(placePosition.getDamage()), vec3d, color, 0.0f, 0.0f, false, null, 120, null);
            }
        }
    }

    private final boolean mineSelfTrap(char c, short s, int n) {
        long l = ((long)c << 48 | (long)s << 48 >>> 16 | (long)n << 32 >>> 32) ^ AutoMine.c;
        long l2 = l ^ 0x6C406EE2C6B2L;
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        if (Globals.getPlayer().getPose() == EntityPose.SWIMMING && !Globals.getPlayer().isTouchingWater() && !Globals.getPlayer().isSubmergedInWater()) {
            BlockPos blockPos3 = blockPos2.up();
            Intrinsics.checkNotNullExpressionValue(blockPos3, "above(...)");
            BlockPos blockPos4 = blockPos3;
            BlockState blockState = Globals.getWorld().getBlockState(blockPos4);
            Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
            BlockState blockState2 = blockState;
            BlockPos blockPos5 = blockPos2.down();
            Intrinsics.checkNotNullExpressionValue(blockPos5, "below(...)");
            BlockPos blockPos6 = blockPos5;
            BlockState blockState3 = Globals.getWorld().getBlockState(blockPos6);
            Intrinsics.checkNotNullExpressionValue(blockState3, "getBlockState(...)");
            BlockState blockState4 = blockState3;
            if (!(Intrinsics.areEqual(blockState2.getBlock(), Blocks.BEDROCK) || !(blockState2.getHardness(Globals.getWorld(), blockPos4) > 0.0f) || Intrinsics.areEqual(blockState2.getBlock(), Blocks.AIR) || Intrinsics.areEqual(blockState2.getBlock(), Blocks.CAVE_AIR) || blockState2.isReplaceable())) {
                this.mineBlock(l2, blockPos4, true);
                return true;
            }
            if (!(Intrinsics.areEqual(blockState4.getBlock(), Blocks.BEDROCK) || !(blockState4.getHardness(Globals.getWorld(), blockPos6) > 0.0f) || Intrinsics.areEqual(blockState4.getBlock(), Blocks.AIR) || Intrinsics.areEqual(blockState4.getBlock(), Blocks.CAVE_AIR) || blockState4.isReplaceable())) {
                this.mineBlock(l2, blockPos6, true);
                return true;
            }
        }
        return false;
    }

    private final boolean mineNearbyEnderChest(int n, int n2, short s) {
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)s << 48 >>> 48) ^ c;
        long l2 = l ^ 0x5D856D283CBAL;
        Iterator iterator2 = ServerInfo.INSTANCE.V().iterator();
        while (iterator2.hasNext()) {
            BlockEntity blockEntity = (BlockEntity)iterator2.next();
            if (Globals.getPlayer().getEyePos().distanceTo(blockEntity.getPos().toCenterPos()) > 2.2 || !(blockEntity instanceof EnderChestBlockEntity)) continue;
            this.mineBlock(l2, ((EnderChestBlockEntity)blockEntity).getPos(), false);
            break;
        }
        return false;
    }

    private final boolean mineNearbyShulker(PlayerEntity playerEntity, long l) {
        long l2 = (l = c ^ l) ^ 0x1750A9FA9021L;
        Iterator iterator2 = ServerInfo.INSTANCE.V().iterator();
        while (iterator2.hasNext()) {
            BlockEntity blockEntity = (BlockEntity)iterator2.next();
            if (playerEntity.getEyePos().distanceTo(blockEntity.getPos().toCenterPos()) > 2.2 || !(blockEntity instanceof ShulkerBoxBlockEntity)) continue;
            this.mineBlock(l2, ((ShulkerBoxBlockEntity)blockEntity).getPos(), true);
            break;
        }
        return false;
    }

    private final void mineBlock(long l, BlockPos blockPos, boolean bl) {
        long l2 = (l = c ^ l) ^ 0xB1899CBB469L;
        if (blockPos == null) {
            return;
        }
        if (SpeedMine.INSTANCE.canMineMore() && !bl) {
            return;
        }
        if (SpeedMine.INSTANCE.getEnabled()) {
            if (!SpeedMine.INSTANCE.getDoubleMine() || SpeedMine.INSTANCE.getMiningQueue().size() == 1) {
                this.reset();
            }
            SpeedMine.INSTANCE.startMining(blockPos, Direction.UP, l2);
        } else {
            Globals.getInteractionManager().updateBlockBreakingProgress(blockPos, Direction.UP);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        }
    }

    public final void reset() {
        cooldownTimer.reset();
    }

    private static final boolean settingVisible() {
        return INSTANCE.getHoldPosition();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getHoldPosition();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getHoldPosition();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getHoldPosition();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoMine.c = ZkmKeys.a(4505002095066251631L, -8975444313398476768L, MethodHandles.lookup().lookupClass()).a(114886712624630L);
                        var20 = AutoMine.c ^ 87137208818909L;
                        AutoMine.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[49];
                        var16_4 = 0;
                        var15_5 = "\u00d1}\u00bc\u00f0\u00e1]%\u0000?\u0094\u00b6\u00c9`\u00db\u00dc\u0082\u00ca\u00b9\u00e01J\u00ce2\u001d\u00f1\u001a\u0010M\u00a5\u00a5\u00d6n\u0010\u008e\u00d1\u00c6\u00a5\u0000\u00ee#\u0004;\u000fo\u0083r8\u0092\u0094 \u000b\u00ec\u00f4\u00a0l\u008aYHH=*c\u00c22@\u00a3\u0019\u0097{\u00ef}/\u00f1\u0018C\u00ac\u00c3\u00fe\u00d6\u00e4\u00b3\u00f3\u0018\u00f16\u00ed\u00a83d\u00ba%\u0084\u00bf\u00a0b]\r\u0013\u00b1\u00feh:\u00ea4\u000b\u00cf\u0017(}\u00a8\u000f\u0018X\u00aa\u008ba\u007fm\u009b{|\u0006\u00a9\u00b6\u00fd\u00f4\u00e7\u0011\u0010\u00c3\u00a2\u00d9*\u00cdT\u00b0.\u0084\u00fc\\\u00b9\u0011\u00ca\u00ccCaf\u0014 \u00cd'o\u0006&\u009bk\r\u00f4\u00f4\u00de\u00ba\u00e0\u00f3\u00c4\u007f\u00b0o\u009e\u0003\u00aa\u0090\t[\u00f2,\u00bd4\u00f7\u0086\u008b\u008a \u00ae1|\u00cc\u00f1.wu\u00e1w;\t|\u00a2\u0093i\u0000rr\u00a3\u00afuW\u009b-H\u008c\u00bdu%\u00d4\u00b9\u0018\u00af\u0013\u009c3\u009e\u00caAF,m\u00ee\u00e5\u0010\u009b\u00bbu\u00ee\u001c!\u0088B\u009b\b 0EF\u00ab\u0016\u00f0D\u00f4u\u00c4\u0089\u00d9\u00f1\\\u00bdX\u008e\u00c6|}\u00bd\t\u00d2jA\u00ac\u009e}DJ\u00e2]\u00a2\u00ce\u0082\u00cb1Qs\u00ce1\u008c\u0089\u00a1\u00b3\u008aCzG \u00f6\u00f7\u00d1A\u0094\u00d5\u0085\u0010\\\u00b2sk\u00a7?\u00a8\u00835d\u000e\u0096\u00bd\u00e7%\u00be?>\u00c7\u00ca\u0095\u00b1}c(H\u001b=\u0087\u0097\u001e\u001b^\u00d8w{Z\u001f\u009er\u0092[\u00bb\u00d7,\u00beh?\u00a0\u00be\u00a0\u00c0\u0002\u000e^\u0010\u00b2D\u00ce.\u00db\u0001\u0098R\u0087 i/m\u00bd\u00d8@\u00b0\u00b8\u00828\u00a7FzNw\u00b3J\u009f=\u000e\u00a1F\u00f1\u0092\u00cb\u0098\u00bd\u00b9@\u001cX\u0080 *\u00d7}\u0001\u00c0\u001eas\u001e\u00a0e=0i /\u00e4\u00aeOM^\u001c\u00f4IQ\u0001A\b\u0098\"\u0089\u0083\u0018w\u00e7\u00b0`\u00f3\u007f\u00c7NQ\u0096#\u00876\u0099\u00b7\u001d\u0012\u00d3\u000f#\u00f3:\u0013G \u0081*J\u00ff\u00bf\u00a3Q\u00f5\u00a4\t\u00b6\u00da^ruM\u000f\u00a3@\u00b8\u00f9\u00c7S@\u00c4\u00e4\u00fe\u0011\u0092\u0093\u0018w(\f\u00e0\u00fa\u009f\u00e8zk\u00f5\u00ef\u00f9'\u00afQ:\u00d8\u00f7l\u00ae\u00cf\r\u00ec\u0018\u008a\u00b2:\u00d9\u0006\u00e6\u00afh%N\u00ba\u00c4Eo\u0098v\u001d\u00e6 c\u0011\u00dfhz\u00a7\u009f\u0087\u00d0O\u0090\u00c7\u00c1\u0014\u0011\u00de\u00d2\u0011}\u00fb\b\u00a8k\u001fk\u00a9i\u0098\u00c1A:\u0013(_;}\u008eL\u00a5\u0005@\u00eb\u009e\u00f2\u001f\u00b7)\u00af_\u00e1\u0095\u00be{2\u00f0\u0080\u00d2\u00bd<\u00b3\u00d1P\u0005J\u0088\u00d2EO\u00bf\u00dfX\u00cdM\u0018\u00f5-\u00bb\u00eb\u0017\u00a1\u00b6\u00e1]\u00cc\u0086\u00fd\u00cb\u00c1ZD+r\u00b9\u0092\u0019t\u00a1\u00de \u00d6f\u00d7[\u0091\u00fa\u00af\u00e7\u00ba\u00c0\u000f%\u000f6\u00d8\u00fbW\u00cb\u00c7\u000e\u0013+\u00dc\u00bc\u0099^TiN\u00d2)\u00f2(/O\u00c8\u0016\u0095r\u00b5\u0007f\u00e7\u00f8\u00bf\u0086\u00e1u\u00fby\u0083\u00fb\u001c\u00c5pq\u000e+-\u00af\u0094^Z\u000e\b8\u0093\u00f2Y\u0015\u00a8\u0019g(\u001d,\u008dv\u0002&\u00be\u008c\u0086\u00d3\u0081\u00aa>\u00f1\u00aa\u0080\u00d4\u00ee\u0096\u00cb\u00cet\u0012\u00cd\u0007\u001fU\u0004 (D\u00d7r\u00c0H\u008b[A7\u00d2 \u008f;\u00c9H\u00d6\u00c7p]\u00a8\u00eb\u00fb\u00b2q)\u00a1\u0083\u00cf)\u00df\u0080\u008f\u00f7v&m=\u00d4\u00d9B\u00fd\u00d5\n \u00be\u00c1\u00af\u00ab\u00dd\u00act,c\nr\u009cn\u00c0\u008ek\u00a0\u0004&:\u00fd\u0012/G\u00ed\u001a\u00f5_&\u00f1E\u00a6\u0018z\u0090\u00c7\u008e-g\u0019*plP\u0002\u00a7\fW\u00c4uh\u00eeS\u00a1\u00e5F\u0003 (\u001b\u00cc\u00bc{?9\u008ed\u00d5\u00c2\u0080/\u00d0\u00dd;X\u0018\u00f9\u00fa!\u00d3g8n\u0087\u00ed\u0092\u00d8\u00be\u00caF \u0090yh\u00fd\u0004V\u00ae\u00c3q\u00e4\u009b\u00a5\u00d1e\u0010Y\u00d0\"\u000ef\u00e7t\u0093\u0086\u00ab\u001bf\b\u0002\u009b\u001d\u0001X\u00e3n\u00a9sw#4\u00f0\u00a7\u0004\u00b2\u00e6\u0084\u00eb\u0000\u00fd\u00cc4\u0016\u00b5\u00c3\u00c1\u00ed\u009eDp\u00b3\u00b8\u00afux\u00c3\u00b8\u00aa\u0010\u008f\u00da\u009cOX\u0080\u00b9\u00df\u00f2\u00baQ}\u00ec\u00fbIS(\u00d0\u00edz/.|\u00da/\f\u008eu\u00b5\u0014A\u001d\u00fe\u00c2T\u001dG\u00e9\f\u00a9\u00d6\u00144\u00b0{rC\u00e9}\u00bf\u00d5+\u00f5 \u00fd\u00022\u0016S\u00bb\u00f4\u00b3\u00f4Y\u0093[E\u0095>\u00b8\u00ca`q\u00df\u00b9w\u00db\u00ba\u00da\u00fc\u0089\u0000\u00d0R\u0014W \u00ca\u0016\u00bd!\u00ac\u00af\u00a1\u00cc\u00ad\u0006\u00cc\u00fb\u0097%PW\u0003L\u00e3\u00d9\u00d0\u00d6\u00c5'@\u008dB\u000fQ\u00e5#\u0015\u0018\u00bc\u00e4\u000e\u00a2B\u00fc(!U\u00f5\u00edkX\u0082\u00b4\\{J\u0091\u009dju\u00c7\u00e7 C\u0011`\u00c7\u0012\u0004\u0013\u008f\u008f\u00ac<q\u00b8\u00ed\u00d6v\u00b2\u00b5y\u00acx[]~9 \f\u00ed\u001beVU 7\u0010<)\u00a3~\u00eb\u00c6\u00ab\u00ee\u0085\u008b\u00a3\tz\u000e,-\u00dc(\u00c5\u00be\u0015\u0083\u00bc\u0099f7\"m\u0083\u00f0\u0018\u0093\u0019\u00deI\u00c95\u00c6u8N>\u0082\u0006\u00f1\u0096\u0002\u0084T&\u00af0\u008e\"\u0086 K\u00a8v\u009b\u0087\u00c1\b\u00b1\u00d0w@\u00d7H\u008e\u00c2\b#\u00e0m\u008b\u0015\u00e1\u00d4y\u00d4\u00c7j\u0093\u00da\u00129\u00f3 1\u0080\u00cc3\u00c3\u00d3p\u00ceF\u00ddfQ\u009b6\u00b1\u00eaQ=\u00fcK\u00f0-\u00d3\u00b9\u0087H(\u00bd\u009e\u0014\u008ff \n\u0014.\u00ae]\u00a1\u0098\u0005\u008c\t\u0080\u00f7(\u0014\u00fe\u0093\u001b\u0086\u00a9\u00a4\u0001\u00f6\u000f\u0018\u00c9Z\u00aa}\u0082gT\u008c\u0010F\u00c7\u000f\u00a6\u00f0`\u00d4E}3\u001e\u00ab\u00b9\u00e7\u00e4\u00c7 q\u008c\u00b1-x40\u00f7\u00cc\u00c7\u00fbV}{t\u00cc\u0004\u0095{\u00ec/Sk\u00d0=\u00acp\u0095\u00f9Q\n\u00ee(\u0003\u0097\u00dc\u009d\u00f3\u00d2'\u00a7\u0010\u00e1\u00f8!\u0000$sD\u0095\u00da\u00c8x\u000e\u0091lm\u00d4^?\u0006z\r5\u00f7\u008eP/!\u00fc8\u0016\u0085\u0018y&h\u0018\u00faHJ\u00d7\u00eew\u00f1\u0013O7s\u00eb\u00a3\u00f3\u00d2\u00ce\u00dd\u00ebk\u00b7\u0010\u00b4f\u0090x\u0088\u008c(\u0018\u00e3E\u00d6\u00bc\u00ee\u0082\u00c6\u00db E\u00fe\u0015d\u001cJ\u00c2t\u00d6\u0013\u00c1\u00d6J\r$\u00e7\u00c6\u00eaa\u0019\u00bf\u00ea\u0081D\u00b4\u009b\u00e0\u009f<\u00e6\u0085,(u`\u00fe\u00c4\u00d9T\u0000i\u0015\u0007\u00a1\u00d1\u00a7S\u00a2\u000br-}\u00d6\u008c\u00e6\u00f7W\u0003\u00c9\u0014L\u00e4Q\u0089Tx\u001f\u00a5\u00fe\u00df&\u0085\u00b0 U\u00c1\u00cc\u0012C\u00dc\u0086\u00d7\u00de0\u000b5]e\u00de\u0080\u00b3\u00be\u0000_\u0015/\u001a\u00d3\u00e0H\u00c4\u00ab\u008e.\u00b9\u00ae\u0010d}6\u00e4\u001aj\u00faX\u0089\u00b2?\u00bd\u00d2\u00ce5@\u0018\u00df\u00ff|\u00d9\u00f0\u00ec\u0016\u0011\u00d9p\u008bs\u009a\u00acU\u00b7n\u00db\u00a9xX\u00bc\u0014F";
                        var17_6 = 1550;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = AutoMine.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e9\u00e7\u00ca\u00e8\u00cd\u00a0\u00d5N\u000b?\u0013e\u00e7\u00910\u0092\u007fBrH2\u00bf\u008a\u00be\u00c9\u0089\u0094\u00ea\u00ca\u00dcF8\u00e4Q\u00f6\u009d\u000e\u00f5\u00c0\u0018\u00182\u00ac\u00da\u00c02\u00e1*\u00a6\u00d5(\u0090\u008b\u00bd/\u0019~*\u0085\"\u0094\u00e6\u0004{\u00c2";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = AutoMine.zkmDecodeUtf8(var19_9).intern();
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
                AutoMine.d = var18_3;
                AutoMine.e = new String[49];
                AutoMine.i = new HashMap<K, V>(13);
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
                var6_12 = new long[19];
                var3_13 = 0;
                var4_14 = "\u0006\u0089NM$\u0014\u008f\u00a0\u00b4\u0005\u00ce\u00c4@:\u000f\u007f\u007f\u0000\u000e\f\u00a3\u0007\u00a8V^\u0004\f\u00ca\u00be\u00aa\u00a5\u00e4\u00e3\f\u001c\u0096\u00de\u00b8\u00c9\u00c4#\u0001X\u001d\u0000\u00b3\\S/\u000fV\u00d4\f\u009d\u00ae\u00a0\u0001m\u00f1\u00e7y\f:\u00a3\u00f5\t#\u0099\u00a4\u00e9\u00c8\u0088\u008bP\u0016DK\r[|\u00c0\u0011P'\u00df\u001a\u00ce\u0010\u00b4\u00c4\u00da\u00ff\u00a7\u00a7\u00df\u000eb\u0086\u0081\u009d\u00de\u00d3\"G\u0018l\u00e9\u008e\u0090\n\u00b1\u00faeX\b\u00bf\u0017\u00a1\u00bc\u001e\u0006\u008c\u00d1Q\u00c2\u00c3{YggS\u0086P7\u0001\u000e";
                var5_15 = 136;
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
                    var4_14 = "j\u001a\u00a4\u00d7>\u0006\u00138!#\u0012\u00c0Zt\u00d6f";
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
        AutoMine.g = var6_12;
        AutoMine.h = new Integer[19];
        var39_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoMine.class, "cevPriority", "getCevPriority()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "antiShulker", "getAntiShulker()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "eChestMine", "getEChestMine()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "debug", "getDebug()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "antiCrawl", "getAntiCrawl()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "levelDown", "getLevelDown()Lsu/catlean/api/setting/values/Bind;", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "cevCrawlers", "getCevCrawlers()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "holdPosition", "getHoldPosition()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "holdGroup", "getHoldGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "holdCiv", "getHoldCiv()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "holdCev", "getHoldCev()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoMine.class, "holdSurr", "getHoldSurr()Z", 0))};
        AutoMine.delegatedProperties = var39_20;
        AutoMine.INSTANCE = new AutoMine();
        AutoMine.cevPriority$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "cev-priority", true, null, null, 12, null);
        AutoMine.antiShulker$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "anti-shulker", true, null, null, 12, null);
        AutoMine.eChestMine$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "e-chests-mine", true, null, null, 12, null);
        AutoMine.debug$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "debug", false, null, null, 12, null);
        AutoMine.antiCrawl$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "anti-crawl", true, null, null, 12, null);
        AutoMine.levelDown$delegate = SettingFactory.bindSetting$default(AutoMine.INSTANCE, "level-down", new Bind(0, false, false, 7, null), null, null, 12, null);
        AutoMine.cevCrawlers$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "cev-crawlers", false, null, null, 12, null);
        AutoMine.holdPosition$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoMine.INSTANCE, "sync-with-rebreak", true, null, null, 12, null);
        AutoMine.holdGroup$delegate = SettingFactory.settingGroup((SettingContainer)AutoMine.INSTANCE, "hold-selection", AuraTargetsMode.BOOLS, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)());
        var40_21 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)();
        var41_22 = AutoMine.INSTANCE.getHoldGroup();
        var42_23 = true;
        var43_24 = "hold-civ";
        var44_25 = AutoMine.INSTANCE;
        AutoMine.holdCiv$delegate = SettingFactory.booleanSetting(var44_25, var43_24, var42_23, var41_22, var40_21);
        var47_26 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)();
        var48_27 = AutoMine.INSTANCE.getHoldGroup();
        var49_28 = true;
        var50_29 = "hold-cev";
        var51_30 = AutoMine.INSTANCE;
        AutoMine.holdCev$delegate = SettingFactory.booleanSetting(var51_30, var50_29, var49_28, var48_27, var47_26);
        var54_31 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)();
        var55_32 = AutoMine.INSTANCE.getHoldGroup();
        var56_33 = true;
        var57_34 = "hold-surr";
        var58_35 = AutoMine.INSTANCE;
        AutoMine.holdSurr$delegate = SettingFactory.booleanSetting(var58_35, var57_34, var56_33, var55_32, var54_31);
        AutoMine.cooldownTimer = new TickTimer();
        AutoMine.targets = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64A6;
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
                throw new RuntimeException("su/catlean/u0", exception);
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
            AutoMine.e[n2] = AutoMine.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoMine.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x37BC;
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
                throw new RuntimeException("su/catlean/u0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoMine.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoMine.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoMine.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoMine.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

