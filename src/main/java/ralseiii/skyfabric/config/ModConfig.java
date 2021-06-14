package ralseiii.skyfabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@me.shedaniel.autoconfig.annotation.Config(name = "skyfabric")
public class ModConfig implements ConfigData {
    public ModConfig() {

    }

    Boolean Enabled = true;

    @ConfigEntry.Gui.CollapsibleObject
    public Config.SolverConfigDropdown solverConfigDropdown = new Config.SolverConfigDropdown();
    @ConfigEntry.Gui.CollapsibleObject
    public Config.DungeonConfigDropdown dungeonConfigDropdown = new Config.DungeonConfigDropdown();

    public Boolean dungeonHud = true;
    public Boolean dungeonMap = true;


}
