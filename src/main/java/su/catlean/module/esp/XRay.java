/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.LightMapUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.XRayBlockEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.render.Theme;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ItemUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.RandomUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class XRay
extends Module {
    @NotNull
    public static final XRay INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BlockListSetting selectedBlocks$delegate;
    @NotNull
    private static final BooleanSetting brutForce$delegate;
    @NotNull
    private static final BooleanSetting fast$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final IntSetting radius$delegate;
    @NotNull
    private static final IntSetting up$delegate;
    @NotNull
    private static final IntSetting down$delegate;
    @NotNull
    private static final Timer timer;
    @NotNull
    private static final ArrayList foundBlocks;
    @Nullable
    private static BlockPos blockPos;
    @NotNull
    private static Box scanBox;
    private static boolean brutForce;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    private XRay() {
        super("x-ray", Categories.getEsp(), null, null);
    }

    private final BlockListValue getSelectedBlocks() {
        return (BlockListValue)selectedBlocks$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setSelectedBlocks(BlockListValue blockListValue) {
        selectedBlocks$delegate.setValue(this, delegatedProperties[0], blockListValue);
    }

    private final boolean getBrutForce() {
        return (Boolean)brutForce$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getFast() {
        return (Boolean)fast$delegate.getValue(this, delegatedProperties[2]);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final int getRadius() {
        return ((Number)radius$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final int getUp() {
        return ((Number)up$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final int getDown() {
        return ((Number)down$delegate.getValue(this, delegatedProperties[6])).intValue();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x5E47FCAE7DC6L;
        int n = (int)((l ^ 0x544448353C70L) << 48 >>> 48);
        GofraState.INSTANCE.setXray(true);
        if (this.getBrutForce() && !brutForce) {
            foundBlocks.clear();
            foundBlocks.addAll(this.scanBlocks((short)n));
            scanBox = this.getScanBox();
        }
        brutForce = this.getBrutForce();
    }

    @Flow
    private final void onXRayBlock(XRayBlockEvent xRayBlockEvent) {
        block0: {
            if (!this.getSelectedBlocks().getBlocks().contains(xRayBlockEvent.getBlock())) break block0;
            xRayBlockEvent.cancel();
        }
    }

    @Override
    public void onEnable(long l) {
        int n = (int)((l ^ 0x269EF17F8E92L) << 48 >>> 48);
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            return;
        }
        foundBlocks.clear();
        foundBlocks.addAll(this.scanBlocks((short)n));
        GofraState.INSTANCE.setXray(true);
        Globals.getMc().worldRenderer.reload();
    }

    @Override
    public void onDisable(long l) {
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            return;
        }
        Globals.getMc().worldRenderer.reload();
        GofraState.INSTANCE.setXray(false);
    }

    @Flow
    private final void onMove(MoveEvent moveEvent) {
        block0: {
            if (!this.getBrutForce() || !(!((Collection)foundBlocks).isEmpty())) break block0;
            moveEvent.setZ(0.0);
            moveEvent.setX(0.0);
            moveEvent.cancel();
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        if (!this.getBrutForce()) {
            return;
        }
        if (blockPos != null && !((Collection)foundBlocks).isEmpty()) {
            BlockPos blockPos = XRay.blockPos;
            Intrinsics.checkNotNull(blockPos);
            Object var28_2 = null;
            Color color = null;
            Color color2 = Theme.INSTANCE.getGradientColor(0);
            Box box = new Box(blockPos);
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color2, color, var28_2);
        }
        Object var39_6 = null;
        Color color = null;
        Color color3 = Theme.INSTANCE.getGradientColor(0);
        Box box = scanBox;
        RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color3, color, var39_6);
        if (foundBlocks.isEmpty()) {
            return;
        }
        ItemStack itemStack = Globals.getPlayer().getMainHandStack();
        Intrinsics.checkNotNullExpressionValue(itemStack, "getMainHandItem(...)");
        if (ItemUtil.isPickaxe(itemStack)) {
            InventoryUtil.INSTANCE.selectSlot(Globals.getPlayer().getInventory().getSelectedSlot() % 8 + 1);
        }
        if (timer.hasPassed2(this.getDelay())) {
            BlockPos blockPos;
            BlockPos blockPos2;
            Object e = foundBlocks.remove(foundBlocks.size() - 1 <= 1 ? 0 : RandomUtil.P(0, foundBlocks.size() - 1, false, null));
            Intrinsics.checkNotNullExpressionValue(e, "removeAt(...)");
            BlockPos blockPos3 = blockPos2 = (blockPos = (BlockPos)e);
            ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
            XRay.blockPos = blockPos3;
            clientPlayerInteractionManager.attackBlock(blockPos2, Globals.getPlayer().getHorizontalFacing());
            Globals.getInteractionManager().cancelBlockBreaking();
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        }
    }

    @Flow
    private final void onLightMapUpdate(LightMapUpdateEvent lightMapUpdateEvent) {
        lightMapUpdateEvent.cancel();
    }

    private final Box getScanBox() {
        return new Box(Globals.getPlayer().getX() - (double)this.getRadius(), Globals.getPlayer().getY() - (double)this.getDown(), Globals.getPlayer().getZ() - (double)this.getRadius(), Globals.getPlayer().getX() + (double)this.getRadius(), Globals.getPlayer().getY() + (double)this.getUp(), Globals.getPlayer().getZ() + (double)this.getRadius());
    }

    /*
     * Unable to fully structure code
     */
    private final ArrayList scanBlocks(short var1_1) {
        block9: {
            var34_2 = 0L;
            var2_3 = (var34_2 << 16 | (long)var1_1 << 48 >>> 48) ^ XRay.a;
            v0 = ZkmReferences.zkmBootstrap("\u00c5", (long)4644249592300626131L, (long)var2_3);
            var30_4 = Globals.getPlayer();
            v1 = BlockPos.ofFloored(this.getPos(var30_4));
            Intrinsics.checkNotNullExpressionValue(v1, (String)XRay.zkmBootstrap("b", (int)993, (long)(4927858526880183908L ^ var2_3)));
            var21_5 = v1;
            var20_6 = v0;
            var22_7 = new ArrayList<BlockPos>();
            var23_8 = var21_5.getX() - this.getRadius();
            var24_9 = var21_5.getX() + this.getRadius();
            v2 = var23_8;
            v3 = var24_9;
            if (var20_6 != null) ** GOTO lbl19
            if (v2 > v3) break block9;
            block0: while (true) {
                v2 = var21_5.getY();
                v3 = this.getDown();
lbl19:
                // 2 sources

                var25_10 = v2 - v3;
                var26_11 = var21_5.getY() + this.getUp();
                do {
                    block8: {
                        block10: {
                            block11: {
                                v4 = var25_10;
                                if (var1_1 >= 0) break block10;
                                if (v4 > var26_11) break block11;
                                block2: while (true) {
                                    block12: {
                                        var27_12 = var21_5.getZ() - this.getRadius();
                                        var28_13 = var21_5.getZ() + this.getRadius();
                                        v4 = var27_12;
                                        v5 = var28_13;
                                        if (var20_6 != null) break block8;
                                        if (v4 > v5) break block12;
                                        block3: while (true) {
                                            block13: {
                                                block14: {
                                                    var29_14 = new BlockPos(var23_8, var25_10, var27_12);
                                                    v4 = (int)Globals.getWorld().isAir(var29_14);
                                                    if (var20_6 != null) ** GOTO lbl63
                                                    if (v4 != 0) break block13;
                                                    v4 = (int)this.getFast();
                                                    if (var20_6 != null) ** GOTO lbl54
                                                    if (var20_6 != null || var1_1 >= 0) break block13;
                                                    if (v4 == 0) break block14;
                                                    v4 = var23_8 % 2;
                                                    if (var20_6 != null) ** GOTO lbl54
                                                    if (v4 == 0) break block13;
                                                    v4 = var25_10 % 2;
                                                    if (var1_1 > 0 || var20_6 != null) ** GOTO lbl54
                                                    if (v4 == 0) break block13;
                                                    v6 = var27_12 % 2;
                                                    if (var20_6 != null || v6 == 0) break block13;
                                                }
                                                v6 = var22_7.add(var29_14);
                                            }
                                            do {
                                                v4 = var27_12;
lbl54:
                                                // 4 sources

                                                v5 = var28_13;
                                                if (var20_6 != null) ** GOTO lbl64
                                                if (v4 == v5) break block3;
                                                ++var27_12;
                                                if (var20_6 == null) continue block3;
                                            } while (var1_1 > 0);
                                            break;
                                        }
                                    }
                                    do {
                                        v4 = var25_10;
lbl63:
                                        // 2 sources

                                        v5 = var26_11;
lbl64:
                                        // 2 sources

                                        if (var20_6 != null) break block8;
                                        if (v4 == v5) break block2;
                                        ++var25_10;
                                        if (var20_6 == null) continue block2;
                                    } while (var1_1 > 0);
                                    break;
                                }
                            }
                            v4 = var23_8;
                        }
                        v5 = var24_9;
                    }
                    if (v4 == v5) break block9;
                    ++var23_8;
                    if (var20_6 == null) continue block0;
                } while (var1_1 >= 0);
                break;
            }
        }
        return var22_7;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getBrutForce();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getBrutForce();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getBrutForce();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getBrutForce();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getBrutForce();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        XRay.a = ZkmKeys.a(8638636948636311576L, -2532462535733190466L, MethodHandles.lookup().lookupClass()).a(34211223239199L);
                        var20 = XRay.a ^ 32887122775773L;
                        XRay.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[27];
                        var16_4 = 0;
                        var15_5 = "s\u00b9\u00bd\u00ae\u0086L\u00aa\u0085\u00914'\u00a2\u00f6\u0001\u0012\u0087\u00ad\u00a7\b\u0086g\u0014\u00f5\u0080\u0018-\u00f64\u00fbI\u0088\u00a4\u00bf\u00d8\u00d3\u008f\u0014o\u00eb\u00eatF\u00eb\u0014\u001e\u00efZ\u0081\u0082\u0010LU\u00b2X\u0089`g%Bd\u00df\u00fd\u00db*\u00fb'\u0018\u00e6\u00a8\u00de\u00b5\u00b7\u0085\u00b2\u001c\u00a3$\u00da?0\u001e\u009e\u00e2\u00a6\u00dc+Y\u009f\u00c2r\u00ac\u0010\u00be\u0005?1\u00be3!t?\u009a0\u0002\u00e0o\u00c6|\u0010\u00e2\u00ff\u0093\u009a\u00c6\u0080F\u001ef\u00c6\u0010?\u0007\u00c0A\u009b\u0018:\u000eP0\u0094\u00e6\u00ea\u001f]O$u\u000f\u0090\u00f7\u00e5\u0090\u0087\u00d6\u00d9)i\u00f9%\u0018\u00bc\u00aa\u000e\u00c6\u008bx\nH[\u00ceF\u00b6\u00f6\u00c4\u00d50\u0086\u00cc\u00aah\u000f\u00cf\u0007\u00a6\u0010\u00f6%\u00cdJ\u0019\u00be|\u00fb\f\u00ee\u0090K\u001b\u00a8\n\u00e3\u0018AZ\u00a0d\u00c1\u00b6\u0081\u00dd\u00f0:\u0098\u00cc\u0004\u00c7\u008d\u00a4\u00b5a\u008f\u00b3\u00fb\u00a8\u00bb\u00f3(\u0004\u009e\u00005+\u0015\u00e4\u0088\u00d1\u00c0\u00c0<\u00a4\u00e6s\u0013\u00a4mQ4\u00cc\u0089M\f\u008b\n{\u00d5#Sxy\u00c9\u00c6\u00fe\u00ed\u008f\u00b7}\u0099\u0010\u009b\u00f3\u00a4\u00ec\u0088\u001dA]\u00ab\u00b6\u00bb\u00d2\u0007\u00fc\u00d6& N9\u00ccJ\u0094]\u0094\u00ae^)\u009b\u00cd\u001f+\u00ef'\u00d4\u0012\u00a7\u009e\u00c5\u00b3@z\u00f7\u000f~\r\u0017\u00fd\u00f5i\u0010\\\u0082&\u0002W\u00f0\u00e9\t\u0097\u0080Q\u00e3,\u0088\u00bbl\u0018R\u00ef\u009d\u001e[[\u008b\u0017\u00ba\u00e0-\u0083\u0001\u00b20\u00cd\u00baq\u00c8\u0011\u001b\f\u00aaG \u00e0\u00c3\u00d2|\u0007\u0088\u0001\u00f1\u00b9T(\u00cb\u0083\u00aa\u00c7\u00a6\u000b\u00b1\u0083\u0093\u0092\u0016FPmD\u00a9x\u00e2%R\u00a5\u0018\u00cc\u00b6\u00eb\n\u00e1\u00fc\u0012\u00aaWc\u009en\u0080\u0010|\u00f5\u0010\u008f\f\u000f\u0096\u00a4\u00ec\u00ce\u0010\u00a6\u0085e\u00a0\u00a0\u00b6W<@/\rR\u0091\u00ac\u00ae\u00c5\u0010\u00c0H3K\u00ee\u008bl\u0080\u0087t\u00cf{S\u009d\u00f8}\u0010%w\u00f2\u00c4+\u0014%\u00c9K\u00b4\u00e5r\u00f9\u0016l@\u0018\u00ad\u0093Ung\u0084\u0084'U\u00d1(m\u00da8\u00b6\u009b\u00bd\u00dd\u00e0\u00ee/\u00ebi.\u0018\u0089\u00d6\u00fdEa\u009c}q4\u0094\u00cc\u000bn\\\u0007\u00e8y\u0082\u00f0\u00c87y\u00a4^\u0010\u0098l?\u00a2\u001eH\u00dc\u00b3\u00a6\u00ec&\u00c0\u00d1o\u0085\u00b9\u0010]\u00a2\u0016=\u001dl\u0007\u00aag\u009a\u0012)\u00c8\u00d38\u001aH\t\u008e\u0004X\u0012\u00d4\u00ac9\u00ac\u00f0\u00b7{l\u00a0\u00bc\u00ab`;S-\u00c0\u00a4\u00e0(C\u0097t\u00e3\u00d86Sh\u0080\u00f1hg3\u008c)\u00d0\u00d4F:JG\u00f2\u00b2\u0002x\u001e_\u00da\u00efO\u0096\u0016\u00f5\u009e\u00e7\u00ce\u00f6'\u00f9T\u00cek\u001eX;\u00calq";
                        var17_6 = 616;
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
                            var18_3[var16_4++] = XRay.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0006\u00ec\u000bm\u0098\u008f\u00ddz\u00ddO\u0094\u00b1'1\u00f9q^e\u0000zTB\u001e\u0010U'g\u00d0.{\u00b9\u00af\u0005\u001d\u00ab;\u00f2\u00fbgM\u00daC\u001b\u00a0\u00a0\n\u0006\u00e2 s\u0007O\u008e\u0003L\u00fb\u0000\u00c7\u00ee\u00ad\u00d5\u008aV\u00b8J\u00e9\u00fc\u0098\u00a2\u00a0 \u00b5\u00d4\u0017wq_\u0012\u0092\u001c\u00c4";
                            var17_6 = 81;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = XRay.zkmDecodeUtf8(var19_9).intern();
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
                XRay.c = var18_3;
                XRay.d = new String[27];
                XRay.i = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "Q\u00a5\u00b8R\u00d4\u0084\u0083#<V=\u0010\u001f8\u009d\u0087\u00c6v>f\u00f7\u00a3\u00f2\u008ap`\u00a2\u0087\u009f\u00fd`\u00e736f\u00b9]\u00b8S\u00e2\u0000\u0007\u0081\u00f5\u00dd\u00ec$\u00a4\u00a5\u0098\u00f1c\u00eb\u00a1\u0007N\u00d0S/\u00f9\u00cd\u001b<\u00d8W1\u00d3@\t\u000eW\u009c\u008f%6\u00e7\u00b1L\u00f0e";
                var5_15 = 80;
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
                    var4_14 = "\b_\u0085D\u00ca\u00cb\u009c\u00e9\u00e3\u00e2\u0082\u00be&`a@";
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
        XRay.f = var6_12;
        XRay.h = new Integer[12];
        var37_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(XRay.class, "selectedBlocks", "getSelectedBlocks()Lsu/catlean/dg;", 0)), Reflection.property1(new PropertyReference1Impl(XRay.class, "brutForce", "getBrutForce()Z", 0)), Reflection.property1(new PropertyReference1Impl(XRay.class, "fast", "getFast()Z", 0)), Reflection.property1(new PropertyReference1Impl(XRay.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(XRay.class, "radius", "getRadius()I", 0)), Reflection.property1(new PropertyReference1Impl(XRay.class, "up", "getUp()I", 0)), Reflection.property1(new PropertyReference1Impl(XRay.class, "down", "getDown()I", 0))};
        XRay.delegatedProperties = var37_20;
        XRay.INSTANCE = new XRay();
        v15 = XRay.INSTANCE;
        var37_20 = new Block[3];
        Intrinsics.checkNotNullExpressionValue(Blocks.DIAMOND_ORE, "DIAMOND_ORE");
        Intrinsics.checkNotNullExpressionValue(Blocks.DEEPSLATE_DIAMOND_ORE, "DEEPSLATE_DIAMOND_ORE");
        Intrinsics.checkNotNullExpressionValue(Blocks.ANCIENT_DEBRIS, "ANCIENT_DEBRIS");
        var32_21 = null;
        var34_22 = null;
        var35_23 = null;
        var36_24 = new BlockListValue(CollectionsKt.mutableListOf(var37_20));
        XRay.selectedBlocks$delegate = SettingFactory.blockListSetting(v15, "blocks", var36_24, var35_23, var34_22, var32_21);
        XRay.brutForce$delegate = SettingFactory.booleanSetting$default((SettingContainer)XRay.INSTANCE, "deobfuscator", false, null, null, 12, null);
        XRay.fast$delegate = SettingFactory.booleanSetting$default((SettingContainer)XRay.INSTANCE, "fast", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        XRay.delay$delegate = SettingFactory.intSetting$default(XRay.INSTANCE, "delay", 25, new IntRange(1, 100), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        XRay.radius$delegate = SettingFactory.intSetting$default(XRay.INSTANCE, "radius", 5, new IntRange(1, 64), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        XRay.up$delegate = SettingFactory.intSetting$default(XRay.INSTANCE, "radius", 5, new IntRange(1, 32), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        XRay.down$delegate = SettingFactory.intSetting$default(XRay.INSTANCE, "down", 5, new IntRange(1, 32), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        XRay.timer = new Timer();
        XRay.foundBlocks = new ArrayList<E>();
        XRay.scanBox = new Box(BlockPos.ORIGIN);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x743D;
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
                throw new RuntimeException("su/catlean/fq", exception);
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
            XRay.d[n2] = XRay.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = XRay.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x31BD;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/fq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            XRay.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = XRay.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(XRay.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(XRay.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

