package ru.shariktlt.hackaton2020.order;

public enum TextTypes {
    STATUS_TITLE("statusTitles"),
    STATUS_DESCRIPTION("statusDescription"),
    CLIENT_STATUS_DESCRIPTION("clientStatusDescription"),
    ;

    private String key;

    TextTypes(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
