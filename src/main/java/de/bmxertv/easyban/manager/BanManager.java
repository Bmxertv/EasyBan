package de.bmxertv.easyban.manager;

import de.bmxertv.easyban.EasyBan;
import de.bmxertv.easyban.model.BanModel;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class BanManager {

    private final EasyBan easyBan = EasyBan.getPlugin(EasyBan.class);
    private final File file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();

    public static boolean isPlayerBanned(UUID uuid) {
        EasyBan easyBan = EasyBan.getPlugin(EasyBan.class);
        File file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getConfigurationSection(uuid.toString()) != null;
    }

    public void saveBan(BanModel model) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                ConsoleUtil.error("File bans.yml can't create");
            }
        }

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        model.serialize(fileConfiguration);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            ConsoleUtil.error("File bans.yml can't save");
        }
    }

    public BanModel getBanned(UUID bannedUuid) {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return BanModel.deserialize(fileConfiguration.getConfigurationSection(bannedUuid.toString()));
    }

}