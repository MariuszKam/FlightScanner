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
    static void saveRouteDetailsAsXml(List<String> steps, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RouteDetails.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            RouteDetails routeDetails = new RouteDetails(steps);
            marshaller.marshal(routeDetails, new File(filePath));
        } catch (Exception e) {
            LOGGER.error("Error saving route details to XML", e);
        }
    }

}
