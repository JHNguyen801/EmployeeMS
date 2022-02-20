package view;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/*
    Class EmployeeMSDemo contains the main method that instantiate the class object.
    It calls the displayEmployeeInfo method to print the information stores in the
    class constructor.
 */
public class EmployeeApp {
    public static void main(String[] args) throws IOException, CustomException {
        EmployeeShow es = new EmployeeShow();
        EmployeeDetail staff = new EmployeeDetail();
        Scanner select = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        char userInput;

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
                    EmployeeDetail ed = new EmployeeDetail();
                    List<EmployeeDetail> employeeList = ed.getInfo();
                    se.saveOutput(employeeList);
                    mainMenu();
                    break;
                }
                case '2': {
                    es.readDataFromFile();
                    System.out.print("\nPress Enter to Continue...");
                    select.nextLine();
                    mainMenu();
                    break;
                }
                case '3': {
                    System.out.print("\nPlease Enter Employee's ID :");
                    String I = select.nextLine();
                    try {
                        es.viewFile(I);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    EmployeeUpdate update = new EmployeeUpdate();
                    System.out.print("Please Enter the detail you want to Update :");
                    System.out.print("\nFor Example :\n");
                    System.out.println("If you want to Change the Name, then Enter Current Name and Press Enter. "
                            + "Then write the new Name then Press Enter. It will Update the Name.\n");
                    String s = select.nextLine();
                    System.out.print("Please Enter the Updated Info :");
                    String n = select.nextLine();
                    try {
                        update.updateFile(I, s, n);
                        System.out.print("\nPress Enter to Continue...");
                        select.nextLine();
                        mainMenu();
                        break;
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                case '4': {
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
        System.out.println("Enter 1 : To Add an Employee Details ");
        System.out.println("Enter 2 : To See an Employee Details ");
        System.out.println("Enter 3 : To Update Employee Details ");
        System.out.println("Enter 4 : To Exit the EIMS Portal ");
    }
}
