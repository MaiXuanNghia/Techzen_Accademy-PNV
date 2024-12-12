package vn.techzen.academy_pnv_25;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Nguyen Van Duc", 9.6),
                    new Student(2, "Hoang Nhat Tan", 9.5),
                    new Student(3, "Nguyen Van a", 9.6),
                    new Student(4, "Hoang Nhat f", 9.5),
                    new Student(5, "Nguyen Van x", 9.6),
                    new Student(6, "Hoang Nhat e", 9.5)
            )
    );
    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        for (Student student : students){
            if (student.getId() == id) {
                return ResponseEntity.ok(student);
            }
        }
        return null;
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId((int) (Math.random() * 100000));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentNew) {
        for (Student student : students){
            if (student.getId() == id) {
                student.setName(studentNew.getName());
                student.setScore(studentNew.getScore());
                return ResponseEntity.ok(student);
            }
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        for (Student student : students){
            if (student.getId() == id) {
                students.remove(student);
                return ResponseEntity.ok(student);
            }
        }
        return null;
    }
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam(defaultValue = " ") String keyword) {
        List<Student> matchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchedStudents.add(student);
            }
        }
        if (matchedStudents.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(matchedStudents);
    }
}

