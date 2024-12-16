package vn.techzen.academy_pnv_25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.techzen.academy_pnv_25.dto.employee.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("""
    FROM Employee e WHERE (:#{#employeeSearchRequest.name} IS NULL OR e.name LIKE CONCAT('%', :#{#employeeSearchRequest.name}, '%'))
    AND (:#{#employeeSearchRequest.dobFrom} IS NULL OR e.dob >= :#{#employeeSearchRequest.dobFrom})
    AND (:#{#employeeSearchRequest.dobTo} IS NULL OR e.dob <= :#{#employeeSearchRequest.dobTo})
    AND (:#{#employeeSearchRequest.gender} IS NULL OR e.gender = :#{#employeeSearchRequest.gender})
    AND (:#{#employeeSearchRequest.salaryRange} = 0 OR e.salary >= :#{#employeeSearchRequest.salaryRange})
    AND (:#{#employeeSearchRequest.phone} IS NULL OR e.phone LIKE CONCAT('%', :#{#employeeSearchRequest.phone}, '%'))
    AND (:#{#employeeSearchRequest.departmentId} IS NULL OR e.department.id = :#{#employeeSearchRequest.departmentId})
""")
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

}
