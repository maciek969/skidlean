/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.awt.Color;
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
import kotlin.NoWhenBranchMatchedException;
import kotlin.ranges.RangesKt;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.cosmetic.AmbientParticle$WhenMappings;
import su.catlean.module.cosmetic.Particles;
import su.catlean.module.cosmetic.ParticlesMode;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.util.RandomUtil;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public final class AmbientParticle {
    private float posX;
    private float posY;
    private float posZ;
    private float motionX;
    private float motionY;
    private float motionZ;
    private float rot;
    private float rotSpeed;
    private float size;
    private float prevPosX;
    private float prevPosY;
    private float prevPosZ;
    private float prevRot;
    private int maxAge;
    private int age;
    private boolean R;
    private boolean V;
    private int hue;
    @NotNull
    private final Color color;
    private float groundY;
    private static final long a;
    private static final long[] c;
    private static final Integer[] d;
    private static final Map e;

    public AmbientParticle(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        BlockPos blockPos;
        BlockPos blockPos2;
        Particles particles;
        int n;
        this.posX = f;
        this.posY = f2;
        this.posZ = f3;
        this.motionX = f4;
        this.motionY = f5;
        this.motionZ = f6;
        this.rot = f7;
        this.rotSpeed = f8;
        this.size = f9;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRot = this.rot;
        if (Particles.access$getPhysics(Particles.INSTANCE) == ParticlesMode.RAIN) {
            n = 80;
        } else {
            particles = Particles.INSTANCE;
            n = (int)((float)Particles.access$getLifetime(particles) + (float)Particles.access$getLifetime(Particles.INSTANCE) * RandomUtil.p(RangesKt.rangeTo(0.0f, 0.5f), false, null));
        }
        this.age = this.maxAge = n;
        this.hue = (int)(360.0f * RandomUtil.p(RangesKt.rangeTo(0.0f, 1.0f), false, null));
        particles = Particles.INSTANCE;
        this.color = switch (AmbientParticle$WhenMappings.K[Particles.access$getLmode(particles).ordinal()]) {
            case 1 -> Particles.access$getColor(Particles.INSTANCE);
            case 2 -> Theme.INSTANCE.getGradientColor(this.hue);
            case 3 -> RenderUtil.INSTANCE.rainbow(18, this.hue);
            case 4 -> RenderUtil.INSTANCE.getRainbow(18, this.hue, 0.6f, 1.0f);
            case 5 -> RenderUtil.INSTANCE.getRainbow(18, this.hue, 1.0f, 1.0f);
            default -> throw new NoWhenBranchMatchedException();
        };
        BlockHitResult blockHitResult = DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, new Vec3d(this.posX, this.posY, this.posZ), new Vec3d(this.posX, (double)this.posY - 128.0, this.posZ), false, null, false, 28, null);
        this.groundY = blockHitResult != null && (blockPos2 = blockHitResult.getBlockPos()) != null && (blockPos = blockPos2.up()) != null ? (float)blockPos.getY() : this.posY;
    }

    public final float getPosX() {
        return this.posX;
    }

    public final void setPosX(float f) {
        this.posX = f;
    }

    public final float getPosY() {
        return this.posY;
    }

    public final void setPosY(float f) {
        this.posY = f;
    }

    public final float getPosZ() {
        return this.posZ;
    }

    public final void setPosZ(float f) {
        this.posZ = f;
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

    public final float getRot() {
        return this.rot;
    }

    public final void setRot(float f) {
        this.rot = f;
    }

    public final float getRotSpeed() {
        return this.rotSpeed;
    }

    public final void setRotSpeed(float f) {
        this.rotSpeed = f;
    }

    public final float getSize() {
        return this.size;
    }

    public final void setSize(float f) {
        this.size = f;
    }

    public final float getPrevPosX() {
        return this.prevPosX;
    }

    public final void setPrevPosX(float f) {
        this.prevPosX = f;
    }

    public final float getPrevPosY() {
        return this.prevPosY;
    }

    public final void setPrevPosY(float f) {
        this.prevPosY = f;
    }

    public final float getPrevPosZ() {
        return this.prevPosZ;
    }

    public final void setPrevPosZ(float f) {
        this.prevPosZ = f;
    }

    public final float getPrevRot() {
        return this.prevRot;
    }

    public final void setPrevRot(float f) {
        this.prevRot = f;
    }

    public final int getMaxAge() {
        return this.maxAge;
    }

    public final void setMaxAge(int n) {
        this.maxAge = n;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int n) {
        this.age = n;
    }

    public final boolean r() {
        return this.R;
    }

    public final void K(boolean bl) {
        this.R = bl;
    }

    public final boolean t() {
        return this.V;
    }

    public final void k(boolean bl) {
        this.V = bl;
    }

    public final int getHue() {
        return this.hue;
    }

    public final void setHue(int n) {
        this.hue = n;
    }

    @NotNull
    public final Color getColor() {
        return this.color;
    }

    public final float getGroundY() {
        return this.groundY;
    }

    public final void setGroundY(float f) {
        this.groundY = f;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block9: {
            block8: {
                AmbientParticle.a = ZkmKeys.a(2829213519929027138L, 4420343396535405648L, MethodHandles.lookup().lookupClass()).a(260760626420435L);
                AmbientParticle.e = new HashMap<K, V>(13);
                var0 = AmbientParticle.a ^ 76953059398063L;
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
                var8_3 = new long[5];
                var5_4 = 0;
                var6_5 = "$?Y\u00c54\u0004\u00e1\u00fc\u00d3>\u0000\u008aGt\u0094\u00c7r\u00d5\u00c7u\u008c(\u00dd\u00e4";
                var7_6 = 24;
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
                    var6_5 = "S(\u00d1\u00ef\u00f1\u00f48\u0006\u00ac\u00eax)\u007f\u00d0\"\u0090";
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
        AmbientParticle.c = var8_3;
        AmbientParticle.d = new Integer[5];
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1659;
        if (d[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = c[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])e.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/r4", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AmbientParticle.d[n2] = n3;
        }
        return d[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AmbientParticle.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/r4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AmbientParticle.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

