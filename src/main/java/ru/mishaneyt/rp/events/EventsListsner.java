package ru.mishaneyt.rp.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import ru.mishaneyt.rp.Main;

public class EventsListsner implements Listener {
    private static final Configuration config = Main.getInstance().getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!config.getBoolean("Settings.Resourcepack")) return;
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> p.setResourcePack(config.getString("Settings.Link")), 1);
    }

    @EventHandler
    public void onResourcePackCheck(PlayerResourcePackStatusEvent e) {
        if (!config.getBoolean("Settings.Resourcepack")) return;

        if (e.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {

            for (String key : config.getStringList("Settings.KickMessage"))
                e.getPlayer().kickPlayer(key.replace("§", "&"));
        }
    }
}