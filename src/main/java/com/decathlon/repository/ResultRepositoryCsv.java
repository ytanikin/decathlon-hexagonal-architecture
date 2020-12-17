package com.decathlon.repository;

import com.decathlon.domain.AthleteResultEntity;
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
    public List<AthleteResultEntity> getResults() {
        try (BufferedReader inputStreamClose = Files.newBufferedReader(Paths.get(path))) {
            return inputStreamClose.lines()
                    .map(this::parseAthleteResult)
                    .collect(toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private AthleteResultEntity parseAthleteResult(String line) {
        String[] scores = line.split(CSV_DELIMITER);
        validate(scores);
        AthleteResultEntity athleteResultEntity = new AthleteResultEntity(scores[0]);
        athleteResultEntity.addEventScore(RUN_100M, scores[1]);
        athleteResultEntity.addEventScore(LONG_JUMP, scores[2]);
        athleteResultEntity.addEventScore(SHOT_PUT, scores[3]);
        athleteResultEntity.addEventScore(HIGH_JUMP, scores[4]);
        athleteResultEntity.addEventScore(RUN_400M, scores[5]);
        athleteResultEntity.addEventScore(RUN_100M_HURDLES, scores[6]);
        athleteResultEntity.addEventScore(DISCUS_THROW, scores[7]);
        athleteResultEntity.addEventScore(POLE_VAULT, scores[8]);
        athleteResultEntity.addEventScore(JAVELIN_THROW, scores[9]);
        athleteResultEntity.addEventScore(RUN_1500M, scores[10]);
        return athleteResultEntity;
    }

    private void validate(String[] scores) {
        if (scores.length != 11) {
            throw new IllegalArgumentException();
        }
    }
}
