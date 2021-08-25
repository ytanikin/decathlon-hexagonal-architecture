package com.decathlon.domain;

import java.util.List;

public interface ScoreRepository {
    void save(List<AthletePoint> athletePointEntities);
}
