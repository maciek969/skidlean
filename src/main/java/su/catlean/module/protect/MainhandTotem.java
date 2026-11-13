/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

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
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.protect.Offhand;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.Timer;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;

public final class MainhandTotem
extends Module {
    @NotNull
    public static final MainhandTotem INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting hp$delegate;
    @NotNull
    private static final BooleanSetting pauseOnEating$delegate;
    @NotNull
    private static final Timer swapTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private MainhandTotem() {
        super("mainhand-totem", Categories.getProtect(), null, null);
    }

    private final float getHp() {
        return ((Number)hp$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final boolean getPauseOnEating() {
        return (Boolean)pauseOnEating$delegate.getValue(this, delegatedProperties[1]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block0: {
            if (!(this.getHealth() <= this.getHp()) || !swapTimer.hasPassed(1000) || Globals.getPlayer().isUsingItem()) break block0;
            this.swapToTotem(this.findTotem());
            swapTimer.reset();
        }
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
        Packet<?> packet = receivePacket.getPacket();
        if (Async.INSTANCE.isMainThread()) {
            try {
                if (packet instanceof EntitySpawnS2CPacket && Intrinsics.areEqual(((EntitySpawnS2CPacket)packet).getEntityType(), EntityType.END_CRYSTAL)) {
                    Vec3d vec3d = new Vec3d(((EntitySpawnS2CPacket)packet).getX(), ((EntitySpawnS2CPacket)packet).getY(), ((EntitySpawnS2CPacket)packet).getZ());
                    if (this.getHealth() - DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, vec3d, Globals.getPlayer(), 3, false, null, false, 56, null) <= this.getHp()) {
                        this.swapToTotem(this.findTotem());
                    }
                }
                if (packet instanceof EntityStatusS2CPacket && ((EntityStatusS2CPacket)packet).getStatus() == 35 && Intrinsics.areEqual(((EntityStatusS2CPacket)packet).getEntity(Globals.getWorld()), Globals.getPlayer())) {
                    this.swapToTotem(this.findTotem());
                }
                if (packet instanceof HealthUpdateS2CPacket && this.getHealth() <= this.getHp()) {
                    this.swapToTotem(this.findTotem());
                }
            }
            catch (Exception exception) {
                System.out.println((Object)("Mainhand Totem Error: " + exception.getLocalizedMessage()));
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void swapToTotem(int var1_1) {
        block11: {
            block9: {
                block10: {
                    var15_2 = TargetUtil.R();
                    v0 = var1_1;
                    if (!var15_2) {
                        if (v0 == -1) {
                            return;
                        }
                        v0 = (int)Globals.getPlayer().isUsingItem();
                    }
                    if (var15_2) break block9;
                    if (v0 == 0) break block10;
                    v0 = (int)this.getPauseOnEating();
                    if (!var15_2) {
                        if (v0 != 0) {
                            return;
                        } else {
                            ** GOTO lbl14
                        }
                    }
                    break block9;
                }
                v1 = InventoryUtil.INSTANCE;
                if (var15_2) break block11;
                v2 = Items.TOTEM_OF_UNDYING;
                Intrinsics.checkNotNullExpressionValue(v2, "TOTEM_OF_UNDYING");
                var14_3 = v2;
                v0 = v1.findInOpenScreen(var14_3);
            }
            if (v0 <= 1 && Offhand.INSTANCE.getEnabled()) {
                return;
            }
            v1 = InventoryUtil.INSTANCE;
        }
        v1.selectSlot(var1_1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final int findTotem() {
        boolean bl = TargetUtil.R();
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.TOTEM_OF_UNDYING, "TOTEM_OF_UNDYING");
        SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar(itemArray);
        boolean bl2 = bl;
        int n = slotResult.isFound();
        if (bl2) return n;
        if (n == 0) return -1;
        SlotResult slotResult2 = slotResult;
        if (!bl2) {
            if (slotResult2.getSlot() == Globals.getPlayer().getInventory().getSelectedSlot()) {
                return -1;
            }
            slotResult2 = slotResult;
        }
        n = slotResult2.getSlot();
        return n;
    }

    private final float getHealth() {
        return Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        MainhandTotem.a = ZkmKeys.a(1768374664013116053L, 8942642889528131806L, MethodHandles.lookup().lookupClass()).a(232414957703300L);
                        var20 = MainhandTotem.a ^ 99089770490855L;
                        MainhandTotem.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "\u00bdKn\u00a5\u00b5Fz\u00dfc\u0013\u00b4\u00b6t\u00be\u00ce%_g\u0087\u00a3c,\u00d2~C\u00ff\u00c3Jm\u00f8\u0081\u00e42!\u0093f\u00ca\u00b6`\u008e\u008d\u00a5\u0005\u0015\u00c7\u0098\f\u0006\u0010\u00b6\u008f.`\u00d4n\u0090\u008e\u00f9\u00d1\u001b\u00c31\u00eeef(DoN\"\fDC\u00a2\u009e\u00bf\u00bfk\u00ac\u00fa\u00fa<\u00b8\u0083\u000b\u009d\u00c4\u00a67A\u00ccXA1\u00a3Zc\u00b82\u0017`\u00f7*\u0018\u001aU\u0018\u00ee\u000e\u001a\u00cd\u001b\u0085c\\Ts\u00a3\u0003$\u00da\u00fc\u00ef\u0016=\n\u00f1\u009c\u00fc\u00fc\u0095(\u00cb\\I_LN\u00ccH\u00b5\u00e9/\u00c8\u000f\u00ad\u0082\u00a8\u00f5\\\u0093h\u00be\u0011\u00f9BR\u00cf\u00a3\u00eff}VH\u00b2\u0011(\u0099vG\u0091\u0003 \u00fd\u00cf\u0003\u00fb6\u0015>\u00d0\u001b@\u0006\u00f6\u00e3\u00f9\u00f6\u0001\u00ad\u00b2\u00f4IZ\u0090\u00fa\u0081\u00a5@_\u00ca`\u00e0\u00c7T(5\u008ak\\\u00a9\\\u001b\u009d\u00b0\u00f5\u00d6\u00e28\u00f4]~\u000f\u0083\u0094<\u00e6\u00c6\u00c0 \u0011\u00f8V\u0006!\u00ad \u00d74\u00a1\u0089\u00f2;\u0084\u00f2\u0095(\n\u008e\u00f9\u00ef\u00fa\u00ccd\u00aa4g\u008e.\u00c2\u0082\u0019\u008c\f\u00d1\u00d09#\u00b5bb8\u0093\u00aa\u00d7\u0090\u00bc\u00cc\u00af\u0002\u0002D/A8\u00e1\u00f4";
                        var17_6 = 287;
                        var14_7 = 48;
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
                            var18_3[var16_4++] = MainhandTotem.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00da\u00ff\u00b8\u00b27\u0016\u00d8a\u00ac>*\u00cc@\u0000\u00b9\u0005 \u00c9X\u00d3\u00f9\u0083\u00bdM\u0007?$\u00d1Pr\u0012\u00cf\u00fa\u00b9\u0013f8\u00a4\u001a{\u0082r\\F\u0004I\u00f7\u008f\u0000";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = MainhandTotem.zkmDecodeUtf8(var19_9).intern();
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
                MainhandTotem.b = var18_3;
                MainhandTotem.c = new String[10];
                MainhandTotem.g = new HashMap<K, V>(13);
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
                var4_14 = "\b\u00eb-t\u00c3\u0081|t]\u00c5\u00bf[\u0098\u0093\u0005Jq=\u00f6+\u00b9\u00ea\u00fe\u001b";
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
                    var4_14 = "\u0086P\u008e\u00b4\u00d26bd\u0003\u00d6\u0080(>2+\u00c8";
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
        MainhandTotem.e = var6_12;
        MainhandTotem.f = new Integer[5];
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(MainhandTotem.class, "hp", "getHp()F", 0)), Reflection.property1(new PropertyReference1Impl(MainhandTotem.class, "pauseOnEating", "getPauseOnEating()Z", 0))};
        MainhandTotem.delegatedProperties = var28_20;
        MainhandTotem.INSTANCE = new MainhandTotem();
        MainhandTotem.hp$delegate = SettingFactory.floatSetting$default(MainhandTotem.INSTANCE, "hp", 16.0f, RangesKt.rangeTo(0.0f, 36.0f), null, null, 24, null);
        MainhandTotem.pauseOnEating$delegate = SettingFactory.booleanSetting$default((SettingContainer)MainhandTotem.INSTANCE, "pause-while-eating", false, null, null, 12, null);
        MainhandTotem.swapTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5C67;
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
                throw new RuntimeException("su/catlean/qr", exception);
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
            MainhandTotem.c[n2] = MainhandTotem.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MainhandTotem.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7F6B;
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
                throw new RuntimeException("su/catlean/qr", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MainhandTotem.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MainhandTotem.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MainhandTotem.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MainhandTotem.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

