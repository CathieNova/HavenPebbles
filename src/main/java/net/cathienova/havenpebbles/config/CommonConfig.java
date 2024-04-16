package net.cathienova.havenpebbles.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {
    public static final Pair<CommonConfig, ModConfigSpec> SPEC_PAIR = new ModConfigSpec.Builder().configure(CommonConfig::new);
    public static final CommonConfig CONFIG = SPEC_PAIR.getLeft();
    public static final ModConfigSpec SPEC = SPEC_PAIR.getRight();
    public final ModConfigSpec.ConfigValue<Boolean> enablePebbles;
    public final ModConfigSpec.ConfigValue<Boolean> onlyDimensionalPebbles;
    public final ModConfigSpec.ConfigValue<Boolean> emitPebbleSound;
    public final ModConfigSpec.ConfigValue<Integer> andesitePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> calcitePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> deepslatePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> dioritePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> dripstonePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> granitePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> tuffPebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> stonePebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> netherrackPebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> basaltPebbleWeight;
    public final ModConfigSpec.ConfigValue<Integer> blackstonePebbleWeight;

    public CommonConfig(ModConfigSpec.Builder builder) {
        enablePebbles = builder.comment("Enable pebbles?").define("enablePebbles", true);
        onlyDimensionalPebbles = builder.comment("Should pebbles only generate in their respective dimensions?").define("onlyDimensionalPebbles", true);
        emitPebbleSound = builder.comment("When right clicking to get pebbles, should it emit sound?").define("emitPebbleSound", true);
        andesitePebbleWeight = builder.comment("The weight of andesite pebbles. (1 = 100%)").defineInRange("andesitePebbleWeight", 15, 0, 100);
        calcitePebbleWeight = builder.comment("The weight of calcite pebbles. (1 = 100%)").defineInRange("calcitePebbleWeight", 10, 0, 100);
        deepslatePebbleWeight = builder.comment("The weight of deepslate pebbles. (1 = 100%)").defineInRange("deepslatePebbleWeight", 10, 0, 100);
        dioritePebbleWeight = builder.comment("The weight of diorite pebbles. (1 = 100%)").defineInRange("dioritePebbleWeight", 10, 0, 100);
        dripstonePebbleWeight = builder.comment("The weight of dripstone pebbles. (1 = 100%)").defineInRange("dripstonePebbleWeight", 10, 0, 100);
        granitePebbleWeight = builder.comment("The weight of granite pebbles. (1 = 100%)").defineInRange("granitePebbleWeight", 10, 0, 100);
        tuffPebbleWeight = builder.comment("The weight of tuff pebbles. (1 = 100%)").defineInRange("tuffPebbleWeight", 10, 0, 100);
        stonePebbleWeight = builder.comment("The weight of stone pebbles. (1 = 100%)").defineInRange("stonePebbleWeight", 40, 0, 100);
        netherrackPebbleWeight = builder.comment("The weight of netherrack pebbles. (1 = 100%)").defineInRange("netherrackPebbleWeight", 90, 0, 100);
        basaltPebbleWeight = builder.comment("The weight of basalt pebbles. (1 = 100%)").defineInRange("basaltPebbleWeight", 10, 0, 100);
        blackstonePebbleWeight = builder.comment("The weight of blackstone pebbles. (1 = 100%)").defineInRange("blackstonePebbleWeight", 5, 0, 100);
    }
}
