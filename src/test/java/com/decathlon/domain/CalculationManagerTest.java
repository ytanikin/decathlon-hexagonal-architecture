package com.decathlon.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.decathlon.domain.EventTable.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationManagerTest {

    private static final String TEST_NAME_1 = "test name1";
    private static final String TEST_NAME_2 = "test name2";
    private static final String TEST_NAME_3 = "test name3";
    private final CalculationManager calculationService = CalculationManager.singleInstance();

    @Test
    void testGetAthletePointsThreeAthleteTheSamePlace() {
        List<AthletePoint> actualAthletePointEntities = calculationService.calculate(getResultsTheSameScore());
        assertEquals(getExpectedTheSamePoints(), actualAthletePointEntities);
    }

    @Test
    void testGetAthletePointsWithNoTheSamePlace() {
        List<AthleteResult> results = new ArrayList<>();
        results.add(getAthleteResult(TEST_NAME_1, "3.3", "62", "3.2", "3", "2.90", "8.22", "9.83", "6", "6.45", "8.63"));
        List<AthletePoint> actualAthletePointEntities = calculationService.calculate(results);
        assertEquals(singletonList(getAthletePoint(TEST_NAME_1, 43760, "1")), actualAthletePointEntities);
    }

    private List<AthleteResult> getResultsTheSameScore() {
        List<AthleteResult> results = new ArrayList<>();
        results.add(getAthleteResult(TEST_NAME_1, "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"));
        results.add(getAthleteResult(TEST_NAME_2, "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"));
        results.add(getAthleteResult(TEST_NAME_3, "12.61", "5.10", "19.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"));
        return results;
    }

    private List<AthletePoint> getExpectedTheSamePoints() {
        List<AthletePoint> points = new ArrayList<>();
        points.add(getAthletePoint(TEST_NAME_3, 4835, "1"));
        points.add(getAthletePoint(TEST_NAME_1, 4203, "2-3"));
        points.add(getAthletePoint(TEST_NAME_2, 4203, "2-3"));
        return points;
    }

    protected AthletePoint getAthletePoint(String name, int points, String place) {
        AthletePoint athletePoint = AthletePoint.from(name, points);
        athletePoint.setPlace(place);
        return athletePoint;
    }

    protected AthleteResult getAthleteResult(String name, String running100mSec, String longJumpCm, String shotPutMeter, String highJumpCm,
                                             String running400mSec, String running100mHurdlesSec, String discusThrowMeter, String poleVaultCm,
                                             String javelinThrowMeter, String running1500mSec) {
        AthleteResult athleteResult = new AthleteResult(name);
        athleteResult.addEventScore(RUN_100M, running100mSec);
        athleteResult.addEventScore(LONG_JUMP, longJumpCm);
        athleteResult.addEventScore(SHOT_PUT, shotPutMeter);
        athleteResult.addEventScore(HIGH_JUMP, highJumpCm);
        athleteResult.addEventScore(RUN_400M, running400mSec);
        athleteResult.addEventScore(RUN_100M_HURDLES, running100mHurdlesSec);
        athleteResult.addEventScore(DISCUS_THROW, discusThrowMeter);
        athleteResult.addEventScore(POLE_VAULT, poleVaultCm);
        athleteResult.addEventScore(JAVELIN_THROW, javelinThrowMeter);
        athleteResult.addEventScore(RUN_1500M, running1500mSec);
        return athleteResult;
    }
}
