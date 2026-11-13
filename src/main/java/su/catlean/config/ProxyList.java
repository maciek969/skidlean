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
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
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
import su.catlean.config.ProxyList$Companion;
import su.catlean.config.ProxyListSerializer;
import su.catlean.module.Module;
import su.catlean.util.ProxySerializer;
import su.catlean.zkm.ZkmKeys;

@Serializable
public final class ProxyList {
    @NotNull
    public static final ProxyList$Companion Companion;
    @NotNull
    private final List proxies;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    public ProxyList(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "proxies");
        this.proxies = list;
    }

    @NotNull
    public final List getProxies() {
        return this.proxies;
    }

    @NotNull
    public final List getProxies2() {
        return this.proxies;
    }

    @NotNull
    public final ProxyList copy(@NotNull List list) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)ProxyList.zkmBootstrap("b", (int)14169, (long)(0x1D2EAA244C48A51AL ^ l))));
        return new ProxyList(list);
    }

    public static ProxyList copy$default(ProxyList proxyList, List list, int n, Object object) {
        if ((n & 1) != 0) {
            list = proxyList.proxies;
        }
        return proxyList.copy(list);
    }

    @NotNull
    public String toString() {
        return "ProxyConfig(proxies=" + this.proxies + ")";
    }

    public int hashCode() {
        return ((Object)this.proxies).hashCode();
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ProxyList)) {
            return false;
        }
        ProxyList proxyList = (ProxyList)object;
        return Intrinsics.areEqual(this.proxies, proxyList.proxies);
    }

    @JvmStatic
    public static final void write$Self(ProxyList proxyList, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, (SerializationStrategy)lazyArray[0].getValue(), proxyList.proxies);
    }

    public ProxyList(int n, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 1, ProxyListSerializer.INSTANCE.getDescriptor());
        }
        this.proxies = list;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(ProxySerializer.INSTANCE);
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        b = ZkmKeys.a(-6829102522648488175L, -5216747680212718762L, MethodHandles.lookup().lookupClass()).a(219569052430666L);
        e = new HashMap(13);
        long l = b ^ 0x503851078207L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[3];
        int n = 0;
        String string = "\u00b0\u00ea|\u00bbu\u00ed\u00a5cYc\u009d\u0093ky\u00bb&\u0002\u00cb\u00feL \u00ef\u00f5\u00b5\u00af\u0082\u0016K\u0096b\u008e\u0082\u00efc\u00f7\u0016\r}\u008f\u00ca\u0010\u00d8)\u00ecH\u00c7yf\u00d0Z\u0089@\u00df}^C%\u0018\u00c2\u00c7\u00a0\r\u000e\u001e*0\u000b\u00ed\u00bd\u0095\u00ef\u00d0f\u00aa\u00e6\u00dcxz\u00b5\u00cdJ\u00be";
        int n2 = 82;
        int n3 = 40;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ProxyList.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                c = stringArray;
                d = new String[3];
                Companion = new ProxyList$Companion(null);
                Lazy[] lazyArray = new Lazy[]{LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, ProxyList::serializer)};
                $childSerializers = lazyArray;
                return;
            }
            n3 = string.charAt(n4);
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64A8;
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
                throw new RuntimeException("su/catlean/po", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            ProxyList.d[n2] = ProxyList.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProxyList.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/po" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProxyList.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

