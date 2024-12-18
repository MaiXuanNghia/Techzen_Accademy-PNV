package vn.techzen.academy_pnv_25.dto.Clazz;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ClazzRequest {
    int id;
    String name;
}
