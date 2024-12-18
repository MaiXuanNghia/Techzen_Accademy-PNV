package vn.techzen.academy_pnv_25.dto.Department;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DepartmentResponse {
    Integer id;
    String name;
}
