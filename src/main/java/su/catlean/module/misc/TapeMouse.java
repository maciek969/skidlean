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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.TapeMouseMode;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class TapeMouse
extends Module {
    @NotNull
    public static final TapeMouse INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final IntSetting randomize$delegate;
    @NotNull
    private static final Timer clickTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private TapeMouse() {
        super("tape-mouse", Categories.getMisc(), null, null);
    }

    private final TapeMouseMode getMode() {
        return (TapeMouseMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final int getRandomize() {
        return ((Number)randomize$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    @Flow
    public final void onRender2D(@NotNull Render2DEvent render2DEvent) {
        block4: {
            HitResult hitResult;
            block6: {
                block5: {
                    Intrinsics.checkNotNullParameter(render2DEvent, "event");
                    if (!clickTimer.hasPassed2(this.getDelay() + (int)((double)this.getRandomize() * Random.Default.nextDouble()))) break block4;
                    HitResult hitResult2 = Globals.getMc().crosshairTarget;
                    if (hitResult2 == null) {
                        return;
                    }
                    hitResult = hitResult2;
                    if (this.getMode() != TapeMouseMode.LEFT) break block5;
                    if (hitResult instanceof EntityHitResult) {
                        Globals.getInteractionManager().attackEntity(Globals.getPlayer(), ((EntityHitResult)hitResult).getEntity());
                    } else if (hitResult instanceof BlockHitResult && !Globals.getWorld().isAir(((BlockHitResult)hitResult).getBlockPos())) {
                        Globals.getInteractionManager().attackBlock(((BlockHitResult)hitResult).getBlockPos(), ((BlockHitResult)hitResult).getSide());
                    }
                    Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                    break block4;
                }
                if (!(hitResult instanceof EntityHitResult)) break block6;
                Globals.getInteractionManager().interactEntity(Globals.getPlayer(), ((EntityHitResult)hitResult).getEntity(), Hand.MAIN_HAND);
                break block4;
            }
            if (!(hitResult instanceof BlockHitResult) || Globals.getWorld().isAir(((BlockHitResult)hitResult).getBlockPos())) break block4;
            Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, (BlockHitResult)hitResult);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        TapeMouse.a = ZkmKeys.a(-713173467801877902L, 6393742044958412619L, MethodHandles.lookup().lookupClass()).a(109711385825995L);
                        var20 = TapeMouse.a ^ 130885443491714L;
                        TapeMouse.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[11];
                        var16_4 = 0;
                        var15_5 = "E0\u00aa\u0016N\u0000`1\u0094v\u00c4<\u00b9\u00f2d\u00a5 \u00a8b\u0003j\u0090\u00c9-\u00dfHG\u0088\u00f0\u00f2\u0099\u00a6\u001auq}\u000e\u00fb\u00c9\u00b3b\b\u0007#\u0001\u00ff\u00ff\u00cb\u00a7\u0010o\u00e8\u008b/%\u0017Q\u007f\u00ced\u00a3\u00ae~K\u00a6\u0018\u0018\u00e8\u00fa\u0015\u0082\u00c6A\u0096\u00bc\u0015\u0010Q\u00e1)x\u00c2\u00d6:\u00b4\u00eb\u00dfR\u00ado\u0081 \u0003bz+a\u00ff\u00d79h\u00b6A\u0097x\u00b9\u00f6t\u00fd\u0098\u00907\u00eb\u0098Q\u00b0\u00c2\u008e\u00a4\u00b5a\u00c7?\u0091\u0010\u00c9\u00f3\u0015PQ\u009cAt\u00da,/v\u00ce\u009b\u00d6\u0001 nX\u00f1TiX\b\u00f76Z&\u0083\u0083!\u0096\u0094\u00b8\u0013\u008dC^|SnAI+S\u00d4\u00bdQ\u00b2\u0018\u00ed\u00ce\u00199\u0010\u00f2z\u009d2\u00f2\u0006\u0094x\u0000:\u00e7M\u0002/Z\"\np\u00df8\u00ccI\u00bb^|/\u00cd\u000e\u00e9\u0081\u00ad\u0099\u00f0\u0083\u00ae\u00df\u009c\u00bc\n\b?\u00e6\u008d\u0084J\u0011\u00b3\r\u00de,$\u00f2\u00a9\u00d6\u00b0\u0086Bc\f\u00fcd\u00e5\u001d\u00bc\u00b4Qe\u00cb\u0002zz_\u00e6\u00a2\u0017I";
                        var17_6 = 256;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = TapeMouse.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "M\u00cc\n\u0018\u009b\u00f7wB\u0089\u00ff\u00b4pV\u00d1\u00e33\u0010\u0011\u0000\u00cb\u00fa\u0018\u00b8\u00df\"\b`\u00ebM\u001c\u00d9\u00cc\u00f7";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = TapeMouse.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                TapeMouse.b = var18_3;
                TapeMouse.c = new String[11];
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
                var0_12 = new long[7];
                var4_13 = 0;
                var5_14 = "\u009eQjT\u00a9\u00db7\rq\u00a9kC)\u0010\fP\u008b\u00c5\u00c1\u0095\u0081\u00ba\u00b8\u00b8\u00ea\u00c5\u0096\u001a\u00f2\u000f\u001eX\u00b9*\u00bf\u00fd\u009c\u00d0\u00eaS";
                var6_15 = 40;
                var3_16 = 0;
                while (true) {
                    var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                    v10 = var0_12;
                    v11 = var4_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl77:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    var5_14 = "\r\u000f~U^\u00cd\u00bb\u0084\u00c2E;\u0082HCl[";
                    var6_15 = 16;
                    var3_16 = 0;
                    while (true) {
                        var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                        v10 = var0_12;
                        v11 = var4_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl90:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl103:
                // 1 sources

                ** continue;
            }
        }
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(TapeMouse.class, "mode", "getMode()Lsu/catlean/m_;", 0)), Reflection.property1(new PropertyReference1Impl(TapeMouse.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(TapeMouse.class, "randomize", "getRandomize()I", 0))};
        TapeMouse.delegatedProperties = var28_20;
        TapeMouse.INSTANCE = new TapeMouse();
        TapeMouse.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)TapeMouse.INSTANCE, "button", (Enum)TapeMouseMode.LEFT, null, null, (int)var0_12[6], null);
        TapeMouse.delay$delegate = SettingFactory.intSetting$default(TapeMouse.INSTANCE, "delay", (int)var0_12[1], new IntRange(0, (int)var0_12[5]), null, null, (int)var0_12[3], null);
        TapeMouse.randomize$delegate = SettingFactory.intSetting$default(TapeMouse.INSTANCE, "randomize", (int)var0_12[4], new IntRange(0, (int)var0_12[0]), null, null, (int)var0_12[2], null);
        TapeMouse.clickTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x482E;
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
                throw new RuntimeException("su/catlean/eq", exception);
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
            TapeMouse.c[n2] = TapeMouse.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TapeMouse.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/eq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TapeMouse.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

