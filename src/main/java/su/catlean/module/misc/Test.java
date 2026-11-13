/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public final class Test
extends Module {
    @NotNull
    public static final Test INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private Test() {
        super("test", Categories.getMisc(), null, null);
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        int n;
        Iterable iterable;
        BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getEyePos());
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n2 = 6;
        IntRange intRange = new IntRange(blockPos2.getX() - n2, blockPos2.getX() + n2);
        IntRange intRange2 = new IntRange(blockPos2.getY() - n2, blockPos2.getY() + n2);
        IntRange intRange3 = new IntRange(blockPos2.getZ() - n2, blockPos2.getZ() + n2);
        long l = 0L;
        long l2 = 0L;
        Iterable iterable2 = iterable = (Iterable)intRange;
        Collection collection = new ArrayList();
        Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            Iterable iterable3;
            int n3;
            int n4 = n3 = ((IntIterator)iterator2).nextInt();
            Iterable iterable4 = iterable3 = (Iterable)intRange2;
            Collection collection2 = new ArrayList();
            Iterator iterator3 = iterable4.iterator();
            while (iterator3.hasNext()) {
                Iterable iterable5;
                int n5;
                int n6 = n5 = ((IntIterator)iterator3).nextInt();
                Iterable iterable6 = iterable5 = (Iterable)intRange3;
                Collection collection3 = new ArrayList();
                Iterable iterable7 = iterable6;
                Iterator iterator4 = iterable7.iterator();
                while (iterator4.hasNext()) {
                    int n7;
                    int n8;
                    int n9 = n8 = (n7 = ((IntIterator)iterator4).nextInt());
                    DamageUtil.INSTANCE.t(false);
                    long l3 = Globals.now();
                    long l4 = l2;
                    Vec3d vec3d = new BlockPos(n4, n6, n9).toCenterPos().add(0.0, 0.5, 0.0);
                    Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
                    float f = DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, vec3d, null, 0, true, null, false, 54, null);
                    Unit unit = Unit.INSTANCE;
                    l2 = l4 + ((Number)TuplesKt.to(unit, Globals.now() - l3).getSecond()).longValue();
                    DamageUtil.INSTANCE.t(true);
                    long l5 = Globals.now();
                    l4 = l;
                    Vec3d vec3d2 = new BlockPos(n4, n6, n9).toCenterPos().add(0.0, 0.5, 0.0);
                    Intrinsics.checkNotNullExpressionValue(vec3d2, "add(...)");
                    float f2 = DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, vec3d2, null, 0, true, null, false, 54, null);
                    unit = Unit.INSTANCE;
                    l = l4 + ((Number)TuplesKt.to(unit, Globals.now() - l5).getSecond()).longValue();
                    if (!(f == f2)) {
                        ModuleMessages.S(INSTANCE, new BlockPos(n4, n6, n9) + " " + f + " != " + f2, false, null);
                    }
                    Unit unit2 = Unit.INSTANCE;
                    collection3.add(unit2);
                }
                Iterable iterable8 = (List)collection3;
                CollectionsKt.addAll(collection2, iterable8);
            }
            Iterable iterable9 = (List)collection2;
            CollectionsKt.addAll(collection, iterable9);
        }
        int n10 = n = (int)((float)(l2 - l) / (float)Math.max(l2, 1L) * 100.0f);
        long l6 = l;
        long l7 = l2;
        ModuleMessages.S(this, "time: " + l7 + "; optimized: " + l6 + ", saved: " + n10 + "%", false, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        Test.a = ZkmKeys.a(-6087483583114611890L, 1560149138984422937L, MethodHandles.lookup().lookupClass()).a(79624201852193L);
                        var20 = Test.a ^ 91727540528158L;
                        Test.d = new HashMap<K, V>(13);
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
                        var15_5 = "\r\u00ae\u00fa\u00da\u0010\u00d6\u0087\u0011\u00fd[\u008f\u0082\u00f2\u001d!\u0002JHVJA\u00f0a\u00bd\u008f\u00d6\u0094g\u00b8\u00a4\u00fd&\u0018\u0010dr\u0096\u00d1-\u0097\u00b0\u00e2\u00df&\u00ec3\u00c9\u00b9\u00e7\n\u00ef]\u00b5x\u001e\u007f\u0004 \u001f:\u008f\u00c5\u00f1\u0096\u00aa\u00dcd\u0096\u00ce\u0081\u008d\u00dd!\u0002V\u00f0_\u00b3F\u00d0\u00a3\u0002\u00dd\u0084>K\u009f\u00ad\u00b8\u00a1\u0018\u0014\u008c\u001d\u009cW\u00b6\u0010\u00ba\u008b\u00e4\u00e5\u00eb\u0001\u00f9q\u00d6\u00dc.\u00b8\u00eb\u00a6ghL\u0010t\u0088\u00ea\u00a7\u0098sF\u008d\u00dc]\u00efn\u00f2n)`\u0010\u0017\u00df\u00bb\u00ba6\u0014[D\u0098;\u00a0l\u00f3\u00e7\u00fb\u009a";
                        var17_6 = 149;
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
                            var18_3[var16_4++] = Test.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ba@&%\u0016\u00cc\u00aer\u00e5k\u00d6%\u0090a\u00d6\u00b4-\u00fd\u00fd\u00e2\u0088\u0007~\u0002JMkhX\u00b9\u00bb\u00b4\u0010\u0098l&_\u00f3tFU\u00d4\u00db\u00cf\u0098af{\u00a5";
                            var17_6 = 49;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = Test.zkmDecodeUtf8(var19_9).intern();
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
                Test.b = var18_3;
                Test.c = new String[8];
                Test.g = new HashMap<K, V>(13);
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
                var4_14 = "\u00aa\t[D.\t\u0093;\u00c4\u0001\t\u00ac\u00ab\\jy\u00f1t_\u00be\u00f3\u001a\u00883";
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
        Test.e = var6_12;
        Test.f = new Integer[3];
        Test.INSTANCE = new Test();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5370;
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
                throw new RuntimeException("su/catlean/ky", exception);
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
            Test.c[n2] = Test.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Test.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ky" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x40D1;
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
                throw new RuntimeException("su/catlean/ky", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Test.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Test.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ky" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Test.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Test.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

