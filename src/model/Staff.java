package model;

public class Staff extends Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String hireDate;
    private String status;

    public Staff(int id, String fName, String lName, String hDate, String st) throws CustomException {
        super(id, fName,lName,hDate,st);
    }

    @Override
    public void getInfo() {
    }

}
