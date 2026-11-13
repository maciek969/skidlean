/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 */
package su.catlean.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.shaders.ShaderType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.io.Closeable;
import java.io.InputStream;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.ShaderApplyEvent;
import su.catlean.gofra.Flow;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;

public final class Pipelines {
    @NotNull
    public static final Pipelines INSTANCE;
    @NotNull
    private static final List x;
    private static boolean U;
    @NotNull
    private static final RenderPipeline.Snippet J;
    @NotNull
    private static final RenderPipeline programPostDefault;
    @NotNull
    private static final RenderPipeline programPostCamouflage;
    @NotNull
    private static final RenderPipeline programPostDoubleglow;
    @NotNull
    private static final RenderPipeline programPostMirror;
    @NotNull
    private static final RenderPipeline programPostBloom;
    @NotNull
    private static final RenderPipeline programPostMultidefault;
    @NotNull
    private static final RenderPipeline programPostLines;
    @NotNull
    private static final RenderPipeline programPostKawaseDownscale;
    @NotNull
    private static final RenderPipeline programPostKawaseUpscale;
    @NotNull
    private static final RenderPipeline programRectTextured;
    @NotNull
    private static final RenderPipeline programRectBlurred;
    @NotNull
    private static final RenderPipeline programRectBlurred2;
    @NotNull
    private static final RenderPipeline programRectRect;
    @NotNull
    private static final RenderPipeline programDefaultColoredTexture;
    @NotNull
    private static final RenderPipeline programDefaultColoredTexture2;
    @NotNull
    private static final RenderPipeline programDefaultColoredTextureCull;
    @NotNull
    private static final RenderPipeline programDefaultColoredTextureCullDepth;
    @NotNull
    private static final RenderPipeline programDefaultColoredTextureAltBlend;
    @NotNull
    private static final RenderPipeline programDefaultColoredTextureAltBlendDepthtest;
    @NotNull
    private static final RenderPipeline programDefaultColoredTextureAltBlend2;
    @NotNull
    private static final RenderPipeline programDefaultPosColorAltBlendDepth;
    @NotNull
    private static final RenderPipeline programDefaultPosColor;
    @NotNull
    private static final RenderPipeline programDefaultPosColorDepth;
    @NotNull
    private static final RenderPipeline programDefaultPosColorNoBlend;
    @NotNull
    private static final RenderPipeline programDefaultPosColorLines;
    @NotNull
    private static final RenderPipeline programDefaultPosColorLinesAlt;
    private static String[] Q;
    private static final long b;
    private static final String[] d;
    private static final String[] f;
    private static final Map g;

    private Pipelines() {
    }

    public final boolean Y() {
        return U;
    }

    public final void P(boolean bl) {
        U = bl;
    }

    @NotNull
    public final RenderPipeline getProgramPostDefault() {
        return programPostDefault;
    }

    @NotNull
    public final RenderPipeline getProgramPostCamouflage() {
        return programPostCamouflage;
    }

    @NotNull
    public final RenderPipeline getProgramPostDoubleglow() {
        return programPostDoubleglow;
    }

    @NotNull
    public final RenderPipeline getProgramPostMirror() {
        return programPostMirror;
    }

    @NotNull
    public final RenderPipeline getProgramPostBloom() {
        return programPostBloom;
    }

    @NotNull
    public final RenderPipeline getProgramPostMultidefault() {
        return programPostMultidefault;
    }

    @NotNull
    public final RenderPipeline getProgramPostLines() {
        return programPostLines;
    }

    @NotNull
    public final RenderPipeline getProgramPostKawaseDownscale() {
        return programPostKawaseDownscale;
    }

    @NotNull
    public final RenderPipeline getProgramPostKawaseUpscale() {
        return programPostKawaseUpscale;
    }

    @NotNull
    public final RenderPipeline getProgramRectTextured() {
        return programRectTextured;
    }

    @NotNull
    public final RenderPipeline getProgramRectBlurred() {
        return programRectBlurred;
    }

    @NotNull
    public final RenderPipeline getProgramRectBlurred2() {
        return programRectBlurred2;
    }

