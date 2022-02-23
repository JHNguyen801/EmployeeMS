package view;

import fileInputOutput.DataConnection;
import fileInputOutput.EmployeeDataLoad;
import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Class EmployeeApp contains the main method that instantiate the class object.
    It is primary use as the main menu option that allow a user to select a choice.
    It calls other menu option that invoke the method to do a request.
 */
public class EmployeeApp {
    public static Scanner select = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static EmployeeAdd staff = new EmployeeAdd();
    private static EmployeeShow es = new EmployeeShow();
    private static DataConnection dataConnection;

    private static final EmployeeDataLoad employeeDataLoad = new EmployeeDataLoad();

    public static void main(String[] args) throws IOException, EmployeeIDException, ClassNotFoundException, InterruptedException, SQLException {
        EmployeeDataLoad eu = new EmployeeDataLoad();
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        // load csv file
        eu.loadData(employeeList);

        // create a new thread
        Thread dLoad = new Thread(new SerializedThread());
        dLoad.start();
        dLoad.join();

        // menu selection option
        mainChoice(employeeList);
    }

    private static void mainChoice(ArrayList<EmployeeAdd> employeeList) throws EmployeeIDException, IOException, ClassNotFoundException, SQLException {
        mainMenu();
        char userInput = 0;
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
                    // create a thread object that implements runnable interface of save output file
                    Thread inputAndSave = new Thread(new AddSaveThread());
                    inputAndSave.start();
                    System.out.println("\nPress Enter to Continue...\n");
                    mainMenu();
                    break;
                }
                case '2': {
                    es.displayEmployeeList(employeeList);
                    System.out.println("\nPress Enter to Continue...\n");
                    System.out.println();
                    mainMenu();
                    break;
                }
                case '3':{
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
                    employeeFilter(employeeList);
                    mainMenu();
                    break;
                }
                case '5': {
                    EmployeeUpdate update = new EmployeeUpdate();
                    es.displayEmployeeList(employeeList);
                    System.out.print("\nPlease Enter Employee's ID: ");
                    int id = select.nextInt();
                    for(int i = 0; i < employeeList.size(); i++){
                        if(id == employeeList.get(i).getEmployeeID()){
                            update.updateEmployee(employeeList,i);
                        }
                    }
                    System.out.println("\nPress Enter to Continue...\n");
                    System.out.println();
                    mainMenu();
                    break;
                }
                case '6':{
                    DBMenu();
                    break;
                }
                case '7': {
                    EmployeeDataLoad.serializeEmployee(staff);
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

    public static void DBMenu() throws SQLException, EmployeeIDException, IOException,
            ClassNotFoundException {
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        boolean out = false;
        char userInput;
        DataConnection dataConnect = null;
        try {
             dataConnect = new DataConnection("EmployeeDB.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (!out) { // Menu loop
            DBSubMenu();

            System.out.print("\nPlease enter the option above: ");
            userInput = select.nextLine().charAt(0);

            switch (userInput) {
                case '1':
                    try {
                        dataConnect.displayStatusOrder();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    DBSubMenu();
                    break;
                case '2':
                {
                    try {
                        dataConnect.displayJoin();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    DBSubMenu();
                    break;
                }
                case '3':
                {
                    mainChoice(employeeList);
                    dataConnect.close();
                    break;
                }
                default:
                    DBSubMenu();
                    System.out.println("Wrong input");
            }
        }// Loop end
    }

    private static void employeeFilter(ArrayList<EmployeeAdd> employeeList) throws EmployeeIDException,
            IOException, ClassNotFoundException, SQLException {
        boolean valid = false;
        char userInput = 0;
        System.out.println("\n\t\t  Filter Submenu");
        System.out.println("*****************************************");
        System.out.println("Enter 1: To filter status");
        System.out.println("Enter 2: To filter salary");
        System.out.println("Enter 3: Back to the main menu");
        while (!valid) {
            try {
                System.out.print("\nPlease Enter a number: ");
                userInput = select.nextLine().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.print("\nPlease enter an input number: ");
                userInput = select.nextLine().charAt(0);
            }
            /** Switch Case Statements **/
            switch (userInput) {
                case '1': {
                    EmployeeFilter employeeFilter = new EmployeeFilter();
                    employeeFilter.statusFilter(employeeList);
                    subMenu();
                    break;
                }
                case '2': {
                    EmployeeFilter employeeFilter = new EmployeeFilter();
                    employeeFilter.salaryFilter(employeeList);
                    subMenu();
                    break;
                }
                case '3': {
                    mainChoice(employeeList);
                    break;
                }
                default:
                    subMenu();
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
        System.out.println("Enter 4: To Filter Employee Info ");
        System.out.println("Enter 5: To Update Employee Info ");
        System.out.println("Enter 6: Connect/View Database ");
        System.out.println("Enter 7: To Exit the EIMS Portal ");
    }
    private static void subMenu(){
        System.out.println("\n\t\t  Filter Submenu");
        System.out.println("*****************************************");
        System.out.println("Enter 1: To filter status");
        System.out.println("Enter 2: To filter salary");
        System.out.println("Enter 3: Back to the main menu");
    }

    private static void DBSubMenu(){
        System.out.println("\n\t\t  Database View Submenu");
        System.out.println("*****************************************");
        System.out.println("Enter 1: Display employees in status order");
        System.out.println("Enter 2: Display Join & Aggregate Employee Info");
        System.out.println("Enter 3: Back to the main menu" + "\n");
    }
}

