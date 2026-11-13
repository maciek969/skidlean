/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ModelPartAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.BodyPart;
import su.catlean.module.cosmetic.FlyingBodyPart;
import su.catlean.render.BatchedBuffer;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.sound.Sounds;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Dismemberment
extends Module {
    @NotNull
    public static final Dismemberment INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting sound$delegate;
    @NotNull
    private static final IntSetting lifeTime$delegate;
    @NotNull
    private static final List parts;
    @NotNull
    private static final List processedPlayers;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private Dismemberment() {
        super("dismemberment", Categories.getCosmetic(), null, null);
    }

    public final boolean getSound() {
        return (Boolean)sound$delegate.getValue(this, delegatedProperties[0]);
    }

    public final int getLifeTime() {
        return ((Number)lifeTime$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    @NotNull
    public final List getParts() {
        return parts;
    }

    @NotNull
    public final List getProcessedPlayers() {
        return processedPlayers;
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().player == null) {
            return;
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = a ^ 0xAE9C41DE2FDL;
        long l2 = l ^ 0x3D9DDE6CAAC8L;
        if (parts.isEmpty()) {
            return;
        }
        Iterable iterable = parts;
        for (Object t : iterable) {
            FlyingBodyPart flyingBodyPart = (FlyingBodyPart)t;
            flyingBodyPart.render(render3DEvent.getStack(), l2);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list, "players(...)");
        Iterable iterable = list;
        for (Object t : iterable) {
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
            if (processedPlayers.contains(abstractClientPlayerEntity)) continue;
            Intrinsics.checkNotNull(abstractClientPlayerEntity);
            processedPlayers.add(abstractClientPlayerEntity);
        }
        processedPlayers.removeIf(arg_0 -> Dismemberment.invokePredicate(Dismemberment::onPlayerUpdate$lambda$0, arg_0));
        parts.removeIf(arg_0 -> Dismemberment.invokePredicate2(Dismemberment::onPlayerUpdate$lambda$2, arg_0));
    }

    private final void spawnBodyParts(PlayerEntity playerEntity) {
        block6: {
            boolean bl;
            PlayerEntityModel playerEntityModel;
            double d;
            double d2;
            double d3;
            Identifier identifier;
            block5: {
                if (playerEntity instanceof ClientPlayerEntity || Globals.getPlayer().age < 20) {
                    return;
                }
                Intrinsics.checkNotNull(playerEntity, "null cannot be cast to non-null type net.minecraft.client.player.AbstractClientPlayer");
                Identifier identifier2 = ((AbstractClientPlayerEntity)playerEntity).getSkin().comp_1626().comp_3627();
                Intrinsics.checkNotNullExpressionValue(identifier2, "texturePath(...)");
                identifier = identifier2;
                d3 = ((AbstractClientPlayerEntity)playerEntity).getX();
                d2 = ((AbstractClientPlayerEntity)playerEntity).getY() + (double)1.45f;
                d = ((AbstractClientPlayerEntity)playerEntity).getZ();
                EntityRenderer<?, Entity> entityRenderer = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)Globals.getPlayer());
                Intrinsics.checkNotNull(entityRenderer, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.player.AvatarRenderer<in @[EnhancedNullability] net.minecraft.client.player.LocalPlayer>");
                PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer)entityRenderer;
                Object m = playerEntityRenderer.getModel();
                Intrinsics.checkNotNullExpressionValue(m, "getModel(...)");
                playerEntityModel = (PlayerEntityModel)m;
                Iterable iterable = parts;
                if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                    bl = false;
                } else {
                    for (Object t : iterable) {
                        FlyingBodyPart flyingBodyPart = (FlyingBodyPart)t;
                        if (!Intrinsics.areEqual(flyingBodyPart.getTexture(), identifier)) continue;
                        bl = true;
                        break block5;
                    }
                    bl = false;
                }
            }
            if (bl) {
                return;
            }
            parts.add(new FlyingBodyPart(identifier, playerEntityModel, BodyPart.HEAD, d3, d2, d));
            parts.add(new FlyingBodyPart(identifier, playerEntityModel, BodyPart.BODY, d3, d2, d));
            double d4 = d;
            double d5 = d2 - (double)0.15f;
            double d6 = d3 + (double)0.35f;
            BodyPart bodyPart = BodyPart.RIGHT_ARM;
            parts.add(new FlyingBodyPart(identifier, playerEntityModel, bodyPart, d6, d5, d4));
            double d7 = d;
            double d8 = d2 - (double)0.15f;
            double d9 = d3 - (double)0.35f;
            BodyPart bodyPart2 = BodyPart.LEFT_ARM;
            parts.add(new FlyingBodyPart(identifier, playerEntityModel, bodyPart2, d9, d8, d7));
            double d10 = d;
            double d11 = d2 - (double)0.7f;
            double d12 = d3 + (double)0.1f;
            BodyPart bodyPart3 = BodyPart.RIGHT_LEG;
            parts.add(new FlyingBodyPart(identifier, playerEntityModel, bodyPart3, d12, d11, d10));
            double d13 = d;
            double d14 = d2 - (double)0.7f;
            double d15 = d3 - (double)0.1f;
            BodyPart bodyPart4 = BodyPart.LEFT_LEG;
            parts.add(new FlyingBodyPart(identifier, playerEntityModel, bodyPart4, d15, d14, d13));
            if (!this.getSound() || !(Globals.getPlayer().squaredDistanceTo(d3, d2, d) < 49.0)) break block6;
            Sounds.INSTANCE.getLegoBreaking().playAt(d3, d2, d);
        }
    }

    public final void renderBodyPart(@NotNull MatrixStack matrixStack, long l, @NotNull PlayerEntityModel playerEntityModel, @NotNull BatchedBuffer batchedBuffer, double d, double d2, double d3, double d4, double d5, double d6, @NotNull BodyPart bodyPart, @NotNull Color color) {
        int n = (int)(((l = a ^ l) ^ 0x5B23CDA16589L) >>> 48);
        Intrinsics.checkNotNullParameter(matrixStack, "stack");
        Intrinsics.checkNotNullParameter(playerEntityModel, "model");
        Intrinsics.checkNotNullParameter(batchedBuffer, "polygon");
        Intrinsics.checkNotNullParameter((Object)bodyPart, "part");
        Intrinsics.checkNotNullParameter(color, "color");
        matrixStack.push();
        matrixStack.translate(d, d2, d3);
        matrixStack.scale(0.0555f, 0.0555f, 0.0555f);
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((float)d4));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)d5));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)d6));
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        ModelPart modelPart = (ModelPart)bodyPart.T().invoke(playerEntityModel);
        Intrinsics.checkNotNull(matrix4f);
        INSTANCE.renderModelPart((short)n, modelPart, batchedBuffer, color, matrix4f);
        INSTANCE.renderModelPart((short)n, (ModelPart)bodyPart.s().invoke(playerEntityModel), batchedBuffer, color, matrix4f);
        matrixStack.pop();
    }

    public final void renderModelPart(short s, @NotNull ModelPart modelPart, @NotNull BatchedBuffer batchedBuffer, @NotNull Color color, @NotNull Matrix4f matrix4f) {
        block4: {
            int n = 0;
            int n2 = 0;
            long l = ((long)s << 48 | (long)n << 32 >>> 16 | (long)n2 << 48 >>> 48) ^ a;
            Intrinsics.checkNotNullParameter(modelPart, (String)((Object)Dismemberment.zkmBootstrap("p", (int)384, (long)(0x252A096B16FADC7L ^ l))));
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5354021052261595954L, (long)l);
            Intrinsics.checkNotNullParameter(batchedBuffer, (String)((Object)Dismemberment.zkmBootstrap("p", (int)12226, (long)(0x7C4CC9B3AE998399L ^ l))));
            Intrinsics.checkNotNullParameter(color, (String)((Object)Dismemberment.zkmBootstrap("p", (int)32141, (long)(0x2B2372C740DED1CDL ^ l))));
            Intrinsics.checkNotNullParameter(matrix4f, (String)((Object)Dismemberment.zkmBootstrap("p", (int)23875, (long)(0x58CB6668D5A97102L ^ l))));
            CallSite callSite2 = callSite;
            List<ModelPart.Cuboid> list = ((ModelPartAccessor)((Object)modelPart)).getCuboidsList();
            Intrinsics.checkNotNullExpressionValue(list, (String)((Object)Dismemberment.zkmBootstrap("p", (int)6573, (long)(0x760E10F34A73B5EEL ^ l))));
            Iterable iterable = list;
            for (Object t : iterable) {
                CallSite callSite3;
                block5: {
                    ModelPart.Cuboid cuboid = (ModelPart.Cuboid)t;
                    Intrinsics.checkNotNullExpressionValue(cuboid.sides, (String)((Object)Dismemberment.zkmBootstrap("p", (int)14663, (long)(0x61ECF06251CE1505L ^ l))));
                    Object[] objectArray = cuboid.sides;
                    int n3 = 0;
                    if (callSite2 != null) break block4;
                    int n4 = objectArray.length;
                    block1: while (n3 < n4) {
                        Object object = objectArray[n3];
                        ModelPart.Quad quad = (ModelPart.Quad)object;
                        ModelPart.Vertex[] vertexArray = quad.comp_3184();
                        batchedBuffer.vertex(matrix4f, vertexArray[0].comp_4804(), vertexArray[0].comp_4805(), vertexArray[0].comp_4806()).texture(vertexArray[0].comp_3187(), vertexArray[0].comp_3188()).color(color);
                        batchedBuffer.vertex(matrix4f, vertexArray[1].comp_4804(), vertexArray[1].comp_4805(), vertexArray[1].comp_4806()).texture(vertexArray[1].comp_3187(), vertexArray[1].comp_3188()).color(color);
                        batchedBuffer.vertex(matrix4f, vertexArray[2].comp_4804(), vertexArray[2].comp_4805(), vertexArray[2].comp_4806()).texture(vertexArray[2].comp_3187(), vertexArray[2].comp_3188()).color(color);
                        batchedBuffer.vertex(matrix4f, vertexArray[3].comp_4804(), vertexArray[3].comp_4805(), vertexArray[3].comp_4806()).texture(vertexArray[3].comp_3187(), vertexArray[3].comp_3188()).color(color);
                        ++n3;
                        do {
                            callSite3 = callSite2;
                            if (s < 0) break block5;
                            if (callSite3 != null) break block1;
                            if (callSite2 == null) continue block1;
                        } while (s < 0);
                    }
                    callSite3 = callSite2;
                }
                if (callSite3 == null) continue;
            }
            if (s >= 0) {
                // empty if block
            }
        }
    }

    private static final boolean onPlayerUpdate$lambda$0(PlayerEntity playerEntity) {
        boolean bl;
        Intrinsics.checkNotNullParameter(playerEntity, "it");
        List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list, "players(...)");
        if (!CollectionsKt.contains((Iterable)list, playerEntity)) {
            INSTANCE.spawnBodyParts(playerEntity);
            bl = true;
        } else {
            bl = false;
        }
        return bl;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean onPlayerUpdate$lambda$2(FlyingBodyPart flyingBodyPart) {
        Intrinsics.checkNotNullParameter(flyingBodyPart, "it");
        return flyingBodyPart.update();
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
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
                        Dismemberment.a = ZkmKeys.a(6224377949498687200L, 7658337560409827342L, MethodHandles.lookup().lookupClass()).a(1835574540851L);
                        var20 = Dismemberment.a ^ 105534204672388L;
                        Dismemberment.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[26];
                        var16_4 = 0;
                        var15_5 = "\u00c6\u0000^\u0081\u008cX6\u00ba\u00db\u00c1]._-\u00d3%\u00a0G\u00a7\u00bbL\u00dc\u00de<\u00a6\u0094b\u00b1\u00a4\u00b0\u0000\u00a2\u0018n\u0088\u0096\u0087e@\u0091\u00be]oC\u00ca\u00fbY\u00d8\u00a4\\9Qrp\u008c\u00fee\u00ed\u0006=\u000e\u0085\u00ff\u008f\u00b0\u00e2r\u000e\u00de}\u0096\u0016\u0004\u00ffx=Uh\r\u001dz\u00eaU\u008a\u00e0\u00b0\u0019\u007f\u00da\u00c4\u0080\u00ff\u0083\u00e2\u00bb\u00a2\u00cb\u0007\u00d1\u0014\"\u0084I^[I\u0095\u0015]\u00e6\u0003\u00d0\u0099\u0010\u00b2\u00b6\u008a\u00f8\u00cc\u00cd\u0082q\u00a0\u000f.\u0000\u00e0\u00df\u00f0\u008f\u00fc\u00c1z7Ex\u0100\u00eb\u00a9\u0094[y\u0088I@\u00f0\u00a4\u00a0\u0010\u00b2\u0000\u001cr\u00c1Q*\u0091\u0010\u00d5\u0014`\u00a8\b\u000b\u00b3\fj9\u00d1\u00b0\u00eb\u000fQ\r\u00e2[.\u0085.\u00e3\u00bf\u00ab\u0000n\u0083;\u00cc\u001e\u0003\u0015\"\u0084\u00ff\u00e6U~'\u0005\u00a5t9~\u009e\u00a5L=Q\u009e\u009bw\u00e2&i\u00e6\u007f\u00dfN\u00a8Q\u00fc\u0095\u0093Ww\u0000y\u00ee\u00c7-\u00c1F\u00c6hR\n\u0014\u009f\u00b6\u001b\u00b5Uw\u00bffwW\u00f8\u00b2\u00c1=o\u00c2\u008c\u008f-\u00a1k7H\u0082\u0094t\u0087\u00c5\u000b\u00e7\u007f\u00d77\u00c0\u00e8\u00f6\u00fd\fNB\u0080\u00d3\u00b5/&\u00e7\u00a9n\u009b\u00ffD\u0095\u00bd9@\u00f1pR\u00bf`Q$\u00c8NF\u00dc\u00c51\u0099\u00d5\u0084\u0000\u00c1\u00de0\u009c\u00ea\u0081\u009d\u0019X\u00c8\u00af\u00d2\u00c6N \u00cd4\u00ae#\u00b5\u00f7y\u00b4I\u008d\u0011\u0083\u00b7\u00d8\"\u0091W\u00fd\u00fdg\u00b4\u008d)*]\u0015\u0096Ha\u001cK\u0095\u0087\t\u00c8\u00ee#\u00d7\u00e14\u009e\u0087s\u0018\u0096\u008c\u009f\u008e\u0019/Wbxq\u0018\u00c0a\u0011\u0010\u0001\u00b1\u0002\u00f2\u00e3\u00ac9\u001b<\u0083\u00c1 \u0089ST\u0084Jle\u0010_\u00ed7\u00d5f\u009b\u00df\u00bc\u0081\u0012`\f\u000e\u00ab\u00b2\u00001\\\u00df_\u00a7\u00f6\u00f1\u0087 \u00069\u00ad\u001f\u00dd5\u0006\u00fd\u00ff|$\u0093\u00ce\u0092P\u0010\u00b80\u009f\u0088L5\u00a6\u00ea\u00b4\u008a\u009a'~\u00d9\u0086H w\u00e0\u009f\u00c8\u00f4\u00bc\u00ce\u00aduv\u00d8k\u008d\u001a\u001e$\u00d2\u00dc\u00af\u00b5`0\u00beUC\u00f7?c\u009d\u00d8\u00c1^\u001058\u00af\u0089\u001d\u00d6\u0011\u0080z[w\u0087\u00e3*Yy\u0010\u00ba\u00ef\u00c1\u00aa+\u00b3\u00ed\u00e9\t\u0014\u0091\u008d\u00b9S%T\u0010E\u00c6Gk0[{\tEO\u0095A\u00ae\u00d2\u00f9\u00a7\u0018\fv9\u0091\u00fb\u00dd\u00d0#[c\b\u00f3\u00e7<\u00f8\u009c\u00ca\u00e8\u0084)Ed\u00e3\u0016\u0010|SU\u00a4\u00bd\u000b=\u0015\u0015a\u00b8\u009a9Q\u00e1p\u0010\u00d4J\u00bd\u0014\u0019\n\u0091b\u00ab\u00db\u00fcr\u00c1\u00bf\u00cbd\u0010$\u0088\u009d\u00d0\u0085(`\u00f7.1\u00ca/o5\u0005\u00b1\u0010\u001e\u00ec\n9\u00f2Zt\u00a8e\u00e7=\u0001\u00c6\u00b8Q\u00d7\u00108\u00e2*\f'w=Kk\t\u00de\u0098\u00a7] \u008f(\u001e\u00dfF\u0087\u0089\u00f3V\u0018\u00eb\u00b9\u0018\u0092\u00be\u0003\u0094Vz\u00ca\u00b6\u0081{\u00c1\u0014A\u00a69\u0001\u009c\u008d\u0014\u001b+\u00b1\u00abfa\u0016j\u0093( \u008f\u00b2\u00ca\u00a2\u009ee\u00aaF\u0085b\u00d9\u00d8U\u0011\u00c04\u0095j\u00f0v\u00d5\u00d5\u00fb\u00db[\u0010\u008d/\n]+4\u0010\"\u00f9\"\u00b1\u00c4\u00d1J)&\u0084Bf&\u0089\u0003\u0004\u0010\u00106A\n\u0017e\u0012u\u00d7\u00b6;5\u0095\u00dbC\u0086\u0010?\u0080~X\u0096\u00ba!\u008d\u0003.\u009f\u0019\u00fd]\u009ew g\u0010\u00fa\u00da+\u00d6cv\r\u00e6\u00a1K\u00df\u0017f4l\fp\t\u0010c%\u0082\u00a7\u00cfD\u00f9d\u00b8\u008d\u00ea \u009e\u00da.\u001b5\u00b9\u00ee/QDRW{\u00dcip\u00fe\u008a\u00f3\u00e76g\u0019\u0001\u00d2\u00ff\u00ea\u00fe\u00f4\u009e\u001f9\u0010;S>@\u00c6\u0002D\u0004\u0007\u0096d\u00c7]\\,\u00af\u0010)\u0099Y\u00b2(I\u009b\u00a0\u00c3>\u00a9\u00ae\u00cf7\u0097\u0000\u0018\u00bfR\u00e2\t\u00c8\u00bf\u0018\u00ee\f\u0000\u00ff\u00c9Ecb\u00ef\u0006$\u00c2B\u000fJ[j";
                        var17_6 = 903;
                        var14_7 = 136;
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
                            var18_3[var16_4++] = Dismemberment.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "V>\u0010\u00840\u001b\u00ac\u00f6\u00cd-\u0095\rr\u00d8\u000ex1\u00c1\u0093v\u0014\u00cd\u00d6s\u00dd]\u00e1A)\u00e2\u00cd\u0085\u00e3\u00f3\u001co[\u00f2\u00c3  a\u00a2b-\u001fP^\u00fa\u00ef]M(\u0012\u00a4\u00aaOb\u00dc\u00e3j\u0010:\u00d7\u00c9\u0003\u0093r\\\u00a7\u00f4+\u009f";
                            var17_6 = 73;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = Dismemberment.zkmDecodeUtf8(var19_9).intern();
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
                Dismemberment.b = var18_3;
                Dismemberment.c = new String[26];
                Dismemberment.g = new HashMap<K, V>(13);
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
                var4_14 = "\u0091\u00c5\u00ec\u001dT\u00b7\u00ad\r~\u00bf\u00e6\u00f5w\u00dev\u008c<W\u00c8\u00d4{\u00b2\u008a\u0097";
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
                    var4_14 = "\u00f3\u00a8\u000e\u001e\u009b^\u00ec\u00ec\u00d7Q\u00bd\bC\u00e0\u00016";
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
        Dismemberment.e = var6_12;
        Dismemberment.f = new Integer[5];
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Dismemberment.class, "sound", "getSound()Z", 0)), Reflection.property1(new PropertyReference1Impl(Dismemberment.class, "lifeTime", "getLifeTime()I", 0))};
        Dismemberment.delegatedProperties = var28_20;
        Dismemberment.INSTANCE = new Dismemberment();
        Dismemberment.sound$delegate = SettingFactory.booleanSetting$default((SettingContainer)Dismemberment.INSTANCE, "sound", true, null, null, 12, null);
        Dismemberment.lifeTime$delegate = SettingFactory.intSetting$default(Dismemberment.INSTANCE, "life-time", 10, new IntRange(3, 100), null, null, 24, null);
        Dismemberment.parts = new ArrayList<E>();
        Dismemberment.processedPlayers = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x67DE;
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
                throw new RuntimeException("su/catlean/kh", exception);
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
            Dismemberment.c[n2] = Dismemberment.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Dismemberment.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x42B5;
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
                throw new RuntimeException("su/catlean/kh", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Dismemberment.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Dismemberment.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Dismemberment.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Dismemberment.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

