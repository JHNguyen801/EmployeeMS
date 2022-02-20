package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
    EmployeeOutput class  has a method takes the arraylist and
    write data to a csv all at once.
 */
public class EmployeeOutput {

    // A method to write a user input into a text file
    public Boolean saveOutput(List<EmployeeAdd> employeeList) throws EmployeeIDException, IOException {
        boolean status = true;
        File filename = new File("src/main/java/data/employee.csv");

        // loop the employee list and write to a file
        for(EmployeeAdd emp : employeeList) {
            try(FileWriter fw = new FileWriter(filename, status);
                PrintWriter myWriter = new PrintWriter(fw);) {
                myWriter.print(emp.getEmployeeID() + "," + emp.getFirstName() +
                        "," + emp.getLastName() + "," + emp.getHireDate() + "," +
                        emp.getSalary() + "," + emp.getStatus() + "\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                status = false;
            }
        }
        return status;
    }

    public Boolean updateAndSave(List<EmployeeAdd>employeeList) throws EmployeeIDException,IOException{
        boolean status = true;
        File filename = new File("src/main/java/data/employee.csv");

        // loop the employee list and write to a file
        for(EmployeeAdd emp : employeeList) {
            try(FileWriter fw = new FileWriter(filename, status);
                PrintWriter myWriter = new PrintWriter(fw);) {
                myWriter.print(emp.getEmployeeID() + "," + emp.getFirstName() +
                        "," + emp.getLastName() + "," + emp.getHireDate() + "," +
                        emp.getSalary() + "," + emp.getStatus() + "\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                status = false;
            }
        }
        return status;
    }
}
