package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeIDExceptionTest {

    @Test
    void checkId() {
        EmployeeAdd employeeAdd = new EmployeeAdd();
        int employId = employeeAdd.setEmployeeID(1);
        if(employId >= 1) {
            assertEquals(1, employId);
        }
    }
}