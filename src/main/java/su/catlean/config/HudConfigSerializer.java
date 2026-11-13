/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.lang.invoke.CallSite;
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
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.HudConfig;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class HudConfigSerializer
implements GeneratedSerializer {
    @NotNull
    public static final HudConfigSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private HudConfigSerializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull HudConfig hudConfig) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(hudConfig, "value");
        SerialDescriptor serialDescriptor = HudConfigSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        HudConfig.write$Self(hudConfig, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final HudConfig deserialize(@NotNull Decoder decoder, int n, char c, int n2) {
        long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x6376C4BDAEFL;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 16 >>> 32);
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = HudConfigSerializer.serialDescriptor;
        boolean bl = true;
        int n5 = 0;
        String string = null;
        List list = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = HudConfig.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
            list = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), list);
            n5 = 3;
        } else {
            block5: while (bl) {
                int n6 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n6) {
                    case -1: {
                        bl = false;
                        continue block5;
                    }
                    case 0: {
                        string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
                        n5 |= 1;
                        continue block5;
                    }
                    case 1: {
                        list = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), list);
                        n5 |= 2;
                        continue block5;
                    }
                }
                throw new UnknownFieldException(n6);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new HudConfig(n5, string, (char)n3, list, null, n4);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = HudConfig.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{StringSerializer.INSTANCE, lazyArray[1].getValue()};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (HudConfig)object);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        long l = a ^ 0x7F9B0797105FL;
        long l2 = l ^ 0x6BE0CD53897CL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        return this.deserialize(decoder, n, (char)n2, n3);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                HudConfigSerializer.a = ZkmKeys.a(-7612119032143918667L, 4553587271965421570L, MethodHandles.lookup().lookupClass()).a(220559094809913L);
                var9 = HudConfigSerializer.a ^ 77984986781066L;
                HudConfigSerializer.d = new HashMap<K, V>(13);
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
                var7_3 = new String[6];
                var5_4 = 0;
                var4_5 = "\u00f4|4Pk|\u001c\u00f1c)\u0084\u00b9\u009a\u00b4\u00f9\u0004v\u00cb6\u008eK\u00a7\u00cd\u00a0\u00f5\u00e8\u00ec \b\u00f4\u00d5\u0081\u0010\u00c9Z\u001e\u00bd\u00d0Y\u00fc\u000f\u00e2\u009a3\u001e\u00f1\u00a4j\u00b1\u0010j\u00ec0RD#_\u00b0\u0006k\u0005\u0012}\u00de\u00f7\u00d6\u0010\u00eeZJ\u00ee\u0000|\u00f5E\u0096\u009b\u00b0\u0018\u00dak\u0091\n";
                var6_6 = 83;
                var3_7 = 32;
                var2_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = HudConfigSerializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "oI\u00b7\u0004[\u00a0{\u0087\u0098hf/\u00a6\u00a7K\u00ea\u0010H\u00f4\u00f5\u00ca\u00d7\u00c3-O\u00b9ght\u00ae\u00f0~\u009c";
                    var6_6 = 33;
                    var3_7 = 16;
                    var2_8 = -1;
lbl34:
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
lbl39:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = HudConfigSerializer.zkmDecodeUtf8(var8_9).intern();
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
lbl51:
                // 1 sources

                ** continue;
            }
        }
        HudConfigSerializer.b = var7_3;
        HudConfigSerializer.c = new String[6];
        HudConfigSerializer.INSTANCE = new HudConfigSerializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.jn", HudConfigSerializer.INSTANCE, 2);
        var11_10.addElement("id", false);
        var11_10.addElement("widgets", true);
        HudConfigSerializer.serialDescriptor = var11_10;
    }

    private static UnknownFieldException zkmRethrow(UnknownFieldException unknownFieldException) {
        return unknownFieldException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xD3A;
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
                throw new RuntimeException("su/catlean/o1", exception);
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
            HudConfigSerializer.c[n2] = HudConfigSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudConfigSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/o1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudConfigSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

