package vn.techzen.academy_pnv_25.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    double score;
    String name;

    @ManyToOne
//    @JsonIgnore; // sẽ bỏ qua tất cả. class tránh đệ quy
    @JsonIgnoreProperties("students")// chỉ bỏ qua class tránh đệ quy nhưng vẫn hiển thị..
    Clazz clazz;

}
