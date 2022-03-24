package application.portIN;

import application.dtos.ClassroomDTO;
import application.dtos.StudentDTO;

import java.util.List;

public interface ReadUseCases {
    List<StudentDTO> getStudents();
    StudentDTO getStudent(String id);
    StudentDTO getStudentByNameEager(String name);
    List<ClassroomDTO> getClassrooms();
    ClassroomDTO getClassroom(String name);
    List<ClassroomDTO> getClassroomsNoAllocations();
    List<ClassroomDTO> getFullyOccupiedClassrooms(String dayOfWeek);
    List<ClassroomDTO> getNotFullyOccupiedClassrooms(String dayOfWeek);
}
