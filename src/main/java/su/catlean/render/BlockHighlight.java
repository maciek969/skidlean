/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

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
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.AnimationStyle;
import su.catlean.render.BlockHighlight$WhenMappings;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.ShapeRenderMode;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BlockHighlight {
    @NotNull
    private final BlockPos pos;
    @NotNull
    private final Color lineColor;
    @NotNull
    private final Color fillColor;
    @NotNull
    private final AnimationStyle animationMode;
    @NotNull
    private final ShapeRenderMode renderMode;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;
    private static final long[] i;
    private static final Long[] k;
    private static final Map l;

    public BlockHighlight(@NotNull BlockPos blockPos, @NotNull Color color, @NotNull Color color2, @NotNull AnimationStyle animationStyle, @NotNull ShapeRenderMode shapeRenderMode) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)17441, (long)(0x3CC29109D4B822C2L ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)15431, (long)(0x34A8796B4BB9DAB5L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)1740, (long)(0x15AB32127E58E022L ^ l))));
        Intrinsics.checkNotNullParameter((Object)animationStyle, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)4521, (long)(0x1104CF57BCC97746L ^ l))));
        Intrinsics.checkNotNullParameter((Object)shapeRenderMode, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)17559, (long)(0x11240AA9C44FA27CL ^ l))));
        this.pos = blockPos;
        this.lineColor = color;
        this.fillColor = color2;
        this.animationMode = animationStyle;
        this.renderMode = shapeRenderMode;
    }

    @NotNull
    public final BlockPos getPos() {
        return this.pos;
    }

    @NotNull
    public final Color getLineColor() {
        return this.lineColor;
    }

    @NotNull
    public final Color getFillColor() {
        return this.fillColor;
    }

    @NotNull
    public final AnimationStyle getAnimationMode() {
        return this.animationMode;
    }

    @NotNull
    public final ShapeRenderMode getRenderMode() {
        return this.renderMode;
    }

    public final void render(long l, @NotNull MatrixStack matrixStack) {
        Intrinsics.checkNotNullParameter(matrixStack, "stack");
        Box box = new Box(this.pos);
        float f = 1.0f;
        switch (BlockHighlight$WhenMappings.T[this.animationMode.ordinal()]) {
            case 1: {
                break;
            }
            case 2: {
                f = 1.0f - (float)l / 300.0f;
                Box box2 = new Box(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX(), this.pos.getY(), this.pos.getZ()).shrink(f, f, f).offset(0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                Intrinsics.checkNotNullExpressionValue(box2, "move(...)");
                box = box2;
                break;
            }
            case 3: {
                f = 1.0f - (float)l / 300.0f;
                box = new Box(this.pos);
                break;
            }
            case 4: {
                f = (float)l / 300.0f;
                Box box3 = new Box(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX(), this.pos.getY(), this.pos.getZ()).shrink(f, f, f).offset(0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                Intrinsics.checkNotNullExpressionValue(box3, "move(...)");
                box = box3;
                break;
            }
            case 5: {
                f = l > 100L ? 1.0f - (float)(l - 100L) / 400.0f : (float)l / 100.0f;
                Box box4 = new Box(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX(), this.pos.getY(), this.pos.getZ()).shrink(f, f, f).offset(0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                Intrinsics.checkNotNullExpressionValue(box4, "move(...)");
                box = box4;
                break;
            }
            case 6: {
                f = (float)l / 300.0f;
                box = new Box(this.pos.getX(), (float)this.pos.getY() + f, this.pos.getZ(), this.pos.getX() + 1, this.pos.getY(), this.pos.getZ() + 1);
                break;
            }
            case 7: {
                f = l < 200L ? 1.0f : 1.0f + ((float)l - 200.0f) / 400.0f;
                Box box5 = new Box(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX(), this.pos.getY(), this.pos.getZ()).shrink(f, f, f).offset(0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                Intrinsics.checkNotNullExpressionValue(box5, "move(...)");
                box = box5;
                break;
            }
            case 8: {
                f = l < 200L ? 1.5f - (float)l / 200.0f * 0.5f : 1.0f;
                Box box6 = new Box(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX(), this.pos.getY(), this.pos.getZ()).shrink(f, f, f).offset(0.5 + (double)(f * 0.5f), 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                Intrinsics.checkNotNullExpressionValue(box6, "move(...)");
                box = box6;
                break;
            }
            case 9: {
                f = 1.0f + (float)l / 1500.0f;
                Box box7 = new Box(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX(), this.pos.getY(), this.pos.getZ()).shrink(f, f, f).offset(0.5 + (double)(f * 0.5f), 0.5 + (double)f * 0.5, 0.5 + (double)f * 0.5);
                Intrinsics.checkNotNullExpressionValue(box7, "move(...)");
                box = box7;
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        this.drawBox(f, box, this.renderMode, this.lineColor, this.fillColor);
    }

    private final void drawBox(float f, Box box, ShapeRenderMode shapeRenderMode, Color color, Color color2) {
        block12: {
            ShapeRenderMode shapeRenderMode2;
            ShapeRenderMode shapeRenderMode3;
            block10: {
                block11: {
                    block9: {
                        int n;
                        block8: {
                            block6: {
                                block7: {
                                    block5: {
                                        block4: {
                                            n = Easing.O();
                                            shapeRenderMode3 = shapeRenderMode;
                                            shapeRenderMode2 = ShapeRenderMode.BOTH;
                                            if (n != 0) break block4;
                                            if (shapeRenderMode3 == shapeRenderMode2) break block5;
                                            shapeRenderMode3 = shapeRenderMode;
                                            shapeRenderMode2 = ShapeRenderMode.LINE;
                                        }
                                        if (n != 0) break block6;
                                        if (shapeRenderMode3 != shapeRenderMode2) break block7;
                                    }
                                    float f2 = RenderUtil.INSTANCE.getAlpha(color) * f;
                                    Object var25_8 = null;
                                    Color color3 = null;
                                    Color color4 = RenderUtil.INSTANCE.withAlpha(color, f2);
                                    Box box2 = box;
                                    RenderState.addOutlineBox$default(RenderState.INSTANCE, box2, color4, color3, var25_8);
                                }
                                shapeRenderMode3 = shapeRenderMode;
                                shapeRenderMode2 = ShapeRenderMode.BOTH;
                            }
                            if (n != 0) break block8;
                            if (shapeRenderMode3 == shapeRenderMode2) break block9;
                            shapeRenderMode3 = shapeRenderMode;
                            shapeRenderMode2 = ShapeRenderMode.FILL;
                        }
                        if (n != 0) break block10;
                        if (shapeRenderMode3 != shapeRenderMode2) break block11;
                    }
                    float f3 = RenderUtil.INSTANCE.getAlpha(color2) * f;
                    Object var36_13 = null;
                    Color color5 = null;
                    Color color6 = RenderUtil.INSTANCE.withAlpha(color2, f3);
                    Box box3 = box;
                    RenderState renderState = RenderState.INSTANCE;
                    RenderState.addFilledBox$default(renderState, box3, color6, color5, var36_13);
                }
                shapeRenderMode3 = shapeRenderMode;
                shapeRenderMode2 = ShapeRenderMode.SHADER;
            }
            if (shapeRenderMode3 != shapeRenderMode2) break block12;
            float f4 = RenderUtil.INSTANCE.getAlpha(color2) * f;
            float f5 = RenderUtil.INSTANCE.getAlpha(color) * f;
            Color color7 = RenderUtil.INSTANCE.withAlpha(color, f5);
            Color color8 = RenderUtil.INSTANCE.withAlpha(color2, f4);
            Box box4 = box;
            RenderState.INSTANCE.addBox(box4, color8, color7);
        }
    }

    @NotNull
    public final BlockPos getPos2() {
        return this.pos;
    }

    @NotNull
    public final Color getLineColor2() {
        return this.lineColor;
    }

    @NotNull
    public final Color getFillColor2() {
        return this.fillColor;
    }

    @NotNull
    public final AnimationStyle getAnimationMode2() {
        return this.animationMode;
    }

    @NotNull
    public final ShapeRenderMode getRenderMode2() {
        return this.renderMode;
    }

    @NotNull
    public final BlockHighlight copy(@NotNull BlockPos blockPos, @NotNull Color color, @NotNull Color color2, @NotNull AnimationStyle animationStyle, @NotNull ShapeRenderMode shapeRenderMode) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)29272, (long)(0x557A6D0715319C6L ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)5626, (long)(0x45A8D0F510C67E68L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)7168, (long)(0x3D8029829A89779BL ^ l))));
        Intrinsics.checkNotNullParameter((Object)animationStyle, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)20484, (long)(0xDB685B091883B93L ^ l))));
        Intrinsics.checkNotNullParameter((Object)shapeRenderMode, (String)((Object)BlockHighlight.zkmBootstrap("u", (int)14317, (long)(0x130F5FA465E8DC78L ^ l))));
        return new BlockHighlight(blockPos, color, color2, animationStyle, shapeRenderMode);
    }

    public static BlockHighlight copy$default(BlockHighlight blockHighlight, BlockPos blockPos, Color color, Color color2, AnimationStyle animationStyle, ShapeRenderMode shapeRenderMode, int n, Object object, long l) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)939786001384799679L, (long)l);
        int n2 = n & 1;
        if (callSite != false) {
            if (n2 != 0) {
                blockPos = blockHighlight.pos;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 != false) {
                if (n2 != 0) {
                    color = blockHighlight.lineColor;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != false) {
                if (n2 != 0) {
                    color2 = blockHighlight.fillColor;
                }
                n2 = n & BlockHighlight.zkmBootstrap2("u", (int)25130, (long)(0x63B2D56B124846DL ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != false) {
            if (n2 != 0) {
                animationStyle = blockHighlight.animationMode;
            }
            n2 = n & BlockHighlight.zkmBootstrap2("u", (int)27102, (long)(0x338049DC79618F9FL ^ l));
        }
        if (n2 != 0) {
            shapeRenderMode = blockHighlight.renderMode;
        }
        return blockHighlight.copy(blockPos, color, color2, animationStyle, shapeRenderMode);
    }

    @NotNull
    public String toString() {
        ShapeRenderMode shapeRenderMode = this.renderMode;
        AnimationStyle animationStyle = this.animationMode;
        Color color = this.fillColor;
        Color color2 = this.lineColor;
        BlockPos blockPos = this.pos;
        return "BlockRenderData(pos=" + blockPos + ", lineColor=" + color2 + ", fillColor=" + color + ", animationMode=" + animationStyle + ", renderMode=" + shapeRenderMode + ")";
    }

    public int hashCode() {
        int n = this.pos.hashCode();
        n = n * 31 + this.lineColor.hashCode();
        n = n * 31 + this.fillColor.hashCode();
        n = n * 31 + this.animationMode.hashCode();
        n = n * 31 + this.renderMode.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        BlockHighlight blockHighlight;
        BlockHighlight blockHighlight2;
        int n;
        block14: {
            boolean bl;
            block13: {
                n = Easing.O();
                Object object2 = this;
                if (n == 0) {
                    if (object2 == object) {
                        return true;
                    }
                    object2 = object;
                }
                if (n == 0) {
                    if (!(object2 instanceof BlockHighlight)) {
                        return false;
                    }
                    object2 = object;
                }
                blockHighlight2 = (BlockHighlight)object2;
                bl = Intrinsics.areEqual(this.pos, blockHighlight2.pos);
                if (n == 0) {
                    if (!bl) {
                        return false;
                    }
                    bl = Intrinsics.areEqual(this.lineColor, blockHighlight2.lineColor);
                }
                if (n != 0) break block13;
                if (!bl) {
                    return false;
                }
                blockHighlight = this;
                if (n != 0) break block14;
                bl = Intrinsics.areEqual(blockHighlight.fillColor, blockHighlight2.fillColor);
            }
            if (!bl) {
                return false;
            }
            blockHighlight = this;
        }
        if (n == 0) {
            if (blockHighlight.animationMode != blockHighlight2.animationMode) {
                return false;
            }
            blockHighlight = this;
        }
        return blockHighlight.renderMode == blockHighlight2.renderMode;
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
                                BlockHighlight.a = ZkmKeys.a(-5221071468899027616L, -2331140290922047323L, MethodHandles.lookup().lookupClass()).a(49924280964594L);
                                BlockHighlight.d = new HashMap<K, V>(13);
                                var22 = BlockHighlight.a ^ 134412840457823L;
                                var24_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var22 >>> 56);
                                for (var25_2 = 1; var25_2 < 8; ++var25_2) {
                                    v2 = v2;
                                    v2[var25_2] = (byte)(var22 << var25_2 * 8 >>> 56);
                                }
                                var24_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var31_3 = new String[18];
                                var29_4 = 0;
                                var28_5 = "\u00ef(4\u00e9\u00ab~[\u00b7\u00cc'\u00db.~\u00ca\u0014|\u0018\u0098\u00bd`/\u008c4\u00bb\u00be\u00e5\u00afm\u0087|\u0080\u00ea\u00b6\u00a1s\u0093\u00eal\u00b3\u00a0\u00d1\u0010,\u0082;\u00f5\u00f6\u00e2\u00964\u0094\u00d4I\u0085 \u000f\u00ad\u00b4\u0018:\u00f6\u009d\u008c\u0016l\u0005\u0003t\u00efv\u0004M{\u00b5\u00a9\u00a7\u00ec\u00ce\u0012\u00b6\u000e\u00bcO ]\u00853\u000b\u00ebMU\\\u0097\b\u0084\u00a4\u0002~\u00f4\u00d6\u0083J\fy`\u0081=\u00a3i\u00c1\u008b\u007f0\u00e4\u00fa.(\u00f2\u00d6`\u00e8\u001e\u001c\u001c!!Q\u0012\u00ba\u009c\u00bc\u008f2\u00d5_\u009b\u00d2f\u00ae\u00f7\u00bfU\u00ac\u0007\u00e6W\u00e6\u0013\u000f\u0099'\u00deY\u00d4\u0088\u0002\u008d J\u009d\u00d5\u0085\u00aek\u00c0\u00dd=\u00881D*<\u00b5v\u00faT\u00e9xs\"\u00d8-9\u0081[\u00e0\u00f5\u001aj/\u0018<\u0005\u00b27\u00cf\u0000:\u00d4H\u00fc\u00b15\u00a8\u0011+[U\u00ec\u00f7P\u00bbw4\u00d7 L\u00b1\u00ee\u0001\u000f\u00be0\u0010g*ZB\u00cf\u00de'\u00db\u00a4i\u00e4\u00e7\u0002\u00b1\u0089a\u00bc\u0007\n\u00f8,\u00a1@\t\u0018\u00a6\u00a3^\u001f\f\u00ddjN\u00cb\u008a\u0011\u00b4(G\u0095E\u008c\u008c\u00a0\u008fT2\u00cfH\u0010\u0018\u0006\u00e2\u00f9\u0080\u00e2\u00aeM\u00ffx\"L-S%\u00b2\u0018D?\r\u00ca\u00a3\u00ed\u009c\"\u00fc\u0000\u00f8\u00f1\u00a1\u008a\u00f4\u0085\u0098o\u00d2j&\u00db;\u00d8\u0018\u0084|n\u0097\u00ae3T\u00a2\u001b\u00c5\u0087F#\u0016c\u00dc{\u00dcG\u0003\u00c0S\u0096K\u0018\u009c:~w\u0007\u00c3K\u00b1(}G\u0083\u00c9\u00bb\u00ba\u00c6,nm?\u0012\u00a0\u0087C(\u00ff\u008c6\u00df\u0006\u0087nd\u00f0N\u00c7\u00c7\u00a8[\u0090\u00b5\b\u00c3\t\u00e6\u00a5\u008c\u00f6sWz\u000e+\\\u00e0\u0019Q\u0018i\u00e7\u00bcU\u008d\u00e8\u0094 .\u00edf\u00cdx\u00cc\u00f0\u00e3*\r\u00ee\u008f\u00da\u00c4]\u00cb\u00c9Di\u001aL\u0001\u00d9m\u00cf\u00ed\u00a3%\u0083\u008d\u00b8\u00b8";
                                var30_6 = 439;
                                var27_7 = 16;
                                var26_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var26_8;
                                    v4 = var28_5.substring(v3, v3 + var27_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var31_3[var29_4++] = BlockHighlight.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    var28_5 = "/\u00c2f\u00e0\u00d9D\\2\u00c9`@\u00eey)K\u00ee\u00e4\u00ca6\u00f0\u001b\u00b6\u000b\u00a8 @}\u00a8\u00de\u00c7\u00e5C/di\u00ffk\u0003\u00b7PB\u000e\u00e3\u0006\u00a9\u001fN@\u0006\u00f0\u009a\u00d9\u001eK\u00d4H\u00ca";
                                    var30_6 = 57;
                                    var27_7 = 24;
                                    var26_8 = -1;
lbl34:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var26_8;
                                        v4 = var28_5.substring(v6, v6 + var27_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl39:
                                // 1 sources

                                while (true) {
                                    var31_3[var29_4++] = BlockHighlight.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var32_9 = var24_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        BlockHighlight.b = var31_3;
                        BlockHighlight.c = new String[18];
                        BlockHighlight.h = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var22 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var22 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[7];
                        var14_13 = 0;
                        var15_14 = "\u0012\u00ee\u0089\u0012d\u00ce?]\u0095\u00feS\u00bcQ\u00c3\u00dc\u00aa\u00c5\u00bd\u009fz\u00f3\u0015\u0019\u0094\u0093\u00ddomq\u009b\u008f=o\u00c9[\u0003\u008a\u00ebb\u00ff";
                        var16_15 = 40;
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
                            var15_14 = "0\u00e2r^\u00c7\u00a1\t\u00fbA\u00f6\u00b5o\u0019;\u00a5\u00d4";
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
                BlockHighlight.e = var17_12;
                BlockHighlight.f = new Integer[7];
                BlockHighlight.l = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var22 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var22 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[3];
                var3_23 = 0;
                var4_24 = "\u00bc\u00fd\u00a5>\u008e\u00f4\u00abL\u00d5\u00f7y\u00b41\u00fbc\u00ff\u00b8\u00dcx\u00bc\u0091\u0092(d";
                var5_25 = 24;
                var2_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl126:
                // 1 sources

                while (true) {
                    var6_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var2_26 < var5_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
            v18 = var3_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        BlockHighlight.i = var6_22;
        BlockHighlight.k = new Long[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xC55;
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
                throw new RuntimeException("su/catlean/pe", exception);
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
            BlockHighlight.c[n2] = BlockHighlight.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BlockHighlight.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6AC9;
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
                throw new RuntimeException("su/catlean/pe", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BlockHighlight.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BlockHighlight.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xB94;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])BlockHighlight.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    BlockHighlight.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/pe", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            BlockHighlight.k[n2] = l4;
        }
        return k[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = BlockHighlight.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap3(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_2().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/pe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BlockHighlight.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BlockHighlight.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findStatic(BlockHighlight.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

