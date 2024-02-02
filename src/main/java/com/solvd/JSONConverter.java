package com.solvd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.model.RouteDetails;

import java.io.File;
import java.io.IOException;
import static com.solvd.Main.LOGGER;

public class JSONConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String URL_PATH = "src/main/resources/RouteDetails.json";

    public static void saveToJSON(RouteDetails routeDetails) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(URL_PATH), routeDetails);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
