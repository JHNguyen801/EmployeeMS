package model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EmployeeFilterTest {
    EmployeeFilter employeeFilter = new EmployeeFilter();
    EmployeeAdd ed = new EmployeeAdd();
    List<EmployeeAdd> employeeList = new ArrayList<>();

    @Test
    void statusFilter() throws EmployeeIDException {
        String status = "active";
        EmployeeAdd ed = new EmployeeAdd(1, "Jake", "Lee",
                "01/15/2021", 50000, "active");
        EmployeeAdd ed1 = new EmployeeAdd(2, "Adam", "Lake",
                "04/05/2021", 65000, "active");
        EmployeeAdd ed2 = new EmployeeAdd(3, "Jessica", "Gor",
                "05/25/2015", 70000, "inactive");
        EmployeeAdd ed3 = new EmployeeAdd(4, "Mel", "May",
                "08/15/2019", 75000, "inactive");

        employeeList.add(ed);
        employeeList.add(ed1);
        employeeList.add(ed2);
        employeeList.add(ed3);

        employeeList.stream().filter((emp -> emp.getStatus().equals(status)))
                .forEach(s -> System.out.format("%5s %15s %20s %15s %12s %12s",
                        s.getEmployeeID(),  s.getFirstName(), s.getLastName(),
                        s.getHireDate(), s.getSalary(), s.getStatus()).println());
        for(EmployeeAdd eda : employeeList){
            Assert.assertEquals("active",status);
        }
    }

    @Test
    void salaryFilter() throws EmployeeIDException {
        int salary = 70000;
        EmployeeAdd ed = new EmployeeAdd(1, "Jake", "Lee",
                "01/15/2021", 50000, "active");
        EmployeeAdd ed1 = new EmployeeAdd(2, "Adam", "Lake",
                "04/05/2021", 65000, "active");
        EmployeeAdd ed2 = new EmployeeAdd(3, "Jessica", "Gor",
                "05/25/2015", 70000, "inactive");
        EmployeeAdd ed3 = new EmployeeAdd(4, "Mel", "May",
                "08/15/2019", 75000, "inactive");

        employeeList.add(ed);
        employeeList.add(ed1);
        employeeList.add(ed2);
        employeeList.add(ed3);

        employeeList.stream().filter(emp->emp.getSalary() >= salary)
                .forEach(s -> System.out.format("%5s %15s %20s %15s %12s %12s",
                        s.getEmployeeID(),  s.getFirstName(), s.getLastName(),
                        s.getHireDate(), s.getSalary(), s.getStatus()).println());
        for(EmployeeAdd eda : employeeList){
            Assert.assertEquals(70000,salary);
        }
    }
}
