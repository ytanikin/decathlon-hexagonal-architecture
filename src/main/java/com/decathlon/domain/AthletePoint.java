package com.decathlon.domain;

import java.util.Objects;

public class AthletePoint {

    private final String name;
    private final int points;
    private String place;

    private AthletePoint(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public static AthletePoint from(AthleteResult result) {
        return from(result.getName(), result.points());
    }

    public static AthletePoint from(String name, int points) {
        return new AthletePoint(name, points);
    }

    public int getPoints() {
        return points;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthletePoint that = (AthletePoint) o;
        return points == that.points &&
                Objects.equals(name, that.name) &&
                Objects.equals(place, that.place);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, points, place);
    }

    @Override
    public String toString() {
        return "AthletePoint{name='" + name + '\'' + ", points=" + points + ", place='" + place + '\'' + '}';
    }

    Exporter export(Exporter exporter) {
        exporter.setName(name);
        exporter.setPlace(place);
        exporter.setPoints(points);
        return exporter;
    }

    public interface Exporter {
        void setName(String name);

        void setPlace(String place);

        void setPoints(int points);
//        build()
    }
}
