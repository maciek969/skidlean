/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PlaceCandidate {
    @NotNull
    private final BlockPos pos;
    @NotNull
    private final Direction side;
    private final float damage;
    private final float selfDamage;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public PlaceCandidate(@NotNull BlockPos blockPos, @NotNull Direction direction, float f, float f2) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ b;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)PlaceCandidate.zkmBootstrap("a", (int)3923, (long)(0x19097B4574BEA7EL ^ l))));
        Intrinsics.checkNotNullParameter(direction, (String)((Object)PlaceCandidate.zkmBootstrap("a", (int)23380, (long)(0x3B0065CACA91BE7EL ^ l))));
        this.pos = blockPos;
        this.side = direction;
        this.damage = f;
        this.selfDamage = f2;
    }

    @NotNull
    public final BlockPos getPos() {
        return this.pos;
    }

    @NotNull
    public final Direction getSide() {
        return this.side;
    }

    public final float getDamage() {
        return this.damage;
    }

    public final float getSelfDamage() {
        return this.selfDamage;
    }

    @NotNull
    public final BlockPos getPos2() {
        return this.pos;
    }

    @NotNull
    public final Direction getSide2() {
        return this.side;
    }

    public final float getDamage2() {
        return this.damage;
    }

    public final float getSelfDamage2() {
        return this.selfDamage;
    }

    @NotNull
    public final PlaceCandidate copy(@NotNull BlockPos blockPos, @NotNull Direction direction, float f, float f2) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)PlaceCandidate.zkmBootstrap("a", (int)2711, (long)(0x594FE931FEA2BC5AL ^ l))));
        Intrinsics.checkNotNullParameter(direction, (String)((Object)PlaceCandidate.zkmBootstrap("a", (int)25216, (long)(0x41EFAA8F530B5449L ^ l))));
        return new PlaceCandidate(blockPos, direction, f, f2);
    }

    public static /* synthetic */ PlaceCandidate copy$default(PlaceCandidate placeCandidate, BlockPos blockPos, Direction direction, float f, int n, float f2, int n2, Object object) {
        int n3 = 0;
        long l = ((long)n3 << 32 | (long)n << 32 >>> 32) ^ b;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-1849342099627830925L, (long)l);
        int n4 = n2 & 1;
        if (callSite == null) {
            if (n4 != 0) {
                blockPos = placeCandidate.pos;
            }
            n4 = n2 & 2;
        }
        CallSite callSite2 = callSite;
        if (n > 0) {
            if (callSite2 == null) {
                if (n4 != 0) {
                    direction = placeCandidate.side;
                }
                n4 = n2 & 4;
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n4 != 0) {
                f = placeCandidate.damage;
            }
            n4 = n2 & PlaceCandidate.zkmBootstrap2("y", (int)25999, (long)(0x2003DD2B108EED70L ^ l));
        }
        if (n4 != 0) {
            f2 = placeCandidate.selfDamage;
        }
        return placeCandidate.copy(blockPos, direction, f, f2);
    }

    @NotNull
    public String toString() {
        float f = this.selfDamage;
        float f2 = this.damage;
        Direction direction = this.side;
        BlockPos blockPos = this.pos;
        return "RenderPosition(pos=" + blockPos + ", side=" + direction + ", damage=" + f2 + ", selfDamage=" + f + ")";
    }

    public int hashCode() {
        int n = this.pos.hashCode();
        n = n * 31 + this.side.hashCode();
        n = n * 31 + Float.hashCode(this.damage);
        n = n * 31 + Float.hashCode(this.selfDamage);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceCandidate)) {
            return false;
        }
        PlaceCandidate placeCandidate = (PlaceCandidate)object;
        if (!Intrinsics.areEqual(this.pos, placeCandidate.pos)) {
            return false;
        }
        if (this.side != placeCandidate.side) {
            return false;
        }
        if (Float.compare(this.damage, placeCandidate.damage) != 0) {
            return false;
        }
        return Float.compare(this.selfDamage, placeCandidate.selfDamage) == 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        PlaceCandidate.b = ZkmKeys.a(-8191077676480898858L, -778446914907880495L, MethodHandles.lookup().lookupClass()).a(278338010141935L);
                        PlaceCandidate.e = new HashMap<K, V>(13);
                        var11 = PlaceCandidate.b ^ 44545044466616L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[8];
                        var18_4 = 0;
                        var17_5 = "|\u00f5f.\u00b3[\u00af\u00ce\"W\u007fK\u00d0\u00ec\r\u00c7(2QH\u0016\u00e5\u00f2?\u00b8\u008a@=\u00a4`\u00a6\u00adf4&\u0097\u00e2\u00cb\u0002\u00e6lL\u00d2?O\u00871\u001bo\u00c2\u001dt\u0080g/\u009c\u008a\u0010\u00d9\\\u000e\u0016\u00ef\u0087\u00ae\u00a1\u009f\u00a6\u00dd\u00db\u00de\u00a0\u00ac}\u0018\u00fb\u00a7\u00e7R\u00d27\u0083\u00cc\u00e9\u00e5r\u00a0\u00e7\u00ad\u008b\u00fd\u00acFA\u00ef?\u00a3\u00f9C\u0010\u00d0\u00cbN\u00d2\u00f2\u0019\u00f5D\u0004\u00bb\u00f8sa\u00c2\u0014U\u0010J\u00cb\u00d8\u00ab-\u00d1\u00b1V\u000e\u00a0H\u0003\u00cd\u00bd>\u00fb";
                        var19_6 = 133;
                        var16_7 = 16;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = PlaceCandidate.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0087$%\u00fc_\u0086\u00a4\u0082>=\u00d7\u0080\u00d2\u0004,\u00f9\u0018\u00c9\u00cad/\u00ba\u00a5o\u00ab\u0095&\u000b\u00a0w\u00d9\u00ed\u0096@K\u00a3m\u0080\u00f5\u00c9\u0097";
                            var19_6 = 41;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = PlaceCandidate.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                PlaceCandidate.c = var20_3;
                PlaceCandidate.d = new String[8];
                PlaceCandidate.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u00a3\u001b\u0016]@\u0019\u00169\u00d8%\u00d6\u001e\u00d5:\u0014\u00ec\u00d47\u00a8\u00ec\u00b8/^d";
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
        PlaceCandidate.f = var6_12;
        PlaceCandidate.g = new Integer[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xE54;
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
                throw new RuntimeException("su/catlean/fa", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            PlaceCandidate.d[n2] = PlaceCandidate.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlaceCandidate.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fa" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6F36;
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
                throw new RuntimeException("su/catlean/fa", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PlaceCandidate.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PlaceCandidate.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fa" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlaceCandidate.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PlaceCandidate.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

