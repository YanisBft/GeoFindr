package com.yanisbft.geofindr.gui.search;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.DrivingSide;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.language.WritingSystem;
import com.yanisbft.geofindr.location.Country;
import com.yanisbft.geofindr.location.GeographicRegion;
import com.yanisbft.geofindr.util.Color;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchCountriesPanel extends AbstractSearchPanel {
    private JLabel capitalLabel = new JLabel();
    private JLabel currencySymbolLabel = new JLabel();
    private JLabel languageLabel = new JLabel();
    private JLabel writingDirectionLabel = new JLabel();
    private JLabel callingCodeLabel = new JLabel();
    private JLabel ccTLDLabel = new JLabel();
    private JLabel geographicRegionLabel = new JLabel();
    private JLabel drivingSideLabel = new JLabel();
    private JLabel flagColorsLabel = new JLabel();

    private JTextField capital;
    private JComboBox<String> currencySymbol;
    private JTextField language;
    private JComboBox<WritingSystem.Direction> writingDirection;
    private JSpinner callingCode;
    private JTextField ccTLD;
    private JComboBox<GeographicRegion> geographicRegion;
    private JCheckBox maritime;
    private JComboBox<DrivingSide> drivingSide;
    private Map<JCheckBox, Color> flagColors;
    private JCheckBox onlySelectedFlagColors;

    public SearchCountriesPanel(Gui gui) {
        super(gui, 6, 2);

        JPanel capitalPanel = new JPanel(new GridLayout(2, 1));
        capitalPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        capitalPanel.add(this.capitalLabel);
        this.capital = new JTextField();
        capitalPanel.add(this.capital);
        this.contentPanel.add(capitalPanel);

        JPanel currencyPanel = new JPanel(new GridLayout(2, 1));
        currencyPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        currencyPanel.add(this.currencySymbolLabel);
        this.currencySymbol = new JComboBox<>();
        this.currencySymbol.addItem(null);
        for (String symbol : DataProvider.ALL_CURRENCY_SYMBOLS.stream().sorted().collect(Collectors.toList())) {
            this.currencySymbol.addItem(symbol);
        }
        currencyPanel.add(this.currencySymbol);
        this.contentPanel.add(currencyPanel);

        JPanel languagePanel = new JPanel(new GridLayout(2, 1));
        languagePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        languagePanel.add(this.languageLabel);
        this.language = new JTextField();
        languagePanel.add(this.language);
        this.contentPanel.add(languagePanel);

        JPanel writingDirectionPanel = new JPanel(new GridLayout(2, 1));
        writingDirectionPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        writingDirectionPanel.add(this.writingDirectionLabel);
        this.writingDirection = new JComboBox<>();
        this.writingDirection.addItem(null);
        this.writingDirection.addItem(WritingSystem.Direction.LEFT_TO_RIGHT);
        this.writingDirection.addItem(WritingSystem.Direction.RIGHT_TO_LEFT);
        writingDirectionPanel.add(this.writingDirection);
        this.contentPanel.add(writingDirectionPanel);

        JPanel callingCodePanel = new JPanel(new GridLayout(2, 1));
        callingCodePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        callingCodePanel.add(this.callingCodeLabel);
        this.callingCode = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        callingCodePanel.add(this.callingCode);
        this.contentPanel.add(callingCodePanel);

        JPanel ccTLDPanel = new JPanel(new GridLayout(2, 1));
        ccTLDPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        ccTLDPanel.add(this.ccTLDLabel);
        this.ccTLD = new JTextField();
        ccTLDPanel.add(this.ccTLD);
        this.contentPanel.add(ccTLDPanel);

        JPanel geographicRegionPanel = new JPanel(new GridLayout(2, 1));
        geographicRegionPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        geographicRegionPanel.add(this.geographicRegionLabel);
        this.geographicRegion = new JComboBox<>();
        this.geographicRegion.addItem(null);
        for (GeographicRegion region : GeographicRegion.values()) {
            this.geographicRegion.addItem(region);
        }
        geographicRegionPanel.add(this.geographicRegion);
        this.contentPanel.add(geographicRegionPanel);

        JPanel maritimePanel = new JPanel(new GridLayout(2, 1));
        maritimePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        this.maritime = new JCheckBox();
        maritimePanel.add(this.maritime);
        this.contentPanel.add(maritimePanel);

        JPanel drivingSidePanel = new JPanel(new GridLayout(2, 1));
        drivingSidePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        drivingSidePanel.add(this.drivingSideLabel);
        this.drivingSide = new JComboBox<>();
        this.drivingSide.addItem(null);
        this.drivingSide.addItem(DrivingSide.LEFT);
        this.drivingSide.addItem(DrivingSide.RIGHT);
        drivingSidePanel.add(this.drivingSide);
        this.contentPanel.add(drivingSidePanel);

        this.contentPanel.add(new JPanel());

        JPanel flagColorsPanel = new JPanel(new GridLayout(2, 1));
        flagColorsPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        JPanel flagTopPanel = new JPanel(new BorderLayout());
        flagTopPanel.add(this.flagColorsLabel);
        this.onlySelectedFlagColors = new JCheckBox();
        flagTopPanel.add(this.onlySelectedFlagColors, BorderLayout.EAST);
        this.flagColors = new HashMap<>();
        JPanel flagRightPanel = new JPanel(new GridLayout(2, 4));
        for (Color color : Color.values()) {
            JCheckBox checkBox = new JCheckBox();
            this.flagColors.put(checkBox, color);
            flagRightPanel.add(checkBox);
        }
        flagColorsPanel.add(flagTopPanel);
        flagColorsPanel.add(flagRightPanel);
        this.contentPanel.add(flagColorsPanel);

        this.retranslateUi();
    }

    @Override
    public void resetFilters() {
        this.capital.setText("");
        this.currencySymbol.setSelectedIndex(0);
        this.language.setText("");
        this.writingDirection.setSelectedIndex(0);
        this.callingCode.setValue(0);
        this.ccTLD.setText("");
        this.geographicRegion.setSelectedIndex(0);
        this.maritime.setSelected(false);
        this.drivingSide.setSelectedIndex(0);
        this.flagColors.keySet().forEach(checkbox -> checkbox.setSelected(false));
        this.onlySelectedFlagColors.setSelected(false);
    }

    @Override
    public void search() {
        Stream<Country> stream = DataProvider.ALL_COUNTRIES.stream();

        if (this.gui.getSettingsPanel().shouldRestrictToStreetviewed()) stream = stream.filter(c -> c.isStreetviewed());

        if (!this.capital.getText().isBlank()) stream = stream.filter(c -> c.getCapital().getName().equalsIgnoreCase(this.capital.getText()));
        if (this.currencySymbol.getSelectedItem() != null) stream = stream.filter(c -> c.getCurrency().getSymbol().equals(this.currencySymbol.getSelectedItem()));
        if (!this.language.getText().isBlank()) stream = stream.filter(c -> c.getOfficialLanguages().stream().anyMatch(l -> l.getName().equalsIgnoreCase(this.language.getText())));
        if (this.writingDirection.getSelectedItem() != null) stream = stream.filter(c -> c.getOfficialLanguages().stream().anyMatch(l -> l.getWritingSystems().stream().anyMatch(w -> w.getDirection().equals(this.writingDirection.getSelectedItem()))));
        if ((int) this.callingCode.getValue() != 0) stream = stream.filter(c -> c.getCallingCode() == (int) this.callingCode.getValue());
        if (!this.ccTLD.getText().isBlank()) stream = stream.filter(c -> c.getCcTLD().equalsIgnoreCase(this.ccTLD.getText()));
        if (this.geographicRegion.getSelectedItem() != null) stream = stream.filter(c -> c.getGeographicRegion().equals(this.geographicRegion.getSelectedItem()));
        if (this.maritime.isSelected()) stream = stream.filter(Country::isMaritime);
        if (this.drivingSide.getSelectedItem() != null) stream = stream.filter(c -> c.getDrivingSide().equals(this.drivingSide.getSelectedItem()));

        List<Color> selectedColors = new ArrayList<>();
        this.flagColors.forEach((checkbox, color) -> {
            if (checkbox.isSelected()) {
                selectedColors.add(color);
            }
        });
        if (!selectedColors.isEmpty()) stream = stream.filter(c -> c.getFlag() == null || ((selectedColors.size() == c.getFlag().getColors().size()) || !this.onlySelectedFlagColors.isSelected()) && c.getFlag().hasColors(selectedColors));

        this.gui.getSearchResultsPanel().setLocations(stream.collect(Collectors.toList()));
        this.gui.retranslateUi();
        this.gui.getTabs().setSelectedIndex(1);
    }

    @Override
    public void retranslateUi() {
        super.retranslateUi();
        this.capitalLabel.setText(I18n.translate("info.capital"));
        this.currencySymbolLabel.setText(I18n.translate("filter.currency_symbol"));
        this.languageLabel.setText(I18n.translate("filter.language"));
        this.writingDirectionLabel.setText(I18n.translate("info.writing_direction"));
        this.callingCodeLabel.setText(I18n.translate("info.calling_code"));
        this.ccTLDLabel.setText(I18n.translate("info.cc_tld"));
        this.geographicRegionLabel.setText(I18n.translate("info.geographic_region"));
        this.maritime.setText(I18n.translate("info.maritime"));
        this.drivingSideLabel.setText(I18n.translate("info.driving_side"));
        this.flagColorsLabel.setText(I18n.translate("filter.flag_colors"));
        this.flagColors.forEach((checkbox, color) -> checkbox.setText(color.toString()));
        this.onlySelectedFlagColors.setText(I18n.translate("filter.only_selected"));
    }
}
