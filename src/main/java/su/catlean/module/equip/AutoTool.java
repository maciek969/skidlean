/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.CalcBlockBreakingDeltaEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Async;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoTool
extends Module {
    @NotNull
    public static final AutoTool INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting silent$delegate;
    @NotNull
    private static final BooleanSetting swapBack$delegate;
    @NotNull
    private static final BooleanSetting saveItem$delegate;
    @NotNull
    private static final BooleanSetting echestSilk$delegate;
    private static int h;
    private static boolean U;
    private static long S;
    @NotNull
    private static final List previousSlots;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] j;
    private static final Map k;

    private AutoTool() {
        super("auto-tool", Categories.getEquip(), null, null);
    }

    private final boolean getSilent() {
        return (Boolean)silent$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getSwapBack() {
        return (Boolean)swapBack$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getSaveItem() {
        return (Boolean)saveItem$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getEchestSilk() {
        return (Boolean)echestSilk$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        if (!(Globals.getMc().crosshairTarget instanceof BlockHitResult)) {
            return;
        }
        HitResult hitResult = Globals.getMc().crosshairTarget;
        Intrinsics.checkNotNull(hitResult, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
        BlockHitResult blockHitResult = (BlockHitResult)hitResult;
        BlockPos blockPos = blockHitResult.getBlockPos();
        Intrinsics.checkNotNullExpressionValue(blockPos, "getBlockPos(...)");
        BlockPos blockPos2 = blockPos;
        if (Globals.getWorld().getBlockState(blockPos2).isAir()) {
            return;
        }
        if (this.findBestTool(blockPos2) != -1 && Globals.getMc().options.attackKey.isPressed()) {
            previousSlots.add(Globals.getPlayer().getInventory().getSelectedSlot());
            if (this.getSilent()) {
                InventoryUtil.INSTANCE.selectSlot(this.findBestTool(blockPos2));
            } else {
                Globals.getPlayer().getInventory().setSelectedSlot(this.findBestTool(blockPos2));
            }
            h = this.findBestTool(blockPos2);
            U = true;
            S = Globals.now();
        } else if (U && !((Collection)previousSlots).isEmpty() && Globals.now() >= S + 300L && (this.getSwapBack() || this.getSilent())) {
            if (this.getSilent()) {
                InventoryUtil.INSTANCE.selectSlot(((Number)previousSlots.get(0)).intValue());
            } else {
                Globals.getPlayer().getInventory().setSelectedSlot(((Number)previousSlots.get(0)).intValue());
            }
            h = ((Number)previousSlots.get(0)).intValue();
            previousSlots.clear();
            U = false;
        }
    }

    @Flow
    public final void onCalcBlockBreakingDelta(@NotNull CalcBlockBreakingDeltaEvent calcBlockBreakingDeltaEvent) {
        block3: {
            float f;
            float f2;
            block4: {
                float f3;
                block5: {
                    CalcBlockBreakingDeltaEvent calcBlockBreakingDeltaEvent2;
                    boolean bl;
                    block2: {
                        bl = Async.t();
                        calcBlockBreakingDeltaEvent2 = calcBlockBreakingDeltaEvent;
                        if (!bl) break block2;
                        Intrinsics.checkNotNullParameter(calcBlockBreakingDeltaEvent2, "event");
                        if (!this.getSilent()) break block3;
                        calcBlockBreakingDeltaEvent2 = calcBlockBreakingDeltaEvent;
                    }
                    f2 = f3 = calcBlockBreakingDeltaEvent2.getState().getHardness(Globals.getWorld(), calcBlockBreakingDeltaEvent.getBlockPos());
                    f = 0.0f;
                    if (!bl) break block4;
                    if (!(f2 < f)) break block5;
                    calcBlockBreakingDeltaEvent.setDelta(0.0f);
                    if (bl) break block3;
                    Module.zkmSetFlag(new Module[4]);
                }
                BlockState blockState = calcBlockBreakingDeltaEvent.getState();
                ItemStack itemStack = Globals.getPlayer().getInventory().getStack(h);
                Intrinsics.checkNotNullExpressionValue(itemStack, "getItem(...)");
                f2 = this.getToolScore(blockState, itemStack);
                f = f3;
            }
            float f4 = f2 / f;
            calcBlockBreakingDeltaEvent.setDelta(Globals.getPlayer().getInventory().getStack(h).isSuitableFor(calcBlockBreakingDeltaEvent.getState()) ? f4 / 30.0f : f4 / 100.0f);
            calcBlockBreakingDeltaEvent.cancel();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final float getToolScore(BlockState blockState, ItemStack itemStack) {
        double d;
        double d2 = itemStack.getMiningSpeedMultiplier(blockState);
        boolean bl = Async.t();
        RegistryKey<Enchantment> registryKey = Enchantments.EFFICIENCY;
        Intrinsics.checkNotNullExpressionValue(registryKey, "EFFICIENCY");
        int n = EnchantUtil.getEnchantmentLevel(itemStack, registryKey);
        double d3 = d2 - 1.0;
        double d4 = d3 == 0.0 ? 0 : (d3 > 0.0 ? 1 : -1);
        if (bl) {
            if (d4 <= 0) {
                d = 0.0;
                return (float)Math.max(d2 + d, 0.0);
            }
            d4 = n * n;
        }
        d = (double)d4 + 1.0;
        return (float)Math.max(d2 + d, 0.0);
    }

    private final int findBestTool(BlockPos blockPos) {
        int n = -1;
        float f = 1.0f;
        for (int i = 0; i < 9; ++i) {
            BlockState blockState;
            ItemStack itemStack;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(i), "getItem(...)");
            if (Intrinsics.areEqual(itemStack, ItemStack.EMPTY) || Globals.getPlayer().getInventory().getStack(i).getMaxDamage() - Globals.getPlayer().getInventory().getStack(i).getDamage() <= 10 && this.getSaveItem()) continue;
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos), "getBlockState(...)");
            RegistryKey<Enchantment> registryKey = Enchantments.EFFICIENCY;
            Intrinsics.checkNotNullExpressionValue(registryKey, "EFFICIENCY");
            int n2 = EnchantUtil.getEnchantmentLevel(itemStack, registryKey);
            float f2 = itemStack.getMiningSpeedMultiplier(blockState);
            if (blockState.getBlock() instanceof AirBlock) {
                return -1;
            }
            if (blockState.getBlock() instanceof EnderChestBlock && this.getEchestSilk()) {
                RegistryKey<Enchantment> registryKey2 = Enchantments.SILK_TOUCH;
                Intrinsics.checkNotNullExpressionValue(registryKey2, "SILK_TOUCH");
                if (!EnchantUtil.hasEnchantment(itemStack, registryKey2) || !((float)n2 + f2 > f)) continue;
                f = (float)n2 + f2;
                n = i;
                continue;
            }
            if (!((float)n2 + f2 > f)) continue;
            f = (float)n2 + f2;
            n = i;
        }
        return n;
    }

    private static final boolean settingVisible() {
        return !INSTANCE.getSilent();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoTool.a = ZkmKeys.a(6547727397543396106L, -6245381199100599443L, MethodHandles.lookup().lookupClass()).a(167113169816925L);
                        var20 = AutoTool.a ^ 85034676594000L;
                        AutoTool.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[22];
                        var16_4 = 0;
                        var15_5 = "4,\u009esS\u00cb\u00bdH9g\u0090\u00eb\u009d\u0089\u000b\u008cT(\u00af\u00f7\u0004\u000b\u000f\u00a1N\u00d3\u000fM\u00eb/\u00e0Cx\u00ae\u00b7\u00c1\u0093\u00f0\u00fe|\u009e\u00b0A\u00e1W\u0012\u00b92\u00e1\u00e9f\u00ff\\\u00fc\u000f\u00aexJ\u00f8\u00f4E\u0087Z\u0003K\u001d\u00e7\u0014eQ\u0013F\u00fa\u00bb\u0083\u00b0\u001d\u00d3\u00d9\u00ce\u00f5\u00deH\u00b9\u0018\u00b4\u000fV1\u00bc\u00bd\u00f5\f\u00a9\u00b9\u0098Z\u00ae;\u001b1\u0097\u0083\u00e5\u00e0\u00ac{\u001c\u001e6N*he\u00a3_>\u0081\u00a8\u00df<\u00ed\u007f\u00e8\u00ec\u00a7\u00e9\u00ab\u00ad\u00a9\u00ff\u00a1\u0019\u00f4\u00cdg\u001d\u00cdN\u0085\u00ce\u00949\u00d4xB\u0015\u00b9\u00d6|\u00e8\u00ba\u0013 \u00d9\u0094\u00ad\u00b9\u00c2]Q-\u0095\u00f6F\u0094\u00ed\u00cf9\u00a4\u0091\u00a7[\f'\u0000~^1GE\u00a1YZ\u00ad\u00dc\u0018\u00ab_<\u00ba\u00e8M\u00fe\u00a8\u0000\u00a3\u00d2l\u00e9>C\u00af\u00b2.D:\u00c8vRc(\\\u000e\u00a8n\u00e5\u00e9`\u008c\u00f1\u0099\u00bf\u0012GL\u00b6\u00b1%\u00ad4T\u00c8\u00d6+71L\r\u00b5\u00f3\u00074\u00b5\u00ad1\u00a2\u00f4\u00d0\u00cd\u00ee\u00fc\u00186\u0098\u00a9\u0003\u0019#\u0082\u00c2k\u00f9\u0095\u00f4W\u0011\u00d9\u00c4\u00b5x\u009bf\u00a9\u009e\u00c6\u00be\u0010\u0004\u00a1f\u00ff\u00a7\u00da\u0085-s\u00f6\u0016\u00ed\u00bb9\u00abE\u0018\u0007>\u008b\u00ad\u001a\u00c9\u00f6=\u00bai\u009b\u0091`7+\u007f\u00f3\u00f0\u00a6\u0082\u00c8\u00c7\u0088\u00ea B\u00f7\u00caC~w\b\u008fP\u0005?V\u00be\u00e3&\u00abb\u00155\u00c1\u00e0#6\u00dd\u00a3\u00f8\u00dc\u00a8\u0010N\u00e9\u0005 \u00b3N\u0092\u009d\f\u00ca\u008b6\u00a5R\u001fj\u00e7_g\u00f9d7\u0096\u0083\u00f5:I\u00b9d\u0087A\u0002R\u001b\u001cp(\u00016n\u00f9\u00024wf\u00e9\u00f7\u00e56\u0091X\u00caVYau\u00ea^ \u0092\u00d80\u00a3*-\u0006\u0012\u000fT\u00ba\u0001\u0012EbX\u0084~ \u0000\u0080\u0081)\u00a5U\u00067\u0085y\u00f7\u0090\u00848Uk+k\u00ed]v\u008a\u00f0e#\u008d#\u00cfX\u00b4\u00c5\u00f3 r\u008f\u0015\u0011\u00c0\u00d2'\u00e9\u0082i`\t&b\u00fd\u0094c\u0003\u0001\u001c\u00a7\u00bb\u00cay0\\\u00f2\u00a6F\u000b\u00d5\u0089\u0010\u0001\u0003\u0012\u0015\u00d1\u00ed\u0097\u00a3\u00b3\u0013\u00fbw\u00eb\u00b88p \bR\u00ae\u008f\u00b2\u00eap\u009a\u00a1m.(\u00dd\u00a8= \u00c9\u00cfU;\u00f4\u00edD\u00cd\u0015Q\u00df~\u00d3A\u00fb\u001d \u00e0x\u0005U?\u00c9l<\u00da$\u00f5\u00e3\u008eP\u00eb\u00049e(\u00d5\u0082\u00faf\u00f8B\u00fa\f\u00ebd\u001a\u0087\u00e1(`\u00e6sE\u00d7\u00ad\u009c~\u00fd\u00ba\u00cb\u00a0\u00f6\u001d\u00183\u00b1\n\u0002U\u00df\u00e6I\u00b3d\u0017En7\u00e0p\u00adAM\u00a2\u00db\u0089\u00c8\u00af\u0016\u0018\u0081\u009e\u00ce\u00aa\u00e4+Fv\u0093S\f\u0003j\u00b4\u0089-\u00d2\u0088\u00d4\u00eeNMU\u00dc\u0018\u00f8\u00cd\u00ea\u0091\u0002\u00f9\t\u000e\u0014s\u0005B,[\u0097mS30\u00ab9\u00a4x'\u0010\u00eek\u00f5\u008emc\u00c7{\u00ab\u008d\u00a2t\u0084\u00e0#}";
                        var17_6 = 683;
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
                            var18_3[var16_4++] = AutoTool.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "lF\u001f\u00d3\u00ac\u00d2\u00fa&1%\u00e2\u00c8\u00d3\u009b\u008e\u0004USG\u008a\u0095\u0013\u0080q@}\u00a5|\u0099\u0018s\u0006 \u00cd\u00ef\u0082\u009c\u0017r\u00ad\u00dc/\u008cP\u00d9.\u00b6\u0081\u0085\u00dd\u00f7\u00b5n\u00e7\u0018'\u00bf!o\u00a0\u00fb\u001a\u00b8\u000f\u00ba";
                            var17_6 = 65;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = AutoTool.zkmDecodeUtf8(var19_9).intern();
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
                AutoTool.b = var18_3;
                AutoTool.c = new String[22];
                AutoTool.k = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u00cb\u0002\u0095\u00a6\u001e\u00a3H\u0097qOm\u0007\u00c0\\=I'<y+\"3\u00a5\u00ef";
                var5_15 = 24;
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
                    var4_14 = "\u00a7\u00b9\u0096\u00c2v\u0086W_\u00cf\u000f\u0018\u00b6\u00ab\bE\u00a7";
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
        AutoTool.g = var6_12;
        AutoTool.j = new Integer[5];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoTool.class, "silent", "getSilent()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoTool.class, "swapBack", "getSwapBack()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoTool.class, "saveItem", "getSaveItem()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoTool.class, "echestSilk", "getEchestSilk()Z", 0))};
        AutoTool.delegatedProperties = var26_20;
        AutoTool.INSTANCE = new AutoTool();
        AutoTool.silent$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoTool.INSTANCE, "silent", false, null, null, 12, null);
        AutoTool.swapBack$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoTool.INSTANCE, "swap-back", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        AutoTool.saveItem$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoTool.INSTANCE, "save-item", true, null, null, 12, null);
        AutoTool.echestSilk$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoTool.INSTANCE, "echest-silk", true, null, null, 12, null);
        AutoTool.previousSlots = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3DF0;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/q7", exception);
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
            AutoTool.c[n2] = AutoTool.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoTool.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x49A4;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/q7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoTool.j[n2] = n3;
        }
        return j[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoTool.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoTool.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoTool.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

