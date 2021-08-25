package com.decathlon.service;

import com.decathlon.domain.*;

import java.util.List;

public class ApplicationService {

    private final ResultRepository resultRepository;
    private final ScoreRepository scoreRepository;
    private final CalculationManager calculationManager;

    public ApplicationService(ResultRepository resultRepository, ScoreRepository scoreRepository) {
        this.resultRepository = resultRepository;
        this.scoreRepository = scoreRepository;
        this.calculationManager = CalculationManager.singleInstance();
    }

    public void calculate() {
        List<AthleteResult> results = resultRepository.getResults();
        List<AthletePoint> points = calculationManager.calculate(results);
        scoreRepository.save(points);
    }
}
