package net.cathienova.havenpebbles.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public record PebbleEffectSet(Selection selection, List<Effect> effects) {
    public static final PebbleEffectSet EMPTY = new PebbleEffectSet(Selection.ALL, List.of());

    public PebbleEffectSet {
        effects = List.copyOf(effects);
    }

    public void apply(LivingEntity entity, RandomSource random) {
        if (effects.isEmpty()) {
            return;
        }

        if (selection == Selection.RANDOM) {
            Effect effect = effects.get(random.nextInt(effects.size()));
            if (random.nextDouble() * 100.0D < effect.chance()) {
                addEffect(entity, effect);
            }
            return;
        }

        for (Effect effect : effects) {
            addEffect(entity, effect);
        }
    }

    private static void addEffect(LivingEntity entity, Effect effect) {
        entity.addEffect(new MobEffectInstance(
                BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect.effect()),
                effect.duration(),
                effect.amplifier()
        ));
    }

    public enum Selection {
        ALL,
        RANDOM
    }

    public record Effect(MobEffect effect, int duration, int amplifier, double chance) {
    }
}