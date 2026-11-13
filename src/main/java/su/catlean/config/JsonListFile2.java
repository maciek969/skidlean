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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.Saveable;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class JsonListFile2
implements Saveable {
    @NotNull
    private final Json json;
    @NotNull
    private final File T;
    public List data;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public JsonListFile2(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.T = new File(ConfigFiles.getCtlDir(), "homies.ctlon");
    }

    @NotNull
    public final List getData() {
        List list = this.data;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    public final void setData(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    @Override
    public void save() {
        Object object;
        JsonListFile2 jsonListFile2 = this;
        if (this.T.createNewFile()) {
            object = new ArrayList();
        } else {
            Object object2;
            JsonListFile2 jsonListFile22 = jsonListFile2;
            try {
                jsonListFile2 = jsonListFile22;
                object2 = this.json;
                String string = FilesKt.readText$default(this.T, null, 1, null);
                ((Json)object2).getSerializersModule();
                object2 = (List)((Json)object2).decodeFromString((DeserializationStrategy)new ArrayListSerializer<String>(StringSerializer.INSTANCE), string);
            }
            catch (Exception exception) {
                jsonListFile2 = jsonListFile22;
                Globals.getLogger().warn("Homies are not loaded from file for some reasons.");
                object2 = new ArrayList();
            }
            object = object2;
        }
        List list = object;
        jsonListFile2.setData(list);
    }

    @Override
    public void load(int n, int n2) {
        Json json = this.json;
        List list = this.getData();
        json.getSerializersModule();
        FilesKt.writeText$default(this.T, json.encodeToString((SerializationStrategy)new ArrayListSerializer<String>(StringSerializer.INSTANCE), list), null, 2, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                JsonListFile2.a = ZkmKeys.a(1130710695779698727L, -7325873853899627731L, MethodHandles.lookup().lookupClass()).a(146233375789374L);
                JsonListFile2.d = new HashMap<K, V>(13);
                var0 = JsonListFile2.a ^ 64613823944546L;
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
                var6_5 = "\u0013M\u00d6\u00b0\u009fp~\u00f4\u00cb\u00a9\u00e1A\u0000\u00e4\u0007\u00e2\u00cf\u00cd\u00f4\u0010Ei\u0090\u007f\u0010\u008d\u00ad\u00f0\u00cby\u0013\u00e0\u0001Tnl\u00c7\u00a7C!\u00fb\u0010^\u00ce\u00ff\u00baJQ v\u00a9g\u00a3(5Tvx";
                var8_6 = 58;
                var5_7 = 24;
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
                    var9_3[var7_4++] = JsonListFile2.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u00fe\u00a2\u00fb\u001dW\u0091\u00e7l\u0001\u0087\u00ee\u0012-2#\u009dX\u00c8\u00ee\u00f7\u0082ut\u00fd\u00a7a\u0093W\u008bc\u00fc\u00cc\u00a78\u0001B\u00c8\u008eirN\u00b4\u00e5\t\u00ba\u0011IG\"\u0011\u000fV\u001b\u00f8\u00aaD\u00ee\u000f;q\u00d3%\u00f9\u00e7\u0080c,N\u00d0\u009b\u00eei\u0004]\u00fcE\u0086\u00da(\u00d9\u00a9\u00f4\u0085S\u00cdo\u00ce\u009d\u009c\u008f\u0098\u00c7\u00cc?m,\u00b14\u00e6\u00cf\u00bdJI\u00e7\u00f2";
                    var8_6 = 105;
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
                    var9_3[var7_4++] = JsonListFile2.zkmDecodeUtf8(var10_9).intern();
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
        JsonListFile2.b = var9_3;
        JsonListFile2.c = new String[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4126;
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
                throw new RuntimeException("su/catlean/dk", exception);
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
            JsonListFile2.c[n2] = JsonListFile2.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = JsonListFile2.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(JsonListFile2.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

