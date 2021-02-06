package com.yanisbft.geofindr.quiz;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.Flag;
import com.yanisbft.geofindr.location.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlagQuiz extends AbstractQuiz<Flag, Country> {

    public FlagQuiz(int questionCount) {
        super(questionCount);
    }

    @Override
    protected List<Question<Flag, Country>> createRandomQuestions() {
        List<Question<Flag, Country>> questions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < this.questionCount; i++) {
            Country country = DataProvider.ALL_COUNTRIES.get(random.nextInt(DataProvider.ALL_COUNTRIES.size()));
            Flag flag = DataProvider.FLAGS_BY_COUNTRY.get(country);
            questions.add(new Question<>(flag, country));
        }

        return questions;
    }
}
