package oop;

/***
 *
 *
 *
 *
 */
public class User {
    private String username;
    private String name;
    private int yearofbirth;

    /**
     *
     * @param username
     * @param name
     * @param yearofbirth
     */
    public User(String username, String name, int yearofbirth) {
        this.username = username;
        this.name = name;
        this.yearofbirth = yearofbirth;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getYearofbirth() {
        return yearofbirth;
    }
}
