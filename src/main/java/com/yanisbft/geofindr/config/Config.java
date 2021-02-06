package com.yanisbft.geofindr.config;

import java.util.prefs.Preferences;

public class Config {
    private Preferences prefs;

    public Config() {
        this.prefs = Preferences.userRoot().node("/geofindr");
    }

    public Theme getTheme() {
        return Theme.fromName(this.prefs.get("theme", "DEFAULT"));
    }

    public void setTheme(Theme theme) {
        this.prefs.put("theme", theme.name());
    }

    public String getLanguage() {
        return this.prefs.get("language", "en_us");
    }

    public void setLanguage(String languageCode) {
        this.prefs.put("language", languageCode);
    }

    public boolean shouldRestrictToStreetviewed() {
        return this.prefs.getBoolean("search.streetviewed", false);
    }

    public void setRestrictToStreetviewed(boolean streetviewed) {
        this.prefs.putBoolean("search.streetviewed", streetviewed);
    }
}