    @NotNull
    public final RenderPipeline getProgramRectRect() {
        return programRectRect;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTexture() {
        return programDefaultColoredTexture;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTexture2() {
        return programDefaultColoredTexture2;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTextureCull() {
        return programDefaultColoredTextureCull;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTextureCullDepth() {
        return programDefaultColoredTextureCullDepth;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTextureAltBlend() {
        return programDefaultColoredTextureAltBlend;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTextureAltBlendDepthtest() {
        return programDefaultColoredTextureAltBlendDepthtest;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultColoredTextureAltBlend2() {
        return programDefaultColoredTextureAltBlend2;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultPosColorAltBlendDepth() {
        return programDefaultPosColorAltBlendDepth;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultPosColor() {
        return programDefaultPosColor;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultPosColorDepth() {
        return programDefaultPosColorDepth;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultPosColorNoBlend() {
        return programDefaultPosColorNoBlend;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultPosColorLines() {
        return programDefaultPosColorLines;
    }

    @NotNull
    public final RenderPipeline getProgramDefaultPosColorLinesAlt() {
        return programDefaultPosColorLinesAlt;
    }

    @Flow
    private final void onShaderApply(ShaderApplyEvent shaderApplyEvent) {
        Iterable iterable = x;
        for (Object t : iterable) {
            RenderPipeline renderPipeline = (RenderPipeline)t;
            RenderSystem.getDevice().precompilePipeline(renderPipeline, Pipelines::onShaderApply$lambda$0);
        }
        U = true;
    }

    private final RenderPipeline register(RenderPipeline renderPipeline) {
        x.add(renderPipeline);
        return renderPipeline;
    }

    private static final String onShaderApply$lambda$0(Identifier identifier, ShaderType shaderType) {
        Intrinsics.checkNotNullParameter(identifier, "id");
        Intrinsics.checkNotNullParameter((Object)shaderType, "<unused var>");
        Closeable closeable = Globals.getMc().getResourceManager().getResource(identifier).get().getInputStream();
        Throwable throwable = null;
        try {
            InputStream inputStream = (InputStream)closeable;
            String string = IOUtils.toString((InputStream)inputStream, (Charset)StandardCharsets.UTF_8);
            return string;
        }
        catch (Throwable throwable2) {
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                Pipelines.b = ZkmKeys.a(6007596052786547178L, -6155363886240531148L, MethodHandles.lookup().lookupClass()).a(251360559628631L);
                var9 = Pipelines.b ^ 65444301903581L;
                Pipelines.g = new HashMap<K, V>(13);
                Pipelines.zkmSetFlag(new String[4]);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[69];
                var5_4 = 0;
                var4_5 = "\u001f\u0085xNR\u00ed\bB\t\u00e2^\u00c1\u00b7\u0083\u00a2Z\u0016\u0001@\u00e0E>\u00f3\u00a0=\u00b8\u0010\u00fb\u00946\u00c7\u00c4\u0019m\r\b\u00a1\u00b6\u00cd^\u00b1\u00ed\u00c1\u00cdjb\u0094\u00db\b\u00ad\u00a7\u00aeM\u009c\u00ae\u00e8\u00e5:\u00a0+\u00ee\u00ea\u00f6\u00ad8<;zQ\u00b1\u001a\u0007\u00a8\u00cc\u00b1\u0000\u0088gT\u00d2\u00ea~\u009dt\u0081\u0094ei\u00ceT\u00fbz\u00ac\u008f\u00ad\u00bad\u0087|\u0091&v\u00b7\u00d2\u0017\u0084\u00dbw=W6}\u00e6EKq\u00fa\u00a0M#\u00168,GI\u00a9{\u0082M\u00e1\u0018\u009d\u0019^|gD\u00dd\u0081z`9\u00f4}\u009a\u001bS\u00ba\u00c1\u00c6pP\u0006u\u00a7\u0088@V*\u0004\u00ef!\u00da\u001b\u00e0g\u007f\u00c1\u00c8R\ty+[)\u00d7&G \u008e\u00058w_\u00b6;i\u0085\u00d7R\u0013\u00ff\u00ac1\u00ec*K\b\u00c5\u00f1\u00b4\u00db\u00db\u00fb\u00e5\u00f4Y\u001c\u0017\u00d5{@I\u0013\u0087\u00eautFXf\u00e8\u00bc\u00e6\u0093\u00ae\r\u0012D\u0010W\u001b\n\u00cf\u00de\u00cf\u00fe\u00ae\u0098\u00bb\u00e6/\u00bd\u00ad\u00ef@\bU\u00ab\u00f9\u00a1\u00a5\u00d8^\u0005\u00f8`\u0088\u001e\u00fc?\u0092z\u00a9\u00a2g\u00aa\u0000\u00d9\u0014\u001cuV\u008f\u00cb\u0092@\u0019d\u00b4|\u0001)\u0010\u0087\u00b6\u0097\u0001\u00e2\u0011\u00f5\u00f3P}\u00ab\u0091\u0099p6\u0003*\u00e8\u007fiAP\u00cd\u0010\u009alq%\u00f9\u0087L\u00d5\u00a3\u00ff\u0099\u008dWTL\u00f2U\u00b5q\u00dd.\u001a\u0092nnQ8\u00b5\u00e0\u0080\u00f6\u00e1\u008c@\u00f2\u00b2\u001f\u00db\u00eb\u00b2/\u000b\u0007\u0087\u00c2\b\u0010\u0018\u00ee\u00cb\u0006sl\u00e1\u00beW(\u0017\u008e\u009c\u00f7H\u00cb\u008a%\u001bQ.\u00b9\u00ab'j\u00e7\u00f8\u0095\u00f0\f\u00c1\u00de\u001bz\u00af\u0084o0C\u00c8\u0093\u00a9T\u00828\u00ad\u00b5\u00cb'\u00dfbP\u00cc\u00e4I\u00c6\u00e8\u0091*7r!cA\u00ab\u009f\u001a`>\u0096B\u00af:\u00169\\\u00c5\u0014\u0089\u0017\u0090\u0007\u0097\u00b0\u00b6S\u0014\u00e5f\u008e5\u00cd\u00de\u00ce\u00d3\u00bf\b\u0098<;\u00e3\u000bq\u0088\u0087#\u00eb\u008dB\u00c22\u008fA\u0094\u00bc\u009852B\u0011\u0096\u00b7\u0017\u00efH\u000f\u00cd\u00be\u00b0\u00d0\u0085% \u00f6\u00fc\u00bd\u0002_,\u00cd\u00b7R\u00e3\u00ec\u00f9\u00c5VA\u00df%r\u00a8Cb\u00a7\u001b\u00f6\u00a6s\f\u00ca'B\u00c5\u00a9X\u00a8\u00dbm\u009d\u00b3\u00c0%\u00e0\u00c9\u0086\u008a\u00b9\u0097\u00d3\u00c9u\u00c6>]\u00d3\u00bf\u00c3K\b\u0012\u009b\u009e\u0001\u00c1R\u000e\u0007\u00b3\u0099a\t\u00d3s\u0090\u00ab\u00d2\u0093S\u00c0^\u00a6@ \u0007*/\u00d1\u00d1\u0091\u00bf}_\u0081\u001ab\u00f0H\u00f9\u00f8\u0093\u00a5L\u00ac\u009c\u00c9F\u009cA\u00ba\u008f\u00b2\u00b3\u00a1,\u0081E\u00d5h\u00b5\u00a9\u00d7.\u00ba(gx\u0093\u00a4\u00e5\u0090\u00b9\u0098\u0080\u0082\u00c1&-\u00a1\u00e6\u00bb\u00f1(1x\u00b9\u00db\u008aI\u000e-\u00fc\u0012\u00a0\u00a2\u00b6G\u00e0-\u00aci\u00d0\u00c1\r\u00a5@R\u00c5\u0004\u00b3\u00fa+\u00e9\u0083\u00cc\u0082\u00f1\u00ac\u00843[\u00ab\u00c6\u00a1\u001a\f\u00ad\u00b5\u0082*\u00c2L\u00d5\u0019\u00b3\u00c8\u00c4\u00b6\u00fc\u00aaP\u0018^\u0093\u008dq\u00a5\u0099\u0092\t\u0091f\u009d\u0083+\u00b1%\u00c6\u00c9m+Z;YV\u0003\u0089s\u0084\u009f@\u00e5\u00d2u\u0082\u00ab\u0007q\u00ab\u00e6r\u001a\u0094\u008d)\u00b4\u00fe\u00e96\u0004C^\u0097HY\u00d2\u009e\u0091\u0082g\u00e3b\u00da\u00be\u0086\u00c6SO\u00cc\u0089\u00de\u001a8\u00ae@UT\u00b9?\u00fe)\u00d08 \u00bby\u00ab\u0081;KE\u00ef\u0090\"\u0080P\u00f1\u0000gx=\u00bd}\u001d!9\u00a1\u00aa\u00f8\u00a88\u0015\u00f8\u00f1\u00ee\u00f86wg\u008d\u000eZru{\u00d2n\u00cc\u008d\u00eb\u00e2\u009f\u0011\\_\u001c\u00ae\u00d9\u0084\u00af\u00dd\u00a5\u00c7\u001f\u00ed\u0098\u001cPQgS\u0090u#\u00a7\u00b6\u00a6<s\u0017\u00af\u0093\u00cc\u0005+\u001c>U\u00b1\u00f1\u00d0\u00ca\u00e4\u0085\u0019\u00da(6\u007f\u00a6%\u0003U*I\u00f05\u0002Qa\u00b0\u00f0?\u00fd\u00bd,\u0084\u00ee\u00fej\u00f8\u00007\u00be\u009fbl\u001f,'\u00ad\u008f\u00c52\u00ad\"\u00ea \u00e6t\u00823a\u00b8V\u0085pP\u0084\u0016\u009a\u000fR\u00c7\u001e\u0002\u00bfF\rQs[\u0006\u00d9\u00d0B\u00e4G\u00b5\u00d6(\u0006<\u00d6\u00e5\u00d0jt1Z[\u00a8R\u00abS\u00f5\u0084W\u0005T\u00b3\u00da\u00cd\u00c8\u0099&\u008b\u00c1\u00e1\u00d5>\u00deC\u00b3\u001f\u009en[\u007f9\u00c8@\u0091.\u00a5\u001a\u0018\u0001\u0096\u00e2\u00a8\u00b89\u00d1\u0097\u00c5!XMkk]!#\u0015\u000f\u00d0n\u00b6\u0087\u00b0\u00fd\u00f9\bF\u00b5\u00c0\u009b^sBg-\u0085E\u00f1\u008d\u00a0\u00ce\u001eC\u00fd\u0018\u00c7pS\u009dH\u009e\"\f\u00bd\u00f3\u00e7\u00be\u00d1\u00108\u00a2\u001ao\u00ca~xS\"\u001e\u00c8\u0086\u00d0S$`(v\u00a8z\u00e7>\u00f2\u001f\u00c1\u00e9\u00ado\u00af\u00cd\u000fT\u00e0\u0001\u00f8i\u00c6\u00da\u008c\u00b4\u00b0\n\u00cf\u00d9[x\u00e6r\u00da \u0094d\u0086\u00a4\u00a7\u001dt@\u00da\u00a4\u00d6ns48\u00ed\u0019\u00dd8\u0085\u0085\u000f_vB2\u00fa\u008d%P']\u00ff&q?\u00fc\u00ea\u0085f,\u00bf\u00b2n\u001b\u008a|m\"\u00c0\u001ep36\u00d4\u009d\u00cd\u00ee\u00d8\u001a=\u00c6\u00ebd\u00b1\u00a3T \u000e\u00f4o\u00fd@cs\u00ef\u00fc\u00b2\u00a4\u00fe\u0097\u0002\u00e6Tv\\\u00f7\u009e\u00ce\u007f\u0010&B\u00cc`\u000e`\u00a1\u0010\u0093\u00de\u0094\u00df@*\u000ey\u00a4^G-\u008f\u00a4P\u0014m\u008f\"\u0092 \u0018\u00d0\u00e8\u00d1\u00b4\u00b8\u00d8a\u00cc\u00e1@*\u0085\u00e3\u00d3|\u00f1 \u00eeh\u00d9\u009a\u000b\u00c3Qn\u0017/D\u00ed\u0095\u0098\u00c7i2\r\u00a8N\u0017X\u00cf\u00e3\u00dak\u00ed?Rk\u0098\u00f68\u00e3\u00ffU&\u00a8\u00ea\u00f4\u00f4\u00dc\u00b3\u00b6#\u00dd\u0014\u00c2\u0006\u001d\u00a8o\u00fa\u00d1\u001c\u00b7\u0084\u00a8\u00ccE\u00cbd\u00ae\u000b\u00df\u00cb\u00bd\u0016!\u00bd\u009dX]t\u009e\u00aaa5\u00f9>\u00fc\u00f0K\u00d7S\u001axNKP\u0082\u00a5\u00cb\u00df\u0085\u0000\u001fL\u00ecK|\u009cu\u00eb\u00a2'mW\u0090\u0099\u00a5#G|\rF\u0088C<:\u008d\u0080\u0090\"rf\u00ack\u0003RS\u00e8\u00a7\u001f=\u008c\u000f&\u008e\u00fe\u0092\u0004\u0011\u0088:\u00d9Z#\u007f\u00ccJG\u0010\u0094\u00d7\u00002\u0012M8\u00e2\b\u0015?-\u0081fle\u00c9(p\u00a3xB\u0019\u00b1D\u00b1\u0018T\u001b\u0097\r\u0000\u00a5\u00a0\u0015\u009b\u009f\u009d\u0095\u0086~\u0086\u00d3\u00ae7,\u00c2nM\u0086\u009f\u00f6`\u00df5\u00e2\u0016\u00ac8\u00dbE*\u00aeX\u0097\u0086\u0097\u00ff'\u00e2&\u00c2\u00bc\u00d7\u007f\u0099\u0005_i\u00a6\u00cb\u0099h\u000e\u00a0\u00bdw\u00958\u00a4\u00b2\u0019d\u00aa\u00ef\u001d\u0087\u0003\u00f6t\u0091\u00c48z\u00e0\u0087\nQh\u00cf\u00cb\u00f7\u0001\u0005x0\u00ea\u00e7z\u00f1\u001fF\u00d4\u00be\u00de\u00e1\u00b6s\u0017 \u00a9\u0013A\u00a1A\u00c8\u0084\u0086\u00d2\u0084\u00c1\u00c9\u00e8I\u00bf\u0011\u00c2*\u00f7V\tN\u00ba-J\nJ{\u00d8\u00d1\u0090C\u00a2\u00ed@\u001d\u008e\u00ad\u00ae\u0000\u00a3\u00c3\u00f5\u000f\u001f\u00d8\u0084G\u00eb\u00e1e$\u00caNj\u00aa_\u00f5z8\u00act\u00d0\"\u00cc\u00eb/\u00ebt/A\u00a4\u0017\u00adg\u0090\u0006c\u00a6d\u00fcB\u00c9\u00e6\u0007\u0085w 8\u0086\u00ceb\u0001B\u00b9\u00d4\u00b47\u00eb8\u00e97`\u0090\u008d\u00d4\u0007\"\u00dd\u00e7,8\u00bd\u00b3~]\u00cd;\u009e3F\u00a8\u00c3\u00b3\u00e7\u00bcfP\u001e \u0094\u0017\u00c7\u00f9\u00b2\u000fZE\u00de\u00d5'>\u00fb\u0012\u0016:\u0080B\u00cdMs\u00a5\u00d2\u0003\u00b7b0\u0013\u00d2k\u00dd\u00aeU\u00f5\u00db\u0099vT\u0082\u00fe\u00da\u0006\u00ea\u00da\u00a2\u00d3\u00a1\u00b0\u00e1\u00afF\u00dd\u00ce\u00b1\u009d\u00c6*\u00c0F\u00b6\u00f7T'\u00d4mE\u0084\u009d\u00c6\u0088\u000e\u0081Ud\t@\u00cb\ftL!\u00b3.\r{\u00143\u009e\u00d8\u00d0q\u00e6?\u0091\u00e1\u00ab\u0014\u00e3j`\u00ca\u00bfP.\u00e64b\u00e9\u0011\u00fa\u001e\u00del,=BDi\u00c5\u00a4\u00eb\u00ff\u00b0\u00a0\\\u00fcr\u0002C\u00ee\u001bBgl\u0000\u00a7\u00e9\u00a9RL\u0010\u00ba\u0004F\u00f83\u0015\u0019`\u00e3s\u0098=g\u00ef\u001fXP\u00e4\u0098\u00c57E~\u00d1\u00bb\u0099S5\u00ba(7XN\u00ddS\u00a0Zt\u00e3\u00e4F\u00ce\u0088\u00d1\u00f4\u00c5\u0010\u00d3\u00a5@Y\u00ceo\u0097%\u00d6\u00f3\u00d4\u00d8-\u00bdJm\u00d6H\u00c0\u0005\u00a9|\u0089M\u0017\u0085\u001bTf!4\u00a0\u00b2a\u00e7 Ef\u00c2\u00b7O;\u008b<K\u009dZ\u00fb\u0089\u00e68\u00b0\u00d1\u001dH\u00a7i\u001a\u0082\u00c9\u00d7\u00ae=\u00e0E\u00ca\u00afR\u00eb\u00b0\u00e2\u0092\u00a0\u00f4\u00ec\u00de\u00d89\u00ae\u00ad=\u008b\u00bf\u0095\u00e3_\u00a0\u00d9]\u00d6\u0000\u00cf\u00d1\u00e7.)\u00d4\u00ac9T\u00e8\u00fa\u00a2\u007f\u00a4h+\u0010\u00be%\u00c3?\u00d1\u00e3\u000f(\u00c3c\u0087]`\u0090\u00ce0H\u00e4\u008eT3?b\u00ccN\u00f6\u00d00b\u00a4\u00a1p\u00d1K\u001e&\u00a5c\u00d3#ag\u001d$\n\u0083\u00ef%\u00a4a\u0007\u0015\u008a\u00d5\u0001AJkO\u0005\u00d5\u0081\u00d94\u00ae\u00ca>\u0004K\u001dsB\u00a9\u00b5\u00e5\u00d8c\u001a&\u00f6\u00d8*\u00c0$\u00e3\u0082\u009f'|H03\u00fab\u00a6Rf\u00ea\u00d8S\\\u00b4\u00e1{\u00f8@\u00be}\u0014\u00e5\u00a7.h\u00da:?\u00fb\u00e9\u00f1\u00d4\u00a9\u00b6\u00a4\u0095\u00ca\u000b\u00b6\u0095 7\u009c\u00ed\u0004q\u0016\u00bc\u0095A~\u0014\u009e\u0091\u0007#\u00b2\u00f8\u00b0\u00bf\u00d9\u009e1M\u00d6\u008c\u00d7\u00d5t\u0002\u00e1\u00c1d9\u0010x\u00c5\u0011G\u00138\u0083W\u0089\u0095\u00d4$\u00e6\u0091B\u00cf(\r5\u0081\u00dc`\u00b8\u00e8\u00b7\u008b\u00d7\u00b7&a\u00e1\u00d3\u00bd\f\u00ed\u00d9X<\u0003\u0098\u00bf\u00b3\u001f\na\u0094D\u00fd_\u00a9\u00dc\u001e\u00c6\u0095\u00aa\u00b9{ w\u009d\u00ba\u00e0\u00ff\u00c2\u0092\u000fR\u00e3\u00c8\u00eb\u0091k\u0005b]\u00f75\"$\u00a1\u009a:\u00b2\u0091ty\u00c7\u00cfA\u00f784\u00bc\u00e2\u00b0T\u001c&z\u007f\u0002\u00b3\u009b\u00bf\u00a2\u0005,L6\u00e6%\u0086\u0095\u00ac\u00e6f\u000b\u0085\u00f9\rB2\u00a8\u00ba\u00bb\u00020\u00e1\u007f~\u00bfP\u00cc~\u0018Iv\u0097\u00c70\u00c8\u00d6\u00ac\u00d4W\u0090\u00a0H=\u00c5k\u00a3\u00e9F\u00a1\u000e\u00c4\u008c\u0081\\\u0084\u009e\u00ae8?\u0085\u00b8\u00948\u00f4\u00b0;\u00f0W\u009e\"\fg\u001a\u009e\u0012^\u00f3\u00cd\u009e\u0092`\u008a:\u0091\u0000\u00d5\u0017\u00a1\u0095\u00b9\u00fb\u0092\u00e0KbmA\u00f0\u0081:C(\u00c8\u00fd\u0080\u009d\u0085\u00b2jz.\u00e3\u0001\u00cb(\u0097\u00ef\u00a3\t\u00a1d\u0093G\u00b8\u0095\u00f64g\u00fd\u0018\u0090\u0080\u00c2\u00f0\u00a0\u0002F]O\u009d\u00b5g\u0006\u00e3|\u008b\u008c\u00ce:\u00f7%.\u0007\u00db\u0012\u0010 5\u0088\u0010\u00f5$w\u0001K\b3\u0089\u00d5\u008b@k8\u0015\u0092^,w\u0002\u0094QYV9I\u00da\u001f\r\u0083v\u00c5\u0084A\u00dd:\u00922\u00d3\u0082\u0093\u00db\u001f\u0098\u00ee\u00eb2\u00192\u00c2\u00ae\u0097&E\u00ec\u00d9\u00a6\u0090Z\u00edh\u00fe\u009a\u00b2\u0010\u001c\u00d0\u008bQy0\u00bdn\u00e2\u00ed)e.\u00d3)w\u0005\u00bb\"\u00d0\u00b9\u00a0\u00d2$YP\u00dfn7\u0083\u00fd\u00f1j\u009aY\u00f0P\\\u0082\u001fS\u0004Vz\u009e\u00dbz\u00a4g\u000e\u008a\u0097\u00bf\u00ab(\u00d5]\u00fe\u0098@\u0019W\u00bc\u0012%S\u0085ev;:\u00f9\u00f0\u00e5\u00cam\u000e\u0010\u008e\u00f7\u0091\u000b\u00d5\u00e3L7\u00d6\u00d9\u0081\u0011\u0019\u00f2\u0096\u00d0\r(\u00cc\u00ce?\u0093R\u00f5\u00d4\u0011z7\u0017{g\u00d9\u0096\u0013\u0081\u00a4\u008c\u0082\u009f+T\u00ce\u001a\u00f3\u0006k\u00aa\u0086!\u0096\u00b5; \u00f5:\u0096\u00fa\u007fX\u00e6\u00ba)\u0096a{\u00cf\u00c0\u00b2\u00b5kfjx</\u0094z\u0087WT\u00ec#Z\u007f\u0015\u000e\u00df\u0019NW\u0091\u00e7\u0087\u00ee\u0082\u00fc\u009e\u00c0E\u00b9\u0087\u00daG\u007f\u00d0~\u00adX\u009bkn\u009f\u00ff0o\u0014\u008f\u0092\u00cd\u00db\u00f8$jJ\u00ad\u00fb\u00cb\u00e2\u00fc\u0012w]9,\u0093\u00dcy\u000e\u00fb\u0094\u000f<\u001c-\u00bc\u0083\u00dc\u0018\u00f3y\u00ff\u001a\t\u0096\b|N\u00eeH\u00ed\u00ae\u00a5L\u00efM\u00c2\u009c\u000ex^d\u00860D<\u008d!\u00a9\u0097\u00f9a\u00135%\u00e8\u00a7V\u00bc\u00af`\u00d0\u00bbU\u000f\u00ady2\u00cc\u00d3\u00f2i\u00ec0L\u00f592\u0091=>\u00e7\u00e3\u0096\u00b0\u0012\u00c8\u0088\u00a2>\u00f4t88\u001eP\u0013\u00d9\"\u00bcz\u0019\u00a8\u00c9\u00f5\u00dd\n\u00f2\u00ee\u0004\u00f3\u00d1\u0006\u00a9}\u00ae2\u00a2\u00b7\u0002\u00d0N\u00e6\u00aa8>O0r\u00bam\u00eb\u00b3j\u009f\u0086'L@]\u007frY.o\u0082\u0090\u0002SH\u00d1%\u00ec\u00f8\u0089\u00ff\u0003\u00dd\u009c\u009a\u001fQQjFg\u00b4w\u00fd\u00fe\u00cc\u00e8G\u00e07\u0010\rO\u00f4\u008c\u00bee.N\u009f\u00a3\u00aa\u0090\u00bdq\u0084\\\u0019\u0092\u00ec\u00c6\u00ad\\2\u00c3\u00f7\\QYM\u00b6pE4\u00bc\u0018^\u001f\u00b86'D,@\u00a3\u00cchPi1\u0002v\u00e7Y\u00ea4\u000e\u00bd\u00cb\u0096\u0006q\u00f9z\u00af\u00dc\u009c?\u00d8\u00c4z_\u00cc\u0002\u00fa\u00de\u00a3\u00ce4\u0019V=\u00adS\u00fc\u00c6\u0083\u00c7HDa\u009dD\u00c3\u00f6\u009f\u00ba*0\u00e8\u00e6\u00de\u00dd\u00b2\u00d8F\u0003\u00fco\u00c4,\u00a5\u00ea\u009a\u00c0\u009b'm(\u00c2\u00e2\u00d7\u0090S\u00c2C3h8P\u00b1\u00c2c<\u00c5|:Q\u0092\u008e7\u0011/\u00b0\u0097\u00cf'\u00b1\u00df\u00a5\u00bb\u00f1!\u0083=\u00a8\u00deK\u00e0\u008bYk\u00a1q6\u0099@\u008b\u00e6?\u0006\u00f5.\u00bbO{\u00bb\u00cc\u00a7q,OR\u001f?Pv\u00bc\u00cbM\u00864\u00afSi*\u0080\u00bb\u0080xE\u0086\u00e3\u0081d\u00a4\u00cc\u00fd\u0084\u00deG\u00a3(\u0084\u00d0\u00ban\u008e\u00f9\u00fb\u00ee\u00ba\u0015\u00d4\u0087\u00a8\u00d6\u008b\u008a\b|\u0090\u00951\u001cL\u0094?!i\u0019@\u0087Z\u00884\u00d7\u00b9E^\u0003\u00cf\u00efy\u00cb\u0093\u00d1\u00ee&hJd}\u00b9t\u00e2@\u00b4j^g.r\u000f\u000b\u00a3kcL\u00bdH3fY\u0098\u00e9?\u001f\u00cdK*\u00faD\u00ce\u00ddZ\u00f2\u00bd\u00b5P\u00d4%\u00ff\u00e9\u00efH\u0015\"\u00b4P\u00b7\u001d\u0087L;J\u00aa\u00f9!\u00ce:\u0003y[\u00c5Jm+\u00f6\u00d3\u0089(F\u00e3\u00b7\u00ed\u00b8\u0007\u00bd\u001d<\u00bf\u00fb\\\u0093\u00d8y\u000b\u00e9\u00c1\b\u00c1X~\u00fc<\u00f8mLw\u001d,j{5\u00f3\u00e2\u00ec;h\u00fd\u00b0@e\u0096\u008aIT\u00d5\u0019\u00f5=\u00d5w\u001a\u000e\u0087\u0007F\u0001UM\u00b0\u00ce\u00be\u00c1\u000bA\u0014\u00bfGs\u00f6&\u00f6\u00a9\u009e\u0005\u0014\u0018\u00bc\u00db\u00eb3\u00a2\u009d\u0080_n\u00daL'-\u00d0\u001c\u00c6\u00df\u00a7\u00a9W$\u00cb\u008c(\u00af\r=(\u0007\u00e9\u0098\u0004\u0083\u0002\u00e9-\u0018ae0\u0006m\u00d5\u001d\tz\u00b6\u00ea\u00e2\u00196\u00f2\u001e\u00df\u00c2\"X\u00c1\u0088\u00bbH\u00ee\u009c\u00b3\u00f6G\u00af\u0082 \u00a2\u00ed\u00e3c\u0011Y\u00fc,3\u00e2U\u00d5{\u000b})\u001d)\u0097\u00c2\u0095\u00e2\u001c\u0098U\u0081\u00d7&\u00d9\u00fd\u001d\u00c18\u00ddC\u00c8\u0084k\b$,\u0090?\u00fa\u00bc\u00d3!_CZ\u00a0\u00e7\u00e6\u000f\u00dbVB\u00e0\u00ed\u00a6\u00a77Sx\u00c2Y\u0092\u00acP\u00e8.\u001b\u0094\u0098\u0081pk\u0084\u009c+\u00fe/b\u00a1N~\u00ad=\u00a6\u0018\u00aen1.\u00b47\u00c3\u00d0nB*\u00b5\u00aae\u00a4d\u00be\u00a96j\u008f\f\u00fcmH\u00e3\\r\u0004\u00f3\u009e\u0098J]\u00e1(\u00d7\u00a0\u00a0Y\u00b3w\u00bd\u00eb\u00ec\u001d\u00be'\u00a8\u00e6\u0091\\\u00e4\u00a2\u00cb\u0018\u007f\u0018\u00e5R\u0093\u00f2\u009a\u0004\\T!\u00ae7\u00c3 [\\i\u0018\u0010v=\u0007_F\u00f5\u000b[)kcOF\u008a\u0002~Z\u00d9\u008b\u0085\u0016(\u001e-\u00c0\u0013E\u0091\u00158\u00ed<p%\u00ba\u00ae\u00df\u00ae\u0012\u00ee\u0006/\u00c0U+?\u00c4\u00c6\u00af'\u00fb\u00cdM\u007f$s,Xc\fj\u00b6\u0018/\u00bc,1'p\u00f7\u0096\u00db`h\u0007\u001e\u0014\u00a9\u008f\u00ea\u00f34\u00b0\u0010I\u00b1\u000e";
                var6_6 = 3546;
                var3_7 = 64;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = Pipelines.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00e6\u00e1\u00c1\u00e2%\u001d\u00cb\u00f0h\u001d\u00bb\u0092}\u00f9\u00adu)u\u00f4\u00fa\u00ea\u0092\u00cf\u001dx,|WW\u00ed.t\u00b7\u00b2\u00d9M\u0097o/\u00eesu\u00ad(\u00a1\bP\u008d\u0090\u00f5\u0084=\u00ac\u0091\u008a\u001c0_\u00e7\u00a3\u0081b_\u0098\u00cc\u00eekz;\u00fe\u00e3!\tz\u00f8rL\u0016\u0097\\\u00da\u0018q\u00d8\u00c3\n\u008bn}7\u009el\u00ae\u0011\u00d9\u00a6\u00e0\u0093|\u008d)\u0010\u008bF\u009b";
                    var6_6 = 105;
                    var3_7 = 56;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = Pipelines.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl52:
                // 1 sources

                ** continue;
            }
        }
        Pipelines.d = var7_3;
        Pipelines.f = new String[69];
        Pipelines.INSTANCE = new Pipelines();
        Pipelines.x = new ArrayList<E>();
        v7 = RenderPipeline.builder(new RenderPipeline.Snippet[0]).withUniform("VertexUBO", UniformType.UNIFORM_BUFFER).buildSnippet();
        Intrinsics.checkNotNullExpressionValue(v7, "buildSnippet(...)");
        Pipelines.J = v7;
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v8 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/default")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/default/default.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v8, "build(...)");
        Pipelines.programPostDefault = Pipelines.INSTANCE.register(v8);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v9 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/camouflage")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/camouflage/camouflage.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v9, "build(...)");
        Pipelines.programPostCamouflage = Pipelines.INSTANCE.register(v9);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v10 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/doubleglow")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/doubleglow/doubleglow.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v10, "build(...)");
        Pipelines.programPostDoubleglow = Pipelines.INSTANCE.register(v10);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v11 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/mirror")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/mirror/mirror.fsh")).withSampler("Sampler0").withSampler("Sampler1").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v11, "build(...)");
        Pipelines.programPostMirror = Pipelines.INSTANCE.register(v11);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v12 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/bloom")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/bloom/bloom.fsh")).withSampler("Sampler0").withSampler("Sampler1").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v12, "build(...)");
        Pipelines.programPostBloom = Pipelines.INSTANCE.register(v12);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v13 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/multidefault")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/multidefault/multidefault.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/multidefault/multidefault.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v13, "build(...)");
        Pipelines.programPostMultidefault = Pipelines.INSTANCE.register(v13);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v14 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/lines")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/lines/lines.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/lines/lines.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v14, "build(...)");
        Pipelines.programPostLines = Pipelines.INSTANCE.register(v14);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v15 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/kawase/downscale")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/kawase/downscale.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v15, "build(...)");
        Pipelines.programPostKawaseDownscale = Pipelines.INSTANCE.register(v15);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v16 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/post/kawase/upscale")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/post/post.vsh")).withFragmentShader(Ids.id("catlean", "shaders/post/kawase/upscale.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v16, "build(...)");
        Pipelines.programPostKawaseUpscale = Pipelines.INSTANCE.register(v16);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v17 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/rect/textured")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/rect/textured_rect.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/rect/textured_rect.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v17, "build(...)");
        Pipelines.programRectTextured = Pipelines.INSTANCE.register(v17);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v18 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/rect/blurred")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/rect/blurred_rect.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/rect/blurred_rect.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v18, "build(...)");
        Pipelines.programRectBlurred = Pipelines.INSTANCE.register(v18);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v19 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/rect/blurred")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/rect/blurred_rect.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/rect/blurred_rect.fsh")).withSampler("Sampler0").withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(true).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v19, "build(...)");
        Pipelines.programRectBlurred2 = Pipelines.INSTANCE.register(v19);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v20 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/rect/rect")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/rect/rect.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/rect/rect.fsh")).withUniform("UBO", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v20, "build(...)");
        Pipelines.programRectRect = Pipelines.INSTANCE.register(v20);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v21 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v21, "build(...)");
        Pipelines.programDefaultColoredTexture = Pipelines.INSTANCE.register(v21);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v22 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v22, "build(...)");
        Pipelines.programDefaultColoredTexture2 = Pipelines.INSTANCE.register(v22);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v23 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture_cull")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(true).build();
        Intrinsics.checkNotNullExpressionValue(v23, "build(...)");
        Pipelines.programDefaultColoredTextureCull = Pipelines.INSTANCE.register(v23);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v24 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture_cull_depth")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST).withDepthWrite(true).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v24, "build(...)");
        Pipelines.programDefaultColoredTextureCullDepth = Pipelines.INSTANCE.register(v24);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v25 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture_alt_blend")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.LIGHTNING).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v25, "build(...)");
        Pipelines.programDefaultColoredTextureAltBlend = Pipelines.INSTANCE.register(v25);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v26 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture_alt_blend_depthtest")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.LIGHTNING).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v26, "build(...)");
        Pipelines.programDefaultColoredTextureAltBlendDepthtest = Pipelines.INSTANCE.register(v26);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v27 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/colored_texture_alt_blend_2")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/colored_texture.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/colored_texture.fsh")).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(new BlendFunction(SourceFactor.ZERO, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO)).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v27, "build(...)");
        Pipelines.programDefaultColoredTextureAltBlend2 = Pipelines.INSTANCE.register(v27);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v28 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/pos_color_alt_blend_depth")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/pos_color.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/pos_color.fsh")).withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST).withDepthWrite(true).withBlend(BlendFunction.LIGHTNING).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v28, "build(...)");
        Pipelines.programDefaultPosColorAltBlendDepth = Pipelines.INSTANCE.register(v28);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v29 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/pos_color")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/pos_color.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/pos_color.fsh")).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v29, "build(...)");
        Pipelines.programDefaultPosColor = Pipelines.INSTANCE.register(v29);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v30 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/pos_color_depth")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/pos_color.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/pos_color.fsh")).withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v30, "build(...)");
        Pipelines.programDefaultPosColorDepth = Pipelines.INSTANCE.register(v30);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v31 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/pos_color_no_blend")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES).withVertexShader(Ids.id("catlean", "shaders/core/default/pos_color.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/pos_color.fsh")).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withoutBlend().withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v31, "build(...)");
        Pipelines.programDefaultPosColorNoBlend = Pipelines.INSTANCE.register(v31);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v32 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/pos_color_lines")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.DEBUG_LINES).withVertexShader(Ids.id("catlean", "shaders/core/default/pos_color.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/pos_color.fsh")).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v32, "build(...)");
        Pipelines.programDefaultPosColorLines = Pipelines.INSTANCE.register(v32);
        var13_10 = new RenderPipeline.Snippet[]{Pipelines.J};
        v33 = RenderPipeline.builder(var13_10).withLocation(Ids.id("catlean", "program/default/pos_color_lines_alt")).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.DEBUG_LINES).withVertexShader(Ids.id("catlean", "shaders/core/default/pos_color.vsh")).withFragmentShader(Ids.id("catlean", "shaders/core/default/pos_color.fsh")).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withBlend(BlendFunction.LIGHTNING).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue(v33, "build(...)");
        Pipelines.programDefaultPosColorLinesAlt = Pipelines.INSTANCE.register(v33);
    }

    public static void zkmSetFlag(String[] stringArray) {
        Q = stringArray;
    }

    public static String[] zkmGetFlag() {
        return Q;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x16DE;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/b6", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            Pipelines.f[n2] = Pipelines.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Pipelines.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/b6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Pipelines.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

