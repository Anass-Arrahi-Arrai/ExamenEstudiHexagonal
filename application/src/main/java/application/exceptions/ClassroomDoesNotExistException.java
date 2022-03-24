package application.exceptions;

public class ClassroomDoesNotExistException extends RuntimeException {
    public ClassroomDoesNotExistException(String name) {
        super("Classroom " + name + " doesn't exist");
    }
}
