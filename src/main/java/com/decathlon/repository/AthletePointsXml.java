package com.decathlon.repository;

import com.decathlon.domain.AthletePoint;
import com.decathlon.domain.AthletePoints;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    static class AthletePointsExporter implements AthletePoints.Exporter {

        private final List<AthletePoint.Exporter> athletePointExporters = new ArrayList<>();

        static AthletePointsXml from(AthletePoints athletePoints) {
            AthletePointsXml.AthletePointsExporter athletePointsExporter = new AthletePointsXml.AthletePointsExporter();
            athletePoints.export(athletePointsExporter);
            return athletePointsExporter.build();
        }

        @Override
        public AthletePoint.Exporter getAthletePointExporter() {
            return new AthletePointXml.AthletePointExporter();
        }

        @Override
        public void addAthletePoint(AthletePoint.Exporter exporter) {
            athletePointExporters.add(exporter);
        }

        private AthletePointsXml build() {
            List<AthletePointXml> athletePointXmls = athletePointExporters.stream()
                    .map(AthletePointXml.AthletePointExporter::from)
                    .collect(Collectors.toList());
            return new AthletePointsXml(athletePointXmls);
        }
    }
}
