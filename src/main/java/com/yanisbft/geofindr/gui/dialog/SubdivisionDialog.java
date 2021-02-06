package com.yanisbft.geofindr.gui.dialog;

import com.yanisbft.geofindr.location.Subdivision;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SubdivisionDialog extends JDialog {
    private JPanel contentPanel;

    public SubdivisionDialog(Gui parent, Subdivision subdivision) {
        super(parent, subdivision.getName());
        this.setLayout(new BorderLayout());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    SubdivisionDialog.this.dispose();
                }
            }
        });

        this.contentPanel = new JPanel(new GridLayout(3, 2));
        this.contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.contentPanel.add(new JLabel(I18n.translate("info.name")));
        this.contentPanel.add(new JLabel(subdivision.getName()));

        this.contentPanel.add(new JLabel(I18n.translate("info.code")));
        this.contentPanel.add(new JLabel(subdivision.getCode()));

        this.contentPanel.add(new JLabel(I18n.translate("info.capital")));
        this.contentPanel.add(new JLabel(subdivision.getCapital().getName()));

        this.add(this.contentPanel);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
}
