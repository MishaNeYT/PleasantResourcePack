package ru.mishaneyt.rp;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class Listener implements org.bukkit.event.Listener {

    public Listener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        FileConfiguration config = Main.getInstance().getConfig();
        if (!config.getBoolean("Settings.Resourcepack")) return;

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> e.getPlayer().setResourcePack(config.getString("Settings.Link")), 1);
    }

    @EventHandler
    public void resourcePack(PlayerResourcePackStatusEvent e) {
        FileConfiguration config = Main.getInstance().getConfig();
        if (!config.getBoolean("Settings.Resourcepack")) return;

        // Проверка клиента на отключение ресурс пака.
        if (e.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED)

            for (String message : config.getStringList("Settings.KickMessage"))
                e.getPlayer().kickPlayer(message.replace("§", "&"));
    }
}