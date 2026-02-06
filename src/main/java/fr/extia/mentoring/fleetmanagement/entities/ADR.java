package fr.extia.mentoring.fleetmanagement.entities;

import java.util.Arrays;

public enum ADR {
    FLAMMABLE,
    EXPLOSIVE,
    CORROSIVE,
    RADIOACTIVE;

    public static ADR fromValue(String str) {
        return Arrays.stream(values())
                .filter(value -> value.toString().equalsIgnoreCase(str))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No ADR exists for value: %s".formatted(str)));
    }
}
