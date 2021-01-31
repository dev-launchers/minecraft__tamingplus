package devlaunchers.untaming;

import org.bukkit.plugin.java.JavaPlugin;

public final class Untaming extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MyListener(), this);
        System.out.println("running plugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
