package org.radialo.spigotprivatemessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use /message <player> <message>");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);

                if (target != null) {
                    StringBuilder messageBuilder = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        messageBuilder.append(args[i]).append(" ");
                    }

                    target.sendMessage(
                            player.getName() + " -> You: " + messageBuilder
                    );

                    player.sendMessage(
                            "You -> " + target.getName() + ": " + messageBuilder
                    );
                } else {
                    player.sendMessage(ChatColor.RED + "Player was not found!");
                }
            }
        }

        return false;
    }
}
