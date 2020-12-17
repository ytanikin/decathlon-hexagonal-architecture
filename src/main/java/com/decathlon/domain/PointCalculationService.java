package com.decathlon.domain;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class PointCalculationService {

    public List<AthletePointEntity> calculate(List<AthleteResultEntity> results) {
        return results.stream()
                .map(AthletePointEntity::from)
                .collect(groupingBy(AthletePointEntity::getPoints, () -> new TreeMap<>(reverseOrder()), toList())).values().stream()
                .peek(calculatePlaceConsumer())
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private Consumer<List<AthletePointEntity>> calculatePlaceConsumer() {
        int[] placeCounter = new int[]{0};
        return athletePoints -> {
            String place = calculatePlace(placeCounter, athletePoints);
            athletePoints.forEach(ap -> ap.setPlace(place));
        };
    }

    private String calculatePlace(int[] placeCounter, List<AthletePointEntity> athletePointEntityEntities) {
        placeCounter[0]++;
        StringBuilder place = new StringBuilder(Integer.toString(placeCounter[0]));
        for (int i = 0; i < athletePointEntityEntities.size() - 1; i++) {
            place.append("-").append(++placeCounter[0]);
        }
        return place.toString();
    }
}
