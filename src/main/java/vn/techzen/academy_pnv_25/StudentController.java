package vn.techzen.academy_pnv_25;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_25.dto.ApiResponse;
import vn.techzen.academy_pnv_25.dto.Clazz.ClazzResponse;
import vn.techzen.academy_pnv_25.dto.Student.StudentRequest;
import vn.techzen.academy_pnv_25.dto.Student.StudentResponse;
import vn.techzen.academy_pnv_25.dto.exception.AppException;
import vn.techzen.academy_pnv_25.dto.exception.ErrorCode;
import vn.techzen.academy_pnv_25.entity.Clazz;
import vn.techzen.academy_pnv_25.entity.Student;
import vn.techzen.academy_pnv_25.mapper.IStudentMapper;
import vn.techzen.academy_pnv_25.service.IStudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;
    IStudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents(@RequestParam(defaultValue = "") String name,
                                                      Double fromScore,
                                                      Double toScore) {
        return ResponseEntity.ok(studentService.findAll(name, fromScore, toScore)
                .stream()
                .map(studentMapper::studentToStudentResponse)
                .collect(Collectors.toList())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudent(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new AppException(ErrorCode.TEACHER_NOT_EXIST);
        }

        return ResponseEntity.ok(ApiResponse.<StudentResponse>builder()
                .data(studentMapper.studentToStudentResponse(student))
                .build());
    }

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest) {
        // B1: StudentRequest -> student
        Student student = studentMapper.studentRequestToStudent(studentRequest);

        /* B2: Chuyển student sang student response */

        studentService.save(student);

        StudentResponse studentResponse = studentMapper.studentToStudentResponse(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
    }

}