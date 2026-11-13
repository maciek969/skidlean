/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.net.URI;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TextureUtil {
    @NotNull
    public static final TextureUtil INSTANCE;
    @NotNull
    private static final ConcurrentHashMap textureCache;
    @NotNull
    private static final ConcurrentHashMap pendingLoads;
    @NotNull
    private static final Identifier identifier;
    private static int V;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private TextureUtil() {
    }

    @NotNull
    public final Identifier getTexture(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "url");
        Identifier identifier = (Identifier)textureCache.get(string);
        if (identifier != null) {
            Identifier identifier2 = identifier;
            return identifier2;
        }
        Intrinsics.checkNotNullExpressionValue(pendingLoads.computeIfAbsent(string, arg_0 -> TextureUtil.getTexture$lambda$1(arg_0 -> TextureUtil.loadFromUrl(string, arg_0), arg_0)), "computeIfAbsent(...)");
        return TextureUtil.identifier;
    }

    @NotNull
    public final BufferedImage cropSquare(@NotNull BufferedImage bufferedImage) {
        Intrinsics.checkNotNullParameter(bufferedImage, "image");
        int n = bufferedImage.getWidth();
        int n2 = bufferedImage.getHeight();
        int n3 = Math.min(n, n2);
        int n4 = (n - n3) / 2;
        int n5 = (n2 - n3) / 2;
        BufferedImage bufferedImage2 = bufferedImage.getSubimage(n4, n5, n3, n3);
        Intrinsics.checkNotNullExpressionValue(bufferedImage2, "getSubimage(...)");
        return bufferedImage2;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @NotNull
    public final NativeImage toNativeImage(int var1_1, @NotNull NativeImage var2_2, short var3_3, char var4_4) {
        block9: {
            var5_5 = ((long)var1_1 << 32 | (long)var3_3 << 48 >>> 32 | (long)var4_4 << 48 >>> 48) ^ TextureUtil.a;
            v0 = ZkmReferences.zkmBootstrap("\u00c5", (long)-1639453670343702722L, (long)var5_5);
            Intrinsics.checkNotNullParameter(var2_2, (String)TextureUtil.zkmBootstrap("k", (int)28680, (long)(9140819960015107906L ^ var5_5)));
            var7_6 = v0;
            var8_7 = TextureUtil.zkmBootstrap2("w", (int)3546, (long)(8453550598200573890L ^ var5_5));
            var9_8 = TextureUtil.zkmBootstrap2("w", (int)27751, (long)(8849635647134457468L ^ var5_5));
            var10_9 = var2_2.getWidth();
            var11_10 = var2_2.getHeight();
            var12_11 = var2_2.getHeight();
            block0: while (true) {
                if (var8_7 < var10_9) ** GOTO lbl22
                v1 = var9_8;
                do {
                    block14: {
                        block13: {
                            block12: {
                                v2 = var12_11;
                                v3 = var7_6;
                                if (var4_4 <= '\u0000') break block12;
                                if (v3 != null) break block13;
                                v3 = var7_6;
                            }
                            if (v3 != null) break block13;
                            if (v1 >= v2) break block14;
lbl22:
                            // 2 sources

                            var8_7 *= 2;
                            v1 = var9_8;
                            v2 = 2;
                        }
                        var9_8 = v1 * v2;
                        if (var7_6 == null) continue block0;
                    }
                    var13_12 = new NativeImage((int)var8_7, (int)var9_8, true);
                    v4 = 0;
                } while (var1_1 < 0);
                break;
            }
            var14_13 = v4;
            while (var14_13 < var10_9) {
                block10: {
                    block11: {
                        if (var7_6 != null) break block9;
                        var15_14 = 0;
                        block3: while (var15_14 < var11_10) {
                            var13_12.setColorArgb(var14_13, var15_14, var2_2.getColorArgb(var14_13, var15_14));
                            ++var15_14;
                            do {
                                v5 = var7_6;
                                if (var3_3 <= 0) break block10;
                                if (v5 != null) break block11;
                                if (var7_6 == null) continue block3;
                            } while (var1_1 < 0);
                        }
                        ++var14_13;
                    }
                    v5 = var7_6;
                }
                if (v5 == null) continue;
            }
            var2_2.close();
            if (var1_1 > 0) {
                // empty if block
            }
        }
        return var13_12;
    }

    private static final String getDynamicTextureId(int n) {
        return "ctl_dyn_texture_" + n;
    }

    private static final void downloadImage$lambda$0(Identifier identifier, NativeImage nativeImage, String string, CompletableFuture completableFuture, int n) {
        Globals.getMc().getTextureManager().registerTexture(identifier, new NativeImageBackedTexture(() -> TextureUtil.getDynamicTextureId(n), nativeImage));
        ((Map)textureCache).put(string, identifier);
        completableFuture.complete(identifier);
        pendingLoads.remove(string);
    }

    private static final void downloadImage(String string, CompletableFuture completableFuture) {
        Identifier identifier = Ids.id("catlean-img-" + (int)(Random.Default.nextFloat() * 100000.0f));
        InputStream inputStream = URI.create(string).toURL().openStream();
        NativeImage nativeImage = NativeImage.read(inputStream);
        Intrinsics.checkNotNullExpressionValue(nativeImage, "read(...)");
        NativeImage nativeImage2 = nativeImage;
        int n = ++V;
        Globals.getMc().execute(() -> TextureUtil.downloadImage$lambda$0(identifier, nativeImage2, string, completableFuture, n));
    }

    private static final CompletableFuture loadFromUrl(String string, String string2) {
        CompletableFuture completableFuture;
        Intrinsics.checkNotNullParameter(string2, "it");
        CompletableFuture completableFuture2 = completableFuture = new CompletableFuture();
        new Thread(() -> TextureUtil.downloadImage(string, completableFuture2)).start();
        return completableFuture;
    }

    private static final CompletableFuture getTexture$lambda$1(Function1 function1, Object object) {
        return (CompletableFuture)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        TextureUtil.a = ZkmKeys.a(-5246863676177136358L, 9119616779345220253L, MethodHandles.lookup().lookupClass()).a(126625378116895L);
                        var20 = TextureUtil.a ^ 36544123156093L;
                        TextureUtil.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[11];
                        var16_4 = 0;
                        var15_5 = "~%:@\u00f8\u00eak\u00f3N\u001a\u00ee\u00cf\u00c2\u00d7\u00e4_\u0010\u009e)2\u00a6Cm\u001f2\u00c3^\u0016=\u00e7S\u00fd\u0010\u0010\u00ac\u00f5\u000e\u00c9\u00e3U}\u00da\u00ef\u00be\u00bbX\u00ee\n?\u009b(\u00b8\rtI\u0017\u00fa\u00e0\u0014\u0002F\u00b02@\u00e6\u00d8j\u009a\u00ca\u00fa\u00fc;\u0018_\u00e0\u00c9\u00e4\u00f1\u0016\u00bd\u007f\u00e7\u0011\u00ccn\u00feV\u0098Fs\u0085(d>m[\u00b81In\u0014|\u0090A0\u00b0\u00d9\u00bd6\u0087\b\u00991\u00f9\u0086F#\u00c9\u00c7\n\u00e0s\u00fc1-&\u00bf$\u00f1\u00f5\u008f@0:q\u00ff\u0080^\u0085s,'6\u00b7\u00f4\u00cc\u0007+\u00e2q\u00af#\u00d1\u0016\u00fc\u0083\u0083\u00dc\u009a\u00edJ\u00d1\u00db\u00a7~\u00e1B\u008f\u009f\u007fB\u008e\u00a3}\u00ed\u00c4\u00a5\u00ca\u00c9\u0096\u008f\u0010My8\u00be\u0082\u0001\u001e\u0094z\u00e9\u00afsZ\u00b1\u0095\u00c4\u0010I\u00c3d\u00c0Rk\u00cd\u00ba\u00e8\u0016\u00b4\u00b5\u00b8\u00936\u009c\u0018y}\u001fZUo\u00c9\u000bN?\u0085\u00ad\u00f4\u0088\u000fc\u0090\u00dc+\u00dcXo\u00f8\u00f9";
                        var17_6 = 240;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = TextureUtil.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c1I\u00eeY!\u00fc3Z\u00dc\u00a1\u0002\u00e9\u0090\\1\u00a9\u00aa\u00eb\u0018U9\u0086::j\u00f9L<@b\u00ac<(#h*\u0090\u00a8X\tR\u00a5\u00ec\u0095\u008f\u00c5\u00e8\u0093\u00a7!)v\u0082R\u00ba\u008aI\u009cU([\u00b0V\u00df\u00c7\u001fL\u00ee\u001f\u00c9wi\u0090";
                            var17_6 = 73;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = TextureUtil.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                TextureUtil.b = var18_3;
                TextureUtil.c = new String[11];
                TextureUtil.g = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "`\u0093J\u000e\u0095\u00f9\u001c\u00c1\f\u0093\u00026sa\u00a0M~-`D\u00f5RM\u0099";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        TextureUtil.e = var6_12;
        TextureUtil.f = new Integer[3];
        TextureUtil.INSTANCE = new TextureUtil();
        TextureUtil.textureCache = new ConcurrentHashMap<K, V>();
        TextureUtil.pendingLoads = new ConcurrentHashMap<K, V>();
        TextureUtil.identifier = Ids.id("catlean", "textures/ui/windows/config.png");
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2F3F;
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
                throw new RuntimeException("su/catlean/wz", exception);
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
            TextureUtil.c[n2] = TextureUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TextureUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5A6D;
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
                throw new RuntimeException("su/catlean/wz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TextureUtil.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TextureUtil.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/wz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TextureUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TextureUtil.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

