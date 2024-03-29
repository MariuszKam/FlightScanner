package com.solvd;

import com.solvd.model.RouteDetails;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

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

}
