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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ClickSlotAction {
    private final int syncId;
    private final int slotId;
    private final int button;
    @NotNull
    private final SlotActionType actionType;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public ClickSlotAction(int n, int n2, int n3, @NotNull SlotActionType slotActionType) {
        Intrinsics.checkNotNullParameter((Object)slotActionType, "actionType");
        this.syncId = n;
        this.slotId = n2;
        this.button = n3;
        this.actionType = slotActionType;
    }

    public final int getSyncId() {
        return this.syncId;
    }

    public final int getSlotId() {
        return this.slotId;
    }

    public final int getButton() {
        return this.button;
    }

    @NotNull
    public final SlotActionType getActionType() {
        return this.actionType;
    }

    public final int getSyncId2() {
        return this.syncId;
    }

    public final int getSlotId2() {
        return this.slotId;
    }

    public final int getButton2() {
        return this.button;
    }

    @NotNull
    public final SlotActionType getActionType2() {
        return this.actionType;
    }

    @NotNull
    public final ClickSlotAction copy(int n, int n2, int n3, @NotNull SlotActionType slotActionType) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter((Object)slotActionType, (String)((Object)ClickSlotAction.zkmBootstrap("n", (int)13709, (long)(0x6DB36379FA6BE791L ^ l))));
        return new ClickSlotAction(n, n2, n3, slotActionType);
    }

    public static /* synthetic */ ClickSlotAction L(ClickSlotAction clickSlotAction, int n, int n2, int n3, SlotActionType slotActionType, int n4, long l, Object object) {
        ClickSlotAction clickSlotAction2;
        block8: {
            l = a ^ l;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2745165972795752708L, (long)l);
            int n5 = n4 & 1;
            if (callSite != null) {
                if (n5 != 0) {
                    n = clickSlotAction.syncId;
                }
                n5 = n4 & 2;
            }
            CallSite callSite2 = callSite;
            if (l > 0L) {
                if (callSite2 != null) {
                    if (n5 != 0) {
                        n2 = clickSlotAction.slotId;
                    }
                    n5 = n4 & 4;
                }
                callSite2 = callSite;
            }
            if (callSite2 != null) {
                if (n5 != 0) {
                    n3 = clickSlotAction.button;
                }
                n5 = n4 & ClickSlotAction.zkmBootstrap2("l", (int)15124, (long)(0x663F936797263A03L ^ l));
            }
            if (n5 != 0) {
                slotActionType = clickSlotAction.actionType;
            }
            clickSlotAction2 = clickSlotAction.copy(n, n2, n3, slotActionType);
            if (l < 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)2782655138269181964L, (long)l) == null) break block8;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[2], (long)2747630314179431814L, (long)l);
        }
        return clickSlotAction2;
    }

    @NotNull
    public String toString() {
        SlotActionType slotActionType = this.actionType;
        int n = this.button;
        int n2 = this.slotId;
        int n3 = this.syncId;
        return "ClickData(syncId=" + n3 + ", slotId=" + n2 + ", button=" + n + ", actionType=" + slotActionType + ")";
    }

    public int hashCode() {
        int n = Integer.hashCode(this.syncId);
        n = n * 31 + Integer.hashCode(this.slotId);
        n = n * 31 + Integer.hashCode(this.button);
        n = n * 31 + this.actionType.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ClickSlotAction)) {
            return false;
        }
        ClickSlotAction clickSlotAction = (ClickSlotAction)object;
        if (this.syncId != clickSlotAction.syncId) {
            return false;
        }
        if (this.slotId != clickSlotAction.slotId) {
            return false;
        }
        if (this.button != clickSlotAction.button) {
            return false;
        }
        return this.actionType == clickSlotAction.actionType;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        ClickSlotAction.a = ZkmKeys.a(8566558813725042847L, 2471778388290338780L, MethodHandles.lookup().lookupClass()).a(78662324520366L);
                        ClickSlotAction.e = new HashMap<K, V>(13);
                        var11 = ClickSlotAction.a ^ 81694494910488L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[6];
                        var18_4 = 0;
                        var17_5 = "\u008e\u001c\u00e2\u00ed\u00cf\u009f\u00e8\u00a0V\u00a8_\u00b8\u00ee\u00cd\u00ea\u00d1\u009b5\u00e3\u00bc\u0090M\u00f5\u00ec]\u00cfi\u0015\u00aa\u001b\u00ec\u00bbA[F\u00dce\u00fc=\u00a1 2\u00c9\u00cf\u00e9DQ~1[W\u0005\u00c8e\u00ff\u00a3\u0099n\u00acp\u00a9\u00a5+b\u00c7<$\u00b9k\u0082\u0017F\u0017 \u00cd\u0015\u001e\u00ad\u0007B\u0091\u00e7\u00a1\u0016\u00f5\u00aa\u00b53M{\u00cb\u0094ol\u008fX\u00f5\u0080U\u008c\u00a9\u00d71$\u0017\u00fb !8\u0001\t\u00ab\u001d\u0085\u0089\u00ae\u00ae_*Gqa>|-\u00d3\u008dL\u0006Z={m\u00a3\u00a5\u00f4;\u0012\u00fd";
                        var19_6 = 139;
                        var16_7 = 40;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = ClickSlotAction.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00f1\u00e1*\u00ef\u00ba\u00e0\u008b\u0080\u00bb\u0094\u00e1\u00c9^b\u00f1\u00c6\u001e\u00f1\u00af\u00d0\u00ccE\u00eb\u00f9\u0018d)VS\u00a5\u001c#s\u00c9`\u008c \u00b4\u00da\u0083\u0098m\u0087\u0091;[\u00ae0Z";
                            var19_6 = 49;
                            var16_7 = 24;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = ClickSlotAction.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                ClickSlotAction.c = var20_3;
                ClickSlotAction.d = new String[6];
                ClickSlotAction.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u00e9\u00e5\u00d7\u00ad\u00ac1Z\u000f[I\u00a7pa\u00a5\u0093W\u00da'\u0095\u008e\u00d0\u00d5\f\u0085";
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
        ClickSlotAction.f = var6_12;
        ClickSlotAction.g = new Integer[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7B83;
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
                throw new RuntimeException("su/catlean/r", exception);
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
            ClickSlotAction.d[n2] = ClickSlotAction.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ClickSlotAction.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/r" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x26DA;
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
                throw new RuntimeException("su/catlean/r", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ClickSlotAction.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ClickSlotAction.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/r" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ClickSlotAction.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ClickSlotAction.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

