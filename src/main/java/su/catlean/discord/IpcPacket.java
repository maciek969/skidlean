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
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.IpcFrame;
import su.catlean.discord.IpcOpcode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class IpcPacket {
    @NotNull
    private final IpcOpcode opcode;
    @NotNull
    private final IpcFrame data;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long f;

    public IpcPacket(@NotNull IpcOpcode ipcOpcode, @NotNull IpcFrame ipcFrame) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter((Object)ipcOpcode, (String)((Object)IpcPacket.zkmBootstrap("l", (int)30649, (long)(0x60FBE9BBC19D2D35L ^ l))));
        Intrinsics.checkNotNullParameter(ipcFrame, (String)((Object)IpcPacket.zkmBootstrap("l", (int)91, (long)(0xC778BB088165AD2L ^ l))));
        this.opcode = ipcOpcode;
        this.data = ipcFrame;
    }

    @NotNull
    public final IpcOpcode getOpcode() {
        return this.opcode;
    }

    @NotNull
    public final IpcFrame getData() {
        return this.data;
    }

    @NotNull
    public final IpcOpcode getOpcode2() {
        return this.opcode;
    }

    @NotNull
    public final IpcFrame getData2() {
        return this.data;
    }

    @NotNull
    public final IpcPacket copy(@NotNull IpcOpcode ipcOpcode, @NotNull IpcFrame ipcFrame) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter((Object)ipcOpcode, (String)((Object)IpcPacket.zkmBootstrap("l", (int)31374, (long)(0x211837AD91D59757L ^ l))));
        Intrinsics.checkNotNullParameter(ipcFrame, (String)((Object)IpcPacket.zkmBootstrap("l", (int)19055, (long)(0x1A82502E921527B1L ^ l))));
        return new IpcPacket(ipcOpcode, ipcFrame);
    }

    public static /* synthetic */ IpcPacket copy$default(IpcPacket ipcPacket, IpcOpcode ipcOpcode, IpcFrame ipcFrame, int n, Object object) {
        long l = 0L;
        int n2 = 0;
        long l2 = (l << 16 | (long)n2 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5816120033724856977L, (long)l2);
        int n3 = n & 1;
        if (callSite == null) {
            if (n3 != 0) {
                ipcOpcode = ipcPacket.opcode;
            }
            n3 = n & 2;
        }
        if (n3 != 0) {
            ipcFrame = ipcPacket.data;
        }
        return ipcPacket.copy(ipcOpcode, ipcFrame);
    }

    @NotNull
    public String toString() {
        return "Packet(opcode=" + this.opcode + ", data=" + this.data + ")";
    }

    public int hashCode() {
        int n = this.opcode.hashCode();
        n = n * (int)f + this.data.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof IpcPacket)) {
            return false;
        }
        IpcPacket ipcPacket = (IpcPacket)object;
        if (this.opcode != ipcPacket.opcode) {
            return false;
        }
        return Intrinsics.areEqual(this.data, ipcPacket.data);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    IpcPacket.a = ZkmKeys.a(3580333649670352958L, 177839796594874134L, MethodHandles.lookup().lookupClass()).a(122955636249980L);
                    IpcPacket.e = new HashMap<K, V>(13);
                    var5 = IpcPacket.a ^ 10421038602459L;
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
                    var11_5 = "Pp\u008c\u00ba\u00b1\u00be\u00a4\u0081\u0002f\u00db\u00f1\u00a0\u0094FD0\u00b4\u008cA_\u00fb\u00c9u\u00e2\u000eR/m\u008d\u009cM\u0010\u00dfUn\u00e1\u00e8J\u00fa\u0091\u00d4\u009f\u0090b\u00c3\u0080O(\u0010\u00bc\u00a9iE\u0090K\u008d\u0019\u00be\u00f5\u0005\u0096\u00e5\u001d\u00f3\u00ac\u0010\u00cd\u0096\u00c4h\u00c3\u0088\u00c8\u00a6\u009e\u00f9\u00fe\u00d3j\u00f1i\u00a1";
                    var13_6 = 83;
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
                        var14_3[var12_4++] = IpcPacket.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "SqZ\n\u0005\u0018\u0095\u0001\u00dd>\u009d,2\u00e7\u00a9z\u0010\u00f0Y\u0096L\u0090\u00d0UL1\u0019\u00fe!\u0018F\u00cd\u00bf";
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
                        var14_3[var12_4++] = IpcPacket.zkmDecodeUtf8(var15_9).intern();
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
            IpcPacket.c = var14_3;
            IpcPacket.d = new String[6];
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
        var4_12 = var0_10.doFinal(new byte[]{115, 78, -14, 50, -53, -57, 36, -115});
        ** while (true)
        IpcPacket.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1078;
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
                throw new RuntimeException("su/catlean/gk", exception);
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
            IpcPacket.d[n2] = IpcPacket.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = IpcPacket.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(IpcPacket.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

