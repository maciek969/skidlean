/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.gui;

import com.google.common.collect.Maps;
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
import java.util.LinkedHashMap;
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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.config.ConfigStore;
import su.catlean.config.ItemPreset;
import su.catlean.config.ItemPresetConfigFile;
import su.catlean.gui.Component;
import su.catlean.gui.ItemPresetEditor$Companion;
import su.catlean.gui.ItemSelectList;
import su.catlean.gui.ListComponent;
import su.catlean.gui.RectButton;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ItemPresetEditor
extends Component {
    @NotNull
    public static final ItemPresetEditor$Companion Companion;
    @NotNull
    private final Map A = new LinkedHashMap();
    @NotNull
    private final RectButton rectButton = new RectButton(0.0f, 0.0f, 40.0f, 15.0f, "", () -> ItemPresetEditor._init_$lambda$0(this), 0, Theme.INSTANCE.i(), Theme.INSTANCE.getInactiveModuleOutlineColor(), 0.0f, 0.0f, 1600, null);
    @Nullable
    private static Component component;
    private static Module[] u;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map g;
    private static final long[] i;
    private static final Integer[] k;
    private static final Map m;

    public ItemPresetEditor() {
        Module[] moduleArray = new Module[]{};
        this.resetSize();
    }

    private final void loadPreset(String string) {
        Object v0;
        ItemPresetConfigFile itemPresetConfigFile;
        block2: {
            Iterable iterable = ConfigStore.INSTANCE.getItemPresetConfigFile().getData();
            itemPresetConfigFile = ConfigStore.INSTANCE.getItemPresetConfigFile();
            Iterator iterator2 = iterable.iterator();
            Module[] moduleArray = new Module[]{};
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                ItemPreset itemPreset = (ItemPreset)t;
                if (!Intrinsics.areEqual(itemPreset.getId(), string)) continue;
                v0 = t;
                break block2;
            }
            v0 = null;
        }
        ItemPreset itemPreset = v0;
        if (itemPreset == null) {
            return;
        }
        itemPresetConfigFile.setCurrent(itemPreset);
        this.resetSize();
    }

    private final void deletePreset(String string) {
        ConfigStore.INSTANCE.getItemPresetConfigFile().getData().removeIf(arg_0 -> ItemPresetEditor.invokePredicate(arg_0 -> ItemPresetEditor.deletePreset$lambda$0(string, arg_0), arg_0));
        this.resetSize();
    }

    public final void resetSize() {
        this.setX(188.0f);
        Module[] moduleArray = new Module[]{};
        this.setY(119.0f);
        this.A.clear();
        int n = 0;
        Module[] moduleArray2 = moduleArray;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 9; ++j) {
                Item item;
                int n2 = n;
                if (ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().get(n) == null) {
                    item = Items.AIR;
                } else {
                    Object v = ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().get(n);
                    Intrinsics.checkNotNull(v);
                    item = (Item)v;
                }
                Item item2 = item;
                Intrinsics.checkNotNull(item2);
                Item item3 = item2;
                this.A.put(new RectButton((float)j * 20.0f, (float)i * 20.0f, 18.0f, 18.0f, "", () -> ItemPresetEditor.resetSize$lambda$0(item3, this, n2), n, Theme.INSTANCE.i(), Theme.INSTANCE.getInactiveModuleOutlineColor(), 0.0f, 0.0f, 1536, null), item3);
                ++n;
            }
        }
    }

    private final void setSlotItem(int n, Item item) {
        Module[] moduleArray = new Module[]{};
        HashMap hashMap = Maps.newHashMap((Map)this.A);
        Intrinsics.checkNotNullExpressionValue(hashMap, "newHashMap(...)");
        Map map = hashMap;
        Module[] moduleArray2 = moduleArray;
        for (Map.Entry entry : map.entrySet()) {
            Map.Entry entry2 = entry;
            if (((RectButton)entry2.getKey()).getId() != n) continue;
            this.A.put(entry2.getKey(), item);
        }
        ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getItems().put(n, item);
    }

    @Override
    public void render(@NotNull DrawContext drawContext, long l, int n, int n2) {
        block5: {
            long l2 = l;
            long l3 = l2 ^ 0x6C71211F7B21L;
            long l4 = l2 ^ 0x7A9ED94CA743L;
            long l5 = l2 ^ 0x632F22679A58L;
            long l6 = l2 ^ 0x5D56585A054BL;
            Intrinsics.checkNotNullParameter(drawContext, "context");
            float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.getX() / 2.0f;
            float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.getY() / 2.0f - 50.0f;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Color[] colorArray = new Color[]{new Color(0x44FFFFFF, true)};
            RenderUtil.INSTANCE.drawGradient(matrix3x2fStack, f + 6.0f, f2 + 91.5f, this.getX() - 12.0f, 0.5f, colorArray, l4);
            this.rectButton.render(f + 6.0f, f2 + 5.0f, n, l5, n2, drawContext);
            Module[] moduleArray = new Module[]{};
            Fonts.getSF_M2().drawStringCentered(drawContext, "select", f + 25.0f, f2 + 11.0f, Theme.INSTANCE.getInactiveTextColor(), l3);
            Fonts.getSF_M2().drawStringCentered(drawContext, "Current kit: " + ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getId(), f + this.getX() / 2.0f, f2 + 11.0f, Theme.INSTANCE.getInactiveTextColor(), l3);
            int n3 = 0;
            Map map = this.A;
            Module[] moduleArray2 = moduleArray;
            Iterator iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()) {
                int n4;
                Map.Entry entry;
                block4: {
                    int n5;
                    block3: {
                        Map.Entry entry2;
                        entry = entry2 = iterator2.next();
                        n5 = n3;
                        if (moduleArray2 == null) break;
                        boolean bl = 0 <= n5 ? n5 < 9 : false;
                        if (!bl) break block3;
                        n4 = 0;
                        break block4;
                    }
                    n4 = (9 <= n5 ? n5 < 18 : false) ? 20 : ((18 <= n5 ? n5 < 27 : false) ? 40 : 65);
                }
                int n6 = n4;
                ((RectButton)entry.getKey()).render((float)(n3 % 9) * 20.0f + f + 5.0f, (float)n6 + f2 + 30.0f, n, l5, n2, drawContext);
                if (component == null) {
                    drawContext.getMatrices().pushMatrix();
                    drawContext.getMatrices().translate((float)(n3 % 9) * 20.0f + f + 6.0f, (float)n6 + f2 + 31.0f);
                    drawContext.drawItem(((Item)entry.getValue()).asItem().getDefaultStack(), 0, 0);
                    drawContext.getMatrices().popMatrix();
                }
                ++n3;
            }
            if (moduleArray2 == null) break block5;
            Component component = ItemPresetEditor.component;
            if (component != null) {
                component.render(drawContext, n, n2, l6);
            }
        }
    }

    @Override
    public void updatePosition(long l, int n, int n2) {
        long l2 = l ^ 0L;
        Module[] moduleArray = new Module[]{};
        super.updatePosition(l2, n, n2);
        if (component != null) {
            Component component = ItemPresetEditor.component;
            Intrinsics.checkNotNull(component);
            component.updatePosition(l2, n, n2);
        } else {
            Map map = this.A;
            Iterator iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry;
                Map.Entry entry2 = entry = iterator2.next();
                ((RectButton)entry2.getKey()).mouseClicked(n, n2);
            }
            this.rectButton.mouseClicked(n, n2);
        }
    }

    @Override
    public void keyPressed(int n, long l) {
        block0: {
            long l2 = l ^ 0L;
            Module[] moduleArray = new Module[]{};
            super.keyPressed(n, l2);
            Module[] moduleArray2 = moduleArray;
            Component component = ItemPresetEditor.component;
            if (component == null) break block0;
            component.keyPressed(n, l2);
        }
    }

    @Override
    public void charTyped(char c) {
        block0: {
            Module[] moduleArray = new Module[]{};
            super.charTyped(c);
            Module[] moduleArray2 = moduleArray;
            Component component = ItemPresetEditor.component;
            if (component == null) break block0;
            component.charTyped(c);
        }
    }

    @Override
    public void mouseScrolled(double d, double d2, double d3) {
        block0: {
            Module[] moduleArray = new Module[]{};
            super.mouseScrolled(d, d2, d3);
            Module[] moduleArray2 = moduleArray;
            Component component = ItemPresetEditor.component;
            if (component == null) break block0;
            component.mouseScrolled(d, d2, d3);
        }
    }

    private static final Unit _init_$lambda$0$lambda$0(ItemPresetEditor itemPresetEditor, String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        itemPresetEditor.loadPreset(string);
        Component component = ItemPresetEditor.component;
        Intrinsics.checkNotNull(component);
        component.setClosing(true);
        return Unit.INSTANCE;
    }

    private static final Unit access$deletePreset(ItemPresetEditor itemPresetEditor, String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        itemPresetEditor.deletePreset(string);
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$0(ItemPresetEditor itemPresetEditor) {
        Iterable iterable;
        Iterable iterable2 = iterable = (Iterable)ConfigStore.INSTANCE.getItemPresetConfigFile().getData();
        Module[] moduleArray = new Module[]{};
        Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object t : iterable2) {
            ItemPreset itemPreset = (ItemPreset)t;
            Collection collection2 = collection;
            collection2.add(itemPreset.getId());
        }
        Function1<String, Unit> function1 = arg_0 -> ItemPresetEditor.access$deletePreset(itemPresetEditor, arg_0);
        Function1<String, Unit> function12 = arg_0 -> ItemPresetEditor._init_$lambda$0$lambda$0(itemPresetEditor, arg_0);
        List list = (List)collection;
        component = new ListComponent(list, function12, function1);
        return Unit.INSTANCE;
    }

    private static final boolean deletePreset$lambda$0(String string, ItemPreset itemPreset) {
        Intrinsics.checkNotNullParameter(itemPreset, "it");
        return Intrinsics.areEqual(itemPreset.getId(), string);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final Unit resetSize$lambda$0$lambda$0(ItemPresetEditor itemPresetEditor, int n, Item item) {
        Intrinsics.checkNotNullParameter(item, "item");
        itemPresetEditor.setSlotItem(n, item);
        Component component = ItemPresetEditor.component;
        Intrinsics.checkNotNull(component);
        component.setClosing(true);
        return Unit.INSTANCE;
    }

    private static final Unit resetSize$lambda$0(Item item, ItemPresetEditor itemPresetEditor, int n) {
        Item[] itemArray = new Item[]{item};
        component = new ItemSelectList(CollectionsKt.mutableListOf(itemArray), arg_0 -> ItemPresetEditor.resetSize$lambda$0$lambda$0(itemPresetEditor, n, arg_0));
        return Unit.INSTANCE;
    }

    public static final Component getComponent() {
        return component;
    }

    public static final void setComponent(Component component) {
        ItemPresetEditor.component = component;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ItemPresetEditor.a = ZkmKeys.a(1339252223487118068L, 462184803136938387L, MethodHandles.lookup().lookupClass()).a(129072437144889L);
                        var20 = ItemPresetEditor.a ^ 123407693425694L;
                        ItemPresetEditor.g = new HashMap<K, V>(13);
                        ItemPresetEditor.zkmSetFlag(new Module[3]);
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
                        var18_3 = new String[8];
                        var16_4 = 0;
                        var15_5 = "U\u0094\u00c1\u0091\u008d\u00e8\u00c6\u00fb\u0000\u0013\u00d3\u00ce\"\u00d5Z\u00b4\u00188\u00d3\u008c\u0098KR\u00aalg\u00a0\u00e6\u008e\u0098\u0001\u00fc\u009aEk2H\u0001i=\u00f0\u0010\u00bc;j+\u00ecN\u00a5\u0088\u00d8b\u00d3v\u00e6\u00eb\u0002K\u0010\u00b8\u00cc\u0096;\u00f8\u00bdM\u0086\t\b\u00dd\u009c\u0015bPa\u0018Z\u009f\u008c\u009c\u009d\u008a\u00ab\bB\u00f0$9\u00ea@\u0006\u0010:\r^\u00d2\u00ca\u00dc\u00ae\u0015\u0010\u008e\u0084\u00ab\u009c\u00d5\u00e9<o\u00d9E\u001d(^\u00fa\u0002\u00d2";
                        var17_6 = 117;
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
                            var18_3[var16_4++] = ItemPresetEditor.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "8\u00f9r*\u0097\u009a\u00f8\u0094q+\u00c1\u0099:cd\u00a2\u0018\u00dd\u00a2\\.F\u00d2M\u00e4Y\u009b\n\u0017\u009d\u0098R\u008c{\u0005=Z\u001e,\u00d0?";
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
                            var18_3[var16_4++] = ItemPresetEditor.zkmDecodeUtf8(var19_9).intern();
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
                ItemPresetEditor.c = var18_3;
                ItemPresetEditor.d = new String[8];
                ItemPresetEditor.m = new HashMap<K, V>(13);
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
                var6_12 = new long[15];
                var3_13 = 0;
                var4_14 = "\u00875}$\u00c7\tP6\u009fqp#OH!\u008b\u001er\u00e3\u00c2h\u0005\u00a8}\u00fc\u0005\u009b\u008c\bD\u009a\u00d8\u0091\u0096\u00f3`\u00fc$\u0018\u00fc\u00c5S\u00a3\u00a7D`V\u00bf\u000e\u000eF! I\u0089W\u0095\u00b55\u009a\u00b4\u00eav\u0001T\u001a\u00ea\u0007+\u00c7\u00a2\u00ff\u00e9 \u00c3\u00eb4\"\u0017\u0089a\u00ec\u0085\u0017G\u00a7\u00e8(\u00a0f\u00ca`\u00c4\u00ab\u0004\u00a5\u00b9\r\u00058\u001e\b\rH";
                var5_15 = 104;
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
                    var4_14 = "\u00bc\u00d9\u00f7\riB\u00e6\u00dc\u00d0\u00c1\u00e9v=?3e";
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
        ItemPresetEditor.i = var6_12;
        ItemPresetEditor.k = new Integer[15];
        ItemPresetEditor.Companion = new ItemPresetEditor$Companion(null);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        u = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return u;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4DC;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/dw", exception);
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
            ItemPresetEditor.d[n2] = ItemPresetEditor.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemPresetEditor.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x37BC;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
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
                throw new RuntimeException("su/catlean/dw", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ItemPresetEditor.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ItemPresetEditor.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/dw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemPresetEditor.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ItemPresetEditor.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

