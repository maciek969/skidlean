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
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.PlayerNameList$$serializer;
import su.catlean.util.PlayerNameList$Companion;
import su.catlean.zkm.ZkmKeys;

@Serializable
public final class PlayerNameList {
    @NotNull
    public static final PlayerNameList$Companion Companion;
    @NotNull
    private final List players;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    public PlayerNameList(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)PlayerNameList.zkmBootstrap("h", (int)25766, (long)(0x51D887002AE9D614L ^ l))));
        this.players = list;
    }

    @NotNull
    public final List getPlayers() {
        return this.players;
    }

    @NotNull
    public final List getPlayers2() {
        return this.players;
    }

    @NotNull
    public final PlayerNameList copy(@NotNull List list) {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ a;
        Intrinsics.checkNotNullParameter(list, (String)((Object)PlayerNameList.zkmBootstrap("h", (int)10498, (long)(0x4C408DCC3BC0A86L ^ l2))));
        return new PlayerNameList(list);
    }

    public static PlayerNameList copy$default(PlayerNameList playerNameList, List list, int n, Object object) {
        if ((n & 1) != 0) {
            list = playerNameList.players;
        }
        return playerNameList.copy(list);
    }

    @NotNull
    public String toString() {
        return "PlayersResponse(players=" + this.players + ")";
    }

    public int hashCode() {
        return ((Object)this.players).hashCode();
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlayerNameList)) {
            return false;
        }
        PlayerNameList playerNameList = (PlayerNameList)object;
        return Intrinsics.areEqual(this.players, playerNameList.players);
    }

    @JvmStatic
    public static final void write$Self(PlayerNameList playerNameList, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, (SerializationStrategy)lazyArray[0].getValue(), playerNameList.players);
    }

    public PlayerNameList(int n, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 1, PlayerNameList$$serializer.INSTANCE.getDescriptor());
        }
        this.players = list;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(StringSerializer.INSTANCE);
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(529438658226126684L, -7499037509600963616L, MethodHandles.lookup().lookupClass()).a(262750791792157L);
        e = new HashMap(13);
        long l = a ^ 0x68EBF6104112L;
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
        String string = "\u0018i\u0092.\u001f\u00f4\u00b7\u00bd\u00ab\u0015\u0083\u0018\u00a8\u001f\"h0\u008b\u0086T\u00ae\b\u00d5\u00a0\b1R\u00190\u009b@\u0081\u0091V*=\u00b4=vk\u00ea\u00fcvP\u009b\"n\u00ad\u00d6\u0087\u00c1\u000e2\u0092n\u00c3\u00b6\u0003\u00b6\u00bf\u00a5\u001f&\u008a\"\u0010\u00c2\u00d1`\u001f`d\u00a5\u00caf%\u0011\u00e6\u00b2\u00f9'\u0014";
        int n2 = 82;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = PlayerNameList.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                c = stringArray;
                d = new String[3];
                Companion = new PlayerNameList$Companion(null);
                Lazy[] lazyArray = new Lazy[]{LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, PlayerNameList::serializer)};
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4CC4;
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
                throw new RuntimeException("su/catlean/ip", exception);
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
            PlayerNameList.d[n2] = PlayerNameList.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerNameList.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ip" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerNameList.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

