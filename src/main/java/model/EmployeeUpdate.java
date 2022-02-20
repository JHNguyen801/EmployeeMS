package model;

import view.EmployeeApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    This class is to prompt a user to enter an employeeID that he or she wants
    to update the salary. It is not yet implement to implement.
 */
public class EmployeeUpdate {

        public double updateEmployee(ArrayList<EmployeeAdd> employeeList, int id) throws EmployeeIDException,
                IOException {
        Scanner sc = new Scanner(System.in);
        EmployeeOutput eo = new EmployeeOutput();
        double newSalary = 0;
        do {
            if (newSalary < 0) {
                System.out.println("You have entered incorrect amount: ");
                System.out.print("Please enter a new salary: ");
                newSalary = sc.nextDouble();
                break;
            } else {
                System.out.print("Please enter a new salary: ");
                newSalary = sc.nextDouble();
                employeeList.get(id).setSalary(newSalary);
                System.out.println("You have entered " + newSalary);
                break;
            }
        } while (true);

        System.out.print("If this a correct salary, do you want to save? ");
        String confirm = sc.next();
        if (confirm.equals("yes")) {
            eo.updateAndSave(employeeList);
            System.out.println("Salary updated");
        }
        else if (confirm.equals("no")) {
        }
        return newSalary;
    }
}
