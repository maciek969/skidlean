/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.ElderGuardianParticle;
import net.minecraft.client.particle.ExplosionLargeParticle;
import net.minecraft.client.particle.ExplosionSmokeParticle;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.client.render.entity.state.ArmorStandEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.GetFogBufferEvent;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.render.BossBarRenderEvent;
import su.catlean.api.event.events.render.DarknessEffectEvent;
import su.catlean.api.event.events.render.EntityAlphaEvent;
import su.catlean.api.event.events.render.HurtTiltEvent;
import su.catlean.api.event.events.render.ItemNameRenderEvent;
import su.catlean.api.event.events.render.MapRenderEvent;
import su.catlean.api.event.events.render.NauseaRenderEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.RenderArmorEvent;
import su.catlean.api.event.events.render.RenderFireOnEntityEvent;
import su.catlean.api.event.events.render.RenderFireOverlayEvent;
import su.catlean.api.event.events.render.RenderGuiBackgroundEvent;
import su.catlean.api.event.events.render.RenderInWallOverlayEvent;
import su.catlean.api.event.events.render.RenderNameTagEvent;
import su.catlean.api.event.events.render.RenderParticleEvent;
import su.catlean.api.event.events.render.RenderPortalOverlayEvent;
import su.catlean.api.event.events.render.RenderSpawnerEntityEvent;
import su.catlean.api.event.events.render.RenderUnderwaterOverlayEvent;
import su.catlean.api.event.events.render.RenderVignetteOverlayEvent;
import su.catlean.api.event.events.render.ScoreBoardRenderEvent;
import su.catlean.api.event.events.render.ShouldRenderEntityEvent;
import su.catlean.api.event.events.render.SignTextRenderEvent;
import su.catlean.api.event.events.render.StatusEffectsRenderEvent;
import su.catlean.api.event.events.render.WeatherRenderEvent;
import su.catlean.api.event.events.world.GetFogModifierEvent;
import su.catlean.config.ProfileConfigFile;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.render.RenderState;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class NoRender
extends Module {
    @NotNull
    public static final NoRender INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final SettingGroup screen$delegate;
    @NotNull
    private static final BooleanSetting hurtCam$delegate;
    @NotNull
    private static final BooleanSetting potions$delegate;
    @NotNull
    private static final BooleanSetting vignette$delegate;
    @NotNull
    private static final BooleanSetting portal$delegate;
    @NotNull
    private static final BooleanSetting bossbar$delegate;
    @NotNull
    private static final BooleanSetting fireOverlay$delegate;
    @NotNull
    private static final BooleanSetting waterOverlay$delegate;
    @NotNull
    private static final BooleanSetting blockOverlay$delegate;
    @NotNull
    private static final BooleanSetting nausea$delegate;
    @NotNull
    private static final BooleanSetting blindness$delegate;
    @NotNull
    private static final BooleanSetting guiBackGround$delegate;
    @NotNull
    private static final BooleanSetting noScoreBoard$delegate;
    @NotNull
    private static final BooleanSetting hotbarItemName$delegate;
    @NotNull
    private static final BooleanSetting antiTitle$delegate;
    @NotNull
    private static final SettingGroup entities$delegate;
    @NotNull
    private static final BooleanSetting xp$delegate;
    @NotNull
    private static final BooleanSetting arrows$delegate;
    @NotNull
    private static final BooleanSetting eggs$delegate;
    @NotNull
    private static final BooleanSetting elderGuardian$delegate;
    @NotNull
    private static final BooleanSetting armor$delegate;
    @NotNull
    private static final BooleanSetting items$delegate;
    @NotNull
    private static final BooleanSetting crystals$delegate;
    @NotNull
    private static final BooleanSetting fireworks$delegate;
    @NotNull
    private static final BooleanSetting noArmorStands$delegate;
    @NotNull
    private static final SettingGroup particles$delegate;
    @NotNull
    private static final BooleanSetting explosions$delegate;
    @NotNull
    private static final BooleanSetting campFire$delegate;
    @NotNull
    private static final BooleanSetting breakParticles$delegate;
    @NotNull
    private static final BooleanSetting noWeather$delegate;
    @NotNull
    private static final SettingGroup world$delegate;
    @NotNull
    private static final BooleanSetting maps$delegate;
    @NotNull
    private static final BooleanSetting spawnerEntity$delegate;
    @NotNull
    private static final BooleanSetting fireEntity$delegate;
    @NotNull
    private static final BooleanSetting signText$delegate;
    @NotNull
    private static final BooleanSetting darkness$delegate;
    @NotNull
    private static final BooleanSetting fog$delegate;
    @NotNull
    private static final SettingGroup other$delegate;
    @NotNull
    private static final BooleanSetting antiPlayerCollision$delegate;
    private static final long U;
    private static final String[] ab;
    private static final String[] fb;
    private static final Map gb;
    private static final long[] hb;
    private static final Integer[] lb;
    private static final Map mb;

    private NoRender() {
        super("no-render", Categories.getWorld(), null, null);
    }

    private final SettingParent getScreen() {
        return (SettingParent)screen$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getHurtCam() {
        return (Boolean)hurtCam$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getPotions() {
        return (Boolean)potions$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getVignette() {
        return (Boolean)vignette$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getPortal() {
        return (Boolean)portal$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getBossbar() {
        return (Boolean)bossbar$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getFireOverlay() {
        return (Boolean)fireOverlay$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getWaterOverlay() {
        return (Boolean)waterOverlay$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getBlockOverlay() {
        return (Boolean)blockOverlay$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getNausea() {
        return (Boolean)nausea$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getBlindness() {
        return (Boolean)blindness$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getGuiBackGround() {
        return (Boolean)guiBackGround$delegate.getValue(this, delegatedProperties[11]);
    }

    private final boolean getNoScoreBoard() {
        return (Boolean)noScoreBoard$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getHotbarItemName() {
        return (Boolean)hotbarItemName$delegate.getValue(this, delegatedProperties[13]);
    }

    private final boolean getAntiTitle() {
        return (Boolean)antiTitle$delegate.getValue(this, delegatedProperties[14]);
    }

    private final SettingParent getEntities() {
        return (SettingParent)entities$delegate.getValue(this, delegatedProperties[15]);
    }

    private final boolean getXp() {
        return (Boolean)xp$delegate.getValue(this, delegatedProperties[16]);
    }

    private final boolean getArrows() {
        return (Boolean)arrows$delegate.getValue(this, delegatedProperties[17]);
    }

    private final boolean getEggs() {
        return (Boolean)eggs$delegate.getValue(this, delegatedProperties[18]);
    }

    private final boolean getElderGuardian() {
        return (Boolean)elderGuardian$delegate.getValue(this, delegatedProperties[19]);
    }

    private final boolean getArmor() {
        return (Boolean)armor$delegate.getValue(this, delegatedProperties[20]);
    }

    private final boolean getItems() {
        return (Boolean)items$delegate.getValue(this, delegatedProperties[21]);
    }

    private final boolean getCrystals() {
        return (Boolean)crystals$delegate.getValue(this, delegatedProperties[22]);
    }

    private final boolean getFireworks() {
        return (Boolean)fireworks$delegate.getValue(this, delegatedProperties[23]);
    }

    private final boolean getNoArmorStands() {
        return (Boolean)noArmorStands$delegate.getValue(this, delegatedProperties[24]);
    }

    private final SettingParent getParticles() {
        return (SettingParent)particles$delegate.getValue(this, delegatedProperties[25]);
    }

    private final boolean getExplosions() {
        return (Boolean)explosions$delegate.getValue(this, delegatedProperties[26]);
    }

    private final boolean getCampFire() {
        return (Boolean)campFire$delegate.getValue(this, delegatedProperties[27]);
    }

    private final boolean getBreakParticles() {
        return (Boolean)breakParticles$delegate.getValue(this, delegatedProperties[28]);
    }

    private final boolean getNoWeather() {
        return (Boolean)noWeather$delegate.getValue(this, delegatedProperties[29]);
    }

    private final SettingParent getWorld() {
        return (SettingParent)world$delegate.getValue(this, delegatedProperties[30]);
    }

    private final boolean getMaps() {
        return (Boolean)maps$delegate.getValue(this, delegatedProperties[31]);
    }

    private final boolean getSpawnerEntity() {
        return (Boolean)spawnerEntity$delegate.getValue(this, delegatedProperties[32]);
    }

    private final boolean getFireEntity() {
        return (Boolean)fireEntity$delegate.getValue(this, delegatedProperties[33]);
    }

    private final boolean getSignText() {
        return (Boolean)signText$delegate.getValue(this, delegatedProperties[34]);
    }

    private final boolean getDarkness() {
        return (Boolean)darkness$delegate.getValue(this, delegatedProperties[35]);
    }

    private final boolean getFog() {
        return (Boolean)fog$delegate.getValue(this, delegatedProperties[36]);
    }

    private final SettingParent getOther() {
        return (SettingParent)other$delegate.getValue(this, delegatedProperties[37]);
    }

    private final boolean getAntiPlayerCollision() {
        return (Boolean)antiPlayerCollision$delegate.getValue(this, delegatedProperties[38]);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block0: {
            if (!(receivePacket.getPacket() instanceof TitleS2CPacket) || !this.getAntiTitle()) break block0;
            receivePacket.cancel();
        }
    }

    @Flow
    private final void onSignTextRender(SignTextRenderEvent signTextRenderEvent) {
        block3: {
            block2: {
                if (!this.getSignText()) break block2;
                if (Globals.getMc().crosshairTarget == null) break block3;
                HitResult hitResult = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(hitResult);
                if (hitResult.getType() != HitResult.Type.BLOCK || !(Globals.getMc().crosshairTarget instanceof BlockHitResult)) break block3;
                HitResult hitResult2 = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(hitResult2);
                if (!Intrinsics.areEqual(hitResult2.getPos(), signTextRenderEvent.getPos())) break block3;
            }
            return;
        }
        signTextRenderEvent.cancel();
    }

    @Flow
    private final void onRenderArmor(RenderArmorEvent renderArmorEvent) {
        block0: {
            if (!this.getArmor()) break block0;
            renderArmorEvent.cancel();
        }
    }

    @Flow
    private final void onMapRender(MapRenderEvent mapRenderEvent) {
        block0: {
            if (!this.getMaps()) break block0;
            mapRenderEvent.cancel();
        }
    }

    @Flow
    private final void onRenderFireOverlay(RenderFireOverlayEvent renderFireOverlayEvent) {
        block0: {
            if (!this.getFireOverlay()) break block0;
            renderFireOverlayEvent.cancel();
        }
    }

    @Flow
    private final void onWeatherRender(WeatherRenderEvent weatherRenderEvent) {
        block0: {
            if (!this.getNoWeather()) break block0;
            weatherRenderEvent.cancel();
        }
    }

    @Flow
    private final void onRenderUnderwaterOverlay(RenderUnderwaterOverlayEvent renderUnderwaterOverlayEvent) {
        block0: {
            if (!this.getWaterOverlay()) break block0;
            renderUnderwaterOverlayEvent.cancel();
        }
    }

    @Flow
    private final void onRenderGuiBackground(RenderGuiBackgroundEvent renderGuiBackgroundEvent) {
        block0: {
            if (!this.getGuiBackGround()) break block0;
            renderGuiBackgroundEvent.cancel();
        }
    }

    @Flow
    private final void onRenderInWallOverlay(RenderInWallOverlayEvent renderInWallOverlayEvent) {
        block0: {
            if (!this.getBlockOverlay()) break block0;
            renderInWallOverlayEvent.cancel();
        }
    }

    @Flow
    private final void onDarknessEffect(DarknessEffectEvent darknessEffectEvent) {
        block0: {
            if (!this.getDarkness()) break block0;
            darknessEffectEvent.cancel();
        }
    }

    @Flow
    private final void onRenderSpawnerEntity(RenderSpawnerEntityEvent renderSpawnerEntityEvent) {
        block0: {
            if (!this.getSpawnerEntity()) break block0;
            renderSpawnerEntityEvent.cancel();
        }
    }

    @Flow
    private final void onNauseaRender(NauseaRenderEvent nauseaRenderEvent) {
        block0: {
            if (!this.getNausea()) break block0;
            nauseaRenderEvent.cancel();
        }
    }

    @Flow
    private final void onStatusEffectsRender(StatusEffectsRenderEvent statusEffectsRenderEvent) {
        block0: {
            if (!this.getPotions()) break block0;
            statusEffectsRenderEvent.cancel();
        }
    }

    @Flow
    private final void onRenderPortalOverlay(RenderPortalOverlayEvent renderPortalOverlayEvent) {
        block0: {
            if (!this.getPortal()) break block0;
            renderPortalOverlayEvent.cancel();
        }
    }

    @Flow
    private final void onRenderVignetteOverlay(RenderVignetteOverlayEvent renderVignetteOverlayEvent) {
        block0: {
            if (!this.getVignette()) break block0;
            renderVignetteOverlayEvent.cancel();
        }
    }

    @Flow
    private final void onScoreBoardRender(ScoreBoardRenderEvent scoreBoardRenderEvent) {
        block0: {
            if (!this.getNoScoreBoard()) break block0;
            scoreBoardRenderEvent.cancel();
        }
    }

    @Flow
    private final void onItemNameRender(ItemNameRenderEvent itemNameRenderEvent) {
        block0: {
            if (!this.getHotbarItemName()) break block0;
            itemNameRenderEvent.cancel();
        }
    }

    @Flow
    private final void onRenderFireOnEntity(RenderFireOnEntityEvent renderFireOnEntityEvent) {
        block0: {
            if (!this.getFireEntity()) break block0;
            renderFireOnEntityEvent.cancel();
        }
    }

    @Flow
    private final void onBossBarRender(BossBarRenderEvent bossBarRenderEvent) {
        block0: {
            if (!this.getBossbar()) break block0;
            bossBarRenderEvent.cancel();
        }
    }

    @Flow
    private final void onHurtTilt(HurtTiltEvent hurtTiltEvent) {
        block0: {
            if (!this.getHurtCam() || !((float)Globals.getPlayer().hurtTime - RenderState.INSTANCE.getTickDelta() > 0.0f)) break block0;
            hurtTiltEvent.cancel();
        }
    }

    @Flow
    private final void onEntityAlpha(EntityAlphaEvent entityAlphaEvent) {
        if (this.getEnabled() && this.getAntiPlayerCollision()) {
            Entity entity = Globals.getPlayer();
            if (!Intrinsics.areEqual(entityAlphaEvent.getPos(), this.getPos(entity))) {
                entityAlphaEvent.setAlpha(Math.clamp((float)(Globals.getPlayer().squaredDistanceTo(entityAlphaEvent.getPos()) / 3.0) + 0.2f, 0.0f, 1.0f));
                entityAlphaEvent.cancel();
            }
        }
    }

    @Flow
    private final void onRenderParticle(RenderParticleEvent renderParticleEvent) {
        if (this.getElderGuardian() && renderParticleEvent.getParticle() instanceof ElderGuardianParticle) {
            renderParticleEvent.cancel();
        } else if (this.getExplosions() && (renderParticleEvent.getParticle() instanceof ExplosionLargeParticle || renderParticleEvent.getParticle() instanceof ExplosionSmokeParticle)) {
            renderParticleEvent.cancel();
        } else if (this.getCampFire() && renderParticleEvent.getParticle() instanceof CampfireSmokeParticle) {
            renderParticleEvent.cancel();
        } else if (this.getBreakParticles() && renderParticleEvent.getParticle() instanceof BlockDustParticle) {
            renderParticleEvent.cancel();
        } else if (this.getFireworks() && (renderParticleEvent.getParticle() instanceof FireworksSparkParticle.FireworkParticle || renderParticleEvent.getParticle() instanceof FireworksSparkParticle.Flash)) {
            renderParticleEvent.cancel();
        }
    }

    @Flow
    private final void onShouldRenderEntity(ShouldRenderEntityEvent shouldRenderEntityEvent) {
        block0: {
            if (!this.shouldHide(shouldRenderEntityEvent.getEntity())) break block0;
            shouldRenderEntityEvent.cancel();
        }
    }

    @Flow
    private final void onRenderNameTag(RenderNameTagEvent renderNameTagEvent) {
        block0: {
            if (!(renderNameTagEvent.getState() instanceof ArmorStandEntityRenderState) || !this.getNoArmorStands()) break block0;
            renderNameTagEvent.cancel();
        }
    }

    @Flow
    private final void onGetFogBuffer(GetFogBufferEvent getFogBufferEvent) {
        block0: {
            if (!this.getFog()) break block0;
            getFogBufferEvent.cancel();
        }
    }

    @Flow
    private final void onGetFogModifier(GetFogModifierEvent getFogModifierEvent) {
        block0: {
            if (!this.getBlindness()) break block0;
            getFogModifierEvent.cancel();
        }
    }

    @Flow
    public final void onRender3D(@NotNull Render3DEvent render3DEvent) {
        String string = ProfileConfigFile.java_lang_String_zkmGetFlag();
        Intrinsics.checkNotNullParameter(render3DEvent, "event");
        String string2 = string;
        GofraState.INSTANCE.setShouldRender(this.isActive());
    }

    private final boolean shouldHide(Object object) {
        if (object instanceof EndCrystalEntity && this.getCrystals()) {
            return true;
        }
        if (object instanceof ArmorStandEntity && this.getNoArmorStands()) {
            return true;
        }
        if (object instanceof ItemEntity && this.getItems()) {
            return true;
        }
        if (object instanceof EggEntity && this.getEggs()) {
            return true;
        }
        if ((object instanceof ExperienceBottleEntity || object instanceof ExperienceOrbEntity) && this.getXp()) {
            return true;
        }
        if (object instanceof ArrowEntity && this.getArrows()) {
            return true;
        }
        return object instanceof SpectralArrowEntity && this.getArrows();
    }

    private final boolean isActive() {
        return this.getEnabled() && (this.getCrystals() || this.getNoArmorStands() || this.getItems() || this.getEggs() || this.getXp() || this.getArrows());
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        NoRender.U = ZkmKeys.a(-1690655905074968031L, -6137860780657220039L, MethodHandles.lookup().lookupClass()).a(201336193738277L);
                        var20 = NoRender.U ^ 71114322204616L;
                        NoRender.gb = new HashMap<K, V>(13);
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
                        var18_3 = new String[119];
                        var16_4 = 0;
                        var15_5 = "\u00c3\u00bf\u00df\u009b=vJ\u008f0d\u008a\u00f5\u0089rM\u00e8\u00b6P\u00976l\u00f1\u00f8m(\u00a4 \u008e\u00e26\u00b9\u00ea\t\u009ea\u00f4\u00e2Z\u00a9\u00ecCga\t1\u0010\n\u00e2\u0080\u00f1Ni\u00a1#\u0087\u0082\u0013\u0090m!:r[Zt\u0018\u009b\u008b\rvF\u00a2\u00d2@\u0014\u00c5\f\u00b8UE5,\u0095\u001d\u008dq\u00b7O\u000b\u00a3 \u00ae\u00b3ob\u00d1o7\u0096\u00ba\u00ea-\u0090\u00df\u0097\u00c2\u0012\u00c4\u00a2\u0004\u00a8\u00b2A\u00f3\u0096\u00ba\u00dd\u00d2+g\t\u0006G \u00e6\u00d5x48\u0017T\u0007\u00e7\u009dX\u00c1\u00f5\u0004-\u000f\u00f6\u00dfD\u00b8b\u0088\u00ffl\u00ef\\l\u00e7\u0091\u00fe\u00015 -\u00c1z\n\u00c5]\u00aa\u007f\u00f4\u000b\u00b2\u00b4\u0083\u00c3T\u00e3\u00f6\u00f8\u00ad\u00d4mq7z_\u00fc\u000b\u00a7#|a\u00e2\u0010\u0007C^\u0081,\u00e0\u00c2\u00c2\u0018Zdw\u00a4\u0002\u00b8\u00fb\u0010\u00ca\u0084\u00b7\u00f6\n\u0092\u00eb\u0095\u00a0\u00b4\u00c5T\u00e5f\u00d9\u00a1 :\u0001}\u00cd\u00c9fuT\u00eb\u00ceu\u00f8\u0094\u00cb\u00b8\u00c7\u000f\u00c9\u0017P'\u00af\u00c2\u001e\u00c8\u00f4\n\u009eh}-\u00e4\u0010\b\u00b1\u008c\u00b6\u00bdf\u00c6n\u0093Ve\u00f2\u00a1\u0090\u00985 i\u0003*\u00fd]\u000e\"V]\u0016J%*\u00ba/F\u00f92>\u001a\u001a\u00dbI\u00a8\u00c1\u00ce.\u0092\u0091\u00a8(\f\u0018*b\u001e\u00c2w\u001a\u00ab0\u0087a\u00963,^\u00ba\u00c6H\u00c7\r\u00da\u00c3\u0017\u00cc\u00c9 \u001a\u00fe\b~\u008f\u00cei\u00a5\u0096\u0082\u00e9K\u00e2y\u00fb\u0086\u00e6]\u00c4C\u00e45\u0089\u00b6@V\u00b3{\u00de&\u00efZ T\u00f4\u00acj\u00dd\u00f3\u00ac\u00e1;%^\u00b2\u00d2\u00b8/v\u00dasz\u0095^\u00a4\u00dc\u00da\u0011\u00e5\u0003\u00a6\u0098\u00e0\u001d5 \u0081Zm\u009af+K\u00f9]\u00186\u001b\u009dy\u00c7\u00a9\u00e2_\u0018\u0003\u00dc\u001d\u00b3'\u000f\u009f#'U?\u00cc\u00da b\u00a3\u00a7\u0010\u0016\u00cf\u000b\u00c3\u00e8%\u001b\u0003s\u00e3~\u0090\u0094?\u00f4\u0095\u00c0\u00e6,\u00fc\f\u00b9\u00fd\u008e\u00ba\u00f18\u00cb(\u00e7\u00d1\u00d7\u00fd\u00fb\u00ae\u0081\u009d;Do\u00a6Z\u000e\u00d3\tUf}\u00df\u00e5\u00afiQ\u000fz\u00dc\u00cb\u00c4\u00bb\u0090\u009f\u0005\u00fc\u00f4[\u0080\u000fz\u00c8\u0018\u00cf0\u0013]\u00ec\u0099\u00d0r+Ym\u0095\u0003/\u00cf\u008b\u00a9X#\u00d0\u0010\u0086\u001e\u00bf(\u0083\u00d9\u0016\u00cd\u00fe\u00f5@Cl\u00b9\u0081$\u0099\u00ea\u00e2\t\u009d\u00b5\u00fcp\u00f0\u009e\u00af\u00188\u00ceiK\u0007\u00fa1\u00fb\u008c\u00e0\u00e9\u00f6\u0084ko\u00b3 \u0016U\u00ceK-{472\u00d1\u00d8\u00127\u0091\b\u0010a\u00e5\u0099\u00e0\u00fd\u008a\u00a6D\u00bf\u00e5\u00b1\u00bb\u00cel\u0010\u00bf(\u00bf\u00fa \u001a:\u008cS\t\u0010\u00dd\u008f\u00e4\u00d9\u008a\u00ec\u00b3|\u008f\u00cait\u0001\u00f6\u0006\u001e\u00b9\u00cd\u00d4\u00c6:F\u00f7\u00a2\u0092\u00ba\u00fb\t\u00ce\u00aa\u00ce\u0010h\u0080\u00f2\u00ed\u00efC\u008d\u00bdp\u00f6\"\u008bZ\u0097\u00f4\u00158\u00bct\\*\u00cb9\u00ab\u00f4\u001e\u00b2\u00f3\u00b0\u00a1\u00b7\u0011\u00b3\u0002\u008d\\\u00d9\\\u00bb\\Nm\u0094F\u00e8\u00e9\u00f0\u00af\u00dd\u00a0\u0005\u00b92\u00c8\u00b8\u0017\u00fc\u0082m\u00b1s}\u00f5nM\u00f5\u00c5lx]\u0003\u000f\u0090(\u00f6:\u00e5\u00ef\u00ca\u0002c;\u00a1\u001dt\u009f\u00e6T\u00f6\u00be\u0018\u0000\u00ab\u00c4\u00e1{\u001ec>}v\u00c5\u0015\u00cd\u0002\u00b5\u00adeK\u009e\u0093\u0085ht(\u0094\u00b7\u0010\u0097\u00f7\u0014?\u0016\u00b0^\u0011\u000e\u00fc\u00c5\u00ef\u0018\u00c9\u00d3\u00ec\u00e6\u0082b\u0080\u008d\u00f8\u00af\u00a7\u00fd\"\u0014\u009a\u00a3\u00ac\u009dJ\u0015\u00c4dW\u00c1 \u00e87>q<X\u00f8\u0098\u00cd\u00beV\u00c2\u00b9\u00c5\u00b0J\u00865g&\u00c2\u001a\u00dd\u00de\u0006\u0001}\u0083t}\u00db\u00ca\u0018\n\u00a8j\u000bif\u00d5p\u00df\u00d6\u00eb\u00ab\u0015\u00ed\u0084\u00fblw/\u00113r>>\u0010\"f\u008f\u001f)\u00dfp7\u0017\u00c1A\u00f9\u00ec\u00b7v\u00d6 \u008c_s\u00de%T\u009c+\u00b3\"\u000bS\u00ceV}\u00b0\u00b0\u00aa\u009d\u00b4\u0085&\u0019u\u00a2t\u0085\\mQ7\r B\u00de,\u00c0\u00d6\u00cay\u00d9\u0010\u00e5\u00d1\u00cbC\u00f3\u00a2F\bZ\u00e2\u00f7\u000f\u00db\u00d0\u008d\u00f7fr\u009f\"\u00ab\u00a0\u00be \u00a0\u00c4\u00fa\u008b\u0006e\u00e4\u00c5\u0086l\u0006\u00e1%\u00cd\u0095;\u00c6\u0011\u0018\u007fv\u00c3\u009e\u00ed\u001d\u00f6\u0084\u00e6 \u00ee\u00e4\u00fe(\u00bfC\u00ab\u009c\u00c1\u00a9L>\u00be\u008c\u00d7\u00eb\u00dc\u000e\u0001\u00d0\u00f0\u00ad\u00fcN\u00cc\u009d\u0087+Eb\u0017\u001d\u0082\u00aaf\u00ac\u00dd|\u0089\u0087w\u00ab\u00ac\u0085\u0018\u00ca~\u00a7\u00b2\u00caJ\u0007t\u00eb\u00f4\u001c~\u00ac\u00bf\n\u0002\u00b83)\u00eb\u00faao\u00ca\u00107F\u00a6]q\u00c9\u0005\u00c21\u00e7%\u0088\u0084>-\u00b9 \u00c1\u0007X\u0099\u00ec\u00f6\u00a5\u00a9\u00e6\u00cf:*B\u00a5\u00b0B\u00cd\u0016|\u00f52\u00d2\u00fd\u00bd(;6v\u00a9_WP\u0010\u0017A ^\u00e0Z\u00ec0\u0094]\u00d2\u00b5\\\u00c1\u00fb\u00d9\u0010})y}}\u008d\u00bc\u00cfx\u00e2\n\u00ce\u001f\u00cee\u00d5 D\u0086\u0081Gjxp|\u00c3\u0098_E\u00d8\u00dbi\u0018]\u00a11?1us\u001e\u0095\u00bd\u009c\u0092!G\u00ac\r\u0018~\u00d3K\u001arKA\r\u0011\u00d1\u00b4'\u00ad6[X}\u0002\u00e2WG9\u0081)\u0010\u007f\u00bf^\u00d0\u0093\u0014\u0089m\u008d\u001b.\u00dco\u00cb\u000f\u0006\u0018L;+\u00bb\u001a5\u00ca8=\u00f6\u00beC\u00e3\u00cc\u0004y\u000fy\u00f8\u00f9\u00b4\f2\b \u00fe+\u0098\u00cf\u0081\u00b2\u0004\u00c6W\u00c8&I\u00b7\u00e7r\u00c8PH\u000bZr9_GPu\u00ed=[^\u00bd\u00f9\u0010\u00aau\u00d3\u001d\u00aa\f6\u00a5\u0013\u00cd\u0006!@\u00b8\u0005\u00db\u0018\u00ec\u009b\u00c1\u00e6\u00ef\u00a9\u00f6\u00f5s\u001ev8t\u0091\u00c2Q\u0090|\u00b1c\u0002\u00e2\u00e4$\u0010\u0081\u00ee^W\u0092\b\\\u0014\u0006P\u00b2\u00e4\u00cd\u001d\u0001O\u0018\u00d4w\u00c6S\u00b2\u0019\u00cf\u00fb\u00c7\u00da}M\u00e4\u0080\u00ec\u0005\u00bfE\u0088\u00c1\u0010\u00c3\u0083\u0012\u0018\u00a3\u0087\u00c5\u00e0\u0091\u00da\u0003\u0002\u00fa\u007fT~\u00e1/\u0083\u00e2\u00b6\u00b6\u0096\u008d\u00f9\u00e1\u00e8\u00c2\u0018\u008f{\u00cd\u00da\u00d6\u00c7\u00da\u00b8\u00b0\u00b6K\u00cb\tM\u00f0;\u008b>c\u00a1>p\u00b0J \rAJ`\u00f1\u00b2\u0012\u0083*C\u00f6W\u00ec\u008a\u009a\u00e0?\u00b81\u0097\u00a5\u0093\u00bcT\u009e\u00a3\u00e6(\u00edi\u00e6\u00b40#\u0010*\u0003\u0097Z\u0094\u0087\u00fe\u0099\u009d\u0098\u0000H/\u0096\u00f2\u0091\r\u00f9\u00ca\u0010Y\u00b1\u00ec\u00ffg\u0007\u00d4\u0018\u00bf\u0093q\u0096\u001d\u001bX\u0091\u007f\u00e6\u0013Ew\u00dc\u0007r\u00f8_ \u001fz\u00df\u001a\u00d7\u0082y\u00b5\u00f6c\u00bf\b\u00f1\u00d88\u001d\u00b8`\u0002,\u00db\u00e9+\u00a3\u0099\u00904\u00be\u00dek\u008b\u0087 \u00d9\u00b1\u001fk\r\u001e\u008d:\u0017\u00aa\u00b8\u00fdE9G\u00fd\u00fc\u00d9\u00d6fMW\u001a}\u00de\u0011\u00e2\u001d \u00b1\u00f5\u009a \u00b7%I\u00ab\u001b\u00ca/`6|\u00ec/ZEp\u0002\u00e97\u0086\u000b\u00bbS\u009b\u00a0V\u00f3\u00e7\u00ed\u00c4\u008akG(\u00bf,]\u0086\u00d6@\u007f\u00c2T\u00be'F&\"w\u00cc%O\u0082\u00a0\u00bf\u000f1[.\u0092Mj\u00e2\u0018\u00ee\n6b\u00e5\u0080BT1\u00f7 \u00e8\u00d4\u008f?\u001b\u00a9\b%\u0002<Tla\u00c4{m\u00fc#\u00bft3\u00b7\u0014\u0003\u0000w\u00bb2\t\u00f66 \u0018\u00e3,\u0011QL\u009dn\u00a2P\u00aa\u00a9\u0017m\u0007\u00c1+Sp\u00a6nT\u00ad8\u00f1 p\u008cI\u00bcR9\u001a_\u00eb=y\u00ddy\u008d\u0016\u00a7\u00fa\u00e6\u00adC\u0013i\u00a8\u0000\u0098\u0004#z\u0094G\u001e[ \u0012l\u00d1\u0088\u00c8\u00df\u00c9\u00ea*\u00e2\u00d0\u001f\u0088}p\u00d0N\u00b9\u008c\u00d7\u00ea?\u008eY\u0086sz~=\"t\u001e(\u001aN0\u0013\u00c91\u00aaf\u00fe\u00ab\u0085\u00e4 \u00fa\u00a4V\u009b\u00cf\u0012\u0089'\u00b8+\u00a84\u0092\u0018{\u0000c\u000b\u0002\u00f7\u00bc\u00d04\b>U$ Q\u00e6\u0010\u008f\u0098P\u00e3k{\u00cfx\u001fS;D\u00c9\u008a:T\u00fe9fN\u00ec%\u0094@\u00e6,\u0092_2\u0010\u00cc\u00fa\u00ba7\u0098vO\u00a5\u0002O\u00dd(\u00bd(\u00da\u00b7(\u0001\u00daRn\u00e7\u0084\u00e6\u00e1\u007f\u00ca>\u00e1\u0007xL\u00e0\u0089\u0082\u00aep<|\u00d4B\nR\u0090\u000e\u00e3q\u001c\u00cb\u00ca\u00f3p>\u00f7\u0001\u00aa\u0098\u0010\u0090\u009c\u00f3sl\u009ct\u00c9\u00aba\u0080[\u0089\u00fc\u00d4`\u0010\u00d3\u00bdr;F[S6\u00f5\u008b\u0013\r\u00f0\u001b;\u00a7(Q\u009fT\u0082Ml\u000e\u00f5\u0005\u00a3\u00117\u00bc\u00c9\u00b2\u00bd]\u00cb\u00c6\u0012\u009e\u001d\u0017\u00c0\u00b72C\u00b0\u00e7\u0016\u00c6\u00e3\u00df\u0001\fG&\u008b\u00c82\u0010\u0086\u00c6\u00f0\u0088OB=b\u00d9c\u0080\u00b8\u00e3\u00cfW\u008c(\u0002\u00d3\u00c0}u#-\u008f\u0003\u00bf\u0095\u0089\u0004\u0014\u00d1\u00b0\u0016g\u00f15\u00ee:\u00e7\u009d\u0087\u00c2A5\b\u00faG\u008a\u00d2\u00f8\u00ce\u009b\u00ad\u0013\u00adw\u0010\u00ab\u00ff\u0019\u009f9~\u0012\u0096\u000et\\*>p\u008a]\u0010~\u00e1\b\u00ec)=\u00f1|\u00a7\u008f\u00d89>\u00dd\u00d3\u008f\u0018\u00c9\u00ff\u00d9\n\u00d0\u00bb\u00b3\t)<\u001b\u0089\u0019s}\u00af\u00c5\u00fa\u00a5)\u0082\u00fdQ\u001d8a\u00a0\u00c8\u00b5V\u00822\u00973\u0003\u00c4\\\u00a7\u00bc0bT:\u0085WN\u00f0\u00bb\u00b9\u0094Ps\u0089s-\u0094\u00e5TJJ\u0081-`Q\u000e\u00a9#T\u00a5j\u001a\u00ea\u00d0D\u0091\u00ab\u00c4\u0093_\u00d3q \u0084eu\u00fcf\u00be\u00cd-9:\u00fc\u0007\u0090u\u0087\u00cd\u00f7\u0092x\u0099\u0093\u007f)0\u0087\u00b2\u00a3\u00f9\u00c5\u0082\u0098\r\u0018N86O\u00aau\u0013\u00c2\u001a\u00b3\u0007&V\u00c4\u00a4\u0090\u00ed\u00e9\u00ba\f\u00a9\u00d9\u00cd(\u0010c\u00d3\u009an\u00fd7v\u00a1\u00d0\u00d9\u0019\u0083\u00f1\u0082\u00f2\u00c58lJ\u0090V\b\u00e4\u00e1$8\u00e0\u00e6z\u00e0\u00b2U\u00e9#\u00af+\u00efbM\u00e2F\u00adKGI\u0094\u00c1n\u00bb\u0093\u001d#\u00ec\u0089r\u00f2\u00ed\u0088\u00ca\u00db~\u00b3o\u00a3%P\u00ca`\u00e7\u00e0\u00e1\u009d\u00a5 Pc'\u00fa\u0097b\u0083\u001e\u0006l\u0011\u00f23\u00ff\u00f9;>\u00a7<\u008f\r\u008b\u00cc\u008ff<{'=\u0098\u0087;0\u00ec\u00a5\u0099Q\u0014\u001a,m\u001dt\u00df\u00dc\u00af\u0091\u00a6\u009d\u00d5m\f\u0094\u00e7\u00b6\u000f\u00ba\u008b\u00d4\tg*\u00b4\u00b7\u00b1\u00d7p*\u0086\u00c6^@\u00adb\u00d6(\u00c7H\u00b04\u00ce\u0018\u00b3_\u0011\t\u00c2\u0003\u00f8\r\u00bc\u0013\u00da/,Z\u00a7\fj\u0003\u00a2\u000ba1\u00b7B \u00d4\u00c6\u00e7\u0002\u007fd<+\u00e7\u00c9\u00bba\u00b0\u00e9\u00b0e\nagj{\u0000\u00ad\u00ee\u009b\u00c3[\u00a4\u0019y\u00d2\u000f\u0010\u00f1|\u00ddM\u00d1m\u00ee@\u00db\u00a0o\u00db\u00b3\u00f0\u00e9J \u00e4d\u0084\t\u009cp\u0089p\u00a0\u00bb\u00c1\u00d8\u008c\u00b3\u0089w<\u00f0\u00a6\u0081 >\u00b6WR\u0097aAE\u00bb\u0003\u001c\u0010\u0000r\u0016\u00ee\u0015\u00d0\u00e2K\u0092\u00e7V\u0000\u00cd^\u00b4\u00d4 \u00cb\u00b2\u001a\u00bb\u00dc\u00bd\u008b[\u0083\u00b7\u00a9=\u00c6eY\u00f5\u00b2\u00c0\u00c4\u00a3U\u00a9\u008a_\u00d9:\u000b\u00dc\u00ce\u001b\u00f8f \u00fey\u0083\u00a3\u00db\u00e20\u00e6r\u000f\u00cd\u00d1\u00b1\f\u00a8\u00d1\u001b\u001a\u0015\u001f\u00a4\u0088\u00e2%\u000f\t*\u00een\u00f5\u00bf\u00ed \u00e1\u00e1\u00cc-\u0012@L\u00fcS\u00d5\u00d8\u00afn\u00b4\u009c\u0006\u00d0\b*\"\u009cB@\u00b1\u00c7hiw\u00a2f\u00eb\u00b0 \u0096g\u00b7?\u00ebdQ\u0097\u00126\u0001q\u00c5\u00fd\u00a3\u0091hy\u00163D\u0017\u0099{\u0098*z\u00cdX<\u00f0=\u0010?8{\u0015X\\z7U%\u009a\u0007\u00a0\u00a6\u00aa\u00fb\u0010Z\u00c88\u00a2\u00b2\u0097y\n\\\u0087\u00a8/R}\u0012'\u0018\u0095\u0001Y\u00d5\u009a\u00a4,\u008d\u00e8\b\u00bc\u00fe.\u00ac\u00b8\u0012A v\u00cbs\u00d8\u00895\u0010\u00df\u00ed\u0000\u00d3\u00cbz\u0012\u0098m8\u00a3\u00f3\u00198|\u00d1\u0018\u00a4\u00c1\u00ed\u0002\u00a9c\u00fe\u0015\u00f9\u00f0}\u00fa|1\u0005E\u00fe\u00bcp%\u0092Fk\u00a8 7h\u0007;$\u00d7%\u00f1N%\u00c8o\u00ca\u0003\u001f\u00e4\u00aax^\u00ae\b\u00b3\u00c8\u00a9u\u00cb\u0097\u000f\u00cdc\u00a6\u0001\u0010\u00ea \u00ba\u00e0\u00ac\u008e\u00d2\u00e7~\u00b0\u00ce(\u00feC\u00d4\u00a7 \u0016\u00d2\u0091Y\u00db\u0016\u001a\u0015#-\u00d2N\u00cd\u00c0y\u00b3\u00beV\u00eci\u00c6\u00b6pi\u0096\u0096\u00d2'\u00b9q\f  \u0095\u00d2\u00a1\u00e9e\u00d25\u00e0\u00af\u00d7uL\u000f\u001bO\u00d5\u001a[\u00ae\u0001VFY\\\u00bc\u00e1\u00c7\u00eb\u0017\u001f\u00a2\u00b6\u00109*\u00a2\u0082M\u00a5ko>>$Dc\u0006\u001a\u00fc \u001co\u009b\u00b4\u00a4\u00abo\u0097\u000fI\u0080\u00aco\u009aU\u00ee\u0090:6J\u00f8\"p\u0096\u00d6\u00ac\u00a1\u00c7tj\u00b4'\u0010G\u00e9\u0098:\u00e7d\u00c2\u0002Y\u00baW\u00c4\u00ec\b\u000e\u00a7\u0018\u00e8>\u0099S[GR\u0000l\u00cec\u00c3\u0081\u00b0e\u00e4\u0090\u0014\u00e8\u0087\u00d4H\u00aa] c\u00ebH\u00bd[8\u009e\u00ea1+|\u00a651%71\u00f0D\u000e\u00d5\u0083\u00a4v\u00f7\u00a7\u00917\u00f69\u00d0'8!\u0017\u00fd\\\u009dWu\u00c9\u00b6\"G\u00a5p\u00deNR#Z\u0004\u00a9\u008f\u00e6ln\u00ffd@\u0091\u00c9\u0082Q\u00bb\u00cd;A\u00bb\u00c1j\u00d2\u00f5\u008dr\u00f7\u0080!a\u008f\u009b\u00b73Z&\u00db\u0005\u00e4\u00af \u00ae_\u000e\u00e4\u00c3\u00d6V\u0014\u00d7;\u001d\u0087\u00f9x\u00c2=\u00e5?(\u00c2\u000e\u008c\u00e7b;\u001f\u00d0\u00d9\u00b1\u008d{~\u0018\u00e6|g\u0083:\u00da\u00ee\u00c6\u00ae$\u009f.b_\u00de]\u0081\u009e\u00f0\u00b2\u00e4_WY\u0018\u00f8\u00dal\u00ee\u008f\u0093\u00af0\u00e0)\u00b5\u00d0.\u008c\u00aa>@5\u00ce\u0099\u00dbK\u009d\u00c2 \u00ae\u00cb\u008d\u00f7R\u00d5\u00e8\u0098\u00fcx\u00cc\u00e1\u00b2/U=M\u00fe\u008a\u00d9\u00b1b6$I\u00e4{\u0082\u0094\u0089=\u00050\u00c8\u00c6|8a\u00d8\u0095\u00efU\u008c\u00e2\bxX\u0090\u00ecTU\u0096C\u00c2\u00b6\u00bdW\u00c6\u0018\u00d7\u00d6\u00aa\u0006z\u009d\u00e3\u0084\u00f2\u00f3\u001b\u00edgn%CX\u00e5\u00b0\n4\u00c4 _\u008ag\u0080&\u00f7\u0007L\u009c\u00f7\u00abW_\u00c0F]$\u001aj\u00d8\u0011>SuC\u000em-\u00e6\u00cd\u001fn \u00ee]\u00caA\u0001\u00c3Q'p\u0018\u009b\u000e\u00fc\u00b9{f\u009a\u00df\u0010\u0099h\r\u00e3\u00ba6\u00f5y\u00da\b(\u008c \u0010\u00a4\u00019\u0097\u00b6u\u00c1^3v\u00964\u0087\u00ab\u0006\u000b =\u008aQx\u00e6\u00b2\u0087\u00ca\u0087\u00d0}C\u00d8-\u00b9\u00f5[>o\u00847\u00d9\u000fr><E\u00f0\u00f8\u009f\u009a\u00ad(\u000f[U\u00c8~bh\u00e8-\u00b0\u00a4\u00bc{<2Fee\u00e1\u00e9v1\u00cd%\u0098\u00f1\u00d3\u00bbM\u00d8\u00ad\u00c5\u00a0L\u00b4\u00d7\u00f08)\u00e4\u0018\\\u0018\u00c9\u00ff\u000bp\u00c7\u00a1b\\\u00e9\u009d\u00837\u00eeDX\u0007\t2\u009f\u00ccv\u0014\u0018}\u00bf\u00f0\u00aeL\u00aa\u0098e\u0082K'\u00e6Y\u0085[\u00a6zM\u0095\u009aU\u000b\u00a8\u00e4\u0018\u00d5\u009a1\u0005\u00a2\u00ad\u008f\u0000\u00f5\u00ea\u00f5\u0095\u00dd?\u00a8\u00b5V\u00dbo\u0087\u00ef\u0004\u00a5\u0086\u0018\u008f\u00a6nuf\u00d1\u001b%c\u00a2]{\u00e5\u00ab\u0082\u0096\u0090\u00cf\u00ac\u000f\u008d\u00ae\u0087\u0085(5\u00c19`\r\u007f4\u0097\u00d9\u00fa\u008e\u009d\u00e8\t\u009fAy\u0097\u00b4\u008d\u00a41e\u001e\u000b\u00b8E>D\u00a6\u00b4\u00de\u009dZ\u00047\u00f1W\u00fd\u00b1\u0010\u00cd\u0092EZ6/\u00f55O[D\u00ec\u0005G9t";
                        var17_6 = 3468;
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
                            var18_3[var16_4++] = NoRender.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00afE\u0010\u00b8\u001e\u00ed\u009d\u009d3\u0004\u00dc\u00c0\u009d\u0090\u00ac_\u0018\u00b2-B#\u00fa\bJ\u00a1\u009a\u00b9\u00b5\u00b9[1Z\u00edn\u0018de\u009b\u00ab\u00b2\u00af";
                            var17_6 = 41;
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
                            var18_3[var16_4++] = NoRender.zkmDecodeUtf8(var19_9).intern();
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
                NoRender.ab = var18_3;
                NoRender.fb = new String[119];
                NoRender.mb = new HashMap<K, V>(13);
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
                var6_12 = new long[67];
                var3_13 = 0;
                var4_14 = "\u00f7\u008cgI\u000b\u001aT\u00cdo\u00a8\u00a1\n\u0003\u00b3^F\u00fa\u00f2{\u00fb\u00e6t\u00eec\u00a8%AH\u00f34\u008a\u00e1\u0094y-\t\u0005\u00f4\u00e58T\u00e6\u00de\u00b5b\u0015\u0088\u00ea\u0091\u0093s2\u00d7\u00f3&u/\u001a,\u0099\u008a\u00f9\t\u00cd\u008a\u0090m-\u0007; \u00b63\u007f.\u0099\u00a3\u00cf\u00a21\u0085\u008f6$\u00fb\u0007\u00ee\u00favX\u00f1\u0018\u00a6\r\u00e3#\u00cfn\u0083\u000b\u0005\u00b6J\u009f\u00a1\u0083\u00a3\u00ec\u00f1\u00bfK\u001127zA\u009b6\u00dc\u00e1\u00fb\u0096\u008a\u0016$\u00d7>\u00f0\u00c2\u00a47*\u00c9\u009b\f^\u00b7\u0016b\u00b3\u0081\u00bd>;\u00berc\u00c5\u00c8\u00d4U3\u00ff\u007f>\u00ef\u00d4\u00afoSl;\u00fb\u00d9\u00d0\u00d9\u00b8-\u0000|&\u00d0=\u00e4aT\u00f2sWa\u00a0\u00c9\u00b3\u00df\u00b4\u00aa\u00e5\u00a8\u00d1\u008c%\u0013Tm\u0019\u009d\u00d5c\u008a\u0015fq\u00f132\u00dbZf\u0011[\u00edZ\u00a3H\u00ab\n\u00ca\u0095\u00df\u0084m\u0097\u00a1U\u008dg,\u00fakx\u00a9m\u001f\u00f6;\u00fc\u00bc\u0081\u001bX\u00fao_\u00b5\u0018\u001a\u0097^\u00b7\u009a\u001d\u00c0\u00cf\u0083.\u0083\u00e64\b\u00ef\u00b0\u00a6eST!\u00d7\u00ae\u00c2\u000f\u00a11T\u001d\u008a}M\u00cd\u001fs\u00ad&\u00c4\u007f\u0095L\u0083\u00ed\u0094K\u00ab\u00b9k\u00bb\f\u00fb\u00d9\u00a7\u00d9HDt\u00e7\u00059\u00ba\u00e6\u0007}p\u000b\u00f1i\u00e23\u00d7\u009c\u00b3\u00ca\n\u00d9\u00ee\u0004\u00b9\u0081\u00d1\u00bf\u00f5x\u0081\u007f\u00f7\u00a9\u00d0\u000e\u00ffg\u009d\"\u00fc\u00b3\u00c7\u0019\u009a\u00b0\u00c1G\u00f5\u00b1\u00c0\u0098\u0090\u00be\u00e4T\u00a2\u000e/\u0003\u00032 \u0091\u00ef{\u00c7\u008dA\u0006\u00f5:e\u0016\u00dc>u\u0019\u00e7\u009b \u00c8\u00b3\u00d2\u00e1:t\u00ae\u007f<\u00bbC}\u00f75\u00ed\u00ed\u00e6\u00c4\u00ea\u0015['\u00f5*\u00e5g\u00e17\u0003\u00e50N\u00db\u007f\u008c\u00ca\u00b2\u0096\u00fc\\\u00b9\u00dd:W\u00f0\u00ed\u0015\u00a2\u00e8/\u0000\u00c5\\/\t\u00d0y\u0099\u00a2\u00f0\u0099\u00eb\u007f\u00fd\u00f2\u00d6\u0088F\u0097\u0092=\u00d3\u00c9\nH\u009a\u000fB\u00ed\u00fe\u00d2\u00e2\u0005\\\u00bd\u00e2\u00e8\u009f\u00b2\u0011\"5<\u00c0\u00f1F\"\u00dd\u0012{\u0089Y\u00fb\u00db\u00b3\u00e6\u00cc\u0096g\u00b0\u001f\u00d0C\u00a0w\u00da\u00a1K\u008c&Y\u00f2\u0013\u00ee\u00a7\u00fdB\u000f\u00d4[\u0001/g\u0092\u00c3z;B\u0083i";
                var5_15 = 520;
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
                    var4_14 = "\u0013\u0087\u0001\u0004\u00b1h#\u00ab\u00c2\u00b8J\u00ef\u000f\u00e9\u00d0\u0002";
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
        NoRender.hb = var6_12;
        NoRender.lb = new Integer[67];
        var39_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NoRender.class, "screen", "getScreen()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "hurtCam", "getHurtCam()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "potions", "getPotions()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "vignette", "getVignette()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "portal", "getPortal()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "bossbar", "getBossbar()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "fireOverlay", "getFireOverlay()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "waterOverlay", "getWaterOverlay()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "blockOverlay", "getBlockOverlay()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "nausea", "getNausea()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "blindness", "getBlindness()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "guiBackGround", "getGuiBackGround()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "noScoreBoard", "getNoScoreBoard()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "hotbarItemName", "getHotbarItemName()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "antiTitle", "getAntiTitle()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "entities", "getEntities()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "xp", "getXp()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "arrows", "getArrows()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "eggs", "getEggs()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "elderGuardian", "getElderGuardian()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "armor", "getArmor()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "items", "getItems()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "crystals", "getCrystals()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "fireworks", "getFireworks()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "noArmorStands", "getNoArmorStands()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "particles", "getParticles()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "explosions", "getExplosions()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "campFire", "getCampFire()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "breakParticles", "getBreakParticles()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "noWeather", "getNoWeather()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "world", "getWorld()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "maps", "getMaps()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "spawnerEntity", "getSpawnerEntity()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "fireEntity", "getFireEntity()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "signText", "getSignText()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "darkness", "getDarkness()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "fog", "getFog()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "other", "getOther()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NoRender.class, "antiPlayerCollision", "getAntiPlayerCollision()Z", 0))};
        NoRender.delegatedProperties = var39_20;
        NoRender.INSTANCE = new NoRender();
        NoRender.screen$delegate = SettingFactory.settingGroup$default(NoRender.INSTANCE, "screen", AuraTargetsMode.BOOLS, null, 4, null);
        NoRender.hurtCam$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "hurt-cam", true, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.potions$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "potions", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.vignette$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "vignette", true, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.portal$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "portal", true, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.bossbar$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "bossbar", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.fireOverlay$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "fire-overlay", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.waterOverlay$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "water-overlay", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.blockOverlay$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "block-overlay", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.nausea$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "nausea", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.blindness$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "blindness", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.guiBackGround$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "gui-back-ground", true, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.noScoreBoard$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "no-score-board", true, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.hotbarItemName$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "hotbar-item-name", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.antiTitle$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "anti-title", false, NoRender.INSTANCE.getScreen(), null, 8, null);
        NoRender.entities$delegate = SettingFactory.settingGroup$default(NoRender.INSTANCE, "entities", AuraTargetsMode.BOOLS, null, 4, null);
        NoRender.xp$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "xp", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.arrows$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "arrows", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.eggs$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "eggs", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.elderGuardian$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "guardian", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.armor$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "armor", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.items$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "items", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.crystals$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "crystals", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.fireworks$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "fireworks", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.noArmorStands$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "no-armor-stands", false, NoRender.INSTANCE.getEntities(), null, 8, null);
        NoRender.particles$delegate = SettingFactory.settingGroup$default(NoRender.INSTANCE, "particles", AuraTargetsMode.BOOLS, null, 4, null);
        NoRender.explosions$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "explosions", true, NoRender.INSTANCE.getParticles(), null, 8, null);
        NoRender.campFire$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "camp-fire", false, NoRender.INSTANCE.getParticles(), null, 8, null);
        NoRender.breakParticles$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "break-particles", true, NoRender.INSTANCE.getParticles(), null, 8, null);
        NoRender.noWeather$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "no-weather", false, NoRender.INSTANCE.getParticles(), null, 8, null);
        NoRender.world$delegate = SettingFactory.settingGroup$default(NoRender.INSTANCE, "world", AuraTargetsMode.BOOLS, null, 4, null);
        NoRender.maps$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "maps", false, NoRender.INSTANCE.getWorld(), null, 8, null);
        NoRender.spawnerEntity$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "spawner-entity", false, NoRender.INSTANCE.getWorld(), null, 8, null);
        NoRender.fireEntity$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "fire-on-entity", true, NoRender.INSTANCE.getWorld(), null, 8, null);
        NoRender.signText$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "sign-text", false, NoRender.INSTANCE.getWorld(), null, 8, null);
        NoRender.darkness$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "darkness", false, NoRender.INSTANCE.getWorld(), null, 8, null);
        NoRender.fog$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "fog", false, NoRender.INSTANCE.getWorld(), null, 8, null);
        NoRender.other$delegate = SettingFactory.settingGroup$default(NoRender.INSTANCE, "other", AuraTargetsMode.BOOLS, null, 4, null);
        NoRender.antiPlayerCollision$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoRender.INSTANCE, "anti-player-collision", true, NoRender.INSTANCE.getOther(), null, 8, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7560;
        if (fb[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])gb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    gb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e6", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = ab[n2].getBytes("ISO-8859-1");
            NoRender.fb[n2] = NoRender.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return fb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoRender.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1CBE;
        if (lb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = hb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])mb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    mb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e6", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NoRender.lb[n2] = n3;
        }
        return lb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NoRender.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoRender.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NoRender.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

