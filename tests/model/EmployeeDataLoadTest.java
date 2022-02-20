package model;

import fileInputOutput.EmployeeDataLoad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDataLoadTest {
    // Test the program to load data from a file
    @Test
    void loadData() throws EmployeeIDException {
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        EmployeeDataLoad employeeDataLoad = new EmployeeDataLoad();
        EmployeeAdd employeeAdd = new EmployeeAdd(1,"Kim", "Lee", "05/01/2015", 55000, "active");
        employeeList.add(employeeAdd);
        employeeDataLoad.loadData(employeeList);
        Assertions.assertEquals(1,employeeAdd.getEmployeeID());
    }

    // Test the serialize of a binary file
    @Test
    void serializeEmployee() throws EmployeeIDException {
        String filePath = "src/main/java/data/employee.dat";
        EmployeeDataLoad employeeDataLoad = new EmployeeDataLoad();
        EmployeeAdd employeeAdd = new EmployeeAdd(1,"Kim", "Lee", "05/01/2015", 55000, "active");
        employeeDataLoad.serializeEmployee(employeeAdd);
        Assertions.assertEquals(1,employeeAdd.getEmployeeID());
    }

    // Test the deserialize of a binary file
    @Test
    void deserializeEmployee() throws EmployeeIDException {
        String filePath = "src/main/java/data/employee.dat";
        EmployeeDataLoad employeeDataLoad = new EmployeeDataLoad();
        EmployeeAdd employeeAdd = new EmployeeAdd(1,"Kim", "Lee", "05/01/2015", 55000, "active");
        try {
            employeeDataLoad.deserializeEmployee(employeeAdd);
        } catch (ClassNotFoundException | IOException e) {
            fail("Cannot deserialize object");
        }
        Assertions.assertEquals(1,employeeAdd.getEmployeeID());
    }
}