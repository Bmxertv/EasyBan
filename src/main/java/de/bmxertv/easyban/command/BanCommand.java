package de.bmxertv.easyban.command;

import de.bmxertv.easyban.EasyBan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public record BanCommand(EasyBan easyBan) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("HELLO WORLD");

        return true;
    }

}
