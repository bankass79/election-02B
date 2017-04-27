package spring.amadou.elections;

@SuppressWarnings("serial")
public class ElectionsException extends RuntimeException {
    public ElectionsException() {
        super();
    }

    public ElectionsException(String message) {
        super(message);
    }

    public ElectionsException(Throwable cause) {
        super(cause);
    }

    public ElectionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
