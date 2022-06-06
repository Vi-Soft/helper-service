package com.visoft.helper.service.transport.dto;

public enum Language {
    EN("en-GB"),
    RU("ru-RU"),
    IL("he-IL");

    private final String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
