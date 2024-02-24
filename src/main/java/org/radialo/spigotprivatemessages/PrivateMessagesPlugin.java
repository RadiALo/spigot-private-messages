package org.radialo.spigotprivatemessages;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PrivateMessagesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("message"))
                .setExecutor(new MessageCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
