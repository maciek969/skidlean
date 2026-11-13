/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.util;

import com.google.common.collect.Lists;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.PostTickEvent;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Module;
import su.catlean.util.DelayedTask;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Async
implements Manager {
    @NotNull
    public static final Async INSTANCE;
    @NotNull
    private static AtomicBoolean running;
    @NotNull
    private static ExecutorService executor;
    @NotNull
    private static List tasks;
    private static boolean U;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private Async() {
    }

    @NotNull
    public final AtomicBoolean getRunning() {
        return running;
    }

    public final void setRunning(@NotNull AtomicBoolean atomicBoolean) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(atomicBoolean, (String)((Object)Async.zkmBootstrap("j", (int)20958, (long)(0x14C1E2A2090E8E1FL ^ l))));
        running = atomicBoolean;
    }

    @NotNull
    public final ExecutorService getExecutor() {
        return executor;
    }

    public final void setExecutor(@NotNull ExecutorService executorService) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(executorService, (String)((Object)Async.zkmBootstrap("j", (int)30239, (long)(0x60E5E05B5CF0FAFEL ^ l))));
        executor = executorService;
    }

    @NotNull
    public final List getTasks() {
        return tasks;
    }

    public final void setTasks(@NotNull List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(list, (String)((Object)Async.zkmBootstrap("j", (int)20958, (long)(0x14C1A341323751FCL ^ l))));
        tasks = list;
    }

    @Flow(priority=20)
    private final void onTick(TickEvent tickEvent) {
        running.set(true);
    }

    @Flow(priority=-20)
    private final void onPostTick(PostTickEvent postTickEvent) {
        running.set(false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isMainThread() {
        boolean bl = Timer.n();
        boolean bl2 = Globals.getMc().isOnThread();
        if (!bl) return bl2;
        if (bl2) return true;
        bl2 = running.get();
        if (!bl) return bl2;
        if (bl2) return false;
        return true;
    }

    @Flow(priority=20)
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        ArrayList arrayList = Lists.newArrayList((Iterable)Globals.getWorld().getPlayers());
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        tasks = arrayList;
    }

    public final void runDelayed(int n, @NotNull Function0 function0) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)Async.zkmBootstrap("j", (int)3552, (long)(0x5DCF74689902EDD2L ^ l))));
        this.getExecutor().execute(new DelayedTask(n, function0));
    }

    public static void J(Async async, long l, int n, Function0 function0, int n2, Object object) {
        block3: {
            Object object2;
            block2: {
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-3976903044339037696L, (long)l);
                int n3 = n2 & 1;
                if (callSite == false) {
                    if (n3 != 0) {
                        n = 0;
                    }
                    Intrinsics.checkNotNullParameter(function0, (String)((Object)Async.zkmBootstrap("j", (int)14372, (long)(0x5F5284380606C4F6L ^ l))));
                    n3 = 0;
                }
                async.getExecutor().execute(new DelayedTask(n, function0));
                object2 = callSite;
                if (l <= 0L) break block2;
                if (object2 == false) break block3;
                object2 = 4;
            }
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[object2], (long)-3961250983983660229L, (long)l);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                Async.a = ZkmKeys.a(1694520301718390403L, 8890729676895669104L, MethodHandles.lookup().lookupClass()).a(177800358160334L);
                var9 = Async.a ^ 137244189963026L;
                Async.d = new HashMap<K, V>(13);
                Async.zkmSetFlag(false);
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
                var4_5 = "\u001do\u00d1\u00a1\u00f7\b \u00c7q\u00df\u00a4\u00c1\u00a8\u00b9\f\u00b5\u001c\u00df.@n\u008e\u00db7.\u00f8\u0092\u00bd=\u00e8;\u0003\u0091\u00ab\u00a1\u00ab\u0085s\u00c3|r\u0086\u00ae2\u0010\u00d7\u00d5\u00a4\u0082\u00a2\u00de\u0081\u00cf WU\u00dd\u0099R\u00ddf}`T(g\u0014\u00a1\u00e4\u00da7\u001cG\u00ee+o\u00bc2\u00d7/F\u0017=:\u00de\u00c4\\#\u00a6\u0092\u00fc\u00d7VOIjd2t~\u00c5y\u0003\u001c\u00d6 \u00c25[\u00c4\u00f1'5_,\f\fS\u008fAV\u00d3\u00a8Z\b\u00aa\u00afQ\u00e1^\u008aq\u00d0G\u00a7=S9\u0010E)c4*G\u00ef\u00bc\u0080-$\u0011\u00bb\u00d7\u00a2\u00d2";
                var6_6 = 155;
                var3_7 = 64;
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
                    var7_3[var5_4++] = Async.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00e7\u0094s\u00c8\u0091\u00ce\u001c\u00ee?\u00c8\u00eaw4\u00b5\u00e6\u00ff l\u00e7N3\u00f0\u00ea\u00d2O>\b9\u0085\u001d\u00bfK\u00a4\u00de}\u001f\u00c2\u0019\u00af\u001f\u0082\u009a\u009a\t\u0086y\u00ab\u00a8!";
                    var6_6 = 49;
                    var3_7 = 16;
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
                    var7_3[var5_4++] = Async.zkmDecodeUtf8(var8_9).intern();
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
        Async.b = var7_3;
        Async.c = new String[6];
        Async.INSTANCE = new Async();
        Async.running = new AtomicBoolean(false);
        v7 = Executors.newVirtualThreadPerTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(v7, "newVirtualThreadPerTaskExecutor(...)");
        Async.executor = v7;
        Async.tasks = CollectionsKt.emptyList();
    }

    public static void zkmSetFlag(boolean bl) {
        U = bl;
    }

    public static boolean zkmGetFlag() {
        return U;
    }

    public static boolean t() {
        return true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x34F1;
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
                throw new RuntimeException("su/catlean/bo", exception);
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
            Async.c[n2] = Async.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Async.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/bo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Async.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

