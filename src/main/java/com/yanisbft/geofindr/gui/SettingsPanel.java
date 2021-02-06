package com.yanisbft.geofindr.gui;

import com.yanisbft.geofindr.GeoFindr;
import com.yanisbft.geofindr.config.Theme;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SettingsPanel extends JPanel {
    private JPanel contentPanel;;
    private TitledBorder viewBorder;
    private TitledBorder searchBorder;

    private JLabel themeLabel = new JLabel();
    private JLabel languageLabel = new JLabel();

    private JComboBox<Theme> theme;
    private JComboBox<String> language;

    private JCheckBox restrictStreetviewed;

    private JPanel buttonsPanel;
    private JButton githubButton;
    private JButton saveSettingsButton;

    public SettingsPanel(Gui gui) {
        super(new BorderLayout());

        this.contentPanel = new JPanel(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(20, 20, 280, 20));

        JPanel viewPanel = new JPanel(new GridLayout(2, 1));
        this.viewBorder = new TitledBorder("");
        viewPanel.setBorder(this.viewBorder);

        JPanel themePanel = new JPanel(new GridLayout(1, 2));
        themePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        themePanel.add(this.themeLabel);
        this.theme = new JComboBox<>(Theme.values());
        themePanel.add(this.theme);
        viewPanel.add(themePanel);

        JPanel languagePanel = new JPanel(new GridLayout(1, 2));
        languagePanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        languagePanel.add(this.languageLabel);
        this.language = new JComboBox<>();
        for (String l : I18n.getAvailableLanguages()) {
            this.language.addItem(I18n.getLanguageName(l));
        }
        languagePanel.add(this.language);
        viewPanel.add(languagePanel);

        JPanel searchPanel = new JPanel(new GridLayout(1, 1));
        this.searchBorder = new TitledBorder("");
        searchPanel.setBorder(this.searchBorder);

        JPanel restrictStreetviewedPanel = new JPanel();
        restrictStreetviewedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.restrictStreetviewed = new JCheckBox();
        restrictStreetviewedPanel.add(this.restrictStreetviewed);
        searchPanel.add(restrictStreetviewedPanel);

        this.buttonsPanel = new JPanel(new BorderLayout());
        this.buttonsPanel.setPreferredSize(new Dimension(Gui.WIDTH, 50));
        this.buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.githubButton = new JButton();
        this.githubButton.addActionListener(action -> {
            try {
                URI uri = new URI(GeoFindr.GITHUB_URL);
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        });
        this.buttonsPanel.add(this.githubButton, BorderLayout.WEST);

        this.saveSettingsButton = new JButton();
        this.saveSettingsButton.addActionListener(action -> {
            ((Theme) this.theme.getSelectedItem()).apply(gui);
            I18n.setLanguage(I18n.getLanguageCode(this.language.getSelectedItem().toString()));
            gui.retranslateUi();
            this.saveConfig();
        });
        this.buttonsPanel.add(this.saveSettingsButton, BorderLayout.EAST);

        this.contentPanel.add(viewPanel);
        this.contentPanel.add(searchPanel, BorderLayout.SOUTH);
        this.add(this.contentPanel);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);

        this.loadConfig();
        this.retranslateUi();
    }

    public boolean shouldRestrictToStreetviewed() {
        return this.restrictStreetviewed.isSelected();
    }

    private void loadConfig() {
        this.theme.setSelectedItem(GeoFindr.CONFIG.getTheme());
        this.language.setSelectedItem(I18n.getLanguageName(GeoFindr.CONFIG.getLanguage()));
        this.restrictStreetviewed.setSelected(GeoFindr.CONFIG.shouldRestrictToStreetviewed());
    }

    private void saveConfig() {
        GeoFindr.CONFIG.setTheme((Theme) this.theme.getSelectedItem());
        GeoFindr.CONFIG.setLanguage(I18n.getLanguageCode(this.language.getSelectedItem().toString()));
        GeoFindr.CONFIG.setRestrictToStreetviewed(this.restrictStreetviewed.isSelected());
    }

    public void retranslateUi() {
        this.viewBorder.setTitle(I18n.translate("settings.view"));
        this.searchBorder.setTitle(I18n.translate("menu.search"));
        this.themeLabel.setText(I18n.translate("settings.theme"));
        this.languageLabel.setText(I18n.translate("settings.language"));
        this.restrictStreetviewed.setText(I18n.translate("settings.search.streetviewed"));
        this.githubButton.setText(I18n.translate("settings.github"));
        this.saveSettingsButton.setText(I18n.translate("settings.save"));
    }
}
