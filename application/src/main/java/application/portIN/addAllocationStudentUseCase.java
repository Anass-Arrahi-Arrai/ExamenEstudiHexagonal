package application.portIN;

import application.dtos.StudentDTO;

public interface addAllocationStudentUseCase {
    void allocateStudentInClassroom(StudentDTO student, String classroomName, String dayOfWeek);
}
