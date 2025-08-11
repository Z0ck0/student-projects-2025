package com.testautomation.enums;

public enum SeverityLevel {
    BLOCKER("blocker"),
    CRITICAL("critical"),
    NORMAL("normal"),
    MINOR("minor"),
    TRIVIAL("trivial");

    private final String value;

    SeverityLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
