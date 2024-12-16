package vn.techzen.academy_pnv_25.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    UUID id;

    String name;

    LocalDate dob;

    Gender gender;

    Double salary;

    String phone;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public enum Gender {
        MALE, FEMALE, OTHER;
    }
}


