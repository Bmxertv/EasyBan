package de.bmxertv.easyban;

import de.bmxertv.easyban.command.BanCommand;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyBan extends JavaPlugin {

    @Override
    public void onEnable() {
        ConsoleUtil.info("------EasyBan------");
        ConsoleUtil.info("Plugin gestartet");
        ConsoleUtil.info("Plugin by: " + getDescription().getAuthors());
        ConsoleUtil.info("Plugin Version: " + getDescription().getVersion());
        ConsoleUtil.info("-------------------");

        getCommand("ban").setExecutor(new BanCommand(this));

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
