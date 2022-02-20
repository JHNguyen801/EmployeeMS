package model;

public class CustomException extends Throwable {

    public boolean checkId(){
        EmployeeDetail employeeDetail = new EmployeeDetail();
        if(employeeDetail.getEmployeeID() <= 0){
            System.out.println("Employee ID must be greater than or equal to 1");
            return true;
        }
        return true;
    }
}
