package com.testautomation.enums;

public enum TestType {
    SMOKE("@smoke"),
    REGRESSION("@regression"),
    INTEGRATION("@integration"),
    GUI("@gui"),
    NEGATIVE("@negative"),
    POSITIVE("@positive"),
    CRUD("@crud"),
    BOUNDARY("@boundary"),
    STATE_TRANSITION("@state-transition"),
    NO_PROD("@no-prod"),
    UI("@ui"),
    API("@api");

    private final String tag;

    TestType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public String getTagWithoutSymbol() {
        return tag.substring(1);
    }

    public static TestType fromTag(String tag) {
        for (TestType testType : values()) {
            if (testType.tag.equals(tag)) {
                return testType;
            }
        }
        return null;
    }

    public static TestType fromTagWithoutSymbol(String tagWithoutSymbol) {
        return fromTag("@" + tagWithoutSymbol);
    }

    public static boolean isValidTag(String tag) {
        return fromTag(tag) != null;
    }

    public static String[] getAllTags() {
        TestType[] values = values();
        String[] tags = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            tags[i] = values[i].tag;
        }
        return tags;
    }

    public static String[] getAllNames() {
        TestType[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return names;
    }

    @Override
    public String toString() {
        return tag;
    }
}
