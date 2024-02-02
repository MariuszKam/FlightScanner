package com.solvd.jaxb;

import com.solvd.Main;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class JAXBParser {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
       /* FlightData flightData;
        try {
            File file = new File("src/main/resources/FlightData.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(FlightData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            flightData = (FlightData) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info(flightData);*/
    }
}