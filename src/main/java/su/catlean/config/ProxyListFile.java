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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ProxyList;
import su.catlean.config.Saveable;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ProxyListFile
implements Saveable {
    @NotNull
    private final Json json;
    @NotNull
    private final File g;
    public ProxyList data;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public ProxyListFile(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.g = new File(ConfigFiles.getCtlDir(), "proxy.ctlon");
    }

    @NotNull
    public final ProxyList getData() {
        ProxyList proxyList = this.data;
        if (proxyList != null) {
            return proxyList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    public final void setData(@NotNull ProxyList proxyList) {
        Intrinsics.checkNotNullParameter(proxyList, "<set-?>");
        this.data = proxyList;
    }

    @Override
    public void save() {
        Object object;
        ProxyListFile proxyListFile = this;
        if (this.g.createNewFile()) {
            object = new ProxyList(new ArrayList());
        } else {
            Object object2;
            ProxyListFile proxyListFile2 = proxyListFile;
            try {
                proxyListFile = proxyListFile2;
                object2 = this.json;
                String string = FilesKt.readText$default(this.g, null, 1, null);
                ((Json)object2).getSerializersModule();
                object2 = (ProxyList)((Json)object2).decodeFromString(ProxyList.Companion.serializer(), string);
            }
            catch (Exception exception) {
                proxyListFile = proxyListFile2;
                Globals.getLogger().warn("Proxies are not loaded from file for some reasons.");
                object2 = new ProxyList(new ArrayList());
            }
            object = object2;
        }
        ProxyList proxyList = object;
        proxyListFile.setData(proxyList);
    }

    @Override
    public void load(int n, int n2) {
        Json json = this.json;
        ProxyList proxyList = this.getData();
        json.getSerializersModule();
        FilesKt.writeText$default(this.g, json.encodeToString(ProxyList.Companion.serializer(), proxyList), null, 2, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                ProxyListFile.a = ZkmKeys.a(-7573330238437348837L, -4944645022501052395L, MethodHandles.lookup().lookupClass()).a(10006981304051L);
                ProxyListFile.d = new HashMap<K, V>(13);
                var0 = ProxyListFile.a ^ 127484116305327L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[5];
                var7_4 = 0;
                var6_5 = "\u00f5.n\u00e4\u00ca\u00eb\u00aeQ\u00e91\u00f7?\u00c7\u00f7[j`\u0085h\u00f5\u008b)\u00e5\u00a3\u00cf\u00ab>4\u00f7\u0090>k\u00c3b\u0000\u00dc\u008ai\u008a\u00ec\"}\u00c7\u00c9\u00ad\u00f0\u00cd\u00b0\u001b\u0092d\u0083nI\u00b2\u0082\u00ba5N~F\u00d1\u00fc-\u000eE\u00b8\u00a9T\u0087~\u001c\u0097kM\u00c2\u00ff\u00b0\u0018c\u00c7\u00b9-\u00ba\u00a7\u0097\u0081O\u00a5\u00bf\u0099,\u00c4\u008f+8\u0003\u00b5\u0085U\u00d3\u000f\u00fc\u00e2\u00c1D\u000f\u00b2\u00cfY\u00eeb\u0090 \u00a00\u0018\u00c0;\u00dd\u00b0\u00fe\u008a-\u001e\u0084\u0090rA\u00e9b\u009c\u00b4?\u00a3\u00b0Y\u00ff)\u0091\u00be\u00ec\u00d3vX\u008e";
                var8_6 = 146;
                var5_7 = 16;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = ProxyListFile.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u0098z}\u00bd\u00ca,qn\u00c8Z\u00ea9\u0005\u008cf\u00f6\u0010\u00cd\u0088?\u00a9\u00808[@\u00ee~O\fj\u00f9\u00f9I";
                    var8_6 = 33;
                    var5_7 = 16;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = ProxyListFile.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        ProxyListFile.b = var9_3;
        ProxyListFile.c = new String[5];
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x500D;
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
                throw new RuntimeException("su/catlean/g4", exception);
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
            ProxyListFile.c[n2] = ProxyListFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProxyListFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/g4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProxyListFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

