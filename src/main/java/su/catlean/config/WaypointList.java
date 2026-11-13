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
import su.catlean.config.WaypointList$Companion;
import su.catlean.config.WaypointListSerializer;
import su.catlean.module.Module;
import su.catlean.util.Waypoint$$serializer;
import su.catlean.zkm.ZkmKeys;

@Serializable
public final class WaypointList {
    @NotNull
    public static final WaypointList$Companion Companion;
    @NotNull
    private final List wayPoints;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public WaypointList(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "wayPoints");
        this.wayPoints = list;
    }

    @NotNull
    public final List getWayPoints() {
        return this.wayPoints;
    }

    @NotNull
    public final List getWayPoints2() {
        return this.wayPoints;
    }

    @NotNull
    public final WaypointList copy(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)WaypointList.zkmBootstrap("z", (int)31704, (long)(0x5B9253790ABC4360L ^ l))));
        return new WaypointList(list);
    }

    public static WaypointList copy$default(WaypointList waypointList, List list, int n, Object object) {
        if ((n & 1) != 0) {
            list = waypointList.wayPoints;
        }
        return waypointList.copy(list);
    }

    @NotNull
    public String toString() {
        return "WayPointConfig(wayPoints=" + this.wayPoints + ")";
    }

    public int hashCode() {
        return ((Object)this.wayPoints).hashCode();
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof WaypointList)) {
            return false;
        }
        WaypointList waypointList = (WaypointList)object;
        return Intrinsics.areEqual(this.wayPoints, waypointList.wayPoints);
    }

    @JvmStatic
    public static final void write$Self(WaypointList waypointList, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, (SerializationStrategy)lazyArray[0].getValue(), waypointList.wayPoints);
    }

    public WaypointList(int n, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 1, WaypointListSerializer.INSTANCE.getDescriptor());
        }
        this.wayPoints = list;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(Waypoint$$serializer.INSTANCE);
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(3437041384660267994L, -7646689094990467597L, MethodHandles.lookup().lookupClass()).a(35891514876969L);
        d = new HashMap(13);
        long l = a ^ 0x609A1ED8C028L;
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
        String string = "\u0089}\u00fb\u0097\u00d6Uj\u00d5\u0093\u00be\u00b7cm\u00a6\u00890`\u00ac\u00e3g\u00af\u00c8$mi\u00aa_>\u009b\u0014\u00b1\u00bc\u00c3^\u0002$\u00a7@\u0087\u0096\u009a4k5\u00cc\u009bE\u00dezi\u0005\u00e9w\u000f\u00c4) >\u00b1XY<3\u0002\u001axmh\u00a7a\u0017\u0016\u0090s\u00c0@\u0007\u0095>\u009c-\u00a9\u000eB\u0018N\u00b8\u009c)\u0018w\\\u00c6\b\u00cb\u0084G\u00b2[\u00dd\u00e2\u001f\u00e1\u0080pQC\u00aa\u008ei\u00c3n\u00b3\u00c4";
        int n2 = 114;
        int n3 = 56;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = WaypointList.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                Companion = new WaypointList$Companion(null);
                Lazy[] lazyArray = new Lazy[]{LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, WaypointList::serializer)};
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x61A0;
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
                throw new RuntimeException("su/catlean/js", exception);
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
            WaypointList.c[n2] = WaypointList.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WaypointList.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/js" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WaypointList.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

