package model;

import java.util.ArrayList;

/*
    EmployeeShow class read a CSV file that store data in the arrayList.
    It displays the data information from a CSV file.
 */
public class EmployeeShow {

    // readDataFromFile method read the csv file and display info on the screen
    public static ArrayList<EmployeeAdd> displayEmployeeList(ArrayList<EmployeeAdd> employeeList){
        // loop the employee list and display the output from the csv file
        System.out.println("\n\t\t\tEMPLOYEE LIST");
        System.out.println("*******************************************");
        System.out.printf("%5s %15s %15s %15s %10s %12s",
                "EmployeeID: ", "First Name: ", "Last Name: ", "Hire Date: ",
                "Salary", "Status:");

        for(int i = 0; i < employeeList.size(); i++){
            System.out.println();
            System.out.format("%5s %15s %20s %15s %12s %12s", employeeList.get(i).getEmployeeID(),
                employeeList.get(i).getFirstName(), employeeList.get(i).getLastName() ,
                    employeeList.get(i).getHireDate(), employeeList.get(i).getSalary(),
                    employeeList.get(i).getStatus(), "\n");
        }
        return employeeList;
    }
}
