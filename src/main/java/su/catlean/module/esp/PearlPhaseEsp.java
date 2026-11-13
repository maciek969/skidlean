/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class PearlPhaseEsp
extends Module {
    @NotNull
    public static final PearlPhaseEsp INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting range$delegate;
    @NotNull
    private static final ColorSetting safe$delegate;
    @NotNull
    private static final ColorSetting unsafe$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;

    private PearlPhaseEsp() {
        super("pearl-phase-esp", Categories.getEsp(), null, null);
    }

    private final float getRange() {
        return ((Number)range$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final Color getSafe() {
        return (Color)safe$delegate.getValue(this, delegatedProperties[1]);
    }

    private final Color getUnsafe() {
        return (Color)unsafe$delegate.getValue(this, delegatedProperties[2]);
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block4: {
            block3: {
                if (!Globals.getPlayer().isOnGround()) break block3;
                Entity entity = Globals.getPlayer();
                if (Globals.getWorld().getBlockState(BlockPos.ofFloored(this.getPos(entity).add(0.0, 0.21, 0.0))).isReplaceable()) break block4;
            }
            return;
        }
        Map map = this.findPhaseSpots();
        for (Map.Entry entry : map.entrySet()) {
            RenderState.addFilledBox$default(RenderState.INSTANCE, (Box)entry.getKey(), (Color)entry.getValue(), null, null);
            Object var29_8 = null;
            Color color = null;
            Color color2 = RenderUtil.INSTANCE.withAlpha((Color)entry.getValue(), 1.0f);
            Box box = (Box)entry.getKey();
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color2, color, var29_8);
        }
    }

    private final Map findPhaseSpots() {
        Map map;
        block2: {
            Map map2 = new LinkedHashMap();
            Iterator<VoxelShape> iterator2 = Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().expand(this.getRange(), 0.0, this.getRange()).withMinY(Globals.getPlayer().getY() + 0.21).withMaxY(Globals.getPlayer().getY() + 0.21)).iterator();
            boolean bl = BlockListSetting.H();
            while (iterator2.hasNext()) {
                Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
                map = map2;
                if (bl) {
                    VoxelShape voxelShape;
                    Box box = voxelShape.getBoundingBox().withMaxY(voxelShape.getBoundingBox().minY);
                    Intrinsics.checkNotNullExpressionValue(box, "setMaxY(...)");
                    map.put(box, Globals.getWorld().getBlockState(BlockPos.ofFloored(voxelShape.getBoundingBox().getCenter()).down()).isReplaceable() ? this.getUnsafe() : this.getSafe());
                    if (bl) continue;
                }
                break block2;
            }
            map = map2;
        }
        return map;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PearlPhaseEsp.a = ZkmKeys.a(8256421777120623764L, 220978328704308155L, MethodHandles.lookup().lookupClass()).a(47797196814303L);
                        var20 = PearlPhaseEsp.a ^ 120377022361817L;
                        PearlPhaseEsp.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "\u0085\u00f7\u0095\u00b4l,\u00dd\n\u008d\u00fdmbJ\u00dd\u00b1\u00c3a\u00c7#\u00c7\u0019W\u0019\u0004\u0010Dx\u0018\u000eI\u00a4!f\u00c6\u0093\u009f\u00b0\u00db+\u00f6\u00c5\u0010\u00dc.\u00a0v>o\u00b5\u00ef(C\u00cd\u00a2`\u00a3-\u0001\u0018\u001d\u0080v1\u000f\u00e1\u00d5\u00bc\u00e9\u0002\u00d5\u0002AM\u00b3s.d\u00e8\u00b9\u00b0\u000e\r!\u0018+\u00c0\u009fO\u00035\u00df\u00b0\u001b\u00c6\u00ea\u00a3[\u0017\u00c9\u0085\u001f\f\u00daN\u009c-\u000b\u00ac\u0018\u0091f\u0001\u0086\u008c]a\u00a3\\\u00aep\u0096\u00f0\u00c1\u00ef\u00a5\u00d2`\u00c74\u00878\u00f7+0\u00d9\u00b8@\u0090G)\u0016\u0002\u00fdy;\u00e2\u00bd\u00bd\u00fb\u00a8\u00a15oh\u0002'\u00fb\u00aaS\u0006\\\u00b4zK\u00a5\u00b4\u0090e\u00b7\u00eb\u00db\u00ccq\u00da\u00f5\u00f3Rna\u00e2i\u00a4\u0010\u00bc\u00de\u00fd\u00e1\u00e2t\u009e\u00cf(\u00b7R\u001e\u0096\u0001C\u00b6 \u000f\u00adm$\r\u0095\u00f0\u00e6\u00be\n\u008a\u0091\u0080\u001e\u00b4\u00f7\u00c19\u0015\u001eh\f\u00a2&\u0083\u0088\n\"\u00dc\u0006\u00d5_\u0010\u00fdFC\u00b3\u00e4x\u00d1\u001a\u00ed\u009fGDE\u00c1\u00cb\u0090";
                        var17_6 = 249;
                        var14_7 = 24;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = PearlPhaseEsp.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c7\u009c +\u00c5f\u00f4$\u001b-`mN\u0085%\u000305\u00ec\u00e7\u00f0\u00c5\u00d9\u00f79\u0093E\u00e4\u008229\u00cbg;\u00e4\u00a3\u0012\u008fX\u00c6D*\u008a\u008a\u0018P5\u000b\u00e4'uF\u00fd\u00bf;\u0015\u00e50?\u00ca\u0015S\rQZ";
                            var17_6 = 65;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
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
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = PearlPhaseEsp.zkmDecodeUtf8(var19_9).intern();
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
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                PearlPhaseEsp.b = var18_3;
                PearlPhaseEsp.c = new String[12];
                var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                    v9 = v9;
                    v9[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                }
                var1_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var0_12 = new long[9];
                var4_13 = 0;
                var5_14 = "A\u00d3\u00c7\u00cbp7sx\u0082\u0093#\u0087\u00cd\u0004\u00b0\u00ef\u009aP\u00ec\u0081\u00bf\u00cf?\u00d1v\u00d9\u0015\u0096\u0005\u00e0aV\u00c7\u00c2Q\u00fc\u00d2\u00c0>\u00b8\u0000\u00ebZ\u00bfu\u00b9\u00bb\u0007>,Ix\u00c8\r\u00f0R";
                var6_15 = 56;
                var3_16 = 0;
                while (true) {
                    var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                    v10 = var0_12;
                    v11 = var4_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl77:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    var5_14 = ";\u0094\u00df(\u00ad\u00e0\u00e1\u00e3h\u00da\u00b8\u00e8Y\u00a22\u009c";
                    var6_15 = 16;
                    var3_16 = 0;
                    while (true) {
                        var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                        v10 = var0_12;
                        v11 = var4_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl90:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl103:
                // 1 sources

                ** continue;
            }
        }
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(PearlPhaseEsp.class, "range", "getRange()F", 0)), Reflection.property1(new PropertyReference1Impl(PearlPhaseEsp.class, "safe", "getSafe()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(PearlPhaseEsp.class, "unsafe", "getUnsafe()Ljava/awt/Color;", 0))};
        PearlPhaseEsp.delegatedProperties = var29_20;
        PearlPhaseEsp.INSTANCE = new PearlPhaseEsp();
        PearlPhaseEsp.range$delegate = SettingFactory.floatSetting$default(PearlPhaseEsp.INSTANCE, "range", 1.0f, RangesKt.rangeTo(0.01f, 3.0f), null, null, (int)var0_12[8], null);
        PearlPhaseEsp.safe$delegate = SettingFactory.colorSetting$default(PearlPhaseEsp.INSTANCE, "safe", new Color((int)var0_12[2], (int)var0_12[0], (int)var0_12[0], (int)var0_12[3]), null, null, (int)var0_12[4], null);
        PearlPhaseEsp.unsafe$delegate = SettingFactory.colorSetting$default(PearlPhaseEsp.INSTANCE, "unsafe", new Color((int)var0_12[0], (int)var0_12[1], (int)var0_12[7], (int)var0_12[5]), null, null, (int)var0_12[6], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x434D;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/k2", exception);
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
            PearlPhaseEsp.c[n2] = PearlPhaseEsp.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PearlPhaseEsp.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PearlPhaseEsp.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

