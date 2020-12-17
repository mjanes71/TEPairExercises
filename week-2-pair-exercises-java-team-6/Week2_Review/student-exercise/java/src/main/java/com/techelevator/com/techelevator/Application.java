package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.techelevator.Department;
import com.techelevator.Employee;
import com.techelevator.Project;


public class Application {

    private static List<com.techelevator.Department> departments = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static Map<String, Project> projects = new HashMap<>();

    /**
     * The main entry point in the application
     * @param args
     */
    public static void main(String[] args) {

        // create some departments
        createDepartments();


        // print each department by name
        printDepartments();



        

        // create employees
        createEmployees();





        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();

    }

    /**
     * Create departments and add them to the collection of departments
     */
    private static void createDepartments() {
        Department marketing = new Department(001, "Marketing");
        departments.add(marketing);

        Department sales = new Department(002, "Sales");
        departments.add(sales);

        Department engineering = new Department(003, "Engineering");
        departments.add(engineering);
    }

    /**
     * Print out each department in the collection.
     */
    private static void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for(Department department : departments){
            System.out.println(department.getName());
        }



    }

    /**
     * Create employees and add them to the collection of employees
     */
    private static void createEmployees() {
        Employee deanJohnson = new Employee();
        deanJohnson.setEmployeeId(001);
        deanJohnson.setFirstName("Dean");
        deanJohnson.setLastName("Johnson");
        deanJohnson.setEmail("djohnson@teams.com");
        deanJohnson.setDepartment(departments.get(2));
        deanJohnson.setHireDate("08/21/2020");

        Employee angieSmith = new Employee(002,"Angie", "Smith", "asmith@teams.com", departments.get(2),"08/21/2020");

        Employee margaretThompson = new Employee(003, "Margaret", "Thompson", "mthompson@teams.com", departments.get(0), "08/21/2020");

        angieSmith.raiseSalary(10.0);

        employees.add(deanJohnson);
        employees.add(angieSmith);
        employees.add(margaretThompson);





    }

    /**
     * Print out each employee in the collection.
     */
    private static void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");
       for(Employee employee : employees){
           System.out.println(employee.getFullName() + " (" + employee.getSalary() + ") " + employee.getDepartment().getName());
       }

    }

    /**
     * Create the 'TEams' project.
     */
    private static void createTeamsProject() {
        Project teams = new Project("TEams", "Project Management Software", "10/10/2020", "11/10/2020" );

        teams.setTeamMembers(employees.subList(0,2));

        projects.put(teams.getName(),teams);


    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private static void createLandingPageProject() {
        Project marketLandingPage = new Project("Marketing Landing Page", "Lead Capture Landing Page for Marketing", "10/10/2020", "10/17/2020");

        marketLandingPage.setTeamMembers(employees.subList(2,3));

        projects.put(marketLandingPage.getName(), marketLandingPage);

    }

    /**
     * Print out each project in the collection.
     */
    private static void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");

        for(Map.Entry<String,Project> project : projects.entrySet()){
            System.out.println(project.getKey() + ": " + project.getValue().getTeamMembers().size());

        }

    }

}
