/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import java.lang.invoke.CallSite;
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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.attack.AutoCrystalFriendProtectionMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CrystalContext {
    @NotNull
    private final List entities;
    @NotNull
    private final PlayerEntity target;
    private final float placeRange;
    private final float explodeRange;
    private final float maxSelfDamage;
    private final float minDamage;
    private final boolean oldVer;
    private final float armorScale;
    private final float facePlaceHp;
    private final boolean overrideSelfDamage;
    private final float efficiencyFactor;
    private final boolean rayTrace;
    @NotNull
    private final AutoCrystalFriendProtectionMode friendProtection;
    private final boolean breakFire;
    private final boolean ignoreTerrain;
    private final int lethalMultiplier;
    private final boolean minimizeSelfDamage;
    private final float maxDamageReduction;
    private final boolean calcShields;
    private final boolean stringWaterRemove;
    private final boolean grimBreakRange;
    private final boolean explodeEChestFarm;
    private final boolean antiPortal;
    private final boolean antiQuiver;
    private final boolean overrideMinDamage;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map g;
    private static final long[] l;
    private static final Integer[] m;
    private static final Map n;

    public CrystalContext(@NotNull List list, @NotNull PlayerEntity playerEntity, float f, float f2, float f3, float f4, boolean bl, float f5, float f6, boolean bl2, float f7, boolean bl3, @NotNull AutoCrystalFriendProtectionMode autoCrystalFriendProtectionMode, boolean bl4, boolean bl5, int n, boolean bl6, float f8, boolean bl7, boolean bl8, boolean bl9, boolean bl10, boolean bl11, boolean bl12, boolean bl13) {
        Intrinsics.checkNotNullParameter(list, "entities");
        Intrinsics.checkNotNullParameter(playerEntity, "target");
        Intrinsics.checkNotNullParameter((Object)autoCrystalFriendProtectionMode, "friendProtection");
        this.entities = list;
        this.target = playerEntity;
        this.placeRange = f;
        this.explodeRange = f2;
        this.maxSelfDamage = f3;
        this.minDamage = f4;
        this.oldVer = bl;
        this.armorScale = f5;
        this.facePlaceHp = f6;
        this.overrideSelfDamage = bl2;
        this.efficiencyFactor = f7;
        this.rayTrace = bl3;
        this.friendProtection = autoCrystalFriendProtectionMode;
        this.breakFire = bl4;
        this.ignoreTerrain = bl5;
        this.lethalMultiplier = n;
        this.minimizeSelfDamage = bl6;
        this.maxDamageReduction = f8;
        this.calcShields = bl7;
        this.stringWaterRemove = bl8;
        this.grimBreakRange = bl9;
        this.explodeEChestFarm = bl10;
        this.antiPortal = bl11;
        this.antiQuiver = bl12;
        this.overrideMinDamage = bl13;
    }

    @NotNull
    public final List getEntities() {
        return this.entities;
    }

    @NotNull
    public final PlayerEntity getTarget() {
        return this.target;
    }

    public final float getPlaceRange() {
        return this.placeRange;
    }

    public final float getExplodeRange() {
        return this.explodeRange;
    }

    public final float getMaxSelfDamage() {
        return this.maxSelfDamage;
    }

    public final float getMinDamage() {
        return this.minDamage;
    }

    public final boolean isOldVer() {
        return this.oldVer;
    }

    public final float getArmorScale() {
        return this.armorScale;
    }

    public final float getFacePlaceHp() {
        return this.facePlaceHp;
    }

    public final boolean isOverrideSelfDamage() {
        return this.overrideSelfDamage;
    }

    public final float getEfficiencyFactor() {
        return this.efficiencyFactor;
    }

    public final boolean isRayTrace() {
        return this.rayTrace;
    }

    @NotNull
    public final AutoCrystalFriendProtectionMode getFriendProtection() {
        return this.friendProtection;
    }

    public final boolean isBreakFire() {
        return this.breakFire;
    }

    public final boolean isIgnoreTerrain() {
        return this.ignoreTerrain;
    }

    public final int getLethalMultiplier() {
        return this.lethalMultiplier;
    }

    public final boolean isMinimizeSelfDamage() {
        return this.minimizeSelfDamage;
    }

    public final float getMaxDamageReduction() {
        return this.maxDamageReduction;
    }

    public final boolean isCalcShields() {
        return this.calcShields;
    }

    public final boolean isStringWaterRemove() {
        return this.stringWaterRemove;
    }

    public final boolean isGrimBreakRange() {
        return this.grimBreakRange;
    }

    public final boolean isExplodeEChestFarm() {
        return this.explodeEChestFarm;
    }

    public final boolean isAntiPortal() {
        return this.antiPortal;
    }

    public final boolean isAntiQuiver() {
        return this.antiQuiver;
    }

    public final boolean isOverrideMinDamage() {
        return this.overrideMinDamage;
    }

    @NotNull
    public final List getEntities2() {
        return this.entities;
    }

    @NotNull
    public final PlayerEntity getTarget2() {
        return this.target;
    }

    public final float getPlaceRange2() {
        return this.placeRange;
    }

    public final float getExplodeRange2() {
        return this.explodeRange;
    }

    public final float getMaxSelfDamage2() {
        return this.maxSelfDamage;
    }

    public final float getMinDamage2() {
        return this.minDamage;
    }

    public final boolean isOldVer2() {
        return this.oldVer;
    }

    public final float getArmorScale2() {
        return this.armorScale;
    }

    public final float getFacePlaceHp2() {
        return this.facePlaceHp;
    }

    public final boolean isOverrideSelfDamage2() {
        return this.overrideSelfDamage;
    }

    public final float getEfficiencyFactor2() {
        return this.efficiencyFactor;
    }

    public final boolean isRayTrace2() {
        return this.rayTrace;
    }

    @NotNull
    public final AutoCrystalFriendProtectionMode getFriendProtection2() {
        return this.friendProtection;
    }

    public final boolean isBreakFire2() {
        return this.breakFire;
    }

    public final boolean isIgnoreTerrain2() {
        return this.ignoreTerrain;
    }

    public final int getLethalMultiplier2() {
        return this.lethalMultiplier;
    }

    public final boolean isMinimizeSelfDamage2() {
        return this.minimizeSelfDamage;
    }

    public final float getMaxDamageReduction2() {
        return this.maxDamageReduction;
    }

    public final boolean isCalcShields2() {
        return this.calcShields;
    }

    public final boolean isStringWaterRemove2() {
        return this.stringWaterRemove;
    }

    public final boolean isGrimBreakRange2() {
        return this.grimBreakRange;
    }

    public final boolean isExplodeEChestFarm2() {
        return this.explodeEChestFarm;
    }

    public final boolean isAntiPortal2() {
        return this.antiPortal;
    }

    public final boolean isAntiQuiver2() {
        return this.antiQuiver;
    }

    public final boolean isOverrideMinDamage2() {
        return this.overrideMinDamage;
    }

    @NotNull
    public final CrystalContext copy(@NotNull List list, @NotNull PlayerEntity playerEntity, float f, float f2, float f3, float f4, boolean bl, float f5, float f6, boolean bl2, float f7, boolean bl3, @NotNull AutoCrystalFriendProtectionMode autoCrystalFriendProtectionMode, boolean bl4, boolean bl5, int n, boolean bl6, float f8, boolean bl7, boolean bl8, boolean bl9, boolean bl10, boolean bl11, boolean bl12, boolean bl13) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)CrystalContext.zkmBootstrap("b", (int)15966, (long)(0xBCE82DA5CF7BCCCL ^ l))));
        Intrinsics.checkNotNullParameter(playerEntity, (String)((Object)CrystalContext.zkmBootstrap("b", (int)7766, (long)(0x19485DE5CBD81CDEL ^ l))));
        Intrinsics.checkNotNullParameter((Object)autoCrystalFriendProtectionMode, (String)((Object)CrystalContext.zkmBootstrap("b", (int)17288, (long)(0x39162ECDD9414112L ^ l))));
        return new CrystalContext(list, playerEntity, f, f2, f3, f4, bl, f5, f6, bl2, f7, bl3, autoCrystalFriendProtectionMode, bl4, bl5, n, bl6, f8, bl7, bl8, bl9, bl10, bl11, bl12, bl13);
    }

    /*
     * WARNING - void declaration
     */
    public static CrystalContext copy$default(CrystalContext crystalContext, List list, PlayerEntity playerEntity, float f, float f2, float f3, float f4, boolean bl, float f5, float f6, boolean bl2, float f7, boolean bl3, AutoCrystalFriendProtectionMode autoCrystalFriendProtectionMode, boolean bl4, boolean bl5, int n, boolean bl6, float f8, long l, boolean bl7, boolean bl8, boolean bl9, boolean bl10, boolean bl11, boolean bl12, boolean bl13, int n2, Object object) {
        CrystalContext crystalContext2;
        block76: {
            boolean bl14;
            block75: {
                boolean bl15;
                block74: {
                    void var28_28;
                    l = a ^ l;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-4978235138598351409L, (long)l);
                    bl15 = var28_28 & 1;
                    if (callSite == null) {
                        if (bl15) {
                            list = crystalContext.entities;
                        }
                        bl15 = var28_28 & 2;
                    }
                    CallSite callSite2 = callSite;
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                playerEntity = crystalContext.target;
                            }
                            bl15 = var28_28 & 4;
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f = crystalContext.placeRange;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)4109, (long)(0x69D3070C71E49D6DL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f2 = crystalContext.explodeRange;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)31532, (long)(0x9544A6DA1E07650L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f3 = crystalContext.maxSelfDamage;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)6373, (long)(0x46F9686DD9819588L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f4 = crystalContext.minDamage;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)5744, (long)(0x60A14FF64C459B19L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl = crystalContext.oldVer;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)7125, (long)(0x5BF24DFDABF316BDL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f5 = crystalContext.armorScale;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)26832, (long)(0x3EC91EB17765E5B6L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f6 = crystalContext.facePlaceHp;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)23914, (long)(0x550D63EC64E6500EL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl2 = crystalContext.overrideSelfDamage;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)24340, (long)(0x17E4ADF4612CD27EL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f7 = crystalContext.efficiencyFactor;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)29283, (long)(0x58EBBD2EAA31FF0CL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl3 = crystalContext.rayTrace;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)23784, (long)(0x2ED173A6B1B5518FL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                autoCrystalFriendProtectionMode = crystalContext.friendProtection;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)13276, (long)(0x622EF438DD57BEBEL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl4 = crystalContext.breakFire;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)1317, (long)(0x1329D2D9DCC3085EL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl5 = crystalContext.ignoreTerrain;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)25573, (long)(0x60B418579C606E98L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                n = crystalContext.lethalMultiplier;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)11939, (long)(0x3838D13CFF57A3CDL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl6 = crystalContext.minimizeSelfDamage;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)18493, (long)(0x556BEF109E474558L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                f8 = crystalContext.maxDamageReduction;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)15065, (long)(0x231F0046518CB7B5L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl7 = crystalContext.calcShields;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)16729, (long)(0x74BBF0C85D334C32L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl8 = crystalContext.stringWaterRemove;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)12158, (long)(0x30C96E34BE7A207L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl9 = crystalContext.grimBreakRange;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)6940, (long)(0x2787846B9A0B1662L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl10 = crystalContext.explodeEChestFarm;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)19561, (long)(0x688E3E8D42EFC108L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl11 = crystalContext.antiPortal;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)31124, (long)(0x8B3E674A502F4F7L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl15) {
                                bl12 = crystalContext.antiQuiver;
                            }
                            bl15 = var28_28 & CrystalContext.zkmBootstrap2("c", (int)13992, (long)(0x5747168043D73BD2L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) break block74;
                    if (!bl15) break block75;
                    bl15 = crystalContext.overrideMinDamage;
                }
                bl14 = bl15;
            }
            crystalContext2 = crystalContext.copy(list, playerEntity, f, f2, f3, f4, bl, f5, f6, bl2, f7, bl3, autoCrystalFriendProtectionMode, bl4, bl5, n, bl6, f8, bl7, bl8, bl9, bl10, bl11, bl12, bl14);
            if (l < 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)-5033137796464925513L, (long)l) == null) break block76;
            ZkmReferences.zkmBootstrap("\u00c5", "BcVJ0", (long)-4972569315104205857L, (long)l);
        }
        return crystalContext2;
    }

    @NotNull
    public String toString() {
        boolean bl = this.overrideMinDamage;
        boolean bl2 = this.antiQuiver;
        boolean bl3 = this.antiPortal;
        boolean bl4 = this.explodeEChestFarm;
        boolean bl5 = this.grimBreakRange;
        boolean bl6 = this.stringWaterRemove;
        boolean bl7 = this.calcShields;
        float f = this.maxDamageReduction;
        boolean bl8 = this.minimizeSelfDamage;
        int n = this.lethalMultiplier;
        boolean bl9 = this.ignoreTerrain;
        boolean bl10 = this.breakFire;
        AutoCrystalFriendProtectionMode autoCrystalFriendProtectionMode = this.friendProtection;
        boolean bl11 = this.rayTrace;
        float f2 = this.efficiencyFactor;
        boolean bl12 = this.overrideSelfDamage;
        float f3 = this.facePlaceHp;
        float f4 = this.armorScale;
        boolean bl13 = this.oldVer;
        float f5 = this.minDamage;
        float f6 = this.maxSelfDamage;
        float f7 = this.explodeRange;
        float f8 = this.placeRange;
        PlayerEntity playerEntity = this.target;
        List list = this.entities;
        return "ConfigState(entities=" + list + ", target=" + playerEntity + ", placeRange=" + f8 + ", explodeRange=" + f7 + ", maxSelfDamage=" + f6 + ", minDamage=" + f5 + ", oldVer=" + bl13 + ", armorScale=" + f4 + ", facePlaceHp=" + f3 + ", overrideSelfDamage=" + bl12 + ", efficiencyFactor=" + f2 + ", rayTrace=" + bl11 + ", friendProtection=" + autoCrystalFriendProtectionMode + ", breakFire=" + bl10 + ", ignoreTerrain=" + bl9 + ", lethalMultiplier=" + n + ", minimizeSelfDamage=" + bl8 + ", maxDamageReduction=" + f + ", calcShields=" + bl7 + ", stringWaterRemove=" + bl6 + ", grimBreakRange=" + bl5 + ", explodeEChestFarm=" + bl4 + ", antiPortal=" + bl3 + ", antiQuiver=" + bl2 + ", overrideMinDamage=" + bl + ")";
    }

    public int hashCode() {
        int n = ((Object)this.entities).hashCode();
        n = n * 31 + this.target.hashCode();
        n = n * 31 + Float.hashCode(this.placeRange);
        n = n * 31 + Float.hashCode(this.explodeRange);
        n = n * 31 + Float.hashCode(this.maxSelfDamage);
        n = n * 31 + Float.hashCode(this.minDamage);
        n = n * 31 + Boolean.hashCode(this.oldVer);
        n = n * 31 + Float.hashCode(this.armorScale);
        n = n * 31 + Float.hashCode(this.facePlaceHp);
        n = n * 31 + Boolean.hashCode(this.overrideSelfDamage);
        n = n * 31 + Float.hashCode(this.efficiencyFactor);
        n = n * 31 + Boolean.hashCode(this.rayTrace);
        n = n * 31 + this.friendProtection.hashCode();
        n = n * 31 + Boolean.hashCode(this.breakFire);
        n = n * 31 + Boolean.hashCode(this.ignoreTerrain);
        n = n * 31 + Integer.hashCode(this.lethalMultiplier);
        n = n * 31 + Boolean.hashCode(this.minimizeSelfDamage);
        n = n * 31 + Float.hashCode(this.maxDamageReduction);
        n = n * 31 + Boolean.hashCode(this.calcShields);
        n = n * 31 + Boolean.hashCode(this.stringWaterRemove);
        n = n * 31 + Boolean.hashCode(this.grimBreakRange);
        n = n * 31 + Boolean.hashCode(this.explodeEChestFarm);
        n = n * 31 + Boolean.hashCode(this.antiPortal);
        n = n * 31 + Boolean.hashCode(this.antiQuiver);
        n = n * 31 + Boolean.hashCode(this.overrideMinDamage);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CrystalContext)) {
            return false;
        }
        CrystalContext crystalContext = (CrystalContext)object;
        if (!Intrinsics.areEqual(this.entities, crystalContext.entities)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.target, crystalContext.target)) {
            return false;
        }
        if (Float.compare(this.placeRange, crystalContext.placeRange) != 0) {
            return false;
        }
        if (Float.compare(this.explodeRange, crystalContext.explodeRange) != 0) {
            return false;
        }
        if (Float.compare(this.maxSelfDamage, crystalContext.maxSelfDamage) != 0) {
            return false;
        }
        if (Float.compare(this.minDamage, crystalContext.minDamage) != 0) {
            return false;
        }
        if (this.oldVer != crystalContext.oldVer) {
            return false;
        }
        if (Float.compare(this.armorScale, crystalContext.armorScale) != 0) {
            return false;
        }
        if (Float.compare(this.facePlaceHp, crystalContext.facePlaceHp) != 0) {
            return false;
        }
        if (this.overrideSelfDamage != crystalContext.overrideSelfDamage) {
            return false;
        }
        if (Float.compare(this.efficiencyFactor, crystalContext.efficiencyFactor) != 0) {
            return false;
        }
        if (this.rayTrace != crystalContext.rayTrace) {
            return false;
        }
        if (this.friendProtection != crystalContext.friendProtection) {
            return false;
        }
        if (this.breakFire != crystalContext.breakFire) {
            return false;
        }
        if (this.ignoreTerrain != crystalContext.ignoreTerrain) {
            return false;
        }
        if (this.lethalMultiplier != crystalContext.lethalMultiplier) {
            return false;
        }
        if (this.minimizeSelfDamage != crystalContext.minimizeSelfDamage) {
            return false;
        }
        if (Float.compare(this.maxDamageReduction, crystalContext.maxDamageReduction) != 0) {
            return false;
        }
        if (this.calcShields != crystalContext.calcShields) {
            return false;
        }
        if (this.stringWaterRemove != crystalContext.stringWaterRemove) {
            return false;
        }
        if (this.grimBreakRange != crystalContext.grimBreakRange) {
            return false;
        }
        if (this.explodeEChestFarm != crystalContext.explodeEChestFarm) {
            return false;
        }
        if (this.antiPortal != crystalContext.antiPortal) {
            return false;
        }
        if (this.antiQuiver != crystalContext.antiQuiver) {
            return false;
        }
        return this.overrideMinDamage == crystalContext.overrideMinDamage;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        CrystalContext.a = ZkmKeys.a(9169581523206441333L, -170958280359002345L, MethodHandles.lookup().lookupClass()).a(102733960513791L);
                        CrystalContext.g = new HashMap<K, V>(13);
                        var11 = CrystalContext.a ^ 111439005571833L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[31];
                        var18_4 = 0;
                        var17_5 = "\u0086\f\u00fd\u00e7\u00bb1\u00eaW\u0010\u00ad\u00f143\u00d7\u00ef(1z\u0000_L\u0097G\u0000\u0018\u001e\u0012\u00af \u00f1\u00be0\u009f\u00c8\u00d1\u00c7\u00ebD\u00faUL\u00f4\r\u001b*\u0098\u0000v\u00cd \u001dD,\u008c\u00aa\u00a3\u00a8\u00ceG\u00ea\u0098\u0080u\u00d51\u000e\u00e6\u00b6\u00a0\t*\u009a\u00eb\u00b4u0b\u00ed\u009a\u0017\u0086\u0086(\u0088)H\nO\u0013\u001b\u00d8\u0095\u009es>\u0084\u00d8\u00e1'\u00d27\u0093\u00bd\u0019\u0080[-\u0015\u00a8K\u009b\u00a8\u00c9\u00fc\u00ea\u001b\u0014\u00f9\u00aeG\nP\u0085\u0018\u00ad\u00c3\u00c0\u0014\u008c\f\u00fa\u000eUU\u000e\u00bd\u00ff#\u00e0\u0098\u0011(\u008cw\u00ee\u00e7\u00c1.(\b\u00dc.]F\u00e1{J\u00d2j#\u00af`wQ\u00f3\u0006\u00e5}\u009d\u00b6|\u001d5\u00ef*)\u00e1Y\u009c)\b\u007f\u00bd\u00b9W\u00bf\u00cc\u009d=(]\b\u001e\u00a0\u009b\u00ee\u0011\u00ae\u0089a8>\u00ef2\u00f1\u0081\bN\u00ef\u00df\u0000\u00fe\u0086\u00d4\u009by\u00bc\t*\u00f7\u00ac\u00cb\u0088\u00f8\u00a5\u00c3\u00e7}\u008b4 \u008e\u0085\u001dC\u00a3\u00cd\u0019J(B\u0086\u00cc\u000f\u00e9e\u0013't\u00b8\u0014\u00a3\u0017\u00ba\u00ac<`2M\u00c2T\u00a4\u008b0\u00d8\u00ed\u00e8,0M\u0017|\u00a7\u000f}\u0010_\u00cb\u0007a\u00c5\u00ad\u009d\u00a3\u00c2\u0010\u00d2c\u00b6y\u00ba\u0015\u00b2\u008f\u0002\u00814:\u00e2\u0090\n\u00e3@\u0004H\u009c\u0011\u0083W\u00ddQd(\u00beS\u0089%\\\u0088\u0013&YS[\u00ad\u00e8!\u00cf\u0019\u00c8- }\u0017\u00ecZ\u00b1-g\u009dj\u00a66d\u0091Q_\u00ea\u00c0\u00c5\u00b8\u00d9L(Y\u000f\u00ae\u00a9$\u0005J\u00db\u0011i{\u0091 q$\u00c7$\fHN\u00c5\u00adOhL\u0013\u00f6\u00d4\u00b5s\u00b0I\u00bcw\u0085f\u00c2{Oh(\u00f0\u00b7\u00ed\u008e\u00c9\u0086\u00a6H\u0005\u00eef\u0092<\u00fc\u00cd\u00f7\u0016\u00f4f\u0002&M\u009b~\u0016\u00e0\u00b7\u008c\u008e\u00bfh\u00ca\u0089s9\u009ec\u00fb\u00fcR G\u00d0\u00f5I\u00ecx\u009a\u008f\u00bbk\u00d9\u0015q\u00eb\u00bd\u0010VM\u00a0-\u00bfd\u00f6\u0005w\u00f7O\u00cf\u000eX\u00f0\u007f\u0018\u00f9\u0085k\u00c6\u0016lQ\u0099\u00cc\u00c5\u00ce\\\u00e1M\u00aa\u0086\u00e4e\u0005\u00bd\u009f\u00b1\u00d5\u00fe \u0005J\u00f1\u00be\u00e1\u00da\u001bL\u00ea\u00a9v\u008eV\u0096\u00ff\u00f5\u0005\u00feI\u000e\u008bZR5O\u00be\u00c8\u00fb\u0000E\"\u00ab \u00c2\u00ea\u00e0\u00b5\u00d7\u00cf\u00b6\u0006\u00b0\u00e5\u00ab9\u00d3*\u00cc\u0011\u0019\u009f\t\u0084}\u008c\u001cF\u001f\u00863M\u00f9R\u00cda\u0010oaZv\u008e\u0088y\u00fd\u00f0\u00ac\u00cc5\u00f0\u00b5\u00ab\u00ff(\t\u0010}\u00aa\u0001\u00f8:\u008a\u00f9t\u0005\u008c4\u0018\u009f\u00da-r'4e\u001cr\u009a\u00f4zg\u00fe#\u001f\u00c7\u0011\u009aL!\\=\u009e^x \u00f9W\u001c\u00d3\u00c1[ow\u001aM\u008a\u001a\u00ae\u00a2n2$*Q\u009e\u00ab\u00e6}\u0085\u0097\u008aC>]\u00e4\u0002p \u00c4\u001e\n\u00bcz`\u00cc0\u009e\u008b\u00ef\r}\u00da\n\u00c0\u00a8}\u00b7>\u0089\u00f6$\u00cc\u0086\u000b\u0014[t\u00f0\u0093/(*\u00eb@q\u00f1O>b\u009a\u00d6\u008d\u00a5\u00dc:|\u00878\u00b3\u00ba\u0005\u0098gb*phK\u0087\u008a\u001a#:\u00e5\u00bd3\u00f1\u00c2\\\u00d38 \u00e8\u00b7{\u00f6]\u00ce\u00f3\u00a3\u0000S\u00e3o]-\u0002\u00ba\u00d0\u001a\u009f\u0082\u00d3X\u00e3\u00d2\u00ff\u00ab973\u00fc\u00ecX(\u00e8M\u00e6)9\u000b\u00f4\u0015v\u008e\u0016\u00d6\u000b\u0011n\u001b\u00c0B\u00db\u008d\u00ffs\u00dbQ$\u00f7{\u00b8\u007f#8\u00c1\u00ce\u00c1\u00d9V\\2\u00f2\u00f0\u0010\u00dc\u0085\u00c5\u00a6\u00db$u\u00e7\u00f99\u0006\u00da\u00b4\u00f7\u0086k \u00e3p\u00e9(\u0080\f\u008dh\u009fK>\u009aYG)\u00d2\u0093u!\u0015\u00cb`\u00cd~\t\u00c7\u00b3\u001e\u00cdJ~c 1}3\u00ed\u0011z.#\u00e0/\u00b0\u00e7W\u00b3'\u00ca\u001d\u0017\u008b/\u00f4\u0001&\u0083\u00c6\u00de&\u0003\u00f0\u00abR\u00c9(\u0019\u0098\u00c4aj;\u0082bb\u00f9\u00f9\u00c2%\rU\u00ad\u00b9p\u001c\u008a\u00b3aki\u0010\u007f\u0090\u00ad\u00f7\u00bf\u00c3!1 \u00b0Q~\u00a9\u00d6\u0097(f\u00a8\u00dc\u000f\u00da\u001a\u0012P\u00cd5\u000e\u001c2\u00c7\u00d8:\u0087\u00f5\u0098D\u001f\u0004r\u00d0\u00194\u00c1\u0080\u00d0\u0019e\u0084h\u0087A\u0088G}\u0094\u00c3(\u001e\u000fN\u0083\u001d\u00cb\u008f\n\u00d5\u0090\u00bd\u00d4T$\u00d4}*\u00c1h\u00c5v\u0019&k\u0097F7\u00d0\u00fb\u00aa\u0001\u00c8\u0087\u00dc\u00f2\u00b2\u00ca\u0097\u00ed'";
                        var19_6 = 1004;
                        var16_7 = 24;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = CrystalContext.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\\\u00ddf4\u00eaH\u0082^ \u00b2a\u00cb\u00e1\u00a0\u0002\u0093\u00dc\u00b8-\u000e.\u00bb\u00d1\u00d3\u000b\u00b6\u0006\u00f2\u00b4{\u00d97\u00ca\u00a8\u00e8[f\u00f7\u00af\u00e0(u\u00df\u00f7,Zm\u00f5\u0005\u00ec\u00f1\u00fb\u00fd\u00bbS\u00ac\u00fa`\u00c6\u00a5\u00ffP\u0003\u00dc\u00c0d\u00cf\u00ea\u00cb\u00b06\u00887\u008c\u00f4\u0097\u00dfJ\u008eox";
                            var19_6 = 81;
                            var16_7 = 40;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = CrystalContext.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                CrystalContext.b = var20_3;
                CrystalContext.d = new String[31];
                CrystalContext.n = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[24];
                var3_13 = 0;
                var4_14 = "\u008eAT\u0082\u00eex{\u008f\u00bbZ?$\u0006z\u00f8\u00b9\u00c0%i\u009a+\u00d7tw\u0011V@\u00a0\u0095\u00d9\u008f\u001c\u000en\u00cf\u0095\u00a0{\u00b8\u00a8\u00f9\u00ab\u009b'\u00f9^\u00fd+\u0097K=^r\u00a9%\u00a2}\u007fu\rh\u0098\u00a4\u00d3\u00b5\u0080E\u009e)\u00e1\u00d7\u00ba\u00a2\u00d7:Ea\u00f7\u00a1\u00a6\u00a4k\u00d9\u00838hOkWh\u00bc^\u00f7\u00da\u00cc\u00b0\u00c3\u00e4\u0000\u00ea\u00e6\u00c9\u00e3\u00193\u00d9\u00ea\u00ac\u00f1}\u00bf\u00b3\u0010\u00b6\u00ec\u0004\u0088\u00e8\u00bf\u001a\u00c1+w \u00809\u00e0\u009b\u00c9\u00bc\u00a4\u00c8w\u00e5\u00feD\u0014\u00ce\u00d7l\u00cb{\u009e:\u00ea\u00ac\u00cc:\u008d\b\u0002Uu2\u00ed,\u000f\u00bb$\u0081\u00f5CR\f\u00eaK\u00a0\u0081\u00c5.\u00c1\u00b8(\\\u00dbx";
                var5_15 = 176;
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
                    var4_14 = "\u00d9\u0017[\u00d0\u00cd\u0098\u0082b\u00d5\u00f8f\u00b2\u00a7\u00fd\u00882";
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
        CrystalContext.l = var6_12;
        CrystalContext.m = new Integer[24];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2645;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/gp", exception);
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
            CrystalContext.d[n2] = CrystalContext.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalContext.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x361C;
        if (m[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = CrystalContext.l[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])CrystalContext.n.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    CrystalContext.n.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/gp", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CrystalContext.m[n2] = n3;
        }
        return m[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CrystalContext.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/gp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalContext.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CrystalContext.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

