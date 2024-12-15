package vn.techzen.academy_pnv_25.service;

import vn.techzen.academy_pnv_25.model.Department;

import java.util.List;

public interface IDepartmentService {

    Department getDepartment(Integer id);

    List<Department> getAllDepartments();

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    Department deleteDepartment(Integer departmentId);
}
