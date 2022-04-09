package ru.mishaneyt.rp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mishaneyt.rp.events.EventsListsner;

import java.io.File;

public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) saveDefaultConfig();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventsListsner(), this);
    }

    public static Main getInstance() {
        return instance;
    }
}