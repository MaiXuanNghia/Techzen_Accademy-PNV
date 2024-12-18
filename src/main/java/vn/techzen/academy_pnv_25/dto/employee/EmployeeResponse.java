package vn.techzen.academy_pnv_25.dto.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_25.dto.Department.DepartmentResponse;
import vn.techzen.academy_pnv_25.typeCustom.Gender;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeResponse {
    UUID id;

    String name;

    LocalDate dob;

    Gender gender;

    Double salary;

    String phone;

    @ManyToOne
    @JoinColumn(name = "department_id")
    DepartmentResponse department;
}
