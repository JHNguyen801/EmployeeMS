package model;

import fileInputOutput.DataConnection;
import fileInputOutput.JReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*
    SaveTread implements interfaces to save the file after the user
    enters the input from the main() of the application
 */
public class AddSaveThread implements Runnable{
    EmployeeOutput employeeOutput = new EmployeeOutput();
    EmployeeAdd employeeAdd = new EmployeeAdd();
    DataConnection dataConnection = new DataConnection("EmployeeDB.db");
    ArrayList<EmployeeAdd> employeeList = employeeAdd.getInfo();
//    JReader jReader =  new JReader();

    // A default constructor
    public AddSaveThread() throws EmployeeIDException, IOException, SQLException {
    }

    @Override
    public void run() {
        try {
//            jReader.saveListToFile(employeeList);
            employeeOutput.saveOutput(employeeList);
            dataConnection.addEmplolyeeToDB(employeeAdd);
        } catch (EmployeeIDException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
