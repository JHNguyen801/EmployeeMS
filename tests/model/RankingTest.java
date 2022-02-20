package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/* This class is to test the Ranking class method to make sure the method takes the data in the arrayList sort
 * properly in descending order
 */
class RankingTest {

    // Test the sorted salary in the array list from highest to lowest
    @Test
    void rank() throws EmployeeIDException {
        EmployeeAdd employeeAdd = new EmployeeAdd();
        Ranking rankEmployee = new Ranking();
        ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
        employeeList.add(new EmployeeAdd(1, "Han", "Do","01/01/2015", 50000.0,"active"));
        employeeList.add(new EmployeeAdd(2, "Bo", "Chan","11/01/2021", 55000.0,"active"));
        employeeList.add(new EmployeeAdd(3, "Steve", "Low","11/01/2001", 65000.0,"inactive"));
        // calls the rank method from a Generic Class
        rankEmployee.rank(employeeList);
        Assertions.assertEquals(1,1);
        Assertions.assertEquals(65000, employeeList.get(0).getSalary());
    }

    // Test the Integer in the array list
    void rank2() throws  EmployeeIDException{
        EmployeeAdd employeeAdd = new EmployeeAdd();
        Ranking rankEmployee = new Ranking();
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(10);
        testList.add(2);
        testList.add(50);
        testList.add(1);
        Assertions.assertEquals(50, testList.get(0));
        Assertions.assertEquals(1, testList.get(3));
    }
}
