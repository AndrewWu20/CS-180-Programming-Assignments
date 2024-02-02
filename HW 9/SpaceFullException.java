/**
 * SpaceFullException
 *
 * Exception program that displays message when a ride is full
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class SpaceFullException extends Exception {
    private String message;

    public SpaceFullException(String message) {
        super(message);
    }
}
