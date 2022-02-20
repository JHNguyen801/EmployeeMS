package model;

import java.io.*;
import java.util.ArrayList;

/*
    EmployeeShow class read a CSV file that store data in the arrayList.
    It displays the data information from a CSV file.
 */
public class EmployeeShow {
    // readDataFromFile method read the csv file and display info on the screen
<<<<<<< HEAD
    public static ArrayList<EmployeeAdd> readDataFromFile(ArrayList<EmployeeAdd> employeeList){
=======
    public static ArrayList<EmployeeAdd> readDataFromFile(){
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        EmployeeAdd employeeAdd = new EmployeeAdd();
        FileInputStream fstream = null;
        try {
            File file = new File("src/data/employee.csv");
            fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strline = "";
            String[] token = strline.split(",");

            // read file line by line
            while ((strline = br.readLine()) != null) {
                token = strline.split(",");

                employeeAdd = new EmployeeAdd(Integer.parseInt(token[0]), token[1], token[2],
                        token[3], Double.parseDouble(token[4]), token[5]);
                employeeList.add(employeeAdd);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (NumberFormatException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmployeeIDException e) {
            e.printStackTrace();
        }
>>>>>>> 911e544d88a2944350a44055768be22ff14dc02e
        // loop the employee list and display the output from the csv file
        System.out.println("\n\t\t\tEMPLOYEE LIST");
        System.out.println("*******************************************");
        System.out.printf("%5s %15s %15s %15s %10s %12s","EmployeeID: ",
                "First Name: ", "Last Name: ", "Hire Date: ",
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
