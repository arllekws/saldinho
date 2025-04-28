package Entities;

import java.time.LocalDate;

public record Recipe(double value, LocalDate date, String source, String description, String type) {

    @Override
    public String toString() {
        return date + " | R$" + value + " | " + type + " | " + source + " - " + description;
    }
}
