/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package su.catlean.render.particle;

import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Module;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.MatrixUtil;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.particle.WorldParticle;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ParticleManager {
    @NotNull
    public static final ParticleManager INSTANCE;
    @NotNull
    private static final ArrayList g;
    private static Module[] n;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    private ParticleManager() {
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = a ^ 0x2D69A31CA9E8L;
        long l2 = l ^ 0x79EF7CA117B7L;
        if (g.isEmpty()) {
            return;
        }
        render3DEvent.getStack().push();
        MatrixStack matrixStack = render3DEvent.getStack();
        Quaternionf quaternionf = RotationAxis.POSITIVE_Y.rotation(-RenderState.INSTANCE.getCameraYaw());
        Intrinsics.checkNotNullExpressionValue(quaternionf, "rotation(...)");
        MatrixUtil.multiply(matrixStack, (Quaternionfc)quaternionf);
        MatrixStack matrixStack2 = render3DEvent.getStack();
        Quaternionf quaternionf2 = RotationAxis.POSITIVE_X.rotation(-RenderState.INSTANCE.getCameraPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf2, "rotation(...)");
        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf2);
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = g.size();
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        MatrixStack matrixStack3 = new MatrixStack();
        Quaternionf quaternionf3 = RotationAxis.POSITIVE_X.rotation(RenderState.INSTANCE.getCameraPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf3, "rotation(...)");
        MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf3);
        Quaternionf quaternionf4 = RotationAxis.POSITIVE_Y.rotation(RenderState.INSTANCE.getCameraYaw());
        Intrinsics.checkNotNullExpressionValue(quaternionf4, "rotation(...)");
        MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf4);
        Quaternionf quaternionf5 = new Quaternionf().rotateYXZ(-RenderState.INSTANCE.getCameraYaw2(), RenderState.INSTANCE.getCameraPitch(), 0.0f);
        Iterable iterable = g;
        for (Object t : iterable) {
            WorldParticle worldParticle = (WorldParticle)t;
            if (!RenderState.INSTANCE.getFrustum().intersectPoint(worldParticle.getPos().x, worldParticle.getPos().y, worldParticle.getPos().z)) continue;
            Intrinsics.checkNotNull(quaternionf5);
            worldParticle.render(matrixStack3, (Quaternionfc)quaternionf5, batchedBuffer);
        }
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlendDepthtest(), null, null, render3DEvent.getStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2)), (int)e, null);
        render3DEvent.getStack().pop();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        g.removeIf(arg_0 -> ParticleManager.invokePredicate(ParticleManager::onPlayerUpdate$lambda$0, arg_0));
    }

    public final boolean spawn(@NotNull WorldParticle worldParticle) {
        Intrinsics.checkNotNullParameter(worldParticle, "p");
        return g.add(worldParticle);
    }

    private static final boolean onPlayerUpdate$lambda$0(WorldParticle worldParticle) {
        Intrinsics.checkNotNullParameter(worldParticle, "it");
        return worldParticle.tick();
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ParticleManager.a = ZkmKeys.a(4183442388880205054L, 1249731790012697374L, MethodHandles.lookup().lookupClass()).a(198072916641736L);
                    var14 = ParticleManager.a ^ 88344582763178L;
                    ParticleManager.d = new HashMap<K, V>(13);
                    ParticleManager.zkmSetFlag(null);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[6];
                    var10_4 = 0;
                    var9_5 = "\\^4\u008ai\u00ac\u00ae\u00e8P\u0096\u000f9\u00dcB\u0082p\u00b8\u00cd\u00c2\u00aauV\u00ec\u00cb\u00bc\u0084\u0019C\u00a1\u008a\u0087\u00b8 |\u009e\u00c7fk\u00e9h\u00efh\u0082\u0011\u00e2\u0003\u00ab;\u0080\u00dc\u00a1w\u00eciT\u00eb\u00a5\u009b\u0083\u00a9\u00fa\u0092\u00e6M\u00cf\u0010\u00be\u00e2-\u00b1\u00f3\u00bfN\u0014\u0086N\u00cb\u0085d\u001a\u00a3h(7\u00a3\u0014'\u00910\u0096\u00b0\u00a4Jr\u0019\u00c1\u0013\u0093T\u001c\u008b\t\u00b1\u00d4_\u0017\u0081?.\u00c15\u00a2\u00cbG+\u0006\u0019K\u007f\u00da-cR";
                    var11_6 = 123;
                    var8_7 = 32;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = ParticleManager.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00c0\u00ee\u00ed\u00d0[\u001c\u00c4U\u00fd\u00b9\u0091Ma^q\u00c1\u009a-\u00af#\u00c0ke\u00c5|;\"DK\u000f\u00d3\u0094-\u00e1\u00cb\u001at\u00b2\u008a\u0099 5\u001c=>\u00c0UDp\u00d6\u00c3Yi%\u0081&\u0005\u008f\u00def\u0082RH\u0095\u009a\u0083\u00ff\u0087\t\u000ey\u00f7\u00d2";
                        var11_6 = 73;
                        var8_7 = 40;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = ParticleManager.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            ParticleManager.b = var12_3;
            ParticleManager.c = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-50, 95, 77, 45, -9, -77, -9, -69});
        ** while (true)
        ParticleManager.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        ParticleManager.INSTANCE = new ParticleManager();
        ParticleManager.g = new ArrayList<E>();
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        n = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return n;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A6;
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
                throw new RuntimeException("su/catlean/dh", exception);
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
            ParticleManager.c[n2] = ParticleManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ParticleManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ParticleManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

