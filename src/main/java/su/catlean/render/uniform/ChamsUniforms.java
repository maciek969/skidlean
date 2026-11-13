/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render.uniform;

import com.mojang.blaze3d.buffers.Std140Builder;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ChamsUniforms
implements DynamicUniformStorage.Uploadable {
    private final float time;
    @NotNull
    private final Color color;
    @NotNull
    private final Color color2;
    private final int colorMode;
    private final int glowQuality;
    private final float glowRadius;
    private final float glowAlphaMult;
    private final int glowThinOutline;
    private final float fillAlphaMult;
    @NotNull
    private final Vec2f uHalfTexelSize;
    private final float uSaturation;
    private final float uBrightness;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    public ChamsUniforms(float f, @NotNull Color color, @NotNull Color color2, int n, int n2, float f2, float f3, int n3, float f4, @NotNull Vec2f vec2f, float f5, float f6) {
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(color2, "color2");
        Intrinsics.checkNotNullParameter(vec2f, "uHalfTexelSize");
        this.time = f;
        this.color = color;
        this.color2 = color2;
        this.colorMode = n;
        this.glowQuality = n2;
        this.glowRadius = f2;
        this.glowAlphaMult = f3;
        this.glowThinOutline = n3;
        this.fillAlphaMult = f4;
        this.uHalfTexelSize = vec2f;
        this.uSaturation = f5;
        this.uBrightness = f6;
    }

    public final float getTime() {
        return this.time;
    }

    @NotNull
    public final Color getColor() {
        return this.color;
    }

    @NotNull
    public final Color getColor2() {
        return this.color2;
    }

    public final int getColorMode() {
        return this.colorMode;
    }

    public final int getGlowQuality() {
        return this.glowQuality;
    }

    public final float getGlowRadius() {
        return this.glowRadius;
    }

    public final float getGlowAlphaMult() {
        return this.glowAlphaMult;
    }

    public final int getGlowThinOutline() {
        return this.glowThinOutline;
    }

    public final float getFillAlphaMult() {
        return this.fillAlphaMult;
    }

    @NotNull
    public final Vec2f getUHalfTexelSize() {
        return this.uHalfTexelSize;
    }

    public final float getUSaturation() {
        return this.uSaturation;
    }

    public final float getUBrightness() {
        return this.uBrightness;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putFloat(this.time).putVec4(RenderUtil.INSTANCE.getRed(this.color), RenderUtil.INSTANCE.getGreen(this.color), RenderUtil.INSTANCE.getBlue(this.color), RenderUtil.INSTANCE.getAlpha(this.color)).putVec4(RenderUtil.INSTANCE.getRed(this.color2), RenderUtil.INSTANCE.getGreen(this.color2), RenderUtil.INSTANCE.getBlue(this.color2), RenderUtil.INSTANCE.getAlpha(this.color2)).putInt(this.colorMode).putInt(this.glowQuality).putFloat(this.glowRadius).putFloat(this.glowAlphaMult).putInt(this.glowThinOutline).putFloat(this.fillAlphaMult).putVec2(this.uHalfTexelSize.x, this.uHalfTexelSize.y).putFloat(this.uSaturation).putFloat(this.uBrightness);
    }

    public final float getTime2() {
        return this.time;
    }

    @NotNull
    public final Color getColor3() {
        return this.color;
    }

    @NotNull
    public final Color getColor22() {
        return this.color2;
    }

    public final int getColorMode2() {
        return this.colorMode;
    }

    public final int getGlowQuality2() {
        return this.glowQuality;
    }

    public final float getGlowRadius2() {
        return this.glowRadius;
    }

    public final float getGlowAlphaMult2() {
        return this.glowAlphaMult;
    }

    public final int getGlowThinOutline2() {
        return this.glowThinOutline;
    }

    public final float getFillAlphaMult2() {
        return this.fillAlphaMult;
    }

    @NotNull
    public final Vec2f getUHalfTexelSize2() {
        return this.uHalfTexelSize;
    }

    public final float getUSaturation2() {
        return this.uSaturation;
    }

    public final float getUBrightness2() {
        return this.uBrightness;
    }

    @NotNull
    public final ChamsUniforms copy(float f, @NotNull Color color, @NotNull Color color2, int n, int n2, float f2, float f3, int n3, float f4, @NotNull Vec2f vec2f, float f5, float f6) {
        long l = 0L;
        int n4 = 0;
        long l2 = (l << 16 | (long)n4 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)ChamsUniforms.zkmBootstrap("y", (int)10758, (long)(0x54673EEB83A95370L ^ l2))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)ChamsUniforms.zkmBootstrap("y", (int)3365, (long)(0x7C309A6EA152F456L ^ l2))));
        Intrinsics.checkNotNullParameter(vec2f, (String)((Object)ChamsUniforms.zkmBootstrap("y", (int)23638, (long)(0x442544D03E4B2529L ^ l2))));
        return new ChamsUniforms(f, color, color2, n, n2, f2, f3, n3, f4, vec2f, f5, f6);
    }

    public static ChamsUniforms copy$default(ChamsUniforms chamsUniforms, float f, Color color, Color color2, long l, int n, int n2, float f2, float f3, int n3, float f4, Vec2f vec2f, float f5, float f6, int n4, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-4986889427793127691L, (long)l);
        int n5 = n4 & 1;
        if (callSite == null) {
            if (n5 != 0) {
                f = chamsUniforms.time;
            }
            n5 = n4 & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    color = chamsUniforms.color;
                }
                n5 = n4 & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    color2 = chamsUniforms.color2;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)16924, (long)(0x10C7C939B2E6285AL ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    n = chamsUniforms.colorMode;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)24462, (long)(0x1432C707B6F5B5C3L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    n2 = chamsUniforms.glowQuality;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)667, (long)(0x1D1C64B3B97368D7L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    f2 = chamsUniforms.glowRadius;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)12899, (long)(0x5105BE07E2AC5826L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    f3 = chamsUniforms.glowAlphaMult;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)255, (long)(0x3225B717037CEAB7L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    n3 = chamsUniforms.glowThinOutline;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)22303, (long)(0x7E98FCD5396EBD55L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    f4 = chamsUniforms.fillAlphaMult;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)1220, (long)(0x1973F5A925C6E8DL ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n5 != 0) {
                    vec2f = chamsUniforms.uHalfTexelSize;
                }
                n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)15790, (long)(0x5AA3EA44E309D7E1L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n5 != 0) {
                f5 = chamsUniforms.uSaturation;
            }
            n5 = n4 & ChamsUniforms.zkmBootstrap2("v", (int)23715, (long)(0x6BE81D8FA831B6E8L ^ l));
        }
        if (n5 != 0) {
            f6 = chamsUniforms.uBrightness;
        }
        return chamsUniforms.copy(f, color, color2, n, n2, f2, f3, n3, f4, vec2f, f5, f6);
    }

    @NotNull
    public String toString() {
        float f = this.uBrightness;
        float f2 = this.uSaturation;
        Vec2f vec2f = this.uHalfTexelSize;
        float f3 = this.fillAlphaMult;
        int n = this.glowThinOutline;
        float f4 = this.glowAlphaMult;
        float f5 = this.glowRadius;
        int n2 = this.glowQuality;
        int n3 = this.colorMode;
        Color color = this.color2;
        Color color2 = this.color;
        float f6 = this.time;
        return "UniformData(time=" + f6 + ", color=" + color2 + ", color2=" + color + ", colorMode=" + n3 + ", glowQuality=" + n2 + ", glowRadius=" + f5 + ", glowAlphaMult=" + f4 + ", glowThinOutline=" + n + ", fillAlphaMult=" + f3 + ", uHalfTexelSize=" + vec2f + ", uSaturation=" + f2 + ", uBrightness=" + f + ")";
    }

    public int hashCode() {
        long l = a ^ 0x96A52684405L;
        int n = Float.hashCode(this.time);
        n = n * 31 + this.color.hashCode();
        n = n * 31 + this.color2.hashCode();
        ZkmReferences.zkmBootstrap("\u00c5", (long)5612708263600431578L, (long)l);
        n = n * 31 + Integer.hashCode(this.colorMode);
        n = n * 31 + Integer.hashCode(this.glowQuality);
        n = n * 31 + Float.hashCode(this.glowRadius);
        n = n * 31 + Float.hashCode(this.glowAlphaMult);
        n = n * 31 + Integer.hashCode(this.glowThinOutline);
        n = n * 31 + Float.hashCode(this.fillAlphaMult);
        n = n * 31 + this.uHalfTexelSize.hashCode();
        n = n * 31 + Float.hashCode(this.uSaturation);
        n = n * 31 + Float.hashCode(this.uBrightness);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        int n;
        ChamsUniforms chamsUniforms;
        CallSite callSite;
        block30: {
            int n2;
            block29: {
                long l = a ^ 0x7BA0BBE46553L;
                callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7832369600011534476L, (long)l);
                Object object2 = this;
                if (callSite == null) {
                    if (object2 == object) {
                        return true;
                    }
                    object2 = object;
                }
                if (callSite == null) {
                    if (!(object2 instanceof ChamsUniforms)) {
                        return false;
                    }
                    object2 = object;
                }
                chamsUniforms = (ChamsUniforms)object2;
                n = Float.compare(this.time, chamsUniforms.time);
                if (callSite == null) {
                    if (n != 0) {
                        return false;
                    }
                    n = Intrinsics.areEqual(this.color, chamsUniforms.color) ? 1 : 0;
                }
                if (callSite == null) {
                    if (n == 0) {
                        return false;
                    }
                    n = Intrinsics.areEqual(this.color2, chamsUniforms.color2) ? 1 : 0;
                }
                if (callSite == null) {
                    if (n == 0) {
                        return false;
                    }
                    n = this.colorMode;
                }
                n2 = chamsUniforms.colorMode;
                if (callSite != null) break block29;
                if (n != n2) {
                    return false;
                }
                n = this.glowQuality;
                if (callSite != null) break block30;
                n2 = chamsUniforms.glowQuality;
            }
            if (n != n2) {
                return false;
            }
            n = Float.compare(this.glowRadius, chamsUniforms.glowRadius);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.glowAlphaMult, chamsUniforms.glowAlphaMult);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = this.glowThinOutline;
        }
        if (callSite == null) {
            if (n != chamsUniforms.glowThinOutline) {
                return false;
            }
            n = Float.compare(this.fillAlphaMult, chamsUniforms.fillAlphaMult);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Intrinsics.areEqual(this.uHalfTexelSize, chamsUniforms.uHalfTexelSize) ? 1 : 0;
        }
        if (callSite == null) {
            if (n == 0) {
                return false;
            }
            n = Float.compare(this.uSaturation, chamsUniforms.uSaturation);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = Float.compare(this.uBrightness, chamsUniforms.uBrightness);
        }
        if (callSite == null) {
            if (n != 0) {
                return false;
            }
            n = 1;
        }
        return n != 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ChamsUniforms.a = ZkmKeys.a(8216273370705814828L, -8309457020984873276L, MethodHandles.lookup().lookupClass()).a(155671392935085L);
                        ChamsUniforms.e = new HashMap<K, V>(13);
                        var11 = ChamsUniforms.a ^ 5298140722440L;
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
                        var20_3 = new String[19];
                        var18_4 = 0;
                        var17_5 = "\u00e8\u00e9\u009a\u001b(\u0086\u00e1\u009e\u0085\u00b7\u00e5\u001e\u0083f \u000f\u001d\u0019k\u0016\u00d6\u0089J\u0089\u00b6\u00d5um\u0018\u009b\u00a2\u0090\u0018\u0016U\u00d2\u009f)\u00a6\u0086\u0086\u0086E\u00ea\u00dalT\u000f\u00f1ng\u0099\u00feP}#i 8\u001b\u009d\u00a4Dj\u0019Z\u0000\u0097\u000e\u00cf\u00af\u0090J\u00a7\u00be\u00bbjN[Lxr\u00b7\u00fdFvJ\u00f0\u00f1\u0014(\u00f6\u00d6\u00ba\u00b2\u00182qj9[*\u00e6\u00a3<2t\u00dft\u00d7\u00e0\u0094\u00b2=\u0011\u0082S%_\u008a\u00bc\u0007\u00c3\u001f\u00b5\rgd\u00bb\u0016\u00e3\u0010\u00cf\u0003\n@\u00de\u0012\u00f3\u0081Q\u0017\u00c1\u00a6!\u0093\u00bb\t\u0018\u00b0\u0006G\u00a4\u00e3\u00d9\u00abh\u00ed\u00c0\u00c4\t\u00e1!\u00af\u00f4\u00f7\u00bf\u00a7yJ\u00a6I\u0011(\u00fa\u00cf\u00b3\u00cba\u00ae\u0000\u00c4\u0011vN\u0092\u00c5\u001d\u00fb\u008b\u00a7n\u00a15\u00d1;0\u00da_x_iD\u00bb[x\r\u001f\u00c8*\u00af\u0089\u00bd9\u0010\u00aa\u0019)\u00c2\u00a8F\u009a\u000f\u00df\u001b\u00ef>\u009c\u00c5@\u00bf 3\u000f\u00e5\u00a3`e!\u00f1\u00fd(\u00c4\u0002\u00af-j\u00f60\u00a8\u0019gmD\u0095\u00fb\u00d1\u00e9\u000e>v\u00ef.\u00d8\u0010\u0010\u0080\u000b\u00aerp\u0016u>\u00cfQ\u00b7\u00920\u0019\u0005\u0010;Afn\u008a!\u00d0\u00ddre\u001f6Y\u00fd\u0095\\\u0018~:\u0006\u0016\u0007\u00c8\u00fd\u00b0\u007fd\u008c\u0081\u00b1Y^\u00d2\u00f8\u00bc\u00d64\u000b\u0085#\u00c8\u0010\u0089(4\nJX\u0094UE\u00f2\u001b2\u00a0\u00b5\tk(\u008f\\\u00ec\u00b8\u008cz\u001dT=\u00f7}s\u00c9\u008fy\u00fe\u000f\u0016ST\u00bb\u0095\u001e\u0097\u00d7\u0090P\u00d7\u0090\u00cb\u0082\u0080\u0099&e\u00b4\u00eeJ\u00c1T\u0018\u0088_\u00cc\u0000\u0011\u0081\u0099R\u0089\u00cfO\u00fa>\u009f\u0094P\u00cd\u00db\u008bt\u0090\u00b5\u00a0% E\u00b3\u00cd\u00cd\b\u00d2\u00ff\u00f05\u00c2\u001b}\u00f43\u00e3\u00a3\u009cr\u0010\u00a32\u00c6Q8*\u00df\u00ee\u0099\u001f\u00a6\u00a2\u00cd(\u0016\u0006*\u00bc\u00ddd\u0005\u009e\u00ff\u00a0\u00e2\u008dt\u00f6Ys1\u0088W\u00da<\u00d0\u00caH\u0086\u0098e\u00f1Z\u00b7\u00efd\u00f7br\u00d2/4\u00b5\u00f4";
                        var19_6 = 480;
                        var16_7 = 32;
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
                            var20_3[var18_4++] = ChamsUniforms.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "&\u00df\u0017\u00f9\u00d0vB\u00e5w\u00cf<\u00ebUu\u00a2,\u00d8-a\u000b\u0094\u00ce=h\u00c6\u00b8\u00b2\u00cd\u001d0\u00f1\u00cal\u00det\u00a4\u00b2\u00cd~E\u0018\u0018\u001am<\u00b76\u00f8\u00fb'\u0090\u00f8\u00d01@ )\u0017\u00cb\u00ecA\u0083\u0090\u00d0\u0013";
                            var19_6 = 65;
                            var16_7 = 40;
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
                            var20_3[var18_4++] = ChamsUniforms.zkmDecodeUtf8(var21_9).intern();
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
                ChamsUniforms.b = var20_3;
                ChamsUniforms.c = new String[19];
                ChamsUniforms.i = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "^LRM\u00e1Vm\u00fb\u00b7\u0087\u00b2\u00b4\u008c\u00af\u0002W;\u00fc\u00cf\u00f4,\f\u00df\u00cf\u00db\u00d3(\u0098\u00cc\u0012\u00f0W{\u00be\u00de\u00ba\u001dot\u00da\u00c6\u00a9\u00d8^:`\"%\u008c\u0006H\u00b2\f(w\u009dF\u0005a\u008c\u00db-\u0085apna\u0000D\r\u00d8\u00de";
                var5_15 = 72;
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
                    var4_14 = "\u008e\u0098\u0093\u0093\u00ce\u00f6\u00bb\u00b6\u00c5\u00f6\u00e6M\u00c37!\u00d4";
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
        ChamsUniforms.g = var6_12;
        ChamsUniforms.h = new Integer[11];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2115;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/i6", exception);
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
            ChamsUniforms.c[n2] = ChamsUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ChamsUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/i6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x51B5;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/i6", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ChamsUniforms.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ChamsUniforms.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/i6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ChamsUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ChamsUniforms.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

