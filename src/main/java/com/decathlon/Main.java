package com.decathlon;

import com.decathlon.domain.PointCalculationService;
import com.decathlon.domain.ResultRepository;
import com.decathlon.domain.ScoreRepository;
import com.decathlon.repository.ResultRepositoryCsv;
import com.decathlon.repository.ScoreRepositoryXml;

public class Main {

    public static void main(String[] args) {
        String resultPath = args.length == 2 ? args[0] : "data/results.csv";
        String pathToSaveResults = args.length == 2 ? args[1] : "data/score.xml";

        ResultRepository resultRepository = new ResultRepositoryCsv(resultPath);
        ScoreRepository scoreRepository = new ScoreRepositoryXml(pathToSaveResults);
        PointCalculationService pointCalculationService = new PointCalculationService();

        ApplicationService applicationService = new ApplicationService(resultRepository, scoreRepository, pointCalculationService);
        applicationService.run();
        System.out.println("Results saved in file " + pathToSaveResults);
    }
}
