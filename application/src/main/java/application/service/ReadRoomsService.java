package application.service;

import application.dtos.ClassroomDTO;
import application.dtos.StudentDTO;
import application.portIN.ReadUseCases;
import application.portOut.ClassroomDAO;
import application.portOut.StudentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadRoomsService implements ReadUseCases {

    StudentDAO studentDAO;
    ClassroomDAO classroomDAO;

    public ReadRoomsService(StudentDAO studentDAO, ClassroomDAO classroomDAO) {
        this.studentDAO = studentDAO;
        this.classroomDAO = classroomDAO;
    }

    @Override
    public List<StudentDTO> getStudents() {
        return studentDAO.getStudents();
    }

    @Override
    public StudentDTO getStudent(String id) {
        return studentDAO.getStudent(id);
    }

    @Override
    public StudentDTO getStudentByNameEager(String name) {
        return studentDAO.getStudentByName(name);
    }

    @Override
    public List<ClassroomDTO> getClassrooms() {
        return classroomDAO.getClassroomsAllocations();
    }

    @Override
    public ClassroomDTO getClassroom(String name) {
        return classroomDAO.getClassroom(name);
    }

    @Override
    public List<ClassroomDTO> getClassroomsNoAllocations() {
        return classroomDAO.getClassroomsNoAllocations();
    }

    @Override
    public List<ClassroomDTO> getFullyOccupiedClassrooms(String dayOfWeek) {
        return classroomDAO.getFullClassrooms(dayOfWeek);
    }

    @Override
    public List<ClassroomDTO> getNotFullyOccupiedClassrooms(String dayOfWeek) {
        return classroomDAO.getNotFullClassrooms(dayOfWeek);
    }
}
