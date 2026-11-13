/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MaceItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AttributeSwapMaceMode;
import su.catlean.module.attack.AttributeSwapMaceMode$WhenMappings;
import su.catlean.module.attack.Criticals;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;

public final class AttributeSwap
extends Module {
    @NotNull
    public static final AttributeSwap INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final ModeSetting mace$delegate;
    @NotNull
    private static final BooleanSetting axe$delegate;
    @NotNull
    private static final BooleanSetting axeOnlyBlocking$delegate;
    @NotNull
    private static final BooleanSetting knockBack$delegate;
    @NotNull
    private static final BooleanSetting axeMaceHit$delegate;
    @NotNull
    private static final IntSetting axeMaceHitDelay$delegate;
    private static int t;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map j;

    private AttributeSwap() {
        super("attribute-swap", Categories.getAttack(), null, null);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final AttributeSwapMaceMode getMace() {
        return (AttributeSwapMaceMode)((Object)mace$delegate.getValue(this, delegatedProperties[1]));
    }

    private final boolean getAxe() {
        return (Boolean)axe$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getAxeOnlyBlocking() {
        return (Boolean)axeOnlyBlocking$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getKnockBack() {
        return (Boolean)knockBack$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getAxeMaceHit() {
        return (Boolean)axeMaceHit$delegate.getValue(this, delegatedProperties[5]);
    }

    private final int getAxeMaceHitDelay() {
        return ((Number)axeMaceHitDelay$delegate.getValue(this, delegatedProperties[6])).intValue();
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        block9: {
            SlotResult slotResult;
            SlotResult slotResult2;
            SlotResult slotResult3;
            SlotResult slotResult4;
            SlotResult slotResult5;
            SlotResult slotResult6;
            Entity entity;
            block10: {
                if (!(sendPacket.getPacket() instanceof PlayerInteractEntityC2SPacket) || !Criticals.INSTANCE.isAttackPacket((PlayerInteractEntityC2SPacket)sendPacket.getPacket())) break block9;
                entity = Criticals.INSTANCE.getTargetEntity((PlayerInteractEntityC2SPacket)sendPacket.getPacket());
                if (entity == null || entity instanceof EndCrystalEntity) {
                    return;
                }
                slotResult6 = null;
                slotResult5 = InventoryUtil.INSTANCE.g();
                slotResult4 = InventoryUtil.INSTANCE.findInHotbar(AttributeSwap::onSendPacket$lambda$0);
                slotResult3 = InventoryUtil.INSTANCE.findInHotbar(AttributeSwap::onSendPacket$lambda$1);
                slotResult2 = InventoryUtil.INSTANCE.findInHotbar(AttributeSwap::onSendPacket$lambda$2);
                slotResult = InventoryUtil.INSTANCE.findInHotbar(AttributeSwap::onSendPacket$lambda$3);
                if (!this.getAxeMaceHit() || !(Globals.getPlayer().getMainHandStack().getItem() instanceof AxeItem) || !slotResult4.isFound()) break block10;
                SlotResult slotResult7 = slotResult2.isFound() ? slotResult2 : slotResult4;
                Scheduler.schedule$default(Scheduler.INSTANCE, this.getAxeMaceHitDelay(), (byte)0, false, () -> AttributeSwap.onSendPacket$lambda$4(slotResult7, entity), 6, null);
                break block9;
            }
            if (this.getAxe() && !(Globals.getPlayer().getMainHandStack().getItem() instanceof AxeItem) && (!this.getAxeOnlyBlocking() || entity instanceof PlayerEntity && ((PlayerEntity)entity).isUsingItem() && (Intrinsics.areEqual(((PlayerEntity)entity).getOffHandStack().getItem(), Items.SHIELD) || Intrinsics.areEqual(((PlayerEntity)entity).getMainHandStack().getItem(), Items.SHIELD)))) {
                slotResult6 = slotResult5;
            }
            if (!(slotResult6 != null && slotResult6.isFound() || this.getMace() == AttributeSwapMaceMode.OFF || Globals.getPlayer().getMainHandStack().getItem() instanceof MaceItem)) {
                switch (AttributeSwapMaceMode$WhenMappings.O[this.getMace().ordinal()]) {
                    case 1: {
                        SlotResult slotResult8 = slotResult3;
                        break;
                    }
                    case 2: {
                        SlotResult slotResult8 = slotResult2;
                        break;
                    }
                    default: {
                        SlotResult slotResult8 = slotResult6 = slotResult4;
                    }
                }
            }
            if ((slotResult6 == null || !slotResult6.isFound()) && this.getKnockBack()) {
                slotResult6 = slotResult;
            }
            if (slotResult6 == null || !slotResult6.isFound()) break block9;
            t = Globals.getPlayer().getInventory().getSelectedSlot();
            slotResult6.select();
            int n = t;
            if (this.getDelay() > 0) {
                Scheduler.schedule$default(Scheduler.INSTANCE, this.getDelay(), (byte)0, false, () -> AttributeSwap.onSendPacket$lambda$5(n), 6, null);
            }
        }
    }

    @Flow
    private final void onAfterSendPacket(AfterSendPacket afterSendPacket) {
        if (afterSendPacket.getPacket() instanceof PlayerInteractEntityC2SPacket && Criticals.INSTANCE.isAttackPacket((PlayerInteractEntityC2SPacket)afterSendPacket.getPacket()) && t != -1 && this.getDelay() == 0) {
            InventoryUtil.INSTANCE.selectSlot(t);
            t = -1;
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getAxe();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getAxeMaceHit();
    }

    private static final boolean onSendPacket$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.MACE);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean onSendPacket$lambda$1(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        if (!Intrinsics.areEqual(itemStack.getItem(), Items.MACE)) return false;
        RegistryKey<Enchantment> registryKey = Enchantments.BREACH;
        Intrinsics.checkNotNullExpressionValue(registryKey, "BREACH");
        if (EnchantUtil.getEnchantmentLevel(itemStack, registryKey) < 1) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean onSendPacket$lambda$2(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        if (!Intrinsics.areEqual(itemStack.getItem(), Items.MACE)) return false;
        RegistryKey<Enchantment> registryKey = Enchantments.DENSITY;
        Intrinsics.checkNotNullExpressionValue(registryKey, "DENSITY");
        if (EnchantUtil.getEnchantmentLevel(itemStack, registryKey) < 1) return false;
        return true;
    }

    private static final boolean onSendPacket$lambda$3(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        RegistryKey<Enchantment> registryKey = Enchantments.KNOCKBACK;
        Intrinsics.checkNotNullExpressionValue(registryKey, "KNOCKBACK");
        return EnchantUtil.getEnchantmentLevel(itemStack, registryKey) >= 1;
    }

    private static final void onSendPacket$lambda$4(SlotResult slotResult, Entity entity) {
        InventoryUtil.INSTANCE.selectSlot(slotResult.getSlot());
        Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entity);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
    }

    private static final void onSendPacket$lambda$5(int n) {
        InventoryUtil.INSTANCE.selectSlot(n);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AttributeSwap.a = ZkmKeys.a(326382974442152207L, 2524609626761883131L, MethodHandles.lookup().lookupClass()).a(123538379694604L);
                        var20 = AttributeSwap.a ^ 13452122983803L;
                        AttributeSwap.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[27];
                        var16_4 = 0;
                        var15_5 = "\u00bfE\u00b3p\u00f4^S\u0003\u00bf^!\u00da\u00f7\u00dc\u00f9\u00b0\u00a2\u00f3\u001a\u00f4\u00fa\u00a6\u0019\u00b9\u0010.\u001b\u00d2\u0093\u00fe\u0011L\u00a2o\u00c4\u00e1\u0017d<\u00cb\u00ce \u009c\u00b4)\\{h\u0007\u00c1#\u00cc~Q\u00ff(\u0000\u00b1\u0096HkM\u00faR\u008cE?VTH\u00ac\u00ef\u00a6\u00f4\u0010E\u00bd\u00e7\u00b3\u008b\u008d\u0080\u00a2X\u00c2X{=\u00cf\u00ea\u008d(\u009d\u00d4x@*5\u00ea\u00dc\u00a6\u00a5]\u00b2FS\u00f8\u008f\u001d\u00c1\u00bf/l\u00c3Y\u00fcJ\u00f6\u008e\u0081\u00ce\u00bf\u00b3{A`9\u00d9\u0084\u00f4\u00a1\u00970\u00e9X\u0012\u00ab\u00f4Q\u00fa\u00a6\u009a\u00a4\u00a3T)mU[\u000b\u00ddbcPH\u00c9\u00b4\u00dfB\u00eb\u0084\u00b1W-.\u009f\u00ccq\u00fd\u00c37\u00c9\u0083\u001a\b\u00caV\u009cP\u0017\u00de \u00ae\u00db\u00a2\u009fY>\u0097\u00cd\u00f4R\f\u0019\u00b5\u0005\u00a8\u00f6\f \u00c7\u00a6\u00b0\u0001+j\u00f5)/E\u0016\u00e2\u00c9\u00a0\u0010\u00e5\u0007ZGo\u0007\"\u0000\u0007\u00b0{\u0094\u001f\u00bb\u0087\u0019\u0010ao\u00ff{y\u0088\u0004\u0083\u001e\u0096\nG\f\u0085\u00b0\u001e0\u00cbd\u008c\u00d7\u009d)\u00f2M\u009b\u00d5\u00d2\u00c6\u0097\u00e0\u00b1\u00f1A|\u00c0C\u00ce\u0094`\u0011\u0096S\u00da\u00c3\u001c\u0099\u00ec\u00a4]L\u00d8\u00c6Qv\u00ae\u00d5!\u001d\u001c\u00135\u00c7\u0082\u007f\u0010D\u00dd\u0095\u0017\u0004\u001bT\u0087\u00ce\u001e\u0095y\u009c\u00c3\u00d2\u00b0\u0010\u00ec\u00b0T\u0081^\u0099\u0088\u00f8\u00f6<Z\u00035\u0088t)\u0010\u00b94\u00b4\u00a6\u0093\u00f1\u00ebk\u00b8\u001c\u0019\u00c3F$\u00f1\u00d3\u0010\u00b8\u00ee\u000fP\u001e\u001aN\u00f1\t`\u0086%\u00a6\u00c7\u00e0G :\"\u00f6\u00b2\u0096\u00ad\u00934\u0083K\u0089/!\u001e\u001eG\u00f7j\u00a0\u00a3-\u001d\u00ff\u00fd\u0083\u00a7_\u009b\u0019\u00fa\u00ba\u00e7\u0010\u00ae\u00eb'\u00ee!\u000e@VqU4\u00f1\u0085\u00b1\u00ba\u00ce(\u00ce\u00bd\u001d]\u00b6\u00e7\u00c7Z$\u00a6\u00ab\u000f\u00ba~X\u00ec4\u00d2\u00b6\u00d0\u00c0Z\u00ac\u0005\u0012\u0085\u00d9m\u00a6\u00c6\u00da\u0082\u000b\u00b1FJ\u00dd\u00809\u00af\u0018\u00b8\f\u0016\u00e2\u0006\u0016YBZu\u00b6O^d\n\u0086\u00bbE\u00cbC\u00fb\u00be\u008f\u0084 z\ra\u00818\u00fe\u0081G\u00d0i\b\u00c5\u00a5\u001bO\u00eb\u0096\u00c6\u00af\u0087\u00b0\u0015:\u009a\u0005\u00f4\u00e6\u00a6_\u008dv\u00ad(\\_\u00af\u00b4\u00edt\u00b3\u0089\\\"\u00dd2\u00cb\u00dfn{3\u009f\u0089\u00a3\u00fb*\u0082\u00ecW\u0006o\u00c4\u008fU\u00b8\u0003fz\u0096\u00df\u000bm\u00feA\u0010\u00c5\u00fc\u00c6\u00d7\u00f2\u00aa\u00ba\u00b7\u00cf,\u00ff:f\u00d8u\u00d2 \u001f#(.\u00ca\u00e3Q6\u00c8\u00aa\u008ey\u00f1:3\u00d0\u00f0\u00c5\u0084\u0097F\u00e3\u00cfk\u008d9\u009bu\u00c3s4j\u0018\u0002\u0018\u00f5\u009f\b0Rq]\u009d\u00f5\u00c5W\u000e*\u0081Afzfb\u0095Y\u00f3 \u00c5;3\u00ad\u00d7\u0017%i\u00ae\u0097\u0087\u0011\u000fO\u00f1'<\u0080b\u00b6\u0013\u0083\u00e6<\u000btr\u00e3\u00da,\u00f6! \u00bbv\u001cS\u000b\tzkqNj\u00f1\u00ec~j]/\u00e2\u001e\u00cc\u00cf4Tt\u000b\u00f8\u00fa\u000e\u00be\u00bc\u0010\u00e2";
                        var17_6 = 696;
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
                            var18_3[var16_4++] = AttributeSwap.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00f3[c\u00cd\u00e4}f\u001alV\u0006*\u00d2VY\u0013,\u00c7\u0085\u008b\u00c2 )AN\u0094\u0015\u00ea\u00a80\u00f1j\u009d\u0013\u00b5TV\u00dd\u0001Q_H\u00aa\u0087x\u00c5\u0097\u00ee v\u00f8\u0091r\u0092J\u00fd>j\u009au\u00c8^\u00c7dHv\u0000\u00e1Q\u00a9\u00d1c\u0011G\u00d6\u00ee\u00c6\u0007\u00af</";
                            var17_6 = 81;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = AttributeSwap.zkmDecodeUtf8(var19_9).intern();
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
                AttributeSwap.b = var18_3;
                AttributeSwap.d = new String[27];
                AttributeSwap.j = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "{\u0087\u0010\u00f0\u00d3\u0018\u00d9 (\u00ef\u0015D\u00fd\u001b\u00d0\f7\u0081R^Z\u0089\u009b7\u00ef\u0010|\u00acg\u001a\u009d\u0096;J\u00f3\u00c9\u00cal\u0084\u0087lp#\u0005`\u008a{z";
                var5_15 = 48;
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
                    var4_14 = "\u0012<e\u0096\u00ba\\|\u00d3\u0007\u00f5\u00ec\t\u0002\u00bd\u00fe\u00e7";
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
        AttributeSwap.f = var6_12;
        AttributeSwap.h = new Integer[8];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "mace", "getMace()Lsu/catlean/w0;", 0)), Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "axe", "getAxe()Z", 0)), Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "axeOnlyBlocking", "getAxeOnlyBlocking()Z", 0)), Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "knockBack", "getKnockBack()Z", 0)), Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "axeMaceHit", "getAxeMaceHit()Z", 0)), Reflection.property1(new PropertyReference1Impl(AttributeSwap.class, "axeMaceHitDelay", "getAxeMaceHitDelay()I", 0))};
        AttributeSwap.delegatedProperties = var30_20;
        AttributeSwap.INSTANCE = new AttributeSwap();
        AttributeSwap.delay$delegate = SettingFactory.intSetting$default(AttributeSwap.INSTANCE, "delay", 0, new IntRange(0, 5), null, null, 24, null);
        AttributeSwap.mace$delegate = SettingFactory.modeSetting$default((SettingContainer)AttributeSwap.INSTANCE, "mace", (Enum)AttributeSwapMaceMode.ANY, null, null, 12, null);
        AttributeSwap.axe$delegate = SettingFactory.booleanSetting$default((SettingContainer)AttributeSwap.INSTANCE, "axe", true, null, null, 12, null);
        AttributeSwap.axeOnlyBlocking$delegate = SettingFactory.booleanSetting$default((SettingContainer)AttributeSwap.INSTANCE, "only-when-target-blocking", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        AttributeSwap.knockBack$delegate = SettingFactory.booleanSetting$default((SettingContainer)AttributeSwap.INSTANCE, "knock-back-item", true, null, null, 12, null);
        AttributeSwap.axeMaceHit$delegate = SettingFactory.booleanSetting$default((SettingContainer)AttributeSwap.INSTANCE, "axe-mace-hit", true, null, null, 12, null);
        AttributeSwap.axeMaceHitDelay$delegate = SettingFactory.intSetting$default(AttributeSwap.INSTANCE, "axe-mace-hit-delay", 4, new IntRange(1, 8), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        AttributeSwap.t = -1;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xAE5;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/uo", exception);
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
            AttributeSwap.d[n2] = AttributeSwap.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AttributeSwap.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x38F0;
        if (h[n2] == null) {
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
                throw new RuntimeException("su/catlean/uo", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AttributeSwap.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AttributeSwap.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/uo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AttributeSwap.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AttributeSwap.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

