package part1.exceptions;

/***
 *
 *
 *
 */
public class AlreadyTakenUsernameException extends Exception {

    /**
     * Exception constructor
     *
     * @param message Message of the exception
     */
    public AlreadyTakenUsernameException(String message) {
        super(message);
    }
}