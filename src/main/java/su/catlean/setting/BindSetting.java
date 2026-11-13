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
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.Bind;
import su.catlean.setting.Setting;
import su.catlean.zkm.ZkmKeys;

public final class BindSetting
extends Setting {
    @NotNull
    private final Json n;
    private static int R;
    private static final long c;
    private static final String[] d;
    private static final String[] g;
    private static final Map i;
    private static final long l;

    public BindSetting(@NotNull String string, @NotNull Bind bind, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(bind, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        super(string, bind, null, null, function0, (int)l, null);
        this.n = JsonKt.Json$default(null, BindSetting::_init_$lambda$0, 1, null);
    }

    public /* synthetic */ BindSetting(String string, Bind bind, Function0 function0, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 4) != 0) {
            function0 = BindSetting::_init_$lambda$0;
        }
        this(string, bind, function0);
    }

    @Override
    public void loadFromString(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "setting");
        Json json = this.n;
        String string2 = string;
        json.getSerializersModule();
        this.setValue(json.decodeFromString(Bind.Companion.serializer(), string2));
    }

    @NotNull
    public String toString() {
        Json json = this.n;
        Object object = this.getValue();
        json.getSerializersModule();
        return json.encodeToString(Bind.Companion.serializer(), object);
    }

    private static final boolean _init_$lambda$0() {
        return true;
    }

    private static final Unit _init_$lambda$0(JsonBuilder jsonBuilder) {
        Intrinsics.checkNotNullParameter(jsonBuilder, "$this$Json");
        jsonBuilder.setIgnoreUnknownKeys(true);
        jsonBuilder.setEncodeDefaults(true);
        return Unit.INSTANCE;
    }

    public static void zkmSetFlag(int n) {
        R = n;
    }

    public static int int_zkmGetFlag() {
        return R;
    }

    public static int s() {
        return 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    BindSetting.c = ZkmKeys.a(-4777379769918061755L, -5755119447319120370L, MethodHandles.lookup().lookupClass()).a(77601524827902L);
                    var14 = BindSetting.c ^ 108143107266488L;
                    BindSetting.i = new HashMap<K, V>(13);
                    BindSetting.zkmSetFlag(48);
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
                    var12_3 = new String[5];
                    var10_4 = 0;
                    var9_5 = "L\u00bf\r\u00da\u0096\u00c1+\u007fF?f\u00c4\u00e5\u008d\r\u0095Y\u0019[xf>\u0097s\u00ddD\u00f3\u0003\u0092n\u00b7a\u0010\u00a7\u00c0\u00907H\u0099\u00ed\u00e6>\u00a7=\u0000>\u00d4\u00d4y\u0010\u00f8\u00f0;\u00b1\u00a0\u00ee\u0099\u00ce\u00ca\u008bw\u0080\u0006\u0018F2";
                    var11_6 = 66;
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
                        var12_3[var10_4++] = BindSetting.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "5\u0092h.;_A\u0011\u00e4\u00b7]s\u0007J\u00b2{ itn\u0012\u00f52\u00b0\u00b9\u00de\u00db\u00e3\u0090F\u001a\u0097\u00fe\u00e9\u00b5\f;\u0015A\u00fe\u008d\u0014\u001cW\u0091\u0099\u00f4\u00b6\u00d8";
                        var11_6 = 49;
                        var8_7 = 16;
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
                        var12_3[var10_4++] = BindSetting.zkmDecodeUtf8(var13_9).intern();
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
            BindSetting.d = var12_3;
            BindSetting.g = new String[5];
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
        var4_12 = var0_10.doFinal(new byte[]{97, -3, -17, -110, 11, -47, -92, -44});
        ** while (true)
        BindSetting.l = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5365;
        if (g[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/av", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            BindSetting.g[n2] = BindSetting.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BindSetting.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/av" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BindSetting.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

