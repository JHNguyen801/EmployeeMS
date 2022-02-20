package model;

public class EmployeeIDException extends Exception{
        EmployeeAdd ed = new EmployeeAdd();
        public EmployeeIDException(){
            if(ed.getEmployeeID() < 0) {
                System.out.println("Employee ID must be greater than or equal to 1");
            }
    }
}
