package com.solvd;

import com.solvd.model.Flight;
import com.solvd.model.RouteDetails;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class XMLConverter {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String FILE_PATH = "src/main/resources/RouteDetails.xml";
    static void saveRouteDetailsAsXml(RouteDetails routeDetails) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(RouteDetails.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            marshaller.marshal(routeDetails, new File(FILE_PATH));
        } catch (Exception e) {
            LOGGER.error("Error saving route details to XML", e);
        }
    }
    static List<String> convertRouteToListOfStrings(Optional<List<Flight>> optionalFlights) {
        if (optionalFlights.isEmpty()) {
            return Collections.emptyList();
        }

        List<Flight> flights = optionalFlights.get();
        List<String> steps = new ArrayList<>();

        for (Flight flight : flights) {
            String step = String.format("Take flight %s at %s to get to %s",
                    flight.getName(),
                    flight.getStart().getName(),
                    flight.getDestination().getName());
            steps.add(step);
        }

        steps.add("You have reached your final destination");

        return steps;
    }
}
