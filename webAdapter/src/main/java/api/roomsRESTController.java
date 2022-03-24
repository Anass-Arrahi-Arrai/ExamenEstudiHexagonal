package api;

import application.dtos.ClassroomDTO;
import application.dtos.StudentDTO;
import application.service.AddAllocationStudentService;
import application.service.ReadRoomsService;
import application.service.addNewStudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.security.Principal;
import java.util.List;

@Validated
@RestController
public class roomsRESTController {
    private AddAllocationStudentService allocateStudent;
    private addNewStudentService addNewStudent;
    private ReadRoomsService readRoomsService;

    public roomsRESTController(AddAllocationStudentService alloStudent, addNewStudentService addStudent,ReadRoomsService readServ ) {
        this.allocateStudent = alloStudent;
        this.addNewStudent = addStudent;
        this.readRoomsService = readServ;
    }

    @GetMapping("/students")
    public List<StudentDTO> getStudents() {
        return readRoomsService.getStudents();
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudent(@PathVariable String id) {
        return readRoomsService.getStudent(id);
    }

    @GetMapping("students/me")
    public StudentDTO getMyProfile(Principal principal) {
        return readRoomsService.getStudentByNameEager(principal.getName());
    }

    @GetMapping("/classrooms")
    public List<ClassroomDTO> getClassrooms() {
        return readRoomsService.getClassroomsNoAllocations();
    }

    @GetMapping("/classrooms/allocations")
    public List<ClassroomDTO> getClassroomsAllocations() {
        return readRoomsService.getClassrooms();
    }

    @GetMapping("/classrooms/{name}/allocations")
    public ClassroomDTO getClassroom(@PathVariable String name) {
        return readRoomsService.getClassroom(name);
    }

    @GetMapping("/classrooms/allocations/{dayOfWeek}")
    public List<ClassroomDTO> fullyOccupiedOrNotClassrooms(@RequestParam(defaultValue = "true") boolean full,
                                                           @PathVariable @Pattern(regexp = "\\bmonday|\\btuesday|\\bwednesday|\\bthursday|\\bfriday|\\bsaturday|\\bsunday/i", flags = Pattern.Flag.CASE_INSENSITIVE) String dayOfWeek) {
        if (full)
            return readRoomsService.getFullyOccupiedClassrooms(dayOfWeek);
        else
            return readRoomsService.getNotFullyOccupiedClassrooms(dayOfWeek);
    }



    @PostMapping("/students")
    public void postStudent(@RequestBody @Valid StudentDTO student) {
        addNewStudent.setNewStudent(student);
    }

    @PostMapping("classrooms/{name}/allocations/{dayOfWeek}/students/{studentId}")
    public void postAllocation(@PathVariable String name, @PathVariable String studentId,
                               @PathVariable @Pattern(regexp = "\\bmonday|\\btuesday|\\bwednesday|\\bthursday|\\bfriday|\\bsaturday|\\bsunday/i", flags = Pattern.Flag.CASE_INSENSITIVE) String dayOfWeek) {
        StudentDTO studentDTO = readRoomsService.getStudent(studentId);
        allocateStudent.allocateStudentInClassroom(studentDTO, name, dayOfWeek.toUpperCase());
    }

}
