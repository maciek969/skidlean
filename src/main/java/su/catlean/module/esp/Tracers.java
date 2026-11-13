/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Quaternionfc
 */
package su.catlean.module.esp;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.esp.TracersMode;
import su.catlean.render.GlowTexture;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Animation;
import su.catlean.util.Async;
import su.catlean.util.Easing;
import su.catlean.util.FpsCounter;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Tracers
extends Module {
    @NotNull
    public static final Tracers INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BooleanSetting flat$delegate;
    @NotNull
    private static final BooleanSetting onlyOffscreen$delegate;
    @NotNull
    private static final ColorSetting friendColor$delegate;
    @NotNull
    private static final BooleanSetting teamColor$delegate;
    private static float smoothedYaw;
    @NotNull
    private static Animation pitchAnimation;
    @NotNull
    private static Animation lengthAnimation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private Tracers() {
        super("tracers", Categories.getEsp(), null, null);
    }

    @NotNull
    public final TracersMode getMode() {
        return (TracersMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getFlat() {
        return (Boolean)flat$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getOnlyOffscreen() {
        return (Boolean)onlyOffscreen$delegate.getValue(this, delegatedProperties[2]);
    }

    private final Color getFriendColor() {
        return (Color)friendColor$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getTeamColor() {
        return (Boolean)teamColor$delegate.getValue(this, delegatedProperties[4]);
    }

    @Flow
    public final void onRender2D(@NotNull Render2DEvent render2DEvent) {
        long l = a ^ 0xF64C7BAD872L;
        long l2 = l ^ 0x3E1C9B36D579L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 40);
        int n3 = (int)(l2 << 56 >>> 56);
        Intrinsics.checkNotNullParameter(render2DEvent, "e");
        if (this.getMode() == TracersMode.LINES) {
            return;
        }
        boolean bl = Globals.getMc().currentScreen != null && !(Globals.getMc().currentScreen instanceof ChatScreen);
        pitchAnimation.animate(this.getFlat() || bl ? 90.0f : 42.0f);
        lengthAnimation.animate(bl ? 150.0f : 40.0f);
        float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f;
        float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f;
        smoothedYaw = this.smooth(smoothedYaw, Globals.getPlayer().getYaw(), 13.0f);
        Iterable iterable = Async.INSTANCE.getTasks();
        int n4 = 0;
        for (Object t : iterable) {
            Color color;
            int n5;
            if ((n5 = n4++) < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
            int n6 = n5;
            if (Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getMc().player) || INSTANCE.getOnlyOffscreen() && RenderState.INSTANCE.getFrustum().isVisible(abstractClientPlayerEntity.getBoundingBox())) continue;
            MatrixStack matrixStack = new MatrixStack();
            matrixStack.push();
            matrixStack.translate(f, f2, 0.0f);
            float f3 = Math.min(90.0f, Math.max(Globals.getPlayer().getPitch(), pitchAnimation.getValue()));
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(Math.abs(f3) - 102.0f));
            matrixStack.translate(-f, -f2, 0.0f);
            double d = RenderUtil.INSTANCE.lerp(abstractClientPlayerEntity.lastX, abstractClientPlayerEntity.getX(), (double)RenderState.INSTANCE.getTickDelta());
            double d2 = RenderUtil.INSTANCE.lerp(abstractClientPlayerEntity.lastZ, abstractClientPlayerEntity.getZ(), (double)RenderState.INSTANCE.getTickDelta());
            double d3 = RenderUtil.INSTANCE.lerp(Globals.getPlayer().lastX, Globals.getPlayer().getX(), (double)RenderState.INSTANCE.getTickDelta());
            double d4 = RenderUtil.INSTANCE.lerp(Globals.getPlayer().lastZ, Globals.getPlayer().getZ(), (double)RenderState.INSTANCE.getTickDelta());
            float f4 = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(d2 - d4, d - d3)) - 90.0) - smoothedYaw;
            matrixStack.translate(f, f2, 0.0f);
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(f4));
            matrixStack.translate(-f, -f2, 0.0f);
            Color color2 = color = Friends.getIsHomie(abstractClientPlayerEntity) ? INSTANCE.getFriendColor() : Theme.INSTANCE.getGradientColor(n6 * 30);
            if (abstractClientPlayerEntity.getScoreboardTeam() != null && INSTANCE.getTeamColor()) {
                color = new Color(abstractClientPlayerEntity.getTeamColorValue());
            }
            INSTANCE.drawTracer(n, n2, matrixStack, f, (byte)n3, f2 - lengthAnimation.getValue(), 18.0f, color);
            matrixStack.pop();
        }
    }

    public final void drawTracer(int n, int n2, @NotNull MatrixStack matrixStack, float f, byte by, float f2, float f3, @NotNull Color color) {
        block0: {
            long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)by << 56 >>> 56) ^ a;
            long l2 = l ^ 0x7A91BBAAED5BL;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)884504647741160890L, (long)l);
            Intrinsics.checkNotNullParameter(matrixStack, (String)((Object)Tracers.zkmBootstrap("j", (int)8949, (long)(0x66A53F4CFBB11E0AL ^ l))));
            CallSite callSite2 = callSite;
            Intrinsics.checkNotNullParameter(color, (String)((Object)Tracers.zkmBootstrap("j", (int)14445, (long)(0x318E7C21AD170490L ^ l))));
            Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f, (String)((Object)Tracers.zkmBootstrap("j", (int)30942, (long)(0x6FA6292EF22EC428L ^ l))));
            Matrix4f matrix4f2 = matrix4f;
            RenderUtil.INSTANCE.drawTexture3D(matrix4f2, GlowTexture.getIdentifier(), f, f2, f3, f3, color, color, color, color, true, this.getMode().getTexture().o(), this.getMode().getTexture().v(), this.getMode().getTexture().L(), l2, this.getMode().getTexture().w());
            if (callSite2 == false) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[4], (long)877224977503421969L, (long)l);
        }
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        int n = TracersMode.zkmIntConstant();
        Intrinsics.checkNotNullParameter(render3DEvent, "e");
        int n2 = n;
        if (this.getMode() != TracersMode.LINES) {
            return;
        }
        for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
            block6: {
                Vec3d vec3d;
                boolean bl;
                Color color;
                Box box;
                block7: {
                    if (Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getPlayer()) && n2 == 0) continue;
                    Intrinsics.checkNotNull(abstractClientPlayerEntity);
                    box = RenderState.INSTANCE.getRenderBox(abstractClientPlayerEntity);
                    Intrinsics.checkNotNullExpressionValue(new Vec3d(0.0, 0.0, 75.0).rotateX(-((float)Math.toRadians(Globals.getMc().gameRenderer.getCamera().getPitch()))).rotateY(-((float)Math.toRadians(Globals.getMc().gameRenderer.getCamera().getYaw()))).add(Globals.getMc().gameRenderer.getCamera().getCameraPos()), "add(...)");
                    Color color2 = color = Friends.getIsHomie(abstractClientPlayerEntity) ? this.getFriendColor() : Theme.INSTANCE.getGradientColor(30);
                    if (n2 != 0) break block6;
                    if (abstractClientPlayerEntity.getScoreboardTeam() == null) break block7;
                    bl = this.getTeamColor();
                    if (n2 != 0) break block6;
                    if (bl) {
                        color = new Color(abstractClientPlayerEntity.getTeamColorValue());
                    }
                }
                Vec3d vec3d2 = box.getCenter();
                Intrinsics.checkNotNullExpressionValue(vec3d2, "getCenter(...)");
                bl = RenderState.addLine$default(RenderState.INSTANCE, vec3d, vec3d2, color, null, true, 8, null);
            }
            if (n2 == 0) continue;
        }
    }

    private final float smooth(float f, float f2, float f3) {
        float f4 = Math.clamp((FpsCounter.INSTANCE.D() > 5 ? 1.0f / (float)FpsCounter.INSTANCE.D() : 0.016f) * f3, 0.0f, 1.0f);
        return (1.0f - f4) * f + f4 * f2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                Tracers.a = ZkmKeys.a(-3758588209181816700L, -6937648329684696947L, MethodHandles.lookup().lookupClass()).a(265804390775478L);
                                var31 = Tracers.a ^ 33993758539278L;
                                Tracers.d = new HashMap<K, V>(13);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[22];
                                var27_4 = 0;
                                var26_5 = "\u00efEG\u00f9\u00b4\t$~U\u0081\u00a3[\u0002\u00a7\u00b6W\u0010\u00ea\u0093\u00e5b\u001c\u00dc`\u00bc\u00fd\n\u008cg\u0099\u00b7'\u0093\u00189(\u00bbHQ\u00bc\u00e8\u00d1.wd\u00c2\u001d\u00ef\u0090\u000b\u0092\u00e8\u001dq\u00e2O\u000exH\u00bb@\u00b4\u00a2@\u0080;\u00d5_P\u0093\u001e\u00c3\u0085\u0085\u00a9z\u00eb\u00a5vp\u00da\u00b6\u00cb\u00f0\u00ca\u00ef\u00c7\u0080\u00d9:V)X\u00e8\u00a2Q\u00a64\u00e9Y\u0013g\u00a34\u0096u\u00b6\u00d4\u00ae D\u00a3\u00fcE\u0093l\u00c9\u00f0\u00c3\u000f\u0096\"wA\u00cd\u00a2\u009b\u00a2\u00ab\u00bf\u00a1\u0010:\u00cf\u00ae}\u00a7\u0015\u00d4\u00ea\u0081\u00fa\r\u009c\u00ba\u0001\u00f1M\u0010\u00a3\u0080\u009e2\u00b5\u001dfI\\\b\u00e9\u00e8\u00c5\u001f\u0087\u0011\u0010\u00a8\u00c6R\u009b\u00a0\u00a75\u00c0`\u00aa\u0016kL\u0017hR {\u00c7yf\\\u0011PY\u00ff\u00a9\u00f3I\u00d0\u00a9\u00d3#c\u00a7\u00bb\u00ed&\u00dc\u0019\u00fb\u00979l\u0084\f\u00ecD) \u00a1\u00a7\u00cfd\u0015L\u0013A\u00fe(bR\u00abV\u00cb\u00c4\u007f\u0014\u00f1\u0007/\u00f2-\r\u00c2\u000b\u0006\u008f\u00c3\u00fdD\u00ba \u00d9\u0093\u0099[\u001e\u00bc=I\u0081\u00b6[\u00d5\u008b%h3,a!\u00ce\u00ac\u008aX\u00fc\u00b6;V\u0003\u00f8\u0011F\u00b1 \"RV\u00a0\u0011Y}\u0088\u0098\u0005\\\u009b#\u00c0u\u00ec\u00f2\u00bdE\u00a1\u00a1\u0084\u00a0W\u00fad\u0092\u0084\u00ed\u00e13\u001f\u0018,W\n\u00b7\u00df\\\u00d4\u00e2^Q\u00df|\u00922% \u009c!\u00c2\u00c8\u00c3\u009b\u00c5x A\u00c2\u00a1\u00ff\u00f1?8\u00df\u0003\u0098&i\u00b6\u00e3B\u00d9\u00ff\u00e0\u00c1f_p\u00a4\"@\u0087\u00bb\u00b0\u00ca|\u009f\u00ba\u0018\u0095#\u00d6\ne\u009c\n\u009b\u00cf:D+\u0003\u0003\r\u0090\u00a8\u00fe\u00e7\u0092\u00eet\u00ad0\u0010\u00f5C%\u00d2\u00ed`\u00db\u00f4\u009c\u00efx\u00a9\u0092\u0096\u00c1\u00830\u00dd[\u00b3\u00c3b\u00aa^#J|fj+\u00ab\u0005h=\u00919-\n\u00ab\r4\u00b7\u0003\u00d1\u00dc:\u009f\u00d4F\u0003\u00d3\u00f2(\u00e0M\u001e'\u00a1Z\u0000\u00der\u00d3\u00ad, v\u00f3\u0014\u00c8\u0086R\u009f\u000e\u0088\u008e\u000e\u00a1\u0007\u00feA\u00f4\u00aa\u00a5\u00b1`\u00b9\u00f5\u00b4,\u00a9\u00d2mSt`$\u009d\u0010\u00e2e\u00a3m;\u00ff\u00f4\u00bd1z\u00e5{N \u00d1_ \u001dy\u00e7\u00c4/@G(\u0096\u00dd\u008c\u00a7\u0080\"\u0019v\u0001O\u008fDJ\u00b0^\u0081\u0001\u00d9\u00a1i\u0094\u00cb}\u0000(\f\u00997 \u0081\u0005\u009d\b\u009a\u009b#A\u00fc\u00ef\u0095\u009e7?\u00a4\u00bd\u00e2\u00f4\u00e9\u00fd\u00a8n<\u00f9\u00b8R\u00bd\u0000\u00e3\u0085\u000e\fO:\u00a3*";
                                var28_6 = 587;
                                var25_7 = 16;
                                var24_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = Tracers.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u008bm\u0087\u00ab\u00a1\u00b8\u00c0\u00a9\u0082\u00ea\u0091\u001b\u00bfO\u0017o:~\u009f\n\u009c\u0018m\u0002)\u00b2-$%Y\u00e8\u000e\u0010\u009f\u0096\u001dKr\u00d5\u00ec\u00ff\u00ddJ(\u00f5\fs\u009c\u0016";
                                    var28_6 = 49;
                                    var25_7 = 32;
                                    var24_8 = -1;
lbl34:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl39:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = Tracers.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        Tracers.b = var29_3;
                        Tracers.c = new String[22];
                        Tracers.h = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[6];
                        var14_13 = 0;
                        var15_14 = "\u00954|X8\u009d\u00df\u0099\t\u00b1\u00abr\u00ba\u001d\u00c15,\u00f08\u00f2\u00b9,\u00ec\u00b2\u00a6\u00aa\u0011\u00ff2 !7";
                        var16_15 = 32;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl78:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "6O\u00bc9\u00b3\u00b8\u008bW%3)7n\u0018\u00e0\u00fe";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl91:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                Tracers.e = var17_12;
                Tracers.f = new Integer[6];
                var1_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var2_21 = 1; var2_21 < 8; ++var2_21) {
                    v17 = v17;
                    v17[var2_21] = (byte)(var31 << var2_21 * 8 >>> 56);
                }
                var1_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var0_22 = new long[2];
                var4_23 = 0;
                var5_24 = "<\u00bd\u000eC\u00e3\u00ff\u00ec\u0014<\u00bd\u000eC\u00e3\u00ff\u00ec\u0014";
                var6_25 = 16;
                var3_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl125:
                // 1 sources

                while (true) {
                    var0_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var3_26 < var6_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var5_24.substring(var3_26, var3_26 += 8).getBytes("ISO-8859-1");
            v18 = var4_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var1_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        var44_30 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Tracers.class, "mode", "getMode()Lsu/catlean/sj;", 0)), Reflection.property1(new PropertyReference1Impl(Tracers.class, "flat", "getFlat()Z", 0)), Reflection.property1(new PropertyReference1Impl(Tracers.class, "onlyOffscreen", "getOnlyOffscreen()Z", 0)), Reflection.property1(new PropertyReference1Impl(Tracers.class, "friendColor", "getFriendColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(Tracers.class, "teamColor", "getTeamColor()Z", 0))};
        Tracers.delegatedProperties = var44_30;
        Tracers.INSTANCE = new Tracers();
        Tracers.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Tracers.INSTANCE, "mode", (Enum)TracersMode.ARROWS_1, null, null, 12, null);
        Tracers.flat$delegate = SettingFactory.booleanSetting$default((SettingContainer)Tracers.INSTANCE, "flat", false, null, null, 12, null);
        Tracers.onlyOffscreen$delegate = SettingFactory.booleanSetting$default((SettingContainer)Tracers.INSTANCE, "only-off-screen", false, null, null, 12, null);
        v19 = Tracers.INSTANCE;
        v20 = Color.GREEN;
        Intrinsics.checkNotNullExpressionValue(v20, "GREEN");
        Tracers.friendColor$delegate = SettingFactory.colorSetting$default(v19, "friend", v20, null, null, 12, null);
        Tracers.teamColor$delegate = SettingFactory.booleanSetting$default((SettingContainer)Tracers.INSTANCE, "team-colors", false, null, null, 12, null);
        Tracers.pitchAnimation = new Animation(Easing.OUT_QUINT, var0_22[1]);
        Tracers.lengthAnimation = new Animation(Easing.OUT_QUINT, var0_22[0]);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x31F6;
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
                throw new RuntimeException("su/catlean/kv", exception);
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
            Tracers.c[n2] = Tracers.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Tracers.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5028;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/kv", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Tracers.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Tracers.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Tracers.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Tracers.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

