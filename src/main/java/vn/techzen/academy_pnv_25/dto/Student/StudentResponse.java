package vn.techzen.academy_pnv_25.dto.Student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_25.dto.Clazz.ClazzResponse;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class StudentResponse {
    Integer id;
    double score;
    String name;

    @ManyToOne
    @JsonIgnoreProperties("studentsResponse")
    ClazzResponse clazz;
}
