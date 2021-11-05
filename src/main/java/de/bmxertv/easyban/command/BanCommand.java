package de.bmxertv.easyban.command;

import de.bmxertv.easyban.EasyBan;
import de.bmxertv.easyban.manager.BanManager;
import de.bmxertv.easyban.model.BanModel;
import de.bmxertv.easyban.model.ReasonModel;
import de.bmxertv.easyban.util.ColorUtils;
import de.bmxertv.easyban.util.ConsoleUtil;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public record BanCommand(EasyBan easyBan) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            ConsoleUtil.info("[EasyBan] You must be a Player to use this Command!");
            return true;
        }

        Player player = (Player) sender;

        ConfigurationSection reasonsConfig = easyBan.getConfig().getConfigurationSection("reasons");

        if (args.length <= 1) {
            Set<String> reasonSet = reasonsConfig.getKeys(false);

            player.sendMessage(ColorUtils.colorize("&8&m------------------&4EasyBan&8&m------------------"));
            sendMessage(player, "Use &7/ban <player> <id> &8[&4customReason&8]");
            sendMessage(player, "");
            sendMessage(player, "&8[&4*&8] &7means yo can use a custom reason");

            reasonSet.forEach(id -> {
                ReasonModel reasonModel = ReasonModel.deserialize(reasonsConfig.getConfigurationSection(id));
                String customReason = reasonModel.isCustomReason() ? "&8[&4*&8]" : "";

                ComponentBuilder message = new ComponentBuilder(ColorUtils.colorize(easyBan.PREFIX));
                String hoverableText = "&8[%s]&7 &c%s &8-> &7%s %s".formatted(id, reasonModel.getName(), reasonModel.calculateUntil().format(DateTimeFormatter.ofPattern(easyBan.getConfig().getString("dateTimeFormate"))), customReason);
                String hoverMessage = "/ban <player> %s %s\n&8Join Message:\n%s".formatted(id, customReason, reasonModel.getMessage().stream().collect(Collectors.joining("\n")));
                hoverMessage = hoverMessage.replace('&', '§');
                message
                        .append(ColorUtils.colorize(hoverableText))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hoverMessage)));

                player.spigot().sendMessage(message.create());
            });

            player.sendMessage(ColorUtils.colorize("&8&m-------------------------------------------"));
        } else {
            BanManager banManager = new BanManager();
            Player target = Bukkit.getPlayer(args[0]);
            String id = args[1];
            String reason = Arrays.stream(args).skip(2).collect(Collectors.joining(" "));

            if (target == null || !target.isOnline()) {
                sendMessage(player, "This player is not Online");
                return true;
            }

            if (!reasonsConfig.contains(id)) {
                sendMessage(player, "This reason is currently not available");
                return true;
            }
            ReasonModel reasonModel = ReasonModel.deserialize(reasonsConfig.getConfigurationSection(id));
            BanModel banModel = new BanModel(target.getUniqueId(), player.getName(), id, reason, LocalDateTime.now(), reasonModel.calculateUntil());
            banManager.ban(banModel);
        }
        return true;
    }

    private void sendMessage(Player player, String message) {
        player.sendMessage(ColorUtils.colorize("%s %s".formatted(easyBan.PREFIX, message)));
    }

}
