package de.bmxertv.easyban.tabcompleter;

import de.bmxertv.easyban.EasyBan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public record UnbanCompleter(EasyBan easyBan) implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        File file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getKeys(false).stream().toList();
    }
}
