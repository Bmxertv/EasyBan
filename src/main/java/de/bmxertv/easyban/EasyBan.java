package de.bmxertv.easyban;

import de.bmxertv.easyban.command.BanCommand;
import de.bmxertv.easyban.command.UnbanCommand;
import de.bmxertv.easyban.listener.ConnectListener;
import de.bmxertv.easyban.tabcompleter.BanCompleter;
import de.bmxertv.easyban.tabcompleter.UnbanCompleter;
import de.bmxertv.easyban.task.UnbanTask;
import de.bmxertv.easyban.util.ConsoleUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class EasyBan extends JavaPlugin {

    public final String PREFIX = "&7[&cEasyBan&7]";
    private UnbanTask unbanTask;

    @Override
    public void onEnable() {
        ConsoleUtil.info("------EasyBan------");
        ConsoleUtil.info("Plugin gestartet");
        ConsoleUtil.info("Plugin by: " + getDescription().getAuthors());
        ConsoleUtil.info("Plugin Version: " + getDescription().getVersion());
        ConsoleUtil.info("-------------------");

        this.unbanTask = new UnbanTask(this);
        this.unbanTask.start();

        Objects.requireNonNull(getCommand("ban")).setExecutor(new BanCommand(this));
        Objects.requireNonNull(getCommand("ban")).setTabCompleter(new BanCompleter(this));
        Objects.requireNonNull(getCommand("unban")).setExecutor(new UnbanCommand(this));
        Objects.requireNonNull(getCommand("unban")).setTabCompleter(new UnbanCompleter(this));
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        ConsoleUtil.info("------EasyBan------");
        ConsoleUtil.info("Plugin gestopt");
        ConsoleUtil.info("Plugin by: " + getDescription().getAuthors());
        ConsoleUtil.info("Plugin Version: " + getDescription().getVersion());
        ConsoleUtil.info("-------------------");

        this.unbanTask.cancel();
    }
}
