package com.decathlon.repository;

import com.decathlon.domain.AthletePointEntity;
import com.decathlon.domain.ScoreRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreRepositoryXml implements ScoreRepository {

    private final String path;

    public ScoreRepositoryXml(String path) {
        this.path = path;
    }

    @Override
    public void save(List<AthletePointEntity> athletePointEntities) {
        List<AthletePointXml> pointXmls = athletePointEntities.stream()
                .map(AthletePointXml.AthletePointExporter::from)
                .collect(Collectors.toList());
        AthletePointsXml athletePointsXml = new AthletePointsXml(pointXmls);
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
