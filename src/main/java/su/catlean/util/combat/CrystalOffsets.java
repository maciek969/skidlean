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
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CrystalOffsets {
    @NotNull
    public static final CrystalOffsets INSTANCE;
    @NotNull
    private static final Float[] c;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;

    private CrystalOffsets() {
    }

    @NotNull
    public final Float[] f() {
        return c;
    }

    public final boolean findBestOffset(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2) {
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Intrinsics.checkNotNullParameter(vec3d2, "interactVec");
        Box box = new Box(vec3d.subtract(1.0, 0.0, 1.0), vec3d.add(1.0, 2.0, 1.0));
        double d = Globals.getPlayer().getEntityInteractionRange();
        double d2 = Double.MAX_VALUE;
        Vec3d vec3d3 = vec3d2.subtract(Globals.getPlayer().getEyePos()).normalize().multiply(d);
        Intrinsics.checkNotNullExpressionValue(vec3d3, "scale(...)");
        Vec3d vec3d4 = vec3d3;
        Vec3d vec3d5 = MovementUtil.INSTANCE.getServerPos();
        Float[] floatArray = c;
        int n = floatArray.length;
        for (int i = 0; i < n; ++i) {
            Vec3d vec3d6;
            Vec3d vec3d7;
            float f = floatArray[i].floatValue();
            Intrinsics.checkNotNullExpressionValue(vec3d5.add(0.0, f, 0.0), "add(...)");
            Intrinsics.checkNotNullExpressionValue(vec3d7.add(vec3d4), "add(...)");
            if (box.contains(vec3d6) || box.contains(vec3d7)) {
                return true;
            }
            Optional<Vec3d> optional = box.raycast(vec3d7, vec3d6);
            if (!optional.isPresent()) continue;
            d2 = Math.min(d2, vec3d7.squaredDistanceTo(optional.get()));
        }
        return d2 <= d * d;
    }

    public static boolean findBestOffset$default(CrystalOffsets crystalOffsets, Vec3d vec3d, Vec3d vec3d2, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3729459883722589415L, (long)l);
        boolean bl = n & 2;
        if (callSite == null) {
            if (bl) {
                vec3d2 = vec3d;
            }
            bl = crystalOffsets.findBestOffset(vec3d, vec3d2);
        }
        return bl;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                CrystalOffsets.a = ZkmKeys.a(5534783901550420242L, -5998892869796493544L, MethodHandles.lookup().lookupClass()).a(91365475665220L);
                CrystalOffsets.e = new HashMap<K, V>(13);
                var0 = CrystalOffsets.a ^ 52994207583463L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[5];
                var7_4 = 0;
                var6_5 = "_1Qv!y\u00c7\u00aa\b*\u0087\u00d9+d\u0004\u00a5 Z\u00e6[o\u000b\u00d3\u00ce\u00ec\u0087G\u0081\u00df\u00f7\u00b9\u001ce\u00f3\u0094\u00f2acm\u00d8\u00b5\u0081\u00ec<\u00fb\u00eb=#\u0002\u0018#\u00f0\u00d5|d\u00a5\u00ef<J\u00a0X\u00c4?p\u00ca\u00c8y\u00ce\u00ca\u00a4D]\u00e4\u00f5";
                var8_6 = 74;
                var5_7 = 16;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = CrystalOffsets.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "U5YZ\u000bH\u00f6<\u00d2\u00ad\u0083\u00a1u\u00c7\u00e2@S\u001e\u00fa/\u00c7\u00f9\u0011\u00b70\u009f\u00b2\u0017]\u008b\u0014\u00db  \u00d0\u00ca\u00a0\u0010<g\u0098\u0084\u0003\u0003\u00e1S(\u0014\u00c5\u00eb/\u00828\\0\u001d\u00b9\u00fb^\u00c5\u0002\u00ec\u00b6cu";
                    var8_6 = 65;
                    var5_7 = 32;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = CrystalOffsets.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        CrystalOffsets.b = var9_3;
        CrystalOffsets.d = new String[5];
        CrystalOffsets.INSTANCE = new CrystalOffsets();
        var11_10 = new Float[]{Float.valueOf(1.62f), Float.valueOf(0.4f)};
        CrystalOffsets.c = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x73A;
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
                throw new RuntimeException("su/catlean/i1", exception);
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
            CrystalOffsets.d[n2] = CrystalOffsets.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalOffsets.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/i1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalOffsets.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

