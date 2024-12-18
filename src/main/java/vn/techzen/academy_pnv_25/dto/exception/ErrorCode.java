package vn.techzen.academy_pnv_25.dto.exception;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    EMPLOYEES_NOT_EXIST(40401, "Employees is not exist!", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXIST(40402, "Department is not exist!", HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXIST(404011, "Teacher not exist!", HttpStatus.NOT_FOUND);

    Integer code;
    String message;
    HttpStatus status;
}