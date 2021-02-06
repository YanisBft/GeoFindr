package com.yanisbft.geofindr.language;

import com.yanisbft.geofindr.util.I18n;

public class WritingSystem {
    private String name;
    private Type type;
    private Direction direction;

    public WritingSystem(String name, Type type, Direction direction) {
        this.name = name;
        this.type = type;
        this.direction = direction;
    }

    /**
     * Returns the name of this writing system.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of this writing system.
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the direction in which languages using this writing system are written in.
     */
    public Direction getDirection() {
        return direction;
    }

    public enum Type {
        ABJAD,
        ABUGIDA,
        ALPHABETIC,
        LOGOGRAPHIC,
        SYLLABIC
    }

    public enum Direction {
        LEFT_TO_RIGHT("info.writing_direction.ltr"),
        RIGHT_TO_LEFT("info.writing_direction.rtl");

        private String translationKey;

        Direction(String translationKey) {
            this.translationKey = translationKey;
        }

        @Override
        public String toString() {
            return I18n.translate(this.translationKey);
        }
    }
}
