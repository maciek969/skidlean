/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import su.catlean.module.attack.AutoDripstope;
import su.catlean.module.world.Nuker;
import su.catlean.module.world.NukerSortMode;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class Nuker$onPlayerUpdate$1
implements Comparator {
    private static int[] R;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public final int compare(Object object, Object object2) {
        float f;
        float f2;
        BlockPos blockPos = (BlockPos)object;
        Object var12_4 = null;
        if (Nuker.access$getSort(Nuker.INSTANCE) == NukerSortMode.FOV) {
            f2 = AutoDripstope.INSTANCE.getYawTo(blockPos);
        } else {
            Vec3d vec3d = blockPos.toCenterPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            f2 = MovementUtil.INSTANCE.squaredDistanceTo(vec3d);
        }
        blockPos = (BlockPos)object2;
        Comparable comparable = Float.valueOf(f2);
        if (Nuker.access$getSort(Nuker.INSTANCE) == NukerSortMode.FOV) {
            f = AutoDripstope.INSTANCE.getYawTo(blockPos);
        } else {
            Vec3d vec3d = blockPos.toCenterPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            f = MovementUtil.INSTANCE.squaredDistanceTo(vec3d);
        }
        return ComparisonsKt.compareValues(comparable, (Comparable)Float.valueOf(f));
    }

    public static void zkmSetFlag(int[] nArray) {
        R = nArray;
    }

    public static int[] zkmGetFlag() {
        return R;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(8634637909241044180L, 8179733634144685024L, MethodHandles.lookup().lookupClass()).a(246365118970863L);
        long l = a ^ 0x10F39AFA6132L;
        d = new HashMap(13);
        Nuker$onPlayerUpdate$1.zkmSetFlag(null);
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
        String string = "\u0012\u00bf6\u00a1\u00d6S'\u00f1\u0002Xnc\u00dc\u00c4^p+S\u00d1\u0098(\u00a6N\u0080\u00b70S3k)\u00d4*\u0018A\u00d4\u00e0\r\u00b1\u00ec\u00f9xP\n\u00db5\u00a9]]\u00ed\n\u0080\n2Re:\u001e";
        int n2 = 57;
        int n3 = 32;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = Nuker$onPlayerUpdate$1.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[2];
                return;
            }
            n3 = string.charAt(n4);
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x508;
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
                throw new RuntimeException("su/catlean/de", exception);
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
            Nuker$onPlayerUpdate$1.c[n2] = Nuker$onPlayerUpdate$1.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Nuker$onPlayerUpdate$1.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/de" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Nuker$onPlayerUpdate$1.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

