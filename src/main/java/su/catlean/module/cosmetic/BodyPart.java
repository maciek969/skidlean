/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class BodyPart
extends Enum {
    @NotNull
    private final Function1 D;
    @NotNull
    private final Function1 L;
    private final double F;
    private final double n;
    public static final BodyPart HEAD;
    public static final BodyPart BODY;
    public static final BodyPart LEFT_ARM;
    public static final BodyPart RIGHT_ARM;
    public static final BodyPart LEFT_LEG;
    public static final BodyPart RIGHT_LEG;
    private static final /* synthetic */ BodyPart[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static String T;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private BodyPart(double d, double d2) {
        void var7_-1;
        void var4_3;
        void var2_-1;
        void var1_-1;
        this.D = (Function1)d;
        this.L = var4_3;
        this.F = d2;
        this.n = var7_-1;
    }

    @NotNull
    public final Function1 T() {
        return this.D;
    }

    @NotNull
    public final Function1 s() {
        return this.L;
    }

    public final double J() {
        return this.F;
    }

    public final double c() {
        return this.n;
    }

    public static BodyPart[] values() {
        return (BodyPart[])$VALUES.clone();
    }

    public static BodyPart valueOf(String string) {
        return Enum.valueOf(BodyPart.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ModelPart _clinit_$lambda$0(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.head;
        Intrinsics.checkNotNullExpressionValue(modelPart, "head");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$1(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.hat;
        Intrinsics.checkNotNullExpressionValue(modelPart, "hat");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$2(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.body;
        Intrinsics.checkNotNullExpressionValue(modelPart, "body");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$3(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.jacket;
        Intrinsics.checkNotNullExpressionValue(modelPart, "jacket");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$4(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.leftArm;
        Intrinsics.checkNotNullExpressionValue(modelPart, "leftArm");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$5(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.leftSleeve;
        Intrinsics.checkNotNullExpressionValue(modelPart, "leftSleeve");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$6(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.rightArm;
        Intrinsics.checkNotNullExpressionValue(modelPart, "rightArm");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$7(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.rightSleeve;
        Intrinsics.checkNotNullExpressionValue(modelPart, "rightSleeve");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$8(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.leftLeg;
        Intrinsics.checkNotNullExpressionValue(modelPart, "leftLeg");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$9(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.leftPants;
        Intrinsics.checkNotNullExpressionValue(modelPart, "leftPants");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$10(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.rightLeg;
        Intrinsics.checkNotNullExpressionValue(modelPart, "rightLeg");
        return modelPart;
    }

    private static final ModelPart _clinit_$lambda$11(PlayerEntityModel playerEntityModel) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "it");
        ModelPart modelPart = playerEntityModel.rightPants;
        Intrinsics.checkNotNullExpressionValue(modelPart, "rightPants");
        return modelPart;
    }

    private static final /* synthetic */ BodyPart[] $values() {
        BodyPart[] bodyPartArray = new BodyPart[(int)e];
        bodyPartArray[0] = HEAD;
        bodyPartArray[1] = BODY;
        bodyPartArray[2] = LEFT_ARM;
        bodyPartArray[3] = RIGHT_ARM;
        bodyPartArray[4] = LEFT_LEG;
        bodyPartArray[5] = RIGHT_LEG;
        return bodyPartArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    BodyPart.a = ZkmKeys.a(1690614973099928185L, 1120909493741359870L, MethodHandles.lookup().lookupClass()).a(52777777527417L);
                    var14 = BodyPart.a ^ 137842115080305L;
                    BodyPart.d = new HashMap<K, V>(13);
                    BodyPart.zkmSetFlag(null);
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
                    var12_3 = new String[20];
                    var10_4 = 0;
                    var9_5 = "\u0081\u0014;=\u00cb\u0006Yb\u00bb%\u00f3\u008duD\u0085b\u00d6l\u00c6L$\u00b4!\u00b6\u00b0Z0M<\u00ff\u00fc\u0013\u0010\u00a7h:\u00a9\u00ce~\u00197\u0083j\bu)\u0003r\u00e6 \u0018\u001a\u00bd\u00c8\u00bdel\u00c2\u00a3b\u00b1\u00e3\u0090\u0093\u00ee,\u0002\u0001\u000e\u00eb\u00b0f[\u00ecM\u00e4\u0091\u00dc9Bk\u00ed\u0010\u00a2L=C\u0094\u000eE\u00a3\u008a\u00ed\u00da]\u00d5\f\u00a0\u001b \b-\u00c9\u009d\u0089\u0091\u0088K\u00a5 Z\u00cd\u00e6\u0011\u00c3;\u0095D\u0018Z\u0086$\u00fe?\u00ea\u00fe\u00d3\u00a6\u00b2\u00ef\u00bb\u00a4 \u0013\u00b2c\u008bu\u00bd\u008ce9\u0016\u00d2*\u00c1\u0019\u0083sT\u0014\u00c1g\u00f2w\u00d0\u0097_\u0011\u00ba\u00ca\u008a\u001c\u00b9\u00cf\u0010\u00a5\u00a1\u00fa\u0015\u00adu\u009d(z\u0000\u0006\u009b\u00f4\u00d8h0\u0018\u00d1\u00cf\u00f2L#\u0098\u00c8\u00e4$Zn\u00fe\u00a8\u00d7\u00d1\u00b6\u0097;\u001d\u00b7\u0088\u00b1\u00fe\u00bc\u0018:\u008c\u008e\u00c8dc\u00ec\u0082M\u0007\u00b4Y\u0099\u0012#;\u000bf\u00ca\u008c\u0017QZ;\u0018\u0014\u008b\u00e5p\u00c7\u00d5\u00ff6\u00f7\u0007Z\u0004\u0092\u00f4\u008c;\u0018(9\u00bb%2\u00fd-\u0010\u009e\u00b9\u00b4\u008d\u00e0\u00ea5\u00b9, d$\u008e\u00c7\u00b39\u0010\u0010\u0017s\u0001\u009b\u001b\u00f3_]l\u0010\u00e0\u00a9,\u00f9P\u0010\u00a7X\u0086\u00dd,a\u00c1M\u00f8\u00b0\u00e9\u0088E\u00c4E\u00e1\u0010\u0006\u00dc\u00edD\u0002\u0082\\\u00ed\u00f0\u00f5C\u00b5\u00b3,|F\u0018$\u00a3d3*h\u0002@\u00ea\u00c7\u00e9\u00fa\u00f98i\u0094n\u00f0\u00ed\u00b8E/\u0089B\u0018\u0017uo?B\u00a0fP\r\u00a3z*y\u0012z\u0096\u00d8\u0007\u0083\u00ba\u00a48$0 \u0089f\u00b6\u00c4F\u00c8\u00d5#\u00b7\u0007\u00bbU\u00d2\u009ef\u0096\u00f7\u009e\u0002\u00e2U\u00f1\u00a2a\u00dd\u00e7\u0093&l\u001c\u00e2j\u0010\u001e\u00bf*\u0000\u0080\u0000?\u007f\u00d1\u00b0\u00d4\u00def\u0086\u0098h";
                    var11_6 = 425;
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
                        var12_3[var10_4++] = BodyPart.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u0089\u00a1\u0014\u0088\u0088\u00dd\u00be\u00ac(eds\u00caqU\u0084\u0010v4D%\u00f7>\u00c0\u00c0`8\u00d9O\u00fd\u00cb\u00a6\u00f2";
                        var11_6 = 33;
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
                        var12_3[var10_4++] = BodyPart.zkmDecodeUtf8(var13_9).intern();
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
            BodyPart.b = var12_3;
            BodyPart.c = new String[20];
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
        var4_12 = var0_10.doFinal(new byte[]{-9, -89, 46, -72, -7, 103, 28, 6});
        ** while (true)
        BodyPart.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        BodyPart.HEAD = new BodyPart("HEAD", 0, (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$1(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), 0.5, 0.25);
        BodyPart.BODY = new BodyPart("BODY", 1, (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$2(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$3(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), 0.7, 0.2);
        BodyPart.LEFT_ARM = new BodyPart("LEFT_ARM", 2, (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$4(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$5(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), 0.6, 0.2);
        BodyPart.RIGHT_ARM = new BodyPart("RIGHT_ARM", 3, (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$6(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$7(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), 0.6, 0.2);
        BodyPart.LEFT_LEG = new BodyPart("LEFT_LEG", 4, (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$8(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$9(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), 0.7, 0.2);
        BodyPart.RIGHT_LEG = new BodyPart("RIGHT_LEG", 5, (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$10(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), (Function1<PlayerEntityModel, ModelPart>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$11(net.minecraft.client.render.entity.model.PlayerEntityModel ), (Lnet/minecraft/client/render/entity/model/PlayerEntityModel;)Lnet/minecraft/client/model/ModelPart;)(), 0.7, 0.2);
        BodyPart.$VALUES = BodyPart.$values();
        BodyPart.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])BodyPart.$VALUES);
    }

    public static void zkmSetFlag(String string) {
        T = string;
    }

    public static String zkmGetFlag() {
        return T;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2F55;
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
                throw new RuntimeException("su/catlean/ce", exception);
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
            BodyPart.c[n2] = BodyPart.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BodyPart.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ce" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BodyPart.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

