package model;

import fileInputOutput.EmployeeDataLoad;

import java.io.IOException;

/*
    SerializedThread implements the Runnable interface to load the serialized dat
    file
 */
public class SerializedThread implements Runnable {
    @Override
    public void run() {
        EmployeeDataLoad employeeDataLoad = new EmployeeDataLoad();
        EmployeeAdd staff = new EmployeeAdd();
        try {
            employeeDataLoad.deserializeEmployee(staff);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
