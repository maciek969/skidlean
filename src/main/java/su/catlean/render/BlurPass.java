/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWVidMode
 */
package su.catlean.render;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
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
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.KawaseUniforms;
import su.catlean.render.Pipelines;
import su.catlean.render.uniform.BlurUniformStorage;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BlurPass {
    @NotNull
    public static final BlurPass INSTANCE;
    private static long e;
    @NotNull
    private static final GpuTextureView[] U;
    @NotNull
    private static Vec2f vec2f;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private BlurPass() {
    }

    @NotNull
    public final GpuTextureView getOutput() {
        return U[0];
    }

    private final GpuTextureView createTarget(int n) {
        long l = 0L;
        l = a ^ l;
        double d = 1.0 / Math.pow(2.0, n);
        int n2 = (int)((double)Globals.getMc().getWindow().getFramebufferWidth() * d);
        int n3 = (int)((double)Globals.getMc().getWindow().getFramebufferHeight() * d);
        GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(RenderSystem.getDevice().createTexture((String)((Object)BlurPass.zkmBootstrap("y", (int)11251, (long)(0x40F5D32EA61EFDFBL ^ l))) + n, (int)BlurPass.zkmBootstrap2("x", (int)10681, (long)(0x49D930B538C659F4L ^ l)), TextureFormat.RGBA8, n2, n3, 1, 1));
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, (String)((Object)BlurPass.zkmBootstrap("y", (int)14938, (long)(0x77F2591624F6C53L ^ l))));
        return gpuTextureView;
    }

    /*
     * Unable to fully structure code
     */
    public final void shouldUpdate(long var1_1) {
        block14: {
            block15: {
                block12: {
                    block13: {
                        var5_2 = (var1_1 = BlurPass.a ^ var1_1) ^ 97723237725924L;
                        var11_3 = ZkmReferences.zkmBootstrap("\u00c5", (long)3271087722737222500L, (long)var1_1);
                        cfr_temp_0 = (double)(Globals.now() - BlurPass.e) - 1000.0 / (double)this.getRefreshRate();
                        v0 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                        if (var11_3 != null) break block13;
                        if (v0 <= 0) break block14;
                        v1 = Globals.getMc().getFramebuffer().getColorAttachmentView();
                        Intrinsics.checkNotNull(v1);
                        this.blitPass(var5_2, BlurPass.U[1], v1, Pipelines.INSTANCE.getProgramPostKawaseDownscale());
                        v0 = var12_4 = (double)true;
                    }
                    while (var12_4 < 3) {
                        this.blitPass(var5_2, BlurPass.U[var12_4 + true], BlurPass.U[var12_4], Pipelines.INSTANCE.getProgramPostKawaseDownscale());
                        ++var12_4;
                        if (var11_3 == null) {
                            if (var11_3 == null) continue;
                        }
                        break block12;
                    }
                    var12_4 = 3;
                }
                while (false < var12_4) {
                    this.blitPass(var5_2, BlurPass.U[var12_4 - true], BlurPass.U[var12_4], Pipelines.INSTANCE.getProgramPostKawaseUpscale());
                    --var12_4;
                    if (var11_3 == null) {
                        if (var11_3 == null) continue;
                    }
                    ** GOTO lbl-1000
                }
                v2 = (cfr_temp_1 = (float)Globals.getMc().getFramebuffer().textureWidth - BlurPass.vec2f.x) == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                if (var11_3 == null) {
                    ** if (v2 != false) goto lbl-1000
lbl-1000:
                    // 1 sources

                    {
                        v2 = (float)true;
                        ** GOTO lbl37
                    }
                }
                break block15;
lbl-1000:
                // 2 sources

                {
                    v2 = (float)false;
                }
            }
            if (v2 == false) ** GOTO lbl-1000
            cfr_temp_2 = (float)Globals.getMc().getFramebuffer().textureHeight - BlurPass.vec2f.y;
            v3 = cfr_temp_2 == 0.0f ? 0 : (cfr_temp_2 < 0.0f ? -1 : 1);
            if (var11_3 == null) {
                v3 = v3 == false ? (float)true : (float)false;
            }
            if (v3 == false) lbl-1000:
            // 2 sources

            {
                BlurPass.vec2f = new Vec2f(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                var13_5 = BlurPass.U.length;
                for (var12_4 = (double)false; var12_4 < var13_5; ++var12_4) {
                    BlurPass.U[var12_4].close();
                    BlurPass.U[var12_4] = this.createTarget((int)var12_4);
                    if (var11_3 == null) {
                        if (var11_3 == null) continue;
                    }
                    break;
                }
            } else {
                BlurPass.e = Globals.now();
            }
        }
    }

    private final void blitPass(long l, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2, RenderPipeline renderPipeline) {
        long l2 = (l = a ^ l) ^ 0x8D954584721L;
        BatchedBuffer.draw$default(new BatchedBuffer(null, 0, false, (int)BlurPass.zkmBootstrap2("x", (int)4813, (long)(0x3925BC3057B2BC0CL ^ l)), null).fullscreenQuad(), l2, renderPipeline, gpuTextureView, null, BlurUniformStorage.INSTANCE.getDynamicUniformStorage().write((DynamicUniformStorage.Uploadable)new KawaseUniforms(0.5f / (float)gpuTextureView.getWidth(0), 0.5f / (float)gpuTextureView.getHeight(0), 5.0f)), null, null, MapsKt.mapOf(TuplesKt.to(BlurPass.zkmBootstrap("y", (int)20466, (long)(0x22535C282107C774L ^ l)), gpuTextureView2)), (int)BlurPass.zkmBootstrap2("x", (int)23561, (long)(0x3B6832E2B33572CFL ^ l)), null);
    }

    /*
     * Enabled aggressive block sorting
     */
    private final int getRefreshRate() {
        Object object;
        long l = 0L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5489108365702505004L, (long)(l = a ^ l));
        if (callSite == null) {
            GLFWVidMode gLFWVidMode = GLFW.glfwGetVideoMode((long)GLFW.glfwGetPrimaryMonitor());
            if (gLFWVidMode != null) {
                object = gLFWVidMode.refreshRate();
                return (int)object;
            }
        }
        object = BlurPass.zkmBootstrap2("x", (int)30241, (long)(0x64CA47A7F5CB0EB0L ^ l));
        return (int)object;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block15: {
            block14: {
                block13: {
                    BlurPass.a = ZkmKeys.a(-5349481545566593660L, 6262944254155458921L, MethodHandles.lookup().lookupClass()).a(181222225104456L);
                    var20 = BlurPass.a ^ 56462929166591L;
                    BlurPass.d = new HashMap<K, V>(13);
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
                    var18_3 = new String[3];
                    var16_4 = 0;
                    var15_5 = "et\u00aa\u0018\u0014\u00dc\u00bd\u00b6\u00f2\u00d5=\u00fdUB\u00ae\u00b3\u0091(\u0016\u0013NY\u0006)c\u00ba\u009a@p\u00c0\u00db=~_\u00dc\u00df\u00e7\u00fd\u00ceX(\u008d\u00b1\u001c\rZ\u00e5}\u00b2\u00f2\u00fdsE\u000bx?\u00113'\u00d1\u0005\u00ce\u00e6\u0018\u00c7\u00c3\u00ce~\u0090\u0011{m\u0091y\u0004\u00fc\u00ce\u00c3''$ z|X\u00bf\u00a5\u00bf*\u00e9blR\t\u001fA\u00e0\u0084\u00ac0A\u0099\u00b0;\u00d5\u00a6{\u00b9\u0081g\u008e\u00b8\u0082t";
                    var17_6 = 114;
                    var14_7 = 40;
                    var13_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        continue;
                        break;
                    }
lbl22:
                    // 1 sources

                    while (true) {
                        var18_3[var16_4++] = BlurPass.zkmDecodeUtf8(var19_9).intern();
                        if ((var13_8 += var14_7) < var17_6) {
                            var14_7 = var15_5.charAt(var13_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                    v3 = ++var13_8;
                    var19_9 = var11_1.doFinal(var15_5.substring(v3, v3 + var14_7).getBytes("ISO-8859-1"));
                    ** while (true)
                }
                BlurPass.b = var18_3;
                BlurPass.c = new String[3];
                BlurPass.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v4 = SecretKeyFactory.getInstance("DES");
                v5 = new byte[8];
                v6 = v5;
                v5[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v6 = v6;
                    v6[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v4.generateSecret(new DESKeySpec(v6)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "g\u00b0\u0014\u00a3z\u001d\u001e'\u001b?\u0006SP{\u008ch\u00f3\u00bf!\u00e1\u008c\n5\u00c7\np\u00e6\u0004\u009d\u0003yj";
                var5_15 = 32;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v7 = var6_12;
                    v8 = var3_13++;
                    v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v10 = -1;
                    break block14;
                    break;
                }
lbl57:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u0010\u0019S`\u00ec\u00e0\u00ba\u00cd\u0082=\u00aa\u00f9!\u00cb\u00d4\n";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v7 = var6_12;
                        v8 = var3_13++;
                        v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v10 = 0;
                        break block14;
                        break;
                    }
                    break;
                }
lbl70:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    break block15;
                    break;
                }
            }
            var8_18 = v9;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v11 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v10) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl83:
                // 1 sources

                ** continue;
            }
        }
        BlurPass.f = var6_12;
        BlurPass.g = new Integer[6];
        BlurPass.INSTANCE = new BlurPass();
        var24_20 = 0;
        var25_21 = new GpuTextureView[BlurPass.zkmBootstrap2("x", (int)25140, (long)(4726414810083137221L ^ var20))];
        while (var24_20 < BlurPass.zkmBootstrap2("x", (int)16427, (long)(4396120638003623129L ^ var20))) {
            var26_22 = var24_20++;
            var25_21[var26_22] = BlurPass.INSTANCE.createTarget(var26_22);
        }
        BlurPass.U = var25_21;
        BlurPass.vec2f = new Vec2f(0.0f, 0.0f);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x13B6;
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
                throw new RuntimeException("su/catlean/dv", exception);
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
            BlurPass.c[n2] = BlurPass.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BlurPass.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x35F6;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/dv", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BlurPass.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BlurPass.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/dv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BlurPass.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BlurPass.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

