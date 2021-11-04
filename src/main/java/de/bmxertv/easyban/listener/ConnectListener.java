package de.bmxertv.easyban.listener;

import de.bmxertv.easyban.EasyBan;
import de.bmxertv.easyban.manager.BanManager;
import de.bmxertv.easyban.util.ColorUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public record ConnectListener(EasyBan easyBan) implements Listener {

    @EventHandler
    public void onPlayerConnect(PlayerLoginEvent event) {

        if (BanManager.isPlayerBanned(event.getPlayer().getUniqueId())) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage(ColorUtils.colorize(BanManager.getBanReason(event.getPlayer().getUniqueId())));
        }

    }

}
