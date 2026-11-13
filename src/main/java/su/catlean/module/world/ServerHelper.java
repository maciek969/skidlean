/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  org.joml.Matrix3x2fStack
 *  org.joml.Vector4d
 */
package su.catlean.module.world;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import org.joml.Vector4d;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.SlotRenderEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.ExpiringItemMarker;
import su.catlean.util.Globals;
import su.catlean.util.Scheduler;
import su.catlean.util.TradeOffer;
import su.catlean.zkm.ZkmKeys;

public final class ServerHelper
extends Module {
    @NotNull
    public static final ServerHelper INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting aucHelper$delegate;
    @NotNull
    private static final BooleanSetting structureCooldowns$delegate;
    @NotNull
    private static final List itemMarkers;
    @NotNull
    private static final List tradeOffers;
    @NotNull
    private static final IntArrayList intArrayList;
    private static final long b;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;
    private static final long j;

    private ServerHelper() {
        super("server-helper", Categories.getWorld(), null, null);
    }

    private final boolean getAucHelper() {
        return (Boolean)aucHelper$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getStructureCooldowns() {
        return (Boolean)structureCooldowns$delegate.getValue(this, delegatedProperties[1]);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (this.getStructureCooldowns() && receivePacket.getPacket() instanceof PlaySoundS2CPacket) {
            if (Intrinsics.areEqual(((PlaySoundS2CPacket)receivePacket.getPacket()).getSound().comp_349(), SoundEvents.BLOCK_PISTON_CONTRACT) || Intrinsics.areEqual(((PlaySoundS2CPacket)receivePacket.getPacket()).getSound().comp_349(), SoundEvents.BLOCK_PISTON_EXTEND)) {
                Scheduler.schedule$default(Scheduler.INSTANCE, 0, (byte)0, false, () -> ServerHelper.onReceivePacket2(receivePacket), 7, null);
            } else if (Intrinsics.areEqual(((PlaySoundS2CPacket)receivePacket.getPacket()).getSound().comp_349(), SoundEvents.BLOCK_ANVIL_PLACE)) {
                BlockPos blockPos = BlockPos.ofFloored(((PlaySoundS2CPacket)receivePacket.getPacket()).getX(), ((PlaySoundS2CPacket)receivePacket.getPacket()).getY(), ((PlaySoundS2CPacket)receivePacket.getPacket()).getZ());
                Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
                BlockPos blockPos2 = blockPos;
                Scheduler.schedule$default(Scheduler.INSTANCE, 5, (byte)0, false, () -> ServerHelper.onReceivePacket$lambda$1(blockPos2), 6, null);
            }
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l;
        long l2 = l = b ^ 0x622328229A72L;
        long l3 = l2 ^ 0x3F23AC57A3DFL;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x519EBC1C1E5EL;
        long l5 = l2 ^ 0x5EAE00340F10L;
        long l6 = l2 ^ 0x56E4ECDC1FC8L;
        if (this.getStructureCooldowns()) {
            itemMarkers.removeIf(arg_0 -> ServerHelper.invokePredicate(ServerHelper::onRender2D$lambda$0, arg_0));
            Iterable iterable = itemMarkers;
            for (Object t : iterable) {
                ExpiringItemMarker expiringItemMarker = (ExpiringItemMarker)t;
                Vector4d vector4d = RenderUtil.INSTANCE.worldToScreen(expiringItemMarker.getPos());
                int n4 = (int)((double)(expiringItemMarker.getExpiresOn() - Globals.now()) / 50.0);
                int n5 = n4 / 1200;
                String string = "%02d";
                Object[] objectArray = new Object[]{n4 % 1200 / 20};
                String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue(string2, "format(...)");
                String string3 = n5 + ":" + string2;
                float f = Fonts.getSF_M().getWidth(l4, string3) + 22.0f;
                if (vector4d == null) continue;
                float f2 = (float)(vector4d.x + (double)((float)(vector4d.z - vector4d.x) / 2.0f) - (double)(f / 2.0f));
                float f3 = (float)vector4d.y;
                Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                Shapes2D.drawShape$default(matrix3x2fStack, (char)n, f2, f3, f, 16.0f, 3.0f, 1.5f, 0.0f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
                render2DEvent.getContext().getMatrices().pushMatrix();
                render2DEvent.getContext().getMatrices().translate(f2 + 2.0f, f3 + 2.0f);
                render2DEvent.getContext().getMatrices().scale(0.7f);
                render2DEvent.getContext().drawItem(expiringItemMarker.getItem().getDefaultStack(), 0, 0);
                render2DEvent.getContext().getMatrices().popMatrix();
                Matrix3x2fStack matrix3x2fStack2 = render2DEvent.getContext().getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                Color[] colorArray = new Color[]{new Color(0x44FFFFFF, true)};
                RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f2 + 14.0f, f3 + 4.0f, 0.5f, 9.0f, colorArray, l6);
                Fonts.getSF_M().drawStringWithShadow(render2DEvent.getContext(), string3, l5, f2 + 18.0f, f3 + 3.0f, Theme.INSTANCE.getTextColor());
            }
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (Globals.getPlayer().currentScreenHandler instanceof GenericContainerScreenHandler && this.getAucHelper()) {
            ScreenHandler screenHandler = Globals.getPlayer().currentScreenHandler;
            Intrinsics.checkNotNull(screenHandler, "null cannot be cast to non-null type net.minecraft.world.inventory.ChestMenu");
            GenericContainerScreenHandler genericContainerScreenHandler = (GenericContainerScreenHandler)screenHandler;
            Screen screen = Globals.getMc().currentScreen;
            Intrinsics.checkNotNull(screen);
            String string = screen.getTitle().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = string;
            if (StringsKt.contains$default((CharSequence)string2, "\u0410\u0443\u043a\u0446\u0438\u043e\u043d", false, 2, null) || StringsKt.contains$default((CharSequence)string2, "\u041f\u043e\u0438\u0441\u043a", false, 2, null)) {
                int n;
                ItemStack itemStack;
                tradeOffers.clear();
                Map map = new LinkedHashMap();
                int n2 = 0;
                Iterator iterator2 = genericContainerScreenHandler.getStacks().iterator();
                Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
                Iterator iterator3 = iterator2;
                while (iterator3.hasNext()) {
                    itemStack = (ItemStack)iterator3.next();
                    if (n2 > 44) continue;
                    Intrinsics.checkNotNull(itemStack);
                    n = this.readLore(itemStack);
                    if (itemStack.getCount() > 1) {
                        n /= itemStack.getCount();
                    }
                    map.put(this.getItemKey(itemStack), Math.min(((Number)map.getOrDefault(this.getItemKey(itemStack), 999999999)).intValue(), n));
                    ++n2;
                }
                n2 = 0;
                Iterator iterator4 = genericContainerScreenHandler.getStacks().iterator();
                Intrinsics.checkNotNullExpressionValue(iterator4, "iterator(...)");
                iterator3 = iterator4;
                while (iterator3.hasNext()) {
                    itemStack = (ItemStack)iterator3.next();
                    Intrinsics.checkNotNull(itemStack);
                    if (map.get(this.getItemKey(itemStack)) != null) {
                        Item item;
                        n = this.readLore(itemStack);
                        if (itemStack.getCount() > 1) {
                            n /= itemStack.getCount();
                        }
                        Intrinsics.checkNotNullExpressionValue(itemStack.getItem(), "getItem(...)");
                        Integer n3 = (Integer)map.get(this.getItemKey(itemStack));
                        int n4 = n2;
                        int n5 = n3 != null ? n3 : 99999999;
                        int n6 = n;
                        tradeOffers.add(new TradeOffer(item, n6, n5, n4));
                    }
                    ++n2;
                }
            }
        }
    }

    private final String getItemKey(ItemStack itemStack) {
        String string = itemStack.getItem().getTranslationKey();
        Intrinsics.checkNotNull(string);
        return string;
    }

    @Flow
    private final void onSlotRender(SlotRenderEvent slotRenderEvent) {
        Slot slot = slotRenderEvent.getSlot();
        DrawContext drawContext = slotRenderEvent.getContext();
        if (Globals.getPlayer().currentScreenHandler instanceof GenericContainerScreenHandler) {
            Screen screen = Globals.getMc().currentScreen;
            Intrinsics.checkNotNull(screen);
            String string = screen.getTitle().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = string;
            if (StringsKt.contains$default((CharSequence)string2, "\u0410\u0443\u043a\u0446\u0438\u043e\u043d", false, 2, null) || StringsKt.contains$default((CharSequence)string2, "\u041f\u043e\u0438\u0441\u043a", false, 2, null)) {
                for (TradeOffer tradeOffer : tradeOffers) {
                    if (tradeOffer.getId() != slot.id || slot.id > 44 || slot.getStack().isEmpty()) continue;
                    float f = (float)Math.pow(tradeOffer.getLowestPrice(), 4.0) / (float)Math.pow(tradeOffer.getPrice(), 4.0);
                    int n = slot.x;
                    int n2 = slot.y;
                    int n3 = slot.x + 16;
                    int n4 = slot.y + 16;
                    Color color = Color.GREEN;
                    Intrinsics.checkNotNullExpressionValue(color, "GREEN");
                    drawContext.fill(n, n2, n3, n4, RenderUtil.INSTANCE.withAlpha(color, f).getRGB());
                    return;
                }
            }
        }
    }

    private final int readLore(ItemStack itemStack) {
        LoreComponent loreComponent = itemStack.getComponents().get(DataComponentTypes.LORE);
        if (loreComponent == null) {
            return 999999999;
        }
        LoreComponent loreComponent2 = loreComponent;
        Object object = Globals.getNetworkHandler().getServerInfo();
        if (object == null || (object = ((ServerInfo)object).address) == null) {
            object = "singleplayer";
        }
        if (StringsKt.contains$default((CharSequence)object, "holyworld", false, 2, null)) {
            Iterator<Text> iterator2 = loreComponent2.comp_2400().iterator();
            while (iterator2.hasNext()) {
                Text text;
                Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
                String string = text.getString();
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (!StringsKt.contains$default((CharSequence)string, "\u0426\u0435\u043d\u0430 \u0437\u0430 1 \u0435\u0434.", false, 2, null)) continue;
                Iterator<Text> iterator3 = text.getSiblings().iterator();
                while (iterator3.hasNext()) {
                    String string2;
                    Text text2;
                    Intrinsics.checkNotNullExpressionValue(iterator3.next(), "next(...)");
                    Intrinsics.checkNotNullExpressionValue(text2.getString(), "getString(...)");
                    CharSequence charSequence = string2;
                    int n = 0;
                    for (int i = 0; i < charSequence.length(); ++i) {
                        char c = charSequence.charAt(i);
                        char c2 = c;
                        if (!Character.isDigit(c2)) continue;
                        ++n;
                    }
                    if (n <= true) continue;
                    return Integer.parseInt(StringsKt.replace$default(StringsKt.replace$default(string2, " ", "", false, 4, null), "\u00a4", "", false, 4, null));
                }
            }
        } else {
            Iterator<Text> iterator4 = loreComponent2.comp_2400().iterator();
            while (iterator4.hasNext()) {
                Text text;
                Intrinsics.checkNotNullExpressionValue(iterator4.next(), "next(...)");
                String string = text.getString();
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (!StringsKt.contains$default((CharSequence)string, "\u0426\u0435\u043da", false, 2, null)) continue;
                String string3 = text.getString();
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                return Integer.parseInt(StringsKt.replace$default(StringsKt.replace$default(string3, "$ \u0426\u0435\u043da $", "", false, 4, null), ",", "", false, 4, null));
            }
        }
        return 9999999;
    }

    private final List getPositionsAround(BlockPos blockPos, int n, int n2) {
        int n3;
        List list = new ArrayList();
        int n4 = blockPos.getX() - n;
        if (n4 <= (n3 = blockPos.getX() + n)) {
            while (true) {
                int n5;
                int n6;
                if ((n6 = blockPos.getZ() - n) <= (n5 = blockPos.getZ() + n)) {
                    while (true) {
                        int n7;
                        int n8;
                        if ((n8 = blockPos.getY() - n2) <= (n7 = blockPos.getY() + n2)) {
                            while (true) {
                                list.add(new BlockPos(n4, n8, n6));
                                if (n8 == n7) break;
                                ++n8;
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
        return list;
    }

    private static final void onReceivePacket2(ReceivePacket receivePacket) {
        Vec3d vec3d = new Vec3d(((PlaySoundS2CPacket)receivePacket.getPacket()).getX(), ((PlaySoundS2CPacket)receivePacket.getPacket()).getY(), ((PlaySoundS2CPacket)receivePacket.getPacket()).getZ());
        long l = Globals.now() + j;
        Item item = Items.NETHERITE_SCRAP;
        Intrinsics.checkNotNullExpressionValue(item, "NETHERITE_SCRAP");
        itemMarkers.add(new ExpiringItemMarker(vec3d, l, item));
    }

    private static final void onReceivePacket$lambda$1(BlockPos blockPos) {
        Object object;
        Iterable iterable = INSTANCE.getPositionsAround(blockPos, 4, 4);
        Iterator iterator2 = iterable;
        Object object2 = new ArrayList();
        Iterator<Object> iterator32 = iterator2.iterator();
        while (iterator32.hasNext()) {
            Object t = iterator32.next();
            BlockPos blockPos2 = (BlockPos)t;
            if (!(blockPos2.toCenterPos().distanceTo(blockPos.toCenterPos()) > 2.0 && Intrinsics.areEqual(Globals.getWorld().getBlockState(blockPos2).getBlock(), Blocks.COBBLESTONE))) continue;
            object2.add(t);
        }
        iterable = (List)object2;
        iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            object = null;
        } else {
            object2 = iterator2.next();
            if (!iterator2.hasNext()) {
                object = object2;
            } else {
                BlockPos blockPos3 = (BlockPos)object2;
                double d = blockPos3.toCenterPos().distanceTo(blockPos.toCenterPos());
                do {
                    BlockPos blockPos4;
                    double d2;
                    if (Double.compare(d, d2 = (blockPos4 = (BlockPos)((Object)(iterator32 = iterator2.next()))).toCenterPos().distanceTo(blockPos.toCenterPos())) <= 0) continue;
                    object2 = iterator32;
                    d = d2;
                } while (iterator2.hasNext());
                object = object2;
            }
        }
        BlockPos blockPos5 = (BlockPos)object;
        if (blockPos5 != null) {
            int n;
            Iterable iterable2 = INSTANCE.getPositionsAround(blockPos5, 1, 0);
            if (iterable2 instanceof Collection && ((Collection)iterable2).isEmpty()) {
                n = 0;
            } else {
                int n2 = 0;
                for (Iterator<Object> iterator32 : iterable2) {
                    BlockPos blockPos6 = (BlockPos)((Object)iterator32);
                    if (!Intrinsics.areEqual(Globals.getWorld().getBlockState(blockPos6).getBlock(), Blocks.ANDESITE) || ++n2 >= 0) continue;
                    CollectionsKt.throwCountOverflow();
                }
                n = n2;
            }
            int n3 = n;
            int n4 = n3 == 8 ? 60000 : 20000;
            Vec3d vec3d = blockPos5.toCenterPos().add(0.0, n3 == 8 ? -0.5 : 0.0, 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            long l = Globals.now() + (long)n4 - 250L;
            Item item = Items.DRIED_KELP;
            Intrinsics.checkNotNullExpressionValue(item, "DRIED_KELP");
            itemMarkers.add(new ExpiringItemMarker(vec3d, l, item));
        }
    }

    private static final boolean onRender2D$lambda$0(ExpiringItemMarker expiringItemMarker) {
        Intrinsics.checkNotNullParameter(expiringItemMarker, "it");
        return Globals.now() - expiringItemMarker.getExpiresOn() >= 0L;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean removeDeadEntity(int n) {
        Entity entity = Globals.getWorld().getEntityById(n);
        if (!(entity != null && entity instanceof PlayerEntity && ((PlayerEntity)entity).isAlive() && Globals.getWorld().isPosLoaded(((PlayerEntity)entity).getBlockPos()))) {
            Globals.getWorld().removeEntity(n, Entity.RemovalReason.KILLED);
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            ServerHelper.b = ZkmKeys.a(4609668170555452464L, -3513194470429409577L, MethodHandles.lookup().lookupClass()).a(169900073998871L);
                            var25 = ServerHelper.b ^ 120281729835595L;
                            ServerHelper.f = new HashMap<K, V>(13);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[37];
                            var21_4 = 0;
                            var20_5 = "(\u00a6\u001c\u0086\u00c0\u00fc\u00a5\u008d\u00a8%\b\u008e\u00e8\u00b2em\u00bd\u0095T\u00fc\u0084F\u00985\u00c3\t'\u00b3\u0084[H_\u00be\u0015$\u00a8\u00e4~\u00fb\u001aU\u00b8\u007f\u00e3`\u00d1i\u00c508\u0095\u00d8\u00dd\u00e5\u00e6`fR\r\u00b3\u00cb\u00a1\u00f06\u00ac \u00a6B\u00ab\u00cf\u00b8q\u00ea\u008f\u00ab\u0007Y\u00d8se\u00e6@7\u000b(\u0086\u00e19\u0011\u008d\u00fe\u0007\u0098\u008a\u00e9*\u00d5\u0018zOA/\u0082\u00f5%\u00fe\u00ab\u008b\"\b\u00f1\u00f3n\u00b7\u001b8\f5\u00ae\u0082\u0097\u00a00\u00b8u\u0086&]n!K\u00c6\u00e8\u00c5\u009e\u0012IF\u0010\\v2\u008b\u0087u\u0099.]\u00e5\u00b4\t\u00deL\u008e)\u00d3\u0011so\u000eD\u00df\u0091\u00e11ey1n\u00d9\u00b6 pU;\u00fc/\u008b\u00f3;\u0087\u00bc\u00e8\u00e5\u00bc\u0081\u0006\u008c//\u0011\u00a5\u008bDx\u0088\u0017^\u00eb\u000ec\u00a4\u0082\u007f\u0018\u008e\u00ff\u00e5\u00a3eS\t4\u00935\u00cd\u00b3\u008c\t\u00bf\u0012N(n\u00d6Y\u00a4}y \u00c7W%+\u00a2\u00d9\u00bc-r\u0099\u00af1\u00f0=\u00aekB{`\u00b2,\u0013\u00b2S\u00cc\u00c6\u00c9\u00d1YC\u00bf}\u00184\u00cc)o.<P\u0004\u008e\u0012\u000f\u009eHX\u00b0\u00a7\u00e48\u00ce\u00aa\u00c3\u0013\u0090p s9^Z@Zy|\u009f;R\u000e\u0081\u0011S\u00c6t\u0095\u0015\u00b0\u0080\u001f\u0012\u00d1\u00cb\u00fa\u008f\ts\u00e8#\u0084\u0010\u00ff/\u001ef\u00f0p,\u00e6\u00f5I^\u00d7\u000e\u00d3\u00c0Y \u00cb\u00b8v\u00c5g3\u00c49\u00d9\u00bcp!u8\u00b3\u008c\u00d7\u00cf\u00b5\u0094h6\u00c6R\u00b1\u00f2~ZR@h\u00dd \u00e1\u0086\u00ab!\u00ac\u00fbw\u0081\u00ba/j\u00c7\u0019\u00ef\u00bfaLE\u00f1v%]\u00d3\u00c1\u00f5/FH\u0000\u009b\u00e3\u00fc \u0004T\u008f|\u0011\u00f4\u00aa\u00e9Y1\u00ef>\u00fd\u00b7\u0007\u009d)\u009bt\u00fd\u00b2\u00af\u0087\u0003\u00c3q\u0007(\u0096L\u0087\u00f0 V\u0015~\u00f8\u00f4\u001d2\u00a8\u00ceE\u00bel\u0097N8\u00b6\n\u0084\t\u00067\u00ec\u00b4\u00b6\u00ea\u00c5\u0019\u0093\u009awuU\u0010\u00dcD\u00ca\u00aa\u00f1T\u0098\u0016\u0000\u0095\u00a4a\u00a8\u00f1~N E-\u000b\u00c6\u00b2i\u0001\u00ee\u00c6\u00f0\\o\u00fdg\u00e5\u00d6\u00fc\u00c0:][G7\u00cd\u00a9\u0007pw\u00cb\u00ea\u00c7\u009d \u00cac\u00a6\u00dc\u000b\u00f1\u007f\u0087{\u0019d\\nE\u009e\u00eb\u0088\u009c\u00cb<\u0005\u00ef\u00ac\u00d5[\u00f0G1\u00bfiG\u00b9\u0010\u00a8\u00d7\u00d67=\u00c3\u00b0\u00cc\u0097wi\u00c2\u00c2\u00d0\u007f\u009d\u0018\u00a8\u0006\u00de\u007f\u00b1\u00d0\u009dx\u0093\u00a0Z!S\u00d5\u00c6wf\u00cd\f\u0099\u0002\u0099\u00bd1\u0080\u0003\u00ee\u00a0.w\u009d\u009bS^F\u008bqp\u0099\u00c1\u00f8`Epi\u00a11\u0080);5\u00e1p\u0003t\u00e0\u00ef\u00f2\u00eb\u00b4\u0094\u00134\u0081\u0002S+>\u001a\u00b5\u008b\u00e1\u00fb\u00f2x\u00bd\u00a3<\u009cp\u00bc\u00c3\u00d1\u00ed\u0004d\u00db`_\"W\u0083\u009e0\u00d1H\u00c1T\u00caD;+c|\u00c1\u00ab5i\u00ba@d\u00b6\u00e3\u0003,\u00f27u2\u00cd\u001f\u0089\u00f3\u00bc\u0088hW\u00ca\u00b52\u00ab\u00d11ah\u0080\u00daq\u0090\u00ff\u00a4\u00f5\u00e2\u00d8\u0002\u00d8O\r\u0088c\u001c3M\u0018\u00b2\u0016\u008c\u00de\u0012Kc\u000f\u00a7\u001bJJ\u008e\u001c\u00f6\u0092\u00a4]\u0011\u008fA\"\u0005\u00f6 }\u00f5\u00eag\u00f5\u001d6\u0093x)RCZ\u0000b\u00e6\u00ec\u00cd\u00b2\u00f5\u00a2d+>\nK\u00dd\u00f4\u00c0\u00f7\u00fb{\u0018\u00bce\u00f4UQP\u0083\u00ba\u00a1\u00cd\u0085\u0015e\u00b8V\u00ce\u00d6\u009cN\u00dd|i\u001e\u00c3\u0010%_\f$\u009c(\u00be\u00c6\u0019D\u00d0@\u00acD\u00c7{\u0018\u0090\u00deH\u0083\u00dbi\u00ba\u0091\u00e03\u00c1\u00e3\u00de\u00dey:\u0087\u00e8\u00cd\u00f3\u009c\u00e2\u00e6\u00ad \u000e4\t}&\u001cTZ\u00fb\u00b4-_\u00f0\u001c\u00fdL\u00c68\u00e6\u0089\u00f2\u00f1\u008d80\u00ec4\u0081SFP\u00cc \u00d5>\u00af\u0003`\u00c4\u001cEe\u00f2\u00af\u008b\u001d\u00ba\u00ca\u001a\u0001$z}\u00e3\"\u0088\u00b2\u0081&\u00b6\u00a1\u0093\u0010\u00ec\u00a3 \u008c\u0013\u00d5dl\u00af\u0089\u00aeno\u00a3\u00e9u\u008e\u0093\u00e5\u00b1\u00b06\u00c4+SLIG\u0083\u00c2;\u00ea\u009a\u00b7\u00c2\u0018\u008a5/`\u001d\u00d6`F\u00a8,\u00cdf\u009b\u0004\u008d\u00f9@1z)\u0097D\u00c6\u00ea \f\u00b4WA\u00b1\u00f5.\u0016\u009dQ\u00dfd\u009e\u00f4\u001b\u001e\u00faX\u00f0l\u0097\u00fe\u00aa\u00f3-\u001f\u00fd\u00f2\u00ed\u0011=\u0015 \u00ba$\u00eb\u000f\u0095\u00d6$\u009d)\flD\u00ff:\u00b5 '\u001f\u0001\u00fb\u0017B\u0096\u00ee\u00ca\u00b9\u00cd\u0080A\u00cbFn D\u00d6B\u000e&DU\u0091\u00e3\u00ac:\u008e\u00f9\u00ad\u00b6\u00c7\u0018\u00b7\u00aa\u0087gB\u00f5\u00bd\u00d2\u001c9)\u00af\u0086 \u00d2\u00b8\u00a3H\u00ce\u00eeO\u001a<\u00a0\u00cb~aK:\\<\u00da\u0012\u00bd\u00aa-\u001c\u00b1r\u00b4\u0096\u00d4O\u00d9\u0001\u000f\u00e5\u00b3\u00d4\u008a\u0094!\u00a0\u0082/\u0003\u00c9%i8\u001f\n\u0006j\u00ea&\u0084\u00a2Q\u0006\u000fX\u0002\u00eci\u0082\u00fd\u00f2g\u00b9\u00fd\u009a?\u00ce-\u00d7\u008a\u00bd\u0015\u00c4\u00cc\u00f8\\\u0092]\u00c2\u001c\u00acy\u00d1\u00ce\u00b5\u001b\u008a\u0085wf1\u008a+$Cy\u0014M2E\u00bdO\u0094?\t\u00dbY\u00c4\u00e6\u00a1\u00ca\u00e7\u0085\u00ae\u0015\u00056\u0000\u00e4?\u0096\u0001[#d\u00d1\u00a1I\u00fa\u0003q\u00cck\u000b:\u00c1@\u008f\u0097\u00df{^\u00970\u0084\u0007\u00bd\u0010xd\u0010\":Z\t\u00033\u0001\u00c4\u007fV\u00e6}>\u000e^\n\u0016\u009c\u00164\u00c1>\u00a6\u00f3u\u001dO3\u00a3\u00e8D\u00d7 \u00d3\u008c\u0089h\u0014\u00dd\u00d5\u00f2\u00dbz\u00ec\u009bf\u00b2\u00fc\u008bZ\u008f\u00f3\u00fax \u00bf\u00f5\u0001!|\u00a3]\u0099\u009d- b2e\u00fc\u00b7\u00e9\u00c7\u0005\u00f19\u00ac\u00ef\u00bcWd\u0094\u00e5h]\u0004\u0082\u00b3VG\u00e97\u009c9_\u0018&\u001d";
                            var22_6 = 1322;
                            var19_7 = 48;
                            var18_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = ServerHelper.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u00ea\u00d8%{\u00c5\u0090\u00b0\u00060\u001eL\u00a5\u00b1\u00bdE\u0005\u00b5J\u00e77\u00a69\u00b4d-\u00f1j\u0085[\u00cf\u00ae\u00a6\\\u00ef\u008d\u00be\u00da/\u008dR\u0018(\u0019br\u00e3\u00e7\u00bf\u0017f\u00bb\u0016\"\u00e0\u0085?/\u00ffZ\u009f\u00c1G\u00bf\u00ecA";
                                var22_6 = 65;
                                var19_7 = 40;
                                var18_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = ServerHelper.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    ServerHelper.d = var23_3;
                    ServerHelper.e = new String[37];
                    ServerHelper.i = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[23];
                    var8_13 = 0;
                    var9_14 = "\u00e7$\u00d1\u00ff\u009e\u0007p\u0092\u00a0\u00bbf8.\u0095\u00ad\u0080\u00ca\u0081\u00cdc\u0091d\u009fDo\u00c8\u0019\u00b1\u00b7}`\u00a2\u00ce\u00be\u009d4\u00bf\u00ea\u00d3(\u00e2\u0093k\u0003\u0092\u0011\u00b1D\u00c6\u00d2\u00cd\u001c\u00cc\u0099Rk\u00a1\u001b\u00e7\u0098YR'1\u00cf\u009dE\u00c5\u009b\u0014Y\u00fb?\u008f\u0010\u00c5N\u0017-\u00a4\u00ca`}\u00818\u00ea\u0094p5\u00c7:\u0097\u0018\n\u00ccJHH\u00f5\\-\u00c7\u00eeF\u001a\u00e6\u0088fo\u00b4,\u00c7\n\u00f2\u00c8s\u00a3\u00ceF\u00d7\u00b3\u00de\u000f\b\u00bc\u00e3\u0086(\u00f9\u001d\u00d8\u00f7\n\u0091y\u000f\u0091z\u00af\u00f9\u00b7\u00baV \u0000\u00c2\u009fL\u00b6A\u00bb\u00ce\u001b\u00dc\u0006\u00a0\u00ebF\u0012\u001fq\u0015W\u00c7A\u00c8%\u00ac";
                    var10_15 = 168;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "v\t;d\u00bfD\u0013|1\u00c4\u00d8\u00c6k\u00ed\u0094\u00ec";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
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
            ServerHelper.g = var11_12;
            ServerHelper.h = new Integer[23];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{3, 36, 96, -120, -63, 98, -25, 81});
        ** while (true)
        ServerHelper.j = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
        var31_23 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ServerHelper.class, "aucHelper", "getAucHelper()Z", 0)), Reflection.property1(new PropertyReference1Impl(ServerHelper.class, "structureCooldowns", "getStructureCooldowns()Z", 0))};
        ServerHelper.delegatedProperties = var31_23;
        ServerHelper.INSTANCE = new ServerHelper();
        ServerHelper.aucHelper$delegate = SettingFactory.booleanSetting$default((SettingContainer)ServerHelper.INSTANCE, "auc-helper", true, null, null, 12, null);
        ServerHelper.structureCooldowns$delegate = SettingFactory.booleanSetting$default((SettingContainer)ServerHelper.INSTANCE, "structure-cooldowns", true, null, null, 12, null);
        ServerHelper.itemMarkers = new ArrayList<E>();
        ServerHelper.tradeOffers = new ArrayList<E>();
        ServerHelper.intArrayList = new IntArrayList();
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7C78;
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
                throw new RuntimeException("su/catlean/e1", exception);
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
            ServerHelper.e[n2] = ServerHelper.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ServerHelper.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2DD4;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/e1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ServerHelper.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ServerHelper.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ServerHelper.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ServerHelper.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

