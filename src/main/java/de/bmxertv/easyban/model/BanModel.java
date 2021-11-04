package de.bmxertv.easyban.model;

import de.bmxertv.easyban.exception.DeserializeException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BanModel {

    private UUID bannedUuid;
    private String from;
    private String reasonId;
    private String reason;
    private LocalDateTime bannedAt;
    private LocalDateTime bannedUntil;

    public BanModel() {
    }

    public BanModel(UUID bannedUuid, String from, String reasonId, String reason, LocalDateTime bannedAt, LocalDateTime bannedUntil) {
        this.bannedUuid = bannedUuid;
        this.from = from;
        this.reasonId = reasonId;
        this.reason = reason;
        this.bannedAt = bannedAt;
        this.bannedUntil = bannedUntil;
    }

    public static BanModel deserialize(ConfigurationSection section) {
        if (section == null || !section.contains("from") || !section.contains("reasonId")|| !section.contains("reason") || !section.contains("at") || !section.contains("until")) {
            try {
                throw new DeserializeException("BanModel", section);
            } catch (DeserializeException e) {
                e.printStackTrace();
            }
        }

        UUID bannedUuid = UUID.fromString(section.getName());
        String from = section.getString("from");
        String reasonId = section.getString("reasonId");
        String reason = section.getString("reason");
        LocalDateTime at = LocalDateTime.parse(section.getString("at"));
        LocalDateTime until = LocalDateTime.parse(section.getString("until"));

        return new BanModel(bannedUuid, from, reasonId, reason, at, until);
    }

    public ConfigurationSection serialize(FileConfiguration configuration) {
        ConfigurationSection section = configuration.createSection(this.bannedUuid.toString());
        section.set("from", this.from);
        section.set("reasonId", this.reasonId);
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
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
                ", from=" + from +
                ", reason='" + reason + '\'' +
                ", bannedAt=" + bannedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")) +
                ", bannedUntil=" + bannedUntil.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")) +
                '}';
    }
}
