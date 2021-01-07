package part1;

/***
 *
 * This class will store the representation of a User
 *
 */
public class User {
    private final String username;
    private final String name;
    private final int yearofbirth;

    /**
     *
     * User constructor.
     *
     * @param username Username
     * @param name Personal name
     * @param yearofbirth Year of birth of the user (must be after 1899 and before the next year of this User creation)
     */
    public User(String username, String name, int yearofbirth) {
        this.username = username;
        this.name = name;
        this.yearofbirth = yearofbirth;
    }

    /**
     *
     * Getter
     *
     * @return Username of this account
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * Getter
     *
     * @return Personal name of this account
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Getter
     *
     * @return Year of birth of the user
     */
    public int getYearofbirth() {
        return yearofbirth;
    }
}
