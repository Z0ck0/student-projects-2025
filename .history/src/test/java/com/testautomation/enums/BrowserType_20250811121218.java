package com.testautomation.enums;

public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    SAFARI("safari");

    private final String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BrowserType fromString(String text) {
        for (BrowserType browserType : BrowserType.values()) {
            if (browserType.value.equalsIgnoreCase(text)) {
                return browserType;
            }
        }
        throw new IllegalArgumentException("No browser type found with value: " + text);
    }
}
