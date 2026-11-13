/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.player.ClickSlotEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.MouseHandlerAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ItemScroller
extends Module {
    @NotNull
    public static final ItemScroller INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting delay$delegate;
    private static boolean j;
    private static boolean F;
    @NotNull
    private static final Timer scrollTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private ItemScroller() {
        super("item-scroller", Categories.getEquip(), null, null);
    }

    public final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    public final void setDelay(int n) {
        block0: {
            int n2 = 0;
            long l = 0L;
            long l2 = ((long)n2 << 56 | l << 8 >>> 8) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1318175972993829949L, (long)l2);
            delay$delegate.setValue(this, delegatedProperties[0], n);
            if (callSite == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[1], (long)1297474293918931004L, (long)l2);
        }
    }

    @Flow
    private final void onClickSlot(ClickSlotEvent clickSlotEvent) {
        int n;
        long l = a ^ 0x3821CCDED040L;
        int n2 = (int)((l ^ 0x5C7B87888CBFL) << 48 >>> 48);
        if ((KeyboardUtil.isKeyPressed((char)n2, 340) || KeyboardUtil.isKeyPressed((char)n2, 344)) && (KeyboardUtil.isKeyPressed((char)n2, 341) || KeyboardUtil.isKeyPressed((char)n2, 345)) && clickSlotEvent.getAction() == SlotActionType.THROW && !F) {
            Item item = Globals.getPlayer().currentScreenHandler.slots.get(clickSlotEvent.getId()).getStack().getItem();
            Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
            Item item2 = item;
            F = true;
            int n3 = Globals.getPlayer().currentScreenHandler.slots.size();
            for (n = 0; n < n3; ++n) {
                if (!Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.slots.get(n).getStack().getItem(), item2)) continue;
                InteractionUtil.clickSlot(n, 1, SlotActionType.THROW, false);
            }
            F = false;
        }
        if ((KeyboardUtil.isKeyPressed((char)n2, 342) || KeyboardUtil.isKeyPressed((char)n2, 346)) && (KeyboardUtil.isKeyPressed((char)n2, 341) || KeyboardUtil.isKeyPressed((char)n2, 345)) && clickSlotEvent.getAction() == SlotActionType.THROW && !F) {
            F = true;
            n = Globals.getPlayer().currentScreenHandler.slots.size();
            for (int i = 0; i < n; ++i) {
                InteractionUtil.clickSlot(i, 1, SlotActionType.THROW, false);
            }
            F = false;
        }
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        if (inputEvent.getDevice() == InputEvent.Device.Mouse && inputEvent.getKey() == 0) {
            j = inputEvent.getAction() == InputEvent.Action.Press;
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = a ^ 0x61D75540E605L;
        int n = (int)((l ^ 0x58D1E16BAFAL) << 48 >>> 48);
        if (Globals.getMc().currentScreen != null && j && KeyboardUtil.isKeyPressed((char)n, 340) && scrollTimer.hasPassed(this.getDelay() + (int)(Random.Default.nextDouble() * 10.0))) {
            Mouse mouse = Globals.getMc().mouse;
            Intrinsics.checkNotNull(mouse, "null cannot be cast to non-null type su.catlean.mixins.accessors.MouseHandlerAccessor");
            ((MouseHandlerAccessor)((Object)mouse)).iMouseClick(Globals.getMc().getWindow().getHandle(), new MouseInput(0, 0), 0);
            Mouse mouse2 = Globals.getMc().mouse;
            Intrinsics.checkNotNull(mouse2, "null cannot be cast to non-null type su.catlean.mixins.accessors.MouseHandlerAccessor");
            ((MouseHandlerAccessor)((Object)mouse2)).iMouseClick(Globals.getMc().getWindow().getHandle(), new MouseInput(0, 1), 1);
            scrollTimer.reset();
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
                        ItemScroller.a = ZkmKeys.a(8720139854541951830L, -2801547820795434101L, MethodHandles.lookup().lookupClass()).a(192110415536035L);
                        var20 = ItemScroller.a ^ 12600350576545L;
                        ItemScroller.d = new HashMap<K, V>(13);
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
                        var15_5 = "\u000eU#R\u00a6\u0000\u00e3'\"\r\u001b\u00de\u000b%\u00eb\u00a6\u00c7\u00e1\u00bd \u0081.i\f\u00f1~\u009c\u001c\u0014\u00fc\u00b6$\u0010c\u001f\u0085-n\u009b\u00f9$\u007fi\u00ab/\u00e0\u0000\u0099U Y$\u0085_o\u00f5\u0000\u008fg\u00e4\u00dd\u00a0\u00bef\u00ec\u001a\r\u00cb\u0088\u001a\u00b2(\u00a1\u0003\u0010\u001c,\u00b5\u00ab\u00c4x\\\u0010Z\u00cc\u00c30,\u001dl\u00102\u00ed?\u00878\u008d\u00c05\u0090(\u00d25\u00c3\u001e\u00ab\u00fa\u00a6\u008boH\u00ab\u00af\u0017/-!\u009evY\u00b5$\u00ed\u00bb=\u00a8\u00de\u00e71\u00bfa\u00cb\u00aee\u00b0\u00e3\u00ad\u00cf`\u00df\u0094\u00c3\u00e5\u00f3%\u009b\u00ca\u0000\u00be\u00ce\u00b8h\u00d8\u00ebDw\u00b5\u00ff\u0007A\"\u00bf/}\u0086\u00f6\u001b\u009fH\u00c2RT\u00fd\u00e9\u001d\u00df;\u00a0\u00d4\u00ed\u0018N1+\n\u008b\u00ac\\\u00f2\u00a2\u0088q\u0082\u00db\u00f21\u00b4\u009f\u0000F:(x\u00c4\u0001\u0090V\u00f4+R\u00b8\u0094}\u00ef\u00f4#-\r\u00d4\u00b7\u00da\u00ac\u00d3\u001c\u0012\u00a0\u00ed\u00c0f\u0089 \u00d7\u00f8\u0018\u00f8g1\u00ef0\u0087n\u00d4P\u0098";
                        var17_6 = 244;
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
                            var18_3[var16_4++] = ItemScroller.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a2l\u00d2\u0081\u00ce\u00e4-3C\u00eb\u00b5\u00bax\u00fb+\u00e6E\u00fb\u0084\u00d5\u0019\u00c7j\u00d3~\u00a7\u00e2S\u00ef,\u00db\f\u0088\u00be\u00ba\u00047sOA|\u0091\u00a8\u008c\u00f9o\u009f\u00b6\u00da\u00e1\u00b2x](\u00c9\u0002R\u001c\u00efmn|\u00a90mN\u00e9\u001f\u00ec\u00bb\t%\u00ed\u0004\u00e9U#\u000b\u0003x\u00f5\u0084\u00fd\u0098\u00d2\u0013\b\f\u00f6\u00a1S\u00ab\u00a5\u00b5/\u00ac\u00b8\u008d0\u008a|\u00dak\u00c2\u0011W\u00b3\u00c8\u0083\u00b0Ed#\u00b5y\u00a4Q\u008e\u00bb\u00d2\u00fd\u00d9E\u00d6t\u00c2\u0092\u00b4t\u00b7\u0006\u0084\u00cf,r\u00de\u00f9\u00fe\u00a4\u00f4\u00b7:\u00d8\u00f1\u00a4\u00cf\u00d3D\u00a2=\u00b2&Q\u00c7W\u009aK\u001c\u0090lM\u0097\u00e0\u00cf\u0094\u00e2\u00b3\u0089a";
                            var17_6 = 169;
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
                            var18_3[var16_4++] = ItemScroller.zkmDecodeUtf8(var19_9).intern();
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
                ItemScroller.b = var18_3;
                ItemScroller.c = new String[7];
                ItemScroller.g = new HashMap<K, V>(13);
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
                var6_12 = new long[13];
                var3_13 = 0;
                var4_14 = "\u0090\u0095\u001cd@O?(i\u0006\u007f?\u009fT\u0015\u00d0\u009eE\u00a0$\u00bbgd.6\u00fd:Cg7vO\u00e0\u00e5\u0080G\u00eapV\u0006\u008f\u00fcb\u00f7\u00a0\u00f7\u00d1\u000b,~\u00afJL3\u00e6\u00e4R\u008f1R\u00aefxg\u00babf;\u00b7\u00a5\u00fd\u0011(\u00ec\u000f\u00cd<?\u00e27U\u00c1\u00b9\u009d=\u00a5G[";
                var5_15 = 88;
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
                    var4_14 = "\u00abx\u00da\u00c0\u00f4\u001cD\u00063\u00cd\u000b:{e\u0011\u00c5";
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
        ItemScroller.e = var6_12;
        ItemScroller.f = new Integer[13];
        var26_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(ItemScroller.class, "delay", "getDelay()I", 0))};
        ItemScroller.delegatedProperties = var26_20;
        ItemScroller.INSTANCE = new ItemScroller();
        ItemScroller.delay$delegate = SettingFactory.intSetting$default(ItemScroller.INSTANCE, "delay", 75, new IntRange(0, 200), null, null, 24, null);
        ItemScroller.scrollTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x28B6;
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
                throw new RuntimeException("su/catlean/qe", exception);
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
            ItemScroller.c[n2] = ItemScroller.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemScroller.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x541B;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qe", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ItemScroller.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ItemScroller.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemScroller.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ItemScroller.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

