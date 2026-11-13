/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.FinishUsingItemEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ChorusDelay
extends Module {
    @NotNull
    public static final ChorusDelay INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BindSetting confirm$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    private static boolean K;
    @NotNull
    private static final LinkedList queuedPackets;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private ChorusDelay() {
        super("chorus-delay", Categories.getPlayer(), null, null);
    }

    private final Bind getConfirm() {
        return (Bind)confirm$delegate.getValue(this, delegatedProperties[0]);
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[1]);
    }

    @Override
    public void onEnable(long l) {
        queuedPackets.clear();
        K = false;
    }

    @Override
    public void onDisable(long l) {
        block0: {
            if (!K) break block0;
            this.releasePackets();
        }
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        Intrinsics.checkNotNullParameter(render3DEvent, "e");
        if (!((Collection)queuedPackets).isEmpty()) {
            Vec3d vec3d = ((PlayerPositionLookS2CPacket)CollectionsKt.first(queuedPackets)).comp_3228().comp_3148();
            Intrinsics.checkNotNull(vec3d);
            Vec3d vec3d2 = vec3d.add(-0.27, 0.0, -0.27);
            Vec3d vec3d3 = ((PlayerPositionLookS2CPacket)CollectionsKt.first(queuedPackets)).comp_3228().comp_3148();
            Intrinsics.checkNotNull(vec3d3);
            Object var8_2 = null;
            Color color = null;
            Color color2 = this.getColor();
            Box box = new Box(vec3d2, vec3d3.add(0.27, 1.8, 0.27));
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color2, color, var8_2);
        }
    }

    @Flow
    public final void onInput(@NotNull InputEvent inputEvent) {
        block0: {
            Intrinsics.checkNotNullParameter(inputEvent, "e");
            if (inputEvent.getKey() != this.getConfirm().getKey() || inputEvent.getAction() != InputEvent.Action.Release) break block0;
            this.releasePackets();
        }
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
        if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket && (K || Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.CHORUS_FRUIT))) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket");
            queuedPackets.add((PlayerPositionLookS2CPacket)packet);
            receivePacket.cancel();
        }
    }

    @Flow
    private final void onFinishUsingItem(FinishUsingItemEvent finishUsingItemEvent) {
        if (finishUsingItemEvent.getItem() != null) {
            ItemStack itemStack = finishUsingItemEvent.getItem();
            Intrinsics.checkNotNull(itemStack);
            if (Intrinsics.areEqual(itemStack.getItem(), Items.CHORUS_FRUIT)) {
                K = true;
            }
        }
    }

    private final void releasePackets() {
        K = false;
        while (!queuedPackets.isEmpty()) {
            ((PlayerPositionLookS2CPacket)queuedPackets.poll()).apply(Globals.getNetworkHandler());
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
                        ChorusDelay.a = ZkmKeys.a(3855373869780569882L, 5193921050610321478L, MethodHandles.lookup().lookupClass()).a(49281075266532L);
                        var20 = ChorusDelay.a ^ 31422838536311L;
                        ChorusDelay.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[8];
                        var16_4 = 0;
                        var15_5 = "b~+\u00e0\u00c2\u00d0\u00a1\u00fe@\u00d3\u00f8\u0016a&o(\n\u00b7(|q\u001b\u00159\u00df9?\u00d3\u00bd\u00ef\u0086\u00b3\u00d1\u008f\u00a2\u00d2\u00ca\u00e0\u00f9\u00ed\u00b1\u009e\u00e8\u001e\u00fe\u00be\u00e3\u0093\u0010\u00d1Y\u00d2\u00d0\u0003(\u00a7\u00ca'g\u00af\u0004\u00a9\u0099\u00f4Q\u0010\u009d\u009aziW\u009cgAG\u00d0\u0012\u00e9\u0090#\u00a9\u00ea\u0010\u00ff4\u000eE\u0095\u0003+\u00a9 5\u00b6Y\u00b0\u009c\u0002\u00ae\u00102\u00e0]]\u00a5\u0005\u00d7\u0092U\u00a6\u00a8\u0093\u0018\u00a2\"\u00e9 \u008e\u0000?\u00010\u00fbu\u00f5*\u009a@/\u00b6\u008a\u0086D;C\u00d6\u00b0\u009f\u00e9\u0011oM;<;\u0082\u009aZE";
                        var17_6 = 149;
                        var14_7 = 48;
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
                            var18_3[var16_4++] = ChorusDelay.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ac\u00fd\u0015D\u0088\u001bv\u00c0\u00f6\u0015\u00d3I\u00ee\u008f\u008fD\u0090@\u008f\u0089\u00a3\u0000\u0090Uw)\u00909\u00fbr\u00f7Qc3\u0081s_h\u00aa{\u00a3\u00a0\u008f\u0017\u00dc\u00ed\u001c,\u008e{\u00f6\u009b1_\u00cb}\u00a8x\u00c9\u0097:\u001c\u00f7DQ\u00bd\u00fe\u001aU@I\u0082\u001b\u009a\u0010\u0004%7TA\u0097\u00a5[A\u00d8\u0018\u00aeI\u001c9\u00bf\u0081\u0085\u00d8\u00f4l\u0005]+2\u00bf\u0082'@\u0083\u0093\nOK,/~s*\u00ef\u00b6\u00c3#\u0017\u00fa\u00e9.}H\u00c5\u00ca\u00f2I\u0080C\\\u00fat\u00f0\u0092\u00b9<\u00baYy\u0011\u0086\b\u00da\u00132^\u00f0X*\u0019\nP\u00fd\u00ee\u008e}t\u0086\u00f1\u0017\u00a3\u0096F\u00d0\u00b4E\b/b,1\u00d7\u0099'\u00d4Bp\u00fd\u00d8\u0004\u0003\u008b\u00c3$\u00b6\u0093\u00f1\u00a10H\u009b\u00d9\u00bd,\u00bc\u0001\u00ec\u0085I\u00c2~//2\u0003\u00f1a\u00c6\u009eG\u00ee7HG\u00cf\u00e9\u00dbd\u00eaA\u00a4O\u00fc(";
                            var17_6 = 225;
                            var14_7 = 56;
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
                            var18_3[var16_4++] = ChorusDelay.zkmDecodeUtf8(var19_9).intern();
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
                ChorusDelay.b = var18_3;
                ChorusDelay.c = new String[8];
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
                var0_12 = new long[5];
                var4_13 = 0;
                var5_14 = "vK\u00cf$k\u00f17\u0001\u0091$'|b\u008e\u00a6J!Z\u0084BC\u00f6;\u00b9";
                var6_15 = 24;
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
                    var5_14 = "\u00c2\u00f2\n;\\\u00073\u00daK\u00f7z\fx\u00eb\u0088\u00cb";
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
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ChorusDelay.class, "confirm", "getConfirm()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(ChorusDelay.class, "color", "getColor()Ljava/awt/Color;", 0))};
        ChorusDelay.delegatedProperties = var32_20;
        ChorusDelay.INSTANCE = new ChorusDelay();
        ChorusDelay.confirm$delegate = SettingFactory.bindSetting$default(ChorusDelay.INSTANCE, "confirm", new Bind((int)var0_12[0], false, false, (int)var0_12[3], null), null, null, (int)var0_12[2], null);
        ChorusDelay.color$delegate = SettingFactory.colorSetting$default(ChorusDelay.INSTANCE, "color", new Color((int)var0_12[4]), null, null, (int)var0_12[1], null);
        ChorusDelay.queuedPackets = new LinkedList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x867;
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
                throw new RuntimeException("su/catlean/ee", exception);
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
            ChorusDelay.c[n2] = ChorusDelay.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ChorusDelay.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ee" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ChorusDelay.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

