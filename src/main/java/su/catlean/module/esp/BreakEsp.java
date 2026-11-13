/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.InteractionManagerAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.esp.BreakEsp$WhenMappings;
import su.catlean.module.esp.BreakEspMode;
import su.catlean.module.esp.BreakingBlock;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class BreakEsp
extends Module {
    @NotNull
    public static final BreakEsp INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting external$delegate;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ColorSetting primary$delegate;
    @NotNull
    private static final ColorSetting secondary$delegate;
    @NotNull
    private static final ColorSetting text$delegate;
    private static float curBlockDamageMP;
    private static float currentProgress;
    @NotNull
    private static final CopyOnWriteArrayList breakingBlocks;
    private static final long d;
    private static final String[] e;
    private static final String[] f;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private BreakEsp() {
        super("break-esp", Categories.getEsp(), null, null);
    }

    private final boolean getExternal() {
        return (Boolean)external$delegate.getValue(this, delegatedProperties[0]);
    }

    private final BreakEspMode getMode() {
        return (BreakEspMode)((Object)mode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final Color getPrimary() {
        return (Color)primary$delegate.getValue(this, delegatedProperties[2]);
    }

    private final Color getSecondary() {
        return (Color)secondary$delegate.getValue(this, delegatedProperties[3]);
    }

    private final Color getText() {
        return (Color)text$delegate.getValue(this, delegatedProperties[4]);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (this.getExternal() && receivePacket.getPacket() instanceof BlockBreakingProgressS2CPacket) {
            Entity entity = Globals.getPlayer();
            if (((BlockBreakingProgressS2CPacket)receivePacket.getPacket()).getPos().toCenterPos().squaredDistanceTo(this.getPos(entity)) <= 144.0) {
                boolean bl;
                block6: {
                    Iterable iterable = breakingBlocks;
                    if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                        bl = false;
                    } else {
                        for (Object t : iterable) {
                            BreakingBlock breakingBlock = (BreakingBlock)t;
                            if (!Intrinsics.areEqual(breakingBlock.getPos(), ((BlockBreakingProgressS2CPacket)receivePacket.getPacket()).getPos())) continue;
                            bl = true;
                            break block6;
                        }
                        bl = false;
                    }
                }
                if (!bl) {
                    BlockPos blockPos = ((BlockBreakingProgressS2CPacket)receivePacket.getPacket()).getPos();
                    Intrinsics.checkNotNullExpressionValue(blockPos, "getPos(...)");
                    DefaultConstructorMarker defaultConstructorMarker = null;
                    float f = 0.0f;
                    float f2 = 0.0f;
                    BlockPos blockPos2 = blockPos;
                    breakingBlocks.add(new BreakingBlock(blockPos2, f2, f, defaultConstructorMarker));
                }
            }
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        breakingBlocks.removeIf(arg_0 -> BreakEsp.invokePredicate(BreakEsp::onPlayerUpdate$lambda$0, arg_0));
        currentProgress = curBlockDamageMP;
        ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
        Intrinsics.checkNotNull(clientPlayerInteractionManager, "null cannot be cast to non-null type su.catlean.mixins.accessors.InteractionManagerAccessor");
        curBlockDamageMP = ((InteractionManagerAccessor)((Object)clientPlayerInteractionManager)).getCurBlockDamageMP();
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        Object object;
        long l = d ^ 0x406DDA0F4D37L;
        long l2 = l ^ 0x4D77348C409EL;
        Intrinsics.checkNotNullParameter(render3DEvent, "e");
        if (Globals.getMc().crosshairTarget != null && Globals.getMc().crosshairTarget instanceof BlockHitResult) {
            HitResult hitResult = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(hitResult, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            Intrinsics.checkNotNullExpressionValue(blockPos, "getBlockPos(...)");
            object = blockPos;
            if (Globals.getInteractionManager().isBreakingBlock() && !Globals.getWorld().isAir((BlockPos)object)) {
                this.renderBlockBreak((BlockPos)object, l2, RangesKt.coerceIn(RenderUtil.INSTANCE.lerp(currentProgress, curBlockDamageMP, RenderUtil.INSTANCE.getTickDelta()), 0.0f, 1.0f));
            }
        }
        object = breakingBlocks;
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            BreakingBlock breakingBlock = (BreakingBlock)t;
            INSTANCE.renderBlockBreak(breakingBlock.getPos(), l2, RangesKt.coerceIn(RenderUtil.INSTANCE.lerp(breakingBlock.getPrevProgress(), breakingBlock.getProgress(), RenderUtil.INSTANCE.getTickDelta()), 0.0f, 1.0f));
        }
    }

    private final void renderBlockBreak(BlockPos blockPos, long l, float f) {
        long l2 = (l = d ^ l) ^ 0x57B634FEB3F0L;
        Box box = this.getBreakBox(blockPos, f);
        Object var26_6 = null;
        Color color = null;
        Color color2 = RenderUtil.INSTANCE.blend(this.getPrimary(), this.getSecondary(), f);
        Box box2 = box;
        RenderState renderState = RenderState.INSTANCE;
        RenderState.addFilledBox$default(renderState, box2, color2, color, var26_6);
        Object var34_11 = null;
        Color color3 = null;
        Color color4 = RenderUtil.INSTANCE.withAlpha(RenderUtil.INSTANCE.blend(this.getPrimary(), this.getSecondary(), f), 1.0f);
        Box box3 = box;
        RenderState.addOutlineBox$default(RenderState.INSTANCE, box3, color4, color3, var34_11);
        Vec3d vec3d = box.getCenter();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
        RenderState.drawWorldText$default(RenderState.INSTANCE, l2, (int)(f * 100.0f) + "%", vec3d, this.getText(), 0.0f, 0.0f, false, null, 120, null);
    }

    private final Box getBreakBox(BlockPos blockPos, float f) {
        return switch (BreakEsp$WhenMappings.a[this.getMode().ordinal()]) {
            case 1 -> new Box(blockPos);
            case 2 -> {
                float var7_3 = 1.0f - f;
                Box var6_4 = new Box(blockPos).shrink(var7_3, var7_3, var7_3).offset((double)var7_3 / 2.0, (double)var7_3 / 2.0, (double)var7_3 / 2.0);
                Intrinsics.checkNotNull(var6_4);
                yield var6_4;
            }
            case 3 -> {
                Box var6_5 = new Box(blockPos).shrink(f, f, f).offset((double)f / 2.0, (double)f / 2.0, (double)f / 2.0);
                Intrinsics.checkNotNull(var6_5);
                yield var6_5;
            }
            case 4 -> {
                Box var6_6 = new Box(blockPos).withMaxY((double)blockPos.getY() + (double)f);
                Intrinsics.checkNotNull(var6_6);
                yield var6_6;
            }
            case 5 -> {
                Box var6_7 = new Box(blockPos).withMaxY((double)blockPos.getY() + (double)(1.0f - f));
                Intrinsics.checkNotNull(var6_7);
                yield var6_7;
            }
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    private static final boolean onPlayerUpdate$lambda$0(BreakingBlock breakingBlock) {
        return breakingBlock.tick();
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        BreakEsp.d = ZkmKeys.a(-5936121614274688790L, 8831190321826685800L, MethodHandles.lookup().lookupClass()).a(173938899466364L);
                        var20 = BreakEsp.d ^ 136273210810720L;
                        BreakEsp.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[21];
                        var16_4 = 0;
                        var15_5 = "\u0087\u00f1\u00fccF\u00a2s\u00b8\u0000\u00ac\u00c0A\u000fg\u00f3y\u0088\u00c9D\u0091\u0089\u00f5H\u00e8\u00c5\u00d3\u0014\u0084m7\u00e1\u00f21\bz\u00ccf/\u00ee\t)\u0090\u00ef\u0014i\u00fc\u00f1\u0097\u00cbp\u00fb\u0096\u00adM&\u0097g\u0093_\u001fF]\u0016\u00ff\u00bb\u001c\u00a1R\u0093\u00cfW\u0088 \u00bc\u00af\u008cw\u00a0Y}\u0016\u00c4\u00b2Cu\u0005\u00fc\u00ff\u00a9)?\u0004\u001f\u00fe\u0005\u0006\u008c\u00b4\u0089\u000e\u00ae\u00c7)F.2\u009c\u00e7\u00cb\u00b7\u00e54\u00a0?q\u00e8\u00d4\u00f6\u00e6\u00ed\u0003\u009a\u00a5O!r\u0011r\r\u00c2A\\\u0095\u00ad\u00f2\u00f5\u0010\u00be\u001cC\u00f0\u00bc\u000e\u00af\u0080\u00e4\u000e\u00d6\u0085\u0013\u00df\u000f\u0092\u0010\u00a8I:\u0096\u008c\u0090o\u008b]\u00b4f\u00aan\u00c1\u007f\u00c6(\u00b7&\u00b4\t\u0003-qx5\u00d7\u0098\r\u00cd\u00dc\u00a3\r\u00b8\u00fdY\u00a6ZtU\u00f17Z\u0017[\u00c0\u00fd\u001e\u00f5r@o\u00dc\u00c0\u0092\u0092\u00d6\u0098\u000fL>o{.\u0093dc\u0093\u00bf\u00c1`\u00cb\u00ac\u001cL/+k\u0096d\u00da\u001e\u00c1st\u00d3Ua\u007f\u00de\u00f9\u0098\u00d0\u0091oDZ\u00c2\u00d7\u00d0\u00ae\u0017HbC\u00cd\u001f\u00d3\u009eZ\u00e7\u00b5k\u0002\u00c2&\u00c8V\u00c9*K\u00c3$\u00f6W\u00e9.\u00bae\u00a2\u00e2\u00c8A\u001f\u0012\u00a3\u00f8]E\\]\u0005\u00bb+ct\u00e3\u00109\u0080\u00f1),\u0015\u001c\u0002\u00fe\u00f6\u00a6\u0092\u00fde\u00bb1\u00f7\u001d\u00ad+O\u00bf\u00c9O\u00b9\u00a5\u00ad==\u00c2\u00e3\u00f6\u00a8f\u00c1\u000e@(\u0095\u00b7\u007f[6\u00bd\u00e5\u00ac\u00b1\u00ecf}\u00ea\u0096]\u00e0*\u0014h\u00ad\u007f\u00c4v\u009e S\u0001\u000b/[\u00f1Na3\u00far_\u008c\u00caN\u00e3tY`9\u00e2\u00ce'QB\u00d3\u00fdX\u00d0\u00fbc \u0018\u0096\u00d3\u00d8\u00ad.\u00dff\u0013e=\r7\u00dd\u009e\u00a4\u00f7\u00ed\u00d1-\u00fb\u00d1\u00f0\u0080\u00ac \u0089\u00d9\u00ad\u0096\u00e718\u0094^\u00b9\u00b2mW\u00acM\u0094\u0084\u00b9\u00f8 \u00d1+\u00e4a\u001d\u00ccQ\u00f8x\u00b0\u00a6k0\u00f5\u0001\u00feV\u008f\u009aK\u00b4p\u0089\u00ce\u00e8{\u00ee\u0007\u00d5\u00b5\u00e6\u0094w\u00c6'\u00bc,\u00bb>\u00acV\u000e\u00b17v\u0095%\u0011TT\u00b2\u00f5)zp0\u00c2\u001a\u0001\u00d6}\u0010\u00c8^\u00e6\u00af6\u0083.s\u00c3 8\u00d5Jh9`8 D\u00b0\u008bvv\u0017\u0087\u00a0\u00df^_LYr\u009c\u008dE3\u00c1\u00b1\u007f\u00e8\u0018\u00c4\u00c2\u000b\u001e\u00d9\u007f.\u00e6<\u00d1ETK\u00f68``\u0010\u00cf\u0098-=\u00c4mzo\u00d2\u0005\u00c8\u00f9\u0087\u00e1\u0018J\u00be\u001e\u00ad\u00b7\u00fd3P\u00c7c#l<\u00b3%\u000b\u00e5p\u0084\u00e0\u00c4\u0014\u009dd8\u00dcr\u00eb,d\u00adq\u0090\u0093h\u00aadS\u00ea\u000fr\u001do>\u000b@\u00eb\u00cab\u00c7\u00ce]\u00c4\u007fy\u009a\u001a\u00d7\u00e0\u0005\u00fc\u00dd|\u00c1\u009f\u0093\u0007\u00d1\u00b5S\u000e\u009a}OgKP9\u00d2\u00aex\u0010\u00a7M\u00a9\u0086[a\u00f5\u00c8U\u00c7\b\u001e\u00f7\u00b5\u00d8\u00fc 7\u00b2*\u0085\u00d1Q<\u00b0\u001a\u0096\u0000\u00195\u0015\u0006\u00fd\u0015`\u00d2`r\u00c7\u00fc]\u009b\u00e5\u00cen\u001b\u00bd\u00fe\u008e \u00ce96\u00e7\u00baY\u00bb\u00d4\u00ba\u00ab\u00de\u0016t\u00fdr(\u00e1\u0000\u00a9\u008d\u0098j\u00c5\u00a0?\u00eb\\x\u0000\u0003\u00f5B \u0080[\u001d\u00a3\u00a3\u00b1\tL\\d\u00f0\n\u00b1WaL\u00d2\u0087]\u00f9\u00c2<\u0010c\u0086\u0082~:\u0081\u0099\u0005\u00f38\u008e\u00c38SI\u00bd`X\u0007\u0092\u00d1c\u00d9\u00e6J\u00c5d\u00db\u0084?\u001b\u0005\u000b\u00d9S!\u0099\u00cd\u00eaL\u00be\u0081\u00af\u00e6\u00eb\u00c2\u00f4\u0085\u0016\f\u00fa\u0012@\u00b5A4\u00eaH\u0081\u009ch\u00fc\u0019*\u00ab\u00b1\u0010cz\u00b7\u00f7V-\u00bbM\u00edQ;\u00b9=6\u00db\u00c5";
                        var17_6 = 850;
                        var14_7 = 136;
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
                            var18_3[var16_4++] = BreakEsp.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u001bW\u0010 J\u00f7!\u00c7\u009dV-*a\u00de\u00dbux\u00be\u007f\u00fd\u00f7\u00b0\u00ce\u00ef'\u00ed\u00c3YV\u00c0{\u0013\u0010V\u00c8\u0015\u00e7\u00d5\u00f0\u00a4\u00fc\u00dd\u0005\u001f\u00b1z\u0014S\u00fd";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = BreakEsp.zkmDecodeUtf8(var19_9).intern();
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
                BreakEsp.e = var18_3;
                BreakEsp.f = new String[21];
                BreakEsp.j = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "\u00e0NzZN{\u0089\u0002\u00e7\u0083\u00b5\u00a0_J\u00942cm\u00e3X\u001d=\u00e4\n\u0085\u00a3b%\u007f\u00db\u00e5\u001c\u0093\u00f5\u00ec\u0085U\u00c2\u00e3\u00b4Eo\u0010\u001f\u00e2\u00a2$\u00d7";
                var5_15 = 48;
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
                    var4_14 = "Xc\u00c4wI\u00c7\u00cc\u009a6\u00fc\u00d3r#\u00f3\fO";
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
        BreakEsp.h = var6_12;
        BreakEsp.i = new Integer[8];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(BreakEsp.class, "external", "getExternal()Z", 0)), Reflection.property1(new PropertyReference1Impl(BreakEsp.class, "mode", "getMode()Lsu/catlean/c4;", 0)), Reflection.property1(new PropertyReference1Impl(BreakEsp.class, "primary", "getPrimary()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(BreakEsp.class, "secondary", "getSecondary()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(BreakEsp.class, "text", "getText()Ljava/awt/Color;", 0))};
        BreakEsp.delegatedProperties = var32_20;
        BreakEsp.INSTANCE = new BreakEsp();
        BreakEsp.external$delegate = SettingFactory.booleanSetting$default((SettingContainer)BreakEsp.INSTANCE, "external", true, null, null, 12, null);
        BreakEsp.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)BreakEsp.INSTANCE, "mode", (Enum)BreakEspMode.SHRINK, null, null, 12, null);
        BreakEsp.primary$delegate = SettingFactory.colorSetting$default(BreakEsp.INSTANCE, "primary", new Color(255, 255, 255, 100), null, null, 12, null);
        BreakEsp.secondary$delegate = SettingFactory.colorSetting$default(BreakEsp.INSTANCE, "secondary", new Color(255, 255, 255, 100), null, null, 12, null);
        BreakEsp.text$delegate = SettingFactory.colorSetting$default(BreakEsp.INSTANCE, "text", new Color(255, 255, 255, 255), null, null, 12, null);
        BreakEsp.breakingBlocks = new CopyOnWriteArrayList<E>();
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x35C5;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/k_", exception);
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
            BreakEsp.f[n2] = BreakEsp.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BreakEsp.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7987;
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
                throw new RuntimeException("su/catlean/k_", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BreakEsp.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BreakEsp.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BreakEsp.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BreakEsp.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

