/*
 * Decompiled with CFR 0.152.
 */
package su.catlean;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.util.rotation.AimMode;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;

public final class tl
extends AimMode {
    @NotNull
    public static final tl INSTANCE;
    private static double g;
    private static int S;
    private static int O;
    private static double D;
    @NotNull
    private static ArrayList s;
    @NotNull
    private static ArrayList samples;
    private static double L;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map i;

    private tl() {
    }

    @Override
    @NotNull
    public Rotation calcRotation(@NotNull Entity entity, long l, @NotNull Rotation rotation, boolean bl) {
        boolean bl2;
        Intrinsics.checkNotNullParameter(entity, "target");
        Intrinsics.checkNotNullParameter(rotation, "currentRotation");
        double d = (double)entity.age / 4.0;
        double d2 = entity.getBoundingBox().getLengthX() / 2.3 * Math.sin(Math.toRadians(d));
        double d3 = entity.getBoundingBox().getLengthY() / 10.0 * Math.sin(Math.toRadians(d + 10.0));
        double d4 = entity.getBoundingBox().getLengthZ() / 2.3 * -Math.cos(Math.toRadians(d));
        Vec3d vec3d = EntityUtil.getPos(entity).add(d2, (double)entity.getHeight() / 2.0 + d3, d4);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        double d5 = MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d2.z - Globals.getPlayer().getZ(), vec3d2.x - Globals.getPlayer().getX())) - 90.0 - (double)rotation.getYaw());
        double d6 = -Math.toDegrees(Math.atan2(vec3d2.y - (EntityUtil.getPos((Entity)((Entity)Globals.getPlayer())).y + (double)Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose())), Math.hypot(vec3d2.x - Globals.getPlayer().getX(), vec3d2.z - Globals.getPlayer().getZ()))) - (double)rotation.getPitch();
        if (Math.abs(g) > 300.0 || L > 100.0) {
            g = 0.0;
            S = RandomUtil.randomInt(1, 3, false);
            D = 0.0;
            L = 0.0;
        }
        if (S > 0) {
            int n = S;
            S = n + -1;
            D = 0.0;
            float f = RandomUtil.randomFloat(0.15f, 0.4f, false);
            return new Rotation(rotation.getYaw() + f, rotation.getPitch() - f / 2.0f, false, null, 12, null);
        }
        boolean bl3 = bl2 = O > 20 && d5 > 60.0;
        if (bl2) {
            O = 0;
        }
        ClosedFloatingPointRange<Float> closedFloatingPointRange = RangesKt.rangeTo(25.0f, 35.0f);
        double d7 = bl2 ? 84.0 + (double)RandomUtil.randomFloat(RangesKt.rangeTo(-1.0f, 1.0f), false) : (double)RandomUtil.randomFloat(closedFloatingPointRange, true);
        boolean bl4 = true;
        ClosedRange closedRange = RangesKt.rangeTo(-5.1, 5.1);
        double d8 = Math.abs(RandomUtil.randomDouble(closedRange, bl4));
        if (Globals.getPlayer().age % RandomUtil.randomInt(2, 10, false) == 0) {
            d7 /= 10.0;
        }
        d5 = RangesKt.coerceIn(d5, -d7, d7);
        d6 = RangesKt.coerceIn(d6, -d8, d8);
        float f = rotation.getYaw() + (float)d5;
        float f2 = rotation.getPitch() + (float)(d6 += d5 / 20.0);
        boolean bl5 = entity.getEyePos().distanceTo(Globals.getPlayer().getEyePos()) < 1.5;
        float f3 = bl5 ? -40.0f + (float)(Globals.getPlayer().age % 100) / 25.0f + RandomUtil.randomFloat(-0.1f, 0.1f, false) : -90.0f;
        float f4 = bl5 ? 60.0f + (float)(Globals.getPlayer().age % 100) / 25.0f + RandomUtil.randomFloat(-0.1f, 0.1f, false) : 90.0f;
        g += d5;
        if (d5 < 20.0) {
            var46_25 = O;
            O = var46_25 + 1;
        } else {
            var46_25 = O;
            O = var46_25 + -1;
        }
        f2 += RandomUtil.randomFloat(RangesKt.rangeTo(-0.5f, 0.5f), false);
        s.add(Float.valueOf(f += RandomUtil.randomFloat(RangesKt.rangeTo(-2.0f, 2.0f), false)));
        samples.add(Float.valueOf((float)d5));
        if (s.size() >= 25) {
            float f5 = this.median(this.filterOutliers(s));
            L += (double)f5;
            s.clear();
        }
        boolean bl6 = false;
        ClosedRange closedRange2 = RangesKt.rangeTo(0.1, 0.3);
        D += RandomUtil.randomDouble(closedRange2, bl6);
        return new Rotation(f, RangesKt.coerceIn(f2, f3, f4), false, null, 12, null);
    }

    private final List filterOutliers(List list) {
        List list2 = new ArrayList();
        Iterator iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            float f = ((Number)iterator2.next()).floatValue();
            list2.add(Float.valueOf(f));
        }
        for (int i = 0; i < 6; ++i) {
            List list3 = new ArrayList();
            float f = Float.MIN_VALUE;
            Iterator iterator3 = list2.iterator();
            while (iterator3.hasNext()) {
                float f2 = ((Number)iterator3.next()).floatValue();
                if (f == Float.MIN_VALUE) {
                    f = f2;
                    continue;
                }
                list3.add(Float.valueOf((float)Math.abs(Math.abs((double)f2) - Math.abs((double)f))));
                f = f2;
            }
            list2 = new ArrayList(list3);
        }
        return list2;
    }

    private final float median(List list) {
        List list2 = list.stream().sorted().collect(Collectors.toList());
        int n = list2.size();
        float f = 0.0f;
        for (int i = 0; i < n; ++i) {
            float f2 = ((float)i + 1.0f) / (float)n;
            Object t = list2.get(i);
            Intrinsics.checkNotNullExpressionValue(t, "get(...)");
            float f3 = Math.abs(f2 - ((Number)t).floatValue());
            f = Math.max(f, f3);
        }
        return f;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        tl.a = ZkmKeys.a(4361498762655927363L, -2915740554165235190L, MethodHandles.lookup().lookupClass()).a(205847304981409L);
                        var20 = tl.a ^ 140263073958541L;
                        tl.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[4];
                        var16_4 = 0;
                        var15_5 = "v\u00bc\u008az\u00edg\u00c8\u00b4_\u00c1\u000b\u00a5z)\u00c6L\u0096<\u00de4\u009d\u00b9\u001f|F1\u00e2\u00e8y\u00b5\u00ba\u00db\u0010\u00c6~\u00e1O);cG\u00e5\u00c2\u00ea\u00b089x\u00c6";
                        var17_6 = 49;
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
                            var18_3[var16_4++] = tl.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d5\u0092\u0087\u0088q\u00c6\u001bP\u009a\u00c7\u009c\u00f9B\u0088\u00c1\u0093\u008b\u00acm[V4[r t\u0015\u0003SQ>\u00be]\u00faxoS\u0006\u00c9\r\u00e1v]\u00b7\u00a4Re\u00a7\u0003E\u000fH\u001dPGf\u0094";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = tl.zkmDecodeUtf8(var19_9).intern();
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
                tl.b = var18_3;
                tl.c = new String[4];
                tl.i = new HashMap<K, V>(13);
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
                var4_14 = "}\u0015\u00f0\u00b3\u000b:\u00def\u00d3\u009f\u00c28S\u00edj\u0084\u0098\u009blW\u0014\u00af\u00c6\u00ab2\u00db\u00d2\u00a8\u000b\u00c9\u00c6\u00b7T\u0012_\u00b5\u009cv7\u00ef_\u008eU\u00a7ys\u00c4\u00bfA\u00a9\u0081fu\u00e4l\u0005";
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
                    var4_14 = ":\u00c5]!\u0083\u00d0\u0088\u0018\u0099\u007f\u007fc3\u00d1\u00a5\u0015";
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
        tl.e = var6_12;
        tl.f = new Integer[9];
        tl.INSTANCE = new tl();
        tl.s = new ArrayList<E>();
        tl.samples = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5C44;
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
                throw new RuntimeException("su/catlean/tl", exception);
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
            tl.c[n2] = tl.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = tl.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5D4A;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/tl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            tl.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = tl.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/tl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(tl.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(tl.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

