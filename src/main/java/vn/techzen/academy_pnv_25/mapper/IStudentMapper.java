package vn.techzen.academy_pnv_25.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;
import vn.techzen.academy_pnv_25.dto.Student.StudentRequest;
import vn.techzen.academy_pnv_25.dto.Student.StudentResponse;
import vn.techzen.academy_pnv_25.entity.Student;
// bean của spring
@Mapper(componentModel = "spring")
@Primary
public interface IStudentMapper {
    StudentResponse studentToStudentResponse(Student student);
    Student studentRequestToStudent(StudentRequest studentRequest);
}
