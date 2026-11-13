/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.PopChamsMode;
import su.catlean.module.cosmetic.PopGhost;
import su.catlean.module.esp.LogoutSpots;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BoxRenderMode;
import su.catlean.render.ModelRenderer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.TexturedPolygonConsumer;
import su.catlean.render.Theme;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class PopChams
extends Module {
    @NotNull
    public static final PopChams INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final IntSetting alpha$delegate;
    @NotNull
    private static final FloatSetting ySpeed$delegate;
    @NotNull
    private static final FloatSetting aSpeed$delegate;
    @NotNull
    private static final CopyOnWriteArrayList ghosts;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private PopChams() {
        super("pop-chams", Categories.getCosmetic(), null, null);
    }

    private final PopChamsMode getMode() {
        return (PopChamsMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final int getAlpha() {
        return ((Number)alpha$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final float getYSpeed() {
        return ((Number)ySpeed$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final float getASpeed() {
        return ((Number)aSpeed$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        ghosts.removeIf(arg_0 -> PopChams.invokePredicate(PopChams::onPlayerUpdate$lambda$0, arg_0));
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l;
        long l2 = l = a ^ 0x7E0293511450L;
        long l3 = l2 ^ 0x49A5C3D1EE49L;
        int n = (int)(l3 >>> 32);
        int n2 = (int)(l3 << 32 >>> 48);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x697670793811L;
        if (this.getMode() == PopChamsMode.DEFAULT) {
            if (!((Collection)ghosts).isEmpty()) {
                PopGhost popGhost;
                VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
                Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
                DefaultConstructorMarker defaultConstructorMarker = null;
                int n4 = 4;
                boolean bl = false;
                int n5 = ghosts.size() * 72;
                VertexFormat vertexFormat2 = vertexFormat;
                BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n5, bl, n4, defaultConstructorMarker);
                Iterable iterable = ghosts;
                for (Object t : iterable) {
                    popGhost = (PopGhost)t;
                    float f = popGhost.getInterpolatedAlpha();
                    BatchedBuffer batchedBuffer2 = batchedBuffer;
                    BoxRenderMode boxRenderMode = BoxRenderMode.FILL;
                    Color color = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f);
                    Vec3d vec3d = popGhost.getInterpolatedPos();
                    PlayerEntityRenderState playerEntityRenderState = popGhost.getState();
                    PlayerEntityModel playerEntityModel = popGhost.getModel();
                    MatrixStack matrixStack = render3DEvent.getStack();
                    ModelRenderer.INSTANCE.h(matrixStack, playerEntityModel, playerEntityRenderState, vec3d, color, boxRenderMode, batchedBuffer2);
                }
                BatchedBuffer.draw$default(l4, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, render3DEvent.getStack().peek().getPositionMatrix(), null, null, 54, null);
                VertexFormat vertexFormat3 = VertexFormats.POSITION_COLOR;
                Intrinsics.checkNotNullExpressionValue(vertexFormat3, "POSITION_COLOR");
                boolean bl2 = true;
                n4 = ghosts.size() * 144;
                VertexFormat vertexFormat4 = vertexFormat3;
                batchedBuffer = new BatchedBuffer(vertexFormat4, n4, bl2);
                iterable = ghosts;
                for (Object t : iterable) {
                    popGhost = (PopGhost)t;
                    float f = popGhost.getInterpolatedAlpha() * 2.0f;
                    BatchedBuffer batchedBuffer3 = batchedBuffer;
                    BoxRenderMode boxRenderMode = BoxRenderMode.OUTLINE;
                    Color color = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f);
                    Vec3d vec3d = popGhost.getInterpolatedPos();
                    PlayerEntityRenderState playerEntityRenderState = popGhost.getState();
                    PlayerEntityModel playerEntityModel = popGhost.getModel();
                    MatrixStack matrixStack = render3DEvent.getStack();
                    ModelRenderer.INSTANCE.h(matrixStack, playerEntityModel, playerEntityRenderState, vec3d, color, boxRenderMode, batchedBuffer3);
                }
                BatchedBuffer.draw$default(l4, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, render3DEvent.getStack().peek().getPositionMatrix(), null, null, 54, null);
            }
        } else {
            Iterable iterable = ghosts;
            for (Object t : iterable) {
                PopGhost popGhost = (PopGhost)t;
                MatrixStack matrixStack = render3DEvent.getStack();
                Intrinsics.checkNotNull(popGhost);
                INSTANCE.renderGhost(matrixStack, n, (short)n2, popGhost, (char)n3);
            }
        }
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        Intrinsics.checkNotNullParameter(receivePacket, "event");
        if (Globals.getMc().getCameraEntity() == null || Globals.getMc().player == null || Globals.getMc().gameRenderer.getCamera() == null || Globals.getMc().world == null || Globals.getPlayer().age < 10) {
            return;
        }
        Packet<?> packet = receivePacket.getPacket();
        if (packet instanceof EntityStatusS2CPacket && ((EntityStatusS2CPacket)packet).getStatus() == 35 && Globals.getMc().getCameraEntity() != null && Globals.getMc().gameRenderer.getCamera() != null) {
            Object object = ((EntityStatusS2CPacket)packet).getEntity(Globals.getWorld());
            PlayerEntity playerEntity = object instanceof PlayerEntity ? (PlayerEntity)object : null;
            if (playerEntity == null) {
                return;
            }
            PlayerEntity playerEntity2 = playerEntity;
            if (Intrinsics.areEqual(playerEntity2, Globals.getMc().player)) {
                return;
            }
            EntityRenderer<?, Entity> entityRenderer = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity2);
            Intrinsics.checkNotNull(entityRenderer, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.player.AvatarRenderer<in @[EnhancedNullability] net.minecraft.world.entity.player.Player>");
            Object m = ((PlayerEntityRenderer)entityRenderer).getModel();
            Intrinsics.checkNotNullExpressionValue(m, "getModel(...)");
            PlayerEntityModel playerEntityModel = (PlayerEntityModel)m;
            Object object2 = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity2).getAndUpdateRenderState((Entity)playerEntity2, RenderState.INSTANCE.getTickDelta());
            Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.state.AvatarRenderState");
            object = (PlayerEntityRenderState)object2;
            object2 = new PlayerEntityRenderState();
            ((PlayerEntityRenderState)object2).field_53536 = ((PlayerEntityRenderState)object).field_53536;
            ((PlayerEntityRenderState)object2).field_53537 = ((PlayerEntityRenderState)object).field_53537;
            ((PlayerEntityRenderState)object2).field_53538 = ((PlayerEntityRenderState)object).field_53538;
            ((PlayerEntityRenderState)object2).x = ((PlayerEntityRenderState)object).x;
            ((PlayerEntityRenderState)object2).y = ((PlayerEntityRenderState)object).y;
            ((PlayerEntityRenderState)object2).z = ((PlayerEntityRenderState)object).z;
            ((PlayerEntityRenderState)object2).bodyYaw = ((PlayerEntityRenderState)object).bodyYaw;
            ((PlayerEntityRenderState)object2).relativeHeadYaw = ((PlayerEntityRenderState)object).relativeHeadYaw;
            ((PlayerEntityRenderState)object2).pitch = ((PlayerEntityRenderState)object).pitch;
            ((PlayerEntityRenderState)object2).handSwingProgress = ((PlayerEntityRenderState)object).handSwingProgress;
            ((PlayerEntityRenderState)object2).flyingRotation = ((PlayerEntityRenderState)object).flyingRotation;
            ((PlayerEntityRenderState)object2).age = ((PlayerEntityRenderState)object).age;
            ((PlayerEntityRenderState)object2).limbSwingAmplitude = ((PlayerEntityRenderState)object).limbSwingAmplitude;
            ((PlayerEntityRenderState)object2).limbAmplitudeInverse = ((PlayerEntityRenderState)object).limbAmplitudeInverse;
            ((PlayerEntityRenderState)object2).limbSwingAnimationProgress = ((PlayerEntityRenderState)object).limbSwingAnimationProgress;
            playerEntityModel.setAngles((PlayerEntityRenderState)object);
            Vec3d vec3d = this.getPos(playerEntity2);
            Identifier identifier = ((AbstractClientPlayerEntity)playerEntity2).getSkin().comp_1626().comp_3627();
            Intrinsics.checkNotNullExpressionValue(identifier, "texturePath(...)");
            Identifier identifier2 = identifier;
            ghosts.add(new PopGhost(playerEntityModel, (PlayerEntityRenderState)object2, vec3d, identifier2, this.getAlpha()));
        }
    }

    private final void renderGhost(MatrixStack matrixStack, int n, short s, PopGhost popGhost, char c) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)c << 48 >>> 48) ^ a;
        long l2 = l ^ 0x20391069AC4EL;
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(popGhost.getTexture()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n2 = 4;
        boolean bl = false;
        int n3 = 256;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n3, bl, n2, defaultConstructorMarker);
        float f = popGhost.getInterpolatedAlpha();
        TexturedPolygonConsumer texturedPolygonConsumer = new TexturedPolygonConsumer(batchedBuffer, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f));
        matrixStack.push();
        Vec3d vec3d = popGhost.getInterpolatedPos();
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue(vec3d2, "position(...)");
        Vec3d vec3d3 = vec3d2;
        double d = vec3d.x - vec3d3.getX();
        double d2 = vec3d.y - vec3d3.getY();
        double d3 = vec3d.z - vec3d3.getZ();
        matrixStack.translate((float)d, (float)d2, (float)d3);
        matrixStack.scale(popGhost.getState().baseScale, popGhost.getState().baseScale, popGhost.getState().baseScale);
        LogoutSpots.INSTANCE.applyModelRotation(popGhost.getState(), matrixStack, popGhost.getState().bodyYaw);
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
        matrixStack.translate(0.0f, -1.501f, 0.0f);
        popGhost.getModel().setAngles(popGhost.getState());
        popGhost.getModel().render(matrixStack, texturedPolygonConsumer, 0xF000F0, 0, Theme.INSTANCE.getPrimaryColor().getRGB());
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureCull(), null, null, new MatrixStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2)), 22, null);
        matrixStack.pop();
    }

    private static final boolean onPlayerUpdate$lambda$0(PopGhost popGhost) {
        return popGhost.update(INSTANCE.getASpeed(), INSTANCE.getYSpeed());
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
                        PopChams.a = ZkmKeys.a(6550658097735700130L, -3140550486208353437L, MethodHandles.lookup().lookupClass()).a(174716233842618L);
                        var20 = PopChams.a ^ 21038908510515L;
                        PopChams.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[24];
                        var16_4 = 0;
                        var15_5 = "NF\u00d4\u00dcx\u0084\u0005\u00d9\u00edV\\O\u00c7-\u00e3\u007f\u00ab\u00a7FH;xI\u009e\u00adYV\u00e5\u009f\u00b7\u00e2\u00e1\u00e8>\u009e\u00ce\u00c8_Kh\u00a9\u00df:\u00ec\u00e0\u00aa]x\u0099w\u00f3\u0017\u00ff\u000f\u00df\u00a1\u008a\u0002q\u00ff\u009d\u00bb\u00e8\u00c6Pr\u00ce\u00b6dhl\u00f4A\u00d5M\u001cO\u00b7\u0001\u000f\u00c5\u001e\u001a3\u00d8\u00ff\u00d4\u00b8\u001c(\u0001\u00f5\n4\u00a4>zH7\u00ea\u0094\u0016\u00f6\u00dbG?e\u00e7\u0004g!\u00b05\u00ca\u0088\u00a1I\u00bbf6\u00c9\u0000~\u001d\u00b8\u0081u\u00a1\u00f6\f\u00f5\u001dn\u0011~~\u0017%\u0097\u00f0F%\u0001\u00c1*x\u0093\u0096Z\u00fb\u00e3~\u0010\u00b08#\u00fdT\u0088\u00a7a\u00e3\u00a7G\u008b{\u00f49R E2\u001fq\t\u001f\u0092\u0084$\u00b8{\u00dfD\u00a8}q3\u001e\u00932s\u00f2Yuk|q\u00ee\u008c\u0092f\r\u00f8\u00ad\u00aa\u0087\u00e6\u00df\u00ea\u00db*v\"\u0012\u0096t\u00a2Z\u00ba\u0094o\u00d9\u009d\u00ca\u001c\u00c4jY\u00f8B6z\u00c0\u0091\u00d4\u00b1\u00a6U\u0096\u000ek\u00de$rv\u00a8\u00d8\u00d2O&7;^TF\u00b5\u000f`\u00d5Vb4\u008b\u00c3\u00c9\u00d0\\M\u00101w\u00e5\u0017Y\u00f7\u00b2S\u00d4\u0019\u00e8\u0004\u00b3\u00b9]\u00f6e\u0002\u00ba\u00e7\u0096=@t\u0006A\u0004\u00f8\u00acnj0\u00c1\u0097:\u00cbD\u00b9\u0082\u001e|\u00bc\u00d7C\u00b1\u00b9\u001e\u00b0^t\u0013h\u00c2\u0092Z\u00d3\u00af\u00de\u00fa3\u000f\u008d\u00bbI\u00e9#\u0097\u00b7\"\u00dd\u00f34\u00ba\u00ac\u00e0\u00c5\u00ccc\u00db\u0013Q#&ni\u0092K5H3\u00c8(t\u00f8\u00b3\u00c9\u0001\u00ca\u00de\u00dat.\u00ab\u001e\u0090\u00c2-8\u00f1DN\u00ef\u00d6\u00f8\u00ac\u0093n,$@h\u00b3O\u0097E\u009c\u00d6\u00a9U\u00ad\u00bc\u0012\u00b09\u00b5\u00c0`i\u0010\u00cc\u0084\u0006oR\u009f\u0016QHa\u0089\u00b3\u00e8\u00a7=\u00b6\u00ab>\u00ff\u0012\u00cbn\u0014\u0015\u00b1\u0096a\u009c\u009a\u0004\u0010\u00cfY\u00bdJ\u00a3!\u00a1;\u001f\u00cf\u00aa\u00bf(\u008d\u0015n\u00b4R\u00a1\u00b2NoY\u009e\u00dd\u00a9%\u00f4\u00eb\u00f6\u00ea\u009c\u0097/\t\u00b6U\u0016$\u00e84\u00f0\u00c0E\u00b0/7\u00edL\u0005\u00e0\u009a\u00ab h\u001f\u00beFCV\u00f7\u00fa\u00cc\t\u00e5\u00f5_4[\u009b\u0083'G\u00c8+\u00f0\u00b8\u00a3\u00f9r\f\u0080]\u0012Y\t\u0018T>o\u007fg\nP\u0013\u00f70\u00cf\tT\u0089;\u00f0\u00a8\u0090@+\u00c0.-\u00e2\u0018\u00ac\u00b0~\u001c'\u00b6\u00ce1`\u000b\u00a4\u00a7\u00e6<`r\u00f7\u00f3\u00ba\u00c7\u00a5\u0087{\u0015(V\u00bf\u0004\u00f5\u00dc\u00a6w\u00bf\u00a0\u00de\t\u0012\u00ab\u007f\u00d2mg\u0010\u008c5\u00e5@\u00df\u009bnt\u0005\u00ad\u00b7\u001bE\u00f2\u00c9>}e`\u00c5\"W \u0010\f\u000fmp\u00a6\u00abZ\u00a3\u00f8\u007f\u001c\u0097\u00a4H\u00e5\u00a5v\u0013\u009aaDy\u00958\u00ebJ\u00d5c\u008d_\"(2\u00c2\u00ed\u00f3/\u0011\u00d5\u00c7\u00ac\u00fd%S^^\u00cf\u00c1\u008d_\u00cf\u00be\u00f1\u0005\u00f0D\u00d1\u00188\u00d1\u00da`\u009a'\u0089\u00fdn\u0099\u00b8\u00ef\u00b9\u0011 \u00a4\u001a\u000b\u00b1\u00b0^x\u00c5\u00cf\u00a2\u00e6\u00a1\u0088\u000b=\u00db\u00b00y\u00d2H\u008a\u00fc\u00de\u00182\u0091\u0093\u0002|T* NII\u00ef\u00c2\u00d4\u0016\u0092\u0093\u00ca]v\u00ef\u009b\u0087\u00f3\u009a\u0094\u00c8\u00a4\u00c1\u0091w\u00e3\u008dXlN\u00d4\u00c9\u0091\u00d1\u0018>\u00a6\u00c0=\u00af\u000eC\u00aa\u00bf\u0082\u001c\u00feRFM\u008a\u0094\u0010\u001fV\u0005\u0089\u0016*\u0010\u001e\u0012&\u0098\u00be\u0014\u00cb\u00d3\u0092_\u001b\u00e7\u009b\u00ef\u00c3\u00cc\u0010\u00eb\u00bd\u00d0Z\r;%Fl9\u00f0dt}2\u0081 3\u00aa\u00a39\u009f\u0086\u00a9\u009b<\u000b\u00d2\u00d6^\u00c6cW\u00a5\u009e|\u0082\u00f1\u00cf,\\\u00e4\bIAeskl\u0010\u00c6\u00f0_\u00b6\u00dc\u0083\u00d1\u00cdM\u00d6\u00be\u0095\u00e4\u001c\u0016\u0004\u0010\u00ce\u00bb\u0089{\u0085Xa\u0085\u00c4\u00a4#\u00175\u00a7`\u00ca\u0010dvu\u00d5\u0096D\u008b\u001fF\u00c0U\u00f9\u001b$b\u0006 \u00e9\u00e2\u0084\u00dc\u0019\u00a1\u0013\u009e\u00a7\u0018[\u009aF\u001f\u00dd\u00f5\u00de\u008e\u00d9\u0086\u0083\u00b9\u00c3\u00de\u000e\u00fdW\u00bd\u007f\u0014R\u00a187\u00b3>\u0007(\u00d1\\\u001d\u0000>\u00c3\u00b1!hF\u00c6o3V\u008a\u00b1\u00c2K\bL\u00bb\u00aa\u00dcZ\u000f#\u00b2Q\u0090\u0096\u0081g2O#\u00a5Z\u00b3\u0001\u00f8\u00b3^\u008a%\u000f\b!\u00be-\u00d1\u00f1";
                        var17_6 = 989;
                        var14_7 = 152;
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
                            var18_3[var16_4++] = PopChams.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00957|\b\u00a8'\u00f13Z\u009aR\u00e1Y\u0011\u00e7\\U\u00ce\u00af\u0097\u00c3^\u00a8\u0090\u0083\u00d4\u00a4O_i\u000f\u0001\u0010\u0004}\u00e6][c\u000b\u00c8\u00bdR\u00e3\u00d1R\u0015\u00cc\u00dd";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = PopChams.zkmDecodeUtf8(var19_9).intern();
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
                PopChams.b = var18_3;
                PopChams.d = new String[24];
                PopChams.h = new HashMap<K, V>(13);
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
                var4_14 = "\u00dd\u0014gA\u00f9\u0080\u00cf\u00c1$=\u0093\u0085Y\u000b\u00fa?ya\u001b\u00d9l@\u001cF\u00b1\u009c0I\u00be\u00ab\u00b9\u0006\u0094\u0093\u00c3\u008a<\u00ae\u00a76\u0007$\u00d7\u0002\u00c3\u0089F\u00ff&\u00ffz$\u0016\u00c8^\u00aa2]\u00aa8\u00ecu\u00ef\u00ceV\u00ce\u009d\u00db\u00faW\u00a1\u00e7\u00b8\u001eD\u008a\u00f5\u0015\u00ec\u00eb\u00eb\u00d1\u00a4\u000f\u00e8\u00a0\u0080zE@\u000b\u0005D\u00b02J";
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
                    var4_14 = "\u0014\u0006~\u0015\n\u00f4K\u00a4\u00c9\b\u00d0\u0089!\u00bd\u00aa\u00d6";
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
        PopChams.f = var6_12;
        PopChams.g = new Integer[14];
        var31_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(PopChams.class, "mode", "getMode()Lsu/catlean/as;", 0)), Reflection.property1(new PropertyReference1Impl(PopChams.class, "alpha", "getAlpha()I", 0)), Reflection.property1(new PropertyReference1Impl(PopChams.class, "ySpeed", "getYSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(PopChams.class, "aSpeed", "getASpeed()F", 0))};
        PopChams.delegatedProperties = var31_20;
        PopChams.INSTANCE = new PopChams();
        PopChams.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)PopChams.INSTANCE, "mode", (Enum)PopChamsMode.DEFAULT, null, null, 12, null);
        PopChams.alpha$delegate = SettingFactory.intSetting$default(PopChams.INSTANCE, "start-alpha", 255, new IntRange(0, 255), null, null, 24, null);
        PopChams.ySpeed$delegate = SettingFactory.floatSetting$default(PopChams.INSTANCE, "y-speed", 0.1f, RangesKt.rangeTo(-1.0f, 1.0f), null, null, 24, null);
        PopChams.aSpeed$delegate = SettingFactory.floatSetting$default(PopChams.INSTANCE, "alpha-speed", 5.0f, RangesKt.rangeTo(1.0f, 20.0f), null, null, 24, null);
        PopChams.ghosts = new CopyOnWriteArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5E7A;
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
                throw new RuntimeException("su/catlean/k4", exception);
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
            PopChams.d[n2] = PopChams.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PopChams.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x534E;
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
                throw new RuntimeException("su/catlean/k4", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PopChams.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PopChams.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PopChams.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PopChams.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

