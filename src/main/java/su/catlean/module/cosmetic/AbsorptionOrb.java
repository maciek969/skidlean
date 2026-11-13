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
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AbsorptionOrb {
    private float yaw;
    private float prevYaw;
    private float pitch;
    private final float radius;
    private float alpha;
    private float prevAlpha;
    private boolean fadeIn;
    private final float spinSpeed;
    private final int lifeTime;
    private int age;
    private static String[] o;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    public AbsorptionOrb(float f, float f2, float f3, float f4, float f5, float f6, boolean bl, float f7, int n, int n2) {
        this.yaw = f;
        this.prevYaw = f2;
        this.pitch = f3;
        this.radius = f4;
        this.alpha = f5;
        this.prevAlpha = f6;
        this.fadeIn = bl;
        this.spinSpeed = f7;
        this.lifeTime = n;
        this.age = n2;
    }

    public AbsorptionOrb(float f, float f2, float f3, float f4, float f5, float f6, boolean bl, float f7, DefaultConstructorMarker defaultConstructorMarker) {
        f2 = f;
        f5 = 0.0f;
        f6 = 0.0f;
        bl = true;
        f7 = 1.5f + ThreadLocalRandom.current().nextFloat() * 1.0f;
        int n = ThreadLocalRandom.current().nextInt(15, 35);
        int n2 = 0;
        this(f, f2, f3, f4, f5, f6, bl, f7, n, n2);
    }

    public final float getYaw() {
        return this.yaw;
    }

    public final void setYaw(float f) {
        this.yaw = f;
    }

    public final float getPrevYaw() {
        return this.prevYaw;
    }

    public final void setPrevYaw(float f) {
        this.prevYaw = f;
    }

    public final float getPitch() {
        return this.pitch;
    }

    public final void setPitch(float f) {
        this.pitch = f;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final float getPrevAlpha() {
        return this.prevAlpha;
    }

    public final void setPrevAlpha(float f) {
        this.prevAlpha = f;
    }

    public final boolean isFadeIn() {
        return this.fadeIn;
    }

    public final void setFadeIn(boolean bl) {
        this.fadeIn = bl;
    }

    public final float getSpinSpeed() {
        return this.spinSpeed;
    }

    public final int getLifeTime() {
        return this.lifeTime;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int n) {
        this.age = n;
    }

    public final float getYaw2() {
        return this.yaw;
    }

    public final float getPrevYaw2() {
        return this.prevYaw;
    }

    public final float getPitch2() {
        return this.pitch;
    }

    public final float getRadius2() {
        return this.radius;
    }

    public final float getAlpha2() {
        return this.alpha;
    }

    public final float getPrevAlpha2() {
        return this.prevAlpha;
    }

    public final boolean isFadeIn2() {
        return this.fadeIn;
    }

    public final float getSpinSpeed2() {
        return this.spinSpeed;
    }

    public final int getLifeTime2() {
        return this.lifeTime;
    }

    public final int getAge2() {
        return this.age;
    }

    @NotNull
    public final AbsorptionOrb copy(float f, float f2, float f3, float f4, float f5, float f6, boolean bl, float f7, int n, int n2) {
        return new AbsorptionOrb(f, f2, f3, f4, f5, f6, bl, f7, n, n2);
    }

    public static AbsorptionOrb copy$default(AbsorptionOrb absorptionOrb, float f, float f2, float f3, float f4, float f5, float f6, boolean bl, float f7, long l, int n, int n2, int n3, Object object) {
        AbsorptionOrb absorptionOrb2;
        block31: {
            block30: {
                int n4;
                block29: {
                    l = a ^ l;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7551780177275202822L, (long)l);
                    n4 = n3 & 1;
                    if (callSite == null) {
                        if (n4 != 0) {
                            f = absorptionOrb.yaw;
                        }
                        n4 = n3 & 2;
                    }
                    CallSite callSite2 = callSite;
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                f2 = absorptionOrb.prevYaw;
                            }
                            n4 = n3 & 4;
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                f3 = absorptionOrb.pitch;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)19358, (long)(0x2D339F32A8333C59L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                f4 = absorptionOrb.radius;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)23093, (long)(0x46956F3A8F39ADF9L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                f5 = absorptionOrb.alpha;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)3023, (long)(0x172C669ECFB2FC0AL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                f6 = absorptionOrb.prevAlpha;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)18082, (long)(0x2CE95468F66FB16DL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                bl = absorptionOrb.fadeIn;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)26265, (long)(0x476D648AFD10915AL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                f7 = absorptionOrb.spinSpeed;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)18401, (long)(0x3EEA25BF43EEB020L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (n4 != 0) {
                                n = absorptionOrb.lifeTime;
                            }
                            n4 = n3 & AbsorptionOrb.zkmBootstrap2("u", (int)10580, (long)(0x2F5BC03F32B85E9FL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) break block29;
                    if (n4 == 0) break block30;
                    n4 = absorptionOrb.age;
                }
                n2 = n4;
            }
            absorptionOrb2 = absorptionOrb.copy(f, f2, f3, f4, f5, f6, bl, f7, n, n2);
            if (l < 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)7499614563403986562L, (long)l) == null) break block31;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new String[5], (long)7539525382911617429L, (long)l);
        }
        return absorptionOrb2;
    }

    @NotNull
    public String toString() {
        int n = this.age;
        int n2 = this.lifeTime;
        float f = this.spinSpeed;
        boolean bl = this.fadeIn;
        float f2 = this.prevAlpha;
        float f3 = this.alpha;
        float f4 = this.radius;
        float f5 = this.pitch;
        float f6 = this.prevYaw;
        float f7 = this.yaw;
        return "Particle3D(yaw=" + f7 + ", prevYaw=" + f6 + ", pitch=" + f5 + ", radius=" + f4 + ", alpha=" + f3 + ", prevAlpha=" + f2 + ", fadeIn=" + bl + ", spinSpeed=" + f + ", lifeTime=" + n2 + ", age=" + n + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.yaw);
        n = n * 31 + Float.hashCode(this.prevYaw);
        n = n * 31 + Float.hashCode(this.pitch);
        n = n * 31 + Float.hashCode(this.radius);
        n = n * 31 + Float.hashCode(this.alpha);
        n = n * 31 + Float.hashCode(this.prevAlpha);
        n = n * 31 + Boolean.hashCode(this.fadeIn);
        n = n * 31 + Float.hashCode(this.spinSpeed);
        n = n * 31 + Integer.hashCode(this.lifeTime);
        n = n * 31 + Integer.hashCode(this.age);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AbsorptionOrb)) {
            return false;
        }
        AbsorptionOrb absorptionOrb = (AbsorptionOrb)object;
        if (Float.compare(this.yaw, absorptionOrb.yaw) != 0) {
            return false;
        }
        if (Float.compare(this.prevYaw, absorptionOrb.prevYaw) != 0) {
            return false;
        }
        if (Float.compare(this.pitch, absorptionOrb.pitch) != 0) {
            return false;
        }
        if (Float.compare(this.radius, absorptionOrb.radius) != 0) {
            return false;
        }
        if (Float.compare(this.alpha, absorptionOrb.alpha) != 0) {
            return false;
        }
        if (Float.compare(this.prevAlpha, absorptionOrb.prevAlpha) != 0) {
            return false;
        }
        if (this.fadeIn != absorptionOrb.fadeIn) {
            return false;
        }
        if (Float.compare(this.spinSpeed, absorptionOrb.spinSpeed) != 0) {
            return false;
        }
        if (this.lifeTime != absorptionOrb.lifeTime) {
            return false;
        }
        return this.age == absorptionOrb.age;
    }

    public static void zkmSetFlag(String[] stringArray) {
        o = stringArray;
    }

    public static String[] zkmGetFlag() {
        return o;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AbsorptionOrb.a = ZkmKeys.a(1258532364230898749L, -780104895118626961L, MethodHandles.lookup().lookupClass()).a(223123135537519L);
                        var20 = AbsorptionOrb.a ^ 22759677013178L;
                        AbsorptionOrb.e = new HashMap<K, V>(13);
                        AbsorptionOrb.zkmSetFlag(null);
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
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "\u00b3N\u001fT]\u00b6)\u0019?'nm\t\u00ded2C\u0082.\u0007\u0013\u00c1\u00bc\u00b8 w\u00ece\u008c\u00baW\u0000\u00867x{\u00e0Lr\u00dc\u00e7|=*\u0000\u00b3\u00a5\u00db\u00b97E\u00f5\u00d8\u008b\u00f1\u00ca\u00a6 \u00cdbw_\u008c6\u00bb\u00b7N\u00e3\u0002\u00ce\u00b2\t\u0018 T#Y\u0080\u00bd?\u0086\u008a\u000f\u00a7\u00dfF\u00c5\u0084\u00d7\u00da \u00cc\u009bp\u00df\u0010\u00ac\u008b\u00b9\u0014\u00e1\u00e3sabV.\u0094\u0001\u0013\u00b4\u00fc\u00bd\u00ef\u00e6\u008a\u00e9\u00f6G\u009d\u00d4\u00b9f Z\u00810\u00d3\u00a86_2\u00e0g|\u00c0\u009d\u001b\u00a6\u00deCv\u00c7E%eZ\u0002\u00bc\u00d5\u00a2\u00f9:\u00a9I\u0007 \u00bb\u0087#\u000f*\u00d1\u00c7g\u00a5\u00a0\u0019\u00d8\u0099K\u0015c}\u0000\u00dd\u001e\u00b3\u000e\u00f3\u0096X\u00ccN\u00bb]\u00edd\u0090 9\u0094 \u0085n\u00e4\u001e'\u00a9\tf\u008ed\u00b3\u00b5\u00c1\u0098T\u0080W\u0000$\u00de\u0004\u009b\u0006\u0019R`\u00eb~A\u0018\u00c6\u009dJI\u0001\u008a9x\u0000Y\u00f1I\u00d4j\u0084\u00ff\u00feV\u00c0\u00bc\u001c;\u00c3\u00f9";
                        var17_6 = 247;
                        var14_7 = 24;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AbsorptionOrb.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e6\u0083\u00ae\u0084\u00a8[\u001b,\u00d4\u001bq\u00eaa\u00ed\u0015U \u00e7\u00b5bd\u00c4\u00c7x)\u00c2\u00a7\u00fb\u00e4\u00eca\u00b1|\u00bbB5\u001f\u00c5\u0013\u00a4`\u00b8\u00d5\u0097\u0093\u0081\u0095\u001c\u00e6";
                            var17_6 = 49;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AbsorptionOrb.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                AbsorptionOrb.c = var18_3;
                AbsorptionOrb.d = new String[10];
                AbsorptionOrb.j = new HashMap<K, V>(13);
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
                var6_12 = new long[17];
                var3_13 = 0;
                var4_14 = "al`:\u00ae\u00c0\u0003`\u00f5j\u0094\\}\u00bc\u001e\u00f0\u0084\u001f&\u00ebn\u00e9!|\u001fw\u00ef\u00d0F\u00e0.\u00d29|\u008fqM\u00fd\u0005\u00ec\u00b9<\u000eA\u0005\u00ac\u00d1\u00ef(\u00d1\u00e9\u009a\u00ed\u00dd\u0013\u0093\u00bc\u00cc\u00ed\u000f\u00c3\u00f22N\b\u008e\u00ba\u00f7\u00ee\u0094\u00f6\u00c2\u00d2\u0099\u009et\u00cd\u0002#\u00bb>&qK\u00034\u00fa\u0013\u0002\u00c7=\u00c0\u001a\u0000%Z'F\u00daSkrY\u00e1r\u00c0\u00a6\u00ecZ_\u00e1v1\u00bc\u00d7\u001c\u0019\u00c6\u0092\u00f4";
                var5_15 = 120;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "y\u00ac\u00a9q\u00ad\u00e1\u009bB/G0*\u0015\b\u009b\u001e";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl92:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl105:
                // 1 sources

                ** continue;
            }
        }
        AbsorptionOrb.h = var6_12;
        AbsorptionOrb.i = new Integer[17];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x667E;
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
                throw new RuntimeException("su/catlean/ti", exception);
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
            AbsorptionOrb.d[n2] = AbsorptionOrb.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AbsorptionOrb.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ti" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1E80;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ti", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AbsorptionOrb.i[n2] = n3;
        }
        return i[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AbsorptionOrb.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ti" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AbsorptionOrb.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AbsorptionOrb.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

