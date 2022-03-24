package domain.exceptions;

public class StudentAlreadyAllocatedException extends RuntimeException {
    public StudentAlreadyAllocatedException(String classroom, String student, String dayOfWeek) {
        super("Student " + student + " is already allocated in classroom " + classroom + " on " + dayOfWeek);
    }
}
