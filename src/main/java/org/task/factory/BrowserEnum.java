package org.task.factory;

import java.util.Arrays;

public enum BrowserEnum {
    CHROME("CHROME"),
    FIREFOX("FIREFOX"),
    DEFAULT("DEFAULT");

    private static BrowserEnum[] values = BrowserEnum.values();
    BrowserEnum(String value) {
        this.value = value;
    }
    private final String value;

    public static BrowserEnum of(String value) {
        if(!existsByValue(value)) {
            return DEFAULT;
        }
        return BrowserEnum.valueOf(value.toUpperCase());
    }

    public static boolean existsByValue(String value) {
        return Arrays.stream(values).anyMatch(n -> n.value.equalsIgnoreCase(value));
    }

    public String getValue() {
        return this.value;
    }
}
