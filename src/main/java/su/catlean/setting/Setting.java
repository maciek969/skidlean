/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.setting.Setting$Companion;
import su.catlean.setting.SettingParent;
import su.catlean.zkm.ZkmKeys;

public abstract class Setting {
    @NotNull
    public static final Setting$Companion Companion;
    @NotNull
    private final String id;
    private final Object defaultValue;
    private Object value;
    @Nullable
    private SettingParent group;
    @NotNull
    private Function0 visible;
    @NotNull
    private final String T;
    @NotNull
    private static final Json json;
    private static int b;
    private static final long a;
    private static final String[] f;
    private static final String[] h;
    private static final Map j;
    private static final long[] p;
    private static final Integer[] q;
    private static final Map r;

    public Setting(@NotNull String string, Object object, Object object2, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        StringBuilder stringBuilder;
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(function0, "visible");
        this.id = string;
        this.defaultValue = object;
        this.value = object2;
        this.group = settingParent;
        this.visible = function0;
        StringBuilder stringBuilder2 = stringBuilder = new StringBuilder();
        Setting setting2 = this;
        Object object3 = new String[]{"-"};
        Object object4 = object3 = (Iterable)StringsKt.split$default((CharSequence)this.id, (String[])object3, false, 0, 6, null);
        Collection collection = new ArrayList();
        Iterator iterator2 = object4.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            String string2 = (String)t;
            if (!(((CharSequence)string2).length() > 0)) continue;
            collection.add(t);
        }
        object3 = (List)collection;
        object4 = object3.iterator();
        while (object4.hasNext()) {
            collection = object4.next();
            String string3 = (String)((Object)collection);
            stringBuilder2.append(" ");
            String string4 = string3.substring(0, 1);
            Intrinsics.checkNotNullExpressionValue(string4, "substring(...)");
            String string5 = string4.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string5, "toUpperCase(...)");
            stringBuilder2.append(string5);
            String string6 = string3.substring(1);
            Intrinsics.checkNotNullExpressionValue(string6, "substring(...)");
            String string7 = string6.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string7, "toLowerCase(...)");
            stringBuilder2.append(string7);
        }
        setting2.T = stringBuilder.toString();
    }

    public Setting(String string, Object object, Object object2, SettingParent settingParent, Function0 function0, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 4) != 0) {
            object2 = object;
        }
        if ((n & 8) != 0) {
            settingParent = null;
        }
        if ((n & 0x10) != 0) {
            function0 = Setting::_init_$lambda$0;
        }
        this(string, object, object2, settingParent, function0);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final Object getDefaultValue() {
        return this.defaultValue;
    }

    public final Object getValue() {
        return this.value;
    }

    public final void setValue(Object object) {
        this.value = object;
    }

    @Nullable
    public final SettingParent getGroup() {
        return this.group;
    }

    public final void setGroup(@Nullable SettingParent settingParent) {
        this.group = settingParent;
    }

    @NotNull
    public final Function0 getVisible() {
        return this.visible;
    }

    public final void setVisible(@NotNull Function0 function0) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)Setting.zkmBootstrap("i", (int)6253, (long)(0x4CFD107EA866C74BL ^ l))));
        this.visible = function0;
    }

    @NotNull
    public final String Q() {
        return this.T;
    }

    public final Object getValue(@Nullable Object object, @NotNull KProperty kProperty) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        return this.value;
    }

    public final void setValue(@Nullable Object object, @NotNull KProperty kProperty, Object object2) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        this.value = object2;
    }

    public final void reset() {
        this.value = this.defaultValue;
    }

    public abstract void loadFromString(@NotNull String var1);

    private static final boolean _init_$lambda$0() {
        return true;
    }

    private static final Unit _clinit_$lambda$0(JsonBuilder jsonBuilder) {
        Intrinsics.checkNotNullParameter(jsonBuilder, "$this$Json");
        jsonBuilder.setIgnoreUnknownKeys(true);
        jsonBuilder.setEncodeDefaults(true);
        return Unit.INSTANCE;
    }

    public static final Json getJson() {
        return json;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        Setting.a = ZkmKeys.a(-80565656895778137L, 4596118210747371218L, MethodHandles.lookup().lookupClass()).a(202152887717538L);
                        var20 = Setting.a ^ 82522546182656L;
                        Setting.j = new HashMap<K, V>(13);
                        Setting.zkmSetFlag(0);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "n\u0004\u0015j\u00e0\u001f\u0010I\u00e8N\u0016\u00f7hd-\u0089\u00a7\u00f3\u00bf=\u00c5\u00b3p\u00a9\u0007\u00ba\u00a0\u00af\u0017\u00c9\u00b1\u0011(p\u00a1 \u00d09[\u008c\u007f\u009a\u00b8\u00dbb\u00be\u00b1\u00bb\u00c2\u00d7\u00e1\u0000\u00e3\u00f9\u0005..D\u00a4\u00c5\u00aa\u0090zY\u0005\u00de{\u0014\u00d8\u00f8\u00c7\u00f8<\u0018\u001c;\u00ee\u00beM\r:\u0099\u00f1\u0018\u0006\u00e9\u008e\u00ff\u00ads*\u0017v)q,\u00d2\u0003 {\b,\u00fa\u00ee\u00c4\u008eI!\u0001\u00ef\u00fcf\u0098\u001f\ra\u0007\u00a1'R:\u008ejE\u00c3y\u0014\u00b2\u00cb@\u00a6\u0010\u00d4^2\u00eaB\u009f\u00d5\u00a9\u00bd\u0088\u000f\u00a2\u0094\u000f\u0016\u009a\u0010\u00bb\u008db\u000b\u00f0\u000f=\u00ff\u00cdP\u0080\u00cd<6\u00d2\u00c8(}.\u00bcu\u00aevo\u0013\u00b6\u0018\u00bd\u00c8Z0;\u00f7\u0091\u009bP\u00da\u00121ag\u00be\\\u00c5\u00fe\u0002K\u009d\u00a3\u00b0\u0013\u00f1\u00e6\u00e1\u009d\u00f8M \u009f\u0013*Mv\u00e0+/\u00e7\u00ff'd\u00c4\u00acY\u00b4^\u00c1\f\u0097\u00ce=\u009d&\u0099\u00ca B\u0018\u00c3\u00c2\u0089";
                        var17_6 = 239;
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Setting.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "B\"\u00ad\u00a5\u00da\u00d1\u0003\u00c2\u00e2GK\u00cd\u00c6\u00c5\u0092\"\u0018\u00b9\u00a8\u00e4\u00cf\u008f\u008c\u00d4\u00a8K<#\u00af\u0098\u000f\u00d9\u00bbx\u00f6\u0094\u00a75\u00ee\u00e4\"";
                            var17_6 = 41;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Setting.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                Setting.f = var18_3;
                Setting.h = new String[10];
                Setting.r = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u00c8\u009c6\u009b\u00df\u0080\u00cf<\u00ca@\u00f8~\u0090\u007fc\u00cf\u0085\u0087\u00faI\u0092\u00bavQ";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl74:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        Setting.p = var6_12;
        Setting.q = new Integer[3];
        Setting.Companion = new Setting$Companion(null);
        Setting.json = JsonKt.Json$default(null, (Function1<JsonBuilder, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(kotlinx.serialization.json.JsonBuilder ), (Lkotlinx/serialization/json/JsonBuilder;)Lkotlin/Unit;)(), 1, null);
    }

    public static void zkmSetFlag(int n) {
        b = n;
    }

    public static int int_zkmGetFlag() {
        return b;
    }

    public static int zkmIntConstant() {
        return 62;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7327;
        if (h[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])j.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/a1", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = f[n2].getBytes("ISO-8859-1");
            Setting.h[n2] = Setting.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Setting.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/a1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3FE0;
        if (q[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = p[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])r.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    r.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/a1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Setting.q[n2] = n3;
        }
        return q[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Setting.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/a1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Setting.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Setting.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

