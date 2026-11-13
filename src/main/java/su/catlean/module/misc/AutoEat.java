/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.StopUsingItemEvent;
import su.catlean.api.event.events.player.UsingItemEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoEat
extends Module {
    @NotNull
    public static final AutoEat INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting hunger$delegate;
    @NotNull
    private static final BooleanSetting swapBack$delegate;
    @NotNull
    private static final BooleanSetting gapple$delegate;
    @NotNull
    private static final BooleanSetting chorus$delegate;
    @NotNull
    private static final BooleanSetting rottenFlesh$delegate;
    @NotNull
    private static final BooleanSetting spiderEye$delegate;
    @NotNull
    private static final BooleanSetting pufferfish$delegate;
    private static boolean V;
    private static int O;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private AutoEat() {
        super("auto-eat", Categories.getMisc(), null, null);
    }

    private final int getHunger() {
        return ((Number)hunger$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final boolean getSwapBack() {
        return (Boolean)swapBack$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getGapple() {
        return (Boolean)gapple$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getChorus() {
        return (Boolean)chorus$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getRottenFlesh() {
        return (Boolean)rottenFlesh$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getSpiderEye() {
        return (Boolean)spiderEye$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getPufferfish() {
        return (Boolean)pufferfish$delegate.getValue(this, delegatedProperties[6]);
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        V = false;
        if (Globals.getPlayer().getHungerManager().getFoodLevel() <= this.getHunger()) {
            boolean bl;
            boolean bl2 = bl = !this.isHoldingFood(Hand.MAIN_HAND) && !this.isHoldingFood(Hand.OFF_HAND) ? this.findFood() : true;
            if (bl) {
                this.eat();
            }
        } else if (O != -1 && this.getSwapBack()) {
            InventoryUtil.INSTANCE.selectSlot(O);
            O = -1;
        }
    }

    private final void eat() {
        if (!Globals.getPlayer().isUsingItem()) {
            InteractionUtil.useItem$default(this.isHoldingFood(Hand.OFF_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        }
        V = true;
    }

    private final boolean findFood() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(i), "getItem(...)");
            if (!itemStack.getComponents().contains(DataComponentTypes.FOOD) || !this.getGapple() && (itemStack.getItem() == Items.GOLDEN_APPLE || itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) || !this.getChorus() && itemStack.getItem() == Items.CHORUS_FRUIT || !this.getRottenFlesh() && itemStack.getItem() == Items.ROTTEN_FLESH || !this.getSpiderEye() && itemStack.getItem() == Items.SPIDER_EYE || !this.getPufferfish() && itemStack.getItem() == Items.PUFFERFISH) continue;
            O = Globals.getPlayer().getInventory().getSelectedSlot();
            InventoryUtil.INSTANCE.selectSlot(i);
            return true;
        }
        return false;
    }

    private final boolean isHoldingFood(Hand hand) {
        Object object = hand == Hand.MAIN_HAND ? Globals.getPlayer().getMainHandStack() : Globals.getPlayer().getOffHandStack();
        Intrinsics.checkNotNull(object);
        ItemStack itemStack = object;
        Item item = itemStack.getItem();
        Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
        object = item;
        return !(!itemStack.getComponents().contains(DataComponentTypes.FOOD) || !this.getGapple() && (object == Items.GOLDEN_APPLE || object == Items.ENCHANTED_GOLDEN_APPLE) || !this.getChorus() && object == Items.CHORUS_FRUIT || !this.getRottenFlesh() && object == Items.ROTTEN_FLESH || !this.getSpiderEye() && object == Items.SPIDER_EYE || !this.getPufferfish() && object == Items.PUFFERFISH);
    }

    @Flow
    private final void onUsingItem(UsingItemEvent usingItemEvent) {
        block0: {
            if (!V) break block0;
            usingItemEvent.cancel();
        }
    }

    @Flow
    private final void onStopUsingItem(StopUsingItemEvent stopUsingItemEvent) {
        block0: {
            if (!V) break block0;
            stopUsingItemEvent.cancel();
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
                        AutoEat.a = ZkmKeys.a(-6054961735656640193L, 4709577558979737275L, MethodHandles.lookup().lookupClass()).a(50406202715930L);
                        var20 = AutoEat.a ^ 31147944461090L;
                        AutoEat.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[24];
                        var16_4 = 0;
                        var15_5 = "y8\u0086?\u00e9\u0012_\u0001\u00c3\u00b8G\u00a6\u0082\u0017HU\u0018\u00d4\u00b7\t<e\u00c6;\u00af\u0092\u00a3r\u00c5\u0003\u00ecZ\u001e\u0013\u00cb!\u00f2\u00d9(=\u00af  d;\u00d9\u008a\u00ecPn\u0083\u008b\u00ce\u00f5\u0088`\u0015&\u00d3\u009a;(\u00b5;\u0084\u00ebHY\u007f\u00f3\u00b7v\u0083\u000f \u00f3\u00f6\u00a2\u00ee\u00f1\u00d6\u0085\u00db\u0090\u00ef\u0082\u008ex\u00d4LW\u00c9Z!\u00e9\u00bf\u00eb\u0014\u0084as\u0019\u00ce\u001d\u001a\\\u008e \u008a\u008b^G\u00b9\u00f3b\u001e\u00a8A\u00b0wa5\u00a4\u00b5\u000e\u00d3\u0080\u00e3\u00b6>W\u00ba\u0001\u0094\u00ad\\\u008d<P\u00c2\u00102h2h\u00e4\u008e\u00e5\u008d\u0091-\u0094\u00cd\u00efL:\u0017\u0010\u00ac\u007f\u00f6\u00a8\u001a\u0093\u0097c\u00fb\u001dRX\u00c0\u00a7'\u00ae\u0018(6d}\u00b8\u00eb\u00e8@\u0005\u00b2\u008a\u00acl\u009d\u001e5\u009cS\u00e9'\u00daT\u00e74(\u00b1u\u00dc\u00f9Ek\u0006\u00a9\u009bHD\u00a6!\u0080\u00a3\u00c2\u00df~\u0088\u00f5\u00cd\n\u00aay\u0015\u00ae=t6\u007f\u0089\u001f\u00c5(%\u00dc;\u000b0} \u00e4\u00e2\u00a3\u00e6*\\X}\u0016\u009b\u00d9nQ\u00f3q\u00bc\u00d7\u00d38\u00efP\t/\u00c5\u0016l\u00df\u00e5s\u008cp\u0094\u0018(C\u0080\u00c4\u00e4\u0002\u0083L\u0083Z\u00179\u00c4\u0081\u009bAM\u0014\u00d4\u00f1)\u0094\u00ffY\u0018f]3\n\u0081\u0083\b-\u001cx\u00df9\u0005x\u00dcS\u00d2^j\u00e7\"\u00010\u0014 \u00c8\u0015\u0096\u009f\u00af)\u00e3\u001b\"\u008b\u008d\u00d8KF\u00c7\u00c6\u00e5\u00aa{WV\u008c\u0085\u00e1\u00be\u0017\u0086\u00cbO\u00b8\u0016,\u0018\u00d1k\u00f3l\u00d4Z\u00d7\u0099H\u009ds\u00f5|@F\u00fe\u00b6I \u009a\u00b8|\u00f1\u00b6\u0010\u0017>\u00d9\u00b6\u000f\u0089\u00e2\u00a6\u0084\u00c5b@\\\u008d,/ !\u0089\u00a3N\u00aa\u00ba\u0097\u00a2\u0097@h\u00da\u0083v<\u00e8\u00ad\u00e1R\t\f=\u00d2s\u00c8F\u00f1\u00dab\u0002\u0019\u0016 *0\u0089\u00c0\\\u0081\u00a8\u00bb4\u00b428q\u00c2\u0019\u000e\u00aa\u00dfU\u00b7v\u0085\u00f2\u009c+o\u00beb\u0000\u0095\u00a6n\u0010\u00f2\u00ed4\u00e0\u00ece\u00db!3\u0018\u00a6\u00efF'\u00cbO\u0010D6\u00c1\u008a\\=s\u0007$\u00cf\u00d1\u00e7\u00b4Q\u0017; P\u0095\bE\u00ac\u00d6y9f\u00af\u0003}\u00d4\u0095,\u00ca\u0081|\u00cd\u0084\u00c2\u00a15[i`.\u001e4<\u001e\u00ad \u00f7\u00fa(\u0094\u00b7\u001ea\u009a\u00b3\u00f6A\u00c9\u00d4\u00ac\u0098\u00e5\u00b5\u00e1\u000e\u00d5\u00f6\u0091Vt}\u00a1\\\u00b8F\u00d5\u0013\u00e0 \u001c\u0088\u00a0\u00dd\u0094\u00e4\u0097\u0002=\u009eW\u00b4u\u00dfQ\u00d2{`H\u00e4\u0095\u00d6\u0089\u00be\u00ed \u0089\u008f\u00b9\u007f\u0018i";
                        var17_6 = 597;
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
                            var18_3[var16_4++] = AutoEat.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u008a:w\u00f5W#\u00ab\u009c\u00a4\u00c0\u0082V\u0099\"U\u0082a\u00c6l\u00e9\u000b]\u0092A+\u00d1\u00b2n\u00d0\u00d7\u00e8\u0084\u0090\u0091\u0005\u00eepv\u00e2m\u0018\u00ef\u0018A8Q\u0010\u0092\u00dfl\u00be\u0006\u00cbE\u00a7\u00f8\u0083\u00ca\u00a0s\u00b7\u009f\u00a2\u00fb\u00d5";
                            var17_6 = 65;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = AutoEat.zkmDecodeUtf8(var19_9).intern();
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
                AutoEat.b = var18_3;
                AutoEat.c = new String[24];
                AutoEat.h = new HashMap<K, V>(13);
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
                var4_14 = "\u00e2\u000e\u00afr->I\u0089\u00d6\u00f4\t\u0095\u00b3(\f\u00db\u0090\f\u0090/\u0099-\u00cemv\u00e7\\\u00dd\u00c0d\u001d<YV\u00d7\u0098Z\u00e0\u00d6\u00ffD\u00cc>{\u0099\u0015T>o\u00d4^\u000em\u00f1\u00edD";
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
                    var4_14 = "\u001b\u00cc\u0086\u00d3\u00dcc+\u00178\u0097:\u000fD)O\u00c8";
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
        AutoEat.e = var6_12;
        AutoEat.f = new Integer[9];
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoEat.class, "hunger", "getHunger()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoEat.class, "swapBack", "getSwapBack()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoEat.class, "gapple", "getGapple()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoEat.class, "chorus", "getChorus()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoEat.class, "rottenFlesh", "getRottenFlesh()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoEat.class, "spiderEye", "getSpiderEye()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoEat.class, "pufferfish", "getPufferfish()Z", 0))};
        AutoEat.delegatedProperties = var28_20;
        AutoEat.INSTANCE = new AutoEat();
        AutoEat.hunger$delegate = SettingFactory.intSetting$default(AutoEat.INSTANCE, "Hunger", 8, new IntRange(0, 20), null, null, 24, null);
        AutoEat.swapBack$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoEat.INSTANCE, "swap-back", true, null, null, 12, null);
        AutoEat.gapple$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoEat.INSTANCE, "gapple", true, null, null, 12, null);
        AutoEat.chorus$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoEat.INSTANCE, "chorus", false, null, null, 12, null);
        AutoEat.rottenFlesh$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoEat.INSTANCE, "rotten-flesh", false, null, null, 12, null);
        AutoEat.spiderEye$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoEat.INSTANCE, "spider-eye", false, null, null, 12, null);
        AutoEat.pufferfish$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoEat.INSTANCE, "puffer-fish", false, null, null, 12, null);
        AutoEat.O = -1;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x342C;
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
                throw new RuntimeException("su/catlean/el", exception);
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
            AutoEat.c[n2] = AutoEat.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoEat.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/el" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4623;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/el", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoEat.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoEat.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/el" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoEat.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoEat.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

