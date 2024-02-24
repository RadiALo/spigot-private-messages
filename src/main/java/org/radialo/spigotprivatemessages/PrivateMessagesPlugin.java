package org.radialo.spigotprivatemessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class PrivateMessagesPlugin extends JavaPlugin implements Listener {

    public static String formatMessage(Player from, Player to, String message) {
        return ChatColor.YELLOW + ChatColor.BOLD.toString()
                + from.getName()
                + " ->" + to.getName() + ": "
                + ChatColor.RESET + message;
    }

    private final Map<UUID, UUID> recentMessages = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("message"))
                .setExecutor(new MessageCommand(recentMessages));

        Objects.requireNonNull(getCommand("reply"))
                .setExecutor(new ReplyCommand(recentMessages));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        recentMessages.remove(event.getPlayer().getUniqueId());
    }
}
