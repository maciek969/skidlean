/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 */
package su.catlean.ui.hud;

import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.VertexFormats;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.CrosshairRenderEvent;
import su.catlean.api.event.events.render.FrameBufferEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.ScreenMouseCoordsEvent;
import su.catlean.config.ConfigStore;
import su.catlean.config.SettingEntry;
import su.catlean.config.SettingsConfig;
import su.catlean.gofra.Flow;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.Render2D;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.draw.ImageCommand;
import su.catlean.render.draw.ShapeCommand2;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Setting;
import su.catlean.ui.NotificationType;
import su.catlean.ui.hud.ArmorWidget;
import su.catlean.ui.hud.ChestCounterWidget;
import su.catlean.ui.hud.CooldownsWidget;
import su.catlean.ui.hud.CoordWidget;
import su.catlean.ui.hud.CrosshairWidget;
import su.catlean.ui.hud.CrystalSpeedWidget;
import su.catlean.ui.hud.FlightWidget;
import su.catlean.ui.hud.FpsWidget;
import su.catlean.ui.hud.HudElement;
import su.catlean.ui.hud.InventoryWidget;
import su.catlean.ui.hud.KeybindsWidget;
import su.catlean.ui.hud.MacroWidget;
import su.catlean.ui.hud.MediaPlayerWidget;
import su.catlean.ui.hud.NotificationsWidget;
import su.catlean.ui.hud.PhaseWidget;
import su.catlean.ui.hud.PingWidget;
import su.catlean.ui.hud.PlayerListWidget;
import su.catlean.ui.hud.PotionsWidget;
import su.catlean.ui.hud.PvpResourcesWidget;
import su.catlean.ui.hud.SpeedWidget;
import su.catlean.ui.hud.StaffWidget;
import su.catlean.ui.hud.TargetInfoWidget;
import su.catlean.ui.hud.TpsWidget;
import su.catlean.ui.hud.WatermarkWidget;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.zkm.ZkmKeys;

