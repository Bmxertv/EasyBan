package de.bmxertv.easyban.tabcompleter;

import de.bmxertv.easyban.EasyBan;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record BanCompleter(EasyBan easyBan) implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> playerNames = Arrays.stream(Bukkit.getOnlinePlayers().toArray(Player[]::new)).map(Player::getName).collect(Collectors.toList());
            Collections.sort(playerNames);
            return playerNames;
        }

        return null;
    }

}
