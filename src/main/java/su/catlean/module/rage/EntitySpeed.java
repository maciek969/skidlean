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
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.EventPlayerTravel;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class EntitySpeed
extends Module {
    @NotNull
    public static final EntitySpeed INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting accelerate$delegate;
    @NotNull
    private static final FloatSetting accelerateFactor$delegate;
    @NotNull
    private static final BooleanSetting stopunloaded$delegate;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final FloatSetting timer$delegate;
    @NotNull
    private static final FloatSetting jitter$delegate;
    private static int m;
    private static float b;
    private static final long a;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map k;

    private EntitySpeed() {
        super("entity-speed", Categories.getRage(), null, null);
    }

    private final boolean getAccelerate() {
        return (Boolean)accelerate$delegate.getValue(this, delegatedProperties[0]);
    }

    private final float getAccelerateFactor() {
        return ((Number)accelerateFactor$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final boolean getStopunloaded() {
        return (Boolean)stopunloaded$delegate.getValue(this, delegatedProperties[2]);
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    private final float getTimer() {
        return ((Number)timer$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    private final float getJitter() {
        return ((Number)jitter$delegate.getValue(this, delegatedProperties[5])).floatValue();
    }

    @Override
    public void onEnable(long l) {
        m = 0;
        b = 0.0f;
    }

    @Flow
    public final void onPlayerTravel(@NotNull EventPlayerTravel eventPlayerTravel) {
        Intrinsics.checkNotNullParameter(eventPlayerTravel, "ev");
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            return;
        }
        Entity entity = Globals.getPlayer().getControllingVehicle();
        if (entity == null) {
            return;
        }
        Entity entity2 = entity;
        if ((!Globals.getWorld().isChunkLoaded((int)this.getPos(entity2).getX() >> 4, (int)this.getPos(entity2).getZ() >> 4) || this.getPos(entity2).getY() < -60.0) && this.getStopunloaded()) {
            return;
        }
        if (entity2.horizontalCollision || Globals.getPlayer().horizontalCollision) {
            b = 0.0f;
        }
        if (!(this.getTimer() == 1.0f)) {
            TimerManager.INSTANCE.x(this.getTimer());
        }
        double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, this.getBoostedSpeed(), false, 0.0f, 0.0f, 0.0f, 30, null);
        double d = entity2.getX() + dArray[0];
        double d2 = entity2.getZ() + dArray[1];
        if ((!Globals.getWorld().isChunkLoaded((int)d >> 4, (int)d2 >> 4) || this.getPos(entity2).getY() < -60.0) && this.getStopunloaded()) {
            return;
        }
        if (MovementUtil.INSTANCE.isMoving()) {
            entity2.setVelocity(dArray[0], entity2.getVelocity().getY(), dArray[1]);
        } else {
            entity2.setVelocity(0.0, entity2.getVelocity().getY(), 0.0);
        }
        int n = m;
        m = n + 1;
        if (n > 50) {
            m = 0;
        }
        eventPlayerTravel.cancel();
    }

    @Flow
    public final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        block1: {
            int n = RenderState.zkmIntConstant();
            Intrinsics.checkNotNullParameter(receivePacket, "e");
            int n2 = n;
            if (receivePacket.getPacket() instanceof PlayerPositionLookS2CPacket) {
                b = 0.0f;
            }
            if (n2 != 0) break block1;
            Module.zkmSetFlag(new Module[4]);
        }
    }

    private final float getBoostedSpeed() {
        block3: {
            EntitySpeed entitySpeed;
            float f;
            block2: {
                float f2;
                float f3 = f2 = b + (20.0f - this.getAccelerateFactor()) / this.getSpeed();
                b = f3;
                double d = f2 / 100.0f;
                double d2 = this.getSpeed();
                f = (float)Math.min(d, d2);
                int n = RenderState.zkmIntConstant();
                entitySpeed = this;
                if (n == 0) break block2;
                if (entitySpeed.getAccelerate()) break block3;
                entitySpeed = this;
            }
            f = entitySpeed.getSpeed();
        }
        return f += m > 25 ? this.getJitter() : 0.0f;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getAccelerate();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        EntitySpeed.a = ZkmKeys.a(-4972314754821266888L, 4656782431449420122L, MethodHandles.lookup().lookupClass()).a(118060033216283L);
                        var20 = EntitySpeed.a ^ 101681660103506L;
                        EntitySpeed.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[20];
                        var16_4 = 0;
                        var15_5 = "\u00ed\u008en[vw\u00ef~\u00f8E\u00fc@\u0002\u00be\u00f6\u0093\u0016\u00bf\u00ce\u00b1\u0017\u00eaj\u00b2\\17ym\u0007\u00af\u0000\u00c2\u0097o@\u00e3\u00aa\u00ec\u00f8 \u00c0O|\u00cfO@\u001d\t\u00c6\u0019Y~\u0084\u00f0\u00ed\u00b399\u008d\u0080\u001bX\u0019W\u00b3\u00ba\u008a\u0007\u008dvP\u0018 N\u008d\u00e2\u008d3\u00c9J\u00d3\u00c4\u00d2\u00be\u009b`c\u00b2Lu\u00176\u00ad\u008dz)\u00f5%\u00ed_\u008a\u00fd\u00feX\u00cf\u0010\u00d7\u00f0\u008eq\u001d\u001f0\u00fc\u00e0\u00b7\u0092\u00b7<\b\u0090I(\f\u001d_w\u008a\u00c0+\u0090\u00fd\u009f\u0013\u00f2\u00c0\u00e1\u00bcc~\u0007\u00a3\u00a6\u00d3\u0013\u00b3\u00d0\u00df\u00f3\u00a6pwVt\u009c\rah\u00a1\u009em;\u00f6\u00100K\u000eb\u00e5_z\u00fbd\u00f8\u00ee\u00c0\u0004\u0093\u00b9\u00e407\u00c0\u008b\u00e0B\u00c2\u009ch\u00a66\u0011\u00d3('\u00fb\u001b\u00df\u00c2\u0014\u009b\u00d6^uu \u0093\u008d\u00a5\u0096\u00c1\u008eA\u00d4\u00e4\u0085\u00cf\u00e4pUgJ\u00fb\u0004\u00e0\u00d4\u00ea}r \u00de\u00d0\u00a9\u00f7\u00df\u0096+B:\u0098v}\u000b\u00a6!\u00cbI1\u00ad\u000b8\u00af\u000fR\u00a16\u001b\u00b9\u000f\u00d6\u009c9\u0010F\u00bb\u00f0I R\u00c2?4>:\u00165q\u00bc^ lF\u00aa#\u000e\u0082\u00f5$\u00edx\u000b\u0097\u00a8\u00fc\u00f1l\u00af\u0092\u00b5p\u0091z\u00f2\u0000\u0011\u0018\u00be\u0091\u00fe\u0093A\u001f(\u0092<\u00bf\u00b5\u008c\rmQ9\u00c8\u00ea\u001e\u0002\u00ff\u00c0\rA\u00das\u0088\"\u00d8[\u00be\u00aerGSL\u00d2\u0094\u001b\u00bb\u00e4\u00a7S\u00b5M\\\u00c1(/X\u00af\u00e7`\u00e7e\u00a2\u0094I(C\u0081\u00b2\u001f<\u00bd\u0090\u00850B1o\u00c2>\u00a7\u0087N5\u00d8I\u00e1\u000e\u0001\u00fc\u0005\u0006&\u00c3\u0089 o\u00b3\u00ea(\u00c5\u00b9\u00f9Pa\u00ca\u0090#6\u00c8:\u0019\u00be_<\fOJ):\n\u00a5\u00dd\u001aBNZ\u00b4\u0010\u0091%}\u00bcW\u00b53\u00cb;\u000f0]\u0090b-m\u0018t1\u0007'R}\u00e5\u00eb\u00df\u00c3N\u009f\u001c\u00c5\u00b1P>\u0085\r\u00b7\u0093cx\u00b5\u0010F\u00b6B\u00af \u00de\u00b6\u00ba\u00c0x<\u00b9E\u0013\u00bb\u00ee\u0010n\u0081qU\u00f685\u001e\u0086/\u009a\u0084\u009d\u00cb;\u008e \u00f2\u00c1W\u0013]ZJ9+N\u00d7av\u00bel\u00f6g\u00a7\u009a\u00cc\u00fba\u00b4 q~\u008e\u00fa\u001e\u00b0\u00f8\u00ae";
                        var17_6 = 537;
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
                            var18_3[var16_4++] = EntitySpeed.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "RC\u001c\u00a6\u00a8GH\u00b7'Cz\f\u00f64&\u00c0\u0018\u001a\u00f1\u00ba\u0014\u008a\u008a\u00ed\u00f5\u0016\u00ed&f\u0089\u008c\u0087\u00d3yB\"\u00a5\u00b2\u009c3r";
                            var17_6 = 41;
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
                            var18_3[var16_4++] = EntitySpeed.zkmDecodeUtf8(var19_9).intern();
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
                EntitySpeed.d = var18_3;
                EntitySpeed.e = new String[20];
                EntitySpeed.k = new HashMap<K, V>(13);
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
                var6_12 = new long[9];
                var3_13 = 0;
                var4_14 = "\fY\u00e8\u0002_hn\u0012\u00ef\u0093`\u0096\u0083t\u00a1\u00bdN\u00fc\u00b3\u0087\u0010\u00a1\u00acvi\u00c6|\u0099\u00a8\u0007\u00fe\u00d2I\u0000\u0018\u00a4[\u00075\u00fb:J\u00ec\u0083\u00f6\u00fe\u00a0~\u000f\u009d\u0099!C\u0081\u0016]";
                var5_15 = 56;
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
                    var4_14 = "z\u008eK\u00e3c\u00af\u00bf\u001fb\u00915\u008b\u0015.3\u0007";
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
        EntitySpeed.h = var6_12;
        EntitySpeed.i = new Integer[9];
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(EntitySpeed.class, "accelerate", "getAccelerate()Z", 0)), Reflection.property1(new PropertyReference1Impl(EntitySpeed.class, "accelerateFactor", "getAccelerateFactor()F", 0)), Reflection.property1(new PropertyReference1Impl(EntitySpeed.class, "stopunloaded", "getStopunloaded()Z", 0)), Reflection.property1(new PropertyReference1Impl(EntitySpeed.class, "speed", "getSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(EntitySpeed.class, "timer", "getTimer()F", 0)), Reflection.property1(new PropertyReference1Impl(EntitySpeed.class, "jitter", "getJitter()F", 0))};
        EntitySpeed.delegatedProperties = var29_20;
        EntitySpeed.INSTANCE = new EntitySpeed();
        EntitySpeed.accelerate$delegate = SettingFactory.booleanSetting$default((SettingContainer)EntitySpeed.INSTANCE, "accelerate", true, null, null, 12, null);
        EntitySpeed.accelerateFactor$delegate = SettingFactory.floatSetting$default(EntitySpeed.INSTANCE, "accelerate-factor", 9.0f, RangesKt.rangeTo(0.0f, 20.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        EntitySpeed.stopunloaded$delegate = SettingFactory.booleanSetting$default((SettingContainer)EntitySpeed.INSTANCE, "stop-unloaded", true, null, null, 12, null);
        EntitySpeed.speed$delegate = SettingFactory.floatSetting$default(EntitySpeed.INSTANCE, "speed", 0.77f, RangesKt.rangeTo(0.1f, 5.0f), null, null, 24, null);
        EntitySpeed.timer$delegate = SettingFactory.floatSetting$default(EntitySpeed.INSTANCE, "timer", 1.0f, RangesKt.rangeTo(0.1f, 5.0f), null, null, 24, null);
        EntitySpeed.jitter$delegate = SettingFactory.floatSetting$default(EntitySpeed.INSTANCE, "jitter", 0.05f, RangesKt.rangeTo(0.0f, 0.5f), null, null, 24, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1975;
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
                throw new RuntimeException("su/catlean/up", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            EntitySpeed.e[n2] = EntitySpeed.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = EntitySpeed.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/up" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2334;
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
                throw new RuntimeException("su/catlean/up", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            EntitySpeed.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = EntitySpeed.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/up" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(EntitySpeed.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(EntitySpeed.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

