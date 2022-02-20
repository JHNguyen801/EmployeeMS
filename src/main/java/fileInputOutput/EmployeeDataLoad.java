package fileInputOutput;

import model.EmployeeAdd;
import model.EmployeeIDException;

import java.io.*;
import java.util.ArrayList;
/*
    EmployeeDataLoad class is reading the data from a CSV file and store
    data into an ArrayList.
 */
public class EmployeeDataLoad
{
    // Load method is to read CSV file and store data in the ArrayList when
    // the program is launch
    public static ArrayList<EmployeeAdd> loadData(ArrayList<EmployeeAdd> employeeList) {
        EmployeeAdd employeeAdd = new EmployeeAdd();
        FileInputStream fstream = null;
        try {
            File file = new File("src/main/java/data/employee.csv");
            fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strline = "";
            String[] token = strline.split(",");

            // read file line by line
            while ((strline = br.readLine()) != null) {
                token = strline.split(",");
                employeeAdd = new EmployeeAdd(Integer.parseInt(token[0]), token[1], token[2],
                        token[3], Double.parseDouble(token[4]), token[5]);
                employeeList.add(employeeAdd);
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmployeeIDException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /*
    This method saves the current object EmployeeAdd in a file using serialization
    */
    public static void serializeEmployee( EmployeeAdd employeeAdd)
    {
        String filePath = "src/main/java/data/employee.dat";
        File serialize = new File(filePath);
        try(ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream(serialize));){
            outfile.writeObject(employeeAdd);
            outfile.close();
            System.out.println("\nFile successfully saved using Serialization");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    This method reads the current object EmployeeAdd in a file using serialization
    */
    public static void deserializeEmployee(EmployeeAdd employeeAdd) throws
            IOException, ClassNotFoundException {
        String filePath = "src/main/java/data/employee.dat";
        File serialize = new File(filePath);
        try(ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(serialize));) {
            employeeAdd = (EmployeeAdd) inFile.readObject();
            System.out.println("File successfully loaded using Serialization\n");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
