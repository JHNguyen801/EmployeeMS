package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/*
    EmployeeDetail class contains 5 variables and has one method
    to prompt a user enter data in the input data that stores
    in an arrayList.
 */
public class EmployeeAdd extends Employee implements Comparable<EmployeeAdd>, Serializable {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String hireDate;
    private String status;
    private double salary;
    static final AtomicInteger count = new AtomicInteger(0);
    private ArrayList<EmployeeAdd> employeeAdds;

    // Default Construtor
    public EmployeeAdd(){
        employeeID = count.incrementAndGet();
        firstName = "";
        lastName = "";
        hireDate = "";
        status = "";
        salary = 0;
    }

    // Overload constructors store id, first name, last name, hire date, and status
    public EmployeeAdd(int id, String fName, String lName, String hDate, double s, String st ) throws EmployeeIDException {
        if(id >= 1){
            employeeID = id;
            employeeID = count.incrementAndGet();
        }
        else {
            throw new EmployeeIDException();
        }
        firstName = fName;
        lastName = lName;
        hireDate = hDate;
        status = st;
        if(salary >= 0){
            salary = s;
        }else{
            System.out.println("Salary must be greater than 0");
        }
    }

    // A method prompt a user to enter input information and add info to
    // the arrayList
    @Override
    public ArrayList<EmployeeAdd> getInfo() throws EmployeeIDException, IOException {
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHow many employees do you want to add: ");
        int number = sc.nextInt();
        for(int i =0; i < number; i++) {
            System.out.print("\nEnter First Name: ");
            firstName = sc.next();
            System.out.print("Enter Last Name: ");
            lastName = sc.next();
            System.out.print("Enter Hired Date in the mm/dd/yyyy : ");
            hireDate = sc.next();
            try{
                System.out.print("Enter Salary : ");
                salary = sc.nextDouble();
            }
            catch (InputMismatchException e){
                System.out.print("Salary information must be digits ");
                e.printStackTrace();
                System.out.print("Enter Salary : ");
                salary = sc.nextDouble();
            }
            System.out.print("Enter Status: ");
            status = sc.next();
            System.out.println("***Employee information saved successfully***\n");
            // add input into an arrayList
            employeeList.add(new EmployeeAdd(employeeID, firstName, lastName, hireDate, salary, status));
        }
        return employeeList;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int setEmployeeID(int id) {
        employeeID = id;
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        lastName = lName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hDate) {hireDate = hDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String st) {
        status = st;
    }

    public double getSalary() {
        return salary;
    }

    public double setSalary(double s) {
        salary = s;
        return s;
    }

    public ArrayList<EmployeeAdd> getEmployeeAdds() {
        return employeeAdds;
    }

    @Override
    public int compareTo(EmployeeAdd o) {
        EmployeeAdd compareEmployee = (EmployeeAdd) o;
        if(this.salary < compareEmployee.salary){
            return -1;
        }
        else if(this.salary > compareEmployee.salary){
            return 1;
        }else {
            return  0;
        }
    }
}
