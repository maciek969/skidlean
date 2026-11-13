/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.legit;

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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.rotation.DefaultRotationHandler;
import su.catlean.zkm.ZkmKeys;

public final class Trigger
extends Module {
    @NotNull
    public static final Trigger INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting attackRange$delegate;
    @NotNull
    private static final IntSetting reactVal$delegate;
    @NotNull
    private static final BooleanSetting whileAttackPressed$delegate;
    @NotNull
    private static final BooleanSetting autoJump$delegate;
    @NotNull
    private static final BooleanSetting ignoreWalls$delegate;
    @NotNull
    private static final BooleanSetting pauseEating$delegate;
    private static int u;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;

    private Trigger() {
        super("trigger", Categories.getLegit(), null, null);
    }

    private final float getAttackRange() {
        return ((Number)attackRange$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final int getReactVal() {
        return ((Number)reactVal$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final boolean getWhileAttackPressed() {
        return (Boolean)whileAttackPressed$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getAutoJump() {
        return (Boolean)autoJump$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getIgnoreWalls() {
        return (Boolean)ignoreWalls$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getPauseEating() {
        return (Boolean)pauseEating$delegate.getValue(this, delegatedProperties[5]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Entity entity;
        long l = a ^ 0x1F0C1CF97BC7L;
        long l2 = l ^ 0x1FD3031EAABFL;
        if (this.getWhileAttackPressed() && !Globals.getMc().options.attackKey.isPressed()) {
            return;
        }
        if (Globals.getPlayer().isUsingItem() && this.getPauseEating()) {
            return;
        }
        if (!Globals.getMc().options.jumpKey.isPressed() && Globals.getPlayer().isOnGround() && this.getAutoJump()) {
            Globals.getPlayer().jump();
        }
        if (!((entity = MovementUtil.raycastEntity$default(MovementUtil.INSTANCE, Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), this.getAttackRange(), this.getIgnoreWalls(), false, null)) == null || entity instanceof PlayerEntity && Friends.getIsHomie((PlayerEntity)entity))) {
            if (u >= this.getReactVal() && DefaultRotationHandler.INSTANCE.z(l2)) {
                Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entity);
                Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            }
            int n = u;
            u = n + 1;
        } else {
            u = 0;
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
                        Trigger.a = ZkmKeys.a(-3586837753753182526L, 2640682371630337351L, MethodHandles.lookup().lookupClass()).a(177743244593320L);
                        var20 = Trigger.a ^ 90382778098170L;
                        Trigger.e = new HashMap<K, V>(13);
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
                        var15_5 = "\u00e84H\u00d7\u00b2N\u0005w\u00be\u00bc\u00a05\u008a\u009c\u00aeZ\u0097\u00d5\u00d6\u00fcvX\u00d5b\u00c2\u0014\u00a93w1V'\u0018\u00d9\u00eb\u0086\u00f8L\u00b1\u0084y\"yL\u0017\u00c3\u00b9r\u00f5\u008b\u00ee\u0080\u00deu\u00ab)\u000f(\u00d6\u0018^\u0004\u001b \u00b9\u00fe\tZ\u009a\u00dc\u0086\u00d9w\u001ar^D\u00af\u00c3\r\u0083\f\"\u000fO\u00d0\u008bftK\u0089\u00e2-J\u00b9K\u00ed&8\u00ba^\u00d4\u00f2\u008f\u0083\f2\u00ab\u0087\u00d6\u00db\u00af\u00b2\u00bdg:C\u00de\u0085n!\u001aU}r<\u001c\u00b6x\u00ef\u00e6\u00d9\u00db\u00f1\u0013\u00b5+\u0084\u00f4\u00eb#\u00ea\u00df\u00c4l\u00de\u00e9\u00ffS\u00ae\u00b1\u00dc\u00ee2\u009e\u0010\u0001\u00b8\n\u0083\u00d0\u00c7\u00c0\u0082\u00be:\u00d3\u00d2\u00eb\u008d\u00d0\u00c8\u0010\"\u00d6\u0000\u00e1\u0082\u00dd=Yr,7YUF\u00b6A \u008c\u00b3e\u00e9:\u00d4\u00cd_\u00fd\u009f\u008e\u00bf\u0003\u00a7\u0006:\u00b8\u00f8\u00ae\u00e0\u0007\n\u00b5\\E[?\u00e5\u00b0\u0001_\u00d4(\u009b\u00bdwF\u00b9\u00fb\u0095\u00c5\u00c5\u00ba5M\u00a3\u001e\u00e8\u00cb\u00d5\u00e4\u00d1\u00dc\u0080\u001aYN\u00fa\u0098\u00cb\u00ff.\\x\u00b3\u00deY\u0090\f\u00db0yQ(\u0016\u0083\u001d#\u0004w\u0012E#\u008e\u00d3\u00a3\u0017\u00d4\u0010J\u00f9eX\u00f9\u0085\u00a5\u00a9\u0004\u00aeoB5zh[6\u00fc\u008d\\\u00cd\u00ed\u00fb\u00e7\u001a(\u0093\u00bd\u00ab\u00dcS\b\u009bw\u00df\u00d2\u00e0V\u008f\u000e\u001f\u00f6a\u001a+\u00b9J\u000f\u001a\u0091\"\u00b2\u00e5\u00b8\u009a\u0082\u00bb\u00fb\u0004L\u00fd\u0081\u00fc\u00ee\u00d980\u00d5\u0095'\u00ca\u00dbs\u009d\u0017\u00fe\u0006N\u009fDZM~\u0082\u00a8\u00ec`\u00ec\u00f6\u0094w\u00a0\u00cbS\u0088\u00859.\u00cf\u00e5\u0086[\u0007\u0014\u0018\u00e9\u00bb\u00da\f\u0085u\b\u00f7\u0016;\u0018hI\u00fe)\"\u00b6). $]\u00db\u00ef\u00da\u00ad\u00d44d\u008d\"\u00f0\u00ec\u001f\u00b2(\u0092D/<l\u00c7\u00b9\u0093X\u00cd\u00eb?\u00df\u00ea\u0004o>\u009f\u00dc,\u0086\"\u00dfwb\u009b\u00e6d\u00fa\u0015\u0002\u0017\u00a3\u00bd\u00f1\u008e|28\u00a5\u0018g\u00c4-\u008dtI\u008fip\u001a\u00ae\u0002\u00dd\u009a]\u008aM\u00e7\u00d2\u0095G$\u00b9A \u0001\u00c2\u0011\u00f9\u00c6O;\u0095S\u0090\u00a3W\u00ba\u00c6\u0011e/4\u00eaq\u00a2v\u00ddd\u008dO\u00cadi><\u00b6 \u00fccG4c#\u00c0x\u00a3\u001e0\u009cOb\u00adJFv\u00e5Z'\u001c\u008b\u008f,\u00a1\u00b4hTl\u00bc\u00ba\u0018\u00f9\u0018\u001b%\u000fx\u009a\u00e9|b\u00b1\u00ebe\u00fd\u00d98\u00ea\u00ec\u008a\u00a8JJL\u0019";
                        var17_6 = 576;
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
                            var18_3[var16_4++] = Trigger.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "J\u000f\u00c8f\u00ed6\u000f\u00c0\u0098\u00e5M y\u0002R\\\u00fe\u00bf\u0096\u00ea\u00c4\u00d7\u00f2\u00d1\u0018\u0011W,^\u00a7\u00c5\u0086\u0019\u0093e\u00b4\u0013\u00b7\u00c1\u00df\f\u000b\u000b%f\u00d3\u00e8\r)";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = Trigger.zkmDecodeUtf8(var19_9).intern();
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
                Trigger.b = var18_3;
                Trigger.d = new String[19];
                Trigger.i = new HashMap<K, V>(13);
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
                var4_14 = "\u00e4\u00b3L\u0017\u00aa\u00a1\u00c6\u00f9^\u00f1\u00c1\u00fc\u008aA\u00fak\u0000\u00d0\u00e9V8]\u0010}u\u00a1\u008fL?5\u00e7;\u00ed\u009e\u00d5\u00f65\u00b2x\u0007";
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
                    var4_14 = "\u00b3\u00e5\u00c2T\u00d98O\u00e1Z\u00a4w\u00e5?\u00ed)\u0084";
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
        Trigger.f = var6_12;
        Trigger.g = new Integer[7];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Trigger.class, "attackRange", "getAttackRange()F", 0)), Reflection.property1(new PropertyReference1Impl(Trigger.class, "reactVal", "getReactVal()I", 0)), Reflection.property1(new PropertyReference1Impl(Trigger.class, "whileAttackPressed", "getWhileAttackPressed()Z", 0)), Reflection.property1(new PropertyReference1Impl(Trigger.class, "autoJump", "getAutoJump()Z", 0)), Reflection.property1(new PropertyReference1Impl(Trigger.class, "ignoreWalls", "getIgnoreWalls()Z", 0)), Reflection.property1(new PropertyReference1Impl(Trigger.class, "pauseEating", "getPauseEating()Z", 0))};
        Trigger.delegatedProperties = var30_20;
        Trigger.INSTANCE = new Trigger();
        Trigger.attackRange$delegate = SettingFactory.floatSetting$default(Trigger.INSTANCE, "range", 3.0f, RangesKt.rangeTo(1.0f, 7.0f), null, null, 24, null);
        Trigger.reactVal$delegate = SettingFactory.intSetting$default(Trigger.INSTANCE, "react-val", 1, new IntRange(0, 10), null, null, 24, null);
        Trigger.whileAttackPressed$delegate = SettingFactory.booleanSetting$default((SettingContainer)Trigger.INSTANCE, "while-attack-pressed", false, null, null, 12, null);
        Trigger.autoJump$delegate = SettingFactory.booleanSetting$default((SettingContainer)Trigger.INSTANCE, "auto-jump", false, null, null, 12, null);
        Trigger.ignoreWalls$delegate = SettingFactory.booleanSetting$default((SettingContainer)Trigger.INSTANCE, "ignore-walls", false, null, null, 12, null);
        Trigger.pauseEating$delegate = SettingFactory.booleanSetting$default((SettingContainer)Trigger.INSTANCE, "pause-while-eating", false, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2698;
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
                throw new RuntimeException("su/catlean/qt", exception);
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
            Trigger.d[n2] = Trigger.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Trigger.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4949;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/qt", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Trigger.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Trigger.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Trigger.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Trigger.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

