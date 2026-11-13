/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.core;

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
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.IPasteable;
import su.catlean.gofra.Gofra;
import su.catlean.module.Module;
import su.catlean.render.RenderQueue;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.zkm.ZkmKeys;

public final class FontSetup {
    private static Module[] b;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    public static final void font(@NotNull IPasteable iPasteable) {
        Intrinsics.checkNotNullParameter(iPasteable, "<this>");
        Fonts.setSF_M(Fonts.createRenderer(7.5f, "sf_medium"));
        Fonts.setSF_M3(Fonts.createRenderer(8.0f, "sf_medium"));
        Fonts.setSF_M2(Fonts.createRenderer(9.5f, "sf_medium"));
        Module[] moduleArray = new Module[]{};
        Fonts.setSF_M4(Fonts.createRenderer(6.5f, "sf_medium"));
        Module[] moduleArray2 = moduleArray;
        FontRenderer fontRenderer = Fonts.createRenderer(12.0f, "sf_bold");
        Fonts.setSF_B(fontRenderer);
        Fonts.setSF_B2(Fonts.createRenderer(8.0f, "sf_bold"));
        Fonts.setSF_B3(Fonts.createRenderer(20.0f, "sf_bold"));
        Fonts.setSF_B4(Fonts.createRenderer(25.0f, "sf_bold"));
        Fonts.setSF_SB(Fonts.createRenderer(12.0f, "sf_semibold"));
        Fonts.setSF(Fonts.createRenderer(7.5f, "sf_regular"));
        Gofra.INSTANCE.plug(RenderQueue.INSTANCE);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        b = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return b;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                FontSetup.a = ZkmKeys.a(-8764471517711460568L, -8821986877305501265L, MethodHandles.lookup().lookupClass()).a(117897264108743L);
                var9 = FontSetup.a ^ 137908168386722L;
                FontSetup.e = new HashMap<K, V>(13);
                FontSetup.zkmSetFlag(new Module[5]);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[7];
                var5_4 = 0;
                var4_5 = "\u00e2I}d\u000e 6\u00aa\u0087\u008b7j4l\u00e2\u000e\u00cf\u00ae\u00fc\u0086=\u00feeK\u0010\r\u0092\u0086\u00cak\u00da\u00d87sT\u008aEPi\u00ba\u00f4 @\u00e0\u0000Z\u0098\u00feV\u0011\u0094\u00a5\u009a\u00da\u00e5\u00bcr\u0085\u00fb\u00cd\u009aN-\u0013\u00c2_~\u001f\u00f0\u009d\u009f\u00d9\u00ef_\u0010\u008c\u00db\u0013.\u00f2\u00e4\u00d9uI\u00e1\u0011|\u001b\u0086.=\u0010\u0090\u00ad\u00dbV\u00c6\u00a7]\u0087v\u001b\u00e6\u00e0o\u001a:%";
                var6_6 = 108;
                var3_7 = 24;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = FontSetup.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00ff\u0011\u0013\u009c\u0088\u00e8\u00f7\u00a9\u00f8\u0003\u0098\u00a6evsQ\u00b7>\u00c0\u00ae\u00a1f\u00c3\u001b \u00d22)\u0002\u00812\u0099tn\u0012\u009ds\u0081\u00b4\u00d9\u00d1>.\u0006&\u00e2\u000b\u0096\u00d7o\u00f12\u00f8\u0094\u0017\u008a\u00c2";
                    var6_6 = 57;
                    var3_7 = 24;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = FontSetup.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        FontSetup.c = var7_3;
        FontSetup.d = new String[7];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3656;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/cv", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            FontSetup.d[n2] = FontSetup.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FontSetup.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/cv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FontSetup.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

