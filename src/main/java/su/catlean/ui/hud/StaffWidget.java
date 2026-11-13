/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.ui.hud;

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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.ui.hud.NamedEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.JoinedPlayer;
import su.catlean.util.PlayerListUtil;
import su.catlean.zkm.ZkmKeys;

public final class StaffWidget
extends HudElement {
    @NotNull
    public static final StaffWidget k;
    @NotNull
    private static final List m;
    @NotNull
    private static final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private StaffWidget(int n, int n2, byte by) {
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)by << 56 >>> 56) ^ a;
        long l2 = l ^ 0x435C285898D8L;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 48 >>> 48);
        super((char)n3, "staff-widget", (char)n4);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        Iterable iterable;
        long l2 = l;
        long l3 = l2 ^ 0x76B33ADF9490L;
        long l4 = l2 ^ 0x5EAEB98FBCE1L;
        int n = (int)(l4 >>> 48);
        int n2 = (int)(l4 << 16 >>> 32);
        int n3 = (int)(l4 << 48 >>> 48);
        long l5 = l2 ^ 0x74C22041CFCEL;
        long l6 = l2 ^ 0x1258F934B109L;
        long l7 = l2 ^ 0xCE7FBF74EB7L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        for (JoinedPlayer joinedPlayer : PlayerListUtil.INSTANCE.a()) {
            boolean bl;
            boolean bl2;
            block10: {
                iterable = m;
                if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                    bl2 = true;
                } else {
                    for (Object object2 : iterable) {
                        NamedEntry namedEntry = (NamedEntry)object2;
                        if (!Intrinsics.areEqual(namedEntry.getName(), joinedPlayer.describe(l7))) continue;
                        bl2 = false;
                        break block10;
                    }
                    bl2 = true;
                }
            }
            if (!(bl = bl2)) continue;
            m.add(new NamedEntry(joinedPlayer.describe(l7), null, false, null));
        }
        Object object = m;
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            boolean bl;
            Iterator iterator3;
            block11: {
                Object object2;
                iterable = iterator2.next();
                iterator3 = (NamedEntry)((Object)iterable);
                object2 = PlayerListUtil.INSTANCE.a();
                if (object2 instanceof Collection && ((Collection)object2).isEmpty()) {
                    bl = true;
                } else {
                    for (Object t : object2) {
                        JoinedPlayer joinedPlayer = (JoinedPlayer)t;
                        if (!Intrinsics.areEqual(joinedPlayer.describe(l7), ((NamedEntry)((Object)iterator3)).getName())) continue;
                        bl = false;
                        break block11;
                    }
                    bl = true;
                }
            }
            if (!bl) continue;
            ((NamedEntry)((Object)iterator3)).setRemoving(true);
        }
        m.removeIf(arg_0 -> StaffWidget.invokePredicate(StaffWidget::updateEntry, arg_0));
        object = "Staff";
        boolean bl = this.getX() > (float)this.getScreenWidth() / 2.0f;
        animation.animate(m.isEmpty() && !(Globals.getMc().currentScreen instanceof ChatScreen) && !(Globals.getMc().currentScreen instanceof HudEditorScreen) ? 0.0f : 1.0f);
        this.drawLabel(drawContext, (String)object, animation.getValue(), bl, l3);
        float f = 17.0f;
        float f2 = Fonts.getSF_M().getWidth(l6, (String)object) + 24.0f;
        for (Object object3 : m) {
            long l8 = l6;
            float f3 = Fonts.getSF_M().getWidth(l8, ((Object)StringsKt.trim((CharSequence)((NamedEntry)object3).getName())).toString());
            if (!(f3 + 7.0f > f2)) continue;
            f2 = f3 + 7.0f;
        }
        for (Object object3 : m) {
            float f4 = Fonts.getSF_M().getWidth(l6, ((Object)StringsKt.trim((CharSequence)((NamedEntry)object3).getName())).toString());
            float f5 = bl ? this.getX() - (1.0f - ((NamedEntry)object3).getAnimation().getValue()) * -10.0f - (f4 + 8.0f - f2) : this.getX() - (1.0f - ((NamedEntry)object3).getAnimation().getValue()) * 10.0f;
            HudElement hudElement = this;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            HudElement.drawBackground$default(hudElement, matrix3x2fStack, f5, (short)n, n2, this.getY() + f, f4 + 7.0f, 13.0f, (short)n3, ((NamedEntry)object3).getAnimation().getValue(), false, 64, null);
            float f6 = f;
            float f7 = ((NamedEntry)object3).getAnimation().getValue();
            this.drawString(drawContext, ((Object)StringsKt.trim((CharSequence)((NamedEntry)object3).getName())).toString(), f5 + 3.0f, l5, this.getY() + f6 + 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f7));
            f += 14.0f * ((NamedEntry)object3).getAnimation().getValue();
        }
        this.setSize(f2, 13.0f + f);
    }

    private static final boolean updateEntry(NamedEntry namedEntry) {
        Intrinsics.checkNotNullParameter(namedEntry, "it");
        namedEntry.getAnimation().animate(namedEntry.isRemoving() ? 0.0f : 1.0f);
        return namedEntry.isRemoving() && namedEntry.getAnimation().getValue() <= 0.0f;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
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
                            StaffWidget.a = ZkmKeys.a(8005792646645263982L, 953061348799163574L, MethodHandles.lookup().lookupClass()).a(143583625293000L);
                            var27 = StaffWidget.a ^ 84531735683691L;
                            v0 = var27 ^ 93536360476728L;
                            var29_1 = (int)(v0 >>> 32);
                            var30_2 = (int)(v0 << 32 >>> 40);
                            var31_3 = (int)(v0 << 56 >>> 56);
                            StaffWidget.f = new HashMap<K, V>(13);
                            var18_4 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v1 = SecretKeyFactory.getInstance("DES");
                            v2 = new byte[8];
                            v3 = v2;
                            v2[0] = (byte)(var27 >>> 56);
                            for (var19_5 = 1; var19_5 < 8; ++var19_5) {
                                v3 = v3;
                                v3[var19_5] = (byte)(var27 << var19_5 * 8 >>> 56);
                            }
                            var18_4.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                            var25_6 = new String[5];
                            var23_7 = 0;
                            var22_8 = "0\u008fH \u00b9L\u009a\u0012\u00a9\u0007(:\u00d7\u00fb\u00f7\u009c\u0018\u0001C\u00ab\u00c0e\u00e7\u00cc\u0091=\u00a7\u00c0\u00f6\u009d\u0010\u00fe\u00ec\u0089\u0002#\u00a6\u00ef\u00a4(\u001e\u0010$z\u00ad\u00c0\u00dc\u00069TZh\u0082-\u00f0^\u00de\u009d";
                            var24_9 = 58;
                            var21_10 = 16;
                            var20_11 = -1;
lbl26:
                            // 2 sources

                            while (true) {
                                v4 = ++var20_11;
                                v5 = var22_8.substring(v4, v4 + var21_10);
                                v6 = -1;
                                break block20;
                                break;
                            }
lbl31:
                            // 1 sources

                            while (true) {
                                var25_6[var23_7++] = StaffWidget.zkmDecodeUtf8(var26_12).intern();
                                if ((var20_11 += var21_10) < var24_9) {
                                    var21_10 = var22_8.charAt(var20_11);
                                    ** continue;
                                }
                                var22_8 = "\u00b7\u00ed4A\u0002\u0091\u00e8*]\u0081z\u0097\u00f0\u00d1+p k\u0017O\u00a1\u001e\u001aY\u00e2\u001d\u00bf\u00a2\u00f4\u001a\u00f1\u0091\u0082w\u00eb\u00c1\u00a0\u00ee \u00be\u0081\u0090\u00ad\u008cD\u00cc\u00bf\f\n";
                                var24_9 = 49;
                                var21_10 = 16;
                                var20_11 = -1;
lbl40:
                                // 2 sources

                                while (true) {
                                    v7 = ++var20_11;
                                    v5 = var22_8.substring(v7, v7 + var21_10);
                                    v6 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl45:
                            // 1 sources

                            while (true) {
                                var25_6[var23_7++] = StaffWidget.zkmDecodeUtf8(var26_12).intern();
                                if ((var20_11 += var21_10) < var24_9) {
                                    var21_10 = var22_8.charAt(var20_11);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_12 = var18_4.doFinal(v5.getBytes("ISO-8859-1"));
                        switch (v6) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl57:
                            // 1 sources

                            ** continue;
                        }
                    }
                    StaffWidget.b = var25_6;
                    StaffWidget.c = new String[5];
                    StaffWidget.i = new HashMap<K, V>(13);
                    var7_13 = Cipher.getInstance("DES/CBC/NoPadding");
                    v8 = SecretKeyFactory.getInstance("DES");
                    v9 = new byte[8];
                    v10 = v9;
                    v9[0] = (byte)(var27 >>> 56);
                    for (var8_14 = 1; var8_14 < 8; ++var8_14) {
                        v10 = v10;
                        v10[var8_14] = (byte)(var27 << var8_14 * 8 >>> 56);
                    }
                    var7_13.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
                    var13_15 = new long[4];
                    var10_16 = 0;
                    var11_17 = "\u00ed\u00e6\u00ec\u00b4\u00dd=\u0018\u00f5\u00f65\\^\\\u00dd\u00d6\u0094";
                    var12_18 = 16;
                    var9_19 = 0;
                    while (true) {
                        var14_20 = var11_17.substring(var9_19, var9_19 += 8).getBytes("ISO-8859-1");
                        v11 = var13_15;
                        v12 = var10_16++;
                        v13 = ((long)var14_20[0] & 255L) << 56 | ((long)var14_20[1] & 255L) << 48 | ((long)var14_20[2] & 255L) << 40 | ((long)var14_20[3] & 255L) << 32 | ((long)var14_20[4] & 255L) << 24 | ((long)var14_20[5] & 255L) << 16 | ((long)var14_20[6] & 255L) << 8 | (long)var14_20[7] & 255L;
                        v14 = -1;
                        break block22;
                        break;
                    }
lbl84:
                    // 1 sources

                    while (true) {
                        v11[v12] = v15;
                        if (var9_19 < var12_18) ** continue;
                        var11_17 = "\u0088\u00b8I\u00c1\u00cd\u009e>\u00aa\u0013\u0090oE2\nZ\u00f7";
                        var12_18 = 16;
                        var9_19 = 0;
                        while (true) {
                            var14_20 = var11_17.substring(var9_19, var9_19 += 8).getBytes("ISO-8859-1");
                            v11 = var13_15;
                            v12 = var10_16++;
                            v13 = ((long)var14_20[0] & 255L) << 56 | ((long)var14_20[1] & 255L) << 48 | ((long)var14_20[2] & 255L) << 40 | ((long)var14_20[3] & 255L) << 32 | ((long)var14_20[4] & 255L) << 24 | ((long)var14_20[5] & 255L) << 16 | ((long)var14_20[6] & 255L) << 8 | (long)var14_20[7] & 255L;
                            v14 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl97:
                    // 1 sources

                    while (true) {
                        v11[v12] = v15;
                        if (var9_19 < var12_18) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_21 = v13;
                var17_22 = var7_13.doFinal(new byte[]{(byte)(var15_21 >>> 56), (byte)(var15_21 >>> 48), (byte)(var15_21 >>> 40), (byte)(var15_21 >>> 32), (byte)(var15_21 >>> 24), (byte)(var15_21 >>> 16), (byte)(var15_21 >>> 8), (byte)var15_21});
                v15 = ((long)var17_22[0] & 255L) << 56 | ((long)var17_22[1] & 255L) << 48 | ((long)var17_22[2] & 255L) << 40 | ((long)var17_22[3] & 255L) << 32 | ((long)var17_22[4] & 255L) << 24 | ((long)var17_22[5] & 255L) << 16 | ((long)var17_22[6] & 255L) << 8 | (long)var17_22[7] & 255L;
                switch (v14) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl110:
                    // 1 sources

                    ** continue;
                }
            }
            StaffWidget.g = var13_15;
            StaffWidget.h = new Integer[4];
            var2_23 = Cipher.getInstance("DES/CBC/NoPadding");
            v16 = SecretKeyFactory.getInstance("DES");
            v17 = new byte[8];
            v18 = v17;
            v17[0] = (byte)(var27 >>> 56);
            for (var3_24 = 1; var3_24 < 8; ++var3_24) {
                v18 = v18;
                v18[var3_24] = (byte)(var27 << var3_24 * 8 >>> 56);
            }
            break block24;
lbl124:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_23.init(2, (Key)v16.generateSecret(new DESKeySpec(v18)), new IvParameterSpec(new byte[8]));
        var6_26 = var2_23.doFinal(new byte[]{-80, 35, -55, 97, -67, -96, 13, -43});
        ** while (true)
        var0_25 = ((long)var6_26[0] & 255L) << 56 | ((long)var6_26[1] & 255L) << 48 | ((long)var6_26[2] & 255L) << 40 | ((long)var6_26[3] & 255L) << 32 | ((long)var6_26[4] & 255L) << 24 | ((long)var6_26[5] & 255L) << 16 | ((long)var6_26[6] & 255L) << 8 | (long)var6_26[7] & 255L;
        StaffWidget.k = new StaffWidget(var29_1, var30_2, (byte)var31_3);
        StaffWidget.m = new ArrayList<E>();
        StaffWidget.animation = new Animation(Easing.OUT_BACK, var0_25);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6CDF;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/pg", exception);
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
            StaffWidget.c[n2] = StaffWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = StaffWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7A4E;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/pg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            StaffWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = StaffWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(StaffWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(StaffWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

