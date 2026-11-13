/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class Sprint
extends Module {
    @NotNull
    public static final Sprint INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting pauseInWater$delegate;
    private static boolean G;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;

    private Sprint() {
        super("sprint", Categories.getBasic(), null, null);
    }

    private final boolean getPauseInWater() {
        return (Boolean)pauseInWater$delegate.getValue(this, delegatedProperties[0]);
    }

    public final boolean V() {
        return G;
    }

    public final void h(boolean bl) {
        G = bl;
    }

    @Flow(priority=10)
    private final void onTick(TickEvent tickEvent) {
        if (!this.isInGame()) {
            return;
        }
        if (G) {
            G = false;
            return;
        }
        if (!(!MovementUtil.INSTANCE.isMoving() || this.getPauseInWater() && Globals.getPlayer().isSubmergedInWater() || Globals.getPlayer().isGliding())) {
            Globals.getPlayer().setSprinting(true);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    Sprint.a = ZkmKeys.a(-5176684480394093158L, -1088314767408584400L, MethodHandles.lookup().lookupClass()).a(134880797631408L);
                    var16 = Sprint.a ^ 26219846231382L;
                    Sprint.e = new HashMap<K, V>(13);
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var16 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var16 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[4];
                    var12_4 = 0;
                    var11_5 = "\u00b6\u000eV\u0001q;\u000b%\rE6h\u00bb\u00c6\u00d5,\b\u00ba\u0085\u00bb\u0087;\u00f2\u00fc*\u00fc\u0097\u00b9\u00c0\u00b8\f\u00fc\u00db{}\u0080\u0004\u00d7\u00ef*\u0018\u008d\u0014\u007f\u00a8\u00e1W\u00d9\u001d\u00df\u00ebp5\u00f5\u00dd\u00c9\u00aa\u00ba2\u00bd\u00ed\u00a5\u009ex\"";
                    var13_6 = 65;
                    var10_7 = 40;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = Sprint.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "J\u00e0c\u007f\u00bb~\u00d2!\u00a4u\u00b89\u0017\u000b2\u000f\u00dc\u00b2+c\u00ab.:P\u00a8\u0017\u001f\u009f\u00bd\u00d5\u00c2r \u0094#\u0081TL\u0092\"\u00fe\u0095,\u0090\u001cU\u00d4\u00fdS\u00dbc\u0082\u00ac\u008a\u00a6\u0096\u00fe\u00c2Nu\u00ee\u001f\u00a5\u00a8\u00e4";
                        var13_6 = 65;
                        var10_7 = 32;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = Sprint.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            Sprint.b = var14_3;
            Sprint.c = new String[4];
            var2_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var16 >>> 56);
            for (var3_11 = 1; var3_11 < 8; ++var3_11) {
                v9 = v9;
                v9[var3_11] = (byte)(var16 << var3_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var6_13 = var2_10.doFinal(new byte[]{-45, 29, 76, -90, -73, 63, -56, -77});
        ** while (true)
        var0_12 = ((long)var6_13[0] & 255L) << 56 | ((long)var6_13[1] & 255L) << 48 | ((long)var6_13[2] & 255L) << 40 | ((long)var6_13[3] & 255L) << 32 | ((long)var6_13[4] & 255L) << 24 | ((long)var6_13[5] & 255L) << 16 | ((long)var6_13[6] & 255L) << 8 | (long)var6_13[7] & 255L;
        var22_14 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Sprint.class, "pauseInWater", "getPauseInWater()Z", 0))};
        Sprint.delegatedProperties = var22_14;
        Sprint.INSTANCE = new Sprint();
        Sprint.pauseInWater$delegate = SettingFactory.booleanSetting$default((SettingContainer)Sprint.INSTANCE, "pause-in-water", false, null, null, (int)var0_12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64D4;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/_6", exception);
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
            Sprint.c[n2] = Sprint.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Sprint.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Sprint.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

