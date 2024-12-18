package vn.techzen.academy_pnv_25.dto.Clazz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_25.dto.Student.StudentResponse;
import vn.techzen.academy_pnv_25.entity.Student;

import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ClazzResponse {
    int id;
    String name;
}
