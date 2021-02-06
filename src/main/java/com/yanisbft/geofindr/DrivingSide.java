package com.yanisbft.geofindr;

import com.yanisbft.geofindr.util.I18n;

public enum DrivingSide {
    LEFT("info.driving_side.left"),
    RIGHT("info.driving_side.right");

    private String translationKey;

    DrivingSide(String translationKey) {
        this.translationKey = translationKey;
    }

    @Override
    public String toString() {
        return I18n.translate(this.translationKey);
    }
}
