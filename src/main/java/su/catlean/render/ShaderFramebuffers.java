/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.GofraState;
import su.catlean.mixins.accessors.GameRendererAccessor;
import su.catlean.module.cosmetic.HandChams;
import su.catlean.render.ModelRenderer;
import su.catlean.render.RenderState;
import su.catlean.render.ShaderMode;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ShaderFramebuffers {
    @Nullable
    private WindowFramebuffer U;
    @Nullable
    private WindowFramebuffer O;
    private boolean active;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @Nullable
    public final WindowFramebuffer d() {
        return this.U;
    }

    public final void q(@Nullable WindowFramebuffer windowFramebuffer) {
        this.U = windowFramebuffer;
    }

    @Nullable
    public final WindowFramebuffer w() {
        return this.O;
    }

    public final void A(@Nullable WindowFramebuffer windowFramebuffer) {
        this.O = windowFramebuffer;
    }

    public final boolean isActive() {
        return this.active;
    }

    public final void setActive(boolean bl) {
        this.active = bl;
    }

    public final void render(@NotNull MatrixStack matrixStack, boolean bl, long l, int n, int n2) {
        long l2 = (l = a ^ l) ^ 0x671B8A7922D3L;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 16 >>> 48);
        Intrinsics.checkNotNullParameter(matrixStack, "stack");
        this.active = true;
        this.ensureFramebuffer((char)n3, (short)n4);
        this.renderEntities(matrixStack, bl, n, n2);
        this.active = false;
    }

    public final void applyShader(@NotNull ShaderMode shaderMode, @NotNull Color color, boolean bl, boolean bl2, float f, float f2, int n, float f3, float f4, int n2, @NotNull Color color2, @NotNull Color color3, int n3, short s, float f5, float f6, float f7, float f8, char c, float f9, float f10) {
        block4: {
            ShaderFramebuffers shaderFramebuffers;
            long l;
            block5: {
                block3: {
                    WindowFramebuffer windowFramebuffer;
                    block2: {
                        long l2 = ((long)n << 32 | (long)s << 48 >>> 32 | (long)c << 48 >>> 48) ^ a;
                        l = l2 ^ 0x7178B520F294L;
                        Intrinsics.checkNotNullParameter((Object)shaderMode, "mode");
                        boolean bl3 = ModelRenderer.v();
                        Intrinsics.checkNotNullParameter(color, "color");
                        boolean bl4 = bl3;
                        Intrinsics.checkNotNullParameter(color2, "color1v");
                        Intrinsics.checkNotNullParameter(color3, "color2v");
                        windowFramebuffer = this.U;
                        if (bl4) break block2;
                        if (windowFramebuffer == null) break block3;
                        shaderFramebuffers = this;
                        if (bl4) break block4;
                        windowFramebuffer = shaderFramebuffers.O;
                    }
                    if (windowFramebuffer != null) break block5;
                }
                return;
            }
            this.active = true;
            WindowFramebuffer windowFramebuffer = this.U;
            Intrinsics.checkNotNull(windowFramebuffer);
            WindowFramebuffer windowFramebuffer2 = this.O;
            Intrinsics.checkNotNull(windowFramebuffer2);
            HandChams.INSTANCE.d(shaderMode, color, windowFramebuffer, windowFramebuffer2, bl, bl2, f, f2, f3, f4, n2, color2, color3, n3, l, f5, f6, f7, f8, f9, f10);
            shaderFramebuffers = this;
        }
        shaderFramebuffers.active = false;
    }

    private final void ensureFramebuffer(char c, short s) {
        block15: {
            block14: {
                int n;
                int n2;
                block13: {
                    CallSite callSite;
                    block12: {
                        block11: {
                            int n3;
                            int n4;
                            block10: {
                                int n5 = 0;
                                long l = ((long)c << 48 | (long)s << 48 >>> 16 | (long)n5 << 32 >>> 32) ^ a;
                                callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8923969018190035127L, (long)l);
                                Framebuffer framebuffer = this.U;
                                if (callSite == false) {
                                    if (framebuffer == null) {
                                        this.U = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                                    }
                                    framebuffer = Globals.getMc().getFramebuffer();
                                }
                                n4 = framebuffer.textureWidth;
                                WindowFramebuffer windowFramebuffer = this.U;
                                Intrinsics.checkNotNull(windowFramebuffer);
                                n3 = windowFramebuffer.textureWidth;
                                if (s < 0 || callSite != false) break block10;
                                if (n4 != n3) break block11;
                                n4 = Globals.getMc().getFramebuffer().textureHeight;
                                WindowFramebuffer windowFramebuffer2 = this.U;
                                Intrinsics.checkNotNull(windowFramebuffer2);
                                n3 = windowFramebuffer2.textureHeight;
                            }
                            if (n4 == n3) break block12;
                        }
                        WindowFramebuffer windowFramebuffer = this.U;
                        Intrinsics.checkNotNull(windowFramebuffer);
                        windowFramebuffer.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                    }
                    CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                    WindowFramebuffer windowFramebuffer = this.U;
                    Intrinsics.checkNotNull(windowFramebuffer);
                    GpuTexture gpuTexture = windowFramebuffer.getColorAttachment();
                    Intrinsics.checkNotNull(gpuTexture);
                    WindowFramebuffer windowFramebuffer3 = this.U;
                    Intrinsics.checkNotNull(windowFramebuffer3);
                    GpuTexture gpuTexture2 = windowFramebuffer3.getDepthAttachment();
                    Intrinsics.checkNotNull(gpuTexture2);
                    commandEncoder.clearColorAndDepthTextures(gpuTexture, 0, gpuTexture2, 1.0);
                    Framebuffer framebuffer = this.O;
                    if (c >= '\u0000' && callSite == false) {
                        if (framebuffer == null) {
                            this.O = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                        }
                        framebuffer = Globals.getMc().getFramebuffer();
                    }
                    n2 = framebuffer.textureWidth;
                    WindowFramebuffer windowFramebuffer4 = this.O;
                    Intrinsics.checkNotNull(windowFramebuffer4);
                    n = windowFramebuffer4.textureWidth;
                    if (c < '\u0000' || callSite != false) break block13;
                    if (n2 != n) break block14;
                    n2 = Globals.getMc().getFramebuffer().textureHeight;
                    WindowFramebuffer windowFramebuffer5 = this.O;
                    Intrinsics.checkNotNull(windowFramebuffer5);
                    n = windowFramebuffer5.textureHeight;
                }
                if (n2 == n) break block15;
            }
            WindowFramebuffer windowFramebuffer = this.O;
            Intrinsics.checkNotNull(windowFramebuffer);
            windowFramebuffer.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        WindowFramebuffer windowFramebuffer = this.O;
        Intrinsics.checkNotNull(windowFramebuffer);
        GpuTexture gpuTexture = windowFramebuffer.getColorAttachment();
        Intrinsics.checkNotNull(gpuTexture);
        WindowFramebuffer windowFramebuffer6 = this.O;
        Intrinsics.checkNotNull(windowFramebuffer6);
        GpuTexture gpuTexture3 = windowFramebuffer6.getDepthAttachment();
        Intrinsics.checkNotNull(gpuTexture3);
        commandEncoder.clearColorAndDepthTextures(gpuTexture, 0, gpuTexture3, 1.0);
    }

    private final void renderEntities(MatrixStack matrixStack, boolean bl, int n, int n2) {
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        double d = RenderState.INSTANCE.getTickDelta();
        Boolean bl2 = Globals.getMc().options.getEntityShadows().getValue();
        Intrinsics.checkNotNullExpressionValue(bl2, "get(...)");
        boolean bl3 = bl2;
        Globals.getMc().options.getEntityShadows().setValue(false);
        GofraState.INSTANCE.setModifyBuffer(true);
        RenderSystem.backupProjectionMatrix();
        GameRenderer gameRenderer = Globals.getMc().gameRenderer;
        Intrinsics.checkNotNull(gameRenderer, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
        RenderSystem.setProjectionMatrix(((GameRendererAccessor)((Object)gameRenderer)).getLevelProjectionMatrixBuffer().set(RenderState.INSTANCE.getProjection()), RenderSystem.getProjectionType());
        Iterable<Entity> iterable = Globals.getWorld().getEntities();
        Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
        Object object = iterable;
        Iterable<Entity> iterable2 = object;
        Collection collection = new ArrayList();
        for (Entity entity : iterable2) {
            Entity entity2 = entity;
            if (!(Math.hypot(camera2.getCameraPos().x - entity2.getX(), camera2.getCameraPos().z - entity2.getZ()) < (double)n2 && Math.abs(camera2.getCameraPos().y - entity2.getY()) < (double)n)) continue;
            collection.add(entity);
        }
        for (Object e : (List)collection) {
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            object = (Entity)e;
            if (!Globals.getMc().getEntityRenderDispatcher().shouldRender(object, RenderState.INSTANCE.getFrustum(), camera2.getCameraPos().x, camera2.getCameraPos().y, camera2.getCameraPos().z) || Intrinsics.areEqual(object, Globals.getMc().player) && Globals.getMc().options.getPerspective().isFirstPerson() || !(object instanceof PlayerEntity) && !bl || !(object instanceof EndCrystalEntity) && bl) continue;
            double d2 = MathHelper.lerp(d, ((Entity)object).lastRenderX, ((Entity)object).getX()) - camera2.getCameraPos().x;
            double d3 = MathHelper.lerp(d, ((Entity)object).lastRenderY, ((Entity)object).getY()) - camera2.getCameraPos().y;
            double d4 = MathHelper.lerp(d, ((Entity)object).lastRenderZ, ((Entity)object).getZ()) - camera2.getCameraPos().z;
            boolean bl4 = ((Entity)object).isInvisible();
            boolean bl5 = ((Entity)object).isGlowingLocal();
            ((Entity)object).setInvisible(false);
            ((Entity)object).setGlowing(false);
            try {
                CameraRenderState cameraRenderState;
                Object object2;
                Intrinsics.checkNotNullExpressionValue(Globals.getMc().getEntityRenderDispatcher().getRenderer(object).getAndUpdateRenderState(object, RenderState.INSTANCE.getTickDelta()), "createRenderState(...)");
                ((EntityRenderState)object2).outlineColor = 0;
                GameRenderer gameRenderer2 = Globals.getMc().gameRenderer;
                Intrinsics.checkNotNull(gameRenderer2, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
                Intrinsics.checkNotNullExpressionValue(((GameRendererAccessor)((Object)gameRenderer2)).getLevelRenderState().cameraRenderState, "cameraRenderState");
                Globals.getMc().getEntityRenderDispatcher().render(object2, cameraRenderState, d2, d3, d4, matrixStack, Globals.getMc().gameRenderer.getEntityRenderCommandQueue());
            }
            catch (Exception exception) {}
            ((Entity)object).setInvisible(bl4);
            ((Entity)object).setGlowing(bl5);
        }
        Globals.getMc().gameRenderer.getEntityRenderDispatcher().render();
        Globals.getMc().getBufferBuilders().getEntityVertexConsumers().draw();
        Globals.getMc().gameRenderer.getEntityRenderCommandQueue().clear();
        GofraState.INSTANCE.setModifyBuffer(false);
        Globals.getMc().options.getEntityShadows().setValue(bl3);
        RenderSystem.restoreProjectionMatrix();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                ShaderFramebuffers.a = ZkmKeys.a(-6069123381226583021L, -4246976263572382941L, MethodHandles.lookup().lookupClass()).a(263357040169584L);
                ShaderFramebuffers.d = new HashMap<K, V>(13);
                var0 = ShaderFramebuffers.a ^ 29976398159775L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[13];
                var7_4 = 0;
                var6_5 = "U\u00b7B\u00c0}\u00db\u00d2\u0098\u0002w\u00ed\u00d2\u00e0\u00a1\u00d8;\u00da\u00c2D/m$gb_E\u0015\u0013\u00d7\u00bd\u0096\u00b5w\u008c\u00f0\u00b5W\u0095\u000e\u00f9&2P$\u00c1\u009e\u00e5\u00bc\u0088\u00ac}\u00f1gNI\u00b4\u00bc\u000b\u00d2rd\u00ady\u0096\u00eek\u0018Q1\u00c0Q\u00c6\u00d9cKh\u007ff\u00ee\u00fcx\u000e+\u00da\u00dd\u00b6l)\u00a4\u0083\u00d8\u00861\u0085\u00b5\u0014\u00de\u00a9\u00ce\u009fSd\u00c3B\u0084P\u00e9\u00aft\u00d1->\u000bF\u00cfz\u0097j\u00aa \u00cd\u00c61/\u0005\u00a0d\u0089\u00cb\u00c4\u009dT\u0080\u00bc\u0091\u00feuf7Z\u008e\u001a^.\u00b1\u00e3\u00be\u00a0l\u0099\u00ce^\u00b4d\u000b\u001eN\u00e7\u0011\u00e8a\u0082\u0099\u00e9\u00fc\u00d8-\u00cc\u00850\u00ae(&*ww\u00ec\u00cd\u00a5\u00d8\u0017\u00b6\u00a9\u00bb\u00d1(\u009b\u00bd\u00890\u00ff\u0094\u0094}\u00c5\u00c9\u00c1\u009c\u00b5\u000b[\u00eb\u00cb:V-\u00dcZ\n{\u00ffL\u00c4\u009a\u00c0h\u00ccf\u00ae$\u00ea=\u001cO\u00d7\u00d2\u0010\u0093M\u00f7\u00cb\u0090\u0085\u00d9\u00f2!]p\u00ea\fy\u00ac\u008a\u0088J\u00e4MOs\u0019\u0015\u00db\u0016\u00c4\r\u009b\u001c\u00f1\u00d8U$9\u00fb\u0098O\u00f4>\u00c1c\u00171\u0089\u00cf\u008b\u00d5\u009dB\u00d2Q\u00da\\\u0017\u0018n\u00dc`\u0097\u00a8\u0092\u00eeg\u008e\u00b8\u0098\u00e9$\u00ce\u00d7k6Tk\u00c9O&\u009aM{\u00d6[F*W!\u00a3w)\n\u00af+\u00df\u008b\u00bf\u00e4\u00ab\u009a}(\u00bf\u00b6\u0080vb\u0081\u00ae\u0090f\u00d6\u00dd\u00c8\u00c3\u00b1\u0094\u00ec\u00d3\u008a\u00ab\u0016\u00fe\u00fc\u00b0\u00a7\u00bb\u00ae\u00850u\u00f0.0;\u001f[\u00d5\u00b7>\u00c0\u00c2\u008a\u0005T\u001d\u00a9o8Ha\u0018[6(T\u00bc\u00b6\u00e3\u00e5\u0005\u00b9\u00b6d\u00b3r\u00ef\u00a3\u0019\u009c\u0092\r\u0011\u0019-$\u0018\u00ed\u0017cu\u00fd \u008d\u00eb\u00c7\u00c6g\u0003\u00c9\u00e8\u00e41S\u00d7\u0010\u00ae\u0006\u00ff\u00e9\u00ce\\\u00b0`\u00e4\u0086b\u00d0\u0097\u00e8\u001e%\u0010\u00af\u00a2\u0001\u00af\u00f1\u00c3\u00e7\u00dd**\u00b5@\u00c1\u00a1O\u0007 \\\u0090\u00c3\u00e9\u00eb\u00e0\u0095D\u00ce\u00b9\u00c6#\u00951\u00c0\u00c1\u0092\u009c\u00eeu\u00fa\u007fc\u00c2\u00b1'\u00b1e\u0096\u00c2\u00f7\u00a9 \u00f0\u001c\u00f6\u001e%\b9\u0099)\u00b5P\u00de\u00a1\u00e9\u00f6\u00b0YH\u00ed\u00ab\u00f4\u0007\u008f\t\u001b\u00a7a\r!+\t\u0001(\u00e8b\u009a\u0096\u0017p\u00cc\u00a0\u0006H\u00dc\u00a7\u00f5\u00d8\u008d\u00fb\u00d2\u001f\u0093\u00e4\u00b6&\u0081\u00db\nA\u00a4\u00a9I\u00f7\u00f5\u0085\u00b5\"\u00ca\u00ce\u009am\r\u00ca";
                var8_6 = 562;
                var5_7 = 48;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = ShaderFramebuffers.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u0090\u00e4Np\u00a2\u0085$\u0089\f\u00a5@\u00e8\u00f3V/\u00d1\u0010\u00f7\u00be{\u001eGgA;\u0001\u0013\u009a\u0084U\u00c1u\u00b7";
                    var8_6 = 33;
                    var5_7 = 16;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = ShaderFramebuffers.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        ShaderFramebuffers.b = var9_3;
        ShaderFramebuffers.c = new String[13];
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3FA1;
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
                throw new RuntimeException("su/catlean/iv", exception);
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
            ShaderFramebuffers.c[n2] = ShaderFramebuffers.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ShaderFramebuffers.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/iv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ShaderFramebuffers.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

