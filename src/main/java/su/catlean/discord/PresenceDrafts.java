/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

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
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.discord.PresenceCycler;
import su.catlean.discord.PresenceDraft;
import su.catlean.zkm.ZkmKeys;

public final class PresenceDrafts {
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public static final boolean saveDraft(@NotNull DiscordRpcSettings discordRpcSettings, @NotNull Function1 function1) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(discordRpcSettings, (String)((Object)PresenceDrafts.zkmBootstrap("m", (int)18253, (long)(0x26DEBC837274018CL ^ l))));
        Intrinsics.checkNotNullParameter(function1, (String)((Object)PresenceDrafts.zkmBootstrap("m", (int)32503, (long)(0x1659B565E7A13834L ^ l))));
        List list = PresenceCycler.INSTANCE.getDrafts();
        PresenceDraft presenceDraft = new PresenceDraft(null, null, null, null, null, null, null, (int)e, null);
        function1.invoke(presenceDraft);
        return list.add(presenceDraft);
    }

    public static final void setDraft(@NotNull DiscordRpcSettings discordRpcSettings, @NotNull Object object) {
        Object v0;
        Object object2;
        block3: {
            Intrinsics.checkNotNullParameter(discordRpcSettings, "<this>");
            Intrinsics.checkNotNullParameter(object, "draftId");
            Iterable iterable = PresenceCycler.INSTANCE.getDrafts();
            for (Object t : iterable) {
                object2 = (PresenceDraft)t;
                if (!Intrinsics.areEqual(((PresenceDraft)object2).getDraftId(), object)) continue;
                v0 = t;
                break block3;
            }
            v0 = null;
        }
        PresenceDraft presenceDraft = v0;
        if (presenceDraft != null) {
            PresenceDraft presenceDraft2 = presenceDraft;
            Object object3 = object2 = DiscordRpcSettings.INSTANCE;
            ((DiscordRpcSettings)object3).setDetails(presenceDraft2.getDetails());
            ((DiscordRpcSettings)object3).setState(presenceDraft2.getState());
            ((DiscordRpcSettings)object3).setLargeImage(presenceDraft2.getLargeImage());
            ((DiscordRpcSettings)object3).setSmallImage(presenceDraft2.getSmallImage());
            ((DiscordRpcSettings)object3).setButton1(presenceDraft2.getButton1());
            ((DiscordRpcSettings)object3).setButton2(presenceDraft2.getButton2());
            v2 = object2;
        } else {
            System.out.println((Object)("Discord IPC - Draft with id " + object + " not found"));
            v2 = Unit.INSTANCE;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    PresenceDrafts.a = ZkmKeys.a(3592788078923359961L, -4735214856617635988L, MethodHandles.lookup().lookupClass()).a(221867716947086L);
                    PresenceDrafts.d = new HashMap<K, V>(13);
                    var5 = PresenceDrafts.a ^ 90147310448611L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[6];
                    var12_4 = 0;
                    var11_5 = "\u0090\u0092yuD\"\u00fb6j\u00dc\u0088x}\u00b6\u0003;7m\u00af\u0080q\u0089&\u0092\f\u00bb\u00f7\u0083\u00a7(2\u00af\u00ef*\u0012~\u00eb8o\u000fu]\u00bd\u00bb3\u00c1J\u0004\u0010\u00f1\u00de\u001cx&\u0099\u00f3\u00a1M}\u0013k\u00fd\u001f\u00d0=\u0018\u00a0>\u00c1\u00ea\n\u00cb\u00cd~N\u0001\u00bf~Tf\u0083?\u00b3\u00fe\u0015;N[VV\u0010\u009c\u00a2qN\u00a5o\u00c4U$\u00fe\u0019P\u00e1\u00f8vp";
                    var13_6 = 107;
                    var10_7 = 48;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = PresenceDrafts.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u001e\u00ccF\u0083\u001dE\t\u00d9-\u0016(\u00e1\u00d8f\u00dfL\u0010ev\u00d9\u00c9F\u000fC\u0088\u00b7BO\u00e5\u0085\u0013\u00ed5";
                        var13_6 = 33;
                        var10_7 = 16;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = PresenceDrafts.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            PresenceDrafts.b = var14_3;
            PresenceDrafts.c = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-120, 12, 7, -61, -36, -104, -93, -119});
        ** while (true)
        PresenceDrafts.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x647F;
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
                throw new RuntimeException("su/catlean/i2", exception);
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
            PresenceDrafts.c[n2] = PresenceDrafts.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PresenceDrafts.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/i2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PresenceDrafts.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

