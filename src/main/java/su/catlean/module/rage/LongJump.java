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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class LongJump
extends Module {
    @NotNull
    public static final LongJump INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting useTimer$delegate;
    @NotNull
    private static final FloatSetting timerValue$delegate;
    @NotNull
    private static final BooleanSetting jumpDisable$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final FloatSetting maxDistance$delegate;
    private static float jumpSpeed;
    private static float lastDistance;
    private static int J;
    @Nullable
    private static Vec3d startPos;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;

    private LongJump() {
        super("long-jump", Categories.getRage(), null, null);
    }

    private final boolean getUseTimer() {
        return (Boolean)useTimer$delegate.getValue(this, delegatedProperties[0]);
    }

    private final float getTimerValue() {
        return ((Number)timerValue$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final boolean getJumpDisable() {
        return (Boolean)jumpDisable$delegate.getValue(this, delegatedProperties[2]);
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    private final float getMaxDistance() {
        return ((Number)maxDistance$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    @Flow
    public final void onMove(@NotNull MoveEvent moveEvent) {
        long l;
        long l2 = l = a ^ 0x33D1F41E56D9L;
        long l3 = l2 ^ 0x24B3DF8B17B3L;
        int n = (int)(l3 >>> 32);
        int n2 = (int)(l3 << 32 >>> 48);
        long l4 = l2 ^ 0x42271E03957FL;
        Intrinsics.checkNotNullParameter(moveEvent, "e");
        if (startPos != null) {
            Entity entity = Globals.getPlayer();
            Vec3d vec3d = this.getPos(entity);
            Vec3d vec3d2 = startPos;
            Intrinsics.checkNotNull(vec3d2);
            if (vec3d.squaredDistanceTo(vec3d2) > (double)(this.getMaxDistance() * this.getMaxDistance())) {
                this.disable(l4);
            }
        }
        if (MovementUtil.INSTANCE.isMoving()) {
            if (this.getUseTimer()) {
                TimerManager.INSTANCE.x(this.getTimerValue());
            }
            switch (J) {
                case 0: {
                    jumpSpeed = (float)((double)this.getSpeed() * MovementUtil.INSTANCE.a());
                    lastDistance = 0.0f;
                    ++J;
                    break;
                }
                case 1: {
                    Globals.getPlayer().setVelocity(Globals.getPlayer().getVelocity().getX(), 0.42 + (double)this.getJumpBoostAmount(), Globals.getPlayer().getVelocity().getZ());
                    moveEvent.setY(0.42 + (double)this.getJumpBoostAmount());
                    jumpSpeed *= 2.149f;
                    ++J;
                    break;
                }
                case 2: {
                    double d = (double)0.66f * ((double)lastDistance - MovementUtil.INSTANCE.a());
                    jumpSpeed = (float)((double)lastDistance - d);
                    ++J;
                    break;
                }
                default: {
                    if (Globals.getPlayer().verticalCollision || Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().expand(-0.2, Globals.getPlayer().getVelocity().getY(), -0.2)).iterator().hasNext()) {
                        if (this.getJumpDisable()) {
                            this.disable(l4);
                        }
                        J = 0;
                        lastDistance = 0.0f;
                    }
                    jumpSpeed = lastDistance - lastDistance / 159.0f;
                }
            }
        }
        jumpSpeed = Math.max((float)MovementUtil.INSTANCE.a(), jumpSpeed);
        double d = jumpSpeed;
        MovementUtil.INSTANCE.setSpeed(n, (short)n2, moveEvent, d);
        moveEvent.cancel();
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        block0: {
            long l = a ^ 0x2AE61F4AB79DL;
            long l2 = l ^ 0x5B10F557743BL;
            Intrinsics.checkNotNullParameter(receivePacket, "e");
            if (!(receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket)) break block0;
            this.disable(l2);
        }
    }

    @Override
    public void onEnable(long l) {
        this.doJump();
    }

    private final void doJump() {
        if (Globals.getMc().player == null) {
            return;
        }
        Entity entity = Globals.getPlayer();
        startPos = this.getPos(entity);
        jumpSpeed = 0.0f;
        lastDistance = 0.0f;
        J = 0;
    }

    private final float getJumpBoostAmount() {
        return Globals.getPlayer().hasStatusEffect(StatusEffects.JUMP_BOOST) ? 0.2f : 0.0f;
    }

    @Flow
    public final void onPreSync(@NotNull PreSyncEvent preSyncEvent) {
        Intrinsics.checkNotNullParameter(preSyncEvent, "eventSync");
        if (MovementUtil.INSTANCE.isMoving()) {
            lastDistance = (float)Math.hypot(Globals.getPlayer().getX() - Globals.getPlayer().lastX, Globals.getPlayer().getZ() - Globals.getPlayer().lastZ);
        } else {
            this.doJump();
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getUseTimer();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        LongJump.a = ZkmKeys.a(122228354191651993L, -3358765337760580810L, MethodHandles.lookup().lookupClass()).a(116281293856565L);
                        var20 = LongJump.a ^ 10963638679047L;
                        LongJump.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[17];
                        var16_4 = 0;
                        var15_5 = "\u009a\u00eej\u00d6\u00ae:\u00cb\u0095\u00f8`t\u00e1\u00b1a\u00fe\u00c5\u0018\u009dP-\u00ea\u00bf\u0014\u00fd\u00b3\u00f0A3\u00f0\u0088v\u00e7\u00b1\u00b6\u001b\u00b6\u00c7Q\u001b\u00e3C(\u00a7@\u00b0D\u00d1\u00de\u00ae7\u008d\u0018J\u00c5\u00cd\u0006\u00be\u00d5j@\u009ec\u008c\u00ad\u00ab,\u00fe\u00bd\u00cdN\u00f3X!_9/K\u00a3nz\u00a8\u00fb \u0014^\u00acuG\u00ccM=s1SypkLW>\u00d9'\u0015V\u00aflw\u00e5\u00feo\u00eb\u00daQZ\u0095 \u00a7\u0006>\"\u00bf_\u0087\u00ab\u0096U\u009d\u00137\u0005\u0002t\u00aafsR\u00a4\rY\u00a8E\u001c\u00ce\u00ee\u00fd4L\u001e B\u0081\u00a8\u0003\u00e8|0\u00fe9J 0\u009e\u00ad\u00ff\u00d9\u00dd\u0096,\u001b\u00ccm~\u00de\u0012\u0006\u00e6\u008a\u00d5\u00fa\u0001\u00c3 \u009bFO\u00ef\u00c6!\"\"\u00c2c\u00aag\u00f8\u00b0\u00c1\u0001\u0006\u00f3\u00af\u00a6=\u00be\u008e\u00b8\u00f1\u0087Y\u00c5\u00a1dS\u00a5\u0010\u001f\\\u00ea\u0091z<~k\u0090kXY\u0018\u00a39\u00e5 \u00ca=\u00c1\u00024\u00ec\u0097t\u00fe\u0080W\u00e9d\u009bR\u0005\u0093\u00b0\u00b7Xx\u001b~\u00ce\u00cb\u00ea~u\u00b5\u00ef\u008f\u0087 \u001b\u0085\b\u00b6\u00e9\u00edl\u00b6\u0005\u00b4H\u0099.\u0018]\u009d\u00f7\u00e3\u00b8\u00ba\\K\u00af\f\u0088\u0006O!\u00d2\u000e,\n \u0004\u0007y\u00b7e\u00e7\u00c5 \u00d2\u0080'%\u00f2\u00bf\u00eeg@a\u00d4\u00b2\u0098\u00a6*\u00a9\u0005\u00e7N\u0080\u0000=\u00a5\u00ed mR\u00b15R\u0003\b\u00b5\u00a4\u0080\u00cdE]\u00ac\u001b\u008d\u001a\u00c7eu\u001e\u00d9\u00b9\u00e4\u0080\u00fc\u0095\u0091v\u0080.~(\u00b4\u00d5\u0005\u00c8\u00fe\u00d3\u00d7'K\u00e5h\u00f2G\u00e9\u00b9\u0016\u0088E\\\u00a7p #\u001bT\u008b\u00a7\u00d7E\u00b8\u009eJ\u00130\u00c3\u00fe\u00d3\u008c`< \u0014u-\u00ee\u00d8\u00f6[n\u00d3-\u0016\u0004\u0014\n\u00fe\u00b7`J\u00e4\u00f0\u00b26\u0014\n\u00c4\u009b@\u00b4b\u000e\u0016K\u0010\u00ec,\u00c7\u0092 \u00cdIWR\u000e\u00cc5j\u00a4\u00dd\u00ef";
                        var17_6 = 454;
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
                            var18_3[var16_4++] = LongJump.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u000e\u00f3\u0083\u00e1\u00a6\u00aa\u001b\u0094(c\u00d2\u009b\u00cd\u00a3\u00de~`]S\f4\f\u00c9\u00dc\u0097\u00f0\u00dd\u0099\u001c\\\u00b5-o\u00daN\u00f9\u00ea\u00be\u00bf\u0001\u00edv\u009f\u0098+\u00f2\u00c4\u00b5 \fk\u00fbZ\u00ca\u001b|\u0017\u00d7\u00e2K:\u0098\r\u00ca\u00ed!\r\u00e8\u00e9\u00be\u00e4\u00ee\u00db\u00b3\u000e2\u00c3\u00133\u00ca\u00ee";
                            var17_6 = 81;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = LongJump.zkmDecodeUtf8(var19_9).intern();
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
                LongJump.b = var18_3;
                LongJump.c = new String[17];
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
                var5_14 = "y\u0088\u00b9\u0014\b\u001b\u00bd}\u00f6\u00c8\u00ca\u008f\u00ca\u00be\u00c4\u00fc7\u007f\u00f5\u00c7P2\u00c0\u0083";
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
                    var5_14 = "\u00b0\u00a8\u00f2\u00bc\t\u0080\u00a1\u008a{\u00a8,\u001a<Y\u00bd\u00d6";
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
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(LongJump.class, "useTimer", "getUseTimer()Z", 0)), Reflection.property1(new PropertyReference1Impl(LongJump.class, "timerValue", "getTimerValue()F", 0)), Reflection.property1(new PropertyReference1Impl(LongJump.class, "jumpDisable", "getJumpDisable()Z", 0)), Reflection.property1(new PropertyReference1Impl(LongJump.class, "speed", "getSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(LongJump.class, "maxDistance", "getMaxDistance()F", 0))};
        LongJump.delegatedProperties = var28_20;
        LongJump.INSTANCE = new LongJump();
        LongJump.useTimer$delegate = SettingFactory.booleanSetting$default((SettingContainer)LongJump.INSTANCE, "timer", false, null, null, (int)var0_12[1], null);
        LongJump.timerValue$delegate = SettingFactory.floatSetting$default(LongJump.INSTANCE, "timer-speed", 1.0f, RangesKt.rangeTo(0.5f, 3.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), (int)var0_12[0], null);
        LongJump.jumpDisable$delegate = SettingFactory.booleanSetting$default((SettingContainer)LongJump.INSTANCE, "jump-disable", true, null, null, (int)var0_12[3], null);
        LongJump.speed$delegate = SettingFactory.floatSetting$default(LongJump.INSTANCE, "speed", 1.35f, RangesKt.rangeTo(0.1f, 10.0f), null, null, (int)var0_12[4], null);
        LongJump.maxDistance$delegate = SettingFactory.floatSetting$default(LongJump.INSTANCE, "max-distance", 10.0f, RangesKt.rangeTo(5.0f, 40.0f), null, null, (int)var0_12[2], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x753C;
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
                throw new RuntimeException("su/catlean/uh", exception);
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
            LongJump.c[n2] = LongJump.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LongJump.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LongJump.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

