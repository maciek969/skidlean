/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.chars.Char2IntArrayMap
 *  it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2ObjectFunction
 *  it.unimi.dsi.fastutil.objects.Object2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  it.unimi.dsi.fastutil.objects.ObjectSet
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 */
package su.catlean.render.font;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.chars.Char2IntArrayMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectFunction;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.awt.Color;
import java.awt.Font;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import su.catlean.module.misc.NameProtect;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.TextureRegion;
import su.catlean.render.Theme;
import su.catlean.render.draw.ColorStop;
import su.catlean.render.font.Fonts;
import su.catlean.render.font.GlyphPage;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class GlyphCache {
    @NotNull
    public static final GlyphCache INSTANCE;
    private static final char S;
    @NotNull
    private static final Char2IntArrayMap char2IntArrayMap;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private GlyphCache() {
    }

    @NotNull
    public final Char2IntArrayMap getChar2IntArrayMap() {
        return char2IntArrayMap;
    }

    public final int floorToMultiple(int n, int n2) {
        return n2 * (int)Math.floor((double)n / (double)n2);
    }

    @NotNull
    public final String stripColorCodes(char c, int n, int n2, @NotNull String string) {
        long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)n2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x72C82077AFB8L;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 16 >>> 32);
        int n5 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(string, "text");
        char[] cArray = string.toCharArray();
        Intrinsics.checkNotNullExpressionValue(cArray, "toCharArray(...)");
        char[] cArray2 = cArray;
        StringBuilder stringBuilder = new StringBuilder(cArray2.length);
        int n6 = 0;
        block3: while (n6 < cArray2.length) {
            char c2 = cArray2[n6];
            if (n2 >= 0) {
                if (c2 == GlyphCache.zkmBootstrap2("j", (int)6068, (long)(0x2465EBA3F71CEC35L ^ l))) {
                    if (n6 + 1 >= cArray2.length) break;
                    char c3 = cArray2[n6 + 1];
                    if (c >= '\u0000') {
                        switch (c3) {
                            case 'X': 
                            case 'x': {
                                if (n2 < 0) break;
                                if (this.isHexColor(cArray2, n6, (short)n3, n4, n5)) {
                                    n6 += 14;
                                    continue block3;
                                }
                            }
                            default: {
                                n6 += 2;
                            }
                        }
                    }
                    if (c >= '\u0000') continue;
                }
                stringBuilder.append(c2);
                ++n6;
            }
            if (n2 >= 0) continue;
        }
        String string2 = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return string2;
    }

    @NotNull
    public final Identifier createTempId() {
        return Ids.id("catlean", "temp/" + this.randomId());
    }

    @NotNull
    public final String randomId() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        String string = IntStream.range(0, (int)GlyphCache.zkmBootstrap2("j", (int)4746, (long)(0x3EFCD111222656C0L ^ l))).mapToObj(GlyphCache::randomChar).collect(Collectors.joining());
        Intrinsics.checkNotNullExpressionValue(string, "collect(...)");
        return string;
    }

    @NotNull
    public final int[] toRgb(int n) {
        int n2 = n >> 16 & 0xFF;
        int n3 = n >> 8 & 0xFF;
        int n4 = n & 0xFF;
        int[] nArray = new int[]{n2, n3, n4};
        return nArray;
    }

    public final float roundScale(float f) {
        return Float.isNaN(f) ? 1.0f : (float)MathKt.roundToInt((float)MathKt.roundToInt(f * 100.0f) / 100.0f * 10.0f) / 10.0f;
    }

    @NotNull
    public final GlyphPage createPage(char c, char c2, @NotNull Font font, @NotNull ObjectArrayList objectArrayList, int n) {
        Intrinsics.checkNotNullParameter(font, "font");
        Intrinsics.checkNotNullParameter(objectArrayList, "maps");
        int n2 = n;
        Identifier identifier = this.createTempId();
        Font font2 = font;
        char c3 = c2;
        char c4 = c;
        GlyphPage glyphPage = new GlyphPage(c4, c3, font2, identifier, n2);
        objectArrayList.add((Object)glyphPage);
        return glyphPage;
    }

    @Nullable
    public final TextureRegion findOrCreatePage(long l, char c, @NotNull ObjectArrayList objectArrayList, @NotNull Font font, @NotNull Font font2) {
        Object object;
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x60571570F220L;
        long l4 = l2 ^ 0x779380F44664L;
        Intrinsics.checkNotNullParameter(objectArrayList, "maps");
        Intrinsics.checkNotNullParameter(font, "font");
        Intrinsics.checkNotNullParameter(font2, "fallBack");
        int n = 5;
        CallSite callSite = GlyphCache.zkmBootstrap2("j", (int)11813, (long)(0x4596F7E2AA70097BL ^ l));
        ObjectListIterator objectListIterator = objectArrayList.iterator();
        Intrinsics.checkNotNullExpressionValue(objectListIterator, "iterator(...)");
        ObjectListIterator objectListIterator2 = objectListIterator;
        while (objectListIterator2.hasNext()) {
            Object object2 = objectListIterator2.next();
            while (((GlyphPage)(object = (GlyphPage)object2)).contains(c, l3)) {
                object2 = ((GlyphPage)object).ensureLoaded(l4, c);
                if (l <= 0L) continue;
                return object2;
            }
        }
        int n2 = this.floorToMultiple(c, (int)callSite);
        boolean bl = font.canDisplay(c);
        if (l >= 0L) {
            if (bl) {
                object = this.createPage((char)n2, (char)(n2 + callSite), font, objectArrayList, n).ensureLoaded(l4, c);
                if (l >= 0L && object != null) {
                    return object;
                }
            }
            bl = font2.canDisplay(c);
        }
        if (bl) {
            return this.createPage((char)n2, (char)(n2 + callSite), font2, objectArrayList, n).ensureLoaded(l4, c);
        }
        return null;
    }

    @Nullable
    public final TextureRegion getGlyph(byte by, int n, char c, @NotNull Char2ObjectArrayMap char2ObjectArrayMap, int n2, @NotNull ObjectArrayList objectArrayList, @NotNull Font font, @NotNull Font font2) {
        long l = ((long)by << 56 | (long)n << 32 >>> 8 | (long)n2 << 40 >>> 40) ^ a;
        long l2 = l ^ 0x6D91752D32BDL;
        Intrinsics.checkNotNullParameter(char2ObjectArrayMap, "allGlyphs");
        Intrinsics.checkNotNullParameter(objectArrayList, "maps");
        Intrinsics.checkNotNullParameter(font, "font");
        Intrinsics.checkNotNullParameter(font2, "fallBack");
        TextureRegion textureRegion = (TextureRegion)char2ObjectArrayMap.get(c);
        if (textureRegion != null) {
            TextureRegion textureRegion2 = textureRegion;
            return textureRegion2;
        }
        TextureRegion textureRegion3 = this.findOrCreatePage(l2, c, objectArrayList, font, font2);
        ((Map)char2ObjectArrayMap).put(Character.valueOf(c), textureRegion3);
        return textureRegion3;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void drawString(@NotNull Matrix3x2fStack var1_1, @NotNull String var2_2, int var3_3, float var4_4, float var5_5, int var6_6, @NotNull Color var7_7, boolean var8_8, int var9_9, float var10_10, @NotNull Char2ObjectArrayMap var11_11, byte var12_12, @NotNull ObjectArrayList var13_13, @NotNull Font var14_14, @NotNull Font var15_15, @NotNull Object2ObjectOpenHashMap var16_16) {
        v0 = var17_17 = ((long)var3_3 << 32 | (long)var6_6 << 40 >>> 32 | (long)var12_12 << 56 >>> 56) ^ GlyphCache.a;
        v1 = v0 ^ 57136396918157L;
        var21_18 = v1 >>> 8;
        var23_19 = (int)(v1 << 56 >>> 56);
        var28_20 = (int)((v0 ^ 11875727502953L) >>> 48);
        v2 = v0 ^ 26848343057985L;
        var36_21 = (int)(v2 >>> 48);
        var37_22 = (int)(v2 << 16 >>> 32);
        var38_23 = (int)(v2 << 48 >>> 48);
        var41_24 = v0 ^ 57589483378611L;
        v3 = v0 ^ 5457712387009L;
        var50_25 = (int)(v3 >>> 56);
        var51_26 = (int)(v3 << 8 >>> 32);
        var52_27 = (int)(v3 << 40 >>> 40);
        Intrinsics.checkNotNullParameter(var1_1, "stack");
        Intrinsics.checkNotNullParameter(var2_2, "s");
        Intrinsics.checkNotNullParameter(var7_7, "color");
        Intrinsics.checkNotNullParameter(var11_11, "allGlyphs");
        v4 = Fonts.zkmGetFlag();
        Intrinsics.checkNotNullParameter(var13_13, "maps");
        var58_28 = v4;
        Intrinsics.checkNotNullParameter(var14_14, "font");
        Intrinsics.checkNotNullParameter(var15_15, "fallBack");
        Intrinsics.checkNotNullParameter(var16_16, "glyphPage");
        var59_29 = NameProtect.INSTANCE.replaceName((char)var28_20, var2_2);
        var60_30 = (float)var7_7.getRed() / 255.0f;
        var61_31 = (float)var7_7.getGreen() / 255.0f;
        var62_32 = (float)var7_7.getBlue() / 255.0f;
        var63_33 = (float)var7_7.getAlpha() / 255.0f;
        var90_34 = var4_4 + 0.5f;
        var64_35 = this.roundScale(var90_34);
        var94_36 = var5_5 + 1.5f;
        var65_37 = this.roundScale(var94_36);
        var1_1.pushMatrix();
        var1_1.translate(var64_35, var65_37);
        var1_1.scale(1.0f / var10_10, 1.0f / var10_10);
        var66_38 = new Matrix3x2f((Matrix3x2fc)var1_1);
        v5 = var59_29.toCharArray();
        Intrinsics.checkNotNullExpressionValue(v5, "toCharArray(...)");
        var67_39 = v5;
        var68_40 = 0.0f;
        var69_41 = 0.0f;
        var70_42 = '\u0000';
        var71_43 = 0;
        var72_44 = 0;
        block7: while (true) {
            v6 = ++var72_44;
            block8: while (v6 < var67_39.length) {
                block21: {
                    block22: {
                        var73_45 = var67_39[var72_44];
                        v7 = var70_42;
                        v8 = var58_28;
                        if (var3_3 <= 0) break block21;
                        if (v7 == '\u0000') break block22;
                        var70_42 = '\u0000';
                        switch (var73_45) {
                            case 'X': 
                            case 'x': {
                                var74_47 = this.parseHexColor((short)var36_21, var37_22, var67_39, (char)var38_23, var72_44);
                                v9 = var58_28;
                                if (var3_3 >= 0) {
                                    if (var74_47 != null) {
                                        var75_49 = ((Number)var74_47.component1()).intValue();
                                        var76_53 = ((Number)var74_47.component2()).intValue();
                                        var77_57 = ((Number)var74_47.component3()).intValue();
                                        var60_30 = (float)var75_49 / 255.0f;
                                        var61_31 = (float)var76_53 / 255.0f;
                                        var62_32 = (float)var77_57 / 255.0f;
                                        var72_44 += 12;
                                    }
                                    v9 = var58_28;
                                }
                                continue block7;
                            }
                        }
                        var74_48 = Character.toUpperCase(var73_45);
                        v10 = GlyphCache.char2IntArrayMap.containsKey(var74_48);
                        v11 = var58_28;
                        if (var6_6 < 0) ** GOTO lbl94
                        if (v10 != '\u0000') {
                            var75_50 = GlyphCache.char2IntArrayMap.get(var74_48);
                            var76_54 = this.toRgb(var75_50);
                            var60_30 = (float)var76_54[0] / 255.0f;
                            var61_31 = (float)var76_54[1] / 255.0f;
                            var62_32 = (float)var76_54[2] / 255.0f;
                            v12 = var58_28;
                            if (var6_6 <= 0) continue block7;
                        } else {
                            v10 = var74_48;
                            v11 = var58_28;
lbl94:
                            // 2 sources

                            if (v10 == GlyphCache.zkmBootstrap2("j", (int)30961, (long)(4576804737230339828L ^ var17_17))) {
                                var60_30 = (float)var7_7.getRed() / 255.0f;
                                var61_31 = (float)var7_7.getGreen() / 255.0f;
                                var62_32 = (float)var7_7.getBlue() / 255.0f;
                                var63_33 = (float)var7_7.getAlpha() / 255.0f;
                            }
                        }
                        ++var72_44;
                        v12 = var58_28;
                        continue block7;
                    }
                    v7 = var8_8;
                    v8 = var58_28;
                }
                if (var6_6 > 0) {
                    if (v7 != '\u0000') {
                        var74_47 = Theme.INSTANCE.getGradientColor(var72_44 * var9_9);
                        var60_30 = (float)var74_47.getRed() / 255.0f;
                        var61_31 = (float)var74_47.getGreen() / 255.0f;
                        var62_32 = (float)var74_47.getBlue() / 255.0f;
                        var63_33 = (float)var74_47.getAlpha() / 255.0f;
                    }
                    v7 = var73_45;
                    v8 = var58_28;
                }
                switch (v7) {
                    case '\u00a7': {
                        var70_42 = '\u0001';
                        ++var72_44;
                        v13 = var58_28;
                        if (var3_3 <= 0) continue block7;
                        continue block7;
                    }
                    case '\n': {
                        v14 = var59_29.substring(var71_43, var72_44);
                        Intrinsics.checkNotNullExpressionValue(v14, "substring(...)");
                        var69_41 += this.getWidth(v14, var11_11, var13_13, var21_18, var14_14, (byte)var23_19, var15_15, var10_10) * var10_10;
                        var68_40 = 0.0f;
                        v6 = var72_44 + 1;
                        if (var6_6 < 0) continue block8;
                        var71_43 = v6;
                        ++var72_44;
                        v13 = var58_28;
                        continue block7;
                    }
                }
                var74_47 = this.getGlyph((byte)var50_25, var51_26, var73_45, var11_11, var52_27, var13_13, var14_14, var15_15);
                v15 = var58_28;
                if (var12_12 < 0) continue block7;
                if (var74_47 != null) {
                    if (var74_47.getValue() != GlyphCache.zkmBootstrap2("j", (int)4746, (long)(4538775491774354612L ^ var17_17))) {
                        var75_51 = var74_47.getOwner().getBindToTexture();
                        var76_55 = new ColorStop(var68_40, var69_41, var60_30, var61_31, var62_32, var63_33, var74_47.getWidth(), var74_47.getHeight(), var74_47.getOwner().V(), var74_47.getOwner().x(), var74_47.getU(), var74_47.getV(), 0.0f, var66_38);
                        ((ObjectList)var16_16.computeIfAbsent((Object)var75_51, (Object2ObjectFunction)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, drawString$lambda$0(java.lang.Object ), (Ljava/lang/Object;)Lit/unimi/dsi/fastutil/objects/ObjectList;)())).add((Object)var76_55);
                    }
                    var68_40 += (float)var74_47.getWidth();
                }
                ++var72_44;
                v15 = var58_28;
                continue block7;
            }
            if (var12_12 >= 0) break;
        }
        v16 = ((ObjectSet)var16_16.keySet()).iterator();
        Intrinsics.checkNotNullExpressionValue(v16, "iterator(...)");
        var73_46 = v16;
        block9: while (var73_46.hasNext()) {
            var74_47 = (Identifier)var73_46.next();
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().getTextureManager().getTexture((Identifier)var74_47).getGlTextureView(), "getTextureView(...)");
            v17 = var16_16.get(var74_47);
            Intrinsics.checkNotNull(v17);
            var76_56 = (ObjectList)v17;
            Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_TEXTURE_COLOR, "POSITION_TEX_COLOR");
            var53_59 = null;
            var54_60 = 4;
            var55_61 = false;
            var56_62 = var76_56.size();
            var77_58 = new BatchedBuffer(var57_63, var56_62, var55_61, var54_60, var53_59);
            Intrinsics.checkNotNullExpressionValue(var76_56.iterator(), "iterator(...)");
            while (var78_64.hasNext()) {
                var79_65 = (ColorStop)var78_64.next();
                var80_66 = var79_65.getAtX();
                var81_67 = var79_65.getAtY();
                var82_68 = var79_65.getW();
                var83_69 = var79_65.getH();
                var84_70 = (float)var79_65.getU() / (float)var79_65.getOw();
                var85_71 = (float)var79_65.getV() / (float)var79_65.getOh();
                var86_72 = (float)(var79_65.getU() + var79_65.getW()) / (float)var79_65.getOw();
                var87_73 = (float)(var79_65.getV() + var79_65.getH()) / (float)var79_65.getOh();
                var97_74 = var79_65.s();
                var99_75 = var79_65.getB();
                var100_76 = var79_65.getG();
                BatchedBuffer.vertex$default(var77_58, var80_66 + (float)false, var81_67 + var83_69, 0.0f, null).texture(var84_70, var87_73).color(var79_65.getR(), var100_76, var99_75, var97_74);
                var103_77 = var79_65.s();
                var105_78 = var79_65.getB();
                var106_79 = var79_65.getG();
                BatchedBuffer.vertex$default(var77_58, var80_66 + var82_68, var81_67 + var83_69, 0.0f, null).texture(var86_72, var87_73).color(var79_65.getR(), var106_79, var105_78, var103_77);
                var109_80 = var79_65.s();
                var111_81 = var79_65.getB();
                var112_82 = var79_65.getG();
                BatchedBuffer.vertex$default(var77_58, var80_66 + var82_68, var81_67 + (float)false, 0.0f, null).texture(var86_72, var85_71).color(var79_65.getR(), var112_82, var111_81, var109_80);
                var115_83 = var79_65.s();
                var117_84 = var79_65.getB();
                var118_85 = var79_65.getG();
                BatchedBuffer.vertex$default(var77_58, var80_66 + (float)false, var81_67 + (float)false, 0.0f, null).texture(var84_70, var85_71).color(var79_65.getR(), var118_85, var117_84, var115_83);
                v18 = var58_28;
                if (var6_6 < 0) continue block9;
            }
            v19 = Pipelines.INSTANCE.getProgramDefaultColoredTexture();
            v20 = Globals.getMc().getFramebuffer();
            Intrinsics.checkNotNullExpressionValue(v20, "getMainRenderTarget(...)");
            BatchedBuffer.draw$default(var41_24, var77_58, v19, v20, null, null, var66_38, MapsKt.mapOf(TuplesKt.to("Sampler0", var75_52)), (int)GlyphCache.zkmBootstrap2("j", (int)11120, (long)(4113195633737969023L ^ var17_17)), null);
            v18 = var58_28;
        }
        var16_16.clear();
        var1_1.popMatrix();
        if (var3_3 >= 0) {
            // empty if block
        }
    }

    public final void drawStringScaled(@NotNull Matrix3x2fStack matrix3x2fStack, @NotNull String string, float f, float f2, @NotNull Color color, boolean bl, int n, float f3, long l, @NotNull Char2ObjectArrayMap char2ObjectArrayMap, @NotNull ObjectArrayList objectArrayList, @NotNull Font font, @NotNull Font font2, float f4, @NotNull Object2ObjectOpenHashMap object2ObjectOpenHashMap) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x70081FA83039L;
        long l4 = l3 >>> 8;
        int n2 = (int)(l3 << 56 >>> 56);
        int n3 = (int)((l2 ^ 0x49320D8883DDL) >>> 48);
        long l5 = l2 ^ 0x4709BC0E7E75L;
        int n4 = (int)(l5 >>> 56);
        int n5 = (int)(l5 << 8 >>> 32);
        int n6 = (int)(l5 << 40 >>> 40);
        long l6 = l2 ^ 0x5B9418FF0BF5L;
        int n7 = (int)(l6 >>> 48);
        int n8 = (int)(l6 << 16 >>> 32);
        int n9 = (int)(l6 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "stack");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(char2ObjectArrayMap, "allGlyphs");
        String[] stringArray = new String[]{};
        Intrinsics.checkNotNullParameter(objectArrayList, "maps");
        Intrinsics.checkNotNullParameter(font, "font");
        Intrinsics.checkNotNullParameter(font2, "fallBack");
        String[] stringArray2 = stringArray;
        Intrinsics.checkNotNullParameter(object2ObjectOpenHashMap, "batchedDraws");
        String string2 = NameProtect.INSTANCE.replaceName((char)n3, string);
        float f5 = (float)color.getRed() / 255.0f;
        float f6 = (float)color.getGreen() / 255.0f;
        float f7 = (float)color.getBlue() / 255.0f;
        float f8 = (float)color.getAlpha() / 255.0f;
        float f9 = f + 0.5f;
        float f10 = this.roundScale(f9);
        float f11 = f2 + 1.5f;
        float f12 = this.roundScale(f11);
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(f10, f12);
        matrix3x2fStack.scale(1.0f / f3, 1.0f / f3);
        Matrix3x2f matrix3x2f = new Matrix3x2f((Matrix3x2fc)matrix3x2fStack);
        char[] cArray = string2.toCharArray();
        Intrinsics.checkNotNullExpressionValue(cArray, "toCharArray(...)");
        char[] cArray2 = cArray;
        float f13 = 0.0f;
        float f14 = 0.0f;
        boolean bl2 = false;
        int n10 = 0;
        Identifier identifier = null;
        ObjectList objectList = null;
        int n11 = 0;
        block7: while (n11 < cArray2.length) {
            Object object;
            char c = cArray2[n11];
            if (bl2) {
                bl2 = false;
                switch (c) {
                    case 'X': 
                    case 'x': {
                        object = this.parseHexColor((short)n7, n8, cArray2, (char)n9, n11);
                        if (object != null) {
                            int n12 = ((Number)((Triple)object).component1()).intValue();
                            int n13 = ((Number)((Triple)object).component2()).intValue();
                            int n14 = ((Number)((Triple)object).component3()).intValue();
                            f5 = (float)n12 / 255.0f;
                            f6 = (float)n13 / 255.0f;
                            f7 = (float)n14 / 255.0f;
                            n11 += 12;
                        }
                        ++n11;
                        continue block7;
                    }
                }
                char c2 = Character.toUpperCase(c);
                if (char2IntArrayMap.containsKey(c2)) {
                    int n15 = char2IntArrayMap.get(c2);
                    int[] nArray = this.toRgb(n15);
                    f5 = (float)nArray[0] / 255.0f;
                    f6 = (float)nArray[1] / 255.0f;
                    f7 = (float)nArray[2] / 255.0f;
                } else if (c2 == 'R') {
                    f5 = (float)color.getRed() / 255.0f;
                    f6 = (float)color.getGreen() / 255.0f;
                    f7 = (float)color.getBlue() / 255.0f;
                    f8 = (float)color.getAlpha() / 255.0f;
                }
                ++n11;
                continue;
            }
            if (bl) {
                object = Theme.INSTANCE.getGradientColor(n11 * n);
                f5 = (float)((Color)object).getRed() / 255.0f;
                f6 = (float)((Color)object).getGreen() / 255.0f;
                f7 = (float)((Color)object).getBlue() / 255.0f;
                f8 = (float)((Color)object).getAlpha() / 255.0f;
            }
            switch (c) {
                case '\u00a7': {
                    bl2 = true;
                    ++n11;
                    continue block7;
                }
                case '\n': {
                    String string3 = string2.substring(n10, n11);
                    Intrinsics.checkNotNullExpressionValue(string3, "substring(...)");
                    f14 += this.getWidth(string3, char2ObjectArrayMap, objectArrayList, l4, font, (byte)n2, font2, f3) * f3;
                    f13 = 0.0f;
                    n10 = n11 + 1;
                    ++n11;
                    continue block7;
                }
            }
            object = this.getGlyph((byte)n4, n5, c, char2ObjectArrayMap, n6, objectArrayList, font, font2);
            if (object != null) {
                if (((TextureRegion)object).getValue() != ' ') {
                    Identifier identifier2 = ((TextureRegion)object).getOwner().getBindToTexture();
                    ColorStop colorStop = new ColorStop(f13, f14, f5, f6, f7, f8, ((TextureRegion)object).getWidth(), ((TextureRegion)object).getHeight(), ((TextureRegion)object).getOwner().V(), ((TextureRegion)object).getOwner().x(), ((TextureRegion)object).getU(), ((TextureRegion)object).getV(), f4, matrix3x2f);
                    if (!Intrinsics.areEqual(identifier, identifier2)) {
                        identifier = identifier2;
                        ObjectList objectList2 = (ObjectList)object2ObjectOpenHashMap.get((Object)identifier2);
                        if (objectList2 == null) {
                            objectList2 = (ObjectList)new ObjectArrayList();
                            ((Map)object2ObjectOpenHashMap).put(identifier2, objectList2);
                        }
                        objectList = objectList2;
                    }
                    ObjectList objectList3 = objectList;
                    Intrinsics.checkNotNull(objectList3);
                    objectList3.add((Object)colorStop);
                }
                f13 += (float)((TextureRegion)object).getWidth();
            }
            ++n11;
        }
        matrix3x2fStack.popMatrix();
    }

    /*
     * Unable to fully structure code
     */
    public final float getWidth(@NotNull String var1_1, @NotNull Char2ObjectArrayMap var2_2, @NotNull ObjectArrayList var3_3, long var4_4, @NotNull Font var6_5, byte var7_6, @NotNull Font var8_7, float var9_8) {
        v0 = var10_9 = (var4_4 << 8 | (long)var7_6 << 56 >>> 56) ^ GlyphCache.a;
        v1 = v0 ^ 90640876773417L;
        var12_10 = (int)(v1 >>> 48);
        var13_11 = (int)(v1 << 16 >>> 32);
        var14_12 = (int)(v1 << 48 >>> 48);
        var15_13 = (int)((v0 ^ 87406739299115L) >>> 48);
        v2 = v0 ^ 71765785273987L;
        var18_14 = (int)(v2 >>> 56);
        var19_15 = (int)(v2 << 8 >>> 32);
        var20_16 = (int)(v2 << 40 >>> 40);
        Intrinsics.checkNotNullParameter(var1_1, "text");
        v3 = Fonts.zkmGetFlag();
        Intrinsics.checkNotNullParameter(var2_2, "allGlyphs");
        var21_17 = v3;
        Intrinsics.checkNotNullParameter(var3_3, "maps");
        Intrinsics.checkNotNullParameter(var6_5, "font");
        Intrinsics.checkNotNullParameter(var8_7, "fallBack");
        var22_18 = NameProtect.INSTANCE.replaceName((char)var15_13, var1_1);
        v4 = this.stripColorCodes((char)var12_10, var13_11, var14_12, var22_18).toCharArray();
        Intrinsics.checkNotNullExpressionValue(v4, "toCharArray(...)");
        var23_19 = v4;
        if (var23_19.length == 0) {
            var24_20 = new char[]{(char)GlyphCache.zkmBootstrap2("j", (int)21140, (long)(2845100283624137159L ^ var10_9))};
            var23_19 = var24_20;
        }
        var24_21 = 0.0f;
        var25_22 = 0.0f;
        var26_23 = var23_19;
        var27_24 = 0;
        var28_25 = var26_23.length;
        while (var27_24 < var28_25) {
            v5 = var29_26 = var26_23[var27_24];
            v6 = var21_17;
            if (var7_6 <= 0) ** GOTO lbl42
            if (v5 == GlyphCache.zkmBootstrap2("j", (int)29017, (long)(1339077482897080895L ^ var10_9))) {
                cfr_temp_0 = var24_21 - 0.0f;
                v5 = (char)(cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1));
                v6 = var21_17;
lbl42:
                // 2 sources

                if (v5 == '\u0000') {
                    v7 = this.getGlyph((byte)var18_14, var19_15, (char)GlyphCache.zkmBootstrap2("j", (int)4746, (long)(4538711366618636790L ^ var10_9)), var2_2, var20_16, var3_3, var6_5, var8_7);
                    var24_21 = v7 != null ? (float)v7.getHeight() : 1.0f / var9_8;
                }
                var25_22 += var24_21;
                var24_21 = 0.0f;
                v8 = var21_17;
                if (var7_6 < 0) continue;
            } else {
                v9 = var30_27 = this.getGlyph((byte)var18_14, var19_15, var29_26, var2_2, var20_16, var3_3, var6_5, var8_7);
                var24_21 = Math.max(v9 == null ? 0.0f : (float)v9.getHeight() / var9_8, var24_21);
            }
            ++var27_24;
            v8 = var21_17;
        }
        return var24_21 + var25_22;
    }

    public final void flush(@NotNull Object2ObjectOpenHashMap object2ObjectOpenHashMap, long l) {
        long l2 = (l = a ^ l) ^ 0x45F8C3C6D4DL;
        String[] stringArray = Fonts.zkmGetFlag();
        Intrinsics.checkNotNullParameter(object2ObjectOpenHashMap, "batchedDraws");
        String[] stringArray2 = stringArray;
        if (object2ObjectOpenHashMap.isEmpty()) {
            return;
        }
        ObjectIterator objectIterator = object2ObjectOpenHashMap.object2ObjectEntrySet().iterator();
        Intrinsics.checkNotNullExpressionValue(objectIterator, "iterator(...)");
        ObjectIterator objectIterator2 = objectIterator;
        block0: while (objectIterator2.hasNext()) {
            GpuTextureView gpuTextureView;
            String[] stringArray3;
            ObjectListIterator objectListIterator;
            VertexFormat vertexFormat;
            Object2ObjectMap.Entry entry = (Object2ObjectMap.Entry)objectIterator2.next();
            Intrinsics.checkNotNull(entry);
            Identifier identifier = (Identifier)((Map.Entry)entry).getKey();
            ObjectList objectList = (ObjectList)((Map.Entry)entry).getValue();
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().getTextureManager().getTexture(identifier).getGlTextureView(), "getTextureView(...)");
            Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_TEXTURE_COLOR, "POSITION_TEX_COLOR");
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n = 4;
            boolean bl = false;
            int n2 = objectList.size();
            BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat, n2, bl, n, defaultConstructorMarker);
            Intrinsics.checkNotNullExpressionValue(objectList.iterator(), "iterator(...)");
            while (objectListIterator.hasNext()) {
                ColorStop colorStop = (ColorStop)objectListIterator.next();
                Matrix3x2f matrix3x2f = colorStop.getMatrix();
                float f = colorStop.getAtX();
                float f2 = colorStop.getAtY();
                float f3 = colorStop.getW();
                float f4 = colorStop.getH();
                float f5 = (float)colorStop.getU() / (float)colorStop.getOw();
                float f6 = (float)colorStop.getV() / (float)colorStop.getOh();
                float f7 = (float)(colorStop.getU() + colorStop.getW()) / (float)colorStop.getOw();
                float f8 = (float)(colorStop.getV() + colorStop.getH()) / (float)colorStop.getOh();
                float f9 = colorStop.s();
                float f10 = colorStop.getB();
                float f11 = colorStop.getG();
                batchedBuffer.vertex(matrix3x2f, f, f2 + f4, colorStop.getZ()).texture(f5, f8).color(colorStop.getR(), f11, f10, f9);
                float f12 = colorStop.s();
                float f13 = colorStop.getB();
                float f14 = colorStop.getG();
                batchedBuffer.vertex(matrix3x2f, f + f3, f2 + f4, colorStop.getZ()).texture(f7, f8).color(colorStop.getR(), f14, f13, f12);
                float f15 = colorStop.s();
                float f16 = colorStop.getB();
                float f17 = colorStop.getG();
                batchedBuffer.vertex(matrix3x2f, f + f3, f2, colorStop.getZ()).texture(f7, f6).color(colorStop.getR(), f17, f16, f15);
                float f18 = colorStop.s();
                float f19 = colorStop.getB();
                float f20 = colorStop.getG();
                batchedBuffer.vertex(matrix3x2f, f, f2, colorStop.getZ()).texture(f5, f6).color(colorStop.getR(), f20, f19, f18);
                stringArray3 = stringArray2;
                if (l <= 0L) continue block0;
            }
            RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultColoredTexture2();
            Framebuffer framebuffer = Globals.getMc().getFramebuffer();
            Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
            BatchedBuffer.draw$default(l2, batchedBuffer, renderPipeline, framebuffer, null, null, new Matrix3x2f(), MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView)), (int)GlyphCache.zkmBootstrap2("j", (int)14376, (long)(0x73375FB5786D44D2L ^ l)), null);
            stringArray3 = stringArray2;
        }
        object2ObjectOpenHashMap.clear();
        if (l >= 0L) {
            // empty if block
        }
    }

    /*
     * Unable to fully structure code
     */
    public final float getHeight(@NotNull String var1_1, @NotNull Char2ObjectArrayMap var2_2, @NotNull ObjectArrayList var3_3, char var4_4, @NotNull Font var5_5, long var6_6, @NotNull Font var8_7, float var9_8) {
        v0 = var10_9 = ((long)var4_4 << 48 | var6_6 << 16 >>> 16) ^ GlyphCache.a;
        v1 = v0 ^ 15687156628694L;
        var12_10 = (int)(v1 >>> 48);
        var13_11 = (int)(v1 << 16 >>> 32);
        var14_12 = (int)(v1 << 48 >>> 48);
        var15_13 = (int)((v0 ^ 21257898903508L) >>> 48);
        v2 = v0 ^ 32361706535548L;
        var18_14 = (int)(v2 >>> 56);
        var19_15 = (int)(v2 << 8 >>> 32);
        var20_16 = (int)(v2 << 40 >>> 40);
        Intrinsics.checkNotNullParameter(var1_1, "text");
        Intrinsics.checkNotNullParameter(var2_2, "allGlyphs");
        Intrinsics.checkNotNullParameter(var3_3, "maps");
        Intrinsics.checkNotNullParameter(var5_5, "font");
        Intrinsics.checkNotNullParameter(var8_7, "fallBack");
        var21_17 = NameProtect.INSTANCE.replaceName((char)var15_13, var1_1);
        v3 = this.stripColorCodes((char)var12_10, var13_11, var14_12, var21_17).toCharArray();
        Intrinsics.checkNotNullExpressionValue(v3, "toCharArray(...)");
        var22_18 = v3;
        var23_19 = 0.0f;
        var24_20 = 0.0f;
        for (char var27_23 : var22_18) {
            block7: {
                block6: {
                    if (var4_4 < '\u0000') break block6;
                    if (var27_23 != GlyphCache.zkmBootstrap2("j", (int)272, (long)(520033989662191243L ^ var10_9))) break block7;
                    var24_20 = Math.max(var23_19, var24_20);
                    var23_19 = 0.0f;
                }
                if (var4_4 >= '\u0000') continue;
            }
            var28_24 = this.getGlyph((byte)var18_14, var19_15, var27_23, var2_2, var20_16, var3_3, var5_5, var8_7);
            if (var4_4 < '\u0000') ** GOTO lbl-1000
            v4 = var28_24;
            if (v4 == null) lbl-1000:
            // 2 sources

            {
                v5 = 0.0f;
            } else {
                v5 = (float)v4.getWidth() / var9_8;
            }
            var23_19 += v5;
            if (var4_4 >= '\u0000') continue;
        }
        return Math.max(var23_19, var24_20);
    }

    private final int isDigit(char c, long l) {
        Object object;
        block10: {
            block18: {
                block17: {
                    Object object2;
                    block16: {
                        char c2;
                        block14: {
                            block15: {
                                block13: {
                                    block12: {
                                        Object object3;
                                        block11: {
                                            block8: {
                                                block9: {
                                                    l = a ^ l;
                                                    c2 = c;
                                                    if (GlyphCache.zkmBootstrap2("j", (int)867, (long)(0x1D65CD036EBD3E2L ^ l)) <= c2) {
                                                        object = c2;
                                                        if (l >= 0L) {
                                                            object = object < GlyphCache.zkmBootstrap2("j", (int)15534, (long)(0x532EF55A5242EC32L ^ l)) ? (char)'\u0001' : '\u0000';
                                                        }
                                                    } else {
                                                        object = '\u0000';
                                                    }
                                                    if (l < 0L) break block8;
                                                    if (object == '\u0000') break block9;
                                                    object = c - GlyphCache.zkmBootstrap2("j", (int)25143, (long)(0x2CF70D10C2FB290L ^ l));
                                                    break block10;
                                                }
                                                object = GlyphCache.zkmBootstrap2("j", (int)25262, (long)(0x54BF3E67E8EA3237L ^ l));
                                            }
                                            object3 = c2;
                                            if (l <= 0L) break block11;
                                            if (object > object3) break block12;
                                            object = c2;
                                            if (l < 0L) break block13;
                                            object3 = GlyphCache.zkmBootstrap2("j", (int)24987, (long)(0xF70126E5F8B10DL ^ l));
                                        }
                                        object = object < object3 ? 1 : '\u0000';
                                        break block13;
                                    }
                                    object = '\u0000';
                                }
                                if (l <= 0L) break block14;
                                if (object == 0) break block15;
                                object = c - GlyphCache.zkmBootstrap2("j", (int)25262, (long)(0x54BF3E67E8EA3237L ^ l)) + GlyphCache.zkmBootstrap2("j", (int)272, (long)(0x737B35D382851BAL ^ l));
                                break block10;
                            }
                            object = GlyphCache.zkmBootstrap2("j", (int)7433, (long)(0x6101C31D25BCCDB1L ^ l));
                        }
                        object2 = c2;
                        if (l <= 0L) break block16;
                        if (object > object2) break block17;
                        object = c2;
                        if (l <= 0L) break block18;
                        object2 = GlyphCache.zkmBootstrap2("j", (int)7948, (long)(0x501A00DCC6AFCFB0L ^ l));
                    }
                    object = object < object2 ? 1 : 0;
                    break block18;
                }
                object = 0;
            }
            if (l >= 0L) {
                object = object != 0 ? c - GlyphCache.zkmBootstrap2("j", (int)22221, (long)(0x4A2BE31B1033867AL ^ l)) + GlyphCache.zkmBootstrap2("j", (int)272, (long)(0x737B35D382851BAL ^ l)) : -1;
            }
        }
        return object;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private final boolean isHexColor(char[] var1_1, int var2_2, short var3_3, int var4_4, int var5_5) {
        block14: {
            var6_6 = ((long)var3_3 << 48 | (long)var4_4 << 32 >>> 16 | (long)var5_5 << 48 >>> 48) ^ GlyphCache.a;
            var8_7 = var6_6 ^ 54687924389008L;
            if (var2_2 + GlyphCache.zkmBootstrap2("j", (int)12332, (long)(1666462759264111826L ^ var6_6)) >= var1_1.length) {
                return false;
            }
            v0 = var1_1[var2_2];
            if (var4_4 > 0) {
                if (v0 != GlyphCache.zkmBootstrap2("j", (int)676, (long)(3453385140488732273L ^ var6_6))) {
                    return false;
                }
                v0 = var1_1[var2_2 + 1];
            }
            v1 = var10_8 = v0;
            if (var4_4 >= 0) {
                if (v1 != GlyphCache.zkmBootstrap2("j", (int)6940, (long)(2335672838999828454L ^ var6_6))) {
                    v1 = var10_8;
                    if (var4_4 >= 0) {
                        if (v1 != GlyphCache.zkmBootstrap2("j", (int)14477, (long)(5692929970442159150L ^ var6_6))) {
                            return false;
                        } else {
                            ** GOTO lbl-1000
                        }
                    }
                } else lbl-1000:
                // 3 sources

                {
                    v1 = '\u0000';
                }
            }
            var11_9 = var2_2 + 2;
            var12_10 /* !! */  = GlyphCache.zkmBootstrap2("j", (int)26443, (long)(2288777334271089567L ^ var6_6));
            for (var13_11 = 0; var13_11 < var12_10 /* !! */ ; ++var13_11) {
                v2 = var1_1[var11_9];
                if (var5_5 >= 0) {
                    if (var3_3 >= 0) {
                        if (v2 != GlyphCache.zkmBootstrap2("j", (int)676, (long)(3453385140488732273L ^ var6_6))) {
                            return false;
                        }
                        v3 = GlyphCache.INSTANCE.isDigit(var1_1[var11_9 + 1], var8_7);
                    }
                    if (var5_5 > 0) {
                        if (v3 < 0) {
                            return false;
                        }
                        v3 = var11_9 + 2;
                    }
                    var11_9 = v3;
                    if (var3_3 >= 0) continue;
                }
                break block14;
            }
            v2 = '\u0001';
        }
        return (boolean)v2;
    }

    private final Triple parseHexColor(short s, int n, char[] cArray, char c, int n2) {
        int n3;
        Object object;
        int n4;
        Object object2;
        block17: {
            int n5;
            CallSite callSite;
            long l;
            long l2;
            block22: {
                Object object3;
                block20: {
                    block21: {
                        block19: {
                            block18: {
                                l2 = ((long)s << 48 | (long)n << 32 >>> 16 | (long)c << 48 >>> 48) ^ a;
                                l = l2 ^ 0x3A86D82D30CDL;
                                callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1017283242758653722L, (long)l2);
                                n5 = n2 - 1;
                                if (callSite == null) break block18;
                                if (n5 < 0) break block19;
                                n5 = cArray[n2 - 1];
                            }
                            object3 = GlyphCache.zkmBootstrap2("j", (int)676, (long)(0x2FECE846D512CA2CL ^ l2));
                            if (c < '\u0000' || callSite == null) break block20;
                            if (n5 == object3) break block21;
                        }
                        return null;
                    }
                    n5 = n2;
                    object3 = GlyphCache.zkmBootstrap2("j", (int)11120, (long)(0x39156F8D18D5E3F1L ^ l2));
                    if (c < '\u0000') break block20;
                    n5 = n5 + object3;
                    if (callSite == null) break block22;
                    object3 = cArray.length;
                }
                if (n5 >= object3) {
                    return null;
                }
                n5 = 0;
            }
            int n6 = n5;
            int n7 = n2 + 1;
            object2 = GlyphCache.zkmBootstrap2("j", (int)25142, (long)(0x3A543C4A76A52AAAL ^ l2));
            n4 = 0;
            while (n4 < object2) {
                int n8;
                int n9 = cArray[n7];
                if (callSite != null) {
                    object = GlyphCache.zkmBootstrap2("j", (int)676, (long)(0x2FECE846D512CA2CL ^ l2));
                    if (callSite == null) break block17;
                    if (n9 != object) {
                        return null;
                    }
                    n9 = n8 = INSTANCE.isDigit(cArray[n7 + 1], l);
                }
                if (s >= 0) {
                    int n10 = n8;
                    if (callSite != null) {
                        if (n10 < 0) {
                            return null;
                        }
                        n6 = n6 << 4 | n8;
                        n10 = n7 + 2;
                    }
                    n7 = n10;
                    ++n4;
                }
                if (callSite != null) continue;
            }
            object2 = n6 >> GlyphCache.zkmBootstrap2("j", (int)28576, (long)(0x4CF7C8906EA2A755L ^ l2)) & GlyphCache.zkmBootstrap2("j", (int)1331, (long)(0x5ED961E4DD1D4D80L ^ l2));
            n4 = n6 >> GlyphCache.zkmBootstrap2("j", (int)12598, (long)(0x71EBBC98996F9CFL ^ l2)) & GlyphCache.zkmBootstrap2("j", (int)1331, (long)(0x5ED961E4DD1D4D80L ^ l2));
            n3 = n6;
            object = 1331;
            if (c >= '\u0000') {
                object = GlyphCache.zkmBootstrap2("j", (int)object, (long)(0x5ED961E4DD1D4D80L ^ l2));
            }
        }
        int n11 = n3 & object;
        return new Triple<Integer, Integer, Integer>((int)object2, n4, n11);
    }

    private static final String randomChar(int n) {
        return String.valueOf((char)new Random().nextInt(97, 123));
    }

    private static final ObjectList drawString$lambda$0(Object object) {
        return (ObjectList)new ObjectArrayList();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        GlyphCache.a = ZkmKeys.a(-8434565965091478071L, 633131840613666972L, MethodHandles.lookup().lookupClass()).a(118144023414312L);
                        var20 = GlyphCache.a ^ 70104833297527L;
                        GlyphCache.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[35];
                        var16_4 = 0;
                        var15_5 = "\u0088\u0019\u0091\u00e7\u0013\u00a4\r\u0012\u0089\bL\u00b8\u0019l\u00a5a\u0010\u00aa$hZ\u00cb~\u00afb\f\u000f\u0086kt\u00af4O\u0018\u0017E:\u00b2*D\u0018\u00bb\u0014ow7`\u00c0t)\u00b2=\u0013\u009fc\u00ca~:\u0018\u00c7\u00a0\u009c\u00b3C\u00fb\u0005E\u00f9\f\u00a7\u00f8\u001a\u0007\u00ab\u001c\u007f\u008d\u0014)y<\u00c8\u00fc\u0018\u00f1y\u0097F{\u00a8\u00d2\u00d0\u00f1\u00e7\u00bbI\u00ado~\u00b3\u008b\u0096\u00c8\u00c5(\u00e8\u0096\u0019(\u00a8\u0017O\u00fb\u00c4\u0004D}\u00d0\u0013\u00b3\u0082\u00ef*3Q\u00d6\u0099\u00f1@\u0019\u007f,Gi\u00e9\u00c2\u008cIB\u00e2\t\u0017.\u00c0\u00fc%\u0014\u0093\u00f1 E\u00d2<\u0002\u00a4\u00d8\u00da\u00eat\u009e\u00e1\u0083$\u00e0\u0098:<\u00c3\u00ecb\u001b\u00e3&\u008f\u00c0\u00a3\f\u00bdo\u00c0-\u0011\u0018jm\n=H>\u00b1r:\b\u00d7\u00f8\u0005\u00eby8\u00e6\u00d1m\u00d9\u00e9\u0018\u00d4\u00f1\u0010\u00809[\u00af\u001d-v\u00de\u00b8\u00d0\u00f9L\u00f0\u008b\u00efh(\u00f7\u00e6\u00d9%M\u00a6\u00a4\u0003\u0090s\u00aaNb\u00b1\u00c1s=[\\\u0010y\u00d6\u00b29\u0091\u0085\u00cb\n\u00fd\u00e2\u00fc\u000b\u00dd\u00f7\u0095\u00ee`\u00f5I\u00e3 \u0019\u00a2\u00dap\u0017\\\u00d5\u00c18\u00ef\u00abH{S\u00f2\u000fC\\Tdb\u00902o\u00f0:r\u00d0Lc\u0087J\u0018 \u00d5\u00ea\u0016i|\u00bc\u00f3\u0004\u00cc\u00cf\u009a\u00bc' \u0090E\u0085b\u000e\u00b0\u00d5\\x\u0010\u00ed\u000f6Y\u00d0\u0001M!\u008d/F\u0001\u00a5s`\u00a8\u0018\u001fg\u00f1A^Z\u0099\u00a3j\u00d4\u00ebb\u0005n#+\"\u0093\u0003R\u00f9<>\u0087\u0010$ \u00f7u\u0015\u00de\u0098\u00f1\u009b\u008av=%\u00b7\u00ba\u00a5\u0010\u00a3\u00855\u00b3\u009c\u0099*o\u00fb9\u00afq\u0090{C\u00d58\u00a6D\u0019\u00ff8%\u00bd\u00edP\u009cjta4m\u00dbjP\u00a1X\u00bc5\u0083\u00d33\u00eb\u001d\u00e6\u00fb[\u00851\u0098\u00a8\u00e6o!K\u00fe\u0000@\u0086O\u00f6j\u00a2T\u0005\u0084( \u00f2\u00fa\u00f1\u00f7\r(\u00bc|\u00f6\u0019,&\u00db\u009e\u0099\u00e2\u0005{\u00bc\u0014\f\u00f6SM\u0084 \u00bdJS(w\u00a1\u00f6{U\u00a2\u00ae\u00d5\n\u00f8tGzz\u00bf\u00ae \u00aa&\u00adO&\u00de\u0085p\u00e7-k\u00f53#\u00e3\u001ce\u00b9X\u00de\t\u0094\u00f5\u0011W\u00c5\u00af\u00fe4z\u00ac{\u0010\u00f2p\u00a5&\u001c\u00f7\u00bc\u00a2\u00f4\u00d7loW9\u00ec\u00cb \u00c1\u00bd\u009c\u00b5\u00eeL\u00f4\u00da\u00bb\u00dde\u00eb\u00f7\u0007\u00bd3r+\u00d9 C\u0083\u0083Jz\u009e\u00d0e\u00fe\u00f5\u00b8>\u0010\u00d5\u00aeA\u00ac\n\u0004\u00c5-\u0019\u0095\u008b\u00bb\u0015Sm\u009d \u0081n\u00ffi.\u0085\u00fd\u00a4q\t\u00d8\u00f0D\u00fc\u00b9Q\u00e5(\u0084E\u00ea\u00f8\u0019\u00c4P/H>\u001f0\u00ce\u008b8.\u0004\u00dd\u00de\u0011\u0007\u001d\u0084\u00f7\u00d9\u009c3\u0003\u0091iK\u00ba\u0006oA\u0080\u008c\u00e8\u0090\u00b8\u008d\u0011\u0016/)\u0088h\u00f6S\u008e\u00a0\u0015\u00f9!\u00abf\u0010\u00ad\u00f3>r\u00f9NJ97\u00c7\u00f9\u00adj\u00a5\u0010\u00c3/hAI\u0002\u00fa\u00dd\"\u0094\u00b2\u00bahqQ\u0007(\u0095$\u0092\u00a3\u00c7[IvL\u0015\u00e9a\u0002b]]\u0085\u00b3B\u00f3\u00caH\u0015\u009c\u00b9\u0099\u001bW\u00c8]'a\u00a4\u0013(1\u007f\u0086\u0015\u00b0\u0010\u00f4p\u0080u\u00b6\u000f\u00e8\u00c3\u0088\u0007\u001dL\u008e\u00c3x\u00d8\u0010(\u00a3\u00be\u00bf\u00ac\b\u00a5\u00c2\u00e1Z\u008au\nB\u009f\u0087\u0018\u00cc\"(\u00e2h\u001f\u0098\r7f\u00a6\u00d0#\u00f5\u00f0\b\u00c9\u00db\u00c8\u00b7\u0014ey\u0089 #\u00cbJ'\u00b8\u00f5\u00df\u00b7\u00cdn\u00fewW\u00e7dEB[\u00e7\u00e1\u00f4\u00ca\u00ca\u00c4a\u00d3z>\u00810\u00f7z \u009c\u0012Z@N\u00fd\u001e|\u0018\u0012\u00bde\u0098n\u00ed\u00ca#\u00a34\u00bf\u009a\u00a7\u0001\u00d2Z\u00b5\u00e3\u000f\u00e5\u000ec\u00d6 4f\u00f7a\u00d5J\u00b1\u001f\u0088\u00a1\u00fb\u00e9]{\u00c6\u0018P\u008d?\u00f70\u00a5\u00ffC0\u00e64J\u008d\u0098\u008d0 W\u0013\u00b3\u0010\u00aae\u00d0\u00ee\u0003\u00c7\u008eG\u0090\u00d6l\u0096\u00a6@\u00dd\u00fb\u0014\u0098c\u00f8UF\u00ceV\u00b4IO\u00c9";
                        var17_6 = "\u0088\u0019\u0091\u00e7\u0013\u00a4\r\u0012\u0089\bL\u00b8\u0019l\u00a5a\u0010\u00aa$hZ\u00cb~\u00afb\f\u000f\u0086kt\u00af4O\u0018\u0017E:\u00b2*D\u0018\u00bb\u0014ow7`\u00c0t)\u00b2=\u0013\u009fc\u00ca~:\u0018\u00c7\u00a0\u009c\u00b3C\u00fb\u0005E\u00f9\f\u00a7\u00f8\u001a\u0007\u00ab\u001c\u007f\u008d\u0014)y<\u00c8\u00fc\u0018\u00f1y\u0097F{\u00a8\u00d2\u00d0\u00f1\u00e7\u00bbI\u00ado~\u00b3\u008b\u0096\u00c8\u00c5(\u00e8\u0096\u0019(\u00a8\u0017O\u00fb\u00c4\u0004D}\u00d0\u0013\u00b3\u0082\u00ef*3Q\u00d6\u0099\u00f1@\u0019\u007f,Gi\u00e9\u00c2\u008cIB\u00e2\t\u0017.\u00c0\u00fc%\u0014\u0093\u00f1 E\u00d2<\u0002\u00a4\u00d8\u00da\u00eat\u009e\u00e1\u0083$\u00e0\u0098:<\u00c3\u00ecb\u001b\u00e3&\u008f\u00c0\u00a3\f\u00bdo\u00c0-\u0011\u0018jm\n=H>\u00b1r:\b\u00d7\u00f8\u0005\u00eby8\u00e6\u00d1m\u00d9\u00e9\u0018\u00d4\u00f1\u0010\u00809[\u00af\u001d-v\u00de\u00b8\u00d0\u00f9L\u00f0\u008b\u00efh(\u00f7\u00e6\u00d9%M\u00a6\u00a4\u0003\u0090s\u00aaNb\u00b1\u00c1s=[\\\u0010y\u00d6\u00b29\u0091\u0085\u00cb\n\u00fd\u00e2\u00fc\u000b\u00dd\u00f7\u0095\u00ee`\u00f5I\u00e3 \u0019\u00a2\u00dap\u0017\\\u00d5\u00c18\u00ef\u00abH{S\u00f2\u000fC\\Tdb\u00902o\u00f0:r\u00d0Lc\u0087J\u0018 \u00d5\u00ea\u0016i|\u00bc\u00f3\u0004\u00cc\u00cf\u009a\u00bc' \u0090E\u0085b\u000e\u00b0\u00d5\\x\u0010\u00ed\u000f6Y\u00d0\u0001M!\u008d/F\u0001\u00a5s`\u00a8\u0018\u001fg\u00f1A^Z\u0099\u00a3j\u00d4\u00ebb\u0005n#+\"\u0093\u0003R\u00f9<>\u0087\u0010$ \u00f7u\u0015\u00de\u0098\u00f1\u009b\u008av=%\u00b7\u00ba\u00a5\u0010\u00a3\u00855\u00b3\u009c\u0099*o\u00fb9\u00afq\u0090{C\u00d58\u00a6D\u0019\u00ff8%\u00bd\u00edP\u009cjta4m\u00dbjP\u00a1X\u00bc5\u0083\u00d33\u00eb\u001d\u00e6\u00fb[\u00851\u0098\u00a8\u00e6o!K\u00fe\u0000@\u0086O\u00f6j\u00a2T\u0005\u0084( \u00f2\u00fa\u00f1\u00f7\r(\u00bc|\u00f6\u0019,&\u00db\u009e\u0099\u00e2\u0005{\u00bc\u0014\f\u00f6SM\u0084 \u00bdJS(w\u00a1\u00f6{U\u00a2\u00ae\u00d5\n\u00f8tGzz\u00bf\u00ae \u00aa&\u00adO&\u00de\u0085p\u00e7-k\u00f53#\u00e3\u001ce\u00b9X\u00de\t\u0094\u00f5\u0011W\u00c5\u00af\u00fe4z\u00ac{\u0010\u00f2p\u00a5&\u001c\u00f7\u00bc\u00a2\u00f4\u00d7loW9\u00ec\u00cb \u00c1\u00bd\u009c\u00b5\u00eeL\u00f4\u00da\u00bb\u00dde\u00eb\u00f7\u0007\u00bd3r+\u00d9 C\u0083\u0083Jz\u009e\u00d0e\u00fe\u00f5\u00b8>\u0010\u00d5\u00aeA\u00ac\n\u0004\u00c5-\u0019\u0095\u008b\u00bb\u0015Sm\u009d \u0081n\u00ffi.\u0085\u00fd\u00a4q\t\u00d8\u00f0D\u00fc\u00b9Q\u00e5(\u0084E\u00ea\u00f8\u0019\u00c4P/H>\u001f0\u00ce\u008b8.\u0004\u00dd\u00de\u0011\u0007\u001d\u0084\u00f7\u00d9\u009c3\u0003\u0091iK\u00ba\u0006oA\u0080\u008c\u00e8\u0090\u00b8\u008d\u0011\u0016/)\u0088h\u00f6S\u008e\u00a0\u0015\u00f9!\u00abf\u0010\u00ad\u00f3>r\u00f9NJ97\u00c7\u00f9\u00adj\u00a5\u0010\u00c3/hAI\u0002\u00fa\u00dd\"\u0094\u00b2\u00bahqQ\u0007(\u0095$\u0092\u00a3\u00c7[IvL\u0015\u00e9a\u0002b]]\u0085\u00b3B\u00f3\u00caH\u0015\u009c\u00b9\u0099\u001bW\u00c8]'a\u00a4\u0013(1\u007f\u0086\u0015\u00b0\u0010\u00f4p\u0080u\u00b6\u000f\u00e8\u00c3\u0088\u0007\u001dL\u008e\u00c3x\u00d8\u0010(\u00a3\u00be\u00bf\u00ac\b\u00a5\u00c2\u00e1Z\u008au\nB\u009f\u0087\u0018\u00cc\"(\u00e2h\u001f\u0098\r7f\u00a6\u00d0#\u00f5\u00f0\b\u00c9\u00db\u00c8\u00b7\u0014ey\u0089 #\u00cbJ'\u00b8\u00f5\u00df\u00b7\u00cdn\u00fewW\u00e7dEB[\u00e7\u00e1\u00f4\u00ca\u00ca\u00c4a\u00d3z>\u00810\u00f7z \u009c\u0012Z@N\u00fd\u001e|\u0018\u0012\u00bde\u0098n\u00ed\u00ca#\u00a34\u00bf\u009a\u00a7\u0001\u00d2Z\u00b5\u00e3\u000f\u00e5\u000ec\u00d6 4f\u00f7a\u00d5J\u00b1\u001f\u0088\u00a1\u00fb\u00e9]{\u00c6\u0018P\u008d?\u00f70\u00a5\u00ffC0\u00e64J\u008d\u0098\u008d0 W\u0013\u00b3\u0010\u00aae\u00d0\u00ee\u0003\u00c7\u008eG\u0090\u00d6l\u0096\u00a6@\u00dd\u00fb\u0014\u0098c\u00f8UF\u00ceV\u00b4IO\u00c9".length();
                        var14_7 = 16;
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
                            var18_3[var16_4++] = GlyphCache.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "#\u008e\u00c9\u00161\u00ca\u00cc\u00b5\u0090\u0083\u0098\u0006\u0096\u0094\u00f1\u00efo;:~8\u0006m\u00b6n\u00e73\u008e\u0092C|\u00d2\u008e\u001d\u0081\u0083V\u0082\u00d7D\u0018\"\u0092p\u00f5\u00c28i\u0013\u007f\u0090\u00ecm%CLT\u00c7\u00e0\u00fb\u009cSP\u0087\u00e6";
                            var17_6 = "#\u008e\u00c9\u00161\u00ca\u00cc\u00b5\u0090\u0083\u0098\u0006\u0096\u0094\u00f1\u00efo;:~8\u0006m\u00b6n\u00e73\u008e\u0092C|\u00d2\u008e\u001d\u0081\u0083V\u0082\u00d7D\u0018\"\u0092p\u00f5\u00c28i\u0013\u007f\u0090\u00ecm%CLT\u00c7\u00e0\u00fb\u009cSP\u0087\u00e6".length();
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
                            var18_3[var16_4++] = GlyphCache.zkmDecodeUtf8(var19_9).intern();
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
                GlyphCache.b = var18_3;
                GlyphCache.c = new String[35];
                GlyphCache.g = new HashMap<K, V>(13);
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
                var6_12 = new long[73];
                var3_13 = 0;
                var4_14 = "P@\u00e3\u00e6\u00ac\u009b\u001e\u00e9w\u00f2I=z\u00cf\u00ba,\u00cd\u00f8\u001a^--\u00d5\u0094U\u0088\f)\u0089Qg\u001cL\u00150\u0013\u00b5\u00af\u009dj\u00c1\u00c1\u00a5\u0015\u00f3\u0090\u00da\u0011\u0014\u0096\u00db\u0003\u007f\u00c2\u0090\u00f5\u00b4\u00e5\u00d8\u00ba\u00f2@J\u00ad\u0099\u000bkF\u00b9\u00f0m\u0003\u00f2!\u00a8\u00aap\u00dc\u0090\"\u0096\u00f4#\u00c7\u0097\u00cd\u008e\u00fc\u008e\u00d3UP\u00ed\u00ac\u00cd\u0003\u00c7o\u000b\u0094\u00861\u00e3\u0000`\u00cf\u0002o\u0090\u0098,0\u00c9|\u0013\u00bdi]\u00f1o\u001b.\u000e\u0083\u00eb\u00f7RDQLHK\u0098\u00c2~! \u0017$\u00b4\u0006\u00c1G\u0005\u0006\u008bIA1\u0007\u0084\u00e7Xz8\u0092\u0019'^\u00fe\u0019\u00f2 .\u00b8\u00aa|\u00ac\u00e3\u0088\u0018\u00ae\u00eb\u00c6p\u00d6\u00db\u0001\u00e5h<\u0018\u00e56\u0095&\u00de\u0006h1\u00cd\u00a5\u00df\u00ee\u0092\u0085\u009bR'\u00f9\u0004\u00e0\u00f1\u0082\u001f\u00ce\n\u00c5\u00a2u\u0011\u00a2\u00c1\u009d\u00c6G\u00ba\u00c8*y\u0091\u00ec\u00bbkY\u00ac<\u00fe\u00ae\rM+m\u000f\u00da:\u00f8\u00df\b\u00a1a\u00c9\u0091\u0011\u00b4L\u00dezA\u0083(\u00df\u008a\u00a7\u00d7\u00c7/\u00a4\u00e66\u00a2\u0097\u00c1\u00ba\u009c\u009f\u0011w\u0097x\u0090\u0016R\u00ac\u000f\u00c7\u00e59N\u00f5\u000e\u00ba\u0011KX\u00c8\u00c0\u00df\u0081\n\u00c0\u00f9\u0090\u009e\u00ed\u00be\u00bc\u00d5'\u00d7\u0014(D\u000b\u00c2\u00de\u0002L:;v\f9\u00cd\u00ce\u0001\u00c3\u0090\u0017\u00c9\u0096\u00f2\u00b8\u00b3u\t\u0000\u009a\u00fa\u00d5bD\u00f2]|\u0000{\u00b4M\u00c4\u0080\u00fa}&\u001e\u001f\u00f9.l\u00df\u00a8u\u00d45\u0017\u00cf\u00df\"\u00der\u00c3\u00e8\u00e7*>\u000b\b\u0096\u00b5\u00e4\u00b3\u0014ZK\u00b6\u00cd\u00d0C\t\u00c4\u00b1<15$\u00e4Q\u00b6\n\u0098\u00bd*\u00c1\u00b1\u00eee\u0088+\u0018\u00c2\u00ad-\u00dca\u00cfS\u0082F\u00c9\u0084\u001c\u00d1\u0006\u00ee\u000b\u00ff{\u00f8\u00e9\u00a0$o\u0098 \u00954<v\u00d6H\u00f4\u001d\u00f8\u00b6f\u0091G\u001f7\u00eb\u00a9\u009ao\u00cb\u00e6\u0093\u0002\u00bb\u00dcoX\u00b4\u0015\u009f\u0011\u00e1\u00bb3\u00b3`\u0019\u0090\u0013F\u00cf\u00f5e\u00f1\u007fh;E}\u00f0\u0087\u0002\u00a5\u00ad\u00a4EE\u00eb;\u00dd\u0011^:\u00a7\u00a8j\u00abD>\u0087\u0092^W`\u000b\u00c3\u00b9}\u00ec\u00ee\u00f7r\u0083/\u0095\u0094\u00a0hS\u00e5\u00a8sW\u00b2?J\u0015{\u0003}!\u00ea\u0004\u0091J\u008e\u00e7\u00e3o\u00ed\u00c9\u00cd$p\u0088\u00bb\u00af\u00bc\u00ebb\u0002=\u00dfS\u00cef\u00db\u00ceo\u00a4\u00a9U~(\u007f\u0094+\u00b9\u008a\u0093\u00c5\u0086\u00c5R";
                var5_15 = "P@\u00e3\u00e6\u00ac\u009b\u001e\u00e9w\u00f2I=z\u00cf\u00ba,\u00cd\u00f8\u001a^--\u00d5\u0094U\u0088\f)\u0089Qg\u001cL\u00150\u0013\u00b5\u00af\u009dj\u00c1\u00c1\u00a5\u0015\u00f3\u0090\u00da\u0011\u0014\u0096\u00db\u0003\u007f\u00c2\u0090\u00f5\u00b4\u00e5\u00d8\u00ba\u00f2@J\u00ad\u0099\u000bkF\u00b9\u00f0m\u0003\u00f2!\u00a8\u00aap\u00dc\u0090\"\u0096\u00f4#\u00c7\u0097\u00cd\u008e\u00fc\u008e\u00d3UP\u00ed\u00ac\u00cd\u0003\u00c7o\u000b\u0094\u00861\u00e3\u0000`\u00cf\u0002o\u0090\u0098,0\u00c9|\u0013\u00bdi]\u00f1o\u001b.\u000e\u0083\u00eb\u00f7RDQLHK\u0098\u00c2~! \u0017$\u00b4\u0006\u00c1G\u0005\u0006\u008bIA1\u0007\u0084\u00e7Xz8\u0092\u0019'^\u00fe\u0019\u00f2 .\u00b8\u00aa|\u00ac\u00e3\u0088\u0018\u00ae\u00eb\u00c6p\u00d6\u00db\u0001\u00e5h<\u0018\u00e56\u0095&\u00de\u0006h1\u00cd\u00a5\u00df\u00ee\u0092\u0085\u009bR'\u00f9\u0004\u00e0\u00f1\u0082\u001f\u00ce\n\u00c5\u00a2u\u0011\u00a2\u00c1\u009d\u00c6G\u00ba\u00c8*y\u0091\u00ec\u00bbkY\u00ac<\u00fe\u00ae\rM+m\u000f\u00da:\u00f8\u00df\b\u00a1a\u00c9\u0091\u0011\u00b4L\u00dezA\u0083(\u00df\u008a\u00a7\u00d7\u00c7/\u00a4\u00e66\u00a2\u0097\u00c1\u00ba\u009c\u009f\u0011w\u0097x\u0090\u0016R\u00ac\u000f\u00c7\u00e59N\u00f5\u000e\u00ba\u0011KX\u00c8\u00c0\u00df\u0081\n\u00c0\u00f9\u0090\u009e\u00ed\u00be\u00bc\u00d5'\u00d7\u0014(D\u000b\u00c2\u00de\u0002L:;v\f9\u00cd\u00ce\u0001\u00c3\u0090\u0017\u00c9\u0096\u00f2\u00b8\u00b3u\t\u0000\u009a\u00fa\u00d5bD\u00f2]|\u0000{\u00b4M\u00c4\u0080\u00fa}&\u001e\u001f\u00f9.l\u00df\u00a8u\u00d45\u0017\u00cf\u00df\"\u00der\u00c3\u00e8\u00e7*>\u000b\b\u0096\u00b5\u00e4\u00b3\u0014ZK\u00b6\u00cd\u00d0C\t\u00c4\u00b1<15$\u00e4Q\u00b6\n\u0098\u00bd*\u00c1\u00b1\u00eee\u0088+\u0018\u00c2\u00ad-\u00dca\u00cfS\u0082F\u00c9\u0084\u001c\u00d1\u0006\u00ee\u000b\u00ff{\u00f8\u00e9\u00a0$o\u0098 \u00954<v\u00d6H\u00f4\u001d\u00f8\u00b6f\u0091G\u001f7\u00eb\u00a9\u009ao\u00cb\u00e6\u0093\u0002\u00bb\u00dcoX\u00b4\u0015\u009f\u0011\u00e1\u00bb3\u00b3`\u0019\u0090\u0013F\u00cf\u00f5e\u00f1\u007fh;E}\u00f0\u0087\u0002\u00a5\u00ad\u00a4EE\u00eb;\u00dd\u0011^:\u00a7\u00a8j\u00abD>\u0087\u0092^W`\u000b\u00c3\u00b9}\u00ec\u00ee\u00f7r\u0083/\u0095\u0094\u00a0hS\u00e5\u00a8sW\u00b2?J\u0015{\u0003}!\u00ea\u0004\u0091J\u008e\u00e7\u00e3o\u00ed\u00c9\u00cd$p\u0088\u00bb\u00af\u00bc\u00ebb\u0002=\u00dfS\u00cef\u00db\u00ceo\u00a4\u00a9U~(\u007f\u0094+\u00b9\u008a\u0093\u00c5\u0086\u00c5R".length();
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
                    var4_14 = "$B\u00f3\u0095\u00c2\u00c4Y\u0015\u0084\u00f5\u00e6\u000bO\u00d0\u00d7\u001a";
                    var5_15 = "$B\u00f3\u0095\u00c2\u00c4Y\u0015\u0084\u00f5\u00e6\u000bO\u00d0\u00d7\u001a".length();
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
        GlyphCache.e = var6_12;
        GlyphCache.f = new Integer[73];
        GlyphCache.S = (char)167;
        GlyphCache.INSTANCE = new GlyphCache();
        GlyphCache.char2IntArrayMap = new Char2IntArrayMap();
        GlyphCache.char2IntArrayMap.put('0', 0);
        GlyphCache.char2IntArrayMap.put('1', 170);
        GlyphCache.char2IntArrayMap.put('2', 43520);
        GlyphCache.char2IntArrayMap.put('3', 43690);
        GlyphCache.char2IntArrayMap.put('4', 0xAA0000);
        GlyphCache.char2IntArrayMap.put('5', 0xAA00AA);
        GlyphCache.char2IntArrayMap.put('6', 0xFFAA00);
        GlyphCache.char2IntArrayMap.put('7', 0xAAAAAA);
        GlyphCache.char2IntArrayMap.put('8', 0x555555);
        GlyphCache.char2IntArrayMap.put('9', 0x5555FF);
        GlyphCache.char2IntArrayMap.put('A', 0x55FF55);
        GlyphCache.char2IntArrayMap.put('a', 0x55FF55);
        GlyphCache.char2IntArrayMap.put('B', 0x55FFFF);
        GlyphCache.char2IntArrayMap.put('b', 0x55FFFF);
        GlyphCache.char2IntArrayMap.put('C', 0xFF5555);
        GlyphCache.char2IntArrayMap.put('c', 0xFF5555);
        GlyphCache.char2IntArrayMap.put('D', 0xFF55FF);
        GlyphCache.char2IntArrayMap.put('d', 0xFF55FF);
        GlyphCache.char2IntArrayMap.put('E', 0xFFFF55);
        GlyphCache.char2IntArrayMap.put('e', 0xFFFF55);
        GlyphCache.char2IntArrayMap.put('F', 0xFFFFFF);
        GlyphCache.char2IntArrayMap.put('f', 0xFFFFFF);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6AF5;
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
                throw new RuntimeException("su/catlean/y3", exception);
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
            GlyphCache.c[n2] = GlyphCache.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GlyphCache.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/y3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x47D7;
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
                throw new RuntimeException("su/catlean/y3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            GlyphCache.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = GlyphCache.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/y3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GlyphCache.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(GlyphCache.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

