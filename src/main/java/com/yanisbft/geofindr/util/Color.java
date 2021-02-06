package com.yanisbft.geofindr.util;

public enum Color {
    BLACK("color.black"),
    BLUE("color.blue"),
    GRAY("color.gray"),
    GREEN("color.green"),
    ORANGE("color.orange"),
    RED("color.red"),
    WHITE("color.white"),
    YELLOW("color.yellow");

    private String translationKey;

    Color(String translationKey) {
        this.translationKey = translationKey;
    }

    @Override
    public String toString() {
        return I18n.translate(this.translationKey);
    }
}
