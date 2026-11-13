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
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class RandomUtil {
    @NotNull
    private static final Random random;
    private static String V;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @NotNull
    public static final Random getRandom() {
        return random;
    }

    public static final int randomInt(int n, int n2, boolean bl) {
        return MathKt.roundToInt((float)n + (float)(n2 - n) * (float)RandomUtil.nextDouble(bl));
    }

    public static /* synthetic */ int P(int n, int n2, boolean bl, Object object) {
        bl = true;
        return RandomUtil.randomInt(n, n2, bl);
    }

    public static final float randomFloat(float f, float f2, boolean bl) {
        return f + (f2 - f) * (float)RandomUtil.nextDouble(bl);
    }

    public static /* synthetic */ float D(float f, float f2, boolean bl, Object object) {
        bl = true;
        return RandomUtil.randomFloat(f, f2, bl);
    }

    public static final double randomDouble(double d, double d2, boolean bl) {
        return d + (d2 - d) * RandomUtil.nextDouble(bl);
    }

    public static /* synthetic */ double a(double d, double d2, boolean bl, Object object) {
        bl = true;
        return RandomUtil.randomDouble(d, d2, bl);
    }

    public static final double randomDouble(@NotNull ClosedRange closedRange, boolean bl) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(closedRange, (String)((Object)RandomUtil.zkmBootstrap("p", (int)17089, (long)(0x56D2C75BD5C0443BL ^ l))));
        return RandomUtil.randomDouble(((Number)closedRange.getStart()).doubleValue(), ((Number)closedRange.getEndInclusive()).doubleValue(), bl);
    }

    public static /* synthetic */ double k(ClosedRange closedRange, boolean bl, Object object) {
        bl = true;
        return RandomUtil.randomDouble(closedRange, bl);
    }

    public static final float randomFloat(@NotNull ClosedRange closedRange, boolean bl) {
        Intrinsics.checkNotNullParameter(closedRange, "range");
        return RandomUtil.randomFloat(((Number)closedRange.getStart()).floatValue(), ((Number)closedRange.getEndInclusive()).floatValue(), bl);
    }

    public static /* synthetic */ float p(ClosedRange closedRange, boolean bl, Object object) {
        bl = true;
        return RandomUtil.randomFloat(closedRange, bl);
    }

    public static final int randomInt(@NotNull ClosedRange closedRange, boolean bl) {
        int n;
        block0: {
            int n2 = 0;
            int n3 = 0;
            long l = ((long)n2 << 32 | (long)n3 << 32 >>> 32) ^ a;
            ZkmReferences.zkmBootstrap("\u00c5", (long)6829613467392648739L, (long)l);
            Intrinsics.checkNotNullParameter(closedRange, (String)((Object)RandomUtil.zkmBootstrap("p", (int)1417, (long)(0x6A2BC9AA22ADC4D2L ^ l))));
            n = RandomUtil.randomInt(((Number)closedRange.getStart()).intValue(), ((Number)closedRange.getEndInclusive()).intValue(), bl);
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)6880019209929441771L, (long)l) == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", "sx0s2", (long)6880583297803749017L, (long)l);
        }
        return n;
    }

    public static /* synthetic */ int f(ClosedRange closedRange, boolean bl, Object object) {
        bl = true;
        return RandomUtil.randomInt(closedRange, bl);
    }

    public static final double nextDouble(boolean bl) {
        return random.nextDouble();
    }

    public static final double nextGaussian() {
        return random.nextGaussian();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(5536565806165132523L, -9036113055716603830L, MethodHandles.lookup().lookupClass()).a(141477773666705L);
        long l = a ^ 0x313D2949857AL;
        d = new HashMap(13);
        RandomUtil.zkmSetFlag(null);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\"\u00c0\u00e2\u00b0\u0083\u00e5'q\u00e5\u0005?\u00fa\u00f8\u00e9\u00cc\u00a5\u0010\u00e2bc\u00bd\u00d1\u001a\u00bfv\u00ca\u00d0\\\u008bk\u00a0\u00a2\t";
        int n2 = 33;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = RandomUtil.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[2];
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n6 = 1;
        while (true) {
            if (n6 >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{55, -51, -112, -31, -19, -95, -82, 107});
                long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                random = new Random(l2);
                return;
            }
            byArray5 = byArray5;
            byArray5[n6] = (byte)(l << n6 * 8 >>> 56);
            ++n6;
        }
    }

    public static void zkmSetFlag(String string) {
        V = string;
    }

    public static String zkmGetFlag() {
        return V;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1F71;
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
                throw new RuntimeException("su/catlean/mf", exception);
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
            RandomUtil.c[n2] = RandomUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RandomUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/mf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RandomUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

