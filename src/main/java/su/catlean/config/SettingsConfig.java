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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.SettingEntry$$serializer;
import su.catlean.config.SettingsConfig$Companion;
import su.catlean.config.SettingsConfigSerializer;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class SettingsConfig {
    @NotNull
    public static final SettingsConfig$Companion Companion;
    @NotNull
    private final String id;
    @NotNull
    private final List settings;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static boolean t;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public SettingsConfig(@NotNull String string, @NotNull List list) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(list, "settings");
        this.id = string;
        this.settings = list;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final List getSettings() {
        return this.settings;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final List getSettings2() {
        return this.settings;
    }

    @NotNull
    public final SettingsConfig copy(@NotNull String string, @NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)SettingsConfig.zkmBootstrap("t", (int)25662, (long)(0x10D6A1161BAC7431L ^ l))));
        Intrinsics.checkNotNullParameter(list, (String)((Object)SettingsConfig.zkmBootstrap("t", (int)1667, (long)(0x6356C9C69C8E168DL ^ l))));
        return new SettingsConfig(string, list);
    }

    public static SettingsConfig copy$default(SettingsConfig settingsConfig, String string, List list, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7318114834929492807L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                string = settingsConfig.id;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            list = settingsConfig.settings;
        }
        return settingsConfig.copy(string, list);
    }

    @NotNull
    public String toString() {
        return "ModuleValue(id=" + this.id + ", settings=" + this.settings + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * (int)e + ((Object)this.settings).hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        Module[] moduleArray = new Module[]{};
        if (this == object) {
            return true;
        }
        if (!(object instanceof SettingsConfig)) {
            return false;
        }
        SettingsConfig settingsConfig = (SettingsConfig)object;
        if (!Intrinsics.areEqual(this.id, settingsConfig.id)) {
            return false;
        }
        return Intrinsics.areEqual(this.settings, settingsConfig.settings);
    }

    @JvmStatic
    public static final void write$Self(SettingsConfig settingsConfig, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, settingsConfig.id);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, (SerializationStrategy)lazyArray[1].getValue(), settingsConfig.settings);
    }

    public SettingsConfig(int n, String string, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 3, SettingsConfigSerializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.settings = list;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(SettingEntry$$serializer.INSTANCE);
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
                    SettingsConfig.a = ZkmKeys.a(-948373274984763685L, -1126620234001459598L, MethodHandles.lookup().lookupClass()).a(83186511652295L);
                    var14 = SettingsConfig.a ^ 27302823185620L;
                    SettingsConfig.d = new HashMap<K, V>(13);
                    SettingsConfig.zkmSetFlag(false);
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
                    var12_3 = new String[6];
                    var10_4 = 0;
                    var9_5 = "\u00e3\u00bd+\u0007sTq\u00b1\u0085O\u00dc\u00c04\f\u0017\u00d3\u00b1\u00ad\u0017\u008a\u00c1[\u00b4\u0081\u0012\u0013i\u00e0\u00df\u0007C#\u0010\u0080TZ\u00c43'\u00b4\u00ae\u0017q\u009b\u00e2Z\f\u00ac\u009d e\u00c9\u00d1\b\u00da\u00d7@mU\u00f1\u00bd\u00f8\u008e:E\u00ba\r\u00ff\u00b0H\u00a0}\u000b\u00d0\u0089\u00caQ\u00a2\b\u0013\u00e6\u00ba\u0010\u00d6\u00dc\u0089A\u00ca\u0000[\u00be\u00ffx\u0084\u00d2y\u00d1\u008b\u00ca";
                    var11_6 = 99;
                    var8_7 = 32;
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
                        var12_3[var10_4++] = SettingsConfig.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00a9a~.[B\u0089-D\u00f0\u0019\u00cf\u0081\u0099\u00bc\u00b9\u0088\u00e1\u0096~%i\u0094F\u00fa\u00a6\u00f1\u0095\u00c3^\u00a5\u0001\u0018\u00ed\b\u00e7\u00be\u00d9=\u0000\u00e8o\u00f2\u00e5\u00b8\u00e0\u00b88w\u009fq\u00a8s\u0001\u00dc\u00a1,";
                        var11_6 = 57;
                        var8_7 = 32;
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
                        var12_3[var10_4++] = SettingsConfig.zkmDecodeUtf8(var13_9).intern();
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
            SettingsConfig.b = var12_3;
            SettingsConfig.c = new String[6];
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
        var4_12 = var0_10.doFinal(new byte[]{-26, 43, -40, 67, -30, 71, -89, 9});
        ** while (true)
        SettingsConfig.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        SettingsConfig.Companion = new SettingsConfig$Companion(null);
        var16_13 = new Lazy[]{null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)())};
        SettingsConfig.$childSerializers = var16_13;
    }

    public static void zkmSetFlag(boolean bl) {
        t = bl;
    }

    public static boolean zkmGetFlag() {
        return t;
    }

    public static boolean x() {
        return true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xA35;
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
                throw new RuntimeException("su/catlean/sd", exception);
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
            SettingsConfig.c[n2] = SettingsConfig.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingsConfig.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/sd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingsConfig.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