public final class HudManager
implements Manager {
    @NotNull
    public static final HudManager INSTANCE;
    @NotNull
    private static List elements;
    @NotNull
    private static final Render2D render2D;
    @NotNull
    private static List shapeCommands;
    @NotNull
    private static List imageCommands;
    @Nullable
    private static HudElement draggedElement;
    @Nullable
    private static HudElement selectedElement;
    private static float mouseX;
    private static float mouseY;
    public static final float u = 5.0f;
    private static int i;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private HudManager() {
    }

    @NotNull
    public final List getElements() {
        return elements;
    }

    public final void setElements(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)HudManager.zkmBootstrap("a", (int)22839, (long)(0x322F2AE32E0FA8C1L ^ l))));
        elements = list;
    }

    @NotNull
    public final Render2D getRender2D() {
        return render2D;
    }

    @NotNull
    public final List getShapeCommands() {
        return shapeCommands;
    }

    public final void setShapeCommands(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)HudManager.zkmBootstrap("a", (int)25006, (long)(0x71E07F0809747231L ^ l))));
        shapeCommands = list;
    }

    @NotNull
    public final List getImageCommands() {
        return imageCommands;
    }

    public final void setImageCommands(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)HudManager.zkmBootstrap("a", (int)25006, (long)(0x71E0348D1A617B4DL ^ l))));
        imageCommands = list;
    }

    @Nullable
    public final HudElement getDraggedElement() {
        return draggedElement;
    }

    public final void setDraggedElement(@Nullable HudElement hudElement) {
        draggedElement = hudElement;
    }

    @Nullable
    public final HudElement getSelectedElement() {
        return selectedElement;
    }

    public final void setSelectedElement(@Nullable HudElement hudElement) {
        selectedElement = hudElement;
    }

    public final float getMouseX() {
        return mouseX;
    }

    public final void setMouseX(float f) {
        mouseX = f;
    }

    public final float getMouseY() {
        return mouseY;
    }

    public final void setMouseY(float f) {
        mouseY = f;
    }

    @Flow
    private final void onScreenMouseCoords(ScreenMouseCoordsEvent screenMouseCoordsEvent) {
        mouseX = screenMouseCoordsEvent.getX();
        mouseY = screenMouseCoordsEvent.getY();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Iterable iterable = elements;
        for (Object t : iterable) {
            HudElement hudElement = (HudElement)t;
            if (!hudElement.getEnabled()) continue;
            hudElement.tick();
        }
    }

    @Flow(priority=-20)
    private final void onRender2D(Render2DEvent render2DEvent) {
        HudElement hudElement;
        Object t;
        long l;
        long l2 = l = a ^ 0x3DD87CBAD6C4L;
        long l3 = l2 ^ 0x21BC35D94056L;
        int n = (int)(l3 >>> 32);
        int n2 = (int)(l3 << 32 >>> 48);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x2BF9969DD008L;
        long l5 = l2 ^ 0x20D4E8D12269L;
        long l6 = l2 ^ 0x6D6E9A16FE24L;
        int n4 = (int)(l6 >>> 32);
        int n5 = (int)(l6 << 32 >>> 32);
        long l7 = l2 ^ 0x35744FFF6477L;
        selectedElement = null;
        String string = NotificationType.zkmGetFlag();
        Object object = elements;
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            t = iterator2.next();
            hudElement = (HudElement)t;
            if (!hudElement.getEnabled()) continue;
            hudElement.renderWithSnapping(l7, render2DEvent.getContext());
        }
        Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        float f = RenderUtil.INSTANCE.getAlpha(Theme.INSTANCE.getWidgetBlurColor()) * RenderUtil.INSTANCE.t();
        float f2 = RenderUtil.INSTANCE.getAlpha(Theme.INSTANCE.getOutlineColor()) * RenderUtil.INSTANCE.t();
        render2D.drawGradientBackground(matrix3x2fStack, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getWidgetBlurColor(), f), n4, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getOutlineColor(), f2), n5, false);
        if (!((Collection)shapeCommands).isEmpty()) {
            VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n6 = 4;
            boolean bl = false;
            int n7 = shapeCommands.size();
            VertexFormat vertexFormat2 = vertexFormat;
            object = new BatchedBuffer(vertexFormat2, n7, bl, n6, defaultConstructorMarker);
            Color color = new Color(0x44FFFFFF, true);
            shapeCommands.removeIf(arg_0 -> HudManager.invokePredicate(arg_0 -> HudManager.onRender2D$lambda$0((BatchedBuffer)object, color, arg_0), arg_0));
            BatchedBuffer.draw$default(l4, (BatchedBuffer)object, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, null, new Matrix3x2f((Matrix3x2fc)render2DEvent.getContext().getMatrices()), null, 46, null);
        }
        if (!((Collection)imageCommands).isEmpty()) {
            imageCommands.removeIf(arg_0 -> HudManager.invokePredicate2(HudManager::onRender2D$lambda$2, arg_0));
        }
        object = elements;
        iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            t = iterator2.next();
            hudElement = (HudElement)t;
            if (!hudElement.getEnabled()) continue;
            hudElement.renderOverlay(render2DEvent.getContext(), l5);
        }
        Fonts.getSF_M().clearCache(n, (char)n2, (short)n3);
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        long l = a ^ 0x87BE562F024L;
        long l2 = l ^ 0x56E919F17D7EL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 48);
        int n3 = (int)(l2 << 32 >>> 32);
        if (inputEvent.getDevice() == InputEvent.Device.Mouse && Globals.getMc().world != null && Globals.getMc().player != null) {
            Iterable iterable = elements;
            for (Object t : iterable) {
                HudElement hudElement = (HudElement)t;
                if (!hudElement.getEnabled()) continue;
                hudElement.onMouseButton((char)n, (short)n2, inputEvent.getKey(), n3, inputEvent.getAction());
            }
        }
    }

    @Flow
    private final void onFrameBuffer(FrameBufferEvent frameBufferEvent) {
        block0: {
            if (!InventoryWidget.b.getEnabled() || !InventoryWidget.b.a()) break block0;
            frameBufferEvent.setFrameBuffer(InventoryWidget.b.getWindowFramebuffer());
            frameBufferEvent.cancel();
        }
    }

    @Flow
    private final void onCrosshairRender(CrosshairRenderEvent crosshairRenderEvent) {
        block0: {
            if (!CrosshairWidget.F.getEnabled()) break block0;
            crosshairRenderEvent.cancel();
        }
    }

    private final void registerWidgets() {
        HudElement[] hudElementArray = new HudElement[]{CoordWidget.I, FpsWidget.i, KeybindsWidget.J, PingWidget.z, PotionsWidget.H, StaffWidget.k, TargetInfoWidget.Y, TpsWidget.f, WatermarkWidget.m, NotificationsWidget.n, SpeedWidget.b, InventoryWidget.b, CrystalSpeedWidget.N, MediaPlayerWidget.S, ArmorWidget.E, ChestCounterWidget.N, PvpResourcesWidget.N, PlayerListWidget.o, CrosshairWidget.F, FlightWidget.x, PhaseWidget.S, CooldownsWidget.N, MacroWidget.G};
        elements.addAll((Collection)CollectionsKt.listOf(hudElementArray));
    }

    public final void updateAll() {
        Iterable iterable = elements;
        for (Object t : iterable) {
            HudElement hudElement = (HudElement)t;
            Iterable iterable2 = hudElement.getSettings();
            for (Object t2 : iterable2) {
                SettingsConfig settingsConfig;
                Setting setting2;
                block7: {
                    SettingsConfig settingsConfig2;
                    Object v0;
                    SettingsConfig settingsConfig3;
                    Object object2;
                    block6: {
                        setting2 = (Setting)t2;
                        Iterable iterable3 = ConfigStore.INSTANCE.getHudConfigFile().getCurrent().getWidgets();
                        for (Object object2 : iterable3) {
                            settingsConfig3 = (SettingsConfig)object2;
                            if (!Intrinsics.areEqual(settingsConfig3.getId(), hudElement.getId())) continue;
                            v0 = object2;
                            break block6;
                        }
                        v0 = null;
                    }
                    if ((SettingsConfig)v0 == null) continue;
                    Iterable iterable4 = settingsConfig2.getSettings();
                    object2 = iterable4.iterator();
                    while (object2.hasNext()) {
                        settingsConfig3 = object2.next();
                        SettingEntry settingEntry = (SettingEntry)((Object)settingsConfig3);
                        if (!Intrinsics.areEqual(settingEntry.getId(), setting2.getId())) continue;
                        settingsConfig = settingsConfig3;
                        break block7;
                    }
                    settingsConfig = null;
                }
                if ((SettingEntry)((Object)settingsConfig) == null) continue;
                try {
                    SettingEntry settingEntry;
                    setting2.loadFromString(settingEntry.getValue());
                }
                catch (Exception exception) {
                    setting2.reset();
                    String string = exception.getMessage();
                    String string2 = hudElement.A();
                    String string3 = setting2.Q();
                    Globals.getLogger().warn("Could not parse setting: " + string3 + " in " + string2 + ": " + string);
                }
            }
        }
    }

    private static final boolean onRender2D$lambda$0(BatchedBuffer batchedBuffer, Color color, ShapeCommand2 shapeCommand2) {
        Intrinsics.checkNotNullParameter(shapeCommand2, "it");
        batchedBuffer.vertex(shapeCommand2.getM(), shapeCommand2.getX(), shapeCommand2.getY() + shapeCommand2.getH(), 0.0f).color(color).vertex(shapeCommand2.getM(), shapeCommand2.getX() + shapeCommand2.getW(), shapeCommand2.getY() + shapeCommand2.getH(), 0.0f).color(color).vertex(shapeCommand2.getM(), shapeCommand2.getX() + shapeCommand2.getW(), shapeCommand2.getY(), 0.0f).color(color).vertex(shapeCommand2.getM(), shapeCommand2.getX(), shapeCommand2.getY(), 0.0f).color(color);
        return true;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean onRender2D$lambda$2(ImageCommand imageCommand) {
        long l = a ^ 0x73D3361BFB82L;
        long l2 = l ^ 0x5494EE806BEDL;
        Intrinsics.checkNotNullParameter(imageCommand, "it");
        RenderTarget.drawRoundedText$default(imageCommand.getImage(), imageCommand.getM(), imageCommand.getX(), imageCommand.getY(), imageCommand.getW(), imageCommand.getH(), null, null, imageCommand.m(), 96, l2, null);
        return true;
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
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
                        HudManager.a = ZkmKeys.a(2968210992011873301L, 703918193530156074L, MethodHandles.lookup().lookupClass()).a(213037719741682L);
                        var20 = HudManager.a ^ 119902178546712L;
                        HudManager.e = new HashMap<K, V>(13);
                        HudManager.zkmSetFlag(0);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "B\u00d0^\u0012f\u0080\u00b2\u00d1@O\u00bd\u0019\u00a4\u00b54L\u0010U8,_o;\u00bd\u00ce\u00bcb\u000f5\u00fb\u00bd\r\u00c1\u0010\u00d8^2\u0089\\\u00ed\u00bbDq\f\u0013\u001eW\u0006\u00de\u00a8\u0010\u0081\u00e3X\u0000\u001dIO\u00dax\\\u001d\u00f3\u00d1\u00a3\u00eb\u00d6\u0010\u00e9\u000b\u0098\u00ba\u00cdd\u00a4Xd\u00d2\u0010Az\u00f4\u009aO\u0018UB\u00e7]\u0001\u0018\u00ad\u00fb\u009a-\u0019+7\u00a0\u001f\u0095\u0083\u00eb\u00e5\u00ad\u00d4`C\u00890\u00e3\u0095+i\u00ce\u00af\u008a\u00aa\u001b\u0093\u008f_\u0099\u00ecX\u0018\u00c5*KRp\u00dabi?{K\u001e\u009b\u00df\u00e0\u0085\u0086\u0019\u0002QF\u0014\u00f0zlru\u00ec\u00a5\u0007d]";
                        var17_6 = 158;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = HudManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a5\t\u00db\u0011\u0019\u0092\u00bc\u0093\u0083\u00a6j\u001c\u00db(\u008c\u0011\u0018\u00a8+\u00f2\u00af\u00ab\u00a5@E\u00a1Jn\u00e0u\u0016\u0081Q\u0097W\u0006F\u0087\u00dc)\u00b1";
                            var17_6 = 41;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = HudManager.zkmDecodeUtf8(var19_9).intern();
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
                HudManager.b = var18_3;
                HudManager.d = new String[9];
                HudManager.h = new HashMap<K, V>(13);
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
                var6_12 = new long[21];
                var3_13 = 0;
                var4_14 = "\u008d!\\\u00c8\u0088k_\u008a}\u00f5T9j\u00d98\u00b8\u0002X\u00d6\u0081\u00bd\u0084\u00c0\u00e8\u00ce\u008a\u00b9%'\u0014\u00e6\u00a61n:\u00be\u0012\u0019\u001du\u0092\u00f88\u00ba\u00e7&\u00e3\u00ceb\u000ec\u0002\u00cei\u00bbD\u00a1\u00a9(\u0088\u0010\u00db\t\u00ff'$\u00f8[\u009a{\u009c\u0013)\u00dc\u009d},\u00c8[\u00d6=<\u007f\u0010<\u009c\u009e\u00fd\b\u00c3\u0095\u00ae \u00cb:\u00c6\u0002\u00f6\u00b9\u00aeD\u00d3\nE\u001b\u0018\u000e\u0084G\u00d9*\u00b1\u0091\u0012mO\u00ec\u001c\u009aw\u00f5\u00d3\u00a8\u0085\u00f2E\u00cf\u00e9!kV\u0099\u0001\u0015?\u00b1\u00d2\u0017\u0001\u00adH\u00a7H\bi#\u00c2>\u0000\u008aY9";
                var5_15 = 152;
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
                    var4_14 = "\u0010q\u0001/\u00bd\u008b|VF\u00ec\u00e6\u00dc\u00f7\u00c7$\u0019";
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
        HudManager.f = var6_12;
        HudManager.g = new Integer[21];
        HudManager.INSTANCE = new HudManager();
        HudManager.elements = new ArrayList<E>();
        HudManager.render2D = new Render2D();
        HudManager.shapeCommands = new ArrayList<E>();
        HudManager.imageCommands = new ArrayList<E>();
        HudManager.INSTANCE.registerWidgets();
        HudManager.INSTANCE.updateAll();
    }

    public static void zkmSetFlag(int n) {
        i = n;
    }

    public static int zkmGetFlag() {
        return i;
    }

    public static int zkmIntConstant() {
        return 46;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3A06;
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
                throw new RuntimeException("su/catlean/w8", exception);
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
            HudManager.d[n2] = HudManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/w8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4CD0;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/w8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HudManager.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HudManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/w8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HudManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

