/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.StepMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.PacketUtil;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class Step
extends Module {
    @NotNull
    public static final Step INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting height$delegate;
    @NotNull
    private static final BooleanSetting useTimer$delegate;
    @NotNull
    private static final BooleanSetting pauseIfShift$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map f;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map k;

    private Step() {
        super("step", Categories.getRage(), null, null);
    }

    private final StepMode getMode() {
        return (StepMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setMode(StepMode stepMode) {
        mode$delegate.setValue(this, delegatedProperties[0], (Object)stepMode);
    }

    private final float getHeight() {
        return ((Number)height$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final void setHeight(float f) {
        height$delegate.setValue(this, delegatedProperties[1], Float.valueOf(f));
    }

    private final boolean getUseTimer() {
        return (Boolean)useTimer$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setUseTimer(boolean bl) {
        useTimer$delegate.setValue(this, delegatedProperties[2], bl);
    }

    private final boolean getPauseIfShift() {
        return (Boolean)pauseIfShift$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setPauseIfShift(boolean bl) {
        pauseIfShift$delegate.setValue(this, delegatedProperties[3], bl);
    }

    @Override
    public void onDisable(long l) {
        this.setStepHeight(0.6f);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    public final void onPreSync(@NotNull PreSyncEvent var1_1) {
        block27: {
            block44: {
                block43: {
                    block42: {
                        block41: {
                            block40: {
                                block39: {
                                    block38: {
                                        block37: {
                                            block36: {
                                                block34: {
                                                    block35: {
                                                        block30: {
                                                            block33: {
                                                                block32: {
                                                                    block31: {
                                                                        block28: {
                                                                            block29: {
                                                                                v0 = StepMode.p();
                                                                                Intrinsics.checkNotNullParameter(var1_1, "e");
                                                                                var24_2 = v0;
                                                                                v1 = this;
                                                                                if (!var24_2) {
                                                                                    if (v1.getMode() == StepMode.NCP_2) {
                                                                                        return;
                                                                                    }
                                                                                    v1 = this;
                                                                                }
                                                                                v2 = v1.getPauseIfShift();
                                                                                if (var24_2) break block28;
                                                                                if (!v2) break block29;
                                                                                v2 = Globals.getMc().options.sneakKey.isPressed();
                                                                                if (var24_2) break block28;
                                                                                if (v2) break block30;
                                                                            }
                                                                            v2 = Globals.getPlayer().getAbilities().flying;
                                                                        }
                                                                        if (var24_2) break block31;
                                                                        if (v2) break block30;
                                                                        v2 = Globals.getPlayer().isRiding();
                                                                    }
                                                                    if (var24_2) break block32;
                                                                    if (v2) break block30;
                                                                    v2 = Globals.getPlayer().isTouchingWater();
                                                                }
                                                                if (var24_2) break block33;
                                                                if (v2) break block30;
                                                                v2 = Globals.getPlayer().isGliding();
                                                            }
                                                            if (var24_2) break block34;
                                                            if (!v2) break block35;
                                                        }
                                                        this.setStepHeight(0.6f);
                                                        return;
                                                    }
                                                    v2 = Globals.getPlayer().isOnGround();
                                                }
                                                if (!v2) break block36;
                                                v3 = this;
                                                if (var24_2) ** GOTO lbl45
                                                if (v3.getMode() == StepMode.STRICT_NCP) {
                                                    v4 = 1.0f;
                                                } else {
                                                    v3 = this;
lbl45:
                                                    // 2 sources

                                                    v4 = v3.getHeight();
                                                }
                                                this.setStepHeight(v4);
                                                if (!var24_2) break block37;
                                                Module.zkmSetFlag(new Module[1]);
                                            }
                                            this.setStepHeight(0.6f);
                                        }
                                        v5 = this.getMode();
                                        v6 = StepMode.NCP;
                                        if (var24_2) break block38;
                                        if (v5 == v6 || v5 == StepMode.GRIM_BYPASS) break block39;
                                        v5 = this.getMode();
                                        v6 = StepMode.STRICT_NCP;
                                    }
                                    if (v5 != v6) break block27;
                                }
                                var25_3 = Globals.getPlayer().getY() - Globals.getPlayer().lastY;
                                cfr_temp_0 = var25_3 - 0.75;
                                v7 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 < 0.0 ? -1 : 1);
                                if (var24_2) break block40;
                                if (v7 <= 0) break block41;
                                cfr_temp_1 = var25_3 - (double)this.getHeight();
                                v7 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 > 0.0 ? 1 : -1);
                            }
                            if (v7 <= 0) break block42;
                        }
                        return;
                    }
                    v8 = var27_4 = this.getStepOffsets(var25_3);
                    if (var24_2) break block43;
                    if (v8 == null) break block27;
                    v8 = var27_4;
                }
                v9 = v8.length;
                if (var24_2) break block44;
                if (v9 <= 1) break block27;
                v9 = (int)this.getUseTimer();
            }
            if (!var24_2) {
                if (v9 != 0) {
                    TimerManager.INSTANCE.x(1.0f / (float)var27_4.length);
                }
                v9 = 0;
            }
            var29_6 = var27_4.length;
            for (var28_5 = v580956; var28_5 < var29_6; ++var28_5) {
                var30_7 = var27_4[var28_5];
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().lastX, Globals.getPlayer().lastY + var30_7, Globals.getPlayer().lastZ, false, Globals.getPlayer().horizontalCollision));
                if (!var24_2) {
                    if (!var24_2) continue;
                }
                break block27;
            }
            if (this.getMode() == StepMode.STRICT_NCP) {
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().lastX, Globals.getPlayer().lastY + var25_3, Globals.getPlayer().lastZ, false, Globals.getPlayer().horizontalCollision));
            }
        }
    }

    @Flow
    private final void onMove(MoveEvent moveEvent) {
        if (!(!Globals.getPlayer().horizontalCollision || this.getMode() != StepMode.NCP_2 || this.getPauseIfShift() && Globals.getMc().options.sneakKey.isPressed())) {
            if (Globals.getPlayer().isOnGround()) {
                moveEvent.setY(0.42);
            } else if (moveEvent.getY() == -0.1552320045166016) {
                moveEvent.setY(0.333);
            } else if (moveEvent.getY() == -0.230527368912964) {
                moveEvent.setY(0.25);
            } else {
                return;
            }
            moveEvent.cancel();
        }
    }

    private final double[] getStepOffsets(double d) {
        return switch ((int)(d * 10000.0)) {
            case 7500, 10000 -> {
                double[] var6_2 = new double[]{0.42, 0.753};
                yield var6_2;
            }
            case 8125, 8750 -> {
                double[] var6_3 = new double[]{0.39, 0.7};
                yield var6_3;
            }
            case 15000 -> {
                double[] var6_4 = new double[]{0.42, 0.75, 1.0, 1.16, 1.23, 1.2};
                yield var6_4;
            }
            case 20000 -> {
                double[] var6_5 = new double[]{0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43};
                yield var6_5;
            }
            case 25000 -> {
                double[] var6_6 = new double[]{0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907};
                yield var6_6;
            }
            default -> null;
        };
    }

    private final void setStepHeight(float f) {
        EntityAttributeInstance entityAttributeInstance = Globals.getPlayer().getAttributeInstance(EntityAttributes.STEP_HEIGHT);
        Intrinsics.checkNotNull(entityAttributeInstance);
        entityAttributeInstance.setBaseValue(f);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() != StepMode.STRICT_NCP && INSTANCE.getMode() != StepMode.NCP_2 && INSTANCE.getMode() != StepMode.GRIM_BYPASS;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible2() {
        boolean bl = StepMode.p();
        StepMode stepMode = INSTANCE.getMode();
        StepMode stepMode2 = StepMode.VANILLA;
        if (!bl) {
            if (stepMode == stepMode2) return false;
            stepMode = INSTANCE.getMode();
            stepMode2 = StepMode.NCP_2;
        }
        if (stepMode == stepMode2) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Step.a = ZkmKeys.a(1901266971513563256L, 7142061953972540871L, MethodHandles.lookup().lookupClass()).a(271882228413522L);
                        var20 = Step.a ^ 12423044634143L;
                        Step.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[13];
                        var16_4 = 0;
                        var15_5 = "\u009d\u00bd\u00f8~\u00e2m\u00d3'\u00c3mL\u0081\u00b8\fV\u001d\u00d6Z\u0000N\u00a1\u00d8=\u00fa\u00a3\"&4\u00d6D\u00a5\u008c>\u00ed\n&\b\u0004\u00e2\u00a0\u0018VI\u00a1\u0089\u0014\u000f\u001e\u00c1\u00a8>\u0003\u0002%\u00a7x\u00e6\u00edo\u0086h\u009bsC\u009e \u0082\u00c3\u008fp\u00f0\u0005ijT\u009cK\u0086H%\"\u0019\u00f5\u001c$\u00f2\u00952\u00a5\u0094y\u00db\u00d5_\u00ae\u0001[w8o\u0012_l\b\u0099z\u00e4\u0099zs\u00d8\u0004\u0011Gh\u000e8\u00f8\u0014\u00e9$\u0003\u00a5\u0017t\u00eb\t\f\u00b5\u00f2~\u00ad\u0096y\u00d0\u00d9\u008dj=`aG\u00b0/\u009b)\\KC\u00fd\u0002Rn\u0081\u00af\u0010\u00cb_w\u0099JD\u0003\u001afQ5\u00860\u00fdQ< \u0088\u0088\u00e8\u00dc\u00fb\u00f9\u0095\u0099\u0084\u00ca:\u001c\u00ab\u00b3S\u00a2hlA\rZ\u00fbQ\u00fd+\u00f5\u00f7\u0006i$G@ ro\u00eb\u00a8\u00fan\u007f\u0081\u00cev\u001e\u00a0\u00cb\u009d\u00f0\u0004\u0002\u001e\u007f\u00d5\u0013\u00f3\u00e9S$v\u0006\u0080\u00d3!R\u00b2\u0010&\u00b4~\u0002\u00ac\u009a\u00e9\u00d2m\u0082\u008d\u0019\u00c1W\u00ec\u00bd\u0010f\u00c4\u00d0d\u00aa\u0002\u0004\u008b\u00ee7\u00a0\u00ec{K\u009c\u00e1\u0010\"\u00ef^\b\u00fb'\u0019\u00a3\u0005\u00afv\u00aa\b\u00af\u0088\u00a4\u0010\u00ec\u00140\u00ea\u00c1V\u0011A\u00d2\u0091\u001bd\u009ctU\u00dd";
                        var17_6 = 306;
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
                            var18_3[var16_4++] = Step.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a4\u00f7t\u00e9o\u0092k\u0097{\u00d3\u00e6\u0015\u00f1\u00d6l\u00cd@\u0003t\u00e2o\u00e5\u00faNJ\u00c1Z\u00ef\u00e1d\u00f6>\u0010\u00b5\u00aeFy\u00b9v\u00f3\u00a7{\u00ff@lE\u00ebT\u008b";
                            var17_6 = 49;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = Step.zkmDecodeUtf8(var19_9).intern();
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
                Step.b = var18_3;
                Step.e = new String[13];
                Step.k = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "5\u00c0\u00bd\u00a1\u00eb\u0006e\u0088(\u00b0:\u001e\u00a3\u00f5\u00f3r\u00aaC=8\u0014~\u00b8o,\u00a2\u00ceDsYJ\u009f\u000e^:\u0084\u0018\u0097\u00a6w\u00c5\u00dfTM\\\u00ddM\u001a\u00bfH\u000e\u00f2\u00b9\u00faAK\u00d0\u0086\u00c2-\u0086 \u00de\u0013\u000b\u00f2/h\u008ai\u0019\u00a8";
                var5_15 = 72;
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
                    var4_14 = "\u00aa\u00e5\u001c\u00da\u00a6\u00a3\u00fd\u000b\u008b\u0082B\u00c3M\u00ddW1";
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
        Step.h = var6_12;
        Step.i = new Integer[11];
        var30_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Step.class, "mode", "getMode()Lsu/catlean/sk;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Step.class, "height", "getHeight()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Step.class, "useTimer", "getUseTimer()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Step.class, "pauseIfShift", "getPauseIfShift()Z", 0))};
        Step.delegatedProperties = var30_20;
        Step.INSTANCE = new Step();
        Step.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Step.INSTANCE, "mode", (Enum)StepMode.NCP, null, null, 12, null);
        Step.height$delegate = SettingFactory.floatSetting$default(Step.INSTANCE, "height", 2.0f, RangesKt.rangeTo(1.0f, 2.5f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        Step.useTimer$delegate = SettingFactory.booleanSetting$default((SettingContainer)Step.INSTANCE, "timer", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        Step.pauseIfShift$delegate = SettingFactory.booleanSetting$default((SettingContainer)Step.INSTANCE, "pause-if-shift", false, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6F86;
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
                throw new RuntimeException("su/catlean/u5", exception);
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
            Step.e[n2] = Step.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Step.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5A08;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u5", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Step.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Step.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Step.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Step.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

