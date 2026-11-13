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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ConfigManager;
import su.catlean.config.JsonConfigFile;
import su.catlean.render.CurrentTheme;
import su.catlean.render.ThemePreset;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ThemeConfigFile
extends JsonConfigFile {
    @NotNull
    private final ConfigManager sharedSource;
    @NotNull
    private final File N;
    public List J;
    public ThemePreset current;
    private static final long a;
    private static final String[] c;
    private static final String[] h;
    private static final Map i;
    private static final long m;

    public ThemeConfigFile(@NotNull Json json, @NotNull ConfigManager configManager) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(configManager, "sharedSource");
        super(json);
        this.sharedSource = configManager;
        this.N = new File(ConfigFiles.getCtlDir(), "themes");
    }

    @Override
    @NotNull
    public File U() {
        return this.N;
    }

    @NotNull
    public final List Z() {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
        List list = this.J;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException((String)((Object)ThemeConfigFile.zkmBootstrap("l", (int)23521, (long)(0x56A115222BD567C4L ^ l2))));
        return null;
    }

    public final void I(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.J = list;
    }

    @NotNull
    public final ThemePreset getCurrent() {
        ThemePreset themePreset = this.current;
        if (themePreset != null) {
            return themePreset;
        }
        Intrinsics.throwUninitializedPropertyAccessException("current");
        return null;
    }

    public final void setCurrent(@NotNull ThemePreset themePreset) {
        Intrinsics.checkNotNullParameter(themePreset, "<set-?>");
        this.current = themePreset;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void save() {
        block8: {
            this.U().mkdirs();
            var28_1 = this;
            try {
                block10: {
                    block9: {
                        var11_2 = this;
                        var29_3 = var28_1;
                        if (var11_2.U().exists() && var11_2.U().isDirectory()) break block9;
                        v0 /* !! */  = new ArrayList<E>();
                        break block10;
                    }
                    var13_4 = var11_2.U().listFiles();
                    if (var13_4 == null) ** GOTO lbl-1000
                    var14_5 = var13_4;
                    var16_6 = var14_5;
                    var17_7 = new ArrayList<E>();
                    var20_9 = ((Object)var16_6).length;
                    for (var19_8 = 0; var19_8 < var20_9; ++var19_8) {
                        var22_12 = var21_11 = var16_6[var19_8];
                        Intrinsics.checkNotNull(var22_12);
                        if (!Intrinsics.areEqual(FilesKt.getExtension((File)var22_12), "ctlon")) continue;
                        var17_7.add((Object)var21_11);
                    }
                    var15_13 /* !! */  = (List)var17_7;
                    var17_7 = var15_13 /* !! */ ;
                    var18_14 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var15_13 /* !! */ , (int)ThemeConfigFile.m));
                    for (Object var21_11 : var17_7) {
                        var22_12 = (File)var21_11;
                        var24_15 = var18_14;
                        var25_16 = JsonConfigFile.access$getJson((JsonConfigFile)var11_2);
                        Intrinsics.checkNotNull(var22_12);
                        var26_17 = FilesKt.readText$default((File)var22_12, null, 1, null);
                        var25_16.getSerializersModule();
                        var24_15.add(var25_16.decodeFromString(ThemePreset.Companion.serializer(), var26_17));
                    }
                    var15_13 /* !! */  = CollectionsKt.toMutableList((List)var18_14);
                    if (var15_13 /* !! */  != null) {
                        v0 /* !! */  = var15_13 /* !! */ ;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v0 /* !! */  = new ArrayList<E>();
                    }
                }
                v1 = var29_3;
                var11_2 = v0 /* !! */ ;
            }
            catch (Exception v2) {
                v1 = var28_1;
                Globals.getLogger().warn("Themes are not loaded from file for some reasons.");
                var11_2 = new ArrayList<E>();
            }
            v1.I((List)var11_2);
            var12_18 = this.Z();
            var28_1 = this;
            var14_5 = var12_18.iterator();
            while (var14_5.hasNext()) {
                var15_13 /* !! */  = var14_5.next();
                var16_6 = (ThemePreset)var15_13 /* !! */ ;
                if (!Intrinsics.areEqual(var16_6.getId(), this.sharedSource.getData().getThemeConfig())) continue;
                v3 = var15_13 /* !! */ ;
                break block8;
            }
            v3 = null;
        }
        v4 = var28_1;
        v5 = v3;
        if (v5 == null) {
            var12_18 = CurrentTheme.getThemePreset();
            var28_1 = v4;
            this.Z().addAll((Collection)CollectionsKt.listOf(CurrentTheme.getThemePreset()));
            v4 = var28_1;
            v5 = var12_18;
        }
        v4.setCurrent((ThemePreset)v5);
    }

    @Override
    public void load(int n, int n2) {
        Iterable iterable = this.Z();
        for (Object t : iterable) {
            Object object;
            ThemePreset themePreset = (ThemePreset)t;
            Object object2 = object = new File(this.U(), themePreset.getId() + ".ctlon");
            ((File)object2).createNewFile();
            File file = object;
            object = this.getJson();
            object2 = themePreset;
            ((Json)object).getSerializersModule();
            FilesKt.writeText$default(file, ((Json)object).encodeToString(ThemePreset.Companion.serializer(), object2), null, 2, null);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ThemeConfigFile.a = ZkmKeys.a(1404259593481068913L, 2990130902032560075L, MethodHandles.lookup().lookupClass()).a(177617342497807L);
                    ThemeConfigFile.i = new HashMap<K, V>(13);
                    var5 = ThemeConfigFile.a ^ 84212303364222L;
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
                    var11_5 = "t\u00e5\u00c4\u0096\u00a6$q\u00d1\u00f1\u0001i\u00df5{\u00cfK\u00dbmz\u0002\u00ec\u00e3\u00ca\u00ec\u009ax\u0007K\u000e\u0099\u0014VXUP\u00af\"|\u0015\u00fav\u0099\u00ad\u001c\u00064o\u0006\u00aa\u00fby\b$T.\u00d39\u001a\u0099jV\u00b1\u0010\u00c1\"r)\u00a7\u009e\u00e1u]\u00ef\u00ca\u00dd\u00065m\u00d9q\u00001;fJ\u001e[>\u009b\u00a4\u009e\u0086Q\u00b3\u00b6\u00bb\u008d\u0018x\u00a0\u008ef\u0093\u00ab\"\u00b1\u001a\u009b1\u00a4\u00b5\nX\u009a\u0088\u0094\u00d2/he\u00dc\u0010\u00fe\u00d7t\u00f1\u0094\u00da\u00d39U\u008e\u00df:\u00d4B;^\u0010\u0099\u008c\u00e5\u00d7\u00d8\u00a8=\u00f3\u00ea*\u00e0\u008a\u001a\u00c6\u0016\u00ba\u0010\u00a5\u009c\u00d1|\u0090\u001d\u0082\u00fb\u0080\u00c6^\u00acD\u00a1\u0001\u00a2\u0010\u00e6\u001e\u00bb\u00cfyAN.<4\u0019:\u00d8C0\u0004\u0010\u00d9x!K\u00c0\u00b9\u00dd\u00dfz:'F\u00dcv\u00eb\u009e\u0010&\u00e4M\u00eb4\u001c\u00b3\u0092I6\u00ab\u00d5\u0006\u00e4h\u008f";
                    var13_6 = 223;
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
                        var14_3[var12_4++] = ThemeConfigFile.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u008da\u00b7U\u00d5\u00d3>\u00be\u00e0=}:\u00df\u00daZ\u000b\u0010\u00b8\u001c'\u0085\bM\u0093\u00d0t\u00c9\u00bf\u009d\u00bfn\u00b6\u00f9";
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
                        var14_3[var12_4++] = ThemeConfigFile.zkmDecodeUtf8(var15_9).intern();
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
            ThemeConfigFile.c = var14_3;
            ThemeConfigFile.h = new String[10];
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
        var4_12 = var0_10.doFinal(new byte[]{21, 118, 22, 39, -39, -13, 123, 44});
        ** while (true)
        ThemeConfigFile.m = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x204;
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
                throw new RuntimeException("su/catlean/o4", exception);
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
            ThemeConfigFile.h[n2] = ThemeConfigFile.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ThemeConfigFile.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/o4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ThemeConfigFile.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

