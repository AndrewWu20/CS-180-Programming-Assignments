/**
 * WrongRideException
 *
 * Exception program that displays message when an exception occurs for the wrong ride
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class WrongRideException extends Exception {
    private String message;

    public WrongRideException(String message) {
        super(message);
    }
}
