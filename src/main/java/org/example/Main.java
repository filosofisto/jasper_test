package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Load the JRXML file from the resources folder
            InputStream jrxmlInput = Main.class.getResourceAsStream("/report.jrxml");

            if (jrxmlInput == null) {
                throw new RuntimeException("Could not find the report file.");
            }

            // Compile the JRXML file
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInput);

            // Create parameters map
            Map<String, Object> parameters = new HashMap<>();

            // Create an empty data source
            JRDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton("One"));

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/eduardo/Dev/report.pdf");

            System.out.println("Report generated successfully!");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
