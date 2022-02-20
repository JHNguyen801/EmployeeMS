package view;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;

import static fileInputOutput.FileController.loadData;

/*
    Class EmployeeApp contains the main method that instantiate the class object.
    It is primary use as the main menu option that allow a user to select a choice.
    It calls other menu option that invoke the method to do a request.
 */
public class EmployeeApp {
    public static void main(String[] args) throws IOException, EmployeeIDException {
        EmployeeShow es = new EmployeeShow();
        EmployeeAdd staff = new EmployeeAdd();
        EmployeeUpdate eu = new EmployeeUpdate();
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        eu.loadData(employeeList);
        Scanner select = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        char userInput = 0;

        mainMenu();
        boolean valid = false;

        /*** Initializing loop for Menu Choices ***/
        while (!valid) {
            try {
                System.out.print("\nPlease Enter a number: ");
                userInput = select.nextLine().charAt(0);
            }
            catch(StringIndexOutOfBoundsException e){
                System.out.print("\nPlease enter an input number: ");
                userInput = select.nextLine().charAt(0);
            }

            /** Switch Case Statements **/
            switch (userInput) {
                case '1': {
                    /** Creating class's object and calling Function using that object **/
                    EmployeeOutput se = new EmployeeOutput();
                    employeeList = staff.getInfo();
                    se.saveOutput(employeeList);
                    System.out.println("\nPress Enter to Continue...\n");
                    System.out.println();
                    mainMenu();
                    break;
                }
                case '2': {
<<<<<<< HEAD
                    es.readDataFromFile(employeeList);
=======
                    es.readDataFromFile();
>>>>>>> 911e544d88a2944350a44055768be22ff14dc02e
                    System.out.println("\nPress Enter to Continue...\n");
                    System.out.println();
                    mainMenu();
                    break;
                }
                case '3':{
<<<<<<< HEAD
//                    ArrayList<EmployeeAdd> esList = eu.loadData(employeeList);
=======
                    ArrayList<EmployeeAdd> esList = eu.loadData();
>>>>>>> 911e544d88a2944350a44055768be22ff14dc02e
                    int rank = 1;
                    Ranking employeeRank = new Ranking();
                    employeeRank.rank(employeeList);

                    System.out.println("\n\t\t\tEMPLOYEE LIST - Sort from Highest to Lowest Salary");
                    System.out.println("************************************************************");
                    System.out.printf("%5s %15s %15s %15s %10s %12s","EmployeeID: ",
                            "First Name: ", "Last Name: ", "Hire Date: ",
                            "Salary", "Status:");
                    for(int i = 0; i < employeeList.size(); i++){
                        System.out.println();
                        System.out.format("%5s %15s %20s %15s %12s %12s",
                                employeeList.get(i).getEmployeeID(), employeeList.get(i).getFirstName(),
                                employeeList.get(i).getLastName(), employeeList.get(i).getHireDate(),
                                employeeList.get(i).getSalary(), employeeList.get(i).getStatus());
                        rank++;
                    }
                    System.out.print("\nPress Enter to Continue...\n");
                    System.out.println();
                    mainMenu();
                    break;
                }
                case '4': {
                    EmployeeUpdate update = new EmployeeUpdate();
<<<<<<< HEAD
                    es.readDataFromFile(employeeList);
                    System.out.print("\nPlease Enter Employee's ID: ");
                    int id = select.nextInt();
                    for(int i = 0; i < employeeList.size(); i++){
                        if(id == employeeList.get(i).getEmployeeID()){
                            update.updateEmployee(employeeList,id);
                        }
=======
                    System.out.println("Please Enter Employee's ID :");
                    int id = select.nextInt();
                    if(id == staff.getEmployeeID()){
                        update.updateEmployee(id);
>>>>>>> 911e544d88a2944350a44055768be22ff14dc02e
                    }
                    System.out.println("\nPress Enter to Continue...\n");
                    System.out.println();
                    mainMenu();
                    break;
                }
                case '5': {
                    System.out.println("\n*****************************************");
                    System.out.println("\t Program terminated ");
                    System.exit(0);
                }
                mainMenu();
                default:
                    System.out.println("Wrong input, it must be a number");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t  Employee Information Management System");
        System.out.println("\t\t*******************************************");
        System.out.println("Enter 1: To Add an Employee Details ");
        System.out.println("Enter 2: To See an Employee Details ");
        System.out.println("Enter 3: To Sort Employee by Salary ");
        System.out.println("Enter 4: To Update Employee Details ");
        System.out.println("Enter 5: To Exit the EIMS Portal ");
    }
}
