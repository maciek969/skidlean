/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.equip.ChestStealerActionMode;
import su.catlean.module.equip.ChestStealerSortMode;
import su.catlean.module.equip.ChestStealerSortMode$WhenMappings;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ItemListSetting;
import su.catlean.setting.ItemListValue;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.RandomUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class ChestStealer
extends Module {
    @NotNull
    public static final ChestStealer INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting action$delegate;
    @NotNull
    private static final ModeSetting sort$delegate;
    @NotNull
    private static final ItemListSetting items$delegate;
    @NotNull
    private static final IntSetting minDelayMs$delegate;
    @NotNull
    private static final IntSetting maxDelayMs$delegate;
    @NotNull
    private static final IntSetting minDelayTicks$delegate;
    @NotNull
    private static final IntSetting maxDelayTicks$delegate;
    @NotNull
    private static final BooleanSetting randomizeSlots$delegate;
    @NotNull
    private static final BooleanSetting close$delegate;
    @NotNull
    private static final BooleanSetting autoOpen$delegate;
    @NotNull
    private static final BooleanSetting rotate$delegate;
    @NotNull
    private static final Timer openTimer;
    @NotNull
    private static final Timer takeTimer;
    @NotNull
    private static final Timer closeTimer;
    private static int B;
    private static int k;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map i;
    private static final long[] j;
    private static final Integer[] l;
    private static final Map m;

    private ChestStealer() {
        super("chest-stealer", Categories.getEquip(), null, null);
    }

    private final ChestStealerActionMode getAction() {
        return (ChestStealerActionMode)((Object)action$delegate.getValue(this, delegatedProperties[0]));
    }

    private final ChestStealerSortMode getSort() {
        return (ChestStealerSortMode)((Object)sort$delegate.getValue(this, delegatedProperties[1]));
    }

    private final ItemListValue getItems() {
        return (ItemListValue)items$delegate.getValue(this, delegatedProperties[2]);
    }

    private final int getMinDelayMs() {
        return ((Number)minDelayMs$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final int getMaxDelayMs() {
        return ((Number)maxDelayMs$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final int getMinDelayTicks() {
        return ((Number)minDelayTicks$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final int getMaxDelayTicks() {
        return ((Number)maxDelayTicks$delegate.getValue(this, delegatedProperties[6])).intValue();
    }

    private final boolean getRandomizeSlots() {
        return (Boolean)randomizeSlots$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getClose() {
        return (Boolean)close$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getAutoOpen() {
        return (Boolean)autoOpen$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getRotate() {
        return (Boolean)rotate$delegate.getValue(this, delegatedProperties[10]);
    }

    @Flow
    private final void onPostTasksProcess(PostTasksProcessEvent postTasksProcessEvent) {
        long l = a ^ 0x350203A2D9A1L;
        long l2 = l ^ 0x66FB7DEFEF9CL;
        if (this.getAction() == ChestStealerActionMode.FAST) {
            this.stealFromOpenChest(l2, false);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x21B3B8D26B14L;
        long l2 = l ^ 0x724AC69F5D29L;
        if (this.getAutoOpen() && Globals.getMc().currentScreen == null && openTimer.hasPassed(3000)) {
            for (BlockEntity blockEntity : ServerInfo.INSTANCE.V()) {
                if (!(blockEntity instanceof EnderChestBlockEntity) || Globals.getPlayer().squaredDistanceTo(((EnderChestBlockEntity)blockEntity).getPos().toCenterPos()) > 39.0) continue;
                double d = RenderUtil.INSTANCE.lerp(-0.4, 0.4, Random.Default.nextFloat());
                Vec3d vec3d = ((EnderChestBlockEntity)blockEntity).getPos().toCenterPos().add(d, 0.375, -d);
                Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
                Vec3d vec3d2 = vec3d;
                if (this.getRotate()) {
                    Object var32_8 = null;
                    Rotation rotation = Rotation.Companion.to(vec3d2);
                    RotationManager rotationManager = RotationManager.INSTANCE;
                    RotationManager.rotate$default(rotationManager, rotation, var32_8);
                }
                Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, new BlockHitResult(vec3d2, Direction.UP, ((EnderChestBlockEntity)blockEntity).getPos(), false));
                Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                break;
            }
        }
        if (this.getAction() == ChestStealerActionMode.ON_TICK) {
            this.stealFromOpenChest(l2, true);
        }
    }

    private final void stealFromOpenChest(long l, boolean bl) {
        long l2 = (l = a ^ l) ^ 0x64EB21506FAEL;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        GuiMove.INSTANCE.setMacroActive(true);
        if (Globals.getMc().player != null && Globals.getMc().world != null && Globals.getPlayer().currentScreenHandler instanceof GenericContainerScreenHandler) {
            ScreenHandler screenHandler = Globals.getPlayer().currentScreenHandler;
            Intrinsics.checkNotNull(screenHandler, "null cannot be cast to non-null type net.minecraft.world.inventory.ChestMenu");
            GenericContainerScreenHandler genericContainerScreenHandler = (GenericContainerScreenHandler)screenHandler;
            IntRange intRange = RangesKt.until(0, genericContainerScreenHandler.getInventory().size());
            Iterator iterator2 = (this.getRandomizeSlots() ? (Iterable)CollectionsKt.shuffled(intRange) : (Iterable)intRange).iterator();
            while (iterator2.hasNext()) {
                String string;
                Slot slot;
                int n2 = ((Number)iterator2.next()).intValue();
                Intrinsics.checkNotNullExpressionValue(genericContainerScreenHandler.getSlot(n2), "getSlot(...)");
                Screen screen = Globals.getMc().currentScreen;
                Intrinsics.checkNotNull(screen);
                Intrinsics.checkNotNullExpressionValue(screen.getTitle().getString(), "getString(...)");
                if (!slot.hasStack()) continue;
                ItemStack itemStack = slot.getStack();
                Intrinsics.checkNotNullExpressionValue(itemStack, "getItem(...)");
                if (!this.shouldTake(itemStack) || !takeTimer.hasPassed2(bl ? B : k) || StringsKt.contains$default((CharSequence)string, "\u0410\u0443\u043a\u0446\u0438\u043e\u043d", false, 2, null) || StringsKt.contains$default((CharSequence)string, "\u043f\u043e\u043a\u0443\u043f\u043a\u0438", false, 2, null) || StringsKt.contains$default((CharSequence)string, "\u0412\u042b\u0411\u041e\u0420", false, 2, null)) continue;
                InteractionUtil.clickSlot$default(n2, 0, SlotActionType.QUICK_MOVE, false, 8, null);
                openTimer.reset();
                this.randomizeDelays();
            }
            if (this.stealItems(genericContainerScreenHandler) && this.getClose() && closeTimer.hasPassed(ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null) + 100)) {
                Globals.getPlayer().closeHandledScreen();
            }
        } else {
            closeTimer.reset();
        }
        GuiMove.INSTANCE.setMacroActive(false);
    }

    private final void randomizeDelays() {
        k = RandomUtil.f(new IntRange(this.getMinDelayMs(), this.getMaxDelayMs()), false, null);
        B = RandomUtil.f(new IntRange(this.getMinDelayTicks(), this.getMaxDelayTicks()), false, null);
    }

    private final boolean shouldTake(ItemStack itemStack) {
        return switch (ChestStealerSortMode$WhenMappings.L[this.getSort().ordinal()]) {
            case 1 -> true;
            case 2 -> this.getItems().getItems().contains(itemStack.getItem());
            default -> !this.getItems().getItems().contains(itemStack.getItem());
        };
    }

    private final boolean stealItems(GenericContainerScreenHandler genericContainerScreenHandler) {
        int n;
        int n2 = n = genericContainerScreenHandler.getInventory().size() == 90 ? 54 : 27;
        for (int i = 0; i < n; ++i) {
            if (!genericContainerScreenHandler.getSlot(i).hasStack()) continue;
            return false;
        }
        return true;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getSort() != ChestStealerSortMode.NONE;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getAction() == ChestStealerActionMode.FAST;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getAction() == ChestStealerActionMode.FAST;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getAction() == ChestStealerActionMode.ON_TICK;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getAction() == ChestStealerActionMode.ON_TICK;
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getAutoOpen();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ChestStealer.a = ZkmKeys.a(2071335294806830349L, 3805267430008194486L, MethodHandles.lookup().lookupClass()).a(252413335889516L);
                        var20 = ChestStealer.a ^ 120356432177155L;
                        ChestStealer.i = new HashMap<K, V>(13);
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
                        var18_3 = new String[42];
                        var16_4 = 0;
                        var15_5 = "\u00e5\u0081)\u00f6\\\u00ba&\u001b\u00c0\t\u00fd\u0091ho]X\u00b4\u008a=\u009d\u00aa\u00dc.[<e\u0012\u008a\u0080\u00c4JH8B\u00e1 \\a\n\u00b9\u0001\u001b\u00a3Myw\u00d8\u009663qQQ*i\u0016\u00cb\b\u009a{\u00f1\u00b9^S\u00dcnC\u00e6\u0084\u00a1\u00d8o\u0082\u00f0\u00ba\u00c8G{\u00a4\u00b9\u0003\u001fY\u00ae\u00e6\u000bb\u00ce\u00d3\u0010\u00eb\u0013\u00dbp\u00d2S\u00d1k\u00c4}^,\u00c1<\u001a\u00ad\u0018\u001b\b6Z\u00cb\u0097\u00b5\u00de\u00a4\u008a\u00bb\u0090B\n=\u00ad\u00c5\u00a6E\u00d5\u00f6t\u0087\u00c5\u0010x\u008e\u00b1\u0088Lm<#\u00a5\u00f1-N]UW\u001e00 \u009f\\\u00d4/\u00f7\u00123\u00bb\u00a9sK-\u0084\u0013\u008b\u00cf\u0083~,\u00f2\u008f\u00f4#\u0086\u00dcg\u00bdmIP7\u007f\u00c9m\u0016,.w$\u0092p\u0007\u00e1\u007f\u0093\u00e90\u00fe\u00ca\u0096\u00a8bJ\u00a8\u0010\u000b2\u009e\u00a6\u00aa\u00a5\u0014\u00a2\n\u00eb:\u0007\u0019RP\u00c4\f\u0011\u00b6jj+\u00d3S\u0087L\u00d7]\u00afH\u009f0u\u00eaqX\u0010g\u00c4\u00dc I\u000e\u00e2\u00d1\"\u00b1y\u00b4\u00d3\u0000\u0005\u00f4\u001ax\u00d0\u00b8!\u00e4SA\u008f\u00d0\u009b\u000f5\u00a4\u00ba?\u00fb\u00bb/\u008a\u0018J4\u00d4\u0088\rf\u001e\fP\u00fcv\u00bc\u00073\u009b\u008f\u00e1\u0001\u0019\u00f7m\u00a4\u00cb\u009e \u00fd\u00d0\u00eck\u007f\u0002\u007fac\u00b2\u0004\u00b3~b\u00db[\u009aMI\u0085!\u00ea$\u00e5\u008a\f\\\u001f/\u0003\u0002<\u0010\u00d7\u00ea\u000bx\u008c\u0084q\u00c3\u00de\u0087\u001eh\u00a5\u00e5\u000f\u00cc \u00bf&Z\u00f1\u0099\u00c9\u00ba\u00ac#\u00c3h2P\u0007\u00d5O\u0097\u00eeE\u00be\u009d\u0007g\u0089{d\u00a4t\u008c\u00e5\u00e1\u00e5 Qt\u00f0\u0098!\u001a\u00e5A@Y\u00cd7\u00f8\u00c8\u0093\u00d5~\u00c4\u00adps\u00cc\u00a1\u0096\u008b5\u00e4\u00a6*\u000bOs \u00b9\u00a7\u00f3W8\u00f2\u00bfZ\b\u00ba\u0088\u00dc7Q\u00be\u00b9\u00bc\u00ea\u00dc\u00b3/\u00a1O\u00ce\u009c3\u000f\u00e2\u0014?\u000bixp\u00ecj\u00e9\u0080H\u00f5.\u00fc\u00ea6C\tM\u00f9\u00d7C\b\u00fc\u0006\u0013\u0081\u000e0\u009b\u00ed`2\u00c7Z\u00c4\u00b3\u0016\u00876\u00f6=)\u00d5\u00d0\u009d\u008d\u00b1\u00a0\u0003\u00f8\u009b\u00df\u0016\u00aeT\u008a\u008f\u00cc\u0002q\u0017\u009b\u001a\u00ff\u008b(\u00e9\u00bb)T;\u0082\u00ebG{\u0099\u00d93\u00d7dinO\u0013[\u00fd \u0084\u0084R\u001e\u00a4\u000f\u0010\u0001,\u00d0q4\u00e7U#\u00e6\u008c\fT>\u0006e$\u0093W\u001c<$K\u00d8l@s/T2T(B\u0093,\u00aa\u00f6Q\u00c85\u00d6\u00fc\u00fa\u00d7|sb\u00bf\u008e\u00c0\u00c4GC.\u00b9\u00a7\u00c5\u00e4!\u00e2\u0082\u00b8\u0080\u00a1){\u00d3\u0006>\u0007\u00a8\u00b8 iV\u00c3\u00e9]\u00fe2\u00e9S\u009f\u008d\u00a1\u00a2R|\u00ab\u0007$\u00bb\u0091\u00bf\u0016J\u00f1\u008d\u008b*\u009aB\u00b4\u0088\u00bc PV\u00f1Elm\"?nnuxy\u0089\u00f8I\u00ce\u00c5.\u001f\u00c1\u00dc\u0090\u0097b}\u0084+1\u0015?a\u0010\u009b\u008f\u0006|#\u00b4\u00e2\n\u00f3\u00a3\u00ebK\u00deJ\u00a7\u00d5\u0010\u00ca\t\u0081v\u000e\u00cd\u0004\u00cc\u008a\u00d8\u0006\u0084%\u0088\u0015^\u0010\u00a3X0uKq\u00b7\u0010_\u00a3'\u0000\u00f6BMR\u0018\u008e\u0081+o\u0002\f!\u00ce\u00ef\u00f6\u0088\u0096\u00f6:\u00fc+\u00b0\u00be\u0092\u00b1\u0012\u00bc\u00a5X0Z\u00bdx\u00c1\u00b7\u00ff\u00bc\u00b7\r\u00f5\u007f\u00a1@U\u00ec\u0082\u00e5\u000f\u009dYm\ryt\f\u001e\u00c9\u0017\u0010\u00ab\u00d0\u008c\u001ac\u00c5\u00df\u00d5\u00ef\u0096k\u008cq\u0006\"\u00a7\u0090\u0011\u00ff \u00f5\u00d99\u008a\u00d4\u0007\u00b8\u00b3\u00d8\u0095\u00e0\u00a6#]]\u00c1\u008f\u0087E_\f\u00a4\u001c\f)\u00ec\u00ab\u0013\u0091dW\u0003 \u009f\u00f8r\u00cbC\u0001\u0007\u00cd\u0090\u0000[cc\u0013\u00b4\u001f7\u00b1,e\u00c8\u008b5\u00a8W\u00ce4O\u0010(\u0099\u0088\u0010\u00d1&\u001c\u0013/\u00e0I\u0017\u0002\u00f0\u00d1\u00d6\u00f2\u00a8\u00b4m\u0018%\u00f8q\u00fc\u0090)\u00acZ\u00f4\u0003\u0003\u00d3u\u0086\u00bfIf\u00f1\u0011\u0004\u001aV.'\u0010W\u0080\u00e0\u007f\u0015;\u0094\u001e\u00fc\u00f5\u00cdz\u0083w\u00c6\u0094\u0018\u0088|\u00f5\u00c4?\u008293\u00fb*\u0015\u00a8\u0093\u00ca\u008e\u00e4\u008d\u009e\f\u00d9\u00a4\u0019\u00daZ\u0010N\u00b2-g.\u00fdZ\u00eeu\u00f9P\u00c3\u00ee(i\u00ea !\u0012+\u0006iA\u0080\u00ee\u00fc\u00a8\u008b`/k(\u00f4=N!\u00fdhb4\u009d\u0015\u00cf\u00af\u00e5i\u0089\u00fa\u00cd0\u00a4\u00dc\u00f3z\u00d1/\u00f6i\u00105\u00c8\u00fb\u00a9\u00b0\u00acRvb_\u001b~\u0097\u00f8\u009aB(g\u00b1\u009adi$\u00bbG\u0083tY\u00af\u00d5\u00b5\u00d3\u00da\u00fdP&\u00e5\u0000%(\u00ff\u00ee\u00c2\u009e\u00eaj\u00a7\u00bb'\u0097\u00a5F\u00ce]\u0080&Zf\u00a3_\u00b5\u00f8\u00c6\u00d4\u00f9;_L\u00dae\u0005\u00e1L\t\u008a\u00dcD\u00ac#\u0084 \u00a5}8\u00c3\u00c4J\u00c7%\u00edY\u0090KqBw\u0097\u00db\u00c3t\u00e9mh]\u00d2\u00c0\u00c0+ZH\u00d8J\u00a0(+\u00fcS,S\u008e8\u0010%~\u001e\u00b9I\u00d4\u0013\u00f3\u00fb\u00c0\u0094\t\u0013\u00fd\u0088\u0080A\u0018\u008f\u00f9\u00b4\u00dd\u00121\u00115V\u00d6\u00a0\u0087\u00f0[ :t\u00a4of\u0094\u008b\u00dbf]:d\u0019\u00dc\u00ed\u008a\u00c6\u00840\u00e2\u000f\u00d7\u0000\u00c7j\u00b12\u0014\u0014o\u0082\u00a6\u0018&\u00bb\u0014\u00a3\u0019\u00e0\u0003U\u00bc\u00ecB\u0014\u0088=\u00bd\u00eew5K\u0014\u00b6 \u001c\u0081 \u0088ag\u0080\u00cf \u00e4\u00c79\u0084\u00b9h\u0015\u001f}zx\u00de\u00da\u0095p<\u00ca\u00bf\u001d\u00dc\u00896\u00dd\u00c2\u00bd\u009b\u0018\u00c9*\u00fb7\t%]\u00af\u0010;\u00c9w\u00f2\u00e5\u00e9\u00ab\u00e5\u00fcf\u00ad$\u0086\u00d8I\u0018\u00fe#\u00b7J.\u00c7\u0083\u00df\u00a8\u00d3\u00a0\u00a2\u00f2\u0014\u0001\u0011g\u00ddj\u00a4\u00fc\u00bed\u009a";
                        var17_6 = 1311;
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
                            var18_3[var16_4++] = ChestStealer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a2J,\u00e3N\u00b0_\u00b6\\\u0087D\u00df80\u00b9E\u00bc\u00baL\u00d9\u0083\u0010>W\u0093\u00c3\u0081Bof\u00b7% \u0000\u0099|\u00d8\u00c1,k\u00eb#\u00e6/Cz\u00fd\u009f\u00fcL\u0012\u0085\u00e3\u00d6R\u00cc}\u0015\u00ad\u00f0\u00fc\u009a\u00e0\u00ba\u00fc";
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
                            var18_3[var16_4++] = ChestStealer.zkmDecodeUtf8(var19_9).intern();
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
                ChestStealer.c = var18_3;
                ChestStealer.d = new String[42];
                ChestStealer.m = new HashMap<K, V>(13);
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
                var6_12 = new long[24];
                var3_13 = 0;
                var4_14 = "\u009a\u00d8\u00e2\u00a4z\u00b0\u008a\u00f1HCu\u00caPO\u00b1d6\u0012\u00e53\u00f6(G\u00ce\u001f\n\u00cdz[`\u00f2?\u008e\u00caP\\t\u009d\u00d3\u001c\u00d4\u00bf,\u00b1,F\u001e\u00fe\u00b1\u009c\u0088\u00f9c\u0003XU/>\u00b8Op=\u00ad\u00fb\\\u00cdc\u000f \u00a7M\u00af@\u00e8e\u00d9\u0004\u00a3S$&\u00ca\u00e8I\u001f?\u00e8Q\u00b9\u0091\u0003\u00f7e\u008cF\u00a1\u009eg\u00ec\u00fc\u001bD\u00e9\u00d7\u001c\u00d3\u0094\u0000\u00a8\u0004*LhiR*s/\u00cc\u008886Q\u009a\u00da\u00af\u00b1\u00c1\u00e1X++\u00f9u\u0081\u00f9Z3\u00e4e\u00a6\u00a1\u0083U\u00be\u00b0\u00d6\u0098\u007f\u0013,\u008a\u0018\u008e\u00fa\u00a0@,S\u00ed\u00ca\u00faQ\u008c_\u00bdC\u009c\u00b5c\u0092wZ\u00ecr\u00ea";
                var5_15 = 176;
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
                    var4_14 = "\u00d1\u00910\u00a8\u0096&-\u00c1\u00a5\u0093\fZ3\u00ca\u00ca\u0007";
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
        ChestStealer.j = var6_12;
        ChestStealer.l = new Integer[24];
        var34_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "action", "getAction()Lsu/catlean/v;", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "sort", "getSort()Lsu/catlean/ta;", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "items", "getItems()Lsu/catlean/nv;", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "minDelayMs", "getMinDelayMs()I", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "maxDelayMs", "getMaxDelayMs()I", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "minDelayTicks", "getMinDelayTicks()I", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "maxDelayTicks", "getMaxDelayTicks()I", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "randomizeSlots", "getRandomizeSlots()Z", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "close", "getClose()Z", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "autoOpen", "getAutoOpen()Z", 0)), Reflection.property1(new PropertyReference1Impl(ChestStealer.class, "rotate", "getRotate()Z", 0))};
        ChestStealer.delegatedProperties = var34_20;
        ChestStealer.INSTANCE = new ChestStealer();
        ChestStealer.action$delegate = SettingFactory.modeSetting$default((SettingContainer)ChestStealer.INSTANCE, "action", (Enum)ChestStealerActionMode.FAST, null, null, 12, null);
        ChestStealer.sort$delegate = SettingFactory.modeSetting$default((SettingContainer)ChestStealer.INSTANCE, "sort", (Enum)ChestStealerSortMode.NONE, null, null, 12, null);
        var35_21 = null;
        var36_22 = 4;
        var37_23 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)();
        var38_24 = null;
        var39_25 = new ItemListValue(null, null);
        var40_26 = "items";
        var41_27 = ChestStealer.INSTANCE;
        ChestStealer.items$delegate = SettingFactory.itemListSetting$default(var41_27, var40_26, var39_25, var38_24, var37_23, var36_22, var35_21);
        ChestStealer.minDelayMs$delegate = SettingFactory.intSetting$default(ChestStealer.INSTANCE, "min-delay-ms", 80, new IntRange(0, 500), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        ChestStealer.maxDelayMs$delegate = SettingFactory.intSetting$default(ChestStealer.INSTANCE, "max-delay-ms", 80, new IntRange(0, 500), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        ChestStealer.minDelayTicks$delegate = SettingFactory.intSetting$default(ChestStealer.INSTANCE, "min-delay-ticks", 0, new IntRange(0, 30), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        ChestStealer.maxDelayTicks$delegate = SettingFactory.intSetting$default(ChestStealer.INSTANCE, "max-delay-ticks", 1, new IntRange(0, 30), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        ChestStealer.randomizeSlots$delegate = SettingFactory.booleanSetting$default((SettingContainer)ChestStealer.INSTANCE, "randomize-slots", false, null, null, 12, null);
        ChestStealer.close$delegate = SettingFactory.booleanSetting$default((SettingContainer)ChestStealer.INSTANCE, "close-if-empty", false, null, null, 12, null);
        ChestStealer.autoOpen$delegate = SettingFactory.booleanSetting$default((SettingContainer)ChestStealer.INSTANCE, "auto-open", false, null, null, 12, null);
        ChestStealer.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)ChestStealer.INSTANCE, "rotate", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        ChestStealer.openTimer = new Timer();
        ChestStealer.takeTimer = new Timer();
        ChestStealer.closeTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2515;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qg", exception);
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
            ChestStealer.d[n2] = ChestStealer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ChestStealer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D63;
        if (ChestStealer.l[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])m.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    m.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ChestStealer.l[n2] = n3;
        }
        return ChestStealer.l[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ChestStealer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ChestStealer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ChestStealer.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

