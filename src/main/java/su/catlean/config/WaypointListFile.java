/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.Saveable;
import su.catlean.config.WaypointList;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class WaypointListFile
implements Saveable {
    @NotNull
    private final Json json;
    @NotNull
    private final File E;
    public WaypointList data;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public WaypointListFile(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.E = new File(ConfigFiles.getCtlDir(), "waypoints.ctlon");
    }

    @NotNull
    public final WaypointList getData() {
        WaypointList waypointList = this.data;
        if (waypointList != null) {
            return waypointList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    public final void setData(@NotNull WaypointList waypointList) {
        Intrinsics.checkNotNullParameter(waypointList, "<set-?>");
        this.data = waypointList;
    }

    @Override
    public void save() {
        Object object;
        WaypointListFile waypointListFile = this;
        if (this.E.createNewFile()) {
            object = new WaypointList(new ArrayList());
        } else {
            Object object2;
            WaypointListFile waypointListFile2 = waypointListFile;
            try {
                waypointListFile = waypointListFile2;
                object2 = this.json;
                String string = FilesKt.readText$default(this.E, null, 1, null);
                ((Json)object2).getSerializersModule();
                object2 = (WaypointList)((Json)object2).decodeFromString(WaypointList.Companion.serializer(), string);
            }
            catch (Exception exception) {
                waypointListFile = waypointListFile2;
                Globals.getLogger().warn("Waypoints are not loaded from file for some reasons.");
                object2 = new WaypointList(new ArrayList());
            }
            object = object2;
        }
        WaypointList waypointList = object;
        waypointListFile.setData(waypointList);
    }

    @Override
    public void load(int n, int n2) {
        Json json = this.json;
        WaypointList waypointList = this.getData();
        json.getSerializersModule();
        FilesKt.writeText$default(this.E, json.encodeToString(WaypointList.Companion.serializer(), waypointList), null, 2, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                WaypointListFile.a = ZkmKeys.a(5732193083935570093L, 5933953737853453803L, MethodHandles.lookup().lookupClass()).a(165257755444180L);
                WaypointListFile.d = new HashMap<K, V>(13);
                var0 = WaypointListFile.a ^ 29189185708627L;
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
                var9_3 = new String[5];
                var7_4 = 0;
                var6_5 = "x\u00e3\u000bz\u00ff\u00a6\u00c5\u00a5\u00a6\u008b>-_\u0083B\u0086 \u00e5\u00d7\u007f\u00ca\u0094\u0005~\u0090\u0090\u00a9\u001a\u00fa\u001e\u00f9Z\u00a2\u00ef-\u001b%,U\u0093J\u0018\u0006/6i\u00cdN\u001b\u0010P.\u00f6Io\u0087>\u00b1\u008a\u00d9Z\u00b6GB\u00b5\u0011";
                var8_6 = 66;
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
                    var9_3[var7_4++] = WaypointListFile.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "z\u009e;\u00acN\u001a1\u00efR\rIB\u0014\u00c5\u00c9\u00d4X\u0096L\u00fdL\u00ea\u00c1A\u00ca\u00c3\u00c7_`\u0083|:\u00fd\u00d4\u00b4\\(\u00a4\u00b0\u00caO\u0017\u00e1\u0019u\u00da\u0011\u001b\u00b2\u0006\u00b9^\u00a7R\\\u0095_\u0017\u00a2\u0082\u00ea\u0013w\u001c\u00f38\u0084\u00f4\u001f\u0010\u0006\u00ea'\u0002\u0081\u0089\u00d4\u00c7ZP>XS\u00837}\u00b0h\u0010\u009bs\u0088\u0091=+\u00e3`\u00cf\u00bb\u00e9\u00ebc\u00d9xE";
                    var8_6 = 105;
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
                    var9_3[var7_4++] = WaypointListFile.zkmDecodeUtf8(var10_9).intern();
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
        WaypointListFile.b = var9_3;
        WaypointListFile.c = new String[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3C73;
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
                throw new RuntimeException("su/catlean/c_", exception);
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
            WaypointListFile.c[n2] = WaypointListFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WaypointListFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/c_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WaypointListFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

