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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class SpeedCalculator {
    @NotNull
    public static final SpeedCalculator INSTANCE;
    @NotNull
    private static final Map t;
    private static String A;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    private SpeedCalculator() {
    }

    @NotNull
    public final Map W() {
        return t;
    }

    public final void updateAll(int n) {
        Object object;
        Object object22;
        List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list, "players(...)");
        Iterable iterable = list;
        for (Object object22 : iterable) {
            object = (AbstractClientPlayerEntity)object22;
            Intrinsics.checkNotNull(object);
            INSTANCE.recordBox((PlayerEntity)object, n);
        }
        List<AbstractClientPlayerEntity> list2 = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list2, "players(...)");
        Iterable iterable2 = list2;
        object22 = iterable2;
        object = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, (int)e));
        Iterator iterator2 = object22.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
            Object object3 = object;
            object3.add(abstractClientPlayerEntity.getId());
        }
        iterable = (List)object;
        t.keySet().removeIf(arg_0 -> SpeedCalculator.invokePredicate(arg_0 -> SpeedCalculator.updateAll$lambda$0((List)iterable, arg_0), arg_0));
    }

    private final void recordBox(PlayerEntity playerEntity, int n) {
        ArrayDeque arrayDeque = t.computeIfAbsent(playerEntity.getId(), arg_0 -> SpeedCalculator.recordBox$lambda$1(SpeedCalculator::recordBox$lambda$0, arg_0));
        Intrinsics.checkNotNullExpressionValue(arrayDeque, "computeIfAbsent(...)");
        ArrayDeque arrayDeque2 = arrayDeque;
        arrayDeque2.addLast(playerEntity.getBoundingBox().expand(0.0));
        while (arrayDeque2.size() > n) {
            arrayDeque2.removeFirst();
        }
    }

    private static final boolean updateAll$lambda$0(List list, Integer n) {
        Intrinsics.checkNotNullParameter(n, "id");
        return !list.contains(n);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final ArrayDeque recordBox$lambda$0(Integer n) {
        Intrinsics.checkNotNullParameter(n, "<unused var>");
        return new ArrayDeque();
    }

    private static final ArrayDeque recordBox$lambda$1(Function1 function1, Object object) {
        return (ArrayDeque)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    SpeedCalculator.a = ZkmKeys.a(-7121557786601803158L, -5886729597098733288L, MethodHandles.lookup().lookupClass()).a(6028895574914L);
                    var14 = SpeedCalculator.a ^ 4973784812099L;
                    SpeedCalculator.d = new HashMap<K, V>(13);
                    SpeedCalculator.zkmSetFlag(null);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[5];
                    var10_4 = 0;
                    var9_5 = "G\u00d0FE\u00d2y\u00ac\u00f3\u00d0\u00b3\u00da\u0019\u0081\u00d0\n\" \u00bd\u00bew\u001e\u0099C\r\u00ef,\u00ef\u00dd\u0015\u00a1N~B\u00e3\u00e3\u00fe\u0080M\u0016\u00dej\u000b\u00e52d\u0099\u008e\u001b\u00ca R5\u0083#\u0014\u00abWz\u00ee\u00fb\u00e5\u00bb\u00b7\u0004+\u00b5~\u00c4\u00cc\u0099\u00a8\u00af_\u00b9G\u00df\u00aa\u007fv\u0012\u0082\u00ed";
                    var11_6 = 82;
                    var8_7 = 16;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = SpeedCalculator.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00a6\u0091$\u0090\u00fdH\u00a7\u0000}\u00ed\u00d0\u00fdk[\u00f6\u00dc&\u0010\u00e5\u00ba\u0085\u00dc\u00e9yx\u00b6\u0011P*\u0006\u00caa|\bH\u00f9\u00c0\u00c4\u00b34 _\u00dd\u00b0\u0005\n\u00f5(\u008d\u0094\"h\u00ede\u00bdbX\u00e3Y\u00cb\u0095\u00bb\u0086\u00a3d\u00d7\u0013\u00aczw\u00b9\u0011\u0013";
                        var11_6 = 73;
                        var8_7 = 40;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = SpeedCalculator.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            SpeedCalculator.b = var12_3;
            SpeedCalculator.c = new String[5];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-80, -123, -91, 78, 22, -29, 21, 82});
        ** while (true)
        SpeedCalculator.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        SpeedCalculator.INSTANCE = new SpeedCalculator();
        SpeedCalculator.t = new LinkedHashMap<K, V>();
    }

    public static void zkmSetFlag(String string) {
        A = string;
    }

    public static String zkmGetFlag() {
        return A;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x175E;
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
                throw new RuntimeException("su/catlean/sz", exception);
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
            SpeedCalculator.c[n2] = SpeedCalculator.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SpeedCalculator.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/sz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SpeedCalculator.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

