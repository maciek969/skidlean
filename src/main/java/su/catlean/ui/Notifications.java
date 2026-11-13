/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.ui;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
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
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.gofra.Flow;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.sound.Sounds;
import su.catlean.ui.Notification;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications$WhenMappings;
import su.catlean.ui.hud.NotificationsWidget;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.zkm.ZkmKeys;

public final class Notifications
implements Manager {
    @NotNull
    public static final Notifications INSTANCE;
    @NotNull
    private static final List notifications;
    @NotNull
    private static final RenderTarget u;
    @NotNull
    private static final RenderTarget F;
    @NotNull
    private static final RenderTarget l;
    @NotNull
    private static final RenderTarget g;
    @NotNull
    private static final RenderTarget B;
    @NotNull
    private static final RenderTarget n;
    private static boolean w;
    private static int O;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private Notifications() {
    }

    @NotNull
    public final RenderTarget C() {
        return u;
    }

    @NotNull
    public final RenderTarget B() {
        return F;
    }

    @NotNull
    public final RenderTarget L() {
        return l;
    }

    @NotNull
    public final RenderTarget A() {
        return g;
    }

    @NotNull
    public final RenderTarget x() {
        return B;
    }

    @NotNull
    public final RenderTarget g() {
        return n;
    }

    public final boolean u() {
        return w;
    }

    public final void t(boolean bl) {
        w = bl;
    }

    public final void push(@NotNull String string, @NotNull String string2, int n, @NotNull NotificationType notificationType, @Nullable ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(string, "title");
        Intrinsics.checkNotNullParameter(string2, "content");
        Intrinsics.checkNotNullParameter((Object)notificationType, "type");
        if (!NotificationsWidget.n.getEnabled() || w) {
            return;
        }
        if (Globals.getMc().player != null) {
            Globals.getMc().execute(() -> Notifications.push$lambda$0(string, string2, notificationType, n, itemStack));
        }
    }

    public static void push$default(Notifications notifications, String string, String string2, int n, NotificationType notificationType, ItemStack itemStack, int n2, Object object) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        long l = ((long)n3 << 32 | (long)n4 << 40 >>> 32 | (long)n5 << 56 >>> 56) ^ a;
        if ((n2 & Notifications.zkmBootstrap2("x", (int)12404, (long)(0x15901EBD7C4B738L ^ l))) != 0) {
            itemStack = null;
        }
        notifications.push(string, string2, n, notificationType, itemStack);
    }

    @Flow
    public final void onRender2D(@NotNull Render2DEvent render2DEvent) {
        Object object;
        long l = a ^ 0x4331C4C9B046L;
        long l2 = l ^ 0xFF0C74B691L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 48);
        int n3 = (int)(l2 << 32 >>> 32);
        Intrinsics.checkNotNullParameter(render2DEvent, "e");
        float f = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f + 25.0f;
        if (notifications.size() > 8) {
            notifications.removeFirst();
        }
        Iterator iterator2 = Lists.newArrayList((Iterable)notifications).iterator();
        Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
        Iterator iterator3 = iterator2;
        while (iterator3.hasNext()) {
            Object e = iterator3.next();
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            object = (Notification)e;
            this.renderNotification((Notification)object, (short)n, render2DEvent.getContext(), f + (float)(notifications.size() * 16), (char)n2, n3);
            f -= 16.0f;
        }
        object = notifications;
        synchronized (object) {
            notifications.removeIf(arg_0 -> Notifications.invokePredicate(Notifications::onRender2D$lambda$0, arg_0));
        }
    }

    private final void renderNotification(Notification notification, short s, DrawContext drawContext, float f, char c, int n) {
        long l;
        long l2 = l = ((long)s << 48 | (long)c << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x61F69C0CA734L;
        int n2 = (int)(l3 >>> 48);
        int n3 = (int)(l3 << 16 >>> 48);
        int n4 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x7B306F0BFBL;
        long l5 = l2 ^ 0x6536BAE8C623L;
        long l6 = l2 ^ 0x831DC871B23L;
        notification.getAnimation().animate(notification.getTimer().hasPassed(notification.getTime() - 350) ? 0.0f : 1.0f);
        if ((double)notification.getAnimation().getValue() < 0.01) {
            return;
        }
        notification.L(this.smooth(notification.w(), f));
        float f2 = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - notification.a() / 2.0f;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Matrix3x2fStack matrix3x2fStack2 = matrix3x2fStack;
        matrix3x2fStack2.pushMatrix();
        matrix3x2fStack2.translate(f2 + notification.a() / 2.0f, notification.w() + 6.5f);
        matrix3x2fStack2.scale(notification.getAnimation().getValue(), notification.getAnimation().getValue());
        matrix3x2fStack2.translate(-(f2 + notification.a() / 2.0f), -(notification.w() + 6.5f));
        Shapes2D.drawShape$default(matrix3x2fStack2, (char)n2, f2, notification.w(), notification.a(), 13.0f, 3.0f, 1.5f, 0.0f, notification.getAnimation().getValue(), Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n3, 0.0f, n4, 0.0f, 14336, null);
        Color[] colorArray = new Color[1];
        float f3 = notification.getAnimation().getValue() * 0.1f;
        colorArray[0] = RenderUtil.INSTANCE.withAlpha(new Color(0x44FFFFFF, true), f3);
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f2 + 14.0f, notification.w() + 2.0f, 0.5f, 9.0f, colorArray, l6);
        float f4 = notification.getAnimation().getValue();
        Fonts.getSF_M().drawStringWithShadow(drawContext, (String)(notification.getItem() == null ? notification.getTitle() + notification.getMessage() : notification.getMessage()), l4, f2 + 16.0f, notification.w() + 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f4));
        if (notification.getItem() == null) {
            RenderTarget.drawRoundedText$default(notification.getRenderTarget(), (Matrix3x2f)matrix3x2fStack2, (double)f2 + 2.0, (double)notification.w() + 0.5, 0.0f, 0.0f, null, null, 0.0f, 248, l5, null);
        } else {
            drawContext.getMatrices().translate(f2 + 2.0f, notification.w() + 1.0f);
            drawContext.getMatrices().scale(0.65f, 0.65f);
            drawContext.drawItem(notification.getItem(), 0, 0);
            drawContext.getMatrices().scale(1.0f, 1.0f);
            drawContext.getMatrices().translate(-(f2 + 2.0f), -(notification.w() + 1.0f));
        }
        matrix3x2fStack2.popMatrix();
    }

    private final float smooth(float f, float f2) {
        return f + (f2 - f) / 8.0f;
    }

    private static final void push$lambda$0(String string, String string2, NotificationType notificationType, int n, ItemStack itemStack) {
        long l = a ^ 0x47BBF964F0DAL;
        long l2 = l ^ 0x7C8C9BAC1F35L;
        long l3 = l2 >>> 32;
        int n2 = (int)(l2 << 32 >>> 32);
        notifications.add(new Notification(string, string2, notificationType, l3, n * 1600, n2, itemStack));
        switch (Notifications$WhenMappings.M[notificationType.ordinal()]) {
            case 1: {
                Sounds.INSTANCE.getDisable().play();
                break;
            }
            case 2: {
                Sounds.INSTANCE.getEnable().play();
                break;
            }
        }
    }

    private static final boolean onRender2D$lambda$0(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "it");
        return notification.isExpired();
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Notifications.a = ZkmKeys.a(-7762248569282793520L, 4859372621254086896L, MethodHandles.lookup().lookupClass()).a(160804293708231L);
                        var20 = Notifications.a ^ 15786382409512L;
                        Notifications.d = new HashMap<K, V>(13);
                        Notifications.zkmSetFlag(0);
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
                        var18_3 = new String[13];
                        var16_4 = 0;
                        var15_5 = "V\u00ac\u00c7\u00bd\u00c5\u00af\u000b\t\u00cf`\u0090\u00bc~\u00fc\u00a0\u0090\u0010T\u0015\u0015@\u001b\u0003^\u001a\u00cbT\u0099Z\u0002\u00cd\u00aa\u008e\u0010&BU\u00b8Z?(\u00a5`\u00f5\u00d6\u00bd\\!\u00fdP\u0018S\u0087\u00ad\u00c0\u008aJ\u00ad\u00d6\u00a8\u0086[\u00a3R\u0084U\u00f7\u00ec\u00b7l\u0015\u00dd\u001d\u00d0\u009e\u0010\u009a\u00d0\u001e\u00be\u00f7>\u00f3\u009e\u00bc\u00b8\u0083f\u00079\u00e0\u00ad\u0010\u0011\u0097\u00bb\u00bc\u0017\u00d1\u000f\u00feZ\u00a7\u0097\u00f1\u00ce\u001e\u008d^\u0010\u0014\u001f\u0003\u00b9\u00df\u00eeo\u00d8B\u00e7\u001cX\u00e2l\u001e\u00b2\u0010\u00d2\u008b\u00fb\u00c7\u00ff\u00a6=\u008a.\u00a7\u00e3D\u00dd\u00ea/\u00b0 \u00d7a\u00af\u00acF\u00b1\u009d\u000b\u00fa\u00d2\u00826\u00d90\u00cf\u00d8\u00b6\"\u0080\u0001\u00fef}|u4\u00f7\u00f0\u00e9\u00ec\u00d0\u00d7\u0010\u00c8\u00ceM6\u00d5\u00d6\u00dcj\u0000N\u00df\u000e\u00dc\u0017\u0089\u00cd \u00e6\u00d6\u0094\u00d4\u00aa\u00d3/T\\O\u007f\u00ec\u00d0\u00e1\u00ba\u00f9\u00d9H1\u001d1\u009e\u009d\u00d2k\u0098\u0080\f\u0096\u00d6\u00d5W";
                        var17_6 = 226;
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
                            var18_3[var16_4++] = Notifications.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "b\u00fb\u00b1\u00ae\u001c\u00cf\u00a8\u00efA\u008e)\u0087\u00a5<\u00ef\u00ce\u00108\u0084\u0013\u0010d\u00f2t:\u00b6M\u0014\u00d1k$ \u00bc";
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
                            var18_3[var16_4++] = Notifications.zkmDecodeUtf8(var19_9).intern();
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
                Notifications.b = var18_3;
                Notifications.c = new String[13];
                Notifications.h = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "6\u00db\u00bd\u00a4L\u00067\u009afFc\by\u00ee\u008e[\u008b\u00a9\u00d5\u00ce\u008f\u0084\u0004.[\u0085G-'C\u00fd\u00f4\u00fdIG\u001ct\u0087\u00b0\u009e\u0091n4I\r\u00af\u001e\u0005Y8\u0003\u00d6b(~\u001fil\u00b0\u0088\u001d\u00ba\u00ed xq`\u00a2_{\u00c9.;n/I\"\u00f4(E";
                var5_15 = 80;
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
                    var4_14 = "\u00d5\"r&}\u0012\u00d2Y<R[Y\u00dc\u00a6\u00f0o";
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
        Notifications.e = var6_12;
        Notifications.f = new Integer[12];
        Notifications.INSTANCE = new Notifications();
        Notifications.notifications = new ArrayList<E>();
        Notifications.u = new RenderTarget("on", 9, 9);
        Notifications.F = new RenderTarget("off", 9, 9);
        Notifications.l = new RenderTarget("warning", 9, 9);
        Notifications.g = new RenderTarget("info", 9, 9);
        Notifications.B = new RenderTarget("success", 9, 9);
        Notifications.n = new RenderTarget("error", 9, 9);
    }

    public static void zkmSetFlag(int n) {
        O = n;
    }

    public static int zkmGetFlag() {
        return O;
    }

    public static int zkmIntConstant() {
        return 90;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2336;
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
                throw new RuntimeException("su/catlean/sg", exception);
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
            Notifications.c[n2] = Notifications.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Notifications.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/sg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x67C2;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/sg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Notifications.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Notifications.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/sg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Notifications.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Notifications.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

