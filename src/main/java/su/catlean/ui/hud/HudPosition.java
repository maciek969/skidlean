/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.setting.Bind;
import su.catlean.ui.hud.HudPosition$Companion;
import su.catlean.ui.hud.HudPositionSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class HudPosition {
    @NotNull
    public static final HudPosition$Companion Companion;
    private float x;
    private float y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public HudPosition(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public HudPosition(float f, float f2, DefaultConstructorMarker defaultConstructorMarker) {
        f = 0.5f;
        f2 = 0.5f;
        this(f, f2);
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final float getX2() {
        return this.x;
    }

    public final float getY2() {
        return this.y;
    }

    @NotNull
    public final HudPosition copy(float f, float f2) {
        return new HudPosition(f, f2);
    }

    public static HudPosition copy$default(HudPosition hudPosition, float f, float f2, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-4094112085556462181L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = hudPosition.x;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            f2 = hudPosition.y;
        }
        return hudPosition.copy(f, f2);
    }

    @NotNull
    public String toString() {
        return "ScreenPosition(x=" + this.x + ", y=" + this.y + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.x);
        n = n * (int)e + Float.hashCode(this.y);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof HudPosition)) {
            return false;
        }
        HudPosition hudPosition = (HudPosition)object;
        if (Float.compare(this.x, hudPosition.x) != 0) {
            return false;
        }
        return Float.compare(this.y, hudPosition.y) == 0;
    }

    @JvmStatic
    public static final void write$Self(HudPosition hudPosition, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block1: {
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) ? true : Float.compare(hudPosition.x, 0.5f) != 0) {
                compositeEncoder.encodeFloatElement(serialDescriptor, 0, hudPosition.x);
            }
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) ? true : Float.compare(hudPosition.y, 0.5f) != 0)) break block1;
            compositeEncoder.encodeFloatElement(serialDescriptor, 1, hudPosition.y);
        }
    }

    public HudPosition(int n, float f, short s, float f2, int n2, SerializationConstructorMarker serializationConstructorMarker) {
        block5: {
            Module[] moduleArray = Bind.zkmGetFlag();
            if ((0 & n) != 0) {
                PluginExceptionsKt.throwMissingFieldException(n, 0, HudPositionSerializer.INSTANCE.getDescriptor());
            }
            Module[] moduleArray2 = moduleArray;
            if (s >= 0) {
                if ((n & 1) == 0) {
                    this.x = 0.5f;
                    if (n2 >= 0) {
                        moduleArray2 = moduleArray;
                    }
                } else {
                    this.x = f;
                }
            }
            if (s < 0) break block5;
            this.y = (n & 2) == 0 ? 0.5f : f2;
        }
    }

    public HudPosition() {
        this(0.0f, 0.0f, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(7129025222538082336L, 4935707233595164357L, MethodHandles.lookup().lookupClass()).a(101694394552199L);
        d = new HashMap(13);
        long l = a ^ 0x4780548EE061L;
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\u0081OR\u00b3Qm\u00a4(\t\u00cb_+\u0001u\u00d0\u00fa(o\u009a\u0013t/>yZJK\u009d\u0085}\u00cc@\u00af>L\u007f\u00c5\u0092\u00f3\"\u009b'\u00d0\u00ce%\u0085vI\u0016R\u00dc\u00ce\u0081\u0080\u009a]\u00ae";
        int n2 = "\u0081OR\u00b3Qm\u00a4(\t\u00cb_+\u0001u\u00d0\u00fa(o\u009a\u0013t/>yZJK\u009d\u0085}\u00cc@\u00af>L\u007f\u00c5\u0092\u00f3\"\u009b'\u00d0\u00ce%\u0085vI\u0016R\u00dc\u00ce\u0081\u0080\u009a]\u00ae".length();
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = HudPosition.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[2];
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n6 = 1;
        while (true) {
            if (n6 >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                long l2 = 5779866279921614930L;
                byte[] byArray6 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
                e = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                Companion = new HudPosition$Companion(null);
                return;
            }
            byArray5 = byArray5;
            byArray5[n6] = (byte)(l << n6 * 8 >>> 56);
            ++n6;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x427D;
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
                throw new RuntimeException("su/catlean/d_", exception);
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
            HudPosition.c[n2] = HudPosition.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudPosition.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/d_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudPosition.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

