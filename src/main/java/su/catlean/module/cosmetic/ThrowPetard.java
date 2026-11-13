/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 */
package su.catlean.module.cosmetic;

import com.mojang.blaze3d.vertex.VertexFormat;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.particle.ParticleManager;
import su.catlean.render.particle.ParticleSprite;
import su.catlean.render.particle.WorldParticle;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public final class ThrowPetard
extends Module {
    @NotNull
    public static final ThrowPetard INSTANCE;
    private static float petardX;
    private static float petardY;
    private static float petardZ;
    private static float prevPetardX;
    private static float prevPetardY;
    private static float prevPetardZ;
    private static float velocityX;
    private static float velocityY;
    private static float velocityZ;
    private static int b;
    private static final long a;
    private static final String[] d;
    private static final String[] e;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private ThrowPetard() {
        super("throw-petard", Categories.getCosmetic(), null, null);
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().player == null) {
            return;
        }
        b = 0;
        Globals.getWorld().playSoundClient(petardX, (double)petardY + 0.5, petardZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 0.2f, 1.0f, false);
        petardX = (float)Globals.getPlayer().getEyePos().x;
        petardY = (float)Globals.getPlayer().getEyePos().y;
        petardZ = (float)Globals.getPlayer().getEyePos().z;
        velocityX = (float)Globals.getPlayer().getRotationVector().x;
        velocityY = (float)Globals.getPlayer().getRotationVector().y + 0.42f + (float)Globals.getPlayer().getVelocity().y;
        velocityZ = (float)Globals.getPlayer().getRotationVector().z;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        boolean bl;
        BlockHitResult blockHitResult;
        long l = a ^ 0x6559794C217DL;
        long l2 = l ^ 0x3D1D4D0276E6L;
        prevPetardX = petardX;
        prevPetardY = petardY;
        prevPetardZ = petardZ;
        petardX += velocityX;
        petardY += velocityY;
        petardZ += velocityZ;
        velocityX *= 0.98f;
        velocityY *= 0.98f;
        velocityZ *= 0.98f;
        BlockHitResult blockHitResult2 = blockHitResult = DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, new Vec3d(petardX, petardY, petardZ), new Vec3d(prevPetardX, prevPetardY, prevPetardZ), false, null, false, 28, null);
        boolean bl2 = bl = !(blockHitResult2 != null ? !blockHitResult2.equals((Object)HitResult.Type.MISS) : false);
        if (!bl) {
            petardX = prevPetardX;
            Object object = blockHitResult;
            petardY = object != null && (object = ((BlockHitResult)object).getBlockPos()) != null && (object = ((BlockPos)object).up()) != null ? (float)((Vec3i)object).getY() : prevPetardY;
            petardZ = prevPetardZ;
            velocityX *= 0.81f;
            velocityZ *= 0.81f;
        } else {
            velocityY -= 0.08f;
        }
        int n = b;
        b = n + 1;
        n = 3;
        for (int i = 0; i < n; ++i) {
            ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleSprite.STAR.getParticleTexture(), new Vec3d(petardX, (double)petardY + 0.5, petardZ), (float)(Math.random() * 50.0), 0.2f, 5, new Vec3d(Math.random() / 4.0 - 0.125, Math.random() / 2.0, Math.random() / 4.0 - 0.125), null, 0.0f, 0.0f, false, 960, null));
        }
        if (b > 100) {
            this.disable(l2);
            Globals.getWorld().playSoundClient(petardX, (double)petardY + 0.5, petardZ, SoundEvents.ENTITY_GENERIC_EXPLODE.comp_349(), SoundCategory.BLOCKS, 0.2f, 1.0f, false);
            int n2 = 100;
            for (int i = 0; i < n2; ++i) {
                ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleSprite.STAR.getParticleTexture(), new Vec3d(petardX, (double)petardY + 0.5, petardZ), (float)(Math.random() * 50.0), 0.5f, 20, new Vec3d((Math.random() - 0.5) * 1.5, Math.random(), (Math.random() - 0.5) * 1.5), null, 0.0f, 0.0f, false, 960, null));
                ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleSprite.FIRE_FLY.getParticleTexture(), new Vec3d(petardX, (double)petardY + 0.5, petardZ), (float)(Math.random() * 50.0), 0.3f, 20, new Vec3d((Math.random() - 0.5) * 1.5, Math.random(), (Math.random() - 0.5) * 1.5), null, 0.0f, 0.0f, false, 960, null));
            }
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = a ^ 0x5BFAA8F031D2L;
        long l2 = l ^ 0x780970A40CC6L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 56);
        int n3 = (int)(l2 << 40 >>> 40);
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        float f = RenderUtil.INSTANCE.lerp(prevPetardX, petardX, RenderUtil.INSTANCE.getTickDelta());
        float f2 = RenderUtil.INSTANCE.lerp(prevPetardY, petardY, RenderUtil.INSTANCE.getTickDelta());
        float f3 = RenderUtil.INSTANCE.lerp(prevPetardZ, petardZ, RenderUtil.INSTANCE.getTickDelta());
        render3DEvent.getStack().push();
        render3DEvent.getStack().translate((double)f - camera2.getCameraPos().x, (double)f2 - camera2.getCameraPos().y, (double)f3 - camera2.getCameraPos().z);
        this.renderPetard(n, (byte)n2, render3DEvent.getStack(), 0.0f, n3, 0.0f, 0.0f);
        render3DEvent.getStack().pop();
    }

    public final void renderPetard(int n, byte by, @NotNull MatrixStack matrixStack, float f, int n2, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        long l = ((long)n << 32 | (long)by << 56 >>> 32 | (long)n2 << 40 >>> 40) ^ a;
        long l2 = l ^ 0x346D98BD6B43L;
        Intrinsics.checkNotNullParameter(matrixStack, "matrices");
        Module[] moduleArray = new Module[]{};
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
        Matrix4f matrix4f2 = matrix4f;
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n3 = 4;
        boolean bl = false;
        int n4 = 48;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n4, bl, n3, defaultConstructorMarker);
        float f9 = 0.12f;
        Module[] moduleArray2 = moduleArray;
        float f10 = 0.5f;
        float f11 = 0.2f;
        int n5 = 16;
        for (int i = 0; i < n5; ++i) {
            float f12 = (float)(Math.PI * 2 * (double)i / 16.0);
            f8 = (float)(Math.PI * 2 * (double)(i + 1) / 16.0);
            f7 = f + (float)Math.cos(f12) * f9;
            f6 = f3 + (float)Math.sin(f12) * f9;
            f5 = f + (float)Math.cos(f8) * f9;
            f4 = f3 + (float)Math.sin(f8) * f9;
            Color color = Color.DARK_GRAY;
            BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(matrix4f2, f7, f2, f6);
            Intrinsics.checkNotNull(color);
            batchedBuffer2.color(color);
            batchedBuffer.vertex(matrix4f2, f7, f2 + f10, f6).color(color);
            batchedBuffer.vertex(matrix4f2, f5, f2 + f10, f4).color(color);
            batchedBuffer.vertex(matrix4f2, f5, f2, f4).color(color);
            if (moduleArray2 != null) continue;
            Module.zkmSetFlag(new Module[5]);
            break;
        }
        float f13 = f2 + 0.25f - 0.1f;
        for (int i = 0; i < n5; ++i) {
            f8 = (float)(Math.PI * 2 * (double)i / 16.0);
            f7 = (float)(Math.PI * 2 * (double)(i + 1) / 16.0);
            f6 = f + (float)Math.cos(f8) * 0.13f;
            f5 = f3 + (float)Math.sin(f8) * 0.13f;
            f4 = f + (float)Math.cos(f7) * 0.13f;
            float f14 = f3 + (float)Math.sin(f7) * 0.13f;
            BatchedBuffer batchedBuffer3 = batchedBuffer.vertex(matrix4f2, f6, f13, f5);
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            batchedBuffer3.color(color);
            BatchedBuffer batchedBuffer4 = batchedBuffer.vertex(matrix4f2, f6, f13 + f11, f5);
            Color color2 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
            batchedBuffer4.color(color2);
            BatchedBuffer batchedBuffer5 = batchedBuffer.vertex(matrix4f2, f4, f13 + f11, f14);
            Color color3 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color3, "WHITE");
            batchedBuffer5.color(color3);
            BatchedBuffer batchedBuffer6 = batchedBuffer.vertex(matrix4f2, f4, f13, f14);
            Color color4 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color4, "WHITE");
            batchedBuffer6.color(color4);
        }
        float f15 = 0.06f;
        f8 = 0.03f;
        BatchedBuffer batchedBuffer7 = batchedBuffer.vertex(matrix4f2, f - f8, f2 + f10 + f15, f3 - f8);
        Color color = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color, "BLACK");
        batchedBuffer7.color(color);
        BatchedBuffer batchedBuffer8 = batchedBuffer.vertex(matrix4f2, f - f8, f2 + f10, f3 - f8);
        Color color5 = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color5, "BLACK");
        batchedBuffer8.color(color5);
        BatchedBuffer batchedBuffer9 = batchedBuffer.vertex(matrix4f2, f + f8, f2 + f10, f3 + f8);
        Color color6 = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color6, "BLACK");
        batchedBuffer9.color(color6);
        BatchedBuffer batchedBuffer10 = batchedBuffer.vertex(matrix4f2, f + f8, f2 + f10 + f15, f3 + f8);
        Color color7 = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color7, "BLACK");
        batchedBuffer10.color(color7);
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, matrixStack.peek().getPositionMatrix(), null, null, 54, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ThrowPetard.a = ZkmKeys.a(-6034562963958908252L, -4729566867936998456L, MethodHandles.lookup().lookupClass()).a(125343006471290L);
                        var20 = ThrowPetard.a ^ 346152035543L;
                        ThrowPetard.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "\u00d7\u00abQ\u00c3Gv\u00ab\u00bd\u009d\u00d1\u00ff\u00e4?N\u00a4\u009c(\u000f\u00b0\u00c3K\t\u00fb\u00e4L\u0002q\u00b7m\u00c2\u00ec\u00e8O\u00d0\u00fa\u00d7\u00b3\u00cf-\u00c4\u008c\u0013oWH\u00e8q\u00a0L\u00d3\u00ccf=g\u00c1\u00fd\u00d1\u0010\u008d\u00cd\u00e1~\u00d6\u00df\u00f0P\u00fa\u00d4\u00d7\u008a\u0096O\u00fbI \u00f8\u0096\u0097o~$q\u00df\u00b8\u0003&^\u008cL\\\u008c\u00ba\u00e28R\f\u00bc\u00cb\u00ef\u0093}`\u008f\u00ea,\u00d5`\u0010]\u000eR~\u0098Nr\u00ef\u00c6\u00f1us\u00fd\u00e4\u00cc\u009c `?\u0084\u00f5]\u0094\u00c0\u009e\u00e3\u00f5\u008f@qV9\u00ad\u0010+\u00f5#\u00d0\u00f8\u00d4\u00b25\u00a0\u001a\u009ch]\u00de\t\u0018Y\u00a9\u008b\u00a5^\u00f6U\u00b4\u0081\u00c3\u00db\u00cd\u009f\u00ccZ\u00cf\u00d5:\u0011\u001c\u000b$\u00ddb";
                        var17_6 = 182;
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
                            var18_3[var16_4++] = ThrowPetard.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0089\u0013\u0003?\u0004Omr\u00dce\u001d06\u00cf\u00ca\u00b7 \u00d8\u00e9)\u008b\u00ec\u00c2\u00fc\n\u00ac\u00d6\u00aa\u0000\u00bf\u00fbH=\u00eb\u0090\u00beg\u00d7\b\u00d8q\u00a7\u008e&5nx\u00cb\u0097";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = ThrowPetard.zkmDecodeUtf8(var19_9).intern();
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
                ThrowPetard.d = var18_3;
                ThrowPetard.e = new String[9];
                ThrowPetard.j = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "&\u008c\u0085\u00fd\u008bs\u00f3\u001c{o\u00cd\u009a\u0089\u00bfu\u009cn\u00ba]\u009b\u000bi\u00e5f\u00a6\r\u008d\u0015\b\b/\u0089r\u00c7I\u00a7\u0096\u009db\u00cbyj'\u00ae\u00d0\u0089\u00abR\r\u00f1\u00c0\u0090\u00eb\u00f4\u00cci\u00f5\u0095D\u00aa%gP\n\u00d0)i\u00c7\u00cc?2\u00d759\u00ea\u008a\u0088\u0019\u00fa\u0014";
                var5_15 = 80;
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
                    var4_14 = "P\u00be\u00d8\u0087\u00a4\u0092\u001b\u008e5{\u00fc\u00dbj\u00e6\u00f0\u00ff";
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
        ThrowPetard.h = var6_12;
        ThrowPetard.i = new Integer[12];
        ThrowPetard.INSTANCE = new ThrowPetard();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3EB8;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/ku", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            ThrowPetard.e[n2] = ThrowPetard.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ThrowPetard.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ku" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6EEA;
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
                throw new RuntimeException("su/catlean/ku", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ThrowPetard.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ThrowPetard.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ku" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ThrowPetard.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ThrowPetard.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

