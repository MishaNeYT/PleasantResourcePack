package ru.mishaneyt.rp;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        new Listener();
    }

    public static synchronized Main getInstance() {
        return instance;
    }
}
