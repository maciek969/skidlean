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
import java.util.HashMap;
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
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.TitledComponent;
import su.catlean.zkm.ZkmKeys;

public final class ItemSelectList
extends TitledComponent {
    @NotNull
    private final List list;
    @NotNull
    private final Function1 clickAction;
    private static final long d;
    private static final String[] o;
    private static final String[] q;
    private static final Map r;

    public ItemSelectList(@NotNull List list, @NotNull Function1 function1) {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ d;
        Intrinsics.checkNotNullParameter(list, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)5108, (long)(0x1EB3D43D41A3FC01L ^ l2))));
        Intrinsics.checkNotNullParameter(function1, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)32527, (long)(0x73907E5AACA310FCL ^ l2))));
        super((String)((Object)ItemSelectList.zkmBootstrap("p", (int)23052, (long)(0x9493E3922AAB5FAL ^ l2))));
        this.list = list;
        this.clickAction = function1;
    }

    @NotNull
    public final List getList() {
        return this.list;
    }

    @NotNull
    public final Function1 getClickAction() {
        return this.clickAction;
    }

    @Override
    @NotNull
    protected List getOptions() {
        long l = 0L;
        DefaultedRegistry<Item> defaultedRegistry = Registries.ITEM;
        Intrinsics.checkNotNullExpressionValue(defaultedRegistry, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)32021, (long)(0x6DA64D6BAAB1E23FL ^ l))));
        return CollectionsKt.toList((Iterable)defaultedRegistry);
    }

    @NotNull
    protected String getItemName(@NotNull Item item) {
        long l = 0L;
        l = d ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)23404, (long)(0x2F80C5AA3BB99EDDL ^ l))));
        String string = item.getName().getString();
        Intrinsics.checkNotNullExpressionValue(string, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)1133, (long)(0x58EA3CC0A546C1DAL ^ l))));
        return string;
    }

    protected boolean isSelected(@NotNull Item item) {
        long l = 0L;
        l = d ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)23404, (long)(0x2F808405485247E5L ^ l))));
        return this.list.contains(item);
    }

    public void drawItem(@NotNull DrawContext drawContext, float f, float f2, @NotNull Item item) {
        long l = 0L;
        l = d ^ l;
        Intrinsics.checkNotNullParameter(drawContext, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)15549, (long)(0xFD03B9D5E01448DL ^ l))));
        Intrinsics.checkNotNullParameter(item, (String)((Object)ItemSelectList.zkmBootstrap("p", (int)14125, (long)(0x2EB2789BF96ACF1BL ^ l))));
        drawContext.getMatrices().pushMatrix();
        drawContext.getMatrices().translate(f + 8.0f, f2);
        drawContext.drawItem(item.getDefaultStack(), 0, 0);
        drawContext.getMatrices().popMatrix();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void toggle(@NotNull Item item) {
        boolean bl;
        List list;
        int n = TitledComponent.R();
        Intrinsics.checkNotNullParameter(item, "item");
        int n2 = n;
        this.clickAction.invoke(item);
        List list2 = list = this.list;
        Item item2 = item;
        if (n2 == 0) {
            if (list2.contains(item2)) {
                bl = list.remove(item);
                return;
            }
            list2 = list;
            item2 = item;
        }
        bl = list2.add(item2);
    }

    @Override
    public String getOptionLabel(Object object) {
        return this.getItemName((Item)object);
    }

    @Override
    public boolean isSelected(Object object) {
        return this.isSelected((Item)object);
    }

    @Override
    public void renderOption(DrawContext drawContext, float f, float f2, Object object) {
        this.drawItem(drawContext, f, f2, (Item)object);
    }

    @Override
    public void select(Object object) {
        this.toggle((Item)object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                ItemSelectList.d = ZkmKeys.a(4484864159493499220L, 3951599726281628396L, MethodHandles.lookup().lookupClass()).a(38506403934038L);
                ItemSelectList.r = new HashMap<K, V>(13);
                var0 = ItemSelectList.d ^ 2911767454843L;
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
                var6_5 = "\u00d1\u00e5\u00a4\u0096LDr^3\u00ec:\u00f3\u00d0\u00c2\u00c0\u0017\u0010\u0086:\u00ce\u00ef\u00b4\u00db\u00c0t\u001a\u0098\u0000.\u0099s\u00c9^ \u00aa\u0080\u00f3Z\u00dd\u0080\u00941['\u008fR\n\u00fc\u00a1\u009c\u0006m\u0097]:{sOt|;\u0083\u009f\u0096\n\u0005\u0010d\u00e9c\u0005\u0083\u00de*@\u00a8\u00bc\u0095\u00ea\u00be\u00c6\u0082;\u0010,pq\u00e7\u00b8\u00f3U\u0012p#tJR\u00fa_F\u0010:/\u008b{-So\u00da\u00e8\u0082z\u000ev\u00bb: ";
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
                    var9_3[var7_4++] = ItemSelectList.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "j\u0007\u00a7\u00d1$\u0082ko\u0093\u00f8\u00b0\u00edi?\u008dL\u00ad\u00e6\u00d85\u0017H\u0019\u00a2\u0098H\u00cbw\u009f\u00dbAD\u0018\u0006\u0002s[\u00d0T0W\u008bD2\u00d4\u0099q\u0089<\u00a1]n\u00e4\u00c3\u00d9a\u00be";
                    var8_6 = 57;
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
                    var9_3[var7_4++] = ItemSelectList.zkmDecodeUtf8(var10_9).intern();
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
        ItemSelectList.o = var9_3;
        ItemSelectList.q = new String[8];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2AD3;
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
                throw new RuntimeException("su/catlean/dr", exception);
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
            ItemSelectList.q[n2] = ItemSelectList.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return q[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemSelectList.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemSelectList.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

