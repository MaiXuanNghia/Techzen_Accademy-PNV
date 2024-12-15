package vn.techzen.academy_pnv_25.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.model.Employee;
import vn.techzen.academy_pnv_25.repository.IEmployeeRepository;
import vn.techzen.academy_pnv_25.service.IEmployeeService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest);
    }


    @Override
    public Employee findById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public Employee delete(Employee employee) {
        return employeeRepository.delete(employee);
    }

}