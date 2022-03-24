package application.service;

import application.dtos.StudentDTO;
import application.portIN.addAllocationStudentUseCase;
import application.portOut.ClassroomDAO;
import org.springframework.stereotype.Service;

@Service
public class AddAllocationStudentService implements addAllocationStudentUseCase {

    private ClassroomDAO classroomDAO;
    public AddAllocationStudentService( ClassroomDAO cl) {
        this.classroomDAO = cl;
    }
    @Override
    public void allocateStudentInClassroom(StudentDTO student, String classroomName, String dayOfWeek) {
        classroomDAO.allocateStudentInClassroom(student.getId(), classroomName, dayOfWeek.toUpperCase());
    }
}
