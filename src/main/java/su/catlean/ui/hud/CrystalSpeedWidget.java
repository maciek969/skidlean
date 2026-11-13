/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

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
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.combat.CrystalSpeedCounter;
import su.catlean.util.combat.DamageData;
import su.catlean.util.combat.PlaceResult;
import su.catlean.zkm.ZkmKeys;

public final class CrystalSpeedWidget
extends HudElement {
    @NotNull
    public static final CrystalSpeedWidget N;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long g;

    private CrystalSpeedWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x15244F1FD4B4L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "crystal-speed-widget", (char)n2);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        Object object;
        long l2 = l ^ 0x6A47001394F1L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Object object2 = (PlaceResult)AutoCrystal.INSTANCE.getPlaceResultRef().get();
        float f = (float)((int)((object2 != null && (object2 = ((PlaceResult)object2).getDamageData()) != null ? ((DamageData)object2).getDamage() : 0.0f) / ((object = (PlaceResult)AutoCrystal.INSTANCE.getPlaceResultRef().get()) != null && (object = ((PlaceResult)object).getDamageData()) != null ? ((DamageData)object).getSelfDamage() : 0.0f) * (float)((int)g))) / 10.0f;
        long l3 = AutoCrystal.INSTANCE.getBreakDelay2();
        long l4 = AutoCrystal.INSTANCE.getSpawnDelay();
        this.drawText(drawContext, l2, CrystalSpeedCounter.INSTANCE.q() + " c/s | " + l4 + " ping | " + l3 + " cpu | " + f + " ef.");
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    CrystalSpeedWidget.a = ZkmKeys.a(3143732170293056007L, -2361134978586770025L, MethodHandles.lookup().lookupClass()).a(120203402105923L);
                    var14 = CrystalSpeedWidget.a ^ 67538741005062L;
                    var16_1 = var14 ^ 126335463986489L;
                    CrystalSpeedWidget.f = new HashMap<K, V>(13);
                    var5_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_3 = 1; var6_3 < 8; ++var6_3) {
                        v2 = v2;
                        v2[var6_3] = (byte)(var14 << var6_3 * 8 >>> 56);
                    }
                    var5_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_4 = new String[6];
                    var10_5 = 0;
                    var9_6 = "\u00cc\u00cbX\u008c\u008bBY\u00ffY\u000ef\u00b7\u001f\u0087G\u00a5\u0010\u00abH\u00f7\u00f1\u00f6\u00ea\u00b7\u00074\u0089\u00a6]]$V\u00aa\u0010\u00b0\u00cb;,\u0096\u00dc\u00e0\u00f0\u00ed\u0089<In\u00e7\u00c9\u00d10\u00fdB\u001f\u00ee\u00bfy\u00a8 \u00e0\u00ab\u00c3\u00d9\u0097;\u0092}\u0017$\u00c3\u00b0a4\u00b8\u0088\u00bb\u001e_\u00da\u00a1\u00ad+\u00e8lJ\u00c9\u0085\u0012\u0090Tv\u0015\u0000\u0004\u00a6\n\u00e1Cs";
                    var11_7 = 99;
                    var8_8 = 16;
                    var7_9 = -1;
lbl22:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_9;
                        v4 = var9_6.substring(v3, v3 + var8_8);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl27:
                    // 1 sources

                    while (true) {
                        var12_4[var10_5++] = CrystalSpeedWidget.zkmDecodeUtf8(var13_10).intern();
                        if ((var7_9 += var8_8) < var11_7) {
                            var8_8 = var9_6.charAt(var7_9);
                            ** continue;
                        }
                        var9_6 = "=\u00b7&\u00d1k\u00cd\u0018/\u00f4]\u0007\u009dt\u00d1s\u00c8\u0018)\u009e\u0007=v\u001d\u00e6\u00cb\u00cc\u00d0\u00ea\u00f3\u0016e\u00cc\u00976\u0019\u0085\u00ebfz6\u00c9";
                        var11_7 = 41;
                        var8_8 = 16;
                        var7_9 = -1;
lbl36:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_9;
                            v4 = var9_6.substring(v6, v6 + var8_8);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl41:
                    // 1 sources

                    while (true) {
                        var12_4[var10_5++] = CrystalSpeedWidget.zkmDecodeUtf8(var13_10).intern();
                        if ((var7_9 += var8_8) < var11_7) {
                            var8_8 = var9_6.charAt(var7_9);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_10 = var5_2.doFinal(v4.getBytes("ISO-8859-1"));
                switch (v5) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl53:
                    // 1 sources

                    ** continue;
                }
            }
            CrystalSpeedWidget.b = var12_4;
            CrystalSpeedWidget.c = new String[6];
            var0_11 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_12 = 1; var1_12 < 8; ++var1_12) {
                v9 = v9;
                v9[var1_12] = (byte)(var14 << var1_12 * 8 >>> 56);
            }
            break block14;
lbl67:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_11.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_13 = var0_11.doFinal(new byte[]{77, 37, 17, 29, 121, -77, 46, -113});
        ** while (true)
        CrystalSpeedWidget.g = ((long)var4_13[0] & 255L) << 56 | ((long)var4_13[1] & 255L) << 48 | ((long)var4_13[2] & 255L) << 40 | ((long)var4_13[3] & 255L) << 32 | ((long)var4_13[4] & 255L) << 24 | ((long)var4_13[5] & 255L) << 16 | ((long)var4_13[6] & 255L) << 8 | (long)var4_13[7] & 255L;
        CrystalSpeedWidget.N = new CrystalSpeedWidget(var16_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4678;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/gv", exception);
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
            CrystalSpeedWidget.c[n2] = CrystalSpeedWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalSpeedWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalSpeedWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

