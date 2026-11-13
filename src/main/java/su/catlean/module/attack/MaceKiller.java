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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Criticals;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public final class MaceKiller
extends Module {
    @NotNull
    public static final MaceKiller INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting height$delegate;
    private static int m;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private MaceKiller() {
        super("mace-killer", Categories.getAttack(), null, null);
    }

    private final int getHeight() {
        return ((Number)height$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        Entity entity;
        Packet<?> packet = sendPacket.getPacket();
        Packet<?> packet2 = packet;
        if (packet2 instanceof PlayerInteractEntityC2SPacket && Criticals.INSTANCE.isAttackPacket((PlayerInteractEntityC2SPacket)packet2) && (entity = Criticals.INSTANCE.getTargetEntity((PlayerInteractEntityC2SPacket)packet2)) != null && !(entity instanceof EndCrystalEntity)) {
            if (entity instanceof PlayerEntity && ((PlayerEntity)entity).isBlocking()) {
                return;
            }
            SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar(MaceKiller::onSendPacket$lambda$0);
            if (slotResult.isFound()) {
                if (Globals.getPlayer().getInventory().getSelectedSlot() != slotResult.getSlot()) {
                    slotResult.select();
                    m = Globals.getPlayer().getInventory().getSelectedSlot();
                }
                Vec3d vec3d = INSTANCE.findFallPosition();
                Entity entity2 = Globals.getPlayer();
                double d = INSTANCE.getPos(entity2).distanceTo(vec3d);
                Entity entity3 = Globals.getPlayer();
                if (INSTANCE.getPos(entity3).distanceTo(vec3d) > 10.0) {
                    int n = (int)Math.ceil(Math.abs(d / 10.0));
                    for (int i = 0; i < n; ++i) {
                        PacketUtil.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(false, Globals.getPlayer().horizontalCollision));
                    }
                }
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(vec3d.x, vec3d.y, vec3d.z, false, Globals.getPlayer().horizontalCollision));
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), false, Globals.getPlayer().horizontalCollision));
            }
        }
    }

    @Flow
    private final void onAfterSendPacket(AfterSendPacket afterSendPacket) {
        Packet<?> packet;
        Packet<?> packet2 = packet = afterSendPacket.getPacket();
        if (packet2 instanceof PlayerInteractEntityC2SPacket && Criticals.INSTANCE.getInteractType((PlayerInteractEntityC2SPacket)packet2) == PlayerInteractEntityC2SPacket.InteractType.ATTACK && m != -1) {
            InventoryUtil.INSTANCE.selectSlot(m);
            m = -1;
        }
    }

    private final Vec3d findFallPosition() {
        Entity entity = Globals.getPlayer();
        Vec3d vec3d = this.getPos(entity);
        int n = 2;
        int n2 = this.getHeight() / 2;
        if (n <= n2) {
            while (true) {
                Entity entity2 = Globals.getPlayer();
                Vec3d vec3d2 = this.getPos(entity2);
                Entity entity3 = Globals.getPlayer();
                Vec3d vec3d3 = this.getPos(entity3).add(0.0, (double)n * 1.8, 0.0);
                Intrinsics.checkNotNullExpressionValue(vec3d3, "add(...)");
                if (DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, vec3d2, vec3d3, false, null, false, 28, null) != null) break;
                Entity entity4 = Globals.getPlayer();
                Intrinsics.checkNotNullExpressionValue(this.getPos(entity4).add(0.0, (double)(n - 1) * 1.8, 0.0), "add(...)");
                if (n == n2) break;
                ++n;
            }
        }
        return vec3d;
    }

    private static final boolean onSendPacket$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.MACE);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        MaceKiller.a = ZkmKeys.a(-4643199976723717207L, 38002085032241359L, MethodHandles.lookup().lookupClass()).a(108338135319869L);
                        var20 = MaceKiller.a ^ 18839870619029L;
                        MaceKiller.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[7];
                        var16_4 = 0;
                        var15_5 = "\u00c4a\u00007m}\u00a4l\u0018\u00a5\u00de\u00a4n\u00bb'\u0018\u0010\u00db7\u00bbn>\u0005F\u0099\u00e9\u00ef!c\u008d\u00fb\u00cf\u00a4\u0010\rmY\u00d7 \u00b6\u0091rJ\u00ea\u00c3\u00879\u0087\u00c8d\u0018\u0095\u0001\u00c4\u00e2%)\u00ddC\u0004\u009e\u00de\u000b\u008a$\u0084\u0094\u00c0\u00a1\u0089\u00ccXE\u00d4k \u00d8\u00d9$t\u00f65D\n\u00c6\u0014\u00a1\u00ed\u0093t;\u0019\bG\u008dg\u001e\u00fdsu1\u0007\u0092\u008b\u001d\u00b5G@";
                        var17_6 = 108;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = MaceKiller.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d3I\u0090\u00d0\u00a1\u00f6S\u000e7\u009e\u00a54\u0013\u0087\u00b2\u0087\u009f\u00e8\u00b1D\u00bf~\u000f\u00ea\u00d7]\u00b8R\u00feS\u00d5\n\u0018H\u00b5Aj#\u009c;)\u00a4\u0002\u00ce\u00eb\u00f5\u0010\u00a5`\u001e#\"k\u00a1\u0005\u00bf\u00b2";
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
                            var18_3[var16_4++] = MaceKiller.zkmDecodeUtf8(var19_9).intern();
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
                MaceKiller.b = var18_3;
                MaceKiller.d = new String[7];
                MaceKiller.h = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u008d\u0017\u0006\u009c`<\u00c8H\r>\u00a1n\u00ff\u0094~\u00c4";
                var5_15 = 16;
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
                    var4_14 = "\u001a\u00d4V\u000e\u00c3\u008f$\u008d_\u00e8l)\u00f1h&4";
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
        MaceKiller.f = var6_12;
        MaceKiller.g = new Integer[4];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(MaceKiller.class, "height", "getHeight()I", 0))};
        MaceKiller.delegatedProperties = var26_20;
        MaceKiller.INSTANCE = new MaceKiller();
        MaceKiller.height$delegate = SettingFactory.intSetting$default(MaceKiller.INSTANCE, "height", 20, new IntRange(0, 200), null, null, 24, null);
        MaceKiller.m = -1;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x72BF;
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
                throw new RuntimeException("su/catlean/ua", exception);
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
            MaceKiller.d[n2] = MaceKiller.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MaceKiller.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ua" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3CA7;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ua", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MaceKiller.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MaceKiller.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ua" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MaceKiller.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MaceKiller.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

