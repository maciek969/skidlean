/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation.aim;

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
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.Aura;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.RandomUtil;
import su.catlean.util.rotation.AimMode;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;

public final class DefaultAim
extends AimMode {
    @NotNull
    public static final DefaultAim INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private DefaultAim() {
    }

    @Override
    @NotNull
    public Rotation calcRotation(@NotNull Entity entity, long l, @NotNull Rotation rotation, boolean bl) {
        double d;
        long l2 = l ^ 0x453297F2EE4CL;
        Intrinsics.checkNotNullParameter(entity, "target");
        Intrinsics.checkNotNullParameter(rotation, "currentRotation");
        Vec3d vec3d = this.getAimPoint(l2, entity);
        entity.setBoundingBox(entity.getBoundingBox().expand(-0.15, 0.0, -0.15));
        boolean bl2 = PlaceManager.INSTANCE.canHitEntity(entity, new Rotation(MovementUtil.INSTANCE.getLastYaw(), MovementUtil.INSTANCE.getLastPitch(), false, null, 12, null), Aura.INSTANCE.getAttackRange() + 2.0f, !Aura.INSTANCE.getIgnoreWalls());
        entity.setBoundingBox(entity.getBoundingBox().expand(0.15, 0.0, 0.15));
        double d2 = MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d.z - Globals.getPlayer().getZ(), vec3d.x - Globals.getPlayer().getX())) - 90.0 - (double)rotation.getYaw()) + (double)RandomUtil.D(-1.0f, 1.0f, false, null);
        double d3 = -Math.toDegrees(Math.atan2(vec3d.y - (EntityUtil.getPos((Entity)((Entity)Globals.getPlayer())).y + (double)Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose())), Math.hypot(vec3d.x - Globals.getPlayer().getX(), vec3d.z - Globals.getPlayer().getZ()))) - (double)rotation.getPitch();
        double d4 = !Globals.getPlayer().isGliding() && (double)Aura.INSTANCE.getAttackRange() < 4.0 ? (bl2 ? RandomUtil.a(0.15, 0.21, false, null) : Math.abs(d2) / (double)RandomUtil.D(1.4f, 1.8f, false, null)) : (d = 360.0);
        double d5 = !Globals.getPlayer().isGliding() && (double)Aura.INSTANCE.getAttackRange() < 4.0 ? Math.max(Math.abs(d3) - 15.0, 0.0) / 2.0 + (Globals.getPlayer().age % 25 == 0 ? 2.0 * Math.random() : 0.0) : 180.0;
        double d6 = (double)rotation.getYaw() + Math.clamp(d2, -d, d);
        double d7 = (double)rotation.getPitch() + Math.clamp(d3, -d5, d5) + Math.sin(Math.toRadians(d2));
        return new Rotation((float)d6, (float)d7, false, null, 12, null);
    }

    @Override
    @NotNull
    public Vec3d getAimPoint(long l, @NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "target");
        if (Globals.getPlayer().isGliding()) {
            Vec3d vec3d = entity.getEyePos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
            return vec3d;
        }
        double d = entity.getBoundingBox().getLengthX() / 2.1 * Math.sin(Math.toRadians(entity.age));
        double d2 = entity.getBoundingBox().getLengthY() / 8.0 * Math.sin(Math.toRadians((double)entity.age + 10.0));
        double d3 = entity.getBoundingBox().getLengthZ() / 2.1 * -Math.cos(Math.toRadians(entity.age));
        Vec3d vec3d = EntityUtil.getPos(entity).add(d, (double)entity.getHeight() / 2.0 + d2, d3);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        return vec3d;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        DefaultAim.a = ZkmKeys.a(6018499707916333913L, -5358344310943380128L, MethodHandles.lookup().lookupClass()).a(128110490494167L);
                        var20 = DefaultAim.a ^ 87636124748298L;
                        DefaultAim.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "\u00a1\u00e0C\u00a63gx*\u00fdN\u00cd\u00bb\u000f\u0094\u0097\u00e5\u0018(\u00dd\u0005\u00fa\u009a\u00f9k\u00c2\u00c5\u00c4K\u0092\u00c2\u00cc\u0014\u00e6;t\t\u0086Al\u0099G gXj\u008e\u00c4T\u00f8N\f^\u00a3\u00f0\u00c3\u00f6;%\u0095i\u0015\u00d6\u00ee|\u0018w:\u008b\u00b6\u00ff\u00cf\u0017Z\u0086";
                        var17_6 = 74;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = DefaultAim.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0082D\u00140{%I9~=,\u00ab\u00b6\u00f2\u00d3\u00e3(\u00fd\u00b7\u0086\u001e\u00c9\u00a0\u00fd\u0093\u00aa\u00a4\u00d6\u00f3I>\u0081\u00c3\u00a9\u00b7/D\u00d5\u0097\u00cc\u00cf\u0094JKf\u00c6\u001b\u001bm\u00bb\u000e\u00a9\u00ea\u0082~5\u0088";
                            var17_6 = 57;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
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
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = DefaultAim.zkmDecodeUtf8(var19_9).intern();
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
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                DefaultAim.b = var18_3;
                DefaultAim.c = new String[5];
                DefaultAim.g = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u0004\u00bc8\u00fe\u00e5Z\u00cd=\u00e4\u00be@S\u0007\u0002`h\u00f6U\u00c84\u001f\u00bb\"\u0092";
                var5_15 = 24;
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00a3\u00ef\t\u00cc\u00b2\u0093\u00f6\u00c8\u0088\u0085\u00c0u\u0094\u00f2/\u00a2";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        DefaultAim.e = var6_12;
        DefaultAim.f = new Integer[5];
        DefaultAim.INSTANCE = new DefaultAim();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x14D5;
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
                throw new RuntimeException("su/catlean/t8", exception);
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
            DefaultAim.c[n2] = DefaultAim.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DefaultAim.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/t8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F6E;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/t8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DefaultAim.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DefaultAim.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/t8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DefaultAim.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(DefaultAim.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

