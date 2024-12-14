package vn.techzen.academy_pnv_25;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employees {

    UUID id;

    String name;

    LocalDate dob;

    Gender gender;

    Double salary;

    String phone;

    public enum Gender {
        MALE, FEMALE, OTHER;
    }
}


