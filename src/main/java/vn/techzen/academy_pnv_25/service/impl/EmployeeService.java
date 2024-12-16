package vn.techzen.academy_pnv_25.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.dto.exception.AppException;
import vn.techzen.academy_pnv_25.dto.exception.ErrorCode;
import vn.techzen.academy_pnv_25.entity.Employee;
import vn.techzen.academy_pnv_25.repository.IEmployeeRepository;
import vn.techzen.academy_pnv_25.service.IEmployeeService;
import vn.techzen.academy_pnv_25.utils.JsonResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeService implements IEmployeeService {
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest);
    }


    @Override
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(Employee employee) {
        Optional<Employee> oldEmployeeOptional = employeeRepository.findById(employee.getId());

        if (oldEmployeeOptional.isPresent()) {
            Employee oldEmployee = oldEmployeeOptional.get();
            oldEmployee.setName(employee.getName());
            oldEmployee.setDob(employee.getDob());
            oldEmployee.setGender(employee.getGender());
            oldEmployee.setSalary(employee.getSalary());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setPhone(employee.getPhone());
            employeeRepository.save(oldEmployee);
            return Optional.of(oldEmployee);
        } else {
            throw new AppException(ErrorCode.EMPLOYEES_NOT_EXIST);
        }
    }


    @Override
    public Optional<Employee> delete(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());

        if (existingEmployee.isPresent()) {
            employeeRepository.deleteById(employee.getId());
            return existingEmployee;
        } else {
            throw new AppException(ErrorCode.EMPLOYEES_NOT_EXIST);
        }
    }


}
