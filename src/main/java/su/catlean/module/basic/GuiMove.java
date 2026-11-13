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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.ClickSlotEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientInputAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.GuiMove$WhenMappings;
import su.catlean.module.basic.GuiMoveClickBypassMode;
import su.catlean.module.basic.Sprint;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.ClickSlotAction;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class GuiMove
extends Module {
    @NotNull
    public static final GuiMove INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting clickBypass$delegate;
    @NotNull
    private static List V;
    @NotNull
    private static final LinkedList queuedClicks;
    @NotNull
    private static final AtomicBoolean isClicking;
    private static boolean Y;
    private static boolean A;
    private static int K;
    private static boolean D;
    private static boolean f;
    @NotNull
    private static final Map U;
    @NotNull
    private static final Map d;
    private static boolean macroActive;
    private static Module[] m;
    private static final long a;
    private static final String[] b;
    private static final String[] g;
    private static final Map h;
    private static final long[] i;
    private static final Integer[] j;
    private static final Map l;

    private GuiMove() {
        super("gui-move", Categories.getBasic(), null, null);
    }

    @NotNull
    public final GuiMoveClickBypassMode getClickBypass() {
        return (GuiMoveClickBypassMode)((Object)clickBypass$delegate.getValue(this, delegatedProperties[0]));
    }

    @NotNull
    public final Map Y() {
        return U;
    }

    @NotNull
    public final Map j() {
        return d;
    }

    public final boolean isMacroActive() {
        return macroActive;
    }

    public final void setMacroActive(boolean bl) {
        macroActive = bl;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Object object;
        long l;
        long l2 = l = a ^ 0x3C4A72835801L;
        long l3 = l2 ^ 0x2D9F81B7FDC5L;
        int n = (int)((l2 ^ 0x78782A22DB10L) << 48 >>> 48);
        if (V.isEmpty()) {
            object = new KeyBinding[6];
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().options.forwardKey, "keyUp");
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().options.backKey, "keyDown");
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().options.leftKey, "keyLeft");
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().options.rightKey, "keyRight");
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().options.jumpKey, "keyJump");
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().options.sprintKey, "keySprint");
            V = CollectionsKt.listOf(object);
        }
        if (!(f && this.getClickBypass() == GuiMoveClickBypassMode.LEGIT || Globals.getMc().currentScreen == null || Globals.getMc().currentScreen instanceof ChatScreen)) {
            object = V;
            Iterator iterator2 = object.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                KeyBinding keyBinding = (KeyBinding)t;
                int n2 = InputUtil.fromTranslationKey(keyBinding.getBoundKeyTranslationKey()).getCode();
                char c = (char)n;
                keyBinding.setPressed(KeyboardUtil.isKeyPressed(c, n2));
            }
        }
        if (this.getClickBypass() == GuiMoveClickBypassMode.LEGIT && !((Collection)queuedClicks).isEmpty() && Y || K != 0) {
            switch (K) {
                case 0: {
                    Sprint.INSTANCE.h(true);
                    D = Globals.getPlayer().isSprinting();
                    Globals.getPlayer().setSprinting(false);
                    MovementUtil.INSTANCE.releaseMovementKeys();
                    K = 1;
                    break;
                }
                case 1: {
                    Sprint.INSTANCE.h(true);
                    isClicking.set(true);
                    this.saveInventory(d);
                    this.restoreInventory(U);
                    while (!queuedClicks.isEmpty()) {
                        ClickSlotAction clickSlotAction = (ClickSlotAction)queuedClicks.poll();
                        ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getMc().interactionManager;
                        if (clientPlayerInteractionManager == null) continue;
                        clientPlayerInteractionManager.clickSlot(clickSlotAction.getSyncId(), clickSlotAction.getSlotId(), clickSlotAction.getButton(), clickSlotAction.getActionType(), Globals.getPlayer());
                    }
                    PacketUtil.sendPacket(new CloseHandledScreenC2SPacket(Globals.getPlayer().currentScreenHandler.syncId));
                    isClicking.set(false);
                    Y = false;
                    Sprint.INSTANCE.h(true);
                    Globals.getPlayer().setSprinting(D);
                    MovementUtil.INSTANCE.releaseMovementKeys(l3);
                    K = 0;
                    this.restoreInventory(d);
                }
            }
            return;
        }
    }

    @Flow
    private final void onClickSlot(ClickSlotEvent clickSlotEvent) {
        if (macroActive || isClicking.get()) {
            return;
        }
        if (!MovementUtil.INSTANCE.isMoving() && !Globals.getMc().options.jumpKey.isPressed() || Globals.getPlayer().isGliding()) {
            f = true;
            return;
        }
        switch (GuiMove$WhenMappings.Z[this.getClickBypass().ordinal()]) {
            case 1: 
            case 2: {
                double d;
                PlayerEntity playerEntity = Globals.getPlayer();
                SlotActionType slotActionType = clickSlotEvent.getAction();
                int n = clickSlotEvent.getButton();
                int n2 = clickSlotEvent.getId();
                int n3 = clickSlotEvent.getSyncId();
                this.clickSlot(n3, n2, n, slotActionType, playerEntity);
                double d2 = d = this.getClickBypass() == GuiMoveClickBypassMode.STRICT_NCP ? 0.0656 : 2.71875E-7;
                if (!Globals.getPlayer().isOnGround() || Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().offset(0.0, d, 0.0)).iterator().hasNext()) break;
                if (Globals.getPlayer().isSprinting()) {
                    PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                    A = true;
                }
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().getX(), Globals.getPlayer().getY() + d, Globals.getPlayer().getZ(), false, Globals.getPlayer().horizontalCollision));
                break;
            }
            case 3: {
                PlayerEntity playerEntity = Globals.getPlayer();
                SlotActionType slotActionType = clickSlotEvent.getAction();
                int n = clickSlotEvent.getButton();
                int n4 = clickSlotEvent.getId();
                int n5 = clickSlotEvent.getSyncId();
                this.clickSlot(n5, n4, n, slotActionType, playerEntity);
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.STOP_SPRINTING));
                Globals.getMc().options.forwardKey.setPressed(false);
                Input input = Globals.getPlayer().input;
                Intrinsics.checkNotNull(input, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
                ((ClientInputAccessor)((Object)input)).setMoveVector(new Vec2f(Globals.getPlayer().input.getMovementInput().x, 0.0f));
                break;
            }
            case 4: {
                if (Globals.getPlayer().currentScreenHandler.slots.size() != 46) {
                    return;
                }
                if (queuedClicks.isEmpty()) {
                    this.saveInventory(U);
                }
                PlayerEntity playerEntity = Globals.getPlayer();
                SlotActionType slotActionType = clickSlotEvent.getAction();
                int n = clickSlotEvent.getButton();
                int n6 = clickSlotEvent.getId();
                int n7 = clickSlotEvent.getSyncId();
                this.clickSlot(n7, n6, n, slotActionType, playerEntity);
                queuedClicks.add(new ClickSlotAction(clickSlotEvent.getSyncId(), clickSlotEvent.getId(), clickSlotEvent.getButton(), clickSlotEvent.getAction()));
                clickSlotEvent.cancel();
                break;
            }
            case 5: {
                clickSlotEvent.cancel();
                break;
            }
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (macroActive) {
            return;
        }
        if (receivePacket.getPacket() instanceof CloseScreenS2CPacket) {
            f = false;
            if (this.getClickBypass() == GuiMoveClickBypassMode.DISABLE_CLICKS) {
                receivePacket.cancel();
            }
            if (this.getClickBypass() == GuiMoveClickBypassMode.LEGIT && !isClicking.get() && !((Collection)queuedClicks).isEmpty()) {
                receivePacket.cancel();
                Y = true;
            }
        }
        if (receivePacket.getPacket() instanceof OpenScreenS2CPacket) {
            f = true;
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (macroActive) {
            return;
        }
        if (sendPacket.getPacket() instanceof CloseHandledScreenC2SPacket) {
            f = false;
            if (this.getClickBypass() == GuiMoveClickBypassMode.DISABLE_CLICKS) {
                sendPacket.cancel();
            }
            if (this.getClickBypass() == GuiMoveClickBypassMode.LEGIT && !isClicking.get()) {
                if (!((Collection)queuedClicks).isEmpty()) {
                    Y = true;
                }
                sendPacket.cancel();
            }
        }
    }

    @Flow
    private final void onAfterSendPacket(AfterSendPacket afterSendPacket) {
        block1: {
            if (macroActive) {
                return;
            }
            if (!(afterSendPacket.getPacket() instanceof ClickSlotC2SPacket) || !A || this.getClickBypass() != GuiMoveClickBypassMode.STRICT_NCP) break block1;
            PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_SPRINTING));
            A = false;
        }
    }

    private final void clickSlot(int n, int n2, int n3, SlotActionType slotActionType, PlayerEntity playerEntity) {
        block0: {
            if (n != playerEntity.currentScreenHandler.syncId) break block0;
            playerEntity.currentScreenHandler.onSlotClick(n2, n3, slotActionType, playerEntity);
        }
    }

    private final void saveInventory(Map map) {
        for (int i = 0; i < 46; ++i) {
            map.put(i, Globals.getPlayer().currentScreenHandler.getSlot(i).getStack().copy());
        }
    }

    private final void restoreInventory(Map map) {
        for (int i = 0; i < 46; ++i) {
            ScreenHandler screenHandler = Globals.getPlayer().currentScreenHandler;
            int n = Globals.getPlayer().currentScreenHandler.getRevision();
            Object v = map.get(i);
            Intrinsics.checkNotNull(v);
            screenHandler.setStackInSlot(i, n, (ItemStack)v);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        GuiMove.a = ZkmKeys.a(-4133011456643079438L, 4599371866093213342L, MethodHandles.lookup().lookupClass()).a(211928457330695L);
                        var20 = GuiMove.a ^ 135887530408709L;
                        GuiMove.h = new HashMap<K, V>(13);
                        GuiMove.zkmSetFlag(null);
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
                        var15_5 = "\u000e\u00dc\u00d9w32\u00bc]\u009e`\u0086H\u0099,Q\n\u00fe\u00ff\u0002\u00c6\u0010\u008f\u00da\u0083\u00bc\u00f6\u00ad\u00ab\u00b4=Fz\u0010_\"\u00bc\u00d0\u00f7\u00dd\b3?\u00d9\u00e4\u0006~\u008a\u00ad\u00dc\u00106W\u0083mI\u00eb\r\u00cfx}V\u00a1\u0012\u0097\u00a9\u001a \u00c5\u00cc\u00c2\u00b5\\\u001b\u0018J\u00baGi\u0091\u009f}\u00076\u00b4\u00f1D{`\u00a1\u0004H\u00b9\u00ff\u0095m\u00d7\u00eb\u001e\u00bf\u0010\u00d7\u00cb\u0087\fx\u008b?\u00be\u009c\u000f\"b\fW\u00c4\u00bc@*z\u0005\u00ael\u0019\u0082\u0082\u00e8\u000bG\u001a\u00eaH \u00ade)t\u0092f\u0014[z\u00f7\u00da32\u00a4\u00c9\u00aaH\u00e8<\u008f\u0019+$O\u00f1<\u0085\u008d#V\u00e6TN\u0016\u00da\u00ed,J\u00fb<D\u007f\u0012\u0093\u00cc\u0096Z\u00d0\u008c\u0010\u00f3\u000e\u0081\n\u008aP\u008dr3\u00c0\u00a5Q\u0089\u0086\u00b9\u0099 \u00e6\u009f\u00f6I\u00d9\u0092\u0004\u009bX\u00e3\tMD\u00can\u00b1\u0000;\u00cb\u000f\u0084`u\u0013A\u0016r\u00b7\u00f4\u00b9\u0094\u0014 \u00ee\u00e2pt\u00b0\u009e\u00b7\u00ca\u00ea\u00d8\u00fcbw\u008d\u00a4m,\u00c2|\u00ac\u0092\u00a8\u00a9\u00914\u00f5h\u009aw\u0082\u0000V";
                        var17_6 = 264;
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = GuiMove.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ae\u00b0-\u00aeT\u0005\n\u00bdyemG\u00eego\u00deP\u00e4\u00f2kOb\u00a9\u0014#\u001a\u00cd\u00bb\u00128\u0099\u00cd\u00c9#\u0098,\u008a,\u00a1|_ts{\u001crk\u00b5\u0094;i\u0089K\u00f1\u00cffV\u001b\u00c6v\u0089/\u0003g\u00fb\u0092\r\u00c6~%\r\u0001a\u0085\u0093\rw\u00fe&\u0080Q\u00de\u00a1\u001a\u00cc\u00e7\u00a6q]\u001d\\Z\u00e1\u00e6\u009d\u008e\u00e3\u00f4\u0095g!\u00d9x\u00b4CE0\u00aa\u00d4\u00d8\u0085\u00c7t\u00e87h\u0015QJ\u00f4\u009av\u0012\rM\u0015@V\u0010e\u00de\u00ccF\u00c9\u00d1n\u00e0\u007f\u00bb9\u00d6\u00c8i\u0087\u00cf";
                            var17_6 = 145;
                            var14_7 = 128;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = GuiMove.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                GuiMove.b = var18_3;
                GuiMove.g = new String[11];
                GuiMove.l = new HashMap<K, V>(13);
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
                var4_14 = "\u0017j\u00c5x8P\u009fB\u00a1\u008e\u00b0\u00b2\u00cd\u00d0 \u0015";
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u008b\u0006|9\u00e55\u0081\u009a\u00fc\u00b2\u00a2D\u00ec\u00d5!\u0005";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        GuiMove.i = var6_12;
        GuiMove.j = new Integer[4];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(GuiMove.class, "clickBypass", "getClickBypass()Lsu/catlean/su;", 0))};
        GuiMove.delegatedProperties = var26_20;
        GuiMove.INSTANCE = new GuiMove();
        GuiMove.clickBypass$delegate = SettingFactory.modeSetting$default((SettingContainer)GuiMove.INSTANCE, "bypass", (Enum)GuiMoveClickBypassMode.NONE, null, null, 12, null);
        GuiMove.V = CollectionsKt.emptyList();
        GuiMove.queuedClicks = new LinkedList<E>();
        GuiMove.isClicking = new AtomicBoolean();
        GuiMove.U = new LinkedHashMap<K, V>();
        GuiMove.d = new LinkedHashMap<K, V>();
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        m = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return m;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5F62;
        if (g[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_n", exception);
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
            GuiMove.g[n2] = GuiMove.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GuiMove.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_n" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3A09;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])GuiMove.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    GuiMove.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_n", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            GuiMove.j[n2] = n3;
        }
        return j[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = GuiMove.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_n" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GuiMove.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(GuiMove.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

