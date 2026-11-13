/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.AntiAimYawMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class AntiAim
extends Module {
    @NotNull
    public static final AntiAim INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting yawMode$delegate;
    @NotNull
    private static final ModeSetting pitchMode$delegate;
    @NotNull
    private static final IntSetting yawDelay$delegate;
    @NotNull
    private static final IntSetting pitchDelay$delegate;
    @NotNull
    private static final FloatSetting yawDelta$delegate;
    @NotNull
    private static final FloatSetting pitchDelta$delegate;
    @NotNull
    private static final FloatSetting yawOffset$delegate;
    @NotNull
    private static final BooleanSetting offGroundOnly$delegate;
    private static float currentYaw;
    private static float currentPitch;
    private static float h;
    private static float b;
    private static final long d;
    private static final String[] f;
    private static final String[] g;
    private static final Map i;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    private AntiAim() {
        super("anti-aim", Categories.getPlayer(), null, null);
    }

    private final AntiAimYawMode getYawMode() {
        return (AntiAimYawMode)((Object)yawMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final AntiAimYawMode getPitchMode() {
        return (AntiAimYawMode)((Object)pitchMode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final int getYawDelay() {
        return ((Number)yawDelay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final void setYawDelay(int n) {
        yawDelay$delegate.setValue(this, delegatedProperties[2], n);
    }

    private final int getPitchDelay() {
        return ((Number)pitchDelay$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final void setPitchDelay(int n) {
        pitchDelay$delegate.setValue(this, delegatedProperties[3], n);
    }

    private final float getYawDelta() {
        return ((Number)yawDelta$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    private final void setYawDelta(float f) {
        yawDelta$delegate.setValue(this, delegatedProperties[4], Float.valueOf(f));
    }

    private final float getPitchDelta() {
        return ((Number)pitchDelta$delegate.getValue(this, delegatedProperties[5])).floatValue();
    }

    private final void setPitchDelta(float f) {
        pitchDelta$delegate.setValue(this, delegatedProperties[5], Float.valueOf(f));
    }

    private final float getYawOffset() {
        return ((Number)yawOffset$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    private final void setYawOffset(float f) {
        long l = 0L;
        l = d ^ l;
        yawOffset$delegate.setValue(this, delegatedProperties[AntiAim.zkmBootstrap2("f", (int)30616, (long)(0x5459889E8D970AEDL ^ l))], Float.valueOf(f));
    }

    private final boolean getOffGroundOnly() {
        return (Boolean)offGroundOnly$delegate.getValue(this, delegatedProperties[7]);
    }

    @Flow(priority=10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (Globals.getMc().options.attackKey.isPressed() || Globals.getMc().options.useKey.isPressed()) {
            return;
        }
        if (Globals.getPlayer().isOnGround() && this.getOffGroundOnly()) {
            return;
        }
        if (this.getPitchMode() == AntiAimYawMode.RANDOM && Globals.getPlayer().age % this.getPitchDelay() == 0) {
            currentPitch = RandomUtil.p(RangesKt.rangeTo(90.0f, -90.0f), false, null);
        }
        if (this.getYawMode() == AntiAimYawMode.RANDOM && Globals.getPlayer().age % this.getYawDelay() == 0) {
            currentYaw = RandomUtil.p(RangesKt.rangeTo(0.0f, 360.0f), false, null);
        }
        if (this.getYawMode() == AntiAimYawMode.SPIN && Globals.getPlayer().age % this.getYawDelay() == 0) {
            if ((currentYaw += this.getYawDelta()) > 360.0f) {
                currentYaw = 0.0f;
            }
            if (currentYaw < 0.0f) {
                currentYaw = 360.0f;
            }
        }
        if (this.getPitchMode() == AntiAimYawMode.SPIN && Globals.getPlayer().age % this.getPitchDelay() == 0) {
            if ((currentPitch += this.getPitchDelta()) > 90.0f) {
                currentPitch = -90.0f;
            }
            if (currentPitch < -90.0f) {
                currentPitch = 90.0f;
            }
        }
        if (this.getPitchMode() == AntiAimYawMode.SIN) {
            currentPitch = (float)((double)Globals.getPlayer().getPitch() + (double)this.getPitchDelta() * Math.sin(h += (float)this.getPitchDelay() / 10.0f));
            currentPitch = RangesKt.coerceIn(currentPitch, -90.0f, 90.0f);
        }
        if (this.getYawMode() == AntiAimYawMode.SIN) {
            currentYaw = (float)((double)Globals.getPlayer().getYaw() + (double)this.getYawDelta() * Math.sin(b += (float)this.getYawDelay() / 10.0f) + (double)this.getYawOffset());
        }
        if (this.getPitchMode() == AntiAimYawMode.FIX) {
            currentPitch = this.getPitchDelta();
        }
        if (this.getYawMode() == AntiAimYawMode.FIX) {
            currentYaw = this.getYawDelta();
        }
        if (this.getPitchMode() == AntiAimYawMode.STATIC) {
            currentPitch = Globals.getPlayer().getPitch() + this.getPitchDelta();
            currentPitch = RangesKt.coerceIn(currentPitch, -90.0f, 90.0f);
        }
        if (this.getYawMode() == AntiAimYawMode.STATIC) {
            currentYaw = Globals.getPlayer().getYaw() % 360.0f + this.getYawDelta();
        }
        int n = Globals.getPlayer().age % (this.getYawDelay() * 2);
        int n2 = Globals.getPlayer().age % (this.getPitchDelay() * 2);
        if (this.getPitchMode() == AntiAimYawMode.JITTER) {
            if (n == 0) {
                currentPitch = this.getPitchDelta() / 2.0f;
            }
            if (n == this.getPitchDelay()) {
                currentPitch = this.getPitchDelta() / -2.0f;
            }
        }
        if (this.getYawMode() == AntiAimYawMode.JITTER) {
            if (n2 == 0) {
                currentYaw = this.getYawDelta() / 2.0f + this.getYawOffset() + Globals.getPlayer().getYaw();
            }
            if (n2 == this.getYawDelay()) {
                currentYaw = this.getYawDelta() / -2.0f + this.getYawOffset() + Globals.getPlayer().getYaw();
            }
        }
        float f = this.getYawMode() != AntiAimYawMode.OFF ? currentYaw : Globals.getPlayer().getYaw();
        float f2 = this.getPitchMode() != AntiAimYawMode.OFF ? currentPitch : Globals.getPlayer().getPitch();
        RotationManager.INSTANCE.rotate(new Rotation(f, f2, false, null, 12, null), 0);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AntiAim.d = ZkmKeys.a(8817249581474992697L, 4284579878735130283L, MethodHandles.lookup().lookupClass()).a(144576988037376L);
                        var20 = AntiAim.d ^ 13004444613589L;
                        AntiAim.i = new HashMap<K, V>(13);
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
                        var18_3 = new String[25];
                        var16_4 = 0;
                        var15_5 = "\u009a\u00a8H.\u00c5\"\u00d9G\u00be\u00ad\u0093;t\n\u00c4\u0002\u00f3\u00b3\u00db|\u00be\u008f\u0011& \u0080?\u00e6&\u00d2?\u0015\u00d7m:\u00dc\u00bcjE\u0095$\u0016\u00eb/\u00a3\u00cc*\u00b7\u0007g=\u001bdn\u00c2\\2 \u0006w\u00b5=\u00a4{R\n\u0086\u0091\rDO&\u00b7\u00bb\u00a1\u00e0\u00a9\u0010nQ\u00ef\f6\u00b5\u00a6f\u00a2\u0013\u0015\u0012\u0010\u00d0\u008d\u00953R\u001d\u001bH'4/\u00a6M\u00b0\u00b3\u00e4 \u0007\u00c1\u00abUA\u00b1\u00f6\u00f3\u0004E\u00aey\u0005\u00b9m\u0095\u0095\u008d~\u00d5k\u008c\u00ec\u0085P\u0098\u0095v&\u00a01\u00a6 \u00d4\n\u00be\u00b5Q\u0001\t\u000e\u00dc\u0097\u0090\u0086\u00dd\u00d6\u0099\u00b0P*\u00c9\u00bf:\u00e6\u00bcd\u00a1*7\u00b2y!hC \u00cfW\u0096\u00d8\u00fc\u0089\u00de{g\u00fe\u00b1\u00d2\u0092\u00a0`\u0084B\u0005\u00ba\u009b\u00b2\u00b8\u00f0\u00ee\u0005\u00c3\u00c7M_6#T\u0018U\u009c]\u008d\u00e2\u0088\u0089e,\u00d5\u00ca\u00f9\u00a5\u00a6\u00f4\u008e`\u00b54\u00f0\u00f0\u00fb\t\u00ca\u0010p\u00a5'\u00f0>\u00d27\u00c6[5\u00a4\u00ddz\u001fr\u00b8 \u00ef\u00be\b\u0001\u0088\u00a4\u0084\u0096\u008f\u00ebX:\u00d5\u0017&\u00cb!y\u0011}\u009b\u00af\u0016\u0083:\u00a3\u00e2\u00d17\u00dbC=\u00182'\u0012\u00f3\u00ca\u00a2~X\u00a0\u00f1\u00b7C[\u00a00^\u00a9`\u00a0\u0099\u0003\u0000\u00d1\u001e(\u001c\u0092\u00ea\u001dyC[R/\u00a59L\u00c4\u009e@\u00b5Ur\u00fd\u00bd#@rT-t~\u0085\u0087J\u00f3\u00bbT\u00a8r\u00d6$\u00e9B\u00fc \u000b\u0016d\u00ba\b\u001bi\u000f\u00ec3\u00e30~\u0085\u00d2\u00e2R\u00a4\u00f1\u0081C\u00a0\u00cc\u0087\u00e9[\u00a2\u00fdq\u00c0Le(h\u00a4\u00f3\u0098_\u00c5\u00be\u001eL\u0080\u0093n\u00c9\u00fd\u00f5\u008e\u009d\u00b1\u0090\t\u00f0\u009fw\u00e3\u00c7\f\u00c2\u0006\u00b6\u0091\u00ef\u0001\u00b1\u00c7n2%\u009a\u00df\u008c \u00a0\u0018\u0013\u0007Nu\u00eb\u008b\u0093\u0087\u00ba\u0016J\u00a1\u0099\u000e\u00cd\u00c3\ndAX\u00d8h\u00cd\u00c29\u00e80a\u0089\u009e ,\u00f4+\u0007\u00b7\u00b21\u009c\u00ea\u0080\u00e7\u00c5\u00c0\u00c0\u00f7E\r/\u0000\u0090\u0004\u009f\u001a~S5\u00f0\u00aa`\u0011\u00ba\u009f0\u009e^I\u00bb\u00f1\u00aa\u0086\u00b6F\u009b\u0084\u00d9\u00ef\u00a9&\u00feI\u00d5$\u00b6\u00e7}\u00c548\u00cf\u001d\u0085-\u00a2\u00845v\u009d|i\u00ad\u00e7EO\u00db\u00a7\u00a1>\u00d1\u00a8\u0000\"\u0010b\u00ben\u00c0$|0\u00dd \u00f5\u00c6^y\u001c)\u00b6(\u00c7\u00922k\u00f3p\u00ea\u00f2\u00e5\u0090{\u00db\u00f0\u00fa\u00ee\"jj\u00b13k\u00a9\u00c9\u00d3\u0004?\u008c\u0080v\u00a3c\u0091\u0002.\u009bA\u00d1C-Q \u0010\u009a\u00dd.0\u008c\u00efr\u00a1snjzvw&\u00cf\u00f5klq:W\u0084\u00cb\u0016!Id\u00d6\u00ceS8\u00ec\u00f7\u0005\u00eb\u00f0J\u00e9\u00b6\u001f\u00a9\u00e3\u00bd\u00f4\u0002j\\qbs\u00ea\u00d5\u00bb@\u00b56\u0014?\u00f6\u00a7c\u00f0D\u00a2?\u001a\u00e5\u00b4\u0007\u0005\u00d3t7\u00cdS\u0011\u00dds\u000f\u001f=\u001b\u0095\u00fc\u00a2\u008e\u0012\u0018a\u00d1\u00967\n\u00ed\u00bb\u0094\u00ed\u00fe\u00ec\u00d9\u00fa\u0091\u00eb&\u00a1i\"H\u00a2\f\u00e2\u00b0\u0018\u00d1G\u00aa\u00fd\"\u0007\u00a6f\u00cb\u000f\u00cf\u00a3<\u00e9\u0015\u00c7\u0097b\u00b7\u00d0\u00de\n{J";
                        var17_6 = 734;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = AntiAim.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0010N\u00ff\u0005B6j\u00a9\u00c9Q\u00f2o\u00d0J-\u00d7\u00d0|\u00ae\u00ad\u00d6s\u00a4?\u00b7\u00a5\u0018\u00a2\u008e\u0017\u00cf\u00b5 \u0000\u00a6\u0082o\u00da\u00b8\u00dc\u001dti\u00ce\u00a2\u00fc\u00eaJ\u000e\u0015\u00e5 _\u00ee\u0017\u00cev\u00a5\u00f2\u00ceV\u00c7\u0086[\u00bc";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = AntiAim.zkmDecodeUtf8(var19_9).intern();
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
                AntiAim.f = var18_3;
                AntiAim.g = new String[25];
                AntiAim.l = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "\u00f2\u0092\u0089\t\"F\u00e9m\u00c3\u008f\u00ea\u0093\u00bf\u00c7\u00f2/F\u00fdL\u0016\u0087\u0014\u00c8\u00d9!8\u0015$;\u00f9\u00f8\u00ab\u00d4\u00c8\u008f\u001ep\u0018*\u00d3@]\u0082\u0012S\u00c8{\u008c\u00c8\u00bc\u00c5\u0086n\u00cb\u00bd\u0004:\u0006jzo\u0093\u009bs\u00868E)\u00c6\u00e5\u00fe\u00f9\u008eqvS\u0013Q\n\u00e2";
                var5_15 = 80;
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
                    var4_14 = "\u00c8%\u0005zOv\u0003\u00a9'\u00c9h\u00a6\u00bf\t!\u00be";
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
        AntiAim.j = var6_12;
        AntiAim.k = new Integer[12];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AntiAim.class, "yawMode", "getYawMode()Lsu/catlean/_q;", 0)), Reflection.property1(new PropertyReference1Impl(AntiAim.class, "pitchMode", "getPitchMode()Lsu/catlean/_q;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiAim.class, "yawDelay", "getYawDelay()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiAim.class, "pitchDelay", "getPitchDelay()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiAim.class, "yawDelta", "getYawDelta()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiAim.class, "pitchDelta", "getPitchDelta()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiAim.class, "yawOffset", "getYawOffset()F", 0)), Reflection.property1(new PropertyReference1Impl(AntiAim.class, "offGroundOnly", "getOffGroundOnly()Z", 0))};
        AntiAim.delegatedProperties = var32_20;
        AntiAim.INSTANCE = new AntiAim();
        AntiAim.yawMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AntiAim.INSTANCE, "yaw", (Enum)AntiAimYawMode.OFF, null, null, 12, null);
        AntiAim.pitchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AntiAim.INSTANCE, "pitch", (Enum)AntiAimYawMode.OFF, null, null, 12, null);
        AntiAim.yawDelay$delegate = SettingFactory.intSetting$default(AntiAim.INSTANCE, "pitch-delay", 1, new IntRange(1, 180), null, null, 24, null);
        AntiAim.pitchDelay$delegate = SettingFactory.intSetting$default(AntiAim.INSTANCE, "yaw-delay", 1, new IntRange(1, 180), null, null, 24, null);
        AntiAim.yawDelta$delegate = SettingFactory.floatSetting$default(AntiAim.INSTANCE, "yaw-delta", 60.0f, RangesKt.rangeTo(-360.0f, 360.0f), null, null, 24, null);
        AntiAim.pitchDelta$delegate = SettingFactory.floatSetting$default(AntiAim.INSTANCE, "pitch-delta", 10.0f, RangesKt.rangeTo(-90.0f, 90.0f), null, null, 24, null);
        AntiAim.yawOffset$delegate = SettingFactory.floatSetting$default(AntiAim.INSTANCE, "yaw-offset", 0.0f, RangesKt.rangeTo(-180.0f, 180.0f), null, null, 24, null);
        AntiAim.offGroundOnly$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiAim.INSTANCE, "off-ground-only", true, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6342;
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
                throw new RuntimeException("su/catlean/e8", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = f[n2].getBytes("ISO-8859-1");
            AntiAim.g[n2] = AntiAim.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AntiAim.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x71F9;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])AntiAim.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    AntiAim.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AntiAim.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AntiAim.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AntiAim.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AntiAim.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

