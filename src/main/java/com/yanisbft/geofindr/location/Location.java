package com.yanisbft.geofindr.location;

import com.yanisbft.geofindr.gui.Gui;

public class Location {
    protected String name;
    protected boolean maritime;

    public Location(String name, boolean maritime) {
        this.name = name;
        this.maritime = maritime;
    }

    /**
     * Returns the name of this location.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether this location has access to water.
     */
    public boolean isMaritime() {
        return this.maritime;
    }

    public void openDialog(Gui parent) {
    }

    @Override
    public String toString() {
        return this.name;
    }
}
