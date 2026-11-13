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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.reflect.KProperty;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.AutoFish$1;
import su.catlean.module.player.AutoFish$2;
import su.catlean.module.player.AutoFishDetectMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class AutoFish
extends Module {
    @NotNull
    public static final AutoFish INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting detectMode$delegate;
    @NotNull
    private static final BooleanSetting rodSave$delegate;
    @NotNull
    private static final BooleanSetting changeRod$delegate;
    @NotNull
    private static final BooleanSetting legit$delegate;
    private static boolean m;
    @NotNull
    private static final Timer x;
    @NotNull
    private static final Timer X;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AutoFish() {
        super("auto-fish", Categories.getPlayer(), null, null);
    }

    private final AutoFishDetectMode getDetectMode() {
        return (AutoFishDetectMode)((Object)detectMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getRodSave() {
        return (Boolean)rodSave$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getChangeRod() {
        return (Boolean)changeRod$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getLegit() {
        return (Boolean)legit$delegate.getValue(this, delegatedProperties[3]);
    }

    @Override
    public void onDisable(long l) {
        m = false;
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block1: {
            String[] stringArray = new String[]{};
            if (!(receivePacket.getPacket() instanceof PlaySoundS2CPacket) || this.getDetectMode() != AutoFishDetectMode.V1_12_2 || !Intrinsics.areEqual(((PlaySoundS2CPacket)receivePacket.getPacket()).getSound().comp_349(), SoundEvents.ENTITY_FISHING_BOBBER_SPLASH) || Globals.getPlayer().fishHook == null) break block1;
            FishingBobberEntity fishingBobberEntity = Globals.getPlayer().fishHook;
            Intrinsics.checkNotNull(fishingBobberEntity);
            if (fishingBobberEntity.squaredDistanceTo(((PlaySoundS2CPacket)receivePacket.getPacket()).getX(), ((PlaySoundS2CPacket)receivePacket.getPacket()).getY(), ((PlaySoundS2CPacket)receivePacket.getPacket()).getZ()) < 4.0) {
                this.recast();
            }
        }
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x70C6E42F8447L;
        long l2 = l ^ 0x66B59EDFDC38L;
        String[] stringArray = new String[]{};
        if (Globals.getPlayer().getMainHandStack().getItem() instanceof FishingRodItem && Globals.getPlayer().getMainHandStack().getDamage() > 52 && this.getRodSave()) {
            if (this.getChangeRod() && this.findFishingRod() != -1) {
                ModuleMessages.S(this, ModuleMessages.i18n$default(this, "swapped", new Object[0], false, 4, null), false, null);
                InventoryUtil.INSTANCE.selectSlot(this.findFishingRod());
                X.reset();
            } else {
                this.disableWithMessage(ModuleMessages.i18n$default(this, "almost-broken", new Object[0], false, 4, null), l2);
            }
        }
        if (!X.hasPassed(1000)) {
            return;
        }
        if (x.hasPassed(45000) && Globals.getPlayer().getMainHandStack().getItem() instanceof FishingRodItem) {
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            x.reset();
            X.reset();
        }
        if (Globals.getPlayer().fishHook != null && this.getDetectMode() == AutoFishDetectMode.V1_17) {
            FishingBobberEntity fishingBobberEntity = Globals.getPlayer().fishHook;
            Intrinsics.checkNotNull(fishingBobberEntity);
            Boolean bl = fishingBobberEntity.getDataTracker().get(FishingBobberEntity.CAUGHT_FISH);
            Intrinsics.checkNotNullExpressionValue(bl, "get(...)");
            boolean bl2 = bl;
            if (!m && bl2) {
                this.recast();
                m = true;
            } else if (!bl2) {
                m = false;
            }
        }
    }

    private final void recast() {
        String[] stringArray = new String[]{};
        if (this.getLegit()) {
            int n = (int)(Random.Default.nextDouble() * 150.0 + 200.0);
            Async async = Async.INSTANCE;
            int n2 = n;
            async.getExecutor().execute(new AutoFish$2(n2));
            async = Async.INSTANCE;
            n2 = n * 2;
            async.getExecutor().execute(new AutoFish$1(n2));
        } else {
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            x.reset();
        }
    }

    private final int findFishingRod() {
        String[] stringArray = new String[]{};
        for (int i = 0; i < 9; ++i) {
            if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(i).getItem(), Items.FISHING_ROD) || Globals.getPlayer().getInventory().getStack(i).getDamage() >= 52) continue;
            return i;
        }
        return -1;
    }

    public static final Timer M() {
        return x;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoFish.a = ZkmKeys.a(1111780659855604800L, -2920809087298162523L, MethodHandles.lookup().lookupClass()).a(185081670462412L);
                        var20 = AutoFish.a ^ 135709011017036L;
                        AutoFish.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[16];
                        var16_4 = 0;
                        var15_5 = "e\u009bp\u00e6\u00d3\u00972iA\u00f1\u00b7\u00d3\u009c\u001fj\u0099\u00a5\u00fe\u0014\u0095\u000e-\u00ad\u00ea\u0018\u00c4\u00c2q\u00cb1a\u0082:\u0088MJ\u00b7\u00b0\u0002\u00a8\u00d9S\u00ef\u00c3\u0001\u00a4\u00ae9\u00c6 \u0080!\u00f5\u0011z\u0087\u00b8\\J?\u0081^L\u00bd\u00b0]\u00b0R\u00c0\u00efh\u00ceuf/\u0018\u0089\u000e\u00f5\u00ccM\u00b3 \u008f\u0095\u00fas\u00cc>\u00a6\t\u00ac\u00d3\u00ef\u00f1\u00eaK/f\u0002(P\"\u0099\f\u008d\u00d85\u00ddCXX\u007fM\u00be\u0018\u00e2\n0\u00cb\u00b4\u00d1Ad\u009di\u0012@\u001e{\u00af\u00deu\u009b\u00bdp\u00f6\u009bX\u00f5\u0010\u00ac\u00ef\u00b5\u00f8S\u00e2w\u0093\u00e8\u00e3\u00f5\u00b4\u00ce.\u00c5\u00d1\u0010\u008a>\u001a\u008c@a\u0013\u00db\u00f3\u00ad\u00a5\u00ab;H\u00f6\u00d7 \u00bd\u00f8\u0084\u00f9\u008b\u0013\u0003E\u00de\u00fco\u00d7\u00ae\u00a2\u0003~\u0080\u0080?\u0088\u00faV\u00116)\u0006\u00ba\fs\u00b1\u00a1\u009f 9\u0019\u00ec\u00e9\u00a9\u00af\u00af\u009f\u009da\u00d5^\u00a2e\u00bf\u00da\u00cf\u00feLAX\u00d9\u009e\u00e5s\u00f4\u00c2\u00d6\u009a]\u00ae\u00ed\u0010\u00c25\u00ac\t\u008c\u000f\u00f5\r\u000f\u00c58\u00dao\u0096\u0015\u00d0 <\u0087\u0095*\u0014\u0013\u00f6,L\u00e3\u0087\u00b0\u0094\u0015W\u00e4G\u0016\u00c5\u0015\u0095#B\f\u0087\u0080\u00b4;X{\u00fb\u00df0\u0001\u0083\u00f5\u00bd\u00cf5\u0088\u000e\u0098\u009c\u00f6\u00f6\u0001H\u00c9V/3\u00de\u00dd|\u00b9b(\u0095\u00b2Hg\u0088\u00c0\u00a7\u00e3j/\u00a0ZW\u000fr\u00bf\u00d9\u0080\u00f4\u001aij*\u00b9 %\u00a9\u00b0\u0015\u001d&\u00d7\u00faJX\u0007\fm\u00e9\u0019G6\u009eA<\u008e~+\u00e2\u00fc@:9\u00e6.\u00ef\u00c1 \u00b4\u00f9\u00de\u008c\u00fc\u00c6\u001a\u00ea4\u00d0\u00ae\b\u001fM\u009d\u00ea\u001d\u00fb\u0006\u000eo\u00a2\u00c3\u0011\u009e\u00a8z\u00a0\u00c6\u00a2\u00ffK";
                        var17_6 = 405;
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
                            var18_3[var16_4++] = AutoFish.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00fb\u00f6\u00c5w\u00df\u00bf\u001cu\u00a23\u0095\u00d7\u00b6,\u00a2J \u00ff\u009a\u00df\u00d8\u0014a\u0098\u00ae+`]\u00dc\u0097\u00d6\u001f\u0095\u00a2\u0098\u00e4\u009bS\u0018\u0086\u00a7\u008a%[\u00b1]9\u00c3\u00ce";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = AutoFish.zkmDecodeUtf8(var19_9).intern();
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
                AutoFish.b = var18_3;
                AutoFish.c = new String[16];
                AutoFish.g = new HashMap<K, V>(13);
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
                var4_14 = "0c\nE)\u0097x\u00014\u00c9\u0099\u00eakIX\u00cc%\u00f5\n\u00ab\\\u0017\u0088#~\u0089Ky\u00b7\r\u00fc\u001f\u00af\u0017\u008a\u00d05\u00ca\u00de#g\u00f3\u00e7\u0006,\u0011\u00f6\u00b0.a\u0082\u00bdP\u0099\u00f2\u00e9";
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
                    var4_14 = "\u0095(\u00b2\u00bb\u00a9\u00f9\u00df\u00b2H\u001cn\u00a3\u00e9\u00cc\u00b0\u0013";
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
        AutoFish.e = var6_12;
        AutoFish.f = new Integer[9];
        var28_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoFish.class, "detectMode", "getDetectMode()Lsu/catlean/bh;", 0)), Reflection.property1(new PropertyReference1Impl(AutoFish.class, "rodSave", "getRodSave()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoFish.class, "changeRod", "getChangeRod()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoFish.class, "legit", "getLegit()Z", 0))};
        AutoFish.delegatedProperties = var28_20;
        AutoFish.INSTANCE = new AutoFish();
        AutoFish.detectMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoFish.INSTANCE, "detect-mode", (Enum)AutoFishDetectMode.V1_17, null, null, 12, null);
        AutoFish.rodSave$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoFish.INSTANCE, "rod-save", true, null, null, 12, null);
        AutoFish.changeRod$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoFish.INSTANCE, "change-rod", false, null, null, 12, null);
        AutoFish.legit$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoFish.INSTANCE, "legit", true, null, null, 12, null);
        AutoFish.x = new Timer();
        AutoFish.X = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x199D;
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
                throw new RuntimeException("su/catlean/e7", exception);
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
            AutoFish.c[n2] = AutoFish.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoFish.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2991;
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
                throw new RuntimeException("su/catlean/e7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoFish.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoFish.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoFish.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoFish.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

