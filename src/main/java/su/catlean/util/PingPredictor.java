/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.ServerInfo;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PingPredictor
implements Manager {
    @NotNull
    public static final PingPredictor INSTANCE;
    private static boolean Y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private PingPredictor() {
    }

    public final int getPredictionTicks(@NotNull PlayerEntity playerEntity, long l) {
        long l2 = l = a ^ l;
        int n = (int)((l2 ^ 0xBE1C4609ECBL) << 48 >>> 48);
        long l3 = l2 ^ 0x427AB0248A11L;
        long l4 = l3 >>> 16;
        int n2 = (int)(l3 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(playerEntity, "target");
        int n3 = ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l4, 1, (char)n2, null);
        int n4 = ServerInfo.INSTANCE.getPing((short)n, playerEntity);
        float f = 0.12857f * (float)(n3 + n4) - 7.0f;
        return RangesKt.coerceIn(MathKt.roundToInt(f), 0, 100);
    }

    /*
     * Unable to fully structure code
     */
    public final boolean isInHole(@NotNull PlayerEntity var1_1, int var2_2, byte var3_3, int var4_4) {
        block9: {
            block10: {
                var5_5 = ((long)var2_2 << 32 | (long)var3_3 << 56 >>> 32 | (long)var4_4 << 40 >>> 40) ^ PingPredictor.a;
                v0 = ZkmReferences.zkmBootstrap("\u00c5", (long)-8578196838058570787L, (long)var5_5);
                Intrinsics.checkNotNullParameter(var1_1, (String)PingPredictor.zkmBootstrap("y", (int)14008, (long)(982264501000663807L ^ var5_5)));
                var12_6 = v0;
                v1 = BlockPos.ofFloored(AntiKnockBack.INSTANCE.getPos(var1_1));
                Intrinsics.checkNotNullExpressionValue(v1, (String)PingPredictor.zkmBootstrap("y", (int)3170, (long)(8053679337157150755L ^ var5_5)));
                var13_7 = v1;
                var14_8 = var13_7.getX() - 2;
                var15_9 = var13_7.getX() + 2;
                v2 = var14_8;
                if (var12_6 == null) break block9;
                if (v2 > var15_9) break block10;
                do {
                    block8: {
                        block11: {
                            if ((var16_10 = var13_7.getY() - 1) > (var17_11 = var13_7.getY() + 4)) break block11;
                            block1: while (true) {
                                block12: {
                                    var18_12 = var13_7.getZ() - 2;
                                    var19_13 = var13_7.getZ() + 2;
                                    v2 = var18_12;
                                    v3 = var19_13;
                                    if (var12_6 == null) break block8;
                                    if (v2 > v3) break block12;
                                    block2: while (true) {
                                        block13: {
                                            var20_14 = new BlockPos(var14_8, var16_10, var18_12);
                                            v2 = (int)var1_1.getBoundingBox().intersects(new Box(var20_14));
                                            if (var12_6 == null) ** GOTO lbl46
                                            if (v2 == 0) break block13;
                                            v2 = (int)Intrinsics.areEqual(Globals.getWorld().getBlockState(var20_14).getBlock(), Blocks.COBWEB);
                                            if (var12_6 != null && var3_3 >= 0 && var12_6 != null) {
                                                if (v2 != 0) {
                                                    return true;
                                                } else {
                                                    ** GOTO lbl32
                                                }
                                            }
                                            ** GOTO lbl35
                                        }
                                        do {
                                            v2 = var18_12;
lbl35:
                                            // 2 sources

                                            v3 = var19_13;
                                            v4 = var12_6;
                                            if (var2_2 < 0) ** GOTO lbl49
                                            if (v4 == null) ** GOTO lbl47
                                            if (v2 == v3) break block2;
                                            ++var18_12;
                                            if (var12_6 != null) continue block2;
                                        } while (var2_2 < 0);
                                        break;
                                    }
                                }
                                do {
                                    v2 = var16_10;
lbl46:
                                    // 2 sources

                                    v3 = var17_11;
lbl47:
                                    // 2 sources

                                    if (var2_2 < 0) break block8;
                                    v4 = var12_6;
lbl49:
                                    // 2 sources

                                    if (v4 == null) break block8;
                                    if (v2 == v3) break block1;
                                    ++var16_10;
                                    if (var12_6 != null) continue block1;
                                } while (var4_4 <= 0);
                                break;
                            }
                        }
                        v2 = var14_8;
                        if (var12_6 == null) break block9;
                        v3 = var15_9;
                    }
                    if (v2 == v3) break block10;
                    ++var14_8;
                } while (var12_6 != null);
                if (var3_3 > 0) {
                    // empty if block
                }
            }
            v2 = 0;
        }
        return (boolean)v2;
    }

    public final boolean isSurrounded(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "<this>");
        BlockPos blockPos = BlockPos.ofFloored(AntiKnockBack.INSTANCE.getPos(playerEntity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n = blockPos2.getX() - 2;
        int n2 = blockPos2.getX() + 2;
        if (n <= n2) {
            while (true) {
                int n3;
                int n4;
                if ((n4 = blockPos2.getY() - 1) <= (n3 = blockPos2.getY() + 4)) {
                    while (true) {
                        int n5;
                        int n6;
                        if ((n6 = blockPos2.getZ() - 2) <= (n5 = blockPos2.getZ() + 2)) {
                            while (true) {
                                BlockPos blockPos3 = new BlockPos(n, n4, n6);
                                if (playerEntity.getBoundingBox().intersects(new Box(blockPos3)) && Intrinsics.areEqual(Globals.getWorld().getBlockState(blockPos3).getBlock(), Blocks.SWEET_BERRY_BUSH)) {
                                    return true;
                                }
                                if (n6 == n5) break;
                                ++n6;
                            }
                        }
                        if (n4 == n3) break;
                        ++n4;
                    }
                }
                if (n == n2) break;
                ++n;
            }
        }
        return false;
    }

    @NotNull
    public final List isPhased(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "<this>");
        double d = BlockPos.ofFloored(playerEntity.getX(), MathKt.roundToInt(playerEntity.getY()), playerEntity.getZ()).getY();
        Box box = playerEntity.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue(box, "getBoundingBox(...)");
        Box box2 = box;
        BlockPos[] blockPosArray = new BlockPos[4];
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(box2.minX, d, box2.minZ), "containing(...)");
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(box2.minX, d, box2.maxZ - (double)1.0E-5f), "containing(...)");
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(box2.maxX - (double)1.0E-5f, d, box2.minZ), "containing(...)");
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(box2.maxX - (double)1.0E-5f, d, box2.maxZ - (double)1.0E-5f), "containing(...)");
        Set<BlockPos> set = SetsKt.setOf(blockPosArray);
        return CollectionsKt.toList((Iterable)set);
    }

    public final boolean raycastBlocks(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)PingPredictor.zkmBootstrap("y", (int)17558, (long)(0x49A782AE520C30B0L ^ l))));
        Intrinsics.checkNotNullParameter(vec3d2, (String)((Object)PingPredictor.zkmBootstrap("y", (int)30954, (long)(0x45C9EBA67768CCFL ^ l))));
        return DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, vec3d, vec3d2, false, null, false, (int)PingPredictor.zkmBootstrap2("e", (int)8156, (long)(0x5CD3B9FD17AFD1D2L ^ l)), null) == null;
    }

    public final boolean isStandingOn(@NotNull PlayerEntity playerEntity, boolean bl) {
        Intrinsics.checkNotNullParameter(playerEntity, "<this>");
        return Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(AntiKnockBack.INSTANCE.getPos(playerEntity))).getBlock(), Blocks.COBWEB) && (!bl || Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(AntiKnockBack.INSTANCE.getPos(playerEntity)).up()).getBlock(), Blocks.COBWEB));
    }

    public static /* synthetic */ boolean isStandingOn$default(PingPredictor pingPredictor, PlayerEntity playerEntity, boolean bl, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5219777163910384473L, (long)l);
        boolean bl2 = n & 1;
        if (callSite != null) {
            if (bl2) {
                bl = false;
            }
            bl2 = pingPredictor.isStandingOn(playerEntity, bl);
        }
        return bl2;
    }

    public final boolean isInWeb(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "<this>");
        return Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(AntiKnockBack.INSTANCE.getPos(playerEntity))).getBlock(), Blocks.SWEET_BERRY_BUSH);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        PingPredictor.a = ZkmKeys.a(752162420255965056L, -1334067080689802843L, MethodHandles.lookup().lookupClass()).a(144219134720967L);
                        var20 = PingPredictor.a ^ 80451334492750L;
                        PingPredictor.d = new HashMap<K, V>(13);
                        PingPredictor.zkmSetFlag(false);
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
                        var18_3 = new String[8];
                        var16_4 = 0;
                        var15_5 = "\u009d\\\u00d9v\u0095G\u00b8\u00a7b_\u00f6\u00ce\u0013L\u008e\u00a7h{P89\u00a8\u00d1r:O\u0092~\u00dam\u00c3\u00e4\f}g\u00c9\u0007\u000fk!\u0010\u00b7\u00e5\u00a8\u007f\u00a5\u00c0\u00bc3y$\u00afG\u00cbQ\u0092\u00eb\u0010\u000f\u008d~\r\u000e\u0017\u0091\u00fb\u008cBr\u00ce\u00abw\u0004\u001a\u00180@\u001b\u001f\u00e0\u00cb\u0082\u009aPW\u00e5\u00ee\u00e25\u00dd \u00a5E*\u00a1\u0099J\u007f\u00c7 \u00b6B\u009e(fX\u00b1\u00b6\u00f7<\u0082\u00b5\u000e\u00a7\u00ca\u00a5!\u00f9\u00fa\u000fd\u00f1|Z>\u00d3\u0085W\u00af\u00de\b\u0095\u0010\u00d8\u00f6Y#\u00e4M\u00eb3i\u008ei\u008dp\u009c\u000e\u00cf";
                        var17_6 = 149;
                        var14_7 = 40;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = PingPredictor.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u009eix\u009d\u009f\u00e7\u00d7\u00c5\u00fe\n\u00f2\"\u007f\u00ed\u0005\u0089\u0010sF3\u0093\u00aa\t\u00a3yQD\u0087]\u009dglO";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = PingPredictor.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                PingPredictor.b = var18_3;
                PingPredictor.c = new String[8];
                PingPredictor.g = new HashMap<K, V>(13);
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
                var4_14 = "SMI\u0003\u00bf\t\u0004\u0081>\u00c7C\u0087E\u0094\u0016\u00f7}\u00ecY\u00e9\u00c6\u00a0\u00f7\u00fe";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl74:
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
        PingPredictor.e = var6_12;
        PingPredictor.f = new Integer[3];
        PingPredictor.INSTANCE = new PingPredictor();
    }

    public static void zkmSetFlag(boolean bl) {
        Y = bl;
    }

    public static boolean zkmGetFlag() {
        return Y;
    }

    public static boolean Y() {
        return true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4C4E;
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
                throw new RuntimeException("su/catlean/bi", exception);
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
            PingPredictor.c[n2] = PingPredictor.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PingPredictor.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/bi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7660;
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
                throw new RuntimeException("su/catlean/bi", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PingPredictor.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PingPredictor.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/bi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PingPredictor.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PingPredictor.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

