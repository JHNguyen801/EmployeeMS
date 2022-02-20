package model;

import java.io.IOException;
import java.util.ArrayList;

/*
    SaveTread implements interfaces to save the file after the user
    enters the input from the main() of the application
 */
public class SaveThread implements Runnable {
    EmployeeAdd employeeAdd = new EmployeeAdd();
    EmployeeOutput employeeOutput = new EmployeeOutput();
    ArrayList<EmployeeAdd> employeeList = new ArrayList<>();

    @Override
    public void run() {
        try {
            employeeOutput.saveOutput(employeeList);
        } catch (EmployeeIDException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
