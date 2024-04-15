package net.cathienova.havenpebbles.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public final ForgeConfigSpec.BooleanValue enablePebbles;
    public final ForgeConfigSpec.BooleanValue onlyDimensionalPebbles;
    public final ForgeConfigSpec.BooleanValue emitPebbleSound;
    public final ForgeConfigSpec.IntValue andesitePebbleWeight;
    public final ForgeConfigSpec.IntValue calcitePebbleWeight;
    public final ForgeConfigSpec.IntValue deepslatePebbleWeight;
    public final ForgeConfigSpec.IntValue dioritePebbleWeight;
    public final ForgeConfigSpec.IntValue dripstonePebbleWeight;
    public final ForgeConfigSpec.IntValue granitePebbleWeight;
    public final ForgeConfigSpec.IntValue tuffPebbleWeight;
    public final ForgeConfigSpec.IntValue stonePebbleWeight;
    public final ForgeConfigSpec.IntValue netherrackPebbleWeight;
    public final ForgeConfigSpec.IntValue basaltPebbleWeight;
    public final ForgeConfigSpec.IntValue blackstonePebbleWeight;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
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
