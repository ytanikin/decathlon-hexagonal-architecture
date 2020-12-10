package com.decathlon;

import com.decathlon.domain.*;
import com.decathlon.domain.AthletePoints;
import com.decathlon.domain.AthleteResult;

import java.util.List;

public class ApplicationService {

    private final ResultRepository resultRepository;
    private final ScoreRepository scoreRepository;
    private final PointCalculationServiceImpl calculationService;

    public ApplicationService(ResultRepository resultRepository, ScoreRepository scoreRepository, PointCalculationServiceImpl calculationService) {
        this.resultRepository = resultRepository;
        this.scoreRepository = scoreRepository;
        this.calculationService = calculationService;
    }

    public void run() {
        List<AthleteResult> results = resultRepository.getResults();
        AthletePoints points = calculationService.calculate(results);
        scoreRepository.save(points);
    }
}
