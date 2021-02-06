package com.yanisbft.geofindr.location;

import com.yanisbft.geofindr.*;
import com.yanisbft.geofindr.gui.dialog.CountryDialog;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.language.Language;

import java.util.ArrayList;
import java.util.List;

public class Country extends Location {
    private Builder builder;
    private List<City> cities;

    private Country(Builder builder) {
        super(builder.name, builder.maritime);
        this.builder = builder;
        this.cities = List.of(builder.capital);

        DataProvider.ALL_COUNTRIES.add(this);
    }

    /**
     * Returns the geographic region of this country.
     */
    public GeographicRegion getGeographicRegion() {
        return this.builder.geographicRegion;
    }

    /**
     * Returns the capital of this country.
     */
    public City getCapital() {
        return this.builder.capital;
    }

    public List<City> getCities() {
        return this.cities;
    }

    /**
     * Returns the official languages of this country.
     */
    public List<Language> getOfficialLanguages() {
        return this.builder.officialLanguages;
    }

    /**
     * Returns the currency of this country.
     */
    public Currency getCurrency() {
        return this.builder.currency;
    }

    /**
     * Returns the calling code of this country.
     */
    public int getCallingCode() {
        return this.builder.callingCode;
    }

    /**
     * Returns the country code top-level domain of this country.
     */
    public String getCcTLD() {
        return this.builder.ccTLD;
    }

    /**
     * Returns the driving side of this country.
     */
    public DrivingSide getDrivingSide() {
        return this.builder.drivingSide;
    }

    /**
     * Returns whether this country is streetviewed, fully or partially.
     */
    public boolean isStreetviewed() {
        return this.builder.streetviewCoverage.equals(StreetviewCoverage.FULL) || this.builder.streetviewCoverage.equals(StreetviewCoverage.LIMITED);
    }

    /**
     * Returns the flag of this country.
     */
    public Flag getFlag() {
        return DataProvider.FLAGS_BY_COUNTRY.get(this);
    }

    /**
     * Returns whether this country has subdivisions.
     */
    public boolean hasSubdivisions() {
        return !DataProvider.SUBDIVISIONS.get(this).isEmpty();
    }

    /**
     * Returns the subdivisions of this country.
     */
    public List<Subdivision> getSubdivisions() {
        if (this.hasSubdivisions()) {
            return new ArrayList<>(DataProvider.SUBDIVISIONS.get(this));
        }

        return null;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    @Override
    public void openDialog(Gui parent) {
        new CountryDialog(parent, this);
    }

    public static class Builder {
        private String name;
        private GeographicRegion geographicRegion;
        private City capital;
        private List<Language> officialLanguages;
        private Currency currency;
        private int callingCode;
        private String ccTLD;
        private DrivingSide drivingSide;
        private boolean maritime;
        private StreetviewCoverage streetviewCoverage;

        public Builder(String name, GeographicRegion geographicRegion, City capital, Currency currency, int callingCode, String ccTLD, StreetviewCoverage streetviewCoverage) {
            this(name, geographicRegion, capital, currency, callingCode, ccTLD, DrivingSide.RIGHT, streetviewCoverage);
        }

        public Builder(String name, GeographicRegion geographicRegion, City capital, Currency currency, int callingCode, String ccTLD, DrivingSide drivingSide, StreetviewCoverage streetviewCoverage) {
            this.name = name;
            this.geographicRegion = geographicRegion;
            this.capital = capital;
            this.currency = currency;
            this.callingCode = callingCode;
            this.ccTLD = ccTLD;
            this.drivingSide = drivingSide;
            this.streetviewCoverage = streetviewCoverage;
        }


        public Builder officialLanguages(Language... languages) {
            this.officialLanguages = List.of(languages);
            return this;
        }

        public Builder maritime() {
            this.maritime = true;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }
}
