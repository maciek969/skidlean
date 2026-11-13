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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class DirectionVector {
    @NotNull
    private final Direction dir;
    @NotNull
    private final Vec3d vector;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long f;

    public DirectionVector(@NotNull Direction direction, @NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(direction, "dir");
        Intrinsics.checkNotNullParameter(vec3d, "vector");
        this.dir = direction;
        this.vector = vec3d;
    }

    @NotNull
    public final Direction getDir() {
        return this.dir;
    }

    @NotNull
    public final Vec3d getVector() {
        return this.vector;
    }

    @NotNull
    public final Direction getDir2() {
        return this.dir;
    }

    @NotNull
    public final Vec3d getVector2() {
        return this.vector;
    }

    @NotNull
    public final DirectionVector copy(@NotNull Direction direction, @NotNull Vec3d vec3d) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(direction, (String)((Object)DirectionVector.zkmBootstrap("k", (int)20405, (long)(0x267A12CB89F31A96L ^ l))));
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)DirectionVector.zkmBootstrap("k", (int)6017, (long)(0x603C6CEC9A71C2A4L ^ l))));
        return new DirectionVector(direction, vec3d);
    }

    public static DirectionVector copy$default(DirectionVector directionVector, Direction direction, Vec3d vec3d, int n, Object object) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 48 | (long)n3 << 48 >>> 16 | (long)n4 << 32 >>> 32) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1427342720328458806L, (long)l);
        int n5 = n & 1;
        if (callSite == null) {
            if (n5 != 0) {
                direction = directionVector.dir;
            }
            n5 = n & 2;
        }
        if (n5 != 0) {
            vec3d = directionVector.vector;
        }
        return directionVector.copy(direction, vec3d);
    }

    @NotNull
    public String toString() {
        return "BreakData(dir=" + this.dir + ", vector=" + this.vector + ")";
    }

    public int hashCode() {
        int n = this.dir.hashCode();
        n = n * (int)f + this.vector.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DirectionVector)) {
            return false;
        }
        DirectionVector directionVector = (DirectionVector)object;
        if (this.dir != directionVector.dir) {
            return false;
        }
        return Intrinsics.areEqual(this.vector, directionVector.vector);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    DirectionVector.a = ZkmKeys.a(-6269097525667313317L, 6505703261685906779L, MethodHandles.lookup().lookupClass()).a(17470763773690L);
                    DirectionVector.e = new HashMap<K, V>(13);
                    var5 = DirectionVector.a ^ 102286616340585L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[6];
                    var12_4 = 0;
                    var11_5 = "\u0099ov\u0011\u00e7\u00aaeD\u00df\u00a0\u00af\u00a9k\u00a5s\u007f\u0018p^:\u00b2\u00ce]\u00fb\f\u00a6\u00c2\u00f9\u00b8\u00f1\u00a6\\\u00fa\u00e5\u00af\u008e\u00ca\u00cf\u00a4\r\u00bb\u0010K.x\u0001\u00cf?/e\u0013-\b-\u00de\u00b2\u009e@\u0010\u00d0\u0000\u0013n@\u0013>\u009flR\u00cd\u00e3,s\u00bf\u00e7";
                    var13_6 = 75;
                    var10_7 = 16;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = DirectionVector.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u0010U\u0001T\u0087\u0012T\u0084.\u007fS%\u00dd!O\u0084\u00f3jD\u0090a\u00be\u00ba)\u00e2q\u00fe*\u0086\u00a6QF\u0010\u00b2\u00d7\u0093y\u00f2\u008f\u00ae\u0013\u00dd\u0085\u00e3\u0017\u00fe&5c";
                        var13_6 = 49;
                        var10_7 = 32;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = DirectionVector.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            DirectionVector.c = var14_3;
            DirectionVector.d = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-42, -103, -50, -37, -33, -56, -62, 60});
        ** while (true)
        DirectionVector.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3052;
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
                throw new RuntimeException("su/catlean/dl", exception);
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
            DirectionVector.d[n2] = DirectionVector.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DirectionVector.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DirectionVector.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

