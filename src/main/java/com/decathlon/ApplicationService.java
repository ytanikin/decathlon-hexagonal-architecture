package com.decathlon;

import com.decathlon.domain.*;

import java.util.List;

public class ApplicationService {

    private final ResultRepository resultRepository;
    private final ScoreRepository scoreRepository;
    private final PointCalculationService pointCalculationService;

    public ApplicationService(ResultRepository resultRepository, ScoreRepository scoreRepository, PointCalculationService pointCalculationService) {
        this.resultRepository = resultRepository;
        this.scoreRepository = scoreRepository;
        this.pointCalculationService = pointCalculationService;
    }

    public void run() {
        List<AthleteResultEntity> results = resultRepository.getResults();
        List<AthletePointEntity> points = pointCalculationService.calculate(results);
        scoreRepository.save(points);
    }
}
