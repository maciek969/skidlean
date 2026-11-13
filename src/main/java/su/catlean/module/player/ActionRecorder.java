/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ButtonSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.ActionHolder;
import su.catlean.util.Globals;
import su.catlean.util.MovementInput;
import su.catlean.zkm.ZkmKeys;

public final class ActionRecorder
extends Module {
    @NotNull
    public static final ActionRecorder INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ButtonSetting recordButton$delegate;
    @NotNull
    private static final ButtonSetting button$delegate;
    @NotNull
    private static final ButtonSetting reset$delegate;
    @NotNull
    private static final BooleanSetting rewindOnDone$delegate;
    @NotNull
    private static final Map j;
    private static boolean d;
    private static boolean C;
    private static int a;
    private static boolean E;
    private static final long b;
    private static final String[] e;
    private static final String[] f;
    private static final Map h;

    private ActionRecorder() {
        super("action-recorder", Categories.getPlayer(), null, null);
    }

    private final ActionHolder getRecordButton() {
        return (ActionHolder)recordButton$delegate.getValue(this, delegatedProperties[0]);
    }

    private final ActionHolder getButton() {
        return (ActionHolder)button$delegate.getValue(this, delegatedProperties[1]);
    }

    private final ActionHolder getReset() {
        return (ActionHolder)reset$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getRewindOnDone() {
        return (Boolean)rewindOnDone$delegate.getValue(this, delegatedProperties[3]);
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        if (!this.isInGame()) {
            return;
        }
        if (d) {
            j.put(a, new MovementInput(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), Globals.getMc().options.forwardKey.isPressed(), Globals.getMc().options.backKey.isPressed(), Globals.getMc().options.leftKey.isPressed(), Globals.getMc().options.rightKey.isPressed(), Globals.getMc().options.sneakKey.isPressed(), Globals.getMc().options.jumpKey.isPressed(), Globals.getMc().options.useKey.isPressed(), Globals.getMc().options.attackKey.isPressed()));
            int n = a;
            a = n + 1;
        }
        if (C) {
            MovementInput movementInput;
            if (a >= j.size()) {
                E = true;
                a = j.size();
            }
            if (a < 0) {
                E = false;
                a = 0;
            }
            if ((movementInput = (MovementInput)j.get(a)) == null) {
                this.playback();
                return;
            }
            Globals.getPlayer().rotate(movementInput.getYaw(), false, movementInput.getPitch(), false);
            Globals.getMc().options.forwardKey.setPressed(movementInput.isForward());
            Globals.getMc().options.backKey.setPressed(movementInput.isBack());
            Globals.getMc().options.leftKey.setPressed(movementInput.isLeft());
            Globals.getMc().options.rightKey.setPressed(movementInput.isRight());
            Globals.getMc().options.sneakKey.setPressed(movementInput.isSneak());
            Globals.getMc().options.jumpKey.setPressed(movementInput.isJump());
            Globals.getMc().options.useKey.setPressed(movementInput.isUse());
            Globals.getMc().options.attackKey.setPressed(movementInput.isAttack());
            this.playback();
        }
    }

    private final void playback() {
        if (this.getRewindOnDone()) {
            if (E) {
                int n = a;
                a = n + -1;
            } else {
                int n = a;
                a = n + 1;
            }
        } else {
            int n = a;
            a = n + 1;
            if (E) {
                a = 0;
                E = false;
            }
        }
    }

    private static final Unit _clinit_$lambda$0() {
        boolean bl = d = !d;
        if (d) {
            a = 0;
        }
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible() {
        return !C;
    }

    private static final Unit _clinit_$lambda$2() {
        boolean bl = C = !C;
        if (C) {
            a = 0;
        }
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible2() {
        return !d;
    }

    private static final Unit _clinit_$lambda$4() {
        j.clear();
        d = false;
        C = false;
        a = 0;
        E = false;
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        ActionRecorder.b = ZkmKeys.a(7468643730345306145L, -6706891590298238627L, MethodHandles.lookup().lookupClass()).a(49911007672534L);
                        var20 = ActionRecorder.b ^ 61125639767691L;
                        ActionRecorder.h = new HashMap<K, V>(13);
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
                        var15_5 = "\u00c2\u00c7\u00af\u00ae\u00fc\u00db#\u00a4\u0015\u00dc\u0010\u0083Lw\u008c_ 49\u0003\u00ff\u00d7\u00bd[\t\u00f67\"5\u00d6\u0082\u00f5\u00d6%\u00022\u00aa\u000e\u00b2\u00f9o\u001c\u009b\u00c9\u0088(q\b\u00e0 ||-\t\u0085\u00f0Z\u00c8\u00a1I\u001c\u00a8mha\u00a2{e\u00e7\u00d7w\u00dd\u00c5\u00f7\u0019o)\bb\u00a3TK B8P\u00e0T\u000b\u0090\u00ed\u0091g\u001f\u00af\u00bf\u00cf7\u00d5fS\u00bc\u00fb\u00b27r\u00b9\u0018\u00a0\u0095x\u00ed\u000b\u007fG\u0010\u008f\u00b0\u0084\u0011\u0006\u00f4h\u0089W@\u00e5H\u00950\u00ac\u00ff8\tS\u00ad\u00e4*O3M\u001fC\u00a0\u00d1E\u00aa^\u0087\u00b4y%Z\u00c56S\u0095\u008cZ\u00c5G`\u0016\u0081\u0093b\u00d7\u0096\u0017W\u0085&k\u00f7E\u00e0\u0099\u000b?u\u0091\u009e\u0094\u00b6\u00ae\u00b1\u00f6W\u00df($\u0096\u00dd\\\u009fl6\u00ebz\u00dd\u0088\u0013\u008a\u00af\u0098\u0081\u00d3C\u00d4\u00b0\u00d2\u0015\u00c2\u00ee\n\u008e\u00b6{p\u00c8,?H\u001a\u009b\u00a7\u001eo\\\u0012\u0010\u00a3\u001d\u00ca\u00896\u00ae\u00c0H\u0080\t\u0012B2\u00e4g\u00f2@A\u00ec\u00a1\u0089\u0019_V\u00db\u00be%`=\u00d2A~\u0012\u00be\u001a\u0013\u00d100\u00a3\u001c\u0011\u00e1\u00a1B\u00e8\u00cd\u009c\u001e\u00c2A\u0017\u00de\u00c4\u00f6\u0007\u00a9\u00cd\\{\u000f\u00a0\u00b1\u00ce\u00b1o\u00a9e\u000f.p\u00c9\u00b1\u00a9\u007f\u00f9\u008bQ\u00a4[?\u0018\u00d9/-M,kT\u0004a\u0011^Im\u00d5\u0011,\u00a5\u0086\u008b\u00c5\u0097u\u008c\u00be86I2\u0014UH-\u00dfO\\\u00da\u00d2\u00f0\u0000\u00e6\u00fe\u008c\u001at\u0091\u00d7\u0088)q\u0088\u00ee|n\u00e2\u008b\u0096A\u00ac\u00df\u0016\u00b0\u001c\u0098PDa\u00a8Y{\u000e\u00ff\u00e0=_\u0015>z\u00f6k\u00ff\t";
                        var17_6 = 394;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ActionRecorder.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e4\u00f1\u00b2\u0092\u0085\u00cd\u009c\u00db~\u00ba\u00ed\u0007\u0095\u00f7?\u00e6\u0089\r_\u00a6\u00d0j\u001f\r\u00cc\u00f1\u0007\u00fb\u00de \u00f1;\u0018\u00cd\u00bd\r\u0010|\u00ed\u009fp\u00ed\u001da\u00b7\u00c9|\u00a7{\u00f5\u00ea\u00fd\u0006\u0019\u00be\u0087\u00a9";
                            var17_6 = 57;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ActionRecorder.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                ActionRecorder.e = var18_3;
                ActionRecorder.f = new String[13];
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
                var0_12 = new long[2];
                var4_13 = 0;
                var5_14 = "9\"\u0088\u0005\u009cV\u00a9\u00ad\u00c9\u00e91\"\u00d3\u00ab\u000b\u0003";
                var6_15 = 16;
                var3_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl72:
                // 1 sources

                while (true) {
                    var0_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var3_16 < var6_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
            v10 = var4_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ActionRecorder.class, "recordButton", "getRecordButton()Lsu/catlean/id;", 0)), Reflection.property1(new PropertyReference1Impl(ActionRecorder.class, "button", "getButton()Lsu/catlean/id;", 0)), Reflection.property1(new PropertyReference1Impl(ActionRecorder.class, "reset", "getReset()Lsu/catlean/id;", 0)), Reflection.property1(new PropertyReference1Impl(ActionRecorder.class, "rewindOnDone", "getRewindOnDone()Z", 0))};
        ActionRecorder.delegatedProperties = var28_20;
        ActionRecorder.INSTANCE = new ActionRecorder();
        ActionRecorder.recordButton$delegate = SettingFactory.buttonSetting$default(ActionRecorder.INSTANCE, "record-/-stop", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        ActionRecorder.button$delegate = SettingFactory.buttonSetting$default(ActionRecorder.INSTANCE, "play-/-stop", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$2(), ()Lkotlin/Unit;)(), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        ActionRecorder.reset$delegate = SettingFactory.buttonSetting$default(ActionRecorder.INSTANCE, "reset", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$4(), ()Lkotlin/Unit;)(), null, null, (int)var0_12[0], null);
        ActionRecorder.rewindOnDone$delegate = SettingFactory.booleanSetting$default((SettingContainer)ActionRecorder.INSTANCE, "rewind-on-done", false, null, null, (int)var0_12[1], null);
        ActionRecorder.j = new LinkedHashMap<K, V>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x539E;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/et", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            ActionRecorder.f[n2] = ActionRecorder.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ActionRecorder.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/et" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ActionRecorder.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

