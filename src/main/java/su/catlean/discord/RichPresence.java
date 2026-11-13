/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.PresenceAssets;
import su.catlean.discord.PresenceAssetsSerializer;
import su.catlean.discord.RichPresence$Companion;
import su.catlean.discord.RichPresenceSerializer;
import su.catlean.discord.Timestamps;
import su.catlean.discord.TimestampsSerializer;
import su.catlean.module.Module;
import su.catlean.ui.Link$$serializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class RichPresence {
    @NotNull
    public static final RichPresence$Companion Companion;
    @Nullable
    private final String details;
    @Nullable
    private final String state;
    @NotNull
    private final Timestamps timestamps;
    @Nullable
    private final PresenceAssets assets;
    @Nullable
    private final List buttons;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public RichPresence(@Nullable String string, @Nullable String string2, @NotNull Timestamps timestamps, @Nullable PresenceAssets presenceAssets, @Nullable List list) {
        Intrinsics.checkNotNullParameter(timestamps, "timestamps");
        this.details = string;
        this.state = string2;
        this.timestamps = timestamps;
        this.assets = presenceAssets;
        this.buttons = list;
    }

    @Nullable
    public final String getDetails() {
        return this.details;
    }

    @Nullable
    public final String getState() {
        return this.state;
    }

    @NotNull
    public final Timestamps getTimestamps() {
        return this.timestamps;
    }

    @Nullable
    public final PresenceAssets getAssets() {
        return this.assets;
    }

    @Nullable
    public final List getButtons() {
        return this.buttons;
    }

    @Nullable
    public final String getDetails2() {
        return this.details;
    }

    @Nullable
    public final String getState2() {
        return this.state;
    }

    @NotNull
    public final Timestamps getTimestamps2() {
        return this.timestamps;
    }

    @Nullable
    public final PresenceAssets getAssets2() {
        return this.assets;
    }

    @Nullable
    public final List getButtons2() {
        return this.buttons;
    }

    @NotNull
    public final RichPresence copy(@Nullable String string, @Nullable String string2, @NotNull Timestamps timestamps, @Nullable PresenceAssets presenceAssets, @Nullable List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(timestamps, (String)((Object)RichPresence.zkmBootstrap("s", (int)19415, (long)(0x7190E26F49C6CB60L ^ l))));
        return new RichPresence(string, string2, timestamps, presenceAssets, list);
    }

    public static RichPresence copy$default(RichPresence richPresence, String string, String string2, long l, Timestamps timestamps, PresenceAssets presenceAssets, List list, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)926371336203302415L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                string = richPresence.details;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    string2 = richPresence.state;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    timestamps = richPresence.timestamps;
                }
                n2 = n & RichPresence.zkmBootstrap2("j", (int)25035, (long)(0x60378817B3ACAB91L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) {
            if (n2 != 0) {
                presenceAssets = richPresence.assets;
            }
            n2 = n & RichPresence.zkmBootstrap2("j", (int)17902, (long)(0x5FA4A3EF9EE18FB5L ^ l));
        }
        if (n2 != 0) {
            list = richPresence.buttons;
        }
        return richPresence.copy(string, string2, timestamps, presenceAssets, list);
    }

    @NotNull
    public String toString() {
        List list = this.buttons;
        PresenceAssets presenceAssets = this.assets;
        Timestamps timestamps = this.timestamps;
        String string = this.state;
        String string2 = this.details;
        return "Activity(details=" + string2 + ", state=" + string + ", timestamps=" + timestamps + ", assets=" + presenceAssets + ", buttons=" + list + ")";
    }

    public int hashCode() {
        int n = this.details == null ? 0 : this.details.hashCode();
        n = n * 31 + (this.state == null ? 0 : this.state.hashCode());
        n = n * 31 + this.timestamps.hashCode();
        n = n * 31 + (this.assets == null ? 0 : this.assets.hashCode());
        n = n * 31 + (this.buttons == null ? 0 : ((Object)this.buttons).hashCode());
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RichPresence)) {
            return false;
        }
        RichPresence richPresence = (RichPresence)object;
        if (!Intrinsics.areEqual(this.details, richPresence.details)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.state, richPresence.state)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.timestamps, richPresence.timestamps)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.assets, richPresence.assets)) {
            return false;
        }
        return Intrinsics.areEqual(this.buttons, richPresence.buttons);
    }

    @JvmStatic
    public static final void write$Self(RichPresence richPresence, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, richPresence.details);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, richPresence.state);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, TimestampsSerializer.INSTANCE, richPresence.timestamps);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, PresenceAssetsSerializer.INSTANCE, richPresence.assets);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, (SerializationStrategy)lazyArray[4].getValue(), richPresence.buttons);
    }

    public RichPresence(int n, String string, String string2, Timestamps timestamps, PresenceAssets presenceAssets, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (0x1F & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 31, RichPresenceSerializer.INSTANCE.getDescriptor());
        }
        this.details = string;
        this.state = string2;
        this.timestamps = timestamps;
        this.assets = presenceAssets;
        this.buttons = list;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(Link$$serializer.INSTANCE);
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        RichPresence.a = ZkmKeys.a(-2789625979092287146L, -8112105834674818296L, MethodHandles.lookup().lookupClass()).a(71756260976984L);
                        RichPresence.d = new HashMap<K, V>(13);
                        var11 = RichPresence.a ^ 9800932957269L;
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
                        var20_3 = new String[7];
                        var18_4 = 0;
                        var17_5 = "\u00e9v\u008b\u00fa\u00d5\u0095\u00e7\u00c2\u00a3N\u0002P^\u009a\u00ae\u00ac\u00d4\u0006\u00de\u00ccr/,\u00ae8\b\u00a2J\u00a6i\u008cp(o\u00f1\u00db-R6\u00eb\u00baT\u00d7\u001b\u00c0\u008b\u0083&\u00d5b\u0098\u00b0cak\u0082s\u00db\u001d\u0011T\u00a5\u00bcX\u00d8=\u0019\u001b\u00cf\u00c5\u00ees\u00c2 *3\u00e0\u00f8\u0013\u00ea\u00b7\\:Kq\u00e1U0^\u00d2uR\u00c7\u00c9G\u00a5_\u00c1\u00960\u00ac\u0083R\u00f8\u008dE y\u00c2\u00df\u00c5\u00e7\u009f\u00a4i\u00b2\u00dak\u009e`!\u00de\u00fb\u00c4\u00c5@}b,@\u00d8Y&\u0005\u0004[\u008cJ$ \u000e\u00f1n\u00c6D\u00eb\u00e7\u00e7\u0085\u00f8(\u00e2@`\u00ectb\u00e1*\u0093|a\u00a1.P\u0014\u00ba\u00ad5\u00bb5\u0095";
                        var19_6 = 172;
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
                            var20_3[var18_4++] = RichPresence.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0081\u0086\u00dc8D\u0099\u00a9\t\u00df\u0013Pj\u00e5\u00e50\u0094\u008c\u00ae\u00c6\u00c6N\u00a4\b\u00d1\u0098_\u0080c\u0080\u007f\u007f. \u009czR\u00c4\r\u0014L\u001c\u00b1\u00fctR\u009e\u00d9\u00fd:\u00b2\u00cd9\u001e*CjO\u00e6\u00dc\u00ef\u00060=e\u00d5";
                            var19_6 = 65;
                            var16_7 = 32;
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
                            var20_3[var18_4++] = RichPresence.zkmDecodeUtf8(var21_9).intern();
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
                RichPresence.b = var20_3;
                RichPresence.c = new String[7];
                RichPresence.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "P\u00e8C\u00b1y\u0087h\u00f8e=\u00b8Z\u00d9D4(";
                var5_15 = 16;
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
                    var4_14 = "\u008a\u00a1\u0082Wx\u0089\u00bdm\u00e3&\u00d3\u00dd\u0016\u0081Wi";
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
        RichPresence.e = var6_12;
        RichPresence.f = new Integer[4];
        RichPresence.Companion = new RichPresence$Companion(null);
        var22_20 = new Lazy[]{null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)())};
        RichPresence.$childSerializers = var22_20;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x53E3;
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
                throw new RuntimeException("su/catlean/yq", exception);
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
            RichPresence.c[n2] = RichPresence.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RichPresence.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/yq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x47EF;
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
                throw new RuntimeException("su/catlean/yq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RichPresence.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RichPresence.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/yq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RichPresence.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RichPresence.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

