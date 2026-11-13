/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.IceBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.EntityPushEvent;
import su.catlean.api.event.events.player.PushOutOfBlocksEvent;
import su.catlean.api.event.events.world.WaterPushEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Animation;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class NoPush
extends Module {
    @NotNull
    public static final NoPush INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting blocks$delegate;
    @NotNull
    private static final BooleanSetting entities$delegate;
    @NotNull
    private static final BooleanSetting water$delegate;
    @NotNull
    private static final BooleanSetting fishingHook$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map j;

    private NoPush() {
        super("no-push", Categories.getBasic(), null, null);
    }

    private final boolean getBlocks() {
        return (Boolean)blocks$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setBlocks(boolean bl) {
        blocks$delegate.setValue(this, delegatedProperties[0], bl);
    }

    private final boolean getEntities() {
        return (Boolean)entities$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setEntities(boolean bl) {
        entities$delegate.setValue(this, delegatedProperties[1], bl);
    }

    private final boolean getWater() {
        return (Boolean)water$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setWater(boolean bl) {
        water$delegate.setValue(this, delegatedProperties[2], bl);
    }

    private final boolean getFishingHook() {
        return (Boolean)fishingHook$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setFishingHook(boolean bl) {
        fishingHook$delegate.setValue(this, delegatedProperties[3], bl);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block2: {
            FishingBobberEntity fishingBobberEntity;
            Entity entity;
            block5: {
                int n;
                block4: {
                    int n2;
                    block3: {
                        block1: {
                            n2 = Animation.zkmIntConstant();
                            n = receivePacket.getPacket() instanceof EntityStatusS2CPacket;
                            if (n2 == 0) break block1;
                            if (n == 0) break block2;
                            n = this.getFishingHook();
                        }
                        if (n2 == 0) break block3;
                        if (n == 0) break block2;
                        n = ((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus();
                    }
                    if (n2 == 0) break block4;
                    if (n != 31) break block2;
                    entity = ((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld());
                    if (n2 == 0) break block5;
                    n = entity instanceof FishingBobberEntity;
                }
                if (n == 0) break block2;
                Entity entity = ((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld());
                entity = entity;
                Intrinsics.checkNotNull(entity2, "null cannot be cast to non-null type net.minecraft.world.entity.projectile.FishingHook");
            }
            if (Intrinsics.areEqual((fishingBobberEntity = (FishingBobberEntity)entity).getHookedEntity(), Globals.getPlayer())) {
                receivePacket.cancel();
            }
        }
    }

    @Flow
    private final void onWaterPush(WaterPushEvent waterPushEvent) {
        if (!this.getWater() || Globals.getMc().world == null || Globals.getMc().player == null) {
            return;
        }
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Vec3d vec3d = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(vec3d, "ZERO");
        Vec3d vec3d2 = vec3d;
        if (((Boolean)waterPushEvent.getState().get(FlowableFluid.FALLING)).booleanValue()) {
            Iterator<Direction> iterator2 = Direction.Type.HORIZONTAL.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
            Iterator<Direction> iterator3 = iterator2;
            while (iterator3.hasNext()) {
                Direction direction;
                Intrinsics.checkNotNullExpressionValue(iterator3.next(), "next(...)");
                mutable.set((Vec3i)waterPushEvent.getPos(), direction);
                if (!this.isFluidPushing(waterPushEvent.getFluid(), mutable, direction)) {
                    Fluid fluid = waterPushEvent.getFluid();
                    Vec3i vec3i = mutable.up();
                    Intrinsics.checkNotNullExpressionValue(vec3i, "above(...)");
                    if (!this.isFluidPushing(fluid, (BlockPos)vec3i, direction)) continue;
                }
                Vec3d vec3d3 = vec3d2.normalize().add(0.0, -6.0, 0.0);
                Intrinsics.checkNotNullExpressionValue(vec3d3, "add(...)");
                vec3d2 = vec3d3;
                break;
            }
        }
        Vec3d vec3d4 = vec3d2.normalize();
        Intrinsics.checkNotNullExpressionValue(vec3d4, "normalize(...)");
        waterPushEvent.setVec(vec3d4);
        waterPushEvent.cancel();
    }

    @Flow
    private final void onPushOutOfBlocks(PushOutOfBlocksEvent pushOutOfBlocksEvent) {
        block0: {
            if (!this.getBlocks()) break block0;
            pushOutOfBlocksEvent.cancel();
        }
    }

    @Flow
    private final void onEntityPush(EntityPushEvent entityPushEvent) {
        block0: {
            if (!this.getEntities()) break block0;
            entityPushEvent.cancel();
        }
    }

    private final boolean isFluidPushing(Fluid fluid, BlockPos blockPos, Direction direction) {
        BlockState blockState = Globals.getWorld().getBlockState(blockPos);
        return Globals.getWorld().getFluidState(blockPos).getFluid().matchesType(fluid) ? false : (direction == Direction.UP ? true : (blockState.getBlock() instanceof IceBlock ? false : blockState.isSideSolidFullSquare(Globals.getWorld(), blockPos, direction)));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        NoPush.a = ZkmKeys.a(-2209060970001580517L, -4265645779473533937L, MethodHandles.lookup().lookupClass()).a(147079701834060L);
                        var20 = NoPush.a ^ 131770210362494L;
                        NoPush.e = new HashMap<K, V>(13);
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
                        var15_5 = "\u0093\u001f\u0013\u00b6W'\u0003R\u0013tA\u00a6\u0086\u001b4C\u0003#v\u0017\u00859\u0013\u00b7\u009a\u0003\u009a\u009c\u00ae\u0095'b\u0010d\u0097\u00b2#\u00c6M\u00d7m\u00a5\u00f6\u00d4v\u009a\u0018o[ \u00ed\u00b9T\u00d3A\u0012,\u00a1\u0015\b\u00ff\u0003\u00fe0\u00bcI\u008b\nNLZUW\u00e8\u009cV\u0013\u008c\u008c\u00fc\u00a8\u00d3 O\u00ad\u0019\u0002G\u00b55\b\u00ef}i\u00193P\u00d9hD\u00e3/-\u00ac\u00ec\u00e0\u0088}\u000e\u00c5\u00b7o\u00be1\u000e\u00187U\u00c17\u00a1\u00e9\u00b3J\u0098-\u008d|]\u00fb\u00f3o\u00c5f\u00f0\u0098\u0093\"\u0096)\u0010\u00f2\u00f5\u0003\u00fd}\u00a9\u0095[+\u000f\u0083X\u00a94:\u0012 m\u0011\u00dfk\u001e,\u00b4I\u00adR\u007f\u00d7KX\u00d2\u00ccuLd\u0095\u0007~N\u00d1\u0019\u0007n\u0007%p/\u00b8\u0010\u0016(y\u001fJ?\u00f34%\u00d3\u00d13\u0006\u008d\u008c\u001f \u008ei\u00f4S{\u00c9\u00c9\u00db\u00e0\u007fa\u0095=\u00fc\u0090*\u00b5`\u00af)S\u00a21\u00e4\u0086V\u00f4\u009c\u008a\u0091R\u00dd\u0018D]]\u008c\u00d1O9\u00f8*\u0004\u008b\u0013\u0005w\u00a9\u00be%\u00df\u00d3\u00d8\u00a9\u0096E3\u0090\u00ee;C\u000f\u00c4%\u00c5C\u00f1\u00b3L)\u00c3\u00a3\u00e9\u0004\u008a\u00c1\u009e\u0081\u00de\u0018X\u0092P\u00e2\u001cK\u00ac#\u00c3T\r\u00b7q\u00a2\u0095\u00bf\u00bc\u001b\u008d\u00c2\u00e8Ww^~\u00c6H\u00d4\u008e\u0015\u00a1\u00e7SC\"\u00d5X\"\u0015T\u00994\u00f6\u00d8\u00db;\u00f1\u0018\u0095M\u00a9\u00ce\u00c3\u00f9\u00f6\u008d\u00ff5\u00d9\u00a3'\u00b5\u00dd\u00c4&\u009b\u00ab<E\u008d\u00e4\u00b9\u00c4\r\u001f@\u0085\u00d1\u000f'\u0097x\u0018\u007f\u008c\u00057P\u00ad W\u00d9\u00dd\u00b8'\u008f\u00ee)\u0007\u00f1]d\u00ebs\u00ec\u00d9\u000b>\u008f\u00a22S\u00db\u00b0\u00b3e\u000f\t\u0002\u00a6pK\u0018k.j\u0082\u00052\u0093\u00e5bW\u00f4jT\u0005\u00846\u00b9\u00ef\u00f1\u009b\u009d\u0087\u00d7a \u00dc\u00e4CP\u0092\u0085S\u0012\u00d1\u00b4F\u00cf|\u008e\u00dc\u0094\u00d2}\u00aa\u00bf\u00dc\u00ba\u001b\u00e9\u00cc\u00ba\u008e\u00fd\u0006(8\u00b4 8q\u000f\u00d6\u0084=2\u0003\u00abvC\u0087%\u00b4\u001cF[\u0011\\\u0089\u00b0\u00d8\u00d8h\u008b\u00aa\u00a4\u00b8\u00d2lE\u009a(\u00e5a\u001c\u009b:\u00de\u009b\u00b4 =\u00c9\u0084f\u00aa\u00a6\u00abp\u00d3\u00ca\u00b2_\n\u00f7.rt\u009d\u0080\u00b8\u009aL\u00c4\u00c0\u00e6\u00ea\u00e0R\u00d1\u00ce\u00f1 VdYu\u0088\u0015_\u00dd\u00d1S~\u00f3\u00ab\u00d8\u00da!l\u008f\r\u008bM\u001dP\u00b7\u00e5-\u00f4Ni\u00f0\u008e\u00b9\u0010\u00f1\u00af\u00f5\u00efe[\u00c2*\u00bf\u00e0\u0018\u00c0\u0087\u0017\u00f4O\u0010\u0090iv\u009c\u00c9Z\r\u00a6&\u009cK\u00c3\u00ca\u0014s(";
                        var17_6 = 609;
                        var14_7 = 32;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = NoPush.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d8BC\u00f44\u00a3($\u001a,%3t\u00e6\u008ez\u0018R\u00be\u00e5~\u00f3\u0001!\b\u00b23\u00d4\u00bc\u0002\u0092\u0080\u009c\u00d8T\u00dd\u00df\u00c0\u007f\u00fc\u00c8";
                            var17_6 = 41;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = NoPush.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                NoPush.b = var18_3;
                NoPush.d = new String[20];
                NoPush.j = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u00e1\u00a6\u0014_\u00b9\u009f\u00a7\u00cbH|\u008e\u00d5\u0083}~\u0096\u00fa\u000f\u000fV7\u00fe:\u00c3";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        NoPush.f = var6_12;
        NoPush.h = new Integer[3];
        var26_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoPush.class, "blocks", "getBlocks()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoPush.class, "entities", "getEntities()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoPush.class, "water", "getWater()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoPush.class, "fishingHook", "getFishingHook()Z", 0))};
        NoPush.delegatedProperties = var26_20;
        NoPush.INSTANCE = new NoPush();
        NoPush.blocks$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoPush.INSTANCE, "blocks", true, null, null, 12, null);
        NoPush.entities$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoPush.INSTANCE, "entities", true, null, null, 12, null);
        NoPush.water$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoPush.INSTANCE, "liquids", true, null, null, 12, null);
        NoPush.fishingHook$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoPush.INSTANCE, "fishing-hook", true, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x19A4;
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
                throw new RuntimeException("su/catlean/_1", exception);
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
            NoPush.d[n2] = NoPush.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoPush.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2FC0;
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
                throw new RuntimeException("su/catlean/_1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NoPush.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NoPush.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoPush.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NoPush.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

