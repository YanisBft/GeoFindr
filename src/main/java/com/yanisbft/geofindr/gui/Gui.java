package com.yanisbft.geofindr.gui;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.GeoFindr;
import com.yanisbft.geofindr.util.I18n;
import com.yanisbft.geofindr.util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gui extends JFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 650;

    private SearchPanel searchPanel;
    private LocationsPanel searchResultsPanel;
    private LocationsPanel countriesPanel;
    private LocationsPanel subdivisionsPanel;
    private QuizPanel quizPanel;
    private SettingsPanel settingsPanel;

    private JTabbedPane tabs;

    public Gui() {
        super("GeoFindr");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);

        this.searchPanel = new SearchPanel(this);
        this.searchResultsPanel = new LocationsPanel(this, new ArrayList<>());
        this.countriesPanel = new LocationsPanel(this, DataProvider.ALL_COUNTRIES);
        this.subdivisionsPanel = new LocationsPanel(this, new ArrayList<>());
        this.quizPanel = new QuizPanel(this);
        this.settingsPanel = new SettingsPanel(this);

        this.tabs = new JTabbedPane();
        this.tabs.addTab("", IconUtil.SEARCH, this.searchPanel);
        this.tabs.addTab("", this.searchResultsPanel);
        this.tabs.addTab("", this.countriesPanel);
        this.tabs.addTab("", this.subdivisionsPanel);
        this.tabs.addTab("", IconUtil.QUIZ, this.quizPanel);
        this.tabs.addTab("", IconUtil.SETTINGS, this.settingsPanel);
        this.add(this.tabs);

        GeoFindr.CONFIG.getTheme().apply(this);
        I18n.setLanguage(GeoFindr.CONFIG.getLanguage());
        this.retranslateUi();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public LocationsPanel getSearchResultsPanel() {
        return this.searchResultsPanel;
    }

    public LocationsPanel getSubdivisionsPanel() {
        return this.subdivisionsPanel;
    }

    public QuizPanel getQuizPanel() {
        return this.quizPanel;
    }

    public SettingsPanel getSettingsPanel() {
        return this.settingsPanel;
    }

    public JTabbedPane getTabs() {
        return this.tabs;
    }

    public void setTabsEnabled(boolean enabled) {
        for (int i = 0; i < this.tabs.getTabCount(); i++) {
            this.tabs.setEnabledAt(i, enabled);
        }
    }

    public void retranslateUi() {
        this.tabs.setTitleAt(0, I18n.translate("menu.search"));
        this.tabs.setTitleAt(1, I18n.translateFormatted("menu.search_results", this.searchResultsPanel.getLocationCount()));
        this.tabs.setTitleAt(2, I18n.translateFormatted("menu.all_countries", this.countriesPanel.getLocationCount()));
        this.tabs.setTitleAt(3, I18n.translateFormatted("menu.subdivisions", this.subdivisionsPanel.getLocationCount()));
        this.tabs.setTitleAt(4, I18n.translate("menu.quiz"));
        this.tabs.setTitleAt(5, I18n.translate("menu.settings"));

        this.searchPanel.retranslateUi();
        this.quizPanel.retranslateUi();
        this.settingsPanel.retranslateUi();
    }
}
