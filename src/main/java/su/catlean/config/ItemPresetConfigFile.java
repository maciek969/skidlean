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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ConfigManager;
import su.catlean.config.ItemPreset;
import su.catlean.config.JsonConfigFile;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ItemPresetConfigFile
extends JsonConfigFile {
    @NotNull
    private final ConfigManager sharedSource;
    @NotNull
    private final File B;
    public ItemPreset current;
    public List data;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map h;
    private static final long i;

    public ItemPresetConfigFile(@NotNull Json json, @NotNull ConfigManager configManager) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(configManager, "sharedSource");
        super(json);
        this.sharedSource = configManager;
        this.B = new File(ConfigFiles.getCtlDir(), "inventories");
    }

    @Override
    @NotNull
    public File U() {
        return this.B;
    }

    @NotNull
    public final ItemPreset getCurrent() {
        ItemPreset itemPreset = this.current;
        if (itemPreset != null) {
            return itemPreset;
        }
        Intrinsics.throwUninitializedPropertyAccessException("current");
        return null;
    }

    public final void setCurrent(@NotNull ItemPreset itemPreset) {
        Intrinsics.checkNotNullParameter(itemPreset, "<set-?>");
        this.current = itemPreset;
    }

    @NotNull
    public final List getData() {
        List list = this.data;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    public final void setData(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void save() {
        block8: {
            v0 = ConfigManager.zkmGetFlag();
            this.U().mkdirs();
            var11_1 = v0;
            var29_2 = this;
            try {
                block10: {
                    block9: {
                        var12_3 = this;
                        var30_4 = var29_2;
                        if (var12_3.U().exists() && var12_3.U().isDirectory()) break block9;
                        v1 /* !! */  = new ArrayList<E>();
                        break block10;
                    }
                    var14_5 /* !! */  = var12_3.U().listFiles();
                    if (var14_5 /* !! */  == null) ** GOTO lbl-1000
                    var15_6 = var14_5 /* !! */ ;
                    var17_7 = var15_6;
                    var18_8 = new ArrayList<E>();
                    var21_10 = ((Object)var17_7).length;
                    for (var20_9 = 0; var20_9 < var21_10; ++var20_9) {
                        var23_13 = var22_12 = var17_7[var20_9];
                        Intrinsics.checkNotNull(var23_13);
                        if (!Intrinsics.areEqual(FilesKt.getExtension((File)var23_13), "ctlon")) continue;
                        var18_8.add((Object)var22_12);
                    }
                    var16_14 /* !! */  = (List)var18_8;
                    var18_8 = var16_14 /* !! */ ;
                    var19_15 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var16_14 /* !! */ , (int)ItemPresetConfigFile.i));
                    for (Object var22_12 : var18_8) {
                        var23_13 = (File)var22_12;
                        var25_16 = var19_15;
                        var26_17 = JsonConfigFile.access$getJson((JsonConfigFile)var12_3);
                        Intrinsics.checkNotNull(var23_13);
                        var27_18 = FilesKt.readText$default((File)var23_13, null, 1, null);
                        var26_17.getSerializersModule();
                        var25_16.add(var26_17.decodeFromString(ItemPreset.Companion.serializer(), var27_18));
                    }
                    var16_14 /* !! */  = CollectionsKt.toMutableList((List)var19_15);
                    if (var16_14 /* !! */  != null) {
                        v1 /* !! */  = var16_14 /* !! */ ;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v1 /* !! */  = new ArrayList<E>();
                    }
                }
                v2 = var30_4;
                var12_3 = v1 /* !! */ ;
            }
            catch (Exception var13_19) {
                v2 = var29_2;
                Globals.getLogger().warn("Inventories are not loaded from file for some reasons.");
                var12_3 = new ArrayList<E>();
            }
            v2.setData((List)var12_3);
            var13_20 = this.getData();
            var29_2 = this;
            var15_6 = var13_20.iterator();
            while (var15_6.hasNext()) {
                var16_14 /* !! */  = var15_6.next();
                var17_7 = (ItemPreset)var16_14 /* !! */ ;
                if (!Intrinsics.areEqual(var17_7.getId(), this.sharedSource.getData().getInventoriesConfig())) continue;
                v3 = var16_14 /* !! */ ;
                break block8;
            }
            v3 = null;
        }
        v4 = var29_2;
        v5 = v3;
        if (v5 == null) {
            var13_20 = new ItemPreset(this.sharedSource.getData().getInventoriesConfig(), new LinkedHashMap<K, V>());
            var14_5 /* !! */  = var13_20;
            var29_2 = v4;
            this.getData().add(var14_5 /* !! */ );
            v4 = var29_2;
            v5 = var13_20;
        }
        v4.setCurrent((ItemPreset)v5);
    }

    @Override
    public void load(int n, int n2) {
        Iterable iterable = this.getData();
        String string = ConfigManager.zkmGetFlag();
        for (Object t : iterable) {
            Object object;
            ItemPreset itemPreset = (ItemPreset)t;
            Object object2 = object = new File(this.U(), itemPreset.getId() + ".ctlon");
            ((File)object2).createNewFile();
            File file = object;
            object = this.getJson();
            object2 = itemPreset;
            ((Json)object).getSerializersModule();
            FilesKt.writeText$default(file, ((Json)object).encodeToString(ItemPreset.Companion.serializer(), object2), null, 2, null);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ItemPresetConfigFile.a = ZkmKeys.a(7294950749699296300L, -9011224739821243123L, MethodHandles.lookup().lookupClass()).a(113526740463639L);
                    ItemPresetConfigFile.h = new HashMap<K, V>(13);
                    var5 = ItemPresetConfigFile.a ^ 54201803568559L;
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
                    var14_3 = new String[10];
                    var12_4 = 0;
                    var11_5 = "\u00fa\u00b7\u00cd\u0015z7\u00a5\u00ab\u00d3^\u00ab\u00f5+\u0013\u0017l\u0010\u0000\u00ca\u00e6w1>\u007f\u0016\u00e6\u009c\b\u00d2*\u00a9h\b\u00187J8K\r\u00bb\u0000B&@#\u007f/\u00e1\u00a8T\u00d5\u000eJK\u00b4\u00a6e\u00bdX>\u00fb\u00fck\u008d\u00bcTY\u0091}\u009bZ\u00169y\u0016\u008d\u00f0\u00fe?\u00ce'\u00e5\u00e4\u00d5\u0084\u00c58 \u00c9\u00de\u001fh\u00bc\u00d8H\u00d3\u00ab$\u00c1\u0096S\u00ff\u0091\u00be\u00ee\u000eWz]\u0007\u00fc\u00b6\u00cc\u00142\u0094\u007fT0\n\u00e6=x\u00edQ\u00d4Id\u00fa/\u0096\u00f9\u00d1_\u00f4\u00ca3\u0015\u00c57\u00b6oy\u009cB;t\u0010H8\u0099\u00e48\u0016)\u00c6\u00c9\u00ec\u0092\u00a0\u00b1}\u009e\u0002\u0010\u00c0tJ\u00efj]\u00f2\u00beVK\u009eX\u0084oR\u00d5 F\u00e4\u001c{\u0086\u0017VrQ\u0001\u00a8x\u0085\u00b6\u0095Q\u0007}%\u00cc\u00d8\u001b\u009e\u00ben\u00c3\u0087.\u00e4\u00a2}Q\u0010\u001c\u00ad\u00cbb\u00c7'\u00b8\u00aaq\u00e1-x\u0093\u00b1\u00a5\u0084";
                    var13_6 = 231;
                    var10_7 = 16;
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
                        var14_3[var12_4++] = ItemPresetConfigFile.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "Z\u00a8t\u00d8R\u00ca\u00f0A\u0094\u00de\u00a6\u00b3\u00b2/\u00d1o\u00109\u009b\u00a1\u00d4\u0017\u00aac\u00e7\u009a+QM=\u00b84\u00bb";
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
                        var14_3[var12_4++] = ItemPresetConfigFile.zkmDecodeUtf8(var15_9).intern();
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
            ItemPresetConfigFile.c = var14_3;
            ItemPresetConfigFile.d = new String[10];
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
        var4_12 = var0_10.doFinal(new byte[]{20, 36, -113, -48, -87, 108, 72, -95});
        ** while (true)
        ItemPresetConfigFile.i = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7BED;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/od", exception);
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
            ItemPresetConfigFile.d[n2] = ItemPresetConfigFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemPresetConfigFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/od" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemPresetConfigFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

