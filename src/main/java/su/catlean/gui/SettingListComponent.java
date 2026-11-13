/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.TitledComponent;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.Setting;
import su.catlean.util.BlockNameComparator;
import su.catlean.zkm.ZkmKeys;

public final class SettingListComponent
extends TitledComponent {
    @NotNull
    private final Setting setting;
    private static final long d;
    private static final String[] o;
    private static final String[] q;
    private static final Map r;

    public SettingListComponent(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super("Select Blocks");
        this.setting = setting2;
    }

    @NotNull
    public final Setting getSetting() {
        return this.setting;
    }

    @Override
    @NotNull
    protected List getOptions() {
        long l = 0L;
        List list = new ArrayList();
        Collection collection = list;
        DefaultedRegistry<Block> defaultedRegistry = Registries.BLOCK;
        Intrinsics.checkNotNullExpressionValue(defaultedRegistry, (String)((Object)SettingListComponent.zkmBootstrap("j", (int)22610, (long)(0x23ED162EAAEAFD81L ^ l))));
        CollectionsKt.addAll(collection, (Iterable)defaultedRegistry);
        list.remove(Blocks.AIR);
        Iterable iterable = list;
        CollectionsKt.sortedWith(iterable, new BlockNameComparator());
        return list;
    }

    @NotNull
    protected String getBlockName(@NotNull Block block) {
        long l = 0L;
        l = d ^ l;
        Intrinsics.checkNotNullParameter(block, (String)((Object)SettingListComponent.zkmBootstrap("j", (int)22076, (long)(0xB957625AA844E90L ^ l))));
        String string = block.getName().getString();
        Intrinsics.checkNotNullExpressionValue(string, (String)((Object)SettingListComponent.zkmBootstrap("j", (int)16279, (long)(0x4A829E2C07CA73CL ^ l))));
        return string;
    }

    protected boolean isSelected(@NotNull Block block) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ d;
        Intrinsics.checkNotNullParameter(block, (String)((Object)SettingListComponent.zkmBootstrap("j", (int)22076, (long)(0xB956F475F620858L ^ l))));
        return ((BlockListValue)this.setting.getValue()).getBlocks().contains(block);
    }

    public void drawBlock(@NotNull DrawContext drawContext, float f, float f2, @NotNull Block block) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ d;
        Intrinsics.checkNotNullParameter(drawContext, (String)((Object)SettingListComponent.zkmBootstrap("j", (int)92, (long)(0x7642B8C2A5496F19L ^ l))));
        Intrinsics.checkNotNullParameter(block, (String)((Object)SettingListComponent.zkmBootstrap("j", (int)10664, (long)(0x1EE68E298B9C46EFL ^ l))));
        drawContext.getMatrices().pushMatrix();
        drawContext.getMatrices().translate(f + 8.0f, f2);
        drawContext.drawItem(block.asItem().getDefaultStack(), 0, 0);
        drawContext.getMatrices().popMatrix();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void toggle(@NotNull Block block) {
        boolean bl;
        Intrinsics.checkNotNullParameter(block, "item");
        List list = ((BlockListValue)this.setting.getValue()).getBlocks();
        int n = TitledComponent.R();
        List list2 = list;
        Block block2 = block;
        if (n == 0) {
            if (list2.contains(block2)) {
                bl = list.remove(block);
                return;
            }
            list2 = list;
            block2 = block;
        }
        bl = list2.add(block2);
    }

    @Override
    public String getOptionLabel(Object object) {
        return this.getBlockName((Block)object);
    }

    @Override
    public boolean isSelected(Object object) {
        return this.isSelected((Block)object);
    }

    @Override
    public void renderOption(DrawContext drawContext, float f, float f2, Object object) {
        this.drawBlock(drawContext, f, f2, (Block)object);
    }

    @Override
    public void select(Object object) {
        this.toggle((Block)object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                SettingListComponent.d = ZkmKeys.a(-8632702415593481019L, 3831179648269467186L, MethodHandles.lookup().lookupClass()).a(92361440263941L);
                SettingListComponent.r = new HashMap<K, V>(13);
                var0 = SettingListComponent.d ^ 18464682235336L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[8];
                var7_4 = 0;
                var6_5 = ",8\u00be\u0089\u0016\\\\'\u008bs\u00f3f{\u009f'\u008c\u0010\u008b&_\u00e8\u0085\u0089o\u00a5\u001f\u00e5l|\u0087v\u0083\u0090 \u0094)\u0087\u00c6\u0000\u000e\u0006l\fo\u00c4\u00ef\u00af<F\tvf\u009e\u00f5\u009drG$\u00ce\u00ef,\u00d2\u00cd4\u0093?\u0010\u00ddb;k\u00d3\u00da\u00937\u0086\u00a2\u00aa\u009a\u001d%\u0016\u00f1\u0010\u00b0B\u0000\u00c0TzJ~\u00c6\u0093H6R8~\u00b2\u0010\u0098\u00d0\u00c6s\u0013\u00a4\u00ee\u00ac\u0003F\u00d2r\u0015\u0007g\u0086";
                var8_6 = 117;
                var5_7 = 16;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = SettingListComponent.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u00ee\u00dd\u00b0\u00f48\u00ab\u0095/&\u008e\t;\u00ed\nQYu\u0083\u0006I\u001a\u00a9\u00c77\u00a4\u0086\u00bf[\u00ef\u00d4 Q\u0010vE\u0010)\u0089\u00dc\u008a\u00d94\u00b5\u0017\u008b\u00a9e\u008b\u00c3";
                    var8_6 = 49;
                    var5_7 = 32;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = SettingListComponent.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        SettingListComponent.o = var9_3;
        SettingListComponent.q = new String[8];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x102B;
        if (q[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])r.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    r.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dp", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = o[n2].getBytes("ISO-8859-1");
            SettingListComponent.q[n2] = SettingListComponent.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return q[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingListComponent.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingListComponent.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

