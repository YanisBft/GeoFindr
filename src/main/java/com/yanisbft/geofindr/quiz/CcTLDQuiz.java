package com.yanisbft.geofindr.quiz;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.Flag;
import com.yanisbft.geofindr.location.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CcTLDQuiz extends AbstractQuiz<String, Country> {

    public CcTLDQuiz(int questionCount) {
        super(questionCount);
    }

    @Override
    protected List<Question<String, Country>> createRandomQuestions() {
        List<Question<String, Country>> questions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < this.questionCount; i++) {
            Country country = DataProvider.ALL_COUNTRIES.get(random.nextInt(DataProvider.ALL_COUNTRIES.size()));
            questions.add(new Question<>(country.getCcTLD(), country));
        }

        return questions;
    }
}
