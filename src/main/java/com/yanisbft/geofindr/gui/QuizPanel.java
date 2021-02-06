package com.yanisbft.geofindr.gui;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.gui.quiz.CapitalFromCountryQuizDialog;
import com.yanisbft.geofindr.gui.quiz.CcTLDQuizDialog;
import com.yanisbft.geofindr.gui.quiz.CountryFromCapitalQuizDialog;
import com.yanisbft.geofindr.gui.quiz.FlagQuizDialog;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuizPanel extends JPanel {
    private final JPanel optionsPanel;
    private final JPanel buttonsPanel;

    private JLabel questionCountLabel = new JLabel();
    private JSpinner questionCount;

    private JLabel chooseLabel;
    private JButton countryFromCapitalQuizButton;
    private JButton capitalFromCountryQuizButton;
    private JButton flagQuizButton;
    private JButton ccTLDQuizButton;

    public QuizPanel(Gui gui) {
        super(new BorderLayout());

        this.optionsPanel = new JPanel(new GridLayout(1, 2));
        this.optionsPanel.setBorder(new EmptyBorder(10, 200, 10, 200));
        this.optionsPanel.setPreferredSize(new Dimension(Gui.WIDTH, 50));
        this.optionsPanel.add(this.questionCountLabel);
        this.questionCount = new JSpinner(new SpinnerNumberModel(10, 1, DataProvider.FLAGS_BY_COUNTRY.size(), 1));
        this.optionsPanel.add(this.questionCount);

        JPanel contentPanel = new JPanel(new BorderLayout());
        this.chooseLabel = new JLabel("", SwingConstants.CENTER);
        this.chooseLabel.setBorder(new EmptyBorder(50, 0, 10, 0));
        contentPanel.add(this.chooseLabel, BorderLayout.NORTH);

        this.buttonsPanel = new JPanel(new GridLayout(2, 2));
        this.buttonsPanel.setBorder(new EmptyBorder(10, 100, 50, 100));
        contentPanel.add(this.buttonsPanel);

        JPanel capitalQuizPanel = new JPanel(new BorderLayout());
        capitalQuizPanel.setBorder(new EmptyBorder(0, 0, 10, 10));
        this.countryFromCapitalQuizButton = new JButton();
        this.countryFromCapitalQuizButton.addActionListener(action -> this.startCountryFromCapitalQuiz(gui));
        capitalQuizPanel.add(this.countryFromCapitalQuizButton);
        this.buttonsPanel.add(capitalQuizPanel);

        {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(new EmptyBorder(0, 10, 10, 0));
            this.capitalFromCountryQuizButton = new JButton();
            this.capitalFromCountryQuizButton.addActionListener(action -> this.startCapitalFromCountryQuiz(gui));
            panel.add(this.capitalFromCountryQuizButton);
            this.buttonsPanel.add(panel);
        }

        JPanel flagQuizPanel = new JPanel(new BorderLayout());
        flagQuizPanel.setBorder(new EmptyBorder(10, 0, 0, 10));
        this.flagQuizButton = new JButton();
        this.flagQuizButton.addActionListener(action -> this.startFlagQuiz(gui));
        flagQuizPanel.add(this.flagQuizButton);
        this.buttonsPanel.add(flagQuizPanel);

        {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(new EmptyBorder(10, 10, 0, 0));
            this.ccTLDQuizButton = new JButton();
            this.ccTLDQuizButton.addActionListener(action -> this.startCcTLDQuiz(gui));
            panel.add(this.ccTLDQuizButton);
            this.buttonsPanel.add(panel);
        }

        this.add(this.optionsPanel, BorderLayout.NORTH);
        this.add(contentPanel);

        this.retranslateUi();
    }

    private void startCountryFromCapitalQuiz(Gui gui) {
        new CountryFromCapitalQuizDialog(gui, (int) this.questionCount.getValue());
        gui.setTabsEnabled(false);
        this.setButtonsEnabled(false);
    }

    private void startCapitalFromCountryQuiz(Gui gui) {
        new CapitalFromCountryQuizDialog(gui, (int) this.questionCount.getValue());
        gui.setTabsEnabled(false);
        this.setButtonsEnabled(false);
    }

    private void startFlagQuiz(Gui gui) {
        new FlagQuizDialog(gui, (int) this.questionCount.getValue());
        gui.setTabsEnabled(false);
        this.setButtonsEnabled(false);
    }

    private void startCcTLDQuiz(Gui gui) {
        new CcTLDQuizDialog(gui, (int) this.questionCount.getValue());
        gui.setTabsEnabled(false);
        this.setButtonsEnabled(false);
    }

    public void setButtonsEnabled(boolean enabled) {
        this.countryFromCapitalQuizButton.setEnabled(enabled);
        this.capitalFromCountryQuizButton.setEnabled(enabled);
        this.flagQuizButton.setEnabled(enabled);
        this.ccTLDQuizButton.setEnabled(enabled);
    }

    public void retranslateUi() {
        this.questionCountLabel.setText(I18n.translate("quiz.option.question_count"));
        this.chooseLabel.setText(I18n.translate("quiz.choose"));
        this.countryFromCapitalQuizButton.setText(I18n.translate("quiz.country_from_capital"));
        this.capitalFromCountryQuizButton.setText(I18n.translate("quiz.capital_from_country"));
        this.flagQuizButton.setText(I18n.translate("quiz.country_from_flag"));
        this.ccTLDQuizButton.setText(I18n.translate("quiz.country_from_cctld"));
    }
}
