/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import java.awt.Color;
import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigIO;
import su.catlean.config.ConfigStore;
import su.catlean.config.ConfigType;
import su.catlean.gui.ConfigPanel;
import su.catlean.gui.ConfigTypeSelector;
import su.catlean.gui.MenuItem;
import su.catlean.gui.PanelType;
import su.catlean.module.Module;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.sound.Sounds;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PanelState {
    @NotNull
    private PanelType type;
    private float x;
    private float y;
    @NotNull
    private String elementId;
    @NotNull
    private ConfigType configType;
    @NotNull
    private final List r;
    private float e;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public PanelState(@NotNull PanelType panelType, float f, float f2, @NotNull String string, @NotNull ConfigType configType) {
        Intrinsics.checkNotNullParameter((Object)panelType, "type");
        Intrinsics.checkNotNullParameter(string, "elementId");
        Intrinsics.checkNotNullParameter((Object)configType, "configType");
        this.type = panelType;
        this.x = f;
        this.y = f2;
        this.elementId = string;
        this.configType = configType;
        this.r = new ArrayList();
        this.e = 62.0f;
        if (this.type == PanelType.Config) {
            MenuItem[] menuItemArray = new MenuItem[3];
            Object[] objectArray = new Object[]{};
            String string2 = "windows.config.load";
            Function1<String, Unit> function1 = arg_0 -> PanelState._init_$lambda$0(this, arg_0);
            String string3 = I18n.INSTANCE.translate(string2, objectArray);
            menuItemArray[0] = new MenuItem(string3, function1);
            Object[] objectArray2 = new Object[]{};
            String string4 = "windows.config.open-in-explorer";
            Function1<String, Unit> function12 = arg_0 -> PanelState._init_$lambda$1(this, arg_0);
            String string5 = I18n.INSTANCE.translate(string4, objectArray2);
            menuItemArray[1] = new MenuItem(string5, function12);
            Object[] objectArray3 = new Object[]{};
            String string6 = "windows.config.delete";
            Function1<String, Unit> function13 = arg_0 -> PanelState._init_$lambda$2(this, arg_0);
            String string7 = I18n.INSTANCE.translate(string6, objectArray3);
            menuItemArray[2] = new MenuItem(string7, function13);
            v0 = this.r.addAll((Collection)CollectionsKt.listOf(menuItemArray));
        } else {
            Object[] objectArray = new Object[]{};
            String string8 = "windows.config.save";
            Function1<String, Unit> function1 = arg_0 -> PanelState._init_$lambda$3(this, arg_0);
            String string9 = I18n.INSTANCE.translate(string8, objectArray);
            v0 = this.r.add(new MenuItem(string9, function1));
        }
    }

    public PanelState(PanelType panelType, float f, float f2, String string, ConfigType configType, DefaultConstructorMarker defaultConstructorMarker) {
        string = "";
        this(panelType, f, f2, string, configType);
    }

    @NotNull
    public final PanelType getType() {
        return this.type;
    }

    public final void setType(@NotNull PanelType panelType) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter((Object)panelType, (String)((Object)PanelState.zkmBootstrap("s", (int)8271, (long)(0x455066D32321B14AL ^ l))));
        this.type = panelType;
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    @NotNull
    public final ConfigType getConfigType() {
        return this.configType;
    }

    public final void setConfigType(@NotNull ConfigType configType) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter((Object)configType, (String)((Object)PanelState.zkmBootstrap("s", (int)2817, (long)(0x4BB52FEFCB572620L ^ l))));
        this.configType = configType;
    }

    public final float I() {
        return this.e;
    }

    public final void r(float f) {
        this.e = f;
    }

    /*
     * Unable to fully structure code
     */
    public final void render(int var1_1, @NotNull DrawContext var2_2, int var3_3, int var4_4, int var5_5, int var6_6) {
        block11: {
            v0 = var7_7 = ((long)var1_1 << 32 | (long)var5_5 << 48 >>> 32 | (long)var6_6 << 48 >>> 48) ^ PanelState.a;
            v1 = v0 ^ 128836436973985L;
            var9_8 = (int)(v1 >>> 48);
            var10_9 = (int)(v1 << 16 >>> 48);
            var11_10 = (int)(v1 << 32 >>> 32);
            var12_11 = v0 ^ 22680622134638L;
            v2 = ZkmReferences.zkmBootstrap("\u00c5", (long)-459075121510451258L, (long)var7_7);
            Intrinsics.checkNotNullParameter(var2_2, (String)PanelState.zkmBootstrap("s", (int)3476, (long)(5060758891686376996L ^ var7_7)));
            var20_12 = (float)this.r.size() * 14.0f + 2.0f;
            var19_13 = v2;
            v3 = var2_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v3, (String)PanelState.zkmBootstrap("s", (int)26148, (long)(1764329146823171486L ^ var7_7)));
            v4 = Theme.INSTANCE.getWidgetBlurColor().darker();
            Intrinsics.checkNotNullExpressionValue(v4, (String)PanelState.zkmBootstrap("s", (int)29328, (long)(4636493256548141348L ^ var7_7)));
            v5 = Theme.INSTANCE.getOutlineColor().darker();
            Intrinsics.checkNotNullExpressionValue(v5, (String)PanelState.zkmBootstrap("s", (int)31092, (long)(7340533264361931459L ^ var7_7)));
            Shapes2D.drawShape$default(v3, (char)var9_8, this.x, this.y, this.e, var20_12, 4.0f, 1.0f, 0.07f, 1.0f, v4, v5, 0.0f, (short)var10_9, 0.0f, var11_10, 0.0f, (int)PanelState.zkmBootstrap2("t", (int)12222, (long)(3737339171664321194L ^ var7_7)), null);
            var21_14 = 0;
            var22_15 = this.r;
            for (T var25_20 : var22_15) {
                block13: {
                    block12: {
                        var26_21 = (MenuItem)var25_20;
                        var28_23 = this.x;
                        var29_24 = this.x + this.e;
                        var30_25 = var3_3;
                        if (var19_13 == null) break block11;
                        v6 = var28_23 == var30_25 ? 0 : (var28_23 < var30_25 ? -1 : 1);
                        v7 = var19_13;
                        if (var1_1 < 0) ** GOTO lbl35
                        if (v7 == null) ** GOTO lbl34
                        if (v6 <= 0) {
                            v6 = var30_25 == var29_24 ? 0 : (var30_25 < var29_24 ? -1 : 1);
lbl34:
                            // 2 sources

                            v7 = var19_13;
lbl35:
                            // 2 sources

                            if (v7 != null) {
                                v6 = v6 <= 0 ? (float)true : (float)false;
                            }
                        } else {
                            v6 = (float)false;
                        }
                        if (v6 == false) break block12;
                        var28_23 = this.y + 2.0f + (float)(var21_14 * PanelState.zkmBootstrap2("t", (int)25811, (long)(5805098528013291969L ^ var7_7)));
                        var29_24 = this.y + (float)PanelState.zkmBootstrap2("t", (int)17418, (long)(4871858835120992540L ^ var7_7)) + (float)(var21_14 * PanelState.zkmBootstrap2("t", (int)17418, (long)(4871858835120992540L ^ var7_7)));
                        var30_25 = var4_4;
                        v8 = var28_23 == var30_25 ? 0 : (var28_23 < var30_25 ? -1 : 1);
                        v9 = var19_13;
                        if (var5_5 <= 0) ** GOTO lbl50
                        if (v9 == null) ** GOTO lbl49
                        if (v8 <= 0) {
                            v8 = var30_25 == var29_24 ? 0 : (var30_25 < var29_24 ? -1 : 1);
lbl49:
                            // 2 sources

                            v9 = var19_13;
lbl50:
                            // 2 sources

                            if (v9 != null) {
                                v8 = v8 <= 0 ? (float)true : (float)false;
                            }
                        } else {
                            v8 = (float)false;
                        }
                        if (v8 != false) {
                            v10 = var2_2.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v10, (String)PanelState.zkmBootstrap("s", (int)23759, (long)(4960004907670792051L ^ var7_7)));
                            Shapes2D.drawShape$default(v10, (char)var9_8, this.x + 2.0f, this.y + 2.0f + (float)(var21_14 * PanelState.zkmBootstrap2("t", (int)17418, (long)(4871858835120992540L ^ var7_7))), this.e - 4.0f, 12.0f, 2.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var10_9, 0.0f, var11_10, 0.0f, (int)PanelState.zkmBootstrap2("t", (int)20456, (long)(2941981078695492349L ^ var7_7)), null);
                        }
                    }
                    v11 = Fonts.getSF_M();
                    v12 = var26_21.getId();
                    v13 = this.y + 2.0f + (float)(var21_14 * PanelState.zkmBootstrap2("t", (int)17418, (long)(4871858835120992540L ^ var7_7)));
                    v14 = var21_14;
                    v15 = this.r.size() - 1;
                    if (var5_5 <= 0 || var19_13 == null) break block13;
                    if (v14 != v15) ** GOTO lbl-1000
                    v14 = this.r.size();
                    v15 = 1;
                }
                if (v14 > v15) {
                    v16 = new Color((int)PanelState.zkmBootstrap2("t", (int)16377, (long)(6500563135672863465L ^ var7_7)), 0, 0, (int)PanelState.zkmBootstrap2("t", (int)16596, (long)(4405159459818649031L ^ var7_7)));
                } else lbl-1000:
                // 2 sources

                {
                    v16 = Color.WHITE;
                }
                var28_22 = v16;
                Intrinsics.checkNotNull(var28_22);
                var16_17 = var28_22;
                var17_18 = v13;
                var18_19 = this.x + 5.0f;
                v11.drawStringWithShadow(var2_2, v12, var12_11, var18_19, var17_18, var16_17);
                ++var21_14;
                if (var19_13 != null) continue;
            }
            if (var6_6 >= 0) {
                // empty if block
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void getHoveredIndex(int var1_1, float var2_2, float var3_3, short var4_4, short var5_5) {
        block13: {
            var6_6 = ((long)var1_1 << 32 | (long)var4_4 << 48 >>> 32 | (long)var5_5 << 48 >>> 48) ^ PanelState.a;
            var9_7 = 0;
            var8_8 = ZkmReferences.zkmBootstrap("\u00c5", (long)-8651677649297077880L, (long)var6_6);
            var10_9 = this.r;
            for (T var13_11 : var10_9) {
                block15: {
                    block14: {
                        var14_12 = (MenuItem)var13_11;
                        var16_13 = this.x;
                        if (var8_8 == null) break block13;
                        cfr_temp_0 = var2_2 - (this.x + this.e);
                        v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                        v1 = var8_8;
                        if (var5_5 < 0) ** GOTO lbl17
                        if (v1 == null) ** GOTO lbl16
                        if (v0 <= 0) {
                            v0 = var16_13 == var2_2 ? 0 : (var16_13 < var2_2 ? -1 : 1);
lbl16:
                            // 2 sources

                            v1 = var8_8;
lbl17:
                            // 2 sources

                            if (v1 != null) {
                                v0 = v0 <= 0 ? (float)true : (float)false;
                            }
                        } else {
                            v0 = (float)false;
                        }
                        if (v0 == false) break block14;
                        var16_13 = this.y + 2.0f + (float)(var9_7 * PanelState.zkmBootstrap2("t", (int)17418, (long)(4871849520363491154L ^ var6_6)));
                        cfr_temp_1 = var3_3 - (this.y + (float)PanelState.zkmBootstrap2("t", (int)17418, (long)(4871849520363491154L ^ var6_6)) + (float)(var9_7 * PanelState.zkmBootstrap2("t", (int)17418, (long)(4871849520363491154L ^ var6_6))));
                        v2 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                        v3 = var8_8;
                        if (var1_1 < 0) ** GOTO lbl31
                        if (v3 == null) ** GOTO lbl30
                        if (v2 <= 0) {
                            v2 = var16_13 == var3_3 ? 0 : (var16_13 < var3_3 ? -1 : 1);
lbl30:
                            // 2 sources

                            v3 = var8_8;
lbl31:
                            // 2 sources

                            if (v3 != null) {
                                v2 = v2 <= 0 ? 1 : 0;
                            }
                        } else {
                            v2 = 0;
                        }
                        if (var1_1 <= 0) break block15;
                        if (v2 != 0) {
                            var14_12.getAction().invoke(this.elementId);
                        }
                    }
                    v2 = var9_7 = var9_7 + 1;
                }
                if (var8_8 != null) continue;
            }
            if (var4_4 > 0) {
                // empty if block
            }
        }
    }

    private static final Unit _init_$lambda$0(PanelState panelState, String string) {
        long l = a ^ 0x690E2909D7F0L;
        long l2 = l ^ 0x1B474C001700L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(string, "it");
        if (!StringsKt.isBlank(panelState.elementId)) {
            ConfigIO.load(panelState.elementId, panelState.configType, n, (short)n2, n3);
        }
        Sounds.INSTANCE.getConfigLoad().play();
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$1(PanelState panelState, String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        if (!StringsKt.isBlank(panelState.elementId)) {
            if (panelState.configType == ConfigType.MODULES) {
                File file;
                File file2 = file = ConfigStore.INSTANCE.getProfileConfigFile().U();
                Util.OperatingSystem operatingSystem = Util.getOperatingSystem();
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                operatingSystem.open(file);
            } else {
                File file;
                File file3 = file = ConfigStore.INSTANCE.getHudConfigFile().U();
                Util.OperatingSystem operatingSystem = Util.getOperatingSystem();
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                operatingSystem.open(file);
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$2(PanelState panelState, String string) {
        block0: {
            long l = a ^ 0x47470B1D6FA5L;
            long l2 = l ^ 0x21261D4E81F8L;
            int n = (int)(l2 >>> 32);
            int n2 = (int)(l2 << 32 >>> 56);
            int n3 = (int)(l2 << 40 >>> 40);
            Intrinsics.checkNotNullParameter(string, "it");
            if (!(!StringsKt.isBlank(panelState.elementId))) break block0;
            ConfigIO.delete(n, (byte)n2, string, panelState.configType, n3);
            ConfigPanel.INSTANCE.setGuiElement(null);
            ConfigPanel.INSTANCE.refresh();
        }
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$3(PanelState panelState, String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        ConfigPanel.INSTANCE.setPanelState(null);
        ConfigPanel.INSTANCE.setGuiElement(new ConfigTypeSelector(panelState.configType));
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
                        PanelState.a = ZkmKeys.a(6372192607014350181L, -6895057981393438557L, MethodHandles.lookup().lookupClass()).a(221304856914690L);
                        PanelState.d = new HashMap<K, V>(13);
                        var11 = PanelState.a ^ 15286683413929L;
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
                        var20_3 = new String[16];
                        var18_4 = 0;
                        var17_5 = "\u0013\u00de\u00d0U%\u00b4\u00beGuQG \u00b2\u0094\u00b4\u0019 \u00be\u0089\u00bb\u008c\u00d3\u00a9Z\u00ac`\u00a1\u0086\u00dc\u00d6\u009e&\u0096\u00ae#\u00be/|\u00b8\u00dfWT\u00c2\u0004\u0093n#\u00f6\u001f(\b\"\u00db\u008a\u00ad p\u00f7\u00db\u00ad\u001eq\u0005LF\u00a1\u00b3Ea\t\u00fdF4\u0089\u00db\u00b6\u00cce\u00ccX0\u008a\u00faQ\u00eb=\u00c0\u00e0K\u00ac(\u000e\u008b\u0095P\u00b8,\u00dc%\u00bf\u00c6\u0092\u00fa4\u00afW.\u0019\u008d\u00bc~+f\u0098\u000f\u0006E\u00fe\u00b3e\u00fb\u001a\u0080\u00ca\u00baw\"\u00d1fr\u007f8g\u00acZ\u0015\u000b\u0083\u00ec\u00ff\u0092\u008eP\u00bc\u0005\u0015\u000e\u0017/\u00a2\u00fd\u00dd\u00e1\u00ce\u00d8\u0094\u00cf\u00b0\u00ce\u0017i{6\u0006\u008e;\u009ea\u00adBEV\u00c53\u00ed\u00be\u00b3\u00d3<\u00ba?C$Xp)s\u00be I\u00e2o\u00e2\u00f8o)L\u00a9\u00af\u008d\u00e5\u00b1o\u00c6w*TE\u00b5W\u001cJ\u00cd\u00d1E\u0099\u00f2w\u00ebt\u008d(\u00d1\u000e*\u00bfJ\u007f\u000b\u00cc\b@\u000e\u00ad\u0085\u00bc\u00bd\u0012\u008c\u00fb ]\u00e5\u00dc\u00a2O}RU\u009d00'\u001f\u0000\u00a9\u00e4\u001ca\u00e7l\u00bd \u000b\u00c7\u00f7n\u009f?\u0007S\u00afW[\u00f8D+\u00f58\u008dGA\u0099\u00d3\u00974\n\u00f5\u00f2\u00d2o\u00e3e\"\u00a1\u0010\u0007I/\u00ce\u00a9\u00f8\u00cd\t\u00fe\u008b\u00d3{\u00cd\u00ba\u00a7\u00dc +\u00b3\u00ace\u00de\u00e9\u00ef~J\u00d5\u001c\"&H\u00bdP\u00de5gK|\u00af\u00d3\u0018}[\u0015\u00a4\u00dd\u00f3\u0005\u00a6 \u00b19\u000f\u00ef\u00cf\u00d9|V\u00f5\u00b6\u00a9\u00cf\u00d8\u00e7-\u00ed\u0016\u00ba\u00edPA\u00ae\u00cd\u00d5\u009cu\u00a4\u000eN\"\u00fd^\u0010\u008b\u00f7\u00e2\u00adA\u001d\u00c0\u009f\u00b9\u00dd\u00d8w\u00c7\u0017\u00db\u00ff\u0018\u0012\u009b\u00f69\u00a3\\\u0084\u00bc\u00be[|\u00edK\u0096ch\u00b5\u00dc\f\u00f3[\u00d1\u0081\u0088\u0010\n\u00ab\u00dc\u0011\u00e5\u00bb\u00cb\u00ed$\u00caMt\u000f\u00a8\u0096\u00bd";
                        var19_6 = 437;
                        var16_7 = 16;
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
                            var20_3[var18_4++] = PanelState.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "3\"\u008c\u001f\u00ba\t-\u00eeP\u0097\u00f5D\u00f7i\u00be\u00a0\u0010\f\u00e5\u009a\u0002{\u0090\u00b8\u00b1\u0002;\u00dd,Ax*\u0014";
                            var19_6 = 33;
                            var16_7 = 16;
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
                            var20_3[var18_4++] = PanelState.zkmDecodeUtf8(var21_9).intern();
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
                PanelState.b = var20_3;
                PanelState.c = new String[16];
                PanelState.h = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "\u00d2\u0084&\f@6\u00a0\u0092\u00f9@2]@iKJa\u00ecj\u00e3A\u00c1)\u00e0\u00f1K\u00e6\b\u00fc\u007f{E\u00f9~\u0083\u0001\u0018h~\u00d3\u001d9\u00e1p\u0012\n\u00bb\u00b7";
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
                    var4_14 = ")\u0000f\u001b7\u00bf#\u00d6\u008b~#\u0088\u00d4GO\u000e";
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
        PanelState.f = var6_12;
        PanelState.g = new Integer[8];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x43FF;
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
                throw new RuntimeException("su/catlean/nr", exception);
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
            PanelState.c[n2] = PanelState.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PanelState.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xD52;
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
                throw new RuntimeException("su/catlean/nr", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PanelState.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PanelState.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/nr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PanelState.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PanelState.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

