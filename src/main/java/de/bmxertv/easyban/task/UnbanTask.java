package de.bmxertv.easyban.task;

import de.bmxertv.easyban.EasyBan;
import de.bmxertv.easyban.manager.BanManager;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UnbanTask extends BukkitRunnable {

    private final EasyBan easyBan;
    private final File file;
    private FileConfiguration fileConfiguration;
    private final BanManager banManager = new BanManager();
    private Set<UUID> uuids;

    public UnbanTask(EasyBan easyBan) {
        this.easyBan = easyBan;
        this.file = Paths.get(easyBan.getDataFolder().getAbsolutePath(), "bans.yml").toFile();


    }


    @Override
    public void run() {

        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
        this.uuids = fileConfiguration.getKeys(false).stream().map(UUID::fromString).collect(Collectors.toSet());
        for (UUID uuid : this.uuids) {
            if (this.banManager.hasBanEnd(uuid)) {
                this.banManager.unban(uuid);
                ConsoleUtil.info("[EasyBan] The UUID %s was automatically unbanned because the ban time is over".formatted(uuid.toString()));
            }
        }

    }

    public void start() {
        this.runTaskTimer(easyBan, 0, 20 * 10);
    }

}
