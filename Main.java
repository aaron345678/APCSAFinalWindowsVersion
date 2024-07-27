import java.util.*;
import java.io.File;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
        // initialise instance variables
        x = 0;
    }
    public static void testSaving() {
        UserInteraction bruh = new UserInteraction();
        bruh.addUser(new User("Aaron","password"),20);
        bruh.addUser(new User("Bruh moment","1234456"),12);
        bruh.addUser(new User("Caelyn","blankShoy"),18);
        bruh.addUser(new User("Anna","20243"),2023);
        bruh.addUser(new User("Aaro","TheWorserAaron"),2024);
        bruh.addUser(new User("Bruh momen","oioioi"),1223);
        bruh.addUser(new User("Caely","ugh"),1238);
        bruh.addUser(new User("Ann","23323"),212);
        bruh.saveNewUsers();
    }
    public static void testGetting() {
        UserInteraction bruh = new UserInteraction();
        bruh.printAllMap();
    }
    public static void main(String[] args) {
        OtherGUI.run();
    }
    
}