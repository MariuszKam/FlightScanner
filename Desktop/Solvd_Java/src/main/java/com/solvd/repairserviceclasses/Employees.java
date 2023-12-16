package com.solvd.repairserviceclasses;

import com.solvd.exceptions.EmployeeManagementException;
import com.solvd.interfaces.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.lang.reflect.Field;

public abstract class Employees implements EmployeeManagement, CustomerRelations {
    protected String name;
    protected int employeeId;
    protected String departmentName;

    protected Employees(String name, int employeeId, String departmentName) {
        this.name = name;
        this.employeeId = employeeId;
        this.departmentName = departmentName;
    }

    public void performTask() throws EmployeeManagementException {
        throw new EmployeeManagementException("Error in performing task");
    }

    private List<String> tasks = new ArrayList<>();
    private Map<String, String> assignedTasks = new HashMap<>();
    private Set<String> customers = new HashSet<>();
    private Queue<String> taskQueue = new LinkedList<>();
    private List<String> employeeSkills = new ArrayList<>();

    public void addTask(String task) {
        tasks.add(task);
    }

    public void assignTaskToEmployee(String employee, String task) {
        assignedTasks.put(employee, task);
    }

    public void addCustomer(String customer) {
        customers.add(customer);
    }

    public void enqueueTask(String task) {
        taskQueue.offer(task);
    }

    public void addEmployeeSkill(String skill) {
        employeeSkills.add(skill);
    }

    @Override
    public void assignTask() {
        System.out.println("Assigning a task to an employee");
    }

    @Override
    public void trackPerformance() {
        System.out.println("Tracking employee performance");
    }

    @Override
    public void handleCustomerInquiries() {
        System.out.println("Handling customer inquiries");
    }

    @Override
    public void processCustomerFeedback() {
        System.out.println("Processing customer feedback");
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected int getEmployeeId() {
        return employeeId;
    }

    protected void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    protected String getDepartmentName() {
        return departmentName;
    }

    protected void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public abstract void performDuties();

    public void processTextFile(String inputFilePath, String outputFilePath) {
        try {
            List<String> lines = FileUtils.readLines(new File(inputFilePath), "UTF-8");

            String text = StringUtils.join(lines, ' ');

            Set<String> uniqueWords = new HashSet<>(List.of(StringUtils.split(text)));

            FileUtils.writeLines(new File(outputFilePath), "UTF-8", List.of("Number of unique words: " + uniqueWords.size()));

            System.out.println("Calculation completed. Result written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useLambdaFunctions() {
        // Lambda function for Consumer: Print employee information
        Consumer<Technician> printEmployeeInfo = technician ->
                System.out.println("Employee: " + technician.getName() +
                        ", ID: " + technician.getEmployeeId() +
                        ", Department: " + technician.getDepartmentName());

        Predicate<Computer> needsRepair = computer ->
                computer.getStatus().equalsIgnoreCase("broken");

        Supplier<String> welcomeMessageSupplier = () ->
                "Welcome to our Computer Repair Service!";

        Function<Computer, Double> calculateRepairCost = computer ->
                needsRepair.test(computer) ? computer.getBaseRepairCost() * 1.5 : 0.0;

        BiFunction<Technician, Computer, String> assignRepairTask = (technician, computer) ->
                technician.getName() + " is assigned to repair a " + computer.getBrand() + " " + computer.getModel();

        System.out.println(welcomeMessageSupplier.get());

        Technician technician = new Technician("John Doe", 1, "IT", 5);

        printEmployeeInfo.accept(technician);

        Computer computer = new Computer("Dell", "XPS 13", 2022, "broken");

        System.out.println("Does the computer need repair? " + needsRepair.test(computer));

        System.out.println("Estimated repair cost: $" + calculateRepairCost.apply(computer));

        System.out.println(assignRepairTask.apply(technician, computer));
    }

    public void useCustomLambdaFunctions() {
        RepairActionLambda<Computer> repairActionLambda = computer ->
                System.out.println("Performing repair action for computer: " + computer);

        Computer computerToRepair = new Computer("Dell", "Latitude", 2022, "broken");
        repairActionLambda.performRepairAction(computerToRepair);

        ConcatenateLambda<String> concatenateLambda = (str1, str2) -> str1 + str2;

        String concatenatedStrings = concatenateLambda.concatenate("Hello", "Lambda");
        System.out.println("Concatenated Strings: " + concatenatedStrings);

        CheckNullLambda<String> checkNullLambda = Objects::isNull;

        String testString = "Not null";
        System.out.println("Is the string null? " + checkNullLambda.checkNull(testString));

        System.out.println("=== Example Usage of Enums ===");

        ComputerStatusEnum computerStatus = ComputerStatusEnum.BROKEN;
        System.out.println("Computer Status: " + computerStatus.getStatusName());
        System.out.println("Computer Status Code: " + computerStatus.getStatusCode());
        computerStatus.repairAction();

        SkillLevelEnum skillLevel = SkillLevelEnum.INTERMEDIATE;
        System.out.println("Skill Level: " + skillLevel.getLevelName());
        System.out.println("Skill Level Description: " + skillLevel.getDescription());

        PriorityEnum priority = PriorityEnum.URGENT;
        System.out.println("Priority: " + priority.getPriorityName());
        System.out.println("Priority Code: " + priority.getPriorityCode());
        priority.processUrgentTask();

        RoleEnum role = RoleEnum.TECHNICIAN;
        System.out.println("Role: " + role.getRoleName());
        System.out.println("Role Description: " + role.getDescription());

        ServiceType serviceType = ServiceType.REPAIR;
        System.out.println("Service Type: " + serviceType.getTypeName());
        System.out.println("Service Type Description: " + serviceType.getDescription());
    }

    public List<Employees> filterByDepartment(List<Employees> employees, String department) {
        return employees.stream()
                .filter(e -> e.getDepartmentName().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<String> mapToUppercaseNames(List<Employees> employees) {
        return employees.stream()
                .map(Employees::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public long countTechnicians(List<Employees> employees) {
        return employees.stream()
                .filter(e -> e instanceof Technician)
                .count();
    }

    public List<Technician> sortTechniciansByExperience(List<Technician> technicians) {
        return technicians.stream()
                .sorted((t1, t2) -> Integer.compare(t2.getExperienceYears(), t1.getExperienceYears()))
                .collect(Collectors.toList());
    }

    public List<String> extractDistinctDepartments(List<Employees> employees) {
        return employees.stream()
                .map(Employees::getDepartmentName)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, List<Employees>> groupByDepartment(List<Employees> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employees::getDepartmentName));
    }

    public double calculateAverageExperience(List<Technician> technicians) {
        return technicians.stream()
                .mapToInt(Technician::getExperienceYears)
                .average()
                .orElse(0.0);
    }

    public void performReflectionOperations() {
        Class<?> clazz = this.getClass();

        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Fields:");
        Arrays.stream(fields).forEach(field -> System.out.println(field.getName() + " - " + field.getType()));

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.println("\nConstructors:");
        Arrays.stream(constructors).forEach(System.out::println);

        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("\nMethods:");
        Arrays.stream(methods).forEach(System.out::println);
    }


    @Override
    public String toString() {
        return "Employee: Name - " + name + ", ID - " + employeeId + ", Department - " + departmentName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employeeId, departmentName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employees that = (Employees) obj;
        return employeeId == that.employeeId &&
                Objects.equals(name, that.name) &&
                Objects.equals(departmentName, that.departmentName);
    }
}
