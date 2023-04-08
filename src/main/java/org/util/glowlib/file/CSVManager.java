package org.util.glowlib.file;

import java.io.*;
import java.util.ArrayList;

public class CSVManager {

    /**
     * Saves the 2D array as a .csv file (Comma Separated Values). This file is saved to the run directory of the program
     * @param list
     * @param folderPath
     * @param fileName
     */
    public static <T> boolean saveAsCSV(ArrayList<ArrayList<T>> list, String folderPath, String fileName) {
        FileManager.createFolderPath(folderPath); //Creates the folder path if it does not exist
        String outputFile = folderPath + "/" + fileName + ".csv";
        try {
            FileWriter writer = new FileWriter(outputFile);
            for (ArrayList<T> rowData : list) {
                writer.write(String.join(",", (ArrayList<String>)rowData) + "\n");
                /* All data in the array is converted to a string. This will slow down the saving method slightly, but it is not that bad
                ArrayList<String> stringRowData = new ArrayList<>();
                rowData.forEach((var) -> stringRowData.add(var.toString()));
                writer.write(String.join(",", stringRowData) + "\n");
                */
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing data to " + outputFile);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a 2D array as the table data fom the .csv file to be used within the program. This method is very
     * useful for retrieving saved data in csv format and saved tables
     * @param folderPath
     * @param fileName
     * @return
     */
    public static ArrayList<String[]> getDataFromCSV(String folderPath, String fileName) {
        File file = new File(folderPath + "/" + fileName + ".csv");
        ArrayList<String[]> rawDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                rawDataList.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawDataList;
    }

}
