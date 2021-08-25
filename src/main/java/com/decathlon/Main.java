package com.decathlon;

import com.decathlon.infrastructure.ResultRepositoryCsv;
import com.decathlon.infrastructure.ScoreRepositoryXml;
import com.decathlon.service.ApplicationService;

public class Main {

    public static void main(String[] args) {
        String resultPath = args.length == 2 ? args[0] : "data/results.csv";
        String pathToSaveResults = args.length == 2 ? args[1] : "data/score.xml";

        ApplicationService applicationService = new ApplicationService(new ResultRepositoryCsv(resultPath), new ScoreRepositoryXml(pathToSaveResults));
        applicationService.calculate();
        System.out.println("Results saved in file " + pathToSaveResults);
    }
}
