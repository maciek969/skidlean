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
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigStore;
import su.catlean.util.Manager;
import su.catlean.zkm.ZkmKeys;

public final class FriendManager
implements Manager {
    @NotNull
    public static final FriendManager INSTANCE;
    private static boolean M;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    private FriendManager() {
    }

    public final boolean isFriend(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        return ConfigStore.INSTANCE.getJsonListFile2().getData().contains(string);
    }

    public final boolean isFriend(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "player");
        String string = playerEntity.getGameProfile().name();
        Intrinsics.checkNotNullExpressionValue(string, "name(...)");
        String string2 = string;
        return this.addFriend(string2);
    }

    public final boolean addFriend(@NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)FriendManager.zkmBootstrap("q", (int)26604, (long)(0x7F4225A870E69C5BL ^ l))));
        return ConfigStore.INSTANCE.getJsonListFile2().getData().add(string);
    }

    public final boolean removeFriend(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "player");
        String string = playerEntity.getGameProfile().name();
        Intrinsics.checkNotNullExpressionValue(string, "name(...)");
        return this.removeFriend(string);
    }

    public final boolean removeFriend(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        return ConfigStore.INSTANCE.getJsonListFile2().getData().remove(string);
    }

    public final void clearFriends() {
        ConfigStore.INSTANCE.getJsonListFile2().getData().clear();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                FriendManager.a = ZkmKeys.a(6441365239734619426L, -2548520157343966958L, MethodHandles.lookup().lookupClass()).a(27629258573058L);
                var9 = FriendManager.a ^ 139875772915231L;
                FriendManager.e = new HashMap<K, V>(13);
                FriendManager.zkmSetFlag(true);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[6];
                var5_4 = 0;
                var4_5 = "Cc\u00be\u008e\n\u00f0s\u00ac$h\u00dc\u00827\u00be\u00d4\u0086\u00c3\u00b1\u00e4\u00b6\u00a5\u00bb\u00d2e\u0010\u00bb\u0012'#\u00d4\u000e\u0084\u009a\u0083\n@\u00d8\u00c9a\u0090\u00b7\u0010\u00f6\u00ef\u0015$\u00ca#\u00ea\u0083\u00f1\u00e1o\u00d0\u000f\u00f5\u00ac\u00ff\u0010>~\u00b7\t\u009c\u0089\u00d9\u0005c\u0090:\u001d&\u0082\u00a4E";
                var6_6 = 75;
                var3_7 = 24;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = FriendManager.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u0080=w\u00acZ\u00a4\u00eb\u0081\u0011e@I\u00e4\u00e9\u009aL\u0083R=\u00822f&\u0083\u0010\u007fG\u00bau\u001ce\u00c9j\u0014&\u00dc\u00eb\u00dd\u00a9\u00f2h";
                    var6_6 = 41;
                    var3_7 = 24;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = FriendManager.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        FriendManager.c = var7_3;
        FriendManager.d = new String[6];
        FriendManager.INSTANCE = new FriendManager();
    }

    public static void zkmSetFlag(boolean bl) {
        M = bl;
    }

    public static boolean zkmGetFlag() {
        return M;
    }

    public static boolean Z() {
        return false;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6D0D;
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
                throw new RuntimeException("su/catlean/c7", exception);
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
            FriendManager.d[n2] = FriendManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FriendManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/c7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FriendManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

