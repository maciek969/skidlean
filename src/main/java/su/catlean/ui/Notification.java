/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui;

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
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.RenderTarget;
import su.catlean.render.font.Fonts;
import su.catlean.ui.Notification$WhenMappings;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

final class Notification {
    @NotNull
    private final String title;
    @NotNull
    private final String message;
    @NotNull
    private final NotificationType type;
    private final int time;
    @Nullable
    private final ItemStack item;
    private float K;
    @NotNull
    private final Animation animation;
    private final float j;
    @NotNull
    private final Timer timer;
    @NotNull
    private final RenderTarget renderTarget;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public Notification(@NotNull String string, @NotNull String string2, @NotNull NotificationType notificationType, long l, int n, int n2, @Nullable ItemStack itemStack) {
        long l2 = (l << 32 | (long)n2 << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x963D1F6C0B5L;
        Intrinsics.checkNotNullParameter(string, "title");
        Intrinsics.checkNotNullParameter(string2, "message");
        Intrinsics.checkNotNullParameter((Object)notificationType, "type");
        this.title = string;
        this.message = string2;
        this.type = notificationType;
        this.time = n;
        this.item = itemStack;
        this.K = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f + 41.0f;
        this.animation = new Animation(Easing.OUT_BACK, h);
        this.j = Fonts.getSF_M().getWidth(l3, (String)(this.item == null ? this.title + " " + this.message : this.message)) + 20.0f;
        this.timer = new Timer();
        this.renderTarget = switch (Notification$WhenMappings.L[this.type.ordinal()]) {
            case 1 -> Notifications.INSTANCE.A();
            case 2 -> Notifications.INSTANCE.C();
            case 3 -> Notifications.INSTANCE.B();
            case 4 -> Notifications.INSTANCE.g();
            case 5 -> Notifications.INSTANCE.L();
            case 6 -> Notifications.INSTANCE.x();
            default -> throw new NoWhenBranchMatchedException();
        };
        if (n2 <= 0) {
            // empty if block
        }
    }

    public Notification(long l, String string, String string2, NotificationType notificationType, int n, ItemStack itemStack, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        long l2 = (l = a ^ l) ^ 0x4E8D3292EE34L;
        long l3 = l2 >>> 32;
        int n3 = (int)(l2 << 32 >>> 32);
        if ((n2 & Notification.zkmBootstrap2("j", (int)118, (long)(0x7AF5070CFD2F0182L ^ l))) != 0) {
            itemStack = null;
        }
        this(string, string2, notificationType, l3, n, n3, itemStack);
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final NotificationType getType() {
        return this.type;
    }

    public final int getTime() {
        return this.time;
    }

    @Nullable
    public final ItemStack getItem() {
        return this.item;
    }

    public final float w() {
        return this.K;
    }

    public final void L(float f) {
        this.K = f;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    public final float a() {
        return this.j;
    }

    @NotNull
    public final Timer getTimer() {
        return this.timer;
    }

    @NotNull
    public final RenderTarget getRenderTarget() {
        return this.renderTarget;
    }

    public final boolean isExpired() {
        return this.timer.hasPassed(this.time);
    }

    @NotNull
    public final String getTitle2() {
        return this.title;
    }

    @NotNull
    public final String getMessage2() {
        return this.message;
    }

    @NotNull
    public final NotificationType getType2() {
        return this.type;
    }

    public final int getTime2() {
        return this.time;
    }

    @Nullable
    public final ItemStack getItem2() {
        return this.item;
    }

    @NotNull
    public final Notification q(long l, @NotNull String string, @NotNull String string2, @NotNull NotificationType notificationType, int n, @Nullable ItemStack itemStack) {
        long l2 = (l = a ^ l) ^ 0x39A93E01779CL;
        long l3 = l2 >>> 32;
        int n2 = (int)(l2 << 32 >>> 32);
        Intrinsics.checkNotNullParameter(string, (String)((Object)Notification.zkmBootstrap("v", (int)21046, (long)(0x1DF4EAA66D34EC42L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)Notification.zkmBootstrap("v", (int)32267, (long)(0x3DEDD3121DDB407EL ^ l))));
        Intrinsics.checkNotNullParameter((Object)notificationType, (String)((Object)Notification.zkmBootstrap("v", (int)14348, (long)(0x55CB256F17D5867FL ^ l))));
        return new Notification(string, string2, notificationType, l3, n, n2, itemStack);
    }

    public static Notification U(Notification notification, String string, String string2, NotificationType notificationType, int n, long l, ItemStack itemStack, int n2, Object object) {
        long l2 = (l = a ^ l) ^ 0x2AA875A78B5L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-3304378407952827617L, (long)l);
        int n3 = n2 & 1;
        if (callSite != null) {
            if (n3 != 0) {
                string = notification.title;
            }
            n3 = n2 & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 != null) {
                if (n3 != 0) {
                    string2 = notification.message;
                }
                n3 = n2 & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n3 != 0) {
                    notificationType = notification.type;
                }
                n3 = n2 & Notification.zkmBootstrap2("j", (int)3319, (long)(0x3914EF4EF7608168L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) {
            if (n3 != 0) {
                n = notification.time;
            }
            n3 = n2 & Notification.zkmBootstrap2("j", (int)11048, (long)(0x4D9E1753D81526B6L ^ l));
        }
        if (n3 != 0) {
            itemStack = notification.item;
        }
        return notification.q(l2, string, string2, notificationType, n, itemStack);
    }

    @NotNull
    public String toString() {
        ItemStack itemStack = this.item;
        int n = this.time;
        NotificationType notificationType = this.type;
        String string = this.message;
        String string2 = this.title;
        return "Notification(title=" + string2 + ", message=" + string + ", type=" + notificationType + ", time=" + n + ", item=" + itemStack + ")";
    }

    public int hashCode() {
        int n = this.title.hashCode();
        n = n * 31 + this.message.hashCode();
        n = n * 31 + this.type.hashCode();
        n = n * 31 + Integer.hashCode(this.time);
        n = n * 31 + (this.item == null ? 0 : this.item.hashCode());
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification notification = (Notification)object;
        if (!Intrinsics.areEqual(this.title, notification.title)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.message, notification.message)) {
            return false;
        }
        if (this.type != notification.type) {
            return false;
        }
        if (this.time != notification.time) {
            return false;
        }
        return Intrinsics.areEqual(this.item, notification.item);
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
                            Notification.a = ZkmKeys.a(-434578291666565723L, -4364688058005122557L, MethodHandles.lookup().lookupClass()).a(115184548285271L);
                            Notification.d = new HashMap<K, V>(13);
                            var16 = Notification.a ^ 59311624386931L;
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
                            var25_3 = new String[11];
                            var23_4 = 0;
                            var22_5 = "\u0086\u00be\u00a0\u00d4\u00ad\u00a6\u001c\u00a4\u00e4\u00ea\u0085\u008e\u00c1mF*\u0010\u00e6_\u009e\u0002\u00f0\u0098/\u00cc\u00a3\u00e9\u0083\u00eb#z\u00a3Y\u0010\u00e9\":\u00c3d\u00f0'_WP\u00c5b\u00c6\u0085\u008a,\u0010B\u00c4K\u0094\u00e5i\u009d\u00be\u00d4=cv!R{\u00cb\u0010\u0014\u00be\u0001v\u008cm\u00ddX3\u00cb\u00fa\u008c)\u00fc\u00acr\u0010VT*XK\u0084\u0098\u00ecp\u008b\u00fa \u00da\n\u00c2\u00da(\u00e7W\u0001T\u0084\u00cb\u00af\u00d1\u00e8J\tJ\u0007,\u008f\u0005h\u001c1\u000f\u00c9\u00fb\u00d6\u00daBpS\u0010\u00fcr\u00da)\u009e\u00c6\u00deXp[\u0093\u00fa\u0018\u00d1\u00eb\u00bc\\\u00f2$\u00bbr\u00e5\u00c1?\u00c4y\u00c6\u0001\u0006\u00fe\u00d2<\u0016\u00a3\u00a8\u00b4,\u00103\u0087\u00d3\u00b7\u00fa\u008b-\to\nX\u0095\n\u0019V\u00c7";
                            var24_6 = 184;
                            var21_7 = 16;
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
                                var25_3[var23_4++] = Notification.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00fe\u00e5kx\u0081\u00d6\u0089\u00aa\u00f5\u000b\u0083S\u0099D\r|\u0010\u00ea'\u00b5\u00c9t\u00df\u00ab\u0017\u0012\u0015\u00b3\u00c1\u0080n\u00d8p";
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
                                var25_3[var23_4++] = Notification.zkmDecodeUtf8(var26_9).intern();
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
                    Notification.b = var25_3;
                    Notification.c = new String[11];
                    Notification.g = new HashMap<K, V>(13);
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
                    var11_12 = new long[5];
                    var8_13 = 0;
                    var9_14 = "!y4.Y-\u0015\u0015\u0092\u00f8\u00d1\u0081]9\u0088\u00d4\u0094yBe\u009da\u00ee\u00c3";
                    var10_15 = 24;
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
                        var9_14 = "\u00a3\u0014\u00f7\u0018\u0011'O^\u009a\u00d7m\u00b1\u00dc?\u00f9\u00c3";
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
            Notification.e = var11_12;
            Notification.f = new Integer[5];
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
        var4_22 = var0_20.doFinal(new byte[]{-46, 22, 92, 50, 105, -128, 0, 83});
        ** while (true)
        Notification.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x784F;
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
                throw new RuntimeException("su/catlean/l1", exception);
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
            Notification.c[n2] = Notification.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Notification.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/l1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5E60;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/l1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Notification.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Notification.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/l1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Notification.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Notification.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

