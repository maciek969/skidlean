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
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.account.Account;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class AccountSerializer
implements GeneratedSerializer {
    @NotNull
    public static final AccountSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private AccountSerializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull Account account) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(account, "value");
        SerialDescriptor serialDescriptor = AccountSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        Account.write$Self(account, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final Account deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = AccountSerializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        String string = null;
        String string2 = null;
        String string3 = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        if (compositeDecoder.decodeSequentially()) {
            string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
            string2 = compositeDecoder.decodeStringElement(serialDescriptor, 1);
            string3 = compositeDecoder.decodeStringElement(serialDescriptor, 2);
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
                        string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
                        n |= 1;
                        continue block6;
                    }
                    case 1: {
                        string2 = compositeDecoder.decodeStringElement(serialDescriptor, 1);
                        n |= 2;
                        continue block6;
                    }
                    case 2: {
                        string3 = compositeDecoder.decodeStringElement(serialDescriptor, 2);
                        n |= 4;
                        continue block6;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new Account(n, string, string2, string3, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArray = new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (Account)object);
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
                AccountSerializer.a = ZkmKeys.a(5860507367026198542L, 1860373817185736680L, MethodHandles.lookup().lookupClass()).a(240731730162171L);
                var9 = AccountSerializer.a ^ 116306720776939L;
                AccountSerializer.d = new HashMap<K, V>(13);
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
                var4_5 = "\u0094\u00ef\u009d\u00b7*\u00a0\u001b\r\u00bd=\u000e\u0014Aw\u00fa\u00d4\u0010\u0086A5\u00be\u009fK\u00e6\u00f3\u00f6m\u00de_jl\u0094\u00d7\u0010\u0015Hn\u0097#\u00c2\u00e2\u00ef+\u00c3h\u00a9\u00b3\u00eaO5 \u00bfy\u0084IAz\u00c0;\u00c9\u0085\u00e9\u00a3\u00ae\u00c3\u008f\u00ce\u0097\u00b7\u009f\u00e0}I\u00fc\u0086]RHl\u001a\u00ef\u0087\u00ef\u0010\u000b\"\u00a2\u00c2\u00ce\"\u00b7\u000b`dg%\u00ae\u0089'\u00ea";
                var6_6 = 100;
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
                    var7_3[var5_4++] = AccountSerializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00f5\u00b4\u00c8.\u0004\u0084n\u00a8\u00e4\u0017\u00de\u00c3\u008c\u0093\"\u00b2 \u00fa#{\u0090\u00f3\u00a3\u00baS\u000e\u00b2\u00b7*89\u00f2\u0013\u001e\u00d2k'\u000f\\F\u00e6\u00e7-$\u00cb\u00c2\u00dam\u0001";
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
                    var7_3[var5_4++] = AccountSerializer.zkmDecodeUtf8(var8_9).intern();
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
        AccountSerializer.b = var7_3;
        AccountSerializer.c = new String[7];
        AccountSerializer.INSTANCE = new AccountSerializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.f0", AccountSerializer.INSTANCE, 3);
        var11_10.addElement("id", true);
        var11_10.addElement("username", true);
        var11_10.addElement("avatar", true);
        AccountSerializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6674;
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
                throw new RuntimeException("su/catlean/fn", exception);
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
            AccountSerializer.c[n2] = AccountSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AccountSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fn" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AccountSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

