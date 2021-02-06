package com.yanisbft.geofindr.location;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.gui.dialog.SubdivisionDialog;

import java.util.List;

public class Subdivision extends Location {
    private Type type;
    private String code;
    private City capital;
    private List<City> cities;

    public Subdivision(Country country, String name, Type type, String code, City capital) {
        this(country, name, type, code, capital, false);
    }

    public Subdivision(Country country, String name, Type type, String code, City capital, boolean maritime) {
        super(name, maritime);
        this.type = type;
        this.code = code;
        this.capital = capital;
        this.cities = List.of(capital);

        DataProvider.SUBDIVISIONS.put(country, this);
    }

    /**
     * Returns the type of this subdivision.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Returns the ISO 3166-2 code of this subdivision.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-2">ISO 3166-2</a>
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the capital of this subdivision.
     */
    public City getCapital() {
        return this.capital;
    }

    public List<City> getCities() {
        return this.cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    @Override
    public void openDialog(Gui parent) {
        new SubdivisionDialog(parent, this);
    }

    public enum Type {
        AUTONOMOUS_CITY,
        AUTONOMOUS_COMMUNITY,
        COUNTY,
        PROVINCE,
        REGION,
        STATE,
        TERRITORY
    }
}
