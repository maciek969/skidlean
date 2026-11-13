/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.account;

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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.account.Account;
import su.catlean.account.AccountSerializer;
import su.catlean.account.AuthResult;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class AuthResultSerializer
implements GeneratedSerializer {
    @NotNull
    public static final AuthResultSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private AuthResultSerializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull AuthResult authResult) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(authResult, "value");
        SerialDescriptor serialDescriptor = AuthResultSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        AuthResult.write$Self(authResult, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final AuthResult deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = AuthResultSerializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        Account account = null;
        Integer n2 = null;
        String string = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        if (compositeDecoder.decodeSequentially()) {
            account = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 0, AccountSerializer.INSTANCE, account);
            n2 = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, n2);
            string = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, string);
            n = 7;
        } else {
            block6: while (bl) {
                int n3 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n3) {
                    case -1: {
                        bl = false;
                        continue block6;
                    }
                    case 0: {
                        account = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 0, AccountSerializer.INSTANCE, account);
                        n |= 1;
                        continue block6;
                    }
                    case 1: {
                        n2 = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, n2);
                        n |= 2;
                        continue block6;
                    }
                    case 2: {
                        string = compositeDecoder.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, string);
                        n |= 4;
                        continue block6;
                    }
                }
                throw new UnknownFieldException(n3);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new AuthResult(n, account, n2, string, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArray = new KSerializer[]{BuiltinSerializersKt.getNullable(AccountSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (AuthResult)object);
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
                AuthResultSerializer.a = ZkmKeys.a(9073740647604714359L, -3812309502717393823L, MethodHandles.lookup().lookupClass()).a(281433894883512L);
                var9 = AuthResultSerializer.a ^ 90153939314485L;
                AuthResultSerializer.d = new HashMap<K, V>(13);
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
                var4_5 = "\u00cd\u009c\u008b:@r\u009cfW\u00b6\u008a\u008f*B\u00bd\u00ed\u0010\u00e2\u0012x\u00e2/\u00d64\u00ab\u00daS`A^\u0088b\u0091\u0010A{7*\u00b7[\u00ca\u00df\u00e7\u00f7\u00e5:\u00ea\u00fe\u00c1Y\u0010\u0016\u00c4\n\u00a4B[\u0012\u00f7\u0092\u008d\u00d9>i\u00e38\u00f0\u0010\u008a\u00ed\u001d\u00da\u009a\u00dd!\\\u00d1\u0080\f\u00fb\u00e7\u00b2\u001b\u00ae";
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
                    var7_3[var5_4++] = AuthResultSerializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "E\u00acoq\u0000W\u0016<\u00c8\"\u00ec\u00a0\u00bb\u00ca\u00e08\u000e\u00fb(H<\u00ff\u000171c28\u001c\u00f4z\u00b8\u0010\u00fc\u00fc\u001c\u0089\u00884\u0001\u001a\u00bd\u001a+\u0011?\u00e2\u000f0";
                    var6_6 = 49;
                    var3_7 = 32;
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
                    var7_3[var5_4++] = AuthResultSerializer.zkmDecodeUtf8(var8_9).intern();
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
        AuthResultSerializer.b = var7_3;
        AuthResultSerializer.c = new String[7];
        AuthResultSerializer.INSTANCE = new AuthResultSerializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.y9", AuthResultSerializer.INSTANCE, 3);
        var11_10.addElement("user", false);
        var11_10.addElement("code", false);
        var11_10.addElement("message", false);
        AuthResultSerializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7FB7;
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
                throw new RuntimeException("su/catlean/zb", exception);
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
            AuthResultSerializer.c[n2] = AuthResultSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AuthResultSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AuthResultSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

