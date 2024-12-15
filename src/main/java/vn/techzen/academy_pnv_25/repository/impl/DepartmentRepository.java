package vn.techzen.academy_pnv_25.repository.impl;
import org.springframework.stereotype.Repository;
import vn.techzen.academy_pnv_25.model.Department;
import vn.techzen.academy_pnv_25.repository.IDepartmentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DepartmentRepository implements IDepartmentRepository {

    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "HR"),
                    new Department(2, "IT"),
                    new Department(3, "Finance"),
                    new Department(4, "Marketing")
            )
    );

    public List<Department> findAll() {
        return departments;
    }

    public Department findById(Integer id) {
        for (Department department : departments) {
            if (department.getId().equals(id)) {
                return department;
            }
        }
        return null;
    }

    public Department save(Department department) {
        for (Department dep : departments) {
            if (dep.getId().equals(department.getId())) {
                departments.add(dep);
                return dep;
            }
        }
        return null;
    }

    public Department update(Department department) {
        for (Department dep : departments) {
            if (dep.getId().equals(department.getId())) {
                departments.add(dep);
                return dep;
            }
        }
        return null;
    }

    public Department delete(Integer id) {
        for (Department dep : departments) {
            if (dep.getId().equals(id)) {
                departments.remove(dep);
                return dep;
            }
        }
        return null;
    }
}
