package com.yanisbft.geofindr.gui.search;

import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractSearchPanel extends JPanel {
    protected Gui gui;
    protected JPanel contentPanel;
    private JPanel buttonsPanel;
    private JButton resetFiltersButton;
    private JButton searchButton;

    public AbstractSearchPanel(Gui gui, int rows, int columns) {
        super(new BorderLayout());
        this.gui = gui;

        this.contentPanel = new JPanel(new GridLayout(rows, columns));
        this.contentPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        this.buttonsPanel = new JPanel(new BorderLayout());
        this.buttonsPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        this.resetFiltersButton = new JButton();
        this.resetFiltersButton.addActionListener(action -> this.resetFilters());
        this.buttonsPanel.add(this.resetFiltersButton, BorderLayout.WEST);

        this.searchButton = new JButton();
        this.searchButton.addActionListener(action -> this.search());
        this.buttonsPanel.add(this.searchButton, BorderLayout.EAST);

        this.add(this.contentPanel);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);
    }

    public abstract void resetFilters();

    public abstract void search();

    public void retranslateUi() {
        this.resetFiltersButton.setText(I18n.translate("search.reset_filters"));
        this.searchButton.setText(I18n.translate("search"));
    }
}
