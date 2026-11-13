/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap
 */
package su.catlean.render.font;

import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.RenderUtil;
import su.catlean.render.TextureRegion;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class GlyphPage {
    private final char fromIncl;
    private final char toExcl;
    @NotNull
    private final Font font;
    @NotNull
    private final Identifier bindToTexture;
    private final int pixelPadding;
    @NotNull
    private final Char2ObjectArrayMap glyphs;
    private int i;
    private int l;
    private boolean generated;
    private static final long a = ZkmKeys.a(-3566970759257700676L, 4329545133701635575L, MethodHandles.lookup().lookupClass()).a(258833237757873L);
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public GlyphPage(char c, char c2, @NotNull Font font, @NotNull Identifier identifier, int n) {
        Intrinsics.checkNotNullParameter(font, "font");
        Intrinsics.checkNotNullParameter(identifier, "bindToTexture");
        this.fromIncl = c;
        this.toExcl = c2;
        this.font = font;
        this.bindToTexture = identifier;
        this.pixelPadding = n;
        this.glyphs = new Char2ObjectArrayMap();
    }

    @NotNull
    public final Font getFont() {
        return this.font;
    }

    @NotNull
    public final Identifier getBindToTexture() {
        return this.bindToTexture;
    }

    public final int V() {
        return this.i;
    }

    public final void x(int n) {
        this.i = n;
    }

    public final int x() {
        return this.l;
    }

    public final void V(int n) {
        this.l = n;
    }

    @Nullable
    public final TextureRegion ensureLoaded(long l, char c) {
        block0: {
            long l2 = (l = a ^ l) ^ 0x50242BE07796L;
            if (this.generated) break block0;
            this.generate(l2);
        }
        return (TextureRegion)this.glyphs.get(c);
    }

    public final void destroy() {
        Globals.getMc().getTextureManager().destroyTexture(this.bindToTexture);
        this.glyphs.clear();
        this.i = -1;
        this.l = -1;
        this.generated = false;
    }

    public final boolean contains(char c, long l) {
        char c2;
        l = a ^ l;
        char c3 = this.fromIncl;
        if (c < this.toExcl) {
            c2 = c3;
            if (l >= 0L) {
                c2 = c2 <= c ? (char)'\u0001' : '\u0000';
            }
        } else {
            c2 = '\u0000';
        }
        return c2 != '\u0000';
    }

    private final Font getFontFor(char c) {
        if (this.font.canDisplay(c)) {
            return this.font;
        }
        return this.font;
    }

    private final void generate(long l) {
        block6: {
            Object object;
            l = a ^ l;
            if (this.generated) {
                return;
            }
            int n = this.toExcl - this.fromIncl - 1;
            int n2 = (int)(Math.ceil(Math.sqrt(n)) * 1.5);
            this.glyphs.clear();
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            int n8 = 0;
            int n9 = 0;
            List list = new ArrayList();
            AffineTransform affineTransform = new AffineTransform();
            FontRenderContext fontRenderContext = new FontRenderContext(affineTransform, true, false);
            while (n3 <= n) {
                char c = (char)(this.fromIncl + n3);
                object = this.getFontFor(c);
                Rectangle2D rectangle2D = ((Font)object).getStringBounds(String.valueOf(c), fontRenderContext);
                int n10 = (int)Math.ceil(rectangle2D.getWidth());
                int n11 = (int)Math.ceil(rectangle2D.getHeight());
                ++n3;
                n5 = (int)Math.max((double)n5, (double)(n7 + n10));
                n6 = (int)Math.max((double)n6, (double)(n8 + n11));
                int n12 = n4;
                int n13 = n2;
                if (l >= 0L) {
                    if (n12 >= n13) {
                        n7 = 0;
                        n8 += n9 + this.pixelPadding;
                        n4 = 0;
                        n9 = 0;
                    }
                    n9 = (int)Math.max((double)n9, (double)n11);
                    list.add(new TextureRegion(n7, n8, n10, n11, c, this));
                    n12 = n7;
                    n13 = n10 + this.pixelPadding;
                }
                n7 = n12 + n13;
                ++n4;
                if (l > 0L) continue;
            }
            BufferedImage bufferedImage = new BufferedImage((int)Math.max((double)(n5 + this.pixelPadding), 1.0), (int)Math.max((double)(n6 + this.pixelPadding), 1.0), 2);
            this.i = bufferedImage.getWidth();
            this.l = bufferedImage.getHeight();
            object = bufferedImage.createGraphics();
            ((Graphics)object).setColor(new Color((int)GlyphPage.zkmBootstrap2("e", (int)23261, (long)(0xA7E1DF7634072F0L ^ l)), (int)GlyphPage.zkmBootstrap2("e", (int)9314, (long)(0x45BC5C22374C0C4EL ^ l)), (int)GlyphPage.zkmBootstrap2("e", (int)9314, (long)(0x45BC5C22374C0C4EL ^ l)), 0));
            ((Graphics)object).fillRect(0, 0, this.i, this.l);
            ((Graphics)object).setColor(Color.WHITE);
            ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            for (TextureRegion textureRegion : list) {
                char c = textureRegion.getValue();
                ((Graphics)object).setFont(this.getFontFor(c));
                ((Graphics2D)object).drawString(String.valueOf(textureRegion.getValue()), textureRegion.getU(), textureRegion.getV() + ((Graphics)object).getFontMetrics().getAscent());
                this.glyphs.put(textureRegion.getValue(), (Object)textureRegion);
                if (l > 0L) {
                    if (l >= 0L) continue;
                }
                break block6;
            }
            RenderUtil.INSTANCE.registerTexture(this.bindToTexture, bufferedImage);
            this.generated = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        d = new HashMap(13);
        long l = a ^ 0x71F82913DFAEL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\u00bf\u0083\u0098\u000b\u00c8\u00fb\u007f\u00cajy\u00ca\u00cf\u001d\u00c6\u00a8\u00e31q\u0001\u00d7o\u00e8\u0007\u00ef\u0010\u0004\u00cb\u00af7\rwt\u00e5\u00d9\u00dac\u00d1\u00a1\r\u00d1\u0089";
        int n2 = 41;
        int n3 = 24;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = GlyphPage.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[2];
        g = new HashMap(13);
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        long[] lArray = new long[2];
        int n6 = 0;
        String string2 = "\u00aafv\u00f0i\u0085x\u00db@\u00bc\u008bE\u00fd\u00ad\u0016\u00f7";
        int n7 = 16;
        int n8 = 0;
        do {
            byte[] byArray6 = string2.substring(n8, n8 += 8).getBytes("ISO-8859-1");
            int n9 = n6++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n9] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n8 < n7);
        e = lArray;
        f = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x46A;
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
                throw new RuntimeException("su/catlean/fj", exception);
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
            GlyphPage.c[n2] = GlyphPage.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GlyphPage.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7947;
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
                throw new RuntimeException("su/catlean/fj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            GlyphPage.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = GlyphPage.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GlyphPage.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(GlyphPage.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

