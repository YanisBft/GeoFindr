package com.yanisbft.geofindr.gui;

import com.yanisbft.geofindr.gui.search.SearchCountriesPanel;
import com.yanisbft.geofindr.gui.search.SearchSubdivisionsPanel;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private JTabbedPane tabs;

    private SearchCountriesPanel countriesPanel;
    private SearchSubdivisionsPanel subdivisionsPanel;

    public SearchPanel(Gui gui) {
        super(new BorderLayout());
        this.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), new TitledBorder("")));

        this.countriesPanel = new SearchCountriesPanel(gui);
        this.subdivisionsPanel = new SearchSubdivisionsPanel(gui);

        this.tabs = new JTabbedPane();
        this.tabs.addTab("", this.countriesPanel);
        this.tabs.addTab("", this.subdivisionsPanel);
        this.add(this.tabs);

        this.retranslateUi();
    }

    public void retranslateUi() {
        this.tabs.setTitleAt(0, I18n.translate("search.countries"));
        this.tabs.setTitleAt(1, I18n.translate("search.subdivisions"));

        this.countriesPanel.retranslateUi();
        this.subdivisionsPanel.retranslateUi();
    }
}
