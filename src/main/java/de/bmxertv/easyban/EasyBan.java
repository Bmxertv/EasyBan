package de.bmxertv.easyban;

import de.bmxertv.easyban.command.BanCommand;
import de.bmxertv.easyban.manager.BanManager;
import de.bmxertv.easyban.model.BanModel;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EasyBan extends JavaPlugin {

    @Override
    public void onEnable() {
        ConsoleUtil.info("------EasyBan------");
        ConsoleUtil.info("Plugin gestartet");
        ConsoleUtil.info("Plugin by: " + getDescription().getAuthors());
        ConsoleUtil.info("Plugin Version: " + getDescription().getVersion());
        ConsoleUtil.info("-------------------");

        Objects.requireNonNull(getCommand("ban")).setExecutor(new BanCommand(this));
        getConfig().options().copyDefaults(true);
        getConfig().options().header("---------\nHello World\n---------");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        ConsoleUtil.info("------EasyBan------");
        ConsoleUtil.info("Plugin gestopt");
        ConsoleUtil.info("Plugin by: " + getDescription().getAuthors());
        ConsoleUtil.info("Plugin Version: " + getDescription().getVersion());
        ConsoleUtil.info("-------------------");
    }
}
