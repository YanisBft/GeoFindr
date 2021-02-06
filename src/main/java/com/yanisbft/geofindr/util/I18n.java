package com.yanisbft.geofindr.util;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.ClassPath;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class I18n {
    public static final String DEFAULT_LANGUAGE = "en_us";
    private static final Gson GSON = new Gson();
    private static Map<String, String> languageNames = new HashMap<>();
    private static Map<String, String> languageCodes = new HashMap<>();
    private static Map<String, String> defaultTranslations = load(DEFAULT_LANGUAGE);
    private static Map<String, String> translations = defaultTranslations;

    private static Map<String, String> load(String languageCode) {
        try (InputStream inputStream = I18n.class.getResourceAsStream("/lang/" + languageCode + ".json")) {
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    return GSON.fromJson(reader, HashMap.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }

    private static void loadLanguageName(String languageCode) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("lang/" + languageCode + ".json")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                Map<String, String> map = GSON.fromJson(reader, HashMap.class);
                languageNames.put(languageCode, map.get("language"));
                languageCodes.put(map.get("language"), languageCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAvailableLanguages() {
        List<String> languageCodes = new ArrayList<>();

        try {
            ImmutableList<ClassPath.ResourceInfo> resources = ClassPath.from(Thread.currentThread().getContextClassLoader()).getResources().asList();
            Stream<ClassPath.ResourceInfo> stream = resources.stream();
            stream.forEach(f -> {
                String file = f.getResourceName();
                if (file.endsWith(".json")) {
                    String name = file.substring(5, file.length() - 5);
                    languageCodes.add(name);
                    loadLanguageName(name);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return languageCodes;
    }

    public static String translate(String key) {
        String value = translations.get(key);
        if (value != null) return value;

        value = defaultTranslations.get(key);
        if (value != null) return value;

        return key;
    }

    public static String translateFormatted(String key, Object... args) {
        String value = translate(key);
        return String.format(value, args);
    }

    public static String getLanguageName(String languageCode) {
        return languageNames.get(languageCode);
    }

    public static String getLanguageCode(String languageName) {
        return languageCodes.get(languageName);
    }

    public static void setLanguage(String languageCode) {
        translations = load(languageCode);
    }
}
