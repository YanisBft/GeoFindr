package com.yanisbft.geofindr.location;

import com.yanisbft.geofindr.util.I18n;

public enum GeographicRegion {
    AUSTRALIA_AND_NEW_ZEALAND("info.geographic_region.australia_and_new_zealand"),
    CARIBBEAN("info.geographic_region.caribbean"),
    CENTRAL_AFRICA("info.geographic_region.central_africa"),
    CENTRAL_AMERICA("info.geographic_region.central_america"),
    CENTRAL_ASIA("info.geographic_region.central_asia"),
    EASTERN_AFRICA("info.geographic_region.eastern_africa"),
    EASTERN_ASIA("info.geographic_region.eastern_asia"),
    EASTERN_EUROPE("info.geographic_region.eastern_europe"),
    MELANESIA("info.geographic_region.melanesia"),
    MICRONESIA("info.geographic_region.micronesia"),
    NORTHERN_AFRICA("info.geographic_region.northern_africa"),
    NORTHERN_AMERICA("info.geographic_region.northern_america"),
    NORTHERN_EUROPE("info.geographic_region.northern_europe"),
    POLYNESIA("info.geographic_region.polynesia"),
    SOUTH_AMERICA("info.geographic_region.south_america"),
    SOUTHEASTERN_ASIA("info.geographic_region.southeastern_asia"),
    SOUTHERN_AFRICA("info.geographic_region.southern_africa"),
    SOUTHERN_ASIA("info.geographic_region.southern_asia"),
    SOUTHERN_EUROPE("info.geographic_region.southern_europe"),
    WESTERN_AFRICA("info.geographic_region.western_africa"),
    WESTERN_ASIA("info.geographic_region.western_asia"),
    WESTERN_EUROPE("info.geographic_region.western_europe");

    private String translationKey;

    GeographicRegion(String translationKey) {
        this.translationKey = translationKey;
    }

    @Override
    public String toString() {
        return I18n.translate(this.translationKey);
    }
}
