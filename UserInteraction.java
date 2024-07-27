import java.util.HashMap;
import javax.swing.*;
/**
 * Write a description of class UserInteraction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserInteraction
{
    // instance variables - replace the example below with your own
    private int x;
    private CSVReader csvReader;
    private static JFrame frame = new JFrame("Banking App");
    private static HashMap<User,Double> allUsers = new HashMap<User,Double>();
    private static HashMap<User,Double> newUsers = new HashMap<User,Double>();
    /**
     * Constructor for objects of class UserInteraction
     */
    public UserInteraction()
    {
        csvReader = new CSVReader();
        allUsers = csvReader.importFileToMap();
        frame.setSize(800,600);
    }
    
    public double getPersonalMoney(User user) {
        Double money = allUsers.get(user);
        if (money == null) {
            System.out.println(Constants.ErrorCodes.NULL_USER);
            return Integer.MIN_VALUE;
        } else {
            return money;
        }
    }
    public boolean changeMoney(User user, double change) {
        double currentMoney = getPersonalMoney(user);
        if (currentMoney+change>=0) {
            allUsers.put(user, currentMoney + change);
            csvReader.saveToCSV(allUsers);
            return true;
        } else {
            return false;
        }
    }
    public User getUser(String name) {
        for(User set:allUsers.keySet()) {
            if(set.getName().equals(name)) {
                return set;
            }
        }
        return null;
    }
    public boolean checkUserNull(String name) {
        if(getUser(name) == null) {
            return true;
        }
        return false;
    }
    public static void printAllMap() {
        for(User set:allUsers.keySet()) {
            System.out.print(set.getName() + ": "  + set.getPassword() + ":" + allUsers.get(set));
            System.out.println();
        }
    }
    public boolean addUser(User user,double money) {
    if(checkUserNull(user.getName())) {
        if(user.getName().indexOf(",") == -1) {
            newUsers.put(user,money);
            return true;
    } else {
        JOptionPane.showMessageDialog(frame,"Illegal character (,)");
        return false;
    }
    } else {
        JOptionPane.showMessageDialog(frame,"User already exists");
        return false;
    }
    }
    public void save() {
        csvReader.saveToCSV(allUsers);
    }
    public void saveNewUsers() {
        csvReader.appendToCSV(newUsers);
        //for each loop add
        for(User set: newUsers.keySet()) {
            allUsers.put(set,newUsers.get(set));
        }
        //clear new users
        newUsers.clear();
        System.err.println("Saved new users");
    }
    public boolean login(User user) {
        String name = user.getName();
        String password = user.getPassword();
        
        for(User set:allUsers.keySet()) {
            if(set.getName().equals(name)) {
                if(set.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
