/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

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
import org.jetbrains.annotations.NotNull;
import su.catlean.macro.Macros;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class MacrosSerializer
implements GeneratedSerializer {
    @NotNull
    public static final MacrosSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;

    private MacrosSerializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull Macros macros) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(macros, "value");
        SerialDescriptor serialDescriptor = MacrosSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        Macros.write$Self(macros, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final Macros deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = MacrosSerializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        List list = null;
        List list2 = null;
        List list3 = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = Macros.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            list = compositeDecoder.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), list);
            list2 = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), list2);
            list3 = compositeDecoder.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy)lazyArray[2].getValue(), list3);
            n = 7;
        } else {
            block6: while (bl) {
                int n2 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n2) {
                    case -1: {
                        bl = false;
                        continue block6;
                    }
                    case 0: {
                        list = compositeDecoder.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), list);
                        n |= 1;
                        continue block6;
                    }
                    case 1: {
                        list2 = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), list2);
                        n |= 2;
                        continue block6;
                    }
                    case 2: {
                        list3 = compositeDecoder.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy)lazyArray[2].getValue(), list3);
                        n |= 4;
                        continue block6;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new Macros(n, list, list2, list3, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = Macros.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{lazyArray[0].getValue(), lazyArray[1].getValue(), lazyArray[2].getValue()};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (Macros)object);
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
                MacrosSerializer.a = ZkmKeys.a(-2983098198207827791L, 8833115122815622980L, MethodHandles.lookup().lookupClass()).a(247830653810644L);
                var9 = MacrosSerializer.a ^ 96052334952183L;
                MacrosSerializer.e = new HashMap<K, V>(13);
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
                var7_3 = new String[7];
                var5_4 = 0;
                var4_5 = "G\u00aa\u00e3r\u00bd\u00fc\u0087\u00b4\u00ac\u00a0T\u001d3\u00ce\u00c3\u00a3\u00caS\u0097\u00f4\u00b1w\u00dei\u0010\u00b8\u00eb\u0007\u00fdR41\u00c9\u001b\u00bf\u0010\u0015<\u001b\u00ea\u0089 -\u0002\u008e=Z`\u0090\u00a8\u00a5\u009c\u00ac\u00c3V\u008d\u00e7\u00ae\f\u00d5T\u00e0+^\u00fb\u00c5A~y\u0001\u00ab\u00fcM\u00ac \u0094\u009c\u001d\u00d7\u00c8=\u0013\u00fdf|\u00a7\u001a\",\u009d\u00b5\u00af\u00e6d X\u0083\u00c7\u00bc\u0092\u009a\u00d8\u00e8g\u00f00\u001c\u0018\u0016\u00cf\u001e\n2\u00d8{l;\u0087\u00fa\u00b0E\u00f0\u00f5\u00c3\u00b80\u00e2@\u00aa\u00c9[S";
                var6_6 = 132;
                var3_7 = 24;
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
                    var7_3[var5_4++] = MacrosSerializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u001a\u00d2\"\u00af\u0081z\f\u00d2#\u00e8p1yW\u00acl\u0010\u00ac\f~\u00fa\u00cfT4\u009d\u00caA\u00b2R\u00e3P}#";
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
                    var7_3[var5_4++] = MacrosSerializer.zkmDecodeUtf8(var8_9).intern();
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
        MacrosSerializer.b = var7_3;
        MacrosSerializer.d = new String[7];
        MacrosSerializer.INSTANCE = new MacrosSerializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.m", MacrosSerializer.INSTANCE, 3);
        var11_10.addElement("settingMacros", true);
        var11_10.addElement("chatMacros", true);
        var11_10.addElement("itemMacros", true);
        MacrosSerializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x189B;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/ow", exception);
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
            MacrosSerializer.d[n2] = MacrosSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MacrosSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ow" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MacrosSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

