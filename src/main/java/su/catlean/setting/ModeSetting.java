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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.setting.Setting;
import su.catlean.setting.SettingParent;
import su.catlean.zkm.ZkmKeys;

public final class ModeSetting
extends Setting {
    private static int I;
    private static final long c;
    private static final String[] d;
    private static final String[] g;
    private static final Map i;
    private static final long l;

    public ModeSetting(@NotNull String string, @NotNull Enum enum_, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(enum_, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        super(string, enum_, null, settingParent, function0, 4, null);
    }

    public /* synthetic */ ModeSetting(String string, Enum enum_, SettingParent settingParent, Function0 function0, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & (int)l) != 0) {
            function0 = ModeSetting::_init_$lambda$0;
        }
        this(string, enum_, settingParent, function0);
    }

    @Override
    public void loadFromString(@NotNull String string) {
        Enum enum_;
        Object v2;
        ModeSetting modeSetting;
        block2: {
            int[] nArray = new int[]{};
            Intrinsics.checkNotNullParameter(string, "setting");
            int[] nArray2 = nArray;
            ?[] objArray = this.getDefaultValue().getClass().getEnumConstants();
            Intrinsics.checkNotNullExpressionValue(objArray, "getEnumConstants(...)");
            ?[] objArray2 = objArray;
            modeSetting = this;
            for (Object obj : objArray2) {
                Enum enum_2 = (Enum)obj;
                if (!Intrinsics.areEqual(enum_2.name(), string)) continue;
                v2 = obj;
                break block2;
            }
            v2 = null;
        }
        if ((enum_ = (Enum)v2) == null) {
            enum_ = (Enum)this.getDefaultValue();
        }
        modeSetting.setValue(enum_);
    }

    @NotNull
    public String toString() {
        return ((Enum)this.getValue()).name();
    }

    private static final boolean _init_$lambda$0() {
        return true;
    }

    public static void zkmSetFlag(int n) {
        I = n;
    }

    public static int int_zkmGetFlag() {
        return I;
    }

    public static int Y() {
        return 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ModeSetting.c = ZkmKeys.a(-3206134455745378555L, -5262085740875104781L, MethodHandles.lookup().lookupClass()).a(23155495641509L);
                    var14 = ModeSetting.c ^ 24468608152650L;
                    ModeSetting.i = new HashMap<K, V>(13);
                    ModeSetting.zkmSetFlag(69);
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
                    var9_5 = "X\u00f9\u00e8qnt3G\u00a0\u00f2E\u00e1rf\u0095\u0085\u0010xe\u00c8\u00ea\u00b6T\u008d\u00932\u009f\u00f5\u008f\u00b6\u000b\u00a8t(\u00e5 G\u0086\u00d4\u00f5N\u00a3\u00bb\u00d6\u0002@\u0095c\u000b\u00e0\u0097t\u00aaV\u0083\u0003\bu1\u00a6\byh\u00f4\u00b1\u008e+\u00d5\u00c4\u00da\u00c2Z%?";
                    var11_6 = 74;
                    var8_7 = 16;
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
                        var12_3[var10_4++] = ModeSetting.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "[\u0000\u00ed\u00d6(\u00ac\u00be\ns3\u009a;\u00126\u001e\u001d\u0018\u0097\u00bcm;\u0086\u0007\u00c4\u0093\u008a\u00e9Ce\u00a8WC\u0012\u00e0\u00e0\u0080T\u00c1_\u001e\u00e1";
                        var11_6 = 41;
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
                        var12_3[var10_4++] = ModeSetting.zkmDecodeUtf8(var13_9).intern();
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
            ModeSetting.d = var12_3;
            ModeSetting.g = new String[5];
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
        var4_12 = var0_10.doFinal(new byte[]{27, 40, 92, -44, -15, 20, -87, 12});
        ** while (true)
        ModeSetting.l = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x69E8;
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
                throw new RuntimeException("su/catlean/cw", exception);
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
            ModeSetting.g[n2] = ModeSetting.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ModeSetting.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/cw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ModeSetting.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

