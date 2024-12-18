package vn.techzen.academy_pnv_25.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.techzen.academy_pnv_25.typeCustom.EmployeeSearchRequest;
import vn.techzen.academy_pnv_25.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("""
    FROM Employee e WHERE (:#{#employeeSearchRequest.name} IS NULL OR e.name LIKE CONCAT('%', :#{#employeeSearchRequest.name}, '%'))
    AND (:#{#employeeSearchRequest.dobFrom} IS NULL OR e.dob >= :#{#employeeSearchRequest.dobFrom})
    AND (:#{#employeeSearchRequest.dobTo} IS NULL OR e.dob <= :#{#employeeSearchRequest.dobTo})
    AND (:#{#employeeSearchRequest.gender} IS NULL OR e.gender = :#{#employeeSearchRequest.gender})
    AND (:#{#employeeSearchRequest.phone} IS NULL OR e.phone LIKE CONCAT('%', :#{#employeeSearchRequest.phone}, '%'))
    AND (:#{#employeeSearchRequest.departmentId} IS NULL OR e.department.id = :#{#employeeSearchRequest.departmentId})
    AND ((:#{#employeeSearchRequest.salaryRange} IS NULL) OR
               (:#{#employeeSearchRequest.salaryRange} = '1-5' AND e.salary < 5000000) OR
               (:#{#employeeSearchRequest.salaryRange} = '5-10' AND e.salary BETWEEN 5000000 AND 10000000) OR
               (:#{#employeeSearchRequest.salaryRange} = '10-20' AND e.salary BETWEEN 10000000 AND 20000000) OR
               (:#{#employeeSearchRequest.salaryRange} = 'gt20' AND e.salary > 20000000)
    )
""")
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    @Query("""
    FROM Employee e WHERE (:#{#employeeSearchRequest.name} IS NULL OR e.name LIKE CONCAT('%', :#{#employeeSearchRequest.name}, '%'))
    AND (:#{#employeeSearchRequest.dobFrom} IS NULL OR e.dob >= :#{#employeeSearchRequest.dobFrom})
    AND (:#{#employeeSearchRequest.dobTo} IS NULL OR e.dob <= :#{#employeeSearchRequest.dobTo})
    AND (:#{#employeeSearchRequest.gender} IS NULL OR e.gender = :#{#employeeSearchRequest.gender})
    AND (:#{#employeeSearchRequest.phone} IS NULL OR e.phone LIKE CONCAT('%', :#{#employeeSearchRequest.phone}, '%'))
    AND (:#{#employeeSearchRequest.departmentId} IS NULL OR e.department.id = :#{#employeeSearchRequest.departmentId})
    AND ((:#{#employeeSearchRequest.salaryRange} IS NULL) OR
               (:#{#employeeSearchRequest.salaryRange} = '1-5' AND e.salary < 5000000) OR
               (:#{#employeeSearchRequest.salaryRange} = '5-10' AND e.salary BETWEEN 5000000 AND 10000000) OR
               (:#{#employeeSearchRequest.salaryRange} = '10-20' AND e.salary BETWEEN 10000000 AND 20000000) OR
               (:#{#employeeSearchRequest.salaryRange} = 'gt20' AND e.salary > 20000000)
    )
""")
    Page<Employee> findByAttr(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);
}
