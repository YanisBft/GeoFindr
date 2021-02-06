package com.yanisbft.geofindr.quiz;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQuiz<Q, A> {
    protected int questionCount;
    protected List<Question<Q, A>> questions;
    protected List<Boolean> userAnswers;
    protected int currentQuestionIndex;
    protected int score;

    public AbstractQuiz(int questionCount) {
        this.questionCount = questionCount;
        this.questions = this.createRandomQuestions();
        this.userAnswers = new ArrayList<>();
    }

    protected abstract List<Question<Q, A>> createRandomQuestions();

    public Question<Q, A> nextQuestion() {
        if (this.currentQuestionIndex < this.questionCount) {
            this.currentQuestionIndex++;
            return this.getCurrentQuestion();
        }

        return null;
    }

    public void incrementScore() {
        this.score++;
    }

    public int getQuestionCount() {
        return this.questionCount;
    }

    public int getCurrentQuestionIndex() {
        return this.currentQuestionIndex;
    }

    public int getScore() {
        return this.score;
    }

    public Question<Q, A> getCurrentQuestion() {
        return this.questions.get(this.currentQuestionIndex - 1);
    }

    public Question<Q, A> getQuestion(int index) {
        return this.questions.get(index);
    }

    public Boolean getUserAnswer(int index) {
        return this.userAnswers.get(index);
    }

    public void setUserAnswer(boolean answer) {
        this.userAnswers.add(answer);
    }
}
