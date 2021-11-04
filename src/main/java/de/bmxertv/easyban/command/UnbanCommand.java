package de.bmxertv.easyban.command;

import de.bmxertv.easyban.EasyBan;
import de.bmxertv.easyban.manager.BanManager;
import de.bmxertv.easyban.util.ColorUtils;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public record UnbanCommand(EasyBan easyBan) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            ConsoleUtil.info("[EasyBan] You must be a Player to use this Command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ColorUtils.colorize("%s /unban <uuid>".formatted(easyBan.PREFIX)));
            return true;
        }

        BanManager banManager = new BanManager();

        if (!BanManager.isPlayerBanned(UUID.fromString(args[0]))) {
            player.sendMessage(ColorUtils.colorize("%s This player is not Banned".formatted(easyBan.PREFIX)));
            return true;
        }

        banManager.unban(UUID.fromString(args[0]));
        player.sendMessage(ColorUtils.colorize("%s You have unbanned this Player".formatted(easyBan.PREFIX)));

        return true;
    }
}
