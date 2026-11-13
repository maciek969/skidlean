/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix4f
 */
package su.catlean.ui.hud;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
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
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import su.catlean.api.event.GofraState;
import su.catlean.mixins.accessors.GameRendererAccessor;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudElement;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class InventoryWidget
extends HudElement {
    @NotNull
    public static final InventoryWidget b;
    @Nullable
    private static WindowFramebuffer windowFramebuffer;
    private static boolean I;
    private static long c;
    private static final long f;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;
    private static final long[] k;
    private static final Integer[] l;
    private static final Map m;
    private static final long n;

    private InventoryWidget(long l) {
        long l2 = (l = f ^ l) ^ 0x3FB33BDABA2EL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "inventory-widget", (char)n2);
    }

    @Nullable
    public final WindowFramebuffer getWindowFramebuffer() {
        return windowFramebuffer;
    }

    public final void setWindowFramebuffer(@Nullable WindowFramebuffer windowFramebuffer) {
        InventoryWidget.windowFramebuffer = windowFramebuffer;
    }

    public final boolean a() {
        return I;
    }

    public final void b(boolean bl) {
        I = bl;
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        block10: {
            boolean bl;
            long l2;
            long l3;
            int n;
            int n2;
            int n3;
            block13: {
                block12: {
                    int n4;
                    int n5;
                    block11: {
                        long l4 = l;
                        long l5 = l4 ^ 0x5EAEB98FBCE1L;
                        n3 = (int)(l5 >>> 48);
                        n2 = (int)(l5 << 16 >>> 32);
                        n = (int)(l5 << 48 >>> 48);
                        l3 = l4 ^ 0x74C22041CFCEL;
                        l2 = l4 ^ 0x1522A9F4B09FL;
                        boolean bl2 = ArrayListEntry.u();
                        Intrinsics.checkNotNullParameter(drawContext, "context");
                        bl = bl2;
                        Framebuffer framebuffer = windowFramebuffer;
                        if (!bl) {
                            if (framebuffer == null) {
                                windowFramebuffer = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                            }
                            framebuffer = Globals.getMc().getFramebuffer();
                        }
                        n5 = framebuffer.textureWidth;
                        WindowFramebuffer windowFramebuffer = InventoryWidget.windowFramebuffer;
                        Intrinsics.checkNotNull(windowFramebuffer);
                        n4 = windowFramebuffer.textureWidth;
                        if (bl) break block11;
                        if (n5 != n4) break block12;
                        n5 = Globals.getMc().getFramebuffer().textureHeight;
                        WindowFramebuffer windowFramebuffer2 = InventoryWidget.windowFramebuffer;
                        Intrinsics.checkNotNull(windowFramebuffer2);
                        n4 = windowFramebuffer2.textureHeight;
                    }
                    if (n5 == n4) break block13;
                }
                WindowFramebuffer windowFramebuffer = InventoryWidget.windowFramebuffer;
                Intrinsics.checkNotNull(windowFramebuffer);
                windowFramebuffer.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
            }
            String string = "Inventory";
            float f = this.getX();
            HudElement hudElement = this;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            HudElement.drawBackground$default(hudElement, matrix3x2fStack, f, (short)n3, n2, this.getY(), 145.0f, 13.0f, (short)n, 0.0f, false, 96, null);
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            Color[] colorArray = new Color[]{new Color(0x44FFFFFF, true)};
            RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f + 14.0f, this.getY() + 3.0f, 0.5f, 7.5f, colorArray, l2);
            HudElement hudElement2 = this;
            RenderTarget renderTarget = this.getRenderTarget();
            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
            HudElement.queueImage$default(hudElement2, renderTarget, matrix3x2fStack3, f + 2.0f, this.getY(), 10.0f, 10.0f, 0.0f, null);
            this.drawString(drawContext, string, f + 18.0f, l3, this.getY() + 1.0f, Theme.INSTANCE.getTextColor());
            int n6 = 9;
            int n7 = 3;
            int n8 = 0;
            block0: while (true) {
                int n9 = n8;
                int n10 = n7;
                block1: while (n9 < n10) {
                    int n11 = n8;
                    float f2 = b.getY() + (float)(n11 * 17) + 14.0f;
                    HudElement hudElement3 = b;
                    Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
                    HudElement.drawBackground$default(hudElement3, matrix3x2fStack4, f, (short)n3, n2, f2, 145.0f, 16.0f, (short)n, 0.0f, false, 96, null);
                    int n12 = 9;
                    if (bl) break block10;
                    for (int i = 0; i < n12; ++i) {
                        boolean bl3;
                        int n13;
                        int n14;
                        n9 = n14 = i;
                        n10 = 8;
                        if (bl) continue block1;
                        if (!bl) {
                            if (n9 < n10) {
                                HudElement hudElement4 = b;
                                Matrix3x2fStack matrix3x2fStack5 = drawContext.getMatrices();
                                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack5, "pose(...)");
                                HudElement.queueShadow$default(hudElement4, matrix3x2fStack5, f + 16.0f + (float)(n14 * 16), f2 + 4.0f, 0.0f, null);
                            }
                            n13 = n6;
                            bl3 = true;
                        }
                        n6 = n13 + bl3;
                        if (!bl) continue;
                    }
                    ++n8;
                    if (!bl) continue block0;
                }
                break;
            }
            this.drawSwapAnimation(drawContext, f);
            this.setSize(145.0f, 70.0f);
        }
    }

    @Override
    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        long l2 = l;
        long l3 = l2 ^ 0x581641834753L;
        long l4 = l2 ^ 0x5A702B75080CL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        this.renderFramebuffer(l4);
        this.drawSlots(drawContext, l3, this.getX());
    }

    /*
     * Unable to fully structure code
     */
    private final void drawSwapAnimation(DrawContext var1_1, float var2_2) {
        block8: {
            var24_3 = 0L;
            var24_3 = InventoryWidget.f ^ var24_3;
            var11_4 = ArrayListEntry.u();
            cfr_temp_0 = Globals.now() - InventoryWidget.c - InventoryWidget.n;
            v0 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
            if (!var11_4) {
                if (v0 < 0) {
                    v0 = (long)Intrinsics.areEqual(HudManager.INSTANCE.getDraggedElement(), this);
                    if (!var11_4) {
                        if (v0 == false) {
                            return;
                        } else {
                            ** GOTO lbl-1000
                        }
                    }
                } else lbl-1000:
                // 3 sources

                {
                    InventoryWidget.c = Globals.now();
                    GofraState.INSTANCE.setModifyBuffer(true);
                    v0 = 1;
                }
            }
            InventoryWidget.I = v0;
            v1 = RenderSystem.getDevice().createCommandEncoder();
            v2 = InventoryWidget.windowFramebuffer;
            Intrinsics.checkNotNull(v2);
            v3 = v2.getColorAttachment();
            Intrinsics.checkNotNull(v3);
            v4 = InventoryWidget.windowFramebuffer;
            Intrinsics.checkNotNull(v4);
            v5 = v4.getDepthAttachment();
            Intrinsics.checkNotNull(v5);
            v1.clearColorAndDepthTextures(v3, 0, v5, 1.0);
            7939000931965772181L ^ var24_3;
            var12_5 = new MatrixStack();
            RenderSystem.backupProjectionMatrix();
            v6 = Globals.getMc().gameRenderer;
            Intrinsics.checkNotNull(v6, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
            RenderSystem.setProjectionMatrix(((GameRendererAccessor)v6).getLevelProjectionMatrixBuffer().set(new Matrix4f().identity().setOrtho(0.0f, (float)Globals.getMc().getWindow().getScaledWidth(), (float)Globals.getMc().getWindow().getScaledHeight(), 0.0f, -160.0f, 160.0f)), RenderSystem.getProjectionType());
            Globals.getMc().gameRenderer.getDiffuseLighting().setShaderLights(DiffuseLighting.Type.ITEMS_FLAT);
            var13_6 = 9;
            var14_7 = 3;
            var15_8 = 0;
            while (var15_8 < var14_7) {
                block9: {
                    var16_9 = var15_8;
                    var18_10 = InventoryWidget.b.getY() + (float)(var16_9 * 17) + 14.0f;
                    var19_11 = 9;
                    v7 = 0;
                    if (var11_4) break block8;
                    var20_12 = v7;
                    while (var20_12 < var19_11) {
                        var21_13 = var20_12++;
                        Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(var13_6), "getItem(...)");
                        var12_5.push();
                        var12_5.translate(var2_2 + 2.5f + (float)(var21_13 * 16), var18_10 + 2.5f, 0.0f);
                        var12_5.scale(0.75f, 0.75f, 1.0f);
                        RenderUtil.INSTANCE.renderItem(var12_5, var23_14);
                        var12_5.pop();
                        ++var13_6;
                        if (!var11_4) {
                            if (!var11_4) continue;
                        }
                        break block9;
                    }
                    ++var15_8;
                }
                if (!var11_4) continue;
            }
            Globals.getMc().gameRenderer.getEntityRenderDispatcher().render();
            Globals.getMc().getBufferBuilders().getEntityVertexConsumers().draw();
            Globals.getMc().gameRenderer.getEntityRenderCommandQueue().clear();
            RenderSystem.restoreProjectionMatrix();
            v7 = 0;
        }
        InventoryWidget.I = v7;
        GofraState.INSTANCE.setModifyBuffer(false);
    }

    private final void renderFramebuffer(long l) {
        long l2 = (l = f ^ l) ^ 0xB0939075599L;
        float f = Globals.getMc().getWindow().getScaledWidth();
        float f2 = Globals.getMc().getWindow().getScaledHeight();
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 6;
        boolean bl = false;
        int n2 = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        BatchedBuffer batchedBuffer2 = BatchedBuffer.vertex$default(batchedBuffer, 0.0f, f2, 0.0f, null).texture(0.0f, 0.0f);
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, "WHITE");
        batchedBuffer2.color(color);
        BatchedBuffer batchedBuffer3 = BatchedBuffer.vertex$default(batchedBuffer, f, f2, 0.0f, null).texture(1.0f, 0.0f);
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
        batchedBuffer3.color(color2);
        BatchedBuffer batchedBuffer4 = BatchedBuffer.vertex$default(batchedBuffer, f, 0.0f, 0.0f, null).texture(1.0f, 1.0f);
        Color color3 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color3, "WHITE");
        batchedBuffer4.color(color3);
        BatchedBuffer batchedBuffer5 = BatchedBuffer.vertex$default(batchedBuffer, 0.0f, 0.0f, 0.0f, null).texture(0.0f, 1.0f);
        Color color4 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color4, "WHITE");
        batchedBuffer5.color(color4);
        RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultColoredTexture();
        Framebuffer framebuffer = Globals.getMc().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
        WindowFramebuffer windowFramebuffer = InventoryWidget.windowFramebuffer;
        Intrinsics.checkNotNull(windowFramebuffer);
        GpuTextureView gpuTextureView = windowFramebuffer.getColorAttachmentView();
        Intrinsics.checkNotNull(gpuTextureView);
        BatchedBuffer.draw$default(l2, batchedBuffer, renderPipeline, framebuffer, null, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView)), 28, null);
    }

    private final void drawSlots(DrawContext drawContext, long l, float f) {
        block5: {
            long l2 = l = InventoryWidget.f ^ l;
            long l3 = l2 ^ 0x32AF0B58A98L;
            int n = (int)(l3 >>> 32);
            int n2 = (int)(l3 << 32 >>> 48);
            int n3 = (int)(l3 << 48 >>> 48);
            long l4 = l2 ^ 0x468D68C81525L;
            int n4 = 9;
            int n5 = 3;
            boolean bl = ArrayListEntry.u();
            int n6 = 0;
            block0: while (true) {
                int n7 = n6;
                int n8 = n5;
                block1: while (n7 < n8) {
                    int n9 = n6;
                    float f2 = b.getY() + (float)(n9 * 17) + 14.0f;
                    int n10 = 9;
                    if (bl) break block5;
                    for (int i = 0; i < n10; ++i) {
                        boolean bl2;
                        int n11;
                        ItemStack itemStack;
                        int n12 = i;
                        Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(n4), "getItem(...)");
                        n7 = itemStack.getCount();
                        n8 = 1;
                        if (bl) continue block1;
                        if (!bl) {
                            if (n7 > n8) {
                                FontRenderer fontRenderer = Fonts.getSF();
                                Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                                FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, String.valueOf(itemStack.getCount()), f + 2.0f + (float)(n12 * 16) + 4.0f, f2 + 1.5f + 4.0f, l4, Theme.INSTANCE.getTextColor(), false, null);
                            }
                            n11 = n4;
                            bl2 = true;
                        }
                        n4 = n11 + bl2;
                        if (!bl) continue;
                    }
                    ++n6;
                    if (!bl) continue block0;
                }
                break;
            }
            Fonts.getSF().clearCache(n, (char)n2, (short)n3);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            InventoryWidget.f = ZkmKeys.a(-5595675317502684446L, 4192046950317347832L, MethodHandles.lookup().lookupClass()).a(153251678729737L);
                            var25 = InventoryWidget.f ^ 52563323962585L;
                            var27_1 = var25 ^ 82270037423228L;
                            InventoryWidget.i = new HashMap<K, V>(13);
                            var16_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_3 = 1; var17_3 < 8; ++var17_3) {
                                v2 = v2;
                                v2[var17_3] = (byte)(var25 << var17_3 * 8 >>> 56);
                            }
                            var16_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_4 = new String[14];
                            var21_5 = 0;
                            var20_6 = "\u00ad\u00eav\u0019+\u00c7|_,\u0090\u000bq6\u00dd\u00e3]\u0010\u0012\u00fa\u00e6\u00fa\u00a1\u00e8\\w]\u0096\u0014\u00c3\u00f3\u0005p\u00ec\u0018\u00f9'\u00d0d\"'T\u009e\u0002Dv\u007f\u00bc\u00af\u008eQ\u00fa\u001b\u00c7\u0017u\u00da\u0088L \u001aE\u00d4Bj\u00d7.+\u00bbE\u008e\u00800\u00ed\u00d7\u0014\u00bd\u00c9\u00a3\\\u000ej\u00cb\u009a]7\u00f0g:\u000f\u0083X \u00c4\u00bf5x\u00ebFx\u00fd\u00bb\u0095\u00b9\u00c3$X\u00fb\u0087H1\u00b1\u0014\u0013\u00c7\u00aa\u00c9\u0095\u0006\u0011\u00c1Y\u00de\u00c5\u00f6 1\u00c5\u00bdu\u0090\u00ffU\u0087\u0098q\u0087wb\u00efe\u00faT\u00c6p\r\u0011|\u00ae\u00e6\u0095V\u00f3^>\u00d9\u00f5s \u0087\u0096\u00d1\u00d3\u00c6d\u00b0\u00a2#\u00c0u\u00f2\u008ad%\u001bY\u00feR;iY\u00b6\u0017\u0089q\u0084\u00b4\u00cceV\u00a7\u0090/\u000f\u00a0\u00c6e,9\u00e5k\u001f\u00a1\u00d4b\u00f4\u00fb\u0004?\u00a3\u00c3\u0080\u00af\u009a\u001b\u00d7c\u001fNZAR,\u0081\u0080)\u00ed\u0097\u0096\u00c4\u00b4\u00a7:(5\u00d6\u00daRu\u00cdu$\u00eb\u009a4\u00e6\u00d7\u001b\u00c4\t\u00a8\u00f6\u0082H@\u00df\u0084j6IIf\u00bd\u00e2\u00b5\u00f2\u00e9\u00de\u00c2\u00f7\u00a8\u00f7\u00dd=\u00feD\u00b3q\u0096\u008f\u0092\u001d\u00a7\u0086\u0083+\u00d6)\u00ccz5x`!\u00da\u001b\u0019\u00f7\u00a5,\u001bSW-\u0093\u00d9b\u00b2\u008e\u008a\u00076I\u00f2\u00009S\u008c\u00f0\u00d4\f\u0094\u009d\u00bbJ\u00b7\u00a6\u001d\t\u0091*\u0017dNS((\u00e5\u00d3\u00a4Gz\u00ca\u00a21\u00dc\u00c2\u00f1I\u00b5\u00e2\u00ca#\u00d1V?\u00e1\u0097#\u00f7\u00a4\u00f8\u00c1eZ!\u00b3\u00f2R\u0083\u00a5\u009c\u0092\u001d<F\u00b3\u0010 0\u00ee,\u0012\u0086\u00a3\u00c1l\u00be\u00a1\u00b7\u00ee\u00c1\t\u00cd \u0081vf\u0018\u0093\u000el\u0003\u00db\u0099\u00ea\u00f7u\u00a6\u00f2\f\u0004xY\u00abf\u0097\u0011\u0092\u001eO\u00d0\u00c5. :8\u0010J\u0084\u0082\u00f9\u00ad\u00f8{\u00bbr\u00bf=\u00aa\u00c3\u0082\u008d\u00c1";
                            var22_7 = 443;
                            var19_8 = 16;
                            var18_9 = -1;
