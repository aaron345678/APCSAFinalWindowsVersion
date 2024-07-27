import java.util.*;
import java.io.*;

/**
 * Write a description of class CSVReader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CSVReader
{
    // instance variables - replace the example below with your own
    private static EditNames keyEncryptor;
    private File file;
    /**
     * Constructor for objects of class CSVReader
     */
    public CSVReader()
    {
        keyEncryptor = new EditNames();
        try {
            file = new File(Constants.CSVRead.FilePath);
            importFileToMap();
        } catch (Exception e) {
            System.err.println(Constants.ErrorCodes.NULL_FILE);
        }

    }

    public HashMap<User,Double> importFileToMap() {
        HashMap<User,Double> mapOfStuff = new HashMap<User,Double>();
        mapOfStuff.clear();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] bruh = scanner.nextLine().split(""+Constants.CSVRead.DELIMITER);
                String name = ((bruh[0]));//Add back encryption
                String password = (bruh[1]);
                double money = Double.parseDouble(bruh[2]);
                mapOfStuff.put(new User(name,password),money);
            }
            return mapOfStuff;
        } catch(Exception e) {
            System.err.println("CSV File not found");
        }
        return null;
    }

    public static void saveToCSV(HashMap<User,Double> mapOfStuff) {
        clearCSV();
        // Try-with-resources to ensure the BufferedWriter is closed after use
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.CSVRead.FilePath,false))) {
            // Iterate over the HashMap entries
            for (User set:mapOfStuff.keySet()) {
                writer.write(set.getName() + "," + set.getPassword() + "," + mapOfStuff.get(set));
                writer.newLine();
            } 
        }catch(IOException e) {
            System.err.println("bruh");   
        }
    }
    public static void appendToCSV(HashMap<User,Double> mapOfStuff) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.CSVRead.FilePath,true))) {
            // Iterate over the HashMap entries
            for (User set:mapOfStuff.keySet()) {
                writer.write(set.getName() + "," + set.getPassword() + "," + mapOfStuff.get(set));
                writer.newLine();
            } 
        }catch(IOException e) {
            System.err.println("bruh");   
        }
    }

    public static void clearCSV() {
        try (FileWriter fileWriter = new FileWriter(Constants.CSVRead.FilePath, false)) {
            fileWriter.write("");
        } catch (IOException e) {
            System.err.println("Error clearing the CSV file: " + e.getMessage());
        }
    }
}