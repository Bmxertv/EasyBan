package de.bmxertv.easyban.exception;

import org.bukkit.configuration.ConfigurationSection;

public class BanModelDeserializeException extends Exception {

    public BanModelDeserializeException(ConfigurationSection section) {
        super("The BanModel {0} can't deserialize because the Model is not Complete".formatted(section.getName()));
    }
}
