package fr.extia.mentoring.fleetmanagement.entities;

import java.util.Arrays;

public enum TrailerType {
    FLAT,
    TARP,
    FRIGO,
    LIQUID_TANK,
    GAS_TANK,
    FOOD_TANK;

    public static TrailerType fromValue(String str) {
        return Arrays.stream(values())
                .filter(value -> value.toString().equalsIgnoreCase(str))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No TrailerType exists for value: %s".formatted(str)));
    }
}
