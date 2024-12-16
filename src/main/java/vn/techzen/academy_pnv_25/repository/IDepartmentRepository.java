package vn.techzen.academy_pnv_25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techzen.academy_pnv_25.entity.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}
