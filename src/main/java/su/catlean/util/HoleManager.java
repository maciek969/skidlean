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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.module.basic.HoleAnchor;
import su.catlean.module.esp.HoleEsp;
import su.catlean.util.Globals;
import su.catlean.util.Hole;
import su.catlean.util.HoleSafety;
import su.catlean.util.HoleType;
import su.catlean.util.Manager;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class HoleManager
implements Manager {
    @NotNull
    public static final HoleManager INSTANCE;
    @NotNull
    private static final ExecutorService executor;
    @Nullable
    private static Future scanFuture;
    @NotNull
    private static final Timer timer;
    @NotNull
    private static final Set blastResistantBlocks;
    @NotNull
    private static final Set unbreakableBlocks;
    @NotNull
    private static List holes;
    @NotNull
    private static final List Q;
    @NotNull
    private static final List J;
    @NotNull
    private static final List i;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private HoleManager() {
    }

    private final int getHorizontalRange() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ a;
        return Math.min((int)HoleManager.zkmBootstrap2("f", (int)28559, (long)(0x444B60B31652A552L ^ l)), Math.max(HoleAnchor.INSTANCE.getHRange(), HoleEsp.INSTANCE.getRangeXZ()));
    }

    private final int getVerticalRange() {
        return Math.min(32, Math.max(HoleAnchor.INSTANCE.getVRange(), HoleEsp.INSTANCE.getRangeY()));
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (timer.hasPassed(1000)) {
            if (!((Collection)holes).isEmpty()) {
                holes = CollectionsKt.emptyList();
            }
            return;
        }
        scanFuture = (Future)((Object)CompletableFuture.supplyAsync(HoleManager::onPlayerUpdate$lambda$0, executor).exceptionally(HoleManager::onPlayerUpdate$lambda$1));
    }

    @NotNull
    public final List getHoles() {
        block0: {
            timer.reset();
            Future future = scanFuture;
            boolean bl = future != null ? future.isDone() : false;
            if (!bl) break block0;
            Future future2 = scanFuture;
            Intrinsics.checkNotNull(future2);
            Object v = future2.get();
            Intrinsics.checkNotNullExpressionValue(v, "get(...)");
            holes = (List)v;
        }
        return holes;
    }

    public final boolean isHole(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "pos");
        timer.reset();
        for (Hole hole : this.getHoles()) {
            if (Intrinsics.areEqual(blockPos, hole.getPos())) {
                return true;
            }
            if (hole.getShape() == HoleType.DOUBLE_X && Intrinsics.areEqual(blockPos, hole.getPos().add(1, 0, 0))) {
                return true;
            }
            if (hole.getShape() == HoleType.DOUBLE_Z && Intrinsics.areEqual(blockPos, hole.getPos().add(0, 0, 1))) {
                return true;
            }
            if (hole.getShape() != HoleType.QUAD || !Intrinsics.areEqual(blockPos, hole.getPos().add(0, 0, 1)) && !Intrinsics.areEqual(blockPos, hole.getPos().add(1, 0, 1)) && !Intrinsics.areEqual(blockPos, hole.getPos().add(1, 0, 0))) continue;
            return true;
        }
        return false;
    }

    /*
     * Loose catch block
     */
    @NotNull
    public final BlockState getBlockState(int n, int n2, int n3, int n4) {
        block10: {
            int n5;
            int n6;
            WorldChunk worldChunk;
            long l;
            block11: {
                block13: {
                    block12: {
                        int n7 = 0;
                        int n8 = 0;
                        l = ((long)n4 << 32 | (long)n7 << 48 >>> 32 | (long)n8 << 48 >>> 48) ^ a;
                        WorldChunk worldChunk2 = Globals.getWorld().getChunk(n >> 4, n3 >> 4);
                        Intrinsics.checkNotNullExpressionValue(worldChunk2, "getChunk(...)");
                        worldChunk = worldChunk2;
                        n6 = Globals.getWorld().getSectionIndex(n2);
                        if (n6 < 0) break block10;
                        n5 = n6;
                        if (n4 <= 0) break block11;
                        if (n5 >= worldChunk.getSectionArray().length) break block10;
                        break block12;
                        catch (Throwable throwable) {
                            throw throwable;
                        }
                    }
                    break block13;
                    catch (Throwable throwable) {
                        throw throwable;
                    }
                }
                n5 = worldChunk.getSectionArray()[n6].isEmpty() ? 1 : 0;
            }
            if (n5 != 0) break block10;
            ChunkSection chunkSection = worldChunk.getSectionArray()[n6];
            BlockState blockState = chunkSection.getBlockState(n & HoleManager.zkmBootstrap2("f", (int)20599, (long)(0x7ED2DDA9C5CDDAF5L ^ l)), n2 & HoleManager.zkmBootstrap2("f", (int)18929, (long)(0x2789E179FA08C371L ^ l)), n3 & HoleManager.zkmBootstrap2("f", (int)18929, (long)(0x2789E179FA08C371L ^ l)));
            Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
            return blockState;
        }
        BlockState blockState = Blocks.AIR.getDefaultState();
        Intrinsics.checkNotNullExpressionValue(blockState, "defaultBlockState(...)");
        return blockState;
    }

    private final BlockState getBlockStateAt(int n, int n2, int n3, int n4, char c, int n5) {
        long l = ((long)n4 << 32 | (long)c << 48 >>> 32 | (long)n5 << 48 >>> 48) ^ a;
        int n6 = (int)((l ^ 0x40775E80788DL) >>> 32);
        return this.getBlockState(n, n2, n3, n6);
    }

    private final boolean isReplaceable(long l, int n, int n2, int n3) {
        long l2 = (l = a ^ l) ^ 0x185A8FDD4EC9L;
        int n4 = (int)(l2 >>> 32);
        int n5 = (int)(l2 << 32 >>> 48);
        int n6 = (int)(l2 << 48 >>> 48);
        return this.getBlockStateAt(n, n2, n3, n4, (char)n5, n6).isReplaceable();
    }

    private final boolean isHoleBlock(Chunk chunk, int n, long l, int n2, int n3) {
        int n4 = (int)(((l = a ^ l) ^ 0x2F15F9820DD6L) >>> 32);
        Block block = this.getBlockState(n, n2, n3, n4).getBlock();
        Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
        Block block2 = block;
        return unbreakableBlocks.contains(block2) || blastResistantBlocks.contains(block2);
    }

    private final boolean isUnbreakable(int n, int n2, short s, int n3, char c, int n4) {
        long l = ((long)s << 48 | (long)c << 48 >>> 16 | (long)n4 << 32 >>> 32) ^ a;
        int n5 = (int)((l ^ 0x3F68EFFB821FL) >>> 32);
        return unbreakableBlocks.contains(this.getBlockState(n, n2, n3, n5).getBlock());
    }

    private final boolean isBlastResistant(int n, int n2, int n3, long l) {
        int n4 = (int)(((l = a ^ l) ^ 0x194349C05159L) >>> 32);
        return blastResistantBlocks.contains(this.getBlockState(n, n2, n3, n4).getBlock());
    }

    private final Hole countSafeSides(long l, int n, int n2, int n3) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x1D14BD5B6968L;
        int n4 = (int)((l2 ^ 0x45396C065F2CL) >>> 32);
        long l4 = l2 ^ 0x75882B72FC32L;
        int n5 = (int)(l4 >>> 48);
        int n6 = (int)(l4 << 16 >>> 48);
        int n7 = (int)(l4 << 32 >>> 32);
        long l5 = l2 ^ 0x53A38D492F74L;
        int n8 = 0;
        int n9 = 0;
        if (this.isBlastResistant(n - 1, n2, n3, l5)) {
            ++n8;
        } else if (this.isUnbreakable(n - 1, n2, (short)n5, n3, (char)n6, n7)) {
            ++n9;
        }
        if (this.isBlastResistant(n, n2, n3 - 1, l5)) {
            ++n8;
        } else if (this.isUnbreakable(n, n2, (short)n5, n3 - 1, (char)n6, n7)) {
            ++n9;
        }
        if (n8 + n9 < 2) {
            return null;
        }
        boolean bl = this.getBlockState(n, n2, n3 + 1, n4).isAir();
        boolean bl2 = this.getBlockState(n + 1, n2, n3, n4).isAir();
        if (bl && bl2) {
            if (!this.getBlockState(n + 1, n2, n3 + 1, n4).isAir()) {
                return null;
            }
            for (Vec3i vec3i : Q) {
                if (this.isBlastResistant(n + vec3i.getX(), n2 + vec3i.getY(), n3 + vec3i.getZ(), l5)) {
                    ++n8;
                    continue;
                }
                if (!this.isUnbreakable(n + vec3i.getX(), n2 + vec3i.getY(), (short)n5, n3 + vec3i.getZ(), (char)n6, n7)) continue;
                ++n9;
            }
            if (n8 != 8 && n9 != 8 && n8 + n9 != 8) {
                return null;
            }
            return new Hole(new BlockPos(n, n2, n3), n9 == 8 ? HoleSafety.SAFE : HoleSafety.UNSAFE, HoleType.QUAD);
        }
        if (bl && this.isReplaceable(l3, n, n2, n3 + 1)) {
            for (Vec3i vec3i : J) {
                if (this.isBlastResistant(n + vec3i.getX(), n2 + vec3i.getY(), n3 + vec3i.getZ(), l5)) {
                    ++n8;
                    continue;
                }
                if (!this.isUnbreakable(n + vec3i.getX(), n2 + vec3i.getY(), (short)n5, n3 + vec3i.getZ(), (char)n6, n7)) continue;
                ++n9;
            }
            if (n8 != 6 && n9 != 6 && n8 + n9 != 6) {
                return null;
            }
            return new Hole(new BlockPos(n, n2, n3), n9 == 6 ? HoleSafety.SAFE : HoleSafety.UNSAFE, HoleType.DOUBLE_Z);
        }
        if (bl2) {
            if (this.isReplaceable(l3, n + 1, n2, n3)) {
                for (Vec3i vec3i : i) {
                    if (this.isBlastResistant(n + vec3i.getX(), n2 + vec3i.getY(), n3 + vec3i.getZ(), l5)) {
                        ++n8;
                        continue;
                    }
                    if (!this.isUnbreakable(n + vec3i.getX(), n2 + vec3i.getY(), (short)n5, n3 + vec3i.getZ(), (char)n6, n7)) continue;
                    ++n9;
                }
                if (n8 != 6 && n9 != 6 && n8 + n9 != 6) {
                    return null;
                }
                return new Hole(new BlockPos(n, n2, n3), n9 == 6 ? HoleSafety.SAFE : HoleSafety.UNSAFE, HoleType.DOUBLE_X);
            }
        }
        if (this.isBlastResistant(n, n2, n3 + 1, l5)) {
            ++n8;
        } else if (this.isUnbreakable(n, n2, (short)n5, n3 + 1, (char)n6, n7)) {
            ++n9;
        }
        if (this.isBlastResistant(n + 1, n2, n3, l5)) {
            ++n8;
        } else if (this.isUnbreakable(n + 1, n2, (short)n5, n3, (char)n6, n7)) {
            ++n9;
        }
        if (n8 != 4 && n9 != 4 && n8 + n9 != 4) {
            return null;
        }
        return new Hole(new BlockPos(n, n2, n3), n9 == 4 ? HoleSafety.SAFE : HoleSafety.UNSAFE, HoleType.SINGLE);
    }

    private final List findHolesAround(int n, char c, int n2) {
        long l;
        long l2 = l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ a;
        long l3 = l2 ^ 0x36F53C000251L;
        long l4 = l2 ^ 0x243829D44A38L;
        long l5 = l2 ^ 0x4E14BC5018C2L;
        long l6 = l2 ^ 0x21761B526D99L;
        int n3 = (int)(l6 >>> 32);
        int n4 = (int)(l6 << 32 >>> 48);
        int n5 = (int)(l6 << 48 >>> 48);
        List list = new ArrayList();
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(AntiKnockBack.INSTANCE.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n6 = blockPos2.getX() - this.getHorizontalRange();
        int n7 = blockPos2.getX() + this.getHorizontalRange();
        int n8 = blockPos2.getY() - this.getVerticalRange();
        int n9 = blockPos2.getY() + this.getVerticalRange();
        int n10 = blockPos2.getZ() - this.getHorizontalRange();
        int n11 = blockPos2.getZ() + this.getHorizontalRange();
        int n12 = n6;
        if (n12 <= n7) {
            while (true) {
                int n13;
                if ((n13 = n8) <= n9) {
                    while (true) {
                        int n14;
                        if ((n14 = n10) <= n11) {
                            while (true) {
                                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getChunk(n12 >> 4, n14 >> 4), "getChunk(...)");
                                BlockState blockState = this.getBlockStateAt(n12, n13, n14, n3, (char)n4, n5);
                                if (blockState.isReplaceable() && this.isReplaceable(l3, n12, n13 + 1, n14) && this.isReplaceable(l3, n12, n13 + 2, n14)) {
                                    WorldChunk worldChunk;
                                    if (this.isHoleBlock(worldChunk, n12, l5, n13 - 1, n14) && this.countSafeSides(l4, n12, n13, n14) != null) {
                                        Hole hole;
                                        list.add(hole);
                                    }
                                }
                                if (n14 == n11) break;
                                ++n14;
                            }
                        }
                        if (n13 == n9) break;
                        ++n13;
                    }
                }
                if (n12 == n7) break;
                ++n12;
            }
        }
        return list;
    }

    private static final List onPlayerUpdate$lambda$0() {
        long l = a ^ 0x1D2C02F33A3FL;
        long l2 = l ^ 0x272370619ABBL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        return INSTANCE.findHolesAround(n, (char)n2, n3);
    }

    private static final List onPlayerUpdate$lambda$1(Throwable throwable) {
        return CollectionsKt.emptyList();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        HoleManager.a = ZkmKeys.a(8634210685257522792L, -2508606231224911979L, MethodHandles.lookup().lookupClass()).a(175533454796704L);
                        var20 = HoleManager.a ^ 121553176362570L;
                        HoleManager.d = new HashMap<K, V>(13);
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
                        var15_5 = "\u00dc\u00fc\u00c0\u00847wV`0\u00d9\u00a7!/\u001d\n\u0082\u00f6\u00c7\u0001\u00c4\u0019\u00ed\u00db^\u00f2\u008a\u009e:\u00e6\u00b4~\u008f(\u009f*\u00ce\u009e\u00ca\b\u00fd\u0001=\u00ba\u00f6\u0082\u00c8\u00f8/\u00b2b*\u00e1\u00ff>O\u00a4\u009d3\u0084\u00dfz\u00a2\u0004\u00fa\u008e\u00ff\u00ce\u00fb\u00a6\u00e0\u00f0\u001f\u00b3 \u00af\u00d9\u00dd\u00a2{\u009f\u00c1\f\f\u001f\u00d21#\u00c2\u00c8\u00b0\u00ec\u009f\u00e1I\u001f\u009aT\u00e3\u00bd\u00d4\u00c7\u008c2\u00c2'\u0092\u0010$/\u00a6h7\u00d55\u00e4o\u00e0\u00ef2A\u0081\u00ae\u0093(\u00f8Vi\u00d8\u0093E\u0012g\u0014\u00e2f/\u00e2\u0080z\u00ba\u0018c\u0011\u00dd\u00e4\u001b\u0011L##K\u0092\u000b-\u0085\u00f4<L\t\b\u001f\u00c5\"\u0000\u0018z\u00d8M-\u00d4)sL#A-Y\u00e0\u000b\u0001\u0007\u0084*]\u00b1\u0014k\u009f\u0096(AA,\b\u0002\u0017\u00a9\u009b\u00e67\u00ceI-y\u008f\u0084\u0010\u00f0(\u00ebvW*u)\u00d5{K\b\u00bdv\u00e8\u0013\u0014'#\u0000\u00fc\u00ddC \u00c1\u00cb=\u00a9\u00ef\u0005\u00b6\u0092\u0082m&\u001b\u00a5\u00a9\u0013p\u0097\u00f2\u00ba\u000fu\u00fe\u009d\u00ee\u008a\u00b6j\u00f9\u0007\u0011So";
                        var17_6 = 263;
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
                            var18_3[var16_4++] = HoleManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "1]*h\u00ad\u00c0\u00ba\u00e6\u008aLHJ\u0084\u0016\u001d\u00e2o\u008aK\n\u00fc\u00da\u008c\u0082!\u00fd\u00ae\u007f3V\u00c2\u00e9@\u00c9K\t\u00fa\u0082\u00dcm\u00ebQ\u00f2\u00cd\u009f\u0006\u0007O\u00a6\u0086\u00bd\u001dm\u00a6\u0097\u00cd\u00a3\u009c\u009e\u00db+\u00f6i\u00e4\u00d8v\u00b7S\u00d9\u00d6\u0090g\u00e5@\u00e2\u00c0(b*\u0010%\u00162\u00ec\u0005TUi\u0093} -\u00aa,\u00d07E";
                            var17_6 = 97;
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
                            var18_3[var16_4++] = HoleManager.zkmDecodeUtf8(var19_9).intern();
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
                HoleManager.b = var18_3;
                HoleManager.c = new String[10];
                HoleManager.h = new HashMap<K, V>(13);
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
                var6_12 = new long[9];
                var3_13 = 0;
                var4_14 = "\u00a4\u00de\u0007)\u00a77\u00e4\u00cfVi\u00ef\t\u00f6\u00e8\u00aaH\u0083\u00cb[\u00d5\u0096\u00d1\u00882m\u001b\u00f9\u0001\u00df\u00b9\u00a6\u00af\fK8\u0086\u00c3\u009bu9\u00a1\u009c\u001d:\u00c4\u00af\u00fe\u0088)\u0082\u00e5\f\u009b\u00ad\u00e7\u00ba";
                var5_15 = 56;
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
                    var4_14 = "\u008e\u00c0\u000f\u0003\u00bb'F\u00b6V\u00faG\u009e\u0019\u0006\u009d\u00e4";
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
        HoleManager.e = var6_12;
        HoleManager.f = new Integer[9];
        HoleManager.INSTANCE = new HoleManager();
        v15 = Executors.newVirtualThreadPerTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(v15, "newVirtualThreadPerTaskExecutor(...)");
        HoleManager.executor = v15;
        4056732573472465409L ^ var20;
        HoleManager.timer = new Timer();
        var22_20 = new Block[]{Blocks.OBSIDIAN, Blocks.ENDER_CHEST, Blocks.ANCIENT_DEBRIS, Blocks.NETHERITE_BLOCK};
        HoleManager.blastResistantBlocks = SetsKt.setOf(var22_20);
        var22_20 = new Block[]{Blocks.BEDROCK, Blocks.BARRIER};
        HoleManager.unbreakableBlocks = SetsKt.setOf(var22_20);
        HoleManager.holes = CollectionsKt.emptyList();
        var22_20 = new Vec3i[]{new Vec3i(-1, 0, 1), new Vec3i(0, 0, 2), new Vec3i(1, 0, 2), new Vec3i(2, 0, 1), new Vec3i(2, 0, 0), new Vec3i(1, 0, -1)};
        HoleManager.Q = CollectionsKt.listOf(var22_20);
        var22_20 = new Vec3i[]{new Vec3i(-1, 0, 1), new Vec3i(0, 0, 2), new Vec3i(1, 0, 1), new Vec3i(1, 0, 0)};
        HoleManager.J = CollectionsKt.listOf(var22_20);
        var22_20 = new Vec3i[]{new Vec3i(0, 0, 1), new Vec3i(1, 0, 1), new Vec3i(2, 0, 0), new Vec3i(1, 0, -1)};
        HoleManager.i = CollectionsKt.mutableListOf(var22_20);
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2537;
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
                throw new RuntimeException("su/catlean/wc", exception);
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
            HoleManager.c[n2] = HoleManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HoleManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3F12;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/wc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HoleManager.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HoleManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/wc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HoleManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HoleManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

