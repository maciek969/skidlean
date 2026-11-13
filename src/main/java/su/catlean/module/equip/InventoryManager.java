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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.config.ConfigStore;
import su.catlean.config.ItemPreset;
import su.catlean.gofra.Flow;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.ItemPresetEditor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.equip.InventoryManagerCleanerMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ButtonSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ItemListSetting;
import su.catlean.setting.ItemListValue;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.ActionHolder;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class InventoryManager
extends Module {
    @NotNull
    public static final InventoryManager INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ButtonSetting openManager$delegate;
    @NotNull
    private static final ItemListSetting cleanerList$delegate;
    @NotNull
    private static final IntSetting actionDelay$delegate;
    @NotNull
    private static final IntSetting clicksPerAction$delegate;
    @NotNull
    private static final ModeSetting cleaner$delegate;
    @NotNull
    private static final IntSetting cleanerDelay$delegate;
    @NotNull
    private static final BooleanSetting cleanChests$delegate;
    @NotNull
    private static final Timer timer;
    private static boolean t;
    private static int A;
    private static final long a;
    private static final String[] d;
    private static final String[] f;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private InventoryManager() {
        super("inventory-manager", Categories.getEquip(), null, null);
    }

    private final ActionHolder getOpenManager() {
        return (ActionHolder)openManager$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setOpenManager(ActionHolder actionHolder) {
        openManager$delegate.setValue(this, delegatedProperties[0], actionHolder);
    }

    private static void w() {
    }

    private final ItemListValue getCleanerList() {
        return (ItemListValue)cleanerList$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setCleanerList(ItemListValue itemListValue) {
        cleanerList$delegate.setValue(this, delegatedProperties[1], itemListValue);
    }

    private final int getActionDelay() {
        return ((Number)actionDelay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final void setActionDelay(int n) {
        actionDelay$delegate.setValue(this, delegatedProperties[2], n);
    }

    private final int getClicksPerAction() {
        return ((Number)clicksPerAction$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final void setClicksPerAction(int n) {
        clicksPerAction$delegate.setValue(this, delegatedProperties[3], n);
    }

    private final InventoryManagerCleanerMode getCleaner() {
        return (InventoryManagerCleanerMode)((Object)cleaner$delegate.getValue(this, delegatedProperties[4]));
    }

    private final int getCleanerDelay() {
        return ((Number)cleanerDelay$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final boolean getCleanChests() {
        return (Boolean)cleanChests$delegate.getValue(this, delegatedProperties[6]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (A > 0) {
            int n = A;
            A = n + -1;
            return;
        }
        if (ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().isEmpty()) {
            return;
        }
        int n = 0;
        ScreenHandler screenHandler = Globals.getPlayer().currentScreenHandler;
        Intrinsics.checkNotNullExpressionValue(screenHandler, "containerMenu");
        Iterator iterator2 = this.findJunkSlots(screenHandler).iterator();
        Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
        Iterator iterator3 = iterator2;
        while (iterator3.hasNext()) {
            Object e = iterator3.next();
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            int n2 = ((Number)e).intValue();
            InteractionUtil.clickSlot$default(n2, 0, null, false, 14, null);
            if (++n < this.getClicksPerAction()) continue;
        }
        A = this.getActionDelay();
    }

    private final int moveItemToRange(Item item, ScreenHandler screenHandler, IntRange intRange) {
        int n;
        if (Intrinsics.areEqual(screenHandler.getCursorStack().getItem(), item)) {
            return -2;
        }
        IntRange intRange2 = this.getSlotRange2(screenHandler.slots.size());
        int n2 = intRange.getFirst();
        if (n2 <= (n = intRange.getLast())) {
            while (true) {
                if (Intrinsics.areEqual(screenHandler.getSlot(n2).getStack().getItem(), item) && !Intrinsics.areEqual(ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().get(n2 - intRange.getFirst() - (intRange.getFirst() == 0 ? intRange2.getFirst() : 0)), item)) {
                    return n2;
                }
                if (n2 == n) break;
                ++n2;
            }
        }
        return -1;
    }

    private final IntRange getSlotRange(int n) {
        return switch (n) {
            case 46 -> new IntRange(9, 44);
            case 63 -> new IntRange(0, 62);
            default -> new IntRange(0, 89);
        };
    }

    private final IntRange getSlotRange2(int n) {
        return switch (n) {
            case 46 -> new IntRange(9, 44);
            case 63 -> new IntRange(27, 62);
            default -> new IntRange(54, 89);
        };
    }

    private final ArrayList findJunkSlots(ScreenHandler screenHandler) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int n = screenHandler.slots.size();
        if (n != 46 && n != 63 && n != 90) {
            return arrayList;
        }
        for (Object k : ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().keySet()) {
            int n2;
            Intrinsics.checkNotNullExpressionValue(k, "next(...)");
            int n3 = ((Number)k).intValue();
            Item item = (Item)ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().get(n3);
            if (Intrinsics.areEqual(item, Items.AIR) || Intrinsics.areEqual(screenHandler.slots.get(n2 = n3 + this.getSlotRange2(n).getFirst()).getStack().getItem(), item)) continue;
            Item item2 = item;
            ScreenHandler screenHandler2 = screenHandler;
            IntRange intRange = this.getSlotRange(n);
            int n4 = this.moveItemToRange(item2, screenHandler2, intRange);
            if (n4 == -2) {
                arrayList.add(n2);
                continue;
            }
            if (n4 == -1) continue;
            arrayList.add(n4);
            arrayList.add(n2);
            if (Intrinsics.areEqual(screenHandler.slots.get(n2).getStack().getItem(), Items.AIR)) continue;
            arrayList.add(n4);
        }
        return arrayList;
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        if (this.getCleaner() != InventoryManagerCleanerMode.OFF) {
            boolean bl = Globals.getMc().currentScreen instanceof InventoryScreen;
            if (Globals.getPlayer().currentScreenHandler instanceof GenericContainerScreenHandler && this.getCleanChests()) {
                ScreenHandler screenHandler = Globals.getPlayer().currentScreenHandler;
                Intrinsics.checkNotNull(screenHandler, "null cannot be cast to non-null type net.minecraft.world.inventory.ChestMenu");
                GenericContainerScreenHandler genericContainerScreenHandler = (GenericContainerScreenHandler)screenHandler;
                Screen screen = Globals.getMc().currentScreen;
                Intrinsics.checkNotNull(screen);
                String string = screen.getTitle().getString();
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = string;
                int n = genericContainerScreenHandler.getInventory().size();
                for (int i = 0; i < n; ++i) {
                    Slot slot;
                    Intrinsics.checkNotNullExpressionValue(genericContainerScreenHandler.getSlot(i), "getSlot(...)");
                    if (!slot.hasStack() || !this.getCleanerList().getItems().contains(slot.getStack().getItem()) || StringsKt.contains$default((CharSequence)string2, "\u0410\u0443\u043a\u0446\u0438\u043e\u043d", false, 2, null) || StringsKt.contains$default((CharSequence)string2, "\u043f\u043e\u043a\u0443\u043f\u043a\u0438", false, 2, null)) continue;
                    this.dropSlot(i);
                }
            }
            if (this.getCleaner() == InventoryManagerCleanerMode.INVENTORY && !bl) {
                return;
            }
            if (this.getCleaner() == InventoryManagerCleanerMode.NOT_IN_INVENTORY && bl) {
                return;
            }
            for (int i = 0; i < 36; ++i) {
                if (!this.getCleanerList().getItems().contains(Globals.getPlayer().getInventory().getStack(i).getItem())) continue;
                this.dropSlot(i < 9 ? i + 36 : i);
            }
            if (t && timer.hasPassed(this.getCleanerDelay() + 100)) {
                PacketUtil.sendPacket(new CloseHandledScreenC2SPacket(Globals.getPlayer().currentScreenHandler.syncId));
                t = false;
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final ItemPreset getPresetSlots(int n, @NotNull String string) {
        Object object;
        void var11_9;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)InventoryManager.zkmBootstrap("r", (int)4730, (long)(0x2D312CE7EF949F35L ^ l))));
        Map map = new LinkedHashMap();
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3079606605857064909L, (long)l);
        CallSite callSite2 = InventoryManager.zkmBootstrap2("g", (int)30995, (long)(0xE2965F83A5EE0E1L ^ l));
        block0: while (var11_9 < InventoryManager.zkmBootstrap2("g", (int)22544, (long)(0xB6D45A638BC1EBL ^ l))) {
            object = map.put((int)(var11_9 - InventoryManager.zkmBootstrap2("g", (int)30995, (long)(0xE2965F83A5EE0E1L ^ l))), Globals.getPlayer().currentScreenHandler.slots.get((int)var11_9).getStack().getItem());
            do {
                ++var11_9;
                if (callSite != null) continue block0;
                object = new ItemPreset(string, map);
            } while (n <= 0);
        }
        return object;
    }

    private final void dropSlot(int n) {
        if (timer.hasPassed2(this.getCleanerDelay())) {
            InteractionUtil.clickSlot$default(n, 1, SlotActionType.THROW, false, 8, null);
            t = true;
        }
    }

    private static final Unit _clinit_$lambda$0() {
        ClickGuiScreen.INSTANCE.setComponent(new ItemPresetEditor());
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getCleaner() != InventoryManagerCleanerMode.OFF;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getCleaner() != InventoryManagerCleanerMode.OFF;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        InventoryManager.a = ZkmKeys.a(-6680767085894232486L, -3152355307944075782L, MethodHandles.lookup().lookupClass()).a(209676086885715L);
                        var20 = InventoryManager.a ^ 114226383160323L;
                        InventoryManager.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[32];
                        var16_4 = 0;
                        var15_5 = "\u008a\u00e2\u00f5\u00fb\u00e3\u00b0 n\u00cc\u00b0A\u0016h\u00e4\u00c3\u00b6o\u001d\u00f4\u00f2\u008a\u0084df\u0084\u0098\u00f5\u00fa9[%\u009e8g\u00b9\u0083\u00d4\u00f8O\u00b1\u0011&\u00ee\u00d3\u00ee\u0002wR\u00de \u0015\u0000\u00b0\u0002\u0091\\}o\u00e6\r&\u0089oD\u00aa2%d\u0096\u00aa]\u0006\tT\u000f\u00ffR\u00a6\\\u00b7\u0085\u00bd\u00c5\u00b4\u00ce\u00fcEh\u00d7(4\u0011%>0\u00e0M\u00d2\u00f0\u0092\u0016\u0083\u0097l\u00e1\u00a1\u00da\tQ@\u008f8 \u007f\u00fe$\u001b\u00fdyJ\no\u001a\u00c7\u0001\u008c\u00ab^\u00da\u0085(M\u00bc\u0004\u00f37\u00ab\u00f3\u00d2\u00b6\u0094J+\u00b7\u00c2\u00bd\u0080Oy]\u00ce\u00bf\u00a3\u001b\u00far\u00b6@E\u0095\u0006R6 \u00b0\u00b0W\u0018Q $ r\u0016\u00b3%\u00f5\f\"g\u0093'\u0086\u00be\u0090\u0019\u00d8(\u00faFry\u0011g\u00b9\u00d2H\f\u00bf\rR\u00ef\u0087\u00ee t\u00dd#\u00b7\u00c4\u00a8\u00f7\u00ef\u00f0\u00eb\u0004M\u00f0\u0093F\u00a3\u00f34\u00e1E0~\u00d3q6\u00ab-\u00d6\u00ae\u00eb\u00eeXX\b$6\u00f7|\u00be\u00bc \u00cbTw\u00fc>\u0082\u0087\u0082s\u001c\u0002\u00d5\u009ay\u00ba\u001cN\u0000\u00d1\u00c9P\u0087\u00b4\u0091\u00e0s\u00a6\u00daD\u001b\u00ddXH\u00a8fvj\u0098\u00e4\u00c8\u008e\u0004x\u0095\u0001\u0085\u00e8\u000e\u000e\u00ccQ\u00e8\u0099Ja\u00e7\u008aO\u00c4i\u0083\u0002\u0099\u0089\u00e8\u00a2:\u0015\u008f\u009e\u00acq#C\u0019A\u00fd\u00a1\tS\u0018\u009d\u008c}I\u001c\u00dc\u00aa\n\u00ac\u00ef-:\u00c9(\u00edU\u0010=\b\u00c36\u00f3\u00d0\u001d\u0010\u001b\u0000\u008d;\u00fb<\u007f\t%\u0018\u0018k\u00cc\u00c4\u00a5\u0010 \u00e2\u00a9\u00e7s\u00de\u00f9!}\u00d0\u00f8\u00c2#n\u00e5)\u00dc\u001ft$#=\u00d6\u00b9\u0011\u00d2\u009e\u00ab\u0018\u00caq\u0005'0\u0000\u00f1\u00fa\u009c\u000evZ\u00ea\u00d5\u00d1\u0016\u0096\u00e7|\u00dd\u00c0\u008a\u001d7\u00978\u009eg\u00a7\u00a9\u0093fmX\u00f5\u00ec\u00f7T\b\u00b9\u00c56\u001e\u0014z\u00fcYU\u0017\u00c2\u00b0\u00d1=8G\u00f7\b\u000f\u009b\u00daM\u00c9\u00a8\u00d8\u00a2[\u00165\n\u00d3\u00f40P\u000e\u00c5\u0016\u00e2g\u00c4kj\u00d7\u00f2\u00cb\u0098\u00c4\u0003\u0096\u001a\u00b1wb\u00f0{\u0019\u00f0\u0084l\u0090\u008dz\u0001\t\u000b\u00e3\u00bd\u00bc\u0097\u0098\n\u0080\u0085\u009c\u0019\u0082\u009a\u008bt\u0019\u00ca\u00f3\u0012\r\u00f1\u0005\u00db\u001b#\u00af\u009b\u0014.*PoM\u00f4n\u0010\u00dd\u00d3\u0086\u00cf\u009f-\u00d1\u00beu\u0096i\u00cfe\u00f3\u00deA\u0087\u00e5\u0006\u00f2V\u0016(\u00d79\u00df\u0083\u0013\u00f1\u00e6\u00ff\u0018\t\u0083\u007fj!G\u00edg\u0014$B\u009c@\u00df\u00be\u00aes\u00e8\u00a9T\u00b2\u00f9@\u00a1\u009d/\u00b5\u001b\u0094E\u00e0W\u0016 4\u0006\u00ec9'\u00e0*\u00c3\u0006e\u00e4O\u00c6\r\u0093\u0082\u00df\u00fa\"\u00d1\u00d6\u00b7[^\u0011\u00a0\u00aaJ\u0086\u00b1e\\L\u009a\u0018\u000bW\u0005\u008c\u000bU\u00bc-\u0018\u0098Z\u0016q\u008ff\u00ff\u00b7kX\u00e7Bi/\u00f1 )\u00b7\u00b7\u008b\u00ffC\u001a$\u00d7\u00ebq\u001d\u0015\u00afk\u00b2\u001da\u00d1@?\u00bc\u00ee\u0002,\u0016\u00a3\u0092\u0082\u00d3\u00e8O\u0018\u00a4\u00e1\u0093\u0094s\u00c6Q`\u00c9[\u0097`\u008c\u00dc\u00aa\u009b9n\u00c1G\u00ce\u00eb\u00ceM \u00fc&\u00b0\u00acb\u0014\u00d6\u00cf#\u00c5!\u00a0\u008fa3\u0096i\u00a4\u00d2G\u00a9: a\u0001\u0012\u00c6\u00d5\u00dc\u0005\u00b5\u0013 \u00cb\u00d3pVH\u0016\u001e\u0094\u00eeW\u00f0 \u00c2\u00f7l\u00c9\u00ac9G\u00a9\u00a1\u0005Y\u00bbZ\u00db\u008a\u0013\u00be\u00d9\u001fo\u0018\u00e6\u0015\u00b5\u00c4O\u0001.\u00e4\u0012xQ0\u0007\u00e28{\u00e7\u007f\u00be\u008f\u00d9\u00cc\u0011g \u00e5\u0084\u00d1\u00a6}mhGf\u00e1K\u00c2\u009b\u00c9\u00bbh\u00b7hoY\u008bl+\u00e8l\u00a7\u00cb\u0019\u008ee\u00fa\u0091\u0018\u0092\u00db\u00e3\u0017unUE\u0010\u00b3T\u00c3\u00fd\u00ff\u00a5\u00c2\u00a6\u00dc\u0002\u0088sH\u00ea\u0005(8\u00cd\u00c5\u0085\u0087\u00a3\u00ce1<\u000bHJ\u0095D=\u00ab\u00c3\u00ab5\u001fL\u00aa\u000b@\f\u0017\u00e3\u00f2\u0098\u0092\u0003\nI\u0014yC\u0094\rh\u00ba z_\u00a8C\u00fa\u0017s\u00eaX\u00ba\u00e17\u0015y?S\u0017\u00f2\u000e\u00e1\u00ca\u00b3\u000f\u00eck`*a\u008b\u00a6`\u00e4(\u00eb\u0005\u001dQ\u00e0\u00d95H\u00b9!\u0015'\u00dcZ\u00ad\u00b9\u00ff\u00cd\u00f8'\u0087\u00c1}\u00df\u00040y\u00a7\u00d7P\u00f7g\u00c0E\u00b2H\u001d\u00c1\u0015\u00e2 \u00e2\u0098\u00c9\u00f2\u00c4o\u00a2Q\u00e5\u00eb\u00c5\u00b6N\u00ce5\u00a2Gn\u00b1`I\u00c7\u009a\u00d3\u0096O&o[]jv(E\u00a8A-\u000f\u00af\u008bd\u00d8\u001c\u00c6\u008f\u00f7;\u00e21\u008a\u00b7gH\u00ce-\u00a3\u00f6D\u00ae\u0084\u00db}H\u00dc\u00b7\u00ed\u00b0\u00fdk\\x\u00ae\u00da\u0010\u00db\u00fe\r\u00ca\u008d/\u00deT\u00e3\u00ec\u0011D\u00fb\u008em\u008b \u00fdX\u008c[#\f\u00dc\u0096h\u00fcd\u00d9\u00a4\u00e3\u00178\u0018w*\u00det\u00c1\u00de\u000f\u0014\u001a\u00c1\u00cbk\u00d4\tA\u0010\u00edd\u000e\u00das\u00c5\u001d\u00da\u00d9.%\u00be\u0014\n\u0018&\u0018f\u00cb\u00ae\u00e8\u00df\u00af\u00cc\u00d6\u00b0\u00daI\u00eaM\u00143a\u00a7\u00bb\u001b\u00fa,\u00d4\u00ad\u00b7";
                        var17_6 = 1149;
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
                            var18_3[var16_4++] = InventoryManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00cbE\u001d\u00de3\u00d47\u00c3`\u00bd`\u00a2\u0096\u00b0#2r\u009c\u0097\u0016=\u0010 \u007f \u007f\u00cc\\Z\u00b9\u0007]\u00a1)\u00bf\u0080\u008a30\u00c2\u0095\u00e4\u00a4\u00dc\u008c\u00b4\u00ba\u0002\u00b3\u00d4Q%\u0011\u00d5\u0006Et";
                            var17_6 = 57;
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
                            var18_3[var16_4++] = InventoryManager.zkmDecodeUtf8(var19_9).intern();
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
                InventoryManager.d = var18_3;
                InventoryManager.f = new String[32];
                InventoryManager.j = new HashMap<K, V>(13);
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
                var6_12 = new long[32];
                var3_13 = 0;
                var4_14 = "\u00e5\u00b7\u00ecu\u00f8\u00a3\u008a\u00ec\u00e3\u00eb\u009ax0\u00ee\u001f\u001c!\bz\u0081\u00bat\u0088\u00f7\u00cc\u00bb7\u0097\u00a7\u00ae\u0086\u00f6\u00f0=\u0088\u00bfe\u00c3\u00a8\u00f1\u00bcL\u00b1\u00da[\u00a4\u00dd*\u0091\u0007X\u0006\u007f!\u00daZ$L\u00cb\u0095\u00b4\u001a\u0091\u0010\u00df\u007f\u00d8\u00f3~U\u00eee\u00b7\u00ac\u0005\u00f8\u00ec\u00aab\u008eg0\u001b:t\u0090vj\u00e5\u00e4\u00bc\u00f0\u00aa\u0004\u00ea]\u00b4\u0018\u008e+\u00c9\u0010\t\u00c3T\u001218\u001d\u00f9\u00ef\u009a\u001fTN\"\u00ee\u00d0\u00d4\u00c3\u00aa\u00fb\u00c4\u00eerj\u008ff\u00ba\u0093T\u00da\u00c3E\u00ab\u00ae\u00bc\u0001k\u00a6\u0003[t\u00af\u00b2\u00fd\u00e2\"\u00f9xV\u00c7]\u00ef;+\u00bcr\u00e9\u00ceu9\u0005F\u00a9~\u00d3\u00baQ\u00b4w\u00fbYF`\u009d\n*_M\u00d9+\u0098\u00dbO'\u00be\u00dc\u0006b\u00cc'\r_\u0081\u00bdao\b\u0006\u00dc\u00cc]j\u00ec;!~\u00aa6$\u0003\u00cc\u00b9y\u00bb6\u0092\u00deQ\u00c2\u0015\u00f0\u0004\u0084L\u001b\u00cf-[M)~\u0087\u008al\u00a8C\u0094v";
                var5_15 = 240;
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
                    var4_14 = "\u00af\u00a54\u00a6\u00da\u007fc\u00c3\u00cc\u00dfH4\u0093j\u0080\u00e5";
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
        InventoryManager.h = var6_12;
        InventoryManager.i = new Integer[32];
        var37_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(InventoryManager.class, "openManager", "getOpenManager()Lsu/catlean/id;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(InventoryManager.class, "cleanerList", "getCleanerList()Lsu/catlean/api/setting/values/ItemsValue;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(InventoryManager.class, "actionDelay", "getActionDelay()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(InventoryManager.class, "clicksPerAction", "getClicksPerAction()I", 0)), Reflection.property1(new PropertyReference1Impl(InventoryManager.class, "cleaner", "getCleaner()Lsu/catlean/r7;", 0)), Reflection.property1(new PropertyReference1Impl(InventoryManager.class, "cleanerDelay", "getCleanerDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(InventoryManager.class, "cleanChests", "getCleanChests()Z", 0))};
        InventoryManager.delegatedProperties = var37_20;
        InventoryManager.INSTANCE = new InventoryManager();
        InventoryManager.openManager$delegate = SettingFactory.buttonSetting$default(InventoryManager.INSTANCE, "open-manager", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null, null, 12, null);
        var38_21 = null;
        var39_22 = 12;
        var40_23 = null;
        var41_24 = null;
        var42_25 = new ItemListValue(null, null);
        var43_26 = "cleaner-list";
        var44_27 = InventoryManager.INSTANCE;
        InventoryManager.cleanerList$delegate = SettingFactory.itemListSetting$default(var44_27, var43_26, var42_25, var41_24, var40_23, var39_22, var38_21);
        InventoryManager.actionDelay$delegate = SettingFactory.intSetting$default(InventoryManager.INSTANCE, "action-delay", 0, new IntRange(0, 10), null, null, 24, null);
        InventoryManager.clicksPerAction$delegate = SettingFactory.intSetting$default(InventoryManager.INSTANCE, "click-per-action", 1, new IntRange(1, 3), null, null, 24, null);
        InventoryManager.cleaner$delegate = SettingFactory.modeSetting$default((SettingContainer)InventoryManager.INSTANCE, "cleaner", (Enum)InventoryManagerCleanerMode.OFF, null, null, 12, null);
        InventoryManager.cleanerDelay$delegate = SettingFactory.intSetting$default(InventoryManager.INSTANCE, "cleaner-delay", 50, new IntRange(0, 500), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        InventoryManager.cleanChests$delegate = SettingFactory.booleanSetting$default((SettingContainer)InventoryManager.INSTANCE, "clean-chests", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        InventoryManager.timer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x26BB;
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
                throw new RuntimeException("su/catlean/q4", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            InventoryManager.f[n2] = InventoryManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = InventoryManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3209;
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
                throw new RuntimeException("su/catlean/q4", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            InventoryManager.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = InventoryManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(InventoryManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(InventoryManager.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

