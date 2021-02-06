package com.yanisbft.geofindr.quiz;

import com.yanisbft.geofindr.DataProvider;
import com.yanisbft.geofindr.location.City;
import com.yanisbft.geofindr.location.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountryFromCapitalQuiz extends AbstractQuiz<City, Country> {

    public CountryFromCapitalQuiz(int questionCount) {
        super(questionCount);
    }

    @Override
    protected List<Question<City, Country>> createRandomQuestions() {
        List<Question<City, Country>> questions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < this.questionCount; i++) {
            Country country = DataProvider.ALL_COUNTRIES.get(random.nextInt(DataProvider.ALL_COUNTRIES.size()));
            questions.add(new Question<>(country.getCapital(), country));
        }

        return questions;
    }
}
