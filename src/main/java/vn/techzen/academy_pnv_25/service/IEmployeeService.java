package vn.techzen.academy_pnv_25.service;

import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.model.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    Employee findById(UUID id);

    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee delete(Employee employee);
}