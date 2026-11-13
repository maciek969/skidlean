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
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.MinecraftAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.RandomUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.TickTimer;
import su.catlean.zkm.ZkmKeys;

public final class AnchorBind
extends Module {
    @NotNull
    public static final AnchorBind INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting anchorDelay$delegate;
    @NotNull
    private static final BooleanSetting randomizeDelay$delegate;
    @NotNull
    private static final BindSetting anchorBind$delegate;
    @NotNull
    private static TickTimer tickTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AnchorBind() {
        super("anchor-bind", Categories.getLegit(), null, null);
    }

    private final int getAnchorDelay() {
        return ((Number)anchorDelay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final boolean getRandomizeDelay() {
        return (Boolean)randomizeDelay$delegate.getValue(this, delegatedProperties[1]);
    }

    private final Bind getAnchorBind() {
        return (Bind)anchorBind$delegate.getValue(this, delegatedProperties[2]);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block5: {
            var2_2 = AnchorBind.a ^ 59776049719975L;
            var17_3 = (int)((var2_2 ^ 127725043572358L) << 48 >>> 48);
            var33_5 = (char)var17_3;
            var32_4 = this.getAnchorBind().getKey();
            if (!KeyboardUtil.isKeyPressed(var33_5, var32_4) || !AnchorBind.tickTimer.hasPassedAndReset(this.getAnchorDelay() > 0 ? this.getAnchorDelay() * 5 + 10 : 2)) break block5;
            var27_6 = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.GLOWSTONE, "GLOWSTONE");
            var26_8 = InventoryUtil.INSTANCE.findInHotbar(var27_6).getSlot();
            var27_7 = Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(Globals.getPlayer().getInventory().getSelectedSlot()).getItem(), Items.GLOWSTONE);
            var29_9 = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.RESPAWN_ANCHOR, "RESPAWN_ANCHOR");
            var28_11 = InventoryUtil.INSTANCE.findInHotbar(var29_9).getSlot();
            var29_10 = Globals.getPlayer().getInventory().getSelectedSlot();
            if (!(Globals.getMc().crosshairTarget instanceof BlockHitResult)) ** GOTO lbl-1000
            v0 = Globals.getWorld();
            v1 = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(v1, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
            if (Intrinsics.areEqual(v0.getBlockState(((BlockHitResult)v1).getBlockPos()).getBlock(), Blocks.RESPAWN_ANCHOR)) {
                v2 = true;
            } else lbl-1000:
            // 2 sources

            {
                v2 = var30_12 = false;
            }
            if (var26_8 == -1 || var28_11 == -1) {
                return;
            }
            if (var30_12) {
                var31_13 = new int[]{var26_8, var29_10};
                this.placeAnchors(var31_13);
            } else {
                var31_14 = new int[]{var28_11, var26_8, var27_7 != false ? var28_11 : var29_10};
                this.placeAnchors(var31_14);
            }
        }
    }

    private final void placeAnchors(int[] nArray) {
        Function0<Unit> function0 = AnchorBind::placeAnchors$lambda$0;
        Iterable iterable = ArraysKt.reversed(nArray);
        for (Object t : iterable) {
            int n = ((Number)t).intValue();
            Function0<Unit> function02 = function0;
            function0 = () -> AnchorBind.selectAndUse(n, function02);
        }
        function0.invoke();
    }

    private static final Unit placeAnchors$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final void useAnchor$lambda$0(Function0 function0) {
        function0.invoke();
    }

    private static final void useAnchor(Function0 function0) {
        MinecraftClient minecraftClient = Globals.getMc();
        Intrinsics.checkNotNull(minecraftClient, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
        ((MinecraftAccessor)((Object)minecraftClient)).idoItemUse();
        Scheduler.INSTANCE.schedule(INSTANCE.getAnchorDelay() - 2 + (INSTANCE.getRandomizeDelay() ? RandomUtil.f(new IntRange(-1, 1), false, null) : 0), (byte)-10, INSTANCE.getAnchorDelay() > 0, () -> AnchorBind.useAnchor$lambda$0(function0));
    }

    private static final Unit selectAndUse(int n, Function0 function0) {
        InventoryUtil.INSTANCE.selectSlot(n);
        Scheduler.INSTANCE.schedule(INSTANCE.getAnchorDelay() - 2 + (INSTANCE.getRandomizeDelay() ? RandomUtil.f(new IntRange(-1, 1), false, null) : 0), (byte)-10, INSTANCE.getAnchorDelay() > 0, () -> AnchorBind.useAnchor(function0));
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AnchorBind.a = ZkmKeys.a(650419375391998831L, -521477186697016015L, MethodHandles.lookup().lookupClass()).a(138568193974860L);
                        var20 = AnchorBind.a ^ 55550742311905L;
                        AnchorBind.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[14];
                        var16_4 = 0;
                        var15_5 = "W\b\u00f3\u00feCd\u00f37\u0086-;~2\u00ed\u0087J\u00ef\u00a2\u00bd\u0083\u00cf\u0014\f|\u00e8\u00ce\u00d81nhCt \u00d5\u00cd*#a\u008e\u0012\u00934{\u0085\u00dc\u00f3\u008b+\u0016W\u00f6\u00ef\u00ee\u00b4h\u00b6v\u0095\u00ecI\u009e\u0086@<u\u0090Y\u0081E6\u00b5&\u00dc_p9\u00b9\u00f5\u00f3\u00ac\u0016@\u00b80@\u0019d\u000f\u00d1z\u00beS\u00f1\n\u0083\u0097u\u00d7F\u00c25\b\u0080\u00be1U\u00f1\u001b\u00bb\u001bq\u009b\u0085\u00fc\u009e'\u0014\u00df0\u00aa\rB\u00e3(\u00ed\u0007\u0018[y\u00e5\u001c\u00fd\bz\u0099\u0018\u00e9\u00f5$\u00d5+\u0090\f\t'L\"DC\u0081\u00d0W\u0007\u001c\u008a/\u00feS@\u0016\u00e2\u00f9\u00cct\u00ab8<i\u007f\u00f9N\u001a\u00a7^\u008aZ\u00f9\u00a8z\u00a7\u00cfv\u00e7)\u00d3\u00e9\u00f1\u00dfv\u00d1\u00a9\u00e1\u0016\u00e2.9=0}0\u00fd\u000f\u0018M\u0089^\u000e\u001b\u001b\u00c9\u0018#\b$r\u00ac[\u0007\u00ab|\u00be\u009a\u00f4\u00b1IB\u00efv\u00e3\u00d0\u00e1\u008a\u009a\u00af\u008d 8\u00a01O{\u00f5JT\r\u00b6+\u00c1\u000fY\u0016_\u0093\u00c9\u0010\u0014\u0093\u0010t\u00fbU?\u000bG\u009c\u00e7+W\u0010;\"\u00a6\u001f\u00f3\u0019\u009e\u0087\u00a7\u00c2\u00a9I\u0012E\u00fb\u00e9 \u00caP%?\u001d\u00fe\u00bb\u00e8;t\u00e7$\u00d0\u0016\u00e5\u00911\u00ad\u001fTj\u00c1.\u00a5\u0012\u00da\u009f3\u00ef\u00cf\u009c\u00af\u00187\u00ffI\u00b9\u00ae\u0013\u00ae\u00cc_\u00d8)\u00a8\u00b9\u00b9#%\u00a3jxZN\u0080\u008c/(\u00aeS\u00a49\u00fa\u0091S\u0006\u00f34\u00ebm\u00ed\u0010\u00da\u00d7j\u00fe\u00a2\u00f8w\u00bb\u0093X\u00baZ\u00f2\u0093j%q\u00d2B$\u0016\u0019W\u00f1Ks \u0094B\u000e\u00c1\u001b\u00c9\u00fe\u0012\u00d1\u00ae\u00b6\r@\u0097t8(\u00c8\u00f7X\u00a1\u00ce\u00aa/Wo\u00de\u00c3\u001d<\u0083\u009d8p\u0001\u000b#\u009dk\u0003\u00ed7O\u00f5\u00e1\u00cb\u00e5l \u00f1\u0006f\u009b\u00bb\u00b1,\u00ea\u00d2\u0003%\u00d23\u0018\u00b9\u00b5gd\u00e1\u00d6U~#\u0096\u00d4k4\u00f8\u0003d7+\u00f2\r\u0080\u001a\u008b\u00cd\u00c9T(%w2\\I\u00a8\u007f4%\u0091\u00b9\u00b6\u00c0(\u0090\u0012\u00b3\u00dc\u0092?\u0086\u00b8\u0090\u0097\u0081\u0012\u00b20%\u00de\u008ff\u0089u\u0088\u00b2:^a=";
                        var17_6 = 515;
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
                            var18_3[var16_4++] = AnchorBind.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0001\u00cf\u0081@?3\u00b8\u0090$2\u00c9\u00c2V\u0012\u0007fV\u00fa\u00d8\u0089XO\u00af\u00b2t3\u0093\u00d60\u00f5\u001b\u00be\u0080\u00c3$\u00ca\u00f3kP\rw\u009a\u00a0\u00c6\u0086k\u00eb\u0099\u00ee\u00a0\u0002\u00c2\u00f5\u0099\u0095j\u00d5\u00ef\u00dd\u00dc\u009f\u0019q\u00bb\u00ebF)\u0018\u0090(>\u0004\u00eb\u00be\u0011\u00ad=\u0011\u00b6\u00ab\u00b3\u00cf\u00ff\u00eek6V\u00a4\u00fe\u008d\u00f7\rv\u00f3P6|\u00bbW\u00e2S9\u001e\u00bb\u001ap\u00ea^\u00b1\u0084mzG\u00b9\u00f8\u008d\u00a0\u000by\u00bc\u009e\u00b8\u009b\u00cb2/\u00bb2$\u00dd\u008e\u00adEq\u00a2\u00af\u00a6\u00de\u0081\u00f0'\u008f\u00fe\u0007\u00b6\u00bc\u00c6\u001c\u0084\u00dc\u0084\u00cblf\u008cE?H\u00df\u00b9Q";
                            var17_6 = 161;
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
                            var18_3[var16_4++] = AnchorBind.zkmDecodeUtf8(var19_9).intern();
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
                AnchorBind.b = var18_3;
                AnchorBind.c = new String[14];
                AnchorBind.g = new HashMap<K, V>(13);
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
                var4_14 = "M\u00cc\u00d12\u00a1\u00bcZ\u008f\u008b\u00e7\u00f2\u0007\u00f5\u00dd|\u007f4]$\u00d9\u00d0\u00cbl\u000f\u0012\u00cf\u008b\u0001\u00e1'7\u00e6\u00123\u00a4\u0080X\f\u00b8\u009e";
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
                    var4_14 = "(\u00c6\u00c3\u008c:\u00e7$\u0000Tl\u0095wYAdW";
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
        AnchorBind.e = var6_12;
        AnchorBind.f = new Integer[7];
        var36_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AnchorBind.class, "anchorDelay", "getAnchorDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AnchorBind.class, "randomizeDelay", "getRandomizeDelay()Z", 0)), Reflection.property1(new PropertyReference1Impl(AnchorBind.class, "anchorBind", "getAnchorBind()Lsu/catlean/lj;", 0))};
        AnchorBind.delegatedProperties = var36_20;
        AnchorBind.INSTANCE = new AnchorBind();
        AnchorBind.anchorDelay$delegate = SettingFactory.intSetting$default(AnchorBind.INSTANCE, "delay", 1, new IntRange(0, 5), null, null, 24, null);
        AnchorBind.randomizeDelay$delegate = SettingFactory.booleanSetting$default((SettingContainer)AnchorBind.INSTANCE, "randomize-delay", false, null, null, 12, null);
        AnchorBind.anchorBind$delegate = SettingFactory.bindSetting$default(AnchorBind.INSTANCE, "anchor-bind", new Bind(0, false, false, 7, null), null, null, 12, null);
        AnchorBind.tickTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x336;
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
                throw new RuntimeException("su/catlean/u6", exception);
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
            AnchorBind.c[n2] = AnchorBind.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AnchorBind.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5DE5;
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
                throw new RuntimeException("su/catlean/u6", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AnchorBind.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AnchorBind.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AnchorBind.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AnchorBind.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

