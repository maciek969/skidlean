/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.world.AutoBoneMeal$onPlayerUpdate$1;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ItemPredicate;
import su.catlean.util.PlaceManager;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class AutoBoneMeal
extends Module {
    @NotNull
    public static final AutoBoneMeal INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final SettingGroup group$delegate;
    @NotNull
    private static final BooleanSetting wheat$delegate;
    @NotNull
    private static final BooleanSetting carrot$delegate;
    @NotNull
    private static final BooleanSetting potato$delegate;
    @NotNull
    private static final BooleanSetting melon$delegate;
    @NotNull
    private static final BooleanSetting pumpkin$delegate;
    @NotNull
    private static final BooleanSetting beetRoot$delegate;
    @NotNull
    private static final TickTimer tickTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] h;
    private static final Map i;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    private AutoBoneMeal() {
        super("auto-bone-meal", Categories.getWorld(), null, null);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        switchMode$delegate.setValue(this, delegatedProperties[0], (Object)swapMode);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final void setDelay(int n) {
        delay$delegate.setValue(this, delegatedProperties[1], n);
    }

    private final SettingParent getGroup() {
        return (SettingParent)group$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getWheat() {
        return (Boolean)wheat$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setWheat(boolean bl) {
        wheat$delegate.setValue(this, delegatedProperties[3], bl);
    }

    private final boolean getCarrot() {
        return (Boolean)carrot$delegate.getValue(this, delegatedProperties[4]);
    }

    private final void setCarrot(boolean bl) {
        carrot$delegate.setValue(this, delegatedProperties[4], bl);
    }

    private final boolean getPotato() {
        return (Boolean)potato$delegate.getValue(this, delegatedProperties[5]);
    }

    private final void setPotato(boolean bl) {
        potato$delegate.setValue(this, delegatedProperties[5], bl);
    }

    private final boolean getMelon() {
        return (Boolean)melon$delegate.getValue(this, delegatedProperties[6]);
    }

    private final void setMelon(boolean bl) {
        long l = 0L;
        l = a ^ l;
        melon$delegate.setValue(this, delegatedProperties[AutoBoneMeal.zkmBootstrap2("e", (int)31127, (long)(0x6CD2738769671874L ^ l))], bl);
    }

    private final boolean getPumpkin() {
        return (Boolean)pumpkin$delegate.getValue(this, delegatedProperties[7]);
    }

    private final void setPumpkin(boolean bl) {
        long l = 0L;
        l = a ^ l;
        pumpkin$delegate.setValue(this, delegatedProperties[AutoBoneMeal.zkmBootstrap2("e", (int)21844, (long)(0x2BC11EF046970ADAL ^ l))], bl);
    }

    private final boolean getBeetRoot() {
        return (Boolean)beetRoot$delegate.getValue(this, delegatedProperties[8]);
    }

    private final void setBeetRoot(boolean bl) {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
        beetRoot$delegate.setValue(this, delegatedProperties[AutoBoneMeal.zkmBootstrap2("e", (int)27488, (long)(0x34B8255D968F0835L ^ l))], bl);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block5: {
            var2_2 = AutoBoneMeal.a ^ 56380136734396L;
            var21_3 = var2_2 ^ 59946940147052L;
            if (!AutoBoneMeal.tickTimer.hasPassedAndReset(this.getDelay())) break block5;
            var30_4 = PlaceManager.INSTANCE.findReachableBlocks();
            var32_5 = var30_4;
            var33_6 = new ArrayList<E>();
            for (T var36_8 : var32_5) {
                var37_9 = (BlockPos)var36_8;
                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(var37_9), "getBlockState(...)");
                if (!AutoBoneMeal.INSTANCE.getTargetBlocks().contains(var39_10.getBlock()) || !(Globals.getPlayer().getEyePos().distanceTo(var37_9.toCenterPos().add(0.0, 0.4375, 0.0)) < Globals.getPlayer().getBlockInteractionRange())) ** GOTO lbl-1000
                v0 = var39_10.getBlock();
                Intrinsics.checkNotNull(v0, "null cannot be cast to non-null type net.minecraft.world.level.block.CropBlock");
                v1 = ((CropBlock)v0).getAge(var39_10);
                v2 = var39_10.getBlock();
                Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type net.minecraft.world.level.block.CropBlock");
                if (v1 != ((CropBlock)v2).getMaxAge()) {
                    v3 = true;
                } else lbl-1000:
                // 2 sources

                {
                    v3 = false;
                }
                if (!v3) continue;
                var33_6.add(var36_8);
            }
            var30_4 = (List)var33_6;
            var32_5 = var30_4;
            var33_6 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var30_4, 10));
            for (T var36_8 : var32_5) {
                var37_9 = (BlockPos)var36_8;
                var40_11 = var33_6;
                var40_11.add(new BlockHitResult(var37_9.toCenterPos().add(0.0, 0.4375, 0.0), Direction.UP, var37_9, false));
            }
            var30_4 = (List)var33_6;
            var29_12 = CollectionsKt.sortedWith(var30_4, new AutoBoneMeal$onPlayerUpdate$1());
            var30_4 = InventoryUtil.INSTANCE.findItem(var21_3, (ItemPredicate)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/item/ItemStack;)Z, onPlayerUpdate$lambda$0(net.minecraft.item.ItemStack ), (Lnet/minecraft/item/ItemStack;)Z)(), this.getSwitchMode());
            if (var29_12.isEmpty() || !var30_4.isFound()) {
                return;
            }
            var31_13 = (BlockHitResult)CollectionsKt.first(var29_12);
            v4 = var31_13.getPos();
            Intrinsics.checkNotNullExpressionValue(v4, "getLocation(...)");
            RotationManager.INSTANCE.submit(new ActionRotationRequest(Rotation.Companion.to(v4), (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, onPlayerUpdate$lambda$1(su.catlean.util.SlotResult net.minecraft.util.hit.BlockHitResult ), ()Lkotlin/Unit;)((SlotResult)var30_4, (BlockHitResult)var31_13), null));
        }
    }

    private final List getTargetBlocks() {
        List list = new ArrayList();
        Object var16_2 = null;
        if (this.getWheat()) {
            Block block = Blocks.WHEAT;
            Intrinsics.checkNotNullExpressionValue(block, "WHEAT");
            list.add(block);
        }
        if (this.getCarrot()) {
            Block block = Blocks.CARROTS;
            Intrinsics.checkNotNullExpressionValue(block, "CARROTS");
            list.add(block);
        }
        if (this.getPotato()) {
            Block block = Blocks.POTATOES;
            Intrinsics.checkNotNullExpressionValue(block, "POTATOES");
            list.add(block);
        }
        if (this.getPumpkin()) {
            Block block = Blocks.PUMPKIN_STEM;
            Intrinsics.checkNotNullExpressionValue(block, "PUMPKIN_STEM");
            list.add(block);
        }
        if (this.getMelon()) {
            Block block = Blocks.MELON_STEM;
            Intrinsics.checkNotNullExpressionValue(block, "MELON_STEM");
            list.add(block);
        }
        if (this.getBeetRoot()) {
            Block block = Blocks.BEETROOTS;
            Intrinsics.checkNotNullExpressionValue(block, "BEETROOTS");
            list.add(block);
        }
        return list;
    }

    private static final boolean onPlayerUpdate$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.BONE_MEAL);
    }

    private static final Unit onPlayerUpdate$lambda$1$lambda$0(BlockHitResult blockHitResult) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        return Unit.INSTANCE;
    }

    private static final Unit onPlayerUpdate$lambda$1(SlotResult slotResult, BlockHitResult blockHitResult) {
        Function0<Unit> function0 = () -> AutoBoneMeal.onPlayerUpdate$lambda$1$lambda$0(blockHitResult);
        SwapMode swapMode = INSTANCE.getSwitchMode();
        int n = slotResult.getSlot();
        InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
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
                        AutoBoneMeal.a = ZkmKeys.a(-4809943490066907250L, 2188418459199564833L, MethodHandles.lookup().lookupClass()).a(70429097109631L);
                        var20 = AutoBoneMeal.a ^ 40399835436009L;
                        AutoBoneMeal.i = new HashMap<K, V>(13);
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
                        var18_3 = new String[39];
                        var16_4 = 0;
                        var15_5 = "Y\u0083\u00d2\u001b\u00a4\u00c9\u0091Z\u00ce\u00c1$8\u00b8\u00f8\u00e1_h\u0010\u0090\u00dc\u008d,\u00f9M\u00c3\u008e\u00aa\\\u00f4\u008bUa8l\u0017\u00e9\u0001\u0017\u000b\u0096\u0013(\u00abo&\u0095?<\u00bb\u00d2z\u0090\u00e25\u009e\u00b3\u00e7\u0016\u00dd\u0085y\u0090\u00d4|\u0006\u00cc\u007fxS\u00f5\u00c4\u00e1\u0093\u00b6\u00a3S\u0093\u00c3\u00ae\u00849\u00ccp\u0087\u008b\u009f\u00cbHI\u0018\u00f9\u00c4\u0087\u0082\u00f2\u00995\u0018\u00cdGO\u00c1Y\u00b2\u00c3\u0083\u0084\u00c3\u0082\u0015b\u00d2\u00e2\u00bd\u0018\u00c8l}\u0000\u000b\u0016\u00cb=]\u00ca1\u00ed&\u00bbb\u008cG\u0086}\u0007\u00a4\u00d5&\u0097\u0010\u00cdK\f\u00cfMN\u00b3\u0011\u00dch\u00a6\u000b^K8p(\u00e4\u00f6g\u0094\u00ac\u00d3(\u001e\u00a5+\u009fy\u00bd<\u008f\u00b4\u0011\u009dr\u00dc<\u00a83\u00e3\u00ad\u009d\u00e0\u00a6\u00c3\u00a1d\u0006\u00e7N\u00006\u00021\u00b9\u00b8\u0010\u008a\u00d1\u007f\u00fa\u000f\u00c1\u0001\u00a1\u00df\u001f\nx\u00b8\u0083\u00ff\u00bf \u00a0\u00c5\u00a3\u001c\u0013\u0092\u00c3\u000e\u00c1\u0002\t\f\u00e1\u001d\u0007\u00eaQ\u009a,\u00df]\u00b6\u00e3\u0082\u00a4\u00979>HC\u00f7\u00d4({\u00e7\u00e5\u00acj\u0014\u008e\u0006P\u00d0\u00b5\u00bb\u00e6\u00ba8\u00f4;X\u00a5OndWBtA\u00ce\u00c3\u0011\u00899\u00dd7\u0095\u00d5\u00e1\b\u009e\u00d2?\u0010b\u000f;\u001d\u00d4\u0086\u00be\u008c?\u00e4p\u000e\u0081\u0014\r\u00cd\u0010\u00f3\u00ae\u00a5\u009a\u00a7\"\u0004Tj\u00a5\u00f0\u00d4\u008d~\u00b7! \u00abQ\u00c3\u00e4,\u00a4Z7EpOM\u00c2\u000f\u00b8\u0093\u009f\u00f0\u0005N\u000f\u0089\u0096\u0017\u00ccx\u008e\u0005\u0094\u00ca\u00f4? g\u008b\u008c\u008e\u009b(4\u009eS\u00e0 jmi?\u00c8\u0090\u00b9\u001a\u00cf\u007fW\u00e1\u00b0\u00f4\u00bd\u00d4\u00c0B\u0084\u00e7h\u0010\u00e8E:1i\u009c;\u00f2\u008a>\u00e6\rt\u0006\u00e8\u00ec 1\u0014\u0019\n0\u00c7U\u00a6\u00aff\u0089\u00bf:\u0098\u00baP\u00e8@\u00af^5/\u00cf\u00cb\u008b[\u00d8P\u00b5A\u00beh\u0010V\u0091\u00aa1\u000f!H\u00a4\u00b8\u00b3S\u0017\u0001mfa\u0010\u009ep\u00c6\u00f0:(\u00c1\u0004B)\u00baXq\u0088|\u0081\u0080\u001fw\u008aM\u00b6\u009a\u0015\u00e6\u00809\\*\u00baV\u00d9\u00fcH\u0016\u008b\u00b2LJ\u00e23\u00dc\u00d7\u00b2\u00b4|\u00dc9kk`\u0094[\u000e  \u0096u\"\u00b1J)z\u008dsk\u00aeT\u0002\u00fd\u0003C[\u00ef\u00b2\u0010\u00a4=\u00ac7J\u00e2\u00f9=\u008e\u0019(>\u00e6\u00b3\u00e5\u0092\u0000)eq\u00fa\u00c4d\u0087\u001c\u00ee\u0013\u00d8\u00c1s\b05\u0085\u00a3\u00aa\u0089\u00bc\u0082s\u0087C%0`\u0087\u0017y\u000b<\u0004KAs!\u0086\u000b\u00ca\u0001\u00d5\u00c4\u00c2\u000e\u00e0\u00efP\f\u00fbQ\u0010\u00ac\u0086\u00a9\u00cf\u00e3\u00cd\u0000\u00d6b\r\u00bd\u0096/{\u00b0\u00ca\u0010\u0006\u00d9\u0012\u008d\u00ab2\u00f4\u00b4\u00b9\u00bc=!\u00bb\u00d8\u0002\u00c90n\u00bdr\u00bey\u0001\u00c8V\u00f5\u008a\u0085\u0001\u00f6K\u008d\u00d1v\u007f\b\u0014\u00a5\u0093/\t\u00d4\u00f5\u00bd\u0014\u0019\u0000i\u0004f\u00c0\u00f7\u00ecd\u00afM\u00e5\u00da\u00a3\u00a8B,\u00edY\u00e3\u0010\u00ab\u0018A\u00db\u00fdr\u00fd\u00fbH\u00aas\u00f7\u00b2f\u00d1\u00c9\u0018jy\u0095\u0094\u0080\u00f4\u00a8\u009b\u00ef\u0082\u00d3\u0087<\u008f\u0089\u009a\u00ed\u00e6\u00d4\u00152\u00aa&m\u0010}$\u00bc\u00f6\u00d2\u009a\u00e4\u00ae\u00e6,\bN\u008a\u00fc\u00ab\u00cc\u0018~\u008ed\u00c9\u00a4\u00a6lM\u00f9q\u009f`\u00c0pL\u00e3#\u0088\u00a6~\u0006m\u008fi\u0010\u00f9\u00173\u00da\u0002*\u00ba\u00c9\u00dd\f\u0011\u008f\u0097X\u00b4\u0006x\u0001\u00bb\u0092'\u00abc\u008cQnu\u001fB\u00c1\u00e6\u00a2O\u0007\u0005\u001b\u000b\u00d0\u0006\u00b0\u00db\u0099\u0013W\u00a90\u00d0\u0019=\u001f\u00ee\u0089\u00f3\u00cf\u009f\u00ee\u009d\u00bd\u00b2\u00dd\u00d8\u00a1\u00de\u000e_\u00f4\u0086\u00f1\u00b5f\u00b1\u0096\u00f9e^\u00e8\u0001\u00a7vW<_P@vV-\u0019\u00b3\u0011\n\u0094\u00fb\u00dbw.\u0083X\u00e46\u0005\u00e1n\u00d8>(\u008aa\u0083\u00b4RV\u008a!+\u00c6.\u00de\u00c0\"g\u00c9\u00a2\b:0qx\u00cd\u0019\u00e2\u00a7\u00e5(\u0092M\u00ab\u0010\u008b\u00c2\u0092\u001aN\u00a5\u0084\u00ec_\u00c3\u0089\u00e7\u00e3\u0014\u00f1\u000e\u0010J\u00c0T]\u00d1\u00b9\u00c8@\u00fe/@\u00c0\u00ac\u0098\u00e6\u00a7 [\u0011\u00af\u00f5\u0015\u001d\u0004\u00a8\u0093n\u00f2\u00c9\u00cc\u00be\u008b\u00ef\u00e4\u0005'\u00b2t&Z\u0097\u00b7\u0013Bp\u00a5\u0096_\u00cb\u0018\u0098{\u00dc\r\u008fCIfS\u00ae\u00aed\u00baf\u000b\u00cd6\u0003\n\u008a\u00f8\u0011\u00b7\u00ab\u0018\u00fe,~\u00ff\u0096k\u00be\u00b67x\u0084\u00d3T\u0010\u00e0\u0012S\u00e33\u00c2\u00a4q\u00a1\u00e6\u0018Qt\u00d0<\u00af\t\\\u001b\u00d9n\u00be\u00f5\u00ae-3@\u000e\u00f6Aq\u00eb:\u00ec\u0017\u0010\u00c1\u00eb\u00a4K\u0003\u000fIR\"\u00e6\u009e\u0083\u0097K\u001do\u0010\u0016aM|\u00bb5bN\u00fd\u00bb\u007fm\u00ac\u00ff\u0095\u009f \u00f9\u00adU\u00f2\u00fe\u00f4(T~o\u0004\u0081\u00f4\u00ad.\u00dbj\u0003\u00b2\u0092\u001er\u00a0\u008fZ9\u0090\u0017\u00caA\u00b9\u00b3\u0018\u00b7'R\u00d4\u008e\u00f1f\u00fc\u00a6+`8\u0083F\u00165\u009b\u0002a\u008c\u0090$\u000f\u00d5";
                        var17_6 = 1140;
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
                            var18_3[var16_4++] = AutoBoneMeal.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "d\u00a4\\\u0090FT5z7l\u00ad\u00bfz\u0013\u00b7\f\u0018\u00c4)u\u00e3*L\u008eZG{\u00aeU\u00e6^x`\u00e7\u0087~/4>\u0084=";
                            var17_6 = 41;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = AutoBoneMeal.zkmDecodeUtf8(var19_9).intern();
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
                AutoBoneMeal.b = var18_3;
                AutoBoneMeal.h = new String[39];
                AutoBoneMeal.l = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "T\u0002&y\u009b)\u00f7\u00e2\u00b8dn\u0019\u00f1\t62\u0002 \u00f6\u007f\u00e1\t\u00a5t\u0095\u0082\u000b\u00ac\u00fe\u008a\u00c3\u00d7I\u00be1\u00a6nk=\u00ca\u00b3\u0086\u00ff\u00b7\u0096\u00f9\u0005/\u00d2\u0083\u009a\u00bc\u00b9\u00fd\u00baxY\u0080Y\"\u00e9ID_e\u0080\u00bd\b\u0016\u0004\u00ce\u00c3";
                var5_15 = 72;
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
                    var4_14 = "\u00ed]\u00b4\u00c5\u00b9\u00c3Gf\f>\u00f3AU\u001a\u00df\u0095";
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
        AutoBoneMeal.j = var6_12;
        AutoBoneMeal.k = new Integer[11];
        var38_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoBoneMeal.class, "group", "getGroup()Lsu/catlean/h;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "wheat", "getWheat()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "carrot", "getCarrot()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "potato", "getPotato()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "melon", "getMelon()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "pumpkin", "getPumpkin()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBoneMeal.class, "beetRoot", "getBeetRoot()Z", 0))};
        AutoBoneMeal.delegatedProperties = var38_20;
        AutoBoneMeal.INSTANCE = new AutoBoneMeal();
        AutoBoneMeal.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
        AutoBoneMeal.delay$delegate = SettingFactory.intSetting$default(AutoBoneMeal.INSTANCE, "delay", 0, new IntRange(0, 10), null, null, 24, null);
        AutoBoneMeal.group$delegate = SettingFactory.settingGroup$default(AutoBoneMeal.INSTANCE, "selection", AuraTargetsMode.BOOLS, null, 4, null);
        AutoBoneMeal.wheat$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "wheat", true, AutoBoneMeal.INSTANCE.getGroup(), null, 8, null);
        AutoBoneMeal.carrot$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "carrot", true, AutoBoneMeal.INSTANCE.getGroup(), null, 8, null);
        AutoBoneMeal.potato$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "potato", true, AutoBoneMeal.INSTANCE.getGroup(), null, 8, null);
        AutoBoneMeal.melon$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "melon", false, AutoBoneMeal.INSTANCE.getGroup(), null, 8, null);
        AutoBoneMeal.pumpkin$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "pumpkin", false, AutoBoneMeal.INSTANCE.getGroup(), null, 8, null);
        AutoBoneMeal.beetRoot$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBoneMeal.INSTANCE, "beetroot", false, AutoBoneMeal.INSTANCE.getGroup(), null, 8, null);
        AutoBoneMeal.tickTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3D31;
        if (h[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e_", exception);
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
            AutoBoneMeal.h[n2] = AutoBoneMeal.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoBoneMeal.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x265;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])AutoBoneMeal.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    AutoBoneMeal.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e_", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoBoneMeal.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoBoneMeal.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoBoneMeal.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoBoneMeal.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

