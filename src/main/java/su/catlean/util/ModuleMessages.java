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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ModuleMessages {
    private static String Y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public static final void notify(@NotNull Module module, @NotNull Object object, long l) {
        int n = (int)(((l = a ^ l) ^ 0x27091F66788L) >>> 32);
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(object, "m");
        ModuleMessages.notify$default(module, object.toString(), n, null, 0, null, (int)ModuleMessages.zkmBootstrap2("l", (int)17943, (long)(0x22EE406C555C521L ^ l)), null);
    }

    public static final void notify(@NotNull Module module, @NotNull String string, @NotNull NotificationType notificationType, int n, @Nullable ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(string, "message");
        Intrinsics.checkNotNullParameter((Object)notificationType, "type");
        Notifications.INSTANCE.push(module.getDisplayName(), string, n, notificationType, itemStack);
    }

    public static /* synthetic */ void notify$default(Module module, String string, int n, NotificationType notificationType, int n2, ItemStack itemStack, int n3, Object object) {
        block5: {
            int n4 = 0;
            int n5 = 0;
            long l = ((long)n << 32 | (long)n4 << 48 >>> 32 | (long)n5 << 48 >>> 48) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3034302244103982446L, (long)l);
            int n6 = n3 & 2;
            if (callSite == null) {
                if (n6 != 0) {
                    notificationType = NotificationType.INFO;
                }
                n6 = n3 & 4;
            }
            if (callSite == null) {
                if (n6 != 0) {
                    n2 = 2;
                }
                n6 = n3 & ModuleMessages.zkmBootstrap2("l", (int)27572, (long)(0x3E97E7C919558382L ^ l));
            }
            if (n6 != 0) {
                itemStack = null;
            }
            ModuleMessages.notify(module, string, notificationType, n2, itemStack);
            if (n < 0 || ZkmReferences.zkmBootstrap("\u00c5", (long)3066608570416903199L, (long)l) == null) break block5;
            ZkmReferences.zkmBootstrap("\u00c5", "HFEsd", (long)3070153045240502808L, (long)l);
        }
    }

    public static final void message(@NotNull Module module, @NotNull Object object) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(module, (String)((Object)ModuleMessages.zkmBootstrap("a", (int)18643, (long)(0x35C2A4CE5FE7D105L ^ l))));
        Intrinsics.checkNotNullParameter(object, "m");
        ModuleMessages.S(module, object.toString(), false, null);
    }

    public static final void message(@NotNull Module module, @NotNull String string, boolean bl) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(string, "message");
        if (bl) {
            Globals.getNetworkHandler().sendChatMessage(string);
            return;
        }
        String string2 = string;
        Formatting formatting = Formatting.GRAY;
        String string3 = ((Object)StringsKt.trim((CharSequence)module.getDisplayName())).toString();
        Formatting formatting2 = Formatting.GOLD;
        Formatting formatting3 = Formatting.GRAY;
        Text text = Text.of(Formatting.GREEN + "> " + formatting3 + "[" + formatting2 + string3 + formatting + "] " + string2);
        Intrinsics.checkNotNullExpressionValue(text, "nullToEmpty(...)");
        Text text2 = text;
        if (Globals.getMc().isOnThread()) {
            Globals.getPlayer().sendMessage(text2, false);
        } else {
            Globals.getMc().executeSync(() -> ModuleMessages.sendRaw(text2));
        }
    }

    public static /* synthetic */ void S(Module module, String string, boolean bl, Object object) {
        bl = false;
        ModuleMessages.message(module, string, bl);
    }

    @NotNull
    public static final String i18n(@NotNull Module module, @NotNull String string, @NotNull Object[] objectArray, boolean bl) {
        Collection<Object> collection;
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(string, "message");
        Intrinsics.checkNotNullParameter(objectArray, "args");
        Object object = objectArray;
        String string2 = "modules." + (String)(bl ? "common" : module.getCategory().getId() + "." + module.getId()) + "." + string;
        I18n i18n = I18n.INSTANCE;
        Object[] objectArray2 = object;
        Collection collection2 = new ArrayList(((Object[])object).length);
        int n = objectArray2.length;
        for (int i = 0; i < n; ++i) {
            Object object2;
            Object object3 = object2 = objectArray2[i];
            collection = collection2;
            Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type java.lang.Object");
            collection.add(object3);
        }
        collection = (List)collection2;
        objectArray2 = object = (Collection)collection;
        Object[] objectArray3 = objectArray2.toArray(new Object[0]);
        Object[] objectArray4 = Arrays.copyOf(objectArray3, objectArray3.length);
        String string3 = string2;
        return i18n.translate(string3, objectArray4);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ String i18n$default(Module module, String string, Object[] objectArray, boolean bl, int n, Object object) {
        void var4_5;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5749357252245088444L, (long)l);
        boolean bl2 = var4_5 & 4;
        if (callSite == null) {
            bl2 = false;
        }
        boolean bl3 = bl2;
        return ModuleMessages.i18n(module, string, objectArray, bl3);
    }

    private static final void sendRaw(Text text) {
        Globals.getPlayer().sendMessage(text, false);
    }

    public static void zkmSetFlag(String string) {
        Y = string;
    }

    public static String zkmGetFlag() {
        return Y;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        ModuleMessages.a = ZkmKeys.a(3056892224553483098L, -7781879240830200846L, MethodHandles.lookup().lookupClass()).a(119731494148497L);
                        var20 = ModuleMessages.a ^ 42644792788137L;
                        ModuleMessages.d = new HashMap<K, V>(13);
                        ModuleMessages.zkmSetFlag(null);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "\u00e7&\u0013\u00ba\u000f\u0018\u00da\u001d\u00f9i5\u009d\u00d3\u0088\u00f9g1\u0013\u0012MCD@\u0081\u00e7.7\u0015\u0013\u00cet\u00e4\u0084\u0002\u001d\u00f0\u00e6\u00f6\u00bey\u0010\u00a7\u00ce\u0093\u008c\u00e8\u00c6b\u0012\u00f8\u00cdu\u0080\u00eb\u0098\u00b97\u0010Sp\u00cbEv51g\u00da\u00f8\u00e9Y\u0007\u001c;H\u0010\u0018t\u0019\u00a1\u00de\u009f\u00df\u0094 \u00aa~\u00a0\u00fd\u00cf\u0095\u0005X\ne\u00bf\u00bb\u000e|\f\u00cb\u00afJ\u00c7z\u0091Q\u00a7\u00fa\u000f%4`\u0019\u00c1\u009cC\u007f\u000e\u00f6\u00f5\u00c1\u00d9v&\u00e2\u001f\u008d\u001b\u00fb\u00e2/\u0082\u009e\u0082\u00c30\u00b6w\u00f6\u00bc\u00af<\u00a9zt>}k\u00b4\u00d2\u00897\u00d9\u001f\u00ea\u00e1\u00a8\u00c9j\u00e4\u008c\u00ab\u0083\u00ae*\u0014ah\u0096\u00e1\u001b06\u00e3\b\u00ab>\u00c7\u0019\u00e9\u0010[\u00ea\u00b4m\u001e\u00bcx\u00f15\u00db\u00b0h\u00fb\u00a6HF\u0010\u00ed\u001e\u0014,\u00fbT\u00beL\u00b7I\u00a0G#\u0000\u00ee- v\u00e2\u00f1$\u00d4\u00b0\u00a4\u00f8\u00d5tXh\u0007m \u0011\u0003<\u008dyHE}Bl\u00f3\u0092\"\u00a1\u00b3\u001d\u00b4\u0010\u0084\u00ef\u00af\u0013E\u00c0w\u00ba}\u0003\u00dae\u00e1\u00c3i\u0083\u0010\u0091\u00fec\u00eb\u00abvZT\u009b\u00c4\u00f5W\u009b\fm\u00a2";
                        var17_6 = 281;
                        var14_7 = 40;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ModuleMessages.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "SQ\u001f\u009f\u009d(\u00c9\u009f\u00d5\u00ac\u00a35\u0000yU\u0013\u0010\u0004\u00d2\u00de\u0098\u0013\u00d5\u001c\u00fa\u00b6\u00c9.K\u0001.\u00c6\u0097";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ModuleMessages.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                ModuleMessages.b = var18_3;
                ModuleMessages.c = new String[12];
                ModuleMessages.g = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "Fs\u0087\u00c9\u00c7[A\u00cc\u00fcL\u00a8\u00c6R\u009b\u00ba\u001c";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl74:
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
        ModuleMessages.e = var6_12;
        ModuleMessages.f = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4130;
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
                throw new RuntimeException("su/catlean/o2", exception);
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
            ModuleMessages.c[n2] = ModuleMessages.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ModuleMessages.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/o2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x43E9;
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
                throw new RuntimeException("su/catlean/o2", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ModuleMessages.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ModuleMessages.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/o2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ModuleMessages.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ModuleMessages.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

