package com.decathlon.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.decathlon.domain.EventTable.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PointCalculationServiceTest {

    private static final String TEST_NAME_1 = "test name1";
    private static final String TEST_NAME_2 = "test name2";
    private static final String TEST_NAME_3 = "test name3";
    private final PointCalculationService calculationService = new PointCalculationService();;

    @Test
    void testGetAthletePointsThreeAthleteTheSamePlace() {
        List<AthletePointEntity> actualAthletePointEntities = calculationService.calculate(getResultsTheSameScore());
        assertEquals(getExpectedTheSamePoints(), actualAthletePointEntities);
    }

    @Test
    void testGetAthletePointsWithNoTheSamePlace() {
        List<AthleteResultEntity> results = new ArrayList<>();
        results.add(getAthleteResult(TEST_NAME_1, "3.3", "62", "3.2", "3", "2.90", "8.22", "9.83", "6", "6.45", "8.63"));
        List<AthletePointEntity> actualAthletePointEntities = calculationService.calculate(results);
        assertEquals(singletonList(getAthletePoint(TEST_NAME_1, 43760, "1")), actualAthletePointEntities);
    }

    private List<AthleteResultEntity> getResultsTheSameScore() {
        List<AthleteResultEntity> results = new ArrayList<>();
        results.add(getAthleteResult(TEST_NAME_1, "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"));
        results.add(getAthleteResult(TEST_NAME_2, "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"));
        results.add(getAthleteResult(TEST_NAME_3, "12.61", "5.10", "19.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"));
        return results;
    }

    private List<AthletePointEntity> getExpectedTheSamePoints() {
        List<AthletePointEntity> points = new ArrayList<>();
        points.add(getAthletePoint(TEST_NAME_3, 4835, "1"));
        points.add(getAthletePoint(TEST_NAME_1, 4203, "2-3"));
        points.add(getAthletePoint(TEST_NAME_2, 4203, "2-3"));
        return points;
    }

    protected AthletePointEntity getAthletePoint(String name, int points, String place) {
        AthletePointEntity athletePointEntity = AthletePointEntity.from(name, points);
        athletePointEntity.setPlace(place);
        return athletePointEntity;
    }

    protected AthleteResultEntity getAthleteResult(String name, String running100mSec, String longJumpCm, String shotPutMeter, String highJumpCm,
                                                   String running400mSec, String running100mHurdlesSec, String discusThrowMeter, String poleVaultCm,
                                                   String javelinThrowMeter, String running1500mSec) {
        AthleteResultEntity athleteResultEntity = new AthleteResultEntity(name);
        athleteResultEntity.addEventScore(RUN_100M, running100mSec);
        athleteResultEntity.addEventScore(LONG_JUMP, longJumpCm);
        athleteResultEntity.addEventScore(SHOT_PUT, shotPutMeter);
        athleteResultEntity.addEventScore(HIGH_JUMP, highJumpCm);
        athleteResultEntity.addEventScore(RUN_400M, running400mSec);
        athleteResultEntity.addEventScore(RUN_100M_HURDLES, running100mHurdlesSec);
        athleteResultEntity.addEventScore(DISCUS_THROW, discusThrowMeter);
        athleteResultEntity.addEventScore(POLE_VAULT, poleVaultCm);
        athleteResultEntity.addEventScore(JAVELIN_THROW, javelinThrowMeter);
        athleteResultEntity.addEventScore(RUN_1500M, running1500mSec);
        return athleteResultEntity;
    }
}
