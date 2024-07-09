package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            // Load the JRXML file from the resources folder
//            InputStream jrxmlInput = Main.class.getResourceAsStream("/report.jrxml");
//            log.info("jrxml loaded");
//
//            if (jrxmlInput == null) {
//                throw new RuntimeException("Could not find the report file.");
//            }
//
//            // Compile the JRXML file
//            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInput);
//            log.info("jasper compiled");

            InputStream jasperInput = Main.class.getResourceAsStream("/report.jasper");

            if (jasperInput == null) {
                throw new RuntimeException("Could not find the compiled report file.");
            }

            log.info("jasper compiled found");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperInput);
            log.info("JasperReport loaded");

            // Create parameters map
            Map<String, Object> parameters = new HashMap<>();

            // Create an empty data source
            JRDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton("One"));

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            log.info("JasperPrint filled");

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/eduardo/Dev/report.pdf");
            log.info("PDF Exported");

            System.out.println("Report generated successfully!");
        } catch (Exception e) {
            log.severe(e.getMessage());
            e.printStackTrace();
        }
    }
}
