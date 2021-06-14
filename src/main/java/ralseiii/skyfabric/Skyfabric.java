package ralseiii.skyfabric;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.MinecraftClient;
import ralseiii.skyfabric.config.Config;
import ralseiii.skyfabric.hud.dungeon.DungeonMainHUD;
import ralseiii.skyfabric.hud.dungeon.DungeonMap;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.solvers.dwarven.PuzzlerSolver;
import ralseiii.skyfabric.utils.SbChecks;
import ralseiii.skyfabric.solvers.dungeon.entity.BlazeSolver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class Skyfabric implements ClientModInitializer {
    private static int tickCounter = 0;
    @Override
    public void onInitializeClient() {
        // register config file
        AutoConfig.register(Config.ModConfig.class, GsonConfigSerializer::new);
        Config.config = AutoConfig.getConfigHolder(Config.ModConfig.class).getConfig();
        DungeonMainHUD.registerHUD();
        DungeonMap.registerHUD();
        ThreeWeirdos.register();
        PuzzlerSolver.register();
    }

    public static void onTick() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (minecraftClient == null) return;
        tickCounter++;
        if (tickCounter % 60 == 0) {
            if (minecraftClient.world != null && !minecraftClient.isInSingleplayer()) {
                SbChecks.checkSkyblock();
               if (SbChecks.isCatacombs)
                    BlazeSolver.blazeSolver();
            }
            tickCounter = 0;
        }
    }
}
