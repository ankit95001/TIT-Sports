package com.titdevelopercommunity.titsports;

public class HighlightDataClass {
    private final String document;
    private final String description;

    public HighlightDataClass(String document, String description) {
        this.document = document;
        this.description = description;
    }

    public String getDocument() {
        return document;
    }

    public String getDescription() {
        return description;
    }
}
