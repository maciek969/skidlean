/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

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
import kotlin.ranges.RangesKt;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.util.RandomUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class RotationSmoother {
    @NotNull
    public static final RotationSmoother INSTANCE;
    private static final float N = 1.732051f;
    private static final float W = 2.236068f;
    private static float e;
    private static float d;
    private static float h;
    private static float U;
    private static Module[] s;
    private static final long a;
    private static final String[] b;
    private static final String[] f;
    private static final Map g;
    private static final long i;

    private RotationSmoother() {
    }

    /*
     * Unable to fully structure code
     */
    @NotNull
    public final Rotation smoothRotation(@NotNull Rotation var1_1, @NotNull Rotation var2_2, float var3_3, float var4_4, long var5_5, float var7_6, float var8_7) {
        block13: {
            block11: {
                block12: {
                    block9: {
                        block10: {
                            block7: {
                                block8: {
                                    var5_5 = RotationSmoother.a ^ var5_5;
                                    Intrinsics.checkNotNullParameter(var1_1, (String)RotationSmoother.zkmBootstrap("r", (int)3037, (long)(8534744889224028610L ^ var5_5)));
                                    v0 = ZkmReferences.zkmBootstrap("\u00c5", (long)6192207300070682785L, (long)var5_5);
                                    Intrinsics.checkNotNullParameter(var2_2, (String)RotationSmoother.zkmBootstrap("r", (int)10374, (long)(2853026782776738456L ^ var5_5)));
                                    var13_8 = v0;
                                    var14_9 = var7_6;
                                    var15_10 = MathHelper.wrapDegrees(var2_2.getYaw() - var1_1.getYaw());
                                    var16_11 = var2_2.getPitch() - var1_1.getPitch();
                                    var17_12 = (float)Math.hypot(var15_10, var16_11);
                                    cfr_temp_0 = var17_12 - 1.5f;
                                    v1 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                    if (var13_8 != null) {
                                        if (v1 < 0) {
                                            return var1_1;
                                        }
                                        RotationSmoother.h /= 1.732051f;
                                        RotationSmoother.U /= 1.732051f;
                                        v1 = var17_12 == var8_7 ? 0 : (var17_12 > var8_7 ? 1 : -1);
                                    }
                                    if (var5_5 < 0L || var13_8 == null) break block7;
                                    if (v1 < 0) break block8;
                                    var18_13 = Math.min(var4_4, var17_12) / 2.236068f;
                                    RotationSmoother.h += RandomUtil.p(RangesKt.rangeTo(-1.0f, 1.0f), false, null) * var18_13;
                                    RotationSmoother.U += RandomUtil.p(RangesKt.rangeTo(-1.0f, 1.0f), false, null) * var18_13;
                                    if (var5_5 <= 0L) break block9;
                                    if (var13_8 != null) break block10;
                                    ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[2], (long)6166480289872175022L, (long)var5_5);
                                }
                                v2 = var14_9;
                                v3 = 3.0f;
                                if (var13_8 == null) ** GOTO lbl38
                                cfr_temp_1 = v2 - v3;
                                v1 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                            }
                            if (v1 < 0) {
                                v4 = RandomUtil.p(RangesKt.rangeTo(3.0f, 6.0f), false, null);
                            } else {
                                v2 = var14_9;
                                v3 = 2.236068f;
lbl38:
                                // 2 sources

                                v4 = v2 / v3;
                            }
                            var14_9 = v4;
                        }
                        var18_13 = (float)Math.hypot(RotationSmoother.e += RotationSmoother.h + var3_3 * var15_10 / var17_12, RotationSmoother.d += RotationSmoother.U + var3_3 * var16_11 / var17_12);
                    }
                    v5 = var18_13;
                    if (var5_5 < 0L) break block11;
                    v6 = var14_9;
                    if (var13_8 == null) break block12;
                    if (!(v5 > v6)) break block13;
                    v7 = var14_9 / 2.0f;
                    v6 = RandomUtil.p(RangesKt.rangeTo(0.0f, 1.0f), false, null) * var14_9 / 2.0f;
                }
                v5 = v7 + v6;
            }
            var19_14 = v5;
            RotationSmoother.e = RotationSmoother.e / var18_13 * var19_14;
            RotationSmoother.d = RotationSmoother.d / var18_13 * var19_14;
        }
        return new Rotation(MathHelper.wrapDegrees(var1_1.getYaw() + RotationSmoother.e), RangesKt.coerceIn(var1_1.getPitch() + RotationSmoother.d, -90.0f, 90.0f), false, null, (int)RotationSmoother.i, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-1196286083931712581L, 8979409605923220942L, MethodHandles.lookup().lookupClass()).a(21342500525128L);
        long l = a ^ 0x360C0607CDCFL;
        g = new HashMap(13);
        RotationSmoother.zkmSetFlag(new Module[4]);
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
        String string = "\u00b6\u00cc\u008d,\u00e0\u00faR!u6Y?\u000f\u0084\u0017\u00d2\u0010Sx\u00af\u00a11I\u00c8yo\u008c\u0080\u00d9.\u0095\u00fd\u00bb";
        int n2 = 33;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = RotationSmoother.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        f = new String[2];
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n6 = 1;
        while (true) {
            if (n6 >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{-32, 1, -79, -71, 56, 109, -5, 15});
                i = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                INSTANCE = new RotationSmoother();
                return;
            }
            byArray5 = byArray5;
            byArray5[n6] = (byte)(l << n6 * 8 >>> 56);
            ++n6;
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        s = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return s;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3EAB;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/wq", exception);
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
            RotationSmoother.f[n2] = RotationSmoother.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RotationSmoother.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RotationSmoother.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

