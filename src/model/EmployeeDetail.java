package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/*
    EmployeeDetail class contains 5 variables and has one method
    to prompt a user enter data in the input data that stores
    in an arrayList.
 */
public class EmployeeDetail extends Employee{
    private int employeeID;
    private String firstName;
    private String lastName;
    private String hireDate;
    private String status;
    static final AtomicInteger count = new AtomicInteger(1);

    // Default Construtor
    public EmployeeDetail(){
        employeeID = count.incrementAndGet();
        firstName = "";
        lastName = "";
        hireDate = "";
        status = "";
    }

    // Overload constructors store id, first name, last name, hire date, and status
    public EmployeeDetail(int id, String fName, String lName, String hDate, String st ) throws CustomException {
        if(id >= 1){
            employeeID = id;
            count.incrementAndGet();
        }
        else {
            throw new CustomException();
        }
        firstName = fName;
        lastName = lName;
        hireDate = hDate;
        status = st;
    }

    // A method prompt a user to enter input information and add info to
    // the arrayList
    @Override
    public List<EmployeeDetail> getInfo() throws CustomException, IOException {
        List<EmployeeDetail> employeeList = new ArrayList<>();
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
            System.out.print("Enter Status: ");
            status = sc.next();
            System.out.println("***Employee information saved successfully***\n");

            // add input into an arrayList
            employeeList.add(new EmployeeDetail(employeeID, firstName, lastName, hireDate, status));
        }
        return employeeList;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int setEmployeeID(int id) {
        employeeID = id;
        count.incrementAndGet();
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

    public void setHireDate(String hDate) {
        this.hireDate = hDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String st) {
        status = st;
    }

    public ArrayList<EmployeeDetail> getAllEmployees(){
        ArrayList<EmployeeDetail> allEmployees = new ArrayList<>();
        for(EmployeeDetail employeeDetail : getAllEmployees()){
            allEmployees.add(employeeDetail);
        }
        return allEmployees;
    }

}
