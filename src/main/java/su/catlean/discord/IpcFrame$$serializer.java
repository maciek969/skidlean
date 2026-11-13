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
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import su.catlean.account.AuthResult;
import su.catlean.account.AuthResultSerializer;
import su.catlean.discord.IpcCommand;
import su.catlean.discord.IpcEventType;
import su.catlean.discord.IpcFrame;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class IpcFrame$$serializer
implements GeneratedSerializer {
    @NotNull
    public static final IpcFrame$$serializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private IpcFrame$$serializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull IpcFrame ipcFrame) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(ipcFrame, "value");
        SerialDescriptor serialDescriptor = IpcFrame$$serializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        IpcFrame.write$Self(ipcFrame, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final IpcFrame deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = IpcFrame$$serializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        IpcEventType ipcEventType = null;
        IpcCommand ipcCommand = null;
        AuthResult authResult = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = IpcFrame.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            ipcEventType = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), ipcEventType);
            ipcCommand = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), ipcCommand);
            authResult = compositeDecoder.decodeSerializableElement(serialDescriptor, 2, AuthResultSerializer.INSTANCE, authResult);
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
                        ipcEventType = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), ipcEventType);
                        n |= 1;
                        continue block6;
                    }
                    case 1: {
                        ipcCommand = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), ipcCommand);
                        n |= 2;
                        continue block6;
                    }
                    case 2: {
                        authResult = compositeDecoder.decodeSerializableElement(serialDescriptor, 2, AuthResultSerializer.INSTANCE, authResult);
                        n |= 4;
                        continue block6;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new IpcFrame(n, ipcEventType, ipcCommand, authResult, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = IpcFrame.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{BuiltinSerializersKt.getNullable((KSerializer)lazyArray[0].getValue()), BuiltinSerializersKt.getNullable((KSerializer)lazyArray[1].getValue()), AuthResultSerializer.INSTANCE};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (IpcFrame)object);
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
                IpcFrame$$serializer.a = ZkmKeys.a(-4667064007167709103L, -441685530707106909L, MethodHandles.lookup().lookupClass()).a(259281605923610L);
                var9 = IpcFrame$$serializer.a ^ 26602687243173L;
                IpcFrame$$serializer.d = new HashMap<K, V>(13);
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
                var4_5 = "\u00d2\u0003\u00d16\u00d9\u00fcz\u00d4\u00d4s\u00b7\u00cdz\u00dfi\u0003\u0010,\u00f0aK\u0088\u001e\u0019\u00efA\u00bd\u008f\b\u00f0f\u0098F\u00102f\u001bC\u000fV\rV\r\u00b2>[\u00ea\u00c2\n\u00b6\u0010\u00fb\u009c\u00ef#\u00f5\u00df\u001aY\u00fe\u00e5\u0085\u00f7\u009c\u00e1`n\u0010\u0004#\u00f6\u0088\u0085\u00c2\u00db\u00e8\u00fd*\u00a4\r^g\u001d\u0007";
                var6_6 = 84;
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
                    var7_3[var5_4++] = IpcFrame$$serializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00afm\u0092A\u00d9\u0088\u00c1\u00d4hh\u001bW6\u00a14) \u00ee\u00f2@\u001d\u001e\u00bc\u0083\u00da\u00ffd\u001a\u00e9^x\u0088\u00e0\u00e5`\u00c5\u00ed\u00fd\u00ea\u00cb\u00c4\u00a7\"\u009a&n\u0086`%";
                    var6_6 = 49;
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
                    var7_3[var5_4++] = IpcFrame$$serializer.zkmDecodeUtf8(var8_9).intern();
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
        IpcFrame$$serializer.b = var7_3;
        IpcFrame$$serializer.c = new String[7];
        IpcFrame$$serializer.INSTANCE = new IpcFrame$$serializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.f4", IpcFrame$$serializer.INSTANCE, 3);
        var11_10.addElement("evt", false);
        var11_10.addElement("cmd", false);
        var11_10.addElement("data", false);
        IpcFrame$$serializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4283;
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
                throw new RuntimeException("su/catlean/xr", exception);
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
            IpcFrame$$serializer.c[n2] = IpcFrame$$serializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = IpcFrame$$serializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(IpcFrame$$serializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

