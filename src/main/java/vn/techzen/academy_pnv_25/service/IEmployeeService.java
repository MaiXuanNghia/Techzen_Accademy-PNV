package vn.techzen.academy_pnv_25.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService  {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    List<Employee> findAll();

    Optional<Employee> findById(UUID id);

    Employee save(Employee employee);

    Optional<Employee> update(Employee employee);

    Optional<Employee> delete(Employee employee);

    Page<Employee> findAll(Pageable pageable);
}
