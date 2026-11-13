/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.UILinesMode;
import su.catlean.module.misc.UIModuleMode;
import su.catlean.module.misc.UIModuleTextMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.zkm.ZkmKeys;

public final class UI
extends Module {
    @NotNull
    public static final UI INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting moduleMode$delegate;
    @NotNull
    private static final ModeSetting moduleText$delegate;
    @NotNull
    private static final ModeSetting linesMode$delegate;
    private static int Y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private UI() {
        super("UI", Categories.getMisc(), null, null);
    }

    @NotNull
    public final UIModuleMode getModuleMode() {
        return (UIModuleMode)((Object)moduleMode$delegate.getValue(this, delegatedProperties[0]));
    }

    @NotNull
    public final UIModuleTextMode getModuleText() {
        return (UIModuleTextMode)((Object)moduleText$delegate.getValue(this, delegatedProperties[1]));
    }

    @NotNull
    public final UILinesMode getLinesMode() {
        return (UILinesMode)((Object)linesMode$delegate.getValue(this, delegatedProperties[2]));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        block13: {
                            UI.a = ZkmKeys.a(-3082537083315868688L, -5029862328146011263L, MethodHandles.lookup().lookupClass()).a(186088794932831L);
                            var20 = UI.a ^ 65757135936049L;
                            UI.d = new HashMap<K, V>(13);
                            UI.zkmSetFlag(0);
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
                            var15_5 = "\u00a3IQ##\u008an`g\u0084>ZV\u00ce\u00b0\u0095\u00b0SB\u0011}&?\u000f \u00cfq\u00de\u00f9\u00fd-)\u00e2\u00daULbUK\u00c6\u0019\u00fa\u00eb%\u008a\u00d8\u00a3\u00b1\u0004\u009b/5\u00ff\u0019\u00d0m\r q\u00b4v\u00bf]\u00d5{\u001b\u00e7\u00b5\u00a1\u00e7\u00e3Y\u008a\u00f5\u0003_\u00a6\u0007\u00ffj\u001c\u009b%@\u0081\u00dc\u00cb)u\u00ef L\u00cf4\u00e0\u00cc\u0007.\u00d9\u00f5\u0084\u008a\u001fi\u00f3\n\u009c\u0083UG\u0012V\u001fQ\u00b8\u00a0\u00a6A\u00bd\u00e5\u0014\u00c9}\u0010\u00b5\u00f9\u0017Rq\u00c4\fg]\u0007\u0003\u008a9a\u0094\u00b00\u00ea&\u0093\u001b4\u00cdAO\u00f4X\u00df\u007f\u00dc\u00f8\u00fa_@\u0091\u0015\u00abzLl\u000e\u00c4v\u00e2\u00fb\u00ae)\u0000\u00c4\u00f3a\u00a5\u00bd:oJ\u00a9\u0097q\u00b3\u0006\u00e7\u0007\u00fd\u0081\u0080.\b\u00f1\u001eF\u00a1X\u0082\u0018j\u00b0\u0016\u00ca\u00e6\u000e\u00b0vM\u0080J\u0005\u00e9\u00eaC\u001a\\\u00bf\u00a2y\u00edSG\u00a9C?\u00f0\u00cd2\u00b9\u00c5\u00f2N\u007f\u0096.\u00e5\u00da=q_\u00fa\u00a1\u00e9\u00da\u0082[]\u00f5\t\u00e2\u00995\u008e|\u00b0\u0007\u0011\u0089$}_=\u00b5\u001f)\b\u009b\u00a5\u00f5\u00a9\u00a8\u009eSn\u00c9\u00a8\u00d4\u0089\u00e3\u00ceU\u0017\u008c\u0005\u0083\u000e\u008f\u00d9r\u00a8\u00d7\u0086\u00a1\u0017\u00d8\u00b2\u00aa\u0018\u00cb\u0005\u000b\u00b9,\u00c0\u0097\u001c\u00dc\u00f7\u00d1\u000b\u00bfi2\u001b\u0016\u00830\u00b6 b\u00b1\u00ce\u00f4\u00c9\u00d2 $P\u0017r(\u0006\u00ef\u00af:i\u000f\u0003?=\u001a\u00d5\u00d6\u00a4\u0088\u0000\u009cy^8\u0014";
                            var17_6 = 351;
                            var14_7 = 24;
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
                                var18_3[var16_4++] = UI.zkmDecodeUtf8(var19_9).intern();
                                if ((var13_8 += var14_7) < var17_6) {
                                    var14_7 = var15_5.charAt(var13_8);
                                    ** continue;
                                }
                                var15_5 = "\u0089\b2?\u00b9\u00b0\u0019|B\u00d3\u0006\u008f?\u000e\u00ee\u0097\u0086O\u00ef\u0081[\u00d6\u00fc\u0097{\"\u00de\u00cf\u00cf \u00cev\u00d0\u00d7)J\u001a\u00d3\r\u00e9\u00802\u00fe\u0081]\u00ee\u00888\u0081\u00ffL\u00d4\u00a2\u0004\u0085(\u0018\u0086\u000b\u00c1\\\u00db[\u00b1\u00ce\u0011\u00a1\u00adU\u00cfA\u00bd\u00de\u000b\u00c4\u00f3p1\u0018\u00dd\u008d";
                                var17_6 = 81;
                                var14_7 = 56;
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
                                var18_3[var16_4++] = UI.zkmDecodeUtf8(var19_9).intern();
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
                    UI.b = var18_3;
                    UI.c = new String[10];
                    var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var20 >>> 56);
                    for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                        v9 = v9;
                        v9[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                    }
                    var1_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var0_12 = new long[3];
                    var4_13 = 0;
                    var5_14 = "!\u00f3Z\u008a\u0019\u00dc\u00d4o\u00d8@|\u00ba\u00e4\u00bb\u0091\u009f\u00e3\u00c2\u00ba\u001b\u00c1\u00d1\u00b1\u00fb";
                    var6_15 = 24;
                    var3_16 = 0;
                    while (true) {
                        break block15;
                        break;
                    }
lbl73:
                    // 1 sources

                    while (true) {
                        var0_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                        if (var3_16 < var6_15) ** continue;
                        break block16;
                        break;
                    }
                }
                var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                v10 = var4_13++;
                var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
                ** while (true)
            }
            var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(UI.class, "moduleMode", "getModuleMode()Lsu/catlean/bl;", 0)), Reflection.property1(new PropertyReference1Impl(UI.class, "moduleText", "getModuleText()Lsu/catlean/bc;", 0)), Reflection.property1(new PropertyReference1Impl(UI.class, "linesMode", "getLinesMode()Lsu/catlean/client/features/modules/utility/misc/ui/UI$Lines;", 0))};
            UI.delegatedProperties = var28_20;
            UI.INSTANCE = new UI();
            UI.moduleMode$delegate = SettingFactory.modeSetting$default((SettingContainer)UI.INSTANCE, "module-mode", (Enum)UIModuleMode.DEFAULT, null, null, (int)var0_12[0], null);
            UI.moduleText$delegate = SettingFactory.modeSetting$default((SettingContainer)UI.INSTANCE, "module-text", (Enum)UIModuleTextMode.CENTER, null, null, (int)var0_12[2], null);
            UI.linesMode$delegate = SettingFactory.modeSetting$default((SettingContainer)UI.INSTANCE, "lines-mode", (Enum)UILinesMode.NEW, null, null, (int)var0_12[2], null);
            if (UI.INSTANCE.getBind().getKey() != -1) break block17;
            UI.INSTANCE.getBind().setKey((int)var0_12[1]);
        }
    }

    public static void zkmSetFlag(int n) {
        Y = n;
    }

    public static int int_zkmGetFlag() {
        return Y;
    }

    public static int zkmIntConstant() {
        return 56;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5895;
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
                throw new RuntimeException("su/catlean/ew", exception);
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
            UI.c[n2] = UI.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = UI.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ew" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(UI.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

