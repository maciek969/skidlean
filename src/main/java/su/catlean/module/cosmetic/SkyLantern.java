/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import su.catlean.module.Module;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

final class SkyLantern {
    private float x;
    private float y;
    private float z;
    private float prevX;
    private float prevY;
    private float prevZ;
    private float motionX;
    private float motionY;
    private float motionZ;
    private int age;
    private float rotation;
    private final float rotationSpeed;
    private final int offset;
    private final int maxAge;
    private static final long c;
    private static final long[] d;
    private static final Integer[] g;
    private static final Map h;

    public SkyLantern(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, float f10, float f11, int n2, int n3) {
        Module[] moduleArray = new Module[]{};
        Module[] moduleArray2 = moduleArray;
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.prevX = f4;
        this.prevY = f5;
        this.prevZ = f6;
        this.motionX = f7;
        this.motionY = f8;
        this.motionZ = f9;
        this.age = n;
        this.rotation = f10;
        this.rotationSpeed = f11;
        this.offset = n2;
        this.maxAge = n3;
    }

    public SkyLantern(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, DefaultConstructorMarker defaultConstructorMarker) {
        Module[] moduleArray = new Module[]{};
        f4 = f;
        f5 = f2;
        f6 = f3;
        f7 = RandomUtil.p(RangesKt.rangeTo(-0.1f, 0.1f), false, null);
        f8 = RandomUtil.p(RangesKt.rangeTo(0.01f, 0.03f), false, null);
        f9 = RandomUtil.p(RangesKt.rangeTo(-0.1f, 0.1f), false, null);
        int n = 0;
        f10 = RandomUtil.p(RangesKt.rangeTo(-15.0f, 15.0f), false, null);
        f11 = RandomUtil.p(RangesKt.rangeTo(-0.5f, 0.5f), false, null);
        int n2 = RandomUtil.f(new IntRange(0, 360), false, null);
        int n3 = RandomUtil.f(new IntRange(100, 200), false, null);
        this(f, f2, f3, f4, f5, f6, f7, f8, f9, n, f10, f11, n2, n3);
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final float getZ() {
        return this.z;
    }

    public final void setZ(float f) {
        this.z = f;
    }

    public final float getPrevX() {
        return this.prevX;
    }

    public final void setPrevX(float f) {
        this.prevX = f;
    }

    public final float getPrevY() {
        return this.prevY;
    }

    public final void setPrevY(float f) {
        this.prevY = f;
    }

    public final float getPrevZ() {
        return this.prevZ;
    }

    public final void setPrevZ(float f) {
        this.prevZ = f;
    }

    public final float getMotionX() {
        return this.motionX;
    }

    public final void setMotionX(float f) {
        this.motionX = f;
    }

    public final float getMotionY() {
        return this.motionY;
    }

    public final void setMotionY(float f) {
        this.motionY = f;
    }

    public final float getMotionZ() {
        return this.motionZ;
    }

    public final void setMotionZ(float f) {
        this.motionZ = f;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int n) {
        this.age = n;
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final void setRotation(float f) {
        this.rotation = f;
    }

    public final float getRotationSpeed() {
        return this.rotationSpeed;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getMaxAge() {
        return this.maxAge;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block9: {
            block8: {
                SkyLantern.c = ZkmKeys.a(3219430370203488093L, -687638557866968794L, MethodHandles.lookup().lookupClass()).a(209337593382170L);
                SkyLantern.h = new HashMap<K, V>(13);
                var0 = SkyLantern.c ^ 53315603422676L;
                var2_1 = Cipher.getInstance("DES/CBC/NoPadding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var8_3 = new long[14];
                var5_4 = 0;
                var6_5 = "uf|\u0083(\u0085\u00b4\u00cc\u00d8\u00a0\u00e6\u00e7q\u00c10\nPOu\u00dd\u0088\u0016 Bt\u009d\u0092\u00d9\u001e\u008ca\u0004C\u00f6\tbsa99\u00aa\r\u0012\u0004\u00a2\u00e0\u0012\u00f1Yj\"1\u008bZ\u00bcSa\u00da\u00ab8B\u00ce\u00d1V\u00f2\u000e\u00e9)9\u00aa\u00e8@\u00e8\u009ekl\u00fb\u00e4>\u00a5\u0091O>\u00ba\u00dc\u00bd@\u0092M\u00adx\u00fa\u00fd\u0098\u00c5\u00a2";
                var7_6 = 96;
                var4_7 = 0;
                while (true) {
                    var9_8 = var6_5.substring(var4_7, var4_7 += 8).getBytes("ISO-8859-1");
                    v3 = var8_3;
                    v4 = var5_4++;
                    v5 = ((long)var9_8[0] & 255L) << 56 | ((long)var9_8[1] & 255L) << 48 | ((long)var9_8[2] & 255L) << 40 | ((long)var9_8[3] & 255L) << 32 | ((long)var9_8[4] & 255L) << 24 | ((long)var9_8[5] & 255L) << 16 | ((long)var9_8[6] & 255L) << 8 | (long)var9_8[7] & 255L;
                    v6 = -1;
                    break block8;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var4_7 < var7_6) ** continue;
                    var6_5 = "\u009ai\u00d4\u00b7(a\u00ab\u0086\u00fe&\u00cc\u00fd,\u00da\u0015N";
                    var7_6 = 16;
                    var4_7 = 0;
                    while (true) {
                        var9_8 = var6_5.substring(var4_7, var4_7 += 8).getBytes("ISO-8859-1");
                        v3 = var8_3;
                        v4 = var5_4++;
                        v5 = ((long)var9_8[0] & 255L) << 56 | ((long)var9_8[1] & 255L) << 48 | ((long)var9_8[2] & 255L) << 40 | ((long)var9_8[3] & 255L) << 32 | ((long)var9_8[4] & 255L) << 24 | ((long)var9_8[5] & 255L) << 16 | ((long)var9_8[6] & 255L) << 8 | (long)var9_8[7] & 255L;
                        v6 = 0;
                        break block8;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var4_7 < var7_6) ** continue;
                    break block9;
                    break;
                }
            }
            var10_9 = v5;
            var12_10 = var2_1.doFinal(new byte[]{(byte)(var10_9 >>> 56), (byte)(var10_9 >>> 48), (byte)(var10_9 >>> 40), (byte)(var10_9 >>> 32), (byte)(var10_9 >>> 24), (byte)(var10_9 >>> 16), (byte)(var10_9 >>> 8), (byte)var10_9});
            v7 = ((long)var12_10[0] & 255L) << 56 | ((long)var12_10[1] & 255L) << 48 | ((long)var12_10[2] & 255L) << 40 | ((long)var12_10[3] & 255L) << 32 | ((long)var12_10[4] & 255L) << 24 | ((long)var12_10[5] & 255L) << 16 | ((long)var12_10[6] & 255L) << 8 | (long)var12_10[7] & 255L;
            switch (v6) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl52:
                // 1 sources

                ** continue;
            }
        }
        SkyLantern.d = var8_3;
        SkyLantern.g = new Integer[14];
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4B4D;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = d[n2];
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
                throw new RuntimeException("su/catlean/fw", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SkyLantern.g[n2] = n3;
        }
        return g[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SkyLantern.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/fw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SkyLantern.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

