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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.module.RotatingModule;
import su.catlean.module.attack.HoleFillerMode;
import su.catlean.module.attack.HoleFillerPreferBlocksMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.HoleManager;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;

public final class HoleFiller
extends RotatingModule {
    @NotNull
    public static final HoleFiller INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ModeSetting preferBlocks$delegate;
    private static final long c;
    private static final String[] h;
    private static final String[] l;
    private static final Map o;
    private static final long[] t;
    private static final Integer[] y;
    private static final Map B;

    private HoleFiller() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 12;
        boolean bl = false;
        int n2 = 0;
        Category category = Categories.getAttack();
        super("hole-filler", category, n2, bl, n, defaultConstructorMarker);
    }

    private final HoleFillerMode getMode() {
        return (HoleFillerMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final HoleFillerPreferBlocksMode getPreferBlocks() {
        return (HoleFillerPreferBlocksMode)((Object)preferBlocks$delegate.getValue(this, delegatedProperties[1]));
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l;
        long l2 = l = c ^ 0x4FA9FDB0447L;
        long l3 = l2 ^ 0x28F820F40E97L;
        long l4 = l2 ^ 0x76AA94A242ACL;
        RotatingModule.s(this, this.getFillPositions(l3), false, false, null, false, 30, l4, null);
    }

    private final List getFillPositions(long l) {
        long l2 = (l = c ^ l) ^ 0x1B10E0325343L;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        List list = this.findNearbyHoles();
        if (this.getMode() == HoleFillerMode.DEFAULT) {
            return list;
        }
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        for (BlockPos blockPos : list) {
            block1: for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
                if (Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getMc().player)) continue;
                Intrinsics.checkNotNull(abstractClientPlayerEntity);
                if (Friends.getIsHomie(abstractClientPlayerEntity)) continue;
                ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                Intrinsics.checkNotNull(clientPlayerEntity);
                if (abstractClientPlayerEntity.distanceTo(clientPlayerEntity) > 12.0f || abstractClientPlayerEntity.getY() < (double)blockPos.up().getY()) continue;
                int n2 = (int)Math.ceil((double)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null) * 1.5 / 50.0);
                if (n2 <= 0) {
                    if (this.getPos(abstractClientPlayerEntity).distanceTo(blockPos.toCenterPos()) > 2.0) continue;
                    arrayList.add(blockPos);
                    continue;
                }
                int n3 = 0;
                if (n3 > n2) continue;
                while (true) {
                    Vec3d vec3d;
                    if (!((vec3d = InterpolationTracker.INSTANCE.predictPosition(abstractClientPlayerEntity, n3)).distanceTo(blockPos.toCenterPos()) > 2.0)) {
                        arrayList.add(blockPos);
                    }
                    if (n3 == n2) continue block1;
                    ++n3;
                }
            }
        }
        return arrayList;
    }

    private final List findNearbyHoles() {
        List list = new ArrayList();
        BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getEyePos());
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n = (int)Math.ceil(this.getRange());
        int n2 = blockPos2.getX() - n;
        int n3 = blockPos2.getX() + n;
        if (n2 <= n3) {
            while (true) {
                int n4;
                int n5;
                if ((n5 = blockPos2.getY() - n) <= (n4 = blockPos2.getY() + n)) {
                    while (true) {
                        int n6;
                        int n7;
                        if ((n7 = blockPos2.getZ() - n) <= (n6 = blockPos2.getZ() + n)) {
                            while (true) {
                                BlockPos blockPos3;
                                if (HoleManager.INSTANCE.isHole(blockPos3 = new BlockPos(n2, n5, n7))) {
                                    list.add(blockPos3);
                                }
                                if (n7 == n6) break;
                                ++n7;
                            }
                        }
                        if (n5 == n4) break;
                        ++n5;
                    }
                }
                if (n2 == n3) break;
                ++n2;
            }
        }
        return list;
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        long l2 = l;
        long l3 = l2 ^ 0x7387C9FF2268L;
        int n = (int)(l3 >>> 32);
        int n2 = (int)(l3 << 32 >>> 48);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0L;
        if (this.getPreferBlocks() == HoleFillerPreferBlocksMode.WEB) {
            Block[] blockArray = new Block[1];
            Intrinsics.checkNotNullExpressionValue(Blocks.COBWEB, "COBWEB");
            if (InventoryUtil.INSTANCE.findBlock(n, (short)n2, blockArray, n3).isFound()) {
                return CollectionsKt.listOf(Blocks.COBWEB);
            }
        }
        return super.getBlocks(l4);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        HoleFiller.c = ZkmKeys.a(-3642399964216823531L, -3278230875015548246L, MethodHandles.lookup().lookupClass()).a(253763653545815L);
                        var20 = HoleFiller.c ^ 62595573547703L;
                        HoleFiller.o = new HashMap<K, V>(13);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "\u00ce<K\u00c6\u0002\u0017\u00e8\u0002\u00dc\u00dbr\u00f5\u00f3\u00f6\u00e6L\u0010\u00db\u00a9ck\u00a5\u00d2\u00df\u0015\u008d?\u0096#\u00b0\u00d8\u00f8#\u0018\u0091\u00ed5>Q\u009bp]\u0084\u00c4U\u00a4\u000e\u00f0\u0081\u0090$Y\u009a\u00db\u0014\u0081y\u0088\u0010\u009a\u00b5\u00e7\u0085Xs\t6\u00a6\u00d5\u00c8k#ziM Di<\u00da>R~\u00b1YYj]\u0083T\u0099\tS\u0011]h6\u00a7\u00b2\u00f2\"}y\u001aV4u-\u0010at<\u00c7\u00d9ywt\u0096\u0093IE\u0002\\\b\u00ab 0\t\u0084\u0015\u00f3\u00e7\u00f1&\u00f8\u00a2\u0013\u00d6w\u00ccH`|\u00a4~\u00cf\u00cd\u0006\u00d1\u0090\u00afK\u00c3\u00e5\u0080\u00e7\u0016\u000f";
                        var17_6 = 158;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = HoleFiller.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a3i~m\u00c5\u0085*i\u008d\u00bb\u00bd\u0081[e1\u00fed\u00f9\u0012\u00bca\u00c7E\u00e2_g\u00fc\u00ce\u00d9\b\u00de\u001b\u0080\u00e0\u00e6O\u0010\u00fd\u00b6\u00baS\u00ee%ND\u0082\r\u00cbv\u00a3rQ\u0089\u00c0\u00d5\f\u0013\u00aa\u00e1\u007f\u0099\u00f8\u00ee\u00cd+D\u001f\u00c8\u00f5\u00a2\u0000\u00ce0z\u0015x\u00b8_;G\u00fc\u001a\u00d6\u00e8\u0003\u00ee\u0089\u00b05v\u00a3~\u009dLx\u00b1\u00c3\u00dfv\u00057\u0097\u00e8\u00a6\u00fb\u00d9u\u0091\u00e3\u00da\u0085q.'\u00a8h\u009d\u00c9\u00f4\u0001\u00bb";
                            var17_6 = 121;
                            var14_7 = 72;
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
                            var18_3[var16_4++] = HoleFiller.zkmDecodeUtf8(var19_9).intern();
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
                HoleFiller.h = var18_3;
                HoleFiller.l = new String[9];
                HoleFiller.B = new HashMap<K, V>(13);
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
                var4_14 = "t\u000f\u00b7\u00b7-g\u0002div\u0006\u008a6r\u001a\u00fa\u00c8R\u00eeP\u00b6\u00f7\u00ae\u00e3";
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
        HoleFiller.t = var6_12;
        HoleFiller.y = new Integer[3];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(HoleFiller.class, "mode", "getMode()Lsu/catlean/no;", 0)), Reflection.property1(new PropertyReference1Impl(HoleFiller.class, "preferBlocks", "getPreferBlocks()Lsu/catlean/nj;", 0))};
        HoleFiller.delegatedProperties = var26_20;
        HoleFiller.INSTANCE = new HoleFiller();
        HoleFiller.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)HoleFiller.INSTANCE, "mode", (Enum)HoleFillerMode.AROUND_PLAYERS, null, null, 12, null);
        HoleFiller.preferBlocks$delegate = SettingFactory.modeSetting$default((SettingContainer)HoleFiller.INSTANCE, "prefer", (Enum)HoleFillerPreferBlocksMode.OBBY, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1114;
        if (HoleFiller.l[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])o.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_i", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = h[n2].getBytes("ISO-8859-1");
            HoleFiller.l[n2] = HoleFiller.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return HoleFiller.l[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HoleFiller.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_i" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x755D;
        if (y[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = t[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])B.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    B.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_i", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HoleFiller.y[n2] = n3;
        }
        return y[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HoleFiller.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_i" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HoleFiller.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HoleFiller.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

