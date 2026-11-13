/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.KeyMappingAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.WindHop$EntriesMappings;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class WindHop
extends Module {
    @NotNull
    public static final WindHop INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private WindHop() {
        super("wind-hop", Categories.getBasic(), null, null);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        switchMode$delegate.setValue(this, delegatedProperties[0], (Object)swapMode);
    }

    @Override
    public void onDisable(long l) {
        block0: {
            int n = (int)((l ^ 0x280BA6AF1127L) << 48 >>> 48);
            KeyBinding keyBinding = Globals.getMc().options.jumpKey;
            Intrinsics.checkNotNull(keyBinding, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            int n2 = ((KeyMappingAccessor)((Object)keyBinding)).getKey().getCode();
            char c = (char)n;
            if (KeyboardUtil.isKeyPressed(c, n2)) break block0;
            Globals.getMc().options.jumpKey.setPressed(false);
        }
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x36397E38F5E2L;
        long l2 = l ^ 0x7A17F4E20688L;
        String string = WindHop$EntriesMappings.zkmGetFlag();
        Globals.getMc().options.jumpKey.setPressed(true);
        String string2 = string;
        if (Globals.getPlayer().getItemCooldownManager().isCoolingDown(Items.WIND_CHARGE.getDefaultStack())) {
            return;
        }
        if (!Globals.getPlayer().isOnGround() && Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().offset(0.0, -1.0, 0.0)).iterator().hasNext()) {
            this.useWindChargeAt(new Rotation(Globals.getPlayer().getYaw(), 90.0f, false, null, 12, null), l2);
        } else if (Globals.getPlayer().horizontalCollision) {
            Entity entity = Globals.getPlayer();
            BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            BlockPos blockPos2 = blockPos;
            Object object2 = WindHop$EntriesMappings.enumEntries;
            Iterable iterable = object2;
            Collection collection = new ArrayList();
            for (Object t : iterable) {
                Direction direction = (Direction)t;
                if (!(direction != Direction.UP && direction != Direction.DOWN)) continue;
                collection.add(t);
            }
            for (Object object2 : (List)collection) {
                BlockPos blockPos3;
                Intrinsics.checkNotNullExpressionValue(blockPos2.offset((Direction)object2), "relative(...)");
                if (Globals.getWorld().getBlockState(blockPos3).isReplaceable()) continue;
                Vec3d vec3d = blockPos3.toCenterPos().add(0.0, -0.5, 0.0);
                Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
                this.useWindChargeAt(Rotation.Companion.to(vec3d), l2);
            }
        }
    }

    private final void useWindChargeAt(Rotation rotation, long l) {
        block0: {
            long l2 = (l = a ^ l) ^ 0x634492341FF0L;
            SlotResult slotResult = InventoryUtil.INSTANCE.findItem(l2, WindHop::isWindCharge, this.getSwitchMode());
            if (!slotResult.isFound()) break block0;
            RotationManager.INSTANCE.submit(new RotationRequest(rotation, 150, () -> WindHop.useWindChargeAt$lambda$1(slotResult)));
        }
    }

    private static final boolean isWindCharge(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.WIND_CHARGE);
    }

    private static final Unit useWindCharge() {
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        return Unit.INSTANCE;
    }

    private static final Unit useWindChargeAt$lambda$1(SlotResult slotResult) {
        Function0<Unit> function0 = WindHop::useWindCharge;
        SwapMode swapMode = INSTANCE.getSwitchMode();
        int n = slotResult.getSlot();
        InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
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
                        WindHop.a = ZkmKeys.a(-672488293640877659L, 3372041345910309674L, MethodHandles.lookup().lookupClass()).a(7836620945013L);
                        var20 = WindHop.a ^ 45973755259595L;
                        WindHop.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "\u00ac\u00d0\u009d\u00a7m\u00f0;B\u007f#W\u00c9\u008fn\u0000q\u00b0G@\u000f{i\u00e6=Zh\u009b\u0006\u0019FI5\u0010S\u00e2\u007fmo<R\u00d8\u0092\u00ed\u00a7\u00d8J\u00f9\u00c0\u00c9\u0010@t\u00fcb\t\u00e1\u0007\u00c4\u0098r(\u00b3\u00f0r\u00ce$0mv\u000e\u00e7\"5\u008ecns\u00e66&\u0090c\u0081\u00e1p\u009c\u009d\u0003e\u00a0\u00e45\u0087\u0004\u00c4\t,i\u00af\u009dB\u00e7\u00db\u00b7\u008a2Z\u008fe\u00f4\u00c1\u001b)\u0002[ p\u0082\"\u001c\u00ab\u00d3Oj\u0089\u00ecgV\u0005\u001b\u00d3\u0090\u0006'J\u00c0\u00061~_\u00e3\u00eat \u0089\u00ad\u008a\u0006\u00881\u00c3\u00bc9wy\u00b6\u001c\u00e9F\u0093VS\u0087\u00e3\u00d7\u00fb\u00ed\u00d5\u0004\u00a6\u00fd]\u00f5\u0002\u00de\u00d7\u00969\u00d9\u0085\\*j\u0093\u00ff\u00b4\u00a6t\u0004\u00a9vh\u00e4d\u0017o\u00d5\u00dfK\u00cb\u0007h\u0095\u00fb\u00d6\u0088\u00b42\u0014\u001c\u00184\u0080\u00b2\u00be\u00b9\u000b\u00be\u0086\u00e8\u00a7scspv\u00e6,\u00da\u00abN[_+\u008dn\u00b2\u0089\u00d7=\u0099+\u00ad*k`\u0000\u000bq8\u00b6\u00ba\u00c4\u0013\u00be\u001b\u009c1\u00bf\u00dc\u00d4q\u00d1\u00fd\u00192\u0096\u00d1\u00e4\u008c\n\u0098.\u0017\u00f9V\b\u00fc\u00d9U'\u0006\u0098lK\u0018\u0014s1\u001e05A\u0092\u0082=\u00c0\u00e0A\u00a7}PV\u00f9\u0001\\\u0091\u0003f%";
                        var17_6 = 310;
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
                            var18_3[var16_4++] = WindHop.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c5\u0004nZ\u00c4\u008f4\u00c3'\u0011M4\u00d3\u0003^\u00b5\u0088\u00c8\u0090\u0086J\u0016\u009e:\u0018\u009c\u00152\u00cc\u00f6(_\u00a7\u00a0\u00ce\u00c8\u00f7'\u0095\u00fd\u008c\u0011\u0011\u0082\u00fe\u0083\u00fc\u0093\u0083";
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
                            var18_3[var16_4++] = WindHop.zkmDecodeUtf8(var19_9).intern();
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
                WindHop.b = var18_3;
                WindHop.c = new String[9];
                WindHop.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "~\u001ek\u00c4\u00c8^'n\u000f\u00f7\u00c9\t\u00a1\u00ecf#";
                var5_15 = 16;
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
                    var4_14 = "\b\u00b0(\r\u00a7\u0099\u008d6J\u00c3\u00e5h\u00bd\u00e3\u00a3\u00c6";
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
        WindHop.e = var6_12;
        WindHop.f = new Integer[4];
        var26_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(WindHop.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0))};
        WindHop.delegatedProperties = var26_20;
        WindHop.INSTANCE = new WindHop();
        WindHop.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)WindHop.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1AF7;
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
                throw new RuntimeException("su/catlean/_v", exception);
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
            WindHop.c[n2] = WindHop.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WindHop.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_v" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x58DF;
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
                throw new RuntimeException("su/catlean/_v", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            WindHop.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = WindHop.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_v" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WindHop.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(WindHop.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

