package vn.techzen.academy_pnv_25.repository;

import vn.techzen.academy_pnv_25.model.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();

    Department findById(Integer id);

    Department save(Department department);

    Department update(Department department);

    Department delete(Integer id);
}
