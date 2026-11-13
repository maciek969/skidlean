/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Quaternionfc
 */
package su.catlean.module.cosmetic;

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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.render.EquipArmEvent;
import su.catlean.api.event.events.render.SwingArmEvent;
import su.catlean.api.event.events.render.SwingDurationEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.SwingAnimations$WhenMappings;
import su.catlean.module.cosmetic.SwingAnimationsMode;
import su.catlean.module.cosmetic.ViewModel;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;

public final class SwingAnimations
extends Module {
    @NotNull
    public static final SwingAnimations INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final IntSetting speed$delegate;
    private static float progress;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private SwingAnimations() {
        super("swing-animations", Categories.getCosmetic(), null, null);
    }

    private final SwingAnimationsMode getMode() {
        return (SwingAnimationsMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setMode(SwingAnimationsMode swingAnimationsMode) {
        mode$delegate.setValue(this, delegatedProperties[0], (Object)swingAnimationsMode);
    }

    private final int getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final void setSpeed(int n) {
        speed$delegate.setValue(this, delegatedProperties[1], n);
    }

    @Flow
    private final void onSwingDuration(SwingDurationEvent swingDurationEvent) {
        swingDurationEvent.setValue(this.getSpeed());
        swingDurationEvent.cancel();
    }

    @Flow
    private final void onEquipArm(EquipArmEvent equipArmEvent) {
        if (equipArmEvent.getArm() == Arm.RIGHT) {
            progress = equipArmEvent.getProgress();
        }
    }

    @Flow
    private final void onSwingArm(SwingArmEvent swingArmEvent) {
        if (swingArmEvent.getArm() != Arm.RIGHT) {
            return;
        }
        swingArmEvent.getMatrices().translate(-0.56f, 0.52f - progress * -0.6f, 0.72f);
        swingArmEvent.cancel();
        switch (SwingAnimations$WhenMappings.U[this.getMode().ordinal()]) {
            case 1: {
                float f = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                this.applySwingAnimation(swingArmEvent.getMatrices(), swingArmEvent.getArm(), swingArmEvent.getSwingProgress());
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 2: {
                float f = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                double d = ((Number)Easing.OUT_ELASTIC.a().invoke(Double.valueOf(swingArmEvent.getSwingProgress()))).doubleValue();
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((float)d * -90.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 3: {
                float f = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                double d = ((Number)Easing.OUT_ELASTIC.a().invoke(Double.valueOf(swingArmEvent.getSwingProgress()))).doubleValue();
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((float)d * -360.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 4: {
                float f = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                double d = ((Number)Easing.OUT_SINE.a().invoke(Double.valueOf(swingArmEvent.getSwingProgress()))).doubleValue();
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((float)d * -360.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 5: {
                float f = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                double d = ((Number)Easing.OUT_BACK.a().invoke(Double.valueOf(swingArmEvent.getSwingProgress()))).doubleValue();
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((float)d * -360.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 6: {
                float f = (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                float f2 = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f2);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(50.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-30.0f * (1.0f - f) - 30.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(110.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 7: {
                float f = (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                swingArmEvent.getMatrices().translate(0.0, 0.0, 0.0);
                float f3 = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f3);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(50.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-60.0f * f - 50.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(110.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 8: {
                float f = (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                float f4 = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f4);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(50.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-60.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(110.0f + 20.0f * f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 9: {
                float f = (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                float f5 = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f5);
                swingArmEvent.getMatrices().translate(0.0, 0.0, (double)(-f) / 4.0);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-120.0f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 10: {
                float f = (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                float f6 = 0.0f;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f6);
                ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-((float)Math.sin(swingArmEvent.getSwingProgress() * 3.0f)) * 60.0f));
                swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(-60.0f * f));
                ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                break;
            }
            case 11: {
                if (swingArmEvent.getSwingProgress() > 0.0f) {
                    float f = (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                    swingArmEvent.getMatrices().translate(0.56f, progress * -0.2f - 0.5f, -0.7f);
                    ViewModel.INSTANCE.applyMainHandOffset(swingArmEvent.getMatrices());
                    swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(45.0f));
                    swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f * -85.0f));
                    if (ViewModel.INSTANCE.getEnabled()) {
                        swingArmEvent.getMatrices().translate(-0.1f * ViewModel.INSTANCE.getScale(), 0.28f * ViewModel.INSTANCE.getScale(), 0.2f * ViewModel.INSTANCE.getScale());
                    } else {
                        swingArmEvent.getMatrices().translate(-0.1f, 0.28f, 0.2f);
                    }
                    swingArmEvent.getMatrices().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-85.0f));
                    ViewModel.INSTANCE.undoMainHandOffset(swingArmEvent.getMatrices());
                    break;
                }
                float f = -0.4f * (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * (float)Math.PI);
                float f7 = 0.2f * (float)Math.sin(MathHelper.sqrt(swingArmEvent.getSwingProgress()) * ((float)Math.PI * 2));
                float f8 = -0.2f * (float)Math.sin(swingArmEvent.getSwingProgress() * (float)Math.PI);
                swingArmEvent.getMatrices().translate(f, f7, f8);
                float f9 = progress;
                Arm arm = swingArmEvent.getArm();
                MatrixStack matrixStack = swingArmEvent.getMatrices();
                this.applyEquipOffset(matrixStack, arm, f9);
                this.applySwingAnimation(swingArmEvent.getMatrices(), swingArmEvent.getArm(), swingArmEvent.getSwingProgress());
            }
        }
    }

    private final void applySwingAnimation(MatrixStack matrixStack, Arm arm, float f) {
        int n = arm == Arm.RIGHT ? 1 : -1;
        float f2 = (float)Math.sin(f * f * (float)Math.PI);
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)n * (45.0f + f2 * -20.0f)));
        float f3 = (float)Math.sin(MathHelper.sqrt(f) * (float)Math.PI);
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)n * f3 * -20.0f));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f3 * -80.0f));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)n * -45.0f));
    }

    private final void applyEquipOffset(MatrixStack matrixStack, Arm arm, float f) {
        matrixStack.translate((float)(arm == Arm.RIGHT ? 1 : -1) * 0.56f, -0.52f + f * -0.6f, -0.72f);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SwingAnimations.b = ZkmKeys.a(5370127439062463046L, -4337332661934070925L, MethodHandles.lookup().lookupClass()).a(212291494360167L);
                        var20 = SwingAnimations.b ^ 22229908249825L;
                        SwingAnimations.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[7];
                        var16_4 = 0;
                        var15_5 = "\u00a7n+\u00a5\u00a2\u008aJ\u0097\u00aa=\u008a\u009b\u0090\u00fc\rk\u00f1d\u00b2\u0003\u00fe\u00b4\u00ca\u00ee\u0013\u0003\u00e5>\u00c5e\u0003\u00b7\u00cd\u00a8\u00cd\u0000\u0085\u00bau\u009c\u0018Vi\u00ec\u00b8\u00ae:\u00e5kI\u00e0\u0003\u00bd39\u00c8\u007f\u00c7\u00f4\u001e\u0096\u00dfb\n\u00e88}|\u0084p\u00d3U11^\u00d5\u0080c\u0015(N\u00ca\u0091Y\u009e\u001f~r\u001f\u00d7*i\u00c0\u00ae\u00c9\u00ccF\u009fx\b\u00e1f^\u00fc\u00b5\u00ac\u0011\u0088F.@\u00ac\u00ba\u00ae\u00a7C\u0096r\u001f1\u00cdM\u0010\u009eb\u0080I\r`$G\u00bd\u00e5\u001a\u00c6\u00eb\u00fd\u00efY\u0010\u0006\u00ae\u0080*;u\u00df\u0006\u0018\u00a0\u00b9]\u009cC\u00da\u0091";
                        var17_6 = 156;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = SwingAnimations.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "#\u00d3.k.\u008e\u00be\u0004\u00dc\u00f8:\u000b|\u0012\u00cdo\u0010g\u00e1_\u0002\u008b\u00991\u00be\u00d9,\u00bc\u00de\u0091\u00d7\u008f%";
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
                            var18_3[var16_4++] = SwingAnimations.zkmDecodeUtf8(var19_9).intern();
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
                SwingAnimations.c = var18_3;
                SwingAnimations.d = new String[7];
                SwingAnimations.h = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u001f5\u00a3\u0086\u00e4\u009d\u00d5@\u008b-~\u0005 \u008a\u007f\u001bq\u00c684\u00dc\u00fe\"8";
                var5_15 = 24;
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
                    var4_14 = "\u00ad`xzr;\u00d2\u00a2\u0006\u00c0f\u00cc\u00b5\u00d4\u00e7\u00c4";
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
        SwingAnimations.f = var6_12;
        SwingAnimations.g = new Integer[5];
        var28_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(SwingAnimations.class, "mode", "getMode()Lsu/catlean/df;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SwingAnimations.class, "speed", "getSpeed()I", 0))};
        SwingAnimations.delegatedProperties = var28_20;
        SwingAnimations.INSTANCE = new SwingAnimations();
        SwingAnimations.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)SwingAnimations.INSTANCE, "mode", (Enum)SwingAnimationsMode.Default, null, null, 12, null);
        SwingAnimations.speed$delegate = SettingFactory.intSetting$default(SwingAnimations.INSTANCE, "speed", 12, new IntRange(0, 64), null, null, 24, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x11B6;
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
                throw new RuntimeException("su/catlean/k5", exception);
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
            SwingAnimations.d[n2] = SwingAnimations.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SwingAnimations.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2D46;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k5", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SwingAnimations.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SwingAnimations.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SwingAnimations.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SwingAnimations.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

