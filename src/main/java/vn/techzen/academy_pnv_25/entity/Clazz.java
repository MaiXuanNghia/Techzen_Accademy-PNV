package vn.techzen.academy_pnv_25.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    String name;
    @OneToMany(mappedBy = "clazz")// nên hạn chế vì n ảnh hưởng đến hiệu xuất.
    @JsonIgnoreProperties("clazz")
    List<Student> students;
}