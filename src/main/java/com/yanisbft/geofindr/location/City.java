package com.yanisbft.geofindr.location;

public class City {
    private String name;

    public City(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this city.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
