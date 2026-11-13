/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import su.catlean.module.Module;
import su.catlean.module.misc.Translator;
import su.catlean.util.ModuleMessages;
import su.catlean.zkm.ZkmKeys;

public final class Translator$onSendPacket$1
implements Runnable {
    final int B;
    final ChatMessageC2SPacket j;
    private static final long a = ZkmKeys.a(-7093673972862245032L, -432391920590358905L, MethodHandles.lookup().lookupClass()).a(8804299783378L);
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public Translator$onSendPacket$1(int n, ChatMessageC2SPacket chatMessageC2SPacket) {
        this.B = n;
        this.j = chatMessageC2SPacket;
    }

    @Override
    public final void run() {
        Thread.sleep(this.B);
        try {
            String string = this.j.comp_945();
            Intrinsics.checkNotNullExpressionValue(string, "message(...)");
            String string2 = Translator.access$getOutMessages(Translator.INSTANCE).name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string2, "toLowerCase(...)");
            String string3 = Translator.access$translate(Translator.INSTANCE, string, string2);
            if (Objects.equals(this.j.comp_945(), string3)) {
                Translator.q(this.j.comp_945());
                Module module = Translator.INSTANCE;
                String string4 = this.j.comp_945();
                Intrinsics.checkNotNullExpressionValue(string4, "message(...)");
                ModuleMessages.message(module, string4, true);
            } else {
                Translator.q(string3);
                ModuleMessages.message(Translator.INSTANCE, string3, true);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        d = new HashMap(13);
        long l = a ^ 0xB2756BBEB10L;
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
        String string = "\u00d1e\u00f9vp\u00d79\u0084\u0016\u00d6\u00be\u00c6K:Qb\"B^\u00c5\u008d\u0097\u00e6\u009a?\u00c9\u00e5\u00d2\u00ac\u00d655\u00fd\u00df\u0097\u00e6\u00ac\u0093\u00f33 \u0010i)\u0084\u00ff\u00f9\u00b5\u0006\u008c\u008bB\u0087\u00cb`\u0080\f6\u0016\"\u00c8\u008f\u00d9i\u00a9?\u00b5\u00d6\u00fe\\[\u00f4\u00b2\u0018m^'$R\u00c8\u00c2\u00ce\u0094\u00bfO\u00e8\u001cu*\u0093;\u0084\u00a7\u00c7\u00b5\u00bew\u008d";
        int n2 = 98;
        int n3 = 40;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = Translator$onSendPacket$1.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x78C3;
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
                throw new RuntimeException("su/catlean/c0", exception);
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
            Translator$onSendPacket$1.c[n2] = Translator$onSendPacket$1.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Translator$onSendPacket$1.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/c0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Translator$onSendPacket$1.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

