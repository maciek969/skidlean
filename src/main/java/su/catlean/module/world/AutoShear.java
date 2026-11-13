/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.world.AutoShear$onPlayerUpdate$1;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class AutoShear
extends Module {
    @NotNull
    public static final AutoShear INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final TickTimer tickTimer;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    private AutoShear() {
        super("auto-shear", Categories.getWorld(), null, null);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        switchMode$delegate.setValue(this, delegatedProperties[0], (Object)swapMode);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final void setDelay(int n) {
        delay$delegate.setValue(this, delegatedProperties[1], n);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x82C9761CA27L;
        long l2 = l ^ 0x3420C089DB90L;
        if (tickTimer.hasPassedAndReset(this.getDelay())) {
            Iterable iterable = Globals.getWorld().getEntities();
            Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
            Object object = iterable;
            Iterable iterable2 = object;
            Collection collection = new ArrayList();
            for (Entity entity : iterable2) {
                Entity entity2 = entity;
                if (!(entity2 instanceof SheepEntity && !((SheepEntity)entity2).isSheared() && !((SheepEntity)entity2).isBaby() && Globals.getPlayer().getEyePos().distanceTo(((SheepEntity)entity2).getEyePos()) < Globals.getPlayer().getEntityInteractionRange())) continue;
                collection.add(entity);
            }
            object = (List)collection;
            List list = CollectionsKt.sortedWith(object, new AutoShear$onPlayerUpdate$1());
            object = InventoryUtil.INSTANCE.findItem(l2, AutoShear::onPlayerUpdate$lambda$0, this.getSwitchMode());
            if (list.isEmpty() || !((SlotResult)object).isFound()) {
                return;
            }
            Object t = CollectionsKt.first(list);
            Intrinsics.checkNotNullExpressionValue(t, "first(...)");
            Entity entity = (Entity)t;
            Vec3d vec3d = entity.getEyePos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
            RotationManager.INSTANCE.submit(new ActionRotationRequest(Rotation.Companion.to(vec3d), () -> AutoShear.onPlayerUpdate$lambda$1((SlotResult)object, entity), null));
        }
    }

    private static final boolean onPlayerUpdate$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return itemStack.getItem() instanceof ShearsItem;
    }

    private static final Unit onPlayerUpdate$lambda$1$lambda$0(Entity entity) {
        Globals.getInteractionManager().interactEntity(Globals.getPlayer(), entity, Hand.MAIN_HAND);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        return Unit.INSTANCE;
    }

    private static final Unit onPlayerUpdate$lambda$1(SlotResult slotResult, Entity entity) {
        Function0<Unit> function0 = () -> AutoShear.onPlayerUpdate$lambda$1$lambda$0(entity);
        SwapMode swapMode = INSTANCE.getSwitchMode();
        int n = slotResult.getSlot();
        InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
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
                        AutoShear.a = ZkmKeys.a(1345393246632750891L, 5283128540758722505L, MethodHandles.lookup().lookupClass()).a(281128856108139L);
                        var20 = AutoShear.a ^ 29437419988048L;
                        AutoShear.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[11];
                        var16_4 = 0;
                        var15_5 = "U\u0083\u00ae\u0004\u0011D\u00c9\u0011\u00c1=\u00a2D\u00ac\u000bw\u0013u\u0089FS;\u000fY\u00faQC\u0099\u0011\u008aX=\u00ee\u0018\u00f2\u00cf?\u00c8~\u00d4&\u00ba\u00dd4w\u009aB\u00bf\u00ab\u0010\u00ff\u009df\u00d3\u001a\u00f7o\u0010\u0081\\H\u0088\u00c8\u00b3\u00d69\"\u008c^\u00ea+c\u00a9\u00af cI!i\u0088\u001b\u0088\u0097\u00fb\u0080\u00fb\u00a5E\u00cd\u0080fzta3\u00aa\u001e\u00f1\u00ddk`\u00ca\u00c0M\u00a9\u00ddS\u0010w\u00f4u.\u00faqb\u00ae\u009bQ\u00d1\u0014\r\u00bd\u00fd\u00fe\u0010\u00e1\u00ed\u00b9#Z\u0083Y\u009c\u00e7\u0098Uu\u0092\u00af\u00e1\u0092(!QP\u0083\u00d8\u00b1\u009f\u00c5\u00e6\u00bb\u00f4Hw\u0081;\u0017\u00f9=\u00a2\u00cd\u009c\u00c7Tgj\u00f8D\u00fb\u00b1\u00a7\u00d4\u00f0\t|\u009d\u0011\u00f0O_y8v.\u00c2!\u00edE\u0095\u00bb\u00af*\u00f9\u0002\u0007\u00c0 Co\u0086WK\u00e3\u00b3\u00fas\u008e\u0018\u0083\u00ad\u00bf\u00abv3\u00bc\u0019i\u00b0\u0005Z\u0003\u00f5\u001c\u00f8+\u00d4\u00ceU\u0087_\u0098[\u00a0D\u00c9Y\u00e7[\u0018\u00f4\u00f4\u00b3\tG\u00fb\u00eeF\tB\u0083\u0005[\u00e6\u00cf6\u00f9\u00ef\u00e4\u0083Q\u00d4O\u00b6\u0010\t\u00fd\u0019\u00ca\u009e\u0090\u00a2\u0091K\u00e5I\u00da\u0095\u001beV";
                        var17_6 = 280;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = AutoShear.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "V\u0014]n+\u00fbA\u00d4\u00ed\u0094\u00e2\u00f2[_7g\u0088\u00ae8\u00b5\u0096\u00df\u00bc\u00f9 \u00e9\u008f\u0095\u00ad\u00f2\u00d3c\u000b/\u00e2\u0092\u00c9\u0013\u00e2^\u00ae^i5OL\u00d2\u00b1'4L\u00cceaK\u00cf\u00ec";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = AutoShear.zkmDecodeUtf8(var19_9).intern();
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
                AutoShear.c = var18_3;
                AutoShear.d = new String[11];
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
                var0_12 = new long[3];
                var4_13 = 0;
                var5_14 = "r;>D\u00db\u009b\u009f\u00a5\u0083AZ\u00e5\u008a\u0092\u00f2\u0082\u00f7\u00d0\u0000^\u008cp\u00d1-";
                var6_15 = 24;
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
        var31_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoShear.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoShear.class, "delay", "getDelay()I", 0))};
        AutoShear.delegatedProperties = var31_20;
        AutoShear.INSTANCE = new AutoShear();
        AutoShear.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoShear.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, (int)var0_12[1], null);
        AutoShear.delay$delegate = SettingFactory.intSetting$default(AutoShear.INSTANCE, "delay", 0, new IntRange(0, (int)var0_12[0]), null, null, (int)var0_12[2], null);
        AutoShear.tickTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x69EC;
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
                throw new RuntimeException("su/catlean/en", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            AutoShear.d[n2] = AutoShear.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoShear.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/en" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoShear.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

