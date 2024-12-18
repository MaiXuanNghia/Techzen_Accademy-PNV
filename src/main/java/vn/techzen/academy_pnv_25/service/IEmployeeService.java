package vn.techzen.academy_pnv_25.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.techzen.academy_pnv_25.typeCustom.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.entity.Employee;
import vn.techzen.academy_pnv_25.typeCustom.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService  {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    List<Employee> findAll();

    Page<Employee> findAll(String name, LocalDate dobFrom, LocalDate dobTo, Gender gender, String salaryRange, String phone, Integer departmentId,Pageable pageable);

    Optional<Employee> findById(UUID id);

    Employee save(Employee employee);

    Optional<Employee> update(Employee employee);

    Optional<Employee> delete(Employee employee);
}
