/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.AfterReceivePacket;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.player.AttackBlockEvent;
import su.catlean.api.event.events.player.CollisionEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.UpdateBlockBreakingProgressEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.InteractionManagerAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.equip.ToolSaver;
import su.catlean.module.player.SpeedMine$WhenMappings;
import su.catlean.module.player.SpeedMineDoubleMinePacketOrderMode;
import su.catlean.module.player.SpeedMineMode;
import su.catlean.module.player.SpeedMinePauseEatMode;
import su.catlean.module.player.SpeedMineRebreakMode;
import su.catlean.module.player.SpeedMineRenderMode;
import su.catlean.module.player.SpeedMineSelectionMode;
import su.catlean.module.player.SpeedMineStartMode;
import su.catlean.module.player.SpeedMineSwapTimingMode;
import su.catlean.module.player.SpeedMineSwitchMode;
import su.catlean.module.player.SpeedMineTimingMode;
import su.catlean.module.world.FreeCam;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MiningData;
import su.catlean.util.MiningPauseReason;
import su.catlean.util.PacketUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SpeedMine
extends Module {
    @NotNull
    public static final SpeedMine INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ModeSetting timing$delegate;
    @NotNull
    private static final ModeSetting rebreak$delegate;
    @NotNull
    private static final BooleanSetting awaitAutoCrystal$delegate;
    @NotNull
    private static final BooleanSetting instantAsync$delegate;
    @NotNull
    private static final BooleanSetting doubleMine$delegate;
    @NotNull
    private static final ModeSetting doubleMinePacketOrder$delegate;
    @NotNull
    private static final ModeSetting startMode$delegate;
    @NotNull
    private static final ModeSetting switchMode;
    @NotNull
    private static final BooleanSetting swapDelay$delegate;
    @NotNull
    private static final ModeSetting swapTiming$delegate;
    @NotNull
    private static final FloatSetting factor$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final BooleanSetting rotate$delegate;
    @NotNull
    private static final BooleanSetting await$delegate;
    @NotNull
    private static final BooleanSetting phase$delegate;
    @NotNull
    private static final BooleanSetting resetOnSwitch$delegate;
    @NotNull
    private static final ModeSetting pauseEat$delegate;
    @NotNull
    private static final SettingGroup renderGroup$delegate;
    @NotNull
    private static final ModeSetting renderMode$delegate;
    @NotNull
    private static final ModeSetting selection$delegate;
    @NotNull
    private static final ColorSetting startColor$delegate;
    @NotNull
    private static final ColorSetting endColor$delegate;
    @NotNull
    private static final Timer timer;
    @Nullable
    private static BlockPos i;
    @Nullable
    private static BlockPos currentPos;
    @NotNull
    private static AtomicBoolean atomicBoolean;
    @NotNull
    private static LinkedList miningQueue;
    @NotNull
    private static ConcurrentHashMap recentBreaks;
    private static String g;
    private static final long e;
    private static final String[] f;
    private static final String[] j;
    private static final Map k;
    private static final long[] u;
    private static final Integer[] C;
    private static final Map N;
    private static final long[] O;
    private static final Long[] P;
    private static final Map W;

    private SpeedMine() {
        super("speed-mine", Categories.getPlayer(), null, null);
    }

    @NotNull
    public final SpeedMineMode getMode() {
        return (SpeedMineMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    @NotNull
    public final SpeedMineTimingMode getTiming() {
        return (SpeedMineTimingMode)((Object)timing$delegate.getValue(this, delegatedProperties[1]));
    }

    @NotNull
    public final SpeedMineRebreakMode getRebreak() {
        return (SpeedMineRebreakMode)((Object)rebreak$delegate.getValue(this, delegatedProperties[2]));
    }

    private final boolean getAwaitAutoCrystal() {
        return (Boolean)awaitAutoCrystal$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getInstantAsync() {
        return (Boolean)instantAsync$delegate.getValue(this, delegatedProperties[4]);
    }

    public final boolean getDoubleMine() {
        return (Boolean)doubleMine$delegate.getValue(this, delegatedProperties[5]);
    }

    private final SpeedMineDoubleMinePacketOrderMode getDoubleMinePacketOrder() {
        return (SpeedMineDoubleMinePacketOrderMode)((Object)doubleMinePacketOrder$delegate.getValue(this, delegatedProperties[6]));
    }

    private final SpeedMineStartMode getStartMode() {
        return (SpeedMineStartMode)((Object)startMode$delegate.getValue(this, delegatedProperties[7]));
    }

    @NotNull
    public final SpeedMineSwitchMode FC() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ e;
        return (SpeedMineSwitchMode)((Object)switchMode.getValue(this, delegatedProperties[SpeedMine.zkmBootstrap2("v", (int)6369, (long)(0x2F2CC5557717A64CL ^ l))]));
    }

    private final boolean getSwapDelay() {
        return (Boolean)swapDelay$delegate.getValue(this, delegatedProperties[9]);
    }

    private final SpeedMineSwapTimingMode getSwapTiming() {
        return (SpeedMineSwapTimingMode)((Object)swapTiming$delegate.getValue(this, delegatedProperties[10]));
    }

    private final float getFactor() {
        return ((Number)factor$delegate.getValue(this, delegatedProperties[11])).floatValue();
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[12])).floatValue();
    }

    private final boolean getRotate() {
        return (Boolean)rotate$delegate.getValue(this, delegatedProperties[13]);
    }

    private final boolean getAwait() {
        return (Boolean)await$delegate.getValue(this, delegatedProperties[14]);
    }

    private final boolean getPhase() {
        return (Boolean)phase$delegate.getValue(this, delegatedProperties[15]);
    }

    public final boolean getResetOnSwitch() {
        return (Boolean)resetOnSwitch$delegate.getValue(this, delegatedProperties[16]);
    }

    private final SpeedMinePauseEatMode getPauseEat() {
        return (SpeedMinePauseEatMode)((Object)pauseEat$delegate.getValue(this, delegatedProperties[17]));
    }

    private final SettingParent getRenderGroup() {
        return (SettingParent)renderGroup$delegate.getValue(this, delegatedProperties[18]);
    }

    private final SpeedMineRenderMode getRenderMode() {
        return (SpeedMineRenderMode)((Object)renderMode$delegate.getValue(this, delegatedProperties[19]));
    }

    private final SpeedMineSelectionMode getSelection() {
        return (SpeedMineSelectionMode)((Object)selection$delegate.getValue(this, delegatedProperties[20]));
    }

    private final Color getStartColor() {
        return (Color)startColor$delegate.getValue(this, delegatedProperties[21]);
    }

    private final Color getEndColor() {
        return (Color)endColor$delegate.getValue(this, delegatedProperties[22]);
    }

    @NotNull
    public final AtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public final void setAtomicBoolean(@NotNull AtomicBoolean atomicBoolean) {
        long l = 0L;
        l = e ^ l;
        Intrinsics.checkNotNullParameter(atomicBoolean, (String)((Object)SpeedMine.zkmBootstrap("j", (int)25976, (long)(0x7CC0712E2DC7BL ^ l))));
        SpeedMine.atomicBoolean = atomicBoolean;
    }

    @NotNull
    public final LinkedList getMiningQueue() {
        return miningQueue;
    }

    public final void setMiningQueue(@NotNull LinkedList linkedList) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ e;
        Intrinsics.checkNotNullParameter(linkedList, (String)((Object)SpeedMine.zkmBootstrap("j", (int)10480, (long)(0x7579F05DD3F19FEDL ^ l))));
        miningQueue = linkedList;
    }

    @Override
    public void onDisable(long l) {
        long l2 = l ^ 0x220F66340504L;
        this.pause(l2, MiningPauseReason.TOGGLE);
    }

    @Override
    public void onEnable(long l) {
        long l2 = l ^ 0x5A242A5324EL;
        this.pause(l2, MiningPauseReason.TOGGLE);
    }

    private final void pause(long l, MiningPauseReason miningPauseReason) {
        block2: {
            long l2 = (l = e ^ l) ^ 0x5452899DA0D5L;
            Iterable iterable = miningQueue;
            Iterator iterator2 = iterable.iterator();
            int n = MiningData.zkmIntConstant();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                MiningData miningData = (MiningData)t;
                miningData.a(l2, miningPauseReason);
                if (n != 0) {
                    if (n != 0) continue;
                }
                break block2;
            }
            miningQueue.clear();
            i = null;
            atomicBoolean.set(false);
        }
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block23: {
            int n;
            int n2;
            block31: {
                InteractionManagerAccessor interactionManagerAccessor;
                block32: {
                    SpeedMine speedMine;
                    block30: {
                        block28: {
                            SpeedMinePauseEatMode speedMinePauseEatMode;
                            SpeedMinePauseEatMode speedMinePauseEatMode2;
                            long l;
                            block29: {
                                boolean bl;
                                block26: {
                                    block27: {
                                        block25: {
                                            block24: {
                                                long l2 = e ^ 0x575466D4CBF7L;
                                                l = l2 ^ 0x5FE1D2DAC6CBL;
                                                n2 = MiningData.zkmIntConstant();
                                                bl = Globals.getPlayer().getAbilities().creativeMode;
                                                if (n2 == 0) break block24;
                                                if (bl) break block25;
                                                bl = atomicBoolean.get();
                                            }
                                            if (n2 == 0) break block26;
                                            if (!bl) break block27;
                                        }
                                        return;
                                    }
                                    bl = Globals.getPlayer().isUsingItem();
                                }
                                if (!bl) break block28;
                                speedMinePauseEatMode2 = this.getPauseEat();
                                speedMinePauseEatMode = SpeedMinePauseEatMode.PAUSE;
                                if (n2 == 0) break block29;
                                if (speedMinePauseEatMode2 == speedMinePauseEatMode) {
                                    return;
                                }
                                speedMine = this;
                                if (n2 == 0) break block30;
                                speedMinePauseEatMode2 = speedMine.getPauseEat();
                                speedMinePauseEatMode = SpeedMinePauseEatMode.RESET;
                            }
                            if (speedMinePauseEatMode2 == speedMinePauseEatMode) {
                                this.pause(l, MiningPauseReason.EATING);
                            }
                        }
                        speedMine = this;
                    }
                    if (speedMine.getMode() != SpeedMineMode.DAMAGE) break block31;
                    ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
                    Intrinsics.checkNotNull(clientPlayerInteractionManager, "null cannot be cast to non-null type su.catlean.mixins.accessors.InteractionManagerAccessor");
                    interactionManagerAccessor = (InteractionManagerAccessor)((Object)clientPlayerInteractionManager);
                    if (n2 == 0) break block32;
                    if (!(interactionManagerAccessor.getCurBlockDamageMP() < this.getSpeed())) break block31;
                    ClientPlayerInteractionManager clientPlayerInteractionManager2 = Globals.getInteractionManager();
                    Intrinsics.checkNotNull(clientPlayerInteractionManager2, "null cannot be cast to non-null type su.catlean.mixins.accessors.InteractionManagerAccessor");
                    interactionManagerAccessor = (InteractionManagerAccessor)((Object)clientPlayerInteractionManager2);
                }
                interactionManagerAccessor.setCurBlockDamageMP(this.getSpeed());
            }
            for (Map.Entry entry : ((Map)recentBreaks).entrySet()) {
                if (n2 != 0) {
                    long l = Globals.now() - ((Number)entry.getValue()).longValue() - 200L;
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                    if (n2 == 0) break block23;
                    if (n <= 0) {
                        v8 = Globals.getWorld();
                        if (n2 != 0) {
                            if (!v8.isAir((BlockPos)entry.getKey())) continue;
                        }
                    } else {
                        v8 = recentBreaks.remove(entry.getKey());
                    }
                }
                if (n2 != 0) continue;
            }
            n = miningQueue.removeIf(arg_0 -> SpeedMine.invokePredicate(SpeedMine::onPlayerUpdate$lambda$0, arg_0));
        }
    }

    @Flow
    private final void onUpdateBlockBreakingProgress(UpdateBlockBreakingProgressEvent updateBlockBreakingProgressEvent) {
        block0: {
            if (this.getMode() != SpeedMineMode.PACKET) break block0;
            updateBlockBreakingProgressEvent.cancel();
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        if (this.getMode() == SpeedMineMode.DAMAGE) {
            return;
        }
        Iterable iterable = miningQueue;
        for (Object t : iterable) {
            Box box;
            MiningData miningData = (MiningData)t;
            if (INSTANCE.getSelection() == SpeedMineSelectionMode.ALWAYS || INSTANCE.getSelection() == SpeedMineSelectionMode.REBREAK && INSTANCE.getRebreak() != SpeedMineRebreakMode.OFF || INSTANCE.getSelection() == SpeedMineSelectionMode.AIR_REBREAK && INSTANCE.getRebreak() != SpeedMineRebreakMode.OFF && Globals.getWorld().isAir(miningData.getPos())) {
                Object var37_4 = null;
                Color color = null;
                Color color2 = RenderUtil.INSTANCE.withAlpha(INSTANCE.getStartColor(), 1.0f);
                Box box2 = new Box(miningData.getPos());
                RenderState.addOutlineBox$default(RenderState.INSTANCE, box2, color2, color, var37_4);
            }
            if (Globals.getWorld().isAir(miningData.getPos())) continue;
            float f = Math.clamp(RenderUtil.INSTANCE.lerp(miningData.G(), miningData.O(), RenderState.INSTANCE.getTickDelta()), 0.0f, 1.0f);
            if (INSTANCE.getPauseEat() == SpeedMinePauseEatMode.PAUSE && Globals.getPlayer().isUsingItem()) {
                f = miningData.O();
            }
            if (miningData.O() >= 1.0f) {
                f = 1.0f;
            }
            switch (SpeedMine$WhenMappings.i[INSTANCE.getRenderMode().ordinal()]) {
                case 1: {
                    box = new Box(miningData.getPos());
                    break;
                }
                case 2: {
                    box = new Box(miningData.getPos().getX(), miningData.getPos().getY(), miningData.getPos().getZ(), miningData.getPos().getX() + 1, (float)miningData.getPos().getY() + f, miningData.getPos().getZ() + 1);
                    break;
                }
                case 3: {
                    Box box3 = new Box(miningData.getPos().getX(), miningData.getPos().getY(), miningData.getPos().getZ(), miningData.getPos().getX(), miningData.getPos().getY(), miningData.getPos().getZ()).shrink(f, f, f).offset(0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                    box = box3;
                    Intrinsics.checkNotNullExpressionValue(box3, "move(...)");
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            Box box4 = box;
            Object var51_12 = null;
            Color color = null;
            Color color3 = RenderUtil.INSTANCE.blend(INSTANCE.getStartColor(), INSTANCE.getEndColor(), f);
            Box box5 = box4;
            RenderState renderState = RenderState.INSTANCE;
            RenderState.addFilledBox$default(renderState, box5, color3, color, var51_12);
            Object var59_17 = null;
            Color color4 = null;
            Color color5 = RenderUtil.INSTANCE.blend(RenderUtil.INSTANCE.withAlpha(INSTANCE.getStartColor(), 1.0f), RenderUtil.INSTANCE.withAlpha(INSTANCE.getEndColor(), 1.0f), f);
            Box box6 = box4;
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box6, color5, color4, var59_17);
        }
    }

    @Flow
    private final void onAttackBlock(AttackBlockEvent attackBlockEvent) {
        long l = e ^ 0x6BABA05918B6L;
        long l2 = l ^ 0x12DD6D3DC479L;
        if (!this.getBreakTime(attackBlockEvent.getPos()) || Globals.getPlayer().getAbilities().creativeMode || this.getMode() == SpeedMineMode.DAMAGE) {
            return;
        }
        attackBlockEvent.cancel();
        this.startMining(attackBlockEvent.getPos(), attackBlockEvent.getDirection(), l2);
    }

    public final void startMining(@NotNull BlockPos blockPos, @NotNull Direction direction, long l) {
        int n = (int)(((l = e ^ l) ^ 0x235332DE4C23L) << 48 >>> 48);
        Intrinsics.checkNotNullParameter(blockPos, "pos");
        Intrinsics.checkNotNullParameter(direction, "direction");
        if (!this.getBreakTime(blockPos) || Globals.getPlayer().getAbilities().creativeMode || this.getMode() == SpeedMineMode.DAMAGE) {
            return;
        }
        if (!this.isQueued(blockPos, (short)n)) {
            if (!this.getDoubleMine() && !((Collection)miningQueue).isEmpty()) {
                ((MiningData)miningQueue.removeFirst()).T();
            }
            if (this.getDoubleMine() && miningQueue.size() >= 2) {
                Object t2;
                block6: {
                    Iterable iterable = miningQueue;
                    for (Object t2 : iterable) {
                        MiningData miningData = (MiningData)t2;
                        if (!Intrinsics.areEqual(miningData.getPos(), currentPos)) continue;
                        break block6;
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
                MiningData miningData = (MiningData)t2;
                miningData.T();
                miningQueue.remove(miningData);
            }
            currentPos = blockPos;
            if (miningQueue.size() >= 2) {
                throw new Exception("Already mining!");
            }
            miningQueue.add(new MiningData(blockPos, direction));
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        }
    }

    @Flow
    private final void onAfterReceivePacket(AfterReceivePacket afterReceivePacket) {
        long l = e ^ 0x73171B0763D7L;
        long l2 = l ^ 0x752FBF0E437CL;
        if (afterReceivePacket.getPacket() instanceof BlockUpdateS2CPacket && this.getRebreak() == SpeedMineRebreakMode.GRIM && this.getInstantAsync() && Intrinsics.areEqual(((BlockUpdateS2CPacket)afterReceivePacket.getPacket()).getState().getBlock(), Blocks.OBSIDIAN)) {
            Iterator iterator2 = miningQueue.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
            Iterator iterator3 = iterator2;
            while (iterator3.hasNext()) {
                Object e = iterator3.next();
                Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                MiningData miningData = (MiningData)e;
                if (!miningData.v() || !Intrinsics.areEqual(miningData.getPos(), ((BlockUpdateS2CPacket)afterReceivePacket.getPacket()).getPos())) continue;
                BlockPos blockPos = ((BlockUpdateS2CPacket)afterReceivePacket.getPacket()).getPos();
                Intrinsics.checkNotNullExpressionValue(blockPos, "getPos(...)");
                if (this.isAutoCrystalTarget(blockPos) && this.getAwaitAutoCrystal()) continue;
                miningData.x(l2, true);
            }
        }
    }

    @Flow
    private final void onAfterSendPacket(AfterSendPacket afterSendPacket) {
        block2: {
            SpeedMine speedMine;
            block4: {
                int n;
                block3: {
                    boolean bl;
                    block1: {
                        n = MiningData.zkmIntConstant();
                        bl = afterSendPacket.getPacket() instanceof UpdateSelectedSlotC2SPacket;
                        if (n == 0) break block1;
                        if (!bl) break block2;
                        speedMine = this;
                        if (n == 0) break block3;
                        bl = speedMine.getResetOnSwitch();
                    }
                    if (!bl) break block2;
                    speedMine = this;
                }
                if (n == 0) break block4;
                if (speedMine.FC() == SpeedMineSwitchMode.Silent) break block2;
                speedMine = this;
            }
            if (speedMine.getRebreak() != SpeedMineRebreakMode.GRIM) {
                Scheduler.schedule$default(Scheduler.INSTANCE, 0, (byte)0, false, SpeedMine::onAfterSendPacket$lambda$0, 6, null);
            }
        }
    }

    @Flow
    private final void onCollision(CollisionEvent collisionEvent) {
        if (this.e()) {
            Map object2;
            Map map = object2 = (Map)recentBreaks;
            Map map2 = new LinkedHashMap();
            Iterator iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry;
                Map.Entry entry2 = entry = iterator2.next();
                if (!Intrinsics.areEqual(collisionEvent.getPos(), entry2.getKey())) continue;
                map2.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry entry : map2.entrySet()) {
                if (!Intrinsics.areEqual(collisionEvent.getPos(), entry.getKey())) continue;
                BlockState blockState = Blocks.AIR.getDefaultState();
                Intrinsics.checkNotNullExpressionValue(blockState, "defaultBlockState(...)");
                collisionEvent.setState(blockState);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean e() {
        boolean bl;
        Map map;
        int n = MiningData.zkmIntConstant();
        boolean bl2 = this.getEnabled();
        if (n != 0) {
            if (!bl2) return false;
            bl2 = this.getPhase();
        }
        if (!bl2) return false;
        Map map2 = map = (Map)recentBreaks;
        if (n != 0) {
            if (map2.isEmpty()) {
                return false;
            }
            map2 = map;
        }
        Iterator iterator2 = map2.entrySet().iterator();
        block0: while (true) {
            Map.Entry entry;
            if (!iterator2.hasNext()) return false;
            Map.Entry entry2 = entry = iterator2.next();
            long l = Globals.now() - ((Number)entry2.getValue()).longValue() - 200L;
            long l2 = l == 0L ? 0 : (l < 0L ? -1 : 1);
            do {
                if (n != 0) {
                    l2 = l2 < 0 ? (long)1 : (long)0;
                }
                if (l2 == false) continue block0;
                boolean bl3 = true;
            } while (n == 0);
            break;
        }
        if (!bl) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     */
    public final boolean isQueued(@NotNull BlockPos var1_1, short var2_2) {
        block5: {
            var11_3 = 0;
            var12_4 = 0;
            var3_5 = ((long)var11_3 << 32 | (long)var12_4 << 48 >>> 32 | (long)var2_2 << 48 >>> 48) ^ SpeedMine.e;
            Intrinsics.checkNotNullParameter(var1_1, (String)SpeedMine.zkmBootstrap("j", (int)29425, (long)(6974277640053812291L ^ var3_5)));
            var6_6 = SpeedMine.miningQueue;
            var5_7 = ZkmReferences.zkmBootstrap("\u00c5", (long)-8425616574246565867L, (long)var3_5);
            v0 = var6_6;
            if (var5_7 != false) ** GOTO lbl17
            if (!(v0 instanceof Collection)) ** GOTO lbl-1000
            v0 = (Collection)var6_6;
            if (var5_7 == false) {
                ** if (!v0.isEmpty()) goto lbl-1000
lbl-1000:
                // 1 sources

                {
                    v1 = false;
                    ** GOTO lbl26
                }
            }
            ** GOTO lbl17
lbl-1000:
            // 2 sources

            {
                v0 = var6_6;
lbl17:
                // 4 sources

                for (T var9_9 : v0) {
                    var10_10 = (MiningData)var9_9;
                    v2 = Intrinsics.areEqual(var10_10.getPos(), var1_1);
                    while (v2) {
                        v2 = true;
                        if (var2_2 >= 0 || var5_7 != false) continue;
                        break block5;
                    }
                }
                v1 = false;
            }
        }
        return v1;
    }

    private final void closeContainer() {
        PacketUtil.sendPacket(new CloseHandledScreenC2SPacket(Globals.getPlayer().currentScreenHandler.syncId));
    }

    private final float getBreakDelta(BlockState blockState, BlockPos blockPos, int n, boolean bl) {
        if (Intrinsics.areEqual(blockState, Blocks.AIR.getDefaultState())) {
            return 0.0f;
        }
        float f = blockState.getHardness(Globals.getWorld(), blockPos);
        if (f < 0.0f) {
            return 0.0f;
        }
        ItemStack itemStack = n == -1 ? Globals.getPlayer().getMainHandStack() : Globals.getPlayer().getInventory().getStack(n);
        Intrinsics.checkNotNull(itemStack);
        ItemStack itemStack2 = itemStack;
        return this.getBreakDelta(blockState, n, bl) / f / (!blockState.isToolRequired() || itemStack2.isSuitableFor(blockState) ? 30.0f : 100.0f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isAutoCrystalTarget(BlockPos blockPos) {
        if (!AutoCrystal.INSTANCE.getEnabled()) return false;
        if (AutoCrystal.INSTANCE.getTarget() == null) return false;
        double d = blockPos.getY();
        PlayerEntity playerEntity = AutoCrystal.INSTANCE.getTarget();
        Intrinsics.checkNotNull(playerEntity);
        if (!(d > playerEntity.getY() + 1.0)) return false;
        return true;
    }

    private final float getMiningSpeed(BlockState blockState, int n) {
        float f = 1.0f;
        if (n != -1 && !Globals.getPlayer().getInventory().getStack(n).isEmpty()) {
            f *= Globals.getPlayer().getInventory().getStack(n).getMiningSpeedMultiplier(blockState);
        }
        return f;
    }

    private final float getBreakDelta(BlockState blockState, int n, boolean bl) {
        float f = this.getMiningSpeed(blockState, n);
        if (f > 1.0f && n != -1) {
            ItemStack itemStack = Globals.getPlayer().getInventory().getStack(n);
            Intrinsics.checkNotNullExpressionValue(itemStack, "getItem(...)");
            ItemStack itemStack2 = itemStack;
            RegistryKey<Enchantment> registryKey = Enchantments.EFFICIENCY;
            Intrinsics.checkNotNullExpressionValue(registryKey, "EFFICIENCY");
            int n2 = EnchantUtil.getEnchantmentLevel(itemStack2, registryKey);
            if (n2 > 0 && !itemStack2.isEmpty()) {
                f += (float)(StrictMath.pow(n2, 2.0) + 1.0);
            }
        }
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.HASTE)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.HASTE);
            Intrinsics.checkNotNull(statusEffectInstance);
            f *= 1.0f + (float)(statusEffectInstance.getAmplifier() + 1) * 0.2f;
        }
        if (Globals.getPlayer().hasStatusEffect(StatusEffects.MINING_FATIGUE)) {
            StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.MINING_FATIGUE);
            Intrinsics.checkNotNull(statusEffectInstance);
            f *= (float)Math.pow(0.3f, statusEffectInstance.getAmplifier() + 1);
        }
        if (Globals.getPlayer().isSubmergedInWater()) {
            EntityAttributeInstance entityAttributeInstance = Globals.getPlayer().getAttributeInstance(EntityAttributes.SUBMERGED_MINING_SPEED);
            Intrinsics.checkNotNull(entityAttributeInstance);
            f *= (float)entityAttributeInstance.getValue();
        }
        if (!bl && !FreeCam.INSTANCE.getEnabled()) {
            f /= 5.0f;
        }
        return f < 0.0f ? 0.0f : f * this.getFactor();
    }

    private final int getToolSlot(BlockPos blockPos) {
        int n = this.findBestToolSlot(blockPos, true);
        int n2 = MiningData.zkmIntConstant();
        int n3 = n;
        if (n2 != 0) {
            if (n3 != -1) {
                return n;
            }
            n3 = this.findBestToolSlot(blockPos, false);
        }
        return n3;
    }

    private final int findBestToolSlot(BlockPos blockPos, boolean bl) {
        int n = -1;
        float f = bl ? Float.MAX_VALUE : Float.MIN_VALUE;
        BlockState blockState = Globals.getWorld().getBlockState(blockPos);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        BlockState blockState2 = blockState;
        if (blockState2.getBlock() instanceof AirBlock) {
            return -1;
        }
        for (int i = 9; i < 45; ++i) {
            ItemStack itemStack;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(i >= 36 ? i - 36 : i), "getItem(...)");
            if (Intrinsics.areEqual(itemStack, ItemStack.EMPTY) || !itemStack.isSuitableFor(blockState2) && bl || itemStack.getMaxDamage() - itemStack.getDamage() <= 4 && ToolSaver.INSTANCE.getEnabled()) continue;
            RegistryKey<Enchantment> registryKey = Enchantments.EFFICIENCY;
            Intrinsics.checkNotNullExpressionValue(registryKey, "EFFICIENCY");
            float f2 = (float)EnchantUtil.getEnchantmentLevel(itemStack, registryKey) + itemStack.getMiningSpeedMultiplier(Globals.getWorld().getBlockState(blockPos));
            if (!bl) {
                if (!(f2 > f)) continue;
                f = f2;
                n = i;
                continue;
            }
            if (!(f2 < f)) continue;
            f = f2;
            n = i;
        }
        return n >= 36 ? n - 36 : n;
    }

    private final boolean getBreakTime(BlockPos blockPos) {
        BlockState blockState = Globals.getWorld().getBlockState(blockPos);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        BlockState blockState2 = blockState;
        return !(blockState2.getBlock().getHardness() == -1.0f) && (Globals.getPlayer().getEyePos().distanceTo(blockPos.toCenterPos()) <= Globals.getPlayer().getBlockInteractionRange() + 0.5 || this.getDoubleMine() && !Intrinsics.areEqual(blockPos, currentPos));
    }

    /*
     * Unable to fully structure code
     */
    private final void switchToTool(int var1_1, int var2_2) {
        block13: {
            block14: {
                block9: {
                    block12: {
                        block10: {
                            block11: {
                                block8: {
                                    block7: {
                                        var16_3 = MiningData.zkmIntConstant();
                                        if (this.FC() == SpeedMineSwitchMode.Alternative) break block7;
                                        v0 = var1_1;
                                        v1 = 9;
                                        if (var16_3 == 0) break block8;
                                        if (v0 < v1) break block9;
                                    }
                                    v0 = var2_2;
                                    v1 = -1;
                                }
                                if (var16_3 == 0) break block10;
                                if (v0 != v1) break block11;
                                v2 = var1_1;
                                v3 = 9;
                                if (var16_3 == 0) ** GOTO lbl19
                                if (v2 < v3) {
                                    v2 = var1_1;
                                    v3 = 36;
lbl19:
                                    // 2 sources

                                    v4 = v2 + v3;
                                } else {
                                    v4 = var1_1;
                                }
                                InteractionUtil.clickSlot$default(v4, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
                                if (var16_3 != 0) break block12;
                            }
                            v0 = var2_2;
                            v1 = 9;
                        }
                        if (var16_3 == 0) ** GOTO lbl32
                        if (v0 < v1) {
                            v0 = var2_2;
                            v1 = 36;
lbl32:
                            // 2 sources

                            v5 = v0 + v1;
                        } else {
                            v5 = var2_2;
                        }
                        InteractionUtil.clickSlot$default(v5, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
                    }
                    this.closeContainer();
                    if (var16_3 != 0) break block13;
                }
                if (this.FC() != SpeedMineSwitchMode.Silent) break block14;
                InventoryUtil.INSTANCE.syncSelectedSlot(var1_1);
                if (var16_3 != 0) break block13;
            }
            InventoryUtil.INSTANCE.selectSlot(var1_1);
        }
        AutoCrystal.INSTANCE.getMineSyncFlag().set(false);
        SpeedMine.atomicBoolean.set(false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean canMineMore() {
        int n = MiningData.zkmIntConstant();
        boolean bl = this.getDoubleMine();
        if (n != 0) {
            if (bl) {
                boolean bl2 = miningQueue.size();
                if (n == 0) return bl2;
                if (bl2 <= true) return false;
                return true;
            }
            bl = ((Collection)miningQueue).isEmpty();
        }
        if (n != 0) {
            if (bl) return false;
            bl = true;
        }
        if (!bl) return false;
        float f2 = ((MiningData)CollectionsKt.first(miningQueue)).G() - -1.0f;
        f2 = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
        if (n != 0) {
            f2 = f2 == false ? 1.0f : 0.0f;
        }
        if (f2 != false) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     */
    @NotNull
    public final List getMinePositions(int var1_1, boolean var2_2, short var3_3, char var4_4) {
        block16: {
            block15: {
                block12: {
                    block14: {
                        block13: {
                            var5_5 = ((long)var1_1 << 32 | (long)var3_3 << 48 >>> 32 | (long)var4_4 << 48 >>> 48) ^ SpeedMine.e;
                            var7_6 = var5_5 ^ 129518990007062L;
                            var11_7 = ZkmReferences.zkmBootstrap("\u00c5", (long)-869842054865323965L, (long)var5_5);
                            v0 /* !! */  = SpeedMine.miningQueue.isEmpty();
                            if (var11_7 != false) {
                                if (v0 /* !! */  != 0) {
                                    return CollectionsKt.emptyList();
                                }
                                v0 /* !! */  = SpeedMine.miningQueue.size();
                            }
                            v1 = 1;
                            if (var11_7 == false) break block12;
                            if (v0 /* !! */  != v1) break block13;
                            var13_8 = (MiningData)CollectionsKt.first(SpeedMine.miningQueue);
                            v0 /* !! */  = (int)SpeedMine.INSTANCE.isFinished((MiningData)var13_8, var7_6);
                            if (var3_3 > 0) {
                                if (v0 /* !! */  != 0) {
                                    return CollectionsKt.listOf(var13_8.getPos());
                                }
                                v0 /* !! */  = (int)var11_7;
                            }
                            if (var3_3 <= 0) break block14;
                            if (v0 /* !! */  != 0) break block15;
                        }
                        v0 /* !! */  = SpeedMine.miningQueue.size();
                    }
                    if (var11_7 == false) break block16;
                    v1 = 2;
                }
                if (v0 /* !! */  == v1) {
                    var13_8 = SpeedMine.miningQueue;
                    var15_9 = var13_8.iterator();
                    block0: while (true) {
                        if (!var15_9.hasNext()) {
                            throw new NoSuchElementException((String)SpeedMine.zkmBootstrap("j", (int)16918, (long)(606952685197403343L ^ var5_5)));
                        }
                        v2 = var15_9.next();
                        block1: while (true) {
                            v3 = var16_10 = v2;
                            do {
                                if (!Intrinsics.areEqual((var17_11 = (MiningData)v3).getPos(), SpeedMine.currentPos)) continue block0;
                                v2 = var16_10;
                                if (var4_4 <= '\u0000') continue block1;
                            } while (var11_7 == false);
                            break;
                        }
                        break;
                    }
                    var12_12 = (MiningData)v2;
                    v0 /* !! */  = (int)this.isFinished(var12_12, var7_6);
                    if (var4_4 >= '\u0000' && var11_7 != false) {
                        if (v0 /* !! */  != 0) {
                            var13_8 = new BlockPos[]{((MiningData)CollectionsKt.first(SpeedMine.miningQueue)).getPos(), var12_12.getPos()};
                            return CollectionsKt.listOf(var13_8);
                        } else {
                            ** GOTO lbl49
                        }
                    } else {
                        ** GOTO lbl48
                    }
                }
                break block15;
lbl48:
                // 2 sources

                break block16;
            }
            v0 /* !! */  = (int)SpeedMine.timer.hasPassed((int)SpeedMine.zkmBootstrap2("v", (int)28651, (long)(4491813575798656391L ^ var5_5)));
        }
        if (v0 /* !! */  == 0 && SpeedMine.i != null && var2_2) {
            v4 = SpeedMine.i;
            Intrinsics.checkNotNull(v4);
            return CollectionsKt.listOf(v4);
        }
        return CollectionsKt.emptyList();
    }

    /*
     * WARNING - void declaration
     */
    public static List getMinePositions$default(SpeedMine speedMine, boolean bl, int n, Object object, long l) {
        void var2_3;
        long l2;
        long l3 = (l2 = e ^ l2) ^ 0x5B798F689D7CL;
        int n2 = (int)(l3 >>> 32);
        int n3 = (int)(l3 << 32 >>> 48);
        int n4 = (int)(l3 << 48 >>> 48);
        int n5 = MiningData.zkmIntConstant();
        boolean bl2 = var2_3 & 1;
        if (n5 != 0) {
            bl2 = true;
        }
        boolean bl3 = bl2;
        return speedMine.getMinePositions(n2, bl3, (short)n3, (char)n4);
    }

    /*
     * Enabled aggressive block sorting
     */
    private final boolean isFinished(MiningData miningData, long l) {
        float f;
        block5: {
            CallSite callSite;
            block6: {
                CallSite callSite2;
                block7: {
                    block4: {
                        l = e ^ l;
                        callSite2 = ZkmReferences.zkmBootstrap("\u00c5", (long)-1002233089921011440L, (long)l);
                        float f2 = miningData.O() - 1.0f;
                        f = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                        if (callSite2 != false) break block4;
                        if (f >= 0) break block5;
                        float f3 = miningData.O() + (miningData.O() - miningData.G()) * 2.5f - 1.0f;
                        f = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                    }
                    callSite = callSite2;
                    if (l < 0L) break block6;
                    if (callSite != false) break block7;
                    if (f < 0) break block5;
                    f = (float)Globals.getWorld().isAir(miningData.getPos());
                }
                callSite = callSite2;
            }
            if (callSite != false) return (boolean)f;
            if (f == false) {
                f = 1.0f;
                return (boolean)f;
            }
        }
        f = 0.0f;
        return (boolean)f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible() {
        int n = MiningData.zkmIntConstant();
        SpeedMine speedMine = INSTANCE;
        if (n != 0) {
            if (speedMine.getMode() == SpeedMineMode.DAMAGE) return false;
            speedMine = INSTANCE;
        }
        boolean bl = speedMine.getRotate();
        if (n == 0) return bl;
        if (!bl) return false;
        return true;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE && INSTANCE.getRebreak() == SpeedMineRebreakMode.GRIM;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible4() {
        int n = MiningData.zkmIntConstant();
        SpeedMine speedMine = INSTANCE;
        if (n != 0) {
            if (speedMine.getMode() == SpeedMineMode.DAMAGE) return false;
            speedMine = INSTANCE;
        }
        if (speedMine.getRebreak() != SpeedMineRebreakMode.GRIM) return false;
        return true;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible6() {
        int n = MiningData.zkmIntConstant();
        SpeedMine speedMine = INSTANCE;
        if (n != 0) {
            if (speedMine.getMode() == SpeedMineMode.DAMAGE) return false;
            speedMine = INSTANCE;
        }
        boolean bl = speedMine.getDoubleMine();
        if (n == 0) return bl;
        if (!bl) return false;
        return true;
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE && !INSTANCE.getDoubleMine();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getMode() == SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible19() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible20() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible21() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean settingVisible22() {
        return INSTANCE.getMode() != SpeedMineMode.DAMAGE;
    }

    private static final boolean onPlayerUpdate$lambda$0(MiningData miningData) {
        long l = e ^ 0x6E0FA306CA1AL;
        long l2 = l ^ 0x5A9EEBF6C6A5L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        return MiningData.Y((short)n, n2, miningData, false, 1, null, (short)n3);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final void onAfterSendPacket$lambda$0() {
        long l = e ^ 0x31143E3C5CEAL;
        long l2 = l ^ 0x60CB1CB9EB7FL;
        Iterable iterable = miningQueue;
        Iterator iterator2 = iterable.iterator();
        int n = MiningData.zkmIntConstant();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            MiningData miningData = (MiningData)t;
            miningData.a(l2, MiningPauseReason.SWITCH);
            if (n != 0 && n != 0) continue;
            break;
        }
    }

    public static final int access$getToolSlot(SpeedMine speedMine, BlockPos blockPos) {
        return speedMine.getToolSlot(blockPos);
    }

    public static final SpeedMineSwapTimingMode access$getSwapTiming(SpeedMine speedMine) {
        return speedMine.getSwapTiming();
    }

    public static final void access$switchToTool(SpeedMine speedMine, int n, int n2) {
        speedMine.switchToTool(n, n2);
    }

    public static final SpeedMineDoubleMinePacketOrderMode access$getDoubleMinePacketOrder(SpeedMine speedMine) {
        return speedMine.getDoubleMinePacketOrder();
    }

    public static final SpeedMineStartMode access$getStartMode(SpeedMine speedMine) {
        return speedMine.getStartMode();
    }

    public static final boolean access$getSwapDelay(SpeedMine speedMine) {
        return speedMine.getSwapDelay();
    }

    public static final BlockPos getCurrentPos() {
        return currentPos;
    }

    public static final boolean access$getBreakTime(SpeedMine speedMine, BlockPos blockPos) {
        return speedMine.getBreakTime(blockPos);
    }

    public static final SpeedMinePauseEatMode access$getPauseEat(SpeedMine speedMine) {
        return speedMine.getPauseEat();
    }

    public static final float access$getBreakDelta(SpeedMine speedMine, BlockState blockState, BlockPos blockPos, int n, boolean bl) {
        return speedMine.getBreakDelta(blockState, blockPos, n, bl);
    }

    public static final ConcurrentHashMap getRecentBreaks() {
        return recentBreaks;
    }

    public static final boolean access$getAwait(SpeedMine speedMine) {
        return speedMine.getAwait();
    }

    public static final boolean access$getRotate(SpeedMine speedMine) {
        return speedMine.getRotate();
    }

    public static final boolean access$isAutoCrystalTarget(SpeedMine speedMine, BlockPos blockPos) {
        return speedMine.isAutoCrystalTarget(blockPos);
    }

    public static final boolean access$getAwaitAutoCrystal(SpeedMine speedMine) {
        return speedMine.getAwaitAutoCrystal();
    }

    public static final Timer getTimer() {
        return timer;
    }

    public static final void a(BlockPos blockPos) {
        i = blockPos;
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
                                SpeedMine.e = ZkmKeys.a(-4621689184939157071L, -4272911587249371302L, MethodHandles.lookup().lookupClass()).a(74883402458109L);
                                var31 = SpeedMine.e ^ 115510377353317L;
                                SpeedMine.k = new HashMap<K, V>(13);
                                SpeedMine.zkmSetFlag("VEFMUb");
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
                                var29_3 = new String[91];
                                var27_4 = 0;
                                var26_5 = "\u00f8\u00a4\u00bc\u0085O\u007f\u0019\u00b4\u00e7\u00db\u0092\u00cc\u0095-\u00a1\u00dd\u0010\u00ab\u00e2v2\u0094c\u0099\u00ba\u00ea\u00e4\u0083=\u00a5\u00b5M\u00a8 \u00fe8\u00cf!\u001f\u00a0\u00eei\u00ac\u00ca\u00d3\u00c1\u00e0\u001e)C\u00c0(\u0080^\b\u00c6cY&\u001bj\u00e4*\u001b\u00c4\u00f5\u0098\u0098O\u009c\u00b2\u00df\u00d5K!H\rU\u00bf\u00bf\u0015\u00c6\u00df\u00b6\u00fam\u00d5ex\u00d4\u0084\u00fa\u00dc\u00be\u00d8$\u0098He9\r<\u00f6ch\u009e-\u00d8?Y\u008e\u0080c\u0001\u00dc\\\u0081\u009b\u00c8\u00bf\u00df2l\u0019\u0087\\\u001d]\u008b!1R\u00a5w\u00ecp\u00a1\u009bX\u00ediQ\u00fb\u0093>\u00a4\u00e9h\u00af.\u00b5\u00fe\u0098%O\u00d3\u0015\u00eb.\u00e9\u00ac\u009d\u00c1\u0014\u00a9\u00f3v=>\u00ce\u001d\u00c9\rfJ7l\u00dc\u0093\u000f\u00d2\u00df\u00ff\u00d4\u00ae8\u0082s?\u0003$$dx\u00c8\u0086\u00bfG23z\u00e6\u00f6\u00c3m\u00ca\u0011\u00f6B\u00e8\u00ab\u00c6-\u00e3V\u00fbf\u00f9'(\u00b6\u00a2v\u001c\u00b0\u0019\u00f3\u00e1\u001fty\u00c69;\u00a9\u00e6\u0082(\u00c9\u0011\u00cd,\b\u008a\u0093\u00d5r\u00af?\u00bb7\u00b3\u0017\u00f9\u00b2\u0003_\u0004PA \u00a5<\u00be\u001c\u00f5a\u00e7E\u00af\u008c\u00bc\u00eb\u00e0\u00b1\u0007\u00d6\u00ccmV$\u00f9EW\u00c6\u00fa\u00ff\u0098\u00058\u0002\u00c1gX\u0082\u00c3\u00dc\u00c0\u00d3\u00a6d\u00f5/z\u001a\u00fc\u0017\u00db\u0001\u00d5_T\u00d6C+7\u00bbM\u00b3\f\u00bc\u001f\u00d8u\u00f8\u0011\u009b\u008b \u00fe\u00d9\u00de\u00e8\u00b9\u0017\\\u00d0\u00c8L*\u0007\u00e5>\f\u00b2V\u00fd\u00b6\u00cf\u00fd\u0091)\u00d5\u00da\u00d1\u00e5\u00fd\u00f5R\u009be\u00aaC~_\u009b\fC\u00ff\u00e5?=\u001fu\u0095-\u00aa\u00a3\u0087\u008f\u00c0!\u0010\u00ac\u00a8\u0084\u008d\u0089\u008b\u0094\n\u000b\u00e7v\u008dj*\u00cd\u00ba K\u0084nD3\u00bb\u009b\u00bbW@\u00ac*]\u00eb\u00d9\u00c8\u0016\u001e\u00ee+\u00e60O6\u00eam2X\u0081\u00eb\u00a5\u00e0\u0010\u0095B\u00ff\u00e4 \u00d9\u00b4\u00e4\u0087k\u00b8zR\u0089uQ8\u00e2\u00a5P1\f\u0098 \u0094\u00bd\u00f4\u00cb\u007fV9\u00d0\u00adW\u0080xY\u00bd1\u00fep\u00c3\u0019\u0096,\u00dd\u0017\u00ecg\u001f\u00ec\u00c9\"\u00ba\u00b1\u0019#u\u0002\u00d3\u00df\u0082\u008b\u00b0\u0084Q\u0015^\u00efz=\u00ffw\u00102\\\u000fe\u00f7\u0013)\u0093\u00b1\u00af\u00ac\u00a1\u00df!\u001e\u00f0 O\u009a\u00aa\u0094\u00b0YU\u008e\u0081\u00c2\u0004\u0080akI\u00b3\u008c\u00c9z\u00c0\u00f5\u009a\f\u00058\u0015%7&OOS \u00f7\u009f\u009c\u0089\u00b3\u000b\t\u00a9y\u00b3g<\u0084\u00ba\u00eagX\u00d6\u00b6\u00d1\u0015\u00e7\u0017q\u00890TE\u00b8\u00eb\u00ac\u00fe0&\u00b6+\u0011`\u00e0\u00fa\u00df,\u0092J\u00ae-(\tk[M\u000b\u000397\u00e6k\u00d0\u0089\u00d0B\u009f!\u00d8\u0002i\u0099\u0083\u008e`\u0086\u001f\u00ca\u00e6\u0092\u0014\u00c3\u00b6\u0097\u00b9\u00bd \u0099\u00ac2bCU\u0089\t\u00a6\u001e\u00a7\u009c\u00af\u00e5\u00aa9\u0015\u00ce\u00fc\u0004\u001bv\u0087\u00dbj\u00ccR\u00dfuh\u00f0\u00bb \u008c\u0080\u0018\u0017\u00b6i\u00e4\u00b9\u00927\u00d4\u009dH.\u00b7\u00f7y\u008b\u0096\u008c%\u00da5F\u00c4\u00b9\u00f5\u0001\u00ad\u0015\u008e\u00e0\u0010\u00aaP\u0097V\u00faz+4\u00f6\u00d0a*\u0005;vH\u0010&V\u0018\u00a6\u0017\rr\\\u00a5$\u00cc\u001d\u00cb\u00e3\u00cf\u00ed\u0018]\u00897<\u00cf5\u00ee\u0095\u0017\u008a\u0016\u00cd\u00a9\u00f6O\u00155\u0013\u0010|T\u00e6\u00a2\u00f6\u0018m\u00b7u=\u0080\u0098;\u009c\u00c5\u00e7\u00c26\u0088\u0086\\S\u00dd\u0080tf\u00e4\u00cb\u00f0\u00eb8J\u0014\u000f\u00ac\u00c6CV|fQ\u00bf*A_\u00dc\u00d3\u00bf\u00bfi\u00d8\u00b4\u001a\u0000\nT\u008210g\u00ce\u00b6R\u00c6\u00b3AsG\u00a7\u00ad\u00f4\u008cU\u008a\u00cda\u00db\u00ad\u000b\u001c\u0010\u00b48s\u00e7^>(\rA\u00b1\u0087I4d1cG>\u00d5*\u00de:g\u0087\u009b>\u00d4\u00f691\u00b94\u00f4\u008a\u00e7\u0013`O\u00a6\u0099\u00a4\u00d7\u000e\u00f4\u00a59\u0013 R8\u00f5\u000bx2\u00ae\u00bb\u0003M\u0097\u00fe\u0016\u0013\u00d3\u0011\u00a8\u00d7\u009aPG\u00e3e'\u0095\u00cb|\u00ac\u00c8?y\u00d9(\u00f3b\u001fQ\u00a6v\u008e\u001e\u00eeed\u0091\u00e9\u00b6+\u00be1\n\u0018LI\u00bc\u00ae\u008b\u00ff8v\u00ef?~\u0083\u008e\u0010\u0016Z\u00d3\u0084\u00d3\u00c4\u00bc Zw\u00d5j6\u0014\t\u00ca6\u00ac\u00179\u00ea\u0004u.\u00daM%\u000fC\u00e7\u0088\u00f5>Z\u00e3\u0000&\u00ae\u009c5(\u008ds\u00f7v\u00be\u009a^\u00c2\u00b7\u00daWJ\u0003\u00fa\u00b5'I\u0015\u00aeg\u00be5\u00e0a\u00b9S\u00b9\u00f5;\u0091]\"\u00dc\u00fb\u00b9\u0003w\u00ea\u00db\u00d40\u00c7ZD\u00a0V\u00ba\u00fde\u0006\u0096\u00d4qA\u00d8\u007fo\u00aa,{m\"\"\u00c3\u00cf(\u0082\u00cf\u0094\u001b\u00c8%c\u00a5&\u00e7\u00ca\u00ee\u009fR\u00f2F\u00a3\u0085\u00d6\u0014\u00f8\u001a\u0087\u0018\r\u00d2\u00e8\u00d0\u0088F\u0004\u0010oHV9=I\u00a8\u00c7\b'\u009cW\u00bb\u0005\u0012\u0090(F\b`|\u0012\t\u00f4\u00b8\u00e8\u00e0>_\u008fy\"\u0098\u00a6dY^.\u00db;1\u00e3n\u0005\u00d4\u00a56]\u0093\u00818\u00d6\u00cc\u00c9\u0080\u00c6\u00de\u0098O\u00f9\u0081Ez\u00d1B`\u00eb\u00e0\u0081j\u00c8\u00a8\u0088\u0003\u00a7\u00d2\u00fa\u0083\u00ea$1q|I\u00ed 3\u00af\u00e7\u0084\u00cau\n,uE\u00f2\u001f?HWn\u00c4R\u00dbqg^\u0011-\u001c\u00df\u0017I$\u00c0\u00b5;>\u001f\u008c>5\u00cb\u0015\u00e4?\u00e7>~\u00b3a4\u0004\u00c7\u00fdkyC\b)\u00de\u0096\u00d9\u0099\u00c4er\u00a6\u00c6\u00bc\u00ac{u\u00c9/\u00cc\u00e9\n\u00ecj\u0019!\u00d8d ~\u0017^\u00fe\u0099l\r\u00bf.\u00b0\u00dbWV\u00beI\u000f3\u00f3\u00a7\u00b1}\u00b9\u0007\u0081(|\n\u007fh,\u00e6\u008e}\u00ff\u0080\u00c4\u00ab5i\u0007*\u0012@\u00c0\u0010\u00b7\u00f1\u00bc2\u009b\u0086\u0018N\u00ba\u00bb\u00a8V\u001d\u0018\u008de\u0010\u0097\u00b2\f\u00164$\u00c1 \u00e5\u00f0<\u00ae\u00932Q% \u00ffAb\u009bf\u00e7\u0090\u00f7\u00d4\u00bf\u00a5\u0080\u0097\u00f1\u00b0p\u00bde\u00f1\u0091\u00ef\u00cd\u001c\u00f4\n'\u00e5V\u00e1\u00beXn\u0010\u00c0\u0090#\u00db\u00f5\u00c3\u0000\u001a\u00d0\u00c7\u00c6\u00ee\u00d7\u0000\u00ccX\u0010\u00fcN\u00c4^\u00ad0\u0010\u00df-\u008f\u0086C\u0094sU\u00de\u0018\u008b\u00ba\u0081 \u0012\\\u00eb\u00ec\u001b\u00e8\u00d2\u009a\u00ab\u00b4\u00ed\u00f6\u00b2u\u008b8\u00f6\u00ea\u00e7\u001c \u00c3\u00efm4;\u00e1K\u001e\u00eadR\u00b7\u00c9\u00b8\u0098$\u00ae\u0083\u00a3:I._\u0081\u00c2\trU\u00c4\u00a8\u00a7WXO[\u008b\u00f0\u00ad\u00bc\u0099\u00de\u00a2\u0017T/dVhC\u00ece\u00dc1\u0089\u00b9\u0006\u00b9\u00bfD'\u001f\u00a3\u000e\u00d0\u0097B\u00af\u0015e\u00a9\u00bb\u00f3\u00bd\u00d58\rT\u0011\u00f2E\u0096\u00ba\u00ae\u00cf\t\u0000LG\u0097\u00b0\u00dc\u001de\u00f8\u00caP;\u0017\u00c3\u0094\u00ca!\u00f4\u008a\u0011~\u00cf\u0013n\u0005pSTJd\u0002\u0096\u00b3k\u00e9\u00868\u001d\u00ca\u00f1\u00046I\u0095\u00ce\u0091\u009f\u00f0\u00ce\u00d4.9S\u00aa\u0088\u0097\u00d53\u0093j\u000fKA%\u008e\u00aa\u001d\u0099\u0086m\u0014\u00a0\u0099[@\u00f6\u0014\u00da\u0090\u00a0\u00d3`\u007f\u00a4\u00801\u0010\u0003\u00d8\u00ba\u00d0\u00bf\u00b4\u0010\u00bff\u00a8\u001ba\u00dduP$'D\u00b3\u00dc\u00c4\u00fc\u0081\u0018\u00dd\u00bb=\u00af\u00a6xs\u0003\u00a0P\u00ab\u00cc\u0000GR\u00f6\u000f\u0083\u0080[\u00e7Xy% \u0080\u0080\u00a3\u00a6g\u0001\u00cd\u009e\u008c_\u00d5I\u00c8x\u00ed\u0013\u0089\u00e9\u0087z\u00a4#\u0000y%\u0094\u00ac\u00ab_\u0001?\u00bc \u00ea0c\u0099\u007fX\u0088r\u00a2\u00e4Fz\u0095\u00b1\u00ef\u00db\u0082z\u00be\u00fe\u0095A(\u00ad\u00de\u00aa4\u00a4-B\u00c6\\ \u009a\u0096\u00f92.\u00c3!JM\u00e4\u00edL\u00ad\u00a8\u0090\u00cf\u0096rb\u00f3/\u009e\u0095ep\u00d8k\u009f\u0003b\u00f6| u\u001c\u00aa8=\u0099\u00c4N\u0092\u0092H\u0013h6\u00ef\u0091f,'9h\u0081cl\u00804\u009c\u00c5\u00d8\u00e8\u00f9M \u00bbM\u0097\u00ed25\u00b8\u00ba9+\u00e7-N\u00c3Vm\u00af\u00d9k\u00c5\u009b\u00dc\u00fd\u00b4\u00c7\u007f\u00cbpt\u00be\u00bfq\u0010\u00ddB\u008d\u0019l,5G%\u00a2\u00c3\u0086\u00fb\u00f1\u0088\u00c3\u0010\u00a7^\u0095\u00b9\u00e1\u00a7\u00bbH\u0097\u008a\u0090\u00a4\u00df\r\u00d3\u0084(|\u0085\u00c6p%~4\u0092\u00edE\u001b\u0002\u0083\u00a1\u008b_2\u00d6\u000e\u00c0K&\u00e2r6\u008b\u009a\u00ca\u00a64>\u00f7H\u009c\u00a8d\u00bf\u00d1\u0015\u0013 \u00aa\u00b9O\u0000{\u001a\u00c2\u0093\u008b\u00c9\u00c6:y\u0094\u0080\u0082l\\f0*\u00a5\u00e4f\u00f6\u00e8\u00f0\u00be\u00b8|\u009c\u0014\u0010\u0006\n\u0092\u00d1\u00d9t}\u00d1c5\u00e7W\u00cc\u00ea\u00ba]\u0018\u00f3\u00c8\u00b0{\u00f0\u0085O\u001dZ\u00c1]\u0006\u00bc/G\u00c28\u00aa\u00fc,\u008d\u00d5R\u009e\u0010X\u00dd\u00ce\u0015\u00f8\u00ae\u00d9D\u0094Q\u00b1j\u0003\u00df&\u00e3\u0018\u0010\u0090?\u0097\u0005l\u00b9\u00b1\u00a9\u00e0\u001c\u00a4/_I\u0090\u00a8\u0007\u0094\u00e2\u00c0g\\\u00e28\u0087\u0012\u00b3?\u00fcTfuq\u00d4\u00daw`\u00ac\u0006r\u00a1\u00ddU\u00b7p\u00b5\u0019\u00b6}/\u0085\u0089\u008a\u00e5+\u00ac\u00a4\u00d8t\u00da\u0012\u0003N3M\u00ad'\u0088`\u00cbuj\u001d\u0002b\u00c4\u00e5W\u00be> \u00e87\u0098F\u009do7\u00dc\u00d8\u00cbV\u00e5\u00885s\u00a5#\u0000\u00ca\u0013\u0087I\u0084)\u00e1\u00aa\u0019>9\u00e2\u00b31\u0018V\u001e!\u00db|!U'\b5\u00d0\u000b)}\u00e3\u00f74\u009eWw\u00bd\u00dc\u00e1\u00be D\u0004\u00fa;5=\u008dm\u001c4\u00de\u00d0\u00a9\u00f4\u0096]\n,\u00f2\u00d0aI\u00c9\u009a'vM\u00f7\u00de\u009em \u0018\u00a7\u0012\u009d]\u0015\u0011\u00e7\u00bc:\u0092\n=\u0082\u001e\u00f6\u00f6\u0010\u00ba\u0091\u0018\u00b7\u00f5\u0089\f\u0010UJ\u00e5q\u000e\u0017\u00a8|h?\u00b7\u0019\n\u00bc\u0007\u00e7 TM+\u00df\u0086\u00c4\u00e9E\u00d7$vD \u00b9\f\u00af2\u00af\u00f7g\u0006\u00de\u00fb\u009e\u00a2\u00eet+\u00f6\u0081gH\u0010\u0085\u00a8\u0096\u00ec4y\u00f6\u00afN\u00ffLTM\t@: \u00b0\u00fbs\u009b\u0013y\u0083\u00fb\u008bRWE\u00bd\u009bK7\u00e4\u00c9\u008a\u0015g\u00a0\u0015\u0018\f\u0017k\u0089S7\u000f\u00ce(?\u000b\u00ea`\u00b8[\u00b9\u00145\u00b3W\u00bb\u00c5cK\u00f6{\u00e6\u0098Rj\nZ\u00bf\u00ba\u0085\u00f8\u008c\u00d3\u00db\u00ea\u00d6\u00e4\u00d8E<\u0019r\u00a8\u00970\u008b\u008af\u00cf\u001d\u00f2\u0093\u0093\u00d1+\u00a9\u00e39\u0094g\u009d^e\u0082\u0097\u00beU\u000e\u00e9\u00be<Sq\u001fe\u00c9\u00fc\u00d8\u00a4\u00bc\u00cbu\u00c1)E\\\u00cb\u00f8H[,GyH\u00a8v\u00d6\u0091DG~(\u0094\u0010\u009e\u00d4C\u00a2\u0081\u00b7\"\u00a2.TP\u00aa#N\u009f\u008f\u0084\u0005X\u0082#o|\u0081O\u00d5\u00c3\u00ea\u00e9}\u00ae\u000er\u0087d];\u001fG\u0094\u00eb\u0091\u00f1\n\u00c0\u00ad\u00a2\n\u0090\u00e0\u00edt\u00c8\u00e1\u0015\u00a7xo\u00d6\u00bd\u00dd\u00dc8\u00fb\u0088B\u0093\u00b5\u0013\u0088\u00b9:t\u00ed<H\u00a7u\u00ee\u009c\u009b74\u00de\u00dbf\u00b5w\u0012\u009a9\u00a2\u00b4\u00f7\u00b6\u00d4\u00e2\u00f1\u00c9\u007f\u000e\u00dc\u00b0\u00bd{\u00f15\u0015uD}\u0015f-\u00fa\u00ach\u008d\u0019\u0010\u00e4`\u0095D\u0098>[G\u00df\rj![\u00bdN>(\u00e9\u00b1)\u0007\u00da{\u00b7\u009c\u008ac\u00c4\t\u00d9\u00d23(H\u0003\u009e1;\u00cc%Dp\u00e8\u00bf\u00b6\u0093\"\u008c\u00eb\u008c\u00f0\u0099z\u00fe\u00fe\u00fa\u0003 \u00b3\u0096\u00d5\u0007\u008bVg\u00f9\u009b6~\u00d6\u00e6^\u008d.,\u0096_t\u00dd\u001a\u0096\u00aaNs\t\u0093\u0000\u00ad\u008c\u0085\u0018C\u00df\u00d1\u000f\u00e9\u00e5\u00bf\u00d8f\u00af2\u00b5\u00fbr\u00e1+|\\z\u00b4\u00a6\u00f8\u001f!8\u00af\u0006;\u00f8\u000e\u00ab\r\u00ae\u00e6\u00fc\u00c5f\u00a1\u00af\u00c3\u00e4\u00e7\u00fa\u00c4q\u009a\u001d Gk\u009f\u00d9\u0015\u009ah\u001f]\u00ca\u00c8\u0083\u00fd\u00bbo'\u00ee\u00d8\u00a2\u008e\u00e92\u0084Jr\u00fd\u008e\u0085\u00d7\u00a9B<\f\u0010\u00eaq\"\u00fd\"\u0086\u009d\u00a4L%x\u00ba\u0098M\u00f5}0\u00ca\u00dd\u0018V\u00bc\u00fa1\u00fdw\u0082\u00b7\u00ed&\u00ef\u00d7\u00c2\u00d6\u00e3W\u00a7\u00f5\u0092\u009eBb\u008e\u00a8\n\u00f8\"\u00a4\u0093\u00e7\"\u00f5+\u00b6l\u00a3\u00a8\u0098a.\u00d17\u00afC4\u0010t\u00f8\u00c91\f~\u0084\u0086\u0084i\u00db\u009f\u00d0$\u00ee\u0003\u0018\u0089Z*\u00d9\u00ce/\u00c2\"\u009c\u000f\u00d5\u00fc\u0001\u00a4\u00e1\u00ac\u00d3\u0082\u0004\u00fc\u00ceo\u00a3\u00ae /\u00eb\u00b7\u0018\u0092\u0014\u00a1K\u00eb\u00fb\u00a4zl<\u00a2\u00ec:\u00de\b+L\u0082\u00ba\u0006\u00a3t\u0092\u00c6XY\u00ff\u00e7 \u00aa\u00beo~/H\u00ff\\\u00d5\u00depf\u0001\u00f9P\u00fc\u001a\u00fe\u00d3]h4\u00c6\u001e\u00ab\u001d\u009c\u00df\u0081\u00e1L\u00a6 H\u0089\u0087\u007f\u001d\u00e7A\u00a6\u008c\u00ac\u009a4@p\u00a1\u0019\u001cG\u00a9\u00f5-]\u00eb9\rBf7\u00cc\u0080\u0011G Bi%\u00c7\u00ea\u0087\u00a7O\u008f\u00ee\u00a6\u0082\u00e5\u0012\u00a2*\u0096aK\u009e\u0011\u00a2r,\u00cd]S\u0018X\u0000\u0000!8\u00a18\u00ad\u00bf\u00e2l\u00d1\u007f\u0082Ii2\u008f\u008f\u00ad6\u00cd&&\\\u00fc\u00a0M!{C\u00e6\u00e0\u0011\u000e\u0003az\u00cb:\rsQ\u0014\t\u008b\u00d7\u00fd\u00ac\u0098\u00d9\u00cb\u0080\bh\u008bT\u00cb!\u00ab\u00800\nL\u00a5m\u00cb\u00f8\u00d1cE\u00bc1\u00ad\u0092\"\u00d6\bz\u00a2.\u0014uO\u001e\u00bc\u00f3\u0097\u0081\u00de\u00bdo#\u00f0\u0016\u00bc#6-+\u00edm*\u00d0\u001bw\u00e6\n\u0012\u00d70d\u00f7\u00d4\u0002\u00deR\u00a1\u00c1-\u0085\u00c0\u00a8\u0090\u00f7^\u00a3\u00a4\u007f\u00b5\u00c2\u0012\n\u00f1\u008e\u00d7\u008d\u0013a>\"\u00f2Y\u00821\u009d-\u0089.\u00dc\u00b9\u0092}\u00a7`x>\u00a6g\u0018\u00d3\u00ff\u0011@\u0091b\u00fb\u008b\u0010\u00e6Iw%Xe\u0084\u0015\u0010\u00f6\u00ca\u00a6W\u00b1\u00cd \u00b3\u00fc\u00c2\u009a\u00e8\u00ab\u001a\u0000\u00baLeg\u00f1#Z\u001af \u0001\u00ba\u00ca@\u00d8\u00ec~S<\u00b8&\u0091\rq\u0010[4)\u00bd\u0007\u0012\u00c7\u00f5\u00de,n\u00e4\u0085\u001f\u00f7a \u00b1\u00ac\u0012\u0012\u00cdN\u00fb\u008e3L\u0098J\u00a2\u0003\u007f\u00b0{\u00e6u\u0006\u007fa\u00adq\u00c5\u00ad\u00023\r\u0084\u00ff\r\u0010\u00f2\u00b1c\u00b5O^\u00c7A@He\u00eb'\u00ddv:";
                                var28_6 = 3192;
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
                                    var29_3[var27_4++] = SpeedMine.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "D\u00b3:.B\u0013\u0013\u008dK+\u0090j\u001b\u00ce\u008c#\ne\u00cbpI\u00e5\u00c4\u000f\u00e2\u000b\ts\u0097\u00fb\u00d1\u00af \u00e8\u00a2\u00b0\u00cc\u009c\u00e7\u00dd\u00ec\u00f3\u0090qv\u00a8\u000e`\u0090)\u0082\u00be\u00dc2D\u00cc\u00f0i\u00ddx\u00ca\u00a8\u0005i\u0000";
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
                                    var29_3[var27_4++] = SpeedMine.zkmDecodeUtf8(var30_9).intern();
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
                        SpeedMine.f = var29_3;
                        SpeedMine.j = new String[91];
                        SpeedMine.N = new HashMap<K, V>(13);
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
                        var17_12 = new long[43];
                        var14_13 = 0;
                        var15_14 = "\n\u00c61\u00fa\u0097\u00ea\u0083c\u00c6\u00c8\u00f0\u00d7\u00cb\u00be,\u00feBz\u00fb\u00feY\u0083\u00bb\u00c1a\u00a9\u00d3\u00a8bH\u00be\u00fe\u0011S(\u00cd%\u00bb\u00b4\u0015\u00c1\u00d1\u00b9\u0082\u0092\u00a5s\u0010\u00b6\u00f8\u0096\u00d3I\u0082\\\u00a9P:i\u00e6\u00eb\u0082\u00d1.q%x\u0080\u00fdUWy7z\u008d\u00bf\u00d2\u00e3\u00b6I\u00045\u008c\u00ca(V\u0012\u00d3\u00e1\u00b5X\u00ee\u00bbug\u00ba\u00e0\u00c2\u00c0bc\u00c0\u00e1\u0014\u0001\u0096\u0090j\u00b1\u0004\u00bd\u008f\u0094\u00be\u00f9F\u00b8\u000b)<\u00bfbW\u00a2\u00d3\u00b5\u009d\u00c6?Q\u0003WCU\u00c64'\u00f7\u009b\u00ac[\u0003\u00f4\u0002zY\u0099\u001bk\u00a9\u00e0\u00b4\u00dd\u008e\u00dc\nRS\u00ec(\u0005&\u00d5\u0012!\u009b \u000e\u00c2\u00c5\u008a\u00ab\u009f3\u0012[\u00f2\u00e7\u0097\u00e1\u0017p\u0082Q\u008c\u0011L\u008f\u0095\u00c4TJ\u00ef`\u009a\u0006\u00a7\u00bf\u0093\u00f5'\\\u000fK\u001d\u0005Y\"\u00b7\u001e\u00c1\u0001\u001d\u0099\u000em\u0080B\u0087\u00e8T\u00d4p\u00bd\u008e\u00b7\u00ba\u00d3\u008bI\u00bfK'\u00bc.\u00aa\u000ef\u0005\u00f8\u00b1\u00ef\u00d1\u00ab\u00e7~i\u00dc\u00c7\u00b9\u00a0P\f>\u00c3\u00cd\u00c7\u00b7\u00ea8\u001b\u00b2\u00b1\u0093y\u00c7\u00120\t;c\r\u00eed0\u00fd\u00cf\u0097\u00be\u0016a\u00c0k\u00c8\u00ad\u00efnT\u00caV)jzS\u001d\u00bb\u0098\u00bdZ7s\u00a0\u00a4\u0003\u001b\u00b5\u00a2\u0088\u00fe\u00b3V\u00a3\u00ed\u00b19\u000b\u0091\u00cc|\u0088\u00ed\u0085\f\u0094\u001a\u00f6\tC";
                        var16_15 = 328;
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
                            var15_14 = "=\u00fc9h%0\u008fED\u00a7.\u0019O\u00f7\u00a1+";
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
                SpeedMine.u = var17_12;
                SpeedMine.C = new Integer[43];
                SpeedMine.W = new HashMap<K, V>(13);
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
                var6_22 = new long[2];
                var3_23 = 0;
                var4_24 = "\b9\u00a3\u00c5\u00beN\u00b1\u0085o\u00bf*7+\u0096\u00a6Q";
                var5_25 = 16;
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
        SpeedMine.O = var6_22;
        SpeedMine.P = new Long[2];
        var51_30 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "mode", "getMode()Lsu/catlean/gx;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "timing", "getTiming()Lsu/catlean/jp;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "rebreak", "getRebreak()Lsu/catlean/d0;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "awaitAutoCrystal", "getAwaitAutoCrystal()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "instantAsync", "getInstantAsync()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "doubleMine", "getDoubleMine()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "doubleMinePacketOrder", "getDoubleMinePacketOrder()Lsu/catlean/oh;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "startMode", "getStartMode()Lsu/catlean/bu;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "switchMode", "getSwitchMode()Lsu/catlean/iw;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "swapDelay", "getSwapDelay()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "swapTiming", "getSwapTiming()Lsu/catlean/zy;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "factor", "getFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "speed", "getSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "rotate", "getRotate()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "await", "getAwait()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "phase", "getPhase()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "resetOnSwitch", "getResetOnSwitch()Z", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "pauseEat", "getPauseEat()Lsu/catlean/cy;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "renderGroup", "getRenderGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "renderMode", "getRenderMode()Lsu/catlean/nc;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "selection", "getSelection()Lsu/catlean/xi;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "startColor", "getStartColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(SpeedMine.class, "endColor", "getEndColor()Ljava/awt/Color;", 0))};
        SpeedMine.delegatedProperties = var51_30;
        SpeedMine.INSTANCE = new SpeedMine();
        SpeedMine.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "mode", (Enum)SpeedMineMode.PACKET, null, null, 12, null);
        SpeedMine.timing$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "timing", (Enum)SpeedMineTimingMode.SEQUENTIAL, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.rebreak$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "rebreak", (Enum)SpeedMineRebreakMode.OFF, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.awaitAutoCrystal$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "await-auto-crystal", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.instantAsync$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "instant-async", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.doubleMine$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "double-mine", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.doubleMinePacketOrder$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "packet-order", (Enum)SpeedMineDoubleMinePacketOrderMode.NEW, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.startMode$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "start-mode", (Enum)SpeedMineStartMode.ABORT, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.switchMode = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "switch-mode", (Enum)SpeedMineSwitchMode.Alternative, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.swapDelay$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "1.21", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.swapTiming$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "swap-timing", (Enum)SpeedMineSwapTimingMode.ONCE, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.factor$delegate = SettingFactory.floatSetting$default(SpeedMine.INSTANCE, "factor", 1.0f, RangesKt.rangeTo(0.5f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 8, null);
        SpeedMine.speed$delegate = SettingFactory.floatSetting$default(SpeedMine.INSTANCE, "speed", 0.5f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 8, null);
        SpeedMine.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "rotate", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.await$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "await", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.phase$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "phase", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.resetOnSwitch$delegate = SettingFactory.booleanSetting$default((SettingContainer)SpeedMine.INSTANCE, "reset-on-switch", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.pauseEat$delegate = SettingFactory.modeSetting$default((SettingContainer)SpeedMine.INSTANCE, "on-eating", (Enum)SpeedMinePauseEatMode.NONE, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 4, null);
        SpeedMine.renderGroup$delegate = SettingFactory.settingGroup$default(SpeedMine.INSTANCE, "render", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)(), 2, null);
        var52_31 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)();
        var53_32 = SpeedMine.INSTANCE.getRenderGroup();
        var54_33 = SpeedMineRenderMode.Shrink;
        var55_34 = "render-mode";
        SpeedMine.renderMode$delegate = SettingFactory.modeSetting((SettingContainer)SpeedMine.INSTANCE, var55_34, var54_33, var53_32, var52_31);
        var58_35 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible20(), ()Ljava/lang/Boolean;)();
        var59_36 = SpeedMine.INSTANCE.getRenderGroup();
        var60_37 = SpeedMineSelectionMode.AIR_REBREAK;
        var61_38 = "selection";
        SpeedMine.selection$delegate = SettingFactory.modeSetting((SettingContainer)SpeedMine.INSTANCE, var61_38, var60_37, var59_36, var58_35);
        SpeedMine.startColor$delegate = SettingFactory.colorSetting(SpeedMine.INSTANCE, "start", new Color(255, 0, 0, 200), SpeedMine.INSTANCE.getRenderGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible21(), ()Ljava/lang/Boolean;)());
        SpeedMine.endColor$delegate = SettingFactory.colorSetting(SpeedMine.INSTANCE, "end", new Color(47, 255, 0, 200), SpeedMine.INSTANCE.getRenderGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible22(), ()Ljava/lang/Boolean;)());
        SpeedMine.timer = new Timer();
        SpeedMine.atomicBoolean = new AtomicBoolean(false);
        SpeedMine.miningQueue = new LinkedList<E>();
        SpeedMine.recentBreaks = new ConcurrentHashMap<K, V>();
    }

    public static void zkmSetFlag(String string) {
        g = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return g;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1420;
        if (j[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])k.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ex", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = f[n2].getBytes("ISO-8859-1");
            SpeedMine.j[n2] = SpeedMine.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return j[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SpeedMine.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ex" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1083;
        if (C[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = u[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])N.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    N.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ex", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SpeedMine.C[n2] = n3;
        }
        return C[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SpeedMine.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ex" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long update(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x133F;
        if (P[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = O[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])W.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    W.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ex", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            SpeedMine.P[n2] = l4;
        }
        return P[n2];
    }

    private static long e(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = SpeedMine.update(n, l);
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
            throw new RuntimeException("su/catlean/ex" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SpeedMine.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SpeedMine.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
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
            return MethodHandles.lookup().findStatic(SpeedMine.class, "e", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

