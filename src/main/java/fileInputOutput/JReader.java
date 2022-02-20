package fileInputOutput;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.EmployeeAdd;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JReader {
//    ArrayList<EmployeeAdd> employeeList = new ArrayList<>();
//    EmployeeAdd employeeAdd = new EmployeeAdd();
    public void saveListToFile(ArrayList<EmployeeAdd> employeeList) {
        String filename = "src/main/java/data/employee.json";
//        File file = new File(filename);
        ObjectMapper mapper = new ObjectMapper();
        for(EmployeeAdd emp : employeeList) {
            try {
                mapper.writeValue(new File(filename), employeeList);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Boolean saveJSON(ArrayList<EmployeeAdd> employeeList) {
        String filename = "src/main/java/data/employee.json";
        boolean status = true;
        File file = new File(filename);
        try {
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file, status));
            Gson gson = new Gson();
//            Type type = new TypeToken<ArrayList<EmployeeAdd>>() {}.getType();
            String json = gson.toJson(employeeList, EmployeeAdd.class);
            buffWriter.append(json);
            buffWriter.newLine();
            buffWriter.close();
        } catch (IOException e) {
            status = false;
        }
        return status;
    }

    public int readCurrentList(ArrayList<EmployeeAdd> employeeList) {
        String filename = "employee.json";
        File file = new File(filename);
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            String line;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<EmployeeAdd>>() {}.getType();
            while ((line = buffReader.readLine()) != null) {
                employeeList.addAll(gson.fromJson(line, type));
            }
            buffReader.close();
        } catch (IOException e) {
            return -1;
        }
        return 0;
    }
}

