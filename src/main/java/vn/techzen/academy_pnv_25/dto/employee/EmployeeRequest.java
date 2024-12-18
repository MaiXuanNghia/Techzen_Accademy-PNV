package vn.techzen.academy_pnv_25.dto.employee;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_25.dto.Department.DepartmentRequest;
import vn.techzen.academy_pnv_25.typeCustom.Gender;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeRequest {
    UUID id;

    String name;

    LocalDate dob;

    Gender gender;

    Double salary;

    String phone;

    DepartmentRequest department;
}
