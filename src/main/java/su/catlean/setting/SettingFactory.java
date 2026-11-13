/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ButtonSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.EntityListSetting;
import su.catlean.setting.EntityListValue;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ItemListSetting;
import su.catlean.setting.ItemListValue;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.PositionSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.setting.StringListSetting;
import su.catlean.setting.TextSetting;
import su.catlean.setting.TextValue;
import su.catlean.ui.hud.HudPosition;
import su.catlean.util.ActionHolder;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SettingFactory {
    private static boolean u;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    @NotNull
    public static final BooleanSetting booleanSetting(@NotNull SettingContainer settingContainer, @NotNull String string, boolean bl, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(function0, "visible");
        BooleanSetting booleanSetting = new BooleanSetting(string, bl, settingParent, function0);
        ((Collection)settingContainer.getSettings()).add(booleanSetting);
        return booleanSetting;
    }

    public static /* synthetic */ BooleanSetting booleanSetting$default(SettingContainer settingContainer, String string, boolean bl, SettingParent settingParent, Function0 function0, int n, Object object) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & 8) != 0) {
            function0 = SettingFactory::booleanSetting$default$lambda$0;
        }
        return SettingFactory.booleanSetting(settingContainer, string, bl, settingParent, function0);
    }

    @NotNull
    public static final IntSetting intSetting(@NotNull SettingContainer settingContainer, @NotNull String string, int n, @NotNull IntRange intRange, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(intRange, "range");
        Intrinsics.checkNotNullParameter(function0, "visible");
        IntSetting intSetting = new IntSetting(string, n, intRange, settingParent, function0);
        ((Collection)settingContainer.getSettings()).add(intSetting);
        return intSetting;
    }

    public static /* synthetic */ IntSetting intSetting$default(SettingContainer settingContainer, String string, int n, IntRange intRange, SettingParent settingParent, Function0 function0, int n2, Object object) {
        if ((n2 & 8) != 0) {
            settingParent = null;
        }
        if ((n2 & 0x10) != 0) {
            function0 = SettingFactory::intSetting$default$lambda$0;
        }
        return SettingFactory.intSetting(settingContainer, string, n, intRange, settingParent, function0);
    }

    @NotNull
    public static final FloatSetting floatSetting(@NotNull SettingContainer settingContainer, @NotNull String string, float f, @NotNull ClosedFloatingPointRange closedFloatingPointRange, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "range");
        Intrinsics.checkNotNullParameter(function0, "visible");
        FloatSetting floatSetting = new FloatSetting(string, f, closedFloatingPointRange, settingParent, function0);
        ((Collection)settingContainer.getSettings()).add(floatSetting);
        return floatSetting;
    }

    public static /* synthetic */ FloatSetting floatSetting$default(SettingContainer settingContainer, String string, float f, ClosedFloatingPointRange closedFloatingPointRange, SettingParent settingParent, Function0 function0, int n, Object object) {
        if ((n & 8) != 0) {
            settingParent = null;
        }
        if ((n & 0x10) != 0) {
            function0 = SettingFactory::floatSetting$default$lambda$0;
        }
        return SettingFactory.floatSetting(settingContainer, string, f, closedFloatingPointRange, settingParent, function0);
    }

    @NotNull
    public static final TextSetting textSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull String string2, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(string2, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        TextSetting textSetting = new TextSetting(string, string2, settingParent, function0);
        ((Collection)settingContainer.getSettings()).add(textSetting);
        return textSetting;
    }

    public static /* synthetic */ TextSetting textSetting$default(SettingContainer settingContainer, String string, String string2, SettingParent settingParent, Function0 function0, int n, Object object) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & 8) != 0) {
            function0 = SettingFactory::textSetting$default$lambda$0;
        }
        return SettingFactory.textSetting(settingContainer, string, string2, settingParent, function0);
    }

    @NotNull
    public static final BindSetting bindSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull Bind bind, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(bind, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        BindSetting bindSetting = new BindSetting(string, bind, function0);
        bindSetting.setGroup(settingParent);
        ((Collection)settingContainer.getSettings()).add(bindSetting);
        return bindSetting;
    }

    public static /* synthetic */ BindSetting bindSetting$default(SettingContainer settingContainer, String string, Bind bind, SettingParent settingParent, Function0 function0, int n, Object object) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & SettingFactory.callSite2("m", (int)24694, (long)(0x4AEC67907527445AL ^ l))) != 0) {
            function0 = SettingFactory::bindSetting$default$lambda$0;
        }
        return SettingFactory.bindSetting(settingContainer, string, bind, settingParent, function0);
    }

    @NotNull
    public static final ModeSetting modeSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull Enum enum_, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(enum_, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        ModeSetting modeSetting = new ModeSetting(string, enum_, settingParent, function0);
        ((Collection)settingContainer.getSettings()).add(modeSetting);
        return modeSetting;
    }

    public static /* synthetic */ ModeSetting modeSetting$default(SettingContainer settingContainer, String string, Enum enum_, SettingParent settingParent, Function0 function0, int n, Object object) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & 8) != 0) {
            function0 = SettingFactory::modeSetting$default$lambda$0;
        }
        return SettingFactory.modeSetting(settingContainer, string, enum_, settingParent, function0);
    }

    @NotNull
    public static final PositionSetting positionSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull HudPosition hudPosition, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(hudPosition, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        PositionSetting positionSetting = new PositionSetting(string, hudPosition, settingParent, function0);
        settingContainer.getSettings().add(positionSetting);
        return positionSetting;
    }

    public static /* synthetic */ PositionSetting positionSetting$default(SettingContainer settingContainer, String string, HudPosition hudPosition, SettingParent settingParent, Function0 function0, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & SettingFactory.callSite2("m", (int)24694, (long)(0x4AEC2348F2B65392L ^ l))) != 0) {
            function0 = SettingFactory::positionSetting$default$lambda$0;
        }
        return SettingFactory.positionSetting(settingContainer, string, hudPosition, settingParent, function0);
    }

    @NotNull
    public static final ColorSetting colorSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull Color color, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(color, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        ColorSetting colorSetting = new ColorSetting(string, color, settingParent, function0);
        settingContainer.getSettings().add(colorSetting);
        return colorSetting;
    }

    public static /* synthetic */ ColorSetting colorSetting$default(SettingContainer settingContainer, String string, Color color, SettingParent settingParent, Function0 function0, int n, Object object) {
        int n2 = 0;
        long l = 0L;
        long l2 = ((long)n2 << 48 | l << 16 >>> 16) ^ a;
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & SettingFactory.callSite2("m", (int)24694, (long)(0x4AEC64DA7FBD89EEL ^ l2))) != 0) {
            function0 = SettingFactory::colorSetting$default$lambda$0;
        }
        return SettingFactory.colorSetting(settingContainer, string, color, settingParent, function0);
    }

    @NotNull
    public static final BlockListSetting blockListSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull BlockListValue blockListValue, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(blockListValue, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        BlockListSetting blockListSetting = new BlockListSetting(string, blockListValue, settingParent, function0);
        settingContainer.getSettings().add(blockListSetting);
        return blockListSetting;
    }

    public static /* synthetic */ BlockListSetting blockListSetting(SettingContainer settingContainer, String string, BlockListValue blockListValue, SettingParent settingParent, Function0 function0, Object object) {
        settingParent = null;
        function0 = SettingFactory::blockListSetting$lambda$0;
        return SettingFactory.blockListSetting(settingContainer, string, blockListValue, settingParent, function0);
    }

    @NotNull
    public static final EntityListSetting entityListSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull EntityListValue entityListValue, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(entityListValue, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        EntityListSetting entityListSetting = new EntityListSetting(string, entityListValue, settingParent, function0);
        settingContainer.getSettings().add(entityListSetting);
        return entityListSetting;
    }

    public static /* synthetic */ EntityListSetting entityListSetting(SettingContainer settingContainer, String string, EntityListValue entityListValue, SettingParent settingParent, Function0 function0, Object object) {
        settingParent = null;
        function0 = SettingFactory::entityListSetting$lambda$0;
        return SettingFactory.entityListSetting(settingContainer, string, entityListValue, settingParent, function0);
    }

    @NotNull
    public static final ItemListSetting itemListSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull ItemListValue itemListValue, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(itemListValue, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        ItemListSetting itemListSetting = new ItemListSetting(string, itemListValue, settingParent, function0);
        settingContainer.getSettings().add(itemListSetting);
        return itemListSetting;
    }

    public static /* synthetic */ ItemListSetting itemListSetting$default(SettingContainer settingContainer, String string, ItemListValue itemListValue, SettingParent settingParent, Function0 function0, int n, Object object) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & 8) != 0) {
            function0 = SettingFactory::itemListSetting$default$lambda$0;
        }
        return SettingFactory.itemListSetting(settingContainer, string, itemListValue, settingParent, function0);
    }

    @NotNull
    public static final ButtonSetting buttonSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull Function0 function0, @Nullable SettingParent settingParent, @NotNull Function0 function02) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(function0, "defaultValue");
        Intrinsics.checkNotNullParameter(function02, "visible");
        ButtonSetting buttonSetting = new ButtonSetting(string, new ActionHolder(function0), settingParent, function02);
        settingContainer.getSettings().add(buttonSetting);
        return buttonSetting;
    }

    public static /* synthetic */ ButtonSetting buttonSetting$default(SettingContainer settingContainer, String string, Function0 function0, SettingParent settingParent, Function0 function02, int n, Object object) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & 8) != 0) {
            function02 = SettingFactory::buttonSetting$default$lambda$0;
        }
        return SettingFactory.buttonSetting(settingContainer, string, function0, settingParent, function02);
    }

    @NotNull
    public static final StringListSetting stringListSetting(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull TextValue textValue, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(textValue, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        StringListSetting stringListSetting = new StringListSetting(string, textValue, settingParent, function0);
        settingContainer.getSettings().add(stringListSetting);
        return stringListSetting;
    }

    public static /* synthetic */ StringListSetting stringListSetting$default(SettingContainer settingContainer, String string, TextValue textValue, SettingParent settingParent, Function0 function0, int n, Object object) {
        if ((n & 4) != 0) {
            settingParent = null;
        }
        if ((n & 8) != 0) {
            function0 = SettingFactory::stringListSetting$default$lambda$0;
        }
        return SettingFactory.stringListSetting(settingContainer, string, textValue, settingParent, function0);
    }

    @NotNull
    public static final SettingGroup settingGroup(@NotNull SettingContainer settingContainer, @NotNull String string, @NotNull AuraTargetsMode auraTargetsMode, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(settingContainer, "<this>");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter((Object)auraTargetsMode, "type");
        Intrinsics.checkNotNullParameter(function0, "visible");
        SettingParent settingParent = new SettingParent(false, auraTargetsMode);
        SettingGroup settingGroup = new SettingGroup(string, settingParent, function0);
        settingContainer.getSettings().add(settingGroup);
        return settingGroup;
    }

    public static /* synthetic */ SettingGroup settingGroup$default(SettingContainer settingContainer, String string, AuraTargetsMode auraTargetsMode, Function0 function0, int n, Object object) {
        if ((n & 2) != 0) {
            auraTargetsMode = AuraTargetsMode.DEFAULT;
        }
        if ((n & 4) != 0) {
            function0 = SettingFactory::settingGroup$default$lambda$0;
        }
        return SettingFactory.settingGroup(settingContainer, string, auraTargetsMode, function0);
    }

    @NotNull
    public static final SettingGroup settingGroup(@NotNull Module module, @NotNull String string, @NotNull AuraTargetsMode auraTargetsMode, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(module, "parent");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter((Object)auraTargetsMode, "type");
        Intrinsics.checkNotNullParameter(function0, "visible");
        SettingParent settingParent = new SettingParent(false, auraTargetsMode);
        SettingGroup settingGroup = new SettingGroup(string, settingParent, function0);
        ((Collection)module.getSettings()).add(settingGroup);
        return settingGroup;
    }

    public static /* synthetic */ SettingGroup settingGroup(Module module, String string, AuraTargetsMode auraTargetsMode, Function0 function0, Object object) {
        auraTargetsMode = AuraTargetsMode.DEFAULT;
        return SettingFactory.settingGroup(module, string, auraTargetsMode, function0);
    }

    @NotNull
    public static final FloatSetting floatSetting(@NotNull Module module, @NotNull String string, float f, @NotNull ClosedFloatingPointRange closedFloatingPointRange, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(module, "parent");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "range");
        Intrinsics.checkNotNullParameter(function0, "visible");
        FloatSetting floatSetting = new FloatSetting(string, f, closedFloatingPointRange, settingParent, function0);
        ((Collection)module.getSettings()).add(floatSetting);
        return floatSetting;
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ FloatSetting floatSetting(Module module, String string, float f, ClosedFloatingPointRange closedFloatingPointRange, SettingParent settingParent, Function0 function0, int n, long l, Object object) {
        FloatSetting floatSetting;
        block3: {
            void var12_10;
            l = a ^ l;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2616338169930728824L, (long)l);
            int n2 = n & SettingFactory.callSite2("m", (int)10930, (long)(0x5A7096DCD5FFADC5L ^ l));
            if (callSite != false) {
                if (n2 != 0) {
                    settingParent = null;
                }
                n2 = n & SettingFactory.callSite2("m", (int)25544, (long)(0x3F479ED3007F64BAL ^ l));
            }
            if (n2 != 0) {
                function0 = SettingFactory::floatSetting$lambda$0;
            }
            floatSetting = SettingFactory.floatSetting(module, string, f, closedFloatingPointRange, settingParent, function0);
            if (l <= 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)-2654730403481860679L, (long)l) == null) break block3;
            ZkmReferences.zkmBootstrap("\u00c5", (int)(++var12_10), (long)-2619738758166871938L, (long)l);
        }
        return floatSetting;
    }

    @NotNull
    public static final IntSetting intSetting(@NotNull Module module, @NotNull String string, int n, @NotNull IntRange intRange, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(module, "parent");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(intRange, "range");
        Intrinsics.checkNotNullParameter(function0, "visible");
        IntSetting intSetting = new IntSetting(string, n, intRange, settingParent, function0);
        ((Collection)module.getSettings()).add(intSetting);
        return intSetting;
    }

    public static /* synthetic */ IntSetting intSetting$default(long l, Module module, String string, int n, IntRange intRange, SettingParent settingParent, Function0 function0, int n2, Object object) {
        IntSetting intSetting;
        block4: {
            Object object2;
            block3: {
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2131571484830970839L, (long)l);
                int n3 = n2 & SettingFactory.callSite2("m", (int)10930, (long)(0x5A709630011494A5L ^ l));
                if (callSite == false) {
                    if (n3 != 0) {
                        settingParent = null;
                    }
                    n3 = n2 & SettingFactory.callSite2("m", (int)19312, (long)(0x9EDD32A03907566L ^ l));
                }
                if (n3 != 0) {
                    function0 = SettingFactory::intSetting$default$lambda$02;
                }
                intSetting = SettingFactory.intSetting(module, string, n, intRange, settingParent, function0);
                object2 = callSite;
                if (l < 0L) break block3;
                if (object2 == false) break block4;
                object2 = 3;
            }
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[object2], (long)-2144093216627103741L, (long)l);
        }
        return intSetting;
    }

    @NotNull
    public static final BooleanSetting booleanSetting(@NotNull Module module, @NotNull String string, boolean bl, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(module, "parent");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(function0, "visible");
        BooleanSetting booleanSetting = new BooleanSetting(string, bl, settingParent, function0);
        ((Collection)module.getSettings()).add(booleanSetting);
        return booleanSetting;
    }

    public static /* synthetic */ BooleanSetting booleanSetting$default(Module module, String string, boolean bl, SettingParent settingParent, Function0 function0, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-7126239359965397982L, (long)l);
        int n2 = n & SettingFactory.callSite2("m", (int)15145, (long)(0x6A2B295F04DA7AF7L ^ l));
        if (callSite != false) {
            if (n2 != 0) {
                settingParent = null;
            }
            n2 = n & SettingFactory.callSite2("m", (int)13629, (long)(0x4F4F9A63BA2E74E2L ^ l));
        }
        if (n2 != 0) {
            function0 = SettingFactory::booleanSetting$default$lambda$02;
        }
        return SettingFactory.booleanSetting(module, string, bl, settingParent, function0);
    }

    @NotNull
    public static final ModeSetting modeSetting(@NotNull Module module, @NotNull String string, @NotNull Enum enum_, @Nullable SettingParent settingParent, @NotNull Function0 function0) {
        Intrinsics.checkNotNullParameter(module, "parent");
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(enum_, "defaultValue");
        Intrinsics.checkNotNullParameter(function0, "visible");
        ModeSetting modeSetting = new ModeSetting(string, enum_, settingParent, function0);
        ((Collection)module.getSettings()).add(modeSetting);
        return modeSetting;
    }

    public static /* synthetic */ ModeSetting modeSetting$default(Module module, String string, Enum enum_, SettingParent settingParent, Function0 function0, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-304174221360999035L, (long)l);
        int n2 = n & SettingFactory.callSite2("m", (int)24694, (long)(0x4AEC2B637BF7C7C9L ^ l));
        if (callSite == false) {
            if (n2 != 0) {
                settingParent = null;
            }
            n2 = n & SettingFactory.callSite2("m", (int)10930, (long)(0x5A70EE1F71AA8D09L ^ l));
        }
        if (n2 != 0) {
            function0 = SettingFactory::modeSetting$default$lambda$02;
        }
        return SettingFactory.modeSetting(module, string, enum_, settingParent, function0);
    }

    private static final boolean booleanSetting$default$lambda$0() {
        return true;
    }

    private static final boolean intSetting$default$lambda$0() {
        return true;
    }

    private static final boolean floatSetting$default$lambda$0() {
        return true;
    }

    private static final boolean textSetting$default$lambda$0() {
        return true;
    }

    private static final boolean bindSetting$default$lambda$0() {
        return true;
    }

    private static final boolean modeSetting$default$lambda$0() {
        return true;
    }

    private static final boolean positionSetting$default$lambda$0() {
        return true;
    }

    private static final boolean colorSetting$default$lambda$0() {
        return true;
    }

    private static final boolean blockListSetting$lambda$0() {
        return true;
    }

    private static final boolean entityListSetting$lambda$0() {
        return true;
    }

    private static final boolean itemListSetting$default$lambda$0() {
        return true;
    }

    private static final boolean buttonSetting$default$lambda$0() {
        return true;
    }

    private static final boolean stringListSetting$default$lambda$0() {
        return true;
    }

    private static final boolean settingGroup$default$lambda$0() {
        return true;
    }

    private static final boolean A() {
        return true;
    }

    private static final boolean floatSetting$lambda$0() {
        return true;
    }

    private static final boolean intSetting$default$lambda$02() {
        return true;
    }

    private static final boolean booleanSetting$default$lambda$02() {
        return true;
    }

    private static final boolean modeSetting$default$lambda$02() {
        return true;
    }

    public static void zkmSetFlag(boolean bl) {
        u = bl;
    }

    public static boolean zkmGetFlag() {
        return u;
    }

    public static boolean V() {
        return true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SettingFactory.a = ZkmKeys.a(7745827321901281846L, -8212081676556772172L, MethodHandles.lookup().lookupClass()).a(138592831165454L);
                        var20 = SettingFactory.a ^ 52168955847623L;
                        SettingFactory.d = new HashMap<K, V>(13);
                        SettingFactory.zkmSetFlag(false);
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
                        var18_3 = new String[14];
                        var16_4 = 0;
                        var15_5 = "$b\u00e9\u00d0\u0018\u00be\u0094\r\u00b7\u0012\u0010\u00c7\u00c5\u0089\fB\u00bfB2)\u00cf=\u00f9\u001c\u0010%n\u0004JT\u00f0\u00f5\u0001\u0088\u0098\u00ac\u0016\u0001\u00da\u00d5u\u0010\u00fa\u00c6p\u00fey\u00c8\u0002f\u00a7\u00f9\u00e6Ue\u00b2\u00dc\u0092\u0010\u00a5/Xc\u00a5\u00b5\u00c9\u00f2\u0005\u0005\u00aa2\u0083`2\u0098\u0010\t\u00a1\u0085\u00f9R\u009eB\u001f\u00e6\u00f1\u00cc\u00a1\u0085\u00c6\u001f\u0004\u0010/\u0004\u00d5s=\u000f\u0012\u00ddW\u00d0\b!\u00f3P\u00ed\u00f3\u0018\u00b38\u00b1\u0092\u00ccl\u0018\u0098(\u00c2\u0087\u00bc\u00ebW9\u00fb{uV\u00024\u00df\u00c1\u0003\u0010\u00dd`\u0002=\u00f6\u00c1\u00e3L\u00e4\u00aar\t\u00f0\u00dd>\u00df\u0010\u00d4\u00b6\u00c6\u00af\u0000\u0087VCkT\u0095\u00a3\u00a6=x\u0090\u0010{J\u009f\u009d5y;\u0086\u001d\u001bT\u00d7\u0098\u0092\u0017\u00c9\u0010\u00b4\u001c\u0099\u00d7\u00ef\u00f6\u0005&\u00f6\u0091\u00caU9\u001b\u00a5\u0097\u0010\u0013_\u00c2\u00a4(\u00cb\u00e4\u00a2r\u0088\u0093\u0086s\u00c1\u0010=";
                        var17_6 = 219;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = SettingFactory.string(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c0=8tZ\u009dl#\u0097\u0088(\u00ab\u00f1\u00b8\u00bf*\u0010\u00a6Q\u00afp\u00a5\u008a;\u00f5\u00fbi(\u00a4Z\u00eb\u00a9\u008d";
                            var17_6 = 33;
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
                            var18_3[var16_4++] = SettingFactory.string(var19_9).intern();
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
                SettingFactory.b = var18_3;
                SettingFactory.c = new String[14];
                SettingFactory.g = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u00a6\u00d8^\u000b\u008ej\u00cf(\u00ad\u0098\t\u001e;'\u00a5\u0080\u0090\u00fcO\u00b9\u009eM\u00aa\u00a5O\u00c9o-RR\u00a0\u0013";
                var5_15 = 32;
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
                    var4_14 = "\u00be\u0088\u00baA\u00bakT\u00e9\u009dp\r\u00b8\u00bd\u0005\u00f0\u00f5";
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
        SettingFactory.e = var6_12;
        SettingFactory.f = new Integer[6];
    }

    private static NumberFormatException numberFormatException(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static String string(byte[] byArray) {
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

    private static String string(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x536B;
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
                throw new RuntimeException("su/catlean/yp", exception);
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
            SettingFactory.c[n2] = SettingFactory.string(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object object(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingFactory.string(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite callSite(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/yp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5D0F;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/yp", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SettingFactory.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SettingFactory.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite callSite2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/yp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingFactory.class, "object", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SettingFactory.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

