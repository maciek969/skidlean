/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.Element;
import su.catlean.gui.WaypointPanel;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Waypoint;
import su.catlean.zkm.ZkmKeys;

public final class WaypointElement
extends Element {
    @NotNull
    private Waypoint point;
    @NotNull
    private final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;
    private static final long f;

    public WaypointElement(@NotNull Waypoint waypoint) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(waypoint, (String)((Object)WaypointElement.zkmBootstrap("z", (int)23947, (long)(0x7D3F9F5EDB850B59L ^ l))));
        this.point = waypoint;
        this.animation = new Animation(Easing.OUT_QUINT, f);
    }

    @NotNull
    public final Waypoint getPoint() {
        return this.point;
    }

    public final void setPoint(@NotNull Waypoint waypoint) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(waypoint, (String)((Object)WaypointElement.zkmBootstrap("z", (int)23947, (long)(0x747307C8D85575AFL ^ l))));
        this.point = waypoint;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        v0 = var1_1;
        v1 = v0 ^ 122872324218116L;
        var10_7 = (int)(v1 >>> 48);
        var11_8 = (int)(v1 << 16 >>> 48);
        var12_9 = (int)(v1 << 32 >>> 32);
        var15_10 = v0 ^ 15727589290443L;
        Intrinsics.checkNotNullParameter(var3_2, "context");
        v2 = var4_3 <= var6_5 ? var6_5 <= var4_3 + 70.0f : false;
        if (!v2) ** GOTO lbl-1000
        v3 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 25.0f : false;
        if (v3) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = false;
        }
        var23_11 = v4;
        this.animation.animate(var23_11 != false ? 1.0f : 0.0f);
        if (var23_11) {
            WaypointPanel.INSTANCE.setFocusedElement(this);
        }
        var24_12 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), this.point.isVisible() != false ? 1.0f : 0.0f);
        var25_13 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveModuleOutlineColor(), Theme.INSTANCE.getModuleOutlineColor(), this.point.isVisible() != false ? 1.0f : 0.0f);
        v5 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
        Shapes2D.drawShape$default(v5, (char)var10_7, var4_3 + 4.0f, var5_4, 70.0f, 25.0f, 2.0f, 2.5f + this.animation.getValue(), 1.0f, 1.0f, var24_12, var25_13, 0.0f, (short)var11_8, 0.0f, var12_9, 0.0f, (int)WaypointElement.e, null);
        v6 = Fonts.getSF_M();
        v7 = this.point.getId();
        v8 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v8, "WHITE");
        v6.drawStringWithShadow(var3_2, v7, var15_10, var4_3 + 6.0f, var5_4, v8);
        v9 = Fonts.getSF_M();
        v10 = this.point.getServer();
        v11 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v11, "WHITE");
        v9.drawStringWithShadow(var3_2, v10, var15_10, var4_3 + 6.0f, var5_4 + 11.0f, RenderUtil.INSTANCE.withAlpha(v11, 0.7f));
    }

    @Override
    public boolean isHovered(float f, float f2) {
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        WaypointElement.a = ZkmKeys.a(6490301495294082611L, 5466949418607035862L, MethodHandles.lookup().lookupClass()).a(123498979073157L);
                        WaypointElement.d = new HashMap<K, V>(13);
                        var10 = WaypointElement.a ^ 14556419915057L;
                        var12_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var10 >>> 56);
                        for (var13_2 = 1; var13_2 < 8; ++var13_2) {
                            v2 = v2;
                            v2[var13_2] = (byte)(var10 << var13_2 * 8 >>> 56);
                        }
                        var12_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var19_3 = new String[6];
                        var17_4 = 0;
                        var16_5 = "\u00bb\u0014\u00e5\u007f\u001b*iL\b\u00d7\u008f\u00ab\u0095\u00e0\u00a0\u00e6\u0010\u0017\u00f7gU_\u0087\u0092+\u00cd^.I\u009eB\u0015!\u0010\u0015\u00f5\u00c22\u0088B\u00b5\u00c4\u00c4]\u00fal\u00a5\u00cb@B \u00e7\u00e7\u00b8\t\u008d\u00ce\u0014#EJ\u00ed*\u00f0\u00a3\u0017\u00f0\u00b4y\u000b\u00a4\u00f3#\u00fcoT1G\u00b1\u00fd\u0087\u0085\u00a9";
                        var18_6 = 83;
                        var15_7 = 16;
                        var14_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var14_8;
                            v4 = var16_5.substring(v3, v3 + var15_7);
                            v5 = -1;
                            break block14;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var19_3[var17_4++] = WaypointElement.zkmDecodeUtf8(var20_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\u00e1\u00ab\u00dcY\u0013\u00d9\u0090$%\u00d0g[\u0088\u008e~\u009b\u0010<\u00d0\u0002\u00ac\u00d2\u00e1\u00b2\u00b3'O\u00b5f\u00a1\u00bc\u0007\u00ff";
                            var18_6 = 33;
                            var15_7 = 16;
                            var14_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var14_8;
                                v4 = var16_5.substring(v6, v6 + var15_7);
                                v5 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var19_3[var17_4++] = WaypointElement.zkmDecodeUtf8(var20_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            break block15;
                            break;
                        }
                    }
                    var20_9 = var12_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                WaypointElement.b = var19_3;
                WaypointElement.c = new String[6];
                var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var10 >>> 56);
                for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                    v9 = v9;
                    v9[var6_11] = (byte)(var10 << var6_11 * 8 >>> 56);
                }
                break block16;
lbl65:
                // 1 sources

                while (true) {
                    continue;
                    break;
                }
            }
            var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
            var9_12 = var5_10.doFinal(new byte[]{-33, 86, -118, -8, 120, -74, -44, 12});
            ** while (true)
            WaypointElement.e = ((long)var9_12[0] & 255L) << 56 | ((long)var9_12[1] & 255L) << 48 | ((long)var9_12[2] & 255L) << 40 | ((long)var9_12[3] & 255L) << 32 | ((long)var9_12[4] & 255L) << 24 | ((long)var9_12[5] & 255L) << 16 | ((long)var9_12[6] & 255L) << 8 | (long)var9_12[7] & 255L;
            var0_13 = Cipher.getInstance("DES/CBC/NoPadding");
            v10 = SecretKeyFactory.getInstance("DES");
            v11 = new byte[8];
            v12 = v11;
            v11[0] = (byte)(var10 >>> 56);
            for (var1_14 = 1; var1_14 < 8; ++var1_14) {
                v12 = v12;
                v12[var1_14] = (byte)(var10 << var1_14 * 8 >>> 56);
            }
            break block17;
lbl82:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_13.init(2, (Key)v10.generateSecret(new DESKeySpec(v12)), new IvParameterSpec(new byte[8]));
        var4_15 = var0_13.doFinal(new byte[]{1, -20, 29, -20, -109, -99, -96, -70});
        ** while (true)
        WaypointElement.f = ((long)var4_15[0] & 255L) << 56 | ((long)var4_15[1] & 255L) << 48 | ((long)var4_15[2] & 255L) << 40 | ((long)var4_15[3] & 255L) << 32 | ((long)var4_15[4] & 255L) << 24 | ((long)var4_15[5] & 255L) << 16 | ((long)var4_15[6] & 255L) << 8 | (long)var4_15[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4D8F;
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
                throw new RuntimeException("su/catlean/xp", exception);
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
            WaypointElement.c[n2] = WaypointElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WaypointElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WaypointElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

