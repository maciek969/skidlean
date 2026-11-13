/*
 * Decompiled with CFR 0.152.
 */
package su.catlean;

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
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import su.catlean.ExcludeCommon;
import su.catlean.PreLaunch;
import su.catlean.api.IPasteable;
import su.catlean.api.event.events.client.InitEvent;
import su.catlean.api.event.events.client.PostTitleScreenInitEvent;
import su.catlean.core.EventBusSetup;
import su.catlean.core.FontSetup;
import su.catlean.gofra.Flow;
import su.catlean.gui.VersionInfoScreen;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.util.ShutdownHooks;
import su.catlean.util.VersionUtil;
import su.catlean.zkm.ZkmKeys;

@ExcludeCommon
@Metadata(mv={2, 2, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\nH\u0003\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lsu/catlean/CatLean;", "Lsu/catlean/api/IPasteable;", "CatLean", "()V", "", "onInitializeClient", "Lsu/catlean/api/event/events/client/InitEvent;", "event", "M", "(Lsu/catlean/api/event/events/client/InitEvent;)V", "Lsu/catlean/api/event/events/client/PostTitleScreenInitEvent;", "X", "(Lsu/catlean/api/event/events/client/PostTitleScreenInitEvent;)V", "catlean"})
public final class CatLean
implements IPasteable {
    @NotNull
    public static final CatLean INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private CatLean() {
    }

    public void onInitializeClient() {
        ShutdownHooks.hooks(this);
        EventBusSetup.bus(this);
    }

    @Flow
    private final void onInit(InitEvent initEvent) {
        long l = a ^ 0x15F20453D038L;
        PreLaunch.INSTANCE.L();
        FontSetup.font(this);
        I18n.INSTANCE.loadLanguages();
        System.setProperty((String)((Object)CatLean.zkmBootstrap("k", (int)25733, (long)(0x44CF06E0B060A026L ^ l))), (String)((Object)CatLean.zkmBootstrap("k", (int)24705, (long)(0x62226F17B7D12423L ^ l))) + System.getProperty((String)((Object)CatLean.zkmBootstrap("k", (int)29840, (long)(0x75BDA4C8A3373031L ^ l)))));
    }

    @Flow
    private final void onPostTitleScreenInit(PostTitleScreenInitEvent postTitleScreenInitEvent) {
        block0: {
            if (!VersionUtil.V()) break block0;
            Globals.getMc().setScreen(new VersionInfoScreen());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(898947561653736876L, -2409245294003337710L, MethodHandles.lookup().lookupClass()).a(59484523516562L);
        d = new HashMap(13);
        long l = a ^ 0x3325FC059C20L;
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
        String string = "@8\u00a4\u00d9^\u00b2-\u000eK\u00899\u00f1\u00df\u00c8\u009d\u00c7\u00f4E\u008a\u00bc\u00ee\u00ffe\u00e1\u00fce\u00f1\u0094g\"\u001b\u00ea\u0010\u0083x\u00bb\u00c2\u00d1\u00b6\u008f\b\u0016\u0088h\u00a0\u00c7\u0010\u000b\u00aa B\u00f0\u009f\u00bc\u008bQ\u00ff\u001c\u00dc\u00eb(2<\u00da?K@y\u0010\u00b8\u00a8PFX\u0018\u00dc)\u0084\u00aeP\u00f4f";
        int n2 = 82;
        int n3 = 32;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = CatLean.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                INSTANCE = new CatLean();
                return;
            }
            n3 = string.charAt(n4);
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xB56;
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
                throw new RuntimeException("su/catlean/CatLean", exception);
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
            CatLean.c[n2] = CatLean.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CatLean.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/CatLean" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CatLean.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