lbl22:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_9;
                                v4 = var20_6.substring(v3, v3 + var19_8);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl27:
                            // 1 sources

                            while (true) {
                                var23_4[var21_5++] = InventoryWidget.zkmDecodeUtf8(var24_10).intern();
                                if ((var18_9 += var19_8) < var22_7) {
                                    var19_8 = var20_6.charAt(var18_9);
                                    ** continue;
                                }
                                var20_6 = "\u0081\u0001\u00a2K\u000b\u0002$i\u00e1J\u00cf\u00ea\u00db\u00b4sc5\u00b2\u0083}\u00fc\u00f8\u008b\u008f\u00e7\u00af\r\u00ca\u00fb\u00b7\u00ebK\u00b1L\u00b1'\u00fd\u00a77\u000f0O\u00a3Rx\u00ff\\\u00f4*\u0003$\u0095\u00ea\u0081\u00c9\u001c|T\u00d8x\u008f\u00b7\u00d3+\u00f7\u00f2?8\u000fc\u00c0x\u007fEA\u00cbU4\u0005\u00a4\u00e1\u00cc )\u0014\u00b4\u00b5A?";
                                var22_7 = 89;
                                var19_8 = 40;
                                var18_9 = -1;
lbl36:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_9;
                                    v4 = var20_6.substring(v6, v6 + var19_8);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl41:
                            // 1 sources

                            while (true) {
                                var23_4[var21_5++] = InventoryWidget.zkmDecodeUtf8(var24_10).intern();
                                if ((var18_9 += var19_8) < var22_7) {
                                    var19_8 = var20_6.charAt(var18_9);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_10 = var16_2.doFinal(v4.getBytes("ISO-8859-1"));
                        switch (v5) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl53:
                            // 1 sources

                            ** continue;
                        }
                    }
                    InventoryWidget.g = var23_4;
                    InventoryWidget.h = new String[14];
                    InventoryWidget.m = new HashMap<K, V>(13);
                    var5_11 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_12 = 1; var6_12 < 8; ++var6_12) {
                        v9 = v9;
                        v9[var6_12] = (byte)(var25 << var6_12 * 8 >>> 56);
                    }
                    var5_11.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_13 = new long[16];
                    var8_14 = 0;
                    var9_15 = "i=\u00ab\u0096\u00acD\u00a55-\u00c4f\u00e3\u0018\u0084\u0091Jd\u001b\r1Q\u00bd\u008cM\u0090y\u008d\u00f9^|;\u00de_\u00ef\u0000T\u00da8V\u0090+\u00fdc\u008d\u00f49)8\u009a\u0092\u00c3\u0007\u00c4_\u009d\u0086h\u0002Z\u0080\u00ba\u00f3\u001b\u00ae\u00cd=gB^\u00bb\u00a9\u00f6\u009f\u00acY\u00f7g\u00a0\u0019M\u0089^\u0085\u007f\u00ec\u00af\u008c\u0005\u00d5\u00b2\u0089\u00c9 \u00bec\u0096\u00d5\u0004f\u00ea\u0017\u00a0o{\u000b\u0083Gz\u00dc\\]#";
                    var10_16 = 112;
                    var7_17 = 0;
                    while (true) {
                        var12_18 = var9_15.substring(var7_17, var7_17 += 8).getBytes("ISO-8859-1");
                        v10 = var11_13;
                        v11 = var8_14++;
                        v12 = ((long)var12_18[0] & 255L) << 56 | ((long)var12_18[1] & 255L) << 48 | ((long)var12_18[2] & 255L) << 40 | ((long)var12_18[3] & 255L) << 32 | ((long)var12_18[4] & 255L) << 24 | ((long)var12_18[5] & 255L) << 16 | ((long)var12_18[6] & 255L) << 8 | (long)var12_18[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl80:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_17 < var10_16) ** continue;
                        var9_15 = "[5Q\u00da\u00e4X\u0098d3\u00c8\u009d\u00dd\u0016\u00ad\u00a9\u009f";
                        var10_16 = 16;
                        var7_17 = 0;
                        while (true) {
                            var12_18 = var9_15.substring(var7_17, var7_17 += 8).getBytes("ISO-8859-1");
                            v10 = var11_13;
                            v11 = var8_14++;
                            v12 = ((long)var12_18[0] & 255L) << 56 | ((long)var12_18[1] & 255L) << 48 | ((long)var12_18[2] & 255L) << 40 | ((long)var12_18[3] & 255L) << 32 | ((long)var12_18[4] & 255L) << 24 | ((long)var12_18[5] & 255L) << 16 | ((long)var12_18[6] & 255L) << 8 | (long)var12_18[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl93:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_17 < var10_16) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_19 = v12;
                var15_20 = var5_11.doFinal(new byte[]{(byte)(var13_19 >>> 56), (byte)(var13_19 >>> 48), (byte)(var13_19 >>> 40), (byte)(var13_19 >>> 32), (byte)(var13_19 >>> 24), (byte)(var13_19 >>> 16), (byte)(var13_19 >>> 8), (byte)var13_19});
                v14 = ((long)var15_20[0] & 255L) << 56 | ((long)var15_20[1] & 255L) << 48 | ((long)var15_20[2] & 255L) << 40 | ((long)var15_20[3] & 255L) << 32 | ((long)var15_20[4] & 255L) << 24 | ((long)var15_20[5] & 255L) << 16 | ((long)var15_20[6] & 255L) << 8 | (long)var15_20[7] & 255L;
                switch (v13) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl106:
                    // 1 sources

                    ** continue;
                }
            }
            InventoryWidget.k = var11_13;
            InventoryWidget.l = new Integer[16];
            var0_21 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_22 = 1; var1_22 < 8; ++var1_22) {
                v17 = v17;
                v17[var1_22] = (byte)(var25 << var1_22 * 8 >>> 56);
            }
            break block24;
lbl120:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_21.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_23 = var0_21.doFinal(new byte[]{7, -35, 56, -50, -82, -81, 6, 44});
        ** while (true)
        InventoryWidget.n = ((long)var4_23[0] & 255L) << 56 | ((long)var4_23[1] & 255L) << 48 | ((long)var4_23[2] & 255L) << 40 | ((long)var4_23[3] & 255L) << 32 | ((long)var4_23[4] & 255L) << 24 | ((long)var4_23[5] & 255L) << 16 | ((long)var4_23[6] & 255L) << 8 | (long)var4_23[7] & 255L;
        InventoryWidget.b = new InventoryWidget(var27_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3ACA;
        if (h[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ps", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = g[n2].getBytes("ISO-8859-1");
            InventoryWidget.h[n2] = InventoryWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = InventoryWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ps" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x53D0;
        if (InventoryWidget.l[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = k[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])m.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    m.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ps", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            InventoryWidget.l[n2] = n3;
        }
        return InventoryWidget.l[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = InventoryWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ps" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(InventoryWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(InventoryWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

