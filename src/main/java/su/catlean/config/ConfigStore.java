/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.reflect.Field;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.FilesDraggedEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ConfigManager;
import su.catlean.config.ConfigStore$onPlayerUpdate$1;
import su.catlean.config.HudConfigFile;
import su.catlean.config.ItemPresetConfigFile;
import su.catlean.config.JsonListFile;
import su.catlean.config.JsonListFile2;
import su.catlean.config.ProfileConfig;
import su.catlean.config.ProfileConfigFile;
import su.catlean.config.ProxyList;
import su.catlean.config.ProxyListFile;
import su.catlean.config.Saveable;
import su.catlean.config.ThemeConfigFile;
import su.catlean.config.WaypointListFile;
import su.catlean.gofra.Flow;
import su.catlean.gui.ConfigPanel;
import su.catlean.gui.PanelScreen;
import su.catlean.gui.ProxyPanel;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.util.Manager;
import su.catlean.util.Proxy;
import su.catlean.util.ProxyManager;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class ConfigStore
implements Manager {
    @NotNull
    public static final ConfigStore INSTANCE;
    @NotNull
    private static final Json json;
    @NotNull
    private static final ConfigManager configManager;
    @NotNull
    private static final HudConfigFile hudConfigFile;
    @NotNull
    private static final ProfileConfigFile profileConfigFile;
    @NotNull
    private static final ThemeConfigFile themeConfigFile;
    @NotNull
    private static final WaypointListFile waypointListFile;
    @NotNull
    private static final ProxyListFile proxyListFile;
    @NotNull
    private static final JsonListFile jsonListFile;
    @NotNull
    private static final JsonListFile2 jsonListFile2;
    @NotNull
    private static final ItemPresetConfigFile itemPresetConfigFile;
    @NotNull
    private static final Timer timer;
    private static Module[] E;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] h;
    private static final Map i;

    private ConfigStore() {
    }

    @NotNull
    public final ConfigManager getConfigManager() {
        return configManager;
    }

    @NotNull
    public final HudConfigFile getHudConfigFile() {
        return hudConfigFile;
    }

    @NotNull
    public final ProfileConfigFile getProfileConfigFile() {
        return profileConfigFile;
    }

    @NotNull
    public final ThemeConfigFile getThemeConfigFile() {
        return themeConfigFile;
    }

    @NotNull
    public final WaypointListFile getWaypointListFile() {
        return waypointListFile;
    }

    @NotNull
    public final ProxyListFile getProxyListFile() {
        return proxyListFile;
    }

    @NotNull
    public final JsonListFile getJsonListFile() {
        return jsonListFile;
    }

    @NotNull
    public final JsonListFile2 getJsonListFile2() {
        return jsonListFile2;
    }

    @NotNull
    public final ItemPresetConfigFile getItemPresetConfigFile() {
        return itemPresetConfigFile;
    }

    private final void loadFiles() {
        Object object;
        Object object2 = ConfigFiles.l();
        int n = ((File[])object2).length;
        for (int i = 0; i < n; ++i) {
            object = object2[i];
            File file = object;
            file.mkdirs();
        }
        Field[] fieldArray = this.getClass().getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(fieldArray, "getDeclaredFields(...)");
        Object object3 = object2 = (Object[])fieldArray;
        Collection collection = new ArrayList();
        for (Object object4 : object3) {
            Field field = (Field)object4;
            if (!Saveable.class.isAssignableFrom(field.getType())) continue;
            collection.add(object4);
        }
        object2 = (List)collection;
        object3 = object2.iterator();
        while (object3.hasNext()) {
            collection = object3.next();
            object = (Field)((Object)collection);
            ((Field)object).setAccessible(true);
            Object object5 = ((Field)object).get(INSTANCE);
            Intrinsics.checkNotNull(object5, "null cannot be cast to non-null type su.catlean.api.service.sources.DataSource");
            ((Saveable)object5).save();
        }
    }

    public final void save(long l) {
        Object object;
        long l2 = (l = a ^ l) ^ 0x36F4790A422DL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 40);
        profileConfigFile.findById(ModuleManager.INSTANCE.L(), profileConfigFile.getCurrent().getId());
        hudConfigFile.findById(HudManager.INSTANCE.getElements(), hudConfigFile.getCurrent().getId());
        Field[] fieldArray = this.getClass().getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(fieldArray, "getDeclaredFields(...)");
        Object object2 = object = (Object[])fieldArray;
        Collection<Object> collection = new ArrayList();
        for (Object object3 : object2) {
            Field field = (Field)object3;
            if (!Saveable.class.isAssignableFrom(field.getType())) continue;
            collection.add(object3);
        }
        object = (List)collection;
        object2 = object.iterator();
        while (object2.hasNext()) {
            collection = object2.next();
            Field field = (Field)((Object)collection);
            field.setAccessible(true);
            Object object4 = field.get(INSTANCE);
            Intrinsics.checkNotNull(object4, "null cannot be cast to non-null type su.catlean.api.service.sources.DataSource");
            ((Saveable)object4).load(n, n2);
        }
    }

    /*
     * Loose catch block
     */
    @Flow
    private final void onFilesDragged(FilesDraggedEvent filesDraggedEvent) {
        String string;
        Object object;
        File file;
        block33: {
            block34: {
                block40: {
                    block39: {
                        block38: {
                            file = new File(((Object)CollectionsKt.first(filesDraggedEvent.getPaths())).toString());
                            if (!(Globals.getMc().currentScreen instanceof PanelScreen)) break block33;
                            break block38;
                            catch (Throwable throwable) {
                                throw throwable;
                            }
                        }
                        if (ProxyPanel.INSTANCE.getGuiElement() == null) break block33;
                        break block39;
                        catch (Throwable throwable) {
                            throw throwable;
                        }
                    }
                    break block40;
                    catch (Throwable throwable) {
                        throw throwable;
                    }
                }
                String string2 = file.getName();
                Intrinsics.checkNotNullExpressionValue(string2, "getName(...)");
                if (!StringsKt.contains$default((CharSequence)string2, ".txt", false, 2, null)) break block34;
                try {
                    object = new BufferedReader(new FileReader(file));
                    string = null;
                    try {
                        BufferedReader bufferedReader = (BufferedReader)object;
                        while (bufferedReader.ready()) {
                            List list;
                            ListIterator listIterator;
                            Object object2;
                            block36: {
                                block35: {
                                    block41: {
                                        String string3 = bufferedReader.readLine();
                                        Intrinsics.checkNotNullExpressionValue(string3, "readLine(...)");
                                        object2 = new String[]{":"};
                                        object2 = StringsKt.split$default((CharSequence)string3, (String[])object2, false, 0, 6, null);
                                        break block41;
                                        catch (Throwable throwable) {
                                            throw throwable;
                                        }
                                    }
                                    if (object2.isEmpty()) break block35;
                                    listIterator = object2.listIterator(object2.size());
                                    while (listIterator.hasPrevious()) {
                                        String string4 = (String)listIterator.previous();
                                        if (((CharSequence)string4).length() == 0) continue;
                                        list = CollectionsKt.take((Iterable)object2, listIterator.nextIndex() + 1);
                                        break block36;
                                    }
                                }
                                list = CollectionsKt.emptyList();
                            }
                            object2 = list;
                            listIterator = object2;
                            String[] stringArray = listIterator.toArray((String[])new String[0]);
                            Integer n = StringsKt.toIntOrNull(stringArray[1]);
                            DefaultConstructorMarker defaultConstructorMarker = null;
                            int n2 = 64;
                            int n3 = 0;
                            boolean bl = false;
                            String string5 = stringArray[3];
                            String string6 = stringArray[2];
                            int n4 = n != null ? n : 80;
                            String string7 = stringArray[0];
                            String string8 = "proxy_" + (int)(Random.Default.nextFloat() * 10000.0f);
                            ProxyManager.INSTANCE.addProxy(new Proxy(string8, string7, n4, string6, string5, bl, n3, n2, defaultConstructorMarker));
                        }
                    }
                    catch (Throwable throwable) {
                        throw throwable;
                    }
                    finally {
                        CloseableKt.closeFinally((Closeable)object, (Throwable)((Object)string));
                    }
                    ProxyPanel.INSTANCE.setGuiElement(null);
                    ProxyPanel.INSTANCE.refresh();
                }
                catch (Exception exception) {
                    ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                    if (clientPlayerEntity != null) {
                        Object[] objectArray = new Object[]{};
                        String string9 = "gui.proxy.import-error";
                        clientPlayerEntity.sendMessage(Text.of(I18n.INSTANCE.translate(string9, objectArray)), false);
                    }
                }
            }
            String string10 = file.getName();
            Intrinsics.checkNotNullExpressionValue(string10, "getName(...)");
            if (!StringsKt.contains$default((CharSequence)string10, ".ctlon", false, 2, null)) break block33;
            try {
                List list = proxyListFile.getData().getProxies();
                object = json;
                string = FilesKt.readText$default(file, null, 1, null);
                ((Json)object).getSerializersModule();
                list.addAll(((ProxyList)((Json)object).decodeFromString(ProxyList.Companion.serializer(), string)).getProxies());
                ProxyPanel.INSTANCE.setGuiElement(null);
                ProxyPanel.INSTANCE.refresh();
            }
            catch (Exception exception) {
                ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                if (clientPlayerEntity != null) {
                    Object[] objectArray = new Object[]{};
                    String string11 = "gui.proxy.import-error";
                    clientPlayerEntity.sendMessage(Text.of(I18n.INSTANCE.translate(string11, objectArray)), false);
                }
            }
            return;
        }
        String string12 = file.getName();
        Intrinsics.checkNotNullExpressionValue(string12, "getName(...)");
        if (StringsKt.contains$default((CharSequence)string12, ".ctlon", false, 2, null)) {
            try {
                List list = profileConfigFile.getData();
                object = json;
                string = FilesKt.readText$default(file, null, 1, null);
                ((Json)object).getSerializersModule();
                list.add(((Json)object).decodeFromString(ProfileConfig.Companion.serializer(), string));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            ConfigPanel.INSTANCE.refresh();
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (timer.hasPassed2(Globals.getMc().currentScreen instanceof PanelScreen ? 2500 : 60000)) {
            Async async = Async.INSTANCE;
            int n = 0;
            async.getExecutor().execute(new ConfigStore$onPlayerUpdate$1(n));
        }
    }

    private static final Unit _clinit_$lambda$0(JsonBuilder jsonBuilder) {
        Intrinsics.checkNotNullParameter(jsonBuilder, "$this$Json");
        jsonBuilder.setPrettyPrint(true);
        jsonBuilder.setIgnoreUnknownKeys(true);
        jsonBuilder.setEncodeDefaults(true);
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ConfigStore.a = ZkmKeys.a(-4066619958810965381L, -3923280114561898578L, MethodHandles.lookup().lookupClass()).a(88221762137594L);
                        var20 = ConfigStore.a ^ 82336131525880L;
                        ConfigStore.d = new HashMap<K, V>(13);
                        ConfigStore.zkmSetFlag(new Module[5]);
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
                        var18_3 = new String[14];
                        var16_4 = 0;
                        var15_5 = "\u00b15\u0014D\u00fa*]\u00c0\u00c8\u00f3]\u00c4\u001f\u007f\u00f6C\u00af\u00f6\u00d5\u00a0x\u0081\u0088\u00f6\u00b8f\u0084\u00aa\u0086H\u00b6\u008d\u0002c;\u00e3\u0099\u00bf-J\u00104\u0015\u00d8!R\u00a6\u0012\u00cf\u0086\u00cf\u0004\u00cfe\u009fH\u0099(\u00fc\u001cJ\u00d9\u00eb\u00bfA\u00bf\u00fe_\u00fc\u00c9vG\u00bc\u00c6\u00ce\u00ccs\u00f8\u0083;-qE\u00a9\u0084\u0080\u00ed\u00c1\u0010Fb.\u0003\u0011\u0082\r\u00ed\u00cc\u0018&\u0083\u00e6\u008c\u0093\u00bf\u00af\u00cb\u00d0\u0083\u008a\u00db\u00c9\u001cK\u00b4\n\u00e4\u00ab\u00d7{\u0084\u00e20 &\u00ab\t~2F\u00ea\u00cf_\u000b=\u009d>k8\u0014\u00ce'\u0016\u00e0\u00bb\u00a07\u00d5\u00e2\u0082\u00e2wp\u00c3C\u0099(\u0098y\u00cc_\u001c\u00bf\u00d5\u000en!\u00d1\u0012\u00a8I\u0091\u00f8%\u009bH\u00d5\u009dv\u0006\u00e1j\u00f5=\u00fe\u0099KPbv\u00c1r\u00d9\u00910X\u00b6 \u0017o\u009ca\u0091\u00abN\u0082\u00de\u00fa\u00c76\u00df\u00f7q\u00d6\u0095\u0089J\u00f64\u0081\u008ap\u00eb\u0093L\u00d99\u0016h\u0085\u0018\u00bff\u008c\u00d7\u00ecQWb>M\u001b\u00f9\u00cf~\u00e1{U\rU&\u00dbw\u0011)\u0010\u00b6<\u00ff\u00ea\u00c2\u00a4T\u00b2K\u0098\u00ea\u00c2\u00e1Aaa\u0080\u00ab\u0005\u00aa\u00bcIHmL\u001dDxa\u00bd/tP\u0097**\u00f3\u00cf\u0092@\u00c8\u0006\u0015\u00f7\u00cb.\u009f\u00b7w\u0093\u00f2L\u001d\fu\u00b7\u00d6\u00a4d\u00e3\u0019\u00fe\u00d6\u00d0\u0080E\u0080\u008d\u0014\u00d5\u0003&P=\u00c5lc]\u00abae\u008a\u00c2\u00eb\u0083\u00a2m\u00e1\u0011~\u00ea\u00b0\u009f\u00ce\u0001\u00ee\u00d3\u009a\u00ce\u00d1Og\u0015u-\u0090\u00b9\u00ad\u00e1B\u00da\u00df\u00b6\u0090\u00d2\u00e56\u00ca_\u00c4\u0090\u00af\u0012\u0091\u008e\u00ff\u000eC\u00ad\u0011\u001d\u00d0\u00b9L\u00da\u0084\u00c8\u0002X\u00f2\u00a7W\u00b7qX\u0080\u00fe\u00b9R4c,\u0004\u001d\u00ef\u008c\u00a1\u001b>\u00e9\u00d0\u00dcW|\u00a3\u008cs\u0006\u0006<\u0018k\u00dfE\u00e6\n\u00fe\u0019\u00cb\u00fa\u00df\u0018z\u00bd\\2\u00c0B\\\u00a5\u0089\u00bd\u001dtt\u00dc\u00be\u00ae\u00da\u00ce\u00f9.\u00b3\u009buE\u00b0\u00d3\u0016\u001e\u0015\u00be*\u0080\u00ad\u00d4\u00de\u00d6\u0092\b\u009e\u0093]\u0001kg\u00e8s\u0087\u00a8R\u0090\u008aV5\u0090y\u009b\u00adW\u00b0'\u00c1\u00e2\u0011\u00e06\u008bV\u00ec\u00b6\u00d91\u00fa\u00c47K\u00f8\u0093\u00d2qH\u00b4\u00b4\u0000\u00f6\u00e6<B7\u00dbL\u000f\u0096\u0010L$\u00e9>\u00f6A15 \u00e15\u00d1\u00c9\u0081\u001f\u0080";
                        var17_6 = 547;
                        var14_7 = 40;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ConfigStore.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "w\u00d74\u001dC\u008f?}\u00e7\u00af\u00db\u00cf\u00f2\u00a1\u0012\u0000(\u00d5\u00a3\u00da\u00d5h\u0003\u008a\u00e2d@m\u00ac\u0019u\u00db-\u00de,-\u00c7S\u009f\u0010\u001d\u0082\u008e\u00f0$w;7\u0088M\u00c7'\u00c8\u00aa|\u00ac\u00b7";
                            var17_6 = 57;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ConfigStore.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
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
                ConfigStore.b = var18_3;
                ConfigStore.c = new String[14];
                ConfigStore.i = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u009a\u00e4D\u00a3\u0010%t\u00d8\n\u00a4\u00fd\u009a\u00ba\u00b5m\u00cc5\u00c7\u00e2\u00c1\u00d2\u00a5a\u008ce?(6\u00c6\u00ecE[";
                var5_15 = 32;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00df\u00b4[\u00a0\u00ba\u008f\u00b1\\kz\u0006$R\u0082g9";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl92:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl105:
                // 1 sources

                ** continue;
            }
        }
        ConfigStore.e = var6_12;
        ConfigStore.h = new Integer[6];
        ConfigStore.INSTANCE = new ConfigStore();
        ConfigStore.json = JsonKt.Json$default(null, (Function1<JsonBuilder, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(kotlinx.serialization.json.JsonBuilder ), (Lkotlinx/serialization/json/JsonBuilder;)Lkotlin/Unit;)(), 1, null);
        ConfigStore.configManager = new ConfigManager(ConfigStore.json);
        ConfigStore.hudConfigFile = new HudConfigFile(ConfigStore.json, ConfigStore.configManager);
        ConfigStore.profileConfigFile = new ProfileConfigFile(ConfigStore.json, ConfigStore.configManager);
        ConfigStore.themeConfigFile = new ThemeConfigFile(ConfigStore.json, ConfigStore.configManager);
        ConfigStore.waypointListFile = new WaypointListFile(ConfigStore.json);
        ConfigStore.proxyListFile = new ProxyListFile(ConfigStore.json);
        ConfigStore.jsonListFile = new JsonListFile(ConfigStore.json);
        ConfigStore.jsonListFile2 = new JsonListFile2(ConfigStore.json);
        ConfigStore.itemPresetConfigFile = new ItemPresetConfigFile(ConfigStore.json, ConfigStore.configManager);
        ConfigStore.timer = new Timer();
        ConfigStore.INSTANCE.loadFiles();
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        E = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return E;
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x74CF;
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
                throw new RuntimeException("su/catlean/yl", exception);
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
            ConfigStore.c[n2] = ConfigStore.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ConfigStore.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/yl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5C18;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/yl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ConfigStore.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ConfigStore.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/yl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ConfigStore.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ConfigStore.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

