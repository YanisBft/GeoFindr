package com.yanisbft.geofindr.config;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;

public enum Theme {
    DEFAULT("settings.theme.default"),
    MODERN_LIGHT("settings.theme.light"),
    MODERN_DARK("settings.theme.dark");

    private String translationKey;

    Theme(String translationKey) {
        this.translationKey = translationKey;
    }

    @Override
    public String toString() {
        return I18n.translate(this.translationKey);
    }

    public void apply(Gui gui) {
        try {
            switch (this) {
                case DEFAULT:
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                case MODERN_LIGHT:
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                case MODERN_DARK:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        SwingUtilities.updateComponentTreeUI(gui);
    }

    public static Theme fromName(String name) {
        for (Theme theme : Theme.values()) {
            if (theme.name().equalsIgnoreCase(name)) {
                return theme;
            }
        }

        return null;
    }
}
