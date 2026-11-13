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
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PotionSlot {
    private final int slot;
    @Nullable
    private final StatusEffectInstance effect;
    private static boolean P;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long f;

    public PotionSlot(int n, @Nullable StatusEffectInstance statusEffectInstance) {
        this.slot = n;
        this.effect = statusEffectInstance;
    }

    public final int getSlot() {
        return this.slot;
    }

    @Nullable
    public final StatusEffectInstance getEffect() {
        return this.effect;
    }

    public final int getSlot2() {
        return this.slot;
    }

    @Nullable
    public final StatusEffectInstance getEffect2() {
        return this.effect;
    }

    @NotNull
    public final PotionSlot copy(int n, @Nullable StatusEffectInstance statusEffectInstance) {
        return new PotionSlot(n, statusEffectInstance);
    }

    public static PotionSlot E(PotionSlot potionSlot, int n, StatusEffectInstance statusEffectInstance, int n2, short s, Object object) {
        PotionSlot potionSlot2;
        block7: {
            Object object2;
            long l;
            block6: {
                long l2 = 0L;
                l = ((long)s << 48 | l2 << 16 >>> 16) ^ a;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-7368803208681432186L, (long)l);
                int n3 = n2 & 1;
                if (callSite != false) {
                    if (n3 != 0) {
                        n = potionSlot.slot;
                    }
                    n3 = n2 & 2;
                }
                if (n3 != 0) {
                    statusEffectInstance = potionSlot.effect;
                }
                potionSlot2 = potionSlot;
                object2 = n;
                if (s < 0) break block6;
                potionSlot2 = potionSlot2.copy((int)object2, statusEffectInstance);
                if (ZkmReferences.zkmBootstrap("\u00c5", (long)-7350556587255363732L, (long)l) == null) break block7;
                object2 = callSite;
            }
            ZkmReferences.zkmBootstrap("\u00c5", (object2 == 0 ? 1 : 0) != 0, (long)-7385765071219038214L, (long)l);
        }
        return potionSlot2;
    }

    @NotNull
    public String toString() {
        return "ArrowData(slot=" + this.slot + ", effect=" + this.effect + ")";
    }

    public int hashCode() {
        int n = Integer.hashCode(this.slot);
        n = n * (int)f + (this.effect == null ? 0 : this.effect.hashCode());
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = PotionSlot.w();
        Object object2 = this;
        if (!bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (!bl) {
            if (!(object2 instanceof PotionSlot)) {
                return false;
            }
            object2 = object;
        }
        PotionSlot potionSlot = (PotionSlot)object2;
        int n = this.slot;
        if (!bl) {
            if (n != potionSlot.slot) {
                return false;
            }
            n = Intrinsics.areEqual(this.effect, potionSlot.effect);
        }
        if (!bl) {
            if (!n) {
                return false;
            }
            n = 1;
        }
        return n;
    }

    public static void zkmSetFlag(boolean bl) {
        P = bl;
    }

    public static boolean zkmGetFlag() {
        return P;
    }

    public static boolean w() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-3321114622787626093L, 7166378035433596697L, MethodHandles.lookup().lookupClass()).a(155346583473214L);
        long l = a ^ 0x4B166C122A72L;
        e = new HashMap(13);
        PotionSlot.zkmSetFlag(true);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\u008f\u00cf\u00d3\u00fd9\u0089l\u0006\u008a\u00d7\u0091\u00e8\u00c9A\u00dc=\u00ce\u00c1N-Z\u00b9\u00b1B\u0092\u00b1x\u0015d/\u0094\u00b8\u0018ML\u0098\u0011\u00ab}\u00ffv\u00d3\u00114Z}=%\u00b3\u00d1\u00db\u00f7\u00c2\u000b\tt\u008d";
        int n2 = 57;
        int n3 = 32;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = PotionSlot.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        c = stringArray;
        d = new String[2];
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n6 = 1;
        while (true) {
            if (n6 >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{85, 109, 75, 51, 79, -85, 66, 78});
                f = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                return;
            }
            byArray5 = byArray5;
            byArray5[n6] = (byte)(l << n6 * 8 >>> 56);
            ++n6;
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x777;
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
                throw new RuntimeException("su/catlean/pi", exception);
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
            PotionSlot.d[n2] = PotionSlot.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PotionSlot.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PotionSlot.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

