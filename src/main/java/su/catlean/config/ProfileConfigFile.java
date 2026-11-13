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
import java.util.Iterator;
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
import su.catlean.config.JsonConfigFile;
import su.catlean.config.ProfileConfig;
import su.catlean.config.SettingEntry;
import su.catlean.config.SettingsConfig;
import su.catlean.module.Module;
import su.catlean.setting.Setting;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ProfileConfigFile
extends JsonConfigFile {
    @NotNull
    private final ConfigManager sharedSource;
    @NotNull
    private final File a;
    public List data;
    public ProfileConfig current;
    private static String I;
    private static final long c;
    private static final String[] d;
    private static final String[] h;
    private static final Map i;
    private static final long[] m;
    private static final Integer[] n;
    private static final Map o;

    public ProfileConfigFile(@NotNull Json json, @NotNull ConfigManager configManager) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(configManager, "sharedSource");
        super(json);
        this.sharedSource = configManager;
        this.a = new File(ConfigFiles.getCtlDir(), "modules");
    }

    @Override
    @NotNull
    public File U() {
        return this.a;
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

    @NotNull
    public final ProfileConfig getCurrent() {
        ProfileConfig profileConfig = this.current;
        if (profileConfig != null) {
            return profileConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("current");
        return null;
    }

    public final void setCurrent(@NotNull ProfileConfig profileConfig) {
        Intrinsics.checkNotNullParameter(profileConfig, "<set-?>");
        this.current = profileConfig;
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
            var30_1 = this;
            var12_2 = v0;
            try {
                block10: {
                    block9: {
                        var13_3 = this;
                        var31_4 = var30_1;
                        if (var13_3.U().exists() && var13_3.U().isDirectory()) break block9;
                        v1 /* !! */  = new ArrayList<E>();
                        break block10;
                    }
                    var15_5 /* !! */  = var13_3.U().listFiles();
                    if (var15_5 /* !! */  == null) ** GOTO lbl-1000
                    var16_6 = var15_5 /* !! */ ;
                    var18_7 = var16_6;
                    var19_8 = new ArrayList<E>();
                    var22_10 = ((Object)var18_7).length;
                    for (var21_9 = 0; var21_9 < var22_10; ++var21_9) {
                        var24_13 = var23_12 = var18_7[var21_9];
                        Intrinsics.checkNotNull(var24_13);
                        if (!Intrinsics.areEqual(FilesKt.getExtension((File)var24_13), "ctlon")) continue;
                        var19_8.add(var23_12);
                    }
                    var17_14 /* !! */  = (List)var19_8;
                    var19_8 = var17_14 /* !! */ ;
                    var20_15 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var17_14 /* !! */ , 10));
                    for (Object var23_12 : var19_8) {
                        var24_13 = (File)var23_12;
                        var26_16 = var20_15;
                        var27_17 = JsonConfigFile.access$getJson((JsonConfigFile)var13_3);
                        Intrinsics.checkNotNull(var24_13);
                        var28_18 = FilesKt.readText$default((File)var24_13, null, 1, null);
                        var27_17.getSerializersModule();
                        var26_16.add(var27_17.decodeFromString(ProfileConfig.Companion.serializer(), var28_18));
                    }
                    var17_14 /* !! */  = CollectionsKt.toMutableList((List)var20_15);
                    if (var17_14 /* !! */  != null) {
                        v1 /* !! */  = var17_14 /* !! */ ;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v1 /* !! */  = new ArrayList<E>();
                    }
                }
                v2 = var31_4;
                var13_3 = v1 /* !! */ ;
            }
            catch (Exception var14_19) {
                v2 = var30_1;
                Globals.getLogger().warn("Modules are not loaded from file for some reasons.");
                var13_3 = new ArrayList<E>();
            }
            v2.setData((List)var13_3);
            var14_20 = this.getData();
            var30_1 = this;
            var16_6 = var14_20.iterator();
            while (var16_6.hasNext()) {
                var17_14 /* !! */  = var16_6.next();
                var18_7 = (ProfileConfig)var17_14 /* !! */ ;
                if (!Intrinsics.areEqual(var18_7.getId(), this.sharedSource.getData().getModulesConfig())) continue;
                v3 = var17_14 /* !! */ ;
                break block8;
            }
            v3 = null;
        }
        v4 = var30_1;
        v5 = v3;
        if (v5 == null) {
            var14_20 = new ProfileConfig(this.sharedSource.getData().getModulesConfig(), null, null);
            var15_5 /* !! */  = var14_20;
            var30_1 = v4;
            this.getData().add(var15_5 /* !! */ );
            v4 = var30_1;
            v5 = var14_20;
        }
        v4.setCurrent((ProfileConfig)v5);
    }

    @Override
    public void load(int n, int n2) {
        Iterable iterable = this.getData();
        Iterator iterator2 = iterable.iterator();
        String string = ConfigManager.zkmGetFlag();
        while (iterator2.hasNext()) {
            Object object;
            Object t = iterator2.next();
            ProfileConfig profileConfig = (ProfileConfig)t;
            Object object2 = object = new File(this.U(), profileConfig.getId() + ".ctlon");
            ((File)object2).createNewFile();
            File file = object;
            object = this.getJson();
            object2 = profileConfig;
            ((Json)object).getSerializersModule();
            FilesKt.writeText$default(file, ((Json)object).encodeToString(ProfileConfig.Companion.serializer(), object2), null, 2, null);
            String string2 = string;
            if (n < 0) continue;
            string2 = string;
        }
    }

    public final void findById(@NotNull List list, @NotNull String string) {
        ProfileConfig profileConfig;
        Object v0;
        Object object;
        block4: {
            Intrinsics.checkNotNullParameter(list, "modules");
            Intrinsics.checkNotNullParameter(string, "id");
            Iterable iterable = this.getData();
            object = iterable.iterator();
            String string2 = ConfigManager.zkmGetFlag();
            while (object.hasNext()) {
                Object t = object.next();
                ProfileConfig profileConfig2 = (ProfileConfig)t;
                if (!Intrinsics.areEqual(profileConfig2.getId(), string)) continue;
                v0 = t;
                break block4;
            }
            v0 = null;
        }
        if ((profileConfig = (ProfileConfig)v0) == null) {
            profileConfig = new ProfileConfig(string, null, null);
        }
        ProfileConfig profileConfig3 = profileConfig;
        Iterable iterable = list;
        ProfileConfig profileConfig4 = profileConfig3;
        Iterable iterable2 = iterable;
        object = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object e : iterable2) {
            Module module = (Module)e;
            Object object2 = object;
            Iterable iterable3 = module.getSettings();
            String string3 = module.getId();
            Iterable iterable4 = iterable3;
            Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
            for (Object t : iterable4) {
                Setting setting2 = (Setting)t;
                Collection collection2 = collection;
                collection2.add(new SettingEntry(setting2.getId(), setting2.toString()));
            }
            List list2 = (List)collection;
            String string4 = string3;
            object2.add(new SettingsConfig(string4, list2));
        }
        profileConfig4.setModules((List)object);
        this.getData().removeIf(arg_0 -> ProfileConfigFile.invokePredicate(arg_0 -> ProfileConfigFile.findById$lambda$0(string, arg_0), arg_0));
        this.getData().add(profileConfig3);
    }

    private static final boolean findById$lambda$0(String string, ProfileConfig profileConfig) {
        Intrinsics.checkNotNullParameter(profileConfig, "it");
        return Intrinsics.areEqual(profileConfig.getId(), string);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    public static void zkmSetFlag(String string) {
        I = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return I;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        ProfileConfigFile.c = ZkmKeys.a(-173405399289624608L, 2318500442154879409L, MethodHandles.lookup().lookupClass()).a(109920486178251L);
                        var20 = ProfileConfigFile.c ^ 30678742887054L;
                        ProfileConfigFile.i = new HashMap<K, V>(13);
                        ProfileConfigFile.zkmSetFlag("vbJRLc");
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
                        var15_5 = "\u00fe\u00912\u0094aR|\u00a99-\u0083\u00b4\u00f4\u001e)\u009ec\u00c9\u00c4`\u009c\u0088\u0091\u0093q\u00ab?\u00f1\u0011+\u00196\u0010\u00cf\u00ff\u00aa\u00ed\u00c5\u00bd\u00c09;m\u00c2ZA\u00fb\u00b8%X?\u00e5\u00d1r\u0001\u008e\u0081Cc\u0097k\u00c9\u00aeq}\u00f8\u00f0k\u008f\u0087\u0091B\u0092<\u00efE\u009d\u00c1\u0013\u00e0\u00ba\u00c0\u00eb\u000e6\u0010\u007f\u00feT\u00bd\u00c7\u00ce\u00061\u00f2\u0089zG\u00e3qY\u00bf\u0011cxk\\U\u00d2R\u00dd\u00d9!E\u00d6\t\u00f3\u00f6\u00bea\fM?\u00c8 \u00e87\u00ac\u00d0\n\u009b\u000f\u00ef\u00e1\u00acw,\t\u0010\u0094\u00f3\u00be\u00c6\u00da\u00a4\u00cf\u000f\u00cdW\u00ddx\u00cb\u0013U\u00b0\u00107\u009e\u0091\u00b7-\u00cf\u00a5\u00d4t\u007f\u00c2\u00cd\u00f0<\u00bd\u00e0\u0010\u00e0\u00e4N\u00b7\rl\u000b$\u00f6lpD\u00d3\u0017\u001a\u001f\u0010\u0018\u009b\u0080\u009ee\u00fa;\u00a3\u001f\u00da\u009d\u00e9?\u00a1JE\u0010\u00bc\u00ed\u00d8\u00e8tU\u00ca\u00f9\u00aef*\u001d}\u0004\u00e0\u00f4\u0010\u00ef:\u00c7*\u009d\u00bf\u00b7\u00c5f\u0081F\u00f2\u00e4\u001f{v\u0010d\u00eb\u00a9\u00d1\u0013\u0081AA\u00d7\u00c5*-S\u00b9\u00c9d\u0010\u001c@\u0095\u00cc|\u0015>\u0097\u00ad\u00de\u00aeU2x\u00e8j";
                        var17_6 = "\u00fe\u00912\u0094aR|\u00a99-\u0083\u00b4\u00f4\u001e)\u009ec\u00c9\u00c4`\u009c\u0088\u0091\u0093q\u00ab?\u00f1\u0011+\u00196\u0010\u00cf\u00ff\u00aa\u00ed\u00c5\u00bd\u00c09;m\u00c2ZA\u00fb\u00b8%X?\u00e5\u00d1r\u0001\u008e\u0081Cc\u0097k\u00c9\u00aeq}\u00f8\u00f0k\u008f\u0087\u0091B\u0092<\u00efE\u009d\u00c1\u0013\u00e0\u00ba\u00c0\u00eb\u000e6\u0010\u007f\u00feT\u00bd\u00c7\u00ce\u00061\u00f2\u0089zG\u00e3qY\u00bf\u0011cxk\\U\u00d2R\u00dd\u00d9!E\u00d6\t\u00f3\u00f6\u00bea\fM?\u00c8 \u00e87\u00ac\u00d0\n\u009b\u000f\u00ef\u00e1\u00acw,\t\u0010\u0094\u00f3\u00be\u00c6\u00da\u00a4\u00cf\u000f\u00cdW\u00ddx\u00cb\u0013U\u00b0\u00107\u009e\u0091\u00b7-\u00cf\u00a5\u00d4t\u007f\u00c2\u00cd\u00f0<\u00bd\u00e0\u0010\u00e0\u00e4N\u00b7\rl\u000b$\u00f6lpD\u00d3\u0017\u001a\u001f\u0010\u0018\u009b\u0080\u009ee\u00fa;\u00a3\u001f\u00da\u009d\u00e9?\u00a1JE\u0010\u00bc\u00ed\u00d8\u00e8tU\u00ca\u00f9\u00aef*\u001d}\u0004\u00e0\u00f4\u0010\u00ef:\u00c7*\u009d\u00bf\u00b7\u00c5f\u0081F\u00f2\u00e4\u001f{v\u0010d\u00eb\u00a9\u00d1\u0013\u0081AA\u00d7\u00c5*-S\u00b9\u00c9d\u0010\u001c@\u0095\u00cc|\u0015>\u0097\u00ad\u00de\u00aeU2x\u00e8j".length();
                        var14_7 = 32;
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
                            var18_3[var16_4++] = ProfileConfigFile.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ec\u00bd\u00a8\u001fR\u009b\u008d\u00e0k\u00ad\u00c0g\bz~*\u0010'\u00a6E\u00f2\u009a\u0003*:\u00e4T\u00822\u00c0\u00fb#\u00c7";
                            var17_6 = "\u00ec\u00bd\u00a8\u001fR\u009b\u008d\u00e0k\u00ad\u00c0g\bz~*\u0010'\u00a6E\u00f2\u009a\u0003*:\u00e4T\u00822\u00c0\u00fb#\u00c7".length();
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
                            var18_3[var16_4++] = ProfileConfigFile.zkmDecodeUtf8(var19_9).intern();
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
                ProfileConfigFile.d = var18_3;
                ProfileConfigFile.h = new String[13];
                ProfileConfigFile.o = new HashMap<K, V>(13);
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
                var4_14 = "H\u0083o\u00c14\u0014a\u001f~\u00b5_a\u00d5\u00d7M\u000e";
                var5_15 = "H\u0083o\u00c14\u0014a\u001f~\u00b5_a\u00d5\u00d7M\u000e".length();
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
        ProfileConfigFile.m = var6_12;
        ProfileConfigFile.n = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1355;
        if (h[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/oc", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            ProfileConfigFile.h[n2] = ProfileConfigFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProfileConfigFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/oc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6C94;
        if (ProfileConfigFile.n[n2] == null) {
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
                throw new RuntimeException("su/catlean/oc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ProfileConfigFile.n[n2] = n3;
        }
        return ProfileConfigFile.n[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ProfileConfigFile.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/oc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProfileConfigFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ProfileConfigFile.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

