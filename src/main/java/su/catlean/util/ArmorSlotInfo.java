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
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.equip.AutoArmor;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

final class ArmorSlotInfo {
    @NotNull
    private final EquipmentSlot equipmentSlot;
    private final int armorSlot;
    private int prevProt;
    private int newSlot;
    private int newProtection;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public ArmorSlotInfo(@NotNull EquipmentSlot equipmentSlot, int n, int n2, int n3, int n4) {
        Intrinsics.checkNotNullParameter(equipmentSlot, "equipmentSlot");
        this.equipmentSlot = equipmentSlot;
        this.armorSlot = n;
        this.prevProt = n2;
        this.newSlot = n3;
        this.newProtection = n4;
    }

    @NotNull
    public final EquipmentSlot getEquipmentSlot() {
        return this.equipmentSlot;
    }

    public final int getArmorSlot() {
        return this.armorSlot;
    }

    public final int getPrevProt() {
        return this.prevProt;
    }

    public final void setPrevProt(int n) {
        this.prevProt = n;
    }

    public final int getNewSlot() {
        return this.newSlot;
    }

    public final void setNewSlot(int n) {
        this.newSlot = n;
    }

    public final int getNewProtection() {
        return this.newProtection;
    }

    public final void setNewProtection(int n) {
        this.newProtection = n;
    }

    public final void getCurrentStack() {
        ItemStack itemStack = Globals.getPlayer().getInventory().getStack(this.armorSlot);
        Intrinsics.checkNotNullExpressionValue(itemStack, "getItem(...)");
        ItemStack itemStack2 = itemStack;
        AutoArmor autoArmor = AutoArmor.INSTANCE;
        this.prevProt = AutoArmor.access$getArmorScore(autoArmor, itemStack2);
        this.newSlot = -1;
        this.newProtection = -1;
    }

    @NotNull
    public final EquipmentSlot getEquipmentSlot2() {
        return this.equipmentSlot;
    }

    public final int getArmorSlot2() {
        return this.armorSlot;
    }

    public final int getPrevProt2() {
        return this.prevProt;
    }

    public final int getNewSlot2() {
        return this.newSlot;
    }

    public final int getNewProtection2() {
        return this.newProtection;
    }

