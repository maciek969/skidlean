/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.lwjgl.glfw.GLFW
 */
package su.catlean.gui.setting;

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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.lwjgl.glfw.GLFW;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.ModuleButton;
import su.catlean.gui.SettingElement;
import su.catlean.gui.SettingOption;
import su.catlean.gui.setting.GroupElement$1;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Setting;
import su.catlean.setting.SettingParent;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class GroupElement
extends SettingElement {
    @NotNull
    private final Setting setting;
    @NotNull
    private final ModuleButton module;
    @NotNull
    private final Animation animation;
    @NotNull
    private final RenderTarget renderTarget;
    @NotNull
    private List J;
    private float y;
    private static final long b;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] h;
    private static final Integer[] k;
    private static final Map l;
    private static final long m;

    public GroupElement(@NotNull Setting setting2, @NotNull ModuleButton moduleButton, long l) {
        long l2 = (l = b ^ l) ^ 0x5AAEA5C617D3L;
        Intrinsics.checkNotNullParameter(setting2, "setting");
        Intrinsics.checkNotNullParameter(moduleButton, "module");
        super(setting2);
        this.setting = setting2;
        this.module = moduleButton;
        this.animation = new Animation(Easing.OUT_QUINT, m);
        this.renderTarget = new RenderTarget("extra", 10, 10);
        this.J = new ArrayList();
        if (((SettingParent)this.getSetting().getValue()).getType() == AuraTargetsMode.BOOLS) {
            Object object;
            float f = 2.0f;
            Iterable iterable = this.module.getModule().getSettings();
            Iterable iterable2 = iterable;
            Collection collection2 = new ArrayList();
            Iterator iterator2 = iterable2.iterator();
            while (iterator2.hasNext()) {
                object = iterator2.next();
                Setting setting3 = (Setting)object;
                if (!Intrinsics.areEqual(setting3.getGroup(), this.getSetting().getValue())) continue;
                collection2.add(object);
            }
            List list = (List)collection2;
            iterable = list;
            iterable = CollectionsKt.sortedWith(iterable, new GroupElement$1());
            for (Collection collection2 : iterable) {
                Setting setting4 = (Setting)((Object)collection2);
                object = setting4.Q();
                float f2 = Fonts.getSF_M4().getWidth(l2, (String)object) + 6.0f;
                if (f + f2 > this.getWidth() - 10.0f) {
                    this.y += 12.0f;
                    f = 2.0f;
                }
                Intrinsics.checkNotNull(setting4, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.Boolean>");
                this.J.add(new SettingOption(f, this.y, f2, setting4, null, null));
                f += f2 + 2.0f;
            }
        }
    }

    @Override
    @NotNull
    public Setting getSetting() {
        return this.setting;
    }

    @NotNull
    public final ModuleButton getModule() {
        return this.module;
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, long l, float f) {
        long l2 = l;
        long l3 = l2 ^ 0x12EA342FFCD3L;
        int n3 = (int)(l3 >>> 48);
        int n4 = (int)(l3 << 16 >>> 48);
        int n5 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x68AE1B4E0484L;
        long l5 = l2 ^ 0x6DC28CF79CA6L;
        long l6 = l2 ^ 0x162A12CB9DC4L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        if (((SettingParent)this.getSetting().getValue()).getType() == AuraTargetsMode.BOOLS) {
            FontRenderer fontRenderer = Fonts.getSF_M3();
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, this.getSetting().Q(), this.getX(), this.getY() - 1.0f, l4, Theme.INSTANCE.getTextColor(), false, null);
            this.setHeight(this.y + 26.0f);
            Iterable iterable = this.J;
            for (Object t : iterable) {
                SettingOption settingOption = (SettingOption)t;
                settingOption.getAnimation().animate((Boolean)settingOption.getSetting().getValue() != false ? 1.0f : 0.0f);
                Color color = RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), settingOption.getAnimation().getValue()).brighter();
                Color color2 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveModuleOutlineColor(), Theme.INSTANCE.getModuleOutlineColor(), settingOption.getAnimation().getValue()).brighter();
                Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                float f2 = this.getY() + settingOption.getY() + 13.0f;
                float f3 = settingOption.getW();
                Intrinsics.checkNotNull(color);
                Intrinsics.checkNotNull(color2);
                Shapes2D.drawShape$default(matrix3x2fStack2, (char)n3, this.getX() + settingOption.getX(), f2, f3, 10.0f, 2.0f, 2.5f, 0.8f, 1.0f, color, color2, 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
                Fonts.getSF_M4().drawStringCentered(drawContext, settingOption.getSetting().Q(), this.getX() + settingOption.getX() + settingOption.getW() / 2.0f - 1.0f, this.getY() - 1.0f + settingOption.getY() + 18.0f, Theme.INSTANCE.getTextColor(), l5);
                float f4 = this.getX() + settingOption.getX() + 1.0f;
                float f5 = this.getX() + settingOption.getX() + 1.0f + settingOption.getW();
                float f6 = n;
                boolean bl = f4 <= f6 ? f6 <= f5 : false;
                if (!bl) continue;
                f4 = this.getY() - 1.0f + settingOption.getY() + 14.0f;
                f5 = this.getY() + settingOption.getY() + 23.0f;
                f6 = n2;
                boolean bl2 = f4 <= f6 ? f6 <= f5 : false;
                if (!bl2 || GLFW.glfwGetPlatform() == 393219) continue;
                GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)221188));
                ClickGuiScreen.INSTANCE.A(false);
            }
        } else {
            this.animation.animate(((SettingParent)this.getSetting().getValue()).isExtended() ? 1.0f : 0.0f);
            float f7 = this.module.M((SettingParent)this.getSetting().getValue()) + (((SettingParent)this.getSetting().getValue()).isExtended() ? 1.0f : 0.0f);
            float f8 = this.animation.getValue() * 2.0f;
            this.setHeight(13.0f);
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, this.getX() - f8, this.getY() - 1.0f, this.getWidth() - 7.0f + f8 * 2.0f, f7, 3.0f, 2.5f, this.animation.getValue() / 2.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
            FontRenderer fontRenderer = Fonts.getSF_M3();
            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack3, this.getSetting().Q(), this.getX() + 3.0f, this.getY() - 1.0f, l4, Theme.INSTANCE.getTextColor(), false, null);
            Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
            RenderTarget.drawRoundedText$default(this.renderTarget, (Matrix3x2f)matrix3x2fStack4, (double)(this.getX() + this.getWidth()) - 18.0, (double)this.getY() - 1.0, 0.0f, 0.0f, Theme.INSTANCE.getTextColor(), null, 0.0f, 216, l6, null);
        }
    }

    @Override
    public void mouseClicked(long l, double d, double d2, int n) {
        if (((SettingParent)this.getSetting().getValue()).getType() == AuraTargetsMode.BOOLS) {
            Iterable iterable = this.J;
            for (Object t : iterable) {
                SettingOption settingOption = (SettingOption)t;
                float f = this.getX() + settingOption.getX();
                boolean bl = d <= (double)(this.getX() + settingOption.getX() + settingOption.getW()) ? (double)f <= d : false;
                if (!bl) continue;
                f = this.getY() - 1.0f + settingOption.getY() + 14.0f;
                boolean bl2 = d2 <= (double)(this.getY() + settingOption.getY() + 23.0f) ? (double)f <= d2 : false;
                if (!bl2) continue;
                settingOption.getSetting().setValue((Boolean)settingOption.getSetting().getValue() == false);
            }
        } else {
            float f = this.getX();
            boolean bl = d <= (double)(this.getX() + (this.getWidth() - 7.0f)) ? (double)f <= d : false;
            if (bl) {
                f = this.getY();
                boolean bl3 = d2 <= (double)(this.getY() + 13.0f) ? (double)f <= d2 : false;
                if (bl3) {
                    ((SettingParent)this.getSetting().getValue()).setExtended(!((SettingParent)this.getSetting().getValue()).isExtended());
                }
            }
        }
    }

    @Override
    public void mouseReleased(double d, double d2) {
    }

    @Override
    public void keyPressed(int n, short s, int n2, char c) {
    }

    @Override
    public void charTyped(char c) {
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3) {
        return false;
    }

    @Override
    public void close() {
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
                            GroupElement.b = ZkmKeys.a(-2238804436590267411L, 975171940259192307L, MethodHandles.lookup().lookupClass()).a(262341776574849L);
                            GroupElement.f = new HashMap<K, V>(13);
                            var16 = GroupElement.b ^ 25296162847652L;
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var16 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var16 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[7];
                            var23_4 = 0;
                            var22_5 = "\u00d8\u00ddL\u00ca\u00b8\u00d7\u00c4\u00a8\u00d1aTnZ\u00f2\u00999\u00ee\u00fe\u00e7\u00b0\u0080\u0087\u00cc\u00b7\u0088\u00cdBJ\b\u000e\u00a3\u001e,tQ\b,\u0005\u00c9\u00a3\u00e9l1\u00a4\u00bb\u00bd\u00e3[\u0085\u001c^)-\u00deIB\u00aco9\u0091\u0010\u00d8([\u009f\u0096\u00f6\u00e0#\u008c\u009b\u007f\u00ee\u008a)]\u00b9W5\u0014Zz\u0016\u00f4)\fY\u0094\u00da\u00a4\u00f5F\u00ee\u0090g\u0098!\u009b\u0086\u00d5Q\u00e8S:\u00aa<u\u001c\u00c60\u00d5TM\u009cK\u00da\u001dk\u00aaS\u00f2\"\u00f5\u00f3\u00aa\u00b2t\u00fc\u00f5\u0097\u00a4\u001c\u00c6\"\u0086#\u000b\u00ce\u000f\u008c}\u00f5\u00ad\u00c9\u00cb\u00dc\u00e6\u00e0 \u00e5\u00e4\u00f3\u00f6#\u0095\u00a8\u00d3\u00d9A#p\u0010/7\u00ads\u00a8\u00cb_\u00c5\u008a^dj\u00b9Z\u00ad\u00d0\u00102dN\u00c11\u000b{\u0018\u0015zY\u0012\u00b4\u001c%\u00df\u0018\u0010l\u0086\u00c6\u00c1\u00dcE\u000b\u00aa\u00ac\u009e\u0016\u00f7\u009d\u00f1\u00bc\u0010%\u000e,\u00b3\u00aa\u000e\u0082";
                            var24_6 = 220;
                            var21_7 = 24;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = GroupElement.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00fb\u0001\u00f6\u00c2'\u008fUf=\u00a9)N~\u00c6\u00b4\u00c3\u0010y\u009d\u00f2\u00e1\u00c7pr\u0013\u00d9J\u0003vu\u000b\u0082E";
                                var24_6 = 33;
                                var21_7 = 16;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = GroupElement.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    GroupElement.d = var25_3;
                    GroupElement.e = new String[7];
                    GroupElement.l = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var16 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var16 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[11];
                    var8_13 = 0;
                    var9_14 = "\u00bf\u00ce\u001f\u00da\u00d0\u0090k\u00e3\u00dam\u00fers\u00ac-\u0015z\u0087\u00dc\u001aCFU\u0005y\u00efts\u00ff\u00a7\u00bf\u00bcI\u00df\u0013-\u00c6\u00d8\u00c6\u0080\u00ee#\u001f\u00d6\u001d\u00b7\u0080\f\u00ea\u00ea\u00b9\u00a6\u0093[\u000f\u00e7\u00a7&\u00f2\u0012\u00a3,}\u00d5\u0090\u00a9Y\u00ee\u00eb\u00b8\u0001\u00df";
                    var10_15 = 72;
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
                        var9_14 = "\u00d1P\u00be.\u00b9|\u0088\u0014RV\u0001\u000bz\u00c4\u0006d";
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
            GroupElement.h = var11_12;
            GroupElement.k = new Integer[11];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var16 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var16 << var1_21 * 8 >>> 56);
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
        var4_22 = var0_20.doFinal(new byte[]{35, 42, 1, 110, 22, -79, 110, 64});
        ** while (true)
        GroupElement.m = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1093;
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
                throw new RuntimeException("su/catlean/r0", exception);
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
            GroupElement.e[n2] = GroupElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GroupElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/r0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4FFC;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])GroupElement.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    GroupElement.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/r0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            GroupElement.k[n2] = n3;
        }
        return k[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = GroupElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/r0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GroupElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(GroupElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

