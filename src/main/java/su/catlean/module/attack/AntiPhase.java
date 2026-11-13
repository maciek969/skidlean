/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.module.RotatingModule;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.EntityCheck;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.PingPredictor;
import su.catlean.zkm.ZkmKeys;

public final class AntiPhase
extends RotatingModule {
    @NotNull
    public static final AntiPhase INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting trapCrawlers$delegate;
    @NotNull
    private static final SettingGroup group$delegate;
    @NotNull
    private static final BooleanSetting ladders$delegate;
    @NotNull
    private static final BooleanSetting vines$delegate;
    @NotNull
    private static final BooleanSetting frames$delegate;
    @NotNull
    private static final BooleanSetting scaffoldings$delegate;
    private static final long h;
    private static final String[] i;
    private static final String[] n;
    private static final Map o;
    private static final long[] w;
    private static final Integer[] y;
    private static final Map F;

    private AntiPhase() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 12;
        boolean bl = false;
        int n2 = 0;
        Category category = Categories.getAttack();
        super("anti-phase", category, n2, bl, n, defaultConstructorMarker);
    }

    private final boolean getTrapCrawlers() {
        return (Boolean)trapCrawlers$delegate.getValue(this, delegatedProperties[0]);
    }

    private final SettingParent getGroup() {
        return (SettingParent)group$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getLadders() {
        return (Boolean)ladders$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getVines() {
        return (Boolean)vines$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getFrames() {
        return (Boolean)frames$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getScaffoldings() {
        return (Boolean)scaffoldings$delegate.getValue(this, delegatedProperties[5]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = h ^ 0x1C6E0C964455L;
        long l2 = l ^ 0x3AE1F05D9DCAL;
        RotatingModule.placeBlocks$default(this, this.findPhasedPlayers(), false, false, l2, null, false, 30, null);
    }

    private final List findPhasedPlayers() {
        AbstractClientPlayerEntity abstractClientPlayerEntity;
        List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list, "players(...)");
        Iterable iterable = list;
        Iterable iterable2 = iterable;
        Collection collection = new ArrayList();
        for (Object t : iterable2) {
            abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
            Intrinsics.checkNotNull(abstractClientPlayerEntity);
            if (!INSTANCE.isPhased(abstractClientPlayerEntity)) continue;
            collection.add(t);
        }
        iterable2 = iterable = (Iterable)((List)collection);
        collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object t : iterable2) {
            abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
            Collection collection2 = collection;
            Intrinsics.checkNotNull(abstractClientPlayerEntity);
            collection2.add(BlockPos.ofFloored(INSTANCE.getPos(abstractClientPlayerEntity)));
        }
        return (List)collection;
    }

    @Override
    @NotNull
    protected List getPlaceBlocks() {
        List list;
        block3: {
            list = new ArrayList();
            if (this.getScaffoldings()) {
                Item item = Blocks.SCAFFOLDING.asItem();
                Intrinsics.checkNotNullExpressionValue(item, "asItem(...)");
                list.add(item);
            }
            if (this.getLadders()) {
                Item item = Blocks.LADDER.asItem();
                Intrinsics.checkNotNullExpressionValue(item, "asItem(...)");
                list.add(item);
            }
            if (this.getVines()) {
                Item item = Blocks.VINE.asItem();
                Intrinsics.checkNotNullExpressionValue(item, "asItem(...)");
                list.add(item);
            }
            if (!this.getFrames()) break block3;
            Item item = Items.ITEM_FRAME;
            Intrinsics.checkNotNullExpressionValue(item, "ITEM_FRAME");
            list.add(item);
        }
        return list;
    }

    private final boolean isPhased(PlayerEntity playerEntity) {
        if (!playerEntity.isAlive() || Intrinsics.areEqual(Globals.getPlayer(), playerEntity) || playerEntity.isSwimming() || Friends.getIsHomie(playerEntity)) {
            return false;
        }
        if (Globals.getWorld().isBlockSpaceEmpty(playerEntity, playerEntity.getBoundingBox().offset(0.0, 0.1, 0.0).expand(1.0, 0.0, 1.0)) || PingPredictor.INSTANCE.isPhased(playerEntity).size() > 1) {
            return false;
        }
        if (this.hasEntityInside(new Box(BlockPos.ofFloored(this.getPos(playerEntity).add(0.0, 0.3, 0.0))))) {
            return false;
        }
        if (!this.getTrapCrawlers() && playerEntity.isCrawling()) {
            return false;
        }
        return Globals.getPlayer().distanceTo(playerEntity) <= this.getRange() + 3.0f;
    }

    private final boolean hasEntityInside(Box box) {
        boolean bl;
        block4: {
            Iterable iterable = Globals.getWorld().getEntities();
            Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
            Iterable iterable2 = iterable;
            Iterable iterable3 = iterable2;
            Collection collection2 = new ArrayList();
            Iterator<Entity> iterator2 = iterable3.iterator();
            while (iterator2.hasNext()) {
                Entity entity = iterator2.next();
                if (!(entity instanceof ItemFrameEntity)) continue;
                collection2.add(entity);
            }
            iterable2 = (List)collection2;
            if (iterable2 instanceof Collection && ((Collection)iterable2).isEmpty()) {
                bl = false;
            } else {
                for (Collection collection2 : iterable2) {
                    ItemFrameEntity itemFrameEntity = (ItemFrameEntity)((Object)collection2);
                    if (!itemFrameEntity.getBoundingBox().intersects(box)) continue;
                    bl = true;
                    break block4;
                }
                bl = false;
            }
        }
        return bl;
    }

    @Override
    @NotNull
    protected EntityCheck getEntityCheck() {
        return EntityCheck.NONE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AntiPhase.h = ZkmKeys.a(-2026720568093427720L, -6173730685574026536L, MethodHandles.lookup().lookupClass()).a(227687632189410L);
                        var20 = AntiPhase.h ^ 8213585110959L;
                        AntiPhase.o = new HashMap<K, V>(13);
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
                        var18_3 = new String[24];
                        var16_4 = 0;
                        var15_5 = "\u00ea\u00de*\\\u0090%Y\u0080g\u009fyw.!\u00bcP\u00aa\u00a4kS\u0087\u00ad\u0014\u008a\u0018\u008b4\u00e6#i9\b-\u00deD+\u00a5\u0015r\u0097\u0098\u00e9\u00b8*P\u00cc\u00b8\u00bah \u0011\u0099sC\u0010\u00b0\u00e7t`\u00b0N9G\u00fa\u0017[\u0012\u008f\u0013\u0083\u00ea/\u00e9\u00fc*t*\u0085\u001bN\u00bbr \f+\u0011\u00b8\u00fa\u00f5\u009e\u00d3+)\u00f3A\u00c9\u00ccTJe\u0097w`\u0092f\u00c1i&w\u00c8\u00bc\r\u007f4\u00df\u00101\u00bfM\u0015\b\u0004\u00ba\u00c2h\u00b4J\u00c3\u00d3\u007f\u00da\u0088 \u009c\u00c3u\b\"a\u00af\u00bbx\u00bb\u00e6\u001bW\u0010}h \u00cf+\u0086\u001b>\u00c0L\u00cc\u001e$\u00b1r\u00a4s\u008b \u00a5!Q>\r\u00f5\u00fd:\u0003\u0006\u0086:\u009c\u00f9\u00b8O\u0004\u00a44\u00e0+\u0097\u00f0\u00bb\u00b0\u00c3?\u00f2\u00f1\u001f\u0012k\u0010\u0087\u00aa\u0016\u00af\u008ciy\u0086\n*\u00e9\\\u0017\u008b'\u0099\u0010%\u00c8\u00f9\u00b1\u008cO\u00b5\u0019\u00cc\u00bf\u00ecPG\u00f6\u0012\u00fa \u00f2\u00c18\u0005\u00f8\u00fb\u00c0\u00ad\u00868q\u00ce\u00bb\u0000\u00d4\u00aa\u00b84\u00d2\u00da\u00bb\u0005\u00ce)\u00b8\u00c8I0\u00cb\u0084C\u00b5\u0010\u00f0\u000e1\u00cdL\u0080CW\u00c0j}\u00fa^\n\u00a4B(\"7\u009f\u00a8\u0011\r&\u0097@\u00e7\u0000\u00af\u00e7\u00fb\u00d3\u00b8^+\u008d\u00bd\u0002M\u00f2,V\u001f\u00fbi\u0019\u00cdg\u00e5\u008a\u00c2\u00a5\u008fiM\u00ae\u0098\u0010\u00c8\u00c0^\u009c\u0015\u0011\u00bb\u00a6\u00da\u0013\u00e5(\u008e\r\u00c0\u00d1\u0018\u00ddX\u000e\u00d6\u00d4{\u0005\u00d6E\u00fe\"\u00c2\u00a8~o\u0017>\u00f5U\u000f6P\u001a\u009d8\u00fcTK\u00dc\u0082q\u00fd\u0087h\u00ca\u00f4\f\u0096\u00a9$rW\u00f3\u00be\u009b\u00a9\u0018%#\u00c89:T\u0017O4\u00d8\u00a7\b\b\u0099*i]\u0091\u00cb\u009d\u00b3\u00f2\u00c0I\u008a\"F\u00d55\u00f14\u00e8ZA \u00d9\u00aa$.\u00ae\u00cb\u00a0\u00fe\u00f6=\u00f7\u00fb7\u00b1>1\u00ccv\u001f\u009f\u0015w\u00bf~Tq\u00e8\u008d\u00ebD\u008b\f\u0018\u0095t\u00fc\u00ecZ7\u00f7#\u00bb\u00f9Eb\u0012`\u00bdj\u00faM~\u00b6e\u00cc\u00d2\u00f3\u0010\u00e8\u00b5\u00ae\u00b8]6\u00cf\u0007a\u00e5\u00c5+\u00ce\u00c4\u0097\u00f9\u0010Uo \u0086\u00a0\t\u009b\u0084\u00ae\u0084%\u0081\u00f5B@,\u0010\u00dd\u00cd0AB\u00a6p\u00b1\u00e3A\u001f\u0081t\u009a\u0019m(c\u0014U \u001fW:\u0007\u00bb\u00ab\u00c9\u0094\u00de\u00a0\u00cd\u00b1\u008b]0\u00a2<J\u0098T\\f\u001bD\u0096\u00f4\u008e\"\u00f92\u0006\u0010\u00b3\rW\u00ad >\u00914Uw\t\u00b5;\u00ad\u00d8T-\u009f\u00cae<\u001e\u0083\u00e8\u0093T\u0006\u00107/\u00d9\u00c5\u00df\u00a9\u00dd\u00c5\u009f";
                        var17_6 = 605;
                        var14_7 = 24;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AntiPhase.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0085\u00be!\u0012\u00f1\u001e\u00f0\u001c%N\u001c\u00a7\u001d\u00c0\u001dz\u00cc\u00f9\u00823sA\u00b8\u00fd\u0003\u0091\u00ae\u00e7ql\u0087\u00dex9\u00fa\u00f4\u00dc\u00a65\u00b3\u0013\u00c6|\u0007%\\\u00bd\u009f\u0087G\u00030\u00d7\u00b6\u00e6\u0096 ?&\u00fbA\u009f\u0096\u00d6P\u00eb\u00d3\u0013bV7D\u0015U\u00bc\u00ec\u0088\u00fb1\u00c0W\u00c3O\u009d\u00f8v\u00b5\u00d1+";
                            var17_6 = 89;
                            var14_7 = 56;
                            var13_8 = -1;
lbl34:
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
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AntiPhase.zkmDecodeUtf8(var19_9).intern();
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
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                AntiPhase.i = var18_3;
                AntiPhase.n = new String[24];
                AntiPhase.F = new HashMap<K, V>(13);
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
                var4_14 = "\u0096g \u00ce\u00c2q\u00e2\u0086\u00ff\u00b7?\u00d3\u00b4\u009a\u0006\u00f4qEI\u00c4\u00b0ss\u001c\u0004$\u00d1\u00d7\u00a0\u00b2\u00ab\u008f\u00e7\u0085>\u00b4\u00bf\u00dd\u000ea";
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\t\u00b8\u008e\u00cd\u0092\u0085\u00ab!\u00ef\u00c6\u00c7\u00ba5\u00e8\u00b8\u00de";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        AntiPhase.w = var6_12;
        AntiPhase.y = new Integer[7];
        var31_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AntiPhase.class, "trapCrawlers", "getTrapCrawlers()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiPhase.class, "group", "getGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(AntiPhase.class, "ladders", "getLadders()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiPhase.class, "vines", "getVines()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiPhase.class, "frames", "getFrames()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiPhase.class, "scaffoldings", "getScaffoldings()Z", 0))};
        AntiPhase.delegatedProperties = var31_20;
        AntiPhase.INSTANCE = new AntiPhase();
        AntiPhase.trapCrawlers$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiPhase.INSTANCE, "trap-crawlers", false, null, null, 12, null);
        AntiPhase.group$delegate = SettingFactory.settingGroup$default(AntiPhase.INSTANCE, "blocks", AuraTargetsMode.BOOLS, null, 4, null);
        AntiPhase.ladders$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiPhase.INSTANCE, "ladders", true, AntiPhase.INSTANCE.getGroup(), null, 8, null);
        AntiPhase.vines$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiPhase.INSTANCE, "vines", true, AntiPhase.INSTANCE.getGroup(), null, 8, null);
        AntiPhase.frames$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiPhase.INSTANCE, "frames", true, AntiPhase.INSTANCE.getGroup(), null, 8, null);
        AntiPhase.scaffoldings$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiPhase.INSTANCE, "scaffoldings", true, AntiPhase.INSTANCE.getGroup(), null, 8, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x62F2;
        if (AntiPhase.n[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])o.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_e", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = i[n2].getBytes("ISO-8859-1");
            AntiPhase.n[n2] = AntiPhase.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return AntiPhase.n[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AntiPhase.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_e" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7AE9;
        if (y[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = w[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])F.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    F.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_e", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AntiPhase.y[n2] = n3;
        }
        return y[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AntiPhase.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_e" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AntiPhase.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AntiPhase.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

