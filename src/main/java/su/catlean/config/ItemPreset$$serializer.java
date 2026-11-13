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
import su.catlean.config.ItemPreset;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class ItemPreset$$serializer
implements GeneratedSerializer {
    @NotNull
    public static final ItemPreset$$serializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private ItemPreset$$serializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull ItemPreset itemPreset) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(itemPreset, "value");
        SerialDescriptor serialDescriptor = ItemPreset$$serializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        ItemPreset.write$Self(itemPreset, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final ItemPreset deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = ItemPreset$$serializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        String string = null;
        Map map = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = ItemPreset.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
            map = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), map);
            n = 3;
        } else {
            block5: while (bl) {
                int n2 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n2) {
                    case -1: {
                        bl = false;
                        continue block5;
                    }
                    case 0: {
                        string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
                        n |= 1;
                        continue block5;
                    }
                    case 1: {
                        map = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), map);
                        n |= 2;
                        continue block5;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new ItemPreset(n, string, map, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = ItemPreset.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{StringSerializer.INSTANCE, lazyArray[1].getValue()};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (ItemPreset)object);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        return this.deserialize2(decoder);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                ItemPreset$$serializer.a = ZkmKeys.a(5201323990429553805L, -744405977627872805L, MethodHandles.lookup().lookupClass()).a(104200104134611L);
                var9 = ItemPreset$$serializer.a ^ 118272731210857L;
                ItemPreset$$serializer.d = new HashMap<K, V>(13);
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
                var4_5 = "\u00db\u00fcAnf\u00e4)\u00c5y\u0087j#\u00c5de\u00c3\u0018G\u00e5\u00ea\u00ca*\u00dfOF\u00f8}\u00e9m\u0097\u008d\u0097h\u00bf\nq)\u00d3\u00f2)\u00b8\u0010\u0094[\u00ea\u00b1-\u00851o\u00e2\u00df\u00e5\u00f8\u00f1\u00bd=\u0092\u0010\u00f2\u00b9\u00f4\u009b\u00cc\u0085&]s\u001f\u0095\u008e\u00e0\u00ef\u00fd:";
                var6_6 = 75;
                var3_7 = 16;
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
                    var7_3[var5_4++] = ItemPreset$$serializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u008eN\u00d7\u00b3\u0011y\u0004\u00df\u008e\u0007`r\u00aa\u00c9\u0016{\u0010\u00b4\u00d6e`=Q\u0007e\u0088Y\u0085\u00f3\u00c4\u00fc\u0012=";
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
                    var7_3[var5_4++] = ItemPreset$$serializer.zkmDecodeUtf8(var8_9).intern();
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
        ItemPreset$$serializer.b = var7_3;
        ItemPreset$$serializer.c = new String[6];
        ItemPreset$$serializer.INSTANCE = new ItemPreset$$serializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.mh", ItemPreset$$serializer.INSTANCE, 2);
        var11_10.addElement("id", false);
        var11_10.addElement("items", false);
        ItemPreset$$serializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1CC4;
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
                throw new RuntimeException("su/catlean/gl", exception);
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
            ItemPreset$$serializer.c[n2] = ItemPreset$$serializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemPreset$$serializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemPreset$$serializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

