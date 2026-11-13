/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import kotlin.ranges.RangesKt;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.AutoCrystalRender$FadeEntry;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;

public final class AutoCrystalRender {
    @NotNull
    public static final AutoCrystalRender INSTANCE;
    @NotNull
    private static final Identifier texture;
    @NotNull
    private static final List fadeEntries;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AutoCrystalRender() {
    }

    public final void renderTexture(@NotNull MatrixStack matrixStack, char c, @NotNull Box box, char c2, int n) {
        long l = ((long)c << 48 | (long)c2 << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        long l2 = l ^ 0x3E508B13E1E7L;
        Intrinsics.checkNotNullParameter(matrixStack, "m");
        Intrinsics.checkNotNullParameter(box, "box");
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        Color color = null;
        int n2 = 4;
        boolean bl = false;
        int n3 = 6;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n3, bl, n2, (DefaultConstructorMarker)((Object)color));
        Color color2 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(0), 0.1f);
        Color color3 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(180), 1.0f);
        if (!RenderState.INSTANCE.getFrustum().isVisible(box)) {
            return;
        }
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "position(...)");
        Vec3d vec3d2 = vec3d;
        float f = (float)(box.minX - vec3d2.getX());
        float f2 = (float)(box.minY - vec3d2.getY());
        float f3 = (float)(box.minZ - vec3d2.getZ());
        float f4 = (float)(box.maxX - vec3d2.getX());
        float f5 = (float)(box.maxY - vec3d2.getY());
        float f6 = (float)(box.maxZ - vec3d2.getZ());
        float f7 = RenderUtil.INSTANCE.interpolate((float)Globals.getPlayer().age - 1.0f, Globals.getPlayer().age) % 80.0f / 80.0f;
        batchedBuffer.vertex(f, f2, f3).texture(0.0f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f, f5, f3).texture(0.0f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f4, f5, f3).texture(0.32f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f4, f2, f3).texture(0.32f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f4, f2, f3).texture(0.32f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f4, f5, f3).texture(0.32f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f4, f5, f6).texture(0.64f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f4, f2, f6).texture(0.64f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f4, f2, f6).texture(0.64f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f, f2, f6).texture(0.96f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f, f5, f6).texture(0.96f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f4, f5, f6).texture(0.64f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f, f2, f6).texture(0.96f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f, f2, f3).texture(1.28f + f7, 1.0f).color(color2);
        batchedBuffer.vertex(f, f5, f3).texture(1.28f + f7, 0.0f).color(color3);
        batchedBuffer.vertex(f, f5, f6).texture(0.96f + f7, 0.0f).color(color3);
        RenderState.INSTANCE.addFace(box, Direction.UP, RenderUtil.INSTANCE.withAlpha(color3, 0.4f));
        Color color4 = RenderUtil.INSTANCE.withAlpha(color3, 0.4f);
        Direction direction = Direction.UP;
        Box box2 = box;
        RenderState.INSTANCE.addFace2(box2, direction, color4);
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend(), null, null, matrixStack.peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", Globals.getMc().getTextureManager().getTexture(texture).getGlTextureView())), 22, null);
        Iterable iterable = fadeEntries;
        for (Object t : iterable) {
            Box box3;
            AutoCrystalRender$FadeEntry autoCrystalRender$FadeEntry = (AutoCrystalRender$FadeEntry)t;
            double d = 0.5 + (double)RangesKt.coerceIn(RenderUtil.INSTANCE.interpolate((float)autoCrystalRender$FadeEntry.getAge() + 1.0f, autoCrystalRender$FadeEntry.getAge()) / 8.0f, 0.0f, 0.5f);
            Intrinsics.checkNotNullExpressionValue(box.withMaxY(box.maxY - (1.0 - d)).expand(-d, 0.0, -d), "inflate(...)");
            float f8 = (float)d;
            color = RenderUtil.INSTANCE.withAlpha(color3, f8);
            Direction direction2 = Direction.UP;
            RenderState.INSTANCE.addFace2(box3, direction2, color);
        }
    }

    public final void addFade() {
        fadeEntries.add(new AutoCrystalRender$FadeEntry(null));
    }

    public final void removeFinished() {
        fadeEntries.removeIf(arg_0 -> AutoCrystalRender.invokePredicate(AutoCrystalRender::removeFinished$lambda$0, arg_0));
    }

    private static final boolean removeFinished$lambda$0(AutoCrystalRender$FadeEntry autoCrystalRender$FadeEntry) {
        Intrinsics.checkNotNullParameter(autoCrystalRender$FadeEntry, "it");
        int n = autoCrystalRender$FadeEntry.getAge();
        autoCrystalRender$FadeEntry.setAge(n + -1);
        return n < 0;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoCrystalRender.a = ZkmKeys.a(-3705592923189375570L, 1094611980104253637L, MethodHandles.lookup().lookupClass()).a(24076002932298L);
                        var20 = AutoCrystalRender.a ^ 31979969786481L;
                        AutoCrystalRender.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[8];
                        var16_4 = 0;
                        var15_5 = "v\u0093\u00ae\u000b\u0007\u0013tk\u001b\u0091\u00cc\u0017B7\u00cbq\u0010o\u00df\u0015B\u009dW\u00d4\u00d0\u00ff:\u00ed\u00a0\u00c5\u00b6\u00e7\u00f5(-Q&C\u0002\u0080/9M\u009e*-\u0000\u00ccq\u00ff#\u0085\u00d1\u00eb\u008ci\fj\u000f\u00ab\u00017=|\u00d1\u00bd?\u00a1\u00a6\u00d1\u00c5\"\u0084\u00b38 \u0094\u000b\u00b9<\u00f9\tE\u00f4\u008eF|y\u00d0\u00e6\u0002:\u00b29\\:H\u00efs\u008a\u0088\u00b9.Xv\b\u00e1\n\u0080\u00f3\u008a\u00047\u00c4\u0005\u00ab\n\u00de\u00e3f\u00fe\u00e3\u00066\u0096\u000fI\u0002:\u00f9\u00d9\u0010\u00baE\u00e6?q\u009e\u00e6\u001c\u00cc[l\u0087\u0003j|! ;\u00a9\u00fb\u00a6y\u00822\u00172:\u0090%\u00b4\u00fc\u00d3\u00d2\u00a8\u001aVu\u00e1\u00caX2\u0011\u00b6\u00d0dm\u00acMd";
                        var17_6 = 181;
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
                            var18_3[var16_4++] = AutoCrystalRender.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "nr\u0099\u0097(Z\u00b5\u0011\u0097\u00dfP\u00b1\u00b2\u00e5\u00fc\u00bba\u00a1\u00f5H\u00e0\u00c2pF\u00e8$[\u0094\u008f\u00c5\u0011\u0007 0\u00fct\u00bev?$\u00d0X\u00a7\u00ab\u00ea\u00f8\n8]&\u00f9/5\u00a2\u00a2\u0015\u00a7\u0010Q\u0095\u00de\u0099\u00ce6\u00bf";
                            var17_6 = 65;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = AutoCrystalRender.zkmDecodeUtf8(var19_9).intern();
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
                AutoCrystalRender.b = var18_3;
                AutoCrystalRender.c = new String[8];
                AutoCrystalRender.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "F\u0000;\u00f44\u00e76\u0004-GNvf\u001a\u00e3\u0096";
                var5_15 = 16;
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
                    var4_14 = "\u00ectD\u009a\u00afn\u00be\u00b5\u00c3\u00a2\\\u0084]\u0089v\u0014";
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
        AutoCrystalRender.e = var6_12;
        AutoCrystalRender.f = new Integer[4];
        AutoCrystalRender.INSTANCE = new AutoCrystalRender();
        AutoCrystalRender.texture = Ids.id("catlean", "textures/exclude/block.png");
        AutoCrystalRender.fadeEntries = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x53EF;
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
                throw new RuntimeException("su/catlean/_b", exception);
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
            AutoCrystalRender.c[n2] = AutoCrystalRender.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoCrystalRender.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_b" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2855;
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
                throw new RuntimeException("su/catlean/_b", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoCrystalRender.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoCrystalRender.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_b" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoCrystalRender.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoCrystalRender.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

