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
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.EnumsKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.IpcCommand$Companion;
import su.catlean.zkm.ZkmKeys;

@Serializable
public final class IpcCommand
extends Enum {
    @NotNull
    public static final IpcCommand$Companion Companion;
    @NotNull
    private static final Lazy f;
    public static final IpcCommand DISPATCH;
    public static final IpcCommand SET_ACTIVITY;
    private static final IpcCommand[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private IpcCommand() {
        void var2_-1;
        void var1_-1;
    }

    public static IpcCommand[] values() {
        return (IpcCommand[])$VALUES.clone();
    }

    public static IpcCommand valueOf(String string) {
        return Enum.valueOf(IpcCommand.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final KSerializer serializer() {
        return EnumsKt.createSimpleEnumSerializer((String)"su.catlean.aw", (Enum[])IpcCommand.values());
    }

    private static final IpcCommand[] $values() {
        IpcCommand[] ipcCommandArray = new IpcCommand[]{DISPATCH, SET_ACTIVITY};
        return ipcCommandArray;
    }

    public static final Lazy O() {
        return f;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-6507926676261367734L, -7118833704870300995L, MethodHandles.lookup().lookupClass()).a(71660955810081L);
        long l = a ^ 0x6AB7A396E0A4L;
        d = new HashMap(13);
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
        String[] stringArray = new String[3];
        int n = 0;
        String string = "\u0002\u0012=~\u00f71\u00a0\u00d8)\u001bP\u00fc\u00c9}\u00f3\u00e7\u0097\u00bew\u00d1\u009b-\u001d\u00a1\u0087\u00ec\u00b6!p\u00dd\u0092\u0095 \u001fD\n\u0080\u00d1\u00b0e\u001b\u0012z\u00d3\u00c4\u00c6IQ0#\u0013\u00d1\u00be\u00dfu\u00ea\u00a5\u00fcxhl\u00b4\u0004\u00b4C \u00c0\u00ef\u00a5~\u008b\u00a9\u00d1?l\u00f3\u00a6\u00e5\u00fa\u009c\u0085\u00ab\u00f4U\u007f>\u00b8,}k`\u00c2g\u0014\u0096\u008d\u0086\u00ff";
        int n2 = 98;
        int n3 = 32;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = IpcCommand.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                DISPATCH = new IpcCommand("DISPATCH", 0);
                SET_ACTIVITY = new IpcCommand("SET_ACTIVITY", 1);
                $VALUES = IpcCommand.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                Companion = new IpcCommand$Companion(null);
                f = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, IpcCommand::serializer);
                return;
            }
            n3 = string.charAt(n4);
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6CD;
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
                throw new RuntimeException("su/catlean/aw", exception);
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
            IpcCommand.c[n2] = IpcCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = IpcCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/aw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(IpcCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

