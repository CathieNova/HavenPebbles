package net.cathienova.havenpebbles.config;

import net.cathienova.havenpebbles.HavenPebbles;
import net.minecraftforge.fml.config.ModConfig;

public class HavenPebblesConfig {
    public static boolean enablePebbles;
    public static boolean onlyDimensionalPebbles;
    public static boolean emitPebbleSound;
    public static int andesitePebbleWeight;
    public static int calcitePebbleWeight;
    public static int deepslatePebbleWeight;
    public static int dioritePebbleWeight;
    public static int dripstonePebbleWeight;
    public static int granitePebbleWeight;
    public static int tuffPebbleWeight;
    public static int stonePebbleWeight;
    public static int netherrackPebbleWeight;
    public static int basaltPebbleWeight;
    public static int blackstonePebbleWeight;

    public static void bake(ModConfig config) {
        enablePebbles = HavenPebbles.c_config.enablePebbles.get();
        onlyDimensionalPebbles = HavenPebbles.c_config.onlyDimensionalPebbles.get();
        emitPebbleSound = HavenPebbles.c_config.emitPebbleSound.get();
        andesitePebbleWeight = HavenPebbles.c_config.andesitePebbleWeight.get();
        calcitePebbleWeight = HavenPebbles.c_config.calcitePebbleWeight.get();
        deepslatePebbleWeight = HavenPebbles.c_config.deepslatePebbleWeight.get();
        dioritePebbleWeight = HavenPebbles.c_config.dioritePebbleWeight.get();
        dripstonePebbleWeight = HavenPebbles.c_config.dripstonePebbleWeight.get();
        granitePebbleWeight = HavenPebbles.c_config.granitePebbleWeight.get();
        tuffPebbleWeight = HavenPebbles.c_config.tuffPebbleWeight.get();
        stonePebbleWeight = HavenPebbles.c_config.stonePebbleWeight.get();
        netherrackPebbleWeight = HavenPebbles.c_config.netherrackPebbleWeight.get();
        basaltPebbleWeight = HavenPebbles.c_config.basaltPebbleWeight.get();
        blackstonePebbleWeight = HavenPebbles.c_config.blackstonePebbleWeight.get();
    }
}
