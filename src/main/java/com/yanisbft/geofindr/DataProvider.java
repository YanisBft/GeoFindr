package com.yanisbft.geofindr;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.yanisbft.geofindr.data.Countries;
import com.yanisbft.geofindr.data.Flags;
import com.yanisbft.geofindr.data.Subdivisions;
import com.yanisbft.geofindr.location.Country;
import com.yanisbft.geofindr.location.Subdivision;

import java.util.*;

public class DataProvider {
    public static final List<Country> ALL_COUNTRIES = new ArrayList<>();
    public static final Set<String> ALL_CURRENCY_SYMBOLS = new HashSet<>();
    public static final Map<Country, Flag> FLAGS_BY_COUNTRY = new HashMap<>();
    public static final Multimap<Country, Subdivision> SUBDIVISIONS = ArrayListMultimap.create();

    public static void init() {
        new Countries();
        new Flags();
        new Subdivisions();
    }
}
