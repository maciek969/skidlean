/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.authlib.GameProfile
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Quaternionfc
 *  org.joml.Vector4d
 */
package su.catlean.module.esp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Quaternionfc;
import org.joml.Vector4d;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.esp.LogoutEntry;
import su.catlean.module.esp.LogoutSpotsRenderMode;
import su.catlean.module.esp.LogoutSpotsRenderMode$WhenMappings;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BoxRenderMode;
import su.catlean.render.ModelRenderer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.TexturedPolygonConsumer;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.zkm.ZkmKeys;

public final class LogoutSpots
extends Module {
    @NotNull
    public static final LogoutSpots INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting renderMode$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @NotNull
    private static final BooleanSetting friends$delegate;
    @NotNull
    private static final IntSetting hDistance$delegate;
    @NotNull
    private static final IntSetting vDistance$delegate;
    @NotNull
    private static final BooleanSetting timeOut$delegate;
    @NotNull
    private static final FloatSetting timeOutValue$delegate;
    @NotNull
    private static final Map h;
    @NotNull
    private static final ArrayList entries;
    @NotNull
    private static final RenderTarget renderTarget;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] k;
    private static final Map l;

    private LogoutSpots() {
        super("logout-spots", Categories.getEsp(), null, null);
    }

    private final LogoutSpotsRenderMode getRenderMode() {
        return (LogoutSpotsRenderMode)((Object)renderMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getFriends() {
        return (Boolean)friends$delegate.getValue(this, delegatedProperties[2]);
    }

    private final int getHDistance() {
        return ((Number)hDistance$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final void setHDistance(int n) {
        hDistance$delegate.setValue(this, delegatedProperties[3], n);
    }

    private final int getVDistance() {
        return ((Number)vDistance$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final void setVDistance(int n) {
        vDistance$delegate.setValue(this, delegatedProperties[4], n);
    }

    private final boolean getTimeOut() {
        return (Boolean)timeOut$delegate.getValue(this, delegatedProperties[5]);
    }

    private final float getTimeOutValue() {
        return ((Number)timeOutValue$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        Object object;
        if (receivePacket.getPacket() instanceof PlayerListS2CPacket) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket");
            PlayerListS2CPacket playerListS2CPacket = (PlayerListS2CPacket)packet;
            if (playerListS2CPacket.getActions().contains((Object)PlayerListS2CPacket.Action.ADD_PLAYER)) {
                object = playerListS2CPacket.getPlayerAdditionEntries().iterator();
                while (object.hasNext()) {
                    Object object2;
                    Intrinsics.checkNotNullExpressionValue(object.next(), "next(...)");
                    entries.removeIf(arg_0 -> LogoutSpots.invokePredicate(arg_0 -> LogoutSpots.onReceivePacket$lambda$0((PlayerListS2CPacket.Entry)object2, arg_0), arg_0));
                }
            }
            h.clear();
        }
        if (receivePacket.getPacket() instanceof PlayerRemoveS2CPacket) {
            for (Object e : ((PlayerRemoveS2CPacket)receivePacket.getPacket()).comp_1105()) {
                Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                object = (UUID)e;
                for (Object e2 : h.keySet()) {
                    PlayerEntity playerEntity;
                    Intrinsics.checkNotNullExpressionValue(e2, "next(...)");
                    UUID uUID = (UUID)e2;
                    if (!Intrinsics.areEqual(uUID, object)) continue;
                    if ((PlayerEntity)h.get(uUID) == null) continue;
                    if (!INSTANCE.getFriends() && Friends.getIsHomie(playerEntity)) continue;
                    Module module2 = INSTANCE;
                    module2 = INSTANCE;
                    Object[] objectArray = new Object[4];
                    Intrinsics.checkNotNullExpressionValue(playerEntity.getName().getString(), "getString(...)");
                    objectArray[1] = (int)playerEntity.getX();
                    objectArray[2] = (int)playerEntity.getY();
                    objectArray[3] = (int)playerEntity.getZ();
                    ModuleMessages.S(module, ModuleMessages.i18n$default(module2, "logout", objectArray, false, 4, null), false, null);
                    entries.add(new LogoutEntry(uUID, playerEntity, System.currentTimeMillis()));
                }
            }
        }
    }

    @Override
    public void onEnable(long l) {
        h.clear();
        entries.clear();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
            if (abstractClientPlayerEntity == null || Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getPlayer()) || this.isBot(abstractClientPlayerEntity)) continue;
            h.put(abstractClientPlayerEntity.getGameProfile().id(), abstractClientPlayerEntity);
        }
        if (this.getTimeOut()) {
            ArrayList arrayList = Lists.newArrayList((Iterable)entries);
            Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
            Iterable iterable = arrayList;
            Iterator iterator2 = iterable.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                LogoutEntry logoutEntry = (LogoutEntry)t;
                if (!((float)(Globals.now() - logoutEntry.getLogTime()) > INSTANCE.getTimeOutValue() * 1000.0f)) continue;
                entries.remove(logoutEntry);
            }
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l;
        long l2 = l = a ^ 0x23CFC781DD37L;
        long l3 = l2 ^ 0x2EB85C4AEAD0L;
        long l4 = l2 ^ 0x24F41156E43L;
        int n = (int)(l4 >>> 48);
        int n2 = (int)(l4 << 16 >>> 32);
        int n3 = (int)(l4 << 48 >>> 48);
        switch (LogoutSpotsRenderMode$WhenMappings.o[this.getRenderMode().ordinal()]) {
            case 1: {
                this.renderSpots((short)n, n2, render3DEvent.getStack(), n3);
                break;
            }
            case 2: {
                Collection<PlayerEntity> collection;
                Iterable iterable = entries;
                Color color = RenderUtil.INSTANCE.withAlpha(this.getColor(), 1.0f);
                Color color2 = this.getColor();
                BoxRenderMode boxRenderMode = BoxRenderMode.BOTH;
                MatrixStack matrixStack = render3DEvent.getStack();
                ModelRenderer modelRenderer = ModelRenderer.INSTANCE;
                Iterable iterable2 = iterable;
                Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Object t : iterable2) {
                    LogoutEntry logoutEntry = (LogoutEntry)t;
                    collection = collection2;
                    collection.add(logoutEntry.getPlayer());
                }
                collection = (List)collection2;
                ArrayList arrayList = Lists.newArrayList((Iterable)collection);
                Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
                int n4 = this.getHDistance();
                int n5 = this.getVDistance();
                ArrayList arrayList2 = arrayList;
                modelRenderer.B(matrixStack, boxRenderMode, color2, color, l3, arrayList2, n5, n4);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l;
        long l2 = l = a ^ 0x62B8E18B9E96L;
        long l3 = l2 ^ 0x726E2C82900CL;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x1CD33CC92D8DL;
        long l5 = l2 ^ 0x13E380E13CC3L;
        long l6 = l2 ^ 0x76AE0A66F11BL;
        ArrayList arrayList = Lists.newArrayList((Iterable)entries);
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        Iterable iterable = arrayList;
        for (Object t : iterable) {
            LogoutEntry logoutEntry = (LogoutEntry)t;
            float f = INSTANCE.getTimeOut() ? 1.0f - (float)(Globals.now() - logoutEntry.getLogTime()) / (INSTANCE.getTimeOutValue() * 1000.0f) : 1.0f;
            Entity entity = logoutEntry.getPlayer();
            Vec3d vec3d = INSTANCE.getPos(entity).add(0.0, 2.0, 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            Vec3d vec3d2 = RenderUtil.INSTANCE.toCameraRelative(vec3d);
            Vector4d vector4d = null;
            if (vec3d2.z > 0.0 && vec3d2.z < 1.0) {
                vector4d = new Vector4d(vec3d2.x, vec3d2.y, vec3d2.z, 0.0);
                vector4d.x = Math.min(vec3d2.x, vector4d.x);
                vector4d.y = Math.min(vec3d2.y, vector4d.y);
                vector4d.z = Math.max(vec3d2.x, vector4d.z);
            }
            float f2 = (float)(System.currentTimeMillis() - logoutEntry.getLogTime()) / 500.0f;
            int n4 = (int)((double)(System.currentTimeMillis() - logoutEntry.getLogTime()) / 1000.0);
            Object object = logoutEntry.getPlayer().getName().getString() + " " + (int)(logoutEntry.getPlayer().getHealth() + logoutEntry.getPlayer().getAbsorptionAmount()) + " " + n4 / 60 + ":" + n4 % 60;
            object = StringsKt.dropLast((String)object, Math.max(0, (int)((float)((String)object).length() * (1.0f - f2))));
            if (vector4d == null) continue;
            float f3 = (float)(vector4d.z - vector4d.x) / 2.0f;
            float f4 = Fonts.getSF_B2().getWidth(l4, (String)object) + 18.0f;
            float f5 = (float)(vector4d.x + (double)f3 - (double)(f4 / 2.0f));
            Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            float f6 = f * RenderUtil.INSTANCE.getAlpha(Theme.INSTANCE.getWidgetBlurColor());
            float f7 = f * RenderUtil.INSTANCE.getAlpha(Theme.INSTANCE.getWidgetBlurColor());
            Shapes2D.drawShape$default(matrix3x2fStack, (char)n, f5 - 2.0f, (float)vector4d.y - 13.0f, f4, 12.0f, 2.0f, 1.5f, 0.0f, 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getWidgetBlurColor(), f6), RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getOutlineColor(), f7), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
            Matrix3x2fStack matrix3x2fStack2 = render2DEvent.getContext().getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            float f8 = f * RenderUtil.INSTANCE.getAlpha(Theme.INSTANCE.getWidgetBlurColor());
            RenderTarget.drawRoundedText$default(renderTarget, (Matrix3x2f)matrix3x2fStack2, f5, (double)((float)vector4d.y) - 13.5, 0.0f, 0.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f8), null, 0.0f, 216, l6, null);
            float f9 = f * RenderUtil.INSTANCE.getAlpha(Theme.INSTANCE.getWidgetBlurColor());
            Fonts.getSF_B2().drawStringWithShadow(render2DEvent.getContext(), (String)object, l5, f5 + 12.0f, (float)vector4d.y - 13.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f9));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isBot(PlayerEntity playerEntity) {
        String string = playerEntity.getGameProfile().name();
        Intrinsics.checkNotNullExpressionValue(string, "name(...)");
        if (StringsKt.contains$default((CharSequence)string, "-", false, 2, null)) return true;
        String string2 = playerEntity.getGameProfile().name();
        Intrinsics.checkNotNullExpressionValue(string2, "name(...)");
        if (!StringsKt.contains$default((CharSequence)string2, "_", false, 2, null)) return false;
        return true;
    }

    private final void renderSpots(short s, int n, MatrixStack matrixStack, int n2) {
        long l = ((long)s << 48 | (long)n << 32 >>> 16 | (long)n2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x57FCD2E823C7L;
        ArrayList arrayList = Lists.newArrayList((Iterable)entries);
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        Iterable iterable = arrayList;
        for (Object t : iterable) {
            LogoutEntry logoutEntry = (LogoutEntry)t;
            Intrinsics.checkNotNull(logoutEntry);
            INSTANCE.renderGhost(matrixStack, logoutEntry, INSTANCE.getTimeOut() ? 1.0f - (float)(Globals.now() - logoutEntry.getLogTime()) / (INSTANCE.getTimeOutValue() * 1000.0f) : Math.max(0.4f, 1.0f - (float)(Globals.now() - logoutEntry.getLogTime()) / 800.0f), l2);
        }
    }

    private final void renderGhost(MatrixStack matrixStack, LogoutEntry logoutEntry, float f, long l) {
        long l2 = (l = a ^ l) ^ 0x530C8D2D699DL;
        PlayerEntity playerEntity = logoutEntry.getPlayer();
        EntityRenderer<?, Entity> entityRenderer = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity);
        Intrinsics.checkNotNull(entityRenderer, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.player.AvatarRenderer<in @[EnhancedNullability] net.minecraft.world.entity.player.Player>");
        Object m = ((PlayerEntityRenderer)entityRenderer).getModel();
        Intrinsics.checkNotNullExpressionValue(m, "getModel(...)");
        PlayerEntityModel playerEntityModel = (PlayerEntityModel)m;
        Object object = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)playerEntity).getAndUpdateRenderState((Entity)playerEntity, 1.0f);
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.state.AvatarRenderState");
        PlayerEntityRenderState playerEntityRenderState = (PlayerEntityRenderState)object;
        TextureManager textureManager = Globals.getMc().getTextureManager();
        Intrinsics.checkNotNull(playerEntity, "null cannot be cast to non-null type net.minecraft.client.player.RemotePlayer");
        GpuTextureView gpuTextureView = textureManager.getTexture(((OtherClientPlayerEntity)playerEntity).getSkin().comp_1626().comp_3627()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        object = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = 256;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        TexturedPolygonConsumer texturedPolygonConsumer = new TexturedPolygonConsumer(batchedBuffer, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f));
        matrixStack.push();
        Vec3d vec3d = this.getPos(playerEntity);
        double d = vec3d.x;
        Camera camera = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera);
        double d2 = d - camera.getCameraPos().getX();
        double d3 = vec3d.y;
        Camera camera2 = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera2);
        double d4 = d3 - camera2.getCameraPos().getY();
        double d5 = vec3d.z;
        Camera camera3 = Globals.getMc().getEntityRenderDispatcher().camera;
        Intrinsics.checkNotNull(camera3);
        double d6 = d5 - camera3.getCameraPos().getZ();
        matrixStack.translate((float)d2, (float)d4, (float)d6);
        matrixStack.scale(playerEntityRenderState.baseScale, playerEntityRenderState.baseScale, playerEntityRenderState.baseScale);
        this.applyModelRotation(playerEntityRenderState, matrixStack, playerEntityRenderState.bodyYaw);
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
        matrixStack.translate(0.0f, -1.501f, 0.0f);
        playerEntityModel.setAngles(playerEntityRenderState);
        playerEntityModel.render(matrixStack, texturedPolygonConsumer, 0xF000F0, 0, Theme.INSTANCE.getPrimaryColor().getRGB());
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureCull(), null, null, new MatrixStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", object)), 22, null);
        matrixStack.pop();
    }

    public final void applyModelRotation(@NotNull PlayerEntityRenderState playerEntityRenderState, @NotNull MatrixStack matrixStack, float f) {
        block2: {
            block3: {
                block1: {
                    Intrinsics.checkNotNullParameter(playerEntityRenderState, "state");
                    Intrinsics.checkNotNullParameter(matrixStack, "stack");
                    if (!playerEntityRenderState.isGliding) break block1;
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - f));
                    if (!playerEntityRenderState.usingRiptide) {
                        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(playerEntityRenderState.getGlidingProgress() * (-90.0f - playerEntityRenderState.pitch)));
                    }
                    if (!playerEntityRenderState.applyFlyingRotation) break block2;
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotation(playerEntityRenderState.flyingRotation));
                    break block2;
                }
                if (!(playerEntityRenderState.leaningPitch > 0.0f)) break block3;
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - f));
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(playerEntityRenderState.leaningPitch, 0.0f, playerEntityRenderState.touchingWater ? -90.0f - playerEntityRenderState.pitch : -90.0f)));
                if (!playerEntityRenderState.isSwimming) break block2;
                matrixStack.translate(0.0f, -1.0f, 0.3f);
                break block2;
            }
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - f));
        }
    }

    private static final boolean onReceivePacket$lambda$0(PlayerListS2CPacket.Entry entry, LogoutEntry logoutEntry) {
        boolean bl;
        Intrinsics.checkNotNullParameter(logoutEntry, "it");
        GameProfile gameProfile = entry.comp_1107();
        if (Intrinsics.areEqual(logoutEntry.getUuid(), gameProfile != null ? gameProfile.id() : null)) {
            Module module = INSTANCE;
            Module module2 = INSTANCE;
            Object[] objectArray = new Object[4];
            Intrinsics.checkNotNullExpressionValue(logoutEntry.getPlayer().getName().getString(), "getString(...)");
            objectArray[1] = (int)logoutEntry.getPlayer().getX();
            objectArray[2] = (int)logoutEntry.getPlayer().getY();
            objectArray[3] = (int)logoutEntry.getPlayer().getZ();
            ModuleMessages.S(module, ModuleMessages.i18n$default(module2, "login", objectArray, false, 4, null), false, null);
            bl = true;
        } else {
            bl = false;
        }
        return bl;
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
                        LogoutSpots.a = ZkmKeys.a(8846461057793024515L, 1805070519772827770L, MethodHandles.lookup().lookupClass()).a(39305428616139L);
                        var20 = LogoutSpots.a ^ 35319565411781L;
                        LogoutSpots.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[49];
                        var16_4 = 0;
                        var15_5 = "\u0012\u00e3v*1x\u0081D<'p.\u009b\u00e4\\\u00a9S\u001c\u00e8\u00b7.\u00e6\u0019\u00d2HW?;\u00a1q\u00f6T\u00da!E\u0016\u00c2\u001a\u00d7\u00b0 22Oh\u00a1\u0093^\u0003=\u00fb\u0089\u0018q\u0091b\u00cf\u00e8\u0097\u00fb5\u00a2\u0004\u0086Xj!<>F\u00d2\u00fd\u009f \u0092\u00fc6\u00cf\u00c4\u00a2\u00e9\u001fk\u000f\u0083J0\u00e4\u00baI\u001d\u008f\u001d3\u00ecXS]\u007fO\u00fe\u0087\u008b\u00eeN\u00a1\u0100WHX\u008d\u00ba\u00f6/ZI\u001e\n\u0094\u00ba^\u0091\u00d9)|. \u00cb\u0089\u00f8\u00fdS\u00de\u0017\u0084\u00ee[\u00bb\u001b\u0006\u00ac\u00a2\u008a\u00b2\u00df'\u00a4\u00b4\u0093F\u00efN\u00fd\u00ae\u0016]$\u00a8\u0081\f6(\u009f\u00f8(\u00a2\u00c0\u008b\"\u008dVTX\u000ee\u0004\u00cf\u0092T\u00d0\u00e1\u00d2\u00cd\u0098\u00cdY\u0001\u0000\u00dc/\b\u00cevK\u00ca\u00c1\u009d\u0086\u00c8\u0089\u00cc\u00ec\u00be\u0014\u009ap\u00e9\u00d3\u001b3E\u0018\u0089\u00ac\u0087\u00db5T\u00c2mP\n\u00e1\u00e6[\u0006\u00cc\u00dd\u00ff\u0080\u00b4)\u00c1=\u00e0\u0012!\u00ef\u0010\u00d9/\u0007\u008c\u00b50\r\u00b4\u001c\u00cb\u00acX\u00c7\u00e6;\u00be\u0013\u0087&\u00ee:,\u00fa\u00d3n\u0094\u00c7\u008c\u00ec\u00b0l\u00c0K\u00c9i\u00cbV\b\u00d9\u00cb\u00b5\u00d9|@\u00f9a\u00f1\u00ba\u0006\u000b\u00e4]\u00ac6>K\u00193\u00ef$N\u0011\u001b\u00f1\u009f\u0086\u0093\u00ee\u00c7mi\b\u00a1\"\u00fc\u0012Oq\u00cc\u00ecRw\u0083P\u009a\u00e6\u0004\u000e\u00ef\u0004h\u00cd\u001b8\u0093:\"\u00b6\u00a1\u00d3\u00ec<\u0015% g\u00cd\u00eb\u00d6\u00eeQH;>>\u00a6_eh\u00b5\u00ba\u00b0\u0014M\u0080\u0084~M\u00b7\u00c5\u00e6i\u00b8\u0002\\q\u00e1\u00ecJ\u00d5q\u00a6\u00c2z9\u00a2\u00c6\u00acT\u00a1\u00cf\u0086\u0082\u0016\u00c7\u0099\u009b[\u00cf\u00f70V\u00a5\u00cc\u0015\u00c2'Q<\u008f\u00f3{\u00fd2^\u00e2\u00b1\u00df\u0081\u009a\u0080\u00e1\u00ed\u00f6\u00f8\t\u00cde\f\u0089HG\u00a2\u0013\u009b^\u0017#O\u00d14\u0013\u0015\u0013f\u000e\u00b99|\u0010\u00e8\u000f\u00b6\u00f1\u00f0L\\\u00b9m\u00d2(\u00886|\u00ca\u00fa\u00fb\u00a7\u0099k\"b\u001a\u0092A\u00fdL'k@<X@\u00d5b\u00d4\u00fc\u0084w\u0091$\u00d4\u00a8 \u0096\u00a0L\u00f8\u00c2\u00c2\u00a7\u00a0\u0093\r?\u0095d5\u00af\u0017\u00ea\u0014\u0098\u0082\u00ec\u00a7\u00df\u00a7g\u0096] Q\"\u0097r\u0010\u0017\u00b6b\u00ae6v\u00c3!K\u0003\u0085\u00d8\u00d96\u00e0\u0086\u0018\u00d9,\u00d1\u00c6)z\u000e\u00f2\u00e6\u00bdyg\u00b9g\u00ce<\u001d\u000fT\u00a20GB\\\u0018+\u00d0\u0014\u00e6\u00fa*\u007f\u00e4\u0000\u0093\u00b1\u00acj{\u00bdn\u0013\u00b4\\\"K\u00bejT\u00a0\u00e3\u00d3\u0002\u00ca\u00af<\u00aaTt\u0098\u00c7\u00e4+\u00ae<)\u0097\u0002\u009cp\u00bf\u0012+\u0081~E\u00f3\u00f5\u0093H\u00ae\u0082I\u00dd\u00cf#\u00d9`\u008d\u0083\u00b2}\u0004\u00b4\u001eh\u00f8\u00ec\u00dc\u00e0\u0014\u00e8\u008d\u00fc&\u00ae\u0083\u0013Q\u00cbCs\u00e0M\u00e9F>)/\u00ad_N\u0081\u0096\u00a2\u00ccH\u0001\u00e9\u00c6LE\u00fc\u00f1\u001d\u00b6\u00ecd\u00c4f\u00d3\u00fe\u00a4\u0083\rj\u00adZ4\u008dDE\u0095\u0097d\u008eV\u00c1)\u0018\u00b2(h\u00f7\\~\u00df\u0014\u0000\u00d1\u00e0=\u0088\u00be\u00f2j Y$\u0003\u00e3\u0088(\u00c5:\u001d)\u00f88\u00cefp\u0089A\f\u00e6\u00c8M\u00f1\u0088\u00c7\u00d6\u00df\t\u0007\u00ae\u00cf'\u00e4O\u0018pB/H\u0083Ez\u0083~}[\u00acn\u0004\u0015m\u00d9\u00c4\u00fer7\u00912\u0099 \u00f8\u0095Y\u000b'\u009d\u007f\u00e9\u008aEY\u009dz\u00af\t\u00c9\u0094\u00b6\u00e5~\u00ae\u00e7N\t\u00e8\u00edH\u00c9g\u00e2\u00df) B\f\u00858\u00a2\u00b1&\u00c9\u0007\u008e]:\u00ce\u00fd\u00e86xm-\u00ab\u0019\u0019r\u0087\u001aVS\u008d\u00e5\u0017\u00dbH\u0010\b\u00fb!\u00df\u00d7\u0096D^\u00ab\u001f+\u00a1\u00ed\u008d\u00f9#8\u00cf\u00e3\u009c\u0096\u00b3\u00d3\u00b5)\u00f5\u008d\u0006e\u0092 \u00e3|\u00ed\u008a\u00f0/\u00aaN\u0001\u00e0\u00e5td\u00b7\u00a12\u00b9T\u00d4\u00c5\u00c4\u00e8\u00f3.M\u00f7\u00e7\u00b9RA\u00d0mC\u00ab\u00ae8\u00e0M\u00ed\u00a6\u00d3\u0082 \u00cf\r3\u00eb\u001a\u0091\u00cb\u009d\u0093_\u008a]\u0099V1\b\u00a1B\u0002|P-\u00c6h\u00cb\u00a8\u0006\u00d2\u00f1>\u00b4V\u00a80q\u00c4P<\u00f9P\u00b2\u00ce\u0088\u00bd7\u00c3\u00e5\u00f5\u00d7\u001bY\u0093X\u00fc*\u00f0\u008bl=\u00ecd\u00d1\f\u00b2\u00dba\u00a3\u00c9\u00bf\u008fZ\u001bb\u0084\u00af\u0015\u00ea|\u00cd\r\u00c5\u00e1\u0010\u00ea\u0088\u00ec\u00aa\u00d7E\u00ceP\u00d0\u00f1:\u00af\u0095\u007f\u00f1\u00c1\u00d1\u00e1k:z\u00bf\u0094\u009eG#\u001a\u00b0:r\u008a\u00d4oFaa\u001a\f\u00f8\u00cd!\u001f%\u00b2\b\u00ea\u001b\u00df\u00af\u0005\u001bi\u00fb\u00c8.\\sq\u00f7\u001b\u0011\u008e\u0013MN\u0085\u0094\u0085U\u00e8\u00f4\u0006\u0080+\u00bb\u00faq\u000b\u00d97\u0080\u0003yH\u0018\u0087\u00e7\u00e0\u00eb\u00a2s\u0082${\u0006\u00c4\u008b&\u0014O\u00c5\u000b%B5R\u00a4\u00db\u00db\u0084q&\u00e4h\u008e\u001f\u0000\u00ac([2\u00e1\u00b1\u00b6\u0013\u00b1;\u0096\u00ad\u00b0\u00a1t\u00a9\u0010\u00d4\u00f4\u0001\u00c1\u00cc8K\u00ba\u00c1\u00c6\u00b2\u00be\u00b6\u00be\u001f%\u0002\u0084\u0093y\u0098yi\u00abw \u00d3\u00e3\u00c7\u0006\u00ec\u00d3\u00a7\u00d2\u00a9@\u0019\u00e4gc\u0082\u001c\u00d3\u009b\u0082X\u00f3\u00bd\u00e7\u00b4\u00bcJ[\u009e\u00e8z\u00c3B\u0010\u009cv\u00c0\u0016zZ\u00d9\u0096*X?\u008e\u00a1\u00c7-\u007f(q~\u00caV\u00a6\u00b0\u00f3\u00a3i\u00fa\u00bb\u00f14b\u0015;t{\u00e4\\q\u008e\u00cb\u001c\u00e6\u00fcD\u009c'\u00ebg\u0002\u008d\u00c2s(\u0003\u0095\u0093\u00e4\u0010A\u0084\u00f1\u00175\rtl\u0088\u00f0\u00df-\u00e4ZD\u00de \u0014\u00cc\u00c1\u00a7\u001e\u00fd\u008c\u00ce\u00f6\u00dc]{\u00a8\u00d3J\u00cf\u0016G\u00df\u00c6\u00a2\u001a2\u0014t\u00d1\u00b8\u00a0\u00e3\u00c7\u00ae\u00d9\u0010\u00e4\u009b\u00c2B\u0096\u00e0tSh\u0011\u001ds\u00fb\u00f6l\u008a8&Z\u00d6H&>\u009e\u0081\u008f\t\u00b1N\u00b5;\u00b1\u00b3@>\u00b0\u00bb\u009dd/5\u0015\u00ae\u00b2|]\u00f9r\u00b4V\u0099 \u00d6j\u00dc\u0015\u0099`\u00068\u00f0\u00b0\u00ba\u0016`<\u00f0\u00e6\u00a4\u00d7a\u00d9\u00e0\u0010\u00db\u00e7\u00b8\u00d4\u00d6;\u009e\u00cc4\u00bb2\u00d4\u0083$$\u00f8\u0010bC\u00e2\u00b9\u0080\u0081n\u0098z>\u008f\u00f2\u007fD#\r \u0017\u00b3\u00b4\\\u00d6\\\u00da\u0080\u00d6N\u0094|\u00ec\u00af&\u00b6>B\u00ce\u009b\u0082\u008e\u00fe\u00fcW\u00d3\u0097^\u000b\u00f8K\u00eb \u0015\u00a3\u0006\u00a3\u00cf\u00c5\u009c(N\u00a9\u00e2\u001c\u00ed\u00a9K\u00c0Wv\u00feI\u00ce\u008891\u00ce\u008e\u0090\u00b4t\u0084[\u00bc \u001e\u00e4\u00a8\u0098F\u00b8\u00c8Uc\u0088dTdBo.\u001fP\u00e1,-\u00ca??\u00d3\n\u0082\u00caK\u00cb\u0099X\u0018\u0090\t\u00aa+\r\b\u00d2Zh\u00dc\u0004\u00be\u0000\u00f3^\u0082\u0093Fk[\u0019X\u0087l([(\u00aa=\u00b0\u00f8\u00ea3Ra\u0019\u00ce\u00b81\u00faE\u009a\u00d28\u00de\u00ebR\f\u001e\u00fb\u00f50~\u00b2\u0084rV<\u00ea}\u00f6\u00ee\u0017T&(Z\u00f4\u00175S:\u00e1V`\u00b9\u00a0\u00a2\u0018\u0011.\u00d4\u00aa?9-\u00b0.W\u0094v\u00fda\"\u00c6\u00c4\u000eC%E\u00f7\u00bd\u00a0v\u0098\u0090(\u00ca:\n\u000e\rP\u000e\u00f6\u009f\u00fe\u00ea\u0096\u00f5\u0014\u0019\u00e0\u00bd>c\u00beY\u00eb\u00d9\u00e0\u00b4\u00fc5\u00dd\u00e5\f\u00f9\u00f7 \u00be9\u00b1\u0006\u00a7\u008b\u00de\u0010\u0093\"\u00f8vs\u00e9\u00da\u0080S\u008fI\u00a8\u0084q#\u00ed \u00a2\u00b9\u00dd\u0090Z\u0014\u00ac\u00e4\u00c7\u00a6\u00bcQli+\u0094MWF\u00d7\u0094\u0084u\bxV\u00af\u0090\u0082\u00a4\u00aa> \u00b2,\u0004\u00aa.T$;\u0001t\u00ea\u00d2\u0081\u00ba\u00f2\u00f8\u00e6\u0080\u00f6\u00f8\u00e5\u008d\u00ee|,\u00de<\u001f\u0005\u009d\u0001\u0019\u0018\u0003\u00adi\u008b\u00ab3E \u00e6\u00a0\u0084\u00144\u0016\u00f7\u0081\u00ec\u0085\u009b\u00fe\u0012\u00ec\u0084\u00bc \u0092\u0000\u00fd\u00ea*\u00d51Y\u0017\u008d\u0019VC\u00bei}\"\u00d9*`A\u00af\"\u001c4p\u00c4\u00bd\u00a1j\u00f5\u00ce(OfZ\u0091\u00b1\u00d15 \u00fb\u00c2\u0017\u00ea|z9\u00cb\\\u00b2\u00d1j\u0087\u0089\u00af\u0006\u0087\u0097\u00f7-\fg\u0096\u0007\u0088(yU\u00c0sN\u001a \u00a1\nl\u00de\u0093\u0087\u009f\u00e2\u00f6\u00a6!\u008c\u00ba\u00ff\u0094\u00ad\u00bb\u00fc%}m~D\u00ec\u0007H\u0081\u00b3}\u0013\u0016\u0081\u0010%W\u00e1\u0080\u009d\u008d\u009e\u000e\u00f4-W\u00ab\u0094=\u00cf\u00a1 0\u00fc\u008e\b\u00b0g\u00b8|\u00f3\u00a2\u00fb9\u00cb\u00a9\u0083\u00c9#v\u008b\u00d2\u0086\u0018\u0095\u00ec[`\u000fzb\u00d1\u00cb\u00ab\u0010x\u0005\\P\u008c\u00aeN\u00f9\u00bb\u00f5ZQ>s\u00ec\u00b5 \u00df \u009c\u0002Fb\u00f7n=i\u00bfJ\u001eK\u001e>\u0012\u0093\u00ae\u0018\u00dc\u00e1\u0097\u0003\u001f\u00f2\u00fb\u00c0\u00d4\u00d2\u00c0[ 8\u00baB\u0090Z.\u00a3y\u00be\u00d1-3R\u0091\u0089\u00ed\u0083;\u00f4\u00f0_\u00b7\u00e1\u0092\u00cb\u0016\u001b\u00b2\u000fb60\u0018\u00df\u00bdM\u0090\u00c7vkh\u0004\u00f2`\u00b0W\u00de\u00e3.6<\u008ew\u009e\u00d7\u0087)";
                        var17_6 = 2030;
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
                            var18_3[var16_4++] = LogoutSpots.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u009eK[a\u00d0d\u0001\u00bb\u00d28\u00ec&\u00d2\u00aa\u00fa\u00b6Y\u000f9!\u0002\u0016\u00eb\u001f\u0096\u00d1\u00d4e\u00c6\u00f1\u00f3@\u00bc\u0085j\u0015\u0017t\u00fd/\u0010\u00c4\u00a9\u0092\u00ee\u0013\u0091\u00e1b\u009f\u00b8\u0016\u00d54\u00af\\s";
                            var17_6 = 57;
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
                            var18_3[var16_4++] = LogoutSpots.zkmDecodeUtf8(var19_9).intern();
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
                LogoutSpots.b = var18_3;
                LogoutSpots.d = new String[49];
                LogoutSpots.l = new HashMap<K, V>(13);
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
                var6_12 = new long[20];
                var3_13 = 0;
                var4_14 = "\u0010\u008c\u00aa\u00b9\u001av~4E\u0015\u00d8F \u00eb\u0095\u009e\u00b1[\u00f3\u00dd\u00ec\u00a6\u0088\u0088\u00bf\u00a4\u00a7Fu/,+\u0093h\u00d7~\u00ea\u00e3\u00b3W]\u00f2\u0004\u009d\u0086P\u00c8H\u0081XR>\u00b7\u0012\u000b\u00d0\u00c4\u00e5\u00e4S \u008au\u00ec\r@\u0080\u00fa\u00fe\u001d\u00fb\u009c\u0089\u0004\u0098\u009ee\u0010X0\u00df\u0011\u00f7\u001b\u00a3}\u0004\u00a5\u0092\u0098\u001e\u000f7?\r-\u0097\u008fO\u00c7\u0016x\u00b4\u00d3\u00f0M\u0010fT\u00e8\u00d2\u00eb\u008c\u00d7\u00e2{\u00d5\u0006\u00b9\\'\u00b0u\u001dU]\u00a6*\u0086\u00dc\u00dc1i\u00edP\"\"\u0084>3\u00c5\u0002\u00aa\u00cf";
                var5_15 = 144;
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
                    var4_14 = "\u0003\u00eb_\u00a4F\u000f\u00cc.q\u00fe\u0098z\u0085\u00f8I{";
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
        LogoutSpots.f = var6_12;
        LogoutSpots.k = new Integer[20];
        var38_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(LogoutSpots.class, "renderMode", "getRenderMode()Lsu/catlean/ne;", 0)), Reflection.property1(new PropertyReference1Impl(LogoutSpots.class, "color", "getColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(LogoutSpots.class, "friends", "getFriends()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(LogoutSpots.class, "hDistance", "getHDistance()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(LogoutSpots.class, "vDistance", "getVDistance()I", 0)), Reflection.property1(new PropertyReference1Impl(LogoutSpots.class, "timeOut", "getTimeOut()Z", 0)), Reflection.property1(new PropertyReference1Impl(LogoutSpots.class, "timeOutValue", "getTimeOutValue()F", 0))};
        LogoutSpots.delegatedProperties = var38_20;
        LogoutSpots.INSTANCE = new LogoutSpots();
        LogoutSpots.renderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)LogoutSpots.INSTANCE, "render-mode", (Enum)LogoutSpotsRenderMode.GHOST, null, null, 12, null);
        v15 = LogoutSpots.INSTANCE;
        v16 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v16, "WHITE");
        LogoutSpots.color$delegate = SettingFactory.colorSetting$default(v15, "color", v16, null, null, 12, null);
        LogoutSpots.friends$delegate = SettingFactory.booleanSetting$default((SettingContainer)LogoutSpots.INSTANCE, "friends", false, null, null, 12, null);
        LogoutSpots.hDistance$delegate = SettingFactory.intSetting$default(LogoutSpots.INSTANCE, "max-horizontal-distance", 64, new IntRange(1, 256), null, null, 24, null);
        LogoutSpots.vDistance$delegate = SettingFactory.intSetting$default(LogoutSpots.INSTANCE, "max-vertical-distance", 32, new IntRange(1, 256), null, null, 24, null);
        LogoutSpots.timeOut$delegate = SettingFactory.booleanSetting$default((SettingContainer)LogoutSpots.INSTANCE, "time-out", false, null, null, 12, null);
        LogoutSpots.timeOutValue$delegate = SettingFactory.floatSetting$default(LogoutSpots.INSTANCE, "time-out-value", 1.0f, RangesKt.rangeTo(0.0f, 3600.0f), null, null, 24, null);
        v17 = Maps.newConcurrentMap();
        Intrinsics.checkNotNullExpressionValue(v17, "newConcurrentMap(...)");
        LogoutSpots.h = v17;
        LogoutSpots.entries = new ArrayList<E>();
        LogoutSpots.renderTarget = new RenderTarget("no-connection", 10, 10);
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x173A;
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
                throw new RuntimeException("su/catlean/kf", exception);
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
            LogoutSpots.d[n2] = LogoutSpots.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LogoutSpots.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x617;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])LogoutSpots.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    LogoutSpots.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kf", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            LogoutSpots.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = LogoutSpots.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LogoutSpots.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(LogoutSpots.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

