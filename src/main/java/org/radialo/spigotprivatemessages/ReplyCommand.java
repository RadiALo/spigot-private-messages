package org.radialo.spigotprivatemessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    private final Map<UUID, UUID> recentMessages;

    public ReplyCommand(Map<UUID, UUID> recentMessages) {
        this.recentMessages = recentMessages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use /reply <message>");
            } else if (recentMessages.containsKey(player.getUniqueId())) {
                Player target = Bukkit.getPlayer(
                        recentMessages.get(player.getUniqueId())
                );

                if (target != null) {
                    StringBuilder messageBuilder = new StringBuilder();

                    for (String arg : args) {
                        messageBuilder.append(arg).append(" ");
                    }

                    String message = PrivateMessagesPlugin.formatMessage(
                            player, target, messageBuilder.toString()
                    );

                    target.sendMessage(message);

                    player.sendMessage(message);

                    recentMessages.put(target.getUniqueId(), player.getUniqueId());;
                } else {
                    player.sendMessage(ChatColor.RED + "Player was not found!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "No recent messages!");
            }
        }

        return false;
    }
}
