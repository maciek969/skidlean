/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.RotatingModuleTimingMode;
import su.catlean.module.world.AutoWitherPoseMode;
import su.catlean.render.AnimationStyle;
import su.catlean.render.BlockHighlightRenderer;
import su.catlean.render.RenderUtil;
import su.catlean.render.ShapeRenderMode;
import su.catlean.render.Theme;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Direction$WhenMappings;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.InteractMode;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.SwitchMode;
import su.catlean.util.Timer;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoWither
extends Module {
    @NotNull
    public static final AutoWither INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting timing$delegate;
    @NotNull
    private static final IntSetting blocksPerAction$delegate;
    @NotNull
    private static final IntSetting delayPerAction$delegate;
    @NotNull
    private static final BooleanSetting rotate$delegate;
    @NotNull
    private static final ModeSetting placeMode$delegate;
    @NotNull
    private static final ModeSetting interact$delegate;
    @NotNull
    private static final ModeSetting switch$delegate;
    @NotNull
    private static final FloatSetting range$delegate;
    @NotNull
    private static final FloatSetting wallRange$delegate;
    @NotNull
    private static final BooleanSetting render$delegate;
    @NotNull
    private static final ModeSetting pose$delegate;
    @NotNull
    private static final List positions;
    private static int K;
    @NotNull
    private static Timer timer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private AutoWither() {
        super("auto-wither", Categories.getWorld(), null, null);
    }

    private final RotatingModuleTimingMode getTiming() {
        return (RotatingModuleTimingMode)((Object)timing$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setTiming(RotatingModuleTimingMode rotatingModuleTimingMode) {
        timing$delegate.setValue(this, delegatedProperties[0], (Object)rotatingModuleTimingMode);
    }

    private final int getBlocksPerAction() {
        return ((Number)blocksPerAction$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final void setBlocksPerAction(int n) {
        blocksPerAction$delegate.setValue(this, delegatedProperties[1], n);
    }

    private final int getDelayPerAction() {
        return ((Number)delayPerAction$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final void setDelayPerAction(int n) {
        delayPerAction$delegate.setValue(this, delegatedProperties[2], n);
    }

    private final boolean getRotate() {
        return (Boolean)rotate$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setRotate(boolean bl) {
        rotate$delegate.setValue(this, delegatedProperties[3], bl);
    }

    private final InteractMode getPlaceMode() {
        return (InteractMode)((Object)placeMode$delegate.getValue(this, delegatedProperties[4]));
    }

    private final void setPlaceMode(InteractMode interactMode) {
        placeMode$delegate.setValue(this, delegatedProperties[4], (Object)interactMode);
    }

    private final PlaceMode getInteract() {
        return (PlaceMode)((Object)interact$delegate.getValue(this, delegatedProperties[5]));
    }

    private final void setInteract(PlaceMode placeMode) {
        interact$delegate.setValue(this, delegatedProperties[5], (Object)placeMode);
    }

    private final SwitchMode getSwitch() {
        return (SwitchMode)((Object)switch$delegate.getValue(this, delegatedProperties[6]));
    }

    private final void setSwitch(SwitchMode switchMode) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 32 | (long)n << 32 >>> 32) ^ a;
        switch$delegate.setValue(this, delegatedProperties[AutoWither.zkmBootstrap2("n", (int)18651, (long)(0x258627D43879A100L ^ l2))], (Object)switchMode);
    }

    private final float getRange() {
        return ((Number)range$delegate.getValue(this, delegatedProperties[7])).floatValue();
    }

    private final void setRange(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        range$delegate.setValue(this, delegatedProperties[AutoWither.zkmBootstrap2("n", (int)8561, (long)(0x125F3E6AB01D1558L ^ l))], Float.valueOf(f));
    }

    private final float getWallRange() {
        return ((Number)wallRange$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final void setWallRange(float f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        wallRange$delegate.setValue(this, delegatedProperties[AutoWither.zkmBootstrap2("n", (int)23899, (long)(0x376BD9623B1290D5L ^ l))], Float.valueOf(f));
    }

    private final boolean getRender() {
        return (Boolean)render$delegate.getValue(this, delegatedProperties[9]);
    }

    private final void setRender(boolean bl) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ a;
        render$delegate.setValue(this, delegatedProperties[AutoWither.zkmBootstrap2("n", (int)11068, (long)(0x198D760FA89D2FE6L ^ l))], bl);
    }

    private final AutoWitherPoseMode getPose() {
        return (AutoWitherPoseMode)((Object)pose$delegate.getValue(this, delegatedProperties[10]));
    }

    private final void setPose(AutoWitherPoseMode autoWitherPoseMode) {
        long l = 0L;
        l = a ^ l;
        pose$delegate.setValue(this, delegatedProperties[AutoWither.zkmBootstrap2("n", (int)5539, (long)(0x28234F2137F8FE06L ^ l))], (Object)autoWitherPoseMode);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        int n;
        boolean bl;
        long l;
        block14: {
            block15: {
                HitResult hitResult;
                block20: {
                    block19: {
                        HitResult hitResult2;
                        block18: {
                            HitResult hitResult3;
                            block17: {
                                block16: {
                                    long l2 = a ^ 0x437C78D971F0L;
                                    l = l2 ^ 0x5A9A807038EDL;
                                    bl = Direction$WhenMappings.m();
                                    n = Globals.getMc().options.useKey.isPressed();
                                    if (bl) break block14;
                                    if (n == 0) break block15;
                                    n = timer.hasPassed2(250) ? 1 : 0;
                                    if (bl) break block14;
                                    if (n == 0) break block15;
                                    n = Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.SOUL_SAND) ? 1 : 0;
                                    if (bl) break block14;
                                    if (n == 0) break block15;
                                    positions.clear();
                                    if (bl) break block16;
                                    hitResult3 = Globals.getMc().crosshairTarget;
                                    if (hitResult3 != null) break block17;
                                }
                                return;
                            }
                            hitResult2 = hitResult = hitResult3;
                            if (bl) break block18;
                            if (hitResult2.getType() != HitResult.Type.BLOCK) break block19;
                            hitResult2 = hitResult;
                        }
                        if (hitResult2 instanceof BlockHitResult) break block20;
                    }
                    return;
                }
                BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos().offset(((BlockHitResult)hitResult).getSide());
                Intrinsics.checkNotNullExpressionValue(blockPos, "relative(...)");
                this.canBuildAt(blockPos);
            }
            n = K;
        }
        if (!bl) {
            if (n > 0) {
                int n2 = K;
                K = n2 + -1;
                return;
            }
            n = positions.isEmpty() ? 1 : 0;
        }
        if (!bl) {
            if (n != 0) {
                return;
            }
            n = 0;
        }
        int n3 = this.getBlocksPerAction();
        Iterator iterator2 = positions.iterator();
        while (iterator2.hasNext()) {
            block21: {
                block24: {
                    block23: {
                        AutoWither autoWither;
                        block22: {
                            Pair pair = (Pair)iterator2.next();
                            if (n3 <= 0 && !bl) break;
                            Object object = new Item[1];
                            Item item = (Boolean)pair.getSecond() != false ? Items.SOUL_SAND : Items.WITHER_SKELETON_SKULL;
                            Intrinsics.checkNotNull(item);
                            object[0] = item;
                            int n4 = InventoryUtil.INSTANCE.findInHotbar((Item[])object).getSlot();
                            if (n4 == -1 && !bl) continue;
                            object = PlaceManager.place$default(PlaceManager.INSTANCE, (BlockPos)pair.getFirst(), n4, this.getInteract(), this.getPlaceMode(), EntityCheck.ALL, this.getRange(), this.getWallRange(), 150, l, this.getSwitch(), null, false, 1536, null);
                            if (bl || object == null) break block21;
                            Object object2 = object;
                            INSTANCE.buildWither((BlockPos)pair.getFirst(), (RotationRequest)object2);
                            iterator2.remove();
                            autoWither = INSTANCE;
                            if (bl) break block22;
                            if (autoWither.getTiming() != RotatingModuleTimingMode.Instant) break block23;
                            n3 += -1;
                            autoWither = INSTANCE;
                        }
                        K = autoWither.getDelayPerAction();
                        if (!bl) break block24;
                    }
                    if (!bl) break;
                }
                if (!bl) continue;
            }
            if (!bl) continue;
        }
    }

    private final void canBuildAt(BlockPos blockPos) {
        boolean bl = this.getPose() == AutoWitherPoseMode.STANDING;
        switch (Direction$WhenMappings.F[Globals.getPlayer().getFacing().ordinal()]) {
            case 1: {
                boolean bl2;
                if (bl) {
                    positions.add(TuplesKt.to(blockPos.up(), true));
                    positions.add(TuplesKt.to(blockPos.up().south(), true));
                    positions.add(TuplesKt.to(blockPos.up().north(), true));
                    positions.add(TuplesKt.to(blockPos.up().up(), false));
                    positions.add(TuplesKt.to(blockPos.up().up().south(), false));
                    bl2 = positions.add(TuplesKt.to(blockPos.up().up().north(), false));
                    break;
                }
                positions.add(TuplesKt.to(blockPos.west(), true));
                positions.add(TuplesKt.to(blockPos.west().south(), true));
                positions.add(TuplesKt.to(blockPos.west().north(), true));
                positions.add(TuplesKt.to(blockPos.west().west().south(), false));
                positions.add(TuplesKt.to(blockPos.west().west().north(), false));
                bl2 = positions.add(TuplesKt.to(blockPos.west().west(), false));
                break;
            }
            case 2: {
                boolean bl3;
                if (bl) {
                    positions.add(TuplesKt.to(blockPos.up(), true));
                    positions.add(TuplesKt.to(blockPos.up().east(), true));
                    positions.add(TuplesKt.to(blockPos.up().west(), true));
                    positions.add(TuplesKt.to(blockPos.up().up(), false));
                    positions.add(TuplesKt.to(blockPos.up().up().east(), false));
                    bl3 = positions.add(TuplesKt.to(blockPos.up().up().west(), false));
                    break;
                }
                positions.add(TuplesKt.to(blockPos.south(), true));
                positions.add(TuplesKt.to(blockPos.south().east(), true));
                positions.add(TuplesKt.to(blockPos.south().west(), true));
                positions.add(TuplesKt.to(blockPos.south().south().east(), false));
                positions.add(TuplesKt.to(blockPos.south().south().west(), false));
                bl3 = positions.add(TuplesKt.to(blockPos.south().south(), false));
                break;
            }
            case 3: {
                boolean bl4;
                if (bl) {
                    positions.add(TuplesKt.to(blockPos.up(), true));
                    positions.add(TuplesKt.to(blockPos.up().west(), true));
                    positions.add(TuplesKt.to(blockPos.up().east(), true));
                    positions.add(TuplesKt.to(blockPos.up().up(), false));
                    positions.add(TuplesKt.to(blockPos.up().up().west(), false));
                    bl4 = positions.add(TuplesKt.to(blockPos.up().up().east(), false));
                    break;
                }
                positions.add(TuplesKt.to(blockPos.north(), true));
                positions.add(TuplesKt.to(blockPos.north().east(), true));
                positions.add(TuplesKt.to(blockPos.north().west(), true));
                positions.add(TuplesKt.to(blockPos.north().north().east(), false));
                positions.add(TuplesKt.to(blockPos.north().north().west(), false));
                bl4 = positions.add(TuplesKt.to(blockPos.north().north(), false));
                break;
            }
            case 4: {
                boolean bl5;
                if (bl) {
                    positions.add(TuplesKt.to(blockPos.up(), true));
                    positions.add(TuplesKt.to(blockPos.up().north(), true));
                    positions.add(TuplesKt.to(blockPos.up().south(), true));
                    positions.add(TuplesKt.to(blockPos.up().up(), false));
                    positions.add(TuplesKt.to(blockPos.up().up().north(), false));
                    bl5 = positions.add(TuplesKt.to(blockPos.up().up().south(), false));
                    break;
                }
                positions.add(TuplesKt.to(blockPos.east(), true));
                positions.add(TuplesKt.to(blockPos.east().south(), true));
                positions.add(TuplesKt.to(blockPos.east().north(), true));
                positions.add(TuplesKt.to(blockPos.east().east().south(), false));
                positions.add(TuplesKt.to(blockPos.east().east().north(), false));
                bl5 = positions.add(TuplesKt.to(blockPos.east().east(), false));
            }
        }
    }

    private final void buildWither(BlockPos blockPos, RotationRequest rotationRequest) {
        block6: {
            block5: {
                block2: {
                    AutoWither autoWither;
                    block3: {
                        boolean bl;
                        block4: {
                            RotationRequest rotationRequest2 = rotationRequest;
                            bl = Direction$WhenMappings.m();
                            autoWither = INSTANCE;
                            if (bl) break block2;
                            if (!autoWither.getRotate()) break block3;
                            if (INSTANCE.getTiming() != RotatingModuleTimingMode.Instant) break block4;
                            RotationManager.INSTANCE.queue(rotationRequest);
                            if (!bl) break block5;
                        }
                        RotationManager.INSTANCE.submit(rotationRequest);
                        if (!bl) break block5;
                    }
                    autoWither = rotationRequest.getAction().invoke();
                }
                rotationRequest.setAction(AutoWither::buildWither$lambda$0);
            }
            if (!INSTANCE.getRender()) break block6;
            ShapeRenderMode shapeRenderMode = ShapeRenderMode.BOTH;
            AnimationStyle animationStyle = AnimationStyle.Fade;
            Color color = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 1.0f);
            Color color2 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 1.0f);
            BlockHighlightRenderer.INSTANCE.add(blockPos, color2, color, animationStyle, shapeRenderMode);
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getTiming() == RotatingModuleTimingMode.Instant;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getTiming() == RotatingModuleTimingMode.Instant;
    }

    private static final Unit buildWither$lambda$0() {
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
                        AutoWither.a = ZkmKeys.a(-4585436243985243151L, 8235611403420552126L, MethodHandles.lookup().lookupClass()).a(267804681414449L);
                        var20 = AutoWither.a ^ 120869271770356L;
                        AutoWither.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[35];
                        var16_4 = 0;
                        var15_5 = "\u00073$\u0090<\u00f6\u0097\b\u00eeYWn\u0085\u00e5\u00b9yo\u00a7kI\u00c9\u00cd\u0007g\u00b5\u008f\u0085\u00ffr:\u000e\u00df4\u001e\u00bci'\u00aa\u001b\u00e28Ar\"+T\u008e+w\u00b3\u00e8\u008d\u001d?\u0094_\u0088\u00a0\u00aa\u00c8\u00d9O\u00b5\u0004\u0095\u00b2\u00eb\u0086\u0006\u00de\u0094=\u00cc\u0092\u00f7\u0013\u00b3\u008d\u00d5E\u0082\u00afn\u001e\u00ac&\u00b6\u00cd\u00cdE\u00ecF\r\u00e4\u0014{S\u0018\u00e5\u00c9M\u0098\u0097\u009f\u00d7)\u00cc;~\u00bc\u001d>\u00ca\u0093?\u00ad\u009f\u008d\u00f7\u00d2\u00e4c\u0010\u00b9O\u00e1\u00f8Va\u00ffC\u0002\u008ff4Ga\u0080\u00a88e\u00c6Vq\u009d$\u00ad\u009d1\u00f4'$_\u00des\u0012Y\u00f4\u00ab\u0015JK\t\u00d43\u0016!\u00d1\u009bX\u00dfA5\u00d5\u00a9WP\u00a5\u00c5\u0093h\u00da^\u0004H\u00d7r\u000ew:\u0013q\u0007u1\u0096\u0018\u000b\u00e4\u00f1\u00ca\u00a2B\u00daU\u00cf\u00f7p\u00c8\u00e5mf\u00de;[\u0016\u0001\u00ce\\\u0094\u008c \u00b7\u0000E\u0011JS_J\u00cc\u00ec\t\u00cc\u008b\u009b<\u00ad1\u00e5\u0085?iS\u00a5\u00f6\u0019\u0098~\u00a1MN\u00ac\u009e86\u0001\u00e9\u0014\u00cc\u009dm\u00a2\u00c7\u0011}\u00a4\u0015\u00ac\u00fa(\u00f8>C\u0011\u00fe)\u000b-\u0000\u00a8\u0081y\u00ecq\u008c#}N\u0094\u00ab\u00fc7\u00bfe\u00a6\u001f/\u00a1\u00ac\u00b2\u00c76\u00bdx$y\u0012%\u00e3\u00d0\u0010\u000b\u0004\u007f2W\u00a5\u00d1\u00ce\u0019\u007f\u008aT\u001d\u001au\u00f3\u0010\u00ae\u001e\u007f,\u0092\u001b\u00ecD\u000f\u0005mH\u0096)TO\u0010-\u00a2 \u00ab\\\u00c1\u001e\f9\u00a3p\u00a0\u00da\u0083\u00a0\u00dd \u008c\u00925|)\u00c9\u00c4/e\u0090\u00ef\u000b\u00bcl\u00ab\u009dI9 \u00b1&\u00b3\u00df{\u00e7\u00d5\u00d5>\u0001\u00bc\u00f5H\u0010\u00ba\u008a`O\u00de\u00ab\u00cb;N\u0005\u0011\u0014p\u00eb|B\u0010\u0081\u00cc\u00df\u001e\u00e3\u0012orz\u00d7&Zb\u00ab\u0001K8\u00b5:\u009e\u0003\u00e9O\u00c2\u00a5b=\u00c3g\u00cf\u00c7F\u0092\u00e8\u00ba\u0004\u00d6\u00d2\u0018\u0007q\u00c9d\u0011y\u00bb7*]\u00d7\u008f\u001b.>\u00bcU;\u0014\u00ee\u00fc\u0099\u00c6\u00f8\u00f9\u00ae|\u00aa\u00f7\u001e;\u00d8\u00fc\u0012 \u00ae\u0006C\u00b6\u0087aC\u009e\u009c\u00b1\u0082\u000f\u00b6\u001d\u0097\u00a9\u0007\u00de\u00eb\u00e1\u00bd\u001aD{\u0007\u00e7F\u00dcm\u007fA=\u0018\u00fe\u00bff\u001e \u00ed\u00b1\u00b1\u00adw\u0017\u00b4\u0012Z\u0087N\u00f6\u00d2#c\u0095\n\u0082\u00c8\u0018\u00d4\u00fe\u00a6\u00f9\u0088\u00b1\u00b8\u00a9U\u00f8_}\u00cb\u001a\u001e\u00a48o\u00de\u0002k\u009aG\u00c7\u0010,\u00db1\f\b\u00dazp\u00f0\u00cdG\u00a3\u00a9r\u009d\u00c0\u00185\u00c1e\u00beaT\u0006sUw4\u0091\u0002U\u00e0\u00e6\u00c4\u00ba\u00ed^\u0005q\"^\u0010F\u00b4j9\u00c1\u00bd+^4\u00ae\u00fb\u00e4b\u00be\u009eW\u0010\u00df{%\u00c6\u00a37\u00ea\u00be\u00d3\u00ba\u00f6\u001e7\u00f4a\u000e\u0018k\u009d.\u00ae'\u00b0{\u009a\u00cc%\u0017e\u00e9Q\u00bb\u00f8\u00d5\u00e8\u0019\u00ef\u00b4\u00e6Ta(\\hd\u001e\u00d6\u00a6\u0094\u00f7E\u00db\u00de\u00fce\u00de\u0086\u00baf#s\u00a1\u001av`E\u00d3\u00e2\u00c7\u00d4\u00de\u0012\u00dc\u00d7\u00b3\u00f82\u00fd\u00bc\u00a9\u00b1u0\u00ef\u00acoM\u0014\\\u0097y\u00dc\u00beCd\u00b3\u00d2\u00e8\u00e1\u00acX\u0002\u00d2GKt\u00c9d}\u000e\u0084\u00b4\u00c6\u00ad\u001b\u0098\u00aa\u001d=\u0080\u008cKf\u0004\u00c4\u008a\u00b6\\\n\u00d8W\u0010\u0091/\u0086D\u0098\u001b\u00c2MR\u00c6\u00175\u00e5\u00ce\u0018v\u0018.\u00b9\u00f5h\u00cc\u0097\fXM_%\u000f`=\u009a\u0098\u00c40_\u00adA\u00f3\u00ad\u00af .\u00e6\u00ec\u008c(\u0018\n\u00ae\u00eb\u009a\u0082\u00e0j\u00f1\u00d8\u00d6\u00ec\u00e2H\u00a8\u00b9H=\u0094wO\u00d7<}\u00c9\u0088\u00ac\u0018\u00b3<f\u0090I\u00eb\u0095\u00f9%\u008f\u00fd\u009e`\u00f5\u0086\u00f4\u0081\u0019\u00d9\u00fe\u009a\u00ef\u00a5G \u0004\u00f2|C+\u00a4\u0002\u00e0\u00a2<\u00e4\u00bd\u0006\u00f4UB\u0096\r\u00ae-\u00f6\u00f6\u00fc\u0098\u00a3k\u0014\u00e2\u00b29gK(\u0012\r\u00be\u00bd8\u00ef&\u00ec\u009b\u0004\u00d4\u00ce\u009a$0N\u00b8\u00d3\u00e9FZj<\u00f0\u00a4=\u00b1\u0082\u0091\u00e2\u00d3e\u0006O@\u00b9\u00b8T\u00db\u0097\u0010}\u00fe%YD!^q\u0007F\u00b071|,\u00d8 \u00ad\u0097\u000eu6m\u009a\u00b08\u00c5\u00f0\u008e\u00b69\u00d2\u00c7\u000f\u00e5\u009b\u0013\u00c2M\u00d9a\u0005\u00a0\u00d6A\u0015X\u00a6{";
                        var17_6 = 984;
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
                            var18_3[var16_4++] = AutoWither.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "aAk|\no\",/e\u00deh\u0002}Z\u00e7(j\u00a1\u00cb\u00ae\u00e2n%F\u00a9K\u00cfe\u0012\u00ba\u0016\u001f\u00a8\u00c4,-\u001c\u008c/\u0018x\u00be\u00aa(\u009e\\M\u00ca\u009b\u00a4\u0000\u00be\u00a4\u00c0\u009f\u00fd\u0001 \u00a6\u00a8y\n\u00e4\u008c";
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
                            var18_3[var16_4++] = AutoWither.zkmDecodeUtf8(var19_9).intern();
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
                AutoWither.b = var18_3;
                AutoWither.c = new String[35];
                AutoWither.j = new HashMap<K, V>(13);
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
                var6_12 = new long[18];
                var3_13 = 0;
                var4_14 = "?\u00f2G\u00ddA\u00f2u\u00adN\u00edp\u00fd/3\u00fcN\u000b\u00e8\u009b\u00cb\u0005\u00ea\u00b7\u0006\u00b2^\u0002Y)}3\u0081=0:B2L-\u00ecv\u00d6i\u00f8\u00fc2\u00b7\u00df\u00cd\u0094x\u00ac\u00d5\u00d3\u0091\u000f\u00ec\u00b3\u00af>\u0012\u00bf=\u00a7UH\u00e7\u0002\u00faR\u00c1\u00ec\u00f6\u00f9<E\u00b2M\u00a8P\f\u00b6\u00cd\u009dQ\u00df\\\u0097kpZ{\u0011\u0006\u00dc\u00e3\u0010\u0097\u00eb\u0095\u00b7\u0092\u00abb\tt\u0085\u00a4U\u00c1Z\u00e9\u00da\u0091_\u00b4Cd\u00b5xa\u00aa\u00de\u00abJ\u00c4\u0091\u0003";
                var5_15 = 128;
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
                    var4_14 = "\u00dd\u00cf\u00fa\u00c0\u0081\u00d4\u00b9\u00a0\u007f\u00ee\u00a0G\u00a9\u0005p\t";
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
        AutoWither.h = var6_12;
        AutoWither.i = new Integer[18];
        var33_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "timing", "getTiming()Lsu/catlean/xl;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "blocksPerAction", "getBlocksPerAction()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "delayPerAction", "getDelayPerAction()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "rotate", "getRotate()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "placeMode", "getPlaceMode()Lsu/catlean/zr;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "interact", "getInteract()Lsu/catlean/xx;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "switch", "getSwitch()Lsu/catlean/yi;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "range", "getRange()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "wallRange", "getWallRange()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "render", "getRender()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWither.class, "pose", "getPose()Lsu/catlean/md;", 0))};
        AutoWither.delegatedProperties = var33_20;
        AutoWither.INSTANCE = new AutoWither();
        AutoWither.timing$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWither.INSTANCE, "timing", (Enum)RotatingModuleTimingMode.Vanilla, null, null, 12, null);
        AutoWither.blocksPerAction$delegate = SettingFactory.intSetting$default(AutoWither.INSTANCE, "blocks-per-action", 6, new IntRange(2, 6), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoWither.delayPerAction$delegate = SettingFactory.intSetting$default(AutoWither.INSTANCE, "delay-per-action", 0, new IntRange(0, 10), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoWither.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoWither.INSTANCE, "rotate", true, null, null, 12, null);
        AutoWither.placeMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWither.INSTANCE, "place-mode", (Enum)InteractMode.Normal, null, null, 12, null);
        AutoWither.interact$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWither.INSTANCE, "interact", (Enum)PlaceMode.Strict, null, null, 12, null);
        AutoWither.switch$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWither.INSTANCE, "switch", (Enum)SwitchMode.Silent, null, null, 12, null);
        AutoWither.range$delegate = SettingFactory.floatSetting$default(AutoWither.INSTANCE, "range", 4.0f, RangesKt.rangeTo(2.0f, 10.0f), null, null, 24, null);
        AutoWither.wallRange$delegate = SettingFactory.floatSetting$default(AutoWither.INSTANCE, "wall-range", 4.0f, RangesKt.rangeTo(2.0f, 10.0f), null, null, 24, null);
        AutoWither.render$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoWither.INSTANCE, "render", true, null, null, 12, null);
        AutoWither.pose$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWither.INSTANCE, "pose", (Enum)AutoWitherPoseMode.LYING, null, null, 12, null);
        AutoWither.positions = new ArrayList<E>();
        AutoWither.timer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x447;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/eo", exception);
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
            AutoWither.c[n2] = AutoWither.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoWither.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/eo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xD36;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/eo", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoWither.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoWither.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/eo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoWither.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoWither.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

