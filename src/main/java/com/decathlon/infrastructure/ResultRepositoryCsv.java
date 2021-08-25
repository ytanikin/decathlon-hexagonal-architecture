package com.decathlon.infrastructure;

import com.decathlon.domain.AthleteResult;
import com.decathlon.domain.ResultRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.decathlon.domain.EventTable.*;
import static java.util.stream.Collectors.toList;

public class ResultRepositoryCsv implements ResultRepository {

    private static final String CSV_DELIMITER = ";";
    private final String path;

    public ResultRepositoryCsv(String path) {
        this.path = path;
    }

    @Override
    public List<AthleteResult> getResults() {
        try (BufferedReader inputStreamClose = Files.newBufferedReader(Paths.get(path))) {
            return inputStreamClose.lines()
                    .map(this::parseAthleteResult)
                    .collect(toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private AthleteResult parseAthleteResult(String line) {
        String[] scores = line.split(CSV_DELIMITER);
        validate(scores);
        AthleteResult athleteResult = new AthleteResult(scores[0]);
        athleteResult.addEventScore(RUN_100M, scores[1]);
        athleteResult.addEventScore(LONG_JUMP, scores[2]);
        athleteResult.addEventScore(SHOT_PUT, scores[3]);
        athleteResult.addEventScore(HIGH_JUMP, scores[4]);
        athleteResult.addEventScore(RUN_400M, scores[5]);
        athleteResult.addEventScore(RUN_100M_HURDLES, scores[6]);
        athleteResult.addEventScore(DISCUS_THROW, scores[7]);
        athleteResult.addEventScore(POLE_VAULT, scores[8]);
        athleteResult.addEventScore(JAVELIN_THROW, scores[9]);
        athleteResult.addEventScore(RUN_1500M, scores[10]);
        return athleteResult;
    }

    private void validate(String[] scores) {
        if (scores.length != 11) {
            throw new IllegalArgumentException();
        }
    }
}
