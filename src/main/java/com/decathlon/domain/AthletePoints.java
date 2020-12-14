package com.decathlon.domain;

import java.util.List;
import java.util.Objects;

public class AthletePoints {

    private final List<AthletePoint> points;

    AthletePoints(List<AthletePoint> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AthletePoints that = (AthletePoints) o;

        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return points != null ? points.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AthletePoints{points=" + points + '}';
    }

    public void export(Exporter exporter) {
        points.stream()
                .map(p -> p.export(exporter.getAthletePointExporter()))
                .forEach(exporter::addAthletePoint);
    }

    public interface Exporter {
        AthletePoint.Exporter getAthletePointExporter();

        void addAthletePoint(AthletePoint.Exporter exporter);
    }
}
