/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.PresenceAssets$Companion;
import su.catlean.discord.PresenceAssetsSerializer;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class PresenceAssets {
    @NotNull
    public static final PresenceAssets$Companion Companion;
    @Nullable
    private final String largeImage;
    @Nullable
    private final String largeText;
    @Nullable
    private final String smallImage;
    @Nullable
    private final String smallText;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    public PresenceAssets(@Nullable String string, @Nullable String string2, @Nullable String string3, @Nullable String string4) {
        this.largeImage = string;
        this.largeText = string2;
        this.smallImage = string3;
        this.smallText = string4;
    }

    @Nullable
    public final String getLargeImage() {
        return this.largeImage;
    }

    @Nullable
    public final String getLargeText() {
        return this.largeText;
    }

    @Nullable
    public final String getSmallImage() {
        return this.smallImage;
    }

    @Nullable
    public final String getSmallText() {
        return this.smallText;
    }

    @Nullable
    public final String getLargeImage2() {
        return this.largeImage;
    }

    @Nullable
    public final String getLargeText2() {
        return this.largeText;
    }

    @Nullable
    public final String getSmallImage2() {
        return this.smallImage;
    }

    @Nullable
    public final String getSmallText2() {
        return this.smallText;
    }

    @NotNull
    public final PresenceAssets copy(@Nullable String string, @Nullable String string2, @Nullable String string3, @Nullable String string4) {
        return new PresenceAssets(string, string2, string3, string4);
    }

    public static PresenceAssets copy$default(PresenceAssets presenceAssets, String string, String string2, long l, String string3, String string4, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-6194945343935950637L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                string = presenceAssets.largeImage;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    string2 = presenceAssets.largeText;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) {
            if (n2 != 0) {
                string3 = presenceAssets.smallImage;
            }
            n2 = n & PresenceAssets.zkmBootstrap2("k", (int)26938, (long)(0x1DCC967133700A3BL ^ l));
        }
        if (n2 != 0) {
            string4 = presenceAssets.smallText;
        }
        return presenceAssets.copy(string, string2, string3, string4);
    }

    @NotNull
    public String toString() {
        String string = this.smallText;
        String string2 = this.smallImage;
        String string3 = this.largeText;
        String string4 = this.largeImage;
        return "ActivityAsset(largeImage=" + string4 + ", largeText=" + string3 + ", smallImage=" + string2 + ", smallText=" + string + ")";
    }

    public int hashCode() {
        Module[] moduleArray = new Module[]{};
        int n = this.largeImage == null ? 0 : this.largeImage.hashCode();
        n = n * 31 + (this.largeText == null ? 0 : this.largeText.hashCode());
        n = n * 31 + (this.smallImage == null ? 0 : this.smallImage.hashCode());
        n = n * 31 + (this.smallText == null ? 0 : this.smallText.hashCode());
        return n;
    }

    public boolean equals(@Nullable Object object) {
        Module[] moduleArray = new Module[]{};
        if (this == object) {
            return true;
        }
        if (!(object instanceof PresenceAssets)) {
            return false;
        }
        PresenceAssets presenceAssets = (PresenceAssets)object;
        if (!Intrinsics.areEqual(this.largeImage, presenceAssets.largeImage)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.largeText, presenceAssets.largeText)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.smallImage, presenceAssets.smallImage)) {
            return false;
        }
        return Intrinsics.areEqual(this.smallText, presenceAssets.smallText);
    }

    @JvmStatic
    public static final void write$Self(PresenceAssets presenceAssets, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, presenceAssets.largeImage);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, presenceAssets.largeText);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, presenceAssets.smallImage);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, presenceAssets.smallText);
    }

    public PresenceAssets(int n, String string, String string2, String string3, String string4, SerializationConstructorMarker serializationConstructorMarker) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 48 | (long)n3 << 32 >>> 16 | (long)n4 << 48 >>> 48) ^ a;
        if (PresenceAssets.zkmBootstrap2("k", (int)13712, (long)(0x37F4DF3CF47A8E95L ^ l)) != (PresenceAssets.zkmBootstrap2("k", (int)8600, (long)(0x3B418684B4261A9CL ^ l)) & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, (int)PresenceAssets.zkmBootstrap2("k", (int)8600, (long)(0x3B418684B4261A9CL ^ l)), PresenceAssetsSerializer.INSTANCE.getDescriptor());
        }
        this.largeImage = string;
        this.largeText = string2;
        this.smallImage = string3;
        this.smallText = string4;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PresenceAssets.a = ZkmKeys.a(8149197362178782050L, 7180011240225930749L, MethodHandles.lookup().lookupClass()).a(280768235004888L);
                        PresenceAssets.e = new HashMap<K, V>(13);
                        var11 = PresenceAssets.a ^ 100384199851021L;
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
                        var20_3 = new String[4];
                        var18_4 = 0;
                        var17_5 = "#_\u00cf<\u00b8\u00af\u0011\u0082\u00e8>\u00abt>\u00a2ng*\u00cf\u00d0j;\u00fb\u0014`5\u00a4et\u0085\u00fbv\u007f\tw\u001cx\u00a7\u00b1\u008ahlp|M\u0001\u0016\u00fc\u00b1 \u0016\u00a9e(\u001e\u008b\u008b`\u00e5J\u00a7\u00fbc\u00d6|\u00ce\u00fbxe^\u00e7\u00ce;\u00ab<\u0004\u00f58fcQ!";
                        var19_6 = 81;
                        var16_7 = 48;
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
                            var20_3[var18_4++] = PresenceAssets.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "W\u00e1\u00d9\u00d9k\u00c0\u00d2\u00c6\u00a9MX>\u00d3>\u00feSh\u001fBZ\u00c8\u00e9m\u00db G@DD>o\u00f0\u008e\u00b25\u0089+\u00e0N\u00f5\u00be0\u00a0E\u00c5\u00d0x\u008b\u00ed\u00c5q\u00c96\u00a4\u00ec?v";
                            var19_6 = 57;
                            var16_7 = 24;
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
                            var20_3[var18_4++] = PresenceAssets.zkmDecodeUtf8(var21_9).intern();
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
                PresenceAssets.b = var20_3;
                PresenceAssets.c = new String[4];
                PresenceAssets.i = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u00b2\u00e5\u00e3\t\u00a0S\u00d1\u00f8\u0014k\u000eHW\u0084p\u00fd9\b\u001a*\u008a\u00d6\u00a4\t";
                var5_15 = 24;
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
                    var4_14 = "\u00c3/\u001a\u000e\u00ef(\u000e\u007f\u00b3\u00e7,\u0094\u00b7\u00bbM\\";
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
        PresenceAssets.g = var6_12;
        PresenceAssets.h = new Integer[5];
        PresenceAssets.Companion = new PresenceAssets$Companion(null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5205;
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
                throw new RuntimeException("su/catlean/an", exception);
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
            PresenceAssets.c[n2] = PresenceAssets.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PresenceAssets.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/an" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x486D;
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
                throw new RuntimeException("su/catlean/an", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PresenceAssets.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PresenceAssets.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/an" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PresenceAssets.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PresenceAssets.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

