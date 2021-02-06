package com.yanisbft.geofindr.quiz;

public class Question<Q, A> {
    private final Q question;
    private final A answer;

    public Question(Q question, A answer) {
        this.question = question;
        this.answer = answer;
    }

    public Q getQuestion() {
        return this.question;
    }

    public A getAnswer() {
        return this.answer;
    }
}
