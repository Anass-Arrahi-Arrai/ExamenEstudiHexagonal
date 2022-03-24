package application.service;
import application.dtos.StudentDTO;
import application.portIN.addNewStudentUseCase;
import application.portOut.StudentDAO;
import org.springframework.stereotype.Service;

@Service
public class addNewStudentService implements addNewStudentUseCase {

    private StudentDAO studentDAO;
    public addNewStudentService (StudentDAO st){
        studentDAO = st;
    }
    @Override
    public void setNewStudent(StudentDTO studentDTO) {
        studentDAO.addStudent(studentDTO);
    }
}
