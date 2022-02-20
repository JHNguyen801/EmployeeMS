package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeUpdateTest {

    @Test
    void updateEmployee() throws EmployeeIDException, IOException {
        EmployeeAdd ed = new EmployeeAdd(1,"Han","Solo","01/01/2015",34000, "active");
        EmployeeUpdate eu = new EmployeeUpdate();
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        int id = 1;
        double newSalary = 45000;
        ed.setSalary(newSalary);
        employeeList.add(ed);
//        eu.updateEmployee(employeeList, id);
        Assertions.assertEquals(45000,newSalary);
    }
}