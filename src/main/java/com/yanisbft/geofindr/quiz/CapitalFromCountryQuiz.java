package com.yanisbft.geofindr.quiz;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.location.City;
import com.yanisbft.geofindr.location.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CapitalFromCountryQuiz extends AbstractQuiz<Country, City> {

    public CapitalFromCountryQuiz(int questionCount) {
        super(questionCount);
    }

    @Override
    protected List<Question<Country, City>> createRandomQuestions() {
        List<Question<Country, City>> questions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < this.questionCount; i++) {
            Country country = DataProvider.ALL_COUNTRIES.get(random.nextInt(DataProvider.ALL_COUNTRIES.size()));
            questions.add(new Question<>(country, country.getCapital()));
        }

        return questions;
    }
}
