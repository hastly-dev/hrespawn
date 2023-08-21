package me.hastly.hrespawn;

import me.hastly.hrespawn.Commands.CMD;
import me.hastly.hrespawn.Events.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("hrespawn").setExecutor(new CMD());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    public static Plugin getInstance() {
        return instance;
    }
}
