/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.util.combat;

import com.google.common.collect.Lists;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.PendingBlocks;
import su.catlean.util.ServerInfo;
import su.catlean.util.TimedPosition;
import su.catlean.zkm.ZkmKeys;

public final class CrystalTracker
implements Manager {
    @NotNull
    public static final CrystalTracker INSTANCE;
    @NotNull
    private static final ConcurrentHashMap spawnTimes;
    @NotNull
    private static final ConcurrentHashMap attackTimes;
    @NotNull
    private static final ConcurrentHashMap placedPositions;
    @NotNull
    private static final ConcurrentHashMap g;
    private static Module[] x;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Long[] f;
    private static final Map h;

    private CrystalTracker() {
    }

    @NotNull
    public final ConcurrentHashMap A() {
        return g;
    }

    public final void onSpawn(@NotNull Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "crystal");
        this.recordSpawn(((Number)pair.getFirst()).intValue(), Globals.now());
        this.trackAttack(pair);
    }

    public final void clear() {
        spawnTimes.clear();
        attackTimes.clear();
        placedPositions.clear();
    }

    public final void removeExpired(int n, char c, char c2) {
        long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x211DE21E44AL;
        long l3 = l2 >>> 16;
        int n2 = (int)(l2 << 48 >>> 48);
        long l4 = Globals.now();
        long l5 = (long)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n2, null) * 2L;
        spawnTimes.entrySet().removeIf(arg_0 -> CrystalTracker.invokePredicate(arg_0 -> CrystalTracker.removeExpired$lambda$0(l4, l5, arg_0), arg_0));
        attackTimes.entrySet().removeIf(arg_0 -> CrystalTracker.invokePredicate2(CrystalTracker::removeExpired$lambda$2, arg_0));
        placedPositions.entrySet().removeIf(arg_0 -> CrystalTracker.invokePredicate3(CrystalTracker::removeExpired$lambda$4, arg_0));
        g.entrySet().removeIf(arg_0 -> CrystalTracker.invokePredicate4(CrystalTracker::removeExpired$lambda$6, arg_0));
    }

    public final boolean hasSpawned(int n) {
        return spawnTimes.containsKey(n);
    }

    public final long getSpawnTime(int n) {
        Long l = spawnTimes.getOrDefault(n, -1L);
        Intrinsics.checkNotNullExpressionValue(l, "getOrDefault(...)");
        return ((Number)l).longValue();
    }

    @Nullable
    public final Long recordSpawn(int n, long l) {
        return spawnTimes.putIfAbsent(n, l);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean hasFailedBreak(int n, long l) {
        long l2 = (l = a ^ l) ^ 0x563830C338C1L;
        if (!attackTimes.containsKey(n)) return false;
        Object v = attackTimes.get(n);
        Intrinsics.checkNotNull(v);
        if (!((TimedPosition)v).hasFailed(l2)) return false;
        return true;
    }

    private final void trackAttack(Pair pair) {
        attackTimes.compute(pair.getFirst(), (arg_0, arg_1) -> CrystalTracker.trackAttack$lambda$1((arg_0, arg_1) -> CrystalTracker.trackAttack$lambda$0(pair, arg_0, arg_1), arg_0, arg_1));
    }

    public final void isBlocked(@NotNull BlockPos blockPos) {
        block0: {
            Intrinsics.checkNotNullParameter(blockPos, "blockPos");
            BlockPos blockPos2 = blockPos.up();
            Intrinsics.checkNotNullExpressionValue(blockPos2, "above(...)");
            ArrayList arrayList = Lists.newArrayList(Globals.getWorld().getEntities());
            Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
            boolean bl = AutoCrystal.INSTANCE.isBlockedByEntity(blockPos2, arrayList);
            placedPositions.compute(blockPos, (arg_0, arg_1) -> CrystalTracker.isBlocked$lambda$1((arg_0, arg_1) -> CrystalTracker.isBlocked$lambda$0(blockPos, bl, arg_0, arg_1), arg_0, arg_1));
            Box box = new Box((double)blockPos.getX() - 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() - 0.5, (double)blockPos.getX() + 1.5, (double)blockPos.getY() + 3.0, (double)blockPos.getZ() + 1.5);
            if (g.containsKey(box)) break block0;
            ((Map)g).put(box, Globals.now());
        }
    }

    public final boolean isPlacedAt(@NotNull BlockPos blockPos, boolean bl) {
        boolean bl2;
        block3: {
            Intrinsics.checkNotNullParameter(blockPos, "blockPos");
            Box box = new Box((double)blockPos.getX() - 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() - 0.5, (double)blockPos.getX() + 1.5, (double)blockPos.getY() + 3.0, (double)blockPos.getZ() + 1.5);
            Map map = g;
            if (map.isEmpty()) {
                bl2 = false;
            } else {
                for (Map.Entry entry : map.entrySet()) {
                    Map.Entry entry2 = entry;
                    if (!(((Box)entry2.getKey()).intersects(box) && (bl || !Intrinsics.areEqual(((Box)entry2.getKey()).getCenter(), box.getCenter())))) continue;
                    bl2 = true;
                    break block3;
                }
                bl2 = false;
            }
        }
        return bl2;
    }

    @Nullable
    public final TimedPosition findAt(@NotNull Vec3d vec3d) {
        Object object;
        Iterator iterator2;
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Object object2 = null;
        Iterator iterator3 = placedPositions.entrySet().iterator();
        while (iterator3.hasNext()) {
            Intrinsics.checkNotNullExpressionValue(iterator3.next(), "next(...)");
            Object k = iterator2.getKey();
            Intrinsics.checkNotNullExpressionValue(k, "component1(...)");
            BlockPos blockPos = (BlockPos)k;
            Object v = iterator2.getValue();
            Intrinsics.checkNotNullExpressionValue(v, "component2(...)");
            object = (TimedPosition)v;
            if (!(vec3d.squaredDistanceTo(blockPos.toCenterPos()) < 0.3)) continue;
            PendingBlocks.INSTANCE.remove(blockPos);
            iterator3.remove();
            object2 = object;
            break;
        }
        iterator2 = g.entrySet().iterator();
        while (iterator2.hasNext()) {
            Object e = iterator2.next();
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            Object k = ((Map.Entry)e).getKey();
            Intrinsics.checkNotNullExpressionValue(k, "component1(...)");
            object = (Box)k;
            if (!(vec3d.squaredDistanceTo(((Box)object).getCenter()) < 2.25)) continue;
            iterator2.remove();
        }
        if (object2 != null) {
            ((ConcurrentHashMap.KeySetView)placedPositions.keySet()).removeIf(arg_0 -> CrystalTracker.invokePredicate5(arg_0 -> CrystalTracker.isAt(vec3d, arg_0), arg_0));
        }
        return object2;
    }

    public final boolean hasFailedPlace(long l, @NotNull BlockPos blockPos) {
        long l2 = (l = a ^ l) ^ 0x861C3644E7FL;
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        TimedPosition timedPosition = (TimedPosition)placedPositions.get(blockPos);
        return timedPosition != null ? timedPosition.hasFailed(l2) : false;
    }

    private static final boolean removeExpired$lambda$0(long l, long l2, Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "it");
        Object v = entry.getValue();
        Intrinsics.checkNotNullExpressionValue(v, "<get-value>(...)");
        return l - ((Number)v).longValue() > l2;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean removeExpired$lambda$2(Map.Entry entry) {
        long l = a ^ 0x20D82F49DDEFL;
        long l2 = l ^ 0x44EF854D2162L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(entry, "it");
        return ((TimedPosition)entry.getValue()).hasMoved(n, (short)n2, n3);
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean removeExpired$lambda$4(Map.Entry entry) {
        long l = a ^ 0x30DD1F1B79CFL;
        long l2 = l ^ 0x54EAB51F8542L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(entry, "it");
        return ((TimedPosition)entry.getValue()).hasMoved(n, (short)n2, n3);
    }

    private static final boolean invokePredicate3(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean removeExpired$lambda$6(Map.Entry entry) {
        long l = a ^ 0x54B5115FE439L;
        long l2 = l ^ 0x452FDE517F8DL;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(entry, "it");
        long l4 = Globals.now();
        Object v = entry.getValue();
        Intrinsics.checkNotNullExpressionValue(v, "<get-value>(...)");
        return (double)(l4 - ((Number)v).longValue()) > Math.max((double)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null) * 1.5, 50.0);
    }

    private static final boolean invokePredicate4(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final TimedPosition trackAttack$lambda$0(Pair pair, int n, TimedPosition timedPosition) {
        long l = a ^ 0x19B4113A60DFL;
        long l2 = l ^ 0x5096C6726A4BL;
        int n2 = (int)(l2 >>> 32);
        long l3 = l2 << 32 >>> 32;
        if (timedPosition == null) {
            return new TimedPosition(Globals.now(), 1, (Vec3d)pair.getSecond(), false);
        }
        timedPosition.incrementAttempts(n2, l3);
        return timedPosition;
    }

    private static final TimedPosition trackAttack$lambda$1(Function2 function2, Object object, Object object2) {
        return (TimedPosition)function2.invoke(object, object2);
    }

    private static final TimedPosition isBlocked$lambda$0(BlockPos blockPos, boolean bl, BlockPos blockPos2, TimedPosition timedPosition) {
        block1: {
            long l = a ^ 0x50A73FEAAB02L;
            long l2 = l ^ 0x1985E8A2A196L;
            int n = (int)(l2 >>> 32);
            long l3 = l2 << 32 >>> 32;
            Intrinsics.checkNotNullParameter(blockPos2, "<unused var>");
            if (timedPosition == null) {
                long l4 = Globals.now();
                Vec3d vec3d = blockPos.toCenterPos();
                Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
                return new TimedPosition(l4, 1, vec3d, true);
            }
            if (bl) break block1;
            timedPosition.incrementAttempts(n, l3);
        }
        return timedPosition;
    }

    private static final TimedPosition isBlocked$lambda$1(Function2 function2, Object object, Object object2) {
        return (TimedPosition)function2.invoke(object, object2);
    }

    private static final boolean isAt(Vec3d vec3d, BlockPos blockPos) {
        return vec3d.squaredDistanceTo(blockPos.toCenterPos()) < 0.3;
    }

    private static final boolean invokePredicate5(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        CrystalTracker.a = ZkmKeys.a(4988672442325891873L, -3532439275496439958L, MethodHandles.lookup().lookupClass()).a(14824217492474L);
                        var20 = CrystalTracker.a ^ 44824922767073L;
                        CrystalTracker.d = new HashMap<K, V>(13);
                        CrystalTracker.zkmSetFlag(new Module[3]);
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
                        var18_3 = new String[19];
                        var16_4 = 0;
                        var15_5 = "\u00f3N\u008e\u00cc\u00der\u00c6\u001d\u00ec\u00c1D\u0097{VV\u00df \u0092\u0088\u00b0\u00e0=\u00c5xon\u00dd$+sy\u00a5]\u00ad-\u008fZ7#\u00a9\u00d57\u00bb^y\u00b1\u0019\u00a0\u00c5\u00104\u00e8pf%y\u00e2\u0086Ze\u00839\u00ad\u00adM\u0017\u0010V\u0014\u00d4'lV\u00e2(\u0088\u00cd\u00e9\u00bfh\u00da\u00d4z m\u00efl\u0086-\u00d3\u00c6\u009b\u0088&\u001cykX\u00c9\u0092[\u0000B0\u00d7\b\u00bd\u00a9\u0098\u00b2`\u00b3 eZ\r \u00dfG\u0001\u00dcSiX\u00f2\u00ba\u00cdk\u0082)\u00a0\u00a1\u009c\u00e6\u008e\u00ee\u00d5\u00bd\u00c4\u0005\u00ff\u0001\u00fbh\u00f9J\u009d\u0018[\u0018\u00fbs\u00e6%a\u0018N\u00c6uSt\u001buW/\u008b\u0092\u0086Kpsl\u00b4G \u00dcj\u0002\u00cb\u0016\u00f1GfC\u009eO$\u0012?\u00cfkr\u0082\u0085\u0088r\u00b6\u00f3\u008e\u0088\u00db\u00dc\u009e\f\u0002\u00dc\u0011(C\u009c3\u0093\u00bd\u00bd\u00c3+\u0003\u0006\u008e\u009f\u0001a\u0098\u00c3\u008c\u0081\u00c4^\u00e5@\u00d0tS\u0083\u00919\u00ad\u00cf\u00b0z\u00cb\u00e4\u00e2\u00df\u0013\u0081$\u00b9(e\u00bf\\m\u001fcp\u00fcch}\u00d0\u001d]\u007f\u00c2\u0015E\u00ef\u00a9S\u00be\u00d4 \u00c1\u00ec\u000f\u00d4\u0016\u00e3\u00cf\u0016\u0000\u00a4'B\u00e1K\u0094\u0092 \u00cd\u00acX\f\u00cc\u00e3\u00cdg\u00b71S\u00a70\u0087\u00fb#\u00c3\u00bf\u00c2\u0083\u00fc\u0004%\u00f6\u0090O\u00bd\u00d7\u008d\u00d5\u001b\u00f1(\u00d6\u0016\u008c\u00d9\u008f\u00e8\u00cep=\u0019\u00d7R~\u00be\u00b9j|R(\u008ae\u001bd\u00de\u0080\u00c1.\u00cc\u00da>\u0013n\"\u008c}\u00ca+\u00e3\u0000\u00ca _\u00a4O\u00f6\u001e4\u0007j\u00fdt\u0086\u00c3*\u0093\u0014\u00a8\u00f0z+ X\u00a9\u00f1F\n\u00d2\u00ff\u000bQ\u00c5\u0001\u001a\u0010\u00881\u000f\u00d0\u00bdD\r\u00e4\u0010B\u00b5r%\u00fa5b\u0010\u0084c]a\u0001\u0004\u00fcwu\u001f\u00912\u00d5\\>Q(\u00c3\n\u00c0\u00a1\fo\u00d4\u00be\u00b5w(f\u00a5\u00e7\u000e\u00a9\u00b5\u00e3\u00ec\u00c7G9'\u00af<c\u009f8\u0004\u00e3-)@\u00ba\u0007\u001cuY\u00ce\u0095\u0018\u00c5\u00f8\u001d\u001b\u009f\u0091A\u000b\u00d7\u0096l\u0083\u0092\r\u00d7a>\u00c9\u00a0R\u0089n\u008f\n";
                        var17_6 = 496;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = CrystalTracker.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e5\u009f\u0018\u00d7\u00ce-$U\u009e\u00d6\n\u0081\u00f3Q\u00da\u009e\u009c8\u000fX\u00fe\u00af\u008d\\\u00cc\u00f6\u00fe?\u0095\n\u00f0< \u00f4\u009fPV.\u00150[\u00a9Y\u00a6@\u00b9\u00a6;:\u00dfF\u0010\u00dd,Z\u00ac\u00c7\u001c\u00a4IO\u00f9\u00fd\u0087\u0012";
                            var17_6 = 65;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = CrystalTracker.zkmDecodeUtf8(var19_9).intern();
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
                CrystalTracker.b = var18_3;
                CrystalTracker.c = new String[19];
                CrystalTracker.h = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "k\u009a\u00a0\u00a0\u00ec\u009b\u00cf]}\u00ab\u008b\u00b8\u00b9\u00abD\u00dd";
                var5_15 = 16;
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
        CrystalTracker.e = var6_12;
        CrystalTracker.f = new Long[2];
        CrystalTracker.INSTANCE = new CrystalTracker();
        CrystalTracker.spawnTimes = new ConcurrentHashMap<K, V>();
        CrystalTracker.attackTimes = new ConcurrentHashMap<K, V>();
        CrystalTracker.placedPositions = new ConcurrentHashMap<K, V>();
        CrystalTracker.g = new ConcurrentHashMap<K, V>();
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        x = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return x;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x423F;
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
                throw new RuntimeException("su/catlean/yk", exception);
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
            CrystalTracker.c[n2] = CrystalTracker.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalTracker.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/yk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4B6D;
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
                throw new RuntimeException("su/catlean/yk", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            CrystalTracker.f[n2] = l4;
        }
        return f[n2];
    }

    private static long b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = CrystalTracker.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/yk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalTracker.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CrystalTracker.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

