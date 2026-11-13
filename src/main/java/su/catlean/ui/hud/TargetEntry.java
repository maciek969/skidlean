/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.ui.ArrayListEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TargetEntry {
    @NotNull
    private final PlayerEntity entity;
    @NotNull
    private final Animation animation;
    private boolean removing;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public TargetEntry(@NotNull PlayerEntity playerEntity, @NotNull Animation animation, boolean bl) {
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.entity = playerEntity;
        this.animation = animation;
        this.removing = bl;
    }

    public /* synthetic */ TargetEntry(PlayerEntity playerEntity, Animation animation, boolean bl, DefaultConstructorMarker defaultConstructorMarker) {
        animation = new Animation(Easing.OUT_QUINT, h);
        bl = false;
        this(playerEntity, animation, bl);
    }

    @NotNull
    public final PlayerEntity getEntity() {
        return this.entity;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    public final boolean isRemoving() {
        return this.removing;
    }

    public final void setRemoving(boolean bl) {
        this.removing = bl;
    }

    @NotNull
    public final String getInfoText(long l) {
        int n = (int)(((l = a ^ l) ^ 0x5C1BC6B6D8BFL) << 48 >>> 48);
        PlayerEntity playerEntity = this.entity;
        short s = (short)n;
        int n2 = ServerInfo.INSTANCE.getPing(s, playerEntity);
        int n3 = MathKt.roundToInt(Globals.getPlayer().distanceTo(this.entity));
        int n4 = MathKt.roundToInt(this.entity.getHealth() + this.entity.getAbsorptionAmount());
        Formatting formatting = Formatting.RESET;
        String string = this.entity.getGameProfile().name();
        return (Friends.getIsHomie(this.entity) ? Formatting.GREEN : Formatting.WHITE) + string + formatting + " | " + n4 + " hp | " + n3 + " m | " + n2 + " ms";
    }

    @NotNull
    public final PlayerEntity getEntity2() {
        return this.entity;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    public final boolean isRemoving2() {
        return this.removing;
    }

    @NotNull
    public final TargetEntry copy(@NotNull PlayerEntity playerEntity, @NotNull Animation animation, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(playerEntity, (String)((Object)TargetEntry.zkmBootstrap("d", (int)2910, (long)(0x6D312338AB606BD6L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)TargetEntry.zkmBootstrap("d", (int)24358, (long)(0x38893AA764FA3FAFL ^ l))));
        return new TargetEntry(playerEntity, animation, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ TargetEntry copy$default(TargetEntry targetEntry, long l, PlayerEntity playerEntity, Animation animation, boolean bl, int n, Object object) {
        boolean bl2;
        block9: {
            boolean bl3;
            block8: {
                void var6_6;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7929407075920700793L, (long)l);
                bl3 = var6_6 & 1;
                if (callSite != false) {
                    if (bl3) {
                        playerEntity = targetEntry.entity;
                    }
                    bl3 = var6_6 & 2;
                }
                CallSite callSite2 = callSite;
                if (l >= 0L) {
                    if (callSite2 != false) {
                        if (bl3) {
                            animation = targetEntry.animation;
                        }
                        bl3 = var6_6 & 4;
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == false) break block8;
                if (!bl3) break block9;
                bl3 = targetEntry.removing;
            }
            bl2 = bl3;
        }
        return targetEntry.copy(playerEntity, animation, bl2);
    }

    @NotNull
    public String toString() {
        boolean bl = this.removing;
        Animation animation = this.animation;
        PlayerEntity playerEntity = this.entity;
        return "PlayerHolder(entity=" + playerEntity + ", animation=" + animation + ", removing=" + bl + ")";
    }

    public int hashCode() {
        int n = this.entity.hashCode();
        n = n * 31 + this.animation.hashCode();
        n = n * 31 + Boolean.hashCode(this.removing);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = ArrayListEntry.u();
        Object object2 = this;
        if (!bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (!bl) {
            if (!(object2 instanceof TargetEntry)) {
                return false;
            }
            object2 = object;
        }
        TargetEntry targetEntry = (TargetEntry)object2;
        boolean bl2 = Intrinsics.areEqual(this.entity, targetEntry.entity);
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.animation, targetEntry.animation);
        }
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.removing;
        }
        if (!bl) {
            if (bl2 != targetEntry.removing) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block19: {
            block18: {
                block17: {
                    block16: {
                        block15: {
                            TargetEntry.a = ZkmKeys.a(8843209845161219949L, -4472365921686497638L, MethodHandles.lookup().lookupClass()).a(59252553739925L);
                            TargetEntry.d = new HashMap<K, V>(13);
                            var16 = TargetEntry.a ^ 139872360388450L;
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
                            var22_5 = "\u0081\u00dc~\u00c4i*\u00d3~5/\u00b8z\u00ea\u0094U\u00a7\u0010\u00cf\r\u008c\u00c4\u00e3-\u0082\u0094\u008fw\u00c5\u00cbm\u00cfop(\u00f4\u00d7;4\u0091%\u00a8\u00da\u0011\u0018\u00e94\u001e5\u00dd'\u00a9\u00b7UQ\u00ef\u00fe3\u00d5\u000b\u009e\u00d9\u00fe\u0088\u00056\u00d6j\u000bs\u001b\u00e7\u00df\u00ac\u000e\u0018g&\u008dI`x\u00dc\u00c4Z8\u00e1eF\u00f9b\u00cf\u00e1\u0001j\u001b\u00e5\u0002_v\u0010rQ\u0096\u00c7\u008bSdt\u009bo\u009f\u008b\f\u0084\u0005a\u0018\u0004W\u00f6]\u00b0N\u00b8s\u0014\u00d6\u0089\u00bf\u00b58x\u0012\u0090\u00bfW\u008c\u00f6,\u00ac\u0014\u0010\u009fK\u00df+\u00e6t]1^)M\u00b8\u0080\u00dc\u00ee\u00c9\u0010p\u00b7\u00c1\u0098\u00e4p\u00fc,\n+t4\u00fa\u0084\u009b( \u00ec\u0019g\u000eO\u00d3\u00bf_O\u00b8\u000e\u00fc\u00e6\u00a6\u008d\u00fd\u00cd\u00e8\u0098\u00c2Z!yRfP5Vv\u00b2B\u00f0";
                            var24_6 = 208;
                            var21_7 = 16;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block15;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = TargetEntry.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "F1\u00caR\u00ecs\u00c3\u0091\\mb\u00f9\u00af=\u00e5\u0092\u008b\u008b\u0014\u00fd\u00e5j\u00f4\u00bez\u00ae6\u009f\u00d0\u009f\u0007n\u0010>M\u00d8\u00a6\u00ae\u00e8\u00a1\u0018\u0018;P\u00b7\u00cc\u000f\u00b9\u00db";
                                var24_6 = 49;
                                var21_7 = 32;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block15;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = TargetEntry.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block16;
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
                    TargetEntry.b = var25_3;
                    TargetEntry.c = new String[11];
                    TargetEntry.g = new HashMap<K, V>(13);
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
                    var11_12 = new long[2];
                    var8_13 = 0;
                    var9_14 = "\u00efE\u00a2\u00b7\u0093\u00e0bW\u00a8}b\u00fe\u00acd^o";
                    var10_15 = 16;
                    var7_16 = 0;
                    while (true) {
                        break block17;
                        break;
                    }
lbl73:
                    // 1 sources

                    while (true) {
                        var11_12[v10] = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                        if (var7_16 < var10_15) ** continue;
                        break block18;
                        break;
                    }
                }
                var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                v10 = var8_13++;
                var13_18 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                ** while (true)
            }
            TargetEntry.e = var11_12;
            TargetEntry.f = new Integer[2];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v11 = SecretKeyFactory.getInstance("DES");
            v12 = new byte[8];
            v13 = v12;
            v12[0] = (byte)(var16 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v13 = v13;
                v13[var1_21] = (byte)(var16 << var1_21 * 8 >>> 56);
            }
            break block19;
lbl96:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v11.generateSecret(new DESKeySpec(v13)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-23, -121, 44, 61, -74, -85, -50, 3});
        ** while (true)
        TargetEntry.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x25A4;
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
                throw new RuntimeException("su/catlean/jv", exception);
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
            TargetEntry.c[n2] = TargetEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TargetEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D49;
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
                throw new RuntimeException("su/catlean/jv", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TargetEntry.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TargetEntry.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/jv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TargetEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TargetEntry.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

