package com.decathlon.repository;

import com.decathlon.domain.AthletePoints;
import com.decathlon.domain.ScoreRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Paths;

public class ScoreRepositoryXml implements ScoreRepository {

    private final String path;

    public ScoreRepositoryXml(String path) {
        this.path = path;
    }

    @Override
    public void save(AthletePoints athletePoints) {
        AthletePointsXml athletePointsXml = AthletePointsXml.AthletePointsExporter.from(athletePoints);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(athletePointsXml.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(athletePointsXml, Paths.get(path).toFile());
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
