/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ProfileConfig$Companion;
import su.catlean.config.ProfileConfigSerializer;
import su.catlean.config.SettingsConfigSerializer;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ProfileConfig {
    @NotNull
    public static final ProfileConfig$Companion Companion;
    @NotNull
    private final String id;
    @NotNull
    private List modules;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static String[] Z;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public ProfileConfig(@NotNull String string, @NotNull List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ProfileConfig.zkmBootstrap("u", (int)28458, (long)(0x6237F2B4B625BA1EL ^ l))));
        Intrinsics.checkNotNullParameter(list, (String)((Object)ProfileConfig.zkmBootstrap("u", (int)1556, (long)(0x2CD6DE521788D323L ^ l))));
        this.id = string;
        this.modules = list;
    }

    public ProfileConfig(String string, List list, DefaultConstructorMarker defaultConstructorMarker) {
        list = CollectionsKt.emptyList();
        this(string, list);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final List getModules() {
        return this.modules;
    }

    public final void setModules(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.modules = list;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final List getModules2() {
        return this.modules;
    }

    @NotNull
    public final ProfileConfig copy(@NotNull String string, @NotNull List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ProfileConfig.zkmBootstrap("u", (int)32018, (long)(0x3EEECFBBBB48BF20L ^ l))));
        Intrinsics.checkNotNullParameter(list, (String)((Object)ProfileConfig.zkmBootstrap("u", (int)27203, (long)(0x55DEEBF7615EA877L ^ l))));
        return new ProfileConfig(string, list);
    }

    public static ProfileConfig copy$default(ProfileConfig profileConfig, String string, List list, int n, Object object) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 48 | (long)n3 << 48 >>> 16 | (long)n4 << 32 >>> 32) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2646016064581427825L, (long)l);
        int n5 = n & 1;
        if (callSite != null) {
            if (n5 != 0) {
                string = profileConfig.id;
            }
            n5 = n & 2;
        }
        if (n5 != 0) {
            list = profileConfig.modules;
        }
        return profileConfig.copy(string, list);
    }

    @NotNull
    public String toString() {
        return "ServiceConfig(id=" + this.id + ", modules=" + this.modules + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * (int)e + ((Object)this.modules).hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ProfileConfig)) {
            return false;
        }
        ProfileConfig profileConfig = (ProfileConfig)object;
        if (!Intrinsics.areEqual(this.id, profileConfig.id)) {
            return false;
        }
        return Intrinsics.areEqual(this.modules, profileConfig.modules);
    }

    @JvmStatic
    public static final void write$Self(ProfileConfig profileConfig, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block0: {
            Lazy[] lazyArray = $childSerializers;
            compositeEncoder.encodeStringElement(serialDescriptor, 0, profileConfig.id);
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) ? true : !Intrinsics.areEqual(profileConfig.modules, CollectionsKt.emptyList()))) break block0;
            compositeEncoder.encodeSerializableElement(serialDescriptor, 1, (SerializationStrategy)lazyArray[1].getValue(), profileConfig.modules);
        }
    }

    public ProfileConfig(int n, String string, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 1, ProfileConfigSerializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.modules = (n & 2) == 0 ? CollectionsKt.emptyList() : list;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(SettingsConfigSerializer.INSTANCE);
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ProfileConfig.a = ZkmKeys.a(8000383159215201605L, -3329495775121983527L, MethodHandles.lookup().lookupClass()).a(36221155869830L);
                    var14 = ProfileConfig.a ^ 136470732487278L;
                    ProfileConfig.d = new HashMap<K, V>(13);
                    ProfileConfig.zkmSetFlag(null);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[7];
                    var10_4 = 0;
                    var9_5 = "\u00e4q\u001f^:}\r\u00f8\u00e9C\u00b8\u008d7Z\u00cf\u008e\u00104B=8\u0086\u00f0\u00ff\u00e6\u0088\r\u0011\u008f\u0082\u00a07\u00a7\u0010kM\u00da\u00bcG\u001f$\u0090UL\u00a9\u00af*\u00bdi\u00d7 \u0001\u008f-\u00ba5\u00b0{\u00c0P\u00f0Y\u0091, \u00f1\u0090\u00ef\u0017\u0080\u0090@\u00ed\u0097t\u00bb\u009cb\u0017i!z\u0090\u0010\u00c2r\u00a9\u00e2\u00c2^\u0089\u00f86m\u008aw\u00f5\u00ab\u000fS";
                    var11_6 = 100;
                    var8_7 = 16;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = ProfileConfig.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00a5\u000f\u00df\u00e4B~\u00d8f\u00dbx\u008b\u0003\u00c8/\u00ee\u00e9r(v+\u00d1\u009b\nI\u00a8\u007f61\u0004\u00de<\u009cK\u00a1<\u00eb\u00f7\u0019\u00bb\u0012\u0010\u00fb ?yM\u0086\u0086HW\u00a2u~\u0007\u00faF\u00dd";
                        var11_6 = 57;
                        var8_7 = 40;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = ProfileConfig.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            ProfileConfig.b = var12_3;
            ProfileConfig.c = new String[7];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{32, 97, 3, 104, -73, 31, 79, -104});
        ** while (true)
        ProfileConfig.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        ProfileConfig.Companion = new ProfileConfig$Companion(null);
        var16_13 = new Lazy[]{null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)())};
        ProfileConfig.$childSerializers = var16_13;
    }

    public static void zkmSetFlag(String[] stringArray) {
        Z = stringArray;
    }

    public static String[] zkmGetFlag() {
        return Z;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5BCA;
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
                throw new RuntimeException("su/catlean/y_", exception);
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
            ProfileConfig.c[n2] = ProfileConfig.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProfileConfig.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/y_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProfileConfig.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

