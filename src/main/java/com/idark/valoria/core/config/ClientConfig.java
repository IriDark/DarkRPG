package com.idark.valoria.core.config;

import net.minecraftforge.common.*;
import org.apache.commons.lang3.tuple.*;

public class ClientConfig {
    public static ForgeConfigSpec.ConfigValue<Integer>
            MAGMA_CHARGE_BAR_Y, MAGMA_CHARGE_BAR_X, MAGMA_CHARGE_BAR_TYPE;
    public static ForgeConfigSpec.ConfigValue<Boolean>
            ABILITY_OVERLAY, PHANTOM_ACTIVATION, OLD_GOBLIN_MODEL;

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }

    public static final ClientConfig INSTANCE;
    public static final ForgeConfigSpec SPEC;
    public ClientConfig(ForgeConfigSpec.Builder builder){
        ABILITY_OVERLAY = builder.comment("When enabled shows the overlay after using a weapon ability (Default: true)")
        .comment("Reload Resourcepacks after turning this on (F3+T)")
        .define("DashOverlay", true);
        MAGMA_CHARGE_BAR_Y = builder.comment("(Y) Coordinate for Magma Bar")
        .comment("Can be edited in-game without reloading packs (If nothing changed reload packs with [F3+T] keybind)")
        .define("MagmaBarY", 5);
        MAGMA_CHARGE_BAR_X = builder.comment("(X) Coordinate for Magma Bar")
        .comment("Can be edited in-game without reloading packs (If nothing changed reload packs with [F3+T] keybind)")
        .define("MagmaBarX", 4);
        MAGMA_CHARGE_BAR_TYPE = builder.comment("Type of Magma Bar")
        .comment("Can be edited in-game without reloading packs (If nothing changed reload packs with [F3+T] keybind)")
        .defineInRange("MagmaBarType", 1, 1, 3);
        PHANTOM_ACTIVATION = builder.comment("Item activation on ability use")
        .define("PhantomActivation", true);
        OLD_GOBLIN_MODEL = builder.comment("Changes goblin model to old one")
        .comment("Reload Resourcepacks after turning this on (F3+T)")
        .define("OldGoblinModel", false);
    }
}