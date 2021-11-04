package de.bmxertv.easyban.model;

import de.bmxertv.easyban.exception.BanModelDeserializeException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BanModel {

    private UUID bannedUuid;
    private UUID fromUuid;
    private String reason;
    private LocalDateTime bannedAt;
    private LocalDateTime bannedUntil;

    public BanModel() {
    }

    public BanModel(UUID bannedUuid, UUID fromUuid, String reason, LocalDateTime bannedAt, LocalDateTime bannedUntil) {
        this.bannedUuid = bannedUuid;
        this.fromUuid = fromUuid;
        this.reason = reason;
        this.bannedAt = bannedAt;
        this.bannedUntil = bannedUntil;
    }

    public static BanModel deserialize(ConfigurationSection section) {
        if (section == null || !section.contains("from") || !section.contains("reason") || !section.contains("at") || !section.contains("until")) {
            try {
                throw new BanModelDeserializeException(section);
            } catch (BanModelDeserializeException e) {
                e.printStackTrace();
            }
        }

        UUID bannedUuid = UUID.fromString(section.getName());
        UUID fromUuid = UUID.fromString(section.getString("from"));
        String reason = section.getString("reason");
        LocalDateTime at = LocalDateTime.parse(section.getString("at"));
        LocalDateTime until = LocalDateTime.parse(section.getString("until"));

        return new BanModel(bannedUuid, fromUuid, reason, at, until);
    }

    public ConfigurationSection serialize(FileConfiguration configuration) {
        ConfigurationSection section = configuration.createSection(this.bannedUuid.toString());
        section.set("from", this.fromUuid.toString());
        section.set("reason", this.reason);
        section.set("at", this.bannedAt.toString());
        section.set("until", this.bannedUntil.toString());
        return section;
    }

    public UUID getBannedUuid() {
        return bannedUuid;
    }

    public void setBannedUuid(UUID bannedUuid) {
        this.bannedUuid = bannedUuid;
    }

    public UUID getFromUuid() {
        return fromUuid;
    }

    public void setFromUuid(UUID fromUuid) {
        this.fromUuid = fromUuid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getBannedAt() {
        return bannedAt;
    }

    public void setBannedAt(LocalDateTime bannedAt) {
        this.bannedAt = bannedAt;
    }

    public LocalDateTime getBannedUntil() {
        return bannedUntil;
    }

    public void setBannedUntil(LocalDateTime bannedUntil) {
        this.bannedUntil = bannedUntil;
    }

    @Override
    public String toString() {
        return "BanModel{" +
                "bannedUuid=" + bannedUuid +
                ", fromUuid=" + fromUuid +
                ", reason='" + reason + '\'' +
                ", bannedAt=" + bannedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")) +
                ", bannedUntil=" + bannedUntil.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")) +
                '}';
    }
}
