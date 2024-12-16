package vn.techzen.academy_pnv_25.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.techzen.academy_pnv_25.dto.exception.AppException;
import vn.techzen.academy_pnv_25.dto.exception.ErrorCode;
import vn.techzen.academy_pnv_25.entity.Department;
import vn.techzen.academy_pnv_25.repository.IDepartmentRepository;
import vn.techzen.academy_pnv_25.service.IDepartmentService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    IDepartmentRepository departmentRepository;

    @Override
    public Optional<Department> getDepartment(Integer id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        for (Department d : departmentRepository.findAll()) {
            if (d.getId().equals(department.getId())) {
                d.setName(department.getName());
                return departmentRepository.save(department);
            }
        }
        throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }

    public Department deleteDepartment(Integer departmentId) {
        departmentRepository.deleteById(departmentId);
        return departmentRepository.findById(departmentId).get();
    }
}
