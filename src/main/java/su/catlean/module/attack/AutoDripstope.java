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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.state.State;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.PlacementHelper;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoDripstope
extends Module {
    @NotNull
    public static final AutoDripstope INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final BooleanSetting fastOpen$delegate;
    @NotNull
    private static TickTimer d;
    @NotNull
    private static TickTimer t;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map i;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    private AutoDripstope() {
        super("auto-dripstope", Categories.getAttack(), null, null);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final void setDelay(int n) {
        delay$delegate.setValue(this, delegatedProperties[0], n);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        switchMode$delegate.setValue(this, delegatedProperties[1], (Object)swapMode);
    }

    private final boolean getFastOpen() {
        return (Boolean)fastOpen$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setFastOpen(boolean bl) {
        fastOpen$delegate.setValue(this, delegatedProperties[2], bl);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block19: {
            Map.Entry entry;
            long l;
            block20: {
                Object v3;
                Iterable iterable;
                Object object;
                Object object2;
                Map.Entry entry2;
                Object object3;
                Object object4;
                long l2;
                long l3 = l2 = a ^ 0x4D116CD71DD3L;
                int n = (int)((l3 ^ 0x6A4380FBE2D9L) >>> 48);
                l = l3 ^ 0x7B1277BDD030L;
                int n2 = this.getDelay();
                if (!d.hasPassed((short)n, n2)) {
                    return;
                }
                Entity entity = Globals.getPlayer();
                BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
                Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
                BlockPos blockPos2 = blockPos;
                int n3 = (int)Math.ceil(Globals.getPlayer().getBlockInteractionRange());
                Map map = new LinkedHashMap();
                int n4 = -n3;
                if (n4 <= n3) {
                    while (true) {
                        int n5;
                        if ((n5 = -n3) <= n3) {
                            while (true) {
                                int n6;
                                if ((n6 = -n3) <= n3) {
                                    while (true) {
                                        Intrinsics.checkNotNullExpressionValue(blockPos2.add(n4, n5, n6), "offset(...)");
                                        Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState((BlockPos)object4), "getBlockState(...)");
                                        Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(((BlockPos)object4).down()), "getBlockState(...)");
                                        if (((AbstractBlock.AbstractBlockState)object3).getBlock() instanceof TrapdoorBlock && ((State)object3).get(TrapdoorBlock.HALF) == BlockHalf.BOTTOM && (((AbstractBlock.AbstractBlockState)((Object)entry2)).isAir() || Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)((Object)entry2)).getBlock(), Blocks.POINTED_DRIPSTONE))) {
                                            boolean bl;
                                            block18: {
                                                Intrinsics.checkNotNullExpressionValue(((BlockPos)object4).toCenterPos(), "getCenter(...)");
                                                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getEntities(), "entitiesForRendering(...)");
                                                if (object2 instanceof Collection && ((Collection)object2).isEmpty()) {
                                                    bl = false;
                                                } else {
                                                    Iterator iterator2 = object2.iterator();
                                                    while (iterator2.hasNext()) {
                                                        Object t = iterator2.next();
                                                        Entity entity2 = (Entity)t;
                                                        if (!(entity2 instanceof LivingEntity && !((LivingEntity)entity2).isDead() && Math.hypot(((Vec3d)object).x - ((LivingEntity)entity2).getX(), ((Vec3d)object).z - ((LivingEntity)entity2).getZ()) < (double)0.8f && !Intrinsics.areEqual(entity2, Globals.getPlayer()) && ((LivingEntity)entity2).getY() < (double)((Vec3i)object4).getY())) continue;
                                                        bl = true;
                                                        break block18;
                                                    }
                                                    bl = false;
                                                }
                                            }
                                            if (bl) {
                                                map.put(object4, object3);
                                            }
                                        }
                                        if (n6 == n3) break;
                                        ++n6;
                                    }
                                }
                                if (n5 == n3) break;
                                ++n5;
                            }
                        }
                        if (n4 == n3) break;
                        ++n4;
                    }
                }
                if (!(object4 = (iterable = (Iterable)map.entrySet()).iterator()).hasNext()) {
                    v3 = null;
                } else {
                    object3 = object4.next();
                    if (!object4.hasNext()) {
                        v3 = object3;
                    } else {
                        entry2 = (Map.Entry)object3;
                        BlockPos blockPos3 = (BlockPos)entry2.getKey();
                        float f = INSTANCE.getYawTo(blockPos3);
                        do {
                            BlockPos blockPos4;
                            float f2;
                            if (Float.compare(f, f2 = INSTANCE.getYawTo(blockPos4 = (BlockPos)(object2 = (Map.Entry)(object = object4.next())).getKey())) <= 0) continue;
                            object3 = object;
                            f = f2;
                        } while (object4.hasNext());
                        v3 = object3;
                    }
                }
                entry = v3;
                if (entry == null) break block19;
                if (!((Boolean)((BlockState)entry.getValue()).get(TrapdoorBlock.OPEN)).booleanValue() && Globals.getWorld().getBlockState(((BlockPos)entry.getKey()).down()).isAir()) break block20;
                double d = Random.Default.nextDouble() / 10.0 + 0.5;
                object4 = new Vec3d((double)((BlockPos)entry.getKey()).getX() + d, ((BlockPos)entry.getKey()).getY(), (double)((BlockPos)entry.getKey()).getZ() + d);
                if (t.hasPassed((short)n, 5)) {
                    RotationManager.INSTANCE.submit(new RotationRequest(Rotation.Companion.to((Vec3d)object4), 100, () -> AutoDripstope.onPlayerUpdate$lambda$0(entry)));
                }
                AutoDripstope.d.reset();
                break block19;
            }
            if (!this.placeDripstone(l, (BlockPos)entry.getKey())) break block19;
            d.reset();
        }
    }

    private final boolean placeDripstone(long l, BlockPos blockPos) {
        block1: {
            long l2 = l = a ^ l;
            long l3 = l2 ^ 0x1CD2343F19DDL;
            long l4 = l2 ^ 0x196903842179L;
            SlotResult slotResult = InventoryUtil.INSTANCE.findItem(l4, AutoDripstope::isDripstone, this.getSwitchMode());
            if (!slotResult.isFound()) break block1;
            BlockPos blockPos2 = blockPos.down();
            Intrinsics.checkNotNullExpressionValue(blockPos2, "below(...)");
            BlockHitResult blockHitResult = PlaceManager.calcPlaceData$default(PlaceManager.INSTANCE, blockPos2, -1, l3, PlaceMode.Strict, EntityCheck.ALL, (float)Globals.getPlayer().getBlockInteractionRange(), (float)Globals.getPlayer().getBlockInteractionRange(), null, null);
            if (blockHitResult != null) {
                Vec3d vec3d = blockHitResult.getPos();
                Intrinsics.checkNotNullExpressionValue(vec3d, "getLocation(...)");
                RotationManager.INSTANCE.submit(new RotationRequest(Rotation.Companion.to(vec3d), 100, () -> AutoDripstope.placeDripstone$lambda$1(slotResult, blockPos, blockHitResult)));
            }
        }
        return true;
    }

    private final void createHitResult(BlockPos blockPos) {
        double d = Random.Default.nextDouble() / 10.0 + 0.5;
        BlockHitResult blockHitResult = new BlockHitResult(new Vec3d((double)blockPos.getX() + d, blockPos.getY(), (double)blockPos.getZ() + d), Direction.DOWN, blockPos, false);
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
    }

    public final float getYawTo(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "pos");
        return (float)Math.abs(MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(blockPos.toCenterPos().z - Globals.getPlayer().getZ(), blockPos.toCenterPos().x - Globals.getPlayer().getX())) - 90.0) - (double)MathHelper.wrapDegrees(Globals.getPlayer().getYaw()));
    }

    private static final Unit onPlayerUpdate$lambda$0(Map.Entry entry) {
        INSTANCE.createHitResult((BlockPos)entry.getKey());
        return Unit.INSTANCE;
    }

    private static final boolean isDripstone(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.POINTED_DRIPSTONE);
    }

    private static final Packet createPlacePacket(BlockHitResult blockHitResult, int n) {
        return new PlayerInteractBlockC2SPacket(Hand.MAIN_HAND, blockHitResult, n);
    }

    private static final Unit placeDripstone$lambda$1$lambda$0(BlockPos blockPos, BlockHitResult blockHitResult) {
        PlacementHelper.INSTANCE.setViaVersionFix(true);
        PacketUtil.sendSequencedPacket(arg_0 -> AutoDripstope.createPlacePacket(blockHitResult, arg_0));
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        PlacementHelper.INSTANCE.setViaVersionFix(false);
        INSTANCE.createHitResult(blockPos);
        if (INSTANCE.getFastOpen()) {
            INSTANCE.createHitResult(blockPos);
        }
        t.reset();
        return Unit.INSTANCE;
    }

    private static final Unit placeDripstone$lambda$1(SlotResult slotResult, BlockPos blockPos, BlockHitResult blockHitResult) {
        Function0<Unit> function0 = () -> AutoDripstope.placeDripstone$lambda$1$lambda$0(blockPos, blockHitResult);
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
                        AutoDripstope.a = ZkmKeys.a(-3669055477203919723L, -5218157628920450632L, MethodHandles.lookup().lookupClass()).a(261688049258216L);
                        var20 = AutoDripstope.a ^ 75894938175723L;
                        AutoDripstope.i = new HashMap<K, V>(13);
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
                        var18_3 = new String[20];
                        var16_4 = 0;
                        var15_5 = "\u00d2M\u00c5r&\u00b5\u008a\u00f1\rz\u00bf\u00f5gfo\u00b4t\u00d5l\u00ab\u0097\u00db\u00c4\u007f\u0082\u0005Ub\u00c4p\u0019\u0099(\u00e9\u0081\u0014a\u00bdo\u008fv\u008dF\f\u00c5\u00c8n\u00ab\u00dd\u009f\u001b\u00ecZ\u00cen\u0098\u0019\nOy\u0082kcq\u0001;\u00be\u00fe\u0010\u00edI\u00d7\u00d4(2ZEPI|\u008c\u0019\u00c1\u009a\u0088\u00d7\u001bi14:\u0091\u00b5\u00b0t\t\u00ea~|\u00b4or\u00c0\u00aa)\u00b1\u00a2\u00dc\u00ffklF3\u00d9 :\u00f5\u00a8\u0091=\u00b9\u0083\u00f1\u007f\u000e\u0098\u0098H.\u00bbnB\u00bc\u00b3\u009fn2n{\u007f\u00ad\u00f6\u00abS\u00a4^q\u0010<\u00d8%\u00f5\u009a\u00b0\"\u00d9h\u0098\u008e\u00b9\u00cc\u00a6\u00a3\u00cb8\u00beB\u0091\u00bd \u00db\u00c3H\u00cc\u001f\u009e\u00ab\u009b\u00eat\u00c2\u00c6'\u008c\u008do6\bRK+\u001d\u009e=\u00a3\u0000\u00bc\n\u0001\u0093\u00c4\u0014\u009b_\u00f4\\'J \u00fa7\u00a6\u00eb\u00dbJ\u00b3\u00bfC\u00bf\u00d8G8\u001f=\u00e2\u00a3\u0000\u0006\u0015\u00ef_\u00ee\u00fd,\u00f7\u00c62*\u00f1\u00c1,\u00b7\u00c1\u00fdn\u000f\u00fb\u0086\u0090\u00c0H{\u00b0\u00c4T\u00a4\u008b\u00bf?6\u00a3\u00a2qQ\u00c3\u00b7\u00e1\u00dfn\u00f5\u00e1\\\u00c6l\u00ef\u0085\u0013? >\u00bfO\u00dcI?\u00cfl\u0088\u00b2\u00d6\u0092P\u00b6\u0007\u00cb\u00f9\u00a5B\u001b*\u00a0\u0015+\u00bc\u00e7a\u00ac\u001d\u0007\u00b3{\u0010\u00ae\u00d5\u00d9\u00e5-Hw\u00be=\u00e3\u0003&\u00a0\u0089\u00fe\u00e6\u0018,\u00a7\u00d0_\u00ba\u00fb\u00dd\u00e0\u008c\\\u00c3\u0095\u0000c#\u00c6\u0016\u009bq\u00a1\u00b6\u00d0\u00ab\u00ad\u0018\u00fb\u0000\u00edY\u00e4\u008e\u00f5\u00e8\u00ca:^V1\u009c\u000f\u00db7\u00fb\u0014+_\u00e9p\u00ab\u0018Q\u00a1\"\u00f1\u00f6\u00ab\u00bc\u00ab)\u00f7\u0081=m\u009c\u008b\u001ddD\u00f8\u00b5\u00e6\u00fc\u00f8\u00d9\u0018\u0090;\u00af\u001a\u00e2\u00e2\u00e9\u00a3\u00b0\u00e11iH\u00d4.\u008d\u00c9\u00ed\u00adI\u009c\u00d5{$\u0010~v\u00d6\u0017\u00d0\u00d8q+\u00ed7\u00fa\u00c4]D\u00e9e\u0010z\u008d9\u00f7\u000f~\u00ff\u00abfY\\\u007f\u00a5p\u001a\u0083\u0010!\u00cb\u00f7\u00cb\u00a7\u009bV\u0017t\u0002!\u00cb\u0001\u00dd\u0007K\u0018\u00b4r2\t\u00b4\u00cc\u00bd\u001e\u00ca\u00e6)\u0096\u0000pP\u00acM\u00d7\u00147)\u00c0\u00bc\u00ca(\u0094\u0011;\u00c9lqz\u00a1\u00df\u00a4G\u00cf+\u0014\u008ct\u0086\u00a4&J~\u00ad\"\u00e9vH\n\u00e7}8\u0010\u008d\u00a8i\u0014\u0015\u0013\u008d\u00abM";
                        var17_6 = 545;
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
                            var18_3[var16_4++] = AutoDripstope.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "s\u008b\u00da/\u0012\u00c5\u00d4\u0081\u008c#\u00ca4\u00c2\u00da\u00c2\u00e5\u00f6\u0082\u00e9x\u00d2\u00d0y% E\u00adt\u00c5\u0013+K\u000b\u00b9\u00c8t-i\u00cf\u008e\u00e8\u0088r\u00e8\u00f4\u0006xvuK\u0084-\u00a2\u0095\u008b\u00ac\u00db";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = AutoDripstope.zkmDecodeUtf8(var19_9).intern();
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
                AutoDripstope.b = var18_3;
                AutoDripstope.e = new String[20];
                AutoDripstope.l = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "/n\u001f\u00131\u00ba\u0082\u00a1OI\u00f5\u0085\u008br\u00d7\u00e6C\u001b\u00e9\bN5\u0014\u00f2D\u0080o\u00bb\u001d\u00eb\u007fe>9\u00b8\u00a2\u00ca\u00c3\u00dd\u00ca";
                var5_15 = 40;
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
                    var4_14 = "\u0092\u00cd\u0005\u00eb\u00b5b\u00f4\tA\u00bdP\u00eb\u00e1\u001d=s";
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
        AutoDripstope.j = var6_12;
        AutoDripstope.k = new Integer[7];
        var32_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoDripstope.class, "delay", "getDelay()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoDripstope.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoDripstope.class, "fastOpen", "getFastOpen()Z", 0))};
        AutoDripstope.delegatedProperties = var32_20;
        AutoDripstope.INSTANCE = new AutoDripstope();
        AutoDripstope.delay$delegate = SettingFactory.intSetting$default(AutoDripstope.INSTANCE, "delay", 0, new IntRange(0, 15), null, null, 24, null);
        AutoDripstope.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoDripstope.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
        AutoDripstope.fastOpen$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoDripstope.INSTANCE, "fast-open", true, null, null, 12, null);
        AutoDripstope.d = new TickTimer();
        AutoDripstope.t = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3CF0;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/u_", exception);
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
            AutoDripstope.e[n2] = AutoDripstope.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoDripstope.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x21B7;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])AutoDripstope.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    AutoDripstope.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u_", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoDripstope.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoDripstope.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoDripstope.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoDripstope.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

