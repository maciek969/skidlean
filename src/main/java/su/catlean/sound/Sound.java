/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.sound;

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
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.sound.CustomSoundInstance;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Sound {
    @NotNull
    private final String name;
    private final float volume;
    @NotNull
    private final SoundEvent c;
    private static int[] K;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long f;

    public Sound(@NotNull String string, float f) {
        Intrinsics.checkNotNullParameter(string, "name");
        this.name = string;
        this.volume = f;
        Identifier identifier = Ids.id("catlean:" + this.name);
        SoundEvent soundEvent = SoundEvent.of(identifier);
        Intrinsics.checkNotNullExpressionValue(soundEvent, "createVariableRangeEvent(...)");
        this.c = soundEvent;
        Registry.register(Registries.SOUND_EVENT, identifier, this.c);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final float getVolume() {
        return this.volume;
    }

    public final void play() {
        Globals.getMc().executeSync(() -> Sound.play$lambda$0(this));
    }

    public final void playAt(@NotNull Vec3d vec3d) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)Sound.zkmBootstrap("p", (int)23319, (long)(0x5D2FC49CD169E17FL ^ l2))));
        double d = vec3d.z;
        double d2 = vec3d.y;
        this.playAt(vec3d.x, d2, d);
    }

    public final void playAt(double d, double d2, double d3) {
        Globals.getMc().executeSync(() -> Sound.playAt$lambda$0(d, d2, d3, this));
    }

    @NotNull
    public final String getName2() {
        return this.name;
    }

    public final float getVolume2() {
        return this.volume;
    }

    @NotNull
    public final Sound copy(@NotNull String string, float f) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Sound.zkmBootstrap("p", (int)1439, (long)(0x1BC5C835D5A34E29L ^ l))));
        return new Sound(string, f);
    }

    public static Sound copy$default(Sound sound, String string, float f, int n, Object object, long l) {
        Sound sound2;
        block3: {
            int n2 = 0;
            long l2 = ((long)n2 << 56 | l << 8 >>> 8) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-592865278864785114L, (long)l2);
            int n3 = n & 1;
            if (callSite == null) {
                if (n3 != 0) {
                    string = sound.name;
                }
                n3 = n & 2;
            }
            if (n3 != 0) {
                f = sound.volume;
            }
            sound2 = sound.copy(string, f);
            if (l <= 0L || ZkmReferences.zkmBootstrap("\u00c5", (long)-578216759590917784L, (long)l2) == null) break block3;
            ZkmReferences.zkmBootstrap("\u00c5", "HXlqvb", (long)-597185986396064751L, (long)l2);
        }
        return sound2;
    }

    @NotNull
    public String toString() {
        return "Sound(name=" + this.name + ", volume=" + this.volume + ")";
    }

    public int hashCode() {
        int n = this.name.hashCode();
        n = n * (int)f + Float.hashCode(this.volume);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Sound)) {
            return false;
        }
        Sound sound = (Sound)object;
        if (!Intrinsics.areEqual(this.name, sound.name)) {
            return false;
        }
        return Float.compare(this.volume, sound.volume) == 0;
    }

    private static final void play$lambda$0(Sound sound) {
        SoundEvent soundEvent = sound.c;
        SoundCategory soundCategory = SoundCategory.VOICE;
        Random random = SoundInstance.createRandom();
        Globals.getMc().getSoundManager().play(new CustomSoundInstance(soundEvent, soundCategory, random));
    }

    private static final void playAt$lambda$0(double d, double d2, double d3, Sound sound) {
        Globals.getWorld().playSoundClient(d, d2, d3, sound.c, SoundCategory.PLAYERS, sound.volume, 1.0f, true);
    }

    public static void zkmSetFlag(int[] nArray) {
        K = nArray;
    }

    public static int[] zkmGetFlag() {
        return K;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    Sound.a = ZkmKeys.a(8889086489295664459L, -7696702242446467936L, MethodHandles.lookup().lookupClass()).a(141004746872133L);
                    var14 = Sound.a ^ 93369187949011L;
                    Sound.e = new HashMap<K, V>(13);
                    Sound.zkmSetFlag(new int[4]);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[7];
                    var10_4 = 0;
                    var9_5 = "\u00c5\u00d5j\u009bZ\u008c\u00a7)\u00b72S?\u0091U\u00dev6u[s\u00bb\u00f3\"\u00f5\u00b8\u00ae\u00e7\u00ee\u00caZ\u00cf\u00cd\u0010\u00d3\u00b4\u009e\u00a1\u00f2\u00abh-L#\u0011\u000e\u00e4\u009c\u00c8;\u00185\u00c5\u00d1!=\u00cb!\u00ec\u0097a0\u00d1\u00aa\u00ca\u00f3\u00b2\u00e3\u008fh\u0086\u001a\u001b{\u00f5\u0010\u00bb&\u00b1R\u0006L\u00c9\u00bc8\u00c2\u0089\u00e5\u00feo\u00bei0@)\u0018\u009e\u00c9\u00c8P\u00e1Bl\u00efz\u00f1\u00a8\u0087\u0007K\u00f0$\u000f\u00f2\u0010\u00d4\u00b5\u000e\u0006S&r\u0083\u00fa\u0013jo\u0012S6\u008c-\u00df\fn\u0013\u001f$m\u00c5F";
                    var11_6 = 140;
                    var8_7 = 32;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = Sound.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\n\u0087x\u001dnJ\u0091\u00e5\u00f5\u00ae\u00cc\u0011t^M\u00b3>\u0094\u0014\u00b7-\u00f2\u00dd\u00c7 (\u0000'\u00d0\u00f0y\u009e\u0010\u009c2\u00e5&\u0007\u00b3\u00f5I\u00bd\u001f\u00c5\u00c6\u00fb\u0096z\u00c0";
                        var11_6 = 49;
                        var8_7 = 32;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = Sound.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            Sound.b = var12_3;
            Sound.d = new String[7];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{19, -119, 96, 98, 29, 86, 24, -65});
        ** while (true)
        Sound.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7ADF;
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
                throw new RuntimeException("su/catlean/lp", exception);
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
            Sound.d[n2] = Sound.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Sound.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/lp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Sound.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

