/**
 * Write a description of class User here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User
{
    /**
     * Constructor for objects of class User
     */
    private String name;
    private String password;
    
    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public boolean checkPassword(String entry) {
        return password.equals(entry);
    }
    public String getPassword() {
        return password;
    }
}