    @NotNull
    public final ArmorSlotInfo copy(@NotNull EquipmentSlot equipmentSlot, int n, int n2, int n3, int n4) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(equipmentSlot, (String)((Object)ArmorSlotInfo.zkmBootstrap("f", (int)5761, (long)(0x677821D6E77F28A4L ^ l))));
        return new ArmorSlotInfo(equipmentSlot, n, n2, n3, n4);
    }

    public static ArmorSlotInfo copy$default(ArmorSlotInfo armorSlotInfo, EquipmentSlot equipmentSlot, int n, int n2, int n3, long l, int n4, int n5, Object object) {
        ArmorSlotInfo armorSlotInfo2;
        block16: {
            block15: {
                int n6;
                block14: {
                    l = a ^ l;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5232939142689025903L, (long)l);
                    n6 = n5 & 1;
                    if (callSite == null) {
                        if (n6 != 0) {
                            equipmentSlot = armorSlotInfo.equipmentSlot;
                        }
                        n6 = n5 & 2;
                    }
                    CallSite callSite2 = callSite;
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (n6 != 0) {
                                n = armorSlotInfo.armorSlot;
                            }
                            n6 = n5 & 4;
                        }
                        callSite2 = callSite;
                    }
                    if (l > 0L) {
                        if (callSite2 == null) {
                            if (n6 != 0) {
                                n2 = armorSlotInfo.prevProt;
                            }
                            n6 = n5 & ArmorSlotInfo.zkmBootstrap2("o", (int)28530, (long)(0x68C3A0464BF14791L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (l >= 0L) {
                        if (callSite2 == null) {
                            if (n6 != 0) {
                                n3 = armorSlotInfo.newSlot;
                            }
                            n6 = n5 & ArmorSlotInfo.zkmBootstrap2("o", (int)16203, (long)(0x2992F745D3D817ABL ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) break block14;
                    if (n6 == 0) break block15;
                    n6 = armorSlotInfo.newProtection;
                }
                n4 = n6;
            }
            armorSlotInfo2 = armorSlotInfo.copy(equipmentSlot, n, n2, n3, n4);
            if (l <= 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)-5226809492468094489L, (long)l) == null) break block16;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[4], (long)-5200405544947547426L, (long)l);
        }
        return armorSlotInfo2;
    }

    @NotNull
    public String toString() {
        int n = this.newProtection;
        int n2 = this.newSlot;
        int n3 = this.prevProt;
        int n4 = this.armorSlot;
        EquipmentSlot equipmentSlot = this.equipmentSlot;
        return "ArmorData(equipmentSlot=" + equipmentSlot + ", armorSlot=" + n4 + ", prevProt=" + n3 + ", newSlot=" + n2 + ", newProtection=" + n + ")";
    }

    public int hashCode() {
        int n = this.equipmentSlot.hashCode();
        n = n * 31 + Integer.hashCode(this.armorSlot);
        n = n * 31 + Integer.hashCode(this.prevProt);
        n = n * 31 + Integer.hashCode(this.newSlot);
        n = n * 31 + Integer.hashCode(this.newProtection);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ArmorSlotInfo)) {
            return false;
        }
        ArmorSlotInfo armorSlotInfo = (ArmorSlotInfo)object;
        if (this.equipmentSlot != armorSlotInfo.equipmentSlot) {
            return false;
        }
        if (this.armorSlot != armorSlotInfo.armorSlot) {
            return false;
        }
        if (this.prevProt != armorSlotInfo.prevProt) {
            return false;
        }
        if (this.newSlot != armorSlotInfo.newSlot) {
            return false;
        }
        return this.newProtection == armorSlotInfo.newProtection;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ArmorSlotInfo.a = ZkmKeys.a(-3929895574944108731L, 4051851449162225347L, MethodHandles.lookup().lookupClass()).a(72818857753439L);
                        ArmorSlotInfo.d = new HashMap<K, V>(13);
                        var11 = ArmorSlotInfo.a ^ 34851018826139L;
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
                        var20_3 = new String[8];
                        var18_4 = 0;
                        var17_5 = "a\u00a8\u000b<\u00ddm\u0004\u00a6\u00c6Oj\u00056\u0012/x\u00c7qD\u00a3\t\u00fa\u00c9\b3\u001c\u009f\\\u0018\u00f97\u00c0()u\u00b3\u00d5\u009e61\u001d\u008b\u00e13\u00fcR\u0096E\u00de\u00ab^\u00e1\r\u00de\u00904\u0098\u00aa\u0001\u0094\u00b3.\u00e6v+\u00e7\u00e7\u00c1FR\u00f0\u0005\u0084\u0018\u0011].\u00ce\u00bc\u00b0\u0093\t\u0001\u0095~\u0007d\u00ca<\u009c\u0080wk\u00b8\u00caq\u00bf\u0089 \u00d2QD,\u00a0<\u00c8\u00ccS\u00c4.\u009f\u00a2\u00aa\u00a4\u00aaL\u00e7\u00c9\u00dc\t'\u0093\u00d6!\u00a7\u009f\u00e7I\u00ady\u0013 \u001dE\u001b\u00deo\u00a6R\u0094\u00c2%\u0013U\u00f1\u00c3\u008b}\u00ec\u00cd\u00e0Lf\u00ed\u00b9\u009e\u00b5\u00f2\u00bdT8\u00a9\u00a9\u00ff \u0087\u00ad\u00fd\u00b6\u0099=\u0001\u00eb\u00f0t\n(4=U\u00df\u0089\u0082\u00e8\u00e8\u00a9(\u00e0\u00c7f\u00a0\u001c6\u00c8\u0098\u008a\u0090";
                        var19_6 = 197;
                        var16_7 = 32;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = ArmorSlotInfo.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00c6\u0094\u0081\u00b7\u00d5\u00dc\u00c3\u00ab\u001a}\u009a\u00c1[\u009e6\u0013\u00a74R\u00f5\u0014\u00b6\u00e9\u00d5\u00ce\u00af\u009e\u000b\u000eINO0\u009e;\u00b7\u00037Ee\u00c4\u0092Za\u0091\u00f9\u00f6\u00f2\u00f9\u00d8\u00a4\u00fd\u00da\u00f4\u00b2\u0082\u009f3\u00e5S\u00f8\u0082\u0088*C\u00a2[\u00cb\u0007j\u001c \u0092P\u008d\u00bbm:(8y";
                            var19_6 = 81;
                            var16_7 = 32;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = ArmorSlotInfo.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
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
                ArmorSlotInfo.b = var20_3;
                ArmorSlotInfo.c = new String[8];
                ArmorSlotInfo.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u00faE\u00d4\u00df\u00cd\u00ee(\u00bb\u000e\u0081\u0096%\u00ad\u0007.\u008f";
                var5_15 = 16;
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
                    var4_14 = "N\u00f5\u00afs\u00baw+\u0003\u00c3\u0092s'%\u00f4p\u00cb";
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
        ArmorSlotInfo.e = var6_12;
        ArmorSlotInfo.f = new Integer[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3BD2;
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
                throw new RuntimeException("su/catlean/o0", exception);
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
            ArmorSlotInfo.c[n2] = ArmorSlotInfo.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ArmorSlotInfo.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/o0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1EC5;
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
                throw new RuntimeException("su/catlean/o0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ArmorSlotInfo.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ArmorSlotInfo.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/o0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ArmorSlotInfo.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ArmorSlotInfo.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

