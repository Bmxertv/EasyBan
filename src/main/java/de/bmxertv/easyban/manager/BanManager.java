package de.bmxertv.easyban.manager;

import de.bmxertv.easyban.EasyBan;
import de.bmxertv.easyban.model.BanModel;
import de.bmxertv.easyban.model.ReasonModel;
import de.bmxertv.easyban.util.ColorUtils;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

public class BanManager {

    private final EasyBan easyBan = EasyBan.getPlugin(EasyBan.class);
    private final File file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();

    public static boolean isPlayerBanned(UUID uuid) {
        EasyBan easyBan = EasyBan.getPlugin(EasyBan.class);
        File file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getConfigurationSection(uuid.toString()) != null;
    }

    public boolean hasBanEnd(UUID uuid) {
        BanModel model = getBanned(uuid);
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(model.getBannedUntil()) || now.isEqual(model.getBannedUntil());
    }

    public static String getBanReason(UUID uuid) {
        EasyBan easyBan = EasyBan.getPlugin(EasyBan.class);
        File file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        BanModel banModel = BanModel.deserialize(fileConfiguration.getConfigurationSection(uuid.toString()));
        ReasonModel reasonModel = ReasonModel.deserialize(easyBan.getConfig().getConfigurationSection("reasons." + banModel.getReasonId()));

        String reason = reasonModel.getMessage()
                .stream()
                .collect(Collectors.joining("\n"))
                .replace("%reason%", banModel.getReason())
                .replace("%from%", banModel.getFrom())
                .replace("%until%", banModel.getBannedUntil().format(DateTimeFormatter.ofPattern(easyBan.getConfig().getString("dateTimeFormate"))));
        return reason;
    }

    public void ban(BanModel model) {
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

        ReasonModel reasonModel = ReasonModel.deserialize(easyBan.getConfig().getConfigurationSection("reasons." + model.getReasonId()));

        String reason = reasonModel.getMessage()
                .stream()
                .collect(Collectors.joining("\n"))
                .replace("%reason%", model.getReason())
                .replace("%from%", model.getFrom())
                .replace("%until%", model.getBannedUntil().format(DateTimeFormatter.ofPattern(easyBan.getConfig().getString("dateTimeFormate"))));

        Bukkit.getPlayer(model.getBannedUuid()).kickPlayer(ColorUtils.colorize(reason));
    }

    public void unban(UUID uuid) {
        if (!file.exists()) {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = fileConfiguration.getConfigurationSection(uuid.toString());
        if (section == null) {
            ConsoleUtil.error("UUID can't unbanned because she doesn't exists");
            return;
        }

        for (String key : section.getKeys(true)) {
            section.set(key, null);
        }
        fileConfiguration.set(uuid.toString(), null);
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