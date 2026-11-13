/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package su.catlean.util;

import com.mojang.authlib.GameProfile;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.Globals;
import su.catlean.util.PlayerListUtil;
import su.catlean.zkm.ZkmKeys;

public final class JoinedPlayer {
    @NotNull
    private final GameProfile profile;
    private boolean announced;
    @NotNull
    private final PlayerListEntry O;
    @Nullable
    private String string;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public JoinedPlayer(@NotNull GameProfile gameProfile, boolean bl) {
        Intrinsics.checkNotNullParameter(gameProfile, "profile");
        this.profile = gameProfile;
        this.announced = bl;
        this.O = new PlayerListEntry(this.profile, false);
    }

    @NotNull
    public final GameProfile getProfile() {
        return this.profile;
    }

    public final boolean isAnnounced() {
        return this.announced;
    }

    public final void setAnnounced(boolean bl) {
        this.announced = bl;
    }

    @NotNull
    public final PlayerListEntry o() {
        return this.O;
    }

    @Nullable
    public final String getDisplayName() {
        block0: {
            if (this.string != null) break block0;
            this.string = PlayerListUtil.INSTANCE.getDisplayName(this.O).getString();
        }
        return this.string;
    }

    private final String findPlayer(long l) {
        int n = (int)(((l = a ^ l) ^ 0x41B6494B45C2L) >>> 32);
        String string = this.profile.name();
        Intrinsics.checkNotNull(string);
        PlayerEntity playerEntity = PlayerListUtil.INSTANCE.findPlayer(n, string);
        if (playerEntity != null) {
            this.announced = false;
            return Formatting.YELLOW + " (near)" + Formatting.RESET;
        }
        PlayerListEntry playerListEntry = Globals.getNetworkHandler().getPlayerListEntry(string);
        if (Globals.getNetworkHandler().getPlayerList().stream().map(arg_0 -> JoinedPlayer.findPlayer$lambda$1(JoinedPlayer::findPlayer$lambda$0, arg_0)).noneMatch(arg_0 -> JoinedPlayer.invokePredicate(arg_0 -> JoinedPlayer.findPlayer$lambda$2(string, arg_0), arg_0))) {
            this.announced = true;
            return Formatting.RED + " Spec!" + Formatting.RESET;
        }
        if (playerListEntry != null && playerListEntry.getGameMode() == GameMode.SPECTATOR) {
            this.announced = false;
            return Formatting.YELLOW + " (gm3)" + Formatting.RESET;
        }
        this.announced = false;
        return "";
    }

    @NotNull
    public final String describe(long l) {
        long l2 = (l = a ^ l) ^ 0x3CCBD8713AEDL;
        return this.findPlayer(l2) + " " + this.getDisplayName();
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (object != null && Intrinsics.areEqual(this.getClass(), object.getClass())) {
            JoinedPlayer joinedPlayer = (JoinedPlayer)object;
            return Intrinsics.areEqual(this.profile.name(), joinedPlayer.profile.name());
        }
        return false;
    }

    public int hashCode() {
        return this.getClass().hashCode();
    }

    private static final String findPlayer$lambda$0(PlayerListEntry playerListEntry) {
        return playerListEntry.getProfile().name();
    }

    private static final String findPlayer$lambda$1(Function1 function1, Object object) {
        return (String)function1.invoke(object);
    }

    private static final boolean findPlayer$lambda$2(String string, String string2) {
        return string2.equals(string);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                JoinedPlayer.a = ZkmKeys.a(-3279992337355112609L, -973474538662296766L, MethodHandles.lookup().lookupClass()).a(264759766636695L);
                JoinedPlayer.d = new HashMap<K, V>(13);
                var0 = JoinedPlayer.a ^ 116410610585899L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[4];
                var7_4 = 0;
                var6_5 = "!\u00e3\u00d4\u00edb\u009ae\u00ab\u000b{\u00fa8U\u00b0\u009e\u00cb\u00103\u007f\u0014E\u00b2\u00aa\u00e1\u00bd\u00ec\u00c9d\u00c30\u00bd\u00cd\u00fe";
                var8_6 = 33;
                var5_7 = 16;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = JoinedPlayer.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "\u00a79e;,\u009c\u0099\u00e8\u009a\u00bb\u00ac\u00baZvi\u0080\u00103\u00ce\u0002\u00bc\u00ce\u009b\u00c9p\u00f8\u008d\u00c5\u00f7\u00ac5\u00c32";
                    var8_6 = 33;
                    var5_7 = 16;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = JoinedPlayer.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        JoinedPlayer.b = var9_3;
        JoinedPlayer.c = new String[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5DE0;
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
                throw new RuntimeException("su/catlean/ds", exception);
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
            JoinedPlayer.c[n2] = JoinedPlayer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = JoinedPlayer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ds" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(JoinedPlayer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

