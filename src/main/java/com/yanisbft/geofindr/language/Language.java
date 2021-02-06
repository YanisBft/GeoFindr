package com.yanisbft.geofindr.language;

import java.util.List;

public class Language {
    private String name;
    private String code;
    private List<WritingSystem> writingSystems;

    public Language(String name, String code, WritingSystem... writingSystems) {
        this.name = name;
        this.code = code;
        this.writingSystems = List.of(writingSystems);
    }

    /**
     * Returns the name of this language.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ISO 639-1 code of this language.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_639-1">ISO 639-1</a>
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the writing systems this language uses.
     */
    public List<WritingSystem> getWritingSystems() {
        return this.writingSystems;
    }
}
