package com.decathlon.domain;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class PointCalculationServiceImpl {

    public AthletePoints calculate(List<AthleteResult> results) {
        List<AthletePoint> points = results.stream()
                .map(AthletePoint::from)
                .collect(groupingBy(AthletePoint::getPoints, () -> new TreeMap<>(reverseOrder()), toList())).values().stream()
                .peek(calculatePlaceConsumer())
                .flatMap(Collection::stream)
                .collect(toList());

        return new AthletePoints(points);
    }

    private Consumer<List<AthletePoint>> calculatePlaceConsumer() {
        int[] placeCounter = new int[]{0};
        return athletePoints -> {
            String place = calculatePlace(placeCounter, athletePoints);
            athletePoints.forEach(ap -> ap.setPlace(place));
        };
    }

    private String calculatePlace(int[] placeCounter, List<AthletePoint> athletePointEntities) {
        placeCounter[0]++;
        StringBuilder place = new StringBuilder(Integer.toString(placeCounter[0]));
        for (int i = 0; i < athletePointEntities.size() - 1; i++) {
            place.append("-").append(++placeCounter[0]);
        }
        return place.toString();
    }

}
