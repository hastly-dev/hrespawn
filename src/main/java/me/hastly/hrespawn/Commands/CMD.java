package me.hastly.hrespawn.Commands;

import me.hastly.hrespawn.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        try {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("hrespawn.reload")){
                    sender.sendMessage(Plugin.getInstance().getConfig().getString("noPermission").replaceAll("&", "§"));
                    return true;
                }

                Plugin.getInstance().reloadConfig();
                sender.sendMessage(Plugin.getInstance().getConfig().getString("reloadMessage").replaceAll("&", "§"));
                return true;
            }

            sender.sendMessage(Plugin.getInstance().getConfig().getString("usageMessage").replaceAll("&", "§") + "/hrespawn reload");
            return true;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage(Plugin.getInstance().getConfig().getString("usageMessage").replaceAll("&", "§") + "/hrespawn reload");
            return true;
        }
    }
}
