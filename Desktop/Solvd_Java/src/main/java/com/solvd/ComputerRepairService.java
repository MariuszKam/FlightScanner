package com.solvd;

import com.solvd.configuration.CustomLinkedList;
import com.solvd.exceptions.EmployeeManagementException;
import com.solvd.multithreading.Connection;
import com.solvd.multithreading.ConnectionPool;
import com.solvd.repairserviceclasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ComputerRepairService {


    private static final Logger logger = Logger.getLogger("ComputerRepairService");

    static {

        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);

        try {

            FileHandler fileHandler = new FileHandler("log.txt");
            logger.addHandler(fileHandler);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        AbstractDevice computer = new Computer("Dell", "XPS 13", 2022);
        Technician technician = new Technician("John Doe", 1, "IT", 5);
        CustomerRelationManager crm = new CustomerRelationManager("Alice", 2, "Sales", "VIP");
        String inputFilePath = "resources/input.txt";
        String outputFilePath = "resources/output.txt";


        Employees employee1 = technician;
        Employees employee2 = crm;

        try {
            employee1.performTask();
        } catch (EmployeeManagementException e) {

            logger.severe("Exception caught: " + e.getMessage());
        }

        CustomLinkedList<String> taskList = new CustomLinkedList<>();
        taskList.add("Task 1");
        taskList.add("Task 2");
        taskList.add("Task 3");

        System.out.println("Tasks in custom LinkedList:");
        taskList.display();

        // Invoke final class methods and access final variables
        FinalReportGenerator reportGenerator = new FinalReportGenerator("Monthly Sales");
        reportGenerator.generateReport();
        System.out.println("Report type: " + reportGenerator.getReportType());
        System.out.println("Company name: " + FinalReportGenerator.COMPANY_NAME);

        employee1.processTextFile(inputFilePath, outputFilePath);

        employee2.useLambdaFunctions();
        System.out.println("--------");
        employee1.useCustomLambdaFunctions();
        System.out.println("--------");
        // Create instances of your classes
        List<Employees> employeesList = Arrays.asList(
                new Technician("John", 1, "IT", 5),
                new Technician("Bob", 3, "IT", 3)
        );

        Employees employeesInstance = new Technician("Charlie", 5, "IT", 4);


        List<Technician> techniciansList = employeesList.stream()
                .filter(e -> e instanceof Technician)
                .map(e -> (Technician) e)
                .collect(Collectors.toList());


        System.out.println("Filtered by Department: " +
                employeesInstance.filterByDepartment(employeesList, "IT"));

        System.out.println("Uppercase Names: " +
                employeesInstance.mapToUppercaseNames(employeesList));

        System.out.println("Number of Technicians: " +
                employeesInstance.countTechnicians(employeesList));

        System.out.println("Sorted Technicians by Experience: " +
                employeesInstance.sortTechniciansByExperience(techniciansList));

        System.out.println("Distinct Departments: " +
                employeesInstance.extractDistinctDepartments(employeesList));

        System.out.println("Grouped by Department: " +
                employeesInstance.groupByDepartment(employeesList));

        System.out.println("Average Experience of Technicians: " +
                employeesInstance.calculateAverageExperience(techniciansList));
        System.out.println("--------");


        employeesInstance.performReflectionOperations();

        System.out.println("--------");

        ConnectionPool connectionPool = ConnectionPool.getInstance(5);


        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println(Thread.currentThread().getName() + " got connection: " + connection.getId());
                    // Simulate some work with the connection
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }


        for (int i = 0; i < 7; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println(Thread.currentThread().getName() + " got connection: " + connection.getId());
                    // Simulate some work with the connection
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        connectionPool.shutdown();

    }
}