package com.solvd.repairserviceclasses;

import com.solvd.configuration.CloseableHandler;

import java.io.IOException;
import java.util.logging.FileHandler;
//import java.util.logging.Logger;
import java.util.logging.*;

public final class FinalReportGenerator {
    private static final Logger logger = Logger.getLogger("FinalReportGenerator");
    private final String reportType;

    public static final String COMPANY_NAME = "Tech Solutions Inc.";

    static {
        System.out.println("Static block executed");
    }

    public FinalReportGenerator(String reportType) {
        this.reportType = reportType;
    }

    public final void generateReport() {
        try (CloseableHandler fileHandler = new CloseableHandler("report.log")) {
            logger.addHandler(fileHandler);


        } catch (IOException e) {
            logger.severe("IOException during report generation: " + e.getMessage());
        }
    }

    public String getReportType() {
        return reportType;
    }
}
