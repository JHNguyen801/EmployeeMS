package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
    EmployeeUpdate class is reading the data and update the employee
    information. It is not yet implement.
 */
public class EmployeeUpdate
{
    ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
    EmployeeShow employeeShow = new EmployeeShow();
    EmployeeAdd ed = new EmployeeAdd();

    public static ArrayList<EmployeeAdd> loadData(ArrayList<EmployeeAdd> employeeList) {
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmployeeIDException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public double updateEmployee(ArrayList<EmployeeAdd> employeeList,int id){
        Scanner sc = new Scanner(System.in);
//        loadData(employeeList);
        double newSalary = 0;
        System.out.print("Please enter a new salary: ");
        newSalary = sc.nextDouble();
        ed.setSalary(newSalary);
        System.out.println("Salary updated");
        return newSalary;
    }
}
