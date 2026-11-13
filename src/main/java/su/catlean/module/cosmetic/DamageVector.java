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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

final class DamageVector {
    @NotNull
    private final Vec3d vec;
    @NotNull
    private final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;
    private static final long f;

    public DamageVector(@NotNull Vec3d vec3d, @NotNull Animation animation) {
        Intrinsics.checkNotNullParameter(vec3d, "vec");
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.vec = vec3d;
        this.animation = animation;
    }

    public /* synthetic */ DamageVector(Vec3d vec3d, Animation animation, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_QUINT, f);
        this(vec3d, animation);
    }

    @NotNull
    public final Vec3d getVec() {
        return this.vec;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    @NotNull
    public final Vec3d getVec2() {
        return this.vec;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    @NotNull
    public final DamageVector copy(@NotNull Vec3d vec3d, @NotNull Animation animation) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)DamageVector.zkmBootstrap("m", (int)22250, (long)(0x5A5360629D984F13L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)DamageVector.zkmBootstrap("m", (int)21672, (long)(0x350C90BEF1244D50L ^ l))));
        return new DamageVector(vec3d, animation);
    }

    public static /* synthetic */ DamageVector copy$default(DamageVector damageVector, Vec3d vec3d, Animation animation, int n, Object object) {
        long l = 0L;
        int n2 = 0;
        long l2 = (l << 8 | (long)n2 << 56 >>> 56) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2170767674992321514L, (long)l2);
        int n3 = n & 1;
        if (callSite == null) {
            if (n3 != 0) {
                vec3d = damageVector.vec;
            }
            n3 = n & 2;
        }
        if (n3 != 0) {
            animation = damageVector.animation;
        }
        return damageVector.copy(vec3d, animation);
    }

    @NotNull
    public String toString() {
        return "Hit(vec=" + this.vec + ", animation=" + this.animation + ")";
    }

    public int hashCode() {
        int n = this.vec.hashCode();
        n = n * (int)e + this.animation.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DamageVector)) {
            return false;
        }
        DamageVector damageVector = (DamageVector)object;
        if (!Intrinsics.areEqual(this.vec, damageVector.vec)) {
            return false;
        }
        return Intrinsics.areEqual(this.animation, damageVector.animation);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        DamageVector.a = ZkmKeys.a(3798595844513167261L, -4030667050693035886L, MethodHandles.lookup().lookupClass()).a(62215024649184L);
                        DamageVector.d = new HashMap<K, V>(13);
                        var10 = DamageVector.a ^ 113592086517260L;
                        var12_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var10 >>> 56);
                        for (var13_2 = 1; var13_2 < 8; ++var13_2) {
                            v2 = v2;
                            v2[var13_2] = (byte)(var10 << var13_2 * 8 >>> 56);
                        }
                        var12_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var19_3 = new String[6];
                        var17_4 = 0;
                        var16_5 = ":\u00b2\u00ec\u00a1e*\u00aeX\u0099b\u0085R\u00a1<\u0013\u00d7 \u00008\u00e8\u0094;\u00d8\u00a6a\u00cd\u00ff9\u00ab\u00db4W\u0080\u0096u\u00116Z\u00eaF\u00e89\u00bb\u00f8\u00f6Jn\u00af\u009d zg\u001d\u00cf.q\u008f<\u001fH\u0005\u00e1\u00a3\n\u00ce\u00a2\u0092\u00db\u0006\u00edR\u00db\u001e\u009c\u00c8\u0007\u00d7\u00b4@\u00ab\u00c9\\\u0010\u00f8\u001e\u00acN~W\u00e0\u00df\u00aa\u00a4>*\u00fe\u0011?y";
                        var18_6 = 99;
                        var15_7 = 16;
                        var14_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var14_8;
                            v4 = var16_5.substring(v3, v3 + var15_7);
                            v5 = -1;
                            break block14;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var19_3[var17_4++] = DamageVector.zkmDecodeUtf8(var20_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\u000b\u00c0o9\u00dc\u000es\u001e~\u00f92\u00c3,\u00a6\u0094\u00b36\u0098\u008f\u00ac\u009d\u00f7\u00d7\u00ce\u00b4[\u0000j\u00b7\u00fb>m 3N\u00d2\u00849\u00ebp\u00dd\u00e3\u0098\u00cb$\u0016M{U\u00ac$i\u00a9)\u001e<U\u00e5\fR\u00a0d\u0001gC";
                            var18_6 = 65;
                            var15_7 = 32;
                            var14_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var14_8;
                                v4 = var16_5.substring(v6, v6 + var15_7);
                                v5 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var19_3[var17_4++] = DamageVector.zkmDecodeUtf8(var20_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            break block15;
                            break;
                        }
                    }
                    var20_9 = var12_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                DamageVector.b = var19_3;
                DamageVector.c = new String[6];
                var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var10 >>> 56);
                for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                    v9 = v9;
                    v9[var6_11] = (byte)(var10 << var6_11 * 8 >>> 56);
                }
                break block16;
lbl65:
                // 1 sources

                while (true) {
                    continue;
                    break;
                }
            }
            var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
            var9_12 = var5_10.doFinal(new byte[]{-64, 33, -81, -76, -6, 121, -109, -97});
            ** while (true)
            DamageVector.e = ((long)var9_12[0] & 255L) << 56 | ((long)var9_12[1] & 255L) << 48 | ((long)var9_12[2] & 255L) << 40 | ((long)var9_12[3] & 255L) << 32 | ((long)var9_12[4] & 255L) << 24 | ((long)var9_12[5] & 255L) << 16 | ((long)var9_12[6] & 255L) << 8 | (long)var9_12[7] & 255L;
            var0_13 = Cipher.getInstance("DES/CBC/NoPadding");
            v10 = SecretKeyFactory.getInstance("DES");
            v11 = new byte[8];
            v12 = v11;
            v11[0] = (byte)(var10 >>> 56);
            for (var1_14 = 1; var1_14 < 8; ++var1_14) {
                v12 = v12;
                v12[var1_14] = (byte)(var10 << var1_14 * 8 >>> 56);
            }
            break block17;
lbl82:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_13.init(2, (Key)v10.generateSecret(new DESKeySpec(v12)), new IvParameterSpec(new byte[8]));
        var4_15 = var0_13.doFinal(new byte[]{58, 51, 68, 100, 61, -59, -43, -12});
        ** while (true)
        DamageVector.f = ((long)var4_15[0] & 255L) << 56 | ((long)var4_15[1] & 255L) << 48 | ((long)var4_15[2] & 255L) << 40 | ((long)var4_15[3] & 255L) << 32 | ((long)var4_15[4] & 255L) << 24 | ((long)var4_15[5] & 255L) << 16 | ((long)var4_15[6] & 255L) << 8 | (long)var4_15[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4DD6;
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
                throw new RuntimeException("su/catlean/m3", exception);
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
            DamageVector.c[n2] = DamageVector.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DamageVector.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/m3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DamageVector.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

