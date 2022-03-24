
import application.dtos.StudentDTO;
import application.exceptions.StudentDoesNotExistException;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO implements application.portOut.StudentDAO {
    private JdbcTemplate jdbcTemplate;

    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResultSetExtractorImpl<StudentDTO> studentsRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(StudentDTO.class);

    RowMapperImpl<StudentDTO> studentRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(StudentDTO.class);

    @Override
    public List<StudentDTO> getStudents() {
        final var query = "select * from student";
        return jdbcTemplate.query(query, studentsRowMapper);
    }

    @Override
    public StudentDTO getStudent(String id) {
        final var query = "select * from student where id = ?";
        try {
            return jdbcTemplate.queryForObject(query, studentRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new StudentDoesNotExistException(id);
        }
    }

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        final var query = "INSERT INTO student (id, name, secondname, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, studentDTO.getId(), studentDTO.getName(), studentDTO.getSecondName(), studentDTO.getEmail());

        return this.getStudent(studentDTO.getId());
    }

    @Override
    public StudentDTO getStudentByName(String name) {
        final String queryProfile = "select * from student where name = ?";
        return getStudentDTO(name, queryProfile);
    }

    private StudentDTO getStudentDTO(String id, String queryProfile) {
        List<StudentDTO> result;
        try {
            result = jdbcTemplate.query(queryProfile, studentsRowMapper, id);
            return result.get(0);
        } catch (EmptyResultDataAccessException e) {
            throw new StudentDoesNotExistException(id);
        }
    }
}
