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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ConfigManager;
import su.catlean.config.HudConfig;
import su.catlean.config.JsonConfigFile;
import su.catlean.config.SettingEntry;
import su.catlean.config.SettingsConfig;
import su.catlean.setting.Setting;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class HudConfigFile
extends JsonConfigFile {
    @NotNull
    private final ConfigManager sharedSource;
    @NotNull
    private final File A;
    public HudConfig current;
    public List r;
    private static String[] V;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map h;
    private static final long[] m;
    private static final Integer[] n;
    private static final Map o;

    public HudConfigFile(@NotNull Json json, @NotNull ConfigManager configManager) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(configManager, "sharedSource");
        super(json);
        this.sharedSource = configManager;
        this.A = new File(ConfigFiles.getCtlDir(), "widgets");
    }

    @Override
    @NotNull
    public File U() {
        return this.A;
    }

    @NotNull
    public final HudConfig getCurrent() {
        HudConfig hudConfig = this.current;
        if (hudConfig != null) {
            return hudConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("current");
        return null;
    }

    public final void setCurrent(@NotNull HudConfig hudConfig) {
        Intrinsics.checkNotNullParameter(hudConfig, "<set-?>");
        this.current = hudConfig;
    }

    @NotNull
    public final List K() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        List list = this.r;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException((String)((Object)HudConfigFile.zkmBootstrap("n", (int)886, (long)(0x2D17BEE1F8AAB40L ^ l))));
        return null;
    }

    public final void H(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.r = list;
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
            var13_1 = v0;
            var31_2 = this;
            try {
                block10: {
                    block9: {
                        var14_3 = this;
                        var32_4 = var31_2;
                        if (var14_3.U().exists() && var14_3.U().isDirectory()) break block9;
                        v1 /* !! */  = new ArrayList<E>();
                        break block10;
                    }
                    var16_5 /* !! */  = var14_3.U().listFiles();
                    if (var16_5 /* !! */  == null) ** GOTO lbl-1000
                    var17_6 = var16_5 /* !! */ ;
                    var19_7 = var17_6;
                    var20_8 = new ArrayList<E>();
                    var23_10 = ((Object)var19_7).length;
                    for (var22_9 = 0; var22_9 < var23_10; ++var22_9) {
                        var25_13 = var24_12 = var19_7[var22_9];
                        Intrinsics.checkNotNull(var25_13);
                        if (!Intrinsics.areEqual(FilesKt.getExtension((File)var25_13), "ctlon")) continue;
                        var20_8.add(var24_12);
                    }
                    var18_14 /* !! */  = (List)var20_8;
                    var20_8 = var18_14 /* !! */ ;
                    var21_15 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var18_14 /* !! */ , 10));
                    for (Object var24_12 : var20_8) {
                        var25_13 = (File)var24_12;
                        var27_16 = var21_15;
                        var28_17 = JsonConfigFile.access$getJson((JsonConfigFile)var14_3);
                        Intrinsics.checkNotNull(var25_13);
                        var29_18 = FilesKt.readText$default((File)var25_13, null, 1, null);
                        var28_17.getSerializersModule();
                        var27_16.add(var28_17.decodeFromString(HudConfig.Companion.serializer(), var29_18));
                    }
                    var18_14 /* !! */  = CollectionsKt.toMutableList((List)var21_15);
                    if (var18_14 /* !! */  != null) {
                        v1 /* !! */  = var18_14 /* !! */ ;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v1 /* !! */  = new ArrayList<E>();
                    }
                }
                v2 = var32_4;
                var14_3 = v1 /* !! */ ;
            }
            catch (Exception var15_19) {
                v2 = var31_2;
                Globals.getLogger().warn("Widgets are not loaded from file for some reasons.");
                var14_3 = new ArrayList<E>();
            }
            v2.H((List)var14_3);
            var15_20 = this.K();
            var31_2 = this;
            var17_6 = var15_20.iterator();
            while (var17_6.hasNext()) {
                var18_14 /* !! */  = var17_6.next();
                var19_7 = (HudConfig)var18_14 /* !! */ ;
                if (!Intrinsics.areEqual(var19_7.getId(), this.sharedSource.getData().getWidgetsConfig())) continue;
                v3 = var18_14 /* !! */ ;
                break block8;
            }
            v3 = null;
        }
        v4 = var31_2;
        v5 = v3;
        if (v5 == null) {
            var15_20 = new HudConfig(this.sharedSource.getData().getWidgetsConfig(), null, null);
            var16_5 /* !! */  = var15_20;
            var31_2 = v4;
            this.K().add(var16_5 /* !! */ );
            v4 = var31_2;
            v5 = var15_20;
        }
        v4.setCurrent((HudConfig)v5);
    }

    @Override
    public void load(int n, int n2) {
        Iterable iterable = this.K();
        String string = ConfigManager.zkmGetFlag();
        for (Object t : iterable) {
            Object object;
            HudConfig hudConfig = (HudConfig)t;
            Object object2 = object = new File(this.U(), hudConfig.getId() + ".ctlon");
            ((File)object2).createNewFile();
            File file = object;
            object = this.getJson();
            object2 = hudConfig;
            ((Json)object).getSerializersModule();
            FilesKt.writeText$default(file, ((Json)object).encodeToString(HudConfig.Companion.serializer(), object2), null, 2, null);
            String string2 = string;
            if (n2 <= 0) continue;
            string2 = string;
        }
    }

    public final void findById(@NotNull List list, @NotNull String string) {
        HudConfig hudConfig;
        Object v1;
        block4: {
            String string2 = ConfigManager.zkmGetFlag();
            Intrinsics.checkNotNullParameter(list, "widgets");
            String string3 = string2;
            Intrinsics.checkNotNullParameter(string, "id");
            Iterable iterable = this.K();
            for (Object t : iterable) {
                HudConfig hudConfig2 = (HudConfig)t;
                if (!Intrinsics.areEqual(hudConfig2.getId(), string)) continue;
                v1 = t;
                break block4;
            }
            v1 = null;
        }
        if ((hudConfig = (HudConfig)v1) == null) {
            hudConfig = new HudConfig(string, null, null);
        }
        HudConfig hudConfig3 = hudConfig;
        Iterable iterable = list;
        HudConfig hudConfig4 = hudConfig3;
        Iterable iterable2 = iterable;
        Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object e : iterable2) {
            HudElement hudElement = (HudElement)e;
            Collection collection2 = collection;
            Iterable iterable3 = hudElement.getSettings();
            String string4 = hudElement.getId();
            Iterable iterable4 = iterable3;
            Collection collection3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
            for (Object t : iterable4) {
                Setting setting2 = (Setting)t;
                Collection collection4 = collection3;
                collection4.add(new SettingEntry(setting2.getId(), setting2.toString()));
            }
            List list2 = (List)collection3;
            String string5 = string4;
            collection2.add(new SettingsConfig(string5, list2));
        }
        hudConfig4.setWidgets((List)collection);
        this.K().removeIf(arg_0 -> HudConfigFile.invokePredicate(arg_0 -> HudConfigFile.findById$lambda$0(string, arg_0), arg_0));
        this.K().add(hudConfig3);
    }

    private static final boolean findById$lambda$0(String string, HudConfig hudConfig) {
        Intrinsics.checkNotNullParameter(hudConfig, "it");
        return Intrinsics.areEqual(hudConfig.getId(), string);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    public static void zkmSetFlag(String[] stringArray) {
        V = stringArray;
    }

    public static String[] java_lang_String_arr_zkmGetFlag() {
        return V;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        HudConfigFile.a = ZkmKeys.a(-2121960723099750115L, 1228622568344113646L, MethodHandles.lookup().lookupClass()).a(146311957694098L);
                        var20 = HudConfigFile.a ^ 85593917996963L;
                        HudConfigFile.h = new HashMap<K, V>(13);
                        HudConfigFile.zkmSetFlag(new String[2]);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[13];
                        var16_4 = 0;
                        var15_5 = "y\u009av\u0083\u008b\u00c2B\u00a6\u00dc\u00ce\u00ef\u001fu\u00b66\u001b\u0010O=O\u00b0\t\u00f7=\u008c\u00c3d\u0007\u00b9\u00c3I\u00f1\u0017 \u0099\u00e6o{!j\u0098>k\u00af\u00f5W\u00bf\u00be\\\u00fda\u00b0K\u001c2h\u00acb\u00eb\u00ceFd\u00b9\u00d9\u00b8\u0017\u0010>\u00fe\u00e0\u008e\u0088\u00fb\u00f4\b\u00de\n=\u00dbK\u00dd\u00ea\u00f8\u0010T\u00e8\u00a4x\u00d6\u00c5F\u00a2\u00f9:4C\u0092/n\u000e\u0010\u0088/\u00192\u00eb\u00a8\u00e0X%\u009f\u00f9\u0019\u00ce\u00e9h\u000e\u0010+`>L\u00b8k<\u0091\u0087\u00ab\u00d72\u00ff?\u00db/\u0010\u00f6\u00fa\u008e\u00dc\u0004\u00a5\u00ee6\u00d6\u0006\u00c6\u00af\u00b8\u00e6\u00a1\u00ae\u0010\nE>\u00ec\u00ec\u00cf2\f\u0099{\u00df\u00a4\u00b9\u00b9\u00actX\u00ad\u0099\u00d4\u0084\u001e\u00c6\u000fc\u0016u}\u00d3\u00b3\u00f2au\u00f4\u00ee\u008cB\u00ef}\u00cb\u00a2\u00ba9\u0095LG\u00e7\u00d8\u00de\u0001\u00f1\u0096;\u00bewZ)\u001d7\u008f\u009e\u00d3%R.?e\u0092\u00a2\u00da\u00de\u00c7\u008a\u008a\u0000AG\u001emE@\u00dd@\u00140xO\u0014\u00b9\u00c5\u00a0X)\u00aa_\u00c5/\u0002\u00048\u0095'\u00f7\u00031\u0010\u00d5r\u009f\u00d1\u00c2\u00bc|/u\\\u000fd\u00d1\u00a9\t\u00ff";
                        var17_6 = "y\u009av\u0083\u008b\u00c2B\u00a6\u00dc\u00ce\u00ef\u001fu\u00b66\u001b\u0010O=O\u00b0\t\u00f7=\u008c\u00c3d\u0007\u00b9\u00c3I\u00f1\u0017 \u0099\u00e6o{!j\u0098>k\u00af\u00f5W\u00bf\u00be\\\u00fda\u00b0K\u001c2h\u00acb\u00eb\u00ceFd\u00b9\u00d9\u00b8\u0017\u0010>\u00fe\u00e0\u008e\u0088\u00fb\u00f4\b\u00de\n=\u00dbK\u00dd\u00ea\u00f8\u0010T\u00e8\u00a4x\u00d6\u00c5F\u00a2\u00f9:4C\u0092/n\u000e\u0010\u0088/\u00192\u00eb\u00a8\u00e0X%\u009f\u00f9\u0019\u00ce\u00e9h\u000e\u0010+`>L\u00b8k<\u0091\u0087\u00ab\u00d72\u00ff?\u00db/\u0010\u00f6\u00fa\u008e\u00dc\u0004\u00a5\u00ee6\u00d6\u0006\u00c6\u00af\u00b8\u00e6\u00a1\u00ae\u0010\nE>\u00ec\u00ec\u00cf2\f\u0099{\u00df\u00a4\u00b9\u00b9\u00actX\u00ad\u0099\u00d4\u0084\u001e\u00c6\u000fc\u0016u}\u00d3\u00b3\u00f2au\u00f4\u00ee\u008cB\u00ef}\u00cb\u00a2\u00ba9\u0095LG\u00e7\u00d8\u00de\u0001\u00f1\u0096;\u00bewZ)\u001d7\u008f\u009e\u00d3%R.?e\u0092\u00a2\u00da\u00de\u00c7\u008a\u008a\u0000AG\u001emE@\u00dd@\u00140xO\u0014\u00b9\u00c5\u00a0X)\u00aa_\u00c5/\u0002\u00048\u0095'\u00f7\u00031\u0010\u00d5r\u009f\u00d1\u00c2\u00bc|/u\\\u000fd\u00d1\u00a9\t\u00ff".length();
                        var14_7 = 16;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = HudConfigFile.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00f6\u0098\u00bf\u00ee\u0007`\u00b6\u0095\u0015\u00c4\u00a1\u00af\u00eeW&'\u0010\t\u00d4\u0099\u00be\u0094\u00fd\u00fd\u0093\u00b6\u00ab3\u00d94G\u00e9\u00be";
                            var17_6 = "\u00f6\u0098\u00bf\u00ee\u0007`\u00b6\u0095\u0015\u00c4\u00a1\u00af\u00eeW&'\u0010\t\u00d4\u0099\u00be\u0094\u00fd\u00fd\u0093\u00b6\u00ab3\u00d94G\u00e9\u00be".length();
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = HudConfigFile.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                HudConfigFile.c = var18_3;
                HudConfigFile.d = new String[13];
                HudConfigFile.o = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "\u0017\u0092\u009d\u00c6\u00fdg\u0097\u008c\u00f0~\u0004\u00b9\u00c5\u00cf\u0090g";
                var5_15 = "\u0017\u0092\u009d\u00c6\u00fdg\u0097\u008c\u00f0~\u0004\u00b9\u00c5\u00cf\u0090g".length();
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl74:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        HudConfigFile.m = var6_12;
        HudConfigFile.n = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x630B;
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
                throw new RuntimeException("su/catlean/oe", exception);
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
            HudConfigFile.d[n2] = HudConfigFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudConfigFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/oe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3FF;
        if (HudConfigFile.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = m[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])o.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/oe", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HudConfigFile.n[n2] = n3;
        }
        return HudConfigFile.n[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HudConfigFile.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/oe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudConfigFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(HudConfigFile.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

