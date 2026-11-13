/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class MovementInput {
    private final float yaw;
    private final float pitch;
    private final boolean forward;
    private final boolean back;
    private final boolean left;
    private final boolean right;
    private final boolean sneak;
    private final boolean jump;
    private final boolean use;
    private final boolean attack;
    private static String H;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    public MovementInput(float f, float f2, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8) {
        this.yaw = f;
        this.pitch = f2;
        this.forward = bl;
        this.back = bl2;
        this.left = bl3;
        this.right = bl4;
        this.sneak = bl5;
        this.jump = bl6;
        this.use = bl7;
        this.attack = bl8;
    }

    public final float getYaw() {
        return this.yaw;
    }

    public final float getPitch() {
        return this.pitch;
    }

    public final boolean isForward() {
        return this.forward;
    }

    public final boolean isBack() {
        return this.back;
    }

    public final boolean isLeft() {
        return this.left;
    }

    public final boolean isRight() {
        return this.right;
    }

    public final boolean isSneak() {
        return this.sneak;
    }

    public final boolean isJump() {
        return this.jump;
    }

    public final boolean isUse() {
        return this.use;
    }

    public final boolean isAttack() {
        return this.attack;
    }

    public final float getYaw2() {
        return this.yaw;
    }

    public final float getPitch2() {
        return this.pitch;
    }

    public final boolean isForward2() {
        return this.forward;
    }

    public final boolean isBack2() {
        return this.back;
    }

    public final boolean isLeft2() {
        return this.left;
    }

    public final boolean isRight2() {
        return this.right;
    }

    public final boolean isSneak2() {
        return this.sneak;
    }

    public final boolean isJump2() {
        return this.jump;
    }

    public final boolean isUse2() {
        return this.use;
    }

    public final boolean isAttack2() {
        return this.attack;
    }

    @NotNull
    public final MovementInput copy(float f, float f2, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8) {
        return new MovementInput(f, f2, bl, bl2, bl3, bl4, bl5, bl6, bl7, bl8);
    }

    /*
     * WARNING - void declaration
     */
    public static MovementInput copy$default(MovementInput movementInput, long l, float f, float f2, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8, int n, Object object) {
        MovementInput movementInput2;
        block31: {
            boolean bl9;
            block30: {
                boolean bl10;
                block29: {
                    void var13_13;
                    l = a ^ l;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-1937730532187199880L, (long)l);
                    bl10 = var13_13 & 1;
                    if (callSite == null) {
                        if (bl10) {
                            f = movementInput.yaw;
                        }
                        bl10 = var13_13 & 2;
                    }
                    CallSite callSite2 = callSite;
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                f2 = movementInput.pitch;
                            }
                            bl10 = var13_13 & 4;
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl = movementInput.forward;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)10795, (long)(0x7370892D0DFA9027L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl2 = movementInput.back;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)20697, (long)(0x7E49197500B06AD2L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl3 = movementInput.left;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)710, (long)(0x2B2C7DE0E78038CCL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl4 = movementInput.right;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)27513, (long)(0x4D30240F42E45178L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl5 = movementInput.sneak;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)8722, (long)(0x6A5AAE013E20181DL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl6 = movementInput.jump;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)19656, (long)(0x3C403EC95C876C1L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (bl10) {
                                bl7 = movementInput.use;
                            }
                            bl10 = var13_13 & MovementInput.zkmBootstrap2("x", (int)26598, (long)(0xDFD87C15F4EDDEBL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) break block29;
                    if (!bl10) break block30;
                    bl10 = movementInput.attack;
                }
                bl9 = bl10;
            }
            movementInput2 = movementInput.copy(f, f2, bl, bl2, bl3, bl4, bl5, bl6, bl7, bl9);
            if (l < 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)-1966796498706564571L, (long)l) == null) break block31;
            ZkmReferences.zkmBootstrap("\u00c5", "WVKx7b", (long)-1977278332407438865L, (long)l);
        }
        return movementInput2;
    }

    @NotNull
    public String toString() {
        boolean bl = this.attack;
        boolean bl2 = this.use;
        boolean bl3 = this.jump;
        boolean bl4 = this.sneak;
        boolean bl5 = this.right;
        boolean bl6 = this.left;
        boolean bl7 = this.back;
        boolean bl8 = this.forward;
        float f = this.pitch;
        float f2 = this.yaw;
        return "ActionRecord(yaw=" + f2 + ", pitch=" + f + ", forward=" + bl8 + ", back=" + bl7 + ", left=" + bl6 + ", right=" + bl5 + ", sneak=" + bl4 + ", jump=" + bl3 + ", use=" + bl2 + ", attack=" + bl + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.yaw);
        n = n * 31 + Float.hashCode(this.pitch);
        n = n * 31 + Boolean.hashCode(this.forward);
        n = n * 31 + Boolean.hashCode(this.back);
        n = n * 31 + Boolean.hashCode(this.left);
        n = n * 31 + Boolean.hashCode(this.right);
        n = n * 31 + Boolean.hashCode(this.sneak);
        n = n * 31 + Boolean.hashCode(this.jump);
        n = n * 31 + Boolean.hashCode(this.use);
        n = n * 31 + Boolean.hashCode(this.attack);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof MovementInput)) {
            return false;
        }
        MovementInput movementInput = (MovementInput)object;
        if (Float.compare(this.yaw, movementInput.yaw) != 0) {
            return false;
        }
        if (Float.compare(this.pitch, movementInput.pitch) != 0) {
            return false;
        }
        if (this.forward != movementInput.forward) {
            return false;
        }
        if (this.back != movementInput.back) {
            return false;
        }
        if (this.left != movementInput.left) {
            return false;
        }
        if (this.right != movementInput.right) {
            return false;
        }
        if (this.sneak != movementInput.sneak) {
            return false;
        }
        if (this.jump != movementInput.jump) {
            return false;
        }
        if (this.use != movementInput.use) {
            return false;
        }
        return this.attack == movementInput.attack;
    }

    public static void zkmSetFlag(String string) {
        H = string;
    }

    public static String zkmGetFlag() {
        return H;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        MovementInput.a = ZkmKeys.a(-3856362123290917829L, -8635259768604139610L, MethodHandles.lookup().lookupClass()).a(264089995816117L);
                        var20 = MovementInput.a ^ 81905839644160L;
                        MovementInput.d = new HashMap<K, V>(13);
                        MovementInput.zkmSetFlag(null);
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
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "\"\u00e2)lW:1Y\u00ae6St\u008f\u0006\u008c\u0093\u00ac\u0013\u00d5\u00f7\u00c2\u00a1`\u00d6Os\u00aaY(S\u009a\u0006\u0010\u00a0[~%\u00ca]\u00fcq\u00a6\u00fe\u009e\u001c,\u00bdq\u0099\u0018\u0016\u001f<\u00a3\u00cb\u008f}\u00c7\u00aa.|\u00daP\u00f7\u00de\u0093\u001bF|Us\u00a0\b\u0011 \u00be\u00a8z\u00c9\u000f\u0092\u00ac\u001d\u00c9\u0084Z\u00d0\u0099\u00c0D\u00f3\u00e0\u0090\u008a~r\u00f8\u009b\u00ad\u00f2\t\u00cc6\u009e\u00cc\u00ed \u0018\u00e4W\u00d3<\u00d3+t\u00e1.\u009f\u00ecBJ\u00cfZ\u00e8\u00c2?&1\u0000>\u00153\u0010\u00bb\u00b6\u0018\u0019Xz\u009aU\u00ca\u00fc\u009d\u00ef\u00cc>\u0016~\u0010w<\u0084\u00f2\u007f\u007f\u00c6U+\u001d?\u001bh\u0001\u00be\u00e6 \u00d5-7a8\u00daSf\u00ed>\u00fd\u00ce\u0002\u00c9\u00d5\u00c9i\u00fc\u00d0t#>\n\u0080\u00dd\u0014-\u00a0\u0096P\u00fb\u00d3";
                        var17_6 = 199;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = MovementInput.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\"R.07\u00c3\u00cd\u000b-\u00ac\u00b9\u009e\u00ed?.(\u0093\u00db\u00bf]\u00ff\u00b8\u000e\u00a0C\u00b0C\u0081\u00d7\u00bcpV\u00bc\f\u0083\u00f7\u00f7(\u0094!OC\u00cd\u0080M\u00ecA\"\u0010\u001bj\u0002T\u008cZ\u00df\u00b5R\u0011\t%\u0000\u000b\u00f6\u00ec";
                            var17_6 = 65;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = MovementInput.zkmDecodeUtf8(var19_9).intern();
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
                MovementInput.b = var18_3;
                MovementInput.c = new String[10];
                MovementInput.h = new HashMap<K, V>(13);
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
                var6_12 = new long[9];
                var3_13 = 0;
                var4_14 = "\u00ad\r \u0005\u001d\u0097qpm\u00f1\u00d8\u00ef\u00a6L\u00f0m-\u00e7+\u0096.\u00f4\t^\u00d1+\u00c84<\u00931\t\u0006L\u00aa\"~\u009f`\b\u00a2\u00e6\u008c\u000f}\u00b4\u0000\u00df\u00c2?\u00ea\u00fdp\u00e9\u0080\u00e7";
                var5_15 = 56;
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
                    var4_14 = "6\u0099\u00ef&E'\u00dd\u00e0\u00bb:\u009e\u00a9\u00a8\u00ac\u00d3\u00f9";
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
        MovementInput.e = var6_12;
        MovementInput.g = new Integer[9];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6B3A;
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
                throw new RuntimeException("su/catlean/bb", exception);
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
            MovementInput.c[n2] = MovementInput.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MovementInput.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/bb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5FEC;
        if (g[n2] == null) {
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
                throw new RuntimeException("su/catlean/bb", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MovementInput.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MovementInput.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/bb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MovementInput.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MovementInput.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

