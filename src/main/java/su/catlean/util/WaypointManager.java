/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.util;

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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.config.ConfigStore;
import su.catlean.gofra.Flow;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.Waypoint;
import su.catlean.zkm.ZkmKeys;

public final class WaypointManager {
    @NotNull
    public static final WaypointManager INSTANCE;
    @NotNull
    private static final RenderTarget renderTarget;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private WaypointManager() {
    }

    @NotNull
    public final List getWaypoints() {
        return ConfigStore.INSTANCE.getWaypointListFile().getData().getWayPoints();
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l;
        long l2 = l = b ^ 0x1AC282A5328FL;
        long l3 = l2 ^ 0x64C4ABE43820L;
        long l4 = l2 ^ 0x1F2C35D83942L;
        Iterable iterable = this.getWaypoints();
        for (Object t : iterable) {
            Waypoint waypoint = (Waypoint)t;
            if (!INSTANCE.isVisibleHere(waypoint)) continue;
            Vec3d vec3d = new Vec3d(waypoint.getX(), waypoint.getY(), waypoint.getZ());
            Integer n = Globals.getMc().options.getFov().getValue();
            Intrinsics.checkNotNullExpressionValue(n, "get(...)");
            Vec3d vec3d2 = vec3d;
            int n2 = ((Number)n).intValue();
            if (!MovementUtil.INSTANCE.getYawTo(vec3d2, n2)) continue;
            Vec3d vec3d3 = RenderUtil.INSTANCE.toCameraRelative(vec3d);
            Fonts.getSF_B2().drawStringCentered(render2DEvent.getContext(), waypoint.getId() + " (" + (int)EntityUtil.getPos(Globals.getPlayer()).distanceTo(vec3d) + "m)", (float)vec3d3.x, (float)vec3d3.y, Theme.INSTANCE.getTextColor(), l3);
            Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            RenderTarget.drawRoundedText$default(renderTarget, (Matrix3x2f)matrix3x2fStack, vec3d3.x - 10.0, vec3d3.y - 25.0, 0.0f, 0.0f, null, null, 0.0f, 248, l4, null);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isVisibleHere(Waypoint waypoint) {
        if (!waypoint.isVisible()) return false;
        if (!Intrinsics.areEqual(waypoint.getServer(), "localhost")) {
            Object object;
            if (!Intrinsics.areEqual(waypoint.getServer(), (object = Globals.getMc().getNetworkHandler()) != null && (object = ((ClientPlayNetworkHandler)object).getServerInfo()) != null ? ((ServerInfo)object).address : null)) return false;
        }
        int n = waypoint.getDimension();
        String string = Globals.getWorld().getRegistryKey().getValue().getPath();
        Intrinsics.checkNotNullExpressionValue(string, "getPath(...)");
        if (n != this.getDimensionId(string)) return false;
        return true;
    }

    public final boolean removeWaypoint(@NotNull Waypoint waypoint) {
        Intrinsics.checkNotNullParameter(waypoint, "wayPoint");
        return this.getWaypoints().remove(waypoint);
    }

    public final boolean addWaypoint(@NotNull Waypoint waypoint) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ b;
        Intrinsics.checkNotNullParameter(waypoint, (String)((Object)WaypointManager.zkmBootstrap("s", (int)23695, (long)(0x12438679A6333293L ^ l))));
        return this.getWaypoints().add(waypoint);
    }

    public final int getDimensionId(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "i");
        String string2 = string;
        return Intrinsics.areEqual(string2, "overworld") ? 0 : (Intrinsics.areEqual(string2, "the_nether") ? 1 : 2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        WaypointManager.b = ZkmKeys.a(6819336676105349059L, -3568924390718009616L, MethodHandles.lookup().lookupClass()).a(97183731294217L);
                        var20 = WaypointManager.b ^ 19796028894514L;
                        WaypointManager.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[11];
                        var16_4 = 0;
                        var15_5 = "\u00bd\u009b\u00dc\u00bb~\u00e8M\u00d7LI\u00b5\u0004\u00bc\u0086\u00a7\u00c3y\u00a67c\u008f3+'\u0018E\u00f0\u00a10\u00f9\u00bb0\u00a7\u000e\u00a9,#\u00110YY;Oh@xe?\u00ec \u00e9-\u0083\u001ck\u00d0+W\u00c0\u00b1\u00ecq\u00aa\u0016z\u00b0W\u0003\u00ca\u0097\u00ce\u0016A\u001d]\u00e8\u0018\u00eao\fB\u00e3\u0010J\u00c6`u\u00cfb\u0001.\u0098P\u0002\u0000J\u00ed\u001c\u0090 q\u00bb\u0084\u00dbh\u00b3\u00e0F\u00df\u00e4(?\u00bf;\u009c:S0\u00b4\u00e7}\u00dc\u00efc\u00e5\u00e5\u00bfh\u00ca\u00c3\u0014\u00b6\u0018\u0084_\u00b0\u0003h\u00d2\u0005\u00a5d\u00b7\u00ce\u00ca\u00d6\u0099\u00d0LS5\u0093q\u00df\u00c6\u00d3\u00af\u0018\u008d\u0007\u00f9\u00ff\u00be\u00cc\u00bc\u00c9\u00b6i\u0081\u0000?\u00d4\u000f\u00a7\u00faD\u0090(Z\u0003\u0080\u00ce G\u0097\f\u009a\u00a7R\u009b\u00b9\u0018C\u00ec\u00ec\u00888\u00cdt\u0004\u00d01\u000e\u001ft\u008cW?\u0083G\u00c3\u00cb\u008c>\u008d \u0000\u008b(\u0014i\u00c6:\u009a\u00a2\u0013\u00b2\u00c7\u00d1\b\u0004\u00c0\nUl\u00a7\u0097\u00f2\u00e7:b\u0082\u009elq\u0083\u00ce\u0011";
                        var17_6 = 248;
                        var14_7 = 24;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = WaypointManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0013J\u00aa\u00ae\u00a9\u0085\u00f4Q\u00b6\u000et\u00ae\u00e9\u000e\u001b_\u0010\u0016J\u0017\u00d9\u0095\u00dd\u00a8\u00d1\u0011\u0084\u00c4\n\u00cd\u0092cS";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = WaypointManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                WaypointManager.c = var18_3;
                WaypointManager.d = new String[11];
                WaypointManager.h = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "@\u0081_W!\u00a0\u0081\u0018\u00aar\n;q\u00a9\u00f8\u009b\u00d5\u00c5\u008dw\u0004\u00a3\u0000x";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        WaypointManager.f = var6_12;
        WaypointManager.g = new Integer[3];
        WaypointManager.INSTANCE = new WaypointManager();
        WaypointManager.renderTarget = new RenderTarget("point", 20, 20);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x535A;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/iu", exception);
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
            WaypointManager.d[n2] = WaypointManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WaypointManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/iu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x21AA;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/iu", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            WaypointManager.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = WaypointManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/iu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WaypointManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(WaypointManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

