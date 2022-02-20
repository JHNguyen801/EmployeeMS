package model;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeFilter {
    Scanner select = new Scanner(System.in);
    /*
    This method prompts a user to enter the status of the employee(s).
    The method takes the user's input and filters the list of employees
    per request. It displays the list of employee status info.
 */
    public String statusFilter(ArrayList<EmployeeAdd> employeeList) {
        String status;
        System.out.print("Enter the status you want to see (active or inactive): ");
        status = select.next();
        select.nextLine();
        System.out.println();
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t  Employee Filter by status");
        System.out.println("\t\t*******************************************");
        System.out.printf("%5s %15s %15s %15s %10s %12s","EmployeeID: ",
                "First Name: ", "Last Name: ", "Hire Date: ",
                "Salary", "Status:");
        System.out.println();
        employeeList.stream().filter((emp -> emp.getStatus().equals(status)))
                .forEach(s -> System.out.format("%5s %15s %20s %15s %12s %12s",
                        s.getEmployeeID(),  s.getFirstName(), s.getLastName(),
                        s.getHireDate(), s.getSalary(), s.getStatus()).println());
        System.out.println();
        return  status;
    }

    /*
    This method prompts a user to enter the salary of the employee(s).
    The method takes the user's input and filters the list of employees
    per request. It displays the list of employee salary info.
 */
    public double salaryFilter(ArrayList<EmployeeAdd> employeeList){
        double salary;
        System.out.print("Please enter a salary you want to see: ");
        salary = select.nextDouble();
        System.out.println();
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t  Employee Filter by Salary");
        System.out.println("\t\t*******************************************");
        System.out.printf("%5s %15s %15s %15s %10s %12s","EmployeeID: ",
                "First Name: ", "Last Name: ", "Hire Date: ",
                "Salary", "Status:");
        System.out.println();
        employeeList.stream().filter(emp->emp.getSalary() >= salary)
                .forEach(s -> System.out.format("%5s %15s %20s %15s %12s %12s",
                        s.getEmployeeID(),  s.getFirstName(), s.getLastName(),
                        s.getHireDate(), s.getSalary(), s.getStatus()).println());
        System.out.println();
        return salary;
    }
}
