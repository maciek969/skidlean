/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Quaternionfc
 */
package su.catlean.module.cosmetic;

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
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.SplashPotionEntity;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.EntityRemove;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.BuffParticle;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.MatrixUtil;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.particle.ParticleManager;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.render.particle.WorldParticle;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

public final class BuffEffect
extends Module {
    @NotNull
    public static final BuffEffect INSTANCE;
    @NotNull
    private static final List particles;
    private static int Y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private BuffEffect() {
        super("buff-effect", Categories.getCosmetic(), null, null);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onRender3D(Render3DEvent var1_1) {
        block3: {
            var2_2 = BuffEffect.a ^ 119673418975353L;
            var12_3 = var2_2 ^ 95923440804147L;
            v0 = BuffParticle.d();
            v1 = Globals.getMc().gameRenderer.getCamera();
            Intrinsics.checkNotNullExpressionValue(v1, "getMainCamera(...)");
            var29_4 = v1;
            var1_1.getStack().push();
            v2 = var1_1.getStack();
            v3 = RotationAxis.POSITIVE_Y.rotationDegrees(-var29_4.getYaw() + 180.0f);
            Intrinsics.checkNotNullExpressionValue(v3, "rotationDegrees(...)");
            MatrixUtil.multiply(v2, (Quaternionfc)v3);
            v4 = var1_1.getStack();
            v5 = RotationAxis.POSITIVE_X.rotationDegrees(-var29_4.getPitch());
            Intrinsics.checkNotNullExpressionValue(v5, "rotationDegrees(...)");
            MatrixUtil.multiply(v4, (Quaternionfc)v5);
            var28_5 = v0;
            v6 = VertexFormats.POSITION_TEXTURE_COLOR;
            Intrinsics.checkNotNullExpressionValue(v6, "POSITION_TEX_COLOR");
            var23_6 = null;
            var24_7 = 4;
            var25_8 = false;
            var26_9 = BuffEffect.particles.size() * 2;
            var27_10 = v6;
            var30_11 = new BatchedBuffer(var27_10, var26_9, var25_8, var24_7, var23_6);
            for (BuffParticle var32_13 : BuffEffect.particles) {
                var33_14 = 0.0f;
                var35_15 = RenderUtil.INSTANCE.interpolate(Math.max(1, var32_13.getAge() - 1), var32_13.getAge());
                if (!var28_5) break block3;
                v7 = var32_13.getAge();
                v8 = 3;
                if (!var28_5) ** GOTO lbl38
                if (v7 < v8) {
                    v9 = var35_15 / 3.0f;
                } else {
                    v7 = var32_13.getAge();
                    v8 = 25;
lbl38:
                    // 2 sources

                    v9 = v7 > v8 ? 1.0f - (var35_15 - 25.0f) / 5.0f : 1.0f;
                }
                var36_16 = v9;
                var37_17 = 8.0f * var36_16;
                var38_18 = RenderUtil.INSTANCE.withAlpha(new Color(var32_13.getColor()), var36_16);
                var45_24 = var36_16 * 0.3f;
                var39_19 = RenderUtil.INSTANCE.withAlpha(new Color(var32_13.getColor()), var45_24);
                var40_20 = new MatrixStack();
                var40_20.push();
                v10 = RotationAxis.POSITIVE_X.rotationDegrees(var29_4.getPitch());
                Intrinsics.checkNotNullExpressionValue(v10, "rotationDegrees(...)");
                MatrixUtil.multiply(var40_20, (Quaternionfc)v10);
                v11 = RotationAxis.POSITIVE_Y.rotationDegrees(var29_4.getYaw() + 180.0f);
                Intrinsics.checkNotNullExpressionValue(v11, "rotationDegrees(...)");
                MatrixUtil.multiply(var40_20, (Quaternionfc)v11);
                var40_20.translate(var32_13.getPos().x - var29_4.getCameraPos().x, var32_13.getPos().y + 0.05000000074505806 - var29_4.getCameraPos().y, var32_13.getPos().z - var29_4.getCameraPos().z);
                v12 = RotationAxis.POSITIVE_Y.rotationDegrees(var33_14);
                Intrinsics.checkNotNullExpressionValue(v12, "rotationDegrees(...)");
                MatrixUtil.multiply(var40_20, (Quaternionfc)v12);
                v13 = RotationAxis.POSITIVE_X.rotationDegrees(-90.0f);
                Intrinsics.checkNotNullExpressionValue(v13, "rotationDegrees(...)");
                MatrixUtil.multiply(var40_20, (Quaternionfc)v13);
                var40_20.translate(-4.0, -4.0, 0.0);
                Intrinsics.checkNotNullExpressionValue(var40_20.peek().getPositionMatrix(), "pose(...)");
                var42_22 = ParticleTexture.CRACK;
                var30_11.vertex(var41_21, 0.0f, 8.0f, 0.0f).texture(var42_22.L(), var42_22.v()).color(var39_19);
                var30_11.vertex(var41_21, 8.0f, 8.0f, 0.0f).texture(var42_22.o(), var42_22.v()).color(var39_19);
                var30_11.vertex(var41_21, 8.0f, 0.0f, 0.0f).texture(var42_22.o(), var42_22.w()).color(var39_19);
                var30_11.vertex(var41_21, 0.0f, 0.0f, 0.0f).texture(var42_22.L(), var42_22.w()).color(var39_19);
                var40_20.translate(4.0, 4.0, 0.0);
                var43_23 = ParticleTexture.CIRCLE;
                var40_20.translate((double)(-var37_17) / 2.0, (double)(-var37_17) / 2.0, 0.0);
                var30_11.vertex(var41_21, 0.0f, var37_17, 0.0f).texture(var43_23.L(), var43_23.v()).color(var38_18);
                var30_11.vertex(var41_21, var37_17, var37_17, 0.0f).texture(var43_23.o(), var43_23.v()).color(var38_18);
                var30_11.vertex(var41_21, var37_17, 0.0f, 0.0f).texture(var43_23.o(), var43_23.w()).color(var38_18);
                var30_11.vertex(var41_21, 0.0f, 0.0f, 0.0f).texture(var43_23.L(), var43_23.w()).color(var38_18);
                var40_20.pop();
                if (var28_5) continue;
            }
            BatchedBuffer.draw$default(var12_3, var30_11, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlendDepthtest(), null, null, var1_1.getStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView())), 22, null);
            var1_1.getStack().pop();
        }
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        particles.removeIf(arg_0 -> BuffEffect.invokePredicate(BuffEffect::onTick$lambda$0, arg_0));
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block6: {
            int n;
            boolean bl;
            block7: {
                block8: {
                    block5: {
                        bl = BuffParticle.d();
                        n = receivePacket.getPacket() instanceof WorldEventS2CPacket;
                        if (!bl) break block5;
                        if (n == 0) break block6;
                        n = ((WorldEventS2CPacket)receivePacket.getPacket()).getEventId();
                    }
                    if (!bl) break block7;
                    if (n == 2007) break block8;
                    n = ((WorldEventS2CPacket)receivePacket.getPacket()).getEventId();
                    if (!bl) break block7;
                    if (n != 2002) break block6;
                }
                n = ((WorldEventS2CPacket)receivePacket.getPacket()).getData();
            }
            int n2 = n;
            float f = (float)(n2 >> 16 & 0xFF) / 255.0f;
            float f2 = (float)(n2 >> 8 & 0xFF) / 255.0f;
            float f3 = (float)(n2 >> 0 & 0xFF) / 255.0f;
            Color color = new Color(f, f2, f3);
            int n3 = color.getRGB();
            if (bl) {
                if (n3 == -13083194) {
                    return;
                }
                n3 = color.getRGB();
            }
            Y = n3;
        }
    }

    @Flow
    private final void onEntityRemove(EntityRemove entityRemove) {
        block3: {
            Entity entity;
            block2: {
                boolean bl = BuffParticle.d();
                entity = entityRemove.getEntity();
                if (!bl) break block2;
                if (!(entity instanceof SplashPotionEntity)) break block3;
                Entity entity2 = entityRemove.getEntity();
                entity = entity2;
                Intrinsics.checkNotNull(entity2, "null cannot be cast to non-null type net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion");
            }
            SplashPotionEntity splashPotionEntity = (SplashPotionEntity)entity;
            BlockPos blockPos = BlockPos.ofFloored(this.getPos(splashPotionEntity));
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            BlockPos blockPos2 = blockPos;
            Vec3d vec3d = blockPos2.toCenterPos().add(0.0, -0.5, 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            particles.add(new BuffParticle(vec3d, Y, null));
        }
    }

    private static final boolean onTick$lambda$0(BuffParticle buffParticle) {
        int n;
        boolean bl;
        block8: {
            boolean bl2 = BuffParticle.d();
            Intrinsics.checkNotNullParameter(buffParticle, "effect");
            bl = bl2;
            int n2 = buffParticle.getAge();
            if (bl) {
                if (n2 == 3) {
                    int n3;
                    block7: {
                        n = 25;
                        for (n3 = 0; n3 < n; ++n3) {
                            Vec3d vec3d = buffParticle.getPos().add(RandomUtil.a(-3.0, 3.0, false, null), 0.0, RandomUtil.a(-3.0, 3.0, false, null));
                            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
                            ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleTexture.FIREFLY, vec3d, 0.0f, 0.25f, 12, new Vec3d(Math.random() / 2.0 - 0.25, Math.random() / 4.0, Math.random() / 2.0 - 0.25), new Color(buffParticle.getColor()), 0.0f, 0.0f, false, 896, null));
                            if (bl) {
                                if (bl) continue;
                            }
                            break block7;
                        }
                        n = 5;
                    }
                    n3 = 0;
                    while (n3 < n) {
                        int n4 = n3++;
                        Vec3d vec3d = buffParticle.getPos().add(0.0, -0.3, 0.0);
                        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
                        DefaultConstructorMarker defaultConstructorMarker = null;
                        int n5 = 896;
                        boolean bl3 = false;
                        float f = 0.0f;
                        float f2 = 0.0f;
                        Color color = RenderUtil.INSTANCE.withAlpha(new Color(buffParticle.getColor()), 0.5f);
                        Vec3d vec3d2 = new Vec3d(0.0, (double)n4 * 0.15, 0.0);
                        int n6 = 4;
                        float f3 = 6.0f;
                        ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleTexture.FIREFLY, vec3d, 0.0f, f3, n6, vec3d2, color, f2, f, bl3, n5, defaultConstructorMarker));
                        if (bl) {
                            if (bl) continue;
                        }
                        break block8;
                    }
                }
                n2 = buffParticle.getAge();
            }
            n = n2;
            buffParticle.setAge(n + 1);
        }
        int n7 = n;
        if (bl) {
            n7 = n7 > 30 ? 1 : 0;
        }
        return n7 != 0;
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
                        BuffEffect.a = ZkmKeys.a(1935642746144268048L, 7250887553497893412L, MethodHandles.lookup().lookupClass()).a(133675555345181L);
                        var20 = BuffEffect.a ^ 2705839345856L;
                        BuffEffect.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "~\u00de\u009d\u00b5\u0080\u00b9\u0002\u00bb\u00f1k\u00a7\u00a3\u00ab\u00af\u008buE\u00ae*\u00ee\u00ed\u00ae\u00adV&\u00f3N\u00a6\u00c0\u00c5C\u001dduM_Z\u00dd\u001f\u00ef jY\u00c4\u0085RT\u00e40\u0098[\u00f1X\u00ec\u00f5s\"\u00a2~:\u00bb\u00ab\"\u00ec\u001ad-bu\u00fb}B\u00ef(\u0087\u0088/80/|}\u0003n\u00a3C\u00e9\u000fV@\u0090\u001e\u00cf^\u00a6\u00f1\u00e5\u00e1Px\u0014\u00fc\u009e\u00ccM\u00e8Y\u0005j4\u0018\u00fb\u00c0s \u00db`Sl\u00d1\u0099=\u001e%\u00b2\u0092\u00c1\u0089\u0082\u00da)8bGU-\u00ef\u00d2\u00f3\u00a0N1\u00d2\u00de(mU \u00e2\b\u00c1\u00d0\u00fd\u000eI\u001a\u00beQ\u009d\rW\u00dd\u0095~\u000f\u00a3W\u00d2\u00fe\u00d9\u00b1\u00c6)\u00c9\u00c7\u0098aTSV\u0018\u0002\u00af\u00fd \u00ab\nn\u00da\u00d6,1\u00e2\u00fal\u00bd\u00a6CQm\u0088\u009f\u0015\u00de\u00ca(\u00be5\u00a1\u0004\u00ad\u00f3\u008bRw\u00c9\u0013\u0085\u00f0\u00b4|U8\u0086\u00a5p\u00d9n\u00bb\u00d1:T\u0018\u00f2\u008aJ'\u00a0\u0005\u00a4^\u00d5\u0086\u00fdT<\u0018,o\u00bc\u0002\u0090\u00811\bQ<\u00b8\u0006 \u00beb\u0001\u00db\u00c9\u00ce\"u\u0018R\u008a(\u00ce\u00ce\u00bc\u00ec\u00bb\r\u00ca\u0094\u00bf\u0094\u00a9kV\u009e\u0093\u00a5\u00c7\u00cbr\u00e4\u008b\u009a\u00f1\u0010\u0002\u00b3n{\u0086\u009d\u00f0\u00c8W\u00c8\u00ae \u0084\u00b8\u00aa\u008b\u0010K\u00d0\u0016-\u0086\u00c29\u0094\u00e9\t\u00f1a\u00a9\u001a;\u00f7";
                        var17_6 = 329;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = BuffEffect.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "bp\u00d9!0\u001b\u009a\u001a\u00cf?\r@\u00cb\u00b7\u001e\u00fcc\u0092ec\u00eb\u00ae\u0084\u00b1} \u00ec\u00d4\f0Pa\u0084\u00fe\u00ff\u0015\u00bc\u00be4:\u0088}\r\u0080\u00d7\u0092n\u00e06\u0013\u00b3L(mUaz)\u00fb\u0001\u00f0\u009cIE\u000e>2\u00ffz\u00ea\u00ec\u00ea\u00a6\u00e5\u00a6[\u00c5\u00d1\u00a3nI}o\u00dc\f\u001b\u00d5\u00ae\u00c4\u00f4N\u00e2\u00f5\u00cc\u00ec2\u00e8\u0016j\u0010{\u00ab\u00f7\u00d0T\u00c1\u00a9\u00db\u0004U\u00d9\u001f\u0094\t\u00fbt\u0084\u00e7\n\u001c\u0092\u00fb\\\u00ec lq\u00e2\u00f50\u0080,\u0013@m\u0011-T4Jq6R\u0010\u00d8\u0095k\u0094\u00b9q\u00ec\u00e4\u00c5\u00de\u00bc47n\u00a7#\u0097\u00c92\u00e9l5\u00bc\u0018\u0007\u00b8\u0081N\u008cy\u008b9\u000bX\u00d3\u000b\u00f8\u00b9\u00f8V \u00d4S\u00dd\u00ab\u00ddn{\u0097\u009d\u00b7%\u0096\u0010\u0096f8C{$\u00fb\u00bc\u00e6w\u00fe\u0097\u00a5n\u00db^t\u00b4\u0084";
                            var17_6 = 217;
                            var14_7 = 184;
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
                            var18_3[var16_4++] = BuffEffect.zkmDecodeUtf8(var19_9).intern();
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
                BuffEffect.b = var18_3;
                BuffEffect.c = new String[12];
                BuffEffect.g = new HashMap<K, V>(13);
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
                var6_12 = new long[14];
                var3_13 = 0;
                var4_14 = "\u0003n\u00ce\u00df\u00d2\u0088\u0093\u0090\u0087\u0087\u00c1\u00b1bj\u00c4\u00ba-\u0005B\u00faBA\u00a1\u00e3p.-\u00e5\u0011\u0018A\u00dbj\u00e9\r\u00e2\u00e0\u00bcw\\8\u00ed\u0001\u00cb\u00fdQl\u0087\u000fZ\u00ee&\u008cH:O\u000e@\u00a5mAR\u00c6\u00cbB\u00dc\u00ad4\u00c7\u0007\u00d1\u00d8`^\u0005\u00dd\u00ff\u009dk\u009fI2\u00bc\u00cc\u00e5h\u009d\u00ab!\u00b7\u00cbS/(\u0003K";
                var5_15 = 96;
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
                    var4_14 = "\u0089\u00cf\u00fc\u0014<[\u00c7d\u001e\u0090\u00d9\u00b8\u0015\u00c6\u009b\u00ac";
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
        BuffEffect.e = var6_12;
        BuffEffect.f = new Integer[14];
        BuffEffect.INSTANCE = new BuffEffect();
        BuffEffect.particles = new ArrayList<E>();
        BuffEffect.Y = -1;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4A65;
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
                throw new RuntimeException("su/catlean/kp", exception);
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
            BuffEffect.c[n2] = BuffEffect.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BuffEffect.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x48CE;
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
                throw new RuntimeException("su/catlean/kp", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BuffEffect.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BuffEffect.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BuffEffect.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BuffEffect.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

