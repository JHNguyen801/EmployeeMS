package fileInputOutput;

import model.EmployeeAdd;

import java.io.File;
import java.sql.*;


public class DataConnection {
    String connectionURL = "jdbc:sqlite:src/main/java/data/EmployeeDB.db";
    Connection conn = DriverManager.getConnection(connectionURL);
    // A class constructor holds a string of database file
    public DataConnection(String dbfilename) throws SQLException {
        // TODO Auto-generated constructor stub
        if(this.conn == null) {
            connectDB(dbfilename);
        }
        else {
            System.out.println("Connection already opened");
        }
    }

    private void connectDB(String dataFilename) throws SQLException {
        File dataFile = new File("");
        String url = "jdbc:sqlite:" + dataFile.getAbsolutePath() + dataFilename;

        this.conn = DriverManager.getConnection(url);
        System.out.println("Connection to database successfully");
    }

    public void addEmplolyeeToDB(EmployeeAdd employeeAdd) throws SQLException {
        String query = "INSERT INTO Employee(FirstName, LastName, hireDate, status)"
                + " values(? , ? , ? , ?)";
        PreparedStatement prepared = this.conn.prepareStatement(query);
        prepared.setString(1, employeeAdd.getFirstName());
        prepared.setString(2, employeeAdd.getLastName());
        prepared.setString(3, employeeAdd.getHireDate());
        prepared.setString(4, employeeAdd.getStatus());
        prepared.execute();

        String query2 = "INSERT INTO "
                + "Salary(salary)"
                + " values(?)";
        PreparedStatement prepared2 = this.conn.prepareStatement(query2);
//        prepared.setInt(1, employeeAdd.getEmployeeID());
        prepared2.setDouble(1, employeeAdd.getSalary());
        prepared2.execute();
    }

    // insertData() method is for testing the SQL insert statement
    public void insertData() throws SQLException {
        String insertInfo = "INSERT INTO Employee(employeeID,FirstName,LastName,hireDate,status)" +
                "values" +
                "(1, 'Kat', 'Burr', '01/01/2021', 'active')," +
                "(2, 'Han', 'Do', '01/01/2022', 'active')," +
                "(3, 'Adam', 'Lee', '05/15/2021', 'active')," +
                "(4, 'Jake', 'Hunt', '12/21/2001', 'inactive')," +
                "(5, 'Steve', 'Eve', '05/01/2020', 'active')," +
                "(6, 'Ayla', 'Shi', '04/01/2011', 'inactive')," +
                "(7, 'Mel', 'May', '09/01/2018', 'inactive')," +
                "(8, 'Jess', 'Gor', '07/25/2018', 'inactive'),";

        try (PreparedStatement statement = conn.prepareStatement(insertInfo)){
            statement.executeUpdate();
        }
        String insertInfo2 = "INSERT INTO Salary(employeeID,salary)" +
                "VALUES" +
                "(1, 76000.0)," +
                "(2, 65000.0)," +
                "(3, 51000.0)," +
                "(4, 75000.0)," +
                "(5, 68000.0)," +
                "(6, 34000.0)," +
                "(7, 74000.0)," +
                "(8, 66500.0),";
        try (PreparedStatement statement = conn.prepareStatement(insertInfo2)){
            statement.executeUpdate();
        }
    }

    // displayStatusOrder method shows the employees information in order by status
    public void displayStatusOrder() throws SQLException {
        String query = "SELECT Employee.employeeID, FirstName, LastName, hireDate, salary, status " +
                "FROM Employee " +
                "JOIN Salary ON Employee.employeeID = Salary.employeeID ORDER BY status";

        PreparedStatement prepared = this.conn.prepareStatement(query);

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

    public void displayJoin() throws SQLException {
        displayAggregate();
        String query = "SELECT Employee.employeeID, FirstName, LastName, HireDate,salary, status " +
                "FROM Employee " +
                "JOIN Salary ON Employee.employeeID = Salary.employeeID ORDER BY FirstName, LastName";

        PreparedStatement prepared = conn.prepareStatement(query);
        ResultSet rs = prepared.executeQuery();
        ResultSetMetaData resultMeta = rs.getMetaData();

        System.out.println("\n***************************************************" +
                "******************************************");
        //Display columns
        System.out.print("*");
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
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
    }

    // displayAggregate method display the statistic salary data
    public void displayAggregate() throws SQLException {
        String query = "SELECT COUNT(employeeID) AS totalEmployee, min(salary) as minimumSalary," +
                "max(salary), avg(salary)\n" +
                "FROM salary";
        PreparedStatement prepared = this.conn.prepareStatement(query);
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

        /**
         * Close the connection to avoid any
         * problems (database lock)
         */
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
