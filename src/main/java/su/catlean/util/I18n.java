/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import su.catlean.CatLean;
import su.catlean.api.event.events.client.ScreenEvent;
import su.catlean.gofra.Flow;
import su.catlean.util.Globals;
import su.catlean.util.Language;
import su.catlean.zkm.ZkmKeys;

public final class I18n {
    @NotNull
    public static final I18n INSTANCE;
    @NotNull
    private static Language language;
    private static String D;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    private I18n() {
    }

    @Flow
    private final void onScreen(ScreenEvent screenEvent) {
        if (!Intrinsics.areEqual(Globals.getMc().options.language, language.getDisplayName())) {
            Language language;
            Object v0;
            block3: {
                Iterable iterable = Language.get$ENTRIES();
                for (Object t : iterable) {
                    Language language2 = (Language)((Object)t);
                    if (!Intrinsics.areEqual(language2.getDisplayName(), Globals.getMc().options.language)) continue;
                    v0 = t;
                    break block3;
                }
                v0 = null;
            }
            if ((language = (Language)v0) == null) {
                language = Language.EN;
            }
            I18n.language = language;
        }
    }

    @NotNull
    public final String translate(@NotNull String string, @NotNull Object[] objectArray) {
        Object object;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, "key");
        Intrinsics.checkNotNullParameter(objectArray, "args");
        String string2 = (String)language.getTranslations().get(string);
        if (string2 == null && (string2 = (String)Language.EN.getTranslations().get(string)) == null) {
            String string3 = net.minecraft.client.resource.language.I18n.translate(string, new Object[0]);
            string2 = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "get(...)");
        }
        String string4 = string2;
        try {
            Object[] objectArray2 = Arrays.copyOf(objectArray, objectArray.length);
            String string5 = String.format(string4, Arrays.copyOf(objectArray2, objectArray2.length));
            Intrinsics.checkNotNullExpressionValue(string5, "format(...)");
            object = string5;
        }
        catch (IllegalFormatException illegalFormatException) {
            object = (String)((Object)I18n.zkmBootstrap("i", (int)27473, (long)(0x5F34BA6375567CF3L ^ l))) + string4;
        }
        return object;
    }

    public final void loadLanguages() {
        I18n i18n = this;
        try {
            for (Language language : Language.get$ENTRIES()) {
                BufferedReader bufferedReader;
                Object object;
                Iterator iterator2;
                Object object2;
                block11: {
                    int n;
                    block10: {
                        ClassLoader classLoader = CatLean.class.getClassLoader();
                        String string = language.name().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(string, "toLowerCase(...)");
                        InputStream inputStream = classLoader.getResourceAsStream("assets/catlean/translations/" + string + ".ctlang");
                        Intrinsics.checkNotNull(inputStream);
                        object2 = Charsets.UTF_8;
                        object = new InputStreamReader((InputStream)((Object)iterator2), (Charset)object2);
                        n = (int)e;
                        if (!(object instanceof BufferedReader)) break block10;
                        Reader reader = object;
                        bufferedReader = (BufferedReader)reader;
                        break block11;
                    }
                    bufferedReader = new BufferedReader((Reader)object, n);
                }
                JsonElement jsonElement = Json.Default.parseToJsonElement(TextStreamsKt.readText(bufferedReader));
                if (!(jsonElement instanceof JsonObject)) continue;
                iterator2 = ((Map)((Object)jsonElement)).entrySet().iterator();
                while (iterator2.hasNext()) {
                    object2 = (Map.Entry)iterator2.next();
                    object = (String)object2.getKey();
                    JsonElement jsonElement2 = (JsonElement)object2.getValue();
                    language.getTranslations().put(object, JsonElementKt.getJsonPrimitive(jsonElement2).getContent());
                }
            }
            Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    I18n.a = ZkmKeys.a(-3900286695721691597L, -7215433332401329112L, MethodHandles.lookup().lookupClass()).a(226001175792180L);
                    var14 = I18n.a ^ 77971358918022L;
                    I18n.d = new HashMap<K, V>(13);
                    I18n.zkmSetFlag("m8Gwib");
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[8];
                    var10_4 = 0;
                    var9_5 = "\u00c1SC\u00056\u00a4\u00a2\u0001AU\u00e7\u000e\u00b8\u00c8\u00b86\u00f8\u00c5!\u0084\u00c48\u00a1s)\u00bd9\u00a7o\u00db|\u0093(\u00a4~+\u001e\u0099oP#\u000b3\u00bd\u00b0J\u00c2\\u\u0089\u00f5\u00ab\u00b0w\u0085\u00a1.\u00b8H5\u00ff\u00f4\u008b\u0087\u0088\u00e4\u00b5\u00a8i\u00c4\u0011Ok\u0018\u00dcW6\u0094.\u0005\u0019=\u00aa,\u0016\u0082\u00a7\u00d5:\u0086o\u00bbK\u00cc\u001c#R\u00e8\u0010U\u00df\t\u0095\u00c1\u00cbe\u00ee\u0088\u00a7\u00f5\u0099\u00ce7\u00d2R\u0010+1\u0086 }~\u009d\u0017\u00d4\u0091\u00cfL\u009f\u00b1.\u0014  \u00b8?\u00c8\u00dfIl\u00d4@\u000b\u00ad\u00dfjO\u00c7a\rW\u00cd+\u0010OgTt\u0003\u00cb\u0016Y\u0015\u008c\u00fe";
                    var11_6 = 165;
                    var8_7 = 32;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = I18n.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u0083\u0013\u00c4\u0000E\u00f4\b\u00dd+g\u00d47\b\u00a7Oc%\u00c0dQ\"\u0087\u00ad.J\u008fi\u00fe\u0015\u00d2\u008cy\u00ff\u0085}\u009b\u00db\u00fd?\n\u00ddp\u009d+\u00b1\t\u00a8\u00d7\u0010\u00d1m\u00ee\u008e\u00d9\"\u00cd\u0089\u00c8\u0002\u0091\u0093\u008a\u00e6\u0011\u00a6";
                        var11_6 = 65;
                        var8_7 = 48;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = I18n.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
                switch (v5) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl52:
                    // 1 sources

                    ** continue;
                }
            }
            I18n.b = var12_3;
            I18n.c = new String[8];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{61, 110, 81, 122, -108, -7, -31, -119});
        ** while (true)
        I18n.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        I18n.INSTANCE = new I18n();
        I18n.language = Language.EN;
    }

    public static void zkmSetFlag(String string) {
        D = string;
    }

    public static String zkmGetFlag() {
        return D;
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xF2;
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
                throw new RuntimeException("su/catlean/i4", exception);
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
            I18n.c[n2] = I18n.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = I18n.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/i4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(I18n.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

