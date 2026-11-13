/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.AutoPotionBrewAfterCompleteMode;
import su.catlean.module.misc.AutoPotionBrewMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoPotionBrew
extends Module {
    @NotNull
    public static final AutoPotionBrew INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final BooleanSetting addGunpowder$delegate;
    @NotNull
    private static final ModeSetting afterComplete$delegate;
    @NotNull
    private static final BooleanSetting open$delegate;
    @NotNull
    private static final Timer delayTimer;
    @NotNull
    private static final Map h;
    @Nullable
    private static BlockPos standPos;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map j;

    private AutoPotionBrew() {
        super("auto-potion-brew", Categories.getMisc(), null, null);
    }

    private final AutoPotionBrewMode getMode() {
        return (AutoPotionBrewMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final boolean getAddGunpowder() {
        return (Boolean)addGunpowder$delegate.getValue(this, delegatedProperties[2]);
    }

    private final AutoPotionBrewAfterCompleteMode getAfterComplete() {
        return (AutoPotionBrewAfterCompleteMode)((Object)afterComplete$delegate.getValue(this, delegatedProperties[3]));
    }

    private final boolean getOpen() {
        return (Boolean)open$delegate.getValue(this, delegatedProperties[4]);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block22: {
            block20: {
                block21: {
                    if (!(Globals.getPlayer().currentScreenHandler instanceof BrewingStandScreenHandler)) break block20;
                    v0 = Globals.getPlayer().currentScreenHandler;
                    Intrinsics.checkNotNull(v0, "null cannot be cast to non-null type net.minecraft.world.inventory.BrewingStandMenu");
                    var47_2 = (BrewingStandScreenHandler)v0;
                    if (this.hasFuel()) {
                        v1 = Items.BLAZE_POWDER;
                        Intrinsics.checkNotNullExpressionValue(v1, "BLAZE_POWDER");
                        if (this.findItemSlot(v1) != -1) {
                            v2 = Items.BLAZE_POWDER;
                            Intrinsics.checkNotNullExpressionValue(v2, "BLAZE_POWDER");
                            var41_4 = 4;
                            var42_8 = v2;
                            this.insertIngredient(var42_8, var41_4);
                        }
                        return;
                    }
                    for (var48_12 = 0; var48_12 < 3; ++var48_12) {
                        if (!Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(var48_12).getStack().getItem(), Items.AIR)) continue;
                        v3 = Potions.WATER.comp_349();
                        Intrinsics.checkNotNullExpressionValue(v3, "value(...)");
                        var49_14 = this.findPotionInInventory(v3);
                        if (!AutoPotionBrew.delayTimer.hasPassed(this.getDelay()) || var49_14 == -1) continue;
                        InteractionUtil.clickSlot$default(var49_14, 0, null, false, 14, null);
                        InteractionUtil.clickSlot$default(var48_12, 0, null, false, 14, null);
                        var41_5 = null;
                        var42_9 = 14;
                        var43_16 = false;
                        var44_17 = null;
                        var45_18 = 0;
                        InteractionUtil.clickSlot$default(var49_14, var45_18, var44_17, var43_16, var42_9, var41_5);
                        AutoPotionBrew.delayTimer.reset();
                        return;
                    }
                    v4 = Potions.WATER.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v4, "value(...)");
                    if (this.countPotionsInStand(v4)) {
                        v5 = Items.NETHER_WART;
                        Intrinsics.checkNotNullExpressionValue(v5, "NETHER_WART");
                        if (this.findItemSlot(v5) != -1 && !Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(3).getStack().getItem(), Items.NETHER_WART)) {
                            v6 = Items.NETHER_WART;
                            Intrinsics.checkNotNullExpressionValue(v6, "NETHER_WART");
                            var41_6 = 3;
                            var42_10 = v6;
                            this.insertIngredient(var42_10, var41_6);
                            return;
                        }
                    }
                    v7 = Potions.AWKWARD.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v7, "value(...)");
                    if (this.countPotionsInStand(v7) && this.findItemSlot(this.getMode().e()) != -1 && !Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(3).getStack().getItem(), this.getMode().e())) {
                        var52_19 = 3;
                        var53_20 = this.getMode().e();
                        this.insertIngredient(var53_20, var52_19);
                        return;
                    }
                    v8 = Potions.NIGHT_VISION.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v8, "value(...)");
                    if (this.countPotionsInStand(v8) && !Intrinsics.areEqual(this.getMode().D(), Items.AIR) && this.findItemSlot(this.getMode().D()) != -1 && !Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(3).getStack().getItem(), this.getMode().D())) {
                        var57_21 = 3;
                        var58_22 = this.getMode().D();
                        this.insertIngredient(var58_22, var57_21);
                        return;
                    }
                    v9 = Potions.STRENGTH.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v9, "value(...)");
                    if (this.countPotionsInStand(v9)) ** GOTO lbl-1000
                    v10 = Potions.SWIFTNESS.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v10, "value(...)");
                    if (this.countPotionsInStand(v10)) ** GOTO lbl-1000
                    v11 = Potions.SWIFTNESS.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v11, "value(...)");
                    if (this.countPotionsInStand(v11)) ** GOTO lbl-1000
                    v12 = Potions.INVISIBILITY.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v12, "value(...)");
                    if (this.countPotionsInStand(v12)) lbl-1000:
                    // 4 sources

                    {
                        v13 = 1;
                    } else {
                        v13 = 0;
                    }
                    var48_12 = v13;
                    var49_14 = 1;
                    if (var48_12 != 0 && !Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(3).getStack().getItem(), this.getMode().Q())) {
                        if (this.findItemSlot(this.getMode().Q()) != -1) {
                            var62_23 = 3;
                            var63_24 = this.getMode().Q();
                            this.insertIngredient(var63_24, var62_23);
                            return;
                        }
                        var49_14 = 0;
                    }
                    v14 = Potions.STRONG_STRENGTH.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v14, "value(...)");
                    if (this.countPotionsInStand(v14)) ** GOTO lbl-1000
                    v15 = Potions.STRONG_SWIFTNESS.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v15, "value(...)");
                    if (this.countPotionsInStand(v15)) ** GOTO lbl-1000
                    v16 = Potions.LONG_FIRE_RESISTANCE.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v16, "value(...)");
                    if (this.countPotionsInStand(v16)) ** GOTO lbl-1000
                    v17 = Potions.LONG_INVISIBILITY.comp_349();
                    Intrinsics.checkNotNullExpressionValue(v17, "value(...)");
                    if (this.countPotionsInStand(v17)) lbl-1000:
                    // 4 sources

                    {
                        v18 = true;
                    } else {
                        v18 = var50_25 = false;
                    }
                    if (var48_12 != 0 && var49_14 == 0) {
                        var50_25 = true;
                    }
                    if (!var50_25) break block21;
                    if (!this.getAddGunpowder()) ** GOTO lbl-1000
                    v19 = Items.GUNPOWDER;
                    Intrinsics.checkNotNullExpressionValue(v19, "GUNPOWDER");
                    if (this.findItemSlot(v19) != -1 && !this.hasPotionsInStand()) {
                        if (!Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(3).getStack().getItem(), Items.GUNPOWDER)) {
                            v20 = Items.GUNPOWDER;
                            Intrinsics.checkNotNullExpressionValue(v20, "GUNPOWDER");
                            var41_7 = 3;
                            var42_11 = v20;
                            this.insertIngredient(var42_11, var41_7);
                            return;
                        }
                    } else lbl-1000:
                    // 2 sources

                    {
                        this.collectPotions();
                        return;
                    }
                }
                if (var47_2.getBrewTime() <= 0 || AutoPotionBrew.standPos == null) break block22;
                Globals.getPlayer().closeHandledScreen();
                v21 = AutoPotionBrew.standPos;
                Intrinsics.checkNotNull(v21);
                AutoPotionBrew.h.put(v21, Globals.getPlayer().age + var47_2.getBrewTime());
                break block22;
            }
            if (this.getOpen()) {
                for (BlockEntity var48_13 : ServerInfo.INSTANCE.V()) {
                    if (!(var48_13 instanceof BrewingStandBlockEntity) || !(Globals.getPlayer().squaredDistanceTo(((BrewingStandBlockEntity)var48_13).getPos().toCenterPos()) <= Globals.getPlayer().getBlockInteractionRange() * Globals.getPlayer().getBlockInteractionRange())) continue;
                    if (AutoPotionBrew.h.get(((BrewingStandBlockEntity)var48_13).getPos()) != null) {
                        v22 = Globals.getPlayer().age;
                        v23 = AutoPotionBrew.h.get(((BrewingStandBlockEntity)var48_13).getPos());
                        Intrinsics.checkNotNull(v23);
                        if (v22 - ((Number)v23).intValue() <= 0) continue;
                    }
                    if (Globals.getPlayer().age % 10 != 0) continue;
                    var49_15 = new BlockHitResult(new Vec3d((double)((BrewingStandBlockEntity)var48_13).getPos().getX() + 0.5, (double)((BrewingStandBlockEntity)var48_13).getPos().getY() + 0.125, (double)((BrewingStandBlockEntity)var48_13).getPos().getZ() + 0.5), Direction.UP, ((BrewingStandBlockEntity)var48_13).getPos(), false);
                    v24 = var49_15.getPos();
                    Intrinsics.checkNotNullExpressionValue(v24, "getLocation(...)");
                    RotationManager.INSTANCE.submit(new RotationRequest(Rotation.Companion.to(v24), (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, onPlayerUpdate$lambda$0(net.minecraft.util.hit.BlockHitResult net.minecraft.block.entity.BlockEntity ), ()Lkotlin/Unit;)((BlockHitResult)var49_15, (BlockEntity)var48_13), null));
                }
            }
        }
    }

    private final void collectPotions() {
        int n = AutoPotionBrewMode.zkmIntConstant();
        for (int i = 0; i < 3; ++i) {
            if (!delayTimer.hasPassed(this.getDelay())) continue;
            AutoPotionBrewAfterCompleteMode autoPotionBrewAfterCompleteMode = this.getAfterComplete();
            AutoPotionBrewAfterCompleteMode autoPotionBrewAfterCompleteMode2 = AutoPotionBrewAfterCompleteMode.TAKE;
            if (n == 0) {
                if (autoPotionBrewAfterCompleteMode == autoPotionBrewAfterCompleteMode2) {
                    InteractionUtil.clickSlot$default(i, 0, SlotActionType.QUICK_MOVE, false, 8, null);
                    if (n == 0) continue;
                }
                autoPotionBrewAfterCompleteMode = this.getAfterComplete();
                autoPotionBrewAfterCompleteMode2 = AutoPotionBrewAfterCompleteMode.THROW;
            }
            if (autoPotionBrewAfterCompleteMode != autoPotionBrewAfterCompleteMode2) continue;
            InteractionUtil.clickSlot$default(i, 0, SlotActionType.THROW, false, 8, null);
            if (n == 0) continue;
        }
    }

    private final boolean hasPotionsInStand() {
        for (int i = 0; i < 3; ++i) {
            if (Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(i).getStack().getItem(), Items.SPLASH_POTION)) continue;
            return false;
        }
        return true;
    }

    private final boolean countPotionsInStand(Potion potion) {
        for (int i = 0; i < 3; ++i) {
            ItemStack itemStack;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().currentScreenHandler.slots.get(i).getStack(), "getItem(...)");
            PotionContentsComponent potionContentsComponent = itemStack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContentsComponent != null && potionContentsComponent.comp_2378().isPresent()) {
                Potion potion2;
                Intrinsics.checkNotNullExpressionValue(potionContentsComponent.comp_2378().get().comp_349(), "value(...)");
                if (Intrinsics.areEqual(potion2, potion)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    private final int findPotionInInventory(Potion potion) {
        for (int i = 5; i < 41; ++i) {
            Potion potion2;
            ItemStack itemStack;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().currentScreenHandler.slots.get(i).getStack(), "getItem(...)");
            PotionContentsComponent potionContentsComponent = itemStack.get(DataComponentTypes.POTION_CONTENTS);
            if (potionContentsComponent == null || !potionContentsComponent.comp_2378().isPresent()) continue;
            Intrinsics.checkNotNullExpressionValue(potionContentsComponent.comp_2378().get().comp_349(), "value(...)");
            if (!Intrinsics.areEqual(potion2, potion)) continue;
            return i;
        }
        return -1;
    }

    private final void insertIngredient(Item item, int n) {
        int n2;
        if (delayTimer.hasPassed(this.getDelay()) && (n2 = this.findItemSlot(item)) != -1) {
            InteractionUtil.clickSlot$default(n2, 0, null, false, 14, null);
            InteractionUtil.clickSlot$default(n, 1, null, false, 12, null);
            Object var14_4 = null;
            int n3 = 14;
            boolean bl = false;
            SlotActionType slotActionType = null;
            int n4 = 0;
            InteractionUtil.clickSlot$default(n2, n4, slotActionType, bl, n3, var14_4);
            delayTimer.reset();
        }
    }

    private final boolean hasFuel() {
        return Globals.getPlayer().currentScreenHandler.slots.get(4).getStack().getItem() instanceof AirBlockItem;
    }

    private final int findItemSlot(Item item) {
        int n;
        block3: {
            int n2 = AutoPotionBrewMode.zkmIntConstant();
            for (int i = 5; i < 41; ++i) {
                n = Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(i).getStack().getItem(), item) ? 1 : 0;
                if (n2 == 0) {
                    int n3;
                    if (n2 == 0) {
                        if (n == 0) continue;
                        n3 = i;
                    }
                    return n3;
                }
                break block3;
            }
            n = -1;
        }
        return n;
    }

    private static final Unit onPlayerUpdate$lambda$0(BlockHitResult blockHitResult, BlockEntity blockEntity) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
        standPos = ((BrewingStandBlockEntity)blockEntity).getPos();
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoPotionBrew.a = ZkmKeys.a(7132504836130794636L, 4528798078840085108L, MethodHandles.lookup().lookupClass()).a(126132845364236L);
                        var20 = AutoPotionBrew.a ^ 32043176750870L;
                        AutoPotionBrew.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[28];
                        var16_4 = 0;
                        var15_5 = "\u009b\u0013\u00814\u000b1\u001d:T\u00c8P\u008a\u008d\"0\u009a7\u00a4\u0093\u00fd\u0095`\u00e8\u009a8zR~\u0093\u00dc&Z\u0003\u00aeT\u00e5\u00cb\u0081\u00c3N\u00fe\u00b4;\u00a4n\u009f\u00cb\u0096\u00a0\u00e3DP\u00af;+}\u008dc\u00cd\u000f\r\u00c6\u00e4\u000e\u00e3A\u0080\u00a1\u00a6o5V\u0018g9\u001f\u00e9:\u00f4k\u00e7('\u009c\u008b>\u00e8H\u0015rF\u0006\u008cP$;\u00a4(\u00db\u00edJkF\u00b4v\u00a1P\u00a4\u00d0z7oc(\u00a8\u00ed\u00a2\u00c7\u0087\u00ab\u00b5\u00ac\u0018\u0086?i\u008b\u00cb\u00e4\u00d6w\u00e9\u00b9^\u00cc\u00c0\u0087g\u001f\u00fa2^J\u00ec')+Hd\u00db\u0092\u0086\u0086\u00847\u0013\u00cdJ\u000b\u0097:X\u0012\u00abu\u0000e\u00e1\u000e\u00a7g\u0098Fm\u0088\u00d5\u00d4\u000e\u00d6\u00e7vw\u00b4D;\u00b2\u00f5\u00a3]\u00c0\u00a1\u00b3\u00f4\u00a3@\u00b1\u00c7\u0085\u00ef\u00d4n8+\u00f1 \u0004\u009f\u008b\u00a0\u0091\u00d2}\u0007!#\u0018_\u0004\u000e\u00f7 ^N\u00aa\u0013Y\u0087\u00a8\u00a1\u00d2\u000e\u00b2\u008d\u00f6\u0005\u0010\u00f2u\u009d\u00a9\u007f\u00cb\u0019\u00e8OK\u00a3\u00d9*a\u009c\u0001\u0094\u0010\u0000\u00854\u008f\u00f9Dw;5\u009d\u00e4\u00d1\tK\u00bf\u0081 {s\u00e9;\u00daq\u001an\u00a3 \u007f.\u007f\u00db\u00d6\u00c9\u0094\u0083\u0097Bo\u0003\u0007\u00e4s\u0015\u00c4\u008b\u00e0\u00d1\u00af\u00a7 \u00a8\u00ffk\u00a0\u00e3\u00f8Hp\u00c2\u009f\u00bfL\u007fx*\bW\u00ba\u0003e\u0007\u00ae\u00e9x>\u00b1\u00ecS\nc\u00ae+\u0018\u0013\u009b\u00d9\u00c3\n\u00ac;\u00de\u00c3\u000e\u00f31\u009c\u0011\u008a[P\u00f1G59\u00a2\u00e2\u00e5\u0018W\u00bfh\u00ca:1\u00fb\u0086\u0093\u0097D\u0096\u00ec\u0087\fz\u00de\u0007u\u00d2H\u00d8J\u00a3 \u00d75\u00bdK!\u0094\u00b6.\u0019Dd$DGW\u0015\u00a0\u00b98SC\u0098k\u0093\u0001\u00af\u0019l\u0001J\u0017Q =v\u00c7#7V\u009c\u00a5\u00d4\u00a40\u0087j*\u009f7\u00d6\u0097\u0017<\t\u0087\u0013\u00c2\u00cb\u00f6\u00cd\u00cfe\u000f\u009c8 \u0017\u00dbf\u0080g\u008c2\u0006\u0097\u00b5\u0004\r\u00fe}M\u00e8X~{\u00b5\u007fJ\u00ebb{\u00a1u\u0095f:B\u009e\u0018\u00d9\u00cfm\u00fb\u00ccGjq\u00a6`>\u00aa\u00bb^\u0018pW\u00cc\u0003\u0016\u0095\u001e\u000e`\u0010\u0091n\u00a5C\u0099\u0097\u00c5\u0084\u00a6\u009fg\u008a\u008a8lS\u0010\u00ea\u00f8\u00019\u008b\u00bfh\u00de\u00d4S\u00c9\u00eb\u00af\u00d2\u0001_\u0010\u0080\u00a7\u00be;q\u00ac\u0001R\u00d7\u0005\u009az\u00c7\u00c2\u00e4  \u00f0mtm\u00c7\u00e6#\u0014\u00bcB\u0085aj\u00bb[\u00b2?\u00dch\u0000\u00cc\u001f/\u00a5\u00f2\u00fd%yx\u0011\u0015\u0098 \u00fec$C\u00c3#\u00b4\r\u00a4\u008dIqb\u000b,\u00a5]\n\u001f*\u0003\u00c6\u0019g\u00c0Wh\u00a2\u00b1\u0081\"S()h_\u008bh\u0002t\u00ce\u001em.\u00bd\u00e3\u00caspg\u00e7\u00b4\t\u00c3\u0090\u0088\u00ab\u00d5\u00fd\u008d;\u00cbj\u00be\u008d\u0081\u00d0m\u00a8\\\u00e0\u009e\u0093 \u00e6\u0097\u00af\u0007\u00fc\u00c8cW=\u00f7\u0005\u00fai\u00b5\"5\u0085\u009c\u00bevUp\u0093\u00ee*q\u0080\u00a51\u00d8+\u00c1 ?3\u00d4|5.\u00faF\u00ce\u00bb7\u00f6\u00ae\u00a0\u001f\u0010\u00fa\u00b1\u00ea\u00ddQ\u00d6Z\u0006B(\u00c0-,\u00ce9\u0011(\u00cb\u00af\u00dd\u00fc\u00d8\u00fa5A\u0082'oC\u00d8\u00cc\u00f7\u00fa,\u00f0\u00e9A\u0092.\u00b3\u00fd\"\u00f3\u00ae\u00dd\u00bc<\u00d50E\u00e7E\u00cd\u00930^l\u0010\u007f\u00a7\u00de\u0004\u00e8sH\\M\u00033\u00e5E\u00c8\u001dq\u0010\u0006\u00ba\u00c3\u000fN\u00fd+7GU/X(\u00cc/\u00f4";
                        var17_6 = 809;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = AutoPotionBrew.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "R\u00d8p}JH\u008b\u00dac\u0014\u00c3\u0006Z\u008d\u0013\u009dSA\u00d76l\u000f\u00b3\u00a5\u0081\u009d\u00b1yg\u00a2\u008f\u00fb/l2kU\u00a2]\u00ab\u001ac?d\u0095{\u00a5 V\u00d2::Ymy\fk)\u00c9\u00ae\u00b7\t\u00f3\u008fa\u00cc?n>\u00e2\u00cd\u00bc\u00e1\u00e5Vd\u00ba\u00fba\u00cc!v\u00b2\u00eb\u00ee\u00ea3\u00ce\u0017\u0093\u0000\u009a(\u00a2\u0088\t\u00ab\u0082\u00dc\u008f\u00c5%h9\u0081\u00dd\b\u00c9B\u00fd\ngU\u00ee\u008f\u00fc\u00b8\u00d9\u00d2?\u00011f\u00b4\u0012\u00d7T\u00d0\u0018\u0093$\u0019;E\u00a2^\u00c9+6\u009f\u00d8\u00e5\\\u00eah\u00a5\u008b\u00e1\u00ba\u00d0\u0006\u0081U";
                            var17_6 = 153;
                            var14_7 = 128;
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
                            var18_3[var16_4++] = AutoPotionBrew.zkmDecodeUtf8(var19_9).intern();
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
                AutoPotionBrew.b = var18_3;
                AutoPotionBrew.c = new String[28];
                AutoPotionBrew.j = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "\u00ceH\u00b54t\u00e9\u0019R\u00f9\u00d3\u0014\u0097\u009cM7\u0083\u00cb\u001c\u000e\u00e8@#j\u00c6s\u0082(\u00db\u0094p>Bv\u00ca\bS\u0096\u0017\u00a9\u00aa\u00c3\u00d2\u00d4l\u001f\u009c\u0099C\u008c\n\u00e8\u00dd\u00e6\u00b6\u00c3)x\u00b4eO\u00e6\u00e4\u00b4\u0091/\u00a7\u0083\u00d0\u00c7\u00c0%H\u0000\u00fa\u001esf\u00dbP\u00bd";
                var5_15 = 80;
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
                    var4_14 = "\u00e2{\u00e2\u001e\u00f1\u0082\u00f4\u00f2\u00f3A\u00e5\u000b\u00d8G[\u00d0";
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
        AutoPotionBrew.f = var6_12;
        AutoPotionBrew.g = new Integer[12];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoPotionBrew.class, "mode", "getMode()Lsu/catlean/m0;", 0)), Reflection.property1(new PropertyReference1Impl(AutoPotionBrew.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoPotionBrew.class, "addGunpowder", "getAddGunpowder()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoPotionBrew.class, "afterComplete", "getAfterComplete()Lsu/catlean/mz;", 0)), Reflection.property1(new PropertyReference1Impl(AutoPotionBrew.class, "open", "getOpen()Z", 0))};
        AutoPotionBrew.delegatedProperties = var30_20;
        AutoPotionBrew.INSTANCE = new AutoPotionBrew();
        AutoPotionBrew.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoPotionBrew.INSTANCE, "potion", (Enum)AutoPotionBrewMode.STRENGTH, null, null, 12, null);
        AutoPotionBrew.delay$delegate = SettingFactory.intSetting$default(AutoPotionBrew.INSTANCE, "delay", 100, new IntRange(0, 1000), null, null, 24, null);
        AutoPotionBrew.addGunpowder$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoPotionBrew.INSTANCE, "throwable", true, null, null, 12, null);
        AutoPotionBrew.afterComplete$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoPotionBrew.INSTANCE, "after-complete", (Enum)AutoPotionBrewAfterCompleteMode.TAKE, null, null, 12, null);
        AutoPotionBrew.open$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoPotionBrew.INSTANCE, "open", true, null, null, 12, null);
        AutoPotionBrew.delayTimer = new Timer();
        AutoPotionBrew.h = new LinkedHashMap<K, V>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3378;
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
                throw new RuntimeException("su/catlean/q0", exception);
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
            AutoPotionBrew.c[n2] = AutoPotionBrew.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoPotionBrew.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x543C;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/q0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoPotionBrew.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoPotionBrew.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoPotionBrew.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoPotionBrew.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

