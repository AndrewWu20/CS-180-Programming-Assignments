/**
 * Session
 *
 * This program will represent an individual session in a lab.
 *
 * @author Andrew WU, L10
 *
 * @version 3/11/22
 *
 */

public class Session {
    private String name;
    private int enrollment;

    public Session(String name, int enrollment) {
        this.name = name;
        this.enrollment = enrollment;
    }

    public Session() {
        name = "";
        enrollment = 0;
    }

    public String getName() {
        return name;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        String session = "Session{Name - %s, Enrollment - %s}";

        return String.format(session, this.name, this.enrollment);
    }
}
