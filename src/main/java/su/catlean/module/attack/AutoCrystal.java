/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.module.attack;

import com.google.common.collect.Lists;
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PostSyncEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.RenderEntityEvent;
import su.catlean.api.event.events.world.AfterEntitySpawn;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientboundEntityEventPacketAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraEspMode;
import su.catlean.module.attack.AutoCrystal$EntriesMappings;
import su.catlean.module.attack.AutoCrystal$WhenMappings;
import su.catlean.module.attack.AutoCrystalAutoSwitchMode;
import su.catlean.module.attack.AutoCrystalDamageSyncMode;
import su.catlean.module.attack.AutoCrystalFriendProtectionMode;
import su.catlean.module.attack.AutoCrystalOnCrystalSpawnMode;
import su.catlean.module.attack.AutoCrystalPredictMode;
import su.catlean.module.attack.AutoCrystalRender;
import su.catlean.module.attack.AutoCrystalRenderMode;
import su.catlean.module.attack.AutoCrystalSpawnTimingMode;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.attack.AutoCrystalTimingMode;
import su.catlean.module.esp.NameTags;
import su.catlean.module.player.AutoMend;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.player.SpeedMineSwitchMode;
import su.catlean.module.protect.Offhand;
import su.catlean.module.rage.Blink;
import su.catlean.render.MainFramebuffer;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.setting.TextSetting;
import su.catlean.sound.Sounds;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketAccess;
import su.catlean.util.PacketUtil;
import su.catlean.util.PendingBlocks;
import su.catlean.util.PlaceManager;
import su.catlean.util.ServerInfo;
import su.catlean.util.SlotResult;
import su.catlean.util.TickTimer;
import su.catlean.util.TimedPosition;
import su.catlean.util.Timer;
import su.catlean.util.ToolPredicate;
import su.catlean.util.combat.AutoCrystalHelper;
import su.catlean.util.combat.CrystalBreaker;
import su.catlean.util.combat.CrystalContext;
import su.catlean.util.combat.CrystalInfo;
import su.catlean.util.combat.CrystalPlacer;
import su.catlean.util.combat.CrystalSpeedCounter;
import su.catlean.util.combat.CrystalTimers;
import su.catlean.util.combat.CrystalTracker;
import su.catlean.util.combat.DamageData;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.combat.PlaceCandidate;
import su.catlean.util.combat.PlaceResult;
import su.catlean.util.combat.TargetUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class AutoCrystal
extends Module {
    @NotNull
    public static final AutoCrystal INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final SettingGroup mainGroup$delegate;
    @NotNull
    private static final ModeSetting timing$delegate;
    @NotNull
    private static final BooleanSetting multiAction$delegate;
    @NotNull
    private static final ModeSetting onCrystalSpawn$delegate;
    @NotNull
    private static final ModeSetting spawnTiming$delegate;
    @NotNull
    private static final BooleanSetting rotate$delegate;
    @NotNull
    private static final BooleanSetting rayTrace$delegate;
    @NotNull
    private static final BooleanSetting crystalMultiPoint$delegate;
    @NotNull
    private static final BooleanSetting asyncCalc$delegate;
    @NotNull
    private static final BooleanSetting failNotify;
    @NotNull
    private static final ModeSetting predict$delegate;
    @NotNull
    private static final IntSetting predictTicks;
    @NotNull
    private static final SettingGroup placeGroup$delegate;
    @NotNull
    private static final BooleanSetting oldVer$delegate;
    @NotNull
    private static final IntSetting placeDelay$delegate;
    @NotNull
    private static final FloatSetting placeRange$delegate;
    @NotNull
    private static final BooleanSetting limitPlace$delegate;
    @NotNull
    private static final BooleanSetting removeFire$delegate;
    @NotNull
    private static final BooleanSetting removeLava$delegate;
    @NotNull
    private static final SettingGroup breakGroup$delegate;
    @NotNull
    private static final IntSetting breakDelay$delegate;
    @NotNull
    private static final BooleanSetting grimBreakRange$delegate;
    @NotNull
    private static final FloatSetting explodeRange$delegate;
    @NotNull
    private static final BooleanSetting limitBreak$delegate;
    @NotNull
    private static final BooleanSetting randomizeDelay$delegate;
    @NotNull
    private static final SettingGroup pauseGroup$delegate;
    @NotNull
    private static final BooleanSetting mining;
    @NotNull
    private static final BooleanSetting eating;
    @NotNull
    private static final BooleanSetting inventory;
    @NotNull
    private static final IntSetting switchPause$delegate;
    @NotNull
    private static final IntSetting offHandPause$delegate;
    @NotNull
    private static final SettingGroup damagesGroup$delegate;
    @NotNull
    private static final BooleanSetting assumeBestArmor$delegate;
    @NotNull
    private static final FloatSetting minDamage$delegate;
    @NotNull
    private static final FloatSetting maxSelfDamage$delegate;
    @NotNull
    private static final FloatSetting efficiencyFactor$delegate;
    @NotNull
    private static final IntSetting lethalMultiplier$delegate;
    @NotNull
    private static final BooleanSetting overrideSelfDamage$delegate;
    @NotNull
    private static final BooleanSetting overrideMinDamage$delegate;
    @NotNull
    private static final FloatSetting armorScale$delegate;
    @NotNull
    private static final FloatSetting facePlaceHp$delegate;
    @NotNull
    private static final BooleanSetting explodeEChestFarm$delegate;
    @NotNull
    private static final BooleanSetting antiPortal$delegate;
    @NotNull
    private static final BooleanSetting antiQuiver$delegate;
    @NotNull
    private static final BooleanSetting terrainIgnore$delegate;
    @NotNull
    private static final ModeSetting friendProtection$delegate;
    @NotNull
    private static final BooleanSetting calcShields$delegate;
    @NotNull
    private static final ModeSetting damageSync$delegate;
    @NotNull
    private static final BooleanSetting facePlaceSync$delegate;
    @NotNull
    private static final BooleanSetting lowCrystals$delegate;
    @NotNull
    private static final FloatSetting stacksLeft$delegate;
    @NotNull
    private static final BooleanSetting minimizeSelfDamage$delegate;
    @NotNull
    private static final FloatSetting maxDamageReduction$delegate;
    @NotNull
    private static final SettingGroup switchGroup$delegate;
    @NotNull
    private static final BooleanSetting gappleSwitch$delegate;
    @NotNull
    private static final ModeSetting switch;
    @NotNull
    private static final ModeSetting antiWeakness;
    @NotNull
    private static final SettingGroup idPredictGroup$delegate;
    @NotNull
    private static final BooleanSetting idPredict$delegate;
    @NotNull
    private static final IntSetting idOffset$delegate;
    @NotNull
    private static final IntSetting idAttacks$delegate;
    @NotNull
    private static final IntSetting idDelay$delegate;
    @NotNull
    private static final BooleanSetting idPredictOnlyDuels$delegate;
    @NotNull
    private static final BooleanSetting swingOnce$delegate;
    @NotNull
    private static final BooleanSetting onlyBreakingSurr$delegate;
    @NotNull
    private static final SettingGroup targeting$delegate;
    @NotNull
    private static final ModeSetting targetSort$delegate;
    @NotNull
    private static final BooleanSetting ignoreCreative$delegate;
    @NotNull
    private static final BooleanSetting ignoreNaked$delegate;
    @NotNull
    private static final SettingGroup renderGroup$delegate;
    @NotNull
    private static final BooleanSetting fakeCrystalRemove$delegate;
    @NotNull
    private static final IntSetting removeDelay$delegate;
    @NotNull
    private static final ModeSetting targetEsp$delegate;
    @NotNull
    private static final ModeSetting renderMode$delegate;
    @NotNull
    private static final TextSetting textPattern$delegate;
    @NotNull
    private static final FloatSetting alpha$delegate;
    @Nullable
    private static PlayerEntity target;
    @NotNull
    private static AtomicReference placeResultRef;
    @NotNull
    private static AtomicReference crystalToBreak;
    @NotNull
    private static AtomicBoolean mineSyncFlag;
    @NotNull
    private static final TickTimer e;
    @NotNull
    private static final TickTimer targetTimer;
    @NotNull
    private static final Timer breakCooldown;
    @NotNull
    private static final TickTimer j;
    @NotNull
    private static final TickTimer switchPauseTimer;
    private static int HF;
    private static int H0;
    private static int H3;
    private static int HS;
    @Nullable
    private static PlaceCandidate placeCandidate;
    @Nullable
    private static Function0 pendingPlace;
    @Nullable
    private static Function0 pendingBreak;
    private static volatile boolean y;
    private static volatile boolean He;
    private static volatile boolean T;
    private static long spawnDelay;
    private static long breakDelay;
    private static volatile int Y;
    @NotNull
    private static final Map minedBlocks;
    @NotNull
    private static final LinkedList placedPositions;
    @NotNull
    private static AtomicReference rotationTarget;
    private static volatile boolean i;
    @NotNull
    private static Timer o;
    @NotNull
    private static final Animation renderAnimation;
    private static String x;
    private static final long ab;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] lb;
    private static final Integer[] mb;
    private static final Map nb;
    private static final long[] ob;
    private static final Long[] pb;
    private static final Map qb;

    private AutoCrystal() {
        super("auto-crystal", Categories.getAttack(), null, null);
    }

    private final SettingParent getMainGroup() {
        return (SettingParent)mainGroup$delegate.getValue(this, delegatedProperties[0]);
    }

    private final AutoCrystalTimingMode getTiming() {
        return (AutoCrystalTimingMode)((Object)timing$delegate.getValue(this, delegatedProperties[1]));
    }

    private final boolean getMultiAction() {
        return (Boolean)multiAction$delegate.getValue(this, delegatedProperties[2]);
    }

    private final AutoCrystalOnCrystalSpawnMode getOnCrystalSpawn() {
        return (AutoCrystalOnCrystalSpawnMode)((Object)onCrystalSpawn$delegate.getValue(this, delegatedProperties[3]));
    }

    private final AutoCrystalSpawnTimingMode getSpawnTiming() {
        return (AutoCrystalSpawnTimingMode)((Object)spawnTiming$delegate.getValue(this, delegatedProperties[4]));
    }

    private final boolean getRotate() {
        return (Boolean)rotate$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getRayTrace() {
        return (Boolean)rayTrace$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getCrystalMultiPoint() {
        return (Boolean)crystalMultiPoint$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getAsyncCalc() {
        return (Boolean)asyncCalc$delegate.getValue(this, delegatedProperties[8]);
    }

    public final boolean NG() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)failNotify.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)28064, (long)(0x179F8FC853D13DF2L ^ l))]);
    }

    private final AutoCrystalPredictMode getPredict() {
        return (AutoCrystalPredictMode)((Object)predict$delegate.getValue(this, delegatedProperties[10]));
    }

    private final int l() {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ ab;
        return ((Number)predictTicks.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)24754, (long)(0x32CB47007AD74989L ^ l))])).intValue();
    }

    private final SettingParent getPlaceGroup() {
        return (SettingParent)placeGroup$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getOldVer() {
        return (Boolean)oldVer$delegate.getValue(this, delegatedProperties[13]);
    }

    private final int getPlaceDelay() {
        return ((Number)placeDelay$delegate.getValue(this, delegatedProperties[14])).intValue();
    }

    private final float getPlaceRange() {
        return ((Number)placeRange$delegate.getValue(this, delegatedProperties[15])).floatValue();
    }

    private final boolean getLimitPlace() {
        return (Boolean)limitPlace$delegate.getValue(this, delegatedProperties[16]);
    }

    private final boolean getRemoveFire() {
        return (Boolean)removeFire$delegate.getValue(this, delegatedProperties[17]);
    }

    private final boolean getRemoveLava() {
        return (Boolean)removeLava$delegate.getValue(this, delegatedProperties[18]);
    }

    private final SettingParent getBreakGroup() {
        return (SettingParent)breakGroup$delegate.getValue(this, delegatedProperties[19]);
    }

    private final int getBreakDelay() {
        return ((Number)breakDelay$delegate.getValue(this, delegatedProperties[20])).intValue();
    }

    private final boolean getGrimBreakRange() {
        return (Boolean)grimBreakRange$delegate.getValue(this, delegatedProperties[21]);
    }

    private final float getExplodeRange() {
        return ((Number)explodeRange$delegate.getValue(this, delegatedProperties[22])).floatValue();
    }

    private final boolean getLimitBreak() {
        return (Boolean)limitBreak$delegate.getValue(this, delegatedProperties[23]);
    }

    public final boolean getRandomizeDelay() {
        return (Boolean)randomizeDelay$delegate.getValue(this, delegatedProperties[24]);
    }

    private final SettingParent getPauseGroup() {
        return (SettingParent)pauseGroup$delegate.getValue(this, delegatedProperties[25]);
    }

    private final boolean NP() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ ab;
        return (Boolean)mining.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)32430, (long)(0x68BAE56E9E5220E2L ^ l))]);
    }

    private final boolean NW() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ ab;
        return (Boolean)eating.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)28700, (long)(0x1BB6029D46540E13L ^ l))]);
    }

    private final boolean Ne() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (Boolean)inventory.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)20517, (long)(0x539B5EF3310380C2L ^ l))]);
    }

    private final int getSwitchPause() {
        return ((Number)switchPause$delegate.getValue(this, delegatedProperties[29])).intValue();
    }

    private final int getOffHandPause() {
        return ((Number)offHandPause$delegate.getValue(this, delegatedProperties[30])).intValue();
    }

    private final SettingParent getDamagesGroup() {
        return (SettingParent)damagesGroup$delegate.getValue(this, delegatedProperties[31]);
    }

    public final boolean getAssumeBestArmor() {
        return (Boolean)assumeBestArmor$delegate.getValue(this, delegatedProperties[32]);
    }

    private final float getMinDamage() {
        return ((Number)minDamage$delegate.getValue(this, delegatedProperties[33])).floatValue();
    }

    private final float getMaxSelfDamage() {
        return ((Number)maxSelfDamage$delegate.getValue(this, delegatedProperties[34])).floatValue();
    }

    private final float getEfficiencyFactor() {
        return ((Number)efficiencyFactor$delegate.getValue(this, delegatedProperties[35])).floatValue();
    }

    private final int getLethalMultiplier() {
        return ((Number)lethalMultiplier$delegate.getValue(this, delegatedProperties[36])).intValue();
    }

    private final boolean getOverrideSelfDamage() {
        return (Boolean)overrideSelfDamage$delegate.getValue(this, delegatedProperties[37]);
    }

    private final boolean getOverrideMinDamage() {
        return (Boolean)overrideMinDamage$delegate.getValue(this, delegatedProperties[38]);
    }

    private final float getArmorScale() {
        return ((Number)armorScale$delegate.getValue(this, delegatedProperties[39])).floatValue();
    }

    private final float getFacePlaceHp() {
        return ((Number)facePlaceHp$delegate.getValue(this, delegatedProperties[40])).floatValue();
    }

    private final boolean getExplodeEChestFarm() {
        return (Boolean)explodeEChestFarm$delegate.getValue(this, delegatedProperties[41]);
    }

    private final boolean getAntiPortal() {
        return (Boolean)antiPortal$delegate.getValue(this, delegatedProperties[42]);
    }

    private final boolean getAntiQuiver() {
        return (Boolean)antiQuiver$delegate.getValue(this, delegatedProperties[43]);
    }

    public final boolean getTerrainIgnore() {
        return (Boolean)terrainIgnore$delegate.getValue(this, delegatedProperties[44]);
    }

    private final AutoCrystalFriendProtectionMode getFriendProtection() {
        return (AutoCrystalFriendProtectionMode)((Object)friendProtection$delegate.getValue(this, delegatedProperties[45]));
    }

    private final boolean getCalcShields() {
        return (Boolean)calcShields$delegate.getValue(this, delegatedProperties[46]);
    }

    private final AutoCrystalDamageSyncMode getDamageSync() {
        return (AutoCrystalDamageSyncMode)((Object)damageSync$delegate.getValue(this, delegatedProperties[47]));
    }

    private final boolean getFacePlaceSync() {
        return (Boolean)facePlaceSync$delegate.getValue(this, delegatedProperties[48]);
    }

    private final boolean getLowCrystals() {
        return (Boolean)lowCrystals$delegate.getValue(this, delegatedProperties[49]);
    }

    private final float getStacksLeft() {
        return ((Number)stacksLeft$delegate.getValue(this, delegatedProperties[50])).floatValue();
    }

    private final boolean getMinimizeSelfDamage() {
        return (Boolean)minimizeSelfDamage$delegate.getValue(this, delegatedProperties[51]);
    }

    private final float getMaxDamageReduction() {
        return ((Number)maxDamageReduction$delegate.getValue(this, delegatedProperties[52])).floatValue();
    }

    private final SettingParent getSwitchGroup() {
        return (SettingParent)switchGroup$delegate.getValue(this, delegatedProperties[53]);
    }

    private final boolean getGappleSwitch() {
        return (Boolean)gappleSwitch$delegate.getValue(this, delegatedProperties[54]);
    }

    private final AutoCrystalAutoSwitchMode Nn() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (AutoCrystalAutoSwitchMode)((Object)switch.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)5142, (long)(0x732D2306AC031827L ^ l))]));
    }

    private final AutoCrystalAutoSwitchMode NQ() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (AutoCrystalAutoSwitchMode)((Object)antiWeakness.getValue(this, delegatedProperties[AutoCrystal.zkmBootstrap2("c", (int)21118, (long)(0xF763618F876C4CBL ^ l))]));
    }

    private final SettingParent getIdPredictGroup() {
        return (SettingParent)idPredictGroup$delegate.getValue(this, delegatedProperties[57]);
    }

    private final boolean getIdPredict() {
        return (Boolean)idPredict$delegate.getValue(this, delegatedProperties[58]);
    }

    private final int getIdOffset() {
        return ((Number)idOffset$delegate.getValue(this, delegatedProperties[59])).intValue();
    }

    private final int getIdAttacks() {
        return ((Number)idAttacks$delegate.getValue(this, delegatedProperties[60])).intValue();
    }

    private final int getIdDelay() {
        return ((Number)idDelay$delegate.getValue(this, delegatedProperties[61])).intValue();
    }

    private final boolean getIdPredictOnlyDuels() {
        return (Boolean)idPredictOnlyDuels$delegate.getValue(this, delegatedProperties[62]);
    }

    private final boolean getSwingOnce() {
        return (Boolean)swingOnce$delegate.getValue(this, delegatedProperties[63]);
    }

    private final boolean getOnlyBreakingSurr() {
        return (Boolean)onlyBreakingSurr$delegate.getValue(this, delegatedProperties[64]);
    }

    private final SettingParent getTargeting() {
        return (SettingParent)targeting$delegate.getValue(this, delegatedProperties[65]);
    }

    private final AutoCrystalTargetSortMode getTargetSort() {
        return (AutoCrystalTargetSortMode)((Object)targetSort$delegate.getValue(this, delegatedProperties[66]));
    }

    private final boolean getIgnoreCreative() {
        return (Boolean)ignoreCreative$delegate.getValue(this, delegatedProperties[67]);
    }

    private final boolean getIgnoreNaked() {
        return (Boolean)ignoreNaked$delegate.getValue(this, delegatedProperties[68]);
    }

    private final SettingParent getRenderGroup() {
        return (SettingParent)renderGroup$delegate.getValue(this, delegatedProperties[69]);
    }

    private final boolean getFakeCrystalRemove() {
        return (Boolean)fakeCrystalRemove$delegate.getValue(this, delegatedProperties[70]);
    }

    private final int getRemoveDelay() {
        return ((Number)removeDelay$delegate.getValue(this, delegatedProperties[71])).intValue();
    }

    private final AuraEspMode getTargetEsp() {
        return (AuraEspMode)((Object)targetEsp$delegate.getValue(this, delegatedProperties[72]));
    }

    private final AutoCrystalRenderMode getRenderMode() {
        return (AutoCrystalRenderMode)((Object)renderMode$delegate.getValue(this, delegatedProperties[73]));
    }

    private final String getTextPattern() {
        return (String)textPattern$delegate.getValue(this, delegatedProperties[74]);
    }

    private final float getAlpha() {
        return ((Number)alpha$delegate.getValue(this, delegatedProperties[75])).floatValue();
    }

    @Nullable
    public final PlayerEntity getTarget() {
        return target;
    }

    public final void setTarget(@Nullable PlayerEntity playerEntity) {
        target = playerEntity;
    }

    @NotNull
    public final AtomicReference getPlaceResultRef() {
        return placeResultRef;
    }

    public final void setPlaceResultRef(@NotNull AtomicReference atomicReference) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter(atomicReference, (String)((Object)AutoCrystal.zkmBootstrap("x", (int)25887, (long)(0x67E8B1E2EF6ACDBBL ^ l))));
        placeResultRef = atomicReference;
    }

    @NotNull
    public final AtomicBoolean getMineSyncFlag() {
        return mineSyncFlag;
    }

    public final void setMineSyncFlag(@NotNull AtomicBoolean atomicBoolean) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter(atomicBoolean, (String)((Object)AutoCrystal.zkmBootstrap("x", (int)25887, (long)(0x67E8F997BBFDDFCBL ^ l))));
        mineSyncFlag = atomicBoolean;
    }

    public final int Y() {
        return HS;
    }

    public final void z(int n) {
        HS = n;
    }

    public final long getSpawnDelay() {
        return spawnDelay;
    }

    public final void setSpawnDelay(long l) {
        spawnDelay = l;
    }

    public final long getBreakDelay2() {
        return breakDelay;
    }

    public final void setBreakDelay(long l) {
        breakDelay = l;
    }

    @NotNull
    public final Map getMinedBlocks() {
        return minedBlocks;
    }

    @NotNull
    public final LinkedList getPlacedPositions() {
        return placedPositions;
    }

    @NotNull
    public final AtomicReference getRotationTarget() {
        return rotationTarget;
    }

    public final void setRotationTarget(@NotNull AtomicReference atomicReference) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter(atomicReference, (String)((Object)AutoCrystal.zkmBootstrap("x", (int)14266, (long)(0x58C0F7E620A51284L ^ l))));
        rotationTarget = atomicReference;
    }

    public final boolean x() {
        return i;
    }

    public final void p(boolean bl) {
        i = bl;
    }

    @NotNull
    public final Timer n() {
        return o;
    }

    public final void o(@NotNull Timer timer) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter(timer, (String)((Object)AutoCrystal.zkmBootstrap("x", (int)25887, (long)(0x67E8C51D8F853096L ^ l))));
        o = timer;
    }

    @Override
    public void onEnable(long l) {
        this.reset();
    }

    @Override
    public void onDisable(long l) {
        this.reset();
    }

    private final void reset() {
        CrystalTracker.INSTANCE.clear();
        placedPositions.clear();
        targetTimer.reset();
        e.reset();
        j.setTicks(20);
        y = false;
        crystalToBreak.set(null);
        placeResultRef.set(null);
        placeCandidate = null;
        target = null;
        spawnDelay = 0L;
        Y = 0;
        HS = 0;
        pendingPlace = null;
        pendingBreak = null;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Vec3d vec3d;
        long l;
        long l2 = l = ab ^ 0x1A0CDA8F3ADCL;
        long l3 = l2 ^ 0x542F93576918L;
        long l4 = l2 ^ 0x7E718F0C5E25L;
        long l5 = l2 ^ 0x6B4262D43600L;
        long l6 = l5 >>> 16;
        int n = (int)(l5 << 48 >>> 48);
        long l7 = l2 ^ 0x23D9074CF6FAL;
        if (this.getRenderMode() == AutoCrystalRenderMode.TECHNO) {
            AutoCrystalRender.INSTANCE.removeFinished();
        }
        float f = Math.max(this.getPlaceRange(), this.getExplodeRange()) + 12.0f;
        Object object = new HashMap(minedBlocks);
        Object object2 = object.entrySet().iterator();
        while (object2.hasNext()) {
            Map.Entry entry;
            Map.Entry entry2 = entry = object2.next();
            BlockPos blockPos = (BlockPos)entry2.getKey();
            Long l8 = (Long)entry2.getValue();
            long l9 = Globals.now();
            Intrinsics.checkNotNull(l8);
            if (!((float)(l9 - l8) > (float)Math.max(100, ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l6, 1, (char)n, null)) * 2.0f)) continue;
            minedBlocks.remove(blockPos);
        }
        if (target != null) {
            PlayerEntity playerEntity = target;
            Intrinsics.checkNotNull(playerEntity);
            if (playerEntity.isDead()) {
                Sounds.INSTANCE.playToggle(true);
                target = null;
                return;
            }
        }
        if ((target = TargetUtil.INSTANCE.findTarget(l4, f, this.getTargetSort(), this.getIgnoreNaked(), this.getIgnoreCreative())) == null) {
            placeResultRef.set(null);
            CrystalTimers.INSTANCE.reset();
            return;
        }
        object = this.recalculate();
        if (this.getAsyncCalc()) {
            new Thread(() -> AutoCrystal.onPlayerUpdate$lambda$0((CrystalContext)object)).start();
        } else {
            this.calculate((CrystalContext)object, l7);
        }
        i = false;
        if (!Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.END_CRYSTAL) && this.getGappleSwitch() && Globals.getMc().options.useKey.isPressed() && Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.END_CRYSTAL)) {
            Item[] itemArray = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.ENCHANTED_GOLDEN_APPLE, "ENCHANTED_GOLDEN_APPLE");
            InventoryUtil.INSTANCE.findInHotbar(itemArray).select();
        }
        int n2 = H3;
        H3 = n2 + 1;
        if (n2 >= 20) {
            Item item = Items.END_CRYSTAL;
            Intrinsics.checkNotNullExpressionValue(item, "END_CRYSTAL");
            Object var55_18 = null;
            boolean bl = false;
            Item item2 = item;
            H0 = MathHelper.clamp(HF - InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, item2, bl, var55_18), 0, 255);
            Item item3 = Items.END_CRYSTAL;
            Intrinsics.checkNotNullExpressionValue(item3, "END_CRYSTAL");
            var55_18 = null;
            bl = false;
            item2 = item3;
            HF = InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, item2, bl, var55_18);
            H3 = 0;
        }
        this.doActions(l3);
        if (this.getRotate() && (vec3d = (Vec3d)rotationTarget.get()) != null) {
            object2 = Rotation.Companion.to(vec3d);
            RotationManager.INSTANCE.rotate((Rotation)object2, 140);
            if (o.hasPassed(500)) {
                rotationTarget.lazySet(null);
                placeCandidate = null;
            }
        }
    }

    @Flow
    private final void onPostSync(PostSyncEvent postSyncEvent) {
        Function0 function0 = pendingBreak;
        if (function0 != null) {
            function0.invoke();
        }
        pendingBreak = null;
        Function0 function02 = pendingPlace;
        if (function02 != null) {
            function02.invoke();
        }
        pendingPlace = null;
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block5: {
            long l;
            long l2 = l = ab ^ 0x7CD2C8DF111FL;
            long l3 = l2 ^ 0x41A188DC33D3L;
            long l4 = l3 >>> 16;
            int n = (int)(l3 << 48 >>> 48);
            long l5 = l2 ^ 0x29053EB63BAEL;
            int n2 = (int)(l5 >>> 32);
            int n3 = (int)(l5 << 32 >>> 48);
            int n4 = (int)(l5 << 48 >>> 48);
            long l6 = l2 ^ 0x2355597DAE35L;
            CrystalTracker.INSTANCE.removeExpired(n2, (char)n3, (char)n4);
            Object var29_9 = null;
            if (target != null) {
                switch (AutoCrystal$WhenMappings.m[this.getTargetEsp().ordinal()]) {
                    case 1: {
                        MatrixStack matrixStack = render3DEvent.getStack();
                        PlayerEntity playerEntity = target;
                        Intrinsics.checkNotNull(playerEntity);
                        Entity entity = playerEntity;
                        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                        PlayerEntity playerEntity2 = target;
                        Intrinsics.checkNotNull(playerEntity2);
                        float f = 1.0f - RangesKt.coerceIn(clientPlayerEntity.distanceTo(playerEntity2) / 12.0f, 0.0f, 1.0f);
                        Entity entity2 = entity;
                        MainFramebuffer.INSTANCE.renderEntityGlow(matrixStack, l4, entity2, (char)n, f);
                        break;
                    }
                    case 2: {
                        MatrixStack matrixStack = render3DEvent.getStack();
                        PlayerEntity playerEntity = target;
                        Intrinsics.checkNotNull(playerEntity);
                        MainFramebuffer.INSTANCE.renderEntityOutline(matrixStack, playerEntity);
                        break;
                    }
                }
            }
            PlaceCandidate placeCandidate = AutoCrystal.placeCandidate;
            if (this.getRenderMode() == AutoCrystalRenderMode.OFF || placeCandidate == null) break block5;
            renderAnimation.animate(0.0f);
            this.renderPlaceBox(render3DEvent.getStack(), StringsKt.replace$default(StringsKt.replace$default(this.getTextPattern(), "damage", String.valueOf(NameTags.INSTANCE.formatNumber(placeCandidate.getDamage())), false, 4, null), "self", String.valueOf(NameTags.INSTANCE.formatNumber(placeCandidate.getSelfDamage())), false, 4, null), l6, new Box(placeCandidate.getPos()));
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        block1: {
            long l = ab ^ 0x2D5FC01CD2E1L;
            long l2 = l ^ 0x67C266D0A5B0L;
            long l3 = l2 >>> 32;
            int n = (int)(l2 << 32 >>> 32);
            if (target == null) {
                return;
            }
            if (AutoCrystal$WhenMappings.m[this.getTargetEsp().ordinal()] != 2) break block1;
            MainFramebuffer.INSTANCE.beginCapture(l3, n);
        }
    }

    @Flow
    private final void onAfterEntitySpawn(AfterEntitySpawn afterEntitySpawn) {
        long l = ab ^ 0x38EA83987B92L;
        long l2 = l ^ 0x478A574C1B57L;
        AfterEntitySpawn afterEntitySpawn2 = afterEntitySpawn;
        Object var12_5 = null;
        if (afterEntitySpawn2.getEntity() instanceof EndCrystalEntity && INSTANCE.getSpawnTiming() == AutoCrystalSpawnTimingMode.ADD_TO_WORLD) {
            Entity entity = afterEntitySpawn2.getEntity();
            INSTANCE.trackCrystalSpawn(l2, afterEntitySpawn2.getEntity().getId(), INSTANCE.getPos(entity));
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        long l = ab ^ 0x4D30135D84EBL;
        long l2 = l ^ 0x3250C789E42EL;
        try {
            Packet<?> packet = receivePacket.getPacket();
            if (packet instanceof EntitySpawnS2CPacket) {
                AutoCrystalHelper.INSTANCE.updateMax(((EntitySpawnS2CPacket)packet).getEntityId());
                if (Intrinsics.areEqual(((EntitySpawnS2CPacket)packet).getEntityType(), EntityType.END_CRYSTAL)) {
                    if (target != null && INSTANCE.getSpawnTiming() == AutoCrystalSpawnTimingMode.PACKET) {
                        if (INSTANCE.getAsyncCalc()) {
                            new Thread(() -> AutoCrystal.onReceivePacket$lambda$0(packet)).start();
                        } else {
                            INSTANCE.trackCrystalSpawn(l2, ((EntitySpawnS2CPacket)packet).getEntityId(), new Vec3d(((EntitySpawnS2CPacket)packet).getX(), ((EntitySpawnS2CPacket)packet).getY(), ((EntitySpawnS2CPacket)packet).getZ()));
                        }
                    }
                }
            }
            if (packet instanceof ExplosionS2CPacket) {
                Iterator iterator2 = Lists.newArrayList(Globals.getWorld().getEntities()).iterator();
                Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
                Iterator iterator3 = iterator2;
                while (iterator3.hasNext()) {
                    Object e = iterator3.next();
                    Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                    Entity entity = (Entity)e;
                    if (!(entity instanceof EndCrystalEntity) || !(entity.squaredDistanceTo(((ExplosionS2CPacket)packet).comp_2883().x, ((ExplosionS2CPacket)packet).comp_2883().y, ((ExplosionS2CPacket)packet).comp_2883().z) <= 144.0) || CrystalTracker.INSTANCE.hasSpawned(((EndCrystalEntity)entity).getId())) continue;
                    CrystalTracker.INSTANCE.recordSpawn(((EndCrystalEntity)entity).getId(), Globals.now());
                }
            }
            if (packet instanceof EntityStatusS2CPacket && ((EntityStatusS2CPacket)packet).getStatus() == 3) {
                if (target != null) {
                    int n = ((ClientboundEntityEventPacketAccessor)((Object)packet)).getEntityId();
                    PlayerEntity playerEntity = target;
                    Intrinsics.checkNotNull(playerEntity);
                    if (n == playerEntity.getId()) {
                        breakCooldown.reset();
                    }
                }
            }
            if (packet instanceof PlaySoundS2CPacket && (Intrinsics.areEqual(((PlaySoundS2CPacket)packet).getSound().comp_349(), SoundEvents.BLOCK_ANVIL_PLACE) || Intrinsics.areEqual(((PlaySoundS2CPacket)packet).getSound().comp_349(), SoundEvents.BLOCK_ANVIL_LAND) || Intrinsics.areEqual(((PlaySoundS2CPacket)packet).getSound().comp_349(), SoundEvents.BLOCK_ANVIL_FALL))) {
                CrystalTimers.INSTANCE.resetPlace();
            }
        }
        catch (Exception exception) {
            ModuleMessages.S(this, "AUTO CRYSTAL PACKET HANDLE ERROR", false, null);
            exception.printStackTrace();
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (sendPacket.getPacket() instanceof UpdateSelectedSlotC2SPacket) {
            if (Y > 0) {
                int n = Y;
                Y = n + -1;
                return;
            }
            switchPauseTimer.reset();
        }
    }

    private final void trackCrystalSpawn(long l, int n, Vec3d vec3d) {
        block5: {
            int n2;
            int n3;
            long l2 = l = ab ^ l;
            long l3 = l2 ^ 0x4D21DF1DA98BL;
            int n4 = (int)(l3 >>> 56);
            long l4 = l3 << 8 >>> 8;
            int n5 = (int)((l2 ^ 0x2AB9A4DFE79L) >>> 48);
            long l5 = l2 ^ 0x18CD44951ABL;
            long l6 = l2 ^ 0xEE14BA3911L;
            TimedPosition timedPosition = CrystalTracker.INSTANCE.findAt(vec3d);
            if (timedPosition == null) {
                return;
            }
            TimedPosition timedPosition2 = timedPosition;
            spawnDelay = Globals.now() - timedPosition2.getTime();
            CrystalSpeedCounter.INSTANCE.recordPlace();
            PlayerEntity playerEntity = target;
            if (playerEntity == null || !targetTimer.hasPassed((short)n5, n3 = INSTANCE.getBreakDelay()) || INSTANCE.getOnCrystalSpawn() == AutoCrystalOnCrystalSpawnMode.NO_REACTION || INSTANCE.isPlaceValid() || !INSTANCE.getMultiAction() || INSTANCE.getRandomizeDelay()) break block5;
            CrystalContext crystalContext = INSTANCE.calculateFor(playerEntity);
            if (INSTANCE.getRotate()) {
                INSTANCE.setPlacePos(vec3d);
            }
            if (CrystalBreaker.INSTANCE.u(INSTANCE, vec3d, crystalContext, l5, false, 0) != null) {
                INSTANCE.shouldBreak(n, vec3d, true, l6);
            }
            if (INSTANCE.getOnCrystalSpawn() == AutoCrystalOnCrystalSpawnMode.ACTION_X && e.hasPassed((short)n5, n2 = INSTANCE.getPlaceDelay())) {
                PlaceResult placeResult = (PlaceResult)placeResultRef.get();
                if (placeResult != null) {
                    PlaceResult placeResult2 = placeResult;
                    INSTANCE.placeCrystal((byte)n4, (BlockHitResult)placeResult2.getData(), true, l4);
                }
            }
        }
    }

    @Flow
    private final void onRenderEntity(RenderEntityEvent renderEntityEvent) {
        block1: {
            if (!this.getFakeCrystalRemove() || target == null || renderEntityEvent.getState() == null) break block1;
            int n = this.getRemoveDelay();
            long l = Globals.now();
            Entity entity = renderEntityEvent.getState();
            Intrinsics.checkNotNull(entity);
            long l2 = l - CrystalTracker.INSTANCE.getSpawnTime(entity.getId());
            boolean bl = (long)n <= l2 ? l2 < 201L : false;
            if (bl) {
                renderEntityEvent.cancel();
            }
        }
    }

    private final void doActions(long l) {
        block5: {
            Object object;
            CrystalInfo crystalInfo;
            PlaceResult placeResult;
            long l2;
            int n;
            long l3;
            int n2;
            block3: {
                int n3;
                block4: {
                    int n4;
                    long l4 = l = ab ^ l;
                    long l5 = l4 ^ 0x7C6242119A8AL;
                    n2 = (int)(l5 >>> 56);
                    l3 = l5 << 8 >>> 8;
                    n = (int)((l4 ^ 0x33E80741CD78L) >>> 48);
                    l2 = l4 ^ 0x31AD89B60A10L;
                    placeResult = (PlaceResult)placeResultRef.get();
                    Object var22_7 = null;
                    crystalInfo = (CrystalInfo)crystalToBreak.get();
                    if (!this.isPlaceValid() && this.getMultiAction()) break block3;
                    if (crystalInfo == null || !targetTimer.hasPassed((short)n, n4 = this.getBreakDelay())) break block4;
                    Entity entity = crystalInfo.getCrystal();
                    this.shouldBreak(crystalInfo.getCrystal().getId(), this.getPos(entity), false, l2);
                    break block5;
                }
                if (placeResult == null || !e.hasPassed((short)n, n3 = this.getPlaceDelay())) break block5;
                this.placeCrystal((byte)n2, (BlockHitResult)placeResult.getData(), false, l3);
                break block5;
            }
            CrystalInfo crystalInfo2 = crystalInfo;
            if (crystalInfo2 != null) {
                object = crystalInfo2;
                int n5 = INSTANCE.getBreakDelay();
                if (targetTimer.hasPassed((short)n, n5)) {
                    Entity entity = ((CrystalInfo)object).getCrystal();
                    INSTANCE.shouldBreak(((CrystalInfo)object).getCrystal().getId(), INSTANCE.getPos(entity), false, l2);
                }
            }
            PlaceResult placeResult2 = placeResult;
            if (placeResult2 == null) break block5;
            object = placeResult2;
            int n6 = INSTANCE.getPlaceDelay();
            if (e.hasPassed((short)n, n6)) {
                INSTANCE.placeCrystal((byte)n2, (BlockHitResult)((PlaceResult)object).getData(), false, l3);
            }
        }
    }

    private final void calculate(CrystalContext crystalContext, long l) {
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x14E37CBE11D5L;
        long l4 = l2 ^ 0x28ED460C0B9FL;
        placeResultRef.set(CrystalPlacer.calcPosition(this, l4, crystalContext));
        crystalToBreak.set(CrystalBreaker.INSTANCE.V(this, crystalContext, l3));
    }

    private final boolean isPlaceValid() {
        PlaceResult placeResult = (PlaceResult)placeResultRef.get();
        Object var11_2 = null;
        if (!this.getRayTrace() || placeResult == null) {
            return false;
        }
        Entity entity = Globals.getPlayer();
        Vec3d vec3d = PlaceManager.INSTANCE.getEyePos(entity);
        Vec3d vec3d2 = ((BlockHitResult)placeResult.getData()).getPos();
        Intrinsics.checkNotNullExpressionValue(vec3d2, "getLocation(...)");
        Vec3d vec3d3 = vec3d2;
        Vec3d vec3d4 = ((BlockHitResult)placeResult.getData()).getBlockPos().toCenterPos().add(0.0, 0.5, 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d4, "add(...)");
        Vec3d vec3d5 = vec3d4;
        Box box = new Box(vec3d5.x - 1.0, vec3d5.y, vec3d5.z - 1.0, vec3d5.x + 1.0, vec3d5.y + 2.0, vec3d5.z + 1.0);
        if (box.raycast(vec3d, vec3d3).isEmpty() && !box.contains(vec3d) && !box.contains(vec3d3)) {
            return true;
        }
        BlockHitResult blockHitResult = DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, vec3d, vec3d3, false, null, false, 28, null);
        return blockHitResult != null && blockHitResult.getType() != HitResult.Type.MISS && (!Intrinsics.areEqual(blockHitResult.getBlockPos(), ((BlockHitResult)placeResult.getData()).getBlockPos()) || blockHitResult.getSide() != ((BlockHitResult)placeResult.getData()).getSide());
    }

    private final void renderPlaceBox(MatrixStack matrixStack, String string, long l, Box box) {
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x52B4BDF0173L;
        long l4 = l2 ^ 0x2D0527B166D7L;
        int n = (int)(l4 >>> 48);
        int n2 = (int)(l4 << 16 >>> 48);
        int n3 = (int)(l4 << 32 >>> 32);
        Color color = Theme.INSTANCE.getPrimaryColor();
        Object var24_10 = null;
        if (this.getRenderMode() == AutoCrystalRenderMode.SHADER) {
            float f = this.getAlpha() * renderAnimation.getValue();
            RenderState.addBox$default(RenderState.INSTANCE, box, RenderUtil.INSTANCE.withAlpha(color, f), null, null);
        } else if (this.getRenderMode() == AutoCrystalRenderMode.DEAFULT) {
            float f = this.getAlpha() * renderAnimation.getValue();
            Object var34_13 = null;
            Color color2 = null;
            Color color3 = RenderUtil.INSTANCE.withAlpha(color, f);
            Box box2 = box;
            RenderState renderState = RenderState.INSTANCE;
            RenderState.addFilledBox$default(renderState, box2, color3, color2, var34_13);
            float f2 = renderAnimation.getValue();
            Object var46_19 = null;
            Color color4 = null;
            Color color5 = RenderUtil.INSTANCE.withAlpha(color, f2);
            Box box3 = box;
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box3, color5, color4, var46_19);
        } else {
            AutoCrystalRender.INSTANCE.renderTexture(matrixStack, (char)n, box, (char)n2, n3);
        }
        Vec3d vec3d = box.getCenter();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
        float f = renderAnimation.getValue();
        RenderState.drawWorldText$default(RenderState.INSTANCE, l3, string, vec3d, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f), 0.0f, 0.0f, false, null, 120, null);
    }

    private final boolean isHoldingCrystal(long l, boolean bl) {
        int n;
        boolean bl2;
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x7CE81BC7E3FL;
        long l4 = l2 ^ 0x7F1FDD5228B9L;
        int n2 = (int)(l4 >>> 48);
        long l5 = l4 << 16 >>> 16;
        int n3 = (int)((l2 ^ 0x669F01ED8F00L) >>> 48);
        boolean bl3 = Globals.getPlayer().getOffHandStack().getItem() instanceof EndCrystalItem;
        boolean bl4 = Globals.getPlayer().getMainHandStack().getItem() instanceof EndCrystalItem;
        Object var44_9 = null;
        boolean bl5 = bl2 = Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.ENCHANTED_GOLDEN_APPLE) || Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.GOLDEN_APPLE);
        if (!breakCooldown.hasPassed(500)) {
            return true;
        }
        if (AutoMend.INSTANCE.x()) {
            return true;
        }
        if (Blink.INSTANCE.getEnabled()) {
            return true;
        }
        if (SpeedMine.INSTANCE.getAtomicBoolean().get()) {
            return true;
        }
        if (Globals.getInteractionManager().isBreakingBlock() && !bl3 && this.NP()) {
            return true;
        }
        if (!bl && this.getSwitchPause() > 0 && !switchPauseTimer.hasPassed((short)n3, n = this.getSwitchPause())) {
            return true;
        }
        if (this.getOffHandPause() > 0 && 5 - Offhand.INSTANCE.M() < this.getOffHandPause()) {
            return true;
        }
        if (bl) {
            Item[] itemArray;
            if (this.Nn() == AutoCrystalAutoSwitchMode.NONE && !bl3 && !bl4) {
                return true;
            }
            if (this.Nn() == AutoCrystalAutoSwitchMode.SILENT || this.Nn() == AutoCrystalAutoSwitchMode.NORMAL) {
                itemArray = new Item[1];
                Intrinsics.checkNotNullExpressionValue(Items.END_CRYSTAL, "END_CRYSTAL");
                if (!InventoryUtil.INSTANCE.findInHotbar(itemArray).isFound() && !bl3) {
                    return true;
                }
            }
            if (this.Nn() == AutoCrystalAutoSwitchMode.INVENTORY) {
                itemArray = new Item[1];
                Intrinsics.checkNotNullExpressionValue(Items.END_CRYSTAL, "END_CRYSTAL");
                if (!InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n2, l5).isFound() && !bl3) {
                    return true;
                }
            }
            if (SpeedMine.getMinePositions$default(SpeedMine.INSTANCE, false, 1, null, l3).isEmpty()) {
                mineSyncFlag.set(false);
            }
            if (mineSyncFlag.get()) {
                return true;
            }
        }
        if (Globals.getPlayer().isUsingItem() && this.NW()) {
            return true;
        }
        if (this.Ne() && MovementUtil.INSTANCE.I()) {
            return true;
        }
        return !bl3 && this.getGappleSwitch() && Globals.getMc().options.useKey.isPressed() && bl2 && this.Nn() != AutoCrystalAutoSwitchMode.SILENT && this.Nn() != AutoCrystalAutoSwitchMode.INVENTORY;
    }

    private final void shouldBreak(int n, Vec3d vec3d, boolean bl, long l) {
        long l2 = (l = ab ^ l) ^ 0x64DA8F1A4868L;
        if (CrystalTracker.INSTANCE.hasSpawned(n) && this.getLimitBreak() || this.isHoldingCrystal(l2, false) || target == null) {
            return;
        }
        if (this.getRotate() && !bl) {
            Vec3d vec3d2;
            if (this.getTiming() == AutoCrystalTimingMode.VANILLA) {
                T = !T;
            }
            Box box = new Box(vec3d.subtract(1.0, 0.0, 1.0), vec3d.add(1.0, 2.0, 1.0));
            if (this.getCrystalMultiPoint() && !box.contains(Globals.getPlayer().getEyePos())) {
                vec3d2 = new Vec3d(RangesKt.coerceIn(Globals.getPlayer().getEyePos().x, vec3d.x - 1.0, vec3d.x + 1.0), RangesKt.coerceIn(Globals.getPlayer().getEyePos().y, vec3d.y, vec3d.y + 2.0), RangesKt.coerceIn(Globals.getPlayer().getEyePos().z, vec3d.z - 1.0, vec3d.z + 1.0));
            } else {
                Vec3d vec3d3 = vec3d.add(0.0, 0.5, 0.0);
                vec3d2 = vec3d3;
                Intrinsics.checkNotNullExpressionValue(vec3d3, "add(...)");
            }
            this.setPlacePos(vec3d2);
            i = true;
        }
        pendingBreak = () -> AutoCrystal.shouldBreak$lambda$0(n, vec3d);
        if (this.getTiming() == AutoCrystalTimingMode.VANILLA || bl) {
            Function0 function0 = pendingBreak;
            Intrinsics.checkNotNull(function0);
            function0.invoke();
            pendingBreak = null;
        }
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private final void runWithAntiWeakness(long var1_1, Function0 var3_2) {
        block15: {
            block13: {
                block14: {
                    block12: {
                        block8: {
                            block9: {
                                block11: {
                                    block10: {
                                        v0 = (var1_1 = AutoCrystal.ab ^ var1_1) ^ 84843823244093L;
                                        var4_3 = (int)(v0 >>> 48);
                                        var5_4 = (int)(v0 << 16 >>> 48);
                                        var6_5 = (int)(v0 << 32 >>> 32);
                                        var22_6 = Globals.getPlayer().getStatusEffect(StatusEffects.WEAKNESS);
                                        var23_7 = Globals.getPlayer().getStatusEffect(StatusEffects.STRENGTH);
                                        var24_8 = -1;
                                        var25_9 = InventoryUtil.INSTANCE.b();
                                        var26_10 = InventoryUtil.INSTANCE.findInInventory((short)var4_3, ToolPredicate.O, (short)var5_4, var6_5);
                                        var20_11 = ZkmReferences.zkmBootstrap("\u00c5", (long)2846407539800065592L, (long)var1_1);
                                        v1 /* !! */  = this.NQ();
                                        if (var20_11 != null) break block8;
                                        if (v1 /* !! */  == AutoCrystalAutoSwitchMode.NONE) break block9;
                                        v1 /* !! */  = var22_6;
                                        if (var1_1 <= 0L || var20_11 != null) break block8;
                                        if (v1 /* !! */  == null) break block9;
                                        v2 = var23_7;
                                        if (var1_1 <= 0L || var20_11 != null) break block10;
                                        if (v2 == null) break block11;
                                        v2 = var23_7;
                                    }
                                    v3 = v2.getAmplifier();
                                    v4 = var22_6.getAmplifier();
                                    if (var1_1 <= 0L || var20_11 != null) break block12;
                                    if (v3 >= v4) break block9;
                                }
                                var28_12 = this.NQ();
                                var29_13 = var26_10;
                                var24_8 = this.switchToCrystal(var25_9, var29_13, var28_12);
                            }
                            v1 /* !! */  = var3_2.invoke();
                        }
                        v3 = var24_8;
                        v5 = var20_11;
                        if (var1_1 <= 0L) break block13;
                        if (v5 != null) break block14;
                        v4 = -1;
                    }
                    if (v3 == v4) break block15;
                    v3 = AutoCrystal$WhenMappings.J[this.NQ().ordinal()];
                }
                if (var1_1 < 0L) ** GOTO lbl51
                v5 = var20_11;
            }
            if (v5 != null) ** GOTO lbl51
            switch (v3) {
                case 1: {
                    v3 = var24_8;
lbl51:
                    // 3 sources

                    InteractionUtil.clickSlot(v3, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
                    if (var20_11 == null) break;
                }
                case 2: 
                case 3: {
                    if (this.NQ() == AutoCrystalAutoSwitchMode.SILENT) {
                        var27_14 = AutoCrystal.Y;
                        AutoCrystal.Y = var27_14 + 1;
                    }
                    InventoryUtil.INSTANCE.selectSlot(var24_8);
                }
            }
        }
    }

    public final void placeCrystal(byte by, @NotNull BlockHitResult blockHitResult, boolean bl, long l) {
        boolean bl2;
        PlaceResult placeResult;
        long l2;
        long l3 = l2 = ((long)by << 56 | l << 8 >>> 8) ^ ab;
        long l4 = l3 ^ 0x45C79E611529L;
        int n = (int)(l4 >>> 32);
        int n2 = (int)(l4 << 32 >>> 48);
        int n3 = (int)(l4 << 48 >>> 48);
        long l5 = l3 ^ 0x5296DEBBBE89L;
        long l6 = l3 ^ 0x291544BDD8F2L;
        Intrinsics.checkNotNullParameter(blockHitResult, "bhr");
        Object var45_11 = null;
        if (this.isHoldingCrystal(l6, true)) {
            return;
        }
        BlockPos blockPos = blockHitResult.getBlockPos();
        Intrinsics.checkNotNullExpressionValue(blockPos, "getBlockPos(...)");
        boolean bl3 = this.getLimitPlace();
        if (CrystalTracker.INSTANCE.isPlacedAt(blockPos, bl3)) {
            return;
        }
        PlaceResult placeResult2 = placeResult = (PlaceResult)placeResultRef.get();
        float f = this.getMinDamage();
        float f2 = this.getStacksLeft();
        boolean bl4 = this.getLowCrystals();
        boolean bl5 = this.getFacePlaceSync();
        AutoCrystalDamageSyncMode autoCrystalDamageSyncMode = this.getDamageSync();
        if (!CrystalTimers.INSTANCE.shouldSync(placeResult2 != null ? placeResult2.getDamageData() : null, n, bl2 = this.getRandomizeDelay() && HS % 5 == 0, n2, autoCrystalDamageSyncMode, bl5, (short)n3, bl4, f2, f)) {
            return;
        }
        BlockPos blockPos2 = blockHitResult.getBlockPos();
        Intrinsics.checkNotNullExpressionValue(blockPos2, "getBlockPos(...)");
        CrystalContext crystalContext = this.recalculate();
        boolean bl6 = false;
        BlockPos blockPos3 = blockPos2;
        if (CrystalPlacer.isPositionBlockedByEntity(this, l5, blockPos3, bl6, crystalContext)) {
            return;
        }
        if (this.getRotate() && !i) {
            if (this.getTiming() == AutoCrystalTimingMode.SEQUENTIAL) {
                T = !T;
            }
            Vec3d vec3d = blockHitResult.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getLocation(...)");
            this.setPlacePos(vec3d);
        }
        pendingPlace = () -> AutoCrystal.isValidPlace(blockHitResult, placeResult);
        if (this.getTiming() == AutoCrystalTimingMode.VANILLA || bl) {
            Function0 function0 = pendingPlace;
            if (function0 != null) {
                function0.invoke();
            }
            pendingPlace = null;
        }
    }

    private final int switchToCrystal(SlotResult slotResult, SlotResult slotResult2, AutoCrystalAutoSwitchMode autoCrystalAutoSwitchMode) {
        int n = Globals.getPlayer().getInventory().getSelectedSlot();
        Object var10_5 = null;
        switch (AutoCrystal$WhenMappings.J[autoCrystalAutoSwitchMode.ordinal()]) {
            case 1: {
                if (!slotResult2.isFound()) break;
                InteractionUtil.clickSlot(slotResult2.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
                return slotResult2.getSlot();
            }
            case 2: 
            case 3: {
                if (autoCrystalAutoSwitchMode == AutoCrystalAutoSwitchMode.SILENT) {
                    int n2 = Y;
                    Y = n2 + 1;
                }
                slotResult.select();
            }
        }
        return n;
    }

    private final void K(int n, int n2, boolean bl, boolean bl2) {
        if ((this.Nn() == AutoCrystalAutoSwitchMode.SILENT || bl) && n != -1) {
            if (bl) {
                InteractionUtil.clickSlot(n2 < 9 ? n2 + 36 : n2, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
            } else if (this.Nn() == AutoCrystalAutoSwitchMode.SILENT) {
                int n3 = Y;
                Y = n3 + 1;
                InventoryUtil.INSTANCE.selectSlot(n);
            }
        }
    }

    private final void switchForPlace(BlockHitResult blockHitResult) {
        Object object = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.STRING, "STRING");
        SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar((Item[])object);
        Object object2 = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.REDSTONE, "REDSTONE");
        object = InventoryUtil.INSTANCE.findInHotbar((Item[])object2);
        Object var16_5 = null;
        Object object3 = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.TORCH, "TORCH");
        object2 = InventoryUtil.INSTANCE.findInHotbar((Item[])object3);
        Object object4 = slotResult.isFound() ? slotResult : (object3 = ((SlotResult)object).isFound() ? object : object2);
        if (((SlotResult)object3).isFound()) {
            InventoryUtil.INSTANCE.switchToSlot(((SlotResult)object3).getSlot());
            Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, new BlockHitResult(blockHitResult.getBlockPos().toCenterPos().add(0.0, 0.5, 0.0), Direction.UP, blockHitResult.getBlockPos(), false));
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, blockHitResult.getBlockPos().up(), Direction.UP));
            Globals.getInteractionManager().breakBlock(blockHitResult.getBlockPos().up());
            InventoryUtil.INSTANCE.restoreSelectedSlot();
        }
    }

    public final boolean isBlockedByEntity(@NotNull BlockPos blockPos, @NotNull ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(blockPos, "base");
        Intrinsics.checkNotNullParameter(arrayList, "entities");
        Box box = new Box(blockPos.up());
        Object var3_4 = null;
        return arrayList.stream().anyMatch(arg_0 -> AutoCrystal.invokePredicate(arg_0 -> AutoCrystal.intersectsEntity(box, arg_0), arg_0));
    }

    public final void swingHand(boolean bl, boolean bl2) {
        if (!bl2) {
            Globals.getPlayer().swingHand(bl ? Hand.OFF_HAND : Hand.MAIN_HAND);
        } else {
            PacketUtil.sendPacket(new HandSwingC2SPacket(bl ? Hand.OFF_HAND : Hand.MAIN_HAND));
        }
    }

    public final int getPredictTicks(long l) {
        long l2 = (l = ab ^ l) ^ 0x7C468AAC0236L;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        return switch (AutoCrystal$WhenMappings.I[this.getPredict().ordinal()]) {
            case 1 -> Math.min((int)Math.floor((double)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null) * 1.5 / 50.0), 10);
            case 2 -> this.l();
            case 3 -> 0;
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    /*
     * Unable to fully structure code
     */
    public final int getPingTicks(int var1_1, short var2_2, boolean var3_3, short var4_4) {
        block7: {
            var5_5 = ((long)var1_1 << 32 | (long)var2_2 << 48 >>> 32 | (long)var4_4 << 48 >>> 48) ^ AutoCrystal.ab;
            v0 = var5_5 ^ 104783314510306L;
            var11_6 = v0 >>> 16;
            var13_7 = (int)(v0 << 48 >>> 48);
            var14_8 = ZkmReferences.zkmBootstrap("\u00c5", (long)4154014846984378398L, (long)var5_5);
            v1 = AutoCrystal$WhenMappings.I[this.getPredict().ordinal()];
            if (var14_8 != null) ** GOTO lbl13
            switch (v1) {
                case 1: {
                    v1 = (int)var3_3;
lbl13:
                    // 2 sources

                    if (var14_8 != null) break;
                    if (v1 != 0) {
                        v1 = Math.min((int)Math.floor((double)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, var11_6, 1, (char)var13_7, null) / 50.0), (int)AutoCrystal.zkmBootstrap2("c", (int)8282, (long)(2247027096604274873L ^ var5_5)));
                        break;
                    }
                    v1 = 0;
                    break;
                }
                case 2: {
                    v1 = (int)var3_3;
                    if (var14_8 != null) break;
                    if (v1 != 0) {
                        v1 = this.l();
                        break;
                    }
                    v1 = 0;
                    break;
                }
                case 3: {
                    v1 = 0;
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            if (var1_1 < 0 || ZkmReferences.zkmBootstrap("\u00c5", (long)4116563721996937137L, (long)var5_5) == null) break block7;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new String[1], (long)4071270640453211588L, (long)var5_5);
        }
        return v1;
    }

    public final boolean findTarget(int n, short s, int n2) {
        int n3;
        block8: {
            long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ ab;
            PlayerEntity playerEntity = target;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-3012541384111868023L, (long)l);
            if (playerEntity == null) {
                return false;
            }
            Iterator iterator2 = Lists.newArrayList(Globals.getWorld().getEntities()).iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, (String)((Object)AutoCrystal.zkmBootstrap("x", (int)32542, (long)(0x729111D3B83CDB10L ^ l))));
            Iterator iterator3 = iterator2;
            block0: while (iterator3.hasNext()) {
                boolean bl;
                block9: {
                    Object e = iterator3.next();
                    Intrinsics.checkNotNullExpressionValue(e, (String)((Object)AutoCrystal.zkmBootstrap("x", (int)22453, (long)(0x21C4CB46B58CF3EDL ^ l))));
                    Entity entity = (Entity)e;
                    do {
                        Box box;
                        block16: {
                            block12: {
                                block15: {
                                    CallSite callSite2;
                                    block13: {
                                        block14: {
                                            block10: {
                                                block11: {
                                                    n3 = entity instanceof ItemEntity;
                                                    callSite2 = callSite;
                                                    if (n2 > 0) {
                                                        if (callSite2 != null) break block8;
                                                        callSite2 = callSite;
                                                    }
                                                    if (n2 < 0) break block10;
                                                    if (callSite2 != null) break block11;
                                                    if (n3 == 0) break block12;
                                                    n3 = Intrinsics.areEqual(((ItemEntity)entity).getStack().getItem(), Items.OBSIDIAN) ? 1 : 0;
                                                }
                                                callSite2 = callSite;
                                            }
                                            if (n2 < 0) break block13;
                                            if (callSite2 != null) break block14;
                                            if (n3 == 0) break block12;
                                            n3 = ((ItemEntity)entity).getStack().getCount();
                                        }
                                        if (n < 0) break block15;
                                        callSite2 = callSite;
                                    }
                                    if (callSite2 != null) break block15;
                                    if (n3 < AutoCrystal.zkmBootstrap2("c", (int)2986, (long)(0x45B3866DFC9034C7L ^ l))) break block12;
                                    n3 = ((ItemEntity)entity).isAlive() ? 1 : 0;
                                }
                                if (n3 != 0) break block16;
                            }
                            if (callSite == null) continue block0;
                        }
                        Intrinsics.checkNotNullExpressionValue(playerEntity.getBoundingBox(), (String)((Object)AutoCrystal.zkmBootstrap("x", (int)23090, (long)(0x7F1926C88C7DFE1DL ^ l))));
                        bl = ((ItemEntity)entity).getBoundingBox().intersects(box.withMinY(box.getCenter().getY()).expand(0.5, 0.0, 0.5));
                        if (callSite != null) break block9;
                        if (bl) break;
                        if (callSite == null) continue block0;
                    } while (s <= 0);
                    bl = true;
                }
                return bl;
            }
            n3 = 0;
        }
        return n3 != 0;
    }

    public final void setPlacePos(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "v");
        o.reset();
        rotationTarget.set(vec3d);
    }

    private final CrystalContext calculateFor(PlayerEntity playerEntity) {
        ArrayList arrayList = Lists.newArrayList(Globals.getWorld().getEntities());
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        boolean bl = this.getOverrideMinDamage();
        boolean bl2 = this.getAntiQuiver();
        boolean bl3 = this.getAntiPortal();
        boolean bl4 = this.getExplodeEChestFarm();
        boolean bl5 = this.getGrimBreakRange();
        boolean bl6 = this.getRemoveLava();
        boolean bl7 = this.getCalcShields();
        float f = this.getMaxDamageReduction();
        boolean bl8 = this.getMinimizeSelfDamage();
        int n = this.getLethalMultiplier();
        boolean bl9 = this.getTerrainIgnore();
        boolean bl10 = this.getRemoveFire();
        AutoCrystalFriendProtectionMode autoCrystalFriendProtectionMode = this.getFriendProtection();
        boolean bl11 = this.getRayTrace();
        return new CrystalContext(arrayList, playerEntity, this.getPlaceRange(), this.getExplodeRange(), this.getMaxSelfDamage(), this.getMinDamage(), this.getOldVer(), this.getArmorScale(), this.getFacePlaceHp(), this.getOverrideSelfDamage(), this.getEfficiencyFactor(), bl11, autoCrystalFriendProtectionMode, bl10, bl9, n, bl8, f, bl7, bl6, bl5, bl4, bl3, bl2, bl);
    }

    @NotNull
    public final CrystalContext recalculate() {
        PlayerEntity playerEntity = target;
        Intrinsics.checkNotNull(playerEntity);
        return this.calculateFor(playerEntity);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getPredict() == AutoCrystalPredictMode.CUSTOM;
    }

    private static final boolean settingVisible2() {
        return !INSTANCE.getGrimBreakRange();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getOverrideMinDamage();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getOverrideMinDamage();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getOverrideMinDamage();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getOverrideMinDamage();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getOverrideMinDamage();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getDamageSync() == AutoCrystalDamageSyncMode.CONDITIONS;
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getDamageSync() == AutoCrystalDamageSyncMode.CONDITIONS;
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getDamageSync() == AutoCrystalDamageSyncMode.CONDITIONS && INSTANCE.getLowCrystals();
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getMinimizeSelfDamage();
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getFakeCrystalRemove();
    }

    private static final void onPlayerUpdate$lambda$0(CrystalContext crystalContext) {
        long l = ab ^ 0x67874DD71379L;
        long l2 = l ^ 0x5E529014DF5FL;
        INSTANCE.calculate(crystalContext, l2);
    }

    private static final void onReceivePacket$lambda$0(Packet packet) {
        long l = ab ^ 0x44CE8ED14140L;
        long l2 = l ^ 0x3BAE5A052185L;
        INSTANCE.trackCrystalSpawn(l2, ((EntitySpawnS2CPacket)packet).getEntityId(), new Vec3d(((EntitySpawnS2CPacket)packet).getX(), ((EntitySpawnS2CPacket)packet).getY(), ((EntitySpawnS2CPacket)packet).getZ()));
    }

    private static final Unit shouldBreak$lambda$0(int n, Vec3d vec3d) {
        long l = ab ^ 0x4CA1B642D442L;
        long l2 = l ^ 0x5164F2238467L;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 48);
        int n4 = (int)(l2 << 32 >>> 32);
        AutoCrystal autoCrystal = INSTANCE;
        StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.WEAKNESS);
        StatusEffectInstance statusEffectInstance2 = Globals.getPlayer().getStatusEffect(StatusEffects.STRENGTH);
        Object var36_9 = null;
        int n5 = -1;
        SlotResult slotResult = InventoryUtil.INSTANCE.b();
        SlotResult slotResult2 = InventoryUtil.INSTANCE.findInInventory((short)n2, ToolPredicate.O, (short)n3, n4);
        if (autoCrystal.NQ() != AutoCrystalAutoSwitchMode.NONE && statusEffectInstance != null && (statusEffectInstance2 == null || statusEffectInstance2.getAmplifier() < statusEffectInstance.getAmplifier())) {
            AutoCrystalAutoSwitchMode autoCrystalAutoSwitchMode = autoCrystal.NQ();
            SlotResult slotResult3 = slotResult2;
            n5 = autoCrystal.switchToCrystal(slotResult, slotResult3, autoCrystalAutoSwitchMode);
        }
        PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = PlayerInteractEntityC2SPacket.attack(Globals.getPlayer(), Globals.getPlayer().isSneaking());
        Intrinsics.checkNotNullExpressionValue(playerInteractEntityC2SPacket, "createAttackPacket(...)");
        PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket2 = playerInteractEntityC2SPacket;
        PacketAccess.setEntityId(playerInteractEntityC2SPacket2, n);
        PacketUtil.sendPacket(playerInteractEntityC2SPacket2);
        CrystalTracker.INSTANCE.onSpawn(TuplesKt.to(n, vec3d));
        ArrayList arrayList = Lists.newArrayList(Globals.getWorld().getEntities());
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        Object object = arrayList;
        Iterable iterable = object;
        Collection collection = new ArrayList();
        for (Object t : iterable) {
            Entity entity = (Entity)t;
            if (!(entity instanceof ItemEntity && vec3d.squaredDistanceTo(INSTANCE.getPos(entity)) < 2.25)) continue;
            collection.add(t);
        }
        for (Object e : (List)collection) {
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            object = (Entity)e;
            CrystalTracker.INSTANCE.recordSpawn(((Entity)object).getId(), Globals.now());
        }
        INSTANCE.swingHand(false, true);
        targetTimer.reset();
        int n6 = HS;
        HS = n6 + 1;
        if (n5 != -1) {
            switch (AutoCrystal$WhenMappings.J[autoCrystal.NQ().ordinal()]) {
                case 1: {
                    InteractionUtil.clickSlot(n5, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
                    break;
                }
                case 2: 
                case 3: {
                    if (autoCrystal.NQ() == AutoCrystalAutoSwitchMode.SILENT) {
                        ++Y;
                    }
                    InventoryUtil.INSTANCE.selectSlot(n5);
                }
            }
        }
        return Unit.INSTANCE;
    }

    private static final Packet createPlacePacket(boolean bl, BlockHitResult blockHitResult, int n) {
        return new PlayerInteractBlockC2SPacket(bl ? Hand.OFF_HAND : Hand.MAIN_HAND, blockHitResult, n);
    }

    private static final Unit isValidPlace(BlockHitResult blockHitResult, PlaceResult placeResult) {
        Object object;
        long l;
        long l2 = l = ab ^ 0x7585F6C603B3L;
        long l3 = l2 ^ 0x1981A499FB2L;
        int n = (int)(l3 >>> 56);
        long l4 = l3 << 8 >>> 8;
        long l5 = l2 ^ 0x579283A66FF9L;
        int n2 = (int)(l5 >>> 48);
        long l6 = l5 << 16 >>> 16;
        long l7 = l2 ^ 0x2F43DF48397FL;
        long l8 = l2 ^ 0x79024F36BE7FL;
        int n3 = (int)(l8 >>> 32);
        int n4 = (int)(l8 << 32 >>> 48);
        int n5 = (int)(l8 << 48 >>> 48);
        Block block = Globals.getWorld().getBlockState(blockHitResult.getBlockPos().up()).getBlock();
        Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
        Block block2 = block;
        Object var92_12 = null;
        int n6 = -1;
        boolean bl = Globals.getPlayer().getOffHandStack().getItem() instanceof EndCrystalItem;
        boolean bl2 = Globals.getPlayer().getMainHandStack().getItem() instanceof EndCrystalItem || bl;
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.END_CRYSTAL, "END_CRYSTAL");
        int n7 = InventoryUtil.INSTANCE.findInHotbar(itemArray).getSlot();
        boolean bl3 = INSTANCE.Nn() == AutoCrystalAutoSwitchMode.INVENTORY;
        boolean bl4 = false;
        boolean bl5 = false;
        if (INSTANCE.getRemoveFire() && Intrinsics.areEqual(block2, Blocks.FIRE)) {
            PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, blockHitResult.getBlockPos().up(), Direction.UP));
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        }
        if (INSTANCE.getRemoveLava() && INSTANCE.getRemoveFire() && Intrinsics.areEqual(block2, Blocks.TRIPWIRE)) {
            PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, blockHitResult.getBlockPos().up(), Direction.UP));
            Globals.getInteractionManager().breakBlock(blockHitResult.getBlockPos().up());
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        }
        if (INSTANCE.getRemoveLava() && Intrinsics.areEqual(block2, Blocks.LAVA)) {
            INSTANCE.switchForPlace(blockHitResult);
        }
        if (!bl2 && !((Collection)SpeedMine.getMinePositions$default(SpeedMine.INSTANCE, false, 1, null, l7)).isEmpty() && SpeedMine.INSTANCE.getResetOnSwitch() && SpeedMine.INSTANCE.FC() == SpeedMineSwitchMode.Alternative) {
            bl5 = true;
            bl3 = true;
        }
        if (bl3 && !Globals.getPlayer().currentScreenHandler.getCursorStack().isEmpty()) {
            return Unit.INSTANCE;
        }
        if (bl3 && n7 == -1) {
            object = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.END_CRYSTAL, "END_CRYSTAL");
            n7 = InventoryUtil.INSTANCE.findInInventory((Item[])object, (char)n2, l6).getSlot();
        }
        if (INSTANCE.Nn() != AutoCrystalAutoSwitchMode.NONE && !bl2 && n7 != -1) {
            n6 = Globals.getPlayer().getInventory().getSelectedSlot();
            if (bl3) {
                InteractionUtil.clickSlot(n7 < 9 ? n7 + 36 : n7, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, true);
                bl4 = true;
                mineSyncFlag.set(bl5);
            } else {
                if (INSTANCE.Nn() == AutoCrystalAutoSwitchMode.SILENT) {
                    int n8 = Y;
                    Y = n8 + 1;
                }
                InventoryUtil.INSTANCE.selectSlot(n7);
            }
        }
        if (!(n7 != -1 && INSTANCE.Nn() != AutoCrystalAutoSwitchMode.NONE || bl2)) {
            return Unit.INSTANCE;
        }
        if (Globals.getPlayer().getMainHandStack().getItem() instanceof EndCrystalItem || bl || INSTANCE.Nn() == AutoCrystalAutoSwitchMode.SILENT) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Intrinsics.checkNotNullExpressionValue(blockPos, "getBlockPos(...)");
            BlockPos blockPos2 = blockPos;
            CrystalTracker.INSTANCE.isBlocked(blockPos2);
            PacketUtil.sendSequencedPacket(arg_0 -> AutoCrystal.createPlacePacket(bl, blockHitResult, arg_0));
            BlockPos blockPos3 = blockHitResult.getBlockPos();
            Intrinsics.checkNotNullExpressionValue(blockPos3, "getBlockPos(...)");
            Direction direction = blockHitResult.getSide();
            Intrinsics.checkNotNullExpressionValue(direction, "getDirection(...)");
            Object object2 = placeResult;
            float f = object2 != null && (object2 = ((PlaceResult)object2).getDamageData()) != null ? ((DamageData)object2).getSelfDamage() : 1.0f;
            Object object3 = placeResult;
            float f2 = object3 != null && (object3 = ((PlaceResult)object3).getDamageData()) != null ? ((DamageData)object3).getDamage() : 1.0f;
            Direction direction2 = direction;
            BlockPos blockPos4 = blockPos3;
            placeCandidate = new PlaceCandidate(blockPos4, direction2, f2, f);
            INSTANCE.swingHand(bl, false);
            BlockPos blockPos5 = blockHitResult.getBlockPos();
            Intrinsics.checkNotNullExpressionValue(blockPos5, "getBlockPos(...)");
            PendingBlocks.INSTANCE.add(blockPos5);
            object = AutoCrystal$EntriesMappings.enumEntries;
            Iterator iterator2 = object.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                Direction direction3 = (Direction)t;
                BlockPos blockPos6 = blockHitResult.getBlockPos().offset(direction3);
                Intrinsics.checkNotNullExpressionValue(blockPos6, "relative(...)");
                PendingBlocks.INSTANCE.add(blockPos6);
            }
            if (INSTANCE.getRenderMode() == AutoCrystalRenderMode.TECHNO) {
                AutoCrystalRender.INSTANCE.addFade();
            }
            if (!(!INSTANCE.getIdPredict() || INSTANCE.getIdPredictOnlyDuels() && !(Globals.getWorld().getWorldBorder().getSize() == 100.0) || INSTANCE.getOnlyBreakingSurr() && !(!((Collection)SpeedMine.INSTANCE.getMinePositions(n3, false, (short)n4, (char)n5)).isEmpty()))) {
                AutoCrystalHelper.INSTANCE.switchSlot(INSTANCE.getIdOffset(), INSTANCE.getIdAttacks(), INSTANCE.getIdDelay(), INSTANCE.getSwingOnce());
                if (!He) {
                    He = true;
                    INSTANCE.placeCrystal((byte)n, blockHitResult, true, l4);
                }
                He = false;
            }
            e.reset();
            Object object4 = placeResult;
            CrystalTimers.INSTANCE.setDelay(object4 != null && (object4 = ((PlaceResult)object4).getDamageData()) != null ? ((DamageData)object4).getDamage() : 0.0f);
            placedPositions.add(blockHitResult.getBlockPos());
            while (true) {
                if (placedPositions.size() <= 10) break;
                placedPositions.poll();
            }
            renderAnimation.animateTo(1.0f);
            INSTANCE.K(n6, n7, bl4, bl5);
        }
        return Unit.INSTANCE;
    }

    private static final boolean intersectsEntity(Box box, Entity entity) {
        return entity != null && entity.getBoundingBox().intersects(box) && entity instanceof EndCrystalEntity;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
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
                                AutoCrystal.ab = ZkmKeys.a(-7331341206250229805L, 5465881950904655000L, MethodHandles.lookup().lookupClass()).a(273753244111873L);
                                var31 = AutoCrystal.ab ^ 93279532678059L;
                                AutoCrystal.hb = new HashMap<K, V>(13);
                                AutoCrystal.zkmSetFlag("E6w6vb");
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
                                var29_3 = new String[261];
                                var27_4 = 0;
                                var26_5 = "\u008ay\u00d1\u008cs6\"@\u00df\u00d7\u008e\u00af\u00d1N\u0096h\u00da\u00e65\u00f2;\\M\u00e3oB\u0000N\u00d3,\u00b0\u00bf\u0018\u0003\u00ecQ\u00f0V\u00f4\u00f5\u0095\u00a2\u00d1\u0082\u009f\u00d0\u00f1%\u00b9i\u0003\u00b5\u009d\u00e2\u00a4\u0094\u0011(\u0016\u00a0\u00cb\u00ab\u009a\u00b7\u009aRS\u001bP\u00c3\u0086\u00bd\u0095\u00f7\u0018\u00d7\u0089\u00fbi)KO\u0003;&\u00e5\u0087c\u0080n\u00b19Hz\u00d3h\u001cbH\u00bcd\u008a\u00c3\u0007\u00d1\u00f8\u00bc|\u00ad\u009b\u001c\u0002\u008e\u0088?\u009f\u0095j\u00c0\u00cd\u0092]\u00a1r\u00c6\u001a\u0004\r%W\u00d7\u00b31WX(\u00188*\u00ffw3-\u00bc(\u00bd\u0007\u00d6\u00a0\u00fdR=\u00f0F\\Iu\u00f6\u00ea\u00b2Gm\u00f3ML@\u00c9k;-c($\u00f8|a\u00d0\u001c]7\u0096\u00b3\u0088\u00c8\u00a4R\u00ceT\bO\u00b0,i\u00a4]\u009c\u00fd\u00edK\u0089\u0002i$\u00c0U0\u00a4\u00bf\u0017\u00ade\u008d \u0015*\u0002\u00e0\u009eN\u00b4\u00885\u001duS\u00c83\"\u008a\u00ba<W\u00f1\u00cf\u00dbr\u00f3l\u00df\u0014r\u000eXf\u00aa\u0010\u00ac\u009a\u00c9\u00daf\u00dd\u0010\r\u00bf\u001d\t.\u00b7\u001e\u00fe\u001d8\u00f7\u00fc\u00c3\u0015y\u00c5\u0014\u00d1\u00a8\u00fa\u0007\u0015\u0019\u00db\u0001\u009b\u00a1\u0003\n\u008eO^\"\u00f4\u00e5\u00b0An\u0095\u00f9j\u00ee\u00ac\u00eal\u0089|\u00b9\u00d9\u00e5a\u00b6\u001c`\u00d8`\u008b-\u0018\u00843\u00eb\u001a\u00fbD\u0018(e\u00a0\u0005~z\u0085\u00c8u\u008c5\u00fbm\u00ad\n\u00e3\u001a\u00a2+D</8D\u00f8[\u00ce\u00d6H\u00ecU\u00b8\u00c4Z\u00ff&\rV1\u00ba\u0007 \u00e1\u00e6qU\u00e2\u00a1\u001a\u00ff\u0019v\u00c4\u00a9.\u00a7\u00e8\u00c8e;X\u0001\u00c2\u00c5\u00bb\u00c4f\"\u00dd\u0088aL\u0090\u00d1\u0018\u0083b\u00cd\u00f4<\u009dXUK\u00c15\u00e4\u00a2\u00e2\u00cc\u0081n$j\u000b\u00e8\u00b0b\u00c4(u+\b\u0005<\u00bfC\u00b7G*\u0013? \u00f7\u00d9\u00f4\u0080\u00f0\u00a9\u0093P\u00a5Y~\u0083\u0081o\u00b1\u00c4Yf\u0094$0\u00da\\\u000f\u00b5\u00b9\u00e1\u0018\u00cbw\u00cc\u00e3\u00126g\u0094\u0097\u0015X4\u00a65o\u00c3\u00d6Y\u0098\u00d3\u00cb\u00db\u0019G\u0018`\u001eYN\u00f1\u00bbF\u00a9\u00bd\u00b6nqn\u00dc\u001b\u00f6\u00e4x\b5`y\u00e0\"\u0010:\u00cf\u00a0\u00a3f\u00ac#\u009c\u0002\u0094\u00b4\u001bN\u00ee\u00cdq\u0010G\u00d9U\u00e9l\u00f9\u00efH\"{\u009b\u00a0F-\u00ee,0\u0001y\u00bdnze #B\u0013UF\u008f\u00cf\u00fd\u00c8[\u00c2RZ\u00c3z\u00d8\u0015\u00f2\u009f\u0016\u007f@\u00b0\u0018$o\u00cf\u00a3\u00cd~\u008d\u00b7F\u0084\u00c2\u001e\u00c1h*!\u00ec(\u00ea\u00ba\u00d1\u00e0\u009b\u00d6\u00ee\u008f\u00c6\"\u009c\u00d5\u00b7z\u0011\u008aOQ\u00b2l;\u00e1<\u00b0\u00de3\u0085\u00d1S\u00c2\u00da\u00d4\u00de\u00aa\u0001Y\u00e0l\u0013X\u0010\u000f*\u00b5\u0011\u008b\u00ef\" -\u00b2\u0010L\u00deqf\u00ef0\u00de\u00d4\u00d0\u00b9T9\u0001\u00bbz\u0085\u0004\u00aa\u0015x\u00e2\u008b\u0091\u00fbu\b\u0097\u00b02\u00c4\u00f7rE8\u0014D\u00f7\u00ee\u00f9qOL&\u00f7\u0093a\u00bcn\u0017s\u008b<\u0099\u00a9 \u001d\u0019\u00b9\u0010vv\u00015\u0086\u00b1\u00d1\u00a4\u00a4\"\u00b59ei\u00b5&\u00cc\u00ff\u0011I,\u00cfJ\u00c4\u00bc\u00f8\u00e6~@5\u00f8\u00e7\u009f\u001d\u009f-\u00c6_\u00b2\u00ec7\u001f\u00ac\u009dmC\u00a9\u00d2t\u00dfV\u00b8\u00ee\u00b7\u00f9\u00c67\u007f_\u00de\u00d2\u00c2\u00ff\u00a5:\u00c1\u00ccy\u00d3\u00ed\u00a7\u000e\u00fb)\u00ac\u0006\u00ea\u0086@s2\b\u0095\u0082*\u00e8CJ\u00c9\u008b\u00f7\u00e4\u0004\u00108U~\u00ab\u0015\u00ff\u00d4\u00a2Z:\u00ed\u00c8\u00b7\u00ea\u00a85(\u0085\u00c4\u0001\u00b0Hq\u00e1\u0080\u001c\u00a8xI\u00ae\u00fa\u00b46\u0005\u00e8\u0018\u0098\u00f9\u00a6\u00c6<\u00c0`xXP\u00a5\u0088\u00e9;7\u00e8$\u00f9rL=(\u00c8\u00c4\u00f8B\u00db\u0086N\u00ff\u008a#\u001c\u0082R\u00e5\u00e8\u009d5+\u00f7\u00d2>\u0092>DmQ\u0004<\u00eb]\u00cd\u0010\u00de\u00eb\u0090\u0013J\u00c8\u0086\b \u00be\u00d5?\u001fA\u00a4\u00ae\u00f0b$q/$b\b\u0084\u0010\u00e6\u00ec\u0091\u0012\u00b6f\u00d1\u00b5\u0098A\u00da+\u0007\u001fV(\u009c\u000b\u0004\u001e\u007f\u00d4,\u0012{h\u00b79\u00eauWz\u00aa\u00beN\u000e\u00cf\u00b3\u00c5W\u0017\u00b2\u009d\u00b0E\u001a]\u00a8\\\u00f7s%n\u00f9\u00fe\u00f4(l:,mi\u009b\u00f6uf\u00e8\u00bd{\u0098\u0089q\u00f4xw\u0005P)\u00c6\u00e1 \u00d8_\u00fff\u00a8\u001a\"\u00e6\u00ce9\u00c1\u00a8\u0091\u00aepP8\u00f8\u0001J\u0019\u00b6\u00fb\u008f\u00fc\u0099J\u001d6\u0085\u0086@\u00a4\u0003\u00c7\u0004tDI&\u0013 ;g \u00d8{,E\u00b8&\u00e1\u00a0\u00d3\u0016\f\u0087\r\u00d9DN\u0091\u00df\u008f\u00ab\u000bi\u00e7l\u0091\u00be\u00ef(\u0010IE\bQ\u00c1\t\u0096\u00c6>\u00a9nr\u0002\u0002\u00d9Y iW\u009c,\u0092\u001b\u0088%\u000f6\f\u0019\u00a3Mt T\u00ca\u00b4j=\u00ac \u00fd\u00ffPT\u00a2i&1+ \u00c9\u008b\u0083\u00d1\u001bJ.'\u00a1\u00cf\u00fb\u0013\u00161 \nC\u00b9~\u00bb~\u00ef\u00f2\u001d%\u00e8R\u00cb\u00faox\u00928\u00cd\u0000\u0097#\u00f5\u00912`\u00a9\u00cf3\u00af\u001f\u00a1@\u0015\u00a6Cg7\u00bd3aq%9\u00be\u00f6\u00a9\u000e\u0014\u007f+\u00e7\u00b4\u00af\u0006\u00faNj\u00f6tA.\u0007\u00fc\u0097\u00de5\u009cib\u00ea\u00ee\u00ba\u00e3(\u0086\u0086\u000b'\u00f4&N\u00ec\u009e\u00f2]l\u0082\u00db\u00adl<\u00a2U\u00d97{B\u0006\u0013\u00b2\u00ac\u00e7P\u00b7\u00d7H\u00a7\u00c8\u00ed\u00f6\u00ac\u001d\u00b5\u00eb \u001bW\u0003M\u00c9\u00d88j\u00ef\tu]B\u00db\u00ceCJ|x%2\u00fd\u00bd\u00e45WHs\u00a0b\u001c\u00c7\u0010\u00ed@D\u0004B>\u008fB\u0096\u00f39\r\u009d\u00be\u0005\u00bd\u0010^0f\u00c9\u00ca\u00c3]\u00aa\u0087\u00daC\u00c7p\u00eb\u00d6\u00da(\u0014Z\u00d5~\u0005\u00b1o!\u00d7|pT\u00fa\u00c3\u00f9\u00d0\u00af\u008bF\u00cc\u00a9g\u00f9\u00d2\u009b\u00ba\u00f5\u00ab\u00fe\u00e4\u000b\u0011\u00e8xs\u0004\u00e0\u00d5\u001fc\u0018\u008d2K\u00db4.*\u00b7@H\u0087\u001b;\u00e64\u00dc\u0099\u00d5\u00beh\u00ed\u00db\u00f5a g\u00d1\u00d7\u00b7]P\"!5S\u00fe\u00cdC<i\u00ba\u00df\u009f7\u0083\u00e5\u00a9\u00d5p\u00e3\u00b0~_\u00cf/\u00ee\u00e7 \u00eb\u00bfA\u009f*\u00a9\n\u001a\u00ee`e\u009b\u00ec\u00f3\u00c0\u009c\u0090\u00bd\n\u00e0\u00dax\u00ffdf\u00d9T\u00cb\u00c4\u00a5\u00e0\u00a7\u0010:\u0089\u007f}\u00ca\u0004\u00da\u0018\u00bcOf\u00aft\u00b6\u00c3\u00cb\u0018jRx\u00df\u0014\u0011\u008d\u00efg\u0083WBW\u009c\u00b1\u00a7\u00ce\u00f4\u0017\u00d2\u00b8\u008e\u00d5h \u000e\u0088\u0014\u00d2\u00ef\u00f3\u0081\u007f\u0080\u00d9\u008f\u00e2\u008e)\u00e6\u00e1\u009fy\u008a\u000bn\u0089^\u0010\u0006\u00a7\u00e8|\u00aaw\u009bH \u0098\u00a2x\u00a9\u000e.\u00ed\u00d3t:\u00be\u00fe$\u0003\u00c0P\u00153[D!\u00c3\u00db\u0005\u00ca\u0005QWj\u00ea?\u00ac0N\u0087hn\u0092\u00b7\u00c0\u009d\u0012\u00d8\u00ff\u00ac\b\u00d0\u00c0\u00bb.N\u00a2\u00ceV\u00e7,rm\u00f6\u00c4\u00bd\u00d5\u00c8`\u00e9S\u00cf\u0098\u00b4\u00c6\u0012\u00e2\u0012t\u0092\u00a1\u00a0\u009b\u00a0\u008b\u00998\u0010\u0084\u00fa*a\u00ad\u00f8\u00f4\u001d,\u0082\u00ce\u00b4\u00ffd\u00f9\u00bf\u00b9\u00e9\u00f1\u00de\u00e0\u00c4\u0097\u00d6\u00bcw\u00f2\u00e6\u00e3h|\u00e7v\u0013\u0013\u00fe*\u00d8jS\u0099\u00e8l\u00bf\u00cb\u00c4\u00a6\u00c4\u0003\u001a\u00ae\u00a6sW\u0085\u0018\u00ae;\u0002\u0091\u00c1\u000f3\u00bb\u009a\u0081\u00a4\u00ce\u0091{\u00b9z\u00ae\u00d3JK\u00c2\u00c8\u00fa\u00b0 \u0092\u00fe\u00f8\u0098-\"\u00ef6#\u000b\u0080\u00024\u001e\u00b4\u00e1x\u00ca\u0083\u00d1*6\u000f\u008cFy\u00b0\u00fd<j\u008d\u00e3(I0=\u00f6la\u00fa\u00e2\u009e\u00a3\u00b5-\u008eE\u00f3@NP\u00d4^\u00e9x9\u00a9?Z\u00cbO\u00a3\u00f8\u0014&8*\u00f1E\u00f0\u00e7\u0096\r0\u00f9\u001b\u00b14dz\u00ae\u0005c\u0085K\u0087\u00af\u009eWTXT%n\u00fc\u000b\u0018(\u0011*\u009d\u00c9D\u00ac\u00bd\u00f9\u00c78\u00d5n7\u0013\n\u00ec\u009d\u00a7\u00a5Q2\u00d6\u00fa\u0094\u0010m\u0012.\u00c8\u00d01\u00ec\u00ac\u000e\u001f\u00dc\u0019K\u00b3\u008c\u00eb\u0018F|\u0081\u0080\u00e4tg@\u00fb\u00a1h\u0097\u00a3\u0082d\u00f5\u00e0\u00aeg\u00a9v\u00d3\u0097\u0010 m\u00ce\u00ef\u00e0\u00ed\u00d4\u00df\u00d9\u00e6\u00b3\u00bd\\\u00a7\u0002oY\u00db\u00e0\u00fb\u00ef\u00b2\u00ccr\u00a8\u00c4\u00cf\u00f3\u0089\u00f1\u00a4\u00e4\u00c6\u0018\u0014\u00c9R\u00dd\u00ed\u00c2\u0003\u00b5_\u00f5`\r\\M\u009d\u00bf\u00c6\u0002\u00c8\u00f4\u00101\u00fc)\u0010\u00ce\u00cf\u00de\u00a2\u0081P\u00b6\u00dcw\u0082#\u0003\u00beoQ\u00d5(V\u00a6V\u000430\u00fb\u00dddE-\u0007\u0013\u00c2G\u0090\u00b0w(\u00d1\u000bYE6\u0082\u00f0X\u00ad\u00f7lN\u00eb9H\u00c7\u00fa\u00c2H\u001a< \u0002#3\u0010\u00c8\u008as\u00ab[\u009at'\u00cf\u00a6\u00e0`\r\u00d9\u00d3\u00a9\u0082\u0000@\u0093\u00a3DY\u00f2\u00b4\u00dd4} \u0081\u00ffT\u0007:\u00b4)\u00b9\u009dR\u009f\u00dc\u00c8\u0012\u00c2d;\u00a4\u0097(\u00fd%E\u00c2\u009bP\u00d4\u00ee\u00b1\\_\u00d4(\u00c9\u0016\u0085^\u0093'1\u0094\u0093\u00acFB^\u00c1\u00d0\u0003\u00860\u00dc\u00aa\u00d6.^\u00f85E\u0088J\u00d1\u00c1b\u00ed\u0088\u00d8\u0091y\u00cd\u00c0\u0011\u00a5 \u00c9Y3\u009f\u00e1A\u00a49\u00e1\"\u00fdL,\u00a2c\u00e1\u00b1\u00f7L\u00a6P\f\u00ca\u009b\u00b0\u00f0\u0013\u00cb\u00ebb\u00d9\\\u0018^3\u0016\u00b5\u0012\u00f3\bO~c!\u0013\u0089\u0004\u00c0J\u00f9\u00d57\u00fe$$\r\u00da S\u00efj\u000e\u0001G#@\u0094\u008f\u00c4\u0002\u00c0\u00e6\u0017\u00b5\u0090d\f\u00fb\u00df\u00b9\u00cdY\u00d4\u00b6J7\u00e8\u00b0\u00c8\b0I\u0018f0G\u0087\u00ca>\u0091\u0080\u00cf1*]F\u0015\u00b8#[[\u009a\u00ed\u00ef\u0094\u00a5\u00dc\u009aU\u00f2O\u0082\u00cc\u00848x\u0087\u00a4+\u00c1T\u00b3\u00b0'\u0080<\u00937\u00dc(H\u00bf\u00d4\u0016\u007f\u001blu]\u009a\u00df\u00b5\u00d6\u00e9\u00eb\u009c\u00e1\u009b\u0019zf\u001f\u00e3\u009az\u00cc\u001f\u00ac\u0082\u000eF~\u0013du\u00d4II@\u001e \u00f9\t\u001c)CA\u00ea\u001c/n\u00fbxV\u00db\u00f0o\u00d9\u00f82\u00e2ii<\u00a9P);\u00f7qa\u0016\u0002\u0018\u00df\u00a4\u00f8L\u0090\u000bO\u0098h\u00f3\u00a9GVD\u00c2xb\u009dWo=\u00e0[\u00b2 \u00ab\u00f0\u00ef\u00f3\u00a8\u00e2\u00d3\u001dn@}\u0010\u0085\u000f\u00a1\u00fcb\u008f\n{\u00b1Em\u0005A\u00a0#\"~\u00b8\u00f9\u0086(\u0091\u00a6\u00ce\u00fc+\u001f\u00aa(\u00ad\u00d7\u00bf\u00ca\u0004\u001c\u00a6\u0091\u00e3DY\u00c8\u00d9\u00f1E\u009c\u00e2{\u00c1\u009e\u0091@~\u0085\u00f1\u00bb\u0091(\u00e5F>\u00b2 \u0081k\u0096\u0016\u00fb\u00c7\u00e1\u00e4\u0096\u00a5 \u001c;<\u0001\u0000\u00aa\u0007\u000b\u00c2\u00a8\u00e2\u00f0d\n( \u0017\u00ca\u00c8\u00a4.0P\u0004u\u0085f(\u008c\u00b5\u00d3(\u00e2Ru|\u00ce\u00de\u00d6p\u00e4\u00e2U\u00b9\u008e\u00b7\u0017\u00b4\u00d8r\fD\u0083\u00c3\u00876\u0012{\u00b1\u00c5Vft\u0002\u009e2\u00d4l\u00a6\u0019 #{-!\u00f3\u0002\u00ae\u00b1\bn\u00e7\u009cn\u00b83o\u00e0\u00ff,QT*\u00b5\u00d8}\u00f0\u0019\u00del\u00d4@\u00df(\u001a\u00ba\u00baV\u00c2\u0005\u00a5P\u00c6\u00a7qGAb\u000e\u00da\u0019\u0013\u00e6\u00ea\u0010\u00d1\u00aa\u008bL\u0000Z+$\u008e\u00ff+\u00f5\u00ccj\u0015\u0019\u0085\u00c9\u008a dC]\u00a5\u00d1\u001arX\u00b1\u00d8k\u009c\u00f3\u008d\u00b0\u00a8\u00aaF\u00875_\u00da\u00cf\u00c9\u009e|\u0014\u00ea\u00b6\u00ae\u00a2T\u0010\u00ee\u00c1\t\u00c9\u00d2\u00cf\u00af\u00e6^j\u00882\u009d&\u00b5> \u008fw#\u00cf\u00f6\u00c5d++\u00c3\u00d9CS\u00b9\u0019\u00b5@\u00cfu\u00c3\u0003\u00c5/\u00da\u001ao\u00fff\u00c3\u00ee\u00ffj(,\u00abS3&o\u00e6`\u00daI(\u00d1\u00cfx\u0013,w`\u00df]\u0000\u00d2\u0092J\u00ef~r\u008ep(n\u00bd\u00f9\u00d4h\u00d3mC\u00b7@8m\u0083\u00f1K\u0095@\u00e6\u0084\u0018\u00b2{\u00a6\u00e3\u0006\u00b2\u0014\u00a6%\u00e2\u00fd{cO\u00ed\u00e1P\u00c3\u00eb8\u00cd\u001ee\u00c3\u00f5\u00e2\u00ce\u0083D\u00ef\u009e\u00e2\u00cd\u0002\u0007\u00e6\u00e9\u009c\u0000\u00e7\u00b4!2\u00f1\u009cF\u00dc \u00a91\u0002\u00af\u00f9\u00c7\u00a7p\u00dd\u00d7>\u00dal\u00ebR\u00044\u00cdc\u00fc\u00dbGD\bC\u00e2\r\u00ca\u00a0#v\u001d(Lw\u0095\u00df\u008cZ?\u008c\u00d9v\u00cciw\u00b17\u009d\u0000\u00895\u00e0w\u00c5\u00ebd\u0098\u00f1eG\u007fm\u00a0M\b\u001cw\u009d\u0099\u00fej4\u0010\u0004;\n\u00a1\u00ad\u00b6\u00a9\u00a5D\u0095\u008eU\u00da\u009f\u00a6\u00d5\u0010\u00f0\u00cb\u008cxZg>rC\fh\u00e1\u00ed\u008fM> \u00f9\u00a2\u000f\u00f3\u00b8\u007f\u00e2\u00e8\u0001\u00dc\u001f\u00d6Fpd\u0003\u00cd\u00dd\u000f\u00f3\u00fa?\u00cc\u00dd*\u00e1\u0015\u00a0\u00e9%\u008e\u00ce(s\u00fa}\u00c3\u001c\u00d7\u0096S6\u00c3\u00d1j\u0007\u0095\u00acj\u009d\u00ed\u00ccSD\u009d\u0080~0L\u00f5H\r\u00cc~2\u001aSO\u0086\u0015~,\u00f3(t\u001b<&sq\u00b5\u00c3t\u00a6_#\u00af\u0001i\u00af\u0099\u0001<A-\u008aY\u00a5\u00a9\"\u00d5\u00e6\u00e0\u00e2\u00a9_oNAG!\u0005\u00c5Y0f!\u0095\u00c4\u00f0v%\u00a0\f\u0019A\u00f1[\u0003\u008e]r\u00da\u0012\u00a2\u00f8\u00d1`G@\u00ad\u009b|_T7`\u008f\u00e4\u0002\u00a4\u00dc\n\u00a3M+H\u00acM\u00deC\u0000} \u00e9\u000fI\u00cal\u0093\u00c4\u00c2\u00a5\u00ee\u00cd\u00c0\u009al\u00e7\u001ep\u00a1\u009a&\u0086\u00b7\u0002\u0081\u00b8ll\u00eeo\u00012\u00cc\u0010\u0000\u0097\u00a8\u00a5\u00df\u00b6&i\u00a3\u00a8xF\u000e\u0097\u00f5\u00c08jg\u00c4L5m\u00cb\u00dc6E\u0000(\u0098=\u00ee'\u00fdP\u00bco\u001a\u00b0\u00abn{8\u0015\u00a0^\u00f0V\u00de\u0000V\u00f0d\u00fa\u00f9[\u008a\u00a4\u00b8\u001b\u00933\b\u00eb\u00d6\u00df\u00a5\u00c5\u00a6\u00cd\u0089\u000f\u00f3\u0018\u0090\u00f8d+\u00ff\u00dd\u0003\u00cd\u0016<\u000bQX\u00d2\u00e4\u00da\u008e\u00d7\u00bc\u00e3\u00cc\u00e5n\u00b7\u0018\u0011\u00da\u00c4Y\u00865\u0002}\u0011\u00c9\u00f4\u00a17uuo\u00f6\u00c4\u00dfv\u00ae\u0089\b\u0085(V\u00c2\u00e8\u00f9\u0013\"\u0007\u000f\u00d1\u001d:\u00a8\u00bf\u00b8\u0007\u00b1\u00dd\u0012\u00c4\u00c0VW\u0001\u0093u\u00cb\u00e7\u00e2\nP\u00afKC\u00af#\u00b3\u000f9\u009c`8V\u00adv]\u0092\u00ce\u00b6\u00a0$Fm\u00ad\u00b1\u00a5\u00far\u00a7\u00e4\u00e8.GCR]\u00f1\u0017w\u009f\u00b6\u00b5\u00f3\u0097\u00f0FuO\u001a\u00ea\u0005MF\u00ab\u00e7k\u0018\u00e6Y'\u00f3\u00d0\u0017^\u00ccn\u0016\u0087(\u00c9G\u001ac\u00e0\u008f\u00ca\"z\u00a9\u0014\u00dc@\u007f-!o\u008e\u00e85\u00d2\u0006\u00b0\u009es\u0082\u00ff\u00dd\u0082\u0015\u00ce\u0097\u00d1|6\u00b3\u0087\u00e9p\u0012\u0010\u00ec\u00fa\u00c0\u00ca3p?\u00d4\u0098\u0001\u00bf*\u00c4\u007f\u00ff\u00d7 Q\u0097 #_\u00a1\u009c\u0015\u009a\u001aM\u00b9\u00ae\u00c1\u00ad\u00d8\u0001\u0012l[\u0011\u00c7f\u0001\u0010>\u009f\u00c2iv/\u00ce(\u00ab\u00b1\u00b3\u0090$\u00d2Z%\u009f\u0086\u000fD\\G\u0013\u00b2}\u00ea\u00c5\u001cw\u00b7\u0010\u0082\u00e96\u0012,\u0003\u00f6c\u000b\u00acS`\u00cdx\u00c6\u00f9\u00b5\u0010\u00e9\u00c0|\u0095\u00f0|9\u0088\u00b1H\u00ce\u0003\u0094z\u000e\u0093@{\u0084\u00d2\u00d7\u0010 \u001cZ-vxc\u00a6{\u00b2\u00e4\u00a6\u00c3L\u00c2\u0093\u00e1\u0012\u008dhC#=\u00ad\u008ep\u0083\u00ac\u00f6\u00b6\u00fc\u0082.x\u00d3S\u00d7\u00e5/\u0001\u00ed\u0082)\r \ftx\u00ae64\u00cf1M\u00f5\u0012,bY wB\u00d9\u00dd\u00f1]B\u0004E#\u00c1O\u008132/\u000e\u009b\u00bba\u00dc\u00119\u00fa\u00dd\u0098\u00ef\u008fF^\u00cc.0\u00eavc\u00aaI\u00ed\u00931\u00939W\u0086o\u00a8\u0004\u00bfWO\u009d\u0006\u0003\u0017\u0003\u001a\u00f0=\u00ad\u00ae\u0099\u000b\u00f5\u0080\u00e5LL\u001a\u00e5\u00e2\u0091\u00e3\u00f5r\u00e6\u0088\u0003\u00abt\u008d\u0010\u00c5\u00f1\u0099\u00ee\u00dao\u00df\u0080m\u0084\u00a5p\u008f\u0000y) \u009f\u0095\u00del\u00d7\u00c043\u008bnfr\u00a5\u00a7\u00ac\u00ae\u00ba\u0087W?\u000e\u00a2\u0099\u00d19XL\u00dam\u00a5\u00866(;\u00fco\u001e\u008cg \u00fb\u001f\t\u008b\u0011]bX\u00fb\u00d5@\u00c3\u00ed\u00c7A\u00d4\u00ecg\u0003\\Gz\u00fb9#\u00cf\u0013\u00c6\u00854\u00c2\u00fd\u0084\u0010\u00e1!\u0015=\u001fT\u0099\u00fa\u0096-\u00aa\u0016\u00a2\u0084\u00d2\u000e(\u00aeI\u008a\u00b4F\u001bz\u00ff\u000b\"\u0001\u001f fN\u00d8nJ3FL\u00d3D\u0092\u0089l\u00ea\u0007q\u00b8i|\u00d2B\u0089\u008a\u0084\u001a\u0007F8\u00fft\u00ab\u00a7\u001b\u0004\u00e3\u00f2rM\u00147\u00e2h\u00bfE\u00c4v\na\u000e\u00c3\u00b8\u00da\u00c7\u00af\u00157\u008a`<\u00d0\u0005[Q\u0012\u00b0\u0094\u0092\f\u00fe\bo\u009d\u00berD\u0010\u00d8l4gOt\u0082n \u00ea\u00cc=KQ\u00af\u00bc\u00b3\u00a3\u00f0\u00e0&\u008d\u0011,\u008e\u001a\u008a\u00d3;\u00c7\u00c9K\u0090\u00c8\u00f0\u001b\u00f7@RQ (C\u00a6%d\u009a\u00c0q0\u0014\u0082\u008bOX\u0086[\u000e\u00fb\u00f4\u00ac\f\u0007A\u00e4\u00ad\u00bc\u0002e\u00be\u00c9\u0017\u0082\u00b13P5?1>qH(@\u001c\u0089\u0087\u009c\u0097\r\u00e1\u00cb\u00ad,g\u0087'\u00c7\u00b5\u00f3\u00b8\u001f\u0015\u000e\u008f\u00e0\r+j'f\u0086Q\u00b9\u00ac\u0006\t\u0095\u00b1\u000e\u00bfS\u0098 +\u00db\u00c0x \u00cc8\u001ff\u00ff\u00e6\u00ac\u00e7\u00f3\u007f\u00aae\u00ba\u00c1#l\u00f8_F\u00c4\u00aa ^a\u00b0]\u009e\u0018g\u0095\u00b2^UV\u00a1aN\u00b5\n\u00af\u00b6\u00a3\u0095\u0085\u00ef\u0092\u00f0\u009d6Mj\u00d1\u0018eC\u00d1\u00ed\u00e1\u001b\u0095w?\f\u00f4\u000f\u00bb\u00dc\u00e8Gl\u00b0\u00b4*\u00cc\u00fer\u0097(0MN;\u00fa|\u00ca\u00db\u00de\u0016~\u00a4\u0016\u0006\u0001\u00d1s#M\u00c3M)\u00e8\u00a6^\u00b5\u0007\u0080\u00aeg\u00c7\u00cd\u00bbz\u00df\u000f\u009eo\u00a7\u00ef .w\u0010\u00bc\u00f1J\u0007\u00fc\u00e0z\u00f5\u0018w,]g\u001c*j\u009aw\u00d8\u00b1\u000eU\u00e1\u0019e\u00f9XL\u00b2\u0018\u00a6\u001d\u00b55\u008e\u00b8\u00ad\u00d4\u00a7\"$\u00c4\u00b8\u0017<\u009b\u0082mk\u008c\u0002\u00ed\u00b8\u00bb0W\u00d1\u00f4\u00eb\u00ff\u0017\u0014G\u008d,D\u0002\u00b6\u0080v\u00bb\u00b1\u0095\u00b9\u008ed\u00b6\u00f0\u0087\u0085pK\u00ba\u001d\u0098\u008e\u0013\u00b6\"q\u001fr1E5\u001b\u00f9\u0083d\u00068\u00e11 Xv\u00fe\u0001\u0094\u00f6\u00d9\u00c2d\u00a0\u001a\u00f54\u00ca\u00e1\u008b\u0081-\u00c5\u00ac0\u00c5E\"\u0001\u00ce\u00be\u0084\u0017\u00b0\u00c3\u0099(\u0096x\u00ba\u00e7|]8\u001f\u00d8\u00aet\u00ff\u0093\u00e3#/\u00a1\u0012\u00a5\u00d2\u00ea\u00bc'G\u0017\u00a9}\u00a3\u000e\u0018r\u009a\u0003\u0019yh=Z\u0089y\u0010\u008b3o\u00f9L\u0089\\9uYsI\u0089Bdr\u0018xY\u001f\u009aD\u0083ZF\u00d7\u009e\flr\u00d5O\u0081\u00ec,&\u008c\u00e4Ql\u0090 V{\u0007\u008d\u00a8\u00be:p\u00bb\u00fb\u009f\u00d2d\u00cf\u00bcI\u000e\u00a5\u00be\u0006w\u0085\u009f\u00f8f\u00b2\u0085\u00ab\u00db\u00d8R\u00dc \u00cef\u00ce_c\u00d0\u00a6jv\u0091\u00e9\u00c8\u0016\u001d\u00b6|\u00e3n\u0091\\\u00a6\u00b2\u0010F\u00a1\u00abr\u008d\u00da\u0006(`(\u0018U\u00edG#\u00bch\u00e5`\u0017=\u0011V\u001d\u00e0\u0015\u0082\u00c4;\u00cbO\u00beR=\u0003\u0099 D\u00a8\u009e\u00e8\u00db\n\u00a8\u00a7\u0087}\u00af@\u00a4(\u00e0\f\u00f7\u001e\u0016\u0018\u00cav\u0006t\u00c1\u00ebu\u00ad\u001b\u00be\u00c9\u0094Z5\u00fc\u0094\u00eez+oAp\u00c7\u00d5\u00b05\u00a1t\u0091\u00faS.\u00ad\u0087 \u00d4\u00de\u00a2\u0002Z*C\u00f7\u00b4\u00e3\u00e1z\u0096~\u0089\u0098\u00b6fw\u009do\u00a6\u008d}1CM\u00d3\u0089A%\"(\u001d\u00d8a\u001f(\u00e0\u0004:\u00ff\u00fbL\u0017\u0010p\u00b5\u00ce'\u00dbT\u00ea\u00f8\u00d7\u0007\u00a3\u00da,F\u0003\u009b\u0013\u001e\u001eW\u0015\u009b\r\u00af\u0095C8 '\u00d0\u0092\u008e\u001d\u00ed:\u00f6W?\u00c0\u00e0\u00f9\u0086\u00d2W\u00e0=\u009d\u00a5\u0088\u0090\u00f1\u000e\u0091\u0019\u0012.\u00fcU\u00f6\u0007(\u009b\u008e\t\u0018\u0080D\u0014\u0093\u00e2\u001d\u000bc\u00eaoS\u00bf\u0091\u00bdb\u00119#v\u00cd\u00f6\u00b0\u000e\u00d8\\\u00d3\u00d6\u00a5\u001e8m\u0089z\u00e4*\u00dd($\u00d7y#G\u0084[\u00015\u00eb\u0019\u00aaa\u00de:\u00c0\u00baN\u008eDW\u00c3\u00adR\u0097\u00ac\u001fJ\u00ff\u008c\u00f2\u00df\u008b|\t\u008e\u0010\u00de\u00ddu \u00ac\u00b7v\u00dc\u0098i5\u00ae\u00f7\u0004K\"#\u00d4\u00da\u00af\u00de\u00edsA\u00a1]m=\"\u0012\u0002_\u00e3\u00b6\u00bb2 \u00f9U\u0092\u00e1\b\u008f\u00afx\\'\u00ddoi\u0081\u00b3\u00ef\u0084\u000f\u00a8\u00b3\u0011VX\u00a4\u00b9\u00d8\u009eN\u0088\u009f\u00cf\u009bH\u0006k9\u00b0%\u00e4$d\u0086\u00fal\u00ee5U\u00b2m \u00dc\u0007O\u00b23?\u0095\u00e8dj\u0010\u00b7\u009f'*\u001f\u00d6\u00bdm0\u00ebi\u008c\u00aa\u0086\u0084r\u00f8\u00af,p}\u007f\u00c0A\u008f\u009a\u008f\u0092\u00d7\u007f\u009a\u00bcP\u00d1\u00ca\"\u00aaO\t!eS\u00d3\u00be\u0018\u0089:\u001c\u007f7\u00c8\u001bTF\u00bf #c\u0099\u00c3_:\u00aa\u00cf\u00e8\u008c\u00c68\u00f0 \u00d0,\u00e1+\u00bfx\u0093\u00a8\u00a9\u00d8\u0001\u0018\u001e\u00d49Y\u007f\u00b7\u00f2\u000b Bw9\u008a#\u00e3,\u00a1\u00d3>\u00d1 \u0002\u0084\u0097\u00b7\u0019\u00aa\u00c9\u009c\u00d5,\u00d7H\u00bd\u00ae\u00ad\u0084\u00a8\u0080sSt(\u0085+q\u008c\u0096\u0006\u009e\u00029\u00a7 \u0015\u0087jE\u00b5\u008e\u0002\u00f6R\u00fe\u00eaJ\u0092QF\r\u00f5\u009c[d=+\u00d4\u0085\u00e0\u00f0%\u00e5\u00f3Y\u00fe}\u0018D\u00b2h\u00e4\u0014'e\u0018\u0004yY\u00bd\u00af\u00c0\u00a09[\u00ea \u0002\"\u00a6\u00ecS(\u00f2\u00985 O\u00c6k\u00a8\u000e\u00a7\u00e2i\u0083\u00e3Fd\u00f5G\u00cc\u009eq\u001a\u0094\u0082\r\u00e4rf\u00ad^,\u0006[\u00b5\u00b7\u009f\u00b3t\u0018\u0005(\u0090C\u00bb\u000e\u00ecB\u0014x\u00fc\u0099e\u000f\u00a2\u00d9\u0089|~v\u00b0\u00c3C\u00a2\u0093@\u00d4\u009b\u00e2$\u00e6\u001dO\u0084B\u0084\u00d2\u00fc\u0012r\u00d5\u0000(-\u008cN\u00be\u00f0\u00ce\u0002Z\u0082\u008fK\u00faK\u0095\u000b\u00a9=A\u00aa\u0096q:p\u0093L2d\u00c0=\u00b1|\u00f9\u00a9\u00c4\u0089\u0017\u009er\u009d< X\u00c6br\u009b\u00cc\u00f1\u0000D9b\u00da\u0093\u00f2\u00a2\u00cb\u0018\u007fTi\u00f2\u001d\u00fcq\u00ee\u00ec\u00a6\u00ed\u00bc\u0086\u009d78e^\u00c7U\u00ael\u00eeo\u00a9\u0011\u00f5\u008fB4\u00bf\u001f\u00a1z\u00ae>r\u00efML\u00db\u00db\\\t\u00ab\u00f0\u000b\u0098<)\u001eS\u00ef\u00fd\u00cf\u00abg\u00ac\u00a9l\u00d5L\u001dN\u00d2\u00d4g\t\u00c3lv\u008b(\u00a9U\u00bb]\u00a1+0\u00cb\u0093\u00a3\u00bbh\u001e\u0093\r\u00ae\u007f_p\u0095\u00a4M\u00bet\u0000\u00d0\u001eQo\u008a\u00cf\u008a\u00e2\u00f1\u0090b\u00f2\u00a27\u00bf \u00b2\u00fa\u00bc\u001bx\u009d\u007f\u00a5\u0000\u0093\u00a5\u008c\u00184\b\u0095\u00a8\u00c2T\u00067=\u0015\u00b0\u00d4\u00e6\u00efR\u0087\u0003\u00f0\u000f a_\u00f1\u001b\u00c3\u00d9\u00bd\u0085\n\u0091\u00a4\u0090,\u00a3Y\u0014eZ\u00a5\u00b4\u00fdy\u0090yR\u00c8(\u00ce\u0015y\u0000\u0090 \u00e9\u001f>kw\u00c1T&DY\f\u00de\u00e8\u00c0t\u00a7\u00b1\u00d5-\u00fcsph\u008d\u00efk\u008f\u00b3\u0086#\u000e\u00a2\u0018=\u0086\u0080t\u00bf\u00ba:\u0089\u00f2\u000e\u00d3\u00a9\u00c2\u00c7\u00d9\u009b\u00dd\u001d\u0013^z\u0084\u0090R(\u00fb\u008a\u008a8\u00a7\u00dc\u00a9;\u0083\u00a7\u00c5\u00da\u009f\u0082\u008b\u0085\u00f8X\u001b\u00e2\u0099?\u00d8M#p\u008d\u0093\u00e8\u0014\u00d2f8Y\u00e5\u00f5\u00fc\u00cee\u00bb\u0018/\u00a8F\u00c3\u0080G\u0007\u0098c\f\u00e2\u00e0\u0003\u00c9g\u001e\u0017\u00d4}_n\u00ee\u009c\u00a4 \u008c\u009a\u00a3Ss.\u008fn,\u0093Iq\u0010]Yc\u00b9X5\u008f\u00c7'x\u00c7\u001e\u00ce\u00d3\u0096N\u00db\u0091\u00bd \u0017C\u00af\u0093;H3~\u0090W\u00a1\u00be\"uL\u008c;R\u0092\u009cb\u008a\u00b7d\u0090\u00e3\u00b4M\u00e3\u0092l\u00cf \u0016?~\u00f9?.\u00c9n\u00e7\u00fc\u009c\u00db\fy\u00fc\u00ea\\\u0084\u00f4\u00c5\u00c8c\u0003(\u009d;\u00cb\u0098\u0005\u00cc:\u00e3(\t\u0006\u0098\u00c2\\<\u0088\u0081Q\u00cem\u00f2\u00c2\u0006}\u0019\u007f\u00ed\u009b\u0093\u00f0\u00b7\u0086\u00f1\u00cd\u009b\u00b7\u00af\u00cb\u00c0(\u008f\u00a9\u00d0sY\u00ea\u00a6Mu \u000e\u0087\u00e8\u0018p\u009a9\u00fcI\u00fdog\u00b9W\u00f9\u00bd\u0006\u0094\u0090&\u009a\u001f-Q\u00e1\u00a5\u00ec\u00fcJ\u00b5\u0092\u0081(\u0092\u00f8\u0096\u00ae\u00e7\u00b43\u0095\u00ca\u00a4\u008b\u00e55\u0004\u0094\u00ec\u001f\u00ebQ,\u00d0K\u00af\u00cf\u0018\u00c329^\u00ee\u009a)\u00a7A\u00de\u00f09\u0091\u00b4\u00b2 \u0088u\u00bf\u00cb\u00d8\u0084\u0081#\u00e3\u0085\u007f\u0096\u00dc\u00b7\u00cb\u0086<\u00d8\u00a3\u00de\u0087\u00be4Q\u00ed\u00b4\u00a1\u0004\u0095xC\u0080(\u00c24\u00db\\2pA\u00be<5;\u00d7\u00e7\u0081\u00c4\u009e\u0011]\u0082\u0001B>O3\u0004\u00ca;D,\u00a1\u007f\u00b0$\u00d8+\u0098E\u0091\u00eb\u0095 \u0093\u008b\u0005\t\u00caY\u0002I\u0004\u00dc\u00efY\u00e0\u00afB\u00fe\u00b3\u00e3;\u00f2\u00de\u00e3\u00d4\u00c5\u00e6oh\u0015\u008b\u00abB\u00ab0\u00f9\u0081$F\u00f8\u001c\b\u00aff\u00c7\u00c8\u00f2P\u00e9$\u0014\u0001\u00d7^\u00af\u00e04t\u00d9y\u00eb\u0018\u00b2A\u00e64\u00a2\u00d5\u0015\u009d\u00abk\u0097\u00dc\u008c\u00c6\u00cf\u008f\u0094\u00b5\u0015\u00eb\u001b\u0010l59\u00ebR\u0097j\u0010+\u00db\u008c((\u00fdk\u00b3(\u008a\u00cb\u001e\u00bc\u0097\u0091@mR\u0082\u00df\u00fcb}\u00d8\u0087\u00cd+\u00c4\u0003\u00fa\u00a3\u00d3\u009c1\u00e6\u00d1\u0085\u0015\u00bbc\u001b?\u00c2\u0092\u00d0\u00f8\u0012R-(\u00bc\u000f\u0096\u0094\u00ed\u00d8\u0086\u00e0<\u00ae*\u0094/D=\u0002\u00f8\u00dbM@]\u00cf\u00c0ZR7\u0018,C-\u00df5\u0094\u00d8nl\u00a6\u009e\u00c4\u009b\u0018\u0002\u00b6\u0096g\u00c10\u00bf\u00b2B\u0096YE\u0098\u00eesp\u0016\u00e0~\u008a\u001aK\u0087\u00e3\u0018]\u00e9\u00cb9\u00df[aL'\u008b\u009fr\u008b\u00bd\u00d7\u00ed4\u00a4\u00bc\u001a\u0017l\u0000\u00a6(5\u00a8G.\u008c9K\u00d4\u00db8\u00d7d!\u00fc\u00de\u00daD\u00ed\u000b\u009b\u00f5\u00ba8\u0014\u00c9\u00bd\u00d54E\u0085\u00d6\u0097[\u0091=9\u00ccuS\u008d\u0018~\u00f9\u001b\u00bd\u00fb\u00f4\u00f8\u00a6\u00aam!d\u00da\u00af\\[\u00d1\u00f1\u0004\u00eap\u0087]d\u0010\u00823\u00d8.'\u0083\u001e\u00e6r\u00c5d!\u0083\u008eI\u0094\u0018`\u008a7\u00cdvv{\u009c\u0091\u009e(\u00ae\u00d5\u0004?\u001e(\u00b4p\u00bc\u00fe\u001fD\u00ef C\u00b1\u0089\u0095\u009c\u0013\u00d5M\u00b8\u00f9s]/6\u001e\u00beI\u00a3\r&:3\u00bb\u00ce#\u001fR\u0011@r#3\u0018\u00ca\u00e9D?\u0093\u00a7m\u00eb\u00f1\u00a9\u00eb\u00052`x\u00b0\u0091\u0080A\u00c1*(\u00bc\u00a3@\u00ce'\u00f9o\u00b1\u00f9\u00a0\u009bJ\u00f0YL*\u00a5\u00b6i\u008d\u00ef\u00a9:\u00fe\u00a3'\u0015$\u00aa\u00c7\u00e5\u00f2\u00ce\u000fo\n\u00d8\u00c9\f\u008a+\u00bbXT\u00ddH\u009c\u00f3]\u001f\u0019E&\u00f9\u00e5Y\u00a1\u00e5\u0002\f<\u000fg\u00a3\u00b0\u00f9G 3\u00a2\u00b3\nU\u00ff\u00edx\u00ee\u00cev\u00e8\u00d5\u0010\u00f2\u00a6\u0085+SCT\u00b7\u00c3\u009b\u00c0\u00ba\u001dx\u0000\u00ff-\u009e\u0018\u0099\u0081\u00146\u00cd\u00fb\u00940\u00e8\u00eb\u009a\u00cfg\u00c4\u0084\u0002\u00abo\u008e\u00ee\u0002\u00a4\u0017\u001c !\u00c7\u0090\u0096U\u009a\u0018$\u00a3#\u001a\u0084,\u00ccc\u0007\u00e0,\t\u0016\u00b6\u00bbWq\u00d2\u009b\u00c9D\u00dfm\u00ce\u0084(\u00d3\u00be\u00a4r\u00ce_IV\u00af\u00be4\u00f8P\u0013:\u00ae\u00aa^\u00af\u00c0\u008az\u00e0\u00e5O\u00de\u00f8\u00a6T\u0010}CPX'\u0083\u0093\u00c7\u0098L\u0010\u0007;\u00f4\u00e4\u008f9t<\r\u001a;\u00d4\u00f0tY\u00d4\u0018q\u001c\u00e6D\u00d3.\u00f2\u0010\u00c8^\u00d4\f\u009dw\u00b5/J\u0087\u0082\u00c0\u00b0\u00f2\u000b\u00f6(\u00cf\u00b4T\f\u00a6\u00b9/l(\u00a5\u000b\u00d9\u00f2\u00e6\u008a\u00bf\u00e2\u009c\u0003\u001eu\u00c3\u00aa\u00ef9\u0012\u00dd\u00fb\u008f\u00f7\u00c3\u00e8\u00b6J{w\u00a83\u00b9M\u0010\u00b2\u0090\u00e7\u00d0F\u00b5\u00b4\u00e36\u00b3\u00a2\u0000\u000b\n\u00c4\u00cd(\u0080\u00af\u009d\u0087\u00d7\b\u00d1\u00abD/\u0011\u00ac)Fk\u00fd\u00e9\u0095h\b,\u00fd\u00a5.[$\u00e0\u0010\u00f6\u00c2\u009e\u00bdT\u00f9\u009e\u001b\u00f6\u00d2\u00d7\u00db \u00af\u0015a\u008f\u00c7\u00ba\u0080UX\u0018\u008a*8\u000b\u00f9\u00be\u00ec\u00fc\u0080\u0014/\u00b9\u00a9\u000f\u0090(\u0002\u0004P\u00e0\u008aT\u0010\u00c3|\u001do\u00ad\u009f&\u00c2\u00a9\u00ad\f}KO{A \u00abV\u00f8\u008d\u00b7\u00c9\u00da;\u00e5a1<k\nG\u00e8\u00e567\u00bclM\fo\u00a0\u00eb\u00cd\u00c3Qe\u00d8X  \u0011;\u00b2\u001e\u00f73oS\u00a9\u00e7\u00a0\u0083\u00aeYs\u00ea\u00b7>UIN\u00fe\u00deW\u009f\u00f4\u0013\u00c0z\u00d8@ \u00ab.\u001e,D:ZX\u00a0\u00a4h\u0088\rW\u00c0y\u00b4rQ\u00e5_\u0085B\b\u001e \u00ff/F\u009e:\u00f4\u0018\u00c5\u00fc\u00ea\u009b\u0004\u009aA\u00b5\u00b9\u008f=\u009f\u008c\u009a\u00f6\u00e6\u001dP\u00ae\u00c1s\u00a9\u0000\u00c2(\u00d8\u0095\u00f6hP\u0013Q\u0016\u00b9`\u00a5\u00a04+\u00b89\u00bbq\u0085\u00f2\u0093\u00b9{\u00d7i,\u001a\u0093\u00f2\\\b\u008e\u00aa?\u00fa^\u00b6C^\u00c5(\u0018\u00f0NAL\u00b0\u00ec\u00d6\u0013D\u0096ao\u0011\u00920\u00a5\u00fd\u00fb\u0087Rm&\u00b3\u00f0\u00d6\u00d2\u009f)\u00f3Oyq\u0003)'\u00b0\u00a6e. ^\u00ac\u00a67;\u00be\u00c5\u00cf\u009f\u00d1\u00c6\u00b8\u009f\u00ced\u00ba\u0002\u00843\u00e5N\u00cd^\u00bfU\u0003\u001f\u00e1\u00f9\u00f1p\u00c4(\u008f[\nC\u0094\u00a5\u0015@G\u00b2\u00a9\u00f1\u00d6\u0019\u0093y\b\u00ac\u0084\u0017(\u00fb\u00a1\u00c9zIu\u0092U\u00d5\u00df\u00f8\u0007RhS\u00e7'\u00d2M \u00a6.N\u007f1\u0093ZM\u00e78\u00a4|\u00b6\u00fe\u00a26sH\u0014m\u00a9\u0018\u00a0\u0081\u00dd2.^\u00b9\"H\u0081(\u00e8\u0091\u008fsJ\u00048:=\u008a\u00a8x7\u00f9\u00ba\f\u0014\u00a5\u00b6\u00fb\u00e2\u0007\u008b\u00f8i\u00f1\u00c8\u00df\f\u00a7$m\u00f9\u00b03l\\\u0089P\u00f7(\u0085\u00c9R\u00e6\u00b6\u00d4R\u00e9\u0007\u0000\u00b5\u0002\u0007J\u00e0\u0096K|\u0087\r\u0086P\u00fdlS\u0082\u0099\f\u009b&\u00d1D\u0089\u00a6\u0091R\u00f8\u00b5A`\u0010!\\\u00bd\u0001i!\u0094\u00b3\u0093\u00b7&#\u00a2\u00c3\u00d6\u00ff \u0018\u00d2w\u00cc\u0080\u00bc\u0016\u00b4p\u00cdG\u00cc#vA\u0001n\u00fa*\u00e6\u0012\u00e7\u0019Z\u00b3\u00d2\u00f1\u009f\u008e\u00af\u00d4\u00d9 \u00e99u\u00e5\u00ceD\u001d\u00a7\u00b6\u0090g\u00a0\u00fdqlg\u00b5\u00f7\u00ae9\u001f\u00d39,\u00906xT\u00d8@d\u001e({\u00c8P\u00de\u00c6\u00eet\u0006=/~ \u00e0n\u00d3%\u00f8#\u0080\u0085\u00fb\u009aOPn\u00fb\u00a7\u00acH\u00b3$\u0097V?\u00db\u008c\u00b1\u00b6\u00c7\u00c8 \u00ee\"K\u00bb\u00aa\u00e5\u00b3\u0018\u00bcAf\u00dd\u009cD:\u00b2\u00cb\u00a8#\u001e\u00e9H^_\u0090\u000b\u00ae\u0094\u00fa\u0019\u00bd\u00de\u00187X?\u008c\u00beZM\u00afoyP\u00a3\u0010\u00ae\u00e5o\u0088\u009c^\u00b5\u00b2\u00a7\u00a4@\u0018\u009d=\u00ca&\u00da\u00e6j\u0096\u00c0\u0013\u00a9\u00e0\u008b\u00d7\u00d1\u009d\u00e3\u00ef1\u00da\u00c4*w\u00e2(I#p\u001dai\u0091P\u0085\u00e4l\u0012\u00a6\u0011\u00f6\u0018\u00ee\u00f7\u00be\u00be\u00fb\u001f\u000f\u00a5\u0000\u00bdfz\u009a/m^\u00e8T\u008c\u0000\u009dLd\u00fa \u009a\u00dcsB\u0081\u0086\u00cap\u0089\u00b4dK%\u0006\u00da\u00bf\u0095\u00c1\u00e0\u00f4S\u00a3\u00fa\u00e5Z\u00e7\u00aaVt\u0093\u0000\u00e8 \u0084\u00fd\u0006\u00ee`\u00b0\u00dc\u008dTC\u009c\u00f4\u00b3:U\u001c\u00f5\u00e4\u00c3\u0010\u000f\f5#\u00e4\u00b1\u00ab\u0016\u0019\u0086c\u0095\u0018)\u000f\u0090\u00b3C\u00a4\u0088\u00d1\u008b\u0093\u0019.\"\u00c4,\u00b9\u00f06P\u00f8\u00a8uu\u00df(X\r\u0000c<t\u00be\u0003\u00bb\u00c3!o\u00d1\u00cc\u00d56\u00a5\u00e7<S<\u00fc\u0001\u00d7\u00f0\u00c9\u000e\u0012\u00e5\u00a4o\u00adp\u00bc\u000b\u00f0\u00b4\u00c1J\u00ed\u0018\u00ca\u0018(\u009d\u0086)\u008a\u00d8%\u0015\u00a6\u00b1f5\u001d\u00894\u009cR\u00a6m\u00a2vi V\u0091\u008c\u00dd\u00af\u00d7\u00d5\u0087\u000b.v\u00e6\u0002GO\f\u00d9h\u00e9\u00c1q\u00a7\u0082\u00d7\u00a7(\u0003il`=\u00a8\u0018\u008d\u009b\u00c05\u00ff\u00c5v0\r\u0005.\u00f2ez\u00ad\u001fz\u0010\u00c1+H\u0088\u00db(\u00187\u00e4\u008fa)\u0082\t\u00e43\u009e\u0012e\u00d1q\u00a0\u00e7\u00b9\u00dc<9\u0096\u0002\u0081y \u001b)\u00fcL\u00a4\u001d\u0085\u00aa%p\u009d#mxLF\u0001j\u00f92\u00f4|\u00f5e\u0095\u00aa\u00a2\u00ea\u00e1f\u00d8\u00b7(\r/z\u00f2\u00bfq_rD\u00d5^x\"\u00d6E\u001d\u00cd%\u0016&\u00e4\u0017\u001c\u00bf\u0002\u0088c\u00bf\u00c8:Za\u009fU\u00c5Cg\u00c0\u0002\u00af bkO\u0016\u00cb,\u0095|\u00be4\u00e6U\u00d8\u0094b\u0002\u00951|\u00beB_-\u00852M\u00fe\u008b\u0017Q\u00bb\u00e0 2I7\u0091\u008b\u00ebIj\u0089\u00d4`*\u00c8\u00a5\u00b7\u00b2\u00a8\u00e9\u009c`sz\u00f3\u0092\u00fe\u00e64\u00a2\u00a3a\u0001/ e\u00b5\u00b0,T(\u00d2w\u0019 j\u00d3,i2\u0080\t\u00cax\u00ff0\u00ffAs\u008e\u00b9xW\u009e\u008b\u00a6\u00180\u00ce\u009d\u00d6S\u0002G\u00e79\u009c\u00d3;\u00b0T{P\u009c\u00fc\u00fcwU\u00ba.`\u00a8\u00e5\u00dc^\u00c3\u00e1\u00ea\u0087\u00fb(}7*\u00fa\u00e6T1Wx\u00ed2\u001d\u00be2\u00ea\u0010,#\u00bfcB\u0081\u0086rb\u00d1\u00a4e\u008f\u00d4r]\u0018adY\u00d3\u00e4\u0084\u00d0\u00a7B\u00e1\u00f0\u00a5\u00e5D\u0000\u00ecP\u0098\u00da\u009d\b\u0011\u00f9J(T*\u00fc]R\u008e\u00f0[\u00ce\f\u0098\u00b7\u00f5\u0006\u001f\u00f1d\u00f1\u0099\u0084;\u0011\u008d\u00c2\"l\u00c6\u0087\f\u00da\u0017\u0018\u001b\u0012\u00a9;\u009b:\u00eb\u000e(\u001f\u0018\u00c4\u00d0\u0093pK\u008d\u00f9\u000e\u00ed\u00a5\u0092\u008c\u001d\u00cf\u00ff\u00a9\u00fb\u00e9+%\u00e1\u00e3r\u008f^y\u00c0M\u00d1c\u00e6\u0098\u00e3'\u0010\u00df\u00a40 \u00e9\u00cb`\\G\u00c5`p\u009dq\u00f6\f\u0017L]1i\u00cb\u00ad\u00cf\u0096\u00b8\u00d1\b\u0084=\u001be\u0085\u00ad\u00fel(v\u00c0\u00d3]\u001553}x\u0016o\u00e6\u001b\u00a7\u00a7\u00850D\u0091\u00e9a\u00f5Z\u00c4\u00f1h\u00ecgQW\u008e\u00a8\u001d+\u00acR\u00e3\u00ea\u0006L X\u00fe.\u00d1\u00be\u00e3)\u00b7\u00c2\u00c7\u008e\u00fer\u00aa\u0090d\u009e\u00e4\u00e7j1\u00a6\u00f4Pw\u00c5J?\u00877*\u00d3(\nz\u00b0\u00d3\u00ae\u00ae\u00d0\u00e69.\u00f3\u00d7\u00e7!%\u00bf\u0095Y1\u00d5p\u0080e'\u00a1!\u00e5\u0016\u00e54W\u00c2\u009e\u00b7\u00ed~\u0004\u0016\u00e2\u00de(\u00b0\u00c4^O\u008dF\u0095HV\u0097\u008d\u00b4G\u00d0\u009a\u0082$\u00b5\u00c3\u008a\u00f1X\u00dc\u008f\u00f1c\u0007=\u00af&\u0001\u00ac'\u00b6\u00ab\u00d2\u00e3\u0084C\u0018\u0018\u00fd\u00f1j\u00b1)\u009d\u00dd\u00d6)7\u00fc\u0010\u008ck`d\u007f\u00ecX\u009c\u0006\u00ce\f\u00a8\u0010\u00fa\u00feAU\u00ee\u00bc\u008c\u0013\u000b\u0094\u00fd6\u00c8pY\u0004 \u000eA@\u0018\u0092sP\u00b0\u00ed[\u009f%m)-\u0096G\u00bf\u00ec\u0012\u0083#)lV\u00ea\u00bb\u0099\u00d1\u00e1\u00aa\u00f4 G\u00bc\u008bo\r\u00cabN\u00ca\u0016\u0091\u001e\u00e3/\u0097(4\u007f\u0089\u00b7\u00da\u0084\u00f7\u00f1\u009f\u001c5t\u00aa\u00f1\u0014N(\u008e\u0093\u00b0\u0088\u0003x(\u00d9\u00c3\u0084\u0012\"\u00b5 \u0099\u00ee:\u0086\u00a6K\u008d\u008d?*u\u008b/\u00aa\u00e1\u008c\u00f0\u009d3\u00e8e@\u00a7\u00c5\u00b6\u001f(\u00ef^\u00b9\u0002J\u00f6<\u0012\b\u00c0\u009d\u0016{T)\u00e7O\u00ec\u0014\u00edw6>\u00d6\n%S\u0019rl~\u00d0\u00a3|\u00f0\u00f5\u00137*\u00b3 N\u00c7\u0004h&\u00bb\u0012\u0012\u0006\u00e3\u00dd~h&~\\\u00a3m\u00f6\u00da\u00da\u00b6\u00bc\u00dd\u00c3\u00f3q\u00c2\u00c3\u0086\u00fe\u008d \u001f\u00f5@\u00b3\u00bb\u0014\u00dd\u008a\u00b6H\u00f2\b3\u00bb\u0085\u00982\u00b1\u0000\u00163I\u00fd\u0089a\u00e8\u00d4\u00c4}\u0097\u001e\u00f5 z\fOuJ\u00a4\u00e5\u00a4\u009fs\u00c9\u00adX\u00e4\u00b5-\u0096\u00bexj\u00bfp\u00b1x\u00f5^\u0019Z\u00c6b\u0098\u00a8(\u0000l\u0000\u00e5\u00cb\\u`o\u00a5\u00a8Y.\u009a\u0017\u00ff\u001e\u00a5\u0002G\u008dE\u00c3q$E\u0096\u00d6x\u00ed\tXr\u00d1\u00e6\u00bd^)\u00ecO8\u00af\u001fy>:\u00c0,\u00efKa\u00b3\u00ca\u00f80~\u0085^\u00a4\u0092\u00f3\u00e6\u00df\t\u001b\u001b\t\u00051\u0090\u008f\u00bflSx'a\u009f\u00ff h\u0018\u0001{b\u008e#\u0096\u0099\u00a5\u00edL\u0090\u00d132\u0091 \u008bs\u001d\u008d%\u000b\u00f3\u00d3\u009f\u00bfF\u0088H\u00e3\u001bNA\u00b8\u00ba\u00bf\u000f\u0080\u0014k\u0089\u00a9\tEJN\u00ec\u0085\u0018>\u00dd\u00b2\u008fo\u0081\u00aa\u00e3\u00f4\u00e4\u00cb\u00d5F\u00c7N\u00e5`\u001c\u00fd\u00d2\u00abJ\u00ab\\8\u00f7*\u0083\u009b\u00ba\u0011A\u001b\u0012\u00cd\u00dc63*\u00b9B\u007fU\u00f3/B`\u0006W\u00a0\u0082\u0094\u0017\u00ab&\u0091]\u00feX\u0004)d\u00f8\u00a8oD\u000b8\u001c.\u0005\u00f6k\u0080%\u00d0\u0080\u00f0j\u008c\u00c60\u0097\u00d9\u00c9\u00db\u0091tl\u00c80\u00c1\u00aa\"\u0011\u000em\u00d7\u00fb\u001d\u00b2\r\u00c5\u0099\u00e0C\u00f9\u00d4\u009d\u008b\u00a5\u00e1B\rc\u00a8\u00bb\u00cc\u0014\u008d\u0082[\u00a2\u00a2\u0082\u0098\u00e1\u00e1u_0y\u00baB{\u00e7W\u0080p\u00e1\u00dd<\u00d8\u00b8\u00d0Fa@\u0090\u0080\u00a2$\u00fbQM\u00b3g\u0006\u00d1\u00026h\u0000\u00f7\u00d1~(\u00e7R\u00fe\u00ed!\u00f2 \u00bfLH\u008c\u0015(\u00a4m\u00f31T\u00cew\u00e1MCp|\u00e2\u00a7\t\u00dc\u001bE\u00ce\u00a3\u0015\u0084f\u00ef}N0\u0003\u008d\u0013\u008a\u0001\u00e79\u00ccG[\u00a0dp\u0018\u0090\u00a9fI\u0019\u0098):\u0081\u00af6\u00d1\u00ac\u00ads.\u00b1x\u00f3\u0094\u00d1w\u00b8+\u0018l\u00fd\u00e7\u00c4\u00e0X\u00a1\u0086D\u0085\u00f1^\u00e9\u0081Ri\u008el\u0004\u0015\u0095O\u0095A\u0010\u001e.\u00e6`\u00b7\u0013&\u00b6\u00f5+F7\u00a5\u0093\f\u00fb \u00943k\u009d,\u0007\u0096\u00184\u0098\"\u0093\u001d\u00d68OF\u00da\u0084\u00d8\u001c\u00fd\u00b2\u00a0%\u000b\u00b0\u00dc\u00af\u00f8\u0091\u001f \u00df\u00d61\u00f4\"\u00d1{\u001fG\u0085\u0014\u00c5\u00b2\u009a\u00b8\u009a\u00c8\u001f\u00fe\u00b8\u00dc\u00f5xi\u00b3\u0096\u0081t\u00f9\u00ee\u0014*\u0018\u0010\u00fe\u009d\u00a1^J\u0089x\u00e72\u0001\u0001\u00f5+\u0085$\u00a3iUP\u00de\u0001\u008d\\ \u00a9$k\u00b7{\u001eGE\u00f4\u00d0\u0019>\u0006:<\u00009\ta\u00c1\u00ff\u00e3\u0004w\u0012-\u00a6\u000b2\u00da\u00f6\u00be v\u00cb\u00f2\u00b5&\u0081n1\u0015f%\u001c\u001a7\u00d7\u0017W\u00d0:\u00f1\u0018r\u0089\u00e5\u00afA\u0003\u00ad\u00a0e\u0003F\u0018\u00c2\u00933\u00a1\u00bd\u00bfAI|\u00fc\u00f9[\u00aa\u00ae\u009e\u00fb\u00b8\u0088\u0093\u001cQ\r\u00e1` l\u00f4\u0093\u0014\u00b3\u001a\u00b4\u00c8\u008e\u00ce<U\u009b\n6q\u00c3\u00ff\u00a8j\u00acH\u0085\u00e1\u00ed9\u00bd\u0002\u0093\u0003O$ \u009fP\u00fa\u00e65,_/\u009d\u00b0\u00f9B^\u00c8\u00d9\u0014Opp\u008e\u00e3\u00b9B+\u0087\u0089\u00c7\u00c9\u00b6!\u008e60\u00ee\u0093\u00f3\u000eO*\u0091\u00d5F\u001f\u00db\u00d7\u0097\u000e\u00a1M\u0006\u00b6\u0099\u00de\u00e5\u00f6@D\u00bb\u000e\u0094\u00f8\u00d7Gb2\u00cbJ\u00ef\u009e\u0086\u00c0~\u009c0s\u0092\u008b{\u00d0A\u00c78qC]k&\u0098\u001c\u00e0 \u00ce\u00bcP \u0084\u00be\u00a2\u00a3\u00de\u00d9\u009e\t\u001dQz\u00f0\u0081\u0004}\u00f1-\u0007D\u00b8\u00ad\u00ec\u0010^a\u00c9\u00a4\b\u00f2\u009dat\u00e0^u\u008d\u00ec\u009b\u00a5\u009f]1\u0082\u0018\u00c0\u0014\u00e4\u0006\u00e5>z\u00b7r\u00d6\u008b\u009aK}\u00b5C2\u0094$\u0000\u00df\u0080<\u0093\u0010\u0014e\u0007\u00dab%\u0013\u0014\u00d9``r!\u0087\u00ccw\u0018\u0006\u00d1P\u00db\u008a\u0018\u00c3;.n\u00a6\u0098L\u00ba\u008c\u0018\u00fe\u0086\u00f0\u0087\u00ed}\u00d9s0\u008ef\u001f\u00fe\u00e0e\u00c8\u00f0\u00c4\u0007SP\u00ab\"6\u0082M\u00af5\u00a9\u00d4y\u00cd\u00c5\u00f8\u0005\u00e2\u00d1\u0004\u00abH\u00e6h_\u00a4\u00f8\u007f\u00f2U\u00c5l\u00aa[\u00df\u00e3\u008b\u0003\u00e9";
                                var28_6 = 8954;
                                var25_7 = 32;
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
                                    var29_3[var27_4++] = AutoCrystal.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00b8a,w\u0086\u008c$\u008e\u00eeC\u00a2z--\u00b6\u00ad \u00b1\u0002\u00e8\u00f2O\u00ba\u008fv,a\u00ec\u00c1\u009b2\u0014\u009e\u00c8\u008ay\u009e\u00da\u00f7\u00d7#B\u00fc\u00f5l\u0087am\u0002";
                                    var28_6 = 49;
                                    var25_7 = 16;
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
                                    var29_3[var27_4++] = AutoCrystal.zkmDecodeUtf8(var30_9).intern();
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
                        AutoCrystal.fb = var29_3;
                        AutoCrystal.gb = new String[261];
                        AutoCrystal.nb = new HashMap<K, V>(13);
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
                        var17_12 = new long[148];
                        var14_13 = 0;
                        var15_14 = "\u00f1\u00e3\u00a2\u0001I\u0097\u00ba\u00af\u001f\u00c9\u0000\u00e2\u00b3\u00a8\u00d6=i\u00c5\u00f7j\u0092\u00f4\u0012\u001b\u001c\u0096N\u00e1d\u00aa'\u00b8\u0014%_\u00f6o\u00bf\u00d4\bKm\u00e0\u00d5\u0003\u00cb\u0004\u00d0\u00c7\u008b\u008e\u00c5A\u00ec\u009b0\u009dL\u00cc\u009ed\u00c9\t\u001fsp\u008e\u008a\u0094\u00ac\u00ec\u008c\u00c1wz\u00fe\u007f\u001b\u001b\u00dc\u00ae\u00e5\u007f\u00d3\u00a5\u00ab\u00be\u0006Q\u000e\u00c8@\u0080\\\u00feK\u00a66t\u00f4\u009bu\u00ea\u008d\u00a3:\u00b2\u00fd!LJ]G\u00a2\u00aa\fW&\u00ba\u00e1\u0011\u0091q\u00b9\u00f1\u009d\u00b2\u00cb5k.\u00eeK\u00c5\u001e).\u0015mD\u00e5\u001d#7y\u0016\u00a3\u0012\u00af\u00c9\u00884\u0017Z\u00fb\u0081\u00ba\u00ca\u0092\u00fa\u00d3\u00f8O\u00c7\u00ba\u00bc\u00fb\u00af\u008e\u0085\u009d=\u00bd\u001c;\u00f7\u0083\u0086tA\u00cb4\u009d(\u008f\u00ad\u00e8\u008bv\u001a\u001d:<\u0004\u0011\u0005\u00b2rC#\u0005I\u00aa\u00f5%\u00c2\fj\u0005\u00071\u000e\u00de\u0098q5\u00d0o.[\u000b\u00ab\u00c5\u00ce\u00b3<\u00d5n\u00c7e\u0096fJz,\u00da4\u00b0^\u00b6v\u00f4d\u00d3\u00a7\u00f7M\u00e4\u00b8g\u00d3\u00e0\u00da3\u000f{\u00be|\u00b6@\u00f4Kn\u001c\\\u0014E\u00a8z%\u0086w\u00d3[+\u0011\u001c\u00ad\u00ff(\u00b0\u0094\u0001\u00b4\u00dc\u00c6\u009bp\u008a\u0084\u00ed\u00971\u008cXD\u00aa\u00aa@\u0002\u00ce\u0012v<\u0001I\u00ab\u00ddn~[L2\u0003\r,%\u00f5.\r\u00f20\u00a49u\u009eL6s\u0086\u008e=\u00ee\u00acb\u00f8\u00e2\u000b\rXQ\u0019\b\u009ci\u00b3}\u00b5\u00b1\u00ff\n\u0086\u00cbE}$\u00ef\u0093cjp\u0092\u009d2US\u0012\u00bbj}\u00b1AO\u0003a\u007f90:\u0007'\u00cc\f\u00e3\u008a\u00c5\\\u0017\u00d3I\u00a9\u00f4E\u0091#\u00e6\t\u00a6\u00f4\u00ad!\u00f8\u00d25%y\u00a9\u00bc\u009b\b\u00b9\u00d6 {i\u00d1f\u001c\u00ca\u00e7\u0005D\u000f\u00b9d\u00b9\u0011\u00c6\u0006\u0012\u00ed\u00b4H\u00e6\u00aaY\u0087Bn\u009b\u0080\u001eo\u00d1\u00a4\r\u00c9l\u009d\u00cc\u00f6\u008a\u00f7\u00b6(U\u00dd\u00c2\u00d6\u00a6\u000f\u008b\u000bEa=\u00cbK\u00a2\u009c\u00a4=\u00cazk\u00b4\u0082\u0013\u00f8!\u00d1\u00cb\u0003\u001f(\u00fag\u00a7\u0084\u009f\u00c7y\u0019\u00e3#%Fe\u009d\u00c7\u00b4\u0019\u0097\u00b35\u00b0\u00cc\u00c2N\u00e8\u00ca\u00f3\u00cc\u007fl,\u00e3\u0011!B\u00ee\u009b\u009b}v\u001e\u0084\u00d4PBr\u00ad\u00c8\u00ae\r(\u00de\u00f6\u0010\u00ef\u00d7\u00d8(BR\u00de^\u00dc/\u00d4\u0096\u00ab\u00d9\u00f0'\u00fa\u00c7c\u00b26\u008a\u00fdj\u00b2\u0082\u00d0.\u001f!V\u00ac\u0007Du\u00f1\u00cb\u0082\u00b9\u00a3\u00b5\u00831\u00b6\u008f\u00b2\u008c\u00c9\u00d3(\u00fd\u0012\u0012\u00ad\u00d4\u0096I\u00f2O\u00d9\u00bc\u00b4U\u0080\u008c(\u00b8zUbu\u0096\u00f0\u00ed\u00f6ab\u00f9\u00f3\u00cf\u00b7\u0014S\u0085\u00eb\u00fdm}\u0088\u00a0*\u0081\u001d\u00cc\u00a2/\u0096\u00e0q@u\u00f0\u0085#7\u00bdv\u00f7\u0017\u00aa/\u0010H\u00a8\u00f5B%\b\u00b9\f\u00fet\u0086\u00a1\u00da\u00b8\u00e0\u00fc\u009e\u0011\u0001Q8A\u00ca\u00ae\u009d%\u00dau(\u00d7\u0099\u00b36S\u00ea\u0098\u00b66\u00da-\u0085\u007f(\u0003 XF\u0010\u0080\u0007\"p\u001c<\u008a\u00ee\u00e5;\u0090E.\u00c9EJ\u00a7\u00ca\u009e\u0091K\u00ff)\u00be\u00ffY\u0000\u0006n\u00e5\u00a9\u00b9\u0099#{\u001a\u0089\u009b\u00ec\u008b@t\u00a5\u0018\u00c6\u00c6\u0018\u00d0\u001b\u00d2\u00ee\u00c6\u0013S|\u0017\u00a7\u00c6\u00f1N\r\u0097\u00f3?\u001d\u00b5\u001fk{-\u00fe\u00cea\u0086\u0083tL_c\u008a\u0082^\u00e9\u0002\u0001\u00f3x\u0014 K\u00d8\u00e0T\u00d7G\u00e6Q\u000f\u00e8;\u00bb\u0092\u00913\u00cf\u00d4\u0082\bQZ\u0084Q\u0093_\u00e39\u0004\u00fd\u0098\u000b@5\u00dc\u00dapQ\u00cc|\u00ce\u00c2\u0005\u00c4\u0011\r\u00f3\u00dc\u000f/\u00ae\u00ccP\u008ae\u00b8\u00a0r>\u00cc\u00c9\u00f7nq%\u0007\u001e\u0011.m\u00c57&v\u00da9\u00ac\u0081\u00f8a1$\u00a5\u0007\u001f\u00b1\u00cco\u00ca>\u000f\u00e9\u00d7v3n\u0089\u00c9\u00ee\tI\u0090T'2B\u00a8J\u008f\u00ff\u00ef\u00a6\u0002p\u00d4\u00bf\b\n\u00a9\u00dc\u00fd&\u0015t^\u00a4d\u0087)N\u00b61\u008c\u00a7\u00e2\u0002\u00c7\u0087\u009c\u001b\u0095&+\u00cf\\\u00bftA\u00b5\u00f6'h\u00ad\u00c1\u00d4NKS\u00bcp1\u00ffa\u00f9\u00fe@N,\u0086\u00f4@`6T\u00e9\u00a1^I\u00ddU\u00d81L\u0017\u00c1\u0005\u0083\u0002pD\u00fe1\u00150\u00e4dt2\u00c30|*~c'\u00ee\u008doNrJw\u00b0\u0096\u007f<j\u00fb\u008c\u00d8\n\u0082\u00d4\u00a2\u00c1\u00d3\nb\u00db\u00cd\u00fb\u0086\u00e8Ey\u00d2%\u00be\b\u00d2\u00e3z{\u00bf\u00d1\u0088dD\u00b4]\u009aU\u0088\u00c3\u00f5\u00a8]Z\u00c3\u00c1^Q \u0092\u00f48\u00ff\u008d\u008f\u00ee\u00fd\u00b1o\u00ae\u00b3D\u000b\u00d1\u00d7\u00d4g\nO\u0096\\\u008e\u00dbU\u00da\u00e1\u0014>\u0018:\u0095\u00d8O*\u00e16\u00d3\u00f4\u007f@\u00d9+\u00a5\u00e7\u00b7\u00d5\u00b8B-\u00a2f-\u00bar\u0006\u00dd`\u00d0\u00b2\u00b0\u00be$@\u0010T<\u00f8\u00b2\u0003\u00d5\u0082\u00b9{\u00dc\u001a\u00ff\u00b3\u0094b\u00d3\u009e>\u00ba\u00f0\u00dd\u0017\u00a2C6b\u00da\u00d8\u0092=\u001f\u00acrk\u009c@A\u009a\u001dG'\u00ba\u00ab\u008d;\u00f1F\u008a\u00c5\u00c7\u00bc\u00d4\u00fc";
                        var16_15 = 1168;
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
                            var15_14 = "\u0003\u0083\u008b\u00f9\u00dbC\u0010\u00d7S\u00e4\u00ce\u0019\u009b q\u00d9";
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
                AutoCrystal.lb = var17_12;
                AutoCrystal.mb = new Integer[148];
                AutoCrystal.qb = new HashMap<K, V>(13);
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
                var4_24 = "\u00ae\u008a\u001f\u00bf\u00b5\u00ce\u00be\u008b\u0083\u0086\u00d18\u00faC\u00e9=";
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
        AutoCrystal.ob = var6_22;
        AutoCrystal.pb = new Long[2];
        var76_30 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "mainGroup", "getMainGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "timing", "getTiming()Lsu/catlean/y;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "multiAction", "getMultiAction()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "onCrystalSpawn", "getOnCrystalSpawn()Lsu/catlean/t9;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "spawnTiming", "getSpawnTiming()Lsu/catlean/n_;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "rotate", "getRotate()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "rayTrace", "getRayTrace()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "crystalMultiPoint", "getCrystalMultiPoint()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "asyncCalc", "getAsyncCalc()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "failNotify", "getFailNotify()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "predict", "getPredict()Lsu/catlean/fy;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "predictTicksValue", "getPredictTicksValue()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "placeGroup", "getPlaceGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "oldVer", "getOldVer()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "placeDelay", "getPlaceDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "placeRange", "getPlaceRange()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "limitPlace", "getLimitPlace()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "removeFire", "getRemoveFire()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "removeLava", "getRemoveLava()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "breakGroup", "getBreakGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "breakDelay", "getBreakDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "grimBreakRange", "getGrimBreakRange()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "explodeRange", "getExplodeRange()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "limitBreak", "getLimitBreak()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "randomizeDelay", "getRandomizeDelay()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "pauseGroup", "getPauseGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "mining", "getMining()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "eating", "getEating()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "inventory", "getInventory()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "switchPause", "getSwitchPause()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "offHandPause", "getOffHandPause()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "damagesGroup", "getDamagesGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "assumeBestArmor", "getAssumeBestArmor()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "minDamage", "getMinDamage()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "maxSelfDamage", "getMaxSelfDamage()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "efficiencyFactor", "getEfficiencyFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "lethalMultiplier", "getLethalMultiplier()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "overrideSelfDamage", "getOverrideSelfDamage()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "overrideMinDamage", "getOverrideMinDamage()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "armorScale", "getArmorScale()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "facePlaceHp", "getFacePlaceHp()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "explodeEChestFarm", "getExplodeEChestFarm()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "antiPortal", "getAntiPortal()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "antiQuiver", "getAntiQuiver()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "terrainIgnore", "getTerrainIgnore()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "friendProtection", "getFriendProtection()Lsu/catlean/sv;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "calcShields", "getCalcShields()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "damageSync", "getDamageSync()Lsu/catlean/zz;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "facePlaceSync", "getFacePlaceSync()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "lowCrystals", "getLowCrystals()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "stacksLeft", "getStacksLeft()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "minimizeSelfDamage", "getMinimizeSelfDamage()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "maxDamageReduction", "getMaxDamageReduction()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "switchGroup", "getSwitchGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "gappleSwitch", "getGappleSwitch()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "autoSwitch", "getAutoSwitch()Lsu/catlean/x0;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "antiWeakness", "getAntiWeakness()Lsu/catlean/x0;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "idPredictGroup", "getIdPredictGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "idPredict", "getIdPredict()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "idOffset", "getIdOffset()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "idAttacks", "getIdAttacks()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "idDelay", "getIdDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "idPredictOnlyDuels", "getIdPredictOnlyDuels()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "swingOnce", "getSwingOnce()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "onlyBreakingSurr", "getOnlyBreakingSurr()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "targeting", "getTargeting()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "targetSort", "getTargetSort()Lsu/catlean/w2;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "ignoreCreative", "getIgnoreCreative()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "ignoreNaked", "getIgnoreNaked()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "renderGroup", "getRenderGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "fakeCrystalRemove", "getFakeCrystalRemove()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "removeDelay", "getRemoveDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "targetEsp", "getTargetEsp()Lsu/catlean/s9;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "renderMode", "getRenderMode()Lsu/catlean/s0;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "textPattern", "getTextPattern()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(AutoCrystal.class, "alpha", "getAlpha()F", 0))};
        AutoCrystal.delegatedProperties = var76_30;
        AutoCrystal.INSTANCE = new AutoCrystal();
        AutoCrystal.mainGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "main", null, null, 6, null);
        AutoCrystal.timing$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "timing", (Enum)AutoCrystalTimingMode.VANILLA, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.multiAction$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "one-tick-action", true, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.onCrystalSpawn$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "on-crystal-spawn", (Enum)AutoCrystalOnCrystalSpawnMode.ACTION_X, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.spawnTiming$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "spawn-timing", (Enum)AutoCrystalSpawnTimingMode.ADD_TO_WORLD, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "rotate", true, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.rayTrace$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "ray-trace", false, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.crystalMultiPoint$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "crystal-multi-point", false, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.asyncCalc$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "async-calc", false, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.failNotify = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "fail-notify", false, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        AutoCrystal.predict$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "predict", (Enum)AutoCrystalPredictMode.AUTO, AutoCrystal.INSTANCE.getMainGroup(), null, 8, null);
        var77_31 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)();
        var78_32 = AutoCrystal.INSTANCE.getMainGroup();
        var79_33 = new IntRange(1, 10);
        var80_34 = 3;
        var81_35 = "predict-ticks";
        var82_36 = AutoCrystal.INSTANCE;
        AutoCrystal.predictTicks = SettingFactory.intSetting(var82_36, var81_35, var80_34, var79_33, var78_32, var77_31);
        AutoCrystal.placeGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "place", null, null, 6, null);
        AutoCrystal.oldVer$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "1.12", false, AutoCrystal.INSTANCE.getPlaceGroup(), null, 8, null);
        AutoCrystal.placeDelay$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "place-delay", 0, new IntRange(0, 10), AutoCrystal.INSTANCE.getPlaceGroup(), null, 16, null);
        var85_37 = null;
        var86_38 = 16;
        var87_39 = null;
        var88_40 = AutoCrystal.INSTANCE.getPlaceGroup();
        var89_41 = RangesKt.rangeTo(1.0f, 6.0f);
        AutoCrystal.placeRange$delegate = SettingFactory.floatSetting$default(AutoCrystal.INSTANCE, "place-range", 5.0f, var89_41, var88_40, var87_39, var86_38, var85_37);
        AutoCrystal.limitPlace$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "limit-place", true, AutoCrystal.INSTANCE.getPlaceGroup(), null, 8, null);
        AutoCrystal.removeFire$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "remove-fire", true, AutoCrystal.INSTANCE.getPlaceGroup(), null, 8, null);
        AutoCrystal.removeLava$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "remove-lava", true, AutoCrystal.INSTANCE.getPlaceGroup(), null, 8, null);
        AutoCrystal.breakGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "break", null, null, 6, null);
        AutoCrystal.breakDelay$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "break-delay", 0, new IntRange(0, 10), AutoCrystal.INSTANCE.getBreakGroup(), null, 16, null);
        AutoCrystal.grimBreakRange$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "grim-break-range", false, AutoCrystal.INSTANCE.getBreakGroup(), null, 8, null);
        AutoCrystal.explodeRange$delegate = SettingFactory.floatSetting((SettingContainer)AutoCrystal.INSTANCE, "break-range", 5.0f, RangesKt.rangeTo(1.0f, 6.0f), AutoCrystal.INSTANCE.getBreakGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)());
        AutoCrystal.limitBreak$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "limit-break", true, AutoCrystal.INSTANCE.getBreakGroup(), null, 8, null);
        AutoCrystal.randomizeDelay$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "randomize-delay", false, AutoCrystal.INSTANCE.getBreakGroup(), null, 8, null);
        AutoCrystal.pauseGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "pause", null, null, 6, null);
        AutoCrystal.mining = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "mining", false, AutoCrystal.INSTANCE.getPauseGroup(), null, 8, null);
        AutoCrystal.eating = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "eating", false, AutoCrystal.INSTANCE.getPauseGroup(), null, 8, null);
        AutoCrystal.inventory = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "inventory", false, AutoCrystal.INSTANCE.getPauseGroup(), null, 8, null);
        AutoCrystal.switchPause$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "switch-pause", 0, new IntRange(0, 10), AutoCrystal.INSTANCE.getPauseGroup(), null, 16, null);
        AutoCrystal.offHandPause$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "off-hand", 2, new IntRange(0, 5), AutoCrystal.INSTANCE.getPauseGroup(), null, 16, null);
        AutoCrystal.damagesGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "damages", null, null, 6, null);
        AutoCrystal.assumeBestArmor$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "assume-best-armor", false, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        var92_42 = null;
        var93_43 = 16;
        var94_44 = null;
        var95_45 = AutoCrystal.INSTANCE.getDamagesGroup();
        var96_46 = RangesKt.rangeTo(2.0f, 20.0f);
        AutoCrystal.minDamage$delegate = SettingFactory.floatSetting$default(AutoCrystal.INSTANCE, "min-damage", 6.0f, var96_46, var95_45, var94_44, var93_43, var92_42);
        var99_47 = null;
        var100_48 = 16;
        var101_49 = null;
        var102_50 = AutoCrystal.INSTANCE.getDamagesGroup();
        var103_51 = RangesKt.rangeTo(2.0f, 20.0f);
        AutoCrystal.maxSelfDamage$delegate = SettingFactory.floatSetting$default(AutoCrystal.INSTANCE, "max-self-damage", 10.0f, var103_51, var102_50, var101_49, var100_48, var99_47);
        var106_52 = null;
        var107_53 = 16;
        var108_54 = null;
        var109_55 = AutoCrystal.INSTANCE.getDamagesGroup();
        var110_56 = RangesKt.rangeTo(0.1f, 5.0f);
        AutoCrystal.efficiencyFactor$delegate = SettingFactory.floatSetting$default(AutoCrystal.INSTANCE, "min-damage-factor", 0.1f, var110_56, var109_55, var108_54, var107_53, var106_52);
        AutoCrystal.lethalMultiplier$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "lethal-multiplier", 1, new IntRange(1, 3), AutoCrystal.INSTANCE.getDamagesGroup(), null, 16, null);
        AutoCrystal.overrideSelfDamage$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "override-self-damage", true, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        AutoCrystal.overrideMinDamage$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "override-min-damage", true, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        AutoCrystal.armorScale$delegate = SettingFactory.floatSetting((SettingContainer)AutoCrystal.INSTANCE, "armor-percent", 5.0f, RangesKt.rangeTo(0.0f, 40.0f), AutoCrystal.INSTANCE.getDamagesGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)());
        AutoCrystal.facePlaceHp$delegate = SettingFactory.floatSetting((SettingContainer)AutoCrystal.INSTANCE, "face-place-health", 5.0f, RangesKt.rangeTo(0.0f, 20.0f), AutoCrystal.INSTANCE.getDamagesGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)());
        var113_57 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)();
        var114_58 = AutoCrystal.INSTANCE.getDamagesGroup();
        var115_59 = true;
        var116_60 = "anti-e-chest-farm";
        var117_61 = AutoCrystal.INSTANCE;
        AutoCrystal.explodeEChestFarm$delegate = SettingFactory.booleanSetting(var117_61, var116_60, var115_59, var114_58, var113_57);
        var120_62 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)();
        var121_63 = AutoCrystal.INSTANCE.getDamagesGroup();
        var122_64 = true;
        var123_65 = "anti-portal";
        var124_66 = AutoCrystal.INSTANCE;
        AutoCrystal.antiPortal$delegate = SettingFactory.booleanSetting(var124_66, var123_65, var122_64, var121_63, var120_62);
        var127_67 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)();
        var128_68 = AutoCrystal.INSTANCE.getDamagesGroup();
        var129_69 = true;
        var130_70 = "anti-quiver";
        var131_71 = AutoCrystal.INSTANCE;
        AutoCrystal.antiQuiver$delegate = SettingFactory.booleanSetting(var131_71, var130_70, var129_69, var128_68, var127_67);
        AutoCrystal.terrainIgnore$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "destructible-terrain", true, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        AutoCrystal.friendProtection$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "friend-protection", (Enum)AutoCrystalFriendProtectionMode.ONLY_LETHAL, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        AutoCrystal.calcShields$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "calc-shields", false, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        AutoCrystal.damageSync$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "damage-sync", (Enum)AutoCrystalDamageSyncMode.OFF, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        var134_72 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)();
        var135_73 = AutoCrystal.INSTANCE.getDamagesGroup();
        var136_74 = true;
        var137_75 = "face-place-sync";
        var138_76 = AutoCrystal.INSTANCE;
        AutoCrystal.facePlaceSync$delegate = SettingFactory.booleanSetting(var138_76, var137_75, var136_74, var135_73, var134_72);
        var141_77 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)();
        var142_78 = AutoCrystal.INSTANCE.getDamagesGroup();
        var143_79 = true;
        var144_80 = "low-crystals";
        var145_81 = AutoCrystal.INSTANCE;
        AutoCrystal.lowCrystals$delegate = SettingFactory.booleanSetting(var145_81, var144_80, var143_79, var142_78, var141_77);
        AutoCrystal.stacksLeft$delegate = SettingFactory.floatSetting((SettingContainer)AutoCrystal.INSTANCE, "stacks-left", 1.5f, RangesKt.rangeTo(0.5f, 5.0f), AutoCrystal.INSTANCE.getDamagesGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)());
        AutoCrystal.minimizeSelfDamage$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "minimize-self-damage", true, AutoCrystal.INSTANCE.getDamagesGroup(), null, 8, null);
        AutoCrystal.maxDamageReduction$delegate = SettingFactory.floatSetting((SettingContainer)AutoCrystal.INSTANCE, "max-damage-reduction", 1.5f, RangesKt.rangeTo(0.01f, 10.0f), AutoCrystal.INSTANCE.getDamagesGroup(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)());
        AutoCrystal.switchGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "swap", null, null, 6, null);
        AutoCrystal.gappleSwitch$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "gapple-switch", true, AutoCrystal.INSTANCE.getSwitchGroup(), null, 8, null);
        AutoCrystal.switch = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "switch", (Enum)AutoCrystalAutoSwitchMode.NORMAL, AutoCrystal.INSTANCE.getSwitchGroup(), null, 8, null);
        AutoCrystal.antiWeakness = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "anti-weakness", (Enum)AutoCrystalAutoSwitchMode.SILENT, AutoCrystal.INSTANCE.getSwitchGroup(), null, 8, null);
        AutoCrystal.idPredictGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "id-predict", null, null, 6, null);
        AutoCrystal.idPredict$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "predict", false, AutoCrystal.INSTANCE.getIdPredictGroup(), null, 8, null);
        AutoCrystal.idOffset$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "id-offset", 1, new IntRange(1, 10), AutoCrystal.INSTANCE.getIdPredictGroup(), null, 16, null);
        AutoCrystal.idAttacks$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "id-attacks", 3, new IntRange(1, 10), AutoCrystal.INSTANCE.getIdPredictGroup(), null, 16, null);
        AutoCrystal.idDelay$delegate = SettingFactory.intSetting$default(AutoCrystal.INSTANCE, "id-delay", 25, new IntRange(0, 500), AutoCrystal.INSTANCE.getIdPredictGroup(), null, 16, null);
        AutoCrystal.idPredictOnlyDuels$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "predict-only-in-duels", false, AutoCrystal.INSTANCE.getIdPredictGroup(), null, 8, null);
        AutoCrystal.swingOnce$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "swing-once", false, AutoCrystal.INSTANCE.getIdPredictGroup(), null, 8, null);
        AutoCrystal.onlyBreakingSurr$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "only-breaking-surround", false, AutoCrystal.INSTANCE.getIdPredictGroup(), null, 8, null);
        AutoCrystal.targeting$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "targeting", null, null, 6, null);
        AutoCrystal.targetSort$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "target-sort", (Enum)AutoCrystalTargetSortMode.DISTANCE, AutoCrystal.INSTANCE.getTargeting(), null, 8, null);
        AutoCrystal.ignoreCreative$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "ignore-creative", true, AutoCrystal.INSTANCE.getTargeting(), null, 8, null);
        AutoCrystal.ignoreNaked$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "ignore-naked", false, AutoCrystal.INSTANCE.getTargeting(), null, 8, null);
        AutoCrystal.renderGroup$delegate = SettingFactory.settingGroup$default(AutoCrystal.INSTANCE, "render", null, null, 6, null);
        AutoCrystal.fakeCrystalRemove$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoCrystal.INSTANCE, "fake-crystal-remove", false, AutoCrystal.INSTANCE.getRenderGroup(), null, 8, null);
        var148_82 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)();
        var149_83 = AutoCrystal.INSTANCE.getRenderGroup();
        var150_84 = new IntRange(0, 200);
        var151_85 = 50;
        var152_86 = "remove-delay";
        var153_87 = AutoCrystal.INSTANCE;
        AutoCrystal.removeDelay$delegate = SettingFactory.intSetting(var153_87, var152_86, var151_85, var150_84, var149_83, var148_82);
        AutoCrystal.targetEsp$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "highlight-target", (Enum)AuraEspMode.COMETS, AutoCrystal.INSTANCE.getRenderGroup(), null, 8, null);
        AutoCrystal.renderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoCrystal.INSTANCE, "render-mode", (Enum)AutoCrystalRenderMode.SHADER, AutoCrystal.INSTANCE.getRenderGroup(), null, 8, null);
        AutoCrystal.textPattern$delegate = SettingFactory.textSetting$default(AutoCrystal.INSTANCE, "text-pattern", "damage / self", AutoCrystal.INSTANCE.getRenderGroup(), null, 8, null);
        var156_88 = null;
        var157_89 = 16;
        var158_90 = null;
        var159_91 = AutoCrystal.INSTANCE.getRenderGroup();
        var160_92 = RangesKt.rangeTo(0.0f, 1.0f);
        AutoCrystal.alpha$delegate = SettingFactory.floatSetting$default(AutoCrystal.INSTANCE, "alpha", 0.4f, var160_92, var159_91, var158_90, var157_89, var156_88);
        AutoCrystal.placeResultRef = new AtomicReference<Object>(null);
        AutoCrystal.crystalToBreak = new AtomicReference<Object>(null);
        AutoCrystal.mineSyncFlag = new AtomicBoolean();
        AutoCrystal.e = new TickTimer();
        AutoCrystal.targetTimer = new TickTimer();
        AutoCrystal.breakCooldown = new Timer();
        AutoCrystal.j = new TickTimer();
        AutoCrystal.switchPauseTimer = new TickTimer();
        AutoCrystal.minedBlocks = new LinkedHashMap<K, V>();
        AutoCrystal.placedPositions = new LinkedList<E>();
        AutoCrystal.rotationTarget = new AtomicReference<V>();
        AutoCrystal.o = new Timer();
        AutoCrystal.renderAnimation = new Animation(Easing.OUT_BACK, 1000L);
    }

    public static void zkmSetFlag(String string) {
        x = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return x;
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x73E8;
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
                throw new RuntimeException("su/catlean/ux", exception);
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
            AutoCrystal.gb[n2] = AutoCrystal.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoCrystal.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ux" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x68D7;
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
                throw new RuntimeException("su/catlean/ux", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoCrystal.mb[n2] = n3;
        }
        return mb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoCrystal.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ux" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xE9B;
        if (pb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = ob[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])qb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    qb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ux", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            AutoCrystal.pb[n2] = l4;
        }
        return pb[n2];
    }

    private static long e(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = AutoCrystal.zkmDecryptLong(n, l);
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
            throw new RuntimeException("su/catlean/ux" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoCrystal.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoCrystal.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
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
            return MethodHandles.lookup().findStatic(AutoCrystal.class, "e", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

