package com.yanisbft.geofindr.gui.dialog;

import com.yanisbft.geofindr.location.Country;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CountryDialog extends JDialog {
    private JPanel flagPanel;
    private JPanel contentPanel;
    private JPanel buttonsPanel;

    public CountryDialog(Gui parent, Country country) {
        super(parent, country.getName());
        this.setLayout(new BorderLayout());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    CountryDialog.this.dispose();
                }
            }
        });

        if (country.getFlag() != null) {
            this.flagPanel = new JPanel();
            this.flagPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
            this.flagPanel.add(new JLabel(country.getFlag().getIcon()));
            this.add(this.flagPanel, BorderLayout.NORTH);
        }

        this.contentPanel = new JPanel(new GridLayout(8, 2));
        this.contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.contentPanel.add(new JLabel(I18n.translate("info.name")));
        this.contentPanel.add(new JLabel(country.getName()));

        this.contentPanel.add(new JLabel(I18n.translate("info.geographic_region")));
        this.contentPanel.add(new JLabel(country.getGeographicRegion().toString()));

        this.contentPanel.add(new JLabel(I18n.translate("info.capital")));
        this.contentPanel.add(new JLabel(country.getCapital().getName()));

        String officialLanguages = country.getOfficialLanguages().get(0).getName();
        for (int i = 1; i < country.getOfficialLanguages().size(); i++) {
            officialLanguages += ", " + country.getOfficialLanguages().get(i).getName();
        }
        this.contentPanel.add(new JLabel(I18n.translate("info.official_languages")));
        this.contentPanel.add(new JLabel(officialLanguages));

        String currency = country.getCurrency().getName() + " (" + country.getCurrency().getCode() + ")";
        this.contentPanel.add(new JLabel(I18n.translate("info.currency")));
        this.contentPanel.add(new JLabel(currency));

        this.contentPanel.add(new JLabel(I18n.translate("info.calling_code")));
        this.contentPanel.add(new JLabel("+" + country.getCallingCode()));

        JLabel ccTLDLabel = new JLabel(I18n.translate("info.cc_tld"));
        ccTLDLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        this.contentPanel.add(ccTLDLabel);
        this.contentPanel.add(new JLabel("." + country.getCcTLD()));

        this.contentPanel.add(new JLabel(I18n.translate("info.driving_side")));
        this.contentPanel.add(new JLabel(country.getDrivingSide().toString()));

        this.buttonsPanel = new JPanel();
        if (country.hasSubdivisions()) {
            JButton statesButton = new JButton(I18n.translate("dialog.subdivisions"));
            statesButton.addKeyListener(this.getKeyListeners()[0]);
            statesButton.addActionListener(action -> {
                this.dispose();
                parent.getSubdivisionsPanel().setLocations(country.getSubdivisions());
                parent.retranslateUi();
                parent.getTabs().setSelectedIndex(3);
            });
            this.buttonsPanel.add(statesButton);
        }
        JButton learnMoreButton = new JButton(I18n.translate("dialog.learn_more"));
        learnMoreButton.addKeyListener(this.getKeyListeners()[0]);
        learnMoreButton.addActionListener(action -> {
            try {
                URI uri = new URI("https://en.wikipedia.org/wiki/" + country.getName().replace(" ", "_"));
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        });
        this.buttonsPanel.add(learnMoreButton);

        this.add(this.contentPanel);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
}
