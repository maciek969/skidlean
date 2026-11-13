/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.legit;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
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
import kotlin.reflect.KProperty;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.gofra.Flow;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class BowMinecart
extends Module {
    @NotNull
    public static final BowMinecart INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final BooleanSetting selfDamageCheck$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private BowMinecart() {
        super("bow-minecart", Categories.getLegit(), null, null);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        switchMode$delegate.setValue(this, delegatedProperties[0], (Object)swapMode);
    }

    private final boolean getSelfDamageCheck() {
        return (Boolean)selfDamageCheck$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setSelfDamageCheck(boolean bl) {
        selfDamageCheck$delegate.setValue(this, delegatedProperties[1], bl);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onAfterSendPacket(AfterSendPacket var1_1) {
        block11: {
            block15: {
                block14: {
                    block13: {
                        block12: {
                            block10: {
                                var2_2 = BowMinecart.a ^ 15830986710372L;
                                v0 = var2_2 ^ 112049447551352L;
                                var9_3 = (int)(v0 >>> 48);
                                var10_4 = v0 << 16 >>> 16;
                                var24_5 = ClickGuiScreen.zkmGetFlag();
                                v1 = var1_1.getPacket();
                                if (var24_5 != 0) break block10;
                                if (!(v1 instanceof PlayerActionC2SPacket)) break block11;
                                v1 = var1_1.getPacket();
                            }
                            if (((PlayerActionC2SPacket)v1).getAction() != PlayerActionC2SPacket.Action.RELEASE_USE_ITEM) break block11;
                            v2 = Globals.getPlayer().getItemUseTime();
                            if (var24_5 != 0) break block12;
                            if (v2 <= 2) break block11;
                            v2 = (int)Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.BOW);
                        }
                        if (var24_5 != 0) break block13;
                        if (v2 == 0) break block11;
                        v3 = Globals.getPlayer().getMainHandStack();
                        Intrinsics.checkNotNullExpressionValue(v3, "getMainHandItem(...)");
                        v4 = Enchantments.FLAME;
                        Intrinsics.checkNotNullExpressionValue(v4, "FLAME");
                        v2 = (int)EnchantUtil.hasEnchantment(v3, v4);
                    }
                    if (v2 == 0 || (var25_6 = this.calculateAim()) == null) break block11;
                    var26_7 = (float)Globals.getPlayer().getBlockInteractionRange();
                    var27_9 = Globals.getPlayer().getEyePos().distanceTo(var25_6.toCenterPos());
                    cfr_temp_0 = 2.299999952316284 - var27_9;
                    v5 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 < 0.0 ? -1 : 1);
                    if (var24_5 != 0) ** GOTO lbl36
                    if (v5 <= 0) {
                        cfr_temp_1 = var27_9 - (double)var26_7;
                        v5 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 < 0.0 ? -1 : 1);
lbl36:
                        // 2 sources

                        if (var24_5 == 0) {
                            v5 = v5 <= 0 ? (double)true : (double)false;
                        }
                    } else {
                        v5 = (double)false;
                    }
                    if (v5 == false) break block11;
                    v6 = this.getSwitchMode();
                    v7 = SwapMode.SILENT;
                    if (var24_5 != 0) break block14;
                    if (v6 == v7) ** GOTO lbl-1000
                    v6 = this.getSwitchMode();
                    v7 = SwapMode.NORMAL;
                }
                if (v6 == v7) lbl-1000:
                // 2 sources

                {
                    v8 = true;
                } else {
                    v8 = var26_8 = false;
                }
                if (var26_8) {
                    var28_11 = new Item[4];
                    Intrinsics.checkNotNullExpressionValue(Items.RAIL, "RAIL");
                    Intrinsics.checkNotNullExpressionValue(Items.POWERED_RAIL, "POWERED_RAIL");
                    Intrinsics.checkNotNullExpressionValue(Items.DETECTOR_RAIL, "DETECTOR_RAIL");
                    Intrinsics.checkNotNullExpressionValue(Items.ACTIVATOR_RAIL, "ACTIVATOR_RAIL");
                    v9 = InventoryUtil.INSTANCE.findInHotbar((Item[])var28_11);
                } else {
                    var28_11 = new Item[4];
                    Intrinsics.checkNotNullExpressionValue(Items.RAIL, "RAIL");
                    Intrinsics.checkNotNullExpressionValue(Items.POWERED_RAIL, "POWERED_RAIL");
                    Intrinsics.checkNotNullExpressionValue(Items.DETECTOR_RAIL, "DETECTOR_RAIL");
                    Intrinsics.checkNotNullExpressionValue(Items.ACTIVATOR_RAIL, "ACTIVATOR_RAIL");
                    v9 = var27_10 = InventoryUtil.INSTANCE.findInInventory((Item[])var28_11, (char)var9_3, var10_4);
                }
                if (var26_8) {
                    var29_12 = new Item[1];
                    Intrinsics.checkNotNullExpressionValue(Items.TNT_MINECART, "TNT_MINECART");
                    v10 = InventoryUtil.INSTANCE.findInHotbar(var29_12);
                } else {
                    var29_12 = new Item[1];
                    Intrinsics.checkNotNullExpressionValue(Items.TNT_MINECART, "TNT_MINECART");
                    v10 = InventoryUtil.INSTANCE.findInInventory(var29_12, (char)var9_3, var10_4);
                }
                var28_11 = v10;
                v11 = var27_10.isFound();
                if (var24_5 != 0) break block15;
                if (!v11) break block11;
                v11 = var28_11.isFound();
            }
            if (v11) {
                v12 = var25_6.up().toCenterPos();
                Intrinsics.checkNotNullExpressionValue(v12, "getCenter(...)");
                RotationManager.INSTANCE.submit(new RotationRequest(Rotation.Companion.to(v12), 100, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, onAfterSendPacket$lambda$0(su.catlean.util.SlotResult net.minecraft.util.math.BlockPos ), ()Lkotlin/Unit;)((SlotResult)var27_10, (BlockPos)var25_6)));
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private final BlockPos calculateAim() {
        int n;
        double d;
        double d2;
        double d3;
        double d4;
        block25: {
            double d5;
            double d6;
            block24: {
                float f;
                double d7;
                block23: {
                    float f2;
                    block21: {
                        block22: {
                            d4 = Globals.getPlayer().getX();
                            d3 = Globals.getPlayer().getEyePos().y - 0.1000000014901161;
                            d2 = Globals.getPlayer().getZ();
                            double d8 = Math.toRadians(Globals.getPlayer().getYaw());
                            double d9 = Math.toRadians(Globals.getPlayer().getPitch());
                            d = -Math.sin(d8) * Math.cos(d9);
                            n = ClickGuiScreen.zkmFlagCheck();
                            d6 = -Math.sin(d9);
                            d7 = Math.cos(d8) * Math.cos(d9);
                            f = (float)Globals.getPlayer().getItemUseTime() / 20.0f;
                            f2 = f = (f * f + f * 2.0f) / 3.0f;
                            if (n == 0) break block21;
                            if (f2 > 1.0f) break block22;
                            float f3 = f - 0.0f;
                            float f4 = f3 == 0.0f ? 0 : (f3 < 0.0f ? -1 : 1);
                            if (n != 0) {
                                f4 = f4 == false ? 1.0f : 0.0f;
                            }
                            if (f4 == false) break block23;
                        }
                        f2 = 1.0f;
                    }
                    f = f2;
                }
                double d10 = Math.sqrt(d * d + d6 * d6 + d7 * d7);
                d /= d10;
                d6 /= d10;
                float f5 = f * 3.0f;
                d *= (double)f5;
                d6 *= (double)f5;
                d5 = (d7 /= d10) * (double)f5;
                if (n == 0) break block24;
                d7 = d5;
                if (Globals.getPlayer().isOnGround()) break block25;
                d5 = d6 + Globals.getPlayer().getVelocity().getY();
            }
            d6 = d5;
        }
        int n2 = 0;
        do {
            Object object;
            Vec3d vec3d;
            Vec3d vec3d2;
            int n3 = n2;
            block1: while (true) {
                if (n3 >= 300) return null;
                vec3d2 = new Vec3d(d4, d3, d2);
                d *= 0.99;
                d6 *= 0.99;
                vec3d = new Vec3d(d4 += d, d3 += (d6 -= (double)0.05f), d2 += (d7 *= 0.99));
                object = Globals.getWorld().getEntities().iterator();
                block2: while (true) {
                    boolean bl = object.hasNext();
                    while (bl) {
                        block28: {
                            Entity entity;
                            block29: {
                                block27: {
                                    block26: {
                                        Intrinsics.checkNotNullExpressionValue(object.next(), "next(...)");
                                        n3 = entity instanceof ArrowEntity;
                                        if (n == 0) continue block1;
                                        if (n == 0) break block26;
                                        if (n3 != 0) break block27;
                                        bl = entity.equals(Globals.getPlayer());
                                    }
                                    if (n == 0) break block28;
                                    if (!bl) break block29;
                                }
                                if (n != 0) continue block2;
                            }
                            bl = entity.getBoundingBox().intersects(new Box(d4 - 0.3, d3 - 0.3, d2 - 0.3, d4 + 0.3, d3 + 0.3, d2 + 0.3));
                        }
                        if (n == 0) continue;
                        if (bl) return null;
                        continue block2;
                    }
                    break block1;
                    break;
                }
                break;
            }
            BlockHitResult blockHitResult = Globals.getWorld().raycast(new RaycastContext(vec3d2, vec3d, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer()));
            Intrinsics.checkNotNullExpressionValue(blockHitResult, "clip(...)");
            object = blockHitResult;
            if (((BlockHitResult)object).getType() == HitResult.Type.BLOCK) {
                int n4 = this.getSelfDamageCheck();
                if (n != 0) {
                    if (n4 == 0) return ((BlockHitResult)object).getBlockPos();
                    Vec3d vec3d3 = ((BlockHitResult)object).getBlockPos().up().toCenterPos();
                    Intrinsics.checkNotNullExpressionValue(vec3d3, "getCenter(...)");
                    float f = DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, vec3d3, null, 0, false, null, false, 62, null) - (Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount());
                    n4 = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                }
                if (n4 <= 0) return ((BlockHitResult)object).getBlockPos();
                if (n == 0) {
                    return ((BlockHitResult)object).getBlockPos();
                }
            } else if (d3 <= -65.0) return null;
            ++n2;
        } while (n != 0);
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean canUseFlameBow() {
        int n = ClickGuiScreen.zkmGetFlag();
        boolean bl = this.getEnabled();
        if (n == 0) {
            if (!bl) return false;
            bl = Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.BOW);
        }
        if (n == 0) {
            if (!bl) return false;
            ItemStack itemStack = Globals.getPlayer().getMainHandStack();
            Intrinsics.checkNotNullExpressionValue(itemStack, "getMainHandItem(...)");
            RegistryKey<Enchantment> registryKey = Enchantments.FLAME;
            Intrinsics.checkNotNullExpressionValue(registryKey, "FLAME");
            bl = EnchantUtil.hasEnchantment(itemStack, registryKey);
        }
        if (n != 0) return bl;
        if (!bl) return false;
        return true;
    }

    private static final Unit onAfterSendPacket$lambda$0$lambda$0(BlockPos blockPos) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, new BlockHitResult(new Vec3d((double)blockPos.getX() + 0.5, blockPos.up().getY(), (double)blockPos.getZ() + 0.0), Direction.UP, blockPos, false));
        return Unit.INSTANCE;
    }

    private static final Unit onAfterSendPacket$lambda$0$lambda$1(BlockPos blockPos) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, new BlockHitResult(new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.up().getY() + 0.125, (double)blockPos.getZ() + 0.5), Direction.UP, blockPos.up(), false));
        return Unit.INSTANCE;
    }

    private static final Unit onAfterSendPacket$lambda$0(SlotResult slotResult, BlockPos blockPos) {
        long l = a ^ 0x460C83F57598L;
        long l2 = l ^ 0x5B5499A09D00L;
        Function0<Unit> function0 = () -> BowMinecart.onAfterSendPacket$lambda$0$lambda$0(blockPos);
        SwapMode swapMode = INSTANCE.getSwitchMode();
        int n = slotResult.getSlot();
        InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
        Item item = Items.TNT_MINECART;
        Intrinsics.checkNotNullExpressionValue(item, "TNT_MINECART");
        InventoryUtil.INSTANCE.swapToItemAndRun(item, INSTANCE.getSwitchMode(), l2, () -> BowMinecart.onAfterSendPacket$lambda$0$lambda$1(blockPos));
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
                        BowMinecart.a = ZkmKeys.a(7968827108016304419L, 3547659086180938873L, MethodHandles.lookup().lookupClass()).a(242243382955606L);
                        var20 = BowMinecart.a ^ 134073708646175L;
                        BowMinecart.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[25];
                        var16_4 = 0;
                        var15_5 = "(\u00a9\u0000\u0099\u00aa\u0095\u0006\u00eb\u0088\\\u00a7}\u00fb\u00ff\u000b\u000b\u00b4\u00142;\u00b3\u00f5H9\u00c2bo\u0081OU+\u0011\u00f5:\u00d0B\u001e\u0084\u00fb\u0098=\u00ca~\f~\u00e3\u00a0\u00dd\u0013\u00ff\u0088\u00da\u00eb\u00d40g \u0097m\u0096\u0016n(]\u00d0\u0018l\u00d7\u001f\u0082\u00c2UX\u00abkg\u00dc\u0010!\u00d8\u0080*\u00adl\n\u00e9\u009d\\\u0013 \u00e8\u00c8lm\u0083\u00d5H\u0011\u00c7\u0081\u0012\u0086\u0092\u00f0}\u00de;QT\u007f00\\\u00fc\u00a4\u00f3$\u0015\u00fb\u00d5\u00b3V \u00e1`Z\u0018\tX\u00af\u00e7r\u009d\u00ef\u0095\u00da\u00c2F\u0007\n\u00b1\u00f5\u00b5\u00d4\u00ff\u00d3\u0000\u00b6\u000f\u0087\u00aaKn\u00e8\u0007\u0018\u00ff\u00b3\u0000\b\u0086\u00a4\u00b2\u00f1E;\u007f\u0013\u007f\u000e\u00a2<\u00f9.\u00fe3\u0097\u00c3\u0006%\u00109\\kXq\u00e34\u00b2\u008b\u0016\u00f6\u00f8J+\u00cd\u00b3\u0018\u0084<\u00f9\r\u00b2\u0002\u0081\u008e\u001bWO\u00d4D\u00a4j\u00a05\u000b\u00a1Ve\u00e2\u00b6w(}N$\u008a\u00b9\u00b6\u00deqj\u000f{\u0095\u0087DC.5jc\u00b2\u00b2^^ \u00e6G\u0084$Q\u00b7\u00c7\u00ce.r\u00ba\u00d2\u000e\u0002o  ^\u00f4\u00cd*\u00af\u0016B\u0007\u0091\u00a0\u0085\u008cB3\u00c5ciC\u0016eW9n,\u00e0<\u001d\u0018H\u0019\u00c0\u00ae(\u008b{\u00be\u00b3\u00ee\u0097\u0096\u0010p\u001c\u00af\u0098Z\u009b\u0093\u00a4\u0095t\u00a1\u00b67S\u00f7\f\u0002N|Z\u0081Ci\u00ed\u00c2S|f\u000f\u00bc^g\u0010\u001c\u00a4]l\u00e2\u00ee\u00ad-\u0092\u0018\u0016\u00c2\u0081\u009e\n\u009e\u0010\u00a09Q\u00ee;\u0003\u0002\u00bc|w\u00a2?\u0016E\u00ceB\u0018c\u001b\u00dau(L\u009e\u00fc (qE\u0089\u0002\n\u00faF\u00af\u00be\u0004\u009a+\u00eb\u0018\u0018Ym\u0097\u00bf\u00f9\u0003\u00d9q\u00b0u\f\u001b\u00db>|Ws\u008fwX\u00d5\u00f5\u00908(\u00c1\u00d9\u0007\u0099G\u00f0Lo\u00b3\u00c4\u0018L\u0002&\u00acs\u0091e\u009f\u00b36D\u00be\u00c1\u00f6\u00f6\u00ce!\u00c2\\\u0093\u009bIK:\u00abS,c\u00d2 {,MR\u00fc\u00c1\u00e0\u00deq\u00a9\u00b2\u0003\u00d9\u00d1\u00aa]=8\u007fUM\u0087E\u00b7g\u0003\u0012\u00f5Q\u00e7\u0005  \u009c\u00cc\u00b8\u0016\u00ff\u00f3\u0013e\u00df\"I\u009c\u00b6\u009d\u00d9P\u00c3\u0002\u00be\u00ce!\u00e6\u00b6\u00a5\u00fd\u0010\u00f9\u00b4\fb\u00fel(\u00afx!\u0088\u00de\u0015g\u0083|\u00b9\u00c9\u008f\u00c6F\u00810\u00ce\u00b1E\u00d6[@\u00a6\u001aF\u00f6\b\u0013P\u0085\u009b\u00ef\u00f6\u00e0\u0016\u00c6\u0094\u0013KT \u00e31\u00f8\u00a94$\u00e8\u0096\b\u00ec\u00e2\u00caS\u00b4^XD\u0082\u00f7\u009a\u0097#Sh$\u0081\u00bdZ\u00fc\u009c\u00c1s \u00f9|#\u00d4\u00d9\u008c%\u0080\u00c4\u00ab\u00b5\u00f3 \u00f8\nX\u0086\u00ea\u0080\u00b1P!f\u0002\u00d1_\u00b7\u00a0F\u00bb\u00ac\u0013\u0018\u00d9% \u00b4\u00e7\u00d0\u00a7\u00b1\u0000~#\u00b8KG\u00e9\u0003\u0085\u00c3\u00f5\u0015|\u00df)\u00e9\u0010+)\u00f8\u000e\u0081g\u00e2i{\u009c\u00d8\u00b4\u00b3\u00f9u\u00e5\u0018\u0085\r\u00ae\u009d\u00ec\u00b5\u00ce\u0086$\u00de\u00e1pO\u00de\u0007R+\u00ad8H\u0091q\u00d9~";
                        var17_6 = 702;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = BowMinecart.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c4\u0016w\u00e3\u009e\u001c>\t\u0081\u00cc\u00ab\u00ff\u008f\u00ad\u00e6\u00f7$D\u00e18p\u0002H\u00a4-\u00a5\u008f|\u00b3\u0004T\u00e3 dN\u009cA\u0011Vg\u0004\u00cc\u00b6\u00fd\u0003\u00b6\u00afQ\u00a4\u000fr\u0098d\u00a4\u00f9<\u00e8:4\u00c57\u00f1\u00be*\n";
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
                            var18_3[var16_4++] = BowMinecart.zkmDecodeUtf8(var19_9).intern();
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
                BowMinecart.b = var18_3;
                BowMinecart.c = new String[25];
                BowMinecart.g = new HashMap<K, V>(13);
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
                var4_14 = "h\u0013\u000f\u00ed\u00c25\u00ef\u00b6\u0083d\u00f9\u000fA\u001c\u00df LvH\u00d6H\u00b8\u00bd\u00c8";
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
                    var4_14 = "\u00c6\u00d6\u008e\u0005\u00bc\u00d7d\u0088\u00c8\u00d3]\u0086\u001f\b\u0015\u0004";
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
        BowMinecart.e = var6_12;
        BowMinecart.f = new Integer[5];
        var28_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(BowMinecart.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BowMinecart.class, "selfDamageCheck", "getSelfDamageCheck()Z", 0))};
        BowMinecart.delegatedProperties = var28_20;
        BowMinecart.INSTANCE = new BowMinecart();
        BowMinecart.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)BowMinecart.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
        BowMinecart.selfDamageCheck$delegate = SettingFactory.booleanSetting$default((SettingContainer)BowMinecart.INSTANCE, "check-self-damage", false, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6BB5;
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
                throw new RuntimeException("su/catlean/uz", exception);
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
            BowMinecart.c[n2] = BowMinecart.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BowMinecart.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1C3;
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
                throw new RuntimeException("su/catlean/uz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BowMinecart.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BowMinecart.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/uz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BowMinecart.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BowMinecart.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

