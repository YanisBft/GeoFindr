package com.yanisbft.geofindr.gui.search;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.location.Country;
import com.yanisbft.geofindr.location.Subdivision;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchSubdivisionsPanel extends AbstractSearchPanel {
    private JLabel countryLabel = new JLabel();
    private JLabel capitalLabel = new JLabel();

    private JComboBox<Country> country;
    private JTextField capital;
    private JCheckBox maritime;

    public SearchSubdivisionsPanel(Gui gui) {
        super(gui, 6, 1);

        JPanel countryPanel = new JPanel(new GridLayout(2, 1));
        countryPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        countryPanel.add(this.countryLabel);
        this.country = new JComboBox<>();
        this.country.addItem(null);
        for (Country country : DataProvider.ALL_COUNTRIES.stream().filter(Country::hasSubdivisions).collect(Collectors.toList())) {
            this.country.addItem(country);
        }
        countryPanel.add(this.country);
        this.contentPanel.add(countryPanel);

        JPanel capitalPanel = new JPanel(new GridLayout(2, 1));
        capitalPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        capitalPanel.add(this.capitalLabel);
        this.capital = new JTextField();
        capitalPanel.add(this.capital);
        this.contentPanel.add(capitalPanel);

        JPanel maritimePanel = new JPanel(new GridLayout(2, 1));
        maritimePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        this.maritime = new JCheckBox();
        maritimePanel.add(this.maritime);
        this.contentPanel.add(maritimePanel);

        this.retranslateUi();
    }

    @Override
    public void resetFilters() {
        this.country.setSelectedIndex(0);
        this.capital.setText("");
        this.maritime.setSelected(false);
    }

    @Override
    public void search() {
        Stream<Subdivision> stream = DataProvider.SUBDIVISIONS.values().stream().sorted(Comparator.comparing(Subdivision::getName));

        if (this.country.getSelectedItem() != null) stream = stream.filter(s -> DataProvider.SUBDIVISIONS.get((Country) this.country.getSelectedItem()).contains(s));
        if (!this.capital.getText().isBlank()) stream = stream.filter(s -> s.getCapital().getName().equalsIgnoreCase(this.capital.getText()));
        if (this.maritime.isSelected()) stream = stream.filter(Subdivision::isMaritime);

        this.gui.getSearchResultsPanel().setLocations(stream.collect(Collectors.toList()));
        this.gui.retranslateUi();
        this.gui.getTabs().setSelectedIndex(1);
    }

    @Override
    public void retranslateUi() {
        super.retranslateUi();
        this.countryLabel.setText(I18n.translate("filter.country"));
        this.capitalLabel.setText(I18n.translate("info.capital"));
        this.maritime.setText(I18n.translate("info.maritime"));
    }
}
