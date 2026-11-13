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
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ModuleConfig;
import su.catlean.config.Saveable;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.Macros;
import su.catlean.macro.SettingMacro;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class JsonListFile
implements Saveable {
    @NotNull
    private final Json json;
    @NotNull
    private final File P;
    public List data;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public JsonListFile(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.P = new File(ConfigFiles.getCtlDir(), "macro.ctlon");
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
        List list;
        JsonListFile jsonListFile = this;
        if (this.P.createNewFile()) {
            list = new ArrayList();
        } else {
            Object object;
            JsonListFile jsonListFile2 = jsonListFile;
            try {
                Object object2 = this.json;
                Object object3 = FilesKt.readText$default(this.P, null, 1, null);
                ((Json)object2).getSerializersModule();
                object = (Macros)((Json)object2).decodeFromString(Macros.Companion.serializer(), (String)object3);
                object3 = object2 = (List)new ArrayList();
                JsonListFile jsonListFile3 = jsonListFile2;
                object3.addAll(((Macros)object).getChatMacros());
                object3.addAll(((Macros)object).getItemMacros());
                object3.addAll(((Macros)object).getSettingMacros());
                jsonListFile = jsonListFile3;
                object = object2;
            }
            catch (Exception exception) {
                jsonListFile = jsonListFile2;
                Globals.getLogger().warn("Macros are not loaded from file for some reasons.");
                object = new ArrayList();
            }
            list = object;
        }
        List list2 = list;
        jsonListFile.setData(list2);
    }

    @Override
    public void load(int n, int n2) {
        Json json = this.json;
        Iterable iterable = this.getData();
        File file = this.P;
        Iterable iterable2 = iterable;
        Iterable iterable3 = new ArrayList();
        for (Object object : iterable2) {
            if (!(object instanceof ChatMacro)) continue;
            iterable3.add(object);
        }
        Object object = TypeIntrinsics.asMutableList((List)iterable3);
        Iterable iterable4 = this.getData();
        iterable3 = iterable4;
        Iterable iterable5 = new ArrayList();
        for (Object object2 : iterable3) {
            if (!(object2 instanceof ModuleConfig)) continue;
            iterable5.add(object2);
        }
        List list = TypeIntrinsics.asMutableList((List)iterable5);
        iterable2 = this.getData();
        iterable5 = iterable2;
        Collection collection = new ArrayList();
        for (Object e : iterable5) {
            if (!(e instanceof SettingMacro)) continue;
            collection.add(e);
        }
        iterable = TypeIntrinsics.asMutableList((List)collection);
        object = new Macros((List)iterable, (List)object, list);
        json.getSerializersModule();
        FilesKt.writeText$default(file, json.encodeToString(Macros.Companion.serializer(), object), null, 2, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                JsonListFile.a = ZkmKeys.a(-2350861240970411063L, -2344192374533439674L, MethodHandles.lookup().lookupClass()).a(56282340420796L);
                JsonListFile.d = new HashMap<K, V>(13);
                var0 = JsonListFile.a ^ 68737517295995L;
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
                var6_5 = "y \u001aR\u0016\u000f\u00f5\u0096\u00be\u0089\u0015\u008di\u00c6\u00b1\u00ce\u0010\bsO<x\u0012xg\u00d9\u000b<\u00fc\u008d\u007f6eX\u0088\u00cf\u00a0\u009fR\u0094\u0087\u0088\\\u0016[\u00fd\u0004p\u0014\u0004,\u008cW\u00e2\u001dG8\u00fc`\u0014\u008e\u0097\u008bD\u00ab\u00f7\u0089\u00de\u0084\u0010\u00f3[~ .\u00b4\u0086`1\u00f9\u009bI\u00f4<\u00af\u0090,\u00d1q\u0099\u0082\u0093NJ\u0083\u0002\u00f9 \u00f9 \u00b6@\u00d3\u00c0\u00dc\u0082n\u00e1\u0087v\u00fd\u0093\u0089Lz\u00eca\u0098\u00d8\u00d8\b\u00b1";
                var8_6 = 122;
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
                    var9_3[var7_4++] = JsonListFile.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u00e8\u00f0Rv\u009a\u0005 \u0096WC\u0098\u008e\u009a(\u00b4\u00ee \u00f9\u009ePwE\u0087`\u00f0\u00d0\u00aeM\u00cc!\u000f,\u000b\u0010OrK\u00f8Ec;\u00f7\u0001\u00cf\u0094\u0017\u00e4\u00d1\u00e7";
                    var8_6 = 49;
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
                    var9_3[var7_4++] = JsonListFile.zkmDecodeUtf8(var10_9).intern();
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
        JsonListFile.b = var9_3;
        JsonListFile.c = new String[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x24D7;
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
                throw new RuntimeException("su/catlean/p1", exception);
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
            JsonListFile.c[n2] = JsonListFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = JsonListFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/p1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(JsonListFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

