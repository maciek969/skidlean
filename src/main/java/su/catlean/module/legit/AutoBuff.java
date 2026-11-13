/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.legit;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.legit.AutoBuffTriggerOnMode;
import su.catlean.module.player.SpeedMine;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.SwapMode;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoBuff
extends Module {
    @NotNull
    public static final AutoBuff INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting strength$delegate;
    @NotNull
    private static final BooleanSetting speedPotion$delegate;
    @NotNull
    private static final BooleanSetting fire$delegate;
    @NotNull
    private static final BooleanSetting heal$delegate;
    @NotNull
    private static final IntSetting healthH$delegate;
    @NotNull
    private static final BooleanSetting regen$delegate;
    @NotNull
    private static final ModeSetting triggerOn$delegate;
    @NotNull
    private static final IntSetting healthR$delegate;
    @NotNull
    private static final IntSetting potionsPerTick;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final TickTimer buffTimer;
    private static int y;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private AutoBuff() {
        super("auto-buff", Categories.getLegit(), null, null);
    }

    private final boolean getStrength() {
        return (Boolean)strength$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getSpeedPotion() {
        return (Boolean)speedPotion$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getFire() {
        return (Boolean)fire$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getHeal() {
        return (Boolean)heal$delegate.getValue(this, delegatedProperties[3]);
    }

    private final int getHealthH() {
        return ((Number)healthH$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final boolean getRegen() {
        return (Boolean)regen$delegate.getValue(this, delegatedProperties[5]);
    }

    private final AutoBuffTriggerOnMode getTriggerOn() {
        return (AutoBuffTriggerOnMode)((Object)triggerOn$delegate.getValue(this, delegatedProperties[6]));
    }

    private final int getHealthR() {
        return ((Number)healthR$delegate.getValue(this, delegatedProperties[7])).intValue();
    }

    private final int Y() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        return ((Number)potionsPerTick.getValue(this, delegatedProperties[AutoBuff.zkmBootstrap2("e", (int)4408, (long)(0x4EDA60B680CCD0EEL ^ l))])).intValue();
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[9]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        long l = 0L;
        l = a ^ l;
        switchMode$delegate.setValue(this, delegatedProperties[AutoBuff.zkmBootstrap2("e", (int)15357, (long)(0x3FB53D06338F21AL ^ l))], (Object)swapMode);
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block0: {
            long l = a ^ 0x4B1F81197066L;
            long l2 = l ^ 0x538533B2CBBCL;
            int n = (int)(l2 >>> 32);
            int n2 = (int)(l2 << 32 >>> 48);
            int n3 = (int)(l2 << 48 >>> 48);
            y = 0;
            if (!this.canBuff(n, n2, (char)n3)) break block0;
            RotationManager.INSTANCE.submit(new RotationRequest(new Rotation(MovementUtil.INSTANCE.getLastYaw(), 90.0f, false, null, 12, null), 200, AutoBuff::onPlayerUpdate$lambda$0));
        }
    }

    private final void checkEffects(long l) {
        long l2 = (l = a ^ l) ^ 0x4E89DFEB3FCDL;
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        String string = SpeedMine.java_lang_String_zkmGetFlag();
        if (!clientPlayerEntity.hasStatusEffect(StatusEffects.SPEED) && INSTANCE.getSpeedPotion() && y < INSTANCE.Y()) {
            RegistryEntry<StatusEffect> registryEntry = StatusEffects.SPEED;
            Intrinsics.checkNotNullExpressionValue(registryEntry, "SPEED");
            INSTANCE.usePotionFor(registryEntry, l2);
        }
        if (!clientPlayerEntity.hasStatusEffect(StatusEffects.STRENGTH) && INSTANCE.getStrength() && y < INSTANCE.Y()) {
            RegistryEntry<StatusEffect> registryEntry = StatusEffects.STRENGTH;
            Intrinsics.checkNotNullExpressionValue(registryEntry, "STRENGTH");
            INSTANCE.usePotionFor(registryEntry, l2);
        }
        if (!clientPlayerEntity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE) && INSTANCE.getFire() && y < INSTANCE.Y()) {
            RegistryEntry<StatusEffect> registryEntry = StatusEffects.FIRE_RESISTANCE;
            Intrinsics.checkNotNullExpressionValue(registryEntry, "FIRE_RESISTANCE");
            INSTANCE.usePotionFor(registryEntry, l2);
        }
        if (clientPlayerEntity.getHealth() + clientPlayerEntity.getAbsorptionAmount() < (float)INSTANCE.getHealthH() && INSTANCE.getHeal() && y < INSTANCE.Y()) {
            RegistryEntry<StatusEffect> registryEntry = StatusEffects.INSTANT_HEALTH;
            Intrinsics.checkNotNullExpressionValue(registryEntry, "INSTANT_HEALTH");
            INSTANCE.usePotionFor(registryEntry, l2);
        }
        if ((!clientPlayerEntity.hasStatusEffect(StatusEffects.REGENERATION) && INSTANCE.getTriggerOn() == AutoBuffTriggerOnMode.LackOfRegen || clientPlayerEntity.getHealth() + clientPlayerEntity.getAbsorptionAmount() < (float)INSTANCE.getHealthR() && INSTANCE.getTriggerOn() == AutoBuffTriggerOnMode.Health) && INSTANCE.getRegen() && y < INSTANCE.Y()) {
            RegistryEntry<StatusEffect> registryEntry = StatusEffects.REGENERATION;
            Intrinsics.checkNotNullExpressionValue(registryEntry, "REGENERATION");
            INSTANCE.usePotionFor(registryEntry, l2);
        }
        buffTimer.reset();
    }

    private final void usePotionFor(RegistryEntry registryEntry, long l) {
        long l2 = (l = a ^ l) ^ 0x26774C9419A7L;
        int n = this.findPotion(registryEntry, l2);
        String string = SpeedMine.java_lang_String_zkmGetFlag();
        if (n == -1) {
            return;
        }
        Function0<Unit> function0 = AutoBuff::usePotion;
        SwapMode swapMode = this.getSwitchMode();
        int n2 = n;
        InventoryUtil.INSTANCE.swapAndRun(n2, swapMode, function0);
    }

    private final int findPotion(RegistryEntry registryEntry, long l) {
        long l2 = (l = a ^ l) ^ 0x5AC2515FF257L;
        return InventoryUtil.INSTANCE.findItem(l2, arg_0 -> AutoBuff.findPotion$lambda$0(registryEntry, arg_0), this.getSwitchMode()).getSlot();
    }

    private final boolean hasEffect(long l, RegistryEntry registryEntry) {
        long l2 = (l = a ^ l) ^ 0x430079603766L;
        return !Globals.getPlayer().hasStatusEffect(registryEntry) && this.findPotion(registryEntry, l2) != -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isPotionOf(ItemStack itemStack, RegistryEntry registryEntry) {
        StatusEffectInstance statusEffectInstance;
        StatusEffectInstance statusEffectInstance2;
        ItemStack itemStack2 = itemStack;
        if (!((itemStack2 != null ? itemStack2.getItem() : null) instanceof SplashPotionItem)) return false;
        Iterable<StatusEffectInstance> iterable = itemStack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).getEffects();
        Intrinsics.checkNotNullExpressionValue(iterable, "getAllEffects(...)");
        Iterable<StatusEffectInstance> iterable2 = iterable;
        if (iterable2 instanceof Collection && ((Collection)iterable2).isEmpty()) {
            return false;
        }
        Iterator<StatusEffectInstance> iterator2 = iterable2.iterator();
        do {
            if (!iterator2.hasNext()) return false;
        } while (!Intrinsics.areEqual((statusEffectInstance2 = (statusEffectInstance = iterator2.next())).getEffectType(), registryEntry));
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean canBuff(int n, int n2, char c) {
        long l;
        long l2 = l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)c << 48 >>> 48) ^ a;
        long l3 = l2 ^ 0x2F88EDB6E249L;
        long l4 = l3 >>> 16;
        int n3 = (int)(l3 << 48 >>> 48);
        long l5 = l2 ^ 0x2E32827A9271L;
        int n4 = (int)((l2 ^ 0x6551FC322566L) >>> 48);
        int n5 = 6 + (int)Math.floor((double)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l4, 1, (char)n3, null) / 50.0);
        if (!buffTimer.hasPassed((short)n4, n5)) return false;
        if (Globals.getPlayer().age <= 100) {
            return false;
        }
        if (Aura.INSTANCE.getEnabled() && Aura.INSTANCE.getTarget() != null && Globals.getPlayer().getAttackCooldownProgress(1.0f) > 0.5f) {
            return false;
        }
        if (Globals.getPlayer().isGliding()) {
            return false;
        }
        RegistryEntry<StatusEffect> registryEntry = StatusEffects.SPEED;
        Intrinsics.checkNotNullExpressionValue(registryEntry, "SPEED");
        if (this.hasEffect(l5, registryEntry)) {
            if (this.getSpeedPotion()) return true;
        }
        RegistryEntry<StatusEffect> registryEntry2 = StatusEffects.STRENGTH;
        Intrinsics.checkNotNullExpressionValue(registryEntry2, "STRENGTH");
        if (this.hasEffect(l5, registryEntry2)) {
            if (this.getStrength()) return true;
        }
        RegistryEntry<StatusEffect> registryEntry3 = StatusEffects.FIRE_RESISTANCE;
        Intrinsics.checkNotNullExpressionValue(registryEntry3, "FIRE_RESISTANCE");
        if (this.hasEffect(l5, registryEntry3)) {
            if (this.getFire()) return true;
        }
        if (Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount() < (float)this.getHealthH()) {
            RegistryEntry<StatusEffect> registryEntry4 = StatusEffects.INSTANT_HEALTH;
            Intrinsics.checkNotNullExpressionValue(registryEntry4, "INSTANT_HEALTH");
            if (this.hasEffect(l5, registryEntry4)) {
                if (this.getHeal()) return true;
            }
        }
        if (this.getTriggerOn() == AutoBuffTriggerOnMode.LackOfRegen) {
            RegistryEntry<StatusEffect> registryEntry5 = StatusEffects.REGENERATION;
            Intrinsics.checkNotNullExpressionValue(registryEntry5, "REGENERATION");
            if (this.hasEffect(l5, registryEntry5)) {
                if (this.getRegen()) return true;
            }
        }
        if (!(Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount() < (float)this.getHealthR())) return false;
        if (this.getTriggerOn() != AutoBuffTriggerOnMode.Health) return false;
        RegistryEntry<StatusEffect> registryEntry6 = StatusEffects.REGENERATION;
        Intrinsics.checkNotNullExpressionValue(registryEntry6, "REGENERATION");
        if (!this.hasEffect(l5, registryEntry6)) return false;
        if (!this.getRegen()) return false;
        return true;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getTriggerOn() == AutoBuffTriggerOnMode.Health;
    }

    private static final Unit onPlayerUpdate$lambda$0() {
        long l = a ^ 0x471D0D55BA13L;
        long l2 = l ^ 0x5A4BD79B82B4L;
        INSTANCE.checkEffects(l2);
        return Unit.INSTANCE;
    }

    private static final Unit usePotion() {
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        int n = y;
        y = n + 1;
        return Unit.INSTANCE;
    }

    private static final boolean findPotion$lambda$0(RegistryEntry registryEntry, ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return INSTANCE.isPotionOf(itemStack, registryEntry);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoBuff.a = ZkmKeys.a(8088570268154467807L, -3809793699463186813L, MethodHandles.lookup().lookupClass()).a(57019293668033L);
                        var20 = AutoBuff.a ^ 11051624758701L;
                        AutoBuff.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[43];
                        var16_4 = 0;
                        var15_5 = "vm\u00e9\u009d\u00f3\u00dd\u00d2]6\u001f\u00fc\u00d1\u00f73\u000f\u0091fu\u0000\u0095\u00ee\u00c2 \u00c7\u00c4\u0091\u0087\u00c0\u008de\u00a1e0+c\u00da\u00a4\u00805\u0000\u00d1\u0016\u00ce4-\u00f0\u00a8\u0084mdM57\u00d4Q\t\u00eb9(s\u00a3\u00a7\u001a\u00ad\u0017&\u00c5\u0003f\u00ee\u00a2z\u00ab\u00fe\u0010\u00c9\\\u00bfy@r \u00c6\u00bb\r\u0012X)\u00d4\u00c9\u00cf\u00e3\u009e\u00f1\u001b]\u00a8\u00c1\u00fd%y_f\u000e\u00bb\b\u00e7n\u00ff\u008d\u00e7<\u00e5\u00ca \u00c3\u0010\u00d6{\u0098o\u0098C\u001c\u001f6zY\u001d\u00b6|\u008b\u00bf\u00f9\u0016\u00e7\u00c3t\u00fac\u0015\u00ff|T\u00ac-O\u0018d\u009cm\u00e2\u00ca\u00a7\u00d4\u00a9\u001d\u00b64>\b\u00a7K\u00d3\u0000r\u00b9\u00b4k\u0097>=\u0018_&\u00dbL\u0084I}\u0097\u008c\u0080\u008ctt\u00e5\u0099OQJ(\u00b5\u00e05\u00d0\f\u00186\u00fe\u00f1\u0091B\u0087\u00b2\u00f4\u0098!K\u00f7Y\u0095\u00ee\u00fe\u0088\u0086\u00ef\u0083\u00a5\u00f3!1\u0010\u0085DZ\u00ed\u000e9\u00ff\u0005\u007f\u00b4\u001ej\u00fe\u00ec\u00b0g\u0010\u0002\u00c9!:\u0000\b\u00fa\u00fe?\u00f2\u00a3\u00a8A\u00e1\u00bdn\u0018\u00bb(\u00efL\u00a9Gn~\u0086\u00b9I\u0018\u0018X\u00aa\u00bf\u00fe\u00cd#.f\u000b+m\u0010\u00a2x\u00df\u008c\u00c1( 8\u00bf\u00fa\u00a3*1&\u00f2\f\u0010\u00be)\u00afV\u0090\u001ama.\u0081\u0084\u00d4\r\u000ed\u00b3\u0010\u00ee6\u00e3\u00cf\u009b\u00ef\u0083A\u00dei:\u0099&\u00c5\b>\u0018t\u00c9\u00a6sz[\u00a2\u00adC\u00d6m\u0090\u00df7^\u00c5\u008a\u00c5v\u00efF]\u0097\r \u00d8\u00ce\u00fe\u00eb\u0097\u00cd\u00baB\u00fb{Zk\u00966\u00bc\u001c\u0013\u00e28\u00da\u0096s\u001c6\u0019g\u0014$n\u00dce\u0010(\u00f2\u001ah\u008f\u00d9\u00cf\u00ea\u00e1R\r\u00ca\u00d7\u009a\u00e3v9\u00e4w\u00b5\u0096\u00aali\u00a7},%\u008e\u00fcl\u00a6NK{\u00ee\u00f2\u001e\u0085\u00a7\u00ae\u0018\u00e6[[f\u001c\u00dd\u008c\u00be\u00c8\u00f28\u007f\u008dB\u0084\u00d1cW\u00bb\u00c6\u00a3\u001d\u0001z(/\u0018\u00d2\u00d7.\u007f\u00be8>Z\u00ee\u00e2\u0007\u00a9\u00ed\u0087\u00b6\u008csv\u00a3\u00f85~\u00db\u001aa\u0095\u00d1\u0083\u00d7%S\u00ee\u00be\u0086\u00c3\u00cc\u0094\u00bd(\u00aa\u008d\u0006\u009c\u00f9M\u00c8$|&b\u00e2azt\u0012{$4\u0097{\u00d3\u009a\u00bc\u009e\u00e4\u00c2\u00e8\u00b4\u00ce]\u008e\u00d5\u008c\u0087\u00ce\u00ec#n\u00f0 !`p*-\u00d7]\u0006\u0003\u0085\u009d\u00be\u00a0]\u0095\u00d4\u00d3\u00df4\u008f\u009b\u0012\u00b3:'(k%\u00b6>\u00027\u0018\u0005\fy\u00c9\u00c9x\u00ee\u00ab-\u0081\u00a4\u00ac\u00fa)H\u00c7\u00c2\u00db\u001bS\u00ea\u0094\u0081b\u0010f\u00ae\u00cb\u00af\u00e1\u008f\u0007\u00a9\u0018-\u00e1\u00e6tl\u00b1V '\u00e9N&\u00ed\u00b08\u00dfD\u001d||M\u0092l\u00a6d\u00d8\u00de6\u00a6\u0000\u00dd.\u00ebp\u0087u\u008f:\u00fc\u00ca \u00ca\u0016\u00a0K\u00ba\u00cc\u0096\u000e\u00da\u00ff\u00d4E\u000e\u001c\u00cbb\u000e\u008e\u00d9L\u00c0\u0013f\u00cer\u001f,=\u00d3\u0005O9\u0010w\t3\u00fb\u0007k\u00b6\u00a9\u00b9\u0093\u00a2Q\u008d\u0006\u00f7k\u0018\u0086\u001bu`\u0004\u00b3\b\u00bb\u00f0~\u00e1\u00d7F\u00aa\u00fa\u008a\u00c8\u00c6gr3\u00fc@F\u0018Y\f.\u009aD]\u00ce\u0002\u00e6)\u00c2\u0012\u00b0\u00f3\u00a5\u00b9\u00fc\u00d66\u0000\u009b#_?\u0018\u00cb)\u00b6\u00cb\u0083\u00d0\u00e1k\u0082\u00d0Nv6\\B\u0019X\u00d3\u0015!\u00d0\u00bd;\u0085\u0010\u00c2)\u0083tL\u00ad\u009aM5\u00a8\u00f4\u00b4L\u00cblt\u0018\u0086\r\u00c7\u008a,+\u00de\u0089s+0}WZL\u00aeV+T,\u0001\u001dIC \u00d5a\u00f3\u0001\u0099\u0086\u000f\u009bR\u0097\u0086|\u001b\u0084\\\u00b2\u00e9\u00c7.\u0082\u00cd\u00bc*%\u00c7\u00a2\u00b4\u00bd1\u00d1\u0086\u000e(7\u007f\u00a0j\u00a7\u00c7y\u0086\u00d8\u00e4\u00da\u00b3\u00de\u00f7\u0093Gv!\u0081\u00d1\u00fd\u00fc\u00e5PSA\\\u00f0\u00bc\u00de\u00f7\u00ea\u00e6\u0013\u00c4\u0014\u008dw'\u00d9 \u0084\u00caP\u0098N\u00d1\u00b7H\u001a\u00b42x\u00cc\u00d2\u0097\u00d2\u00d4\u0016\u00cb\u008a\u00e8~P\u00d7\u00c3\u0090\u0096\u0001\u00eb4\u0013\u0007\u0010\u0018\u00e82.$K\u00ebj\u007f\u00bb#\u00f2\u00fal\u00ea\n0\u00a9.\u00af;-t\\U\u008e]\u00aeC=\u00e9\u008f\t\u0097\u00c0\u00ac\u0018~\u0010\u0012\u0006\u00ba\u00bbQ\u00dc\u00975\u00ed\u00e3\u00fd\u0006\u0088\u0005\u00c4R\u0087\u00ebe\u000e,\u009c(\r\r\u00c8 \u0003wH\u00b2\u007fe\u0017o\u00e3^\u00a5\u00caP\u00d4\u00f5\u0097J+r\u0019DT\u00aa\u00c2\u008b_\f\u009f\u0018\u0089\u00e6) j\u00af'\u00e5W\u008b\u00e6\u008e\u00ed\u0096\u00bb\"\u008d\u0098E\u00a1i{\u00da*\u00a6.\u00e6u%\u00d9c\u00cd\u00fe\u001d\u00ee\u00e5\u0010\u00fbf\u007f\u008a\rb\u00c1\u00f5:\u00a7u\u0003~\u00fdE\u0010 g\u00a3)\u0014w\u00d3\u0004\u00bc\u00b8`91\u00e1N \u00e6\u0090,m4\u00daW\u00e0\u00aeQ\u0005\u00a0.D\u00d7\u009f{ \u00e9M\u001a\u008d8\u00ddg\u009b\u0003Wg\u000b\u00f0\u00b6\u0098\u00cc\u00f9YN\u00f5\u00ef\u00e8\u00f3B\u00bc\u00fb\u00d9\u00c85\u0092QP\u0010Y\u00d8\u0018o|*\u00ac1\u00e7\u00a2Ql\u00a9\u00b0\u0088\u00c3";
                        var17_6 = 1152;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = AutoBuff.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c7\u0001\u008a\u00b8\u00faJ\u0082\u00eb\u00e7\u00e1\u00cd\u009cH\u000by\u00c6\u0010\u0019\u00e6\u00d0!\u0081d\u008a\u0093\u0084ch\u001aN\u009b\u0083\u00a3";
                            var17_6 = 33;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = AutoBuff.zkmDecodeUtf8(var19_9).intern();
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
                AutoBuff.b = var18_3;
                AutoBuff.d = new String[43];
                AutoBuff.i = new HashMap<K, V>(13);
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
                var6_12 = new long[17];
                var3_13 = 0;
                var4_14 = "R\u00e3\u0088\u00b60cp\u00e4\u00cc\u0084\u00fbN\u00fb\u00e6t\u0080\u00c4Z\u00b8>.+\u0083\u0085\u001e\u009c\u00be\u001eS\u0094\u00f3S\u001f\u00ed\u0018]\u00c6\"Z\u0001\u00ecvF\u00b6\u009c\u00aez\u0088\u00c3\u0093!x\u00a2E&\u00c8\u0080\u00d7\u00c0\u00d10\u00cc~\u00fe&\u00e5\u00be\u00cb\u00de\u00a2\u009c\u0096\u001b\u00f0F\u00c4\u00f1\u0092\fg@5~nC\u00a6f\u00c1\u0017@\u00bd\u00dd\u001a\u009e\u00ec\u00a4)?\u0002\u0086\u00f0im\u0004t\u000f4\u0096\u00d9\u00b7\u0011\u00c6C\u008dw\u008e\u0004\u00b4\u00d9/";
                var5_15 = 120;
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
                    var4_14 = "o\u00b7\u00c8_cW\u0080\u00bb\u00c0\b<C \u008e\u00d8\u0011";
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
        AutoBuff.g = var6_12;
        AutoBuff.h = new Integer[17];
        var33_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "strength", "getStrength()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "speedPotion", "getSpeedPotion()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "fire", "getFire()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "heal", "getHeal()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "healthH", "getHealthH()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "regen", "getRegen()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "triggerOn", "getTriggerOn()Lsu/catlean/p;", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "healthR", "getHealthR()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoBuff.class, "potionsPerTick", "getPotionsPerTick()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoBuff.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0))};
        AutoBuff.delegatedProperties = var33_20;
        AutoBuff.INSTANCE = new AutoBuff();
        AutoBuff.strength$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBuff.INSTANCE, "strength", true, null, null, 12, null);
        AutoBuff.speedPotion$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBuff.INSTANCE, "speed", true, null, null, 12, null);
        AutoBuff.fire$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBuff.INSTANCE, "fire-resistance", true, null, null, 12, null);
        AutoBuff.heal$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBuff.INSTANCE, "instant-healing", true, null, null, 12, null);
        AutoBuff.healthH$delegate = SettingFactory.intSetting$default(AutoBuff.INSTANCE, "health", 8, new IntRange(0, 20), null, null, 24, null);
        AutoBuff.regen$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBuff.INSTANCE, "regeneration", true, null, null, 12, null);
        AutoBuff.triggerOn$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoBuff.INSTANCE, "trigger", (Enum)AutoBuffTriggerOnMode.LackOfRegen, null, null, 12, null);
        AutoBuff.healthR$delegate = SettingFactory.intSetting$default(AutoBuff.INSTANCE, "hp", 8, new IntRange(0, 20), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoBuff.potionsPerTick = SettingFactory.intSetting$default(AutoBuff.INSTANCE, "potions-per-tick", 3, new IntRange(1, 3), null, null, 24, null);
        AutoBuff.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoBuff.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
        AutoBuff.buffTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x468;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/uv", exception);
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
            AutoBuff.d[n2] = AutoBuff.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoBuff.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/uv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7349;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/uv", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoBuff.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoBuff.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/uv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoBuff.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoBuff.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

