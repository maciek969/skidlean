/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.sound;

import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;

public final class CustomSoundInstance
extends AbstractSoundInstance {
    CustomSoundInstance(SoundEvent soundEvent, SoundCategory soundCategory, Random random) {
        super(soundEvent, soundCategory, random);
    }

    @Override
    public float getVolume() {
        return super.getVolume() * this.volume;
    }
}

