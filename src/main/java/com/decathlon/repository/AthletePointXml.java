package com.decathlon.repository;

import com.decathlon.domain.AthletePoint;

import java.util.Objects;

public class AthletePointXml {

    private String name;
    private int points;
    private String place;

    public AthletePointXml(String name, String place, int points) {
        this.name = name;
        this.place = place;
        this.points = points;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public void setPoints(int points) {
        this.points = points;
    }

    @SuppressWarnings("unused")
    public void setPlace(String place) {
        this.place = place;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public int getPoints() {
        return points;
    }

    @SuppressWarnings("unused")
    public String getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthletePointXml that = (AthletePointXml) o;
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
        return "AthletePoint{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", place='" + place + '\'' +
                '}';
    }

    static class AthletePointExporter implements AthletePoint.Exporter {
        private String name;
        private String place;
        private int points;

        static AthletePointXml from(AthletePoint.Exporter p) {
            return ((AthletePointExporter) p).build();
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void setPlace(String place) {
            this.place = place;
        }

        @Override
        public void setPoints(int points) {
            this.points = points;
        }

        public AthletePointXml build() {
            return new AthletePointXml(name, place, points);
        }
    }
}
