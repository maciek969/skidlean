/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 */
package su.catlean.render;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.attributes.ViewBox;
import com.github.weisj.jsvg.parser.SVGLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import su.catlean.render.RenderUtil;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class RenderTarget {
    @NotNull
    private String name;
    private int width;
    private int height;
    private int L;
    @Nullable
    private Identifier K;
    private static boolean h;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public RenderTarget(@NotNull String string, int n, int n2) {
        Intrinsics.checkNotNullParameter(string, "name");
        this.name = string;
        this.width = n;
        this.height = n2;
        this.L = -1;
    }

    public RenderTarget(String string, int n, int n2, int n3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = 0;
        }
        this(string, n, n2);
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int n) {
        this.width = n;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int n) {
        this.height = n;
    }

    /*
     * Loose catch block
     */
    private final void resize(float f, float f2) {
        long l;
        block10: {
            block12: {
                l = 0L;
                l = a ^ l;
                if (this.K == null) break block10;
                break block12;
                catch (Throwable throwable) {
                    throw throwable;
                }
            }
            this.release();
        }
        this.K = Ids.id("temp/vector" + this.name);
        try {
            Graphics2D graphics2D;
            BufferedImage bufferedImage;
            block11: {
                SVGDocument sVGDocument;
                block13: {
                    SVGLoader sVGLoader = new SVGLoader();
                    InputStream inputStream = this.getClass().getResourceAsStream("/assets/catlean/vector/" + this.name + ".svg");
                    Intrinsics.checkNotNull(inputStream);
                    SVGDocument sVGDocument2 = sVGLoader.load(inputStream);
                    bufferedImage = new BufferedImage((int)Math.ceil(f), (int)Math.ceil(f2), 2);
                    graphics2D = bufferedImage.createGraphics();
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                    sVGDocument = sVGDocument2;
                    if (sVGDocument == null) break block11;
                    break block13;
                    catch (Throwable throwable) {
                        throw throwable;
                    }
                }
                sVGDocument.render(null, graphics2D, new ViewBox(f, f2));
            }
            graphics2D.dispose();
            Identifier identifier = this.K;
            Intrinsics.checkNotNull(identifier);
            RenderUtil.INSTANCE.registerTexture(identifier, bufferedImage);
        }
        catch (Throwable throwable) {
            this.K = Identifier.ofVanilla((String)((Object)RenderTarget.zkmBootstrap("f", (int)9305, (long)(0xB2241A3EFFED834L ^ l))));
        }
    }

    /*
     * Exception decompiling
     */
    public final void drawRoundedText(short var1, @NotNull Matrix3x2f var2, double var3, short var5, double var6, float var8, float var9, int var10, @NotNull Color var11, @NotNull Color var12, float var13) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl146 : POP - null : trying to set 1 previously set to 2
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.populateStackInfo(Op02WithProcessedDataAndRefs.java:207)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.populateStackInfo(Op02WithProcessedDataAndRefs.java:1559)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:434)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static void drawRoundedText$default(RenderTarget renderTarget, Matrix3x2f matrix3x2f, double d, double d2, float f, float f2, Color color, Color color2, float f3, int n, long l, Object object) {
        long l2 = (l = a ^ l) ^ 0x5FEF6630A58BL;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 48);
        int n4 = (int)(l2 << 32 >>> 32);
        if ((n & 8) != 0) {
            f = renderTarget.width;
        }
        if ((n & 0x10) != 0) {
            f2 = renderTarget.height;
        }
        if ((n & 0x20) != 0) {
            Color color3 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color3, "WHITE");
            color = color3;
        }
        if ((n & 0x40) != 0) {
            color2 = color;
        }
        if ((n & 0x80) != 0) {
            f3 = 1.0f;
        }
        renderTarget.drawRoundedText((short)n2, matrix3x2f, d, (short)n3, d2, f, f2, n4, color, color2, f3);
    }

    /*
     * Unable to fully structure code
     */
    private final void release() {
        block7: {
            block6: {
                var4_1 = 0L;
                var3_2 = ZkmReferences.zkmBootstrap("\u00c5", (long)5054539938317201486L, (long)(var4_1 = RenderTarget.a ^ var4_1));
                if (var3_2 != false) ** GOTO lbl-1000
                v0 = this.K;
                if (v0 != null) {
                    v1 = v0.getNamespace();
                } else lbl-1000:
                // 2 sources

                {
                    v1 = null;
                }
                if (!Intrinsics.areEqual(v1, RenderTarget.zkmBootstrap("f", (int)9569, (long)(6476360774567764968L ^ var4_1)))) break block6;
                v2 = this;
                if (var3_2 != false) break block7;
                if (v2.K != null) {
                    v3 = Globals.getMc().getTextureManager();
                    v4 = this.K;
                    Intrinsics.checkNotNull(v4);
                    v3.destroyTexture(v4);
                }
            }
            v2 = this;
        }
        v2.K = null;
    }

    public static void zkmSetFlag(boolean bl) {
        h = bl;
    }

    public static boolean zkmGetFlag() {
        return h;
    }

    public static boolean P() {
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        RenderTarget.a = ZkmKeys.a(5175348085078378909L, 471373585935156248L, MethodHandles.lookup().lookupClass()).a(90639656104317L);
                        var20 = RenderTarget.a ^ 125017045715582L;
                        RenderTarget.d = new HashMap<K, V>(13);
                        RenderTarget.zkmSetFlag(true);
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
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "\u0087*\u00b5\u0012\u009b8\u008cC\u00c9\u00bcY\u00d0GJ2Krb P\u00ab\u00ecL9\u009cq@\u00d9\u007fm\u00f9\u00a9\u0010^\u00e7CE\u00f5\u00fa^Vf^\u0091\u00df6,\u001f:\u0010\u0013M\tV\u00dd\u0085Q\u00e2\u00cc=+\u00c7\u008dT\u00fc\u00bf\u0010\u00bbY\u00a0q$<\u00aa\u001f\u0016\u00e7\u00982\u0004\u0015\u00b7\u009a\u0010\u00cd\u0094\u0010\u0091?\u00cc\u00c2\u00ad\u00c4\u0093\u00a1\t\u00af\u009f\n\u000e\u0018E\u00e8&\u00da&\u00d3\u00a8\u00e6r\u00eb*\u000b8\u0012\u00ba\u00e5\u00d7$P\u00fdC\u00cf\u00fa\u00f6 \u0006\u00c7%\u00cc\u0089f\u00a3\u008eZ\u00e3\u00b0H\u0005p\u009b\u00f71\u008e\u00b8\u008c\u0086\u00c4\u00df\u00bb\u00b4~<\u00b4g\u00fc\u00ca\u00de \u008e\u00a0\u00b4'\u00e2\u0019H\u00ac\u0089\u0090\u0097\u0087i\u0013\u00c8\u00ca\u0007\u0007U3\u00b1\u0082\u00a3G\u00fay\u00fbZQ0\u00c7v";
                        var17_6 = 191;
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = RenderTarget.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00fa\u00a7GQ_x\u00c8m\u00c1<\u009cD&\u008d3[(5(\rM\u0001\u00d4\r\u00f8\u00dd9\u009f\u001d\u00a9\u00d4\u00f3z\u00e5\u00cb\u00a6\u008e\u0084\u00c6_\u00f5gf\u00cd\u008b\u0095\u0089\u0080\u0014\u00b3mN\u009d\u00a4o?\u00d0";
                            var17_6 = 57;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = RenderTarget.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                RenderTarget.b = var18_3;
                RenderTarget.c = new String[10];
                RenderTarget.g = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "E\u00f1\"\u00ef+v\u00ac\u00fc\u00ec.[\u0005\u009e\u00aa%\u00c3\u0091?\u00b6\u0086\u00e6p\bWA\u00f0\u00148v% \u0016";
                var5_15 = 32;
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "u\u00bc\u0095\u00b8\u00b3\u0087\u00f5\u009fCD\u00d9\u0080\u00b5\u00d8\u0003\u009e";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        RenderTarget.e = var6_12;
        RenderTarget.f = new Integer[6];
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x41A2;
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
                throw new RuntimeException("su/catlean/bj", exception);
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
            RenderTarget.c[n2] = RenderTarget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RenderTarget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/bj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x55BD;
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
                throw new RuntimeException("su/catlean/bj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RenderTarget.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RenderTarget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/bj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RenderTarget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RenderTarget.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

