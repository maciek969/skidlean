/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.DamageNumber;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

public final class DamageParticles
extends Module {
    @NotNull
    public static final DamageParticles INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ColorSetting color1$delegate;
    @NotNull
    private static final ColorSetting color2$delegate;
    @NotNull
    private static final FloatSetting size$delegate;
    @NotNull
    private static final IntSetting ticks$delegate;
    @NotNull
    private static final HashMap lastHealth;
    @NotNull
    private static final ArrayList numbers;
    @NotNull
    private static final DecimalFormat numberFormat;
    private static final long b;
    private static final String[] c;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private DamageParticles() {
        super("damage-particles", Categories.getCosmetic(), null, null);
    }

    private final Color getColor1() {
        return (Color)color1$delegate.getValue(this, delegatedProperties[0]);
    }

    private final Color getColor2() {
        return (Color)color2$delegate.getValue(this, delegatedProperties[1]);
    }

    private final float getSize() {
        return ((Number)size$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final int getTicks() {
        return ((Number)ticks$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    @NotNull
    public final DecimalFormat getNumberFormat() {
        return numberFormat;
    }

    @Override
    public void onEnable(long l) {
        numbers.clear();
        lastHealth.clear();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Iterator<Entity> iterator2 = Globals.getWorld().getEntities().iterator();
        while (iterator2.hasNext()) {
            Entity entity;
            Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
            if (Globals.getPlayer().distanceTo(entity) > 10.0f || !(entity instanceof LivingEntity)) continue;
            Float f = lastHealth.getOrDefault(((LivingEntity)entity).getId(), Float.valueOf(((LivingEntity)entity).getMaxHealth()));
            float f2 = f != null ? f.floatValue() : 20.0f;
            float f3 = ((LivingEntity)entity).getHealth() + ((LivingEntity)entity).getAbsorptionAmount();
            ((Map)lastHealth).put(((LivingEntity)entity).getId(), Float.valueOf(f3));
            if (f2 == f3) continue;
            numbers.add(new DamageNumber(entity, f3 - f2, ((LivingEntity)entity).getX() + RandomUtil.a(-0.5, 0.5, false, null), ((LivingEntity)entity).getY() + ((LivingEntity)entity).getBoundingBox().getLengthY() / 2.0, ((LivingEntity)entity).getZ() + RandomUtil.a(-0.5, 0.5, false, null), null));
        }
        numbers.removeIf(arg_0 -> DamageParticles.invokePredicate(DamageParticles::onPlayerUpdate$lambda$0, arg_0));
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = b ^ 0xA199414FA9FL;
        long l2 = l ^ 0x16DA57F8E251L;
        Iterable iterable = numbers;
        for (Object t : iterable) {
            String string;
            DamageNumber damageNumber = (DamageNumber)t;
            float f = (float)INSTANCE.getTicks() / 2.0f;
            float f2 = (float)damageNumber.getTicks() < f ? RangesKt.coerceIn((float)damageNumber.getTicks() / 4.0f, 0.0f, 1.0f) : 1.0f - ((float)damageNumber.getTicks() - f) / f;
            Intrinsics.checkNotNullExpressionValue(numberFormat.format(Float.valueOf(damageNumber.getHp())), "format(...)");
            Object var18_9 = null;
            int n = 24;
            FontRenderer fontRenderer = Fonts.getSF_B4();
            boolean bl = true;
            float f3 = 0.0f;
            float f4 = 0.0f;
            Color color = RenderUtil.INSTANCE.withAlpha(damageNumber.getHp() > 0.0f ? INSTANCE.getColor1() : INSTANCE.getColor2(), f2);
            Vec3d vec3d = new Vec3d(damageNumber.getPosX(), damageNumber.getPosY(), damageNumber.getPosZ());
            RenderState.drawWorldText$default(RenderState.INSTANCE, l2, string, vec3d, color, f4, f3, bl, fontRenderer, n, var18_9);
        }
    }

    private static final boolean onPlayerUpdate$lambda$0(DamageNumber damageNumber) {
        Intrinsics.checkNotNullParameter(damageNumber, "it");
        int n = damageNumber.getTicks();
        damageNumber.setTicks(n + 1);
        return n >= INSTANCE.getTicks();
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        DamageParticles.b = ZkmKeys.a(238869510665001619L, -7529362997906066858L, MethodHandles.lookup().lookupClass()).a(50413436651046L);
                        var20 = DamageParticles.b ^ 43362904450417L;
                        DamageParticles.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[19];
                        var16_4 = 0;
                        var15_5 = "k\u008c\u00ab\u00d6\t^.\u00a4\u001a\u00aa\u0094/\u00b0\u00f70\u00fa\u0091\u00a9H\u00f4=\rhXn\u00b78\u001e\u0010St\u00a3\u001d\u00ef=\u00bc8\u00bf\u00c9%\u009f\u00ef&\u00a0St4\u00c5\u00be\u0018\u00ed\u00cbr>\u00dd>\u0010\u00e68\u0090\u001cO+P\u00ca\u00e21\u00a3\u00d5\u001eN\u00fc\u00fc0\u008ci\u00ea\u00f2\u00bcg\u0082$N\u00de\u00b7ta{~\u00f9\u0019\u00da\u00beNc\u0004\u00b4\u009b\u00e0\u0082\u00a3\u0083\u00f7\u0000\u00a5\u00c4\u00c9F\u00df\u0089\u00e1t\u00fb\u008a=\u00fb\u00bdS\u00ec\u00d8\u00e0x\u0010[\u0017.\u0016>\u00ee\b\u001eK*\t>\u008dn\u00e8\u00e4\u0018qM1#\u00f6s#}jK\u0084\u00d6]ZF\u00f2\u0099a\u0015ai\u00f4\u009b6\u0010\u00b0\u00a1o\u00d5V)*\u00a0\u00de2+\u00c5\u008a\u00d61\u00f1 ~\u00bc+\u0084(\u00c8\u0083\u00fe\u00866\u0098r\u009aW\u00e2\u00be\u0098bd5h\u0017s2\u0090C\u00ce\u00e9`\u00a00\u00bc d\u00f4\u00e1\u0011\u00f3\u0098\u0080\r\u0017\u0004\u00dc,\u0005t\u00ee\b\u0083\u00ccP\u00c6\u00a3.3\u00a6\u00d5\u00b7#j@$\u00a6h\u0010\u00bc%\u00cf\u001b\u000b\u00b3|@As\u00c4x\u00ff \u00a9\u00e3\u0010)\u001b4\u00dc\u0017e\u000b%\u00fad\u0011\u00fdW9\u001b\u00dd\u0010\u00e1v)\u00d0\u00c7\"\u00d1YC\u00d95\u0017\u00ab\u00b6\u0081d\u0010C\u0085A\u0089\u00b1\u00b6\u0081Z\u00b1!8\u00ea\u008b}#1 PR\u00d2E\u00ce\u00db:v\u0083\u008f\u00df\u00fc\u008d\u0089\u00ca\u0013\u00fc'\u00d8\u00c9C(E\u00c6\u00ae\u001a\u0089\u009aC\u00ef\u00f6# \\\u001c4\u00cfz\u008e\u009cp\u00c6o\u009e\u00d4\u00a0\u00afp9\u00fe/\u009f\u00be\u00be\u0003\u00f3G\u00df\u0010\u00ae\u00ec\u00d7\u008ci\u00fc(tb\u00af\u0096\u0088\u00e3ooHY\u00ba_\u000b\u00f4\u0094%\u0018x(\u00d2?\fFo;\u008e^\u00f4q\u0001\u0080\u00d9\u009ax\u00e7\u00ffCm\u009cW\u0010cfk\u0091\u008cL\u00db\f4\u0084\u00fb\u00fe\u0096\u008d\u00fc\u00e9\u0018\u00fb\u00eb\u00e6*/i[\u00d8\u008f\u00fb\u0088]\u0003`0$\u0085\u00a9\u00c2\u00bd'=\u00a7Z";
                        var17_6 = 464;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = DamageParticles.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "@a\u0081h\b\u00fb\u0085\u00f0\u00fe\u00b57}l\u0016\u00a3I\u0010I<\u00c9\u0092\u00aa\u00bd\u00ed\u0005\u00ee\u00ed\u009d\u00e9`B\u00f2\u00df";
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
                            var18_3[var16_4++] = DamageParticles.zkmDecodeUtf8(var19_9).intern();
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
                DamageParticles.c = var18_3;
                DamageParticles.e = new String[19];
                DamageParticles.i = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "Y*=\u00bd\u00f8\u00d2E\u00e5\u00e9\u00a20\u00dbz\u009e\u001b\u00c6\u00c9-\u00b7\u00db\u00f9\u0087\n\u00e00j\u00fa\u00148\u00b5\u001aC\u00cd\u009c\u0019z!\u00b9&\u00b5";
                var5_15 = 40;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "ER\n\u00be\"\u00ee\u00a8u\u00cfw\u00c0\u00cf\u0082\u0003\u00da\u0014";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        DamageParticles.g = var6_12;
        DamageParticles.h = new Integer[7];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(DamageParticles.class, "color1", "getColor1()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(DamageParticles.class, "color2", "getColor2()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(DamageParticles.class, "size", "getSize()F", 0)), Reflection.property1(new PropertyReference1Impl(DamageParticles.class, "ticks", "getTicks()I", 0))};
        DamageParticles.delegatedProperties = var32_20;
        DamageParticles.INSTANCE = new DamageParticles();
        v15 = DamageParticles.INSTANCE;
        v16 = Color.GREEN;
        Intrinsics.checkNotNullExpressionValue(v16, "GREEN");
        DamageParticles.color1$delegate = SettingFactory.colorSetting$default(v15, "heal-color", v16, null, null, 12, null);
        v17 = DamageParticles.INSTANCE;
        v18 = Color.RED;
        Intrinsics.checkNotNullExpressionValue(v18, "RED");
        DamageParticles.color2$delegate = SettingFactory.colorSetting$default(v17, "damage-color", v18, null, null, 12, null);
        DamageParticles.size$delegate = SettingFactory.floatSetting$default(DamageParticles.INSTANCE, "size", 0.5f, RangesKt.rangeTo(0.1f, 3.0f), null, null, 24, null);
        DamageParticles.ticks$delegate = SettingFactory.intSetting$default(DamageParticles.INSTANCE, "ticks", 35, new IntRange(5, 60), null, null, 24, null);
        DamageParticles.lastHealth = new HashMap<K, V>();
        DamageParticles.numbers = new ArrayList<E>();
        DamageParticles.numberFormat = new DecimalFormat("#.#");
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6BE8;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/kr", exception);
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
            DamageParticles.e[n2] = DamageParticles.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DamageParticles.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4410;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kr", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DamageParticles.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DamageParticles.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DamageParticles.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(DamageParticles.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

