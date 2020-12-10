package com.decathlon.domain;

import java.time.Duration;
import java.util.function.Function;

import static java.lang.Double.parseDouble;

public enum EventTable {

    RUN_100M(25.437, 18.0, 1.81, EventType.TRACK, Double::parseDouble),
    LONG_JUMP(0.14354, 220, 1.40, EventType.FIELD, EventTable::convertToCentimeters),
    SHOT_PUT(51.39, 1.5, 1.05, EventType.FIELD, Double::parseDouble),
    HIGH_JUMP(0.8465, 75, 1.42, EventType.FIELD, EventTable::convertToCentimeters),
    RUN_400M(1.53775, 82, 1.81, EventType.TRACK, Double::parseDouble),
    RUN_100M_HURDLES(5.74352, 28.5, 1.92, EventType.TRACK, Double::parseDouble),
    DISCUS_THROW(12.91, 4.0, 1.1, EventType.FIELD, Double::parseDouble),
    POLE_VAULT(0.2797, 100, 1.35, EventType.FIELD, EventTable::convertToCentimeters),
    JAVELIN_THROW(10.14, 7.0, 1.08, EventType.FIELD, Double::parseDouble),
    RUN_1500M(0.03768, 480, 1.85, EventType.TRACK, EventTable::convertToSeconds);

    private final double a;
    private final double b;
    private final double c;
    private final EventType eventType;
    private final Function<String, Double> converter;

    EventTable(double a, double b, double c, EventType eventType, Function<String, Double> converter) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.eventType = eventType;
        this.converter = converter;
    }

    public double parse(String result) {
        return converter.apply(result);
    }

    public int calculatePoint(double performance) {
        if (eventType == EventType.TRACK) {
            return calculateTrackPoint(performance);
        }
        return calculateFieldPoint(performance);
    }

    private int calculateTrackPoint(double performance) {
        return (int) Math.floor(a * Math.pow((b - performance), c));
    }

    private int calculateFieldPoint(double performance) {
        return (int) Math.floor(a * Math.pow((performance - b), c));
    }

    private static double convertToCentimeters(String meters) {
        return parseDouble(meters) * 100;
    }

    private static double convertToSeconds(String score) {
        String durationString = "PT" + score.replace(":", "M") + "S";
        return Duration.parse(durationString).getSeconds();
    }

    private enum EventType {
        TRACK, FIELD
    }
}
