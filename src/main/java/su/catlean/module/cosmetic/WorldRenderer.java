/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector4f
 */
package su.catlean.module.cosmetic;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.time.ZonedDateTime;
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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4f;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.ClearColorEvent;
import su.catlean.api.event.events.world.ApplyFogEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class WorldRenderer
extends Module {
    @NotNull
    public static final WorldRenderer INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting fogModify$delegate;
    @NotNull
    private static final IntSetting fogStart$delegate;
    @NotNull
    private static final IntSetting fogEnd$delegate;
    @NotNull
    private static final ColorSetting fogColor$delegate;
    @NotNull
    private static final BooleanSetting timeChanger$delegate;
    @NotNull
    private static final BooleanSetting realTime$delegate;
    @NotNull
    private static final IntSetting ctimeVal$delegate;
    private static long A;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] i;
    private static final Map j;

    private WorldRenderer() {
        super("world-renderer", Categories.getCosmetic(), null, null);
    }

    private final boolean getFogModify() {
        return (Boolean)fogModify$delegate.getValue(this, delegatedProperties[0]);
    }

    private final int getFogStart() {
        return ((Number)fogStart$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final int getFogEnd() {
        return ((Number)fogEnd$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final Color getFogColor() {
        return (Color)fogColor$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getTimeChanger() {
        return (Boolean)timeChanger$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getRealTime() {
        return (Boolean)realTime$delegate.getValue(this, delegatedProperties[5]);
    }

    private final int getCtimeVal() {
        return ((Number)ctimeVal$delegate.getValue(this, delegatedProperties[6])).intValue();
    }

    private final void setCtimeVal(int n) {
        ctimeVal$delegate.setValue(this, delegatedProperties[6], n);
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().world != null) {
            A = Globals.getWorld().getTimeOfDay();
        }
    }

    @Override
    public void onDisable(long l) {
        if (Globals.getMc().world != null) {
            Globals.getWorld().setTime(A, A, true);
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block0: {
            Packet<?> packet = receivePacket.getPacket();
            if (!(packet instanceof WorldTimeUpdateS2CPacket) || !this.getTimeChanger()) break block0;
            A = ((WorldTimeUpdateS2CPacket)packet).comp_3219();
            receivePacket.cancel();
        }
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        if (this.getTimeChanger()) {
            if (this.getRealTime()) {
                this.setCtimeVal(this.getRealWorldTime());
            }
            Globals.getWorld().setTime(this.getCtimeVal(), this.getCtimeVal(), false);
        }
    }

    @Flow
    private final void onApplyFog(ApplyFogEvent applyFogEvent) {
        block0: {
            if (!this.getFogModify() || !this.isInGame()) break block0;
            applyFogEvent.setColorVec(new Vector4f(RenderUtil.INSTANCE.getRed(this.getFogColor()), RenderUtil.INSTANCE.getGreen(this.getFogColor()), RenderUtil.INSTANCE.getBlue(this.getFogColor()), RenderUtil.INSTANCE.getAlpha(this.getFogColor())));
            applyFogEvent.setEStart(this.getFogStart());
            applyFogEvent.setEEnd(this.getFogEnd());
            applyFogEvent.setRStart(this.getFogStart());
            applyFogEvent.setREnd(this.getFogEnd());
            applyFogEvent.cancel();
        }
    }

    @Flow
    private final void onClearColor(ClearColorEvent clearColorEvent) {
        if (this.getFogColor().getAlpha() == 0 || !this.getFogModify()) {
            return;
        }
        clearColorEvent.cancel();
    }

    public final int getRealWorldTime() {
        double d = ZonedDateTime.now().toLocalTime().toSecondOfDay();
        double d2 = d / 86400.0 * 24000.0;
        return (int)((d2 - 6000.0 + 24000.0) % 24000.0);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getFogModify();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getFogModify();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getFogModify();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getTimeChanger();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getTimeChanger();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        WorldRenderer.b = ZkmKeys.a(-6005477155652132014L, 4869941492332302944L, MethodHandles.lookup().lookupClass()).a(5715448013098L);
                        var20 = WorldRenderer.b ^ 104094697991104L;
                        WorldRenderer.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[22];
                        var16_4 = 0;
                        var15_5 = "\u00fc;\u000bc\u00d2\u00d2B4C@43\u001f\u00ddQs\u009a\u00f1\u00f5\u00fc\u0005J\u00bf*n\u00de\u00d7\u00f4\u007f\u0004\u00c3\u00138\u00e4\u0010\u00ce\u00ab>Ae>@ps7:\u009f +K\u009bg\u0099\u00bc\u00a3~\u0015(BY\u001fs\u0097e\u00b0\u00f5\u00ad\u00d3{\u0015,HM\tZ[#U\u00ef\u00e3Fp/\u00ec=6\u00b0\u00c6&\u0018*L\u00ec}\u0011}\u00d2r\u00fd\u008ax\u0018\u00b8\u00e8\u00ea-s\u00f3\u00e5\u00bf@'\r\u00d7\u0018!\u0006\u00abJ\u00ae\u00ff\u0082\u00af\u00f6\u00a5\u00e1\u00bc:\u00e5r&\u000f\u0099\u00dc\u00e7S\u0096wQ y\u00b49\u001f\u00c1o\u0013\u00c3\u00da$\u0011\u0091mL\u009c\u0015\u009b\u0090l\u00b2FL\u00bf\u0007\u00e9\u00ff+m\u0094\u001f#N\u0010\u00c8\u00d2\u00a8\u00ad\u00d8]\u00c0\u0002f\u00ad\u00e4:\u00d7\r\u00e7{ \u000e\u0015k\u0004+\u00e7\u0002Po\u0005\u00af\u00e2\u009f\u00dc\u00c3_\u00c8[W\u00d5OR?r\u00e6\u00a7\u0001B4U9\u0014\u0010'\u00d0b+\u0006\u00ccm\"5\u0082R\u00f7?\f** \u00db|\u0094\u00c5HTeb6\u0082\u00de\u00a51\u00fa\u00a8\u009a\u001d\u00f8U\u001e\u00dd\u008d\u00a6K\u00b7q\u0094C\u0015Pp\u0090 \u00f4\u00af\u00b8\u00cc\u00b9v\u009d%6h\u009c\u00d5\u00ed\b2\f\u00ca>\u00fe\u0005\u00b9ym\u00f2\u00c6\u001bj,q\u00ae\u00fdl \u0016x\u001a\u001a:\u0015\u0010z@\u00a5\u00df B7\u000e\u00a6\u00db`\u00ae\u00bb\u00fe~9[N{\u00d6gE\u00ad\u0083,(;\t|\u00d0\u00b9\u00a8\u00a9c\u0000\u0091'?0\\\u00f2\u00d2\u00a31\u00aa%\u00a2\u0018}\u00e93\u00f3\u00e0s\u00f3\u00bf\u00be\u00b5\u00d6\u00cc\u00a7}\u00ce\u00f7+X\u0010\u00f3\u00ea\u00f7\u00b9$\bs\u0080\u00e4.0\u00b7\u00eb\u000f>\u00e7\u0018\u009f-\u009c\u00de\u00df4\u00d0\u0090\u008c\u0000\u00de=c\u000f\u0081bV;6Q\u00e3V\u00be\u0082\u0018\u00b5\u008bZ \u00f8Y\u0013\u00fb\u00a2\u0087\u00dd\u00fb\u00b5\u00a2[\u00b6&\u0099Q\u00f1\u0091LBh\u0018PsS\u00a8\u0018\u00f5\u00f5'\u00d6\u00fd\u007f\\\u00ca\u00ef\u0015\u00e4\u00cc\u00cd\u00d3\u008e\u0091W\u00a8\u00ba \u00d5Z\u0085s\u0081b\u00b0\u0015KC\nv\u00d4\u00d3E\u00cf%\u00bfYI\u001c@\u001a\u00b6\u000f\b\u00df\u00ea\u0015\u00b8z\u00ce \u00eb\u00b39%\u00fa\u001b'o\u00bc\u00f0\u0004\u0005\u00b4\u0095\u00fcqDF\u0089\u009fA\u001f\u00e6\u0080\u0007\u00c2\u009c\u001b\b\u0082\u00b0\u00fb \u000b*!\u00de\u008b\u00fc\u00f8\u00af_\u0091\u0006\u00b9\u0004\u00fdT\u0096g\u00a9\u00e5\u00a1\u00be\u0086s\u001a\u00be\u0097\u0011\u00b3e\u007f\u0085| B\u0006\u00a66s\u00e4\u000b+&\r\u00e8]\u00b7!\u008c9\u0083\u00c5\u00e1@\u00c3\u00e0\u0081'!\b(e\u0017/B\u00eb";
                        var17_6 = 603;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = WorldRenderer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a5\u00a6 ^h\u00d9#4\u00bf\u0097\u0011\u008e\u00e2\u00ed\u00cf\u0018\u008eXY\u00b6TV\u00b1\u00e8 \u0099\u0096H\u0090O\u00d4\u00ccZt\u00cd=\u0085%\u0089\u00c1\u00a0\u0099\u00b6\u000fM\u00d5\u00a5\u009e\u00e7\u00aa\u00af\u00d1c\u0011bd\u00b2";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = WorldRenderer.zkmDecodeUtf8(var19_9).intern();
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
                WorldRenderer.c = var18_3;
                WorldRenderer.d = new String[22];
                WorldRenderer.j = new HashMap<K, V>(13);
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
                var6_12 = new long[17];
                var3_13 = 0;
                var4_14 = "\u0018E\u0005 B\u00d8\r\nQ\u00feaZ\u0080U\u00f0\u009fq.\u00ff\u00e8\u001ax\u00edkX\u001c\u00814vJ\u00dd\u008e6\u00f6[t\u00b5\u00c4\u00d6\u00f9\u0001u\u00bf\u0017\u00bc\u00ceA\u00de0qBVMz\u008f^\\\u00ca\u00eer\u001c,6;5l\u00beh\u00ae\u00cb\u00a2{{\u00ab\u00a4)\u0010\u0092z\u00a9\u00f9\u0088+\u00946\u00bd\u000esh\u00ed-*\u00de\u008e\u00e2\u00c4\u00c3o1\u0002\u0003\u0013HS\u00fb9+\u00f9n<\u00f2\u0098\n\u00a5#/\u0086\u00dco\u0007";
                var5_15 = 120;
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
                    var4_14 = "\u00ba\u00f1I/\u001d\u0093+\u00ab\u0099\u0081\u00ce\u00a6W\u008b\u0011\r";
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
        WorldRenderer.f = var6_12;
        WorldRenderer.i = new Integer[17];
        var31_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(WorldRenderer.class, "fogModify", "getFogModify()Z", 0)), Reflection.property1(new PropertyReference1Impl(WorldRenderer.class, "fogStart", "getFogStart()I", 0)), Reflection.property1(new PropertyReference1Impl(WorldRenderer.class, "fogEnd", "getFogEnd()I", 0)), Reflection.property1(new PropertyReference1Impl(WorldRenderer.class, "fogColor", "getFogColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(WorldRenderer.class, "timeChanger", "getTimeChanger()Z", 0)), Reflection.property1(new PropertyReference1Impl(WorldRenderer.class, "realTime", "getRealTime()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WorldRenderer.class, "ctimeVal", "getCtimeVal()I", 0))};
        WorldRenderer.delegatedProperties = var31_20;
        WorldRenderer.INSTANCE = new WorldRenderer();
        WorldRenderer.fogModify$delegate = SettingFactory.booleanSetting$default((SettingContainer)WorldRenderer.INSTANCE, "fog-modify", true, null, null, 12, null);
        WorldRenderer.fogStart$delegate = SettingFactory.intSetting$default(WorldRenderer.INSTANCE, "fog-start", 0, new IntRange(0, 256), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        WorldRenderer.fogEnd$delegate = SettingFactory.intSetting$default(WorldRenderer.INSTANCE, "fog-end", 64, new IntRange(10, 256), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        WorldRenderer.fogColor$delegate = SettingFactory.colorSetting$default(WorldRenderer.INSTANCE, "fog-color", new Color(11075839), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        WorldRenderer.timeChanger$delegate = SettingFactory.booleanSetting$default((SettingContainer)WorldRenderer.INSTANCE, "time-changer", false, null, null, 12, null);
        WorldRenderer.realTime$delegate = SettingFactory.booleanSetting$default((SettingContainer)WorldRenderer.INSTANCE, "real-time", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        WorldRenderer.ctimeVal$delegate = SettingFactory.intSetting$default(WorldRenderer.INSTANCE, "time", 21000, new IntRange(0, 23000), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4C9D;
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
                throw new RuntimeException("su/catlean/km", exception);
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
            WorldRenderer.d[n2] = WorldRenderer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WorldRenderer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/km" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64AF;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/km", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            WorldRenderer.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = WorldRenderer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/km" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WorldRenderer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(WorldRenderer.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

