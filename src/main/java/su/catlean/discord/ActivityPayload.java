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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.ActivityPayload$Companion;
import su.catlean.discord.ActivityPayloadSerializer;
import su.catlean.discord.RichPresence;
import su.catlean.discord.RichPresenceSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ActivityPayload {
    @NotNull
    public static final ActivityPayload$Companion Companion;
    private final int pid;
    @NotNull
    private final RichPresence activity;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long f;

    public ActivityPayload(int n, @NotNull RichPresence richPresence) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(richPresence, (String)((Object)ActivityPayload.zkmBootstrap("f", (int)10348, (long)(0x44ED406EBA443C27L ^ l))));
        this.pid = n;
        this.activity = richPresence;
    }

    public final int getPid() {
        return this.pid;
    }

    @NotNull
    public final RichPresence getActivity() {
        return this.activity;
    }

    public final int getPid2() {
        return this.pid;
    }

    @NotNull
    public final RichPresence getActivity2() {
        return this.activity;
    }

    @NotNull
    public final ActivityPayload copy(int n, @NotNull RichPresence richPresence) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(richPresence, (String)((Object)ActivityPayload.zkmBootstrap("f", (int)17877, (long)(0x508680E231B1C652L ^ l))));
        return new ActivityPayload(n, richPresence);
    }

    public static ActivityPayload copy$default(ActivityPayload activityPayload, int n, RichPresence richPresence, int n2, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-7193315830545369590L, (long)l);
        int n3 = n2 & 1;
        if (callSite == null) {
            if (n3 != 0) {
                n = activityPayload.pid;
            }
            n3 = n2 & 2;
        }
        if (n3 != 0) {
            richPresence = activityPayload.activity;
        }
        return activityPayload.copy(n, richPresence);
    }

    @NotNull
    public String toString() {
        return "FrameArgs(pid=" + this.pid + ", activity=" + this.activity + ")";
    }

    public int hashCode() {
        int n = Integer.hashCode(this.pid);
        n = n * (int)f + this.activity.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ActivityPayload)) {
            return false;
        }
        ActivityPayload activityPayload = (ActivityPayload)object;
        if (this.pid != activityPayload.pid) {
            return false;
        }
        return Intrinsics.areEqual(this.activity, activityPayload.activity);
    }

    @JvmStatic
    public static final void write$Self(ActivityPayload activityPayload, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeIntElement(serialDescriptor, 0, activityPayload.pid);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, RichPresenceSerializer.INSTANCE, activityPayload.activity);
    }

    public ActivityPayload(int n, int n2, RichPresence richPresence, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 3, ActivityPayloadSerializer.INSTANCE.getDescriptor());
        }
        this.pid = n2;
        this.activity = richPresence;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ActivityPayload.a = ZkmKeys.a(7305753417115354482L, -4603186143088505191L, MethodHandles.lookup().lookupClass()).a(135432161967122L);
                    ActivityPayload.e = new HashMap<K, V>(13);
                    var5 = ActivityPayload.a ^ 89339671385754L;
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
                    var14_3 = new String[4];
                    var12_4 = 0;
                    var11_5 = "-\u00d8\u00e6>G\u00ee\u0002\u00a67\u0097S\u008b4>\u008df|\u0011\u00db\u0014\u00d1\u0084\tl\u00e33\u00fbj\u00ce\u00ca\u00865 \f)\u00c5\f\u00d1l\u00f9E\u00c5\u00ea\u00ffI\u00ae\u00aa\u001crz8\u00b0\u00f7-\u001d(\u0088v,\u001a\u00a7\u00c2wZ\u0088";
                    var13_6 = 65;
                    var10_7 = 32;
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
                        var14_3[var12_4++] = ActivityPayload.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u00be\u0091Y\u0094\u00a2s\u00a1\u00e1q|\u0018\u0007\u00ae^\u00ffV\u00cb\u00dc\u00e47\u00a8\u00bbL\u001f\u00cf\u00c6=\u00bb\u00ab>\u009f\u0089 <t\u008f\u0010\u00f7\u0005\u0091]\u00f24rO\u009d\u000fY\f;L\u0014\u00c9\u00fb\u0082Z\u001cL\u00fa?\u00ba\u00dd\u00c8\u009c\u0012";
                        var13_6 = 65;
                        var10_7 = 32;
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
                        var14_3[var12_4++] = ActivityPayload.zkmDecodeUtf8(var15_9).intern();
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
            ActivityPayload.b = var14_3;
            ActivityPayload.d = new String[4];
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
        var4_12 = var0_10.doFinal(new byte[]{59, 70, -32, -85, 102, -125, 99, -110});
        ** while (true)
        ActivityPayload.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        ActivityPayload.Companion = new ActivityPayload$Companion(null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3A67;
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
                throw new RuntimeException("su/catlean/dn", exception);
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
            ActivityPayload.d[n2] = ActivityPayload.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ActivityPayload.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dn" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ActivityPayload.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

