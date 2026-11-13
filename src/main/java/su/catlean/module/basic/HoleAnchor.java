/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoDripstope;
import su.catlean.module.basic.MoveFix;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.HoleManager;
import su.catlean.util.MovementUtil;
import su.catlean.util.TimerManager;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class HoleAnchor
extends Module {
    @NotNull
    public static final HoleAnchor INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting disableOverHole$delegate;
    @NotNull
    private static final IntSetting pitch$delegate;
    @NotNull
    private static final FloatSetting maxSpeed$delegate;
    @NotNull
    private static final FloatSetting rubberBandSpeed$delegate;
    @NotNull
    private static final IntSetting rubberBandTimeOut$delegate;
    @NotNull
    private static final BooleanSetting useTimer$delegate;
    @NotNull
    private static final FloatSetting timerValue$delegate;
    @NotNull
    private static final IntSetting hRange$delegate;
    @NotNull
    private static final IntSetting vRange$delegate;
    @NotNull
    private static final IntSetting prioritizedFov$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map j;

    private HoleAnchor() {
        super("hole-anchor", Categories.getBasic(), null, null);
    }

    private final boolean getDisableOverHole() {
        return (Boolean)disableOverHole$delegate.getValue(this, delegatedProperties[0]);
    }

    private final int getPitch() {
        return ((Number)pitch$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final float getMaxSpeed() {
        return ((Number)maxSpeed$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final float getRubberBandSpeed() {
        return ((Number)rubberBandSpeed$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    private final int getRubberBandTimeOut() {
        return ((Number)rubberBandTimeOut$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final boolean getUseTimer() {
        return (Boolean)useTimer$delegate.getValue(this, delegatedProperties[5]);
    }

    private final float getTimerValue() {
        return ((Number)timerValue$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    public final int getHRange() {
        return ((Number)hRange$delegate.getValue(this, delegatedProperties[7])).intValue();
    }

    public final int getVRange() {
        return ((Number)vRange$delegate.getValue(this, delegatedProperties[8])).intValue();
    }

    private final int getPrioritizedFov() {
        return ((Number)prioritizedFov$delegate.getValue(this, delegatedProperties[9])).intValue();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block1: {
            BlockPos blockPos;
            long l = a ^ 0x38BD18B8FAD7L;
            long l2 = l ^ 0x1A947B574949L;
            long l3 = l2 >>> 16;
            int n = (int)(l2 << 48 >>> 48);
            if (!MoveFix.INSTANCE.getEnabled()) {
                return;
            }
            BlockPos blockPos2 = blockPos = this.pullIntoHole(l3, (char)n);
            if (blockPos2 == null) break block1;
            BlockPos blockPos3 = blockPos2;
            Vec3d vec3d = blockPos3.down().toCenterPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            Vec3d vec3d2 = vec3d;
            RotationManager.INSTANCE.rotate(Rotation.Companion.to(vec3d2).withMoveFix(su.catlean.util.rotation.MoveFix.FORCE), 0);
        }
    }

    @Flow(priority=-10)
    private final void onMove(MoveEvent moveEvent) {
        block2: {
            BlockPos blockPos;
            long l = a ^ 0x186127F63E7FL;
            long l2 = l ^ 0x3A4844198DE1L;
            long l3 = l2 >>> 16;
            int n = (int)(l2 << 48 >>> 48);
            if (MoveFix.INSTANCE.getEnabled()) {
                return;
            }
            BlockPos blockPos2 = blockPos = this.pullIntoHole(l3, (char)n);
            if (blockPos2 == null) break block2;
            BlockPos blockPos3 = blockPos2;
            Vec3d vec3d = blockPos3.down().toCenterPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            Vec3d vec3d2 = vec3d;
            Entity entity = Globals.getPlayer();
            Entity entity2 = Globals.getPlayer();
            Vec3d vec3d3 = vec3d2.subtract(INSTANCE.getPos(entity)).normalize().multiply(Math.min(INSTANCE.isRecentlySetback(), vec3d2.distanceTo(INSTANCE.getPos(entity2))));
            Intrinsics.checkNotNullExpressionValue(vec3d3, "scale(...)");
            Vec3d vec3d4 = vec3d3;
            moveEvent.setX(vec3d4.x);
            moveEvent.setZ(vec3d4.z);
            moveEvent.cancel();
            if (INSTANCE.getUseTimer()) {
                TimerManager.INSTANCE.x(INSTANCE.getTimerValue());
            }
        }
    }

    private final BlockPos pullIntoHole(long l, char c) {
        BlockPos blockPos;
        BlockPos blockPos2;
        Object object;
        Object object2;
        Object object3;
        int n;
        long l2 = (l << 16 | (long)c << 48 >>> 48) ^ a;
        long l3 = l2 ^ 0x31154BCD5300L;
        Entity entity = Globals.getPlayer();
        BlockPos blockPos3 = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos3, "containing(...)");
        BlockPos blockPos4 = blockPos3;
        if (Globals.getPlayer().getPitch() < (float)this.getPitch()) {
            return null;
        }
        if (HoleManager.INSTANCE.isHole(blockPos4)) {
            if (this.getDisableOverHole()) {
                this.disable(l3);
            }
            return null;
        }
        List list = new ArrayList();
        int n2 = blockPos4.getX() - this.getHRange();
        if (n2 <= (n = blockPos4.getX() + this.getHRange())) {
            while (true) {
                int n3;
                int n4;
                if ((n4 = blockPos4.getZ() - this.getHRange()) <= (n3 = blockPos4.getZ() + this.getHRange())) {
                    while (true) {
                        int n5;
                        int n6;
                        if ((n6 = blockPos4.getY() - this.getVRange()) <= (n5 = blockPos4.getY())) {
                            while (true) {
                                object3 = new BlockPos(n2, n6, n4);
                                Intrinsics.checkNotNullExpressionValue(((BlockPos)object3).down().toCenterPos(), "getCenter(...)");
                                boolean bl = DamageUtil.INSTANCE.canSeeFromEyes((Vec3d)object2);
                                if (bl) {
                                    BlockPos blockPos5 = ((BlockPos)object3).down();
                                    Intrinsics.checkNotNullExpressionValue(blockPos5, "below(...)");
                                    if (HoleManager.INSTANCE.isHole(blockPos5)) {
                                        list.add(object3);
                                    }
                                }
                                if (n6 == n5) break;
                                ++n6;
                            }
                        }
                        if (n4 == n3) break;
                        ++n4;
                    }
                }
                if (n2 == n) break;
                ++n2;
            }
        }
        Iterable iterable = list;
        Iterator iterator2 = iterable;
        Object object4 = new ArrayList();
        object3 = iterator2.iterator();
        while (object3.hasNext()) {
            object2 = object3.next();
            BlockPos blockPos6 = (BlockPos)object2;
            if (!(AutoDripstope.INSTANCE.getYawTo(blockPos6) <= (float)INSTANCE.getPrioritizedFov())) continue;
            object4.add(object2);
        }
        iterable = (List)object4;
        iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            object = null;
        } else {
            object4 = iterator2.next();
            if (!iterator2.hasNext()) {
                object = object4;
            } else {
                BlockPos blockPos7 = (BlockPos)object4;
                Entity entity2 = Globals.getPlayer();
                double d = INSTANCE.getPos(entity2).distanceTo(blockPos7.toCenterPos());
                do {
                    object3 = iterator2.next();
                    object2 = (BlockPos)object3;
                    Entity entity3 = Globals.getPlayer();
                    double d2 = INSTANCE.getPos(entity3).distanceTo(((BlockPos)object2).toCenterPos());
                    if (Double.compare(d, d2) <= 0) continue;
                    object4 = object3;
                    d = d2;
                } while (iterator2.hasNext());
                object = object4;
            }
        }
        if ((blockPos2 = (blockPos = (BlockPos)object)) == null) {
            Object object5;
            iterable = list;
            iterator2 = iterable.iterator();
            if (!iterator2.hasNext()) {
                object5 = null;
            } else {
                object4 = iterator2.next();
                if (!iterator2.hasNext()) {
                    object5 = object4;
                } else {
                    BlockPos blockPos8 = (BlockPos)object4;
                    Entity entity4 = Globals.getPlayer();
                    double d = INSTANCE.getPos(entity4).distanceTo(blockPos8.toCenterPos());
                    do {
                        object3 = iterator2.next();
                        object2 = (BlockPos)object3;
                        Entity entity5 = Globals.getPlayer();
                        double d3 = INSTANCE.getPos(entity5).distanceTo(((BlockPos)object2).toCenterPos());
                        if (Double.compare(d, d3) <= 0) continue;
                        object4 = object3;
                        d = d3;
                    } while (iterator2.hasNext());
                    object5 = object4;
                }
            }
            blockPos2 = (BlockPos)object5;
        }
        return blockPos2;
    }

    private final double isRecentlySetback() {
        return MovementUtil.INSTANCE.getTimeSinceSetback() < (long)(this.getRubberBandTimeOut() * 1000) ? 0.2873 * (double)this.getRubberBandSpeed() : 0.2873 * (double)this.getMaxSpeed();
    }

    private static final boolean settingVisible() {
        return !MoveFix.INSTANCE.getEnabled();
    }

    private static final boolean settingVisible2() {
        return !MoveFix.INSTANCE.getEnabled();
    }

    private static final boolean settingVisible3() {
        return !MoveFix.INSTANCE.getEnabled();
    }

    private static final boolean settingVisible4() {
        return !MoveFix.INSTANCE.getEnabled();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getUseTimer() && !MoveFix.INSTANCE.getEnabled();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        HoleAnchor.a = ZkmKeys.a(-3363508769263124191L, -1191212947661654777L, MethodHandles.lookup().lookupClass()).a(32579613076829L);
                        var20 = HoleAnchor.a ^ 46817702926260L;
                        HoleAnchor.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[36];
                        var16_4 = 0;
                        var15_5 = "\u00c8~A@\u008d:\u00cffb\u00d4P?\u00ec\u00e6E\u00a8O\u001b\u00f8\u001a\u00bd\u00d5\u00ca\u00b8w\u00b7\u0019\u00de\u0013\u00f7\u00d6\u00ea\u0018\u00f7\u00e4^\u00a3\u0018\n\u00df\u00e7\u00fc\u00fc\u00cd\u00a9\u00ea\u00bar1V\u00d3\"/\u00df\u00ca_\u00bc\u0010\u001e\u00c31\bGz\u0016\u00e9\u00cb\u00fc[=[q>\u009a(Ii\u008f\u00d3\u00d6\u0084'\u00e9h{\u00b0\u00fa\u00d2\u0099\u00ff\u0094\u00e5\u00942\u00d3\u00de\u00de'y>\u00d6\u00cb\u00bf\u001fn;\u00a7\u00ea\u0014\u0014\u00f0\u00d3\\H, \u00a6\u00e3y\u00aeg\u0018\u00f6l\u00d6\u00ea\u00ef\u0018\u00b6\u0082\u00c1\u00e3o\u00cf\u0082\u0005+v\u0082\u00920\u00a1}\u00af\u0094^\u00fb) \u009f\u0018\u008a\u00d4kM1\u0080\u00c1\u0015\u0099\u008e\u00f5.\u00ee\u0092C\u00bb\u008f\u008f\u00e4?\u009e\u00ddL\u00b2Wg\u0019oN\u00ab(\u009d\u00b2Z\u00125\t[\u009b`\u00d7\u00f9\u00c3\u00ee\u00ab6\u00a4\t\u00fa=\u0088x\u00ea\u00ba\u00b7Ma\u0092\f\u00f8\u00e8\u00f8 \u00b3HT\u00a1\u000f/8\u0099(\u007fE\u00c1\u00fd\u00abA\u001c\u00d1L\u0090\u00af\u00bcgQ\u00be\u00b9\u00ca\u0087\u00e76T\u00c1\u0003\u00a1]\u00ee\u00ac\u000e\u00a6e\u00dc\u00e3\u001fX\u009e.\u00e1\u00b0h\u00a1 \u00a4\u00fb-9V\u00bc\u00a7\u00c2\u000e\u00ba\u0007\u001al#_A\u00b3\u0016^;\u008a=\u00b1\u0017A\u00c77'\u00b4\u00b4\u000f\u00d5\u0018O\u00d5\u00ba\u008am\u00bf\u00bb?\u0088Mr\u0094\u00b9\u00b6\u00ab\u00b6Oz\u00f0\u0016|\u008b\u00c5m \u00db{\u00eaA\u0083S)\u0010\u00e9uSO\u00ab\u00da\u00e8\u00a7\u008d\u00adk\u0000PU\u00f2\u0087q\u0089.\u0015\u000f%\u00c0u\u0018k\u00c2S\u007fJ/<\u0083\u00e9\u0014\u0092\u009e\u00e8\u00b31\u00dd\u001f\u00aa\u00e4\u00a9\u00bc\u00f7\u00f29\u0010P\u0003k-\u009b\u00f4\u007fg\u00b8\u00a81\u00d3\u00ed\u00e8\u00a5\u0018 >\u0018Hj\u00aba|\u0092\u00f2\u0010h\u0007F\u009b\u0016'.V\u00d8R\u00fa \u00cb\u000e!\u00c8\u00f5\u00aa\u00e6M\u00dd\\\u0018\u0017\u009d\u00da\u00a5\u00a9\u0088\u00e2a8\u0004Z\u00bbb#\u00ab\u0007\"\u00c9\u00d4\u00a0\u0088\u009d\u0001T(\u0096\u00c1E!\u0017\u0012\u0004\u00d8\u00d6=\u001b'n_\u00b7e\u0003O0\u009bs\u00c69Fto\u00aa0\u0083{\u00de\u0081 \u007f\u00a3\u00f3\u00886\u00d3\b\u0018>DBd]#\u0018\u00f6\u00ef\u00b2\u000e%0\u00d6\u00b0\u00df\u00a0\u0007\u0005:\u00c4\u00a5W\u00ab o\u0006\u00fc\u00ef\u00e1\u00fd0\u00aav>\u00b3\u00eb0\u0084I@\u00d3\u008eO\u007f|&\u00ee\u00fa\u00e3\u00bf/\u00f9\u00e5\u00cf\u0093A\u0018\u00e0\u00d8\u0095m\u00e2f8k\u0080M\u00f1\u001f23t>u\u007f \u0081\n\u00f1fs\u0010\u00aa\u00cef9\u0005B(\u00fd\u00bb\u00b7n\u00ea\u0083\u001f\u001cU\u0018\u0093a\u001d\u00b1\u00a0\u00bai|\u00e52b\u00a2\u0015X\u00f4f\u0013\u009e\u00a0B\u0093\u009e0w(\u00e4\u0002T\u0011\u0083\u00d5\u001f\u00e0k\u00cb\u00fd}\u00ed\u0096\u00f2\u00ab\u009bvE\u00b8V\u00ce\u00f2J\u00880e\u008a\u0004Q\u001a.Cs\u0080\u0010k\u0007\u00a9\u001f \u00f8\u009e\u00caI\u0096x.\u001f\u00af\u00e2uZvl<x\u00d9\u00b8(\u001a+\u00c0)\u00a8<\u0097Y\u00c4\u00a3\b+*\u0018\u00c7M\u00a3\u008f#\u00b9\u001a\u00d9\u00d9\t[\u00fa\u00a7\u0094\u00bc\u000b\u00b7\u00c0\u0006\u00f3s\u00a4*\u0085 \u00ce\u00a5\t\u00bav\u00ab\u00b2\u0011r\u00fe\u00ba\u0007:\u00e9\u00f8\u00e2q\u0000\u00bd\u00ff\u00df\u0019r\u00bdT\u0084\b\u00ea\u0089\u00a7\u00ff\u00b0\u0010\u00a1\\`z\u0098\u00ef\u00ce\u008a0{\u0088N\u00b5\u008ex\u00e4(\u00d3z\u00da\u00f4\u0098\u00c3\u00ed\u00f5\u001c\u007fS0{\u00b9\u0091\u00ee\u008ci\u00ef\u00ec0\u0016Mu\u00e2\u000b\u00bc\u0010\u00dcBE\u00fdY\u0018\u00e7L\u00cf\u00a2\u0004\\\u0018\u00cc\u0012\u0097`\u00f2\u001cZ~~M\u00f5\u0091\u0095Bg\u0012\u00e0\u00cc[\u00d3\u0006,k\u00b1(\u00a3\u0005\u0095\u00ed\u00b7l\u0018\u0016\u00f8)\u000eX\u0007j\u00d5\u00c3[Y\\z,K,\u00bd\u0085%\"h\u00f3\u00d52,\u00fcsF\f\u009c\u00b2\u00f8*\u0018j\u00b8y\u0088\u007f\u0004\u0003%u\u00e9(}\u00a3E\u00a3Ze\u00dfXX<\u0019\u0003Q +\u000f\u00e6\u00c8\\\u008d2L>8\u00e9\u0081\u001e\f\u00fb\u00ea\u009f\u00d6\u00adgYK\u00a5\u00f5\u0006\u0011U\u0096Ml\u00ec\u00ab(f\u00af\u00d5\u00c6\u0087\u00bf&$\u0015\u00d1B\u00f4\u000e\u0014p\u00d6\u00eb\u001d\u00bcT\u0084\u0001_\u0016\u00b5\u008d<\u00bb\u0019e\u00bfB')\u0095H\u00f6\u00b1V\u009a(\u009d\u008bF\tM\u001a\u0017\u00f7p\r\u00df\u00b9\u0010\u0082\u00ff\u00b7e\u00a8\u00d6\u00e4\u00f5r\u00c9\u00ae\u00cc\u00b3\u00c9W\u0090\u0003\u001bh\u0004\u00ca\u00b2\r\u00a5G\u00ae\u00d2(Dv\u00be\u0094\u00b6\u009b\u00a9\u00f3\u00a9\u00c9\u00c9H\u00c1\u00b2\u00a7\u00ed\u00e1t\u009c\u0018\u0098\u009ar\u00e6#\u00f0\u00bc\u00f95\u00866Hy\u00d2\u0083\u0003\u0088b\u00f2\u000f";
                        var17_6 = 1057;
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
                            var18_3[var16_4++] = HoleAnchor.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e9jH\u00ef'#\u00ba\u001f?>l\u00d07\u0006 z\u00b5\u00a1[\u00ad\u00d4\r\u00c3\u00d0\u00932\u00d5\u0088\u009e\u00a1wO \u0081\f\u0091G\u00ef\u00e9\u00ba\t]\u00c8\u00c1\u0095\u0017\u00bdb\u0095\u00dc\u001aL\u00b3p\u0086\u0015\u00ba\u00b4\u00b8\u00c5\u00f0\u00aa\u00ba\u0090\u0019";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = HoleAnchor.zkmDecodeUtf8(var19_9).intern();
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
                HoleAnchor.b = var18_3;
                HoleAnchor.e = new String[36];
                HoleAnchor.j = new HashMap<K, V>(13);
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
                var6_12 = new long[19];
                var3_13 = 0;
                var4_14 = "\n\u0097\u00fe%\u0083~\u00a2(\u0000\u00e4\u00bc\u0097'\u00d5W\u00bfs\u00c50\u00f7\u00b0\u00a3\u00f89~\u00e8pw\u00e3H\u00e4\u00e3\u00b8*\u00d3F\u0097\u00f2\u00b8\u0014\u00026\u00817a\u0093g\u008c\u0013\u0012_\u001dD\u00e8\u0016\u00d4\u00a6M\u00d5m\u0099\u00b8\u00a0\n\u00a9b\u00df\u008a\u0017\u0086\u00bf\u00da\u00fd\u00d6;\u00d6\u0018\u0017\u0000y\u0090jO\\er\u0089\u0098UA,\u00f8\u00aa^\u00fc\u00e4\u00bb\u00ecu\\\u00f3N\n\u00fb\u00dc\u001f4|\u0096S\u00dfBqV^r8\u00e5\u00d6\u00e2\u00bd&\u0086\u0080:\u008a\u00b6k7\u0092\u0006A\u00f8\u0089{p";
                var5_15 = 136;
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
                    var4_14 = "S\u00f6\u00b9Nw\u00d5W^\u00d5\u0014\u000e\u00f4\u0088|oZ";
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
        HoleAnchor.g = var6_12;
        HoleAnchor.h = new Integer[19];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "disableOverHole", "getDisableOverHole()Z", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "pitch", "getPitch()I", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "maxSpeed", "getMaxSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "rubberBandSpeed", "getRubberBandSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "rubberBandTimeOut", "getRubberBandTimeOut()I", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "useTimer", "getUseTimer()Z", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "timerValue", "getTimerValue()F", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "hRange", "getHRange()I", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "vRange", "getVRange()I", 0)), Reflection.property1(new PropertyReference1Impl(HoleAnchor.class, "prioritizedFov", "getPrioritizedFov()I", 0))};
        HoleAnchor.delegatedProperties = var30_20;
        HoleAnchor.INSTANCE = new HoleAnchor();
        HoleAnchor.disableOverHole$delegate = SettingFactory.booleanSetting$default((SettingContainer)HoleAnchor.INSTANCE, "disable-over-hole", false, null, null, 12, null);
        HoleAnchor.pitch$delegate = SettingFactory.intSetting$default(HoleAnchor.INSTANCE, "pitch", 60, new IntRange(0, 90), null, null, 24, null);
        HoleAnchor.maxSpeed$delegate = SettingFactory.floatSetting$default(HoleAnchor.INSTANCE, "max-speed", 2.0f, RangesKt.rangeTo(0.1f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        HoleAnchor.rubberBandSpeed$delegate = SettingFactory.floatSetting$default(HoleAnchor.INSTANCE, "rubberband-speed", 1.0f, RangesKt.rangeTo(0.1f, 5.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        HoleAnchor.rubberBandTimeOut$delegate = SettingFactory.intSetting$default(HoleAnchor.INSTANCE, "rubberband-timeout", 10, new IntRange(1, 30), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        HoleAnchor.useTimer$delegate = SettingFactory.booleanSetting$default((SettingContainer)HoleAnchor.INSTANCE, "use-timer", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        HoleAnchor.timerValue$delegate = SettingFactory.floatSetting$default(HoleAnchor.INSTANCE, "timer-value", 1.0f, RangesKt.rangeTo(1.0f, 10.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        HoleAnchor.hRange$delegate = SettingFactory.intSetting$default(HoleAnchor.INSTANCE, "horizontal-range", 3, new IntRange(1, 3), null, null, 24, null);
        HoleAnchor.vRange$delegate = SettingFactory.intSetting$default(HoleAnchor.INSTANCE, "vertical-range", 3, new IntRange(1, 3), null, null, 24, null);
        HoleAnchor.prioritizedFov$delegate = SettingFactory.intSetting$default(HoleAnchor.INSTANCE, "prioritized-fov", 30, new IntRange(10, 180), null, null, 24, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6B87;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/_f", exception);
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
            HoleAnchor.e[n2] = HoleAnchor.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HoleAnchor.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_f" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x290E;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/_f", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HoleAnchor.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HoleAnchor.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_f" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HoleAnchor.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HoleAnchor.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

