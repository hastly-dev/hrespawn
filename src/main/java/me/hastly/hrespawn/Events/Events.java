package me.hastly.hrespawn.Events;

import com.destroystokyo.paper.Title;
import me.hastly.hrespawn.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class Events implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Plugin.getInstance().getConfig().getBoolean("enableAutoRespawn", true)) {
            final Player player = event.getEntity();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {
                @Override
                public void run() {
                    player.spigot().respawn();
                    player.setVelocity(new Vector(0, 0, 0));
                    player.sendTitle(Plugin.getInstance().getConfig().getString("deathTitle").replaceAll("&", "§"), Plugin.getInstance().getConfig().getString("deathSubTitle").replaceAll("&", "§"), 10, 40, 20);
                }
            }, getTicks());
        }
    }

    private int getTicks() {
        final int ticks = Plugin.getInstance().getConfig().getInt("delayBeforeRespawnTicks");
        if (ticks >= 0) {
            return ticks;
        }
        else {
            Plugin.getInstance().getLogger().log(Level.WARNING, "Ticks should not be less than 0");
            Plugin.getInstance().getConfig().set("delayBeforeRespawnTicks", 0);
            Plugin.getInstance().saveConfig();
            return 0;
        }
    }
}