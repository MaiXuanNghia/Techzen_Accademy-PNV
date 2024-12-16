package vn.techzen.academy_pnv_25.service;

import vn.techzen.academy_pnv_25.entity.Student;

import java.util.List;

public interface IStudentService {

    Student findById(Integer id);

    Student save(Student student);

    List<Student> findAll(String name, Double fromScore, Double toScore);
}
