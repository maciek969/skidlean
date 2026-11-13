/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

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
import su.catlean.setting.Bind$Companion;
import su.catlean.setting.BindSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class Bind {
    @NotNull
    public static final Bind$Companion Companion;
    private int key;
    private boolean hold;
    private boolean mouse;
    private static Module[] D;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public Bind(int n, boolean bl, boolean bl2) {
        this.key = n;
        this.hold = bl;
        this.mouse = bl2;
    }

    public Bind(int n, boolean bl, boolean bl2, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 1) != 0) {
            n = -1;
        }
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            bl2 = false;
        }
        this(n, bl, bl2);
    }

    public final int getKey() {
        return this.key;
    }

    public final void setKey(int n) {
        this.key = n;
    }

    public final boolean isHold() {
        return this.hold;
    }

    public final void setHold(boolean bl) {
        this.hold = bl;
    }

    public final boolean isMouse() {
        return this.mouse;
    }

    public final void setMouse(boolean bl) {
        this.mouse = bl;
    }

    public final int getKey2() {
        return this.key;
    }

    public final boolean isHold2() {
        return this.hold;
    }

    public final boolean isMouse2() {
        return this.mouse;
    }

    @NotNull
    public final Bind copy(int n, boolean bl, boolean bl2) {
        return new Bind(n, bl, bl2);
    }

    /*
     * WARNING - void declaration
     */
    public static Bind copy$default(Bind bind, int n, long l, boolean bl, boolean bl2, int n2, Object object) {
        boolean bl3;
        block9: {
            boolean bl4;
            block8: {
                void var6_6;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3309840979125999451L, (long)l);
                bl4 = var6_6 & 1;
                if (callSite == null) {
                    if (bl4) {
                        n = bind.key;
                    }
                    bl4 = var6_6 & 2;
                }
                CallSite callSite2 = callSite;
                if (l > 0L) {
                    if (callSite2 == null) {
                        if (bl4) {
                            bl = bind.hold;
                        }
                        bl4 = var6_6 & 4;
                    }
                    callSite2 = callSite;
                }
                if (callSite2 != null) break block8;
                if (!bl4) break block9;
                bl4 = bind.mouse;
            }
            bl3 = bl4;
        }
        return bind.copy(n, bl, bl3);
    }

    @NotNull
    public String toString() {
        boolean bl = this.mouse;
        boolean bl2 = this.hold;
        int n = this.key;
        return "Bind(key=" + n + ", hold=" + bl2 + ", mouse=" + bl + ")";
    }

    public int hashCode() {
        int n = Integer.hashCode(this.key);
        n = n * 31 + Boolean.hashCode(this.hold);
        n = n * 31 + Boolean.hashCode(this.mouse);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Bind)) {
            return false;
        }
        Bind bind = (Bind)object;
        if (this.key != bind.key) {
            return false;
        }
        if (this.hold != bind.hold) {
            return false;
        }
        return this.mouse == bind.mouse;
    }

    /*
     * Unable to fully structure code
     */
    @JvmStatic
    public static final void write$Self(Bind var0, CompositeEncoder var1_1, int var2_2, byte var3_3, SerialDescriptor var4_4, int var5_5) {
        block8: {
            var8_6 = Bind.zkmGetFlag();
            v0 = var1_1.shouldEncodeElementDefault(var4_4, 0) != false ? true : var0.key != -1;
            if (var3_3 >= 0) {
                if (v0) {
                    var1_1.encodeIntElement(var4_4, 0, var0.key);
                }
                v0 = var1_1.shouldEncodeElementDefault(var4_4, 1);
            }
            v1 = var8_6;
            if (var2_2 <= 0) ** GOTO lbl15
            if (v0) {
                v2 = true;
            } else {
                v0 = var0.hold;
                v1 = var8_6;
lbl15:
                // 2 sources

                v2 = v0 != false;
            }
            if (var3_3 > 0) {
                if (v2) {
                    var1_1.encodeBooleanElement(var4_4, 1, var0.hold);
                }
                v2 = var1_1.shouldEncodeElementDefault(var4_4, 2);
            }
            v3 = var8_6;
            if (var5_5 <= 0) ** GOTO lbl28
            if (v2) {
                v4 = true;
            } else {
                v2 = var0.mouse;
                v3 = var8_6;
lbl28:
                // 2 sources

                v4 = v2 != false;
            }
            if (!v4) break block8;
            var1_1.encodeBooleanElement(var4_4, 2, var0.mouse);
        }
    }

    public Bind(int n, int n2, boolean bl, boolean bl2, SerializationConstructorMarker serializationConstructorMarker, byte by) {
        if ((0 & n) != 0) {
            PluginExceptionsKt.throwMissingFieldException(n, 0, BindSerializer.INSTANCE.getDescriptor());
        }
        this.key = (n & 1) == 0 ? -1 : n2;
        if (by < 0) {
            if ((n & 2) == 0) {
                this.hold = false;
                if (by <= 0) {
                    // empty if block
                }
            } else {
                this.hold = bl;
            }
        }
        this.mouse = (n & 4) == 0 ? false : bl2;
    }

    public Bind() {
        long l = 0L;
        l = a ^ l;
        this(0, false, false, (int)Bind.zkmBootstrap2("z", (int)22453, (long)(0x2339079DF43658C9L ^ l)), null);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(1958681444245326318L, 7049126197005911037L, MethodHandles.lookup().lookupClass()).a(4636725917268L);
        long l = a ^ 0x6F94F24B371CL;
        d = new HashMap(13);
        Bind.zkmSetFlag(null);
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
        String string = "\u00d07\u008b\u0092\u001d\u00e8\u00edeXL\u00d2Y\u00ef\u0015\u00de\u00d8\u00d5\u0001P\u00d8\u0092.<j\u00b5A\u009a\u00bf\u00140jZ \u00bc\u00fd\u00b06e=\u00c9ib\u0095\u0014\u000f\u001d\u00bd\u008f\u00bdru4\u00b9@\u00ac\u00f9u\u00ea\u00b0\t7\u00e54\u0018\u0090\u0010\u009d\u0004\u00fd\u00f4\u00ed\u00c4\u0019h\u0018~lN\u00dc\u00bb\u0091f";
        int n2 = "\u00d07\u008b\u0092\u001d\u00e8\u00edeXL\u00d2Y\u00ef\u0015\u00de\u00d8\u00d5\u0001P\u00d8\u0092.<j\u00b5A\u009a\u00bf\u00140jZ \u00bc\u00fd\u00b06e=\u00c9ib\u0095\u0014\u000f\u001d\u00bd\u008f\u00bdru4\u00b9@\u00ac\u00f9u\u00ea\u00b0\t7\u00e54\u0018\u0090\u0010\u009d\u0004\u00fd\u00f4\u00ed\u00c4\u0019h\u0018~lN\u00dc\u00bb\u0091f".length();
        int n3 = 32;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = Bind.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[3];
        g = new HashMap(13);
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        long[] lArray = new long[3];
        int n6 = 0;
        String string2 = " ~|\u00f6K\u009fD\u00f3\u0098e\u00e7\u00aa\u009b\u009dBn\u0000[:`\u00a9\u0095C\u00f4";
        int n7 = " ~|\u00f6K\u009fD\u00f3\u0098e\u00e7\u00aa\u009b\u009dBn\u0000[:`\u00a9\u0095C\u00f4".length();
        int n8 = 0;
        do {
            byte[] byArray6 = string2.substring(n8, n8 += 8).getBytes("ISO-8859-1");
            int n9 = n6++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n9] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n8 < n7);
        e = lArray;
        f = new Integer[3];
        Companion = new Bind$Companion(null);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        D = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return D;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5031;
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
                throw new RuntimeException("su/catlean/lj", exception);
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
            Bind.c[n2] = Bind.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Bind.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/lj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x49B5;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/lj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Bind.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Bind.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/lj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Bind.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(Bind.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

