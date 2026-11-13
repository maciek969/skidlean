/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.Aura;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.protect.Offhand;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationHandler;
import su.catlean.zkm.ZkmKeys;

public final class AuraRotationHandler
implements RotationHandler {
    @NotNull
    public static final AuraRotationHandler INSTANCE;
    @NotNull
    private static Function0 m;
    @NotNull
    private static Function0 X;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AuraRotationHandler() {
    }

    @NotNull
    public final Function0 h() {
        return m;
    }

    public final void i(@NotNull Function0 function0) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)AuraRotationHandler.zkmBootstrap("i", (int)14816, (long)(0x7620E78DCF87F6F3L ^ l))));
        m = function0;
    }

    @NotNull
    public final Function0 T() {
        return X;
    }

    public final void Y(@NotNull Function0 function0) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)AuraRotationHandler.zkmBootstrap("i", (int)1008, (long)(0x3D14BDF085507B09L ^ l))));
        X = function0;
    }

    @Override
    public void preAttack(int n, long l) {
    }

    @Override
    public void postRotate(@NotNull Rotation rotation, short s, int n, int n2) {
        long l = (long)s << 48 | (long)n << 32 >>> 16 | (long)n2 << 48 >>> 48;
        long l2 = l ^ 0x2EC7578AFC21L;
        Intrinsics.checkNotNullParameter(rotation, (String)((Object)AuraRotationHandler.zkmBootstrap("i", (int)16593, (long)(0x7BADCB2EC296F33CL ^ l))));
        X.invoke();
        X = AuraRotationHandler::postRotate$lambda$0;
        Offhand.INSTANCE.P(1);
        MovementUtil.INSTANCE.releaseMovementKeys(l2);
    }

    @Override
    public void reset() {
    }

    public final void swapOnHit() {
        int n;
        if (Aura.INSTANCE.getOnHitSwap() && (n = this.findBestWeapon()) != -1) {
            Function0<Unit> function0;
            boolean bl = !(Globals.getMc().currentScreen instanceof ShulkerBoxScreen) && !(Globals.getMc().currentScreen instanceof GenericContainerScreen);
            MovementUtil.INSTANCE.releaseMovementKeys();
            Scheduler.schedule$default(Scheduler.INSTANCE, 2, (byte)0, false, AuraRotationHandler::stopMoving, 6, null);
            int n2 = n < 9 ? n + 36 : n;
            m = function0 = () -> AuraRotationHandler.swapSlot(n2, bl);
            X = function0;
        }
    }

    public final int findBestWeapon() {
        Object object;
        Iterable iterable;
        int n;
        int n2 = -1;
        double d = 0.0;
        for (n = 0; n < 37; ++n) {
            Component<?> component;
            if (n == 36) {
                Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getOffHandStack().getComponents().iterator(), "iterator(...)");
                while (iterable.hasNext()) {
                    Intrinsics.checkNotNullExpressionValue(iterable.next(), "next(...)");
                    if (!Intrinsics.areEqual(component.comp_2443().toString(), "minecraft:attribute_modifiers") || !(component.comp_2444() instanceof AttributeModifiersComponent)) continue;
                    Object obj = component.comp_2444();
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type net.minecraft.world.item.component.ItemAttributeModifiers");
                    for (Object object2 : ((AttributeModifiersComponent)obj).comp_2393()) {
                        Intrinsics.checkNotNullExpressionValue(object2, "next(...)");
                        object = (AttributeModifiersComponent.Entry)object2;
                        String string = ((AttributeModifiersComponent.Entry)object).comp_2395().comp_349().getTranslationKey();
                        Intrinsics.checkNotNullExpressionValue(string, "getDescriptionId(...)");
                        if (!StringsKt.contains$default((CharSequence)string, "attack_damage", false, 2, null) || ((AttributeModifiersComponent.Entry)object).comp_2397() != AttributeModifierSlot.OFFHAND || !(((AttributeModifiersComponent.Entry)object).comp_2396().comp_2449() > d)) continue;
                        d = ((AttributeModifiersComponent.Entry)object).comp_2396().comp_2449();
                        n2 = -1;
                    }
                }
                continue;
            }
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(n).getComponents().iterator(), "iterator(...)");
            while (iterable.hasNext()) {
                Intrinsics.checkNotNullExpressionValue(iterable.next(), "next(...)");
                if (!Intrinsics.areEqual(component.comp_2443().toString(), "minecraft:attribute_modifiers") || !(component.comp_2444() instanceof AttributeModifiersComponent)) continue;
                Object obj = component.comp_2444();
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type net.minecraft.world.item.component.ItemAttributeModifiers");
                for (Object object3 : ((AttributeModifiersComponent)obj).comp_2393()) {
                    Intrinsics.checkNotNullExpressionValue(object3, "next(...)");
                    object = (AttributeModifiersComponent.Entry)object3;
                    String string = ((AttributeModifiersComponent.Entry)object).comp_2395().comp_349().getTranslationKey();
                    Intrinsics.checkNotNullExpressionValue(string, "getDescriptionId(...)");
                    if (!StringsKt.contains$default((CharSequence)string, "attack_damage", false, 2, null) || ((AttributeModifiersComponent.Entry)object).comp_2397() != AttributeModifierSlot.OFFHAND || !(((AttributeModifiersComponent.Entry)object).comp_2396().comp_2449() > d)) continue;
                    d = ((AttributeModifiersComponent.Entry)object).comp_2396().comp_2449();
                    n2 = n;
                }
            }
        }
        if (n2 == -1) {
            for (n = 0; n < 37; ++n) {
                boolean bl;
                block11: {
                    ComponentMap componentMap = Globals.getPlayer().getInventory().getStack(n).getComponents();
                    Intrinsics.checkNotNullExpressionValue(componentMap, "getComponents(...)");
                    iterable = componentMap;
                    if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                        bl = false;
                    } else {
                        Iterator<Object> iterator2 = iterable.iterator();
                        while (iterator2.hasNext()) {
                            object = iterator2.next();
                            Component component = (Component)object;
                            String string = component.toString();
                            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                            if (!StringsKt.contains$default((CharSequence)string, "\u0423\u0440\u043e\u043d V", false, 2, null)) continue;
                            bl = true;
                            break block11;
                        }
                        bl = false;
                    }
                }
                if (!bl) continue;
                n2 = n;
            }
        }
        return n2;
    }

    public final void runPending() {
        m.invoke();
        m = AuraRotationHandler::runPending$lambda$0;
    }

    private static final Unit _clinit_$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$1() {
        return Unit.INSTANCE;
    }

    private static final Unit postRotate$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final void stopMoving() {
        long l = a ^ 0x4000683631EAL;
        long l2 = l ^ 0x56F87CAC6D39L;
        MovementUtil.INSTANCE.releaseMovementKeys(l2);
    }

    private static final Unit swapSlot(int n, boolean bl) {
        GuiMove.INSTANCE.setMacroActive(true);
        if (!Globals.getPlayer().currentScreenHandler.getCursorStack().isEmpty()) {
            InteractionUtil.clickSlot$default(-999, 0, null, false, 14, null);
        }
        InteractionUtil.clickSlot(n, 40, SlotActionType.SWAP, bl);
        GuiMove.INSTANCE.setMacroActive(false);
        Offhand.INSTANCE.P(15);
        return Unit.INSTANCE;
    }

    private static final Unit runPending$lambda$0() {
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
                        AuraRotationHandler.a = ZkmKeys.a(6133856104906453840L, -2501179907936501617L, MethodHandles.lookup().lookupClass()).a(149998217995249L);
                        AuraRotationHandler.d = new HashMap<K, V>(13);
                        var11 = AuraRotationHandler.a ^ 126497688771329L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[18];
                        var18_4 = 0;
                        var17_5 = "\u00bd\u008c>\u009c\u00e8\u00ea*\u00d8\\\u0095\u00bdQ\u00b5\u00b5\u0006]\u00aa\u00ff\u0098\u00a7,\u009e+\u00d4(\b\u00dc\u00d4z\u00ac\u00d2\u00ca*\u001a\u0090\u0095\u009b\u00c3\u00d1\u0086\u0095\u00ccq*\u00b8\u0095N\u00e8w\u00a0\u00dd;4\u00cd\u00b1\u00ce\u00e0\u00ff`8\u00e8e\u001b\u00ff!8\u0098\u00ec\u00b5'\u008a6g\u0090k\u00e9`S\u00cb\u008bY\u00a0\u0091\u0003\u00fd\u0007Y\u00aa\u0087\u00ab<x/\u001a\u0084\u00adY\u00acVIL\u00c0\u00ba-\t\u00f9\u0088\u009bU4\u007fF\u0004\u00c7:\u009f|\u00e2\u0095\u00a5\u0001\u00c2 r\u00c8\u00b3\u00a3\u0001\u00e1\u00b2\u009f\u00ff\u0084\u001d\u00a3\u0011u\u00bal\u00dc\u00d8\u009e\u00a7)\u0091)\u008ck\u0086&\u0081\u00d4\u00c1s\u009e\u0010<\u009c\u0085\u00b7;\u007f\u0001a3\u0000e\u000f\"\u0007.\u0000 &%\u00a4OR\u00f2\u00a7\u00dd2!\u00bb\u0012\u0002(`\u00dd\u0005\u00acc\u00bd'\u00cc%P\u00ff\u00c6+\u0005:y\u00b8\u001d \u00d1\u0005\u0080\u00fa\u0004s\u00d3a;<\u00b6v\u00a3\u00fb#\u00ae\u00ca\u00e7\u001b\u00e4\u00e1\u00f7\u00cfT\u00e5\u00f9\u00c5\u00ea\u001c\u00c6\u00f9\u00b5\u0018\u0000\u00bbM?\td\u008e=T_+\u00b4\u00c3\u00d2|h)8$l\u00b5\u009a\u009d\u0097\u0018N\u00d9\fXR\u00e6Y\u0000>@64j\u0094\u00aa\u00ed\u00b0\u00da\u00edxa\u008aw\u00f2(\u0080\u00a1\u0010\u00b6\u00fc\u00d2\u0017V\u00b1\u000bV\u00efm~\u00ad\u0004\u0095\u00b7\u00f0\u0002\u00c7\u00ad\u00d5b\u0097\u00d4Km\u00f2\u0085\u00f3\u008d\u00d9)t\u00b3l\u00e5\u0096o\u0010\u00fb\u00c8oh\u00d3\u00e4\u00adr}b\u00ef\u001d<\u00e7t\u0015 a5\u00b5\u000e;\u0004#\u00ddC\u00b0F\u00ad\u00a6\u00de\u00d5\u0012Q\u00a4OyH\u00910\u00de\u000bZ\u00e7\u00bd\u00859\u00aa~ :\u00c5x\u00a4\u00e31\u00da\u00d1\u00ca\u00d2\u009f\u009fa\u00d1\u007f\u00ea\u00b4|\u00d9\u00ddh\u0080zV)d\u00c8s\u000f\u00b1MR\u0018\u0005\u0090M\u00ed\u00fdZ\u00baNzy\u00e1J\u00b4\u00edM\u00fd\u000fk\u00c2\u00f0/v\u0099\u00cf \u00df\u00bec\u00b9<y\u0006l\u00a7V\u009c\u0095!nM\u0080/7\u008f\u0019\u00e0\u00fc\u0000c\u00cd\u00a4\u0087\u00d7\u00ee\u00e8\"\u00b8\u0098&\u00fe)5;0$x\u00a1\u008fxX\u00dds\u00a5U\u00a3%\u00fa\u00fb\u0014\u0018!b\u001ea\u001c\u00dd\u00be&UF\u00ebM\n\":@RTg\u00a0\u00ec\u00a2\u0012\u0019\u00e2\u0083\u00c4\u00d0\u00cb\f]\u0016R8\u00f0\u00dd9\u00c9<Q9 6\u00d8%P\u0018,Y\u0081R\"Cy\u00a8\u00bc\u0092\u00a46\n\u00eejt\u0082\t\u00acx{-\u00be\u00e7\\{\u00d6\u00e6\u0001j\u00aa\u00cc\u0090\u00ce\u0019B_i\u00f4\u00a7\u001c.4\u00d0c\u00cb\u00ac^\u00d69I\u001c\u00d4\u0098R'\u009a\u00980\u00ae\u0019\u00b6B%\u00d5\u0019\u0093U\u00f3\u00f9\u001f\u00ec\u0005d\\\u00dd\u00eb\u00a5I\u0010\u00a2<\u0002";
                        var19_6 = 623;
                        var16_7 = 24;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = AuraRotationHandler.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "1\u00d4R\u008cJ\u00bb\t\u008b\u00be\u00bb\u0014\u0082n\u00f8\u00a8^\\\u000f\u00e3U\u00e2\t1@\u00bc\u0017\u0010.\u00a2\u008e\u00cf\u001f\u00a9\u00be\u00det.\u00c9?a\u0099\u0087\u00c8\u001e\u00c7\u00c3\u00817va\u009f\u00b6q\u00a2\u00b6\u00a9B\b\u00f1\u00a4\n\u00e8\u00ec\u00a0w\u00bb\u008c`\u0018\u00c6l\u00ee`\u000b\u00ab)\u00eda\u00bc\u009c\u0084 \u0016>8\u00eb\u00a8[\u00f5\u001e:\u00fd\r\u00d2 \u008a\u00dd&W\u0015\u00f2%y\u00ef\u00a6\u008c\u00e7U4\u0002\u0088\u00da\u00b3\u00bb\u0090\u0005\u0000\t\u00beq\"\u00f5`\u001b\u00be\u00ce~\u00c9\u00f36\u008c8\u00d0\u00fc\u00e2\u00aa0L,\u00f9\u00f4D\u00cd\u0085\u00c9G\u00d7\u0017\u00b1\u0095=lb\u0097\n\u0003\u001e`f\u00a7f\u0081ioz\u00b3m\u001bpbPy\u00be\u00b7\u00beN\u00bdq7\u001c\u00a1w\u00baE.";
                            var19_6 = 185;
                            var16_7 = 136;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = AuraRotationHandler.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                AuraRotationHandler.b = var20_3;
                AuraRotationHandler.c = new String[18];
                AuraRotationHandler.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "\u00e3\u0086\u00adS\u00a6>`Na\t\u00ff4B\u00a6\u00e7acE2\u00cd\u0096\u00f1g\u009d3\u00ddA~9|#\u00d0\nBm5dB%\u00d267\u00c1H\u00de1\u00b9\u00d8\u0014\u00f2p\u0014\u0087\u001b\u00ee\u00d3\u00bfk\u000b;un[\u0019";
                var5_15 = 64;
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
                    var4_14 = "\u0080\u008d\u00db\u0014\u00a0\u008d\u00d1\u00a1\u0012\u0002\u00a6*\u0017\u0088\u0086\u00f3";
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
        AuraRotationHandler.e = var6_12;
        AuraRotationHandler.f = new Integer[10];
        AuraRotationHandler.INSTANCE = new AuraRotationHandler();
        AuraRotationHandler.m = (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)();
        AuraRotationHandler.X = (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$1(), ()Lkotlin/Unit;)();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A71;
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
                throw new RuntimeException("su/catlean/tc", exception);
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
            AuraRotationHandler.c[n2] = AuraRotationHandler.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AuraRotationHandler.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x69E9;
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
                throw new RuntimeException("su/catlean/tc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AuraRotationHandler.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AuraRotationHandler.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/tc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AuraRotationHandler.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AuraRotationHandler.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

