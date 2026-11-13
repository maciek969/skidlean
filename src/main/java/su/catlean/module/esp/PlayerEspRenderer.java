/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Quaternionfc
 *  org.lwjgl.opengl.GL32C
 */
package su.catlean.module.esp;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.lwjgl.opengl.GL32C;
import su.catlean.module.world.FreeCam;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class PlayerEspRenderer {
    @NotNull
    public static final PlayerEspRenderer INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private PlayerEspRenderer() {
    }

    public final void renderSkeletons(@NotNull MatrixStack matrixStack, @NotNull Color color, long l, @NotNull Color color2, boolean bl) {
        long l2 = (l = a ^ l) ^ 0x7AA6C2D9B43CL;
        Intrinsics.checkNotNullParameter(matrixStack, "stack");
        Intrinsics.checkNotNullParameter(color, "lineColor");
        Intrinsics.checkNotNullParameter(color2, "friendLineColor");
        Iterable<Entity> iterable = Globals.getWorld().getEntities();
        Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
        Iterable<Entity> iterable2 = iterable;
        Object object = iterable2;
        Iterable iterable3 = new ArrayList();
        Iterator<Entity> iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            Entity entity = iterator2.next();
            if (!(entity instanceof PlayerEntity)) continue;
            iterable3.add(entity);
        }
        List list = (List)iterable3;
        if (list.isEmpty()) {
            return;
        }
        float f = RenderUtil.INSTANCE.getTickDelta();
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        boolean bl2 = true;
        int n = 9 * list.size();
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n, bl2);
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        object = camera;
        iterable3 = list;
        for (Entity entity : iterable3) {
            float f2;
            PlayerEntity playerEntity = (PlayerEntity)entity;
            if (Globals.getMc().options.getPerspective() == Perspective.FIRST_PERSON && !FreeCam.INSTANCE.getEnabled() && Intrinsics.areEqual(Globals.getMc().player, playerEntity)) continue;
            matrixStack.push();
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-((Camera)object).getYaw() + 180.0f));
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-((Camera)object).getPitch()));
            Color color3 = Friends.getIsHomie(playerEntity) ? color2 : color;
            Vec3d vec3d = RenderState.getInterpolatedPos$default(RenderState.INSTANCE, playerEntity, 0.0f, null);
            EntityRenderer<?, Entity> entityRenderer = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity);
            Intrinsics.checkNotNull(entityRenderer, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.player.AvatarRenderer<in @[EnhancedNullability] net.minecraft.world.entity.player.Player>");
            Object m = ((PlayerEntityRenderer)entityRenderer).getModel();
            Intrinsics.checkNotNullExpressionValue(m, "getModel(...)");
            PlayerEntityModel playerEntityModel = (PlayerEntityModel)m;
            Entity entity2 = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity).getAndUpdateRenderState((Entity)playerEntity, RenderState.INSTANCE.getTickDelta());
            Intrinsics.checkNotNull(entity2, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.state.AvatarRenderState");
            PlayerEntityRenderState playerEntityRenderState = (PlayerEntityRenderState)((Object)entity2);
            if (Intrinsics.areEqual(playerEntity, Globals.getPlayer()) && RotationManager.INSTANCE.H() != null && RotationManager.INSTANCE.c() != null) {
                Rotation rotation = RotationManager.INSTANCE.H();
                Intrinsics.checkNotNull(rotation);
                float f3 = rotation.getYaw();
                Rotation rotation2 = RotationManager.INSTANCE.c();
                Intrinsics.checkNotNull(rotation2);
                f2 = MathHelper.wrapDegrees(RenderUtil.INSTANCE.lerp(f3, rotation2.getYaw(), RenderState.INSTANCE.getTickDelta()));
            } else {
                f2 = MathHelper.lerpAngleDegrees(f, playerEntity.lastBodyYaw, playerEntity.bodyYaw);
            }
            float f4 = f2;
            float f5 = Intrinsics.areEqual(playerEntity, Globals.getPlayer()) ? MovementUtil.INSTANCE.getLastPitch() : playerEntity.getPitch(f);
            playerEntityModel.setAngles(playerEntityRenderState);
            boolean bl3 = playerEntity.isInSwimmingPose();
            boolean bl4 = playerEntity.isSneaking();
            boolean bl5 = playerEntity.isGliding();
            matrixStack.translate(vec3d.x, vec3d.y, vec3d.z);
            if (bl3) {
                matrixStack.translate(0.0f, 0.35f, 0.0f);
            }
            matrixStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_Y.rotationDegrees(f4 + 180.0f));
            if (bl3 || bl5) {
                matrixStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotationDegrees(80.0f + f5));
            }
            if (bl3) {
                matrixStack.translate(0.0f, -0.95f, 0.0f);
            }
            Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
            BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(matrix4f, 0.0f, bl4 ? 0.6f : 0.7f, bl4 ? 0.23f : 0.0f).color(color3);
            Matrix4f matrix4f2 = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f2, "pose(...)");
            BatchedBuffer batchedBuffer3 = batchedBuffer2.vertex(matrix4f2, 0.0f, bl4 ? 1.05f : 1.4f, 0.0f).color(color3);
            Matrix4f matrix4f3 = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f3, "pose(...)");
            BatchedBuffer batchedBuffer4 = batchedBuffer3.vertex(matrix4f3, -0.37f, bl4 ? 1.05f : 1.35f, 0.0f).color(color3);
            Matrix4f matrix4f4 = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f4, "pose(...)");
            BatchedBuffer batchedBuffer5 = batchedBuffer4.vertex(matrix4f4, 0.37f, bl4 ? 1.05f : 1.35f, 0.0f).color(color3);
            Matrix4f matrix4f5 = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f5, "pose(...)");
            BatchedBuffer batchedBuffer6 = batchedBuffer5.vertex(matrix4f5, -0.15f, bl4 ? 0.6f : 0.7f, bl4 ? 0.23f : 0.0f).color(color3);
            Matrix4f matrix4f6 = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f6, "pose(...)");
            batchedBuffer6.vertex(matrix4f6, 0.15f, bl4 ? 0.6f : 0.7f, bl4 ? 0.23f : 0.0f).color(color3);
            matrixStack.push();
            matrixStack.translate(0.0f, bl4 ? 1.05f : 1.4f, 0.0f);
            ModelPart modelPart = playerEntityModel.head;
            Intrinsics.checkNotNullExpressionValue(modelPart, "head");
            INSTANCE.drawBone(modelPart, 0.3f, matrixStack, color3, batchedBuffer);
            if (bl) {
                matrixStack.push();
                matrixStack.translate(0.0f, bl4 ? 0.5f : 0.7f, 0.0f);
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotation(playerEntityModel.head.roll));
                matrixStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_Y.rotation(playerEntityModel.head.yaw));
                matrixStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotation(1.1f));
                Matrix4f matrix4f7 = matrixStack.peek().getPositionMatrix();
                Intrinsics.checkNotNullExpressionValue(matrix4f7, "pose(...)");
                BatchedBuffer batchedBuffer7 = batchedBuffer.vertex(matrix4f7, 0.0f, 0.0f, 0.0f).color(color3);
                Matrix4f matrix4f8 = matrixStack.peek().getPositionMatrix();
                Intrinsics.checkNotNullExpressionValue(matrix4f8, "pose(...)");
                batchedBuffer7.vertex(matrix4f8, 0.0f, 0.3f, 0.0f).color(color3);
                matrixStack.pop();
            }
            matrixStack.push();
            matrixStack.translate(0.15f, bl4 ? 0.6f : 0.7f, bl4 ? 0.23f : 0.0f);
            ModelPart modelPart2 = playerEntityModel.rightLeg;
            Intrinsics.checkNotNullExpressionValue(modelPart2, "rightLeg");
            INSTANCE.drawBone(modelPart2, -0.6f, matrixStack, color3, batchedBuffer);
            matrixStack.push();
            matrixStack.translate(-0.15f, bl4 ? 0.6f : 0.7f, bl4 ? 0.23f : 0.0f);
            ModelPart modelPart3 = playerEntityModel.leftLeg;
            Intrinsics.checkNotNullExpressionValue(modelPart3, "leftLeg");
            INSTANCE.drawBone(modelPart3, -0.6f, matrixStack, color3, batchedBuffer);
            matrixStack.push();
            matrixStack.translate(0.37f, bl4 ? 1.05f : 1.35f, 0.0f);
            ModelPart modelPart4 = playerEntityModel.rightArm;
            Intrinsics.checkNotNullExpressionValue(modelPart4, "rightArm");
            INSTANCE.drawBone(modelPart4, -0.55f, matrixStack, color3, batchedBuffer);
            matrixStack.push();
            matrixStack.translate(-0.37f, bl4 ? 1.05f : 1.35f, 0.0f);
            ModelPart modelPart5 = playerEntityModel.leftArm;
            Intrinsics.checkNotNullExpressionValue(modelPart5, "leftArm");
            INSTANCE.drawBone(modelPart5, -0.55f, matrixStack, color3, batchedBuffer);
            if (bl3) {
                matrixStack.translate(0.0f, 0.95f, 0.0f);
            }
            if (bl3 || bl5) {
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(90.0f + f5));
            }
            if (bl3) {
                matrixStack.translate(0.0f, -0.35f, 0.0f);
            }
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(f4 + 180.0f));
            matrixStack.translate(-vec3d.x, -vec3d.y, -vec3d.z);
            matrixStack.pop();
        }
        GL32C.glEnable((int)2848);
        GL32C.glHint((int)3154, (int)4354);
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, matrixStack.peek().getPositionMatrix(), null, null, 54, null);
        GL32C.glDisable((int)2848);
    }

    private final void drawBone(ModelPart modelPart, float f, MatrixStack matrixStack, Color color, BatchedBuffer batchedBuffer) {
        this.applyPartTransform(matrixStack, modelPart);
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
        BatchedBuffer batchedBuffer2 = batchedBuffer.vertex(matrix4f, 0.0f, 0.0f, 0.0f).color(color);
        Matrix4f matrix4f2 = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f2, "pose(...)");
        batchedBuffer2.vertex(matrix4f2, 0.0f, f, 0.0f).color(color);
        matrixStack.pop();
    }

    private final void applyPartTransform(MatrixStack matrixStack, ModelPart modelPart) {
        block2: {
            if (!(modelPart.roll == 0.0f)) {
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotation(modelPart.roll));
            }
            if (!(modelPart.yaw == 0.0f)) {
                matrixStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_Y.rotation(modelPart.yaw));
            }
            if (modelPart.pitch == 0.0f) break block2;
            matrixStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotation(modelPart.pitch));
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PlayerEspRenderer.a = ZkmKeys.a(3667303866055336807L, 2436327542982929003L, MethodHandles.lookup().lookupClass()).a(92525562334414L);
                        PlayerEspRenderer.d = new HashMap<K, V>(13);
                        var11 = PlayerEspRenderer.a ^ 140611028640746L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[16];
                        var18_4 = 0;
                        var17_5 = "\u0006\u0091\u00ec\u00aa\u00e1\u000e\u00b8\u00abh+\u00d2/-\u00f0\u0015\u00e7\u00d7/h\u001d\u00ac?E=\u0006\u0015#k\u00b0@/\nt\u00be\u00c9<\u0088E\u0014\u00d4\u00ae\u00b9\u0000\u00bd\u00a8\u00d1\u00ba\u00dd)r\u00c0\u00fd;\u0088\u0089\u00b9\u00ad;\u008a\u00c5\u007f\u00c4\u009d\u0002\u00af\u0081\u0001\u0086+4F\u00869\u00a5\u0011U\u00ab\u00d1\u00bc\u00c2\u00f5j1\u00a7P\u00e5\u00e7\u0003&\u00ab\u00d6\u00c7\u0006z\u00f1\u00eey\u009c4\u00e0[\u00cf\u00a4L\u00b53\u00e4@\u0014\u0089\u001c\u0011\n\u00d3\u00b9\u0089\u00cb\u0096\u0010%\u0085\u00a4\f\u00b6\u0082\u008f\u00a3\u0012oA\u00ab\u00da\u0000\u00c4\u00be\u0085\u00fb!\u00a3?Q\u00eb}\u0014\u001a\u00bbz0-\u00fc\u00a0\u00b9\u00dd\u008b\u00ab\u00b07}8\u00ccek\u00a6\u0084\u00c8\u0090F\\\u001a%h\u00d9Y\u00ff4\u0084\u009b\u00b7\u009d\u0005\u00ae\u00d8\u00d8\u00cd\u00a3\u0014\u00b9h\u0093D,\u0016#\u00d1\u001d\u0099r\u0000>\u00dc)\u00f0\u00f5\u00eb\u00b1=4\u0096\u001f\u00d8\f\u00a7\u00c7\u00ce0\u0096<\u00c0c\u00c5O\u0014\u00f1\u007f\u00b8Y\u00d8\u0083\u00bf1?\u00f0\u0092\u00da\u0098\u0083\u001dbbv\u0003&{\u00c1\u0089\u00b3\u00e4\tXI\u00b4\u00a6I 0! Z\u0006\u000b\u00fe\\\u000e\u00d7\u0096\u00fc\u00dd\u00d9\u009e\u00b4\u00ee\u0015\u00b7\u00a4!r\u00baOi88_\u009a\u00c2\b\u00c0\u00df\u001b\u00cd \u00078\u00c3A5\\\u00ea\u00d70k\u00f8\u001a@\u00ad\u00d8\u00e6\u00b3\u0005%\u00dbx@\u00cf\u0006L\u00f1-@\u00f7\n\u00e0g t\u0094jg|\u00dd\u00e5%J\u00b2\u00e5\u0095\u0011\u00af\u00b6\u0084\u00f4JFA\u00e5O\u00bbTk\nk\u0002\u0007\u00c9\u0090\u00d7\u0018\u0099\u00c4\u00c8\u00a3\u00f4\u00ffN\u00d6E\u001d\u0005\u00b9\u0016\u00aa\u0097\u0096\u009fZC0\u00c4\u00b8\u00c3\u0097 A\u00d5E\u001e\u0018\u00e1\u00b4\u000eUt\u001e\u009d\u00d53\u00e0PT@/>\u0089\u00d2\u00db\u00c9\u00e0\u00ba\u00f2\r\u00b8}\u00fa\u00b4\u0010\u001c\t7+\u0013\u00890\u00ca\u00ee\u0012\u00b7\u009bA|\u00fa\u00a08=\u00aci\u00ef\u00e1rS\u008a\u00e3\t\u00b0\u00ad\n\u00db\u00bc\u008d\u00a6yr\u0002i\u00ed\u0098Rr\u00b5>\u00e3\u00d9\u00dc\u009a^v\u009c\u00e6c\u00a6\u00be\u00e9RS\u00d1\u0087\"\u0006\u00ca\u00ca\u0080\u00cb\u0088\u001a\u00b2\u00ef\u00cc\u00c0\u00be\u0010\u001e3\u00d5\u0080gF\u00e3\u0083\u00d8R!R\u00c5hs\u00ff\u0018^\u00e3\u0085f\u008a\u00f5\u0016\r\u00cdU\u00b4\u00f3vd:\u000e\u0014z\u00ba \u001c11k\u0018\u001e\u00c5\u0085\u0007\u00a9(\u008fl\u00b9\u0091\r(%\u00f1\u00c3=\u00d3>\u00cdK\u007f\u00a2t\u00f9(G\u0087\u00ac\u00a7\u0007\u00b7\u00a5R=\u00a7VL0\u00a7e\\\u008e\u00f1\rE\u00e4~`n\u00f1&\u00bf\"\u00ab\u0011g\u00b4\u0094\u00f6\u00c6y\u0086\u0013\u00ecy\u0010\u001d\u0007\u00da\u00d7\u00a4YZ(\u009e\u009a[\u00cfn\u00cc=[ \u0097$vX\u00f0A\u00e1\u0006\u00a8\u00a6\u00b0D7\u00e9n\u000f\f\u000bih\u00b0\u00e5R\u000e\u0006LK\u00bf]\u0089\u00a4\u00a0";
                        var19_6 = 645;
                        var16_7 = 256;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = PlayerEspRenderer.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00c3\u009b\u0088\u0085\u00d6\u0010mo\u001cR\u00fe\u0012\u00c0\u00f3\u00d0\u00b6\u0098NQ\u00b2,\u00e7Jca\u001d\u0004N\u00f4=\u00f5\u00ddF0<\u0012\u00adj\u00e2\u008f\t\u009dc\u00b2\u0088\u009dx_\u00e6\u001c\u001b\u00c3\u00be\u00d4\u00d9\u00f9\u0093\u00bbOB0\u00fa\u00fe\u00d78\u00f0\u00eb%\u00fb\u00df\u001f\u00a0\u008e\u001eL\u0012HT\u0016(\u0004\u00cc\u00edU54\u0095j\u0007Vk\u00e3\u0019\u00a4\u0096a\u00ce\u00fe\u0092\u00dc\u00a7\u00d5\u0094\bI\u00c8\u00b3\u00f8\u0084\u0007n\u0011\u00fd\u00bdd\u00c3\u00bc\u00f6\u00b7\u00be\u0080\u00f4I\u00d6icS\u00da\u00e6\u0098\u0010\u00c5\u00f2\u008b`\u00b5\u00c6\u00ec\u00d0\u00ae~v\u00e4\u001b\u00b3Syx\u00c3\u008e\u00ed\u00cfB\u00ca4F\u00aa+n\u0096\u008b\u009c\u0015B\u00e4;\u0006\u0003]";
                            var19_6 = 169;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = PlayerEspRenderer.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                PlayerEspRenderer.b = var20_3;
                PlayerEspRenderer.c = new String[16];
                PlayerEspRenderer.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "\u00ed\f\u0003\u0017g\u00e5@\u00022\u00b7\u00da}\u0085\u00dc\\\u009cU\u001d\u00d3F\u00ae\tI\u00c0@\u00d0o(\u00b0\u007f\u00c7\u00bf\u00ac\u00cb\u0082C\u00eeV\u0086\u00b59\u0095\u0019V\u00ae\u00ec&\u00b5,\u00c7\u008a\u00cf\u00ab\u001e3=C\u00ef\u00e8\u000e\u0096\u00cc\u00e6\u0089";
                var5_15 = 64;
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
                    var4_14 = "\u0083\u0099\u009f'\u00f2\u00f9X\u0099d\r^\u00d7\u0092\b\u00e6\u00c0";
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
        PlayerEspRenderer.e = var6_12;
        PlayerEspRenderer.f = new Integer[10];
        PlayerEspRenderer.INSTANCE = new PlayerEspRenderer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6E61;
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
                throw new RuntimeException("su/catlean/lt", exception);
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
            PlayerEspRenderer.c[n2] = PlayerEspRenderer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerEspRenderer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/lt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x94D;
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
                throw new RuntimeException("su/catlean/lt", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PlayerEspRenderer.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PlayerEspRenderer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/lt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerEspRenderer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PlayerEspRenderer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

