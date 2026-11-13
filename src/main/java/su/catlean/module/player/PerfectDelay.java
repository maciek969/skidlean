/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.CallSite;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.LocalPlayerAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.PerfectDelayHorseMode;
import su.catlean.module.rage.TridentBoost;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class PerfectDelay
extends Module {
    @NotNull
    public static final PerfectDelay INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting horse$delegate;
    @NotNull
    private static final BooleanSetting bow$delegate;
    @NotNull
    private static final BooleanSetting crossbow$delegate;
    @NotNull
    private static final BooleanSetting trident$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private PerfectDelay() {
        super("perfect-delay", Categories.getPlayer(), null, null);
    }

    private final PerfectDelayHorseMode getHorse() {
        return (PerfectDelayHorseMode)((Object)horse$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getBow() {
        return (Boolean)bow$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getCrossbow() {
        return (Boolean)crossbow$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getTrident() {
        return (Boolean)trident$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block6: {
            if (this.getTrident() && Globals.getPlayer().getActiveItem().getItem() instanceof TridentItem && Globals.getPlayer().getItemUseTime() > (TridentBoost.INSTANCE.getEnabled() ? TridentBoost.INSTANCE.getCooldown$catlean() : 9)) {
                Globals.getInteractionManager().stopUsingItem(Globals.getPlayer());
            }
            if (Globals.getPlayer().getActiveItem().getItem() instanceof CrossbowItem && this.getCrossbow()) {
                double d = Globals.getPlayer().getItemUseTime();
                ItemStack itemStack = Globals.getPlayer().getActiveItem();
                Intrinsics.checkNotNullExpressionValue(itemStack, "getUseItem(...)");
                if (d >= 25.0 - 0.25 * (double)this.getEfficiencyLevel(itemStack) * 20.0) {
                    Globals.getInteractionManager().stopUsingItem(Globals.getPlayer());
                }
            }
            if (Globals.getPlayer().getActiveItem().getItem() instanceof BowItem && this.getBow() && Globals.getPlayer().getItemUseTime() > 19) {
                Globals.getInteractionManager().stopUsingItem(Globals.getPlayer());
            }
            if (Globals.getPlayer().getControllingVehicle() == null || !(Globals.getPlayer().getControllingVehicle() instanceof HorseEntity)) break block6;
            if (this.getHorse() == PerfectDelayHorseMode.Rage) {
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                Intrinsics.checkNotNull(clientPlayerEntity, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
                ((LocalPlayerAccessor)((Object)clientPlayerEntity)).setJumpRidingScale(1.0f);
            }
            if (this.getHorse() == PerfectDelayHorseMode.Legit && Globals.getPlayer().getMountJumpStrength() >= 1.0f) {
                Globals.getMc().options.jumpKey.setPressed(false);
            }
        }
    }

    private final float getEfficiencyLevel(ItemStack itemStack) {
        return EnchantmentHelper.getLevel((RegistryEntry<Enchantment>)Globals.getWorld().getRegistryManager().getOptional(Enchantments.QUICK_CHARGE.getRegistryRef()).get().getEntry(Enchantments.QUICK_CHARGE.getValue()).get(), itemStack);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PerfectDelay.a = ZkmKeys.a(4988594446482109599L, 1743809862500794472L, MethodHandles.lookup().lookupClass()).a(269193059696458L);
                        var20 = PerfectDelay.a ^ 44357384567478L;
                        PerfectDelay.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[15];
                        var16_4 = 0;
                        var15_5 = "\u0007\u00df\u0090t\u0019\u00ab\u00a8q\u001bD\u0001Q \u00eb\u00b6\u00faU/\u001f\u00dc\u00ed\u0098g`{W\u00b3\u00aeM\u00cc\u0089\u00e6\u0010\u00edP\u00a9\u00be\u00c0\u00ff\u0015M0m\u0015\u0098\u00e4\u000f\u00cf\u0086\u0010\u0007\u001bY\u00a8\u00f0\u00bfu\u00e6\u00f6\u00ae\u001a\u00ea\u0098\u00a6e#\u0080\u00c5X\u008a\u001b#\u00b8\u0098w\u007fn\u001eG\u00d0/\u00a2\u000f\u0017\u0007\u0003 *f\u0015\u009d4\u00d1\u00df-r~\u00d9\u00aa\u00e2\u0086r\u00ac<C\u00b7\u0091\u0096\u00ef\u0001`\u00a3T\u00c3\u0002k\u0095\u00fc\u0082\u0088\u0086\u0012/x\u008d\u00f3\u00e5Ln\u00e3b\u0085\u001e\u0083\u008f\u00c9\u00b9\u0099\u00ef\u009c\u00ab\u00e4\u00b6\u0093X\u0099\u00f73.9\u0080i \u00fa\u00f86\u00b4\u0017\u0010\u0090WL\u00e1\u00c0\u0016\u00da\u00d8\u00bb\u008b\u00ec\u00a1\u00fd\u009bW\u0004\u0011 /\u009c\u00b7;w[}\u0080\u00fbr\u0084I\\r\u00f5*\u00cb\u000f\u0010w\u00d9\b\u00e0LB)\f\u00b4G\n\u00b1\u00f7p\u00a7Q\u0010\u00f6\u00a1\u009cZ\u00f7\u00e58x\u009drJ\u00d0\u00f0\u001a/\u009e \u008d\u0010|\u00ecB\u0015\u0010\u0094\u00fb\u00d8\u00abE'\u00a5\u00d1W4\u008e\u0010\u0080\u009b\u0017\u00c8V2P\u00c8\u00ab\u00f7\u0096\u001c \u0010.91\u00c9\u00f7\u00a1\u00a1\u0014\u00c1\u008f\u00d2\u00abm\u00c2f\u00a6\u0010\u0000O\u000f\u00d2\u00b3\\\u00ae\u00bf\u00da\u00a7\u0098I+\u00ae\n\u00fd\u0018W\u00ec\u00b8D`\u001b\u00ce\u000f\u008f\u00e6\u0097\u00b7|\u00b9\u00e6(\u00ba\u001c\u00b3\u0013\u00c6y\u00cf\u00be \u0081s\u00a4t?<\u0096\u00ae\u008d\u00d6x\"D\u00e2\u0003\u00a6\u00ad?U\u0002\u00db\u00ee\u00e3\u0015\u00a4\u00a4\u009d \u00bb{\u00ad\u00140/H\u0096)\u00f4\u00c8h\u00aa\u0081\u008e)\taz\u00e3\u00d4)\u008a\u00b5\\\u008ch\u00d5X\u0094\u00b4\u0004\u0092\u00c7:\u0085\u00a7\u0099\u008cg'G\b\u0007\u000f\u0087h6\u00a1\u00ef0[\u0096 \\\u00b6\u00da)\u00f1$\u00c0\u0086\u0002\u001b\u009b\u0081M\u00deW\u001b\u0090\u00a3S\u00f1O\fi6\u00f2*\u00a6\u00c5\u00b0\u008f\u00e16";
                        var17_6 = 436;
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
                            var18_3[var16_4++] = PerfectDelay.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "I ?\u00c6\u00ff\r\u0015\u00b6\u000f;\u00acu\u00c81\u00d5\u008fh\u00d9\u0087\u0014ep\u00a1\u0012 j\u00b1\u00da\u00e0\u00d6\u00dc\u001c\u0018/\n $B\u000eF\u00e4\u00e7\u0010\u00e8\u00b2\u00cf\u00bfYYq\u00fcB \u0095+\u0013!";
                            var17_6 = 57;
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
                            var18_3[var16_4++] = PerfectDelay.zkmDecodeUtf8(var19_9).intern();
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
                PerfectDelay.b = var18_3;
                PerfectDelay.c = new String[15];
                PerfectDelay.g = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u0089,\u00c2$\u0013\u00ac1\u00b7\u00dc\u0011_Dg\u00a1\u00f9P\u00deRc,&\u009d\u0098\u00d2\u00e8b\u00ea\u00bf\u00dcW\u0015\u00f3";
                var5_15 = 32;
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
                    var4_14 = "tk\u00e6\u0097^t>^\u0013\u00a2\\H\u00f1}\u00eb\u00dc";
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
        PerfectDelay.e = var6_12;
        PerfectDelay.f = new Integer[6];
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(PerfectDelay.class, "horse", "getHorse()Lsu/catlean/s2;", 0)), Reflection.property1(new PropertyReference1Impl(PerfectDelay.class, "bow", "getBow()Z", 0)), Reflection.property1(new PropertyReference1Impl(PerfectDelay.class, "crossbow", "getCrossbow()Z", 0)), Reflection.property1(new PropertyReference1Impl(PerfectDelay.class, "trident", "getTrident()Z", 0))};
        PerfectDelay.delegatedProperties = var28_20;
        PerfectDelay.INSTANCE = new PerfectDelay();
        PerfectDelay.horse$delegate = SettingFactory.modeSetting$default((SettingContainer)PerfectDelay.INSTANCE, "horse", (Enum)PerfectDelayHorseMode.Legit, null, null, 12, null);
        PerfectDelay.bow$delegate = SettingFactory.booleanSetting$default((SettingContainer)PerfectDelay.INSTANCE, "bow", true, null, null, 12, null);
        PerfectDelay.crossbow$delegate = SettingFactory.booleanSetting$default((SettingContainer)PerfectDelay.INSTANCE, "crossbow", true, null, null, 12, null);
        PerfectDelay.trident$delegate = SettingFactory.booleanSetting$default((SettingContainer)PerfectDelay.INSTANCE, "trident", true, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3152;
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
                throw new RuntimeException("su/catlean/e9", exception);
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
            PerfectDelay.c[n2] = PerfectDelay.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PerfectDelay.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x270C;
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
                throw new RuntimeException("su/catlean/e9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PerfectDelay.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PerfectDelay.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PerfectDelay.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PerfectDelay.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

