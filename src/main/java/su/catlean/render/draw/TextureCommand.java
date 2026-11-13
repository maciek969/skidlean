/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 */
package su.catlean.render.draw;

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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TextureCommand {
    @NotNull
    private final Identifier texture;
    @NotNull
    private final Matrix3x2f matrix;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public TextureCommand(@NotNull Identifier identifier, @NotNull Matrix3x2f matrix3x2f) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(identifier, (String)((Object)TextureCommand.zkmBootstrap("s", (int)1482, (long)(0x3185A165CF1846EL ^ l))));
        Intrinsics.checkNotNullParameter(matrix3x2f, (String)((Object)TextureCommand.zkmBootstrap("s", (int)22544, (long)(0x31DCA8492F63D9B2L ^ l))));
        this.texture = identifier;
        this.matrix = matrix3x2f;
    }

    @NotNull
    public final Identifier getTexture() {
        return this.texture;
    }

    @NotNull
    public final Matrix3x2f getMatrix() {
        return this.matrix;
    }

    @NotNull
    public final Identifier getTexture2() {
        return this.texture;
    }

    @NotNull
    public final Matrix3x2f getMatrix2() {
        return this.matrix;
    }

    @NotNull
    public final TextureCommand copy(@NotNull Identifier identifier, @NotNull Matrix3x2f matrix3x2f) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(identifier, (String)((Object)TextureCommand.zkmBootstrap("s", (int)29798, (long)(0x53BB5E65B2AEF318L ^ l))));
        Intrinsics.checkNotNullParameter(matrix3x2f, (String)((Object)TextureCommand.zkmBootstrap("s", (int)12002, (long)(0xDDD42DFB768299FL ^ l))));
        return new TextureCommand(identifier, matrix3x2f);
    }

    public static /* synthetic */ TextureCommand copy$default(TextureCommand textureCommand, Identifier identifier, Matrix3x2f matrix3x2f, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-6783818495113565985L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                identifier = textureCommand.texture;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            matrix3x2f = textureCommand.matrix;
        }
        return textureCommand.copy(identifier, matrix3x2f);
    }

    @NotNull
    public String toString() {
        return "BatchKey(texture=" + this.texture + ", matrix=" + this.matrix + ")";
    }

    public int hashCode() {
        int n = this.texture.hashCode();
        n = n * (int)e + this.matrix.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        String[] stringArray = new String[]{};
        if (this == object) {
            return true;
        }
        if (!(object instanceof TextureCommand)) {
            return false;
        }
        TextureCommand textureCommand = (TextureCommand)object;
        if (!Intrinsics.areEqual(this.texture, textureCommand.texture)) {
            return false;
        }
        return Intrinsics.areEqual(this.matrix, textureCommand.matrix);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    TextureCommand.a = ZkmKeys.a(-9204319520826755182L, -4410248780512164879L, MethodHandles.lookup().lookupClass()).a(255099186751597L);
                    TextureCommand.d = new HashMap<K, V>(13);
                    var5 = TextureCommand.a ^ 54889015447275L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[6];
                    var12_4 = 0;
                    var11_5 = "\u00d2~iZ\u00b6y/\u00fa\u00a9\u00e4\u00b40J\u00c8m\u00da\u00fd<\u00d9!&>\u0007\u00d8({\u00a2\u00a6\u00d8\u00e0`%1\u0013F\u0011\u00bd%[n\u0010\u0005\u00d6\u00bc?mx\u001d\u00a0g\u0089o\u00d4\u001b\u00afQM\u0010\u0094\u00e3\u00b7`F-\u008c\u00ea\u00ad\u00bep\u00d3\u00fcQ\u009f\u00ce\u0010\u00d8\u0093y\u00eaJ\u00bd5H.\u0018\u00eb)\r\u0013\u008d\f";
                    var13_6 = 91;
                    var10_7 = 40;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = TextureCommand.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "jn\u00af\u000f\u00f4\u00fd\u009c\u00f1\u00fa\u00c4\u008fa\u00b9\u00dc\u0016\u001a\u00c9`G-\u001f\u0091\u00c3\u0088\u00f6\u00b3P\u00a0b\u00da9?\u0010|\\\u00b7Pk\u001a\u0083\u0015\u00b0\u00f89\u009aKtnk";
                        var13_6 = 49;
                        var10_7 = 32;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = TextureCommand.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            TextureCommand.b = var14_3;
            TextureCommand.c = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{127, -9, 103, 2, 126, -50, -9, 61});
        ** while (true)
        TextureCommand.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x234F;
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
                throw new RuntimeException("su/catlean/t1", exception);
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
            TextureCommand.c[n2] = TextureCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TextureCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/t1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TextureCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

