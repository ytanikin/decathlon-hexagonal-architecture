package com.decathlon.application;

import com.decathlon.domain.*;
import com.decathlon.infrastructure.ResultRepositoryCsv;
import com.decathlon.infrastructure.ScoreRepositoryXml;

import java.util.List;

public class ApplicationService {

    private final ResultRepository resultRepository;
    private final ScoreRepository scoreRepository;
    private final PointCalculationService pointCalculationService;

    public ApplicationService(String resultPath, String pathToSaveResults) {
        this.resultRepository = new ResultRepositoryCsv(resultPath);
        this.scoreRepository = new ScoreRepositoryXml(pathToSaveResults);
        this.pointCalculationService = new PointCalculationService();
    }

    public void run() {
        List<AthleteResultEntity> results = resultRepository.getResults();
        List<AthletePointEntity> points = pointCalculationService.calculate(results);
        scoreRepository.save(points);
    }
}
