/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.gofra.Gofra;
import su.catlean.interfaces.IEntity;
import su.catlean.module.Category;
import su.catlean.module.Toggleable;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.zkm.ZkmKeys;

public abstract class Module
implements SettingContainer,
Toggleable {
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private final String id;
    @NotNull
    private final Category category;
    @NotNull
    private final List aliases;
    @NotNull
    private final List settings;
    @NotNull
    private final List M;
    @NotNull
    private final String displayName;
    @NotNull
    private final BooleanSetting enabled$delegate;
    @NotNull
    private final BindSetting bind$delegate;
    private static Module[] v;
    private static final long bb;
    private static final String[] cb;
    private static final String[] db;
    private static final Map eb;
    private static final long[] ib;
    private static final Integer[] jb;
    private static final Map kb;

    public Module(@NotNull String string, @NotNull Category category, @NotNull List list) {
        StringBuilder stringBuilder;
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(list, "aliases");
        this.id = string;
        this.category = category;
        this.aliases = list;
        this.settings = new ArrayList();
        this.M = new ArrayList();
        StringBuilder stringBuilder2 = stringBuilder = new StringBuilder();
        Module module = this;
        Object object = new String[]{"-"};
        object = StringsKt.split$default((CharSequence)this.id, (String[])object, false, 0, 6, null);
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            String string2;
            Object t = iterator2.next();
            String string3 = (String)t;
            StringBuilder stringBuilder3 = stringBuilder2;
            String string4 = string3;
            if (((CharSequence)string4).length() > 0) {
                char c = string4.charAt(0);
                StringBuilder stringBuilder4 = new StringBuilder();
                StringBuilder stringBuilder5 = stringBuilder3;
                String string5 = String.valueOf(c);
                Intrinsics.checkNotNull(string5, "null cannot be cast to non-null type java.lang.String");
                String string6 = string5.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(string6, "toUpperCase(...)");
                CharSequence charSequence = string6;
                stringBuilder3 = stringBuilder5;
                StringBuilder stringBuilder6 = stringBuilder4.append((Object)charSequence);
                String string7 = string4;
                int n = 1;
                String string8 = string7.substring(n);
                Intrinsics.checkNotNullExpressionValue(string8, "substring(...)");
                string2 = stringBuilder6.append(string8).toString();
            } else {
                string2 = string4;
            }
            stringBuilder3.append(string2);
            stringBuilder2.append(' ');
            StringsKt.trim(stringBuilder2);
        }
        module.displayName = stringBuilder.toString();
        this.enabled$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "enabled", false, null, null, 12, null);
        this.bind$delegate = SettingFactory.bindSetting$default(this, "bind", new Bind(0, false, false, 7, null), null, null, 12, null);
    }

    public /* synthetic */ Module(String string, Category category, List list, DefaultConstructorMarker defaultConstructorMarker) {
        list = CollectionsKt.emptyList();
        this(string, category, list);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final Category getCategory() {
        return this.category;
    }

    @NotNull
    public final List getAliases() {
        return this.aliases;
    }

    @Override
    @NotNull
    public List getSettings() {
        return this.settings;
    }

    @Override
    @NotNull
    public List u() {
        return this.M;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    public boolean getEnabled() {
        return (Boolean)this.enabled$delegate.getValue(this, delegatedProperties[0]);
    }

    public void setEnabled(boolean bl) {
        this.enabled$delegate.setValue(this, delegatedProperties[0], bl);
    }

    @NotNull
    public Bind getBind() {
        return (Bind)this.bind$delegate.getValue(this, delegatedProperties[1]);
    }

    public void setBind(@NotNull Bind bind) {
        Intrinsics.checkNotNullParameter(bind, "<set-?>");
        this.bind$delegate.setValue(this, delegatedProperties[1], bind);
    }

    public final boolean isInGame() {
        return Globals.getMc().player != null && Globals.getMc().world != null;
    }

    @Override
    public void toggle(long l) {
        long l2 = l;
        long l3 = l2 ^ 0x3D5CF9CB0B0CL;
        int n = (int)(l3 >>> 56);
        long l4 = l3 << 8 >>> 8;
        long l5 = l2 ^ 0x3B8B5F891995L;
        if (this.getEnabled()) {
            this.disable(l5);
        } else {
            this.enable((byte)n, l4);
        }
    }

    @Override
    public void enable(byte by, long l) {
        long l2 = (long)by << 56 | l << 8 >>> 8;
        long l3 = l2 ^ 0x15C467DDD709L;
        this.setEnabled(true);
        Gofra.INSTANCE.unplug(this);
        Gofra.INSTANCE.plug(this);
        this.onEnable(l3);
        Object var15_5 = null;
        CallSite callSite = Module.zkmBootstrap2("v", (int)31161, (long)(0x50D595D21444E833L ^ l2));
        ItemStack itemStack = null;
        NotificationType notificationType = NotificationType.ENABLED;
        int n = 1;
        String string = ModuleMessages.i18n(this, "enable", new Object[0], true);
        String string2 = this.displayName;
        Notifications notifications = Notifications.INSTANCE;
        Notifications.push$default(notifications, string2, string, n, notificationType, itemStack, (int)callSite, var15_5);
    }

    public final void disableWithMessage(@NotNull String string, long l) {
        long l2 = (l = bb ^ l) ^ 0x18099665B00L;
        Intrinsics.checkNotNullParameter(string, "message");
        this.disable(l2);
        ModuleMessages.S(this, string, false, null);
    }

    @Override
    public void disable(long l) {
        long l2 = l ^ 0x34BEE50EF2DAL;
        this.setEnabled(false);
        Gofra.INSTANCE.unplug(this);
        this.onDisable(l2);
        Object var12_3 = null;
        int n = 16;
        ItemStack itemStack = null;
        NotificationType notificationType = NotificationType.DISABLED;
        int n2 = 1;
        String string = ModuleMessages.i18n(this, "disable", new Object[0], true);
        String string2 = this.displayName;
        Notifications notifications = Notifications.INSTANCE;
        Notifications.push$default(notifications, string2, string, n2, notificationType, itemStack, n, var12_3);
    }

    public void onEnable(long l) {
    }

    public void onDisable(long l) {
    }

    @NotNull
    public final Vec3d getPos(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "<this>");
        Vec3d vec3d = ((IEntity)((Object)entity)).catLean$getPosition();
        Intrinsics.checkNotNullExpressionValue(vec3d, "catLean$getPosition(...)");
        return vec3d;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Module.bb = ZkmKeys.a(-3043674970192207614L, 1371597856073184348L, MethodHandles.lookup().lookupClass()).a(107184716577853L);
                        var20 = Module.bb ^ 115016563258759L;
                        Module.eb = new HashMap<K, V>(13);
                        Module.zkmSetFlag(null);
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
                        var18_3 = new String[18];
                        var16_4 = 0;
                        var15_5 = "H<\u00b5'\u00ce\u00f0\u0097\u000b]\u00a5a\u00f9\u009bn\u00cd\u00b2 P\u00ab\u008a\u001e\u0005x\n\u0095\u00e7\u001d\u00bal\u00dc\u007f\bH\u0014k\u00e4\u00ad\u0097\u00d5\u00ea\u001f\u0007\u0083\u00a7/*&\u00ebR\u0018\u001e\u0019\u00a2P(M8\u00a9\u0088)\u0004|m}qv\u00a8fS\u0085\u00c0,`\u000e\u0010_{K\t\u00ab+\u00c9}V\u00cb5c\u00c6\u00c6\u0091!(h,D\u00ff>#\u00ecy\u0006\u00e1Z\u00d9)\u00e5\u00d0\u00b8R\n:Z\u00dd\u00b5\u00fd\u00af\u00bd-\u00e1Jh\u00c0\u0012N\u00e6\u00e8\u00a4\u00a9j\u00b7\u0011\u00f0\u0010k\u0097\u0006\u00aa\u0013\u00d2\u00d9\u00b4\u00be\u00ac\u00af\u00ccXR4\u00dd\u0010\u00c7\u00a1\u001c\u009f\u00e8\u00b9h%2jM\u00ce9eb{\u0010 \u00b7\u00066\u00ba_\u00dc'\u00b4\u00c9J\u00d5\u0018,]\u00f3 \u00c8\u00d9\u00b1\u0013\u00f9|\u00d0-H>GC\u0018\u00b1\u00c5\u0085\u001c\u00b1!YIqJpV\u00d0\u00fc\u001fV\u008a\u00ac\u00d4\u0010\u00b6\u001d\u007f\u008fZ\u0019\u001a/\u00a0\u0019\u0017\u00a4m\u000b\u0081!8\u00d0\u00bf\u00fb\u0011jk\u0005<!-f2\u00c4\u0002\u00f8x\u0010\u009a\u00cf\u00c1\u00c1\u00198)sm\u00f2\u00c1]\u00b1\u00e6V\u00c2\u0005\u00d3\u00f0\u00bd\u0006#\u001a<\u008b\u00b0\u00fdc\u0086<\u00f4\u00c93@\u00f1\nJ_\u00e48\u00e9\u0086xU\u00bd6\u00fb\u001a\u00a5\u0087\u00ccI\u001e/l\u001a\u00d7TC\u009b)\u0015\u0087\u0005\u00ff\u0096r\u00f9Qx\u0084H\u0001\u0007\u00cf\u0090\u00fc\u00a1\u00f2SL\u00aa\u00b3\u00b5jj\u008a^\u00cd\u001b\u00acec\u00b6\u0015\u00e6X\u00c2\u00e5Yi\u0082\u00a2Anr\u0080J\u0002\u0088\u00fd\u009a\u00b5\\\u0004:\f\u001b\u00cf\u00ab\u0085\u009cF}\u00fb\u00c918?%\u0081PcvO\u00b8CP\u00cafwG\u009a\u00b4\u00ab\u0005\u00ef0\u001a\u00f2)\u0017E\u0085o\u00caJ+\u00d0P\u001dq\u00c2\u00bfB\u00f1\u00ec\u00c0S\u00e7\u00d8-\u00e8\u00fb\u0089\u00c9\u0089\u0088\u00a1\u00deW\u00e7\u00ac(q\u0010\u00f5\u00acA\u00d6\u0007Yf\u000bR\u00a0\u00d4\u001b0\u00c4\u00a5\u00f0\u0010\u00a8\u00f3\u00d8a\u0080\u001cT4\u00a9\u0094\u00fcx\u0089\u00ff\u001f\u008b\u0010\u0083\u00c9\u00f0t\u0088\u00d3\u00bc\u001dq\u0099YR!JK\u0085";
                        var17_6 = 487;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = Module.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u008f3\u0005m\u0010\u00f4\u00ac\u00ef\u00b2%\u009e\u008e\u0084M\u0007\u009c\u0010\u000ep\u0012\u00d5\u00acT\u00b7\u00b5\u0011\bY+\u00fcSk\u0012";
                            var17_6 = 33;
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
                            var18_3[var16_4++] = Module.zkmDecodeUtf8(var19_9).intern();
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
                Module.cb = var18_3;
                Module.db = new String[18];
                Module.kb = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "\u00b8kb\u00a0\u0084\u00d0\u00e9\u00ae\u00a8\u0012-K\u008e!\u008e\u00ee\u00fbjq\u008c\u0082)\u00a8i-3\n&\u0083\u00b5\u001e\u009fN\u00e3\u00e0\u00ee\u00e3\u00cb\u00cd\"";
                var5_15 = 40;
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
                    var4_14 = "\u00a6\u00e0\u00bf\u00d8\u0094\u00ac\u0082FEB\u00bd3A\u00e7\u00f7\u0015";
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
        Module.ib = var6_12;
        Module.jb = new Integer[7];
        var22_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Module.class, "enabled", "getEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Module.class, "bind", "getBind()Lsu/catlean/lj;", 0))};
        Module.delegatedProperties = var22_20;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        v = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return v;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3F75;
        if (db[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])eb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    eb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_g", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = cb[n2].getBytes("ISO-8859-1");
            Module.db[n2] = Module.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return db[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Module.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_g" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x638D;
        if (jb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = ib[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])kb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    kb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_g", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Module.jb[n2] = n3;
        }
        return jb[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Module.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_g" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Module.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Module.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

