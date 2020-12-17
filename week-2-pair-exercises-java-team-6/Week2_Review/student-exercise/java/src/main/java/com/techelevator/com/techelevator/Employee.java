package com.techelevator;
import com.techelevator.Department;

public class Employee {

    private static final double DEFAULT_SALARY = 60000.00;

    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
    private Department department;
    private String hireDate;

    public Employee(long employeeId, String firstName, String lastName, String email, Department department, String hireDate){
        setEmployeeId(employeeId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setDepartment(department);
        setHireDate(hireDate);
        setSalary(DEFAULT_SALARY);

    }

    public Employee(){
        setSalary(DEFAULT_SALARY);

    }

    public long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
    return lastName;
}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary( double salary) {
        this.salary= salary;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment( Department department) {
        this.department = department;
    }
    public String getHireDate() {
        return hireDate;
    }
    public void setHireDate( String hireDate) {
        this.hireDate = hireDate;
    }
    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }

    public void raiseSalary(double percent){
        double changedSalary = getSalary() * percent + getSalary();
        setSalary(changedSalary);

    }


}
//current salary * percent + current salary


