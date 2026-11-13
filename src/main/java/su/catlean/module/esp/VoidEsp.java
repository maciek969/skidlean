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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.esp.VoidEsp$WhenMappings;
import su.catlean.module.esp.VoidEspMode;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class VoidEsp
extends Module {
    @NotNull
    public static final VoidEsp INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @NotNull
    private static final IntSetting range$delegate;
    private static int G;
    @NotNull
    private static List holes;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;

    private VoidEsp() {
        super("void-esp", Categories.getEsp(), null, null);
    }

    private final VoidEspMode getMode() {
        return (VoidEspMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[1]);
    }

    private final int getRange() {
        return ((Number)range$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final void setRange(int n) {
        range$delegate.setValue(this, delegatedProperties[2], n);
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        Iterable iterable = holes;
        for (Object t : iterable) {
            BlockPos blockPos = (BlockPos)t;
            Box box = new Box(blockPos);
            switch (VoidEsp$WhenMappings.u[INSTANCE.getMode().ordinal()]) {
                case 1: {
                    Object object;
                    Box box2;
                    Intrinsics.checkNotNullExpressionValue(box.withMaxY(blockPos.getY()), "setMaxY(...)");
                    Object var34_15 = null;
                    Color color = null;
                    Color color2 = INSTANCE.getColor();
                    RenderState renderState = RenderState.INSTANCE;
                    RenderState.addFilledBox$default(renderState, box2, color2, color, var34_15);
                    Intrinsics.checkNotNullExpressionValue(box.withMaxY(blockPos.getY()), "setMaxY(...)");
                    Object var19_4 = null;
                    Color color3 = null;
                    Color color4 = RenderUtil.INSTANCE.withAlpha(INSTANCE.getColor(), 1.0f);
                    boolean bl2 = RenderState.addOutlineBox$default(RenderState.INSTANCE, (Box)object, color4, color3, var19_4);
                    break;
                }
                case 2: {
                    Object var42_20 = null;
                    Color color = null;
                    Color color5 = INSTANCE.getColor();
                    Box box3 = box;
                    RenderState renderState = RenderState.INSTANCE;
                    RenderState.addFilledBox$default(renderState, box3, color5, color, var42_20);
                    Object var50_25 = null;
                    Color color6 = null;
                    Color color7 = RenderUtil.INSTANCE.withAlpha(INSTANCE.getColor(), 1.0f);
                    Box box4 = box;
                    boolean bl2 = RenderState.addOutlineBox$default(RenderState.INSTANCE, box4, color7, color6, var50_25);
                    break;
                }
                case 3: {
                    Object var57_29 = null;
                    Color color = null;
                    Color color8 = RenderUtil.INSTANCE.withAlpha(INSTANCE.getColor(), 1.0f);
                    Box box5 = box;
                    boolean bl2 = RenderState.addOutlineBox$default(RenderState.INSTANCE, box5, color8, color, var57_29);
                    break;
                }
                case 4: {
                    Object var19_4 = null;
                    int n = 8;
                    boolean bl = true;
                    Color color4 = null;
                    Object object = INSTANCE.getColor();
                    Vec3d vec3d = new Vec3d(box.maxX, box.minY, box.minZ);
                    Vec3d vec3d2 = new Vec3d(box.minX, box.minY, box.maxZ);
                    RenderState.addLine$default(RenderState.INSTANCE, vec3d2, vec3d, (Color)object, color4, bl, n, var19_4);
                    var19_4 = null;
                    n = 8;
                    bl = true;
                    color4 = null;
                    object = INSTANCE.getColor();
                    vec3d = new Vec3d(box.minX, box.minY, box.minZ);
                    vec3d2 = new Vec3d(box.maxX, box.minY, box.maxZ);
                    boolean bl2 = RenderState.addLine$default(RenderState.INSTANCE, vec3d2, vec3d, (Color)object, color4, bl, n, var19_4);
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        holes = this.findVoidHoles();
    }

    private final List findVoidHoles() {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n = (int)(Globals.getPlayer().getX() + (double)this.getRange());
        for (int i = (int)(Globals.getPlayer().getX() - (double)this.getRange()); i < n; ++i) {
            int n2 = (int)(Globals.getPlayer().getZ() + (double)this.getRange());
            for (int j = (int)(Globals.getPlayer().getZ() - (double)this.getRange()); j < n2; ++j) {
                BlockPos blockPos;
                Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(i, Globals.getWorld().getBottomY(), j), "containing(...)");
                if (Intrinsics.areEqual(Globals.getWorld().getBlockState(blockPos).getBlock(), Blocks.BEDROCK)) continue;
                arrayList.add(blockPos);
            }
        }
        return arrayList;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        VoidEsp.a = ZkmKeys.a(6680345788288045339L, 5452596865731013714L, MethodHandles.lookup().lookupClass()).a(216175532279012L);
                        var20 = VoidEsp.a ^ 97045749215361L;
                        VoidEsp.d = new HashMap<K, V>(13);
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
                        var15_5 = "\u00ef\u00f3~\u0005\u00c1\u001dm\u00e6\u00f6\u00dbrE\u000e\u000e\u00a5\u00b7U\u0084\u00ccD3\u00d3\u008a\u009f\u008d\u009c\u00b6\u00a3/\u00c4Gy8\u00c9\u00f6}b\u007fu>D\u00c95\u00fb\u00c9\u00e6y5Z\u00dd\n\u00ed\u0084\u0095o0\u009d\u00c8\u00ba\u00acF\u008f\u00d1\u0092\u00e2X\u0018\u0087\u00cb@:\u00dc\u00f2E\u00f3W\u0092-\u00cd\u0006uP7\u0005\u00eb\u0018\u00df\u008c\u00af\u0010=>\u008a\u00cbk_\u00e9\u00c8u_\u0001q\u00aa\u0001_\u00c6\u0010\u00c4[Z\u0006=~\u008ecT\u0082\u00ccWN\u0087\u00c5n\u0010z\u00e4\u0003:\u00d0l\u00a9\u001d&\u00e9\u00a6\u00e8g\u00c2\u00fdK\u0010Nj\u00ddI\u000bh\u0010_o\u00bd\u009d!\u00ee\t\u00f9H \u00deQ\u0004\u00db\u0089\u00faf\u0018\u009f.\u00ed\u00fa\u00af\u00b3\u0088\u009eV1v\u0011\u008fqM]&\u00d5#\u00cc\u00fc\u0015\u001c\u00e7\u0010\u00a58\bB\u0083/S\u00cb\u00f1\r\u00b2\u009cY\u00d9\u0018\r QO\u00b5f\u000e|\u00adK\u00f5\u00ef\u00d2\u00a7\u00d8B\u00a3cS\u00f23\u00f7\u0081\u00e4\u0098\u0001\u00ba\u00bb\u00b3~\u001f\u00ac'Q\u0010F\f7\u0080$\u00c99\u00fcP\u0085\u0014\u00d3\u00cfbVj\u0018\u00b7C\u00ebW_\u0099\u009c\u001f\u00fd\u00c0$c\u00c1\u00ecN\u00c8\u00a5X0\u00d9\u00aa\u0099\u00bc\u008288\u00ceA\u00eae\u00ee\u00a4\u00db\u0089e\u0013\u00f45/\u00a0\u001c/J\u00d7\u0011B\u0017\u0087\u00a6\u0003E\u0098\u000b0\u009f\u001b\"\u00d3\u00ac3\u00ba\u00041\u00d2ET\u0013\u009f\u00a4\u00b4j\u00ba\u00bc\u00e9\u00a3\u0091\u00c1X\u0015]\u00c5";
                        var17_6 = 339;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = VoidEsp.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "Fi\u0093!\u009d\u00d7\u001ek\u00dc$b\u00fc\u0010\u00c2:\u008e\u00f4\u008f\u0005\u0018\u00ad\u0094\u0014p\u00fb\u00e8\u0094\u00ba2d}^\u0010A\u00afV\u001a\u001e\u00be\u00a8\u0088>\f\u00a0+u[\u00dbZ";
                            var17_6 = 49;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = VoidEsp.zkmDecodeUtf8(var19_9).intern();
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
                VoidEsp.b = var18_3;
                VoidEsp.c = new String[14];
                VoidEsp.i = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "\u0010O\u00c5\u00b8\u00df\u00a0\u00ec\u00d6\u00dd\u009faL}j\u00f0\u009e6\u007f7\u0087kB\u00bb\u00c2\u00a5W\u00e5\u00ee\u009a\u00e4\u00a9\u00a2<y\u00b7,<=#z";
                var5_15 = 40;
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
                    var4_14 = "\u00fd\u0085\u00e5\u00c8\u00b1\u007f\u00d8V\u00a7\u00ed1\u00ec 97/";
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
        VoidEsp.f = var6_12;
        VoidEsp.g = new Integer[7];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(VoidEsp.class, "mode", "getMode()Lsu/catlean/xn;", 0)), Reflection.property1(new PropertyReference1Impl(VoidEsp.class, "color", "getColor()Ljava/awt/Color;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(VoidEsp.class, "range", "getRange()I", 0))};
        VoidEsp.delegatedProperties = var32_20;
        VoidEsp.INSTANCE = new VoidEsp();
        VoidEsp.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)VoidEsp.INSTANCE, "mode", (Enum)VoidEspMode.CROSS, null, null, 12, null);
        v15 = VoidEsp.INSTANCE;
        v16 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v16, "WHITE");
        VoidEsp.color$delegate = SettingFactory.colorSetting$default(v15, "color", v16, null, null, 12, null);
        VoidEsp.range$delegate = SettingFactory.intSetting$default(VoidEsp.INSTANCE, "range", 10, new IntRange(1, 64), null, null, 24, null);
        VoidEsp.holes = new ArrayList<E>();
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3DE0;
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
                throw new RuntimeException("su/catlean/kz", exception);
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
            VoidEsp.c[n2] = VoidEsp.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = VoidEsp.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x36CF;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/kz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            VoidEsp.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = VoidEsp.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(VoidEsp.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(VoidEsp.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

