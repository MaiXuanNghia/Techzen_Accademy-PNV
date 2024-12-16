package vn.techzen.academy_pnv_25.service;

import vn.techzen.academy_pnv_25.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    Optional<Department> getDepartment(Integer id);

    List<Department> getAllDepartments();

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    Department deleteDepartment(Integer departmentId);
}
