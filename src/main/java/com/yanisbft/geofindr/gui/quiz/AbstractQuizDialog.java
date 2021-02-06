package com.yanisbft.geofindr.gui.quiz;

import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.quiz.AbstractQuiz;
import com.yanisbft.geofindr.quiz.Question;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AbstractQuizDialog<Q, A> extends JDialog {
    protected JPanel numberPanel;
    protected JPanel questionPanel;
    protected JPanel answerPanel;
    protected JPanel buttonsPanel;

    protected JLabel questionNumber;
    protected JLabel question;
    protected JButton continueButton;

    protected AbstractQuiz<Q, A> quiz;

    public AbstractQuizDialog(Gui parent, String title, AbstractQuiz<Q, A> quiz) {
        super(parent, title);
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);

        this.quiz = quiz;

        this.numberPanel = new JPanel();
        this.numberPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        this.questionNumber = new JLabel();
        this.numberPanel.add(this.questionNumber);

        this.questionPanel = new JPanel(new GridLayout(2, 1));
        this.questionPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        this.question = new JLabel();
        this.question.setHorizontalAlignment(SwingConstants.CENTER);
        this.questionPanel.add(this.question);

        this.answerPanel = new JPanel(new GridLayout(2, 1));
        this.answerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.questionPanel.add(this.answerPanel);

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.continueButton = new JButton(I18n.translate("quiz.continue"));
        this.continueButton.addActionListener(action -> this.continueQuiz());
        this.buttonsPanel.add(this.continueButton);

        this.add(this.numberPanel, BorderLayout.NORTH);
        this.add(this.questionPanel);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setTabsEnabled(true);
                parent.getQuizPanel().setButtonsEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                parent.setTabsEnabled(true);
                parent.getQuizPanel().setButtonsEnabled(true);
            }
        });

        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void continueQuiz() {
        if (this.isAnswerEqual()) {
            this.quiz.incrementScore();
            this.quiz.setUserAnswer(true);
        } else {
            this.quiz.setUserAnswer(false);
        }

        Question<Q, A> nextQuestion = this.quiz.nextQuestion();
        if (nextQuestion != null) {
            this.showNextQuestion(nextQuestion);
        } else {
            this.questionNumber.setText(I18n.translateFormatted("quiz.score", this.quiz.getScore(), this.quiz.getQuestionCount()));
            this.clearQuestionPanel();
            this.showResults();
            this.continueButton.removeActionListener(this.continueButton.getActionListeners()[0]);
            this.continueButton.addActionListener(a -> this.dispose());
        }
    }

    private void clearQuestionPanel() {
        this.questionPanel.removeAll();
        this.questionPanel.repaint();
        this.questionPanel.setLayout(new GridLayout());
    }

    protected abstract boolean isAnswerEqual();

    protected abstract void showNextQuestion(Question<Q, A> nextQuestion);

    protected abstract void showResults();
}
