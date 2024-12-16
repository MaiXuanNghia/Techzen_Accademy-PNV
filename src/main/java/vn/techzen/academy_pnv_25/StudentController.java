package vn.techzen.academy_pnv_25;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.dto.exception.AppException;
import vn.techzen.academy_pnv_25.dto.exception.ErrorCode;
import vn.techzen.academy_pnv_25.entity.Student;
import vn.techzen.academy_pnv_25.service.IStudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam(defaultValue = "nghia") String name,
                                                     @RequestParam(required = false) Double fromScore,
                                                     @RequestParam(required = false) Double toScore) {
        System.out.println("GetStudents method is called!");

        return ResponseEntity.ok(studentService.findAll(name, fromScore, toScore));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new AppException(ErrorCode.TEACHER_NOT_EXIST);
        }

        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .data(student)
                .build());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

}