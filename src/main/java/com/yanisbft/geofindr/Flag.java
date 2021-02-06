package com.yanisbft.geofindr;

import com.yanisbft.geofindr.location.Country;
import com.yanisbft.geofindr.util.Color;
import com.yanisbft.geofindr.util.IconUtil;

import javax.swing.*;
import java.util.List;

public class Flag {
    private ImageIcon icon;
    private List<Color> colors;

    public Flag(Country country, Color... colors) {
        this.icon = IconUtil.loadFlag(country.getName());
        this.colors = List.of(colors);

        DataProvider.FLAGS_BY_COUNTRY.put(country, this);
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public List<Color> getColors() {
        return this.colors;
    }

    public boolean hasColors(List<Color> colors) {
        return colors.stream().allMatch(color -> this.colors.contains(color));
    }
}
