package fileInputOutput;

import model.EmployeeAdd;
import model.EmployeeIDException;
import org.junit.jupiter.api.Test;

import java.sql.*;

class DataConnectionTest {
    String connectionURL = "jdbc:sqlite:src/main/java/data/EmployeeDB_test.db";
    Connection conn = DriverManager.getConnection(connectionURL);

    DataConnectionTest() throws SQLException {
    }

    @Test
    void insertData() throws SQLException {
        Statement statement = conn.createStatement();
        try {
            statement.executeUpdate("INSERT INTO Employee VALUES (1, 'Kat', 'Burr', '01/01/2021', 'active')");
            statement.executeUpdate("INSERT INTO Employee VALUES (2, 'Han', 'Do', '01/01/2022', 'active')");
            statement.executeUpdate("INSERT INTO Employee VALUES (3, 'Adam', 'Lee', '05/15/2021', 'active')");
            statement.executeUpdate("INSERT INTO Employee VALUES (4, 'Jake', 'Hunt', '12/21/2001', 'inactive')");
            statement.executeUpdate("INSERT INTO Employee VALUES (5, 'Steve', 'Eve', '05/01/2020', 'active')");
            statement.executeUpdate("INSERT INTO Employee VALUES (6, 'Ayla', 'Shi', '04/01/2011', 'inactive')");
            statement.executeUpdate("INSERT INTO Employee VALUES (7, 'Mel', 'May', '09/01/2018', 'inactive')");
            statement.executeUpdate("INSERT INTO Employee VALUES (8, 'Jess', 'Gor', '07/25/2018', 'inactive')");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement2 = conn.createStatement();
        try{
            statement2.executeUpdate("INSERT INTO Salary VALUES(1, 76000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(2, 65000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(3, 51000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(4, 75000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(5, 68000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(6, 34000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(7, 74000.0)");
            statement2.executeUpdate("INSERT INTO Salary VALUES(8, 66500.0)");
            statement2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void addEmplolyeeToDB() throws SQLException, EmployeeIDException {
        EmployeeAdd employeeAdd = new EmployeeAdd(9,"Jen","Tran","11/05/1992",41000,"active");
        String query = "INSERT INTO Employee(FirstName, LastName, hireDate, status)"
                + " values(? , ?, ?, ?)";
        PreparedStatement prepared = this.conn.prepareStatement(query);

        prepared.setString(1, employeeAdd.getFirstName());
        prepared.setString(2, employeeAdd.getLastName());
        prepared.setString(3, employeeAdd.getHireDate());
        prepared.setString(4, employeeAdd.getStatus());
        prepared.execute();
        prepared.close();

        String query2 = "INSERT INTO "
                + "Salary(salary)"
                + " values(41000)";
        PreparedStatement prepared2 = this.conn.prepareStatement(query2);
        prepared.setDouble(1, employeeAdd.getSalary());
        prepared2.execute();
        prepared2.close();
    }

    @Test
    void displayStatusOrder() throws SQLException {
        String query = "SELECT Employee.employeeID, FirstName, LastName, hireDate, salary, status " +
                "FROM Employee " +
                "JOIN Salary ON Employee.employeeID = Salary.employeeID ORDER BY status";

        PreparedStatement prepared = conn.prepareStatement(query);

        ResultSet rs = prepared.executeQuery();
        ResultSetMetaData resultMeta = rs.getMetaData();
        int columnsNumber = resultMeta.getColumnCount();

        System.out.println("\n***************************************************" +
                "******************************************");
        //Display column header
        System.out.print("*");
        for(int i = 1; i <= columnsNumber; i++)
            System.out.printf("%-10s\t" ,resultMeta.getColumnName(i).toUpperCase()
                    + "\t |");

        System.out.println("\n***************************************************" +
                "******************************************");

        while(rs.next()) {
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.printf("%-15s\t", rs.getObject(i).toString());

            System.out.println("\n-----------------------------------------------" +
                    "----------------------------------------------");
        }
        rs.close();
        prepared.close();
    }

    @Test
    void displayJoin() throws SQLException {
        displayAggregate();
        String query = "SELECT Employee.employeeID, FirstName, LastName, salary, status " +
                "FROM Employee " +
                "JOIN Salary ON Employee.employeeID = Salary.employeeID ORDER BY FirstName, LastName";

        PreparedStatement prepared = conn.prepareStatement(query);

        ResultSet rs = prepared.executeQuery();
        ResultSetMetaData resultMeta = rs.getMetaData();

        System.out.println("\n***************************************************" +
                "********************************");
        //Display columns
        System.out.print("*");
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            System.out.print("\t"
                    + resultMeta.getColumnName(i).toUpperCase()
                    + "\t *");

        System.out.println("\n***************************************************" +
                "********************************");
        while(rs.next()) {
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t"
                        + rs.getObject(i).toString()
                        + "\t\t |");

            System.out.println("\n---------------------------------------------------" +
                    "-------------------------------");
        }
        rs.close();
        prepared.close();
    }

    @Test
    void displayAggregate() throws SQLException {
        String query = "SELECT COUNT(employeeID) AS totalEmployee, min(salary) as minimumSalary," +
                "max(salary), avg(salary)\n" +
                "FROM salary";
        PreparedStatement prepared = conn.prepareStatement(query);
        ResultSet rs = prepared.executeQuery();
        ResultSetMetaData resultMeta = rs.getMetaData();
        System.out.printf("%10s %12s %14s %14s", "Total Employee", "Min Salary", "Max Salary", "Avg Salary");
        System.out.println("\n--------------------------------------------------------------");
        while(rs.next()) {
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t"
                        + rs.getObject(i).toString()
                        + "\t\t |");
            System.out.println("\n--------------------------------------------------------------");
        }
        rs.close();
        prepared.close();
    }

//    @Test
//    void updateEmployee() throws SQLException, EmployeeIDException {
//        EmployeeAdd empAdd = new EmployeeAdd(13,"Jen","Tran","11/05/1992",41000,"active" );
//        String updateEmployee = "UPDATE Salary SET salary = 54000 WHERE employeeID = 11";
//        PreparedStatement updateEmployeeStatement = this.conn.prepareStatement(updateEmployee);
//        updateEmployeeStatement.setDouble(1, empAdd.getSalary());
//        updateEmployeeStatement.executeUpdate();
//        updateEmployeeStatement.close();
//    }

    /**
     * Close the connection
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}