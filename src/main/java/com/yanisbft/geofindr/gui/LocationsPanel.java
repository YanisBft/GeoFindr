package com.yanisbft.geofindr.gui;

import com.yanisbft.geofindr.location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LocationsPanel extends JPanel {
    private DefaultListModel<Location> model;
    private JList<Location> locations;

    public LocationsPanel(Gui gui, List<? extends Location> locations) {
        super(new GridLayout());

        this.model = new DefaultListModel<>();
        this.locations = new JList<>(this.model);
        this.locations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2 && e.getButton() == MouseEvent.BUTTON1) {
                    LocationsPanel.this.locations.getSelectedValue().openDialog(gui);
                }
            }
        });
        this.locations.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    LocationsPanel.this.locations.getSelectedValue().openDialog(gui);
                }
            }
        });
        this.add(new JScrollPane(this.locations));

        this.model.addAll(locations);
    }

    public void setLocations(List<? extends Location> locations) {
        this.model.clear();
        this.model.addAll(locations);
    }

    public int getLocationCount() {
        return this.model.size();
    }
}
