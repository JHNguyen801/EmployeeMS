package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EmployeeAddTest {
    // Test to see the constructor store data correctly
    @Test
    public void testConstructor() throws EmployeeIDException {
        EmployeeAdd employeeAdd = new EmployeeAdd(1, "Han", "Solo",
                "04/01/2015", 50000,"active");
        Assertions.assertEquals("Han", employeeAdd.getFirstName());
    }

    @Test
    void getInfo() throws EmployeeIDException {
        List<EmployeeAdd> employeeList = new ArrayList<>();
        EmployeeAdd ed = new EmployeeAdd();
            int employeeId = 1;
            String firstName = "Jake";
            String lastName = "Lee";
            String hireDate = "01/15/2021";
            String status = "active";
            double salary = 50000;

            employeeList.add(new EmployeeAdd(employeeId, firstName, lastName, hireDate, salary,status));

            for(int i = 0; i < employeeList.size(); i++){
                Assertions.assertEquals(employeeList.get(i).getFirstName(),firstName);
                Assertions.assertEquals(employeeList.get(i).getLastName(),lastName);
                Assertions.assertEquals(employeeList.get(i).getHireDate(),hireDate);
                Assertions.assertEquals(employeeList.get(i).getSalary(),salary);
                Assertions.assertEquals(employeeList.get(i).getStatus(),status);
            }
    }
}