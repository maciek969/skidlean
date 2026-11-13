/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.Saveable;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public abstract class JsonConfigFile
implements Saveable {
    @NotNull
    private final Json json;
    private static boolean Y;
    private static final long b;
    private static final String[] e;
    private static final String[] f;
    private static final Map g;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    public JsonConfigFile(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
    }

    @NotNull
    protected final Json getJson() {
        return this.json;
    }

    @NotNull
    public abstract File U();

    /*
     * Enabled aggressive block sorting
     */
    protected final /* synthetic */ List q(long l) {
        List list;
        block18: {
            File file;
            File[] fileArray;
            File[] fileArray2;
            CallSite callSite;
            block21: {
                File[] fileArray3;
                File file2;
                block20: {
                    boolean bl;
                    block19: {
                        l = b ^ l;
                        callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-9076263344814228711L, (long)l);
                        bl = this.U().exists();
                        if (callSite != false) break block19;
                        if (!bl) return new ArrayList();
                        file2 = this.U();
                        if (callSite != false) break block20;
                        bl = file2.isDirectory();
                    }
                    if (!bl) {
                        return new ArrayList();
                    }
                    file2 = this.U();
                }
                fileArray2 = fileArray3 = file2.listFiles();
                if (callSite != false) break block21;
                if (fileArray2 == null) break block18;
                fileArray2 = fileArray3;
            }
            File[] fileArray4 = fileArray = fileArray2;
            Iterable<File> iterable = new ArrayList();
            int n = 0;
            int n2 = fileArray4.length;
            block0: while (n < n2) {
                int n3;
                File file3;
                file = file3 = fileArray4[n];
                Intrinsics.checkNotNull(file);
                CallSite callSite2 = callSite;
                do {
                    if (l >= 0L) {
                        if (callSite2 != false) break block18;
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == false) {
                            if (Intrinsics.areEqual(FilesKt.getExtension(file), JsonConfigFile.zkmBootstrap("e", (int)21478, (long)(0x208B93EBF70CB9A8L ^ l)))) {
                                iterable.add((File)file3);
                            }
                            ++n;
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 == false) continue block0;
                    n3 = 3;
                } while (l <= 0L);
                ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[n3], (long)-9054510724529944470L, (long)l);
                break;
            }
            List list2 = (List)iterable;
            iterable = list2;
            Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, (int)JsonConfigFile.zkmBootstrap2("w", (int)27312, (long)(0xB1BC3DCF8C48F80L ^ l))));
            block2: for (Object e : iterable) {
                file = (File)e;
                Collection collection2 = collection;
                Json json = this.json;
                Intrinsics.checkNotNull(file);
                String string = FilesKt.readText$default(file, null, 1, null);
                SerializersModule serializersModule = json.getSerializersModule();
                Intrinsics.reifiedOperationMarker((int)JsonConfigFile.zkmBootstrap2("w", (int)32172, (long)(0x7954615356D5189DL ^ l)), "T");
                MagicApiIntrinsics.voidMagicApiCall(JsonConfigFile.zkmBootstrap("e", (int)29540, (long)(0x1A5260D6989A992BL ^ l)));
                collection2.add(json.decodeFromString((DeserializationStrategy)SerializersKt.serializer(serializersModule, null), string));
                do {
                    CallSite callSite3 = callSite;
                    if (l > 0L) {
                        if (callSite3 != false) break block18;
                        callSite3 = callSite;
                    }
                    if (callSite3 == false) continue block2;
                } while (l < 0L);
            }
            list = list2 = CollectionsKt.toMutableList((List)collection);
            if (callSite != false) return list;
            if (list != null) {
                list = list2;
                return list;
            }
        }
        list = new ArrayList();
        return list;
    }

    public static void zkmSetFlag(boolean bl) {
        Y = bl;
    }

    public static boolean boolean_zkmGetFlag() {
        return Y;
    }

    public static boolean d() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        b = ZkmKeys.a(5738047638491747592L, 7579577316647829245L, MethodHandles.lookup().lookupClass()).a(147959669946714L);
        long l = b ^ 0x19DADA510604L;
        g = new HashMap(13);
        JsonConfigFile.zkmSetFlag(false);
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
        String string = "S\u00c8\u0006\u00af\u00beD[\u00ec\u00bd\u0095\u00bd\u00fa*\u00del\u00c4P\u00f9\u00f46%2/\u00ea\u00ad\u00eew\u00fd\u00d5\u00b1\n\u008d,\b\n\u00f6\u00f7\u0019\u00a2\u0081\u0000\u0018,\u00e5\u00b0\u00c0\u000e4\u00b1aX;\u00e3l\u00dfM\u00cePQ\u00e8\u00bd\u009c7\u007f@\u009f\u0097\u00cd(\u00cb\u009f\u00cf\u00f0\u00af?\u00b33#xf|\t\u00a97\u00ed\u00ab#\u00e6\u0010\u00e9l\u007f\u00a8\u0011\u008a?t\u0010\u008cw\u00c8\u0091\u00c0\u008e\u008a\u0002\u0003\u00fd\u00dbe\u00d7#}\u001d";
        int n2 = 114;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = JsonConfigFile.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        e = stringArray;
        f = new String[3];
        JsonConfigFile.l = new HashMap(13);
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
        long[] lArray = new long[2];
        int n6 = 0;
        String string2 = "\u00fdW\u0085\u0014\u00d8o0+\u008eW\u00dd\u008e\u0016/\u00b6\u0086";
        int n7 = 16;
        int n8 = 0;
        do {
            byte[] byArray6 = string2.substring(n8, n8 += 8).getBytes("ISO-8859-1");
            int n9 = n6++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n9] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n8 < n7);
        j = lArray;
        k = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x693E;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/o3", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            JsonConfigFile.f[n2] = JsonConfigFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = JsonConfigFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/o3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6640;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])JsonConfigFile.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    JsonConfigFile.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/o3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            JsonConfigFile.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = JsonConfigFile.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/o3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(JsonConfigFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(JsonConfigFile.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

