package com.yanisbft.geofindr.gui.quiz;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.Flag;
import com.yanisbft.geofindr.gui.Gui;
import com.yanisbft.geofindr.location.Country;
import com.yanisbft.geofindr.quiz.FlagQuiz;
import com.yanisbft.geofindr.quiz.Question;
import com.yanisbft.geofindr.util.I18n;

import javax.swing.*;
import java.awt.*;

public class FlagQuizDialog extends AbstractQuizDialog<Flag, Country> {
    private final JComboBox<Country> answer;

    public FlagQuizDialog(Gui parent, int questionCount) {
        super(parent, I18n.translate("quiz.country_from_flag"), new FlagQuiz(questionCount));

        this.question.setIcon(this.quiz.nextQuestion().getQuestion().getIcon());
        this.questionNumber.setText(I18n.translateFormatted("quiz.question_number", this.quiz.getCurrentQuestionIndex(), this.quiz.getQuestionCount()));

        this.answerPanel.add(new JLabel(I18n.translate("quiz.enter_country")));
        this.answer = new JComboBox<>();
        this.answer.addItem(null);
        DataProvider.ALL_COUNTRIES.forEach(this.answer::addItem);
        this.answerPanel.add(this.answer);
    }

    @Override
    protected boolean isAnswerEqual() {
        return this.quiz.getCurrentQuestion().getAnswer().equals(this.answer.getSelectedItem());
    }

    @Override
    protected void showNextQuestion(Question<Flag, Country> nextQuestion) {
        this.question.setIcon(nextQuestion.getQuestion().getIcon());
        this.questionNumber.setText(I18n.translateFormatted("quiz.question_number", this.quiz.getCurrentQuestionIndex(), this.quiz.getQuestionCount()));
        this.answer.setSelectedIndex(0);
    }

    @Override
    protected void showResults() {
        JPanel resultsPanel = new JPanel(new GridLayout(this.quiz.getQuestionCount(), 1));
        for (int i = 0; i < this.quiz.getQuestionCount(); i++) {
            JCheckBox checkBox = new JCheckBox(this.quiz.getQuestion(i).getAnswer().getName(), this.quiz.getUserAnswer(i));
            checkBox.setEnabled(false);
            resultsPanel.add(checkBox);
        }
        this.questionPanel.add(new JScrollPane(resultsPanel));
    }
}
