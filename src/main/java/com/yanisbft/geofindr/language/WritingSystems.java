package com.yanisbft.geofindr.language;

public class WritingSystems {
    public static final WritingSystem LATIN = register("Latin", WritingSystem.Type.ALPHABETIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem CYRILLIC = register("Cyrillic", WritingSystem.Type.ALPHABETIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem GREEK = register("Greek", WritingSystem.Type.ALPHABETIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem ARMENIAN = register("Armenian", WritingSystem.Type.ALPHABETIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem GEORGIAN = register("Georgian", WritingSystem.Type.ALPHABETIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem HANGUL = register("Hangul", WritingSystem.Type.ALPHABETIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem HANZI = register("Hanzi", WritingSystem.Type.LOGOGRAPHIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem KANA = register("Kana", WritingSystem.Type.SYLLABIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem KANJI = register("Kanji", WritingSystem.Type.LOGOGRAPHIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem HANJA = register("Hanja", WritingSystem.Type.LOGOGRAPHIC, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem ARABIC = register("Arabic", WritingSystem.Type.ABJAD, WritingSystem.Direction.RIGHT_TO_LEFT);
    public static final WritingSystem HEBREW = register("Hebrew", WritingSystem.Type.ABJAD, WritingSystem.Direction.RIGHT_TO_LEFT);
    public static final WritingSystem NORTH_INDIC = register("North Indic", WritingSystem.Type.ABUGIDA, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem SOUTH_INDIC = register("South Indic", WritingSystem.Type.ABUGIDA, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem ETHIOPIC = register("Ethiopic", WritingSystem.Type.ABUGIDA, WritingSystem.Direction.LEFT_TO_RIGHT);
    public static final WritingSystem THAANA = register("Thaana", WritingSystem.Type.ABUGIDA, WritingSystem.Direction.RIGHT_TO_LEFT);
    public static final WritingSystem CANADIAN_SYLLABIC = register("Canadian Syllabic", WritingSystem.Type.ABUGIDA, WritingSystem.Direction.LEFT_TO_RIGHT);

    private static WritingSystem register(String name, WritingSystem.Type type, WritingSystem.Direction direction) {
        return new WritingSystem(name, type, direction);
    }
}
