package vn.techzen.academy_pnv_25.dto.Student;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_25.dto.Clazz.ClazzRequest;
import vn.techzen.academy_pnv_25.dto.Clazz.ClazzResponse;
import vn.techzen.academy_pnv_25.entity.Clazz;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StudentRequest {
    double score;
    String name;

    ClazzRequest clazz;
}
