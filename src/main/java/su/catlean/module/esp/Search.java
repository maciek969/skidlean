/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.module.esp;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.BlockStateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.esp.Search$onRender3D$1;
import su.catlean.module.esp.Search$onRender3D$2;
import su.catlean.module.esp.SearchChunk;
import su.catlean.module.esp.SearchResult;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.EntityListSetting;
import su.catlean.setting.EntityListValue;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.FpsCounter;
import su.catlean.util.Globals;
import su.catlean.util.ServerInfo;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class Search
extends Module {
    @NotNull
    public static final Search INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BlockListSetting selectedBlocks$delegate;
    @NotNull
    private static final EntityListSetting selectedEntities$delegate;
    @NotNull
    private static final BooleanSetting limit$delegate;
    @NotNull
    private static final IntSetting limitCount$delegate;
    @NotNull
    private static final BooleanSetting mapColor$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @NotNull
    private static final BooleanSetting tracers$delegate;
    @NotNull
    private static final BooleanSetting fill$delegate;
    @NotNull
    private static final BooleanSetting outline$delegate;
    @NotNull
    private static final ArrayList chunks;
    private static final ExecutorService executor;
    @NotNull
    private static final Timer rescanTimer;
    private static long J;
    private static boolean m;
    private static final long d;
    private static final String[] e;
    private static final String[] f;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map j;

    private Search() {
        super("search", Categories.getEsp(), null, null);
    }

    private final BlockListValue getSelectedBlocks() {
        return (BlockListValue)selectedBlocks$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setSelectedBlocks(BlockListValue blockListValue) {
        selectedBlocks$delegate.setValue(this, delegatedProperties[0], blockListValue);
    }

    private final EntityListValue getSelectedEntities() {
        return (EntityListValue)selectedEntities$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setSelectedEntities(EntityListValue entityListValue) {
        selectedEntities$delegate.setValue(this, delegatedProperties[1], entityListValue);
    }

    private final boolean getLimit() {
        return (Boolean)limit$delegate.getValue(this, delegatedProperties[2]);
    }

    private final int getLimitCount() {
        return ((Number)limitCount$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    private final boolean getMapColor() {
        return (Boolean)mapColor$delegate.getValue(this, delegatedProperties[4]);
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getTracers() {
        return (Boolean)tracers$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getFill() {
        return (Boolean)fill$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getOutline() {
        return (Boolean)outline$delegate.getValue(this, delegatedProperties[8]);
    }

    @Override
    public void onEnable(long l) {
        chunks.clear();
        J = System.currentTimeMillis();
        m = true;
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        block0: {
            Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
            if (!m) break block0;
            executor.submit(Search::onPlayerUpdate$lambda$0);
        }
    }

    @Flow
    public final void onBlockState(@NotNull BlockStateEvent blockStateEvent) {
        Intrinsics.checkNotNullParameter(blockStateEvent, "e");
        executor.submit(() -> Search.onBlockState2(blockStateEvent));
    }

    @Flow
    public final void onAfterReceivePacket(@NotNull AfterReceivePacket afterReceivePacket) {
        Intrinsics.checkNotNullParameter(afterReceivePacket, "e");
        if (afterReceivePacket.getPacket() instanceof ChunkDataS2CPacket) {
            Packet<?> packet = afterReceivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket");
            ChunkDataS2CPacket chunkDataS2CPacket = (ChunkDataS2CPacket)packet;
            executor.submit(() -> Search.onAfterReceivePacket$lambda$0(chunkDataS2CPacket));
        }
    }

    private final void refresh() {
        m = false;
        executor.submit(Search::refresh$lambda$0);
    }

    private final List scanChunk(ChunkPos chunkPos) {
        List list = new ArrayList();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int n = chunkPos.x * 16;
        int n2 = chunkPos.z * 16;
        for (int i = -64; i < 320; ++i) {
            for (int j = 0; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    Block block;
                    mutable.set(n + j, i, n2 + k);
                    Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(mutable).getBlock(), "getBlock(...)");
                    if (!this.isSelected(block)) continue;
                    BlockPos blockPos = mutable.toImmutable();
                    Intrinsics.checkNotNullExpressionValue(blockPos, "immutable(...)");
                    list.add(new SearchResult(blockPos, this.getMapColor() ? new Color(block.getDefaultMapColor().color) : this.getColor()));
                }
            }
        }
        return list;
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        long l = d ^ 0x6001E8936B8DL;
        long l2 = l ^ 0x640AA17D8021L;
        Intrinsics.checkNotNullParameter(render3DEvent, "e");
        if (chunks.isEmpty() && this.getSelectedEntities().getEntities().isEmpty()) {
            return;
        }
        int n = 0;
        if (FpsCounter.INSTANCE.D() < 8 && Globals.getPlayer().age > 100) {
            this.disable(l2);
            return;
        }
        if (this.getFill() || this.getOutline()) {
            Iterator<Entity> iterator2;
            Object object;
            ArrayList arrayList = Lists.newArrayList((Iterable)chunks);
            Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
            Object object322 = CollectionsKt.filterNotNull(arrayList);
            for (Object object322 : CollectionsKt.sortedWith(object322, new Search$onRender3D$2())) {
                Iterable iterable = ((SearchChunk)object322).getList();
                for (SearchResult searchResult : CollectionsKt.sortedWith(iterable, new Search$onRender3D$1())) {
                    Color color;
                    if (n > this.getLimitCount() && this.getLimit()) continue;
                    Entity entity = Globals.getPlayer();
                    if (searchResult.getPos().toCenterPos().distanceTo(this.getPos(entity)) > (double)((((Number)Globals.getMc().options.getViewDistance().getValue()).intValue() + 4) * 16)) continue;
                    object = new Box(searchResult.getPos());
                    Color color2 = color = this.getMapColor() ? RenderUtil.INSTANCE.withAlpha(searchResult.getColor(), 0.5f) : this.getColor();
                    if (this.getFill()) {
                        RenderState.addFilledBox$default(RenderState.INSTANCE, (Box)object, color, null, null);
                    }
                    if (this.getOutline()) {
                        float f = (float)color.getAlpha() * 2.0f;
                        Object var64_7 = null;
                        Color color3 = null;
                        Color color4 = RenderUtil.INSTANCE.withAlpha(color, f);
                        Object object4 = object;
                        RenderState.addOutlineBox$default(RenderState.INSTANCE, (Box)object4, color4, color3, var64_7);
                    }
                    if (this.getTracers()) {
                        Intrinsics.checkNotNullExpressionValue(new Vec3d(0.0, 0.0, 75.0).rotateX(-((float)Math.toRadians(Globals.getMc().gameRenderer.getCamera().getPitch()))).rotateY(-((float)Math.toRadians(Globals.getMc().gameRenderer.getCamera().getYaw()))).add(Globals.getMc().gameRenderer.getCamera().getCameraPos()), "add(...)");
                        Vec3d vec3d = searchResult.getPos().toCenterPos();
                        Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
                        RenderState.addLine$default(RenderState.INSTANCE, (Vec3d)((Object)iterator2), vec3d, color, null, true, 8, null);
                    }
                    ++n;
                }
            }
            Iterable<Entity> iterable = Globals.getWorld().getEntities();
            Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
            object322 = iterable;
            Iterable<Entity> iterable2 = object322;
            object = new ArrayList();
            iterator2 = iterable2.iterator();
            while (iterator2.hasNext()) {
                Entity entity;
                Entity entity2 = entity = iterator2.next();
                if (!INSTANCE.getSelectedEntities().getEntities().contains(entity2.getType())) continue;
                object.add(entity);
            }
            for (Object object2 : (List)object) {
                Vec3d vec3d;
                Vec3d vec3d2;
                Intrinsics.checkNotNullExpressionValue(object2, "next(...)");
                object322 = (Entity)object2;
                if (Intrinsics.areEqual(object322, Globals.getPlayer())) continue;
                Box box = RenderState.INSTANCE.getRenderBox((Entity)object322);
                if (this.getFill()) {
                    Object var71_25 = null;
                    Color color = null;
                    Color color3 = this.getColor();
                    Object object3 = box;
                    RenderState renderState = RenderState.INSTANCE;
                    RenderState.addFilledBox$default(renderState, (Box)object3, color3, color, var71_25);
                }
                if (this.getOutline()) {
                    float f = (float)this.getColor().getAlpha() * 2.0f;
                    Object var83_31 = null;
                    Color color = null;
                    Color color4 = RenderUtil.INSTANCE.withAlpha(this.getColor(), f);
                    Object object4 = box;
                    RenderState.addOutlineBox$default(RenderState.INSTANCE, (Box)object4, color4, color, var83_31);
                }
                if (!this.getTracers()) continue;
                Intrinsics.checkNotNullExpressionValue(new Vec3d(0.0, 0.0, 75.0).rotateX(-((float)Math.toRadians(Globals.getMc().gameRenderer.getCamera().getPitch()))).rotateY(-((float)Math.toRadians(Globals.getMc().gameRenderer.getCamera().getYaw()))).add(Globals.getMc().gameRenderer.getCamera().getCameraPos()), "add(...)");
                Intrinsics.checkNotNullExpressionValue(box.getCenter(), "getCenter(...)");
                Object var90_35 = null;
                int n2 = 8;
                boolean bl = true;
                Color color = null;
                Color color5 = this.getColor();
                Vec3d vec3d3 = vec3d2;
                RenderState.addLine$default(RenderState.INSTANCE, vec3d3, vec3d, color5, color, bl, n2, var90_35);
            }
        }
        J = System.currentTimeMillis();
    }

    private final boolean isSelected(Block block) {
        return this.getSelectedBlocks().getBlocks().contains(block);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getLimit();
    }

    private static final boolean settingVisible2() {
        return !INSTANCE.getMapColor();
    }

    private static final void onPlayerUpdate$lambda$0() {
        INSTANCE.refresh();
    }

    private static final void onBlockState2(BlockStateEvent blockStateEvent) {
        ArrayList arrayList = chunks;
        synchronized (arrayList) {
            Object v0;
            block5: {
                int n = blockStateEvent.getPos().getX() >> 4;
                int n2 = blockStateEvent.getPos().getZ() >> 4;
                Iterable iterable = chunks;
                for (Object t : iterable) {
                    SearchChunk searchChunk = (SearchChunk)t;
                    if (!(searchChunk.getZ() == n2 && searchChunk.getX() == n)) continue;
                    v0 = t;
                    break block5;
                }
                v0 = null;
            }
            SearchChunk searchChunk = v0;
            if (searchChunk != null) {
                searchChunk.setRecalc(true);
            }
        }
    }

    private static final void onAfterReceivePacket$lambda$0(ChunkDataS2CPacket chunkDataS2CPacket) {
        ArrayList arrayList = chunks;
        synchronized (arrayList) {
            Object v0;
            block5: {
                Iterable iterable = chunks;
                for (Object t : iterable) {
                    SearchChunk searchChunk = (SearchChunk)t;
                    if (!(searchChunk.getZ() == chunkDataS2CPacket.getChunkZ() && searchChunk.getX() == chunkDataS2CPacket.getChunkZ())) continue;
                    v0 = t;
                    break block5;
                }
                v0 = null;
            }
            SearchChunk searchChunk = v0;
            if (searchChunk != null) {
                searchChunk.setRecalc(true);
            }
        }
    }

    private static final boolean refresh$lambda$0$lambda$0(int n, int n2, SearchChunk searchChunk) {
        Intrinsics.checkNotNullParameter(searchChunk, "it");
        return Math.hypot((double)n - (double)searchChunk.getX(), (double)n2 - (double)searchChunk.getZ()) > (double)((((Number)Globals.getMc().options.getViewDistance().getValue()).intValue() + 4) * 16);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final void refresh$lambda$0() {
        ArrayList arrayList = chunks;
        synchronized (arrayList) {
            boolean bl = rescanTimer.hasPassed2(1500);
            for (ChunkPos chunkPos : ServerInfo.INSTANCE.getChunksInView()) {
                Object v0;
                Iterable iterable;
                block8: {
                    iterable = chunks;
                    for (Object t : iterable) {
                        SearchChunk searchChunk = (SearchChunk)t;
                        if (!(searchChunk.getZ() == chunkPos.z && searchChunk.getX() == chunkPos.x)) continue;
                        v0 = t;
                        break block8;
                    }
                    v0 = null;
                }
                SearchChunk searchChunk = v0;
                if (searchChunk != null && bl && searchChunk.getList().isEmpty()) {
                    chunks.remove(searchChunk);
                } else if (searchChunk != null && !searchChunk.isRecalc()) continue;
                iterable = INSTANCE.scanChunk(chunkPos);
                if (searchChunk != null && searchChunk.isRecalc()) {
                    chunks.remove(searchChunk);
                }
                DefaultConstructorMarker defaultConstructorMarker = null;
                boolean bl2 = false;
                Iterable iterable2 = iterable;
                int n = chunkPos.z;
                chunks.add(new SearchChunk(chunkPos.x, n, (List)iterable2, bl2, defaultConstructorMarker));
                int n2 = (int)Globals.getPlayer().getX() >> 4;
                int n3 = (int)Globals.getPlayer().getZ() >> 4;
                chunks.removeIf(arg_0 -> Search.invokePredicate(arg_0 -> Search.refresh$lambda$0$lambda$0(n2, n3, arg_0), arg_0));
            }
            m = true;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Search.d = ZkmKeys.a(-3048513154843461334L, -3887378791680686365L, MethodHandles.lookup().lookupClass()).a(90942984204258L);
                        var20 = Search.d ^ 77436458330171L;
                        Search.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[39];
                        var16_4 = 0;
                        var15_5 = ">\u00d7#K\u0089\u00b5(\u00bf\u0004\u00b0\u00db\u00de\u00b5\u008d\u0018\u00e7\u0018F\u00e8Iw\u00ab\u009c\u00b7o\u00a8\u0097\u00e0{CDy\u00b9\u00a9\u00bb5\u00cf\u00f5\u00f6\u00d0v \u00f2\u00d5\u00bb\u00b0\u00c1>R,\u00f8b\u007f\u00e8\u00db5:\u00c5\u00dd\u0096\u00f4YZ\u00c0\u00efI\u00dc;E\u0002\u0097\u00d4\u00bd\u009f(\u000b\u00a0\u00f6n\u00b0\u00a7g\u00cbN\u0080\u00c5\u00cdV\u00c0\u00f2\u001a\u0090Ol\u00a1\u008f\u00f5D\"\u0018\f\u009d\u00864\u0017F\u00da\u00eby\u001a4\u00f40\u0094o \u00d1\u00be\u00db\u00c6\u00ff\u00f7\u00f4VR\u00e2@\u0002\u00d39\u008d\u00b1kF\u00f4\u0001\u0084\u00ffd?r\u00da\u00e6\u00d2\u0089/\u009d3\u0018\u008c\u00a2\u009e\u00e5%\u00df7J7\u009d(z!\u0086\u0092d\u00fa\u001f\u00da\u00fe\u00d7\u00ecJi\u0018\u00a0w\u009c\u00bf\u0003\u00bcm\u0010\u00aa\u00c6y\u00a4S'\u00d6\u00ad'\u00e9\u008cO\u0094*=6\u0010<e\u0013\u001ck\u0011z\r\u001d\u008b\u00ef\u0093\u00a7\u008a\u0091\u00ae =\u00c4J\u0084!\u001cNx4yb\u00adHwQ\u00afb\u00f9\u00ef\u00c6\u00bd\u00ca\u0007Qg\u00e6!\u0017\u00a0q\\\u00d8 \u009alh\u00e7a%\u0011H\u0083\u001a\u0007F\u00e4\u0084\u00b2mR\u00ab\u001ewg\u00ed\u00dd\u0001\u001f\u00c7J\u00b4:\u0081\u0096)\u0010\u00d0\rGbTN\u00b0\u00a39 9E\u00d9\u0017\u0016\u00d0\u0010\u00a2\u00fbj\u00eaC\u00d1\u00b1\u00bd*\u00cc\u00f2\u00cdh5\u00bfB\u0018{\u00cfG+\u00ab\u0018D\u0003\u00fc\u00c1\u00ab\u00ea\f\u00cej\u00d7]\u00d3ne\u00c5\u0096\u007f$0$\u0010k0\u00eawf\u00a8\u00de\u00ca\u00cf\u00cd\u00cb\u00b5t\u00cc\u0001T\u00e7}!z0\u00f9*WTK\u00f3\u00d3\u00a1\u00ee\u0088\u00add\u0012/\u008b-\u00eb\u00c9\u009fc?\u00e3'\u0085% \u0097\u009f\u00d8\u00f1\u0097\u0017L\u00da\u0082\u0089F\t\u000e\u00e0Q\u00bbM`$h\u00d8\u0001\u00ccI\u00de\u0093)\u0005\u0019\u00c1g+H\u00bc\u00d6\u00c8\u00d9a\u0082f\u0088\u0002\u0084:\u00e0\u0015\u00b2\u0090 \u00c9\u00956\u00fe\u00b8\u00af\u0007\u00b3+n\b\u0019Jl\u00d8V\u00ceE\u0081\t\u00d7E\u00c1\u008d\u00e6|\u0081\u00b9\u0082C\u0091\u00b6\u00ca\u00ff\u00fb\u0005s\u0094\u00a3\u00ed\u009f\u00daW\u0006\rq\u0013\u00bf\u00fb\u0097\u0019z\u0010\u00e3\u00b8\u00d7 s|m\u00b9\u00bb\u00e4\u00b3\u00a2\u0017L\u00fe\u00eb\u00f2L\u00eeZ1\u00a6\u00e1!\u000e\u009d\u0099Ex~h4\u0018\u0002\u00e6\u0092\u0010\u00cf)3.\u00a7\u00c9(\u008d\u0087\u00c1\u008d\u00a5\u0019\u0005\u00df \u0010F\u0091y\u008e\u00bb\u0093\u00dd\u00ac\u00f4\u00e1\u009d\u00b0\u00bc\u00e6\u0094\u00e9 +XW,b@F~%Q\u008f\u00b6\u00d2v\u00e1\u00ad\u0005.GL\u0010\u00c5\u00a5\u00c0\u008e\u0014\f\u00b9\u00ab\u0091\u0091\u00a2\u0010\u00dai\u00d0Ak\u0002p\u007f\u00f4q\u00b5\u00f5\u0094\u0003\u00e68\u0010\u00bf\u001c\u00cc\u00ed\u00f1\u00d9Y\u00af\u0014\u0081\u00a9\u00d5{\u001f\u00c2\r(\u00db\u0089\\U\u0091\u00be`\u008f\u001f\u00fcA\u00c5\u0007;D\u008c\u0004`\u009dtO?-\u00a2\u00f1\u0090\u00ac\u009c\u00cdt\u00b0\u0018\u0018\u00f9\u009a\u008a%K\u009f\u001a\u0018P9\u00e1\u008a\u008al\u00b1)\u009b\u00b9\u00ac_\u00a5\u009b\u00bd\u00cc:\u00ff\u00e8L\u00e4(\u00d6<\u0010\u00d4\u0007)\u00fd\u00a0O\u00d2\u0016\u00d5\faVwR\u00b2\u0010 c\u0099\u001b\u0006\u008a\u00c0\u0087\u0010xO\u00be\u0003\u000e#\u00aa\u00fb\u00aa\u00f5\u00ba\u0017\u0080k\u00af\u00dd\u0096\u00b5}\u00f2\u00ac\u00b5+\u00858l\u00f3#\u0081\u00f6\u0013o\u00c6|0\u00041\u00efdN\u0004\u00d1WwO\u00951 \u008aHG\u008d\u00b2\u00f27\nB\u0006\u0095\u00ad\u00a4\u0084[\u00f5\u009d\u009cp\u00b1\u00e7Jg(\u00d1\u0015\u00f3LX\"\u00aey\u00ec0\u00bcv\u0018@!i\u00d9a9\u0090un\u009cT@s\u00caZm-\u00b4\\|^\u00d4#\u00a0\u00d9:\u0019U\u00ac\u000b`<q\u00bf\n\u00a4\u00c1CBc\u00aa\u008f\u00fd\u00c7]\u0018\u008dK\u008a\u00f4\u00d7a\u008e'G^VC\u00d1?\u00f4\u00faIw\b\u00ba\"\u00ddw\u00a5\u0018\u0004\u00dd=\u0089k\u00fce\u00ac\u00ec_-\u00ab\u00918U\u00a7\f\u00a7=-\u00ea \u00ccr\u0010\u009c\u0013Mq\u0098\u0001\u0080\u0018\u0087\u0001)\u00acZ\u00ca4^\u0010\u00b4\u0090\u00d2`\u00e4\u0001L\u00e1'*\u00ad\u00f1-\u00c0\u0012z@\u00dfX\u00da\u0093\u00a9\u001f\u0011_\u00b8\u00c8\u009b\u00da\\\u0083\u008c\u00acR~\u00a4\u009a\u00d7\u008d\u00cb\u0090|V\u00fa+D\u009b\u00f0\u00fe\u00a7'\u0083\u00b9\bn\u00ea\u00ed\u00a7\\\u00f0Ac\u0097H\u000f4\u00b1h\u00f4\u00ad?\u00d3\u00f9u \u0083\\\u0082!\u0013\"\u00105\u00a5_m\u00cf8\r\u000e\u00f3C\u00cf\"Y\u00d5\fQ \u00c0j\u00c06\u00e8\u00e3\u00ec\u00a8\u0086\u00a6'\u00c9\u00fc\u00ac\u0097\u00f4&3\u00b8\u0004\u00a1\u00fc\u00ccr\u001b\u00a8\u00bd\u00eb\u0082\u0086\u00c1\u00d2\u00a8\u0014\u0003\u00a0\u0012\u00acq>\u00a4y\u00faj\u00e6\u001eo\u001d|\u00c9\u0086\u00c5/\u00c6\u00dd\u00fa3\u00bdP\u0086\u00e44Z2\u00b0q\u00a6\u0085}eGL\u0016\u0089r\u00a3\u00c8\u00eeP-\u00a3\u000f\u0096R&\u00f5\u00bfc\u00cd\u00b6N\u0084\u00c2\"\u00eb$\u00f2\u00f48\t\u00efW\u00bb3\u00c6\u00f4\u00a4;\u009d+\u009d\u0014\u0011'\u00aa\u00f0\u0011+:_\u00d6\u00ef\u00fe\u00f0\u00bd\u00aa\u0005\u0097\u00cc\u00f9X!R\u0083\u00f7\u00f7[\u00a1|\u009f\u00f9\u00be\u00f3vS\u0005}\u00d2\u00c5\u009fB\u0082Y|\u00c4$o\u00d2{S\u008b\u00db=%\u00c3lw\u00c4r\u00fd\u00f2G\u009d\u00c0\u00f1\u00d2\"1\"\u0013\u00ba\u0082\u00e3u0\u00e9\u00b8\u00d5\u00ef\u00e1{\"\u0081\u00d6a\u00e6\u0013[0\u0087W\u0010>6,1\u0004\u00a6\u00b5\u0083:\u0016\u00d6\u00ca1\u0006\u00fe\u001b";
                        var17_6 = 1236;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = Search.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = ">\u00b6H\u00a4\u00a6\u00d6\u00a4\u0011\u0002\u00e0p~\u00c2\u00aa\u0019\u0003\u008f\u00a1/a\u0011\u001a\b\u0012\u00d2\u00b7\u00bb\u0013y-\u00f2\u00a6 \u001b\u00de\u00ea\u00f5A\u00b7\u00ad\f!\u0001\u0007\u0089c\u00fa\u008a\u00e1f\u009d\u00d0\u0016\beS\u00e7!Rce\u00d4\u00c3\u0099\u0089";
                            var17_6 = 65;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = Search.zkmDecodeUtf8(var19_9).intern();
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
                Search.e = var18_3;
                Search.f = new String[39];
                Search.j = new HashMap<K, V>(13);
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
                var6_12 = new long[19];
                var3_13 = 0;
                var4_14 = "m\u00a6|\u00c3\u0016T\u00f7&\u00e5\u00fb\u00d5\u00b3\u00f8\u0082\u00f51\u009a\u00c8\u00fa\u00e6\f\u00a6\u0087\f\u00ee(\f\u009e\u00c9bR\u00e9\u008a\u0015\u0097\u00da\u00a1\u00e7d ~\b\u0012\u0013\u00ed\u0098\u0016\u0001\u001f\u00ec)\u001ck\u00ac\u001aG\u00ef\u00ed\u00d6h8\u0084f\u008b\u001af\u00ecSzN\u0015H=\u00d2Ok\u00ec\u00abX\u0014I\u00bd\u00fc\u0093PUH\u0089\u00a1\u00cd\u00df\u00e6\u00a5\u00c6`tk\u00e5\u00d6\u008b] \u00aef\u00dcMF1\u00fa\u00ee\u0011\u00e3$9\u00ebLf\u008a\u00f2\u00d8\u00b2\u0015\u009e\u00aefD\u00bb\u00cf\u0087l\u00ff\u0080Y\u00ce=\u0012";
                var5_15 = 136;
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
                    var4_14 = "2\u0013\r\u0094\u00c6@A\u0097}\\\u0081\u009d\u0002\u00edn\u009b";
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
        Search.h = var6_12;
        Search.i = new Integer[19];
        var47_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Search.class, "selectedBlocks", "getSelectedBlocks()Lsu/catlean/dg;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Search.class, "selectedEntities", "getSelectedEntities()Lsu/catlean/d4;", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "limit", "getLimit()Z", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "limitCount", "getLimitCount()I", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "mapColor", "getMapColor()Z", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "color", "getColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "tracers", "getTracers()Z", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "fill", "getFill()Z", 0)), Reflection.property1(new PropertyReference1Impl(Search.class, "outline", "getOutline()Z", 0))};
        Search.delegatedProperties = var47_20;
        Search.INSTANCE = new Search();
        var42_21 = null;
        var44_22 = null;
        var45_23 = null;
        var46_24 = new BlockListValue(null, null);
        Search.selectedBlocks$delegate = SettingFactory.blockListSetting(Search.INSTANCE, "blocks", (BlockListValue)var46_24, var45_23, var44_22, var42_21);
        var42_21 = null;
        var44_22 = null;
        var45_23 = null;
        var46_24 = new EntityListValue(null, null);
        Search.selectedEntities$delegate = SettingFactory.entityListSetting(Search.INSTANCE, "entities", (EntityListValue)var46_24, var45_23, var44_22, var42_21);
        Search.limit$delegate = SettingFactory.booleanSetting$default((SettingContainer)Search.INSTANCE, "limit", true, null, null, 12, null);
        Search.limitCount$delegate = SettingFactory.intSetting$default(Search.INSTANCE, "limit-count", 50, new IntRange(1, 2048), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        Search.mapColor$delegate = SettingFactory.booleanSetting$default((SettingContainer)Search.INSTANCE, "map-color", false, null, null, 12, null);
        Search.color$delegate = SettingFactory.colorSetting$default(Search.INSTANCE, "color", new Color(170, 0, 0, 120), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        Search.tracers$delegate = SettingFactory.booleanSetting$default((SettingContainer)Search.INSTANCE, "tracers", false, null, null, 12, null);
        Search.fill$delegate = SettingFactory.booleanSetting$default((SettingContainer)Search.INSTANCE, "fill", true, null, null, 12, null);
        Search.outline$delegate = SettingFactory.booleanSetting$default((SettingContainer)Search.INSTANCE, "outline", true, null, null, 12, null);
        Search.chunks = new ArrayList<E>();
        Search.executor = Executors.newSingleThreadExecutor();
        Search.rescanTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6DA3;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kx", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            Search.f[n2] = Search.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Search.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2052;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kx", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Search.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Search.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Search.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Search.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

