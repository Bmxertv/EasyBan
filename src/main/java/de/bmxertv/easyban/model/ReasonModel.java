package de.bmxertv.easyban.model;

import de.bmxertv.easyban.exception.DeserializeException;
import org.bukkit.configuration.ConfigurationSection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReasonModel {

    private String name;
    private boolean customReason;
    private List<String> message;
    private int seconds;
    private int minutes;
    private int hours;
    private int days;
    private int weeks;
    private int months;
    private int years;

    public ReasonModel() {
    }

    public ReasonModel(String name, boolean customReason, List<String> message, int seconds, int minutes, int hours, int days, int weeks, int months, int years) {
        this.name = name;
        this.customReason = customReason;
        this.message = message;
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
        this.weeks = weeks;
        this.months = months;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCustomReason() {
        return customReason;
    }

    public void setCustomReason(boolean customReason) {
        this.customReason = customReason;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public static ReasonModel deserialize(ConfigurationSection section) {
        if (section == null || !section.contains("name") || !section.contains("message") || !section.contains("time")) {
            try {
                throw new DeserializeException("ReasonMode", section);
            } catch (DeserializeException e) {
                e.printStackTrace();
            }
        }

        String name = section.getString("name");
        boolean customReason = section.contains("customReason") ? section.getBoolean("customReason") : false;
        List<String> message = section.getStringList("message");
        int seconds = section.getInt("seconds");
        int minutes = section.getInt("minutes");
        int hours = section.getInt("hours");
        int days = section.getInt("days");
        int weeks = section.getInt("weeks");
        int months = section.getInt("months");
        int years = section.getInt("years");
        return new ReasonModel(name, customReason, message, seconds, minutes, hours, days, weeks, months, years);
    }

    public LocalDateTime calculateUntil() {
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.plusSeconds(this.seconds);
        dateTime.plusMinutes(this.minutes);
        dateTime.plusHours(this.hours);
        dateTime.plusDays(this.days);
        dateTime.plusWeeks(this.weeks);
        dateTime.plusMonths(this.months);
        dateTime.plusYears(this.years);
        return dateTime;
    }
}
