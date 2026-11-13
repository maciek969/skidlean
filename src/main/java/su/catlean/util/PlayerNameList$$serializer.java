/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import su.catlean.util.PlayerNameList;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class PlayerNameList$$serializer
implements GeneratedSerializer {
    @NotNull
    public static final PlayerNameList$$serializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private PlayerNameList$$serializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull PlayerNameList playerNameList) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(playerNameList, "value");
        SerialDescriptor serialDescriptor = PlayerNameList$$serializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        PlayerNameList.write$Self(playerNameList, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final PlayerNameList deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = PlayerNameList$$serializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        List list = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = PlayerNameList.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            list = compositeDecoder.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), list);
            n = 1;
        } else {
            block4: while (bl) {
                int n2 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n2) {
                    case -1: {
                        bl = false;
                        continue block4;
                    }
                    case 0: {
                        list = compositeDecoder.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), list);
                        n |= 1;
                        continue block4;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new PlayerNameList(n, list, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = PlayerNameList.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{lazyArray[0].getValue()};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (PlayerNameList)object);
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
                PlayerNameList$$serializer.a = ZkmKeys.a(-2463412172762261032L, 3420083969172123640L, MethodHandles.lookup().lookupClass()).a(186358358752169L);
                var9 = PlayerNameList$$serializer.a ^ 99391639478728L;
                PlayerNameList$$serializer.d = new HashMap<K, V>(13);
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
                var7_3 = new String[5];
                var5_4 = 0;
                var4_5 = "E\u0007}\u00b4\u000e\u00f9Z@*\u00eb\u0004\u00a4\u00c5\u00bd\u00f3@W\u000b\u00b8\u00d2\u00de5Q\u0083\u00ac'a:\r\u00ffYb\u0010\u00e7G|\u0081\u0090\u00fcm\u00e8\u00dd:\u00d9Y\u009d\u00f3\u00e4\u00e8\u0010\u0007\u001e\u00eeF|\u00bf\u00e3\u009c\u0012\u007f\u00b39\u009bV\u00db\u001c";
                var6_6 = 66;
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
                    var7_3[var5_4++] = PlayerNameList$$serializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "5\u00ec6}\u00fe\u000fSq8\u00c7\u00be\u0004\u00abI\u00dc\u001a\u0010*\u00d8\u00a6\u00a3\u0013\u00a8\u00ea\u00d0\u0019\u00ea\u0018\u0082\u0084OY\u00fc";
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
                    var7_3[var5_4++] = PlayerNameList$$serializer.zkmDecodeUtf8(var8_9).intern();
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
        PlayerNameList$$serializer.b = var7_3;
        PlayerNameList$$serializer.c = new String[5];
        PlayerNameList$$serializer.INSTANCE = new PlayerNameList$$serializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.ip", PlayerNameList$$serializer.INSTANCE, 1);
        var11_10.addElement("players", false);
        PlayerNameList$$serializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6EC0;
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
                throw new RuntimeException("su/catlean/zc", exception);
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
            PlayerNameList$$serializer.c[n2] = PlayerNameList$$serializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerNameList$$serializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerNameList$$serializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

