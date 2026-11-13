/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.basic.GuiMove;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.InteractMode;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.util.SwitchMode;
import su.catlean.util.Timer;
import su.catlean.util.combat.TargetUtil;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoNetheriteScrap
extends Module {
    @NotNull
    public static final AutoNetheriteScrap INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BindSetting placeWithRegion$delegate;
    @NotNull
    private static final BindSetting placeNormal$delegate;
    @NotNull
    private static final BooleanSetting privateOthers$delegate;
    @NotNull
    private static final BooleanSetting checkRange$delegate;
    @NotNull
    private static Timer messageCooldown;
    @NotNull
    private static Timer t;
    private static boolean A;
    @NotNull
    private static List N;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private AutoNetheriteScrap() {
        super("auto-netherite-scrap", Categories.getAttack(), null, null);
    }

    private final Bind getPlaceWithRegion() {
        return (Bind)placeWithRegion$delegate.getValue(this, delegatedProperties[0]);
    }

    private final Bind getPlaceNormal() {
        return (Bind)placeNormal$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getPrivateOthers() {
        return (Boolean)privateOthers$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getCheckRange() {
        return (Boolean)checkRange$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        block4: {
            long l;
            long l2 = l = a ^ 0x363BC0F41DBAL;
            long l3 = l2 ^ 0x59EA29DE27D2L;
            int n = (int)(l3 >>> 48);
            int n2 = (int)(l3 << 16 >>> 32);
            int n3 = (int)(l3 << 48 >>> 48);
            long l4 = l2 ^ 0x687F0C2EC0FL;
            long l5 = l2 ^ 0x4DF49CD5F2F9L;
            int n4 = (int)(l5 >>> 32);
            long l6 = l5 << 32 >>> 32;
            if (inputEvent.getKey() != this.getPlaceWithRegion().getKey() && inputEvent.getKey() != this.getPlaceNormal().getKey() || inputEvent.getAction() != InputEvent.Action.Press || Globals.getMc().currentScreen != null) {
                return;
            }
            if (!messageCooldown.hasPassed(50000)) {
                ModuleMessages.S(this, ModuleMessages.i18n$default(this, "delay", new Object[0], false, 4, null), false, null);
                return;
            }
            if (this.getCheckRange() && TargetUtil.Y((char)n, TargetUtil.INSTANCE, 1.5f, AutoCrystalTargetSortMode.DISTANCE, false, false, 12, n2, (char)n3, null) == null) {
                ModuleMessages.S(this, ModuleMessages.i18n$default(this, "distance", new Object[0], false, 4, null), false, null);
                return;
            }
            if (inputEvent.getKey() == this.getPlaceWithRegion().getKey()) {
                this.startCrafting(l4);
            }
            if (inputEvent.getKey() != this.getPlaceNormal().getKey()) break block4;
            this.switchToScrap(n4, l6);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block3: {
            long l;
            long l2 = l = a ^ 0x1BFEB022CD2FL;
            long l3 = l2 ^ 0x240984C5772BL;
            long l4 = l2 ^ 0x94AD47433AEL;
            if (!A && !this.getPrivateOthers() || !t.hasPassed(15000)) break block3;
            BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getEyePos()).up();
            Intrinsics.checkNotNullExpressionValue(blockPos, "above(...)");
            BlockPos blockPos2 = blockPos;
            if (Intrinsics.areEqual(Globals.getWorld().getBlockState(blockPos2.up()).getBlock(), Blocks.OBSIDIAN)) {
                RotationRequest rotationRequest;
                int n = this.findScrap(l3);
                if (n == -1) {
                    return;
                }
                if (!this.findPlacePos() && (rotationRequest = PlaceManager.place$default(PlaceManager.INSTANCE, blockPos2, n, PlaceMode.Strict, InteractMode.Normal, EntityCheck.NONE, 4.0f, 0.0f, 200, l4, SwitchMode.Alternative, null, false, 1536, null)) != null) {
                    RotationRequest rotationRequest2 = rotationRequest;
                    RotationManager.INSTANCE.submit(rotationRequest2);
                    t.reset();
                    A = false;
                }
            }
        }
    }

    private final int findScrap(long l) {
        long l2 = (l = a ^ l) ^ 0x7F8B6E57190AL;
        int n = (int)(l2 >>> 56);
        int n2 = (int)(l2 << 8 >>> 32);
        int n3 = (int)(l2 << 40 >>> 40);
        SlotResult slotResult = InventoryUtil.INSTANCE.findItems((byte)n, n2, N, n3);
        if (slotResult.isFound()) {
            return slotResult.getSlot();
        }
        return -1;
    }

    private final boolean findPlacePos() {
        BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getEyePos()).up();
        Intrinsics.checkNotNullExpressionValue(blockPos, "above(...)");
        BlockPos blockPos2 = blockPos;
        for (int i = -5; i < 6; ++i) {
            for (int j = -5; j < 6; ++j) {
                for (int k = -5; k < 6; ++k) {
                    if (!N.contains(Globals.getWorld().getBlockState(blockPos2.add(i, j, k)).getBlock())) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private final void startCrafting(long l) {
        long l2 = (l = a ^ l) ^ 0x69319AD5AFC3L;
        int n = (int)(l2 >>> 32);
        long l3 = l2 << 32 >>> 32;
        this.switchToScrap(n, l3);
        A = true;
        Scheduler.schedule$default(Scheduler.INSTANCE, 10, (byte)0, false, AutoNetheriteScrap::reset, 6, null);
    }

    private final void switchToScrap(int n, long l) {
        long l2 = ((long)n << 32 | l << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x2056FF06EA29L;
        int n2 = (int)(l3 >>> 48);
        int n3 = (int)(l3 << 16 >>> 48);
        int n4 = (int)(l3 << 32 >>> 32);
        SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar(AutoNetheriteScrap::isNetheriteScrap);
        if (slotResult.isFound()) {
            InventoryUtil.INSTANCE.switchToSlot(slotResult.getSlot());
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            InventoryUtil.INSTANCE.restoreSelectedSlot();
            messageCooldown.reset();
            return;
        }
        SlotResult slotResult2 = InventoryUtil.INSTANCE.findInInventory((short)n2, AutoNetheriteScrap::isNetheriteScrap2, (short)n3, n4);
        if (slotResult2.isFound()) {
            GuiMove.INSTANCE.setMacroActive(true);
            InteractionUtil.clickSlot$default(slotResult2.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            InteractionUtil.clickSlot$default(slotResult2.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
            GuiMove.INSTANCE.setMacroActive(false);
            messageCooldown.reset();
        }
    }

    private static final void reset() {
        A = false;
    }

    private static final boolean isNetheriteScrap(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.NETHERITE_SCRAP);
    }

    private static final boolean isNetheriteScrap2(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.NETHERITE_SCRAP);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoNetheriteScrap.a = ZkmKeys.a(835664927788733921L, 4280059109689286568L, MethodHandles.lookup().lookupClass()).a(43011627313028L);
                        var20 = AutoNetheriteScrap.a ^ 470489601031L;
                        AutoNetheriteScrap.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[24];
                        var16_4 = 0;
                        var15_5 = "w2Fx'\u00fc=<|V\\\u0098\u00f4\\\u00fc`XN\u0015<\u0019\u001a\u00aa|\u0095\u00e6\n#VFP\u00e3 \u0002\u00d0u\n\u009b\u000f\u00f17\u001e1\u008b%\u0082\u00e2\u0004L\u00e2&\u00e7\u00eb\u00d3\u00b0\u00f5\u00f3\u00d3\u00f1 \u00c7\u00b4\u00ef\u00d2\u0094\u0010*\u009d\u00cf\u00eb\u0084#\u001bMh.DG\u00c9\u0012\u00a8\u0094\u0010\u00a9Bo\u00edQ\u00a0\u00c4>\u00edQ\u00e7\u00a8\u00e9\u00b5\u0087\u00c0(7\u0091\u00d7\u00d8\u00e6\u00e4\u00e7\u00b5\u00e8\u0006d\u008c*\u001f\u0011\u0092\\\u00a5\u00a4\u00fb\u00a6\u009f\u00b2Bk\u0018\u0013R\u00e7\u00b1\u00c2\u00d7\u0096\u008el\u0019H\u000f<\u00a4(\u00f5\u00cb\\P\u00a3\u00cf\u008f\u0012\u00d13\u0018\u009b\u00d7h\u00ef\u00038z\u00f4\u001d\u000f+\u00b8\u00bep\bFF\u0005\u009dn\u00fd3,\u00f5G{\u00da\u00da}\u0018\u00f0\u0082uQ\u00d2\u00ffI\u00f7\u0003\u00d4\u00e3\u00ea.\u008c\u00804J\u00b1\u00cbsk]\u00d5\u00ae(35\u00cb1A\u00c60\rN`\u00bdXw\u001en\b\u00bc\u00b3\u00f5\u00d2\u00ad\u00de\u0087\u00c47\u0000D\u00ac\u00fa\u0098M\u001e\u00d4R\u00b1\u00c1\u008c9\u00d8\u0005\u0018\u00e3\u00c4\u0018i\u00ecU\u008b\u0093_\u001d\u00b88\u00e0\u0001\u00a1\u00c1\u000b#\u00a0\u00a0\u00e2\u00a1\u000eB \u0092^\u00db\u00f2\u00c4>\u00e8\u0099\u008bm\u00f9\u001e\u008fk\u001e\u00e0\u00c1\u0011\fcL=\u0016\u00bb~W<R\u00cd6\u00ac\u00e3\u0018^\u00f2^M\u001a=\u008dq\u009c\u0018\u00fa\u0016T\u00d3\u00b4^\u00c1b\u00ea\u0094\u00b3\u00c6s^($B&\u000b\u00ce\u00fa\u0093\u00a6)\u00c2\u00f8\u0093\u008aGJ\u00abM\u00c1\u00df\u00b2\u00b2&\u00d5\u00b4Is\u008c[A\u0088t\u00d7X\u0093~\u00ab\u0080R?p\u0018\u0016\u00e0@\u00939X\u0000(;\u00ccU\u00d2\u00bd\u00de7\u0089\u00a7DvJc^\u0011\u0082\u0018\u0016\u009bR\u00e5\u00cd\u00b6l\u0083P\n\u009e\u001f\u009eb\u00ed\u00b2\u00f94m-v2\u0019g ~\u00a8oE\u009aP\u00cct\u0003\u00b8C;EWn\u00cd\u0001\u0082M}\u00cb/\u00d3Z\u00e5\u001e\u00f2\u008e3^X\u00cc aIl@\u0002\u00fco\u007f\u001a\u00eb0\u00a2\u00f2^\u00ba\u0089Z\u008aP\u00ae[\u00b5\n\u001fF\u0091\u00d6\u0012c\u009a\u0099\u00a5\u0010\u0090\u0090v\u00fb\u00b3gL8\u0019\u00ab\u0080H'w2c \u00a3''x\u00ba_m\u00d32\u00f8\u00b2\u00a6\u0082\u0018\u0084\u0019\u00c0\u00c5S\u001ekK\u009c\u00a4\u00d3\u008f\u00e5\u008f\u0017\u00c4\u00b3\u00840\u00d2\u0089@\u00bdBs\u0090\u00e4z\u00c8Se\u0015\u00bew*\u0081,\u00c9\u00ecl\u008d0\u00a8\u0088e\\\u00e4W\u009a\u00ad\u000b$<\u00e7\fh\u00fe\u00fd\u00b2u=@\u000b\u00b17\u00dc\n \u00c2\u00a1\u00e9c\u008e\u00a2\u00deq\u00aes\u00ce)n5\u009a\u00b6#\u00a9\u00f7VT\u00c1\u00ef\u00c6E\u00e0@\u00b7\u00cc\u00d1\u000ey\u0018\u0003\u00b8\u00da\u0000\u00fd\u00e2\u00afgaV\u008d\u00ab\u00fa\u00e3\fN\u00be?\u00eb\u001fV?\u00fd\u00fe\u0018(R\u00b3WQ\u0089\u00e1\u00fe`\u00f3h\u00975\u00e8f\u00e5\u00efA\u00de\u00b5k\u0011\u0004!";
                        var17_6 = 669;
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
                            var18_3[var16_4++] = AutoNetheriteScrap.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "|7\u00b9\\\u00c8\u00e7\u00bdC\u00cd\u00ff\u0097Z!\u00be,\u00f9A\u00caN\u008c\u0090\u0096\u008e/\u008b\u00d1(\u0097\u00cc\u00c6\u00a6\u0016H+}\u00b7\u0084\u0001_\u00f5\u00d6\u00cev\u00c3\u0005\u00b7\u00afj\u00d9\u00bei\u00f6g(b\u0005oqB\u008aR=\u00fe\u00f5\u00ba`i.\u00feB)P\u00fa\u00e3\u0014\u00a0N\u000b\u00fel,B\u0087Q\u00dd\u00b9`\u00da\u00f0\u00abJ{\u00f8\u00c7{D\u001b,4\u00dd\u00cd\u00a3\u0004\u001f\u00ef";
                            var17_6 = 105;
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
                            var18_3[var16_4++] = AutoNetheriteScrap.zkmDecodeUtf8(var19_9).intern();
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
                AutoNetheriteScrap.b = var18_3;
                AutoNetheriteScrap.e = new String[24];
                AutoNetheriteScrap.j = new HashMap<K, V>(13);
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
                var6_12 = new long[15];
                var3_13 = 0;
                var4_14 = "E\u00c8Su?L\u009cK\u0001\u00e5\u00da\u00e3\u00b8r\u00a9n\u00cd\u00bd\u0000ce\u0095\u00fe\u00d30\u00d0+\u0014z.\u00a2\u00a2\u00b2b\u00bd%p\u008c-&^\u00fd\u00ae\u00a2K>\b\u00ec\u00b1g5\u00de\u00ed\u008b9yc0k\u00c1\u00a9\u009b\u0089T\u00897\u00e4\u000eim\u00be\u00fb\u00ab\u00ab\u00e3\n\u0003\u0007\r\u0012d/\u00d8\u000f\u0096!A\u00f0E\u00ed\u00bb\u00ab\u00ea)\u0091\u0010K\\M\u0094\u009c\u00ef\u00ddp";
                var5_15 = 104;
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
                    var4_14 = "[o\u00fdrx\u0081=\u00bb\u0085PK\u00e4(c\u00e4\u000f";
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
        AutoNetheriteScrap.h = var6_12;
        AutoNetheriteScrap.i = new Integer[15];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoNetheriteScrap.class, "placeWithRegion", "getPlaceWithRegion()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(AutoNetheriteScrap.class, "placeNormal", "getPlaceNormal()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(AutoNetheriteScrap.class, "privateOthers", "getPrivateOthers()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoNetheriteScrap.class, "checkRange", "getCheckRange()Z", 0))};
        AutoNetheriteScrap.delegatedProperties = var32_20;
        AutoNetheriteScrap.INSTANCE = new AutoNetheriteScrap();
        AutoNetheriteScrap.placeWithRegion$delegate = SettingFactory.bindSetting$default(AutoNetheriteScrap.INSTANCE, "place-with-region", new Bind(0, false, false, 7, null), null, null, 12, null);
        AutoNetheriteScrap.placeNormal$delegate = SettingFactory.bindSetting$default(AutoNetheriteScrap.INSTANCE, "place-normal", new Bind(0, false, false, 7, null), null, null, 12, null);
        AutoNetheriteScrap.privateOthers$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoNetheriteScrap.INSTANCE, "private-others", true, null, null, 12, null);
        AutoNetheriteScrap.checkRange$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoNetheriteScrap.INSTANCE, "check-range", false, null, null, 12, null);
        AutoNetheriteScrap.messageCooldown = new Timer();
        AutoNetheriteScrap.t = new Timer();
        var32_20 = new Block[5];
        Intrinsics.checkNotNullExpressionValue(Blocks.COAL_BLOCK, "COAL_BLOCK");
        Intrinsics.checkNotNullExpressionValue(Blocks.DIAMOND_ORE, "DIAMOND_ORE");
        Intrinsics.checkNotNullExpressionValue(Blocks.DIAMOND_BLOCK, "DIAMOND_BLOCK");
        Intrinsics.checkNotNullExpressionValue(Blocks.EMERALD_ORE, "EMERALD_ORE");
        Intrinsics.checkNotNullExpressionValue(Blocks.ANCIENT_DEBRIS, "ANCIENT_DEBRIS");
        AutoNetheriteScrap.N = CollectionsKt.listOf(var32_20);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5CA6;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/un", exception);
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
            AutoNetheriteScrap.e[n2] = AutoNetheriteScrap.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoNetheriteScrap.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/un" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5727;
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
                throw new RuntimeException("su/catlean/un", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoNetheriteScrap.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoNetheriteScrap.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/un" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoNetheriteScrap.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoNetheriteScrap.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

