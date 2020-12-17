package com.decathlon.repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "results")
public class AthletePointsXml {

    @XmlElement(name = "result")
    private List<AthletePointXml> points;

    @SuppressWarnings("unused")
    public AthletePointsXml(){}

    public AthletePointsXml(List<AthletePointXml> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AthletePointsXml that = (AthletePointsXml) o;

        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return points != null ? points.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AthletePoints{" +
                "points=" + points +
                '}';
    }
}
