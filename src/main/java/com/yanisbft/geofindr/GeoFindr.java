package com.yanisbft.geofindr;

import com.yanisbft.geofindr.config.Config;
import com.yanisbft.geofindr.gui.Gui;

public class GeoFindr {
    public static final String GITHUB_URL = "https://github.com/YanisBft/GeoFindr";
    public static final Config CONFIG = new Config();

    public static void main(String... args) {
        DataProvider.init();
        new Gui();
    }
}
