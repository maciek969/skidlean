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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.TitledComponent;
import su.catlean.setting.EntityListValue;
import su.catlean.setting.Setting;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SettingTitledComponent
extends TitledComponent {
    @NotNull
    private final Setting setting;
    private static final long d;
    private static final String[] o;
    private static final String[] q;
    private static final Map r;

    public SettingTitledComponent(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super("Select Entities");
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
        DefaultedRegistry<EntityType<?>> defaultedRegistry = Registries.ENTITY_TYPE;
        Intrinsics.checkNotNullExpressionValue(defaultedRegistry, (String)((Object)SettingTitledComponent.zkmBootstrap("f", (int)6063, (long)(0x3CB4B46C8D174209L ^ l))));
        return CollectionsKt.toList((Iterable)defaultedRegistry);
    }

    @NotNull
    protected String getEntityName(@NotNull EntityType entityType) {
        long l = 0L;
        l = d ^ l;
        Intrinsics.checkNotNullParameter(entityType, (String)((Object)SettingTitledComponent.zkmBootstrap("f", (int)22865, (long)(0x5F56B31FF21E1A37L ^ l))));
        String string = entityType.getName().getString();
        Intrinsics.checkNotNullExpressionValue(string, (String)((Object)SettingTitledComponent.zkmBootstrap("f", (int)30112, (long)(0x6585F43E9C3136C5L ^ l))));
        return string;
    }

    protected boolean isSelected(@NotNull EntityType entityType) {
        long l = 0L;
        l = d ^ l;
        Intrinsics.checkNotNullParameter(entityType, (String)((Object)SettingTitledComponent.zkmBootstrap("f", (int)20976, (long)(0x6F5C0CEF6D03911EL ^ l))));
        return ((EntityListValue)this.setting.getValue()).getEntities().contains(entityType);
    }

    /*
     * Unable to fully structure code
     */
    public void drawEntity(@NotNull DrawContext var1_1, float var2_2, float var3_3, @NotNull EntityType var4_4) {
        var8_5 = 0L;
        var8_5 = SettingTitledComponent.d ^ var8_5;
        v0 = ZkmReferences.zkmBootstrap("\u00c5", (long)7665641275394559561L, (long)var8_5);
        Intrinsics.checkNotNullParameter(var1_1, (String)SettingTitledComponent.zkmBootstrap("f", (int)21131, (long)(6103299079457331555L ^ var8_5)));
        var5_6 = v0;
        Intrinsics.checkNotNullParameter(var4_4, (String)SettingTitledComponent.zkmBootstrap("f", (int)28113, (long)(4604678328301315642L ^ var8_5)));
        if (var5_6 == false) ** GOTO lbl-1000
        v1 = SpawnEggItem.forEntity(var4_4);
        if (v1 != null) {
            v2 = v1;
        } else lbl-1000:
        // 2 sources

        {
            v2 = Items.BARRIER;
        }
        var7_7 = v2;
        Intrinsics.checkNotNull(var7_7);
        var6_8 = var7_7;
        var1_1.getMatrices().pushMatrix();
        var1_1.getMatrices().translate(var2_2 + 8.0f, var3_3);
        var1_1.drawItem(var6_8.getDefaultStack(), 0, 0);
        var1_1.getMatrices().popMatrix();
    }

    protected void toggle(@NotNull EntityType entityType) {
        Intrinsics.checkNotNullParameter(entityType, "item");
        List list = ((EntityListValue)this.setting.getValue()).getEntities();
        boolean bl = list.contains(entityType) ? list.remove(entityType) : list.add(entityType);
    }

    @Override
    public String getOptionLabel(Object object) {
        return this.getEntityName((EntityType)object);
    }

    @Override
    public boolean isSelected(Object object) {
        return this.isSelected((EntityType)object);
    }

    @Override
    public void renderOption(DrawContext drawContext, float f, float f2, Object object) {
        this.drawEntity(drawContext, f, f2, (EntityType)object);
    }

    @Override
    public void select(Object object) {
        this.toggle((EntityType)object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                SettingTitledComponent.d = ZkmKeys.a(5470963018992934984L, 6806161391662975180L, MethodHandles.lookup().lookupClass()).a(211938083608534L);
                SettingTitledComponent.r = new HashMap<K, V>(13);
                var0 = SettingTitledComponent.d ^ 23114834137429L;
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
                var6_5 = "\u00ef\u00de\u00c2?\u008aV\u0087\u001c\u00002\u009a\u0084\u00a5\u00cc{*\u0010\u0088\u00e7\u009dl\u001c\u00c92\u0095;]\u00ab\u00c5\u00c1;\u009eV \u00c1\u00aa\u000b*\u00e9V\u00db\u00ec\u00de\u0092O\u009b\u00a1+\u007f%\u001a\u0092\u0091\u00d0\u00dby\u009a\u00a3\u00fa\u0011Z\u00bf\u00ae\u0018\u00ad\u00c3\u0010j\u001f\u009b\u00ef\u00cb\u00c3r\u00f6\u00f3\u00d6L*\u00d9\u00c0\u00da\u0003\u0010Y\u0083m~\u00a7?\u00c7?\u00d1\u00e3t^\u009c\\\u00bf0\u0010\u0090\u00dcN\u00c7\u009c\u00e1Kd\u00da,M\u00d1U\u00c8_&";
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
                    var9_3[var7_4++] = SettingTitledComponent.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u00efq\u0012>\u00c4\u00a6 D#\u00be\u009f\u008a\u0096\u0085\u0097;\u00be\u008b\u00b8\u00bf*\u008b\u0090O\u00b5\u00d9hN\u009bG\u001cq\u0018\u0000\u00a2\u00bd_\u0015\u0091\u00ea\u0083\u001e\u00bc\u00a1o\u00b2ttq\u00efj\u00dd\u00b2\u00fex\u00a4\u00d9";
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
                    var9_3[var7_4++] = SettingTitledComponent.zkmDecodeUtf8(var10_9).intern();
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
        SettingTitledComponent.o = var9_3;
        SettingTitledComponent.q = new String[8];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6058;
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
                throw new RuntimeException("su/catlean/d8", exception);
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
            SettingTitledComponent.q[n2] = SettingTitledComponent.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return q[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingTitledComponent.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/d8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingTitledComponent.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

